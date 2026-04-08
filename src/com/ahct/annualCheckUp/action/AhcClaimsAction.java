package com.ahct.annualCheckUp.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.ahct.annualCheckUp.form.AhcClaimsForm;
import com.ahct.annualCheckUp.service.AhcClaimsService;
import com.ahct.annualCheckUp.service.AnnualCheckUpService;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.claimPaymentReportVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ExcelWriter;
import com.ahct.common.util.PdfGenerator;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAhcCaseClaim;
import com.ahct.model.EhfAhcPatientTest;
import com.ahct.model.EhfAhcTdChklst;
import com.ahct.model.EhfAhcexChklst;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.model.EhfAnnualPatientDtls;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;


public class AhcClaimsAction extends Action{
	private final static Logger GLOGGER = Logger.getLogger ( AhcClaimsAction.class ) ;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
			{
				response.setHeader("pragma","no-cache");
				response.setDateHeader("Expires", 0);    
				HttpSession session = request.getSession ( false ) ;
				AhcClaimsForm ahcClaimsForm=(AhcClaimsForm)form;
				String lStrActionVal=null, lstrResultPage=null;
				lStrActionVal=request.getParameter("actionFlag");
				ConfigurationService configurationService = (ConfigurationService) ServiceFinder
				.getContext(request).getBean("configurationService");
				Configuration config = configurationService.getConfiguration();
				String lStrResultPage = HtmlEncode.verifySession(request, response);
		        if (lStrResultPage.length() > 0)
		        {
		            return mapping.findForward("sessionExpire");
		        }
				CommonService commonService = (CommonService) ServiceFinder.getContext(
				request).getBean("commonService");
				AhcClaimsService ahcClaimsService = (AhcClaimsService) ServiceFinder.getContext(
				request).getBean("ahcClaimsService");
				AnnualCheckUpService annualCheckUpService = (AnnualCheckUpService) ServiceFinder.getContext(
						request).getBean("annualCheckUpService");
				List<LabelBean> grpList=null;
				List<String> lStrgrpList=new ArrayList<String>();
				String roleId = null,lStrUserId=null;
				if (session.getAttribute("EmpID").toString() != null) {
					lStrUserId = session.getAttribute("EmpID").toString();
				}
				if(session.getAttribute("groupList").toString()!=null)
				{
					grpList=(List<LabelBean>)session.getAttribute("groupList");
				}
				for(LabelBean labelBean:grpList)
				{
					lStrgrpList.add(labelBean.getID());
				}
				if(lStrgrpList.contains(config.getString("Group.Mithra")))
				{
					roleId=config.getString("Group.Mithra");
				}
				else if(lStrgrpList.contains(config.getString("Group.Medco")))
				{
					roleId=config.getString("Group.Medco");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-EX")))
				{
					roleId=config.getString("Group.AHC-EX");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-TD")))
				{
					roleId=config.getString("Group.AHC-TD");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-CH")))
				{
					roleId=config.getString("Group.AHC-CH");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-ACO")))
				{
					roleId=config.getString("Group.AHC-ACO");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-EO")))
				{
					roleId=config.getString("Group.AHC-EO");
				}
				else
				{
					roleId=lStrgrpList.get(0);
				}
				String casesSearchFlag =request.getParameter("casesSearchFlag");
				request.setAttribute("casesSearchFlag", casesSearchFlag);
				
				if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("claimsPage")){
					
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
					String ahcId= request.getParameter("ahcId");
					//String configRole=config.getString("Group.AHC-TD");
					EhfAnnualCaseDtls caseDtls=ahcClaimsService.getAhcStatusDtls(ahcId);
					EhfAhcCaseClaim ahcCaseClaim=ahcClaimsService.getAhcCaseClaimDtls(ahcId);
					request.setAttribute("ahcCaseSchemeId",caseDtls.getSchemeId());
					
					ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
					ahcClaimsForm.setPackageAmt(caseDtls.getTotPckgAmt());
					claimFlowVO.setCaseId(ahcId);
					claimFlowVO.setCaseStat(caseDtls.getAhcStatus());
					if(caseDtls.getAhcRegnDate()!=null)
						ahcClaimsForm.setRegDate(sdf1.format(caseDtls.getAhcRegnDate()));
					if(caseDtls.getAhcClmSubDt()!=null)
						ahcClaimsForm.setClaimDt(sdf1.format(caseDtls.getAhcClmSubDt()));
					else
						ahcClaimsForm.setClaimDt(sdf.format(new Date()));
					if(caseDtls.getBillDate()!=null){
						
						ahcClaimsForm.setBillDt(sdf1.format(caseDtls.getBillDate()));
					}
					if(caseDtls.getClaimInitAmount()!=null){
						ahcClaimsForm.setBillAmt(String.valueOf(caseDtls.getClaimInitAmount()));
						ahcClaimsForm.setBillAmt1(String.valueOf(caseDtls.getClaimInitAmount()));
					}
					if(caseDtls.getClaimInitRemarks()!=null)
						ahcClaimsForm.setMedcoRemarks(caseDtls.getClaimInitRemarks());
					claimFlowVO.setRoleId(roleId);
					claimFlowVO.setUserId(lStrUserId);
					if(casesSearchFlag==null || (casesSearchFlag!=null && !casesSearchFlag.equalsIgnoreCase("Y")))
						{
					List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
							caseDtls.getAhcStatus(), roleId,
							"OPERATIONS",
							"AHC");
					claimFlowVO.setButtonList(buttonList);
						}
					List<ClaimsFlowVO> lstWorkFlow = ahcClaimsService.getAuditTrail(ahcId);
					/*if(roleId!=null && ((roleId.equalsIgnoreCase(config.getString("Group.AHC-TD"))) ||
							(roleId.equalsIgnoreCase(config.getString("Group.AHC-CH"))) ||
							(roleId.equalsIgnoreCase(config.getString("Group.AHC-ACO"))))
					){*/
						EhfAhcexChklst exeRmrks=ahcClaimsService.getExeRemarks(ahcId);
						if(exeRmrks!=null){
							ahcClaimsForm.setAcquaintanceAttached(exeRmrks.getAcquaintanceAttached());
							ahcClaimsForm.setAcquaintanceRemarks(exeRmrks.getAcquaintanceRemarks());
							ahcClaimsForm.setPhotoAttached(exeRmrks.getPhotoYn());
							ahcClaimsForm.setPhotoRemarks(exeRmrks.getPhotoRemarks());
							ahcClaimsForm.setPhotoMatch(exeRmrks.getPhotoMatching());
							ahcClaimsForm.setPhotoMatchRemarks(exeRmrks.getPhotomatchRemarks());
							ahcClaimsForm.setReportsAttached(exeRmrks.getReportsAttached());
							ahcClaimsForm.setReportsRemarks(exeRmrks.getReportsRemarks());
							ahcClaimsForm.setAHCCEXRemarks(exeRmrks.getCexRemarks());
							
							
						}
				//	}
					/*if(roleId!=null && ((roleId.equalsIgnoreCase(config.getString("Group.AHC-CH"))) ||
							(roleId.equalsIgnoreCase(config.getString("Group.AHC-ACO"))))
					){*/
						EhfAhcTdChklst tdRmrks=ahcClaimsService.getTdRemarks(ahcId);
						if(tdRmrks!=null){
							ahcClaimsForm.setExeRemarksVerified(tdRmrks.getExeRmksVerified());
							ahcClaimsForm.setExeVerifyRemarks(tdRmrks.getExeRmksRemarks());
							ahcClaimsForm.setFinalApproveAmt(tdRmrks.getFinalApproveAmt());
							ahcClaimsForm.setAHCCTDRemarks(tdRmrks.getRemarks());
							
							
						}
					//}
						if(ahcCaseClaim!=null)
							{
								if(ahcCaseClaim.getNamRemarks()!=null)
									ahcClaimsForm.setAHCCNAMRemarks(ahcCaseClaim.getNamRemarks());
								if(ahcCaseClaim.getAcoRemarks()!=null)
									ahcClaimsForm.setAHCACORemarks(ahcCaseClaim.getAcoRemarks());
								if(ahcCaseClaim.getChAprvAmt()!=null)
									ahcClaimsForm.setAHCCHAmt(ahcCaseClaim.getChAprvAmt().toString());
								if(ahcCaseClaim.getChRemarks()!=null)
									ahcClaimsForm.setAHCCHRemarks(ahcCaseClaim.getChRemarks());
								if(ahcCaseClaim.getCtdUpdRemarks()!=null)
									ahcClaimsForm.setMedcoCTDUpdRemarks(ahcCaseClaim.getCtdUpdRemarks());
								 if(ahcCaseClaim.getChUpdRemarks()!=null)
									 ahcClaimsForm.setMedcoCHUpdRemarks(ahcCaseClaim.getChUpdRemarks());
							}
					
					
					if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.AHC-ACO")))
					{
						
						if(caseDtls.getPckAppvAmt()!=null)
							ahcClaimsForm.setChApproveAmt(String.valueOf(caseDtls.getPckAppvAmt()));
					}
					
					ahcClaimsForm.setLstworkFlow(lstWorkFlow);
					ahcClaimsForm.setStatus(caseDtls.getAhcStatus());
					//ahcClaimsForm.setStatus("CD375");
					ahcClaimsForm.setClaimInfo(claimFlowVO);
					request.setAttribute("status", caseDtls.getAhcStatus());
				//	request.setAttribute("status", "CD375");
					request.setAttribute("UserRole", roleId);
					lstrResultPage="claimsPage";
					
				}
				else if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("checkMandatoryAttch")){
					 String ahcId = request.getParameter("ahcId");
			    	 String attachType = request.getParameter("attachType");
			    	 String msg = ahcClaimsService.checkMandatoryAttch(ahcId,attachType);
			    	 request.setAttribute("AjaxMessage",msg);
			    	 return mapping.findForward("ajaxResult");	
					
				}
				else if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("saveClaimDtls")){
					SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
					 String ahcId = ahcClaimsForm.getAhcId();
					 String actionDone = request.getParameter("actionDone");
					 ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
					 claimFlowVO.setTotalClaim(ahcClaimsForm.getBillAmt());
					 if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.AHC-EX"))){
						 claimFlowVO.setPhotoMatch(ahcClaimsForm.getPhotoMatch());
						 claimFlowVO.setPhotoMatchRemarks(ahcClaimsForm.getPhotoMatchRemarks());
						 claimFlowVO.setPhotoAttached(ahcClaimsForm.getPhotoAttached());
						 claimFlowVO.setPhotoRemarks(ahcClaimsForm.getPhotoRemarks());
						 claimFlowVO.setAcquaintanceAttached(ahcClaimsForm.getAcquaintanceAttached());
						 claimFlowVO.setAcquaintanceRemarks(ahcClaimsForm.getAcquaintanceRemarks());
						 claimFlowVO.setReportsAttached(ahcClaimsForm.getReportsAttached());
						 claimFlowVO.setReportsRemarks(ahcClaimsForm.getReportsRemarks());
					 }
					 else if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.AHC-TD"))){
						 claimFlowVO.setExeRemarksVerified(ahcClaimsForm.getExeRemarksVerified());
						 claimFlowVO.setExeVerifyRemarks(ahcClaimsForm.getExeVerifyRemarks());
						 claimFlowVO.setFinalApproveAmt(ahcClaimsForm.getFinalApproveAmt());
						 claimFlowVO.setTotalClaim(ahcClaimsForm.getFinalApproveAmt());
						 claimFlowVO.setRemarks(ahcClaimsForm.getRemarks());
						
					 }
					 else if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.AHC-CH"))){
						 claimFlowVO.setFinalApproveAmt(ahcClaimsForm.getChApproveAmt());
					 }
					 claimFlowVO.setCaseId(ahcId);
					 claimFlowVO.setActionDone(actionDone);
					 
