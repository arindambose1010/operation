package com.ahct.medicalAudit.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Calendar;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.common.vo.LabelBean;

import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.EhfmUsers;
import com.ahct.medicalAudit.dao.MedicalAuditDao;
import com.ahct.medicalAudit.valueobject.MedicalAuditVO;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCasePatient;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfMedAuditWorkFlowAudit;
import com.ahct.model.EhfMedAuditWorkFlowAuditId;
import com.ahct.model.EhfMedicalAuditAnswers;
import com.ahct.model.EhfMedicalAuditAnswersId;
import com.ahct.model.EhfMedicalAuditHighVolumeCases;
import com.ahct.model.EhfMedicalAuditHighVolumeCasesId;
import com.ahct.model.EhfMedicalAuditSampleCases;
import com.ahct.model.EhfMedicalAuditWorkflow;
import com.ahct.model.EhfmGrpWorkFlow;
import com.ahct.model.EhfmGrps;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmSpecialities;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmTherapyProcMstId;

public class MedicalAuditServiceImpl implements MedicalAuditService {

	static final Logger gLogger = Logger.getLogger(MedicalAuditServiceImpl.class);
	MedicalAuditDao medicalAuditDao;
	GenericDAO genericDao ;
	/*ConfigurationService configurationService;*/
	private static ConfigurationService configurationService;
	HibernateTemplate hibernateTemplate;
	private static CompositeConfiguration config;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/*public ConfigurationService getConfigurationService() {
		return configurationService;
	}*/

