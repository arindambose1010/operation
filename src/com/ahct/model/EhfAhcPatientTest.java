package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_AHC_PATIENT_TESTS database table.
 * 
 */
@Entity
@Table(name="EHF_AHC_PATIENT_TESTS")
public class EhfAhcPatientTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private long sno;
	private String attachPath;
	private Date crtDt;
	private String crtUsr;
	private String investPrice;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String patientId;
	private String testId;
	private String testName;

    public EhfAhcPatientTest() {
    }


	@Id
	public long getSno() {
		return this.sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}


	@Column(name="ATTACH_PATH")
	public String getAttachPath() {
		return this.attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
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


	@Column(name="INVEST_PRICE")
	public String getInvestPrice() {
		return this.investPrice;
	}

	public void setInvestPrice(String investPrice) {
		this.investPrice = investPrice;
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


	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	@Column(name="TEST_ID")
	public String getTestId() {
		return this.testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}


	@Column(name="TEST_NAME")
	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

}