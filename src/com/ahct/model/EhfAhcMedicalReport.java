package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_AHC_MEDICAL_REPORT database table.
 * 
 */
@Entity
@Table(name="EHF_AHC_MEDICAL_REPORT")
public class EhfAhcMedicalReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AHC_ID")
	private String ahcId;

	@Column(name="BLOOD_SUGAR_LEVEL")
	private String bloodSugarLevel;

	@Column(name="CARDIAC_STATUS")
	private String cardiacStatus;

	@Column(name="CHOLESTEROL_LEVEL")
	private String cholesterolLevel;

    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="HAEMOGLOBIN_LEVEL")
	private String haemoglobinLevel;

	@Column(name="KIDNEY_STATUS")
	private String kidneyStatus;

	@Column(name="LIVER_FUNCTIONING")
	private String liverFunctioning;

    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;
	
	@Column(name="OVERALL_HEALTH")
	private String overallHealth;
	
	@Column(name="HEALTH_SUMMARY")
	private String healthSummary;
	
	@Column(name="HEALTH_GRADE")
	private String healthGrade;
	
	
    public EhfAhcMedicalReport() {
    }

	public String getAhcId() {
		return this.ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}

	public String getBloodSugarLevel() {
		return this.bloodSugarLevel;
	}

	public void setBloodSugarLevel(String bloodSugarLevel) {
		this.bloodSugarLevel = bloodSugarLevel;
	}

	public String getCardiacStatus() {
		return this.cardiacStatus;
	}

	public void setCardiacStatus(String cardiacStatus) {
		this.cardiacStatus = cardiacStatus;
	}

	public String getCholesterolLevel() {
		return this.cholesterolLevel;
	}

	public void setCholesterolLevel(String cholesterolLevel) {
		this.cholesterolLevel = cholesterolLevel;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getHaemoglobinLevel() {
		return this.haemoglobinLevel;
	}

	public void setHaemoglobinLevel(String haemoglobinLevel) {
		this.haemoglobinLevel = haemoglobinLevel;
	}

	public String getKidneyStatus() {
		return this.kidneyStatus;
	}

	public void setKidneyStatus(String kidneyStatus) {
		this.kidneyStatus = kidneyStatus;
	}

	public String getLiverFunctioning() {
		return this.liverFunctioning;
	}

	public void setLiverFunctioning(String liverFunctioning) {
		this.liverFunctioning = liverFunctioning;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getOverallHealth() {
		return overallHealth;
	}

	public void setOverallHealth(String overallHealth) {
		this.overallHealth = overallHealth;
	}

	public String getHealthSummary() {
		return healthSummary;
	}

	public void setHealthSummary(String healthSummary) {
		this.healthSummary = healthSummary;
	}

	public String getHealthGrade() {
		return healthGrade;
	}

	public void setHealthGrade(String healthGrade) {
		this.healthGrade = healthGrade;
	}

}