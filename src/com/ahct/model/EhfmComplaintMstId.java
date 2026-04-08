package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmComplaintMstId implements Serializable {

	private String complCode;
	private String mainComplCode;
	
	
	public EhfmComplaintMstId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmComplaintMstId(String complCode, String mainComplCode) {
		super();
		this.complCode = complCode;
		this.mainComplCode = mainComplCode;
	}

	@Column(name="compl_code",nullable=false)
    public String getComplCode() {
		return complCode;
	}

	public void setComplCode(String complCode) {
		this.complCode = complCode;
	}
	@Column(name="main_compl_code")
	public String getMainComplCode() {
		return mainComplCode;
	}

	public void setMainComplCode(String mainComplCode) {
		this.mainComplCode = mainComplCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((complCode == null) ? 0 : complCode.hashCode());
		result = prime * result
				+ ((mainComplCode == null) ? 0 : mainComplCode.hashCode());
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
		EhfmComplaintMstId other = (EhfmComplaintMstId) obj;
		if (complCode == null) {
			if (other.complCode != null)
				return false;
		} else if (!complCode.equals(other.complCode))
			return false;
		if (mainComplCode == null) {
			if (other.mainComplCode != null)
				return false;
		} else if (!mainComplCode.equals(other.mainComplCode))
			return false;
		return true;
	}
}
