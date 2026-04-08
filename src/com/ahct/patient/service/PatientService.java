package com.ahct.patient.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;

public interface PatientService {
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
    public PatientVO retrieveCardDetails(PatientVO patientVO);
    /**
     * ;
     * @param PatientVO patientVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	public int registerPatient(PatientVO patientVO);
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
	public List<LabelBean> getHospital(String userId,String roleId);
	/**
     * ;
     * @param HashMap<String,String> searchMap
     * @return PatientVO registeredPatientsList
     * @function This Method is Used For retrieving registered patients list for given search data
     */
	public PatientVO getRegisteredPatients(HashMap<String, String> searchMap);
	/**
     * ;
     * @param String patientId
     * @return AsritPatient asritPatient
     * @function This Method is Used For retrieving PatientDetails for given PatientId
     */
	public EhfPatient getPatientDetails(String patientId);
	/**
     * ;
     * @return List<String> complaintList
     * @function This Method is Used For getting Complaints List
     */
	public List<String> getComplaints(String mainCompId);
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For retrieving Doctors List associated for the specified hospId
     */
	public List<LabelBean> getDoctorsList(String doctorType,String hospId,String scheme);
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return String caseId
	 * @function This Method is Used For Registering Patient Case as IP/OP/Chronic OP
	 */
	public String saveCaseDetails(PatientVO patientVO);
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	public String getHospName(String hospId);
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	public String getLocationName(String locId);
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @param String doctorId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For getting Selected Doctor Details
     */
	public List<LabelBean> getSelDocDetails(String doctorType,String hospId,String docId,String schemeId); 
	 /**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations();
    /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
    public String getRelationName(String relationId);
    /**
     * ;
     * @param String occupationId
     * @return String occupationName
     * @function This Method is Used For getting occupationName for given occupationId from EhfmOccupationMst table
     */
    public String getOccupationName(String occupationId);
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
    public String getPatientPhoto(String cardNo);
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo of journalist
     */
    public String getJournalistPhoto(String cardNo);
    /**
     * ;
     * @param PatientVO patientVO
     * @return int records inserted
     * @function This Method is Used For Saving telephonic information
     */
	public int captureTelephonicPatientDtls(PatientVO patientVO);
	/**
     * ;
     * @param PatientVO patientVO
     * @return PatientVO patientVO
     * @function This Method is Used For Retrieving telephonic registered details for specified telephonicId
     */
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo);
	/**
     * ;
     * @param String hospId
     * @return List<LabelBean> ICDCategorylist 
     * @function This Method is Used For getting ICD Category List for specific hospital
     */
	public List<LabelBean> getTherapyCategory(String hospId);
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> ICDSubCategorylist 
     * @function This Method is Used For getting ICD Sub Category List for specific category
     */
	public List<LabelBean> getTherapySubCategory(String categoryId);
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> procList 
     * @function This Method is Used For getting ICD Procedure List for specific category
     */
	public List<LabelBean> getTherapyProcedure(String subCategoryId);
	/**
     * ;
     * @return List<LabelBean> diagnosisTypesList
     * @function This Method is Used For getting Diagnosis Types List(EHFM_DIAGNOSIS_MST--EhfmDiagnosisMst)
     */
	public List<LabelBean> getDiagnosisTypes();
	/**
     * ;
     * @param String diagnosisId
     * @return List<LabelBean> diagnosisMainCategoryList
     * @function This Method is Used For getting Diagnosis Main Category List(EHFM_DIA_MAINCAT_MST--EhfmDiagMainCatMst)
     */
	public List<LabelBean> getDiagnMainCategory(String diagnosisId);
	/**
     * ;
     * @param String mainCategoryId
     * @return List<LabelBean> diagnosisCategoryList
     * @function This Method is Used For getting Diagnosis Category List(EHFM_DIA_CATEGORY_MST--EhfmDiagCategoryMst)
     */
	public List<LabelBean> getDiagnCategory(String mainCategoryId);
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> diagnosisSubCategoryList
     * @function This Method is Used For getting Diagnosis Sub Category List(EHFM_DIA_SUBCAT_MST--EhfmDiagSubCatMst)
     */
	public List<LabelBean> getDiagnSubCategory(String categoryId);
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDiseaseList
     * @function This Method is Used For getting Diagnosis Disease List(EHFM_DIA_DISEASE_MST--EhfmDiagDiseaseMst)
     */
	public List<LabelBean> getDiagnDisease(String code,String param);
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDisAnatomicalList
     * @function This Method is Used For getting Diagnosis Disease Anatomical List(EHFM_DIA_DISANATOMICAL_MST--EhfmDiagDisAnatomicalMst)
     */
	public List<LabelBean> getDiagnDisAnatomical(String code,String param);
	/**
     * ;
     * @return List<LabelBean> mainComplaintList
     * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
     */
	public List<LabelBean> getMainComplaintLst();
	/**
     * ;
     * @param String mainSymptomCode
     * @param String subSymptomCode
     * @return List<String> symptomList
     * @function This Method is Used For retrieving Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	public List<String> getSymptomLists(String mainSymptomCode, String subSymptomCode);
	/**
     * ;
     * @param String icdCatCode
     * @param String asriCatCode
     * @param String hospId
     * @return List<String> procedureList
     * @function This Method is Used For retrieving Icd procedure List(EHFM_MAIN_THERAPY--EhfmTherapyProcMst)
     */
	public List<String> getIcdProcList(String icdCatCode,String asriCatCode, String hospId,String state,String hosType);
	/**
     * ;
     * @param String drugTypeCode
     * @return List<String> drugSubTypeList
     * @function This Method is Used For getting Drug Sub Type List From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public List<String> getDrugSubList(String drugTypeCode);
	
	public List<String> getOpDrugSubList(String drugTypeCode,String pStrIpOpType);
	public List<String> getOpPharSubList(String drugTypeCode,String pStrIpOpType);
	public List<String> getOpChemSubList(String pharSubCode,String pStrIpOpType);
	public List<String> getChemSubList(String cSubGrpCode,String pStrIpOpType);
	
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public String getDrugDetails(String drugCode);
	/**
     * ;
     * @param String icdProcCode
     * @return String procCode
     * @function This Method is Used For getting Procedure Codes From EhfmTherapyProcMst(EHFM_THERAPY_PROC_MST )
     */
	public String getTherapyProcCodes(String icdProcCode);
	/**
     * ;
     * @return List<LabelBean> opCategoryList
     * @function This Method is Used For getting Therapy OP Category Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<LabelBean> getTherapyOPCategory();
	/**
     * ;
     * @param String opMainCode
     * @return List<LabelBean> opPkgList
     * @function This Method is Used For getting Therapy OP Package Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<String> getOpPkgList(String opCatCode);
	/**
     * ;
     * @param String opPkgCode
     * @return List<LabelBean> opIcdList
     * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<String> getOpIcdList(String opPkgCode);
	/**
     * ;
     * @param String cardNo
     * @param String opCatCode
     * @return int count
     * @function This Method is Used For getting count of cases with given cardNo and therapyOPCategory whose medication period is not completed
     */
	public int validateTherapyOPCat(String cardNo,String opCatCode);
	
	
	public List<DrugsVO> getChronicDetails(String cardNo);
	public List<String> getSubSymptomLists(String mainSymptomCode);
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId);
	public String deleteInvestigations(String caseId, String procCode,String investId,String asriCode);
	public String deleteGenInvest(String patientId, String investId);
	public List<String> getInvestigations(String lStrBlockId);
	public List<String> getRouteList(String routeTypeCode,String pStrIpOpType);
	public List<String> getStrengthList(String strengthTypeCode,String pStrIpOpType);
	public String getSequence(String pStrSeqName) throws Exception;
	public List<CommonDtlsVO> getDtrsFormDtls(String caseId) throws Exception;
	public PatientVO getOPCases(HashMap<String, String> searchMap) ;
	public String getDutyDoctorById(String regNo);
	public String getProcedureType(String procCode,String asriCode);
	
	/**
	 * ;
	 * @param String caseId
	 * @return List<CommonDtlsVO> caseDetailsList
	 	 * @function This Method is Used For common details for the caseId specified
	 */
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId);
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<LabelBean> docAavilList
	 * @function This Method is Used For getting available doctor list for given hospital and specialty
	 */
	public List<LabelBean> getDocAvailLst(PatientVO patientVO);
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<String> specInvestList
	 * @function This Method is Used For getting special investigation list for given therapy
	 */
	public List<String> getSpecialInvestigationLst(PatientVO patientVO);
	
	public List<PreauthVO> getcaseSurgeryDtls(String caseId);
	public List<LabelBean> getSymptomsDtls(String lStrCaseId);
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId);
	public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId);
	public String getEmpNameById(String id);
	public String getDoctorById(String id);
	public List<DrugsVO> getDrugs(String pStrPatId,String flag);
	public String getHospActiveStatus(String userId, String roleId);
	//pravalika
	public List<LabelBean> getcategoryList();
	public String getProvisionDiagnosis(PatientForm patientForm,String lStrUserId);
	public List<LabelBean> getPrintDiagnosis(String patientId);
	
	
	/**
     * @param String icdProcCode
     * @return int dentalUnits
     * @function This Method is Used For getting Dental Units for given Procedure
     */
	public int getDenUnitsList(String icdProcCode);
	/*
	 * Added by Srikalyan to Verify New Born
	 * Baby Condition for Mithra based on 
	 * the scheme
	 */
	public String checkNewBornCond(String lStrUserId);
	
	public String getInvestPrice(String blockId,String investId);
	
	
	
	public String getDaysActiveOP(String caseId);
	
	public List<LabelBean> getConsultDtls(String patientId);
	
	public List<CasesSearchVO> getOpPastHistoryDetails(CasesSearchVO vo);
	
	
	public EhfmOpDrugMst getopdrugDataAuto(String chemicalCode);
	
	public String getEmpCode(String PatientId);

	/*
	 * Added by Srikalyan to Verify and get
	 * Dental Conditions for TG Patients 
	 */
	public PreauthVO validateAndGetDentalCond(PreauthVO preauthVO);
	/*
	 * Added by Srikalyan to get the Procedure Name 
	 * for corresponding Procedure ID
	 */
	public PreauthVO getProcName(String procId,String patSchemeId);

	/*
	 * Added by Srikalyan to get the Submit
	 * Drug Details of Pharmacy Case
	 */
	public String submitPharmacyCase(String caseId,String patId,String drugDtls);
	
	public EhfOpConsultData getOpDocDtls(String patientId);
	
	