					 claimFlowVO.setPackAmt(ahcClaimsForm.getPackageAmt());
					 claimFlowVO.setCurrStatus(ahcClaimsForm.getStatus());
					 claimFlowVO.setBillDt(ahcClaimsForm.getBillDt());
					 if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
						 claimFlowVO.setBillAmt(ahcClaimsForm.getBillAmt());
						 if(ahcClaimsForm.getMedcoCTDUpdRemarks()!=null)
							 claimFlowVO.setMedcoCTDUpdRemarks(ahcClaimsForm.getMedcoCTDUpdRemarks());
						 if(ahcClaimsForm.getMedcoCHUpdRemarks()!=null)
							 claimFlowVO.setMedcoCHUpdRemarks(ahcClaimsForm.getMedcoCHUpdRemarks());
					 }
					 else{
						 claimFlowVO.setBillAmt(ahcClaimsForm.getBillAmt1());
					 }
					 
					 claimFlowVO.setRoleId(roleId);
					 claimFlowVO.setUserId(lStrUserId);
					 claimFlowVO.setRemarks(ahcClaimsForm.getRemarks());
					 claimFlowVO.setAcoRemark(ahcClaimsForm.getMedcoRemarks());
					 String msg=ahcClaimsService.saveAhcClaim(claimFlowVO);
					
					 EhfAnnualPatientDtls emailProps= annualCheckUpService.getPatientDtls(ahcId);
					 request.setAttribute("ahcCaseSchemeId",emailProps.getSchemeId());
					 if(msg!=null && "AHC Claim Details updated successfully".equalsIgnoreCase(msg)){
						 if (config.getBoolean("EmailRequired")) 
							{
								
								if(emailProps.getEmailid()!=null && !emailProps.getEmailid().equals(""))
								{
									
									String[] emailToArray = {emailProps.getEmailid()};
									EmailVO emailVO = new EmailVO();
									emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
									emailVO.setTemplateName(config.getString("AhcClaimInitTemplate"));
									emailVO.setFromEmail(config.getString("emailFrom"));
									emailVO.setToEmail(emailToArray);
									emailVO.setSubject(config.getString("AhcClaimInitSubject"));
									Map<String, String> model = new HashMap<String, String>();
									model.put("patientName", emailProps.getName().trim());
									model.put("ahcNo", emailProps.getAhcId().trim());
									model.put("statusDate",sdf.format(new Date()));
									commonService.generateMail(emailVO, model);
								}
								else{
									System.out.println("Email Id not available for this employee--AhcClaimsAction--saveClaimDtls");
								}
							}
					 }
					 ahcClaimsForm.setMsg(msg);
					 request.setAttribute("saveMsg", msg);
					 request.setAttribute("ahcId", ahcId);
					 lstrResultPage="claimsPage";
				}
				else if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("claimPayments")){
					session= request.getSession(false);
					String scheme=null;
					if(session.getAttribute("userState")!=null){
						scheme=(String)session.getAttribute("userState");
					}
					if(scheme!=null && scheme.equalsIgnoreCase("CD203"))
						ahcClaimsForm.setShowScheme("show");
					
					String genFlag = request.getParameter("genFlag");
					
					ClaimsFlowVO claimsFlowVO = new ClaimsFlowVO();
					String lStrSchemeType = request.getParameter("schemeType");
					
					if(lStrSchemeType==null || "".equalsIgnoreCase(lStrSchemeType)){
						if(session.getAttribute("userState")!=null)
						lStrSchemeType=session.getAttribute("userState").toString();
					}
					
					if (!request.getParameter("status").equalsIgnoreCase("")
							&& request.getParameter("status") != null){
						claimsFlowVO.setCaseStat(request.getParameter("status"));
					}
					
					else {
						claimsFlowVO.setCaseStat(ahcClaimsForm.getStatus());
						
					}
					if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.AHC-ACO")))
						claimsFlowVO.setCaseStat(config.getString("AHC_CLM_REJ_BANK"));
					else if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.AHC-EO")))
						claimsFlowVO.setCaseStat(config.getString("AHC-ACO-APPRV"));
					request.setAttribute("status", claimsFlowVO.getCaseStat());
					ahcClaimsForm.setRoleId(roleId);			
					claimsFlowVO.setCaseId(ahcClaimsForm.getAhcId());
					claimsFlowVO.setFromDate(ahcClaimsForm.getFromDate());
					claimsFlowVO.setToDate(ahcClaimsForm.getToDate());
					claimsFlowVO.setPatName(ahcClaimsForm.getName());
					claimsFlowVO.setWapNo(ahcClaimsForm.getCardNo());
					claimsFlowVO.setSchemeType(lStrSchemeType);
					
					List<LabelBean> casesForPaymentList = ahcClaimsService
							.getCasesForPayments(claimsFlowVO);
					ahcClaimsForm.setCasesForPaymentList(casesForPaymentList);
					if (casesForPaymentList.size() > 0) {
						// getting list of buttons for specific role and status
						List<LabelBean> buttonList = commonService
								.getDynamicWrkFlowDtls(claimsFlowVO.getCaseStat(),
										roleId,
										"OPERATIONS",
										"AHC");

						claimsFlowVO.setButtonList(buttonList);
						request.setAttribute("buttons", buttonList);
					}

					ahcClaimsForm.setGenFlag(ClaimsConstants.Y);
					ahcClaimsForm.setStatus(claimsFlowVO.getCaseStat());
		            // defining number of cases for payment
					
						request.setAttribute("pageSize", config.getString("EHF.AHC.PageSize"));
		            //for excel 
					if(genFlag != null){
					List<claimPaymentReportVO> expList = new ArrayList<claimPaymentReportVO>();
					claimPaymentReportVO row1 = new claimPaymentReportVO();
					row1.setSno("SL No.");
					row1.setCaseNo("Case No");
					row1.setPatName("Patient Name");
					row1.setHospName("Hospital Name");
					row1.setHospAccountName("Hospital Account Name");
					row1.setWapNo("Health Card No");
					row1.setApprovedAmount("Claim Amount");
					row1.setDistrict("District");
					expList.add(row1);
					int i = 0;
					for (LabelBean row : casesForPaymentList) {
						row1 = new claimPaymentReportVO();
						row1.setSno(++i + ClaimsConstants.EMPTY);
						row1.setCaseNo(row.getID());
						row1.setPatName(row.getVALUE());
						row1.setHospName(row.getLVL());
						row1.setHospAccountName(row.getSUBNAME());
						row1.setWapNo(row.getUNITID());
						row1.setApprovedAmount(row.getWFTYPE());
						row1.setDistrict(row.getEMPNAME());
						expList.add(row1);
					}

					if (genFlag != null && genFlag.equalsIgnoreCase("E")) {
						String lStrDirPath = config.getString("AHC.MapPath");
						String lStrFileName = config.getString("AHC.MapPath")
								+ config.getString("SLASH_VALUE")
								+ config.getString("AHC.Claim")
								+ config.getString("DOT_VALUE")
								+ config.getString("REPORT.XlsExtn");

						// Creates file in EHFTemp folder of client machine
						File xlFile = createFile(lStrDirPath, lStrFileName);
						ExcelWriter.prepareExl(xlFile, expList);
						byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);

						// Setting request and response objects
						request.setAttribute("File", lbBytes);
						response.setContentType(config
								.getString("REPORT.ExcelContentType"));
						response.setHeader("Content-Disposition",
								"Attachment; filename=" + lStrFileName);
						return mapping.findForward("openFile");
					}
					if (genFlag != null && genFlag.equalsIgnoreCase("P")) {
						String lStrDirPath = config.getString("PAYMENT.MapPath");
						String lStrFileName = config.getString("PAYMENT.MapPath")
								+ config.getString("SLASH_VALUE")
								+ config.getString("PAYMENT.Claim")
								+ config.getString("DOT_VALUE")
								+ config.getString("REPORT.PdfExtn");

						// Creates file in EHFTemp folder of client machine
						File xlFile = createFile(lStrDirPath, lStrFileName);
						PdfGenerator.preparePdf(xlFile, expList, "TDS Payments Report");
						byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);

						// Setting request and response objects
						request.setAttribute("File", lbBytes);
						response.setContentType(config
								.getString("REPORT.PdfContentType"));
						response.setHeader("Content-Disposition",
								"Attachment; filename=" + lStrFileName);
						return mapping.findForward("openFile");
					}
				}
					lstrResultPage="claimsPaymentPage";
				}
				
				else if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("updateClaimForPayment")){
					String status = request.getParameter("status");
					String paymentType = request.getParameter("paymentType");
					ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
					claimFlowVO.setCaseSelected(ahcClaimsForm.getCaseSelected());
					claimFlowVO.setRoleId(ahcClaimsForm.getRoleId());
					claimFlowVO.setUserId(lStrUserId);
					claimFlowVO.setActionDone(request.getParameter("actionDone"));
					claimFlowVO.setPaymentType(paymentType);
					claimFlowVO.setCurrStatus(status);  
					claimFlowVO.setSchemeType(ahcClaimsForm.getScheme());
					String msg=ahcClaimsService.updateClaimDtlsforPayment(claimFlowVO);
					request.setAttribute("saveMsg", msg);
					
					request.setAttribute("schemeTypeSel", claimFlowVO.getSchemeType());
					lstrResultPage="claimsPaymentPage";
					
				}
				else if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("uploadAhcAttachments")){
					String ahcInvestigationList=null;
					String lStrPatientId=request.getParameter("ahcId");
					List<LabelBean> ahcInvList=new ArrayList<LabelBean>();
					if(request.getParameter("addTests")!=null && !request.getParameter("addTests").equals(""))
					{
						ahcInvestigationList=request.getParameter("addTests");
						String[] st1=ahcInvestigationList.split("~");
						//StringTokenizer st=new StringTokenizer(ahcInvestigationList,"~");
						for(int i=0;i<st1.length;i++){
							LabelBean labelBean=new LabelBean();
							labelBean.setID(st1[i]);
							 ahcInvList.add(labelBean)  ;
						}
						
						/*while(st.hasMoreTokens()){
							
							String lL=st.nextToken();
							 GLOGGER.info("investigation :"+lL);
							 System.out.println("investigation :"+lL);
							String[] st1=new String[3];
							st1=lL.split("\\$");
							//StringTokenizer st1=new StringTokenizer(lL,"$");
						
								labelBean.setVALUE(st1[0]);
								labelBean.setID(st1[1]);   
									 if(st1[2]!=null)
								labelBean.setPrice(st1[2]); 
								 else
									 labelBean.setPrice(""); 
								
									 ahcInvList.add(labelBean)  ;
							
						
						}*/
					}
					FormFile lFormFile=null;String errmsg="";int i=0;
					for(LabelBean labelBean: ahcInvList){
						//used to avoid deleted attachments-conflict
						while(ahcClaimsForm.getInvestAttach(i)==null )
						{
							i++;
						}
						if(ahcClaimsForm.getInvestAttach(i)!=null ){
							lFormFile=ahcClaimsForm.getInvestAttach(i);
							if (lFormFile.getFileSize() > 204800) 
							{
								errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
							}
						}
						i++;
					}
					List<AnnualCheckUpVo> ahcTestLst= new ArrayList<AnnualCheckUpVo>();
					i=0;
					for(LabelBean labelBean: ahcInvList){
						//int attachLength=Integer.parseInt(investAttachLength);
						//annualCheckupVo.setInvestLength(attachLength);
						/*for(int i=0; i<attachLength;i++)
							{*/
						while(ahcClaimsForm.getInvestAttach(i)==null )
						{
							i++;
						}
								if(ahcClaimsForm.getInvestAttach(i)!=null)
									{
										if(ahcClaimsForm.getInvestAttach(i).getFileSize()<204800)
											{
												try
													{
													AnnualCheckUpVo test= new AnnualCheckUpVo();
														StringBuffer filePath=new StringBuffer();
														filePath.append(config.getString("mapPath"));
														filePath.append(config.getString("ahcAttchpath"));
														filePath.append(lStrPatientId);
														String fileName=filePath.toString()+"/"+ahcClaimsForm.getInvestAttach(i).getFileName();
														//Creating Folder
														File folderPath =new File(filePath.toString()+"/");
														if(!folderPath.exists())
															folderPath.mkdirs();
														
														File newFile=new File(fileName);
														if(newFile.exists())
															newFile.delete();
														
														//Creating File															
														newFile.createNewFile();
														byte[] fileData=ahcClaimsForm.getInvestAttach(i).getFileData();
														
														FileOutputStream fos=new FileOutputStream(newFile);
														fos.write(fileData);
														fos.close();
														test.setFilePath(fileName);
														test.setICDcatName(labelBean.getID());
														//test.setICDsubCatName(labelBean.getVALUE());
														//test.setCatName(labelBean.getPrice());
														ahcTestLst.add(test);
														i++;
													}
												catch(Exception e)
													{
														e.printStackTrace();
														GLOGGER.error("Exception Occured in Creating Investigation Attachments in AHC Patient Registration "+e.getMessage());
													}
												
											}
										else
											{
												break;
											}
									}
							//}
					//}
				}
					boolean res=annualCheckUpService.uploadAttachments(ahcTestLst,lStrPatientId);
					if(res){
						request.setAttribute("ResultMsg", "Uploaded Successfully");
					}
					else
					{
						request.setAttribute("ResultMsg", "Something failed. Please try again later");
					}
					lStrActionVal="viewAhcAttachments";
					
				}
				
				
				 if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("viewAhcAttachments")){
					
					 String headerShow=request.getParameter("headerShow");
					 request.setAttribute("headerShow", headerShow);
					List<LabelBean> investList=new ArrayList<LabelBean>();
					investList=annualCheckUpService.getGeneralInvest(request.getParameter("ahcId"));
					if(investList!=null && investList.size()>0)
						request.setAttribute("investList", investList);
					EhfAnnualCaseDtls ahcStatus = ahcClaimsService.getAhcStatusDtls(request.getParameter("ahcId"));
					if(roleId!=null && config.getString("Group.Medco").equalsIgnoreCase(roleId))
						request.setAttribute("viewType", "medco");
       	    	 if(ahcStatus!=null){
       	    		 request.setAttribute("status", ahcStatus.getAhcStatus());
       	    	 }
       	    	 
				List<EhfAhcPatientTest> attachList =new ArrayList<EhfAhcPatientTest>();
				attachList=annualCheckUpService.getAttachDtls(request.getParameter("ahcId"));
				if(attachList!=null && attachList.size()>0)
					{
						List<LabelBean> attachLbList=new ArrayList<LabelBean>(); 
						for(EhfAhcPatientTest localBean:attachList)
							{
								LabelBean lbean=new LabelBean();
								if(localBean!=null && localBean.getTestId()!=null
										&& localBean.getPatientId()!=null
										&& !"".equalsIgnoreCase(localBean.getPatientId()))
									{
										
										lbean.setCALLID(localBean.getTestId());
										lbean.setATTACH(localBean.getTestName());
										lbean.setVALUE(localBean.getAttachPath());
										if(localBean.getAttachPath()!=null && !"".equalsIgnoreCase(localBean.getAttachPath())){
											int location=localBean.getAttachPath().lastIndexOf("/");
											lbean.setID(localBean.getAttachPath().substring(location+1, localBean.getAttachPath().length()));
										}
										lbean.setCaseId(localBean.getPatientId());
									}
								attachLbList.add(lbean);
							}
						request.setAttribute("attachList", attachLbList);
						System.out.println(attachLbList.size());
					}
				List<LabelBean> oldConsultList =new ArrayList<LabelBean>();
				oldConsultList= annualCheckUpService.getConsultationDtls(request.getParameter("ahcId"));
				String oldConsultLst="";
				for(LabelBean lb : oldConsultList){
					oldConsultLst=oldConsultLst+lb.getID()+"~"+lb.getCOUNT()+"~"+lb.getUNITTYPE()+"~"+lb.getUNITNAME()+"~"+lb.getREMARKS()+"@";
				}
				request.setAttribute("oldConsultList", oldConsultList);
				request.setAttribute("oldConsultLst", oldConsultLst);
				request.setAttribute("ahcId", request.getParameter("ahcId"));
				lstrResultPage="ahcAttachments";
				}
			return mapping.findForward(lstrResultPage);
			}
			private File createFile(String lStrDirPath, String lStrFileName)throws IOException {
			// Making Directory
			File file = new File(lStrDirPath);
			if (!file.exists()) {
				file.mkdir();
			}
			File lfile = new File(lStrFileName);
			if (!lfile.exists()) {
				lfile.createNewFile();
			} else {
				lfile.delete();
				lfile.createNewFile();
			}
			return lfile;

	}
}
