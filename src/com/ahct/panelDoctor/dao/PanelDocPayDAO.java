package com.ahct.panelDoctor.dao;


import java.util.HashMap;
import java.util.List;

import com.ahct.panelDoctor.vo.PanelDocPayVO;


public interface PanelDocPayDAO {



	List<PanelDocPayVO> generatepanelDocCases(PanelDocPayVO panelDocPayVO, String currLevel, String prevGrp,int startIndex,int maxResults);

	List<PanelDocPayVO> generatepanelDocDtlCasesList(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);

	List<PanelDocPayVO> generateAllSelCasesDetails(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);
	
	List<PanelDocPayVO> generateCasesByWrkFlow(PanelDocPayVO panelDocPayVO, String prevGrpId);

	List<PanelDocPayVO> generatecaseCountStatus(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);

	String getStatus(String actionPerformed);

	String generateGrpId(String grpName);

	List<PanelDocPayVO> generateAllRejCaseDtls(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults);

	

	List<PanelDocPayVO> generatepanelDocCasesForCEO(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults);

	List<PanelDocPayVO> generateTDSCases(PanelDocPayVO panelDocPayVO);

	//HashMap updateTDSClaimStatus(HashMap hParamMap);

	void updateClaimStatusSentByBank();

	List<PanelDocPayVO> generateAllBankRejCases(String scheme,int startIndex,int maxResults);

	List<PanelDocPayVO> generateAllRejCasesCEO(String scheme,int startIndex,int maxResults);

	HashMap updatePanelDocPayStatus();

	List<PanelDocPayVO> getSchemeStatus();

	public void panelDocInitialisation();

	public String insertWrkFlowRecord(List<PanelDocPayVO> panelDocCases,PanelDocPayVO panelDocPayVOWrkLst);
	
	public String modifyPnlDocCaseDtls(PanelDocPayVO panelDocPayVOWrkLst);

	public void panelDocInitialisationOld();

	
	



}
