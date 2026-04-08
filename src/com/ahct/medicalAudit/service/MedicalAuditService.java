package com.ahct.medicalAudit.service;

import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.medicalAudit.valueobject.MedicalAuditVO;


public interface MedicalAuditService {
	
	
	public List<LabelBean> getDistricts();
	public List<LabelBean> getSpecialities();
	public List<LabelBean> getCategories(String specialityType);
	public List<LabelBean> getProcedures(String categoryType);
	public List<LabelBean> getHospitals(String districtCode,String hospType);
	public void saveMedicalAuditAnswers(MedicalAuditVO medicalAuditVO);
	public List<LabelBean> getQuestionAnswers(String caseId);
	public List<MedicalAuditVO> getSampleCasesForAuditNew(MedicalAuditVO medicalAuditVO );
	public List<MedicalAuditVO> auditCasesForDisplay(List<MedicalAuditVO> auditSampleCases);
	public String findAuditAlreadyStarted(String caseId,String userId);
	public List<MedicalAuditVO> getMedicalAuditWorklist(MedicalAuditVO medicalAuditVO);
	public String fetchRemarks(String caseId);
	public List<MedicalAuditVO> getPreviousRemarks(String caseId);
	 public String getCurrentWorkFlowId(String caseId);
	 public String getPreviousOwnerGrp(String workflowId);
	 public void saveMedAuditWrkFlowDtls(MedicalAuditVO medicalAuditVO);
	 public List<MedicalAuditVO> getAuditedCasesList(MedicalAuditVO medicalAuditVO);

}
