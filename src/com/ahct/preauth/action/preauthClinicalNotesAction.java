package com.ahct.preauth.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.form.PreauthClinicalNotesForm;
import com.ahct.preauth.service.PreauthClinicalNotesService;
import com.ahct.preauth.service.PreauthService;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.model.EhfCaseMedicalDtls;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfCaseSurgicalDtls;
import com.ahct.model.EhfDischargeSummary;
import com.ahct.model.EhfTelephonicRegn;
import com.ahct.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;

import org.apache.log4j.Logger;
//import java.lang.reflect.Field;

public class preauthClinicalNotesAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
			{
		HttpSession session = null;
		String lStrActionFlag = null;
		String lStrResultPage = null;        
		String lStrUserId=null;   
		//		String lStrUserRole=null;      
		String lStrCaseId=null;
		String lStrResultMsg=null;
		//String lStrCaseStatus=null;
		//String lStrSurgeonDivFlag=null;
		String lStrMedMgmtFlag=null;
		String lStrMedicalFlag=null;
		String lStrAddClinicNotesFlag=null;
		String lstrRestrictFlag="1";
		String lStrPostOPNotesFlag;
		String lStrDischargeSumryFlag;
		int iPostClinicCnt=0;
		String lStrPostAddClinicNotesFlag;
		String lStrTrmtSurgSubmitFlag=null;
		boolean lStrResultFlag=false;
		String lStrDischargeSumryRadioFlag=null;
		String lStrDischargeUpdateSuccess=null;
		String lStrDischargeSumryButFlag=null;
		String lStrDischargeSumryPrintFlag=null;
		Long lPostOpNotespPendCnt=0L;
		String lStrTrmtMedicalSubmitFlag = null;
		String lStrMedcoRole = null;
		String lStrUserGroup = null;
		String preauthCaseStatus  = null;
		String caseApprovalFlag = null;
		String lStrFollowupFlag = null;
		String lStrCochlearFollowUpFlag=null;
		int cochlearFollowUpCount=0;
		String followUpRole=null;
		String enhApprovedFlag = null;
		String ipOpType = null;
		String pStrPatientId=null;
		String caseStatus=null;
		int dop=0,nonDop=0;
		
		PreauthClinicalNotesService preauthClinicalNotesService = (PreauthClinicalNotesService)ServiceFinder.getContext(request).getBean("preauthClinicalNotesService");
		PreauthService preauthService = (PreauthService)ServiceFinder.getContext(request).getBean("preauthService");
		CommonService  commonService = (CommonService)ServiceFinder.getContext(request).getBean("commonService");
		TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		PreauthClinicalNotesForm preauthClinicalNotesForm=(PreauthClinicalNotesForm)form;
		Logger logger = Logger.getLogger(preauthClinicalNotesAction.class);
		session = request.getSession(false);
		String callType=null;
		if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
		{
			callType=request.getParameter("callType");
		}
		lStrResultPage = HtmlEncode.verifySession(request,response);
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
		ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		List<LabelBean> groupsList = null;
		if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
			groupsList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
		if(session.getAttribute("UserID")!=null && !session.getAttribute("UserID").equals(""))
			lStrUserId=session.getAttribute("UserID").toString();
		/* if(session.getAttribute("UserRole")!=null && !session.getAttribute("UserRole").equals(""))
        		lStrUserRole=session.getAttribute("UserRole").toString();*/
		if(request.getParameter("actionFlag")!=null && !request.getParameter("actionFlag").equals(""))
			lStrActionFlag=request.getParameter("actionFlag");
		if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
		{
			lStrCaseId=request.getParameter("caseId");
			request.setAttribute("caseId", lStrCaseId);        	
		}
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//String lStrCurentDtTime=preauthClinicalNotesService.getServerDate("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String lStrCurentDate = sdf2.format(date);
		request.setAttribute("lStrCurentDtTime",lStrCurentDate);
		request.setAttribute("currDt",new SimpleDateFormat("dd-MM-yyyy").format(date));
		request.setAttribute("todayDate",sdf.format(date));

		EhfCase ehfCase=null;
		EhfTelephonicRegn ehfTelephonicRegn=null;
		EhfDischargeSummary ehfDischargeSummary=null;
		if(lStrCaseId!=null && !lStrCaseId.equals(""))
			ehfCase=preauthClinicalNotesService.getCaseDetails(lStrCaseId);
		List<LabelBean> drugsList=commonService.getOpDrugs("ip");     //getDrugs();
		request.setAttribute("drugsList", drugsList);

		List<LabelBean> routeTypeLst=commonService.getRouteType("ip");     //getDrugs();
		request.setAttribute("routeTypeLst", routeTypeLst);

		List<LabelBean> strengthTypeLst=commonService.getStrengthType("ip");     //getDrugs();
		request.setAttribute("strengthTypeLst", strengthTypeLst);
		/**
		 * get user group and case Status 
		 */
		if(ehfCase!=null)
		{
			preauthCaseStatus = ehfCase.getCaseStatus();
			lStrUserGroup = config.getString("userGroup_"+preauthCaseStatus);
			
			pStrPatientId=ehfCase.getCasePatientNo();
			EhfPatient ehfPatient=preauthClinicalNotesService.getPatientDetails(pStrPatientId);
			
			if(ehfPatient!=null)
			{
				if(ehfPatient.getIntimationId()!=null && !"".equalsIgnoreCase(ehfPatient.getIntimationId()))
				{
					ehfTelephonicRegn=preauthClinicalNotesService.getTeleCaseDetails(pStrPatientId);
					preauthClinicalNotesForm.setTeleRegDate(sdf.format(ehfTelephonicRegn.getCrtDt()));
					request.setAttribute("teleRegDateTime", sdf1.format(ehfTelephonicRegn.getCrtDt()));
					preauthClinicalNotesForm.setTeleFlag("Y");
				}
			}	
		}
		String medcoGrp=null;
		for(LabelBean labelBean:groupsList)
		{
			if(labelBean.getID() != null && labelBean.getID().equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))){
				followUpRole = config.getString("EHF.ADD_CLINIC_NOTES.ROLES");
				medcoGrp=config.getString("EHF.ADD_CLINIC_NOTES.ROLES");
			}
			if(labelBean.getID() != null && lStrUserGroup!=null && lStrUserGroup.contains("~"+labelBean.getID()+"~"))
			{
				lStrUserGroup= labelBean.getID();
				break;	
			}
			else
				lStrUserGroup = null;
		}
		if(lStrUserGroup==null && medcoGrp!=null)
			lStrUserGroup=medcoGrp;
		if(groupsList == null || groupsList.isEmpty() )
		{
			lStrUserGroup = null;	
		}
		caseApprovalFlag  = request.getParameter("caseApprovalFlag");
		request.setAttribute("caseApprovalFlag", caseApprovalFlag);
		if(caseApprovalFlag != null && caseApprovalFlag.equalsIgnoreCase("N"))
		{
			lStrUserGroup = null;	
		}   	   
		if(lStrCaseId!=null && !"".equalsIgnoreCase(lStrCaseId))
		{
			ehfDischargeSummary=preauthClinicalNotesService.getDischargeSummary(lStrCaseId);
		}
		if(ehfCase!=null)
		{
			if(ehfCase.getCaseRegnDate() != null && !ehfCase.getCaseRegnDate().equals(""))
			{
				preauthClinicalNotesForm.setIpRegDate(sdf.format(ehfCase.getCaseRegnDate()));
				request.setAttribute("ipRegDateTime", sdf1.format(ehfCase.getCaseRegnDate()));
				
			}
			if(ehfCase.getCsDisDt()!=null )
			{
				request.setAttribute("DischargeRadioSel","checked");
				request.setAttribute("DischargeRadioDivStyle","block");
			}
			else
			{
				request.setAttribute("DischargeRadioSel","notchecked");
				request.setAttribute("DischargeRadioDivStyle","none");
			}
			if(ehfCase.getCsDeathDt()!=null)
			{
				request.setAttribute("DeathRadioSel","checked");
				request.setAttribute("DeathRadioDivStyle","block");
			}
			else
			{
				request.setAttribute("DeathRadioSel","notchecked");
				request.setAttribute("DeathRadioDivStyle","none");
			}
		}

		/**
		 * Initially for general pre clinical notes functionality  
		 */
		if(lStrActionFlag!=null && "clinicalNotes".equalsIgnoreCase(lStrActionFlag))
		{        	    
			LabelBean hospDtls= preauthClinicalNotesService.getHospitalDetails(lStrCaseId);
			request.setAttribute("hospType", hospDtls.getHOSPTYPE());
			request.setAttribute("hospId", hospDtls.getHOSPID());
			String caseRegDt = new SimpleDateFormat("dd/MM/yyyy").format(ehfCase.getCaseRegnDate());
            request.setAttribute("startDate", caseRegDt);
			List<CasesSearchVO> list=preauthClinicalNotesService.getDopDtls(lStrCaseId);
			if(list!=null)
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
				
					if(list.size()>0)
						{
							if(list.get(0).getCaseStatus()!=null)
								caseStatus=list.get(0).getCaseStatus();
						}
				}
			 String casesForApprovalFlag = request.getParameter("caseApprovalFlag");
			 request.setAttribute("casesForApprovalFlag", casesForApprovalFlag);
			 System.out.println(casesForApprovalFlag);
			
			List<PreauthClinicalNotesVO> clinicalNotesList=preauthClinicalNotesService.getClinicalNotesForCase(lStrCaseId,"PRE");
			preauthClinicalNotesForm.setClinicalNotesList(clinicalNotesList);
			request.setAttribute("resMsg", request.getParameter("ClinicalCount"));
			String surgFlag="";
			List<DrugsVO> drugs=preauthClinicalNotesService.getIPDrugs(lStrCaseId,"PRE");
			preauthClinicalNotesForm.setDrugsLst(drugs);
			/* To display existing 'surgery update' details.
			 *  Retrieve surgeon or doctor details if exists to display in read only while page is refreshed after submit surgeoan details
			 */
			logger.info("****caseId is : "+lStrCaseId);
			
		/*	if(ehfCase!=null)
			{*/
			if(ehfCase.getCsSurgDt()!=null)
			{
				if(!ehfCase.getCsSurgDt().equals(""))
				{
					request.setAttribute("surgViewFlag", "Y");
					surgFlag="Y";
					
					String checkDentalCond=preauthClinicalNotesService.checkDentalCase(ehfCase.getCaseId());
					if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
						{
							List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(ehfCase.getCaseId());
							preauthClinicalNotesForm.setLstPreauthVO(lstSurgyDtls);
							request.setAttribute("dentalSurg","Y");
							request.setAttribute("dentalSurgUpd","Y");
							
							if(ehfCase.getCaseStatus()!=null 
									&& ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")))
								request.setAttribute("dentalSurgUpd","N");
							
							request.setAttribute("slabAmount", preauthClinicalNotesService.getSlabAmount(ehfCase.getCaseId()));
							
							for(LabelBean labelBean:groupsList)
								{	
									if(labelBean.getID()!=null && (labelBean.getID().equalsIgnoreCase("GP5")||labelBean.getID().equalsIgnoreCase("GP8")||
											labelBean.getID().equalsIgnoreCase("GP28")||labelBean.getID().equalsIgnoreCase("GP7")||
											labelBean.getID().equalsIgnoreCase("GP9")||labelBean.getID().equalsIgnoreCase("GP93")||
											labelBean.getID().equalsIgnoreCase("GP31")||labelBean.getID().equalsIgnoreCase("GP29")||
											labelBean.getID().equalsIgnoreCase("GP31")))
												{
													request.setAttribute("priceView","Y");
													break;
												}
								}			
						}
	
					EhfCaseMedicalDtls ehfCaseMedicalDtls=preauthClinicalNotesService.getCaseMedmgntDtls(lStrCaseId);
					EhfCaseSurgicalDtls ehfCaseSurgicalDtls=preauthClinicalNotesService.getCasePacOperationDtls(lStrCaseId);
	
					if(ehfCaseMedicalDtls!=null)
					{
						preauthClinicalNotesForm.setTreatSurgeonName(ehfCaseMedicalDtls.getSurgeonName());
						preauthClinicalNotesForm.setTreatSurgeonQual(ehfCaseMedicalDtls.getSurgeonQual());
						preauthClinicalNotesForm.setTreatSurgeonRegNo(ehfCaseMedicalDtls.getSurgeonRegno());
						preauthClinicalNotesForm.setTreatSurgeonCnctNo(ehfCaseMedicalDtls.getSurgeonCntctNo());    	             
						preauthClinicalNotesForm.setTreatAsstSurName(ehfCaseMedicalDtls.getSurgAsstName());
						preauthClinicalNotesForm.setTreatAsstSurQual(ehfCaseMedicalDtls.getSurgAsstQual());
						preauthClinicalNotesForm.setTreatAsstSurRegNo(ehfCaseMedicalDtls.getSurgAsstRegno());
						preauthClinicalNotesForm.setTreatAsstSurContctNo(ehfCaseMedicalDtls.getSurgAsstCntctno());    	             
						preauthClinicalNotesForm.setTreatExpHospStay(ehfCaseMedicalDtls.getExpectdHospStay());
						preauthClinicalNotesForm.setTreatNurseName(ehfCaseMedicalDtls.getNurseName());    	             
						preauthClinicalNotesForm.setTreatParadMedicName(ehfCaseMedicalDtls.getParamedicName());
						if(ehfCaseMedicalDtls.getAdmnDt()!=null)
							preauthClinicalNotesForm.setTreatSurgStartDt(sdf.format(ehfCaseMedicalDtls.getAdmnDt()));
						if(ehfCase.getCsSurgDt()!=null)
						{
							preauthClinicalNotesForm.setSurgStartDt(sdf.format(ehfCase.getCsSurgDt()));
							preauthClinicalNotesForm.setSurgSaveDate(sdf.format(ehfCase.getCsSurgDt()));
						}
						/*preauthClinicalNotesForm.setFloor();
		    	             preauthClinicalNotesForm.setRoom();  */  	 
		    	             request.setAttribute("expHospStay1", ehfCaseMedicalDtls.getExpectdHospStay());
		    	             if(!"N".equalsIgnoreCase(casesForApprovalFlag))
		    	             {
		    	            	 lStrTrmtMedicalSubmitFlag ="Y";
		    	             }	 
		    	             lStrTrmtSurgSubmitFlag="N";
		    	             request.setAttribute("lStrTrmtMedicalSubmitFlag", lStrTrmtMedicalSubmitFlag);
					}
	
					if(ehfCaseSurgicalDtls!=null)
					{
						preauthClinicalNotesForm.setSurgeonName(ehfCaseSurgicalDtls.getSurgeonName());
						preauthClinicalNotesForm.setSurgeonQual(ehfCaseSurgicalDtls.getSurgeonQual());
						preauthClinicalNotesForm.setSurgeonRegNo(ehfCaseSurgicalDtls.getSurgeryRegno());
						preauthClinicalNotesForm.setSurgeonCnctNo(ehfCaseSurgicalDtls.getSurgeryCntctNo());    	             
						preauthClinicalNotesForm.setAsstSurName(ehfCaseSurgicalDtls.getSurgeryAsst1Name());
						preauthClinicalNotesForm.setAsstSurQual(ehfCaseSurgicalDtls.getSurgeryAsst1Qual());
						preauthClinicalNotesForm.setAsstSurRegNo(ehfCaseSurgicalDtls.getSurgeryAsst1Regno());
						preauthClinicalNotesForm.setAsstSurContctNo(ehfCaseSurgicalDtls.getSurgeryAsst1CntctNo()); 
						if(ehfCase.getCsSurgDt()!=null)
						{
							preauthClinicalNotesForm.setSurgStartDt(sdf.format(ehfCase.getCsSurgDt()));
							preauthClinicalNotesForm.setSurgSaveDate(sdf.format(ehfCase.getCsSurgDt()));
						}
						/*preauthClinicalNotesForm.setSurgStartTime();
		    	             preauthClinicalNotesForm.setSurgEndTime();*/
		    	             request.setAttribute("surgStartTime",ehfCaseSurgicalDtls.getSurgStartTime());
		    	             request.setAttribute("surgEndTime",ehfCaseSurgicalDtls.getSurgEndTime());
		    	             String[] strtTime = ehfCaseSurgicalDtls.getSurgStartTime().split(":");
		    	             String[] endTime = ehfCaseSurgicalDtls.getSurgEndTime().split(":");
		    	             request.setAttribute("lStrSurgStrHrs", strtTime[0]);
		    	             request.setAttribute("lStrSurgStrMins", strtTime[1]);
		    	             request.setAttribute("lStrSurgEndHrs", endTime[0]);
		    	             request.setAttribute("lStrSurgEndMins", endTime[1]);
		    	             preauthClinicalNotesForm.setAnesthetistName(ehfCaseSurgicalDtls.getAnaesthetistName());
		    	             preauthClinicalNotesForm.setAnesthetistRegNo(ehfCaseSurgicalDtls.getAnaesthetistRegNo());
		    	             preauthClinicalNotesForm.setAnesthetistMbNo(ehfCaseSurgicalDtls.getAnaesthetistMbNo());
		    	             preauthClinicalNotesForm.setExpHospStay(ehfCaseSurgicalDtls.getExpectedHospStay());
		    	             preauthClinicalNotesForm.setNurseName(ehfCaseSurgicalDtls.getNurseName());    	             
		    	             preauthClinicalNotesForm.setParadMedicName(ehfCaseSurgicalDtls.getParamedicName());
	
		    	             preauthClinicalNotesForm.setAnesthesiaType(ehfCaseSurgicalDtls.getAnesthesiaType());
		    	             preauthClinicalNotesForm.setIncisionType(ehfCaseSurgicalDtls.getIncisionType());
		    	             preauthClinicalNotesForm.setIntraOpPotos(ehfCaseSurgicalDtls.getOpPhotoWebEx());
		    	             preauthClinicalNotesForm.setVideoRec(ehfCaseSurgicalDtls.getVideorec());
		    	             preauthClinicalNotesForm.setSwabCount(ehfCaseSurgicalDtls.getSwabCnt());
		    	             preauthClinicalNotesForm.setSutureLigature(ehfCaseSurgicalDtls.getSurturesLigatures());
		    	             preauthClinicalNotesForm.setSpecimenRem(ehfCaseSurgicalDtls.getSpecimenRem());
		    	             preauthClinicalNotesForm.setDrainageCnt(ehfCaseSurgicalDtls.getDrainageCnt());
		    	             preauthClinicalNotesForm.setBloodLosss(ehfCaseSurgicalDtls.getBloodLoss());
		    	             preauthClinicalNotesForm.setComplications(ehfCaseSurgicalDtls.getComplications());
		    	             preauthClinicalNotesForm.setPostOperativeInst(ehfCaseSurgicalDtls.getPostOperInstru());
		    	             preauthClinicalNotesForm.setConditiOfPat(ehfCaseSurgicalDtls.getCondOfPat());
		    	             preauthClinicalNotesForm.setSpecimenName(ehfCaseSurgicalDtls.getSpecimenName());
		    	             preauthClinicalNotesForm.setComplicationsRemarks(ehfCaseSurgicalDtls.getComplicationsRemarks());
		    	             /*preauthClinicalNotesForm.setFloor();
		    	             preauthClinicalNotesForm.setRoom(); */
		    	             request.setAttribute("expHospStay1", ehfCaseSurgicalDtls.getExpectedHospStay());    	             
		    	             if(ehfCase.getCsDeathDt()!=null)
		    	            	 preauthClinicalNotesForm.setDeathDate(sdf.format(ehfCase.getCsDeathDt()));
		    	             lStrTrmtSurgSubmitFlag="N";
					}            	 	            
					request.setAttribute("TrmtSurgSubmitFlag",lStrTrmtSurgSubmitFlag);
					// if(ehfCase.getCsEnhReqDt() != null && ehfCase.getCsEnhApvDt()!=null && ehfCase.getCsDisUpdDt()==null && !"CD20".equalsIgnoreCase(ehfCase.getCaseStatus()))
					//{
					/*if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")))
		 	            		request.setAttribute("surgButFlag", "Y");
		 	            	else
		 	            	{*/
					//request.setAttribute("surgViewFlag", "N");
					request.setAttribute("surgButFlag", "N");
					//surgFlag="N";
					//}
					// }

				
				}
			}
			else
			{
				/*
				 * To display buttons in Surgery Update/ Treatment blocks
				 */ 

				if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) || lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES_NEW.ROLES"))) &&
						(ehfCase.getPreauthAprvDate()!= null && ehfCase.getPreauthRejDate()== null && ehfCase.getPreauthCancelDt()== null )	)//|| ehfCase.getPreauthRejDate()!= null
				{
					if(ehfCase.getCsEnhReqDt() == null || (ehfCase.getCsEnhReqDt() != null && (ehfCase.getCsEnhApvDt()!=null || ehfCase.getCsEnhRejDt()!=null)))
					{
						request.setAttribute("surgButFlag", "Y");
						request.setAttribute("surgViewFlag", "Y");
						surgFlag="Y";
						
						/*if(lStrTrmtMedicalSubmitFlag==null)
							{
								String checkDentalCond=preauthClinicalNotesService.checkDentalCase(ehfCase.getCaseId());
								if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
									{
										List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(ehfCase.getCaseId());
										preauthClinicalNotesForm.setLstPreauthVO(lstSurgyDtls);
										request.setAttribute("dentalSurg","Y");
										for(LabelBean labelBean:groupsList)
											{	
												if(labelBean.getID()!=null && (labelBean.getID().equalsIgnoreCase("GP5")||labelBean.getID().equalsIgnoreCase("GP8")||
														labelBean.getID().equalsIgnoreCase("GP28")||labelBean.getID().equalsIgnoreCase("GP7")||
														labelBean.getID().equalsIgnoreCase("GP9")||labelBean.getID().equalsIgnoreCase("GP93")||
														labelBean.getID().equalsIgnoreCase("GP31")||labelBean.getID().equalsIgnoreCase("GP29")||
														labelBean.getID().equalsIgnoreCase("GP31")))
															{
																request.setAttribute("priceView","Y");
																break;
															}
											}			
									}
							}*/
					}

					else 
					{
						request.setAttribute("surgButFlag", "N");
						request.setAttribute("surgViewFlag", "N");
						surgFlag="N";
					}
				}
			}
			
			
		/*}*/
			/* To display existing 'discahrge update' details */            
			if(ehfDischargeSummary!=null)
			{
				preauthClinicalNotesForm.setTreatmentGvn(ehfDischargeSummary.getTreatmentGiven());
				preauthClinicalNotesForm.setOperatveFindgs(ehfDischargeSummary.getOperativeFindings());
				preauthClinicalNotesForm.setPostOperatvePerd(ehfDischargeSummary.getPostOperPeriod());
				preauthClinicalNotesForm.setPostSplInvstgtns(ehfDischargeSummary.getPostSplInvestgtns());
				preauthClinicalNotesForm.setStatusAtDischrg(ehfDischargeSummary.getDischargeStatus());
				preauthClinicalNotesForm.setReview(ehfDischargeSummary.getReview());
				preauthClinicalNotesForm.setAdvice(ehfDischargeSummary.getAdvise());
				if(ehfCase.getCsDisDt()!=null)
					preauthClinicalNotesForm.setDisDate(sdf.format(ehfCase.getCsDisDt()));
				if(ehfDischargeSummary.getNextFollowupDt()!=null && !ehfDischargeSummary.getNextFollowupDt().equals(""))
					preauthClinicalNotesForm.setNxtFollUpDt(sdf.format(ehfDischargeSummary.getNextFollowupDt()));
				preauthClinicalNotesForm.setBlockName(ehfDischargeSummary.getBlockName());
				preauthClinicalNotesForm.setDisfloor(ehfDischargeSummary.getFloorNo());
				preauthClinicalNotesForm.setDisroomNo(ehfDischargeSummary.getRoomNo());
				if(ehfCase.getCsDeathDt()!=null)
					preauthClinicalNotesForm.setDisDeathDate(sdf.format(ehfCase.getCsDeathDt()));
				preauthClinicalNotesForm.setCauseOfDeath(ehfDischargeSummary.getCauseOfDeath());            	          	
				request.setAttribute("ehfDischargeSummarySize", "1");
			}
			else
				request.setAttribute("ehfDischargeSummarySize", "0");
			/*To disable discharge/death radio after save*/
			if(ehfCase.getCsDeathDt()!=null || ehfCase.getCsDisDt()!=null)
			{
				lStrDischargeSumryRadioFlag="N";            	
			}
			request.setAttribute("DischargeSumryRadioFlag", lStrDischargeSumryRadioFlag);
			/*To remove save and submit buttons in discharge summary after case status updation to 'DISCHARGE UPDATE'*/
			if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) && (ehfCase.getCaseStatus()!=null && ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("ehf_clinical_dischargeUpdate"))))
			{
				lStrDischargeSumryButFlag="N";
			}
			//if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) && (ehfCase.getCaseStatus()!=null && (ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")) || ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("preauth_enhancement_reject")) || ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("preauth_enhancement_recReject")) || ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("preauth_enhancement_approve")))))
			if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))
					&& ehfCase.getCsSurgUpdDt()!=null && ((ehfCase.getCsEnhReqDt()!=null && (ehfCase.getCsEnhApvDt()!=null || ehfCase.getCsEnhRejDt()!=null)) 
							|| (ehfCase.getCsEnhReqDt()==null && (ehfCase.getCaseStatus()!=null && (config.getString("ehf_clinical_surgeryUpdate").equalsIgnoreCase(ehfCase.getCaseStatus()) 
									|| config.getString("ehf_clinical_treatment_schedule_update").equalsIgnoreCase(ehfCase.getCaseStatus()))))) )

			{
				lStrMedcoRole="Y";
				request.setAttribute("medcoRole",lStrMedcoRole);

			}
			request.setAttribute("DischargeSumryButFlag", lStrDischargeSumryButFlag);
			
			if(ehfCase!=null)
				{
					if(ehfCase.getCochlearYN()!=null)
						{
							if(ehfCase.getCochlearYN().equalsIgnoreCase("Y"))
								{
									if(ehfCase.getCsSurgUpdDt()!=null)
										{
											if(ehfCase.getCaseStatus()!=null)
												{
													if(!config.getString("All.Auto.Cancelled.Status").contains(("~"+ehfCase.getCaseStatus()+"~")))
														{
															long milliSecsPerDay=24*60*60*1000;
															long daysDiff=0l,diff=0l;
															Date dateTemp=new Date();
															diff=dateTemp.getTime()-ehfCase.getCsSurgUpdDt().getTime();
															
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
																
																	lStrCochlearFollowUpFlag = preauthClinicalNotesService.getCochlearFollowUpFlag(ehfCase.getCaseId(),cochlearFollowUpCount);
																	if(lStrCochlearFollowUpFlag!=null)
																		{
																			if(lStrCochlearFollowUpFlag.equalsIgnoreCase("Y"))
																				{
																					session.setAttribute("cochlearCount",cochlearFollowUpCount);
																					request.setAttribute( "surgUpdDtforCoch",new SimpleDateFormat("dd/MM/yyyy").format(ehfCase.getCsSurgUpdDt()));
																				}
																		}
																}
														}
												}
											
										}
								}
						}
				}
			
			if(followUpRole != null && followUpRole.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) && (ehfCase.getCsDeathDt()!=null || ehfCase.getCsDisDt() != null) && !ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")))
			{
				lStrFollowupFlag = preauthClinicalNotesService.getFollowUpFlag(ehfCase.getCaseId());
				//lStrFollowupFlag="Y";
			}
			// to enable print discharge summary form button
			if(ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("EHF.Claims.DISCHARGE"))||ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("EHF.Claims.HeadPending")))
			{
				lStrDischargeSumryPrintFlag="Y";
			}
			request.setAttribute("DischargeSumryPrintFlag", lStrDischargeSumryPrintFlag);
			
			request.setAttribute("lStrFollowupFlag",lStrFollowupFlag);
			
			request.setAttribute("lStrCochlearFollowUpFlag",lStrCochlearFollowUpFlag);
			
			/*
			 * Condition to display 'Treating Doctor/Surgeon/Asst Doctor/Anesthetist' div or not based on case status and surgery date
			 */                  
			/*if(ehfCase!=null && ehfCase.getCaseStatus()!=null && !ehfCase.getCaseStatus().equals(""))
			{
				lStrCaseStatus=ehfCase.getCaseStatus().trim();            	 

				 if( (lStrCaseStatus.equalsIgnoreCase("CD81") || lStrCaseStatus.equalsIgnoreCase("CD77")  || lStrCaseStatus.equalsIgnoreCase("CD84") || lStrCaseStatus.equalsIgnoreCase("CD430") || lStrCaseStatus.equalsIgnoreCase("CD329") 	 
            			 || ehfCase.getCsSurgDt()!=null || (ehfCase.getCsSurgDt()==null && (lStrCaseStatus.equalsIgnoreCase("CD29") || lStrCaseStatus.equalsIgnoreCase("CD1802")) ) ) )
            	 {
            		 lStrSurgeonDivFlag="Y";
            	 } */ 
				/* if( lStrUserGroup != null && ( lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))  || lStrTrmtMedicalSubmitFlag != null && lStrTrmtMedicalSubmitFlag.equals("Y") || lStrTrmtSurgSubmitFlag != null && lStrTrmtSurgSubmitFlag.equals("N") )
            			||(lStrCaseStatus.equalsIgnoreCase(config.getString("ehf_preauth_approved")) ||lStrCaseStatus.equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")) || lStrCaseStatus.equalsIgnoreCase(config.getString("ehf_clinical_dischargeUpdate"))
            					 || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_reject")) || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_approve")) || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_intiated"))
            					 || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_recPending")) && ehfCase.getCsEnhReqDt() == null  	 
            			 || (ehfCase.getCsSurgDt()==null && (lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_reject")) || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_approve"))) ) )

            	 ) 
            	 {
            		 lStrSurgeonDivFlag="Y";
            	 }  */
				//String caseStatus = ","+lStrCaseStatus+",";
				/*if(lStrUserGroup != null && (ehfCase.getPreauthAprvDate()!= null || ehfCase.getPreauthRejDate()!= null ) &&
            			(( lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))  &&( lStrTrmtMedicalSubmitFlag != null && lStrTrmtMedicalSubmitFlag.equals("Y") || lStrTrmtSurgSubmitFlag != null && lStrTrmtSurgSubmitFlag.equals("N") )) || lStrTrmtMedicalSubmitFlag != null && lStrTrmtMedicalSubmitFlag.equals("Y") || lStrTrmtSurgSubmitFlag != null && lStrTrmtSurgSubmitFlag.equals("N")  && (ehfCase.getCsSurgDt()!=null ) 
            			 ) ||  (lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) && config.getString("Epreauth_enable_surgeonDtls").contains(caseStatus))) 
             	 {
             		 lStrSurgeonDivFlag="Y";
             	 } 
            	 if(ehfCase.getCsSurgDt()!=null || (ehfCase.getCsSurgDt()==null && (config.getString("EHF.CLINIC_NOTES_ENHANCEMENT.CASESTATUS1").trim().equalsIgnoreCase(lStrCaseStatus) || config.getString("EHF.CLINIC_NOTES_ENHANCEMENT.CASESTATUS2").trim().equalsIgnoreCase(lStrCaseStatus))) )
            	 {
            		 lStrSurgeonDivFlag="Y";
            	 }
            	 else if(config.getString("EHF.CLINIC_NOTES_SURGERY_UPDT.CASESTATUS")!=null && !config.getString("EHF.CLINIC_NOTES_SURGERY_UPDT.CASESTATUS").trim().equals(""))
            	 {
            		 String[] addClinicNotesCaseStatus=new String[(config.getString("EHF.CLINIC_NOTES_SURGERY_UPDT.CASESTATUS").split("~")).length];
            		 addClinicNotesCaseStatus=config.getString("EHF.CLINIC_NOTES_SURGERY_UPDT.CASESTATUS").split("~");
	   	    		  if(addClinicNotesCaseStatus!=null)
	   	    		  {
	   	    			  for(int i=0;i<addClinicNotesCaseStatus.length;i++){
	   	    				if(addClinicNotesCaseStatus[i].equalsIgnoreCase(lStrCaseStatus))
	   	    				{
	   	    					lStrSurgeonDivFlag="Y";
	   	    					break;
	   	    				}
	   	    			  }	    			  
	   	    		  }
            	 }            	 
            	 else
            		 lStrSurgeonDivFlag="N";
            	 request.setAttribute("surgeonDivFlag", lStrSurgeonDivFlag);
				//request.setAttribute("surgeonDivFlag", "Y");

			}*/	

			/*
			 * Based on medical management flag either Treating doctor or Anesthetist details div
			 */
			if(surgFlag!=null && !"".equalsIgnoreCase(surgFlag) && "Y".equalsIgnoreCase(surgFlag))
			{            	  
				lStrMedMgmtFlag=preauthClinicalNotesService.getMedMngmtFlag(lStrCaseId);
				lStrMedicalFlag=preauthClinicalNotesService.getMedicalFlag(lStrCaseId);
				// lStrMedicalFlag="Y";
				request.setAttribute("medMgmtFlag",lStrMedMgmtFlag);
				request.setAttribute("medicalFlag",lStrMedicalFlag);
			}
			/*
			 * Condition to display add clinical notes check box
			 */
			//EHF.ADD_CLINIC_NOTES.ROLES
			if(ehfCase.getCsSurgDt()==null && config.getString("EHF.ADD_CLINIC_NOTES.ROLES")!=null && lStrUserGroup != null && lStrUserGroup.trim().equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) && lstrRestrictFlag=="1")             
				lStrAddClinicNotesFlag="Y";
			else
				lStrAddClinicNotesFlag="N";
			
			
			/**
			 * check for medco role in groups list and enable add clinical notes flag
			 */
			if(caseApprovalFlag == null || caseApprovalFlag.equalsIgnoreCase("Y"))
			{
				for(LabelBean labelBean:groupsList)
				{
					if(labelBean.getID() != null && labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) && ehfCase.getCsSurgDt()==null )
					{
						lStrAddClinicNotesFlag="Y";
						break;
					}
				}
			}
			
			if(dop>0 && nonDop==0)
				lStrAddClinicNotesFlag="N";
			
			request.setAttribute("addClinicNotesFlag", lStrAddClinicNotesFlag);
			/*
			 * Condition to display post add clinical notes check box
			 */
			if(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")!=null && ehfCase.getCsSurgDt()!=null && ehfCase.getCsDisUpdDt()==null && lStrUserGroup !=null && lStrUserGroup.trim().equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) )//&& lStrCaseStatus!=null && (lStrCaseStatus.equalsIgnoreCase(config.getString("ehf_clinical_treatingScheduleUpdate")) 
				//|| lStrCaseStatus.equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")) || ))
				//|| lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_reject")) || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_recReject")) || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_approve"))))

			{
				lStrPostAddClinicNotesFlag="Y"; 
				if(dop>0 && nonDop==0)
					lStrPostAddClinicNotesFlag="N";
				request.setAttribute("addClinicNotesFlag", "N");
			}
			else
				lStrPostAddClinicNotesFlag="N";
			request.setAttribute("postAddClinicNotesFlag", lStrPostAddClinicNotesFlag);

			List<PreauthClinicalNotesVO> postClinicalList=preauthClinicalNotesService.getClinicalNotesForCase(lStrCaseId,"POST");
			if(postClinicalList!=null && postClinicalList.size()>0)
				iPostClinicCnt=postClinicalList.size();
			preauthClinicalNotesForm.setPostClinicalNotesList(postClinicalList);
			List<DrugsVO> drugs1=preauthClinicalNotesService.getIPDrugs(lStrCaseId,"POST");
			preauthClinicalNotesForm.setDrugsLstPre(drugs1);             

			/*
			 *  Condition to display discharge summary check box
			 */
			if(iPostClinicCnt>0)             
				lStrPostOPNotesFlag="YES";            	
			else
				lStrPostOPNotesFlag="NO"; 
			
			/*if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))&& (lStrCaseStatus.equalsIgnoreCase(config.getString("ehf_clinical_surgeryUpdate")) 	 || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_reject")) || lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_enhancement_recReject")) 
            	|| ehfCase.getCsDisDt()!=null ||  ehfCase.getCsDeathDt()!=null || ehfCase.getCaseStatus().equals(config.getString("preauth_enhancement_approve")) || ehfCase.getCaseStatus().equals(config.getString("preauth_enhancement_reject")))  && lStrPostOPNotesFlag.equalsIgnoreCase("YES") 
            	 )*/
			if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))
					&& ehfCase.getCsSurgUpdDt()!=null && ((ehfCase.getCsEnhReqDt()!=null && (ehfCase.getCsEnhApvDt()!=null || ehfCase.getCsEnhRejDt()!=null)) 
							|| (ehfCase.getCsEnhReqDt()==null && (ehfCase.getCaseStatus()!=null && (config.getString("ehf_clinical_surgeryUpdate").equalsIgnoreCase(ehfCase.getCaseStatus()) 
									|| config.getString("ehf_clinical_treatment_schedule_update").equalsIgnoreCase(ehfCase.getCaseStatus()))))) && lStrPostOPNotesFlag.equalsIgnoreCase("YES"))
			{
				lStrDischargeSumryFlag="Y"; 
			}
			//in case of viewing discharge summary in readonly
			else if(ehfDischargeSummary!=null)
				lStrDischargeSumryFlag="Y";
			else
				lStrDischargeSumryFlag="N";
			
			if(dop>0 && nonDop==0)
			{
				if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES"))
						&& ehfCase.getCsSurgUpdDt()!=null && ((ehfCase.getCsEnhReqDt()!=null && (ehfCase.getCsEnhApvDt()!=null || ehfCase.getCsEnhRejDt()!=null)) 
								|| (ehfCase.getCsEnhReqDt()==null && (ehfCase.getCaseStatus()!=null && (config.getString("ehf_clinical_surgeryUpdate").equalsIgnoreCase(ehfCase.getCaseStatus()) 
										|| config.getString("ehf_clinical_treatment_schedule_update").equalsIgnoreCase(ehfCase.getCaseStatus()))))))
					lStrDischargeSumryFlag="Y"; 
			}
			
			request.setAttribute("dischargeSumryFlag", lStrDischargeSumryFlag);

			//If lStrDischargeSumryFlag is 'Y' (i.e discharge summary div is enabled) then chk for post op notes count                          
			if(lStrDischargeSumryFlag=="Y" && ehfCase.getCaseId()!=null)
			{
				lPostOpNotespPendCnt=preauthClinicalNotesService.getPostOPCheckForSurgery(lStrCaseId);

				if(lPostOpNotespPendCnt>0)
					request.setAttribute("PostOpNotespPendCnt",lPostOpNotespPendCnt);
			}
			/**
			 * enable surgery details saving after enhancement approved by ctd or ch
			 */
			if(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("EHF.ADD_CLINIC_NOTES.ROLES")) && ehfCase.getCsSurgDt()!=null && !ehfCase.getCsSurgDt().equals("") && ehfCase.getCaseStatus().equals(config.getString("preauth_enhancement_approve"))  ) //|| ehfCase.getCaseStatus().equals(config.getString("preauth_enhancement_recApprove"))
			{
				enhApprovedFlag ="Y"; 
				request.setAttribute("enhApprovedFlag", enhApprovedFlag);
			}
			/**
			 * get attachments at surgery time
			 */
			List<LabelBean> lstAttchments = preauthService.getDocCount(lStrCaseId,null);
			if(lstAttchments != null && lstAttchments.size() > 0)
			{
				for(LabelBean labelBean:lstAttchments)
				{
					if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("webEx_attch")))
						request.setAttribute("webExRec",labelBean.getCOUNT());
					if(labelBean.getVALUE() != null && labelBean.getVALUE().equalsIgnoreCase(config.getString("VideoRec_atch")))
						request.setAttribute("videoRecAttach",labelBean.getCOUNT());

				}
			}
			lStrResultPage="preauthClinicalNotes";
		}

		/**
		 * To save clinical notes data
		 */
		else if(lStrActionFlag!=null && "saveClinicalNotes".equalsIgnoreCase(lStrActionFlag))
		{
			//String lStrTimeHrs=null;
			//String lStrTimeMins=null;
			String caseStartTime = sds.format(new Date().getTime());
			PreauthClinicalNotesVO preauthClinicalNotesVO=new PreauthClinicalNotesVO();          
			preauthClinicalNotesVO.setCASEID(lStrCaseId);

			if(request.getParameter("prePostFlag")!=null && !request.getParameter("prePostFlag").equals("")){            
				preauthClinicalNotesVO.setPREPOSTFLAG(request.getParameter("prePostFlag"));
			}

			List<DrugsVO> drugsList1 = new ArrayList<DrugsVO>();
			DrugsVO drugsVO = null;
			if(preauthClinicalNotesForm.getDrugs()!=null && !preauthClinicalNotesForm.getDrugs().equalsIgnoreCase("")){
				String s=preauthClinicalNotesForm.getDrugs().substring(0, preauthClinicalNotesForm.getDrugs().length()-1);
				String[] drugs=s.split("@,");
				System.out.println(s);
				for(int z=0;z<drugs.length;z++)
				{
					String drug=drugs[z];
					String[] drugValues=drug.split("~");
					drugsVO = new DrugsVO();
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

					drugsList1.add(drugsVO);
				}
			}
			preauthClinicalNotesVO.setDrugs(drugsList1);

			if(preauthClinicalNotesVO.getPREPOSTFLAG()!=null && (preauthClinicalNotesVO.getPREPOSTFLAG().equalsIgnoreCase("PRE")|| preauthClinicalNotesVO.getPREPOSTFLAG().equalsIgnoreCase("POST")))
			{
				if(request.getParameter("systolic")!=null && !request.getParameter("systolic").equals("")
						&& request.getParameter("diastolic")!=null && !request.getParameter("diastolic").equals("")){	                
					preauthClinicalNotesVO.setBLOODPRESSURE(request.getParameter("systolic")+'/'+request.getParameter("diastolic"));
				}
				if(request.getParameter("invesgtnDate")!=null && !request.getParameter("invesgtnDate").equals(""))	            
					preauthClinicalNotesVO.setINVESTIGATIONDATE(request.getParameter("invesgtnDate").replace("-", "/"));	            
				if(request.getParameter("invesgtnDate1")!=null && !request.getParameter("invesgtnDate1").equals(""))	            
					preauthClinicalNotesVO.setINVESTIGATIONDATE(request.getParameter("invesgtnDate1"));
				if(request.getParameter("pulseRate")!=null && !request.getParameter("pulseRate").equals(""))
					preauthClinicalNotesVO.setPULSE(request.getParameter("pulseRate"));	            
				if(request.getParameter("temperature")!=null && !request.getParameter("temperature").equals(""))
					preauthClinicalNotesVO.setTEMPERATURE(request.getParameter("temperature"));
				if(request.getParameter("respRate")!=null && !request.getParameter("respRate").equals(""))
					preauthClinicalNotesVO.setRESPIRATORY(request.getParameter("respRate"));	            
				if(request.getParameter("heartRate")!=null && !request.getParameter("heartRate").equals(""))
					preauthClinicalNotesVO.setHEART_RATE(request.getParameter("heartRate"));	           
				if(request.getParameter("wardType")!=null && !request.getParameter("wardType").equals(""))
					preauthClinicalNotesVO.setWARD_TYPE(request.getParameter("wardType"));
				preauthClinicalNotesVO.setREMARKS(preauthClinicalNotesForm.getRemarks());
				/*if(request.getParameter("wardInTimeHrs")!=null && !request.getParameter("wardInTimeHrs").equals("-1")
	            		&& request.getParameter("wardInTimeMins")!=null && !request.getParameter("wardInTimeMins").equals("-1"))
	            {
	            	lStrTimeHrs=request.getParameter("wardInTimeHrs");
	            	lStrTimeMins=request.getParameter("wardInTimeMins"); 
	            	if(request.getParameter("wardInTimeHrs").length()==1){
	                     lStrTimeHrs="0"+request.getParameter("wardInTimeHrs");
	                 }
	                     if(request.getParameter("wardInTimeMins").length()==1){
	                         lStrTimeMins="0"+request.getParameter("wardInTimeMins");
	                     }
	                 preauthClinicalNotesVO.setWARDINTIME(lStrTimeHrs+":"+lStrTimeMins);;
	            }
	            lStrTimeHrs=null;
	            lStrTimeMins=null;
	            if(request.getParameter("wardOutTimeHrs")!=null && !request.getParameter("wardOutTimeHrs").equals("-1")
	            		 && request.getParameter("wardOutTimeMins")!=null && !request.getParameter("wardOutTimeMins").equals("-1") )
	            {
	            	 lStrTimeHrs=request.getParameter("wardOutTimeHrs");
	            	 lStrTimeMins=request.getParameter("wardOutTimeMins"); 
	            	 if(request.getParameter("wardOutTimeHrs").length()==1){
	                     lStrTimeHrs="0"+request.getParameter("wardOutTimeHrs");
	                 }
	                     if(request.getParameter("wardOutTimeMins").length()==1){
	                         lStrTimeMins="0"+request.getParameter("wardOutTimeMins");
	                     }
	                 preauthClinicalNotesVO.setWARDOUTTIME(lStrTimeHrs+":"+lStrTimeMins);
	            } */                                  
				if(request.getParameter("lungs")!=null && !request.getParameter("lungs").equals(""))
					preauthClinicalNotesVO.setLUNGS(request.getParameter("lungs"));	            
				if(request.getParameter("fluidInput")!=null && !request.getParameter("fluidInput").equals(""))            
					preauthClinicalNotesVO.setFLUIDINPT(request.getParameter("fluidInput"));	            
				if(request.getParameter("fluidOutput")!=null && !request.getParameter("fluidOutput").equals(""))            
					preauthClinicalNotesVO.setFLUIDOUTPUT(request.getParameter("fluidOutput"));	 
				if(request.getParameter("docName")!=null && !request.getParameter("docName").equals(""))            
					preauthClinicalNotesVO.setDocName(request.getParameter("docName"));	
				if(request.getParameter("progressOfpat")!=null && !request.getParameter("progressOfpat").equals(""))            
					preauthClinicalNotesVO.setProgressOfpat(request.getParameter("progressOfpat"));	
				if(request.getParameter("plasmaBbf")!=null && !request.getParameter("plasmaBbf").equals(""))            
					preauthClinicalNotesVO.setPlasmaBbf(request.getParameter("plasmaBbf"));	
				if(request.getParameter("plasmaBl")!=null && !request.getParameter("plasmaBl").equals(""))            
					preauthClinicalNotesVO.setPlasmaBl(request.getParameter("plasmaBl"));	
				if(request.getParameter("plasmaBd")!=null && !request.getParameter("plasmaBd").equals(""))            
					preauthClinicalNotesVO.setPlasmaBd(request.getParameter("plasmaBd"));	
				if(request.getParameter("plasmaMn")!=null && !request.getParameter("plasmaMn").equals(""))            
					preauthClinicalNotesVO.setPlasmaMn(request.getParameter("plasmaMn"));	
				if(request.getParameter("insulinBbf")!=null && !request.getParameter("insulinBbf").equals(""))            
					preauthClinicalNotesVO.setInsulinBbf(request.getParameter("insulinBbf"));
				if(request.getParameter("insulinSr")!=null && !request.getParameter("insulinSr").equals(""))            
					preauthClinicalNotesVO.setInsulinSr(request.getParameter("insulinSr"));
				if(request.getParameter("insulinBd")!=null && !request.getParameter("insulinBd").equals(""))            
					preauthClinicalNotesVO.setInsulinBd(request.getParameter("insulinBd"));
				if(request.getParameter("insulinMn")!=null && !request.getParameter("insulinMn").equals(""))            
					preauthClinicalNotesVO.setInsulinMn(request.getParameter("insulinMn"));

				preauthClinicalNotesForm.setLungs("");
				preauthClinicalNotesForm.setFluidInput("");
				preauthClinicalNotesForm.setFluidOutput("");
				preauthClinicalNotesForm.setInvesgtnDate("");
				preauthClinicalNotesForm.setRemarks("");
			}

			//For post clinical notes
			else if(preauthClinicalNotesVO.getPREPOSTFLAG()!=null && preauthClinicalNotesVO.getPREPOSTFLAG().equalsIgnoreCase("POST1"))
			{
				if(request.getParameter("systolic1")!=null && !request.getParameter("systolic1").equals("")
						&& request.getParameter("diastolic1")!=null && !request.getParameter("diastolic1").equals("")){	                
					preauthClinicalNotesVO.setBLOODPRESSURE(request.getParameter("systolic1")+'/'+request.getParameter("diastolic1"));
				}	                        	            
				if(request.getParameter("pulseRate1")!=null && !request.getParameter("pulseRate1").equals(""))
					preauthClinicalNotesVO.setPULSE(request.getParameter("pulseRate1"));	            
				if(request.getParameter("temperature1")!=null && !request.getParameter("temperature1").equals(""))
					preauthClinicalNotesVO.setTEMPERATURE(request.getParameter("temperature1"));
				if(request.getParameter("respRate1")!=null && !request.getParameter("respRate1").equals(""))
					preauthClinicalNotesVO.setRESPIRATORY(request.getParameter("respRate1"));	            
				if(request.getParameter("heartRate1")!=null && !request.getParameter("heartRate1").equals(""))
					preauthClinicalNotesVO.setHEART_RATE(request.getParameter("heartRate1"));
				if(request.getParameter("wardType1")!=null && !request.getParameter("wardType1").equals(""))
					preauthClinicalNotesVO.setWARD_TYPE(request.getParameter("wardType1"));
				if(request.getParameter("lungs1")!=null && !request.getParameter("lungs1").equals(""))
					preauthClinicalNotesVO.setLUNGS(request.getParameter("lungs1"));	            
				if(request.getParameter("fluidInput1")!=null && !request.getParameter("fluidInput1").equals(""))            
					preauthClinicalNotesVO.setFLUIDINPT(request.getParameter("fluidInput1"));	            
				if(request.getParameter("fluidOutput1")!=null && !request.getParameter("fluidOutput1").equals(""))            
					preauthClinicalNotesVO.setFLUIDOUTPUT(request.getParameter("fluidOutput1"));	
				if(request.getParameter("invesgtnDate1")!=null && !request.getParameter("invesgtnDate1").equals(""))	            
					preauthClinicalNotesVO.setINVESTIGATIONDATE(request.getParameter("invesgtnDate1").replace("-", "/"));	            

				/* if(request.getParameter("wardInTimeHrs1")!=null && !request.getParameter("wardInTimeHrs1").equals("-1")
	            		&& request.getParameter("wardInTimeMins1")!=null && !request.getParameter("wardInTimeMins1").equals("-1"))
	            {
	            	lStrTimeHrs=request.getParameter("wardInTimeHrs1");
	            	lStrTimeMins=request.getParameter("wardInTimeMins1"); 
	            	if(request.getParameter("wardInTimeHrs1").length()==1){
	                     lStrTimeHrs="0"+request.getParameter("wardInTimeHrs1");
	                 }
	                     if(request.getParameter("wardInTimeMins1").length()==1){
	                         lStrTimeMins="0"+request.getParameter("wardInTimeMins1");
	                     }
	                 preauthClinicalNotesVO.setWARDINTIME(lStrTimeHrs+":"+lStrTimeMins);;
	            }
	            lStrTimeHrs=null;
	            lStrTimeMins=null;
	            if(request.getParameter("wardOutTimeHrs1")!=null && !request.getParameter("wardOutTimeHrs1").equals("-1")
	            		 && request.getParameter("wardOutTimeMins1")!=null && !request.getParameter("wardOutTimeMins1").equals("-1") )
	            {
	            	 lStrTimeHrs=request.getParameter("wardOutTimeHrs1");
	            	 lStrTimeMins=request.getParameter("wardOutTimeMins1"); 
	            	 if(request.getParameter("wardOutTimeHrs1").length()==1){
	                     lStrTimeHrs="0"+request.getParameter("wardOutTimeHrs1");
	                 }
	                     if(request.getParameter("wardOutTimeMins1").length()==1){
	                         lStrTimeMins="0"+request.getParameter("wardOutTimeMins1");
	                     }
	                 preauthClinicalNotesVO.setWARDOUTTIME(lStrTimeHrs+":"+lStrTimeMins);
	            } */
				preauthClinicalNotesVO.setREMARKS(preauthClinicalNotesForm.getRemarks1());
				if(request.getParameter("woundStatus")!=null && !request.getParameter("woundStatus").equals("")){

					preauthClinicalNotesVO.setWOUND_STATUS(request.getParameter("woundStatus"));
				}
				if(request.getParameter("woundDtls")!=null && !request.getParameter("woundDtls").equals("")){

					preauthClinicalNotesVO.setWOUND_DTLS(request.getParameter("woundDtls"));
				}
				if(request.getParameter("docName1")!=null && !request.getParameter("docName1").equals(""))            
					preauthClinicalNotesVO.setDocName(request.getParameter("docName1"));	
				if(request.getParameter("plasmaBbf1")!=null && !request.getParameter("plasmaBbf1").equals(""))            
					preauthClinicalNotesVO.setPlasmaBbf(request.getParameter("plasmaBbf1"));	
				if(request.getParameter("plasmaBl1")!=null && !request.getParameter("plasmaBl1").equals(""))            
					preauthClinicalNotesVO.setPlasmaBl(request.getParameter("plasmaBl1"));	
				if(request.getParameter("plasmaBd1")!=null && !request.getParameter("plasmaBd1").equals(""))            
					preauthClinicalNotesVO.setPlasmaBd(request.getParameter("plasmaBd1"));	
				if(request.getParameter("plasmaMn1")!=null && !request.getParameter("plasmaMn1").equals(""))            
					preauthClinicalNotesVO.setPlasmaMn(request.getParameter("plasmaMn1"));	
				if(request.getParameter("insulinBbf1")!=null && !request.getParameter("insulinBbf1").equals(""))            
					preauthClinicalNotesVO.setInsulinBbf(request.getParameter("insulinBbf1"));
				if(request.getParameter("insulinSr1")!=null && !request.getParameter("insulinSr1").equals(""))            
					preauthClinicalNotesVO.setInsulinSr(request.getParameter("insulinSr1"));
				if(request.getParameter("insulinBd1")!=null && !request.getParameter("insulinBd1").equals(""))            
					preauthClinicalNotesVO.setInsulinBd(request.getParameter("insulinBd1"));
				if(request.getParameter("insulinMn1")!=null && !request.getParameter("insulinMn1").equals(""))            
					preauthClinicalNotesVO.setInsulinMn(request.getParameter("insulinMn1"));

				preauthClinicalNotesForm.setLungs1("");
				preauthClinicalNotesForm.setFluidInput1("");
				preauthClinicalNotesForm.setFluidOutput1("");
				preauthClinicalNotesForm.setInvesgtnDate1("");
				preauthClinicalNotesForm.setRemarks1("");
			}

			/*if(request.getParameter("remarks")!=null && !request.getParameter("remarks").equals("")){              
                preauthClinicalNotesVO.setREMARKS(request.getParameter("remarks"));
            }*/
			/* if(request.getParameter("dailyDose")!=null && !request.getParameter("dailyDose").equals("")){
                preauthClinicalNotesVO.setDAILY_DOSE(request.getParameter("dailyDose"));
            }*/


			/*String lStrbloodTherapy=null;
            String lStrChemoTherapy=null;
            String lStrRadiatnTherapy=null;
            lStrTimeHrs=null;
            lStrTimeMins=null;
            if(request.getParameter("ChemoTheraphy")!=null && !request.getParameter("ChemoTheraphy").equals(""))
            {
                lStrChemoTherapy=request.getParameter("ChemoTheraphy");
                preauthClinicalNotesVO.setCHEMO_YN(lStrChemoTherapy);
                if(request.getParameter("medicines")!=null && !request.getParameter("medicines").equals("")){

                    preauthClinicalNotesVO.setMEDICINES(request.getParameter("medicines"));
                }
                if(request.getParameter("dosages")!=null && !request.getParameter("dosages").equals("")){

                    preauthClinicalNotesVO.setINSTRUCTIONS(request.getParameter("dosages"));
                }
            }
           else if(request.getParameter("BloodTheraphy")!=null && !request.getParameter("BloodTheraphy").equals(""))
           {
                lStrbloodTherapy=request.getParameter("BloodTheraphy");
                preauthClinicalNotesVO.setBLOOD_YN(lStrbloodTherapy);
            }
           else if(request.getParameter("RadiationTheraphy")!=null && !request.getParameter("RadiationTheraphy").equals(""))
           {
            lStrRadiatnTherapy=request.getParameter("RadiationTheraphy");
                preauthClinicalNotesVO.setRADIATN_YN(lStrRadiatnTherapy);
                if(preauthClinicalNotesForm.getField()!=null && !preauthClinicalNotesForm.getField().equals("")){
                    preauthClinicalNotesVO.setFIELD(preauthClinicalNotesForm.getField());
                }   
                if(preauthClinicalNotesForm.getFieldDt()!=null && !preauthClinicalNotesForm.getFieldDt().equals("")){
                    preauthClinicalNotesVO.setFSTRTDT(preauthClinicalNotesForm.getFieldDt());
                }  

                if(request.getParameter("fieldHrs")!=null && !request.getParameter("fieldHrs").equals("-1") &&
                request.getParameter("fieldMins")!=null && !request.getParameter("fieldMins").equals("-1") )
                {
                	lStrTimeHrs=request.getParameter("fieldHrs");
                	lStrTimeMins=request.getParameter("fieldMins");
                    if(request.getParameter("fieldHrs").length()==1){
                        lStrTimeHrs="0"+request.getParameter("fieldHrs");
                    }
                        if(request.getParameter("fieldMins").length()==1){
                            lStrTimeMins="0"+request.getParameter("fieldMins");
                        }
                    preauthClinicalNotesVO.setFTIME(lStrTimeHrs+":"+lStrTimeMins);
                }
                if(preauthClinicalNotesForm.getSiteFieldSize()!=null && !preauthClinicalNotesForm.getSiteFieldSize().equals("")){
                    preauthClinicalNotesVO.setSITEFIELDNAME(preauthClinicalNotesForm.getSiteFieldSize());
                }     
                if(preauthClinicalNotesForm.getDailyDose()!=null && !preauthClinicalNotesForm.getDailyDose().equals("")){
                    preauthClinicalNotesVO.setDAILY_DOSE(preauthClinicalNotesForm.getDailyDose());
                }    
                if(preauthClinicalNotesForm.getInstructions()!=null && !preauthClinicalNotesForm.getInstructions().equals("")){
                    preauthClinicalNotesVO.setINSTRUCTIONS(preauthClinicalNotesForm.getInstructions());
                } 
                if(preauthClinicalNotesForm.getAdmisteredBy()!=null && !preauthClinicalNotesForm.getAdmisteredBy().equals("")){
                    preauthClinicalNotesVO.setADMINESTEDBY(preauthClinicalNotesForm.getAdmisteredBy());
                } 
                if(request.getParameter("techChecked")!=null && !request.getParameter("techChecked").equals("")){
                    preauthClinicalNotesVO.setTEST_CHECK_YN(request.getParameter("techChecked"));
                } 
                if(request.getParameter("phyChecked")!=null && !request.getParameter("phyChecked").equals("")){
                    preauthClinicalNotesVO.setPHYSICIST_CHECK_YN(request.getParameter("phyChecked"));
                }  
                if(request.getParameter("mdChecked")!=null && !request.getParameter("mdChecked").equals("")){
                    preauthClinicalNotesVO.setMD_CHECK_YN(request.getParameter("mdChecked"));
                } 
            }
		   common fileds
            if(preauthClinicalNotesForm.getCndtnStrInf()!=null && !preauthClinicalNotesForm.getCndtnStrInf().equals("")){           
		       preauthClinicalNotesVO.setCNDNT_STR_INF(preauthClinicalNotesForm.getCndtnStrInf());
		   } 
           if(preauthClinicalNotesForm.getInfsnStrtDtC()!=null && !preauthClinicalNotesForm.getInfsnStrtDtC().equals("")  ){
              preauthClinicalNotesVO.setINF_STR_DT(preauthClinicalNotesForm.getInfsnStrtDtC());
           }
           if(preauthClinicalNotesForm.getInfsnStrtDtB()!=null && !preauthClinicalNotesForm.getInfsnStrtDtB().equals("")  ){
               preauthClinicalNotesVO.setINF_STR_DT(preauthClinicalNotesForm.getInfsnStrtDtB());
            }
           if(preauthClinicalNotesForm.getInfsnStrtDtR()!=null && !preauthClinicalNotesForm.getInfsnStrtDtR().equals("")  ){
               preauthClinicalNotesVO.setINF_STR_DT(preauthClinicalNotesForm.getInfsnStrtDtR());
            }
            if(preauthClinicalNotesForm.getInfsnEndDtB()!=null && !preauthClinicalNotesForm.getInfsnEndDtB().equals("")  ){        
                preauthClinicalNotesVO.setINF_END_DT(preauthClinicalNotesForm.getInfsnEndDtB());
            }
            if(preauthClinicalNotesForm.getInfsnEndDtR()!=null && !preauthClinicalNotesForm.getInfsnEndDtR().equals("")  ){        
                preauthClinicalNotesVO.setINF_END_DT(preauthClinicalNotesForm.getInfsnEndDtR());
            }
            if(preauthClinicalNotesForm.getInfsnEndDtC()!=null && !preauthClinicalNotesForm.getInfsnEndDtC().equals("")  ){        
                preauthClinicalNotesVO.setINF_END_DT(preauthClinicalNotesForm.getInfsnEndDtC());
            }
             lStrTimeHrs=null;
             lStrTimeMins=null;
            if(request.getParameter("infsnStrHrs")!=null && !request.getParameter("infsnStrHrs").equals("-1") &&
           request.getParameter("infsnStrMins")!=null && !request.getParameter("infsnStrMins").equals("-") )
            {
                lStrTimeHrs=request.getParameter("infsnStrHrs");
                lStrTimeMins=request.getParameter("infsnStrMins");

                if(request.getParameter("infsnStrHrs").length()==1){
                    lStrTimeHrs="0"+request.getParameter("infsnStrHrs");
                }
                    if(request.getParameter("infsnStrMins").length()==1){
                        lStrTimeMins="0"+request.getParameter("infsnStrMins");
                    }
                preauthClinicalNotesVO.setINF_STR_TIME(lStrTimeHrs+":"+lStrTimeMins);
            }
            lStrTimeHrs=null;
                lStrTimeMins=null;
            if(request.getParameter("infsnEndHrs")!=null && !request.getParameter("infsnEndHrs").equals("") &&
            request.getParameter("infsnEndMins")!=null && !request.getParameter("infsnEndMins").equals("") )
            {
                lStrTimeHrs=request.getParameter("infsnEndHrs");
                lStrTimeMins=request.getParameter("infsnEndMins");
	            if(request.getParameter("infsnEndHrs").length()==1){
	                lStrTimeHrs="0"+request.getParameter("infsnEndHrs");
	            }
                if(request.getParameter("infsnEndMins").length()==1){
                    lStrTimeMins="0"+request.getParameter("infsnEndMins");
                }

                preauthClinicalNotesVO.setINF_END_TIME(lStrTimeHrs+":"+lStrTimeMins);
            }
            if(request.getParameter("cndtnEndInf")!=null && !request.getParameter("cndtnEndInf").equals("")){
                preauthClinicalNotesVO.setCNDNT_END_INF(request.getParameter("cndtnEndInf"));
            }
            else if(request.getParameter("cndtnEndInfB")!=null && !request.getParameter("cndtnEndInfB").equals("")){
                preauthClinicalNotesVO.setCNDNT_END_INF(request.getParameter("cndtnEndInfB"));
            }
            else if(request.getParameter("cndtnEndInfR")!=null && !request.getParameter("cndtnEndInfR").equals("")){
                preauthClinicalNotesVO.setCNDNT_END_INF(request.getParameter("cndtnEndInfR"));
            }
            if(preauthClinicalNotesForm.getComments()!=null && !preauthClinicalNotesForm.getComments().equals("")){
              preauthClinicalNotesVO.setCONDTN_CMPLCTN_DESC(preauthClinicalNotesForm.getComments());
            }*/


			lStrResultMsg=preauthClinicalNotesService.saveClinicalNotes(preauthClinicalNotesVO,lStrUserId);
			String caseEndTime = sds.format(new Date().getTime());
			String actionDone = commonService.getActionDoneName(preauthClinicalNotesVO.getCASEID(),"ehfCase");
			loggingService.logTime(actionDone, preauthClinicalNotesVO.getCASEID(), caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
			preauthClinicalNotesForm.setResultMsg(lStrResultMsg);
			List<PreauthClinicalNotesVO> clinicalNotesList=preauthClinicalNotesService.getClinicalNotesForCase(lStrCaseId,"PRE");
			List<PreauthClinicalNotesVO> postClinicalList=preauthClinicalNotesService.getClinicalNotesForCase(lStrCaseId,"POST");
			preauthClinicalNotesForm.setClinicalNotesList(clinicalNotesList);
			preauthClinicalNotesForm.setPostClinicalNotesList(postClinicalList);

			lStrResultPage="preauthClinicalNotes";
		}

		/**
		 * To save surgery date and details
		 */
		else if(lStrActionFlag!=null && "saveSurgeryDate".equalsIgnoreCase(lStrActionFlag))
		{	
			String caseStartTime = sds.format(new Date().getTime());
			PreauthClinicalNotesVO preauthClinicalNotesVO=new PreauthClinicalNotesVO();
			//For attachments manadtory chk
			PreauthClinicalNotesVO preauthClinicalNotesAttachCntVO=preauthClinicalNotesService.getAttachmentsCntByType(lStrCaseId);            
			request.setAttribute("PreauthClinicalNotesAttachCntVO", preauthClinicalNotesAttachCntVO);
			Long lSurgeryPhoCnt=0L;
			Long lOpNotesAttachCnt=0L;
			/*Long lSatisFctryLttrCnt=0L;
			Long lTransprtLetterCnt=0L;
			Long lDisPhotCnt=0L;
			Long lDisSummaryDocCnt=0L;
			Long lDthCertiCnt=0L;
			Long lDthSumryAtchCnt=0L;*/
			PreauthClinicalNotesVO preauthClinicalNotesVO1=new PreauthClinicalNotesVO();
			preauthClinicalNotesVO1=preauthClinicalNotesService.getAttachmentsCntByType(lStrCaseId);            
			lSurgeryPhoCnt= preauthClinicalNotesVO1.getAFSURG_PHT_CNT();
			request.setAttribute("aftersurg",lSurgeryPhoCnt);
			lOpNotesAttachCnt= preauthClinicalNotesVO1.getOP_NOTES_CNT();
			request.setAttribute("opnotesattach",lOpNotesAttachCnt);
			/*
			lSatisFctryLttrCnt= preauthClinicalNotesVO1.getSATISF_LETTER_CNT();
			lTransprtLetterCnt= preauthClinicalNotesVO1.getTRANSPRT_LETTER_CNT();
			lDisPhotCnt= preauthClinicalNotesVO1.getDIS_PHT_CNT();
			lDisSummaryDocCnt= preauthClinicalNotesVO1.getDIS_SUMRY_DOC_CNT();
			lDthCertiCnt= preauthClinicalNotesVO1.getDEATH_CERTI_CNT();
			lDthSumryAtchCnt=preauthClinicalNotesVO1.getDEATH_SUMMRY_CNT();
			            
            jsonObject.put("lSurgeryPhoCnt",lSurgeryPhoCnt); 
            jsonObject.put("lOpNotesAttachCnt",lOpNotesAttachCnt); 
            jsonObject.put("lSatisFctryLttrCnt",lSatisFctryLttrCnt); 
            jsonObject.put("lTransprtLetterCnt",lTransprtLetterCnt); 
            jsonObject.put("lDisPhotCnt",lDisPhotCnt); 
            jsonObject.put("lDisSummaryDocCnt",lDisSummaryDocCnt); 
            jsonObject.put("lDthCertiCnt",lDthCertiCnt); 
            jsonObject.put("lDthSumryAtchCnt",lDthSumryAtchCnt);*/


			String lStrSelSurgeryDate=null;
			String lStrSelDthDt=null;            
			//String lStrSubmitType=null;


			/* var lSubmitType="normal";
                if(arr.caseStatus!='CD430'){
				lSubmitType="update"
				}
			 */ 
			/*if(request.getParameter("SubmitType")!=null && !request.getParameter("SubmitType").equals("")){
                lStrSubmitType=request.getParameter("SubmitType");
            }*/
			/*if(request.getParameter("selSurgDate")!=null && !request.getParameter("selSurgDate").equals("")){
            lStrSelSurgeryDate=request.getParameter("selSurgDate");
            }*/
			
			/**
			 * Removed  by Srikalyan for Saving The Actual treated Units for Dental Surgery at the time of 
			 * Surgery Updation --Changed to Discharge Updation Level 
			 */
			/*String dentalSurg=preauthClinicalNotesForm.getDentalSurg();
			if(dentalSurg!=null && dentalSurg.equalsIgnoreCase("Y"))
				{
					//Added to Double Check whether the Current Case is Dental 
					String checkDentalCond=preauthClinicalNotesService.checkDentalCase(ehfCase.getCaseId());
					if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
						{
							String caseUnits = preauthClinicalNotesForm.getCaseUnits();
							String toothedUnits = preauthClinicalNotesForm.getToothedUnits();
							if(caseUnits != null && toothedUnits!=null)	
								{
									preauthClinicalNotesVO.setDentCond(checkDentalCond);
									preauthClinicalNotesVO.setCaseUnits(caseUnits);
									preauthClinicalNotesVO.setToothedUnits(toothedUnits);
								}
						}
				}*/

			if(preauthClinicalNotesForm.getSurgStartDt()!=null && !preauthClinicalNotesForm.getSurgStartDt().equals(""))
				lStrSelSurgeryDate=preauthClinicalNotesForm.getSurgStartDt().replace("-", "/");                

			if(preauthClinicalNotesForm.getDeathDate()!=null && !preauthClinicalNotesForm.getDeathDate().equals(""))
			{
				lStrSelDthDt=preauthClinicalNotesForm.getDeathDate().replace("-", "/");
				preauthClinicalNotesVO.setDEATHDT(lStrSelDthDt);              
			}
			if(preauthClinicalNotesForm.getSurgeonName()!=null && !preauthClinicalNotesForm.getSurgeonName().equals("")){
				preauthClinicalNotesVO.setSURGEON_NAME(preauthClinicalNotesForm.getSurgeonName()); 
			}
			if(preauthClinicalNotesForm.getSurgeonRegNo()!=null && !preauthClinicalNotesForm.getSurgeonRegNo().equals("")){
				preauthClinicalNotesVO.setSURGEON_REGNO(preauthClinicalNotesForm.getSurgeonRegNo()); 
			}
			if(preauthClinicalNotesForm.getSurgeonQual()!=null && !preauthClinicalNotesForm.getSurgeonQual().equals("")){
				preauthClinicalNotesVO.setSURGEON_QUAL(preauthClinicalNotesForm.getSurgeonQual()); 
			}
			if(preauthClinicalNotesForm.getSurgeonCnctNo()!=null && !preauthClinicalNotesForm.getSurgeonCnctNo().equals("")){
				preauthClinicalNotesVO.setSURGEON_CNTCTNO(preauthClinicalNotesForm.getSurgeonCnctNo()); 
			}
			if(preauthClinicalNotesForm.getAnesthetistName()!=null && !preauthClinicalNotesForm.getAnesthetistName().equals("")){
				preauthClinicalNotesVO.setANESTNAME(preauthClinicalNotesForm.getAnesthetistName()); 
			}
			if(preauthClinicalNotesForm.getAnesthetistRegNo()!=null && !preauthClinicalNotesForm.getAnesthetistRegNo().equals("")){
				preauthClinicalNotesVO.setANESTREGNO(preauthClinicalNotesForm.getAnesthetistRegNo()); 
			}
			if(preauthClinicalNotesForm.getAnesthetistMbNo()!=null && !preauthClinicalNotesForm.getAnesthetistMbNo().equals("")){
				preauthClinicalNotesVO.setANESTMOBILENO(preauthClinicalNotesForm.getAnesthetistMbNo()); 
			}
			String lStrTimeHrs=null;
			String lStrTimeMins=null;
			if(request.getParameter("surgStrHrs")!=null && !request.getParameter("surgStrHrs").equals("") &&
					request.getParameter("surgStrMins")!=null && !request.getParameter("surgStrMins").equals("") ){
				lStrTimeHrs=request.getParameter("surgStrHrs");
				lStrTimeMins=request.getParameter("surgStrMins");

				if(request.getParameter("surgStrHrs").length()==1){
					lStrTimeHrs="0"+request.getParameter("surgStrHrs");
				}
				if(request.getParameter("surgStrMins").length()==1){
					lStrTimeMins="0"+request.getParameter("surgStrMins");
				}
				preauthClinicalNotesVO.setSURGSTARTTIME(lStrTimeHrs+":"+lStrTimeMins);

			}
			if(request.getParameter("surgEndHrs")!=null && !request.getParameter("surgEndHrs").equals("") &&
					request.getParameter("surgEndMins")!=null && !request.getParameter("surgEndMins").equals("") ){
				lStrTimeHrs=request.getParameter("surgEndHrs");
				lStrTimeMins=request.getParameter("surgEndMins");

				if(request.getParameter("surgEndHrs").length()==1){
					lStrTimeHrs="0"+request.getParameter("surgEndHrs");
				}
				if(request.getParameter("surgEndMins").length()==1){
					lStrTimeMins="0"+request.getParameter("surgEndMins");
				}
				preauthClinicalNotesVO.setSURGENDTIME(lStrTimeHrs+":"+lStrTimeMins);

			}
			if(preauthClinicalNotesForm.getAsstSurName()!=null && !preauthClinicalNotesForm.getAsstSurName().equals("")){
				preauthClinicalNotesVO.setASST_SURG_NAME(preauthClinicalNotesForm.getAsstSurName()); 
			}   
			if(preauthClinicalNotesForm.getAsstSurRegNo()!=null && !preauthClinicalNotesForm.getAsstSurRegNo().equals("")){
				preauthClinicalNotesVO.setASST_SURG_REGNO(preauthClinicalNotesForm.getAsstSurRegNo()); 
			}     
			if(preauthClinicalNotesForm.getAsstSurQual()!=null && !preauthClinicalNotesForm.getAsstSurQual().equals("")){
				preauthClinicalNotesVO.setASST_SURG_QUAL(preauthClinicalNotesForm.getAsstSurQual()); 
			}   
			if(preauthClinicalNotesForm.getAsstSurContctNo()!=null && !preauthClinicalNotesForm.getAsstSurContctNo().equals("")){
				preauthClinicalNotesVO.setASST_SURG_CNTCTNO(preauthClinicalNotesForm.getAsstSurContctNo()); 
			}  
			if(preauthClinicalNotesForm.getParadMedicName()!=null && !preauthClinicalNotesForm.getParadMedicName().equals("")){
				preauthClinicalNotesVO.setPARAMEDIC_NAME(preauthClinicalNotesForm.getParadMedicName()); 
			} 
			if(preauthClinicalNotesForm.getNurseName()!=null && !preauthClinicalNotesForm.getNurseName().equals("")){
				preauthClinicalNotesVO.setNURSE_NAME(preauthClinicalNotesForm.getNurseName()); 
			}  
			/*if(request.getParameter("floor")!=null && !request.getParameter("floor").equals("")){
                         preauthClinicalNotesVO.setSFLOOR(request.getParameter("floor")); 
                   }  
                if(request.getParameter("roomNo")!=null && !request.getParameter("roomNo").equals("")){
                         preauthClinicalNotesVO.setSROOM_NO(request.getParameter("roomNo")); 
                   }  */
			if(preauthClinicalNotesForm.getExpHospStay()!=null && !preauthClinicalNotesForm.getExpHospStay().equals("")){
				preauthClinicalNotesVO.setEXP_HOSP_STAY(preauthClinicalNotesForm.getExpHospStay()); 
			}  
			lStrMedMgmtFlag=preauthClinicalNotesService.getMedMngmtFlag(lStrCaseId);    
			preauthClinicalNotesVO.setMEDMGMT_YN(lStrMedMgmtFlag);                   
			preauthClinicalNotesVO.setCASEID(lStrCaseId);
			preauthClinicalNotesVO.setAnesthesiaType(preauthClinicalNotesForm.getAnesthesiaType());
			preauthClinicalNotesVO.setIncisionType(preauthClinicalNotesForm.getIncisionType());
			preauthClinicalNotesVO.setIntraOpPotos(preauthClinicalNotesForm.getIntraOpPotos());
			preauthClinicalNotesVO.setVideoRec(preauthClinicalNotesForm.getVideoRec());
			preauthClinicalNotesVO.setSwabCount(preauthClinicalNotesForm.getSwabCount());
			preauthClinicalNotesVO.setSutureLigature(preauthClinicalNotesForm.getSutureLigature());
			preauthClinicalNotesVO.setSpecimenRem(preauthClinicalNotesForm.getSpecimenRem());
			preauthClinicalNotesVO.setDrainageCnt(preauthClinicalNotesForm.getDrainageCnt());
			preauthClinicalNotesVO.setBloodLosss(preauthClinicalNotesForm.getBloodLosss());
			preauthClinicalNotesVO.setComplications(preauthClinicalNotesForm.getComplications());
			preauthClinicalNotesVO.setPostOperativeInst(preauthClinicalNotesForm.getPostOperativeInst());
			preauthClinicalNotesVO.setConditiOfPat(preauthClinicalNotesForm.getConditiOfPat());
			preauthClinicalNotesVO.setSpecimenName(preauthClinicalNotesForm.getSpecimenName());
			preauthClinicalNotesVO.setComplicationsRemarks(preauthClinicalNotesForm.getComplicationsRemarks());
			/**
			 * get Treatment schedule details.....
			 */
			 String medicalFlag = request.getParameter("medicalFlag");
			if(medicalFlag != null && medicalFlag.equalsIgnoreCase("Y"))
			{
				preauthClinicalNotesVO.setTreatAsstSurContctNo(preauthClinicalNotesForm.getTreatAsstSurContctNo());
				preauthClinicalNotesVO.setTreatAsstSurName(preauthClinicalNotesForm.getTreatAsstSurName());
				preauthClinicalNotesVO.setTreatAsstSurQual(preauthClinicalNotesForm.getTreatAsstSurQual());
				preauthClinicalNotesVO.setTreatAsstSurRegNo(preauthClinicalNotesForm.getTreatAsstSurRegNo());
				preauthClinicalNotesVO.setTreatDeathDate(preauthClinicalNotesForm.getTreatDeathDate());
				preauthClinicalNotesVO.setTreatExpHospStay(preauthClinicalNotesForm.getTreatExpHospStay());
				preauthClinicalNotesVO.setTreatNurseName(preauthClinicalNotesForm.getTreatNurseName());
				preauthClinicalNotesVO.setTreatParadMedicName(preauthClinicalNotesForm.getTreatParadMedicName());
				preauthClinicalNotesVO.setTreatSurgeonCnctNo(preauthClinicalNotesForm.getTreatSurgeonCnctNo());
				preauthClinicalNotesVO.setTreatSurgeonName(preauthClinicalNotesForm.getTreatSurgeonName());
				preauthClinicalNotesVO.setTreatSurgeonQual(preauthClinicalNotesForm.getTreatSurgeonQual());
				preauthClinicalNotesVO.setTreatSurgeonRegNo(preauthClinicalNotesForm.getTreatSurgeonRegNo());
				//preauthClinicalNotesVO.setTreatSurgStartDt(preauthClinicalNotesForm.getTreatSurgStartDt().replace("-", "/"));
				preauthClinicalNotesVO.setMedicalFlag(medicalFlag);

			}
			if(lStrSelSurgeryDate!=null)
				preauthClinicalNotesVO.setSURGERYDATE(lStrSelSurgeryDate.toString().replace("-", "/"));    
			lStrResultFlag= preauthClinicalNotesService.saveSurgeryDetails(preauthClinicalNotesVO,lStrUserGroup,lStrUserId);
			String caseEndTime = sds.format(new Date().getTime());
			String actionDone = commonService.getActionDoneName(lStrCaseId,"ehfCase");
			if(lStrResultFlag)
			{
				loggingService.logTime(actionDone, lStrCaseId, caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
				request.setAttribute("surgeryUpdateSuccess","Y");
				if(preauthClinicalNotesVO.getMEDMGMT_YN()!=null && preauthClinicalNotesVO.getMEDMGMT_YN().equalsIgnoreCase("Y"))
					preauthClinicalNotesForm.setResultMsg("Treatment Schedule Updated");
				if(preauthClinicalNotesVO.getMEDMGMT_YN()!=null && (preauthClinicalNotesVO.getMEDMGMT_YN().equalsIgnoreCase("N") || preauthClinicalNotesVO.getMEDMGMT_YN().equalsIgnoreCase("YY")))
					preauthClinicalNotesForm.setResultMsg("Treatment/Surgery Updated");            	
			}                
			lStrResultPage="preauthClinicalNotes";
		}
		/**
		 * For discharge summary
		 */
		else if(lStrActionFlag!=null && lStrActionFlag.equalsIgnoreCase("dischSumryUpdate"))
		{       
			String caseStartTime = sds.format(new Date().getTime());
			String lStrDisDeathFlag=null;                       

			PreauthClinicalNotesVO preauthClinicalNotesVO=new PreauthClinicalNotesVO();
			
			for(LabelBean labelBean:groupsList)
			{
				if(labelBean.getID() != null && labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) )
				{
					preauthClinicalNotesVO.setRole(config.getString("preauth_medco_role"));
					break;
				}
			}
			/**
			 * Added by Srikalyan for Saving The Actual treated Units for Dental Surgery at the time of 
			 * Surgery Updation 
			 */
			String dentalSurg=preauthClinicalNotesForm.getDentalSurg();
			if(dentalSurg!=null && dentalSurg.equalsIgnoreCase("Y"))
				{
					//Added to Double Check whether the Current Case is Dental 
					String checkDentalCond=preauthClinicalNotesService.checkDentalCase(ehfCase.getCaseId());
					if(checkDentalCond!=null && checkDentalCond.equalsIgnoreCase("Y"))
						{
							String caseUnits = preauthClinicalNotesForm.getCaseUnits();
							String toothedUnits = preauthClinicalNotesForm.getToothedUnits();
							if(caseUnits != null && toothedUnits!=null)	
								{
									preauthClinicalNotesVO.setDentCond(checkDentalCond);
									preauthClinicalNotesVO.setCaseUnits(caseUnits);
									preauthClinicalNotesVO.setToothedUnits(toothedUnits);
								}
						}
				}
			
			if(request.getParameter("disDeathFlag")!=null && !request.getParameter("disDeathFlag").equals("")){
				lStrDisDeathFlag=request.getParameter("disDeathFlag");
			}
			if(request.getParameter("saveSubmitFlag")!=null && !request.getParameter("saveSubmitFlag").equals("")){
				preauthClinicalNotesVO.setDIS_SAVE_SUBMIT(request.getParameter("saveSubmitFlag"));
			}
			if(preauthClinicalNotesForm.getTreatmentGvn()!=null && !preauthClinicalNotesForm.getTreatmentGvn().equals("")){
				preauthClinicalNotesVO.setTREATMNT_GVN(preauthClinicalNotesForm.getTreatmentGvn());
			}
			if(preauthClinicalNotesForm.getOperatveFindgs()!=null && !preauthClinicalNotesForm.getOperatveFindgs().equals("")){
				preauthClinicalNotesVO.setOPERATION_FNDNGS(preauthClinicalNotesForm.getOperatveFindgs());
			}
			if(preauthClinicalNotesForm.getPostOperatvePerd()!=null && !preauthClinicalNotesForm.getPostOperatvePerd().equals("")){
				preauthClinicalNotesVO.setPOST_OPERATVE_PERIOD(preauthClinicalNotesForm.getPostOperatvePerd());
			}
			if(preauthClinicalNotesForm.getPostSplInvstgtns()!=null && !preauthClinicalNotesForm.getPostSplInvstgtns().equals("")){
				preauthClinicalNotesVO.setPOST_SPL_INVSTGNS(preauthClinicalNotesForm.getPostSplInvstgtns());
			}
			if(preauthClinicalNotesForm.getStatusAtDischrg()!=null && !preauthClinicalNotesForm.getStatusAtDischrg().equals("")){
				preauthClinicalNotesVO.setSTATUS_DISCARGE(preauthClinicalNotesForm.getStatusAtDischrg());
			}
			if(preauthClinicalNotesForm.getAdvice()!=null && !preauthClinicalNotesForm.getAdvice().equals("")){
				preauthClinicalNotesVO.setADVICE(preauthClinicalNotesForm.getAdvice());
			}
			if(preauthClinicalNotesForm.getReview()!=null && !preauthClinicalNotesForm.getReview().equals("")){
				preauthClinicalNotesVO.setREVIEW(preauthClinicalNotesForm.getReview());
			}
			//if(ehfCase.getCsDisDt()==null)
			if(preauthClinicalNotesForm.getDisDate()!=null && !preauthClinicalNotesForm.getDisDate().equals("")){                    
				preauthClinicalNotesVO.setDISCHARGEDT(preauthClinicalNotesForm.getDisDate().replace("-", "/"));
			}
			//if(ehfCase.getCsDeathDt()==null)
			if(preauthClinicalNotesForm.getDisDeathDate()!=null && !preauthClinicalNotesForm.getDisDeathDate().equals("")){                    
				preauthClinicalNotesVO.setDISCHARGEDT(preauthClinicalNotesForm.getDisDeathDate().replace("-", "/"));
			}
			//if(ehfDischargeSummary!=null && ehfDischargeSummary.getNextFollowupDt()==null)
			if(preauthClinicalNotesForm.getNxtFollUpDt()!=null && !preauthClinicalNotesForm.getNxtFollUpDt().equals("")){                    
				preauthClinicalNotesVO.setNEXTFOLLOWUPDT(preauthClinicalNotesForm.getNxtFollUpDt().replace("-", "/"));
			}                
			if(preauthClinicalNotesForm.getBlockName()!=null && !preauthClinicalNotesForm.getBlockName().equals("")){                    
				preauthClinicalNotesVO.setBLOCKNAME(preauthClinicalNotesForm.getBlockName());
			}                
			if(preauthClinicalNotesForm.getDisfloor()!=null && !preauthClinicalNotesForm.getDisfloor().equals("")){                    
				preauthClinicalNotesVO.setFLOORNO(preauthClinicalNotesForm.getDisfloor());
			}
			if(preauthClinicalNotesForm.getDisroomNo()!=null && !preauthClinicalNotesForm.getDisroomNo().equals("")){                    
				preauthClinicalNotesVO.setROOMNO(preauthClinicalNotesForm.getDisroomNo());
			}
			if(preauthClinicalNotesForm.getCauseOfDeath()!=null && !preauthClinicalNotesForm.getCauseOfDeath().equals(""))
				preauthClinicalNotesVO.setCAUSE_OF_DEATH(preauthClinicalNotesForm.getCauseOfDeath());

			preauthClinicalNotesVO.setCASEID(lStrCaseId);

			lStrResultFlag=preauthClinicalNotesService.saveDischargeSummaryDtls(preauthClinicalNotesVO,lStrUserId,lStrUserGroup,lStrDisDeathFlag);
			String caseEndTime = sds.format(new Date().getTime());
			String actionDone = commonService.getActionDoneName(preauthClinicalNotesVO.getCASEID(),"ehfCase");
			if(lStrResultFlag)
			{       
				loggingService.logTime(actionDone, preauthClinicalNotesVO.getCASEID(), caseStartTime, caseEndTime, lStrUserId, "EHS_Operations");
				if(preauthClinicalNotesVO.getDIS_SAVE_SUBMIT().equalsIgnoreCase("submit"))
				{
					preauthClinicalNotesForm.setResultMsg("Discharge Updated");
					lStrDischargeUpdateSuccess="Y";//To close the window and redirect to cases search page
				}
				else
				{
					preauthClinicalNotesForm.setResultMsg("Discharge summary saved successfully");
					lStrDischargeUpdateSuccess="S";
				}                	
			}         
			else
			{
				preauthClinicalNotesForm.setResultMsg("Discharge summary failure");
				lStrDischargeUpdateSuccess="N";
			}
			request.setAttribute("dischargeUpdateSuccess", lStrDischargeUpdateSuccess);
			lStrResultPage="preauthClinicalNotes";                
		}

		/**
		 * For discharge print form
		 */
		else if(lStrActionFlag!=null && lStrActionFlag.equalsIgnoreCase("printDischargeForm"))
		{   
			LabelBean hospDtls= preauthClinicalNotesService.getHospitalDetails(lStrCaseId);
			request.setAttribute("hospDtls", hospDtls);
			LabelBean usrDtls= preauthClinicalNotesService.getUserDetails(lStrUserId);
			request.setAttribute("usrDtls", usrDtls);
			if(ehfDischargeSummary!=null)
			{
				LabelBean discharge= new LabelBean();
				discharge.setACTION(ehfDischargeSummary.getTreatmentGiven());
				discharge.setADDRESSOFESTABLISHMENT(ehfDischargeSummary.getOperativeFindings());
				discharge.setAPPRVAUTHORITY(ehfDischargeSummary.getPostOperPeriod());
				discharge.setAPPSPE(ehfDischargeSummary.getPostSplInvestgtns());
				discharge.setATTACH(ehfDischargeSummary.getDischargeStatus());
				discharge.setREMARKS(ehfDischargeSummary.getReview());
				discharge.setCOMPON(ehfDischargeSummary.getAdvise());
				if(ehfCase.getCsDisDt()!=null)
					discharge.setCOMPDESC(sdf.format(ehfCase.getCsDisDt()));
				discharge.setCOMPROLE(ehfDischargeSummary.getCauseOfDeath()); 
				request.setAttribute("discharge", discharge);
			}
			if(ehfCase!=null)
			{
				PreauthVO preauthVOPrint = preauthService.getPatientOpDtls(lStrCaseId, ehfCase.getCasePatientNo());
				preauthVOPrint.setCaseNo(ehfCase.getCaseNo());
				List<LabelBean> comorbidDtls= preauthClinicalNotesService.getComorbidDtls(ehfCase.getComorbidVals());
				request.setAttribute("comorbidDtls",comorbidDtls);
				request.setAttribute("PatientOpList",preauthVOPrint);
				request.setAttribute("claimNo",ehfCase.getClaimNo());
				request.setAttribute("scheme", ehfCase.getSchemeId());
				request.setAttribute("patientScheme", ehfCase.getPatientScheme());
				if(ehfCase.getCsSurgDt()!=null && !ehfCase.getCsSurgDt().equals(""))
				{
					EhfCaseSurgicalDtls ehfCaseSurgicalDtls=preauthClinicalNotesService.getCasePacOperationDtls(lStrCaseId);
					EhfCaseMedicalDtls ehfCaseMedicalDtls=preauthClinicalNotesService.getCaseMedmgntDtls(lStrCaseId);
					if(ehfCaseSurgicalDtls!=null)
					{	
						PreauthVO surgDtls= new PreauthVO();
						if(ehfCaseSurgicalDtls.getSurgeonName()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSurgeonName()))
							surgDtls.setSurgeryName(ehfCaseSurgicalDtls.getSurgeonName());
						else
							surgDtls.setSurgeryName("-NA-");
						if(ehfCaseSurgicalDtls.getSurgeonQual()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSurgeonQual()))
							surgDtls.setSurgeryId(ehfCaseSurgicalDtls.getSurgeonQual());
						else
							surgDtls.setSurgeryId("-NA-");
						if(ehfCaseSurgicalDtls.getSurgeryRegno()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSurgeryRegno()))
						{
							surgDtls.setRegNo(ehfCaseSurgicalDtls.getSurgeryRegno());
							String docSpec= preauthService.getDocSpeciality(ehfCaseSurgicalDtls.getSurgeryRegno());
							if(docSpec!=null && !"".equalsIgnoreCase(docSpec))
								surgDtls.setDoctorSpeciality(docSpec);
							else
								surgDtls.setDoctorSpeciality("-NA-");
						}
						else
						{
							surgDtls.setRegNo("-NA-");
							surgDtls.setDoctorSpeciality("-NA-");
						}
						if(ehfCaseSurgicalDtls.getSurgeryCntctNo()!=null && !"".equalsIgnoreCase(ehfCaseSurgicalDtls.getSurgeryCntctNo()))
							surgDtls.setContactNo(ehfCaseSurgicalDtls.getSurgeryCntctNo());    	  
						else
							surgDtls.setContactNo("-NA-");

						if(ehfCase.getCsSurgDt()!=null)
						{
							surgDtls.setSurgeryDt(sdf.format(ehfCase.getCsSurgDt()));
						}
						if(ehfCaseSurgicalDtls.getSurgStartTime()!=null)
							request.setAttribute("surgStartTime",ehfCaseSurgicalDtls.getSurgStartTime());
						else
							request.setAttribute("surgStartTime","-NA-");
						request.setAttribute("SurgDtls", surgDtls);

					}      
					if(ehfCaseMedicalDtls!=null)
					{	
						PreauthVO surgDtls= new PreauthVO();
						if(ehfCaseMedicalDtls.getSurgeonName()!=null && !"".equalsIgnoreCase(ehfCaseMedicalDtls.getSurgeonName()))
							surgDtls.setSurgeryName(ehfCaseMedicalDtls.getSurgeonName());
						else
							surgDtls.setSurgeryName("-NA-");
						if(ehfCaseMedicalDtls.getSurgeonQual()!=null && !"".equalsIgnoreCase(ehfCaseMedicalDtls.getSurgeonQual()))
							surgDtls.setSurgeryId(ehfCaseMedicalDtls.getSurgeonQual());
						else
							surgDtls.setSurgeryId("-NA-");
						if(ehfCaseMedicalDtls.getSurgeonRegno()!=null && !"".equalsIgnoreCase(ehfCaseMedicalDtls.getSurgeonRegno()))
							surgDtls.setRegNo(ehfCaseMedicalDtls.getSurgeonRegno());
						else
							surgDtls.setRegNo("-NA-");
						if(ehfCaseMedicalDtls.getSurgeonCntctNo()!=null && !"".equalsIgnoreCase(ehfCaseMedicalDtls.getSurgeonCntctNo()))
							surgDtls.setContactNo(ehfCaseMedicalDtls.getSurgeonCntctNo());    	  
						else
							surgDtls.setContactNo("-NA-");
						if(ehfCase.getCsSurgDt()!=null)
						{
							surgDtls.setSurgeryDt(sdf.format(ehfCase.getCsSurgDt()));
						}

						request.setAttribute("SurgDtls", surgDtls);

					}      
				}
				CommonDtlsVO otherDtls= preauthService.getOtherDtls(lStrCaseId,ehfCase.getCasePatientNo());
				request.setAttribute("otherDtls", otherDtls); 
				if(ehfCase.getCrtDt()!=null)
					request.setAttribute("ipDate", sdf2.format(ehfCase.getCrtDt()));
				
				if(ehfCase.getSchemeId()!=null)
					request.setAttribute("preauthCaseSchemeId", ehfCase.getSchemeId());
			}
			
			
			List<LabelBean> pastHistoryList=null;
			pastHistoryList=commonService.getPastIllnessHitory();
			request.setAttribute("pastHistoryList", pastHistoryList);
			List<LabelBean> symptomsList = new ArrayList<LabelBean>();
			symptomsList = preauthService.getSymptomsDtls(lStrCaseId);
			request.setAttribute("symptomsList",symptomsList);
			List<PreauthVO> lstSurgyDtls = preauthService.getcaseSurgertDtls(lStrCaseId);
			request.setAttribute("surgeryDtls",lstSurgyDtls);
			request.setAttribute("decFlag", request.getParameter("decFlag"));
			lStrResultPage="dischargeSummryForm";                
		}

		/**
		 * Generate Patient Satisfaction Letter
		 */
		else if(lStrActionFlag!=null && lStrActionFlag.equalsIgnoreCase("generateSatisfactionLetter"))
		{ 
			request.setAttribute("hospDtls", preauthClinicalNotesService.getHospitalDetails(lStrCaseId));
			request.setAttribute("PatientOpList",preauthService.getPatientOpDtls(lStrCaseId, ehfCase.getCasePatientNo()));
			request.setAttribute("surgeryDtls",preauthService.getcaseSurgertDtls(lStrCaseId));
			request.setAttribute("usrDtls", preauthClinicalNotesService.getUserDetails(lStrUserId));
			Long totPckgAmt = 0L;
			if(ehfCase!=null)
			{
				if(ehfCase.getPckAppvAmt()!=null)
					totPckgAmt= ehfCase.getPckAppvAmt();
				if(ehfCase.getEnhAppvAmt()!=null)
					totPckgAmt= totPckgAmt+ehfCase.getEnhAppvAmt();
				if(ehfCase.getComorbidAppvAmt()!=null)
					totPckgAmt= totPckgAmt+ehfCase.getComorbidAppvAmt();
				request.setAttribute("scheme", ehfCase.getSchemeId());
			}
			request.setAttribute("totPckgAmt", totPckgAmt);
			lStrResultPage="patSatisfactionLetter";  
		}
		if(lStrActionFlag!=null && "getOpDrugSubList".equalsIgnoreCase(lStrActionFlag))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugTypeId");        		
			}
			drugSubList=preauthClinicalNotesService.getOpDrugSubList(drugTypeId,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrResultPage="ajaxResult";
		}
		if(lStrActionFlag!=null && "getPharDrugList".equalsIgnoreCase(lStrActionFlag))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("lStrDrugSubTypeId")!=null && !request.getParameter("lStrDrugSubTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugSubTypeId");        		
			}
			drugSubList=preauthClinicalNotesService.getOpPharSubList(drugTypeId,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrResultPage="ajaxResult";
		}
		if(lStrActionFlag!=null && "getOpChemSubList".equalsIgnoreCase(lStrActionFlag))
		{
			List<String> drugSubList=null;
			String pharSubCode=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("pharSubCode")!=null && !request.getParameter("pharSubCode").equals(""))
			{
				pharSubCode=request.getParameter("pharSubCode");        		
			}
			drugSubList=preauthClinicalNotesService.getOpChemSubList(pharSubCode,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrResultPage="ajaxResult";
		}
		if(lStrActionFlag!=null && "getChemSubList".equalsIgnoreCase(lStrActionFlag))
		{
			List<String> drugSubList=null;
			String cSubGrpCode=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("cSubGrpCode")!=null && !request.getParameter("cSubGrpCode").equals(""))
			{
				cSubGrpCode=request.getParameter("cSubGrpCode");        		
			}
			drugSubList=preauthClinicalNotesService.getChemSubList(cSubGrpCode,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrResultPage="ajaxResult";
		}
		if(lStrActionFlag!=null && "getRouteList".equalsIgnoreCase(lStrActionFlag))
		{
			List<String> routeList=null;
			String routeTypeCode=null;
			if(request.getParameter("routeTypeCode")!=null && !request.getParameter("routeTypeCode").equals(""))
			{
				routeTypeCode=request.getParameter("routeTypeCode");        		
			}
			ipOpType = request.getParameter("ipOpType");
			routeList=preauthClinicalNotesService.getRouteList(routeTypeCode,ipOpType);
			if (routeList != null && routeList.size() > 0)
				request.setAttribute("AjaxMessage", routeList);
			lStrResultPage="ajaxResult";
		}
		if(lStrActionFlag!=null && "getStrengthList".equalsIgnoreCase(lStrActionFlag))
		{
			List<String> strengthList=null;
			String strengthTypeCode=null;
			if(request.getParameter("strengthTypeCode")!=null && !request.getParameter("strengthTypeCode").equals(""))
			{
				strengthTypeCode=request.getParameter("strengthTypeCode");        		
			}
			ipOpType = request.getParameter("ipOpType");
			strengthList=preauthClinicalNotesService.getStrengthList(strengthTypeCode,ipOpType);
			if (strengthList != null && strengthList.size() > 0)
				request.setAttribute("AjaxMessage", strengthList);
			lStrResultPage="ajaxResult";
		}
		return mapping.findForward(lStrResultPage);
			}
	/*
	 private static final String STRING_CLASS="java.lang.String";
	    public Object formBean(Class className,HttpServletRequest request)
	    {
	     String propName=null;
	     String propValue=null;
	     Class klass =null;
	     Object obj=null;
	    try{
	    klass =Class.forName(className.getName());
	    obj=klass.newInstance();
	    Field[] fields=klass.getDeclaredFields(); 
	    for(Field field:fields){
	        if(STRING_CLASS.equalsIgnoreCase(field.getType().getName())){
	       propName=field.getName();
	       propValue=request.getParameter(propName);
	       field.setAccessible(true);
	       field.set(obj,propValue);
	        }

	    }
	    }catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    return obj;
	    }*/
}
