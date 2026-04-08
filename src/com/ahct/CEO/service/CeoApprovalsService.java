package com.ahct.CEO.service;

import java.util.ArrayList;
import java.util.List;

import com.ahct.CEO.vo.ChangeMgmtVO;
import com.ahct.CEO.vo.SQLChangeMgmtTransVO;

public interface CeoApprovalsService {

	public Long getNextLineItemNoForMvmt ( String pstrCrId ) throws Exception;
	  public Long getNextLineItemNoForRemarks ( String pstrCrId ) throws Exception;
	  public boolean getNextUnitAndSave(String lStrBtntype,String crIdList,String lStrCrId,String lStrUser,String lStrUserState)throws Exception;
	  public List<SQLChangeMgmtTransVO> getCeoWorkList(String lStrUserState);
	  public Long getNextLineItemNoAttach ( String pstrCrId ) throws Exception;
	public ChangeMgmtVO getCrRequestDetails(String crId);
	public ArrayList getActionWiseAllRemarks(String parameter, String lStrEmpId);
	public List<ChangeMgmtVO> getAllAttachments(String parameter) throws Exception;
	public com.ahct.model.SgvaCRMgmtAttach getAttachment(String pStrCRId,String lineItemNo);

	  
	
	
}
