package com.ahct.chronicOp.dao;


import com.ahct.model.EhfChronicPatientDtl;
import com.ahct.model.EhfPatient;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;
import java.math.BigDecimal;
import java.util.List;
import com.ahct.chronicOp.vo.ChronicOPVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;


public interface ChronicOPDAO {
	
	List<ChronicOPVO> getChronicOPPatients(String hospId,String roleId,String userState);

	public ChronicOPVO getChronicOPCasesView(ChronicOPVO chronicOPVO);
	
	public ChronicOPVO searchChroniOPCases(ChronicOPVO chronicOPVO);
	
	public String saveCaseDetails(ChronicOPVO ChronicOpVO) throws Exception;
	List<ChronicOPVO>  searchChronicOPPatients(String chronicId,String patName,String state,String district,String fromDt,String toDate,String user,String hospId,String roleId);

	
	List<ChronicOPVO>  searchChronicClaimCases(String chronicId,String patName,String state,String district,String fromDt,String toDate,String user,String hospId,String roleId);
	
	/**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations() throws Exception;
    
    
    /**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
    public List<LabelBean> getHospital(String userId,String roleId)throws Exception;
    
    
    public String getHospActiveStatus(String userId, String roleId);
    
    
    /**
     * ;
     * @param ChronicOPVO chronicOPVO
     * @return chronicOPVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
	public ChronicOPVO retrieveCardDetails(ChronicOPVO chronicOPVO) throws Exception;
	
	
	/**
     * ;
     * @param String payGrade
     * @return String slab
     * @function This Method is Used For Retrieving Slab type for given payGrade
     */
	public String getSlabType(String payGrade)throws Exception;
	
	/**
	  * ;
	  * @param String hospId
	  * @return String hospName
	  * @function This Method is Used For getting Hospital Name
	  */
	 public String getHospName(String hospId)throws Exception;
	 
	 public String getSequence(String pStrSeqName) throws Exception;
	 
	 /**
	  * ;
	  * @param ChronicOPVO chronicOPVO
	  * @return int noOfRecords
	  * @function This Method is Used For Registering Direct Patient
	  */
	 /**
	  * ;
	  * @param ChronicOPVO chronicOPVO
	  * @return int noOfRecords
	  * @function This Method is Used For Registering Direct Patient
	  */
	public int registerPatient(ChronicOPVO chronicOPVO)throws Exception;
		
	 /**
	  * ;
	  * @param String chronicId
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
    public String getRelationName(String relationId) throws Exception;
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
    public String getPatientPhoto(String cardNo)throws Exception;
    /**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
    public String getLocationName(String locId)throws Exception;
    
	List<ChronicOPVO> getChronicClaimCases(String roleId,String hospId,String userState);

	String getHospitalID(String userId,String roleId);
	
	public List<LabelBean> getOpPkgList()throws Exception;
	
	public PreauthVO getPatientFullDtls(String lStrChronicId,String chronicNo,String showAll);
	
	public String deleteGenInvest(String chronicId, String investId);
	
	public List<CommonDtlsVO> getPatientCommonDtls(String chronicId);
	
	public String  getChronicInstallment(String chronicId);
	
	public long getPackageAmount(String chronicId,String installment,String scheme);
	
	public boolean validateAttachments(String chronicNo,String caseStatus,String usrRole);
	
	public List<ClaimsFlowVO> getAuditTrail(String chronicNo);
	
	public String saveChronicClaim(ChronicOPVO chronicOPVO);
	
	public boolean validateChronicClaim(String chronicId,String chronicNo);
	
	public List<LabelBean> getChronicDrugs(String opPkgCode) throws Exception;
	
	public List<LabelBean> getChronicInvestBlockName(String opPkgCode,String installment);
	
	public List<String> getchronicInvestigations(String lStrBlockId,String packageCode,String installment);
	
	public List<LabelBean> getChronicChemSubstance(String opPkgCode) throws Exception;
	
	/*public List<LabelBean> getRouteList(String actCode);
	
	public List<LabelBean> getStrengthList(String actCode);*/
	
	public List<LabelBean> getOpIcdList(String opCode,String scheme) throws Exception;
	
	public List<LabelBean> getOpDrugSubList(String drugTypeCode);
	
	public List<LabelBean> getOpPharSubList(String drugTypeCode);
	
	public List<LabelBean> getOpChemSubList(String pharSubCode);
	
	public List<LabelBean> getChemSubList(String cSubGrpCode);
	
	public ChronicOPVO getCotdChkList(String chronicId,String chronicNo) throws Exception;
	
	public ChronicOPVO getCoexChkList(String chronicId,String chronicNo) throws Exception;
	
	public Number getMonthsbetween(String chronicId,String chronicNo) throws Exception;
	
	public String saveChronicInstallment(ChronicOPVO ChronicOpVO) throws Exception;
	
	public boolean getUserPkgCode(String pkgCode,String cardNo);
	
	public boolean cancelCase(String chronicId,String chronicNo,String user);
	
	public Number getDaysBetween(String chronicId,String chronicNo);
	
	public long getPackageDrugAmount(ChronicOPVO  chronicOPVO);
	
	public String getMedcoScheme(String userId);
	
	public float calculateDrugAmount(DrugsVO  drugsVO);
	
	
	public String calculatePackageAmt(String pkgCode,String actOrder,String scheme);
	
	public List<LabelBean> getChronicStatus() ;
	
	
	public String updateSentBackClaims(ChronicOPVO  chronicOPVO);
	
	public long getCasePackageAmount(String chronicId,String chronicNo);
}
