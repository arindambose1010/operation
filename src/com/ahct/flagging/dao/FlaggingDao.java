package com.ahct.flagging.dao;

import java.util.List;
import java.util.Map;
import com.ahct.common.vo.LabelBean; 
import com.ahct.flagging.vo.FlaggingVO;


public interface FlaggingDao 
{
	/*
	 *Function
	 *to get
	 *the flags in Dao*/
	public List<LabelBean> getFlagList();
	
	/*
	 * Function
	 * to get 
	 * Districts*/
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
	 * to retrive 
	 * the flagged Cases*/
	public List<FlaggingVO> getFlaggedCases(FlaggingVO flaggingVO);
	
	
	/*
	 * Function
	 * to get the
	 * FlagAttachments */
	public List<FlaggingVO> getflagAttach(String caseId,String flagId,String flagDocId);
	
	/*
	 * Function
	 * to get all
	 * Flagged Cases */
	public List<FlaggingVO> getAllFlaggedCases(FlaggingVO flaggingVO);
	
	/*
	 * Function
	 * to get all
	 * Authority */
	public FlaggingVO checkAuthority(List<LabelBean> rolesList);
	/*
	 * Function
	 * to get 
	 * Non Deflagged Cases*/
	public Number getNoOfFlaggedCases(String lStrEmpId);
	/*
	 * Function
	 * to get 
	 * Flagged cases Colour*/
	public FlaggingVO getFlaggedCasesForColor(String caseId);
	
	/*
	 * Function
	 * to update 
	 * the money collection cases*/
	public void changeMoneyCollectionFlow();
	
	/*
	 * Function
	 * to get
	 * the Flag DocId*/
	public String getFlagDocId();
	
	/*
	 * Function
	 * to check 
	 * Case is Flagged*/
	public FlaggingVO checkCaseFlagged(String caseId);

	/*
	 * Function to execute 
	 * Normal HQL Query */
	public Map<String,Object> executeNormalHQLQuery(String query , String classPath);
	public List<FlaggingVO> getDistrictsNew(String stateId, String patStateType);
	
}

