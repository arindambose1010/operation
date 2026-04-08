package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ehfm_grps")
public class EhfmGrps implements Serializable {
	@Id
	@Column(name = "grp_id", nullable = false)
	private String grpId;
	@Column(name = "grp_name", nullable = false)
	private String grpName;
	@Column(name = "grp_short_name", nullable = false)
	private String grpShortName;
	@Column(name = "grp_desc", nullable = true)
	private String grpDesc;
	@Column(name = "lang_id", nullable = false)
	private String langId;
	@Column(name = "loc_id", nullable = false)
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

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getGrpShortName() {
		return grpShortName;
	}

	public void setGrpShortName(String grpShortName) {
		this.grpShortName = grpShortName;
	}

	public String getGrpDesc() {
		return grpDesc;
	}

	public void setGrpDesc(String grpDesc) {
		this.grpDesc = grpDesc;
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
