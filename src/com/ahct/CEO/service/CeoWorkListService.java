package com.ahct.CEO.service;

import java.util.List;
import java.util.Map;

import com.ahct.CEO.vo.EdcHospitalListVO;
import com.ahct.CEO.vo.EdcRequestRemarksVO;
import com.ahct.CEO.vo.UtilityVO;
import com.ahct.CEO.vo.EdcVO;
import com.ahct.CEO.vo.EmpanelHospVO;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.common.vo.LabelBean;

public interface CeoWorkListService {

	 public List<EmpanelHospVO> getCeoWorkList(String lStrUserState,String lStrMenu);
	 public boolean saveDtls(Map lParamMap);
	 public boolean saveOthrStDtls(Map lParamMap);
	 public List<EdcVO> getEdcCeoWorklist(String ceoGrp,String scheme);
	 public String saveEdsCeoDtls(String actionIDList,String btnType,String userId);
	 public boolean updateCeoStatus(String btnType,String userId,String actionAndHospList,String scheme);
	 public String updateHospitalStatus(String actionID,String actionType, String hospId,
				String buttonValue, List<UtilityVO> spltyList1, String userName,String scheme);
	public List<UtilityVO> getSpltyDempanelList(String actionId);
	public int operationsWorkflow(String userGrp,String Scheme);
	public List<EmpanelHospVO> getHospDtls(String lStrHospInfoId);
	public Number getDutyDctrsCnt(String lStrHospInfoId);	
	public Number getParamdcsCnt(String lStrHospInfoId);
	public Number getSplstsCnt(String lStrHospInfoId);
	public  List<AttachmentVO> viewAttachments(String crRefNo); 		
	public String getHospLocation(String hospId);
	public String getTypeOfCR(String actionId);
	public Integer getGroupHierarchy(String workflowId,String grpId);
	public List<List<EdcRequestRemarksVO>> getPreviousRemarks(String actionId,
				Integer grpLevel);		
	public String getHospSplties(String hospId); 
				public String getHospType(String hospId);
	public String getActionType(String actionId);
	public List<UtilityVO> getArticleList(String actionId);
	public List<EdcRequestRemarksVO> getEdcActionDtls(String actionId);
	public String verifyIfMedco(String userId);
}
