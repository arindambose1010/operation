package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfCrAttachmentsId implements Serializable {
	private String crRefId;
	 private Integer attachOrder;
	 
	 
	 
	 public EhfCrAttachmentsId(String crRefId, Integer attachOrder) {
		super();
		this.crRefId = crRefId;
		this.attachOrder = attachOrder;
	}
	 
	public EhfCrAttachmentsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "CR_ID", nullable = false, length = 25)
	public String getCrRefId() {
		return crRefId;
	}
	public void setCrRefId(String crRefId) {
		this.crRefId = crRefId;
	}
	@Column(name = "ATTACH_ORDER", nullable = false, length = 25)
	public Integer getattachOrder() {
		return attachOrder;
	}
	public void setattachOrder(Integer attachOrder) {
		this.attachOrder = attachOrder;
	}  
	 
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crRefId == null) ? 0 : crRefId.hashCode());
		result = prime * result
				+ ((attachOrder == null) ? 0 : attachOrder.hashCode());
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
		EhfCrAttachmentsId other = (EhfCrAttachmentsId) obj;
		if (crRefId == null) {
			if (other.attachOrder != null)
				return false;
		} else if (!crRefId.equals(other.attachOrder))
			return false;
		if (attachOrder == null) {
			if (other.attachOrder != null)
				return false;
		} else if (!attachOrder.equals(other.attachOrder))
			return false;
		return true;
	}

}
