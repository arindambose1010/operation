package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_MEDCO_DTLS database table.
 * 
 */
@Entity
@Table(name="EHFM_MEDCO_DTLS")
public class EhfmMedcoDtls implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmMedcoDtlsId id;
	private String activeYN;
	private Date crtDt;
	private String crtUsr;
	private Date endDate;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String scheme;

    public EhfmMedcoDtls() {
    }


	@EmbeddedId
	public EhfmMedcoDtlsId getId() {
		return this.id;
	}

	public void setId(EhfmMedcoDtlsId id) {
		this.id = id;
	}
	

	@Column(name="ACTIVE_YN")
	public String getActiveYN() {
		return this.activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}


    @Temporal( TemporalType.DATE)
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


    @Temporal( TemporalType.DATE)
	@Column(name="END_DT")
	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

    @Temporal( TemporalType.DATE)
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


	public String getScheme() {
		return this.scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

}