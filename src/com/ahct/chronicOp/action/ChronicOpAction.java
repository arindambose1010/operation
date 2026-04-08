package com.ahct.chronicOp.action;
import org.apache.struts.upload.FormFile;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
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
import org.springframework.util.StringUtils;

import com.ahct.claims.util.ClaimCases;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.EhfChronicPatientDtl;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmSeq;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.chronicOp.form.ChronicOPForm;
import com.ahct.chronicOp.service.ChronicOPService;
import com.ahct.chronicOp.vo.ChronicOPVO;
import com.ahct.flagging.vo.FlaggingVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;


public class ChronicOpAction  extends Action{

	
	private final static Logger GLOGGER = Logger.getLogger ( ChronicOpAction.class ) ;
	@SuppressWarnings({ "unchecked", "unused" })
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0);    
		HttpSession session = request.getSession ( false ) ;
		
		
		
        String lStrEmpId = null ;
	    String lStrLocId = null ;
	    String lStrLangId = null ;
	    String lStrUserName = null;
	    String lStrFlagStatus = null;
	    String lStrUserRole = null;
	    String userState=null;
	    String msg=null;
	    List<LabelBean> rolesList = null;
        if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
		    lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
		    Date crtDt = new Date () ;
		    if ( ( session.getAttribute ( "LocID" ) != null ) && !( session.getAttribute ( "LocID" ).equals ( "" ) ) )
		    	lStrLocId = ( String ) session.getAttribute ( "LocID" ) ;
		    if ( ( session.getAttribute ( "LangID" ) != null ) && !( session.getAttribute ( "LangID" ).equals ( "" ) ) )
		    	lStrLangId = ( String ) session.getAttribute ( "LangID" ) ;
		    if ( ( session.getAttribute ( "userName" ) != null ) && !( session.getAttribute ( "userName" ).equals ( "" ) ) )
		    	lStrUserName = ( String ) session.getAttribute ( "userName" ) ;
		    if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
		    	rolesList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
		    if ( ( session.getAttribute ( "UserRole" ) != null ) && !( session.getAttribute ( "UserRole" ).equals ( "" ) ) )
		    	lStrUserRole = ( String ) session.getAttribute ( "UserRole" ) ;
		    if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
		    	userState = ( String ) session.getAttribute ( "userState" ) ;
		    request.setAttribute("userState", userState);
		ChronicOPVO ChronicOpVO=null;
	
		//PreauthVO preauthVO=null;
		ConfigurationService configurationService = 
				(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		ChronicOPService chronicOPService=(ChronicOPService)ServiceFinder.getContext(request).getBean("chronicOPService");
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
		String userName=null;
		String lStrLangID=null;
		String lStrUserId =null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String roleId=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
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
		if(session.getAttribute("userState").toString()!=null)
		{
			schemeId=session.getAttribute("userState").toString();
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
			session.setAttribute("medco", "Y");
			
		}
		else if(lStrgrpList.contains(config.getString("Group.Pex")))
		{
			roleId=config.getString("Group.COEX");
		}else if(lStrgrpList.contains(config.getString("Group.COEX")))
		{
			roleId=config.getString("Group.COEX");
		}
		else if(lStrgrpList.contains(config.getString("EHF.FollowUP.FCX")))
		{
			roleId=config.getString("Group.COEX");
		}
		else if(lStrgrpList.contains(config.getString("Group.COTD")))
		{
			roleId=config.getString("Group.COTD");
		}else if(lStrgrpList.contains(config.getString("Group.COCH")))
		{
			roleId=config.getString("Group.COCH");
		}else if(lStrgrpList.contains(config.getString("GROUP.COACO")))
		{
			roleId=config.getString("GROUP.COACO");
		}else if(lStrgrpList.contains(config.getString("GROUP.COEO")))
		{
			roleId=config.getString("GROUP.COEO");
		}
		else
		{
			roleId=lStrgrpList.get(0);
		}
		String hospId= chronicOPService.getHospitalID(session.getAttribute("UserID").toString(),roleId);
		String stateHdrId =config.getString("IPOP.StateHDRID");
		String statePrntId =config.getString("IPOP.StatePrntId");
		List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
		session.setAttribute("stateList", stateList);
		String distHdrId =config.getString("IPOP.DistrictHDRID");
		String distParntId = config.getString("IPOP.APCode");
		

		ChronicOPForm chronicOpForm=(ChronicOPForm)form;

		String apStateId = config.getString("IPOP.APCode");
		String tgStateId = config.getString("IPOP.TGCode");
		
		String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
		String lStrMandalHdrId = config.getString("IPOP.MandalHDRID");
		String lStrMunicipalHdrId = config.getString("IPOP.MunicipalityHDRID");
		String lStrVillageHdrId = config.getString("IPOP.VillageHDRID");
		String lStrMplVillageHdrId = config.getString("IPOP.MunicipalVillageHDRID");

		String lStrActionVal = request.getParameter("actionFlag");

		String lStrPageName = null; 
		

		
		
		if ("openChronicPatRegForm".equalsIgnoreCase(lStrActionVal))
		{

			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			session.setAttribute("castesList",castesList);

			List<LabelBean> relationList=chronicOPService.getRelations();
			session.setAttribute("relationList",relationList);

			List<LabelBean> maritalStatusList=commonService.getComboDetails(config.getString("IPOP.MaritalStatusCMBHDRID"));
			session.setAttribute("maritalStatusList",maritalStatusList);

			List<LabelBean> hospitalList=chronicOPService.getHospital(lStrUserId,roleId);
			session.setAttribute("hospitalList",hospitalList);

			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);
			try{
				chronicOpForm.setDtTime(serverDt);
				chronicOpForm.setDisableFlag(config.getString("YFlag"));
			}
			catch(Exception e){e.printStackTrace();}
			String hospStatus= chronicOPService.getHospActiveStatus(lStrUserId,roleId);
			if(config.getString("inactive_status")!=null && hospStatus!=null 
					&& config.getString("inactive_status").contains("~"+hospStatus+"~"))
			{
				request.setAttribute("inActiveStatusMsg", config.getString("msg_"+hospStatus));
				request.setAttribute("inActiveStatusFlag", "Y");
			}

			lStrPageName = "chronicOpReg"; 
		}
		
		if("retrieveCardDetails".equalsIgnoreCase(lStrActionVal))
		{
			ChronicOPVO chronicOPVO=new ChronicOPVO();
			String cardNo=request.getParameter("cardNo");
			chronicOPVO.setCardNo(cardNo);
			chronicOPVO.setCardType(chronicOpForm.getCard_type());
			chronicOPVO.setSchemeId(schemeId);
			chronicOPVO=chronicOPService.retrieveCardDetails(chronicOPVO);
			/*chronicOpForm.setTelephonicId(chronicOpForm.getTelephonicId());
			chronicOpForm.setTelephonicReg(chronicOpForm.getTelephonicReg());*/
			
			if(chronicOPVO!=null && !(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(chronicOPVO.getMsg()))
								 && !(config.getString("invalid_state_remarks").equalsIgnoreCase(chronicOPVO.getMsg())))
			{
					if(chronicOPVO.getVillageCode()!=null )
					{
						LabelBean labelBeanVillage=new LabelBean();
						labelBeanVillage.setID(chronicOPVO.getVillageCode());
						labelBeanVillage=commonService.getNewLocations(labelBeanVillage);
						if(labelBeanVillage!=null)
						{
							chronicOPVO.setDistrictCode(labelBeanVillage.getNEW_DIST());					
							chronicOPVO.setMandalCode(labelBeanVillage.getNEW_MAND());
							chronicOPVO.setVillageCode(labelBeanVillage.getNEW_VILLAGE());
							
						}
					}
				
				chronicOpForm.setCardIssueDt(chronicOPVO.getCardIssueDt());
				chronicOpForm.setEmpCode(chronicOPVO.getEmpCode());
				chronicOpForm.setCardNo(cardNo);
				chronicOpForm.setCard_type(chronicOpForm.getCard_type());
				chronicOpForm.setHead(chronicOpForm.getHead());
				chronicOpForm.setDisableFlag(config.getString("NFlag"));
				chronicOpForm.setAddrEnable(config.getString("YFlag"));
				String dob[]=chronicOPVO.getDateOfBirth().substring(0,10).split("-");
				String finalDob=dob[2]+"-"+dob[1]+"-"+dob[0];

				chronicOpForm.setErrMsg("");
				chronicOpForm.setDateOfBirth(finalDob);
				chronicOpForm.setGender(chronicOPVO.getGender());
				chronicOpForm.setFname(chronicOPVO.getFirstName());
				chronicOpForm.setRelation(chronicOPVO.getRelation());
				chronicOpForm.setCaste(chronicOPVO.getCaste());
				chronicOpForm.setContactno(chronicOPVO.getContactNo());
				chronicOpForm.setMaritalStatus(chronicOPVO.getMaritalStatus());
				chronicOpForm.setOccupation(chronicOPVO.getOccupationCd());
				chronicOpForm.setSlab(chronicOPVO.getSlab());
				chronicOpForm.setScheme(chronicOPVO.getSchemeId());
				chronicOpForm.setPostDist(chronicOPVO.getPostDist());
				chronicOpForm.setDeptHod(chronicOPVO.getDeptHod());
				chronicOpForm.setDdoCode(chronicOPVO.getPostDdoCode());
				chronicOpForm.setStoCode(chronicOPVO.getDdoOffUnit());
				request.setAttribute("scheme", chronicOPVO.getSchemeId());
				if(chronicOPVO.geteMailId()!=null && !chronicOPVO.geteMailId().equals(""))
				{
					chronicOpForm.seteMailId(chronicOPVO.geteMailId());
				}
				if(chronicOPVO.getPhoto()!=null)
				{
					try
					{
						File photo = new File(chronicOPVO.getPhoto());
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
						chronicOpForm.setPhotoUrl(chronicOPVO.getPhoto());
					}
					catch(Exception e)
					{
						GLOGGER.error ( "Exception occured while photo is not available in the path specified in ChronicOpAction." +e.getMessage()) ;
					}
				}
				chronicOpForm.setHno(chronicOPVO.getAddr1());
				chronicOpForm.setStreet(chronicOPVO.getAddr2());
				List<LabelBean> districtList=null;
				if(chronicOPVO.getState()!=null)
				{
					chronicOpForm.setState(chronicOPVO.getState());
					districtList=commonService.getAsrimLocations(distHdrId, chronicOPVO.getState());
				}
				request.setAttribute("districtList",districtList);
				chronicOpForm.setDistrict(chronicOPVO.getDistrictCode());
				chronicOpForm.setMdl_mcl(chronicOPVO.getMdl_mpl());
				if(chronicOPVO.getMdl_mpl()!=null && chronicOPVO.getMdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> mdlList=commonService.getAsrimLocations(lStrMandalHdrId, chronicOPVO.getDistrictCode());
					request.setAttribute("mdlList", mdlList);
					chronicOpForm.setMandal(chronicOPVO.getMandalCode());

					List<LabelBean> villList=commonService.getAsrimLocations(lStrVillageHdrId, chronicOPVO.getMandalCode());
					request.setAttribute("villList", villList);
				}
				else if(chronicOPVO.getMdl_mpl()!=null && chronicOPVO.getMdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> mplList=commonService.getAsrimLocations(lStrMunicipalHdrId, chronicOPVO.getDistrictCode());
					request.setAttribute("mplList", mplList);
					chronicOpForm.setMunicipality(chronicOPVO.getMandalCode());

					List<LabelBean> villList=commonService.getAsrimLocations(lStrMplVillageHdrId, chronicOPVO.getMandalCode());
					request.setAttribute("villList", villList);
				}
				chronicOpForm.setVillage(chronicOPVO.getVillageCode());
				chronicOpForm.setDtTime(serverDt);
				//GLOGGER.info ( "Setting  Card Details to ChronicOpForm in ChronicOpAction." +"  Mandal Code is "+chronicOPVO.getMandalCode()+"  and village Code is"+chronicOPVO.getVillageCode()) ;
				
				
			}
			else if(chronicOPVO!=null &&(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(chronicOPVO.getMsg())
									  || config.getString("invalid_state_remarks").equalsIgnoreCase(chronicOPVO.getMsg())))
			{
				chronicOpForm.setDisableFlag(config.getString("YFlag"));
				chronicOpForm.setCardNo("");
				chronicOpForm.setCard_type("");
				chronicOpForm.setHead("");
				chronicOpForm.setDtTime(serverDt);
				chronicOpForm.setErrMsg(config.getString("inactivate_card_death_remarks"));
				if(chronicOPVO.getMsg()!=null && config.getString("invalid_state_remarks").equalsIgnoreCase(chronicOPVO.getMsg()))
					chronicOpForm.setErrMsg(config.getString("invalid_state_remarks"));
			}
			else
			{
				chronicOpForm.setDisableFlag(config.getString("YFlag"));
				chronicOpForm.setCardNo("");
				chronicOpForm.setCard_type("");
				chronicOpForm.setHead("");
				chronicOpForm.setDtTime(serverDt);
				chronicOpForm.setErrMsg(config.getString("err.InvalidCardNo"));
			}
			

			/*if(request.getParameter("SelOper")!=null && request.getParameter("SelOper").equalsIgnoreCase("TELE"))
				lStrPageName = "telephonicPatientEntry";
			else*/
				lStrPageName = "chronicOpReg";
		}
		
		
		if ("registerPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			ChronicOPVO chronicOPVO=null;
			System.out.println(chronicOpForm.getEmpCode());
			String wapNo=request.getParameter("wapNo");
			String familyNo=request.getParameter("familyNo");
			chronicOPVO=new ChronicOPVO();
			EhfmSeq ehfmSeqPatient = null;
			String conHospId=null;
			String liNextVal="";
			String userId=null;
			int phaseRenewal=0;
			Date date = new Date();
			String crtDate=sdfw.format(date);
		    crtDt = sdfw.parse((sdfw.format(date)));
			userId=session.getAttribute("EmpID").toString(); 
			//GLOGGER.info("Card No"+wapNo+"/"+familyNo) ;
			
			conHospId=chronicOpForm.getHospitalId().substring(3);
			
			if(chronicOpForm.getScheme()!=null && config.getString("Scheme.ap").equalsIgnoreCase(chronicOpForm.getScheme()))
			{
				liNextVal = "AP"+chronicOPService.getSequence("EHF_AP_CHRONIC_ID");
			}
			else if(chronicOpForm.getScheme()!=null && config.getString("Scheme.tg").equalsIgnoreCase(chronicOpForm.getScheme()))
			{
				liNextVal = "TG"+chronicOPService.getSequence("EHF_TG_CHRONIC_ID");
			}
			else if(chronicOpForm.getScheme()!=null && "1".equalsIgnoreCase(chronicOpForm.getScheme()))
			{
				liNextVal = chronicOPService.getSequence("EHF_CHRONIC_ID");
			}
			//liNextVal = chronicOPService.getSequence("PATIENT_ID");
			liNextVal=config.getString("CASE.CHRONIC")+config.getString("SLASH_VALUE")+conHospId+
													   config.getString("SLASH_VALUE")+liNextVal;
			chronicOPVO.setPatientId(liNextVal);
			chronicOPVO.setProcessInstanceId("1");
			chronicOPVO.setCrtDt(crtDate);
			chronicOPVO.setCrtUsr(userId);
			chronicOPVO.setRationCard((wapNo+"/"+familyNo).toUpperCase());
			chronicOPVO.setCardType(chronicOpForm.getCard_type());
			chronicOPVO.setCardIssueDt(chronicOpForm.getCardIssueDt());
			chronicOPVO.setDateOfBirth(chronicOpForm.getDateOfBirth());
			chronicOPVO.setFirstName(chronicOpForm.getFname());
			chronicOPVO.setGender(chronicOpForm.getGender());
			if(!familyNo.equalsIgnoreCase("01") && chronicOpForm.getHead()==null)
			{
				chronicOPVO.setFamilyHead(config.getString("NFlag"));
			}
			else
			{
				chronicOPVO.setFamilyHead(config.getString("YFlag"));
				chronicOPVO.setChild_yn(config.getString("NFlag"));
			}
			String[] age=chronicOpForm.getAgeString().split("~");
			chronicOPVO.setEmpCode(chronicOpForm.getEmpCode());
			chronicOPVO.setAge(age[0]);
			chronicOPVO.setAgeMonths(age[1]);
			chronicOPVO.setAgeDays(age[2]);
			chronicOPVO.setRelation(chronicOpForm.getRelation());
			chronicOPVO.setCaste(chronicOpForm.getCaste());
			chronicOPVO.setMaritalStatus(chronicOpForm.getMaritalStatus());
			chronicOPVO.setOccupationCd(chronicOpForm.getOccupation());
			chronicOPVO.setContactNo(chronicOpForm.getContactno());
			chronicOPVO.setSlab(chronicOpForm.getSlab());
			chronicOPVO.seteMailId(chronicOpForm.geteMailId());
			chronicOPVO.setAddr1(chronicOpForm.getHno());
			chronicOPVO.setAddr2(chronicOpForm.getStreet());
			chronicOPVO.setVillageCode(chronicOpForm.getVillage());
			chronicOPVO.setState(chronicOpForm.getState());
			chronicOPVO.setDistrictCode(chronicOpForm.getDistrict());
			
			chronicOPVO.setDeptHod(chronicOpForm.getDeptHod());
			chronicOPVO.setPostDist(chronicOpForm.getPostDist());
			chronicOPVO.setDdoOffUnit(chronicOpForm.getStoCode());
			chronicOPVO.setPostDdoCode(chronicOpForm.getDdoCode());
			
			chronicOPVO.setMdl_mpl(chronicOpForm.getMdl_mcl());
			if(chronicOpForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
			{
				chronicOPVO.setMandalCode(chronicOpForm.getMandal());
			}
			else if(chronicOpForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
			{
				chronicOPVO.setMandalCode(chronicOpForm.getMunicipality());
			}
			chronicOPVO.setPincode(chronicOpForm.getPin());
			//chronicOPVO.setSrcRegistration(config.getString("PatientIPOP.Source"));
			//start sameaddress check
			if(chronicOpForm.getCommCheck()==null)
			{
				chronicOPVO.setPermAddr1(chronicOpForm.getComm_hno());
				chronicOPVO.setPermAddr2(chronicOpForm.getComm_street());
				chronicOPVO.setC_pin_code(chronicOpForm.getComm_pin());
				chronicOPVO.setC_state(chronicOpForm.getComm_state());
				chronicOPVO.setC_district_code(chronicOpForm.getComm_dist());
				chronicOPVO.setC_mdl_mpl(chronicOpForm.getComm_mdl_mcl());
				if(chronicOpForm.getComm_mdl_mcl().equalsIgnoreCase("Mdl"))
				{
					chronicOPVO.setC_mandal_code(chronicOpForm.getComm_mandal());
				}
				else if(chronicOpForm.getComm_mdl_mcl().equalsIgnoreCase("Mpl"))
				{
					chronicOPVO.setC_mandal_code(chronicOpForm.getComm_municipality());
				}

				chronicOPVO.setC_village_code(chronicOpForm.getComm_village());	
			}
			else
			{
				chronicOPVO.setPermAddr1(chronicOpForm.getHno());
				chronicOPVO.setPermAddr2(chronicOpForm.getStreet());
				chronicOPVO.setC_pin_code(chronicOpForm.getPin());
				chronicOPVO.setC_state(chronicOpForm.getState());
				chronicOPVO.setC_district_code(chronicOpForm.getDistrict());
				chronicOPVO.setC_mdl_mpl(chronicOpForm.getMdl_mcl());
				if(chronicOpForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
				{
					chronicOPVO.setC_mandal_code(chronicOpForm.getMandal());
				}
				else if(chronicOpForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
				{
					chronicOPVO.setC_mandal_code(chronicOpForm.getMunicipality());
				}
				chronicOPVO.setC_village_code(chronicOpForm.getVillage());	
			}
			//end sameaddress check
			//chronicOPVO.setPatient_ipop(config.getString("PatientIPOP.RegisterStatus"));

			//phaseRenewal=commonService.getPhaseId(chronicOpForm.getDistrict());
			phaseRenewal=0;
			//chronicOPVO.setPhaseId(phaseRenewal+"");
			//chronicOPVO.setRenewal(config.getString("PatientIPOP.Renewal"));
			//chronicOPVO.setSchemeId(config.getString("PatientIPOP.SchemeId"));
			chronicOPVO.setSchemeId(chronicOpForm.getScheme());
			/*chronicOPVO.setCid(config.getString("PatientIPOP.Cid"));
			if(chronicOpForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Emp")))
				chronicOPVO.setEmployeeRenewal(config.getString("PatientIPOP.Renewal"));
			else if(chronicOpForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Pen")))
				chronicOPVO.setPensionerRenewal(config.getString("PatientIPOP.Renewal"));*/
			chronicOPVO.setRegHospId(chronicOpForm.getHospitalId());
			chronicOPVO.setRegHospDt(crtDate);
			/*if(!chronicOpForm.getTelephonicId().equals(""))
			{
				chronicOPVO.setTelephonicId(chronicOpForm.getTelephonicId());
				chronicOPVO.setRegState("Telephonic Registration");
			}*/
			try{
				int rowsInserted=chronicOPService.registerPatient(chronicOPVO);
				if(rowsInserted==0)
				{

					GLOGGER.info("Patient cannot be registered");
					lStrPageName="failure";
				}
				else
				{
					chronicOpForm.setPatientNo(liNextVal);             
					if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					{
						if(chronicOPVO.getContactNo()!=null && !chronicOPVO.getContactNo().equals(""))
						{
							smsNextVal = chronicOPService.getSequence("PATIENT_SMS_SNO");
							String lStrResultMsg=null;
							PatientSmsVO patientSmsVO=new PatientSmsVO();
							patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
							patientSmsVO.setPhoneNo(chronicOPVO.getContactNo());
							patientSmsVO.setSmsText("Dear "+chronicOPVO.getFirstName().trim()+" , You have been registered as a Chronic OutPatient in "+chronicOPService.getHospName(chronicOpForm.getHospitalId())+" with chronic ID : "+liNextVal+" and it is pending with doctor for diagnosis");
							patientSmsVO.setEmpCode(chronicOPVO.getEmpCode());
							patientSmsVO.setEmpName(chronicOPVO.getFirstName().trim());
							patientSmsVO.setCrtUsr(chronicOPVO.getCrtUsr());
							patientSmsVO.setCrtDt(crtDt);
							patientSmsVO.setSmsAction("Chronic Patient Registered");
							patientSmsVO.setPatientId(chronicOPVO.getPatientId());
							lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
						}
					}
					if (config.getBoolean("EmailRequired")) 
					{
						String mailId=null;
						if(chronicOpForm.geteMailId()!=null && !chronicOpForm.geteMailId().equals(""))
						{
							mailId=chronicOpForm.geteMailId();
							String[] emailToArray = {mailId};
							EmailVO emailVO = new EmailVO();
							emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
							emailVO.setTemplateName(config.getString("EHFChronicPatientTemplateName"));
							emailVO.setFromEmail(config.getString("emailFrom"));
							emailVO.setToEmail(emailToArray);
							emailVO.setSubject(config.getString("patientEmailSubject"));
							Map<String, String> model = new HashMap<String, String>();
							model.put("chronicId",liNextVal);
							model.put("patientName", chronicOPVO.getFirstName().trim());
							model.put("registeredHosp", chronicOPService.getHospName(chronicOpForm.getHospitalId()));
							model.put("status", "Chronic Patient Registered");
							model.put("statusDate",crtDate);
							commonService.generateMail(emailVO, model);
						}
					}
					lStrPageName="chronicPatient";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//GLOGGER.error ( "Exception occured while registering patient in ChronicOpAction." +e.getMessage()) ;
			}       				
		}
		

		if(lStrActionVal.equalsIgnoreCase("viewChronicOPPatients")){
			List<ChronicOPVO> chrList1=null;
			List<ChronicOPVO> chrList=null;
			int  totalRecords=1;
			int pageNo=1;
			int  showPage=1;;
			int rowsPerPage=10;
			int pages=1;
			int startIndex=1;
			int endIndex=5;
			int startPage=1;
			int endPage=10;
			
			if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search").toString())) &&  "search".equalsIgnoreCase(request.getParameter("search").toString()) ){
				
			 chrList1=chronicOPService.searchChronicOPPatients(request.getParameter("chronicId"), request.getParameter("PatName"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDt"),request.getParameter("toDate"),request.getParameter("userState"),hospId,roleId);
				
				if(chrList1!=null && chrList1.size()>0){
					chronicOpForm.setChronincOPPatList(chrList1);
					
				}
				
			}else{
				
				 chrList=chronicOPService.getChronicOPPatients(hospId,roleId,userState);
				if(chrList!=null && chrList.size()>0){
									
					chronicOpForm.setChronincOPPatList(chrList);
					
				}
						
				
				
			}
			if(chronicOpForm.getChronincOPPatList()!=null && chronicOpForm.getChronincOPPatList().size()>0){
				
				totalRecords=chronicOpForm.getChronincOPPatList().size();
				
			}
         if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
				
				rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
			}
         
         if(rowsPerPage==0){
        	 
        	 rowsPerPage=10;
         }
			if(totalRecords>rowsPerPage){
				
				
				if((totalRecords%rowsPerPage)==0){
					pages=totalRecords/rowsPerPage;
					
				}else{
					
					pages=(totalRecords/rowsPerPage)+1;
				}
				
			}else{
				
				pages=1;
			}
			
			if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
				
				
				 if(pages>10 && showPage<=10){
						
						request.setAttribute("next", "next");
						chronicOpForm.setNext("next");
						
					}
		              if(pages>10 && (showPage-10)>1){
						
						request.setAttribute("prev", "prev");
						chronicOpForm.setPrev("prev");
						
		              }
				
				
				
			}
			
      
			if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
				
					String str=request.getParameter("pageNo");				
				showPage=Integer.parseInt(str);
				
				startIndex=((showPage-1)*rowsPerPage)+1;
				
				if((showPage*rowsPerPage)<totalRecords){
					
					
					endIndex=showPage*rowsPerPage;
					
				}else{
					
					endIndex=totalRecords;
					
				}
				
				
	           int x=chronicOpForm.getStartPage();
				
				int y=chronicOpForm.getEndPage();
				
				if(y>pages){
				y=pages;
				}
				
				if(pages>10 && pages>y){
					
					
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
				
				if(pages>10 && x>10){
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
				}
				
				
				startPage=x;
				endPage=y;
				
				
			}
			
			if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
				
				int x=chronicOpForm.getStartPage();
				
				int y=chronicOpForm.getEndPage();
				if(x>=10 && pages>10){
					
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
				
				if(x-10>10 && pages>10){
					
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
				}
				
				if(showPage==1){
					
					if(totalRecords>rowsPerPage){
						
						startIndex=1;
						endIndex=rowsPerPage;
					}else{
					
						startIndex=(showPage-1)*rowsPerPage+1;
						if((startIndex+rowsPerPage)<totalRecords){
							
							endIndex=startIndex+rowsPerPage;
						}else{
							
							endIndex=totalRecords;
						}
						
						
					}
				}
				startPage=x-10;
				endPage=x-1;
				
				
				chronicOpForm.setStartPage(startPage);
				chronicOpForm.setEndPage(endPage);
				
	startIndex=((showPage-1)*rowsPerPage)+1;
				
				if((showPage*rowsPerPage)<totalRecords){
					
					
					endIndex=showPage*rowsPerPage;
					
				}else{
					
					endIndex=totalRecords;
					
				}
			}
			
			
			if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
				
                int x=chronicOpForm.getStartPage();
				
				int y=chronicOpForm.getEndPage();
			
				
				startPage=y+1;
				endPage=y+10;
				if(endPage>pages){
					
					endPage=pages;
				}
				
				if(pages>startPage+10){
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
				
				if(startPage-10>=1){
					
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
				}
				chronicOpForm.setStartPage(startPage);
				chronicOpForm.setEndPage(endPage);
				showPage=startPage;
              startIndex=((showPage-1)*rowsPerPage)+1;
				
				if((showPage*rowsPerPage)<totalRecords){
					
					
					endIndex=showPage*rowsPerPage;
					
				}else{
					
					endIndex=totalRecords;
					
				}
			
				
				
				
			}
			
			if(showPage==1){
				
				if(totalRecords>rowsPerPage){
					
					startIndex=1;
					endIndex=rowsPerPage;
				}else{
				
					startIndex=(showPage-1)*rowsPerPage+1;
					if((startIndex+rowsPerPage)<totalRecords){
						
						endIndex=startIndex+rowsPerPage;
					}else{
						
						endIndex=totalRecords;
					}
					
					
				}
			}
			startIndex=startIndex-1;
			List<ChronicOPVO> chrList3=new ArrayList<ChronicOPVO>();
			if( (request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search"))) && request.getParameter("search").equalsIgnoreCase("search")){
				
				if(chrList1!=null && chrList1.size()>0){
				for(int i=startIndex;i<endIndex;i++){
					
					ChronicOPVO vo=new ChronicOPVO();
					vo.setChronicSubID(chrList1.get(i).getChronicSubID());
					vo.setChronicID(chrList1.get(i).getChronicID());
					vo.setName(chrList1.get(i).getName());
					vo.setEmployeeNo(chrList1.get(i).getEmployeeNo());
					vo.setAge(chrList1.get(i).getAge());
					vo.setDistrict(chrList1.get(i).getDistrict());
					vo.setGender(chrList1.get(i).getGender());
					vo.setRegistrationDate(chrList1.get(i).getRegistrationDate());
					chrList3.add(vo);
					
				}
				
				}
			}else{
				
				if(chrList!=null && chrList.size()>0){
                    for(int j=startIndex;j<endIndex;j++){
					
                    	ChronicOPVO vo=new ChronicOPVO();
                    	vo.setChronicSubID(chrList.get(j).getChronicSubID());
					vo.setChronicID(chrList.get(j).getChronicID());
					
					vo.setName(chrList.get(j).getName());
					vo.setEmployeeNo(chrList.get(j).getEmployeeNo());
					vo.setAge(chrList.get(j).getAge());
					vo.setDistrict(chrList.get(j).getDistrict());
					vo.setGender(chrList.get(j).getGender());
					vo.setRegistrationDate(chrList.get(j).getRegistrationDate());
					chrList3.add(vo);
					
				}
				}
				
			}
					
			chronicOpForm.setChronincOPPatList(chrList3);
			request.setAttribute("liPageNo",showPage);
			if(chrList3.size()==0){
				request.setAttribute("startIndex", 0);
			}else{
				
				request.setAttribute("startIndex", startIndex+1);
			}
			if(chrList3.size()==0){
				request.setAttribute("endIndex", 0);
			}else{
				request.setAttribute("endIndex", endIndex);
				
			}
			
			request.setAttribute("rowsPerPage", rowsPerPage);
			request.setAttribute("pages", pages);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("endPage", endPage);
			if(chrList3.size()==0){
				
				request.setAttribute("totalRecords", 0);
			}else{
				request.setAttribute("totalRecords", totalRecords);
			}
			
		
			
			
			lStrPageName="viewChronicOpPatients";
			
		}

		
		


		
		if ("getLocations".equalsIgnoreCase(lStrActionVal))
		{
			String locHdrId=request.getParameter("lStrHdrId");
			String stateId=request.getParameter("stateId");
			String distId=request.getParameter("distId");
			String mandalId=request.getParameter("mandalId");
			String villageId=request.getParameter("villageId");
			try {
				List<LabelBean> cmbHdrList=null;
				List<String> locationsList = new ArrayList<String>();
				if(stateId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, stateId);
					request.setAttribute("districtList",cmbHdrList);
				}
				else if(distId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, distId);
					request.setAttribute("mandalList",cmbHdrList);
				}
				else if(mandalId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, mandalId);
					request.setAttribute("villageList",cmbHdrList);
				}
				else if(villageId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, villageId);
					request.setAttribute("hamletList",cmbHdrList);
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
				e.printStackTrace();
			}
			lStrPageName="ajaxResult";
		}
		
		if ("printPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			String patientId=null;
			String lStrCaseId = request.getParameter("caseId");
			EhfChronicPatientDtl ehfChronicPatientDtl=null;
			patientId=request.getParameter("patientId");
			chronicOpForm.setPatientNo(patientId);
			// request.setAttribute("patientId",patientId);
			ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(patientId);
			//storing patcrtdt
			session.setAttribute("patCrtdt",ehfChronicPatientDtl.getCrtDt());
			chronicOpForm.setCardNo(ehfChronicPatientDtl.getCardNo());
			chronicOpForm.setFname(ehfChronicPatientDtl.getName());
			if(ehfChronicPatientDtl.getCardIssueDt()!=null)
			{
				chronicOpForm.setCardIssueDt(sdf.format(ehfChronicPatientDtl.getCardIssueDt()));
			}
			else
				chronicOpForm.setCardIssueDt("NA");
			if(ehfChronicPatientDtl.getGender()!=null && "M".equalsIgnoreCase(ehfChronicPatientDtl.getGender()))
			{
				chronicOpForm.setGender("Male");
			}
			else if(ehfChronicPatientDtl.getGender()!=null && "F".equalsIgnoreCase(ehfChronicPatientDtl.getGender()))
			{
				chronicOpForm.setGender("Female");
			}
			if(ehfChronicPatientDtl.getEnrollDob()!=null)
			{
				chronicOpForm.setDateOfBirth(sdf.format(ehfChronicPatientDtl.getEnrollDob()));
			}
			if(ehfChronicPatientDtl.getAge()!=null)
			{
				chronicOpForm.setYears(ehfChronicPatientDtl.getAge().toString());
			}
			if(ehfChronicPatientDtl.getAgeMonths()!=null)
			{
				chronicOpForm.setMonths(ehfChronicPatientDtl.getAgeMonths().toString());
			}
			if(ehfChronicPatientDtl.getAgeDays()!=null)
			{
				chronicOpForm.setDays(ehfChronicPatientDtl.getAgeDays().toString());
			}
			String relationId=ehfChronicPatientDtl.getRelation();
			String relationName=chronicOPService.getRelationName(relationId);
			chronicOpForm.setRelation(relationName);
			String casteId=ehfChronicPatientDtl.getCaste();
			if(casteId!=null && !casteId.equals(""))
			{
				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
				chronicOpForm.setCaste(casteName);
			}
			if(ehfChronicPatientDtl.getContactNo()!=null && !ehfChronicPatientDtl.getContactNo().equals(""))
			{
				chronicOpForm.setContactno(ehfChronicPatientDtl.getContactNo().toString());
			}
			/*String occName = patientService.getOccupationName(ehfPatient.getOccupationCd());
        	if(occName!=null && !occName.equalsIgnoreCase(""))*/
			chronicOpForm.setOccupation(ehfChronicPatientDtl.getOccupationCd());
			chronicOpForm.setScheme(ehfChronicPatientDtl.getSchemeId());
			/*else
        	patientForm.setOccupation("NA");*/
			//Setting slab
			String slabType=null;
			String slab=null;
			if(ehfChronicPatientDtl.getSlab()!=null)
			{
				slabType=ehfChronicPatientDtl.getSlab();
			}
			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.SemiPrivateWard");
			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.PrivateWard");
			chronicOpForm.setSlab(slab);
			//End Of Slab
			chronicOpForm.setHno(ehfChronicPatientDtl.getHouseNo());
			chronicOpForm.setStreet(ehfChronicPatientDtl.getStreet());
			chronicOpForm.setState(chronicOPService.getLocationName(ehfChronicPatientDtl.getState()));
			String distCode=ehfChronicPatientDtl.getDistrictCode();
			String distName=chronicOPService.getLocationName(distCode);
			chronicOpForm.setDistrict(distName);
			String mandalCode=ehfChronicPatientDtl.getMandalCode();
			String mandalName=chronicOPService.getLocationName(mandalCode);
			chronicOpForm.setMandal(mandalName);
			String villageCode=ehfChronicPatientDtl.getVillageCode();
			String villageName=chronicOPService.getLocationName(villageCode);
			chronicOpForm.setVillage(villageName);
			if(ehfChronicPatientDtl.getPinCode()!=null && !ehfChronicPatientDtl.getPinCode().equalsIgnoreCase(""))
				chronicOpForm.setPin(ehfChronicPatientDtl.getPinCode());
			else
				chronicOpForm.setPin("NA");
			//Setting Communication Address
			chronicOpForm.setComm_hno(ehfChronicPatientDtl.getcHouseNo());
			chronicOpForm.setComm_street(ehfChronicPatientDtl.getcStreet());
			chronicOpForm.setComm_state(chronicOPService.getLocationName(ehfChronicPatientDtl.getcState()));
			chronicOpForm.setComm_dist(chronicOPService.getLocationName(ehfChronicPatientDtl.getcDistrictCode()));
			chronicOpForm.setComm_mandal(chronicOPService.getLocationName(ehfChronicPatientDtl.getcMandalCode()));
			chronicOpForm.setComm_village(chronicOPService.getLocationName(ehfChronicPatientDtl.getcVillageCode()));
			if(ehfChronicPatientDtl.getcPinCode()!=null && !ehfChronicPatientDtl.getcPinCode().equalsIgnoreCase(""))
				chronicOpForm.setComm_pin(ehfChronicPatientDtl.getcPinCode());
			else
				chronicOpForm.setComm_pin("NA");
			chronicOpForm.setDtTime(sdfw.format(ehfChronicPatientDtl.getRegHospDate()));
			String photoUrl=chronicOPService.getPatientPhoto(ehfChronicPatientDtl.getCardNo());
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
					chronicOpForm.setPhotoUrl(photoUrl);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					//GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Medco in ChronicOPAction." +e.getMessage()) ;
				}
			}
			String hospCode=ehfChronicPatientDtl.getRegHospId();
			String hospName=chronicOPService.getHospName(hospCode);
			chronicOpForm.setHospitalId(hospCode);
			request.setAttribute("hospitalId",hospCode);
			chronicOpForm.setHospitalName(hospName);

			/*if(ehfPatient.getIntimationId()!=null)
			{
				chronicOpForm.setTelephonicId(ehfPatient.getIntimationId());
				chronicOpForm.setTelephonicReg("Yes");
				PatientVO patientVo = new PatientVO();
				patientVo.setTelephonicId(ehfPatient.getIntimationId());
				PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);

				patientForm.setTherapyType(patientVO1.getAsriCatCode());
				patientForm.setTherapyCategory(patientVO1.getICDcatName());
				patientForm.setTherapy(patientVO1.getTherapyCatId());
			}
			*/
			String pageType="";
			if(request.getParameter("pageType")!=null && !request.getParameter("pageType").equals(""))
			{
				pageType=request.getParameter("pageType");
				if(pageType.equalsIgnoreCase("print"))
					lStrPageName="chronicPrintDetails";
			}	
		}
		
		if(lStrActionVal.equalsIgnoreCase("COPatientsCSV")){
			List<ChronicOPVO> chrList1=null;
			List<ChronicOPVO> chrList=null;
			
			
			String lStrDirPath=config.getString("mapPath");
			 System.out.println(lStrDirPath);
	         String lStrFileName=config.getString("ChronicPatientTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
	         System.out.println(lStrFileName);
	         String lStrFileName1=lStrDirPath+"/"+lStrFileName; 
	         System.out.println(lStrFileName1);
	         
	         if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search").toString())) &&  "search".equalsIgnoreCase(request.getParameter("search").toString()) ){
					
				 chrList1=chronicOPService.searchChronicOPPatients(request.getParameter("chronicId"), request.getParameter("PatName"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDt"),request.getParameter("toDate"),request.getParameter("userState"),hospId,roleId);
					
					if(chrList1!=null && chrList1.size()>0){
						chronicOpForm.setChronicCSVList(chrList1);
						
					}
					
				}else{
					
					 chrList=chronicOPService.getChronicOPPatients(hospId,roleId,userState);
					if(chrList!=null && chrList.size()>0){
										
						chronicOpForm.setChronicCSVList(chrList);
						
					}
					}
			     //Creates file in EHFTemp folder of client machine 
		         File lcsvfile = createFile(lStrDirPath,lStrFileName1);	         
		         char separator = '^';
		         StringBuilder line = new StringBuilder();   
			
		         line.append("ChronicId");
                 line.append(separator);
                 line.append("Employee No");
                 line.append(separator);
                 line.append("Patient Name");
                 line.append(separator);
                 line.append("District");
                 line.append(separator);
                 line.append("Gender");
                 line.append(separator);
                 line.append("Age");
                 line.append(separator);
                 line.append("Registration Date");
                 line.append("\n");
                 line.append("\n");
			
			
			
			if(chronicOpForm.getChronicCSVList()!=null && chronicOpForm.getChronicCSVList().size()>0){
				
				
				for(int i=0;i<chronicOpForm.getChronicCSVList().size();i++){
					  
					
					   
					  line.append(chronicOpForm.getChronicCSVList().get(i).getChronicID());
                      line.append(separator);
                      line.append(chronicOpForm.getChronicCSVList().get(i).getEmployeeNo());
                      line.append(separator);
                      line.append(chronicOpForm.getChronicCSVList().get(i).getName());
                      line.append(separator);
                      line.append(chronicOpForm.getChronicCSVList().get(i).getDistrict());
                      line.append(separator);
                      line.append(chronicOpForm.getChronicCSVList().get(i).getGender());
                      line.append(separator);
                      line.append(chronicOpForm.getChronicCSVList().get(i).getAge());
                      line.append(separator);
                      line.append(chronicOpForm.getChronicCSVList().get(i).getRegistrationDate());
                      line.append("\n");
					
					
					
					
				}
				
				request.setAttribute("File", line.toString().getBytes());    
			    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
			    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
			    response.setCharacterEncoding("UTF-8");
			    return mapping.findForward("openFile");
				
				
			}else{
				
				lStrPageName="viewChronicOpPatients";
				
				
			}
				}
       if(lStrActionVal.equalsIgnoreCase("COClaimsCSV")){
			
			
    	   
    	   List<ChronicOPVO> chrList1=null;
			List<ChronicOPVO> chrList=null;
			
			
			String lStrDirPath=config.getString("mapPath");
			 System.out.println(lStrDirPath);
	         String lStrFileName=config.getString("ChronicClaimCasesTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
	         System.out.println(lStrFileName);
	         String lStrFileName1=lStrDirPath+"/"+lStrFileName; 
	         System.out.println(lStrFileName1);
	         
	         if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search").toString())) &&  "search".equalsIgnoreCase(request.getParameter("search").toString()) ){
					
				 chrList1=chronicOPService.searchChronicClaimCases(request.getParameter("chronicId"), request.getParameter("PatName"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDt"),request.getParameter("toDate"),request.getParameter("userState"),hospId,roleId);
					
					
						chronicOpForm.setChronicCSVList(chrList1);
						
						
					
					
				}else{
					
					 chrList=chronicOPService.getChronicClaimCases(roleId,hospId,userState);
					
										
					 chronicOpForm.setChronicCSVList(chrList);
						
					}
			     //Creates file in EHFTemp folder of client machine 
		         File lcsvfile = createFile(lStrDirPath,lStrFileName1);	         
		         char separator = '^';
		         StringBuilder line = new StringBuilder();   
			
		         line.append("ChronicId");
                line.append(separator);
                line.append("Employee No");
                line.append(separator);
                line.append("Patient Name");
                line.append(separator);
                line.append("District");
                line.append(separator);
                line.append("Gender");
                line.append(separator);
                line.append("Age");
                line.append(separator);
                line.append("Registration Date");
                line.append("\n");
                line.append("\n");
			
			
			
			if(chronicOpForm.getChronicCSVList()!=null && chronicOpForm.getChronicCSVList().size()>0){
				
				
				for(int i=0;i<chronicOpForm.getChronicCSVList().size();i++){
					  
					
					   
					  line.append(chronicOpForm.getChronicCSVList().get(i).getChronicID());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getEmployeeNo());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getName());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getDistrict());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getGender());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getAge());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getRegistrationDate());
                     line.append("\n");
					
					
					
					
				}
				
				request.setAttribute("File", line.toString().getBytes());    
			    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
			    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
			    response.setCharacterEncoding("UTF-8");
			    return mapping.findForward("openFile");
				
				
			}else{
				
				
			
				lStrPageName="coClaimCases";
				
				
			}
			
			
		   
    	   
    	   
    	   
    	   
    	   
		}
      if(lStrActionVal.equalsIgnoreCase("chronicOPCasesView")){
    	   List<ChronicOPVO> chrList1=null;
			List<ChronicOPVO> chrList=null;
			int  totalRecords=1;
			int pageNo=1;
			int  showPage=1;;
			int rowsPerPage=10;
			int pages=1;
			int startIndex=0;
			int endIndex=5;
			int startPage=1;
			int endPage=10;
			long count=0;
			
			String regCaseFlg=request.getParameter("regCaseFlg");
			String caseApprovalFlag=request.getParameter("caseApprovalFlag");
			String pendingFlag=request.getParameter("pendingFlag");
			
			if(request.getParameter("caseApprovalFlag")!=null && !("").equalsIgnoreCase(request.getParameter("caseApprovalFlag")))
				caseApprovalFlag=request.getParameter("caseApprovalFlag");
			else if(chronicOpForm.getCaseApprovalFlag()!=null&& !("").equalsIgnoreCase(chronicOpForm.getCaseApprovalFlag()))
				caseApprovalFlag=chronicOpForm.getCaseApprovalFlag();
			
			if(request.getParameter("regCaseFlg")!=null && !("").equalsIgnoreCase(request.getParameter("regCaseFlg")))
				regCaseFlg=request.getParameter("regCaseFlg");
			else if(chronicOpForm.getRegCaseFlg()!=null&& !("").equalsIgnoreCase(chronicOpForm.getRegCaseFlg()))
				regCaseFlg=chronicOpForm.getRegCaseFlg();
			
			ChronicOPVO chronicOPVO=new ChronicOPVO();
			chronicOPVO.setCaseApprovalFlag(caseApprovalFlag);
			chronicOPVO.setRegCaseFlg(regCaseFlg);
			chronicOPVO.setPendingFlag(pendingFlag);
			request.setAttribute("pendingFlag",pendingFlag);
			request.setAttribute("regCaseFlg",regCaseFlg);
			chronicOpForm.setCaseApprovalFlag(caseApprovalFlag);
			chronicOPVO.setUserId(lStrUserId);
		
            if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
				
				rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
			}
         
           if(rowsPerPage==0){
        	 
         	 rowsPerPage=10;
            }
			
			if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
				
			String str=request.getParameter("pageNo");				
			showPage=Integer.parseInt(str);
			
			startIndex=((showPage-1)*rowsPerPage);
			}
			
           if(request.getParameter("userState")!=null && !("-1").equalsIgnoreCase(request.getParameter("userState") ))
           {
        	   userState=request.getParameter("userState");
           }
           
           chronicOPVO.setStartIndex(startIndex+"");
		   chronicOPVO.setRowsPerPage(rowsPerPage+"");
		   chronicOPVO.setHospitalCode(hospId);
		   //chronicOPVO.setUserRole(roleId);
		   chronicOPVO.setSchemeId(userState);
		   
		   chronicOpForm.setRowsPerPage(rowsPerPage+"");
	
		   String mappedGrps=null;
		   if(rolesList!=null && rolesList.size()>0)
		   {
			   int roleCount=0;
			   for(LabelBean roles:rolesList )
			   {
				   
				   if(roleCount==0)
					   mappedGrps=roles.getID();
				   else
					   mappedGrps=mappedGrps+"~"+roles.getID();
				   roleCount++;
			   }
		   }
			
		   chronicOPVO.setUserRole(mappedGrps);
			
			if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search").toString())) &&  "search".equalsIgnoreCase(request.getParameter("search").toString()) ){
				
				 
				chronicOPVO.setChronicID(request.getParameter("chronicId"));
				chronicOPVO.setState(request.getParameter("state"));
				chronicOPVO.setName(request.getParameter("PatName"));
				chronicOPVO.setDistrict(request.getParameter("district"));
				chronicOPVO.setFromDt(request.getParameter("fromDate"));
				chronicOPVO.setToDate(request.getParameter("toDate"));
				
				chronicOPVO.setChronicStatus(request.getParameter("chronicStatus"));
				
				
				ChronicOPVO caseLst=new ChronicOPVO();
				
				caseLst=chronicOPService.searchChroniOPCases(chronicOPVO);
				chrList=caseLst.getCasesLst();
				count=caseLst.getCount();
					if(chrList!=null && chrList.size()>0){
						chronicOpForm.setChronincOPPatList(chrList);
						
					}
					
				}else{
					
					
			
					
					ChronicOPVO caseLst=new ChronicOPVO();
					
					
					caseLst=chronicOPService.getChronicOPCasesView(chronicOPVO);
					chrList=caseLst.getCasesLst();
					count=caseLst.getCount();
					if(chrList!=null && chrList.size()>0){
										
					chronicOpForm.setChronincOPPatList(chrList);
						
						
					}
								
				}
			
         if(chronicOpForm.getChronincOPPatList()!=null && chronicOpForm.getChronincOPPatList().size()>0){
				
				totalRecords=(int) count;
				
			}
         
			if(totalRecords>rowsPerPage){
				
				
				if((totalRecords%rowsPerPage)==0){
					pages=totalRecords/rowsPerPage;
					
				}else{
					
					pages=(totalRecords/rowsPerPage)+1;
				}
				
			}else{
				
				pages=1;
			}
			if(pages<=10)
			chronicOpForm.setEndPage(pages);
			else
				chronicOpForm.setEndPage(10);	
			
			if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
				
				
				 if(pages>10 && showPage<=10){
						
						request.setAttribute("next", "next");
						chronicOpForm.setNext("next");
						
					}
		              if(pages>10 && (showPage-10)>1){
						
						request.setAttribute("prev", "prev");
						chronicOpForm.setPrev("prev");
						
		              }
				
				
				
			}
			
      
			if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
				
					String str=request.getParameter("pageNo");				
				showPage=Integer.parseInt(str);
				
				startIndex=((showPage-1)*rowsPerPage)+1;
				
				if((showPage*rowsPerPage)<totalRecords){
					
					
					endIndex=showPage*rowsPerPage;
					
				}else{
					
					endIndex=totalRecords;
					
				}
				
				
	           int x=chronicOpForm.getStartPage();
				
				int y=chronicOpForm.getEndPage();
				
				if(y>pages){
				y=pages;
				}
				
				if(pages>10 && pages>y){
					
					
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
				
				if(pages>10 && x>10){
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
				}
				
				
				startPage=x;
				endPage=y;
				
				
			}
			
			if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
				
				int x=chronicOpForm.getStartPage();
				
				int y=chronicOpForm.getEndPage();
				if(x>=10 && pages>10){
					
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
				
				if(x-10>10 && pages>10){
					
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
				}
				
				if(showPage==1){
					
					if(totalRecords>rowsPerPage){
						
						startIndex=1;
						endIndex=rowsPerPage;
					}else{
					
						startIndex=(showPage-1)*rowsPerPage+1;
						if((startIndex+rowsPerPage)<totalRecords){
							
							endIndex=startIndex+rowsPerPage;
						}else{
							
							endIndex=totalRecords;
						}
						
						
					}
				}
				startPage=x-10;
				endPage=x-1;
				
				
				chronicOpForm.setStartPage(startPage);
				chronicOpForm.setEndPage(endPage);
				
	startIndex=((showPage-1)*rowsPerPage)+1;
				
				if((showPage*rowsPerPage)<totalRecords){
					
					
					endIndex=showPage*rowsPerPage;
					
				}else{
					
					endIndex=totalRecords;
					
				}
			}
			
			
			if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
				
                int x=chronicOpForm.getStartPage();
				
				int y=chronicOpForm.getEndPage();
			
				
				startPage=y+1;
				endPage=y+10;
				if(endPage>pages){
					
					endPage=pages;
				}
				
				if(pages>startPage+10){
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
				
				if(startPage-10>=1){
					
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
				}
				chronicOpForm.setStartPage(startPage);
				chronicOpForm.setEndPage(endPage);
				showPage=startPage;
              startIndex=((showPage-1)*rowsPerPage)+1;
				
				if((showPage*rowsPerPage)<totalRecords){
					
					
					endIndex=showPage*rowsPerPage;
					
				}else{
					
					endIndex=totalRecords;
					
				}
			
				
				
				
			}
			
			if(showPage==1){
				
				if(totalRecords>rowsPerPage){
					
					startIndex=1;
					endIndex=rowsPerPage;
				}else{
				
					startIndex=(showPage-1)*rowsPerPage;
					if((startIndex+rowsPerPage)<totalRecords){
						
						endIndex=startIndex+rowsPerPage;
					}else{
						
						endIndex=totalRecords;
					}
					
					
				}
			}
			startIndex=startIndex-1;
			
			request.setAttribute("startIndex",startIndex+1);	
			request.setAttribute("endIndex", endIndex);
			request.setAttribute("totalRecords", totalRecords);
			
			List<ChronicOPVO> chrList3=new ArrayList<ChronicOPVO>();
			/*if( (request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search"))) && request.getParameter("search").equalsIgnoreCase("search")){
				
				if(chrList1!=null && chrList1.size()>0){
				for(int i=startIndex;i<endIndex;i++){
					
					ChronicOPVO vo=new ChronicOPVO();
					vo.setChronicSubID(chrList1.get(i).getChronicSubID());
					vo.setChronicID(chrList1.get(i).getChronicID());
					vo.setName(chrList1.get(i).getName());
					vo.setEmployeeNo(chrList1.get(i).getEMPLOYEENO());
					vo.setAge(chrList1.get(i).getAge());
					vo.setDistrict(chrList1.get(i).getDistrict());
					vo.setGender(chrList1.get(i).getGender());
					vo.setRegistrationDate(chrList1.get(i).getRegistrationDate());
					chrList3.add(vo);
					
				}
				
				}
			}else{
				
				if(chrList!=null && chrList.size()>0){
                    for(int j=startIndex;j<endIndex;j++){
					
                    	ChronicOPVO vo=new ChronicOPVO();
                    	vo.setChronicSubID(chrList.get(j).getChronicSubID());
					vo.setChronicID(chrList.get(j).getChronicID());
					
					vo.setName(chrList.get(j).getName());
					vo.setEmployeeNo(chrList.get(j).getEMPLOYEENO());
					vo.setAge(chrList.get(j).getAge());
					vo.setDistrict(chrList.get(j).getDistrict());
					vo.setGender(chrList.get(j).getGender());
					vo.setRegistrationDate(chrList.get(j).getRegistrationDate());
					chrList3.add(vo);
					
				}
				}
				
			}*/
		
			//chronicOpForm.setChronincOPPatList(chrList3);
			request.setAttribute("liPageNo",showPage);
			/*if(chrList3.size()==0){
				request.setAttribute("startIndex", 0);
			}else{
				
				request.setAttribute("startIndex", startIndex+1);
			}
			if(chrList3.size()==0){
				request.setAttribute("endIndex", 0);
			}else{
				request.setAttribute("endIndex", endIndex);
				
			}*/
			
			request.setAttribute("rowsPerPage", rowsPerPage);
			request.setAttribute("pages", pages);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
	
			List<LabelBean> chronicStatus=new ArrayList<LabelBean>();
			chronicStatus=chronicOPService.getChronicStatus();
			request.setAttribute("chronicStatus",chronicStatus);
			chronicOpForm.setChronicStatus(chronicOpForm.getChronicStatus());
			
			
		String stateId=request.getParameter("state");
		List<LabelBean> cmbHdrList=new ArrayList<LabelBean>();;
		String lStrHdrId="LH6";
			if(stateId!=null)
			{
				//cmbHdrList=commonService.getAsrimLocations(lStrHdrId, stateId);
				cmbHdrList=commonService.getLocationsNew(lStrHdrId, stateId, request.getParameter("stateType"));
			}
			if(request.getParameter("district")!=null && !"-1".equalsIgnoreCase(request.getParameter("district")))
			chronicOpForm.setDistrict(request.getParameter("district"));
			request.setAttribute("districtList",cmbHdrList);
			
			if(request.getParameter("stateType")!=null && !"-1".equalsIgnoreCase(request.getParameter("stateType")))
			{
				chronicOpForm.setStateType(request.getParameter("stateType"));
			}
			
			if(caseApprovalFlag==null || !("Y").equalsIgnoreCase(caseApprovalFlag))
				caseApprovalFlag="N";
				request.setAttribute("caseApprovalFlag",caseApprovalFlag);
				chronicOpForm.setCaseApprovalFlag(caseApprovalFlag);
			 lStrPageName="chronicOPCasesView";
			 
			 
		 }
       
       
       
       if(lStrActionVal.equalsIgnoreCase("chronicOPCasesCSVView")){
			List<ChronicOPVO> chrList1=null;
			List<ChronicOPVO> chrList=null;
			
			
			String lStrDirPath=config.getString("mapPath");
			 System.out.println(lStrDirPath);
	         String lStrFileName=config.getString("ChronicOPCasesTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
	         System.out.println(lStrFileName);
	         String lStrFileName1=lStrDirPath+"/"+lStrFileName; 
	         System.out.println(lStrFileName1);
	         
	         String regCaseFlg=request.getParameter("regCaseFlg");

	     	
				if(request.getParameter("regCaseFlg")!=null && !("").equalsIgnoreCase(request.getParameter("regCaseFlg")))
					regCaseFlg=request.getParameter("regCaseFlg");
				else if(chronicOpForm.getRegCaseFlg()!=null&& !("").equalsIgnoreCase(chronicOpForm.getRegCaseFlg()))
					regCaseFlg=chronicOpForm.getRegCaseFlg();

			    
				request.setAttribute("regCaseFlg",regCaseFlg);
				
	     	String caseApprovalFlag=request.getParameter("caseApprovalFlag");	
			if(request.getParameter("caseApprovalFlag")!=null && !("").equalsIgnoreCase(request.getParameter("caseApprovalFlag")))
				caseApprovalFlag=request.getParameter("caseApprovalFlag");
			else if(chronicOpForm.getCaseApprovalFlag()!=null&& !("").equalsIgnoreCase(chronicOpForm.getCaseApprovalFlag()))
				caseApprovalFlag=chronicOpForm.getCaseApprovalFlag();
			
			ChronicOPVO chronicOPVO=new ChronicOPVO();
			chronicOPVO.setCaseApprovalFlag(caseApprovalFlag);
			chronicOpForm.setCaseApprovalFlag(caseApprovalFlag);
			chronicOPVO.setRegCaseFlg(regCaseFlg);
	         
	       
	            chronicOPVO.setHospitalCode(hospId);
				chronicOPVO.setUserRole(roleId);
				chronicOPVO.setSchemeId(request.getParameter("userState"));
				chronicOPVO.setCsvFlag("Y");
	         
	         chronicOPVO.setStartIndex("0");
	         chronicOPVO.setRowsPerPage("10");
	         
	         if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search").toString())) &&  "search".equalsIgnoreCase(request.getParameter("search").toString()) ){
					
				 
	        	    chronicOPVO.setChronicID(request.getParameter("chronicId"));
					chronicOPVO.setState(request.getParameter("state"));
					chronicOPVO.setName(request.getParameter("PatName"));
					chronicOPVO.setDistrict(request.getParameter("district"));
					chronicOPVO.setFromDt(request.getParameter("fromDt"));
					chronicOPVO.setToDate(request.getParameter("toDate"));
					chronicOPVO.setChronicStatus(request.getParameter("chronicStatus"));
					
					
					ChronicOPVO caseLst=new ChronicOPVO();
					
					caseLst=chronicOPService.searchChroniOPCases(chronicOPVO);
					chrList1=caseLst.getCasesLst();
	        	 
					
					if(chrList1!=null && chrList1.size()>0){
						chronicOpForm.setChronicCSVList(chrList1);
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					
				}else{
					
					
					
					
                    ChronicOPVO caseLst=new ChronicOPVO();
					
					
					caseLst=chronicOPService.getChronicOPCasesView(chronicOPVO);
					chrList=caseLst.getCasesLst();
										
					if(chrList!=null && chrList.size()>0){
										
						chronicOpForm.setChronicCSVList(chrList);
						
					}
					}
			     //Creates file in EHFTemp folder of client machine 
		         File lcsvfile = createFile(lStrDirPath,lStrFileName1);	         
		         char separator = '^';
		         StringBuilder line = new StringBuilder();   
			
		         line.append("ChronicId");
                line.append(separator);
                line.append("ChronicNo");
                line.append(separator);
                line.append("Employee No");
                line.append(separator);
                line.append("Patient Name");
                line.append(separator);
                line.append("Chronic Status");
                line.append(separator);
                line.append("District");
                line.append(separator);
                line.append("Gender");
                line.append(separator);
                line.append("Age");
                line.append(separator);
                line.append("Registration Date");
                line.append("\n");
                line.append("\n");
			
			
			
			if(chronicOpForm.getChronicCSVList()!=null && chronicOpForm.getChronicCSVList().size()>0){
				
				
				for(int i=0;i<chronicOpForm.getChronicCSVList().size();i++){
					  
					
					   
					 line.append(chronicOpForm.getChronicCSVList().get(i).getChronicSubID());
                     line.append(separator);
                	 line.append(chronicOpForm.getChronicCSVList().get(i).getChronicNo());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getEmployeeNo());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getName());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getChronicStatus());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getDistrict());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getGender());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getPatAge());
                     line.append(separator);
                     line.append(chronicOpForm.getChronicCSVList().get(i).getCaseRegnDt());
                     line.append("\n");
					
					
					
					
				}
				
				request.setAttribute("File", line.toString().getBytes());    
			    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
			    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
			    response.setCharacterEncoding("UTF-8");
			    return mapping.findForward("openFile");
				
				
			}else{
				
				request.setAttribute("NODATA", "NODATA");
				lStrPageName="chronicOPCasesView";
				
				
			}
				}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*added by venkatesh*/
		if ("viewPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			String 	ChronicId=null;
			String chronicNo=null;
			String viewType= request.getParameter("viewType");
			System.out.println("view type is :"+viewType);	
			EhfChronicPatientDtl ehfChronicPatientDtl=null;
			chronicNo=request.getParameter("patientId");
			ChronicId=chronicNo.substring(0,chronicNo.length()-2);
			String installment=chronicOPService.getChronicInstallment(ChronicId);
			//String chronicNo=ChronicId+"/"+installment;
			chronicOpForm.setPatientNo(ChronicId);
			// request.setAttribute("patientId",patientId);
			ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(ChronicId);
			//storing patcrtdt
			session.setAttribute("patCrtdt",ehfChronicPatientDtl.getCrtDt());
			
			
			String serverDate="0";
			serverDt=chronicOPService.getCreatedDt("dd/MM/yyyy");
			request.setAttribute("serverDt",serverDt);
			
			chronicOpForm.setCardNo(ehfChronicPatientDtl.getCardNo());
			chronicOpForm.setFname(ehfChronicPatientDtl.getName());
			if(ehfChronicPatientDtl.getCardIssueDt()!=null)
			{
				chronicOpForm.setCardIssueDt(sdf.format(ehfChronicPatientDtl.getCardIssueDt()));
			}
			else
				chronicOpForm.setCardIssueDt("NA");
			if(ehfChronicPatientDtl.getGender()!=null && "M".equalsIgnoreCase(ehfChronicPatientDtl.getGender()))
			{
				chronicOpForm.setGender("Male");
			}
			else if(ehfChronicPatientDtl.getGender()!=null && "F".equalsIgnoreCase(ehfChronicPatientDtl.getGender()))
			{
				chronicOpForm.setGender("Female");
			}
			if(ehfChronicPatientDtl.getEnrollDob()!=null)
			{
				chronicOpForm.setDateOfBirth(sdf.format(ehfChronicPatientDtl.getEnrollDob()));
			}
			if(ehfChronicPatientDtl.getAge()!=null)
			{
				chronicOpForm.setYears(ehfChronicPatientDtl.getAge().toString());
			}
			if(ehfChronicPatientDtl.getAgeMonths()!=null)
			{
				chronicOpForm.setMonths(ehfChronicPatientDtl.getAgeMonths().toString());
			}
			if(ehfChronicPatientDtl.getAgeDays()!=null)
			{
				chronicOpForm.setDays(ehfChronicPatientDtl.getAgeDays().toString());
			}
			String relationId=ehfChronicPatientDtl.getRelation();
			String scheme=ehfChronicPatientDtl.getSchemeId();
			session.setAttribute("scheme", scheme);
			String relationName=patientService.getRelationName(relationId);
			chronicOpForm.setRelation(relationName);
			String casteId=ehfChronicPatientDtl.getCaste();
			if(casteId!=null && !casteId.equals(""))
			{
				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
				chronicOpForm.setCaste(casteName);
			}
			if(ehfChronicPatientDtl.getContactNo()!=null && !ehfChronicPatientDtl.getContactNo().equals(""))
			{
				chronicOpForm.setContactno(ehfChronicPatientDtl.getContactNo().toString());
			}
			/*String occName = patientService.getOccupationName(ehfChronicPatientDtl.getOccupationCd());
        if(occName!=null && !occName.equalsIgnoreCase(""))*/
			chronicOpForm.setOccupation(ehfChronicPatientDtl.getOccupationCd());
		
			/*else
        	chronicOpForm.setOccupation("NA");*/
			//Setting slab
			String slabType=null;
			String slab=null;
			if(ehfChronicPatientDtl.getSlab()!=null)
			{
				slabType=ehfChronicPatientDtl.getSlab();
			}
			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.SemiPrivateWard");
			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.PrivateWard");
			chronicOpForm.setSlab(slab);
			//End Of Slab
			chronicOpForm.setHno(ehfChronicPatientDtl.getHouseNo());
			chronicOpForm.setStreet(ehfChronicPatientDtl.getStreet());
			chronicOpForm.setState(patientService.getLocationName(ehfChronicPatientDtl.getState()));
			String distCode=ehfChronicPatientDtl.getDistrictCode();
			String distName=patientService.getLocationName(distCode);
			chronicOpForm.setDistrict(distName);
			String mandalCode=ehfChronicPatientDtl.getMandalCode();
			String mandalName=patientService.getLocationName(mandalCode);
			chronicOpForm.setMandal(mandalName);
			String villageCode=ehfChronicPatientDtl.getVillageCode();
			String villageName=patientService.getLocationName(villageCode);
			chronicOpForm.setVillage(villageName);
			if(ehfChronicPatientDtl.getPinCode()!=null && !ehfChronicPatientDtl.getPinCode().equalsIgnoreCase(""))
				chronicOpForm.setPin(ehfChronicPatientDtl.getPinCode());
			else
				chronicOpForm.setPin("NA");
			//Setting Communication Address
		
			
			
			
				chronicOpForm.setComm_pin("NA");
			chronicOpForm.setDtTime(sdfw.format(ehfChronicPatientDtl.getRegHospDate()));
			String photoUrl=patientService.getPatientPhoto(ehfChronicPatientDtl.getCardNo());
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
					chronicOpForm.setPhotoUrl(photoUrl);
				}
				catch(Exception e)
				{
					//GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in chronicopAction." +e.getMessage()) ;
				}
			}
			String hospCode=ehfChronicPatientDtl.getRegHospId();
			String hospName=patientService.getHospName(hospCode);
			chronicOpForm.setHospitalId(hospCode);
			request.setAttribute("hospitalId",hospCode);
			chronicOpForm.setHospitalName(hospName);
