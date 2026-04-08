package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@SuppressWarnings("serial")
@Entity
@Table(name = "ehfm_cmb_hdr")
public class EhfmCmbHdr implements Serializable{

	@Id
	@Column(name = "CMB_HDR_ID", nullable = false, length = 5)
	private String cmbHdrId;
	@Column(name = "CMB_HDR_NAME", nullable = false, length = 200)
	private String cmbHdrName;
	@Column(name = "CMB_ATTR_NAME", length = 50)
	private String cmbAttrName;
	@Column(name = "LANG_ID", nullable = false, length = 5)
	private String langId;
	@Column(name = "LOC_ID", nullable = false, length = 5)
	private String locId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "crt_dt", nullable = true)
	private Date crtDt;
	@Column(name = "crt_usr", nullable = false)
	private String crtUsr;
	@Column(name = "lst_upd_usr", nullable = true)
	private String lstUpdUsr;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_upd_dt", nullable = true)
	private Date lstUpdDt;
	
	public EhfmCmbHdr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmCmbHdr(String cmbHdrId, String cmbHdrName, String cmbAttrName,
			String langId, String locId, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.cmbHdrId = cmbHdrId;
		this.cmbHdrName = cmbHdrName;
		this.cmbAttrName = cmbAttrName;
		this.langId = langId;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}
	public String getCmbHdrId() {
		return cmbHdrId;
	}
	public void setCmbHdrId(String cmbHdrId) {
		this.cmbHdrId = cmbHdrId;
	}
	public String getCmbHdrName() {
		return cmbHdrName;
	}
	public void setCmbHdrName(String cmbHdrName) {
		this.cmbHdrName = cmbHdrName;
	}
	public String getCmbAttrName() {
		return cmbAttrName;
	}
	public void setCmbAttrName(String cmbAttrName) {
		this.cmbAttrName = cmbAttrName;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	
}
