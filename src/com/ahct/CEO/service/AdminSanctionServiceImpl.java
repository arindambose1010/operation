package com.ahct.CEO.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.CEO.vo.AdminSanctionRemarksVO;
import com.ahct.CEO.vo.AdminSanctionVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.AccountsConstants;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.AsritAccountsDocAttch;
import com.ahct.model.AsritAdminSanctionData;
import com.ahct.model.EhfAccountsWorkflow;
import com.ahct.model.EhfAdminSanctionAudit;
import com.ahct.model.EhfAdminSanctionAuditid;
import com.ahct.model.EhfAdminSanctionsMetaData;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfmGrpWorkFlow;

public class AdminSanctionServiceImpl implements AdminSanctionService {
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	CommonService commonService;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	public static final String EMPTY_STRING = "";
	static 
	{
		configurationService = ConfigurationService.getInstance(); 
		config = configurationService.getConfiguration();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	/**
     * @return String departmentsList
     * @function This method is used to retrieve all the departments
     */
	@Override
	public List<LabelBean> getDeptDetails() {
		List<LabelBean> deptList = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT deptId as ID, deptName as VALUE FROM EhfmDepts where deptType='CD201' order by deptName");
		deptList = genericDao.executeHQLQueryList(LabelBean.class,
				query.toString());
		return deptList;
	}
	
	/**
	 * @param String userState
     * @return Scheme List
     * @function This method is used to get the scheme list based on userState
     */
	@Override
	public List<LabelBean> getSchemeList(String lStrUserState) {
		List<LabelBean> scList= new ArrayList<LabelBean>();
			StringBuffer query= new StringBuffer();
			String[] args=null;
			try
			{
			query.append("Select schemeId as ID, schemeName as VALUE from EhfmAcctsSchemes where stateCode in (?,?) and activeYn=? ");
			args=new String[3];
			args[0]=config.getString("ACC.APState");
			args[1]=config.getString("ACC.TGState");
			args[2]="Y";
			scList=genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return scList;
	}

	@Override
	/**
	 *  @param String schemeId
     * @return detailed head list
     * @function This method is used to get the detailed heads list based on scheme
     */
	public List<LabelBean> getDetailedHeadList(String lstrSchemeId) {
		List<LabelBean> detailedHeadsList= new ArrayList<LabelBean>();
		StringBuffer query= new StringBuffer();
		String[] args=null;
		try
		{
		query.append("select accountCode as ID,accountCode as VALUE from EhfmAcctsHeads where headType='Detailed' and activeYn=? and id.scheme=?");
		args=new String[2];
		args[1]=lstrSchemeId;
		args[0]="Y";
		detailedHeadsList=genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return detailedHeadsList;
	}
	
	/**
	 * @param String lstrSchemeId,String detailedHead
     * @return head details string
     * @function This method is used to get the detailed heads string  which contains sub head code name,major head code name
     */
	@Override
	public String getHeadsDetails(String lstrSchemeId, String detailedHead)
	{
		List<AdminSanctionVO> detailedHeadNameList= new ArrayList<AdminSanctionVO>();
		StringBuffer query;
		String[] args=null;
		String headParentId=null;
		String detailsString=null;
		String detailedHeadName=null;
		String subHead=null;
    	String subHeadName=null;
    	 String majorHead=null;
    	 String majorHeadName=null;
		//To get Detailed Head Name
		try
		{
			query = new StringBuffer();
		query.append("select headName as detailedHeadName, headParentId as headId from EhfmAcctsHeads where accountCode=? and id.scheme=?");
		args=new String[2];
		args[1]=lstrSchemeId;
		args[0]=detailedHead;
		detailedHeadNameList=genericDao.executeHQLQueryList(AdminSanctionVO.class,query.toString(),args);
		  if(detailedHeadNameList.size()>0)
	        {
	        	 detailedHeadName=detailedHeadNameList.get(0).getDetailedHeadName();
	        	headParentId=detailedHeadNameList.get(0).getHeadId();
	        }
		//To get Sub Head Details
		if(headParentId!=null)
		{
            query = new StringBuffer();
			query.append("select headCode as subHead,headName as subHeadName,headParentId as headId  from EhfmAcctsHeads where id.headId=? and  headType='Minor' and id.scheme=?");
			args=new String[2];
			args[1]=lstrSchemeId;
			args[0]=headParentId;
			detailedHeadNameList=genericDao.executeHQLQueryList(AdminSanctionVO.class,query.toString(),args);
			  if(detailedHeadNameList.size()>0)
		        {
				   subHead=detailedHeadNameList.get(0).getSubHead();
		        	subHeadName=detailedHeadNameList.get(0).getSubHeadName();
		        	headParentId=detailedHeadNameList.get(0).getHeadId();
		         }
		}
		if(headParentId!=null)
		{
			query = new StringBuffer();
			query.append("select headCode as majorHead,headName as majorHeadName  from EhfmAcctsHeads where id.headId=? and  headType='Major' and id.scheme=?");
			args=new String[2];
			args[1]=lstrSchemeId;
			args[0]=headParentId;
			detailedHeadNameList=genericDao.executeHQLQueryList(AdminSanctionVO.class,query.toString(),args);
			  if(detailedHeadNameList.size()>0)
		        {
				   majorHead=detailedHeadNameList.get(0).getMajorHead();
		        	majorHeadName=detailedHeadNameList.get(0).getMajorHeadName();
		        }
			}
		      detailsString=detailedHeadName+"~"+subHead+"~"+subHeadName+"~"+majorHead+"~"+majorHeadName;
		     
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return detailsString;
}
	/**
     * @return vendors list
     * @function This method is used to get the vendors list
     */
	@Override
	public List<LabelBean> getVendorList() {
		
		 List<LabelBean> vendorList = null;
	        StringBuffer lsb = new StringBuffer () ;   
	       
       try
	      {
	          lsb.append ( " select vm.vendor_id ID,vm.vendor_name VALUE ");
	          lsb.append ( " FROM EHFM_VENDOR_MASTER vm");
	          lsb.append ( " where ACTIVE_YN = 'Y' ");
	          
	          vendorList = genericDao.executeSQLQueryList(LabelBean.class,lsb.toString());
	      }
	      catch ( Exception e )
	      {
	    	  e.printStackTrace();
	       
	      }
	      return vendorList ;
	}
	@Override
	/**
	 * @param stateFlag
     * @return budget source list
     * @function This method is used to get the budget source list based on the scheme of user 
     */
	public List<LabelBean> getBudgetSourceList(String stateFlag) {
		
		    List<LabelBean> budgetSourceList = null;
	        StringBuffer lsb = new StringBuffer () ;   
	        String[] args=null;
	        
       try
	      {
	          lsb.append ( " select DTL_VAL as ID,DTL_DESC as VALUE ");
	          lsb.append ( " FROM EHFM_BUDGET_DTLS where DTL_TYP='S' and STATE_FLAG in ( ");
	          
	          if(stateFlag.equals(config.getString("ACC.APTGState")))
	          {
	        	  lsb.append ( " ?,? )");
	        	  args=new String[2];
	        	  args[0]=config.getString("ACC.APState");
	        	  args[1]=config.getString("ACC.TGState");
	          }
	          else 
	          {
	        	  lsb.append (" ? )");
	        	  args=new String[1];
	        	  args[0]=stateFlag;
	          }
	          budgetSourceList = genericDao.executeSQLQueryList(LabelBean.class,lsb.toString(),args);
	      }
	      catch ( Exception e )
	      {
	    	  e.printStackTrace();
	       
	      }
	      return budgetSourceList ;
	}
	@Override
	/**
	 * @param String issuingAuthority
     * @return issuing authority name
     * @function This method is used to get the issuing authority name based on issuingAuthority
     */
	public List<LabelBean> getIssuingAuthName(String issuingAuthority,String lStrUserState)
	{
		 List<LabelBean> issuingAuthNameList = null;
	        StringBuffer lsb = new StringBuffer () ;  
	        String[] args = null;
    try
	      {
    	args=new String[1];

		String[] dsgnArray=config.getString("ACC.CEO_EO").split("~");
		if(issuingAuthority!=null && issuingAuthority.equals(config.getString(("ACC.CEO"))))
			args[0]=dsgnArray[1];
	    	else if(issuingAuthority!=null && issuingAuthority.equals("EO_ADMIN"))
	    		args[0]=dsgnArray[2];
	          lsb.append ( " select firstName||''||lastName as ID ,firstName||''||lastName as VALUE");
	          lsb.append ( " FROM EhfmUsers");
	          lsb.append ( " where serviceFlag = 'Y' and dsgnId=?");
	          
	          if(!lStrUserState.equalsIgnoreCase(config.getString("ACC.APTGState"))){
	        	  lsb.append(" and userType='"+lStrUserState+"'");
	 			
	 		 }
	          issuingAuthNameList = genericDao.executeHQLQueryList(LabelBean.class,lsb.toString(),args);
	      }
	      catch ( Exception e )
	      {
	    	  e.printStackTrace();
	       
	      }
	    
		return issuingAuthNameList;
	      
	      
	}

	@Override
	/**
     * @param String userType
     * @return String sanctionId
     * @function This method is used to get the Sanction id for admin sanction based on usertype
     */
 public String getSanctionId(String userType){
	 
	 String sancId = null;
	 sancId = getSanctionSequence(AccountsConstants.SANCTION_SEQ,AccountsConstants.ADMIN_SANCTION_SEQ,userType);             
         return sancId;
	 
 }
 public String getSanctionSequence(String pStrSeqIdentifier, String pStrSeqName, String userType){
	 
	 String lStrSeqRetVal = EMPTY_STRING; 
	 String currYear = EMPTY_STRING; 
	     
	    
	    try{
	     
	    	StringBuffer query = new StringBuffer();
	    	query.append(" select to_char(sysdate, 'YYYY') ID from dual ");
	        List<LabelBean> year= genericDao.executeSQLQueryList(LabelBean.class,query.toString());
	        if(year!=null && year.size()>0)
	        	currYear= year.get(0).getID();
	        query = new StringBuffer();
	    	query.append(" SELECT "+pStrSeqName+".NEXTVAL COUNT  FROM DUAL ");
	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

	        if(seqList != null){
	        	
	          lStrSeqRetVal = userType+pStrSeqIdentifier +currYear+"/"+ seqList.get(0).getCOUNT();
	        }
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }
	 return lStrSeqRetVal;
	 
	 
	 
	 
	 
 }
 
@Override
/**
 * @param String workflowId
 * @return String grpId
 * @function This method is used to retrieve the group id of corresponding workflow id
 */
public String getGrpId(String workflowId){
	 String grpId=null;
	 List<EhfmGrpWorkFlow> lStEhfmGrpWorkFlow=null;
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId", workflowId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 criteriaList.add(new GenericDAOQueryCriteria("id.grpLevel", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
	 lStEhfmGrpWorkFlow=genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
	 criteriaList.removeAll(criteriaList);	
	 if (lStEhfmGrpWorkFlow != null && lStEhfmGrpWorkFlow.size() > 0) {
			
			grpId=lStEhfmGrpWorkFlow.get(0).getGrpId();
}
	 return grpId;
}

@Override
@Transactional
/**
 * @param AdminSanctionVO adminSanctionVO
 * @return String successFlag
 * @function This method is used to save and submit the details of admin sanction form 
 */
public String saveSanctionDetails(AdminSanctionVO adminSanctionVO) {
	
	String msg=null;
	EhfAdminSanctionsMetaData ehfAdminSanctionMetaData =null;
	EhfAccountsWorkflow ehfAccountsWorkFlow=null;
	
	//Saving details into adminsanctionmetadata table
	if(adminSanctionVO.getButtonValue().equalsIgnoreCase("SaveBasic")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Submit")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Update")){
		
		ehfAdminSanctionMetaData=genericDao.findById(EhfAdminSanctionsMetaData.class, String.class,adminSanctionVO.getSanctionId());
		if(ehfAdminSanctionMetaData==null){
			ehfAdminSanctionMetaData=new EhfAdminSanctionsMetaData();
			ehfAdminSanctionMetaData.setSanctionId(adminSanctionVO.getSanctionId());
			ehfAdminSanctionMetaData.setCrtDt(new Timestamp(new Date().getTime()));
			ehfAdminSanctionMetaData.setCrtUsr(adminSanctionVO.getCrtUsr());
		}
		
		else
		{
            ehfAdminSanctionMetaData.setLstUpdUsr(adminSanctionVO.getLstUpdUsr());			
			ehfAdminSanctionMetaData.setLstUpdDt(new Timestamp(new Date().getTime()));
		}
		ehfAdminSanctionMetaData.setIssuingAuthority(adminSanctionVO.getIssuingAuthority());
		ehfAdminSanctionMetaData.setIssuingAuthorityName(adminSanctionVO.getIssuingAuthorityName());
			ehfAdminSanctionMetaData.setFormDate(adminSanctionVO.getDate());
			ehfAdminSanctionMetaData.setSubject(adminSanctionVO.getSubject());
			ehfAdminSanctionMetaData.setDeptName(adminSanctionVO.getDeptName());
			ehfAdminSanctionMetaData.setReference(adminSanctionVO.getReference());
			ehfAdminSanctionMetaData.setScheme(adminSanctionVO.getScheme());
		   
			
			ehfAdminSanctionMetaData.setSanctionAmount(adminSanctionVO.getSanctionAmount());
			ehfAdminSanctionMetaData.setDetailedHead(adminSanctionVO.getDetailedHead());
			ehfAdminSanctionMetaData.setDetailedHeadName(adminSanctionVO.getDetailedHeadName());
			ehfAdminSanctionMetaData.setSubHead(adminSanctionVO.getSubHead());
			ehfAdminSanctionMetaData.setSubHeadName(adminSanctionVO.getSubHeadName());
			ehfAdminSanctionMetaData.setMajorHead(adminSanctionVO.getMajorHead());
			ehfAdminSanctionMetaData.setMajorHeadName(adminSanctionVO.getMajorHeadName());
			ehfAdminSanctionMetaData.setSourceOfBudget(adminSanctionVO.getSourceOfBudget());
			ehfAdminSanctionMetaData.setSourceCode(adminSanctionVO.getSourceCode());
			ehfAdminSanctionMetaData.setSanctionOrderDate(adminSanctionVO.getSanctionOrderDate());
			
			
			ehfAdminSanctionMetaData.setPurchaseDate(adminSanctionVO.getPurchaseDate());
			ehfAdminSanctionMetaData.setInspectionAuthority(adminSanctionVO.getInspectionAuthority());
			ehfAdminSanctionMetaData.setExecutingAuthority(adminSanctionVO.getExecutingAuthority());
			ehfAdminSanctionMetaData.setVendorType(adminSanctionVO.getVendorType());
			ehfAdminSanctionMetaData.setVendorName(adminSanctionVO.getVendorName());
			ehfAdminSanctionMetaData.setVendorCode(adminSanctionVO.getVendorCode());
			ehfAdminSanctionMetaData.setToBeIssuedOn(adminSanctionVO.getToBeIssuedOn());
			
			
			ehfAdminSanctionMetaData.setTypoOfSanction(adminSanctionVO.getTypoOfSanction());
			ehfAdminSanctionMetaData.setSpecification(adminSanctionVO.getSpecification());
			ehfAdminSanctionMetaData.setCost(adminSanctionVO.getCost());
			ehfAdminSanctionMetaData.setDeptName(adminSanctionVO.getDeptName());
			
			
			try {
				ehfAdminSanctionMetaData=genericDao.save(ehfAdminSanctionMetaData);
					msg = "success";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "failure";
				}
	}
			
			//Saving details to work flow table
				ehfAccountsWorkFlow=genericDao.findById(EhfAccountsWorkflow.class, String.class,adminSanctionVO.getSanctionId());
				if(ehfAccountsWorkFlow==null){
					ehfAccountsWorkFlow=new EhfAccountsWorkflow();
					ehfAccountsWorkFlow.setSanctionId(adminSanctionVO.getSanctionId());
					ehfAccountsWorkFlow.setCrtDt(new Timestamp(new Date().getTime()));
				 	ehfAccountsWorkFlow.setCrtUsr(adminSanctionVO.getCrtUsr());
					
				}
				else
				{
					ehfAccountsWorkFlow.setLstUpdUsr(adminSanctionVO.getLstUpdUsr());
				 	ehfAccountsWorkFlow.setLstUpddt(new Timestamp(new Date().getTime()));
				}
		 
			if(adminSanctionVO.getButtonValue().equalsIgnoreCase("Submit")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Update")){
			
				ehfAccountsWorkFlow.setPrevOwnerGrp(adminSanctionVO.getPrevOwner());
				ehfAccountsWorkFlow.setCurrOwnerGrp(adminSanctionVO.getCurrentOwner());
				ehfAccountsWorkFlow.setCurrWorkFlowId(adminSanctionVO.getWorkFlowId());
				ehfAccountsWorkFlow.setStatus("P");
				ehfAccountsWorkFlow.setPrevWorkFlowId(adminSanctionVO.getPrevWorkFlowId());
			
			
			}
			else if(adminSanctionVO.getButtonValue().equalsIgnoreCase("Approve")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Pending")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Reject") ||adminSanctionVO.getButtonValue().equalsIgnoreCase("recApprove")){
				
				 ehfAccountsWorkFlow.setPrevOwnerGrp(adminSanctionVO.getPrevOwner());
				 ehfAccountsWorkFlow.setCurrOwnerGrp(adminSanctionVO.getCurrentOwner());
				 ehfAccountsWorkFlow.setCurrWorkFlowId(adminSanctionVO.getWorkFlowId());
				if(adminSanctionVO.getButtonValue().equalsIgnoreCase("Reject")){
				 ehfAccountsWorkFlow.setStatus("R");
				 }
				else if(adminSanctionVO.getButtonValue().equalsIgnoreCase("recApprove"))
				{
					 ehfAccountsWorkFlow.setStatus("RA");
				}
				else
					ehfAccountsWorkFlow.setStatus("P");
				if((adminSanctionVO.getCurrentOwner()==null||adminSanctionVO.getCurrentOwner()=="")&&(adminSanctionVO.getButtonValue().equalsIgnoreCase("Approve"))){
					
					ehfAccountsWorkFlow.setStatus("A");
				}
				 ehfAccountsWorkFlow.setPrevWorkFlowId(adminSanctionVO.getPrevWorkFlowId());
				
				
			}
			else
			{
		 	ehfAccountsWorkFlow.setPrevOwnerGrp(adminSanctionVO.getGrpId());
		 	ehfAccountsWorkFlow.setCurrOwnerGrp(adminSanctionVO.getGrpId());
			ehfAccountsWorkFlow.setCurrWorkFlowId(adminSanctionVO.getWorkFlowId());
		 	ehfAccountsWorkFlow.setStatus("I");
		 	ehfAccountsWorkFlow.setPrevWorkFlowId("NA");
			}
			
			try {
				ehfAccountsWorkFlow=genericDao.save(ehfAccountsWorkFlow);
				msg = "success";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "failure";
				}
			
			
			
			if(adminSanctionVO.getButtonValue().equalsIgnoreCase("Approve")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Pending")||adminSanctionVO.getButtonValue().equalsIgnoreCase("Reject")||(adminSanctionVO.getButtonValue().equalsIgnoreCase("Submit"))||adminSanctionVO.getButtonValue().equalsIgnoreCase("Update") || adminSanctionVO.getButtonValue().equalsIgnoreCase("recApprove")){
			
			if (msg.equalsIgnoreCase("success")) {
				
				//Final level Approval saving details to admin sanction audit table
				 if((adminSanctionVO.getCurrentOwner()==null||adminSanctionVO.getCurrentOwner()=="")&&(adminSanctionVO.getButtonValue().equalsIgnoreCase("Approve"))){
					 
					  ehfAdminSanctionMetaData=genericDao.findById(EhfAdminSanctionsMetaData.class, String.class,adminSanctionVO.getSanctionId());
                       
					  AsritAdminSanctionData asritAdminSanctionData=new AsritAdminSanctionData();
					  if(ehfAdminSanctionMetaData!=null)
					  {
						  asritAdminSanctionData.setSanctionId(ehfAdminSanctionMetaData.getSanctionId());
						if(adminSanctionVO.getPrevWorkFlowId().equals(config.getString("AdminSanctionEOApproval.WorkFlowId").trim()))
						  asritAdminSanctionData.setStatus("CD2161");
						else
							 asritAdminSanctionData.setStatus("CD2160");
						  asritAdminSanctionData.setAmountRequested(Long.parseLong(ehfAdminSanctionMetaData.getSanctionAmount()));
						  asritAdminSanctionData.setAmountApproved(Long.parseLong(ehfAdminSanctionMetaData.getSanctionAmount()));
						  asritAdminSanctionData.setCrtUsr(adminSanctionVO.getCrtUsr()) ;
						  asritAdminSanctionData.setCrtDt(Calendar.getInstance());
						  asritAdminSanctionData.setVendors(ehfAdminSanctionMetaData.getVendorType());
						  
						  try {
							  asritAdminSanctionData=genericDao.save(asritAdminSanctionData);
								msg = "success";
							} catch (Exception e) {
								e.printStackTrace();
								msg = "failure";
								}
						  
					  }
				 }
				
				//Saving details to audit tables
				EhfAdminSanctionAudit ehfAdminSanctionAudit=new EhfAdminSanctionAudit();
				 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.sanctionId", adminSanctionVO.getSanctionId(),
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfAdminSanctionAudit> ehfAdminSanctionAuditList = genericDao
						.findAllByCriteria(EhfAdminSanctionAudit.class, criteriaList);
				 criteriaList.removeAll(criteriaList);	
				
				int lineNo =ehfAdminSanctionAuditList.size()+1;
				ehfAdminSanctionAudit.setId(new EhfAdminSanctionAuditid(adminSanctionVO.getSanctionId(),lineNo));
				ehfAdminSanctionAudit.setRemarks(adminSanctionVO.getAdminSancRemarks());
				ehfAdminSanctionAudit.setActor(adminSanctionVO.getCrtUsr());
				ehfAdminSanctionAudit.setGrpId(adminSanctionVO.getPrevOwner());
				ehfAdminSanctionAudit.setCurrentWorkFlowId(adminSanctionVO.getPrevWorkFlowId());
				ehfAdminSanctionAudit.setNextWorkFlwId(adminSanctionVO.getWorkFlowId());
			 criteriaList.add(new GenericDAOQueryCriteria("id.sanctionId", adminSanctionVO.getSanctionId(),
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
			
			 List<EhfAdminSanctionAudit> auditList=genericDao.findAllByCriteria(EhfAdminSanctionAudit.class,criteriaList);
			 criteriaList.removeAll(criteriaList);
			
			 
			 if(auditList.size()>0){
				 
				 ehfAdminSanctionAudit.setAssigndDt(auditList.get(0).getActdDt());
			 }
			 else{
				 ehfAdminSanctionAudit.setAssigndDt(new Timestamp(new Date().getTime()));
			 }
			 ehfAdminSanctionAudit.setActdDt(new Timestamp(new Date().getTime()));
			 if((adminSanctionVO.getCurrentOwner()==null||adminSanctionVO.getCurrentOwner()=="")){
					
					if(adminSanctionVO.getButtonValue().equalsIgnoreCase("Reject")){
						
						ehfAdminSanctionAudit.setStatus("R");
					}
					if(adminSanctionVO.getButtonValue().equalsIgnoreCase("Approve")){
						
						ehfAdminSanctionAudit.setStatus("A");
					}
					 
				
				}
				else{
					if(adminSanctionVO.getButtonValue().equalsIgnoreCase("recApprove")){
						ehfAdminSanctionAudit.setStatus("RA");
					}
					else
					ehfAdminSanctionAudit.setStatus("P");
				}
			 try {
				 ehfAdminSanctionAudit=genericDao.save(ehfAdminSanctionAudit);
					msg = "success";
				} catch (Exception e) {
					e.printStackTrace();
					msg = "failure";
					}
			 
			 
			}
	
			}
	
	return msg;
}
/**
 * ; 
 *  @param String sanctionId
 * @return String workFlowId
 * @function Given a sanctionId,this method is used to identify the type of Sanction.
 */
@Override
public String getWorkFlowId(String sanctionId) {
	 String workFlowId=null;
	 List<EhfAccountsWorkflow> lStEhfCrWorkFlow=null;
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("sanctionId", sanctionId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 lStEhfCrWorkFlow=genericDao.findAllByCriteria(EhfAccountsWorkflow.class,criteriaList);
	 criteriaList.removeAll(criteriaList);	 
	 if(lStEhfCrWorkFlow.size()>0){
		 
		 workFlowId=lStEhfCrWorkFlow.get(0).getCurrWorkFlowId();
	 }
	 
	 return workFlowId;
}
/**
 * ; 
 *  @param List<LabelBean> grpList,String state
 * @return List<AdminSanctionVO>
 * @function Retrieves admin sanction requests based on groups and state of the user
 */
@Override
public List<AdminSanctionVO> getAdminSancRequests(List<LabelBean> grplist,String state){
	 
	 
	 Configuration config = configurationService.getConfiguration();
	// String apCode=config.getString("ACC.EHFAPSchemeID");
	// String tgCode=config.getString("ACC.EHFTGSchemeID");
	 
	 if(state.equals(config.getString("ACC.APState")))
		{
     	
		 state =config.getString("ACC.EHFAPSchemeID");
		}
     if(state.equals(config.getString("ACC.TGState")))
		{
    	 state =config.getString("ACC.EHFTGSchemeID");
		}
	 
	 List<AdminSanctionVO> reqList=null;
	 StringBuffer query = new StringBuffer();
	 int i=0;
	 String[] args= new String[grplist.size()];
	 query.append("select  aw.sanctionId as sanctionId, aw.crtDt as crtDt,aw.crtUsr as crtUsr,aw.lstUpdUsr as lstUpdUsr,aw.lstUpddt as lstUpdDt,aw.status as status,md.sanctionAmount as sanctionAmount,aw.currWorkFlowId as currentWorkFlowId,aw.prevWorkFlowId as prevWorkFlowId  from EhfAccountsWorkflow aw,EhfmGrpWorkFlow grp,EhfAdminSanctionsMetaData md where aw.currWorkFlowId=grp.id.workFlowId and aw.sanctionId=md.sanctionId and aw.currOwnerGrp in(");
		 
	 for(int j=0;j<grplist.size();j++){
		 
		 if(j==grplist.size()-1){
			 query.append( "? ");
		 }
		 else{
		 query.append( "?,");
		 }
			args[i] = grplist.get(j).getID();
			i++;		 
	 }
	 query.append(")");
	 
	 if(!state.equalsIgnoreCase(config.getString("ACC.APTGState"))){
		 
		 //query.append(" and md.scheme in ('"+apCode+"','"+tgCode+"'");
		 query.append(" and md.scheme='"+state+"'");
		
	 }
	 /*else {
		 query.append(" and md.scheme='"+state+"'");
	 }
	 query.append(")");*/
	 query.append(" order by aw.crtDt asc");
	
	 reqList=genericDao.executeHQLQueryList(AdminSanctionVO.class, query.toString(),args);
		return reqList;
}

@Override
/**
 * ; 
 *  @param List<AdminSanctionVO>
 * @return List<AdminSanctionVO>
 * @function To format the list of admin sanction vos obtained for displaying in jsp
 */
public List<AdminSanctionVO> getAdminSancworkList(
		List<AdminSanctionVO> reqList) {
	 Configuration config = configurationService.getConfiguration();
	 List<AdminSanctionVO> lStAdminSanctionVO=null;
	 lStAdminSanctionVO=new ArrayList<AdminSanctionVO>(); 
	 String appRejWorkFlowName=null;
	 String userName=null;
	  String workFlowName=null;
	 for(AdminSanctionVO adminSanctionVO:reqList ){
		 
		 adminSanctionVO.setSanctionId(adminSanctionVO.getSanctionId());
		  
		 if(adminSanctionVO.getCurrentWorkFlowId()!=null && adminSanctionVO.getCurrentWorkFlowId()!=""){
			 workFlowName=config.getString(adminSanctionVO.getCurrentWorkFlowId());
			  }  
				if(workFlowName==null||workFlowName==""){
					
			if(adminSanctionVO.getPrevWorkFlowId()!=null && adminSanctionVO.getPrevWorkFlowId()!=""){
				appRejWorkFlowName= config.getString(adminSanctionVO.getPrevWorkFlowId());
			}
				}
                 if(adminSanctionVO.getStatus().equalsIgnoreCase("P")){	
					adminSanctionVO.setStatus("Pending with "+workFlowName);
					
				}
                 if(adminSanctionVO.getStatus().equalsIgnoreCase("RA")){	
 					adminSanctionVO.setStatus("Recommended for approval to "+workFlowName);
 					
 				}
				if(adminSanctionVO.getStatus().equalsIgnoreCase("A")){
					
					adminSanctionVO.setStatus("Approved by "+appRejWorkFlowName);
					 
				}
				if(adminSanctionVO.getStatus().equalsIgnoreCase("R")){
		
					adminSanctionVO.setStatus("Rejected by "+appRejWorkFlowName);
				}	
				if(adminSanctionVO.getStatus().equalsIgnoreCase("I")){
					
					adminSanctionVO.setStatus("Initiated by  "+workFlowName);
				}	
				
		  adminSanctionVO.setSanctionAmount(adminSanctionVO.getSanctionAmount());
		  userName=getUserName(adminSanctionVO.getCrtUsr());
		  adminSanctionVO.setCrtUsr(userName);
		  
		  /*SimpleDateFormat sdf =new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss");
			String crtDate = sdf.format(adminSanctionVO.getCrtDt());
			try {
				adminSanctionVO.setCrtDt(sdf.parse(crtDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
		  adminSanctionVO.setCrtDt(adminSanctionVO.getCrtDt());
		  adminSanctionVO.setLstUpdDt(adminSanctionVO.getLstUpdDt());
		  if(adminSanctionVO.getLstUpdUsr()!=null)
		  {
			  userName=getUserName(adminSanctionVO.getLstUpdUsr());
			  adminSanctionVO.setLstUpdUsr(userName);
			/*  String lstUpdDate=sdf.format(adminSanctionVO.getLstUpdDt());
			  try {
				adminSanctionVO.setLstUpdDt(sdf.parse(lstUpdDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
		  }
		  
		  lStAdminSanctionVO.add(adminSanctionVO);
      }
	 return lStAdminSanctionVO;
}

/**
 * ; 
 *  @param userId
 * @return userName
 * @function to get the username based on userid
 */
private String getUserName(String crtUsr) {
	List<LabelBean> userNamesList = null;
    StringBuffer lsb = new StringBuffer () ;  
    String userName=null;
    String[] args = null;
try
  {
      args=new String[1];
      args[0]=crtUsr;
      lsb.append ( " select firstName as ID,lastName as VALUE ");
      lsb.append ( " FROM EhfmUsers");
      lsb.append ( " where serviceFlag = 'Y' and id.userId=?");
      
      userNamesList = genericDao.executeHQLQueryList(LabelBean.class,lsb.toString(),args);
  }
  catch ( Exception e )
  {
	  e.printStackTrace();
   
  }
  if(userNamesList.size()>0)
  {
	  userName=userNamesList.get(0).getID()+" "+userNamesList.get(0).getVALUE();
  }
   
  return userName ;
}

@Override
/**
 * ; 
 *  @param sanctionId
 * @return attachments Status
 * @function To verify whether attachments are present for the given sanction id 
 */
public String getAttachStatus(String sanctionId)
{
	String status="no";
	List<LabelBean> fileNames=new ArrayList<LabelBean>();
        List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
    criteriaList.add(new GenericDAOQueryCriteria("id.transId",sanctionId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    List<AsritAccountsDocAttch> docAttch=genericDao.findAllByCriteria(AsritAccountsDocAttch.class,criteriaList);
	if(docAttch!=null && docAttch.size()>0)
		status= "yes";
    return status;

}

@Override
/**
 * ; 
 *  @param sanctionId
 * @return ehfAdminSanctionMetaData
 * @function To retrieve all the details of ehfAdminSanctionMetaData table based on sanction Id
 */
public EhfAdminSanctionsMetaData getSanctionDeatils(String sanctionId) {
	
	EhfAdminSanctionsMetaData ehfAdminSanctionMetaData=genericDao.findById(EhfAdminSanctionsMetaData.class, String.class,sanctionId);
	
	return ehfAdminSanctionMetaData;
}

/**
 * ;
 * @param String sanctionId
* @return String msg
 * @function This method is used to find out if Sanction is Incomplete(Details not submitted-just saved)
 */
@Override
public String decideLabelValue(String sanctionId){
	 
	 String msg="Complete";
	 List<EhfAccountsWorkflow> lStEhfAccountsWorkflow=null;
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("sanctionId", sanctionId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 lStEhfAccountsWorkflow=genericDao.findAllByCriteria(EhfAccountsWorkflow.class,criteriaList);
	 criteriaList.removeAll(criteriaList);	 
	 if(lStEhfAccountsWorkflow.size()>0){
		 
		 String status=lStEhfAccountsWorkflow.get(0).getStatus();
		 if(status.equalsIgnoreCase("I")){
			 msg="Incomplete";
		 }
	 }
	 return msg;
}

/**
 * ; 
 *  @param sanctionId
 * @return List<AdminSanctionRemarksVO> 
 * @function To get previous remarks of all the members who belong to that workflow of admin sanction 
 */
@Override
public List<AdminSanctionRemarksVO> getPreviousRemarks(String sanctionId) {
	 String actorName=null;
	 Configuration config = configurationService.getConfiguration();
	 List<EhfAdminSanctionAudit> lstEhfAdminSanctionAudit=null;
	 List<AdminSanctionRemarksVO> lStadminSanctionRemarksVO=null;
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("id.sanctionId", sanctionId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	
	 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.ASC));
	
	 lstEhfAdminSanctionAudit=genericDao.findAllByCriteria(EhfAdminSanctionAudit.class,criteriaList);
	 criteriaList.removeAll(criteriaList);
	
	
	 	 
	 lStadminSanctionRemarksVO=new ArrayList<AdminSanctionRemarksVO>(); 
	 AdminSanctionRemarksVO adminSanctionRemarksVO = null;

	 String appRejWorkFlowName=null;
	 String workFlowName=null;
	 for(EhfAdminSanctionAudit ehfAdminSanctionAudit:lstEhfAdminSanctionAudit){
		 adminSanctionRemarksVO=new AdminSanctionRemarksVO();
		 adminSanctionRemarksVO.setRemarksDt(ehfAdminSanctionAudit.getActdDt());
		 if(ehfAdminSanctionAudit.getActor()!=null && ehfAdminSanctionAudit.getActor()!=""){
		actorName=getActorName(ehfAdminSanctionAudit.getActor());
		 }
		 adminSanctionRemarksVO.setUpdatedBy(actorName);
		 if(ehfAdminSanctionAudit.getNextWorkFlwId()!=null && ehfAdminSanctionAudit.getNextWorkFlwId()!=""){
		workFlowName=config.getString(ehfAdminSanctionAudit.getNextWorkFlwId());
		 }
		if(workFlowName==null||workFlowName==""){
	
			if(ehfAdminSanctionAudit.getCurrentWorkFlowId()!=null && ehfAdminSanctionAudit.getCurrentWorkFlowId()!=""){
		appRejWorkFlowName= config.getString(ehfAdminSanctionAudit.getCurrentWorkFlowId());
			}
		}
		
		
		if(ehfAdminSanctionAudit.getStatus().equalsIgnoreCase("P")){
			
			
			 adminSanctionRemarksVO.setAction("Pending with "+workFlowName);
			
		}
		if(ehfAdminSanctionAudit.getStatus().equalsIgnoreCase("RA")){
			
			
			 adminSanctionRemarksVO.setAction("Recommended for approval to "+workFlowName);
			
		}
		if(ehfAdminSanctionAudit.getStatus().equalsIgnoreCase("A")){
			
			 adminSanctionRemarksVO.setAction("Approved by "+appRejWorkFlowName);
			 
		}
		if(ehfAdminSanctionAudit.getStatus().equalsIgnoreCase("R")){

			adminSanctionRemarksVO.setAction("Rejected by "+appRejWorkFlowName);
		}
		
		 adminSanctionRemarksVO.setRemarks(ehfAdminSanctionAudit.getRemarks());
		 lStadminSanctionRemarksVO.add(adminSanctionRemarksVO);
	 }
		
	 
	 return lStadminSanctionRemarksVO;
}
/**
 * ; 
 *  @param String userId
* @return String actorName
 * @function This method is used to find First name and Last name of a user ,provided userId
 */
public String getActorName(String userId){

 String actorName=null;

EhfmUsers ehfmUser =genericDao.findById(EhfmUsers.class,String.class,userId);
if(ehfmUser!=null){
	
	actorName=ehfmUser.getFirstName()+" "+ehfmUser.getLastName();
	
}
return actorName;
}

/**
 * @param state 
 * @return List<AdminSanctionVO> 
 * @function This method is used to find the status of of requests in the workflow based on scheme
 */
@Override
public List<AdminSanctionVO> findSanctionRequestStatus(String state,String empId) {
	Configuration config = configurationService.getConfiguration();
	 String apCode=config.getString("ACC.EHFAPSchemeID");
	 String tgCode=config.getString("ACC.EHFTGSchemeID");
	 String grpFlag="N";
	 if(state.equals(config.getString("ACC.APState")))
		{
    	
		 state =config.getString("ACC.EHFAPSchemeID");
		}
    if(state.equals(config.getString("ACC.TGState")))
		{
   	 state =config.getString("ACC.EHFTGSchemeID");
		}
    List<AdminSanctionVO> lstCrtUsrs= getCrtUsr(state);
	 List<AdminSanctionVO> reqList=null;
	 StringBuffer query = new StringBuffer();
	 int i=0;
	 query.append("select  aw.sanctionId as sanctionId, aw.crtDt as crtDt,aw.crtUsr as crtUsr,aw.lstUpdUsr as lstUpdUsr,aw.lstUpddt as lstUpdDt,aw.status as status,md.sanctionAmount as sanctionAmount,aw.currWorkFlowId as currentWorkFlowId,aw.prevWorkFlowId as prevWorkFlowId  from EhfAccountsWorkflow aw,EhfAdminSanctionsMetaData md where aw.sanctionId=md.sanctionId  ");
		 

	 if(!state.equalsIgnoreCase(config.getString("ACC.APTGState"))){
		 
		// query.append(" md.scheme in ('"+apCode+"','"+tgCode+"'");
		 //query.append(")");
		 query.append(" and md.scheme='"+state+"'");
	 }
	/* else {
		 query.append(" md.scheme='"+state+"'");
	 }*/
	 if(lstCrtUsrs!=null && empId!=null )
	  {
		 for(int j=0;j<lstCrtUsrs.size();j++)
		 {
		 if(lstCrtUsrs.get(j).getCrtUsr().equalsIgnoreCase(empId))
		 {
			 grpFlag="Y";
		     break;
		 }
		 }
		 
		 if(grpFlag.equals("N"))
			 query.append(" and aw.status not in ('I') ");
	  }
	 query.append(" order by aw.crtDt asc");
	
	 reqList=genericDao.executeHQLQueryList(AdminSanctionVO.class, query.toString());
		return reqList;
}

@Override
/**
 * @param department 
 * @return LabelBean
 * @function This method is used to get department name based on department Id provided
 */
public LabelBean getDepartment(String department) {
	List<LabelBean> dept=null;
	StringBuffer query = new StringBuffer();
	String args[] = new String[1];
	args[0] = department;
	query.append("SELECT deptId as ID, deptName as VALUE FROM EhfmDepts where deptId=? order by deptName");
	dept=genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
	return dept.get(0);
}

@Override
/** 
 * @return Long sanctionAmount
 * @function This method is used to get all the amount sanctioned by EO in that particular month
 */
public Long getSanctionAmountByMonth()
{
Date d1= new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(d1);
Long sancAmt=0L;
String month= (cal.get(Calendar.MONTH)+1) + "";
if(month.length()==1) 
month="0"+month;
String monthYr=month+"/"+(cal.get(Calendar.YEAR));
List<AdminSanctionVO> sanctionsList=null;
 StringBuffer query = new StringBuffer();
 query.append("select amountRequested as reqAmt  from AsritAdminSanctionData where status='CD2161' and  TO_CHAR(crtDt, 'MM/YYYY') =?");
  String[] args= new String[1];
  args[0]=monthYr;
 sanctionsList=genericDao.executeHQLQueryList(AdminSanctionVO.class, query.toString(),args);
 if(sanctionsList.size()>0)
 {
	 for(int i=0;i<sanctionsList.size();i++)
	 {
		 sancAmt+=sanctionsList.get(i).getReqAmt();
     }
	 }
	
return sancAmt;
}

@Override
/** 
 * @param sanctionId
 * @return String approvalFlag
 * @function This method is used to find whether that particular sanction id is approved finally or not
 */
public String verifyFinalLevelApproval(String sanctionId) {
	String status=null;

	AsritAdminSanctionData asritAdminSanctionData =genericDao.findById(AsritAdminSanctionData.class,String.class,sanctionId);
	if(asritAdminSanctionData!=null){
		
		status=asritAdminSanctionData.getStatus();
		
	}
	
	return status;
}

@Override
/** 
 * @param String sanctionId,String reqStatus, String startDate, String endDate, String state
 * @return List<AdminSanctionVO>
 * @function This method is used to search sanction requests based on the search criteria provided
 */
public List<AdminSanctionVO> findSancRequests(String sanctionId,
		String reqStatus, String startDate, String endDate, String state,String empId) {
	Configuration config = configurationService.getConfiguration();
	 String apCode=config.getString("ACC.EHFAPSchemeID");
	 String tgCode=config.getString("ACC.EHFTGSchemeID");
	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	 String grpFlag="N";
	 List<AdminSanctionVO> lstCrtUsrs= getCrtUsr(state);
	 
	 
	 if(state.equals(config.getString("ACC.APState")))
		{
		 state =config.getString("ACC.EHFAPSchemeID");
		}
   if(state.equals(config.getString("ACC.TGState")))
		{
  	 state =config.getString("ACC.EHFTGSchemeID");
		}
	 
	 List<AdminSanctionVO> reqList=null;
	 StringBuffer query = new StringBuffer();
	 int i=0;
	 query.append("select  aw.sanctionId as sanctionId, aw.crtDt as crtDt,aw.crtUsr as crtUsr,aw.lstUpdUsr as lstUpdUsr,aw.lstUpddt as lstUpdDt,aw.status as status,md.sanctionAmount as sanctionAmount,aw.currWorkFlowId as currentWorkFlowId,aw.prevWorkFlowId as prevWorkFlowId  from EhfAccountsWorkflow aw,EhfAdminSanctionsMetaData md where aw.sanctionId=md.sanctionId  ");
		
	 if(lstCrtUsrs!=null && empId!=null )
	  {
		 for(int j=0;j<lstCrtUsrs.size();j++)
		 {
		 if(lstCrtUsrs.get(j).getCrtUsr().equalsIgnoreCase(empId))
		 {
			 grpFlag="Y";
		     break;
		 }
		 }
		 
		 if(grpFlag.equals("N"))
			 query.append(" and aw.status not in ('I') ");
	  }
	 if(sanctionId!=null && !("").equalsIgnoreCase(sanctionId))
	  {
		query.append(" and aw.sanctionId = ?");
		i++;
		
	  }
	 if(reqStatus!=null && !("").equalsIgnoreCase(reqStatus))
	  {
		query.append(" and aw.status = ?");
		i++;
		
	  }
	 if(startDate !=null &&!("").equalsIgnoreCase(startDate)&&( endDate !=null && !("").equalsIgnoreCase(endDate)))
	  {
		  query.append(" and trunc(aw.crtDt) between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy') ");
		i=i+2;
	  }
	
	  String[] args= new String[i];
	  i=0;
	  if(sanctionId!=null && !("").equalsIgnoreCase(sanctionId))
	  {
		 args[i]=sanctionId.toUpperCase().trim();
		i++;
		
	  }
	  if(reqStatus!=null && !("").equalsIgnoreCase(reqStatus))
	  {
		  args[i]=reqStatus;
			i++;
		
	  }
	  
	  if(startDate !=null &&!("").equalsIgnoreCase(startDate))
	  {
		 try {
			args[i]=sdf.format(sdf.parse(startDate));
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		i++;
	  }
	 if( endDate !=null && !("").equalsIgnoreCase(endDate)){
		 try {
				args[i]=sdf.format(sdf.parse(endDate));
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
			i++;
	}
	  
	 if(!state.equalsIgnoreCase(config.getString("ACC.APTGState"))){
		 query.append(" and  md.scheme='"+state+"'");
		 //query.append(" and  md.scheme in ('"+apCode+"','"+tgCode+"'");
		 //query.append(")");
	 }
	 /*else {
		 query.append(" and  md.scheme='"+state+"'");
	 }*/
	
	 query.append(" order by aw.crtDt asc");
	
	 reqList=genericDao.executeHQLQueryList(AdminSanctionVO.class, query.toString(),args);
		return reqList;
	
}


@Override
/**
 * @param String state
 * @return String created user
 * This method used to get the created user of initiated requests based on state
 */
public List<AdminSanctionVO> getCrtUsr(String state){
	 Configuration config = configurationService.getConfiguration();
	 String apCode=config.getString("ACC.EHFAPSchemeID");
	 String tgCode=config.getString("ACC.EHFTGSchemeID");
	 
	 if(state.equals(config.getString("ACC.APState")))
		{
		 state =config.getString("ACC.EHFAPSchemeID");
		}
    if(state.equals(config.getString("ACC.TGState")))
		{
   	 state =config.getString("ACC.EHFTGSchemeID");
		}
	 List<AdminSanctionVO> reqList=null;
	 StringBuffer query = new StringBuffer();
	 query.append("select distinct(aw.crtUsr) as crtUsr from EhfAccountsWorkflow aw,EhfAdminSanctionsMetaData md where aw.sanctionId=md.sanctionId and aw.status='I' and ");
		 

	 if(state.equalsIgnoreCase(config.getString("ACC.APTGState"))){
		 
		 query.append(" md.scheme in ('"+apCode+"','"+tgCode+"'");
		 query.append(")");
	 }
	 else {
		 query.append(" md.scheme='"+state+"'");
	 }
	
	 reqList=genericDao.executeHQLQueryList(AdminSanctionVO.class, query.toString());
	 return reqList;
}

@Override
public String verifyIfDyEo(List<LabelBean> grpList)
{
	String initStatus="N";
	 Configuration config = configurationService.getConfiguration();
     String initWorkFlowId=config.getString("AdminSanctionInit.WorkFlowId");
     List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
     criteriaList.add(new GenericDAOQueryCriteria("id.workFlowId",initWorkFlowId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));   
     List<EhfmGrpWorkFlow> ehfmGrpWorkFlowList = genericDao.findAllByCriteria(EhfmGrpWorkFlow.class,criteriaList);
     if(ehfmGrpWorkFlowList.size()>0)
     {
    	 for(int j=0;j<grpList.size();j++)
    	 {
    		 if(ehfmGrpWorkFlowList.get(0).getGrpId().equals(grpList.get(j).getID()))
    				 {
    			       initStatus="Y";
    			        break;
    				 }
    				 
    	 }
     }
	
	
	return initStatus;
	
}

@Override
public String getAdminSancApprovedDate(String sanctionId) {
	String lstUpdDate="";
	EhfAccountsWorkflow ehfAccountsWorkFlow=genericDao.findById(EhfAccountsWorkflow.class, String.class,sanctionId);
	if(ehfAccountsWorkFlow!=null){
		if(ehfAccountsWorkFlow.getLstUpddt()!=null && !ehfAccountsWorkFlow.getLstUpddt().equals(""))
		lstUpdDate=sdf.format(ehfAccountsWorkFlow.getLstUpddt());
	}
	return lstUpdDate;
}

@Override
public List<AdminSanctionVO> getCeoAdminSancRequests(List<LabelBean> grplist,
		String state) {
	 Configuration config = configurationService.getConfiguration();
	 if(state.equals(config.getString("ACC.APState")))
		{
		 state =config.getString("ACC.EHFAPSchemeID");
		}
    if(state.equals(config.getString("ACC.TGState")))
		{
   	 state =config.getString("ACC.EHFTGSchemeID");
		}
	 
	 List<AdminSanctionVO> reqList=null;
	 StringBuffer query = new StringBuffer();
	 int i=0;
	 String[] args= new String[grplist.size()];
	 query.append("select  aw.sanction_Id as SANCTIONID, md.subject as SUBJECT,md.sanction_Amount as SANCAMOUNT,md.detailed_Head_Name as ACCCODE,DECODE(md.vendor_Type, 'S', md.vendor_Name,'M', 'Multiple Vendors','NA','Not Applicable') as VENDORNAME  from Ehf_Accounts_Workflow aw,Ehfm_Grp_WorkFlow grp,Ehf_Admin_Sanctions_Meta_Data md where aw.current_WorkFlow_Id=grp.workFlow_Id and aw.sanction_Id=md.sanction_Id and aw.current_owner_grp in(");
		 
	 for(int j=0;j<grplist.size();j++){
		 
		 if(j==grplist.size()-1){
			 query.append( "? ");
		 }
		 else{
		 query.append( "?,");
		 }
			args[i] = grplist.get(j).getID();
			i++;		 
	 }
	 query.append(")");
	 
	 if(!state.equalsIgnoreCase(config.getString("ACC.APTGState"))){
		 query.append(" and md.scheme='"+state+"'");
	 }
	 query.append(" order by aw.crt_Dt asc");
	
	 reqList=genericDao.executeSQLQueryList(AdminSanctionVO.class, query.toString(),args);
		return reqList;
}

@Override
public List<LabelBean> viewAttachments(String sanctionId) {
	 LabelBean l=null;
	    List<LabelBean> fileNames=new ArrayList<LabelBean>();
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
      criteriaList.add(new GenericDAOQueryCriteria("id.transId",sanctionId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<AsritAccountsDocAttch> docAttch=genericDao.findAllByCriteria(AsritAccountsDocAttch.class,criteriaList);
		for(int j=0;j<docAttch.size();j++)
		{
			l = new LabelBean(docAttch.get(j).getId().getSavedName(),docAttch.get(j).getActualName());
			fileNames.add(l);
		}
		return fileNames;
}

@Override
public String getAttachPath(String lStrtransid, String fname) {
	
	List<LabelBean> fileNames=new ArrayList<LabelBean>();
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
    criteriaList.add(new GenericDAOQueryCriteria("id.transId",lStrtransid,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    criteriaList.add(new GenericDAOQueryCriteria("actualName",fname,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    List<AsritAccountsDocAttch> docAttch=genericDao.findAllByCriteria(AsritAccountsDocAttch.class,criteriaList);
	if(docAttch.size()>0)
		return docAttch.get(0).getId().getSavedName();
	else
	return null;
}
}
