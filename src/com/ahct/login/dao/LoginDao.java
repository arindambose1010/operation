package com.ahct.login.dao;

import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.login.vo.MenuVo;



public interface LoginDao {
public List<MenuVo> getActionBarModulesForEMP(String pStrUsrID,String pStrParentModId,String pStrLangId);
/*public List<LabelBean> getAllPendingComplaints();
public LabelBean getComplaintInfo(String complaintId);
public List<LabelBean> getAllComplaintsHistory(String emporPenId);*/
public List<EmployeeDetailsVO> checkCasesForClinicalNotes(String userId);
public List<LabelBean> getUserMessage(String userId,String moduleId);
public List<LabelBean> getGroupMessage(List grpId,String moduleId);
public List<LabelBean> getHospitalMessage(String hospId,String grpId,String moduleId);

public String getHospitslId(String userId,String grpId);

 }
