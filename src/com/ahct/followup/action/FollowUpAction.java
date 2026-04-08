package com.ahct.followup.action;

import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.util.ClaimCases;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.ErrPaymentReportVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.PdfGenerator;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;

import com.ahct.followup.form.FollowUpForm;
import com.ahct.followup.service.FollowUpService;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.model.EhfmSeq;


import com.ahct.preauth.service.PreauthService;
import com.ahct.preauth.vo.AttachmentVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PatientVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;

public class FollowUpAction extends Action {
	private final static Logger logger = Logger.getLogger ( FollowUpAction.class ) ;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		ResourceBundle bundle = ResourceBundle.getBundle("SGVConstants");
		HttpSession session = request.getSession(false);
		String callType = null;
		if (request.getParameter("callType") != null
				&& !ClaimsConstants.EMPTY.equals(request.getParameter("callType"))) {
			callType = request.getParameter("callType");
		}
		String lStrResultPage = HtmlEncode.verifySession(request, response);
		if (lStrResultPage.length() > 0) {
			request.setAttribute("caseSession", ClaimsConstants.Y);
			if (callType != null && "Ajax".equals(callType)) {
				request.setAttribute("AjaxMessage", "SessionExpired");
				return mapping.findForward("ajaxResult");
			} else
				return mapping.findForward("sessionExpire");
		}
		String lStrFlagStatus = request.getParameter("actionFlag");
		/*TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");*/
		SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
		String caseStartTime="";
		String lStrEmpId = null;String lStrUserState=null;
		String lStrLocId = null;
		String lStrLangId = null;
		String lStrUserName = null;
		String oldRoleId = null;
		String saveFlag = ClaimsConstants.EMPTY;
		String lStrUserRole = null;
		String lStrCaseId = null;
		String lStrUserId = null;
		String lStrPageName = null;
		String caseIdchk=null;
		String caseIdOnLoad=null;
		String cochlearFollowUpId=null;
		String cochlearCaseId=null;
		String cochlearCasesApprovalFlag=null;
		String cochlearSubmitUserGrp=null;
		String cochlearSubmitButton=null;
		String cochlearMsg=null,cochlearSms=null,fromClaimSave=null;
		
