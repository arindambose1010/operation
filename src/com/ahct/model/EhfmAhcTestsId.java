package com.ahct.model;

import java.io.Serializable;
import javax.persistence.Column;

public class EhfmAhcTestsId implements Serializable {


	private String testCode;
	private String testSubCode;
	
	
	public EhfmAhcTestsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmAhcTestsId(String testCode, String testSubCode) {
		super();
		this.testCode = testCode;
		this.testSubCode = testSubCode;
	}

	
	@Column(name="TEST_CODE")
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	
	@Column(name="TEST_SUB_CODE")
	public String getTestSubCode() {
		return testSubCode;
	}
	public void setTestSubCode(String testSubCode) {
		this.testSubCode = testSubCode;
	}
	
	}