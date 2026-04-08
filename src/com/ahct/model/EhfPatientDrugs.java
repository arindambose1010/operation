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
@Table(name="EHF_PATIENT_DRUGS")
public class EhfPatientDrugs implements Serializable {
	
	private Long drugId;
	private String patientId;
	private String caseId;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugCode;
	private String route;
	private String strength;
	private String dosage;
	private String medicationPeriod;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Date issueFromDt;
	private Date issueToDt;
	private String pharSubGrpCode;
	private String cheSubGrpCode;
	private String routeType;
	private String strengthType;
	private String otherDrugName;
	private String issueYn;
	private String quantity;
	private Date expiryDate;
	
	public EhfPatientDrugs() {
		super();
	}
	
	public EhfPatientDrugs(Long drugId, String patientId, String caseId,
			String drugTypeCode, String drugSubTypeCode, String drugCode,
			String route, String strength, String dosage,
			String medicationPeriod, String crtUsr, Date crtDt,
			String lstUpdUsr, Date lstUpdDt, Date issueFromDt, Date issueToDt,
			String pharSubGrpCode, String cheSubGrpCode, String routeType,
			String strengthType) {
		super();
		this.drugId = drugId;
		this.patientId = patientId;
		this.caseId = caseId;
		this.drugTypeCode = drugTypeCode;
		this.drugSubTypeCode = drugSubTypeCode;
		this.drugCode = drugCode;
		this.route = route;
		this.strength = strength;
		this.dosage = dosage;
		this.medicationPeriod = medicationPeriod;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.issueFromDt = issueFromDt;
		this.issueToDt = issueToDt;
		this.pharSubGrpCode = pharSubGrpCode;
		this.cheSubGrpCode = cheSubGrpCode;
		this.routeType = routeType;
		this.strengthType = strengthType;
	}



	@Id
	@Column(name="DRUG_ID")
	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}

	@Column(name="PATIENT_ID")
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
	@Column(name="DRUG_TYPE_CODE")
	public String getDrugTypeCode() {
		return drugTypeCode;
	}

	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	@Column(name="DRUG_SUB_TYPE_CODE")
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}

	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	@Column(name="ASRI_DRUG_CODE")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	@Column(name="ROUTE")
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}
	@Column(name="STRENGTH")
	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}
	@Column(name="DOSAGE")
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	@Column(name="MEDICATION_PERIOD")
	public String getMedicationPeriod() {
		return medicationPeriod;
	}

	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
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
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_FROM_DT")
	public Date getIssueFromDt() {
		return issueFromDt;
	}

	public void setIssueFromDt(Date issueFromDt) {
		this.issueFromDt = issueFromDt;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_TO_DT")
	public Date getIssueToDt() {
		return issueToDt;
	}

	public void setIssueToDt(Date issueToDt) {
		this.issueToDt = issueToDt;
	}
	@Column(name="phar_sub_grp_code")
	public String getPharSubGrpCode() {
		return pharSubGrpCode;
	}

	public void setPharSubGrpCode(String pharSubGrpCode) {
		this.pharSubGrpCode = pharSubGrpCode;
	}
	@Column(name="che_sub_grp_code")
	public String getCheSubGrpCode() {
		return cheSubGrpCode;
	}

	public void setCheSubGrpCode(String cheSubGrpCode) {
		this.cheSubGrpCode = cheSubGrpCode;
	}
	@Column(name="route_type")
	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	@Column(name="strength_type")
	public String getStrengthType() {
		return strengthType;
	}

	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}

	@Column(name="other_drug_name")
	public String getOtherDrugName() {
		return otherDrugName;
	}

	public void setOtherDrugName(String otherDrugName) {
		this.otherDrugName = otherDrugName;
	}
	
	@Column(name="ISSUE_YN")
	public String getIssueYn() {
		return issueYn;
	}

	public void setIssueYn(String issueYn) {
		this.issueYn = issueYn;
	}

	@Column(name="QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
