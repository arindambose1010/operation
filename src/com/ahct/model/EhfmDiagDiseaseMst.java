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
@Table(name = "EHFM_DIA_DISEASE_MST")
public class EhfmDiagDiseaseMst implements Serializable {

	private EhfmDiagDiseaseMstId id;
	private String diseaseName;
	private String activeYn;
	private String langId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;

	public EhfmDiagDiseaseMst() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "catCode", column = @Column(name = "cat_code", nullable = false, length = 100)),
			@AttributeOverride(name = "subCatCode", column = @Column(name = "sub_cat_code", nullable = false, length = 100)),
			@AttributeOverride(name = "diseaseCode", column = @Column(name = "disease_code", nullable = false, length = 100))

	})
	public EhfmDiagDiseaseMstId getId() {
		return id;
	}

	public void setId(EhfmDiagDiseaseMstId id) {
		this.id = id;
	}

	@Column(name = "disease_name")
	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
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

}
