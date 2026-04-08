package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class EhfEdcWorkFlowId implements Serializable {
	private String hospId;
	private String actionId;
	
	
	@Column(name = "HOSP_ID", nullable = false, length = 25)
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	
	
	public EhfEdcWorkFlowId() {
		super();
		
	}
	@Column(name = "ACTION_ID", nullable = false, length = 50)
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public EhfEdcWorkFlowId(String hospId, String actionId) {
		super();
		this.hospId = hospId;
		this.actionId = actionId;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hospId == null) ? 0 : hospId.hashCode());
		result = prime * result
				+ ((actionId == null) ? 0 : actionId.hashCode());
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
		EhfEdcWorkFlowId other = (EhfEdcWorkFlowId) obj;
		if (hospId == null) {
			if (other.hospId != null)
				return false;
		} else if (!hospId.equals(other.hospId))
			return false;
		if (actionId == null) {
			if (other.actionId != null)
				return false;
		} else if (!actionId.equals(other.actionId))
			return false;
		return true;
	}

}
