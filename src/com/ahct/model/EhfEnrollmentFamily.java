package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EHF_ENROLLMENT_FAMILY")
public class EhfEnrollmentFamily implements Serializable {
	private String aadharEid;
	private String aadharId;
	private Date crtDt;
	private String crtUsr;
	private String enrollDisability;
	private String enrollHeight;
	private String enrollDisabilityPercent;
	private String enrollDisabled;
	private Date enrollDob;
	private char enrollGender;
	private String enrollId;
	private String enrollName;
	private String enrollPrntId;
	private String enrollRelation;
	private Long enrollSno;
	private String enrollStatus;
	private String enrollDisabilityCerf;
	private String enrollPhoto;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String idType;
	private String enrollDobCerti;
	private String displayFlag;
	private String enrollAadharCerti;
	private String ehfCardNo;
	private String biometricString;
	 private String viewFlag;
	 private String cardSlotFlag;
	 private String rejected;
	 private String aadharExists;
	 private Date submittedDate;
	 private String spouseEmpType;
	 private String annualCheckup;
	 //Chandana - 8326 - for ABHA
	private String ekycDoneYn;
	private String abhaId;
	private Date abhaUpdDt;
	private String abhaUpdUsr;
	   
		
	    @Column(name = "view_flag", nullable = true)
		public String getViewFlag() {
			return viewFlag;
		}

		public void setViewFlag(String viewFlag) {
			this.viewFlag = viewFlag;
		}
	public EhfEnrollmentFamily() {
	}

	@Column(name = "AADHAR_EID")
	public String getAadharEid() {
		return aadharEid;
	}

	public void setAadharEid(String aadharEid) {
		this.aadharEid = aadharEid;
	}

	@Column(name = "AADHAR_ID")
	public String getAadharId() {
		return aadharId;
	}

	public void setAadharId(String aadharId) {
		this.aadharId = aadharId;
	}

	@Column(name = "CRT_DT", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Column(name = "ENROLL_DISABILITY")
	public String getEnrollDisability() {
		return enrollDisability;
	}

	public void setEnrollDisability(String enrollDisability) {
		this.enrollDisability = enrollDisability;
	}

	@Column(name = "ENROLL_DISABILITY_PERCENT")
	public String getEnrollDisabilityPercent() {
		return enrollDisabilityPercent;
	}

	public void setEnrollDisabilityPercent(String enrollDisabilityPercent) {
		this.enrollDisabilityPercent = enrollDisabilityPercent;
	}

	@Column(name = "ENROLL_DISABLED")
	public String getEnrollDisabled() {
		return enrollDisabled;
	}

	public void setEnrollDisabled(String enrollDisabled) {
		this.enrollDisabled = enrollDisabled;
	}

	@Column(name = "ENROLL_DOB")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEnrollDob() {
		return enrollDob;
	}

	public void setEnrollDob(Date enrollDob) {
		this.enrollDob = enrollDob;
	}

	@Id
	@Column(name = "ENROLL_ID", nullable = false)
	public String getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}

