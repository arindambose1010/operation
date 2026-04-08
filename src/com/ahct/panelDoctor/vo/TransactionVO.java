package com.ahct.panelDoctor.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TransactionVO implements Serializable{

	private String voucherId;
	private String transactionId;
	private String debtAccount;
	private String creditAccount;
	private String amount;
	private String transactionType;
	private String paymentMode;
	private String voucherType;
	private String paymentDetailedInfo;
	private String transDate;
	private String narration;
	private String crtDate;
	private String crtUser;
	private String result;
	private String detailedTransType;
	private String flag;
	private String scheme;
	private String adminNo;
	private String adminDate;
	private String chequeOrDDNo;
	private String chequeOrDDDate;
	private String section;
	private String uniqueTxn;
	//For PanelDoctor Payments
	private String tdsId;
	private String caseSetId;
	private String grossAmount;
	private String netAmount;
	private String tdsAmount;
	private String tdsType;
	private String docId;
	private String activeYn;
	
	
	
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getUniqueTxn() {
		return uniqueTxn;
	}
	public void setUniqueTxn(String uniqueTxn) {
		this.uniqueTxn = uniqueTxn;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getChequeOrDDNo() {
		return chequeOrDDNo;
	}
	public void setChequeOrDDNo(String chequeOrDDNo) {
		this.chequeOrDDNo = chequeOrDDNo;
	}
	public String getChequeOrDDDate() {
		return chequeOrDDDate;
	}
	public void setChequeOrDDDate(String chequeOrDDDate) {
		this.chequeOrDDDate = chequeOrDDDate;
	}
	
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(String adminDate) {
		this.adminDate = adminDate;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDetailedTransType() {
		return detailedTransType;
	}
	public void setDetailedTransType(String detailedTransType) {
		this.detailedTransType = detailedTransType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getDebtAccount() {
		return debtAccount;
	}
	public void setDebtAccount(String debtAccount) {
		this.debtAccount = debtAccount;
	}
	public String getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getPaymentDetailedInfo() {
		return paymentDetailedInfo;
	}
	public void setPaymentDetailedInfo(String paymentDetailedInfo) {
		this.paymentDetailedInfo = paymentDetailedInfo;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getCaseSetId() {
		return caseSetId;
	}
	public void setCaseSetId(String caseSetId) {
		this.caseSetId = caseSetId;
	}
	
	public String getTdsId() {
		return tdsId;
	}
	public void setTdsId(String tdsId) {
		this.tdsId = tdsId;
	}
	
	public String getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	public String getTdsAmount() {
		return tdsAmount;
	}
	public void setTdsAmount(String tdsAmount) {
		this.tdsAmount = tdsAmount;
	}
	public String getTdsType() {
		return tdsType;
	}
	public void setTdsType(String tdsType) {
		this.tdsType = tdsType;
	}
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
}
