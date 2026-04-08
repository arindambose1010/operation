package com.ahct.model;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_CLAIM_ACCOUNTS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_CLAIM_ACCOUNTS")
public class EhfChronicClaimAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal aprvdAmt;
	private String chronicNo;
	private Date crtDt;
	private String crtUsr;
	private String desAcctNo;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String payeeName;
	private String remarks;
	private Date sbhPaidDate;
	private String srcAcctNo;
	private Long timeMilSec;
	private String transStatus;
	private Date transactionDt;
	private String transactionId;

    public EhfChronicClaimAccount() {
    }


	@Column(name="APRVD_AMT")
	public BigDecimal getAprvdAmt() {
		return aprvdAmt;
	}


	public void setAprvdAmt(BigDecimal aprvdAmt) {
		this.aprvdAmt = aprvdAmt;
	}

    @Id
	@Column(name="CHRONIC_NO")
	public String getChronicNo() {
		return this.chronicNo;
	}

	


	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	@Column(name="DES_ACCT_NO")
	public String getDesAcctNo() {
		return this.desAcctNo;
	}

	public void setDesAcctNo(String desAcctNo) {
		this.desAcctNo = desAcctNo;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="PAYEE_NAME")
	public String getPayeeName() {
		return this.payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}


	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="SBH_PAID_DATE")
	public Date getSbhPaidDate() {
		return this.sbhPaidDate;
	}

	public void setSbhPaidDate(Date sbhPaidDate) {
		this.sbhPaidDate = sbhPaidDate;
	}


	@Column(name="SRC_ACCT_NO")
	public String getSrcAcctNo() {
		return this.srcAcctNo;
	}

	public void setSrcAcctNo(String srcAcctNo) {
		this.srcAcctNo = srcAcctNo;
	}


	@Column(name="TIME_MIL_SEC")
	public Long getTimeMilSec() {
		return timeMilSec;
	}


	public void setTimeMilSec(Long timeMilSec) {
		this.timeMilSec = timeMilSec;
	}


	@Column(name="TRANS_STATUS")
	public String getTransStatus() {
		return this.transStatus;
	}

	

	


	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="TRANSACTION_DT")
	public Date getTransactionDt() {
		return this.transactionDt;
	}

	public void setTransactionDt(Date transactionDt) {
		this.transactionDt = transactionDt;
	}


	@Column(name="TRANSACTION_ID")
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}