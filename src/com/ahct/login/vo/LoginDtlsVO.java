package com.ahct.login.vo;

import java.io.Serializable;
import java.util.Date;

public class LoginDtlsVO implements Serializable {
	
	private String seqId;
	private String sessionId;
	private String userId;
	private String ipAddress;
	private String macAddress;
	private Date loginTime;
	private Date logoutTime;
	private String accounts;
	private String admin;
	private String ceo;
	private String cms;
	private String ehs;
	private String empanelment;
	private String grievance;
	private String home;
	private String homepage;
	private String journalist;
	private String operations;
	private String operationsnabh;
	private String pricing;
	private String reports;
	private Date crtdt;
	private String crtusr;
	
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(String operations) {
		this.operations = operations;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getEmpanelment() {
		return empanelment;
	}
	public void setEmpanelment(String empanelment) {
		this.empanelment = empanelment;
	}
	public String getReports() {
		return reports;
	}
	public void setReports(String reports) {
		this.reports = reports;
	}
	public Date getCrtdt() {
		return crtdt;
	}
	public void setCrtdt(Date crtdt) {
		this.crtdt = crtdt;
	}
	public String getCrtusr() {
		return crtusr;
	}
	public void setCrtusr(String crtusr) {
		this.crtusr = crtusr;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getCms() {
		return cms;
	}
	public void setCms(String cms) {
		this.cms = cms;
	}
	public String getEhs() {
		return ehs;
	}
	public void setEhs(String ehs) {
		this.ehs = ehs;
	}
	public String getGrievance() {
		return grievance;
	}
	public void setGrievance(String grievance) {
		this.grievance = grievance;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getJournalist() {
		return journalist;
	}
	public void setJournalist(String journalist) {
		this.journalist = journalist;
	}
	public String getOperationsnabh() {
		return operationsnabh;
	}
	public void setOperationsnabh(String operationsnabh) {
		this.operationsnabh = operationsnabh;
	}
	public String getPricing() {
		return pricing;
	}
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
	
	
}
