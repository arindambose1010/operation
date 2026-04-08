package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_RELATION_MST"
)
public class EhfmRelationMst implements java.io.Serializable{

private Integer relationId;
private String relationName;
private String cmbDtlId;
private String langId;
private char activeYN;
private Date crtDt;
private String crtUser;
private Date lstUpdDt;
private String lstUpdUsr;
@Id
@Column(name="relation_id", nullable=false)
public Integer getRelationId() {
	return relationId;
}
public void setRelationId(Integer relationId) {
	this.relationId = relationId;
}
@Column(name="relation_name", nullable=true)
public String getRelationName() {
	return relationName;
}
public void setRelationName(String relationName) {
	this.relationName = relationName;
}
@Column(name="cmb_dtl_id", nullable=true)
public String getCmbDtlId() {
	return cmbDtlId;
}
public void setCmbDtlId(String cmbDtlId) {
	this.cmbDtlId = cmbDtlId;
}
@Column(name="lang_id", nullable=true)
public String getLangId() {
	return langId;
}
public void setLangId(String langId) {
	this.langId = langId;
}
@Column(name="active_yn", nullable=true)
public char getActiveYN() {
	return activeYN;
}
public void setActiveYN(char activeYN) {
	this.activeYN = activeYN;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt", nullable=true)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="crt_usr", nullable=true)
public String getCrtUser() {
	return crtUser;
}
public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="lst_upd_dt", nullable=true)
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
@Column(name="lst_upd_usr", nullable=true)
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
public EhfmRelationMst(Integer relationId, String relationName,
		String cmbDtlId, String langId, char activeYN, Date crtDt,
		String crtUser, Date lstUpdDt, String lstUpdUsr) {
	super();
	this.relationId = relationId;
	this.relationName = relationName;
	this.cmbDtlId = cmbDtlId;
	this.langId = langId;
	this.activeYN = activeYN;
	this.crtDt = crtDt;
	this.crtUser = crtUser;
	this.lstUpdDt = lstUpdDt;
	this.lstUpdUsr = lstUpdUsr;
}
public EhfmRelationMst() {
	super();
}


}
