package com.ahct.medicalAudit.form;

import java.util.List;

import org.apache.struts.action.ActionForm;


import com.ahct.medicalAudit.valueobject.MedicalAuditVO;


public class MedicalAuditForm extends ActionForm{

	
	private String selectionType;
	private String distName;
	private String hospType;
	private String groupType;
	private String hospName;
	private String speciality;
	private String category;
	private String procedure;
	private String audited;
	private String caseNumber;
	private String claimNumber;
	private String finalRemarks;
	private String caseId;
	private List<MedicalAuditVO> auditList;
    private String workFlowRemarks;
    private List<MedicalAuditVO> remarksList;
    private String showScheme;
    private String schemeType;
	private String auditedBy;
	private String fromDate;
	private String toDate;
	private String specialityDen;
	
	
	
	
	
	
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getShowScheme() {
		return showScheme;
	}
	public void setShowScheme(String showScheme) {
		this.showScheme = showScheme;
	}
	public List<MedicalAuditVO> getRemarksList() {
		return remarksList;
	}
	public void setRemarksList(List<MedicalAuditVO> remarksList) {
		this.remarksList = remarksList;
	}
	public String getWorkFlowRemarks() {
		return workFlowRemarks;
	}
	public void setWorkFlowRemarks(String workFlowRemarks) {
		this.workFlowRemarks = workFlowRemarks;
	}
	public List<MedicalAuditVO> getAuditList() {
		return auditList;
	}
	public void setAuditList(List<MedicalAuditVO> auditList) {
		this.auditList = auditList;
	}
	public String getSelectionType() {
		return selectionType;
	}
	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getAudited() {
		return audited;
	}
	public void setAudited(String audited) {
		this.audited = audited;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getFinalRemarks() {
		return finalRemarks;
	}
	public void setFinalRemarks(String finalRemarks) {
		this.finalRemarks = finalRemarks;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getAuditedBy() {
		return auditedBy;
	}
	public void setAuditedBy(String auditedBy) {
		this.auditedBy = auditedBy;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSpecialityDen() {
		return specialityDen;
	}
	public void setSpecialityDen(String specialityDen) {
		this.specialityDen = specialityDen;
	}
	
	
}
