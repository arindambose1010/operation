package com.ahct.login.service;

import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.login.vo.LoginDtlsVO;
import com.ahct.login.vo.MenuVo;


public interface LoginService {
public boolean authenticateUser (String userID, String password);
public EmployeeDetailsVO checkEmpDetails(String pStrUserName, String pStrPassword);
public List<MenuVo> getRecSubMenuListForEMP(String pStrParntModId,List<String> grpIdList, String pStrUserId, String pStrLangId);
public String getTheme(String pStrUserRole);
public String getPhoneNumber(String pStrUserName);
public boolean saveTheme(String pStrTheme,String pStrUserRole);
public List<LabelBean> getMainMods(String pStrEmpId);
public String generateOTPAndSendPswdSms(EmployeeDetailsVO employeeDetailsVO);
/*
public int checkPasswordExpiryCheck(String lStrUserId);
public boolean saveTheme(String pStrTheme,String pStrUserRole);
public String getPhoneNumber(String pStrUserName);
public String saveComplaintForm(EmployeeVO employeeVO);
public String getSequenceNextVal(String seqIdentifier);
public List<LabelBean> getAllPendingComplaints();
public LabelBean getComplaintInfo(String complaintId);
public Map<String,String> submitRemarksForComplaint(String complaintId,String remarks);
public List<LabelBean> getAllComplaintsHistory(String emporPenId);*/
List<EmployeeDetailsVO> getContactInfoForEhfmUsers(String login);
public List<EmployeeDetailsVO> checkCasesForClinicalNotes(String userId);

public List<LabelBean> getUserMessage(String userId,String moduleId);
public List<LabelBean> getGroupMessage(List grpId,String moduleId);
public List<LabelBean> getHospitalMessage(String hospId,String grpId,String moduleId);
public String getHospitslId(String userId,String grpId);
public String getMedcoScheme(String userId);

/*
 * Added for User Service Flag Validation after 
 * Successful Open AM Authentication
 */
public boolean authenticateUserServiceFlagForOpenAM (String userID);

/*
 * Added for Checking Hospital Type 
 */
public String checkHospType(String userID);
public String getNimsMedco(String userid);
public boolean checkNimsMedco(String lStrUserId);
public String getNimsMedcoDoc(String caseId);

public EmployeeDetailsVO saveLoginDtls(LoginDtlsVO vo);
public EmployeeDetailsVO saveLogOutDtls(LoginDtlsVO vo);
public int getVisitorCount(String userid);
public String getorgFlag(String caseId);

}

