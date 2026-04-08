package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_DRUG_PRIC_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_DRUG_PRIC_MST")
public class EhfmChronicDrugPricMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmChronicDrugPricMstPK id;
	private Date crtDt;
	private String crtUsr;
	private String drugName;
	private Date endDate;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private Date startDate;
	private String unitPrice;

    public EhfmChronicDrugPricMst() {
    }


	@EmbeddedId
	public EhfmChronicDrugPricMstPK getId() {
		return this.id;
	}

	public void setId(EhfmChronicDrugPricMstPK id) {
		this.id = id;
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


	@Column(name="DRUG_NAME")
	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="END_DATE")
	public Date getEndDate() {
		return this.endDate;
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


    @Temporal( TemporalType.DATE)
	@Column(name="START_DATE")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	@Column(name="UNIT_PRICE")
	public String getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

}