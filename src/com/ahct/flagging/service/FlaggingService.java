package com.ahct.flagging.service;
import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.model.EhfCase;

public interface FlaggingService 
{
	/*
	 *Function
	 *to get
	 *the flags */
	public List<LabelBean> getFlagList();
	
	/*
	 *Function
	 *to get
	 *the districts */
	public List<FlaggingVO> getDistricts(String stateId);
	
	/*
	 * Function
	 * to get 
	 * Districts*/
	public List<FlaggingVO> getHospList(String stateId,String distId,String hospType);
	
	/*
	 * Function 
	 * to save the 
	 * Flag Details*/
	public String saveFlagDtls(FlaggingVO flaggingVO);
	
	/*
	 * Function 
	 * to get the
	 * flagged cases*/
	public List<FlaggingVO> getFlaggedCases(FlaggingVO flaggingVO);
	
	/*
	 * Function
	 * to get the
	 * FlagAttachments */
	public List<FlaggingVO> getflagAttach(String caseId,String flagId,String flagDocId);
	
	/*
	 * Function
	 * to get 
	 * All Flagged Cases*/
	public List<FlaggingVO> getAllFlaggedCases(FlaggingVO flaggingVO);
	/*
	 * Function
	 * to get 
	 * Authority*/
	public FlaggingVO checkAuthority(List<LabelBean> rolesList);
	/*
	 * Function
	 * to get 
	 * Non Deflagged Cases*/
	public Number getNoOfFlaggedCases(String lStrEmpId);
	/*
	 * Function
	 * to get 
	 * Flagged cases for Color*/
	public FlaggingVO getFlaggedCasesForColor(String caseId);
	
	public String getFlagDocId();
	
	/*
	 * Function
	 * to check 
	 * Case is Flagged*/
	public FlaggingVO checkCaseFlagged(String caseId);
	
	/*
	 * Function to check the  
	 * authority for DC DM TL */
	public FlaggingVO checkDCTLDMAuthority(FlaggingVO flaggingVO);
	
	
	/*
	 * Function to get Case Details
	 */
	public FlaggingVO getCaseDtls(String caseId);
	public List<LabelBean> getCancelledCases(String userHospid);
	public List<FlaggingVO> getDistrictsNew(String stateId, String patStateType);
}
