package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="SGVA_CRMGMT_ATTACH", uniqueConstraints = {@UniqueConstraint(columnNames={"CR_ID", "LINEITEM_NO"})}
)

public class SgvaCRMgmtAttach  implements java.io.Serializable
{
    public SgvaCRMgmtAttach() {}
    
    private SgvaCRMgmtAttachId id;
    private String empId;
    private String attachFileName;
    private String attachFileSize;
    private String attachFileExtension;
    private String attachFileContentType;
    private String attachFilePath;
    private String active;
    private Date crtDate;
    private String crtUser;
    private Date lastUpdDate;
    private String lastUpdUser;
    private Long remarksLineItemNo;

    @EmbeddedId    
    
    

    public SgvaCRMgmtAttachId getId() {
        return this.id;
    }
    
    public void setId(SgvaCRMgmtAttachId id) {
        this.id = id;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Column(name="EMP_ID", length=12)
    public String getEmpId() {
        return empId;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }

    @Column(name="ATTCH_FILE_NAME", length=200)
    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileSize(String attachFileSize) {
        this.attachFileSize = attachFileSize;
    }

    @Column(name="ATTCH_FILE_SIZE", length=10)
    public String getAttachFileSize() {
        return attachFileSize;
    }

    public void setAttachFileExtension(String attachFileExtension) {
        this.attachFileExtension = attachFileExtension;
    }

    @Column(name="ATTCH_FILE_EXTENSION", length=10)
    public String getAttachFileExtension() {
        return attachFileExtension;
    }

    public void setAttachFileContentType(String attachFileContentType) {
        this.attachFileContentType = attachFileContentType;
    }

    @Column(name="ATTACH_FILE_CONTENT_TYPE", length=200)
    public String getAttachFileContentType() {
        return attachFileContentType;
    }

    public void setAttachFilePath(String attachFilePath) {
        this.attachFilePath = attachFilePath;
    }

    @Column(name="ATTACH_FILE_PATH", length=200)
    public String getAttachFilePath() {
        return attachFilePath;
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

    @Column(name="CRT_USR", length=10)
    public String getCrtUser() {
        return crtUser;
    }
    
    public void setActive(String active) {
        this.active = active;
    }

    @Column(name="ACTIVE", length=5)
    public String getActive() {
        return active;
    }
    
    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="LST_UPD_DT", length=7)
    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }

    @Column(name="LST_UPD_USR", length=10)
    public String getLastUpdUser() {
        return lastUpdUser;
    }

    public void setRemarksLineItemNo(Long remarksLineItemNo) {
        this.remarksLineItemNo = remarksLineItemNo;
    }
    
    @Column(name="REMARKS_LINEITEM_NO",length=10)
    public Long getRemarksLineItemNo() {
        return remarksLineItemNo;
    }
}
