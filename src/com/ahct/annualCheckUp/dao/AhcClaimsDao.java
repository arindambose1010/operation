package com.ahct.annualCheckUp.dao;

import com.ahct.claims.valueobject.ClaimsFlowVO;

public interface AhcClaimsDao {

	public String saveClaimDtls(ClaimsFlowVO claimFlowVO);

	public String updateClaimDtlsforPayment(ClaimsFlowVO claimFlowVO);

	public void generateAHcFile();

	public void updateClaimStatusSentByBank();

}
