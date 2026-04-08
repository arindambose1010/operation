package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHFM_AHC_TESTS")
public class EhfmAhcTests implements java.io.Serializable {
	
	private EhfmAhcTestsId id;
	
	private String testDescription;
	private String testSubDescrip;
	private String testSubSeq;
	private String activeYn;
	private String langId;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	
	
	public EhfmAhcTests() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmAhcTests(EhfmAhcTestsId id,String testDescription, 
			String testSubDescrip,String testSubSeq,String activeYn,String langId,
			Date crtDt, String crtUsr,Date lstUpdDt, String lstUpdUsr) {
		super();
		this.id = id;
		this.testDescription=testDescription;
		this.testSubDescrip = testSubDescrip;
		this.testSubSeq = testSubSeq;
		this.crtUsr = crtUsr;
		this.activeYn = activeYn;
		this.langId = langId;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.crtDt = lstUpdDt;
		this.crtUsr = lstUpdUsr;
	}

	
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "testCode", column = @Column(name = "TEST_CODE", nullable = false, length = 20)),
		@AttributeOverride(name = "testSubCode", column = @Column(name = "TEST_SUB_CODE", nullable = false, length = 15))
		})
		
    public EhfmAhcTestsId getId() {
	return id;
	}

	public void setId(EhfmAhcTestsId id) {
	this.id = id;
	}
	
	
	@Column(name="TEST_DESCRIP")
	public String getTestDescription() {
		return testDescription;
	}
	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
	@Id
	
	@Column(name="TEST_SUB_DESCRIP")
	public String getTestSubDescrip() {
		return testSubDescrip;
	}
	public void setTestSubDescrip(String testSubDescrip) {
		this.testSubDescrip = testSubDescrip;
	}
	@Column(name="TEST_SUB_SEQ")
	public String getTestSubSeq() {
		return testSubSeq;
	}
	public void setTestSubSeq(String testSubSeq) {
		this.testSubSeq = testSubSeq;
	}
	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="LANG_ID")
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR")
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
	

	
}
