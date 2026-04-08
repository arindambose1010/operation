package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHFM_USER_LOGIN_DTLS")
public class EhfmUserLoginDtls implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String seqId;
	private String sessionId;
	private String userId;
	private String ipAddress;
	private String macAddress;
	private Date loginTime;
	private Date logoutTime;
	private Date accounts;
	private Date admin;
	private Date ceo;
	private Date cms;
	private Date ehs;
	private Date empanelment;
	private Date grievance;
	private Date home;
	private Date homepage;
	private Date journalist;
	private Date operations;
	private Date operationsnabh;
	private Date pricing;
	private Date reports;
	private Date crtdt;
	private String crtusr;
	
	@Id
	@Column(name="SEQ_ID", nullable=false)
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	
	@Column(name="SESSION_ID")
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="IP_ADDRESS")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name="MAC_ADDRESS")
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOGIN_TIME")
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOGOUT_TIME")
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATIONS")
	public Date getOperations() {
		return operations;
	}
	public void setOperations(Date operations) {
		this.operations = operations;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACCOUNTS")
	public Date getAccounts() {
		return accounts;
	}
	public void setAccounts(Date accounts) {
		this.accounts = accounts;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EMPANELMENT")
	public Date getEmpanelment() {
		return empanelment;
	}
	public void setEmpanelment(Date empanelment) {
		this.empanelment = empanelment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ADMIN")
	public Date getAdmin() {
		return admin;
	}
	public void setAdmin(Date admin) {
		this.admin = admin;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO")
	public Date getCeo() {
		return ceo;
	}
	public void setCeo(Date ceo) {
		this.ceo = ceo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CMS")
	public Date getCms() {
		return cms;
	}
	public void setCms(Date cms) {
		this.cms = cms;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EHS")
	public Date getEhs() {
		return ehs;
	}
	public void setEhs(Date ehs) {
		this.ehs = ehs;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GRIEVANCE")
	public Date getGrievance() {
		return grievance;
	}
	public void setGrievance(Date grievance) {
		this.grievance = grievance;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HOME")
	public Date getHome() {
		return home;
	}
	public void setHome(Date home) {
		this.home = home;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HOMEPAGE")	
	public Date getHomepage() {
		return homepage;
	}
	public void setHomepage(Date homepage) {
		this.homepage = homepage;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="JOURNALIST")
	public Date getJournalist() {
		return journalist;
	}
	public void setJournalist(Date journalist) {
		this.journalist = journalist;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATIONS_NABH")
	public Date getOperationsnabh() {
		return operationsnabh;
	}
	public void setOperationsnabh(Date operationsnabh) {
		this.operationsnabh = operationsnabh;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PRICING")
	public Date getPricing() {
		return pricing;
	}
	public void setPricing(Date pricing) {
		this.pricing = pricing;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPORTS")
	public Date getReports() {
		return reports;
	}
	public void setReports(Date reports) {
		this.reports = reports;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtdt() {
		return crtdt;
	}
	public void setCrtdt(Date crtdt) {
		this.crtdt = crtdt;
	}
	
	@Column(name="CRT_USR")
	public String getCrtusr() {
		return crtusr;
	}
	public void setCrtusr(String crtusr) {
		this.crtusr = crtusr;
	}
	
	
}
