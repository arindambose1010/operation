package com.ahct.login.vo;

import java.util.Date;

public class UpdateProfileVo {
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String langId;
	private String crtUsr;
	private String userId;
	private String locId;
	private Date crtDt;
	private String msg;
	private String loginName;
	private String desgId;
	private String serviceFlag;
	private String userName;
	private String userNo;
	private String commMsg;
	
	public String getCommMsg() {
		return commMsg;
	}
	public void setCommMsg(String commMsg) {
		this.commMsg = commMsg;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date date) {
		this.crtDt = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getDesgId() {
		return desgId;
	}
	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}
	public String getServiceFlag() {
		return serviceFlag;
	}
	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
}
