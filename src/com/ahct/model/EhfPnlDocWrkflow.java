package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "EHF_PNLDOC_WORKFLOW")
public class EhfPnlDocWrkflow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfPnlDocWrkFlowId Id;
	private String prevWrkflwId;
	private String prevOwnrGrp;
	private String currWrkflwId;
	private String currOwnrGrp;
	private String StatusFlg;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String scheme;
	private Long claimAprvAmt;
	private Long claimRejAmt;
	private Long claimPendAmt;
	private Long preauthAprvAmt;
	private Long preauthRejAmt;
	private Long preauthPendAmt;
	private Long totalPnldocAmt;
	private String month;
	private String year;
	private String sendBackFlag;
	private String sendbackToUser;
	
	private Long holdPendingAmt;
	private Long holdReleasedAmt;
	
	@EmbeddedId 
	    @AttributeOverrides( {
	        @AttributeOverride(name="wId", column=@Column(name="W_ID",nullable=false)),
	        @AttributeOverride(name="docId",column=@Column(name="DOC_ID",nullable=false ))
	    })
	public EhfPnlDocWrkFlowId getId() {
		return Id;
	}
	public void setId(EhfPnlDocWrkFlowId id) {
		Id = id;
	}
	@Column(name = "PREVIOUS_WORKFLOW_ID", length = 50)
	public String getPrevWrkflwId() {
		return prevWrkflwId;
	}
	public void setPrevWrkflwId(String prevWrkflwId) {
		this.prevWrkflwId = prevWrkflwId;
	}
	
	@Column(name = "PREVIOUS_OWNER_GRP", length = 20)
	public String getPrevOwnrGrp() {
		return prevOwnrGrp;
	}
	public void setPrevOwnrGrp(String prevOwnrGrp) {
		this.prevOwnrGrp = prevOwnrGrp;
	}
	
	
	@Column(name = "CURRENT_WORKFLOW_ID", length = 50)
	public String getCurrWrkflwId() {
		return currWrkflwId;
	}
	public void setCurrWrkflwId(String currWrkflwId) {
		this.currWrkflwId = currWrkflwId;
	}
	
	
	@Column(name = "CURRENT_OWNER_GRP", length = 20)
	public String getCurrOwnrGrp() {
		return currOwnrGrp;
	}
	public void setCurrOwnrGrp(String currOwnrGrp) {
		this.currOwnrGrp = currOwnrGrp;
	}
	
	
	@Column(name = "STATUS_FLAG")
	public String getStatusFlg() {
		return StatusFlg;
	}
	public void setStatusFlg(String statusFlg) {
		StatusFlg = statusFlg;
	}
	
	
	@Column(name = "CRT_USR", nullable = false, length = 20)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	
	@Column(name = "LST_UPD_USR", length = 20)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name = "SCHEME", length = 20)
	 public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	@Column(name = "CLAIM_APRV_AMT", length = 20)
	public Long getClaimAprvAmt() {
		return claimAprvAmt;
	}
	public void setClaimAprvAmt(Long claimAprvAmt) {
		this.claimAprvAmt = claimAprvAmt;
	}
	
	@Column(name = "CLAIM_REJ_AMT", length = 20)
	public Long getClaimRejAmt() {
		return claimRejAmt;
	}
	public void setClaimRejAmt(Long claimRejAmt) {
		this.claimRejAmt = claimRejAmt;
	}
	
	@Column(name = "CLAIM_PEND_AMT", length = 20)
	public Long getClaimPendAmt() {
		return claimPendAmt;
	}
	public void setClaimPendAmt(Long claimPendAmt) {
		this.claimPendAmt = claimPendAmt;
	}
	
	@Column(name = "PREAUTH_APRV_AMT", length = 20)
	public Long getPreauthAprvAmt() {
		return preauthAprvAmt;
	}
	public void setPreauthAprvAmt(Long preauthAprvAmt) {
		this.preauthAprvAmt = preauthAprvAmt;
	}
	
	@Column(name = "PREAUTH_REJ_AMT", length = 20)
	public Long getPreauthRejAmt() {
		return preauthRejAmt;
	}
	public void setPreauthRejAmt(Long preauthRejAmt) {
		this.preauthRejAmt = preauthRejAmt;
	}
	
	@Column(name = "PREAUTH_PEND_AMT", length = 20)
	public Long getPreauthPendAmt() {
		return preauthPendAmt;
	}
	public void setPreauthPendAmt(Long preauthPendAmt) {
		this.preauthPendAmt = preauthPendAmt;
	}
	
	@Column(name = "TOTAL_PNLDOC_AMT", length = 20)
	public Long getTotalPnldocAmt() {
		return totalPnldocAmt;
	}
	public void setTotalPnldocAmt(Long totalPnldocAmt) {
		this.totalPnldocAmt = totalPnldocAmt;
	}
	
	@Column(name = "MONTH", length = 20)
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	@Column(name = "YEAR", length = 20)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name = "send_back_flag", length = 20)
	public String getSendBackFlag() {
		return sendBackFlag;
	}
	public void setSendBackFlag(String sendBackFlag) {
		this.sendBackFlag = sendBackFlag;
	}
	
	@Column(name = "hold_pend_amt", length = 20)
	public Long getHoldPendingAmt() {
		return holdPendingAmt;
	}
	public void setHoldPendingAmt(Long holdPendingAmt) {
		this.holdPendingAmt = holdPendingAmt;
	}
	
	@Column(name = "hold_released_amt", length = 20)
	public Long getHoldReleasedAmt() {
		return holdReleasedAmt;
	}
	public void setHoldReleasedAmt(Long holdReleasedAmt) {
		this.holdReleasedAmt = holdReleasedAmt;
	}
	
	@Column(name = "SENDBACK_TO_USER", length = 20)
	public String getSendbackToUser() {
		return sendbackToUser;
	}
	public void setSendbackToUser(String sendbackToUser) {
		this.sendbackToUser = sendbackToUser;
	}	
	
}
