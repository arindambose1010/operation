package com.ahct.preauth.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Map;



//import com.ahct.preauth.vo.CmaAuditVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfPatientDocAttach;

public interface PreauthDtlsDao {

	public List<CommonDtlsVO> getPatientCommonDtls(String caseId) throws Exception;	
	public List<EhfPatientDocAttach> getOnBedPhotoDtls(String patId) throws Exception;
	//public Map getPatientOP(HashMap lParamMap);
	//public Map getFraudCrDtls(String pStrCaseId);
	//public Map saveFraudCrDtls(CmaAuditVO cmaVo);
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId);
	public List<PreauthVO> getCasesWorkList(String caseId);
	public String getEmpNameById(String id);
	public String getDoctorById(String id);
	public String getHospName(String hospId)throws Exception;
	public PatientVO retrieveCardDetails(PatientVO patientVo) throws Exception;
	public List<LabelBean> getDrugSubList(String drugTypeCode);
	public CasesSearchVO getCaseCommonDtls(String caseId);
	public String getDrugDetails(String drugCode);
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo);
	public String getPatientScheme(String lStrCaseId);
	
	/**
	 * @author Kalyan
	 * @param ClassPath-Result class after Query Execution and Query
	 * @method This method is used to Execute HQL Query in Dao
	 * @return Map That Contains Result class path and Result Object  
	 */
	 public Map<String,Object> executeNormalQuery(String classPath,String query);
}
