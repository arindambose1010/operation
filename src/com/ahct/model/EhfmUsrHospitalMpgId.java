package com.ahct.model;

import javax.persistence.Column;

public class EhfmUsrHospitalMpgId implements java.io.Serializable{
private String userId;
private String hospitalId;
@Column(name = "USER_ID")
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
@Column(name = "hospital_id")
public String getHospitalId() {
	return hospitalId;
}
public void setHospitalId(String hospitalId) {
	this.hospitalId = hospitalId;
}
public EhfmUsrHospitalMpgId(String userId, String hospitalId) {
	super();
	this.userId = userId;
	this.hospitalId = hospitalId;
}
public EhfmUsrHospitalMpgId() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((hospitalId == null) ? 0 : hospitalId.hashCode());
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
	EhfmUsrHospitalMpgId other = (EhfmUsrHospitalMpgId) obj;
	if (hospitalId == null) {
		if (other.hospitalId != null)
			return false;
	} else if (!hospitalId.equals(other.hospitalId))
		return false;
	if (userId == null) {
		if (other.userId != null)
			return false;
	} else if (!userId.equals(other.userId))
		return false;
	return true;
}


}
