package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CLAIM_HUBSPOKE_PAYMENT database table.
 * 
 */
@Entity
@Table(name="EHF_CLAIM_HUBSPOKE_PAYMENT")
@NamedQuery(name="EhfCaseClaimHubSpokePayment.findAll", query="SELECT a FROM EhfCaseClaimHubSpokePayment a")
public class EhfCaseClaimHubSpokePayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CASE_ID")
	private String caseId;

	@Column(name="CLAIM_AMOUNT")
	private Double claimAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Id
	@Column(name="HUBSPOKE_PAYMENT_ID")
	private String hubspokePaymentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;

	private String remarks;

	@Column(name="TIME_MIL_SEC")
	private Double timeMilSec;

	@Column(name="TRANS_STATUS")
	private String transStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRANSACTION_DT")
	private Date transactionDt;

	@Column(name="TRANSACTION_ID")
	private String transactionId;

	public EhfCaseClaimHubSpokePayment() {
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public Double getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getHubspokePaymentId() {
		return this.hubspokePaymentId;
	}

	public void setHubspokePaymentId(String hubspokePaymentId) {
		this.hubspokePaymentId = hubspokePaymentId;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getTimeMilSec() {
		return this.timeMilSec;
	}

	public void setTimeMilSec(Double timeMilSec) {
		this.timeMilSec = timeMilSec;
	}

	public String getTransStatus() {
		return this.transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public Date getTransactionDt() {
		return this.transactionDt;
	}

	public void setTransactionDt(Date transactionDt) {
		this.transactionDt = transactionDt;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}