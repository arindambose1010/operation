package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;



public class EhfMedAuditWorkFlowAuditId implements Serializable {
	
	private String caseId;
	 private Integer actOrder;
	 
	 
	 
	

	public EhfMedAuditWorkFlowAuditId() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EhfMedAuditWorkFlowAuditId(String caseId, Integer actOrder) {
		super();
		this.caseId = caseId;
		this.actOrder = actOrder;
	}
	
	
	@Column(name = "CASE_ID", nullable = false, length = 25)
	
	public String getCaseId() {
		return caseId;
	}


	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@Column(name = "ACT_ORDER", nullable = false, length = 25)
	public Integer getActOrder() {
		return actOrder;
	}
	


	public void setActOrder(Integer actOrder) {
		this.actOrder = actOrder;
	}  
	 
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseId == null) ? 0 : caseId.hashCode());
		result = prime * result
				+ ((actOrder == null) ? 0 : actOrder.hashCode());
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
		EhfMedAuditWorkFlowAuditId other = (EhfMedAuditWorkFlowAuditId) obj;
		if (caseId == null) {
			if (other.actOrder != null)
				return false;
		} else if (!caseId.equals(other.actOrder))
			return false;
		if (actOrder == null) {
			if (other.actOrder != null)
				return false;
		} else if (!actOrder.equals(other.actOrder))
			return false;
		return true;
	}
	
}
