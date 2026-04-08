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
@Table(name = "ehfm_designation")
public class EhfmDesignation implements Serializable {

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "dsgnId", column = @Column(name = "dsgn_id", nullable = false, length = 12)),
			@AttributeOverride(name = "langId", column = @Column(name = "LANG_ID", nullable = false, length = 5)) })
	private EhfmDesignationId id;
	@Column(name = "dsgn_name", nullable = false)
	private String dsgnName;
	@Column(name = "dsgn_shrt_name", nullable = false)
	private String dsgnShortName;
	@Column(name = "lang_id", nullable = false)
	private String langId;
	@Column(name = "loc_id", nullable = false)
	private String locId;
	@Column(name = "dsgn_status", nullable = true)
	private String dsgnStatus;
	@Column(name = "dsgn_order", nullable = true)
	private String dsgnOrder;
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

	

	public EhfmDesignationId getId() {
		return id;
	}

	public void setId(EhfmDesignationId id) {
		this.id = id;
	}

	public String getDsgnName() {
		return dsgnName;
	}

	public void setDsgnName(String dsgnName) {
		this.dsgnName = dsgnName;
	}

	public String getDsgnShortName() {
		return dsgnShortName;
	}

	public void setDsgnShortName(String dsgnShortName) {
		this.dsgnShortName = dsgnShortName;
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

	public String getDsgnStatus() {
		return dsgnStatus;
	}

	public void setDsgnStatus(String dsgnStatus) {
		this.dsgnStatus = dsgnStatus;
	}

	public String getDsgnOrder() {
		return dsgnOrder;
	}

	public void setDsgnOrder(String dsgnOrder) {
		this.dsgnOrder = dsgnOrder;
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
