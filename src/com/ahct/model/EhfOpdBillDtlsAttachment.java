package com.ahct.model;

import javax.persistence.*;
import java.util.Date;
/**
 * Chandana - 8441 - The persistent class for the EHF_OPD_BILL_ATTACHMENT database table.
 * 
 */
@Entity
@Table(name = "EHF_OPD_BILL_ATTACHMENT")
public class EhfOpdBillDtlsAttachment {

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehf_opd_attach_seq_gen")
    @SequenceGenerator(name = "ehf_opd_attach_seq_gen", sequenceName = "EHF_OPD_ATTACH_ID_SEQ", allocationSize = 1)
    @Column(name = "SEQ_ID")
    private Long seqId;

    @Column(name = "CR_NO")
    private String crNo;
    
    @Column(name="HOSP_CODE")
    private String hospCode;
    
    @Column(name = "CLAIM_NO", length = 200)
    private String claimNo;
    
    @Column(name = "RECORD_NAME")
    private String recordName;
    
    @Column(name = "DOC_PATH")
    private String docPath;
    
    @Column(name = "STATUS")
    private String status;
    
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

    @Column(name = "COMMENTS")
    private String comments;
    
    @Column(name = "ACTIVE_YN")
    private String activeYn;
    
    //Chandana - 8599 - Added this for bill no column
    @Column(name="BILL_NO")
    private String billNo;

    // Getters and Setters
	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	
	public String getCrNo() {
		return crNo;
	}

	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	//Chandana - 8599 - Added getters and setters
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
}

