package com.ahct.annualCheckUp.service;

import java.util.List;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAhcCaseClaim;
import com.ahct.model.EhfAhcTdChklst;
import com.ahct.model.EhfAhcexChklst;
import com.ahct.model.EhfAnnualCaseDtls;

public interface AhcClaimsService {

	public EhfAnnualCaseDtls getAhcStatusDtls(String ahcId);

	public List<ClaimsFlowVO> getAuditTrail(String ahcId);

	public String checkMandatoryAttch(String ahcId, String attachType);

	public String saveAhcClaim(ClaimsFlowVO claimFlowVO);

	public EhfAhcexChklst getExeRemarks(String ahcId);

	public List<LabelBean> getCasesForPayments(ClaimsFlowVO claimsFlowVO);

	public String updateClaimDtlsforPayment(ClaimsFlowVO claimFlowVO);

	public EhfAhcTdChklst getTdRemarks(String ahcId);
	
	public EhfAhcCaseClaim getAhcCaseClaimDtls(String ahcId);
	
}
