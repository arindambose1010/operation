package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "EHFM_USR_speciality_MPG")
public class EhfmUsrSpecialityMpg implements java.io.Serializable{
private EhfmUsrSpecialityMpgId id;
private String crtUsr;
private Date crtDt;
private String lstUpdUsr;
private Date lstUpdDt;
private String activeYN;
@EmbeddedId
@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 12)),
		@AttributeOverride(name = "specialityId", column = @Column(name = "Speciality_id", nullable = false, length = 12)),
		@AttributeOverride(name = "grpId", column = @Column(name = "grp_id", nullable = false, length = 5)) })
public EhfmUsrSpecialityMpgId getId() {
	return id;
}
public void setId(EhfmUsrSpecialityMpgId id) {
	this.id = id;
}
@Column(name = "CRT_USR")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Column(name = "CRT_DT")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name = "LST_UPD_USR")
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
@Column(name = "LST_UPD_DT")
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
public EhfmUsrSpecialityMpg(EhfmUsrSpecialityMpgId id, String crtUsr,
		Date crtDt, String lstUpdUsr, Date lstUpdDt) {
	super();
	this.id = id;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.lstUpdUsr = lstUpdUsr;
	this.lstUpdDt = lstUpdDt;
}
public EhfmUsrSpecialityMpg() {
	super();
}
@Column(name = "activeYN")
public String getActiveYN() {
	return activeYN;
}
public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}
	

}
