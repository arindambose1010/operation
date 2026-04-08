package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_PATIENT_PER_HSTRY database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_PATIENT_PER_HSTRY")
public class EhfChronicPatientPerHstry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHRONIC_ID")
	private String chronicId;

	private String addictions;

	private String appetite;

	private String bowels;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	private String diet;

	@Column(name="KNOWN_ALLERGIES")
	private String knownAllergies;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="MARITAL_STATUS")
	private String maritalStatus;

	private String nutrition;

	private String occupation;

    public EhfChronicPatientPerHstry() {
    }

	public String getChronicId() {
		return this.chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	public String getAddictions() {
		return this.addictions;
	}

	public void setAddictions(String addictions) {
		this.addictions = addictions;
	}

	public String getAppetite() {
		return this.appetite;
	}

	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}

	public String getBowels() {
		return this.bowels;
	}

	public void setBowels(String bowels) {
		this.bowels = bowels;
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

	public String getDiet() {
		return this.diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getKnownAllergies() {
		return this.knownAllergies;
	}

	public void setKnownAllergies(String knownAllergies) {
		this.knownAllergies = knownAllergies;
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

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNutrition() {
		return this.nutrition;
	}

	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

}