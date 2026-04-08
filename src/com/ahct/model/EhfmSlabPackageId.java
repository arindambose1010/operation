package com.ahct.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfmSlabPackageId implements java.io.Serializable{
private String nabhFlag;
private String slabType;
@Column(name="nabh_flag")
public String getNabhFlag() {
	return nabhFlag;
}
public void setNabhFlag(String nabhFlag) {
	this.nabhFlag = nabhFlag;
}
@Column(name="slab_type")
public String getSlabType() {
	return slabType;
}
public void setSlabType(String slabType) {
	this.slabType = slabType;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nabhFlag == null) ? 0 : nabhFlag.hashCode());
	result = prime * result + ((slabType == null) ? 0 : slabType.hashCode());
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
	EhfmSlabPackageId other = (EhfmSlabPackageId) obj;
	if (nabhFlag == null) {
		if (other.nabhFlag != null)
			return false;
	} else if (!nabhFlag.equals(other.nabhFlag))
		return false;
	if (slabType == null) {
		if (other.slabType != null)
			return false;
	} else if (!slabType.equals(other.slabType))
		return false;
	return true;
}
public EhfmSlabPackageId(String nabhFlag, String slabType) {
	super();
	this.nabhFlag = nabhFlag;
	this.slabType = slabType;
}
public EhfmSlabPackageId() {
	super();
}



}
