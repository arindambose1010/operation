package com.ahct.preauth.vo;

import java.io.Serializable;

public class CaseTherapyVO implements Serializable{

	private String diagnosisCategory;
	private String therapyType;
	private String therapyCategory;
	private String therapy;
	private String diagnosisSubCategory;
	private Long caseTherapyId;
	private String asriCatCode;
	private String ICDCatCode;
	private String ICDProcCode;
	private String docRegNum;
	private String docName;
	private String docQual;
	private String docMobileNo;
	private String procUnits;
	
	public Long getCaseTherapyId() {
		return caseTherapyId;
	}
	public void setCaseTherapyId(Long caseTherapyId) {
		this.caseTherapyId = caseTherapyId;
	}
	public String getDiagnosisCategory() {
		return diagnosisCategory;
	}
	public void setDiagnosisCategory(String diagnosisCategory) {
		this.diagnosisCategory = diagnosisCategory;
	}
	public String getTherapyType() {
		return therapyType;
	}
	public void setTherapyType(String therapyType) {
		this.therapyType = therapyType;
	}
	public String getTherapyCategory() {
		return therapyCategory;
	}
	public void setTherapyCategory(String therapyCategory) {
		this.therapyCategory = therapyCategory;
	}
	public String getTherapy() {
		return therapy;
	}
	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}
	public String getDiagnosisSubCategory() {
		return diagnosisSubCategory;
	}
	public void setDiagnosisSubCategory(String diagnosisSubCategory) {
		this.diagnosisSubCategory = diagnosisSubCategory;
	}
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}
	public String getICDCatCode() {
		return ICDCatCode;
	}
	public void setICDCatCode(String iCDCatCode) {
		ICDCatCode = iCDCatCode;
	}
	public String getICDProcCode() {
		return ICDProcCode;
	}
	public void setICDProcCode(String iCDProcCode) {
		ICDProcCode = iCDProcCode;
	}
	public String getDocRegNum() {
		return docRegNum;
	}
	public void setDocRegNum(String docRegNum) {
		this.docRegNum = docRegNum;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocQual() {
		return docQual;
	}
	public void setDocQual(String docQual) {
		this.docQual = docQual;
	}
	public String getDocMobileNo() {
		return docMobileNo;
	}
	public void setDocMobileNo(String docMobileNo) {
		this.docMobileNo = docMobileNo;
	}
	public String getProcUnits() {
		return procUnits;
	}
	public void setProcUnits(String procUnits) {
		this.procUnits = procUnits;
	}
	
}
