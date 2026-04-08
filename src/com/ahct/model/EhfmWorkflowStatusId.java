package com.ahct.model;

import javax.persistence.Column;

public class EhfmWorkflowStatusId  implements java.io.Serializable{

	private String currStatus;
	private String currRole;
	private String actionDone;
	
	@Column(name="current_status", nullable=false)
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
	@Column(name="current_role", nullable=false)
	public String getCurrRole() {
		return currRole;
	}
	public void setCurrRole(String currRole) {
		this.currRole = currRole;
	}
	@Column(name="action_done", nullable=false)
	public String getActionDone() {
		return actionDone;
	}
	public void setActionDone(String actionDone) {
		this.actionDone = actionDone;
	}
	public EhfmWorkflowStatusId(String currStatus, String currRole,
			String actionDone) {
		super();
		this.currStatus = currStatus;
		this.currRole = currRole;
		this.actionDone = actionDone;
	}
	public EhfmWorkflowStatusId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