String hospScheme="";
			if(hospCode!=null)
			{
			EhfmHospitals  ehfmHospitals=new EhfmHospitals();
			ehfmHospitals=commonService.getHospInfo(hospCode);
			hospScheme=ehfmHospitals.getScheme();
			request.setAttribute("hospScheme",ehfmHospitals.getScheme());
			}
			else
			{
				hospScheme=scheme;
			
			}
			chronicOpForm.setHospScheme(hospScheme);
			request.setAttribute("hospScheme",hospScheme);	
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
					chronicOpForm.setHosptype(hospitalList.get(0).getNATURE());
				}

				/*List<LabelBean> testsList=null;
        testsList=patientService.getTests(lStrLangID);
        request.setAttribute("testsList", testsList);*/

				List<LabelBean> mainComplaintList=null;
				mainComplaintList=patientService.getMainComplaintLst();
				request.setAttribute("mainComplaintList", mainComplaintList);

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
				/*investigationsList=commonService.getInvestBlockName();
				request.setAttribute("investigationsList", investigationsList);*/


				/*List<LabelBean> drugsList=commonService.getOpDrugs(null);     //getDrugs();
				request.setAttribute("drugsList", drugsList);
				*/
				List<LabelBean> routeTypeLst=commonService.getRouteType(null);     //getDrugs();
				request.setAttribute("routeTypeLst", routeTypeLst);
				
				List<LabelBean> strengthTypeLst=commonService.getStrengthType(null);     //getDrugs();
				request.setAttribute("strengthTypeLst", strengthTypeLst);

				List<LabelBean> examinFndgsList=null;
				examinFndgsList=commonService.getGenExaminFndgs(ehfChronicPatientDtl.getSchemeId());
				request.setAttribute("examinFndgsList", examinFndgsList);

				
				List<LabelBean> diagnTypeList = patientService.getDiagnosisTypes();
				session.setAttribute("diagnTypeList",diagnTypeList);

				List<LabelBean> categoryList = commonService.getAsriCategoryList(hospCode,scheme);
				session.setAttribute("AsricategoryList",categoryList);

				List<LabelBean> procedureList= new ArrayList<LabelBean>();
				session.setAttribute("ICDprocedureList",procedureList);

				List<LabelBean> opPackageList= chronicOPService.getOpPkgList(scheme);
				request.setAttribute("opCategoryList",opPackageList);
				
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
				
				PreauthVO preauthDtlsVO = new PreauthVO();
				chronicOpForm.setPatientType("");
                if(ChronicId!=null && !ChronicId.equalsIgnoreCase("")){
               
				preauthDtlsVO = chronicOPService.getPatientFullDtls(ehfChronicPatientDtl.getChronicId(),chronicNo,null);
				scheme=ehfChronicPatientDtl.getSchemeId();
				
				chronicOpForm.setChronicNo(chronicNo);
				chronicOpForm.setOpPkgCode(preauthDtlsVO.getOpPkgCode());
				chronicOpForm.setOpPkgName(preauthDtlsVO.getOpPackageName());
				chronicOpForm.setOpCatCode(preauthDtlsVO.getOpIcdCode());
				chronicOpForm.setOpCatName(preauthDtlsVO.getOpCatName());
				
				chronicOpForm.setChronicPackageCode(preauthDtlsVO.getOpPkgCode());
				chronicOpForm.setChronicCatCode(preauthDtlsVO.getOpIcdCode());
				
				
				/*Added do calculate total package amount*/
				long drugAmt=0;
				ChronicOPVO chronicOPVO=new ChronicOPVO();
				chronicOPVO.setOpPkgCode(preauthDtlsVO.getOpPkgCode());
				chronicOPVO.setActOrder(installment);
				String medcoScheme=chronicOPService.getMedcoScheme(lStrUserId);
				chronicOPVO.setSchemeId(medcoScheme);
				drugAmt=chronicOPService.getPackageDrugAmount(chronicOPVO);
				chronicOpForm.setPackageDrugAmt(drugAmt+"");
				
				
				
				session.setAttribute("scheme",scheme);
				preauthDtlsVO.setIpOpFlag("chronicOP");
				chronicOpForm.setComplaints(preauthDtlsVO.getComplaintType());
			    chronicOpForm.setComplaintCode(preauthDtlsVO.getComplaintType());
			    chronicOpForm.setPatComplaintCode(preauthDtlsVO.getPatComplaint());
			    chronicOpForm.setPresentHistory(preauthDtlsVO.getPresentIllness());
				if(preauthDtlsVO.getPersonalHis()!=null && !preauthDtlsVO.getPersonalHis().equalsIgnoreCase(""))
			    {
				String[]  persHist = preauthDtlsVO.getPersonalHis().split("~");
			    chronicOpForm.setPersonalHist(persHist);
				}
				if(preauthDtlsVO.getExamFindg()!=null && !preauthDtlsVO.getExamFindg().equalsIgnoreCase(""))
			    {
				String[] examfndList = preauthDtlsVO.getExamFindg().split("~");
			    chronicOpForm.setExaminationFnd(examfndList);
				}
			    
			    	chronicOpForm.setOpNo(preauthDtlsVO.getPatientIPNo());
			    	chronicOpForm.setOpRemarks(preauthDtlsVO.getRemarks());
			    	chronicOpForm.setDiagnosedBy(preauthDtlsVO.getDocType());	
			    	if(preauthDtlsVO.getDrugList()!=null && preauthDtlsVO.getDrugList().size()>0){
			    	chronicOpForm.setDrugLt(preauthDtlsVO.getDrugList());
			    	String drugLst="";
			    	for(DrugsVO drugVO: preauthDtlsVO.getDrugList()){
			    		
			    		DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
			    		Date expDt=drugVO.getExpDt();
			    		
						String expiryDt=drugVO.getExpiryDt();
			    		
			    		//String expiryDt=df.format(expDt);
			    		drugLst = drugLst +drugVO.getDrugCode()+"~"+drugVO.getDosage()+"~"+drugVO.getMedicationPeriod()+"~"+drugVO.getBatchNo()+"~"+expiryDt+"@#";
			    	}
					request.setAttribute("drugsLst",drugLst);
			    	}
			    	  
			    	
			    	/*to get general investigations*/
			    	int otherInvestCount=0;
			    	List<LabelBean> genInvList = new ArrayList<LabelBean>();
			    	  String genInvestLst="";
						if(preauthDtlsVO.getInvList()!=null && preauthDtlsVO.getInvList().size()>0){
							for(PreauthVO preauthVo : preauthDtlsVO.getInvList()){
								LabelBean inv = new LabelBean();
								if(preauthVo.getSPLINVSTID()!=null && preauthVo.getSPLINVSTID().contains("OI"))
								{
								
									otherInvestCount=Integer.parseInt(preauthVo.getSPLINVSTID().substring(2,preauthVo.getSPLINVSTID().length()));
								}
								inv.setID(preauthVo.getSPLINVSTID());
								inv.setVALUE(preauthVo.getNAME());
								inv.setLVL(preauthVo.getROUTE());
								inv.setACTION(preauthVo.getTEST());
								genInvestLst = genInvestLst+preauthVo.getTESTKNOWN()+"~"+preauthVo.getSPLINVSTID()+"~"+preauthVo.getNAME()+"@";
								genInvList.add(inv);
							}
							
							chronicOpForm.setGenInvList(genInvList);
							chronicOpForm.setOtherInvestCount(otherInvestCount);
							request.setAttribute("genInvestFlag", "true");
							request.setAttribute("genInvestLst", genInvestLst);
							request.setAttribute("invSize",genInvestLst.length());
						}
			    	}
			    
			   
				request.setAttribute("serverDt",serverDt);
				request.setAttribute("PatientOpList",preauthDtlsVO);
				request.setAttribute("viewType", viewType);
			
				lStrPageName="chronicPatientDetails";
			}
			else if(pageType.equalsIgnoreCase("print"))
				lStrPageName="printDetails";
		}

		/*added for getting packages based on category selected*/
		if("getChronicPkgList".equalsIgnoreCase(lStrActionVal)){
			String cardNo=null;
			int count=0;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			String opCatCode= request.getParameter("lStrOpCatCode");
			List<String> opPkgList=null;
			//opPkgList=chronicOPService.getOpPkgList(opCatCode);
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
		
		
		if("getChronicInvestList".equalsIgnoreCase(lStrActionVal)){
			String cardNo=null;
			int count=0;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			
			String opCatCode= request.getParameter("lStrOpCatCode");
			String currentInstallment=(String) session.getAttribute("currentInstallment");
			String  installment=(String) session.getAttribute("installment");
			System.out.println("installment is :"+installment);
			
			if(installment==null)
			{
				installment="1";
			}
			if(currentInstallment!=null)
			{
				installment=currentInstallment;
			}
	
		
			List<String> chronicInvestList=null;
			chronicInvestList=chronicOPService.getChronicInvestList(opCatCode,installment);
			if (chronicInvestList != null && chronicInvestList.size() > 0)
				request.setAttribute("AjaxMessage", chronicInvestList);
			lStrPageName="ajaxResult";
		}
		
		
		
		if("getChronicDrugsList".equalsIgnoreCase(lStrActionVal)){
			String cardNo=null;
			int count=0;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			String opCatCode= request.getParameter("lStrOpCatCode");
			//String installment=(String) session.getAttribute("installment");
			List<String> ChronicDrugsList=null;
			//ChronicDrugsList=chronicOPService.getChronicDrugsList(opCatCode);
			ChronicDrugsList=chronicOPService.getChronicChemSubstanceList(opCatCode);
			if (ChronicDrugsList != null && ChronicDrugsList.size() > 0)
				request.setAttribute("AjaxMessage", ChronicDrugsList);
	
			lStrPageName="ajaxResult";
		}

		
		

		
	
	
	
	
	
	
	if ("saveCaseDetails".equalsIgnoreCase(lStrActionVal))
	{
		chronicOpForm.setPatientNo("");
		String saveFlg = request.getParameter("saveFlag");
	
		SimpleDateFormat sdfp=new SimpleDateFormat("dd-MM-yyyy");
		String patCrtDt=null;	
		Date date = new Date();
		if(session.getAttribute("patCrtdt")!=null)
			 patCrtDt=sdfp.format(session.getAttribute("patCrtdt"));	
		else
			patCrtDt=sdfp.format(date);	
		
		String crtDate=sdfw.format(date);
		crtDt = sdfw.parse((sdfw.format(date)));
		ChronicOpVO=new ChronicOPVO();
		String liNextVal="";
		String surgeryNextVal="";
		EhfmSeq ehfmSeqCase = null;
		EhfmSeq ehfmSeqCaseTherapy = null;
		String lStrChronicId=null;
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
		userId=session.getAttribute("EmpID").toString(); 
		if(request.getParameter("chronicId")!=null && !request.getParameter("chronicId").equals("")){
			lStrChronicId=request.getParameter("chronicId");
		}
		if(request.getParameter("treatingDocRmks")!=null && !request.getParameter("treatingDocRmks").equals("")){
			treatingDocRmks=request.getParameter("treatingDocRmks");
		}
		if(request.getParameter("checkType")!=null && !request.getParameter("checkType").equals("")){
			checkType=request.getParameter("checkType");
			request.setAttribute("checkType", checkType);
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
		//String drugNextVal = String.valueOf(ehfmSeqDrug.getSeqNextVal());
		//Long drugSeqNextVal=Long.parseLong(drugNextVal);
		ChronicOpVO.setSchemeId(chronicOpForm.getScheme());
		ChronicOpVO.setPersonalHistVal(chronicOpForm.getPersonalHistVal());
		ChronicOpVO.setExamFndsVal(chronicOpForm.getExamFndsVal());
		ChronicOpVO.setCrtDt(crtDate);

		ChronicOpVO.setCaseSurgId(surgeryNextVal);
		ChronicOpVO.setChronicID(lStrChronicId);
		ChronicOpVO.setCrtUsr(userId);
		ChronicOpVO.setLangId(lStrLangID);
		ChronicOpVO.setUserRole(roleId);
		//if(chronicOpForm.getComplaints()!=null && !chronicOpForm.getComplaints().equalsIgnoreCase("") && !chronicOpForm.getComplaints().equalsIgnoreCase("-1"))
		ChronicOpVO.setComplaints(chronicOpForm.getComplaintCode());
		ChronicOpVO.setPatientComplaint(chronicOpForm.getPatientComplaint());
		if(chronicOpForm.getPresentHistory()!=null && chronicOpForm.getPresentHistory().length()>3000)
		{
			ChronicOpVO.setPresentHistory(chronicOpForm.getPresentHistory().substring(0, 3000));
		}
		else if(chronicOpForm.getPresentHistory()!=null)
		{
			ChronicOpVO.setPresentHistory(chronicOpForm.getPresentHistory());
		}
		else 
			ChronicOpVO.setPresentHistory("");
		ChronicOpVO.setPersonalHistory(chronicOpForm.getPersonalHistory());
		ChronicOpVO.setFamilyHistory(chronicOpForm.getFamilyHistory());
		ChronicOpVO.setPastHistory(chronicOpForm.getPastHistory());
		ChronicOpVO.setExaminationFnd(chronicOpForm.getExaminationFnd());
		ChronicOpVO.setFamilyHistoryOthr(chronicOpForm.getFamilyHistoryOthr());
		ChronicOpVO.setPastHistryOthr(chronicOpForm.getPastHistryOthr());
		ChronicOpVO.setPastHistryCancers(chronicOpForm.getPastHistryCancers()); 
		ChronicOpVO.setPastHistryEndDis(chronicOpForm.getPastHistryEndDis());
		if(chronicOpForm.getPastHistrySurg()!=null && chronicOpForm.getPastHistrySurg().length()>3000)
		{
			ChronicOpVO.setPastHistrySurg(chronicOpForm.getPastHistrySurg().substring(0, 3000));
		}
		else if(chronicOpForm.getPastHistrySurg()!=null)
		{
			ChronicOpVO.setPastHistrySurg(chronicOpForm.getPastHistrySurg());
		}
		
		
		/*if(chronicOpForm.getDiagType()!=null && !chronicOpForm.getDiagType().equalsIgnoreCase("") && !chronicOpForm.getDiagType().equalsIgnoreCase("-1"))
		{
			ChronicOpVO.setDiagnosisType(chronicOpForm.getDiagType());
			GLOGGER.error("Info Message while setting Diagnosis Type "+chronicOpForm.getDiagType()+" for chronic Id "+lStrChronicId);
		}
		if(chronicOpForm.getMainCatName()!=null && !chronicOpForm.getMainCatName().equalsIgnoreCase("") && !chronicOpForm.getMainCatName().equalsIgnoreCase("-1"))
		{
			ChronicOpVO.setMainCatName(chronicOpForm.getMainCatName());
			GLOGGER.error("Info Message while setting Diagnosis Main Category Code "+chronicOpForm.getMainCatName()+" for chronic Id "+lStrChronicId);
		}
		if(chronicOpForm.getCatName()!=null && !chronicOpForm.getCatName().equalsIgnoreCase("") && !chronicOpForm.getCatName().equalsIgnoreCase("-1"))
		{
			ChronicOpVO.setCatName(chronicOpForm.getCatName());
			GLOGGER.error("Info Message while setting Diagnosis Category Code "+chronicOpForm.getCatName()+" for chronic Id "+lStrChronicId);
		}
		if(chronicOpForm.getSubCatName()!=null && !chronicOpForm.getSubCatName().equalsIgnoreCase("") && !chronicOpForm.getSubCatName().equalsIgnoreCase("-1"))
		{
			ChronicOpVO.setSubCatName(chronicOpForm.getSubCatName());
			GLOGGER.error("Info Message while setting Diagnosis Sub Category Code "+chronicOpForm.getSubCatName()+" for chronic Id "+lStrChronicId);
		}
		if(chronicOpForm.getDiseaseName()!=null && !chronicOpForm.getDiseaseName().equalsIgnoreCase("") && !chronicOpForm.getDiseaseName().equalsIgnoreCase("-1"))
		{
			ChronicOpVO.setDiseaseName(chronicOpForm.getDiseaseName());
			GLOGGER.error("Info Message while setting Diagnosis Disease Code "+chronicOpForm.getDiseaseName()+" for chronic Id "+lStrChronicId);
		}
		if(chronicOpForm.getDisAnatomicalName()!=null && !chronicOpForm.getDisAnatomicalName().equalsIgnoreCase("") && !chronicOpForm.getDisAnatomicalName().equalsIgnoreCase("-1"))
		{
			ChronicOpVO.setDisAnatomicalName(chronicOpForm.getDisAnatomicalName());
			GLOGGER.error("Info Message while setting Diagnosis Disease Anatomical Code "+chronicOpForm.getDisAnatomicalName()+" for chronic Id "+lStrChronicId);
		}
		//ChronicOpVO.setMainSymptomCode(chronicOpForm.getMainSymptomName());
		//ChronicOpVO.setSymptomCode(chronicOpForm.getSymptomName());

		List<LabelBean> symptomsList = new ArrayList<LabelBean>();
		LabelBean symptom = null;
		if(chronicOpForm.getSymptoms()!=null && !chronicOpForm.getSymptoms().equalsIgnoreCase("")){
		String sym=chronicOpForm.getSymptoms().substring(0, chronicOpForm.getSymptoms().length()-1);
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
			symptom.setID(symptmValues[0]);
			symptom.setSUBCODE(symptmValues[1]);
			symptom.setVALUE(symptmValues[2]);  
			symptomsList.add(symptom);
		}
		ChronicOpVO.setSymptoms(symptomsList);
		}
		ChronicOpVO.setPatientType(chronicOpForm.getPatientType());
		//ChronicOpVO.setCaseId(liNextVal);
		if(chronicOpForm.getPatientType()!=null && chronicOpForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IP"))))
		{
			ChronicOpVO.setDiagnosedBy(chronicOpForm.getIpDiagnosedBy());
			ChronicOpVO.setDoctorName(chronicOpForm.getIpDoctorName());

			//ChronicOpVO.setOtherDocName(chronicOpForm.getIpOtherDocName());
			//ChronicOpVO.setDocRegNo(chronicOpForm.getIpDocRegNo());
			//ChronicOpVO.setDocQual(chronicOpForm.getIpDocQual());
			//ChronicOpVO.setDocMobileNo(chronicOpForm.getIpDocMobileNo());

			List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
			CaseTherapyVO caseTherapy = null;
			
			ChronicOpVO.setCaseTherapy(caseTherapyList);
			//ehfmSeqCaseTherapy.setSeqNextVal(Long.valueOf(caseTheSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqCaseTherapy);

			//ChronicOpVO.setCaseProcCodes(caseProcCodes.substring(0, caseProcCodes.length()-1));
		
		}
		
		
*/			ChronicOpVO.setDiagnosedBy(chronicOpForm.getDiagnosedBy());
			ChronicOpVO.setDoctorName(chronicOpForm.getDoctorName());
			ChronicOpVO.setOtherDocName(chronicOpForm.getOtherDocName());
			ChronicOpVO.setDocRegNo(chronicOpForm.getDocRegNo());
			ChronicOpVO.setDocQual(chronicOpForm.getDocQual());
			ChronicOpVO.setDocMobileNo(chronicOpForm.getDocMobileNo());
			String s=null;
			List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
			DrugsVO drugsVO = null;
			if(chronicOpForm.getDrugs()!=null && chronicOpForm.getDrugs().length()>0){
				String verify=chronicOpForm.getDrugs().substring(chronicOpForm.getDrugs().length()-2, chronicOpForm.getDrugs().length()-1);
				if(("@").equals(verify))
			s=chronicOpForm.getDrugs().substring(0, chronicOpForm.getDrugs().length()-2);
				else
					s=chronicOpForm.getDrugs().substring(0, chronicOpForm.getDrugs().length()-1);		
			String[] drugs=s.split("@,");
			for(int z=0;z<drugs.length;z++)
			{
				String drug=drugs[z];
				String[] drugValues=drug.split("~");
				drugsVO = new DrugsVO();
				
				drugsVO.setDrugName(drugValues[0]);
				
				drugsVO.setDosage(drugValues[1]);
				drugsVO.setMedicationPeriod(drugValues[2]);
				/*drugsVO.setBatchNo(drugValues[3]);
				drugsVO.setExpiryDt(drugValues[4]);*/
				Long drugSeqNextVal = Long.parseLong(patientService.getSequence("DRUG_ID"));
				drugsVO.setDrugId(drugSeqNextVal);
				//drugsVO.setDrugId(drugSeqNextVal);
				//drugSeqNextVal=drugSeqNextVal+1;
				drugsList.add(drugsVO);
			}
			}
			ChronicOpVO.setDrugs(drugsList);
			//ehfmSeqDrug.setSeqNextVal(Long.valueOf(drugSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqDrug);
			if(chronicOpForm.getAddPckgFlg()!=null && ("Y").equalsIgnoreCase(chronicOpForm.getAddPckgFlg()))
			{
			ChronicOpVO.setOpCatCode(chronicOpForm.getChronicCatCode());
			ChronicOpVO.setOpPkgCode(chronicOpForm.getChronicPackageCode());
			ChronicOpVO.setOpIcdCode(chronicOpForm.getChronicCatCode());
		
			}
		GLOGGER.info("ChronicId"+lStrChronicId+"while Patient Case registration in ChronicAction.") ;

		if(request.getParameter("addTests")!=null && !request.getParameter("addTests").equals(""))
		{
			genInvestigationList=request.getParameter("addTests");
			LabelBean labelBean=new LabelBean();
			StringTokenizer st=new StringTokenizer(genInvestigationList,"~");
			while(st.hasMoreTokens()){
				labelBean=new LabelBean();
				String lL=st.nextToken();
				StringTokenizer st1=new StringTokenizer(lL,"$");
				while(st1.hasMoreTokens()){
					labelBean.setVALUE(st1.nextToken());
					labelBean.setID(st1.nextToken());                        
					lGenInvList.add(labelBean)  ;
				}
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
		for(LabelBean labelBean: lGenInvList){
			//used to avoid deleted attachments-conflict
			while(chronicOpForm.getGenAttach(i)==null )
			{
				i++;
			}
			if(chronicOpForm.getGenAttach(i)!=null ){
				lFormFile=chronicOpForm.getGenAttach(i);
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
			while(chronicOpForm.getUpdateGenAttach(i)==null )
			{
				if(i==99)
				{
					break;
				}
				i++;
			}
			if(chronicOpForm.getUpdateGenAttach(i)!=null ){
				lFormFile=chronicOpForm.getUpdateGenAttach(i);
				if (lFormFile.getFileSize() > 204800) 
				{
					errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
				}
			}
			i++;
		}

		//For IP Therapy Investigations
		i=0;
		for(LabelBean labelBean: lSelInvList){
			//used to avoid deleted attachments-conflict
			while(chronicOpForm.getAttach(i)==null )
			{
				i++;
			}
			if(chronicOpForm.getAttach(i)!=null ){
				lFormFile=chronicOpForm.getAttach(i);
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
				while(chronicOpForm.getGenAttach(i)==null )
				{
					i++;
				}
				lFormFile=chronicOpForm.getGenAttach(i);                                     
				lFileName=lFormFile.getFileName();
				if(lFileName!=null && !lFileName.equals(""))
				{
					lCount=lFileName.lastIndexOf(".");
					lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
					// save file in folder's
					String lStrSharePath = 
							config.getString("STORAGE_BOX") + 
							/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
							config.getString("SLASH_VALUE") + lStrChronicId + 
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
						GLOGGER.error ( "Exception occurred using saveChronicCaseDetails actionFlag in ChronicAction." +e.getMessage()) ;
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
				lGenTestAttachList.add(attachmentVO);                 
				i++;
			}
			ChronicOpVO.setGenAttachmentsList(lGenTestAttachList);  

			//ehfmSeqGenTests.setSeqNextVal(Long.valueOf(genTestSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqGenTests);

			
			i=0; 
			//For Updating  General Investigations
			for(LabelBean labelBean: lUpdateGenInvList){
				AttachmentVO attachmentVO=new AttachmentVO();
				//used to avoid deleted attachments-conflict
				while(chronicOpForm.getUpdateGenAttach(i)==null )
				{
					if(i==99)
					{
						break;
					}
					i++;
				}
				lFormFile=chronicOpForm.getUpdateGenAttach(i);  
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
							config.getString("SLASH_VALUE") + lStrChronicId + 
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
						GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in chronicopAction while saving general investigations " +e.getMessage()) ;
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
			ChronicOpVO.setUpdGenAttachmentsList(lUpdGenTestAttachList);  
			
			
			
			i=0;
			//For IP Therapy Investigations
			//ChronicOpVO.setCaseTherInvestSeqId(caseTheInvestSeqNextVal);
			for(LabelBean labelBean: lSelInvList){
				AttachmentVO attachmentVO=new AttachmentVO(); 
				//used to avoid deleted attachments-conflict
				while(chronicOpForm.getAttach(i)==null )
				{
					i++;
				}
				lFormFile=chronicOpForm.getAttach(i);                                     
				lFileName=lFormFile.getFileName();
				lCount=lFileName.lastIndexOf(".");
				lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
				// save file in folder's
				String lStrSharePath = 
						config.getString("STORAGE_BOX") + 
						/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
						config.getString("SLASH_VALUE") + lStrChronicId + 
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
					GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in chronicopAction." +e.getMessage()) ;
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
			ChronicOpVO.setAttachmentsList(lTestsAttachList); 

			//ehfmSeqCaseTherInvest.setSeqNextVal(Long.valueOf(caseTheInvestSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqCaseTherInvest);
            ChronicOpVO.setSaveFlag(saveFlg);
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
            			chronicOPService.deleteGenInvest(lStrChronicId,genInvestRem[i]);         
            		}
            	}
            }
			String chronicNo=chronicOPService.saveCaseDetails(ChronicOpVO);

			//chronicOpForm.setPatientType(ChronicOpVO.getPatientType());
			chronicOpForm.setChronicID(lStrChronicId);
			chronicOpForm.setChronicNo(chronicNo);
			
			if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit")){
				chronicOpForm.setMsg("Case Registered Successfully with Chronic ID :");
			}else{
				chronicOpForm.setMsg("Case Saved Successfully with Chronic ID :");
				if(checkType!=null)
				{
					chronicOpForm.setDisableFlag(checkType);
				}
			}
			if(chronicNo!=null && !chronicNo.equalsIgnoreCase(""))
			{
				
				
					if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit")){
					if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					{
						EhfChronicPatientDtl ehfChronicPatientDtl=null;
						ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(lStrChronicId);

						if(ehfChronicPatientDtl.getContactNo().toString()!=null && !ehfChronicPatientDtl.getContactNo().toString().equals(""))
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
							patientSmsVO.setPhoneNo(ehfChronicPatientDtl.getContactNo().toString());
							patientSmsVO.setSmsText("Dear "+ehfChronicPatientDtl.getName().trim()+" , You have been registered as "+ chronicOpForm.getPatientType()+" in "+patientService.getHospName(chronicOpForm.getHospitalId())+" with Chronic Id "+lStrCaseId);
							patientSmsVO.setEmpCode(ehfChronicPatientDtl.getEmployeeNo());
							patientSmsVO.setEmpName(ehfChronicPatientDtl.getName().trim());
							patientSmsVO.setCrtUsr(userId);
							patientSmsVO.setCrtDt(crtDt);
							patientSmsVO.setSmsAction("Case Registered as Chronic OP" );
							patientSmsVO.setPatientId(lStrChronicId);
							lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
						}
					}
					if (config.getBoolean("EmailRequired")) 
					{
						EhfChronicPatientDtl ehfChronicPatientDtl=null;
						ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(lStrChronicId);
						String mailId=null;
						if(ehfChronicPatientDtl.getEmailid()!=null && !ehfChronicPatientDtl.getEmailid().equals(""))
						{
							mailId=ehfChronicPatientDtl.getEmailid();
							String[] emailToArray = {mailId};
							EmailVO emailVO = new EmailVO();
							emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
							emailVO.setTemplateName(config.getString("EHFChronicPatientTemplateName"));
							emailVO.setFromEmail(config.getString("emailFrom"));
							emailVO.setToEmail(emailToArray);
							emailVO.setSubject(config.getString("patientEmailSubject"));
							Map<String, String> model = new HashMap<String, String>();
							model.put("chronicId",lStrChronicId);
							model.put("patientName", ehfChronicPatientDtl.getName().trim());
							model.put("registeredHosp", patientService.getHospName(chronicOpForm.getHospitalId()));
							model.put("status", "Case Registered as Chronic OP with Chronic No "+chronicNo);
							model.put("statusDate",crtDate);
							commonService.generateMail(emailVO, model);
						}
					}
					}						
				
			}
			request.setAttribute("saveFlag",saveFlg);
			lStrPageName="patient";
		}
		else
		{
			chronicOpForm.setErrMsg("Uploaded File Size Should not exceed 200KB");
			lStrPageName="patient"; 
		}
	
	}
	
	
	
	if("casePrintForm".equalsIgnoreCase(lStrActionVal))
	{
	
		String chronicId=null;String chronicNo=null;
		hospId=null;
		String chronicStatus=null;
		int inst=0;
		CommonDtlsVO commonDtlsVO=null;
		String type=request.getParameter("type");
		EhfChronicPatientDtl ehfChronicPatientDtl=null;
		PreauthVO patientData= new PreauthVO();
		String printFlag=request.getParameter("printFlag");
		String DTRS=request.getParameter("DTRS");
		String showAll=request.getParameter("showAll");
		String prescription=request.getParameter("prescription");
		System.out.println(printFlag);
		
		if(request.getParameter("chronicId")!=null && !request.getParameter("chronicId").equals(""))
		{
			chronicId=request.getParameter("chronicId");
			
		}
		

		if(request.getParameter("chronicId")!=null)
			{
				String chronicCaseScheme=chronicOPService.getChronicScheme(request.getParameter("chronicId"), chronicNo);
				request.setAttribute("chronicCaseScheme",chronicCaseScheme);
			}
		session.setAttribute("chronicId", chronicId);
		System.out.println(chronicId);
		
		//hospId=caseValues[1];

		List<CommonDtlsVO> commonDtls=chronicOPService.getPatientCommonDtls(chronicId);
		ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(chronicId);
		ChronicOPVO patientVO=new ChronicOPVO();
		ChronicOPVO opDtls=new ChronicOPVO();
		
		
		
		
		chronicOpForm.setCardNo(ehfChronicPatientDtl.getCardNo());
		chronicOpForm.setFname(ehfChronicPatientDtl.getName());
		if(ehfChronicPatientDtl.getCardIssueDt()!=null)
		{
			chronicOpForm.setCardIssueDt(sdf.format(ehfChronicPatientDtl.getCardIssueDt()));
		}
		else
			chronicOpForm.setCardIssueDt("NA");
		if(ehfChronicPatientDtl.getGender()!=null && "M".equalsIgnoreCase(ehfChronicPatientDtl.getGender()))
		{
			chronicOpForm.setGender("Male");
		}
		else if(ehfChronicPatientDtl.getGender()!=null && "F".equalsIgnoreCase(ehfChronicPatientDtl.getGender()))
		{
			chronicOpForm.setGender("Female");
		}
		if(ehfChronicPatientDtl.getEnrollDob()!=null)
		{
			chronicOpForm.setDateOfBirth(sdf.format(ehfChronicPatientDtl.getEnrollDob()));
		}
		if(ehfChronicPatientDtl.getAge()!=null)
		{
			chronicOpForm.setYears(ehfChronicPatientDtl.getAge().toString());
		}
		if(ehfChronicPatientDtl.getAgeMonths()!=null)
		{
			chronicOpForm.setMonths(ehfChronicPatientDtl.getAgeMonths().toString());
		}
		if(ehfChronicPatientDtl.getAgeDays()!=null)
		{
			chronicOpForm.setDays(ehfChronicPatientDtl.getAgeDays().toString());
		}
		String relationId=ehfChronicPatientDtl.getRelation();
		String relationName=patientService.getRelationName(relationId);
		chronicOpForm.setRelation(relationName);
		String casteId=ehfChronicPatientDtl.getCaste();
		if(casteId!=null && !casteId.equals(""))
		{
			String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
			chronicOpForm.setCaste(casteName);
		}
		if(ehfChronicPatientDtl.getContactNo()!=null && !ehfChronicPatientDtl.getContactNo().equals(""))
		{
			chronicOpForm.setContactno(ehfChronicPatientDtl.getContactNo().toString());
		}
		/*String occName = patientService.getOccupationName(ehfChronicPatientDtl.getOccupationCd());
    if(occName!=null && !occName.equalsIgnoreCase(""))*/
		chronicOpForm.setOccupation(ehfChronicPatientDtl.getOccupationCd());
	
		/*else
    	chronicOpForm.setOccupation("NA");*/
		//Setting slab
		String slabType=null;
		String slab=null;
		if(ehfChronicPatientDtl.getSlab()!=null)
		{
			slabType=ehfChronicPatientDtl.getSlab();
		}
		if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
			slab=config.getString("Slab.Name.SemiPrivateWard");
		else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
			slab=config.getString("Slab.Name.PrivateWard");
		chronicOpForm.setSlab(slab);
		//End Of Slab
		chronicOpForm.setHno(ehfChronicPatientDtl.getHouseNo());
		chronicOpForm.setStreet(ehfChronicPatientDtl.getStreet());
		chronicOpForm.setState(patientService.getLocationName(ehfChronicPatientDtl.getState()));
		String distCode=ehfChronicPatientDtl.getDistrictCode();
		String distName=patientService.getLocationName(distCode);
		chronicOpForm.setDistrict(distName);
		String mandalCode=ehfChronicPatientDtl.getMandalCode();
		String mandalName=patientService.getLocationName(mandalCode);
		chronicOpForm.setMandal(mandalName);
		String villageCode=ehfChronicPatientDtl.getVillageCode();
		String villageName=patientService.getLocationName(villageCode);
		chronicOpForm.setVillage(villageName);
		if(ehfChronicPatientDtl.getPinCode()!=null && !ehfChronicPatientDtl.getPinCode().equalsIgnoreCase(""))
			chronicOpForm.setPin(ehfChronicPatientDtl.getPinCode());
		else
			chronicOpForm.setPin("NA");
		//Setting Communication Address
	
		
		
		
			chronicOpForm.setComm_pin("NA");
		chronicOpForm.setDtTime(sdfw.format(ehfChronicPatientDtl.getRegHospDate()));
		String photoUrl=patientService.getPatientPhoto(ehfChronicPatientDtl.getCardNo());
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
				chronicOpForm.setPhotoUrl(photoUrl);
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in chronicopAction." +e.getMessage()) ;
			}
		}
		
		
		
		
		
		
		
		
		if(commonDtls!=null && commonDtls.size()>0)
		{
			commonDtlsVO=commonDtls.get(0);
		}
		if(commonDtlsVO!=null)
		{
			
			patientVO.setFirstName(commonDtlsVO.getPATIENTNAME());
			chronicOpForm.setPatientName(commonDtlsVO.getPATIENTNAME());
			patientVO.setSchemeId(commonDtlsVO.getScheme());
			String scheme=commonDtlsVO.getScheme();
			session.setAttribute("scheme",scheme);
			patientVO.setCardNo(commonDtlsVO.getCARDNO());
			patientVO.setDistrictCode(commonDtlsVO.getDISTRICT());
			patientVO.setMandalCode(commonDtlsVO.getMANDAL());
			patientVO.setVillageCode(commonDtlsVO.getVILLAGE());
			if(commonDtlsVO.getDate()!=null && !"".equals(commonDtlsVO.getDate()))
				patientVO.setOpDate(sdfw.format(commonDtlsVO.getDate()));
			patientVO.setAge(commonDtlsVO.getAGE());
			patientVO.setContactNo(commonDtlsVO.getCONTACT());
			patientVO.setChronicID(commonDtlsVO.getCHRONICID());
			patientVO.setRefHospNo(commonDtlsVO.getHOSPNAME());
			patientVO.setGender(commonDtlsVO.getGENDER());
			patientVO.setAddr1(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
			patientVO.setIpDate(commonDtlsVO.getPATDT());
			patientVO.setIpNo(commonDtlsVO.getIPNO());
			patientVO.setAdmissionType(commonDtlsVO.getADMTYPE());
			patientVO.setRegHospId(commonDtlsVO.getHOSPADDR());
			patientVO.setDoctorName(commonDtlsVO.getDOCNAME());
			patientVO.setDocMobileNo(commonDtlsVO.getDOCCONTACT());
			patientVO.setDocQual(commonDtlsVO.getDOCQUAL());
			patientVO.setDocRegNo(commonDtlsVO.getDOCREGNO());
			request.setAttribute("commonDtls", patientVO);
			String installment=chronicOPService.getChronicInstallment(chronicId);
			chronicNo=chronicId+"/"+installment;
		    inst=Integer.parseInt(installment);
			
			patientData=chronicOPService.getPatientFullDtls(chronicId,chronicNo,showAll);			    
				//patientService.getPatientOpDtls(chronicId,commonDtlsVO.getPATID());
			
			 
			 if(patientData!=null)
				{
					opDtls.setAddr1(patientData.getTemperature());
					opDtls.setAddr2(patientData.getPulseRate());
					opDtls.setAddr3(patientData.getRespirationRate());
					opDtls.setAdmissionType(patientData.getBpLmt());
					opDtls.setAge(patientData.getBpRmt());
					
					System.out.println(patientData.getPastIllness());
					System.out.println(patientData.getFamilyHis());
					

					if(patientData.getPastIllness()!=null && !"".equalsIgnoreCase(patientData.getPastIllness()))
					{
						String rmrks="";
						if(patientData.getPastHisOthers()!=null && !"".equalsIgnoreCase(patientData.getPastHisOthers()))
						rmrks=commonService.getPastIllnessHitoryNames(patientData.getPastIllness())+" - "+patientData.getPastHisOthers();
						else
						rmrks=commonService.getPastIllnessHitoryNames(patientData.getPastIllness());	
						opDtls.setAgeDays(rmrks);
					}
					
					if(patientData.getFamilyHis()!=null && !"".equalsIgnoreCase(patientData.getFamilyHis()) )
					{
						String rmrks="";    
						    if(patientData.getFamilyHisOthers()!=null && !"".equalsIgnoreCase(patientData.getFamilyHisOthers()))
							rmrks=commonService.getFamilyHisNames(patientData.getFamilyHis())+" - "+patientData.getFamilyHisOthers();
							else
							rmrks=commonService.getFamilyHisNames(patientData.getFamilyHis());	
						    opDtls.setAgeMonths(rmrks);
					}
					
					opDtls.setAsriCatCode(patientData.getSymName());
					opDtls.setAsriDrugCode(patientData.getComplaintName());
					opDtls.setAsriProcCode(patientData.getComplaintTypeName());
					opDtls.setBiometricVal(patientData.getPresentIllness());
					opDtls.setC_district_code(patientData.getPastIllnessValue());
					opDtls.setC_hamlet_code(patientData.getFamilyHistValue());
					opDtls.setC_mandal_code(patientData.getFamilyHistoryOthr());
					opDtls.setC_mdl_mpl(patientData.getPastIllenesOthr());
					opDtls.setC_phc_code(patientData.getRemarks());
					opDtls.setOpCatName(patientData.getOpCatName());
					opDtls.setOpPackageName(patientData.getOpPackageName());
					opDtls.setOpPkgCode(patientData.getOpPkgCode());
					opDtls.setOpIcdCode(patientData.getOpIcdCode());
					opDtls.setOpIcdName(patientData.getOpIcdName());
					opDtls.setAllergy(patientData.getTestKnown());
					opDtls.setHabbits(patientData.getTest());
				}

			chronicOpForm.setComplaints(patientData.getComplaintType());
		    chronicOpForm.setComplaintCode(patientData.getComplaintType());
		    chronicOpForm.setPatComplaintCode(patientData.getPatComplaint());
		    chronicOpForm.setPresentHistory(patientData.getPresentIllness());
			if(patientData.getPersonalHis()!=null && !patientData.getPersonalHis().equalsIgnoreCase(""))
		    {
			String[]  persHist = patientData.getPersonalHis().split("~");
		    chronicOpForm.setPersonalHist(persHist);
			}
			if(patientData.getExamFindg()!=null && !patientData.getExamFindg().equalsIgnoreCase(""))
		    {
			String[] examfndList = patientData.getExamFindg().split("~");
		    chronicOpForm.setExaminationFnd(examfndList);
			}
		    
		    	chronicOpForm.setOpNo(patientData.getPatientIPNo());
		    	chronicOpForm.setOpRemarks(patientData.getRemarks());
		    	chronicOpForm.setDiagnosedBy(patientData.getDocType());	
		    	
		    	
		    	
		    	if(patientData.getDrugList()!=null && patientData.getDrugList().size()>0){
		    	chronicOpForm.setDrugLt(patientData.getDrugList());
		    	String drugLst="";
		    	for(DrugsVO drugVO: patientData.getDrugList()){
		    		String expiryDt="";
		    		if(drugVO.getExpDt()!=null)
		    		{
		    		DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		    		Date expDt=drugVO.getExpDt();
		    		expiryDt=df.format(expDt);
		    		}
		    		if(("Y").equals(showAll))
		    		{
		    		drugLst = drugLst+"~"+drugVO.getDrugCode()+"~"+drugVO.getDosage()+"~"+drugVO.getMedicationPeriod()+"~"+drugVO.getBatchNo()+"~"+expiryDt+"~"+drugVO.getInstallment()+"@#";
		    		}
		    		else
		    		{
		    			drugLst = drugLst+"~"+drugVO.getDrugCode()+"~"+drugVO.getDosage()+"~"+drugVO.getMedicationPeriod()+"~"+drugVO.getBatchNo()+"~"+expiryDt+"@#";	
		    		}
		    	}
				request.setAttribute("drugsList",patientData.getDrugList());
		    	}
		    	  
		    	
		    	/*to get general investigations*/
		    	
		    	List<LabelBean> genInvList = new ArrayList<LabelBean>();
		    	  String genInvestLst="";
					if(patientData.getInvList()!=null && patientData.getInvList().size()>0){
						for(PreauthVO preauthVo : patientData.getInvList()){
							LabelBean inv = new LabelBean();
							inv.setID(preauthVo.getSPLINVSTID());
							inv.setVALUE(preauthVo.getNAME());
							inv.setLVL(preauthVo.getROUTE());
							inv.setACTION(preauthVo.getTEST());
							genInvestLst = 
								genInvestLst+preauthVo.getTESTKNOWN()+"~"+preauthVo.getSPLINVSTID()+"~"+preauthVo.getNAME()+"@";
							genInvList.add(inv);
						}
						
						chronicOpForm.setGenInvList(genInvList);
						request.setAttribute("genInvestFlag", "true");
						request.setAttribute("genInvestLst", patientData.getInvList());
					}
		    	}
		    
		   
			request.setAttribute("serverDt",serverDt);
			request.setAttribute("PatientOpList",patientData);
			/*PreauthVO othrDtls=chronicOPService.getPatientFullDtls(chronicId);
				//patientService.getOtherDtls(chronicId, commonDtlsVO.getPATID());
			
			chronicOpForm.setInvestigationLt(othrDtls.getInvestigations());
			chronicOpForm.setDiagType(othrDtls.getDiagnosisType());
			chronicOpForm.setMainCatName(othrDtls.getMainCatName());
			chronicOpForm.setCatName(othrDtls.getCatName());
			chronicOpForm.setSubCatName(othrDtls.getSubCatName());
			chronicOpForm.setDiseaseName(othrDtls.getDisName());
			chronicOpForm.setDisAnatomicalName(othrDtls.getDisAnatomicalSitename());
		

			List<PreauthVO> procDtls=patientService.getcaseSurgeryDtls(chronicId);
			chronicOpForm.setProcList(procDtls);
			chronicOpForm.setCaseId(chronicId);
			request.setAttribute("decFlag", request.getParameter("decFlag"));*/
			
					//List<DrugsVO> drugs=patientService.getDrugs(commonDtlsVO.getPATID(),"op");
					//chronicOpForm.setDrugLt(drugs);
			String mithraName = patientService.getEmpNameById(commonDtlsVO.getMithra());
			if(mithraName!=null && !mithraName.equalsIgnoreCase(""))
				chronicOpForm.setMithra(mithraName);
			
			if(("Y").equals(DTRS) || ("Y").equalsIgnoreCase(prescription))
			{
			chronicOpForm.setDoctorName(patientService.getEmpNameById(lStrUserId));
			}
			else
			{
					String doctorName="";
					
					if(commonDtlsVO!=null)
					{
					if(commonDtlsVO.getDoctType()!=null && !" ".equalsIgnoreCase(commonDtlsVO.getDoctType()) )
					{
					if("Consultant".equalsIgnoreCase(commonDtlsVO.getDoctType()) || "InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
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
					if(doctorName!=null && !doctorName.equalsIgnoreCase("")){
						chronicOpForm.setDoctorName(doctorName);
					opDtls.setDoctorName(doctorName);
	}
					if(doctorName==null || doctorName.equalsIgnoreCase("") )
					{
						chronicOpForm.setDoctorName("-NA-");
						opDtls.setDoctorName("-NA-");
					}
					}
					chronicOpForm.setDiagnosedBy(commonDtlsVO.getDoctType());
					}
			}
					
			
			if(type!=null && ("EoView").equalsIgnoreCase(type))
			{
				request.setAttribute("roleId",config.getString("EHF.Claims.CEO"));
				request.setAttribute("chronicNo",chronicNo);
				chronicStatus=chronicOPService.getChronicStatus(chronicId,chronicNo);
				request.setAttribute("chronicStatus",chronicStatus);
				
			}
			/*added for getting audit trail*/
		 	
		 	List<ClaimsFlowVO> lstWorkFlow = chronicOPService.getAuditTrail(chronicNo);
		 	chronicOpForm.setLstworkFlow(lstWorkFlow);
		 	
					request.setAttribute("printFlag", printFlag);
					request.setAttribute("DTRS", DTRS);
					request.setAttribute("allDtls", opDtls);
					request.setAttribute("type", type);
					request.setAttribute("showAll", showAll);
					request.setAttribute("inst",inst);
					request.setAttribute("instDrug",inst);
					if(prescription!=null && ("Y").equalsIgnoreCase(prescription))
						lStrPageName="chronicOPPrescription";
					else
					    lStrPageName="chronicOpcaseCopy";
				
			}
	
	
	if ("viewScreenedCases".equalsIgnoreCase(lStrActionVal))
	{
		String chronicId=null;
		String disableFlag=request.getParameter("disableFlag");
		String caseApprovalFlag=request.getParameter("caseApprovalFlag");
		String clinical=request.getParameter("clinicalNotes");
		String pendingFlag=request.getParameter("pendingFlag");
		String chronicNo=null;
		session.setAttribute("pendingFlag", pendingFlag);
		session.setAttribute("caseApprovalFlag", caseApprovalFlag);
		session.setAttribute("disableFlag", disableFlag);
		if(request.getParameter("chronicNo")!=null && !request.getParameter("chronicNo").equals(""))
		{
			chronicNo=request.getParameter("chronicNo");
			/*int occurance = StringUtils.countOccurrencesOf(chronicId, "/");
			if(occurance==3)
			{
				chronicId=chronicId.substring(0, chronicId.lastIndexOf('/'));	
			
			}*/
			chronicId=chronicNo.substring(0,chronicNo.length()-2);
		}
		String installment=null;
		
		String dayStatus=null;
		String expiredFlag=null;
		installment=chronicOPService.getChronicInstallment(chronicId);
		//chronicNo=chronicId+"/"+installment;
        String chronicStatus= chronicOPService.getChronicStatus(chronicId,chronicNo);
		
        if(("Y").equals(disableFlag))
        {
        Number diff=chronicOPService.getDaysBetween(chronicId, chronicNo);
        int inst=Integer.parseInt(installment);
        float reqDiff=0;
		if(inst>=1 && inst<=3){
        reqDiff=((365/4)*inst);  }  /*to be enabled before 15 days in a quarter(condition removed)*/
		if(diff!=null && reqDiff>0 ){
			
			if(diff.intValue()>365)
			{
				expiredFlag="Y";
				
			}
			
			else if(diff.floatValue()>=reqDiff )
		    {
			dayStatus="Y";
			}
			
			
		   else
		    {
			Number days=reqDiff-diff.intValue();		
			DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();    
			c.setTime(new Date());
			c.add(Calendar.DATE, days.intValue());
			System.out.println(dateFormat.format(c.getTime()));
			String day=dateFormat.format(c.getTime());
			
			request.setAttribute("days",day);
			dayStatus="N";
		}
		}
	if(("Y").equals(expiredFlag))
	{
		request.setAttribute("expiredFlag", expiredFlag);
	}
		else if(("GP2").equalsIgnoreCase(roleId)&&("Y").equalsIgnoreCase(clinical)&&("Y").equalsIgnoreCase(dayStatus))
		{
			request.setAttribute("clinicalNotes", "Y");
		}
		
		else if(("GP2").equalsIgnoreCase(roleId)&&("Y").equalsIgnoreCase(clinical)&&("N").equalsIgnoreCase(dayStatus))
		{

		request.setAttribute("inactiveFlag", "Y");
		}
		
		else if(("GP2").equalsIgnoreCase(roleId)&&("Y").equalsIgnoreCase(clinical)&& dayStatus==null)
		{
		
			request.setAttribute("workflowFlag", "Y");
		}
        }
		session.setAttribute("chronicId",chronicId);
		session.setAttribute("chronicNo",chronicNo);
		lStrPageName="chronicDisplayFrame";
	
	}
	
	if ("intiateFollowUP".equalsIgnoreCase(lStrActionVal))
	{
		
		lStrPageName="intiateFollowUP";
	}
	
	if ("chronicOpDtls".equalsIgnoreCase(lStrActionVal))
	{
		String chronicId=(String) session.getAttribute("chronicId");
		String installment=null;
		String chronicNo=null;
		installment=chronicOPService.getChronicInstallment(chronicId);
		if((String) session.getAttribute("chronicNo")!=null)
		{
			chronicNo=(String) session.getAttribute("chronicNo");	
		}
		else
		{
			chronicNo=chronicId+"/"+installment;	
		}
		 
		session.setAttribute("chronicNo",chronicNo);
		session.setAttribute("chronicId",chronicId);
		session.setAttribute("installment",installment);
		lStrPageName="chronicOpDtls";
	
	}
	
	
	if ("saveChronicClaim".equalsIgnoreCase(lStrActionVal))
	{
		
		String chronicNo=(String) session.getAttribute("chronicNo");
		String chronicId=(String) session.getAttribute("chronicId");
		long pkgAmount=chronicOpForm.getPackageAmt();
		long claimAmt=0;
		String actionDone=null;
		String remarks=null;
		
		actionDone=request.getParameter("actionDone");
		lStrUserRole = (String) session.getAttribute("UserRole");
		String lStrUserGroup=null;
		String userGrpType="chronicGroup_";
		roleId=chronicOpForm.getRoleId();
		String chronicStatus= chronicOPService.getChronicStatus(chronicId,chronicNo);
		
		
		
		 for(LabelBean labelBean:rolesList)
	 	{
			 lStrUserGroup = config.getString(userGrpType+chronicStatus);
	 	if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(lStrUserGroup) || 
	 				(lStrUserGroup!=null && lStrUserGroup.contains("~"+labelBean.getID()+"~"))))
	 	{
	 		lStrUserGroup= labelBean.getID();
	 		break;	
	 	}
	 	else
	 		lStrUserGroup = null;
	 	}
	 	if(rolesList == null || rolesList.isEmpty() )
	 	{
	 		lStrUserGroup = null;	
	 	}
		
		
	
		
		
	 	ChronicOPVO chronicOPVO=new ChronicOPVO();
		
		/*getting claim amount*/ 
		if(lStrUserGroup!=null && (config.getString("Chronic.Mithra")).equalsIgnoreCase(lStrUserGroup))
		{
		if(chronicOpForm.getClaimNamAmt()>0)
			claimAmt=chronicOpForm.getClaimNamAmt();
		}
		
		else if(lStrUserGroup!=null && (config.getString("Chronic.Medco")).equalsIgnoreCase(lStrUserGroup))
		{
		 if(chronicOpForm.getClaimNwhAmt()>0)
			claimAmt=chronicOpForm.getClaimNwhAmt();
		}
		
		else if(lStrUserGroup!=null && (config.getString("Group.COEX")).equalsIgnoreCase(lStrUserGroup))
		{
		 if(chronicOpForm.getClaimFcxAmt()>0)
			claimAmt=chronicOpForm.getClaimFcxAmt();
		}
		
		else if(lStrUserGroup!=null && (config.getString("Group.COTD")).equalsIgnoreCase(lStrUserGroup))
		{
		 if(chronicOpForm.getClaimFtdAmt()>0)
			claimAmt=chronicOpForm.getClaimFtdAmt();
		}
		
		else if(lStrUserGroup!=null && (config.getString("Group.COCH")).equalsIgnoreCase(lStrUserGroup))
		{
		 if(chronicOpForm.getClaimChAmt()>0)
			claimAmt=chronicOpForm.getClaimChAmt();
		}
		
		else if(lStrUserGroup!=null && (config.getString("GROUP.COACO")).equalsIgnoreCase(lStrUserGroup))
		{
		 if(chronicOpForm.getAcoAprAmt()>0)
			claimAmt=chronicOpForm.getAcoAprAmt();
		}
		
		else 
			claimAmt=chronicOpForm.getClaimAmt();
		
		
		/*getting remarks*/ 
		
		if(chronicOpForm.getMedcoRemarks()!=null)
			remarks=chronicOpForm.getMedcoRemarks();
		else if(chronicOpForm.getNamRemarks()!=null)
			remarks=chronicOpForm.getNamRemarks();
		else if(chronicOpForm.getFcxRemark()!=null)
			remarks=chronicOpForm.getFcxRemark();
		else if(chronicOpForm.getFtdRmks()!=null)
			remarks=chronicOpForm.getFtdRmks();
		else if(chronicOpForm.getChRemark()!=null)
			remarks=chronicOpForm.getChRemark();
		else if(chronicOpForm.getAcoRemark()!=null)
		{
			remarks=chronicOpForm.getAcoRemark();
			
			
		}
		else
			remarks=null;
	
		
		/*setting in commonDtlsVo*/
		
		chronicOPVO.setChronicID(chronicId);
		chronicOPVO.setChronicNo(chronicNo);
		chronicOPVO.setChronicStatus(chronicStatus);
		chronicOPVO.setPKGAMOUNT(pkgAmount);
		chronicOPVO.setClaimAmt(claimAmt);
		chronicOPVO.setActionDone(actionDone);
		chronicOPVO.setDate(crtDt);
		chronicOPVO.setEMPLOYEENO(lStrUserId);
		chronicOPVO.setUserGroup(lStrUserGroup);
		chronicOPVO.setRemarks(remarks);
		
		/*generating claim number at mithra level*/
		
		if(roleId.equalsIgnoreCase(config.getString("Chronic.Mithra"))){
			
			
			
		}
		
		
		/*saving details for COEX*/
		
		if(roleId.equalsIgnoreCase(config.getString("Group.COEX"))){
		
		chronicOPVO.setCoexFlag("Y");	
		chronicOPVO.setExeDisphotoChklst(chronicOpForm.getExeDisphotoChklst());
		chronicOPVO.setExePatphotoChklst(chronicOpForm.getExePatphotoChklst());
		chronicOPVO.setExeDisphotoremark(chronicOpForm.getExeDisphotoremark());
		chronicOPVO.setExeAcqvrifyChklst(chronicOpForm.getExeAcqvrifyChklst());
		chronicOPVO.setExeAcqverifyRemark(chronicOpForm.getExeAcqverifyRemark());
		chronicOPVO.setExePatphotoRemark(chronicOpForm.getExePatphotoRemark());
		chronicOPVO.setExereprtcheckChklst(chronicOpForm.getExereprtcheckChklst());
		chronicOPVO.setExeReprtcheckRemark(chronicOpForm.getExeReprtcheckRemark());
		
		}
		
		
		
		/*saving details for cotd*/
		
		if(roleId.equalsIgnoreCase(config.getString("Group.COTD"))){
			
		chronicOPVO.setCotdFlag("Y");	
		chronicOPVO.setFtdRemarkvrifedChklst(chronicOpForm.getFtdRemarkvrifedChklst());
		chronicOPVO.setClaimAmt(chronicOpForm.getClaimFtdAmt());
		//chronicOPVO.setFtdBeneficiryChklst(chronicOpForm.getFtdBeneficiryChklst());
		//chronicOPVO.setFtdBeneficiryRemark(chronicOpForm.getFtdBeneficiryRemark());
		chronicOPVO.setFtdRemarkvrifedRemark(chronicOpForm.getFtdRemarkvrifedRemark());
		}
		
		if(roleId.equalsIgnoreCase(config.getString("Group.COCH"))){
			
			
			chronicOPVO.setClaimAmt(chronicOpForm.getClaimChAmt());
		}
		
        
		
		
		msg=chronicOPService.saveChronicClaim(chronicOPVO);
		System.out.println(msg);
		if(msg!=null)
		{
		lStrActionVal="claims";
		chronicOpForm.setUserRole(lStrUserGroup) ;
		chronicOpForm.setClaimSubmit("Yes");
		//lStrActionVal="claims";
		request.setAttribute("msg", msg);
		}
		
	}
	if(lStrActionVal!=null && "COClaimCases".equalsIgnoreCase(lStrActionVal))
	{
		
		List<ChronicOPVO> chrList1=null;
		List<ChronicOPVO> chrList=null;

		int  totalRecords=1;
		int pageNo=1;
	    int  showPage=1;
		int rowsPerPage=10;
		int pages=1;
		int startIndex=1;
		int endIndex=5;
		int startPage=1;
		int endPage=10;
		
		if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search").toString())) &&  "search".equalsIgnoreCase(request.getParameter("search").toString()) ){
			
			 chrList1=chronicOPService.searchChronicClaimCases(request.getParameter("chronicId"), request.getParameter("PatName"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDt"),request.getParameter("toDate"),request.getParameter("userState"),hospId,roleId);
				
			 
					chronicOpForm.setChronincOPPatList(chrList1);
					
				
				
			}else{
				
				 chrList=chronicOPService.getChronicClaimCases(roleId,hospId,userState);
				
									
					chronicOpForm.setChronincOPPatList(chrList);
					
				}
					
		
		
		if(chronicOpForm.getChronincOPPatList()!=null && chronicOpForm.getChronincOPPatList().size()>0){
			
			totalRecords=chronicOpForm.getChronincOPPatList().size();
			
		}
     if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
			
			rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
		}
     
     if(rowsPerPage==0){
    	 
    	 rowsPerPage=10;
     }
		if(totalRecords>rowsPerPage){
			
			
			if((totalRecords%rowsPerPage)==0){
				pages=totalRecords/rowsPerPage;
				
			}else{
				
				pages=(totalRecords/rowsPerPage)+1;
			}
			
		}else{
			
			pages=1;
		}
		
		if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
			
			
			 if(pages>10 && showPage<=10){
					
					request.setAttribute("next", "next");
					chronicOpForm.setNext("next");
					
				}
	              if(pages>10 && (showPage-10)>1){
					
					request.setAttribute("prev", "prev");
					chronicOpForm.setPrev("prev");
					
	              }
			
			
			
		}
		
  
		if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
			
				String str=request.getParameter("pageNo");				
			showPage=Integer.parseInt(str);
			
			startIndex=((showPage-1)*rowsPerPage)+1;
			
			if((showPage*rowsPerPage)<totalRecords){
				
				
				endIndex=showPage*rowsPerPage;
				
			}else{
				
				endIndex=totalRecords;
				
			}
			
			
           int x=chronicOpForm.getStartPage();
			
			int y=chronicOpForm.getEndPage();
			
			if(y>pages){
			y=pages;
			}
			
			if(pages>10 && pages>y){
				
				
				request.setAttribute("next", "next");
				chronicOpForm.setNext("next");
				
			}
			
			if(pages>10 && x>10){
				request.setAttribute("prev", "prev");
				chronicOpForm.setPrev("prev");
			}
			
			
			startPage=x;
			endPage=y;
			
			
		}
		
		if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
			
			int x=chronicOpForm.getStartPage();
			
			int y=chronicOpForm.getEndPage();
			if(x>=10 && pages>10){
				
				request.setAttribute("next", "next");
				chronicOpForm.setNext("next");
				
			}
			
			if(x-10>10 && pages>10){
				
				request.setAttribute("prev", "prev");
				chronicOpForm.setPrev("prev");
			}
			
			if(showPage==1){
				
				if(totalRecords>rowsPerPage){
					
					startIndex=1;
					endIndex=rowsPerPage;
				}else{
				
					startIndex=(showPage-1)*rowsPerPage+1;
					if((startIndex+rowsPerPage)<totalRecords){
						
						endIndex=startIndex+rowsPerPage;
					}else{
						
						endIndex=totalRecords;
					}
					
					
				}
			}
			startPage=x-10;
			endPage=x-1;
			
			
			chronicOpForm.setStartPage(startPage);
			chronicOpForm.setEndPage(endPage);
			
