package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfFollowUpAuditId implements Serializable {
	
	public Long actOrder;
	public String caseFollowupId;
	public String caseType;
		
	
	public EhfFollowUpAuditId(Long actOrder, String caseFollowupId,
			String caseType) {
		super();
		this.actOrder = actOrder;
		this.caseFollowupId = caseFollowupId;
		this.caseType = caseType;
	}
	public EhfFollowUpAuditId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(name="ACT_ORDER", nullable = false)
	public Long getActOrder() {
		return actOrder;
	}
	public void setActOrder(Long actOrder) {
		this.actOrder = actOrder;
	}
	@Column(name="CASE_FOLLOWUP_ID", nullable = false)
	public String getCaseFollowupId() {
		return caseFollowupId;
	}
	public void setCaseFollowupId(String caseFollowupId) {
		this.caseFollowupId = caseFollowupId;
	}
	@Column(name="CASE_TYPE", nullable = false)
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actOrder == null) ? 0 : actOrder.hashCode());
		result = prime * result
				+ ((caseFollowupId == null) ? 0 : caseFollowupId.hashCode());
		result = prime * result
				+ ((caseType == null) ? 0 : caseType.hashCode());
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
		EhfFollowUpAuditId other = (EhfFollowUpAuditId) obj;
		if (actOrder == null) {
			if (other.actOrder != null)
				return false;
		} else if (!actOrder.equals(other.actOrder))
			return false;
		if (caseFollowupId == null) {
			if (other.caseFollowupId != null)
				return false;
		} else if (!caseFollowupId.equals(other.caseFollowupId))
			return false;
		if (caseType == null) {
			if (other.caseType != null)
				return false;
		} else if (!caseType.equals(other.caseType))
			return false;
		return true;
	}
	
	

}
