package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfMedicalAuditHighVolumeCasesId implements Serializable {
	
	
	
	private String caseId;
	private String procedureId;
	
	public EhfMedicalAuditHighVolumeCasesId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfMedicalAuditHighVolumeCasesId(String caseId, String procedureId) {
		super();
		this.caseId = caseId;
		this.procedureId = procedureId;
	}
	
	@Column(name = "CASE_ID", nullable = false)
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name = "PROCEDURE_ID", nullable = false)
	public String getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseId == null) ? 0 : caseId.hashCode());
		result = prime * result + ((procedureId == null) ? 0 : procedureId.hashCode());
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
		EhfMedicalAuditHighVolumeCasesId other = (EhfMedicalAuditHighVolumeCasesId) obj;
		if (caseId == null) {
			if (other.caseId != null)
				return false;
		} else if (!caseId.equals(other.caseId))
			return false;
		if (procedureId == null) {
			if (other.procedureId != null)
				return false;
		} else if (!procedureId.equals(other.procedureId))
			return false;
		return true;
	}
	

}
