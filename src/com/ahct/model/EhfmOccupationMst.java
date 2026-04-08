package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_OCCUPATION_MST")
public class EhfmOccupationMst implements java.io.Serializable{
private String occupationId;
private String occName;
private String langId;
private char activeYN;
private Date crtDt;
private String crtUsr;
private Date lstUpdDt;
private String lstUpdUsr;
@Id
@Column(name="occupation_id", nullable=false)
public String getOccupationId() {
	return occupationId;
}
public void setOccupationId(String occupationId) {
	this.occupationId = occupationId;
}
@Column(name="occupation_name", nullable=true)
public String getOccName() {
	return occName;
}
public void setOccName(String occName) {
	this.occName = occName;
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
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
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
public EhfmOccupationMst(String occupationId, String occName, String langId,
		char activeYN, Date crtDt, String crtUsr, Date lstUpdDt,
		String lstUpdUsr) {
	super();
	this.occupationId = occupationId;
	this.occName = occName;
	this.langId = langId;
	this.activeYN = activeYN;
	this.crtDt = crtDt;
	this.crtUsr = crtUsr;
	this.lstUpdDt = lstUpdDt;
	this.lstUpdUsr = lstUpdUsr;
}
public EhfmOccupationMst() {
	super();
}


}
