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
@Table(name = "EHFM_QUAL_MST")
public class EhfmQualMst implements Serializable {
	
	private String qualId;
	private String qual;
	private String docParamedFlg;
	private String activeYn;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String qualType;
	private String categoryType;
	
	@Id
    @Column(name="QUAL_ID", nullable = false,length = 100)
	public String getQualId() {
		return qualId;
	}
	public void setQualId(String qualId) {
		this.qualId = qualId;
	}
	 @Column(name="QUAL", nullable = false,length = 4000)
	public String getQual() {
		return qual;
	}
	public void setQual(String qual) {
		this.qual = qual;
	}
	 @Column(name="DOC_PARAMED_FLG", nullable = true,length = 10)
	public String getDocParamedFlg() {
		return docParamedFlg;
	}
	public void setDocParamedFlg(String docParamedFlg) {
		this.docParamedFlg = docParamedFlg;
	}
	 @Column(name="ACTIVE_YN", nullable = true,length = 10)
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	 @Column(name="CRT_USR", nullable = true,length = 100)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	 @Column(name="CRT_DT", nullable = true,length = 7)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	 @Column(name="LST_UPD_USR", nullable = true,length = 100)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	 @Column(name="LST_UPD_DT", nullable = true,length = 7)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	 @Column(name="QUAL_TYPE", nullable = true,length = 5)
	public String getQualType() {
		return qualType;
	}
	public void setQualType(String qualType) {
		this.qualType = qualType;
	}
	 @Column(name="CATEGORY_TYPE", nullable = true,length = 5)
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
  
}
