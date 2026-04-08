package com.ahct.model;

import javax.persistence.Column;

public class EhfClinicalFlpupMpgId implements java.io.Serializable {

	private String followUpId;
	private String clinicalId;
	
	
	@Column(name="followup_id",nullable=false)
	public String getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(String followUpId) {
		this.followUpId = followUpId;
	}
	
	@Column(name="clinical_id")
	public String getClinicalId() {
		return clinicalId;
	}
	public void setClinicalId(String clinicalId) {
		this.clinicalId = clinicalId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clinicalId == null) ? 0 : clinicalId.hashCode());
		result = prime * result
				+ ((followUpId == null) ? 0 : followUpId.hashCode());
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
		EhfClinicalFlpupMpgId other = (EhfClinicalFlpupMpgId) obj;
		if (clinicalId == null) {
			if (other.clinicalId != null)
				return false;
		} else if (!clinicalId.equals(other.clinicalId))
			return false;
		if (followUpId == null) {
			if (other.followUpId != null)
				return false;
		} else if (!followUpId.equals(other.followUpId))
			return false;
		return true;
	}
	public EhfClinicalFlpupMpgId(String followUpId, String clinicalId) {
		super();
		this.followUpId = followUpId;
		this.clinicalId = clinicalId;
	}
	public EhfClinicalFlpupMpgId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
