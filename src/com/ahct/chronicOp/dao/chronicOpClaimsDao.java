package com.ahct.chronicOp.dao;

import com.ahct.claims.valueobject.ClaimsFlowVO;

public interface chronicOpClaimsDao {

	
	/*public String saveClaimDtls(ClaimsFlowVO claimFlowVO);

	public String updateClaimDtlsforPayment(ClaimsFlowVO claimFlowVO);*/

	public void generateChronicFile();

	public void updateClaimStatusSentByBank();
	
}
