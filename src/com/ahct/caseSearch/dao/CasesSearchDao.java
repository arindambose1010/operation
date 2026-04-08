package com.ahct.caseSearch.dao;

import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;

public interface CasesSearchDao
{
	public CasesSearchVO getFeedbackList(CasesSearchVO casesSearchVO);
	public CasesSearchVO getListCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getTeleIntimationCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getRegTeleIntimationCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getListIssues(CasesSearchVO casesSearchVO);
	public List<CasesSearchVO> getDeathCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getListCasesDirect(CasesSearchVO casesSearchVO);
	/*
	 * Added by Srikalyan for CSV Downloads Query Insertion
	 */
	public CasesSearchVO getListCasesForCSV(CasesSearchVO casesSearchVO);
	public CasesSearchVO getCasesSearchCSV(CasesSearchVO casesSearchVO);
	List<CasesSearchVO> getPreauthApprovedCases(String empPenId,String healthCardNum);
}
