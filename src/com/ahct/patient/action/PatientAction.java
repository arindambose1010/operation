package com.ahct.patient.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.google.gson.Gson;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.util.ClaimCases;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.login.service.LoginService;
import com.ahct.model.EhfJrnlstPatientTherapy;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.model.EhfmSeq;
import com.ahct.patient.constants.FileConstants;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CaseTherapyVO;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.preauth.service.CasesApprovalService;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;

public class PatientAction extends Action {
	private final static Logger GLOGGER = Logger.getLogger ( PatientAction.class ) ;
	@SuppressWarnings({ "unchecked", "null" })
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{	response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0);    
		HttpSession session = request.getSession ( false ) ;
		PatientForm patientForm=(PatientForm)form;
		PatientVO patientVO=null;
		//PreauthVO preauthVO=null;
		ConfigurationService configurationService = 
				(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
		LoginService loginService =(LoginService)ServiceFinder.getContext(request).getBean("loginService");
		 TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		CasesApprovalService casesApprovalService = ( CasesApprovalService ) ServiceFinder.getContext ( request ).getBean ( "casesApprovalService" ) ;
		String userName=null;
		String lStrLangID=null;
		String lStrUserId =null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String roleId="";
		String userMobileNo=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
		String serverDt = sdfw.format(new Date());
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		FileInputStream fis=null;
		DataInputStream dis=null;
		String schemeId=null;
		byte bytes[]=null;
		//EhfmSeq ehfmSeq=null;
		String smsNextVal="";
		String callType=null;
		String ipOpType = null;
		String claimStatus = null;//Chandana - 7845
    	String nextStatus = null;//Chandana - 7845
    	String nextRoleStatus = null; String userRole = null; //Chandana - 8602 - Added two variables
    	String actnDone = null;//Chandana - 7845
		String bifurcationDate=config.getString("bifurcationDate");
		request.setAttribute("bifurcationDate", bifurcationDate);
		if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
		{
			callType=request.getParameter("callType");
		}
		String lStrResultPage = HtmlEncode.verifySession(request, response);
		if (lStrResultPage.length() > 0)
		{
			if(callType!=null && "Ajax".equals(callType))
			{
				request.setAttribute("AjaxMessage","SessionExpired");
				return mapping.findForward("ajaxResult");
			}
			else
				return mapping.findForward("sessionExpire");
		}
		
		String userIDu=(String) session.getAttribute("UserID");
		String hospGovu=patientService.checkGovernmentHosp(userIDu);
		//Added by ramalakshmi for hubspoke
		String checkHubSpoke=patientService.checkHubSpoke(userIDu);
		if(checkHubSpoke!=null && "SPOKE".equalsIgnoreCase(checkHubSpoke))
		{
			request.setAttribute("hubSpoke","S");
		}
		if(checkHubSpoke!=null && "HUB".equalsIgnoreCase(checkHubSpoke))
		{
			request.setAttribute("hubSpoke","S");
		}
		request.setAttribute("hospGovu", hospGovu);
		String bioRegFlag = config.getString("biometricRegistration");
		
		if(bioRegFlag==null && session.getAttribute("bioRegFlag")!=null)
			bioRegFlag=(String)session.getAttribute("bioRegFlag");
		
		if(bioRegFlag!=null &&!"".equalsIgnoreCase(bioRegFlag))
			session.setAttribute("bioRegFlag",bioRegFlag);
		
		String spokeRole="";
		if(session.getAttribute("EmpID").toString()!=null)
		{
			lStrUserId = session.getAttribute("EmpID").toString();
		}
		if(session.getAttribute("LangID").toString()!=null)
		{
			lStrLangID = session.getAttribute("LangID").toString();
		}
		if(session.getAttribute("userName").toString()!=null)
		{
			userName=session.getAttribute("userName").toString();
		}
		if(session.getAttribute("groupList").toString()!=null)
		{
			grpList=(List<LabelBean>)session.getAttribute("groupList");
		}
		if(session.getAttribute("userState").toString()!=null)
		{
			schemeId=session.getAttribute("userState").toString();
		}
		if(session.getAttribute("userMobileNo").toString()!=null)
		{
			userMobileNo=session.getAttribute("userMobileNo").toString();
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
			if((lStrgrpList.contains(config.getString("preauth_spoke_role"))))
			{
				roleId=config.getString("Group.Medco");
				spokeRole=config.getString("preauth_spoke_role");
			}
			else
				roleId=config.getString("Group.Medco");
			
		}
		else if(lStrgrpList.contains(config.getString("Group.Pex")))
		{
			roleId=config.getString("Group.Pex");
		}
		else
		{
			roleId=lStrgrpList.get(0);
		}
		String stateHdrId =config.getString("IPOP.StateHDRID");
		String statePrntId =config.getString("IPOP.StatePrntId");
		String distHdrId =config.getString("IPOP.DistrictHDRID");
		String distParntId = config.getString("IPOP.APCode");
		
		String apStateId = config.getString("IPOP.APCode");
		String tgStateId = config.getString("IPOP.TGCode");
		
		String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
		String lStrMandalHdrId = config.getString("IPOP.MandalHDRID");
		String lStrMunicipalHdrId = config.getString("IPOP.MunicipalityHDRID");
		String lStrVillageHdrId = config.getString("IPOP.VillageHDRID");
		String lStrMplVillageHdrId = config.getString("IPOP.MunicipalVillageHDRID");

		String lStrActionVal = request.getParameter("actionFlag");
		String lStrPageName = null; 
		String checkHosp=null;
		List<LabelBean> hospDtlsList = null;
		request.setAttribute("hospDtlsList", hospDtlsList);
		
		List<LabelBean> drugTypeList = null;
		request.setAttribute("drugTypeList", drugTypeList);
		String loggedUserHospId= commonService.getloggedUserHospId(lStrUserId,schemeId);
		
		List<LabelBean> medicalSpltyList=null;
		if(!"".equalsIgnoreCase(loggedUserHospId) && loggedUserHospId != null)
		{
			medicalSpltyList	=commonService.getMedicalSpltyList(loggedUserHospId,schemeId);     
			request.setAttribute("medicalSpltyList", medicalSpltyList);
		}
		List<LabelBean> medicalCatList = commonService.getMedicalCatgryListDflt();   
		request.setAttribute("medicalCatList", medicalCatList);
		
		String dentalrnt=request.getParameter("dentalredirect");
		request.setAttribute("dentalornt", "dentalrnt");
		//Chandana - 8618 - New method for getting the userhospid
		if("GP1".equalsIgnoreCase(roleId) || "GP2".equalsIgnoreCase(roleId)){
			String hospId = patientService.getUserHospId(lStrUserId, roleId, schemeId);
			request.setAttribute("userHospId", hospId);
		}

		if ("openPatRegForm".equalsIgnoreCase(lStrActionVal))
		{
			
			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);

			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			session.setAttribute("castesList",castesList);

			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);

			List<LabelBean> maritalStatusList=commonService.getComboDetails(config.getString("IPOP.MaritalStatusCMBHDRID"));
			session.setAttribute("maritalStatusList",maritalStatusList);

			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			session.setAttribute("hospitalList",hospitalList);
			
