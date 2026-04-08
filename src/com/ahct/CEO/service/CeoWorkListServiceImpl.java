package com.ahct.CEO.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.configuration.Configuration;

import com.ahct.CEO.vo.EdcHospitalListVO;
import com.ahct.CEO.vo.EdcRequestRemarksVO;
import com.ahct.CEO.vo.UtilityVO;
import com.ahct.CEO.vo.EdcVO;
import com.ahct.CEO.vo.EmpanelHospVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfCrAttachments;
import com.ahct.model.EhfEdcAttachments;
import com.ahct.model.EhfEdcAudit;
import com.ahct.model.EhfEdcAuditId;
import com.ahct.model.EhfEdcWorkFlow;
import com.ahct.model.EhfEmpnlAprvlDtls;
import com.ahct.model.EhfEmpnlAprvlDtlsId;
import com.ahct.model.EhfEmpnlHospInfo;
import com.ahct.model.EhfEmpnlHospInfoAudit;
import com.ahct.model.EhfmEDCHospActionDtls;
import com.ahct.model.EhfmEDCHospActionDtlsId;
import com.ahct.model.EhfmEmpnlArticleMstr;
import com.ahct.model.EhfmGrpWorkFlow;
import com.ahct.model.EhfmHospCatMst;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmMedcoDetails;
import com.ahct.model.EhfmSpecialities;
import com.ahct.model.EhfmUsers;
import com.ahct.preauth.vo.PreauthVO;


public class CeoWorkListServiceImpl implements CeoWorkListService{
	
	    Logger logger = Logger.getLogger(CeoWorkListServiceImpl.class);
	    private GenericDAO genericDao; 
	    private HibernateTemplate hibernateTemplate;
	    ConfigurationService configurationService;
	    
	    
	    public ConfigurationService getConfigurationService() {
			return configurationService;
		}

		public void setConfigurationService(ConfigurationService configurationService) {
			this.configurationService = configurationService;
		}
	    public GenericDAO getGenericDao() {
			return genericDao;
		}



		public void setGenericDao(GenericDAO genericDao) {
			this.genericDao = genericDao;
		}



		public HibernateTemplate getHibernateTemplate() {
			return hibernateTemplate;
		}



