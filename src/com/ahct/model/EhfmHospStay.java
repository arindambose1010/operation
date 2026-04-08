package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHFM_HOSP_STAY")
public class EhfmHospStay implements java.io.Serializable{
private String hospStayId;
private String stayType;
private String crtUsr;
private Date crtDt;
private String enhType;
@Id
@Column(name="hosp_stay_id")
public String getHospStayId() {
	return hospStayId;
}
public void setHospStayId(String hospStayId) {
	this.hospStayId = hospStayId;
}
@Column(name="stay_type")
public String getStayType() {
	return stayType;
}
public void setStayType(String stayType) {
	this.stayType = stayType;
}
@Column(name="crt_usr")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

public EhfmHospStay() {
	super();
}
@Column(name="enh_type")
public String getEnhType() {
	return enhType;
}
public void setEnhType(String enhType) {
	this.enhType = enhType;
}
public EhfmHospStay(String hospStayId, String stayType, String crtUsr,
		Date crtDt, String enhType) {
	super();
	this.hospStayId = hospStayId;
	this.stayType = stayType;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.enhType = enhType;
}


}
