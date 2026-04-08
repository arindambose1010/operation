package com.ahct.model;

import javax.persistence.Column;

public class EhfmDotorSpltyId implements java.io.Serializable{
private String regNum;
private String hospId;
private String spctlyCode;
@Column(name = "REG_NUM", nullable = false, length =50)
public String getRegNum() {
	return regNum;
}
public void setRegNum(String regNum) {
	this.regNum = regNum;
}
@Column(name = "HOSP_ID", nullable = false, length = 20)
public String getHospId() {
	return hospId;
}
public void setHospId(String hospId) {
	this.hospId = hospId;
}
@Column( name = "SPCLTY_CODE", nullable = false, length = 5)
public String getSpctlyCode() {
	return spctlyCode;
}
public void setSpctlyCode(String spctlyCode) {
	this.spctlyCode = spctlyCode;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((hospId == null) ? 0 : hospId.hashCode());
	result = prime * result + ((regNum == null) ? 0 : regNum.hashCode());
	result = prime * result
			+ ((spctlyCode == null) ? 0 : spctlyCode.hashCode());
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
	EhfmDotorSpltyId other = (EhfmDotorSpltyId) obj;
	if (hospId == null) {
		if (other.hospId != null)
			return false;
	} else if (!hospId.equals(other.hospId))
		return false;
	if (regNum == null) {
		if (other.regNum != null)
			return false;
	} else if (!regNum.equals(other.regNum))
		return false;
	if (spctlyCode == null) {
		if (other.spctlyCode != null)
			return false;
	} else if (!spctlyCode.equals(other.spctlyCode))
		return false;
	return true;
}
public EhfmDotorSpltyId(String regNum, String hospId, String spctlyCode) {
	super();
	this.regNum = regNum;
	this.hospId = hospId;
	this.spctlyCode = spctlyCode;
}
public EhfmDotorSpltyId() {
	super();
}


}
