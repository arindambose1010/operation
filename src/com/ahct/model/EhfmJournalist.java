package com.ahct.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "EHFM_JRNLST")
public class EhfmJournalist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID", nullable = false)
	private  String  userId;
	
	@Column(name = "USER_NO", nullable = false)
	private  String  userNo;
	
	@Column(name = "LOC_ID", nullable = false)
	private  String  locId;
	
	@Column(name = "LOGIN_NAME", nullable = false)
	private  String  loginName;
	
	@Column(name = "FIRST_NAME", nullable = true)
	private  String  firstName;
	
	@Column(name = "MIDDLE_NAME",nullable=true)
	private  String  middleName;
	
	@Column(name = "LAST_NAME", nullable = false)
	private  String  lastName;
	
	@Column(name = "DOJ", nullable = true)
	private  Date 	 doj;
	
	@Column(name = "DSGN_ID", nullable = false)
	private  String  dsgnId;
	
	@Column(name = "EMAIL_ID")
	private  String  emailId;
	
	
	@Column(name = "SERVICE_FLG", nullable = false)
	private  String  serviceFlg;
	
	@Column(name = "LANG_ID", nullable = false)
	private  String  langId;
	
	@Column(name = "MOBILE_NO")
	private  String  mobileNo;
	
	@Column(name = "SERVICE_EXPIRY_DT")
	private  String  serviceExpiryDt;
	
	@Column(name = "FORGOT_PWD")
	private  String  forgotPwd;
	
	@Column(name = "FRST_LOGIN_FLG")
	private  String  frstLoginFlg;
	
	@Column(name = "PERIOD_FROM")
	private  Date    periodFrom ;  
	
	@Column(name = "PERIOD_TO")
	private  Date    periodTo;
	
	@Column(name = "PASSWD")
	private  String  passwd;
	
	@Column(name = "BIO_AUTH_REQ")
	private  String  bioAuthReq;
	
	@Column(name = "DIGI_VERIFY_REQ")
	private  String  DigiVerifyReq;
	
	@Column(name = "USER_TYPE")
	private  String  userType;
	
	@Column(name = "CRT_DT",nullable=false)
	private  Timestamp  crtDt;
	
	@Column(name = "CRT_USR",nullable=false)
	private  String  crtUsr;
	
	@Column(name = "LST_UPD_USR")
	private  String  lstUpdUsr;
	
	@Column(name = "LST_UPD_DT")
	private  Date    lstUpdDt;
	
	@Column(name = "THEME")
	private  String  theme;
	
	@Column(name = "DJHS_FLAG")
	private  String  djhsFlag;
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getDsgnId() {
		return dsgnId;
	}
	public void setDsgnId(String dsgnId) {
		this.dsgnId = dsgnId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getServiceFlg() {
		return serviceFlg;
	}
	public void setServiceFlg(String serviceFlg) {
		this.serviceFlg = serviceFlg;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getServiceExpiryDt() {
		return serviceExpiryDt;
	}
	public void setServiceExpiryDt(String serviceExpiryDt) {
		this.serviceExpiryDt = serviceExpiryDt;
	}
	public String getForgotPwd() {
		return forgotPwd;
	}
	public void setForgotPwd(String forgotPwd) {
		this.forgotPwd = forgotPwd;
	}
	public String getFrstLoginFlg() {
		return frstLoginFlg;
	}
	public void setFrstLoginFlg(String frstLoginFlg) {
		this.frstLoginFlg = frstLoginFlg;
	}
	public Date getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}
	public Date getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getBioAuthReq() {
		return bioAuthReq;
	}
	public void setBioAuthReq(String bioAuthReq) {
		this.bioAuthReq = bioAuthReq;
	}
	public String getDigiVerifyReq() {
		return DigiVerifyReq;
	}
	public void setDigiVerifyReq(String digiVerifyReq) {
		DigiVerifyReq = digiVerifyReq;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Timestamp getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Timestamp timestamp) {
		this.crtDt = timestamp;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDjhsFlag() {
		return djhsFlag;
	}
	public void setDjhsFlag(String djhsFlag) {
		this.djhsFlag = djhsFlag;
	}

}
