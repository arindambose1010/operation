package com.ahct.login.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;



public class LoginForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String meeLogin;
	private String meePassword;
	private String meeReqId;
	private String msg;
	
	private String personName;
	private String empId;
	private String pensionerId;
	private String emporPensionerId;
	private String mobileNo;
	private String emailId;
	private String issue;
	private String distId;
	private String stoId;
	private String hodId;
	private String ddoId;
	private String loginType;
	private String district;
	private int noOfNotifications;
	private String otpAuthenticate;
	private String otp;
	private String otpTemp;
	private String checkClose;
	private String checkClose2;
	
	
	
	public String getCheckClose2() {
		return checkClose2;
	}
	public void setCheckClose2(String checkClose2) {
		this.checkClose2 = checkClose2;
	}
	public int getNoOfNotifications() {
		return noOfNotifications;
	}
	public void setNoOfNotifications(int noOfNotifications) {
		this.noOfNotifications = noOfNotifications;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private FormFile attachment;
	private String attachPath;
	
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDdoId() {
		return ddoId;
	}
	public void setDdoId(String ddoId) {
		this.ddoId = ddoId;
	}
	
	public String getEmporPensionerId() {
		return emporPensionerId;
	}
	public void setEmporPensionerId(String emporPensionerId) {
		this.emporPensionerId = emporPensionerId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPensionerId() {
		return pensionerId;
	}
	public void setPensionerId(String pensionerId) {
		this.pensionerId = pensionerId;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getStoId() {
		return stoId;
	}
	public void setStoId(String stoId) {
		this.stoId = stoId;
	}
	public String getHodId() {
		return hodId;
	}
	public void setHodId(String hodId) {
		this.hodId = hodId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMeeLogin() {
		return meeLogin;
	}
	public void setMeeLogin(String meeLogin) {
		this.meeLogin = meeLogin;
	}
	public String getMeePassword() {
		return meePassword;
	}
	public void setMeePassword(String meePassword) {
		this.meePassword = meePassword;
	}
	public String getMeeReqId() {
		return meeReqId;
	}
	public void setMeeReqId(String meeReqId) {
		this.meeReqId = meeReqId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public FormFile getAttachment() {
		return attachment;
	}
	public void setAttachment(FormFile attachment) {
		this.attachment = attachment;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getOtpAuthenticate() {
		return otpAuthenticate;
	}
	public void setOtpAuthenticate(String otpAuthenticate) {
		this.otpAuthenticate = otpAuthenticate;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtpTemp() {
		return otpTemp;
	}
	public void setOtpTemp(String otpTemp) {
		this.otpTemp = otpTemp;
	}
	public String getCheckClose() {
		return checkClose;
	}
	public void setCheckClose(String checkClose) {
		this.checkClose = checkClose;
	}
	
	
}
