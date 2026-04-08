package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_HOSP_QUANTITY_NIMS")
public class EhfmHospQuantityNIMS implements java.io.Serializable{
private String deptId;
private String itemCode;
private String itemDesc;
private Integer amount;
private String crtUser;
private Date crtDt;
private String activeYn;

@Id
@Column(name="ITEM_CODE")
public String getItemCode() {
	return itemCode;
}
public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}
@Column(name="DEPT_ID")
public String getDeptId() {
	return deptId;
}
public void setDeptId(String deptId) {
	this.deptId = deptId;
}
@Column(name="ITEM_DESC")
public String getItemDesc() {
	return itemDesc;
}
public void setItemDesc(String itemDesc) {
	this.itemDesc = itemDesc;
}
@Column(name="AMOUNT")
public Integer getAmount() {
	return amount;
}
public void setAmount(Integer amount) {
	this.amount = amount;
}
@Column(name="CRT_USR")
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

@Column(name="ACTIVE")
public String getActiveYn() {
	return activeYn;
}
public void setActiveYn(String activeYn) {
	this.activeYn = activeYn;
}
public EhfmHospQuantityNIMS() {
	super();
}
public EhfmHospQuantityNIMS(String deptId,String itemCode, String itemDesc, Integer amount,
		String crtUser, Date crtDt) {
	super();
	this.deptId = deptId;
	this.itemCode = itemCode;
	this.itemDesc = itemDesc;
	this.amount = amount;
	this.crtUser = crtUser;
	this.crtDt = crtDt;
}




}
