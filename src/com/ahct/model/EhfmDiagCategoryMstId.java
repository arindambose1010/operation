package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmDiagCategoryMstId implements Serializable {
	private String catCode;
	private String mainCatCode;
	
	public EhfmDiagCategoryMstId() {
		super();
		// TODO Auto-generated constructor stub
	}
		public EhfmDiagCategoryMstId(String catCode, String mainCatCode) {
		super();
		this.catCode = catCode;
		this.mainCatCode = mainCatCode;
	}
	@Column(name="cat_code")
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	@Column(name="main_cat_code")
	public String getMainCatCode() {
		return mainCatCode;
	}
	public void setMainCatCode(String mainCatCode) {
		this.mainCatCode = mainCatCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mainCatCode == null) ? 0 : mainCatCode.hashCode());
		result = prime * result
				+ ((catCode == null) ? 0 : catCode.hashCode());
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
		EhfmDiagCategoryMstId other = (EhfmDiagCategoryMstId) obj;
		if (mainCatCode == null) {
			if (other.mainCatCode != null)
				return false;
		} else if (!mainCatCode.equals(other.mainCatCode))
			return false;
		if (catCode == null) {
			if (other.catCode != null)
				return false;
		} else if (!catCode.equals(other.catCode))
			return false;
		return true;
	}
}
