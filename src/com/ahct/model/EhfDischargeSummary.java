package com.ahct.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_DISCHARGE_SUMMARY")
public class EhfDischargeSummary implements Serializable{
	private String advise;
    private String blockName;
    private String caseId;
    private String clincalHistory;
    private Date crtDt;
    private String crtUsr;
    private String dischargeStatus;
    private String examination;
    private String floorNo;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private Timestamp nextFollowupDt;
    private String operativeFindings;
    private String postOperPeriod;
    private String review;
    private String roomNo;
    private String treatmentGiven;
    private String postSplInvestgtns;
    private String causeOfDeath;

    public EhfDischargeSummary (){
    }

    @Column(name="ADVISE")
    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    @Column(name="BLOCK_NAME")
    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    @Id
    @Column(name="CASE_ID", nullable = false)
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Column(name="CLINCAL_HISTORY")
    public String getClincalHistory() {
        return clincalHistory;
    }

    public void setClincalHistory(String clincalHistory) {
        this.clincalHistory = clincalHistory;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT")
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    @Column(name="DISCHARGE_STATUS")
    public String getDischargeStatus() {
        return dischargeStatus;
    }

    public void setDischargeStatus(String dischargeStatus) {
        this.dischargeStatus = dischargeStatus;
    }

    @Column(name="EXAMINATION")
    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    @Column(name="FLOOR_NO")
    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
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

    @Column(name="NEXT_FOLLOWUP_DT")
    public Timestamp getNextFollowupDt() {
        return nextFollowupDt;
    }

    public void setNextFollowupDt(Timestamp nextFollowupDt) {
        this.nextFollowupDt = nextFollowupDt;
    }

    @Column(name="OPERATIVE_FINDINGS")
    public String getOperativeFindings() {
        return operativeFindings;
    }

    public void setOperativeFindings(String operativeFindings) {
        this.operativeFindings = operativeFindings;
    }

    @Column(name="POST_OPER_PERIOD")
    public String getPostOperPeriod() {
        return postOperPeriod;
    }

    public void setPostOperPeriod(String postOperPeriod) {
        this.postOperPeriod = postOperPeriod;
    }
    
    @Column(name="REVIEW")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Column(name="ROOM_NO")
    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    @Column(name="TREATMENT_GIVEN")
    public String getTreatmentGiven() {
        return treatmentGiven;
    }

    public void setTreatmentGiven(String treatmentGiven) {
        this.treatmentGiven = treatmentGiven;
    }

    public void setPostSplInvestgtns(String postSplInvestgtns) {
        this.postSplInvestgtns = postSplInvestgtns;
    }
    @Column(name="POST_SPL_INVESTGTNS")
    public String getPostSplInvestgtns() {
        return postSplInvestgtns;
    }
    
    @Column(name="CAUSE_OF_DEATH")
	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
}
