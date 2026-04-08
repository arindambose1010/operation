package com.ahct.model;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "EHF_OPD_PATIENTS")
public class EhfOpdPatient {
    @Id
    @Column(name = "SEQ_ID")
    private Long seqId;
    @Column(name = "CLAIM_NO", length = 200)
    private String claimNo;
    @Column(name = "CR_NO", length = 100)
    private String crNo;
    @Column(name = "PATIENT_NAME", length = 200)
    private String patientName;
    @Column(name = "LETTER_NO", length = 100)
    private String letterNo;
    @Column(name = "CLAIM_ORG", length = 500)
    private String claimOrg;
    @Column(name = "CLAIM_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDt;
    @Column(name = "TOTAL_BILL")
    private Double totalBill;
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
    @Column(name = "CLAIM_STATUS", length = 20)
    private String claimStatus;
    @Column(name = "OP_CLAIM_SEQ", length = 20)
    private String opClaimSeq;
    @Column(name = "MEDCO_CLM_DT")
    @Temporal(TemporalType.TIMESTAMP) //Chandana - 7845 - Changed from date to timestamp, as i need to save date along withtime
    private Date medcoClmDt;
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
    public String getCrNo() {
        return crNo;
    }
    public void setCrNo(String crNo) {
        this.crNo = crNo;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getLetterNo() {
        return letterNo;
    }
    public void setLetterNo(String letterNo) {
        this.letterNo = letterNo;
    }
    public String getClaimOrg() {
        return claimOrg;
    }
    public void setClaimOrg(String claimOrg) {
        this.claimOrg = claimOrg;
    }
    public Date getClaimDt() {
        return claimDt;
    }
    public void setClaimDt(Date claimDt) {
        this.claimDt = claimDt;
    }
    public Double getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
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
    public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getOpClaimSeq() {
        return opClaimSeq;
    }
    public void setOpClaimSeq(String opClaimSeq) {
        this.opClaimSeq = opClaimSeq;
    }
	public Date getMedcoClmDt() {
		return medcoClmDt;
	}
	public void setMedcoClmDt(Date medcoClmDt) {
		this.medcoClmDt = medcoClmDt;
	}
}
