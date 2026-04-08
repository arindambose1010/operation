package com.ahct.model;

import javax.persistence.Column;


public class EhfCaseStatusAttachId implements java.io.Serializable{
private String caseStatus;
private String attachType;
private String userRole;
private String langId;
@Column(name="lang_id", nullable=true, length=20)
public String getLangId() {
	return langId;
}
public void setLangId(String langId) {
	this.langId = langId;
}

@Column(name="CASE_STATUS", nullable=false)
public String getCaseStatus() {
	return caseStatus;
}
public void setCaseStatus(String caseStatus) {
	this.caseStatus = caseStatus;
}
@Column(name="ATTACH_TYPE", nullable=false)
public String getAttachType() {
	return attachType;
}
public void setAttachType(String attachType) {
	this.attachType = attachType;
}
@Column(name="USR_ROLE", nullable=false)
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}


public EhfCaseStatusAttachId(String caseStatus, String attachType,
		String userRole, String langId) {
	super();
	this.caseStatus = caseStatus;
	this.attachType = attachType;
	this.userRole = userRole;
	this.langId = langId;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((attachType == null) ? 0 : attachType.hashCode());
	result = prime * result
			+ ((caseStatus == null) ? 0 : caseStatus.hashCode());
	result = prime * result + ((langId == null) ? 0 : langId.hashCode());
	result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
	EhfCaseStatusAttachId other = (EhfCaseStatusAttachId) obj;
	if (attachType == null) {
		if (other.attachType != null)
			return false;
	} else if (!attachType.equals(other.attachType))
		return false;
	if (caseStatus == null) {
		if (other.caseStatus != null)
			return false;
	} else if (!caseStatus.equals(other.caseStatus))
		return false;
	if (langId == null) {
		if (other.langId != null)
			return false;
	} else if (!langId.equals(other.langId))
		return false;
	if (userRole == null) {
		if (other.userRole != null)
			return false;
	} else if (!userRole.equals(other.userRole))
		return false;
	return true;
}
public EhfCaseStatusAttachId() {
	super();
}

}
