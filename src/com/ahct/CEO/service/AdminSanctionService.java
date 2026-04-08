package com.ahct.CEO.service;

import java.util.List;

import com.ahct.CEO.vo.AdminSanctionRemarksVO;
import com.ahct.CEO.vo.AdminSanctionVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAdminSanctionsMetaData;

public interface AdminSanctionService {

	List<LabelBean> getDeptDetails();

	List<LabelBean> getSchemeList(String lStrUserState);

	List<LabelBean> getDetailedHeadList(String lstrSchemeId);

	String getHeadsDetails(String lstrSchemeId, String detailedHead);

	List<LabelBean> getVendorList();

	List<LabelBean> getIssuingAuthName(String issuingAuthority, String lStrUserState);

	List<LabelBean> getBudgetSourceList(String stateFlag);

	String getSanctionId(String userType);

	String getGrpId(String workFlowId);

	String saveSanctionDetails(AdminSanctionVO adminSanctionVO);

	String getWorkFlowId(String sanctionId);

	List<AdminSanctionVO> getAdminSancRequests(List<LabelBean> grplist,String state);

	List<AdminSanctionVO> getAdminSancworkList(
			List<AdminSanctionVO> adminSancListTemp);

	String getAttachStatus(String sanctionId);

	EhfAdminSanctionsMetaData getSanctionDeatils(String sanctionId);

	String decideLabelValue(String sanctionId);

	List<AdminSanctionRemarksVO> getPreviousRemarks(String sanctionId);

	List<AdminSanctionVO> findSanctionRequestStatus(String state, String lStrEmpId);

	LabelBean getDepartment(String department);
	
	 Long getSanctionAmountByMonth();

	String verifyFinalLevelApproval(String sanctionId);

	List<AdminSanctionVO> findSancRequests(String sanctionId, String reqStatus,
			String startDate, String endDate, String lStrUserState, String lStrEmpId);

	List<AdminSanctionVO> getCrtUsr(String state);

	String verifyIfDyEo(List<LabelBean> grpList);

	String getAdminSancApprovedDate(String sanctionId);

	List<AdminSanctionVO> getCeoAdminSancRequests(List<LabelBean> grplist,
			String lStrUserState);

	List<LabelBean> viewAttachments(String sanctionId);

	String getAttachPath(String lStrtransid, String lStrFileName);

}
