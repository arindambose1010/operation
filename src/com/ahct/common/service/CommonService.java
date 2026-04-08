package com.ahct.common.service;

import java.util.List;
import java.util.Map;

import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.EhfmDepts;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmSeq;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.emailConfiguration.EmailVO;

public interface CommonService {
	/**
     * ;
     * @param String seqIdentifier
     * @return EhfmSeq ehfmSeq
     * @function This Method is Used For getting EhfmSeq data based on seqIdentifier
     */
	public EhfmSeq getSeqNextVal(String seqIdentifier);
	public void updateSequenceVal(EhfmSeq ehfmSeq);
	/**
     * ;
     * @param EhfmSeq ehfmSeq
     * @function This Method is Used For saving seqVal in EhfmSeq Table
     */
	
	
	  
	  
	//public SgvcSeqMst getNextVal(String seqIdentifier);
	//public void updateSequence(SgvcSeqMst sgvcSeqMst);
	
	
	public List<LabelBean> getDistrictList() throws Exception;
	public List<LabelBean> getMandalList(String distId) throws Exception;
	public List<LabelBean> getVillageList(String mandalId) throws Exception;
	public List<LabelBean> getHamletList(String villageId) throws Exception;
	public List<LabelBean> getCastes() throws Exception;
	public List<LabelBean> getRelations() throws Exception;
	public List<LabelBean> getAsrimLocations(String locHdrId,String locParntId) throws Exception;
	public String getLocationName(String locId) throws Exception;
	public List<LabelBean> getOccupations()throws Exception;
	public List<LabelBean> getComboDetails(String cmbHdrId) throws Exception;
	public int getPhaseId(String distId)throws Exception;
	public List<LabelBean> getPhases() throws Exception;
	public String getDecryptedPswd(String pStrLoginName);
	public String getCmbHdrname(String cmbHdrId,String cmbVal)throws Exception;
	//public List<LabelBean> getGoDetails(String goType);
	public List<LabelBean> getHospitals() throws Exception;
	public List<LabelBean> getPersonalHistory(String parntCode)throws Exception;
	public List<LabelBean> getFamilyHistory()throws Exception;
	public List<LabelBean> getInvestigations()throws Exception;
	public List<LabelBean> getDrugs()throws Exception;
	public List<LabelBean> getGenExaminFndgs(String schemeId) throws Exception;
	public List<LabelBean> getPastIllnessHitory()throws Exception;
	public List<LabelBean> getAllDiagnosisCat()throws Exception;
	public List<LabelBean> getDiagnosisSubCat(String lStrDiagCat);
	public List<LabelBean> getMainSymptonLst();
	public List<LabelBean> getAsriCategoryList(String hospId,String scheme);
	public List<LabelBean> getAsriDrugs(String drugSubTypeId) throws Exception;
	public List<LabelBean> getDynamicWrkFlowDtls(String pCurrStatus,String pCurrRole,String pMainModule,String pSubModule);
	public List<LabelBean> getCategorys(String pStrCatId,String userId);
	public List<LabelBean> getProcedures(String pStrCatCode , String pStrProcCode,String pStrHospId,String Scheme,String dentalEnhFlag);
	public List<LabelBean> getCategoryList(String userId, String userRole);
	public String getPastIllnessHitoryNames(String illnessHistory);
	public String getFamilyHisNames(String illnessHistory);
	public List<LabelBean> getICDCategoryList(String pStrCatId,String userId,String hosType);
	public List<LabelBean> getDistrictListNew();
	
	public Long getCaseTherapyInvestId();
	/**
     * ;
     * @param PatientSmsVO patientSmsVO
     * @return String Msg
     * @function This method is used for sending sms to the specified patient
     */
	public String sendPatientSms(PatientSmsVO patientSmsVO);
	/**
     * ;
     * @param EmailVO emailVO
     * @param Map model
     * @function This method is used for sending Email to the specified patient
     */
	public void generateMail(EmailVO emailVO, Map model);
	
	/**
	 * @function This method is used for sending Email to the specified receipent without any images
	 * @param emailVO
	 * @param model
	 */
	public void generateNonImageMail(EmailVO emailVO,Map model);
	/**
     * ;
     * @param String userId
     * @param String caseId
     * @param String lockStatus
     * @return int recordsInserted
     * @function This method is used for locking a specific case against a specified user
     */
	public int lockCase(String userId,String caseId,String lockStatus);
	/**
     * ;
     * @param String userId
     * @param String caseId
     * @function This method is used for unlocking cases locked by a specified user
     */
	public void unlockCases(String userId);
	/**
     * ;
     * @param String caseId
     * @function This method is used for getting lock status for a specific case
     */
	public String getLockStatus(String caseId);
	public List<LabelBean> getInvestBlockName();
	public List<LabelBean> getInvestBlockNameNew(PatientVO patientVO);
	public List<LabelBean> getOpDrugs(String pStrIpOpType)throws Exception;
	public List<LabelBean> getRouteType(String pStrIpOpType)throws Exception;
	public List<LabelBean> getStrengthType(String pStrIpOpType) throws Exception;
	public String getSequence(String pStrSeqName)throws Exception;
	public List<String> getDocListBySpec(String asriCatCode, String hospCode,String scheme);
	public String getHospType(String hospId);
	public void saveToBuffer(String message,String phoneList);
	public String getDutyDoctorById(String regNo);
	public List<LabelBean> getOpDrugsAuto() throws Exception;
	public EhfmHospitals getHospInfo(String hospId) throws Exception;
	public List<LabelBean> getICDProcedures(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag);
	public String ckDentalClinic(String userid, String caseId);
	public String saveToAsritSms(Map<String,Object> lmap);
	public List<LabelBean> getDentalMainSymptonLst();
	public List<LabelBean> getDentalInvestBlockNameNew(PatientVO investVo);
	public List<LabelBean> getDentalGenExaminFndgs(String schemeId);
	public List<LabelBean> getDentalPersonalHistory(String string);
	public List<String> getICDCategoryList(String asriCatCode);
	public List<LabelBean> getCardDetails(String cardNo);
	public String getloggedUserHospId(String lStrUserId, String schemeId);
	public List<LabelBean> getMedicalSpltyList(String loggedUserHospId,
			String schemeId);
	public List<LabelBean> getMedicalCatgryListDflt();
	public List<LabelBean> getMedicalCatgryList(String medicalSpclty);
	public List<LabelBean> getMedicalDrugList(String cat);
	public List<LabelBean> getLocationsNew(String locHdrId, String distId,
			String stateType);

	/* Added by Namratha To get all New Locations   
	 * */
	public LabelBean getNewLocations(LabelBean labelBeanVillage);
	public List<LabelBean> getActiveHospitals();
	public List<LabelBean> getAllUsersFromDepts1(String deptId);

	public String getActionDoneName(String caseId,String module);
	public List<LabelBean> getHubSpoke(String lStrUserId, String schemeId);
	public List<LabelBean> getcaseList(String caseId, String schemeId);
	public LabelBean getUserDtls(String userId);
	public String getNxtValCEOSent();
	public List<LabelBean> getAllUsersFromDepts(String deptId);
	public List<EhfmDepts> getAllDepartments();
}
