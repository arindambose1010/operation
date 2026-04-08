

package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfAdminSanctionAuditid implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sanctionId;
	 private Integer actOrder;
	 
	 
	 
	 public EhfAdminSanctionAuditid(String sanctionId, Integer actOrder) {
		super();
		this.sanctionId = sanctionId;
		this.actOrder = actOrder;
	}
	 
	public EhfAdminSanctionAuditid() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "SANCTION_ID", nullable = false, length = 25)
	public String getsanctionId() {
		return sanctionId;
	}
	public void setsanctionId(String sanctionId) {
		this.sanctionId = sanctionId;
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
		result = prime * result + ((sanctionId == null) ? 0 : sanctionId.hashCode());
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
		EhfAdminSanctionAuditid other = (EhfAdminSanctionAuditid) obj;
		if (sanctionId == null) {
			if (other.actOrder != null)
				return false;
		} else if (!sanctionId.equals(other.actOrder))
			return false;
		if (actOrder == null) {
			if (other.actOrder != null)
				return false;
		} else if (!actOrder.equals(other.actOrder))
			return false;
		return true;
	}
	
}
