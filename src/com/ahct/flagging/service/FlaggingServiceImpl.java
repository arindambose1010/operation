package com.ahct.flagging.service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;

import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.dao.FlaggingDao;
import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.ahct.preauth.service.PreauthServiceImpl;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class FlaggingServiceImpl implements FlaggingService
{
	FlaggingDao flaggingDao;
	String caseId;
	String flagId;
	String flagDocId;
	List<LabelBean> flagList=new ArrayList<LabelBean>();
	List<FlaggingVO> hospList=new ArrayList<FlaggingVO>();
	List<FlaggingVO> flaggingVOLst;
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	static final Logger gLogger = Logger.getLogger(FlaggingServiceImpl.class);
	private static final Logger logger = Logger.getLogger(PreauthServiceImpl.class);
	
	GenericDAO genericDao;
	public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }
	public FlaggingDao getFlaggingDao() {
		return flaggingDao;
	}

	public void setFlaggingDao(FlaggingDao flaggingDao) {
		this.flaggingDao = flaggingDao;
	}
	/*
	 * Implementation
	 * to get flags
	 * for Interface service*/
	@Override
	public List<LabelBean> getFlagList()
		{
			flagList=flaggingDao.getFlagList();
			return flagList;
		}

	/*
	 *Implementation
	 *to get
	 *the districts */
	public List<FlaggingVO> getDistricts(String stateId)
		{
			List<FlaggingVO> distList=flaggingDao.getDistricts(stateId);
			return distList;
		}
	
	/*
	 * Implementation
	 * to get districts
	 * for Interface service*/
	@Override
	public List<FlaggingVO> getHospList(String stateId,String distId,String hospType)
		{
			hospList=flaggingDao.getHospList(stateId,distId,hospType);
			return hospList;
		}
	
	/*
	 * Implementation 
	 * to save the 
	 * Flag Details*/
	@Override
	public String saveFlagDtls(FlaggingVO flaggingVO)
		{
			String msg=flaggingDao.saveFlagDtls(flaggingVO);
			return msg;
		}
	
	/*
	 * Implementation
	 * to retrieve
	 * Flag Details*/
	@Override
	public List<FlaggingVO> getFlaggedCases(FlaggingVO flaggingVO)
		{
			flaggingVOLst=flaggingDao.getFlaggedCases(flaggingVO);
			return flaggingVOLst;
		}
	
	
	/*
	 * Implementation
	 * to get the
	 * FlagAttachments */
	@Override
	public List<FlaggingVO> getflagAttach(String caseId,String flagId,String flagDocId)
		{
		this.caseId=caseId;
		this.flagId=flagId;
		this.flagDocId=flagDocId;
		
		flaggingVOLst=flaggingDao.getflagAttach(caseId,flagId,flagDocId);
		return flaggingVOLst;
		}
	
	/*
	 * Implementation
	 * to get all
	 * Flagged Cases */
	@Override
	public List<FlaggingVO> getAllFlaggedCases(FlaggingVO flaggingVO)
		{
			flaggingVOLst=flaggingDao.getAllFlaggedCases(flaggingVO);
			return flaggingVOLst;
		}
	
	/*
	 * Function
	 * to get 
	 * Authority*/
	@Override
	public FlaggingVO checkAuthority(List<LabelBean> rolesList)
		{
			FlaggingVO flaggingVO=flaggingDao.checkAuthority(rolesList);
			return flaggingVO;
		}
	/*
	 * Function
	 * to get 
	 * Non Delagged Cases*/
	@Override
	public Number getNoOfFlaggedCases(String lStrEmpId)
		{
			Number flaggedCases=flaggingDao.getNoOfFlaggedCases(lStrEmpId);
			return flaggedCases;
		}
	/*
	 * Function
	 * to get 
	 * Flagged Cases for color*/
	@Override
	public FlaggingVO getFlaggedCasesForColor(String caseId)
		{
			FlaggingVO flaggingVO=flaggingDao.getFlaggedCasesForColor(caseId);
			return flaggingVO;
		}
	
	@Override
	public String getFlagDocId()
	{
		String id=flaggingDao.getFlagDocId();
		return id;
	}
	
	
	/*
	 * Function
	 * to check 
	 * Case is Flagged*/
	@Override
	public FlaggingVO checkCaseFlagged(String caseId)
		{
			FlaggingVO flaggingVO=flaggingDao.checkCaseFlagged(caseId);
			return flaggingVO;
		}
	
	/*
	 * Function to check the  
	 * authority for DC DM TL */
	@SuppressWarnings("unchecked")
	@Override
	public FlaggingVO checkDCTLDMAuthority(FlaggingVO flaggingVO)
		{
			FlaggingVO returnVo =new FlaggingVO();
			
			if(flaggingVO.getDcId()!=null || flaggingVO.getDmId()!=null ||
					flaggingVO.getTlId()!=null)
				{
					StringBuffer query=new StringBuffer();
					query.append( " select eht.hospId as hospId , eht.hospDist as hospDist " );
					query.append( " from EhfmHospTlDcMpg eht " );
					
					if(flaggingVO.getDcId()!=null && !"".equalsIgnoreCase(flaggingVO.getDcId()))
						query.append(" where eht.dcId = '"+flaggingVO.getDcId()+"' ");
					else if(flaggingVO.getDmId()!=null && !"".equalsIgnoreCase(flaggingVO.getDmId()))
						query.append(" where eht.dmId = '"+flaggingVO.getDmId()+"' ");
					else if(flaggingVO.getTlId()!=null && !"".equalsIgnoreCase(flaggingVO.getTlId()))
						query.append(" where eht.tlId = '"+flaggingVO.getTlId()+"' ");
						
					Map<String,Object> returnMap=new HashMap<String,Object>();
					String path="com.ahct.flagging.vo.FlaggingVO";
					returnMap=flaggingDao.executeNormalHQLQuery(query.toString(),path);
					if(returnMap!=null)
						{
							if(returnMap.get(path)!=null)
								returnVo.setFlaggingVOLst((List<FlaggingVO>)returnMap.get(path));
						}
					
					
				}
			
			return returnVo;
			
				
		}
	
	/*
	 * Function to get Case Details
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FlaggingVO getCaseDtls(String caseId)
		{
			String hospId=null;
			FlaggingVO returnVO = new FlaggingVO(); 
			StringBuffer query = new StringBuffer();
			try
				{
					query.append( " select a.caseHospCode as hospId , a.schemeId as schemeId from EhfCase a " );
					query.append( " where a.caseId ='"+caseId+"' " );
					
					Map<String,Object> returnMap=new HashMap<String,Object>();
					String path="com.ahct.flagging.vo.FlaggingVO";
					returnMap=flaggingDao.executeNormalHQLQuery(query.toString(),path);
					if(returnMap!=null)
						{
							if(returnMap.get(path)!=null)
								{
									List<FlaggingVO> lst=(List<FlaggingVO>)returnMap.get(path);
									if(lst!=null)
										if(lst.size()>0)
											if(lst.get(0)!=null)
												{
													if(lst.get(0).getHospId()!=null)
														hospId = lst.get(0).getHospId();
													if(lst.get(0).getSchemeId()!=null)
														returnVO.setSchemeId(lst.get(0).getSchemeId());
												}	
								}
						}
					returnVO.setHospId(hospId);
				}
			catch(Exception e )
				{
					e.printStackTrace();
				}
			return returnVO;
		}
	
	/*
	 * Function to get auto cancelled Case Details by Satish Kola
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<LabelBean> getCancelledCases(String userHospid)
		{
		
		
		   List<LabelBean> allCanceledCases = new ArrayList<LabelBean>();
		   
		   List<LabelBean> validatePendingPreauth1 = validatePendingPreauth("CD10",42,"CD202",userHospid); //PTD Preauth Pending
		   if (!validatePendingPreauth1.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth1);
		   }
			
		   List<LabelBean> validatePendingPreauth2 = validatePendingPreauth("CD210",42,"CD202",userHospid); //PPD kept Pending
		   if (!validatePendingPreauth2.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth2);
		   }
			
		   List<LabelBean> validatePendingPreauth3 = validatePendingPreauth("CD208",42,"CD202",userHospid); //CEO Pending
		   if (!validatePendingPreauth3.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth3);
		   }
			
		   List<LabelBean> validatePendingPreauth4 = validatePendingPreauth("CD217",42,"CD202",userHospid); //EO Preauth Pending
		   if (!validatePendingPreauth4.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth4);
		   }
		
			
		   List<LabelBean> validateSurgeryUpdatedCases = validateSurgeryUpdatedCases("preauthAprvDate","csSurgUpdDt",57,userHospid); //Validate Surgery Updated before and  feb 01 16
		   if (!validateSurgeryUpdatedCases.isEmpty()) {
			   allCanceledCases.addAll(validateSurgeryUpdatedCases);
		   }
		   List<LabelBean> validateUnDischargedCasesLst = validateUnDischargedCases("preauthAprvDate","csSurgUpdDt",57,userHospid); //Validate Surgery Updated before and  feb 01 16
		   if (!validateUnDischargedCasesLst.isEmpty()) {
			   allCanceledCases.addAll(validateUnDischargedCasesLst);
		   }
			/*validateUpdatedCases("csSurgUpdDt","csDisUpdDt",60); //Validate Discharge Updated*/
		
			//For revised SLA's after Feb 01 2016
		   List<LabelBean> validatePendingPreauthNew1 = validatePendingPreauthNew("CD10",27,"CD202",userHospid);//PTD Preauth Pending
		   if (!validatePendingPreauthNew1.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew1);
		   }
			
		   List<LabelBean> validatePendingPreauthNew2 = validatePendingPreauthNew("CD210",27,"CD202",userHospid);//PPD kept Pending
		   if (!validatePendingPreauthNew2.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew2);
		   }
			
		   List<LabelBean> validatePendingPreauthNew3 = validatePendingPreauthNew("CD208",27,"CD202",userHospid);//CEO Pending
		   if (!validatePendingPreauthNew3.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew3);
		   }
			
		   List<LabelBean> validatePendingPreauthNew4 = validatePendingPreauthNew("CD217",27,"CD202",userHospid);//EO Preauth Pending
		   if (!validatePendingPreauthNew4.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew4);
		   }
			
			//Claims
		   List<LabelBean> validateClaimPending1 = validateClaimPending("CD44",27,"CD202",userHospid);	//CPD Pending
		   if (!validateClaimPending1.isEmpty()) {
			   allCanceledCases.addAll(validateClaimPending1);
		   }
		   
		   List<LabelBean> validateClaimPending2 = validateClaimPending("CD47",27,"CD202",userHospid);	//CTD Pending
		   if (!validateClaimPending2.isEmpty()) {
			   allCanceledCases.addAll(validateClaimPending2);
		   }
		   
		   List<LabelBean> validateClaimPending3 = validateClaimPending("CD49",27,"CD202",userHospid);	//CH Pending
		   if (!validateClaimPending3.isEmpty()) {
			   allCanceledCases.addAll(validateClaimPending3);
		   }
		   
		   List<LabelBean> validateClaimSubmitPending = validateClaimSubmitPending("CD21",57,"CD202",userHospid); 	//Discharge updated(claim submission)	
		   if (!validateClaimSubmitPending.isEmpty()) {
			   allCanceledCases.addAll(validateClaimSubmitPending);
		   }
		   if(allCanceledCases.size()>0)
			return allCanceledCases;
		   else return null;
		}
	private List<LabelBean> validateClaimSubmitPending(String status, int days, String schemeId,String userHospid) 
	{
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
    	args[1]=userHospid;
    	args[2]=schemeId;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
    	
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" and A.case_hosp_code = ? ");
		query.append(" AND A.SCHEME_ID=? AND TRUNC(SYSDATE-A.LST_UPD_DT) between 57 and 60 ");
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
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		String arguments="";
		for(int i=0; i<args.length;i++)
		{
			arguments=arguments+"~"+args[i];
		}
		gLogger.info("About to expire validateClaimSubmitPending Query" +query.toString()+" "+arguments);
		if(newCasesList != null && newCasesList.size()>0)
		{
			for(int i=0;i<newCasesList.size();i++)
			{
				gLogger.info("About to expire validateClaimSubmitPending Cases List" +newCasesList.get(i).getCASEID());
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newCasesList;
	
	}
	
	private List<LabelBean> validateClaimPending(String status, int days, String schemeId,String userHospid) 
	{
		System.out.println("Start:validateClaimPending(CD44,CD47,CD49) method");
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
    	args[1]=userHospid;
    	args[2]=schemeId;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
		
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" and A.case_hosp_code = ? ");
		query.append(" AND A.SCHEME_ID=? AND TRUNC(SYSDATE-A.LST_UPD_DT) between 27 and 30 ");
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
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		String arguments="";
		for(int i=0; i<args.length;i++)
		{
			arguments=arguments+"~"+args[i];
		}
		gLogger.info("About to expire validateClaimPending Query" +query.toString()+" "+arguments);
		if(newCasesList != null && newCasesList.size()>0)
		{
			for(int i=0;i<newCasesList.size();i++)
			{
				gLogger.info("About to expire validateClaimPending Cases List" +newCasesList.get(i).getCASEID());
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return newCasesList;
	
		
	}
	
	private List<LabelBean> validatePendingPreauthNew(String status, int days, String schemeId,String userHospid) 
	{
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
    	args[1]=userHospid;
    	args[2]=schemeId;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
    	
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" and A.case_hosp_code = ? ");
		query.append(" AND A.SCHEME_ID=? AND TRUNC(SYSDATE-A.LST_UPD_DT) between 27 and 30 ");
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
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		String arguments="";
		for(int i=0; i<args.length;i++)
		{
			arguments=arguments+"~"+args[i];
		}
		gLogger.info("About to expire validatePendingPreauthNew Query" +query.toString()+" "+arguments);
		if(newCasesList != null && newCasesList.size()>0)
		{
			for(int i=0;i<newCasesList.size();i++)
			{
				gLogger.info("About to expire validatePendingPreauthNew Cases List" +newCasesList.get(i).getCASEID());
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    
		return newCasesList;
	
		
	}
	private List<LabelBean> validatePendingPreauth(String status, int days,String schemeId,String userHospid)
	{
		System.out.println("Start: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = new ArrayList<LabelBean>();
		try{
		StringBuffer query = new StringBuffer();

		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	args[0]=status;
    	args[1]=userHospid;
    	args[2]=schemeId;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
    	
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE  FROM EHF_CASE A , EHF_PATIENT B");

		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" and A.case_hosp_code = ? ");
		query.append(" AND A.SCHEME_ID=? AND TRUNC(SYSDATE-A.LST_UPD_DT) between 42 and 45 ");
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
	
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		String arguments="";
		for(int i=0; i<args.length;i++)
		{
			arguments=arguments+"~"+args[i];
		}
		gLogger.info("About to expire validatePendingPreauth Query" +query.toString()+" "+arguments);
			if(newCasesList != null && newCasesList.size()>0)
			{
				for(int i=0;i<newCasesList.size();i++)
				{
					gLogger.info("About to expire validatePendingPreauth Cases List" +newCasesList.get(i).getCASEID());
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return newCasesList;
	}
	
	private List<LabelBean> validateUnDischargedCases(String columnName1, String columnName2, int days,String userHospid)
		{
			System.out.println("Start: validateSurgeryUpdatedCases(preauthApprvDt) before feb01 2016 ");
			
			List<LabelBean> newCasesList = null;
			try{
			StringBuffer query = new StringBuffer();
			
			String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
			String testarr[]=test.split("~");
			int cnt=testarr.length;
			
			String[] args=new String[cnt+5];
			int totCnt=args.length;
	    	
	    	args[0]="CD202";
	    	args[1]=userHospid;
	    	for(int k=0;k<cnt;k++)
	    	{
	    		args[k+2]=testarr[k];
	    	}
	    	args[totCnt-3]="Y";
	    	args[totCnt-2]="01/02/2016";
	    	args[totCnt-1]="01/02/2016";
			
			query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B");
			query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CS_DIS_UPD_DT IS NULL ");
			query.append(" AND A.SCHEME_ID=? AND A.PREAUTH_CANCEL_DT IS NULL AND A.CASE_STATUS='CD20' ");
			query.append(" and A.case_hosp_code = ? AND ");
			query.append(" A.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
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
			query.append(" and (case when trunc(b.reg_hosp_date) >= to_date(?, 'DD/MM/YYYY') and (sysdate - trunc(a.CS_SURG_UPD_DT)) between 27 and 30 then 1 ");
			query.append(" when trunc(b.reg_hosp_date) < to_date(?, 'DD/MM/YYYY') and (sysdate - trunc(a.CS_SURG_UPD_DT)) between 57 and 60 then 1 else 0  end) = 1 ");
		
			newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
			String arguments="";
			for(int i=0; i<args.length;i++)
			{
				arguments=arguments+"~"+args[i];
			}
			gLogger.info("About to expire validateUnDischargedCases Query" +query.toString()+" "+arguments);
			if(newCasesList != null && newCasesList.size()>0)
			{
				for(int i=0;i<newCasesList.size();i++)
				{
					gLogger.info("About to expire validateUnDischargedCases Cases List" +newCasesList.get(i).getCASEID());
				}
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			return newCasesList;
		}
	private List<LabelBean> validateSurgeryUpdatedCases(String columnName1, String columnName2, int days,String userHospid)
	{
		System.out.println("Start: validateSurgeryUpdatedCases(preauthApprvDt) before feb01 2016 ");
		
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		
		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	
    	args[0]="CD202";
    	args[1]=userHospid;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+2]=testarr[k];
    	}
    	args[totCnt-3]="Y";
    	args[totCnt-2]="01/02/2016";
    	args[totCnt-1]="01/02/2016";
    	
		
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CS_SURG_DT IS NULL ");
		query.append(" AND A.SCHEME_ID=? AND A.PREAUTH_CANCEL_DT IS NULL AND A.CASE_STATUS='CD8' ");
		query.append(" and A.case_hosp_code = ? AND ");
		query.append(" A.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
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
		query.append(" and (case when trunc(b.reg_hosp_date) >= to_date(?, 'DD/MM/YYYY') and (sysdate - trunc(a.preauth_aprv_dt)) between 27 and 30 then 1 ");
		query.append(" when trunc(b.reg_hosp_date) < to_date(?, 'DD/MM/YYYY') and (sysdate - trunc(a.preauth_aprv_dt)) between 57 and 60 then 1 else 0  end) = 1 ");
	
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		String arguments="";
		for(int i=0; i<args.length;i++)
		{
			arguments=arguments+"~"+args[i];
		}
		gLogger.info("About to expire validateSurgeryUpdatedCases Query" +query.toString()+" "+arguments);
		if(newCasesList != null && newCasesList.size()>0)
		{
			for(int i=0;i<newCasesList.size();i++)
			{
				gLogger.info("About to expire validateSurgeryUpdatedCases Cases List" +newCasesList.get(i).getCASEID());
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return newCasesList;
	}
	@Override
	public List<FlaggingVO> getDistrictsNew(String stateId, String patStateType)
	{
		List<FlaggingVO> distList=flaggingDao.getDistrictsNew(stateId,patStateType);
		return distList;
	}
}