		public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
			this.hibernateTemplate = hibernateTemplate;
		}



	public List<EmpanelHospVO> getCeoWorkList(String lStrUserState,String lStrMenu)
	{
		  List<EmpanelHospVO> ceoWorkList=null;
		  StringBuffer query = new StringBuffer();
		  String[] args = new String[4];
		  args[0] = "LH6";
		  args[1] = "LH1";
		  if("othrSt".equalsIgnoreCase(lStrMenu))
			  args[2]="CD395";
		  else
			  args[2] = "CD1901";
		  args[3] = lStrUserState;
		  
		  query.append("select eh.hosp_name HOSPNAME,eh.hospinfo_id HOSPID,cd.cmb_dtl_name VALUE,loc.loc_name STATE,el.loc_name DISTRICT,eh.hosp_add || eh.hosp_street ||' '|| elm.loc_name HOSPLOCATION ");
		  query.append(" from ehf_empnl_hospinfo eh,ehf_empnl_aprvl_dtls ad,ehfm_cmb_dtls cd,ehfm_locations elm,(select el.loc_id, el.loc_name from ehfm_locations el where el.loc_hdr_id = ?) el, ");
		  query.append(" (select el.loc_id, el.loc_name from ehfm_locations el where el.loc_hdr_id = ?) loc ");
		  query.append(" where eh.status = ?  and scheme = ? and el.loc_id = eh.district_code and loc.loc_id = eh.state_code and ad.hospinfo_id = eh.hospinfo_id  and eh.applictn_type = cd.cmb_dtl_id and elm.loc_id(+) = eh.mandal ");
		  if("othrSt".equalsIgnoreCase(lStrMenu))
			  query.append(" and ad.othr_stceo_rmks is null"); 
		  
		  ceoWorkList = genericDao.executeSQLQueryList(EmpanelHospVO.class, query.toString(), args);
		  
		  return ceoWorkList;
	}
	
	public int operationsWorkflow(String userGrp,String Scheme)
	{
		List<PreauthVO>  preauthVO=new ArrayList<PreauthVO>();
		StringBuffer query = new StringBuffer();
		  String[] args = new String[2];
		  args[0]=userGrp;
		  args[1]=Scheme;
		  int size=0;
		  
		  query.append("select case_id as CASEID from ehf_case where case_status in ");
		  query.append(" (select distinct current_status from ehfm_workflow_status where current_role=? and active_yn='Y') ");
		  query.append(" and scheme_id=? ");
		  
		  preauthVO = genericDao.executeSQLQueryList(PreauthVO.class, query.toString(), args);
		  
          size= preauthVO.size();
          
          return size;
		  
	}
	
	 @Transactional(rollbackFor={Exception.class})
	public boolean saveDtls(Map lParamMap)
	{
		boolean rslt=true;
		String hospInfoId = "",status="";
		EhfEmpnlAprvlDtls empnlAprvDtls=null;        
	    EhfEmpnlAprvlDtlsId dtlsIdVO=null;
	    String hospInfoIdList = (String)lParamMap.get("hospInfoIdList");
	    String lStrBtnType = (String)lParamMap.get("btnType");
	    String userId = (String)lParamMap.get("userId");
	   
	    String lStrRmks = "";
		try
		{	//ArrayList l=new ArrayList();
			String[] tempList = hospInfoIdList.split("~");
			for(int j=0;j<tempList.length;j++)
			{
				hospInfoId = tempList[j];
				EhfEmpnlHospInfo EhfEmpnlHospInfoObj = genericDao.findById(EhfEmpnlHospInfo.class, String.class, hospInfoId);
				if("Approve".equalsIgnoreCase(lStrBtnType))
				{
					status = "CD395";
					lStrRmks = "Approved by CEO";
				}
				else if("Reject".equalsIgnoreCase(lStrBtnType))
				{
					status = "CD396";
					lStrRmks = "Rejected by CEO";
				}
				EhfEmpnlHospInfoObj.setStatus(status);
				EhfEmpnlHospInfoObj.setUpdDate(new Timestamp(new Date().getTime()));
				EhfEmpnlHospInfoObj.setUpdUsr(userId);
				EhfEmpnlHospInfoObj = genericDao.save(EhfEmpnlHospInfoObj);
				
				EhfEmpnlHospInfoAudit ehfEmpnlHospInfoAudit = new EhfEmpnlHospInfoAudit();
				BeanUtils.copyProperties(ehfEmpnlHospInfoAudit, EhfEmpnlHospInfoObj);
				ehfEmpnlHospInfoAudit = genericDao.save(ehfEmpnlHospInfoAudit);
				
				 dtlsIdVO=new EhfEmpnlAprvlDtlsId();
		         dtlsIdVO.setHospinfo_Id(hospInfoId);
		         dtlsIdVO.setEnhanced_count("0");     
		         empnlAprvDtls=getEmpnlAprvlRecord(dtlsIdVO);
		         if(empnlAprvDtls==null){            
	                    empnlAprvDtls=new EhfEmpnlAprvlDtls();
	                    empnlAprvDtls.setId(dtlsIdVO);
	                
	             }
		    	         
		         empnlAprvDtls.setCeo_approved_date(new Timestamp(new Date().getTime()));
		         empnlAprvDtls.setCeo_approver_id(userId);
		         empnlAprvDtls.setCeo_approver_remarks(lStrRmks);
		         empnlAprvDtls.setApprv_status(status);
		         empnlAprvDtls=genericDao.save(empnlAprvDtls);
				
			}
		}
		catch(Exception e)
		{
			rslt=false;
			//logger.error("Exception in saveDtls method of CeoWorkListServiceImpl class "+e);
			e.printStackTrace();
		}
		return rslt;
	}
	
    public EhfEmpnlAprvlDtls getEmpnlAprvlRecord(EhfEmpnlAprvlDtlsId id){
        EhfEmpnlAprvlDtls empnlRecord=genericDao.findById(EhfEmpnlAprvlDtls.class,EhfEmpnlAprvlDtlsId.class,id);
        return empnlRecord;
    }
    @Transactional(rollbackFor={Exception.class})
    public boolean saveOthrStDtls(Map lParamMap)
    {
    	boolean rslt=true;
    	EhfEmpnlAprvlDtls empnlAprvDtls=null;        
	    EhfEmpnlAprvlDtlsId dtlsIdVO=null;
	    String hospInfoIdList = (String)lParamMap.get("hospInfoIdList");
	    String lStrBtnType = (String)lParamMap.get("btnType");
	    String userId = (String)lParamMap.get("userId");
	    String scheme = (String)lParamMap.get("scheme");
	    String lStrRmks = "",hospInfoId="";
    	
    	try{
    		 String[] tempList = hospInfoIdList.split("~");
    		 for(int j=0;j<tempList.length;j++){
    			 
    			 hospInfoId = tempList[j];
    			 dtlsIdVO=new EhfEmpnlAprvlDtlsId();
    	         dtlsIdVO.setHospinfo_Id(hospInfoId);
    	         dtlsIdVO.setEnhanced_count("0");   
    	         empnlAprvDtls=getEmpnlAprvlRecord(dtlsIdVO);
    	         if("Approve".equalsIgnoreCase(lStrBtnType))
    	         {
    	        	 lStrRmks = "Approved by Other State CEO";
    	        	String hospId = this.getHospId(hospInfoId);
     	            EhfmEDCHospActionDtls EhfmEDCHospActionDtlsObj = new EhfmEDCHospActionDtls();
     	     	    EhfmEDCHospActionDtlsId EhfmEDCHospActionDtlsIdObj = new EhfmEDCHospActionDtlsId();
     	         	EhfmEDCHospActionDtlsIdObj.setHospId(hospId);
     	    		EhfmEDCHospActionDtlsIdObj.setScheme(scheme);
     	    		EhfmEDCHospActionDtlsObj.setId(EhfmEDCHospActionDtlsIdObj);
     	    		EhfmEDCHospActionDtlsObj.setHospStatus("Y");
     	    		EhfmEDCHospActionDtlsObj.setCrtDt(new Timestamp(new Date().getTime()));
     	    		EhfmEDCHospActionDtlsObj.setCrtUsr(userId);
     	    		EhfmEDCHospActionDtlsObj = genericDao.save(EhfmEDCHospActionDtlsObj);
     	         
    	         }
    	         else if("Reject".equalsIgnoreCase(lStrBtnType))
    	         {
    	        	 lStrRmks = "Rejected by Other State CEO";
    	         }
    	         if(empnlAprvDtls != null)
    	         {
    	        	 empnlAprvDtls.setOthrStCeoRmks(lStrRmks);
    	        	 empnlAprvDtls.setOthrStCeoRmksDt(new Timestamp(new Date().getTime()));
    	        	 empnlAprvDtls.setOthrStCeoUsrId(userId);
    	        	 empnlAprvDtls = genericDao.save(empnlAprvDtls);
    	         }
    	         
    	           
    			 
    		 }

    	}
    	catch(Exception e)
    	{
    		 rslt = false;
    		//logger.error("Exception in saveOthrStDtls method of CeoWorkListServiceImpl"+e);
    		e.printStackTrace();
    	}
    	return  rslt;
    	
    }
    
    private String getHospId(String hospInfoId)
    {
    	String hospId="";
    	List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
    	critList.add(new GenericDAOQueryCriteria("hospEmpnlRefNum",hospInfoId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	List<EhfmHospitals> hospIdList=genericDao.findAllByCriteria(EhfmHospitals.class,critList );
    	if(hospIdList.size()  > 0)
    	{
    		hospId = hospIdList.get(0).getHospId();
    	}
    	
    	return hospId;
    	
    }
	
	@Override
	public List<EdcVO> getEdcCeoWorklist(String ceoGrp,String scheme) {
		
		List<EdcVO> reqList = null;
		StringBuffer query = new StringBuffer();
		
		String[] args = new String[2];
		args[0]=ceoGrp;
		args[1]=scheme;
		query.append("select  hosp.hospName as hospId,hosp.hospId as hospCode, cr.id.actionId as actionId,cr.crtDt as crtDt,cr.actionType as actionType from EhfEdcWorkFlow cr,EhfmHospitals hosp,EhfmGrpWorkFlow grp  where cr.currWorkFlowId=grp.id.workFlowId and cr.id.hospId = hosp.hospId  and");
		query.append(" cr.currOwnerGrp=? and cr.scheme=? order  by cr.crtDt asc" );
		
		reqList = genericDao.executeHQLQueryList(EdcVO.class, query.toString(),args);
		
		return reqList;
		
		
		
	}



	@Override
	public String saveEdsCeoDtls(String actionIDList,String btnType,String userId) {
		List<EhfEdcAudit> ehfEdcAuditListSave=new ArrayList<EhfEdcAudit>();
		List<EhfEdcWorkFlow> lStEhfEdcWorkFlow;
		 int lineNo=0;
		 String status="";
		 Date assigndDate=null;
		 Date startDate=null;
		 Date endDate=null;
		 String successMsg="";
		try
		{	//ArrayList l=new ArrayList();
			String[] tempList = actionIDList.split("~");
			for(int j=0;j<tempList.length;j++)
			{
				String hospId;
				String[] actionTmpList = tempList[j].split("@");
				 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
				 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionTmpList[1],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 criteriaList.add(new GenericDAOQueryCriteria("id.hospId", actionTmpList[0],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 lStEhfEdcWorkFlow=genericDao.findAllByCriteria(EhfEdcWorkFlow.class,criteriaList);
				 criteriaList.removeAll(criteriaList);
				 String prevCurrentOwner=lStEhfEdcWorkFlow.get(0).getCurrOwnerGrp();
				if(lStEhfEdcWorkFlow!=null && lStEhfEdcWorkFlow.size()>0){
					
						
					
					
					 hospId=lStEhfEdcWorkFlow.get(0).getId().getHospId();
					if(btnType.equalsIgnoreCase("Approve")){
						
						
						
						EhfEdcWorkFlow ehfEdcWorkFlow =lStEhfEdcWorkFlow.get(0);
						
						 ehfEdcWorkFlow.setLstUpdUsr(userId);
						 ehfEdcWorkFlow.setLstUpddt(new Timestamp(new Date().getTime()));
						ehfEdcWorkFlow.setCurrOwnerGrp("GP2");
						ehfEdcWorkFlow.setCurrWorkFlowId("WF_EDC_SUSPENSION_MEDCO_REPLY");
						ehfEdcWorkFlow.setPrevOwnerGrp("GP16");
						ehfEdcWorkFlow.setPrevWorkFlowId("WF_EDC_SUSPENSION_CEO_APPRVAL");
						if((prevCurrentOwner==null||prevCurrentOwner=="")){
							
							if("Reject".equalsIgnoreCase(btnType)){
								
								ehfEdcWorkFlow.setStatus("R");
							}
							if("Approve".equalsIgnoreCase(btnType)){
								
								ehfEdcWorkFlow.setStatus("A");
							}
							
						}
						else{
							
								ehfEdcWorkFlow.setStatus("P");
						}
						
						 ehfEdcWorkFlow=genericDao.save(ehfEdcWorkFlow);
						 if(ehfEdcWorkFlow!=null){
							 
							 successMsg="Suc";
						 }
						
						
					}
					
					if(btnType.equalsIgnoreCase("Reject")){
						
						
						EhfEdcWorkFlow ehfEdcWorkFlow =lStEhfEdcWorkFlow.get(0);
						
						 ehfEdcWorkFlow.setLstUpdUsr(userId);
						 ehfEdcWorkFlow.setLstUpddt(new Timestamp(new Date().getTime()));
						ehfEdcWorkFlow.setCurrOwnerGrp("");
						ehfEdcWorkFlow.setCurrWorkFlowId("NA");
						ehfEdcWorkFlow.setPrevOwnerGrp("GP16");
						ehfEdcWorkFlow.setPrevWorkFlowId("WF_EDC_DEEMPANELSPEC_CEO_APRVL");
						prevCurrentOwner="";
						if((prevCurrentOwner==null||prevCurrentOwner=="")){
							
							if("Reject".equalsIgnoreCase(btnType)){
								
								ehfEdcWorkFlow.setStatus("R");
							}
							if("Approve".equalsIgnoreCase(btnType)){
								
								ehfEdcWorkFlow.setStatus("A");
							}
							
						}
						else{
							
								ehfEdcWorkFlow.setStatus("P");
						}
						
						 ehfEdcWorkFlow=genericDao.save(ehfEdcWorkFlow);
						 if(ehfEdcWorkFlow!=null){
							 
							 successMsg="Suc";
						 }
										
						
						
					}
					
						
				
				}
				
				
				 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionTmpList[1],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
				 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
				
				 List<EhfEdcAudit> auditList=genericDao.findAllByCriteria(EhfEdcAudit.class,criteriaList);
				 if(auditList.size()>0) {
						lineNo = auditList.get(0).getId().getActOrder();
						}
				 criteriaList.removeAll(criteriaList);
				 
				 
				 if(auditList.size()>0){
					 
					  assigndDate=auditList.get(0).getActdDt();
					  if(auditList.get(0).getStartDate()!=null)
					  {
						  startDate=auditList.get(0).getStartDate();
					  }
					  if(auditList.get(0).getEndDate()!=null)
					  {
						  endDate=auditList.get(0).getEndDate();;
					  }
				 }
				
				 if((prevCurrentOwner==null||prevCurrentOwner=="")){
						
						if(btnType.equalsIgnoreCase("Reject")){
							
							status="R";
						}
						if(btnType.equalsIgnoreCase("Approve")){
							
							status="A";
						}
						 
					
					}
					else{
						
						status="P";
					}
				 
				 
				 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionTmpList[1],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 criteriaList.add(new GenericDAOQueryCriteria("id.hospId", actionTmpList[0],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 lStEhfEdcWorkFlow=genericDao.findAllByCriteria(EhfEdcWorkFlow.class,criteriaList);
			
				 for(int i=0;i<auditList.size();i++)
					{
					 EhfEdcAudit	ehfEdcAudit=new EhfEdcAudit();
					 
					 EhfEdcAuditId ehfEdcAuditId=new EhfEdcAuditId();
					ehfEdcAudit.setId(new EhfEdcAuditId(auditList.get(i).getId().getActionId()
							, lineNo+1,i+1));
					ehfEdcAudit.setActor(userId);
					ehfEdcAudit.setGrpId("GP16");
					if(lStEhfEdcWorkFlow.size()>0){
						ehfEdcAudit.setCurrentWorkFlowId(lStEhfEdcWorkFlow.get(0).getPrevWorkFlowId());
								
						ehfEdcAudit.setNextWorkFlwId(lStEhfEdcWorkFlow.get(0).getCurrWorkFlowId());
								
					}
				
					
					ehfEdcAudit.setRemarks("CEO Approved");
					if(auditList.get(i).getArticles()!=null){
					if(auditList.get(i).getArticles()!=null && !"".equalsIgnoreCase(auditList.get(i).getArticles()))
					{
					ehfEdcAudit.setArticles(auditList.get(i).getArticles());
					}
					}
					else
					{
						ehfEdcAudit.setArticles("NA");
					}
					if(startDate!=null)
					{
						ehfEdcAudit.setStartDate(startDate);
					}
					if(endDate!=null)
					{
						ehfEdcAudit.setEndDate(endDate);
					}
					ehfEdcAudit.setActionType(auditList.get(i).getActionType());
					ehfEdcAudit.setAssigndDt(assigndDate);
					ehfEdcAudit.setActdDt(new Timestamp(new Date().getTime()));
					ehfEdcAudit.setStatus(status);
					
					ehfEdcAuditListSave.add(ehfEdcAudit);
					}
					ehfEdcAuditListSave=(List<EhfEdcAudit>) genericDao.saveAll(ehfEdcAuditListSave); 
					if(ehfEdcAuditListSave.size()>0)
					{
						successMsg=successMsg+"cess";
					}
			
			
		return successMsg;
				
				
				
			}
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		return successMsg;
	}



	@Override
	public boolean updateCeoStatus(String btnType, String userId,
			String actionAndHospList, String scheme) {
		
		boolean msg=false;
		if(btnType.equalsIgnoreCase("Approve")){
			
			msg=true;
		}else{
			
			msg=false;
		}
		String hospitalStatusMsg="";
		List<EhfEdcWorkFlow> lStEhfEdcWorkFlow=null;
		try{
			
			String[] tempList = actionAndHospList.split("~");
			for(int j=0;j<tempList.length;j++)
			{
				String hospId;
				
				String[] actionTmpList = tempList[j].split("@");
				 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
				 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionTmpList[1],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 criteriaList.add(new GenericDAOQueryCriteria("id.hospId", actionTmpList[0],
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				 lStEhfEdcWorkFlow=genericDao.findAllByCriteria(EhfEdcWorkFlow.class,criteriaList);
				 
				 if(lStEhfEdcWorkFlow!=null && lStEhfEdcWorkFlow.size()>0){
					 
					 List<UtilityVO> spltyList=null;
					 spltyList=getSpltyDempanelList(lStEhfEdcWorkFlow.get(0).getId().getActionId());
				 hospitalStatusMsg= updateHospitalStatus(lStEhfEdcWorkFlow.get(0).getId().getActionId(), lStEhfEdcWorkFlow.get(0).getActionType(),
							 lStEhfEdcWorkFlow.get(0).getId().getHospId(), btnType, spltyList,
							 userId, scheme);
				 }
				 
				 
			}
			
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		
		return msg;
	}



	@Override
	public String updateHospitalStatus(String actionID, String actionType,
			String hospId, String buttonValue, List<UtilityVO> spltyList1,
			String userName, String scheme) {
		String msg="";
		String msgTemp="";
		List<EhfmHospitals> ehfHospitalsList=new ArrayList<EhfmHospitals>();
		List<EhfmEDCHospActionDtls> edchospDtlsLst=new ArrayList<EhfmEDCHospActionDtls>();
		EhfmEDCHospActionDtls edcHospDtls=new EhfmEDCHospActionDtls();
		EhfmHospCatMst ehfmHospSplty;
		EhfmHospitals EhfmHospitalsObj=new EhfmHospitals();
		List<EhfmHospCatMst> ehfHospSpltyList=new ArrayList<EhfmHospCatMst>();
		List<EhfmHospCatMst> ehfHospSpltyListTemp=new ArrayList<EhfmHospCatMst>();
		if (buttonValue.equalsIgnoreCase("Approve")) {
	if(!actionType.equalsIgnoreCase("CD256") && !actionType.equalsIgnoreCase("CD250") && !actionType.equalsIgnoreCase("CD261") && !actionType.equalsIgnoreCase("CD262") ){
			List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("hospId", hospId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
			ehfHospitalsList=genericDao.findAllByCriteria(EhfmHospitals.class,criteriaList);
			criteriaList.removeAll(criteriaList);
			criteriaList.add(new GenericDAOQueryCriteria("id.hospId", hospId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.scheme", scheme,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
			
			edchospDtlsLst=genericDao.findAllByCriteria(EhfmEDCHospActionDtls.class,criteriaList);
			
			if(ehfHospitalsList.size()>0)
			{
			
				EhfmHospitalsObj=ehfHospitalsList.get(0);
				
			 if(edchospDtlsLst.size()>0){
					
					edcHospDtls=edchospDtlsLst.get(0);
					edcHospDtls.setLstUpdUsr(userName);
					edcHospDtls.setLstUpdDt(new Timestamp(new Date().getTime()));
					
				}
				
			}
			if(actionType.equalsIgnoreCase("CD257"))
			{
				EhfmHospitalsObj.setHospActiveYN("E");
				
			}
			else if(actionType.equalsIgnoreCase("CD258"))
			{
				EhfmHospitalsObj.setHospActiveYN("D");
			}
			else if(actionType.equalsIgnoreCase("CD264"))
			{
				if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("Y"))
				{
				EhfmHospitalsObj.setHospActiveYN("S");
				}
				else if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("P")) {
					EhfmHospitalsObj.setHospActiveYN("SP");
				}
				else{
					EhfmHospitalsObj.setHospActiveYN("S");
				}
			}
			else if(actionType.equalsIgnoreCase("CD265"))
			{
				if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("Y"))
				{
				EhfmHospitalsObj.setHospActiveYN("C");
				}
				else if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("P")){
					EhfmHospitalsObj.setHospActiveYN("CP");
				}
				else{
					EhfmHospitalsObj.setHospActiveYN("C");
				}
			}
			else if(actionType.equalsIgnoreCase("CD263"))
			{
				if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("Y"))
				{
				EhfmHospitalsObj.setHospActiveYN("P");
				}
				else if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("S")){
					EhfmHospitalsObj.setHospActiveYN("SP");
				}
				else if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("C"))
				{
					EhfmHospitalsObj.setHospActiveYN("CP");
				}
				else if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("CB"))
				{
					EhfmHospitalsObj.setHospActiveYN("CBP");
				}
			}
			else if(actionType.equalsIgnoreCase("CD259"))
			{
				if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("P"))
				{
				EhfmHospitalsObj.setHospActiveYN("Y");
				}
				else{
					EhfmHospitalsObj.setHospActiveYN(EhfmHospitalsObj.getHospActiveYN().replace("P", ""));
				}
				
			}
			else if(actionType.equalsIgnoreCase("CD260"))
			{
				
				if(EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("S") || EhfmHospitalsObj.getHospActiveYN().equalsIgnoreCase("C") )
				{
				EhfmHospitalsObj.setHospActiveYN("Y");
				}
				else{
					EhfmHospitalsObj.setHospActiveYN(EhfmHospitalsObj.getHospActiveYN().replace("S", ""));
					EhfmHospitalsObj.setHospActiveYN(EhfmHospitalsObj.getHospActiveYN().replace("C", ""));
				}
			}
			
			/*
			EhfmHospitalsObj.setLstUpdDt(new Timestamp(new Date().getTime()));
			EhfmHospitalsObj.setLstUpdUser(userName);*/
			EhfmHospitalsObj=genericDao.save(EhfmHospitalsObj);
			
			
			 if(edchospDtlsLst.size()>0){
				 edcHospDtls.setHospStatus(EhfmHospitalsObj.getHospActiveYN());
					if(edcHospDtls!=null){
						
						edcHospDtls=genericDao.save(edcHospDtls);
					} 
				 
			 }
			 
			 
			 EhfmHospitalsObj.setHospActiveYN("Y");
			 genericDao.save(EhfmHospitalsObj);
			if(EhfmHospitalsObj!=null)
			{
				msg="Sucess";
			}
		}
	else if(actionType.equalsIgnoreCase("CD256")){
		List<String> args=new ArrayList<String>();
		
		for(int i=0;i<spltyList1.size();i++)
		{
			args.add(spltyList1.get(i).getVALUE());
		}
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("id.catId", args,
				GenericDAOQueryCriteria.CriteriaOperator.IN));
		criteriaList.add(new GenericDAOQueryCriteria("id.hospId", hospId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		ehfHospSpltyList=genericDao.findAllByCriteria(EhfmHospCatMst.class,criteriaList);
		criteriaList.removeAll(criteriaList);
		if(ehfHospSpltyList.size()>0)
		{
			for(int i=0;i<ehfHospSpltyList.size();i++){
			ehfmHospSplty=new EhfmHospCatMst();
			ehfmHospSplty=ehfHospSpltyList.get(i);
			ehfmHospSplty.setIsActvFlg("N");
			ehfmHospSplty.setLstUpDt(new Timestamp(new Date().getTime()));
			ehfmHospSplty.setLstUpdUsr(userName);
			ehfHospSpltyListTemp.add(ehfmHospSplty);
			}
			ehfHospSpltyListTemp=(List<EhfmHospCatMst>) genericDao.saveAll(ehfHospSpltyListTemp);
			if(ehfHospSpltyListTemp!=null)
			{
				msgTemp="Sucess";
			}
		}
	}
		}
		return msg;
	}



	@Override
	public List<UtilityVO> getSpltyDempanelList(String actionId) {

		List<UtilityVO> spltyListTemp=null;
		List<UtilityVO> spltyList=null;
		String splties;
		String[] spltyArray;
		StringBuffer queryTemp=new StringBuffer();
		StringBuffer query=new StringBuffer();
		String[] args=new String[1];
		
		args[0]=actionId;
		queryTemp.append(" select specialityDeempaneled as VALUE from EhfEdcWorkFlow where id.actionId=?");
		spltyListTemp=genericDao.executeHQLQueryList(UtilityVO.class,queryTemp.toString(),args);
		
		if(spltyListTemp.size()>0)
		{
			splties=spltyListTemp.get(0).getVALUE();
			if(splties!=null && !"".equalsIgnoreCase(splties)){
				spltyArray=splties.split(",");
				String[] args1=new String[spltyArray.length];
				query.append("select ehs.facilityId as VALUE,ehs.facilityDtlName as LBL from EhfmFacilityMaster ehs");
				query.append(" where ehs.facilityId in (");
				for(int i=0;i<spltyArray.length;i++)
				{
					
					args1[i]=spltyArray[i];
					if(i!=spltyArray.length-1)
					{
					query.append(" ?,");
					}
					if(i==spltyArray.length-1)
					{
						query.append(" ?)");
					}
				}
				spltyList=genericDao.executeHQLQueryList(UtilityVO.class,query.toString(),args1);
				
			}
		
		}
			return spltyList;
	}
	
	public List<EmpanelHospVO> getHospDtls(String lStrHospInfoId)
	{
		List<EmpanelHospVO> hospDtlsList = null;
		StringBuffer query= new StringBuffer();
		String[] args = new String[5];
		args[0] = lStrHospInfoId;
		args[1] = lStrHospInfoId;
		args[2] = "LH6";
		args[3] = "LH1";
		args[4] = "GP25";
		
		query.append("select ad.hospinfo_id HOSPID,eh.hosp_name HOSPNAME,eh.hosp_category HOSPTYPE,c.cmb_dtl_name APPTYPE,eh.hosp_bed_strength BEDSTRNGTH,eh.hosp_md_tel_ph MDMOBILE,eh.applictn_type VALUE,");
		query.append(" eh.hosp_add ||' '|| eh.hosp_street ||' '|| elm.loc_name ||', '||  dis.loc_name||', '|| st.loc_name HOSPLOCATION,");
		query.append(" ad.inspc_approver_remarks INSPRMKS, fm.EOJEORECOMMENDED,");
		query.append(" to_char(ad.eo_jeo_approver_remarks) EDCRMKS,stragg(sca.sca_rmks) SCARMKS, to_char(ad.ceo_approver_remarks) CEORMKS");
		query.append(" from ehf_empnl_aprvl_dtls ad, ehf_empnl_hospinfo eh,ehfm_locations dis,ehfm_locations st,ehfm_locations elm,");
		query.append(" (select wm_concat(facility_dtl_name) EOJEORECOMMENDED from EHFM_facility_master afm,");
		query.append(" ( WITH DATA AS ( select eo_jeo_recomended from EHF_empnl_aprvl_dtls  where hospinfo_id = ?) "); 
		query.append(" SELECT trim(regexp_substr(eo_jeo_recomended, '[^,]+', 1, LEVEL)) eo_jeo_recomended ");
		query.append(" FROM DATA CONNECT BY instr(eo_jeo_recomended, ',', 1, LEVEL - 1) > 0) fac");
		query.append(" where afm.facility_id = fac.eo_jeo_recomended) fm,ehf_empnl_scarmks sca,ehfm_cmb_dtls c ");
		query.append(" where eh.hospinfo_id = ad.hospinfo_id and dis.loc_id = eh.district_code and st.loc_id = eh.state_code and elm.loc_id(+)=eh.mandal ");
		query.append(" and sca.hospinfo_id = ad.hospinfo_id and c.cmb_dtl_id = eh.applictn_type and ad.hospinfo_id = ? and dis.loc_hdr_id = ? and st.loc_hdr_id = ? and sca.crt_user = ?");
		query.append("  group by ad.hospinfo_id,eh.hosp_name,eh.hosp_category,c.cmb_dtl_name, eh.hosp_bed_strength,eh.hosp_md_tel_ph,eh.applictn_type,");
		query.append(" eh.hosp_add ||' '|| eh.hosp_street ||' '|| elm.loc_name ||', '||  dis.loc_name||', '|| st.loc_name,ad.inspc_approver_remarks,");
		query.append(" fm.EOJEORECOMMENDED,to_char(ad.eo_jeo_approver_remarks), to_char(ad.ceo_approver_remarks)");
		hospDtlsList = genericDao.executeSQLQueryList(EmpanelHospVO.class, query.toString(), args);
		
		return hospDtlsList;
		
		
	}
	
	@Override
	public List<AttachmentVO> viewAttachments(String crRefNo) {
		 Configuration config = configurationService.getConfiguration();
			String mapPath = config.getString("mapPath");
			List<AttachmentVO> attachmentVOList = new ArrayList<AttachmentVO>();
			try {
				 List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
				List<EhfEdcAttachments> listEhfCrAttachments = new ArrayList<EhfEdcAttachments>();
				 critList.add(new GenericDAOQueryCriteria("id.actionId", crRefNo,
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfEdcAttachments> ehfEdcAttachmentsList = genericDao
							.findAllByCriteria(EhfEdcAttachments.class, critList);
				critList.removeAll(critList);
				if (ehfEdcAttachmentsList != null && ehfEdcAttachmentsList.size() > 0) {
					for (int i = 0; i < ehfEdcAttachmentsList.size(); i++) {
						AttachmentVO attachmentVO = new AttachmentVO();
						attachmentVO.setACTUAL_NAME( ehfEdcAttachmentsList.get(i).getActualName());
						attachmentVO.setAttachPath(mapPath + 
								ehfEdcAttachmentsList.get(i).getAttachPath());
						attachmentVO.setActOrder(ehfEdcAttachmentsList.get(i).getActOrder());
						attachmentVOList.add(attachmentVO);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return attachmentVOList;
	}

	@Override
	public String getHospLocation(String hospId) {
		String hospLocation="";
		List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		if(hospId!=null && !"".equalsIgnoreCase(hospId)){
			String hospDist="";
			String hospState="";
		EhfmHospitals  dtls=genericDao.findById(EhfmHospitals.class, String.class, hospId);
		if(dtls!=null){
		
			if(dtls.getHospDist()!=null && !"".equalsIgnoreCase(dtls.getHospDist())){
		     		
				crList.add(new GenericDAOQueryCriteria("id.locId",dtls.getHospDist(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfmLocations>  loc=genericDao.findAllByCriteria(EhfmLocations.class,crList );
				if(loc!=null && loc.size()>0){
					hospDist=loc.get(0).getLocName();
				}
			}
			hospLocation="HOUSE NO: "+dtls.getHouseNumber()+","+"STREET: "+dtls.getStreet()+","+"CITY: "+dtls.getHospCity()+","+"DISTRICT:"+hospDist+","+"STATE"+hospState;
		}
		}
		return hospLocation;
	}
	public String getTypeOfCR(String actionId){
		 String workFlowId=null;
		 List<EhfEdcWorkFlow> lStEhfEdcWorkFlow=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEhfEdcWorkFlow=genericDao.findAllByCriteria(EhfEdcWorkFlow.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	 
		 if(lStEhfEdcWorkFlow.size()>0){
			 workFlowId=lStEhfEdcWorkFlow.get(0).getCurrWorkFlowId();
		 }
		 
		 return workFlowId;
	}
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
	 
	 public List<List<EdcRequestRemarksVO>> getPreviousRemarks(String actionId,
			Integer grpLevel) {
		 String actorName=null;
		 int actOrder=0;
		 Configuration config = configurationService.getConfiguration();
		 List<EhfEdcAudit> lStEdcrAudit=null;
		 List<EhfEdcAudit> lStEdcrAuditTemp=null;
		 List<EdcRequestRemarksVO> lStEdcRequestRemarksVO=null;
		 List<List<EdcRequestRemarksVO>> listOfList=new  ArrayList<List<EdcRequestRemarksVO>>();
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
		 lStEdcrAuditTemp=genericDao.findAllByCriteria(EhfEdcAudit.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if(lStEdcrAuditTemp.size()>0)
		 {
			 actOrder= lStEdcrAuditTemp.get(0).getId().getActOrder();
		 }
		 
		 for(int i=0;i<actOrder;i++)
		 {
		 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
		 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder",i+1,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.remarksOrder", "",GenericDAOQueryCriteria.CriteriaOperator.ASC));
		 lStEdcrAudit=genericDao.findAllByCriteria(EhfEdcAudit.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 
		 lStEdcRequestRemarksVO=new ArrayList<EdcRequestRemarksVO>(); 
		 EdcRequestRemarksVO edcRequestRemarksVO = null;
		 String appRejWorkFlowName=null;
		 String workFlowName=null;
		 String articleValue="";
		 String articleName="";
		 for(EhfEdcAudit ehfEdcAudit:lStEdcrAudit ){
			 edcRequestRemarksVO=new EdcRequestRemarksVO();
			 edcRequestRemarksVO.setRemarksDt(ehfEdcAudit.getActdDt());
			 if(ehfEdcAudit.getId().getActOrder()==1)
			 {
				 if(ehfEdcAudit.getStartDate()!=null)
				 {
					 edcRequestRemarksVO.setStartDateValue(ehfEdcAudit.getStartDate());
				 }
				 if(ehfEdcAudit.getEndDate()!=null)
				 {
					 edcRequestRemarksVO.setEndDateValue(ehfEdcAudit.getEndDate());
				 }
			 }
			 if(ehfEdcAudit.getActor()!=null && ehfEdcAudit.getActor()!=""){
			actorName=getActorName(ehfEdcAudit.getActor());
			 }
			 edcRequestRemarksVO.setUpdatedBy(actorName);
			 if(ehfEdcAudit.getNextWorkFlwId()!=null && ehfEdcAudit.getNextWorkFlwId()!=""){
				 
			workFlowName=config.getString((ehfEdcAudit.getNextWorkFlwId()).trim());
			if(ehfEdcAudit.getNextWorkFlwId().equals("WF_EDC_CSUSPENSION_MEDCO_REPLY"))
			{
				workFlowName="MEDCO";
			}
			//logger.info(" for testing sucess "+config.getString("WF_EDC_REVOCATION_MEDCO_VIEW"));
			//logger.info(" for testing "+config.getString("WF_EDC_CSUSPENSION_MEDCO_REPLY"));
			
			//logger.info("in of edcserviceimpl before config ehfEdcAudit.getNextWorkFlwId()"+ehfEdcAudit.getNextWorkFlwId());
			//System.out.println("in of edcserviceimpl before config ehfEdcAudit.getNextWorkFlwId()"+ehfEdcAudit.getNextWorkFlwId());
			 }
			if(workFlowName==null||workFlowName==""){
		
				if(ehfEdcAudit.getCurrentWorkFlowId()!=null && ehfEdcAudit.getCurrentWorkFlowId()!=""){
			appRejWorkFlowName= config.getString(ehfEdcAudit.getCurrentWorkFlowId());
				}
			}
			
			
			if(ehfEdcAudit.getStatus().equalsIgnoreCase("P")){
				
				
				edcRequestRemarksVO.setAction("Pending with "+workFlowName);
				//logger.info("in of edcserviceimpl workFlowName"+workFlowName);
				System.out.println("in of edcserviceimpl workFlowName"+workFlowName);
				
			}
			if(ehfEdcAudit.getStatus().equalsIgnoreCase("A")){
				
				edcRequestRemarksVO.setAction("Approved by "+appRejWorkFlowName);
				 
			}
			if(ehfEdcAudit.getStatus().equalsIgnoreCase("R")){

				edcRequestRemarksVO.setAction("Rejected by "+appRejWorkFlowName);
			}
			
			edcRequestRemarksVO.setRemarkValue(ehfEdcAudit.getRemarks());
			if(ehfEdcAudit.getArticles()!=null && !"".equalsIgnoreCase(ehfEdcAudit.getArticles()) && !"NA".equalsIgnoreCase(ehfEdcAudit.getArticles()))
			{
				articleValue="";
				articleName="";
				articleValue=ehfEdcAudit.getArticles();
				articleName=getArticleName(articleValue);
				
				edcRequestRemarksVO.setArticleValue(articleValue);
				edcRequestRemarksVO.setArticleName(articleName);
			}
			else{
				edcRequestRemarksVO.setArticleValue("NA");
				edcRequestRemarksVO.setArticleName("General Remarks");
			}
			edcRequestRemarksVO.setActOrder(ehfEdcAudit.getId().getActOrder());
			lStEdcRequestRemarksVO.add(edcRequestRemarksVO);
		 }
		 listOfList.add(lStEdcRequestRemarksVO);
		 }
		 return listOfList;
	}

	
	public String getArticleName(String articleValue)
	{
		String articleName="";
		List<EhfmEmpnlArticleMstr> ehfmEmpnlArticleMstr=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("articleId", articleValue,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
		 criteriaList.add(new GenericDAOQueryCriteria("articleId", articleValue,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 ehfmEmpnlArticleMstr=genericDao.findAllByCriteria(EhfmEmpnlArticleMstr.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if(ehfmEmpnlArticleMstr.size()>0)
		 {
			 articleName=ehfmEmpnlArticleMstr.get(0).getArticleDesc();
		 }
		return articleName;
	}
	
	public String getActorName(String userId){

		 String actorName=null;

		EhfmUsers ehfmUser =genericDao.findById(EhfmUsers.class,String.class,userId);
		if(ehfmUser!=null){
			
			actorName=ehfmUser.getFirstName()+" "+ehfmUser.getLastName();
			
		}
		return actorName;
		}


	@Override
	public String getHospSplties(String hospId) {
		String spltyString="";
		
		List<EdcHospitalListVO> edcHospitalListVoObj = null;

		StringBuffer query = new StringBuffer();
		String[] args = new String[2];
		args[0] = hospId;
		args[1] = hospId;
		try {
			query.append("SELECT ehfu.last_name as MEDCONAME,ehfs.specialities as HOSPSPECIALITIES,");
			query.append(" ehfu.email_id as MEDCOEMAIL,ehfu.mobile_no as MEDCOMOBILE ");
			query.append(" FROM ehfm_users ehfu,(select WM_CONCAT(ehfs.ICD_CAT_CODE) as specialities,ehfs.hosp_id");
			query.append(" from ehfm_hosp_speciality ehfs where ehfs.is_active_flg = 'Y'");
			query.append(" group by ehfs.hosp_id) ehfs where ehfu.user_id in (select ehfd.medco_id");
			query.append(" FROM ehfm_medco_dtls ehfd where ehfd.hosp_id = ?)");
			query.append(" AND ehfs.hosp_id = ? and  ehfu.service_flg='Y'");

			edcHospitalListVoObj = genericDao.executeSQLQueryList(
					EdcHospitalListVO.class, query.toString(), args);
			String hospSpltyString=edcHospitalListVoObj.get(0).getHOSPSPECIALITIES();
			String[] hospTmpStr=hospSpltyString.split(",");
			if(hospTmpStr!=null && hospTmpStr.length>0){
			for(int i=0;i<hospTmpStr.length;i++){
				EhfmSpecialities spltyies=genericDao.findById(EhfmSpecialities.class, String.class, hospTmpStr[i]);
				spltyString=spltyString+spltyies.getDisMainName()+", ";
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spltyString;
	}


	@Override
	public String getHospType(String hospId) {
		
		String hospType="";
		EhfmHospitals hospDtls=genericDao.findById(EhfmHospitals.class, String.class, hospId);
		
		
		if(hospDtls!=null){
			hospType=Character.toString(hospDtls.getHospType());
			
		}
		
		
		return hospType;
	}
	
	public String getActionType(String actionId) {
		// TODO Auto-generated method stub
		String actionTypeTemp="";
		StringBuffer query=new StringBuffer();
		String[] args=new String[1];
		args[0]=actionId;
		query.append(" select eh.actionType as LBL from EhfEdcWorkFlow eh where id.actionId=?");
		
		List<UtilityVO> action=genericDao.executeHQLQueryList(UtilityVO.class, query.toString(), args);
		if(action.size()>0)
		{
			actionTypeTemp=action.get(0).getLBL();
		}
		return actionTypeTemp;
	}
	
	
	public List<UtilityVO> getArticleList(String actionId) {
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		List<UtilityVO> articleList=new ArrayList<UtilityVO>();
		int act=1;
		UtilityVO utilityVO=null;
		 List<EhfEdcAudit> lStEdcrAuditTemp=null;
		 criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", act,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEdcrAuditTemp=genericDao.findAllByCriteria(EhfEdcAudit.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		if(lStEdcrAuditTemp.size()>0)
		{
			for(int i=0;i<lStEdcrAuditTemp.size();i++)
			{
				utilityVO=new UtilityVO();
				utilityVO.setVALUE(lStEdcrAuditTemp.get(i).getArticles());
				utilityVO.setLBL(getArticleName(lStEdcrAuditTemp.get(i).getArticles()));
				articleList.add(utilityVO);
			}
		}
		 return articleList;
	}
	
	
	
	public List<EdcRequestRemarksVO> getEdcActionDtls(String actionId) {
		Configuration config = configurationService.getConfiguration();
		int count=1;
		String articleName="";
		String articleValue="";
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 List<EhfEdcAudit> lStEdcrAudit=null;
		 List<EdcRequestRemarksVO> edcActionDtlsList=new ArrayList<EdcRequestRemarksVO>();
	     EdcRequestRemarksVO edcRequestRemarksVO=null;
		criteriaList.add(new GenericDAOQueryCriteria("id.actionId", actionId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.actOrder",count,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEdcrAudit=genericDao.findAllByCriteria(EhfEdcAudit.class,criteriaList);
		 criteriaList.removeAll(criteriaList);
		 if(lStEdcrAudit.size()>0)
		 {
			 for(EhfEdcAudit ehfEdcAudit:lStEdcrAudit ){
				 edcRequestRemarksVO=new EdcRequestRemarksVO();
				 edcRequestRemarksVO.setActionId(ehfEdcAudit.getId().getActionId());
				 edcRequestRemarksVO.setActionType(config.getString(ehfEdcAudit.getActionType()));
				 if(ehfEdcAudit.getStartDate()!=null)
				 {
				 edcRequestRemarksVO.setStartDateValue(ehfEdcAudit.getStartDate());
				 }
				 if(ehfEdcAudit.getEndDate()!=null)
				 {
				 edcRequestRemarksVO.setEndDateValue(ehfEdcAudit.getEndDate());
				 }
				 edcRequestRemarksVO.setRemarkValue(ehfEdcAudit.getRemarks());
				
				 if(ehfEdcAudit.getArticles()!=null && !"NA".equalsIgnoreCase(ehfEdcAudit.getArticles()) )
				 {
					 	articleValue="";
						articleName="";
						articleValue=ehfEdcAudit.getArticles();
						articleName=getArticleName(articleValue);
						
						edcRequestRemarksVO.setArticleValue(articleValue);
						edcRequestRemarksVO.setArticleName(articleName);
					}
					else{
						edcRequestRemarksVO.setArticleValue("NA");
						edcRequestRemarksVO.setArticleName("General Remarks");
					}
				 edcActionDtlsList.add(edcRequestRemarksVO);
			 }
		 }
		return edcActionDtlsList;
	}
	
	public String verifyIfMedco(String userId){
		 
		 String hospId=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("id.userId", userId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmMedcoDetails> ehfmMedcoDetailsList = genericDao
					.findAllByCriteria(EhfmMedcoDetails.class, criteriaList);
			 criteriaList.removeAll(criteriaList);	
			 if(ehfmMedcoDetailsList.size()>0){
				 
				 hospId=ehfmMedcoDetailsList.get(0).getId().getHospId();
			 }
			
			 return hospId;
		 
		}

	public Number getDutyDctrsCnt(String lStrHospInfoId)
	{
		Number dutyDctrCnt=0;
		List<EmpanelHospVO> dutyDctrsCntList = null;
		StringBuffer query= new StringBuffer();
		String[] args = new String[2];
		String lStrTable = "";
		if(lStrHospInfoId != null && lStrHospInfoId != null)
		{
			if(lStrHospInfoId.contains("HSINE"))
			{
				lStrTable = "EHFM_EMPNL_LABELS";
			}
			else if(lStrHospInfoId.contains("HSID"))
	         {
	        	 if(lStrHospInfoId.contains("HSIDC") || lStrHospInfoId.contains("HSIDH"))
	        		 lStrTable = "ehfm_empnl_dental_labels";
	        	 else 
	        		 lStrTable = "ehfm_empnl_dp_labels";	 
	         }
			else if(lStrHospInfoId.contains("HSIPE"))
	         {
	        		 lStrTable = "ehfm_empnl_dp_labels";	 
	         }
		}
		args[0] = lStrHospInfoId;
		args[1] = "D";
		
		query.append("  SELECT count(*) COUNT FROM "+lStrTable+" AEL, EHF_EMPNL_MANPOWER_DTLS AEMD");
		query.append("  WHERE AEMD.HOSPINFO_ID = ? AND AEL.SUBITEM_NO = AEMD.SUBITEM_ID AND AEL.STAFF_TYPE = ?");
		
		dutyDctrsCntList = genericDao.executeSQLQueryList(EmpanelHospVO.class, query.toString(), args);
		if(dutyDctrsCntList.size() > 0)
		{
			dutyDctrCnt = dutyDctrsCntList.get(0).getCOUNT();
		}
		return dutyDctrCnt;
	}
	public Number getSplstsCnt(String lStrHospInfoId)
	{
		Number splstCnt=0;
		List<EmpanelHospVO> splstCntList = null;
		StringBuffer query= new StringBuffer();
		String[] args = new String[2];
		
		String lStrTable = "";
		if(lStrHospInfoId != null && lStrHospInfoId != null)
		{
			if(lStrHospInfoId.contains("HSINE"))
			{
				lStrTable = "EHFM_EMPNL_LABELS";
			}
			else if(lStrHospInfoId.contains("HSID"))
	         {
	        	 if(lStrHospInfoId.contains("HSIDC") || lStrHospInfoId.contains("HSIDH"))
	        		 lStrTable = "ehfm_empnl_dental_labels";
	        	 else 
	        		 lStrTable = "ehfm_empnl_dp_labels";	 
	         }
			else if(lStrHospInfoId.contains("HSIPE"))
	         {
	        		 lStrTable = "ehfm_empnl_dp_labels";	 
	         }
		}
		
		args[0] = lStrHospInfoId;
		args[1] = "S";
		
		query.append("  SELECT count(*) COUNT FROM "+lStrTable+" AEL, EHF_EMPNL_MANPOWER_DTLS AEMD ");
		query.append(" WHERE AEMD.HOSPINFO_ID = ? AND AEL.SUBITEM_NO = AEMD.SUBITEM_ID AND AEL.STAFF_TYPE = ? AND SPECIALITY NOT LIKE 'G%' ");
		
		splstCntList = genericDao.executeSQLQueryList(EmpanelHospVO.class, query.toString(), args);
		if(splstCntList.size() > 0)
		{
			splstCnt = splstCntList.get(0).getCOUNT();
		}
		
		return splstCnt;
	}
	public Number getParamdcsCnt(String lStrHospInfoId)
	{
		Number paramdcsCnt=0;
		List<EmpanelHospVO> paramdcsCntList = null;
		StringBuffer query= new StringBuffer();
		String[] args = new String[2];

		String lStrTable = "";
		if(lStrHospInfoId != null && lStrHospInfoId != null)
		{
			if(lStrHospInfoId.contains("HSINE"))
			{
				lStrTable = "EHFM_EMPNL_LABELS";
			}
			else if(lStrHospInfoId.contains("HSID"))
	         {
	        	 if(lStrHospInfoId.contains("HSIDC") || lStrHospInfoId.contains("HSIDH"))
	        		 lStrTable = "ehfm_empnl_dental_labels";
	        	 else 
	        		 lStrTable = "ehfm_empnl_dp_labels";	 
	         }
			else if(lStrHospInfoId.contains("HSIPE"))
	         {
	        		 lStrTable = "ehfm_empnl_dp_labels";	 
	         }
		}
		
		
		query.append("SELECT count(*) COUNT FROM "+lStrTable+" AEL, EHF_EMPNL_MANPOWER_DTLS AEMD  WHERE AEMD.HOSPINFO_ID = ? ");
		query.append(" AND AEL.SUBITEM_NO = AEMD.SUBITEM_ID  AND AEL.STAFF_TYPE = ? ");
		
		args[0] = lStrHospInfoId;
		args[1] = "P";
		
		paramdcsCntList = genericDao.executeSQLQueryList(EmpanelHospVO.class, query.toString(), args);
		if(paramdcsCntList.size() > 0)
		{
			paramdcsCnt = paramdcsCntList.get(0).getCOUNT();
		}
		
		
		return paramdcsCnt;
	}

}
