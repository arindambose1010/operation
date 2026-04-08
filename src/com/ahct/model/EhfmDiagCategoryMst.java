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
@Table(name = "EHFM_DIA_CATEGORY_MST")
public class EhfmDiagCategoryMst implements Serializable {

	private EhfmDiagCategoryMstId id;
	private String catName;
	private String activeYn;
	private String langId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	public EhfmDiagCategoryMst()
	{}
	
	public EhfmDiagCategoryMst(EhfmDiagCategoryMstId id, String catName,
			String activeYn, String langId, String crtUsr, Date crtDt,
			String lstUpdUsr, Date lstUpdDt) {
		super();
		this.id = id;
		this.catName = catName;
		this.activeYn = activeYn;
		this.langId = langId;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
	}
	
	@EmbeddedId
	@AttributeOverrides( {
	   @AttributeOverride(name="catCode", column=@Column(name="cat_code", nullable=false) ), 
	   @AttributeOverride(name="mainCatCode", column=@Column(name="main_cat_code", nullable=false) )
	   } )
	public EhfmDiagCategoryMstId getId() {
		return id;
	}
	public void setId(EhfmDiagCategoryMstId id) {
		this.id = id;
	}
	@Column(name="cat_name")
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	@Column(columnDefinition="char",name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="lang_id")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="crt_usr")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cr_dt")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="lst_upd_usr")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lst_upd_dt")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	

}
