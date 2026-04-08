package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="ehf_flag_dtls")
public class EhfFlagDtls implements java.io.Serializable
{
	 String flagId;		
	 String caseId;		
	 String natureOfFlag; 
	 String currentStatusOfFlag;
	 long amount;
	 String locId;				
	 String langId;
	 String crtUsr;
	 Date crtDt;
	 String lstUpdUsr;
	 Date lstUpdDt;
	 String gmOpFlag; 
	 long amountRef; 
	 

	@Id
	@Column(name="FLAG_ID", nullable = false)
	public String getFlagId() {
		return flagId;
	}
	public void setFlagId(String flagId) {
		this.flagId = flagId;
	}
	
	@Column(name="CASE_ID", nullable = false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@Column(name="NATURE_OF_FLAG",nullable = false)
	public String getNatureOfFlag() {
		return natureOfFlag;
	}
	public void setNatureOfFlag(String natureOfFlag) {
		this.natureOfFlag = natureOfFlag;
	}
	
	@Column(name="CURRENT_STATUS_OF_FLAG",nullable = false)
	public String getCurrentStatusOfFlag() {
		return currentStatusOfFlag;
	}
	public void setCurrentStatusOfFlag(String currentStatusOfFlag) {
		this.currentStatusOfFlag = currentStatusOfFlag;
	}

	@Column(name="AMOUNT")
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	@Column(name="LOC_ID ")
	public String getLocId() {
		return locId;
	}
	
	public void setLocId(String locId) {
		this.locId = locId;
	}
	
	@Column(name="LANG_ID", nullable = false)
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="GM_OP_FLAG")
	public String getGmOpFlag() {
		return gmOpFlag;
	}
	public void setGmOpFlag(String gmOpFlag) {
		this.gmOpFlag = gmOpFlag;
	} 
	
	@Column(name="AMOUNT_REF")
    public long getAmountRef() {
		return amountRef;
	}
	public void setAmountRef(long amountRef) {
		this.amountRef = amountRef;
	}
	
	public EhfFlagDtls() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EhfFlagDtls(String flagId,String caseId, String natureOfFlag,
			String currentStatusOfFlag,long amount,String locId,String langId,
			Date crtDt, String crtUsr,String lstUpdUsr,Date lstUpdDt,String gmOpFlag,
			long amountRef) {
		super();
	
		this.flagId = flagId;
		this.caseId = caseId;
		this.natureOfFlag = natureOfFlag;
		this.currentStatusOfFlag = currentStatusOfFlag;
		this.amount=amount;
		this.locId = locId;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUsr = lstUpdUsr;
		this.lstUpdDt = lstUpdDt;
		this.gmOpFlag=gmOpFlag;
		this.amountRef=amountRef;
		
	}
			
}
