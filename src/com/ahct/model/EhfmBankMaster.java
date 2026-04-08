package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_BANK_MASTER")
public class EhfmBankMaster implements java.io.Serializable{
private String bankId;
private String ifcCode;
private String bankName;
private String bankBranch;
private String bankCategory;
private float openBalance;
private float closeBalance;
private Integer schemeId;
private String crtUser;
private Date crtDt;
private String lstUpdUser;
private Date lstUpdDt;
private String district;
private String city;
private String state;
private String micrCode;

@Id
@Column(name="bank_id", nullable=false)
public String getBankId() {
	return bankId;
}
public void setBankId(String bankId) {
	this.bankId = bankId;
}
@Column(name="ifc_code", nullable=true)
public String getIfcCode() {
	return ifcCode;
}
public void setIfcCode(String ifcCode) {
	this.ifcCode = ifcCode;
}
@Column(name="bank_name", nullable=true)
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
@Column(name="bank_branch", nullable=true)
public String getBankBranch() {
	return bankBranch;
}
public void setBankBranch(String bankBranch) {
	this.bankBranch = bankBranch;
}
@Column(name="bank_category", nullable=true)
public String getBankCategory() {
	return bankCategory;
}
public void setBankCategory(String bankCategory) {
	this.bankCategory = bankCategory;
}
@Column(name="open_balance", nullable=true)
public float getOpenBalance() {
	return openBalance;
}
public void setOpenBalance(float openBalance) {
	this.openBalance = openBalance;
}
@Column(name="clos_balance", nullable=true)
public float getCloseBalance() {
	return closeBalance;
}
public void setCloseBalance(float closeBalance) {
	this.closeBalance = closeBalance;
}
@Column(name="scheme_id", nullable=true)
public Integer getSchemeId() {
	return schemeId;
}
public void setSchemeId(Integer schemeId) {
	this.schemeId = schemeId;
}
@Column(name="crt_usr", nullable=true)
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt", nullable=true)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="lst_upd_usr", nullable=true)
public String getLstUpdUser() {
	return lstUpdUser;
}
public void setLstUpdUser(String lstUpdUser) {
	this.lstUpdUser = lstUpdUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="lst_upd_dt", nullable=true)
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
@Column(name="district", nullable=false)
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
@Column(name="city", nullable=false)
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
@Column(name="micr_Code", nullable=false)
public String getMicrCode() {
	return micrCode;
}
public void setMicrCode(String micrCode) {
	this.micrCode = micrCode;
}
@Column(name="state", nullable=false)
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

public EhfmBankMaster(String bankId, String ifcCode, String bankName,
		String bankBranch, String bankCategory, float openBalance,
		float closeBalance, Integer schemeId, String crtUser, Date crtDt,
		String lstUpdUser, Date lstUpdDt,String district,String city,
		String micrCode,String state) {
	super();
	this.bankId = bankId;
	this.ifcCode = ifcCode;
	this.bankName = bankName;
	this.bankBranch = bankBranch;
	this.bankCategory = bankCategory;
	this.openBalance = openBalance;
	this.closeBalance = closeBalance;
	this.schemeId = schemeId;
	this.crtUser = crtUser;
	this.crtDt = crtDt;
	this.lstUpdUser = lstUpdUser;
	this.lstUpdDt = lstUpdDt;
	this.district=district;
	this.city=city;
	this.state=state;
	this.micrCode=micrCode;
}
public EhfmBankMaster() {
	super();
}



}

