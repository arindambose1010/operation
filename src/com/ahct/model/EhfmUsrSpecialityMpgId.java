package com.ahct.model;

import javax.persistence.Column;

public class EhfmUsrSpecialityMpgId implements java.io.Serializable{
private String userId;
private String specialityId;
private String grpId;
@Column(name = "USER_ID")
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
@Column(name = "Speciality_id")
public String getSpecialityId() {
	return specialityId;
}
public void setSpecialityId(String specialityId) {
	this.specialityId = specialityId;
}
@Column(name = "grp_id")
public String getGrpId() {
	return grpId;
}
public void setGrpId(String grpId) {
	this.grpId = grpId;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((grpId == null) ? 0 : grpId.hashCode());
	result = prime * result
			+ ((specialityId == null) ? 0 : specialityId.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
	EhfmUsrSpecialityMpgId other = (EhfmUsrSpecialityMpgId) obj;
	if (grpId == null) {
		if (other.grpId != null)
			return false;
	} else if (!grpId.equals(other.grpId))
		return false;
	if (specialityId == null) {
		if (other.specialityId != null)
			return false;
	} else if (!specialityId.equals(other.specialityId))
		return false;
	if (userId == null) {
		if (other.userId != null)
			return false;
	} else if (!userId.equals(other.userId))
		return false;
	return true;
}
public EhfmUsrSpecialityMpgId(String userId, String specialityId, String grpId) {
	super();
	this.userId = userId;
	this.specialityId = specialityId;
	this.grpId = grpId;
}
public EhfmUsrSpecialityMpgId() {
	super();
}


}
