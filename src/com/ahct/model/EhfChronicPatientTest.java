package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_CHRONIC_PATIENT_TESTS database table.
 * 
 */
@Entity
@Table(name="EHF_CHRONIC_PATIENT_TESTS")
public class EhfChronicPatientTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long sno;

	@Column(name="ATTACH_PATH")
	private String attachPath;

	@Column(name="CHRONIC_ID")
	private String chronicId;
	
	@Column(name="CHRONIC_NO")
	private String chronicNo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="TEST_ID")
	private String testId;
	
	@Column(name="TEST_NAME")
	private String testName;

    public EhfChronicPatientTest() {
    }

	public long getSno() {
		return this.sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public String getAttachPath() {
		return this.attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}

	public String getChronicId() {
		return this.chronicId;
	}

	public void setChronicId(String chronicId) {
		this.chronicId = chronicId;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getTestId() {
		return this.testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getChronicNo() {
		return chronicNo;
	}

	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	

}