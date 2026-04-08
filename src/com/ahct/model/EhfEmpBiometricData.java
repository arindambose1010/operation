package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_EMP_BIOMETRIC_DATA database table.
 * 
 */
@Entity
@Table(name="EHF_EMP_BIOMETRIC_DATA")
public class EhfEmpBiometricData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private Date crtDt;
	private String crtUsr;
	private String encryptedFingerprint;
	private String hospitalCode;
	private String loginName;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String macAddress;

    public EhfEmpBiometricData() {
    }


	@Id
	@Column(name="USER_ID")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


    @Temporal( TemporalType.DATE)
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


	@Column(name="ENCRYPTED_FINGERPRINT")
	public String getEncryptedFingerprint() {
		return this.encryptedFingerprint;
	}

	public void setEncryptedFingerprint(String encryptedFingerprint) {
		this.encryptedFingerprint = encryptedFingerprint;
	}


	@Column(name="HOSPITAL_CODE")
	public String getHospitalCode() {
		return this.hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	@Column(name="LOGIN_NAME")
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


    @Temporal( TemporalType.DATE)
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


	@Column(name="MAC_ADDRESS")
	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

}