			String newBornBabyFlag="N";
			
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			/*if(hospitalList.size()>0)
				{
					for(int hl=0;hl<hospitalList.size();hl++)
						{
							checkHosp=hospitalList.get(hl).getHospActiveYn();
							if("S".equalsIgnoreCase(checkHosp)||"C".equalsIgnoreCase(checkHosp))
								hospitalList.remove(hl);
						}
				}*/
			if(hospitalList.size()==0)
				return mapping.findForward("noAuthorisation");
			
			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);

			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));
			
			String hospStatus= patientService.getHospActiveStatus(lStrUserId,roleId);
			if(config.getString("inactive_status")!=null && hospStatus!=null 
					&& config.getString("inactive_status").contains("~"+hospStatus+"~"))
			{
				request.setAttribute("inActiveStatusMsg", config.getString("msg_"+hospStatus));
				request.setAttribute("inActiveStatusFlag", "Y");
			}

			lStrPageName = "InPatientRegistration"; 
		}
		//pravalika
		if ("openPatRegFormDLH".equalsIgnoreCase(lStrActionVal))
		{
			
			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);

			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			session.setAttribute("castesList",castesList);

			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);

			List<LabelBean> maritalStatusList=commonService.getComboDetails(config.getString("IPOP.MaritalStatusCMBHDRID"));
			session.setAttribute("maritalStatusList",maritalStatusList);

			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			session.setAttribute("hospitalList",hospitalList);
			
			String newBornBabyFlag="N";
			
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			if(hospitalList.size()>0)
				{
					for(int hl=0;hl<hospitalList.size();hl++)
						{
							checkHosp=hospitalList.get(hl).getHospActiveYn();
							if("S".equalsIgnoreCase(checkHosp)||"C".equalsIgnoreCase(checkHosp))
								hospitalList.remove(hl);
						}
				}
			if(hospitalList.size()==0)
				return mapping.findForward("noAuthorisation");
			
			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);

			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));
			
			String hospStatus= patientService.getHospActiveStatus(lStrUserId,roleId);
			if(config.getString("inactive_status")!=null && hospStatus!=null 
					&& config.getString("inactive_status").contains("~"+hospStatus+"~"))
			{
				request.setAttribute("inActiveStatusMsg", config.getString("msg_"+hospStatus));
				request.setAttribute("inActiveStatusFlag", "Y");
			}
			
			List<LabelBean> categoryList = patientService.getcategoryList();
			session.setAttribute("categoryList",categoryList);
			
			lStrPageName = "InPatientRegDlhJHS"; 
		}
		if("checkPatientIsRegisteredInNims".equalsIgnoreCase(lStrActionVal)){//Chandana - 8618 - New actionVal for checking the reg hosp is nims and 30 days condition for registration
			String result ="";
			String cardNo = request.getParameter("cardNo");
			result = patientService.checkPatientIsRegisteredInNims(cardNo);
			Gson gson = new Gson();
        	String gsonString = gson.toJson(result);   
        	response.getWriter().write(gsonString);
        	return null;
		}
		if("retrieveCardDetails".equalsIgnoreCase(lStrActionVal))
		{
			String newBornBabyFlag="N";
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			
			PatientVO patientVo=new PatientVO();
			String cardNo=request.getParameter("cardNo");
			patientVo.setCardNo(cardNo);
			patientVo.setCardType(patientForm.getCard_type());
			patientVo.setSchemeId(schemeId);
			List<LabelBean> hospitalList=new ArrayList<LabelBean>();
			
			if(((List<LabelBean>)session.getAttribute("hospitalList"))!=null)
				hospitalList=(List<LabelBean>)session.getAttribute("hospitalList");
			else
				hospitalList=patientService.getHospital(lStrUserId,roleId);
			
			patientForm.setTgGovPatCond("N");
			if(hospitalList!=null)
				{
					if(hospitalList.size()>0)
						{
							if(hospitalList.get(0).getID()!=null && !"".equalsIgnoreCase(hospitalList.get(0).getID()))
								{
									patientVo.setHospitalCode(hospitalList.get(0).getID());
											
								}
						}
				}
			patientVo.setRoleId(roleId);
			patientVO=patientService.retrieveCardDetails(patientVo);
			if(patientVO!=null)
			{
				if(patientVO.getVillageCode()!=null )
				{
					LabelBean labelBeanVillage=new LabelBean();
					labelBeanVillage.setID(patientVO.getVillageCode());
					labelBeanVillage=commonService.getNewLocations(labelBeanVillage);
					if(labelBeanVillage!=null)
					{
						patientVO.setDistrictCode(labelBeanVillage.getNEW_DIST());					
						patientVO.setMandalCode(labelBeanVillage.getNEW_MAND());
						patientVO.setVillageCode(labelBeanVillage.getNEW_VILLAGE());
						
					}
				}
			}
			patientForm.setTelephonicId(patientForm.getTelephonicId());
			patientForm.setTelephonicReg(patientForm.getTelephonicReg());
			
			if(patientVO!=null && !(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_cont_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_delist_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_deempan_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_cont_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("invalid_hosp_scheme").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("invalid_born_baby").equalsIgnoreCase(patientVO.getMsg()))
							    && !(config.getString("invalid_state_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("invalid_born_baby_Scheme").equalsIgnoreCase(patientVO.getMsg())))
				
			{
				patientForm.setCardIssueDt(patientVO.getCardIssueDt());
				patientForm.setEmpCode(patientVO.getEmpCode());
				patientForm.setCardNo(cardNo);
				patientForm.setCard_type(patientForm.getCard_type());
				patientForm.setHead(patientForm.getHead());
				patientForm.setDisableFlag(config.getString("NFlag"));
				patientForm.setAddrEnable(config.getString("YFlag"));
				String dob[]=patientVO.getDateOfBirth().substring(0,10).split("-");
				String finalDob=dob[2]+"-"+dob[1]+"-"+dob[0];

				patientForm.setErrMsg("");
				patientForm.setDateOfBirth(finalDob);
				patientForm.setGender(patientVO.getGender());
				patientForm.setFname(patientVO.getFirstName());
				patientForm.setEkycFlag(patientVO.getEkycFlag());//Chandana - 8326 - for abha
				patientForm.setAbhaId(patientVO.getAbhaId());//Chandana - 8326 - for abha id
				userName = session.getAttribute("userName").toString();//Chandana - 8326
				patientForm.setUserName(userName);//Chandana - 8326
				patientForm.setRelation(patientVO.getRelation());
				patientForm.setCaste(patientVO.getCaste());
				patientForm.setContactno(patientVO.getContactNo());
				patientForm.setMaritalStatus(patientVO.getMaritalStatus());
				patientForm.setOccupation(patientVO.getOccupationCd());
				patientForm.setSlab(patientVO.getSlab());
				patientForm.setScheme(patientVO.getSchemeId());
				patientForm.setTelScheme(patientVO.getSchemeId());
				patientForm.setPrc(patientVO.getPrc());
				patientForm.setPayScale(patientVO.getPayScale());
				patientForm.setDept(patientVO.getDept());
				patientForm.setDeptHod(patientVO.getDeptHod());
				patientForm.setPostDist(patientVO.getPostDist());
				patientForm.setPostDDOcode(patientVO.getPostDDOcode());
				patientForm.setServDsgn(patientVO.getServDsgn());
				patientForm.setDdoOffUnit(patientVO.getDdoOffUnit());
				patientForm.setCurrPay(patientVO.getCurrPay());
				patientForm.setDesignation(patientVO.getDesignation());
				patientForm.setAadharID(patientVO.getAadharID());
				patientForm.setAadharEID(patientVO.getAadharEID());
			
				request.setAttribute("scheme", patientVO.getSchemeId());
				if(patientVO.geteMailId()!=null && !patientVO.geteMailId().equals(""))
				{
					patientForm.seteMailId(patientVO.geteMailId());
				}
				if(patientVO.getPhoto()!=null)
				{
					try
					{
						File photo = new File(patientVO.getPhoto());
						fis = new FileInputStream(photo);
						dis= new DataInputStream(fis);
						bytes = new byte[dis.available()];
						fis.read(bytes);
						String lStrContentType=null;
						lStrContentType="image/jpg";
						session.setAttribute("ContentType", lStrContentType);
						session.setAttribute("File", bytes);
						fis.close();
						dis.close();
						patientForm.setPhotoUrl(patientVO.getPhoto());
					}
					catch(Exception e)
					{
						e.printStackTrace();
//						GLOGGER.error ( "Exception occured while photo is not available in the path specified in PatientAction." +e.getMessage()) ;
					}
				}
				patientForm.setHno(patientVO.getAddr1());
				patientForm.setStreet(patientVO.getAddr2());
				List<LabelBean> districtList=null;
				if(patientVO.getState()!=null)
				{
					patientForm.setState(patientVO.getState());
					districtList=commonService.getAsrimLocations(distHdrId, patientVO.getState());
				}
				request.setAttribute("districtList",districtList);
				patientForm.setDistrict(patientVO.getDistrictCode());
				patientForm.setMdl_mcl(patientVO.getMdl_mpl());
				if(patientVO.getMdl_mpl()!=null && patientVO.getMdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> mdlList=commonService.getAsrimLocations(lStrMandalHdrId, patientVO.getDistrictCode());
					request.setAttribute("mdlList", mdlList);
					patientForm.setMandal(patientVO.getMandalCode());

					List<LabelBean> villList=commonService.getAsrimLocations(lStrVillageHdrId, patientVO.getMandalCode());
					request.setAttribute("villList", villList);
				}
				else if(patientVO.getMdl_mpl()!=null && patientVO.getMdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> mplList=commonService.getAsrimLocations(lStrMunicipalHdrId, patientVO.getDistrictCode());
					request.setAttribute("mplList", mplList);
					patientForm.setMunicipality(patientVO.getMandalCode());

					List<LabelBean> villList=commonService.getAsrimLocations(lStrMplVillageHdrId, patientVO.getMandalCode());
					request.setAttribute("villList", villList);
				}

				patientForm.setVillage(patientVO.getVillageCode());
				patientForm.setDtTime(serverDt);
				GLOGGER.info ( "Setting  Card Details to PatientForm in PatientAction." +"  Mandal Code is "+patientVO.getMandalCode()+"  and village Code is"+patientVO.getVillageCode()) ;
				
				patientForm.setTgGovPatCond("N");
				if(hospitalList!=null)
					{
						if(hospitalList.size()>0)
							{
								if(hospitalList.get(0)!=null)
									{
										//Checking for only Government Hospitals and for Patient whose Scheme is TG
										if(hospitalList.get(0).getID()!=null && !"".equalsIgnoreCase(hospitalList.get(0).getID())
												&& hospitalList.get(0).getNATURE()!=null && "G".equalsIgnoreCase(hospitalList.get(0).getNATURE())
												&& patientVO.getSchemeId()!=null && !"".equalsIgnoreCase(patientVO.getSchemeId())
												&& config.getString("TG").equalsIgnoreCase(patientVO.getSchemeId()))
											{
												patientForm.setTgGovPatCond("Y");
												if(bioRegFlag!=null && !"".equalsIgnoreCase(bioRegFlag) 
														 && "Y".equalsIgnoreCase(bioRegFlag))
													{
														List<LabelBean> bioFingerOptions=new ArrayList<LabelBean>();
														if(config.getString("BioMetricFingersDropDown")!=null &&
																!"".equalsIgnoreCase(config.getString("BioMetricFingersDropDown")))
															{
																String[] bioFingerLst=config.getString("BioMetricFingersDropDown").split("@");
																for(String locFinger : bioFingerLst)
																	{
																		String[] values=locFinger.split("~"); 
																		LabelBean localBean=new LabelBean();
																		localBean.setID(values[0]);
																		localBean.setVALUE(values[1]);
																		bioFingerOptions.add(localBean);
																	}
																request.setAttribute( "bioFingerOptions" , bioFingerOptions );
															}
													}	
											}
									}
							}
					}	
			}
			else if(patientVO!=null &&(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(patientVO.getMsg())
					   || (config.getString("scheme_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_cont_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_delist_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_deempan_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_cont_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("invalid_hosp_scheme").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("invalid_state_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("invalid_born_baby_Scheme").equalsIgnoreCase(patientVO.getMsg()))))
				
			{
				patientForm.setDisableFlag(config.getString("YFlag"));
				patientForm.setCardNo("");
				patientForm.setCard_type("");
				patientForm.setHead("");
				patientForm.setDtTime(serverDt);
				if(patientVO.getMsg()!=null && config.getString("inactivate_card_death_remarks").equalsIgnoreCase(patientVO.getMsg()))	
					patientForm.setErrMsg(config.getString("inactivate_card_death_remarks"));
				if(patientVO.getMsg()!=null && config.getString("invalid_state_remarks").equalsIgnoreCase(patientVO.getMsg()))
					patientForm.setErrMsg(config.getString("invalid_state_remarks"));
				else if(patientVO.getMsg()!=null)
					patientForm.setErrMsg(patientVO.getMsg());
			}
			else
			{
				patientForm.setDisableFlag(config.getString("YFlag"));
				patientForm.setCardNo("");
				patientForm.setCard_type("");
				patientForm.setHead("");
				patientForm.setDtTime(serverDt);
				patientForm.setErrMsg(config.getString("err.InvalidCardNo"));
			}
			
			if(session.getAttribute("userState").toString()!=null)
			{
				schemeId=session.getAttribute("userState").toString();
			}
			request.setAttribute("execScheme", schemeId);
			
			if(request.getParameter("SelOper")!=null && request.getParameter("SelOper").equalsIgnoreCase("TELE"))
				lStrPageName = "telephonicPatientEntry";
			else if("DJ".equalsIgnoreCase(patientVo.getCardType()) || "DAB".equalsIgnoreCase(patientVo.getCardType())){
				lStrPageName = "InPatientRegDlhJHS";
			}
			else
				lStrPageName = "InPatientRegistration";
		}
		
		/*@Author Sravan
		 * Added to get hosp staus while registering TID
		 */
		
		if ("getHospStatus".equalsIgnoreCase(lStrActionVal))
		{
			String cardNo = request.getParameter("cardNo");
			String hospId = request.getParameter("hospId");
			LabelBean lb = patientService.getHospStatus(cardNo,hospId);
			System.out.println(lb.getMsg());
			if(lb.getMsg() == null || lb.getMsg()=="")
				request.setAttribute("AjaxMessage", "success");
			else
			request.setAttribute("AjaxMessage", lb.getMsg());
			lStrPageName="ajaxResult";
		}
		
		if ("registerPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			patientVO=new PatientVO();
			String caseStartTime = sds.format(new Date().getTime());
			String tgGovPatCond=request.getParameter("tgGovPatCond");
			
			if(tgGovPatCond!=null && !"".equalsIgnoreCase(tgGovPatCond)
					&& "Y".equalsIgnoreCase(tgGovPatCond))
				
				{
					patientVO.setTgGovPatCond(tgGovPatCond);
					if(bioRegFlag!=null && !"".equalsIgnoreCase(bioRegFlag) 
							 && "Y".equalsIgnoreCase(bioRegFlag))
						{
							patientVO.setBioRegFlag(bioRegFlag);
							patientVO.setFingerPrint(patientForm.getBiometricValue());
							patientVO.setFingerCaptured(patientForm.getBioFinger());
							patientVO.setBioHand(patientForm.getBioHand());
						}
				}
			
			
			String newBornBabyFlag="N";
			
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			System.out.println(patientForm.getEmpCode());
			String wapNo=request.getParameter("wapNo");
			String familyNo=request.getParameter("familyNo");
			
			EhfmSeq ehfmSeqPatient = null;
			String liNextVal="";
			String userId=null;
			int phaseRenewal=0;
			Date date = new Date();
			String crtDate=sdfw.format(date);
			Date crtDt = sdfw.parse((sdfw.format(date)));
			userId=session.getAttribute("EmpID").toString(); 
			GLOGGER.info("Card No"+wapNo+"/"+familyNo) ;
			
			liNextVal = patientService.getSequence("PATIENT_ID");

			patientVO.setPatientId(liNextVal);
			patientVO.setProcessInstanceId("1");
			patientVO.setCrtDt(crtDate);
			patientVO.setCrtUsr(userId);
			//Chandana - 8442 - Added the below code for abha numbers, using abha number getting the main card number either journalist or emp or pensioner
			//CR 9043 pravalika - Added OR condition for abha(delhi journalist)
			if (patientForm.getCard_type().equalsIgnoreCase("AB") || patientForm.getCard_type().equalsIgnoreCase("DAB")) {
			    String cardDetails = patientService.getCardDtlsForAbhaSearch(patientForm.getCardNo());
			    if (cardDetails != null && cardDetails.contains("~")) {
			        String[] parts = cardDetails.split("~");
			        String cardNum = parts[0];
			        String type = parts[1];
			        patientForm.setCardNo(cardNum);
			        patientForm.setCard_type(type);
			        patientVO.setRationCard(cardNum);
			        patientVO.setCardType(type);
			        patientVO.setAbhaId(wapNo);
			    }
			}
			else{
				patientVO.setRationCard((wapNo+"/"+familyNo).toUpperCase());
				patientVO.setCardType(patientForm.getCard_type());
			}
			if(patientForm.getCard_type()!=null)
				{
					if(patientForm.getCard_type().equalsIgnoreCase("E") || patientForm.getCard_type().equalsIgnoreCase("P")
							|| patientForm.getCard_type().equalsIgnoreCase("NB"))
						patientVO.setPatientScheme(config.getString("Scheme.EHS"));
					else if(patientForm.getCard_type().equalsIgnoreCase("J") || patientForm.getCard_type().equalsIgnoreCase("DJ") )
						patientVO.setPatientScheme(config.getString("Scheme.JHS"));
				}
			//pravalika
			//CR 9043 OR Condition for delhi Abha
			if(patientForm.getCard_type().equalsIgnoreCase("DJ") || patientForm.getCard_type().equalsIgnoreCase("DAB"))
			{
				patientForm.setPatientNo(liNextVal);
				String diagnosis = patientService.getProvisionDiagnosis(patientForm,lStrUserId);
				session.setAttribute("diagnosis",diagnosis);
				patientForm.setDialysis(patientForm.getDialysis());
				patientForm.setDiagnosisCategory(patientForm.getDiagnosisCategory());
				//CR 9043 admission details
				patientForm.setAdmissionDate(patientForm.getAdmissionDate());
				patientForm.setAdmissionNote(patientForm.getAdmissionNote());
				patientForm.setEstAmount(patientForm.getEstAmount());
				//patientForm.set
			}
			
			
			patientVO.setCardIssueDt(patientForm.getCardIssueDt());
			patientVO.setDateOfBirth(patientForm.getDateOfBirth());
			patientVO.setFirstName(patientForm.getFname());
			patientVO.setGender(patientForm.getGender());
			if((!familyNo.equalsIgnoreCase("01") && patientForm.getHead()==null)||
					patientForm.getCard_type().equalsIgnoreCase("NB"))
			{
				patientVO.setFamilyHead(config.getString("NFlag"));
			}
			else if(!patientForm.getCard_type().equalsIgnoreCase("NB"))
			{
				patientVO.setFamilyHead(config.getString("YFlag"));
				patientVO.setChild_yn(config.getString("NFlag"));
			}
			String[] age=patientForm.getAgeString().split("~");
			patientVO.setEmpCode(patientForm.getEmpCode());
			patientVO.setAge(age[0]);
			patientVO.setAgeMonths(age[1]);
			patientVO.setAgeDays(age[2]);
			patientVO.setRelation(patientForm.getRelation());
			patientVO.setCaste(patientForm.getCaste());
			patientVO.setMaritalStatus(patientForm.getMaritalStatus());
			patientVO.setOccupationCd(patientForm.getOccupation());
			patientVO.setContactNo(patientForm.getContactno());
			patientVO.setSlab(patientForm.getSlab());
			patientVO.seteMailId(patientForm.geteMailId());
			patientVO.setAddr1(patientForm.getHno());
			patientVO.setAddr2(patientForm.getStreet());
			patientVO.setVillageCode(patientForm.getVillage());
			patientVO.setState(patientForm.getState());
			patientVO.setDistrictCode(patientForm.getDistrict());
			patientVO.setMdl_mpl(patientForm.getMdl_mcl());
			
			patientVO.setPrc(patientForm.getPrc());
			patientVO.setPayScale(patientForm.getPayScale());
			patientVO.setDept(patientForm.getDept());
			patientVO.setDeptHod(patientForm.getDeptHod());
			patientVO.setPostDist(patientForm.getPostDist());
			patientVO.setPostDDOcode(patientForm.getPostDDOcode());
			patientVO.setServDsgn(patientForm.getServDsgn());
			patientVO.setDdoOffUnit(patientForm.getDdoOffUnit());
			patientVO.setCurrPay(patientForm.getCurrPay());
			patientVO.setDesignation(patientForm.getDesignation());
			patientVO.setAadharID(patientForm.getAadharID());
			patientVO.setAadharEID(patientForm.getAadharEID());
			
			if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
			{
				patientVO.setMandalCode(patientForm.getMandal());
			}
			else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
			{
				patientVO.setMandalCode(patientForm.getMunicipality());
			}
			patientVO.setPincode(patientForm.getPin());
			patientVO.setSrcRegistration(config.getString("PatientIPOP.Source"));
			//start sameaddress check
			if(patientForm.getCommCheck()==null)
			{
				patientVO.setPermAddr1(patientForm.getComm_hno());
				patientVO.setPermAddr2(patientForm.getComm_street());
				patientVO.setC_pin_code(patientForm.getComm_pin());
				patientVO.setC_state(patientForm.getComm_state());
				patientVO.setC_district_code(patientForm.getComm_dist());
				patientVO.setC_mdl_mpl(patientForm.getComm_mdl_mcl());
				if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_mandal());
				}
				else if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_municipality());
				}

				patientVO.setC_village_code(patientForm.getComm_village());	
			}
			else
			{
				patientVO.setPermAddr1(patientForm.getHno());
				patientVO.setPermAddr2(patientForm.getStreet());
				patientVO.setC_pin_code(patientForm.getPin());
				patientVO.setC_state(patientForm.getState());
				patientVO.setC_district_code(patientForm.getDistrict());
				patientVO.setC_mdl_mpl(patientForm.getMdl_mcl());
				if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getMandal());
				}
				else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getMunicipality());
				}
				patientVO.setC_village_code(patientForm.getVillage());	
			}
			//end sameaddress check
			patientVO.setPatient_ipop(config.getString("PatientIPOP.RegisterStatus"));

			//phaseRenewal=commonService.getPhaseId(patientForm.getDistrict());
			phaseRenewal=0;
			patientVO.setPhaseId(phaseRenewal+"");
			patientVO.setRenewal(config.getString("PatientIPOP.Renewal"));
			//patientVO.setSchemeId(config.getString("PatientIPOP.SchemeId"));
			patientVO.setSchemeId(patientForm.getScheme());
			patientVO.setCid(config.getString("PatientIPOP.Cid"));
			if(patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Emp")))
				patientVO.setEmployeeRenewal(config.getString("PatientIPOP.Renewal"));
			else if(patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Pen")))
				patientVO.setPensionerRenewal(config.getString("PatientIPOP.Renewal"));
			patientVO.setRegHospId(patientForm.getHospitalId());
			patientVO.setRegHospDt(crtDate);
			if(!patientForm.getTelephonicId().equals(""))
			{
				patientVO.setTelephonicId(patientForm.getTelephonicId());
				patientVO.setRegState("Telephonic Registration");
			}
			try{
				int rowsInserted=patientService.registerPatient(patientVO);
				String caseEndTime = sds.format(new Date().getTime());
				if(rowsInserted==0)
				{

					GLOGGER.info("Patient cannot be registered");
					lStrPageName="failure";
				}
				else
				{
					if(patientForm.getTelephonicId() != null && !"".equalsIgnoreCase(patientForm.getTelephonicId()))
						loggingService.logTime("TelephonicRegistration", patientVO.getPatientId(), caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
					else
					loggingService.logTime("DirectPatientRegistration", patientVO.getPatientId(), caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
					patientForm.setPatientNo(liNextVal);             
					if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					{
						if(patientVO.getContactNo()!=null && !patientVO.getContactNo().equals(""))
						{
							smsNextVal = patientService.getSequence("PATIENT_SMS_SNO");
							String lStrResultMsg=null;
							PatientSmsVO patientSmsVO=new PatientSmsVO();
							patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
							patientSmsVO.setPhoneNo(patientVO.getContactNo());
							patientSmsVO.setSmsText("Dear "+patientVO.getFirstName().trim()+" , You have been registered as a Patient in "+patientService.getHospName(patientForm.getHospitalId())+" and it is pending with doctor for diagnosis");
							patientSmsVO.setEmpCode(patientVO.getEmpCode());
							patientSmsVO.setEmpName(patientVO.getFirstName().trim());
							patientSmsVO.setCrtUsr(patientVO.getCrtUsr());
							patientSmsVO.setCrtDt(crtDt);
							patientSmsVO.setSmsAction("Patient Registered");
							patientSmsVO.setPatientId(patientVO.getPatientId());
							lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
						}
					}
					if (config.getBoolean("EmailRequired")) 
					{
						String mailId=null;
						if(patientForm.geteMailId()!=null && !patientForm.geteMailId().equals(""))
						{
							mailId=patientForm.geteMailId();
							String[] emailToArray = {mailId};
							EmailVO emailVO = new EmailVO();
							emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
							
							emailVO.setFromEmail(config.getString("emailFrom"));
							emailVO.setToEmail(emailToArray);
							emailVO.setSubject(config.getString("patientEmailSubject"));
							Map<String, String> model = new HashMap<String, String>();
							model.put("patientName", patientVO.getFirstName().trim());
							model.put("registeredHosp", patientService.getHospName(patientForm.getHospitalId()));
							model.put("status", "Patient Registered");
							model.put("statusDate",crtDate);
							
							if(patientVO.getPatientScheme()!=null)
								{
									if(patientVO.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS")))
										{
											emailVO.setSubject(config.getString("patientEmailSubjectJournalist"));
											emailVO.setFromEmail(config.getString("emailFromJournalist"));
											emailVO.setTemplateName(config.getString("EHFPatientTemplateNameJourn"));
											commonService.generateNonImageMail(emailVO, model);
										}
									else
										{
											emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
											commonService.generateMail(emailVO, model);
										}
								}
							else
								{
									emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
									commonService.generateMail(emailVO, model);
								}
						}
					}
					lStrPageName="patient";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error ( "Exception occured while registering patient in PatientAction." +e.getMessage()) ;
			}       				
		}
		if ("getLocations".equalsIgnoreCase(lStrActionVal))
		{
			String locHdrId=request.getParameter("lStrHdrId");
			String stateId=request.getParameter("stateId");
			String distId=request.getParameter("distId");
			String mandalId=request.getParameter("mandalId");
			String villageId=request.getParameter("villageId");
			String stateType=request.getParameter("stateType");
			try {
				List<LabelBean> cmbHdrList=null;
				List<String> locationsList = new ArrayList<String>();
				if(stateId!=null)
				{
					cmbHdrList=commonService.getLocationsNew(locHdrId, stateId,stateType);
					session.setAttribute("districtList",cmbHdrList);
				}
				else if(distId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, distId);
					session.setAttribute("mandalList",cmbHdrList);
				}
				else if(mandalId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, mandalId);
					session.setAttribute("villageList",cmbHdrList);
				}
				else if(villageId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, villageId);
					session.setAttribute("hamletList",cmbHdrList);
				}
				for (LabelBean labelBean: cmbHdrList) {
					if (labelBean.getID() != null && 
							labelBean.getVALUE() != null)
						if (locationsList != null && locationsList.size() > 0) {
							locationsList.add(labelBean.getID() + "~" + 
									labelBean.getVALUE());
						} else
							locationsList.add(labelBean.getID() + "~" + 
									labelBean.getVALUE());
				}
				if (locationsList != null && locationsList.size() > 0)

					request.setAttribute("AjaxMessage", locationsList);
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred in Ajax getLocations actionFlag in PatientAction." +e.getMessage()) ;
			}
			lStrPageName="ajaxResult";
		}

		if ("ViewRegisteredPatients".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			List<LabelBean> distList= new ArrayList<LabelBean>();
			PatientVO retPatVO=null;
			String stateType=request.getParameter("stateType");
			List<PatientVO> registeredPatientsList=null;
			HashMap<String, String> searchMap=new HashMap<String, String>();
			int noOfPages=0;int totRowsCount=0;
			int lStrRowsperpage =10;
			if(patientForm.getRowsPerPage()!=null && !("").equalsIgnoreCase(patientForm.getRowsPerPage()))
				lStrRowsperpage=Integer.parseInt(patientForm.getRowsPerPage());
			int lStrStartIndex = 0;
			int pageNo=0;
			String actionType=null;
			searchMap.put("lStrStartIndex", lStrStartIndex+"");
			searchMap.put("lStrRowsperpage", lStrRowsperpage+"");
			if(hospitalList.size()>0)
			{
				searchMap.put("userHospId",hospitalList.get(0).getID());
			}
			if(request.getParameter("pageNo")!=null && !request.getParameter("pageNo").equals("")){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(pageNo==0)
			{
				request.setAttribute("selectedPage",pageNo+1);
			}
			else
			{
				request.setAttribute("selectedPage",pageNo);
			}
			if(request.getParameter("actionType")!=null && !request.getParameter("actionType").equals("")){
				actionType=request.getParameter("actionType");
			}
			if(request.getParameter("advSearch")!=null && request.getParameter("advSearch").equalsIgnoreCase("true"))
			{
				String patientName="",patientNo="",cardNo="",district="",state="",fromDate="",toDate="";

				if(actionType!=null && actionType.equalsIgnoreCase("link"))
				{
					if(!patientForm.getCurrPatId().equals("")){
						patientNo=patientForm.getCurrPatId();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCurrPatName().equals("")){
						patientName=patientForm.getCurrPatName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getCurrHealthCardNo().equals("")){
						cardNo=patientForm.getCurrHealthCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getCurrStateId().equals("-1") && !patientForm.getCurrStateId().equals("")){
						state=patientForm.getCurrStateId();
						searchMap.put("state",state);
					}
					if(!patientForm.getCurrDistrictId().equals("-1") && !patientForm.getCurrDistrictId().equals("")){
						district=patientForm.getCurrDistrictId();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getCurrFromDate().equals("")){
						fromDate=patientForm.getCurrFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getCurrToDate().equals("")){
						toDate=patientForm.getCurrToDate();
						searchMap.put("toDate",toDate);
					}
				}
				else if(actionType!=null && actionType.equalsIgnoreCase("button"))
				{
					if(!patientForm.getPatientName().equals(""))
					{
						patientName=patientForm.getPatientName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getPatientNo().equals(""))
					{
						patientNo=patientForm.getPatientNo();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCardNo().equals(""))
					{
						cardNo=patientForm.getCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getState().equals("-1"))
					{
						state=patientForm.getState();
						searchMap.put("state",state);
					}
					if(!patientForm.getDistrict().equals("-1"))
					{
						district=patientForm.getDistrict();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getFromDate().equals(""))
					{
						fromDate=patientForm.getFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getToDate().equals(""))
					{
						toDate=patientForm.getToDate();
						searchMap.put("toDate",toDate);
					}
				}
				/*patientForm.setPatientNo("");
        		patientForm.setPatientName("");
        		patientForm.setCardNo("");
        		patientForm.setDistrict("-1");
        		patientForm.setFromDate("");
        		patientForm.setToDate("");*/

			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				
				/*List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
				session.setAttribute("districtList",distList);*/

/*				List<LabelBean> phaseList=commonService.getPhases();
				session.setAttribute("phaseList",phaseList);*/
				//Creating ageList
/*				List<LabelBean> ageList=new ArrayList<LabelBean>();
				for(int i=1;i<=7;i++)
				{
					LabelBean labelBean=new LabelBean();
					labelBean.setID(""+i);
					labelBean.setVALUE(config.getString("REPORTS.AgeGrp"+i));
					ageList.add(labelBean);
				}
				session.setAttribute("ageList",ageList); */
			}
			if(pageNo>0)
			{
				lStrStartIndex = getStartIndex(lStrRowsperpage,pageNo);
				searchMap.put("lStrStartIndex", lStrStartIndex+"");
			}
			searchMap.put("schemeId",schemeId);
			retPatVO=patientService.getRegisteredPatients(searchMap);
			registeredPatientsList=retPatVO.getRegisteredPatList();
			totRowsCount = retPatVO.getTotRowCount();
			noOfPages = getNoOfPages(totRowsCount, lStrRowsperpage);

			request.setAttribute("noOfPages", noOfPages);
			patientForm.setNoOfPages(noOfPages);

			if(registeredPatientsList!=null && registeredPatientsList.size()>0)
			{
				int endIndex=0;
				if(lStrStartIndex+lStrRowsperpage<= retPatVO.getTotRowCount())
				{
					endIndex=lStrStartIndex+lStrRowsperpage;
				}
				else
				{
					endIndex=retPatVO.getTotRowCount();
				}
				request.setAttribute("recordsList",lStrStartIndex+"-"+endIndex);
				request.setAttribute("totalRecords", retPatVO.getTotRowCount());
				request.setAttribute("registeredPatientsList", registeredPatientsList);
				request.setAttribute("searchListSize",retPatVO.getTotRowCount());
			}
			else if(retPatVO.getMsg()!=null && !retPatVO.getMsg().equalsIgnoreCase(""))
			{
				patientForm.setErrMsg(retPatVO.getMsg());
			}
			else
			{
				patientForm.setErrMsg(config.getString("err.NoRecordsFound"));
			}
			request.setAttribute("districtList",distList);

			lStrPageName="viewRegisteredPatients";
		}
		
		if("redirecttodental".equalsIgnoreCase(lStrActionVal))
		{
			/*String dentalrnt=request.getParameter("dentalredirect");
			request.setAttribute("dentalornt", "dentalrnt");*/
			if("yes".equalsIgnoreCase(dentalrnt) || "no".equalsIgnoreCase(dentalrnt))
			{
			 lStrActionVal="viewPatientDetails";
			}
		}
		if ("viewPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			request.setAttribute("dentalFlg", request.getParameter("dental"));
			String patientId=null;
			String lStrCaseId = request.getParameter("caseId");
			EhfPatient ehfPatient=null;
			patientId=request.getParameter("patientId");
			patientForm.setPatientNo(patientId);
			String userID=(String) session.getAttribute("UserID");
			String check=patientService.checkDentalClinic(userID,patientId);
			String hospGov=patientService.checkGovernmentHosp(userID,patientId);
			if(!check.equals("hospital"))
			{
				patientForm.setHosType("DC");
			}
			else
			{
				patientForm.setHosType("hospital");
			}
			// request.setAttribute("patientId",patientId);
			ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
			//storing patcrtdt
			session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
			int otherInvestCount=0;
			int otherDrugCount=0;
			patientForm.setCardNo(ehfPatient.getCardNo());
			patientForm.setFname(ehfPatient.getName());
			PatientVO investVo=new PatientVO();
			if(ehfPatient!= null)
			{
				if("IPM".equalsIgnoreCase(ehfPatient.getPatientIpop()))
				{
					List<LabelBean> catList = patientService.getCatName(lStrCaseId);
					if(catList!=null && catList.size() > 0)	
					{
						if(catList.get(0).getID() != null)
							patientForm.setMedicalCat(catList.get(0).getID());
						if(catList.get(0).getICDCODE() != null)
							patientForm.setMedicalSpclty(catList.get(0).getICDCODE());
					}
				}
			}
			if(ehfPatient.getCardIssueDt()!=null)
			{
				patientForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
			}
			else
				patientForm.setCardIssueDt("NA");
			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
			{
				patientForm.setGender("Male");
			}
			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
			{
				patientForm.setGender("Female");
			}
			if(ehfPatient.getEnrollDob()!=null)
			{
				patientForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
			}
			if(ehfPatient.getAge()!=null)
			{
				patientForm.setYears(ehfPatient.getAge().toString());
				if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 ))
				{
					request.setAttribute("child", "Y");
				}
				else
				{
					request.setAttribute("child", "N");
				}
			}
			if(ehfPatient.getAgeMonths()!=null)
			{
				patientForm.setMonths(ehfPatient.getAgeMonths().toString());
			}
			if(ehfPatient.getAgeDays()!=null)
			{
				patientForm.setDays(ehfPatient.getAgeDays().toString());
			}
			String relationId=ehfPatient.getRelation();
			String relationName=patientService.getRelationName(relationId);
			patientForm.setRelation(relationName);
			String casteId=ehfPatient.getCaste();
			if(casteId!=null && !casteId.equals(""))
			{
				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
				patientForm.setCaste(casteName);
			}
			if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
			{
				patientForm.setContactno(ehfPatient.getContactNo().toString());
			}
			/*String occName = patientService.getOccupationName(ehfPatient.getOccupationCd());
            if(occName!=null && !occName.equalsIgnoreCase(""))*/
			patientForm.setOccupation(ehfPatient.getOccupationCd());
			if(ehfPatient.getSchemeId()!=null )
				session.setAttribute("regPatientScheme",ehfPatient.getSchemeId());
			patientForm.setScheme(ehfPatient.getSchemeId());
			
			patientForm.setTelScheme(ehfPatient.getSchemeId());
			
			investVo.setSchemeId(ehfPatient.getSchemeId());
			/*else
        	patientForm.setOccupation("NA");*/
			//Setting slab
			String slabType=null;
			String slab=null;
			if(ehfPatient.getSlab()!=null)
			{
				slabType=ehfPatient.getSlab();
			}
			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.SemiPrivateWard");
			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.PrivateWard");
			patientForm.setSlab(slab);
			//End Of Slab
			patientForm.setHno(ehfPatient.getHouseNo());
			patientForm.setStreet(ehfPatient.getStreet());
			patientForm.setState(patientService.getLocationName(ehfPatient.getState()));
			String distCode=ehfPatient.getDistrictCode();
			String distName=patientService.getLocationName(distCode);
			patientForm.setDistrict(distName);
			String mandalCode=ehfPatient.getMandalCode();
			String mandalName=patientService.getLocationName(mandalCode);
			patientForm.setMandal(mandalName);
			String villageCode=ehfPatient.getVillageCode();
			String villageName=patientService.getLocationName(villageCode);
			patientForm.setVillage(villageName);
			if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
				patientForm.setPin(ehfPatient.getPinCode());
			else
				patientForm.setPin("NA");
			//Setting Communication Address
			patientForm.setComm_hno(ehfPatient.getcHouseNo());
			patientForm.setComm_street(ehfPatient.getcStreet());
			patientForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
			patientForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
			patientForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
			patientForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
			if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
				patientForm.setComm_pin(ehfPatient.getcPinCode());
			else
				patientForm.setComm_pin("NA");
			patientForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
			String photoUrl=null;
			if(ehfPatient.getPatientScheme()!=null)
				{
					patientForm.setPatientScheme(ehfPatient.getPatientScheme());
					request.setAttribute("patientScheme",ehfPatient.getPatientScheme());
					investVo.setPatientScheme(ehfPatient.getPatientScheme());
					if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
							config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
						 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
					else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
							 && ehfPatient.getNewBornBaby().equalsIgnoreCase("Y"))
						photoUrl=null;
					else
						photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
				}
			else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
					 && ehfPatient.getNewBornBaby().equalsIgnoreCase(config.getString("Y")))
				photoUrl=null;
			else
				photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
			
			if(photoUrl!=null)
			{
				try
				{
					File photo = new File(photoUrl);
					fis = new FileInputStream(photo);
					dis= new DataInputStream(fis);
					bytes = new byte[dis.available()];
					fis.read(bytes);
					String lStrContentType=null;
					lStrContentType="image/jpg";
					session.setAttribute("ContentType", lStrContentType);
					session.setAttribute("File", bytes);
					fis.close();
					dis.close();
					patientForm.setPhotoUrl(photoUrl);
				}
				catch(Exception e)
				{
					GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
				}
			}
			String hospCode=ehfPatient.getRegHospId();
			String hospName=patientService.getHospName(hospCode);
			String dentalFlg=patientService.checkDentalHospital(hospCode);
			patientForm.setHospitalId(hospCode);
			request.setAttribute("hospitalId",hospCode);
			investVo.setHospitalCode(hospCode);
			patientForm.setHospitalName(hospName);

			if(ehfPatient.getIntimationId()!=null)
			{
				patientForm.setTelephonicId(ehfPatient.getIntimationId());
				patientForm.setTelephonicReg("Yes");
				PatientVO patientVo = new PatientVO();
				patientVo.setTelephonicId(ehfPatient.getIntimationId());
				PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);

				patientForm.setTherapyType(patientVO1.getAsriCatCode());
				patientForm.setTherapyCategory(patientVO1.getICDcatName());
				patientForm.setTherapy(patientVO1.getTherapyCatId());
			}
			String pageType="";
			if(request.getParameter("pageType")!=null && !request.getParameter("pageType").equals(""))
			{
				pageType=request.getParameter("pageType");
			}
			if(pageType.equalsIgnoreCase(""))
			{

				List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
				if(hospitalList!=null && hospitalList.size()>0)
				{
					patientForm.setHosptype(hospitalList.get(0).getNATURE());
					request.setAttribute("hospType",hospitalList.get(0).getNATURE());
					investVo.setHospType(hospitalList.get(0).getNATURE());
					investVo.setHospitalCode(hospitalList.get(0).getID());
				}

				/*List<LabelBean> testsList=null;
        testsList=patientService.getTests(lStrLangID);
        request.setAttribute("testsList", testsList);*/													
					
				List<LabelBean> mainComplaintList=null;
				mainComplaintList=patientService.getMainComplaintLst();
				if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
						config.getString("Scheme.EHS").equalsIgnoreCase(ehfPatient.getPatientScheme())
					&& hospitalList.get(0).getNATURE()!=null && ("G").equalsIgnoreCase(hospitalList.get(0).getNATURE())
					
					&& !"".equalsIgnoreCase(ehfPatient.getSchemeId()) && 
					config.getString("TG").equalsIgnoreCase(ehfPatient.getSchemeId())
				
				)
				{
					LabelBean otherCmpLst=new LabelBean();
					otherCmpLst.setID("OC");
					otherCmpLst.setVALUE("Others");
					
					if(mainComplaintList!=null)
					{
						//mainComplaintList.add(otherCmpLst);
					}
					
				}
				
				request.setAttribute("mainComplaintList", mainComplaintList);
				List<LabelBean> districtsList = null;
				districtsList= commonService.getDistrictListNew();
				request.setAttribute("districtsList", districtsList);

				List<LabelBean> pastHistoryList=null;
				pastHistoryList=commonService.getPastIllnessHitory();
				request.setAttribute("pastHistoryList", pastHistoryList);

				List<LabelBean> personalHistoryList=null;
				personalHistoryList=commonService.getPersonalHistory("PR");
				request.setAttribute("personalHistoryList", personalHistoryList);

				List<LabelBean> familyHistoryList=null;
				familyHistoryList=commonService.getFamilyHistory();
				request.setAttribute("familyHistoryList", familyHistoryList);

				List<LabelBean> investigationsList=null;
				//investigationsList=commonService.getInvestigations();
				
				investigationsList=commonService.getInvestBlockNameNew(investVo);
				request.setAttribute("investigationsList", investigationsList);

				/*List<LabelBean> diaglist=patientService.getDiagList();     //getDiagnosis list();
				request.setAttribute("diaglist", diaglist);*/

				List<LabelBean> drugsList=commonService.getOpDrugs(null);     //getDrugs();
				request.setAttribute("drugsList", drugsList);
				
				List<LabelBean> drugsListAuto=commonService.getOpDrugsAuto();     //getopDrugs automatic
				request.setAttribute("drugsListAuto", drugsListAuto);
				
				
				List<LabelBean> routeTypeLst=commonService.getRouteType(null);     //getDrugs();
				request.setAttribute("routeTypeLst", routeTypeLst);
				
				List<LabelBean> strengthTypeLst=commonService.getStrengthType(null);     //getDrugs();
				request.setAttribute("strengthTypeLst", strengthTypeLst);

				List<LabelBean> examinFndgsList=null;
				examinFndgsList=commonService.getGenExaminFndgs(ehfPatient.getSchemeId());
				request.setAttribute("examinFndgsList", examinFndgsList);

				List<LabelBean> mainSymptonLst=null;
				mainSymptonLst=commonService.getMainSymptonLst();
				request.setAttribute("mainSymptonLst", mainSymptonLst);

				List<LabelBean> diagnTypeList = patientService.getDiagnosisTypes();
				session.setAttribute("diagnTypeList",diagnTypeList);

				List<LabelBean> categoryList = commonService.getAsriCategoryList(hospCode,schemeId);
				session.setAttribute("AsricategoryList",categoryList);

				List<LabelBean> procedureList= new ArrayList<LabelBean>();
				session.setAttribute("ICDprocedureList",procedureList);

				List<LabelBean> opCategoryList= patientService.getTherapyOPCategory();
				request.setAttribute("opCategoryList",opCategoryList);
				
				List<LabelBean> dentalsublist= new ArrayList<LabelBean>();
				session.setAttribute("Dentalsublist",dentalsublist);
				
				List<LabelBean> Dentalmainlist0= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist0",Dentalmainlist0);
				List<LabelBean> Dentalmainlist1= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist1",Dentalmainlist1);
				List<LabelBean> Dentalmainlist2= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist2",Dentalmainlist2);
				List<LabelBean> Dentalmainlist3= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist3",Dentalmainlist3);
				List<LabelBean> Dentalsublistjaws1= new ArrayList<LabelBean>();
				request.setAttribute("Dentalsublistjaws1",Dentalsublistjaws1);
				List<LabelBean> Dentalsublistrl0= new ArrayList<LabelBean>();
				request.setAttribute("Dentalsublistrl0",Dentalsublistrl0);
				List<LabelBean> dentalUnitsList = new ArrayList<LabelBean>();
				String dentalUnitsStr=config.getString("Dental.Units");
				String[] dentalUnitslst=dentalUnitsStr.split("~");
				for(int i=0;i<dentalUnitslst.length;i++)
				{
					LabelBean lb=new LabelBean();
					lb.setID(dentalUnitslst[i]);
					lb.setVALUE(dentalUnitslst[i]);
					dentalUnitsList.add(lb);
				}
				request.setAttribute("dentalUnitsList",dentalUnitsList);
				
				
				
				
				CasesSearchVO casesSearchVO = new CasesSearchVO();
				casesSearchVO.setCaseId(lStrCaseId);
				
				String empCode=null;
				empCode=patientService.getEmpCode(ehfPatient.getPatientId());
				
				if(request.getParameter("employeeNo")!=null && !"".equalsIgnoreCase(request.getParameter("employeeNo")))
					casesSearchVO.setLoginName(request.getParameter("employeeNo"));
				else
				casesSearchVO.setLoginName(empCode);
				request.setAttribute("fromPastHistory", "Y");
				request.setAttribute("caseId", lStrCaseId);
				//casesSearchVO.setCaseForDissFlg("Y");
				request.setAttribute("employeeNo", empCode);
				List<CasesSearchVO> casesList=patientService.getOpPastHistoryDetails(casesSearchVO);
				if(casesList.size()==0)
				{
					//casesSearchForm.setFlag("false");
				}
				else
				{
					//casesSearchForm.setFlag("true");
					patientForm.setLstCaseSearch(casesList);
				}
																
				PreauthVO preauthDtlsVO = new PreauthVO();
				PatientVO patientDtlsVo= new PatientVO();
				patientForm.setPatientType("");
				if(dentalFlg !=null && "Y".equalsIgnoreCase(dentalFlg)){
					List<LabelBean> mainComplaintList1=new ArrayList<LabelBean>();
					mainComplaintList=patientService.getDentalMainComplaintLst();
					for(int i=0 ; i<mainComplaintList.size() ; i++)
					{
						if(!mainComplaintList.get(i).getVALUE().equalsIgnoreCase("SORES"))
						{ 
							LabelBean locBean= new LabelBean();
							locBean.setID(mainComplaintList.get(i).getID()); 
							locBean.setVALUE(mainComplaintList.get(i).getVALUE());														
							mainComplaintList1.add(locBean);
						}
						
					}
					
					mainSymptonLst=commonService.getDentalMainSymptonLst();
					investigationsList=commonService.getDentalInvestBlockNameNew(investVo);
					examinFndgsList=commonService.getDentalGenExaminFndgs(ehfPatient.getSchemeId());
					personalHistoryList=commonService.getDentalPersonalHistory("PR");
					request.setAttribute("personalHistoryList", personalHistoryList);
					request.setAttribute("examinFndgsList", examinFndgsList);
					request.setAttribute("investigationsList", investigationsList);
					request.setAttribute("mainComplaintList", mainComplaintList1);
					request.setAttribute("mainSymptonLst", mainSymptonLst);
					
					/*added by pavan for the dental case sheet fetching new options add to sheet*/
					List<LabelBean> extraoraldtls=null;
					List<LabelBean> intraoraldtls=null;
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					List<LabelBean> permanentdentdtlsothr=null;
					List<LabelBean> medicalhsitorydtls=null;
			        String extraoral="HD1";
					String intraoral="HD2";
					String occlusion="CH12";
					String deciduousdent ="CH13";
					String  permanentdent="CH14";
					String  permanentdentothr="CH91";
					String  medicalHsitory="HD6";

					medicalhsitorydtls=patientService.getdentalexaminationDtls(medicalHsitory);
					extraoraldtls=patientService.getdentalexaminationDtls(extraoral);
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					permanentdentdtls=patientService.getdentalexaminationDtls(permanentdent);
					permanentdentdtlsothr=patientService.getdentalexaminationDtls(permanentdentothr);
					
					patientForm.setMedicalhsitorydtls(medicalhsitorydtls);
                    patientForm.setExtraoraldtls(extraoraldtls);
					patientForm.setIntraoraldtls(intraoraldtls);
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					
					request.setAttribute("medicalhsitorydtls", medicalhsitorydtls);
					request.setAttribute("extraoraldtls", extraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", deciduousdentdtls);
					request.setAttribute("permanentdentdtls", permanentdentdtls);
					request.setAttribute("permanentdentdtlsothr", permanentdentdtlsothr);										
				}
				
				request.setAttribute("dentalFlg",dentalFlg );
				System.out.println("dentalflg : "+dentalFlg);
                if(lStrCaseId!=null && !lStrCaseId.equalsIgnoreCase("")){
                patientForm.setCaseId(lStrCaseId);
				preauthDtlsVO = patientService.getPatientFullDtls(lStrCaseId, ehfPatient.getPatientId());		
				
				if(preauthDtlsVO.getCarriesdecidous()!=null)
				{
					patientForm.setCarriesdecidous(preauthDtlsVO.getCarriesdecidous());
				}
				
				if(preauthDtlsVO.getExtraoral()!=null)
				{
				String[] extraoralsublist=preauthDtlsVO.getExtraoral();
				for (int i=0 ; i<extraoralsublist.length; i++)
				{
					List<LabelBean> extraoralsubmodlist=null;
					extraoralsubmodlist=patientService.getdentalexaminationDtls(extraoralsublist[i]);
					if("CH4".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist0", extraoralsubmodlist);
					}
					if("CH3".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist1", extraoralsubmodlist);
					}
					if("CH2".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist2", extraoralsubmodlist);
					}
					if("CH1".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist3", extraoralsubmodlist);
					}
				}
				}
				if(preauthDtlsVO.getSubdentalrl0()!=null)
				{
				String lymphnodessublist=preauthDtlsVO.getSubdental0();
				
					List<LabelBean> lymphnodessubmodlist=null;
					lymphnodessubmodlist=patientService.getdentalexaminationDtls(lymphnodessublist);
					request.setAttribute("Dentalsublistrl0", lymphnodessubmodlist);
				}
				if(preauthDtlsVO.getOcclusionType()!=null)
				{
					String occlusiondtlslist=preauthDtlsVO.getOcclusion();
					List<LabelBean> occlusiondtlssublist=null;
					occlusiondtlssublist=patientService.getdentalexaminationDtls(occlusiondtlslist);
					request.setAttribute("Dentalsublist", occlusiondtlssublist);
				}
				if(preauthDtlsVO.getSubdentaljaws1()!=null)
				{
				String jawsvalue=preauthDtlsVO.getSubdental1();
				List<LabelBean> jawsubmodlist=null;
			    jawsubmodlist=patientService.getdentalexaminationDtls(jawsvalue);
			    request.setAttribute("Dentalsublistjaws1", jawsubmodlist);				
				}
				
				request.setAttribute("denatlcasesheet", preauthDtlsVO);	
				
				if(preauthDtlsVO.getMedicalDtlsid()!=null )
			    {
				String[] medicalList = preauthDtlsVO.getMedicalDtlsid();
			    patientForm.setMedicalDtlsid(medicalList);
				}
				if(preauthDtlsVO.getShowMedicalTextval()!=null)
				{
					patientForm.setShowMedicalTextval(preauthDtlsVO.getShowMedicalTextval());
				}
				
				preauthDtlsVO.setDentalFlg(dentalFlg);
				if(preauthDtlsVO.getDentalFlg()!=null && "Y".equalsIgnoreCase(preauthDtlsVO.getDentalFlg())){
					
					/*added by pavan for the dental case sheet fetching new options add to sheet*/
					List<LabelBean> extraoraldtls=null;
					List<LabelBean> intraoraldtls=null;
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					List<LabelBean> permanentdentdtlsothr=null;
					List<LabelBean> medicalhsitorydtls=null;
			        String extraoral="HD1";
					String intraoral="HD2";
					String occlusion="CH12";
					String deciduousdent ="CH13";
					String  permanentdent="CH14";
					String  permanentdentothr="CH91";
					String  medicalHsitory="HD6";

					medicalhsitorydtls=patientService.getdentalexaminationDtls(medicalHsitory);
					extraoraldtls=patientService.getdentalexaminationDtls(extraoral);
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					permanentdentdtls=patientService.getdentalexaminationDtls(permanentdent);
					permanentdentdtlsothr=patientService.getdentalexaminationDtls(permanentdentothr);
					
					//patientForm.setMedicalhsitorydtls(medicalhsitorydtls);
                    patientForm.setExtraoraldtls(extraoraldtls);
					patientForm.setIntraoraldtls(intraoraldtls);
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					
					request.setAttribute("medicalhsitorydtls", medicalhsitorydtls);
					request.setAttribute("extraoraldtls", extraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", deciduousdentdtls);
					request.setAttribute("permanentdentdtls", permanentdentdtls);
					request.setAttribute("permanentdentdtlsothr", permanentdentdtlsothr);
							
							//request.setAttribute("patientForm", );										
					patientDtlsVo=patientService.getPatientDentalDtls(ehfPatient.getPatientId());
					patientForm.setDiagnosis(patientDtlsVo.getDiagnosis());
					patientForm.setAdmissionDetails(patientDtlsVo.getAdmissionDetails());
					patientForm.setPatientType(patientDtlsVo.getPatientType());
					patientForm.setAdvancedInvestigations(patientDtlsVo.getAdvancedInvestigations());
					patientForm.setFollowupAdv(patientDtlsVo.getFollowupAdv());
					
					request.setAttribute("dentalDtls", patientDtlsVo);
				}
			    patientForm.setComplaints(preauthDtlsVO.getComplaintType());
			    patientForm.setComplaintCode(preauthDtlsVO.getComplaintType());
			    patientForm.setOtherComplaint(preauthDtlsVO.getOtherComplaintName());
			    patientForm.setPatComplaintCode(preauthDtlsVO.getPatComplaint());
			    patientForm.setPresentHistory(preauthDtlsVO.getPresentIllness());
			    patientForm.setDrughistoryid(preauthDtlsVO.getDrugHst());
			    if(preauthDtlsVO.getDrugHstVal()!=null || preauthDtlsVO.getDrugHstVal()!="")
			    patientForm.setDrughistory(preauthDtlsVO.getDrugHstVal());
				if(preauthDtlsVO.getPersonalHis()!=null && !preauthDtlsVO.getPersonalHis().equalsIgnoreCase(""))
			    {
				String[]  persHist = preauthDtlsVO.getPersonalHis().split("~");
			    patientForm.setPersonalHist(persHist);
				}
				if(preauthDtlsVO.getExamFindg()!=null && !preauthDtlsVO.getExamFindg().equalsIgnoreCase(""))
			    {
				String[] examfndList = preauthDtlsVO.getExamFindg().split("~");
			    patientForm.setExaminationFnd(examfndList);
				}
			    
			    if(preauthDtlsVO.getIpOpFlag()!=null && !preauthDtlsVO.getIpOpFlag().equalsIgnoreCase(""))
			    	{
			    	patientForm.setPatientType(preauthDtlsVO.getIpOpFlag());
			    	patientForm.setDiagType(preauthDtlsVO.getDiagnosisType());
			    	patientForm.setDiagCode(preauthDtlsVO.getDiagnosisType());
			    	patientForm.setMainCatName(preauthDtlsVO.getMainCatName());
			    	patientForm.setMainCatCode(preauthDtlsVO.getMainCatName());
			    	patientForm.setCatName(preauthDtlsVO.getCatId());
			    	patientForm.setCatCode(preauthDtlsVO.getCatId());
			    	patientForm.setSubCatName(preauthDtlsVO.getSubCatName());
			    	patientForm.setSubCatCode(preauthDtlsVO.getSubCatName());
			    	if(preauthDtlsVO.getOtherDiagName()!=null && !("").equalsIgnoreCase(preauthDtlsVO.getOtherDiagName()))
			    	{
			    	patientForm.setDiagOthersName(preauthDtlsVO.getOtherDiagName());
			    	request.setAttribute("otherDiag","Y");
			    	}
			    	if(preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("IP"))
			    	{
			    		patientForm.setIpNo(preauthDtlsVO.getPatientIPNo());
			    		patientForm.setIpDiagnosedBy(preauthDtlsVO.getDocType());
			    		patientForm.setIpDoctorName(preauthDtlsVO.getDocName());
			    		patientForm.setDocRegNo(preauthDtlsVO.getDocReg());
			    		patientForm.setDocQual(preauthDtlsVO.getDocQual());
			    		patientForm.setDocMobileNo(preauthDtlsVO.getDocMobNo());
			    		patientForm.setAdmissionType(preauthDtlsVO.getAdmType());
			    		patientForm.setLegalCase(preauthDtlsVO.getLegalCaseCheck());
			    		patientForm.setLegalCaseNo(preauthDtlsVO.getLegalCaseNo());
			    		patientForm.setPoliceStatName(preauthDtlsVO.getPoliceStatName());
			    		if(preauthDtlsVO.getDate()!=null)
			    		patientForm.setIpDate(sdf.format(preauthDtlsVO.getDate()));
			    		patientForm.setRemarks(preauthDtlsVO.getRemarks());
			    		List<PreauthVO> investList = patientService.getcaseSurgeryDtls(lStrCaseId);
			    
			    		String investLst="";List<PreauthVO> finlInvestLst = new ArrayList<PreauthVO>();
			    		if(investList!=null && investList.size()>0){
			    			
			    			for(PreauthVO preVO: investList){
			    				
			    				if(preVO.getLstSplInvet()!=null && preVO.getLstSplInvet().size()>0)
			    					{
			    					for(PreauthVO preVO1 : preVO.getLstSplInvet()){
			    						investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    						PreauthVO preVO2 = new PreauthVO();
			    						preVO2.setAsriCatName(preVO.getAsriCatName());
			    						preVO2.setCatId(preVO.getCatId());
			    						if(preVO.getCatId()!=null && preVO.getCatId().contains("S"))
			    							request.setAttribute("surgProc","Y");
			    						preVO2.setCatName(preVO.getCatName());
			    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
			    						preVO2.setProcName(preVO.getProcName());
			    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
			    						preVO2.setSeqNo(preVO.getSeqNo());
			    						preVO2.setDocName(preVO.getDocName());
			    						preVO2.setDocReg(preVO.getDocReg());
			    						preVO2.setDocMobNo(preVO.getDocMobNo());
			    						preVO2.setDocQual(preVO.getDocQual());
			    						preVO2.setOpProcUnits(preVO.getOpProcUnits());
			    						preVO2.setTherapyId(preVO1.getTherapyId());
			    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   						
			    						investLst = investLst+"~"+preVO1.getTherapyId()+"~"+preVO1.getFilename()+"~"+preVO.getOpProcUnits();
			    						//Added by Srikalyan for Dental Conditions
			    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    						preVO2.setFilename(preVO1.getFilename());
			    						preVO2.setFilePath(preVO1.getIpOpFlag());
			    						if(preVO1.getIpOpFlag()!=null && !("").equalsIgnoreCase(preVO1.getIpOpFlag()))
			    						preVO2.setName("View");
			    						else
			    							preVO2.setName("NA");	
			    						preVO2.setAuditName(preVO.getIcdProcCode()+preVO1.getTherapyId());
			    						preVO2.setInvestRemarks(preVO1.getTherapyId());	
				    					finlInvestLst.add(preVO2);
			    					}
			    					}
			    					else
			    					{
			    					investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    					investLst = investLst+"~NA~NA"+"~"+preVO.getOpProcUnits();
		    						//Added by Srikalyan for Dental Conditions
		    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    					PreauthVO preVO2 = new PreauthVO();
			    					preVO2.setAsriCatName(preVO.getAsriCatName());
		    						preVO2.setCatId(preVO.getCatId());
		    						preVO2.setCatName(preVO.getCatName());
		    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
		    						preVO2.setProcName(preVO.getProcName());
		    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
		    						preVO2.setSeqNo(preVO.getSeqNo());
		    						preVO2.setDocName(preVO.getDocName());
		    						preVO2.setDocReg(preVO.getDocReg());
		    						preVO2.setDocMobNo(preVO.getDocMobNo());
		    						preVO2.setDocQual(preVO.getDocQual());
		    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   		
		    						preVO2.setFilename("NA");
		    						preVO2.setFilePath("NA");
		    						preVO2.setName("NA");preVO2.setAuditName(preVO2.getIcdProcCode()+"NA");
		    						preVO2.setInvestRemarks("NA");
			    					finlInvestLst.add(preVO2);
			    					}		    							    				
			    			}
			    			
			    			patientForm.setInvestigationLt(finlInvestLst);
			    			request.setAttribute("invetLst",investLst);
			    		}
			    	}
			    	else if(preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("OP") || preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("IPM"))
			    	{
			    		
			    		/*Added to get active state of op case*/
			    		String opActiveMsg=null;
			    		opActiveMsg=patientService.getDaysActiveOP(lStrCaseId);
			    		if(opActiveMsg!=null)
			    		{
			    			System.out.println(opActiveMsg);
			    			request.setAttribute("opActiveMsg",opActiveMsg);
			    			patientForm.setOpActiveMsg(opActiveMsg);
			    		}
			    		
			    		
			    		/*Added by venkatesh to  get cross conultation details */
			    		
			    		List<LabelBean> consultData=new ArrayList<LabelBean>();
			    		consultData=patientService.getConsultDtls(patientId);
			    		patientForm.setConsultData(consultData);
			    		String consultationData="";
			    		if(consultData!=null && consultData.size()>0)
			    		{
			    			int count=0;
			    			
			    			for(LabelBean docData:consultData)
			    			{
			    				if(count>0)
			    				{
			    					consultationData=consultationData+"$";
			    				}
			    				consultationData=consultationData+docData.getUnitName()+"~"+docData.getHodName();
			    				count++;
			    			}
			    		}
			    	request.setAttribute("consultationData",consultationData);	
			    	patientForm.setOpNo(preauthDtlsVO.getPatientIPNo());
			    	patientForm.setOpRemarks(preauthDtlsVO.getRemarks());
			    	patientForm.setDiagnosedBy(preauthDtlsVO.getDocType());	
			    	if(preauthDtlsVO.getDrugList()!=null && preauthDtlsVO.getDrugList().size()>0){
			    	patientForm.setDrugLt(preauthDtlsVO.getDrugList());
			    	String drugLst="";
			    	for(DrugsVO drugVO: preauthDtlsVO.getDrugList()){
			    		if(drugVO.getDRUGCODE()!=null && drugVO.getDRUGCODE().contains("OD")){
			    			
			    			//if(hospCode!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode))
			    				if(hospGovu!=null && "G".equalsIgnoreCase(hospGovu))
			    				patientForm.setOtherDrugName(drugVO.getOTHERDRUGNAME());
			    			else
			    			{
			    			drugLst = drugLst + drugVO.getOTHERDRUGNAME()+"~"+drugVO.getDRUGCODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"@#";
			    			otherDrugCount=Integer.parseInt(drugVO.getDRUGCODE().substring(2,drugVO.getDRUGCODE().length()));
			    			}
			    		}
			    		else
			    		{
			    			drugLst = drugLst + drugVO.getDRUGTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getPSUBGRPCODE()+"~"+drugVO.getCSUBGRPCODE()+"~"+drugVO.getDRUGCODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"@#";
			    		}
			    	}
					request.setAttribute("drugsLst",drugLst);
					patientForm.setOtherDrugCount(otherDrugCount);
					request.setAttribute("otherDrugCount",otherDrugCount);
			    	}
			    	}	
			    	else if(preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("DOP"))
			    	{

			    		patientForm.setIpNo(preauthDtlsVO.getPatientIPNo());
			    		patientForm.setIpDiagnosedBy(preauthDtlsVO.getDocType());
			    		patientForm.setIpDoctorName(preauthDtlsVO.getDocName());
			    		patientForm.setDocRegNo(preauthDtlsVO.getDocReg());
			    		patientForm.setDocQual(preauthDtlsVO.getDocQual());
			    		patientForm.setDocMobileNo(preauthDtlsVO.getDocMobNo());
			    		patientForm.setAdmissionType(preauthDtlsVO.getAdmType());
			    		patientForm.setLegalCase(preauthDtlsVO.getLegalCaseCheck());
			    		patientForm.setLegalCaseNo(preauthDtlsVO.getLegalCaseNo());
			    		patientForm.setPoliceStatName(preauthDtlsVO.getPoliceStatName());
			    		if(preauthDtlsVO.getDate()!=null)
			    		patientForm.setIpDate(sdf.format(preauthDtlsVO.getDate()));
			    		patientForm.setRemarks(preauthDtlsVO.getRemarks());
			    		List<PreauthVO> investList = patientService.getcaseSurgeryDtls(lStrCaseId);
			    
			    		String investLst="";List<PreauthVO> finlInvestLst = new ArrayList<PreauthVO>();
			    		if(investList!=null && investList.size()>0){
			    			
			    			for(PreauthVO preVO: investList){
			    				
			    				if(preVO.getLstSplInvet()!=null && preVO.getLstSplInvet().size()>0)
			    					{
			    					for(PreauthVO preVO1 : preVO.getLstSplInvet()){
			    						investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    						PreauthVO preVO2 = new PreauthVO();
			    						preVO2.setAsriCatName(preVO.getAsriCatName());
			    						preVO2.setCatId(preVO.getCatId());
			    						if(preVO.getCatId()!=null && preVO.getCatId().contains("S"))
			    							request.setAttribute("surgProc","Y");
			    						preVO2.setCatName(preVO.getCatName());
			    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
			    						preVO2.setProcName(preVO.getProcName());
			    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
			    						preVO2.setSeqNo(preVO.getSeqNo());
			    						preVO2.setDocName(preVO.getDocName());
			    						preVO2.setDocReg(preVO.getDocReg());
			    						preVO2.setDocMobNo(preVO.getDocMobNo());
			    						preVO2.setDocQual(preVO.getDocQual());
			    						preVO2.setOpProcUnits(preVO.getOpProcUnits());
			    						preVO2.setTherapyId(preVO1.getTherapyId());
			    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   						
			    						investLst = investLst+"~"+preVO1.getTherapyId()+"~"+preVO1.getFilename()+"~"+preVO.getOpProcUnits();
			    						//Added by Srikalyan for Dental Conditions
			    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    						preVO2.setFilename(preVO1.getFilename());
			    						preVO2.setFilePath(preVO1.getIpOpFlag());
			    						if(preVO1.getIpOpFlag()!=null && !("").equalsIgnoreCase(preVO1.getIpOpFlag()))
			    						preVO2.setName("View");
			    						else
			    							preVO2.setName("NA");	
			    						preVO2.setAuditName(preVO.getIcdProcCode()+preVO1.getTherapyId());
			    						preVO2.setInvestRemarks(preVO1.getTherapyId());	
				    					finlInvestLst.add(preVO2);
			    					}
			    					}
			    					else
			    					{
			    					investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    					investLst = investLst+"~NA~NA"+"~"+preVO.getOpProcUnits();
		    						//Added by Srikalyan for Dental Conditions
		    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    					PreauthVO preVO2 = new PreauthVO();
			    					preVO2.setAsriCatName(preVO.getAsriCatName());
		    						preVO2.setCatId(preVO.getCatId());
		    						preVO2.setCatName(preVO.getCatName());
		    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
		    						preVO2.setProcName(preVO.getProcName());
		    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
		    						preVO2.setSeqNo(preVO.getSeqNo());
		    						preVO2.setDocName(preVO.getDocName());
		    						preVO2.setDocReg(preVO.getDocReg());
		    						preVO2.setDocMobNo(preVO.getDocMobNo());
		    						preVO2.setDocQual(preVO.getDocQual());
		    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   		
		    						preVO2.setFilename("NA");
		    						preVO2.setFilePath("NA");
		    						preVO2.setName("NA");preVO2.setAuditName(preVO2.getIcdProcCode()+"NA");
		    						preVO2.setInvestRemarks("NA");
		    						preVO2.setOpProcUnits(preVO.getOpProcUnits());
			    					finlInvestLst.add(preVO2);
			    					}
			    				
			    				
			    			}
			    			
			    			patientForm.setInvestigationLt(finlInvestLst);
			    			request.setAttribute("invetLst",investLst);
			    		}
			    	
			    	}
			    	}
			    
			    List<LabelBean> symptomsList = new ArrayList<LabelBean>();
			    List<LabelBean> genInvList = new ArrayList<LabelBean>();
			    symptomsList = patientService.getSymptomsDtls(lStrCaseId);
			    String symptomsLst="";
			    int otherSymptomCount=0;
			    for(LabelBean lb:symptomsList){
			    	if(lb.getWFTYPE()!=null && lb.getWFTYPE().contains("OS"))
			    	{
			    		otherSymptomCount++;
			    		patientForm.setOtherSymptomCount(otherSymptomCount);
			    		symptomsLst = symptomsLst+lb.getWFTYPE()+"~"+lb.getDSGNID()+"~"+lb.getID()+"@#";
			    		
			    	}
			    	else
			    	{
			    	symptomsLst = symptomsLst+lb.getUNITID()+"~"+lb.getDSGNID()+"~"+lb.getLEVELID()+"@#";
			    	}
			    }
				request.setAttribute("symptomsList",symptomsList);
				request.setAttribute("symptomsLst",symptomsLst);
				patientForm.setConsultFee(50);
				patientForm.setSymList(symptomsList);
				String genInvestLst="";
				long totInvCost=0;
				if(preauthDtlsVO.getInvList()!=null && preauthDtlsVO.getInvList().size()>0){
					 
					for(PreauthVO preauthVo : preauthDtlsVO.getInvList()){
						LabelBean inv = new LabelBean();
						inv.setID(preauthVo.getSPLINVSTID());
						if(preauthVo.getSPLINVSTID()!=null && preauthVo.getSPLINVSTID().contains("OI"))
						{
							//if(hospCode!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode))
							if(hospGovu!=null && "G".equalsIgnoreCase(hospGovu))
								patientForm.setOtherInvName(preauthVo.getNAME());
							else
							otherInvestCount=Integer.parseInt(preauthVo.getSPLINVSTID().substring(2,preauthVo.getSPLINVSTID().length()));
						}
						inv.setVALUE(preauthVo.getNAME());
						inv.setLVL(preauthVo.getROUTE());
						inv.setACTION(preauthVo.getTEST());
						inv.setPrice(preauthVo.getPRICE());
						if(preauthVo.getPRICE()!=null)
						{
							totInvCost=totInvCost+Long.valueOf(preauthVo.getPRICE());	
						}
						
						genInvestLst = genInvestLst+preauthVo.getTEST()+"~"+preauthVo.getSPLINVSTID()+"~"+preauthVo.getNAME()+"~"+preauthVo.getPRICE()+"@";
						genInvList.add(inv);
					}
					patientForm.setGenInvList(genInvList);
					patientForm.setTotInvestPrice(totInvCost);
					
					patientForm.setTotalOpCost(50+patientForm.getTotInvestPrice());
					request.setAttribute("genInvestFlag", "true");
					request.setAttribute("genInvestLst", genInvestLst);
				}
				
			}			
                
				request.setAttribute("serverDt",serverDt);
				request.setAttribute("schemeId",ehfPatient.getSchemeId());
				request.setAttribute("dentalEnhFlag","N");
				patientForm.setOtherInvestCount(otherInvestCount);
				request.setAttribute("otherInvestCount",otherInvestCount);
				request.setAttribute("PatientOpList",preauthDtlsVO);
				request.setAttribute("hospType",patientForm.getHosptype());
				String ceoApprovalFlag=preauthDtlsVO.getCeoApprovalFlag();
				
				request.setAttribute("dentalSpecialAppvFlag",ceoApprovalFlag);
				//(config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode)) 
				/*dentalrntdbvalue retrieved to redirect the pageto respective case sheet */
				String dentalrntdbvalue=preauthDtlsVO.getTreatmentDntl();
				request.setAttribute("dentalrntdbvalue", dentalrntdbvalue);
				/*
				 * Check whether NIMS Hospital to remove IP surgical radio button 
				 */
				boolean nabhFlag=false;
				boolean  checkNimsMedco= false;
				if(lStrgrpList.contains(config.getString("Group.Medco")) || lStrgrpList.contains(config.getString("Group.Mithra"))){
					nabhFlag=true;													
				}																	
				
				if(nabhFlag){
					if(!"".equalsIgnoreCase(lStrUserId) && lStrUserId != null)
					{
						checkNimsMedco = loginService.checkNimsMedco(lStrUserId);
						
						if(checkNimsMedco){
							//Start CR#4511-SriHari-20/10/24
							String[] highEndFormsResponse = patientService.getHighEndFormsSubmitFlag(ehfPatient.getCardNo(), "cardNo", patientId);
							if(highEndFormsResponse != null){
								request.setAttribute("highEndProformaSubmitFlag", highEndFormsResponse[0]);
								if(highEndFormsResponse[1] != null && !"null".equalsIgnoreCase(highEndFormsResponse[1]))
									request.setAttribute("highEndProformaId", highEndFormsResponse[1]);
								
								if(highEndFormsResponse[2] != null && !"null".equalsIgnoreCase(highEndFormsResponse[2]))
									request.setAttribute("highEndEvaluationFlag", highEndFormsResponse[2]);
							}
							//End CR#4511-SriHari
							request.setAttribute("nimsHosp", "Y");
						}else{
							request.setAttribute("nimsHosp", "N");
						}
						
					}
				}
				if((hospGov!=null && "G".equalsIgnoreCase(hospGov) && lStrCaseId == null) ||
						(hospGov!=null && "G".equalsIgnoreCase(hospGov) && "no".equalsIgnoreCase(dentalrnt)) ||
						(hospGov!=null && "G".equalsIgnoreCase(hospGov) && "no".equalsIgnoreCase(dentalrntdbvalue)))
				{
					lStrPageName="patientDetailsNims";	
				}
				else if ((dentalFlg !=null && "Y".equalsIgnoreCase(dentalFlg) && "yes".equalsIgnoreCase(dentalrnt)) || 
						(dentalFlg !=null && "Y".equalsIgnoreCase(dentalFlg) && "yes".equalsIgnoreCase(dentalrntdbvalue)))
				{
					lStrPageName="dentalPatientDetails";
				}
				else if(checkNimsMedco)
				{
					lStrPageName="patientDetailsNims";	
				}
				else
					lStrPageName="patientDetails";
			}
			else if(pageType.equalsIgnoreCase("print")){
				//CR 9043 added OR condition while submitting abha for print
				if("DJ".equalsIgnoreCase(ehfPatient.getCardType()) || "DAB".equalsIgnoreCase(ehfPatient.getCardType())){
					
					String cardType = ehfPatient.getCardType();
					patientForm.setCard_type(cardType);
					
				List<LabelBean> printDiagnosis = patientService.getPrintDiagnosis(patientId);
				session.setAttribute("printDiagnosis",printDiagnosis);
				String printCat = printDiagnosis.get(0).getCONST();
				String printDiagnos = printDiagnosis.get(0).getVALUE();
				//CR 9043 setting admission details
				Date admDate = printDiagnosis.get(0).getDOB();
				String admNote = printDiagnosis.get(0).getDSGNNAME();
				BigDecimal estAmt = printDiagnosis.get(0).getCOUNT1();
				String fileName = printDiagnosis.get(0).getNEW_VILLAGE();
				
				patientForm.setDiagnosisCategory(printCat);
				patientForm.setDialysis(printDiagnos);
				//admission details
				String formattedDate = "";

				if (admDate != null) {
				    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				    formattedDate = sdf1.format(admDate);
				}
				patientForm.setAdmissionDate(formattedDate);
				patientForm.setAdmissionNote(admNote);
				patientForm.setAge(estAmt);
				patientForm.setFileName(fileName);
				
				
				}
				lStrPageName="printDetails";
			System.out.println("Redirecting to : "+lStrPageName);
			}
		}
		if ("getMedicalSubCat".equalsIgnoreCase(lStrActionVal))
		{
			String medicalSpclty=request.getParameter("medicalSpclty");			
			
			List<String> hospListArray = new ArrayList<String>();
			
			List<LabelBean> medicalCatList1=commonService.getMedicalCatgryList(medicalSpclty);     //getDrugs();
			request.setAttribute("medicalCatList", medicalCatList1);
				if (medicalCatList1 != null && medicalCatList1.size() > 0)
				{	
					for (LabelBean labelBean: medicalCatList1) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							hospListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", hospListArray);
				}
			
			lStrPageName="ajaxResult";
		}
		// Start CR#4511-SriHari-10/10/24
		if("generateOTP".equalsIgnoreCase(lStrActionVal)){
			String patientId = request.getParameter("patientId");
			String resp = patientService.generateOTP(userMobileNo, lStrUserId, patientId);
			String gsonString = new Gson().toJson(resp);   
        	response.getWriter().write(gsonString);
        	return null;
		}
		if("verifyOTP".equalsIgnoreCase(lStrActionVal)){
			String patientId = request.getParameter("patientId");
			String OTP = request.getParameter("otp");
			String resp = patientService.verifyOTP(userMobileNo, lStrUserId, OTP, patientId);
			String gsonString = new Gson().toJson(resp);   
        	response.getWriter().write(gsonString);
        	return null;
		}
		if("highEndOncologySearch".equals(lStrActionVal)){
			try{
				String patientId=request.getParameter("patientId");
				String ajaxFlag = request.getParameter("ajaxFlag");
				String cardNo = request.getParameter("cardNo");
				String caseType = request.getParameter("caseType");
				Map<String,Object> lResMap = new HashMap<String,Object>();
				List<LabelBean> resList = new ArrayList<LabelBean>();
				lResMap.put("patientId", patientId);
				lResMap.put("userName",userName);
				lResMap.put("cardNo", cardNo);
				lResMap.put("roleId", roleId);
				lResMap.put("caseType", caseType);
				if(loggedUserHospId != null && !"".equalsIgnoreCase(loggedUserHospId))
					lResMap.put("loggedUserHospId", loggedUserHospId);
				
				resList=patientService.getProformaHighEndApproval(lResMap); 
				request.setAttribute("oncologyApprovalCasesLst",resList);
				request.setAttribute("roleId", roleId);
				if("Y".equalsIgnoreCase(ajaxFlag)){
					String gsonString = new Gson().toJson(resList);   
		        	response.getWriter().write(gsonString);
		        	return null;
				}else{
					lStrPageName="highEndOncologySearch";
				}
					
			}catch(Exception e){
				GLOGGER.error ( "Exception occurred in highEndOncologySearch actionFlag in PatientAction." +e.getMessage()) ;
			}
		}
		if("saveOncologyCMTEResp".equalsIgnoreCase(lStrActionVal)){
			String patientId = request.getParameter("patientId");
			String remarks = request.getParameter("remarks");
			String status = request.getParameter("status");
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("patientId", patientId);
			hashMap.put("remarks", remarks);
			hashMap.put("status", status);
			hashMap.put("userId", lStrUserId);
			hashMap.put("userName", userName);
			
			String statusResp = patientService.saveOncologyCMTEResp(hashMap);
			
			Gson gson = new Gson();
			String gsonString = gson.toJson(statusResp);   
        	response.getWriter().write(gsonString);
        	return null;
		}
		if("getProformaFormHighEnd".equals(lStrActionVal)){
			try{
				String medicalCat=request.getParameter("medicalCat");
				List<LabelBean> medicalDrugList=commonService.getMedicalDrugList(medicalCat);
				request.setAttribute("medicalDrugList", medicalDrugList);
				String cardNo=request.getParameter("cardNo");
				String checkFormId = request.getParameter("checkFormId");
				String proformaId = request.getParameter("proformaId");
				Map<String,Object> lResMap = new HashMap<String,Object>();
				lResMap.put("cardNo", cardNo);
				lResMap.put("checkFormId", checkFormId);
				lResMap.put("proformaId", proformaId);
				lResMap=patientService.getProformaFormHighEnd(lResMap); 
				request.setAttribute("lResMap",lResMap);
				lStrPageName="highEndProformaForm";
			}catch(Exception e){
				GLOGGER.error ( "Exception occurred in getProformaFormHighEnd actionFlag in PatientAction." +e.getMessage()) ;
			}
		}
		if("getMoleculesForOrgan".equalsIgnoreCase(lStrActionVal)){
			String organId = request.getParameter("organId");
			List<LabelBean> moleculesList = patientService.getMoleculesForOrgan(organId);
			Gson gson = new Gson();
			String gsonString = gson.toJson(moleculesList);   
        	response.getWriter().write(gsonString);
        	return null;
		}
		if("saveProformaFormHighEnd".equals(lStrActionVal)){
			try{
				List<DrugsVO> drugList = new ArrayList<DrugsVO>();
				String cnt = "";
				for(String drug : patientForm.getDrugList()){
					cnt = drug.split("~")[1];
					DrugsVO drugVO = new DrugsVO();
					drugVO.setDrugCode(drug.split("~")[0]);
					drugVO.setDosage(request.getParameter("dosage"+cnt));
					drugVO.setRoute(request.getParameter("frequency"+cnt));
					drugList.add(drugVO);
					
				}
				patientForm.setDrugLt(drugList);
				patientForm.setCrtUsr(lStrUserId);
				Map<String,Object> lResMap=patientService.saveProformaFormHighEnd(patientForm); 
				//request.setAttribute("proformId", patientForm.getProformId());
				request.setAttribute("lResMap",lResMap);
				
				lStrPageName="highEndProformaForm";
			}catch(Exception e){
				GLOGGER.error ( "Exception occurred in getProformaFormHighEnd actionFlag in PatientAction." +e.getMessage()) ;
			}
		}
		if("getOncologyEvaluationResponse".equalsIgnoreCase(lStrActionVal)){
			try{
				String patientId=request.getParameter("patientId");
				Map<String,Object> lResMap = new HashMap<String,Object>();
				lResMap.put("patientNo", patientId);
				lResMap = patientService.getOncologyEvaluationResponse(lResMap); 
				lResMap.put("patientNo", patientId);
				request.setAttribute("lResMap",lResMap);
				lStrPageName = "oncologyEvaluation";
			}catch(Exception le){
				GLOGGER.error ( "Exception occurred in getOncologyEvaluationResponses actionFlag in PatientAction." +le.getMessage()) ;
			}
		}
		if("saveOncologyEvaluationForm".equalsIgnoreCase(lStrActionVal)){
			try{
				String patientId=request.getParameter("patientId");
				patientForm.setCrtUsr(lStrUserId);
				Map<String,Object> lResMap = patientService.saveOncologyEvaluationForm(patientForm); 
				request.setAttribute("lResMap",lResMap);
				request.setAttribute("patientId", patientId);
				lStrPageName = "oncologyEvaluation";
			}catch(Exception le){
				GLOGGER.error ( "Exception occurred in saveOncologyEvaluationForm actionFlag in PatientAction." +le.getMessage()) ;
			}
		}
		if ("getMedicalDrug".equalsIgnoreCase(lStrActionVal))
		{
			String medicalCat=request.getParameter("medicalCat");			
			
			List<String> hospListArray = new ArrayList<String>();
			
			List<LabelBean> medicalDrugList=commonService.getMedicalDrugList(medicalCat);     //getDrugs();
			request.setAttribute("medicalDrugList", medicalDrugList);
				if (medicalDrugList != null && medicalDrugList.size() > 0)
				{	
					for (LabelBean labelBean: medicalDrugList) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							hospListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", hospListArray);
				}
			
			lStrPageName="ajaxResult";
		}		
		if("savePreauthInitforOncologyCases".equalsIgnoreCase(lStrActionVal)){
			String patientId = request.getParameter("patientId");
			String remarks = request.getParameter("remarks");
			String proformaId = request.getParameter("proformaId");
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("patientId", patientId);
			hashMap.put("remarks", remarks);
			hashMap.put("userId", lStrUserId);
			hashMap.put("userName", userName);
			hashMap.put("proformaId", proformaId);
			
			String caseNo = patientService.savePreauthInitforOncologyCases(hashMap);
			
			patientForm.setMsg("Case Registered Successfully with Case ID :");
			patientForm.setCaseId(caseNo);
			patientForm.setPatientType("IPM");
			request.setAttribute("highEndOncologyCase", "P");
			lStrPageName="patient";
		}
		//End CR#4511-SriHari		
		if("getDentalCaseSheetList".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> dentalSubmoduleList=null;
			String type=request.getParameter("type");
			List<String> subdentalList = new ArrayList<String>();
			String mainCompId=request.getParameter("mainCompId");
			
			dentalSubmoduleList=patientService.getdentalexaminationDtls(mainCompId);
			if("mainlst0".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist0", dentalSubmoduleList);
			else if("mainlst1".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist1", dentalSubmoduleList);
			else if("mainlst2".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist2", dentalSubmoduleList);
			else if("mainlst3".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist3", dentalSubmoduleList);
			else if("sublst0".equalsIgnoreCase(type))
				request.setAttribute("Dentalsublistrl0", dentalSubmoduleList);
			else if("sublst1".equalsIgnoreCase(type))
				request.setAttribute("Dentalsublistjaws1", dentalSubmoduleList);
			patientForm.setDentalsublist(dentalSubmoduleList);
			
			if (dentalSubmoduleList != null && dentalSubmoduleList.size() > 0) {
				for (LabelBean labelBean : dentalSubmoduleList) {
					if (labelBean.getID() != null && labelBean.getVALUE() != null)
					{
						if (dentalSubmoduleList != null && dentalSubmoduleList.size() > 0) {
							subdentalList.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						} else
							subdentalList.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
				
						//catList.add(labelBean.getICDCODE() + "~"+ labelBean.getICDNAME());
						}
				}
			}
			
			
			    if(dentalSubmoduleList!=null && dentalSubmoduleList.size()>0)
				request.setAttribute("AjaxMessage", subdentalList);
			     lStrPageName="ajaxResult";
			
		}
/*		if("getSubCategoryList".equalsIgnoreCase(lStrActionVal)){
			List<LabelBean> diaSubCatList=null;
			List finalDiaSubCatList=new ArrayList();
			String lStrDiagCat = request.getParameter("diagCat");
			diaSubCatList=commonService.getDiagnosisSubCat(lStrDiagCat); 
			for(LabelBean labelBean : diaSubCatList)
			{
				LabelBean labelBean1=new LabelBean();
				labelBean1.setID(labelBean.getID()+"~"+labelBean.getVALUE());
				finalDiaSubCatList.add(labelBean1.getID());
			}
			request.setAttribute("AjaxMessage",finalDiaSubCatList);
			lStrPageName="ajaxResult";
		}*/
/*		if("getTherapyCatList".equals(lStrActionVal)) 
		{
			preauthVO=new PreauthVO();       
			List finalTherapyCatList=new ArrayList();
			preauthVO.setHospitalCode(request.getParameter("hospId"));
			List<LabelBean> therapyCatList=new ArrayList<LabelBean>();
			therapyCatList=preauthService.getDocSpecList(preauthVO);  
			for(LabelBean labelBean : therapyCatList)
			{
				LabelBean labelBean1=new LabelBean();
				labelBean1.setID(labelBean.getID()+"~"+labelBean.getVALUE());
				finalTherapyCatList.add(labelBean1.getID());
			}
			request.setAttribute("AjaxMessage",finalTherapyCatList);
			lStrPageName="ajaxResult";	
		}*/
		/*if("getTherapyList".equals(lStrActionVal)) 
		{
			preauthVO=new PreauthVO();
			preauthVO.setDisMainId(request.getParameter("therapyMainId"));
			preauthVO.setTherapyType(request.getParameter("medicalMng"));	    	
			List<LabelBean> getSurgeryList = preauthService.getSurgeryList(preauthVO);
			request.setAttribute("AjaxMessage",getSurgeryList);
			lStrPageName="ajaxResult";	
		}*/
		
		if ("getAllDentalConditions".equalsIgnoreCase(lStrActionVal))
			{
				String icdProcCode=request.getParameter("icdProcCode");
				String patientId=request.getParameter("patientId");
				String patSchemeId=request.getParameter("patSchemeId");
				String deleteProc = request.getParameter("deleteProc");
				String ajaxMsg=null;
				if(icdProcCode!=null && patientId!=null)	
					{
						PreauthVO patCondVO=new PreauthVO();
						PreauthVO patResCondVO=new PreauthVO();
						patCondVO.setPatientID(patientId);
						patCondVO.setIcdProcCode(icdProcCode);
						patCondVO.setScheme(patSchemeId);
						patCondVO.setTestKnown(deleteProc);
						
						String dentalTGSpecialCond=config.getString("dentalTGSpecialCond");
						String[] specialStatus=dentalTGSpecialCond.split("~");
						for(String status:specialStatus)
							{
								if(status!=null && !"".equalsIgnoreCase(status) && status.equalsIgnoreCase(icdProcCode))
									{
										ajaxMsg=patientService.checkSpecialDenatlCond(patCondVO);
									}
								if(ajaxMsg!=null && ajaxMsg.length()>0)
									ajaxMsg="AlertCont~~~"+ajaxMsg;
								else
									ajaxMsg=null;
							}
						if(deleteProc!=null && !"".equalsIgnoreCase(deleteProc) && "Y".equalsIgnoreCase(deleteProc))
							ajaxMsg=null;
						
						if(ajaxMsg==null || (ajaxMsg!=null && ajaxMsg.length()<1))
							{
								patResCondVO=patientService.validateAndGetDentalCond(patCondVO);
								if(patResCondVO!=null)
									{	
										Gson gson=new Gson();
										ajaxMsg=gson.toJson(patResCondVO);
									}			
							}
						
					}
				request.setAttribute("AjaxMessage", ajaxMsg);
				lStrPageName="ajaxResult";
			}
		if ("getProcName".equalsIgnoreCase(lStrActionVal))
			{
				String ajaxMsg=null;
				String icdProcCode=request.getParameter("icdProcCode");
				callType=request.getParameter("callType");
				String patSchemeId=request.getParameter("patSchemeId");
				if(icdProcCode !=null && !"".equalsIgnoreCase(icdProcCode) &&
						callType!=null && !"".equalsIgnoreCase(callType) &&
						patSchemeId!=null && !"".equalsIgnoreCase(patSchemeId))
					{
						if(callType.equalsIgnoreCase("Ajax"))
							{
								PreauthVO resultVO=new PreauthVO();
								resultVO=patientService.getProcName(icdProcCode,patSchemeId);
								if(resultVO!=null && resultVO.getProcName()!=null)
									{	
										ajaxMsg =resultVO.getProcName();
									}
							}
					}
				request.setAttribute("AjaxMessage", ajaxMsg);
				lStrPageName="ajaxResult";
				
			}
		if ("getPersonalHistory".equalsIgnoreCase(lStrActionVal))
		{
			String parntCode=null;
			List<String> personalHistoryList = new ArrayList<String>(); 
			if(request.getParameter("parntCode")!=null && !request.getParameter("parntCode").equals(""))
			{
				parntCode=request.getParameter("parntCode");
			}
			List<LabelBean> pHistoryList=null;
			pHistoryList=commonService.getPersonalHistory(parntCode);
			try
			{
				for (LabelBean labelBean: pHistoryList) {
					if (labelBean.getID() != null && 
							labelBean.getVALUE() != null)
					{
						personalHistoryList.add(labelBean.getID() + "~" + labelBean.getVALUE());
					}
				}
				if (personalHistoryList != null && personalHistoryList.size() > 0)

					request.setAttribute("AjaxMessage", personalHistoryList);
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred in Ajax getLocations actionFlag in PatientAction." +e.getMessage()) ;
			}
			lStrPageName="ajaxResult";
		}
		if ("getTherapyInvestigations".equalsIgnoreCase(lStrActionVal))
		{
			ArrayList<String> therapyList=new ArrayList<String>();
			String therapyId=null;String asriCode=null,scheme=null;
			if(request.getParameter("therapyId")!=null && !request.getParameter("therapyId").equals(""))
			{
				therapyId=request.getParameter("therapyId");        		
			}
			if(request.getParameter("asriCode")!=null && !request.getParameter("asriCode").equals(""))
			{
				asriCode=request.getParameter("asriCode");        		
			}
			if(request.getParameter("scheme")!=null && !request.getParameter("scheme").equals(""))
			{
				scheme=request.getParameter("scheme");        		
			}
			patientVO=new PatientVO();
			patientVO.setTherapyCatId(therapyId);
			patientVO.setAsriCatCode(asriCode);
			patientVO.setSchemeId(schemeId);
			List<String> getInvestigationsList = patientService.getSpecialInvestigationLst(patientVO);
			GLOGGER.info("size of List"+getInvestigationsList.size());
			String procedureType= patientService.getProcedureType(therapyId, asriCode);
			GLOGGER.info("Proc Type"+procedureType);
			request.setAttribute("AjaxMessage",getInvestigationsList);
			request.setAttribute("AjaxMessage1",procedureType);
			lStrPageName="ajaxResult";	
		}
		if ("deleteInvestigationsOnload".equalsIgnoreCase(lStrActionVal))
		{
			String investId = request.getParameter("investId");
			String caseId = request.getParameter("caseId");
			String procCode = request.getParameter("procId");
			String asriCode =  request.getParameter("asriCode");
			String result = patientService.deleteInvestigations(caseId,procCode,investId,asriCode);
			request.setAttribute("AjaxMessage",result);
			lStrPageName="ajaxResult";	
		}	if ("deleteGenInvest".equalsIgnoreCase(lStrActionVal))
		{
			String investId = request.getParameter("investId");
			String patientId = request.getParameter("patientNo");
			String result = patientService.deleteGenInvest(patientId,investId);
			request.setAttribute("AjaxMessage",result);
			lStrPageName="ajaxResult";	
		}		
		if("getDrugSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugTypeId");        		
			}
			drugSubList=patientService.getDrugSubList(drugTypeId);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getOpDrugSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugTypeId");        		
			}
			drugSubList=patientService.getOpDrugSubList(drugTypeId,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getPharDrugList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("lStrDrugSubTypeId")!=null && !request.getParameter("lStrDrugSubTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugSubTypeId");        		
			}
			drugSubList=patientService.getOpPharSubList(drugTypeId,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getOpChemSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String pharSubCode=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("pharSubCode")!=null && !request.getParameter("pharSubCode").equals(""))
			{
				pharSubCode=request.getParameter("pharSubCode");        		
			}
			drugSubList=patientService.getOpChemSubList(pharSubCode,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getChemSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String cSubGrpCode=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("cSubGrpCode")!=null && !request.getParameter("cSubGrpCode").equals(""))
			{
				cSubGrpCode=request.getParameter("cSubGrpCode");        		
			}
			drugSubList=patientService.getChemSubList(cSubGrpCode,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getRouteList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> routeList=null;
			String routeTypeCode=null;
			if(request.getParameter("routeTypeCode")!=null && !request.getParameter("routeTypeCode").equals(""))
			{
				routeTypeCode=request.getParameter("routeTypeCode");        		
			}
			ipOpType = request.getParameter("ipOpType");
			routeList=patientService.getRouteList(routeTypeCode,ipOpType);
			if (routeList != null && routeList.size() > 0)
				request.setAttribute("AjaxMessage", routeList);
			lStrPageName="ajaxResult";
		}
		if("getStrengthList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> strengthList=null;
			String strengthTypeCode=null;
			if(request.getParameter("strengthTypeCode")!=null && !request.getParameter("strengthTypeCode").equals(""))
			{
				strengthTypeCode=request.getParameter("strengthTypeCode");        		
			}
			ipOpType = request.getParameter("ipOpType");
			strengthList=patientService.getStrengthList(strengthTypeCode,ipOpType);
			if (strengthList != null && strengthList.size() > 0)
				request.setAttribute("AjaxMessage", strengthList);
			lStrPageName="ajaxResult";
		}
		if ("getDrugList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugListArray = new ArrayList<String>();
			List<LabelBean> drugList=null;
			String drugSubTypeId=null;
			if(request.getParameter("lStrDrugSubTypeId")!=null && !request.getParameter("lStrDrugSubTypeId").equals(""))
			{
				drugSubTypeId=request.getParameter("lStrDrugSubTypeId");        		
			}
			drugList=commonService.getAsriDrugs(drugSubTypeId);
			try
			{
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
				GLOGGER.error ( "Exception occurred in Ajax getDrugList actionFlag in PatientAction." +e.getMessage()) ;
			}
			lStrPageName="ajaxResult";	
		}
		if("getDrugDetails".equalsIgnoreCase(lStrActionVal))
		{
			String drugCode=null;
			if(request.getParameter("lStrDrugCode")!=null && !request.getParameter("lStrDrugCode").equals(""))
			{
				drugCode=request.getParameter("lStrDrugCode");        		
			}
			String drugDetails=patientService.getDrugDetails(drugCode);

			request.setAttribute("AjaxMessage",drugDetails);
			lStrPageName="ajaxResult";	
		}
		if ("getDoctorListAjax".equalsIgnoreCase(lStrActionVal))
		{
			String lStrHospId=null;
			lStrHospId=request.getParameter("hospId");
			List<String> doctorsList = new ArrayList<String>();
			List<LabelBean> lDoctorList=null;
			String lStrDocType=null;
			if(request.getParameter("docTypeVal")!=null && !request.getParameter("docTypeVal").equals(""))
			{
				lStrDocType=request.getParameter("docTypeVal");
				try{
					if(request.getParameter("patientType")!=null && !request.getParameter("patientType").equals(""))
					{
						String consultant="";
						String therapyCategory=request.getParameter("therapyCategory");
						if(config.getString("doctorType.InHouseDoctor").equalsIgnoreCase(lStrDocType))
						{
							consultant="N";
						}
						else if(config.getString("doctorType.Consultant").equalsIgnoreCase(lStrDocType))
						{
							consultant="Y";
						}
						patientVO=new PatientVO();
						patientVO.setHospitalCode(lStrHospId);
						patientVO.setTherapyCatId(therapyCategory);
						patientVO.setDoctorType(consultant);
						lDoctorList=patientService.getDocAvailLst(patientVO);
						lDoctorList=patientService.getDoctorsList(lStrDocType,lStrHospId,schemeId);
					}
					//End Of IP Doctor Details based on therapy category
					else
					{
						lDoctorList=patientService.getDoctorsList(lStrDocType,lStrHospId,schemeId);
					}
				}
				catch(Exception e){
					GLOGGER.error ( "Exception occurred using getDoctorListAjax actionFlag in PatientAction." +e.getMessage()) ;
				}
				if(lDoctorList!=null && lDoctorList.size()>0)
				{
					for (LabelBean labelBean: lDoctorList) {
						if (labelBean.getID() != null && labelBean.getVALUE() != null)
						{
							doctorsList.add(labelBean.getID() + "~" + 
									labelBean.getVALUE());
						}
					}
					GLOGGER.info ( "Retrieving Doctor Details using Ajax Call in PatientAction." ) ;

					//doctorsList.add("0~Others");
				}
				if(doctorsList!=null && doctorsList.size() > 0)
					request.setAttribute("AjaxMessage", doctorsList);
			}
			lStrPageName="ajaxResult";
		}
		if ("getDoctorsDetails".equalsIgnoreCase(lStrActionVal))
		{
			String lStrHospId=null;
			String lStrDocType=null;
			String lStrDocId=null;
			if(request.getParameter("hospId")!=null && !request.getParameter("hospId").equals("")) 
			{
				lStrHospId=request.getParameter("hospId");
			}
			if(request.getParameter("doctorType")!=null && !request.getParameter("doctorType").equals("")) 
			{
				lStrDocType=request.getParameter("doctorType");
			}
			if(request.getParameter("doctId")!=null && !request.getParameter("doctId").equals("")) 
			{
				lStrDocId=request.getParameter("doctId");
			}
			List<LabelBean>  lDocDataList=null;
			try
			{
				lDocDataList= patientService.getSelDocDetails(lStrDocType,lStrHospId,lStrDocId,schemeId) ;
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred using getDoctorsDetails actionFlag in PatientAction." +e.getMessage()) ;
			}
			String lDocResult=null;
			if(lDocDataList!=null && lDocDataList.size() > 0)
			{
				if(lDocDataList.get(0).getID()!=null){
					lDocResult=lDocDataList.get(0).getID();
				}
				else{
					lDocResult="NA";
				}
				if(lDocDataList.get(0).getVALUE()!=null){
					lDocResult=lDocResult+"~"+lDocDataList.get(0).getVALUE();     
				}
				else{
					lDocResult=lDocResult+"~NA";  
				}
				if(lDocDataList.get(0).getDSGNNAME()!=null){
					lDocResult=lDocResult+"~"+lDocDataList.get(0).getDSGNNAME();    
				}
				else{
					lDocResult=lDocResult+"~NA";   
				}

			}
			request.setAttribute("AjaxMessage", lDocResult);
			lStrPageName="ajaxResult";
		}
		if ("saveCaseDetails".equalsIgnoreCase(lStrActionVal))
		{
			String caseStartTime = sds.format(new Date().getTime());
			patientForm.setPatientNo("");
			String saveFlg = request.getParameter("saveFlag");
			
			SimpleDateFormat sdfp=new SimpleDateFormat("dd-MM-yyyy");
			String patCrtDt=sdfp.format(session.getAttribute("patCrtdt"));	
			Date date = new Date();
			String crtDate=sdfw.format(date);
			Date crtDt = sdfw.parse((sdfw.format(date)));
			patientVO=new PatientVO();
			String liNextVal="";
			String surgeryNextVal="";
			EhfmSeq ehfmSeqCase = null;
			EhfmSeq ehfmSeqCaseTherapy = null;
			String lStrPatientId=null;
			String treatingDocRmks=null;
			String investigationList=null;
			String genInvestigationList=null;
			String updateGenInvestList=null;
			int i=0;
			String lDir=null;
			String lFileName=null;
			String lFileExt=null;
			int lCount=0;
			String lStrTotFileName=null;
			Date ldtToday = new Date();
			long ltime = ldtToday.getTime();
			List<LabelBean> lSelInvList=new ArrayList<LabelBean>();
			List<LabelBean> lGenInvList=new ArrayList<LabelBean>();
			List<LabelBean> lUpdateGenInvList=new ArrayList<LabelBean>(); 
			String userId=null;
			String checkType=null;
			String highEndProformaId = null;
			userId=session.getAttribute("EmpID").toString(); 
			if(request.getParameter("patientId")!=null && !request.getParameter("patientId").equals("")){
				lStrPatientId=request.getParameter("patientId");
			}
			if(request.getParameter("treatingDocRmks")!=null && !request.getParameter("treatingDocRmks").equals("")){
				treatingDocRmks=request.getParameter("treatingDocRmks");
			}
			if(request.getParameter("checkType")!=null && !request.getParameter("checkType").equals("")){
				checkType=request.getParameter("checkType");
			}
			if(request.getParameter("highEndProformaId")!=null && !request.getParameter("highEndProformaId").equals("")){
				highEndProformaId=request.getParameter("highEndProformaId");
			}
			//ehfmSeqCase = commonService.getSeqNextVal("CASE_ID");
			//liNextVal = String.valueOf(ehfmSeqCase.getSeqNextVal());
			//int seqNextVal=Integer.parseInt(liNextVal);
			//ehfmSeqCase.setSeqNextVal(Long.valueOf(seqNextVal + 1));
			//commonService.updateSequenceVal(ehfmSeqCase);

			//ehfmSeqCaseTherapy = commonService.getSeqNextVal("CASE_THERAPY_ID");
			//String caseTheNextVal = String.valueOf(ehfmSeqCaseTherapy.getSeqNextVal());
			//Long caseTheSeqNextVal=Long.parseLong(caseTheNextVal);

			//EhfmSeq ehfmSeqCaseTherInvest =  commonService.getSeqNextVal("CASE_THER_INVEST_ID");
			//String caseTheInvestNextVal = String.valueOf(ehfmSeqCaseTherInvest.getSeqNextVal());
			//Long caseTheInvestSeqNextVal=Long.parseLong(caseTheInvestNextVal);

			//EhfmSeq ehfmSeqGenTests =  commonService.getSeqNextVal("GEN_INVEST_ID");
			//String genTestNextVal = String.valueOf(ehfmSeqGenTests.getSeqNextVal());
			//Long genTestSeqNextVal=Long.parseLong(genTestNextVal);

			//EhfmSeq ehfmSeqDrug =  commonService.getSeqNextVal("DRUG_ID");
			String subdentalJawTxt = request.getParameter("subdentalJawTxt");
			//String drugNextVal = String.valueOf(ehfmSeqDrug.getSeqNextVal());
			//Long drugSeqNextVal=Long.parseLong(drugNextVal);
			if(hospGovu!=null)
			patientVO.setHospGov(hospGovu);
			patientVO.setSchemeId(patientForm.getScheme());
			patientVO.setPatientScheme(patientForm.getPatientScheme());
			patientVO.setHospType(patientForm.getHosptype());
			patientVO.setPersonalHistVal(patientForm.getPersonalHistVal());
			patientVO.setExamFndsVal(patientForm.getExamFndsVal());
			//pavan dental case sheet 
			patientVO.setTreatmentDntl(patientForm.getTreatmentDntl());
			patientVO.setDrughistoryid(patientForm.getDrughistoryid());
			if(patientForm.getDrughistory()!=null || patientForm.getDrughistory()!="")
			patientVO.setDrughistory(patientForm.getDrughistory());
			patientVO.setMedicalDtlsid(patientForm.getMedicalDtlsid());
			patientVO.setShowMedicalTextval(patientForm.getShowMedicalTextval());
			patientVO.setExtraoral(patientForm.getExtraoral());
			patientVO.setIntraoral(patientForm.getIntraoral());
			
			//added for swelling and pus discharge details
			patientVO.setSwSite(patientForm.getSwSite());
			patientVO.setSwSize(patientForm.getSwSize());
			patientVO.setSwExtension(patientForm.getSwExtension());
			patientVO.setSwColour(patientForm.getSwColour());
			patientVO.setSwConsistency(patientForm.getSwConsistency());
			patientVO.setSwTenderness(patientForm.getSwTenderness());
			patientVO.setSwBorders(patientForm.getSwBorders());
			patientVO.setPsSite(patientForm.getPsSite());
			patientVO.setPsDischarge(patientForm.getPsDischarge());			
			patientVO.setSubdental0(patientForm.getSubdental0());
			patientVO.setSubdental1(patientForm.getSubdental1());
			patientVO.setSubdental2(patientForm.getSubdental2());
			patientVO.setSubdental3(patientForm.getSubdental3());
			patientVO.setDntsublistoral0(patientForm.getDntsublistoral0());
			patientVO.setDntsublistoral1(patientForm.getDntsublistoral1());
			patientVO.setDntsublistoral2(patientForm.getDntsublistoral2());
			patientVO.setDntsublistoral3(patientForm.getDntsublistoral3());
			patientVO.setDntsublistoral4(patientForm.getDntsublistoral4());
			patientVO.setDntsublistoral5(patientForm.getDntsublistoral5());
			patientVO.setDntsublistoral6(patientForm.getDntsublistoral6());
//			patientVO.setSubdentalrl0(patientForm.getSubdentalrl0());
			patientVO.setSubdentaljaws1(patientForm.getSubdentaljaws1());
			if("CH68".equalsIgnoreCase(patientVO.getSubdental0()))
				patientVO.setSubdentalrltxt(patientForm.getSubdentalrltxt());
			else
				patientVO.setSubdentalrl0(patientForm.getSubdentalrl0());
//			patientVO.setSubdentaljawstxt(patientForm.getSubdentaljawstxt());
			if(!"".equalsIgnoreCase(subdentalJawTxt) && subdentalJawTxt != null)
				patientVO.setSubdentaljawstxt(subdentalJawTxt);
			patientVO.setCarriesdecidous(patientForm.getCarriesdecidous());
			patientVO.setMissingdecidous(patientForm.getMissingdecidous());
			patientVO.setMobiledecidous(patientForm.getMobiledecidous());
			patientVO.setGrosslydecadedecidous(patientForm.getGrosslydecadedecidous());
			patientVO.setCarriespermanent(patientForm.getCarriespermanent());
			patientVO.setMobilitypermanent(patientForm.getMobilitypermanent());
			patientVO.setRootstumppermannet(patientForm.getRootstumppermannet());
			patientVO.setAttritionpermanent(patientForm.getAttritionpermanent());
			patientVO.setMissingpermanent(patientForm.getMissingpermanent());
			patientVO.setProbingdepth(patientForm.getProbingdepth());
			patientVO.setOtherpermanent(patientForm.getOtherpermanent());
			patientVO.setPreviousDentalTreatment(patientForm.getPreviousDentalTreatment());
			patientVO.setOcclusion(patientForm.getOcclusion());
			patientVO.setOcclusionType(patientForm.getOcclusionType());
			patientVO.setOcclusionOther(patientForm.getOcclusionOther());
			patientVO.setDiagnosis(patientForm.getDiagnosis());
			patientVO.setFollowupAdv(patientForm.getFollowupAdv());
			patientVO.setAdmissionDetails(patientForm.getAdmissionDetails());
			patientVO.setAdvancedInvestigations(patientForm.getAdvancedInvestigations());
			patientVO.setMedicationGiven(patientForm.getMedicationGiven());
			
			/**
			 * Added by ramalakshmi for hubspoke CR
			 */
			if(spokeRole!=null && spokeRole.equalsIgnoreCase(config.getString("preauth_spoke_role")))
			{
				patientVO.setGroupId(spokeRole);
			}
			else if(checkHubSpoke!=null && "HUB".equalsIgnoreCase(checkHubSpoke)){
				patientVO.setGroupId(checkHubSpoke);
			}
			else
			patientVO.setGroupId(roleId);//Added for hubspoke
			
			
			String hubHospId = patientService.getHubSpokeHospId(userIDu,patientVO.getGroupId());
			if(hubHospId!=null && !"".equalsIgnoreCase(hubHospId))
				patientVO.setHubHospId(hubHospId);
			//End of hubspoke changes
			patientVO.setCrtDt(crtDate);

			patientVO.setCaseSurgId(surgeryNextVal);
			patientVO.setPatientId(lStrPatientId);
			patientVO.setCrtUsr(userId);
			patientVO.setLangId(lStrLangID);
			patientVO.setUserRole(roleId);
			//if(patientForm.getComplaints()!=null && !patientForm.getComplaints().equalsIgnoreCase("") && !patientForm.getComplaints().equalsIgnoreCase("-1"))
			patientVO.setComplaints(patientForm.getComplaintCode());
			patientVO.setPatientComplaint(patientForm.getPatientComplaint());
			if(patientForm.getPresentHistory()!=null && patientForm.getPresentHistory().length()>3000)
			{
				patientVO.setPresentHistory(patientForm.getPresentHistory().substring(0, 3000));
			}
			else if(patientForm.getPresentHistory()!=null)
			{
				patientVO.setPresentHistory(patientForm.getPresentHistory());
			}
			else 
				patientVO.setPresentHistory("");
			
			if(patientForm.getOtherComplaint()!=null && !("").equalsIgnoreCase(patientForm.getOtherComplaint()))
			{
				patientVO.setOtherComplaint(patientForm.getOtherComplaint());
			}
			else
			{
				patientVO.setOtherComplaint("");
			}
			patientVO.setPersonalHistory(patientForm.getPersonalHistory());
			patientVO.setFamilyHistory(patientForm.getFamilyHistory());
			patientVO.setPastHistory(patientForm.getPastHistory());
			patientVO.setExaminationFnd(patientForm.getExaminationFnd());
			patientVO.setFamilyHistoryOthr(patientForm.getFamilyHistoryOthr());
			patientVO.setPastHistryOthr(patientForm.getPastHistryOthr());
			patientVO.setPastHistryCancers(patientForm.getPastHistryCancers()); 
			patientVO.setPastHistryEndDis(patientForm.getPastHistryEndDis());
			if(patientForm.getPastHistrySurg()!=null && patientForm.getPastHistrySurg().length()>3000)
			{
				patientVO.setPastHistrySurg(patientForm.getPastHistrySurg().substring(0, 3000));
			}
			else if(patientForm.getPastHistrySurg()!=null)
			{
				patientVO.setPastHistrySurg(patientForm.getPastHistrySurg());
			}
			
			
			if(patientForm.getDiagType()!=null && !patientForm.getDiagType().equalsIgnoreCase("") && !patientForm.getDiagType().equalsIgnoreCase("-1"))
			{
				patientVO.setDiagnosisType(patientForm.getDiagType());
//				GLOGGER.error("Info Message while setting Diagnosis Type "+patientForm.getDiagType()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getMainCatName()!=null && !patientForm.getMainCatName().equalsIgnoreCase("") && !patientForm.getMainCatName().equalsIgnoreCase("-1"))
			{
				patientVO.setMainCatName(patientForm.getMainCatName());
//				GLOGGER.error("Info Message while setting Diagnosis Main Category Code "+patientForm.getMainCatName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getCatName()!=null && !patientForm.getCatName().equalsIgnoreCase("") && !patientForm.getCatName().equalsIgnoreCase("-1"))
			{
				patientVO.setCatName(patientForm.getCatName());
//				GLOGGER.error("Info Message while setting Diagnosis Category Code "+patientForm.getCatName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getSubCatName()!=null && !patientForm.getSubCatName().equalsIgnoreCase("") && !patientForm.getSubCatName().equalsIgnoreCase("-1"))
			{
				patientVO.setSubCatName(patientForm.getSubCatName());
//				GLOGGER.error("Info Message while setting Diagnosis Sub Category Code "+patientForm.getSubCatName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getDiseaseName()!=null && !patientForm.getDiseaseName().equalsIgnoreCase("") && !patientForm.getDiseaseName().equalsIgnoreCase("-1"))
			{
				patientVO.setDiseaseName(patientForm.getDiseaseName());
//				GLOGGER.error("Info Message while setting Diagnosis Disease Code "+patientForm.getDiseaseName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getDisAnatomicalName()!=null && !patientForm.getDisAnatomicalName().equalsIgnoreCase("") && !patientForm.getDisAnatomicalName().equalsIgnoreCase("-1"))
			{
				patientVO.setDisAnatomicalName(patientForm.getDisAnatomicalName());
//				GLOGGER.error("Info Message while setting Diagnosis Disease Anatomical Code "+patientForm.getDisAnatomicalName()+" for patient Id "+lStrPatientId);
			}
			System.out.println( "**************diagOthers : "+patientForm.getDiagOthers());
			patientVO.setOtherDiagName("");
			
			
			
			/*Added by venkatesh for NIMS*/
			String hospitalId=patientForm.getHospitalId();
			if((hospGovu!=null && "G".equalsIgnoreCase(hospGovu)) && !("").equalsIgnoreCase(patientForm.getDiagOthersName()) )
			{
				patientVO.setOtherDiagName(patientForm.getDiagOthersName());
				patientVO.setDisAnatomicalName("Others");
				patientVO.setDiseaseName("Others");
				patientVO.setSubCatName("Others");
				patientVO.setCatName("Others");
				patientVO.setMainCatName("Others");
				patientVO.setDiagnosisType("Others");	
			}
			
			else if(patientForm.getDiagOthers()!=null && ("Y").equalsIgnoreCase(patientForm.getDiagOthers())&& patientForm.getDiagOthersName()!=null && !("").equalsIgnoreCase(patientForm.getDiagOthersName()))
			{
				patientVO.setOtherDiagName(patientForm.getDiagOthersName());
				patientVO.setDisAnatomicalName("Others");
				patientVO.setDiseaseName("Others");
				patientVO.setSubCatName("Others");
				patientVO.setCatName("Others");
				patientVO.setMainCatName("Others");
				patientVO.setDiagnosisType("Others");
			}
			//patientVO.setMainSymptomCode(patientForm.getMainSymptomName());
			//patientVO.setSymptomCode(patientForm.getSymptomName());

			List<LabelBean> symptomsList = new ArrayList<LabelBean>();
			LabelBean symptom = null;
			if(patientForm.getSymptoms()!=null && !patientForm.getSymptoms().equalsIgnoreCase("")){
			String sym=patientForm.getSymptoms().substring(0, patientForm.getSymptoms().length()-1);
			String[] symptoms=sym.split("@,");
			System.out.println(sym);
			for(int z=0;z<symptoms.length;z++)
			{
				String symptm=symptoms[z];
				if(symptm.contains("@"))
					{String[] symptoms1=symptm.split("@");
					symptm=symptoms1[0];
					}
				String[] symptmValues=symptm.split("~");
				System.out.println(symptmValues);
				symptom = new LabelBean();
				if(symptmValues[0]!=null && symptmValues[0].contains("OS"))
				{
					symptom.setID(symptmValues[0]);
					symptom.setSUBCODE("others");
					symptom.setVALUE("others");
					symptom.setOtherSymptomName(symptmValues[2]);
				}
				else
				{
				symptom.setID(symptmValues[0]);
				symptom.setSUBCODE(symptmValues[1]);
				symptom.setVALUE(symptmValues[2]);
				}
				symptomsList.add(symptom);
			}
			patientVO.setSymptoms(symptomsList);
			}
			
			
			/*added by venkatesh for saving consulation details*/
			
			List<LabelBean> consultList = new ArrayList<LabelBean>();
			
			
			if(request.getParameter("consultationData")!=null && !("").equalsIgnoreCase(request.getParameter("consultationData")))
			{
				String[] consultation=request.getParameter("consultationData").split("~");
				
				for(String cons:consultation)
				{
					LabelBean consultData = new LabelBean();
					StringTokenizer st=new StringTokenizer(cons,"$");
					while(st.hasMoreTokens())
					{	
						consultData.setUnitName(st.nextToken());
						consultData.setDiagnoisedBy(st.nextToken());
						consultData.setHodName(st.nextToken());
					}
					consultList.add(consultData);
					
				}
				
				patientVO.setConsultList(consultList);
			}
			
			patientVO.setPatientType(patientForm.getPatientType());
			//patientVO.setCaseId(liNextVal);
			if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IP"))))
			{
				patientVO.setDiagnosedBy(patientForm.getIpDiagnosedBy());
				patientVO.setDoctorName(patientForm.getIpDoctorName());

				
				
				
				//patientVO.setOtherDocName(patientForm.getIpOtherDocName());
				//patientVO.setDocRegNo(patientForm.getIpDocRegNo());
				//patientVO.setDocQual(patientForm.getIpDocQual());
				//patientVO.setDocMobileNo(patientForm.getIpDocMobileNo());

				List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
				CaseTherapyVO caseTherapy = null;
				if(patientForm.getSpeciality()!=null && !patientForm.getSpeciality().equalsIgnoreCase("")){
				String s=patientForm.getSpeciality();
				String otherDocDetails=patientForm.getOtherDocDetailsList();
				String[] specialities=s.split(",");
				String[] otherDocList=otherDocDetails.split(",");
				String caseProcCodes="";Long caseTheSeqNextVal=0L;
				for(int z=0;z<specialities.length;z++)
				{
					String speciality=specialities[z];
					//String otherDocDet=otherDocList[z];
					String[] specValues=speciality.split("~");
					//String[] otherDocValues=otherDocDet.split("~");
					if(!" ".equals(specValues[0]) && !" ".equals(specValues[1]))
					{
						
						caseTherapy = new CaseTherapyVO();
						if(("S16").equals(specValues[0]))
						{
							patientVO.setCochlearYN("Y");
						}
						if(("S19").equals(specValues[0]))
						{
							patientVO.setOrganTransYN("Y");
						}
						caseTherapy.setAsriCatCode(specValues[0]);
						caseTherapy.setICDCatCode(specValues[1]);
						caseTherapy.setICDProcCode(specValues[2]);
						
						caseTherapy.setDocName(specValues[3]);
						caseTherapy.setDocRegNum(specValues[4]);
						caseTherapy.setDocQual(specValues[5]);
						caseTherapy.setDocMobileNo(specValues[6]);
					/*	if(!"-1".equals(specValues[9]))
						{
							caseTherapy.setProcUnits(specValues[9]);
						}*/
						caseTherapy.setProcUnits(specValues[9]);
						caseTherapy.setSurgProcUnits(specValues[9]);
						//caseProcCodes=caseProcCodes+"'"+specValues[2]+"',";
						caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
						caseTherapy.setCaseTherapyId(caseTheSeqNextVal);
						caseTheSeqNextVal=caseTheSeqNextVal+1;
						caseTherapyList.add(caseTherapy);
					}
				}
				}
				patientVO.setCaseTherapy(caseTherapyList);
				//ehfmSeqCaseTherapy.setSeqNextVal(Long.valueOf(caseTheSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqCaseTherapy);

				//patientVO.setCaseProcCodes(caseProcCodes.substring(0, caseProcCodes.length()-1));
				patientVO.setIpNo(patientForm.getIpNo());
				patientVO.setAdmissionType(patientForm.getAdmissionType());
				patientVO.setIpDate(patientForm.getIpDate());
				if(patientForm.getLegalCase()!=null)
				{
					patientVO.setLegalCase(patientForm.getLegalCase());
					if(patientForm.getLegalCase().equalsIgnoreCase("Y"))
					{
						patientVO.setLegalCaseNo(patientForm.getLegalCaseNo());
						patientVO.setPoliceStatName(patientForm.getPoliceStatName());
					}
				}
				if(treatingDocRmks!=null && treatingDocRmks.length()>3000)
				{
					patientVO.setTeleDocremarks(treatingDocRmks.substring(0,3000));
				}
				else
				{
					patientVO.setTeleDocremarks(treatingDocRmks);
				}
				if(patientForm.getRemarks()!=null && patientForm.getRemarks().length()>3000)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks().substring(0,3000));
				}
				else if(patientForm.getRemarks()!=null)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks());
				}
			}
			if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase("IPM"))
			{
				patientVO.setMedicalSplty(patientForm.getMedicalSpclty());
				patientVO.setCategory(patientForm.getMedicalCat());
				patientVO.setDiagnosedBy(patientForm.getDiagnosedBy());
				patientVO.setDoctorName(patientForm.getDoctorName());
				patientVO.setOpRemarks(patientForm.getOpRemarks());
				patientVO.setDocQual(patientForm.getIpDoctorName());
				Long caseTheSeqNextVal=0L;
				caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
				patientVO.setCaseThrpySeq(caseTheSeqNextVal);
				
				
				
				List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
				CaseTherapyVO caseTherapy = null;
				if(patientForm.getSpeciality()!=null && !patientForm.getSpeciality().equalsIgnoreCase("")){
				String s=patientForm.getSpeciality();
				String otherDocDetails=patientForm.getOtherDocDetailsList();
				String[] specialities=s.split(",");
				String[] otherDocList=otherDocDetails.split(",");
				String caseProcCodes="";
				
				
				
				
				for(int z=0;z<specialities.length;z++)
				{
					String speciality=specialities[z];
					//String otherDocDet=otherDocList[z];
					String[] specValues=speciality.split("~");
					//String[] otherDocValues=otherDocDet.split("~");
					if(!" ".equals(specValues[0]) && !" ".equals(specValues[1]))
					{
						
						caseTherapy = new CaseTherapyVO();
						if(("S16").equals(specValues[0]))
						{
							patientVO.setCochlearYN("Y");
						}
						
						
						
						if(("S19").equals(specValues[0]))
						{
							patientVO.setOrganTransYN("Y");
						}
						caseTherapy.setAsriCatCode(specValues[0]);
						caseTherapy.setICDCatCode(specValues[1]);
						caseTherapy.setICDProcCode(specValues[2]);
						
						caseTherapy.setDocName(specValues[3]);
						caseTherapy.setDocRegNum(specValues[4]);
						caseTherapy.setDocQual(specValues[5]);
						caseTherapy.setDocMobileNo(specValues[6]);
					/*	if(!"-1".equals(specValues[9]))
						{
							caseTherapy.setProcUnits(specValues[9]);
						}*/
						caseTherapy.setProcUnits(specValues[9]);
						
						caseTherapy.setSurgProcUnits(specValues[9]);
						//caseProcCodes=caseProcCodes+"'"+specValues[2]+"',";
						caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
						caseTherapy.setCaseTherapyId(caseTheSeqNextVal);
						caseTheSeqNextVal=caseTheSeqNextVal+1;
						
						caseTherapyList.add(caseTherapy);
					}
				}
				}
				patientVO.setCaseTherapy(caseTherapyList);
				patientVO.setOpNo(patientForm.getOpNo());
				patientVO.setAdmissionType(patientForm.getAdmissionType());
				patientVO.setIpDate(patientForm.getIpDate());
				if(patientForm.getLegalCase()!=null)
				{
					patientVO.setLegalCase(patientForm.getLegalCase());
					if(patientForm.getLegalCase().equalsIgnoreCase("Y"))
					{
						patientVO.setLegalCaseNo(patientForm.getLegalCaseNo());
						patientVO.setPoliceStatName(patientForm.getPoliceStatName());
					}
				}
				if(treatingDocRmks!=null && treatingDocRmks.length()>3000)
				{
					patientVO.setTeleDocremarks(treatingDocRmks.substring(0,3000));
				}
				else
				{
					patientVO.setTeleDocremarks(treatingDocRmks);
				}
				if(patientForm.getRemarks()!=null && patientForm.getRemarks().length()>3000)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks().substring(0,3000));
				}
				else if(patientForm.getRemarks()!=null)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks());
				}
			}
			else if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.OP")))
			{
				patientVO.setDiagnosedBy(patientForm.getDiagnosedBy());
				if(patientForm.getScheme()!=null && (config.getString("TG").equalsIgnoreCase(patientForm.getScheme()))
				&& (patientForm.getPatientScheme()!=null && (config.getString("Scheme.EHS").equalsIgnoreCase(patientForm.getPatientScheme())
						&& (patientForm.getHosptype()!=null && (("G").equalsIgnoreCase(patientForm.getHosptype())		
				)))))
				{	
				patientVO.setOpTotPkgAmt(50+patientForm.getTotInvestPrice()+"");
				patientVO.setConsultFee(50+"");
				patientVO.setTotInvestAmt(patientForm.getTotInvestPrice()+"");
				}
				patientVO.setOpNo(patientForm.getOpNo());
				if(patientForm.getOpRemarks()!=null && patientForm.getOpRemarks().length()>3000)
				{
					patientVO.setOpRemarks(patientForm.getOpRemarks().substring(0,3000));
				}
				else if(patientForm.getOpRemarks()!=null)
				{
					patientVO.setOpRemarks(patientForm.getOpRemarks());
				}
				patientVO.setOpDate(serverDt);
				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				DrugsVO drugsVO = null;
				
				/*if(hospitalId!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId) && !("").equalsIgnoreCase(patientForm.getOtherDrugName()) && patientForm.getOtherDrugName()!=null )*/
				if(hospGovu!=null && "G".equalsIgnoreCase(hospGovu) && !("").equalsIgnoreCase(patientForm.getOtherDrugName()) && patientForm.getOtherDrugName()!=null )
				{
					drugsVO = new DrugsVO();
					drugsVO.setDrugTypeName("others");
					drugsVO.setDrugSubTypeName("others");
					drugsVO.setpSubGrpCode("others");
					drugsVO.setcSubGrpCode("others");
					drugsVO.setOtherDrugName(patientForm.getOtherDrugName());
					drugsVO.setDrugName("OD1");
					drugsVO.setRouteType("");
					drugsVO.setRoute("");
					drugsVO.setStrengthType("");
					drugsVO.setStrength("");
					drugsVO.setDosage("");
					drugsVO.setMedicationPeriod("");	
					
					Long drugSeqNextVal = Long.parseLong(patientService.getSequence("OP_DRUG_ID"));
					drugsVO.setDrugId(drugSeqNextVal);
					//drugSeqNextVal=drugSeqNextVal+1;
					drugsList.add(drugsVO);
				}
				
				else if(patientForm.getDrugs()!=null && !patientForm.getDrugs().equalsIgnoreCase("")){
				String s=patientForm.getDrugs().substring(0, patientForm.getDrugs().length()-1);
				String[] drugs=s.split("@,");
				System.out.println(s);
				/*Added by venkatesh for NIMS*/
				
		
				
				for(int z=0;z<drugs.length;z++)
				{
					if(drugs[z].contains("@"))
					{String[] drugs1=drugs[z].split("@");
					drugs[z]=drugs1[0];
					}
					String drug=drugs[z];
					String[] drugValues=drug.split("~");
					drugsVO = new DrugsVO();
					if(drugValues[1]!=null && drugValues[1].contains("OD"))
					{
						drugsVO.setDrugTypeName("others");
						drugsVO.setDrugSubTypeName("others");
						drugsVO.setpSubGrpCode("others");
						drugsVO.setcSubGrpCode("others");
						drugsVO.setOtherDrugName(drugValues[0]);
						drugsVO.setDrugName(drugValues[1]);
						drugsVO.setRouteType(drugValues[2]);
						drugsVO.setRoute(drugValues[3]);
						drugsVO.setStrengthType(drugValues[4]);
						drugsVO.setStrength(drugValues[5]);
						drugsVO.setDosage(drugValues[6]);
						drugsVO.setMedicationPeriod(drugValues[7]);	
					}
					else
					{
					drugsVO.setDrugTypeName(drugValues[0]);
					drugsVO.setDrugSubTypeName(drugValues[1]);
					drugsVO.setpSubGrpCode(drugValues[2]);
					drugsVO.setcSubGrpCode(drugValues[3]);
					drugsVO.setDrugName(drugValues[4]);
					drugsVO.setRouteType(drugValues[5]);
					drugsVO.setRoute(drugValues[6]);
					drugsVO.setStrengthType(drugValues[7]);
					drugsVO.setStrength(drugValues[8]);
					drugsVO.setDosage(drugValues[9]);
					drugsVO.setMedicationPeriod(drugValues[10]);
					
					}
					Long drugSeqNextVal = Long.parseLong(patientService.getSequence("OP_DRUG_ID"));
					drugsVO.setDrugId(drugSeqNextVal);
					//drugSeqNextVal=drugSeqNextVal+1;
					drugsList.add(drugsVO);
				}
				
				}
				patientVO.setDrugs(drugsList);
				//ehfmSeqDrug.setSeqNextVal(Long.valueOf(drugSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqDrug);

				patientVO.setDoctorName(patientForm.getDoctorName());

				patientVO.setOtherDocName(patientForm.getOtherDocName());
				patientVO.setDocRegNo(patientForm.getDocRegNo());
				patientVO.setDocQual(patientForm.getDocQual());
				patientVO.setDocMobileNo(patientForm.getDocMobileNo());
				
				patientVO.setUnitName(patientForm.getUnitName());
				patientVO.setUnitHodName(patientForm.getUnitHodName());
			}
			else if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.DOP"))))
			{

				patientVO.setDiagnosedBy(patientForm.getIpDiagnosedBy());
				patientVO.setDoctorName(patientForm.getIpDoctorName());
                List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
				CaseTherapyVO caseTherapy = null;
				if(patientForm.getSpeciality()!=null && !patientForm.getSpeciality().equalsIgnoreCase("")){
				String s=patientForm.getSpeciality();
				String otherDocDetails=patientForm.getOtherDocDetailsList();
				String[] specialities=s.split(",");
				String[] otherDocList=otherDocDetails.split(",");
				String caseProcCodes="";Long caseTheSeqNextVal=0L;
				for(int z=0;z<specialities.length;z++)
				{
					String speciality=specialities[z];
					//String otherDocDet=otherDocList[z];
					String[] specValues=speciality.split("~");
					//String[] otherDocValues=otherDocDet.split("~");
					if(!" ".equals(specValues[0]) && !" ".equals(specValues[1]))
					{
						
						caseTherapy = new CaseTherapyVO();
						if(("S16").equals(specValues[0]))
						{
							patientVO.setCochlearYN("Y");
						}
						
						if(("S19").equals(specValues[0]))
						{
							patientVO.setOrganTransYN("Y");
						}
						caseTherapy.setAsriCatCode(specValues[0]);
						caseTherapy.setICDCatCode(specValues[1]);
						caseTherapy.setICDProcCode(specValues[2]);
						
						caseTherapy.setDocName(specValues[3]);
						caseTherapy.setDocRegNum(specValues[4]);
						caseTherapy.setDocQual(specValues[5]);
						caseTherapy.setDocMobileNo(specValues[6]);
					/*	if(!"-1".equals(specValues[9]))
						{
							caseTherapy.setProcUnits(specValues[9]);
						}*/
						caseTherapy.setProcUnits(specValues[9]);
						
						caseTherapy.setSurgProcUnits(specValues[9]);
						//caseProcCodes=caseProcCodes+"'"+specValues[2]+"',";
						caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
						caseTherapy.setCaseTherapyId(caseTheSeqNextVal);
						caseTheSeqNextVal=caseTheSeqNextVal+1;
						caseTherapyList.add(caseTherapy);
					}
				}
				}
				patientVO.setCaseTherapy(caseTherapyList);
				patientVO.setIpNo(patientForm.getIpNo());
				//patientVO.setAdmissionType(patientForm.getAdmissionType());
				patientVO.setIpDate(patientForm.getIpDate());
				if(patientForm.getLegalCase()!=null)
				{
					patientVO.setLegalCase(patientForm.getLegalCase());
					if(patientForm.getLegalCase().equalsIgnoreCase("Y"))
					{
						patientVO.setLegalCaseNo(patientForm.getLegalCaseNo());
						patientVO.setPoliceStatName(patientForm.getPoliceStatName());
					}
				}
				if(treatingDocRmks!=null && treatingDocRmks.length()>3000)
				{
					patientVO.setTeleDocremarks(treatingDocRmks.substring(0,3000));
				}
				else
				{
					patientVO.setTeleDocremarks(treatingDocRmks);
				}
				if(patientForm.getRemarks()!=null && patientForm.getRemarks().length()>3000)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks().substring(0,3000));
				}
				else if(patientForm.getRemarks()!=null)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks());
				}
			
			}
			else if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.ChronicOP")))
			{
				patientVO.setDiagnosedBy(patientForm.getDiagnosedBy());
				patientVO.setDoctorName(patientForm.getDoctorName());
				patientVO.setOtherDocName(patientForm.getOtherDocName());
				patientVO.setDocRegNo(patientForm.getDocRegNo());
				patientVO.setDocQual(patientForm.getDocQual());
				patientVO.setDocMobileNo(patientForm.getDocMobileNo());

				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				DrugsVO drugsVO = null;
				/*if(patientForm.getDrugs()!=null){
				String s=patientForm.getDrugs().substring(0, patientForm.getDrugs().length()-1);
				String[] drugs=s.split("@,");
				for(int z=0;z<drugs.length;z++)
				{
					String drug=drugs[z];
					String[] drugValues=drug.split("~");
					drugsVO = new DrugsVO();
					drugsVO.setDrugTypeName(drugValues[0]);
					drugsVO.setDrugSubTypeName(drugValues[1]);
					drugsVO.setDrugName(drugValues[2]);
					drugsVO.setRoute(drugValues[3]);
					drugsVO.setStrength(drugValues[4]);
					drugsVO.setDosage(drugValues[5]);
					drugsVO.setMedicationPeriod(drugValues[6]);
					Long drugSeqNextVal = Long.parseLong(patientService.getSequence("DRUG_ID"));
					drugsVO.setDrugId(drugSeqNextVal);
					//drugsVO.setDrugId(drugSeqNextVal);
					//drugSeqNextVal=drugSeqNextVal+1;
					drugsList.add(drugsVO);
				}
				}*/
				patientVO.setDrugs(drugsList);
				//ehfmSeqDrug.setSeqNextVal(Long.valueOf(drugSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqDrug);

				patientVO.setOpCatCode(patientForm.getOpCatName());
				patientVO.setOpPkgCode(patientForm.getOpPkgName());
				patientVO.setOpIcdCode(patientForm.getOpIcdName());
			}

			GLOGGER.info("PatientId"+lStrPatientId+"while Patient Case registration in PatientAction.") ;

			/*Added for NIMS by venkatesh
			
			
			if(hospitalId!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId) && !("").equalsIgnoreCase(patientForm.getOtherInvName()) && patientForm.getOtherInvName()!=null )
			{
				LabelBean labelBean=new LabelBean();
				labelBean.setVALUE(patientForm.getOtherInvName());
				labelBean.setID("OI1");  	
				
				 labelBean.setPrice(""); 
					
				 lGenInvList.add(labelBean)  ;
				
			
			}
			*/
			
			if(request.getParameter("addTests")!=null && !request.getParameter("addTests").equals(""))
			{
				genInvestigationList=request.getParameter("addTests");
				
				StringTokenizer st=new StringTokenizer(genInvestigationList,"~");
				
				
				while(st.hasMoreTokens()){
					LabelBean labelBean=new LabelBean();
					String lL=st.nextToken();
					 GLOGGER.info("investigation :"+lL);
					 System.out.println("investigation :"+lL);
					String[] st1=new String[3];
					st1=lL.split("\\$");
					//StringTokenizer st1=new StringTokenizer(lL,"$");
				
						labelBean.setVALUE(st1[0]);
						labelBean.setID(st1[1]);   
						 if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.IP")))
							 labelBean.setPrice(""); 
						 else if(patientForm.getScheme()!=null && patientForm.getScheme().equalsIgnoreCase(config.getString("TG")) && (patientForm.getHosptype()!=null && ("G").equalsIgnoreCase(patientForm.getHosptype()))
								 && (patientForm.getPatientScheme()!=null && (config.getString("Scheme.EHS")).equalsIgnoreCase(patientForm.getPatientScheme()))		 
						 )
							
							 if(st1[2]!=null)
						labelBean.setPrice(st1[2]); 
						 else
							 labelBean.setPrice(""); 
						
						 lGenInvList.add(labelBean)  ;
					
				
				}
			}
			
			//Storing removed investigation into List<String> to compare with updateTests and then add to lUpdateGenInvList
			List<String> remList=new ArrayList<String>();
			String genInvestRemoved = request.getParameter("genInvestRemove");
			if(genInvestRemoved!=null && !"".equals(genInvestRemoved))
			{
				StringTokenizer str=new StringTokenizer(genInvestRemoved,"@");
				while(str.hasMoreTokens()){
					String investId=str.nextToken();
					if(investId!=null && !"".equals(investId))
						remList.add(investId);
				}
			}

			if(request.getParameter("updateTests")!=null && !request.getParameter("updateTests").equals(""))
			{
				updateGenInvestList=request.getParameter("updateTests");
				LabelBean labelBean=new LabelBean();
				StringTokenizer st=new StringTokenizer(updateGenInvestList,"~");
				while(st.hasMoreTokens()){
					labelBean=new LabelBean();
					String lL=st.nextToken();
					StringTokenizer st1=new StringTokenizer(lL,"$");
					while(st1!=null && st1.hasMoreTokens()){
						String investId=st1.nextToken();
						if(remList.contains(investId))
						{
							st1=null;
						}
						if(!remList.contains(investId))
						{
							labelBean.setID(investId); 
							labelBean.setVALUE(st1.nextToken());
							lUpdateGenInvList.add(labelBean)  ;
						}
					}
				}
			}
			if(request.getParameter("investigationsSel")!=null && !request.getParameter("investigationsSel").equals(""))
			{
				investigationList=request.getParameter("investigationsSel");
				LabelBean labelBean=new LabelBean();
				StringTokenizer st=new StringTokenizer(investigationList,"~");
				while(st.hasMoreTokens()){
					labelBean=new LabelBean();
					String lL=st.nextToken();
					StringTokenizer st1=new StringTokenizer(lL,"$");
					while(st1.hasMoreTokens()){
						labelBean.setVALUE(st1.nextToken());
						labelBean.setID(st1.nextToken());
						labelBean.setICDCODE(st1.nextToken());
						//labelBean.setPrice(st1.nextToken());
						lSelInvList.add(labelBean)  ;
					}
				}
			}

			FormFile lFormFile=null;

			List<AttachmentVO> lTestsAttachList=new ArrayList<AttachmentVO>(); 
			List<AttachmentVO> lGenTestAttachList=new ArrayList<AttachmentVO>();
			List<AttachmentVO> lUpdGenTestAttachList=new ArrayList<AttachmentVO>();
			String errmsg="";
			//For General Investigations
			if(hospitalId!=null /*&& !config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId)*/)
			{
			for(LabelBean labelBean: lGenInvList){
				//used to avoid deleted attachments-conflict
				while(patientForm.getGenAttach(i)==null )
				{
					i++;
				}
				if(patientForm.getGenAttach(i)!=null ){
					lFormFile=patientForm.getGenAttach(i);
					if (lFormFile.getFileSize() > 204800) 
					{
						errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
					}
				}
				i++;
			}
			//For Updating  General Investigations
			i=0;
			for(LabelBean labelBean: lUpdateGenInvList){
				//used to avoid deleted attachments-conflict
				while(patientForm.getUpdateGenAttach(i)==null )
				{
					if(i==99)
					{
						break;
					}
					i++;
				}
				if(patientForm.getUpdateGenAttach(i)!=null ){
					lFormFile=patientForm.getUpdateGenAttach(i);
					if (lFormFile.getFileSize() > 204800) 
					{
						errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
					}
				}
				i++;
			}
			}

			//For IP Therapy Investigations
			i=0;
			for(LabelBean labelBean: lSelInvList){
				//used to avoid deleted attachments-conflict
				while(patientForm.getAttach(i)==null )
				{
					i++;
				}
				if(patientForm.getAttach(i)!=null ){
					lFormFile=patientForm.getAttach(i);
					if (lFormFile.getFileSize() > 204800) 
					{
						errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
					}
				}
				i++;
			}
		
			String lStrCaseId=null;
			if("".equals(errmsg))
			{
				i=0; 
				//For General Investigations
				for(LabelBean labelBean: lGenInvList){
					AttachmentVO attachmentVO=new AttachmentVO();
					//used to avoid deleted attachments-conflict
					if(hospitalId!=null /*&& !config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId)*/)
					{
					while(patientForm.getGenAttach(i)==null )
					{
						i++;
					}
					
					lFormFile=patientForm.getGenAttach(i);                                     
					lFileName=lFormFile.getFileName();
					if(lFileName!=null && !lFileName.equals(""))
					{
						lCount=lFileName.lastIndexOf(".");
						lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
						// save file in folder's
						String lStrSharePath = 
								config.getString("STORAGE_BOX") + 
								/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
								config.getString("SLASH_VALUE") + lStrPatientId + 
								config.getString("SLASH_VALUE") + 
								config.getString("TESTS_ATTACH")+ 
								config.getString("SLASH_VALUE");

						lStrTotFileName = ltime + "_" + lFileName;
						lDir = lStrSharePath+lStrTotFileName; 
						try
						{
							File lFile = new File(lDir);
							File lDirectory = new File(lStrSharePath);
							boolean lbDirectioryPresent = false; 
							if(!lDirectory.exists())
							{
								lbDirectioryPresent = lDirectory.mkdirs();
							}
							else
							{
								lbDirectioryPresent = true;
							}

							if(lbDirectioryPresent)
							{
								if(lFile.exists())
								{
									lFile.delete();
								}

								FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
								byte[] fileData = lFormFile.getFileData();
								lFileOutputStream.write(fileData);
								lFileOutputStream.flush();
								lFileOutputStream.close();
								lFileOutputStream = null;
							}

						}
						catch(Exception e) {
							GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction." +e.getMessage()) ;
						}
					}}
					attachmentVO.setTestId(labelBean.getID());
					attachmentVO.setTestName(labelBean.getVALUE());
					 /*if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.OP")))*/
					attachmentVO.setPrice(labelBean.getPrice());	 
					//attachmentVO.setFileName(lStrTotFileName);
					if(lFileName!=null && !lFileName.equals(""))
					{
						attachmentVO.setFileName(lFileName);
						attachmentVO.setFileSize(lFormFile.getFileSize());
						attachmentVO.setFileReportPath(lDir);
						attachmentVO.setFileContentType(lFormFile.getContentType());
						attachmentVO.setFileExtsn(lFileExt);
					}
					String genTestSeqNextVal = patientService.getSequence("GEN_INVEST_ID");
					attachmentVO.setAttachId(genTestSeqNextVal);
					//genTestSeqNextVal++;
					lGenTestAttachList.add(attachmentVO);                 
					i++;
				}
				patientVO.setGenAttachmentsList(lGenTestAttachList);  

				//ehfmSeqGenTests.setSeqNextVal(Long.valueOf(genTestSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqGenTests);

				
				i=0; 
				//For Updating  General Investigations
				for(LabelBean labelBean: lUpdateGenInvList){
					AttachmentVO attachmentVO=new AttachmentVO();
					//used to avoid deleted attachments-conflict
					while(patientForm.getUpdateGenAttach(i)==null )
					{
						if(i==99)
						{
							break;
						}
						i++;
					}
					lFormFile=patientForm.getUpdateGenAttach(i);  
					if(lFormFile!=null)
					{
					lFileName=lFormFile.getFileName();
					if(lFileName!=null && !lFileName.equals(""))
					{
						lCount=lFileName.lastIndexOf(".");
						lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
						// save file in folder's
						String lStrSharePath = 
								config.getString("STORAGE_BOX") + 
								/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
								config.getString("SLASH_VALUE") + lStrPatientId + 
								config.getString("SLASH_VALUE") + 
								config.getString("TESTS_ATTACH")+ 
								config.getString("SLASH_VALUE");

						lStrTotFileName = ltime + "_" + lFileName;
						lDir = lStrSharePath+lStrTotFileName; 
						try
						{
							File lFile = new File(lDir);
							File lDirectory = new File(lStrSharePath);
							boolean lbDirectioryPresent = false; 
							if(!lDirectory.exists())
							{
								lbDirectioryPresent = lDirectory.mkdirs();
							}
							else
							{
								lbDirectioryPresent = true;
							}

							if(lbDirectioryPresent)
							{
								if(lFile.exists())
								{
									lFile.delete();
								}

								FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
								byte[] fileData = lFormFile.getFileData();
								lFileOutputStream.write(fileData);
								lFileOutputStream.flush();
								lFileOutputStream.close();
								lFileOutputStream = null;
							}

						}
						catch(Exception e) {
							GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction while saving general investigations " +e.getMessage()) ;
						}
					}
					attachmentVO.setTestId(labelBean.getID());
					attachmentVO.setTestName(labelBean.getVALUE());
					//attachmentVO.setFileName(lStrTotFileName);
					if(lFileName!=null && !lFileName.equals(""))
					{
						attachmentVO.setFileName(lFileName);
						attachmentVO.setFileSize(lFormFile.getFileSize());
						attachmentVO.setFileReportPath(lDir);
						attachmentVO.setFileContentType(lFormFile.getContentType());
						attachmentVO.setFileExtsn(lFileExt);
					}
					String genTestSeqNextVal = patientService.getSequence("GEN_INVEST_ID");
					attachmentVO.setAttachId(genTestSeqNextVal);
					//genTestSeqNextVal++;
					lUpdGenTestAttachList.add(attachmentVO);  
					}
					i++;
					
				}
				patientVO.setUpdGenAttachmentsList(lUpdGenTestAttachList);  
				
				
				
				i=0;
				//For IP Therapy Investigations
				//patientVO.setCaseTherInvestSeqId(caseTheInvestSeqNextVal);
				for(LabelBean labelBean: lSelInvList){
					AttachmentVO attachmentVO=new AttachmentVO(); 
					//used to avoid deleted attachments-conflict
					while(patientForm.getAttach(i)==null )
					{
						i++;
					}
					lFormFile=patientForm.getAttach(i);                                     
					lFileName=lFormFile.getFileName();
					if(lFileName!=null && !("").equalsIgnoreCase(lFileName))
					{
					lCount=lFileName.lastIndexOf(".");
					lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
					// save file in folder's
					String lStrSharePath = 
							config.getString("STORAGE_BOX") + 
							/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
							config.getString("SLASH_VALUE") + lStrPatientId + 
							config.getString("SLASH_VALUE") + 
							config.getString("TESTS_ATTACH")+ 
							config.getString("SLASH_VALUE");

					lStrTotFileName = ltime + "_" + lFileName;
					lDir = lStrSharePath+lStrTotFileName; 
				
					try
					{
						File lFile = new File(lDir);
						File lDirectory = new File(lStrSharePath);
						boolean lbDirectioryPresent = false; 
						if(!lDirectory.exists())
						{
							lbDirectioryPresent = lDirectory.mkdirs();
						}
						else
						{
							lbDirectioryPresent = true;
						}

						if(lbDirectioryPresent)
						{
							if(lFile.exists())
							{
								lFile.delete();
							}

							FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
							byte[] fileData = lFormFile.getFileData();
							lFileOutputStream.write(fileData);
							lFileOutputStream.flush();
							lFileOutputStream.close();
							lFileOutputStream = null;
						}

					}
					catch(Exception e) {
						GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction." +e.getMessage()) ;
					}
					}
					attachmentVO.setTestId(labelBean.getID());
					attachmentVO.setTestName(labelBean.getVALUE());
					//attachmentVO.setFileName(lStrTotFileName);
					attachmentVO.setIcdProcCode(labelBean.getICDCODE());
					attachmentVO.setFileName(lFileName);
					attachmentVO.setFileSize(lFormFile.getFileSize());
					attachmentVO.setFileReportPath(lDir);
					attachmentVO.setFileContentType(lFormFile.getContentType());
					attachmentVO.setFileExtsn(lFileExt);
					String caseTheInvestSeqNextVal = patientService.getSequence("CASE_THER_INVEST_ID");
					attachmentVO.setAttachId(caseTheInvestSeqNextVal);
					//caseTheInvestSeqNextVal++;
					lTestsAttachList.add(attachmentVO);                 
					i++;
				}
				patientVO.setAttachmentsList(lTestsAttachList); 

				//ehfmSeqCaseTherInvest.setSeqNextVal(Long.valueOf(caseTheInvestSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqCaseTherInvest);
                patientVO.setSaveFlag(saveFlg);
                String specRemove = request.getParameter("specRemove");
    			String genInvestRemove = request.getParameter("genInvestRemove");
    			String caseId = request.getParameter("caseId");
                if(specRemove!=null && !specRemove.equalsIgnoreCase("") && caseId!=null && !caseId.equalsIgnoreCase("")){
                	String[] specRem = specRemove.split("@");
                	for(i=0;i<specRem.length;i++){
                		if(specRem[i]!=null && !specRem[i].equalsIgnoreCase("")){
                			String[] specDtls = specRem[i].split("~");
                			patientService.deleteInvestigations(caseId,specDtls[0],specDtls[2],specDtls[1]);         
                		}
                	}
                }
                if(genInvestRemove!=null && !genInvestRemove.equalsIgnoreCase("")){
                	String[] genInvestRem = genInvestRemove.split("@");
                	for(i=0;i<genInvestRem.length;i++){
                		if(genInvestRem[i]!=null && !genInvestRem[i].equalsIgnoreCase("")){
                			patientService.deleteGenInvest(patientVO.getPatientId(),genInvestRem[i]);         
                		}
                	}
                }
                String dentalFlg=request.getParameter("dentalFlg");
                patientVO.setDentalFlg(dentalFlg);
                if(dentalFlg!=null && "Y".equalsIgnoreCase(dentalFlg)){
                	/*String softTissues="";
                	for(String softTissue : patientForm.getSoftTissue()){
                		if("".equalsIgnoreCase(softTissues))
                			softTissues+=softTissue;
                		else
                			softTissues+="~"+softTissue;
                	}*/
                	patientVO.setSoftTissue(StringUtils.join(patientForm.getSoftTissue(), "~"));
                	               	
                	patientVO.setDeciduosDentition(StringUtils.join(patientForm.getChildCaries(), "~"));
                	
                	patientVO.setMissing(StringUtils.join(patientForm.getMissingTeeth(), "~"));
                	
                	patientVO.setCaries(StringUtils.join(patientForm.getCaries(), "~"));
                	
                	patientVO.setDecayed(StringUtils.join(patientForm.getDecayed(), "~"));
                	
                	//patientVO.setMobile(StringUtils.join(patientForm.getMobile(), "~"));
                	
                	patientVO.setAttrition(StringUtils.join(patientForm.getAttrition(), "~"));
                	patientVO.setPreviousDentalTreatment(patientForm.getPreviousDentalTreatment());
                	patientVO.setOcclusion(patientForm.getOcclusion());
                	patientVO.setTmj(patientForm.getTmj());
                	
                	patientVO.setProbeDepth(StringUtils.join(patientForm.getProbeDepth(), "~"));
                	patientVO.setDiagnosis(patientForm.getDiagnosis());
                	patientVO.setFollowupAdv(patientForm.getFollowupAdv());
                	if(patientForm.getPatientType()!=null && "IP".equalsIgnoreCase(patientForm.getPatientType())){
                		patientVO.setAdmissionDetails(patientForm.getAdmissionDetails());
                		patientVO.setAdvancedInvestigations(patientForm.getAdvancedInvestigations());
                	}
                }
                if(patientForm.getPatientScheme()!=null)
                	patientVO.setPatientScheme(patientForm.getPatientScheme());
                
                if(highEndProformaId!=null)
                	patientVO.setHighEndProformaId(highEndProformaId);
                
				lStrCaseId=patientService.saveCaseDetails(patientVO);
				String caseEndTime = sds.format(new Date().getTime());
				
				if(lStrCaseId != null && !"".equalsIgnoreCase(lStrCaseId)){
					String[] tokens = lStrCaseId.split("/");
					String lastToken = tokens[tokens.length-1];
					if(lastToken != null && !"".equalsIgnoreCase(lastToken) && "D".equalsIgnoreCase(lastToken))
						lastToken = tokens[tokens.length-2];
					else
						lastToken = tokens[tokens.length-1];
					String actionDone = commonService.getActionDoneName(lastToken,"ehfCase");
					loggingService.logTime(actionDone, lastToken, caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
				}
				patientForm.setPatientScheme(patientVO.getPatientScheme());
				patientForm.setPatientType(patientVO.getPatientType());
				patientForm.setCaseId(lStrCaseId);
				if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && !"S12MMD".equalsIgnoreCase(patientForm.getMedicalCat())){
					patientForm.setMsg("Case Registered Successfully with Case ID :");
				}
				else if(patientForm.getMedicalCat() != null && "S12MMD".equalsIgnoreCase(patientForm.getMedicalCat())){
					patientForm.setMsg("Case Submitted Successfully for High-End Drugs Oncology Committe for Approval");
					request.setAttribute("highEndOncologyCase", "Y");
				}
				else{
					patientForm.setMsg("Case Saved Successfully with Case ID :");
					if(checkType!=null){
						patientForm.setDisableFlag(checkType);
					}
				}
				if(lStrCaseId!=null && !lStrCaseId.equalsIgnoreCase("")){
					if(lStrCaseId.equalsIgnoreCase("Already Registered")){
						patientForm.setErrMsg("Patient Already Registered");
						patientForm.setCaseId("");
						lStrPageName="patient";
					}
					else{
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit")){
						/*if("true".equalsIgnoreCase(config.getString("SmsRequired")))
						{
							EhfPatient ehfPatient=null;
							ehfPatient=(EhfPatient)patientService.getPatientDetails(lStrPatientId);

							if(ehfPatient.getContactNo().toString()!=null && !ehfPatient.getContactNo().toString().equals(""))
							{
								//ehfmSeq=commonService.getSeqNextVal("PATIENT_SMS_SNO");
								//smsNextVal = String.valueOf(ehfmSeq.getSeqNextVal());
								//int smsSeqNextVal=Integer.parseInt(smsNextVal);
								smsNextVal = patientService.getSequence("PATIENT_SMS_SNO");
								//ehfmSeq.setSeqNextVal(Long.valueOf(smsSeqNextVal + 1));
								//commonService.updateSequenceVal(ehfmSeq);

								String lStrResultMsg=null;
								PatientSmsVO patientSmsVO=new PatientSmsVO();
								patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
								patientSmsVO.setPhoneNo(ehfPatient.getContactNo().toString());
								patientSmsVO.setSmsText("Dear "+ehfPatient.getName().trim()+" , You have been registered as "+ patientForm.getPatientType()+" in "+patientService.getHospName(patientForm.getHospitalId())+" with Case Id "+lStrCaseId);
								patientSmsVO.setEmpCode(ehfPatient.getEmployeeNo());
								patientSmsVO.setEmpName(ehfPatient.getName().trim());
								patientSmsVO.setCrtUsr(userId);
								patientSmsVO.setCrtDt(crtDt);
								patientSmsVO.setSmsAction("Case Registered as "+patientForm.getPatientType());
								patientSmsVO.setPatientId(lStrPatientId);
								lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
							}
						}*/
						if (config.getBoolean("EmailRequired")){
							EhfPatient ehfPatient=null;
							ehfPatient=(EhfPatient)patientService.getPatientDetails(lStrPatientId);
							String mailId=null;
							if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals("")){
								mailId=ehfPatient.getEmailId();
								String[] emailToArray = {mailId};
								EmailVO emailVO = new EmailVO();
								emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
								emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
								emailVO.setFromEmail(config.getString("emailFrom"));
								emailVO.setToEmail(emailToArray);
								emailVO.setSubject(config.getString("patientEmailSubject"));
								Map<String, String> model = new HashMap<String, String>();
								model.put("patientName", ehfPatient.getName().trim());
								model.put("registeredHosp", patientService.getHospName(patientForm.getHospitalId()));
								model.put("status", "Case Registered as "+patientForm.getPatientType()+" with Case Id "+lStrCaseId);
								model.put("statusDate",crtDate);
								
								if(ehfPatient.getPatientScheme()!=null){
										if(ehfPatient.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS"))){
												emailVO.setSubject(config.getString("patientEmailSubjectJournalist"));
												emailVO.setFromEmail(config.getString("emailFromJournalist"));
												emailVO.setTemplateName(config.getString("EHFPatientTemplateNameJourn"));
												commonService.generateNonImageMail(emailVO, model);
										}
										else{
												emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
												commonService.generateMail(emailVO, model);
											}
								}
								else{
										emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
										commonService.generateMail(emailVO, model);
									}
								
								
							}
						}
						}						
					}
				}
				lStrPageName="patient";
			}
			else
			{
				patientForm.setErrMsg("Uploaded File Size Should not exceed 200KB");
				lStrPageName="patient"; 
			}
		}
		if ("telephonicEntry".equalsIgnoreCase(lStrActionVal))
		{

			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);
