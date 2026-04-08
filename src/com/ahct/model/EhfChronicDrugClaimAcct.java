package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_DRUG_CLAIM_ACCTS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_DRUG_CLAIM_ACCTS")
public class EhfChronicDrugClaimAcct implements Serializable {
	private static final long serialVersionUID = 1L;
	private String chronicDrugId;
	private String chronicNo;
	private Date crtDt;
	private String crtUsr;
	private String desAcctNo;
	private BigDecimal drugAmt;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String payeeName;
	private String remarks;
	private String revDestAccNo;
	private Date sbhPaidDate;
	private String srcAcctNo;
	private BigDecimal timeMilSec;
	private String transStatus;
	private Date transactionDt;
	private String transactionId;

    public EhfChronicDrugClaimAcct() {
    }


	@Id
	@Column(name="CHRONIC_DRUG_ID")
	public String getChronicDrugId() {
		return this.chronicDrugId;
	}

	public void setChronicDrugId(String chronicDrugId) {
		this.chronicDrugId = chronicDrugId;
	}


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


	@Column(name="DRUG_AMT")
	public BigDecimal getDrugAmt() {
		return this.drugAmt;
	}

	public void setDrugAmt(BigDecimal drugAmt) {
		this.drugAmt = drugAmt;
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


	@Column(name="REV_DEST_ACC_NO")
	public String getRevDestAccNo() {
		return this.revDestAccNo;
	}

	public void setRevDestAccNo(String revDestAccNo) {
		this.revDestAccNo = revDestAccNo;
	}


    @Temporal( TemporalType.TIMESTAMP)
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
	public BigDecimal getTimeMilSec() {
		return this.timeMilSec;
	}

	public void setTimeMilSec(BigDecimal timeMilSec) {
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