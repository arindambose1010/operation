package com.ahct.panelDoctor.service;


import java.util.HashMap;
import com.ahct.common.vo.LabelBean;
import java.util.List;
import java.util.Map;

import com.ahct.panelDoctor.vo.PanelDocPayVO;

public interface PanelDocPayService {



	List<PanelDocPayVO> getPanelDocCases(PanelDocPayVO panelDocPayVO, String currLevel, String prevGrp, int startIndex,int maxResults);

	List<PanelDocPayVO> getPanelDocDtlCasesList(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);

	List<PanelDocPayVO> getAllSelCasesDetails(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);
	
	List<PanelDocPayVO> getCasesByWrkFlow(PanelDocPayVO panelDocPayVO, String prevGrpId);

	PanelDocPayVO insertAuditRecord(List<PanelDocPayVO> panelDocCasesDetails, String userId, PanelDocPayVO panelDocPayVO);

	List<PanelDocPayVO> getCaseCountStatus(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);

	String getgrpId(String grpName);

	String getgrpName(String grpId);
	
	List<PanelDocPayVO> getAllRejctdCasesDtls(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults);

	Map updatePanelDocPayStatus(HashMap hParamMap);

	List<PanelDocPayVO> getPanelDocCasesForCEO(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults);

	List<PanelDocPayVO> getPanelDocTDSCases(PanelDocPayVO panelDocPayVO);

	//Map updateTDSClaimStatus(HashMap hParamMap);

	PanelDocPayVO updateApprvdRecord(List<PanelDocPayVO> panelDocCasesDetails,
			String userId, PanelDocPayVO panelDocPayVO);
	
	PanelDocPayVO updateRejectRecord(List<PanelDocPayVO> panelDocCasesDetails,
			String userId, PanelDocPayVO panelDocPayVO);


	List<PanelDocPayVO> getAllBankRejctdCases(String scheme,int startIndex,int maxResults);

	List<PanelDocPayVO> getAllRejctdCasesDtlsCEO(String scheme,int startIndex,int maxResults);
	
	boolean updateStatus(PanelDocPayVO panelDocPayVO) throws Exception;

	boolean updateTDSStatus(PanelDocPayVO panelDocPayVO);

	public String updateCaseDtls(List<PanelDocPayVO> panelDocCasesDetails,String action,String userId);

	/* Added by Srikalyan remarks for 
	 * worklists in Panel Doctor payments for CEO
	 * */
	public List<PanelDocPayVO> getCEORemarksList(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId);
	
	/***
	 * @author Kalyan
	 * @Param User to Which the Statuses needs to be populated
	 * @return Returns all the statuses for a User to Search upon. 
	 */
	public List<LabelBean> getCaseStatus(String user);
	
	/***
	 * @author Kalyan
	 * @Param Payment Status and Current user Grp or Panel Doctor details 
	 * @return Returns all the Payments related to Search Criteria  
	 */
	public List<PanelDocPayVO> getPanelDocCasesSearch(PanelDocPayVO panelDocPayVO);
	public String getLoginName(String grpId);
	public String getDocId(Long docId);
	public List<PanelDocPayVO> getPnlDocSpclty(String docId);
	public String getPnlDocAmt(Long wId,String docId);
	public String getDoctorName(String docId);
	public List<PanelDocPayVO> generateCasesByWrkFlow(PanelDocPayVO panelDocPayVO, String prevGrpId);
}
