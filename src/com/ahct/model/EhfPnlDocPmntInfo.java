package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_PNLDOC_PMNT_INFO")
public class EhfPnlDocPmntInfo implements Serializable{
	
	private Long pmntId;
	private Date fromDate;
	private Date toDate;
	private Float amount;
	private Float preauthPercent;
	private Float claimPercent;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String activeFlg;
	

	@Id
	@Column(name = "PMNT_ID", nullable = false, length = 12)
	public Long getPmntId() {
		return pmntId;
	}

	public void setPmntId(Long pmntId) {
		this.pmntId = pmntId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FROM_DATE", nullable = false)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TO_DATE", nullable = false)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	
	@Column(name = "AMOUNT", nullable = false)
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "PREAUTH_PERCENT", nullable = false)
	public Float getPreauthPercent() {
		return preauthPercent;
	}

	public void setPreauthPercent(Float preauthPercent) {
		this.preauthPercent = preauthPercent;
	}

	@Column(name = "CLAIM_PERCENT", nullable = false)
	public Float getClaimPercent() {
		return claimPercent;
	}

	public void setClaimPercent(Float claimPercent) {
		this.claimPercent = claimPercent;
	}

	
	@Column(name = "CRT_USR", nullable = false, length = 12)
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", nullable = true, length = 12)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String updUsr) {
		this.lstUpdUsr = updUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT",nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date updDt) {
		this.lstUpdDt = updDt;
	}

	
	@Column(name = "ACTIVE_YN", nullable = true, length = 1)
	public String getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}
	
	
	
	

}
