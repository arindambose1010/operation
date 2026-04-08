package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_ACCTS_TRANSACTION_DTLS")
public class EhfAcctsTransactionDtls implements Serializable
{
  private String voucherId;
  private String transId ;
  private String debtAccount;
  private String creditAccount;
  private Float amount;
  private String narration;
  private String paymentType;
  private Date transDate;
  private String paymentMode;
  private String voucherType;
  private String paymentModeDtl;
  private String transactionDtl;
  private String scheme;
  private String caseId;
  private String uniqueTxn;
  private String accountType;
  private String accountNature;
  private String section;
  private Date crtDt;
  private String crtUsr;
  private Date lstUpdDt;
  private String lstUpdUsr;
  private Date chqOrDDDate;
  private String procNo;
  private Date procDate;
  private String active_yn;
  
@Id
@Column(name = "VOUCHER_ID", nullable = false, length = 30)
public String getVoucherId() {
	return voucherId;
}
public void setVoucherId(String voucherId) {
	this.voucherId = voucherId;
}

@Column(name = "TRANS_ID", nullable = false, length = 30)
public String getTransId() {
	return transId;
}
public void setTransId(String transId) {
	this.transId = transId;
}

@Column(name = "DEBT_ACCOUNT", nullable = false, length = 30)
public String getDebtAccount() {
	return debtAccount;
}
public void setDebtAccount(String debtAccount) {
	this.debtAccount = debtAccount;
}

@Column(name = "CREDIT_ACCOUNT", nullable = false, length = 30)
public String getCreditAccount() {
	return creditAccount;
}
public void setCreditAccount(String creditAccount) {
	this.creditAccount = creditAccount;
}

@Column(name = "AMOUNT", nullable = false, length = 20)
public Float getAmount() {
	return amount;
}
public void setAmount(Float amount) {
	this.amount = amount;
}

@Column(name = "NARRATION", nullable = true, length = 1000)
public String getNarration() {
	return narration;
}
public void setNarration(String narration) {
	this.narration = narration;
}

@Column(name = "PAYMENT_TYPE", nullable = true, length = 30)
public String getPaymentType() {
	return paymentType;
}
public void setPaymentType(String paymentType) {
	this.paymentType = paymentType;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name="TRANS_DATE", nullable = false)
public Date getTransDate() {
	return transDate;
}
public void setTransDate(Date transDate) {
	this.transDate = transDate;
}

@Column(name = "PAYMENT_MODE", nullable = true, length = 30)
public String getPaymentMode() {
	return paymentMode;
}
public void setPaymentMode(String paymentMode) {
	this.paymentMode = paymentMode;
}

@Column(name = "VOUCHER_TYPE", nullable = true, length = 30)
public String getVoucherType() {
	return voucherType;
}
public void setVoucherType(String voucherType) {
	this.voucherType = voucherType;
}

@Column(name = "PAYMENT_MODE_IN_DTL", nullable = true, length = 100)
public String getPaymentModeDtl() {
	return paymentModeDtl;
}
public void setPaymentModeDtl(String paymentModeDtl) {
	this.paymentModeDtl = paymentModeDtl;
}
@Column(name = "TRANS_TYPE_IN_DTL", nullable = true, length = 100)
public String getTransactionDtl() {
	return transactionDtl;
}
public void setTransactionDtl(String transactionDtl) {
	this.transactionDtl = transactionDtl;
}
@Column(name = "SCHEME", nullable = true, length = 100)
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}
@Column(name = "CASE_ID", nullable = true, length = 50)
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
@Column(name = "UNIQUE_TXN", nullable = true, length = 30)
public String getUniqueTxn() {
	return uniqueTxn;
}
public void setUniqueTxn(String uniqueTxn) {
	this.uniqueTxn = uniqueTxn;
}

@Column(name = "ACCOUNT_TYPE", nullable = true, length = 20)
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
@Column(name = "ACCOUNT_NATURE", nullable = true, length = 20)
public String getAccountNature() {
	return accountNature;
}
public void setAccountNature(String accountNature) {
	this.accountNature = accountNature;
}
@Column(name = "SECTION", nullable = true, length = 20)
public String getSection() {
	return section;
}
public void setSection(String section) {
	this.section = section;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DT", nullable = false)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

@Column(name = "CRT_USR", nullable = false, length = 20)
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name="lst_upd_dt", nullable = true)
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
@Column(name = "LST_UPD_USR", nullable = true, length = 20)
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name="CHQ_DD_DT", nullable = true)
public Date getChqOrDDDate() {
	return chqOrDDDate;
}
public void setChqOrDDDate(Date chqOrDDDate) {
	this.chqOrDDDate = chqOrDDDate;
}

@Column(name = "PROC_NO", nullable = true, length = 20)
public String getProcNo() {
	return procNo;
}
public void setProcNo(String procNo) {
	this.procNo = procNo;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name="PROC_DATE", nullable = true)
public Date getProcDate() {
	return procDate;
}
public void setProcDate(Date procDate) {
	this.procDate = procDate;
}
@Column(name = "active_y_n")
public String getActive_yn() {
	return active_yn;
}
public void setActive_yn(String active_yn) {
	this.active_yn = active_yn;
}
  

}
