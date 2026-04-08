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
@Table(name = "EHF_PNLDOC_PAYMENTS")
public class EhfPanelDocPayments implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String docId;
	private String caseSetId;
	private Double amount;
	private String fileName;
	private String transId;
	private Date transDate;
	private String transStatus;
	private Long timeInMilSec;
	private String remarks;
	private String pmntChck;
	private String crtUsr;
	private Date crtDt;
	private Date sbhPaidDate;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String srcAccNo;
	private String destAccNo;
	private String used;
	private Double pnlDocAmt;
	private Double tdsAmt;
	private Double tdsSurchargeAmt;
	private Double tdsCessAmt;
	private Double tdsTaxAmt;
	private String pmntStatus;
	private String tdsDesAcc;
	private String scheme;
	
	
	
	@Id
	@Column(name = "CASES_SET_ID", nullable = false,length = 15)
	public String getCaseSetId() {
		return caseSetId;
	}
	public void setCaseSetId(String caseSetId) {
		this.caseSetId = caseSetId;
	}
	
	@Column(name = "DOCTOR_ID", length = 12)
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Column(name = "FILENAME", length =20)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "TRANSACTION_ID", length = 20)
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSACTION_DT")
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
	@Column(name = "TRANS_STATUS", length = 15)
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	@Column(name = "TIME_MIL_SEC")
	public Long getTimeInMilSec() {
		return timeInMilSec;
	}
	public void setTimeInMilSec(Long timeInMilSec) {
		this.timeInMilSec = timeInMilSec;
	}
	
	@Column(name = "REMARKS", length = 500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "PAYMENT_CHECK", length = 10)
	public String getPmntChck() {
		return pmntChck;
	}
	public void setPmntChck(String pmntChck) {
		this.pmntChck = pmntChck;
	}
	
	@Column(name = "CRT_USR", length = 12)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SBH_PAID_DATE")
	public Date getSbhPaidDate() {
		return sbhPaidDate;
	}
	public void setSbhPaidDate(Date sbhPaidDate) {
		this.sbhPaidDate = sbhPaidDate;
	}
	
	@Column(name = "LST_UPD_USR",  length = 12)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name = "SRC_ACCT_NO", length = 20)
	public String getSrcAccNo() {
		return srcAccNo;
	}
	public void setSrcAccNo(String srcAccNo) {
		this.srcAccNo = srcAccNo;
	}
	
	@Column(name = "DES_ACCT_NO", length = 20)
	public String getDestAccNo() {
		return destAccNo;
	}
	public void setDestAccNo(String destAccNo) {
		this.destAccNo = destAccNo;
	}
	
	
	@Column(name = "USED",  length = 2)
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	
	
	@Column(name = "PNLDOC_AMT")
	public Double getPnlDocAmt() {
		return pnlDocAmt;
	}
	public void setPnlDocAmt(Double pnlDocAmt) {
		this.pnlDocAmt = pnlDocAmt;
	}
	
	
	@Column(name = "TDS_AMT")
	public Double getTdsAmt() {
		return tdsAmt;
	}
	public void setTdsAmt(Double tdsAmt) {
		this.tdsAmt = tdsAmt;
	}
	
	
	@Column(name = "TDS_SUR_AMT")
	public Double getTdsSurchargeAmt() {
		return tdsSurchargeAmt;
	}
	public void setTdsSurchargeAmt(Double tdsSurchargeAmt) {
		this.tdsSurchargeAmt = tdsSurchargeAmt;
	}
	
	@Column(name = "TDS_CESS_AMT")
	public Double getTdsCessAmt() {
		return tdsCessAmt;
	}
	public void setTdsCessAmt(Double tdsCessAmt) {
		this.tdsCessAmt = tdsCessAmt;
	}
	
	@Column(name = "TDS_TAX_AMT")
	public Double getTdsTaxAmt() {
		return tdsTaxAmt;
	}
	public void setTdsTaxAmt(Double tdsTaxAmt) {
		this.tdsTaxAmt = tdsTaxAmt;
	}
	
	@Column(name = "PAYMENT_STATUS", length = 25)
	public String getPmntStatus() {
		return pmntStatus;
	}
	public void setPmntStatus(String pmntStatus) {
		this.pmntStatus = pmntStatus;
	}
	
	
	@Column(name = "TDS_DES_ACCT_NO", length = 50)
	public String getTdsDesAcc() {
		return tdsDesAcc;
	}
	public void setTdsDesAcc(String tdsDesAcc) {
		this.tdsDesAcc = tdsDesAcc;
	}
	
	@Column(name = "SCHEME", length = 10)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	
	
}
