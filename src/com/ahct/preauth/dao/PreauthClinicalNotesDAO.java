package com.ahct.preauth.dao;


import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;


public interface PreauthClinicalNotesDAO {
    //public Long getClinicalNotesCOUNT(String pStrCaseId,String pStrNotesType)throws Exception ;
    //public List<PreauthClinicalNotesVO> getClinicalNotesForCase(String pStrCaseId,String pStrNotesType) throws Exception;
    public List<PreauthClinicalNotesVO> getTherapyDetails(String pStrCaseId,String pStrNotesType) throws Exception;
    public String saveClinicalNotes(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId);
    public boolean saveSurgeryDetails(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserRole,String pStrUserId) throws Exception;
    public String getTelephneIntimationDate(String pStrCaseId) throws Exception;
    public boolean saveDischargeSummaryDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId,String pStrUserRole,String pStrDisDeathFlag) throws Exception;
    public Long getPostOPCheckForSurgery(String pStrCaseId) throws Exception;
    public boolean saveAttachments(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrUserId) throws Exception;
    public Long getAttachmentsCntByType(String pStrCaseId,String pStrAttchType) throws Exception;
    //public List<AsritCaseClinicalDose> getAdditionlCliniclDtls(String pStrClinicalId,String pStrDoseFor);
    public Long checkForDeathDate(String pStrPatientId)throws Exception;
    public Long getPhaseStatus(String pStrCaseId)throws Exception;
    public String getServerDate(String pStrDtFormat) throws Exception;
    public String getFollowUpFlag(String pStrCaseId) throws Exception;
    public String getMedMngmtFlag(String pStrCaseId)throws Exception;
    public String getMedicalFlag(String pStrCaseId);
    public List<PreauthClinicalNotesVO> getDisSumDtls(PreauthClinicalNotesVO preauthClinicalNotesVO,String pStrCaseId,String pStrNotesType) throws Exception;
    public LabelBean getHospitalDetails(String pStrCaseId) throws Exception;
    public LabelBean getUserDetails(String pStrUserId) throws Exception;
    public List<LabelBean> getOpDrugSubList(String drugTypeCode,String pStrIpOpType);
	public List<LabelBean> getOpPharSubList(String drugTypeCode,String pStrIpOpType);
	public List<LabelBean> getOpChemSubList(String pharSubCode,String pStrIpOpType);
	public List<LabelBean> getChemSubList(String cSubGrpCode,String pStrIpOpType);
	public List<LabelBean> getRouteList(String routeTypeCode,String pStrIpOpType);
	public List<LabelBean> getStrengthList(String strengthTypeCode,String pStrIpOpType);
}