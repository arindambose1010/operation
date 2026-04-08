package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_DRUG_ISSUE_DTLS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_DRUG_ISSUE_DTLS")
public class EhfChronicDrugIssueDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfChronicDrugIssueDtlPK id;

	@Column(name="CASE_ID")
	private String caseId;

    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

    @Temporal( TemporalType.DATE)
	@Column(name="ISSUE_FROM_DT")
	private Date issueFromDt;

    @Temporal( TemporalType.DATE)
	@Column(name="ISSUE_TO_DT")
	private Date issueToDt;

    public EhfChronicDrugIssueDtl() {
    }

	public EhfChronicDrugIssueDtlPK getId() {
		return this.id;
	}

	public void setId(EhfChronicDrugIssueDtlPK id) {
		this.id = id;
	}
	
	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getIssueFromDt() {
		return this.issueFromDt;
	}

	public void setIssueFromDt(Date issueFromDt) {
		this.issueFromDt = issueFromDt;
	}

	public Date getIssueToDt() {
		return this.issueToDt;
	}

	public void setIssueToDt(Date issueToDt) {
		this.issueToDt = issueToDt;
	}

}