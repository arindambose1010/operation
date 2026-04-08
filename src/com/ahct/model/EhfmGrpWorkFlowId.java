package com.ahct.model;

import java.io.Serializable;

public class EhfmGrpWorkFlowId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String workFlowId;
	private Integer grpLevel;
	
	
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	
	public Integer getGrpLevel() {
		return grpLevel;
	}
	public void setGrpLevel(Integer grpLevel) {
		this.grpLevel = grpLevel;
	}
	public EhfmGrpWorkFlowId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public EhfmGrpWorkFlowId(String workFlowId, Integer grpLevel) {
		super();
		this.workFlowId = workFlowId;
		this.grpLevel = grpLevel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((workFlowId == null) ? 0 : workFlowId.hashCode());
		result = prime * result
				+ ((grpLevel == null) ? 0 : grpLevel.hashCode());
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
		EhfmGrpWorkFlowId other = (EhfmGrpWorkFlowId) obj;
		if (workFlowId == null) {
			if (other.workFlowId != null)
				return false;
		} else if (!workFlowId.equals(other.workFlowId))
			return false;
		if (grpLevel == null) {
			if (other.grpLevel != null)
				return false;
		} else if (!grpLevel.equals(other.grpLevel))
			return false;
		return true;
	}

}
