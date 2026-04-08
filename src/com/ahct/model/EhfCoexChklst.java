package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_COEX_CHKLST database table.
 * 
 */
@Entity
@Table(name="EHF_COEX_CHKLST")
public class EhfCoexChklst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfCoexChklstPK id;
	private String acquaintanceAttached;
	private String acquaintanceRemarks;
	private String cexRemarks;
	private Date crtDt;
	private String crtUsr;
	private String datePatnameYn;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String mandrprtYn;
	private String patsatletterYn;
	private String patsignYn;
	private String photoMatching;
	private String photoRemarks;
	private String photoYn;
	private String photomatchRemarks;
	private String reportsAttached;
	private String reportsRemarks;
	private String rprtsignYn;

    public EhfCoexChklst() {
    }


	@EmbeddedId
	public EhfCoexChklstPK getId() {
		return this.id;
	}

	public void setId(EhfCoexChklstPK id) {
		this.id = id;
	}
	

	@Column(name="ACQUAINTANCE_ATTACHED")
	public String getAcquaintanceAttached() {
		return this.acquaintanceAttached;
	}

	public void setAcquaintanceAttached(String acquaintanceAttached) {
		this.acquaintanceAttached = acquaintanceAttached;
	}


	@Column(name="ACQUAINTANCE_REMARKS")
	public String getAcquaintanceRemarks() {
		return this.acquaintanceRemarks;
	}

	public void setAcquaintanceRemarks(String acquaintanceRemarks) {
		this.acquaintanceRemarks = acquaintanceRemarks;
	}


	@Column(name="CEX_REMARKS")
	public String getCexRemarks() {
		return this.cexRemarks;
	}

	public void setCexRemarks(String cexRemarks) {
		this.cexRemarks = cexRemarks;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	@Column(name="DATE_PATNAME_YN")
	public String getDatePatnameYn() {
		return this.datePatnameYn;
	}

	public void setDatePatnameYn(String datePatnameYn) {
		this.datePatnameYn = datePatnameYn;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="MANDRPRT_YN")
	public String getMandrprtYn() {
		return this.mandrprtYn;
	}

	public void setMandrprtYn(String mandrprtYn) {
		this.mandrprtYn = mandrprtYn;
	}


	@Column(name="PATSATLETTER_YN")
	public String getPatsatletterYn() {
		return this.patsatletterYn;
	}

	public void setPatsatletterYn(String patsatletterYn) {
		this.patsatletterYn = patsatletterYn;
	}


	@Column(name="PATSIGN_YN")
	public String getPatsignYn() {
		return this.patsignYn;
	}

	public void setPatsignYn(String patsignYn) {
		this.patsignYn = patsignYn;
	}


	@Column(name="PHOTO_MATCHING")
	public String getPhotoMatching() {
		return this.photoMatching;
	}

	public void setPhotoMatching(String photoMatching) {
		this.photoMatching = photoMatching;
	}


	@Column(name="PHOTO_REMARKS")
	public String getPhotoRemarks() {
		return this.photoRemarks;
	}

	public void setPhotoRemarks(String photoRemarks) {
		this.photoRemarks = photoRemarks;
	}


	@Column(name="PHOTO_YN")
	public String getPhotoYn() {
		return this.photoYn;
	}

	public void setPhotoYn(String photoYn) {
		this.photoYn = photoYn;
	}


	@Column(name="PHOTOMATCH_REMARKS")
	public String getPhotomatchRemarks() {
		return this.photomatchRemarks;
	}

	public void setPhotomatchRemarks(String photomatchRemarks) {
		this.photomatchRemarks = photomatchRemarks;
	}


	@Column(name="REPORTS_ATTACHED")
	public String getReportsAttached() {
		return this.reportsAttached;
	}

	public void setReportsAttached(String reportsAttached) {
		this.reportsAttached = reportsAttached;
	}


	@Column(name="REPORTS_REMARKS")
	public String getReportsRemarks() {
		return this.reportsRemarks;
	}

	public void setReportsRemarks(String reportsRemarks) {
		this.reportsRemarks = reportsRemarks;
	}


	@Column(name="RPRTSIGN_YN")
	public String getRprtsignYn() {
		return this.rprtsignYn;
	}

	public void setRprtsignYn(String rprtsignYn) {
		this.rprtsignYn = rprtsignYn;
	}

}