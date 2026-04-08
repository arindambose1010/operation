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
@Table(name = "EHF_PATIENT_PERSONAL_HISTORY")
public class EhfPatientPersonalHistory implements Serializable {

	
    private String patientId;
    private String caseId;
    private String appetite;
    private String diet;
    private String bowels;
    private String nutrition;
    private String knownAllergies;
    private String addictions;
    private String maritalStatus;
    private String occupation;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String drugHst;
    private String drugHstVal;
    
    @Id
    @Column(name="PATIENT_ID", nullable = false)
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    @Column(name="CASE_ID")
    public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name="APPETITE")
	public String getAppetite() {
		return appetite;
	}

	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}
	@Column(name="DIET")
	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}
	@Column(name="BOWELS")
	public String getBowels() {
		return bowels;
	}

	public void setBowels(String bowels) {
		this.bowels = bowels;
	}
	@Column(name="NUTRITION")
	public String getNutrition() {
		return nutrition;
	}

	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}
	@Column(name="KNOWN_ALLERGIES")
	public String getKnownAllergies() {
		return knownAllergies;
	}

	public void setKnownAllergies(String knownAllergies) {
		this.knownAllergies = knownAllergies;
	}
	@Column(name="ADDICTIONS")
	public String getAddictions() {
		return addictions;
	}

	public void setAddictions(String addictions) {
		this.addictions = addictions;
	}
	@Column(name="MARITAL_STATUS")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	@Column(name="OCCUPATION")
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = false)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    
    @Column(name="drug_hst")
	public String getDrugHst() {
		return drugHst;
	}

	public void setDrugHst(String drugHst) {
		this.drugHst = drugHst;
	}
	@Column(name="drug_hst_val")
	public String getDrugHstVal() {
		return drugHstVal;
	}

	public void setDrugHstVal(String drugHstVal) {
		this.drugHstVal = drugHstVal;
	}

    

    
}
