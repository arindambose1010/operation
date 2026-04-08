package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ehf_followup_claim_accounts"
)
public class EhfFollowUpClaimAccounts implements java.io.Serializable{

	private Double aprvdAmt;
    private String caseFollowUpId;
    private String payeeName;
    private String remarks;
    private String transactionId;
    private Date transactionDt;
    private String transStatus;
    private Long timeMilSec;
    private String srcAcctNo;
    private String desAcctNo;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String revDesAcctNo;
    private String tdsDesAcctNo;
    private Date sbhPaidDate;
    
    @Column(name="aprvd_amt")
	public Double getAprvdAmt() {
		return aprvdAmt;
	}
	public void setAprvdAmt(Double aprvdAmt) {
		this.aprvdAmt = aprvdAmt;
	}
	@Id
	@Column(name="case_followup_id",nullable=false)
	public String getCaseFollowUpId() {
		return caseFollowUpId;
	}
	public void setCaseFollowUpId(String caseFollowUpId) {
		this.caseFollowUpId = caseFollowUpId;
	}
	@Column(name="payee_name")
	public String getPayeeName() {
		return payeeName;
	}
	
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="transaction_id")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Column(name="transaction_dt")
	public Date getTransactionDt() {
		return transactionDt;
	}
	public void setTransactionDt(Date transactionDt) {
		this.transactionDt = transactionDt;
	}
	@Column(name="trans_status")
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	@Column(name="time_mil_sec")
	public Long getTimeMilSec() {
		return timeMilSec;
	}
	public void setTimeMilSec(Long timeMilSec) {
		this.timeMilSec = timeMilSec;
	}
	@Column(name="src_acct_no")
	public String getSrcAcctNo() {
		return srcAcctNo;
	}
	public void setSrcAcctNo(String srcAcctNo) {
		this.srcAcctNo = srcAcctNo;
	}
	@Column(name="des_acct_no")
	public String getDesAcctNo() {
		return desAcctNo;
	}
	public void setDesAcctNo(String desAcctNo) {
		this.desAcctNo = desAcctNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="crt_dt")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="crt_usr")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lst_upd_dt")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="lst_upd_usr")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Column(name="REV_DES_ACCT_NO")
    public String getRevDesAcctNo() {
        return revDesAcctNo;
    }

    public void setRevDesAcctNo(String revDesAcctNo) {
        this.revDesAcctNo = revDesAcctNo;
    }

    @Column(name="TDS_DES_ACCT_NO")
    public String getTdsDesAcctNo() {
        return tdsDesAcctNo;
    }

    public void setTdsDesAcctNo(String tdsDesAcctNo) {
        this.tdsDesAcctNo = tdsDesAcctNo;
    }
    public void setSbhPaidDate(Date sbhPaidDate) {
        this.sbhPaidDate = sbhPaidDate;
    }
    @Column(name="sbh_paid_date")
    public Date getSbhPaidDate() {
        return sbhPaidDate;
    }
}
