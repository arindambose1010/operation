package com.ahct.claims.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.CommonDtlsVO;


/**
 * The Interface ClaimsFlowDAO.
 *
 * @author Ishank Paharia
 * @version jdk 1.6
 * The Interface ClaimsFlowDAO which is used for Claim Process
 * @Date  4 April 2013
 */
public interface ClaimsFlowDAO {

	/**
	 * Save claim.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the claims flow vo
	 * @throws Exception the exception
	 */
	ClaimsFlowVO saveClaim(ClaimsFlowVO claimFlowVO) throws Exception;

	/**
	 * Save err claim.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return the claims flow vo
	 */
	ClaimsFlowVO saveErrClaim(ClaimsFlowVO claimFlowVO);

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
	@SuppressWarnings("rawtypes")
	HashMap updateClaimStatus(HashMap hParamMap);

	/**
	 * Update tds claim status.
	 *
	 * @param hParamMap the h param map
	 * @return the hash map
	 */
	@SuppressWarnings("rawtypes")
	HashMap updateTDSClaimStatus(HashMap hParamMap);

	/**
	 * Update claim status sent by bank.
	 */
	void updateClaimStatusSentByBank();
	
	public String updateClaimStatusReady(ClaimsFlowVO claimFlowVO);
	
	public void generateFile();
	
	public void generateERRFile();
	
	public void generateFollowUpFile();

	public String updateTDSStatusReady(ClaimsFlowVO claimFlowVO);
	
	public String updateChronicClaimStatusReady(ClaimsFlowVO claimFlowVO);
	
	public boolean getPaymentSlab(HashMap lparamMap) throws Exception;
	ClaimsFlowVO getTotalPrice(ClaimsFlowVO claimFlowVO) throws Exception;
	
	
	/**
	 * @author Namratha
	 * @Method Triggered by Scheduler for Generating Payment Files 
	 * 		   for Journalist Payments  
	 */
	public void jrnlstGenerateFile();
	public ClaimsFlowVO getInvestigationDetails(ClaimsFlowVO claimsFlowVO) throws Exception;

	public ClaimsFlowVO getDrugDetails(ClaimsFlowVO claimFlowVO);
	void updateSurplusDeductions();
	
	public HashMap insertIntoPaymentTable(ArrayList lContentList,HashMap lparamMap);
	public List<LabelBean> getOpdClaimCasesListForACO(HashMap hashMap);//Chandana - 8602 - New method
	ClaimsFlowVO saveOpdClaim(ClaimsFlowVO claimFlowVO) throws Exception;//Chandana - 8602 - Added new method
	public String pendingOpClaimByACO(HashMap hashMap) throws Exception;//Chandana - 8602 - New method for keep pending the claim case by ACO
}
