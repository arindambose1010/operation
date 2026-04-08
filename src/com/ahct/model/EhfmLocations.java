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
@Table(name="ehfm_locations")
public class EhfmLocations implements java.io.Serializable{

	private EhfmLocationsId id;
	private String locName;
	private Long locOrder;
	private String locDesc;
	private Long locVal;
	private String langId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpd_dt;
	private String locHdrId;
	private String activeYn;
	private String locShrtName;
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "locId", column = @Column(name = "LOC_ID", nullable = false, length = 25)),
			@AttributeOverride(name = "locParntId", column = @Column(name = "LOC_PARNT_ID", nullable = false, length=25)) })
	public EhfmLocationsId getId() {
		return id;
	}
	public void setId(EhfmLocationsId id) {
		this.id = id;
	}
	
	@Column(columnDefinition="ntext",name = "LOC_NAME", nullable = true, length = 100)
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	
	@Column(name = "LOC_ORDER", nullable = true, precision=10,scale=0)
	public Long getLocOrder() {
		return locOrder;
	}
	public void setLocOrder(Long locOrder) {
		this.locOrder = locOrder;
	}
	
	@Column(columnDefinition="ntext",name = "LOC_DESC", nullable = true, length = 100)
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	
	@Column(name = "LOC_VAL", nullable = true, precision=10,scale=0)
	public Long getLocVal() {
		return locVal;
	}
	public void setLocVal(Long locVal) {
		this.locVal = locVal;
	}
	
	@Column(name = "LANG_ID", nullable = true, length = 5)
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	
	@Column(name = "CRT_USR", nullable = true, length = 12)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CRT_DT", nullable = true, length = 7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name = "LST_UPD_USR", nullable = true, length = 12)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT", nullable = true, length = 7)
	public Date getLstUpd_dt() {
		return lstUpd_dt;
	}
	public void setLstUpd_dt(Date lstUpd_dt) {
		this.lstUpd_dt = lstUpd_dt;
	}
	
	@Column(name = "LOC_HDR_ID", nullable = true, length = 5)
	public String getLocHdrId() {
		return locHdrId;
	}
	public void setLocHdrId(String locHdrId) {
		this.locHdrId = locHdrId;
	}
	
	@Column(columnDefinition="char", name = "ACTIVE_YN", nullable = false, length = 1)
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="loc_shrt_name")
	public String getLocShrtName() {
		return locShrtName;
	}
	public void setLocShrtName(String locShrtName) {
		this.locShrtName = locShrtName;
	}
		

}
