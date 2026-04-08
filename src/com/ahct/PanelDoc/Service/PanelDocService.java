package com.ahct.PanelDoc.Service;

import java.util.List;

import com.ahct.PanelDoc.VO.panelDocVo;

public interface PanelDocService {

	public panelDocVo getPanelDocLst(panelDocVo panelDocVo);
	
	public String saveAccountDetails(panelDocVo panelDocVo);
	
	public String validatePnlDoc(String loginName);
	
	public String getIfscCode(String bankId);
	
	public List<panelDocVo> bankBranchList(String BankName);
	
	public List<panelDocVo> banksList();
	
	public panelDocVo getPanelDocDetails(String UserId);
	
	public String getUserId(String loginName);
}
