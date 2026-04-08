
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
@Table(name = "EHF_PNLDOC_TDS_PAYMENT")

public class EhfPnldocTdsPayment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tdsPaymentId;
   
    private String paymentStatus;
    private Double claimAmt;
    private String transId;
    private Date transDate;
    private String transStatus;
    private Long timeMilliSec;
    private String remarks;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;
    private String paymentCheck;
    private String toBePaid;
    private Date sbhPaidDate;
    private String receiptCheck;
    private String caseType;
    private String paymentType;
    private String tdsStatus;
    private String paymentRefId;
    private String caseSetId;
    private String deductorType;
    private String fileName;
    private String scheme;;

   

	public void setTdsPaymentId(String tdsPaymentId) {
        this.tdsPaymentId = tdsPaymentId;
    }

    @Id
    @Column(name="TDS_PAYMENT_ID", length=25, nullable=false)
    public String getTdsPaymentId() {
        return tdsPaymentId;
    }

    

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Column(name="PAYMENT_STATUS", length=200)
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setClaimAmt(Double claimAmt) {
        this.claimAmt = claimAmt;
    }

    @Column(name="CLAIM_AMOUNT")
    public Double getClaimAmt() {
        return claimAmt;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    @Column(name="TRANSACTION_ID", length=30)
    public String getTransId() {
        return transId;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TRANSACTION_DT", length=7)
    public Date getTransDate() {
        return transDate;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    @Column(name="TRANS_STATUS", length=12)
    public String getTransStatus() {
        return transStatus;
    }

    public void setTimeMilliSec(Long timeMilliSec) {
        this.timeMilliSec = timeMilliSec;
    }

    @Column(name="TIME_MIL_SEC")
    public Long getTimeMilliSec() {
        return timeMilliSec;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name="REMARKS", length=500)
    public String getRemarks() {
        return remarks;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", length=7)
    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    @Column(name="CRT_USR", length=20)
    public String getCrtUser() {
        return crtUser;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT", length=7)
    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }

    @Column(name="LST_UPD_USR", length=20)
    public String getLastUpdUser() {
        return lastUpdUser;
    }

    public void setPaymentCheck(String paymentCheck) {
        this.paymentCheck = paymentCheck;
    }

    @Column(name="PAYMENT_CHECK", length=15)
    public String getPaymentCheck() {
        return paymentCheck;
    }

    public void setToBePaid(String toBePaid) {
        this.toBePaid = toBePaid;
    }

    @Column(name="TOBE_PAID", length=5)
    public String getToBePaid() {
        return toBePaid;
    }

    public void setSbhPaidDate(Date sbhPaidDate) {
        this.sbhPaidDate = sbhPaidDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SBH_PAID_DATE", length=7)
    public Date getSbhPaidDate() {
        return sbhPaidDate;
    }

    

    public void setReceiptCheck(String receiptCheck) {
        this.receiptCheck = receiptCheck;
    }

    @Column(name="RECEIPT_CHECK", length=10)
    public String getReceiptCheck() {
        return receiptCheck;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    @Column(name="CASE_TYPE", length=10)
    public String getCaseType() {
        return caseType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Column(name="PAYMENT_TYPE", length=10)
    public String getPaymentType() {
        return paymentType;
    }

    public void setTdsStatus(String tdsStatus) {
        this.tdsStatus = tdsStatus;
    }

    @Column(name="TDS_STATUS", length=10)
    public String getTdsStatus() {
        return tdsStatus;
    }

    public void setPaymentRefId(String paymentRefId) {
        this.paymentRefId = paymentRefId;
    }

    @Column(name="PAYMENT_REF_ID", length=40)
    public String getPaymentRefId() {
        return paymentRefId;
    }

    public void setCaseSetId(String caseSetId) {
        this.caseSetId = caseSetId;
    }

    @Column(name="CASE_SET_ID", length=15)
    public String getCaseSetId() {
        return caseSetId;
    }

    public void setDeductorType(String deductorType) {
        this.deductorType = deductorType;
    }

    @Column(name="DEDUCTOR_TYPE", length=10,nullable=false)
    public String getDeductorType() {
        return deductorType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name="FILE_NAME", length=25)
    public String getFileName() {
        return fileName;
    }
    
    @Column(name="SCHEME")
    public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}

