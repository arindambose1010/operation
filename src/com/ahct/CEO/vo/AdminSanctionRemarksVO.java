package com.ahct.CEO.vo;

import java.util.Date;

public class AdminSanctionRemarksVO {

	
	private String updatedBy;
	private String action;
	private String remarks;
	 private Date remarksDt;
	 private String currentOwner;
	 private String crRefNo;
	 private String grpId;
	 private String userId;
	 private Integer grpLevel;
	 private String status;
	 private String actionTaken;
	 private String workflowId;
	 private String prevOwner;
	 private String prevWorkFlowId;
	 
	 
	
	 
	 
	public String getPrevOwner() {
		return prevOwner;
	}
	public void setPrevOwner(String prevOwner) {
		this.prevOwner = prevOwner;
	}
	public String getPrevWorkFlowId() {
		return prevWorkFlowId;
	}
	public void setPrevWorkFlowId(String prevWorkFlowId) {
		this.prevWorkFlowId = prevWorkFlowId;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getActionTaken() {
		return actionTaken;
	}
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getGrpLevel() {
		return grpLevel;
	}
	public void setGrpLevel(Integer grpLevel) {
		this.grpLevel = grpLevel;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCrRefNo() {
		return crRefNo;
	}
	public void setCrRefNo(String crRefNo) {
		this.crRefNo = crRefNo;
	}
	public String getCurrentOwner() {
		return currentOwner;
	}
	public void setCurrentOwner(String currentOwner) {
		this.currentOwner = currentOwner;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getRemarksDt() {
		return remarksDt;
	}
	public void setRemarksDt(Date remarksDt) {
		this.remarksDt = remarksDt;
	}
	 
	
}
