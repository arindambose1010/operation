package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_BANK_ACCOUNT_DTLS")
public class EhfmBankAccountDtls implements java.io.Serializable{

	private String accountNumber;
	private String accountType;
	private Double openBalAmount;
	private Double outStandingAmt;
	private Double processAmount;
	private String stateFlag;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private Double prevBalanceAmt;
	private Double prevOutAmt;
	private Date ceoRegClmProcessDt;
	private Date ceoDenClmProcessDt;
	private Date ceoCochProcessDt;
	private Date ceoFlupProcessDt;	
	private Date ceoPdpProcessDt;				
	private Date ceoVpProcessDt;
	private Date ceoSpProcessDt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_REGCLM_PROCESS_DT", nullable=true)
	public Date getCeoRegClmProcessDt() {
		return ceoRegClmProcessDt;
	}
	public void setCeoRegClmProcessDt(Date ceoRegClmProcessDt) {
		this.ceoRegClmProcessDt = ceoRegClmProcessDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_DENCLM_PROCESS_DT", nullable=true)
	public Date getCeoDenClmProcessDt() {
		return ceoDenClmProcessDt;
	}
	public void setCeoDenClmProcessDt(Date ceoDenClmProcessDt) {
		this.ceoDenClmProcessDt = ceoDenClmProcessDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_COCH_PROCESS_DT", nullable=true)
	public Date getCeoCochProcessDt() {
		return ceoCochProcessDt;
	}
	public void setCeoCochProcessDt(Date ceoCochProcessDt) {
		this.ceoCochProcessDt = ceoCochProcessDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_FLUP_PROCESS_DT", nullable=true)
	public Date getCeoFlupProcessDt() {
		return ceoFlupProcessDt;
	}
	public void setCeoFlupProcessDt(Date ceoFlupProcessDt) {
		this.ceoFlupProcessDt = ceoFlupProcessDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_PDP_PROCESS_DT", nullable=true)
	public Date getCeoPdpProcessDt() {
		return ceoPdpProcessDt;
	}
	public void setCeoPdpProcessDt(Date ceoPdpProcessDt) {
		this.ceoPdpProcessDt = ceoPdpProcessDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_VP_PROCESS_DT", nullable=true)
	public Date getCeoVpProcessDt() {
		return ceoVpProcessDt;
	}
	public void setCeoVpProcessDt(Date ceoVpProcessDt) {
		this.ceoVpProcessDt = ceoVpProcessDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CEO_SP_PROCESS_DT", nullable=true)
	public Date getCeoSpProcessDt() {
		return ceoSpProcessDt;
	}
	public void setCeoSpProcessDt(Date ceoSpProcessDt) {
		this.ceoSpProcessDt = ceoSpProcessDt;
	}
	@Id
	@Column(name="account_number", nullable=false)
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Column(name="Account_type", nullable=true)
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	

	@Column(name="open_bal_amount", nullable=true)
	public Double getOpenBalAmount() {
		return openBalAmount;
	}
	public void setOpenBalAmount(Double openBalAmount) {
		this.openBalAmount = openBalAmount;
	}
	
	
	@Column(name="out_standing_amt", nullable=true)
	public Double getOutStandingAmt() {
		return outStandingAmt;
	}
	public void setOutStandingAmt(Double outStandingAmt) {
		this.outStandingAmt = outStandingAmt;
	}
	
	@Column(name="process_amount", nullable=true)
	public Double getProcessAmount() {
		return processAmount;
	}
	public void setProcessAmount(Double processAmount) {
		this.processAmount = processAmount;
	}
	
	@Column(name="State_flag", nullable=true)
	public String getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="crt_dt", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name="crt_usr", nullable=true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lst_upd_dt", nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="lst_upd_usr", nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	

	@Column(name="prev_balance_amt", nullable=true)
	public Double getPrevBalanceAmt() {
		return prevBalanceAmt;
	}
	public void setPrevBalanceAmt(Double prevBalanceAmt) {
		this.prevBalanceAmt = prevBalanceAmt;
	}
	
	@Column(name="prev_out_amt", nullable=true)
	public Double getPrevOutAmt() {
		return prevOutAmt;
	}
	public void setPrevOutAmt(Double prevOutAmt) {
		this.prevOutAmt = prevOutAmt;
	}
	
	
}
