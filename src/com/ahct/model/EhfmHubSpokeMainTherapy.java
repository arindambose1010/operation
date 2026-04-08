package com.ahct.model;

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
@Table(name="EHFM_HUBSPOKE_MAIN_THERAPY")
public class EhfmHubSpokeMainTherapy implements java.io.Serializable {

	private EhfmHubSpokeMainTherapyId id;
	private String procName;
	private String icdCatCode;
	private String schemeId;
	private String medical_surg;
	private String postOpChk;
	private String hospstayAmt;
	private String commonCatAmt;
	private String icdAmt;
	private String langId;
	private char activeYN;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Integer noOfDays;
	private String govOrCorp;
	
	@Column(name="no_of_days")
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="hubHospId", column=@Column(name="HOSP_ID", nullable=false)),
	   @AttributeOverride(name="asriCode", column=@Column(name="ASRI_CODE", nullable=false) ), 
	   @AttributeOverride(name="icdProcCode", column=@Column(name="ICD_PROC_CODE", nullable=false) ),
	   @AttributeOverride(name="state", column=@Column(name="STATE", nullable=false)) ,
	   @AttributeOverride(name="process", column=@Column(name="PROCESS", nullable=false))
	} )
	
	public EhfmHubSpokeMainTherapyId getId() {
		return id;
	}
	public void setId(EhfmHubSpokeMainTherapyId id) {
		this.id = id;
	}
	@Column(name="proc_name")
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	@Column(name="icd_cat_code")
	public String getIcdCatCode() {
		return icdCatCode;
	}
	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}
	@Column(name="scheme_id")
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	@Column(name="medical_surg")
	public String getMedical_surg() {
		return medical_surg;
	}
	public void setMedical_surg(String medical_surg) {
		this.medical_surg = medical_surg;
	}
	@Column(name="postopchk")
	public String getPostOpChk() {
		return postOpChk;
	}
	public void setPostOpChk(String postOpChk) {
		this.postOpChk = postOpChk;
	}
	@Column(name="hosp_stay_amt")
	public String getHospstayAmt() {
		return hospstayAmt;
	}
	public void setHospstayAmt(String hospstayAmt) {
		this.hospstayAmt = hospstayAmt;
	}
	@Column(name="common_cat_amt")
	public String getCommonCatAmt() {
		return commonCatAmt;
	}
	public void setCommonCatAmt(String commonCatAmt) {
		this.commonCatAmt = commonCatAmt;
	}
	@Column(name="icd_amt")
	public String getIcdAmt() {
		return icdAmt;
	}
	public void setIcdAmt(String icdAmt) {
		this.icdAmt = icdAmt;
	}
	@Column(name="lang_id" , nullable=true)
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="active_yn" , nullable=true)
	public char getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(char activeYN) {
		this.activeYN = activeYN;
	}
	
	@Column(name="crt_usr" , nullable=true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cr_dt" , nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="lst_upd_usr" , nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lst_upd_dt" , nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="gov_or_corp")
	public String getGovOrCorp() {
		return govOrCorp;
	}
	public void setGovOrCorp(String govOrCorp) {
		this.govOrCorp = govOrCorp;
	}

	public EhfmHubSpokeMainTherapy() {
		super();
	}
	
	
	public EhfmHubSpokeMainTherapy(EhfmHubSpokeMainTherapyId id,String procName,
			String icdCatCode, String schemeId, String medical_surg,
			String postOpChk, String hospstayAmt, String commonCatAmt,
			String icdAmt, String langId, char activeYN, String crtUsr,
			Date crtDt, String lstUpdUsr, Date lstUpdDt, Integer noOfDays,
			String govOrCorp) {
		super();
		this.id = id;
		this.procName = procName;
		this.icdCatCode = icdCatCode;
		this.schemeId = schemeId;
		this.medical_surg = medical_surg;
		this.postOpChk = postOpChk;
		this.hospstayAmt = hospstayAmt;
		this.commonCatAmt = commonCatAmt;
		this.icdAmt = icdAmt;
		this.langId = langId;
		this.activeYN = activeYN;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.noOfDays = noOfDays;
		this.govOrCorp = govOrCorp;
	}
	
}
