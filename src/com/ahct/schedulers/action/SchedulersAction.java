package com.ahct.schedulers.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.annualCheckUp.service.AhcScheduler;
import com.ahct.schedulers.form.SchedulersForm;
import com.ahct.claims.dao.ClaimsFlowDAO;
import com.ahct.claims.dao.ClaimsGenFileDAOImpl;
import com.ahct.chronicOp.dao.chronicOpClaimsDao;
import com.ahct.annualCheckUp.dao.AhcClaimsDao;
import com.ahct.common.util.ServiceFinder;
import com.ahct.panelDoctor.dao.PanelDocPayDAO;
import com.ahct.preauth.service.AutoCancelCasesScheduler;
import com.ahct.flagging.dao.FlaggingDao;
import com.ahct.medicalAudit.service.MedicalAuditSchedular;

public class SchedulersAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
			{
				String lStrActionVal="";
				String lStrPageName="";
				String schedulerType=null;
				if(request.getParameter("type")!=null &&
						!"".equalsIgnoreCase(request.getParameter("type")))
					{
						schedulerType=request.getParameter("type");
					}
				SchedulersForm schedulersForm=(SchedulersForm) form;
				if(request.getParameter("actionFlag")!=null &&
						!"".equalsIgnoreCase(request.getParameter("actionFlag")))
					{
						lStrActionVal=request.getParameter("actionFlag");
					}
				if(lStrActionVal!=null && "openSchedulers".equalsIgnoreCase(lStrActionVal))
					{
						schedulersForm.setMsg("");
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "claimsScheduler".equalsIgnoreCase(lStrActionVal))
					{	
						
						ClaimsFlowDAO claimsFlowDAO=(ClaimsFlowDAO)ServiceFinder.getContext(request).getBean("claimsFlowDAO");
					
						if(schedulerType!=null && "generateBankFile".equalsIgnoreCase(schedulerType))
							{
								claimsFlowDAO.generateFile();
								schedulersForm.setMsg("Claims generate Bank File Scheduler ran");
							}
						else if(schedulerType!=null && "generateJrnlstBankFile".equalsIgnoreCase(schedulerType))
						{
							claimsFlowDAO.jrnlstGenerateFile();
							schedulersForm.setMsg("Journalist Claims generate Bank File Scheduler ran");
						}
						else if(schedulerType!=null && "generateErrBankFile".equalsIgnoreCase(schedulerType))
							{
								claimsFlowDAO.generateERRFile();
								schedulersForm.setMsg("Erraneous Claims generate Bank File Scheduler ran");
							}
						else if(schedulerType!=null && "generateFollowUpBankFile".equalsIgnoreCase(schedulerType))
							{
								claimsFlowDAO.generateFollowUpFile();
								schedulersForm.setMsg("Followup claims Generate Bank File Scheduler ran");
							}
						else if(schedulerType!=null && "readClaimsBankFile".equalsIgnoreCase(schedulerType))
							{
								claimsFlowDAO.updateClaimStatusSentByBank();
								schedulersForm.setMsg("Update Claim Status Sent By Bank Scheduler ran");
							}
						else if(schedulerType!=null && "surplusDeductions".equalsIgnoreCase(schedulerType))
						{
							claimsFlowDAO.updateSurplusDeductions();
							schedulersForm.setMsg("Surplus deductions Scheduler ran");						
						}
						
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "ahcScheduler".equalsIgnoreCase(lStrActionVal))
					{

						if(schedulerType!=null && "updateEnrollmentFamily".equalsIgnoreCase(schedulerType))
							{
								AhcScheduler ahcScheduler=(AhcScheduler)ServiceFinder.getContext(request).getBean("ahcCron");
								ahcScheduler.fetchAhcData();
								schedulersForm.setMsg("AHC Scheduler to Update the Enrollment Family Table ran");
							}
						else if(schedulerType!=null && "generateAHcFile".equalsIgnoreCase(schedulerType))
							{
								AhcClaimsDao ahcClaimsDao=(AhcClaimsDao)ServiceFinder.getContext(request).getBean("ahcClaimsDao");
								ahcClaimsDao.generateAHcFile();
								schedulersForm.setMsg("AHC Claims Generate Bank File Scheduler ran");
							}
						else if(schedulerType!=null && "AHCUpdateClaimStatusSentByBank".equalsIgnoreCase(schedulerType))
							{
								AhcClaimsDao ahcClaimsDao=(AhcClaimsDao)ServiceFinder.getContext(request).getBean("ahcClaimsDao");
								ahcClaimsDao.updateClaimStatusSentByBank();
								schedulersForm.setMsg("AHC Update Claim Status Sent By Bank Scheduler ran");
							}
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "chronicScheduler".equalsIgnoreCase(lStrActionVal))
					{
						chronicOpClaimsDao chronicOpClaimsDaoInst=(chronicOpClaimsDao)ServiceFinder.getContext(request).getBean("chronicClaimsDao");
					
						if(schedulerType!=null && "generateChronicFile".equalsIgnoreCase(schedulerType))
							{
								chronicOpClaimsDaoInst.generateChronicFile();
								schedulersForm.setMsg("CHRONIC Claims Generate Bank File Scheduler ran");
							}
						else if(schedulerType!=null && "chronicClaimSentStatusUpdating".equalsIgnoreCase(schedulerType))
							{
								chronicOpClaimsDaoInst.updateClaimStatusSentByBank();
								schedulersForm.setMsg("CHRONIC Update Claim Status Sent By Bank Scheduler ran");
							}
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "panelDocScheduler".equalsIgnoreCase(lStrActionVal))
					{
						PanelDocPayDAO panelDocPayDAO=(PanelDocPayDAO)ServiceFinder.getContext(request).getBean("panelDocPayDao");
					
						if(schedulerType!=null && "panelDocPmtsInit".equalsIgnoreCase(schedulerType))
							{
								panelDocPayDAO.panelDocInitialisation();
								schedulersForm.setMsg("Panel Doctor Payments Initialization Scheduler ran");
							}
						else if(schedulerType!=null && "panelDocPmts".equalsIgnoreCase(schedulerType))
							{
								panelDocPayDAO.updatePanelDocPayStatus();
								schedulersForm.setMsg("Panel Doctor Payments(Includes generating files and reading response files) Scheduler ran");
							}
						else if(schedulerType!=null && "panelDocPmtsInitOld".equalsIgnoreCase(schedulerType))
						{
							panelDocPayDAO.panelDocInitialisationOld();
							schedulersForm.setMsg("Panel Doctor Payments Initialization for Old Cases Scheduler ran");
						}
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "flaggingScheduler".equalsIgnoreCase(lStrActionVal))
					{
						FlaggingDao flaggingDao=(FlaggingDao)ServiceFinder.getContext(request).getBean("flaggingDao");
					
						if(schedulerType!=null && "flaggingMoneyCollection".equalsIgnoreCase(schedulerType))
							{
								flaggingDao.changeMoneyCollectionFlow();
								schedulersForm.setMsg("Flagging(Money Collection) Scheduler ran");
							}
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "medicalAuditScheduler".equalsIgnoreCase(lStrActionVal))
					{
						MedicalAuditSchedular medicalAuditSchedular=(MedicalAuditSchedular) ServiceFinder.getContext(request).getBean("medicalAuditCron");
					
						if(schedulerType!=null && "fetchDataDental".equalsIgnoreCase(schedulerType))
							{
								medicalAuditSchedular.fetchDataDental();
								schedulersForm.setMsg("Medical Audit-Fetch Dental Data Scheduler ran");
							}
						if(schedulerType!=null && "fetchData".equalsIgnoreCase(schedulerType))
							{
								medicalAuditSchedular.fetchData();
								schedulersForm.setMsg("Medical Audit-Fetch all data Except Dental Scheduler ran");
							}
						if(schedulerType!=null && "findCaseGroup".equalsIgnoreCase(schedulerType))
							{
								medicalAuditSchedular.findCaseGroup();
								schedulersForm.setMsg("Medical Audit-Case Group Scheduler ran");
							}
						if(schedulerType!=null && "findHighCostCase".equalsIgnoreCase(schedulerType))
							{
								medicalAuditSchedular.findHighCostCase();
								schedulersForm.setMsg("Medical Audit-High Cost Cases Scheduler ran");
							}
						if(schedulerType!=null && "findHighVolumeCase".equalsIgnoreCase(schedulerType))
							{
								medicalAuditSchedular.findHighVolumeCase();
								schedulersForm.setMsg("Medical Audit-High Volume Cases Scheduler ran");
							}
						lStrPageName="Schedulers";
					}
				if(lStrActionVal!=null && "autoCancelCasesScheduler".equalsIgnoreCase(lStrActionVal))
					{
						AutoCancelCasesScheduler autoCancelCasesScheduler=(AutoCancelCasesScheduler)ServiceFinder.getContext(request).getBean("cancelCasesCron");
						if(schedulerType!=null && "autoCancel".equalsIgnoreCase(schedulerType))
							{
								autoCancelCasesScheduler.cancelPendingCases();
								schedulersForm.setMsg("Auto Cancel Cases Scheduler ran");
							}
						lStrPageName="Schedulers";
					}
				
				if(lStrActionVal!=null  && "xmlFileGenScheduler".equalsIgnoreCase(lStrActionVal))
				{
					ClaimsGenFileDAOImpl xmlFileGen=(ClaimsGenFileDAOImpl)ServiceFinder.getContext(request).getBean("claimsGenFileDAO");
					xmlFileGen.xmlFileGeneration();
					lStrPageName="Schedulers";
				}
				return mapping.findForward(lStrPageName);
			}
	
	
}
