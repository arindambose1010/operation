package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_PATIENT_DRUGS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_PATIENT_DRUGS")
public class EhfChronicPatientDrug implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfChronicPatientDrugPK id;
	private String asriDrugCode;
	private String cheSubGrpCode;
	private Date crtDt;
	private String crtUsr;
	private String dosage;
	private String drugAmount;
	private String drugSubTypeCode;
	private String drugTypeCode;
	private Date issueFromDt;
	private Date issueToDt;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String medicationPeriod;
	private String pharSubGrpCode;
	private String route;
	private String routeType;
	private String strength;
	private String strengthType;
	private String batchNo;
	private Date expiryDt;

    public EhfChronicPatientDrug() {
    }


	@EmbeddedId
	public EhfChronicPatientDrugPK getId() {
		return this.id;
	}

	public void setId(EhfChronicPatientDrugPK id) {
		this.id = id;
	}
	

	@Column(name="ASRI_DRUG_CODE")
	public String getAsriDrugCode() {
		return this.asriDrugCode;
	}

	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}


	@Column(name="CHE_SUB_GRP_CODE")
	public String getCheSubGrpCode() {
		return this.cheSubGrpCode;
	}

	public void setCheSubGrpCode(String cheSubGrpCode) {
		this.cheSubGrpCode = cheSubGrpCode;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}


	@Column(name="DRUG_AMOUNT")
	public String getDrugAmount() {
		return this.drugAmount;
	}

	public void setDrugAmount(String drugAmount) {
		this.drugAmount = drugAmount;
	}


	@Column(name="DRUG_SUB_TYPE_CODE")
	public String getDrugSubTypeCode() {
		return this.drugSubTypeCode;
	}

	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}


	@Column(name="DRUG_TYPE_CODE")
	public String getDrugTypeCode() {
		return this.drugTypeCode;
	}

	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="ISSUE_FROM_DT")
	public Date getIssueFromDt() {
		return this.issueFromDt;
	}

	public void setIssueFromDt(Date issueFromDt) {
		this.issueFromDt = issueFromDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="ISSUE_TO_DT")
	public Date getIssueToDt() {
		return this.issueToDt;
	}

	public void setIssueToDt(Date issueToDt) {
		this.issueToDt = issueToDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="MEDICATION_PERIOD")
	public String getMedicationPeriod() {
		return this.medicationPeriod;
	}

	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}


	@Column(name="PHAR_SUB_GRP_CODE")
	public String getPharSubGrpCode() {
		return this.pharSubGrpCode;
	}

	public void setPharSubGrpCode(String pharSubGrpCode) {
		this.pharSubGrpCode = pharSubGrpCode;
	}


	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}


	@Column(name="ROUTE_TYPE")
	public String getRouteType() {
		return this.routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}


	public String getStrength() {
		return this.strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}


	@Column(name="STRENGTH_TYPE")
	public String getStrengthType() {
		return this.strengthType;
	}

	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}

	@Column(name="BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name="EXPIRY_DT")
	public Date getExpiryDt() {
		return expiryDt;
	}


	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

}