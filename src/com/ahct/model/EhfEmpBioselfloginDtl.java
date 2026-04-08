package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_EMP_BIOSELFLOGIN_DTLS database table.
 * 
 */
@Entity
@Table(name="EHF_EMP_BIOSELFLOGIN_DTLS")
public class EhfEmpBioselfloginDtl implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfEmpBioselfloginDtlPK id;
	private String attendancetype;
	private Date crtDt;
	private String crtUsr;
	private String hospitalCode;
	private Date loginTime;
	private Date logoutDate;
	private Date logoutTime;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String macAddress;

    public EhfEmpBioselfloginDtl() {
    }


	@EmbeddedId
	public EhfEmpBioselfloginDtlPK getId() {
		return this.id;
	}

	public void setId(EhfEmpBioselfloginDtlPK id) {
		this.id = id;
	}
	

	public String getAttendancetype() {
		return this.attendancetype;
	}

	public void setAttendancetype(String attendancetype) {
		this.attendancetype = attendancetype;
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


	@Column(name="HOSPITAL_CODE")
	public String getHospitalCode() {
		return this.hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LOGIN_TIME")
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="LOGOUT_DATE")
	public Date getLogoutDate() {
		return this.logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LOGOUT_TIME")
	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
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


	@Column(name="MAC_ADDRESS")
	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

}