	@Column(name = "ENROLL_NAME")
	public String getEnrollName() {
		return enrollName;
	}

	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}

	@Column(name = "ENROLL_PRNT_ID")
	public String getEnrollPrntId() {
		return enrollPrntId;
	}

	public void setEnrollPrntId(String enrollPrntId) {
		this.enrollPrntId = enrollPrntId;
	}

	@Column(name = "ENROLL_RELATION")
	public String getEnrollRelation() {
		return enrollRelation;
	}

	public void setEnrollRelation(String enrollRelation) {
		this.enrollRelation = enrollRelation;
	}

	@Column(name = "ENROLL_SNO")
	public Long getEnrollSno() {
		return enrollSno;
	}

	public void setEnrollSno(Long enrollSno) {
		this.enrollSno = enrollSno;
	}

	@Column(name = "ENROLL_STATUS", nullable = false)
	public String getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(String enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	@Column(name = "LST_UPD_DT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Column(name = "LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public void setEnrollDisabilityCerf(String enrollDisabilityCerf) {
		this.enrollDisabilityCerf = enrollDisabilityCerf;
	}

	@Column(name = "ENROLL_DISABILITY_CERF")
	public String getEnrollDisabilityCerf() {
		return enrollDisabilityCerf;
	}

	public void setEnrollPhoto(String enrollPhoto) {
		this.enrollPhoto = enrollPhoto;
	}

	@Column(name = "ENROLL_PHOTO")
	public String getEnrollPhoto() {
		return enrollPhoto;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	@Column(name = "ID_TYPE")
	public String getIdType() {
		return idType;
	}

	public void setEnrollHeight(String enrollHeight) {
		this.enrollHeight = enrollHeight;
	}

	@Column(name = "ENROLL_HEIGHT")
	public String getEnrollHeight() {
		return enrollHeight;
	}

	public void setEnrollDobCerti(String enrollDobCerti) {
		this.enrollDobCerti = enrollDobCerti;
	}

	@Column(name = "ENROLL_DOB_CERTI")
	public String getEnrollDobCerti() {
		return enrollDobCerti;
	}

	public void setEnrollGender(char enrollGender) {
		this.enrollGender = enrollGender;
	}

	@Column(name = "ENROLL_GENDER")
	public char getEnrollGender() {
		return enrollGender;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	@Column(name = "DISPLAY_FLG")
	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setEnrollAadharCerti(String enrollAadharCerti) {
		this.enrollAadharCerti = enrollAadharCerti;
	}

	@Column(name = "ENROLL_AADHAR_CERTI")
	public String getEnrollAadharCerti() {
		return enrollAadharCerti;
	}

	public void setEhfCardNo(String ehfCardNo) {
		this.ehfCardNo = ehfCardNo;
	}

	@Column(name = "EHF_CARD_NO")
	public String getEhfCardNo() {
		return ehfCardNo;
	}

	public void setBiometricString(String biometricString) {
		this.biometricString = biometricString;
	}

	@Column(name = "BIOMETRIC_STRING")
	public String getBiometricString() {
		return biometricString;
	}
	
	@Column(name = "CARD_SLOT_FLAG")
	public String getCardSlotFlag() {
		return cardSlotFlag;
	}

	public void setCardSlotFlag(String cardSlotFlag) {
		this.cardSlotFlag = cardSlotFlag;
	}
	@Column(name = "AADHAR_EXISTS")
	public String getAadharExists() {
		return aadharExists;
	}

	public void setAadharExists(String aadharExists) {
		this.aadharExists = aadharExists;
	}
	
	@Column(name = "REJECTED")
	public String getRejected() {
		return rejected;
	}

	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	@Column(name = "SUBMITTED_DATE")
	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	@Column(name = "SPOUSE_EMP_TYPE")
	public String getSpouseEmpType() {
		return spouseEmpType;
	}

	public void setSpouseEmpType(String spouseEmpType) {
		this.spouseEmpType = spouseEmpType;
	}

	@Column(name = "ANNUAL_CHECKUP")
	public String getAnnualCheckup() {
		return annualCheckup;
	}

	public void setAnnualCheckup(String annualCheckup) {
		this.annualCheckup = annualCheckup;
	}
	
	//Chandana - 8326
		@Column(name="EKYC_DONE_YN")
		public String getEkycDoneYn() {
			return ekycDoneYn;
		}

		public void setEkycDoneYn(String ekycDoneYn) {
			this.ekycDoneYn = ekycDoneYn;
		}
		@Column(name="ABHA_ID")
		public String getAbhaId() {
			return abhaId;
		}
		public void setAbhaId(String abhaId) {
			this.abhaId = abhaId;
		}
		@Column(name="ABHA_UPD_DT")
		public Date getAbhaUpdDt() {
			return abhaUpdDt;
		}
		public void setAbhaUpdDt(Date abhaUpdDt) {
			this.abhaUpdDt = abhaUpdDt;
		}
		@Column(name="ABHA_UPD_USR")
		public String getAbhaUpdUsr() {
			return abhaUpdUsr;
		}
		public void setAbhaUpdUsr(String abhaUpdUsr) {
			this.abhaUpdUsr = abhaUpdUsr;
		}
	


}
