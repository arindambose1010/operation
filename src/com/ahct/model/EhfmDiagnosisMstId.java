package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmDiagnosisMstId implements Serializable {

	private String chapter;
	private String diagnosisCode;
	public EhfmDiagnosisMstId() {
		super();
	}
	public EhfmDiagnosisMstId(String chapter, String diagnosisCode) {
		super();
		this.chapter = chapter;
		this.diagnosisCode = diagnosisCode;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
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
		result = prime * result + ((chapter == null) ? 0 : chapter.hashCode());
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
		EhfmDiagnosisMstId other = (EhfmDiagnosisMstId) obj;
		if (chapter == null) {
			if (other.chapter != null)
				return false;
		} else if (!chapter.equals(other.chapter))
			return false;
		if (diagnosisCode == null) {
			if (other.diagnosisCode != null)
				return false;
		} else if (!diagnosisCode.equals(other.diagnosisCode))
			return false;
		return true;
	}
}