startIndex=((showPage-1)*rowsPerPage)+1;
			
			if((showPage*rowsPerPage)<totalRecords){
				
				
				endIndex=showPage*rowsPerPage;
				
			}else{
				
				endIndex=totalRecords;
				
			}
		}
		
		
		if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
			
            int x=chronicOpForm.getStartPage();
			
			int y=chronicOpForm.getEndPage();
		
			
			startPage=y+1;
			endPage=y+10;
			if(endPage>pages){
				
				endPage=pages;
			}
			
			if(pages>startPage+10){
				request.setAttribute("next", "next");
				chronicOpForm.setNext("next");
				
			}
			
			if(startPage-10>=1){
				
				request.setAttribute("prev", "prev");
				chronicOpForm.setPrev("prev");
			}
			chronicOpForm.setStartPage(startPage);
			chronicOpForm.setEndPage(endPage);
			showPage=startPage;
          startIndex=((showPage-1)*rowsPerPage)+1;
			
			if((showPage*rowsPerPage)<totalRecords){
				
				
				endIndex=showPage*rowsPerPage;
				
			}else{
				
				endIndex=totalRecords;
				
			}
		
			
			
			
		}
		
		if(showPage==1){
			
			if(totalRecords>rowsPerPage){
				
				startIndex=1;
				endIndex=rowsPerPage;
			}else{
			
				startIndex=(showPage-1)*rowsPerPage+1;
				if((startIndex+rowsPerPage)<totalRecords){
					
					endIndex=startIndex+rowsPerPage;
				}else{
					
					endIndex=totalRecords;
				}
				
				
			}
		}
		startIndex=startIndex-1;
				List<ChronicOPVO> chrList3=new ArrayList<ChronicOPVO>();
				if( (request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search"))) && request.getParameter("search").equalsIgnoreCase("search")){
					
					if(chrList1!=null && chrList1.size()>0){
					for(int i=startIndex;i<endIndex;i++){
						
						ChronicOPVO vo=new ChronicOPVO();
						vo.setChronicSubID(chrList1.get(i).getChronicSubID());
						vo.setChronicID(chrList1.get(i).getChronicID());
						vo.setName(chrList1.get(i).getName());
						vo.setEmployeeNo(chrList1.get(i).getEmployeeNo());
						vo.setAge(chrList1.get(i).getAge());
						vo.setDistrict(chrList1.get(i).getDistrict());
						vo.setGender(chrList1.get(i).getGender());
						vo.setRegistrationDate(chrList1.get(i).getRegistrationDate());
						chrList3.add(vo);
						
					}
					
					}
				}else{
					
					if(chrList!=null && chrList.size()>0){
	                    for(int j=startIndex;j<endIndex;j++){
						
	                    	ChronicOPVO vo=new ChronicOPVO();
						vo.setChronicID(chrList.get(j).getChronicID());
						vo.setChronicSubID(chrList.get(j).getChronicSubID());
						vo.setName(chrList.get(j).getName());
						vo.setEmployeeNo(chrList.get(j).getEmployeeNo());
						vo.setAge(chrList.get(j).getAge());
						vo.setDistrict(chrList.get(j).getDistrict());
						vo.setGender(chrList.get(j).getGender());
						vo.setRegistrationDate(chrList.get(j).getRegistrationDate());
						chrList3.add(vo);
						
					}
					}
					
				}
						
				chronicOpForm.setChronincOPPatList(chrList3);
				request.setAttribute("liPageNo",showPage);
				if(chrList3.size()==0){
					request.setAttribute("startIndex", 0);
				}else{
					
					request.setAttribute("startIndex", startIndex+1);
				}
				if(chrList3.size()==0){
					request.setAttribute("endIndex", 0);
				}else{
					request.setAttribute("endIndex", endIndex);
					
				}
				
				request.setAttribute("rowsPerPage", rowsPerPage);
				request.setAttribute("pages", pages);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("endPage", endPage);
				if(chrList3.size()==0){
					
					request.setAttribute("totalRecords", 0);
				}else{
					request.setAttribute("totalRecords", totalRecords);
				}
				
			
		
		lStrPageName="coClaimCases";	
		
	}
	
	
	
	if ("claims".equalsIgnoreCase(lStrActionVal))
	{
      lStrUserRole = (String) session.getAttribute("UserRole");
      String approvalFlag=(String) session.getAttribute("caseApprovalFlag");
      String installment= (String) session.getAttribute("installment");
      String chronicId=(String) session.getAttribute("chronicId");
      String chronicNo=null;
      if((String) session.getAttribute("chronicNo")!=null)
		{
			chronicNo=(String) session.getAttribute("chronicNo");	
		}
		else
		{
			chronicNo=chronicId+"/"+installment;	
		}
      
  	 
      System.out.println(lStrUserRole);
      String scheme=(String) session.getAttribute("scheme");
      String userRole=chronicOpForm.getUserRole();
      String claimSubmit=chronicOpForm.getClaimSubmit();
      /*added for chronic op*/
      String disableFlag=(String) session.getAttribute("disableFlag");
	  
	chronicOpForm.setChronicID(chronicId);
	if(request.getParameter("chronicId")!=null)
		{
			String chronicCaseScheme=chronicOPService.getChronicScheme(request.getParameter("chronicId"), chronicNo);
			request.setAttribute("chronicCaseScheme",chronicCaseScheme);
		}
	else
		{
			String chronicCaseScheme=chronicOPService.getChronicScheme(chronicId, chronicNo);
			request.setAttribute("chronicCaseScheme",chronicCaseScheme);
		}
	
	//String chronicFollowUpId=request.getParameter("chronicFollowUpId");
	//chronicOpForm.setClaimNwhAmt(0);
	chronicOpForm.setMedcoRemarks(ClaimsConstants.EMPTY);
	
	String lStrCaseStatus=null;
	String chronicStatus=null;
	String lStrUserGroup=null;
	String userGrpType=null;
	long pkgAmount=0;
	long claimAmt=0;
	boolean attachStatus=false;
	boolean claimInit=false;
	String showFlag=null;

	/*added for chronic op*/
	 if(request.getParameter("chronicId") != null && !request.getParameter("chronicId").equalsIgnoreCase(""))
	    	{
   	
		 	request.setAttribute("chronicId", request.getParameter("chronicId"));
		    
		 	//hParamMap.put("chronicId",request.getParameter("chronicId"));
	    	lStrCaseStatus = chronicOPService.getChronicStatus(request.getParameter("chronicId"),chronicNo);
	    	chronicStatus=lStrCaseStatus;
	    	request.setAttribute("chronicStatus",chronicStatus);
	    	if(chronicStatus!=null && (config.getString("COTD-REC-PEN").equalsIgnoreCase(chronicStatus) || config.getString("COCH-PEN").equalsIgnoreCase(chronicStatus) || config.getString("CO-CLAIM-SENT-BACK-TO-CH").equalsIgnoreCase(chronicStatus) ))
	    	{
	    		request.setAttribute("updateFlag","Y");
	    	}
	    	userGrpType="chronicGroup_";
	    	
	    	
   	}
	 
	 
	 if(("Yes").equals(claimSubmit))
	 {
		 lStrUserGroup=chronicOpForm.getUserRole();
	 }
	
	 else
	 {
	 for(LabelBean labelBean:rolesList)
 	{
		 lStrUserGroup = config.getString(userGrpType+lStrCaseStatus);
		
		/* if(labelBean.getID().equalsIgnoreCase(config.getString("Chronic.Medco")))
	 		{
	 			showFlag="Y";
	 			//request.setAttribute("disableAll",ClaimsConstants.YES);
	 			lStrUserGroup=config.getString("Chronic.Medco");
	 		}*/
		 request.setAttribute("showUserNames","Y");
		 if(labelBean.getID()!=null && (config.getString("Chronic.Medco").equalsIgnoreCase(labelBean.getID()) ||  config.getString("Chronic.Mithra").equalsIgnoreCase(labelBean.getID())))
		 	{
		 		request.setAttribute("showUserNames","N");
		 	}
		 
 	if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(lStrUserGroup) || 
 				(lStrUserGroup!=null && lStrUserGroup.contains("~"+labelBean.getID()+"~"))))
 	{
 		
 		lStrUserGroup= labelBean.getID();
 		
 		
 		
 		break;	
 		
 		
 		
 	}
 	
 	
 	
 	
 	else
 		lStrUserGroup = null;
 	}
	 
	 }
	 if(!("Y").equals(approvalFlag))
	 {
		 showFlag="Y";
		 lStrUserGroup = null;
		 
	 }
	 if(("Y").equals(disableFlag) && showFlag==null )
	 {
		 lStrUserGroup=null;
		 request.setAttribute("disableAll",ClaimsConstants.YES);
	 }
 	if(rolesList == null || rolesList.isEmpty() )
 	{
 		lStrUserGroup = null;	
 	}
 	
 	
 	
 	if(lStrUserGroup == null && showFlag==null)
 	{
 		request.setAttribute("disableAll",ClaimsConstants.YES);
 	//lStrPageName="noAuthorizationPage";
 		}
 
 	
 	/*added for enabling fields in claims tab based on roles*/
 	
 	if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("Chronic.Mithra")))
 	{
 		chronicOpForm.setShowNam("Yes");
 		chronicOpForm.setShowMedco("Yes");
 		
 	}

 	else if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("Chronic.Medco")))
 	{
 		chronicOpForm.setShowMedco("Yes");
 	}

 	else if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("Group.COEX")))
 	{
 		chronicOpForm.setShowFcx("Yes");
 		
 	}

 	else if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("Group.COTD")))
 	{
 		chronicOpForm.setShowFtd("Yes");
 	
 	}

 	else if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("Group.COCH")))
 	{
 		chronicOpForm.setShowCh("Yes");
 		
 	}

 	else if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase(config.getString("GROUP.COACO")))
 	{
 		chronicOpForm.setShowAco("Yes");
 	
 	}

 	else
 	{
 		request.setAttribute("disableAll",ClaimsConstants.YES);
 	}
 	
 	
 	
 			
 	chronicOpForm.setRoleId(lStrUserGroup);
 	request.setAttribute("UserRole1",lStrUserGroup);
 	
 	/*added for checking whether claim is already initiated for case*/
 	
 	/*claimInit=chronicOPService.validateChronicClaim(chronicId, chronicNo);
 	if(claimInit)
 	{
 		session.setAttribute("claimInit",ClaimsConstants.YES);
 		request.setAttribute("disableAll",ClaimsConstants.YES);
 	}*/
 	
 	/*added for validating attachments*/
 	if(("Y").equalsIgnoreCase(approvalFlag)){
 	attachStatus=chronicOPService.validateAttachments(chronicNo, chronicStatus, lStrUserGroup);
	 	if(attachStatus)
	 	{
	 	request.setAttribute("attachMsg","yes");
	 	/*added for calculating package amount*/
	
	 	
	 	}
	 	else
	 		request.setAttribute("attachMsg","no");
	 
 	}
	pkgAmount=chronicOPService.getCasePackageAmount(chronicId,chronicNo);
 	chronicOpForm.setPackageAmt(pkgAmount);
 	request.setAttribute("packageAmt", pkgAmount);
 	/*added for getting audit trail*/
 	
 	List<ClaimsFlowVO> lstWorkFlow = chronicOPService.getAuditTrail(chronicNo);
 	chronicOpForm.setLstworkFlow(lstWorkFlow);
 	
 	int size=lstWorkFlow.size();
 	if(size>0)
 	{
 	claimAmt=(Long) lstWorkFlow.get(size-1).getCOUNT();
 	chronicOpForm.setClaimAmt(claimAmt);
 	}
 	else
 		claimAmt=0;
 	request.setAttribute("claimAmt",claimAmt);
 	
 	if(!("Y").equalsIgnoreCase(approvalFlag) && size==0)
 	{
 		
 		request.setAttribute("claimMsg","N");
 		lStrPageName="chronicOpclaimForm";
 	}
 	
 	if(size>0)
 	{
 	for(ClaimsFlowVO claimsFlowVO:lstWorkFlow)
 	{
 	
 		if(claimsFlowVO.getRoleId()!=null &&claimsFlowVO.getRoleId().equalsIgnoreCase(config.getString("Chronic.Mithra")))
 		{
 			chronicOpForm.setShowNam("Yes");
 			chronicOpForm.setNamRemarks(claimsFlowVO.getCexRemark());

 		}
 		if(claimsFlowVO.getRoleId()!=null &&claimsFlowVO.getRoleId().equalsIgnoreCase(config.getString("Chronic.Medco")))
 		{
 			chronicOpForm.setShowMedco("Yes");
 			chronicOpForm.setClaimNwhAmt(claimsFlowVO.getCOUNT().longValue());
 			chronicOpForm.setMedcoRemarks(claimsFlowVO.getCexRemark());
 			SimpleDateFormat sdf2=new SimpleDateFormat("dd/MM/yyyy");
 			Date followupDate=claimsFlowVO.getChronicAuditDate();
 			String followupDt=sdf2.format(followupDate);
 			chronicOpForm.setFollowUpDt(followupDt);
 		}
 		if(claimsFlowVO.getRoleId()!=null &&claimsFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.COEX")))
 		{
 			chronicOpForm.setShowFcx("Yes");
 			chronicOpForm.setFcxRemark(claimsFlowVO.getCexRemark());
 			ChronicOPVO  chronicOPVO=new ChronicOPVO();
 			chronicOPVO=chronicOPService.getCoexChkList(chronicId, chronicNo);
 			if(chronicOPVO!=null)
 			{
 			chronicOpForm.setExeDisphotoChklst(chronicOPVO.getExeDisphotoChklst());
 			chronicOpForm.setExePatphotoChklst(chronicOPVO.getExePatphotoChklst());
 			chronicOpForm.setExeAcqvrifyChklst(chronicOPVO.getExeAcqvrifyChklst());
 			chronicOpForm.setExereprtcheckChklst(chronicOPVO.getExereprtcheckChklst());
 			chronicOpForm.setExeDisphotoremark(chronicOPVO.getExeDisphotoremark());
 			chronicOpForm.setExePatphotoRemark(chronicOPVO.getExePatphotoRemark());
 			chronicOpForm.setExeAcqverifyRemark(chronicOPVO.getExeAcqverifyRemark());
 			chronicOpForm.setExeReprtcheckRemark(chronicOPVO.getExeReprtcheckRemark());
 		
 			
 			}
 		}
 		if(claimsFlowVO.getRoleId()!=null &&claimsFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.COTD")))
 		{
 			chronicOpForm.setShowFtd("Yes");
 			chronicOpForm.setFtdRmks(claimsFlowVO.getCexRemark());
 			chronicOpForm.setClaimFtdAmt(claimsFlowVO.getCOUNT().longValue());
 			ChronicOPVO  chronicOPVO=new ChronicOPVO();
 			chronicOPVO=chronicOPService.getCotdChkList(chronicId, chronicNo);
 			if(chronicOPVO!=null)
 			{
 				chronicOpForm.setFtdRemarkvrifedChklst(chronicOPVO.getFtdRemarkvrifedChklst());
 				chronicOpForm.setFtdBeneficiryChklst(chronicOPVO.getFtdBeneficiryChklst());
 				chronicOpForm.setFtdRemarkvrifedRemark(chronicOPVO.getFtdRemarkvrifedRemark());
 				chronicOpForm.setFtdBeneficiryRemark(chronicOPVO.getFtdBeneficiryRemark());
 			}
 			
 		}
 		if(claimsFlowVO.getRoleId()!=null &&claimsFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.COCH")))
 		{
 			chronicOpForm.setShowCh("Yes");
 			chronicOpForm.setChRemark(claimsFlowVO.getCexRemark());
 			chronicOpForm.setClaimChAmt(claimsFlowVO.getCOUNT().longValue());
 		}
 		if(claimsFlowVO.getRoleId()!=null &&claimsFlowVO.getRoleId().equalsIgnoreCase(config.getString("GROUP.COACO")))
 		{
 			chronicOpForm.setShowAco("Yes");
 			chronicOpForm.setAcoRemark(claimsFlowVO.getCexRemark());
 			
 		}
 		
 		
 	}
 	}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	if(
 	chronicOpForm.getFollowUpDt()==null || ("").equalsIgnoreCase(chronicOpForm.getFollowUpDt()))
 	{
 		chronicOpForm.setFollowUpDt(serverDt);
 	}
 	
 	
 	
 	
 	/*added for getting buttons list based on roles and chronic status*/
 	List<LabelBean> buttonList = commonService.getDynamicWrkFlowDtls(
 			chronicStatus, lStrUserGroup, ClaimsConstants.CHRONICOP,
			ClaimsConstants.CHRONICOP_CLAIM);
	chronicOpForm.setButtonList(buttonList);

	if (buttonList.size() == 0)
		request.setAttribute("disableAll", ClaimsConstants.YES);
	
	request.setAttribute("buttons",buttonList);
 	
	/*end of buttons list*/
	
	//request.setAttribute("msg","hiii");
 	
		
		lStrPageName="chronicOpclaimForm";
	}
	if(lStrActionVal!=null && "getGenInvestList".equalsIgnoreCase(lStrActionVal)){
		String lStrBlockId=request.getParameter("lStrBlockId");	
		String packageCode=request.getParameter("packageCode");	
		List<String> symptomList=null;
		String currentInstallment=(String) session.getAttribute("currentInstallment");
		String  installment=(String) session.getAttribute("installment");
		System.out.println("installment is :"+installment);
		
		if(installment==null)
		{
			installment="1";
		}
		if(currentInstallment!=null)
		{
			installment=currentInstallment;
		}
		
		symptomList=chronicOPService.getchronicInvestigations(lStrBlockId,packageCode,installment);
		if (symptomList != null && symptomList.size() > 0)
			request.setAttribute("AjaxMessage", symptomList);

		lStrPageName="ajaxResult";
	}
	if("getOpIcdList".equalsIgnoreCase(lStrActionVal)){
		String opCode= request.getParameter("lStrOpPkgCode");
		String scheme=(String) session.getAttribute("scheme");
		List<String> opIcdList=null;
		opIcdList=chronicOPService.getOpIcdList(opCode,scheme);
		if (opIcdList != null && opIcdList.size() > 0)
			request.setAttribute("AjaxMessage", opIcdList);
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
		drugSubList=chronicOPService.getOpDrugSubList(drugTypeId);
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
		drugSubList=chronicOPService.getOpPharSubList(drugTypeId);
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
		drugSubList=chronicOPService.getOpChemSubList(pharSubCode);
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
		drugSubList=chronicOPService.getChemSubList(cSubGrpCode);
		if (drugSubList != null && drugSubList.size() > 0)
			request.setAttribute("AjaxMessage", drugSubList);
		lStrPageName="ajaxResult";
	}
	
	
	
	
	/*if("getRouteTypeList".equalsIgnoreCase(lStrActionVal))
	{
		List<String> routeTypeList=null;
		String actCode=null;
		if(request.getParameter("actCode")!=null && !request.getParameter("actCode").equals(""))
		{
			actCode=request.getParameter("actCode");        		
		}
	
		routeTypeList=chronicOPService.getRouteList(actCode);
		if (routeTypeList != null && routeTypeList.size() > 0)
			request.setAttribute("AjaxMessage", routeTypeList);
		lStrPageName="ajaxResult";
	}
	if("getStrengthTypeList".equalsIgnoreCase(lStrActionVal))
	{
		List<String> strengthTypeList=null;
		String actCode=null;
		if(request.getParameter("actCode")!=null && !request.getParameter("actCode").equals(""))
		{
			actCode=request.getParameter("actCode");        		
		}
		
		strengthTypeList=chronicOPService.getStrengthList(actCode);
		if (strengthTypeList != null && strengthTypeList.size() > 0)
			request.setAttribute("AjaxMessage", strengthTypeList);
		lStrPageName="ajaxResult";
	}
	*/
	
	if ("saveChronicInstallment".equalsIgnoreCase(lStrActionVal))
	{
		chronicOpForm.setPatientNo("");
		String saveFlg = request.getParameter("saveFlag");
	
		SimpleDateFormat sdfp=new SimpleDateFormat("dd-MM-yyyy");
		String patCrtDt=null;	
		Date date = new Date();
		if(session.getAttribute("patCrtdt")!=null)
			 patCrtDt=sdfp.format(session.getAttribute("patCrtdt"));	
		else
			patCrtDt=sdfp.format(date);	
		
		String crtDate=sdfw.format(date);
		crtDt = sdfw.parse((sdfw.format(date)));
		ChronicOpVO=new ChronicOPVO();
		String liNextVal="";
		String surgeryNextVal="";
		EhfmSeq ehfmSeqCase = null;
		EhfmSeq ehfmSeqCaseTherapy = null;
		String lStrChronicId=null;
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
		long pkgAmount=0;
		userId=session.getAttribute("EmpID").toString(); 
		String scheme=chronicOpForm.getScheme();
		String installment=(String) session.getAttribute("currentInstallment");
			lStrChronicId=(String) session.getAttribute("chronicId");
			String chronicNo=(String) session.getAttribute("chronicId")+"/"+installment;
			pkgAmount=chronicOPService.getPackageAmount(lStrChronicId, installment,scheme);
		
		if(request.getParameter("checkType")!=null && !request.getParameter("checkType").equals("")){
			checkType=request.getParameter("checkType");
			request.setAttribute("checkType", checkType);
		}		
		ChronicOpVO.setPKGAMOUNT(pkgAmount);
		ChronicOpVO.setSchemeId(chronicOpForm.getScheme());
		ChronicOpVO.setPersonalHistVal(chronicOpForm.getPersonalHistVal());
		ChronicOpVO.setExamFndsVal(chronicOpForm.getExamFndsVal());
		ChronicOpVO.setCrtDt(crtDate);
		ChronicOpVO.setChronicID(lStrChronicId);
		ChronicOpVO.setChronicNo(chronicNo);
		ChronicOpVO.setCrtUsr(userId);
		ChronicOpVO.setLangId(lStrLangID);
		ChronicOpVO.setUserRole(roleId);
					
			
		
		
		
			String s=null;
			List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
			DrugsVO drugsVO = null;
			if(chronicOpForm.getDrugs()!=null && chronicOpForm.getDrugs().length()>0){
				String verify=chronicOpForm.getDrugs().substring(chronicOpForm.getDrugs().length()-2, chronicOpForm.getDrugs().length()-1);
				if(("@").equals(verify))
			s=chronicOpForm.getDrugs().substring(0, chronicOpForm.getDrugs().length()-2);
				else
					s=chronicOpForm.getDrugs().substring(0, chronicOpForm.getDrugs().length()-1);		
			String[] drugs=s.split("@,");
			for(int z=0;z<drugs.length;z++)
			{
				String drug=drugs[z];
				String[] drugValues=drug.split("~");
				drugsVO = new DrugsVO();
				
				drugsVO.setDrugName(drugValues[0]);
				
				drugsVO.setDosage(drugValues[1]);
				drugsVO.setMedicationPeriod(drugValues[2]);
				/*drugsVO.setBatchNo(drugValues[3]);
				drugsVO.setExpiryDt(drugValues[4]);*/
				Long drugSeqNextVal = Long.parseLong(patientService.getSequence("DRUG_ID"));
				drugsVO.setDrugId(drugSeqNextVal);
				//drugsVO.setDrugId(drugSeqNextVal);
				//drugSeqNextVal=drugSeqNextVal+1;
				drugsList.add(drugsVO);
			}
			}
			ChronicOpVO.setDrugs(drugsList);
			//ehfmSeqDrug.setSeqNextVal(Long.valueOf(drugSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqDrug);

			//ChronicOpVO.setOpCatCode(chronicOpForm.getOpCatName());
			
			ChronicOpVO.setOpPkgCode(chronicOpForm.getOpPkgCode());
			ChronicOpVO.setOpIcdCode(chronicOpForm.getOpIcdCode());
			

		//GLOGGER.info("ChronicId"+lStrChronicId+"while Patient Case registration in ChronicAction.") ;

		if(request.getParameter("addTests")!=null && !request.getParameter("addTests").equals(""))
		{
			genInvestigationList=request.getParameter("addTests");
			LabelBean labelBean=new LabelBean();
			StringTokenizer st=new StringTokenizer(genInvestigationList,"~");
			while(st.hasMoreTokens()){
				labelBean=new LabelBean();
				String lL=st.nextToken();
				StringTokenizer st1=new StringTokenizer(lL,"$");
				while(st1.hasMoreTokens()){
					labelBean.setVALUE(st1.nextToken());
					labelBean.setID(st1.nextToken());                        
					lGenInvList.add(labelBean)  ;
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
		for(LabelBean labelBean: lGenInvList){
			//used to avoid deleted attachments-conflict
			while(chronicOpForm.getGenAttach(i)==null )
			{
				i++;
			}
			if(chronicOpForm.getGenAttach(i)!=null ){
				lFormFile=chronicOpForm.getGenAttach(i);
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
			while(chronicOpForm.getUpdateGenAttach(i)==null )
			{
				if(i==99)
				{
					break;
				}
				i++;
			}
			if(chronicOpForm.getUpdateGenAttach(i)!=null ){
				lFormFile=chronicOpForm.getUpdateGenAttach(i);
				if (lFormFile.getFileSize() > 204800) 
				{
					errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
				}
			}
			i++;
		}

		//For IP Therapy Investigations
		i=0;
		for(LabelBean labelBean: lSelInvList){
			//used to avoid deleted attachments-conflict
			while(chronicOpForm.getAttach(i)==null )
			{
				i++;
			}
			if(chronicOpForm.getAttach(i)!=null ){
				lFormFile=chronicOpForm.getAttach(i);
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
				while(chronicOpForm.getGenAttach(i)==null )
				{
					i++;
				}
				lFormFile=chronicOpForm.getGenAttach(i);                                     
				lFileName=lFormFile.getFileName();
				if(lFileName!=null && !lFileName.equals(""))
				{
					lCount=lFileName.lastIndexOf(".");
					lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
					// save file in folder's
					String lStrSharePath = 
							config.getString("STORAGE_BOX") + 
							/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
							config.getString("SLASH_VALUE") + lStrChronicId + 
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
						GLOGGER.error ( "Exception occurred using saveChronicCaseDetails actionFlag in ChronicAction." +e.getMessage()) ;
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
				lGenTestAttachList.add(attachmentVO);                 
				i++;
			}
			ChronicOpVO.setGenAttachmentsList(lGenTestAttachList);  

			//ehfmSeqGenTests.setSeqNextVal(Long.valueOf(genTestSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqGenTests);

			
			i=0; 
			//For Updating  General Investigations
			for(LabelBean labelBean: lUpdateGenInvList){
				AttachmentVO attachmentVO=new AttachmentVO();
				//used to avoid deleted attachments-conflict
				while(chronicOpForm.getUpdateGenAttach(i)==null )
				{
					if(i==99)
					{
						break;
					}
					i++;
				}
				lFormFile=chronicOpForm.getUpdateGenAttach(i);  
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
							config.getString("SLASH_VALUE") + lStrChronicId + 
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
						GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in chronicopAction while saving general investigations " +e.getMessage()) ;
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
			ChronicOpVO.setUpdGenAttachmentsList(lUpdGenTestAttachList);  
			
			
			
			i=0;
			//For IP Therapy Investigations
			//ChronicOpVO.setCaseTherInvestSeqId(caseTheInvestSeqNextVal);
			for(LabelBean labelBean: lSelInvList){
				AttachmentVO attachmentVO=new AttachmentVO(); 
				//used to avoid deleted attachments-conflict
				while(chronicOpForm.getAttach(i)==null )
				{
					i++;
				}
				lFormFile=chronicOpForm.getAttach(i);                                     
				lFileName=lFormFile.getFileName();
				lCount=lFileName.lastIndexOf(".");
				lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
				// save file in folder's
				String lStrSharePath = 
						config.getString("STORAGE_BOX") + 
						/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
						config.getString("SLASH_VALUE") + lStrChronicId + 
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
					GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in chronicopAction." +e.getMessage()) ;
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
			ChronicOpVO.setAttachmentsList(lTestsAttachList); 

			//ehfmSeqCaseTherInvest.setSeqNextVal(Long.valueOf(caseTheInvestSeqNextVal));
			//commonService.updateSequenceVal(ehfmSeqCaseTherInvest);
            ChronicOpVO.setSaveFlag(saveFlg);
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
            			chronicOPService.deleteGenInvest(lStrChronicId,genInvestRem[i]);         
            		}
            	}
            }
         
		     msg=chronicOPService.saveChronicInstallment(ChronicOpVO);

			//chronicOpForm.setPatientType(ChronicOpVO.getPatientType());
			chronicOpForm.setChronicID(lStrChronicId);
			chronicOpForm.setChronicNo(chronicNo);
			
			if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit")){
				chronicOpForm.setMsg("Case Registered Successfully with Chronic ID :");
			}else{
				chronicOpForm.setMsg("Case Saved Successfully with Chronic ID :");
				if(checkType!=null)
				{
					chronicOpForm.setDisableFlag(checkType);
				}
			}
			if(msg!=null && !msg.equalsIgnoreCase("Y"))
			{
				
				
					if(ChronicOpVO.getSaveFlag()!=null && ChronicOpVO.getSaveFlag().equalsIgnoreCase("Submit")){
					if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					{
						EhfChronicPatientDtl ehfChronicPatientDtl=null;
						ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(lStrChronicId);

						if(ehfChronicPatientDtl.getContactNo().toString()!=null && !ehfChronicPatientDtl.getContactNo().toString().equals(""))
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
							patientSmsVO.setPhoneNo(ehfChronicPatientDtl.getContactNo().toString());
							patientSmsVO.setSmsText("Dear "+ehfChronicPatientDtl.getName().trim()+" , You have been registered as "+ chronicOpForm.getPatientType()+" in "+patientService.getHospName(chronicOpForm.getHospitalId())+" with Case Id "+lStrCaseId);
							patientSmsVO.setEmpCode(ehfChronicPatientDtl.getEmployeeNo());
							patientSmsVO.setEmpName(ehfChronicPatientDtl.getName().trim());
							patientSmsVO.setCrtUsr(userId);
							patientSmsVO.setCrtDt(crtDt);
							patientSmsVO.setSmsAction("chronic reg" );
							patientSmsVO.setPatientId(lStrChronicId);
							lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
						}
					}
					if (config.getBoolean("EmailRequired")) 
					{
						EhfChronicPatientDtl ehfChronicPatientDtl=null;
						ehfChronicPatientDtl=(EhfChronicPatientDtl)chronicOPService.getPatientDetails(lStrChronicId);
						String mailId=null;
						if(ehfChronicPatientDtl.getEmailid()!=null && !ehfChronicPatientDtl.getEmailid().equals(""))
						{
							mailId=ehfChronicPatientDtl.getEmailid();
							String[] emailToArray = {mailId};
							EmailVO emailVO = new EmailVO();
							emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
							emailVO.setTemplateName(config.getString("EHFChronicPatientTemplateName"));
							emailVO.setFromEmail(config.getString("emailFrom"));
							emailVO.setToEmail(emailToArray);
							emailVO.setSubject(config.getString("patientEmailSubject"));
							Map<String, String> model = new HashMap<String, String>();
							model.put("chronicId",lStrChronicId);
							model.put("patientName", ehfChronicPatientDtl.getName().trim());
							model.put("registeredHosp", patientService.getHospName(chronicOpForm.getHospitalId()));
							model.put("status", "Case Registered as Chronic OP with Chronic Id "+lStrCaseId);
							model.put("statusDate",crtDate);
							commonService.generateMail(emailVO, model);
						}
					}
					}						
				
			}
			request.setAttribute("saveFlag",saveFlg);
			request.setAttribute("inst", msg);
			lStrActionVal="ClinicalNotes"; 
		}
		else
		{
			chronicOpForm.setErrMsg("Uploaded File Size Should not exceed 200KB");
			request.setAttribute("inst", msg);
			lStrActionVal="ClinicalNotes"; 
		}
	
	}
	
	if ("validateChronicOpPkg".equalsIgnoreCase(lStrActionVal))
	{
		String pkgCode=request.getParameter("lStrOpPkgCode");
		String cardNo=request.getParameter("lStrCardNo");
		boolean registered=false;
		String pkgMsg=null;
		registered=chronicOPService.getUserPkgCode(pkgCode, cardNo);
		if(registered)
		{
			pkgMsg="A Chronic OP case is already registered with this package for the following patient.Please select another package and proceed ";
			request.setAttribute("AjaxMessage",pkgMsg);
		}
		else
		{
			request.setAttribute("AjaxMessage",null);
		}
		lStrPageName="ajaxResult";
		
	}
	if ("cancelCase".equalsIgnoreCase(lStrActionVal))
	{
		String chronicId=request.getParameter("chronicId");
		String chronicNo=chronicId+"/"+"1";
		String user=lStrEmpId;
		Boolean status=false;
		status=chronicOPService.cancelCase(chronicId, chronicNo, user);
		
		if(status)
		{
			msg="Chronic OP case "+chronicId+" cancelled successfully";
			request.setAttribute("cancelFlag","Y");
			chronicOpForm.setMsg(msg);
		}
		
		lStrPageName="patient";
	}
	
	
/*added for getting preauth cases on load*/
	
	if(lStrActionVal!= null && "OnloadCaseOpen".equals(lStrActionVal)) 
    {
    	
    		
    		String lstrUserGroup=null;
    		String caseIdchk=null;
    		//session.removeAttribute("chronicId");
    		String caseStatus=null,csSurgDt=null,clinicalTab="show";
    	    String autoCaseId = request.getParameter("autoCaseId");
    	    String disableFlag=request.getParameter("disableFlag");
    	    if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
    	    	autoCaseId = "0";
    	    ClaimCases.releaseCase(autoCaseId);
        	String searchType= request.getParameter("caseSearchType");	        	
        	String caseId = request.getParameter("CaseId");	
 	        String flag = request.getParameter("flag");
 	        String module = request.getParameter("module");
 	        String caseForDissFlg = request.getParameter("disSearchType");
 	        String casesForApprovalFlag = request.getParameter("casesForApproval");
 	        String errCaseApprovalFlag = request.getParameter("errSearchType");
 	        String lStrSchemeType = request.getParameter("schemeType");
 	       
 	       /*if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
 	    	  casesForApprovalFlag = chronicOpForm.getCasesForApprovalFlag();*/
 	        if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
 	        	casesForApprovalFlag="N";
 	        if(errCaseApprovalFlag==null || errCaseApprovalFlag.equalsIgnoreCase(""))
 	        	errCaseApprovalFlag="N";
 	        if(caseForDissFlg==null || caseForDissFlg.equalsIgnoreCase(""))
 	        	caseForDissFlg="N";
 	        if(caseId != null && !caseId.equals(""))
 	        	chronicOPService.setviewFlag(caseId,flag,lStrEmpId);
 	       /* if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
 	        	lStrSchemeType = lStrUserState;*/
 	        /**
 	         * end 
 	         */
        	CasesSearchVO casesSearchVO = new CasesSearchVO();
        	casesSearchVO.setCaseSearchType(searchType);
        	casesSearchVO.setUserId(lStrEmpId);		        	
        	casesSearchVO.setGrpList(rolesList);
        	casesSearchVO.setCasesForApprovalFlag(casesForApprovalFlag);
        	casesSearchVO.setErrCaseApprovalFlag(errCaseApprovalFlag);
        	casesSearchVO.setCaseForDissFlg(caseForDissFlg);
        	casesSearchVO.setModule(module);
        	casesSearchVO.setSchemeVal(lStrSchemeType);
        	//chronicOpForm.setCasesForApprovalFlag(casesForApprovalFlag);

        	 for(LabelBean labelBean:rolesList)
     	    {
     	   if(labelBean.getID() != null && config.getString("chronicop_roles").contains(labelBean.getID()) )
     	     {
     		   lstrUserGroup = labelBean.getID() ;
     			break;
     	     }
     	     else{
     	    	lstrUserGroup = null;
     		casesSearchVO.setRoleId(labelBean.getID());
     	     }
     	     }
        	//casesSearchVO = casesSearchService.getUserRole(casesSearchVO);
        	String medcoUpdatedStatus = config.getString("preauth_medco_updated");
        	String timeOutCount = "0";
        	casesSearchVO.setUserRole(lstrUserGroup);
        	String checkForCase = ClaimCases.getCaseForUserId(lStrEmpId,module);
        	if(checkForCase!=null && checkForCase.equalsIgnoreCase("false")){
        		 List<ChronicOPVO>  lstCasesSearchVO  =  chronicOPService.getChronicClaimCases(lstrUserGroup,null,schemeId);
        	if(lstCasesSearchVO.size()>0){
        	for (ChronicOPVO chronicOPVO : lstCasesSearchVO){
        		String caseId1 = chronicOPVO.getChronicID();
        		boolean status = ClaimCases.isAvailable(caseId1,lStrEmpId,module);
        		if (status) {
        			/*timeOutCount = "0";		        			
        			if(!medcoUpdatedStatus.contains(chronicOPVO.getChronicStatus()))
        			timeOutCount = chronicOPService.getTimeOutCount(caseId1,rolesList,module);*/
        			 
        			caseIdchk=caseId1;
        			request.setAttribute("CaseId", caseId1);
        			String chronicId=caseId1.substring(0, caseId1.length()-2);
            		session.setAttribute("chronicId", chronicId);
            		session.setAttribute("chronicNo", caseId1);
        			/* request.setAttribute("timeOutCount", timeOutCount);*/
                    break;
                }		        		
        	}
        	}else
        		return mapping.findForward("caseNotFound");
        	}
        	else{
        	    /*timeOutCount = "0";		        		
        		if(!medcoUpdatedStatus.contains(chronicOPService.getCaseStatusForCase(checkForCase)))
        		timeOutCount = chronicOPService.getTimeOutCount(checkForCase,rolesList,module);*/
        		
        		//request.setAttribute("timeOutCount", timeOutCount);
        		request.setAttribute("CaseId", checkForCase);
        		String chronicId=checkForCase.substring(0, checkForCase.length()-2);
        		session.setAttribute("chronicNo", checkForCase);
        		session.setAttribute("chronicId", chronicId);
        		caseIdchk=checkForCase;
        	}
        	session.setAttribute("caseApprovalFlag", casesForApprovalFlag);
        	session.setAttribute("disableFlag", disableFlag);
        	session.setAttribute("autoActionFlag", "OnloadCaseOpen");
        	//request.setAttribute("autoActionFlag", "OnloadCaseOpen");
        	
        	
 
        	
        	/*FlaggingVO fVO=new FlaggingVO();
			fVO=flaggingService.checkAuthority(rolesList);
			if(fVO!=null)
			{
				String authority=fVO.getAuthority();
					if("Y".equalsIgnoreCase(authority))
						{
							FlaggingVO fVO1=new FlaggingVO();
							fVO1=flaggingService.getFlaggedCasesForColor(caseIdchk);
							if(fVO1!=null)
								{
									request.setAttribute("flagColour",fVO1.getFlagColour());
									System.out.println(fVO1.getFlagColour());
								}
						}
			}
			fVO=new FlaggingVO();
			fVO=flaggingService.checkCaseFlagged(caseIdchk);
			if(fVO!=null)
			{
				if(fVO.getFlagged()!=null)
					request.setAttribute("flagged",fVO.getFlagged());
				else
					request.setAttribute("flagged",null);
			}*/
			
			
		
            return mapping.findForward("chronicDisplayFrame");
    }
	
	
	if ("ClinicalNotes".equalsIgnoreCase(lStrActionVal))
	{
		String chronicId=(String) session.getAttribute("chronicId");
		String chronicNo=(String) session.getAttribute("chronicNo");
		String installment=(String) session.getAttribute("installment");
		String scheme=(String) session.getAttribute("scheme");
		Number diff=chronicOPService.getMonthsbetween(chronicId, chronicNo);
		int currInst=0;
		String Chronicmsg="";
		String currentInstallment=null;
		

		List<LabelBean> opPackageList= chronicOPService.getOpPkgList(scheme);
		request.setAttribute("opCategoryList",opPackageList);
		int inst=Integer.parseInt(installment);
		
		if(diff.floatValue()<12)
		{
		if(diff.floatValue()>3 && diff.floatValue()<=6){
			currInst=2;
			}
		if(diff.floatValue()>6 && diff.floatValue()<=9){
			currInst=3;
			}
		
		if(diff.floatValue()>9 && diff.floatValue()<=12){
			currInst=4;
			}
		
		if(currInst-inst>1)
		{
			Chronicmsg="Following Patient Missed "+(currInst-inst-1)+" Installments.";	
		}
		
		Chronicmsg=Chronicmsg+"Current Chronic Installment is : ";
		
		}
		else{
			currInst=(inst+3);
			currentInstallment="";
		Chronicmsg="The following Chronic op case is expired as all the installments are lapsed ";}
		
		currentInstallment=String.valueOf(currInst);
		session.setAttribute("currentInstallment", currentInstallment);
		request.setAttribute("msg", Chronicmsg+currentInstallment);
		
		List<CommonDtlsVO> commonDtls=chronicOPService.getPatientCommonDtls(chronicId);
		if(commonDtls!=null && commonDtls.size()>0)
		chronicOpForm.setScheme(commonDtls.get(0).getScheme());
		else
			chronicOpForm.setScheme(scheme);
		
		PreauthVO patientData= new PreauthVO();
		patientData=chronicOPService.getPatientFullDtls(chronicId,chronicNo,null);
		
		ChronicOPVO opDtls=new ChronicOPVO();
		if(patientData!=null)
						{
							opDtls.setOpCatName(patientData.getOpCatName());
							opDtls.setOpPackageName(patientData.getOpPackageName());
							opDtls.setOpIcdName(patientData.getOpIcdName());
							}
		
		/*Added do calculate total package amount*/
		long drugAmt=0;
		ChronicOPVO chronicOPVO=new ChronicOPVO();
		chronicOPVO.setOpPkgCode(patientData.getOpPkgCode());
		chronicOPVO.setActOrder(installment);
		String medcoScheme=chronicOPService.getMedcoScheme(lStrUserId);
		chronicOPVO.setSchemeId(medcoScheme);
		drugAmt=chronicOPService.getPackageDrugAmount(chronicOPVO);
		chronicOpForm.setPackageDrugAmt(drugAmt+"");
		
		
		request.setAttribute("PatientOpList",patientData);
		request.setAttribute("chronicId",chronicId);
		
		lStrPageName="chronicOPClinicalNotes";
	}
	
	if(("getPackageDrugAmt").equalsIgnoreCase(lStrActionVal))
	{
		String opPkgCode=request.getParameter("opPkgCode");
		String chronicId=request.getParameter("chronicId");
		String installment=chronicOPService.getChronicInstallment(chronicId);
		long drugAmt=0;
		ChronicOPVO  chronicOPVO=new ChronicOPVO();
		chronicOPVO.setOpPkgCode(opPkgCode);
		chronicOPVO.setActOrder(installment);
		String medcoScheme=chronicOPService.getMedcoScheme(lStrUserId);
		chronicOPVO.setSchemeId(medcoScheme);
		drugAmt=chronicOPService.getPackageDrugAmount(chronicOPVO);
		
		request.setAttribute("AjaxMessage",drugAmt);
		return mapping.findForward("ajaxResult");
		
		
	}
	
	if(("calculateChronicDrugAmt").equalsIgnoreCase(lStrActionVal))
	{
		String opPkgCode=request.getParameter("opPkgCode");
		
		
		String drug=request.getParameter("drugs");
		float totalDrugAmount=0;
		
		String s=null;
		List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
		DrugsVO drugsVO;
		
		if(drug!=null && drug.length()>0){
			String verify=drug.substring(drug.length()-2, drug.length()-1);
			if(("@").equals(verify))
		     s=drug.substring(0, drug.length()-2);
			else
				s=drug.substring(0, drug.length()-1);		
		String[] drugs=s.split("@,");
		for(int z=0;z<drugs.length;z++)
		{
			String d=drugs[z];
			float drugAmt=0;
			String[] drugValues=d.split("~");
			drugsVO = new DrugsVO();
			
			drugsVO.setDrugName(drugValues[0]);
			drugsVO.setOpPkgCode(opPkgCode);
			drugsVO.setDosage(drugValues[1]);
			drugsVO.setMedicationPeriod(drugValues[2]);
		
			
			drugAmt=chronicOPService.calculateDrugAmount(drugsVO);
			totalDrugAmount=totalDrugAmount+drugAmt;
			//drugsVO.setDrugId(drugSeqNextVal);
			//drugSeqNextVal=drugSeqNextVal+1;
			//drugsList.add(drugsVO);
		}
		}
		request.setAttribute("AjaxMessage",totalDrugAmount);
		return mapping.findForward("ajaxResult");
		
	
	}
	
	
	
	/*added for updating ceo sent back cases */  /*by venkatesh*/
	if("updateSentBackCases".equalsIgnoreCase(lStrActionVal))
	{
	String chronicNo=request.getParameter("chronicNo");
	String lStrUserGroup="";
	
	String pendingFlag=request.getParameter("pendingFlag");
	String remarks=request.getParameter("remarks");
	String chronicStatus=request.getParameter("chronicStatus");
	//String actionDone=request.getParameter("actionDone");
	String moduleType=request.getParameter("moduleType");
	ChronicOPVO  chronicOPVO=new ChronicOPVO();
	msg=null;
	

	chronicOPVO.setChronicNo(chronicNo);

	chronicOPVO.setPendingFlag(pendingFlag);
	chronicOPVO.setRemarks(remarks);
	
	chronicOPVO.setUserId(lStrUserId);
	
	lStrUserGroup = config.getString("chronicGroup_"
			+ chronicStatus);
	
	chronicOPVO.setUserRole(lStrUserGroup);
	
	msg=chronicOPService.updateSentBackClaims(chronicOPVO);
	
	request.setAttribute("AjaxMessage",msg);
	return mapping.findForward("ajaxResult");
	
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	return mapping.findForward(lStrPageName);
	  }
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
}
