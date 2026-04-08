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
@Table(name="EHF_PATIENT_SMS_DATA")
public class EhfPatientSmsData implements Serializable {

	private long serialNo; 
	private String phoneNo; 
	private String smsText; 
	private String empCode;
	private String empName; 
	private String crtUsr;
	private Date crtDt;
	private String smsAction;
	private String patientId;
	public EhfPatientSmsData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfPatientSmsData(long serialNo, String phoneNo, String smsText,
			String empCode, String empName, String crtUsr, Date crtDt,
			String smsAction, String patientId) {
		super();
		this.serialNo = serialNo;
		this.phoneNo = phoneNo;
		this.smsText = smsText;
		this.empCode = empCode;
		this.empName = empName;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.smsAction = smsAction;
		this.patientId = patientId;
	}
	@Id
	@Column(name="SERIAL_NO")
	public long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name="PHONE_NO")
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Column(name="SMS_TEXT")
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	@Column(name="EMP_CODE")
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	@Column(name="EMP_NAME")
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="SMS_ACTION")
	public String getSmsAction() {
		return smsAction;
	}
	public void setSmsAction(String smsAction) {
		this.smsAction = smsAction;
	}
	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
}
