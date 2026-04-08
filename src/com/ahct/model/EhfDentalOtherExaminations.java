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
@Table

(name = "ehf_dental_othr_examinations" )

public class EhfDentalOtherExaminations {


	private String patientId ;  
    private String caseId;
    private String medicalHistory;           
    private String medicalHistoryOthr;            
    private String previousDentalTreatment;            
    private String occlusion;            
    private String occlusionOther;  
    private String occlusionDivii;             
    private String clinicalProbingDepth;              
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    
    
    public EhfDentalOtherExaminations()
    {
    	super();
    }
    
    
	@Id
    @Column ( name = "PATIENT_ID", nullable = false )
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column ( name = "CASE_ID" )
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column ( name = "Medical_History" )
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	@Column ( name = "Medical_History_othr" )
	public String getMedicalHistoryOthr() {
		return medicalHistoryOthr;
	}
	public void setMedicalHistoryOthr(String medicalHistoryOthr) {
		this.medicalHistoryOthr = medicalHistoryOthr;
	}
	@Column ( name = "Previous_Dental_Treatment" )
	public String getPreviousDentalTreatment() {
		return previousDentalTreatment;
	}
	public void setPreviousDentalTreatment(String previousDentalTreatment) {
		this.previousDentalTreatment = previousDentalTreatment;
	}
	@Column ( name = "Occlusion" )
	public String getOcclusion() {
		return occlusion;
	}
	public void setOcclusion(String occlusion) {
		this.occlusion = occlusion;
	}
	@Column ( name = "Occlusion_Other" )
	public String getOcclusionOther() {
		return occlusionOther;
	}
	public void setOcclusionOther(String occlusionOther) {
		this.occlusionOther = occlusionOther;
	}
	@Column ( name = "Occlusion_divii" )
	public String getOcclusionDivii() {
		return occlusionDivii;
	}
	public void setOcclusionDivii(String occlusionDivii) {
		this.occlusionDivii = occlusionDivii;
	}
	@Column ( name = "Clinical_Probing_Depth" )
	public String getClinicalProbingDepth() {
		return clinicalProbingDepth;
	}
	public void setClinicalProbingDepth(String clinicalProbingDepth) {
		this.clinicalProbingDepth = clinicalProbingDepth;
	}
	@Column ( name = "CRT_DT" )
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column ( name = "CRT_USR" )
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Column ( name = "LST_UPD_DT" )
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column ( name = "LST_UPD_USR" )
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
}
