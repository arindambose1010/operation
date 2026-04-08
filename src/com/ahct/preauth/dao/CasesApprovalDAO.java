package com.ahct.preauth.dao;

import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;

public interface CasesApprovalDAO {
	//public Map getCaseDtls(HashMap lparamMap);
	public String getOnlinecaseSheetFlag(String caseId);
	public List<CasesSearchVO> getAllCaseSearchDetails(CasesSearchVO vo) ;
	public List<CasesSearchVO> getCaseSearchDetails(CasesSearchVO vo);
	public List<CasesSearchVO> getAllCaseSearchDetailsForPen(CasesSearchVO casesSearchVO);
}
