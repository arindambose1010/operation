package com.ahct.model;

import javax.persistence.Column;

public class EhfmTherapyGrpTimeoutId implements java.io.Serializable{

	private String specialityCode;
	private String grpId;
	private String moduleType;
	private String icdProcCode;
	private String scheme;
	
	@Column(name="SPECIALITY_CODE")
	public String getSpecialityCode() {
		return specialityCode;
	}
	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}
	
	@Column(name="GRP_ID")
	public String getGrpId() {
		return grpId;
	}	
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	@Column(name="MODULE_TYPE")
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	@Column(name="ICD_PROC_CODE")
	public String getIcdProcCode() {
		return icdProcCode;
	}
	public void setIcdProcCode(String icdProcCode) {
		this.icdProcCode = icdProcCode;
	}
	
	@Column(name="SCHEME")
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public EhfmTherapyGrpTimeoutId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmTherapyGrpTimeoutId(String specialityCode, String grpId,
			String moduleType, String icdProcCode,String scheme) {
		super();
		this.specialityCode = specialityCode;
		this.grpId = grpId;
		this.moduleType = moduleType;
		this.icdProcCode = icdProcCode;
		this.scheme = scheme;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grpId == null) ? 0 : grpId.hashCode());
		result = prime * result
				+ ((icdProcCode == null) ? 0 : icdProcCode.hashCode());
		result = prime * result
				+ ((moduleType == null) ? 0 : moduleType.hashCode());
		result = prime * result
				+ ((specialityCode == null) ? 0 : specialityCode.hashCode());
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
		EhfmTherapyGrpTimeoutId other = (EhfmTherapyGrpTimeoutId) obj;
		if (grpId == null) {
			if (other.grpId != null)
				return false;
		} else if (!grpId.equals(other.grpId))
			return false;
		if (icdProcCode == null) {
			if (other.icdProcCode != null)
				return false;
		} else if (!icdProcCode.equals(other.icdProcCode))
			return false;
		if (moduleType == null) {
			if (other.moduleType != null)
				return false;
		} else if (!moduleType.equals(other.moduleType))
			return false;
		if (specialityCode == null) {
			if (other.specialityCode != null)
				return false;
		} else if (!specialityCode.equals(other.specialityCode))
			return false;
		return true;
	}	
}
