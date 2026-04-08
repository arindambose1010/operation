package com.ahct.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EHF_ONCOLOGY_OTP_AUDIT")
public class EhfOncologyOtpAudit implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq-gen")
	@SequenceGenerator(name="seq-gen", sequenceName="EHF_ONCOLOGY_OTP_AUDIT_SEQ", initialValue=20, allocationSize=1)
	@Column(name="SEQ_ID")
	private Long seqId;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="OTP")
	private String otp;
	
	@Column(name="GENERATE_FLAG")
	private String generateFlag;
	
	@Column(name="VERIFY_FLAG")
	private String verifyFlag;
	
	@Column(name="OTP_COUNT")
	private Long otpCount;
	
	@Column(name="CRT_DT")
	private Timestamp crtDt;
	
	@Column(name="LST_UPD_DT")
	private Timestamp lstUpdDt;
	
	@Column(name="OTP_VALID_DT")
	private Timestamp otpValidDt;

	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getGenerateFlag() {
		return generateFlag;
	}

	public void setGenerateFlag(String generateFlag) {
		this.generateFlag = generateFlag;
	}

	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public Long getOtpCount() {
		return otpCount;
	}

	public void setOtpCount(Long otpCount) {
		this.otpCount = otpCount;
	}

	public Timestamp getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Timestamp crtDt) {
		this.crtDt = crtDt;
	}

	public Timestamp getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Timestamp lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public Timestamp getOtpValidDt() {
		return otpValidDt;
	}

	public void setOtpValidDt(Timestamp otpValidDt) {
		this.otpValidDt = otpValidDt;
	}
	
}
