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
@Table(name="EHF_AHC_CLAIM_UPLOAD_FILE"
)
public class EhfAhcClaimUploadFile implements Serializable {

	private String fileName;
    private String crtUser;
    private String filePath;
    private Date crtDate;
    private String fileStatus;
    private Date lstUpdDate;
    private Integer setId;
    private String attachmentPath;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Id
    @Column(name="FILE_NAME",length=20,nullable=false)
    public String getFileName() {
        return fileName;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }
    @Column(name="CRT_USR",length=12,nullable=false)
    public String getCrtUser() {
        return crtUser;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @Column(name="FILE_PATH",length=200,nullable=false)
    public String getFilePath() {
        return filePath;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT",nullable=false)
    public Date getCrtDate() {
        return crtDate;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }
    @Column(name="FILE_STATUS",length=12,nullable=false)
    public String getFileStatus() {
        return fileStatus;
    }

    public void setLstUpdDate(Date lstUpdDate) {
        this.lstUpdDate = lstUpdDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT",nullable=true)
    public Date getLstUpdDate() {
        return lstUpdDate;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }
    @Column(name="SET_ID",length=5,nullable=false)
    public Integer getSetId() {
        return setId;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
    @Column(name="ATTACHMENT_PATH",length=200,nullable=true)
    public String getAttachmentPath() {
        return attachmentPath;
    }
}
