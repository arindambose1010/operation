package com.ahct.followup.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.model.EhfCochlearFollowup;



public interface FollowUpDAO {
	public String saveFollowUp( FollowUpVO followUpVo,String pStrUserId);

	public FollowUpVO saveFollowUpClaim(FollowUpVO followUpVO);

	public FollowUpVO getListCases(FollowUpVO followUpVO);

	public List<CommonDtlsVO> getPatientCommonDtls(String lStrCaseId, String lStrFollowUpId);

	public List<FollowUpVO> getFollowUpList(String lStrCaseId,String lStrFollowUpId);

	public String checkValidForFollowup(String lStrCaseId);
	CommonDtlsVO getFPDtlsForPayment(String lStrCaseId,String lStrFollowUpId);
	public HashMap updateFollowupPayments(HashMap lParamMap) throws Exception;

	public List<FollowUpVO> getClinicalData(FollowUpVO followUpVO);

	public List<DrugsVO> getDrugsDtls(FollowUpVO followUpVO,String clinicalId);
	
	public Long getNextFollowUpId(FollowUpVO followUpVo);

	public String updateFPClaimStatusReady(ClaimsFlowVO claimFlowVO);
	public List<LabelBean> getDrugSubList(String drugTypeCode);
	public String getDrugDetails(String drugCode);
	public List<String> getInvestigations(String lStrBlockId);
	public String getNextStatus(String pStrCaseStatus, String pStrUserRole,String lStrActionDone) ;
	
	/*
	 * This method is used to retrieve the patient photo
	 * based upon the caseId.
	 */
	public String getPatientPhoto(String caseId);
	/*
	 * This method is used to get the all the range for next followup for a case
	 */
	public long getFollowUpLst(String caseId);
	/*
	 * This method is used to get the next follow up date
	 */
	public Date getNxtFollowUpDt(String caseId,String offset);
	/*
	 * This method is used to get the Approval Cases for follopwUp without Pagination
	 */
	public List<CasesSearchVO> getAllListCases(FollowUpVO followUpVO);
	
	/*
	 * Added by Srikalyan for Saving
	 * Cochlear FollowUp Details
	 */
	public String saveCochlearFollowUpDetails(EhfCochlearFollowup ehfCochlearFollowup);
	
	/*
	 * Added by Srikalyan for getting
	 * Case Object
	 */
	public com.ahct.model.EhfCase getCase(String caseId);
	
	
	/*
	 * Added by Srikalyan for Saving 
	 * Cochlear Case Claim Object
	 */
	public String saveCochCaseClaim(com.ahct.model.EhfCaseFollowupClaim ehfCaseFollowupClaim);
	/*
	 * Added by Srikalyan for getting
	 * Cochlear FollowUp Details
	 */
	public List<FollowUpVO> getCochlearDetails(String caseId,int cochlearCount);
	/*
	 * Added by Srikalyan for getting
	 * Case Details
	 */
	public com.ahct.model.EhfCase getCaseDtls(String caseId);

	


	 /*
	 * Added by Srikalyan to Execute a Pagination Query for 
	 * Cochlear FollowUp Workflow Cases
	 */
	 public List<FollowUpVO> executeQuery(String query,int startIndex,int maxResults);
	 
	 /*
	 * Added by Srikalyan to Execute Query for 
	 * Cochlear FollowUp Workflow Cases
	 */
	 public List<FollowUpVO> executeNormalQuery(String query);
	 
	 /*
	 * Added by Srikalyan to Obtain Case FollowUp 
	 * Claim Object for Cochlear Case 
	 */
	 public com.ahct.model.EhfCaseFollowupClaim getCaseClaim(String followUpId);
		 
	 /*
	 * Added by Srikalyan to Insert Cochelar   
	 * FollowUp Claim Objects in Data Base 
	 */
	 public String saveObj(HashMap<String,Object> saveObj,String saveName);
	 
}
