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
@Table(name="sgva_crmgmt_testscen_mst")
public class SgvaCRMgmtTestScenMst implements Serializable
{
    //default constructor
    public SgvaCRMgmtTestScenMst() 
    {
    }
    private long sno;
    private String crId;
    private String testScenarioDesc;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;

    public void setSno(long sno) {
        this.sno = sno;
    }
    
    @Id
    @Column(name="SNO", nullable=false, length=10)
    public long getSno() {
        return sno;
    }

    public void setCrId(String crId) {
        this.crId = crId;
    }

    @Column(name="CR_ID", nullable=false, length=200)
    public String getCrId() {
        return crId;
    }

    public void setTestScenarioDesc(String testScenarioDesc) {
        this.testScenarioDesc = testScenarioDesc;
    }

    @Column(name="Test_Scenario_Desc", nullable=false, length=1000)
    public String getTestScenarioDesc() {
        return testScenarioDesc;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", length=7)
    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    @Column(name="CRT_USR", length=12)
    public String getCrtUser() {
        return crtUser;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT", length=7)
    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }

    @Column(name="LST_UPD_USR", length=12)
    public String getLastUpdUser() {
        return lastUpdUser;
    }
}
