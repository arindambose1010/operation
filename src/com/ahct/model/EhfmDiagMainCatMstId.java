package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmDiagMainCatMstId implements Serializable {

	private String mainCatCode;
	private String  diagnosisCode;
	
	public EhfmDiagMainCatMstId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmDiagMainCatMstId(String mainCatCode, String diagnosisCode) {
		super();
		this.mainCatCode = mainCatCode;
		this.diagnosisCode = diagnosisCode;
	}

	@Column(name="main_cat_code")
	public String getMainCatCode() {
		return mainCatCode;
	}
	public void setMainCatCode(String mainCatCode) {
		this.mainCatCode = mainCatCode;
	}
	@Column(name="diagnosis_code")
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mainCatCode == null) ? 0 : mainCatCode.hashCode());
		result = prime * result
				+ ((diagnosisCode == null) ? 0 : diagnosisCode.hashCode());
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
		EhfmDiagMainCatMstId other = (EhfmDiagMainCatMstId) obj;
		if (mainCatCode == null) {
			if (other.mainCatCode != null)
				return false;
		} else if (!mainCatCode.equals(other.mainCatCode))
			return false;
		if (diagnosisCode == null) {
			if (other.diagnosisCode != null)
				return false;
		} else if (!diagnosisCode.equals(other.diagnosisCode))
			return false;
		return true;
	}
}
