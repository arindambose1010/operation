package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfSymtematicExamDtlsId implements Serializable{

	private String symptomCode;
	private String caseId;
	
	
   public EhfSymtematicExamDtlsId() {
		super();
		// TODO Auto-generated constructor stub
	}
public EhfSymtematicExamDtlsId(String symptomCode, String caseId) {
		super();
		this.symptomCode = symptomCode;
		this.caseId = caseId;
	}
	@Column(name="symptom_code",nullable=false)
    public String getSymptomCode() {
		return symptomCode;
	}
	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}
	@Column(name="case_id",nullable=false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseId == null) ? 0 : caseId.hashCode());
		result = prime * result
				+ ((symptomCode == null) ? 0 : symptomCode.hashCode());
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
		EhfSymtematicExamDtlsId other = (EhfSymtematicExamDtlsId) obj;
		if (caseId == null) {
			if (other.caseId != null)
				return false;
		} else if (!caseId.equals(other.caseId))
			return false;
		if (symptomCode == null) {
			if (other.symptomCode != null)
				return false;
		} else if (!symptomCode.equals(other.symptomCode))
			return false;
		return true;
	}
	
	
}
