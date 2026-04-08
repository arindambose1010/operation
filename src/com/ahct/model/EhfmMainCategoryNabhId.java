package com.ahct.model;

import javax.persistence.Column;

public class EhfmMainCategoryNabhId implements java.io.Serializable{
private String asriCatCode;
private String icdCatCode;
@Column(name="ASRI_CAT_CODE")
public String getAsriCatCode() {
	return asriCatCode;
}

public void setAsriCatCode(String asriCatCode) {
	this.asriCatCode = asriCatCode;
}
@Column(name="ICD_CAT_CODE")
public String getIcdCatCode() {
	return icdCatCode;
}

public void setIcdCatCode(String icdCatCode) {
	this.icdCatCode = icdCatCode;
}

public EhfmMainCategoryNabhId(String asriCatCode, String icdCatCode) {
	super();
	this.asriCatCode = asriCatCode;
	this.icdCatCode = icdCatCode;
}

public EhfmMainCategoryNabhId() {
	super();
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((asriCatCode == null) ? 0 : asriCatCode.hashCode());
	result = prime * result
			+ ((icdCatCode == null) ? 0 : icdCatCode.hashCode());
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
	EhfmMainCategoryNabhId other = (EhfmMainCategoryNabhId) obj;
	if (asriCatCode == null) {
		if (other.asriCatCode != null)
			return false;
	} else if (!asriCatCode.equals(other.asriCatCode))
		return false;
	if (icdCatCode == null) {
		if (other.icdCatCode != null)
			return false;
	} else if (!icdCatCode.equals(other.icdCatCode))
		return false;
	return true;
}

}
