package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_MEDICAL_AUDIT_SAMPLE_CASES")
public class EhfMedicalAuditSampleCases implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String caseId;
	private String caseNo;
	private String claimNo;
	private String patientId;
	private String districtCode;
	private Long claimPaidAmt;
	private String hospId;
	private char hospType;
	private String schemeId;
	private Date crtDt;
	private String quarter;
	private String year;
    private String highCost;
    private String highVolume;
    private String highCostAuditCase;
    private String highVolumeAuditCase;
    private String group;
    private String workflowStatus;
    private String lstUpdUsr;
	private Date lstUpddt;
	
	
	public EhfMedicalAuditSampleCases() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Id
	 @Column(name = "CASE_ID", nullable = false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name = "CASE_NO", nullable = true)
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	@Column(name = "CLAIM_NO", nullable = true)
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	
	@Column(name = "PATIENT_ID", nullable = true)
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Column(name = "DISTRICT_CODE", nullable = true)
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	
	@Column(name = "CLAIM_PAID_AMT", nullable = true)
	public Long getClaimPaidAmt() {
		return claimPaidAmt;
	}
	public void setClaimPaidAmt(Long claimPaidAmt) {
		this.claimPaidAmt = claimPaidAmt;
	}
	@Column(name = "HOSP_ID", nullable = true)
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	
	
	@Column(name = "HOSP_TYPE", nullable = true)
	
	public char getHospType() {
		return hospType;
	}


	public void setHospType(char hospType) {
		this.hospType = hospType;
	}
	@Column(name = "SCHEME_ID", nullable = true)
	public String getSchemeId() {
		return schemeId;
	}
	


	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DATE", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name = "QUARTER", nullable = true)
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}


	@Column(name = "YEAR", nullable = true)
	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "HIGH_COST", nullable = true)
	public String getHighCost() {
		return highCost;
	}


	public void setHighCost(String highCost) {
		this.highCost = highCost;
	}

	@Column(name = "HIGH_VOLUME", nullable = true)
	public String getHighVolume() {
		return highVolume;
	}


	public void setHighVolume(String highVolume) {
		this.highVolume = highVolume;
	}

	@Column(name = "HIGH_COST_AUDIT_CASE", nullable = true)
	public String getHighCostAuditCase() {
		return highCostAuditCase;
	}


	public void setHighCostAuditCase(String highCostAuditCase) {
		this.highCostAuditCase = highCostAuditCase;
	}

	@Column(name = "HIGH_VOLUME_AUDIT_CASE", nullable = true)
	public String getHighVolumeAuditCase() {
		return highVolumeAuditCase;
	}


	public void setHighVolumeAuditCase(String highVolumeAuditCase) {
		this.highVolumeAuditCase = highVolumeAuditCase;
	}

	@Column(name = "CASE_GROUP", nullable = true)
	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}

	@Column(name = "WORKFLOW_STATUS", nullable = true)
	public String getWorkflowStatus() {
		return workflowStatus;
	}


	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	@Column(name = "LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}


	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true)
	public Date getLstUpddt() {
		return lstUpddt;
	}


	public void setLstUpddt(Date lstUpddt) {
		this.lstUpddt = lstUpddt;
	}
	
	
	
	  
	  
	  
	  
	  

}
