package com.ahct.workflow.common.service;

import java.util.List;

import com.ahct.common.vo.LabelBean;

public interface WorkFlowCommonService {
	
	
	public String findWhetherLastInHierarchy(String workflowId,List<LabelBean> grpList);
	public String getNextWorkFlowId(String workflowId,String buttonValue);
	 public String getCurrenttOwnerForNewWorkFlow(String workflowId);
	 public Integer getGroupHierarchy(String workflowId,String grpId);
	 public String getCurrentOwnerforForward(String workflowId,Integer grpLevel);

}
