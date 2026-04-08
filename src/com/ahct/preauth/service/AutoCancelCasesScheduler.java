package com.ahct.preauth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.framework.configuration.ConfigurationService;
import com.ahct.claims.dao.ClaimsFlowDAOImpl;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class AutoCancelCasesScheduler {
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	static final Logger gLogger = Logger.getLogger(PreauthServiceImpl.class);
	private static final Logger logger = Logger.getLogger(PreauthServiceImpl.class);
	
	GenericDAO genericDao;
	public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }
	
	public void cancelPendingCases()
	{System.out.println("Scheduler to Auto Cancel Inactive Preauth Cases started in TS");
		logger.info("Scheduler :started");
		System.out.println("Scheduler :started");
		//Before feb 01 2016
		validatePendingPreauth("CD10",45,"CD202"); //PTD Preauth Pending
		
		validatePendingPreauth("CD210",45,"CD202"); //PPD kept Pending
		
		validatePendingPreauth("CD208",45,"CD202"); //CEO Pending
		
		validatePendingPreauth("CD217",45,"CD202"); //EO Preauth Pending
	
		
		validateSurgeryUpdatedCases("preauthAprvDate","csSurgUpdDt",30); //If not updated case from PTD approved to surg upd//Validate Surgery Updated before and  feb 01 16
		
		validateUpdatedCases("csSurgUpdDt","csDisUpdDt",60); //not updated case from surg upd to disch upd
	
		//For revised SLA's after Feb 01 2016
		validatePendingPreauthNew("CD10",30,"CD202");//PTD Preauth Pending
		
		validatePendingPreauthNew("CD210",30,"CD202");//PPD kept Pending
		
		validatePendingPreauthNew("CD208",30,"CD202");//CEO Pending
		
		validatePendingPreauthNew("CD217",30,"CD202");//EO Preauth Pending
		
		//Claims
		validateClaimPending("CD44",30,"CD202");	//CPD Pending
		
		validateClaimPending("CD47",30,"CD202");	//CTD Pending
		
		validateClaimPending("CD49",30,"CD202");	//CH Pending
		
		//For revised SLA's after Nov 01 2018
		validateClaimPendingNew("CD44",30,"CD202");	//CPD Pending After Nov 01 2018
		
		validateClaimPendingNew("CD47",30,"CD202");	//CTD Pending After Nov 01 2018
		
		validateClaimPendingNew("CD49",30,"CD202");	//CH Pending After Nov 01 2018
		
		validateClaimPendingNew("CD1351",30,"CD202");  //EO Claim Pending  After Nov 01 2018
		
		validateClaimSubmitPending("CD21",60,"CD202"); 	//Discharge updated but not claim submitted)
		logger.info("Scheduler to Auto Cancel Inactive Preauth Cases ended in TS");
		System.out.println("Scheduler to Auto Cancel Inactive Preauth Cases ended in TS");
	}
	//If Medco not updated the case from Discharge updated status to claim submitted status case will be auto cancelled
	private String validateClaimSubmitPending(String status, int days, String schemeId) 
	{
		logger.info("Start:validateClaimSubmitPending(CD21) method");
		System.out.println("Start:validateClaimSubmitPending(CD21) method");
		
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	args[0]=status;
    	args[1]=schemeId;
    	args[2]=Integer.toString(days);
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" AND A.SCHEME_ID=? AND (SYSDATE-A.LST_UPD_DT)>? ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		for(int k=0;k<cnt;k++)
    	{
    		if(k==0){
    			query.append(" ( ? ");
    			
    		}
    		else if(k==cnt-1)
    		{
    			query.append(" , ? ) ");
    		}
    		else
    		{
    			query.append(" , ? ");
    		}
    		
    	}
		query.append(" AND ect.activeyn = ?)");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE(?,'DD/MM/YYYY')");
		//Added for exemption of NABH Cases
		query.append(" AND A.CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP EE  WHERE ((TRUNC(SYSDATE)-TRUNC(CRT_DT))- (NO_OF_DAYS)) <=0 ) ");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		


		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    EhfmWorkflowStatus ehfmWorkflowStatus = null;
	    String pStrCaseStatus=status;
	    String pStrUserRole=config.getString("auto_cancel_role");
	    String lStrActionDone="CD1300";
	    
	    	
	    try
	    {
		    
			//criteriaList.removeAll(criteriaList);
				for(LabelBean revisedCases:newCasesList)
				{
					try
					{
						logger.info("Loop:inside validateClaimSubmitPending(CD21) loop");
						System.out.println("Loop:inside validateClaimSubmitPending(CD21) loop");
						finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
						logger.info("Loop:After findbyid");
						String newCaseStatus=null;
						if(config.getString("PreauthPendingStatuses").contains(("~"+status+"~")))
						{
							newCaseStatus=config.getString("PendingCancelStatus");
						}
						else
						{
							newCaseStatus = getNextStatus(pStrCaseStatus, pStrUserRole,lStrActionDone);
						}
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						logger.info("Loop:newCaseStatus"+newCaseStatus);
						if(finalCase!=null)
						{
							logger.info("in finalcase");
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							logger.info("saved in ehfcase");
							if(finalCase!=null)
							{
								logger.info("after saved in ehfcase");
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been reverted by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been reverted by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));		
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								ehfAudit.setApprvAmt(0.0);
								genericDao.save(ehfAudit);
								logger.info("saved in ehfaudit");
							}
						}
						logger.info("After save ehfaudit and ehfcase :inside validateClaimSubmitPending(CD21) ehfaudit and ehfcase ");
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
	    }
	   
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
	    
		return null;
	
		
	}
	
	private String validateClaimPending(String status, int days, String schemeId) 
	{
		
		logger.info("Start:validateClaimPending(CD44,CD47,CD49) method");
		System.out.println("Start:validateClaimPending(CD44,CD47,CD49) method");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS='"+status+"'");
		query.append(" AND A.SCHEME_ID='"+schemeId+"' AND (SYSDATE-A.LST_UPD_DT)>30");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		query.append(" ('20.96','N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','99.23.1','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y')");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE('01/02/2016','DD/MM/YYYY') AND TRUNC(B.REG_HOSP_DATE)<TO_DATE('01/11/2018','DD/MM/YYYY')  ");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		


		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    EhfmWorkflowStatus ehfmWorkflowStatus = null;
	    String pStrCaseStatus=status;
	    String pStrUserRole=config.getString("auto_cancel_role");
	    String lStrActionDone="CD1299";
	    
	    	
	    try
	    {
		    
			//criteriaList.removeAll(criteriaList);
				for(LabelBean revisedCases:newCasesList)
				{
					try
					{
						logger.info("Loop:inside validateClaimPending(CD44,CD47,CD49) loop");
						System.out.println("Loop:validateClaimPending(CD44,CD47,CD49) loop");
						
						finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
						String newCaseStatus=null;
						if(config.getString("PreauthPendingStatuses").contains(("~"+status+"~")))
						{
							newCaseStatus=config.getString("PendingCancelStatus");
						}
						else
						{
							newCaseStatus = getNextStatus(pStrCaseStatus, pStrUserRole,lStrActionDone);
						}
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						if(finalCase!=null)
						{
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							//finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							if(finalCase!=null)
							{
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been reverted by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been reverted by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								ehfAudit.setApprvAmt(0.0);
								genericDao.save(ehfAudit);
							}
						}
						logger.info("After save ehfaudit and ehfcase :inside validateClaimPending(CD44,CD47,CD49) ehfaudit and ehfcase ");
						System.out.println("After save ehfaudit and ehfcase :inside validateClaimPending(CD44,CD47,CD49) ehfaudit and ehfcase ");
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validateclaimpending of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
	    }
	   
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validateclaimpending of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
	    
		return null;
	
		
	}
		//If NWH not updated the case from claim pending status to claim pending pending updated status
		//case gets autocancelled by trust  inactivity of 30days (CD616,CD617,CD618,CD619)
		private String validateClaimPendingNew(String status, int days, String schemeId) 
		{
			logger.info("Start:validateClaimPendingNew(CD44,CD47,CD49,CD1351) method");
			System.out.println("Start:validateClaimPendingNew(CD44,CD47,CD49,CD1351) method");
			// TODO Auto-generated method stub
			List<LabelBean> newCasesList = null;
			try{
			StringBuffer query = new StringBuffer();
			query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
			query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS='"+status+"'");
			query.append(" AND A.SCHEME_ID='"+schemeId+"' AND (SYSDATE-A.LST_UPD_DT)>30");
			query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
			query.append(" ('20.96','N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','99.23.1','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y')");
			query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE('01/11/2018','DD/MM/YYYY')");
			
			newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			


			Date dateWithoutTime= new Date();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR , -days);
			cal.set(Calendar.HOUR_OF_DAY, 0);
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    cal.set(Calendar.MILLISECOND, 0);
		    dateWithoutTime = cal.getTime();
		    
		    Date date= Calendar.getInstance().getTime();
		    EhfCase finalCase=null;
		    EhfmWorkflowStatus ehfmWorkflowStatus = null;
		    String pStrCaseStatus=status;
		    String pStrUserRole=config.getString("auto_cancel_role");
		    String lStrActionDone="CD1300";
		    
		    	
		    try
		    {
			    
				//criteriaList.removeAll(criteriaList);
					for(LabelBean revisedCases:newCasesList)
					{
						try
						{
							logger.info("Loop:inside validateClaimPendingNew(CD44,CD47,CD49,CD1351) loop");
							System.out.println("Loop:validateClaimPendingNew(CD44,CD47,CD49,CD1351) loop");
							
							finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
							String newCaseStatus=null;
							if(config.getString("PreauthPendingStatuses").contains(("~"+status+"~")))
							{
								newCaseStatus=config.getString("PendingCancelStatus");
							}
							else
							{
								newCaseStatus = getNextStatus(pStrCaseStatus, pStrUserRole,lStrActionDone);
							}
							if(newCaseStatus==null)
								newCaseStatus=config.getString("auto_cancelled_status");
							if(finalCase!=null)
							{
								EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
								String msg="";
								if(ehfmCmbDtls!=null)
									{
										if(ehfmCmbDtls.getCmbDtlName()!=null)
											msg=ehfmCmbDtls.getCmbDtlName();
									}
								finalCase.setCaseStatus(newCaseStatus);
								//finalCase.setPreauthCancelDt(date);
								finalCase.setLstUpdDt(date);
								finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
								finalCase= genericDao.save(finalCase);
								if(finalCase!=null)
								{
									Long actOrder = null;
									
									if(getMaxActOrder(finalCase.getCaseId())!=null)
									{
									actOrder = getMaxActOrder(finalCase.getCaseId())+1;
									}
									else{
									actOrder = 1L;
									}
									EhfAudit ehfAudit = new EhfAudit();
									ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
									ehfAudit.setActId(newCaseStatus);
									ehfAudit.setActBy(config.getString("auto_cancel_actor"));
									if(msg!=null)
										ehfAudit.setRemarks("As per Note order Dated 01.11.2018,this Case has been cancelled by Trust on end of "+days+" days of inactivity in " +msg+" status");
									else
										ehfAudit.setRemarks("As per Note order Dated 01.11.2018,this Case has been cancelled by Trust on end of "+days+" days of inactivity");
									ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
									ehfAudit.setCrtDt(date);
									ehfAudit.setLangId("en_US");
									ehfAudit.setUserRole(config.getString("auto_cancel_role"));
									ehfAudit.setApprvAmt(0.0);
									genericDao.save(ehfAudit);
								}
							}
							logger.info("After save ehfaudit and ehfcase :inside validateClaimPendingNew(CD44,CD47,CD49,CD1351) ehfaudit and ehfcase ");
							System.out.println("After save ehfaudit and ehfcase :inside validateClaimPendingNew(CD44,CD47,CD49,CD1351) ehfaudit and ehfcase ");
						}
						catch(Exception e)
						{
							gLogger.error("Error in For loop in method validateclaimpendingNew of class ValidateCaseImpl.java--> "+ e.getMessage());
							break;
						}
					}
		    }
		   
		    catch(Exception e)
			{
		    	gLogger.error("Error in method validateclaimpendingNew of class ValidateCaseImpl.java--> "+ e.getMessage());
				
			}
		    
			return null;
		
			
		}
	private String validatePendingPreauthNew(String status, int days, String schemeId) 
	{
		logger.info("Start:inside validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 ");
		System.out.println("Start:inside validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 ");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	args[0]=status;
    	args[1]=schemeId;
    	args[2]=Integer.toString(days);
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" AND A.SCHEME_ID=? AND (SYSDATE-A.LST_UPD_DT)>? ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		for(int k=0;k<cnt;k++)
    	{
    		if(k==0){
    			query.append(" ( ? ");
    			
    		}
    		else if(k==cnt-1)
    		{
    			query.append(" , ? ) ");
    		}
    		else
    		{
    			query.append(" , ? ");
    		}
    		
    	}
		query.append(" AND ect.activeyn = ?)");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE(?,'DD/MM/YYYY')");
		//Added for exemption of NABH Cases
		query.append(" AND A.CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP EE  WHERE ((TRUNC(SYSDATE)-TRUNC(CRT_DT))- (NO_OF_DAYS)) <=0 ) ");
				
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		


		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    EhfmWorkflowStatus ehfmWorkflowStatus = null;
	    String pStrCaseStatus=status;
	    String pStrUserRole=config.getString("auto_cancel_role");
	    String lStrActionDone="CD1300";
	    
	    	
	    try
	    {
		    
			//criteriaList.removeAll(criteriaList);
				for(LabelBean revisedCases:newCasesList)
				{
					try
					{
						logger.info("Loop:inside validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 loop");
						System.out.println("Loop:validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 loop");
						 
						finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
						String newCaseStatus=null;
						if(config.getString("PreauthPendingStatuses").contains(("~"+status+"~")))
						{
							newCaseStatus=config.getString("PendingCancelStatus");
						}
						else
						{
							newCaseStatus = getNextStatus(pStrCaseStatus, pStrUserRole,lStrActionDone);
						}
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						if(finalCase!=null)
						{
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							if(finalCase!=null)
							{
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been Cancelled by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been Cancelled by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								ehfAudit.setApprvAmt(0.0);
								genericDao.save(ehfAudit);
							}
						}
						logger.info("After save: validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 ");
						System.out.println("After SAve validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 ");
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
	    }
	   
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
	    
		return null;
	
		
	}
	
	
	private String validatePendingPreauth(String status, int days,String schemeId)
	{
		logger.info("Start: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
		System.out.println("Start: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	args[0]=status;
    	args[1]=schemeId;
    	args[2]=Integer.toString(days);
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" AND A.SCHEME_ID=? AND (SYSDATE-A.LST_UPD_DT)>?");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		for(int k=0;k<cnt;k++)
    	{
    		if(k==0){
    			query.append(" ( ? ");
    			
    		}
    		else if(k==cnt-1)
    		{
    			query.append(" , ? ) ");
    		}
    		else
    		{
    			query.append(" , ? ");
    		}
    		
    	}
		query.append(" AND ect.activeyn = ?)");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)<TO_DATE(?,'DD/MM/YYYY')");
		//Added for exemption of NABH Cases
		query.append(" AND A.CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP EE  WHERE ((TRUNC(SYSDATE)-TRUNC(CRT_DT))- (NO_OF_DAYS)) <=0 ) ");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    EhfmWorkflowStatus ehfmWorkflowStatus = null;
	    String pStrCaseStatus=status;
	    String pStrUserRole=config.getString("auto_cancel_role");
	    String lStrActionDone="CD1300";
	    
	    try
	    {
		    /*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		    criteriaList.add(new GenericDAOQueryCriteria("caseStatus",status, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		    criteriaList.add(new GenericDAOQueryCriteria("lstUpdDt",dateWithoutTime, GenericDAOQueryCriteria.CriteriaOperator.LT ));
		    criteriaList.add(new GenericDAOQueryCriteria("schemeId",schemeId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		    List<EhfCase> ehfCase= genericDao.findAllByCriteria(EhfCase.class, criteriaList);*/
			//criteriaList.removeAll(criteriaList);
	    	for(LabelBean revisedCases:newCasesList)
			{
					try
					{
						logger.info("Loop:inside validatePendingPreauthNew(CD10,CD210,CD217,CD208) before feb01 2016 loop");
						System.out.println("Loop:validatePendingPreauthNew(CD10,CD210,CD217,CD208) before feb01 2016 loop");
						
						finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
						String newCaseStatus=null;
						if(config.getString("PreauthPendingStatuses").contains(("~"+status+"~")))
						{
							newCaseStatus=config.getString("PendingCancelStatus");
						}
						else
						{
							newCaseStatus = getNextStatus(pStrCaseStatus, pStrUserRole,lStrActionDone);
						}
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						if(finalCase!=null)
						{
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							if(finalCase!=null)
							{
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been Cancelled by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 02.12.2015,this Case has been Cancelled by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								ehfAudit.setApprvAmt(0.0);
								genericDao.save(ehfAudit);
							}
						}
						logger.info("After Save: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
						System.out.println("After Save: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
					
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
	    }
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
		return null;
	}
	
	private String getNextStatus(String pStrCaseStatus, String pStrUserRole,String lStrActionDone) 
	{
		// TODO Auto-generated method stub
		String lStrNextStatus = null;
		try {

			EhfmWorkflowStatus ehfmWorkflowStatus = null;
			ehfmWorkflowStatus = genericDao.findById(EhfmWorkflowStatus.class,
					EhfmWorkflowStatusId.class, new EhfmWorkflowStatusId(
							pStrCaseStatus, pStrUserRole, lStrActionDone));
			if (ehfmWorkflowStatus != null)
				lStrNextStatus = ehfmWorkflowStatus.getNextStatus();
		} catch (Exception e) {
			e.printStackTrace();
//			gLogger.error("Error occured in getNextStatus()."
//					+ e.getMessage());
		}

		return lStrNextStatus;

	}
	//If not updated case from PTD approved to  Surgery Updated before and  feb 01 16 & cancelled status is CD614
	private String validateSurgeryUpdatedCases(String columnName1, String columnName2, int days)
	{
		logger.info("Start: validateSurgeryUpdatedCases(preauthApprvDt) before and after feb01 2016 ");
		System.out.println("Start: validateSurgeryUpdatedCases(preauthApprvDt) before feb01 2016 ");
		
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		
		String[] args=new String[5];
		args[0]="CD202";
		args[1]="CD8";
		args[2]="01/02/2016";
		args[3]="01/02/2016";
		args[4]="IPM";
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CS_SURG_DT IS NULL ");
		query.append(" AND A.SCHEME_ID=? AND A.PREAUTH_CANCEL_DT IS NULL AND A.CASE_STATUS=? and "); 
		query.append("  (case when trunc(b.reg_hosp_date) >= to_date(?, 'DD/MM/YYYY') and (sysdate - (a.preauth_aprv_dt)) > 30 then 1 ");
		query.append(" when trunc(b.reg_hosp_date) < to_date(?, 'DD/MM/YYYY') and (sysdate - (a.preauth_aprv_dt)) > 60 then 1 else 0  end) = 1 ");
		query.append(" and (case when B.Patient_Ipop = ? and A.Enh_Amount > 200000 then 0 else 1 end) = 1 ");
		//Added for exemption of NABH Cases
		query.append(" AND A.CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP EE  WHERE ((TRUNC(SYSDATE)-TRUNC(CRT_DT))- (NO_OF_DAYS)) <=0 ) ");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    
	    try
	    {
		   
	    	for(LabelBean revisedCases:newCasesList)
				{
					try
					{
						logger.info("Loop:inside validateSurgeryUpdatedCases(preauthApprvDt)  loop");
						System.out.println("Loop:validateSurgeryUpdatedCases(preauthApprvDt) loop");
						
						finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
						
						//Start To Check Special Procs Only when Surgery Updated and Discharge not Updated
						if(columnName1.equalsIgnoreCase("csSurgUpdDt") && columnName2.equalsIgnoreCase("csDisUpdDt") &&
								finalCase.getSchemeId().equalsIgnoreCase(config.getString("TG")))
							{
								String validateSpecProcs=checkTherapyCond(finalCase,"Days");
								
								if(validateSpecProcs!=null && !"".equalsIgnoreCase(validateSpecProcs))
									{
									if(validateSpecProcs.equalsIgnoreCase("false"))
									{
										continue;
									}
									List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
										
										cal = Calendar.getInstance();
										cal.add(Calendar.DAY_OF_YEAR , -Integer.parseInt(validateSpecProcs));
										cal.set(Calendar.HOUR_OF_DAY, 0);
									    cal.set(Calendar.MINUTE, 0);
									    cal.set(Calendar.SECOND, 0);
									    cal.set(Calendar.MILLISECOND, 0);
										
										dateWithoutTime = cal.getTime();
										criteriaList.add(new GenericDAOQueryCriteria("caseId",finalCase.getCaseId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName1,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NOT_NULL ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName1,dateWithoutTime, GenericDAOQueryCriteria.CriteriaOperator.LT ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName2,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
									    criteriaList.add(new GenericDAOQueryCriteria("preauthCancelDt",null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
									    List<EhfCase> newCaseList= genericDao.findAllByCriteria(EhfCase.class, criteriaList);
									    if(newCaseList==null)
									    	continue;
									    else if(newCaseList.size()==0)
									    	continue;
									}		
							}
						else if(columnName1.equalsIgnoreCase("preauthAprvDate") && columnName2.equalsIgnoreCase("csSurgUpdDt") &&
								finalCase.getSchemeId().equalsIgnoreCase(config.getString("TG")))
							{
								String validateSpecProcs=checkTherapyCond(finalCase,"Cancel");
								
								if(validateSpecProcs!=null && !"".equalsIgnoreCase(validateSpecProcs))
									{
										if(validateSpecProcs.equalsIgnoreCase("false"))
										{
											continue;
										}
									}
							}
						//End To Check Special Procs Only when Surgery Updated and Discharge not Updated
						
						String newCaseStatus=null;
						if(config.getString("PreauthApprStatuses").contains(("~"+finalCase.getCaseStatus()+"~")))
							newCaseStatus=config.getString("ApprovedCancelStatus");
						else if(config.getString("SurgyStatuses").contains(("~"+finalCase.getCaseStatus()+"~")))
							newCaseStatus=config.getString("SurgeryUpdCancelStatus");
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						
						if(finalCase!=null)
						{
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							if(finalCase!=null)
							{
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 19.04.2014,this Case has been Cancelled by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 19.04.2014,this Case has been Cancelled by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								ehfAudit.setApprvAmt(0.0);
								genericDao.save(ehfAudit);
							}
						}
						logger.info("After SAve : validateSurgeryUpdatedCases before and after feb01 2016 ");
						System.out.println("After Save : validateSurgeryUpdatedCases before feb01 2016 ");
						
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
			
	    }
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
		return null;
	}
	
	private String validateUpdatedCases(String columnName1, String columnName2, int days)
	{
		logger.info("Start: validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before and after feb01 2016 ");
		System.out.println("Start : validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before feb01 2016 ");
		
		//String[] cases={ "TG240897","TG188947","TG201385","TG195919","TG192696","TG220846","TG212591","TG212573","TG212610","TG223567","TG228641","TG228607","TG223053","TG250667","TG245745","TG242882","TG243158","TG243438","TG242849","TG243140","TG242182","TG242696","TG244828","TG244026","TG250712","TG245629","TG243220","TG239402","TG243172","TG242862","TG243175","TG243206","TG241791","TG243014","TG261945","TG260011","TG260835","TG242913","TG255622","TG261800","TG255618","TG259572","TG257867","TG258670"};
		 //List<String> ExempCaseList = Arrays.asList( cases );
		 
		 List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		
//		query.append(" SELECT CASE_ID AS CASEID FROM  (select case_id, cs_Surg_Upd_Dt ");
//		query.append(" from ehf_case where cs_Dis_Upd_Dt is null and cs_Surg_Upd_Dt is not null  ");
//		query.append(" and preauth_cancel_dt is null and scheme_id = 'CD202') "); 
//		query.append(" where (sysdate - cs_Surg_Upd_Dt) > 60 ");
		
		String[] args = new String[3];
		args[0]="CD202";
		args[1]="CD20";
		args[2]="IPM";
		
		query.append(" SELECT CASE_ID AS CASEID FROM (select A.case_id, A.cs_Surg_Upd_Dt ");
		query.append(" from ehf_case A, ehf_patient B where A.case_patient_no=B.patient_id and A.cs_Dis_Upd_Dt is null ");
		query.append(" and A.cs_Surg_Upd_Dt is not null and A.preauth_cancel_dt is null and A.scheme_id = ?  and A.case_status=? ");
		query.append(" and (case when B.Patient_Ipop = ? and A.Enh_Amount > 200000 then 0 else 1 end) = 1) ");
		query.append(" where (sysdate - cs_Surg_Upd_Dt) > 60 ");
		//Added for exemption of NABH Cases
		query.append(" AND CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP EE  WHERE ((TRUNC(SYSDATE)-TRUNC(CRT_DT))- (NO_OF_DAYS)) <=0 ) ");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		 
		 
		 
		 
		// TODO Auto-generated method stub
		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    
	    try
	    {
		    /*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		    criteriaList.add(new GenericDAOQueryCriteria(columnName1,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NOT_NULL ));
		    criteriaList.add(new GenericDAOQueryCriteria(columnName1,dateWithoutTime, GenericDAOQueryCriteria.CriteriaOperator.LT ));
		    criteriaList.add(new GenericDAOQueryCriteria(columnName2,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
		    criteriaList.add(new GenericDAOQueryCriteria("preauthCancelDt",null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
		    criteriaList.add(new GenericDAOQueryCriteria("schemeId",config.getString("TG"), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		    criteriaList.add(new GenericDAOQueryCriteria(finalCase.getCaseId(),ExempCaseList,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
		    List<EhfCase> ehfCase= genericDao.findAllByCriteria(EhfCase.class, criteriaList);*/
			//criteriaList.removeAll(criteriaList);
			
			
				for(LabelBean revisedCases:newCasesList)
				{
					try
					{
						logger.info("Loop:inside validateUpdatedCases(csSurgUpdDt,csDisUpdDt)  loop");
						System.out.println("Loop:validateUpdatedCases(csSurgUpdDt,csDisUpdDt) loop");
						
						
						finalCase= genericDao.findById(EhfCase.class, String.class, revisedCases.getCASEID());
						
						//Start To Check Special Procs Only when Surgery Updated and Discharge not Updated
						if(columnName1.equalsIgnoreCase("csSurgUpdDt") && columnName2.equalsIgnoreCase("csDisUpdDt") &&
								finalCase.getSchemeId().equalsIgnoreCase(config.getString("TG")))
							{
								String validateSpecProcs=checkTherapyCond(finalCase,"Days");
								
								if(validateSpecProcs!=null && !"".equalsIgnoreCase(validateSpecProcs))
									{
									if(validateSpecProcs.equalsIgnoreCase("false"))
									{
										continue;
									}
									List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
										
										cal = Calendar.getInstance();
										cal.add(Calendar.DAY_OF_YEAR , -Integer.parseInt(validateSpecProcs));
										cal.set(Calendar.HOUR_OF_DAY, 0);
									    cal.set(Calendar.MINUTE, 0);
									    cal.set(Calendar.SECOND, 0);
									    cal.set(Calendar.MILLISECOND, 0);
										
										dateWithoutTime = cal.getTime();
										criteriaList.add(new GenericDAOQueryCriteria("caseId",finalCase.getCaseId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName1,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NOT_NULL ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName1,dateWithoutTime, GenericDAOQueryCriteria.CriteriaOperator.LT ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName2,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
									    criteriaList.add(new GenericDAOQueryCriteria("preauthCancelDt",null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
									    List<EhfCase> newCaseList= genericDao.findAllByCriteria(EhfCase.class, criteriaList);
									    if(newCaseList==null)
									    	continue;
									    else if(newCaseList.size()==0)
									    	continue;
									}		
							}
						else if(columnName1.equalsIgnoreCase("preauthAprvDate") && columnName2.equalsIgnoreCase("csSurgUpdDt") &&
								finalCase.getSchemeId().equalsIgnoreCase(config.getString("TG")))
							{
								String validateSpecProcs=checkTherapyCond(finalCase,"Cancel");
								
								if(validateSpecProcs!=null && !"".equalsIgnoreCase(validateSpecProcs))
									{
										if(validateSpecProcs.equalsIgnoreCase("false"))
										{
											continue;
										}
									}
							}
						//End To Check Special Procs Only when Surgery Updated and Discharge not Updated
						
						String newCaseStatus=null;
						if(config.getString("PreauthApprStatuses").contains(("~"+finalCase.getCaseStatus()+"~")))
							newCaseStatus=config.getString("ApprovedCancelStatus");
						else if(config.getString("SurgyStatuses").contains(("~"+finalCase.getCaseStatus()+"~")))
							newCaseStatus=config.getString("SurgeryUpdCancelStatus");
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						
						if(finalCase!=null)
						{
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							if(finalCase!=null)
							{
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 19.04.2014,this Case has been Cancelled by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 19.04.2014,this Case has been Cancelled by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								ehfAudit.setApprvAmt(0.0);
								genericDao.save(ehfAudit);
							}
						}
						logger.info("Start: validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before and after feb01 2016 ");
						System.out.println("Start : validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before feb01 2016 ");
						
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
			
	    }
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
		return null;
	}

	private Long getMaxActOrder(String caseId)
	{
		Long actOrder = (long) 0;
		try
		{
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		    criteriaList.add(new GenericDAOQueryCriteria("id.caseId",caseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		    actOrder= genericDao.getMaxByPropertyCriteria(EhfAudit.class, "id.actOrder",criteriaList);
			
		}
		catch(Exception e)
		{
			gLogger.error("Error in method getMaxActOrder of class ValidateCaseImpl.java--> "+ e.getMessage());
		}
		return actOrder;
	}
	private String checkTherapyCond(EhfCase ehfcase ,String arg)
		{
			String returnCond=null;
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			if(ehfcase!=null)
				{
					criteriaList.removeAll(criteriaList);
					if(ehfcase.getCaseId()!=null)
						{
							String[] specProcs=config.getString("Cancel.Special.Proc").split("~");
							String[] specProcsDays=config.getString("Cancel.Special.ProcDays").split("~");
							
							criteriaList.add(new GenericDAOQueryCriteria("caseId",ehfcase.getCaseId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
							criteriaList.add(new GenericDAOQueryCriteria("activeyn","Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
						    List<EhfCaseTherapy> ehfCaseTherapyList= genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
						    for(EhfCaseTherapy locTherapy: ehfCaseTherapyList)
						    	{
						    		if(locTherapy.getIcdProcCode()!=null && !"".equalsIgnoreCase(locTherapy.getIcdProcCode()))
						    			{
						    			if("'20.96','N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','99.23.1','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3','S13.1.8','S13.1.7','S13.1.9','S13.1.6','S13.1.10','S13.1.11','S13.1.2','S13.1.1','S13.1.3','S13.1.5','S13.1.4'".contains(locTherapy.getIcdProcCode()))
						    			{
						    				return "false";
						    			}
						    			if(arg.equalsIgnoreCase("days"))
						    			{
						    				for (int i=0;i<specProcs.length;i++)
						    					{
						    						/*
						    						 * Matches the corressponding Proc Code and takes Corressponding
						    						 * conditional Days saved in Config.
						    						 */
						    						if(locTherapy.getIcdProcCode().equalsIgnoreCase(specProcs[i]))
						    							{	
						    								returnCond=specProcsDays[i];
						    								break;
						    							}
						    					}
						    			}
						    			}
						    	}
						}
						
				}
			return returnCond;
		}
}
