package com.ahct.model;

import javax.persistence.Column;

public class EhfAttachDisplayId implements java.io.Serializable{
private String attachType;
private String userRole;
private String viewType;
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
@Column(name="VIEW_TYPE", nullable=false)
public String getViewType() {
	return viewType;
}
public void setViewType(String viewType) {
	this.viewType = viewType;
}
public EhfAttachDisplayId(String attachType, String userRole, String viewType) {
	super();
	this.attachType = attachType;
	this.userRole = userRole;
	this.viewType = viewType;
}
public EhfAttachDisplayId() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((attachType == null) ? 0 : attachType.hashCode());
	result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
	result = prime * result + ((viewType == null) ? 0 : viewType.hashCode());
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
	EhfAttachDisplayId other = (EhfAttachDisplayId) obj;
	if (attachType == null) {
		if (other.attachType != null)
			return false;
	} else if (!attachType.equals(other.attachType))
		return false;
	if (userRole == null) {
		if (other.userRole != null)
			return false;
	} else if (!userRole.equals(other.userRole))
		return false;
	if (viewType == null) {
		if (other.viewType != null)
			return false;
	} else if (!viewType.equals(other.viewType))
		return false;
	return true;
}

}
