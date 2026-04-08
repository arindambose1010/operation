package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_CLAIM_UPLOAD_FILE database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_CLAIM_UPLOAD_FILE")
public class EhfChronicClaimUploadFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String attachmentPath;
	private Date crtDt;
	private String crtUsr;
	private String fileName;
	private String filePath;
	private String fileStatus;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private int setId;

    public EhfChronicClaimUploadFile() {
    }


	@Column(name="ATTACHMENT_PATH")
	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Id
	@Column(name="FILE_NAME")
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Column(name="FILE_PATH")
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	@Column(name="FILE_STATUS")
	public String getFileStatus() {
		return this.fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="SET_ID")
	public int getSetId() {
		return this.setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}

}