package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "EHF_CONTACT_DTLS_AUDIT")
public class EhfContactDtlsAudit implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private EhfContactDtlsAuditId id;
	private String manuType;
	private String crtUser;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String phone;
	private String email;
	
	
	
	
	@EmbeddedId
	@AttributeOverrides( {
		   @AttributeOverride(name="empNo", column=@Column(name="EMP_NO", nullable=false, length=15) ),
	   @AttributeOverride(name="seqNo", column=@Column(name="SEQ_NO", nullable=false, length=3) )
	   } )
	public EhfContactDtlsAuditId getId() {
		return id;
	}
	public void setId(EhfContactDtlsAuditId id) {
		this.id = id;
	}
	
	@Column(name="MANUP_TYPE",nullable=false)
	public String getManuType() {
		return manuType;
	}
	public void setManuType(String manuType) {
		this.manuType = manuType;
	}
	
	@Column(name="CRT_USR",nullable=false)
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT",nullable=false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name="LST_UPD_USR",nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Column(name="LST_UPD_DT",nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	
	
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="PHONE",nullable=true)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="EMAIL",nullable=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
}
