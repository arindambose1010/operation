package com.ahct.model;

import java.io.Serializable;
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
@Table(name = "EHFM_ACCTS_HEADS")
public class EhfmAcctsHeads implements Serializable
{
	
 private EhfmAcctsHeadsId id ;
 private String headName;
 private String headDesc;
 private String headCode;
 private String headParentId;
 private String activeYn;
 private String headOrder;
 private String headType;
 private String accountCode;
 private String vendorYnFlg;
 private Date crtDt;
 private String crtUsr;
 private Date lstUpdDt;
 private String lstUpdUsr;
 
 
 @EmbeddedId
 @AttributeOverrides ( { @AttributeOverride ( name = "headId", column = @Column ( name = "HEAD_ID", nullable = false, length = 20 ))
     , @AttributeOverride ( name = "scheme", column = @Column ( name = "SCHEME", nullable = false, length = 50 )) } )
public EhfmAcctsHeadsId getId() {
	return id;
}
public void setId(EhfmAcctsHeadsId id) {
	this.id = id;
}
@Column(name = "HEAD_NAME", nullable = false, length = 100)
public String getHeadName() {
	return headName;
}
public void setHeadName(String headName) {
	this.headName = headName;
}

@Column(name = "HEAD_DESC", nullable = true, length = 100)
public String getHeadDesc() {
	return headDesc;
}
public void setHeadDesc(String headDesc) {
	this.headDesc = headDesc;
}

@Column(name = "HEAD_CODE", nullable = false, length = 20)
public String getHeadCode() {
	return headCode;
}
public void setHeadCode(String headCode) {
	this.headCode = headCode;
}

@Column(name = "HEAD_PARENT_ID", nullable = true, length = 20)
public String getHeadParentId() {
	return headParentId;
}
public void setHeadParentId(String headParentId) {
	this.headParentId = headParentId;
}

@Column(name = "ACTIVE_YN", nullable = false, length = 1)
public String getActiveYn() {
	return activeYn;
}
public void setActiveYn(String activeYn) {
	this.activeYn = activeYn;
}

@Column(name = "HEAD_ORDER", nullable = true, length = 20)
public String getHeadOrder() {
	return headOrder;
}
public void setHeadOrder(String headOrder) {
	this.headOrder = headOrder;
}

@Column(name = "HEAD_TYPE", nullable = false, length = 10)
public String getHeadType() {
	return headType;
}
public void setHeadType(String headType) {
	this.headType = headType;
}

@Column(name = "ACCOUNT_CODE", nullable = true, length = 20)
public String getAccountCode() {
	return accountCode;
}
public void setAccountCode(String accountCode) {
	this.accountCode = accountCode;
}

@Column(name = "VENDOR_YN_FLG", nullable = true, length = 10)
public String getVendorYnFlg() {
	return vendorYnFlg;
}
public void setVendorYnFlg(String vendorYnFlg) {
	this.vendorYnFlg = vendorYnFlg;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT", nullable = false)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

@Column(name = "CRT_USR", nullable = false, length = 20)
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name="LST_UPD_DT", nullable = true)
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}

@Column(name = "LST_UPD_USR", nullable = true, length = 20)
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
}
