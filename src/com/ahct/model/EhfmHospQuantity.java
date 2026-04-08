package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_HOSP_quantity")
public class EhfmHospQuantity implements java.io.Serializable{
private String quantId;
private String quantName;
private Integer amount;
private String crtUser;
private Date crtDt;
private String enhTypeId;
private String activeYn;

@Id
@Column(name="quant_id")
public String getQuantId() {
	return quantId;
}
public void setQuantId(String quantId) {
	this.quantId = quantId;
}
@Column(name="quant_name")
public String getQuantName() {
	return quantName;
}
public void setQuantName(String quantName) {
	this.quantName = quantName;
}
@Column(name="amount")
public Integer getAmount() {
	return amount;
}
public void setAmount(Integer amount) {
	this.amount = amount;
}
@Column(name="crt_usr")
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="enh_type_id")
public String getEnhTypeId() {
	return enhTypeId;
}
public void setEnhTypeId(String enhTypeId) {
	this.enhTypeId = enhTypeId;
}
@Column(name="ACTIVE_YN")
public String getActiveYn() {
	return activeYn;
}
public void setActiveYn(String activeYn) {
	this.activeYn = activeYn;
}
public EhfmHospQuantity() {
	super();
}
public EhfmHospQuantity(String quantId, String quantName, Integer amount,
		String crtUser, Date crtDt, String enhTypeId) {
	super();
	this.quantId = quantId;
	this.quantName = quantName;
	this.amount = amount;
	this.crtUser = crtUser;
	this.crtDt = crtDt;
	this.enhTypeId = enhTypeId;
}



}
