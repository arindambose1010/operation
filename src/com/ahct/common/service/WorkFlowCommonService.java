package com.ahct.common.service;

public interface WorkFlowCommonService {
	
	
	public String findWhetherLastInHierarchy(String workflowId,String grpId);
	public String getNextWorkFlowId(String workflowId,String buttonValue);
	 public String getCurrenttOwnerForNewWorkFlow(String workflowId);
	 public Integer getGroupHierarchy(String workflowId,String grpId);
	 public String getCurrentOwnerforForward(String workflowId,Integer grpLevel);

}
