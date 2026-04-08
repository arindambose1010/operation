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

@SuppressWarnings("serial")
@Entity
@Table(name = "EHFM_DOCTOR_QLFCTNS")
public class EhfmDoctorQlfctns implements Serializable {

	private EhfmDoctorQlfctnsId id;
	private String reqNo;
	private String isActiveYn;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "regNum", column = @Column(name="REG_NUM", nullable = false,length = 50)),
			@AttributeOverride(name = "hospId", column = @Column(name="HOSP_ID", nullable = false,length = 20)),
			@AttributeOverride(name = "qualId", column = @Column(name="QUAL_ID", nullable = false,length = 10)) })
	public EhfmDoctorQlfctnsId getId() {
		return id;
	}
	public void setId(EhfmDoctorQlfctnsId id) {
		this.id = id;
	}
	@Column(name="REQ_NO", nullable = false,length = 50)
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	@Column(columnDefinition="char",name="IS_ACTIVEYN", nullable = false,length = 1)
	public String getIsActiveYn() {
		return isActiveYn;
	}
	public void setIsActiveYn(String isActiveYn) {
		this.isActiveYn = isActiveYn;
	}
	@Column(name="CRT_USR", nullable = false,length = 40)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = true,length = 7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR", nullable = true,length = 40)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable = true,length = 7)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	
}
