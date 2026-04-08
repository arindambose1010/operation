package com.ahct.medicalAudit.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.common.vo.LabelBean;

import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.medicalAudit.valueobject.MedicalAuditVO;
import com.ahct.model.EhfCasePatient;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfMedicalAuditAnswers;
import com.ahct.model.EhfMedicalAuditAnswersId;
import com.ahct.model.EhfMedicalAuditSampleCases;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmMedAuditQuesMst;
import com.ahct.model.EhfmSpecialities;
import com.ahct.model.EhfmTherapyCatMst;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmTherapyProcMstId;


public class MedicalAuditDaoImpl implements MedicalAuditDao {

	HibernateTemplate hibernateTemplate;
	private GenericDAO genericDao ;
	ConfigurationService configurationService;
	
	 public GenericDAO getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}


	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}


	public List<LabelBean> getQuestionAnswers(String caseId){
         List<LabelBean> quesAnsList=null;
         
         try{
        	 List<EhfmMedAuditQuesMst> lStEhfmMedAuditQuesMst=null;
        	 EhfMedicalAuditAnswers ehfMedicalAuditAnswers=null;
    	   	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
    	   	criteriaList.add(new GenericDAOQueryCriteria("quesSno", "",
    	   				GenericDAOQueryCriteria.CriteriaOperator.ASC));
    	   	lStEhfmMedAuditQuesMst=genericDao.findAllByCriteria(EhfmMedAuditQuesMst.class,criteriaList);
    	   	 criteriaList.removeAll(criteriaList);
    	   	LabelBean labelBean = null;
    	   	quesAnsList=new ArrayList<LabelBean>(); 
    	   	 for(int i=0;i<lStEhfmMedAuditQuesMst.size();i++){
    	   		labelBean=new LabelBean();
    	   		labelBean.setID(lStEhfmMedAuditQuesMst.get(i).getQuestionId());
    	   		labelBean.setVALUE(lStEhfmMedAuditQuesMst.get(i).getQuesDesc());
    	   		labelBean.setLVL(lStEhfmMedAuditQuesMst.get(i).getQuesCategory());
    	   		labelBean.setCOUNT(lStEhfmMedAuditQuesMst.get(i).getQuesSno());
    	   		ehfMedicalAuditAnswers=genericDao.findById(EhfMedicalAuditAnswers.class, EhfMedicalAuditAnswersId.class, new EhfMedicalAuditAnswersId(caseId,lStEhfmMedAuditQuesMst.get(i).getQuestionId()));
    	   	 if(ehfMedicalAuditAnswers!=null){
    	   		 
    	   		labelBean.setCONST(ehfMedicalAuditAnswers.getAnswer());
    	   		labelBean.setREMARKS(ehfMedicalAuditAnswers.getRemarks());
    	   	 }
    	   	 	quesAnsList.add(labelBean);
    	   	 }
         }
         catch(Exception e){
             e.printStackTrace();
         }
         
         return quesAnsList; 
     }
	public List<LabelBean> getDistricts(){
		
		 Configuration config = configurationService.getConfiguration();
		String distLocHdr= config.getString("distLocHdr");
		List<EhfmLocations> lStEhfmLocations=null;
		List<String> locParntId =new ArrayList<String>();
		locParntId.add("S17");
		locParntId.add("S35");
	   	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	   	 List<LabelBean> disList=null;
	   	criteriaList.add(new GenericDAOQueryCriteria("locHdrId", distLocHdr,
	   				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	   	criteriaList.add(new GenericDAOQueryCriteria("locName", "",
   				GenericDAOQueryCriteria.CriteriaOperator.ASC));
	   	criteriaList.add(new GenericDAOQueryCriteria("id.locParntId", locParntId,
   				GenericDAOQueryCriteria.CriteriaOperator.IN));
	   	
	   	lStEhfmLocations=genericDao.findAllByCriteria(EhfmLocations.class,criteriaList);
	   	 criteriaList.removeAll(criteriaList);
	   	LabelBean labelBean = null;
	   	disList=new ArrayList<LabelBean>(); 
	   	 for(EhfmLocations ehfmLocations:lStEhfmLocations){
	   		labelBean=new LabelBean();
	   		labelBean.setID(ehfmLocations.getId().getLocId());
	   		labelBean.setVALUE(ehfmLocations.getLocName());
	   		disList.add(labelBean);
	   	 } 
	   	return  disList;
	}
	public List<LabelBean> getSpecialities(){
		 List<EhfmSpecialities> lStEhfmSpecialities=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 List<LabelBean> specList=null;
		 criteriaList.add(new GenericDAOQueryCriteria("disActiveYN", "Y",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("disMainName", "",
					GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 lStEhfmSpecialities=genericDao.findAllByCriteria(EhfmSpecialities.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 LabelBean labelBean = null;
		 specList=new ArrayList<LabelBean>(); 
		 for(EhfmSpecialities ehfmSpecialities:lStEhfmSpecialities ){
			 labelBean=new LabelBean();
			 labelBean.setVALUE(ehfmSpecialities.getDisMainName());
			 labelBean.setID(ehfmSpecialities.getDisMainId());
			 specList.add(labelBean);
		 } 
		return  specList;
	 }
	
	public List<LabelBean> getCategories(String specialityType){
		
		 List<EhfmTherapyCatMst> lStEhfmTherapyCatMst=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 List<LabelBean> categoryList=null;
		 criteriaList.add(new GenericDAOQueryCriteria("activeYN", "Y",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.asriCatCode", specialityType,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("icdCatName", "",
					GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 lStEhfmTherapyCatMst=genericDao.findAllByCriteria(EhfmTherapyCatMst.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 LabelBean labelBean = null;
		 categoryList=new ArrayList<LabelBean>(); 
		 for(EhfmTherapyCatMst ehfmTherapyCatMst: lStEhfmTherapyCatMst ){
			 labelBean=new LabelBean();
			 labelBean.setID(ehfmTherapyCatMst.getId().getIcdCatCode());
			 labelBean.setVALUE(ehfmTherapyCatMst.getIcdCatName());
			 categoryList.add(labelBean);
		 } 
		return  categoryList;
	}
	public List<LabelBean> getProcedures(String categoryType){
		
		 List<EhfmTherapyProcMst> lStEhfmTherapyProcMst=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 List<LabelBean> categoryList=null;
		 criteriaList.add(new GenericDAOQueryCriteria("activeYN", "Y",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("icdCatCode", categoryType,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("procName", "",
					GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 lStEhfmTherapyProcMst=genericDao.findAllByCriteria(EhfmTherapyProcMst.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 LabelBean labelBean = null;
		 categoryList=new ArrayList<LabelBean>(); 
		 for(EhfmTherapyProcMst ehfmTherapyProcMst: lStEhfmTherapyProcMst ){
			 labelBean=new LabelBean();
			 labelBean.setID(ehfmTherapyProcMst.getId().getIcdProcCode());
			 labelBean.setVALUE(ehfmTherapyProcMst.getProcName());
			 categoryList.add(labelBean);
		 } 
		return  categoryList;
	}
	public List<LabelBean> getHospitals(String districtCode,String hospType){
		
		List<EhfmHospitals> lStEhfmHospitals=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 List<LabelBean> categoryList=null;
		 criteriaList.add(new GenericDAOQueryCriteria("hospActiveYN", "Y",
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("hospDist", districtCode,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("hospType", hospType,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("hospName", "",
					GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 lStEhfmHospitals=genericDao.findAllByCriteria(EhfmHospitals.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 LabelBean labelBean = null;
		 categoryList=new ArrayList<LabelBean>(); 
		 for(EhfmHospitals ehfmHospitals: lStEhfmHospitals ){
			 labelBean=new LabelBean();
			 labelBean.setID(ehfmHospitals.getHospId());
			 labelBean.setVALUE(ehfmHospitals.getHospName());
			 categoryList.add(labelBean);
		 } 
		return  categoryList;
	}
	@SuppressWarnings("unchecked")
	public List<MedicalAuditVO> getSampleCasesForAuditNew(MedicalAuditVO medicalAuditVO ){
		
		
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		StringBuffer sb=new StringBuffer();
		StringBuffer csId=new StringBuffer();
		
		 List<MedicalAuditVO> auditCases = new ArrayList<MedicalAuditVO>();
	    //List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		String district= null;
		String hospType=null;
		String grpType=null;
		String hospId=null;
		String specialityId=null;
		String categoryId=null;
		String procedureId=null;
		String caseNo=null;
		String claimNo=null;
		List<MedicalAuditVO> lStMedicalAuditVO=new ArrayList<MedicalAuditVO>(); 
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
				
				sb.append(" and emasc.workflowStatus is null ");
			}
			else {
				//criteriaList.add(new GenericDAOQueryCriteria("caseId", "",
	 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				sb.append(" emasc.caseId='' ");
				
				sb.append(" and emasc.workflowStatus is null ");
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
				sb.append(" from EhfMedicalAuditSampleCases emasc where ");
				
				sb.append(" emasc.workflowStatus is null ");
			}
			
			//criteriaList.add(new GenericDAOQueryCriteria("workflowStatus", null,
				//GenericDAOQueryCriteria.CriteriaOperator.IS_NULL));
			//sb.append(" emasc.workflowStatus is null ");
			
			
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
			if(medicalAuditVO.getHospType()!=null && !"".equalsIgnoreCase(medicalAuditVO.getHospType()))
	         {
	             hospType=  medicalAuditVO.getHospType(); 
	             //criteriaList.add(new GenericDAOQueryCriteria("hospType", hospType,
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
				 //criteriaList.add(new GenericDAOQueryCriteria("group", grpType,
		 					//GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 sb.append(" and emasc.group='"+grpType+"' ");
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
			 
			
			 //List<EhfMedicalAuditSampleCases> auditCasesSearchList= genericDao.findAllByCriteria(EhfMedicalAuditSampleCases.class, criteriaList);
			
			 List<MedicalAuditVO> auditCasesSearchList=new ArrayList<MedicalAuditVO>();
			 
			 if(medicalAuditVO.getEnd_index()!=0){
				 auditCasesSearchList=session.createQuery(sb.toString()).setFirstResult(medicalAuditVO.getStart_index())
				 		.setMaxResults(medicalAuditVO.getEnd_index()).setResultTransformer(Transformers.aliasToBean(MedicalAuditVO.class)).list();}
				
				else if((medicalAuditVO.getStart_index()==0)&&(medicalAuditVO.getEnd_index()==0)){
						auditCasesSearchList=session.createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(MedicalAuditVO.class)).list();
						return auditCasesSearchList;
					}
			 for(MedicalAuditVO auditList:auditCasesSearchList){
				 
				 
				 MedicalAuditVO auditCase=new MedicalAuditVO();
				 auditCase.setCASEID(auditList.getCaseId());
				 auditCase.setCASENO(auditList.getCaseNo());
				 auditCase.setHospId(auditList.getHospId());
				 auditCase.setCLAIMAMOUNT(auditList.getClaimPaidAmt());
				 auditCase.setGRP(auditList.getGroup());
				 auditCase.setDistrict(auditList.getDistrictCode());
				 auditCase.setPatientId(auditList.getPatientId());
				
				 auditCases.add(auditCase);
				 
				 
				 
			 }
			 
			 
			 for(MedicalAuditVO medicalAuditVO1:auditCases ){
				 MedicalAuditVO medicalAuditVOa=new MedicalAuditVO();
				 medicalAuditVOa.setCASEID(medicalAuditVO1.getCASEID());
				 medicalAuditVOa.setCASENO(medicalAuditVO1.getCASENO());
				String patientDetails= fetchPatientDetails(medicalAuditVO1.getPatientId());
				String[] details=patientDetails.split("~");
				
				medicalAuditVOa.setRATIONCARDNO(details[0]);
				medicalAuditVOa.setPATIENTNAME(details[1]);
				 EhfmHospitals ehfmHospitals= getHospitalDetails(medicalAuditVO1.getHospId());
				 if(ehfmHospitals!=null){
					 medicalAuditVOa.setHOSPITALNAME(ehfmHospitals.getHospName());
				 }
				 medicalAuditVOa.setDISTRICTNAME(getDistrictName(medicalAuditVO1.getDistrict()));
				 
				 medicalAuditVOa.setGRP(medicalAuditVO1.getGRP());
				
				 medicalAuditVOa.setSPECIALITYNAME(fetchCaseSpecialities(medicalAuditVO1.getCASEID()));
				 medicalAuditVOa.setPROCEDURENAME(fetchProcedures(medicalAuditVO1.getCASEID()));
				 medicalAuditVOa.setCLAIMAMOUNT(medicalAuditVO1.getCLAIMAMOUNT());
				 lStMedicalAuditVO.add(medicalAuditVOa);
	         }
		}
			catch(Exception e){
				e.printStackTrace();
			} 
			finally {
				session.close();
				factory.close();
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
	
}
