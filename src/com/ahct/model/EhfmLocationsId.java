package com.ahct.model;

import javax.persistence.Column;

public class EhfmLocationsId implements java.io.Serializable{

	private String locId;
	private String locParntId;
	
	@Column(name = "LOC_ID", nullable = false, length = 25)
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	
	@Column(name = "LOC_PARNT_ID", nullable = false, length = 25)
	public String getLocParntId() {
		return locParntId;
	}
	public void setLocParntId(String locParntId) {
		this.locParntId = locParntId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locId == null) ? 0 : locId.hashCode());
		result = prime * result
				+ ((locParntId == null) ? 0 : locParntId.hashCode());
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
		EhfmLocationsId other = (EhfmLocationsId) obj;
		if (locId == null) {
			if (other.locId != null)
				return false;
		} else if (!locId.equals(other.locId))
			return false;
		if (locParntId == null) {
			if (other.locParntId != null)
				return false;
		} else if (!locParntId.equals(other.locParntId))
			return false;
		return true;
	}
	public EhfmLocationsId(String locId, String locParntId) {
		super();
		this.locId = locId;
		this.locParntId = locParntId;
	}
	public EhfmLocationsId() {
		super();
	}
	
}
