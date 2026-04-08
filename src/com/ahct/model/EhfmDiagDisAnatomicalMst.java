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

@Entity
@Table(name = "EHFM_DIA_DISANATOMICAL_MST")
public class EhfmDiagDisAnatomicalMst implements Serializable {

	private EhfmDiagDisAnatomicalMstId id;
	private String disAnatomicalName;
	private String activeYn;
	private String langId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;

	public EhfmDiagDisAnatomicalMst() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "catCode", column = @Column(name = "cat_code", nullable = false, length = 100)),
			@AttributeOverride(name = "subCatCode", column = @Column(name = "sub_cat_code", nullable = false, length = 100)),
			@AttributeOverride(name = "diseaseCode", column = @Column(name = "disease_code", nullable = false, length = 100)),
			@AttributeOverride(name = "disAnatomicalCode", column = @Column(name = "dis_anatomical_code", nullable = false, length = 100)) })
	public EhfmDiagDisAnatomicalMstId getId() {
		return id;
	}

	public void setId(EhfmDiagDisAnatomicalMstId id) {
		this.id = id;
	}

	@Column(name = "dis_anatomical_name")
	public String getDisAnatomicalName() {
		return disAnatomicalName;
	}

	public void setDisAnatomicalName(String disAnatomicalName) {
		this.disAnatomicalName = disAnatomicalName;
	}

	@Column(columnDefinition = "char", name = "active_yn")
	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	@Column(name = "lang_id")
	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Column(name = "crt_usr")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cr_dt")
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "lst_upd_usr")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_upd_dt")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public EhfmDiagDisAnatomicalMst(EhfmDiagDisAnatomicalMstId id,
			String disAnatomicalName, String activeYn, String langId,
			String crtUsr, Date crtDt, String lstUpdUsr, Date lstUpdDt) {
		super();
		this.id = id;
		this.disAnatomicalName = disAnatomicalName;
		this.activeYn = activeYn;
		this.langId = langId;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}

}
