package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_PATIENT_EXAM_FIND database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_PATIENT_EXAM_FIND")
public class EhfChronicPatientExamFind implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHRONIC_ID")
	private String chronicId;

	private String bmi;

	@Column(name="BP_LT")
	private String bpLt;

	@Column(name="BP_RT")
	private String bpRt;

	@Column(name="CLUB_OF_FINGRTOES")
	private String clubOfFingrtoes;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	private String cyanosis;

	private String dehydration;

	private String height;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	private String lymphadenopathy;

	private String malnutrition;

	@Column(name="OEDEMA_IN_FEET")
	private String oedemaInFeet;

	private String pallor;

	@Column(name="PLUSE_RATE")
	private String pluseRate;

	@Column(name="RESPIRATION_RATE")
	private String respirationRate;

	private String temperature;

	private String weight;

    public EhfChronicPatientExamFind() {
    }

	public String getChronicId() {
		return this.chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	public String getBmi() {
		return this.bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getBpLt() {
		return this.bpLt;
	}

	public void setBpLt(String bpLt) {
		this.bpLt = bpLt;
	}

	public String getBpRt() {
		return this.bpRt;
	}

	public void setBpRt(String bpRt) {
		this.bpRt = bpRt;
	}

	public String getClubOfFingrtoes() {
		return this.clubOfFingrtoes;
	}

	public void setClubOfFingrtoes(String clubOfFingrtoes) {
		this.clubOfFingrtoes = clubOfFingrtoes;
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

	public String getCyanosis() {
		return this.cyanosis;
	}

	public void setCyanosis(String cyanosis) {
		this.cyanosis = cyanosis;
	}

	public String getDehydration() {
		return this.dehydration;
	}

	public void setDehydration(String dehydration) {
		this.dehydration = dehydration;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
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

	public String getLymphadenopathy() {
		return this.lymphadenopathy;
	}

	public void setLymphadenopathy(String lymphadenopathy) {
		this.lymphadenopathy = lymphadenopathy;
	}

	public String getMalnutrition() {
		return this.malnutrition;
	}

	public void setMalnutrition(String malnutrition) {
		this.malnutrition = malnutrition;
	}

	public String getOedemaInFeet() {
		return this.oedemaInFeet;
	}

	public void setOedemaInFeet(String oedemaInFeet) {
		this.oedemaInFeet = oedemaInFeet;
	}

	public String getPallor() {
		return this.pallor;
	}

	public void setPallor(String pallor) {
		this.pallor = pallor;
	}

	public String getPluseRate() {
		return this.pluseRate;
	}

	public void setPluseRate(String pluseRate) {
		this.pluseRate = pluseRate;
	}

	public String getRespirationRate() {
		return this.respirationRate;
	}

	public void setRespirationRate(String respirationRate) {
		this.respirationRate = respirationRate;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}