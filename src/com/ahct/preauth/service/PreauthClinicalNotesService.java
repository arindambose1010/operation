
 package com.ahct.preauth.service;

import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
//import com.ahct.model.AsrimHospitals;
//import com.ahct.model.AsritBiomFinger;
//import com.ahct.model.AsritCaseProcess;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfCaseMedicalDtls;
import com.ahct.model.EhfCaseSurgicalDtls;
import com.ahct.model.EhfDischargeSummary;
import com.ahct.model.EhfTelephonicRegn;


public interface PreauthClinicalNotesService
 {
     //public Long getClinicalNotesCOUNT(String pStrCaseId,String pStrNotesType) throws Exception;
     public List<PreauthClinicalNotesVO> getTherapyDetails(String pStrCaseId,String pStrNotesType) throws Exception;
     public List<PreauthClinicalNotesVO> getClinicalNotesForCase(String pStrCaseId,String pStrNotesType) throws Exception;
     public String saveClinicalNotes(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId);
     public EhfCase getCaseDetails(String pStrCaseId) throws Exception;
     public EhfTelephonicRegn getTeleCaseDetails(String pStrPatientId) throws Exception;
     public EhfPatient getPatientDetails(String pStrPatientId) throws Exception;
     public boolean saveSurgeryDetails(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserRole,String pStrUserId) throws Exception;
     public String getTelephneIntimationDate(String pStrCaseId) throws Exception;
     //public AsrimHospitals getUserHospType(String pStrUserId) throws Exception;
     public boolean saveDischargeSummaryDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId,String pStrUserRole,String pStrDisDeathFlag) throws Exception;
     public EhfDischargeSummary getDischargeSummary(String pStrCaseId);
     public Long getPostOPCheckForSurgery(String pStrCaseId) throws Exception;
     public boolean saveAttachments(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId) throws Exception;
     public PreauthClinicalNotesVO getAttachmentsCntByType(String pStrCaseId) throws Exception;
     //public List<AsritCaseClinicalDose> getAdditionlCliniclDtls(String pStrClinicalId,String pStrDoseFor);
     public EhfCaseMedicalDtls getCaseMedmgntDtls(String pStrCaseId) throws Exception;
     public EhfCaseSurgicalDtls getCasePacOperationDtls(String pStrCaseId) throws Exception;
     public Long checkForDeathDate(String pStrPatientId)throws Exception;
     public Long getPhaseStatus(String pStrCaseId)throws Exception;
     //public AsritCaseProcess getCaseProcessDtls(String pStrCaseId) throws Exception;
     //public AsritBiomFinger getPatBioFinger(String pStrPatientId) throws Exception;
     public String getServerDate(String pStrDtFormat) throws Exception; 
     public String getFollowUpFlag(String pStrCaseId) throws Exception;
     public String getMedMngmtFlag(String pStrCaseId)throws Exception;
     public List<PreauthClinicalNotesVO> getDisSumDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrCaseId,String pStrNotesType) throws Exception;     
     public String getMedicalFlag(String pStrCaseId);
     public LabelBean getHospitalDetails(String pStrCaseId) throws Exception;
     public LabelBean getUserDetails(String pStrUserId) throws Exception;
     public List<LabelBean> getComorbidDtls(String comorbidVal) throws Exception;
     public List<DrugsVO> getIPDrugs(String pStrCaseId,String pStrPrePostFlag);
     public List<String> getOpDrugSubList(String drugTypeCode,String pStrIpOpType);
     public List<String> getOpPharSubList(String drugTypeCode,String pStrIpOpType);
     public List<String> getOpChemSubList(String pharSubCode,String pStrIpOpType);
 	 public List<String> getChemSubList(String cSubGrpCode,String pStrIpOpType);
 	 public List<String> getRouteList(String routeTypeCode,String pStrIpOpType);
	 public List<String> getStrengthList(String strengthTypeCode,String pStrIpOpType);
	 /*
	     * Used to get all the therapies of the case
	     */
	 public List<CasesSearchVO> getDopDtls(String caseId);
	 
	 /*
	  * Added by Srikalyan to Check the
	  * Cochlear FollowUp Conditions
	  */
	 public String getCochlearFollowUpFlag(String caseId,int cochlearFollowUpCount);		
	 
	 /***
	  * @author Kalyan
	  * @param Case ID
	  * @return Dental Flag
	  * @method Checks Whether the case is Dental or not  
	  */
	 public String checkDentalCase(String caseId);
	 
	 /***
	  * @author Kalyan
	  * @param Case ID
	  * @return Slab Amount
	  * @method Obtain Slab Amount for the Case  
	  */
	 public String getSlabAmount(String caseId);
 }