/*Added by venkatesh for chronic op migration*/
	public String saveChronicCaseDetails(PatientVO patientVO) throws Exception ;
	
	public List<LabelBean> getDiagListSearch(String data,String type) throws Exception;

public LabelBean getDiagnosisDtlsAuto(String disCode) throws Exception;
	
	public List<LabelBean> getDiagList();

	/*
	 * Added by Srikalyan to get the Biometric Details 
	 * for corresponding Registered Patient
	 */
	


  /* Added by Yashasvi to check if it is a dental clinic */
	public String checkDentalClinic(String userid , String patientId);
	public String checkGovernmentHosp(String userid , String patientId);
	public String checkGovernmentHosp(String userid);
	public PatientVO getPatientDentalDtls(String patientId);
	public List<LabelBean> getdentalexaminationDtls(String mainCompId);
	/***
	 * @author Kalyan
	 * @return type
	 * @function This Method is Used to check Special conditions for TG. 
	 */
	public String checkSpecialDenatlCond(PreauthVO preauthVO);
	public List<LabelBean> getDentalMainComplaintLst();
	public String checkDentalHospital(String hospCode);
	 /* Added by Srikalyan to get the Biometric Details 
	 * for corresponding Registered Patient
	 */
	public PatientVO getBiomDtls(CommonDtlsVO commonDtlsVO);
	
	/***
	 * @author Kalyan
	 * @param Patient ID
	 * @return Follow Up Flag a String
	 * @function This Method is Used to check Dental Follow UP Eligibilty for a Specific Patient. 
	 */
	public List<String> checkDenFlp(String patientID,List<String> therapyList);
	public String getTreatmentDntlvalue(String caseId);
	public List<LabelBean> getCatName(String lStrCaseId);
	public LabelBean getHospStatus(String cardNo, String hospId);
	public String checkHubSpoke(String userIDu);
	public String getHubSpokeHospId(String userIDu, String string);
	public Map<String,Object> saveProformaFormHighEnd(PatientForm form) throws Exception;
	public Map<String, Object> getProformaFormHighEnd(Map<String, Object> lResMap) throws Exception;
	public List<LabelBean> getProformaHighEndApproval(Map<String, Object> lResMap) throws Exception;
	public List<LabelBean> getOpClaimCasesList(HashMap<String, String> hashMap);
	public List<LabelBean> getOpClaimBillDtls(HashMap hashMap);
	public String updateMedcoOpClaim(HashMap hashMap);
	public String checkOpClaimNo(String patientId);
	public List<String> getNimsOpClaimCases(String status, String roleId, String userId);//Chandana - 8602 - Added userid in the existing method
	public String updateCHOpClaim(HashMap hashMap);
	public String getUserHospId(String lStrUserId, String roleId, String schemeId);//Chandana - 8618 - New method for getting the userhospid
	public String checkPatientIsRegisteredInNims(String cardNo);//Chandana - 8618 - New method for checking the registered hosp and 30 days condition
	public Map<String, Object> saveOncologyEvaluationForm(PatientForm patientForm);
	public Map<String, Object> getOncologyEvaluationResponse(Map<String, Object> lResMap);
	public List<LabelBean> getMoleculesForOrgan(String organId);
	public String saveOncologyCMTEResp(Map<String, Object> hashMap) throws Exception;
	public String[] getHighEndFormsSubmitFlag(String id, String idType, String patientId) throws Exception;
	public String generateOTP(String userMobileNo, String userId, String patientId) throws Exception;
	public String verifyOTP(String userMobileNo, String lStrUserId, String OTP, String patientId) throws Exception;
	public String savePreauthInitforOncologyCases(Map<String, Object> hashMap) throws Exception;	
	public String getClaimStatus(String patientId);//Chandana - 7845
	public String getNextStatus(String currentStatus, String actiondone);//Chandana - 7845
	public String updatePEXOpClaim(HashMap hashMap); //Chandana - 8036 - new method for updating the claims for PEX
	public String convertPDFToBase64(String filePath) throws Exception; //Chandana -7845- for view docs
	public List<LabelBean> getWorkFlowDtls(HashMap hashMap); //Chandana - 7845 - to get the claims workflow details
	public List<LabelBean> getNimsOPDClaimStatusLst(String acoFlag); //Chandana - 7845 - to get the status list related o nims opd claims, 8602 - Added ACO flag to differentiate the user
	public String getCardDtlsForAbhaSearch(String cardNo) throws Exception;//Chandana - 8442 - New method for getting the card details when the card type is ABHA
	public List<LabelBean> getAddtnalAttach(HashMap hashMap) throws Exception;//Chandana - 8441 - New method for getting the additional attachments
	public String updateAdditionalAttach(HashMap hashMap) throws Exception;//Chandana - 8441 - New method for deleting or saving the additional attachment
	public String updateMissingAttach(HashMap hashMap) throws Exception;//Chandana - Task Tracker#8599 - New method for saving the Missing attachments
	public String getApprovedAmount(String patientId) throws Exception;//Chandana - 8602 - Added new method to get the approved amount
	public String getPatNoFromClaimNo(String opClaimNo, String seqId) throws Exception;//Chandana - 8602 - New method for getting patient id using opClaimNo
	public String getFlagForACOReturnedClaim(String userId, String opClaimNo) throws Exception;//Chandana - 8602
	public List<LabelBean> getDlhJrnlstClaimCasesLst(HashMap<String, String> hashMap);//Chandana - 8755 - New method for getting the list
	public List<LabelBean> getAttachmentsLst(HashMap hashMap) throws Exception;//Chandana - 8755 - New method for getting the attachment types list
	public String updateMedcoDlhJrnlstClaim(HashMap hashMap) throws Exception;//Chandana - 8755 - New method for claims submission
	public String updDlhJrnlstAddtnalAttach(HashMap hashMap) throws Exception;//Chandana - 8755 - New method for uploading the additional attachments
	public List<LabelBean> getCatProvisionalDtls(HashMap hashMap);//Chandana - 8755 - New method for getting the catogry and provisional details
	public List<LabelBean> getDlhJrnlstAddtnalAttach(HashMap hashMap) throws Exception;//Chandana - 8755 - New method for getting the aditional attachment s for delhi journalist
	public List<LabelBean> getJrnlstDlhClaimStatusLst(String acoFlag);//Chandana - 8755 - New method for getting all the status
	public String checkjrnlstDClaimNo(String patientId);//Chandana - 8755 - New method to get the claim number
	public List<LabelBean> getDlhJrnlstMandateAttach(HashMap hashMap) throws Exception;//Chandana - 8755 - New method for getting the Mandatory attachment s for delhi journalist
	public String getInitiatedAmount(String patientId) throws Exception;//Chandana - 8755 - New method for getting the initiated amount
	public String checkjrnlstDStatus(String patientId);//Chandana - 8755 - New method to get the claim status
	public List<LabelBean> getJrnlstDWorkFlowDtls(HashMap hashMap); //Chandana - 8755 - to get the claims workflow details
	public List<LabelBean> getOPConsolidateBill(HashMap hashMap);//Chandana - 8874 - New method for getting the consolidate bill
	//Chandana - for delhi journalist
	public List<String> getDlhJrnlstClaimCases(String status, String roleId, String userId);
	public String updCEXDlhJrnlstClaim(HashMap hashMap);
	public String getDlhJurnlstClaimStatus(String patientId);
	public String updDlhJrnlstCHClaim(HashMap hashMap);
	public List<LabelBean> getDlhJrnlstClaimsLstForApprvl(HashMap<String, String> hashMap);
	public String dlhJrnlstClaimPenByACO(HashMap hashMap) throws Exception;
	public String getPatientId(String caseId);
	public List<LabelBean> getAdmissionDtls(HashMap hashMap);//Chandana - 9045 - New method to get the admission details
	public ClaimsFlowVO saveDlhJrnlstClaim(ClaimsFlowVO claimFlowVO) throws Exception;
}


