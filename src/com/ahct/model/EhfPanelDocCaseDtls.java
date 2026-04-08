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
@Table(name = "EHF_PNLDOC_CASE_DTLS")
public class EhfPanelDocCaseDtls implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String caseId;
	private String docId;
	private String hospId;
	private String casePmntStatus;
	private Date caseAppOrPendDate;
	private Float amount;
	private String particulars;
	private String pnlDocPmntStatus;
	private String pmntId;
	private Date pmntDate;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Long workFlwId;
	private String scheme;

	@Id
	@Column(name = "ID", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CASE_ID", nullable = false, length = 12)
	public String getCaseId() {
		return caseId;
	}


	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@Column(name = "DOC_ID", nullable = false, length = 12)
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	@Column(name = "HOSP_ID", nullable = false, length = 10)
	public String getHospId() {
		return hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	@Column(name = "CASE_PMNT_STATUS", nullable = false, length = 12)
	public String getCasePmntStatus() {
		return casePmntStatus;
	}

	public void setCasePmntStatus(String casePmntStatus) {
		this.casePmntStatus = casePmntStatus;
	}
	
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(name = "CASE_APP_OR_PEND_DT", nullable = false)
	    public Date getCaseAppOrPendDate() {
			return caseAppOrPendDate;
		}

		public void setCaseAppOrPendDate(Date caseAppOrPendDate) {
			this.caseAppOrPendDate = caseAppOrPendDate;
		}
		
		@Column(name = "AMOUNT", length = 10)
		public Float getAmount() {
			return amount;
		}

		public void setAmount(Float amount) {
			this.amount = amount;
		}

		@Column(name = "PARTICULARS",length=200)
		public String getParticulars() {
			return particulars;
		}

		public void setParticulars(String particulars) {
			this.particulars = particulars;
		}

		@Column(name = "PNL_DOC_PMNT_STATUS", length = 12)
		public String getPnlDocPmntStatus() {
			return pnlDocPmntStatus;
		}

		public void setPnlDocPmntStatus(String pnlDocPmntStatus) {
			this.pnlDocPmntStatus = pnlDocPmntStatus;
		}
		
		@Column(name = "PMNT_ID",length = 12)
		public String getPmntId() {
			return pmntId;
		}

		public void setPmntId(String pmntId) {
			this.pmntId = pmntId;
		}
		
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "PMNT_DATE")
		public Date getPmntDate() {
			return pmntDate;
		}

		public void setPmntDate(Date pmntDate) {
			this.pmntDate = pmntDate;
		}
		
		@Column(name = "CRT_USR", nullable = false, length = 12)
		public String getCrtUsr() {
			return crtUsr;
		}

		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="CRT_DT", nullable = false)
		public Date getCrtDt() {
			return crtDt;
		}

		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}

		
		
		
		@Column(name = "LST_UPD_USR", length = 12)
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}

		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="LST_UPD_DT")
		public Date getLstUpdDt() {
			return lstUpdDt;
		}

		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}

		
		@Column(name = "WRKFLW_ID")
		public Long getWorkFlwId() {
			return workFlwId;
		}

		public void setWorkFlwId(Long workFlwId) {
			this.workFlwId = workFlwId;
		}

		
		
		@Column(name = "SCHEME")
		public String getScheme() {
			return scheme;
		}

		public void setScheme(String scheme) {
			this.scheme = scheme;
		}
		
}
