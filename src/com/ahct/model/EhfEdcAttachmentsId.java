package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfEdcAttachmentsId implements Serializable {
	private String actionId;
	 private Integer attachOrder;
	 
	 
	 
	 public EhfEdcAttachmentsId(String actionId, Integer attachOrder) {
		super();
		this.actionId = actionId;
		this.attachOrder = attachOrder;
	}
	 
	public EhfEdcAttachmentsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "ACTION_ID", nullable = false, length = 25)
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
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
		result = prime * result + ((actionId == null) ? 0 : actionId.hashCode());
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
		EhfEdcAttachmentsId other = (EhfEdcAttachmentsId) obj;
		if (actionId == null) {
			if (other.attachOrder != null)
				return false;
		} else if (!actionId.equals(other.attachOrder))
			return false;
		if (attachOrder == null) {
			if (other.attachOrder != null)
				return false;
		} else if (!attachOrder.equals(other.attachOrder))
			return false;
		return true;
	}

}