	static
	{
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	
	public void setConfigurationService(ConfigurationService configurationService) {
		MedicalAuditServiceImpl.configurationService = configurationService;
	}


	public MedicalAuditDao getMedicalAuditDao() {
		return medicalAuditDao;
	}


	public void setMedicalAuditDao(MedicalAuditDao medicalAuditDao) {
		this.medicalAuditDao = medicalAuditDao;
	}

	

	public GenericDAO getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	
	/**
     * ;
     * @return List<LabelBean> districtList
     * @function This method is used to fetch Districts list
     */
	
	public List<LabelBean> getDistricts(){
	
		List<LabelBean> districtList= null;
        try{
        	districtList= medicalAuditDao.getDistricts();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return districtList;
	}
	/**
     * ;
     * @return List<LabelBean> districtList
     * @function This method is used to fetch Specialities
     */
	public List<LabelBean> getSpecialities(){
		
		
		List<LabelBean> specialitiesList= null;
        try{
        	specialitiesList= medicalAuditDao.getSpecialities();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return specialitiesList;
	}
	
	/**
     * ;
     * @return List<LabelBean> districtList
     * @function This method is used to fetch Categories specific to a Speciality
     */
	public List<LabelBean> getCategories(String specialityType){
		
		List<LabelBean> categoriesList= null;
        try{
        	categoriesList= medicalAuditDao.getCategories(specialityType);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return categoriesList;
	}
	
	public List<LabelBean> getProcedures(String categoryType){
		
		List<LabelBean> proceduresList= null;
        try{
        	proceduresList= medicalAuditDao.getProcedures(categoryType);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return proceduresList;
	}
	public List<LabelBean> getHospitals(String districtCode,String hospType){
		
		List<LabelBean> hospList= null;
        try{
        	hospList= medicalAuditDao.getHospitals(districtCode,hospType);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return hospList;
	}
	public void saveMedicalAuditAnswers(MedicalAuditVO medicalAuditVO){
		
			String[] qnIds=null;
	        String[] rmks=null;
	        //List<String> grpLst=new ArrayList<String>();
	        List<String> grpLst=medicalAuditVO.getGpLst();
	        
	  
	        
	        String questions = medicalAuditVO.getResult();
	        String remarks= medicalAuditVO.getRemarks();
	        if(questions!=null && !"".equalsIgnoreCase(questions))
	        {
	             questions = questions.substring(0,questions.lastIndexOf('@'));
	             qnIds= questions.split("@");
	        }
	        if(remarks!=null && !"".equalsIgnoreCase(remarks))
	        {
	            remarks = remarks.substring(0,remarks.lastIndexOf('@'));
	            rmks= remarks.split("@");
	        }
	        if(qnIds!=null)
            {
                for(int i=0;i< qnIds.length;i++)
                {   
                	 EhfMedicalAuditAnswers ehfMedicalAuditAnswers=null;
                	 ehfMedicalAuditAnswers=genericDao.findById(EhfMedicalAuditAnswers.class, EhfMedicalAuditAnswersId.class, new EhfMedicalAuditAnswersId(medicalAuditVO.getCASEID(),qnIds[i].substring(0,qnIds[i].indexOf('~'))));
                    if(ehfMedicalAuditAnswers!= null)
                    {
                    	ehfMedicalAuditAnswers.setLstUpdUsr(medicalAuditVO.getUserId());
                    	ehfMedicalAuditAnswers.setLstUpdDt(new Timestamp(new Date().getTime()));
                    }
                    else{
                    	ehfMedicalAuditAnswers = new EhfMedicalAuditAnswers();
                    	ehfMedicalAuditAnswers.setId(new EhfMedicalAuditAnswersId(medicalAuditVO.getCASEID(),qnIds[i].substring(0,qnIds[i].indexOf('~'))));
                    	ehfMedicalAuditAnswers.setCrtDt(new Timestamp(new Date().getTime()));
                    	ehfMedicalAuditAnswers.setCrtUsr(medicalAuditVO.getUserId());
                    }
                    ehfMedicalAuditAnswers.setAnswer(qnIds[i].substring((qnIds[i].indexOf('~'))+1));
                    ehfMedicalAuditAnswers=genericDao.save(ehfMedicalAuditAnswers); 
                 }
            }
	        if(rmks!=null)
            {
                for(int i=0;i< rmks.length;i++)
                {   
                    
                	 EhfMedicalAuditAnswers ehfMedicalAuditAnswers=null;
                	 ehfMedicalAuditAnswers=genericDao.findById(EhfMedicalAuditAnswers.class, EhfMedicalAuditAnswersId.class, new EhfMedicalAuditAnswersId(medicalAuditVO.getCASEID(),rmks[i].substring(0,rmks[i].indexOf('~'))));
                    if(ehfMedicalAuditAnswers!= null)
                    {
                    	ehfMedicalAuditAnswers.setLstUpdUsr(medicalAuditVO.getUserId());
                    	ehfMedicalAuditAnswers.setLstUpdDt(new Timestamp(new Date().getTime()));
                    }
                    else{
                    	ehfMedicalAuditAnswers = new EhfMedicalAuditAnswers();
                    	ehfMedicalAuditAnswers.setId(new EhfMedicalAuditAnswersId(medicalAuditVO.getCASEID(),rmks[i].substring(0,rmks[i].indexOf('~'))));
                    	ehfMedicalAuditAnswers.setCrtDt(new Timestamp(new Date().getTime()));
                    	ehfMedicalAuditAnswers.setCrtUsr(medicalAuditVO.getUserId());
                    }
                    ehfMedicalAuditAnswers.setRemarks(rmks[i].substring((rmks[i].indexOf('~'))+1));
                    ehfMedicalAuditAnswers = genericDao.save(ehfMedicalAuditAnswers); 
                 }
            }
	        
	        EhfMedicalAuditWorkflow ehfMedicalAuditWorkflow=null;
	        ehfMedicalAuditWorkflow=genericDao.findById(EhfMedicalAuditWorkflow.class, String.class,medicalAuditVO.getCASEID());
           if(ehfMedicalAuditWorkflow!= null)
           {
        	   ehfMedicalAuditWorkflow.setLstUpdUsr(medicalAuditVO.getUserId());
        	   ehfMedicalAuditWorkflow.setLstUpddt((new Timestamp(new Date().getTime())));
        	  if(medicalAuditVO.getActionType().equalsIgnoreCase("Submit")){
   	        	ehfMedicalAuditWorkflow.setStatus("P");
   	        	if(grpLst.contains("GP70"))
      			  ehfMedicalAuditWorkflow.setStatus("C");
   	        }
           }
           else {
	         ehfMedicalAuditWorkflow =new EhfMedicalAuditWorkflow();
	         ehfMedicalAuditWorkflow.setCaseId(medicalAuditVO.getCASEID());
	        if(medicalAuditVO.getActionType().equalsIgnoreCase("Save")){
	        	
	        	ehfMedicalAuditWorkflow.setStatus("I");
	        	ehfMedicalAuditWorkflow.setCrtUsr(medicalAuditVO.getUserId());
		        ehfMedicalAuditWorkflow.setCrtDt((new Timestamp(new Date().getTime())));
		        
	        }
	        if(medicalAuditVO.getActionType().equalsIgnoreCase("Submit")){
	        	
	        	ehfMedicalAuditWorkflow.setStatus("P");
	        	if(grpLst.contains("GP70"))
	      			  ehfMedicalAuditWorkflow.setStatus("C");
	        	ehfMedicalAuditWorkflow.setCrtUsr(medicalAuditVO.getUserId());
		        ehfMedicalAuditWorkflow.setCrtDt((new Timestamp(new Date().getTime())));
		        
	        }
           }
	       
	        ehfMedicalAuditWorkflow.setPrevOwnerGrp(medicalAuditVO.getPreviousOwnerGrp());
	        ehfMedicalAuditWorkflow.setPrevWorkFlowId(medicalAuditVO.getPrevWorkflowId());
	        ehfMedicalAuditWorkflow.setCurrOwnerGrp(medicalAuditVO.getCurrentOwnerGrp());
	        ehfMedicalAuditWorkflow.setCurrWorkFlowId(medicalAuditVO.getCurrentWorkflowId());
	        genericDao.save(ehfMedicalAuditWorkflow);
	        if(medicalAuditVO.getActionType().equalsIgnoreCase("Submit")){
	        	
	    EhfMedicalAuditSampleCases alreadyPresentCase=genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, medicalAuditVO.getCASEID());
				 
				 if(alreadyPresentCase!=null){
					
					 if(grpLst.contains("GP70"))
						 alreadyPresentCase.setWorkflowStatus("C");
					 else
						 alreadyPresentCase.setWorkflowStatus("P");
					 
					 alreadyPresentCase.setLstUpddt((new Timestamp(new Date().getTime())));
					 alreadyPresentCase.setLstUpdUsr(medicalAuditVO.getUserId());
					 genericDao.save(alreadyPresentCase);
	        }
	        }
	        EhfMedAuditWorkFlowAudit ehfMedAuditWorkFlowAudit = new EhfMedAuditWorkFlowAudit();
			 List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
			 critList.add(new GenericDAOQueryCriteria("id.caseId", medicalAuditVO.getCASEID(),
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfMedAuditWorkFlowAudit> efMedAuditWorkFlowAuditList = genericDao
						.findAllByCriteria(EhfMedAuditWorkFlowAudit.class, critList);
			critList.removeAll(critList);	
			
			if(efMedAuditWorkFlowAuditList.size()>0){
				
				efMedAuditWorkFlowAuditList.get(0).setRemarks(medicalAuditVO.getFinalRemarks());
				efMedAuditWorkFlowAuditList.get(0).setAssigndDt(new Timestamp(new Date().getTime()));
				efMedAuditWorkFlowAuditList.get(0).setActdDt(new Timestamp(new Date().getTime()));
				efMedAuditWorkFlowAuditList.get(0).setCurrentWorkFlowId(medicalAuditVO.getPrevWorkflowId());
				efMedAuditWorkFlowAuditList.get(0).setNextWorkFlwId(medicalAuditVO.getCurrentWorkflowId());
				genericDao.save(efMedAuditWorkFlowAuditList.get(0));
			}
			else {
				
			int lineNo =efMedAuditWorkFlowAuditList.size()+1;
			ehfMedAuditWorkFlowAudit.setId(new EhfMedAuditWorkFlowAuditId(medicalAuditVO.getCASEID(),lineNo));
			ehfMedAuditWorkFlowAudit.setActor(medicalAuditVO.getUserId());
			ehfMedAuditWorkFlowAudit.setGrpId(medicalAuditVO.getPreviousOwnerGrp());
			ehfMedAuditWorkFlowAudit.setCurrentWorkFlowId(medicalAuditVO.getPrevWorkflowId());
			ehfMedAuditWorkFlowAudit.setNextWorkFlwId(medicalAuditVO.getCurrentWorkflowId());
			ehfMedAuditWorkFlowAudit.setAssigndDt(new Timestamp(new Date().getTime()));
			ehfMedAuditWorkFlowAudit.setActdDt(new Timestamp(new Date().getTime()));
			ehfMedAuditWorkFlowAudit.setRemarks(medicalAuditVO.getFinalRemarks());
			ehfMedAuditWorkFlowAudit.setStatus("P");
			if(grpLst.contains("GP70"))
				ehfMedAuditWorkFlowAudit.setStatus("C");
			ehfMedAuditWorkFlowAudit=genericDao.save(ehfMedAuditWorkFlowAudit);
			}
			
	}
	public List<LabelBean> getQuestionAnswers(String caseId){
		
		List<LabelBean> hospList= null;
        try{
        	hospList= medicalAuditDao.getQuestionAnswers(caseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return hospList;
	}
	public List<MedicalAuditVO> getSampleCasesForAuditNew(MedicalAuditVO medicalAuditVO ){
		
		List<MedicalAuditVO> auditCasesList=null;
		auditCasesList=medicalAuditDao.getSampleCasesForAuditNew(medicalAuditVO);
		return auditCasesList;
	}
	public List<MedicalAuditVO> auditCasesForDisplay(List<MedicalAuditVO> auditSampleCases){
		
		 List<MedicalAuditVO> lStMedicalAuditVO=new ArrayList<MedicalAuditVO>(); 
		 for(MedicalAuditVO medicalAuditVO:auditSampleCases ){
			 medicalAuditVO.setCASEID(medicalAuditVO.getCASEID());
			 medicalAuditVO.setCASENO(medicalAuditVO.getCASENO());
			String patientDetails= fetchPatientDetails(medicalAuditVO.getPatientId());
			String[] details=patientDetails.split("~");
			
			 medicalAuditVO.setRATIONCARDNO(details[0]);
			 medicalAuditVO.setPATIENTNAME(details[1]);
			 EhfmHospitals ehfmHospitals= getHospitalDetails(medicalAuditVO.getHospId());
			 if(ehfmHospitals!=null){
				 medicalAuditVO.setHOSPITALNAME(ehfmHospitals.getHospName());
			 }
		 medicalAuditVO.setDISTRICTNAME(getDistrictName(medicalAuditVO.getDistrict()));
			 
		 medicalAuditVO.setGRP(medicalAuditVO.getGRP());
			
			 medicalAuditVO.setSPECIALITYNAME(fetchCaseSpecialities(medicalAuditVO.getCASEID()));
			 medicalAuditVO.setPROCEDURENAME(fetchProcedures(medicalAuditVO.getCASEID()));
			 medicalAuditVO.setCLAIMAMOUNT(medicalAuditVO.getCLAIMAMOUNT());
			 lStMedicalAuditVO.add(medicalAuditVO);
         }
		 return lStMedicalAuditVO;
	}
	
	public String getDistrictName(String districtId){
		
		String districtName=null;
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("id.locId", districtId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfmLocations> districtList = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if(districtList.size()>0){
			 
			 districtName=districtList.get(0).getLocName();
		 }
		 return districtName;
	}
	
	
	public String fetchCaseSpecialities(String caseId){
		
	String specialities="";
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("caseId", caseId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 List<EhfCaseTherapy> ehfCaseTherapy = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
	 criteriaList.removeAll(criteriaList);
	 if (ehfCaseTherapy.size() > 1) {
			for (int i = 0;  i<ehfCaseTherapy.size(); i++) {
				if (i == (ehfCaseTherapy.size() - 1)) {
					specialities=specialities+getSpecialityName(ehfCaseTherapy.get(i).getAsriCatCode());
				} else {
					
					if (i < ehfCaseTherapy.size())

					{
						specialities=specialities+getSpecialityName(ehfCaseTherapy.get(i).getAsriCatCode())+",";
						
					}

				}

			}
	 }
			
	
		else {
			specialities=getSpecialityName(ehfCaseTherapy.get(0).getAsriCatCode());
			
		}
	 return specialities;
	}
	
	public String fetchProcedures(String caseId){
		
		 Set<String> specialitiesList=new HashSet<String>();
		 Set<String> proceduresList=new HashSet<String>();
		String procedure="";
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("caseId", caseId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 List<EhfCaseTherapy> ehfCaseTherapy = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if (ehfCaseTherapy.size() > 0) {
				for (int i = 0;  i<ehfCaseTherapy.size(); i++) {
					
					specialitiesList.add(ehfCaseTherapy.get(i).getAsriCatCode());
					 proceduresList.add(ehfCaseTherapy.get(i).getIcdProcCode());

				}
		 }
				
		 for(String speciality:specialitiesList){
			 
			 for(String proc:proceduresList){
				 
			EhfmTherapyProcMst ehfmTherapyProcMst = genericDao.findById(EhfmTherapyProcMst.class, EhfmTherapyProcMstId.class, new EhfmTherapyProcMstId(speciality,proc, null,null));
				
			if(ehfmTherapyProcMst!=null)
			{
				if(procedure==""){
				procedure=ehfmTherapyProcMst.getProcName();
			}
				else {
					
					procedure=procedure+","+ehfmTherapyProcMst.getProcName();
				}
			}
			 }
			 
		 }
			
		 return procedure;
	}
	 
	 public String getSpecialityName(String specialityId){
		 
		 EhfmSpecialities ehfmSpecialities=genericDao.findById(EhfmSpecialities.class, String.class,specialityId );
		 
		 if(ehfmSpecialities!=null){
		 return ehfmSpecialities.getDisMainName();
		 }
		 else {
			 
			 return null;
		 }
		 
	 }
	 
	 
public EhfmHospitals getHospitalDetails(String hospId){
		
		
		EhfmHospitals ehfmHospitalDetails=genericDao.findById(EhfmHospitals.class, String.class,hospId );
		
			
			return ehfmHospitalDetails;
		
	}
	public String fetchPatientDetails(String patientId){
		
		String patientName=null;
		String rationCardNo=null;
		EhfCasePatient ehfCasePatient=genericDao.findById(EhfCasePatient.class, String.class, patientId);
		if(ehfCasePatient!=null){
			
			patientName=ehfCasePatient.getName();
			rationCardNo=ehfCasePatient.getCardNo();
		}
		
		return rationCardNo+"~"+patientName;
		
	}
	
	
	
	

 
	
	
	
	
	
	
	public String findAuditAlreadyStarted(String caseId,String userId){
		
		String status=null;
		EhfMedicalAuditWorkflow ehfMedicalAuditWorkflow=null;
        ehfMedicalAuditWorkflow=genericDao.findById(EhfMedicalAuditWorkflow.class, String.class,caseId);
       if(ehfMedicalAuditWorkflow!= null)
       {
    	   if(ehfMedicalAuditWorkflow.getStatus().equalsIgnoreCase("I"))
    	   {
    		   if(!userId.equalsIgnoreCase(ehfMedicalAuditWorkflow.getCrtUsr())){
    		   String actorName=getActorName(ehfMedicalAuditWorkflow.getCrtUsr());
    		   status="initiated"+"~"+actorName;
    	   }
    	   }
    	   
	}
       return status;
	}
	
	 public String getActorName(String userId){
			
		 String actorName=null;
		
		EhfmUsers ehfmUser =genericDao.findById(EhfmUsers.class,String.class,userId);
		if(ehfmUser!=null){
			
			actorName=ehfmUser.getFirstName()+" "+ehfmUser.getLastName();
			
		}
		return actorName;
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<MedicalAuditVO> getAuditedCasesList(MedicalAuditVO medicalAuditVO){
		 
		 	SessionFactory factory=hibernateTemplate.getSessionFactory();
			Session session=factory.openSession();
			StringBuffer sb=new StringBuffer();
			StringBuffer csId=new StringBuffer();
			
		 List<MedicalAuditVO> auditCases = new ArrayList<MedicalAuditVO>();
		    List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			String district= null;
			String hospType=null;
			String grpType=null;
			String hospId=null;
			String specialityId=null;
			String categoryId=null;
			String procedureId=null;
			String caseNo=null;
			String claimNo=null;
			String auditedBy=null;
			try{
		if(medicalAuditVO.getSpecialityId()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSpecialityId())||medicalAuditVO.getCategoryId()!=null && !"".equalsIgnoreCase(medicalAuditVO.getCategoryId())||medicalAuditVO.getProcedureId()!=null && !"".equalsIgnoreCase(medicalAuditVO.getProcedureId()) )
				{
					 
					 specialityId=medicalAuditVO.getSpecialityId();
					 categoryId=medicalAuditVO.getCategoryId(); 
					 procedureId=medicalAuditVO.getProcedureId();
					 List<EhfMedicalAuditSampleCases> totalSampledCases=null;
				List<GenericDAOQueryCriteria> criteriaListBegin=new ArrayList<GenericDAOQueryCriteria>();
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHC".equalsIgnoreCase(medicalAuditVO.getSelectionType())){
				criteriaListBegin.add(new GenericDAOQueryCriteria("highCostAuditCase", "Y",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				totalSampledCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaListBegin);
				criteriaListBegin.removeAll(criteriaListBegin);
				}
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHV".equalsIgnoreCase(medicalAuditVO.getSelectionType())){
					criteriaListBegin.add(new GenericDAOQueryCriteria("highVolumeAuditCase", "Y",
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					totalSampledCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaListBegin);
					criteriaListBegin.removeAll(criteriaListBegin);
				}
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "DEN".equalsIgnoreCase(medicalAuditVO.getSelectionType())){
					criteriaListBegin.add(new GenericDAOQueryCriteria("group", "G4",
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					totalSampledCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaListBegin);
					criteriaListBegin.removeAll(criteriaListBegin);
				}
				
				List<String> caseIdList=new ArrayList<String>();
				for(EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases:totalSampledCases){
				
					criteriaListBegin.add(new GenericDAOQueryCriteria("caseId", ehfMedicalAuditSampleCases.getCaseId(),
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					if(!specialityId.equalsIgnoreCase("-1")&& !specialityId.equalsIgnoreCase("")){
						
						criteriaListBegin.add(new GenericDAOQueryCriteria("asriCatCode", specialityId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
					if(!categoryId.equalsIgnoreCase("-1") && !categoryId.equalsIgnoreCase("")){
						
						criteriaListBegin.add(new GenericDAOQueryCriteria("icdCatCode", categoryId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
					if(!procedureId.equalsIgnoreCase("-1") && !procedureId.equalsIgnoreCase("") ){
						
						criteriaListBegin.add(new GenericDAOQueryCriteria("icdProcCode", procedureId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
					List<EhfCaseTherapy> therapySearch = genericDao.findAllByCriteria(EhfCaseTherapy.class,criteriaListBegin);
					criteriaListBegin.removeAll(criteriaListBegin);
					
					if(therapySearch.size()>0){
						
						caseIdList.add(ehfMedicalAuditSampleCases.getCaseId());
					}
				}
				
					sb.append("select emasc.caseId as caseId,emasc.caseNo as caseNo,emasc.claimNo as claimNo,");
					sb.append("emasc.patientId as patientId,emasc.districtCode as districtCode,");
					sb.append("emasc.claimPaidAmt as claimPaidAmt,emasc.hospId as hospId,emasc.hospType as hospType1,");
					sb.append("emasc.schemeId as schemeId,emasc.crtDt as crtDt,emasc.quarter as quarter,");
					sb.append("emasc.year as year,emasc.highCost as highCost,emasc.highVolume as highVolume,");
					sb.append("emasc.highCostAuditCase as highCostAuditCase,emasc.highVolumeAuditCase as highVolumeAuditCase,");
					sb.append("emasc.group as group,emasc.workflowStatus as workflowStatus");
					sb.append(" from EhfMedicalAuditSampleCases emasc,EhfMedAuditWorkFlowAudit emawa,");
					sb.append("EhfMedicalAuditWorkflow emaw where ");
					sb.append(" emawa.id.caseId=emasc.caseId and emawa.id.actOrder='1' and emasc.caseId=emaw.caseId ");
	
		
					if(caseIdList.size()>0){
						
						for(int l=0;l<caseIdList.size();l++)
						{
							if(l==0)
								csId.append("'");
							else
								csId.append(",'");
							
							csId.append(caseIdList.get(l));
							csId.append("'");
						}
						
						/*criteriaList.add(new GenericDAOQueryCriteria("caseId", caseIdList,
			 					GenericDAOQueryCriteria.CriteriaOperator.IN));*/
					sb.append(" and emasc.caseId in ("+csId+") ");
					
					sb.append(" and emasc.workflowStatus='C' ");
						}
						else {
							//criteriaList.add(new GenericDAOQueryCriteria("caseId", "",
				 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					sb.append(" and emasc.caseId='' ");	
					
					sb.append(" and emasc.workflowStatus='C' ");
						}
						}
				else
				{
					sb.append("select emasc.caseId as caseId,emasc.caseNo as caseNo,emasc.claimNo as claimNo,");
					sb.append("emasc.patientId as patientId,emasc.districtCode as districtCode,");
					sb.append("emasc.claimPaidAmt as claimPaidAmt,emasc.hospId as hospId,emasc.hospType as hospType1,");
					sb.append("emasc.schemeId as schemeId,emasc.crtDt as crtDt,emasc.quarter as quarter,");
					sb.append("emasc.year as year,emasc.highCost as highCost,emasc.highVolume as highVolume,");
					sb.append("emasc.highCostAuditCase as highCostAuditCase,emasc.highVolumeAuditCase as highVolumeAuditCase,");
					sb.append("emasc.group as group,emasc.workflowStatus as workflowStatus ");
					sb.append("from EhfMedicalAuditSampleCases emasc,EhfMedAuditWorkFlowAudit emawa,");
					sb.append("EhfMedicalAuditWorkflow emaw where ");
					
					sb.append(" emasc.workflowStatus='C' ");
					sb.append(" and emawa.id.caseId=emasc.caseId and emawa.id.actOrder='1' and emasc.caseId=emaw.caseId ");
				}
				
					//sb.append(" emasc.workflowStatus='C' ");
		
				
				if(medicalAuditVO.getHospType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getHospType()))
		         {
		             hospType=  medicalAuditVO.getHospType(); 
		            // criteriaList.add(new GenericDAOQueryCriteria("hospType", hospType,
		 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		             sb.append(" and emasc.hospType='"+hospType+"' ");
		         }
				if(medicalAuditVO.getHospId()!=null && !medicalAuditVO.getHospId().equalsIgnoreCase("-1") && !"".equalsIgnoreCase(medicalAuditVO.getHospId())){
					 
					 hospId=medicalAuditVO.getHospId();
					 //criteriaList.add(new GenericDAOQueryCriteria("hospId", hospId,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.hospId='"+hospId+"' ");
				 }
				if(medicalAuditVO.getCASENO()!=null && !"".equalsIgnoreCase(medicalAuditVO.getCASENO())){
					 
					 caseNo=medicalAuditVO.getCASENO().toUpperCase();
					 //criteriaList.add(new GenericDAOQueryCriteria("caseNo", caseNo,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.caseNo='"+caseNo+"' ");
				 }
				 if(medicalAuditVO.getCLAIMNO()!=null && !"".equalsIgnoreCase(medicalAuditVO.getCLAIMNO())){
			 
					 claimNo=medicalAuditVO.getCLAIMNO().toUpperCase();
					 //criteriaList.add(new GenericDAOQueryCriteria("claimNo", claimNo,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.claimNo='"+claimNo+"' ");
				 }
				 
				 if(medicalAuditVO.getDistrict()!=null && !"".equalsIgnoreCase(medicalAuditVO.getDistrict())) 
		         {
		             district=  medicalAuditVO.getDistrict(); 
		             //criteriaList.add(new GenericDAOQueryCriteria("districtCode", district,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		             sb.append(" and emasc.districtCode='"+district+"' ");
		         }
				 
				 
				
				 if(medicalAuditVO.getGroupType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getGroupType()))
				 {
					 grpType=medicalAuditVO.getGroupType();
					// criteriaList.add(new GenericDAOQueryCriteria("group", grpType,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.group='"+grpType+"' ");
				 }
				 
				 if(medicalAuditVO.getAuditedBy()!=null && !"".equalsIgnoreCase(medicalAuditVO.getAuditedBy()))
				 {
					 auditedBy=medicalAuditVO.getAuditedBy();
					// criteriaList.add(new GenericDAOQueryCriteria("group", grpType,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 if(auditedBy.equalsIgnoreCase("DEO"))
					 		auditedBy=config.getString("DEO");
					 if(auditedBy.equalsIgnoreCase("JEO"))
					 		auditedBy=config.getString("JEO");
					 if(auditedBy.equalsIgnoreCase("CMA"))
					 		auditedBy=config.getString("CMA");
					 
					 	sb.append(" and emawa.grpId='"+auditedBy+"' ");
				 }
				 
				 if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHC".equalsIgnoreCase(medicalAuditVO.getSelectionType()))
					{
				 //criteriaList.add(new GenericDAOQueryCriteria("highCostAuditCase", "Y",
							//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.highCostAuditCase='Y' ");
					}
				 
				 if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHV".equalsIgnoreCase(medicalAuditVO.getSelectionType()))
					{
				 //criteriaList.add(new GenericDAOQueryCriteria("highVolumeAuditCase", "Y",
							//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.highVolumeAuditCase='Y' ");
					}
				 if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "DEN".equalsIgnoreCase(medicalAuditVO.getSelectionType()))
					{
				 //criteriaList.add(new GenericDAOQueryCriteria("group", "G4",
							//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 sb.append(" and emasc.group='G4' ");
					}
				 
				 if(medicalAuditVO.getFromDate()!=null && !"".equalsIgnoreCase(medicalAuditVO.getFromDate())
						 && medicalAuditVO.getToDate()!=null && !"".equalsIgnoreCase(medicalAuditVO.getToDate()))
					{
					 try
					 	{
						 	String toDate=medicalAuditVO.getToDate().toString();
						 	Calendar cal=Calendar.getInstance();
						 	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
						 	cal.add(Calendar.DAY_OF_YEAR, 1);
						 	Date nextDt=cal.getTime();
						 	String nextDat=new SimpleDateFormat("dd-MM-yyyy").format(nextDt);
						 			
						 	sb.append(" and emasc.lstUpddt between TO_DATE('"+medicalAuditVO.getFromDate()+"','DD-MM-YYYY') ");
						 	sb.append(" and TO_DATE('"+nextDat+"','DD-MM-YYYY') ");
						} 
					 catch(Exception e)
					 	{
						 	e.printStackTrace();
//						 	gLogger.error("Exception Occurred in MedicalAuditServiceImpl class."+e.getMessage());
					 	}
					}
				 Configuration config = configurationService.getConfiguration();
				 String apCode=config.getString("AP");
				 String tgCode=config.getString("TG");
				 List<String> userStateList= new ArrayList<String>();
				 userStateList.add(apCode);
				 userStateList.add(tgCode);
				 if(medicalAuditVO.getSchemeId()!=null && !medicalAuditVO.getSchemeId().equalsIgnoreCase("")) {
					  
					 if(medicalAuditVO.getUserState().equalsIgnoreCase(config.getString("COMMON"))){
						  
						  if(medicalAuditVO.getSchemeId().equalsIgnoreCase(medicalAuditVO.getUserState())){
							 
							  
								 //criteriaList.add(new GenericDAOQueryCriteria("schemeId", userStateList,
											//GenericDAOQueryCriteria.CriteriaOperator.IN));
							  sb.append(" and emasc.schemeId in ('"+apCode+"','"+tgCode+"','1') ");
							  
						  }
						  else{
						 
						  //criteriaList.add(new GenericDAOQueryCriteria("schemeId", medicalAuditVO.getSchemeId(),
									//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
							  sb.append(" and emasc.schemeId='"+medicalAuditVO.getSchemeId()+"' ");
						  }
						 }
						 else {
							
							 //criteriaList.add(new GenericDAOQueryCriteria("schemeId", medicalAuditVO.getUserState(),
										//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
							 sb.append(" and emasc.schemeId='"+medicalAuditVO.getUserState()+"' ");
						 }
					 
				}
					  else {
						  
						  if(medicalAuditVO.getUserState().equalsIgnoreCase(config.getString("COMMON"))){
							  
							 // criteriaList.add(new GenericDAOQueryCriteria("schemeId", userStateList,
										//GenericDAOQueryCriteria.CriteriaOperator.IN));
							  sb.append(" and emasc.schemeId in ('"+apCode+"','"+tgCode+"','1') ");
								  
							  }
							 
							 else {
								 //criteriaList.add(new GenericDAOQueryCriteria("schemeId", medicalAuditVO.getUserState(),
											//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								 sb.append(" and emasc.schemeId='"+medicalAuditVO.getUserState()+"' ");
							 }
					  }
				// criteriaList.add(new GenericDAOQueryCriteria("workflowStatus", "C",
		 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS)); 
				
				 //List<EhfMedicalAuditSampleCases> auditCasesSearchList= genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
				 List<MedicalAuditVO> auditCasesSearchList=new ArrayList<MedicalAuditVO>();
				 
				 if(medicalAuditVO.getEnd_index()!=0){
					 auditCasesSearchList=session.createQuery(sb.toString()).setFirstResult(medicalAuditVO.getStart_index())
					 		.setMaxResults(medicalAuditVO.getEnd_index()).setResultTransformer(Transformers.aliasToBean(MedicalAuditVO.class)).list();}
					
					else if((medicalAuditVO.getStart_index()==0)&&(medicalAuditVO.getEnd_index()==0)){
					 auditCasesSearchList=session.createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(MedicalAuditVO.class)).list();
					 return auditCasesSearchList;}
		 criteriaList.removeAll(criteriaList);
		 List<String> caseIdMainList=new ArrayList<String>();
		 for(MedicalAuditVO sample:auditCasesSearchList){
			 
			 caseIdMainList.add(sample.getCaseId());
		 }
		 if(caseIdMainList.size()>0){
				
				for(int i=0;i<caseIdMainList.size();i++){
					String caseType=null;
				EhfMedicalAuditSampleCases searchCase=genericDao.findById(EhfMedicalAuditSampleCases.class, String.class,caseIdMainList.get(i));
				MedicalAuditVO auditCase=new MedicalAuditVO();
				 auditCase.setCASEID(searchCase.getCaseId());
				 auditCase.setCASENO(searchCase.getCaseNo());
				 auditCase.setHospId(searchCase.getHospId());
				 auditCase.setCLAIMAMOUNT(searchCase.getClaimPaidAmt());
				 auditCase.setGRP(searchCase.getGroup());
				 auditCase.setDistrict(searchCase.getDistrictCode());
				 auditCase.setPatientId(searchCase.getPatientId());
				 if(!searchCase.getGroup().equalsIgnoreCase("G4"))
				 {
					 if(searchCase.getHighCostAuditCase()!=null && searchCase.getHighCostAuditCase().equalsIgnoreCase("Y") && searchCase.getHighVolumeAuditCase()==null){
						 
						 caseType = "High Cost";
					 }
					 if(searchCase.getHighCostAuditCase()==null && searchCase.getHighVolumeAuditCase().equalsIgnoreCase("Y") && searchCase.getHighVolumeAuditCase()!=null){
						 
						 caseType = "High Volume";
					 }
					 if(searchCase.getHighCostAuditCase()!=null && searchCase.getHighCostAuditCase().equalsIgnoreCase("Y") && searchCase.getHighVolumeAuditCase()!=null && searchCase.getHighVolumeAuditCase().equalsIgnoreCase("Y")){
						 
						 caseType = "High Cost & High Volume";
					 }
				 }	 
				 auditCase.setCaseType(caseType);
				 auditCases.add(auditCase);
				}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				session.close();
				factory.close();
			}
			return auditCases;
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<MedicalAuditVO> getMedicalAuditWorklist(MedicalAuditVO medicalAuditVO){
		 
		 SessionFactory factory=hibernateTemplate.getSessionFactory();
		 Session session=factory.openSession();
		 StringBuffer sb=new StringBuffer();
		 StringBuffer grp=new StringBuffer();
		 StringBuffer csId=new StringBuffer();
		 
		 List<MedicalAuditVO> auditCases = new ArrayList<MedicalAuditVO>();
		    List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			String district= null;
			String hospType=null;
			String grpType=null;
			String hospId=null;
			String specialityId=null;
			String categoryId=null;
			String procedureId=null;
			String caseNo=null;
			String claimNo=null;
			try{
		if(medicalAuditVO.getSpecialityId()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSpecialityId())||medicalAuditVO.getCategoryId()!=null && !"".equalsIgnoreCase(medicalAuditVO.getCategoryId())||medicalAuditVO.getProcedureId()!=null && !"".equalsIgnoreCase(medicalAuditVO.getProcedureId()) )
				{
					 
					 specialityId=medicalAuditVO.getSpecialityId();
					 categoryId=medicalAuditVO.getCategoryId(); 
					 procedureId=medicalAuditVO.getProcedureId();
					 List<EhfMedicalAuditSampleCases> totalSampledCases=null;
				List<GenericDAOQueryCriteria> criteriaListBegin=new ArrayList<GenericDAOQueryCriteria>();
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHC".equalsIgnoreCase(medicalAuditVO.getSelectionType())){
				criteriaListBegin.add(new GenericDAOQueryCriteria("highCostAuditCase", "Y",
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				totalSampledCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaListBegin);
				criteriaListBegin.removeAll(criteriaListBegin);
				}
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHV".equalsIgnoreCase(medicalAuditVO.getSelectionType())){
					criteriaListBegin.add(new GenericDAOQueryCriteria("highVolumeAuditCase", "Y",
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					totalSampledCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaListBegin);
					criteriaListBegin.removeAll(criteriaListBegin);
				}
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "DEN".equalsIgnoreCase(medicalAuditVO.getSelectionType())){
					criteriaListBegin.add(new GenericDAOQueryCriteria("group", "G4",
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					totalSampledCases = genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class,criteriaListBegin);
					criteriaListBegin.removeAll(criteriaListBegin);
				}
				List<String> caseIdList=new ArrayList<String>();
				for(EhfMedicalAuditSampleCases ehfMedicalAuditSampleCases:totalSampledCases){
				
					criteriaListBegin.add(new GenericDAOQueryCriteria("caseId", ehfMedicalAuditSampleCases.getCaseId(),
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					if(!specialityId.equalsIgnoreCase("-1")&& !specialityId.equalsIgnoreCase("")){
						
						criteriaListBegin.add(new GenericDAOQueryCriteria("asriCatCode", specialityId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
					if(!categoryId.equalsIgnoreCase("-1") && !categoryId.equalsIgnoreCase("")){
						
						criteriaListBegin.add(new GenericDAOQueryCriteria("icdCatCode", categoryId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
					if(!procedureId.equalsIgnoreCase("-1") && !procedureId.equalsIgnoreCase("") ){
						
						criteriaListBegin.add(new GenericDAOQueryCriteria("icdProcCode", procedureId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
					List<EhfCaseTherapy> therapySearch = genericDao.findAllByCriteria(EhfCaseTherapy.class,criteriaListBegin);
					criteriaListBegin.removeAll(criteriaListBegin);
					
					if(therapySearch.size()>0){
						
						caseIdList.add(ehfMedicalAuditSampleCases.getCaseId());
					}
				}
				
				
				sb.append("select emasc.caseId as caseId,emasc.caseNo as caseNo,emasc.claimNo as claimNo,");
				sb.append("emasc.patientId as patientId,emasc.districtCode as districtCode,");
				sb.append("emasc.claimPaidAmt as claimPaidAmt,emasc.hospId as hospId,emasc.hospType as hospType1,");
				sb.append("emasc.schemeId as schemeId,emasc.crtDt as crtDt,emasc.quarter as quarter,");
				sb.append("emasc.year as year,emasc.highCost as highCost,emasc.highVolume as highVolume,");
				sb.append("emasc.highCostAuditCase as highCostAuditCase,emasc.highVolumeAuditCase as highVolumeAuditCase,");
				sb.append("emasc.group as group,emasc.workflowStatus as workflowStatus");
				sb.append(" from EhfMedicalAuditSampleCases emasc where ");
				
				
				if(caseIdList.size()>0){
					
					for(int l=0;l<caseIdList.size();l++)
					{
						if(l==0)
							csId.append("'");
						else
							csId.append(",'");
						
						csId.append(caseIdList.get(l));
						csId.append("'");
					}
					
				//criteriaList.add(new GenericDAOQueryCriteria("caseId", caseIdList,
	 					//GenericDAOQueryCriteria.CriteriaOperator.IN));
					sb.append(" emasc.caseId in ("+csId+") ");
				}
				else {
					//criteriaList.add(new GenericDAOQueryCriteria("caseId", "",
		 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					sb.append(" emasc.caseId='' ");
					
					sb.append(" and emasc.workflowStatus='P' ");
				}
				}
				else
				{
					sb.append("select emasc.caseId as caseId,emasc.caseNo as caseNo,emasc.claimNo as claimNo,");
					sb.append("emasc.patientId as patientId,emasc.districtCode as districtCode,");
					sb.append("emasc.claimPaidAmt as claimPaidAmt,emasc.hospId as hospId,emasc.hospType as hospType1,");
					sb.append("emasc.schemeId as schemeId,emasc.crtDt as crtDt,emasc.quarter as quarter,");
					sb.append("emasc.year as year,emasc.highCost as highCost,emasc.highVolume as highVolume,");
					sb.append("emasc.highCostAuditCase as highCostAuditCase,emasc.highVolumeAuditCase as highVolumeAuditCase,");
					sb.append("emasc.group as group,emasc.workflowStatus as workflowStatus ");
					sb.append("from EhfMedicalAuditSampleCases emasc,EhfMedicalAuditWorkflow emawf where ");
					
					sb.append(" emasc.workflowStatus='P' ");
				}
				//sb.append(" emasc.workflowStatus='P' ");
				
				sb.append(" and emawf.status='P' ");
				
				sb.append(" and emasc.caseId=emawf.caseId ");
				
				for(int g=0;g<medicalAuditVO.getGrpLst().size();g++)
				{
					if(g==0)
						grp.append("'");
					else
						grp.append(",'");
					
					grp.append(medicalAuditVO.getGrpLst().get(g));
					grp.append("'");
				}
				
				if(medicalAuditVO.getCmaDeoFlag()!=null)
					{
						if(medicalAuditVO.getCmaDeoFlag().equalsIgnoreCase("Y"))
						{
							sb.append(" and emawf.prevOwnerGrp in ('"+config.getString("DEO_MA_Grp")+"') ");
						}
						else if(medicalAuditVO.getCmaDeoFlag().equalsIgnoreCase("N"))
						{
							sb.append(" and emawf.prevOwnerGrp in ('"+config.getString("JEO_MA_Grp")+"') ");
							//sb.append(" and emawf.currOwnerGrp in ('"+config.getString("CMA_MA_Grp")+"') ");
						}
						else if(medicalAuditVO.getCmaDeoFlag().equalsIgnoreCase("C"))
						{
							sb.append(" and emawf.prevOwnerGrp in ('"+config.getString("CMA_MA_Grp")+"') ");
							//sb.append(" and emawf.currOwnerGrp in ('"+config.getString("CMA_MA_Grp")+"') ");
						}
						else
							sb.append(" and emawf.currOwnerGrp in ("+grp.toString()+") ");
					}
				else
					sb.append(" and emawf.currOwnerGrp in ("+grp.toString()+") ");
		
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHC".equalsIgnoreCase(medicalAuditVO.getSelectionType()))
					{
						//criteriaList.add(new GenericDAOQueryCriteria("highCostAuditCase", "Y",
								//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						sb.append(" and emasc.highCostAuditCase='Y' ");
					}
	 
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "PHV".equalsIgnoreCase(medicalAuditVO.getSelectionType()))
					{
						//criteriaList.add(new GenericDAOQueryCriteria("highVolumeAuditCase", "Y",
							//	GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						sb.append(" and emasc.highVolumeAuditCase='Y' ");
					}
				if(medicalAuditVO.getSelectionType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getSelectionType()) && "DEN".equalsIgnoreCase(medicalAuditVO.getSelectionType()))
				{
						//criteriaList.add(new GenericDAOQueryCriteria("group", "G4",
								//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						sb.append(" and emasc.group='G4' ");
				} 
				if(medicalAuditVO.getHospType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getHospType()))
		         {
		             hospType=  medicalAuditVO.getHospType(); 
		             //criteriaList.add(new GenericDAOQueryCriteria("hospType", hospType,
		 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		             	sb.append(" and emasc.hospType='"+hospType+"' ");
		         }
				if(medicalAuditVO.getHospId()!=null && !medicalAuditVO.getHospId().equalsIgnoreCase("-1") &&  !"".equalsIgnoreCase(medicalAuditVO.getHospId())){
					 
					 hospId=medicalAuditVO.getHospId();
					 //criteriaList.add(new GenericDAOQueryCriteria("hospId", hospId,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 	sb.append(" and emasc.hospId='"+hospId+"' ");
				 }
				if(medicalAuditVO.getCASENO()!=null && !"".equalsIgnoreCase(medicalAuditVO.getCASENO())){
					 
					 caseNo=medicalAuditVO.getCASENO().toUpperCase();
					 //criteriaList.add(new GenericDAOQueryCriteria("caseNo", caseNo,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 	sb.append(" and emasc.caseNo='"+caseNo+"' ");
				 }
				 if(medicalAuditVO.getCLAIMNO()!=null && !"".equalsIgnoreCase(medicalAuditVO.getCLAIMNO())){
			 
					 claimNo=medicalAuditVO.getCLAIMNO().toUpperCase();
					 //criteriaList.add(new GenericDAOQueryCriteria("claimNo", claimNo,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 	sb.append(" and emasc.claimNo='"+claimNo+"' ");
				 }
				 
				 if(medicalAuditVO.getDistrict()!=null && !"".equalsIgnoreCase(medicalAuditVO.getDistrict())) 
		         {
		             district=  medicalAuditVO.getDistrict(); 
		             //criteriaList.add(new GenericDAOQueryCriteria("districtCode", district,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		             	sb.append(" and emasc.districtCode='"+district+"' ");
		         }
				 
				 
				
				 if(medicalAuditVO.getGroupType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getGroupType()))
				 {
					 grpType=medicalAuditVO.getGroupType();
					// criteriaList.add(new GenericDAOQueryCriteria("group", grpType,
			 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 	sb.append(" and emasc.group='"+grpType+"' ");
				 }
				 
				 
				 //criteriaList.add(new GenericDAOQueryCriteria("workflowStatus", "P",
		 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS)); 
				 
				 	
				 
				 Configuration config = configurationService.getConfiguration();
				 String apCode=config.getString("AP");
				 String tgCode=config.getString("TG");
				 List<String> userStateList= new ArrayList<String>();
				 userStateList.add(apCode);
				 userStateList.add(tgCode);
				 if(medicalAuditVO.getSchemeId()!=null && !medicalAuditVO.getSchemeId().equalsIgnoreCase("")) {
					  
					 if(medicalAuditVO.getUserState().equalsIgnoreCase(config.getString("COMMON"))){
						  
						  if(medicalAuditVO.getSchemeId().equalsIgnoreCase(medicalAuditVO.getUserState())){
							 
							  
								// criteriaList.add(new GenericDAOQueryCriteria("schemeId", userStateList,
									//		GenericDAOQueryCriteria.CriteriaOperator.IN));
							  sb.append(" and emasc.schemeId in ('"+apCode+"','"+tgCode+"','1') ");
							  
						  }
						  else{
						 
						  //criteriaList.add(new GenericDAOQueryCriteria("schemeId", medicalAuditVO.getSchemeId(),
									//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						  sb.append(" and emasc.schemeId='"+medicalAuditVO.getSchemeId()+"' ");
						  }
						 }
						 else {
							
							 //criteriaList.add(new GenericDAOQueryCriteria("schemeId", medicalAuditVO.getUserState(),
										//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
							 sb.append(" and emasc.schemeId='"+medicalAuditVO.getUserState()+"' ");
						 }
					 
				}
					  else {
						  
						  if(medicalAuditVO.getUserState().equalsIgnoreCase(config.getString("COMMON"))){
							  
							  //criteriaList.add(new GenericDAOQueryCriteria("schemeId", userStateList,
										//GenericDAOQueryCriteria.CriteriaOperator.IN));
							  sb.append(" and emasc.schemeId in ('"+apCode+"','"+tgCode+"','1') ");
								  
							  }
							 
							 else {
								 //criteriaList.add(new GenericDAOQueryCriteria("schemeId", medicalAuditVO.getUserState(),
											//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								 sb.append(" and emasc.schemeId='"+medicalAuditVO.getUserState()+"' ");
							 }
					  }
				 //List<EhfMedicalAuditSampleCases> auditCasesSearchList= genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
				 List<MedicalAuditVO> auditCasesSearchList=new ArrayList<MedicalAuditVO>();
		 criteriaList.removeAll(criteriaList);
		 
		 if(medicalAuditVO.getEnd_index()!=0){
			 auditCasesSearchList=session.createQuery(sb.toString()).setFirstResult(medicalAuditVO.getStart_index())
			 		.setMaxResults(medicalAuditVO.getEnd_index()).setResultTransformer(Transformers.aliasToBean(MedicalAuditVO.class)).list();}
			
			else if((medicalAuditVO.getStart_index()==0)&&(medicalAuditVO.getEnd_index()==0)){
			 auditCasesSearchList=session.createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(MedicalAuditVO.class)).list();
			 return auditCasesSearchList;}
		 
		 List<String> caseIdMainList=new ArrayList<String>();
		 for(MedicalAuditVO sample:auditCasesSearchList){
			 
			 caseIdMainList.add(sample.getCaseId());
		 }
		 
		 //criteriaList.add(new GenericDAOQueryCriteria("currOwnerGrp", medicalAuditVO.getGrpLst(),
					//GenericDAOQueryCriteria.CriteriaOperator.IN)); 
		 //criteriaList.add(new GenericDAOQueryCriteria("status", "P",
					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS)); 
		
	//List<EhfMedicalAuditWorkflow> workList = genericDao.findAllByCriteria(EhfMedicalAuditWorkflow.class, criteriaList);
	criteriaList.removeAll(criteriaList);
	 //List<String> caseIdWorkList=new ArrayList<String>();
			//for(EhfMedicalAuditWorkflow ehfMedicalAuditWorkflow:workList){
				
				//caseIdWorkList.add(ehfMedicalAuditWorkflow.getCaseId());
				
				
			//}
			
			
			//List<String> commonCaseIdList = new ArrayList<String>(caseIdWorkList);
			//commonCaseIdList.retainAll(caseIdMainList);
			
			if(caseIdMainList.size()>0){
				
				for(int i=0;i<caseIdMainList.size();i++){
					String caseType=null;
				EhfMedicalAuditSampleCases searchCase=genericDao.findById(EhfMedicalAuditSampleCases.class, String.class,caseIdMainList.get(i));
				MedicalAuditVO auditCase=new MedicalAuditVO();
				 auditCase.setCASEID(searchCase.getCaseId());
				 auditCase.setCASENO(searchCase.getCaseNo());
				 auditCase.setHospId(searchCase.getHospId());
				 auditCase.setCLAIMAMOUNT(searchCase.getClaimPaidAmt());
				 auditCase.setGRP(searchCase.getGroup());
				 auditCase.setDistrict(searchCase.getDistrictCode());
				 auditCase.setPatientId(searchCase.getPatientId());
				 if(!searchCase.getGroup().equalsIgnoreCase("G4"))
				 {
					 if(searchCase.getHighCostAuditCase()!=null && searchCase.getHighCostAuditCase().equalsIgnoreCase("Y") && searchCase.getHighVolumeAuditCase()==null){
						 
						 caseType = "High Cost";
					 }
					 if(searchCase.getHighCostAuditCase()==null && searchCase.getHighVolumeAuditCase().equalsIgnoreCase("Y") && searchCase.getHighVolumeAuditCase()!=null){
						 
						 caseType = "High Volume";
					 }
					 if(searchCase.getHighCostAuditCase()!=null && searchCase.getHighCostAuditCase().equalsIgnoreCase("Y") && searchCase.getHighVolumeAuditCase()!=null && searchCase.getHighVolumeAuditCase().equalsIgnoreCase("Y")){
						 
						 caseType = "High Cost & High Volume";
					 }
				 }	 
				 auditCase.setCaseType(caseType);
				 auditCases.add(auditCase);
				}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				session.close();
				factory.close();
			}
			return auditCases;
		 
	 }
	 
	 
	
	
	public String fetchRemarks(String caseId){
		
		String remarks=null;
		EhfMedAuditWorkFlowAudit ehfMedAuditWorkFlowAudit=genericDao.findById(EhfMedAuditWorkFlowAudit.class, EhfMedAuditWorkFlowAuditId.class, new EhfMedAuditWorkFlowAuditId(caseId,1));
		
		if(ehfMedAuditWorkFlowAudit!=null){
			
			remarks=ehfMedAuditWorkFlowAudit.getRemarks();
		}
		return remarks;
	}
	
	public List<MedicalAuditVO> getPreviousRemarks(String caseId){
		
		String actorName=null;
		List<MedicalAuditVO> lStMedicalAuditVO=new ArrayList<MedicalAuditVO>(); 
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.caseId", caseId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.ASC));
		
		 List<EhfMedAuditWorkFlowAudit> lStEhfMedAuditWorkFlowAudit=genericDao.findAllByCriteria(EhfMedAuditWorkFlowAudit.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 for(EhfMedAuditWorkFlowAudit ehfMedAuditWorkFlowAudit:lStEhfMedAuditWorkFlowAudit){
			 MedicalAuditVO medicalAuditVO=new MedicalAuditVO();
			 DateFormat dateFormat =new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss");
			 String strDate = dateFormat.format(ehfMedAuditWorkFlowAudit.getActdDt());
			 medicalAuditVO.setRemarksDt(strDate);
			String grpName= findGroupName(ehfMedAuditWorkFlowAudit.getGrpId());
			medicalAuditVO.setRole(grpName);
			 if(ehfMedAuditWorkFlowAudit.getActor()!=null && ehfMedAuditWorkFlowAudit.getActor()!=""){
			actorName=getActorName(ehfMedAuditWorkFlowAudit.getActor());
			 }
			 medicalAuditVO.setUpdatedBy(actorName);
			 medicalAuditVO.setPrevRemarks(ehfMedAuditWorkFlowAudit.getRemarks());
			 lStMedicalAuditVO.add(medicalAuditVO);
		 }
		 
		 return lStMedicalAuditVO;
	}
	
	public String findGroupName(String GrpId){
		
		String grpName=null;
		EhfmGrps ehfmGrps= genericDao.findById(EhfmGrps.class, String.class, GrpId);
		
		if(ehfmGrps.getGrpName()!=null){
			grpName=ehfmGrps.getGrpName();
		}
		
		return grpName;
	}
	 public String getCurrentWorkFlowId(String caseId){
		 String workFlowId=null;
		 List<EhfMedicalAuditWorkflow> lStEhfMedicalAuditWorkflow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("caseId", caseId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEhfMedicalAuditWorkflow=genericDao.findAllByCriteria(EhfMedicalAuditWorkflow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	 
		 if(lStEhfMedicalAuditWorkflow.size()>0){
			 
			 workFlowId=lStEhfMedicalAuditWorkflow.get(0).getCurrWorkFlowId();
		 }
		 
		 return workFlowId;
	 }
	 public String getPreviousOwnerGrp(String workflowId){
		 String grpId=null;
		 List<EhfmGrpWorkFlow> lStEhfmGrpWorkFlow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
		 lStEhfmGrpWorkFlow=genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	
		 if (lStEhfmGrpWorkFlow != null && lStEhfmGrpWorkFlow.size() > 0) {
				
				grpId=lStEhfmGrpWorkFlow.get(0).getGrpId();
	 }
		 return grpId;
	}
	 public void saveMedAuditWrkFlowDtls(MedicalAuditVO medicalAuditVO){
		
		 
		 List<EhfMedicalAuditWorkflow> lStEhfMedicalAuditWorkflow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("caseId", medicalAuditVO.getCASEID(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEhfMedicalAuditWorkflow=genericDao.findAllByCriteria(EhfMedicalAuditWorkflow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	 
		 if(lStEhfMedicalAuditWorkflow.size()>0){
			 
			 EhfMedicalAuditWorkflow ehfMedicalAuditWorkflow =lStEhfMedicalAuditWorkflow.get(0);
			 ehfMedicalAuditWorkflow.setLstUpdUsr(medicalAuditVO.getUserId());
			 ehfMedicalAuditWorkflow.setLstUpddt(new Timestamp(new Date().getTime()));
			 ehfMedicalAuditWorkflow.setCurrOwnerGrp(medicalAuditVO.getCurrentOwnerGrp());
			 ehfMedicalAuditWorkflow.setCurrWorkFlowId(medicalAuditVO.getCurrentWorkflowId());
			 ehfMedicalAuditWorkflow.setPrevOwnerGrp(medicalAuditVO.getPreviousOwnerGrp());
			 ehfMedicalAuditWorkflow.setPrevWorkFlowId(medicalAuditVO.getPrevWorkflowId());
			
			if((medicalAuditVO.getCurrentOwnerGrp()==null||medicalAuditVO.getCurrentOwnerGrp()=="")){
				
				ehfMedicalAuditWorkflow.setStatus("C");
				
			}
			else{
				ehfMedicalAuditWorkflow.setStatus("P");
			}
			
			ehfMedicalAuditWorkflow=genericDao.save(ehfMedicalAuditWorkflow);
			
			 
		 }
		 EhfMedAuditWorkFlowAudit  ehfMedAuditWorkFlowAudit=new EhfMedAuditWorkFlowAudit();
		 criteriaList.add(new GenericDAOQueryCriteria("id.caseId", medicalAuditVO.getCASEID(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfMedAuditWorkFlowAudit> ehfMedAuditWorkFlowAuditList = genericDao
					.findAllByCriteria(EhfMedAuditWorkFlowAudit.class, criteriaList);
			 criteriaList.removeAll(criteriaList);	
			int lineNo =ehfMedAuditWorkFlowAuditList.size()+1;
			ehfMedAuditWorkFlowAudit.setId(new EhfMedAuditWorkFlowAuditId(medicalAuditVO.getCASEID(),lineNo));
			ehfMedAuditWorkFlowAudit.setRemarks(medicalAuditVO.getWorkFlowRemarks());
			ehfMedAuditWorkFlowAudit.setActor(medicalAuditVO.getUserId());
			ehfMedAuditWorkFlowAudit.setGrpId(medicalAuditVO.getPreviousOwnerGrp());
			ehfMedAuditWorkFlowAudit.setCurrentWorkFlowId(medicalAuditVO.getPrevWorkflowId());
			ehfMedAuditWorkFlowAudit.setNextWorkFlwId(medicalAuditVO.getCurrentWorkflowId());
		 criteriaList.add(new GenericDAOQueryCriteria("id.caseId", medicalAuditVO.getCASEID(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
		
		 List<EhfMedAuditWorkFlowAudit> auditList=genericDao.findAllByCriteria(EhfMedAuditWorkFlowAudit.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if(auditList.size()>0){
			 
			 ehfMedAuditWorkFlowAudit.setAssigndDt(auditList.get(0).getActdDt());
		 }
		 ehfMedAuditWorkFlowAudit.setActdDt(new Timestamp(new Date().getTime()));
		 if((medicalAuditVO.getCurrentOwnerGrp()==null||medicalAuditVO.getCurrentOwnerGrp()=="")){
				
			 ehfMedAuditWorkFlowAudit.setStatus("C");
			}
			else{
				ehfMedAuditWorkFlowAudit.setStatus("P");
			}
			
		 ehfMedAuditWorkFlowAudit=genericDao.save(ehfMedAuditWorkFlowAudit);
		 
		 if((medicalAuditVO.getCurrentOwnerGrp()==null||medicalAuditVO.getCurrentOwnerGrp()=="")){
			 
			 EhfMedicalAuditSampleCases alreadyPresentCase=genericDao.findById(EhfMedicalAuditSampleCases.class, String.class, medicalAuditVO.getCASEID());
			 
			 if(alreadyPresentCase!=null){
				
				 alreadyPresentCase.setWorkflowStatus("C");
				 alreadyPresentCase.setLstUpddt((new Timestamp(new Date().getTime())));
				 alreadyPresentCase.setLstUpdUsr(medicalAuditVO.getUserId());
				 genericDao.save(alreadyPresentCase);
        }
		 }
		 
		
	 }
}
