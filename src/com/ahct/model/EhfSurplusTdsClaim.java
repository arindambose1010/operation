package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ehs_surplus_tds_claim")
public class EhfSurplusTdsClaim implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String caseId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Double hospPctAmt;
	private Double tdsHospPctAmt;
    private Double tdsPctAmt;
   private Double trustPctAmt;
   private String TDSRFFflag;
	
	@Id
	@Column(name = "case_id", nullable = false)
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@Column(name = "CRT_USR", length = 30)
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", length = 30)
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Column(name="HOSP_PCT_AMT")
    public Double getHospPctAmt() {
        return hospPctAmt;
    }

    public void setHospPctAmt(Double hospPctAmt) {
        this.hospPctAmt = hospPctAmt;
    }
    

    @Column(name="TDS_HOSP_PCT_AMT")
    public Double getTdsHospPctAmt() {
        return tdsHospPctAmt;
    }

    public void setTdsHospPctAmt(Double tdsHospPctAmt) {
        this.tdsHospPctAmt = tdsHospPctAmt;
    }

    @Column(name="TDS_PCT_AMT")
    public Double getTdsPctAmt() {
        return tdsPctAmt;
    }

    public void setTdsPctAmt(Double tdsPctAmt) {
        this.tdsPctAmt = tdsPctAmt;
    }


    @Column(name="TRUST_PCT_AMT")
    public Double getTrustPctAmt() {
        return trustPctAmt;
    }

    public void setTrustPctAmt(Double trustPctAmt) {
        this.trustPctAmt = trustPctAmt;
    }
    @Column(name="TDS_RF_FLAG")
	public String getTDSRFFflag() {
		return TDSRFFflag;
	}

	public void setTDSRFFflag(String tDSRFFflag) {
		TDSRFFflag = tDSRFFflag;
	}


	
}