/*			List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
			session.setAttribute("districtList",distList);*/

			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			// List<LabelBean> castesList=patientService.getCastes();
			session.setAttribute("castesList",castesList);

			//List<LabelBean> relationList=commonService.getComboDetails(lStrRelationsHdrId);
			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);

			// List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			List<LabelBean> hospitalList = commonService.getActiveHospitals();
			session.setAttribute("hospitalList",hospitalList);

			List<LabelBean> diagnTypeList = patientService.getDiagnosisTypes();
			session.setAttribute("diagnTypeList",diagnTypeList);

			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);

			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));

			session.setAttribute("userRole",roleId);
			if(session.getAttribute("userState").toString()!=null)
			{
				schemeId=session.getAttribute("userState").toString();
			}
			request.setAttribute("execScheme", schemeId);
			lStrPageName = "telephonicPatientEntry";
		}
		if("getTherapyCategory".equalsIgnoreCase(lStrActionVal)){
			String lStrHospId=request.getParameter("lStrHospId");
			List<LabelBean> categoryList = commonService.getAsriCategoryList(lStrHospId,schemeId);
			session.setAttribute("AsriCategoryList",categoryList);

			List<String> categoryList1 = new ArrayList<String>();
			for (LabelBean labelBean: categoryList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
					if (categoryList1 != null && categoryList1.size() > 0) {
						categoryList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
					} else
						categoryList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
			}
			if (categoryList1 != null && categoryList1.size() > 0)

				request.setAttribute("AjaxMessage", categoryList1);

			lStrPageName="ajaxResult";
		}
		/*if("getTherapySubcategory".equalsIgnoreCase(lStrActionVal)){
			String lStrCatId=request.getParameter("lStrCatId");
			List<LabelBean> subCategoryList=patientService.getTherapySubCategory(lStrCatId);
	         session.setAttribute("ICDsubCategoryList",subCategoryList);
	         List<String> subCategoryList1 = new ArrayList<String>();
	         for (LabelBean labelBean: subCategoryList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (subCategoryList1 != null && subCategoryList1.size() > 0) {
	                    	subCategoryList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE());
	                    } else
	                    	subCategoryList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE());
	            }
	            if (subCategoryList1 != null && subCategoryList1.size() > 0)
	               request.setAttribute("AjaxMessage", subCategoryList1);

	         lStrPageName="ajaxResult";
		}*/
		if("getTherapyProcedure".equalsIgnoreCase(lStrActionVal)){
			String lStrCatId=request.getParameter("lStrCatId");
			List<LabelBean> procedureList=patientService.getTherapyProcedure(lStrCatId);
			session.setAttribute("ICDprocedureList",procedureList);
			List<String> procedureList1 = new ArrayList<String>();
			for (LabelBean labelBean: procedureList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
					if (procedureList1 != null && procedureList1.size() > 0) {
						procedureList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
					} else
						procedureList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
			}
			if (procedureList1 != null && procedureList1.size() > 0)
				request.setAttribute("AjaxMessage", procedureList1);

			lStrPageName="ajaxResult";
		}
		if("getDiagnMainCategory".equalsIgnoreCase(lStrActionVal)){
			String lStrDiagnId=request.getParameter("lStrDiagnId");
			List<LabelBean> mainCatList=patientService.getDiagnMainCategory(lStrDiagnId);
			session.setAttribute("mainCatList",mainCatList);
			List<String> mainCatList1 = new ArrayList<String>();
			for (LabelBean labelBean: mainCatList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
					if (mainCatList1 != null && mainCatList1.size() > 0) {
						mainCatList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
					} else
						mainCatList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
			}
			if (mainCatList1 != null && mainCatList1.size() > 0)
				request.setAttribute("AjaxMessage", mainCatList1);

			lStrPageName="ajaxResult";
		}
		if("getDiagnCategory".equalsIgnoreCase(lStrActionVal)){
			String lStrDiagnMainId=request.getParameter("lStrDiagnMainId");
			List<LabelBean> categoryList=patientService.getDiagnCategory(lStrDiagnMainId);
			session.setAttribute("categoryList",categoryList);
			List<String> categoryList1 = new ArrayList<String>();
			for (LabelBean labelBean: categoryList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
					if (categoryList1 != null && categoryList1.size() > 0) {
						categoryList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
					} else
						categoryList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
			}
			if (categoryList1 != null && categoryList1.size() > 0)
				request.setAttribute("AjaxMessage", categoryList1);

			lStrPageName="ajaxResult";
		}
		if("getDiagnCatSubDetails".equalsIgnoreCase(lStrActionVal))
		{
			String lStrDiagnCode=request.getParameter("lStrDiagnCode");
			String lStrListType=request.getParameter("lStrListType");
			//List<List<String>> finalSubDetailsList=new ArrayList<List<String>>();
			String finalSubDetailsList="";
			List<LabelBean> subCategoryList=null;
			List<LabelBean> diseaseList=null;
			List<LabelBean> disAnatomicalList=null;
			if("categoryId".equalsIgnoreCase(lStrListType))
			{
				subCategoryList=patientService.getDiagnSubCategory(lStrDiagnCode);
				diseaseList=patientService.getDiagnDisease(lStrDiagnCode,lStrListType);
				disAnatomicalList=patientService.getDiagnDisAnatomical(lStrDiagnCode,lStrListType);
			}
			else if("subCategoryId".equalsIgnoreCase(lStrListType))
			{
				diseaseList=patientService.getDiagnDisease(lStrDiagnCode,lStrListType);
				disAnatomicalList=patientService.getDiagnDisAnatomical(lStrDiagnCode,lStrListType);
			}
			else if("diseaseId".equalsIgnoreCase(lStrListType))
			{
				disAnatomicalList=patientService.getDiagnDisAnatomical(lStrDiagnCode,lStrListType);
			}
			if(subCategoryList!=null)
			{
				List<String> finalSubCatList = new ArrayList<String>();
				for(LabelBean labelBean : subCategoryList)
				{
					finalSubCatList.add(labelBean.getID()+"@");
				}
				//finalSubDetailsList.add(finalSubCatList);
				finalSubDetailsList=finalSubDetailsList+finalSubCatList;
			}
			if(diseaseList!=null)
			{
				List<String> finalDiseaseList = new ArrayList<String>();
				for(LabelBean labelBean : diseaseList)
				{
					finalDiseaseList.add(labelBean.getID()+"@");
				}
				//finalSubDetailsList.add(finalDiseaseList);
				if(finalSubDetailsList.equals(""))
				{
					finalSubDetailsList=finalSubDetailsList+finalDiseaseList;
				}
				else
				{
					finalSubDetailsList=finalSubDetailsList+"$"+finalDiseaseList;
				}
			}
			if(disAnatomicalList!=null)
			{
				List<String> finaldisAnatomicalList = new ArrayList<String>();
				for(LabelBean labelBean : disAnatomicalList)
				{
					finaldisAnatomicalList.add(labelBean.getID()+"@");
				}
				//finalSubDetailsList.add(finaldisAnatomicalList);
				if(finalSubDetailsList.equals(""))
				{
					finalSubDetailsList=finalSubDetailsList+finaldisAnatomicalList;
				}
				else
				{
					finalSubDetailsList=finalSubDetailsList+"$"+finaldisAnatomicalList;
				}
			}
			//if (finalSubDetailsList != null && finalSubDetailsList.size() > 0)
			request.setAttribute("AjaxMessage", finalSubDetailsList);

			lStrPageName="ajaxResult";
		}
		if ("captureTelephonicPatientDtls".equalsIgnoreCase(lStrActionVal))
		{
			String wapNo=null;
			String familyNo=null;
			String cardNo=null;
			if(request.getParameter("wapNo")!=null && !request.getParameter("wapNo").equals(""))
			{
				wapNo=request.getParameter("wapNo");
			}
			if(request.getParameter("familyNo")!=null && !request.getParameter("familyNo").equals(""))
			{
				familyNo=request.getParameter("familyNo");
			}
			if(request.getParameter("cardNo")!=null && !request.getParameter("cardNo").equals(""))
			{
				cardNo=request.getParameter("cardNo");
			}
			patientVO=new PatientVO();
			EhfmSeq ehfmSeqTelephonic = null;
			String liNextVal="";
			String userId=null;
			int phaseRenewal=0;
			Date date = new Date();
			String crtDate=sdfw.format(date);
			Date crtDt = sdfw.parse((sdfw.format(date)));
			userId=session.getAttribute("EmpID").toString(); 
			GLOGGER.info("Card No"+wapNo+"/"+familyNo) ;
			liNextVal = patientService.getSequence("TELEPHONIC_ID");

			patientVO.setTelephonicId(liNextVal);
			patientVO.setCrtDt(crtDate);
			patientVO.setCrtUsr(userId);
			if(cardNo!=null)
			{
				patientVO.setRationCard(cardNo.toUpperCase());
			}
			patientVO.setCardType(patientForm.getCard_type());
			patientVO.setCardIssueDt(patientForm.getCardIssueDt());
			patientVO.setDateOfBirth(patientForm.getDateOfBirth());
			patientVO.setFirstName(patientForm.getFname());
			patientVO.setGender(patientForm.getGender());
			patientVO.setSchemeId(patientForm.getScheme());
			if(patientForm.getScheme()==null || patientForm.getScheme() .equalsIgnoreCase(""))
				{
					if(patientForm.getTelScheme()!=null && !"".equalsIgnoreCase(patientForm.getTelScheme()))
						patientVO.setSchemeId(patientForm.getTelScheme());
				}
			if(familyNo!=null && !familyNo.equalsIgnoreCase("01") && patientForm.getHead()==null)
			{
				patientVO.setFamilyHead(config.getString("NFlag"));
			}
			else if(familyNo!=null)
			{
				patientVO.setFamilyHead(config.getString("YFlag"));
			}

			String[] age=patientForm.getAgeString().split("~");
			patientVO.setEmpCode(patientForm.getEmpCode());
			patientVO.setAge(age[0]);
			patientVO.setAgeMonths(age[1]);
			patientVO.setAgeDays(age[2]);
			patientVO.setRelation(patientForm.getRelation());
			patientVO.setCaste(patientForm.getCaste());
			patientVO.setOccupationCd(patientForm.getOccupation());
			patientVO.setContactNo(patientForm.getContactno());
			patientVO.setSlab(patientForm.getSlab());
			patientVO.setAddr1(patientForm.getHno());
			patientVO.setAddr2(patientForm.getStreet());
			patientVO.setVillageCode(patientForm.getVillage());
			patientVO.setState(patientForm.getState());
			patientVO.setDistrictCode(patientForm.getDistrict());
			patientVO.setMdl_mpl(patientForm.getMdl_mcl());
			if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
			{
				patientVO.setMandalCode(patientForm.getMandal());
			}
			else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
			{
				patientVO.setMandalCode(patientForm.getMunicipality());
			}
			patientVO.setPincode(patientForm.getPin());
			//start sameaddress check
			if(patientForm.getCommCheck()==null)
			{
				patientVO.setPermAddr1(patientForm.getComm_hno());
				patientVO.setPermAddr2(patientForm.getComm_street());
				patientVO.setC_pin_code(patientForm.getComm_pin());
				patientVO.setC_state(patientForm.getComm_state());
				patientVO.setC_district_code(patientForm.getComm_dist());
				patientVO.setC_mdl_mpl(patientForm.getComm_mdl_mcl());
				if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_mandal());
				}
				else if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_municipality());
				}

				patientVO.setC_village_code(patientForm.getComm_village());	
			}
			else
			{
				patientVO.setPermAddr1(patientForm.getHno());
				patientVO.setPermAddr2(patientForm.getStreet());
				patientVO.setC_pin_code(patientForm.getPin());
				patientVO.setC_state(patientForm.getState());
				patientVO.setC_district_code(patientForm.getDistrict());
				patientVO.setC_mdl_mpl(patientForm.getMdl_mcl());
				if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getMandal());
				}
				else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getMunicipality());
				}
				patientVO.setC_village_code(patientForm.getVillage());	
			}
			//end sameaddress check
			if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Emp")))
				patientVO.setEmployeeRenewal("0");
			if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase(config.getString("NewBornBaby")))
				patientVO.setEmployeeRenewal("0");
			else if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Pen")))
				patientVO.setPensionerRenewal("0");
			patientVO.setRegHospId(patientForm.getHospitalId());
			patientVO.setTeleCallerDesgn(patientForm.getTeleCallerDesgn());
			patientVO.setTeleCallerName(patientForm.getTeleCallerName());
			patientVO.setTeleDocDesgn(patientForm.getTeleDocDesgn());
			patientVO.setTeleDocName(patientForm.getTeleDocName());
			patientVO.setTeleDocPhoneNo(patientForm.getTeleDocPhoneNo());
			patientVO.setTeleDocremarks(patientForm.getRemarksProcedure()+"~"+patientForm.getRemarksDiagnosis());
			patientVO.setTelePhoneNo(patientForm.getTelePhoneNo());

			patientVO.setDiagnosisType(patientForm.getDiagType());
			patientVO.setMainCatName(patientForm.getMainCatName());
			patientVO.setSubCatName(patientForm.getSubCatName());
			patientVO.setCatName(patientForm.getCatName());
			patientVO.setDiseaseName(patientForm.getDiseaseName());
			patientVO.setDisAnatomicalName(patientForm.getDisAnatomicalName());
			patientVO.setAsriCatCode(patientForm.getAsriCatName());
			patientVO.setICDcatName(patientForm.getICDCatName());
			patientVO.setICDsubCatName(patientForm.getICDSubCatName());
			patientVO.setICDprocName(patientForm.getICDProcName());
			if(patientForm.getIndication().length()>3000)
			{
				patientVO.setIndication(patientForm.getIndication().substring(0, 3000));
			}
			else
			{
				patientVO.setIndication(patientForm.getIndication());
			}
			try{
				int rowsInserted=patientService.captureTelephonicPatientDtls(patientVO);
				if(rowsInserted==0)
				{

					GLOGGER.info("Patient cannot be registered");
					lStrPageName="failure";
				}
				else
				{
					patientForm.setTelephonicId(liNextVal);
					lStrPageName="telephonicSucess";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				GLOGGER.error ( "Exception occured while Capturing telephonic intimation in PatientAction." +e.getMessage()) ;
			}    
		}

		if("telephonicDirectReg".equalsIgnoreCase(lStrActionVal)){

			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);
