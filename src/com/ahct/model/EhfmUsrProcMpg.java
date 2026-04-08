package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "EHFM_USR_PROC_MPG")
public class EhfmUsrProcMpg implements java.io.Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private EhfmUsrProcMpgId id;
private String procName;
private String crtUsr;
private Date crtDt;
private String lstUpdUsr;
private Date lstUpdDt;
private String activeYN;
@EmbeddedId
@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 12)),
		@AttributeOverride(name = "procId", column = @Column(name = "procedure_id", nullable = false, length = 12)),
		@AttributeOverride(name = "icdCatCode",column= @Column(name = "icd_cat_code",nullable = false,length = 12)), 
		@AttributeOverride(name = "asriCode",column= @Column(name = "asri_code",nullable = false,length = 12)) })
public EhfmUsrProcMpgId getId() {
	return id;
}
public void setId(EhfmUsrProcMpgId id) {
	this.id = id;

}
@Column(name = "PROC_NAME")
public String getProcName() {
	return procName;
}
public void setProcName(String procName) {
	this.procName = procName;
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
public EhfmUsrProcMpg(EhfmUsrProcMpgId id, String crtUsr,
		Date crtDt, String lstUpdUsr, Date lstUpdDt) {
	super();
	this.id = id;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.lstUpdUsr = lstUpdUsr;
	this.lstUpdDt = lstUpdDt;
}
public EhfmUsrProcMpg() {
	super();
}
@Column(name = "ACTIVE_YN")
public String getActiveYN() {
	return activeYN;
}
public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}
	

}
