package com.ahct.medicalAudit.dao;

import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.medicalAudit.valueobject.MedicalAuditVO;

public interface MedicalAuditDao {

	
	public List<LabelBean> getDistricts();
	public List<LabelBean> getSpecialities();
	public List<LabelBean> getCategories(String specialityType);
	public List<LabelBean> getProcedures(String categoryType);
	public List<LabelBean> getHospitals(String districtCode,String hospType);
    public List<LabelBean> getQuestionAnswers(String caseId);
    public List<MedicalAuditVO> getSampleCasesForAuditNew(MedicalAuditVO medicalAuditVO );
}
