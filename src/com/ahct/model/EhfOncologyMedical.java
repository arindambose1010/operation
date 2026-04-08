package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="EHF_ONCOLOGY_MEDICAL")
public class EhfOncologyMedical implements Serializable {

	private String proformaId;
	private String approvalAuthority;
	private String attachment;
	private String attachmentPath;
	private String availability;
	private String availabilityIndia;
	private String cardNo;
	private Date crtDt;
	private String crtUsr;
	private String diagnosis;
	private String doctorName;
	private String drugList;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String mcbsGrade;
	private String molecularMarkers;
	private String palliativeSetting;
	private String patientName;
	private String regNo;
	private String remarks;
	private String section;
	private String stage;
	private String superIndentName;
	private String organId;
	private String organName;
	private Timestamp preauthInitDt;
	private String status;

	public EhfOncologyMedical() {
	}

	@Id
	@Column(name="PROFORMA_ID")
	public String getProformaId() {
		return this.proformaId;
	}

	public void setProformaId(String proformaId) {
		this.proformaId = proformaId;
	}

	@Column(name="APPROVAL_AUTHORITY")
	public String getApprovalAuthority() {
		return this.approvalAuthority;
	}

	public void setApprovalAuthority(String approvalAuthority) {
		this.approvalAuthority = approvalAuthority;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Column(name="ATTACHMENT_PATH")
	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAvailability() {
		return this.availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Column(name="AVAILABILITY_INDIA")
	public String getAvailabilityIndia() {
		return this.availabilityIndia;
	}

	public void setAvailabilityIndia(String availabilityIndia) {
		this.availabilityIndia = availabilityIndia;
	}

	@Column(name="CARD_NO")
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

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

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	@Column(name="DOCTOR_NAME")
	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Column(name="DRUG_LIST")
	public String getDrugList() {
		return this.drugList;
	}

	public void setDrugList(String drugList) {
		this.drugList = drugList;
	}

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

	@Column(name="MCBS_GRADE")
	public String getMcbsGrade() {
		return this.mcbsGrade;
	}

	public void setMcbsGrade(String mcbsGrade) {
		this.mcbsGrade = mcbsGrade;
	}

	@Column(name="MOLECULAR_MARKERS")
	public String getMolecularMarkers() {
		return this.molecularMarkers;
	}

	public void setMolecularMarkers(String molecularMarkers) {
		this.molecularMarkers = molecularMarkers;
	}

	@Column(name="PALLIATIVE_SETTING")
	public String getPalliativeSetting() {
		return this.palliativeSetting;
	}

	public void setPalliativeSetting(String palliativeSetting) {
		this.palliativeSetting = palliativeSetting;
	}

	@Column(name="PATIENT_NAME")
	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Column(name="REG_NO")
	public String getRegNo() {
		return this.regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="\"SECTION\"")
	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(name="SUPER_INDENT_NAME")
	public String getSuperIndentName() {
		return this.superIndentName;
	}

	public void setSuperIndentName(String superIndentName) {
		this.superIndentName = superIndentName;
	}

	@Column(name="ORGAN_ID")
	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	@Column(name="ORGAN_NAME")
	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	@Column(name="PREAUTH_INIT_DT")
	public Timestamp getPreauthInitDt() {
		return preauthInitDt;
	}

	public void setPreauthInitDt(Timestamp preauthInitDt) {
		this.preauthInitDt = preauthInitDt;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}