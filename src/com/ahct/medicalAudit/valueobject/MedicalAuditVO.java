package com.ahct.medicalAudit.valueobject;

import java.util.Date;
import java.util.List;

public class MedicalAuditVO {

	private String result;
	private String remarks;
	private String CASEID;
	private String userId;
	private String prevWorkflowId;
	private String currentWorkflowId;
	private String currentOwnerGrp;
	private String previousOwnerGrp;
	private String actionType;
	private String selectionType;
	
	private String CASENO;
	private String RATIONCARDNO;
	private String CLAIMNO;
	private String DISTRICTNAME;
	private String HOSPITALNAME;
	private String SPECIALITYNAME;
	private String GRP;
	private String PATIENTNAME;
	private Number CLAIMAMOUNT;
	private String district;
	private String hospType;
	private char hospType1;
	private String groupType;
	private String hospId;
	private String specialityId;
	private String categoryId;
	private String procedureId;
	private String PROCEDURENAME;
	private String patientId;
	private String schemeId;
	private List<String> grpLst;
	private List<String> gpLst;
	private String finalRemarks;
	private String updatedBy;
	private String remarksDt;
	private String prevRemarks;
	private String role;
	private String workFlowRemarks;
	private String caseType;
	private String userState;
	private String auditedBy;
	
	private int start_index;
	private int end_index;
	
	
	private String caseId;
	private String caseNo;
	private String claimNo;
	private String districtCode;
	private Long claimPaidAmt;
	private Date crtDt;
	private String quarter;
	private String year;
    private String highCost;
    private String highVolume;
    private String highCostAuditCase;
    private String highVolumeAuditCase;
    private String group;
    private String workflowStatus;
    private String cmaDeoFlag;
    private String fromDate;
	private String toDate;

	
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getWorkFlowRemarks() {
		return workFlowRemarks;
	}
	public void setWorkFlowRemarks(String workFlowRemarks) {
		this.workFlowRemarks = workFlowRemarks;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPrevRemarks() {
		return prevRemarks;
	}
	public void setPrevRemarks(String prevRemarks) {
		this.prevRemarks = prevRemarks;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getRemarksDt() {
		return remarksDt;
	}
	public void setRemarksDt(String remarksDt) {
		this.remarksDt = remarksDt;
	}
	public String getFinalRemarks() {
		return finalRemarks;
	}
	public void setFinalRemarks(String finalRemarks) {
		this.finalRemarks = finalRemarks;
	}
	public List<String> getGrpLst() {
		return grpLst;
	}
	public void setGrpLst(List<String> grpLst) {
		this.grpLst = grpLst;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getPROCEDURENAME() {
		return PROCEDURENAME;
	}
	public void setPROCEDURENAME(String pROCEDURENAME) {
		PROCEDURENAME = pROCEDURENAME;
	}
	public String getCASENO() {
		return CASENO;
	}
	public void setCASENO(String cASENO) {
		CASENO = cASENO;
	}
	public String getCASEID() {
		return CASEID;
	}
	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}
	public String getSpecialityId() {
		return specialityId;
	}
	public void setSpecialityId(String specialityId) {
		this.specialityId = specialityId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	
	
	public String getRATIONCARDNO() {
		return RATIONCARDNO;
	}
	public void setRATIONCARDNO(String rATIONCARDNO) {
		RATIONCARDNO = rATIONCARDNO;
	}
	
	public String getCLAIMNO() {
		return CLAIMNO;
	}
	public void setCLAIMNO(String cLAIMNO) {
		CLAIMNO = cLAIMNO;
	}
	
	public String getDISTRICTNAME() {
		return DISTRICTNAME;
	}
	public void setDISTRICTNAME(String dISTRICTNAME) {
		DISTRICTNAME = dISTRICTNAME;
	}
	
	public String getHOSPITALNAME() {
		return HOSPITALNAME;
	}
	public void setHOSPITALNAME(String hOSPITALNAME) {
		HOSPITALNAME = hOSPITALNAME;
	}
	public String getSPECIALITYNAME() {
		return SPECIALITYNAME;
	}
	public void setSPECIALITYNAME(String sPECIALITYNAME) {
		SPECIALITYNAME = sPECIALITYNAME;
	}
	public String getGRP() {
		return GRP;
	}
	public void setGRP(String gRP) {
		GRP = gRP;
	}
	public String getPATIENTNAME() {
		return PATIENTNAME;
	}
	public void setPATIENTNAME(String pATIENTNAME) {
		PATIENTNAME = pATIENTNAME;
	}
	
	public Number getCLAIMAMOUNT() {
		return CLAIMAMOUNT;
	}
	public void setCLAIMAMOUNT(Number cLAIMAMOUNT) {
		CLAIMAMOUNT = cLAIMAMOUNT;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSelectionType() {
		return selectionType;
	}
	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getCurrentOwnerGrp() {
		return currentOwnerGrp;
	}
	public void setCurrentOwnerGrp(String currentOwnerGrp) {
		this.currentOwnerGrp = currentOwnerGrp;
	}
	public String getPreviousOwnerGrp() {
		return previousOwnerGrp;
	}
	public void setPreviousOwnerGrp(String previousOwnerGrp) {
		this.previousOwnerGrp = previousOwnerGrp;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPrevWorkflowId() {
		return prevWorkflowId;
	}
	public void setPrevWorkflowId(String prevWorkflowId) {
		this.prevWorkflowId = prevWorkflowId;
	}
	public String getCurrentWorkflowId() {
		return currentWorkflowId;
	}
	public void setCurrentWorkflowId(String currentWorkflowId) {
		this.currentWorkflowId = currentWorkflowId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getStart_index() {
		return start_index;
	}
	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}
	public int getEnd_index() {
		return end_index;
	}
	public void setEnd_index(int end_index) {
		this.end_index = end_index;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public Long getClaimPaidAmt() {
		return claimPaidAmt;
	}
	public void setClaimPaidAmt(Long claimPaidAmt) {
		this.claimPaidAmt = claimPaidAmt;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getHighCost() {
		return highCost;
	}
	public void setHighCost(String highCost) {
		this.highCost = highCost;
	}
	public String getHighVolume() {
		return highVolume;
	}
	public void setHighVolume(String highVolume) {
		this.highVolume = highVolume;
	}
	public String getHighCostAuditCase() {
		return highCostAuditCase;
	}
	public void setHighCostAuditCase(String highCostAuditCase) {
		this.highCostAuditCase = highCostAuditCase;
	}
	public String getHighVolumeAuditCase() {
		return highVolumeAuditCase;
	}
	public void setHighVolumeAuditCase(String highVolumeAuditCase) {
		this.highVolumeAuditCase = highVolumeAuditCase;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getWorkflowStatus() {
		return workflowStatus;
	}
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	public char getHospType1() {
		return hospType1;
	}
	public void setHospType1(char hospType1) {
		this.hospType1 = hospType1;
	}
	public String getCmaDeoFlag() {
		return cmaDeoFlag;
	}
	public void setCmaDeoFlag(String cmaDeoFlag) {
		this.cmaDeoFlag = cmaDeoFlag;
	}
	public List<String> getGpLst() {
		return gpLst;
	}
	public void setGpLst(List<String> gpLst) {
		this.gpLst = gpLst;
	}
	public String getAuditedBy() {
		return auditedBy;
	}
	public void setAuditedBy(String auditedBy) {
		this.auditedBy = auditedBy;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	
	
}
