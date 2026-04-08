package com.ahct.patient.service;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.patient.dao.PatientDao;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.tcs.framework.configuration.ConfigurationService;



public class PatientServiceImpl implements PatientService {
	private final static Logger GLOGGER = Logger.getLogger ( PatientServiceImpl.class ) ;
	PatientDao patientDao;
	public PatientDao getPatientDao()
	{
		return patientDao;
	}
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
	@Override
	public PatientVO retrieveCardDetails(PatientVO patientVo) {
		PatientVO patientVO=null;
		try
		{
			patientVO=patientDao.retrieveCardDetails(patientVo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return patientVO;
	}
	/**
     * ;
     * @param PatientVO patientVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	@Override
	public int registerPatient(PatientVO patientVO) {
		int i=0;
		try {
			i = patientDao.registerPatient(patientVO);
		}
		catch (Exception e) {
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in registerPatient() in PatientServiceImpl class."+e.getMessage());
		}
		return i;
		
	}
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
	@Override
	public List<LabelBean> getHospital(String userId,String roleId) {
		List<LabelBean> hospitalList=null;
		try {
			hospitalList = patientDao.getHospital(userId,roleId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospital() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}
	/**
     * ;
     * @param HashMap<String,String> searchMap
     * @return PatientVO registeredPatientsList
     * @function This Method is Used For retrieving registered patients list for given search data
     */
	@Override
	public PatientVO getRegisteredPatients(HashMap<String, String> searchMap) {
		PatientVO registeredPatientsList=null;
		try {
			registeredPatientsList=patientDao.getRegisteredPatients(searchMap);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getRegisteredPatients() in PatientServiceImpl class."+e.getMessage());
		}
		return registeredPatientsList;
	}
	/**
     * ;
     * @param String patientId
     * @return AsritPatient asritPatient
     * @function This Method is Used For retrieving PatientDetails for given PatientId
     */
	@Override
	public EhfPatient getPatientDetails(String patientId) {
		EhfPatient asritPatient=null;
		try 
		{
			asritPatient=(EhfPatient)patientDao.getPatientDetails(patientId);
		} 
		catch (Exception e) 
		{
			GLOGGER.error("Exception Occurred in getPatientDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return asritPatient;
	}
	/**
     * ;
     * @return List<String> complaintList
     * @function This Method is Used For getting Complaints List
     */
	@Override
	public List<String> getComplaints(String mainCompId) {
		List<String> symptomsList=null;
		try
		{
			symptomsList=patientDao.getComplaints(mainCompId);
		}
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getComplaints() in PatientServiceImpl class."+e.getMessage());
		}
		return symptomsList;
	}
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For retrieving Doctors List associated for the specified hospId
     */
	@Override
		public List<LabelBean> getDoctorsList(String doctorType, String hospId,String schemeId) {
		List<LabelBean> doctorsList=null;
		try 
		{
			doctorsList=patientDao.getDoctorsList(doctorType, hospId,schemeId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getDoctorsList() in PatientServiceImpl class."+e.getMessage());
		}
		return doctorsList;
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return String caseId
	 * @function This Method is Used For Registering Patient Case as IP/OP/Chronic OP
	 */
	@Override
	public String saveCaseDetails(PatientVO patientVO)
	{
		String lStrCaseId=null;
		try 
		{
			lStrCaseId = patientDao.saveCaseDetails(patientVO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in saveCaseDetails() in PatientServiceImpl class."+e.getMessage());
		}      
		return lStrCaseId;
		
	}
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	@Override
	public String getHospName(String hospId) {
		String hospitalName=null;
		try
		{
			hospitalName=patientDao.getHospName(hospId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospName() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	@Override
	public String getLocationName(String locId) {
		String locationName=null;
		try
		{
			locationName=patientDao.getLocationName(locId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getLocationName() in PatientServiceImpl class."+e.getMessage());
		}
		return locationName;
	}
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @param String doctorId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For getting Selected Doctor Details
     */
	@Override
	public List<LabelBean> getSelDocDetails(String doctorType, String hospId,String docId,String schemeId)
	{
		List<LabelBean> docDetails=null;
		try
		{
			docDetails=patientDao.getSelDocDetails(doctorType, hospId, docId,schemeId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getSelDocDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return docDetails;
	}
	 /**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
	@Override
	public List<LabelBean> getRelations() {
		List<LabelBean> relationsList=null;
		try
		{
			relationsList=patientDao.getRelations();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelations() in PatientServiceImpl class."+e.getMessage());
		}
		return relationsList;
	}
	 /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
	@Override
	public String getRelationName(String relationId) {
		String relationName=null;
		try
		{
			relationName=patientDao.getRelationName(relationId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelationName() in PatientServiceImpl class."+e.getMessage());
		}
		return relationName;
	}
	/**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
	@Override
	public String getPatientPhoto(String cardNo) {
		String photoUrl=null;
		try
		{
			photoUrl=patientDao.getPatientPhoto(cardNo);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getPatientPhoto() in PatientServiceImpl class."+e.getMessage());
		}
		return photoUrl;
	}
	
	/**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo of journalist
     */
    public String getJournalistPhoto(String cardNo)
	    {
	    	String photoUrl=null;
			try
				{
					photoUrl=patientDao.getJournalistPhoto(cardNo);
				}
			catch(Exception e)
				{
					GLOGGER.error("Exception Occurred in getJournalistPhoto() in PatientServiceImpl class."+e.getMessage());
				}
		return photoUrl;
	    }
	/**
     * ;
     * @param PatientVO patientVO
     * @return int records inserted
     * @function This Method is Used For Saving telephonic information
     */
	@Override
	public int captureTelephonicPatientDtls(PatientVO patientVO) {
		int i=0;
		try {
			i = patientDao.captureTelephonicPatientDtls(patientVO);
		} catch (Exception e) {
			GLOGGER.error("Exception Occurred in captureTelephonicPatientDtls() in PatientServiceImpl class."+e.getMessage());
		}
		return i;
	}
	/**
     * ;
     * @param PatientVO patientVO
     * @return PatientVO patientVO
     * @function This Method is Used For Retrieving telephonic registered details for specified telephonicId
     */
	@Override
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo) {
		PatientVO patientVO = new PatientVO();
		try
		{
		patientVO =patientDao.getTelephonicIntimationDtls(patientVo);
		}
		catch(Exception e)
		{			
			GLOGGER.error("Exception Occurred in getTelephonicIntimationDtls() in PatientServiceImpl class."+e.getMessage());
		}		
		return patientVO;
	}
	/**
     * ;
     * @param String hospId
     * @return List<LabelBean> ICDCategorylist 
     * @function This Method is Used For getting ICD Category List for specific hospital
     */
	@Override
	public List<LabelBean> getTherapyCategory(String lStrhospId) {
		List<LabelBean> catList=null;
		try
		{
			
			catList=patientDao.getTherapyCategory(lStrhospId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getTherapyCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return catList;
	}
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> ICDSubCategorylist 
     * @function This Method is Used For getting ICD Sub Category List for specific category
     */
	@Override
	public List<LabelBean> getTherapySubCategory(String categoryId) {
		List<LabelBean> subCatList=null;
		try
		{
			
			subCatList=patientDao.getTherapySubCategory(categoryId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getTherapySubCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return subCatList;
	}
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> procList 
     * @function This Method is Used For getting ICD Procedure List for specific category
     */
	@Override
	public List<LabelBean> getTherapyProcedure(String subCategoryId) {
		List<LabelBean> procList=null;
		try
		{
			
			procList=patientDao.getTherapyProcedure(subCategoryId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyProcedure() in PatientServiceImpl class."+e.getMessage());
		}
		return procList;
	}
	/**
     * ;
     * @param String occupationId
     * @return String occupationName
     * @function This Method is Used For getting occupationName for given occupationId from EhfmOccupationMst table
     */
	@Override
	public String getOccupationName(String occupationId) {
		String relationName=null;
		try
		{
			relationName=patientDao.getOccupationName(occupationId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOccupationName() in PatientServiceImpl class."+e.getMessage());
		}
		return relationName;
	}
	/**
     * ;
     * @return List<LabelBean> diagnosisTypesList
     * @function This Method is Used For getting Diagnosis Types List(EHFM_DIAGNOSIS_MST--EhfmDiagnosisMst)
     */
	@Override
	public List<LabelBean> getDiagnosisTypes() {
		List<LabelBean> diagnosisList=null;
		try
		{
			diagnosisList=patientDao.getDiagnosisTypes();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnosisTypes() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisList;
	}
	/**
     * ;
     * @param String diagnosisId
     * @return List<LabelBean> diagnosisMainCategoryList
     * @function This Method is Used For getting Diagnosis Main Category List(EHFM_DIA_MAINCAT_MST--EhfmDiagMainCatMst)
     */
	@Override
	public List<LabelBean> getDiagnMainCategory(String diagnosisId) {
		 
		List<LabelBean> diagnosisMainCatList=null;
		try
		{
			diagnosisMainCatList=patientDao.getDiagnMainCategory(diagnosisId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnMainCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisMainCatList;
	}
	/**
     * ;
     * @param String mainCategoryId
     * @return List<LabelBean> diagnosisCategoryList
     * @function This Method is Used For getting Diagnosis Category List(EHFM_DIA_CATEGORY_MST--EhfmDiagCategoryMst)
     */
	@Override
	public List<LabelBean> getDiagnCategory(String mainCategoryId) {
		
		List<LabelBean> diagnosisCatList=null;
		try
		{
			diagnosisCatList=patientDao.getDiagnCategory(mainCategoryId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisCatList;
	}
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> diagnosisSubCategoryList
     * @function This Method is Used For getting Diagnosis Sub Category List(EHFM_DIA_SUBCAT_MST--EhfmDiagSubCatMst)
     */
	@Override
	public List<LabelBean> getDiagnSubCategory(String categoryId) {
		List<LabelBean> diagnosisSubCatList=null;
		List<LabelBean> diagnosisSubCatList1=new ArrayList<LabelBean>();
		try
		{
			diagnosisSubCatList=patientDao.getDiagnSubCategory(categoryId);
			for (LabelBean labelBean: diagnosisSubCatList) {
                if (labelBean.getID() != null && labelBean.getVALUE() != null)
                {
                    LabelBean labelBean1=new LabelBean();
                    labelBean1.setID(labelBean.getID() + "~" + labelBean.getVALUE());
                    diagnosisSubCatList1.add(labelBean1);
                }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnSubCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisSubCatList1;
	}
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDiseaseList
     * @function This Method is Used For getting Diagnosis Disease List(EHFM_DIA_DISEASE_MST--EhfmDiagDiseaseMst)
     */
	@Override
	public List<LabelBean> getDiagnDisease(String code, String param) {
		List<LabelBean> diseaseList=null;
		List<LabelBean> diseaseList1=new ArrayList<LabelBean>();
		try
		{
			diseaseList=patientDao.getDiagnDisease(code,param);
			for (LabelBean labelBean: diseaseList) {
                if (labelBean.getID() != null && labelBean.getVALUE() != null)
                {
                    LabelBean labelBean1=new LabelBean();
                    labelBean1.setID(labelBean.getID() + "~" + labelBean.getVALUE());
                    diseaseList1.add(labelBean1);
                }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getDiagnDisease() in PatientServiceImpl class."+e.getMessage());
		}
		return diseaseList1;
	}
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDisAnatomicalList
     * @function This Method is Used For getting Diagnosis Disease Anatomical List(EHFM_DIA_DISANATOMICAL_MST--EhfmDiagDisAnatomicalMst)
     */
	@Override
	public List<LabelBean> getDiagnDisAnatomical(String code, String param) {
		List<LabelBean> disAnatomicalList=null;
		List<LabelBean> disAnatomicalList1=new ArrayList<LabelBean>();
		try
		{
			disAnatomicalList=patientDao.getDiagnDisAnatomical(code, param);
			for (LabelBean labelBean: disAnatomicalList) {
                if (labelBean.getID() != null && labelBean.getVALUE() != null)
                {
                    LabelBean labelBean1=new LabelBean();
                    labelBean1.setID(labelBean.getID() + "~" + labelBean.getVALUE());
                    disAnatomicalList1.add(labelBean1);
                }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnDisAnatomical() in PatientServiceImpl class."+e.getMessage());
		}
		return disAnatomicalList1;
	}
	/**
     * ;
     * @return List<LabelBean> mainComplaintList
     * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
     */
	@Override
	public List<LabelBean> getMainComplaintLst() {
		List<LabelBean> mainComplaintList=null;
		try
		{
			mainComplaintList=patientDao.getMainComplaintLst();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientServiceImpl class."+e.getMessage());
		}
		return mainComplaintList;
	}
	/**
     * ;
     * @param String mainSymptomCode
     * @param String subSymptomCode
     * @return List<String> symptomList
     * @function This Method is Used For retrieving Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	@Override
	public List<LabelBean> getDentalMainComplaintLst() {
		List<LabelBean> mainComplaintList=null;
		try
		{
			mainComplaintList=patientDao.getDentalMainComplaintLst();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientServiceImpl class."+e.getMessage());
		}
		return mainComplaintList;
	}
	public List<String> getSymptomLists(String mainSymptomCode, String subSymptomCode) {
		List<String> mainSymptomList=null;
		try
		{
			mainSymptomList=patientDao.getSymptomList(mainSymptomCode,subSymptomCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientServiceImpl class."+e.getMessage());
		}
		return mainSymptomList;
	}
	/**
     * ;
     * @param String mainSymptomCode
     * @return List<String> subSymptomList
     * @function This Method is Used For retrieving Sub Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	@Override
	public List<String> getSubSymptomLists(String mainSymptomCode) {
		List<String> subSymptomList=null;
		try
		{
			subSymptomList=patientDao.getSubSymptomList(mainSymptomCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getSubSymptomLists() in PatientServiceImpl class."+e.getMessage());
		}
		return subSymptomList;
	}
	/**
     * ;
     * @param String icdCatCode
     * @param String asriCatCode
     * @param String hospId
     * @return List<String> procedureList
     * @function This Method is Used For retrieving Icd procedure List(EHFM_MAIN_THERAPY--EhfmTherapyProcMst)
     */
	@Override
	public List<String> getIcdProcList(String icdCatCode,String asriCatCode,String hospId,String state,String hosType) {
		List<String> procedureList=null;
		try
		{
			procedureList=patientDao.getIcdProcList(icdCatCode,asriCatCode,hospId,state,hosType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getIcdProcList() in PatientServiceImpl class."+e.getMessage());
		}
		return procedureList;
	}
	/**
     * ;
     * @param String drugTypeCode
     * @return List<String> drugSubTypeList
     * @function This Method is Used For getting Drug Sub Type List From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	@Override
	public List<String> getDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getDrugSubList(drugTypeCode);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	@Override
	public String getDrugDetails(String drugCode) {
		String drugDetails=null;
		try
		{
			drugDetails=patientDao.getDrugDetails(drugCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return drugDetails;
	}
	/**
     * ;
     * @param String icdProcCode
     * @return String procCode
     * @function This Method is Used For getting Procedure Codes From EhfmTherapyProcMst(EHFM_THERAPY_PROC_MST )
     */
	@Override
	public String getTherapyProcCodes(String icdProcCode) {
		String procCode=null;
		try
		{
			procCode=patientDao.getTherapyProcCodes(icdProcCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyProcCodes() in PatientServiceImpl class."+e.getMessage());
		}
		return procCode;
	}
	/**
     * ;
     * @return List<LabelBean> opCategoryList
     * @function This Method is Used For getting Therapy OP Category Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	@Override
	public List<LabelBean> getTherapyOPCategory() {
		List<LabelBean> opCategoryList=null;
		try
		{
			opCategoryList=patientDao.getTherapyOPCategory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyOPCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return opCategoryList;
	}
	/**
     * ;
     * @param opMainCode
     * @return List<LabelBean> opPkgList
     * @function This Method is Used For getting Therapy OP Package Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	@Override
	public List<String> getOpPkgList(String opCatCode) {
		List<String> pkgList1 = new ArrayList<String>();
		List<LabelBean> pkgList=null;
		try
		{
			pkgList=patientDao.getOpPkgList(opCatCode);
			 for (LabelBean labelBean: pkgList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (pkgList1 != null && pkgList1.size() > 0) {
	                    	pkgList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	pkgList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getOpPkgList() in PatientServiceImpl class."+e.getMessage());
		}
		return pkgList1;
	}
	/**
     * ;
     * @param opPkgCode
     * @return List<LabelBean> opIcdList
     * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	@Override
	public List<String> getOpIcdList(String opPkgCode) {
		List<String> icdList1 = new ArrayList<String>();
		List<LabelBean> icdList=null;
		try
		{
			icdList=patientDao.getOpIcdList(opPkgCode);
			 for (LabelBean labelBean: icdList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (icdList1 != null && icdList1.size() > 0) {
	                    	icdList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	icdList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOpPkgList() in PatientServiceImpl class."+e.getMessage());
		}
		return icdList1;
	}
	/**
     * ;
     * @param cardNo
     * @param opCatCode
     * @return int count
     * @function This Method is Used For getting count of cases with given cardNo and therapyOPCategory whose medication period is not completed
     */
	@Override
	public int validateTherapyOPCat(String cardNo, String opCatCode) {
		int count=0;
		try
		{
			count=patientDao.validateTherapyOPCat(cardNo, opCatCode);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return count;
	}
	
	
	
	@Override
	public List<DrugsVO> getChronicDetails(String cardNo) {
		List<DrugsVO> drugsList=null;
		try
		{
			drugsList=patientDao.getChronicDetails(cardNo);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return drugsList;
	}
	@Override
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId) {
		PreauthVO preauthVO = patientDao.getPatientFullDtls(lStrCaseId,patientId);
		return preauthVO;
	}
	@Override
	public String deleteInvestigations(String caseId, String procCode,String investId,String asriCode) {
		String result="false";
		try
		{
			result=patientDao.deleteInvestigations(caseId,procCode,investId,asriCode);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return result;
	}
	@Override
	public String deleteGenInvest(String patientId, String investId) {
		String result="false";
		try
		{
			result=patientDao.deleteGenInvest(patientId,investId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return result;
	}
	@Override
	public List<String> getInvestigations(String lStrBlockId) {
		List<String> mainInvestList=null;
		try
		{
			mainInvestList=patientDao.getInvestigations(lStrBlockId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getInvestigations() in PatientServiceImpl class."+e.getMessage());
		}
		return mainInvestList;
	}
	@Override
	public List<String> getOpDrugSubList(String drugTypeCode ,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getOpDrugSubList(drugTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getOpPharSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getOpPharSubList(drugTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getOpChemSubList(String pharSubCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getOpChemSubList(pharSubCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getChemSubList(String cSubGrpCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getChemSubList(cSubGrpCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getRouteList(String routeTypeCode,String pStrIpOpType) {
		List<LabelBean> routeList=null;
		List<String> routeList1 = new ArrayList<String>();
		try
		{
			routeList=patientDao.getRouteList(routeTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: routeList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (routeList1 != null && routeList1.size() > 0) {
	                    	routeList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	routeList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return routeList1;
	}
	@Override
	public List<String> getStrengthList(String strengthTypeCode,String pStrIpOpType) {
		List<LabelBean> strengthList=null;
		List<String> strengthList1 = new ArrayList<String>();
		try
		{
			strengthList=patientDao.getStrengthList(strengthTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: strengthList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (strengthList1 != null && strengthList1.size() > 0) {
	                    	strengthList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	strengthList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getStrengthList() in PatientServiceImpl class."+e.getMessage());
		}
		return strengthList1;
	}
	
	@Override
	public String getSequence(String pStrSeqName)throws Exception{
		String seqId = patientDao.getSequence(pStrSeqName);
		return seqId;
	}
	@Override
	public List<CommonDtlsVO> getDtrsFormDtls(String caseId) throws Exception {
		List<CommonDtlsVO> result=null;
		try
		{
			result=patientDao.getDtrsFormDtls(caseId);
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in getDtrsFormDtls() in PatientServiceImpl class."+e.getMessage());
		}
		return result;
	}
	@Override
	public PatientVO getOPCases(HashMap<String, String> searchMap)
			{
		PatientVO registeredPatientsList=null;
		registeredPatientsList=patientDao.getOPCases(searchMap);
		return registeredPatientsList;
	}
	@Override
	public String getDutyDoctorById(String regNo) {
		return patientDao.getDutyDoctorById(regNo);
	}
	@Override
	public String getProcedureType(String procCode,String asriCode) {
		String procedureType=null;
		try
		{
			procedureType=patientDao.getProcedureType(procCode, asriCode);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getProcedureType() in PatientServiceImpl class."+e.getMessage());
		}
			return procedureType;
	}
	
	
	/**
	 * ;
	 * @param caseId
	 * @return List<CommonDtlsVO> caseDetailsList
	 	 * @function This Method is Used For common details for the caseId specified
	 */
	@Override
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId)
	{
		List<CommonDtlsVO> commonDtls=null;
		try {
			commonDtls = patientDao.getPatientCommonDtls(caseId);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getPatientCommonDtls() in PatientServiceImpl class."+e.getMessage());	
		}
		return commonDtls;		
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<LabelBean> docAavilList
	 * @function This Method is Used For getting available doctor list for given hospital and specialty
	 */
	@Override
	public List<LabelBean> getDocAvailLst(PatientVO patientVO) {
		List<LabelBean> docAvailList=null;
		try {
			docAvailList = patientDao.getDocAvailLst(patientVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getDocAvailLst() in PatientServiceImpl class."+e.getMessage());	
		}
		return docAvailList;	
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<String> specInvestList
	 * @function This Method is Used For getting special investigation list for given therapy
	 */
	@Override
	public List<String> getSpecialInvestigationLst(PatientVO patientVO) {
		List<String> specInvestList=null;
		try {
			specInvestList = patientDao.getSpecialInvestigationLst(patientVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getSpecialInvestigationLst() in PatientServiceImpl class."+e.getMessage());	
		}
		return specInvestList;	
	}
	@Override
	public List<PreauthVO> getcaseSurgeryDtls(String caseId)  
	{
		List<PreauthVO> lstSurgerydlts = null;
		try{
			lstSurgerydlts=patientDao.getcaseSurgeryDtls(caseId);
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return lstSurgerydlts;
	}
	@Override
	public List<LabelBean> getSymptomsDtls(String lStrCaseId) 
	{
		List<LabelBean> lstSymptomDlts = null;
		try{
			lstSymptomDlts=patientDao.getSymptomsDtls(lStrCaseId);
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return lstSymptomDlts;
	}
	@Override
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId)
	{
		PreauthVO PreauthVO = patientDao.getPatientOpDtls(pStrCaseId, pStrPatientId);
		return PreauthVO;
	}
	@Override
	public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId)
	{
		CommonDtlsVO commonDtlsVO=null;
		try
		{
			commonDtlsVO = patientDao.getOtherDtls(pStrCaseId, pStrPatId);
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return commonDtlsVO;
	}

	/*
	 * Added by Srikalyan to get the Biometric Details for corresponding
	 * Registered Patient
	 */
	@Override
	public PatientVO getBiomDtls(CommonDtlsVO commonDtlsVO) {
		PatientVO patientVO = null;
		try {
			patientVO = patientDao.getBiomDtls(commonDtlsVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientVO;
	}
	
	@Override
	public String getEmpNameById(String id)
	{
		return patientDao.getEmpNameById(id);
	}
	@Override
	public String getDoctorById(String id) {
		return patientDao.getDoctorById(id);
	}
	@Override
	public List<DrugsVO> getDrugs(String pStrPatId,String flag)
	{
		List<DrugsVO> drugsList=null;
		try
		{
			drugsList=patientDao.getDrugs(pStrPatId, flag);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return drugsList;
	}
	
	public String getHospActiveStatus(String userId, String roleId)
	{
		String actStatus="";
		try
		{
			actStatus=patientDao.getHospActiveStatus(userId, roleId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Error in method getHospActiveStatus() of PatientServiceimpl "+e.getMessage());
		}
		return actStatus;
	}
	
	
	/**
     * @param String icdProcCode
     * @return int dentalUnits
     * @function This Method is implementation For getting Dental Units for given Procedure
     */
	@Override
	public int getDenUnitsList(String icdProcCode)
	{
		int denUnitsList=0;
		try
		{
			denUnitsList=patientDao.getDenUnitsList(icdProcCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return denUnitsList;
	}
	/*
	 * Added by Srikalyan to Verify New Born
	 * Baby Condition for Mithra based on 
	 * the scheme
	 */
	public String checkNewBornCond(String lStrUserId)
		{
			String cond="N";
			cond=patientDao.checkNewBornCond(lStrUserId);
			return cond; 
		}
	
	public String getInvestPrice(String blockId,String investId)
	{
		String price=null;
		price=patientDao.getInvestPrice( blockId, investId);
		return price;
	}
	
	
	
		
		public String getDaysActiveOP(String caseId)
		{
			String msg=null;
			msg=patientDao.getDaysActiveOP(caseId);
			return msg;
			
		}
		
		public List<LabelBean> getConsultDtls(String patientId)
		{
			List<LabelBean> consulLst = new ArrayList<LabelBean>();
			consulLst=patientDao.getConsultDtls(patientId);
			return consulLst;
			
		}
		
		public List<CasesSearchVO> getOpPastHistoryDetails(CasesSearchVO vo)
		{
			List<CasesSearchVO> lstCase=new ArrayList<CasesSearchVO>();
			lstCase=patientDao.getOpPastHistoryDetails(vo);
			return lstCase;
		}
		
		public String getEmpCode(String PatientId)
		{
			String empCode=null;
			empCode=patientDao.getEmpCode(PatientId);
			return empCode;
		}
		
/*Added by venkatesh for chronic op migration*/
		public String saveChronicCaseDetails(PatientVO patientVO) throws Exception 
		{
			String chronicNo=null;
			chronicNo=patientDao.saveChronicCaseDetails(patientVO);
			return chronicNo;
		}
		public EhfmOpDrugMst getopdrugDataAuto(String chemicalCode)
		{
			EhfmOpDrugMst ehfmOpDrugMst=new EhfmOpDrugMst();
			
			ehfmOpDrugMst=patientDao. getopdrugDataAuto(chemicalCode);
			return ehfmOpDrugMst;
			
			
		}
/*
 * Added by Srikalyan to Verify and get
 * Dental Conditions for TG Patients 
 */
@Override
public PreauthVO validateAndGetDentalCond(PreauthVO preauthVO)
	{
		PreauthVO preVO=new PreauthVO();
		try
			{
				StringBuffer query=new StringBuffer();
				query.append( " select caseId as caseId from EhfCase where casePatientNo = '"+preauthVO.getPatientID()+"' " );
				
				String classPath="com.ahct.patient.vo.PreauthVO",caseId=null;
				Map<String ,Object> resMap=null;
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				if(resMap!=null)
					{
						PreauthVO returnVO=returnVOFromMap(resMap,classPath);
						if(returnVO !=null && returnVO.getCaseId()!=null)
							caseId=returnVO.getCaseId();
					}
				
				if(preauthVO!=null)
					{
						if(preauthVO.getIcdProcCode()!=null && !"".equalsIgnoreCase(preauthVO.getIcdProcCode())
							&& preauthVO.getPatientID()!=null && !"".equalsIgnoreCase(preauthVO.getPatientID())
							&& preauthVO.getScheme()!=null &&!"".equalsIgnoreCase(preauthVO.getScheme()))
						{
							String icdProcCode=preauthVO.getIcdProcCode();
							String schemeId=preauthVO.getScheme();
							
							PreauthVO resultVO=getDentalCondDB(icdProcCode,schemeId);
							if(resultVO!=null)
								{
									preVO=resultVO;
									String ageMsg=null,unitsVO=null;
									if(resultVO.getAgeLimit()!=null && !"".equalsIgnoreCase(resultVO.getAgeLimit())
											&& !"-1".equalsIgnoreCase(resultVO.getAgeLimit()))
										{
											//Method to get the Required Message for age condition
											ageMsg = getAgeMsg(Integer.parseInt(resultVO.getAgeLimit()),preauthVO.getPatientID());
											preVO.setAgeMsg(ageMsg);
										}
									if(resultVO.getLifetimeUnitsLimit()!=null && !"".equalsIgnoreCase(resultVO.getLifetimeUnitsLimit()))
										{
											//Method to get the Number of Units Left
											if(resultVO.getLifetimeUnitsLimit().equalsIgnoreCase("-1"))
												unitsVO="-1";
											else
												unitsVO=calculateUnitsLeft(icdProcCode,resultVO.getLifetimeUnitsLimit(),resultVO.getLifeTimeMonths(),preauthVO.getPatientID(),preauthVO.getTestKnown(),caseId);
											
											preVO.setActualUnitsLeft(unitsVO);
										}
									if(resultVO.getComboProcCode()!=null && !"".equalsIgnoreCase(resultVO.getComboProcCode())
											&& !"NA".equalsIgnoreCase(resultVO.getComboProcCode()))
										{
											String comboProcs=getComboNonComProcs(resultVO.getComboProcCode(),preauthVO.getScheme());
											preVO.setComboProcNames(comboProcs);
										}
									if(resultVO.getNonComboProcCode()!=null && !"".equalsIgnoreCase(resultVO.getNonComboProcCode())
											&& !"NA".equalsIgnoreCase(resultVO.getNonComboProcCode()))
										{
											String nonComboProcs=getComboNonComProcs(resultVO.getNonComboProcCode(),preauthVO.getScheme());
											preVO.setComboNonProcNames(nonComboProcs);
										}
									if(resultVO.getStandaloneProc()!=null && !"".equalsIgnoreCase(resultVO.getStandaloneProc())
											&&  !"NA".equalsIgnoreCase(resultVO.getStandaloneProc()))
										{
											String standAloneProcs=getComboNonComProcs(resultVO.getStandaloneProc(),preauthVO.getScheme());
											preVO.setStandaloneProcNames(standAloneProcs);
										}
								}
						}
				}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in validateAndGetDentalCond() in PatientServiceImpl class."+e.getMessage());
				return preVO;
			}
	return preVO;
}	
		public EhfOpConsultData getOpDocDtls(String patientId)
		
		{
			
			EhfOpConsultData ehfOpConsultData=new EhfOpConsultData();
			
			ehfOpConsultData=patientDao.getOpDocDtls(patientId);
			return ehfOpConsultData;
		}
		
		public LabelBean getDiagnosisDtlsAuto(String disCode) throws Exception
		{
			LabelBean diagDtl=new LabelBean();
			diagDtl=patientDao.getDiagnosisDtlsAuto(disCode);
			return diagDtl;
		}
		public List<LabelBean> getDiagList()
		{
			List<LabelBean> diagList=new ArrayList<LabelBean>();
			diagList=patientDao.getDiagList();
			return diagList;
		}
		
		public List<LabelBean> getDiagListSearch(String data,String type) throws Exception
		{
			List<LabelBean> diagLst=patientDao.getDiagListSearch(data,type);
			return diagLst;
		}




		/*
		 * Added by Srikalyan to get the Submit
		 * Drug Details of Pharmacy Case
		 */
		@Override
		public String submitPharmacyCase(String caseId,String patId,String drugDtls)
			{
				String retMsg = null;
				try {
						retMsg = patientDao.submitPharmacyCase(caseId, patId ,drugDtls);
					}
				catch (Exception e) 
					{ 
						e.printStackTrace();
					}
				return retMsg;
			}

		
		




/*
 * Added by Srikalyan to get Dental Conditions for TG Patients
 */
private PreauthVO getDentalCondDB(String icdProcCode,String schemeId)
	{
		PreauthVO returnVO=new PreauthVO(); 
		Map<String ,Object> resMap=null;
		StringBuffer query=new StringBuffer();
		
		try
			{
				query.append( " select edp.ageLimit as ageLimit , edp.comoboProcCode as comboProcCode, ");
				query.append( " edp.frameworkPrice as frameworkPrice , edp.ipOp as ipOp, ");
				query.append( " edp.lifetimeUnitsLimit as lifetimeUnitsLimit , edp.standaloneProc as standaloneProc, ");
				query.append( " edp.subsequentPrice as subsequentPrice , edp.unitsLimit as unitsLimit, ");
				query.append( " edp.id.icdProcCode as icdProcCode , edp.id.schemeId as scheme , edp.activeYn as activeYn , ");
				query.append( " edp.nonComboProcCode as nonComboProcCode , edp.lifeTimeMonths as lifeTimeMonths " );
				
				query.append( " from EhfmDentalProcCriteria edp  ");
				query.append( " where edp.id.icdProcCode = '"+icdProcCode+"' ");
				query.append( " and edp.id.schemeId = '"+schemeId+"' and edp.activeYn='Y' ");
				
				String classPath="com.ahct.patient.vo.PreauthVO";
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				if(resMap!=null)
					returnVO=returnVOFromMap(resMap,classPath);
				
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getDentalCondDB() in PatientServiceImpl class."+e.getMessage());
				return returnVO;
			}
		
		return returnVO;

		


	}
	public String checkDentalClinic(String userid , String patientId)
		{
			return patientDao.checkDentalClinic(userid , patientId);
			
		}
	public String checkGovernmentHosp(String userid , String patientId)
	{
		return patientDao.checkGovernmentHosp(userid , patientId);
		
	}
	public String checkGovernmentHosp(String userid )
	{
		return patientDao.checkGovernmentHosp(userid );
		
	}
/*
 * Added by Srikalyan to get Age Message in Dental Conditions for TG Patients
 */
private String getAgeMsg(int ageLimit,String patientID)
	{
		String ageMsg=null;
		try
			{
				Map<String ,Object> resMapNew=null;
				String classPath="com.ahct.model.EhfPatient";
				Map<String,Object> resMap=new HashMap<String,Object>();
				resMap.put("classPath",classPath);
				resMap.put("ID",patientID);
				resMapNew=patientDao.executeIDClass(resMap);
				
				if(resMapNew!=null)
					{
						if(resMapNew.get(classPath)!=null)
							{
								EhfPatient ehfPatient=(EhfPatient)resMapNew.get(classPath);
								if(ehfPatient!=null)
									{
										if(ehfPatient.getAge()!=null)
											{
												if(ehfPatient.getAge().intValue()>ageLimit && ageLimit!=-1)
													{
														ageMsg="Age Limit for the selected Procedure is "+ageLimit+" Years.Hence current Procedure cannot be selected as the Patient's age is "+ehfPatient.getAge()+".";
													}
												else
													ageMsg=null;
											}
									}
							}
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getAgeMsg() in PatientServiceImpl class."+e.getMessage());
				return ageMsg;
			}
		return ageMsg;
	}



/*
 * Added by Srikalyan to get Life Time Units Left
 * in Dental Conditions for TG Patients
 */
private String calculateUnitsLeft(String icdProcCode,String lifeTimeUnits , String lifeTimeMnths , String patientId,String deleteProc,String caseId)
	{
		String unitsLeft=null,unitsDone="0";
		try
			{
				StringBuffer query=new StringBuffer();
				PreauthVO returnVO=new PreauthVO(); 
				if(icdProcCode!=null && !"".equalsIgnoreCase(icdProcCode))
					{
						query.append( " select sum(c.surgProcUnits) as opProcUnits " ); 
						query.append( " from EhfPatient a , EhfCase b , " );
						query.append( " EhfCaseTherapy c , EhfPatient d " );
						query.append( " where a.patientId  = '"+patientId+"' " );
						query.append( " and d.patientId = b.casePatientNo and b.caseId = c.caseId " );
						query.append( " and d.cardNo = a.cardNo  " );
						query.append( " and c.activeyn = 'Y' ");
						query.append( " and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~', ',')+") ");
						query.append( " and c.icdProcCode = '"+icdProcCode+"' " );
						
						if(deleteProc!=null && "Y".equalsIgnoreCase(deleteProc))
							{
								query.append( " and (case when c.caseId = '"+caseId+"' and ");
								query.append( " c.id.icdProcCode = '"+icdProcCode+"' then 0 else 1 end=1)");
							}
						
						if(lifeTimeMnths!=null && !"".equalsIgnoreCase(lifeTimeMnths) && !"-1".equalsIgnoreCase(lifeTimeMnths))
							query.append( " and c.crtDt > add_months(sysdate , -"+lifeTimeMnths+") " );
						
						Map <String , Object > resMap = new HashMap <String ,Object> ();
						String classPath="com.ahct.patient.vo.PreauthVO";
						resMap=patientDao.executeNormalQuery(classPath,query.toString());
						
						if(resMap!=null)
							{	
								returnVO=returnVOFromMap(resMap,classPath);
								if(returnVO!=null)
									{
										if(returnVO.getOpProcUnits()!=null && !"".equalsIgnoreCase(returnVO.getOpProcUnits()))
											unitsDone=returnVO.getOpProcUnits();
											
										if( lifeTimeUnits!=null && unitsDone!=null)
											{
												if(Integer.parseInt(lifeTimeUnits) > Integer.parseInt(unitsDone))
													unitsLeft=Integer.toString(Integer.parseInt(lifeTimeUnits)-Integer.parseInt(unitsDone));
												else
													unitsLeft="0";
											}	
									}	
							}		
								
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in calculateUnitsLeft() in PatientServiceImpl class."+e.getMessage());
				return unitsLeft;
			}
		return unitsLeft;
	}


/*
 * Added by Srikalyan to get Names of Combo and Non Combo 
 * Procedures in Dental Conditions for TG Patients
 */
private String getComboNonComProcs(String icdProcCodes,String schemeId)
	{
		String comboCodes[]=null,comboNames=null,cond=null;
		try
			{
				if(icdProcCodes!=null && !"".equalsIgnoreCase(icdProcCodes) && schemeId!=null && !"".equalsIgnoreCase(schemeId))
					{
						if(icdProcCodes.contains("~"))
							{
								comboCodes=icdProcCodes.split("~");
							}
						else
							{
								comboCodes=new String[1];
								comboCodes[0]=icdProcCodes;
							}
						
						if(comboCodes.length>0)
							{
								int count=0;
								String orderCond="";
								for(int i=0;i<comboCodes.length;i++)
									{
										if(cond==null)
											cond="'"+comboCodes[i]+"'";
										else
											cond=cond+",'"+comboCodes[i]+"'";
										
										orderCond = orderCond + " when a.id.icdProcCode='"+comboCodes[i]+"' then "+(++count)+"";
									}
								StringBuffer query=new StringBuffer();
								query.append(" select a.procName as procName ");
								query.append(" from EhfmTherapyProcMst a " );
								query.append(" where a.id.icdProcCode in ( "+cond+" ) ");
								query.append(" and a.id.state ='"+schemeId+"' ");
								
								if(count>0)
									query.append(" order by case "+orderCond+" else 0 end ");
								
								Map <String , Object > resMap = new HashMap <String ,Object> ();
								String classPath="com.ahct.patient.vo.PreauthVO";
								resMap=patientDao.executeNormalQuery(classPath,query.toString());
								
								List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
								if(localVOLst!=null)
									{
										if(localVOLst.size()>0)
											{
												for(PreauthVO localVO:localVOLst)
													{
														if(localVO.getProcName()!=null && !"".equalsIgnoreCase(localVO.getProcName()))
															{
																if(comboNames!=null)
																	comboNames=comboNames+"~"+localVO.getProcName();
																else
																	comboNames=localVO.getProcName();
															}
													}
											}
									}
							}
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getComboNonComProcs() in PatientServiceImpl class."+e.getMessage());
				return comboNames;
			}
		return comboNames; 
	}
/*
 * Added by Srikalyan to return preauthVO List from Map 
 */
@SuppressWarnings("unchecked")
private List<PreauthVO> returnVOLst(Map<String,Object> resMap,String classPath)
	{
		List<PreauthVO> returnLst=new ArrayList<PreauthVO>();
		try
			{
				if(resMap!=null)
					{	
						if(resMap.get(classPath)!=null)
							{
								List<PreauthVO> preVOLst=(List<PreauthVO>)resMap.get(classPath);;
								if(preVOLst!=null)
									{
										if(preVOLst.size()>0)
											{
												returnLst=preVOLst;
											}
									}
							}
					}
			}	
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in returnVOLst() in PatientServiceImpl class."+e.getMessage());
				return returnLst;
			}
		return returnLst;
	}


/*
 * Added by Srikalyan to return preauthVO from Map 
 */
@SuppressWarnings("unchecked")
private PreauthVO returnVOFromMap(Map<String,Object> resMap,String classPath)
	{
		PreauthVO returnVO=new PreauthVO();
		try
			{
				if(resMap!=null)
					{	
						if(resMap.get(classPath)!=null)
							{
								List<PreauthVO> preVOLst=(List<PreauthVO>)resMap.get(classPath);;
								if(preVOLst!=null)
									{
										if(preVOLst.size()>0)
											{
												if(preVOLst.get(0)!=null)
													{
														returnVO=preVOLst.get(0);	
													}
											}
									}
							}
					}
			}	
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in returnVOFromMap() in PatientServiceImpl class."+e.getMessage());
				return returnVO;
			}
		return returnVO;
	}

/*
 * Added by Srikalyan to get the Procedure Name 
 * for corresponding Procedure ID
 */
@Override
public PreauthVO getProcName(String procId,String patSchemeId)
	{	
		PreauthVO resultVO =new PreauthVO();
		try
			{
				StringBuffer query=new StringBuffer();
				query.append( " select a.procName as procName " );
				query.append( " from EhfmTherapyProcMst a " );
				query.append( " where a.id.icdProcCode = '"+procId+"' " );
				query.append( " and a.id.state = '"+patSchemeId+"' " );
				query.append( " and a.activeYN = 'Y' " );
				
				Map <String , Object > resMap = new HashMap <String ,Object> ();
				String classPath="com.ahct.patient.vo.PreauthVO";
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				
				resultVO=returnVOFromMap(resMap,classPath);
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getProcName() in PatientServiceImpl class."+e.getMessage());
				return resultVO;
			}
		
		return resultVO;
	}



/***
 * @author Kalyan
 * @return type
 * @function This Method is Used to check Special conditions for TG. 
 */
@Override
public PatientVO getPatientDentalDtls(String patientId) {
	// TODO Auto-generated method stub
	return patientDao.getPatientDentalDtls(patientId);
}	
@Override
public String checkSpecialDenatlCond(PreauthVO preauthVO)
	{
		String returnMsg =null;
		try
			{
				//returnMsg
				StringBuffer query=new StringBuffer();
				query.append( " select c.icdProcCode as icdProcCode , c.crtDt as date ,  " );
				query.append( " e.procName as procName ");
				query.append( " from EhfPatient a , EhfCase b , EhfCaseTherapy c , " );
				query.append( " EhfPatient d , EhfmTherapyProcMst e " );
				query.append( " where a.patientId = '"+preauthVO.getPatientID()+"'  " );
				query.append( " and b.casePatientNo = d.patientId " );
				query.append( " and b.caseId = c.caseId and a.cardNo = d.cardNo and c.activeyn = 'Y' " );
				query.append( " and c.asriCatCode = e.id.asriCode and c.icdProcCode = e.id.icdProcCode " );
				query.append( " and e.id.state = b.schemeId and activeYN = 'Y' " );
				query.append( " and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~',',')+") ");
			
				Map <String , Object > resMap = new HashMap <String ,Object> ();
				String classPath="com.ahct.patient.vo.PreauthVO";
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				
				List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
				if( localVOLst!=null && localVOLst.size() > 0)
					{	
						Calendar cal=Calendar.getInstance();
						cal.add(Calendar.YEAR, -5);
						Date date=cal.getTime();
						for(PreauthVO localVO:localVOLst)
							{
								if(localVO!=null && localVO.getIcdProcCode()!=null
										&& localVO.getDate()!=null
										&& localVO.getIcdProcCode().equalsIgnoreCase(preauthVO.getIcdProcCode()))
									{
										StringBuffer msg =new StringBuffer();
										msg.append( "Patient has already utilized this Procedure.");
										msg.append(localVO.getProcName());
										msg.append("("+preauthVO.getIcdProcCode()+")");
										msg.append(" cannot be selected as this procedure can be availed only once. ");
										if(date.after(localVO.getDate()))
											{
												msg.append("Instead ");
												msg.append(config.getString("DentalStatus_"+localVO.getIcdProcCode()));
												msg.append(" procedure can be availed.");
											}
										returnMsg=msg.toString();
									}
							}
					}
				
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in checkSpecialDenatlCond() in PatientServiceImpl class."+e.getMessage());
				return returnMsg;
			}
		return returnMsg;
	}
@Override
public String checkDentalHospital(String hospCode) {
	// TODO Auto-generated method stub
	
	return patientDao.checkDentalHospital(hospCode);
}
	/***
	 * @author Kalyan
	 * @param Patient ID
	 * @return Follow Up Flag a String
	 * @function This Method is Used to check Dental Follow UP Eligibilty for a Specific Patient. 
	 */
	public List<String> checkDenFlp(String patientID,List<String> therapyList)
		{
			String procCodeCond=null;
			List<String> returnLst = therapyList;
			try
				{
					String[] arr=new String[2];
					if(config.getString("dentalFlpConds")!=null)
						arr=config.getString("dentalFlpConds").split("~");
					
					for(String str:arr)
						{
							if(procCodeCond==null)
								procCodeCond="'"+str+"'";
							else
								procCodeCond+=",'"+str+"'";
						}
					
					StringBuffer query=new StringBuffer();					
					query.append( " select " );
					query.append( "   to_char(nvl(sum(case when c.icdProcCode = '"+arr[0]+"' then 1 else 0 end),0)) as surgCount , " );
					query.append( "   to_char(nvl(sum(case when c.icdProcCode = '"+arr[1]+"' then 1 else 0 end),0)) as flpSurgCount  " );
					query.append( "   from EhfPatient a , EhfCase b , EhfCaseTherapy c " );
					query.append( "   , EhfPatient d " );
					query.append( "   where a.patientId = '"+patientID+"'  " );
					query.append( "   and a.cardNo = d.cardNo and a.schemeId = d.schemeId  " );
					query.append( "   and b.casePatientNo = d.patientId and b.caseId = c.caseId  " );
					query.append( "   and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~',',')+") ");
					query.append( "   and c.activeyn = 'Y' and c.icdProcCode in ("+procCodeCond+") " );
					query.append( "   and d.patientId not in ('"+patientID+"') ");
					query.append( "   and case when c.icdProcCode = '"+arr[0]+"' and b.csDisDt < (sysdate-11) " );
					query.append( " 		   then 1	" );
					query.append( "            when c.icdProcCode = '"+arr[1]+"' " );
					query.append( " 		   then 1	" );			
					query.append( " 		   else 0 ");
					query.append( "       end = 1 ");
					
					
					Map <String , Object > resMap = new HashMap <String ,Object> ();
					String classPath="com.ahct.patient.vo.PreauthVO";
					resMap=patientDao.executeNormalQuery(classPath,query.toString());
					
					List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
					if( localVOLst!=null && localVOLst.size()>0 && localVOLst.get(0)!=null )
						{
							/**Only Initial Proc utilized and Follow Up Proc need to be utilized then List will be sent Unmodified 
							   1.Conditions Surgery Count Should be greater than 0.
							   2.Follow Up Surgery Count should be 0 as this should be performed only once*/
							if(localVOLst.get(0).getSurgCount() !=null && !"".equalsIgnoreCase(localVOLst.get(0).getSurgCount())
											&& (Integer.parseInt(localVOLst.get(0).getSurgCount()) > 0)
									&& localVOLst.get(0).getFlpSurgCount() !=null && !"".equalsIgnoreCase(localVOLst.get(0).getFlpSurgCount())
											&& (Integer.parseInt(localVOLst.get(0).getFlpSurgCount()) == 0))
								{
									returnLst = therapyList;
								}
							/**If both Initial Proc and Follow Up Proc are utilized or
							   Both Initial Proc and Follow Up Proc are not utilized 
							   and for all the other conditions the list will be modified 
							   */
							else
								{
									for(int i=0;i<therapyList.size();i++)
										{
											PreauthVO vo=getProcName(arr[1],config.getString("Scheme.TG.State"));
											if(vo!=null && vo.getProcName()!=null)
												{
													if(therapyList.get(i).contains(arr[1]))
														{
															if(therapyList.get(i).contains(arr[1]+"~"+vo.getProcName()+"@"))
																{
																	therapyList.remove(i);
																	//break;
																	//Here This Loop is not breaked believing the possiblity of occurance of multiple Similar Procs. 
																}	
														}
												}
										}
									returnLst = therapyList;
								}
							
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					GLOGGER.error("Exception Occurred in checkSpecialDenatlCond() in PatientServiceImpl class."+e.getMessage());
					return returnLst;
				}
			
			return returnLst;
		}
	@Override
	public List<LabelBean> getdentalexaminationDtls(String prntId) {
		// TODO Auto-generated method stub
		return patientDao.getdentalexaminationDtls(prntId);
		
	}
	public String getTreatmentDntlvalue(String caseId) {
		// TODO Auto-generated method stub
		String dentalvalue = patientDao.getTreatmentDntlvalue(caseId);
		return dentalvalue;
	}
	@Override
	public List<LabelBean> getCatName(String lStrCaseId) {
		List<LabelBean> catList=null;
		try
		{
			catList = patientDao.getCatName(lStrCaseId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return catList;
	}
	@Override
	public LabelBean getHospStatus(String cardNo, String hospId)
	{
		LabelBean catList=null;
		try
		{
			catList = patientDao.getHospStatus(cardNo,hospId);
		}
	catch (Exception e) {
		GLOGGER.error("Exception Occurred in getHospStatus() in PatientServiceImpl class."+e.getMessage());
	}
	return catList;
	}
	@Override
	public String checkHubSpoke(String userIDu) {
		// TODO Auto-generated method stub
		
		return patientDao.checkHubSpoke(userIDu);
	}
	@Override
	public String getHubSpokeHospId(String userIDu,String groupId) {
		// TODO Auto-generated method stub
		return patientDao.getHubSpokeHospId(userIDu,groupId);
	}
	
	//Start CR#4511-SriHari-10/10/24
	@Override
	public Map<String,Object> saveProformaFormHighEnd(PatientForm form) throws Exception {
		return patientDao.saveProformaFormHighEnd(form);
	}
	@Override
	public Map<String, Object> getProformaFormHighEnd(Map<String, Object> lResMap) throws Exception {
		return patientDao.getProformaFormHighEnd(lResMap);
	}
	@Override
	public List<LabelBean> getProformaHighEndApproval(Map<String, Object> lResMap) throws Exception {
		return patientDao.getProformaHighEndApproval(lResMap);
	}
	@Override
	public List<LabelBean> getOpClaimCasesList(HashMap<String, String> hashMap) {
		List<LabelBean> resultList = new ArrayList<LabelBean>();
		try{
			resultList = patientDao.getOpClaimCasesList(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultList;
	}
	@Override
	public List<LabelBean> getOpClaimBillDtls(HashMap hashMap) {
		List<LabelBean> resultList = new ArrayList<LabelBean>();
		try{
			resultList = patientDao.getOpClaimBillDtls(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultList;
	}
	@Override
	public String updateMedcoOpClaim(HashMap hashMap) {
		String result = null;
		try{
			result = patientDao.updateMedcoOpClaim(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public String getClaimStatus(String patientId) {//Chandana -7845- new method for getting the status of the claim
		String result = null;
		try{
			result = patientDao.getClaimStatus(patientId);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public String getNextStatus(String currentStatus, String actiondone) {//Chandana -7845- new method for getting the next status from workflow
		String result = null;
		try{
			result = patientDao.getNextStatus(currentStatus,actiondone);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public String checkOpClaimNo(String patientId) {
		return patientDao.checkOpClaimNo(patientId);
	}
	@Override
	public List<String> getNimsOpClaimCases(String status, String roleId, String userId) {//Chandana - 8602 - Added userid in the existing method
		List<String> resList = new ArrayList<String>();
		try{
			resList = patientDao.getNimsOpClaimCases(status,roleId,userId);//Chandana - 8602 - Added userid in the existing method
		}catch(Exception le){
			le.printStackTrace();
		}
		return resList;
		}
	@Override
	public String updateCHOpClaim(HashMap hashMap) {
		String result = null;
		try{
			result = patientDao.updateCHOpClaim(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	//Chandana - 8036 - for PEX nims op claims update
	@Override
	public String updatePEXOpClaim(HashMap hashMap) {
		String result = null;
		try{
			result = patientDao.updatePEXOpClaim(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	//Chandana - 8618 - New method for getting the userhospid
	@Override
	public String getUserHospId(String lStrUserId, String roleId, String schemeId) {
		String hospId = null;
		try{
			hospId = patientDao.getUserHospId(lStrUserId, roleId, schemeId);
		}catch(Exception le){
			le.printStackTrace();
		}
		return hospId;
	}
	//Chandana - 8618 - New method for checking the registered hosp and 30 days condition
	@Override
	public String checkPatientIsRegisteredInNims(String cardNo) {
		String result = null;
		try{
			result = patientDao.checkPatientIsRegisteredInNims(cardNo);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public Map<String, Object> saveOncologyEvaluationForm(PatientForm patientForm) {
		return patientDao.saveOncologyEvaluationForm(patientForm);
	}
	@Override
	public Map<String, Object> getOncologyEvaluationResponse(Map<String, Object> lResMap) {
		return patientDao.getOncologyEvaluationResponse(lResMap);
	}
	@Override
	public List<LabelBean> getMoleculesForOrgan(String organId) {
		return patientDao.getMoleculesForOrgan(organId);
	}
	@Override
	public String saveOncologyCMTEResp(Map<String, Object> hashMap) throws Exception {
		return patientDao.saveOncologyCMTEResp(hashMap);
	}
	@Override
	public String[] getHighEndFormsSubmitFlag(String id, String idType, String patientId) throws Exception {
		return patientDao.getHighEndFormsSubmitFlag(id, idType, patientId);
	}
	@Override
	public String generateOTP(String userMobileNo, String userId, String patientId) throws Exception {
		return  patientDao.generateOTP(userMobileNo, userId, patientId);
	}
	@Override
	public String verifyOTP(String userMobileNo, String lStrUserId, String OTP, String patientId) throws Exception {
		return  patientDao.verifyOTP(userMobileNo, lStrUserId, OTP, patientId);
	}
	@Override
	public String savePreauthInitforOncologyCases(Map<String, Object> hashMap)throws Exception {
		return patientDao.savePreauthInitforOncologyCases(hashMap);
	}
	//End CR#4511-SriHari
	//Chandana -7845- for view docs
	public String convertPDFToBase64(String filePath) throws Exception {
	    File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] pdfBytes = new byte[(int) file.length()];
        fileInputStream.read(pdfBytes);
        fileInputStream.close();
        return DatatypeConverter.printBase64Binary(pdfBytes);
    }
	//Chandana - 7845 - for Claims Workfloe details
	@Override
	public List<LabelBean> getWorkFlowDtls(HashMap hashMap) {
		List<LabelBean> resultList = new ArrayList<LabelBean>();
		try{
			resultList = patientDao.getWorkFlowDtls(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultList;
	}
	//Chandana - 7845 - to get the status list related o nims opd claims
	@Override
	public List<LabelBean> getNimsOPDClaimStatusLst(String acoFlag) {//Chandana - 8602 - Added ACO flag to differentiate the user
		List<LabelBean> resultList = new ArrayList<LabelBean>();
		try{
			resultList = patientDao.getNimsOPDClaimStatusLst(acoFlag);//chandana - 8602 - Added ACO flag to differentiate the user
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultList;
	}
	//Chandana - 8442 - New method for getting the card details when the card type is ABHA
	@Override
	public String getCardDtlsForAbhaSearch(String cardNo){
		String ResLst="";
    	try{
    		ResLst = patientDao.getCardDtlsForAbhaSearch(cardNo);
    	}catch(Exception e){
    		GLOGGER.error("Error in Generating updDrugMonthlyDtls() list of ClaimsFlowPaymentServiceImpl "+ e.getMessage());
    		e.printStackTrace();
    	}
    	return ResLst;
	}
	//Chandana - 8441 - New method for additional attachments
		@Override
		public List<LabelBean> getAddtnalAttach(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getAddtnalAttach(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
	//Chandana - 8441 - New method for deleting or saving the additional attachment
		@Override
		public String updateAdditionalAttach(HashMap hashMap) {
			String result = null;
			try{
				result = patientDao.updateAdditionalAttach(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return result;
		}
		//Chandana - 8599 - New method for updating the missing attachments
		@Override
		public String updateMissingAttach(HashMap hashMap) {
			String result = null;
			try{
				result = patientDao.updateMissingAttach(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return result;
		}
		//Chandana - 8602 - Added new method to get the approved amount
		@Override
		public String getApprovedAmount(String patId) throws Exception{
			String result = null;
			try{
				result = patientDao.getApprovedAmount(patId);
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		//Chandana - 8602 - New method for getting patient id using opClaimNo
		@Override
		public String getPatNoFromClaimNo(String patId, String seqId) throws Exception{
			String result = null;
			try{
				result = patientDao.getPatNoFromClaimNo(patId,seqId);
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		@Override
		public String getFlagForACOReturnedClaim(String userId, String opClaimNo) throws Exception{
			String result = null;
			try{
				result = patientDao.getFlagForACOReturnedClaim(userId,opClaimNo);
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		@Override
	public List<LabelBean> getcategoryList() {
		return patientDao.getcategoryList();
	 
	}
	@Override
	public List<LabelBean> getPrintDiagnosis(String patientId) {
		return patientDao.getPrintDiagnosis(patientId);
	 
	}
	
	@Override 
	public String getProvisionDiagnosis(PatientForm patientForm,String lStrUserId) {
		String result = null;
		try{
			result = patientDao.getProvisionDiagnosis(patientForm,lStrUserId);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
//Chandana - 8755 - New method
		@Override
		public List<LabelBean> getDlhJrnlstClaimCasesLst(HashMap<String, String> hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getDlhJrnlstClaimCasesLst(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8755 - New method for getting the attachment type list
		@Override
		public List<LabelBean> getAttachmentsLst(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getAttachmentsLst(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8755 - New method for insert
		@Override
		public String updateMedcoDlhJrnlstClaim(HashMap hashMap) {
			String result = null;
			try{
				result = patientDao.updateMedcoDlhJrnlstClaim(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return result;
		}
		//Chandana - 8755 - New method for deleting or saving the additional attachment for Delhi journalist
		@Override
		public String updDlhJrnlstAddtnalAttach(HashMap hashMap) {
			String result = null;
			try{
				result = patientDao.updDlhJrnlstAddtnalAttach(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return result;
		}
		//Chandana - 8755 - New method for getting the catogry and provisional details
		@Override
		public List<LabelBean> getCatProvisionalDtls(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getCatProvisionalDtls(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8755 - New method for getting the additional attachment s for Delhi journalist
		@Override
		public List<LabelBean> getDlhJrnlstAddtnalAttach(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getDlhJrnlstAddtnalAttach(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8755 - to get the status list related o nims opd claims
		@Override
		public List<LabelBean> getJrnlstDlhClaimStatusLst(String acoFlag) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getJrnlstDlhClaimStatusLst(acoFlag);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8755 - New method for getting the claim number
		@Override
		public String checkjrnlstDClaimNo(String patientId) {
			return patientDao.checkjrnlstDClaimNo(patientId);
		}
		//Chandana - 8755 - New method for getting the claim number
		@Override
		public String checkjrnlstDStatus(String patientId) {
			return patientDao.checkjrnlstDStatus(patientId);
		}
		//Chandana - 8755 - New method for getting the Mandatory attachment s for Delhi journalist
		@Override
		public List<LabelBean> getDlhJrnlstMandateAttach(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
					resultList = patientDao.getDlhJrnlstMandateAttach(hashMap);
			}catch(Exception le){
					le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8755 - Added new method to get the initiated amount
		@Override
		public String getInitiatedAmount(String patId) throws Exception{
			String result = null;
			try{
				result = patientDao.getInitiatedAmount(patId);
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		//Chandana - 8755 - for Claims Workfloe details
		@Override
		public List<LabelBean> getJrnlstDWorkFlowDtls(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getJrnlstDWorkFlowDtls(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - 8874 - New method for getting the consolidated bill details
		@Override
		public List<LabelBean> getOPConsolidateBill(HashMap hashMap) {
			List<LabelBean> resultList = new ArrayList<LabelBean>();
			try{
				resultList = patientDao.getOPConsolidateBill(hashMap);
			}catch(Exception le){
				le.printStackTrace();
			}
			return resultList;
		}
		//Chandana - For Delhi journalist
	@Override
	public List<String> getDlhJrnlstClaimCases(String status, String roleId, String userId) {//Chandana - 8602 - Added userid in the existing method
		List<String> resList = new ArrayList<String>();
		try{
			resList = patientDao.getDlhJrnlstClaimCases(status,roleId,userId);//Chandana - 8602 - Added userid in the existing method
		}catch(Exception le){
			le.printStackTrace();
		}
		return resList;
	}
	@Override
	public String updCEXDlhJrnlstClaim(HashMap hashMap) {
		String result = null;
		try{
			result = patientDao.updCEXDlhJrnlstClaim(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public String getDlhJurnlstClaimStatus(String patientId) {
		String result = null;
		try{
			result = patientDao.getDlhJurnlstClaimStatus(patientId);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public String updDlhJrnlstCHClaim(HashMap hashMap) {
		String result = null;
		try{
			result = patientDao.updDlhJrnlstCHClaim(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public List<LabelBean> getDlhJrnlstClaimsLstForApprvl(HashMap<String, String> hashMap) {
		List<LabelBean> resultList = new ArrayList<LabelBean>();
		try{
			resultList = patientDao.getDlhJrnlstClaimsLstForApprvl(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultList;
	}
	@Override
	public String dlhJrnlstClaimPenByACO(HashMap hashMap) {
		String result = null;
		try{
			result = patientDao.dlhJrnlstClaimPenByACO(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public List<LabelBean> getAdmissionDtls(HashMap hashMap) {//Chandana - 9045 - New method for getting the admission details
		List<LabelBean> resultList = new ArrayList<LabelBean>();
		try{
			resultList = patientDao.getAdmissionDtls(hashMap);
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultList;
	}
	@Override
	public String getPatientId(String caseId) {
		String result = null;
		try{
			result = patientDao.getPatientId(caseId);
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}
	@Override
	public ClaimsFlowVO saveDlhJrnlstClaim(ClaimsFlowVO claimFlowVO) throws Exception {
		try{
			claimFlowVO = patientDao.saveDlhJrnlstClaim(claimFlowVO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return claimFlowVO;
	}
}
