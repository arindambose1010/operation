package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CSV_CASE_SEARCH database table.
 * 
 */
@Entity
@Table(name="EHF_CSV_CASE_SEARCH")
public class EhfCsvCaseSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	private String requestId;
	private Date crtDt;
	private String crtUsr;
	private String csvQuery;
	private String csvStatus;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String userId;
	private String fileName;
	private String filePath;
	 
    public EhfCsvCaseSearch() {
    }


	@Id
	@Column(name="REQUEST_ID")
	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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


    @Lob()
	@Column(name="CSV_QUERY")
	public String getCsvQuery() {
		return this.csvQuery;
	}

	public void setCsvQuery(String csvQuery) {
		this.csvQuery = csvQuery;
	}


	@Column(name="CSV_STATUS")
	public String getCsvStatus() {
		return this.csvStatus;
	}

	public void setCsvStatus(String csvStatus) {
		this.csvStatus = csvStatus;
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


	@Column(name="USER_ID")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name="FILE_PATH")
	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



}