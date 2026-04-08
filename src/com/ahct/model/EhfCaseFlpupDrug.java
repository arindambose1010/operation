package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_CASE_FLPUP_DRUG")
public class EhfCaseFlpupDrug implements java.io.Serializable{

	private String srlId;
	private String clinicalId;
	private String drugCode;
	private String drupTypeCode;
	private String dosage;
	private String medication;
	private String crtUser;
	private Date crtDt;
	private String lstUpdUser;
	private Date lstUpdDt;
	private String batchNumber;
	private Date drugExpiryDate;
	
	@Id
	@Column(name="srl_id")
	public String getSrlId() {
		return srlId;
	}
	public void setSrlId(String srlId) {
		this.srlId = srlId;
	}
	@Column(name="clinical_id")
	public String getClinicalId() {
		return clinicalId;
	}
	public void setClinicalId(String clinicalId) {
		this.clinicalId = clinicalId;
	}
	@Column(name="drug_code")
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	@Column(name="drug_type_code")
	public String getDrupTypeCode() {
		return drupTypeCode;
	}
	public void setDrupTypeCode(String drupTypeCode) {
		this.drupTypeCode = drupTypeCode;
	}
	@Column(name="dosage")
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	@Column(name="medication")
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	@Column(name="CRT_USR")
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR")
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="BATCH_NUMBER")
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	@Column(name="DRUG_EXPIRY_DATE")
	public Date getDrugExpiryDate() {
		return drugExpiryDate;
	}
	public void setDrugExpiryDate(Date drugExpiryDate) {
		this.drugExpiryDate = drugExpiryDate;
	}
	
	public EhfCaseFlpupDrug(String srlId, String clinicalId, String drugCode,
			String drupTypeCode, String dosage, String medication,
			String crtUser, Date crtDt, String lstUpdUser, Date lstUpdDt, String batchNumber , 
			Date drugExpiryDate ) {
		super();
		this.srlId = srlId;
		this.clinicalId = clinicalId;
		this.drugCode = drugCode;
		this.drupTypeCode = drupTypeCode;
		this.dosage = dosage;
		this.medication = medication;
		this.crtUser = crtUser;
		this.crtDt = crtDt;
		this.lstUpdUser = lstUpdUser;
		this.lstUpdDt = lstUpdDt;
		this.batchNumber=batchNumber;
		this.drugExpiryDate=drugExpiryDate;
	}
	public EhfCaseFlpupDrug() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
