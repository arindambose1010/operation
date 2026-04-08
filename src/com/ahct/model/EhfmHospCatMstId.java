package com.ahct.model;

import javax.persistence.Column;

public class EhfmHospCatMstId implements java.io.Serializable{
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catId == null) ? 0 : catId.hashCode());
		result = prime * result + ((hospId == null) ? 0 : hospId.hashCode());
		result = prime * result + ((phaseId == null) ? 0 : phaseId.hashCode());
		result = prime * result + ((renewal == null) ? 0 : renewal.hashCode());
		result = prime * result
				+ ((schemeId == null) ? 0 : schemeId.hashCode());
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
		EhfmHospCatMstId other = (EhfmHospCatMstId) obj;
		if (catId == null) {
			if (other.catId != null)
				return false;
		} else if (!catId.equals(other.catId))
			return false;
		if (hospId == null) {
			if (other.hospId != null)
				return false;
		} else if (!hospId.equals(other.hospId))
			return false;
		if (phaseId == null) {
			if (other.phaseId != null)
				return false;
		} else if (!phaseId.equals(other.phaseId))
			return false;
		if (renewal == null) {
			if (other.renewal != null)
				return false;
		} else if (!renewal.equals(other.renewal))
			return false;
		if (schemeId == null) {
			if (other.schemeId != null)
				return false;
		} else if (!schemeId.equals(other.schemeId))
			return false;
		return true;
	}
private String hospId;
private String catId;
private Integer phaseId;
private Integer renewal;
private Integer schemeId;
@Column(name="HOSP_ID" ,nullable=false)
public String getHospId() {
	return hospId;
}
public void setHospId(String hospId) {
	this.hospId = hospId;
}
@Column(name="ICD_Cat_Code" ,nullable=false)
public String getCatId() {
	return catId;
}
public void setCatId(String catId) {
	this.catId = catId;
}
@Column(name="PHASE_ID" ,nullable=false)
public Integer getPhaseId() {
	return phaseId;
}
public void setPhaseId(Integer phaseId) {
	this.phaseId = phaseId;
}
@Column(name="RENEWAL" ,nullable=false)
public Integer getRenewal() {
	return renewal;
}
public void setRenewal(Integer renewal) {
	this.renewal = renewal;
}
@Column(name="SCHEME_ID" ,nullable=false)
public Integer getSchemeId() {
	return schemeId;
}
public void setSchemeId(Integer schemeId) {
	this.schemeId = schemeId;
}
public EhfmHospCatMstId(String hospId, String catId, Integer phaseId,
		Integer renewal, Integer schemeId) {
	super();
	this.hospId = hospId;
	this.catId = catId;
	this.phaseId = phaseId;
	this.renewal = renewal;
	this.schemeId = schemeId;
}
public EhfmHospCatMstId() {
	super();
}


}
