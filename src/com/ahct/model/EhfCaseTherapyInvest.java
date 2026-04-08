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
@Table(name = "EHF_CASE_THERAPY_INVEST")
public class EhfCaseTherapyInvest implements Serializable {

	private Long sno;
	private String caseId;
	private String icdProcCode;
	private String investigationId;
	private String attachTotalPath;
	private String investigationStage;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String activeYN;
    private String filename;
    
    @Column(name="filename")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public EhfCaseTherapyInvest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
     @Column(name="sno")
    public Long getSno() {
		return sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}
	@Column(name="case_id")
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name="ICD_Proc_Code")
	public String getIcdProcCode() {
		return icdProcCode;
	}

	public void setIcdProcCode(String icdProcCode) {
		this.icdProcCode = icdProcCode;
	}
	@Column(name="investigation_id")
	public String getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}
	@Column(name="attach_total_path")
	public String getAttachTotalPath() {
		return attachTotalPath;
	}

	public void setAttachTotalPath(String attachTotalPath) {
		this.attachTotalPath = attachTotalPath;
	}
	@Column(name="investigation_stage")
	public String getInvestigationStage() {
		return investigationStage;
	}

	public void setInvestigationStage(String investigationStage) {
		this.investigationStage = investigationStage;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = false)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    
    @Column(name="activeyn")
	public String getActiveYN() {
		return activeYN;
	}
	
   public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

public EhfCaseTherapyInvest(Long sno, String caseId, String icdProcCode,
		String investigationId, String attachTotalPath,
		String investigationStage, Date crtDt, String crtUsr, Date lstUpdDt,
		String lstUpdUsr, String activeYN, String filename) {
	super();
	this.sno = sno;
	this.caseId = caseId;
	this.icdProcCode = icdProcCode;
	this.investigationId = investigationId;
	this.attachTotalPath = attachTotalPath;
	this.investigationStage = investigationStage;
	this.crtDt = crtDt;
	this.crtUsr = crtUsr;
	this.lstUpdDt = lstUpdDt;
	this.lstUpdUsr = lstUpdUsr;
	this.activeYN = activeYN;
	this.filename = filename;
}
   
}
