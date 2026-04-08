package com.ahct.model;

import javax.persistence.*;

import java.util.Date;
/**
 * Chandana - 8755 - The persistent class for the EHF_JRNLSTD_ATTACHMENT database table.
 * 
 */
@Entity
@Table(name = "EHF_JRNLSTD_ATTACHMENT")
public class EhfJrnlstDAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehf_jrnlstd_attach_seq_gen")
    @SequenceGenerator(name = "ehf_jrnlstd_attach_seq_gen", sequenceName = "EHF_JRNLSTD_ATTCH_SEQ", allocationSize = 1)
    @Column(name = "SEQ_ID")
    private Long seqId;
    
    @Column(name = "PATIENT_ID")
    private String patientId;

    @Column(name = "CLAIM_NO")
    private String claimNo;
    
    @Column(name="FILE_NAME")
    private String fileName;
    
    @Column(name = "ATTACH_TYPE")
    private String attcahType;
    
    @Column(name = "DOC_PATH")
    private String docPath;
    
    @Column(name = "REMARKS")
    private String remarks;
    
    @Column(name = "ACTIVE_YN")
    private String activeYn;
    
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

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttcahType() {
		return attcahType;
	}

	public void setAttcahType(String attcahType) {
		this.attcahType = attcahType;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
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

	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	
		
}

