package com.ahct.preauth.action;
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
import java.util.Collections;
import java.util.Iterator;
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

import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.util.ClaimCases;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.patient.action.PatientAction;
import com.ahct.patient.service.PatientService;
import com.ahct.preauth.util.PreauthConstants;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.form.PreauthDetailsForm;
import com.ahct.preauth.service.PreauthClinicalNotesService;
import com.ahct.preauth.service.PreauthService;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.preauth.vo.cochlearCaseVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.service.TimeLoggingService;
import com.ahct.login.service.LoginService;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseMedicalDtls;
import com.ahct.model.EhfCaseSurgicalDtls;
import com.ahct.model.EhfPatient;
import com.google.gson.Gson;

public class PreauthDetailsAction extends Action{
	private final static Logger GLOGGER = Logger.getLogger ( PreauthDetailsAction.class ) ;
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	    {
		 response.setHeader("Cache-Control","no-cache");
	        response.setHeader("pragma","no-cache");
	        response.setDateHeader("Expires", 0);  
	        String callType=null;
	        
	        if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
	        {
	        callType=request.getParameter("callType");
	        }
	        HttpSession session = request.getSession ( false ) ;
	        String lStrResultPage = HtmlEncode.verifySession(request, response);
	        if (lStrResultPage.length() > 0)
	        {
	        	 request.setAttribute("caseSession", "Y");
	        	if(callType!=null && "Ajax".equals(callType))
	        	{
	        	request.setAttribute("AjaxMessage","SessionExpired");
	        	return mapping.findForward("ajaxResult");
	        	}
	        	else
	            return mapping.findForward("regSessionExpire");
	        }
	        String lStrFlagStatus = request.getParameter("actionFlag");
	        String lStrEmpId = null ;
		    //String lStrLocId = null ;
		    String lStrLangId = null ;
		    //String lStrUserName = null;
		    String lStrUserGroup = null;
		    String caseApprovalFlag = null;
		    String enhancementFlag = null;
		   // String enhCaseStatus = null;
		    String ceoApprovalFlag = null;
		    String schemeId=null;
		    SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
		    /**
		     * added for groups
		     */
		    String preauthCaseId = null;
		    String preauthCaseStatus  = null;
		  //  String lStrUserRole = null;
		    PreauthVO preauthVO = new PreauthVO();
		    PreauthDetailsForm preauthDetailsForm = ( PreauthDetailsForm ) form ;
		   
	        if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
			    lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
	        
	        if(session.getAttribute("userState").toString()!=null)
			{
				schemeId=session.getAttribute("userState").toString();
			}
	        
			    /*if ( ( session.getAttribute ( "LocID" ) != null ) && !( session.getAttribute ( "LocID" ).equals ( "" ) ) )
			    	lStrLocId = ( String ) session.getAttribute ( "LocID" ) ;*/
			    if ( ( session.getAttribute ( "LangID" ) != null ) && !( session.getAttribute ( "LangID" ).equals ( "" ) ) )
			    	lStrLangId = ( String ) session.getAttribute ( "LangID" ) ;
			    /*if ( ( session.getAttribute ( "userName" ) != null ) && !( session.getAttribute ( "userName" ).equals ( "" ) ) )
			    	lStrUserName = ( String ) session.getAttribute ( "userName" ) ;
			     if ( ( session.getAttribute ( "UserRole" ) != null ) && !( session.getAttribute ( "UserRole" ).equals ( "" ) ) )
			    	lStrUserRole = ( String ) session.getAttribute ( "UserRole" ) ;*/
			    // added for group mapping
			    List<LabelBean> groupsList = null;
			    if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
			    	groupsList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
			    
			    PreauthService preauthService = (PreauthService)ServiceFinder.getContext(request).getBean("preauthService");
			    CommonService  commonService = (CommonService)ServiceFinder.getContext(request).getBean("commonService");
			    PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
			    LoginService loginService =(LoginService)ServiceFinder.getContext(request).getBean("loginService");
			    PreauthClinicalNotesService  preauthClinicalNotesService = (PreauthClinicalNotesService)ServiceFinder.getContext(request).getBean("preauthClinicalNotesService");
			    ConfigurationService configurationService=(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
			    TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		        Configuration config = configurationService.getConfiguration();
		    	preauthVO.setPhase("");
		    	preauthVO.setRenewal("");
		    	
		    	/**
		    	 * get case status based on caseId and for getting user groups
		    	 */
		    	preauthCaseId = request.getParameter("caseId");
		    	EhfCase ehfCase=null;
		    	if(preauthCaseId == null || preauthCaseId.equalsIgnoreCase(""))
		    	{
		    		preauthCaseId = preauthDetailsForm.getCaseId();	
		    	}
		    	if(preauthCaseId != null && !preauthCaseId.equalsIgnoreCase(""))
		    	{
		    	preauthCaseStatus = preauthService.getCaseStatus(preauthCaseId);
		    	ceoApprovalFlag=preauthService.getceoApprovalFlag(preauthCaseId);
		    	preauthDetailsForm.setCaseId(preauthCaseId);
		    	}
		    	
		    	
		    	/*if(preauthCaseStatus!=null && (preauthCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_cpd_recApprove"))))
		    	{
		    		lStrUserGroup = config.getString("userGroup_CD1211");	
		    	}
		    	
		    	*/
		    	if(preauthCaseStatus!=null && (preauthCaseStatus.equalsIgnoreCase(config.getString("preauth_ptd_recommended_approve")) || preauthCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_approve"))) && ceoApprovalFlag!= null && ceoApprovalFlag.equalsIgnoreCase("Y"))
		    	{
		    		lStrUserGroup = config.getString("userGroup_eo");	
		    	}
		    	else
		    		lStrUserGroup = config.getString("userGroup_"+preauthCaseStatus);	
		    	boolean groupFlag = false;
		    	boolean isMedco_Mithra= false;
		    	boolean isMedco=false;
		    	boolean holdAccess = false;
		    	
		    	for(LabelBean labelBean:groupsList)
		    	{
		    		if(labelBean.getID() != null && config.getString("HOLD_UNHOLD_ACCESS_GROUPS") != null && (config.getString("HOLD_UNHOLD_ACCESS_GROUPS")).contains("~"+ labelBean.getID() +"~")){
		    			holdAccess = true;
		    		}
		    	if(labelBean.getID() != null && lStrUserGroup!=null && lStrUserGroup.contains("~"+labelBean.getID()+"~") )
		    	{
		    	groupFlag=true;
		    	lStrUserGroup= labelBean.getID();
		    	break;	
		    	}}
		    	for(LabelBean labelBean:groupsList)
		    	{
		    		if(labelBean.getID() != null && (config.getString("preauth_medco_role").equalsIgnoreCase(labelBean.getID())))
			    	{
			    		isMedco_Mithra=true;
			    		isMedco=true;
			    		request.setAttribute("isMedco", isMedco);
			    	break;	
			    	}	
		    	if(labelBean.getID() != null && ("GP1".equalsIgnoreCase(labelBean.getID()) || "GP2".equalsIgnoreCase(labelBean.getID())))
		    	{
		    		isMedco_Mithra=true;
		    	}}
		    	if(!groupFlag)
		    	{
		    		lStrUserGroup = null;
		    	}
		    	if(groupsList == null || groupsList.isEmpty() )
		    	{
		    		lStrUserGroup = null;	
		    	}
		    	caseApprovalFlag  = request.getParameter("caseApprovalFlag");
		    	if(caseApprovalFlag != null && caseApprovalFlag.equalsIgnoreCase("N"))
		    	{
		    		lStrUserGroup = null;	
		    		request.setAttribute("caseApprovalFlag", caseApprovalFlag);
		    	}
		    	else if (caseApprovalFlag != null && caseApprovalFlag.equalsIgnoreCase("Y")
		    		&& lStrUserGroup==null)
		    		{
		    			//For Dental Cases Activating PTD for PEX Forwarded/PPD pending updated Cases
		    			if(config.getString("preauth_Dent_panelDocStatus").contains("~"+preauthCaseStatus+"~"))
		    				{
			    				List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(preauthDetailsForm.getCaseId());
						    	String dentFlag="N";
						    	if(lstSurgyDtls!=null && lstSurgyDtls.size()>0)
						    		{
						    			for(PreauthVO localVO :lstSurgyDtls)
						    				{
						    					if(localVO.getCatId()!=null && 
						    							localVO.getCatId().equalsIgnoreCase(config.getString("DentalSurgeryID")))
						    						{
						    							dentFlag="Y";
						    							break;
						    						}
						    				}
						    		}
						    	if(dentFlag!=null && dentFlag.equalsIgnoreCase("Y"))
					    			{
				    					for(LabelBean labelBean:groupsList)
								    		{
									    		if(labelBean.getID() != null && (config.getString("preauth_ptd_role").equalsIgnoreCase(labelBean.getID())))
											    	{
									    				lStrUserGroup=config.getString("preauth_ptd_role");
											    		break;	
											    	}	
									    	}
					    			}	
		    				}
		    		}
		    	/**
		    	 * end of getting user gropus
		    	 */
		    	List<String> lStrgrpList=new ArrayList<String>();
		    	String roleId=null;
		    	for(LabelBean labelBean:groupsList)
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
				else if(lStrgrpList.contains(config.getString("Group.Pex")))
				{
					roleId=config.getString("Group.Pex");
				}
				else
				{
					roleId=lStrgrpList.get(0);
				}
		    	/**
		    	 * Stay Type List
		    	 */
		    	List<LabelBean> StayTypeList = new ArrayList<LabelBean>();
		    	StayTypeList.add(new LabelBean("postTrtEvd","Post Treatment Evidence"));
		    	StayTypeList.add(new LabelBean("preOp","Pre OP"));
		    	StayTypeList.add(new LabelBean("ot","OT"));
		    	StayTypeList.add(new LabelBean("postOp","Post OP"));
		    	request.setAttribute("StayTypeList", StayTypeList);
		    	/**
		    	 * stay Type list 2 
		    	 */
		    	List<LabelBean> StayTypeList2 = new ArrayList<LabelBean>();
		    	StayTypeList2.add(new LabelBean("1","OP(Visits)"));
		    	StayTypeList2.add(new LabelBean("2","ICU with Ventilators(hrs)"));
		    	StayTypeList2.add(new LabelBean("3","Post OP Ward(days)"));
		    	StayTypeList2.add(new LabelBean("4","Casuality (hrs)"));
		    	StayTypeList2.add(new LabelBean("5","Ward (days)"));
		    	StayTypeList2.add(new LabelBean("6","ICU without ventilator(days)"));
		    	StayTypeList2.add(new LabelBean("7","OT (if Surgeries - min)"));
		    	request.setAttribute("StayTypeList2", StayTypeList2);
		    	request.setAttribute("userRole", lStrUserGroup);
			    if(lStrFlagStatus!= null && "preauthDetails".equals(lStrFlagStatus)) 
		        {
			    
			    	List<LabelBean> docTypeList = new ArrayList<LabelBean>();
			    	docTypeList.add(new LabelBean("N","Inhouse Doctor"));
			    	docTypeList.add(new LabelBean("Y","Consultant Doctor"));
			    	request.setAttribute("docTypeList", docTypeList);
			    	
			    	List<LabelBean> docSpecList = new ArrayList<LabelBean>();
			    	docSpecList = preauthService.getDocSpecList(preauthVO) ;
			    	request.setAttribute("docSpecList", docSpecList);
			    	request.setAttribute("docAvalList", new ArrayList<LabelBean>());
			    	
			    	
			    	return mapping.findForward("preauthDtls");
			    	
		        }
				if(lStrFlagStatus!= null && "savePreauthDtls".equals(lStrFlagStatus)) 
		        {
                  
					String caseId=request.getParameter("caseId");
					String ipFlag=request.getParameter("ipFlag");
					preauthVO.setCruUsr(lStrEmpId);
					preauthVO.setCaseId(caseId);
					preauthVO.setIpOpFlag(ipFlag);
					if(!"".equalsIgnoreCase(ipFlag) && "IPM".equalsIgnoreCase(ipFlag))
					preauthVO.setEnhAmt(preauthDetailsForm.getEnhAmt());
					preauthVO.setTreatmntDt(preauthDetailsForm.getTreatmntDt().replace("-", "/"));
					preauthVO.setDischargeDt(preauthDetailsForm.getDischrgeDt().replace("-", "/"));
										
					String rslt = preauthService.savePreauthDtls(preauthVO);
					if(!"".equalsIgnoreCase(rslt) && "true".equalsIgnoreCase(rslt))
					{
						preauthDetailsForm.setMsg("Discharge Updated");
					}
					//request.setAttribute("msg", rslt);
			    	lStrFlagStatus="preauthDetailsEhf";
		        }
				if(lStrFlagStatus!= null && "updateRemarks".equals(lStrFlagStatus))  
		        {
					String caseId=request.getParameter("caseId");
					preauthVO.setCruUsr(lStrEmpId);
					preauthVO.setCaseId(caseId);
					preauthVO.setRemarks(preauthDetailsForm.getMedcoRmrks());
					String rslt = preauthService.updateMedcoRemarks(preauthVO);
					if(!"".equalsIgnoreCase(rslt) && "true".equalsIgnoreCase(rslt))
					{
						preauthDetailsForm.setMsg("Remarks Updated");
					}
			    	lStrFlagStatus="preauthDetailsEhf";
		        }
			    if(lStrFlagStatus!= null && "saveEnhDtls".equals(lStrFlagStatus)){
			    	preauthVO.setCaseId(preauthDetailsForm.getCaseId());
			    	//preauthVO.setPatientID(preauthDetailsForm.getPatientId());
			    	preauthVO.setDrugs(preauthDetailsForm.getDrugs());
			    	preauthVO.setEnhancementDtls(preauthDetailsForm.getEnhancementDtls());
			    	preauthVO.setCruUsr(lStrEmpId);
			    	preauthVO.setEnhAmt(preauthDetailsForm.getEnhAmt());
			    	//Added for enhancement Amounts
			    	preauthVO.setEnhAmounts(preauthDetailsForm.getEnhAmounts());
			    	//Added for Attachments
					FormFile lFormFile=null;
					String lFileName="";
					String lDir=null;
					String lFileExt=null;
					String lStrTotFileName=null;
					Date ldtToday = new Date();
					SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
					long ltime = ldtToday.getTime();
					List<AttachmentVO> lAttachList=new ArrayList<AttachmentVO>();
					List<AttachmentVO> drugAttachList=new ArrayList<AttachmentVO>();
					int lCount=0;
			   
			    		int i=0;
			    		while(preauthDetailsForm.getMedclExpnsAttch(i) !=null )
						{
								AttachmentVO attachmentVO=new AttachmentVO();											
								lFormFile=preauthDetailsForm.getMedclExpnsAttch(i);                                     
								lFileName=lFormFile.getFileName();
								if(lFileName!=null && !lFileName.equals(""))
								{
									lCount=lFileName.lastIndexOf(".");
									lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
									// save file in folder's
									String lStrSharePath = 
											config.getString("STORAGE_BOX") + 
											/*config.getString("SLASH_VALUE")+*/  sdf1.format(ldtToday) + 
											config.getString("SLASH_VALUE") + preauthDetailsForm.getCaseId() + 
											config.getString("SLASH_VALUE") + 
											"MEDCLEXPENSESATTACH"+ 
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
								
								
								if(lFileName!=null && !lFileName.equals(""))
								{
									attachmentVO.setFileName(lFileName);
									attachmentVO.setAttachPath(lDir);
									
								}
								lAttachList.add(attachmentVO);                 
								i++;
						}
			    		preauthVO.setAttachmentsList(lAttachList);
			    		
			    	//Drugs Attachments
			    		int k=0;
			    		while(preauthDetailsForm.getMedclDrugsAttch(k) !=null )
						{
								AttachmentVO attachmentVO=new AttachmentVO();											
								lFormFile=preauthDetailsForm.getMedclDrugsAttch(k);                                     
								lFileName=lFormFile.getFileName();
								if(lFileName!=null && !lFileName.equals(""))
								{
									lCount=lFileName.lastIndexOf(".");
									lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
									String lStrSharePath = 
											config.getString("STORAGE_BOX") + 
											 sdf1.format(ldtToday) + 
											config.getString("SLASH_VALUE") + preauthDetailsForm.getCaseId() + 
											config.getString("SLASH_VALUE") + 
											"DRUGS_ATTACH"+ 
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
								
								
								if(lFileName!=null && !lFileName.equals(""))
								{
									attachmentVO.setFileName(lFileName);
									attachmentVO.setAttachPath(lDir);
									
								}
								drugAttachList.add(attachmentVO);                 
								k++;
						}
			    		preauthVO.setDrugsAttchList(drugAttachList);
			    	
			    	preauthDetailsForm.setDrugs("");
			    	preauthDetailsForm.setEnhancementDtls("");
			    	String nimsHospIdNew=preauthService.getHospScheme(preauthDetailsForm.getCaseId());
					 preauthVO.setHospitalId(nimsHospIdNew);
					 String patType=preauthService.getPatType(preauthDetailsForm.getPatientId());
					 preauthVO.setPatientID(patType);
			    	String msg = preauthService.saveEnhDtls(preauthVO);	
			    	request.setAttribute("msg", msg);
			    	String statusCheck = preauthService.getStatusCase(preauthDetailsForm.getCaseId());
			    	if(!"".equalsIgnoreCase(statusCheck) && statusCheck != null)
			    	{
			    		request.setAttribute("msg", statusCheck);
			    		preauthDetailsForm.setMsg(statusCheck);
			    	}
			    	ClaimsFlowVO claimFlowVO=new ClaimsFlowVO();
			    	 claimFlowVO.setCaseId(preauthDetailsForm.getCaseId());
			    		//request.setAttribute("msgNew", 'Y');
			    	//Added for IP Medical Scenarios 	
			    	 claimFlowVO = preauthService.getDrugDetailsData(claimFlowVO);
					 claimFlowVO = preauthService.getInvestigationDetails(claimFlowVO);
					 claimFlowVO = preauthService.saveEnhanceAmounts(claimFlowVO);
					 if(claimFlowVO.getEnhAmounts() != null)
					 {
						 preauthDetailsForm.setEnhAmounts(claimFlowVO.getEnhAmounts().toString());
					 }
					 else
					 {
						 preauthDetailsForm.setEnhAmounts("");
					 }
			    	lStrFlagStatus="preauthDetailsEhf";
			    	
			    }
			    
			    if(lStrFlagStatus!= null && "deleteEnhanceDtls".equals(lStrFlagStatus)) 
		        {
			    	String caseId= request.getParameter("caseId");
			    	String sno= request.getParameter("sno");
			    	preauthVO.setCaseId(caseId);
			    	preauthVO.setIcdCatCode(sno);
			    	 ClaimsFlowVO claimFlowVO=new ClaimsFlowVO();
			    	 claimFlowVO.setCaseId(caseId);
			    	String msg = preauthService.deleteEnhnDtls(preauthVO);
			    	//Added for IP Medical Scenarios 	
					
			    	 claimFlowVO = preauthService.getDrugDetailsData(claimFlowVO);
					 claimFlowVO = preauthService.getInvestigationDetails(claimFlowVO);
					 claimFlowVO = preauthService.saveEnhanceAmounts(claimFlowVO);
			    	request.setAttribute("AjaxMessage",msg);
			    	return mapping.findForward("ajaxResult");
		        }
			    if(lStrFlagStatus!= null && "getDocAvailLst".equals(lStrFlagStatus)) 
		        {
			    	preauthVO.setDisMainId(request.getParameter("docSpec"));
			    	preauthVO.setConsultant(request.getParameter("docType"));
			    	List<LabelBean> getdocList = new ArrayList<LabelBean>();
			    	getdocList = preauthService.getDocAvailLst(preauthVO) ;
			    	List<LabelBean> getSubDiseases = preauthService.getSubDiseases(preauthVO);
			    	
			    	request.setAttribute("AjaxMessage",getdocList);
			    	request.setAttribute("AjaxMessage1",getSubDiseases);
			    	return mapping.findForward("ajaxResult");	
		        }
			    if(lStrFlagStatus!= null && "getDocdetails".equals(lStrFlagStatus)) 
		        {
			    	preauthVO.setRegNo(request.getParameter("regNo"));
			    	//List<LabelBean> getdocList = new ArrayList<LabelBean>();
			    	String getdocList = preauthService.getDocdetails(preauthVO) ;
			    	request.setAttribute("AjaxMessage",getdocList);
			    	return mapping.findForward("ajaxResult");	
		        }
			    if(lStrFlagStatus!= null && "getSurgeryLst".equals(lStrFlagStatus)) 
		        {
			    	preauthVO.setDisMainId(request.getParameter("disMainId"));
			    	preauthVO.setTherapyType(request.getParameter("therapyType"));
			    	List<LabelBean> getSurgeryList = preauthService.getSurgeryList(preauthVO);
			    	request.setAttribute("AjaxMessage",getSurgeryList);
			    	return mapping.findForward("ajaxResult");	
		        }
			    if(lStrFlagStatus!= null && "getSpecialInvestigation".equals(lStrFlagStatus)) 
		        {
			    	preauthVO.setSurgeryId(request.getParameter("icdProcCode"));
			    	preauthVO.setAsriCatName(request.getParameter("catCode"));
			    	preauthVO.setScheme(request.getParameter("schemeId"));
			    	List<LabelBean> getSpecialInvestigationLst = preauthService.getSpecialInvestigationLst(preauthVO);
			    	request.setAttribute("AjaxMessage",getSpecialInvestigationLst);
			    	return mapping.findForward("ajaxResult");	
		        }
			    if(lStrFlagStatus!= null && "uploaddCaseAttach".equals(lStrFlagStatus)) 
		        {
			    
			    	String caseId = request.getParameter("caseId");
			    	String surgeryId = request.getParameter("surgId");
			    	String investId = request.getParameter("splinvestid");
			    	String spltType = request.getParameter("spltype");
			    	String dir = null;
			    	if(preauthDetailsForm.getAttachedIndex(1)!= null)
			    	{
				    	FormFile obj = 	preauthDetailsForm.getAttachedIndex(1);
				    	if(obj.getFileName() != null)
				    	{
					    	if(obj.getFileSize() < 204800)
					    	{
					    		 String lStrSharePath = config.getString("mapPath")+config.getString("preauthSplInvest")+caseId+"/";
					             String filePath= lStrSharePath + spltType; 
					             java.util.Date ldtToday = new java.util.Date();
			                     String fullPath = filePath ; 
			                     dir =fullPath;
			                     boolean flag = ( new File ( dir ) ).mkdirs () ;
			                     dir = dir +"/"+ ldtToday.getTime()+"_"+obj.getFileName();
			                     File lFileFS = new File ( dir ) ;
			                       FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
			                       fos.write ( obj.getFileData() ) ;
			                       AttachmentVO attachmentVO = new AttachmentVO();
			                       attachmentVO.setActualName(obj.getFileName());
			                       attachmentVO.setSavedName(dir);
			                       attachmentVO.setCrtUsrName(lStrEmpId);
			                       attachmentVO.setAttachType("CD309");
			                       attachmentVO.setCaseId(caseId);
			                       attachmentVO.setSurgeryId(surgeryId);
			                       attachmentVO.setSpltType(spltType);
			                       attachmentVO.setSurgInvstId(investId);
			                     //  String msg1 = attachmentService.upldCaseAttachments(attachmentVO);   
			                       String msg1 = preauthService.upldSplInvestAttachments(attachmentVO);   
			                       request.setAttribute("resMsg", msg1);
					    	}
					    	else
					    	{
					    		 String msg1 = ASRIConstants.ATTCH_SIZE_EXCEED_ERROR+ " in \\'"+ obj.getFileName()+ "\\' \\n";
					    		 request.setAttribute("resMsg", msg1);
					    	}
				    	}
				    	lStrFlagStatus = "getCaseAtachments";
			    	}			    	
			    }
			    
			    if(lStrFlagStatus!= null && "deleteSplInvstAttch".equals(lStrFlagStatus)) 
		        {
			    	String sno = request.getParameter("sno");
			    	String msg =preauthService.deleteSplInvstAttach(sno,lStrEmpId);  
			    	request.setAttribute("resMsg", msg);
			    	lStrFlagStatus="getCaseAtachments";	
		        }
			    if(lStrFlagStatus!= null && "getCaseAtachments".equals(lStrFlagStatus)) 
		        {
			    	String caseId = request.getParameter("caseId");
			    	String surgeryId = request.getParameter("surgId");
			    	String investId = request.getParameter("splinvestid");
			    	String spltType = request.getParameter("spltype");
			    	request.setAttribute("caseId", caseId);
			    	request.setAttribute("surgeryId", surgeryId);
			    	request.setAttribute("investId", investId);
			    	request.setAttribute("spltType", spltType);
			    	
			    	preauthVO.setSurgeryId(surgeryId);
			    	preauthVO.setCaseId(caseId);
			    	preauthVO.setSpltType(spltType);
			    	preauthVO.setSplInvstId(investId);
			    	List<AttachmentVO> lstAttachmentVO = preauthService.getcaseAttachments(preauthVO); 
			    	preauthDetailsForm.setLstAttachments(lstAttachmentVO);
			    	request.setAttribute("lstAttachments", lstAttachmentVO);
			    	return mapping.findForward("caseAttachments");	
		        }
			    if(lStrFlagStatus!= null && "getSurgeryDtls".equals(lStrFlagStatus)) 
		        {
			   
			    preauthVO.setCatId(request.getParameter("catCode")); 
			    preauthVO.setIcdCatCode(request.getParameter("icdCatCode"));
			  //  preauthVO.setProcCode(request.getParameter("procCode"));
			    preauthVO.setIcdProcCode(request.getParameter("icdProcCode"));
			   
			    String getSurgeryDtls = preauthService.getSurgeryDtls(preauthVO);
		    	request.setAttribute("AjaxMessage",getSurgeryDtls);
		    	return mapping.findForward("ajaxResult");
			    
		        }
			    if(lStrFlagStatus!= null && "submitPreauth".equals(lStrFlagStatus)) 
		        {
			    	
			    	System.out.println(request.getParameter("postTrtEvdLabInvestUnitPrice1"));
			    	System.out.println(request.getParameter("postTrtEvdImageologyUnitPrice1"));
			    	System.out.println(request.getParameter("postTrtEvdLabInvestUnitPrice0"));
			    	System.out.println(request.getParameter("postTrtEvdImageologyUnitPrice0"));
			    	 for(int i=0; i<StayTypeList.size();i++)
			    	 {
			    		 
			    		 System.out.println(request.getParameter(StayTypeList.get(i).getID()+"StayType")); 
			    		System.out.println(StayTypeList.get(i).getID()+"LabInvestCount"); 
			    		System.out.println(request.getParameter(StayTypeList.get(i).getID()+"LabInvestCount")); 
			    		System.out.println(request.getParameter(StayTypeList.get(i).getID()+"LabInvestName")); 
			    		System.out.println(request.getParameter(StayTypeList.get(i).getID()+"LabInvestUnitPrice")); 
			    		 
			    	 }
			    	 
			    	 List<PreauthVO> lstLabInvestigations = new ArrayList<PreauthVO>();
			    	 List<PreauthVO> lstImageology = new ArrayList<PreauthVO>();
			    	 List<PreauthVO> lstPharmacy = new ArrayList<PreauthVO>();
			    	 List<PreauthVO> lstImplants = new ArrayList<PreauthVO>();
			    	 
			    	 for(int i=0; i<StayTypeList.size();i++)
			    	 {
			    		 String labInvstCnt = request.getParameter(StayTypeList.get(i).getID()+"LabInvestCount");
			    		 if( labInvstCnt!= null && !labInvstCnt .equalsIgnoreCase(""))
			    		 {
			    			 for(int j=1; j<=Integer.parseInt(labInvstCnt);j++)
				    		 {
			    				 preauthVO = new PreauthVO();
			    				 preauthVO.setStayType(StayTypeList.get(i).getID());
			    				 preauthVO.setName(request.getParameter(StayTypeList.get(i).getID()+"LabInvestName"+j));
			    				 preauthVO.setUnitPrice(request.getParameter(StayTypeList.get(i).getID()+"LabInvestUnitPrice"+j));
			    				 lstLabInvestigations.add(preauthVO);
				    		 }
			    		 }
			    		 String imageologyCnt = request.getParameter(StayTypeList.get(i).getID()+"ImageologyCount");
			    		 if( imageologyCnt!= null && !imageologyCnt .equalsIgnoreCase(""))
			    		 {
			    			 for(int j=1; j<=Integer.parseInt(imageologyCnt);j++)
				    		 {
			    				 preauthVO = new PreauthVO();
			    				 preauthVO.setStayType(StayTypeList.get(i).getID());
			    				 preauthVO.setName(request.getParameter(StayTypeList.get(i).getID()+"ImageologyName"+j));
			    				 preauthVO.setUnitPrice(request.getParameter(StayTypeList.get(i).getID()+"ImageologyUnitPrice"+j));
			    				 lstImageology.add(preauthVO);
				    		 } 
			    		 }
			    		 String drugCnt = request.getParameter(StayTypeList.get(i).getID()+"DrugCount");
			    		 if( drugCnt!= null && !drugCnt .equalsIgnoreCase(""))
			    		 {
			    			 for(int j=1; j<=Integer.parseInt(drugCnt);j++)
				    		 {
			    				 preauthVO = new PreauthVO();
			    				 preauthVO.setStayType(StayTypeList.get(i).getID());
			    				 preauthVO.setName(request.getParameter(StayTypeList.get(i).getID()+"DrugName"+j));
			    				 preauthVO.setRoute(request.getParameter(StayTypeList.get(i).getID()+"DrugRoute"+j));
			    				 preauthVO.setStrength(request.getParameter(StayTypeList.get(i).getID()+"DrugStrength"+j));
			    				 preauthVO.setDosage(request.getParameter(StayTypeList.get(i).getID()+"DrugDosage"+j));
			    				 preauthVO.setDays(request.getParameter(StayTypeList.get(i).getID()+"DrugDays"+j));
			    				 preauthVO.setUnitPrice(request.getParameter(StayTypeList.get(i).getID()+"DrugUnitPrice"+j));
			    				 lstPharmacy.add(preauthVO); 
				    		 }
			    		 }
			    		 String implantCnt = request.getParameter(StayTypeList.get(i).getID()+"ImplantCount");
			    		 if( implantCnt!= null && !implantCnt .equalsIgnoreCase(""))
			    		 {
			    			 for(int j=1; j<=Integer.parseInt(implantCnt);j++)
				    		 {
			    				 preauthVO = new PreauthVO();
			    				 preauthVO.setStayType(StayTypeList.get(i).getID());
			    				 preauthVO.setName(request.getParameter(StayTypeList.get(i).getID()+"ImplantName"+j));
			    				 preauthVO.setUnitPrice(request.getParameter(StayTypeList.get(i).getID()+"ImplantUnitPrice"+j));
			    				 lstImplants.add(preauthVO);
				    		 }  
			    		 }
			    		
			    	 }
			    	
			     	return mapping.findForward("preauthDtls");
		        }
			    if(lStrFlagStatus!= null && "submitPreauthEhf".equals(lStrFlagStatus)) 
		        {
			    	String caseStartTime = sds.format(new Date().getTime());
			    	String buttonVal = request.getParameter("buttonVal");
			    	String enhFlag = request.getParameter("enhFlag");
			    	String ceoFlag=request.getParameter("ceoFlag");
			    	String ipFlag=request.getParameter("ipFlag");
			    	/*
			    	 *get enhancmenet drugs list 
			    	 */
			    	if(enhFlag !=null && enhFlag.equalsIgnoreCase("Y")){
			    		List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
			    		if(preauthDetailsForm.getDrugs() != null && !preauthDetailsForm.getDrugs().equals(""))
			    		{
			    			DrugsVO drugsVO = null;
							String s=preauthDetailsForm.getDrugs().substring(0,preauthDetailsForm.getDrugs().length()-1);
							String[] drugs=s.split("@,");
							System.out.println(s);
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
								drugsVO.setNoOfUnit(drugValues[7]);
								drugsVO.setAmountPerUnit(drugValues[8]);
								drugsVO.setTotAmt(drugValues[9]);
								
								drugsList.add(drugsVO);
							}
			    		}
			    		preauthVO.setDrugList(drugsList);
			    	}
			    	
			    	String msg = null;
			    	preauthVO.setCaseId(preauthDetailsForm.getCaseId());
			    	preauthVO.setSplInvstId(preauthDetailsForm.getSplInvest());
			    	preauthVO.setAdmissionRadio(preauthDetailsForm.getAdmissionType());
			    	preauthVO.setAdmissionDate(preauthDetailsForm.getIpRegDate());
			    	preauthVO.setPatientID(preauthDetailsForm.getPatientId());
			    	preauthVO.setButtonVal(buttonVal);
			    	preauthVO.setUserRole(lStrUserGroup);
			     // preauthVO.setUserRole(lStrUserRole);
			    	String cat=preauthDetailsForm.getCategory();
			    	preauthVO.setCochlearYN(preauthDetailsForm.getCochlearYN());
			    	preauthVO.setOrganTransYN(preauthDetailsForm.getOrganTransYN());
			    	if(("S16").equals(cat))
			    	{
			    		preauthVO.setCochlearYN("Y");
			    	}
			    	if(("S19").equals(cat))
			    	{
			    		preauthVO.setOrganTransYN("Y");
			    	}
			    	preauthVO.setRemarks(preauthDetailsForm.getGenRemarks());
			    	preauthVO.setTotPackgAmt(preauthDetailsForm.getTotPkgAmt());
			    	System.out.println(preauthDetailsForm.getSplInvest());
			    	preauthVO.setCaseSurgId(preauthDetailsForm.getCasSugeryId());
			    	System.out.println(preauthDetailsForm.getCasSugeryId());
			    	preauthVO.setEnhancementDtls(preauthDetailsForm.getEnhancementDtls());
			    	preauthVO.setCruUsr(lStrEmpId);
			    	preauthVO.setLangId(lStrLangId);
			    	preauthVO.setCaseStatusId(preauthDetailsForm.getCaseStatus());
			    	preauthVO.setEnhRemarks(preauthDetailsForm.getEnhRemarks());
			    	preauthVO.setEnhReqAmt(preauthDetailsForm.getEnhAmt());
			    	preauthDetailsForm.setEnhAmt(preauthDetailsForm.getEnhAmt());
			    	preauthVO.setEnhApprvAmt(preauthDetailsForm.getEnhApprvAmt());
			    	preauthVO.setComorBidAmt(preauthDetailsForm.getComorBidAmt());
			    	preauthVO.setComorBidVals(preauthDetailsForm.getComorBidVals());
			    	preauthVO.setPreauthPckgAmt(preauthDetailsForm.getPreauthPckgAmt());
			    //	preauthDetailsForm.setGenRemarks(preauthDetailsForm.getGenRemarks());
			    	preauthVO.setRemEnhList(preauthDetailsForm.getRemEnhList());
			    	preauthVO.setDrugDeletionString(preauthDetailsForm.getDrugDeletionString());
			    	preauthVO.setProcedureConsent(preauthDetailsForm.getProcedureConsent());
			    	preauthVO.setMedCardioClearence(preauthDetailsForm.getMedCardioClearence());
			    	preauthVO.setBloodTransfusion(preauthDetailsForm.getBloodTransfusion());
			    	preauthVO.setHospStayAmt(preauthDetailsForm.getHospStayAmt());
			    	preauthVO.setNabhFlg(preauthDetailsForm.getNabhFlg());
			    	preauthVO.setScheme(preauthDetailsForm.getSchemeId());
			    	if(!"".equalsIgnoreCase(ipFlag) && "IPM".equalsIgnoreCase(ipFlag))
			    	{
			    		preauthVO.setTotPackgAmt(preauthDetailsForm.getPreauthTotalPackageAmt());
			    		preauthVO.setPreauthPckgAmt(preauthDetailsForm.getPreauthTotalPackageAmt());
			    		preauthVO.setPreauthTotalPackageAmt(preauthDetailsForm.getPreauthTotalPackageAmt());
			    		preauthVO.setIpOpFlag(ipFlag);
			    	}
			    	else
			    	{
			    		preauthVO.setIpOpFlag(ipFlag);
			    	}
			    	if(enhFlag != null && !enhFlag.equalsIgnoreCase("") && enhFlag.equalsIgnoreCase("Y"))
			    	msg = preauthService.saveEnhancementPreauth(preauthVO);
			    	else
			    	msg = preauthService.savePreauth(preauthVO);
			    	String caseEndTime = sds.format(new Date().getTime());
			    	String actionDone = commonService.getActionDoneName(preauthVO.getCaseId(),"ehfCase");
			    	loggingService.logTime(actionDone, preauthVO.getCaseId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
			    	request.setAttribute("ResultMsg", msg);
			    	if(msg!=null && !"".equalsIgnoreCase(msg)  && !"Could not update Preauthorization".equalsIgnoreCase(msg)
			    			&& !"Not updated".equalsIgnoreCase(msg) && !"failure".equalsIgnoreCase(msg))
			    		request.setAttribute("successFlag", "Y");
			    	if(("Y").equalsIgnoreCase(ceoFlag))
			    		lStrFlagStatus="preauthDetailsCeo";
			    	else
			    	lStrFlagStatus="preauthDetailsEhf";
		        }
			    if(lStrFlagStatus!= null && "sentForPreauth".equals(lStrFlagStatus)) 
		        {
			    	preauthVO.setCaseId(preauthDetailsForm.getCaseId());
			    	preauthVO.setCruUsr(lStrEmpId);
			    	String msg = preauthService.sentForPreauth(preauthVO);
			    	request.setAttribute("ResultMsg", msg);
			    	if(msg!=null && !"".equalsIgnoreCase(msg)  && !"failure".equalsIgnoreCase(msg))
			    		request.setAttribute("successFlag", "Y");
			    	lStrFlagStatus="preauthDetailsEhf";
		        }
			    if(lStrFlagStatus!= null && "submitNextLvl".equals(lStrFlagStatus)) 
		        {
			    	String caseStartTime = sds.format(new Date().getTime());
			    	String buttonVal = request.getParameter("buttonVal");
			    	String viewType = request.getParameter("viewType");
			    	preauthVO.setCaseId(preauthDetailsForm.getCaseId());
			    	String ceoFlag=request.getParameter("ceoFlag");
			    	preauthVO.setCruUsr(lStrEmpId);
			    	preauthVO.setViewType(viewType);
			    	preauthVO.setRemarks(preauthDetailsForm.getGenRemarks());
			    	preauthVO.setButtonVal(buttonVal);
			    	preauthVO.setCaseStatusId(preauthDetailsForm.getCaseStatus());
			   // 	preauthVO.setUserRole(lStrUserRole);
			    	preauthVO.setUserRole(lStrUserGroup);
			    	preauthVO.setCochlearYN(preauthDetailsForm.getCochlearYN());
			    	preauthVO.setOrganTransYN(preauthDetailsForm.getOrganTransYN());
			    	preauthVO.setScheme(schemeId);
			    	System.out.println(preauthDetailsForm.getState());
			    	
			    	
			    	//preauthDetailsForm.setGenRemarks(preauthDetailsForm.getGenRemarks());
			    	/**
			    	 * save and enhancement approve amount and pckg approval amount
			    	 */
			    	preauthVO.setEnhApprAmt(preauthDetailsForm.getEnhApprAmt());
			    	
			    	/**
			    	 * save preauth package amount
			    	 */
			    	preauthVO.setApprvdPckAmt(preauthDetailsForm.getApprvdPckAmt());
			    	preauthVO.setComorBidAppvAmt(preauthDetailsForm.getComorBidAppvAmt());
			    	preauthVO.setPtdTotalApprvAmt(preauthDetailsForm.getPtdTotalApprvAmt());
			    	preauthVO.setNabhAmt(preauthDetailsForm.getNabhAmt());
			    	
			    	
				    if(config.getString("preauth_Dent_panelDocStatus").contains("~"+preauthDetailsForm.getCaseStatus()+"~"))
		    			{
			    			List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(preauthDetailsForm.getCaseId());
					    	String dentFlag="N";
					    	if(lstSurgyDtls!=null && lstSurgyDtls.size()>0)
					    		{
					    			for(PreauthVO localVO :lstSurgyDtls)
					    				{
					    					if(localVO.getCatId()!=null && 
					    							localVO.getCatId().equalsIgnoreCase(config.getString("DentalSurgeryID")))
					    						{
					    							dentFlag="Y";
					    							break;
					    						}
					    				}
					    		}
					    	if(dentFlag!=null && dentFlag.equalsIgnoreCase("Y"))
					    		{
					    			for(LabelBean labelBean:groupsList)
							    		{
								    		if(labelBean.getID() != null && (config.getString("preauth_ptd_role").equalsIgnoreCase(labelBean.getID())))
										    	{
								    				lStrUserGroup=config.getString("preauth_ptd_role");
								    				preauthVO.setUserRole(lStrUserGroup);
										    		break;	
										    	}	
								    	}
					    		}	
	    				}
			    	
			    	String msg = preauthService.sentVerifyPanel(preauthVO);
			    	String caseEndTime = sds.format(new Date().getTime());
			    	String actionDone = commonService.getActionDoneName(preauthVO.getCaseId(),"ehfCase");
			    	loggingService.logTime(actionDone, preauthVO.getCaseId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");
			    	request.setAttribute("ResultMsg", msg);
			    	if(msg!=null && !"".equalsIgnoreCase(msg)  && !"failure".equalsIgnoreCase(msg))
			    		{
			    			request.setAttribute("successFlag", "Y");
			    			if(msg!=null)
			    				{
			    					if(!msg.equalsIgnoreCase(config.getString("preauth_msg_CD213")))
				    					{
				    						String autoCaseId = preauthDetailsForm.getCaseId();
					    	        	    if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
					    	        	    	autoCaseId = "0";
					    	        	    ClaimCases.releaseCase(autoCaseId);
				    					}   
			    				}
			    		}
			    	if(("Y").equalsIgnoreCase(ceoFlag))
			    		lStrFlagStatus="preauthDetailsCeo";
			    	else
			    	lStrFlagStatus="preauthDetailsEhf";
		        }
			    /**
			     * new preauth changes 
			     */
			    if(lStrFlagStatus!= null && "cancelPreauth".equals(lStrFlagStatus)) 
		        {
			    	PreauthVO preauthVOCancel = new PreauthVO();
			    	preauthVOCancel.setCaseId(preauthDetailsForm.getCaseId());
			    	preauthVOCancel.setRemarks(preauthDetailsForm.getCancelRemarks());
			    	preauthVOCancel.setCruUsr(lStrEmpId);
			    	String msg = preauthService.cancelPreauth(preauthVOCancel);
			    	request.setAttribute("ResultMsg", msg);
			    	if(msg!=null && !"".equalsIgnoreCase(msg)  && !"failed".equalsIgnoreCase(msg))
			    		request.setAttribute("successFlag", "Y");
			    	lStrFlagStatus="preauthDetailsEhf";	
		        }
			    
				 if(lStrFlagStatus!= null && "holdUnholdCase".equals(lStrFlagStatus)) 
			     {
					 String caseId=request.getParameter("caseId");
					 String holdStatus=request.getParameter("buttonVal");
					 String apprvAmt=request.getParameter("apprvAmt");
					 String resultMsg = "";
					 String message = "";
					 System.out.println(roleId);
					 preauthVO.setCruUsr(lStrEmpId);
					 preauthVO.setCaseId(caseId);
					 preauthVO.setButtonVal(holdStatus);
					 preauthVO.setHoldRemarks(preauthDetailsForm.getHoldRemarks());
					 preauthVO.setUserRole(roleId);
					 preauthVO.setApprvdPckAmt(apprvAmt);
					 resultMsg = preauthService.holdUnholdCase(preauthVO);
					 
					 if(resultMsg != null && resultMsg.trim().length() > 0){
						 if("Y".equalsIgnoreCase(resultMsg)){
							 if(holdStatus != null && holdStatus.trim().length() >0 && (PreauthConstants.HOLD_STATUS).equalsIgnoreCase(holdStatus)){
								 message = "Case has been put on hold";
							 }
							 else{
								 message = "Case has been released from hold";
							 }
						 }
						 else{
							 message = "Unable to process the case. Please try again";
						 }
					 }
					 request.setAttribute("ResultMsg", message);
					 request.setAttribute("exceedResultFlg", "Y");
					 lStrFlagStatus="preauthDetailsEhf";
			     }
			    if(lStrFlagStatus!= null && "preauthDetailsEhf".equals(lStrFlagStatus)) 
		        {
			    	 String casesForApprovalFlag = request.getParameter("caseApprovalFlag");
					 request.setAttribute("casesForApprovalFlag", casesForApprovalFlag);
					 System.out.println(casesForApprovalFlag);
					 boolean verifySentBackCase=false;
				    	List<LabelBean> nimsDepts = preauthService.getNimsDepts();
				    	request.setAttribute("nimsDepts", nimsDepts);
					 
			    	 String caseId = request.getParameter("caseId");
			    	 String patientId = request.getParameter("patientId");
			    	 String printFlag = request.getParameter("printFlag");
			    	 request.setAttribute("patientAge", request.getParameter("patientAge"));
			    	 /**
			    	  * get case id and patient id while printing the form
			    	  */
			    	 if(caseId==null || "".equalsIgnoreCase(caseId))
			    		 caseId= preauthDetailsForm.getCaseId();
			    	 if(patientId==null || "".equalsIgnoreCase(patientId))
			    		 patientId= preauthDetailsForm.getPatientId();
			    	 EhfPatient ehfPatientDtls = preauthService.getPatDtls(patientId);
			    	 if(ehfPatientDtls != null)
			    		 request.setAttribute("ipFlag",ehfPatientDtls.getPatientIpop());
			    	 
			    	 List<LabelBean> catgoryList = patientService.getCatName(caseId);
						if(catgoryList.size() > 0)	
						{
							preauthDetailsForm.setMedicalCat(catgoryList.get(0).getVALUE());
							preauthDetailsForm.setMedicalSpclty(catgoryList.get(0).getICDNAME());
							// Start CR#4511-SriHari-15/12/25
							if(catgoryList.get(0).getID().equalsIgnoreCase("S12MMD")){
								String[] highEndFormsResponse = patientService.getHighEndFormsSubmitFlag(patientId,"patientId", patientId);
								if(highEndFormsResponse != null){
									request.setAttribute("highEndProformaFlag", highEndFormsResponse[0]);
									if(highEndFormsResponse[1] != null && !"null".equalsIgnoreCase(highEndFormsResponse[1]))
										request.setAttribute("highEndProformaId", highEndFormsResponse[1]);
									
									if(highEndFormsResponse[2] != null && !"null".equalsIgnoreCase(highEndFormsResponse[2]))
										request.setAttribute("highEndEvaluationFlag", highEndFormsResponse[2]);
								}
							}
							//End CR#4511-SriHari
								
						}
			    	 ClaimsFlowVO claimFlowVO=new ClaimsFlowVO();
			    	 claimFlowVO.setCaseId(caseId);
			    	 claimFlowVO.setUserId(lStrEmpId);
			    	 verifySentBackCase=preauthService.verifyClaimPending(claimFlowVO);
						if(verifySentBackCase)
							request.setAttribute("sentBack",verifySentBackCase);
			    	 
			    	String nimsHosp=loginService.getNimsMedcoDoc(caseId);
			    	if(!"".equalsIgnoreCase(nimsHosp) && ("EHS34".equalsIgnoreCase(nimsHosp) || "EHS13".equalsIgnoreCase(nimsHosp)))
			    	{
			    		request.setAttribute("nimsMedco", "Y");
			    		claimFlowVO.setNimsFlag("Y");
			    	}
			    	else
			    	{
			    		request.setAttribute("nimsMedco", "N");
			    		claimFlowVO.setNimsFlag("N");
			    	}	
			    	/**
			    	 * Added by ramalakshmi for hubspoke CR
			    	 */
			    	String spokeFlag=null;
			    	String diaFlg = null;
			    	List<LabelBean> caseList = commonService.getcaseList(caseId,schemeId);
					if((caseList!=null && caseList.size()>0))
					{
						diaFlg = "Y";
						for(int i=0;i<caseList.size();i++)
						{
							if(caseList.get(i).getVALUE().equalsIgnoreCase(config.getString("preauth_partail_save")))
								spokeFlag="Y";
							else
								spokeFlag="N";
						}
						
					}
					else
					{
						diaFlg = "N";
						spokeFlag="N";
					}
			    	/**
			    	 * End of hubspoke CR
			    	 */
					//Added for IP Medical Scenarios 	
					 if(!"".equalsIgnoreCase(ehfPatientDtls.getPatientIpop()) && "IPM".equalsIgnoreCase(ehfPatientDtls.getPatientIpop()))
					 {
						 claimFlowVO = preauthService.getDrugDetailsData(claimFlowVO);
						 claimFlowVO = preauthService.getInvestigationDetails(claimFlowVO);
						 claimFlowVO = preauthService.saveEnhanceAmounts(claimFlowVO);
						
					 }
					  if(claimFlowVO.getEnhAmounts() != null)
					 {
						 preauthDetailsForm.setEnhAmounts(claimFlowVO.getEnhAmounts().toString());
						
					 }
					 else
					 {
						 preauthDetailsForm.setEnhAmounts("");
						
					 }
			    	 if(caseId!=null && !"".equalsIgnoreCase(caseId))
			    		{
			    		 	String preauthSchemeId=preauthService.getPatientScheme(caseId);
			    		 	String preauthCaseSchemeId=preauthService.getCaseScheme(caseId);
			    		 	request.setAttribute("preauthSchemeId", preauthSchemeId);
			    		 	request.setAttribute("preauthCaseSchemeId", preauthCaseSchemeId);
			    		}
			    	 
			    	 /**
			    	  * get list of attachments to be added when pending updated
			    	  */
			    	 List<LabelBean> lstAttchments = preauthService.getDocCount(caseId,"Y");
			    	 if(lstAttchments != null && lstAttchments.size() > 0)
			    	 {
			    	 for(LabelBean labelBean:lstAttchments)
			    	 {
			    	 if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("consent_docs")))
			    		request.setAttribute("SignPRFForm",labelBean.getCOUNT());
			    	 }
			    	 }
			    	 else
			    	 {
			    		 request.setAttribute("SignPRFForm",0);
			    	 }
			    	 List<LabelBean> lstAttchments1 = preauthService.getDocCount(caseId,null);
			    	 if(lstAttchments != null && lstAttchments.size() > 0)
			    	 {
			    	 for(LabelBean labelBean:lstAttchments1)
			    	 {	
			    	 
			    	 if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("medical_card_attch")))
				    		request.setAttribute("medCardClearence",labelBean.getCOUNT());
			    	 if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("blood_transfusion_attch")))
				    		request.setAttribute("bloodTransfusionAttach",labelBean.getCOUNT());
			    	 }}
			    	 /**
			    	  * end 
			    	  */
			    	 /**
			    	  * print form details
			    	  */
			    	 if(printFlag != null && printFlag.equalsIgnoreCase("Y"))
				    	{
			    	  PreauthVO preauthVOPrint = preauthService.getPatientOpDtls(caseId, patientId);
				      request.setAttribute("PatientOpList",preauthVOPrint);
				      request.setAttribute("mitActorname", preauthVOPrint.getCruUsr());
				      List<LabelBean> lstInvestigations=preauthService.getInvestigations(patientId);
				      request.setAttribute("investigationsLst",lstInvestigations);
					      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				      Date date = new Date();
				      String lStrCurentDate = sdf.format(date);
				      request.setAttribute("lStrCurentDtTime",lStrCurentDate);
					      request.setAttribute("lStrCurentDt",new SimpleDateFormat("dd/MM/yyyy").format(date));
					      List<LabelBean> symptomsList = new ArrayList<LabelBean>();
					      symptomsList = preauthService.getSymptomsDtls(caseId);
						  request.setAttribute("symptomsList",symptomsList);
						  List<LabelBean> pastHistoryList=null;
						  pastHistoryList=commonService.getPastIllnessHitory();
					      request.setAttribute("pastHistoryList", pastHistoryList);
					      request.setAttribute("mitActDt", lStrCurentDate);
					      List<PreauthVO> casesWorkList= preauthService.getCasesWorkList(caseId);
					      if(casesWorkList!=null && casesWorkList.size()>0)
					      {
					    	  for(int i=0; i<casesWorkList.size();i++)
					    	  {
					    		  if(casesWorkList.get(i).getActId()!=null && !"".equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    		  {
					    			  if(config.getString("mitDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("mitView", "Y");
					    				  request.setAttribute("mitActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("mitActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("mitRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("ptdAprvDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("ptdAprvView", "Y");
					    				  request.setAttribute("ptdAprvActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("ptdAprvActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("ptdAprvRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("ptdRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("ptdRejView", "Y");
					    				  request.setAttribute("ptdRejActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("ptdRejActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("ptdRejRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("ppdDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("ppdView", "Y");
					    				  request.setAttribute("ppdActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("ppdActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("ppdRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("ppdRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("ppdRejView", "Y");
					    				  request.setAttribute("ppdRejActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("ppdRejActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("ppdRejRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("eoAprvDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("eoAprvView", "Y");
					    				  request.setAttribute("eoAprvActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("eoAprvActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("eoAprvRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("eoRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("eoRejView", "Y");
					    				  request.setAttribute("eoRejActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("eoRejActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("eoRejRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("ceoAprvDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("ceoAprvView", "Y");
					    				  request.setAttribute("ceoAprvActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("ceoAprvActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("ceoAprvRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    			  else if(config.getString("ceoRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
					    			  {
					    				  request.setAttribute("ceoRejView", "Y");
					    				  request.setAttribute("ceoRejActorname", casesWorkList.get(i).getAuditName());
					    				  request.setAttribute("ceoRejActDt", casesWorkList.get(i).getAuditDate());
					    				  request.setAttribute("ceoRejRemarks", casesWorkList.get(i).getRemarks());
					    			  }
					    		  }
					    	  }
					    	  if(isMedco_Mithra)
					    	  {
					    		  request.setAttribute("ptdAprvActorname", "Trust Doctor");
					    		  request.setAttribute("ptdRejActorname", "Trust Doctor");
					    		  request.setAttribute("ppdActorname", "Panel Doctor");
					    		  request.setAttribute("ppdRejActorname", "Panel Doctor");
					    		  request.setAttribute("eoAprvActorname", "EO");
					    		  request.setAttribute("eoRejActorname", "EO");
					    		  request.setAttribute("ceoAprvActorname", "CEO");
					    		  request.setAttribute("ceoRejActorname", "CEO");
					    	  }
					      }
					      
				    	}
			    	 List<LabelBean> catList = commonService.getCategorys(null,lStrEmpId);
			    	 request.setAttribute("catList", catList);
			    	 /**
				    	 * Populate Dental Units
				    	 */
			    	 List<LabelBean> dentalUnitsList = new ArrayList<LabelBean>();
					String dentalUnitsStr=config.getString("Dental.Units");
					if(dentalUnitsStr!=null && !"".equalsIgnoreCase(dentalUnitsStr))
					{
						String[] dentalUnitslst=dentalUnitsStr.split("~");
						for(int i=0;i<dentalUnitslst.length;i++)
						{
							LabelBean lb=new LabelBean();
							lb.setID(dentalUnitslst[i]);
							lb.setVALUE(dentalUnitslst[i]);
							dentalUnitsList.add(lb);
						}
					}
					request.setAttribute("dentalUnitsList",dentalUnitsList);
				    	/**
				    	 * check for telephonic intimation
				    	 */
				    	 preauthVO = preauthService.getTelephonicDtls(caseId);
				    	if(preauthVO.getTelephonicFlag() == null || preauthVO.getTelephonicFlag().equals("")|| preauthVO.equals("N"))
				    	{
				    		preauthDetailsForm.setTelephonicFlag("No");	
				    	}
				    	else
				    	{
				    		preauthDetailsForm.setTelephonicFlag("Yes");		
				    		preauthDetailsForm.setTelephonicId(preauthVO.getTelephonicId());
				    		preauthDetailsForm.setTelephonicRemarks(preauthVO.getTelephonicRemarks());
				    	}
				    	preauthDetailsForm.setEnhApprAmt(preauthVO.getEnhApprAmt());
				    	preauthDetailsForm.setCaseNo(preauthVO.getCaseNo());
				    	request.setAttribute("status", preauthVO.getCaseStatusId());
				    	/**
				    	 * get nwh and treating doc Details
				    	 */
				    	SimpleDateFormat sdfer=new SimpleDateFormat("dd-MM-yyyy");
				    	PreauthVO	preauthVO1 = preauthService.getTreatingDocDtls(patientId ,caseId); 
				    	preauthDetailsForm.setTreatmntDt(preauthVO1.getSurgeryDt());
				    	preauthDetailsForm.setDischrgeDt(preauthVO1.getDischrgeDt());
				    	preauthDetailsForm.setHospAddress(preauthVO1.getHospAddress());
				    	preauthDetailsForm.setHospitalName(preauthVO1.getHospitalName());
				    	preauthDetailsForm.setDocName(preauthVO1.getDocName());
				    	preauthDetailsForm.setDocReg(preauthVO1.getDocReg());
				    	preauthDetailsForm.setDocMobNo(preauthVO1.getDocMobNo());
				    	preauthDetailsForm.setDocQual(preauthVO1.getDocQual());
				    	preauthDetailsForm.setDocType(preauthVO1.getDocType());
				    	preauthDetailsForm.setAdmissionRadio(preauthVO1.getAdmissionRadio());
				    	preauthDetailsForm.setHospContactNo(preauthVO1.getHospContactNo());
				    	preauthDetailsForm.setHospCode(preauthVO1.getHospDispCode());
				    	preauthDetailsForm.setAdmissionDate(preauthVO1.getAdmissionDate());
				    	preauthDetailsForm.setAdmissionDate(preauthVO1.getIpRegDate());
				    	preauthDetailsForm.setAdmissionType(preauthVO1.getAdmissionRadio());
				    	preauthDetailsForm.setIpRegDate(preauthVO1.getIpRegDate());
				    	preauthDetailsForm.setComorBidVals(preauthVO1.getComorBidVals());
				    	preauthDetailsForm.setComorBidAmt(preauthVO1.getComorBidAmt());
				    	preauthDetailsForm.setTotPkgAmt(preauthVO1.getTotPackgAmt());
				    	//preauthDetailsForm.setNabhFlg(preauthVO1.getNabhFlg());
				    	preauthDetailsForm.setPreauthPckgAmt(preauthVO1.getPreauthPckgAmt());
				    	preauthDetailsForm.setComorbidValues(preauthVO1.getComorbidValues());
				    	preauthDetailsForm.setPendingFlag(preauthVO1.getPendingFlag());
				    	preauthDetailsForm.setDocSpec(preauthVO1.getDoctorSpeciality());
				    	preauthDetailsForm.setHospStayAmt(preauthVO1.getHospStayAmt());
				    	preauthDetailsForm.setHospitalId(preauthVO1.getHospitalId());
				    	preauthDetailsForm.setPreauthTotalPackageAmt(preauthVO1.getPreauthTotalPackageAmt());
				    	
				    	preauthDetailsForm.setProcedureConsent(preauthVO1.getProcedureConsent());
				    	preauthDetailsForm.setMedCardioClearence(preauthVO1.getMedCardioClearence());
				    	preauthDetailsForm.setBloodTransfusion(preauthVO1.getBloodTransfusion());
				    	preauthDetailsForm.setState(preauthVO1.getScheme());
				    	request.setAttribute("schemeId",preauthVO1.getScheme());
				    	preauthDetailsForm.setCochlearYN(preauthVO1.getCochlearYN());
				    	preauthDetailsForm.setCochlearQuestionnaire(preauthVO1.getCochlearQues());
				    	preauthDetailsForm.setNabhFlg(preauthVO1.getNabhFlg());
				    	preauthDetailsForm.setOrganTransYN(preauthVO1.getOrganTransYN());//For organ transplantation
				    	 if(ehfCase!=null && (!"".equalsIgnoreCase(ehfPatientDtls.getPatientIpop()) && "IPM".equalsIgnoreCase(ehfPatientDtls.getPatientIpop())))
					 		{
				    		 	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					    			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
					 			if(ehfCase.getCaseRegnDate() != null && !ehfCase.getCaseRegnDate().equals(""))
					 			{
					 				preauthDetailsForm.setIpRegDate(sdf.format(ehfCase.getCaseRegnDate()));
					 				request.setAttribute("ipRegDateTime", sdf1.format(ehfCase.getCaseRegnDate()));
					 				
					 			}
					 		}
				    	if(!"".equalsIgnoreCase(preauthVO1.getExceedFlag()) && "Y".equalsIgnoreCase(preauthVO1.getExceedFlag()))				  
				    	{
				    		request.setAttribute("exceedFlag", "Y");
				    	}
				    	else
				    	{
				    		request.setAttribute("exceedFlag", "N");
				    	}
				    	int grp=0;
				    	List<String> grps=new ArrayList<String>();
				    	preauthDetailsForm.setSchemeId(preauthVO1.getScheme());
				    	if(preauthDetailsForm.getSchemeId()!=null)
				    		{
				        		for(LabelBean labelBean:groupsList)
				        			{
				        				if(labelBean.getID() != null && !"".equalsIgnoreCase(labelBean.getID()))
				        					{
				        						grp++;
				        						grps.add(labelBean.getID());
				        					}
				        			}
				    		
				    			if(grp>0 && grps!=null && grps.size()>0)
				    				{
				    					if(grps.contains("GP2"))
				    						request.setAttribute("medcoFlg", "Y");
				    				}
				    	/*		if(preauthVO1.getCaseNabhFlg()!=null)
				    				{
					    				if(preauthDetailsForm.getSchemeId().equalsIgnoreCase("CD201") && preauthVO1.getPreauthTotalPackageAmt()!=null && preauthVO1.getCaseNabhFlg().equalsIgnoreCase("Y"))
					    					preauthDetailsForm.setPreauthNabhAmt((int)Math.round(0.25*Integer.parseInt(preauthVO1.getPreauthTotalPackageAmt())));
					    				else	
					    					preauthDetailsForm.setPreauthNabhAmt(0);
				    				}
				    			else
				    				{
				    					if(preauthVO1.getNabhFlg()!=null)
				    						{
				    							if(preauthVO1.getNabhFlg().equalsIgnoreCase("Y") && preauthDetailsForm.getSchemeId().equalsIgnoreCase("CD201") && preauthVO1.getPreauthTotalPackageAmt()!=null)
				    								preauthDetailsForm.setPreauthNabhAmt((int)Math.round(0.25*Integer.parseInt(preauthVO1.getPreauthTotalPackageAmt())));
				    							else
				    								preauthDetailsForm.setPreauthNabhAmt(0);
				    						}
				    					else
				    						preauthDetailsForm.setPreauthNabhAmt(0);
				    				}	*/
				    		}
				    		
				    	/**
				    	 * get diagnosois and treatment
				    	 */
				    	request.setAttribute("cochlearYN",preauthVO1.getCochlearYN());
				    	request.setAttribute("cochlearQues", preauthVO1.getCochlearQues());
				    	request.setAttribute("organTransYN",preauthVO1.getOrganTransYN());
				    	enhancementFlag = preauthVO1.getEnhancementFlag();
				    	//enhCaseStatus = preauthVO1.getEnhCaseStatus();
				    	preauthDetailsForm.setEnhancementFlag(enhancementFlag);
				    	preauthDetailsForm.setEnhancementFlag(preauthVO1.getEnhancementFlag());
				    	if(preauthVO1.getDiagnosisType()==null || "".equalsIgnoreCase(preauthVO1.getDiagnosisType()))
				    		preauthDetailsForm.setDiagnosisType("-NA-");
				    	else
				    		preauthDetailsForm.setDiagnosisType(preauthVO1.getDiagnosisType());
				    	if(preauthVO1.getMainCatName()==null || "".equalsIgnoreCase(preauthVO1.getMainCatName()))
				    		preauthDetailsForm.setMainCatName("-NA-");
				    	else
				    		preauthDetailsForm.setMainCatName(preauthVO1.getMainCatName());
				    	if(preauthVO1.getCatName()==null || "".equalsIgnoreCase(preauthVO1.getCatName()))
				    		preauthDetailsForm.setCatName("-NA-");
				    	else
				    		preauthDetailsForm.setCatName(preauthVO1.getCatName());
				    	if(preauthVO1.getSubCatName()==null || "".equalsIgnoreCase(preauthVO1.getSubCatName()))
				    		preauthDetailsForm.setSubCatName("-NA-");
				    	else
				    		preauthDetailsForm.setSubCatName(preauthVO1.getSubCatName());
				    	if(preauthVO1.getDisName()==null || "".equalsIgnoreCase(preauthVO1.getDisName()))
				    		preauthDetailsForm.setDisName("-NA-");
				    	else
				    		preauthDetailsForm.setDisName(preauthVO1.getDisName());
				    	if(preauthVO1.getDisAnatomicalSitename()==null || "".equalsIgnoreCase(preauthVO1.getDisAnatomicalSitename()))
				    		preauthDetailsForm.setDisAnatomicalSitename("-NA-");
				    	else
				    		preauthDetailsForm.setDisAnatomicalSitename(preauthVO1.getDisAnatomicalSitename());
				    	preauthDetailsForm.setPatientId(preauthVO1.getPatientID());
				    	preauthDetailsForm.setCaseStatus(preauthVO.getCaseStatusId());
				    	preauthDetailsForm.setEnhApprvDt(preauthVO1.getEnhApvDt());
				    	preauthDetailsForm.setEnhRejDate(preauthVO1.getEnhRejDt());
				    	preauthDetailsForm.setEnhAmt(preauthVO1.getEnhAmt());
				    	preauthDetailsForm.setPatientScheme(preauthVO1.getPatientScheme());
				    	request.setAttribute("enhancementFlag", enhancementFlag);
				    	request.setAttribute("nabhFlag",preauthVO1.getNabhFlg());
				    	
				    	/**
				    	 *   changed
				    	 */
				    	List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(caseId);
				    	if(lstSurgyDtls!=null && lstSurgyDtls.size()>0)
				    	{
				    		session.setAttribute("scatId", lstSurgyDtls.get(0).getCatId());
				    	}
				    	else
				    	{
				    		session.setAttribute("scatId", "0");
				    	}
				    	String dentFlag="N";
				    	if(lstSurgyDtls!=null && lstSurgyDtls.size()>0)
				    		{
				    			for(PreauthVO localVO :lstSurgyDtls)
				    				{
				    					if(localVO.getCatId()!=null && 
				    							localVO.getCatId().equalsIgnoreCase(config.getString("DentalSurgeryID")))
				    						{
				    							dentFlag="Y";
				    							break;
				    						}
				    				}
				    		}

				    	preauthDetailsForm.setLstPreauthVO(lstSurgyDtls);
				    	if(printFlag == null || !printFlag.equalsIgnoreCase("Y"))
				    	{
				    	/**
				    	 * get enhancement list
				    	 */
				    		String patientType = "";
			    		if(ehfPatientDtls.getPatientIpop() != null)
				    			patientType=	ehfPatientDtls.getPatientIpop();
			    		List<PreauthVO> lstenhancementDtls = preauthService.getEnhancementList(caseId,patientType,claimFlowVO.getNimsFlag());
			    		preauthDetailsForm.setEnhamcementList(lstenhancementDtls);
				    	/**
				    	 * get audit trail
				    	 */
				    	PreauthVO auditVo = new PreauthVO();
				    	auditVo.setCaseId(caseId);
				    	
				    	auditVo.setUserRole(lStrUserGroup);
				    	//auditVo.setUserRole(lStrUserRole);
				    	/**
				    	 * audit needs to be changed
				    	 */
				    	List<PreauthVO> lstWorkFlow = preauthService.getAuditTrail(auditVo);
				    	preauthDetailsForm.setLstworkFlow(lstWorkFlow);
				    	
				    	
				    	preauthDetailsForm.setStatus(preauthVO.getCaseStatusId());
				    	//preauthDetailsForm.setGenRemarks("");
				    	
				    	// enhancement audit
				    	auditVo.setEnhancementFlag(config.getString("activeY"));
				    	List<PreauthVO> lstEnhancementWorkFlow = preauthService.getAuditTrail(auditVo);
				    	preauthDetailsForm.setLstEnhancementworkFlow(lstEnhancementWorkFlow);
				    	
				    	/**
				    	 * check which view should be enabled dynamically
				    	 */
				    	String roleStatus = ","+lStrUserGroup+"~"+preauthVO.getCaseStatusId()+",";
				    	if(config.getString("preauth_role_status_mapping").contains(roleStatus))
				    	{
				    		/**
					    	 * get buttons list  based on particular roles and case status
					    	 */
					    	
					    	List<LabelBean> buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module"));
					    	
					    	
				    		if(config.getString("preauth_statusName_"+lStrUserGroup) != null && !config.getString("preauth_statusName_"+lStrUserGroup).equalsIgnoreCase(""))
				    		{
				    		request.setAttribute("viewType",config.getString("preauth_statusName_"+lStrUserGroup) );	
				    		if(lStrUserGroup!=null && lStrUserGroup.equalsIgnoreCase("GP90"))
				    		{
				    			request.setAttribute("viewType","medco" );
				    		}
				    		if(config.getString("preauth_statusName_"+lStrUserGroup) .equalsIgnoreCase(config.getString("MithraName")))
				    		{
				    		PreauthVO namDeclarationDtls =preauthService.getNamDeclarationDtls(preauthDetailsForm.getPatientId()); 
				    		preauthDetailsForm.setPatName(namDeclarationDtls.getPatientName());
				    		preauthDetailsForm.setDistrict(namDeclarationDtls.getDistrict());
				    		preauthDetailsForm.setMandal(namDeclarationDtls.getMandal());
				    		preauthDetailsForm.setVillage(namDeclarationDtls.getVillage());
				    		preauthDetailsForm.setCardNo(namDeclarationDtls.getPatCardNo());
				    		if(namDeclarationDtls.getDate() != null)
				    		preauthDetailsForm.setRegDate(namDeclarationDtls.getDate().toString());
				    		}
				    		 if(config.getString("preauth_statusName_"+lStrUserGroup) .equalsIgnoreCase(config.getString("preauth_statusName_GP2")) )
				    		 {
				    			if((preauthVO1.getPendingFlag()==null ||preauthVO1.getPendingFlag().equals("")) && (spokeFlag!=null && spokeFlag.equalsIgnoreCase("N") ))
				    			{
				    				buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module_save"));	
				    			}
				    			/**
				    			 * Added by ramalakshmi for hubspoke buttons
				    			 */
				    			else if((preauthVO1.getPendingFlag()!=null ) && (spokeFlag!=null && spokeFlag.equalsIgnoreCase("Y") ))
				    			{
				    				buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module_fwd"));	
				    			}
				    			
				    			else
				    				request.setAttribute("viewType", "saveRemarks");	
				    		 }
				    		 if(config.getString("preauth_statusName_"+lStrUserGroup) .equalsIgnoreCase(config.getString("preauth_statusName_GP90")) )
				    		 {
					    		 if((preauthVO1.getPendingFlag()!=null ||"Y".equalsIgnoreCase(preauthVO1.getPendingFlag())) && (spokeFlag!=null && spokeFlag.equalsIgnoreCase("N") ))
					    			{
					    				buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module_save"));	
					    			}
					    		 else if((preauthVO1.getPendingFlag()!=null || "Y".equalsIgnoreCase(preauthVO1.getPendingFlag()) ) && (spokeFlag!=null && spokeFlag.equalsIgnoreCase("N") ))
					    			{
					    				buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module_fwd"));	
					    			}
					    		 else
					    				request.setAttribute("viewType", "saveRemarks");	
				    		 }
				    		 /**
				    			 * End of hubspoke buttons 
				    			 */
				    		/***
				    		 * 1.Added by Srikalyan to remove unnecessary buttons for Preauth 
				    		 * initiation for TG Patients in Govt Hospitals only for non Cochlear Cases
				    		 * 2.If status true initiate button needs to be removed
				    		 * 3.If status false approve button needs to be removed    
				    		 */
				    		if(preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_inpatient_caseReg")) && (spokeFlag!=null && !"Y".equalsIgnoreCase(spokeFlag)))
					    		 {
					    			boolean status=preauthService.checkTGPatReg(preauthVO1.getHospitalId(),caseId);
					    			if(preauthVO1.getCochlearYN()!=null && preauthVO1.getCochlearYN().equalsIgnoreCase("Y"))
					    				status=false;
					    			if(preauthVO1.getOrganTransYN()!=null && preauthVO1.getOrganTransYN().equalsIgnoreCase("Y"))
					    				status=false;
						    		if(status==true)
						    			{
						    				/**
						    				 * newButtonLst added because the Button List returned is an unmodifiable List 
						    				 */
						    				List<LabelBean> newButtonLst =new ArrayList<LabelBean>();
						    				if(buttonsList!=null && buttonsList.size()>0)
						    					{
						    						for(LabelBean lbLst : buttonsList)
						    							{
						    								if(lbLst!=null && lbLst.getID()!=null &&
						    										!lbLst.getID().equalsIgnoreCase(config.getString("Preauth.Medco.Initiate.Button")))
						    									{
						    										newButtonLst.add(lbLst);
						    									}
						    							}
						    					}
						    				buttonsList=Collections.unmodifiableList(newButtonLst);
						    			}
						    		else
						    			{
							    			/**
						    				 * newButtonLst added because the Button List returned is an unmodifiable List 
						    				 */
						    				List<LabelBean> newButtonLst =new ArrayList<LabelBean>();
						    				if(buttonsList!=null && buttonsList.size()>0)
						    					{
						    						for(LabelBean lbLst : buttonsList)
						    							{
						    								if(lbLst!=null && lbLst.getID()!=null &&
						    										!lbLst.getID().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button")))
						    									{
						    										newButtonLst.add(lbLst);
						    									}
						    							}
						    					}
						    				buttonsList=Collections.unmodifiableList(newButtonLst);
						    			}
					    		 }
				    		
				    		String checkDentalCond=preauthService.checkDentalCase(caseId);
							if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
								claimFlowVO.setDentalFlag(checkDentalCond);
				    		/***
				    		 * 1.Added by Kalyan to remove unnecessary buttons for PTD Pending Cases 
				    		 * 2.For PTD ,If count is less than 2 pending button needs to be added for Only Dental Cases
				    		 */
							if(lStrUserGroup!=null
								&& (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ptd_role")))
								&& checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
									{
										int count=preauthService.getCount(caseId,lStrUserGroup);
										if(count==1)
											{
							    				/**
							    				 * newButtonLst added because the Button List returned is an unmodifiable List 
							    				 */
							    				List<LabelBean> newButtonLst =new ArrayList<LabelBean>();
							    				if(buttonsList!=null && buttonsList.size()>0)
							    					{
							    						for(LabelBean lbLst : buttonsList)
							    							{
					    										if(lbLst!=null && lbLst.getID()!=null &&
							    										!lbLst.getID().equalsIgnoreCase(config.getString("EHF.PendButton")))
							    									{
							    										newButtonLst.add(lbLst);
							    									}
							    							}
							    						
							    						LabelBean localBean =new LabelBean();
							    						localBean.setID(config.getString("EHF.PendButton"));
							    						localBean.setVALUE("Pending");
							    						newButtonLst.add(localBean);
							    					}
							    				
							    				buttonsList=Collections.unmodifiableList(newButtonLst);
											}
									}
				    		preauthDetailsForm.setButtonsLst(buttonsList);
				    		
				    	}
				    		else
				    			request.setAttribute("viewType", "disable");		
				    	}
				    	else if(config.getString("ppd_dent_mapping").contains(roleStatus) && dentFlag!=null &&
				    			dentFlag.equalsIgnoreCase("Y") && caseApprovalFlag != null && caseApprovalFlag.equalsIgnoreCase("Y"))
				    		{
					    		/**
						    	 * get buttons list  based on particular roles and case status
						    	 */
						    	List<LabelBean> buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module"));
						    	preauthDetailsForm.setButtonsLst(buttonsList);
						    	request.setAttribute("viewType",config.getString("preauth_statusName_"+lStrUserGroup) );
				    		}	
				    	else
			    			request.setAttribute("viewType", "disable");
				    	
				    	//To show Auditor Names to Trust Users
				    	for(LabelBean labelBean:groupsList)
				    	{
					    	if(labelBean.getID() != null && config.getString("view_audit_names")!=null && config.getString("view_audit_names").contains("~"+labelBean.getID()+"~") )
					    	{
					    		request.setAttribute("viewAuditNames", "Y");
						    	break;	
					    	}
				    	}
				    	
				    	/**
				    	 * get enhancement drugs
				    	 */
				    	List<DrugsVO> drugs=preauthService.getIpDrugs(preauthDetailsForm.getPatientId(),"Preauth");
				    	preauthDetailsForm.setDrugLt(drugs);
				    	List<LabelBean> drugsList=commonService.getDrugs();
						request.setAttribute("drugsList", drugsList);
				    	/**
				    	 * for enhancement get view type
				    	 */
				    	if(preauthVO1.getMedicalSurgicalFlag() != null && preauthVO1.getMedicalSurgicalFlag().equalsIgnoreCase("Y") 
				    			&& (
				    					(  preauthVO1.getCeoApprvFlag() != null && preauthVO1.getCeoApprvFlag().equalsIgnoreCase("A") &&  preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ceo_approved"))
				    					)
				    					|| 
				    					(
				    					  (  preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ptd_approved"))
				    						 ||preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate"))
				    						 ||preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("Preauth.Medco.Immediate.Approve"))
				    					  )  
				    					  && 
				    					  (  preauthVO1.getCeoApprvFlag() == null || preauthVO1.getCeoApprvFlag().equalsIgnoreCase("") || !preauthVO1.getCeoApprvFlag().equalsIgnoreCase("Y")
				    					  )
				    					) || (preauthVO1.getEnhancementFlag() != null && preauthVO1.getEnhancementFlag().equals("Y"))
				    			   ))
				    	{
				    		
				    	if(config.getString("preauth_role_status_mapping_enh").contains(roleStatus))
				    	{
				    		if(config.getString("preauth_statusName__enh_"+lStrUserGroup) != null && !config.getString("preauth_statusName__enh_"+lStrUserGroup).equalsIgnoreCase(""))
				    		{
				    			if(config.getString("preauth_statusName__enh_"+lStrUserGroup).equalsIgnoreCase("enhMedco"))
				    			{
				    				/**
							    	 * get Enhancement details
							    	 */
							    	List<LabelBean> hospStay = preauthService.getHospStayist(config.getString("enhType_HospitalStay"));
							    	request.setAttribute("hospStay", hospStay);
							    	List<LabelBean> imageologyStay = preauthService.getHospStayist(config.getString("enhType_Imageology"));
							    	request.setAttribute("imageologyStay", imageologyStay);
							    	List<LabelBean> labInvestigations = preauthService.getHospStayist(config.getString("enhType_LabInvestigations"));
							    	request.setAttribute("labInvestigations", labInvestigations);
							    	List<LabelBean> drugTypes = preauthService.getHospStayist(config.getString("enhType_drugs"));
							    	request.setAttribute("drugTypes", drugTypes);
							    	List<LabelBean> implantTypes = preauthService.getHospStayist(config.getString("enhType_implants"));
							    	request.setAttribute("implantTypes", implantTypes);	
				    			}
				    			/**
						    	 * get enhancement workflow
						    	 */
						    	auditVo.setEnhancementFlag("Y");
						    	
						    	/**
						    	 * end of workflow
						    	 */
						    	
						    	/**
						    	 * get enhancement buttons list
						    	 */
						    	if(preauthVO1.getEnhRejDt() == null || preauthVO1.getEnhRejDt().equals(""))
						    	{
 						    	List<LabelBean> enhButtonsList  = new ArrayList<LabelBean>();
						    	 enhButtonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_enh_sub_module"));
						    	 if(!"N".equalsIgnoreCase(casesForApprovalFlag))
						    	 preauthDetailsForm.setEnhButtonsLst(enhButtonsList);
						    	}
						    	 preauthDetailsForm.setGenRemarks("");
						    	/**
						    	 * end of enhancement details
						    	 */
				    				request.setAttribute("viewType",config.getString("preauth_statusName__enh_"+lStrUserGroup) );	
				    		}
				    		else
				    			request.setAttribute("viewType", "disable");		
				    	}
				    	else
			    			request.setAttribute("viewType", "disable");
				    	}
				    	
				    	
				    	/**
				    	 * end
				    	 */
				    	
				    /*	*//**
				    	 * check for which view should be enabled
				    	 *//*
				    	
				    	
				    	if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_inpatient_caseReg")))
				    		||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ptd_pending")))
				    	||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ppd_pending")))	 
				    	||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ceo_pending")))
				    	)
				    			
				    	{
				    		request.setAttribute("viewType", "medco");
				    	}
				    	else if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(   config.getString("preauth_nam_role") ) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_medco_initiated"))
				    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_nam_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_medco_forwarded"))))
				    	{
				    		request.setAttribute("viewType", "nam");
				    		*//**
				    		 * get NAm declaration dtls
				    		 *//*
				    		
				    	
				    	}
				    	else if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ptd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ppd_rejected"))
				    			|| (lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ptd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ppd_approved")))
				    			|| (lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ptd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_medco_preauth_reinitiated")))
				    			))
				    	{
				    		request.setAttribute("viewType", "ptd");
				    	}
				    	else if( lStrUserGroup != null && (preauthVO1.getMedicalSurgicalFlag().equalsIgnoreCase("Y")  && ( preauthVO1.getCeoApprvFlag() == null || preauthVO1.getCeoApprvFlag().equalsIgnoreCase("") || !preauthVO1.getCeoApprvFlag().equalsIgnoreCase("Y")))&& ((lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ptd_approved"))) 
				    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")))
				    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_pending"))) 
				    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_recPending")))) && (preauthVO1.getEnhRejDt() == null || preauthVO1.getEnhRejDt().equals("")) 
				    	||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && (preauthVO1.getCeoApprvFlag() != null && preauthVO1.getCeoApprvFlag().equalsIgnoreCase("A") &&  preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ceo_approved")))) )
				    	{
				    		*//**
				    		 * check view type for enhancment medoc
				    		 *//*
				    		request.setAttribute("viewType", "enhMedco");	
				    		
				    	}
				    	else if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ctd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_intiated")))
				    			|| (lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ctd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_reinitiated_ctd"))	))
				    	{
				    		request.setAttribute("viewType", "ctd");		
				    	}
				    	else if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ch_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_recApprove")))
		                  || ( lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ch_role"))) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_recReject"))
				    	  ||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ch_role"))) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_recPending"))
				    	  ||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ch_role"))) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_enhancement_reinitiated"))	)
				    	{
				    		request.setAttribute("viewType", "ch");		
				    	}
				    	else if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(   config.getString("userGroupCeo") ) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ptd_approved")))
				    			|| (lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(   config.getString("userGroupCeo") ) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_medco_ceoPendingUpdate"))))
				    	{
				    		request.setAttribute("viewType", "ceo");			
				    	}
				    	else if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(   config.getString("preauth_pex_role") ) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_nam_forwarded")))
				    	{
				    		request.setAttribute("viewType", "pex");			
				    	}
				    	else if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ppd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_pex_forwarded"))) 
				    			|| (lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_ppd_role")) && preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ppd_pending_updated"))))
						    	{
						    		request.setAttribute("viewType", "ppd");		
						    	}
				    	else
				    	{ 
				    		request.setAttribute("viewType", "disable");	
				    	}*/
				    	
				    	preauthDetailsForm.setCaseId(caseId);
				    	
			    	// return mapping.findForward("preauthDtlsEhf");
				    	 // set total package amount for preauth and ehf
				    	 request.setAttribute("preauthAmt", Integer.parseInt(config.getString("preauth_package_amt_limit")));
				    	 request.setAttribute("preauthEnhAmt", Integer.parseInt(config.getString("preauth_enh_amt_limit")));
			    	
				    	 // get comorbid list
				    	 
				    	 List<CommonDtlsVO> lstComorbids = preauthService.getComorBidVals();
				    	 request.setAttribute("lstComorbid", lstComorbids);
				    	
				    	 /**
				    	  *  cancel button provision
				    	  */
				    	 boolean cancelButt=false;
				    	 if(isMedco && preauthVO1.getPreauthInitiatedDt() != null && (preauthVO1.getPreauthRejDt() ==null || preauthVO1.getPreauthRejDt().equals("")) 
				    			 &&(preauthVO1.getSurgeryDt() == null || preauthVO1.getSurgeryDt().equals("")) 
				    			 && (preauthVO1.getPreauthCancelDt() ==null || preauthVO1.getPreauthCancelDt().equals("")))
				    	 {
				    		 if(!"N".equalsIgnoreCase(casesForApprovalFlag))
				    		 	{
				    			 cancelButt=true; 
				    			 request.setAttribute("cancelBut", cancelButt);
				    		 	}
				    	 }
				    	 
				    	 request.setAttribute("caseStatus",preauthVO.getCaseStatusId());
				    	 request.setAttribute("caseId",caseId);
				    	 request.setAttribute("diaFlg",diaFlg);
				    	boolean nimsRedirect=false; 
				    	nimsRedirect=preauthService.checkNimsCase(caseId);
				    	if(nimsRedirect)
				    	{
					    	 drugs=preauthService.getMedicalDrugs(preauthDetailsForm.getCaseId(),"Preauth");
					    	if(drugs.size()>0)
					    	{	
					    		if(drugs != null)
					    			preauthDetailsForm.setDrugLt(drugs);
					    	}

				    		return mapping.findForward("preauthDtlsEhfNIMS");
				    	}
				    	else
				    	 return mapping.findForward("preauthDtlsEhf1");
				    	}
				    	else
				    	 return mapping.findForward("getPRFForm");	
			    	 
			    }
			    
			   
			    if(lStrFlagStatus!= null && "checkMandatoryAttch".equals(lStrFlagStatus)) 
		        {
			    	 String caseId = request.getParameter("caseId");
			    	 String ipFlag = request.getParameter("ipFlag");
			    	 String attachType = request.getParameter("attachType");
			    	 String msg = preauthService.checkMandatoryAttch(caseId,attachType,ipFlag);
			    	 request.setAttribute("AjaxMessage",msg);
			    	 return mapping.findForward("ajaxResult");	
		        }
			    if(lStrFlagStatus!= null && "checkClinicalNotes".equals(lStrFlagStatus)) 
		        {
			    	 String caseId = request.getParameter("caseId");
			    	 String msg = preauthService.checkClinicalNotes(caseId);
			    	 int dop=0,nonDop=0;
					 List<CasesSearchVO> list=preauthService.getDopDtls(caseId);
					 if(list!=null)
						{
							if(list.size()>0)
								{
									for(CasesSearchVO casesSearchVODOP:list)
										{
											if(casesSearchVODOP.getProcess()!=null)
												{
													if(casesSearchVODOP.getProcess().equalsIgnoreCase("DOP"))
														{
															dop=dop+1;
														}
													else
														{
															if(casesSearchVODOP.getActiveyn()!=null)
															{
																if(casesSearchVODOP.getActiveyn().equalsIgnoreCase("Y"))
																	nonDop=nonDop+1;
															}
														}
												}
										}
								}
						}
						
						if(nonDop==0 && dop>0)
							msg = "success";
						
			    	 request.setAttribute("AjaxMessage",msg);
			    	 return mapping.findForward("ajaxResult");	
		        }
			    
			    if(lStrFlagStatus!= null && "submitQuestions".equals(lStrFlagStatus)) 
		        {
			    	preauthVO.setPatientID(preauthDetailsForm.getPatientId());
			    	preauthVO.setEnrollCardNo(preauthDetailsForm.getEnrollCardNo());
			    	preauthVO.setPatCardNo(preauthDetailsForm.getPatCardNo());
			    	preauthVO.setEnrollName(preauthDetailsForm.getEnrollName());
			    	preauthVO.setPatName(preauthDetailsForm.getPatName());
			    	preauthVO.setEnrollAge(preauthDetailsForm.getEnrollAge());
			    	preauthVO.setPatage(preauthDetailsForm.getPatAge());
			    	preauthVO.setEnrollSex(preauthDetailsForm.getEnrollSex());
			    	preauthVO.setPatSex(preauthDetailsForm.getPatSex());
			    	preauthVO.setEnrollPotoAttach(preauthDetailsForm.getEnrollPotoAttach());
			    	preauthVO.setPatPotoAttch(preauthDetailsForm.getPatPotoAttch());
			    	preauthVO.setNumRadio(preauthDetailsForm.getNumRadio());
			    	preauthVO.setNameRadio(preauthDetailsForm.getNameRadio());
			    	preauthVO.setAgeRadio(preauthDetailsForm.getAgeRadio());
			    	preauthVO.setGenderRadio(preauthDetailsForm.getGenderRadio());
			    	preauthVO.setPrePat1Radio(preauthDetailsForm.getPrePat1Radio());
			    	preauthVO.setPreDoc1Radio(preauthDetailsForm.getPreDoc1Radio());
			    	preauthVO.setPreRamco1Radio(preauthDetailsForm.getPreRamco1Radio());
			    	preauthVO.setPrePat2Radio(preauthDetailsForm.getPrePat2Radio());
			    	preauthVO.setPreDoc2Radio(preauthDetailsForm.getPreDoc2Radio());
			    	preauthVO.setPreRamco2Radio(preauthDetailsForm.getPreRamco2Radio());
			    	preauthVO.setConPat1Radio(preauthDetailsForm.getConPat1Radio());
			    	preauthVO.setConDoc1Radio(preauthDetailsForm.getConDoc1Radio());
			    	preauthVO.setConRamco1Radio(preauthDetailsForm.getConRamco1Radio());
			    	preauthVO.setConPat2Radio(preauthDetailsForm.getConPat2Radio());
			    	preauthVO.setConDoc2Radio(preauthDetailsForm.getConDoc2Radio());
			    	preauthVO.setConRamco2Radio(preauthDetailsForm.getConRamco2Radio());
			    	preauthVO.setPhotoradio(preauthDetailsForm.getPhotoradio());
			    	preauthVO.setCruUsr(lStrEmpId);
			    	//String msg = preauthService.saveQuestionnaire(preauthVO);
			    	request.setAttribute("quesMsg", "saved successfully");
			    	lStrFlagStatus ="pexQuestions";	
		        }
			    if(lStrFlagStatus!= null && "pexQuestions".equals(lStrFlagStatus)) 
		        {
			    	 String caseId = request.getParameter("caseId");
			    	 preauthVO = preauthService.getQuestionerData(caseId);
			    	 if(preauthVO != null)
			    	 {
			    		request.setAttribute("preauthVO", preauthVO); 
			    		FileInputStream fis=null;
						DataInputStream dis=null;
						 byte[] bytes = null;
						 File file=null;
						 if(preauthVO.getEnrollPotoAttach()!=null)
						 {
						   file = new File(preauthVO.getEnrollPotoAttach());

						   if(file.exists())
						   {
							   fis = new FileInputStream(file);
							 dis= new DataInputStream(fis);
							 bytes = new byte[dis.available()];
							
				             fis.read(bytes);
				             String lStrContentType=null;
				             lStrContentType="image/png";
				             session.setAttribute("ContentType", lStrContentType);
				             session.setAttribute("File", bytes);
				              fis.close();
				              dis.close();}}
			    		
			    	    if(preauthVO.getQuesMsg() != null && preauthVO.getQuesMsg().equalsIgnoreCase("Y"))
			    	    {
			    	    	preauthDetailsForm.setEnrollCardNo(preauthVO.getEnrollCardNo());
			    	    	preauthDetailsForm.setPatCardNo(preauthVO.getPatCardNo());
			    	    	preauthDetailsForm.setEnrollName(preauthVO.getEnrollName());
			    	    	preauthDetailsForm.setPatName(preauthVO.getPatName());
			    	    	preauthDetailsForm.setEnrollAge(preauthVO.getEnrollAge());
			    	    	preauthDetailsForm.setPatAge(preauthVO.getPatAge());
			    	    	preauthDetailsForm.setEnrollSex(preauthVO.getEnrollSex());
			    	    	preauthDetailsForm.setPatSex(preauthVO.getPatSex());
			    	    	preauthDetailsForm.setEnrollPotoAttach(preauthVO.getEnrollPotoAttach());
			    	    	preauthDetailsForm.setPatPotoAttch(preauthVO.getPatPotoAttch());
			    	    	preauthDetailsForm.setNumRadio(preauthVO.getNumRadio());
			    	    	preauthDetailsForm.setNameRadio(preauthVO.getNameRadio());
			    	    	preauthDetailsForm.setAgeRadio(preauthVO.getAgeRadio());
			    	    	preauthDetailsForm.setGenderRadio(preauthVO.getGenderRadio());
			    	    	preauthDetailsForm.setPrePat1Radio(preauthVO.getPrePat1Radio());
			    	    	preauthDetailsForm.setPreDoc1Radio(preauthVO.getPreDoc1Radio());
			    	    	preauthDetailsForm.setPreRamco1Radio(preauthVO.getPreRamco1Radio());
			    	    	preauthDetailsForm.setPrePat2Radio(preauthVO.getPrePat2Radio());
			    	    	preauthDetailsForm.setPreDoc2Radio(preauthVO.getPreDoc2Radio());
			    	    	preauthDetailsForm.setPreRamco2Radio(preauthVO.getPreRamco2Radio());
			    	    	preauthDetailsForm.setConPat1Radio(preauthVO.getConPat1Radio());
			    	    	preauthDetailsForm.setConDoc1Radio(preauthVO.getConDoc1Radio());
			    	    	preauthDetailsForm.setConRamco1Radio(preauthVO.getConRamco1Radio());
			    	    	preauthDetailsForm.setConPat2Radio(preauthVO.getConPat2Radio());
			    	    	preauthDetailsForm.setConDoc2Radio(preauthVO.getConDoc2Radio());
			    	    	preauthDetailsForm.setConRamco2Radio(preauthVO.getConRamco2Radio());
			    	    	preauthDetailsForm.setPhotoradio(preauthVO.getPhotoradio());
			    	    	preauthDetailsForm.setNumRadio(preauthVO.getNumRadio()); 
			    	    	request.setAttribute("disableQues", "Y");	
			    	    }
			    		preauthDetailsForm.setPatientId(preauthVO.getPatientID());
			    		preauthVO.setCaseId(caseId);
			    	 }
			    	 return mapping.findForward("pexQuestions");	
		        }
        if(lStrFlagStatus!= null && "getIcdCatList".equals(lStrFlagStatus)) 
		        {
        	String catId = request.getParameter("catId");
        	String caseID=request.getParameter("caseId");
        	List<String> catList = new ArrayList<String>();
        	String userID=(String) session.getAttribute("UserID");
        	String hosType= commonService.ckDentalClinic(userID, caseID);
        	String hType="";
        	if(!hosType.equals("hospital"))
        	{
        		hType="DC";
        	}
        	else
        	{
        		hType="HOSPITAL";
        	}
        	List<LabelBean> lstIcdCat=null; 
        	if(catId.equals("S18"))
        	{
        	lstIcdCat = commonService.getICDCategoryList(catId,lStrEmpId,hType);
        	}
        	else
        	{
        	lstIcdCat = commonService.getCategorys(catId,lStrEmpId);
        	}
			if (lstIcdCat != null && lstIcdCat.size() > 0) {
				for (LabelBean labelBean : lstIcdCat) {
					if (labelBean.getICDCODE() != null && labelBean.getICDNAME() != null)
					{
						if (catList != null && catList.size() > 0) {
							catList.add("@" + labelBean.getICDCODE() + "~"
									+ labelBean.getICDNAME());
						} else
							catList.add(labelBean.getICDCODE() + "~"
									+ labelBean.getICDNAME());
				
						//catList.add(labelBean.getICDCODE() + "~"+ labelBean.getICDNAME());
						}
				}
			}
			if (catList != null && catList.size() > 0)
				request.setAttribute("AjaxMessage", catList);
			
			// get treating doctor details
			
			String hospCode=request.getParameter("hospId");
			List<String> doctorList=null;
			doctorList=commonService.getDocListBySpec(catId,hospCode,schemeId);
			System.out.println("doctor list size is :"+doctorList.size());
			if (doctorList != null && doctorList.size() > 0)
				request.setAttribute("AjaxMessage1", doctorList);
			
			
			return mapping.findForward("ajaxResult");	
		        }
        if(lStrFlagStatus!= null && "getProcList".equals(lStrFlagStatus)) 
        {
        	String schemeType = config.getString("Scheme.TG.State");
        	CommonDtlsVO commonDtlsVO=null;
        	String caseId=request.getParameter("caseId");
        	String userID=(String) session.getAttribute("UserID");
        	String hosType= commonService.ckDentalClinic(userID, caseId);
        	
	        List<CommonDtlsVO> commonDtls=preauthService.getPatientCommonDtls(caseId);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			String scheme=null;
	
			if(schemeType!=null||"".equalsIgnoreCase(schemeType))
				{
				if(schemeType.equalsIgnoreCase(config.getString("COMMON")))
					scheme=config.getString("AP");
					else
					scheme=schemeType;
				}
			
			else if(commonDtlsVO.getScheme()!=null)
				scheme=commonDtlsVO.getScheme();
		
	  String asriCode = request.getParameter("asriCode");
	  String icdCatCode = request.getParameter("IcdcatId");
	  String hospId = request.getParameter("hospId");
	  String dentEnhFlag="";
	  List<String> procList = new ArrayList<String>();
	  String hType="";
	  if(!hosType.equals("hospital"))
	  	{
	  		hType="DC";
	  	}
	  	else
	  	{
	  		hType="HOSPITAL";
	  	}
	  List<LabelBean> lstIcdCat=null;
	  if(hType.equals("DC"))
	  {
		  lstIcdCat = commonService.getICDProcedures(icdCatCode, asriCode,hospId,scheme,dentEnhFlag);
	  }
	  else
	  {
		  lstIcdCat = commonService.getProcedures(icdCatCode, asriCode,hospId,scheme,dentEnhFlag);
	  }
	  PreauthVO preVO=null;
	  String dentalFlpCond="N";
	  
	  //For Adding and removing Dental Follow Up Procedure
	  if(asriCode!=null && asriCode.equalsIgnoreCase(config.getString("DentalSurgeryID")))
		  {
		  	preVO=preauthService.checkDenFlp(caseId);
		  	if(preVO!=null)
			  	{
				  	if(preVO.getActId()!=null && preVO.getActId().equalsIgnoreCase("Y")
				  			&& preVO.getProcCode()!=null && !preVO.getProcCode().equalsIgnoreCase(""))
				  		dentalFlpCond = preVO.getProcCode();
			  	}
		  }
	  
	  //If by any Chance dentalFlpCond is made Null Then it is again made N  
	  if(dentalFlpCond==null)
		  dentalFlpCond="N";
	  
	  if (lstIcdCat != null && lstIcdCat.size() > 0) 
	  	{
			  for (LabelBean labelBean : lstIcdCat) 
			  	{
					  if(icdCatCode != null && !icdCatCode.equalsIgnoreCase(""))
						  {
							  if (labelBean.getICDCODE() != null && labelBean.getICDNAME() != null && dentalFlpCond!=null)
									{
								  		//dentalFlpCond contains Proc That Should be Removed from List.Hence Every Proc should be matched with dentalFlpCond
								  		if(!labelBean.getICDCODE().equalsIgnoreCase(dentalFlpCond))
								  			{
											  	  if (procList != null && procList.size() > 0) 
													  	{
															  procList.add("@" + labelBean.getICDCODE() + "~"
																		+ labelBean.getICDNAME());
														}
												  else
													  	{
															  procList.add(labelBean.getICDCODE() + "~"
																	  	+ labelBean.getICDNAME());
													  	}	
												//	procList.add(labelBean.getICDCODE() + "~"+ labelBean.getICDNAME());	
								  			}
									}	  
						  }
					  else
						  {
							//dentalFlpCond contains Proc That Should be Removed from List.Hence Every Proc should be matched with dentalFlpCond
						  		if(!labelBean.getICDCODE().equalsIgnoreCase(dentalFlpCond))
						  			{
										if (labelBean.getID() != null && labelBean.getVALUE() != null)
											{
												procList.add(labelBean.getID() + "~"+ labelBean.getVALUE());
											}
									}	
						  }
				  }
	     }
			if (procList != null && procList.size() > 0)
				request.setAttribute("AjaxMessage", procList);
			return mapping.findForward("ajaxResult");	
			    }
        if(lStrFlagStatus!= null && "getHospAmt".equals(lStrFlagStatus)) 
        {
        	String quantId = request.getParameter("quantId");
        	String noOfUnits = request.getParameter("noOfUnits");
        	String nabhFlag = request.getParameter("nabhFlag");
        	String caseId = request.getParameter("caseId");
        	Integer amount=0;
        	if(quantId!=null && !"".equalsIgnoreCase(quantId) && (config.getString("enhQuantId").contains("~"+quantId+"~")))
        		amount= preauthService.getQuantAmountForNabh(quantId,nabhFlag,caseId);
        	else
        		amount =  preauthService.getQuantAmount(quantId);
        	Integer totAmt = 0;
        	if(noOfUnits != null && !noOfUnits.equalsIgnoreCase(""))
        	{
        		totAmt = Integer.parseInt(noOfUnits) * amount;
        	}
        	request.setAttribute("AjaxMessage", totAmt);
			return mapping.findForward("ajaxResult");	
        }
        /**
         * get Enhancement quantity list
         */
        if(lStrFlagStatus!= null && "getEnhQuantList".equals(lStrFlagStatus)) 
        {
	String enhTypeId = request.getParameter("enhTypeId");
	List<String> enhQuantList = new ArrayList<String>();
	List<LabelBean> lstenhQuant = preauthService.getHospStayQuantList(enhTypeId);
	if (lstenhQuant != null && lstenhQuant.size() > 0) {
		for (LabelBean labelBean : lstenhQuant) {
			if (labelBean.getID() != null && labelBean.getVALUE() != null)
			{
				if(enhQuantList!=null && enhQuantList.size()>0)
					enhQuantList.add("@" +labelBean.getID() + "~"+ labelBean.getVALUE());
				else
					enhQuantList.add(labelBean.getID() + "~"+ labelBean.getVALUE());
			}
		}
	}
	if (enhQuantList != null && enhQuantList.size() > 0)
		request.setAttribute("AjaxMessage", enhQuantList);
	return mapping.findForward("ajaxResult");	
        }
        /*
         * NIMS Investigations 
         */
        if(lStrFlagStatus!= null && "getHospAmtNIMS".equals(lStrFlagStatus)) 
        {
        	String quantId = request.getParameter("quantId");
        	String noOfUnits = request.getParameter("noOfUnits");
        	String nabhFlag = request.getParameter("nabhFlag");
        	String caseId = request.getParameter("caseId");
        	Integer amount=0;
        	/*if(quantId!=null && !"".equalsIgnoreCase(quantId) && (config.getString("enhQuantId").contains("~"+quantId+"~")))
        		amount= preauthService.getQuantAmountForNabh(quantId,nabhFlag,caseId);
        	else*/
        		amount =  preauthService.getQuantAmountNIMS(quantId);
        	Integer totAmt = 0;
        	if(noOfUnits != null && !noOfUnits.equalsIgnoreCase(""))
        	{
        		totAmt = Integer.parseInt(noOfUnits) * amount;
        	}
        	request.setAttribute("AjaxMessage", totAmt);
			return mapping.findForward("ajaxResult");	
        }
        /**
         * get Enhancement quantity list
         */
        if(lStrFlagStatus!= null && "getEnhQuantListNIMS".equals(lStrFlagStatus)) 
        {
				String enhTypeId = request.getParameter("enhTypeId");
				List<String> enhQuantList = new ArrayList<String>();
				List<LabelBean> lstenhQuant = preauthService.getHospStayQuantListNIMS(enhTypeId);
				if (lstenhQuant != null && lstenhQuant.size() > 0) 
				{
					for (LabelBean labelBean : lstenhQuant) 
					{
						if (labelBean.getID() != null && labelBean.getVALUE() != null)
						{
							if(enhQuantList!=null && enhQuantList.size()>0)
								enhQuantList.add("@" +labelBean.getID() + "~"+ labelBean.getVALUE());
							else
								enhQuantList.add(labelBean.getID() + "~"+ labelBean.getVALUE());
						}
					 }
				}
				if (enhQuantList != null && enhQuantList.size() > 0)
					request.setAttribute("AjaxMessage", enhQuantList);
				return mapping.findForward("ajaxResult");	
        }
        if(lStrFlagStatus!= null && "calcTotalPackageAmt".equals(lStrFlagStatus)) 
        {
        	Long hospStayAmt =0L;
        	Long nabhMaxAmt =0L;
        	Long cmnCatAmt =0L;
        	Long icdAmt =0L;
        	Long TotAmt = 0L;
        	Long hospAmtF =0L;
        	String surgeryList = request.getParameter("amtList");
        	String nabhAmt = request.getParameter("hospStayAmt");
        	StringTokenizer str = new StringTokenizer(surgeryList,"*");
        	Map<String,String> surgerys = new HashMap<String,String>();
        	while(str.hasMoreTokens())
        	{
        	StringTokenizer str1 = new StringTokenizer(str.nextToken(),",");	
        	while(str1.hasMoreTokens())
        	{
        		
        		/**
        		 * add surgerys to map
        		 */
        		String surgeryId = str1.nextToken();
        		String commonCatAmt=str1.nextToken();
        		if(commonCatAmt != null && Float.valueOf(commonCatAmt) >0)
        		surgerys.put(surgeryId,commonCatAmt);
        		String icdamt = str1.nextToken();
        		icdAmt = icdAmt+Long.valueOf(icdamt);
        		String hospAmt = str1.nextToken();
        		String noOfDays = str1.nextToken();
        		if(nabhAmt != null && !nabhAmt.equalsIgnoreCase(""))
        		hospAmtF = Long.parseLong(hospAmt);
        		if(nabhMaxAmt < (Long.parseLong(nabhAmt) * Long.parseLong(noOfDays)) )
        		{
        			nabhMaxAmt=Long.parseLong(nabhAmt) * Long.parseLong(noOfDays);
        		}
        		if(hospStayAmt  < hospAmtF )
        		{
        			hospStayAmt =hospAmtF;	
        		}
        		
        	}
        	}
			Iterator iter = surgerys.entrySet().iterator();
 
			while (iter.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iter.next();
				System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
				cmnCatAmt = cmnCatAmt + Long.valueOf(mEntry.getValue().toString())	;
			}
        	TotAmt = hospStayAmt+cmnCatAmt+icdAmt+nabhMaxAmt;
        	//TotAmt = Math.round(TotAmt);
        	request.setAttribute("AjaxMessage", TotAmt);
        	return mapping.findForward("ajaxResult");	
        }
        if(lStrFlagStatus!= null && "getDisbledComorbidList".equals(lStrFlagStatus)) 
        {
        	 String lStrProcs = request.getParameter("procList");
        	 List<String> comorBidList = new ArrayList<String>();
	    	 List<CommonDtlsVO> lstComorbidsTobedisbled = preauthService.getComorbidsDisList(null,lStrProcs);
	    	 if (lstComorbidsTobedisbled != null && lstComorbidsTobedisbled.size() > 0) {
	    			for (CommonDtlsVO commonDtlsVO : lstComorbidsTobedisbled) {
	    				if (commonDtlsVO.getComorbidId() != null && commonDtlsVO.getComorbidName() != null)
	    				{
	    					comorBidList.add(commonDtlsVO.getComorbidId() + "~"+ commonDtlsVO.getComorbidName());
	    					}
	    			}
	    		}
	    		if (comorBidList != null && comorBidList.size() > 0)
	    			request.setAttribute("AjaxMessage", comorBidList);
	    	 return mapping.findForward("ajaxResult");		
        }
        if(lStrFlagStatus!= null && "getOnlineCaseSheet".equals(lStrFlagStatus)) 
        {
        	 String caseId= request.getParameter("caseId");
        	 String patientId= request.getParameter("patientId");
        	 request.setAttribute("caseSheetFlag",request.getParameter("caseSheetFlag"));
        	 request.setAttribute("patientId",patientId);
        	 request.setAttribute("closeBut",request.getParameter("closeBut"));
			request.setAttribute("caseId",caseId);
			request.setAttribute("patientId",patientId);
			request.setAttribute("forPrint",request.getParameter("forPrint"));
        	 if(request.getParameter("caseSheetFlag")!=null && !"".equalsIgnoreCase(request.getParameter("caseSheetFlag")) && "Y".equalsIgnoreCase(request.getParameter("caseSheetFlag")))
        	 {
        		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	 PreauthVO preauthVOPrint = preauthService.getPatientOpDtls(caseId, patientId);
			     request.setAttribute("PatientOpList",preauthVOPrint);
			     PreauthVO	preauthVO1 = preauthService.getTreatingDocDtls(patientId ,caseId); 
			     request.setAttribute("hospDtls",preauthVO1);	
			     List<LabelBean> pastHistoryList=commonService.getPastIllnessHitory();
			     request.setAttribute("pastHistoryList", pastHistoryList);
			     List<LabelBean> symptomsList = preauthService.getSymptomsDtls(caseId);
				 request.setAttribute("symptomsList",symptomsList); 
				 List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(caseId);
				 request.setAttribute("SurgDtls",lstSurgyDtls); 
				 List<LabelBean> familyHistoryList=commonService.getFamilyHistory();
				 request.setAttribute("familyHistoryList", familyHistoryList);
				 List<PreauthClinicalNotesVO> clinicalNotesList=preauthClinicalNotesService.getClinicalNotesForCase(caseId,"PRE");
				 request.setAttribute("preClinicalNotes", clinicalNotesList);
				 clinicalNotesList=preauthClinicalNotesService.getClinicalNotesForCase(caseId,"POST");
				 request.setAttribute("postClinicalNotes", clinicalNotesList);
				 List<DrugsVO> drugs=preauthClinicalNotesService.getIPDrugs(caseId,"PRE");
	             request.setAttribute("drugsLst", drugs);
	             drugs=preauthClinicalNotesService.getIPDrugs(caseId,"POST");
	             request.setAttribute("drugsPostLst", drugs);
	             List<AttachmentVO> lstAttachmentVO = preauthService.getAttachmentsForCase(caseId); 
	             //List<LabelBean> caseAttachList = new ArrayList<LabelBean>();
				 if(lstAttachmentVO.size()>0 )
	            	 {
	                	 for(AttachmentVO attachVO : lstAttachmentVO)
	                	 {
	                		 if(attachVO.getAttachType()!=null && !"".equalsIgnoreCase(attachVO.getAttachType()) && config.getString("webEx_attch").equalsIgnoreCase(attachVO.getAttachType()))
	                		 {
	                			 request.setAttribute("intraOpAttachPath", attachVO.getSavedName());
	                			 request.setAttribute("intraOpAttachName", attachVO.getFileName());
	    						 break;
	                		 }
	    				 }
	    					//request.setAttribute("genInvestList", genInvList);
	                 }
	             List<LabelBean> genInvList = new ArrayList<LabelBean>();
				 String genInvestLst="";
				 CommonDtlsVO userData=preauthService.getOtherDtls(caseId,patientId);
	             if(userData.getInvestigations().size()>0)
	             {
	            	 for(PreauthVO preauthVo : userData.getInvestigations()){
							LabelBean inv = new LabelBean();
							inv.setID(preauthVo.getTest());
							inv.setVALUE(preauthVo.getName());
							inv.setLVL(preauthVo.getRoute());
							genInvestLst = genInvestLst+preauthVo.getTestKnown()+"~"+preauthVo.getSplInvstId()+"~"+preauthVo.getName()+"@";
							genInvList.add(inv);
						}
						request.setAttribute("genInvestList", genInvList);
	             }
				  ehfCase=preauthClinicalNotesService.getCaseDetails(caseId);
				 EhfCaseMedicalDtls ehfCaseMedicalDtls=preauthClinicalNotesService.getCaseMedmgntDtls(caseId);
	        	 EhfCaseSurgicalDtls ehfCaseSurgicalDtls=preauthClinicalNotesService.getCasePacOperationDtls(caseId);
	        	 PreauthClinicalNotesVO preauthClinicalNotesVO= new PreauthClinicalNotesVO();
	        	 if(ehfCaseMedicalDtls!=null)
	        	 {
	        		 request.setAttribute("caseType", "medical");
	        		 preauthClinicalNotesVO.setTreatSurgeonName(ehfCaseMedicalDtls.getSurgeonName());
	        		 preauthClinicalNotesVO.setTreatSurgeonQual(ehfCaseMedicalDtls.getSurgeonQual());
	        		 preauthClinicalNotesVO.setTreatSurgeonRegNo(ehfCaseMedicalDtls.getSurgeonRegno());
	        		 preauthClinicalNotesVO.setTreatSurgeonCnctNo(ehfCaseMedicalDtls.getSurgeonCntctNo());    	             
		             preauthClinicalNotesVO.setTreatAsstSurName(ehfCaseMedicalDtls.getSurgAsstName());
		             preauthClinicalNotesVO.setTreatAsstSurQual(ehfCaseMedicalDtls.getSurgAsstQual());
		             preauthClinicalNotesVO.setTreatAsstSurRegNo(ehfCaseMedicalDtls.getSurgAsstRegno());
		             preauthClinicalNotesVO.setTreatAsstSurContctNo(ehfCaseMedicalDtls.getSurgAsstCntctno());    	             
		             preauthClinicalNotesVO.setTreatExpHospStay(ehfCaseMedicalDtls.getExpectdHospStay());
		             preauthClinicalNotesVO.setTreatNurseName(ehfCaseMedicalDtls.getNurseName());    	             
		             preauthClinicalNotesVO.setTreatParadMedicName(ehfCaseMedicalDtls.getParamedicName());
		             if(ehfCaseMedicalDtls.getAdmnDt()!=null)
		            	 preauthClinicalNotesVO.setTreatSurgStartDt(sdf.format(ehfCaseMedicalDtls.getAdmnDt()));
		             if(ehfCase.getCsSurgDt()!=null)
		             {
		            	 preauthClinicalNotesVO.setSURGERYDATE(sdf.format(ehfCase.getCsSurgDt()));
		             }
		             	 
		             request.setAttribute("expHospStay1", ehfCaseMedicalDtls.getExpectdHospStay());
		             
	        	 }
	        	 
	        	 if(ehfCaseSurgicalDtls!=null)
	        	 {
	        		 request.setAttribute("caseType", "surgical");
	        		 preauthClinicalNotesVO.setSURGEON_NAME(ehfCaseSurgicalDtls.getSurgeonName());
	        		 preauthClinicalNotesVO.setSURGEON_QUAL(ehfCaseSurgicalDtls.getSurgeonQual());
	        		 preauthClinicalNotesVO.setSURGEON_REGNO(ehfCaseSurgicalDtls.getSurgeryRegno());
	        		 preauthClinicalNotesVO.setSURGEON_CNTCTNO(ehfCaseSurgicalDtls.getSurgeryCntctNo());    	             
	        		 preauthClinicalNotesVO.setASST_SURG_NAME(ehfCaseSurgicalDtls.getSurgeryAsst1Name());
	        		 preauthClinicalNotesVO.setASST_SURG_QUAL(ehfCaseSurgicalDtls.getSurgeryAsst1Qual());
	        		 preauthClinicalNotesVO.setASST_SURG_REGNO(ehfCaseSurgicalDtls.getSurgeryAsst1Regno());
	        		 preauthClinicalNotesVO.setASST_SURG_CNTCTNO(ehfCaseSurgicalDtls.getSurgeryAsst1CntctNo()); 
		             if(ehfCase.getCsSurgDt()!=null)
		             {
		            	 preauthClinicalNotesVO.setSURGSTARTTIME(sdf.format(ehfCase.getCsSurgDt()));
		            }
		             
		             request.setAttribute("surgStartTime",ehfCaseSurgicalDtls.getSurgStartTime());
		             request.setAttribute("surgEndTime",ehfCaseSurgicalDtls.getSurgEndTime());
		             String[] strtTime = ehfCaseSurgicalDtls.getSurgStartTime().split(":");
		             String[] endTime = ehfCaseSurgicalDtls.getSurgEndTime().split(":");
		             request.setAttribute("lStrSurgStrHrs", strtTime[0]);
	                 request.setAttribute("lStrSurgStrMins", strtTime[1]);
	                 request.setAttribute("lStrSurgEndHrs", endTime[0]);
	                 request.setAttribute("lStrSurgEndMins", endTime[1]);
	                 if(ehfCaseSurgicalDtls.getAnaesthetistName()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getAnaesthetistName()))
	                	 preauthClinicalNotesVO.setANESTNAME(ehfCaseSurgicalDtls.getAnaesthetistName());
	                 else
	                	 preauthClinicalNotesVO.setANESTNAME("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getAnaesthetistRegNo()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getAnaesthetistRegNo()))
	                	 preauthClinicalNotesVO.setANESTREGNO(ehfCaseSurgicalDtls.getAnaesthetistRegNo());
	                 else
	                	 preauthClinicalNotesVO.setANESTREGNO("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getAnaesthetistMbNo()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getAnaesthetistMbNo()))
	                	 preauthClinicalNotesVO.setANESTMOBILENO(ehfCaseSurgicalDtls.getAnaesthetistMbNo());
	                 else
	                	 preauthClinicalNotesVO.setANESTMOBILENO("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getExpectedHospStay()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getExpectedHospStay()))
	                	 preauthClinicalNotesVO.setEXP_HOSP_STAY(ehfCaseSurgicalDtls.getExpectedHospStay());
	                 else
	                	 preauthClinicalNotesVO.setEXP_HOSP_STAY("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getNurseName()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getNurseName()))
	                	 preauthClinicalNotesVO.setNURSE_NAME(ehfCaseSurgicalDtls.getNurseName()); 
	                 else
	                	 preauthClinicalNotesVO.setNURSE_NAME("-NA-");
	                    	             
	                 if(ehfCaseSurgicalDtls.getParamedicName()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getParamedicName()))
	                	 preauthClinicalNotesVO.setPARAMEDIC_NAME(ehfCaseSurgicalDtls.getParamedicName());
	                 else
	                	 preauthClinicalNotesVO.setPARAMEDIC_NAME("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getAnesthesiaType()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getAnesthesiaType()))
	                	 preauthClinicalNotesVO.setAnesthesiaType(ehfCaseSurgicalDtls.getAnesthesiaType());
	                 else
	                	 preauthClinicalNotesVO.setAnesthesiaType("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getIncisionType()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getIncisionType()))
	                	 preauthClinicalNotesVO.setIncisionType(ehfCaseSurgicalDtls.getIncisionType());
	                 else
	                	 preauthClinicalNotesVO.setIncisionType("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getOpPhotoWebEx()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getOpPhotoWebEx()))
	                	 preauthClinicalNotesVO.setIntraOpPotos(ehfCaseSurgicalDtls.getOpPhotoWebEx());
	                 else
	                	 preauthClinicalNotesVO.setIntraOpPotos("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getVideorec()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getVideorec()))
	                	 preauthClinicalNotesVO.setVideoRec(ehfCaseSurgicalDtls.getVideorec());
	                 else
	                	 preauthClinicalNotesVO.setVideoRec("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getSwabCnt()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSwabCnt()))
	                	 preauthClinicalNotesVO.setSwabCount(ehfCaseSurgicalDtls.getSwabCnt());
	                 else
	                	 preauthClinicalNotesVO.setSwabCount("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getSurturesLigatures()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSurturesLigatures()))
	                	 preauthClinicalNotesVO.setSutureLigature(ehfCaseSurgicalDtls.getSurturesLigatures());
	                 else
	                	 preauthClinicalNotesVO.setSutureLigature("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getSpecimenRem()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSpecimenRem()))
	                	 preauthClinicalNotesVO.setSpecimenRem(ehfCaseSurgicalDtls.getSpecimenRem());
	                 else
	                	 preauthClinicalNotesVO.setSpecimenRem("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getBloodLoss()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getBloodLoss()))
	                	 preauthClinicalNotesVO.setBloodLosss(ehfCaseSurgicalDtls.getBloodLoss());
	                 else
	                	 preauthClinicalNotesVO.setBloodLosss("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getComplications()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getComplications()))
	                	 preauthClinicalNotesVO.setComplications(ehfCaseSurgicalDtls.getComplications());
	                 else
	                	 preauthClinicalNotesVO.setComplications("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getPostOperInstru()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getPostOperInstru()))
	                	 preauthClinicalNotesVO.setPostOperativeInst(ehfCaseSurgicalDtls.getPostOperInstru());
	                 else
	                	 preauthClinicalNotesVO.setPostOperativeInst("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getCondOfPat()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getCondOfPat()))
	                	 preauthClinicalNotesVO.setConditiOfPat(ehfCaseSurgicalDtls.getCondOfPat());
	                 else
	                	 preauthClinicalNotesVO.setConditiOfPat("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getSpecimenName()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSpecimenName()))
	                	 preauthClinicalNotesVO.setSpecimenName(ehfCaseSurgicalDtls.getSpecimenName());
	                 else
	                	 preauthClinicalNotesVO.setSpecimenName("-NA-");
	                 
	                 if(ehfCaseSurgicalDtls.getComplicationsRemarks()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getComplicationsRemarks()))
	                	 preauthClinicalNotesVO.setComplicationsRemarks(ehfCaseSurgicalDtls.getComplicationsRemarks());
	                 else
	                	 preauthClinicalNotesVO.setComplicationsRemarks("-NA-");
	                 
		             request.setAttribute("expHospStay1", ehfCaseSurgicalDtls.getExpectedHospStay());   
		             
		         }  
	        	 request.setAttribute("clinicalNotes", preauthClinicalNotesVO); 
	        	 
	        	 // To get patient photo
	        	 String patOnBedPic=null;
	        	 PatientVO patientVo1=new PatientVO();
				 patientVo1.setCardNo(preauthVOPrint.getRationCard());
				 patientVo1=preauthService.retrieveCardDetails(patientVo1);
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
        			}
				 }
				 request.setAttribute("patOnBedPic",patOnBedPic);	
        	 }
		     return mapping.findForward("onlineCaseSheet");		
        }
        if("getDrugSubList".equalsIgnoreCase(lStrFlagStatus))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugTypeId");        		
			}
			drugSubList=preauthService.getDrugSubList(drugTypeId);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			return mapping.findForward("ajaxResult");	
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
				e.printStackTrace();
			}
			return mapping.findForward("ajaxResult");	
		}
        if("getDrugDetails".equalsIgnoreCase(lStrFlagStatus))
		{
			String drugCode=null;
			if(request.getParameter("lStrDrugCode")!=null && !request.getParameter("lStrDrugCode").equals(""))
			{
				drugCode=request.getParameter("lStrDrugCode");        		
			}
			String drugDetails=preauthService.getDrugDetails(drugCode);

			request.setAttribute("AjaxMessage",drugDetails);
			return mapping.findForward("ajaxResult");	
		}
        if("casePrintForm".equalsIgnoreCase(lStrFlagStatus))
		{
			String caseNo=null;
			String caseId=null;
			//String hospId=null;
			String lStrPageName=null;
			CommonDtlsVO commonDtlsVO=null;
			PatientVO patientVO= new PatientVO();
			PatientVO opDtls= new PatientVO();
			
			SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseNo=request.getParameter("caseId");
			}
			
			String[] caseValues=caseNo.split("/");
			caseId=caseValues[2];
			//hospId=caseValues[1];
			List<CommonDtlsVO> commonDtls=preauthService.getPatientCommonDtls(caseId);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				if(commonDtlsVO.getScheme()!=null)
					request.setAttribute("caseSchemeId",commonDtlsVO.getScheme());
				
				patientVO.setFirstName(commonDtlsVO.getPATIENTNAME());
				patientVO.setSchemeId(commonDtlsVO.getScheme());
				patientVO.setCardNo(commonDtlsVO.getCARDNO());
				patientVO.setDistrictCode(commonDtlsVO.getDISTRICT());
				patientVO.setMandalCode(commonDtlsVO.getMANDAL());
				patientVO.setVillageCode(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null && !"".equals(commonDtlsVO.getDate()))
					patientVO.setOpDate(sdfw.format(commonDtlsVO.getDate()));
				patientVO.setAge(commonDtlsVO.getAGE());
				patientVO.setContactNo(commonDtlsVO.getCONTACT());
				patientVO.setPatientId(commonDtlsVO.getPATID());
				patientVO.setRefHospNo(commonDtlsVO.getHOSPNAME());
				patientVO.setGender(commonDtlsVO.getGENDER());
				patientVO.setAddr1(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientVO.setIpDate(commonDtlsVO.getPATDT());
				patientVO.setIpNo(commonDtlsVO.getIPNO());
				patientVO.setAdmissionType(commonDtlsVO.getADMTYPE());
				patientVO.setRegHospId(commonDtlsVO.getHOSPADDR());
				if(commonDtlsVO.getPatientScheme()!=null)
					patientVO.setPatientScheme(commonDtlsVO.getPatientScheme());

				patientVO.setDocMobileNo(commonDtlsVO.getDOCCONTACT());
				patientVO.setDocQual(commonDtlsVO.getDOCQUAL());
				patientVO.setDocRegNo(commonDtlsVO.getDOCREGNO());
				if("IPM".equalsIgnoreCase(commonDtlsVO.getPATTYPE()))
				{
					String[] caseValuesArr=caseNo.split("/");
					caseId=caseValuesArr[2];
					List<LabelBean> catList = patientService.getCatName(caseId);
					if(catList.size() > 0)
					{
						patientVO.setMedicalSpclty(catList.get(0).getICDNAME());
						patientVO.setMedicalCat(catList.get(0).getVALUE());
					}
					patientVO.setOpNo(commonDtlsVO.getIPNO());
					patientVO.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
					request.setAttribute("IPM", "Y");
				}
				else
				{
					request.setAttribute("IPM", "N");
				}
				if(commonDtlsVO.getRelation()!=null)
					{
						if(commonDtlsVO.getRelation().equalsIgnoreCase("New Born Baby"))
							patientVO.setRelation(commonDtlsVO.getRelation());
					}
				request.setAttribute("commonDtls", patientVO);
				PreauthVO patientData=preauthService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
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
						opDtls.setAgeDays(commonService.getPastIllnessHitoryNames(patientData.getPastIllness()));
					}
					
					if(patientData.getFamilyHis()!=null && !"".equalsIgnoreCase(patientData.getFamilyHis()) )
					{
						opDtls.setAgeMonths(commonService.getFamilyHisNames(patientData.getFamilyHis()));
					}
					
					opDtls.setAsriCatCode(patientData.getSymName());
					opDtls.setAsriDrugCode(patientData.getPatComplaint());
					opDtls.setAsriProcCode(patientData.getComplaintType());
					opDtls.setBiometricVal(patientData.getPresentIllness());
					opDtls.setC_district_code(patientData.getPastIllnessValue());
					opDtls.setC_hamlet_code(patientData.getFamilyHistValue());
					opDtls.setC_mandal_code(patientData.getFamilyHistoryOthr());
					opDtls.setC_mdl_mpl(patientData.getPastIllenesOthr());
					opDtls.setC_phc_code(patientData.getRemarks());
				}
				CommonDtlsVO othrDtls=preauthService.getOtherDtls(caseId, commonDtlsVO.getPATID());
				opDtls.setC_pin_code(othrDtls.getAllergy());
				opDtls.setC_relationshipof_code(othrDtls.getHabbits());
				opDtls.setC_relationshipof_code(othrDtls.getDiagnosisType());
				opDtls.setC_village_code(othrDtls.getMainCatName());
				opDtls.setCatName(othrDtls.getCatName());
				opDtls.setSubCatName(othrDtls.getSubCatName());
				opDtls.setDiseaseName(othrDtls.getDisName());
				opDtls.setDisAnatomicalName(othrDtls.getDisAnatomicalSitename());
				request.setAttribute("investList", othrDtls.getInvestigations());
				String mithraName = preauthService.getEmpNameById(commonDtlsVO.getMithra());
				if(mithraName!=null && !mithraName.equalsIgnoreCase(""))
					opDtls.setMsg(mithraName);

				List<PreauthVO> procDtls=preauthService.getcaseSurgertDtls(caseId);
				request.setAttribute("procDtls", procDtls);
				opDtls.setCaseId(caseNo);
				request.setAttribute("decFlag", request.getParameter("decFlag"));
				if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IP") || commonDtlsVO.getPATTYPE().equalsIgnoreCase("IPM")  )
				{
					opDtls.setDoctorName(preauthService.getEmpNameById(commonDtlsVO.getDOCID()));
					lStrPageName="ipCaseCopy";
				}
				
				else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
				{
					List<DrugsVO> drugs=preauthService.getDrugs(commonDtlsVO.getPATID(),"op");
					request.setAttribute("drugsList",drugs);
					String doctorName="";
					if("Consultant".equalsIgnoreCase(commonDtlsVO.getDoctType()) || "InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
					{
						doctorName = preauthService.getDoctorById(commonDtlsVO.getDOCID());
					}
					else if("DutyDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
					{
						doctorName = commonService.getDutyDoctorById(commonDtlsVO.getDOCID());
					}
					else if("MEDCO".equalsIgnoreCase(commonDtlsVO.getDoctType()))
					{
						doctorName=preauthService.getEmpNameById(commonDtlsVO.getDOCID());
					}
					if(doctorName!=null && !doctorName.equalsIgnoreCase(""))
						opDtls.setDoctorName(doctorName);
						opDtls.setDiagnosedBy(commonDtlsVO.getDoctType());
						lStrPageName="opCaseCopy";
				}
				request.setAttribute("allDtls", opDtls);
				
			}
			return mapping.findForward(lStrPageName);
		}
        
        if("ViewTelephonicDtls".equalsIgnoreCase(lStrFlagStatus)){
			String telephonicId=request.getParameter("telephonicId");
			PatientVO patientVo = new PatientVO();
			patientVo.setTelephonicId(telephonicId);
			PatientVO patientVO1 =  preauthService.getTelephonicIntimationDtls(patientVo);
			request.setAttribute("telDtls", patientVO1);
			request.setAttribute("state", commonService.getLocationName(patientVO1.getState()));
			request.setAttribute("district", commonService.getLocationName(patientVO1.getDistrictCode()));
			request.setAttribute("mandal", commonService.getLocationName(patientVO1.getMandalCode()));
			request.setAttribute("village", commonService.getLocationName(patientVO1.getVillageCode()));
			request.setAttribute("cState", commonService.getLocationName(patientVO1.getC_state()));
			request.setAttribute("cDist", commonService.getLocationName(patientVO1.getC_district_code()));
			request.setAttribute("cMandal", commonService.getLocationName(patientVO1.getC_mandal_code()));
			request.setAttribute("cVillage", commonService.getLocationName(patientVO1.getC_village_code()));
			String[] remarks=patientVO1.getTeleDocremarks().split("~");
			request.setAttribute("RemarksProcedure",remarks[0]);
			request.setAttribute("RemarksDiagnosis",remarks[1]);

			return mapping.findForward("viewTelephonicDetails"); 
		}
        if(lStrFlagStatus!= null && "cochlearQuestionnaire".equals(lStrFlagStatus)) 
        {
        	System.out.println("in action");
        	String caseId=request.getParameter("CaseId");
        	String type=request.getParameter("type");
        	System.out.println(type);
        	System.out.println(caseId);
        	List<cochlearCaseVO> cochlearCaseVo=new ArrayList<cochlearCaseVO>();
        	cochlearCaseVo=preauthService.getCochlearCaseDtls(caseId,type);
        	
        	
        	
        	preauthDetailsForm.setChildName(cochlearCaseVo.get(0).getCHILDNAME());
        	preauthDetailsForm.setFatherName(cochlearCaseVo.get(0).getFATHERNAME());
        	preauthDetailsForm.setChildAge(cochlearCaseVo.get(0).getCHILDAGE());
        	preauthDetailsForm.setHospitalName(cochlearCaseVo.get(0).getHOSPITALNAME());
        	preauthDetailsForm.setAssessDate(cochlearCaseVo.get(0).getASSESSDATE());
        	preauthDetailsForm.setCaseNo(cochlearCaseVo.get(0).getCASENO());
        	preauthDetailsForm.setClaimNo(cochlearCaseVo.get(0).getCLAIMNO());
        	preauthDetailsForm.setCaseId(cochlearCaseVo.get(0).getCASEID());
    		preauthDetailsForm.setAssessDate(cochlearCaseVo.get(0).getASSESSDATE());
        	preauthDetailsForm.setOnsetAge(cochlearCaseVo.get(0).getONSETAGE());
        	preauthDetailsForm.setInterventionAge(cochlearCaseVo.get(0).getINTERVENTIONAGE());
        	preauthDetailsForm.setConventionalAids(cochlearCaseVo.get(0).getCONVENTIONALAIDS());
			preauthDetailsForm.setBenifitFromConventional(cochlearCaseVo.get(0).getBENEFITFROMCONVENTIONAL());
        	preauthDetailsForm.setAuditory(cochlearCaseVo.get(0).getAUDITORY());
        	preauthDetailsForm.setOralaural(cochlearCaseVo.get(0).getORALAURAL());
        	preauthDetailsForm.setSpeakRead(cochlearCaseVo.get(0).getSPEAKREAD());
        	preauthDetailsForm.setMotherAwareness(cochlearCaseVo.get(0).getMOTHERAWARENESS());
        	preauthDetailsForm.setMotivationSpeech(cochlearCaseVo.get(0).getMOTIVATIONSPEECH());
        	preauthDetailsForm.setMotivationAudio(cochlearCaseVo.get(0).getMOTIVATIONAUDIO());
        	preauthDetailsForm.setRealisticExpect(cochlearCaseVo.get(0).getREALISTICEXPECT());
        	preauthDetailsForm.setMiddleEar(cochlearCaseVo.get(0).getMIDDLEEAR());
        	preauthDetailsForm.setCongenital(cochlearCaseVo.get(0).getCONGENITAL());
        	preauthDetailsForm.setBonyCapsule(cochlearCaseVo.get(0).getBONYCAPSULE());
        	preauthDetailsForm.setMileStones(cochlearCaseVo.get(0).getMILESTONES());
        	preauthDetailsForm.setSpeechMechanism(cochlearCaseVo.get(0).getSPEECHMECHANISM());
        	preauthDetailsForm.setADHD(cochlearCaseVo.get(0).getADHD());
        	preauthDetailsForm.setStableQuiet(cochlearCaseVo.get(0).getSTABLEQUIET());
			preauthDetailsForm.setAutistic(cochlearCaseVo.get(0).getAUTISTIC());
        	preauthDetailsForm.setStubborn(cochlearCaseVo.get(0).getSTUBBORN());
        	preauthDetailsForm.setImitative(cochlearCaseVo.get(0).getIMITATIVE());
        	preauthDetailsForm.setFitUnfit(cochlearCaseVo.get(0).getFITUNFIT());
        	preauthDetailsForm.setAudioVerbal(cochlearCaseVo.get(0).getAUDIOVERBAL());
        	
        	
        	
        	preauthDetailsForm.setConventionalAids_remarks(cochlearCaseVo.get(0).getCONVENTIONALAIDS_REMARKS());
			preauthDetailsForm.setBenifitFromConventional_remarks(cochlearCaseVo.get(0).getBENEFIT_REMARKS());
        	preauthDetailsForm.setAuditory_remarks(cochlearCaseVo.get(0).getAUDITORY_REMARKS());
        	preauthDetailsForm.setOralaural_remarks(cochlearCaseVo.get(0).getORALAURAL_REMARKS());
        	preauthDetailsForm.setSpeakRead_remarks(cochlearCaseVo.get(0).getSPEAKREAD_REMARKS());
        	preauthDetailsForm.setMotherAwareness_remarks(cochlearCaseVo.get(0).getMOTHERAWARENESS_REMARKS());
        	preauthDetailsForm.setMotivationSpeech_remarks(cochlearCaseVo.get(0).getMOTIVATIONSPEECH_REMARKS());
        	preauthDetailsForm.setMotivationAudio_remarks(cochlearCaseVo.get(0).getMOTIVATIONAUDIO_REMARKS());
        	preauthDetailsForm.setRealisticExpect_remarks(cochlearCaseVo.get(0).getREALISTICEXPECT_REMARKS());
        	preauthDetailsForm.setMiddleEar_remarks(cochlearCaseVo.get(0).getMIDDLEEAR_REMARKS());
        	preauthDetailsForm.setCongenital_remarks(cochlearCaseVo.get(0).getCONGENITAL_REMARKS());
        	preauthDetailsForm.setBonyCapsule_remarks(cochlearCaseVo.get(0).getBONYCAPSULE_REMARKS());
        	preauthDetailsForm.setMileStones_remarks(cochlearCaseVo.get(0).getMILESTONES_REMARKS());
        	preauthDetailsForm.setSpeechMechanism_remarks(cochlearCaseVo.get(0).getSPEECHMECHANISM_REMARKS());
        	preauthDetailsForm.setADHD_remarks(cochlearCaseVo.get(0).getADHD_REMARKS());
        	preauthDetailsForm.setStableQuiet_remarks(cochlearCaseVo.get(0).getSTABLEQUIET_REMARKS());
			preauthDetailsForm.setAutistic_remarks(cochlearCaseVo.get(0).getAUTISTIC_REMARKS());
        	preauthDetailsForm.setStubborn_remarks(cochlearCaseVo.get(0).getSTUBBORN_REMARKS());
        	preauthDetailsForm.setImitative_remarks(cochlearCaseVo.get(0).getIMITATIVE_REMARKS());
        	preauthDetailsForm.setFitUnfit_remarks(cochlearCaseVo.get(0).getFITUNFIT_REMARKS());
        	preauthDetailsForm.setAudioVerbal_remarks(cochlearCaseVo.get(0).getAUDIOVERBAL_REMARKS());
        	
        	request.setAttribute("caseId", caseId);
        	request.setAttribute("caseNo", cochlearCaseVo.get(0).getCASENO());
        	request.setAttribute("childName",cochlearCaseVo.get(0).getCHILDNAME());
        	request.setAttribute("childAge",cochlearCaseVo.get(0).getCHILDAGE());
        	request.setAttribute("hospitalName",cochlearCaseVo.get(0).getHOSPITALNAME());
        	request.setAttribute("claimNo",cochlearCaseVo.get(0).getCLAIMNO());
        	request.setAttribute("viewType",type);
        											
        	
        	return mapping.findForward("cochlearQuesttionaire"); 
        }
        
        if(lStrFlagStatus!= null && "saveCochlearQuestionnaire".equals(lStrFlagStatus)) 
        {

        	cochlearCaseVO cochlearCaseVo=new cochlearCaseVO();
        	System.out.println("updating for case :"+preauthDetailsForm.getCaseId());
        	cochlearCaseVo.setCASEID(preauthDetailsForm.getCaseId());
        	cochlearCaseVo.setFATHERNAME(preauthDetailsForm.getFatherName());
        	cochlearCaseVo.setASSESSDATE(preauthDetailsForm.getAssessDate());
        	cochlearCaseVo.setONSETAGE(preauthDetailsForm.getOnsetAge());
        	cochlearCaseVo.setINTERVENTIONAGE(preauthDetailsForm.getInterventionAge());
        	cochlearCaseVo.setCONVENTIONALAIDS(preauthDetailsForm.getConventionalAids());
        	cochlearCaseVo.setBENEFITFROMCONVENTIONAL(preauthDetailsForm.getBenifitFromConventional());
        	cochlearCaseVo.setAUDITORY(preauthDetailsForm.getAuditory());
        	cochlearCaseVo.setORALAURAL(preauthDetailsForm.getOralaural());
        	cochlearCaseVo.setSPEAKREAD(preauthDetailsForm.getSpeakRead());
        	cochlearCaseVo.setMOTHERAWARENESS(preauthDetailsForm.getMotherAwareness());
        	cochlearCaseVo.setMOTIVATIONSPEECH(preauthDetailsForm.getMotivationSpeech());
        	cochlearCaseVo.setMOTIVATIONAUDIO(preauthDetailsForm.getMotivationAudio());
        	cochlearCaseVo.setREALISTICEXPECT(preauthDetailsForm.getRealisticExpect());
        	cochlearCaseVo.setMIDDLEEAR(preauthDetailsForm.getMiddleEar());
        	cochlearCaseVo.setCONGENITAL(preauthDetailsForm.getCongenital());
        	cochlearCaseVo.setBONYCAPSULE(preauthDetailsForm.getBonyCapsule());
        	cochlearCaseVo.setMILESTONES(preauthDetailsForm.getMileStones());
        	cochlearCaseVo.setSPEECHMECHANISM(preauthDetailsForm.getSpeechMechanism());
        	cochlearCaseVo.setADHD(preauthDetailsForm.getADHD());
        	cochlearCaseVo.setSTABLEQUIET(preauthDetailsForm.getStableQuiet());
        	cochlearCaseVo.setAUTISTIC(preauthDetailsForm.getAutistic());
        	cochlearCaseVo.setSTUBBORN(preauthDetailsForm.getStubborn());
        	cochlearCaseVo.setIMITATIVE(preauthDetailsForm.getImitative());
        	cochlearCaseVo.setFITUNFIT(preauthDetailsForm.getFitUnfit());
        	cochlearCaseVo.setAUDIOVERBAL(preauthDetailsForm.getAudioVerbal());
        	
        	
        	
        	cochlearCaseVo.setCONVENTIONALAIDS_REMARKS(preauthDetailsForm.getConventionalAids_remarks());
        	cochlearCaseVo.setBENEFIT_REMARKS(preauthDetailsForm.getBenifitFromConventional_remarks());
        	cochlearCaseVo.setAUDITORY_REMARKS(preauthDetailsForm.getAuditory_remarks());
        	cochlearCaseVo.setORALAURAL_REMARKS(preauthDetailsForm.getOralaural_remarks());
        	cochlearCaseVo.setSPEAKREAD_REMARKS(preauthDetailsForm.getSpeakRead_remarks());
        	cochlearCaseVo.setMOTHERAWARENESS_REMARKS(preauthDetailsForm.getMotherAwareness_remarks());
        	cochlearCaseVo.setAUDIOVERBAL_REMARKS(preauthDetailsForm.getAudioVerbal_remarks());
        	cochlearCaseVo.setMOTIVATIONSPEECH_REMARKS(preauthDetailsForm.getMotivationSpeech_remarks());
        	cochlearCaseVo.setMOTIVATIONAUDIO_REMARKS(preauthDetailsForm.getMotivationAudio_remarks());
        	cochlearCaseVo.setREALISTICEXPECT_REMARKS(preauthDetailsForm.getRealisticExpect_remarks());
        	cochlearCaseVo.setMIDDLEEAR_REMARKS(preauthDetailsForm.getMiddleEar_remarks());
        	cochlearCaseVo.setCONGENITAL_REMARKS(preauthDetailsForm.getCongenital_remarks());
        	cochlearCaseVo.setBONYCAPSULE_REMARKS(preauthDetailsForm.getBonyCapsule_remarks());
        	cochlearCaseVo.setMILESTONES_REMARKS(preauthDetailsForm.getMileStones_remarks());
        	cochlearCaseVo.setSPEECHMECHANISM_REMARKS(preauthDetailsForm.getSpeechMechanism_remarks());
        	cochlearCaseVo.setADHD_REMARKS(preauthDetailsForm.getADHD_remarks());
        	cochlearCaseVo.setSTABLEQUIET_REMARKS(preauthDetailsForm.getStableQuiet_remarks());
        	cochlearCaseVo.setAUTISTIC_REMARKS(preauthDetailsForm.getAutistic_remarks());
        	cochlearCaseVo.setSTUBBORN_REMARKS(preauthDetailsForm.getStubborn_remarks());
        	cochlearCaseVo.setIMITATIVE_REMARKS(preauthDetailsForm.getImitative_remarks());
        	cochlearCaseVo.setFITUNFIT_REMARKS(preauthDetailsForm.getFitUnfit_remarks());
        	
        	
        	
        	String status=preauthService.saveCochlearCaseDtls(cochlearCaseVo);
        	request.setAttribute("status", status);
        	String caseId=request.getParameter("caseId");
        	request.setAttribute("caseId", caseId);
        	return mapping.findForward("cochlearQuesttionaire"); 
        	
        	
        	
        	
        }
        
        
        
        
        
        /*ADDED FOR PREAUTH IN CEO LOGIN*/
        
	    if(lStrFlagStatus!= null && "preauthDetailsCeo".equals(lStrFlagStatus)) 
        {
	    	 
	    	SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
			String serverDt = sdfw.format(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			FileInputStream fis=null;
			DataInputStream dis=null;
			byte bytes[]=null;
			String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
	    	
	    	String casesForApprovalFlag = request.getParameter("caseApprovalFlag");
			 request.setAttribute("casesForApprovalFlag", casesForApprovalFlag);
			 System.out.println(casesForApprovalFlag);
			 
	    	 String caseId = request.getParameter("caseId");
	    	 String patientId = request.getParameter("patientId");
	    	 String printFlag = request.getParameter("printFlag");
	    	 request.setAttribute("patientAge", request.getParameter("patientAge"));
	    	 /**
	    	  * get case id and patient id while printing the form
	    	  */
	    	 if(caseId==null || "".equalsIgnoreCase(caseId))
	    		 caseId= preauthDetailsForm.getCaseId();
	    	 if(patientId==null || "".equalsIgnoreCase(patientId))
	    		 patientId= preauthDetailsForm.getPatientId();
	    	 
	    	 
	    	 
	    	 
	    	 /*added for getting patient details*/
	    	 EhfPatient ehfPatient=new EhfPatient();
	    	 ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				//storing patcrtdt
				session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
				preauthDetailsForm.setCardNo(ehfPatient.getCardNo());
				preauthDetailsForm.setFname(ehfPatient.getName());
				/*if(ehfPatient.getCardIssueDt()!=null)
				{
					preauthDetailsForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
				}
				else
					preauthDetailsForm.setCardIssueDt("NA");*/
				if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
				{
					preauthDetailsForm.setGender("Male");
				}
				else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
				{
					preauthDetailsForm.setGender("Female");
				}
				if(ehfPatient.getEnrollDob()!=null)
				{
					preauthDetailsForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
				}
				if(ehfPatient.getAge()!=null)
				{
					preauthDetailsForm.setYears(ehfPatient.getAge().toString());
				}
				if(ehfPatient.getAgeMonths()!=null)
				{
					preauthDetailsForm.setMonths(ehfPatient.getAgeMonths().toString());
				}
				if(ehfPatient.getAgeDays()!=null)
				{
					preauthDetailsForm.setDays(ehfPatient.getAgeDays().toString());
				}
				String relationId=ehfPatient.getRelation();
				String relationName=patientService.getRelationName(relationId);
				//preauthDetailsForm.setRelation(relationName);
				String casteId=ehfPatient.getCaste();
				if(casteId!=null && !casteId.equals(""))
				{
					String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
					preauthDetailsForm.setCaste(casteName);
				}
				if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
				{
					preauthDetailsForm.setContactno(ehfPatient.getContactNo().toString());
				}
				/*String occName = patientService.getOccupationName(ehfPatient.getOccupationCd());
	        if(occName!=null && !occName.equalsIgnoreCase(""))*/
				preauthDetailsForm.setOccupation(ehfPatient.getOccupationCd());
				preauthDetailsForm.setScheme(ehfPatient.getSchemeId());
				preauthDetailsForm.setTelScheme(ehfPatient.getSchemeId());
				/*else
	        	preauthDetailsForm.setOccupation("NA");*/
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
				preauthDetailsForm.setSlab(slab);
				//End Of Slab
				preauthDetailsForm.setHno(ehfPatient.getHouseNo());
				preauthDetailsForm.setStreet(ehfPatient.getStreet());
				preauthDetailsForm.setState(patientService.getLocationName(ehfPatient.getState()));
				String distCode=ehfPatient.getDistrictCode();
				String distName=patientService.getLocationName(distCode);
				preauthDetailsForm.setDistrict(distName);
				String mandalCode=ehfPatient.getMandalCode();
				String mandalName=patientService.getLocationName(mandalCode);
				preauthDetailsForm.setMandal(mandalName);
				String villageCode=ehfPatient.getVillageCode();
				String villageName=patientService.getLocationName(villageCode);
				preauthDetailsForm.setVillage(villageName);
				if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
					preauthDetailsForm.setPin(ehfPatient.getPinCode());
				else
					preauthDetailsForm.setPin("NA");
				//Setting Communication Address
				/*preauthDetailsForm.setComm_hno(ehfPatient.getcHouseNo());
				preauthDetailsForm.setComm_street(ehfPatient.getcStreet());
				preauthDetailsForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
				preauthDetailsForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
				preauthDetailsForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
				preauthDetailsForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
				if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
					preauthDetailsForm.setComm_pin(ehfPatient.getcPinCode());
				else
					preauthDetailsForm.setComm_pin("NA");*/
				preauthDetailsForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
				String photoUrl=null;
				if(ehfPatient.getPatientScheme()!=null)
					{
						preauthDetailsForm.setPatientScheme(ehfPatient.getPatientScheme());
						
						if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
								config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
							 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
						else
							photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
					}
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
						preauthDetailsForm.setPhotoUrl(photoUrl);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						//GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
					}
				}
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 /**
	    	  * get list of attachments to be added when pending updated
	    	  */
	    	 List<LabelBean> lstAttchments = preauthService.getDocCount(caseId,"Y");
	    	 if(lstAttchments != null && lstAttchments.size() > 0)
	    	 {
	    	 for(LabelBean labelBean:lstAttchments)
	    	 {
	    	 if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("consent_docs")))
	    		request.setAttribute("SignPRFForm",labelBean.getCOUNT());
	    	 }
	    	 }
	    	 else
	    	 {
	    		 request.setAttribute("SignPRFForm",0);
	    	 }
	    	 List<LabelBean> lstAttchments1 = preauthService.getDocCount(caseId,null);
	    	 if(lstAttchments != null && lstAttchments.size() > 0)
	    	 {
	    	 for(LabelBean labelBean:lstAttchments1)
	    	 {	
	    	 
	    	 if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("medical_card_attch")))
		    		request.setAttribute("medCardClearence",labelBean.getCOUNT());
	    	 if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("blood_transfusion_attch")))
		    		request.setAttribute("bloodTransfusionAttach",labelBean.getCOUNT());
	    	 }}
	    	 /**
	    	  * end 
	    	  */
	    	 /**
	    	  * print form details
	    	  */
	    	 if(printFlag != null && printFlag.equalsIgnoreCase("Y"))
		    	{
	    	  PreauthVO preauthVOPrint = preauthService.getPatientOpDtls(caseId, patientId);
		      request.setAttribute("PatientOpList",preauthVOPrint);
		      request.setAttribute("mitActorname", preauthVOPrint.getCruUsr());
		      List<LabelBean> lstInvestigations=preauthService.getInvestigations(patientId);
		      request.setAttribute("investigationsLst",lstInvestigations);
			      //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		      Date date = new Date();
		      String lStrCurentDate = sdf.format(date);
		      request.setAttribute("lStrCurentDtTime",lStrCurentDate);
			      request.setAttribute("lStrCurentDt",new SimpleDateFormat("dd/MM/yyyy").format(date));
			      List<LabelBean> symptomsList = new ArrayList<LabelBean>();
			      symptomsList = preauthService.getSymptomsDtls(caseId);
				  request.setAttribute("symptomsList",symptomsList);
				  List<LabelBean> pastHistoryList=null;
				  pastHistoryList=commonService.getPastIllnessHitory();
			      request.setAttribute("pastHistoryList", pastHistoryList);
			      request.setAttribute("mitActDt", lStrCurentDate);
			      List<PreauthVO> casesWorkList= preauthService.getCasesWorkList(caseId);
			      if(casesWorkList!=null && casesWorkList.size()>0)
			      {
			    	  for(int i=0; i<casesWorkList.size();i++)
			    	  {
			    		  if(casesWorkList.get(i).getActId()!=null && !"".equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    		  {
			    			  if(config.getString("mitDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("mitView", "Y");
			    				  request.setAttribute("mitActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("mitActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("mitRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("ptdAprvDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("ptdAprvView", "Y");
			    				  request.setAttribute("ptdAprvActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("ptdAprvActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("ptdAprvRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("ptdRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("ptdRejView", "Y");
			    				  request.setAttribute("ptdRejActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("ptdRejActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("ptdRejRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("ppdDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("ppdView", "Y");
			    				  request.setAttribute("ppdActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("ppdActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("ppdRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("ppdRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("ppdRejView", "Y");
			    				  request.setAttribute("ppdRejActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("ppdRejActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("ppdRejRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("eoAprvDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("eoAprvView", "Y");
			    				  request.setAttribute("eoAprvActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("eoAprvActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("eoAprvRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("eoRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("eoRejView", "Y");
			    				  request.setAttribute("eoRejActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("eoRejActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("eoRejRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("ceoAprvDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("ceoAprvView", "Y");
			    				  request.setAttribute("ceoAprvActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("ceoAprvActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("ceoAprvRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    			  else if(config.getString("ceoRejDec").equalsIgnoreCase(casesWorkList.get(i).getActId()))
			    			  {
			    				  request.setAttribute("ceoRejView", "Y");
			    				  request.setAttribute("ceoRejActorname", casesWorkList.get(i).getAuditName());
			    				  request.setAttribute("ceoRejActDt", casesWorkList.get(i).getAuditDate());
			    				  request.setAttribute("ceoRejRemarks", casesWorkList.get(i).getRemarks());
			    			  }
			    		  }
			    	  }
			    	  if(isMedco_Mithra)
			    	  {
			    		  request.setAttribute("ptdAprvActorname", "Trust Doctor");
			    		  request.setAttribute("ptdRejActorname", "Trust Doctor");
			    		  request.setAttribute("ppdActorname", "Panel Doctor");
			    		  request.setAttribute("ppdRejActorname", "Panel Doctor");
			    		  request.setAttribute("eoAprvActorname", "EO");
			    		  request.setAttribute("eoRejActorname", "EO");
			    		  request.setAttribute("ceoAprvActorname", "CEO");
			    		  request.setAttribute("ceoRejActorname", "CEO");
			    	  }
			      }
			      
		    	}
	    	 List<LabelBean> catList = commonService.getCategorys(null,lStrEmpId);
	    	 request.setAttribute("catList", catList);
	    	 /**
		    	 * Populate Dental Units
		    	 */
	    	 List<LabelBean> dentalUnitsList = new ArrayList<LabelBean>();
			String dentalUnitsStr=config.getString("Dental.Units");
			if(dentalUnitsStr!=null && !"".equalsIgnoreCase(dentalUnitsStr))
			{
				String[] dentalUnitslst=dentalUnitsStr.split("~");
				for(int i=0;i<dentalUnitslst.length;i++)
				{
					LabelBean lb=new LabelBean();
					lb.setID(dentalUnitslst[i]);
					lb.setVALUE(dentalUnitslst[i]);
					dentalUnitsList.add(lb);
				}
			}
			request.setAttribute("dentalUnitsList",dentalUnitsList);
		    	/**
		    	 * check for telephonic intimation
		    	 */
		    	 preauthVO = preauthService.getTelephonicDtls(caseId);
		    	if(preauthVO.getTelephonicFlag() == null || preauthVO.getTelephonicFlag().equals("")|| preauthVO.equals("N"))
		    	{
		    		preauthDetailsForm.setTelephonicFlag("No");	
		    	}
		    	else
		    	{
		    		preauthDetailsForm.setTelephonicFlag("Yes");		
		    		preauthDetailsForm.setTelephonicId(preauthVO.getTelephonicId());
		    		preauthDetailsForm.setTelephonicRemarks(preauthVO.getTelephonicRemarks());
		    	}
		    	preauthDetailsForm.setEnhApprAmt(preauthVO.getEnhApprAmt());
		    	preauthDetailsForm.setCaseNo(preauthVO.getCaseNo());
		    	request.setAttribute("status", preauthVO.getCaseStatusId());
		    	/**
		    	 * get nwh and treating doc Details
		    	 */
		    	PreauthVO	preauthVO1 = preauthService.getTreatingDocDtls(patientId ,caseId); 
		    	preauthDetailsForm.setTreatmntDt(preauthVO1.getSurgeryDt());
		    	preauthDetailsForm.setDischrgeDt(preauthVO1.getDischrgeDt());
		    	preauthDetailsForm.setHospAddress(preauthVO1.getHospAddress());
		    	preauthDetailsForm.setHospitalName(preauthVO1.getHospitalName());
		    	preauthDetailsForm.setDocName(preauthVO1.getDocName());
		    	preauthDetailsForm.setDocReg(preauthVO1.getDocReg());
		    	preauthDetailsForm.setDocMobNo(preauthVO1.getDocMobNo());
		    	preauthDetailsForm.setDocQual(preauthVO1.getDocQual());
		    	preauthDetailsForm.setDocType(preauthVO1.getDocType());
		    	preauthDetailsForm.setAdmissionRadio(preauthVO1.getAdmissionRadio());
		    	preauthDetailsForm.setHospContactNo(preauthVO1.getHospContactNo());
		    	preauthDetailsForm.setHospCode(preauthVO1.getHospDispCode());
		    	preauthDetailsForm.setAdmissionDate(preauthVO1.getAdmissionDate());
		    	preauthDetailsForm.setAdmissionDate(preauthVO1.getIpRegDate());
		    	preauthDetailsForm.setAdmissionType(preauthVO1.getAdmissionRadio());
		    	preauthDetailsForm.setIpRegDate(preauthVO1.getIpRegDate());
		    	preauthDetailsForm.setComorBidVals(preauthVO1.getComorBidVals());
		    	preauthDetailsForm.setComorBidAmt(preauthVO1.getComorBidAmt());
		    	preauthDetailsForm.setComorBidAppvAmt(preauthVO1.getComorBidAmt());
		    	preauthDetailsForm.setTotPkgAmt(preauthVO1.getTotPackgAmt());
		    	//preauthDetailsForm.setNabhFlg(preauthVO1.getNabhFlg());
		    	preauthDetailsForm.setPreauthPckgAmt(preauthVO1.getPreauthPckgAmt());
		    	preauthDetailsForm.setApprvdPckAmt(preauthVO1.getPreauthPckgAmt());
		    	preauthDetailsForm.setComorbidValues(preauthVO1.getComorbidValues());
		    	preauthDetailsForm.setPendingFlag(preauthVO1.getPendingFlag());
		    	preauthDetailsForm.setDocSpec(preauthVO1.getDoctorSpeciality());
		    	preauthDetailsForm.setHospStayAmt(preauthVO1.getHospStayAmt());
		    	preauthDetailsForm.setHospitalId(preauthVO1.getHospitalId());
		    	preauthDetailsForm.setPreauthTotalPackageAmt(preauthVO1.getPreauthTotalPackageAmt());
		    	preauthDetailsForm.setIpNo(preauthVO1.getPatientIPNo());
		    	preauthDetailsForm.setProcedureConsent(preauthVO1.getProcedureConsent());
		    	preauthDetailsForm.setMedCardioClearence(preauthVO1.getMedCardioClearence());
		    	preauthDetailsForm.setBloodTransfusion(preauthVO1.getBloodTransfusion());
		    	//preauthDetailsForm.setState(preauthVO1.getScheme());
		    	preauthDetailsForm.setCochlearYN(preauthVO1.getCochlearYN());
		    	preauthDetailsForm.setCochlearQuestionnaire(preauthVO1.getCochlearQues());
		    	preauthDetailsForm.setNabhFlg(preauthVO1.getNabhFlg());
		    	preauthDetailsForm.setOrganTransYN(preauthVO1.getOrganTransYN());
		    	//preauthDetailsForm.setGenRemarks("Approved");
		    	int grp=0;
		    	List<String> grps=new ArrayList<String>();
		    	preauthDetailsForm.setSchemeId(preauthVO1.getScheme());
		    	if(preauthDetailsForm.getSchemeId()!=null)
		    		{
		        		for(LabelBean labelBean:groupsList)
		        			{
		        				if(labelBean.getID() != null && !"".equalsIgnoreCase(labelBean.getID()))
		        					{
		        						grp++;
		        						grps.add(labelBean.getID());
		        					}
		        			}
		    		
		    			if(grp>0 && grps!=null && grps.size()>0)
		    				{
		    					if(grps.contains("GP2"))
		    						request.setAttribute("medcoFlg", "Y");
		    				}
		    	/*		if(preauthVO1.getCaseNabhFlg()!=null)
		    				{
			    				if(preauthDetailsForm.getSchemeId().equalsIgnoreCase("CD201") && preauthVO1.getPreauthTotalPackageAmt()!=null && preauthVO1.getCaseNabhFlg().equalsIgnoreCase("Y"))
			    					preauthDetailsForm.setPreauthNabhAmt((int)Math.round(0.25*Integer.parseInt(preauthVO1.getPreauthTotalPackageAmt())));
			    				else	
			    					preauthDetailsForm.setPreauthNabhAmt(0);
		    				}
		    			else
		    				{
		    					if(preauthVO1.getNabhFlg()!=null)
		    						{
		    							if(preauthVO1.getNabhFlg().equalsIgnoreCase("Y") && preauthDetailsForm.getSchemeId().equalsIgnoreCase("CD201") && preauthVO1.getPreauthTotalPackageAmt()!=null)
		    								preauthDetailsForm.setPreauthNabhAmt((int)Math.round(0.25*Integer.parseInt(preauthVO1.getPreauthTotalPackageAmt())));
		    							else
		    								preauthDetailsForm.setPreauthNabhAmt(0);
		    						}
		    					else
		    						preauthDetailsForm.setPreauthNabhAmt(0);
		    				}	*/
		    		}
		    		
		    	/**
		    	 * get diagnosois and treatment
		    	 */
		    	request.setAttribute("cochlearYN",preauthVO1.getCochlearYN());
		    	request.setAttribute("cochlearQues", preauthVO1.getCochlearQues());
		    	request.setAttribute("organTransYN", preauthVO1.getOrganTransYN());
		    	enhancementFlag = preauthVO1.getEnhancementFlag();
		    	//enhCaseStatus = preauthVO1.getEnhCaseStatus();
		    	preauthDetailsForm.setEnhancementFlag(enhancementFlag);
		    	preauthDetailsForm.setEnhancementFlag(preauthVO1.getEnhancementFlag());
		    	if(preauthVO1.getDiagnosisType()==null || "".equalsIgnoreCase(preauthVO1.getDiagnosisType()))
		    		preauthDetailsForm.setDiagnosisType("-NA-");
		    	else
		    		preauthDetailsForm.setDiagnosisType(preauthVO1.getDiagnosisType());
		    	if(preauthVO1.getMainCatName()==null || "".equalsIgnoreCase(preauthVO1.getMainCatName()))
		    		preauthDetailsForm.setMainCatName("-NA-");
		    	else
		    		preauthDetailsForm.setMainCatName(preauthVO1.getMainCatName());
		    	if(preauthVO1.getCatName()==null || "".equalsIgnoreCase(preauthVO1.getCatName()))
		    		preauthDetailsForm.setCatName("-NA-");
		    	else
		    		preauthDetailsForm.setCatName(preauthVO1.getCatName());
		    	if(preauthVO1.getSubCatName()==null || "".equalsIgnoreCase(preauthVO1.getSubCatName()))
		    		preauthDetailsForm.setSubCatName("-NA-");
		    	else
		    		preauthDetailsForm.setSubCatName(preauthVO1.getSubCatName());
		    	if(preauthVO1.getDisName()==null || "".equalsIgnoreCase(preauthVO1.getDisName()))
		    		preauthDetailsForm.setDisName("-NA-");
		    	else
		    		preauthDetailsForm.setDisName(preauthVO1.getDisName());
		    	if(preauthVO1.getDisAnatomicalSitename()==null || "".equalsIgnoreCase(preauthVO1.getDisAnatomicalSitename()))
		    		preauthDetailsForm.setDisAnatomicalSitename("-NA-");
		    	else
		    		preauthDetailsForm.setDisAnatomicalSitename(preauthVO1.getDisAnatomicalSitename());
		    	preauthDetailsForm.setPatientId(preauthVO1.getPatientID());
		    	preauthDetailsForm.setCaseStatus(preauthVO.getCaseStatusId());
		    	preauthDetailsForm.setEnhApprvDt(preauthVO1.getEnhApvDt());
		    	preauthDetailsForm.setEnhRejDate(preauthVO1.getEnhRejDt());
		    	preauthDetailsForm.setEnhAmt(preauthVO1.getEnhAmt());
		    	preauthDetailsForm.setEnhApprvAmt(preauthVO1.getEnhAmt());
		    	preauthDetailsForm.setPatientScheme(preauthVO1.getPatientScheme());
		    	if(preauthVO1.getEnhAmt()!=null){
		    	int enhAmt=Integer.parseInt(preauthVO1.getEnhAmt());
		    	request.setAttribute("enhAmt", enhAmt);}
		    	request.setAttribute("enhancementFlag", enhancementFlag);
		    	request.setAttribute("nabhFlag",preauthVO1.getNabhFlg());
		    	
		    	/**
		    	 *   changed
		    	 */
		    	List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(caseId);
		    	preauthDetailsForm.setLstPreauthVO(lstSurgyDtls);
		    	if(printFlag == null || !printFlag.equalsIgnoreCase("Y"))
		    	{
		    	/**
		    	 * get enhancement list
		    	 */
	    		String patientType = "";
	    		 EhfPatient ehfPatientDtls = preauthService.getPatDtls(patientId);
		    	 if(ehfPatientDtls != null)
			    		 request.setAttribute("ipFlag",ehfPatientDtls.getPatientIpop());
	    		if(ehfPatientDtls.getPatientIpop() != null)
		    			patientType=	ehfPatientDtls.getPatientIpop();
	    		
	    		String nimsHosp=loginService.getNimsMedcoDoc(caseId);
	    		String nimsFalg="";
		    	if(!"".equalsIgnoreCase(nimsHosp) && ("EHS34".equalsIgnoreCase(nimsHosp) || "EHS13".equalsIgnoreCase(nimsHosp)))
		    	{
		    		request.setAttribute("nimsMedco", "Y");
		    		nimsFalg="Y";
		    	}
		    	else
		    	{
		    		request.setAttribute("nimsMedco", "N");
		    		nimsFalg="N";
		    	}	
	    		List<PreauthVO> lstenhancementDtls = preauthService.getEnhancementList(caseId,patientType,nimsFalg);
	    		preauthDetailsForm.setEnhamcementList(lstenhancementDtls);
		    	/**
		    	 * get audit trail
		    	 */
		    	PreauthVO auditVo = new PreauthVO();
		    	auditVo.setCaseId(caseId);
		    	
		    	auditVo.setUserRole(lStrUserGroup);
		    	//auditVo.setUserRole(lStrUserRole);
		    	/**
		    	 * audit needs to be changed
		    	 */
		    	List<PreauthVO> lstWorkFlow = preauthService.getAuditTrail(auditVo);
		    	preauthDetailsForm.setLstworkFlow(lstWorkFlow);
		    	
		    	
		    	preauthDetailsForm.setStatus(preauthVO.getCaseStatusId());
		    	//preauthDetailsForm.setGenRemarks("");
		    	
		    	// enhancement audit
		    	auditVo.setEnhancementFlag(config.getString("activeY"));
		    	List<PreauthVO> lstEnhancementWorkFlow = preauthService.getAuditTrail(auditVo);
		    	preauthDetailsForm.setLstEnhancementworkFlow(lstEnhancementWorkFlow);
		    	
		    	/**
		    	 * check which view should be enabled dynamically
		    	 */
		    	String roleStatus = ","+lStrUserGroup+"~"+preauthVO.getCaseStatusId()+",";
		    	
		    	
		    	
		    	
		    	
		    	if(!("Y").equalsIgnoreCase(enhancementFlag))
		    	{
		    	if(config.getString("preauth_role_status_mapping").contains(roleStatus))
		    	{
		    		/**
			    	 * get buttons list  based on particular roles and case status
			    	 */
			    	
			    	List<LabelBean> buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_sub_module"));
			    	
			    	
		    		if(config.getString("preauth_statusName_"+lStrUserGroup) != null && !config.getString("preauth_statusName_"+lStrUserGroup).equalsIgnoreCase(""))
		    		{
		    		request.setAttribute("viewType",config.getString("preauth_statusName_"+lStrUserGroup) );	
		
		    		preauthDetailsForm.setButtonsLst(buttonsList);
		    	}
		    		else
		    			request.setAttribute("viewType", "disable");		
		    	}
		    	else
	    			request.setAttribute("viewType", "disable");
		    	
		    	}
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	if(("Y").equalsIgnoreCase(enhancementFlag))
		    	{
		    	if(config.getString("preauth_role_status_mapping_enh").contains(roleStatus))
		    	{
		    		/**
			    	 * get buttons list  based on particular roles and case status
			    	 */
			    	
			    	List<LabelBean> buttonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_enh_sub_module"));
			    	
			    	
		    		if(config.getString("preauth_statusName_"+lStrUserGroup) != null && !config.getString("preauth_statusName_"+lStrUserGroup).equalsIgnoreCase(""))
		    		{
		    		request.setAttribute("viewType",config.getString("preauth_statusName__enh_"+lStrUserGroup) );	
		
		    		preauthDetailsForm.setEnhButtonsLst(buttonsList);
		    	}
		    		else
		    			request.setAttribute("viewType", "disable");		
		    	}
		    	else
	    			request.setAttribute("viewType", "disable");
		    	
		    	}
		    	
		    	//To show Auditor Names to Trust Users
		    	for(LabelBean labelBean:groupsList)
		    	{
			    	if(labelBean.getID() != null && config.getString("view_audit_names")!=null && config.getString("view_audit_names").contains("~"+labelBean.getID()+"~") )
			    	{
			    		request.setAttribute("viewAuditNames", "Y");
				    	break;	
			    	}
		    	}
		    	
		    	/**
		    	 * get enhancement drugs
		    	 */
		    	List<DrugsVO> drugs=preauthService.getIpDrugs(preauthDetailsForm.getPatientId(),"Preauth");
		    	preauthDetailsForm.setDrugLt(drugs);
		    	List<LabelBean> drugsList=commonService.getDrugs();
				request.setAttribute("drugsList", drugsList);
		    	/**
		    	 * for enhancement get view type
		    	 */
		    	if(preauthVO1.getMedicalSurgicalFlag() != null && preauthVO1.getMedicalSurgicalFlag().equalsIgnoreCase("Y") && ((preauthVO1.getCeoApprvFlag() != null && preauthVO1.getCeoApprvFlag().equalsIgnoreCase("A") &&  preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ceo_approved"))) || ((preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("preauth_ptd_approved"))||preauthVO.getCaseStatusId().equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")))  && (preauthVO1.getCeoApprvFlag() == null || preauthVO1.getCeoApprvFlag().equalsIgnoreCase("") || !preauthVO1.getCeoApprvFlag().equalsIgnoreCase("Y"))) || (preauthVO1.getEnhancementFlag() != null && preauthVO1.getEnhancementFlag().equals("Y"))))
		    	{
		    		
		    	if(config.getString("preauth_role_status_mapping_enh").contains(roleStatus))
		    	{
		    		if(config.getString("preauth_statusName__enh_"+lStrUserGroup) != null && !config.getString("preauth_statusName__enh_"+lStrUserGroup).equalsIgnoreCase(""))
		    		{
		    			if(config.getString("preauth_statusName__enh_"+lStrUserGroup).equalsIgnoreCase("enhMedco"))
		    			{
		    				/**
					    	 * get Enhancement details
					    	 */
					    	List<LabelBean> hospStay = preauthService.getHospStayist(config.getString("enhType_HospitalStay"));
					    	request.setAttribute("hospStay", hospStay);
					    	List<LabelBean> imageologyStay = preauthService.getHospStayist(config.getString("enhType_Imageology"));
					    	request.setAttribute("imageologyStay", imageologyStay);
					    	List<LabelBean> labInvestigations = preauthService.getHospStayist(config.getString("enhType_LabInvestigations"));
					    	request.setAttribute("labInvestigations", labInvestigations);
					    	List<LabelBean> drugTypes = preauthService.getHospStayist(config.getString("enhType_drugs"));
					    	request.setAttribute("drugTypes", drugTypes);
					    	List<LabelBean> implantTypes = preauthService.getHospStayist(config.getString("enhType_implants"));
					    	request.setAttribute("implantTypes", implantTypes);	
		    			}
		    			/**
				    	 * get enhancement workflow
				    	 */
				    	auditVo.setEnhancementFlag("Y");
				    	
				    	/**
				    	 * end of workflow
				    	 */
				    	
				    	/**
				    	 * get enhancement buttons list
				    	 */
				    	if(preauthVO1.getEnhRejDt() == null || preauthVO1.getEnhRejDt().equals(""))
				    	{
					    	List<LabelBean> enhButtonsList  = new ArrayList<LabelBean>();
				    	 enhButtonsList =  commonService.getDynamicWrkFlowDtls(preauthVO.getCaseStatusId(), lStrUserGroup,config.getString("preauth_main_module"),config.getString("preauth_enh_sub_module"));
				    	 //if(!"N".equalsIgnoreCase(casesForApprovalFlag))
				    	 //preauthDetailsForm.setEnhButtonsLst(enhButtonsList);
				    	}
				    	 preauthDetailsForm.setGenRemarks("");
				    	/**
				    	 * end of enhancement details
				    	 */
		    				request.setAttribute("viewType",config.getString("preauth_statusName__enh_"+lStrUserGroup) );	
		    		}
		    		else
		    			request.setAttribute("viewType", "disable");		
		    	}
		    	else
	    			request.setAttribute("viewType", "disable");
		    	}
		    	
		    	

		    	
		    	preauthDetailsForm.setCaseId(caseId);
		    	
	    	// return mapping.findForward("preauthDtlsEhf");
		    	 // set total package amount for preauth and ehf
		    	 request.setAttribute("preauthAmt", Integer.parseInt(config.getString("preauth_package_amt_limit")));
		    	 request.setAttribute("preauthEnhAmt", Integer.parseInt(config.getString("preauth_enh_amt_limit")));
	    	
		    	 // get comorbid list
		    	 
		    	 List<CommonDtlsVO> lstComorbids = preauthService.getComorBidVals();
		    	 request.setAttribute("lstComorbid", lstComorbids);
		    	
		    	 /**
		    	  *  cancel button provision
		    	  */
		    	 boolean cancelButt=false;
		    	 if(isMedco && preauthVO1.getPreauthInitiatedDt() != null && (preauthVO1.getPreauthRejDt() ==null || preauthVO1.getPreauthRejDt().equals("")) 
		    			 &&(preauthVO1.getSurgeryDt() == null || preauthVO1.getSurgeryDt().equals("")) 
		    			 && (preauthVO1.getPreauthCancelDt() ==null || preauthVO1.getPreauthCancelDt().equals("")))
		    	 {
		    		 if(!"N".equalsIgnoreCase(casesForApprovalFlag))
		    		 	{
		    			 cancelButt=true; 
		    			 request.setAttribute("cancelBut", cancelButt);
		    		 	}
		    	 }
		    	 
		    	 request.setAttribute("comorbidAmt",preauthVO1.getComorBidAmt());
		    	 request.setAttribute("preauthPkgAmt",preauthVO1.getPreauthPckgAmt());
		    	 return mapping.findForward("operationsPreauth");
		    	}
		    
	    	 
	    } 
        
        
        
	    /*added for updating ceo sent back cases */  /*by venkatesh*/
		if("updateSentBackCases".equalsIgnoreCase(lStrFlagStatus))
		{
		String caseId=request.getParameter("caseId");
		String followupId=request.getParameter("followUpId");
		String sendBackFlag=request.getParameter("sendBackFlag");
		String remarks=request.getParameter("remarks");
		String caseStatus=request.getParameter("caseStatus");
		//String actionDone=request.getParameter("actionDone");
		String moduleType=request.getParameter("moduleType");
		ClaimsFlowVO claimFlowVO=new ClaimsFlowVO();
		String msg=null;
		
		//if(("claims").equalsIgnoreCase(moduleType))
		claimFlowVO.setCaseId(caseId);
		//else if(("followUp").equalsIgnoreCase(moduleType))
		//claimFlowVO.setFollowUpId(followupId);	
		claimFlowVO.setSendBackFlag(sendBackFlag);
		claimFlowVO.setRemarks(remarks);
		claimFlowVO.setModuleType(moduleType);
		claimFlowVO.setUserId(lStrEmpId);
		
		lStrUserGroup = config.getString("preauth_caseStatus_"
				+ caseStatus);
		
		claimFlowVO.setRoleId(lStrUserGroup);
		
		msg=preauthService.updateSentBackClaims(claimFlowVO);
		
		request.setAttribute("AjaxMessage",msg);
		return mapping.findForward("ajaxResult");
		
		
		}
		//sai:#CR8566:actionvalue to get the details of Dialysis Cycles details.
	    if(lStrFlagStatus!= null && "viewDialysisCycles".equals(lStrFlagStatus)){
	    	preauthVO.setCaseId(request.getParameter("CaseId"));
	    	List<LabelBean> dialysisList = preauthService.getDialysisCycles(preauthVO);
	    	if(dialysisList!=null && !dialysisList.isEmpty()){
	    		request.setAttribute("dialysisList",dialysisList);
	    	}
	    	return mapping.findForward("viewDialysisCycles");
	    }
		//ALERT OR POP UP MSG FOR MULTIPLE PREAUTHS RAISED FOR SINGLE PATIENT
		if(lStrFlagStatus!= null && "checkPreauthDates".equals(lStrFlagStatus)) 
        {
	    	 String cardNo = request.getParameter("cardNo");
	    	 String msg="";
			 List<CasesSearchVO> preauthList=preauthService.getPreauthDtls(cardNo);
			 if(preauthList!=null )
				{
					if(preauthList.size()>0)
						{
							for(int i=0;i<preauthList.size();i++)
							{
								CasesSearchVO caseVO= new CasesSearchVO();
								caseVO.setDIFF(preauthList.get(i).getDIFF());
								caseVO.setCARDNO(preauthList.get(i).getCARDNO());
								if(preauthList.get(i).getDIFF().intValue()>0 && preauthList.get(i).getDIFF().intValue() <= 7)
								{
									msg = "success";
								}
								else
								{
									msg="No Preauth";
								}
							}
						}
				}
								
				
				
	    	 request.setAttribute("AjaxMessage",msg);
	    	 return mapping.findForward("ajaxResult");	
        }
		if(lStrFlagStatus!= null && "getHealthCardDetails".equals(lStrFlagStatus)) 
        {
		String json="";
		String cardNo=request.getParameter("cardNo");
		String x=preauthCaseId;
		List<LabelBean> cardDetails=commonService.getCardDetails(cardNo);
		if(cardDetails!=null){
		Gson gson = new Gson();
	 	HashMap<String,Object> lParamMap = new HashMap<String,Object>();
		lParamMap.put("cardDetails",cardDetails);
		 json = gson.toJson(lParamMap);
		request.setAttribute("AjaxMessage",json);
		}
		else
			request.setAttribute("AjaxMessage",null);
   	 	return mapping.findForward("ajaxResult");	
        }
	        return null;
	    }
	 
	 
	 
		
	 
	 
}

