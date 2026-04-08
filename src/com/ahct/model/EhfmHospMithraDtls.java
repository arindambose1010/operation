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
@Table(name="EHFM_HOSP_MITHRA_DTLS"
)
public class EhfmHospMithraDtls implements java.io.Serializable{
	
private EhfmHospMithraDtlsId id;
private Date endDt;
private String shiftId;
private char activeYN;
private Date crtDt;
private String crtUser;
private Date lstUpdDt;
private String lstUpdUser;
@EmbeddedId

@AttributeOverrides( {
   @AttributeOverride(name="hospId", column=@Column(name="hosp_id", nullable=false) ), 
   @AttributeOverride(name="mithraId", column=@Column(name="mithra_id", nullable=false) ),
   @AttributeOverride(name="startDt", column=@Column(name="start_dt", nullable=false) )
   } )
public EhfmHospMithraDtlsId getId() {
	return id;
}
public void setId(EhfmHospMithraDtlsId id) {
	this.id = id;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="end_dt", nullable=true)
public Date getEndDt() {
	return endDt;
}
public void setEndDt(Date endDt) {
	this.endDt = endDt;
}
@Column(name="shift_id", nullable=true)
public String getShiftId() {
	return shiftId;
}
public void setShiftId(String shiftId) {
	this.shiftId = shiftId;
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
public String getLstUpdUser() {
	return lstUpdUser;
}
public void setLstUpdUser(String lstUpdUser) {
	this.lstUpdUser = lstUpdUser;
}
public EhfmHospMithraDtls(EhfmHospMithraDtlsId id, Date endDt, String shiftId,
		char activeYN, Date crtDt, String crtUser, Date lstUpdDt,
		String lstUpdUser) {
	super();
	this.id = id;
	this.endDt = endDt;
	this.shiftId = shiftId;
	this.activeYN = activeYN;
	this.crtDt = crtDt;
	this.crtUser = crtUser;
	this.lstUpdDt = lstUpdDt;
	this.lstUpdUser = lstUpdUser;
}
public EhfmHospMithraDtls() {
	super();
}



}