		ConfigurationService configurationService = (ConfigurationService) ServiceFinder
				.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		FollowUpForm followUpForm = (FollowUpForm) form;
		FollowUpService followUpService = (FollowUpService) ServiceFinder
				.getContext(request).getBean("followUpService");
		if (session.getAttribute("UserID") != null
				&& !session.getAttribute("UserID").equals(ClaimsConstants.EMPTY))
			lStrUserId = session.getAttribute("UserID").toString();
		if ((session.getAttribute("EmpID") != null)
				&& !(session.getAttribute("EmpID").equals(ClaimsConstants.EMPTY)))
			lStrEmpId = (String) session.getAttribute("EmpID");
		if ((session.getAttribute("LocID") != null)
				&& !(session.getAttribute("LocID").equals(ClaimsConstants.EMPTY)))
			lStrLocId = (String) session.getAttribute("LocID");
		if ((session.getAttribute("LangID") != null)
				&& !(session.getAttribute("LangID").equals(ClaimsConstants.EMPTY)))
			lStrLangId = (String) session.getAttribute("LangID");
		if ((session.getAttribute("userName") != null)
				&& !(session.getAttribute("userName").equals(ClaimsConstants.EMPTY)))
			lStrUserName = (String) session.getAttribute("userName");
		if ((session.getAttribute("UserRole") != null)
				&& !(session.getAttribute("UserRole").equals(ClaimsConstants.EMPTY)))
			lStrUserRole = (String) session.getAttribute("UserRole");
		if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
	    	lStrUserState = ( String ) session.getAttribute ( "userState" ) ;
		request.setAttribute("userState",lStrUserState);
		if (request.getParameter("caseId") != null
				&& !request.getParameter("caseId").equals(ClaimsConstants.EMPTY)) {
			lStrCaseId = request.getParameter("caseId");
			request.setAttribute("caseId", lStrCaseId);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		
		List<LabelBean> groupsList = null;
		if ((session.getAttribute("groupList") != null)
				&& !(session.getAttribute("groupList").equals(ClaimsConstants.EMPTY)))
			groupsList = (List<LabelBean>) session.getAttribute("groupList");

		CommonService commonService = (CommonService) ServiceFinder.getContext(request).getBean("commonService");
		TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		PreauthService preauthService = (PreauthService)ServiceFinder.getContext(
				request).getBean("preauthService");
		String lStrRowsperpage = bundle.getString("case.RowsPerPage");
		String lStrStartIndex = bundle.getString("case.StartIndex0");
		String showPage = bundle.getString("case.ShowPage");
		String next = bundle.getString("case.next");
		
		if(groupsList!=null)
			{
				if(groupsList.size()>0)
					{
						for(LabelBean lb:groupsList)
							{
								if(lb.getID()!=null &&
										!"".equalsIgnoreCase(lb.getID()))
									if(lb.getID().equalsIgnoreCase(config.getString("MEDCO_GRP")))
										request.setAttribute("MedcoYN", "Y");
							}
					}
			}

		if (lStrFlagStatus != null && "saveFollowUpDtls".equals(lStrFlagStatus)) {

			 caseStartTime = sds.format(new Date().getTime());
			FollowUpVO followUpVO = new FollowUpVO();
			followUpVO.setCASEID(lStrCaseId);

			SimpleDateFormat sdfw = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String crtDate = sdfw.format(date);
			long ltime = date.getTime();

			//String lStrMsg = followUpService.checkValidForFollowup(lStrCaseId);
			/*if (!lStrMsg.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
				request.setAttribute("msg", lStrMsg);
				lStrFlagStatus = "followUpinit";
			}*/
			Long followUpSeq = followUpService.getNextFollowUpId(followUpVO);
			if(followUpSeq==0){
				request.setAttribute("msg",
				"360 days completed from discharge day.No FollowUp can be initiated now.");
		        lStrFlagStatus = "followUpinit";
			}
			else {	
				followUpVO.setFollowUpSeq(followUpSeq+"");
				followUpVO.setFlag("FOLLOWUP");
				followUpVO.setBLOODPRESSURE(followUpForm.getBloodPS() + '/'
						+ followUpForm.getBloodPD());
				followUpVO.setFLUIDINPT(followUpForm.getFluidIn());
				followUpVO.setFLUIDOUTPUT(followUpForm.getFluidOut());
				followUpVO.setHEART_RATE(followUpForm.getHeartRate());
				followUpVO.setLUNGS(followUpForm.getLungs());
				followUpVO.setPULSE(followUpForm.getPluseRate());
				followUpVO.setRESPIRATORY(followUpForm.getRespRate());
				followUpVO.setTEMPERATURE(followUpForm.getTemp());
				followUpVO.setHEMOGLOBIN(followUpForm.getHemoglobin());
				followUpVO.setDRUGCODE(followUpForm.getDrugCode());
				followUpVO.setASRIDRUGCODE(followUpForm.getAsriDrugCode());
				followUpVO.setROUTE(followUpForm.getRoute());
				followUpVO.setDOSAGE(followUpForm.getDosage());
				followUpVO.setSTRENGTH(followUpForm.getStrength());
				followUpVO.setMEDICATION(followUpForm.getMedicatPeriod());
				
				followUpVO.setNxtFollowUpDt(followUpForm.getNxtFollowUpDt());

				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				DrugsVO drugsVO = null;
				String s = followUpForm.getDrugs().substring(0,
						followUpForm.getDrugs().length() - 1);
				String[] drugs = s.split("@,");
				for (int z = 0; z < drugs.length; z++) {
					String drug = drugs[z];
					String[] drugValues = drug.split("~");
					drugsVO = new DrugsVO();
					drugsVO.setDrugTypeName(drugValues[0]);
					drugsVO.setDrugSubTypeName(drugValues[1]);
					drugsVO.setDrugName(drugValues[2]);
					drugsVO.setRoute(drugValues[3]);
					drugsVO.setStrength(drugValues[4]);
					drugsVO.setDosage(drugValues[5]);
					drugsVO.setMedicationPeriod(drugValues[6]);
					drugsVO.setBatchNumber(drugValues[7]);
					drugsVO.setDrugExpiryDt(drugValues[8]);
					
					Long drugSeqNextVal;
					try {
						drugSeqNextVal = Long.parseLong(commonService.getSequence("FOLLOWUP_DRUG_SEQ"));
						drugsVO.setDrugId(drugSeqNextVal);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					drugsList.add(drugsVO);
				}
				followUpVO.setDrugs(drugsList);
				List<LabelBean> lGenInvList = new ArrayList<LabelBean>();
				List<AttachmentVO> lGenTestAttachList = new ArrayList<AttachmentVO>();
				int i = 0;
				FormFile lFormFile = null;
				String errmsg = ClaimsConstants.EMPTY;

				if (request.getParameter("selInvest") != null
						&& !request.getParameter("selInvest").equals(ClaimsConstants.EMPTY)) {
					String genInvestigationList = request
							.getParameter("selInvest");
					followUpVO.setINVESTIGATIONS(genInvestigationList);
					LabelBean labelBean = new LabelBean();
					StringTokenizer st = new StringTokenizer(
							genInvestigationList, "~");
					while (st.hasMoreTokens()) {
						labelBean = new LabelBean();
						String lL = st.nextToken();
						StringTokenizer st1 = new StringTokenizer(lL, "$");
						while (st1.hasMoreTokens()) {
							labelBean.setVALUE(st1.nextToken());
							labelBean.setID(st1.nextToken());
							lGenInvList.add(labelBean);
						}
					}
				}
				//For General Investigations
				for (LabelBean labelBean : lGenInvList) {
					// used to avoid deleted attachments-conflict
					while (followUpForm.getGenAttach(i) == null) {
						i++;
					}
					if (followUpForm.getGenAttach(i) != null) {
						lFormFile = followUpForm.getGenAttach(i);
						if (lFormFile.getFileSize() > 204800) {
							errmsg = errmsg + "\\'" + labelBean.getVALUE()
									+ "\\' \\n";
						}
					}
					i++;
				}
				if (ClaimsConstants.EMPTY.equals(errmsg)) {
					i = 0;
					String lDir = null;
					String lFileName = null;
					String lFileExt = null;
					int lCount = 0;
					String lStrTotFileName = null;
					// For General Investigations
					for (LabelBean labelBean : lGenInvList) {
						AttachmentVO attachmentVO = new AttachmentVO();
						// used to avoid deleted attachments-conflict
						while (followUpForm.getGenAttach(i) == null) {
							i++;
						}
						lFormFile = followUpForm.getGenAttach(i);
						lFileName = lFormFile.getFileName();
						lCount = lFileName.lastIndexOf(".");
						lFileExt = lFileName.substring(lCount + 1,
								lFileName.length());
						// save file in folder's
						String lStrSharePath = config.getString("STORAGE_BOX")
								+ /*config.getString("SLASH_VALUE")+*/ crtDate
								+ config.getString("SLASH_VALUE") + lStrCaseId
								+ config.getString("SLASH_VALUE")
								+ config.getString("followUpReport")
								+ config.getString("SLASH_VALUE"); 

						lStrTotFileName = ltime + "_" + lFileName;
						lDir = lStrSharePath + lStrTotFileName;
						try {
							File lFile = new File(lDir);
							File lDirectory = new File(lStrSharePath);
							boolean lbDirectioryPresent = false;
							if (!lDirectory.exists()) {
								lbDirectioryPresent = lDirectory.mkdirs();
							} else {
								lbDirectioryPresent = true;
							}

							if (lbDirectioryPresent) {
								if (lFile.exists()) {
									lFile.delete();
								}

								FileOutputStream lFileOutputStream = new FileOutputStream(
										lFile);
								byte[] fileData = lFormFile.getFileData();
								lFileOutputStream.write(fileData);
								lFileOutputStream.flush();
								lFileOutputStream.close();
								lFileOutputStream = null;
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						attachmentVO.setTestId(labelBean.getID());
						attachmentVO.setTestName(labelBean.getVALUE());
						// attachmentVO.setFileName(lStrTotFileName);
						attachmentVO.setFileName(lFileName);
						attachmentVO.setFileSize(lFormFile.getFileSize());
						attachmentVO.setFileReportPath(lDir);
						attachmentVO.setFileContentType(lFormFile
								.getContentType());
						attachmentVO.setFileExtsn(lFileExt);
						try {
							String genTestSeqNextVal =commonService.getSequence("FOLLOWUP_GEN_ATTACH_SEQ");
							attachmentVO.setAttachId(genTestSeqNextVal);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// genTestSeqNextVal++;
						lGenTestAttachList.add(attachmentVO);
						i++;
					}
					followUpVO.setGenAttachmentsList(lGenTestAttachList);

					String lStrResultMsg = followUpService.saveFollowUp(followUpVO, lStrUserId);
					 if(lStrResultMsg!=null && !"".equalsIgnoreCase(lStrResultMsg))
					 {
						String caseEndTime = sds.format(new Date().getTime());
						loggingService.logTime("FollowUpDone", lStrResultMsg, caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
						 lStrResultMsg= "Followup submitted successfully with FollowUp ID : "+lStrResultMsg;
					 }
					 else
						 lStrResultMsg= "faliure"; 	
					request.setAttribute("msg", lStrResultMsg);
					lStrFlagStatus = "followUpinit";
				} else {
					request.setAttribute("msg",
							"Uploaded File Size Should not exceed 200KB");
					lStrFlagStatus = "followUpinit";
				}
			}
		}
		if (lStrFlagStatus != null && "followUpinit".equals(lStrFlagStatus)) {
			List<LabelBean> investigationsList = null;
			List<LabelBean> drugsList = null;
			long daysDiff=0L;
			String offset=null;
			Date nxtFollowUpDt=null;
			String nextFupDt="hide";
			try {
				investigationsList=commonService.getInvestBlockName();
				drugsList = commonService.getDrugs();
				if(lStrCaseId!=null )
					{
						if(!"".equalsIgnoreCase(lStrCaseId))
							{
								daysDiff=followUpService.getFollowUpLst(lStrCaseId);
							}
					}
				if(daysDiff!=0L)
					{
						if(daysDiff>=0 && daysDiff<=90)
							{
								offset="101";
								nextFupDt="show";
							}
						else if(daysDiff>90 && daysDiff<=180)
							{
								offset="191";
								nextFupDt="show";	
							}
						else if(daysDiff>180 && daysDiff<=270)
							{
								offset="281";
								nextFupDt="show";
							}
					}
				if(nextFupDt!=null)
					{
						if(!"hide".equalsIgnoreCase(nextFupDt))
							nxtFollowUpDt=followUpService.getNxtFollowUpDt(lStrCaseId,offset);
					}
				request.setAttribute("nxtFollowUpDt",nxtFollowUpDt);
				request.setAttribute("nextFupDt",nextFupDt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("investigationsList", investigationsList);
			request.setAttribute("drugsList", drugsList);
			return mapping.findForward("followUpInit");
		}
		/*
		 * Added by Srikalyan to Open Cochlear 
		 * FollowUp Initiation for Initial Mapping and 
		 * Audio Verbal Theraphies 
		 */
		if("cochFollowUpinit".equalsIgnoreCase(lStrFlagStatus))
			{
				int cochlearFollowUpCount=0;	
				String caseId=request.getParameter("caseId");
				request.setAttribute("caseId",caseId);
				
				com.ahct.model.EhfCase ehfCase=followUpService.getCaseDtls(caseId);
				
				if(ehfCase.getCsSurgUpdDt()!=null)
					{
						long milliSecsPerDay=24*60*60*1000;
						long daysDiff=0l,diff=0l;
						Date dateTemp=new Date();
						diff=dateTemp.getTime()-ehfCase.getCsSurgUpdDt().getTime();
						request.setAttribute( "surgUpdDtforCoch",new SimpleDateFormat("dd/MM/yyyy").format(ehfCase.getCsSurgUpdDt()));
						
						daysDiff=Math.round(diff/(milliSecsPerDay));
						
						if(daysDiff>30)
							{
								/*if(daysDiff > 30 && daysDiff < 60)
									cochlearFollowUpCount=0;*/
								if(daysDiff > 30 && daysDiff < 90)
									cochlearFollowUpCount=1;
								/*else if(daysDiff > 60 && daysDiff < 90)
									cochlearFollowUpCount=1;*/
								else if(daysDiff > 90 && daysDiff < 180)
									cochlearFollowUpCount=2;
								else if(daysDiff > 180 && daysDiff < 270)
									cochlearFollowUpCount=3;
								else if(daysDiff > 270 && daysDiff < 360)
									cochlearFollowUpCount=4;
								else if(daysDiff > 360)
									cochlearFollowUpCount=5;
								
								request.setAttribute("cochlearCount",cochlearFollowUpCount);
							}
						
						request.setAttribute("surgUpdDt",ehfCase.getCsSurgUpdDt());
						
					}
				String cochlearCount=Integer.toString(cochlearFollowUpCount);
				if(cochlearFollowUpCount==0)
					{
						if(session.getAttribute("cochlearCount")!=null)
							cochlearCount=Integer.toString((Integer)session.getAttribute("cochlearCount"));
					}
				
				
				if(cochlearCount!=null && !"".equalsIgnoreCase(cochlearCount)) 
					{	
						int cochlearCountNum=Integer.parseInt(cochlearCount);
						if(cochlearCountNum >= 0 && cochlearCountNum <6)
							{
								List<FollowUpVO> followUpVOLst=new ArrayList<FollowUpVO>();
								followUpVOLst=followUpService.getCochlearDetails(caseId,cochlearCountNum);
								if(followUpVOLst!=null)
									{
										if(followUpVOLst.size()>0)
											{
												if(followUpVOLst.get(0)!=null)
													{
														FollowUpVO voLoc=followUpVOLst.get(0);
														if(voLoc.getPostOP()!=null)
															followUpForm.setPostOP(voLoc.getPostOP());
														if(voLoc.getPostOPRemarks()!=null)
															followUpForm.setPostOPRemarks(voLoc.getPostOPRemarks());
														if(voLoc.getAudiologist()!=null)
															followUpForm.setAudiologist(voLoc.getAudiologist());
														if(voLoc.getWoundSight()!=null)
															followUpForm.setWoundSight(voLoc.getWoundSight());
														if(voLoc.getSwitchOnDate()!=null)
															followUpForm.setSwitchOnDate(voLoc.getSwitchOnDate());
														
														request.setAttribute("switchOnDt", voLoc.getSwitchOnDate());
													}
												followUpForm.setFollowUpCochList(followUpVOLst);
												request.setAttribute("disableFields","Y");
												request.setAttribute("toStartFUS",followUpVOLst.size());
												request.setAttribute("missedFUS",cochlearCountNum-followUpVOLst.size());
												if(followUpVOLst.size()>cochlearCountNum)
													{
														request.setAttribute("buttonsDiv","hide");
													}
												else
													request.setAttribute("buttonsDiv","show");
												
												if(followUpVOLst.size()>1)
													{
														FollowUpVO voDates=followUpService.getPrevDateDtls(followUpVOLst.size()-1,caseId);
														if(voDates!=null)
															{	
																if(voDates.getFromDate()!=null)
																	request.setAttribute("prevFromDt" ,voDates.getFromDate() );
																if(voDates.getToDate()!=null)
																	request.setAttribute("prevToDt" ,voDates.getToDate() );
																if(voDates.getReviewDate()!=null)
																	request.setAttribute("prevReviewDt" ,voDates.getReviewDate() );
															}
													}
											}
										else
											{
												request.setAttribute("toStartFUS","0");
												request.setAttribute("buttonsDiv","show");
												request.setAttribute("missedFUS",cochlearCountNum);
											}
									}
								else
									{
										followUpForm.setMsg(config.getString("Cochlear_Fetch_Failed"));
										request.setAttribute("returnMsg",config.getString("Cochlear_Fetch_Failed"));
										request.setAttribute("close","Y");
									}
							}
						/*if(cochlearCountNum==0)
							{
								List<FollowUpVO> followUpVOLst=new ArrayList<FollowUpVO>();
								followUpVOLst=followUpService.getCochlearDetails(caseId,cochlearCountNum);
								if(followUpVOLst!=null)
									{
										if(followUpVOLst.size()>0)
											{
												if(followUpVOLst.get(0)!=null)
													{
														FollowUpVO voLoc=followUpVOLst.get(0);
														if(voLoc.getPostOP()!=null)
															followUpForm.setPostOP(voLoc.getPostOP());
														if(voLoc.getPostOPRemarks()!=null)
															followUpForm.setPostOPRemarks(voLoc.getPostOPRemarks());
														if(voLoc.getAudiologist()!=null)
															followUpForm.setAudiologist(voLoc.getAudiologist());
														if(voLoc.getWoundSight()!=null)
															followUpForm.setWoundSight(voLoc.getWoundSight());
														if(voLoc.getSwitchOnDate()!=null)
															followUpForm.setSwitchOnDate(voLoc.getSwitchOnDate());
													}
												followUpForm.setFollowUpCochList(followUpVOLst);
												request.setAttribute("disableFields","Y");
												request.setAttribute("toStartFUS",followUpVOLst.size());
												request.setAttribute("missedFUS",cochlearCountNum-followUpVOLst.size());
												if(followUpVOLst.size()>cochlearCountNum)
													{
														request.setAttribute("buttonsDiv","hide");
													}
												else
													request.setAttribute("buttonsDiv","show");
											}
										else
											request.setAttribute("buttonsDiv","show");
									}	
								else
									request.setAttribute("buttonsDiv","show");
							}*/
					}
				request.setAttribute("cochlearCount",cochlearCount);
				return mapping.findForward("cochFollowUpinit");
			}
		/*
		 * Added by Srikalyan to Initiate
		 * Cochlear FollowUps
		 */
		if("initiateCochlearFollowUp".equalsIgnoreCase(lStrFlagStatus))
			{
				 caseStartTime = sds.format(new Date().getTime());
				String cochlearCount=request.getParameter("cochlearCount");
				String caseId=request.getParameter("caseId");
				String toStartFUS=request.getParameter("toStartFUS");
				String currentValue=request.getParameter("currentValue");
				if(currentValue==null ||currentValue.equalsIgnoreCase(""))
					{
						followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
						request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
						request.setAttribute("close","Y");
						return mapping.findForward("cochFollowUpinit");
					}
				int toStartFUSNum=0;
				int currentValueNum=0;
				int testValue=0;
				currentValueNum=Integer.parseInt(currentValue);
				String prevFollowUp="N";
				request.setAttribute("caseId",caseId);
				FollowUpVO followUpVOFinal =new FollowUpVO();
				if(cochlearCount!=null && !"".equalsIgnoreCase(cochlearCount))
					{
						int cochlearFollowUpCount=Integer.parseInt(cochlearCount);
						List<FollowUpVO> followUpVOLstTemp=new ArrayList<FollowUpVO>();
						followUpVOLstTemp=followUpService.getCochlearDetails(caseId,cochlearFollowUpCount);
						if(followUpVOLstTemp!=null)
							{
								if(followUpVOLstTemp.size()>0)
									testValue=followUpVOLstTemp.size();
								else if(!(cochlearFollowUpCount==0 || (Integer.parseInt(toStartFUS)==0 && currentValueNum==0)))
									{
										followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
										request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
										request.setAttribute("close","Y");
										return mapping.findForward("cochFollowUpinit");
									}
							}
						else 
							{
								followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
								request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
								request.setAttribute("close","Y");
								return mapping.findForward("cochFollowUpinit");
							}
						
						if(cochlearFollowUpCount==0 || (Integer.parseInt(toStartFUS)==0 && currentValueNum==0))
							{
								String postOPPeriod=followUpForm.getPostOP();
								String postOPRmrks=followUpForm.getPostOPRemarks();
								String woundSight=followUpForm.getWoundSight();
								String dateOfSwitchOn=followUpForm.getSwitchOnDate();
								String audiologist=followUpForm.getAudiologist();
								
								FollowUpVO followUpVO=new FollowUpVO();
								followUpVO.setCaseId(caseId);
								followUpVO.setPostOP(postOPPeriod);
								followUpVO.setPostOPRemarks(postOPRmrks);
								followUpVO.setWoundSight(woundSight);
								followUpVO.setSwitchOnDate(dateOfSwitchOn);
								followUpVO.setAudiologist(audiologist);
								followUpVO.setCochlearCount(cochlearFollowUpCount);
								followUpVO.setCRTUSR(lStrUserId);
								if(Integer.parseInt(toStartFUS)==0)
									followUpVO.setInitialMapping("Y");
								
								followUpVOFinal =followUpService.saveCochlearFollowUpDetails(followUpVO);
								if(followUpVOFinal.getCochFollowUpId() != null && !"".equalsIgnoreCase(followUpVOFinal.getCochFollowUpId()))
								{
									String caseEndTime = sds.format(new Date().getTime());
									String actionDone = commonService.getActionDoneName(followUpVOFinal.getCochFollowUpId(),"ehfCochlrFlp");
									loggingService.logTime(actionDone, followUpVOFinal.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
								}
							}
						else if(cochlearFollowUpCount==1 || cochlearFollowUpCount==2 || 
								cochlearFollowUpCount==3 || cochlearFollowUpCount==4 || cochlearFollowUpCount==5 )
							{
							
								if(testValue == 0 || !(currentValueNum==testValue && testValue==Integer.parseInt(toStartFUS)))
									{
										followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
										request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
										request.setAttribute("close","Y");
										return mapping.findForward("cochFollowUpinit");
									}
							
								followUpVOFinal =new FollowUpVO();
								FollowUpVO followUpVO=new FollowUpVO();
								if(toStartFUS!=null)
									{
										toStartFUSNum=Integer.parseInt(toStartFUS);
										if(cochlearFollowUpCount>toStartFUSNum)
											{
												prevFollowUp="Y";
												String[] array=new String[5];
												
												if(followUpForm.getFromDatePrev(toStartFUSNum)!=null)
													{
														array[0]=followUpForm.getFromDatePrev(toStartFUSNum);
														followUpVO.setFromDatePrev(array);
													}
												else
													prevFollowUp="N";
												
												if(followUpForm.getToDatePrev(toStartFUSNum)!=null)
													{
														array=new String[5];
														array[0]=followUpForm.getToDatePrev(toStartFUSNum);
														followUpVO.setToDatePrev(array);
													}
												else
													prevFollowUp="N";
												
												if(followUpForm.getReviewDatePrev(toStartFUSNum)!=null)
													{
														array=new String[5];
														array[0]=followUpForm.getReviewDatePrev(toStartFUSNum);
														followUpVO.setReviewDatePrev(array);
													}
												else
													prevFollowUp="N";
												if(followUpForm.getChildProgressRemarksPrev(toStartFUSNum)!=null)
													{
														array=new String[5];
														array[0]=followUpForm.getChildProgressRemarksPrev(toStartFUSNum);
														followUpVO.setChildProgressRemarksPrev(array);
													}
												else
													prevFollowUp="N";
												
												if(followUpForm.getAudiologistNamePrev(toStartFUSNum)!=null)
													{
														array=new String[5];
														array[0]=followUpForm.getAudiologistNamePrev(toStartFUSNum);
														followUpVO.setAudiologistNamePrev(array);
													}
												else
													prevFollowUp="N";
												
												if(prevFollowUp.equalsIgnoreCase("N"))
													{
														followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
														request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
														request.setAttribute("close","Y");
														return mapping.findForward("cochFollowUpinit");
													}	
												
												
												followUpVO.setToStartFUS(Integer.parseInt(toStartFUS));
												followUpVO.setCurrentNum(currentValueNum);
												followUpVO.setPrevFollowUp(prevFollowUp);
												
												/*String[] array=new String[5];
												int j=0;
												for(int i=0 ;i<5;i++)
													{
														if(followUpForm.getFromDatePrev(i)!=null)
															{
																array[j]=followUpForm.getFromDatePrev(i);
																j++;
															}
													}
												followUpVO.setFromDatePrev(array);
												array=new String[5];
												j=0;
												for(int i=0 ;i<5;i++)
													{
														if(followUpForm.getToDatePrev(i)!=null)
															{
																array[j]=followUpForm.getToDatePrev(i);
																j++;
															}
													}
												followUpVO.setToDatePrev(array);
												array=new String[5];
												j=0;
												for(int i=0 ;i<5;i++)
													{
														if(followUpForm.getReviewDatePrev(i)!=null)
															{
																array[j]=followUpForm.getReviewDatePrev(i);
																j++;
															}
													}
												followUpVO.setReviewDatePrev(array);
												array=new String[5];
												j=0;
												for(int i=0 ;i<5;i++)
													{
														if(followUpForm.getChildProgressRemarksPrev(i)!=null)
															{
																array[j]=followUpForm.getChildProgressRemarksPrev(i);
																j++;
															}
													}
												followUpVO.setChildProgressRemarksPrev(array);
												array=new String[5];
												j=0;
												for(int i=0 ;i<5;i++)
													{
														if(followUpForm.getAudiologistNamePrev(i)!=null)
															{
																array[j]=followUpForm.getAudiologistNamePrev(i);
																j++;
															}
													}
												followUpVO.setAudiologistNamePrev(array);
												followUpVO.setToStartFUS(Integer.parseInt(toStartFUS));
												followUpVO.setPrevFollowUp(prevFollowUp);*/
											}
										else if(cochlearFollowUpCount==toStartFUSNum)
											{
												String fromDate=followUpForm.getFromDate();
												String toDate=followUpForm.getToDate();
												String progress=followUpForm.getChildProgressRemarks();
												String reviewDate=followUpForm.getReviewDate();
												String audiologistName=followUpForm.getAudiologistName();
												
												followUpForm.setFromDate(null);
												followUpForm.setToDate(null);
												followUpForm.setChildProgressRemarks(null);
												followUpForm.setReviewDate(null);
												followUpForm.setAudiologistName(null);
												
												followUpVO.setFromDate(fromDate);
												followUpVO.setToDate(toDate);
												followUpVO.setChildProgressRemarks(progress);
												followUpVO.setReviewDate(reviewDate);
												followUpVO.setAudiologistName(audiologistName);
											}
							
										
										followUpVO.setCochlearCount(cochlearFollowUpCount);
										followUpVO.setCRTUSR(lStrUserId);
										followUpVO.setCaseId(caseId);
										
										followUpVOFinal =followUpService.saveCochlearFollowUpDetails(followUpVO);
										String caseEndTime = sds.format(new Date().getTime());
										String actionDone = commonService.getActionDoneName(followUpVOFinal.getCochFollowUpId(),"ehfCochlrFlp");
										loggingService.logTime(actionDone, followUpVOFinal.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
									}
								else
									{
										followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
										request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
										request.setAttribute("close","Y");
										return mapping.findForward("cochFollowUpinit");
									}
								
							}
						else
							{
								followUpForm.setMsg(config.getString("Cochlear_Condition_Fail"));
								request.setAttribute("returnMsg",config.getString("Cochlear_Condition_Fail"));
								request.setAttribute("close","Y");
								return mapping.findForward("cochFollowUpinit");
							}
						
						if(followUpVOFinal!=null)
							if(followUpVOFinal.getMsg()!=null)
								{
									if(followUpVOFinal.getMsg().equalsIgnoreCase("Success"))
										{
											if(Integer.parseInt(toStartFUS) < Integer.parseInt(cochlearCount))
												request.setAttribute("cochlearCount", cochlearCount);
											else
												request.setAttribute("cochlearCount", Integer.parseInt(cochlearCount)+1);
											
											/*if(cochlearFollowUpCount==0 || Integer.parseInt(toStartFUS)==0)
												request.setAttribute("cochlearCount", 1);*/	
											
											if(followUpVOFinal.getReturnMsg()!=null)
												request.setAttribute("returnMsg", followUpVOFinal.getReturnMsg());
											else
												request.setAttribute("returnMsg", config.getString("Cochlear_Success_Followup"));
											
											cochlearCount=request.getParameter("cochlearCount");
											if(cochlearCount!=null && !"".equalsIgnoreCase(cochlearCount)) 
												{	
													int cochlearCountNum=Integer.parseInt(cochlearCount);
													if(cochlearCountNum > 0 && cochlearCountNum <6)
														{
															List<FollowUpVO> followUpVOLst=new ArrayList<FollowUpVO>();
															followUpVOLst=followUpService.getCochlearDetails(caseId,cochlearCountNum);
															if(followUpVOLst!=null)
																{
																	if(followUpVOLst.size()>0)
																		{
																			if(followUpVOLst.get(0)!=null)
																				{
																					FollowUpVO voLoc=followUpVOLst.get(0);
																					if(voLoc.getPostOP()!=null)
																						followUpForm.setPostOP(voLoc.getPostOP());
																					if(voLoc.getPostOPRemarks()!=null)
																						followUpForm.setPostOPRemarks(voLoc.getPostOPRemarks());
																					if(voLoc.getAudiologist()!=null)
																						followUpForm.setAudiologist(voLoc.getAudiologist());
																					if(voLoc.getWoundSight()!=null)
																						followUpForm.setWoundSight(voLoc.getWoundSight());
																					if(voLoc.getSwitchOnDate()!=null)
																						followUpForm.setSwitchOnDate(voLoc.getSwitchOnDate());
																					
																					request.setAttribute("switchOnDt", voLoc.getSwitchOnDate());
																				}
																			followUpForm.setFollowUpCochList(followUpVOLst);
																			request.setAttribute("disableFields","Y");
																			request.setAttribute("toStartFUS",followUpVOLst.size());
																			request.setAttribute("missedFUS",cochlearCountNum-followUpVOLst.size());
																			if(followUpVOLst.size()>1)
																				{
																					FollowUpVO voDates=followUpService.getPrevDateDtls(followUpVOLst.size()-1,caseId);
																					if(voDates!=null)
																						{	
																							if(voDates.getFromDate()!=null)
																								request.setAttribute("prevFromDt" ,voDates.getFromDate() );
																							if(voDates.getToDate()!=null)
																								request.setAttribute("prevToDt" ,voDates.getToDate() );
																							if(voDates.getReviewDate()!=null)
																								request.setAttribute("prevReviewDt" ,voDates.getReviewDate() );
																						}
																				}
																			if(followUpVOLst.size()>cochlearCountNum)
																				{
																					request.setAttribute("buttonsDiv","hide");
																				}
																			else
																				request.setAttribute("buttonsDiv","show");
																		}
																	else
																		{
																			followUpForm.setMsg(config.getString("Cochlear_Fetch_Failed"));
																			request.setAttribute("returnMsg",config.getString("Cochlear_Fetch_Failed"));
																			request.setAttribute("close","Y");
																		}
																}
															else
																{
																	followUpForm.setMsg(config.getString("Cochlear_Fetch_Failed"));
																	request.setAttribute("returnMsg",config.getString("Cochlear_Fetch_Failed"));
																	request.setAttribute("close","Y");
																}
															com.ahct.model.EhfCase ehfCase=followUpService.getCaseDtls(caseId);
															
															request.setAttribute( "surgUpdDtforCoch",new SimpleDateFormat("dd/MM/yyyy").format(ehfCase.getCsSurgUpdDt()));
														}
												}
											
											
										}
									else if(followUpVOFinal.getMsg().equalsIgnoreCase("Failed"))
										{
											request.setAttribute("returnMsg", config.getString("Cochlear_Failed_Followup"));
											request.setAttribute("close","Y");
										}
									else
										{
											request.setAttribute("returnMsg", config.getString("Cochlear_Cannotinit_Followup"));
											request.setAttribute("close","Y");
										}
								}
					}
				else
					{
						followUpForm.setMsg(config.getString("Cochlear_Cannotinit_Followup"));
						request.setAttribute("returnMsg", config.getString("Cochlear_Cannotinit_Followup"));
						request.setAttribute("close","Y");
					}
				return mapping.findForward("cochFollowUpinit");
			}
		
		
		if(lStrFlagStatus!=null & "saveCochlearFlpClaim".equalsIgnoreCase(lStrFlagStatus))
			{
				/*
				 * Start for Supportive Data for Back Button 
				 */
				 caseStartTime = sds.format(new Date().getTime());
				String searchFlpStatus=followUpForm.getSearchFollowUpStatus();
				String searchCaseNo=followUpForm.getSearchCaseNo();
				String searchFlpId=followUpForm.getSearchFollowUpId();
				String searchPatName=followUpForm.getSearchPatName();
				String searchFromDt=followUpForm.getSearchFromDate();
				String searchToDt=followUpForm.getSearchToDate();
				String searchCardNo=followUpForm.getSearchCardNo();
				String searchScheme=followUpForm.getSearchSchemeType();
			
				followUpForm.setSearchFollowUpStatus(searchFlpStatus);
				followUpForm.setSearchCaseNo(searchCaseNo);
				followUpForm.setSearchFollowUpId(searchFlpId);
				followUpForm.setSearchPatName(searchPatName);
				followUpForm.setSearchFromDate(searchFromDt);
				followUpForm.setSearchToDate(searchToDt);
				followUpForm.setSearchCardNo(searchCardNo);
				followUpForm.setSearchSchemeType(searchScheme);
				/*
				 * End for Supportive Data for Back Button 
				 */
				
			
				String followUpId=followUpForm.getFollowUpIdHidden();
				String caseId=followUpForm.getCaseIdHidden();
				String casesApprovalFlag=followUpForm.getCasesForApprovalFlag();
				String submitUserGrp=followUpForm.getLstrUsrGrp();
				String submitButton=followUpForm.getSubmitButton();
				FollowUpVO followUpVOResult=new FollowUpVO();
				cochlearSubmitUserGrp=submitUserGrp;
				String PendingFlag=request.getParameter("PendingFlag");
				String cochlearCHSentBack=request.getParameter("cochlearCHSentBack");
				request.setAttribute("casesForApproval", casesApprovalFlag);
				
				if(submitUserGrp!=null &&!"".equalsIgnoreCase(submitUserGrp)
						&& casesApprovalFlag!=null && !"".equalsIgnoreCase(casesApprovalFlag))
					{
						FollowUpVO flpCochlearVO=new FollowUpVO();
						flpCochlearVO.setFollowUpId(followUpId);
						flpCochlearVO.setCaseId(caseId);
						flpCochlearVO.setCasesForApprovalFlag(casesApprovalFlag);
						flpCochlearVO.setUserGrp(submitUserGrp);
						flpCochlearVO.setUserId(lStrUserId);
						flpCochlearVO.setSubmitButton(submitButton);
						
						if(PendingFlag!=null && "Y".equalsIgnoreCase(PendingFlag))
							{
								if((config.getString("AllCochlearWFGrps")).contains("~"+submitUserGrp+"~") )
									{
										if(submitUserGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))
											{
												String claimDyEoAmt=followUpForm.getClaimDyEoAmt();
												String dyeoRemark=followUpForm.getDyeoRemark();
												
												if(claimDyEoAmt!=null && !"".equalsIgnoreCase(claimDyEoAmt)
														&& dyeoRemark!=null && !"".equalsIgnoreCase(dyeoRemark))
													{
														flpCochlearVO.setPendingFlag(PendingFlag);
														flpCochlearVO.setClaimDyEoAmt(Double.parseDouble(claimDyEoAmt));
														flpCochlearVO.setDyeoRemark(dyeoRemark);
														followUpVOResult=followUpService.saveCochlearFlpClaimSendBck(flpCochlearVO);
														if(followUpVOResult!=null)
															{
																if(followUpVOResult.getMsg()!=null )
																	cochlearMsg=followUpVOResult.getMsg();
																if(followUpVOResult.getSmsMsg()!=null )
																	cochlearSms=followUpVOResult.getSmsMsg();
															}
													}
												else
													{
														cochlearMsg=config.getString("MandatoryFieldsUnentered");
													}
											}
										else
											{
												String sendBackRmrks=followUpForm.getSendBackRmrks();
												if(sendBackRmrks!=null && !"".equalsIgnoreCase(sendBackRmrks))
													{
														flpCochlearVO.setPendingFlag(PendingFlag);
														flpCochlearVO.setSendBackRmrks(sendBackRmrks);
														followUpVOResult=followUpService.saveCochlearFlpClaimSendBck(flpCochlearVO);	
														if(followUpVOResult!=null)
															{
																if(followUpVOResult.getMsg()!=null )
																	cochlearMsg=followUpVOResult.getMsg();
															}	
													}
												else
													{
														cochlearMsg=config.getString("MandatoryFieldsUnentered");
													}
											}
									}
							}
						else if(PendingFlag==null || (PendingFlag!=null && !"Y".equalsIgnoreCase(PendingFlag)))
							{
								if(submitUserGrp.equalsIgnoreCase(config.getString("MEDCO_GRP")))
									{
										String medcoClaimAmount=followUpForm.getClaimNwhAmt();
										String medcoRemarks=followUpForm.getMedcoRemarks();
										if(medcoClaimAmount!=null && !"".equalsIgnoreCase(medcoClaimAmount) 
												&& medcoRemarks!=null && !"".equalsIgnoreCase(medcoRemarks))
											{
												flpCochlearVO.setClaimNwhAmt(medcoClaimAmount);
												flpCochlearVO.setMedcoRemarks(medcoRemarks);
												followUpVOResult=followUpService.saveCochlearFlpClaim(flpCochlearVO);
												String caseEndTime = sds.format(new Date().getTime());
												String actionDone = commonService.getActionDoneName(flpCochlearVO.getCochFollowUpId(),"ehfCochlrFlp");
												loggingService.logTime(actionDone, flpCochlearVO.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
											
												if(followUpVOResult!=null)
													{
														if(followUpVOResult.getMsg()!=null )
															cochlearMsg=followUpVOResult.getMsg();
														if(followUpVOResult.getSmsMsg()!=null )
															cochlearSms=followUpVOResult.getSmsMsg();
													}
											}
										else
											{
												cochlearMsg=config.getString("MandatoryFieldsUnentered");
											}
									}
								else if(submitUserGrp.equalsIgnoreCase(config.getString("MITHRA_GRP")))
									{
										String mithraRemarks=followUpForm.getNamRemarks();
										if(mithraRemarks!=null && !"".equalsIgnoreCase(mithraRemarks))
											{
												flpCochlearVO.setNamRemarks(mithraRemarks);
												followUpVOResult=followUpService.saveCochlearFlpClaim(flpCochlearVO);
												String caseEndTime = sds.format(new Date().getTime());
												String actionDone = commonService.getActionDoneName(flpCochlearVO.getCochFollowUpId(),"ehfCochlrFlp");
												loggingService.logTime(actionDone, flpCochlearVO.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
											
												if(followUpVOResult!=null)
													{
														if(followUpVOResult.getMsg()!=null )
															cochlearMsg=followUpVOResult.getMsg();
														
														cochlearSms=null;
													}
											}
										else
											{
												cochlearMsg=config.getString("MandatoryFieldsUnentered");
											}
									}
								else if(submitUserGrp.equalsIgnoreCase(config.getString("COCHCOMT_GRP")))
									{
										String claimCocCmtAmt=followUpForm.getClaimCocCmtAmt();
										String coccmtRemark=followUpForm.getCoccmtRemark();
										if(submitButton!=null)
											{
												if(submitButton.equalsIgnoreCase(config.getString("EHF.PendButton")) ||
														submitButton.equalsIgnoreCase(config.getString("EHF.RedPendButton")) ||
														submitButton.equalsIgnoreCase(config.getString("EHF.RedRejButton"))  ||
														submitButton.equalsIgnoreCase(config.getString("EHF.RejButton")) )
													{
														if(claimCocCmtAmt==null || "".equalsIgnoreCase(claimCocCmtAmt))
															claimCocCmtAmt="0";
													}
											}
											
										if(claimCocCmtAmt!=null && !"".equalsIgnoreCase(claimCocCmtAmt)
												&& coccmtRemark!=null && !"".equalsIgnoreCase(coccmtRemark))
											{
												flpCochlearVO.setClaimCocCmtAmt(Double.parseDouble(claimCocCmtAmt));
												flpCochlearVO.setCoccmtRemark(coccmtRemark);
												followUpVOResult=followUpService.saveCochlearFlpClaim(flpCochlearVO);
												String caseEndTime = sds.format(new Date().getTime());
												String actionDone = commonService.getActionDoneName(flpCochlearVO.getCochFollowUpId(),"ehfCochlrFlp");
												loggingService.logTime(actionDone, flpCochlearVO.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
											
												if(followUpVOResult!=null)
													{
														if(followUpVOResult.getMsg()!=null )
															cochlearMsg=followUpVOResult.getMsg();
														
														cochlearSms=null;
													}
											}
										else
											{
												cochlearMsg=config.getString("MandatoryFieldsUnentered");
											}
									}
								else if(submitUserGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))
									{
										String claimDyEoAmt=followUpForm.getClaimDyEoAmt();
										String dyeoRemark=followUpForm.getDyeoRemark();
										
										if(submitButton!=null)
											{
												if(submitButton.equalsIgnoreCase(config.getString("EHF.PendButton")) ||
														submitButton.equalsIgnoreCase(config.getString("EHF.RedPendButton"))||
														submitButton.equalsIgnoreCase(config.getString("EHF.RedRejButton")) ||
														submitButton.equalsIgnoreCase(config.getString("EHF.RejButton")) 
														)
													{
														if(claimDyEoAmt==null || "".equalsIgnoreCase(claimDyEoAmt))
															claimDyEoAmt="0";
													}
											}
										
										if(claimDyEoAmt!=null && !"".equalsIgnoreCase(claimDyEoAmt)
												&& dyeoRemark!=null && !"".equalsIgnoreCase(dyeoRemark))
											{
												flpCochlearVO.setClaimDyEoAmt(Double.parseDouble(claimDyEoAmt));
												flpCochlearVO.setDyeoRemark(dyeoRemark);
												followUpVOResult=followUpService.saveCochlearFlpClaim(flpCochlearVO);
												String caseEndTime = sds.format(new Date().getTime());
												String actionDone = commonService.getActionDoneName(flpCochlearVO.getCochFollowUpId(),"ehfCochlrFlp");
												loggingService.logTime(actionDone, flpCochlearVO.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
											
												if(followUpVOResult!=null)
													{
														if(followUpVOResult.getMsg()!=null )
															cochlearMsg=followUpVOResult.getMsg();
														if(followUpVOResult.getSmsMsg()!=null )
															cochlearSms=followUpVOResult.getSmsMsg();
													}
											}
										else
											{
												cochlearMsg=config.getString("MandatoryFieldsUnentered");
											}
										
									}
								else if(submitUserGrp.equalsIgnoreCase(config.getString("ACO_GRP")))
									{
										String acoRemarks=followUpForm.getAcoRemark();
										if(acoRemarks!=null && !"".equalsIgnoreCase(acoRemarks))
											{
												flpCochlearVO.setAcoRemark(acoRemarks);
												followUpVOResult=followUpService.saveCochlearFlpClaim(flpCochlearVO);
												String caseEndTime = sds.format(new Date().getTime());
												String actionDone = commonService.getActionDoneName(flpCochlearVO.getCochFollowUpId(),"ehfCochlrFlp");
												loggingService.logTime(actionDone, flpCochlearVO.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
											
												if(followUpVOResult!=null)
													{
														if(followUpVOResult.getMsg()!=null )
															cochlearMsg=followUpVOResult.getMsg();
														if(followUpVOResult.getSmsMsg()!=null )
															cochlearSms=followUpVOResult.getSmsMsg();
														
													}
											}
										else
											{
												cochlearMsg=config.getString("MandatoryFieldsUnentered");
											}
									}
								else if(submitUserGrp.equalsIgnoreCase(config.getString("Payments_GRP")))
									{
										String ceoSendBkRem=followUpForm.getCeoRemark();
										if(ceoSendBkRem!=null && !"".equalsIgnoreCase(ceoSendBkRem))
											{
												FollowUpVO flpTemp=new FollowUpVO();
												flpTemp=followUpService.getCaseStatus(followUpId);
												if(flpTemp!=null)
													if(flpTemp.getFollowUpStatus()!=null)
														followUpForm.setFollowUpStatus(flpTemp.getFollowUpStatus());
												
												flpCochlearVO.setCeoRemark(ceoSendBkRem);
												followUpVOResult=followUpService.saveCochlearFlpClaim(flpCochlearVO);
												String caseEndTime = sds.format(new Date().getTime());
												String actionDone = commonService.getActionDoneName(flpCochlearVO.getCochFollowUpId(),"ehfCochlrFlp");
												loggingService.logTime(actionDone, flpCochlearVO.getCochFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
											
												if(followUpVOResult!=null)
													{
														if(followUpVOResult.getMsg()!=null )
															cochlearMsg=followUpVOResult.getMsg();
														request.setAttribute("CEOSentBackStatus", flpTemp.getFollowUpStatus());
														
														request.setAttribute("cochlearMsg", cochlearMsg);
														
														return mapping.findForward("cochlearFlpClaim");
													}
											}
									}
							}
					}
				else
					{
						followUpForm.setMsg(config.getString("NoSubmitAuthority"));
					}
				
				if(followUpVOResult!=null)
					{
						if(followUpVOResult.getStatusId()!=null && followUpVOResult.getSendSms()!=null && followUpId!=null
								 && cochlearSms!=null && followUpVOResult.getPatientId()!=null && cochlearMsg!=null)
							{
								if("Success".equalsIgnoreCase(followUpVOResult.getStatusId()) &&
										"true".equalsIgnoreCase(followUpVOResult.getSendSms()))
									{
										try {
												EhfmSeq ehfmSeq=null;
												String smsNextVal=ClaimsConstants.EMPTY;
												PatientVO patientVO=new PatientVO();
												Date date = new Date();
												Date crtDt;			
												crtDt = sdf.parse((sdf.format(date)));
												
												ehfmSeq=commonService.getSeqNextVal("PATIENT_SMS_SNO");
									            smsNextVal = String.valueOf(ehfmSeq.getSeqNextVal());
									            int smsSeqNextVal=Integer.parseInt(smsNextVal);
									            if(followUpVOResult.getPatientId()!=null||!"".equalsIgnoreCase(followUpVOResult.getPatientId()))
									            	patientVO = followUpService.getPatientDtls(followUpVOResult.getPatientId()); 
									            
									            if(ClaimsConstants.TRUE.equalsIgnoreCase(config.getString("SmsRequired")))
										            {
										            	String lStrResultMsg=null;
										            	PatientSmsVO patientSmsVO=new PatientSmsVO();
										            	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
										            	patientSmsVO.setPhoneNo(patientVO.getContactNo());
										            	patientSmsVO.setSmsText("Dear "+patientVO.getFirstName().trim()+" ,"+cochlearSms);
										            	patientSmsVO.setEmpCode(patientVO.getEmpCode());
										            	patientSmsVO.setEmpName(patientVO.getFirstName());
										            	patientSmsVO.setCrtUsr(lStrUserId);
										            	patientSmsVO.setCrtDt(crtDt);
										            	patientSmsVO.setSmsAction(cochlearMsg);
										            	patientSmsVO.setPatientId(followUpVOResult.getPatientId());
										            	lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
										            	if(lStrResultMsg!=null)
											            	{
												            	ehfmSeq.setSeqNextVal(Long.valueOf(smsSeqNextVal + 1));
																commonService.updateSequenceVal(ehfmSeq);
											            	}
										            }
									            
									           if (config.getBoolean("EmailRequired")) 
										            {
									        	   		SimpleDateFormat sdsf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
										            	String mailId=null;
										            	if(patientVO.geteMailId()!=null && !patientVO.geteMailId().equals(ClaimsConstants.EMPTY))
										            	{
										            	mailId=patientVO.geteMailId();
										            	String[] emailToArray = {mailId};
										            	EmailVO emailVO = new EmailVO();
														emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
														emailVO.setTemplateName(config.getString("EhfCochlearFPTemplateName"));
														emailVO.setFromEmail(config.getString("emailFrom"));
														emailVO.setToEmail(emailToArray);
														emailVO.setSubject(config.getString("cochFPMailSubject"));
														Map<String, String> model = new HashMap<String, String>();
														model.put("patientName", patientVO.getFirstName().trim());
														model.put("caseNo", followUpId);
														model.put("status", cochlearMsg);
														if(crtDt!=null)
															model.put("statusDate",sdsf.format(crtDt).toString());
														commonService.generateMail(emailVO, model);
										            	}
										   			}	
											}
										catch(Exception e)
											{
												e.printStackTrace();
											}
									}
							}
					}
				
				cochlearFollowUpId=followUpId;
				cochlearCaseId=caseId;
				cochlearCasesApprovalFlag=casesApprovalFlag;
				fromClaimSave="Y";
				
				request.setAttribute("cochlearMsg", cochlearMsg);
				
				lStrFlagStatus="cochlearFPClaim";
			}
		/*
		 * Added by Srikalyan to Open Cochlear
		 * Follow Up Claims
		 */
		if(lStrFlagStatus != null && "cochlearFPClaim".equalsIgnoreCase(lStrFlagStatus))
			{
				List<FollowUpVO> flpStatusLst=new ArrayList<FollowUpVO>();
				flpStatusLst=followUpService.getFlpStatusLst();
				request.setAttribute("flpStatusLst", flpStatusLst);
			
				String casesForApproval=request.getParameter("casesForApproval");
				String pendingFlag=request.getParameter("PendingFlag");
				String csvFlag=request.getParameter("csvFlag");
				
				if(casesForApproval==null || "".equalsIgnoreCase(casesForApproval))
					casesForApproval=followUpForm.getCasesForApprovalFlag();;
					
				request.setAttribute("casesForApproval", casesForApproval);
				request.setAttribute("PendingFlag", pendingFlag);
				
				FollowUpVO fupVO=new FollowUpVO();
				int startIndex=0,maxResults=0,pageId=0;
				if(request.getParameter("startIndex")!=null)
					startIndex=Integer.parseInt(request.getParameter("startIndex"));
				if(request.getParameter("endIndex")!=null)
					maxResults=Integer.parseInt(request.getParameter("endIndex"));
				if(request.getParameter("pageId")!=null)
					pageId=Integer.parseInt(request.getParameter("pageId"));
				
				fupVO.setStrtIndex(startIndex);
				fupVO.setMaxresults(maxResults);
				fupVO.setPageId(pageId);
				
				fupVO.setGrpList(groupsList);
				fupVO.setUserId(lStrUserId);
				fupVO.setUserScheme(lStrUserState);
				
				fupVO.setPendingFlag(pendingFlag);
				fupVO.setCasesForApprovalFlag(casesForApproval);
				
				if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
					{
						fupVO.setFollowUpStatus(followUpForm.getCsvFollowUpStatus());
						fupVO.setCaseNo(followUpForm.getCsvCaseNo());
						fupVO.setFollowUpId(followUpForm.getCsvFollowUpId());
						fupVO.setCardNo(followUpForm.getCsvCardNo());
						fupVO.setFromDate(followUpForm.getCsvFromDate());
						fupVO.setToDate(followUpForm.getCsvToDate());
						fupVO.setPatName(followUpForm.getCsvPatName());
						fupVO.setPatientScheme(followUpForm.getCsvSchemeType());
						fupVO.setCsvFlag(csvFlag);
						
						StringBuilder line=followUpService.cochCSVDownload(fupVO);
						
						try
							{
								String lStrDirPath = config.getString("CASESSEARCH.MapPath");
								String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE")+ "CSV";
								File lcsvfile = createFile(lStrDirPath,lStrFileName);
								
								request.setAttribute("File", line.toString().getBytes());    
							    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
							    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
							    response.setCharacterEncoding("UTF-8");
							}
						catch(Exception e )
							{
								e.printStackTrace();
							}
				        return mapping.findForward("openFile");
					}
				
				fupVO.setFollowUpStatus(followUpForm.getSearchFollowUpStatus());
				fupVO.setCaseNo(followUpForm.getSearchCaseNo());
				fupVO.setFollowUpId(followUpForm.getSearchFollowUpId());
				fupVO.setCardNo(followUpForm.getSearchCardNo());
				fupVO.setFromDate(followUpForm.getSearchFromDate());
				fupVO.setToDate(followUpForm.getSearchToDate());
				fupVO.setPatName(followUpForm.getSearchPatName());
				fupVO.setPatientScheme(followUpForm.getSearchSchemeType());

				followUpForm.setCsvFollowUpStatus(followUpForm.getSearchFollowUpStatus());
				followUpForm.setCsvCaseNo(followUpForm.getSearchCaseNo());
				followUpForm.setCsvFollowUpId(followUpForm.getSearchFollowUpId());
				followUpForm.setCsvCardNo(followUpForm.getSearchCardNo());
				followUpForm.setCsvFromDate(followUpForm.getSearchFromDate());
				followUpForm.setCsvToDate(followUpForm.getSearchToDate());
				followUpForm.setCsvPatName(followUpForm.getSearchPatName());
				followUpForm.setCsvSchemeType(followUpForm.getSearchSchemeType());
				
				
				FollowUpVO followUpFinal=followUpService.getCochlearClaimCases(fupVO);
				if(followUpFinal!=null)
					{
						if(followUpFinal.getClaimList()!=null)
							{
								if(followUpFinal.getClaimList().size()>0)
									{
										followUpForm.setFollowUpCochClaimList(followUpFinal.getClaimList());
										request.setAttribute("totalPages",followUpFinal.getTotalPages());
										request.setAttribute("totalRecords",followUpFinal.getTotalRecords());
										request.setAttribute("pageId",followUpFinal.getPageId());
										request.setAttribute("endIndex",followUpFinal.getMaxresults());
										request.setAttribute("startIndex",followUpFinal.getStrtIndex());
										request.setAttribute("endResults",followUpFinal.getStrtIndex()+followUpFinal.getClaimList().size());
									}
							}
						
					}
				
				return mapping.findForward("cochfollowUpViewClaim");
				
			}
		
		
		if(lStrFlagStatus != null && "openCochlearFPClaim".equalsIgnoreCase(lStrFlagStatus))
			{
				/*
				 * Start for Supportive Data for Back Button 
				 */
				String searchFlpStatus=followUpForm.getSearchFollowUpStatus();
				String searchCaseNo=followUpForm.getSearchCaseNo();
				String searchFlpId=followUpForm.getSearchFollowUpId();
				String searchPatName=followUpForm.getSearchPatName();
				String searchFromDt=followUpForm.getSearchFromDate();
				String searchToDt=followUpForm.getSearchToDate();
				String searchCardNo=followUpForm.getSearchCardNo();
				String searchScheme=followUpForm.getSearchSchemeType();
				
				followUpForm.setSearchFollowUpStatus(searchFlpStatus);
				followUpForm.setSearchCaseNo(searchCaseNo);
				followUpForm.setSearchFollowUpId(searchFlpId);
				followUpForm.setSearchPatName(searchPatName);
				followUpForm.setSearchFromDate(searchFromDt);
				followUpForm.setSearchToDate(searchToDt);
				followUpForm.setSearchCardNo(searchCardNo);
				followUpForm.setSearchSchemeType(searchScheme);
				/*
				 * End for Supportive Data for Back Button
				 */
				
				String followUpId=request.getParameter("followUpId");
				String caseId=request.getParameter("caseId");
				String casesForApproval=request.getParameter("casesForApproval");
				String backBtn=request.getParameter("backBtn");
				String attachBut=request.getParameter("attachBut");
				request.setAttribute("attachBut",attachBut);
				request.setAttribute("backBtn",backBtn);
				String pendingFlag=request.getParameter("PendingFlag");
				request.setAttribute("PendingFlag", pendingFlag);
				String sentBckRemks=null;
				
				String photo=null;
				if(fromClaimSave!=null)
					{
						if(fromClaimSave.equalsIgnoreCase("Y"))
							{
								if(followUpId==null)
									followUpId=cochlearFollowUpId;
								if(caseId==null)
									caseId=cochlearCaseId;
								if(casesForApproval==null)
									casesForApproval=cochlearCasesApprovalFlag;
							}	
					}
				
				String lStrUserGrp=null,statusId=null;
				FollowUpVO followUpVODtls=followUpService.getCochlearFlpCase(caseId,followUpId);
				if(followUpVODtls!=null)
					{
						if(followUpVODtls.getCochlearCountStr()!=null)
							request.setAttribute("cochCount" , followUpVODtls.getCochlearCountStr());
						
						if(cochlearSubmitUserGrp!=null)
							{
								lStrUserGrp=cochlearSubmitUserGrp;
							}
						else
							lStrUserGrp=config.getString("CochlearFlpStatus_"+followUpVODtls.getStatusId());
						
						if(groupsList!=null)
							{
								int count=0;
								for(LabelBean lb:groupsList)
									{
										if(lb.getID()!=null && !"".equalsIgnoreCase(lb.getID()))
											{
												if(lb.getID().equalsIgnoreCase(lStrUserGrp))
													count++;
											}
									}
								if(count==0)
									lStrUserGrp=null;
							}
						else
							lStrUserGrp=null;
						
						request.setAttribute("lStrUserGrpForCochFlp",lStrUserGrp);
						request.setAttribute("followUpDtls",followUpVODtls);
						FollowUpVO flpVO=followUpService.getSendBackDtls(followUpId);
						if(followUpVODtls.getStatusId()!=null && !"".equalsIgnoreCase(followUpVODtls.getStatusId()))
							{
								statusId=followUpVODtls.getStatusId();
								if(followUpVODtls.getMedcoRemarks()!=null && !"".equalsIgnoreCase(followUpVODtls.getMedcoRemarks()))
									{
										/*if(config.getString("CochlearCommitteePending").equalsIgnoreCase(followUpVODtls.getStatusId())||
												config.getString("CochlearDyEoPending").equalsIgnoreCase(followUpVODtls.getStatusId()))
											{
												followUpForm.setClaimNwhAmt("");
												followUpForm.setMedcoRemarks("");
											}
										else
											{*/
												followUpForm.setClaimNwhAmt(followUpVODtls.getClaimNWHAmtStr());
												followUpForm.setMedcoRemarks(followUpVODtls.getMedcoRemarks());		
										
										
										request.setAttribute("nwhCochlearForm", "show");
										request.setAttribute("nwhCochlearDisabled", "true");
									}
									
								if(followUpVODtls.getNamRemarks()!=null  && !"".equalsIgnoreCase(followUpVODtls.getNamRemarks()))
									{
										followUpForm.setNamRemarks(followUpVODtls.getNamRemarks());
										request.setAttribute("namCochlearForm", "show");
										request.setAttribute("namCochlearDisabled", "true");
									}
								
								if(followUpVODtls.getCoccmtRemark()!=null  && !"".equalsIgnoreCase(followUpVODtls.getCoccmtRemark()))
									{
										/*if(config.getString("CochlearCommitteePendingUpdated").equalsIgnoreCase(followUpVODtls.getStatusId()))
											{
												followUpForm.setCoccmtRemark("");
												followUpForm.setClaimCocCmtAmt("");
											}	
										else
											{*/
												followUpForm.setCoccmtRemark(followUpVODtls.getCoccmtRemark());
												followUpForm.setClaimCocCmtAmt((followUpVODtls.getClaimCocCmtAmtStr()));
											
										request.setAttribute("cocCmtCochlearForm", "show");
										request.setAttribute("cocCmtCochlearDisabled", "true");
									}
								if(followUpVODtls.getDyeoRemark()!=null  && !"".equalsIgnoreCase(followUpVODtls.getDyeoRemark()))
									{
										followUpForm.setClaimDyEoAmt(followUpVODtls.getClaimDyEoAmtStr());
										followUpForm.setDyeoRemark(followUpVODtls.getDyeoRemark());	
										request.setAttribute("dyEOCochlearForm", "show");
										request.setAttribute("dyEOCochlearFormDisabled", "true");
									}
								if(followUpVODtls.getAcoRemark()!=null  && !"".equalsIgnoreCase(followUpVODtls.getAcoRemark()))
									{
										followUpForm.setAcoRemark(followUpVODtls.getAcoRemark());	
										request.setAttribute("acoCochlearForm", "show");
										request.setAttribute("acoCochlearFormDisabled", "true");
									}
								if(flpVO!=null)
									{
										sentBckRemks=flpVO.getSendBackRmrks();
										if(flpVO.getCeoRemark()!=null && !"".equalsIgnoreCase(flpVO.getCeoRemark()))
											{
												followUpForm.setCeoRemark(flpVO.getCeoRemark());	
												request.setAttribute("ceoCochlearForm", "show");
												request.setAttribute("ceoCochlearFormDisabled", "true");
											}
										if(flpVO.getSendBackRmrks()!=null && !"".equalsIgnoreCase(flpVO.getSendBackRmrks()))
											{
												followUpForm.setSendBackRmrks(flpVO.getSendBackRmrks());	
												request.setAttribute("cochlearSentBackForm", "show");
												request.setAttribute("cochlearSentBackFormDisabled", "true");
											}
										
									}
								
							}
						
					}
				if(caseId!=null)
					{
						photo=followUpService.getPatientPhoto(caseId);
						if(photo!=null)
							{
								try
									{
										File f=new File(photo);
										FileInputStream fis=new FileInputStream(f);
										DataInputStream dis=new DataInputStream(fis);
										byte bytes[]=new byte[dis.available()];
										fis.read(bytes);
										session.setAttribute("contentType", "image/jpg");
										session.setAttribute("File", bytes);
										fis.close();
										dis.close();
										followUpForm.setPhotoUrl(photo);
									}
								catch(Exception e)
									{
										e.printStackTrace();
									}
								
							}
					}
				List<FollowUpVO> prevFlpDtls=followUpService.getPrevCochlearFlpDtls(caseId,followUpId);
				followUpForm.setPrevFlpDtls(prevFlpDtls);
				
				if(casesForApproval!=null &&
						"Y".equalsIgnoreCase(casesForApproval))
					{
						List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
								statusId, lStrUserGrp, ClaimsConstants.FOLLOW_UP,
								ClaimsConstants.COCHLEAR_FOLLOWUP_CLAIM);
						followUpForm.setButtonList(buttonList);
						
						if(lStrUserGrp!=null && !"".equalsIgnoreCase(lStrUserGrp) && (pendingFlag==null || (pendingFlag !=null && !"Y".equalsIgnoreCase(pendingFlag))))
							{
								if(lStrUserGrp.equalsIgnoreCase(config.getString("MEDCO_GRP")))
									{
										String checkStatus=followUpService.checkInitiatationStatus(caseId,followUpId);
										request.setAttribute("checkStatus", checkStatus);
										request.setAttribute("medcoYN" , "Y" );
										request.setAttribute("nwhCochlearForm", "show");
										request.setAttribute("nwhCochlearDisabled", "false");
										if(statusId.equalsIgnoreCase(config.getString("Cochlear_FollowUp_Done")))
											request.setAttribute("cochRem", "Y");
										else
											request.setAttribute("cochRem", "N");
										followUpForm.setClaimNwhAmt("");
										followUpForm.setMedcoRemarks("");
									}
								else if(lStrUserGrp.equalsIgnoreCase(config.getString("MITHRA_GRP")))
									{
										request.setAttribute("namCochlearForm", "show");
										request.setAttribute("namCochlearDisabled", "false");
										followUpForm.setNamRemarks("");
									}
								else if(lStrUserGrp.equalsIgnoreCase(config.getString("COCHCOMT_GRP")))
									{
										request.setAttribute("cocCmtCochlearForm", "show");
										request.setAttribute("cocCmtCochlearDisabled", "false");
										followUpForm.setCoccmtRemark("");
										followUpForm.setClaimCocCmtAmt("");
									}
								else if(lStrUserGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))
									{
										request.setAttribute("dyEOCochlearForm", "show");
										request.setAttribute("dyEOCochlearFormDisabled", "false");
										followUpForm.setClaimDyEoAmt("");
										followUpForm.setDyeoRemark("");
									}
								else if(lStrUserGrp.equalsIgnoreCase(config.getString("ACO_GRP")))
									{
										request.setAttribute("acoCochlearForm", "show");
										request.setAttribute("acoCochlearFormDisabled", "false");
										followUpForm.setAcoRemark("");
									}
									
							}
						else if(pendingFlag !=null && "Y".equalsIgnoreCase(pendingFlag))
							{
								request.setAttribute("cochlearSentBackForm", "show");
								request.setAttribute("cochlearSentBackFormDisabled", "false");
								followUpForm.setSendBackRmrks("");
							}
						
						
						if(followUpVODtls.getStatusId()!=null && !"".equalsIgnoreCase(followUpVODtls.getStatusId()) && 
								config.getString("CochlearTDSB").equalsIgnoreCase(followUpVODtls.getStatusId()))
							{
								if(lStrUserGrp.equalsIgnoreCase(config.getString("DYEO_GRP")) )
									{
										request.setAttribute("cochlearCHSentBack", "Show");
										request.setAttribute("dyEOCochlearForm", "show");
										request.setAttribute("dyEOCochlearFormDisabled", "false");
										followUpForm.setClaimDyEoAmt("");
										followUpForm.setDyeoRemark("");
										request.setAttribute("cochlearSentBackFormDisabled", "true");
										if(sentBckRemks==null )
											{
												request.setAttribute("cochlearSentBackForm", "dontShow");
												request.setAttribute("cochlearSentBackFormDisabled", "true");
											}
										else if("".equalsIgnoreCase(sentBckRemks))
											{
												request.setAttribute("cochlearSentBackForm", "dontShow");
												request.setAttribute("cochlearSentBackFormDisabled", "true");
											}
										else 
											followUpForm.setSendBackRmrks(sentBckRemks);
									}								
							}
	
					}
				else if(casesForApproval!=null &&
						"CEO".equalsIgnoreCase(casesForApproval))
					{
						if(followUpVODtls.getStatusId()!=null && !"".equalsIgnoreCase(followUpVODtls.getStatusId()))
							{
								if(lStrUserGrp!=null)
									{
										if(config.getString("Cochlear_"+lStrUserGrp+"_Status").contains("~"+followUpVODtls.getStatusId()+"~"))
											{
												followUpForm.setCeoRemark("");	
												request.setAttribute("ceoCochlearForm", "show");
												request.setAttribute("ceoCochlearFormDisabled", "false");
											}		
									}
								
							}
					}
				
				FollowUpVO commonDtls =followUpService.getCommonDtls(caseId,followUpId);
				
				if(commonDtls!=null)
					{
						request.setAttribute("followUpDate",commonDtls.getDateStr());
						request.setAttribute("actualPack",commonDtls.getActualPack());
					}
				List<FollowUpVO> workFlow=followUpService.getCochlearClaimWorkFlow(caseId,followUpId);
				if(workFlow!=null)
					{
						if(workFlow.size()>0)
							{
								followUpForm.setAuditLst(workFlow);
							}
					}
				
				request.setAttribute("followUpId",followUpId);
				request.setAttribute("caseId",caseId);
				request.setAttribute("casesForApproval",casesForApproval);
				
				for(LabelBean labelBean:groupsList)
					{
						if(labelBean.getID() != null && 
								config.getString("Cochlear_Audit_Trust_Users")!=null && 
								config.getString("Cochlear_Audit_Trust_Users").contains("~"+labelBean.getID()+"~") )
						{
							request.setAttribute("viewCochFlpAuditNames", "Y");
							break;	
						}
					}
				
				return mapping.findForward("cochlearFlpClaim");
				
			}
		if ("saveFollowupClaimCh".equalsIgnoreCase(lStrFlagStatus)
				&& lStrFlagStatus != null) {
			
			 caseStartTime = sds.format(new Date().getTime());
			String[] casesSelected=followUpForm.getCaseSelected();
	    	casesSelected=casesSelected[0].split(",");
			
			
			FollowUpVO followUpVO = new FollowUpVO();
			FollowUpVO followUpDtls = new FollowUpVO();

			followUpVO.setRoleId(followUpForm.getRoleId());
			oldRoleId = followUpForm.getRoleId();

			followUpVO.setFollowUpStatus(followUpForm.getFollowUpStatus());
			
			followUpVO.setRoleId(followUpForm.getRoleId());
			
			String caseApprovalFlag = null;
			caseApprovalFlag = request.getParameter("casesForApproval");
			request.setAttribute("casesForApproval", caseApprovalFlag);
			String ActionDone=request.getParameter("actionDone");
			followUpVO.setUserId(lStrEmpId);
			followUpVO.setActionDone(ActionDone);
			//followUpVO.setCaseId(request.getParameter("CaseId"));
			//followUpVO.setCLINICALID(request.getParameter("clinicalId"));
			followUpVO.setFollowUpDt(followUpForm.getFollowUpDt());
			//followUpVO.setFollowUpId(followUpForm.getFollowUpId());
			followUpVO.setClaimNwhAmt(followUpForm.getClaimNwhAmt());
			
			/*added to get followup details saved by FTD*/
			
			if(casesSelected!=null){
		    	for(String followupId:casesSelected)
		    	{
		    	
		    		
		    	if(followupId!=null && !("").equalsIgnoreCase(followupId))
		    	{
		    followUpVO.setFollowUpId(followupId);
			followUpDtls=followUpService.getFollowupDtls(followUpVO);
			
			
			
			if(followUpDtls.getClaimCHPharmBill()!=null && !"".equalsIgnoreCase(followUpDtls.getClaimCHPharmBill()))
				followUpVO.setClaimCHPharmBill(followUpDtls.getClaimCHPharmBill());
			else
				followUpVO.setClaimCHPharmBill("0");
			
			if(followUpDtls.getClaimCHConsulBill()!=null && !"".equalsIgnoreCase(followUpDtls.getClaimCHConsulBill()))
				followUpVO.setClaimCHConsulBill(followUpDtls.getClaimCHConsulBill());
			else
				followUpVO.setClaimCHConsulBill("0");
			
			if(followUpDtls.getClaimCHInvestBill()!=null && !"".equalsIgnoreCase(followUpDtls.getClaimCHInvestBill()))
				followUpVO.setClaimCHInvestBill(followUpDtls.getClaimCHInvestBill());
			else
				followUpVO.setClaimCHInvestBill("0");
			
		if(ActionDone!=null)
		{
			if(ActionDone.equalsIgnoreCase(config.getString("EHF.AppButton")))
				followUpVO.setChRemark("Followup Approved By CH");	
			
			else if(ActionDone.equalsIgnoreCase(config.getString("EHF.PendButton")))
				followUpVO.setChRemark("Followup kept Pending By CH");	
			
			else if(ActionDone.equalsIgnoreCase(config.getString("EHF.RejButton")))
				followUpVO.setChRemark("Followup Rejected By CH");	
			
			else if(ActionDone.equalsIgnoreCase(config.getString("EHF.VerifyButton")))
				followUpVO.setChRemark("Followup Verified By CH");	
			
			
		}
				//followUpVO.setChRemark(followUpForm.getChRemark());			
			
			
			
			followUpVO.setClaimChAmt(followUpDtls.getClaimChAmt());
			
			
			followUpVO = followUpService.saveFollowUpClaim(followUpVO);	
			String caseEndTime = sds.format(new Date().getTime());
			String actionDone = commonService.getActionDoneName(followUpVO.getFollowUpId(),"ehfFollowup");
			loggingService.logTime(actionDone, followUpVO.getFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
		
			

						
						if(!followUpVO.getSmsMsg().equalsIgnoreCase(ClaimsConstants.EMPTY)){
							try {
							EhfmSeq ehfmSeq=null;
							String smsNextVal=ClaimsConstants.EMPTY;
							PatientVO patientVO=new PatientVO();
							Date date = new Date();
							Date crtDt;			
							crtDt = sdf.parse((sdf.format(date)));
							
							ehfmSeq=commonService.getSeqNextVal("PATIENT_SMS_SNO");
				            smsNextVal = String.valueOf(ehfmSeq.getSeqNextVal());
				            int smsSeqNextVal=Integer.parseInt(smsNextVal);
				            if(followUpVO.getPatientId()!=null||!"".equalsIgnoreCase(followUpVO.getPatientId()))
				            	patientVO = followUpService.getPatientDtls(followUpVO.getPatientId()); 
				            
				           /* if(ClaimsConstants.TRUE.equalsIgnoreCase(config.getString("SmsRequired")))
				            {
				            	String lStrResultMsg=null;
				            	PatientSmsVO patientSmsVO=new PatientSmsVO();
				            	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
				            	patientSmsVO.setPhoneNo(patientVO.getContactNo());
				            	patientSmsVO.setSmsText("Dear "+patientVO.getFirstName().trim()+" ,"+followUpVO.getSmsMsg());
				            	patientSmsVO.setEmpCode(patientVO.getEmpCode());
				            	patientSmsVO.setEmpName(patientVO.getFirstName());
				            	patientSmsVO.setCrtUsr(lStrUserId);
				            	patientSmsVO.setCrtDt(crtDt);
				            	patientSmsVO.setSmsAction(followUpVO.getMsg());
				            	patientSmsVO.setPatientId(followUpVO.getPatientId());
				            	lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
				            	if(lStrResultMsg!=null)
				            	{
				            	ehfmSeq.setSeqNextVal(Long.valueOf(smsSeqNextVal + 1));
								commonService.updateSequenceVal(ehfmSeq);
				            	}
				            }*/
				            
				          /* if (config.getBoolean("EmailRequired")) 
				            {
				            	String mailId=null;
				            	if(patientVO.geteMailId()!=null && !patientVO.geteMailId().equals(ClaimsConstants.EMPTY))
				            	{
				            	mailId=patientVO.geteMailId();
				            	String[] emailToArray = {mailId};
				            	EmailVO emailVO = new EmailVO();
								emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
								emailVO.setTemplateName(config.getString("EhfFPTemplateName"));
								emailVO.setFromEmail(config.getString("emailFrom"));
								emailVO.setToEmail(emailToArray);
								emailVO.setSubject(config.getString("fpMailSubject"));
								Map<String, String> model = new HashMap<String, String>();
								model.put("patientName", patientVO.getFirstName().trim());
								model.put("caseNo", followUpVO.getFollowUpId());
								model.put("status", followUpVO.getMsg());
								model.put("statusDate",crtDt.toString());
								commonService.generateMail(emailVO, model);
				            	}
				   			}	*/
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
		    	}}}
						if (!followUpVO.getMsg().equalsIgnoreCase(ClaimsConstants.Failure)) {
							
							
							
							
							
							if(ActionDone.equalsIgnoreCase(config.getString("EHF.AppButton")))
								followUpVO.setMsg("Selected Followup Cases Approved By CH");	
							
							else if(ActionDone.equalsIgnoreCase(config.getString("EHF.PendButton")))
								followUpVO.setMsg("Selected Followup Cases kept Pending By CH");	
							
							else if(ActionDone.equalsIgnoreCase(config.getString("EHF.RejButton")))
								followUpVO.setMsg("Selected Followup Cases Rejected By CH");	
							
							else if(ActionDone.equalsIgnoreCase(config.getString("EHF.VerifyButton")))
								followUpVO.setMsg("Selected Followup Cases Verified By CH");	
							
							
							
							
							
							request.setAttribute("saveMsg", followUpVO.getMsg());
							request.setAttribute("disableAll", ClaimsConstants.YES);
						}
						saveFlag = ClaimsConstants.TRUE;			
						lStrFlagStatus = "caseSearch";
			
		}
		
		
		
		
		
		
		
		
		
		
		
		if ("caseSearch".equalsIgnoreCase(lStrFlagStatus)
				&& lStrFlagStatus != null) {

			String casesForApprovalFlag = request.getParameter("casesForApproval");
			String lStrSchemeType = request.getParameter("schemeType");
			String patientScheme=request.getParameter("patientScheme");
			String refreshFlag=request.getParameter("refreshFlag");
			String pendingFlag=request.getParameter("pendingFlag");
			if (casesForApprovalFlag == null || casesForApprovalFlag.equals(ClaimsConstants.EMPTY))
				casesForApprovalFlag = followUpForm.getCasesForApprovalFlag();
			if (casesForApprovalFlag == null || casesForApprovalFlag.equals(ClaimsConstants.EMPTY))
				casesForApprovalFlag = "N";
			 if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
	 	        lStrSchemeType = lStrUserState;
			 
			String flag = request.getParameter("viewFlag");

			if(followUpForm.getFollowUpId() != null && !followUpForm.getFollowUpId().equals("") && !("Y").equalsIgnoreCase(refreshFlag))
							followUpService.setviewFlag(followUpForm.getFollowUpId(),flag);

			String lStrUserGroup = null;
			lStrStartIndex = bundle.getString("case.StartIndex0");
			FollowUpVO followUpVO = new FollowUpVO();
			followUpVO.setCaseNo(followUpForm.getCaseNo());
			followUpVO.setFollowUpId(followUpForm.getFollowUpId());
			followUpVO.setWapNo(followUpForm.getWapNo());
			followUpVO.setPatName(followUpForm.getPatName());
			followUpVO.setFromDate(followUpForm.getFromDate());
			followUpVO.setToDate(followUpForm.getToDate());
			followUpVO.setSearchFlag(request.getParameter("searchFlag"));
			followUpVO.setUserId(lStrEmpId);
			followUpVO.setRowsPerPage(lStrRowsperpage);
			followUpVO.setStartIndex(lStrStartIndex);
			followUpVO.setShowPage(showPage);
			followUpVO.setGrpList(groupsList);
			followUpVO.setFollowUpStatus(followUpForm.getFollowUpStatus());
			followUpVO.setSchemeType(lStrSchemeType);
			followUpVO.setPatientScheme(patientScheme);
			followUpVO.setPendingFlag(pendingFlag);
			if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
				followUpForm.setShowScheme("show");
				followUpForm.setSchemeType(lStrSchemeType);
			}
			else {
				followUpForm.setShowScheme("hide");
			}

			followUpVO.setCasesForApprovalFlag(casesForApprovalFlag);
			followUpForm.setCasesForApprovalFlag(casesForApprovalFlag);
			/**
			 * set user role
			 */
			for (LabelBean labelBean : groupsList) {
				if (labelBean.getID() != null
						&& (labelBean.getID().equalsIgnoreCase(
								config.getString("preauth_medco_role")) || labelBean
								.getID()
								.equalsIgnoreCase(
										config.getString("preauth_mithra_role")))|| labelBean
										.getID()
										.equalsIgnoreCase(
												config.getString("CH_Grp"))) {
					lStrUserGroup = labelBean.getID();
					break;
				} else
					lStrUserGroup = null;
			}
			// casesSearchVO = casesSearchService.getUserRole(casesSearchVO);
			followUpVO.setUserRole(lStrUserGroup);
			followUpForm.setRoleId(lStrUserGroup);
			
			if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("CH_Grp")) && ("Y").equalsIgnoreCase(casesForApprovalFlag)
			&& lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("AP")) && lStrEmpId.equalsIgnoreCase("USR1887") )		
			
			{
				if(followUpForm.getFollowUpStatus()==null)
					followUpForm.setFollowUpStatus(config.getString("EHF.FollowUP.FTDApprove"));
				followUpVO.setFollowUpStatus(followUpForm.getFollowUpStatus());
				followUpVO.setSearchFlag("Y");
				
				
					// getting list of buttons for specific role and status
					List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
							followUpVO.getFollowUpStatus(), lStrUserGroup, ClaimsConstants.FOLLOW_UP,
							ClaimsConstants.FOLLOWUP_CLAIM);
					followUpForm.setButtonList(buttonList);

					if (buttonList.size() == 0)
						request.setAttribute("disableAll", ClaimsConstants.YES);
					
					request.setAttribute("buttons",buttonList);
					request.setAttribute("lStrUserState",lStrUserState);
				
			}
			// followUpVO = followUpService.getUserRole(followUpVO);
			if (followUpForm.getRowsPerPage() != null
					&& !followUpForm.getRowsPerPage().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
				lStrRowsperpage = followUpForm.getRowsPerPage();
				followUpVO.setRowsPerPage(followUpForm.getRowsPerPage());
				followUpForm.setRowsPerPage(followUpForm.getRowsPerPage());
			}
			if (followUpForm.getShowPage() != null
					&& !followUpForm.getShowPage().equals(ClaimsConstants.EMPTY)) {
				followUpVO.setShowPage(followUpForm.getShowPage());
				followUpForm.setShowPage(followUpForm.getShowPage());
				if (Integer.parseInt(followUpForm.getShowPage()) > Integer
						.parseInt(bundle.getString("case.RowsPerPage"))) {
					followUpForm.setPrev(followUpForm.getShowPage());
					followUpForm.setNext(Integer.toString((Integer
							.parseInt(followUpForm.getShowPage()) + 1)));

				}
			}

			FollowUpVO lstCasesSearchVO = followUpService
					.getListCases(followUpVO);
			followUpForm.setStartIndex(lstCasesSearchVO.getStartIndex());
			followUpForm.setRowsPerPage(followUpVO.getRowsPerPage());
			 request.setAttribute("StatusList", followUpService.getCaseStatus());
			// request.setAttribute("CategoryList",
			// followUpService.getCatList());
			// request.setAttribute("ErrStatList",
			// followUpService.getErroneousList());
			// request.setAttribute("SurgeryList",
			// followUpService.getListOfSurgery());
			followUpForm.setLstCaseSearch(lstCasesSearchVO.getLstCaseSearch());
			/**
			 * pagination code starting
			 */

			followUpForm.setEndIndex(Integer.toString((Integer
					.parseInt(followUpForm.getStartIndex()) + lstCasesSearchVO
					.getLstCaseSearch().size())));
			followUpForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
			int liTotalRows = 0;
			if (lstCasesSearchVO.getTotRowCount() != null)
				liTotalRows = Integer.parseInt(lstCasesSearchVO
						.getTotRowCount());
			int liRowsPerPage = Integer.parseInt(followUpForm.getRowsPerPage());
			int liStartIndex = Integer.parseInt(followUpForm.getStartIndex());
			int liPageNo = Integer.parseInt(followUpVO.getShowPage());
			int liStartPage = Integer.parseInt(bundle
					.getString("case.StartIndex1"));
			int liEndPage = Integer.parseInt(bundle
					.getString("case.RowsPerPage"));
			int liTotalPages = Integer.parseInt(bundle
					.getString("case.StartIndex0"));
			if ((liTotalRows != Integer.parseInt(bundle
					.getString("case.StartIndex0")))
					&& (liRowsPerPage != Integer.parseInt(bundle
							.getString("case.StartIndex0")))) {
				liTotalPages = liTotalRows / liRowsPerPage;
				if ((liTotalRows % liRowsPerPage) > Integer.parseInt(bundle
						.getString("case.StartIndex0")))
					liTotalPages++;
			}
			liPageNo = (liStartIndex / liRowsPerPage)
					+ Integer.parseInt(bundle.getString("case.StartIndex1"));
			if (liPageNo > Integer.parseInt(bundle
					.getString("case.StartIndex1")) && liPageNo <= liTotalPages) {
				if (liPageNo
						% Integer
								.parseInt(bundle.getString("case.RowsPerPage")) == 1) {
					liStartPage = liPageNo;
					liEndPage = liPageNo + 9;
				} else if (liPageNo > Integer.parseInt(bundle
						.getString("case.RowsPerPage"))) {
					int liTemp = Integer.parseInt(bundle
							.getString("case.StartIndex0"));
					liTemp = liPageNo
							% Integer.parseInt(bundle
									.getString("case.RowsPerPage"));
					if (liTemp == Integer.parseInt(bundle
							.getString("case.StartIndex0")))
						liTemp = liPageNo - 9;
					else
						liTemp = (liPageNo - liTemp)
								+ Integer.parseInt(bundle
										.getString("case.StartIndex1"));
					liStartPage = liTemp;
					liEndPage = liTemp + 9;
				}
			}
			if (liEndPage > liTotalPages)
				liEndPage = liTotalPages;
			request.setAttribute("liEndPage", liEndPage);
			request.setAttribute("liTotalPages", liTotalPages);
			request.setAttribute("liStartPage", liStartPage);
			request.setAttribute("liPageNo", liPageNo);
			request.setAttribute("lStrRowsperpage", lStrRowsperpage);
			request.setAttribute("patientScheme",patientScheme);

			List<Integer> pages = new ArrayList<Integer>();
			if (liTotalPages > Integer.parseInt(bundle
					.getString("case.StartIndex0"))) {
				for (int i = liStartPage; i <= liEndPage; i++) {
					pages.add(i);
				}
			}
			request.setAttribute("pages", pages);
			if (pages.size() > Integer.parseInt(bundle
					.getString("case.RowsPerPage"))) {
				if (followUpForm.getNext() != null) {
					if (!(Integer.parseInt(followUpForm.getNext()) <= pages
							.size())) {
						followUpForm.setNext(null);
					}
				} else
					followUpForm.setNext(next);
			}

			/**
			 * pagination code ending
			 */
			request.setAttribute("pendingFlag",pendingFlag);
			request.setAttribute("loginUser", lStrEmpId);
			lStrPageName = "folowUpsearch";
			// return mapping.findForward("folowUpsearch");
		}
		if ("saveFollowupClaim".equalsIgnoreCase(lStrFlagStatus)
				&& lStrFlagStatus != null) {
			 caseStartTime = sds.format(new Date().getTime());
			FollowUpVO followUpVO = new FollowUpVO();

			followUpVO.setRoleId(followUpForm.getRoleId());
			oldRoleId = followUpForm.getRoleId();

			followUpVO.setFollowUpStatus(followUpForm.getFollowUpStatus());
			
			followUpVO.setRoleId(followUpForm.getRoleId());
			
			String caseApprovalFlag = null;
			caseApprovalFlag = request.getParameter("casesForApproval");
			request.setAttribute("casesForApproval", caseApprovalFlag);
			
			followUpVO.setUserId(lStrEmpId);
			followUpVO.setActionDone(request.getParameter("actionDone"));
			followUpVO.setCaseId(request.getParameter("CaseId"));
			followUpVO.setCLINICALID(request.getParameter("clinicalId"));
			followUpVO.setFollowUpDt(followUpForm.getFollowUpDt());
			followUpVO.setFollowUpId(followUpForm.getFollowUpId());
			followUpVO.setClaimNwhAmt(followUpForm.getClaimNwhAmt());
			if(followUpForm.getMedcoRemarks()!=null && followUpForm.getMedcoRemarks().length()>3000)
				followUpVO.setMedcoRemarks(followUpForm.getMedcoRemarks().substring(0, 3000));
			else
				followUpVO.setMedcoRemarks(followUpForm.getMedcoRemarks());			
			//followUpVO.setMedcoRemarks(followUpForm.getMedcoRemarks());
			followUpVO.setClaimNamAmt(followUpForm.getClaimNamAmt());
			if(followUpForm.getNamRemarks()!=null && followUpForm.getNamRemarks().length()>3000)
				followUpVO.setNamRemarks(followUpForm.getNamRemarks().substring(0, 3000));
			else
				followUpVO.setNamRemarks(followUpForm.getNamRemarks());	
			
			if(followUpForm.getClaimFcxPharmBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFcxPharmBill()))
				followUpVO.setClaimFcxPharmBill(followUpForm.getClaimFcxPharmBill());
			else
				followUpVO.setClaimFcxPharmBill("0");
			
			if(followUpForm.getClaimFcxConsulBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFcxConsulBill()))
				followUpVO.setClaimFcxConsulBill(followUpForm.getClaimFcxConsulBill());
			else
				followUpVO.setClaimFcxConsulBill("0");
			
			if(followUpForm.getClaimFcxInvestBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFcxInvestBill()))
				followUpVO.setClaimFcxInvestBill(followUpForm.getClaimFcxInvestBill());
			else
				followUpVO.setClaimFcxInvestBill("0");
			
			if(followUpForm.getClaimFcxAmt()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFcxAmt()))
				followUpVO.setClaimFcxAmt(followUpForm.getClaimFcxAmt());
			else
				followUpVO.setClaimFcxAmt("0");
			
			if(followUpForm.getFcxRemark()!=null && followUpForm.getFcxRemark().length()>3000)
				followUpVO.setFcxRemark(followUpForm.getFcxRemark().substring(0, 3000));
			else
				followUpVO.setFcxRemark(followUpForm.getFcxRemark());	
			
			//followUpVO.setClaimFcxAmt(followUpForm.getClaimFcxAmt());
			
			if(followUpForm.getExeAcqverifyRemark()!=null && followUpForm.getExeAcqverifyRemark().length()>3000)
				followUpVO.setExeAcqverifyRemark(followUpForm.getExeAcqverifyRemark().substring(0, 3000));
			else
				followUpVO.setExeAcqverifyRemark(followUpForm.getExeAcqverifyRemark());	
			
			followUpVO.setExeAcqvrifyChklst(followUpForm.getExeAcqvrifyChklst());
			
			if(followUpForm.getExeDisphotoChklst()!=null && followUpForm.getExeDisphotoChklst().length()>3000)
				followUpVO.setExeDisphotoChklst(followUpForm.getExeDisphotoChklst().substring(0, 3000));
			else
				followUpVO.setExeDisphotoChklst(followUpForm.getExeDisphotoChklst());	
			
			if(followUpForm.getExeDisphotoremark()!=null && followUpForm.getExeDisphotoremark().length()>3000)
				followUpVO.setExeDisphotoremark(followUpForm.getExeDisphotoremark().substring(0, 3000));
			else
				followUpVO.setExeDisphotoremark(followUpForm.getExeDisphotoremark());
			
			followUpVO.setExeMedverifyChklst(followUpForm
					.getExeMedverifyChklst());
			
			if(followUpForm.getExeMedVerifyRemark()!=null && followUpForm.getExeMedVerifyRemark().length()>3000)
				followUpVO.setExeMedVerifyRemark(followUpForm.getExeMedVerifyRemark().substring(0, 3000));
			else
				followUpVO.setExeMedVerifyRemark(followUpForm.getExeMedVerifyRemark());
			
			followUpVO
					.setExePatphotoChklst(followUpForm.getExePatphotoChklst());
			
			if(followUpForm.getExePatphotoRemark()!=null && followUpForm.getExePatphotoRemark().length()>3000)
				followUpVO.setExePatphotoRemark(followUpForm.getExePatphotoRemark().substring(0, 3000));
			else
				followUpVO.setExePatphotoRemark(followUpForm.getExePatphotoRemark());
			
			followUpVO.setExeQuantverifyChklst(followUpForm
					.getExeQuantverifyChklst());
			
			if(followUpForm.getExeQuantverifyRemark()!=null && followUpForm.getExeQuantverifyRemark().length()>3000)
				followUpVO.setExeQuantverifyRemark(followUpForm.getExeQuantverifyRemark().substring(0, 3000));
			else
				followUpVO.setExeQuantverifyRemark(followUpForm.getExeQuantverifyRemark());
			
			followUpVO.setExereprtcheckChklst(followUpForm
					.getExereprtcheckChklst());
			
			if(followUpForm.getExeReprtcheckRemark()!=null && followUpForm.getExeReprtcheckRemark().length()>3000)
				followUpVO.setExeReprtcheckRemark(followUpForm.getExeReprtcheckRemark().substring(0, 3000));
			else
				followUpVO.setExeReprtcheckRemark(followUpForm.getExeReprtcheckRemark());
			
			followUpVO.setFtdBeneficiryChklst(followUpForm
					.getFtdBeneficiryChklst());
			
			
			if(followUpForm.getClaimFTDPharmBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFTDPharmBill()))
				followUpVO.setClaimFTDPharmBill(followUpForm.getClaimFTDPharmBill());
			else
				followUpVO.setClaimFTDPharmBill("0");
			
			if(followUpForm.getClaimFTDConsulBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFTDConsulBill()))
				followUpVO.setClaimFTDConsulBill(followUpForm.getClaimFTDConsulBill());
			else
				followUpVO.setClaimFTDConsulBill("0");
			
			if(followUpForm.getClaimFTDInvestBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFTDInvestBill()))
				followUpVO.setClaimFTDInvestBill(followUpForm.getClaimFTDInvestBill());
			else
				followUpVO.setClaimFTDInvestBill("0");
			
			/*if(followUpForm.getClaimFTDTotBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimFTDTotBill()))
				followUpVO.setClaimFTDTotBill(followUpForm.getClaimFTDTotBill());
			else
				followUpVO.setClaimFTDTotBill("0");*/
			
			
			
			if(followUpForm.getFtdBeneficiryRemark()!=null && followUpForm.getFtdBeneficiryRemark().length()>3000)
				followUpVO.setFtdBeneficiryRemark(followUpForm.getFtdBeneficiryRemark().substring(0, 3000));
			else
				followUpVO.setFtdBeneficiryRemark(followUpForm.getFtdBeneficiryRemark());
					
			followUpVO.setFtdRemarkvrifedChklst(followUpForm
					.getFtdRemarkvrifedChklst());
			
			if(followUpForm.getFtdRemarkvrifedRemark()!=null && followUpForm.getFtdRemarkvrifedRemark().length()>3000)
				followUpVO.setFtdRemarkvrifedRemark(followUpForm.getFtdRemarkvrifedRemark().substring(0, 3000));
			else
				followUpVO.setFtdRemarkvrifedRemark(followUpForm.getFtdRemarkvrifedRemark());
			followUpVO.setFtdRemarkvrifedRemark(followUpForm
					.getFtdRemarkvrifedRemark());	
			
			followUpVO.setClaimFtdAmt(followUpForm.getClaimFtdAmt());
			
			if(followUpForm.getFtdRmks()!=null && followUpForm.getFtdRmks().length()>3000)
				followUpVO.setFtdRmks(followUpForm.getFtdRmks().substring(0, 3000));
			else
				followUpVO.setFtdRmks(followUpForm.getFtdRmks());
			
			
			if(followUpForm.getClaimCHPharmBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimCHPharmBill()))
				followUpVO.setClaimCHPharmBill(followUpForm.getClaimCHPharmBill());
			else
				followUpVO.setClaimCHPharmBill("0");
			
			if(followUpForm.getClaimCHConsulBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimCHConsulBill()))
				followUpVO.setClaimCHConsulBill(followUpForm.getClaimCHConsulBill());
			else
				followUpVO.setClaimCHConsulBill("0");
			
			if(followUpForm.getClaimCHInvestBill()!=null && !"".equalsIgnoreCase(followUpForm.getClaimCHInvestBill()))
				followUpVO.setClaimCHInvestBill(followUpForm.getClaimCHInvestBill());
			else
				followUpVO.setClaimCHInvestBill("0");
			
			if(followUpForm.getChRemark()!=null && followUpForm.getChRemark().length()>3000)
				followUpVO.setChRemark(followUpForm.getChRemark().substring(0, 3000));
			else
				followUpVO.setChRemark(followUpForm.getChRemark());			
			followUpVO.setClaimChAmt(followUpForm.getClaimChAmt());
			
			followUpVO.setAcoAprAmt(followUpForm.getAcoAprAmt());
			
			if(followUpForm.getAcoRemark()!=null && followUpForm.getAcoRemark().length()>3000)
				followUpVO.setAcoRemark(followUpForm.getAcoRemark().substring(0, 3000));
			else
				followUpVO.setAcoRemark(followUpForm.getAcoRemark());			
			
			String OnloadCaseOpen=request.getParameter("OnloadCaseOpen");
			if(OnloadCaseOpen!=null)
				{
					if(OnloadCaseOpen.equalsIgnoreCase("Y"))
						followUpVO.setOnloadCaseOpen(OnloadCaseOpen);
				}
			logger.info("In saveFollowupClaim before saving");
			followUpVO = followUpService.saveFollowUpClaim(followUpVO);	
			String caseEndTime = sds.format(new Date().getTime());
			String actionDone = commonService.getActionDoneName(followUpVO.getFollowUpId(),"ehfFollowup");
			loggingService.logTime(actionDone,followUpVO.getFollowUpId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
/*	    	if(followUpVO.getMsg()!=null && !"".equalsIgnoreCase(followUpVO.getMsg())  && !"failure".equalsIgnoreCase(followUpVO.getMsg()))
    		{
    			request.setAttribute("successFlag", "Y");
    			if(followUpVO.getMsg()!=null)
    				{
    					if(!followUpVO.getMsg().equalsIgnoreCase(config.getString("preauth_msg_CD213")))
	    					{
	    						String autoCaseId = followUpForm.getFollowUpId();
		    	        	    if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
		    	        	    	autoCaseId = "0";
		    	        	    ClaimCases.releaseCase(autoCaseId);
	    					}   
    				}
    		}*/
			
			if(!followUpVO.getSmsMsg().equalsIgnoreCase(ClaimsConstants.EMPTY)){
				try {
				EhfmSeq ehfmSeq=null;
				String smsNextVal=ClaimsConstants.EMPTY;
				PatientVO patientVO=new PatientVO();
				Date date = new Date();
				Date crtDt;			
				crtDt = sdf.parse((sdf.format(date)));
				
				ehfmSeq=commonService.getSeqNextVal("PATIENT_SMS_SNO");
	            smsNextVal = String.valueOf(ehfmSeq.getSeqNextVal());
	            int smsSeqNextVal=Integer.parseInt(smsNextVal);
	            if(followUpVO.getPatientId()!=null||!"".equalsIgnoreCase(followUpVO.getPatientId()))
	            	patientVO = followUpService.getPatientDtls(followUpVO.getPatientId()); 
	            
	            if(ClaimsConstants.TRUE.equalsIgnoreCase(config.getString("SmsRequired")))
	            {
	            	String lStrResultMsg=null;
	            	PatientSmsVO patientSmsVO=new PatientSmsVO();
	            	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
	            	patientSmsVO.setPhoneNo(patientVO.getContactNo());
	            	patientSmsVO.setSmsText("Dear "+patientVO.getFirstName().trim()+" ,"+followUpVO.getSmsMsg());
	            	patientSmsVO.setEmpCode(patientVO.getEmpCode());
	            	patientSmsVO.setEmpName(patientVO.getFirstName());
	            	patientSmsVO.setCrtUsr(lStrUserId);
	            	patientSmsVO.setCrtDt(crtDt);
	            	patientSmsVO.setSmsAction(followUpVO.getMsg());
	            	patientSmsVO.setPatientId(followUpVO.getPatientId());
	            	lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
	            	if(lStrResultMsg!=null)
	            	{
	            	ehfmSeq.setSeqNextVal(Long.valueOf(smsSeqNextVal + 1));
					commonService.updateSequenceVal(ehfmSeq);
	            	}
	            }
	            
	           if (config.getBoolean("EmailRequired")) 
	            {
	            	String mailId=null;
	            	if(patientVO.geteMailId()!=null && !patientVO.geteMailId().equals(ClaimsConstants.EMPTY))
	            	{
	            	mailId=patientVO.geteMailId();
	            	String[] emailToArray = {mailId};
	            	EmailVO emailVO = new EmailVO();
					emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
					emailVO.setTemplateName(config.getString("EhfFPTemplateName"));
					emailVO.setFromEmail(config.getString("emailFrom"));
					emailVO.setToEmail(emailToArray);
					emailVO.setSubject(config.getString("fpMailSubject"));
					Map<String, String> model = new HashMap<String, String>();
					model.put("patientName", patientVO.getFirstName().trim());
					model.put("caseNo", followUpVO.getFollowUpId());
					model.put("status", followUpVO.getMsg());
					model.put("statusDate",crtDt.toString());
					commonService.generateMail(emailVO, model);
	            	}
	   			}	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			
			if (!followUpVO.getMsg().equalsIgnoreCase(ClaimsConstants.Failure)) {
				request.setAttribute("saveMsg", followUpVO.getMsg());
				request.setAttribute("disableAll", ClaimsConstants.YES);
			}
			saveFlag = ClaimsConstants.TRUE;			
			lStrFlagStatus = "getFollowUpDtls";
			
			if(followUpVO.getUnlockCase()!=null && OnloadCaseOpen!=null)
			{
				if("Y".equalsIgnoreCase(followUpVO.getUnlockCase()) && OnloadCaseOpen.equalsIgnoreCase("Y"))
					{
						String autoCaseId = followUpForm.getFollowUpId();
	        	    	if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
	        	    		autoCaseId = "0";
	        	    	ClaimCases.releaseCase(autoCaseId);
	        	    	request.setAttribute("autoCaseId", autoCaseId);
	        	    	request.setAttribute("OnloadCaseOpen", "Y");
	        	    	request.setAttribute("UnlockCase", "Y");
					}
			}	
		}
		
		if(lStrFlagStatus != null && "OnloadCaseOpen".equalsIgnoreCase(lStrFlagStatus))
		{
            caseStartTime = sds.format(new Date().getTime());
			
		 	String casesForApprovalFlag = request.getParameter("casesForApproval");
		 	String lStrSchemeType = request.getParameter("schemeType");
		 	String patientScheme=request.getParameter("patientScheme");
		 	if (casesForApprovalFlag == null || casesForApprovalFlag.equals(ClaimsConstants.EMPTY))
				casesForApprovalFlag = followUpForm.getCasesForApprovalFlag();
		 	if (casesForApprovalFlag == null || casesForApprovalFlag.equals(ClaimsConstants.EMPTY))
				casesForApprovalFlag = "N";
		 	if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
	 	        lStrSchemeType = lStrUserState;
		 	
		 	String flag = request.getParameter("viewFlag");
		 	if(followUpForm.getFollowUpId() != null && !followUpForm.getFollowUpId().equals(""))
				followUpService.setviewFlag(followUpForm.getFollowUpId(),flag);
		 	
		 	String autoCaseId = request.getParameter("autoCaseId");
    	    if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
    	    	autoCaseId = "0";
    	    ClaimCases.releaseCase(autoCaseId);
    	    String module = request.getParameter("module");
    	    
    	    FollowUpVO followUpVO = new FollowUpVO();
    	    
    	    followUpVO.setShowPage(showPage);
			followUpVO.setGrpList(groupsList);
			followUpVO.setSchemeType(lStrSchemeType);
			followUpVO.setSearchFlag(request.getParameter("searchFlag"));
			followUpVO.setUserId(lStrEmpId);
			followUpVO.setPatientScheme(patientScheme);
    	    
			if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
				followUpForm.setShowScheme("show");
				followUpForm.setSchemeType(lStrSchemeType);
			}
			else {
				followUpForm.setShowScheme("hide");
			}

			followUpVO.setCasesForApprovalFlag(casesForApprovalFlag);
			followUpForm.setCasesForApprovalFlag(casesForApprovalFlag);
			
    	    
    	    String checkForCase = ClaimCases.getCaseForUserId(lStrEmpId,module);
        	if(checkForCase!=null && checkForCase.equalsIgnoreCase("false"))
	        	{
        			List<CasesSearchVO> lstCasesSearchVO = new ArrayList<CasesSearchVO>();
        			lstCasesSearchVO=followUpService.getAllListCases(followUpVO);
		        	if(lstCasesSearchVO!=null)
		        		{
		        			if(lstCasesSearchVO.size()>0)
					        	{
						        	for (CasesSearchVO caseSearchVO : lstCasesSearchVO)
							        	{
							        		String caseId1 = caseSearchVO.getFollowUpId();
							        		if(caseSearchVO.getCaseId()!=null)
							        			caseIdOnLoad=caseSearchVO.getCaseId();
							        		boolean status = ClaimCases.isAvailable(caseId1,lStrEmpId,module);
							        		if (status) 
							        			{
									        		//timeOutCount = "0";		        			
									        		//if(!medcoUpdatedStatus.contains(caseSearchVO.getCaseStatusId()))
									        		//timeOutCount = casesSearchService.getTimeOutCount(caseId1,groupsList,module);
									        		 
									        		caseIdchk=caseId1;
									        		request.setAttribute("CaseId", caseId1);
									        		//request.setAttribute("timeOutCount", timeOutCount);
								                      break;
							        			}		        		
							        	}
					        	}
				        	else
				        		return mapping.findForward("caseNotFound");
		        		}	
		        	else
		        		return mapping.findForward("caseNotFound");
	        	}
        	else
	        	{
	        	    //timeOutCount = "0";		        		
	        		//if(!medcoUpdatedStatus.contains(casesSearchService.getCaseStatusForCase(checkForCase)))
	        		//timeOutCount = casesSearchService.getTimeOutCount(checkForCase,groupsList,module);
	        		//request.setAttribute("timeOutCount", timeOutCount);
        		
	        		request.setAttribute("CaseId", checkForCase);
	        		caseIdchk=checkForCase;
	        		caseIdOnLoad=caseIdchk.substring(0, caseIdchk.length()-2);
	        	}
        	
        	request.setAttribute("casesForApprovalFlag", casesForApprovalFlag);
        	request.setAttribute("autoActionFlag", "OnloadCaseOpen");
        	request.setAttribute("OnloadCaseOpen", "Y");
        	
        	lStrFlagStatus="getFollowUpDtls";
		}
		
		
		
		if ("getFollowUpDtls".equalsIgnoreCase(lStrFlagStatus)
				&& lStrFlagStatus != null) {
			String followUpId = request.getParameter("followUpid");
			
			
			if(followUpId==null)
				{
					if(caseIdchk!=null)
						followUpId=caseIdchk;
				}
			lStrCaseId = request.getParameter("CaseId");
			boolean verifySentBackCase=false;
			if(lStrCaseId==null)
				{
					if(caseIdOnLoad!=null)
						lStrCaseId=caseIdOnLoad;
				}
			String flag = request.getParameter("viewFlag");
			String backFlg=request.getParameter("backFlg");
			if(backFlg!=null)
				{
					if(backFlg.equalsIgnoreCase("N"))
						request.setAttribute("hideBackButton","Y");
				}
			String photo=null;
			
		 	 
	    	 verifySentBackCase=preauthService.verifyFollowUpClaimPending(followUpId);
				if(verifySentBackCase)
					request.setAttribute("sentBack",verifySentBackCase);
			
			FollowUpVO followUpVO = new FollowUpVO();
			followUpVO.setFollowUpId(followUpId);
			followUpVO.setCaseId(lStrCaseId);
			List<CommonDtlsVO> commonDtls = followUpService
					.getPatientCommonDtls(lStrCaseId, followUpId);
			if(lStrCaseId!=null)
				{
					photo=followUpService.getPatientPhoto(lStrCaseId);
					if(photo!=null)
						{
							try
								{
									File f=new File(photo);
									FileInputStream fis=new FileInputStream(f);
									DataInputStream dis=new DataInputStream(fis);
									byte bytes[]=new byte[dis.available()];
									fis.read(bytes);
									session.setAttribute("contentType", "image/jpg");
									session.setAttribute("File", bytes);
									fis.close();
									dis.close();
									followUpForm.setPhotoUrl(photo);
								}
							catch(Exception e)
								{
									e.printStackTrace();
									//GLOGGER.error ( "Exception occured while photo is not available in the path specified in FollowUpAction." +e.getMessage()) ;
								}
							
						}
				}
			if (commonDtls != null && !commonDtls.isEmpty()) {
				int lstSize = commonDtls.size();
				String surgName = ClaimsConstants.EMPTY;
				for (int i = 0; i < lstSize; i++) {
					if (!ClaimsConstants.EMPTY.equalsIgnoreCase(commonDtls.get(i).getSURGNAME())) {
						if (lstSize > 1)
							surgName = surgName
									+ commonDtls.get(i).getSURGNAME() + ",";
						else
							surgName = surgName
									+ commonDtls.get(i).getSURGNAME();
					}
				}

				request.setAttribute("surgName", surgName);
				request.setAttribute("patCommonDtls", commonDtls.get(0));
				request.setAttribute("scheme_id", commonDtls.get(0).getScheme());
				request.setAttribute("patientScheme", commonDtls.get(0).getPatientScheme());
				if(commonDtls.get(0).getCsAdmDt()!=null && !"".equalsIgnoreCase(commonDtls.get(0).getCsAdmDt()))
					followUpForm.setCsAdmDt(commonDtls.get(0).getCsAdmDt());
				if(commonDtls.get(0).getCsSurgDt()!=null && !"".equalsIgnoreCase(commonDtls.get(0).getCsSurgDt()))
					followUpForm.setCsSurgDt(commonDtls.get(0).getCsSurgDt());
				if(commonDtls.get(0).getCsDisDt()!=null && !"".equalsIgnoreCase(commonDtls.get(0).getCsDisDt()))
					followUpForm.setCsDisDt(commonDtls.get(0).getCsDisDt());
			}

			List<FollowUpVO> followUPLst = followUpService.getFollowUpList(
					lStrCaseId, followUpVO.getFollowUpId());
			followUpForm.setFollowUPLst(followUPLst);
			
			if(followUpId != null && !followUpId.equals(""))
				followUpService.setviewFlag(followUpId,flag);
			
			/*
			 * if(followUPLst!=null && followUPLst.size()>0){ List<FollowUpVO>
			 * followUPTotalLst = new ArrayList<FollowUpVO>();
			 * followUPTotalLst.add(followUPLst.get(followUPLst.size()-1));
			 * followUpForm.setFollowUPTotalLst(followUPTotalLst); }
			 */
             String ftdFlag="";
			// request.setAttribute("followUpList",followUPLst);
			if(followUpId!=null && !"".equalsIgnoreCase(followUpId))
			{
			 ftdFlag=followUpService.getfollowupFtdFlag(followUpId);
			}
			request.setAttribute("ftdFlag", ftdFlag);

			followUpVO = followUpService.getFollowUPDtls(followUpVO);
			followUpForm.setFollowUpId(followUpVO.getFollowUpId());
			request.setAttribute("followUpID",followUpVO.getFollowUpId());
			followUpForm.setFollowUpDt(followUpVO.getFollowUpDt());
			followUpForm.setClaimAmt(followUpVO.getClaimAmt());
			followUpForm.setPackageAmt(followUpVO.getPackAmt());
			followUpForm.setClaimNwhAmt(followUpVO.getClaimNwhAmt());
			followUpForm.setCaseId(followUpVO.getCaseId());
			followUpForm.setClinicalId(followUpVO.getCLINICALID());

			followUpForm.setMedcoRemarks(followUpVO.getMedcoRemarks());
			followUpForm.setNamRemarks(followUpVO.getNamRemarks());
			followUpForm.setClaimNamAmt(followUpVO.getClaimNamAmt());
			followUpForm.setFcxRemark(followUpVO.getFcxRemark());
			//followUpForm.setClaimFcxAmt(followUpVO.getClaimFcxAmt());
			followUpForm.setClaimFtdAmt(followUpVO.getClaimFtdAmt());
			followUpForm.setFtdRmks(followUpVO.getFtdRmks());
			followUpForm.setClaimChAmt(followUpVO.getClaimChAmt());
			followUpForm.setChRemark(followUpVO.getChRemark());
			
			followUpForm.setAcoRemark(followUpVO.getAcoRemark());
			followUpForm.setAcoAprAmt(followUpVO.getAcoAprAmt());
            request.setAttribute("followupStatus", followUpVO.getFollowUpStatus());
            if(followUpVO.getSchemeType()!=null && !"".equalsIgnoreCase(followUpVO.getSchemeType()))
            	request.setAttribute("followUPSchemeID",followUpVO.getSchemeType());
            	
			if(followUpVO.getNewClmFwdAmt()!=null)
				followUpForm.setNewClmFwdAmt(followUpVO.getNewClmFwdAmt());
			else
				followUpForm.setNewClmFwdAmt("0");
			
			if(followUpVO.getClaimFcxPharmBill()!=null)
				followUpForm.setClaimFcxPharmBill(followUpVO.getClaimFcxPharmBill());
			else
				followUpForm.setClaimFcxPharmBill("0");
			
			if(followUpVO.getClaimFcxConsulBill()!=null)
				followUpForm.setClaimFcxConsulBill(followUpVO.getClaimFcxConsulBill());
			else
				followUpForm.setClaimFcxConsulBill("0");
			
			if(followUpVO.getClaimFcxInvestBill()!=null)
				followUpForm.setClaimFcxInvestBill(followUpVO.getClaimFcxInvestBill());
			else
				followUpForm.setClaimFcxInvestBill("0");
			
			if(followUpVO.getClaimFcxAmt()!=null)
				followUpForm.setClaimFcxAmt(followUpVO.getClaimFcxAmt());
			else
				followUpForm.setClaimFcxAmt("0");
				
			followUpForm.setExeAcqverifyRemark(followUpVO
					.getExeAcqverifyRemark());
			followUpForm.setExeAcqverifyRemark(followUpVO
					.getExeAcqverifyRemark());
			followUpForm
					.setExeAcqvrifyChklst(followUpVO.getExeAcqvrifyChklst());
			followUpForm
					.setExeDisphotoChklst(followUpVO.getExeDisphotoChklst());
			followUpForm
					.setExeDisphotoremark(followUpVO.getExeDisphotoremark());
			followUpForm.setExeMedverifyChklst(followUpVO
					.getExeMedverifyChklst());
			followUpForm.setExeMedVerifyRemark(followUpVO
					.getExeMedVerifyRemark());
			followUpForm
					.setExePatphotoChklst(followUpVO.getExePatphotoChklst());
			followUpForm
					.setExePatphotoRemark(followUpVO.getExePatphotoRemark());
			followUpForm.setExeQuantverifyChklst(followUpVO
					.getExeQuantverifyChklst());
			followUpForm.setExeQuantverifyRemark(followUpVO
					.getExeQuantverifyRemark());
			followUpForm.setExereprtcheckChklst(followUpVO
					.getExereprtcheckChklst());
			followUpForm.setExeReprtcheckRemark(followUpVO
					.getExeReprtcheckRemark());
			followUpForm.setFtdBeneficiryChklst(followUpVO
					.getFtdBeneficiryChklst());
			followUpForm.setFtdBeneficiryRemark(followUpVO
					.getFtdBeneficiryRemark());
			followUpForm.setFtdRemarkvrifedChklst(followUpVO
					.getFtdRemarkvrifedChklst());
			followUpForm.setFtdRemarkvrifedRemark(followUpVO
					.getFtdRemarkvrifedRemark());
			
			if(followUpVO.getClaimFTDPharmBill()!=null)
				followUpForm.setClaimFTDPharmBill(followUpVO.getClaimFTDPharmBill());
			else
				followUpForm.setClaimFTDPharmBill("0");
			
			if(followUpVO.getClaimFTDConsulBill()!=null)
				followUpForm.setClaimFTDConsulBill(followUpVO.getClaimFTDConsulBill());
			else
				followUpForm.setClaimFTDConsulBill("0");
			
			if(followUpVO.getClaimFTDInvestBill()!=null)
				followUpForm.setClaimFTDInvestBill(followUpVO.getClaimFTDInvestBill());
			else
				followUpForm.setClaimFTDInvestBill("0");
			
			/*if(followUpVO.getClaimFTDTotBill()!=null)
				followUpForm.setClaimFTDTotBill(followUpVO.getClaimFTDTotBill());
			else
				followUpForm.setClaimFTDTotBill("0");*/
			
			if(followUpVO.getClaimCHPharmBill()!=null)
				followUpForm.setClaimCHPharmBill(followUpVO.getClaimCHPharmBill());
			else
				followUpForm.setClaimCHPharmBill("0");
			
			if(followUpVO.getClaimCHConsulBill()!=null)
				followUpForm.setClaimCHConsulBill(followUpVO.getClaimCHConsulBill());
			else
				followUpForm.setClaimCHConsulBill("0");
			
			if(followUpVO.getClaimCHInvestBill()!=null)
				followUpForm.setClaimCHInvestBill(followUpVO.getClaimCHInvestBill());
			else
				followUpForm.setClaimCHInvestBill("0");
			
			
			followUpForm.setFollowUpStatus(followUpVO.getFollowUpStatus());
			
			// getting group id
			if (saveFlag != null && saveFlag.equalsIgnoreCase(ClaimsConstants.TRUE)) {
				lStrUserRole = oldRoleId;

			} else {
				String lStrUserGroup1=null;
				String lStrUserGroup = config.getString("followupGroup_"
						+ followUpVO.getFollowUpStatus());
				for (LabelBean labelBean : groupsList) {
					if (labelBean.getID() != null
							&& labelBean.getID()
									.equalsIgnoreCase(lStrUserGroup)) {
						lStrUserGroup1=lStrUserGroup;
						break;
					} else
						lStrUserGroup1 = null;
				}
				if (groupsList == null || groupsList.isEmpty()) {
					lStrUserGroup1 = null;
				}
				String caseApprovalFlag = null;
				caseApprovalFlag = request.getParameter("casesForApproval");
				request.setAttribute("casesForApproval", caseApprovalFlag);
				if (caseApprovalFlag != null
						&& caseApprovalFlag.equalsIgnoreCase("N")) {
					lStrUserGroup1 = null;
				}

				lStrUserRole = lStrUserGroup1;
			}

			// getting list of buttons for specific role and status
			List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
					followUpVO.getFollowUpStatus(), lStrUserRole, ClaimsConstants.FOLLOW_UP,
					ClaimsConstants.FOLLOWUP_CLAIM);
			followUpForm.setButtonList(buttonList);

			if (buttonList.size() == 0)
				request.setAttribute("disableAll", ClaimsConstants.YES);

			List<com.ahct.attachments.vo.AttachmentVO> lstAttachments = followUpService
					.getFollowUPAttach(followUpVO.getFollowUpId());
			followUpForm.setLstAttachments(lstAttachments);

			List<com.ahct.attachments.vo.AttachmentVO> lstCompDocs = followUpService
					.getFollowUPCompDocs(lStrCaseId,"compDocs");
			followUpForm.setLstCompDocs(lstCompDocs);

			List<com.ahct.attachments.vo.AttachmentVO> lstCompPhotos = followUpService
					.getFollowUPCompDocs(lStrCaseId,"compPhotos");
			followUpForm.setLstCompPhotos(lstCompPhotos);
			
			List<com.ahct.attachments.vo.AttachmentVO> lstCompDTRS = followUpService
					.getFollowUPCompDocs(lStrCaseId,"compDTRS");
			followUpForm.setLstCompDTRS(lstCompDTRS);
			
			if (lStrUserRole != null
					&& lStrUserRole.equalsIgnoreCase(config
							.getString("EHF.Claims.MEDCO"))) {
				if (followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.FlpDone"))
						|| followUpVO.getFollowUpStatus().equalsIgnoreCase(
								config.getString("EHF.FollowUP.FTDPending"))
						|| followUpVO.getFollowUpStatus().equalsIgnoreCase(
								config.getString("EHF.FollowUP.HeadPending"))) {
					followUpForm.setClaimNwhAmt(followUpVO.getClaimNwhAmt());
					followUpForm.setMedcoRemarks(ClaimsConstants.EMPTY);
					followUpForm.setAddAttach(ClaimsConstants.YES);
					followUpForm.setViewAttach(ClaimsConstants.NO);
					request.setAttribute("followupStatus", followUpVO.getFollowUpStatus());
				} else {
					if (followUpVO.getClaimNwhAmt() == null
							|| followUpVO.getClaimNwhAmt().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
						followUpForm.setMsg("Cannot initiate FollowUp");
						followUpForm.setRoleId(lStrUserRole);
					}
					
					lStrPageName = "FolowUpClaimForm";
				}
			} else if (lStrUserRole != null
					&& lStrUserRole.equalsIgnoreCase(config
							.getString("preauth_nam_role"))) {
				if (followUpVO.getFollowUpStatus()!=null && followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.Initiated"))) {
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);
				} 
				else if (followUpVO.getFollowUpStatus()!=null && followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.CHPendUpdated"))) {
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setNamRemarks(ClaimsConstants.EMPTY);
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);
				}	
				else if (followUpVO.getFollowUpStatus()!=null && followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.FTDPendUpdated"))) {
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setNamRemarks(ClaimsConstants.EMPTY);
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);
				}	
                else {
					if (followUpVO.getNamRemarks() != null
							&& !followUpVO.getNamRemarks().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
						followUpForm.setShowNam(ClaimsConstants.YES);
					} else {
						followUpForm
								.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						// followUpForm.setRoleId(roleId);
						lStrPageName = "FolowUpClaimForm";
					}
				}

			} else if (lStrUserRole != null
					&& lStrUserRole.equalsIgnoreCase(config
							.getString("EHF.FollowUP.FCX"))) {
				if (followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.Forwarded"))) {
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setShowFcx(ClaimsConstants.YES);
					followUpForm.setFcxRemark(ClaimsConstants.EMPTY);
					
					followUpForm
					.setExeDisphotoChklst(ClaimsConstants.EMPTY);
			        followUpForm
					.setExeDisphotoremark(ClaimsConstants.EMPTY);
			        followUpForm
					.setExePatphotoChklst(ClaimsConstants.EMPTY);
			        followUpForm
					.setExePatphotoRemark(ClaimsConstants.EMPTY);
					
					followUpForm.setExeAcqverifyRemark(ClaimsConstants.EMPTY);
					followUpForm
							.setExeAcqvrifyChklst(ClaimsConstants.EMPTY);
					
					followUpForm.setExeMedverifyChklst(ClaimsConstants.EMPTY);
					followUpForm.setExeMedVerifyRemark(ClaimsConstants.EMPTY);
					
					followUpForm.setExeQuantverifyChklst(ClaimsConstants.EMPTY);
					followUpForm.setExeQuantverifyRemark(ClaimsConstants.EMPTY);
					followUpForm.setExereprtcheckChklst(ClaimsConstants.EMPTY);
					followUpForm.setExeReprtcheckRemark(ClaimsConstants.EMPTY);
					
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);
				} else {
					if (followUpVO.getFcxRemark() != null
							&& !followUpVO.getFcxRemark().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
						followUpForm.setShowNam(ClaimsConstants.YES);
						followUpForm.setShowFcx(ClaimsConstants.YES);
					} else {
						followUpForm
								.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						// followUpForm.setRoleId(roleId);
						lStrPageName = "FolowUpClaimForm";
					}
				}
			} else if (lStrUserRole != null
					&& lStrUserRole.equalsIgnoreCase(config
							.getString("EHF.FollowUP.FTD"))) {
				if (followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.verified"))) {
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setShowFcx(ClaimsConstants.YES);
					if(ftdFlag!=null && "Y".equalsIgnoreCase(ftdFlag))
					{
					followUpForm.setShowFtd(ClaimsConstants.YES);
					}
					
					followUpForm.setFtdBeneficiryChklst(ClaimsConstants.EMPTY);
					followUpForm.setFtdBeneficiryRemark(ClaimsConstants.EMPTY);
					followUpForm.setFtdRemarkvrifedChklst(ClaimsConstants.EMPTY);
					followUpForm.setFtdRemarkvrifedRemark(ClaimsConstants.EMPTY);
					if(followUpForm.getClaimFtdAmt()==null)
						followUpForm.setClaimFtdAmt(ClaimsConstants.EMPTY);
					else if(followUpForm.getClaimFtdAmt()!=null && followUpForm.getClaimFtdAmt().length()==0)
						followUpForm.setClaimFtdAmt(ClaimsConstants.EMPTY);
					followUpForm.setFtdRmks(ClaimsConstants.EMPTY);
					
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);
				} else {
					if (followUpVO.getFtdRmks() != null
							&& !followUpVO.getFtdRmks().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
						followUpForm.setShowNam(ClaimsConstants.YES);
						followUpForm.setShowFcx(ClaimsConstants.YES);
						if(ftdFlag!=null && "Y".equalsIgnoreCase(ftdFlag))
						{
						followUpForm.setShowFtd(ClaimsConstants.YES);
						}
					} else {
						followUpForm
								.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						// followUpForm.setRoleId(roleId);
						lStrPageName = "FolowUpClaimForm";
					}
				}
			} else if (lStrUserRole != null
					&& lStrUserRole.equalsIgnoreCase(config
							.getString("EHF.Claims.CH"))) {
				if (followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.FTDApprove")) || followUpVO.getFollowUpStatus().equalsIgnoreCase(
								config.getString("EHF.FollowUP.FCXForwarded"))) {
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setShowFcx(ClaimsConstants.YES);
					if(ftdFlag!=null && "Y".equalsIgnoreCase(ftdFlag))
					{
					followUpForm.setShowFtd(ClaimsConstants.YES);
					}
					followUpForm.setShowCh(ClaimsConstants.YES);
					if(followUpForm.getClaimChAmt()==null)
						followUpForm.setClaimChAmt(ClaimsConstants.EMPTY);
					else if(followUpForm.getClaimChAmt()!=null && followUpForm.getClaimChAmt().length()==0)
						followUpForm.setClaimChAmt(ClaimsConstants.EMPTY);
					//followUpForm.setClaimChAmt(ClaimsConstants.EMPTY);
					followUpForm.setChRemark(ClaimsConstants.EMPTY);
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);					
				} else {
					if (followUpVO.getChRemark() != null
							&& !followUpVO.getChRemark().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
						followUpForm.setShowNam(ClaimsConstants.YES);
						followUpForm.setShowFcx(ClaimsConstants.YES);
						if(ftdFlag!=null && "Y".equalsIgnoreCase(ftdFlag))
						{
						followUpForm.setShowFtd(ClaimsConstants.YES);
						}
						followUpForm.setShowCh(ClaimsConstants.YES);
						followUpForm.setViewAttach(ClaimsConstants.YES);
					} else {
						followUpForm
								.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						// followUpForm.setRoleId(roleId);
						lStrPageName = "FolowUpClaimForm";
					}
				}
			}else if (lStrUserRole!=null && lStrUserRole.equalsIgnoreCase(config
					.getString("EHF.Claims.ACO"))) {
				if (followUpVO.getFollowUpStatus().equalsIgnoreCase(
						config.getString("EHF.FollowUP.HeadApprove"))) {
					
					followUpForm.setAcoRemark(ClaimsConstants.EMPTY);
					followUpForm.setAcoAprAmt(ClaimsConstants.EMPTY);
					
					followUpForm.setShowNam(ClaimsConstants.YES);
					followUpForm.setShowFcx(ClaimsConstants.YES);
					if(ftdFlag!=null && "Y".equalsIgnoreCase(ftdFlag))
					{
					followUpForm.setShowFtd(ClaimsConstants.YES);
					}
					followUpForm.setShowCh(ClaimsConstants.YES);
					followUpForm.setShowAco(ClaimsConstants.YES);
					followUpForm.setAddAttach(ClaimsConstants.NO);
					followUpForm.setViewAttach(ClaimsConstants.YES);
				} 
				else {
					if (followUpVO.getAcoRemark() != null
							&& !followUpVO.getAcoRemark().equalsIgnoreCase(
									ClaimsConstants.EMPTY)) {
						followUpForm.setShowNam(ClaimsConstants.YES);
						followUpForm.setShowFcx(ClaimsConstants.YES);
						if(ftdFlag!=null && "Y".equalsIgnoreCase(ftdFlag))
						{
						followUpForm.setShowFtd(ClaimsConstants.YES);
						}
						followUpForm.setShowCh(ClaimsConstants.YES);
						followUpForm.setShowAco(ClaimsConstants.YES);
						followUpForm.setViewAttach(ClaimsConstants.YES);
					} else {
						followUpForm
								.setMsg(ClaimsConstants.CLAIM_NOTINIT);
						//followUpForm.setRoleId(lStrUserRole);
						lStrPageName = "FolowUpClaimForm";
					}
				}

			} else if (lStrUserRole == null) {
				if (followUpVO.getNamRemarks() != null
						&& !followUpVO.getNamRemarks().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
					followUpForm.setShowNam(ClaimsConstants.YES);
				}
				if (followUpVO.getFcxRemark() != null
						&& !followUpVO.getFcxRemark().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
					followUpForm.setShowFcx(ClaimsConstants.YES);
				}
				if (followUpVO.getFtdRmks() != null
						&& !followUpVO.getFtdRmks().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
					followUpForm.setShowFtd(ClaimsConstants.YES);
				}
				if (followUpVO.getChRemark() != null
						&& !followUpVO.getChRemark().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
					followUpForm.setShowCh(ClaimsConstants.YES);
				}
				if (followUpVO.getAcoRemark() != null
						&& !followUpVO.getAcoRemark().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
					followUpForm.setShowAco(ClaimsConstants.YES);
				}
				request.setAttribute("disableAll", ClaimsConstants.YES);
			}
			/**
			 * get audit trail dtls for worklist
			 */

			List<ClaimsFlowVO> lstWorkFlow = followUpService
					.getAuditTrail(followUpVO);
			followUpForm.setLstworkFlow(lstWorkFlow);

			followUpForm.setRoleId(lStrUserRole);
			request.setAttribute("UserRole1", lStrUserRole);
			String caseEndTime = sds.format(new Date().getTime());
			if( lStrUserRole != null && !"".equalsIgnoreCase(lStrUserRole) && !(lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) || lStrUserRole.equalsIgnoreCase(config.getString("preauth_mithra_role"))))
			{
			loggingService.logTime("FollowUpLoadingTime", followUpVO.getCaseId(), caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
			}
			lStrPageName = "FolowUpClaimForm";
		}
		
		 /*added for updating ceo sent back cases */  /*by venkatesh*/
		if("updateSentBackCases".equalsIgnoreCase(lStrFlagStatus))
		{
		
		String followupId=request.getParameter("followupId");
		String sendBackFlag=request.getParameter("sendBackFlag");
		String remarks=request.getParameter("remarks");
		String followupStatus=request.getParameter("followupStatus");
		//String actionDone=request.getParameter("actionDone");
		String moduleType=request.getParameter("moduleType");
		ClaimsFlowVO claimFlowVO=new ClaimsFlowVO();
		String msg=null;
		String lStrUserGroup=null;
	
		claimFlowVO.setCaseId(followupId);
		
		claimFlowVO.setSendBackFlag(sendBackFlag);
		claimFlowVO.setRemarks(remarks);
		claimFlowVO.setModuleType(moduleType);
		claimFlowVO.setUserId(lStrEmpId);
		
		lStrUserGroup = config.getString("followup_sentBackStatus_"
				+ followupStatus);
		
		claimFlowVO.setRoleId(lStrUserGroup);
		
		msg=preauthService.updateSentBackClaims(claimFlowVO);
		
		request.setAttribute("AjaxMessage",msg);
		return mapping.findForward("ajaxResult");
		
		
		}
		
	if ("getFPPaymentRecrds".equalsIgnoreCase(lStrFlagStatus)) {
		FollowUpVO followUpVO = new FollowUpVO();
		lStrUserName = session.getAttribute("userName").toString();
		request.setAttribute("StatusList", followUpService.getFPCaseStatus(lStrUserName));
		String lStrSchemeType = request.getParameter("schemeType");
		
		if(request.getParameter("followUpStatus")!=null && !request.getParameter("followUpStatus").equalsIgnoreCase(ClaimsConstants.EMPTY) )
			followUpVO.setFollowUpStatus(request.getParameter("followUpStatus"));
		if(lStrUserName!=null && lStrUserName.equalsIgnoreCase("C075"))
			followUpVO.setFollowUpStatus("CD108");
		else{
			followUpVO.setFollowUpStatus(followUpForm.getFollowUpStatus());
			if(lStrUserName!=null && lStrUserName.equalsIgnoreCase("C075"))
				followUpVO.setFollowUpStatus("CD108");
		}
		
		if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase("")){
			if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON")))
				lStrSchemeType = config.getString("AP");
			else
				lStrSchemeType = lStrUserState;
		}
		followUpVO.setSchemeType(lStrSchemeType);

		String lStrUserGroup1 = null;
			String lStrUserGroup = config.getString("followupGroup_"
					+ followUpVO.getFollowUpStatus());
			for (LabelBean labelBean : groupsList) {
				if (labelBean.getID() != null
						&& labelBean.getID().equalsIgnoreCase(lStrUserGroup)) {
					lStrUserGroup1=lStrUserGroup;					
					break;
				} else
					lStrUserGroup1 = null;
			}
			if (groupsList == null || groupsList.isEmpty()) {
				lStrUserGroup1 = null;
			}
			lStrUserRole = lStrUserGroup1;
			
			
			if(lStrUserRole!=null && (config.getString("FIN.AccountsJEOGrp").equalsIgnoreCase(lStrUserRole)))
			{
				request.setAttribute("StatusList", followUpService.getFPCaseStatusByGrp(lStrUserRole));
			}
		   
		   followUpForm.setRoleId(lStrUserGroup);
		   
		   followUpVO.setCaseId(followUpForm.getCaseId());
		   followUpVO.setFollowUpId(followUpForm.getFollowUpId());
		   followUpVO.setFromDate(followUpForm.getFromDate());
		   followUpVO.setToDate(followUpForm.getToDate());
		   followUpVO.setPatName(followUpForm.getPatName());
		   followUpVO.setWapNo(followUpForm.getWapNo());
		   
		   
		   String cochlearSearch=request.getParameter("cochlearSearch");
		   if(cochlearSearch!=null && "Y".equalsIgnoreCase(cochlearSearch))
			   followUpVO.setCochelarSearch(cochlearSearch);
		   
		   if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
			   followUpForm.setShowScheme("show");
			   followUpForm.setSchemeType(lStrSchemeType);
			}
			else {
				followUpForm.setShowScheme("hide");
			}  
		   
		
		
		List<LabelBean> casesForPaymentList = followUpService.getFPCasesForPayments(followUpVO);
		followUpForm.setCasesForPaymentList(casesForPaymentList);
		if(casesForPaymentList.size()>0){
			// getting list of buttons for specific role and status
			List<LabelBean> buttonList=null;
			if(request.getParameter("followUpStatus")!=null &&
					(request.getParameter("followUpStatus").equalsIgnoreCase(config.getString("CochlearACOApproved")) ||
							request.getParameter("followUpStatus").equalsIgnoreCase(config.getString("CochlearTrustSBUpdApproved"))))
				buttonList = commonService.getDynamicWrkFlowDtls(
						followUpVO.getFollowUpStatus(), lStrUserRole, ClaimsConstants.FOLLOW_UP,
						ClaimsConstants.COCHLEAR_FOLLOWUP_CLAIM);
			else
				buttonList = commonService.getDynamicWrkFlowDtls(
					followUpVO.getFollowUpStatus(), lStrUserRole, ClaimsConstants.FOLLOW_UP,
					ClaimsConstants.FOLLOWUP_PAYMENT);
			followUpForm.setButtonList(buttonList);

			if (buttonList.size() == 0)
				request.setAttribute("disableAll", ClaimsConstants.YES);
			
			request.setAttribute("buttons",buttonList);
		}
		followUpForm.setGenFlag(ClaimsConstants.Y);
		followUpForm.setFollowUpStatus(followUpVO.getFollowUpStatus());
		if(followUpVO.getFollowUpStatus().equalsIgnoreCase(config.getString("EHF.FollowUP.ClaimSentForPayment")) ||
				followUpVO.getFollowUpStatus().equalsIgnoreCase(config.getString("EHF.FollowUP.ClaimPaid")))
			{
				if(followUpVO.getCochelarSearch()!=null && "Y".equalsIgnoreCase(followUpVO.getCochelarSearch()))
					followUpForm.setFollowUpStatus(config.getString("EHF.CochlearCEOID."+followUpVO.getFollowUpStatus()));
			}
		
		followUpForm.setFollowUpId(followUpVO.getFollowUpId());
		
		String genFlag=request.getParameter("genFlag");
		List<ErrPaymentReportVO> expList=new ArrayList<ErrPaymentReportVO>();
		ErrPaymentReportVO row1=new ErrPaymentReportVO();
		row1.setSno("SL No.");
		row1.setErrCaseId("FollowUp Id");
		row1.setCaseNo("Case No");
		row1.setPatName("Patient Name");
		row1.setHospName("Hospital Name");
		row1.setHospAccountName("Hospital Account Name");
		row1.setWapNo("Health Card No");
		row1.setApprovedAmount("Claim Amount");
		row1.setDistrict("District");
		expList.add(row1);
		int i=0;
		for(LabelBean row:casesForPaymentList)
		{
			row1=new ErrPaymentReportVO();
			row1.setSno(++i+"");
			row1.setErrCaseId(row.getLEVELID());
			row1.setCaseNo(row.getID());
			row1.setPatName(row.getVALUE());
			row1.setHospName(row.getLVL());
			row1.setHospAccountName(row.getSUBNAME());
			row1.setWapNo(row.getUNITID());
			row1.setApprovedAmount(row.getWFTYPE());
			row1.setDistrict(row.getEMPNAME());
			expList.add(row1);
		}
		
		
		
		if(genFlag!=null && genFlag.equalsIgnoreCase("E"))
		{
			String lStrDirPath=config.getString("PAYMENT.MapPath");
	         	String lStrFileName=config.getString("PAYMENT.MapPath")+config.getString("SLASH_VALUE")+config.getString("PAYMENT.FollowUp")+config.getString("DOT_VALUE")+config.getString("REPORT.XlsExtn");

	         	//Creates file in EHFTemp folder of client machine 
	         	File xlFile;
				try {
					xlFile = createFile(lStrDirPath,lStrFileName);
					//ExcelWriter.prepareExl(xlFile,expList);
					//byte[] lbBytes= ExcelWriter.getBytesFromFile(xlFile);
					//Setting request and response objects
					//request.setAttribute("File", lbBytes);          
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         	

				       
				response.setContentType(config.getString("REPORT.ExcelContentType"));
				response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName); 
				return mapping.findForward("openFile"); 
		}
		if(genFlag!=null && genFlag.equalsIgnoreCase("P"))
		{
			String lStrDirPath=config.getString("PAYMENT.MapPath");
	         	String lStrFileName=config.getString("PAYMENT.MapPath")+config.getString("SLASH_VALUE")+config.getString("PAYMENT.FollowUp")+config.getString("DOT_VALUE")+config.getString("REPORT.PdfExtn");

	         	//Creates file in EHFTemp folder of client machine 
	         	File xlFile;
				try {
					xlFile = createFile(lStrDirPath,lStrFileName);
					PdfGenerator.preparePdf(xlFile,expList,"TDS Payments Report");
					byte[] lbBytes= PdfGenerator.getBytesFromFile(xlFile);

					//Setting request and response objects
					request.setAttribute("File", lbBytes);  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				               
				response.setContentType(config.getString("REPORT.PdfContentType"));
				response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName); 
				return mapping.findForward("openFile"); 
		}
		
		
		lStrPageName = "fpclaimsPayment";
	}
	if("sendBackToCh".equalsIgnoreCase(lStrFlagStatus)){
		
		FollowUpVO followUpVO = new FollowUpVO();
		followUpVO.setFollowUpId(request.getParameter("FollowUpId"));
		if(followUpVO.getFollowUpId()==null)
			followUpVO.setFollowUpId(followUpForm.getFollowUpId());
		followUpVO.setFollowUpStatus(followUpForm.getFollowUpStatus());
		followUpVO.setActionDone(request.getParameter("actionDone"));
		followUpVO.setUserId(lStrUserId);
		followUpVO.setRoleId(followUpForm.getRoleId());
		followUpVO.setCeoRemark(followUpForm.getCeoRemark());
		
		
		followUpVO = followUpService.saveFollowUpClaim(followUpVO);
		if ( !followUpVO.getMsg().equalsIgnoreCase(ClaimsConstants.Failure)) {
			request.setAttribute("saveMsg", followUpVO.getMsg());
		}
					
		
		lStrFlagStatus = "getFpCaseDetails";
	}
	if("getFpCaseDetails".equalsIgnoreCase(lStrFlagStatus)){
		String followUpId = request.getParameter("FollowUpId");
		String caseId = request.getParameter("caseId");
		FollowUpVO followUpVO = new FollowUpVO(); 
		CommonDtlsVO commonDtlsVO = followUpService.getFPDtlsForPayment(caseId,followUpId);
		followUpVO.setCaseId(commonDtlsVO.getCASEID());
		followUpForm.setCaseId(followUpVO.getCaseId());
		followUpForm.setFollowUpStatus(commonDtlsVO.getCASESTAT());
		followUpVO.setFollowUpStatus(commonDtlsVO.getCASESTAT());
		followUpVO.setFollowUpId(followUpId);
		followUpForm.setFollowUpId(followUpVO.getFollowUpId());
		String lStrUserGroup1=null;
		String lStrUserGroup = config.getString("followupGroup_"
				+ followUpVO.getFollowUpStatus());
		for (LabelBean labelBean : groupsList) {
			if (labelBean.getID() != null
					&& labelBean.getID().equalsIgnoreCase(lStrUserGroup)) {
				lStrUserGroup1=lStrUserGroup;
				break;
			} else
				lStrUserGroup1 = null;
		}
		if (groupsList == null || groupsList.isEmpty()) {
			lStrUserGroup1 = null;
		}
		lStrUserRole = lStrUserGroup1;
		followUpForm.setRoleId(lStrUserRole);
		
		
		
		   /**
			 * get audit trail dtls for worklist
			 */

			List<ClaimsFlowVO> lstWorkFlow = followUpService
					.getAuditTrail(followUpVO);
			followUpForm.setLstworkFlow(lstWorkFlow);
		request.setAttribute("patCommonDtls",commonDtlsVO);
		
		// getting list of buttons for specific role and status
		List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
				followUpVO.getFollowUpStatus(), lStrUserRole, ClaimsConstants.FOLLOW_UP,
				ClaimsConstants.FOLLOWUP_CLAIM);
		followUpForm.setButtonList(buttonList);
		request.setAttribute("buttons",buttonList);
		
		request.setAttribute("UserRole", followUpForm.getRoleId());  
		
		lStrPageName = "viewFPDtlsPayPage";
	}
	if("changeFPClaimForCases".equalsIgnoreCase(lStrFlagStatus)){
		String lResult = ClaimsConstants.EMPTY;
		String followUpStatus = request.getParameter("followUpStatus");
		
		request.setAttribute("flpUpStatus",followUpStatus);
		ClaimsFlowVO claimFlowVO = new ClaimsFlowVO(); 
		claimFlowVO.setCaseSelected(followUpForm.getCaseSelected());
		claimFlowVO.setRoleId(followUpForm.getRoleId());
		claimFlowVO.setUserId(lStrUserId);
		claimFlowVO.setActionDone(request.getParameter("actionDone"));
		claimFlowVO.setCaseStat(followUpStatus);
		claimFlowVO.setSchemeType(followUpForm.getSchemeType());
		if(followUpForm.getPaymentRadio()!=null)
			{
				if(followUpForm.getPaymentRadio().equalsIgnoreCase("CD97"))
					claimFlowVO.setSendBackRmrks(followUpForm.getSendBackRmrks());
			}
		
		lResult = followUpService.updateFPClaimStatusReady(claimFlowVO);
		
		if ("0".equals(lResult)) {
			lResult = ClaimsConstants.MSG_0;
		} else if ("2".equals(lResult)) { 
			lResult = ClaimsConstants.MSG_2;
		} else if ("1".equals(lResult)) {
			lResult = ClaimsConstants.MSG_1;
		} else if ("5".equals(lResult)) {
			lResult = ClaimsConstants.MSG_4;
		} else {
			lResult += ClaimsConstants.MSG_3;
		}
		request.setAttribute("saveMsg", lResult);
		request.setAttribute("schemeTypeSel", claimFlowVO.getSchemeType());
		request.setAttribute("StatusList", followUpService.getFPCaseStatus(lStrUserName));
				
		lStrPageName = "fpclaimsPayment";
	}
	if("getPayFPClaimForCases".equalsIgnoreCase(lStrFlagStatus)){
		HashMap hParamMap = new HashMap();
		Map lParamMap = new HashMap();
		String lResult=ClaimsConstants.EMPTY;
		String followUpStatus = request.getParameter("followUpStatus");
		ClaimsFlowVO claimFlowVO = new ClaimsFlowVO(); 
		claimFlowVO.setCaseSelected(followUpForm.getCaseSelected());
		claimFlowVO.setRoleId(followUpForm.getRoleId());
		claimFlowVO.setUserId(lStrUserId);
		claimFlowVO.setActionDone(request.getParameter("actionDone"));
		
		hParamMap.put("claimFlowVO", claimFlowVO);
		hParamMap.put("caseStatus",followUpStatus);
		hParamMap.put("CRTUSER",lStrUserId);
		hParamMap.put("SentStatus",ClaimsConstants.SENT);
		hParamMap.put("SharePath",config.getString("mapPath"));
		
		hParamMap.put("TransReadyStatus",ClaimsConstants.TransReadyStatus); 
		hParamMap.put("TDS_CASE_TYPE",config.getString("EHF.Claims.Trust"));
		hParamMap.put("PaymentType",ClaimsConstants.FollowUp);
		
		try {
			lParamMap = followUpService.updateFPClaimStatus(hParamMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(lParamMap.size()!=0   )
        {
          lResult=(String)lParamMap.get("Message");            
        }
		if("0".equals(lResult))
        {
			lResult+=ClaimsConstants.MSG_0;
        }
        else if("2".equals(lResult))
        {
        	lResult+=ClaimsConstants.MSG_2;
        }
        else if("1".equals(lResult))
        {
        	lResult+=ClaimsConstants.MSG_1;
        }
        else{
        	lResult+=ClaimsConstants.MSG_3;
        }
		request.setAttribute("saveMsg",lResult);
		request.setAttribute("StatusList", followUpService.getFPCaseStatus(lStrUserName));
		
		if (config.getBoolean("EmailRequired")) 
        {
        	String mailId=null;
        	if(lParamMap.get("failedCaseIdInList")!=null)
        	{
        	mailId="ishank.paharia@tcs.com";
        	String[] emailToArray = {mailId};
        	EmailVO emailVO = new EmailVO();
			emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
			emailVO.setTemplateName(config.getString("EhfFailedCasePayment"));
			emailVO.setFromEmail(config.getString("emailFrom"));
			emailVO.setToEmail(emailToArray);
			emailVO.setSubject(config.getString("failedCasesPayment"));
			Map<String, String> model = new HashMap<String, String>();
			model.put("caseNo", (String) lParamMap.get("failedCaseIdInList"));
			commonService.generateMail(emailVO, model);
        	}
			}	
		
		lStrPageName = "fpclaimsPayment";
		
	}
	
	if("viewClinicalData".equalsIgnoreCase(lStrFlagStatus)){
		FollowUpVO followUpVO = new FollowUpVO(); 
		followUpVO.setCaseId(followUpForm.getCaseId());
		followUpVO.setFollowUpId(request.getParameter("followUpId"));
		

		List<FollowUpVO>listClinicalNotes = followUpService.getClinicalData(followUpVO);
		request.setAttribute("followUpVOList",listClinicalNotes);
		//request.setAttribute("followUpVO",followUpVO);
		/*
		List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
		drugsList = followUpService.getDrugsDtls(followUpVO);
		request.setAttribute("drugsList",drugsList);
		
		List<com.tcs.Webframework.attachments.vo.AttachmentVO> lstAttachments = followUpService
		.getFollowUPAttach(followUpVO.getFollowUpId());
		request.setAttribute("attachList",lstAttachments);
		*/
		lStrPageName= "viewClinicalData";
	}
	if("printDTRS".equalsIgnoreCase(lStrFlagStatus)){
		FollowUpVO followUpVO = new FollowUpVO(); 
		followUpVO.setCaseId(followUpForm.getCaseId());
		String scheme_id= request.getParameter("scheme_id");
		
		followUpVO.setFollowUpId(request.getParameter("followUpId"));
		
		LabelBean dtrsData=followUpService.getDTRSData(followUpForm.getCaseId());
		request.setAttribute("dtrsData",dtrsData);
		
		List<com.ahct.attachments.vo.AttachmentVO> lstAttachments = followUpService
		.getFollowUPAttach(followUpVO.getFollowUpId());
		
		String crtUsr=followUpService.getCrtUsrMedco(request.getParameter("followUpId"));
		request.setAttribute("medcoName", crtUsr);
		request.setAttribute("scheme_id", scheme_id);
		request.setAttribute("attachList",lstAttachments);
		followUpForm.setLstAttachments(lstAttachments);
		
		
		Date date =new Date();
		SimpleDateFormat sdsf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
		request.setAttribute("currentDateTime", sdsf.format(date));
		
		lStrPageName= "printDTRS";
	}
	if("printPrescription".equalsIgnoreCase(lStrFlagStatus)){
		FollowUpVO followUpVO = new FollowUpVO(); 
		followUpVO.setCaseId(followUpForm.getCaseId());
		followUpVO.setFollowUpId(request.getParameter("followUpId"));
		if(request.getParameter("followUpId")!=null && !"".equalsIgnoreCase(request.getParameter("followUpId")))
			{
				String schemeId=followUpService.getSchemeId(request.getParameter("followUpId"));
				if(schemeId!=null && !"".equalsIgnoreCase(schemeId))
					request.setAttribute("followUpSchemeId", schemeId );
			}
		String patientId=followUpService.getPatientIdFromCaseId(followUpForm.getCaseId());
		if(patientId!=null||!"".equalsIgnoreCase(patientId))
			{	PatientVO patientDtls=followUpService.getPatientDtls(patientId);
				followUpForm.setPatient(patientDtls);
				request.setAttribute("patient",patientDtls);
			}
		List<FollowUpVO> listClinicalNotes = followUpService.getClinicalData(followUpVO);
		if(listClinicalNotes!=null && listClinicalNotes.size()>0)
		followUpVO.setCRTUSR(preauthService.getEmpNameById(listClinicalNotes.get(0).getCRTUSR()));
		request.setAttribute("ramcoName",followUpVO.getCRTUSR());
		followUpForm.setFollowUpData(listClinicalNotes);
		/*
		List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
		drugsList = followUpService.getDrugsDtls(followUpVO);
		followUpForm.setDrugsList(drugsList);
		request.setAttribute("drugsList",drugsList);
		
		List<com.tcs.Webframework.attachments.vo.AttachmentVO> lstAttachments = followUpService
		.getFollowUPAttach(followUpVO.getFollowUpId());
		request.setAttribute("attachList",lstAttachments);
		followUpForm.setLstAttachments(lstAttachments);
		*/
		String scheme_id= request.getParameter("scheme_id");
		request.setAttribute("scheme_id", scheme_id);
		Date date =new Date();
		SimpleDateFormat sdsf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
		request.setAttribute("currentDateTime", sdsf.format(date));
		lStrPageName= "printPrescription";
	}
	if(lStrFlagStatus!= null && "checkMandatoryAttch".equals(lStrFlagStatus)) 
    {
    	 String followUpId = request.getParameter("followUpId");
    	 String attachType = request.getParameter("attachType");
    	 String msg = followUpService.checkMandatoryAttch(followUpId,attachType);
    	 request.setAttribute("AjaxMessage",msg);
    	 return mapping.findForward("ajaxResult");	
    }
	if("getDrugSubList".equalsIgnoreCase(lStrFlagStatus))
	{
		List<String> drugSubList=null;
		String drugTypeId=null;
		if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
		{
			drugTypeId=request.getParameter("lStrDrugTypeId");        		
		}
		drugSubList=followUpService.getDrugSubList(drugTypeId);
		if (drugSubList != null && drugSubList.size() > 0)
			request.setAttribute("AjaxMessage", drugSubList);
		lStrPageName="ajaxResult";
	}
	if ("getDrugList".equalsIgnoreCase(lStrFlagStatus))
	{
		List<String> drugListArray = new ArrayList<String>();
		List<LabelBean> drugList=null;
		String drugSubTypeId=null;
		if(request.getParameter("lStrDrugSubTypeId")!=null && !request.getParameter("lStrDrugSubTypeId").equals(""))
		{
			drugSubTypeId=request.getParameter("lStrDrugSubTypeId");        		
		}
		
		try
		{
			drugList=commonService.getAsriDrugs(drugSubTypeId);
			for (LabelBean labelBean: drugList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
				{
					drugListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
				}
			}
			if (drugListArray != null && drugListArray.size() > 0)

				request.setAttribute("AjaxMessage", drugListArray);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		lStrPageName="ajaxResult";	
	}
	if("getDrugDetails".equalsIgnoreCase(lStrFlagStatus))
	{
		String drugCode=null;
		if(request.getParameter("lStrDrugCode")!=null && !request.getParameter("lStrDrugCode").equals(""))
		{
			drugCode=request.getParameter("lStrDrugCode");        		
		}
		String drugDetails=followUpService.getDrugDetails(drugCode);

		request.setAttribute("AjaxMessage",drugDetails);
		lStrPageName="ajaxResult";	
	}
	if(lStrFlagStatus!=null && "getGenInvestList".equalsIgnoreCase(lStrFlagStatus)){
		String lStrBlockId=request.getParameter("lStrBlockId");			
		List<String> symptomList=null;
		symptomList=followUpService.getInvestigations(lStrBlockId);
		if (symptomList != null && symptomList.size() > 0)
			request.setAttribute("AjaxMessage", symptomList);

		lStrPageName="ajaxResult";
	}
	return mapping.findForward(lStrPageName);
}
	
	
	//File creation 
	private File createFile(String lStrDirPath,String lStrFileName) throws IOException 
	{
		//Making Directory		  
		File file = new File(lStrDirPath);
		if (!file.exists()) 
		{
			file.mkdir();
		}
		File lfile = new File(lStrFileName);
		if(!lfile.exists())
		{
			lfile.createNewFile();
		}
		else
		{
			lfile.delete();
			lfile.createNewFile();
		}
		return lfile;
		
	}
	private String removeNullString(String data)
	{
		if(data==null)
			return "";
		else
			return data;
	}

}
