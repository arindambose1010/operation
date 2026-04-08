package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehf_claim_trust_payment")

public class EhfClaimTrustPayment implements Serializable {

	private String trustPaymentId;
	private String caseId;
	private String paymentStatus;
	private Double claimAmount;
	private String transactionId;
	private Date transactionDt;
	private String transStatus;
	private Double timeMilSec;
	private String remarks;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String paymentCheck;
	private String tobePaid;
	private Date sbhPaidDate;
	private String caseFollowupId;
	private String receiptCheck;
	private String parentStatus;
    private String patientScheme;
	private String errClaimId;
	private String filename;
	private String schemeId;
    private String booksUpdatedStatus;
	
public EhfClaimTrustPayment() {
	 
}

@Id
@Column(name="TRUST_PAYMENT_ID", nullable = false)
public String getTrustPaymentId() {
        return this.trustPaymentId;
}
    
@Column(name="CASE_FOLLOWUP_ID")
public String getCaseFollowupId() {
	return this.caseFollowupId;
}
@Column(name="CASE_ID")
public String getCaseId() {
	return this.caseId;
}
@Column(name="CLAIM_AMOUNT")
public Double getClaimAmount() {
	return this.claimAmount;
}
@Column(name="CRT_DT")
public Date getCrtDt() {
	return this.crtDt;
}
@Column(name="CRT_USR")
public String getCrtUsr() {
	return this.crtUsr;
}
@Column(name="ERR_CLAIM_ID")
public String getErrClaimId() {
	return this.errClaimId;
}
@Column(name="LST_UPD_DT")
public Date getLstUpdDt() {
	return this.lstUpdDt;
}
@Column(name="LST_UPD_USR")
public String getLstUpdUsr() {
	return this.lstUpdUsr;
}
@Column(name="PARENT_STATUS")
public String getParentStatus() {
	return this.parentStatus;
}
@Column(name="PAYMENT_CHECK")
public String getPaymentCheck() {
	return this.paymentCheck;
}
 @Column(name="PAYMENT_STATUS")
public String getPaymentStatus() {
	return this.paymentStatus;
}
@Column(name="RECEIPT_CHECK")
public String getReceiptCheck() {
	return this.receiptCheck;
}
@Column(name="REMARKS")
public String getRemarks() {
	return this.remarks;
}
@Column(name="SBH_PAID_DATE")
public Date getSbhPaidDate() {
	return this.sbhPaidDate;
}
@Column(name="TIME_MIL_SEC")
public Double getTimeMilSec() {
	return this.timeMilSec;
}
@Column(name="TOBE_PAID")
public String getTobePaid() {
	return this.tobePaid;
}
@Column(name="TRANS_STATUS")
public String getTransStatus() {
	return this.transStatus;
}
@Column(name="TRANSACTION_DT")
public Date getTransactionDt() {
	return this.transactionDt;
}
@Column(name="TRANSACTION_ID")
public String getTransactionId() {
	return this.transactionId;
}


public void setCaseFollowupId(String caseFollowupId) {
	this.caseFollowupId = caseFollowupId;
}

public void setCaseId(String caseId) {
	this.caseId = caseId;
}

public void setClaimAmount(Double claimAmount) {
	this.claimAmount = claimAmount;
}

public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}

public void setErrClaimId(String errClaimId) {
	this.errClaimId = errClaimId;
}

public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}

public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}

public void setParentStatus(String parentStatus) {
	this.parentStatus = parentStatus;
}

public void setPaymentCheck(String paymentCheck) {
	this.paymentCheck = paymentCheck;
}

public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}

public void setReceiptCheck(String receiptCheck) {
	this.receiptCheck = receiptCheck;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public void setSbhPaidDate(Date sbhPaidDate) {
	this.sbhPaidDate = sbhPaidDate;
}

public void setTimeMilSec(Double timeMilSec) {
	this.timeMilSec = timeMilSec;
}

public void setTobePaid(String tobePaid) {
	this.tobePaid = tobePaid;
}

public void setTransStatus(String transStatus) {
	this.transStatus = transStatus;
}

public void setTransactionDt(Date transactionDt) {
	this.transactionDt = transactionDt;
}

public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}

public void setTrustPaymentId(String trustPaymentId) {
	this.trustPaymentId = trustPaymentId;
}
@Column(name="FILE_NAME")
public String getFilename() {
	return filename;
}

public void setFilename(String filename) {
	this.filename = filename;
}
@Column(name="scheme_id")
public String getSchemeId() {
	return schemeId;
}
public void setSchemeId(String schemeId) {
	this.schemeId = schemeId;
}
@Column(name="patient_scheme")
public String getPatientScheme() {
	return patientScheme;
}

public void setPatientScheme(String patientScheme) {
	this.patientScheme = patientScheme;
}
@Column(name="BOOKS_UPDATED_STATUS")
public String getBooksUpdatedStatus() {
	return booksUpdatedStatus;
}

public void setBooksUpdatedStatus(String booksUpdatedStatus) {
	this.booksUpdatedStatus = booksUpdatedStatus;
}
}
