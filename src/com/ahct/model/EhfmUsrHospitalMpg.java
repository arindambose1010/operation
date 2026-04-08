package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "EHFM_USR_hospital_MPG")
public class EhfmUsrHospitalMpg implements java.io.Serializable {
private EhfmUsrHospitalMpgId id;
private String activeYN;
private Date crtDt;
private String crtUsr;
@EmbeddedId
@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 12)),
		@AttributeOverride(name = "hospitalId", column = @Column(name = "hospital_id", nullable = false, length = 10)) })
public EhfmUsrHospitalMpgId getId() {
	return id;
}
public void setId(EhfmUsrHospitalMpgId id) {
	this.id = id;
}
@Column(name = "activeYN")
public String getActiveYN() {
	return activeYN;
}
public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}
@Column(name = "CRT_DT")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name = "CRT_USR")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
public EhfmUsrHospitalMpg(EhfmUsrHospitalMpgId id, String activeYN, Date crtDt,
		String crtUsr) {
	super();
	this.id = id;
	this.activeYN = activeYN;
	this.crtDt = crtDt;
	this.crtUsr = crtUsr;
}
public EhfmUsrHospitalMpg() {
	super();
}


}
