package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmDiagSubCatMstId implements Serializable {

	private String subCatCode;
	private String catCode;

	@Column(name = "sub_cat_code")
	public String getSubCatCode() {
		return subCatCode;
	}

	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}

	@Column(name = "cat_code")
	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catCode == null) ? 0 : catCode.hashCode());
		result = prime * result
				+ ((subCatCode == null) ? 0 : subCatCode.hashCode());
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
		EhfmDiagSubCatMstId other = (EhfmDiagSubCatMstId) obj;
		if (catCode == null) {
			if (other.catCode != null)
				return false;
		} else if (!catCode.equals(other.catCode))
			return false;
		if (subCatCode == null) {
			if (other.subCatCode != null)
				return false;
		} else if (!subCatCode.equals(other.subCatCode))
			return false;
		return true;
	}

	public EhfmDiagSubCatMstId(String subCatCode, String catCode) {
		super();
		this.subCatCode = subCatCode;
		this.catCode = catCode;
	}

	public EhfmDiagSubCatMstId() {
		super();
		// TODO Auto-generated constructor stub
	}

}
