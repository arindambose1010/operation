package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfmPreauthWorkflowId implements Serializable{
	private String currentStatus;
	private String actionUsr;
	private String actiondone;
	
	@Column(name="CURRENT_STATUS",nullable=false)
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@Column(name="ACTION_USR",nullable=false)
	public String getActionUsr() {
		return actionUsr;
	}
	public void setActionUsr(String actionUsr) {
		this.actionUsr = actionUsr;
	}
	@Column(name="ACTION_DONE",nullable=false)
	public String getActiondone() {
		return actiondone;
	}
	public void setActiondone(String actiondone) {
		this.actiondone = actiondone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionUsr == null) ? 0 : actionUsr.hashCode());
		result = prime * result
				+ ((actiondone == null) ? 0 : actiondone.hashCode());
		result = prime * result
				+ ((currentStatus == null) ? 0 : currentStatus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfmPreauthWorkflowId other = (EhfmPreauthWorkflowId) obj;
		if (actionUsr == null) {
			if (other.actionUsr != null)
				return false;
		} else if (!actionUsr.equals(other.actionUsr))
			return false;
		if (actiondone == null) {
			if (other.actiondone != null)
				return false;
		} else if (!actiondone.equals(other.actiondone))
			return false;
		if (currentStatus == null) {
			if (other.currentStatus != null)
				return false;
		} else if (!currentStatus.equals(other.currentStatus))
			return false;
		return true;
	}
	
	public EhfmPreauthWorkflowId(String currentStatus, String actionUsr,
			String actiondone) {
		super();
		this.currentStatus = currentStatus;
		this.actionUsr = actionUsr;
		this.actiondone = actiondone;
	}
	public EhfmPreauthWorkflowId() {
		super();
		// TODO Auto-generated constructor stub
	}		
}
