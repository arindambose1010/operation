package com.ahct.followup.service;

import java.util.HashMap;
import java.util.Date;
import java.util.List;

import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;



public interface FollowUpService {
	public String saveFollowUp( FollowUpVO followUpVo,String pStrUserId);

	public FollowUpVO saveFollowUpClaim(FollowUpVO followUpVO);

	//public FollowUpVO getUserRole(FollowUpVO followUpVO);

	public FollowUpVO getListCases(FollowUpVO followUpVO);

	public List<CommonDtlsVO> getPatientCommonDtls(String lStrCaseId, String followUpId);

	public List<FollowUpVO> getFollowUpList(String lStrCaseId,String lStrFollowUpId);

	public FollowUpVO getFollowUPDtls(FollowUpVO followUpVO);

	public List<ClaimsFlowVO> getAuditTrail(FollowUpVO followUpVO);

	public String checkValidForFollowup(String lStrCaseId);

	public List<AttachmentVO> getFollowUPAttach(String followUpId);

	public List<LabelBean> getFPCasesForPayments(FollowUpVO followUpVO);

	public List<LabelBean> getFPCaseStatus(String userName);
	CommonDtlsVO getFPDtlsForPayment(String caseNo,String lStrFollowupId);
	
	HashMap updateFPClaimStatus(HashMap hParamMap)  throws Exception;

	public List<FollowUpVO> getClinicalData(FollowUpVO followUpVO);

	//public List<DrugsVO> getDrugsDtls(FollowUpVO followUpVO);

	public List<LabelBean> getCaseStatus();

	public PatientVO getPatientDtls(String patientId);

	public void setviewFlag(String followUpId, String flag);

	public String checkMandatoryAttch(String followUpId, String attachType);
	
	public String getPatientIdFromCaseId(String caseId);
	
	public LabelBean getDTRSData(String caseId);
	
	public Long getNextFollowUpId(FollowUpVO followUpVo);

	public String updateFPClaimStatusReady(ClaimsFlowVO claimFlowVO);
	public List<String> getDrugSubList(String drugTypeCode);
	public String getDrugDetails(String drugCode);
	public List<String> getInvestigations(String lStrBlockId);
	
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
	 *This method is used to get the comparative documents for the followup 
	 */
	public List<AttachmentVO> getFollowUPCompDocs(String lStrCaseId,String type);
	
	/*
	 * This method is used to get the Approval Cases for follopwUp without Pagination
	 */
	public List<CasesSearchVO> getAllListCases(FollowUpVO followUpVO);
	
	public List<LabelBean> getFPCaseStatusNew(String userGroup);
	
	public FollowUpVO getFollowupDtls(FollowUpVO followUpVO) ;
	
	/*
	 * Added by Srikalyan for Saving
	 * Cochlear FollowUp Details
	 */
	public FollowUpVO saveCochlearFollowUpDetails(FollowUpVO followUpVO);
	
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
	

	 
	 public List<LabelBean> getFPCaseStatusByGrp(String grpId);

	
	/*
	 * Added by Srikalyan to get Cochlear FollowUp Claim cases 
	 */
	 public FollowUpVO getCochlearClaimCases(FollowUpVO followUpVO);
	 
	 /*
	 * Added by Srikalyan to get Cochlear FollowUp Claim case Details
	 */
	 public FollowUpVO getCochlearFlpCase(String caseId,String followUpId);
	 
	 /*
	 * Added by Srikalyan to get previous Cochlear 
	 * FollowUp Claim case Details
	 */
	 public List<FollowUpVO> getPrevCochlearFlpDtls(String caseId,String followUpId);
	 
	 /*
	 * Added by Srikalyan to get Common Cochlear 
	 * FollowUp Claim case Details
	 */
	 public FollowUpVO getCommonDtls(String caseId,String followUpId);

	 /*
	 * Added by Srikalyan to Save Cochlear 
	 * FollowUp Claim for Each Level in Workflow
	 */
	 public FollowUpVO saveCochlearFlpClaim(FollowUpVO followUpVO);
	 
	 /*
	 * Added by Srikalyan to Save Cochlear 
	 * FollowUp Claim for Send Back Cases 
	 */
	 public FollowUpVO saveCochlearFlpClaimSendBck(FollowUpVO followUpVO);
	 
	 /*
	 * Added by Srikalyan to Get Cochlear 
	 * FollowUp Claim Details for Each Level in Workflow
	 */
	 public List<FollowUpVO> getCochlearClaimWorkFlow(String caseId,String followUpId);
	 
	 /*
	 * Added by Srikalyan to Check previous 
	 * Cochlear FollowUp Claims Status
	 */
	 public String checkInitiatationStatus(String caseId,String followUpId);
	 
	 /*
	 * Added by Srikalyan to get the  
	 * Cochlear FollowUp Claims Status
	 */
	 public List<FollowUpVO> getFlpStatusLst();
	 
	 /*
	 * Added by Srikalyan for getting
	 * Cochlear FollowUp Code
	 */
	public FollowUpVO getCochFlpProcCode(String surgeryId,String schemeId,int cochlearCount);

	/*
	 * Added by Srikalyan for getting
	 * Send Back Details of Cochlear FollowUp Claim
	 */
	public FollowUpVO getSendBackDtls(String followUpId);
	
	/*
	 * Added by Srikalyan for getting
	 * Case Status of Cochlear FollowUp Claim
	 */
	public FollowUpVO getCaseStatus(String followUpId);
	
	/*
	 * Added by Srikalyan for getting
	 * Previous Cochlear Followup's dates
	 */
	public FollowUpVO getPrevDateDtls(int cochlearCount,String caseId);	
	
	/*
	 * Added by Srikalyan to get the CSV Download for Cochlear Follow Up Cases  
	 */
	public StringBuilder cochCSVDownload(FollowUpVO flpVO);
		
	/*
	 * Added by Srikalyan to get the Scheme Id Based on Follow Up ID 
	 */
	public String getSchemeId(String followUpId);
	
	
	/*
	 * Added by Srikalyan to get the Medco Name Based on Follow Up ID 
	 */
	public String getCrtUsrMedco(String followUpId);
	public String getfollowupFtdFlag(String followUpId);
}
