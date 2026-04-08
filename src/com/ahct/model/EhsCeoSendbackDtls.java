package com.ahct.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;

@Entity
@Table(name="EHS_CEO_SENDBACK_DTLS")
public class EhsCeoSendbackDtls implements Serializable{
	
	public EhsCeoSendbackDtlsId id;
	private String functionality;
	private String unitId;
	private String department;
	private String schemeId;
	private Date recievedDate;
	private String ceoRemarks;
	private String userRemarks;
	private String acted;
	private String smsFlag;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false, length = 20)),
		@AttributeOverride(name = "referenceNo", column = @Column(name = "REFERENCE_NO", nullable = false, length = 20))
	})
	public EhsCeoSendbackDtlsId getId() {
		return id;
	}
	public void setId(EhsCeoSendbackDtlsId id) {
		this.id = id;
	}
	
	@Column(name="FUNCTIONALITY", nullable = false)
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	} 
	
	@Column(name="UNIT_ID", nullable = false)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name="DEPARTMANT", nullable = false)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RECIEVED_DATE", nullable = false)
	public Date getRecievedDate() {
		return recievedDate;
	}
	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}
	
	@Column(name="CEO_REMARKS")
	public String getCeoRemarks() {
		return ceoRemarks;
	}
	public void setCeoRemarks(String ceoRemarks) {
		this.ceoRemarks = ceoRemarks;
	}
	
	@Column(name="USER_REMARKS")
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	
	@Column(name="ACTED", nullable = false)
	public String getActed() {
		return acted;
	}
	public void setActed(String acted) {
		this.acted = acted;
	}
	
	@Column(name="SMS_FLAG", nullable = false)
	public String getSmsFlag() {
		return smsFlag;
	}
	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}
	
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name="LST_UPD_USR" )
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT" )
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
}
