package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@SuppressWarnings("serial")
@Entity
@Table(name="EHFM_MOD")
public class EhfmMod implements java.io.Serializable {
	@Id
	@Column(name = "mod_id", nullable = false, length = 12)
	private String modId;
	@Column(name = "mod_name", nullable = false, length = 100)
	private String modName;
	@Column(name = "mod_parnt_id", length = 12)
    private String modParntId;
	@Column(name = "mod_desc")
	private String modDesc;
	@Column(name = "sub_url")
	private String subUrl;
	@Column(name = "mod_order")
	private Long modOrder;
	@Column(name = "active_yn")
	private String activeYn;
	@Column(name = "lang_id")
	private String langId;
    @Column(name = "LOC_ID", length = 5)
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
	public EhfmMod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EhfmMod(String modId, String modName, String modParntId,
			String modDesc, String subUrl, Long modOrder, String activeYn,
			String langId, String locId, Date crtDt, String crtUsr,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.modId = modId;
		this.modName = modName;
		this.modParntId = modParntId;
		this.modDesc = modDesc;
		this.subUrl = subUrl;
		this.modOrder = modOrder;
		this.activeYn = activeYn;
		this.langId = langId;
		this.locId = locId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getModParntId() {
		return modParntId;
	}
	public void setModParntId(String modParntId) {
		this.modParntId = modParntId;
	}
	public String getModDesc() {
		return modDesc;
	}
	public void setModDesc(String modDesc) {
		this.modDesc = modDesc;
	}
	public String getSubUrl() {
		return subUrl;
	}
	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}
	public Long getModOrder() {
		return modOrder;
	}
	public void setModOrder(Long modOrder) {
		this.modOrder = modOrder;
	}
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
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
