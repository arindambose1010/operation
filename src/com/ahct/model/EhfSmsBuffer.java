package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table( name = "EHF_SMS_BUFFER" )
public class EhfSmsBuffer implements Serializable {

	private String serialNo;
	private String phoneNo;
	private String smsText;
	private String resentYN;
	private String crtUsr;
	private Date crtDt;
	private Date lstUpdDt;
	
	@Id
	@Column(name="SERIAL_NO")  
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
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
	@Column(name="RESENT_YN")  
	public String getResentYN() {
		return resentYN;
	}
	public void setResentYN(String resentYN) {
		this.resentYN = resentYN;
	}
	@Column(name="CRT_USR")  
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Column(name="CRT_DT")  
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_DT") 
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
}
