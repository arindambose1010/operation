package com.ahct.workflow.common.service;

import java.util.ArrayList;
import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.EhfmGrpWorkFlow;
import com.ahct.model.EhfmWorkFlowGrpMpg;

public class WorkFlowCommonServiceImpl implements WorkFlowCommonService{
	
	private GenericDAO genericDao ;
	
	
	
	public GenericDAO getGenericDao() {
		return genericDao;
	}



	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	/**
     * ;
     * @param String workflowId
     * @param String grpId
     * @return String lastPersonInHierarchy
     * @function This method is used to find out if the group is last in hierarchy for a work flow.
     */
	public String findWhetherLastInHierarchy(String workflowId,List<LabelBean> grpList){
		
		String lastPersonInHierarchy="false";
	
		 List<EhfmGrpWorkFlow> lStEhfmGrpWorkFlow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
		 lStEhfmGrpWorkFlow=genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	
		 if (lStEhfmGrpWorkFlow != null && lStEhfmGrpWorkFlow.size() > 0) {
				
				int levelId=lStEhfmGrpWorkFlow.get(0).getId().getGrpLevel();
				criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", levelId,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfmGrpWorkFlow> listLevel = genericDao.findAllByCriteria(
						EhfmGrpWorkFlow.class, criteriaList);
				if (listLevel != null && listLevel.size() > 0) {
				for(int i=0;i<listLevel.size();i++){
					
					for(int j=0;j<grpList.size();j++){
					
					if ((lStEhfmGrpWorkFlow.get(i).getGrpId())
							.equalsIgnoreCase(grpList.get(j).getID())) {
						
						lastPersonInHierarchy = "true";
						break;
					}
					
					}
				}
				}
			}
		
		return lastPersonInHierarchy;
		
	}
	
	/**
     * ;
     * @param String workflowId
     * @param String buttonValue
     * @return String workFlowId
     * @function This method is used to find out workflow that must be invoked when a action button(Approve/Pending/Reject) is clicked.
     */
	public String getNextWorkFlowId(String workflowId,String buttonValue){
		 
		 String workFlowId=null;
		 EhfmWorkFlowGrpMpg ehfmWorkFlowGrpMpg=genericDao.findById(EhfmWorkFlowGrpMpg.class,String.class,workflowId);
		 if(buttonValue.equalsIgnoreCase("Approve")||buttonValue.equalsIgnoreCase("Submit")||buttonValue.equalsIgnoreCase("Update") || buttonValue.equalsIgnoreCase("recApprove") || buttonValue.equalsIgnoreCase("recAppr")){
			 workFlowId= ehfmWorkFlowGrpMpg.getAprvlWorkFlowId();
		 }
		 if(buttonValue.equalsIgnoreCase("Pending")){
			 
			 workFlowId=ehfmWorkFlowGrpMpg.getPndgWorkFlowId();
		 }
		 if(buttonValue.equalsIgnoreCase("Reject") || buttonValue.equalsIgnoreCase("Dispose")){
			 workFlowId=ehfmWorkFlowGrpMpg.getRjctnWorkFlowId();
			 
		 }
		 
		 return workFlowId;
		 
	 }
	/**
     * ;
     * @param String workflowId
     * @return String currentOwnerGrp
     * @function This method is used to find out the current owner for workflow that was invoked upon clicking a action button(Approve/Pending/Reject).
     */

	 public String getCurrenttOwnerForNewWorkFlow(String workflowId){
		 String currentOwnerGrp=null;
		 List<EhfmGrpWorkFlow> lStEhfmGrpWorkFlow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", "",
					GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 lStEhfmGrpWorkFlow=genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	
		 if(lStEhfmGrpWorkFlow.size()>0){
			 currentOwnerGrp= lStEhfmGrpWorkFlow.get(0).getGrpId();
		 }
		 return currentOwnerGrp;
	 }
	 
	 /**
	     * ;
	     * @param String workflowId
	     * @param String grpId
	     * @return Integer level
	     * @function This method is used to find out the level of a particular group for a given work flow
	     */
 public Integer getGroupHierarchy(String workflowId,String grpId){
		 
		 int level=0;
		List<EhfmGrpWorkFlow> lStEhfmGrpWorkFlow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("grpId", grpId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEhfmGrpWorkFlow=genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if(lStEhfmGrpWorkFlow.size()>0){
			 level=lStEhfmGrpWorkFlow.get(0).getId().getGrpLevel();
			 
		 }
		
		return level;
		 
	 }
 /**
  * ;
  * @param String workflowId
  * @param String grpLevel
  * @return String currentOwnerGrp
  * @function This method is used to find out the current owner for a particular workflow upon forward action
  */
 public String getCurrentOwnerforForward(String workflowId,Integer grpLevel){
	 String currentOwnerGrp=null;
	 List<EhfmGrpWorkFlow> lStEhfmGrpWorkFlow=null;
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", grpLevel,
				GenericDAOQueryCriteria.CriteriaOperator.GT));
	 criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", grpLevel,
				GenericDAOQueryCriteria.CriteriaOperator.ASC));
	
	 lStEhfmGrpWorkFlow=genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
	 criteriaList.removeAll(criteriaList);	 
	 if(lStEhfmGrpWorkFlow.size()>0){
		 
		 currentOwnerGrp= lStEhfmGrpWorkFlow.get(0).getGrpId();
	 }
	
	 return currentOwnerGrp;
	 
	 
 }
}
