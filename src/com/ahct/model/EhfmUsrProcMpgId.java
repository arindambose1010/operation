package com.ahct.model;

import javax.persistence.Column;

public class EhfmUsrProcMpgId implements java.io.Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String userId;
private String procId;
private String icdCatCode;
private String asriCode;

@Column(name = "USER_ID")
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
@Column(name = "PROCEDURE_ID")
public String getProcId() {
	return procId;
}
public void setProcId(String procId) {
	this.procId = procId;
}
@Column(name = "ICD_CAT_CODE")
public String getIcdCatCode() {
	return icdCatCode;
}
public void setIcdCatCode(String icdCatCode) {
	this.icdCatCode = icdCatCode;
}
@Column(name= "ASRI_CODE")
public String getAsriCode() {
	return asriCode;
}
public void setAsriCode(String asriCode) {
	this.asriCode = asriCode;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((procId == null) ? 0 : procId.hashCode());
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
	EhfmUsrProcMpgId other = (EhfmUsrProcMpgId) obj;
	if (procId == null) {
		if (other.procId != null)
			return false;
	} else if (!procId.equals(other.procId))
		return false;
	if (userId == null) {
		if (other.userId != null)
			return false;
	} else if (!userId.equals(other.userId))
		return false;
	return true;
}
public EhfmUsrProcMpgId(String userId, String procId) {
	super();
	this.userId = userId;
	this.procId = procId;
}
public EhfmUsrProcMpgId() {
	super();
}


}
