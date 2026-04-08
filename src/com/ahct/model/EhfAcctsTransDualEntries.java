package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_ACCTS_TRANS_DUAL_ENTRIES")
public class EhfAcctsTransDualEntries implements Serializable
{
	 private String dualEntryId;
	 private String voucherId;
	 private String transId;
	 private String accountCode;
	 private String accountName;
	 private String debtAmount;
	 private String creditAmount;
	 private String closingBalance;
	 private Date transDate;
	 private String narration;
	 private String paymentType;
	 private String ledgerCode;
	 private String debtOrCreditType;
	 private String paymentMode;
	 private String voucherType;
	 private String paymentModeDtl;
	 private String scheme;
	 
	 
	 
	 
	
	
	@Id
	@Column(name = "DUALENTRY_ID", nullable = false, length = 30)
	public String getDualEntryId() {
		return dualEntryId;
	}
	public void setDualEntryId(String dualEntryId) {
		this.dualEntryId = dualEntryId;
	}
	
	@Column(name = "VOUCHER_ID", nullable = true, length = 30)
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	
	@Column(name = "TRANS_ID", nullable = true, length = 30)
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	@Column(name = "ACCOUNT_CODE", nullable = true, length = 30)
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	@Column(name = "ACCOUNT_NAME", nullable = true, length = 100)
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Column(name = "DEBT_AMOUNT", nullable = true, length = 30)
	public String getDebtAmount() {
		return debtAmount;
	}
	public void setDebtAmount(String debtAmount) {
		this.debtAmount = debtAmount;
	}
	
	@Column(name = "CREDIT_AMOUNT", nullable = true, length = 30)
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	
	@Column(name = "CLOSING_BALANCE", nullable = true, length = 30)
	public String getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(String closingBalance) {
		this.closingBalance = closingBalance;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRANS_DATE", nullable = true)
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
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
	
	@Column(name = "LEDGER_CODE", nullable = true, length = 30)
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	 
	@Column(name = "DEBT_OR_CREDIT_TYPE", nullable = true, length = 30)
	public String getDebtOrCreditType() {
		return debtOrCreditType;
	}
	public void setDebtOrCreditType(String debtOrCreditType) {
	    this.debtOrCreditType = debtOrCreditType;
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
	
	@Column(name = "SCHEME", nullable = true, length = 100)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}
