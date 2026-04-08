package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_COTD_CHKLST database table.
 * 
 */
@Entity
@Table(name="EHF_COTD_CHKLST")
public class EhfCotdChklst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfCotdChklstPK id;
	/*private String benificiaryEnq;
	private String benificiaryRmrks;*/
	private Date crtDt;
	private String crtUsr;
	private String exeRmksRemarks;
	private String exeRmksVerified;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String remarks;

    public EhfCotdChklst() {
    }


	@EmbeddedId
	public EhfCotdChklstPK getId() {
		return this.id;
	}

	public void setId(EhfCotdChklstPK id) {
		this.id = id;
	}
	

	/*@Column(name="BENIFICIARY_ENQ")
	public String getBenificiaryEnq() {
		return this.benificiaryEnq;
	}

	public void setBenificiaryEnq(String benificiaryEnq) {
		this.benificiaryEnq = benificiaryEnq;
	}


	@Column(name="BENIFICIARY_RMRKS")
	public String getBenificiaryRmrks() {
		return this.benificiaryRmrks;
	}

	public void setBenificiaryRmrks(String benificiaryRmrks) {
		this.benificiaryRmrks = benificiaryRmrks;
	}*/


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


	@Column(name="EXE_RMKS_REMARKS")
	public String getExeRmksRemarks() {
		return this.exeRmksRemarks;
	}

	public void setExeRmksRemarks(String exeRmksRemarks) {
		this.exeRmksRemarks = exeRmksRemarks;
	}


	@Column(name="EXE_RMKS_VERIFIED")
	public String getExeRmksVerified() {
		return this.exeRmksVerified;
	}

	public void setExeRmksVerified(String exeRmksVerified) {
		this.exeRmksVerified = exeRmksVerified;
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


	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}