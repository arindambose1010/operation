package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmSystematicExamFndId implements Serializable {
	
	private String symptomCode;
	private String subSymptomCode;
	private String mainSymptomCode;
	
	public EhfmSystematicExamFndId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EhfmSystematicExamFndId(String symptomCode, String subSymptomCode,
			String mainSymptomCode) {
		super();
		this.symptomCode = symptomCode;
		this.subSymptomCode = subSymptomCode;
		this.mainSymptomCode = mainSymptomCode;
	}

	@Column(name="symptom_code",nullable=false)
    public String getSymptomCode() {
		return symptomCode;
	}
	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}
	@Column(name="sub_symptom_code")
	public String getSubSymptomCode() {
		return subSymptomCode;
	}
	public void setSubSymptomCode(String subSymptomCode) {
		this.subSymptomCode = subSymptomCode;
	}
	@Column(name="main_symptom_code")
	public String getMainSymptomCode() {
		return mainSymptomCode;
	}
	public void setMainSymptomCode(String mainSymptomCode) {
		this.mainSymptomCode = mainSymptomCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symptomCode == null) ? 0 : symptomCode.hashCode());
		result = prime * result
				+ ((subSymptomCode == null) ? 0 : subSymptomCode.hashCode());
		result = prime * result
				+ ((mainSymptomCode == null) ? 0 : mainSymptomCode.hashCode());
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
		EhfmSystematicExamFndId other = (EhfmSystematicExamFndId) obj;
		if (symptomCode == null) {
			if (other.symptomCode != null)
				return false;
		} else if (!symptomCode.equals(other.symptomCode))
			return false;
		if (subSymptomCode == null) {
			if (other.subSymptomCode != null)
				return false;
		} else if (!subSymptomCode.equals(other.subSymptomCode))
			return false;
		if (mainSymptomCode == null) {
			if (other.mainSymptomCode != null)
				return false;
		} else if (!mainSymptomCode.equals(other.mainSymptomCode))
			return false;
		return true;
	}
}