/*			List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
			session.setAttribute("districtList",distList);*/

			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			session.setAttribute("castesList",castesList);

			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);

			List<LabelBean> hospitalList = patientService.getHospital(lStrUserId,roleId);
			session.setAttribute("hospitalList",hospitalList);

			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);

			List<LabelBean> maritalStatusList=commonService.getComboDetails("CH5");
			session.setAttribute("maritalStatusList",maritalStatusList);

			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));

			PatientVO patientVo=new PatientVO();
			String cardNo=null;
			String cardType=null;
			String telephonicId=request.getParameter("telephonicId");
			if(request.getParameter("cardNo")!=null && !request.getParameter("cardNo").equals(""))
			{
				cardNo=request.getParameter("cardNo");
			}
			if(request.getParameter("cardType")!=null && !request.getParameter("cardType").equals(""))
			{
				cardType = request.getParameter("cardType");
			}
			patientVo.setTelephonicId(telephonicId);
			patientForm.setTelephonicId(telephonicId);
			patientVo.setCardNo(cardNo);
			patientVo.setCardType(cardType);
			//patientForm.setCard_type(cardType);
			PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);   
			if(patientVO1!=null)
			{
				if(cardNo!=null)
				{
					patientForm.setCardNo(cardNo);
					patientForm.setCard_type(cardType);
					//patientForm.setSlab(patientVO1.getSlab());
				}

				patientForm.setDisableFlag(config.getString("NFlag"));
				if(patientVO1.getEmpCode()!=null && !patientVO1.getEmpCode().equals(""))
				{
					patientForm.setEmpCode(patientVO1.getEmpCode());
				}
				if(patientVO1.getCardIssueDt()!=null)
				{
					patientForm.setCardIssueDt(patientVO1.getCardIssueDt());
				}
				if(patientVO1.getDateOfBirth()!=null)
				{
					String dob1[]=patientVO1.getDateOfBirth().substring(0,10).split("-");
					String finalDob1=dob1[2]+"-"+dob1[1]+"-"+dob1[0];
					patientForm.setDateOfBirth(patientVO1.getDateOfBirth());
				}

				if(patientVO1.getFamilyHead()!=null)
					patientForm.setHead(patientVO1.getFamilyHead());

				if(patientVO1.getGender()!=null)
					patientForm.setGender(patientVO1.getGender());

				if(patientVO1.getFirstName()!=null)
					patientForm.setFname(patientVO1.getFirstName()); 
				if(patientVO1.getRelation()!=null)
					patientForm.setRelation(patientVO1.getRelation());
				if(patientVO1.getContactNo()!=null)
					patientForm.setContactno(patientVO1.getContactNo());
				if(patientVO1.getRegHospId()!=null)
					patientForm.setHospitalId(patientVO1.getRegHospId());

				//setting card address
				if(patientVO1.getAddr1()!=null)
					patientForm.setHno(patientVO1.getAddr1());
				if(patientVO1.getAddr2()!=null)
					patientForm.setStreet(patientVO1.getAddr2());
				List<LabelBean> districtList=null;
				if(patientVO1.getState()!=null)
				{
					patientForm.setState(patientVO1.getState());
					districtList=commonService.getAsrimLocations(distHdrId, patientVO1.getState());
				}
				request.setAttribute("districtList",districtList);
				if(patientVO1.getDistrictCode()!=null)
					patientForm.setDistrict(patientVO1.getDistrictCode());
				if(patientVO1.getMdl_mpl()!=null)
					patientForm.setMdl_mcl(patientVO1.getMdl_mpl());
				if(patientVO1.getMdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> mdlList=commonService.getAsrimLocations(lStrMandalHdrId, patientVO1.getDistrictCode());
					request.setAttribute("mdlList", mdlList);
					patientForm.setMandal(patientVO1.getMandalCode());

					List<LabelBean> villList=commonService.getAsrimLocations(lStrVillageHdrId, patientVO1.getMandalCode());
					request.setAttribute("villList", villList);
				}
				else if(patientVO1.getMdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> mplList=commonService.getAsrimLocations(lStrMunicipalHdrId, patientVO1.getDistrictCode());
					request.setAttribute("mplList", mplList);
					patientForm.setMunicipality(patientVO1.getMandalCode());

					List<LabelBean> villList=commonService.getAsrimLocations(lStrMplVillageHdrId, patientVO1.getMandalCode());
					request.setAttribute("villList", villList);
				}
				if(patientVO1.getVillageCode()!=null)
					patientForm.setVillage(patientVO1.getVillageCode());
				if(patientVO1.getPincode()!=null)
					patientForm.setPin(patientVO1.getPincode());
				//setting communication address
				if(patientVO1.getPermAddr1()!=null)
					patientForm.setComm_hno(patientVO1.getPermAddr1());
				if(patientVO1.getPermAddr2()!=null)
					patientForm.setComm_street(patientVO1.getPermAddr2());
				List<LabelBean> cdistrictList=null;
				if(patientVO1.getC_state()!=null)
				{
					patientForm.setComm_state(patientVO1.getC_state());
					cdistrictList=commonService.getAsrimLocations(distHdrId, patientVO1.getC_state());
				}
				request.setAttribute("cdistrictList",cdistrictList);
				
				if(patientVO1.getC_district_code()!=null)
					patientForm.setComm_dist(patientVO1.getC_district_code());
				
				if(patientVO1.getC_mdl_mpl()!=null)
					patientForm.setComm_mdl_mcl(patientVO1.getC_mdl_mpl());
				if(patientVO1.getC_mdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> cmdlList=commonService.getAsrimLocations(lStrMandalHdrId, patientVO1.getC_district_code());
					request.setAttribute("cmdlList", cmdlList);
					patientForm.setComm_mandal(patientVO1.getC_mandal_code());

					List<LabelBean> cvillList=commonService.getAsrimLocations(lStrVillageHdrId, patientVO1.getC_mandal_code());
					request.setAttribute("cvillList", cvillList);
				}
				else if(patientVO1.getC_mdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> cmplList=commonService.getAsrimLocations(lStrMunicipalHdrId, patientVO1.getC_district_code());
					request.setAttribute("cmplList", cmplList);
					patientForm.setComm_municipality(patientVO1.getC_mandal_code());

					List<LabelBean> cvillList=commonService.getAsrimLocations(lStrMplVillageHdrId, patientVO1.getC_mandal_code());
					request.setAttribute("cvillList", cvillList);
				}

				if(patientVO1.getC_village_code()!=null)
					patientForm.setComm_village(patientVO1.getC_village_code());
				if(patientVO1.getC_pin_code()!=null)
					patientForm.setComm_pin(patientVO1.getC_pin_code());

				if(patientVO1.getOccupationCd()!=null)
					patientForm.setOccupation(patientVO1.getOccupationCd());

				patientForm.setDtTime(serverDt);
			}
			else
			{
				patientForm.setDisableFlag(config.getString("YFlag"));
				patientForm.setCardNo("");
				patientForm.setCard_type("");
				patientForm.setHead("");
				patientForm.setDtTime(serverDt);
				patientForm.setErrMsg(config.getString("err.InvalidCardNo"));
			}
			patientForm.setTelephonicReg("Yes");
			lStrPageName = "InPatientRegistration"; 
		}


		if("ViewTelephonicDtls".equalsIgnoreCase(lStrActionVal)){
			String telephonicId=request.getParameter("telephonicId");
			if(telephonicId==null || telephonicId.equalsIgnoreCase("")){
				telephonicId = patientForm.getTelephonicId();
			}
			PatientVO patientVo = new PatientVO();
			patientVo.setTelephonicId(telephonicId);
			patientForm.setTelephonicId(telephonicId);
			PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);
			if(patientVO1.getCardNo()!=null && !patientVO1.getCardNo().equals(""))
			{
				patientForm.setCardNo(patientVO1.getCardNo());	
			}
			else
			{
				patientForm.setCardNo("NA");
			}
			patientForm.setCard_type(patientVO1.getCardType());
			patientForm.setFname(patientVO1.getFirstName());
			if(patientVO1.getCardIssueDt()!=null)
				patientForm.setCardIssueDt(patientVO1.getCardIssueDt());
			else
				patientForm.setCardIssueDt("NA");
			if(patientVO1.getDateOfBirth()!=null)
				patientForm.setDateOfBirth(patientVO1.getDateOfBirth());
			else
				patientForm.setDateOfBirth("NA");
			patientForm.setYears(patientVO1.getAge());
			patientForm.setMonths(patientVO1.getAgeMonths());
			patientForm.setDays(patientVO1.getAgeDays());

			/*String casteName=commonService.getCmbHdrname(lStrcastesHdrId, patientVO1.getCaste());
        patientForm.setCaste(casteName);*/
			patientForm.setGender(patientVO1.getGender());
			patientForm.setRelation(patientVO1.getRelOthers());
			patientForm.setOccupation(patientVO1.getOccOthers());
			patientForm.setContactno(patientVO1.getContactNo());

			patientForm.setHno(patientVO1.getAddr1());
			patientForm.setStreet(patientVO1.getAddr2());
			patientForm.setState(patientService.getLocationName(patientVO1.getState()));
			String distName=patientService.getLocationName(patientVO1.getDistrictCode());
			patientForm.setDistrict(distName);
			String mandalName=patientService.getLocationName(patientVO1.getMandalCode());
			patientForm.setMandal(mandalName);
			String villName=patientService.getLocationName(patientVO1.getVillageCode());
			patientForm.setVillage(villName);
			patientForm.setPin(patientVO1.getPincode());

			patientForm.setComm_hno(patientVO1.getPermAddr1());
			patientForm.setComm_street(patientVO1.getPermAddr2());
			patientForm.setComm_state(patientService.getLocationName(patientVO1.getC_state()));
			String cDistName=patientService.getLocationName(patientVO1.getC_district_code());
			patientForm.setComm_dist(cDistName);
			String cMandalName=patientService.getLocationName(patientVO1.getC_mandal_code());
			patientForm.setComm_mandal(cMandalName);
			String cVillName=patientService.getLocationName(patientVO1.getC_village_code());
			patientForm.setComm_village(cVillName);
			patientForm.setComm_pin(patientVO1.getC_pin_code());

			patientForm.setHospitalName(patientVO1.getRegHospDt());
			patientForm.setTeleCallerDesgn(patientVO1.getTeleCallerDesgn());
			patientForm.setTeleCallerName(patientVO1.getTeleCallerName());
			patientForm.setTeleDocDesgn(patientVO1.getTeleDocDesgn());
			patientForm.setTeleDocName(patientVO1.getTeleDocName());
			patientForm.setTeleDocPhoneNo(patientVO1.getTeleDocPhoneNo());
			patientForm.setTelePhoneNo(patientVO1.getTelePhoneNo());

			patientForm.setDiagType(patientVO1.getDiagnosisType());
			patientForm.setMainCatName(patientVO1.getMainCatName());
			patientForm.setCatName(patientVO1.getCatName());
			patientForm.setSubCatName(patientVO1.getSubCatName());
			patientForm.setDiseaseName(patientVO1.getDiseaseName());
			patientForm.setDisAnatomicalName(patientVO1.getDisAnatomicalName());

			patientForm.setAsriCatName(patientVO1.getAsriCatCode());
			patientForm.setICDCatName(patientVO1.getICDcatName());
			patientForm.setICDSubCatName(patientVO1.getICDsubCatName());
			patientForm.setICDProcName(patientVO1.getICDprocName());
			patientForm.setIndication(patientVO1.getIndication());

			if(patientVO1.getTeleDocremarks()!=null)
				{
					String[] remarks=patientVO1.getTeleDocremarks().split("~");
					patientForm.setRemarksProcedure(remarks[0]);
					patientForm.setRemarksDiagnosis(remarks[1]);
				}

			patientForm.setDtTime(patientVO1.getCrtDt());
			patientForm.setHead(patientVO1.getFamilyHead());


			lStrPageName = "viewTelephonicDetails"; 
		}
		if("getComplaintList".equalsIgnoreCase(lStrActionVal)){
			String mainCompId=request.getParameter("mainCompId");
			List<String> complaintList=null;
			complaintList=patientService.getComplaints(mainCompId);
			for(int i=0 ;i<complaintList.size();i++)
			{
			if(complaintList.get(i).equals("S18.22.1~Trauma to tooth or Jaw@"))
				complaintList.set(i, "S18.22.1~Trauma to tooth or Face@");
			}
			if (complaintList != null && complaintList.size() > 0)
				request.setAttribute("AjaxMessage", complaintList);

			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getSubSymptomLst".equalsIgnoreCase(lStrActionVal)){
			String mainSymptomCode=request.getParameter("mainSymptomCode");			
			List<String> symptomList=null;
			symptomList=patientService.getSubSymptomLists(mainSymptomCode);
			if (symptomList != null && symptomList.size() > 0)
				request.setAttribute("AjaxMessage", symptomList);

			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getSymptomLst".equalsIgnoreCase(lStrActionVal)){
			String mainSymptomCode=request.getParameter("mainSymptomCode");
			String subSymptomCode=request.getParameter("subSymptomCode");
			List<String> symptomList=null;
			symptomList=patientService.getSymptomLists(mainSymptomCode,subSymptomCode);
			if (symptomList != null && symptomList.size() > 0)
				request.setAttribute("AjaxMessage", symptomList);

			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getGenInvestList".equalsIgnoreCase(lStrActionVal)){
			String lStrBlockId=request.getParameter("lStrBlockId");			
			List<String> symptomList=null;
			symptomList=patientService.getInvestigations(lStrBlockId);
			if (symptomList != null && symptomList.size() > 0)
				request.setAttribute("AjaxMessage", symptomList);

			lStrPageName="ajaxResult";
		}
		
		if(lStrActionVal!=null && "getInvestPrice".equalsIgnoreCase(lStrActionVal))
			{
				String blockId=request.getParameter("blockId");
				String investId=request.getParameter("investId");
				String price=null;
				price=patientService.getInvestPrice(blockId,investId);
				if(price!=null)
				{
					long investPrice=Long.valueOf(price);
					request.setAttribute("AjaxMessage",investPrice);
					
				}
				return mapping.findForward("ajaxResult");
			}
		
		if("getICDCatByAsriCode".equalsIgnoreCase(lStrActionVal)){
			String asriCatCode=request.getParameter("lStrAsriCatId");
			String hospCode=request.getParameter("hospId");
			//String treatType=request.getParameter("treatType");
			List<String> icdCatList=null;List<String> doctorList=null;List<String> bothList=null;
			icdCatList=commonService.getICDCategoryList(asriCatCode);
			doctorList=commonService.getDocListBySpec(asriCatCode,hospCode,schemeId);
			if (icdCatList != null && icdCatList.size() > 0)
				request.setAttribute("AjaxMessage", icdCatList);
			if (doctorList != null && doctorList.size() > 0)
				request.setAttribute("AjaxMessage1", doctorList);
			
			lStrPageName="ajaxResult";
		}
		if("getProcByCat".equalsIgnoreCase(lStrActionVal)){
			EhfPatient ehfPatient=null;
			String lStrPatientId=null;
			String hosType=null;
			String state=null;
			lStrPatientId=request.getParameter("patientId");
			state=request.getParameter("scheme");
			hosType=request.getParameter("hosType");
			//String treatType=request.getParameter("treatType");
			if(state==null || state=="")
			{
			patientForm.setPatientNo(lStrPatientId);
			if(lStrPatientId!=null)
				{
					ehfPatient=(EhfPatient)patientService.getPatientDetails(lStrPatientId);
					state=ehfPatient.getSchemeId();
				}
			}
			String icdCatCode=request.getParameter("lStrICDCatId");
			String asriCatCode=request.getParameter("lStrAsriCode");
			String hospId = request.getParameter("hospId");
			List<String> therapyProcList=null;
			therapyProcList=patientService.getIcdProcList(icdCatCode,asriCatCode,hospId,state,hosType);
			//For Adding and removing Dental Follow Up Procedure
			if(asriCatCode!=null && asriCatCode.equalsIgnoreCase(config.getString("DentalSurgeryID")))
				therapyProcList=patientService.checkDenFlp(lStrPatientId,therapyProcList);
					
			if (therapyProcList != null && therapyProcList.size() > 0)
				request.setAttribute("AjaxMessage", therapyProcList);

			lStrPageName="ajaxResult";
		}
		if("getUnitsByProc".equalsIgnoreCase(lStrActionVal))
		{
			String icdProcCode=request.getParameter("lStrProcCode");
			int dentalUnits = 0;
			dentalUnits=patientService.getDenUnitsList(icdProcCode);
		
			request.setAttribute("AjaxMessage", dentalUnits);

			lStrPageName="ajaxResult";
		}
		if("getTherapyProcCodes".equalsIgnoreCase(lStrActionVal)){
			String icdProcCode=request.getParameter("lStrICDProcId");
			String procCode=null;
			procCode=patientService.getTherapyProcCodes(icdProcCode);
			if (procCode != null)
				request.setAttribute("AjaxMessage", procCode);

			lStrPageName="ajaxResult";
		}
		if("getOpPkgList".equalsIgnoreCase(lStrActionVal)){
			String cardNo=null;
			int count=0;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			String opCatCode= request.getParameter("lStrOpCatCode");
			List<String> opPkgList=null;
			opPkgList=patientService.getOpPkgList(opCatCode);
			if (opPkgList != null && opPkgList.size() > 0)
				request.setAttribute("AjaxMessage", opPkgList);
			/*count=patientService.validateTherapyOPCat(cardNo, opCatCode);
		if(count==0)
		{
			List<String> opPkgList=null;
			opPkgList=patientService.getOpPkgList(opCatCode);
			if (opPkgList != null && opPkgList.size() > 0)
				request.setAttribute("AjaxMessage", opPkgList);
		}
		else
		{
			request.setAttribute("AjaxMessage", count);
		}*/
			lStrPageName="ajaxResult";
		}
		if("getOpIcdList".equalsIgnoreCase(lStrActionVal)){
			String opCode= request.getParameter("lStrOpPkgCode");
			List<String> opIcdList=null;
			opIcdList=patientService.getOpIcdList(opCode);
			if (opIcdList != null && opIcdList.size() > 0)
				request.setAttribute("AjaxMessage", opIcdList);
			lStrPageName="ajaxResult";
		}
		if("casePrintForm".equalsIgnoreCase(lStrActionVal))
		{
			String caseNo=null;
			String patientType=null;
			String caseId=null;
			String hospId=null;
			CommonDtlsVO commonDtlsVO=null;
			String opTgFlag=null;
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseNo=request.getParameter("caseId");
				System.out.println(caseNo);
			}
			
			String[] caseValues=caseNo.split("/");
			caseId=caseValues[2];
			hospId=caseValues[1];
			List<CommonDtlsVO> commonDtls=patientService.getPatientCommonDtls(caseId);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				patientForm.setPatientName(commonDtlsVO.getPATIENTNAME());
				patientForm.setScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getPatientScheme()!=null){
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				;}
				patientForm.setTelScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getScheme()!=null)
				{
					request.setAttribute("schemeId",commonDtlsVO.getScheme());
					request.setAttribute("caseSchemeId",commonDtlsVO.getScheme());
				}
				patientForm.setCardNo(commonDtlsVO.getCARDNO());
				patientForm.setDistrict(commonDtlsVO.getDISTRICT());
				patientForm.setMandal(commonDtlsVO.getMANDAL());
				patientForm.setVillage(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null && !"".equals(commonDtlsVO.getDate()))
					patientForm.setDtTime(sdfw.format(commonDtlsVO.getDate()));
				if(commonDtlsVO.getINTIID()!=null)
					request.setAttribute("regHospId", commonDtlsVO.getINTIID());
				patientForm.setAgeString(commonDtlsVO.getAGE());
				patientForm.setContactno(commonDtlsVO.getCONTACT());
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				patientForm.setHospitalName(commonDtlsVO.getHOSPNAME());
				patientForm.setGender(commonDtlsVO.getGENDER());
				patientForm.setAddr(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientForm.setDateIp(commonDtlsVO.getPATDT());
				patientForm.setIpNo(commonDtlsVO.getIPNO());
				patientForm.setAdmissionType(commonDtlsVO.getADMTYPE());
				patientForm.setHospaddr(commonDtlsVO.getHOSPADDR());

				patientForm.setDocMobileNo(commonDtlsVO.getDOCCONTACT());
				patientForm.setDocQual(commonDtlsVO.getDOCQUAL());
				patientForm.setDocRegNo(commonDtlsVO.getDOCREGNO());
				if(commonDtlsVO.getPatientScheme()!=null)
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());

				PreauthVO patientData=patientService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
				if(patientData!=null)
				{
					patientForm.setPatTemp(patientData.getTemperature());
					patientForm.setPatPulse(patientData.getPulseRate());
					patientForm.setRespir(patientData.getRespirationRate());
					patientForm.setBloodPrLt(patientData.getBpLmt());
					patientForm.setBloodPrRt(patientData.getBpRmt());
					patientForm.setHeight(patientData.getHeight());
					patientForm.setWeight(patientData.getWeight());
					patientForm.setBmi(patientData.getBmi());
					
					patientForm.setPastHist(patientData.getPastIllness());
					patientForm.setFamHist(patientData.getFamilyHis());
					patientForm.setSymptomName(patientData.getSymName());
					patientForm.setComplaints(patientData.getPatComplaint());
					patientForm.setComplType(patientData.getComplaintType());
					patientForm.setPresentHistory(patientData.getPresentIllness());
					patientForm.setPastIllnessValue(patientData.getPastIllnessValue());
					patientForm.setFamilyHistValue(patientData.getFamilyHistValue());
					patientForm.setFamilyHistoryOthr(patientData.getFamilyHistoryOthr());
					patientForm.setPastHistryOthr(patientData.getPastIllenesOthr());
					patientForm.setRemarks(patientData.getRemarks());
					patientForm.setDrughistory(patientData.getDrugHstVal());
					patientForm.setMedicalHistVal(patientData.getMedHistVal());
					patientForm.setShowMedicalTextval(patientData.getMedicalHistoryOthr());
					patientForm.setRegionalLymphadenopathyDtrsTxt(patientData.getRegionalLymphadenopathyDtrsTxt());
					patientForm.setJawsDtrsTxt(patientData.getJawsDtrsTxt());
					patientForm.setRegionalLymphadenopathyDtrsSub(patientData.getRegionalLymphadenopathyDtrsSub());
					patientForm.setJawsDtrsSub(patientData.getJawsDtrsSub());
					patientForm.setTmjDtrsSub(patientData.getTmjDtrsSub());
					patientForm.setFaceDtrsSub(patientData.getFaceDtrsSub());
					List<LabelBean> intraoraldtls=null;
					String intraoral="HD2";
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					patientForm.setIntraoraldtls(intraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					
					patientForm.setDntsublistoral0(patientData.getDntsublistoral0());
					patientForm.setDntsublistoral1(patientData.getDntsublistoral1());
					patientForm.setDntsublistoral2(patientData.getDntsublistoral2());
					patientForm.setDntsublistoral3(patientData.getDntsublistoral3());
					patientForm.setDntsublistoral4(patientData.getDntsublistoral4());
					patientForm.setDntsublistoral5(patientData.getDntsublistoral5());
					patientForm.setDntsublistoral6(patientData.getDntsublistoral6());
					patientForm.setSwSite(patientData.getSwSite());
					patientForm.setSwSize(patientData.getSwSize());
					patientForm.setSwExtension(patientData.getSwExtension());
					patientForm.setSwColour(patientData.getSwColour());
					patientForm.setSwConsistency(patientData.getSwConsistency());
					patientForm.setSwTenderness(patientData.getSwTenderness());
					patientForm.setSwBorders(patientData.getSwBorders());
					patientForm.setPsSite(patientData.getPsSite());
					patientForm.setPsDischarge(patientData.getPsDischarge());
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					String occlusion="CH12";
					String deciduousdent ="CH13";
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					EhfPatient ehfPatient=null;
					ehfPatient=(EhfPatient)patientService.getPatientDetails(commonDtlsVO.getPATID());
					PreauthVO preauthDtlsVO = new PreauthVO();
					preauthDtlsVO = patientService.getPatientFullDtls(caseId, ehfPatient.getPatientId());		
					if(preauthDtlsVO.getCarriesdecidous()!=null)
					{
						patientForm.setCarriesdecidous(preauthDtlsVO.getCarriesdecidous());
					}
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", preauthDtlsVO);
					patientForm.setPreviousDentalTreatment(patientData.getPreviousDentalTreatment());
					patientForm.setOcclusionTxt(patientData.getOcclusionTxt());
					patientForm.setOcclusionTypeTxt(patientData.getOcclusionTypeTxt());
					patientForm.setOcclusionOtherTxt(patientData.getOcclusionOtherTxt());
					
					
				}
				CommonDtlsVO othrDtls=patientService.getOtherDtls(caseId, commonDtlsVO.getPATID());
				patientForm.setAllergy(othrDtls.getAllergy());
				patientForm.setHabbits(othrDtls.getHabbits());
				patientForm.setInvestigationLt(othrDtls.getInvestigations());
				int diagCount=0;
				if(othrDtls.getDiagnosisType()!=null && !("").equalsIgnoreCase(othrDtls.getDiagnosisType()))
				patientForm.setDiagType(othrDtls.getDiagnosisType());
				else
				{
					request.setAttribute("DiagnosisType","N");
					diagCount++;
				}
				if(othrDtls.getMainCatName()!=null && !("").equalsIgnoreCase(othrDtls.getMainCatName()))
				patientForm.setMainCatName(othrDtls.getMainCatName());
				else
				{
					request.setAttribute("MainCatName","N");
					diagCount++;
				}
				if(othrDtls.getCatName()!=null && !("").equalsIgnoreCase(othrDtls.getCatName()))
				patientForm.setCatName(othrDtls.getCatName());
				else
				{
					request.setAttribute("CatName","N");
					diagCount++;
				}
				if(othrDtls.getSubCatName()!=null && !("").equalsIgnoreCase(othrDtls.getSubCatName()))
				patientForm.setSubCatName(othrDtls.getSubCatName());
				else
				{
					request.setAttribute("SubCatName","N");
					diagCount++;
				}
				if(othrDtls.getDisName()!=null && !("").equalsIgnoreCase(othrDtls.getDisName()))
				patientForm.setDiseaseName(othrDtls.getDisName());
				else
				{
					request.setAttribute("DisName","N");
					diagCount++;
				}
				int diagAny=0;
				if(othrDtls.getDisAnatomicalSitename()!=null && !("").equalsIgnoreCase(othrDtls.getDisAnatomicalSitename()))
				patientForm.setDisAnatomicalName(othrDtls.getDisAnatomicalSitename());
				else
				{
					
					diagAny++;
					diagCount++;
				}
				if(othrDtls.getOtherDiagName()!=null&& !("").equalsIgnoreCase(othrDtls.getDisAnatomicalSitename()))
				{
					patientForm.setDisAnatomicalName(othrDtls.getOtherDiagName());
					request.setAttribute("DisAnatomicalSitename","Y");
				}
				else
				{
					
					diagAny++;
					
				}
				if(diagAny==2)
				{
					request.setAttribute("DisAnatomicalSitename","N");
				}
				if(diagCount>6)
				{
					request.setAttribute("disableDiag","Y");
				}
				String mithraName = patientService.getEmpNameById(commonDtlsVO.getMithra());
				if(mithraName!=null && !mithraName.equalsIgnoreCase(""))
					patientForm.setMithra(mithraName);

				List<PreauthVO> procDtls=patientService.getcaseSurgeryDtls(caseId);
				patientForm.setProcList(procDtls);
				patientForm.setCaseId(caseNo);
				request.setAttribute("decFlag", request.getParameter("decFlag"));
				if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IP") || commonDtlsVO.getPATTYPE().equalsIgnoreCase("DOP"))
					{

						patientForm.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
						lStrPageName="ipCaseCopy";
					}
					else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IPM"))
					{
						if("IPM".equalsIgnoreCase(commonDtlsVO.getPATTYPE()))
						{
							String[] caseValuesArr=caseNo.split("/");
							caseId=caseValuesArr[2];
							List<LabelBean> catList = patientService.getCatName(caseId);
							if(catList.size() > 0)
							{
								patientForm.setMedicalSpclty(catList.get(0).getICDNAME());
								patientForm.setMedicalCat(catList.get(0).getVALUE());
							}
							patientForm.setOpNo(commonDtlsVO.getIPNO());
							patientForm.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
							request.setAttribute("highEndOncologyCase", request.getParameter("highEndOncologyCase"));
							lStrPageName="ipmCaseCopy";
						}
					}
					else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
					{
						List<DrugsVO> drugs=patientService.getDrugs(commonDtlsVO.getPATID(),"op");
						if(drugs!=null && drugs.size()>0)
						patientForm.setDrugLt(drugs);
						String doctorName="";
						
						if((commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))|| commonDtlsVO.getINTIID()!=null && "G".equalsIgnoreCase(hospGovu))
						{
							
							
							/*EhfOpConsultData opDocDtls=new EhfOpConsultData();
							
							opDocDtls=patientService.getOpDocDtls(commonDtlsVO.getPATID());
							
							if(opDocDtls!=null)
							{
							request.setAttribute("opTgFlag","Y");
							opTgFlag="Y";
							patientForm.setDiagnosedBy(opDocDtls.getDiagnoisedBy());
							patientForm.setDoctorName(opDocDtls.getUnitHodName());
							if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
							{
								patientForm.setDiagnosedBy("Resident Doctor");	
							}
						}*/
						}
						
						  if(commonDtlsVO.getUnitHodName()!=null && !("").equalsIgnoreCase(commonDtlsVO.getUnitHodName()))
							 {
								doctorName=commonDtlsVO.getUnitHodName();
							 }
						  else if("Consultant".equalsIgnoreCase(commonDtlsVO.getDoctType()) || "InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDoctorById(commonDtlsVO.getDOCID());
						}
						else if("DutyDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDutyDoctorById(commonDtlsVO.getDOCID());
						}
						else if("MEDCO".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName=patientService.getEmpNameById(commonDtlsVO.getDOCID());
						}
					
						if(doctorName!=null && !doctorName.equalsIgnoreCase(""))
							patientForm.setDoctorName(doctorName);
						patientForm.setDiagnosedBy(commonDtlsVO.getDoctType());
						request.setAttribute("diagnosedBy",commonDtlsVO.getDoctType());
						
												if((commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))|| commonDtlsVO.getINTIID()!=null && "G".equalsIgnoreCase(hospGovu))
						{
							if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
							{
								EhfOpConsultData opDocDtls=new EhfOpConsultData();
								
								opDocDtls=patientService.getOpDocDtls(commonDtlsVO.getPATID());
								
								if(opDocDtls!=null)
								{
								request.setAttribute("opTgFlag","Y");
								opTgFlag="Y";
								patientForm.setDiagnosedBy(opDocDtls.getDiagnoisedBy());
								patientForm.setDoctorName(opDocDtls.getUnitHodName());
								request.setAttribute("diagnosedBy",opDocDtls.getDiagnoisedBy());
								/*if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
								{
									patientForm.setDiagnosedBy("Resident Doctor");	
								}*/
							}
							}
							
						}
						
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
						lStrPageName="opCaseCopyTG";
						}
						else
						{
							lStrPageName="opCaseCopy";
						}
					}
				}
		}
		
		if("submitPharmaCase".equalsIgnoreCase(lStrActionVal))
			{
				String pharmaCases=request.getParameter("pharmaCases");
				String apprPharma=request.getParameter("apprPharma");
				request.setAttribute("pharmaCases", pharmaCases);
				request.setAttribute("apprPharma", apprPharma);
				
				String caseId=null,patientId=null,returnMsg=null,drugDtls=null;
				caseId=request.getParameter("pharmaCaseId");
				patientId=request.getParameter("patientId");
				drugDtls=request.getParameter("drugDtls");
				
				if(caseId!=null && !"".equalsIgnoreCase(caseId)
						&& (patientId!=null && !"".equalsIgnoreCase(patientId)))
					{
						returnMsg=patientService.submitPharmacyCase(caseId,patientId,drugDtls);
						lStrActionVal="getPharmaOPCases";
					}
				else
					{
						returnMsg=config.getString("insufficientData.Pharmacy");
						lStrActionVal="pharmaCasePrintForm";
					}
				
				request.setAttribute("returnMsg", returnMsg);
				
			}
		if("pharmaCasePrintForm".equalsIgnoreCase(lStrActionVal))
		{
			String pharmaCases=request.getParameter("pharmaCases");
			String apprPharma=request.getParameter("apprPharma");
			request.setAttribute("pharmaCases", pharmaCases);
			request.setAttribute("apprPharma", apprPharma);
			
			String caseNo=null;
			String patientType=null;
			String caseId=null;
			String hospId=null;
			CommonDtlsVO commonDtlsVO=null;
			String opTgFlag=null;
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseNo=request.getParameter("caseId");
				System.out.println(caseNo);
			}
			
			if(request.getParameter("patientId")!=null)
				request.setAttribute("patientId", request.getParameter("patientId"));
			if(request.getParameter("caseId")!=null)
				request.setAttribute("caseId", request.getParameter("caseId"));
			
			String[] caseValues=caseNo.split("/");
			caseId=caseValues[2];
			hospId=caseValues[1];
			List<CommonDtlsVO> commonDtls=patientService.getPatientCommonDtls(caseId);
			request.setAttribute("pharmaCaseId", caseId);
				
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				if(commonDtlsVO.getScheme()!=null && 
						config.getString("TG").equalsIgnoreCase(commonDtlsVO.getScheme())
						&& commonDtlsVO.getHospType()!='\u0000' && commonDtlsVO.getHospType()=='G')
					{
						if(bioRegFlag!=null && !"".equalsIgnoreCase(bioRegFlag) 
								 && "Y".equalsIgnoreCase(bioRegFlag))
							{
								PatientVO biomVO=patientService.getBiomDtls(commonDtlsVO);
								if(biomVO!=null)
									{
										request.setAttribute("showBiom", "Y");
										patientForm.setTgGovPatCond("Y");
										
										patientForm.setBioFinger(biomVO.getFingerCaptured());
										patientForm.setFingerPrint(biomVO.getFingerPrint());
										patientForm.setBioHand(biomVO.getBioHand());
										
									}
							}	
					}
				
				
				patientForm.setPatientName(commonDtlsVO.getPATIENTNAME());
				patientForm.setScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getPatientScheme()!=null){
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				;}
				patientForm.setTelScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getScheme()!=null)
				{
					request.setAttribute("schemeId",commonDtlsVO.getScheme());
					request.setAttribute("caseSchemeId",commonDtlsVO.getScheme());
				}
				
				if(commonDtlsVO.getINTIID()!=null)
					request.setAttribute("regHospId", commonDtlsVO.getINTIID());
				
				patientForm.setCardNo(commonDtlsVO.getCARDNO());
				patientForm.setDistrict(commonDtlsVO.getDISTRICT());
				patientForm.setMandal(commonDtlsVO.getMANDAL());
				patientForm.setVillage(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null && !"".equals(commonDtlsVO.getDate()))
					patientForm.setDtTime(sdfw.format(commonDtlsVO.getDate()));
				patientForm.setAgeString(commonDtlsVO.getAGE());
				patientForm.setContactno(commonDtlsVO.getCONTACT());
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				request.setAttribute("pharmaPatId", commonDtlsVO.getPATID());
				patientForm.setHospitalName(commonDtlsVO.getHOSPNAME());
				patientForm.setGender(commonDtlsVO.getGENDER());
				patientForm.setAddr(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientForm.setDateIp(commonDtlsVO.getPATDT());
				patientForm.setIpNo(commonDtlsVO.getIPNO());
				patientForm.setAdmissionType(commonDtlsVO.getADMTYPE());
				patientForm.setHospaddr(commonDtlsVO.getHOSPADDR());

				patientForm.setDocMobileNo(commonDtlsVO.getDOCCONTACT());
				patientForm.setDocQual(commonDtlsVO.getDOCQUAL());
				patientForm.setDocRegNo(commonDtlsVO.getDOCREGNO());
				if(commonDtlsVO.getPatientScheme()!=null)
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());

				PreauthVO patientData=patientService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
				if(patientData!=null)
				{
					patientForm.setPatTemp(patientData.getTemperature());
					patientForm.setPatPulse(patientData.getPulseRate());
					patientForm.setRespir(patientData.getRespirationRate());
					patientForm.setBloodPrLt(patientData.getBpLmt());
					patientForm.setBloodPrRt(patientData.getBpRmt());
					patientForm.setHeight(patientData.getHeight());
					patientForm.setWeight(patientData.getWeight());
					patientForm.setBmi(patientData.getBmi());
					
					patientForm.setPastHist(patientData.getPastIllness());
					patientForm.setFamHist(patientData.getFamilyHis());
					patientForm.setSymptomName(patientData.getSymName());
					patientForm.setComplaints(patientData.getPatComplaint());
					patientForm.setComplType(patientData.getComplaintType());
					patientForm.setPresentHistory(patientData.getPresentIllness());
					patientForm.setPastIllnessValue(patientData.getPastIllnessValue());
					patientForm.setFamilyHistValue(patientData.getFamilyHistValue());
					patientForm.setFamilyHistoryOthr(patientData.getFamilyHistoryOthr());
					patientForm.setPastHistryOthr(patientData.getPastIllenesOthr());
					patientForm.setRemarks(patientData.getRemarks());
				}
				/*CommonDtlsVO othrDtls=patientService.getOtherDtls(caseId, commonDtlsVO.getPATID());
				patientForm.setAllergy(othrDtls.getAllergy());
				patientForm.setHabbits(othrDtls.getHabbits());
				patientForm.setInvestigationLt(othrDtls.getInvestigations());
				int diagCount=0;
				if(othrDtls.getDiagnosisType()!=null && !("").equalsIgnoreCase(othrDtls.getDiagnosisType()))
				patientForm.setDiagType(othrDtls.getDiagnosisType());
				else
				{
					request.setAttribute("DiagnosisType","N");
					diagCount++;
				}
				if(othrDtls.getMainCatName()!=null && !("").equalsIgnoreCase(othrDtls.getMainCatName()))
				patientForm.setMainCatName(othrDtls.getMainCatName());
				else
				{
					request.setAttribute("MainCatName","N");
					diagCount++;
				}
				if(othrDtls.getCatName()!=null && !("").equalsIgnoreCase(othrDtls.getCatName()))
				patientForm.setCatName(othrDtls.getCatName());
				else
				{
					request.setAttribute("CatName","N");
					diagCount++;
				}
				if(othrDtls.getSubCatName()!=null && !("").equalsIgnoreCase(othrDtls.getSubCatName()))
				patientForm.setSubCatName(othrDtls.getSubCatName());
				else
				{
					request.setAttribute("SubCatName","N");
					diagCount++;
				}
				if(othrDtls.getDisName()!=null && !("").equalsIgnoreCase(othrDtls.getDisName()))
				patientForm.setDiseaseName(othrDtls.getDisName());
				else
				{
					request.setAttribute("DisName","N");
					diagCount++;
				}
				if(othrDtls.getDisAnatomicalSitename()!=null && !("").equalsIgnoreCase(othrDtls.getDisAnatomicalSitename()))
				patientForm.setDisAnatomicalName(othrDtls.getDisAnatomicalSitename());
				else
				{
					request.setAttribute("DisAnatomicalSitename","N");
					diagCount++;
				}
				if(diagCount>6)
				{
					request.setAttribute("disableDiag","Y");
				}*/
				String mithraName = patientService.getEmpNameById(commonDtlsVO.getMithra());
				if(mithraName!=null && !mithraName.equalsIgnoreCase(""))
					patientForm.setMithra(mithraName);

				List<PreauthVO> procDtls=patientService.getcaseSurgeryDtls(caseId);
				patientForm.setProcList(procDtls);
				patientForm.setCaseId(caseNo);
				request.setAttribute("decFlag", request.getParameter("decFlag"));
				if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IP"))
					{

						patientForm.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
						lStrPageName="ipCaseCopy";
					}
					else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
					{
						List<DrugsVO> drugs=patientService.getDrugs(commonDtlsVO.getPATID(),"op");
						
						if(drugs!=null)
							request.setAttribute("drugsSize",drugs.size());
						
						patientForm.setDrugLt(drugs);
						String doctorName="";
						
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
							request.setAttribute("opTgFlag","Y");
							opTgFlag="Y";
							if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
							{
								patientForm.setDiagnosedBy("Resident Doctor");	
							}
						}
						
						  if(commonDtlsVO.getUnitHodName()!=null && !("").equalsIgnoreCase(commonDtlsVO.getUnitHodName()))
							 {
								doctorName=commonDtlsVO.getUnitHodName();
							 }
						  else if("Consultant".equalsIgnoreCase(commonDtlsVO.getDoctType()) || "InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDoctorById(commonDtlsVO.getDOCID());
						}
						else if("DutyDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDutyDoctorById(commonDtlsVO.getDOCID());
						}
						else if("MEDCO".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName=patientService.getEmpNameById(commonDtlsVO.getDOCID());
						}
					
						if(doctorName!=null && !doctorName.equalsIgnoreCase(""))
							patientForm.setDoctorName(doctorName);
						patientForm.setDiagnosedBy(commonDtlsVO.getDoctType());
						request.setAttribute("diagnosedBy",commonDtlsVO.getDoctType());
						
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
							
							if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
							{
								patientForm.setDiagnosedBy("Resident Doctor");	
							}
						}
						
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
						lStrPageName="opPharmaCaseCopyTG";
						}
						else
						{
							lStrPageName="opPharmaCaseCopyTG";
						}
					}
				}
		}

		if("getChronicDetails".equalsIgnoreCase(lStrActionVal))
		{
			String cardNo=null;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			List<DrugsVO> drugsList=patientService.getChronicDetails(cardNo);
			List<String> drugsLst=new ArrayList<String>(); 
			for(DrugsVO drugsVO : drugsList)
			{
				drugsLst.add(drugsVO.getDrugTypeName()+"~"+drugsVO.getDrugSubTypeName()+"~"+drugsVO.getDrugName()+"~"+drugsVO.getRoute()+"~"+drugsVO.getStrength()+"~"+drugsVO.getDosage()+"~"+drugsVO.getMedicationPeriod()+"@");
			}
			if (drugsList != null && drugsList.size() > 0)
				request.setAttribute("AjaxMessage", drugsLst);
			lStrPageName="ajaxResult";
		}
		if("dtrsPrintForm".equalsIgnoreCase(lStrActionVal))
		{
			String caseId=null;
			CommonDtlsVO commonDtlsVO=null;
			String treatmentDntl=request.getParameter("treatmentDntl");
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseId=request.getParameter("caseId");
			}
			String dentalrntdbvalue=patientService.getTreatmentDntlvalue(caseId);
			List<CommonDtlsVO> commonDtls=patientService.getDtrsFormDtls(caseId);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				patientForm.setScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getPatientScheme()!=null){
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
					
				}
				patientForm.setTelScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getScheme()!=null)
				request.setAttribute("schemeId",commonDtlsVO.getScheme());
				patientForm.setPatientName(commonDtlsVO.getPATIENTNAME());
				patientForm.setCardNo(commonDtlsVO.getCARDNO());
				patientForm.setDistrict(commonDtlsVO.getDISTRICT());
				patientForm.setMandal(commonDtlsVO.getMANDAL());
				patientForm.setVillage(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null)
				{
					patientForm.setDtTime(sdfw.format(commonDtlsVO.getDate()));
				}
				patientForm.setAgeString(commonDtlsVO.getAGE());
				patientForm.setContactno(commonDtlsVO.getCONTACT());
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				patientForm.setDateIp(commonDtlsVO.getPATDT());
				patientForm.setHospitalName(commonDtlsVO.getHOSPNAME());
				patientForm.setGender(commonDtlsVO.getGENDER());
				patientForm.setAddr(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientForm.setHospaddr(commonDtlsVO.getHOSPADDR());
				PreauthVO patientData=patientService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
				if(patientData!=null)
				{
					patientForm.setPatTemp(patientData.getTemperature());
					patientForm.setPatPulse(patientData.getPulseRate());
					patientForm.setRespir(patientData.getRespirationRate());
					patientForm.setBloodPrLt(patientData.getBpLmt());
					patientForm.setBloodPrRt(patientData.getBpRmt());
					patientForm.setPastHist(patientData.getPastIllness());
					patientForm.setWeight(patientData.getWeight());
					patientForm.setHeight(patientData.getHeight());
					patientForm.setBmi(patientData.getBmi());
					patientForm.setFamHist(patientData.getFamilyHis());
					patientForm.setSymptomName(patientData.getSymName());
					patientForm.setComplaints(patientData.getPatComplaint());
					patientForm.setComplType(patientData.getComplaintType());
					patientForm.setPresentHistory(patientData.getPresentIllness());
					patientForm.setPastIllnessValue(patientData.getPastIllnessValue());
					patientForm.setFamilyHistValue(patientData.getFamilyHistValue());
					patientForm.setFamilyHistoryOthr(patientData.getFamilyHistoryOthr());
					patientForm.setPastHistryOthr(patientData.getPastIllenesOthr());
					patientForm.setRemarks(patientData.getRemarks());
					if(patientData.getNewBornBaby()!=null)
						patientForm.setNewBornBaby(patientData.getNewBornBaby());
					patientForm.setDrughistory(patientData.getDrugHstVal());
					patientForm.setMedicalHistVal(patientData.getMedHistVal());
					patientForm.setShowMedicalTextval(patientData.getMedicalHistoryOthr());
					patientForm.setRegionalLymphadenopathyDtrsTxt(patientData.getRegionalLymphadenopathyDtrsTxt());
					patientForm.setJawsDtrsTxt(patientData.getJawsDtrsTxt());
					patientForm.setRegionalLymphadenopathyDtrsSub(patientData.getRegionalLymphadenopathyDtrsSub());
					patientForm.setJawsDtrsSub(patientData.getJawsDtrsSub());
					patientForm.setTmjDtrsSub(patientData.getTmjDtrsSub());
					patientForm.setFaceDtrsSub(patientData.getFaceDtrsSub());
					List<LabelBean> intraoraldtls=null;
					String intraoral="HD2";
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					patientForm.setIntraoraldtls(intraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					
					patientForm.setDntsublistoral0(patientData.getDntsublistoral0());
					patientForm.setDntsublistoral1(patientData.getDntsublistoral1());
					patientForm.setDntsublistoral2(patientData.getDntsublistoral2());
					patientForm.setDntsublistoral3(patientData.getDntsublistoral3());
					patientForm.setDntsublistoral4(patientData.getDntsublistoral4());
					patientForm.setDntsublistoral5(patientData.getDntsublistoral5());
					patientForm.setDntsublistoral6(patientData.getDntsublistoral6());
					patientForm.setSwSite(patientData.getSwSite());
					patientForm.setSwSize(patientData.getSwSize());
					patientForm.setSwExtension(patientData.getSwExtension());
					patientForm.setSwColour(patientData.getSwColour());
					patientForm.setSwConsistency(patientData.getSwConsistency());
					patientForm.setSwTenderness(patientData.getSwTenderness());
					patientForm.setSwBorders(patientData.getSwBorders());
					patientForm.setPsSite(patientData.getPsSite());
					patientForm.setPsDischarge(patientData.getPsDischarge());
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					String occlusion="CH12";
					String deciduousdent ="CH13";
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					
					EhfPatient ehfPatient=null;
					ehfPatient=(EhfPatient)patientService.getPatientDetails(commonDtlsVO.getPATID());
					PreauthVO preauthDtlsVO = new PreauthVO();
					preauthDtlsVO = patientService.getPatientFullDtls(caseId, ehfPatient.getPatientId());		
					if(preauthDtlsVO.getCarriesdecidous()!=null)
					{
						patientForm.setCarriesdecidous(preauthDtlsVO.getCarriesdecidous());
					}
					
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", preauthDtlsVO);
					patientForm.setPreviousDentalTreatment(patientData.getPreviousDentalTreatment());
					patientForm.setOcclusionTxt(patientData.getOcclusionTxt());
					patientForm.setOcclusionTypeTxt(patientData.getOcclusionTypeTxt());
					patientForm.setOcclusionOtherTxt(patientData.getOcclusionOtherTxt());
				}
				CommonDtlsVO othrDtls=patientService.getOtherDtls(caseId, commonDtlsVO.getPATID());
				patientForm.setAllergy(othrDtls.getAllergy());
				patientForm.setHabbits(othrDtls.getHabbits());
				patientForm.setInvestigationLt(othrDtls.getInvestigations());
				patientForm.setDoctorName(patientService.getEmpNameById(lStrUserId));
			}
			if((treatmentDntl!=null && "yes".equalsIgnoreCase(treatmentDntl)) || (dentalrntdbvalue!=null && "yes".equalsIgnoreCase(dentalrntdbvalue)))
			lStrPageName="dentaldtrsForm";
			else
			lStrPageName="dtrsForm";
		}
		if("getPharmaOPCases".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			PatientVO retPatVO=null;
			List<PatientVO> registeredPatientsList=null;
			HashMap<String, String> searchMap=new HashMap<String, String>();
			int noOfPages=0;int totRowsCount=0;
			int lStrRowsperpage = 10;
			int lStrStartIndex = 0;
			int pageNo=0;
			String actionType=null;
			String pharmaScheme=config.getString("TG");
			String patientScheme=request.getParameter("patientScheme");
			request.setAttribute("patientScheme",patientScheme);
			request.setAttribute("pharmaScheme",pharmaScheme);
			String pharmaCases=request.getParameter("pharmaCases");
			String apprPharma=request.getParameter("apprPharma");
			request.setAttribute("pharmaCases",pharmaCases);
			request.setAttribute("apprPharma",apprPharma);
			searchMap.put("pharmaCases",pharmaCases);
			searchMap.put("apprPharma",apprPharma);
			searchMap.put("pharmaScheme",pharmaScheme);
			searchMap.put("patientScheme",patientScheme);
			searchMap.put("lStrStartIndex", lStrStartIndex+"");
			searchMap.put("lStrRowsperpage", lStrRowsperpage+"");
			searchMap.put("grpId",roleId);
			if(hospitalList.size()>0)
			{
				searchMap.put("userHospId",hospitalList.get(0).getID());
			}
			if(request.getParameter("pageNo")!=null && !request.getParameter("pageNo").equals("")){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(pageNo==0)
			{
				request.setAttribute("selectedPage",pageNo+1);
			}
			else
			{
				request.setAttribute("selectedPage",pageNo);
			}
			if(request.getParameter("actionType")!=null && !request.getParameter("actionType").equals("")){
				actionType=request.getParameter("actionType");
			}
			if(request.getParameter("advSearch")!=null && request.getParameter("advSearch").equalsIgnoreCase("true"))
			{
				String patientName="",patientNo="",caseNo="",cardNo="",district="",state="",fromDate="",toDate="",hospId="";

				if(actionType!=null && actionType.equalsIgnoreCase("link"))
				{
					if(!patientForm.getCurrCaseId().equals("")){
						caseNo=patientForm.getCurrCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getCurrPatId().equals("")){
						patientNo=patientForm.getCurrPatId();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCurrPatName().equals("")){
						patientName=patientForm.getCurrPatName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getCurrHealthCardNo().equals("")){
						cardNo=patientForm.getCurrHealthCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getCurrStateId().equals("-1") && !patientForm.getCurrStateId().equals("")){
						state=patientForm.getCurrStateId();
						searchMap.put("state",state);
					}
					if(!patientForm.getCurrDistrictId().equals("-1") && !patientForm.getCurrDistrictId().equals("")){
						district=patientForm.getCurrDistrictId();
						searchMap.put("district",district);
					}
					if(!patientForm.getCurrFromDate().equals("")){
						fromDate=patientForm.getCurrFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getCurrToDate().equals("")){
						toDate=patientForm.getCurrToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getCurrHospId().equals("-1") && !patientForm.getCurrHospId().equals("")){
						hospId=patientForm.getCurrHospId();
						searchMap.put("userHospId",hospId);
					}
				}
				else if(actionType!=null && actionType.equalsIgnoreCase("button"))
				{
					if(!patientForm.getCaseId().equals(""))
					{
						caseNo=patientForm.getCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getPatientName().equals(""))
					{
						patientName=patientForm.getPatientName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getPatientNo().equals(""))
					{
						patientNo=patientForm.getPatientNo();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCardNo().equals(""))
					{
						cardNo=patientForm.getCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getState().equals("-1"))
					{
						state=patientForm.getState();
						searchMap.put("state",state);
					}
					if(!patientForm.getDistrict().equals("-1"))
					{
						district=patientForm.getDistrict();
						searchMap.put("district",district);
					}
					if(!patientForm.getFromDate().equals(""))
					{
						fromDate=patientForm.getFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getToDate().equals(""))
					{
						toDate=patientForm.getToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getHospitalId().equals("-1"))
					{
						hospId=patientForm.getHospitalId();
						searchMap.put("userHospId",hospId);
					}
				}
			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				
				/*List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
				session.setAttribute("districtList",distList);*/
				
				if(!"GP1".equalsIgnoreCase(roleId) && !"GP2".equalsIgnoreCase(roleId))
				{
					session.setAttribute("hospList", commonService.getHospitals());
				}
			}
			if(pageNo>0)
			{
				lStrStartIndex = getStartIndex(lStrRowsperpage,pageNo);
				searchMap.put("lStrStartIndex", lStrStartIndex+"");
			}
			retPatVO=patientService.getOPCases(searchMap);
			registeredPatientsList=retPatVO.getRegisteredPatList();
			totRowsCount = retPatVO.getTotRowCount();
			noOfPages = getNoOfPages(totRowsCount, lStrRowsperpage);

			request.setAttribute("noOfPages", noOfPages);
			patientForm.setNoOfPages(noOfPages);

			if(registeredPatientsList.size()>0)
			{
				int endIndex=0;
				if(lStrStartIndex+lStrRowsperpage<= retPatVO.getTotRowCount())
				{
					endIndex=lStrStartIndex+lStrRowsperpage;
				}
				else
				{
					endIndex=retPatVO.getTotRowCount();
				}
				request.setAttribute("recordsList",lStrStartIndex+"-"+endIndex);
				request.setAttribute("totalRecords", retPatVO.getTotRowCount());
				request.setAttribute("registeredPatientsList", registeredPatientsList);
				request.setAttribute("searchListSize",retPatVO.getTotRowCount());
			}
			else if(retPatVO.getMsg()!=null && !retPatVO.getMsg().equalsIgnoreCase(""))
			{
				patientForm.setErrMsg(retPatVO.getMsg());
			}
			else
			{
				patientForm.setErrMsg(config.getString("err.NoRecordsFound"));
			}

			lStrPageName="opPharmPatientsView";
		}
		if("getOPCases".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			List<LabelBean> distList= new ArrayList<LabelBean>();
			PatientVO retPatVO=null;
			List<PatientVO> registeredPatientsList=null;
			HashMap<String, String> searchMap=new HashMap<String, String>();
			int noOfPages=0;int totRowsCount=0;
			int lStrRowsperpage = 10;
			int lStrStartIndex = 0;
			int pageNo=0;
			String actionType=null;
			String patientScheme=request.getParameter("patientScheme");
			request.setAttribute("patientScheme",patientScheme);
			String stateType=request.getParameter("stateType");
			searchMap.put("patientScheme",patientScheme);
			searchMap.put("lStrStartIndex", lStrStartIndex+"");
			searchMap.put("lStrRowsperpage", lStrRowsperpage+"");
			searchMap.put("grpId",roleId);
			if(hospitalList.size()>0)
			{
				searchMap.put("userHospId",hospitalList.get(0).getID());
			}
			if(request.getParameter("pageNo")!=null && !request.getParameter("pageNo").equals("")){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(pageNo==0)
			{
				request.setAttribute("selectedPage",pageNo+1);
			}
			else
			{
				request.setAttribute("selectedPage",pageNo);
			}
			if(request.getParameter("actionType")!=null && !request.getParameter("actionType").equals("")){
				actionType=request.getParameter("actionType");
			}
			if(request.getParameter("advSearch")!=null && request.getParameter("advSearch").equalsIgnoreCase("true"))
			{
				String patientName="",patientNo="",caseNo="",cardNo="",district="",state="",fromDate="",toDate="",hospId="";

				if(actionType!=null && actionType.equalsIgnoreCase("link"))
				{
					if(!patientForm.getCurrCaseId().equals("")){
						caseNo=patientForm.getCurrCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getCurrPatId().equals("")){
						patientNo=patientForm.getCurrPatId();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCurrPatName().equals("")){
						patientName=patientForm.getCurrPatName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getCurrHealthCardNo().equals("")){
						cardNo=patientForm.getCurrHealthCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getCurrStateId().equals("-1") && !patientForm.getCurrStateId().equals("")){
						state=patientForm.getCurrStateId();
						searchMap.put("state",state);
					}
					if(!patientForm.getCurrDistrictId().equals("-1") && !patientForm.getCurrDistrictId().equals("")){
						district=patientForm.getCurrDistrictId();
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
						searchMap.put("district",district);
					}
					if(!patientForm.getCurrFromDate().equals("")){
						fromDate=patientForm.getCurrFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getCurrToDate().equals("")){
						toDate=patientForm.getCurrToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getCurrHospId().equals("-1") && !patientForm.getCurrHospId().equals("")){
						hospId=patientForm.getCurrHospId();
						searchMap.put("userHospId",hospId);
					}
				}
				else if(actionType!=null && actionType.equalsIgnoreCase("button"))
				{
					if(!patientForm.getCaseId().equals(""))
					{
						caseNo=patientForm.getCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getPatientName().equals(""))
					{
						patientName=patientForm.getPatientName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getPatientNo().equals(""))
					{
						patientNo=patientForm.getPatientNo();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCardNo().equals(""))
					{
						cardNo=patientForm.getCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getState().equals("-1"))
					{
						state=patientForm.getState();
						searchMap.put("state",state);
					}
					if(!patientForm.getDistrict().equals("-1"))
					{
						district=patientForm.getDistrict();
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
						searchMap.put("district",district);
					}
					if(!patientForm.getFromDate().equals(""))
					{
						fromDate=patientForm.getFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getToDate().equals(""))
					{
						toDate=patientForm.getToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getHospitalId().equals("-1"))
					{
						hospId=patientForm.getHospitalId();
						searchMap.put("userHospId",hospId);
					}
				}
			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				
				/*List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
				session.setAttribute("districtList",distList);*/
				
				if(!"GP1".equalsIgnoreCase(roleId) && !"GP2".equalsIgnoreCase(roleId))
				{
					session.setAttribute("hospList", commonService.getHospitals());
				}
			}
			if(pageNo>0)
			{
				lStrStartIndex = getStartIndex(lStrRowsperpage,pageNo);
				searchMap.put("lStrStartIndex", lStrStartIndex+"");
			}
			retPatVO=patientService.getOPCases(searchMap);
			registeredPatientsList=retPatVO.getRegisteredPatList();
			totRowsCount = retPatVO.getTotRowCount();
			noOfPages = getNoOfPages(totRowsCount, lStrRowsperpage);

			request.setAttribute("noOfPages", noOfPages);
			patientForm.setNoOfPages(noOfPages);

			if(registeredPatientsList.size()>0)
			{
				int endIndex=0;
				if(lStrStartIndex+lStrRowsperpage<= retPatVO.getTotRowCount())
				{
					endIndex=lStrStartIndex+lStrRowsperpage;
				}
				else
				{
					endIndex=retPatVO.getTotRowCount();
				}
				request.setAttribute("recordsList",lStrStartIndex+"-"+endIndex);
				request.setAttribute("totalRecords", retPatVO.getTotRowCount());
				request.setAttribute("registeredPatientsList", registeredPatientsList);
				request.setAttribute("searchListSize",retPatVO.getTotRowCount());
			}
			else if(retPatVO.getMsg()!=null && !retPatVO.getMsg().equalsIgnoreCase(""))
			{
				patientForm.setErrMsg(retPatVO.getMsg());
			}
			else
			{
				patientForm.setErrMsg(config.getString("err.NoRecordsFound"));
			}
			request.setAttribute("districtList",distList);
			lStrPageName="opRegPatientsView";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "viewAttachment" ) )
	      {
			String lStrFilePath = null;
      	   	String docSeqId = request.getParameter("docSeqId");
      	   	String lStrContentType = request.getContentType();
	          lStrContentType = (lStrContentType==null)?FileConstants.EMPTY_STRING:lStrContentType;
/*      	   if(docSeqId != null && !docSeqId.equalsIgnoreCase(""))
      	   {
      		   lStrFilePath = attachmentService.getIvestPath(docSeqId);  
      	   }
      	   else
      		   lStrFilePath = request.getParameter("filePath");*/ 
	          lStrFilePath = request.getParameter("filePath");
      	    File file = null ;
		        FileInputStream fis1 = null ;
		        DataInputStream dis1 = null ;
		        String lStrType = null;
		        ServletOutputStream out = response.getOutputStream();
	          /**
	           * 
	           */
	          String fileExt = lStrFilePath.substring((lStrFilePath.lastIndexOf(".")+1));
	          String lStrFileName=lStrFilePath.substring((lStrFilePath.lastIndexOf("/")+1));
	          String attachMode="attachment";
	          if(fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPG) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPEG) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_GIF)){
	              attachMode="inline";
	          }
	           lStrContentType=FileConstants.FILE_EXT.get(fileExt);
	          
	          /**
	           * 
	           */
	           String dir =  lStrFilePath  ;
	          byte[] lbBytes = null ;
	          try
	          {
	            if ( lStrFileName != null && !lStrFileName.equals ( "" ) )
	            {
	              file = new File ( dir ) ; 
	              fis1 = new FileInputStream ( file ) ;
	              dis1 = new DataInputStream ( fis1 ) ;
	              lbBytes = new byte[ dis1.available () ] ;
	              fis1.read ( lbBytes ) ;
	              request.setAttribute ( "File", lbBytes ) ;
	              request.setAttribute ( "ContentType", lStrContentType ) ;
	              request.setAttribute ( "FileName", lStrFileName ) ;
	              request.setAttribute ( "Extn", lStrType ) ;
	              response.setContentType ( lStrContentType ) ;
	              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);//006
	              out.write(lbBytes);
	              //out.close();			             
	            }
	            else
	            {
	              lbBytes = new byte[ 0 ] ;
	            }
	          }
	          catch ( Exception e )
	          {
	            e.getMessage () ;
	            e.printStackTrace();
	          }
	          finally
	          {
	        	  out.close();
	          }
	          
	      }
		
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getOpDrugDtlsAuto" ) )
		{
			String chemicalCode=request.getParameter("chemicalCode");
			Gson jsonData=new Gson();
			EhfmOpDrugMst ehfmOpDrugMst=new EhfmOpDrugMst();
			ehfmOpDrugMst=patientService.getopdrugDataAuto(chemicalCode);
			request.setAttribute("AjaxMessage",jsonData.toJson(ehfmOpDrugMst));
			return mapping.findForward("ajaxResult");
			
			
		}
		
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getdiagDtlsAuto" ) )
		{
			String disAnatomicalCode=request.getParameter("disAnatomicalCode");
			Gson jsonData=new Gson();
			LabelBean diagDtls=new LabelBean();
			if(disAnatomicalCode!=null && !("").equalsIgnoreCase(disAnatomicalCode))
			{
			diagDtls=patientService.getDiagnosisDtlsAuto(disAnatomicalCode);
			}
			request.setAttribute("AjaxMessage",jsonData.toJson(diagDtls));
			return mapping.findForward("ajaxResult");
			
			
		}
		
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getdiagListAuto" ) )
		{
			String diagName=request.getParameter("diagName");
			String diagType=request.getParameter("diagType");
			Gson jsonData=new Gson();
			List<LabelBean> diagDtls=new ArrayList<LabelBean>();
			if(diagName!=null && !("").equalsIgnoreCase(diagName))
			{
			diagDtls=patientService.getDiagListSearch(diagName,diagType);
			}
			request.setAttribute("AjaxMessage",jsonData.toJson(diagDtls));
			return mapping.findForward("ajaxResult");
			
			
		}
		
		/*if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "uploadRegPhoto" ) )
	      {
				String uplaodPhotoPath=config.getString("RegBabyPhotosPath");
				String uploadStatus=request.getParameter("photoID");
				FormFile newFile = null;
				String msg="";
				if(uploadStatus!=null)
					{
						if(uploadStatus.equalsIgnoreCase(config.getString("")))
							{
								newFile=patientForm.getChildPhoto();
							}
						else if(uploadStatus.equalsIgnoreCase(config.getString("")))
							{
								newFile=patientForm.getIdentificationAttachment();
							}
						if(newFile!=null)
							{	
								if(newFile.getFileSize()>204800)
									{
										msg="Cannot upload files of size greater than 200KB";
									}
								else
									{
										try
											{
												boolean fileFlag=new File(uplaodPhotoPath).mkdirs();
												String fileCompleteName=uplaodPhotoPath+newFile.getFileName();
												System.out.println(fileFlag);
												byte [] fileData=newFile.getFileData();
												File file =new File(fileCompleteName);
												FileOutputStream fos=new FileOutputStream(file);
												fos.write(fileData);
												fos.close();
											}
										catch(Exception e)
											{
												e.printStackTrace();
											}
									}
							}
						else
							{
								msg="Please Insert the File Once Again";
							}
					}
				else 
					{
						msg="Please Insert the File Once Again";
					}
				
	      }*/
		if(lStrActionVal != null && "getOpClaimCasesList".equals(lStrActionVal)){
        	String claimDt  = request.getParameter("claimDt");
			String patientId  = request.getParameter("patientId");
			String crNo = request.getParameter("crNo");
			String cardNo = request.getParameter("cardNo");//Chandana - 7845 - Added this for search based on the card number
			String letterNo = request.getParameter("letterNo");
			String caseType = request.getParameter("caseType");
			String clmStatus = request.getParameter("claimStatus");
			String claimSubmittedNo = request.getParameter("claimSubmittedNo");
			String ajaxCall = request.getParameter("ajaxCall");
			HashMap<String, String> hashMap = new HashMap<String, String>();
			ClaimCases.releaseCase("0");//Chandana - 8598 - To release a lock
			
			hashMap.put("claimDt", claimDt);
			hashMap.put("patientId", patientId);
			hashMap.put("crNo", crNo);
			hashMap.put("claimSubmittedNo", claimSubmittedNo);
			hashMap.put("letterNo", letterNo);
			hashMap.put("claimStatus", clmStatus);
			hashMap.put("cardNo", cardNo);
			String acoFlag = "N";//Chandana - 8602 - Added the flag for aco
			List<LabelBean> opClaimCasesList = patientService.getOpClaimCasesList(hashMap);
			List<LabelBean> claimStatusList = patientService.getNimsOPDClaimStatusLst(acoFlag);//Chandana - 8602 - Added acoFlag to method
			Gson gson = new Gson();
			if(ajaxCall == null || !"Y".equalsIgnoreCase(ajaxCall)){
				request.setAttribute("opClaimCasesList", opClaimCasesList);
				request.setAttribute("claimStatusList", claimStatusList);
				return mapping.findForward("opClaimCases");
			}else{
	        	String gsonString = gson.toJson(opClaimCasesList);   
	        	response.getWriter().write(gsonString);
	        	return null;
			}
        }
        if(lStrActionVal != null && "getOpClaimCaseDtls".equals(lStrActionVal)){
        	EhfPatient ehfPatient = new EhfPatient();
        	String patientId = null;
        	String acoFlag = request.getParameter("acoFlag");//Chandana - 8602 - To know the hit is from aco role
        	if("Y".equalsIgnoreCase(acoFlag)){//Chandana - 8602 - Added if condition to get the patient id using opclaimno
        		String opClaimNo = request.getParameter("patientId");
        		String seqId = request.getParameter("seqId");
        		patientId = patientService.getPatNoFromClaimNo(opClaimNo,seqId);
        	}
        	else
        		patientId = request.getParameter("patientId");
        	String crNo = request.getParameter("crNo");//Chandana - 22-10-25 - Added this to get the cr number from request
        	//String approvedAmount = null;
        	if("GP29".equalsIgnoreCase(roleId) || "GP3".equalsIgnoreCase(roleId) || "GP9".equalsIgnoreCase(roleId)){//Chandana - 8602 - To get the patient number using the op claim case number
        		String amount = patientService.getApprovedAmount(patientId);
        		String approvedAmount = "0"; String deductedAmount = "0";
        		if (amount != null && amount.contains("~")) {
                    String[] parts = amount.split("~");
                    approvedAmount = parts[0];
                    deductedAmount = parts[1];
                }
        		request.setAttribute("approvedAmount", approvedAmount);
        		request.setAttribute("deductedAmount", deductedAmount);
			}
			patientForm.setPatientNo(patientId);
			patientForm.setCrNumber(crNo);//Chandana - 8606 - Added this to setting the cr number to patient form to show the cr number in ui
			String userID=(String) session.getAttribute("UserID");
			String check=patientService.checkDentalClinic(userID,patientId);
			String hospGov=patientService.checkGovernmentHosp(userID,patientId);
			if(!check.equals("hospital")){
				patientForm.setHosType("DC");
			}else{
				patientForm.setHosType("hospital");
			}
			ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
			session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
			patientForm.setCardNo(ehfPatient.getCardNo());
			patientForm.setFname(ehfPatient.getName());
			if(ehfPatient.getCardIssueDt()!=null){
				patientForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
			}else
				patientForm.setCardIssueDt("NA");
			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender())){
				patientForm.setGender("Male");
			}
			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender())){
				patientForm.setGender("Female");
			}
			if(ehfPatient.getEnrollDob()!=null){
				patientForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
			}
			if(ehfPatient.getAge()!=null){
				patientForm.setYears(ehfPatient.getAge().toString());
				if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 )){
					request.setAttribute("child", "Y");
				}else{
					request.setAttribute("child", "N");
				}
			}
			if(ehfPatient.getAgeMonths()!=null){
				patientForm.setMonths(ehfPatient.getAgeMonths().toString());
			}
			if(ehfPatient.getAgeDays()!=null){
				patientForm.setDays(ehfPatient.getAgeDays().toString());
			}
			String relationId=ehfPatient.getRelation();
			String relationName=patientService.getRelationName(relationId);
			patientForm.setRelation(relationName);
			String casteId=ehfPatient.getCaste();
			if(casteId!=null && !casteId.equals(""))
			{
				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
				patientForm.setCaste(casteName);
			}
			if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
			{
				patientForm.setContactno(ehfPatient.getContactNo().toString());
			}
			patientForm.setOccupation(ehfPatient.getOccupationCd());
			if(ehfPatient.getSchemeId()!=null )
				session.setAttribute("regPatientScheme",ehfPatient.getSchemeId());
			patientForm.setScheme(ehfPatient.getSchemeId());
			patientForm.setTelScheme(ehfPatient.getSchemeId());
			String slabType=null;
			String slab=null;
			if(ehfPatient.getSlab()!=null)
			{
				slabType=ehfPatient.getSlab();
			}
			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.SemiPrivateWard");
			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.PrivateWard");
			patientForm.setSlab(slab);
			patientForm.setHno(ehfPatient.getHouseNo());
			patientForm.setStreet(ehfPatient.getStreet());
			patientForm.setState(patientService.getLocationName(ehfPatient.getState()));
			String distCode=ehfPatient.getDistrictCode();
			String distName=patientService.getLocationName(distCode);
			patientForm.setDistrict(distName);
			String mandalCode=ehfPatient.getMandalCode();
			String mandalName=patientService.getLocationName(mandalCode);
			patientForm.setMandal(mandalName);
			String villageCode=ehfPatient.getVillageCode();
			String villageName=patientService.getLocationName(villageCode);
			patientForm.setVillage(villageName);
			if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
				patientForm.setPin(ehfPatient.getPinCode());
			else
				patientForm.setPin("NA");
			patientForm.setComm_hno(ehfPatient.getcHouseNo());
			patientForm.setComm_street(ehfPatient.getcStreet());
			patientForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
			patientForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
			patientForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
			patientForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
			if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
				patientForm.setComm_pin(ehfPatient.getcPinCode());
			else
				patientForm.setComm_pin("NA");
			patientForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
			String photoUrl=null;
			if(ehfPatient.getPatientScheme()!=null)
				{
					patientForm.setPatientScheme(ehfPatient.getPatientScheme());
					request.setAttribute("patientScheme",ehfPatient.getPatientScheme());
					if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
							config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
						 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
					else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
							 && ehfPatient.getNewBornBaby().equalsIgnoreCase("Y"))
						photoUrl=null;
					else
						photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
				}
			else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
					 && ehfPatient.getNewBornBaby().equalsIgnoreCase(config.getString("Y")))
				photoUrl=null;
			else
				photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
			if(photoUrl!=null)
			{
				try
				{
					File photo = new File(photoUrl);
					fis = new FileInputStream(photo);
					dis= new DataInputStream(fis);
					bytes = new byte[dis.available()];
					fis.read(bytes);
					String lStrContentType=null;
					lStrContentType="image/jpg";
					session.setAttribute("ContentType", lStrContentType);
					session.setAttribute("File", bytes);
					fis.close();
					dis.close();
					patientForm.setPhotoUrl(photoUrl);
				}
				catch(Exception e)
				{
					GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
				}
			}
			String hospCode=ehfPatient.getRegHospId();
			String hospName=patientService.getHospName(hospCode);
			String dentalFlg=patientService.checkDentalHospital(hospCode);
			patientForm.setHospitalId(hospCode);
			request.setAttribute("hospitalId",hospCode);
			patientForm.setHospitalName(hospName);
			HashMap hashMap = new HashMap();
			hashMap.put("patientId", patientId);
			List<LabelBean> billDtls = patientService.getOpClaimBillDtls(hashMap);
			if(!billDtls.isEmpty() || billDtls.size()>0){
			 claimStatus = billDtls.get(0).getCASESTATUS();//Chandana - 7845 - getting the claim status
			}
			request.setAttribute("opClaimBillDtls", billDtls);
			List<LabelBean> conslidateBill = patientService.getOPConsolidateBill(hashMap);//Chandana - 8874 - Calling method to get the consolidated attachment
			request.setAttribute("conslidateBill", conslidateBill);
			request.setAttribute("claimStatus", claimStatus);//Chandana - 7845 - sending the status to jsp
			if(billDtls == null || billDtls.isEmpty() || billDtls.size() > 0){
				String opdClaimNo = patientService.checkOpClaimNo(patientId);
				request.setAttribute("opdClaimNo", opdClaimNo);
			}
			hashMap.put("roleId", roleId);
			List<LabelBean> workFlowDtls = patientService.getWorkFlowDtls(hashMap);
			request.setAttribute("workFlowDtls", workFlowDtls);
			//Chandana - 8441 - adding below code for getting the additional attachments and setting the list to attribute
			List<LabelBean> additionalAttach = patientService.getAddtnalAttach(hashMap);
			request.setAttribute("additionalAttach", additionalAttach);
			request.setAttribute("roleId", roleId);
			request.setAttribute("dataIsPresent", "Y");
			if("Y".equalsIgnoreCase(acoFlag))//Chandana - 8602 - to redirect to ACO related result page
				lStrPageName = "opClaimCaseDtlsForACO";
			else
				lStrPageName = "opClaimCaseDtls";
        }
        //Chandana - 8441 - Added this action flag for deleting or saving the additional attachments
        if(lStrActionVal != null && "updateAdditnalAttach".equalsIgnoreCase(lStrActionVal)){
        	PatientForm patForm = (PatientForm) form;
        	FormFile file = null;
        	String fileName = null;
        	String description = null;
        	String remarks = null;
        	
	        file = patForm.getAttachment();
			fileName = patForm.getFname();
			description = patientForm.getMsg();
			remarks = patientForm.getRemarks();
        	String seqId = request.getParameter("seqId");
        	String claimNo = request.getParameter("patientNo");
        	String action = request.getParameter("action"); 
        	HashMap hashMap = new HashMap();
        	hashMap.put("seqId",seqId);
        	hashMap.put("file", file);
        	hashMap.put("fileName", fileName);
        	hashMap.put("claimNo", claimNo);
        	hashMap.put("lStrUserId", lStrUserId);
        	hashMap.put("action", action);
        	hashMap.put("description", description);
        	hashMap.put("remarks", remarks);
        	String attachDel = patientService.updateAdditionalAttach(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(attachDel);   
        	response.getWriter().write(gsonString);
        	return null;
        }
      //Chandana - 8599 - Added this action flag for deleting or saving the additional attachments
        if(lStrActionVal != null && "uploadMissingAttachments".equalsIgnoreCase(lStrActionVal)){
        	PatientForm patForm = (PatientForm) form;
        	FormFile file = null;
        	String fileName = null;
	        file = patForm.getAttachment();
			fileName = patForm.getFname();
        	String claimNo = request.getParameter("patientNo");
        	String billNo = request.getParameter("billNo");
        	String seqId = request.getParameter("seqId");
        	String crNo = request.getParameter("crNo");
        	HashMap hashMap = new HashMap();
        	hashMap.put("file", file);
        	hashMap.put("fileName", fileName);
        	hashMap.put("claimNo", claimNo);
        	hashMap.put("lStrUserId", lStrUserId);
        	hashMap.put("billNo", billNo);
        	hashMap.put("seqId", seqId);
        	hashMap.put("crNo", crNo);
        	String attachDel = patientService.updateMissingAttach(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(attachDel);   
        	response.getWriter().write(gsonString);
        	return null;
        }
        if(lStrActionVal != null && "updateMedcoOpClaim".equalsIgnoreCase(lStrActionVal)){
        	String status = null;
        	String patientId = (String) request.getParameter("patientId");
        	String userId = (String) session.getAttribute("UserID");
        	String remarks = (String) request.getParameter("remarks");
        	status = (String) request.getParameter("status");
        	if("Submit".equalsIgnoreCase(status))//Chandana -7845- to send the actionDone status to get the next status based on the current status and action done columns 
        		actnDone = "CD7321";
        	else if("ReSubmit".equalsIgnoreCase(status))//Chandana - 8036 - Added this for resubmitted by medco for PEX and CH pending
        		actnDone = "CD7333";
        	
        	HashMap hashMap = new HashMap();
        	hashMap.put("patientId", patientId);
        	hashMap.put("userId",userId);
        	hashMap.put("remarks", remarks);
        	hashMap.put("status", status);
        	/*hashMap.put("opdClaimNo", opdClaimNo);*/
        	claimStatus = patientService.getClaimStatus(patientId);//Chandana-7845 - to get the claim status using claim no(patientId)
        	if(claimStatus != null || "".equalsIgnoreCase(claimStatus)) //Chandana -7845- if not null then sending the claim status and taking back the next status
        		 nextRoleStatus = patientService.getNextStatus(claimStatus,actnDone);
        	if (nextRoleStatus != null && nextRoleStatus.contains("~")) {//Chandana - 8602 - Added if condition and getting the both next action and user role from the workflow status table
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
        	hashMap.put("nextStatus", nextStatus);
        	hashMap.put("userRole", userRole);//Chandana - 8602 - adding the userRole id to hashmap
        	String opdClaimApprovedSeq = patientService.updateMedcoOpClaim(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(opdClaimApprovedSeq);   
        	response.getWriter().write(gsonString);
        	return null;
        }
        if(lStrActionVal != null && "viewDocuments".equalsIgnoreCase(lStrActionVal)){
        	String filePath = (String) request.getParameter("filePath");
        	if (filePath != null && !filePath.isEmpty()) {
    			String base64Data = null;
    			if(filePath.toLowerCase().endsWith(".pdf")){
    				base64Data = FileConstants.convertPDFToBase64(filePath);
    			}else{
    				base64Data = FileConstants.convertImageToBase64(filePath);
    			}
    			response.setContentType("text/html");		
    			response.getWriter().write(base64Data);
    		}
        }
        if(lStrActionVal != null && "getNimsOpClaimCasesforApproval".equalsIgnoreCase(lStrActionVal)){
        	String status = (String) request.getParameter("status");
        	List<String> claimsCasesList = new ArrayList<String>();
        	String autoCaseId = (String) request.getParameter("autoCaseId");
    	    if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
    	    	autoCaseId = "0";
    	    ClaimCases.releaseCase(autoCaseId);
    	    
    	    String assigningPatientId = null;
    	    String claimSeqId = null;
    	    String crNumber = null;//Chandana - 8606 - New variable for CR number
    	    String[] claimCasePart = null;
    	    
        	claimsCasesList = patientService.getNimsOpClaimCases(status,roleId,lStrUserId);//Chandana - 8036 - added roleId condition to differentiate CH or CEX
        	
        	if(!claimsCasesList.isEmpty() && claimsCasesList.size() > 0){
        		request.setAttribute("dataIsPresent", "Y");
        		String checkForCases = ClaimCases.getCaseForUserId(lStrUserId, status);
        		
        		if(checkForCases != null && checkForCases.equalsIgnoreCase("false")){
        			if(!claimsCasesList.isEmpty() && claimsCasesList.size() > 0){
        				for(String claimCase : claimsCasesList){
        					System.out.println("Cases: "+ claimCase);
        					claimCasePart = claimCase.split("~");
        					claimSeqId = claimCasePart[0];
        					crNumber = claimCasePart[2];//Chandana - 8606 - Added this to get the cr number
        					boolean caseAvailbility = ClaimCases.isAvailable(claimSeqId, lStrUserId, status);
        					if(caseAvailbility){
        						assigningPatientId = claimCasePart[1];
        						if("CD7343".equalsIgnoreCase(claimCasePart[3])){//Chandana - 8602 - Case should be assigned to the specific CH who worked on it if it's reverted by the ACO.
        							String sameChUser = patientService.getFlagForACOReturnedClaim(lStrUserId,claimCasePart[0]);
        							if("Y".equalsIgnoreCase(sameChUser))
        								break;
        							else if("N".equalsIgnoreCase(sameChUser))
        								request.setAttribute("dataIsPresent", "N");
        						}
        						else{
        							request.setAttribute("dataIsPresent", "Y");
        							break;
        						}
        					}
        				}
        			}else{
        				request.setAttribute("dataIsPresent", "N");
        			}
        		}else{
        			if(!claimsCasesList.isEmpty() && claimsCasesList.size() > 0){
        				for(String claimCase : claimsCasesList){
        					System.out.println("Cases: "+ claimCase);
        					claimCasePart = claimCase.split("~");
        					claimSeqId = claimCasePart[0];
        					crNumber = claimCasePart[2];//Chandana - 8606 - Added this to get the cr number
							if("CD7343".equalsIgnoreCase(claimCasePart[3])){//Chandana - 8602 - Case should be assigned to the specific CH who worked on it if it's reverted by the ACO.
    							String sameChUser = patientService.getFlagForACOReturnedClaim(lStrUserId,claimCasePart[0]);
    							if("Y".equalsIgnoreCase(sameChUser))
    								break;
    							else if("N".equalsIgnoreCase(sameChUser))
    								request.setAttribute("dataIsPresent", "N");
    						}
    						else{
    							request.setAttribute("dataIsPresent", "Y");
    							break;
    						}
        					if(checkForCases.equalsIgnoreCase(claimSeqId)){
        						assigningPatientId = claimCasePart[1];
        						break;
        					}
        				}
        			}else{
        				request.setAttribute("dataIsPresent", "N");
        			}
        		}
        	}else{
        		request.setAttribute("dataIsPresent", "N");
        	}
        	patientForm.setCrNumber(crNumber);//Chandana - 8606 - Setting the cr number to patient form to show the cr number in ui
        	if(assigningPatientId != null && !"".equalsIgnoreCase(assigningPatientId)){
        		EhfPatient ehfPatient = new EhfPatient();
        		patientForm.setPatientNo(assigningPatientId);
    			String check=patientService.checkDentalClinic(lStrUserId,assigningPatientId);
    			String hospGov=patientService.checkGovernmentHosp(lStrUserId,assigningPatientId);
    			if(!check.equals("hospital")){
    				patientForm.setHosType("DC");
    			}else{
    				patientForm.setHosType("hospital");
    			}
    			ehfPatient=(EhfPatient)patientService.getPatientDetails(assigningPatientId);
    			session.setAttribute("patCrtdt",ehfPatient.getCrtDt());

    			patientForm.setCardNo(ehfPatient.getCardNo());
    			patientForm.setFname(ehfPatient.getName());
    			
    			if(ehfPatient.getCardIssueDt()!=null){
    				patientForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
    			}else
    				patientForm.setCardIssueDt("NA");
    			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender())){
    				patientForm.setGender("Male");
    			}
    			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender())){
    				patientForm.setGender("Female");
    			}
    			if(ehfPatient.getEnrollDob()!=null){
    				patientForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
    			}
    			if(ehfPatient.getAge()!=null){
    				patientForm.setYears(ehfPatient.getAge().toString());
    				if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 )){
    					request.setAttribute("child", "Y");
    				}else{
    					request.setAttribute("child", "N");
    				}
    			}
    			if(ehfPatient.getAgeMonths()!=null){
    				patientForm.setMonths(ehfPatient.getAgeMonths().toString());
    			}
    			if(ehfPatient.getAgeDays()!=null){
    				patientForm.setDays(ehfPatient.getAgeDays().toString());
    			}
    			String relationId=ehfPatient.getRelation();
    			String relationName=patientService.getRelationName(relationId);
    			patientForm.setRelation(relationName);
    			String casteId=ehfPatient.getCaste();
    			if(casteId!=null && !casteId.equals(""))
    			{
    				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
    				patientForm.setCaste(casteName);
    			}
    			if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
    			{
    				patientForm.setContactno(ehfPatient.getContactNo().toString());
    			}

    			patientForm.setOccupation(ehfPatient.getOccupationCd());
    			if(ehfPatient.getSchemeId()!=null )
    				session.setAttribute("regPatientScheme",ehfPatient.getSchemeId());
    			patientForm.setScheme(ehfPatient.getSchemeId());
    			
    			patientForm.setTelScheme(ehfPatient.getSchemeId());
    			
    			String slabType=null;
    			String slab=null;
    			if(ehfPatient.getSlab()!=null)
    			{
    				slabType=ehfPatient.getSlab();
    			}
    			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
    				slab=config.getString("Slab.Name.SemiPrivateWard");
    			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
    				slab=config.getString("Slab.Name.PrivateWard");
    			patientForm.setSlab(slab);

    			patientForm.setHno(ehfPatient.getHouseNo());
    			patientForm.setStreet(ehfPatient.getStreet());
    			patientForm.setState(patientService.getLocationName(ehfPatient.getState()));
    			String distCode=ehfPatient.getDistrictCode();
    			String distName=patientService.getLocationName(distCode);
    			patientForm.setDistrict(distName);
    			String mandalCode=ehfPatient.getMandalCode();
    			String mandalName=patientService.getLocationName(mandalCode);
    			patientForm.setMandal(mandalName);
    			String villageCode=ehfPatient.getVillageCode();
    			String villageName=patientService.getLocationName(villageCode);
    			patientForm.setVillage(villageName);
    			if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
    				patientForm.setPin(ehfPatient.getPinCode());
    			else
    				patientForm.setPin("NA");

    			patientForm.setComm_hno(ehfPatient.getcHouseNo());
    			patientForm.setComm_street(ehfPatient.getcStreet());
    			patientForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
    			patientForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
    			patientForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
    			patientForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
    			if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
    				patientForm.setComm_pin(ehfPatient.getcPinCode());
    			else
    				patientForm.setComm_pin("NA");
    			patientForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
    			String photoUrl=null;
    			if(ehfPatient.getPatientScheme()!=null)
    				{
    					patientForm.setPatientScheme(ehfPatient.getPatientScheme());
    					request.setAttribute("patientScheme",ehfPatient.getPatientScheme());
    					if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
    							config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
    						 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
    					else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
    							 && ehfPatient.getNewBornBaby().equalsIgnoreCase("Y"))
    						photoUrl=null;
    					else
    						photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
    				}
    			else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
    					 && ehfPatient.getNewBornBaby().equalsIgnoreCase(config.getString("Y")))
    				photoUrl=null;
    			else
    				photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
    			
    			if(photoUrl!=null)
    			{
    				try
    				{
    					File photo = new File(photoUrl);
    					fis = new FileInputStream(photo);
    					dis= new DataInputStream(fis);
    					bytes = new byte[dis.available()];
    					fis.read(bytes);
    					String lStrContentType=null;
    					lStrContentType="image/jpg";
    					session.setAttribute("ContentType", lStrContentType);
    					session.setAttribute("File", bytes);
    					fis.close();
    					dis.close();
    					patientForm.setPhotoUrl(photoUrl);
    				}
    				catch(Exception e)
    				{
    					GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
    				}
    			}
    			String hospCode=ehfPatient.getRegHospId();
    			String hospName=patientService.getHospName(hospCode);
    			String dentalFlg=patientService.checkDentalHospital(hospCode);
    			patientForm.setHospitalId(hospCode);
    			request.setAttribute("hospitalId",hospCode);
    			patientForm.setHospitalName(hospName);
    			
    			HashMap hashMap = new HashMap();
    			hashMap.put("patientId", assigningPatientId);
    			hashMap.put("claimAssignFlag", "Y");
    			List<LabelBean> billDtls = patientService.getOpClaimBillDtls(hashMap);
    			request.setAttribute("opClaimBillDtls", billDtls);
    			 claimStatus = billDtls.get(0).getCASESTATUS();//Chandana - 7845 - getting the claim status
    			request.setAttribute("claimStatus", claimStatus);//Chandana - 7845 - sending the status to jsp
    			String opdClaimNo = patientService.checkOpClaimNo(assigningPatientId);
				request.setAttribute("opdClaimNo", opdClaimNo);
				List<LabelBean> conslidateBill = patientService.getOPConsolidateBill(hashMap);//Chandana - 8934 - Calling method to get the consolidated attachment
				request.setAttribute("conslidateBill", conslidateBill);
				List<LabelBean> workFlowDtls = patientService.getWorkFlowDtls(hashMap);
				request.setAttribute("workFlowDtls", workFlowDtls);
				//Chandana - 8441 - adding below code for getting the additional attachments and setting the list to attribute
				List<LabelBean> additionalAttach = patientService.getAddtnalAttach(hashMap);
				request.setAttribute("additionalAttach", additionalAttach);
        	}else{
        		request.setAttribute("dataIsPresent", "N");
        	}
        	request.setAttribute("roleId", roleId);
        	if("CD7343".equalsIgnoreCase(claimStatus) && ("GP29".equalsIgnoreCase(roleId) || "GP9".equalsIgnoreCase(roleId))){//Chandana - 8602 - if condition to get the approved amount for CD7343 this status
        		String amount = patientService.getApprovedAmount(patientForm.getPatientNo());
        		String approvedAmount = "0"; String deductedAmount = "0";
        		if (amount != null && amount.contains("~")) {
                    String[] parts = amount.split("~");
                    approvedAmount = parts[0];
                    deductedAmount = parts[1];
                }
        		request.setAttribute("approvedAmount", approvedAmount);
        		request.setAttribute("deductedAmount", deductedAmount);
			}
        	
        	lStrPageName = "opClaimCaseDtls";
        }
        else if("updateCHOpClaim".equalsIgnoreCase(lStrActionVal)){
        	String result = null;
        	String status = request.getParameter("status");
        	String opdClaimNo = request.getParameter("opdClaimNo");
        	String deductAmt = request.getParameter("deductAmt");
        	String finalAmt = request.getParameter("finalAmt");
        	String remarks = request.getParameter("remarks");
        	String patientId = request.getParameter("patientId");
        	HashMap hashMap = new HashMap();
        	
        	hashMap.put("status", status);
        	hashMap.put("opdClaimNo", opdClaimNo);
        	hashMap.put("remarks", remarks);
        	hashMap.put("deductAmt", deductAmt);
        	hashMap.put("finalAmt", finalAmt);
        	hashMap.put("userId",lStrUserId);
        	hashMap.put("patientId",patientId);
        	
        	if("Approve".equalsIgnoreCase(status))
        		actnDone = "CD7322";
        	else if("ReApprove".equalsIgnoreCase(status))//Chandana - 8602 - Added this else if condition for Re-Approving the claim coming from the ACO
        		actnDone = "CD7346";
			else if("Reject".equalsIgnoreCase(status))
				actnDone = "CD7323";
			else
				actnDone = "CD7324";
			
        	claimStatus = patientService.getClaimStatus(patientId);//Chandana - 8036 - to get the claim status using claim no(patientId)
        	if(claimStatus != null || "".equalsIgnoreCase(claimStatus)) //Chandana - 8036 - if not null then sending the claim status and taking back the next status
        		nextRoleStatus = patientService.getNextStatus(claimStatus,actnDone);
        	if (nextRoleStatus != null && nextRoleStatus.contains("~")) {//Chandana - 8602 - Added if condition and getting the both next action and user role from the workflow status table
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
        	hashMap.put("nextStatus", nextStatus);
        	hashMap.put("userRole", userRole);
        	result = patientService.updateCHOpClaim(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(result);   
        	response.getWriter().write(gsonString);
        	return null;
        }
        //Chandana - 8036 - below for update the NIMS OP claims for PEX
        else if("updatePEXOpClaim".equalsIgnoreCase(lStrActionVal)){
        	String result = null;
        	String status = request.getParameter("status");
        	String opdClaimNo = request.getParameter("opdClaimNo");
        	String remarks = request.getParameter("remarks");
        	String patientId = request.getParameter("patientId");
        	String finalAmt = request.getParameter("finalAmt");
        	HashMap hashMap = new HashMap();
        	
        	hashMap.put("status", status);
        	hashMap.put("opdClaimNo", opdClaimNo);
        	hashMap.put("remarks", remarks);
        	hashMap.put("userId",lStrUserId);
        	hashMap.put("patientId", patientId);
        	hashMap.put("finalAmt", finalAmt);
        	if("Approve".equalsIgnoreCase(status))//Chandana - 8036 - to send the actionDone status to get the next status based on the current status and action done columns 
        		actnDone = "CD7331";
        	else if("Return".equalsIgnoreCase(status))
        		actnDone = "CD7332";
        	claimStatus = patientService.getClaimStatus(patientId);//Chandana - 8036 - to get the claim status using claim no(patientId)
        	if(claimStatus != null || "".equalsIgnoreCase(claimStatus)) //Chandana - 8036 - if not null then sending the claim status and taking back the next status
        		nextRoleStatus = patientService.getNextStatus(claimStatus,actnDone);
        	if (nextRoleStatus != null && nextRoleStatus.contains("~")) {//Chandana - 8602 - Added if condition and getting the both next action and user role from the workflow status table
                String[] parts = nextRoleStatus.split("~");
                nextStatus = parts[0];
                userRole = parts[1];
            }
        	hashMap.put("nextStatus", nextStatus);
        	hashMap.put("userRole", userRole);
        	result = patientService.updatePEXOpClaim(hashMap);
        	Gson gson = new Gson();
        	String gsonString = gson.toJson(result);   
        	response.getWriter().write(gsonString);
        	return null;
        }
		//Chandana -7845- for doc view
        else if("viewInvestigation".equalsIgnoreCase(lStrActionVal)){
			String docPath = request.getParameter("filePath");
			String base64Data = null;
			if(docPath.toLowerCase().endsWith(".pdf"))
				base64Data=patientService.convertPDFToBase64(docPath);
			request.setAttribute("base64DataDoc", base64Data);	
			lStrPageName = "viewDocs";
        }
        //CR 9043 pravalika- view admission document 
        else if ("viewDlhJrnlstDoc".equalsIgnoreCase(lStrActionVal)) {

            String fileName = request.getParameter("fileName");

            if (fileName != null) {

                String baseDir = "C:/storageNAS-TS-Production/DJHS_ADM/";
                String fullPath = baseDir + fileName;

                File file = new File(fullPath);

                if (file.exists()) {

                    if (fileName.toLowerCase().endsWith(".pdf")) {
                        response.setContentType("application/pdf");
                    } else if (fileName.toLowerCase().endsWith(".jpg") ||
                               fileName.toLowerCase().endsWith(".jpeg")) {
                        response.setContentType("image/jpeg");
                    } else if (fileName.toLowerCase().endsWith(".png")) {
                        response.setContentType("image/png");
                    }

                    response.setHeader("Content-Disposition",
                            "inline; filename=\"" + fileName + "\"");

                    FileInputStream f = new FileInputStream(file);
                    OutputStream os = response.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = f.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }

                    f.close();
                    os.close();
                }
            }
            return null;
        }
       else if("getCaseFlag".equalsIgnoreCase(lStrActionVal)){//Chandana - 8598 - To get the flag for locking or not locking cases
    	   String patId = request.getParameter("patientId");
    	   ClaimCases.releaseCase("0");
    	   OutputStream out = response.getOutputStream();
           if(ClaimCases.isAvailable(patId))
                out.write("yes".getBytes());
            else 
                out.write("no".getBytes());
           out.close();
           return null;
       }
		return mapping.findForward(lStrPageName);
	  }
	private int getNoOfPages(int totRows,int noOfRowsPerPage)
	{
		int noOfPages = 0;
		if(totRows>0 && noOfRowsPerPage>0)
		{
			noOfPages = totRows/noOfRowsPerPage;
			if(totRows%noOfRowsPerPage>0)
			{
				noOfPages++;
			}
		}
		return noOfPages;
	}
	private int getStartIndex(int noOfRowsPerPage,int pageNo)
	{
		int startIndex = 0;
		if(noOfRowsPerPage>0 && pageNo>0)
		{
			if(pageNo==1)
			{
				startIndex = 0;
			}
			else if(pageNo>1)
			{
				startIndex = (pageNo-1)*noOfRowsPerPage;
			}
		}
		return startIndex;
	}
	
	
}