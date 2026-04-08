package com.ahct.medicalAudit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfMedicalAuditHighVolumeCases;
import com.ahct.model.EhfMedicalAuditHighVolumeCasesId;
import com.ahct.model.EhfMedicalAuditSampleCases;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class MedicalAuditSchedular {
	private final static Logger LOGGER = Logger.getLogger(MedicalAuditSchedular.class);  
	GenericDAO genericDao ;
	
	public GenericDAO getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	/**
     * ;
     * @function This method is used to fetch raw data required for performing medical audit.Only Claim Paid,No Death and Last 3 months cases are considered.
     */

	@Scheduled(cron="00 00 02 03 Dec,Sep,Mar,jan * ")
	public void fetchDataDental()
	{
	int count=0;
	try
	{
		StringBuffer query = new StringBuffer();
		List<LabelBean> ehfCaseLst= new ArrayList<LabelBean>();
		
		query.append("select ec.caseId as CASEID, ec.caseNo as CASENO, ec.claimNo as CLAIMNO, ");
		query.append(" ec.casePatientNo as PATIENTID, ec.caseHospCode as CASEHOSPCODE, ec.csClAmount as CSCLAMT, ec.schemeId as schemeId");
		query.append(" from EhfCase ec, EhfCaseTherapy ect ");
		query.append(" WHERE ec.id.caseId = ect.caseId");
		query.append(" and ec.caseStatus = 'CD51' ");
		query.append(" and ec.schemeId = 'CD202'  and ect.asriCatCode = 'S18' ");
		query.append(" and ec.caseId not in (select em.caseId from EhfMedicalAuditSampleCases em)");
		ehfCaseLst=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		/*List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	
		criteriaList.add(new GenericDAOQueryCriteria("asriCatCode", "S18",
			GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfCaseTherapy> EhfCaseTherapy = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
		
		criteriaList.removeAll(criteriaList);
		
		for(int i=0;i<EhfCaseTherapy.size();i++)
		{
			 criteriaList.removeAll(criteriaList);
			 criteriaList.add(new GenericDAOQueryCriteria("caseId", EhfCaseTherapy.get(i).getCaseId().toString(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("caseStatus", "CD51",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 //List<EhfCase> ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);*/
			 //for(LabelBean ehfCase:ehfCaseLst)
			 //{
			 //if(ehfCase==null)
				 //continue;
			 //criteriaList.removeAll(criteriaList);
			 //EhfMedicalAuditSampleCases alreadyPresentCase=new EhfMedicalAuditSampleCases();
			 //alreadyPresentCase=genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, ehfCase.getCASEID());
			if(ehfCaseLst!=null && ehfCaseLst.size()>0)
			{
			for (LabelBean ehfCase:ehfCaseLst)
			 {
				 count++;
				 String quater=findQuater();	
				 Calendar startCal = GregorianCalendar.getInstance(); 
				 int year=startCal.get(Calendar.YEAR);
		
			EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases=new EhfMedicalAuditSampleCases();	 		
			ehfMedicalAuditSampleCases.setCaseId(ehfCase.getCASEID());
			ehfMedicalAuditSampleCases.setCaseNo(ehfCase.getCASENO());
			ehfMedicalAuditSampleCases.setClaimNo(ehfCase.getCLAIMNO());
			ehfMedicalAuditSampleCases.setPatientId(ehfCase.getPATIENTID());
			ehfMedicalAuditSampleCases.setHospId(ehfCase.getCASEHOSPCODE());
			ehfMedicalAuditSampleCases.setSchemeId(ehfCase.getSchemeId());
			ehfMedicalAuditSampleCases.setCrtDt(new Timestamp(new Date().getTime()));
			ehfMedicalAuditSampleCases.setClaimPaidAmt(ehfCase.getCSCLAMT());
			ehfMedicalAuditSampleCases.setGroup("G4");
			ehfMedicalAuditSampleCases.setQuarter(quater);
			ehfMedicalAuditSampleCases.setYear(String.valueOf(year));
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("hospId", ehfCase.getCASEHOSPCODE(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmHospitals> ehfmHospitals = genericDao.findAllByCriteria(EhfmHospitals.class, criteriaList);
			criteriaList.removeAll(criteriaList);
			ehfMedicalAuditSampleCases.setDistrictCode(ehfmHospitals.get(0).getHospDist());
			ehfMedicalAuditSampleCases.setHospType(ehfmHospitals.get(0).getHospType());
			criteriaList.removeAll(criteriaList);
			try
				{
					genericDao.save(ehfMedicalAuditSampleCases);
				}
			catch(Exception e)
				{
					e.printStackTrace();
					LOGGER.info("Error Occured in Medical Audit Scheduler for Case id:"+ehfCase.getCASEID()+"Case ID ends"+e.getMessage());
				}
			 }
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println("dental schedular "+count);	
	}
	
	
	
	@Scheduled(cron="00 00 03 03 Dec,Sep,Mar,jan * ")
	public void fetchData(){
		System.out.println("an Scheduler to Fetch data for Medical Audit is started in TS");
		List<String> schemeId= new ArrayList<String>();
		schemeId.add("1");
		schemeId.add("CD201");
		schemeId.add("CD202");
		
		
		StringBuffer query = new StringBuffer();
		query.append("select sysdate-90 as DT from DUAl ");
		Date date=null;
		try
		{
			List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

	        if(seqList != null){
	        	
	        	date = seqList.get(0).getDT();
		}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
			
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("caseStatus", "CD51",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("csDeathDt", null,
					GenericDAOQueryCriteria.CriteriaOperator.IS_NULL));
		 criteriaList.add(new GenericDAOQueryCriteria("schemeId",schemeId,
				 	GenericDAOQueryCriteria.CriteriaOperator.IN));
		 criteriaList.add(new GenericDAOQueryCriteria("lstUpdDt",date,
				 	GenericDAOQueryCriteria.CriteriaOperator.GE));
		 List<EhfCase> ehfCase = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		 for(int i=0;i<ehfCase.size();i++){
			 
			 EhfMedicalAuditSampleCases alreadyPresentCase=genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, ehfCase.get(i).getCaseId());
			 
			 if(alreadyPresentCase==null){
		String quater=findQuater();	
		Calendar startCal = GregorianCalendar.getInstance(); 
		int year=startCal.get(Calendar.YEAR);
		//startCal.setTime(ehfCase.get(0).getLstUpdDt()); 
		//long startTime = startCal.getTimeInMillis(); 
		//Long days=((new java.util.Date()).getTime() - startTime)/ 86400000;
		//if(days<=90){
			EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases=new EhfMedicalAuditSampleCases();	 		
			ehfMedicalAuditSampleCases.setCaseId(ehfCase.get(i).getCaseId());
			ehfMedicalAuditSampleCases.setCaseNo(ehfCase.get(i).getCaseNo());
			ehfMedicalAuditSampleCases.setClaimNo(ehfCase.get(i).getClaimNo());
			ehfMedicalAuditSampleCases.setPatientId(ehfCase.get(i).getCasePatientNo());
			ehfMedicalAuditSampleCases.setHospId(ehfCase.get(i).getCaseHospCode());
			ehfMedicalAuditSampleCases.setSchemeId(ehfCase.get(i).getSchemeId());
			ehfMedicalAuditSampleCases.setCrtDt(new Timestamp(new Date().getTime()));
			ehfMedicalAuditSampleCases.setClaimPaidAmt(ehfCase.get(i).getCsClAmount());
			ehfMedicalAuditSampleCases.setQuarter(quater);
			ehfMedicalAuditSampleCases.setYear(String.valueOf(year));
			criteriaList.add(new GenericDAOQueryCriteria("hospId", ehfCase.get(i).getCaseHospCode(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmHospitals> ehfmHospitals = genericDao.findAllByCriteria(EhfmHospitals.class, criteriaList);
			criteriaList.removeAll(criteriaList);
			ehfMedicalAuditSampleCases.setDistrictCode(ehfmHospitals.get(0).getHospDist());
			ehfMedicalAuditSampleCases.setHospType(ehfmHospitals.get(0).getHospType());
			criteriaList.removeAll(criteriaList);
			genericDao.save(ehfMedicalAuditSampleCases);
			
		 //}
		 }
		 }
			System.out.println("an Scheduler to Fetch data for Medical Audit is ended in TS");
	}
	
	
	@Scheduled(cron="00 00 04 03 Dec,Sep,Mar,jan * ")
public void findCaseGroup(){
		try{
		String group=null;
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("locHdrId", "LH6",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("id.locParntId", "S17",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfmLocations> districtListAndhra = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		for(EhfmLocations district:districtListAndhra){
			
			String districtCode=district.getId().getLocId();
			criteriaList.add(new GenericDAOQueryCriteria("districtCode", districtCode,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("hospType", "C",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			List<EhfMedicalAuditSampleCases> dstCorpLst = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaList);
			 criteriaList.removeAll(criteriaList);
			 if(dstCorpLst.size()>0){
			 if(dstCorpLst.size()>30){
				 
				 group="G1";
				 
			 }
			
			 else if (dstCorpLst.size()<=30 && dstCorpLst.size()>10){
				 
				 group="G2";
				 
			 }
			 
			 else {
				 
				 group="G3";
			 }
		
			 for(EhfMedicalAuditSampleCases corpLst:dstCorpLst){
				 
				 if(!"G4".equalsIgnoreCase(corpLst.getGroup()))
					 corpLst.setGroup(group);
				// corpLst.setSchemeId("CD201");
				 genericDao.save(corpLst);
			 }
			 
		}
				criteriaList.add(new GenericDAOQueryCriteria("districtCode", districtCode,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("hospType", "G",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
				List<EhfMedicalAuditSampleCases> dstGovtLst = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaList);
				 criteriaList.removeAll(criteriaList);
				 
				 if(dstGovtLst.size()>0){
				 if(dstGovtLst.size()>10){
					 
					 group="G1";
					 
				 }
				
				 else if (dstGovtLst.size()<=10 && dstGovtLst.size()>5){
					 
					 group="G2";
					 
				 }
				 
				 else {
					 
					 group="G3";
				 }
			
				 for(EhfMedicalAuditSampleCases govtLst:dstGovtLst){
					 if(!"G4".equalsIgnoreCase(govtLst.getGroup()))
					 govtLst.setGroup(group);
					// govtLst.setSchemeId("CD201");
					 genericDao.save(govtLst);
				 }
		}
		}
		criteriaList.add(new GenericDAOQueryCriteria("locHdrId", "LH6",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("id.locParntId", "S35",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfmLocations> districtListTelangana = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		for(EhfmLocations district:districtListTelangana){
			
			String districtCode=district.getId().getLocId();
			criteriaList.add(new GenericDAOQueryCriteria("districtCode", districtCode,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("hospType", "C",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			List<EhfMedicalAuditSampleCases> dstCorpLst = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaList);
			 criteriaList.removeAll(criteriaList);
			 if(dstCorpLst.size()>0){
			 if(dstCorpLst.size()>30){
				 
				 group="G1";
				 
			 }
			
			 else if (dstCorpLst.size()<=30 && dstCorpLst.size()>10){
				 
				 group="G2";
				 
			 }
			 
			 else {
				 
				 group="G3";
			 }
		
			 for(EhfMedicalAuditSampleCases corpLst:dstCorpLst){
				 
				 if(!"G4".equalsIgnoreCase(corpLst.getGroup()))
				 corpLst.setGroup(group);
				 //corpLst.setSchemeId("CD202");
				 genericDao.save(corpLst);
			 }
			 
		}
				criteriaList.add(new GenericDAOQueryCriteria("districtCode", districtCode,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("hospType", "G",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
				List<EhfMedicalAuditSampleCases> dstGovtLst = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaList);
				 criteriaList.removeAll(criteriaList);
				 
				 if(dstGovtLst.size()>0){
				 if(dstGovtLst.size()>10){
					 
					 group="G1";
					 
				 }
				
				 else if (dstGovtLst.size()<=10 && dstGovtLst.size()>5){
					 
					 group="G2";
					 
				 }
				 
				 else {
					 
					 group="G3";
				 }
			
				 for(EhfMedicalAuditSampleCases govtLst:dstGovtLst){
					 
					 if(!"G4".equalsIgnoreCase(govtLst.getGroup()))
					 govtLst.setGroup(group);
					// govtLst.setSchemeId("CD202");
					 genericDao.save(govtLst);
				 }
		}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
     * ;
     * @function This method is used to find the quarter in which the scheduler is runned.
     */
	public String findQuater(){
	Calendar c = GregorianCalendar.getInstance(); 
	int month = c.get(Calendar.MONTH);
	
	return (month >= Calendar.JANUARY && month <= Calendar.MARCH)     ? "Q1":
					       (month >= Calendar.APRIL && month <= Calendar.JUNE)        ? "Q2" :
					       (month >= Calendar.JULY && month <= Calendar.SEPTEMBER)    ? "Q3" :
					                                                                    "Q4" ;
				}
	/**
     * ;
     * @function This method is used to identify all high cost cases present in raw data .In each district for Government and Corporate Hospitals highest claim paid case(s) is/are identified for audit. 
     * Among all high cost cases top 1% of cases are picked for each state for medical audit.
     */
	@Scheduled(cron="00 20 20 06 Dec,Sep,Mar,jan * ")
	public void findHighCostCase(){
		try{
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 List<EhfMedicalAuditSampleCases> auditSampleCasesList = genericDao.findAll(EhfMedicalAuditSampleCases.class);
		 Long claimAmtCorporate=0L;
		 Long claimAmtCorporate1=0L;
		 List<String> corPoratecaseId=new ArrayList<String>();
		 List<String> govtcaseId=new ArrayList<String>();
		 List<String> caseId=new ArrayList<String>();
		 
		 Set<String> districtsList=new HashSet<String>();
		 List<String> group=new ArrayList<String>();
		 group.add("G1");
		 group.add("G2");
		 group.add("G3");
		 
		 for(int i=0;i<auditSampleCasesList.size();i++){
			 
			 districtsList.add(auditSampleCasesList.get(i).getDistrictCode());
		 }
		 for (String district : districtsList) {
			    
			 criteriaList.add(new GenericDAOQueryCriteria("districtCode", district,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("hospType", "C",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("group",group,
						GenericDAOQueryCriteria.CriteriaOperator.IN));
			 claimAmtCorporate=genericDao.getMaxByPropertyCriteria(EhfMedicalAuditSampleCases.class,"claimPaidAmt",criteriaList); 
			 criteriaList.removeAll(criteriaList);
			
			 if(claimAmtCorporate!=null)
				 corPoratecaseId=getHighCostCaseIds(district,"C",claimAmtCorporate);
			
			 criteriaList.add(new GenericDAOQueryCriteria("districtCode", district,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("hospType", "G",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("group",group,
						GenericDAOQueryCriteria.CriteriaOperator.IN));
			 claimAmtCorporate1=genericDao.getMaxByPropertyCriteria(EhfMedicalAuditSampleCases.class,"claimPaidAmt",criteriaList); 
			 criteriaList.removeAll(criteriaList);
			 
			 if(claimAmtCorporate1!=null)
				 govtcaseId=getHighCostCaseIds(district,"G",claimAmtCorporate1);
			 
			 
			 if(corPoratecaseId.size()>0)
			 {
				 for(String corp:corPoratecaseId){
				 
					 caseId.add(corp);
				 }
			 }	
			 if(govtcaseId.size()>0)
			 {
			 for(String govt:govtcaseId){
				 
				 caseId.add(govt);
			 }
			 
			 if(caseId.size()>0)
			 {
				 for(String caseIdInd:caseId){
				
				 EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, caseIdInd);
				 if(ehfMedicalAuditSampleCases!=null){
					 
					 ehfMedicalAuditSampleCases.setHighCost("Y");
					 genericDao.save(ehfMedicalAuditSampleCases);
				 }
				 }
			 	}
			 }
		 }
		
			criteriaList.add(new GenericDAOQueryCriteria("highCost", "Y",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("schemeId","CD201",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("claimPaidAmt", "",
					GenericDAOQueryCriteria.CriteriaOperator.DESC));
			List<EhfMedicalAuditSampleCases> highCostTotalCasesAndhra = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
			 criteriaList.removeAll(criteriaList);
			 Double totalHighCostCasesAndhra= Double.valueOf(highCostTotalCasesAndhra.size());
				
				//percentage to be changed
				Double reqHCCAndhra=(1*totalHighCostCasesAndhra)/100;
				int requiredSizeAndhra=(int) Math.ceil(reqHCCAndhra);
				
				for(int j=0;j<requiredSizeAndhra;j++){
					
					EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, highCostTotalCasesAndhra.get(j).getCaseId());
					 if(ehfMedicalAuditSampleCases!=null){
						 
						 ehfMedicalAuditSampleCases.setHighCostAuditCase("Y");
						 genericDao.save(ehfMedicalAuditSampleCases);
					 }
					
				}
				criteriaList.add(new GenericDAOQueryCriteria("highCost", "Y",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("schemeId","CD202",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("claimPaidAmt", "",
						GenericDAOQueryCriteria.CriteriaOperator.DESC));
				List<EhfMedicalAuditSampleCases> highCostTotalCasesTelangana = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
				 criteriaList.removeAll(criteriaList);
				 Double totalHighCostCases= Double.valueOf(highCostTotalCasesTelangana.size());
					
					//percentage to be changed
					Double reqHCC=(1*totalHighCostCases)/100;
					Integer requiredSize=(int) Math.ceil(reqHCC);
					
					for(int j=0;j<requiredSize;j++){
						
						EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, highCostTotalCasesTelangana.get(j).getCaseId());
						 if(ehfMedicalAuditSampleCases!=null){
							 
							 ehfMedicalAuditSampleCases.setHighCostAuditCase("Y");
							 genericDao.save(ehfMedicalAuditSampleCases);
						 }
					}
							 
				criteriaList.add(new GenericDAOQueryCriteria("highCost", "Y",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("schemeId","1",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("claimPaidAmt", "",
						GenericDAOQueryCriteria.CriteriaOperator.DESC));
				List<EhfMedicalAuditSampleCases> highCostTotalCasesBefBif = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
					 criteriaList.removeAll(criteriaList);
				 Double totHighCostCases= Double.valueOf(highCostTotalCasesBefBif.size());
								
					//percentage to be changed
					Double reqHCCA=(1*totHighCostCases)/100;
						Integer reqSize=(int) Math.ceil(reqHCCA);
								
						for(int k=0;k<reqSize;k++){
									
							EhfMedicalAuditSampleCases ehfMedicalAuditSampleCasesNew =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, highCostTotalCasesBefBif.get(k).getCaseId());
						 if(ehfMedicalAuditSampleCasesNew!=null){
											 
							 ehfMedicalAuditSampleCasesNew.setHighCostAuditCase("Y");
							 genericDao.save(ehfMedicalAuditSampleCasesNew);			 
						 }
						
						}
				
		
					
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	/**
     * ;
     * @param String district
     * @param String hospType
     * @param String claimAmtCorporate
     * @return List<String> caseId
     * @function This method is used to fetch case id's of cases identified as High Cost. 
     */
public List<String> getHighCostCaseIds(String district,String hospType,Long claimAmtCorporate){
	
		double value=(0.6*claimAmtCorporate);
		claimAmtCorporate=Math.round(value);
		
		List<String> caseId=new ArrayList<String>();
		 List<String> group=new ArrayList<String>();
		 group.add("G1");
		 group.add("G2");
		 group.add("G3");
		
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("districtCode", district,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("hospType", hospType,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("claimPaidAmt", claimAmtCorporate,
				GenericDAOQueryCriteria.CriteriaOperator.GE));
		criteriaList.add(new GenericDAOQueryCriteria("group",group,
				GenericDAOQueryCriteria.CriteriaOperator.IN));
		
		List<EhfMedicalAuditSampleCases> highCostSampleCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		 
		 for(int i=0;i<highCostSampleCases.size();i++){
			 
			 caseId.add(highCostSampleCases.get(i).getCaseId());
		 }
		 
	
	
		return caseId;
	}

/**
 * ;
 * @function This method is used to identify all high volume cases present in raw data .In each district for Government and Corporate Hospitals the procedure that underwent more number of times is identified and all the cases which underwent the identified procedure are identified as High Volume Cases. 
 * Among all high volume cases 1% of cases are picked by random sampling for each state for medical audit.
 */
@Scheduled(cron="00 00 06 03 Dec,Sep,Mar,jan * ")
public void findHighVolumeCase(){
	
	String procId=null;
	String caseId=null;
	String quarter=findQuater();
	List<String> locParntIdList=new ArrayList<String>();
	locParntIdList.add("S17");
	locParntIdList.add("S35");
	Set<String> districtsList=new HashSet<String>();
	Set<String> proceduresList=new HashSet<String>();
	List<String> group=new ArrayList<String>();
	 group.add("G1");
	 group.add("G2");
	 group.add("G3");
	Calendar cal = GregorianCalendar.getInstance(); 
	String year=String.valueOf(cal.get(Calendar.YEAR));
	List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	criteriaList.add(new GenericDAOQueryCriteria("locHdrId", "LH6",
			GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("id.locParntId", locParntIdList,
			GenericDAOQueryCriteria.CriteriaOperator.IN));
	List<EhfmLocations> districtList = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
	 criteriaList.removeAll(criteriaList);
	for(EhfmLocations district:districtList){
		
		String districtCode=district.getId().getLocId();
		criteriaList.add(new GenericDAOQueryCriteria("districtCode", districtCode,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("quarter", quarter,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("year", year,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("group",group,
				GenericDAOQueryCriteria.CriteriaOperator.IN));
		List<EhfMedicalAuditSampleCases> dstCaseLst = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		if(dstCaseLst.size()>0){
			
			  for(EhfMedicalAuditSampleCases casesList:dstCaseLst){
				
				  caseId=casesList.getCaseId();
				  criteriaList.add(new GenericDAOQueryCriteria("caseId", caseId,
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 List<EhfCaseTherapy> ehfCaseTherapy = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
				 criteriaList.removeAll(criteriaList);
				 
				 for(EhfCaseTherapy therapy:ehfCaseTherapy){
					
					 EhfMedicalAuditHighVolumeCases ehfMedicalAuditHighVolumeCases=new EhfMedicalAuditHighVolumeCases();
					  
					ehfMedicalAuditHighVolumeCases.setHospType(casesList.getHospType());
					 ehfMedicalAuditHighVolumeCases.setDistrictCode(casesList.getDistrictCode());
					 procId=therapy.getIcdProcCode();
					ehfMedicalAuditHighVolumeCases.setId(new EhfMedicalAuditHighVolumeCasesId(caseId,procId));
					districtsList.add(casesList.getDistrictCode());
					proceduresList.add(procId);
					genericDao.save(ehfMedicalAuditHighVolumeCases);
				  
				  
			  }
				
		}
		
		
	}
	
	
}
	
	
	List<String> highVolList =new ArrayList<String>();
	 for(String dist:districtsList){
		 int maxCount;
			int cnt=0;
			int temp=0;
		 String procedureId=null;
			String distCode=null;
			String hospType=null;
		 
			for(String proc:proceduresList){
			 
			 criteriaList.add(new GenericDAOQueryCriteria("districtCode", dist,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("id.procedureId", proc,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("hospType", "C",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 List<EhfMedicalAuditHighVolumeCases> highVolumeList=genericDao.findAllByCriteria(EhfMedicalAuditHighVolumeCases.class, criteriaList);
			
			 
			 criteriaList.removeAll(criteriaList);
			 if(cnt==0){
			 maxCount=highVolumeList.size();
			 temp=maxCount;
			 procedureId=proc;
			 }
			 else {
				
				 if(highVolumeList.size()>temp){
					 
					 maxCount=highVolumeList.size();
					 procedureId=proc;
					 temp=maxCount;
				 }
				 
				 else if(highVolumeList.size()==temp){
					 
					 procedureId=procedureId+"~"+proc;
				 }
			 }
			 
			 distCode=dist;
			 hospType="C";
			 cnt=cnt+1;
			 
			 
		 }
		 
		 highVolList.add(distCode+"#"+hospType+"#"+procedureId);
		 
	 }
	 for(String dist:districtsList){
		 int maxCount;
			int cnt=0;
			int temp=0;
		 String procedureId=null;
			String distCode=null;
			String hospType=null;
		 
			for(String proc:proceduresList){
			 
			 criteriaList.add(new GenericDAOQueryCriteria("districtCode", dist,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("id.procedureId", proc,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("hospType", "G",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 List<EhfMedicalAuditHighVolumeCases> highVolumeList=genericDao.findAllByCriteria(EhfMedicalAuditHighVolumeCases.class, criteriaList);
			 criteriaList.removeAll(criteriaList);
			 if(cnt==0){
			 maxCount=highVolumeList.size();
			 temp=maxCount;
			 procedureId=proc;
			 }
			 else {
				
				 if(highVolumeList.size()>temp){
					 
					 maxCount=highVolumeList.size();
					 procedureId=proc;
					 temp=maxCount;
				 }
				 
				 else if(highVolumeList.size()==temp){
					 
					 procedureId=procedureId+"~"+proc;
				 }
			 }
			 
			 distCode=dist;
			 hospType="G";
			 cnt=cnt+1;
			 
			 
		 }
		 
		 highVolList.add(distCode+"#"+hospType+"#"+procedureId);
		 
	 }
	 
	 List<String> highVolCaseId=new ArrayList<String>();
	 List<EhfMedicalAuditHighVolumeCases> highVolumeList=null;
	 for(int i=0;i<highVolList.size();i++){
		 
		 String[] criteria=highVolList.get(i).split("#");
		 
		 String[] procedure=criteria[2].split("~");
		 
		 for(int j=0;j<procedure.length;j++){
			 
			 criteriaList.add(new GenericDAOQueryCriteria("districtCode", criteria[0],
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("hospType", criteria[1],
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("id.procedureId", procedure[j],
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 highVolumeList =genericDao.findAllByCriteria(EhfMedicalAuditHighVolumeCases.class, criteriaList);
			 criteriaList.removeAll(criteriaList);
		 }
		 
		 for(EhfMedicalAuditHighVolumeCases caseIdList:highVolumeList){
			 
			 highVolCaseId.add(caseIdList.getId().getCaseId());
		 }
		 

	 }
	 for(String caseIdhv:highVolCaseId){
			
		 EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, caseIdhv);
		 if(ehfMedicalAuditSampleCases!=null){
			 
			 ehfMedicalAuditSampleCases.setHighVolume("Y");
			 genericDao.save(ehfMedicalAuditSampleCases);
		 }
	 }
	 criteriaList.add(new GenericDAOQueryCriteria("highVolume", "Y",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 criteriaList.add(new GenericDAOQueryCriteria("schemeId","CD201",
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
		List<EhfMedicalAuditSampleCases> highVolumeTotalCasesAndhra = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		 Collections.shuffle(highVolumeTotalCasesAndhra);
		 Integer totalHighCostCasesAndhra= highVolumeTotalCasesAndhra.size();
			
			//percentage to be changed
			
			Integer requiredSize=(int) Math.ceil((1*totalHighCostCasesAndhra.doubleValue())/100);
			
			for(int j=0;j<requiredSize;j++){
				
				EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, highVolumeTotalCasesAndhra.get(j).getCaseId());
				 if(ehfMedicalAuditSampleCases!=null){
					 
					 ehfMedicalAuditSampleCases.setHighVolumeAuditCase("Y");
					 genericDao.save(ehfMedicalAuditSampleCases);
				 }
				
			}
			criteriaList.add(new GenericDAOQueryCriteria("highVolume", "Y",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("schemeId","CD202",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			List<EhfMedicalAuditSampleCases> highVolumeTotalCasesTelangana = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
			 criteriaList.removeAll(criteriaList);
			 Collections.shuffle(highVolumeTotalCasesTelangana);
			Integer totalHighCostCases= highVolumeTotalCasesTelangana.size();
			
				
				//percentage to be changed
				
				Integer requiredSizeTG=(int) Math.ceil((1*totalHighCostCases.doubleValue())/100);
				
				for(int j=0;j<requiredSizeTG;j++){
					
					EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, highVolumeTotalCasesTelangana.get(j).getCaseId());
					 if(ehfMedicalAuditSampleCases!=null){
						 
						 ehfMedicalAuditSampleCases.setHighVolumeAuditCase("Y");
						 genericDao.save(ehfMedicalAuditSampleCases);
					 }
					
				}
				List<EhfMedicalAuditHighVolumeCases> highVolumedeleteList=null;
				highVolumedeleteList = genericDao.findAll(EhfMedicalAuditHighVolumeCases.class);
				 for(int i=0;i<highVolumedeleteList.size();i++){
					 genericDao.delete(highVolumedeleteList.get(i));
				 }
				
			 criteriaList.add(new GenericDAOQueryCriteria("highVolume", "Y",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("schemeId","1",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
			List<EhfMedicalAuditSampleCases> highVolumeTotalCasesBefBif = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
			criteriaList.removeAll(criteriaList);
				 Collections.shuffle(highVolumeTotalCasesBefBif);
				Integer totalHighCostCasesNew= highVolumeTotalCasesBefBif.size();
					
						
						//percentage to be changed
						
						Integer requiredSizeNew=(int) Math.ceil((1*totalHighCostCasesNew.doubleValue())/100);
						
						for(int j=0;j<requiredSizeNew;j++){
							
							EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases =genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, highVolumeTotalCasesBefBif.get(j).getCaseId());
							 if(ehfMedicalAuditSampleCases!=null){
								 
								 ehfMedicalAuditSampleCases.setHighVolumeAuditCase("Y");
								 genericDao.save(ehfMedicalAuditSampleCases);
							 }
							
						}
						List<EhfMedicalAuditHighVolumeCases> highVolumedeleteListNew=null;
						highVolumedeleteListNew = genericDao.findAll(EhfMedicalAuditHighVolumeCases.class);
						 for(int i=0;i<highVolumedeleteListNew.size();i++){
							 genericDao.delete(highVolumedeleteListNew.get(i));
						 }		 
				 
}
		
}
