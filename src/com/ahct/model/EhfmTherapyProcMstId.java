package com.ahct.model;

import javax.persistence.Column;

public class EhfmTherapyProcMstId implements java.io.Serializable{
private String asriCode;
private String icdProcCode;
private String state;
private String process;

@Column(name="STATE")
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
@Column(name="ASRI_CODE")
public String getAsriCode() {
	return asriCode;
}
public void setAsriCode(String asriCode) {
	this.asriCode = asriCode;
}
@Column(name="ICD_PROC_CODE")
public String getIcdProcCode() {
	return icdProcCode;
}
public void setIcdProcCode(String icdProcCode) {
	this.icdProcCode = icdProcCode;
}
@Column(name="process")
public String getProcess() {
	return process;
}
public void setProcess(String process) {
	this.process = process;
}


public EhfmTherapyProcMstId(String asriCode, String icdProcCode, String state, String process) {
	super();
	this.asriCode = asriCode;
	this.icdProcCode = icdProcCode;
	this.state = state;
	this.process = process;
}
public EhfmTherapyProcMstId() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((asriCode == null) ? 0 : asriCode.hashCode());
	result = prime * result
			+ ((icdProcCode == null) ? 0 : icdProcCode.hashCode());
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
	EhfmTherapyProcMstId other = (EhfmTherapyProcMstId) obj;
	if (asriCode == null) {
		if (other.asriCode != null)
			return false;
	} else if (!asriCode.equals(other.asriCode))
		return false;
	if (icdProcCode == null) {
		if (other.icdProcCode != null)
			return false;
	} else if (!icdProcCode.equals(other.icdProcCode))
		return false;
	return true;
}

}
