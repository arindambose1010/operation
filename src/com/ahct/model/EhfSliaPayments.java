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
@Table(name="EHF_SLIA_PAYMENTS")
public class EhfSliaPayments implements Serializable {

	private static final long serialVersionUID = 1L;
	private String transId;
	private String status;
	private String paymentType;
	private String uniqueId;
	private String fileName;
	private String fileFlag;
	private String beneficiaryId;
	private String beneficiaryAddress;
	private String beneficiaryAccountName;
	private String beneficiaryAccountNo;
	private String beneficiaryBankId;
	private String beneficiaryBankIfsc;
	private String beneficiaryBankName;
	private String bankBranch;
	private Double transactionAmount;
	private String paymentMode;
	private String emailId;
	private Long rejectCount;
	private Date crtDate;
	private Date lstUpdDt;
	private Date fileDate;
	private String clientAccountNo;
	private String clientIfsc;
	
	
	
	@Id
	@Column(name = "TRANS_ID", nullable = false)
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "PAYMENT_TYPE")
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	@Column(name = "UNIQUE_ID")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "FILE_FLAG")
	public String getFileFlag() {
		return fileFlag;
	}
	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}
	
	@Column(name = "BENEFICIARY_ID")
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	
	@Column(name = "BENEFICIARY_ADDR")
	public String getBeneficiaryAddress() {
		return beneficiaryAddress;
	}
	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}
	
	@Column(name = "BENEFICIARY_ACCOUNT_NAME")
	public String getBeneficiaryAccountName() {
		return beneficiaryAccountName;
	}
	public void setBeneficiaryAccountName(String beneficiaryAccountName) {
		this.beneficiaryAccountName = beneficiaryAccountName;
	}
	
	@Column(name = "BENEFICIARY_ACCOUNT_NO")
	public String getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}
	public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}
	
	@Column(name = "BENEFICIARY_BANK_ID")
	public String getBeneficiaryBankId() {
		return beneficiaryBankId;
	}
	public void setBeneficiaryBankId(String beneficiaryBankId) {
		this.beneficiaryBankId = beneficiaryBankId;
	}
	
	@Column(name = "BENEFICIARY_BANK_IFC_CODE")
	public String getBeneficiaryBankIfsc() {
		return beneficiaryBankIfsc;
	}
	public void setBeneficiaryBankIfsc(String beneficiaryBankIfsc) {
		this.beneficiaryBankIfsc = beneficiaryBankIfsc;
	}
	
	@Column(name = "BENEFICIARY_BANK_NAME")
	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}
	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}
	
	@Column(name = "BANK_BRANCH")
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	
	@Column(name = "TRANSACTION_AMOUNT")
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
	
	@Column(name = "PAYMENT_MODE")
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	@Column(name = "EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name = "REJ_COUNT")
	public Long getRejectCount() {
		return rejectCount;
	}
	public void setRejectCount(Long rejectCount) {
		this.rejectCount = rejectCount;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name = "FILE_DATE")
	public Date getFileDate() {
		return fileDate;
	}
	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(name = "CLIENT_AC_NUMBER")
	public String getClientAccountNo() {
		return clientAccountNo;
	}
	public void setClientAccountNo(String clientAccountNo) {
		this.clientAccountNo = clientAccountNo;
	}
	
	
	@Column(name = "CLIENT_BANK_IFC_CODE")
	public String getClientIfsc() {
		return clientIfsc;
	}
	public void setClientIfsc(String clientIfsc) {
		this.clientIfsc = clientIfsc;
	}
	
	
	
	
	
}
