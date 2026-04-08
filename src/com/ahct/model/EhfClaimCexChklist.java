package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
(name = "EHF_CLAIM_CEX_CHKLST")
public class EhfClaimCexChklist implements java.io.Serializable{

	private String caseId;
	private String nameYn;
	private String genderYn;
	private String cardOnbedYn;
	private Date sheetAdmDate;
	private Date sheetSurgthrDate;
	private Date sheetDisdeathDate;
	private String admDateYn;
	private String surgthrDateYn;
	private String disDeathDateYn;
	private String patSignYn;
	private String patSatLetterYn;
	private String mandRprtYn;
	private String rprtSignYn;
	private String datePatnameYn;
	private String cexRemarks;
	 private String crtUsr;
	    private Date crtDt;
	    private String lstUpdUsr;
	    private Date lstUpdDt;
	
	    @Id
	    @Column(name="case_id",nullable= false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	  @Column(name="name_yn")
	public String getNameYn() {
		return nameYn;
	}
	public void setNameYn(String nameYn) {
		this.nameYn = nameYn;
	}
	  @Column(name="gender_yn")
	public String getGenderYn() {
		return genderYn;
	}
	public void setGenderYn(String genderYn) {
		this.genderYn = genderYn;
	}
	  @Column(name="card_onbed_yn")
	public String getCardOnbedYn() {
		return cardOnbedYn;
	}
	public void setCardOnbedYn(String cardOnbedYn) {
		this.cardOnbedYn = cardOnbedYn;
	}
	  @Column(name="sheet_adm_date")
	public Date getSheetAdmDate() {
		return sheetAdmDate;
	}
	public void setSheetAdmDate(Date sheetAdmDate) {
		this.sheetAdmDate = sheetAdmDate;
	}
	  @Column(name="sheet_surgthr_date")
	public Date getSheetSurgthrDate() {
		return sheetSurgthrDate;
	}
	public void setSheetSurgthrDate(Date sheetSurgthrDate) {
		this.sheetSurgthrDate = sheetSurgthrDate;
	}
	  @Column(name="sheet_disdeath_date")
	public Date getSheetDisdeathDate() {
		return sheetDisdeathDate;
	}
	public void setSheetDisdeathDate(Date sheetDisdeathDate) {
		this.sheetDisdeathDate = sheetDisdeathDate;
	}
	  @Column(name="adm_date_yn")
	public String getAdmDateYn() {
		return admDateYn;
	}
	public void setAdmDateYn(String admDateYn) {
		this.admDateYn = admDateYn;
	}
	  @Column(name="surgthr_date_yn")
	public String getSurgthrDateYn() {
		return surgthrDateYn;
	}
	public void setSurgthrDateYn(String surgthrDateYn) {
		this.surgthrDateYn = surgthrDateYn;
	}
	  @Column(name="disDeath_date_yn")
	public String getDisDeathDateYn() {
		return disDeathDateYn;
	}
	public void setDisDeathDateYn(String disDeathDateYn) {
		this.disDeathDateYn = disDeathDateYn;
	}
	  @Column(name="patSign_yn")
	public String getPatSignYn() {
		return patSignYn;
	}
	public void setPatSignYn(String patSignYn) {
		this.patSignYn = patSignYn;
	}
	  @Column(name="patSatLetter_yn")
	public String getPatSatLetterYn() {
		return patSatLetterYn;
	}
	public void setPatSatLetterYn(String patSatLetterYn) {
		this.patSatLetterYn = patSatLetterYn;
	}
	  @Column(name="mandRprt_yn")
	public String getMandRprtYn() {
		return mandRprtYn;
	}
	public void setMandRprtYn(String mandRprtYn) {
		this.mandRprtYn = mandRprtYn;
	}
	  @Column(name="rprtSign_yn")
	public String getRprtSignYn() {
		return rprtSignYn;
	}
	public void setRprtSignYn(String rprtSignYn) {
		this.rprtSignYn = rprtSignYn;
	}
	  @Column(name="date_patname_yn")
	public String getDatePatnameYn() {
		return datePatnameYn;
	}
	public void setDatePatnameYn(String datePatnameYn) {
		this.datePatnameYn = datePatnameYn;
	}
	  @Column(name="cex_remarks")
	public String getCexRemarks() {
		return cexRemarks;
	}
	public void setCexRemarks(String cexRemarks) {
		this.cexRemarks = cexRemarks;
	}
	  @Column(name="CRT_USR",length=30)
    public String getCrtUsr() {
	       return this.crtUsr;
	    }
	    
	    public void setCrtUsr(String crtUsr) {
	       this.crtUsr = crtUsr;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="CRT_DT")

	    public Date getCrtDt() {
	       return this.crtDt;
	    }
	    
	    public void setCrtDt(Date crtDt) {
	       this.crtDt = crtDt;
	    }
	    
	    @Column(name="LST_UPD_USR", length=30)

	    public String getLstUpdUsr() {
	       return this.lstUpdUsr;
	    }
	    
	    public void setLstUpdUsr(String lstUpdUsr) {
	       this.lstUpdUsr = lstUpdUsr;
	    }
	    @Temporal(TemporalType.DATE)
	    @Column(name="LST_UPD_DT")

	    public Date getLstUpdDt() {
	       return this.lstUpdDt;
	    }
	    
	    public void setLstUpdDt(Date lstUpdDt) {
	       this.lstUpdDt = lstUpdDt;
	    }
}
