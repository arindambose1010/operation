package com.ahct.model;

import javax.persistence.*;
import java.util.Date;
/**
 * Chandana - 8441 - The persistent class for the EHF_OPD_BILL_ATTCH_AUDIT database table.
 * 
 */
@Entity
@Table(name = "EHF_OPD_BILL_ATTCH_AUDIT")
public class EhfOpdBillAttachAudit {

    //@Id
    @Column(name = "SEQ_ID")
    private Long seqId;

    @Column(name = "CLAIM_NO", length = 200)
    private String claimNo;

    @Column(name = "REMARKS", length = 100)
    private String remarks;
    
    @Column(name = "ACTION")
    private String action;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Id
    @Column(name = "CRT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDt;

    @Column(name = "CRT_USR", length = 100)
    private String crtUsr;

    @Column(name = "LST_UPD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lstUpdDt;

    @Column(name = "LST_UPD_USR", length = 100)
    private String lstUpdUsr;

    // Getters and Setters
	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
   
}

