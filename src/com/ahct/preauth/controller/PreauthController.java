package com.ahct.preauth.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.service.CommonService;
/*import com.ahct.common.util.ServiceFinder;*/
import com.ahct.common.vo.LabelBean;
//import com.ahct.patient.service.PatientService;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.service.PreauthService;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PreauthVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;


@Controller
public class PreauthController {	
	@Autowired
	private PreauthService preauthService;
	//private PatientService patientService;
		
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private CommonService commonService;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(Model model) {
		model.addAttribute(new UploadItem());
		return "Preauth/PatientCommonDtls";
	}*/
	
	
	@RequestMapping("/patCommonDtls.htm")
	public ModelAndView patCommonDtls(HttpServletRequest request,Model model,UploadItem uploadItem,BindingResult result) 
	{
		String lStrResultPage=null;		
		Configuration config = configurationService.getConfiguration () ;
		
		try
		{
			/**Common Code**/
			String lStrActionFlag=request.getParameter("actionFlag");
			TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
			SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
			HttpSession session = request.getSession(false);
			String lStrUserRole=null;	      
	        
	        if(session == null || session.getAttribute("UserID")==null)
	        {
	             request.setAttribute("Message","Your session has expired. Login again");
	             lStrResultPage = "login/regSessionInvalid";//003
	             return new ModelAndView(lStrResultPage);
	        }
	        String lStrEmpId = null ;
	        if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
			    lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
			String lStrCaseId=null;
			if(request.getParameter ( "CaseId" ) !=null)
			{
				lStrCaseId=request.getParameter ( "CaseId" ) ;
			}
			request.setAttribute("caseId", lStrCaseId);	
			 List<LabelBean> groupsList = null;
			 if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
			 {		    
				  groupsList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
				  String roles[]={"preauth_medco_role","preauth_ch_role","preauth_ctd_role","PTD_Grp","PAO_Grp","PPD_Grp","CPD_Grp","EO_Grp","preauth_mithra_role","EHF.Claims.HUB"};
				   for(LabelBean labelBean:groupsList)
			    	{
					   for(int i=0;i<roles.length;i++)
					   	{
						   if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString(roles[i]))))
							   {
							   		lStrUserRole=config.getString(roles[i]);
							   		break;
							   }
					   	}
					   if(lStrUserRole!=null)
						   break;
			    	/*if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role"))))
			    	{
			    		lStrUserRole=config.getString("preauth_medco_role");
			    		break;
			    	}
			    	else
			    		lStrUserRole = null;*/
			    	}
			 }
	        
	        /**End of Common Code**/
			/**
			 * upload File
			 */
			 for(LabelBean labelBean:groupsList)
		    	{
				  
					   if(labelBean.getID() != null && (config.getString("claim_loginName").contains("~"+labelBean.getID()+"~")))
						   {
						   		request.setAttribute("ClaimPage", "Y");
						   		break;
						   }
				   	}
			  if("uploadFile".equalsIgnoreCase(lStrActionFlag))
			 {	
				String patientId = request.getParameter("patId");
			/**
			 * saving attachments
			 */
			try{
				if(uploadItem.getFileData().getBytes() != null)
				{
					if(uploadItem.getFileData().getSize() <= 2097152)
					{
					 java.util.Date ldtToday = new java.util.Date();
					 String filePath = ASRIConstants.STORAGE_BOX +patientId+ASRIConstants.SLASH_VALUE;
					 String fullPath = filePath ; 
	                 String dir =fullPath;
	                 boolean flag = ( new File ( dir ) ).mkdirs () ;
	                 dir = dir + ldtToday.getTime()+ASRIConstants.UNDERSCORE_VALUE+uploadItem.getFileData().getOriginalFilename();
	                 File lFileFS = new File ( dir ) ; 
	                 FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
                     fos.write ( uploadItem.getFileData().getBytes() ) ;
                     AttachmentVO attachmentVO = new AttachmentVO();
                     attachmentVO.setEmpParentId(patientId);
                     attachmentVO.setActualName(uploadItem.getFileData().getOriginalFilename());
                     attachmentVO.setSavedName(dir);
                     attachmentVO.setCrtUsrName(lStrEmpId);
                     String msg1 = preauthService.savePotoAttach(attachmentVO);
					}
					else
						request.setAttribute("resMsg", "Cannot Attch More than 2 MB ");
				}
           	 }catch(Exception e)
           	 {
           		e.printStackTrace(); 
           	 }
			
			lStrActionFlag = "commonDtls";
			 }
			/**
			 * PATIENT COMMON DETAILS
			 */
			   if(lStrActionFlag!=null && ("commonDtls".equalsIgnoreCase(lStrActionFlag)|| "patCommonDtls".equalsIgnoreCase(lStrActionFlag)))
			{
				   String caseStartTime=request.getParameter("caseStartTime");
				String status=request.getParameter("Status");
				if(lStrCaseId!=null && lStrCaseId.length()>2)
					{
						/*if(lStrCaseId.charAt(lStrCaseId.length()-2)=='/')
								lStrCaseId=lStrCaseId.substring(0, lStrCaseId.length()-2);*/
						
						if(lStrCaseId.contains("/"))
							{
								int index=lStrCaseId.indexOf("/");
								lStrCaseId=lStrCaseId.substring(0, index);
							}
					}
				List<CommonDtlsVO> commonDtls = preauthService.getPatientCommonDtls(lStrCaseId);
				if(commonDtls!= null &&  !commonDtls.isEmpty())
				{
					int lstSize=commonDtls.size();
					String surgName="";	
					String caseScheme="";
					for(int i=0;i<lstSize;i++)
					{
						if(!"".equalsIgnoreCase(commonDtls.get(i).getSURGNAME())){
						if(lstSize > 1)
							surgName = surgName+commonDtls.get(i).getSURGNAME()+",";
						else
							surgName = surgName+commonDtls.get(i).getSURGNAME();
						
						
						}
					}
					request.setAttribute("surgName",surgName);
					request.setAttribute("patCommonDtls",commonDtls.get(0));
					request.setAttribute("caseScheme", commonDtls.get(0).getScheme());
					String checkNABH = preauthService.getNABHhosptls(commonDtls.get(0).getINTIID(),commonDtls.get(0).getCASENO());
					if(!"".equalsIgnoreCase(checkNABH) && checkNABH != null && checkNABH.equalsIgnoreCase("Y") )
					{
						request.setAttribute("checkNABH","Y");
					}
					else{
						request.setAttribute("checkNABH","N");
					}
					if ("patCommonDtls".equalsIgnoreCase(lStrActionFlag)){
					if(lStrUserRole != null && (lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) && commonDtls.get(0).getCASESTAT().equalsIgnoreCase(config.getString("preauth_inpatient_caseReg")))
				    		||(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) && commonDtls.get(0).getCASESTAT().equalsIgnoreCase(config.getString("preauth_ptd_pending")))
				    		 ){
						request.setAttribute("viewType", "medco");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")))
					{
						request.setAttribute("loggedInRole", "medco");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("PPD_Grp")))
					{
						request.setAttribute("loggedInRole", "ppd");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_ch_role")))
					{
						request.setAttribute("loggedInRole", "claimHead");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_ctd_role")))
					{
						request.setAttribute("loggedInRole", "ctd");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("PAO_Grp")))
					{
						request.setAttribute("loggedInRole", "pao");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("EO_Grp")))
					{
						request.setAttribute("loggedInRole", "eo");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("CPD_Grp")))
					{
						request.setAttribute("loggedInRole", "cpd");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("PTD_Grp")))
					{
						request.setAttribute("loggedInRole", "ptd");		
					}
					if((request.getParameter("headFlag") == null || "".equalsIgnoreCase(request.getParameter("headFlag"))) ||
					   (request.getParameter("RestrictFlag") == null || "".equalsIgnoreCase(request.getParameter("RestrictFlag")))	)
					{
						request.setAttribute("RestrictFlag","1");
					}
					String hideBackButton="N";
					String[] buttonBlock=(config.getString("HIDEROLES.BACKBUTTON").split("~"));
					String[] statCondn=(config.getString("HIDEROLES.STATCONDITION").split("~"));
					for(String count:buttonBlock)
					{
						if(count.equalsIgnoreCase(lStrUserRole))
						{
							for(String statCnt:statCondn)
							{									
								if(statCnt.equalsIgnoreCase(status))
								{
									hideBackButton="Y";
									break;
								}
							}
							break;
						}
					}
					if(!("1".equalsIgnoreCase(request.getParameter("RestrictFlag")) || "1".equalsIgnoreCase((String)request.getAttribute("RestrictFlag")) )
						|| "Y".equalsIgnoreCase(hideBackButton))
					{
						hideBackButton="Y";
					}
					request.setAttribute("hideBackButton", hideBackButton);
					String[] fieldAttach=(config.getString("HIDEROLES.FIELDATTACH").split("~"));
					int len=fieldAttach.length;
					//String fieldAttachFlg="Y";
					for(int i=0;i<len;i++)
					{
						if(fieldAttach[i].equalsIgnoreCase(lStrUserRole))
						{
							//fieldAttachFlg="N";
							break;
						}
					}
					
					String[] detailedClaim=(config.getString("HIDEROLES.DETAILEDCLAIM").split("~"));					
					String detailedClaimFlg="Y";
					int len1=detailedClaim.length;
					for(int i=0;i<len1;i++)
					{
						if(detailedClaim[i].equalsIgnoreCase(lStrUserRole))
						{
							detailedClaimFlg="N";
							break;
						}
					}
					request.setAttribute("detailedClaimFlg", detailedClaimFlg);
				
				//	List<EhfPatientDocAttach> asritPatientDocAttach = preauthService.getOnBedPhotoDtls(commonDtls.get(0).getPATID());
					String patOnBedPic=null;
					// get patient photo path
					 PatientVO patientVo1=new PatientVO();
					 patientVo1.setCardNo(commonDtls.get(0).getCARDNO());
					 patientVo1.setCardType(commonDtls.get(0).getCardType());
					 patientVo1.setPatientScheme(commonDtls.get(0).getPatientScheme());
					 patientVo1=preauthService.retrieveCardDetails(patientVo1);
					 System.out.println("Patient Commnn Dtls line 234-------");
					 System.out.println(patientVo1);
					if(patientVo1 != null && patientVo1.getPhoto() != null && !patientVo1.getPhoto().isEmpty())
					{
						patOnBedPic = patientVo1.getPhoto();
						 try
	        			 {
	        				 File photo = new File(patOnBedPic);
	        				 FileInputStream fis = new FileInputStream(photo);
	        				 DataInputStream dis= new DataInputStream(fis);
	        				 byte bytes[] = new byte[dis.available()];
	        				 fis.read(bytes);
	        				 String lStrContentType=null;
	        				 lStrContentType="image/jpg";
	        				 session.setAttribute("ContentType", lStrContentType);
	        				 session.setAttribute("File", bytes);
	        				 fis.close();
	        				 dis.close();
	        				 patOnBedPic="Y";
	        			 }
	        			 catch(Exception e)
	        			 {
	        				 e.printStackTrace();
	        				 patOnBedPic="N";
	        				 //GLOGGER.error ( "Exception occured while photo is not available in the path specified in PatientAction." +e.getMessage()) ;
	        			 }
					}
					else if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("SHOWROLE.ONBEDPIC")) &&
							(commonDtls.get(0).getCASESTAT()).equalsIgnoreCase(config.getString("SHOWROLE.BEDUPDSTATUS"))
							&& (("1".equalsIgnoreCase(request.getParameter("RestrictFlag")) || "1".equalsIgnoreCase((String)request.getAttribute("RestrictFlag")) )))
					{
						patOnBedPic="Y";
					}
					if(commonDtls.get(0).getRelation()!=null )
						if(commonDtls.get(0).getRelation().equalsIgnoreCase("New Born Baby"))
								patOnBedPic="N";
					System.out.println("Patient Commnn Dtls line 266-------");
					model.addAttribute(new UploadItem());
					request.setAttribute("patOnBedPic",patOnBedPic);					
					request.setAttribute("caseId", lStrCaseId);
					request.setAttribute("caseScheme", caseScheme);
				}
				}
				if("commonDtls".equalsIgnoreCase(lStrActionFlag))
				{
					 //sai krishna:#CR8566:Flag for dialysis cycle to hide the link
					String speciality=request.getParameter("speciality");
					if(speciality!=null && !speciality.isEmpty()){
						request.setAttribute("speciality", speciality);
						System.out.println("sai krishna for Dialysis details2:"+speciality);
					}
					if(lStrUserRole != null && (lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) && commonDtls.get(0).getCASESTAT().equalsIgnoreCase(config.getString("preauth_inpatient_caseReg")))
				    		||(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) && commonDtls.get(0).getCASESTAT().equalsIgnoreCase(config.getString("preauth_ptd_pending")))
				    		 )
					{
						request.setAttribute("viewType", "medco");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")))
					{
						request.setAttribute("loggedInRole", "medco");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("PPD_Grp")))
					{
						request.setAttribute("loggedInRole", "ppd");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_ch_role")))
					{
						request.setAttribute("loggedInRole", "claimHead");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_ctd_role")))
					{
						request.setAttribute("loggedInRole", "ctd");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("PAO_Grp")))
					{
						request.setAttribute("loggedInRole", "pao");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("EO_Grp")))
					{
						request.setAttribute("loggedInRole", "eo");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("CPD_Grp")))
					{
						request.setAttribute("loggedInRole", "cpd");		
					}
					if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("PTD_Grp")))
					{
						request.setAttribute("loggedInRole", "ptd");		
					}
					String caseEndTime = sds.format(new Date().getTime());
					if( lStrUserRole != null && !"".equalsIgnoreCase(lStrUserRole) && !(lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) || lStrUserRole.equalsIgnoreCase(config.getString("preauth_mithra_role"))))
					{
					loggingService.logTime("CaseLoadingTime", lStrCaseId, caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
					}
					lStrResultPage="Preauth/PatientCommonDtls";
				}
				else if ("patCommonDtls".equalsIgnoreCase(lStrActionFlag))
					lStrResultPage="patient/patientCommonDtls";
			}
			   else if("getCaseDetails".equalsIgnoreCase(lStrActionFlag)){
				   String caseId=request.getParameter("CaseId");
				   CasesSearchVO caseSearchVo = preauthService.getCaseCommonDtls(caseId);
				   request.setAttribute("caseFullDtls",caseSearchVo);
				   lStrResultPage="caseSearch/caseFullDetails";
			   }
			/**
			 * IP TAB
			 */
		 else if("getPatDetails".equalsIgnoreCase(lStrActionFlag))
		 {	//sai krishna:#CR8566:Flag for dialysis cycle to hide the link
				String speciality=request.getParameter("speciality");
				if(speciality!=null && !speciality.isEmpty()){
					request.setAttribute("speciality", speciality);
					System.out.println("getPatDetails:"+speciality);
				}
			 String status=request.getParameter("Status");
				List<CommonDtlsVO> commonDtls = preauthService.getPatientCommonDtls(lStrCaseId);
				if(commonDtls!= null &&  !commonDtls.isEmpty())
				{
					int lstSize=commonDtls.size();
					String surgName="";	
					String caseScheme="";
					for(int i=0;i<lstSize;i++)
					{
						if(!"".equalsIgnoreCase(commonDtls.get(i).getSURGNAME())){
						if(lstSize > 1)
							surgName = surgName+commonDtls.get(i).getSURGNAME()+",";
						else
							surgName = surgName+commonDtls.get(i).getSURGNAME();
						}
					}
					request.setAttribute("surgName",surgName);
					request.setAttribute("patCommonDtls",commonDtls.get(0));
					String checkNABH = preauthService.getNABHhosptls(commonDtls.get(0).getINTIID(),commonDtls.get(0).getCASENO());
					if(!"".equalsIgnoreCase(checkNABH) && checkNABH != null && checkNABH.equalsIgnoreCase("Y") )
					{
						request.setAttribute("checkNABH","Y");
					}
					else{
						request.setAttribute("checkNABH","N");
					}
					request.setAttribute("fromCMS", request.getParameter("fromCMS"));
					if(lStrUserRole != null && (lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) && commonDtls.get(0).getCASESTAT().equalsIgnoreCase(config.getString("preauth_inpatient_caseReg")))
				    		||(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")) && commonDtls.get(0).getCASESTAT().equalsIgnoreCase(config.getString("preauth_ptd_pending")))
				    		 ){
						request.setAttribute("viewType", "medco");		
				}
					if(lStrUserRole != null && (lStrUserRole.equalsIgnoreCase(config.getString("preauth_medco_role")))){
						request.setAttribute("loginUserRole", "medco");		
				}
					if(lStrUserRole != null && (lStrUserRole.equalsIgnoreCase(config.getString("preauth_mithra_role")))){
						request.setAttribute("loginUserRole", "mithra");		
				}
					if((request.getParameter("headFlag") == null || "".equalsIgnoreCase(request.getParameter("headFlag"))) ||
					   (request.getParameter("RestrictFlag") == null || "".equalsIgnoreCase(request.getParameter("RestrictFlag")))	)
					{
						request.setAttribute("RestrictFlag","1");
					}
					String hideBackButton="N";
					String[] buttonBlock=(config.getString("HIDEROLES.BACKBUTTON").split("~"));
					String[] statCondn=(config.getString("HIDEROLES.STATCONDITION").split("~"));
					for(String count:buttonBlock)
					{
						if(count.equalsIgnoreCase(lStrUserRole))
						{
							for(String statCnt:statCondn)
							{									
								if(statCnt.equalsIgnoreCase(status))
								{
									hideBackButton="Y";
									break;
								}
							}
							break;
						}
					}
					if(!("1".equalsIgnoreCase(request.getParameter("RestrictFlag")) || "1".equalsIgnoreCase((String)request.getAttribute("RestrictFlag")) )
						|| "Y".equalsIgnoreCase(hideBackButton))
					{
						hideBackButton="Y";
					}
					request.setAttribute("hideBackButton", hideBackButton);
					String[] fieldAttach=(config.getString("HIDEROLES.FIELDATTACH").split("~"));
					int len=fieldAttach.length;
					//String fieldAttachFlg="Y";
					for(int i=0;i<len;i++)
					{
						if(fieldAttach[i].equalsIgnoreCase(lStrUserRole))
						{
							//fieldAttachFlg="N";
							break;
						}
					}
					
					String[] detailedClaim=(config.getString("HIDEROLES.DETAILEDCLAIM").split("~"));					
					String detailedClaimFlg="Y";
					int len1=detailedClaim.length;
					for(int i=0;i<len1;i++)
					{
						if(detailedClaim[i].equalsIgnoreCase(lStrUserRole))
						{
							detailedClaimFlg="N";
							break;
						}
					}
					request.setAttribute("detailedClaimFlg", detailedClaimFlg);
				
					String patientScheme=null;
					patientScheme=preauthService.getPatientScheme(lStrCaseId);
					
				//	List<EhfPatientDocAttach> asritPatientDocAttach = preauthService.getOnBedPhotoDtls(commonDtls.get(0).getPATID());
					String patOnBedPic=null;
					// get patient photo path
					 PatientVO patientVo1=new PatientVO();
					 patientVo1.setCardNo(commonDtls.get(0).getCARDNO());
					 patientVo1.setCardType(commonDtls.get(0).getCardType());
					 patientVo1.setPatientScheme(patientScheme);
					 
					 patientVo1=preauthService.retrieveCardDetails(patientVo1);
					 System.out.println("Patient Commnn Dtls line 234-------");
					 System.out.println(patientVo1);
					if(patientVo1 != null && patientVo1.getPhoto() != null && !patientVo1.getPhoto().isEmpty())
					{
						patOnBedPic = patientVo1.getPhoto();
						 try
	        			 {
	        				 File photo = new File(patOnBedPic);
	        				 FileInputStream fis = new FileInputStream(photo);
	        				 DataInputStream dis= new DataInputStream(fis);
	        				 byte bytes[] = new byte[dis.available()];
	        				 fis.read(bytes);
	        				 String lStrContentType=null;
	        				 lStrContentType="image/jpg";
	        				 session.setAttribute("ContentType", lStrContentType);
	        				 session.setAttribute("File", bytes);
	        				 fis.close();
	        				 dis.close();
	        				 patOnBedPic="Y";
	        			 }
	        			 catch(Exception e)
	        			 {
	        				 e.printStackTrace();
	        				 patOnBedPic="N";
	        				 //GLOGGER.error ( "Exception occured while photo is not available in the path specified in PatientAction." +e.getMessage()) ;
	        			 }
					}
					else if(lStrUserRole != null && lStrUserRole.equalsIgnoreCase(config.getString("SHOWROLE.ONBEDPIC")) &&
							(commonDtls.get(0).getCASESTAT()).equalsIgnoreCase(config.getString("SHOWROLE.BEDUPDSTATUS"))
							&& (("1".equalsIgnoreCase(request.getParameter("RestrictFlag")) || "1".equalsIgnoreCase((String)request.getAttribute("RestrictFlag")) )))
					{
						patOnBedPic="Y";
					}
					if(commonDtls.get(0).getNewBornBaby()!=null )
						if(commonDtls.get(0).getNewBornBaby().equalsIgnoreCase("Y"))
							{
								patOnBedPic="N";
							}
					System.out.println("Patient Commnn Dtls line 266-------");
					model.addAttribute(new UploadItem());
					request.setAttribute("patOnBedPic",patOnBedPic);					
					request.setAttribute("caseId", lStrCaseId);
					caseScheme=commonDtls.get(0).getScheme();
					request.setAttribute("caseScheme",caseScheme );
					System.out.println("caseScheme is "+caseScheme);
				}
				
			  List<LabelBean> familyHistoryList=null;
			  familyHistoryList=commonService.getFamilyHistory();
			  request.setAttribute("familyHistoryList", familyHistoryList);
			  
			  List<LabelBean> pastHistoryList=null;
		      pastHistoryList=commonService.getPastIllnessHitory();
		      request.setAttribute("pastHistoryList", pastHistoryList);
			  	      
			  HashMap<String,String> lParamMap=new HashMap<String,String>();
		      if(request.getParameter("CaseId")!=null)	    	  
	    		  lStrCaseId=request.getParameter("CaseId");
		      lParamMap.put("PatientID",request.getParameter("PatientID"));
		      lParamMap.put("CaseId",lStrCaseId);		      
		      //Map lReferalDtlsMap = .getPatientOP(lParamMap);
		      //request.setAttribute("PatientOpList",lReferalDtlsMap);		
			  //sai krishna:#CR8566:method to get the dialysis cycles count to  link display condition
		      String DialysisCnt = preauthService.getDailysisCnt(lStrCaseId);
		      if(DialysisCnt!=null && !DialysisCnt.isEmpty()){
		    	  request.setAttribute("DialysisCnt",DialysisCnt);
		      }
		      PreauthVO preauthVO = preauthService.getPatientOpDtls(lStrCaseId, request.getParameter("PatientID"));
		      request.setAttribute("PatientOpList",preauthVO);
		      
		      List<LabelBean> symptomsList = new ArrayList<LabelBean>();
		      symptomsList = preauthService.getSymptomsDtls(lStrCaseId);
			  request.setAttribute("symptomsList",symptomsList);
				/**
				 * get telephonic remarks
				 */
			  preauthVO = preauthService.getTelephonicDtls(lStrCaseId);
			  if(preauthVO.getTelephonicFlag() != null && preauthVO.getTelephonicFlag().equals("Y"))
			  {
				  request.setAttribute("telephonicRemks",preauthVO.getTelephonicRemarks());  
			  }
			  
		      lStrResultPage="Preauth/patientOPDtls"; 
		  }
			
		
			/**
			 * FRAUD CR TAB
			 */
		 /*else if("viewCMAremarks".equalsIgnoreCase(lStrActionFlag))
	      {	        	        	       
	        request.setAttribute("CmaCnt",request.getParameter("CmaCnt"));
	          request.setAttribute("Count",request.getParameter("Count"));
	          
			  String lStrDiscussionNotesFlag="N";
			  CmaAuditVO cmaVO=null;
			  ArrayList<ClaimsFlowVO> discussionList = null;
			  final String EMPTY_STRING = "";
			  final String NONE_STRING = "none";
			  String L1DisplayRmrks = NONE_STRING;
			  String L1SubButton = NONE_STRING;
			  String L2DisplayRmrks = NONE_STRING;
			  String L2SubButton = NONE_STRING;
			  String L3DisplayRmrks = NONE_STRING;
			  String L3SubButton = NONE_STRING;
	    	  if(request.getParameter("CaseId")!=null)
	    	  {
	    		  lStrCaseId=request.getParameter("CaseId");
	    		  request.setAttribute("CaseId",lStrCaseId);
	    		  Map lResMap=preauthService.getFraudCrDtls(lStrCaseId);
	    		  
	    		  if(lResMap != null && lResMap.get("cmaAuditVO") !=null)
	    		  {
	    			  cmaVO=(CmaAuditVO)lResMap.get("cmaAuditVO");
	    			  request.setAttribute("cmaVO",cmaVO);
	    		  }
	    		  
	    		  if(lResMap != null && lResMap.get("discList") !=null)
	    		  {
	    			  discussionList = (ArrayList<ClaimsFlowVO>)lResMap.get("discList");	    			  
	    		  }   
	    		  request.setAttribute("discussionList",discussionList);
	    	  }
	    	  //For Discussion notes conditions
	    	  if(config.getString("EHF.FRAUDCR.DISCUSSIONNOTES.ROLES")!=null)
	    	  {	    		  
	    		  String[] discussionNotesRoles=new String[(config.getString("EHF.FRAUDCR.DISCUSSIONNOTES.ROLES").split("~")).length];
	    		  discussionNotesRoles=config.getString("EHF.FRAUDCR.DISCUSSIONNOTES.ROLES").split("~");
	    		  if(discussionNotesRoles!=null)
	    		  {
	    			  for(int i=0;i<discussionNotesRoles.length;i++){
	    				if(discussionNotesRoles[i].equalsIgnoreCase(lStrUserRole))
	    					lStrDiscussionNotesFlag="Y";	    				
	    			  }	    			  
	    		  }
	    	  }
	    	  request.setAttribute("DiscussionNotesFlag",lStrDiscussionNotesFlag);
	    	  
	    	  //Conditions for remarks display
	    	  if(lStrUserRole.equalsIgnoreCase(config.getString("EHF.FRAUDCR.Role.DEOMedicalAudit")) && cmaVO.getJEORMKS().trim().length() == 0 && cmaVO.getCMARMKS().trim().length() == 0 )
	    		 {
	    			if (cmaVO.getDEORMKS().trim().length() == 0 && (config.getString("EHF.FRAUDCR.CASE.ClaimSettled")).equalsIgnoreCase(cmaVO.getCASESTATUSCD()))
	    			{
	    				L1DisplayRmrks = EMPTY_STRING;
	    				L1SubButton = EMPTY_STRING;
	    			}
	    			else
	    			{
	    				L1DisplayRmrks = EMPTY_STRING;
	    			}
	    		 }
	    		 
	    		 if(lStrUserRole.equalsIgnoreCase(config.getString("EHF.FRAUDCR.Role.JEOMedicalAudit")) && cmaVO.getCMARMKS().trim().length() == 0)
	    		 {
	    			if (cmaVO.getJEORMKS().trim().length() == 0 && (config.getString("EHF.FRAUDCR.CASE.ClaimSettled")).equalsIgnoreCase(cmaVO.getCASESTATUSCD()))
	    			{
	    				L2DisplayRmrks = EMPTY_STRING;
	    				L2SubButton = EMPTY_STRING;
	    			}
	    			else
	    			{
	    				L2DisplayRmrks = EMPTY_STRING;
	    			}
	    		 }
	    		 
	    		 if(lStrUserRole.equalsIgnoreCase(config.getString("EHF.FRAUDCR.Role.CMA")))
	    		 {
	    			
	    			if (cmaVO.getCMARMKS().trim().length() == 0 && (config.getString("EHF.FRAUDCR.CASE.ClaimSettled")).equalsIgnoreCase(cmaVO.getCASESTATUSCD()))
	    			{
	    				L3DisplayRmrks = EMPTY_STRING;
	    				L3SubButton = EMPTY_STRING;
	    			}
	    			else
	    			{
	    				L3DisplayRmrks = EMPTY_STRING;
	    			}
	    		 }
	    			    	  
	    		 if(config.getString("EHF.FRAUDCR.ROLES")!=null)
		    	  {	    		  
		    		  String[] fraudCrRoles=(config.getString("EHF.FRAUDCR.ROLES").split("~"));
		    		  if(fraudCrRoles!=null)
		    		  {
		    			  for(int i=0;i<fraudCrRoles.length;i++){
		    				if(fraudCrRoles[i].equalsIgnoreCase(lStrUserRole))
		    				{
		    					if (cmaVO.getDEORMKS().trim().length() > 0)
		    					{
		    						L1DisplayRmrks = EMPTY_STRING;
		    					}
		    				if (cmaVO.getJEORMKS().trim().length() > 0)
		    					{
		    						L2DisplayRmrks = EMPTY_STRING;
		    					}
		    				if (cmaVO.getCMARMKS().trim().length() > 0)
		    					{
		    						L3DisplayRmrks = EMPTY_STRING;
		    					}
		    				}		    									
		    			  }	    			  
		    		  }
		    	  }	    		 
		    	  request.setAttribute("L1DisplayRmrks",L1DisplayRmrks);		    	  
		    	  request.setAttribute("L2DisplayRmrks",L2DisplayRmrks);
		    	  request.setAttribute("L3DisplayRmrks",L3DisplayRmrks);
		    	  request.setAttribute("L1SubButton",L1SubButton);
		    	  request.setAttribute("L2SubButton",L2SubButton);
		    	  request.setAttribute("L3SubButton",L3SubButton);
		    	  request.setAttribute("UserRole",lStrUserRole);
	    	  lStrResultPage="Preauth/fraudCrDtls";
	      }
			
			
			 * Fraud CR saving
			 
			
	      else if ("SaveCMAremarks".equalsIgnoreCase(lStrActionFlag))
	      {	        
	        CmaAuditVO cmaVO = new CmaAuditVO();	        
	        cmaVO.setCASEID((String)request.getParameter("CaseId"));
	        cmaVO.setCRTUSERROLE(lStrUserRole);
	        if(config.getString("EHF.FRAUDCR.Role.DEOMedicalAudit").equalsIgnoreCase(lStrUserRole))
	          {
	            cmaVO.setDEOUSERID((String)session.getAttribute("UserID"));
	            cmaVO.setDEORMKS(request.getParameter("L1Rmrks") != null ?request.getParameter("L1Rmrks") : "");
	          }
	        else if(config.getString("EHF.FRAUDCR.Role.JEOMedicalAudit").equalsIgnoreCase((String)session.getAttribute("UserRole")))
	          {
	            cmaVO.setJEOUSERID((String)session.getAttribute("UserID"));
	            cmaVO.setJEORMKS(request.getParameter("L2Rmrks") != null ?request.getParameter("L2Rmrks") : "");
	          }  
	        else if(config.getString("EHF.FRAUDCR.Role.CMA").equalsIgnoreCase((String)session.getAttribute("UserRole")))
	          { 
	            cmaVO.setCMAUSERID((String)session.getAttribute("UserID"));
	            cmaVO.setCMARMKS(request.getParameter("L3Rmrks") != null ?request.getParameter("L3Rmrks") : "");
	          }	  
	        
		      Map lResMap=preauthService.saveFraudCrDtls(cmaVO);
		      if(lResMap != null && lResMap.get("cmaAuditVO") !=null)
			  {
				  cmaVO=(CmaAuditVO)lResMap.get("cmaAuditVO");
				  request.setAttribute("cmaVO",cmaVO);
			  }	        
	      	request.setAttribute("CaseId",request.getParameter("CaseId"));	      
	      	lStrResultPage="Preauth/fraudCrDtls";
	      }*/			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(lStrResultPage);
	}	
}

