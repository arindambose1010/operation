package com.ahct.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EHF_MEDICAL_AUDIT_HIGH_VOLUME")
public class EhfMedicalAuditHighVolumeCases implements Serializable{
	
	private EhfMedicalAuditHighVolumeCasesId id;
	private String districtCode;
	private char hospType;
	
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "caseId", column = @Column(name = "CASE_ID", nullable = false, length = 25)),
			@AttributeOverride(name = "procedureId", column = @Column(name = "PROCEDURE_ID", nullable = false, length=25)) })
	public EhfMedicalAuditHighVolumeCasesId getId() {
		return id;
	}
	public void setId(EhfMedicalAuditHighVolumeCasesId id) {
		this.id = id;
	}
	@Column(name = "DIST_CODE", nullable = true)
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	@Column(name = "HOSP_TYPE", nullable = true)
	public char getHospType() {
		return hospType;
	}
	public void setHospType(char hospType) {
		this.hospType = hospType;
	}
	
	
	

}
