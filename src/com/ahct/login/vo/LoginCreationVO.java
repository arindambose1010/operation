package com.ahct.login.vo;
import java.util.List;

public class LoginCreationVO implements java.io.Serializable{
private String empCode;
private String empType;
private String empDesgnId;
private String firstName;
private String lastName;
private String crtuser;
private String locId;
private String langId;
private String mobileNo;
private String emailId;
private String dob;
private String grpType;
private String password;
private String dsgnId;


public String getDsgnId() {
	return dsgnId;
}
public void setDsgnId(String dsgnId) {
	this.dsgnId = dsgnId;
}
public String getGrpType() {
	return grpType;
}
public void setGrpType(String grpType) {
	this.grpType = grpType;
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
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getLocId() {
	return locId;
}
public void setLocId(String locId) {
	this.locId = locId;
}
public String getLangId() {
	return langId;
}
public void setLangId(String langId) {
	this.langId = langId;
}
public String getCrtuser() {
	return crtuser;
}
public void setCrtuser(String crtuser) {
	this.crtuser = crtuser;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
private List<LoginCreationVO> lstLogins;

public List<LoginCreationVO> getLstLogins() {
	return lstLogins;
}
public void setLstLogins(List<LoginCreationVO> lstLogins) {
	this.lstLogins = lstLogins;
}
public String getEmpCode() {
	return empCode;
}
public void setEmpCode(String empCode) {
	this.empCode = empCode;
}
public String getEmpType() {
	return empType;
}
public void setEmpType(String empType) {
	this.empType = empType;
}
public String getEmpDesgnId() {
	return empDesgnId;
}
public void setEmpDesgnId(String empDesgnId) {
	this.empDesgnId = empDesgnId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


}
