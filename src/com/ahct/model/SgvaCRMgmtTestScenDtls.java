package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQueries ( { 
     @NamedNativeQuery ( name = "getBugList", resultClass = SgvaCRMgmtTestScenDtls.class, query = "select t.* from sgva_crmgmt_testscen_dtls t where t.line_no in(select max(t1.line_no) from sgva_crmgmt_testscen_dtls t1,sgva_crmgmt_testscen_mst tm where tm.cr_id=:crId and tm.sno=t1.sno and t.testresult=:testRes)" )
} )

@Entity
@Table(name="sgva_crmgmt_testscen_dtls")
public class SgvaCRMgmtTestScenDtls implements Serializable
{
    
    //default constructor
    public SgvaCRMgmtTestScenDtls() {
    }
    
    private SgvaCRMgmtTestScenDtlsId Id;
    private String testremarks ;
    private String testResult ;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;
       
    @EmbeddedId    
    
    @AttributeOverrides( {
        @AttributeOverride(name="sno", column=@Column(name="sno", nullable=false, length=10) ), 
        @AttributeOverride(name="lineNo", column=@Column(name="LINE_NO", nullable=false, length=5) ),
        @AttributeOverride(name="testedBy", column=@Column(name="TestedBy", nullable=false, length=50) )} )
    
    public SgvaCRMgmtTestScenDtlsId getId() {
        return Id;
    }
    
    public void setId(SgvaCRMgmtTestScenDtlsId id) {
        this.Id = id;
    }
    
    public void setTestremarks(String testremarks) {
        this.testremarks = testremarks;
    }

    @Column(name="Testremarks", length=1000)
    public String getTestremarks() {
        return testremarks;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name="TestResult", length=5)
    public String getTestResult() {
        return testResult;
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
