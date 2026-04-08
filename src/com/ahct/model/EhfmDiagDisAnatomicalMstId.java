package com.ahct.model;

import javax.persistence.Column;

public class EhfmDiagDisAnatomicalMstId implements java.io.Serializable{

	private String disAnatomicalCode;	
	private String diseaseCode;
	private String catCode;
	private String subCatCode;
	
	@Column(name="dis_anatomical_code", nullable=false, length=100)
	public String getDisAnatomicalCode() {
		return disAnatomicalCode;
	}
	public void setDisAnatomicalCode(String disAnatomicalCode) {
		this.disAnatomicalCode = disAnatomicalCode;
	}
	@Column(name="disease_code", nullable=false, length=100)
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	@Column(name="cat_code", nullable=false, length=100)
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	@Column(name="sub_cat_code", nullable=false, length=100)
	public String getSubCatCode() {
		return subCatCode;
	}
	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}
	public EhfmDiagDisAnatomicalMstId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmDiagDisAnatomicalMstId(String disAnatomicalCode,
			String diseaseCode, String catCode, String subCatCode) {
		super();
		this.disAnatomicalCode = disAnatomicalCode;
		this.diseaseCode = diseaseCode;
		this.catCode = catCode;
		this.subCatCode = subCatCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catCode == null) ? 0 : catCode.hashCode());
		result = prime
				* result
				+ ((disAnatomicalCode == null) ? 0 : disAnatomicalCode
						.hashCode());
		result = prime * result
				+ ((diseaseCode == null) ? 0 : diseaseCode.hashCode());
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
		EhfmDiagDisAnatomicalMstId other = (EhfmDiagDisAnatomicalMstId) obj;
		if (catCode == null) {
			if (other.catCode != null)
				return false;
		} else if (!catCode.equals(other.catCode))
			return false;
		if (disAnatomicalCode == null) {
			if (other.disAnatomicalCode != null)
				return false;
		} else if (!disAnatomicalCode.equals(other.disAnatomicalCode))
			return false;
		if (diseaseCode == null) {
			if (other.diseaseCode != null)
				return false;
		} else if (!diseaseCode.equals(other.diseaseCode))
			return false;
		if (subCatCode == null) {
			if (other.subCatCode != null)
				return false;
		} else if (!subCatCode.equals(other.subCatCode))
			return false;
		return true;
	}
	
	
	
	
}
