package com.ahct.chronicOp.service;


import com.ahct.model.EhfChronicPatientDtl;
import com.ahct.model.EhfPatient;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;


import java.math.BigDecimal;
import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.chronicOp.vo.ChronicOPVO;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfChronicPatientDtl;



public interface ChronicOPService {

	


	
	
List<ChronicOPVO> getChronicOPPatients(String hospId,String roleId,String userState);
	
	
public ChronicOPVO getChronicOPCasesView(ChronicOPVO chronicOPVO);
public ChronicOPVO searchChroniOPCases(ChronicOPVO chronicOPVO) ;
	

	List<ChronicOPVO>  searchChronicOPPatients(String chronicId,String patName,String state,String district,String fromDt,String toDate,String userState,String hospId,String roleId);
	
	List<ChronicOPVO>  searchChronicClaimCases(String chronicId,String patName,String state,String district,String fromDt,String toDate,String userState,String hospId,String roleId);
	
	
	List<ChronicOPVO> getChronicClaimCases(String roleId,String hospId,String userState);
	
	String getHospitalID(String userId,String roleId);


	public List<LabelBean> getHospital(String userId,String roleId);
	
	public String getHospActiveStatus(String userId, String roleId);	
	
	
	/**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations();
    
    /**
     * ;
     * @param ChronicOPVO ChronicOPVO
     * @return ChronicOPVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
    public ChronicOPVO retrieveCardDetails(ChronicOPVO chronicOPVO);
    
    
    public String getSequence(String pStrSeqName) throws Exception;
    
    
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	public String getHospName(String hospId);
	
    
    /**
     * ;
     * @param ChronicOPVO chronicOPVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	public int registerPatient(ChronicOPVO chronicOPVO);
	
	 /**
	  * ;
	  * @param String ChronicId
	  * @return EhfChronicPatientDtl ehfChronicPatientDtl
	  * @function This Method is Used For retrieving PatientDetails for given chronicId
	  */
	public EhfChronicPatientDtl getPatientDetails(String chronicId) throws Exception;
	
	
	 /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
    public String getRelationName(String relationId);
    
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	public String getLocationName(String locId);
	
	
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
    public String getPatientPhoto(String cardNo);
	
    
    
    
    /*added by venkatesh */
	
    public String saveCaseDetails(ChronicOPVO ChronicOpVO);
	
	public PreauthVO getPatientFullDtls(String lStrChronicId,String chronicNo,String showAll);
	
	public String deleteGenInvest(String chronicId, String investId);
	
	public List<CommonDtlsVO> getPatientCommonDtls(String chronicId);
	
	public String getChronicStatus(String chronicId,String chronicNo);
	
	public List<LabelBean> getOpPkgList(String scheme);
	
	public String  getChronicInstallment(String chronicId);
	
	public boolean validateAttachments(String chronicNo,String caseStatus,String usrRole);
	
	public long getPackageAmount(String chronicId,String installment,String scheme);
	
	public List<ClaimsFlowVO> getAuditTrail(String chronicNo);
	
	public String saveChronicClaim(ChronicOPVO chronicOPVO);
	
	public boolean validateChronicClaim(String chronicId,String chronicNo);
	
	public List<String> getChronicDrugsList(String opCatCode);
	
	public List<String> getChronicInvestList(String opCatCode,String installment);
	
	public List<String> getchronicInvestigations(String lStrBlockId,String packageCode,String installment);
	
	public List<String> getOpIcdList(String opPkgCode,String scheme);
	
	public List<String> getChronicChemSubstanceList(String opCatCode);
	
	public List<String> getOpDrugSubList(String drugTypeCode);
	
	public List<String> getOpPharSubList(String drugTypeCode);
	
	public List<String> getOpChemSubList(String pharSubCode);
	
	public List<String> getChemSubList(String cSubGrpCode);
	/*public List<String> getRouteList(String actCode);
	
	public List<String> getStrengthList(String actCode);*/
	
	public ChronicOPVO getCotdChkList(String chronicId,String chronicNo) throws Exception;
	
	public ChronicOPVO getCoexChkList(String chronicId,String chronicNo) throws Exception;
	
	public Number getMonthsbetween(String chronicId,String chronicNo) throws Exception;
	
	public String saveChronicInstallment(ChronicOPVO ChronicOpVO) throws Exception;
	
	public boolean getUserPkgCode(String pkgCode,String cardNo);
	
	public boolean cancelCase(String chronicId,String chronicNo,String user);
	
	public Number getDaysBetween(String chronicId,String chronicNo);
	
	public String setviewFlag(String pStrCaseId, String pStrFlag, String lStrEmpId);
	

	
	public String getTimeOutCount(String caseId1, List<LabelBean> groupsList, String lStrModule) ;
	
	public String getCaseStatusForCase(String caseId);
	
	public long getPackageDrugAmount(ChronicOPVO  chronicOPVO);
	
	public String getMedcoScheme(String userId);
	
	public float calculateDrugAmount(DrugsVO  drugsVO);
	
	public String getCreatedDt(String format) throws Exception;
	
	public List<LabelBean> getChronicStatus() ;
	
	public String updateSentBackClaims(ChronicOPVO  chronicOPVO);
	
	public long getCasePackageAmount(String chronicId,String chronicNo);

	
	/*
	 * Added by Srikalyan to get The Case/Patient Scheme
	 */
	public String getChronicScheme(String chronicId,String chronicNo);
}


