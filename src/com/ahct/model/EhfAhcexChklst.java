package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_AHCEX_CHKLST database table.
 * 
 */
@Entity
@Table(name="EHF_AHCEX_CHKLST")
public class EhfAhcexChklst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AHC_ID")
	private String ahcId;

	@Column(name="ACQUAINTANCE_ATTACHED")
	private String acquaintanceAttached;

	@Column(name="ACQUAINTANCE_REMARKS")
	private String acquaintanceRemarks;

	@Column(name="CEX_REMARKS")
	private String cexRemarks;

    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	
	@Column(name="PHOTO_MATCHING")
	private String photoMatching;

	@Column(name="PHOTO_REMARKS")
	private String photoRemarks;

	@Column(name="PHOTO_YN")
	private String photoYn;

	@Column(name="PHOTOMATCH_REMARKS")
	private String photomatchRemarks;

	@Column(name="REPORTS_ATTACHED")
	private String reportsAttached;

	@Column(name="REPORTS_REMARKS")
	private String reportsRemarks;

	

    public EhfAhcexChklst() {
    }

	public String getAhcId() {
		return this.ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}

	public String getAcquaintanceAttached() {
		return this.acquaintanceAttached;
	}

	public void setAcquaintanceAttached(String acquaintanceAttached) {
		this.acquaintanceAttached = acquaintanceAttached;
	}

	public String getAcquaintanceRemarks() {
		return this.acquaintanceRemarks;
	}

	public void setAcquaintanceRemarks(String acquaintanceRemarks) {
		this.acquaintanceRemarks = acquaintanceRemarks;
	}

	public String getCexRemarks() {
		return this.cexRemarks;
	}

	public void setCexRemarks(String cexRemarks) {
		this.cexRemarks = cexRemarks;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	

	public String getPhotoMatching() {
		return this.photoMatching;
	}

	public void setPhotoMatching(String photoMatching) {
		this.photoMatching = photoMatching;
	}

	public String getPhotoRemarks() {
		return this.photoRemarks;
	}

	public void setPhotoRemarks(String photoRemarks) {
		this.photoRemarks = photoRemarks;
	}

	public String getPhotoYn() {
		return this.photoYn;
	}

	public void setPhotoYn(String photoYn) {
		this.photoYn = photoYn;
	}

	public String getPhotomatchRemarks() {
		return this.photomatchRemarks;
	}

	public void setPhotomatchRemarks(String photomatchRemarks) {
		this.photomatchRemarks = photomatchRemarks;
	}

	public String getReportsAttached() {
		return this.reportsAttached;
	}

	public void setReportsAttached(String reportsAttached) {
		this.reportsAttached = reportsAttached;
	}

	public String getReportsRemarks() {
		return this.reportsRemarks;
	}

	public void setReportsRemarks(String reportsRemarks) {
		this.reportsRemarks = reportsRemarks;
	}

	
}