package com.ahct.claims.service;

import java.util.HashMap;
import java.util.List;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;



// TODO: Auto-generated Javadoc
/**
 * The Interface ClaimsFlowService.
 *
 * @author Ishank Paharia
 * @class This Interface is used for claim
 * @version jdk 1.6
 * @Date  4 April 2013
 */
public interface ClaimsFlowService {

	/**
	 * Gets the case dtls.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the case dtls
	 */
	ClaimsFlowVO getCaseDtls(ClaimsFlowVO claimFlowVO);

	/**
	 * Save claim.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the claims flow vo
	 * @throws Exception the exception
	 */
	ClaimsFlowVO saveClaim(ClaimsFlowVO claimFlowVO) throws Exception;

	/**
	 * Gets the audit trail dtls for worklist.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the audit trail
	 */
	List<ClaimsFlowVO> getAuditTrail(ClaimsFlowVO claimFlowVO);

	/**
	 * Gets the patient dtls.
	 *
	 * @param patientId the patient id
	 * @return the patient dtls
	 */
	PatientVO getPatientDtls(String patientId);

	/**
	 * Save err claim.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the claims flow vo
	 */
	ClaimsFlowVO saveErrClaim(ClaimsFlowVO claimFlowVO);

	/**
	 * Gets the cases for payments.
	 *
	 * @param claimsFlowVO the claims flow vo
	 * @return the cases for payments
	 */
	List<LabelBean> getCasesForPayments(ClaimsFlowVO claimsFlowVO);

	/**
	 * Gets the case status.
	 * @param userName 
	 *
	 * @return the case status
	 */
	public List<LabelBean> getCaseStatus(String userName);
	
	/**
	 * Gets the err case status.
	 * @param userName 
	 *
	 * @return the err case status
	 */
	public List<LabelBean> getErrCaseStatus(String userName);
	
	/**
	 * Gets the case dtls for payment.
	 *
	 * @param caseNo the case no
	 * @param paymentType the payment type
	 * @return the case dtls for payment
	 */
	CommonDtlsVO getCaseDtlsForPayment(String caseNo,String paymentType);

	/**
	 * Update claim status.
	 *
	 * @param hParamMap the h param map
	 * @return the hash map
	 */
	HashMap updateClaimStatus(HashMap hParamMap);
	
	/**
	 * Gets the err cases for payments.
	 *
	 * @param claimsFlowVO the claims flow vo
	 * @return the err cases for payments
	 */
	public List<LabelBean> getErrCasesForPayments(ClaimsFlowVO claimsFlowVO);

	/**
	 * Gets the tDS cases for payments.
	 *
	 * @param claimsFlowVO the claims flow vo
	 * @return the tDS cases for payments
	 */
	public List<LabelBean> getTDSCasesForPayments(ClaimsFlowVO claimsFlowVO);

	/**
	 * Gets the tds payment type.
	 *
	 * @return the tds payment type
	 */
	public List<LabelBean> getTdsPaymentType();

	/**
	 * Update tds claim status.
	 *
	 * @param hParamMap the h param map
	 * @return the hash map
	 */
	public HashMap updateTDSClaimStatus(HashMap hParamMap);

	/**
	 * Check disscussion case flag.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the string
	 */
	public String checkDissCaseFlag(ClaimsFlowVO claimFlowVO);

	/**
	 * Calculate claim amount in ramco login
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the claims flow vo
	 */
	public ClaimsFlowVO calculateClaimAmount(ClaimsFlowVO claimFlowVO);

	public String updateClaimStatusReady(ClaimsFlowVO claimFlowVO);

	public List<LabelBean> getRfCasesForPayments(ClaimsFlowVO claimsFlowVO);

	public String updateTDSStatusReady(ClaimsFlowVO claimFlowVO);
	
	public String checkFlaggingForMoneyCollection(ClaimsFlowVO claimFlowVO);
	
	public List<LabelBean> getChronicCasesForPayments(ClaimsFlowVO claimsFlowVO);
	
	public List<LabelBean> getChronicStatus(String userName);
	
	public String updateChronicClaimStatusReady(ClaimsFlowVO claimFlowVO);
	
	
	/*
	 * Added by Srikalyan to get EhfCase Object
	 */
	public com.ahct.model.EhfCase getCase(String caseId);
	
	
	public String getCaseStat(String caseId);
	
	/*Added by venkatesh for reject claim entries
	
	 */
	
	public List<LabelBean> getErrCaseStatusByGrp(String grpId);
	
	public List<LabelBean> getCaseStatusByGrp(String grpId);
	int getCount(String caseId,String roleId);

	int getDentalErrClm(String caseId);
	
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
	  * @return List of PreauthVO
	  * @method Get the case Surgical Details   
	  */
	public List<PreauthVO> getcaseSurgertDtls(String caseId);
	ClaimsFlowVO getUniquePrices(ClaimsFlowVO claimFlowVO);

	ClaimsFlowVO getTotalPrice(ClaimsFlowVO claimFlowVO) throws Exception;;

	String getNabhAmnt(String caseId);
	
	String getTherapyId(String caseId);

	public String getorgFlag(String caseId);
	public List<LabelBean> getOpdClaimCasesListForACO(HashMap hashMap);//Chandana - 8602 - New method for getting the claim cases list
	public String getNIMSOPDCaseStat(String caseId);//Chandana - 8602 - New method
	ClaimsFlowVO saveOpdClaim(ClaimsFlowVO claimFlowVO) throws Exception;//Chandana - 8602 - New method for saving the details submitted by ACO
	public String pendingOpClaimByACO(HashMap hashMap) throws Exception;//Chandana - 8602 - New method for keep pending the claim case by ACO
}
