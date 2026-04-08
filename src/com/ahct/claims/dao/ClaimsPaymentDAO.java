package com.ahct.claims.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ahct.claims.valueobject.TransactionVO;



/**
 * The Interface ClaimsPaymentDAO.
 *
 * @author Ishank Paharia
 * @version jdk 1.6
 * The Interface ClaimsPaymentDAO which is used for Claim Payment Process
 * @Date  4 April 2013
 */
public interface ClaimsPaymentDAO {

	/**
	 * Calculate claim percentage.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	public boolean calculateClaimPercentage(HashMap lparamMap) throws Exception;
	
	/**
	 * Insert upload file.
	 *
	 * @param lparamMap the lparam map
	 * @return the array list
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList insertUploadFile(HashMap lparamMap) throws Exception;
	
	/**
	 * Pay tds fund.
	 *
	 * @param lmap the lmap
	 * @return the hash map
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	public HashMap payTDSFund(HashMap lmap) throws Exception;
	 
 	/**
 	 * Sets the tDS audit details.
 	 *
 	 * @param lparamMap the new tDS audit details
 	 * @throws Exception the exception
 	 */
 	@SuppressWarnings("rawtypes")
	public void setTDSAuditDetails(HashMap lparamMap) throws Exception;
	
	/**
	 * Sets the claim status.
	 *
	 * @param lparamMap the lparam map
	 * @return the string
	 */
	@SuppressWarnings("rawtypes")
	public String SetClaimStatus(HashMap lparamMap);
	
	/**
	 * Process erroneous claims.
	 *
	 * @param lparamMap the lparam map
	 * @return the string
	 */
	@SuppressWarnings("rawtypes")
	public String processErroneousClaims(HashMap lparamMap);
	
	/**
	 * Sets the followup claim status.
	 *
	 * @param lparamMap the lparam map
	 * @return the string
	 */
	@SuppressWarnings("rawtypes")
	public String SetFollowupClaimStatus(HashMap lparamMap);
	
	/**
	 * Calculate claim percentage new.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	public boolean calculateClaimPercentageNew(HashMap lparamMap) throws Exception;
	
	/**
	 * Submit claim payments in accounts.
	 *
	 * @param transactionVO the transaction vo
	 * @return the transaction vo
	 * @throws ParseException the parse exception
	 */
	public TransactionVO submitClaimPaymentsInAccounts(TransactionVO transactionVO) throws ParseException;
	
	/**
	 * Submit tds or rf payments in accounts.
	 *
	 * @param transactionVO the transaction vo
	 * @return the transaction vo
	 * @throws ParseException the parse exception
	 */
	public TransactionVO submitTdsOrRfPaymentsInAccounts(TransactionVO transactionVO) throws ParseException;

	public TransactionVO submitClaimPaymentsInAccountsForRej(TransactionVO transactionVO) throws ParseException;

	public TransactionVO submitTdsOrRfPaymentsInAccountsForRej(TransactionVO transactionVO);
	
	public TransactionVO submitChronicClaimPaymentsInAccounts(TransactionVO transactionVO) throws ParseException;

	public TransactionVO submitTdsOrRfSurplusPaymentsInAccounts(
			TransactionVO transactionVO) throws ParseException;
	/**
	 * Pay surplus tds fund  for tDS exemption hospitals
	 *
	 * @param lmap the lmap
	 * @return the hash map
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	public HashMap payTDSFundSurplus(HashMap ldataMap) throws Exception;

	public String SetJHSClaimStatus(HashMap lparamMap);

	boolean calculateClaimPercentageSurplus(HashMap lparamMap) throws Exception;
	
	public int getSetId(HashMap lparamMap) throws Exception;
	
}
