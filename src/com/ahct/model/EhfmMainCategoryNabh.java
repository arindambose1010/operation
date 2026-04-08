package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EHFM_MAIN_CATEGORY_NABH")
public class EhfmMainCategoryNabh implements java.io.Serializable{
	
	private EhfmMainCategoryNabhId id;
    private String icdCatName;
	
	private String langId;
	private char activeYN;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="asriCatCode", column=@Column(name="ASRI_CAT_CODE", nullable=false) ), 
	   @AttributeOverride(name="icdCatCode", column=@Column(name="ICD_CAT_CODE", nullable=false) )
	
	} )
	public EhfmMainCategoryNabhId getId() {
		return id;
	}

	public void setId(EhfmMainCategoryNabhId id) {
		this.id = id;
	}
	@Column(name="icd_cat_name", nullable=false)
	public String getIcdCatName() {
		return icdCatName;
	}

	public void setIcdCatName(String icdCatName) {
		this.icdCatName = icdCatName;
	}

	@Column(name="lang_id", nullable=false)
	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="active_yn", nullable=true)
	public char getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(char activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name="crt_usr", nullable=false)
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Column(name="cr_dt", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="lst_upd_usr", nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Column(name="lst_upd_dt", nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	public EhfmMainCategoryNabh() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
