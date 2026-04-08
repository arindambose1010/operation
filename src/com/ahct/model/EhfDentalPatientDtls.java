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

( name = "ehf_dental_patient_dtls" )
public class EhfDentalPatientDtls implements Serializable
{
        private String  patientId ;  
        private String caseId;
        private String diagnosis ;         
        private String ipOp ;           
        private String admissionDtls;
        private String advancedInvestigations ; 
        private String flwupAdvise;  
        private String medicationGiven;
        private Date crtDt;
        private String crtUsr;
        private Date lstUpdDt;
        private String lstUpdUsr;
        
    public EhfDentalPatientDtls() {
    }
    
    @Id
    @Column ( name = "patient_id", nullable = false )
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	 @Column ( name = "case_id" )
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	 
	 @Column ( name = "diagnosis" )
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	 @Column ( name = "IP_OP" )
	public String getIpOp() {
		return ipOp;
	}

	public void setIpOp(String ipOp) {
		this.ipOp = ipOp;
	}
	 @Column ( name = "admsn_dtls" )
	public String getAdmissionDtls() {
		return admissionDtls;
	}

	public void setAdmissionDtls(String admissionDtls) {
		this.admissionDtls = admissionDtls;
	}
	 @Column ( name = "adv_investgn" )
	public String getAdvancedInvestigations() {
		return advancedInvestigations;
	}

	public void setAdvancedInvestigations(String advancedInvestigations) {
		this.advancedInvestigations = advancedInvestigations;
	}
	 @Column ( name = "flwp_adv" )
	public String getFlwupAdvise() {
		return flwupAdvise;
	}

	public void setFlwupAdvise(String flwupAdvise) {
		this.flwupAdvise = flwupAdvise;
	}
	 @Column ( name = "crt_dt" )
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	 @Column ( name = "crt_usr" )
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	 @Column ( name = "lst_upd_dt" )
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	 @Column ( name = "lst_upd_usr" )
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Column ( name = "medication_Given" )
	public String getMedicationGiven() {
		return medicationGiven;
	}

	public void setMedicationGiven(String medicationGiven) {
		this.medicationGiven = medicationGiven;
	}

   
    
   
    
}


