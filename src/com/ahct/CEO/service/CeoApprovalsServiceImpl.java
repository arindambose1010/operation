package com.ahct.CEO.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.ahct.CEO.vo.ChangeMgmtVO;
import com.ahct.CEO.vo.SQLChangeMgmtTransVO;
import com.ahct.model.SgvaCRMgmtAttach;
import com.ahct.model.SgvaCRMgmtDtls;
import com.ahct.model.SgvaCRMgmtMvmnt;
import com.ahct.model.SgvaCRMgmtMvmntId;
import com.ahct.model.SgvaCRMgmtRemarks;
import com.ahct.model.SgvaCRMgmtRemarksId;
import com.ahct.model.SgvaCRMgmtTestScenMst;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfmDepts;
import com.ahct.model.SgvaCRMgmtAttachId;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class CeoApprovalsServiceImpl implements CeoApprovalsService {

	
	 HibernateTemplate hibernateTemplate ;
	    GenericDAO genericDao;
	    public HibernateTemplate getHibernateTemplate() {
			return hibernateTemplate;
		}
		public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
			this.hibernateTemplate = hibernateTemplate;
		}
	    public void setGenericDao(GenericDAO genericDao) {
	        this.genericDao = genericDao;
	    }   
	 
	  
	    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");       
	    String[] empDtls=null;
	    
	    private static ConfigurationService configurationService ;
	    private static CompositeConfiguration config ;

	    static {
	      configurationService = ConfigurationService.getInstance () ;
	      config = configurationService.getConfiguration () ;
	    }
	    
	 
	    private static Logger glogger = Logger.getLogger ( CeoApprovalsServiceImpl.class ) ; 
	    
	    public CeoApprovalsServiceImpl() { }
	



	@Override
	public List<SQLChangeMgmtTransVO> getCeoWorkList(String lStrUserState)
	{
		List<SQLChangeMgmtTransVO> myCrRqstList = null;
		 StringBuffer query = new StringBuffer();
		  String[] args = new String[1];
		  if("CD201".equalsIgnoreCase(lStrUserState))
		  {
			  args[0] = "DP7_DG9999USR83586";
		  }
		  else if ("CD202".equalsIgnoreCase(lStrUserState))
		  {
			  args[0] = "DP7_DG9999USR88806";
		  }
		  
		  query.append(" select scd.cr_id CR_ID, scd.cr_title CR_TITLE, scd.cr_desc CR_DESC,d.dept_name CR_RAISED_DEPT,");
		  query.append(" eu.last_name EMP_FULL_NAME from sgva_crmgmt_dtls scd, ehfm_users eu, ehfm_depts d where eu.user_id = scd.emp_id and");
		  query.append("  d.dept_id = scd.created_deptid  and scd.curr_ownr_unitid = ? and scd.active = 'Y' ");
  
		  myCrRqstList=genericDao.executeSQLQueryList(SQLChangeMgmtTransVO.class,query.toString(),args);
	
		return myCrRqstList;
		
	}

	
	@Override
	public boolean getNextUnitAndSave(String lStrBtntype, String crIdList,
			String lStrCrId,String lStrUser,String lStrUserState) throws Exception 
	{
			String crId = "",status="";
			String permamentCrId="";
			boolean lStrResult= true;
			/* String[] fileDetailsArray = null;
			 SgvaCRMgmtAttach sgvaCRMgmtAttach = null*/;
		 
	        SgvaCRMgmtMvmnt sgvaCRMgmtMvmnt=null;
	        SgvaCRMgmtRemarks sgvaCRMgmtRemarks=null;
	        try
	        {     String[] tempList = crIdList.split("~");
		        for(int j=0;j<tempList.length;j++)
		        {
		        	lStrCrId = tempList[j];
		        	SgvaCRMgmtDtls  sgvaCRMgmtDtls = genericDao.findById(SgvaCRMgmtDtls.class,String.class,lStrCrId);
		        	if(lStrCrId.startsWith("T_"))
		        	/*if(lStrCrId!=null)*/
		            {
		                   permamentCrId=lStrCrId.substring(2); 
		              //To retrieve all temporary recs
		              List<SgvaCRMgmtDtls> crDtls =genericDao.findAllByPropertyMatch(SgvaCRMgmtDtls.class,"crId",lStrCrId);
		              List<GenericDAOQueryCriteria> CriteriaList=new ArrayList<GenericDAOQueryCriteria>();
		              CriteriaList.add(new GenericDAOQueryCriteria("id.crId",lStrCrId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		              List<SgvaCRMgmtAttach> crAttachDtls =genericDao.findAllByCriteria(SgvaCRMgmtAttach.class,CriteriaList);
		              List<SgvaCRMgmtMvmnt> crMvmtDtls =genericDao.findAllByCriteria(SgvaCRMgmtMvmnt.class,CriteriaList);
		              List<GenericDAOQueryCriteria> remarkCriteriaList=new ArrayList<GenericDAOQueryCriteria>();;
		              remarkCriteriaList.add(new GenericDAOQueryCriteria("id.remarksId",lStrCrId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		              List<SgvaCRMgmtRemarks> crRemarkDtls =genericDao.findAllByCriteria(SgvaCRMgmtRemarks.class,remarkCriteriaList);
		              //Test scenarios crid updation
		              List<SgvaCRMgmtTestScenMst> crTestScenMstList=genericDao.findAllByPropertyMatch(SgvaCRMgmtTestScenMst.class,"crId",lStrCrId);
		              //Update
		              for(SgvaCRMgmtTestScenMst sgvaCRMgmtTestScenMst:crTestScenMstList)
		              {
		                  sgvaCRMgmtTestScenMst.setCrId(permamentCrId);
		                  sgvaCRMgmtTestScenMst=genericDao.save(sgvaCRMgmtTestScenMst);
		              }
		              //To insert permanantly
		              for(SgvaCRMgmtDtls sgvaCrMgmtDtls:crDtls)
		              {
		                  SgvaCRMgmtDtls insertCRMgmtDtls=new SgvaCRMgmtDtls();
		                  if("Approve".equalsIgnoreCase(lStrBtntype))
		                  {
		                	  insertCRMgmtDtls.setCurrLogicalSubject("TCSREL_CRFLOW");  
		                	  insertCRMgmtDtls.setStatus("Pending with MR/MS Madineni Sivakumar(246433)");
		                	  insertCRMgmtDtls.setCurrOwnerUnitId("DP13_DG2001USR43244");
		                	  insertCRMgmtDtls.setActive("Y");
		                	  insertCRMgmtDtls.setCrExternalStatus("EXTSTAT9");
		                  }
		                 if("Reject".equalsIgnoreCase(lStrBtntype) && "CD201".equalsIgnoreCase(lStrUserState))
	                		  {
		                	  		insertCRMgmtDtls.setStatus(" CR rejected by Shri. Lav Agarwal,I.A.S.,(CEOAP)");
		                	  		insertCRMgmtDtls.setCurrLogicalSubject(null);
		                	  		 insertCRMgmtDtls.setActive("N");
		                	  		  insertCRMgmtDtls.setCurrOwnerUnitId("DP7_DG9999USR83586");
		                	  		insertCRMgmtDtls.setCrExternalStatus("EXTSTAT51");
	                		  }
		                  if("Reject".equalsIgnoreCase(lStrBtntype) && "CD202".equalsIgnoreCase(lStrUserState))
		                  {
		                	  insertCRMgmtDtls.setStatus(" CR rejected by Shri.Dr.Jyothi Buddha Prakash, IAS.,(CEOTS)");
		                	  insertCRMgmtDtls.setCurrLogicalSubject(null);
		                	  insertCRMgmtDtls.setActive("N");
		                	  insertCRMgmtDtls.setCurrOwnerUnitId("DP7_DG9999USR88806");
		                	  insertCRMgmtDtls.setCrExternalStatus("EXTSTAT51");
		                  }
		                  
		                  insertCRMgmtDtls.setCrAppName(sgvaCrMgmtDtls.getCrAppName());
		                  insertCRMgmtDtls.setCrAssignedEmpId(sgvaCrMgmtDtls.getCrAssignedEmpId());
		                  insertCRMgmtDtls.setCrDesc(sgvaCrMgmtDtls.getCrDesc());
		                  insertCRMgmtDtls.setCrExpectedDeliveryDt(sgvaCrMgmtDtls.getCrExpectedDeliveryDt());
		                  insertCRMgmtDtls.setCrId(permamentCrId);
		                  insertCRMgmtDtls.setCrOrgName(sgvaCrMgmtDtls.getCrOrgName());
		                  insertCRMgmtDtls.setCrPriority(sgvaCrMgmtDtls.getCrPriority());
		                  insertCRMgmtDtls.setCrRaisedDate(sgvaCrMgmtDtls.getCrRaisedDate());
		                  insertCRMgmtDtls.setCrReqTypeId(sgvaCrMgmtDtls.getCrReqTypeId());
		                  insertCRMgmtDtls.setCrRequiredDate(sgvaCrMgmtDtls.getCrRequiredDate());
		                  insertCRMgmtDtls.setCrSeverity(sgvaCrMgmtDtls.getCrSeverity());
		                  insertCRMgmtDtls.setCrtDate(sgvaCrMgmtDtls.getCrtDate());
		                  insertCRMgmtDtls.setCrTitle(sgvaCrMgmtDtls.getCrTitle());
		                  insertCRMgmtDtls.setCrtUser(sgvaCrMgmtDtls.getCrtUser());
		                  insertCRMgmtDtls.setCrType(sgvaCrMgmtDtls.getCrType());
		                  insertCRMgmtDtls.setCrWorkflowId(sgvaCrMgmtDtls.getCrWorkflowId());
		                  insertCRMgmtDtls.setCurrDeptId(sgvaCrMgmtDtls.getCurrDeptId());
		                  insertCRMgmtDtls.setDeptId(sgvaCrMgmtDtls.getDeptId());
		                  insertCRMgmtDtls.setEmpId(sgvaCrMgmtDtls.getEmpId());
		                  insertCRMgmtDtls.setUserType(sgvaCrMgmtDtls.getUserType());
		                  insertCRMgmtDtls.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
		                  insertCRMgmtDtls.setLastUpdUser(lStrUser);
		                  insertCRMgmtDtls.setNextOwnerUnitId(sgvaCrMgmtDtls.getNextOwnerUnitId());
		                  insertCRMgmtDtls.setParentCrId(sgvaCrMgmtDtls.getParentCrId());
		                  insertCRMgmtDtls.setPrevOwnerUnitId(sgvaCrMgmtDtls.getPrevOwnerUnitId());
		                  insertCRMgmtDtls.setWorkStatus(sgvaCrMgmtDtls.getWorkStatus());
		                  insertCRMgmtDtls.setSubMenuName(sgvaCrMgmtDtls.getSubMenuName());
		                  insertCRMgmtDtls.setModuleId(sgvaCrMgmtDtls.getModuleId());
		                  insertCRMgmtDtls=genericDao.save(insertCRMgmtDtls);
		                  //To update old rec with null unit and active as N
		                  sgvaCRMgmtDtls.setCurrOwnerUnitId("null");
		                  //sgvaCRMgmtDtls.setActive("N");
		                  sgvaCRMgmtDtls=genericDao.save(sgvaCRMgmtDtls);
		              }
		           /*   Long maxLineNoAttach=this.getNextLineItemNoAttach(lStrCrId);
		              for(int i=0 ; i< fileDetailsArray.length; i++) 
		              {
		                  if(fileDetailsArray[i]!=null)
		                  { 
		                  SgvaCRMgmtAttach insertCRMgmtAttach=new SgvaCRMgmtAttach();
		                  insertCRMgmtAttach.setActive(sgvaCRMgmtAttach.getActive());
		                  insertCRMgmtAttach.setAttachFileContentType(sgvaCRMgmtAttach.getAttachFileContentType());
		                  insertCRMgmtAttach.setAttachFileExtension(sgvaCRMgmtAttach.getAttachFileExtension());
		                  insertCRMgmtAttach.setAttachFileName(sgvaCRMgmtAttach.getAttachFileName());
		                  insertCRMgmtAttach.setAttachFilePath(sgvaCRMgmtAttach.getAttachFilePath());
		                  insertCRMgmtAttach.setAttachFileSize(sgvaCRMgmtAttach.getAttachFileSize());
		                  insertCRMgmtAttach.setCrtDate(sgvaCRMgmtAttach.getCrtDate());
		                  insertCRMgmtAttach.setCrtUser(sgvaCRMgmtAttach.getCrtUser());
		                  insertCRMgmtAttach.setEmpId(sgvaCRMgmtAttach.getEmpId());
		                  insertCRMgmtAttach.setLastUpdDate(sgvaCRMgmtAttach.getLastUpdDate());
		                  insertCRMgmtAttach.setLastUpdUser(sgvaCRMgmtAttach.getLastUpdUser());
		                  insertCRMgmtAttach.setRemarksLineItemNo(sgvaCRMgmtAttach.getRemarksLineItemNo());
		                  insertCRMgmtAttach.setId(new SgvaCRMgmtAttachId(permamentCrId,sgvaCRMgmtAttach.getId().getLineItemNo()));
		                  insertCRMgmtAttach=genericDao.save(insertCRMgmtAttach);
		              }
		              }*/
		               Long maxLineNo=this.getNextLineItemNoForMvmt(lStrCrId);
	                    if(maxLineNo!=null && maxLineNo!=-1)
	                    {
		                  SgvaCRMgmtMvmnt insertCRMgmtMvmnt=new SgvaCRMgmtMvmnt();
		                  if("Approve".equalsIgnoreCase(lStrBtntype))
		                  {
		                	  insertCRMgmtMvmnt.setActionFlag("F");
			                  insertCRMgmtMvmnt.setActionTaken("Approved"); 
			                  insertCRMgmtMvmnt.setToUnitId("DP13_DG2001USR43244");
			                  insertCRMgmtMvmnt.setFromLgsb("CEOAPRV_CRFLOW");
			                  insertCRMgmtMvmnt.setToLgsb("TCSREL_CRFLOW");
			                  insertCRMgmtMvmnt.setToUnitId("DP13_DG2001USR43244");
			                  insertCRMgmtMvmnt.setActive("Y");
			                  insertCRMgmtMvmnt.setToDept(sgvaCRMgmtDtls.getDeptId());
			                  if("CD201".equalsIgnoreCase(lStrUserState))
			                  {
			                	  insertCRMgmtMvmnt.setFromUnitId("DP7_DG9999USR83586"); 
			                  }
			                  else  if("CD202".equalsIgnoreCase(lStrUserState))
			                  {
			                	  insertCRMgmtMvmnt.setFromUnitId("DP7_DG9999USR88806");  
			                  }
			                  
		                  }
		                  else if("Reject".equalsIgnoreCase(lStrBtntype))
		                  {
		                	  insertCRMgmtMvmnt.setActionFlag("R");
			                  insertCRMgmtMvmnt.setActionTaken("Rejcted");
		                	  insertCRMgmtMvmnt.setToUnitId(sgvaCRMgmtDtls.getPrevOwnerUnitId());
		                	  insertCRMgmtMvmnt.setFromLgsb("CEOAPRV_CRFLOW");
			                  insertCRMgmtMvmnt.setToLgsb(null);
			                  insertCRMgmtMvmnt.setActive("N");
			                  insertCRMgmtMvmnt.setToDept(null);
		                  }
		                  insertCRMgmtMvmnt.setCrtDate(new java.sql.Timestamp(new Date().getTime()));
		                  insertCRMgmtMvmnt.setCrtUser(lStrUser);
		                  insertCRMgmtMvmnt.setFromDept(sgvaCRMgmtDtls.getDeptId());
		                  insertCRMgmtMvmnt.setId(new SgvaCRMgmtMvmntId(permamentCrId,maxLineNo));
		                  insertCRMgmtMvmnt=genericDao.save(insertCRMgmtMvmnt);
		              }
	                    Long maxLineNo1=this.getNextLineItemNoForRemarks(lStrCrId);
		                if(maxLineNo1!=null && maxLineNo1!=-1)
		                { 
		                	 SgvaCRMgmtRemarks insertCRMgmtRemarks=new SgvaCRMgmtRemarks();
		                	 if("Approve".equalsIgnoreCase(lStrBtntype))
		                	 {
		                		  insertCRMgmtRemarks.setActionTaken("Approved");
		                		  insertCRMgmtRemarks.setRemarks("Approved");
		                	 }
		                	 if("Reject".equalsIgnoreCase(lStrBtntype))
		                	 {
		                		  insertCRMgmtRemarks.setActionTaken("Rejected");
		                		  insertCRMgmtRemarks.setRemarks("Rejected");
		                	 }
		                
		                  insertCRMgmtRemarks.setActive(null);
		                  insertCRMgmtRemarks.setCrtDate(new java.sql.Timestamp(new Date().getTime()));
		                  insertCRMgmtRemarks.setCrtUser(lStrUser);
		                  insertCRMgmtRemarks.setEmpId(lStrUser);
		                  insertCRMgmtRemarks.setId(new SgvaCRMgmtRemarksId(permamentCrId,maxLineNo1));
		                  insertCRMgmtRemarks.setLastUpdDate(null);
		                  insertCRMgmtRemarks.setLastUpdUser(null);
		                 insertCRMgmtRemarks=genericDao.save(insertCRMgmtRemarks);
		              }
		            }
		        }
	        }
		          catch(Exception e)
		          {
		        	   lStrResult= false;
		              e.printStackTrace();
		          }
          return lStrResult;
		      
	        }
    public Long getNextLineItemNoForMvmt ( String pstrCrId ) throws Exception
    {
      StringBuffer query = new StringBuffer () ;
      long cnt = -1 ;
      try
      {
        query.append ( " select max(d.lineitem_no) COUNT from sgva_crmgmt_mvmnt d where d.cr_id=?" ) ;
        String[] args = new String[ 1 ] ;
        args[ 0 ] = pstrCrId ;
        List<SQLChangeMgmtTransVO> lineItemNoList = genericDao.executeSQLQueryList ( SQLChangeMgmtTransVO.class, query.toString (), args ) ;
        if ( lineItemNoList != null && !lineItemNoList.isEmpty () )
        {
          if ( lineItemNoList.get ( 0 ).getCOUNT () != null )
          {
            //cnt=Integer.parseInt(lineItemNoList.get(0).getCOUNT().toString());
            //cnt=cnt+1;
            cnt = lineItemNoList.get ( 0 ).getCOUNT ().intValue () + 1 ;
          }
          else
          {
            cnt = 0 ;
          }
        }
        else
        {
          cnt = 0 ;
        }
      }
      catch ( RuntimeException e )
      {
        e.printStackTrace () ;
        throw e ;
      }
      return cnt ;
    }
    public Long getNextLineItemNoAttach ( String pstrCrId ) throws Exception
    {
        StringBuffer query = new StringBuffer () ;
        long cnt = -1 ;
        try
        {
          query.append ( " select max(d.lineitem_no) COUNT from sgva_crmgmt_attach d where d.cr_id=?" ) ;
          String[] args = new String[ 1 ] ;
          args[ 0 ] = pstrCrId ;
          List<SQLChangeMgmtTransVO> lineItemNoList = genericDao.executeSQLQueryList ( SQLChangeMgmtTransVO.class, query.toString (), args ) ;
          if ( lineItemNoList != null && !lineItemNoList.isEmpty () )
          {
            if ( lineItemNoList.get ( 0 ).getCOUNT () != null )
            {
              cnt = lineItemNoList.get ( 0 ).getCOUNT ().intValue () + 1 ;
            }
            else    
            {
              cnt = 0 ;
            }
          }
          else
          {
            cnt = 0 ;
          }
        }
        catch ( RuntimeException e )
        {
          e.printStackTrace () ;
          throw e ;
        }
        return cnt ;
    }
    public Long getNextLineItemNoForRemarks ( String pstrCrId ) throws Exception
    {
      StringBuffer query = new StringBuffer () ;
      long cnt = -1 ;
      try
      {
        query.append ( " select max(d.lineitem_no) COUNT from sgva_crmgmt_remarks d where d.remarks_id=?" ) ;
        String[] args = new String[ 1 ] ;
        args[ 0 ] = pstrCrId ;
        List<SQLChangeMgmtTransVO> lineItemNoList = genericDao.executeSQLQueryList ( SQLChangeMgmtTransVO.class, query.toString (), args ) ;
        if ( lineItemNoList != null && !lineItemNoList.isEmpty () )
        {
          if ( lineItemNoList.get ( 0 ).getCOUNT () != null )
          {
            //cnt=Integer.parseInt(lineItemNoList.get(0).getCOUNT().toString());
            //cnt=cnt+1;
            cnt = lineItemNoList.get ( 0 ).getCOUNT ().intValue () + 1 ;
          }
          else
          {
            cnt = 0 ;
          }
        }
        else
        {
          cnt = 0 ;
        }
      }
      catch ( RuntimeException e )
      {
        e.printStackTrace () ;
        throw e ;
      }
      return cnt ;
    }
    
	public ChangeMgmtVO getCrRequestDetails(String crId) {
		 ChangeMgmtVO changeMgmtVO=null;
	        try
	        {
	            SgvaCRMgmtDtls sgvaCRMgmtDtls=genericDao.findById(SgvaCRMgmtDtls.class,String.class,crId);
	            if(sgvaCRMgmtDtls!=null)
	            {
	                changeMgmtVO=new ChangeMgmtVO();   
	                changeMgmtVO.setCrAppName(sgvaCRMgmtDtls.getCrAppName());
	                changeMgmtVO.setCrDesc(sgvaCRMgmtDtls.getCrDesc());
	                changeMgmtVO.setCrId(sgvaCRMgmtDtls.getCrId());
	                changeMgmtVO.setCrOrgName(sgvaCRMgmtDtls.getCrOrgName());
	                changeMgmtVO.setCrReqTypeId(sgvaCRMgmtDtls.getCrReqTypeId());
	                changeMgmtVO.setCrTitle(sgvaCRMgmtDtls.getCrTitle());
	                changeMgmtVO.setCrType(sgvaCRMgmtDtls.getCrType());
	                changeMgmtVO.setDeptId(sgvaCRMgmtDtls.getDeptId());
	                changeMgmtVO.setEmpId(sgvaCRMgmtDtls.getEmpId());
	                changeMgmtVO.setCurrDeptId(sgvaCRMgmtDtls.getCurrDeptId());
	                changeMgmtVO.setCurrLgsb(sgvaCRMgmtDtls.getCurrLogicalSubject());
	                changeMgmtVO.setCurrOwnerUnitId(sgvaCRMgmtDtls.getCurrOwnerUnitId());
	                changeMgmtVO.setCrPriorityId(sgvaCRMgmtDtls.getCrPriority());
	                changeMgmtVO.setCrRequiredDate(sgvaCRMgmtDtls.getCrRequiredDate());
	                changeMgmtVO.setCrAsignedEmpId(sgvaCRMgmtDtls.getCrAssignedEmpId());
	                changeMgmtVO.setCrExpectDeliveryDt(sgvaCRMgmtDtls.getCrExpectedDeliveryDt());
	                changeMgmtVO.setCrSeverityId(sgvaCRMgmtDtls.getCrSeverity());
	                changeMgmtVO.setParentCrId(sgvaCRMgmtDtls.getParentCrId());
	                changeMgmtVO.setCrWorkflowId(sgvaCRMgmtDtls.getCrWorkflowId());
	                changeMgmtVO.setCrWorkStatusId(sgvaCRMgmtDtls.getWorkStatus());
	                changeMgmtVO.setCrBuildId(sgvaCRMgmtDtls.getCrBuildId());
	                changeMgmtVO.setModuleId(sgvaCRMgmtDtls.getModuleId());
	                changeMgmtVO.setStatus(sgvaCRMgmtDtls.getStatus());
	                changeMgmtVO.setCaseId(sgvaCRMgmtDtls.getCaseId());
	                changeMgmtVO.setCaseNo(sgvaCRMgmtDtls.getCaseNo());
	                changeMgmtVO.setUserType(sgvaCRMgmtDtls.getUserType());
	                changeMgmtVO.setSourceDeptName(this.getDeptNameByDeptId(sgvaCRMgmtDtls.getDeptId(),"en_US"));
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        return changeMgmtVO;
	    }
	@Override
	public ArrayList getActionWiseAllRemarks(String crId, String lStrEmpId) {
		
	        List<SQLChangeMgmtTransVO> signOffRemarksList=null;
	        List<SQLChangeMgmtTransVO> remarksList=null;
	        ArrayList allremarksList=null;
	        StringBuffer st=new StringBuffer()    ;
	        StringBuffer query=new StringBuffer()    ;
	        try
	        {            
	            st.append("select distinct cr.remarks CR_REMARKS,cr.action_taken CR_ACTION_TAKEN,em.first_name||' '||em.middle_name||' '||em.last_name EMP_FULL_NAME, ");
	            st.append("to_char(cr.crt_dt,'dd/mm/yyyy HH12:MI:SS am') REMARKS_ENTRYTIME,d.dsgn_name DSGN_NAME,w.user_code EMPCODE, ");
	            st.append("nvl(em.mobile_no,'--NA--') PHONE_NUMBER,  ");
	            st.append("cr.attach_exists ATTACH_EXISTS,cr.remarks_id CR_ID,cr.lineitem_no LINEITEMNO ");
	            st.append("from sgva_crmgmt_remarks cr,ehfm_users em,ehfm_designation d,ehfm_work_allocation w where cr.remarks_id=? and ");
	            st.append("em.user_id=cr.emp_id and d.dsgn_id=em.dsgn_id and w.user_code=em.login_name and w.primary_flag='Y' and cr.action_taken not in(?) "); 
	            st.append("order by cr.lineitem_no desc");
	            String[] args=new String[2];
	          
	            args[0]=crId;
	            args[1]="Signed Off";
	            remarksList=genericDao.executeSQLQueryList(SQLChangeMgmtTransVO.class,st.toString(),args);
	            
	            query.append("select distinct cr.remarks CR_REMARKS,cr.action_taken CR_ACTION_TAKEN,em.first_name||' '||em.middle_name||' '||em.last_name EMP_FULL_NAME, ");
	            query.append("to_char(cr.crt_dt,'dd/mm/yyyy HH12:MI:SS am') REMARKS_ENTRYTIME,d.dsgn_name DSGN_NAME,w.user_code EMPCODE, ");
	            query.append("nvl(em.mobile_no,'--NA--') PHONE_NUMBER,  ");
	            query.append("cr.attach_exists ATTACH_EXISTS,cr.remarks_id CR_ID,cr.lineitem_no LINEITEMNO ");
	            query.append("from sgva_crmgmt_remarks cr,ehfm_users em,ehfm_designation d,ehfm_work_allocation w where cr.remarks_id=? and ");
	            query.append("em.user_id=cr.emp_id and d.dsgn_id=em.dsgn_id and w.user_code=em.login_name and w.primary_flag='Y' and cr.action_taken in(?)  "); 
	            query.append("order by cr.lineitem_no desc");
	            String[] signOffArgs=new String[2];
	           
	            signOffArgs[0]=crId;
	            args[1]="Signed Off";
	            signOffRemarksList=genericDao.executeSQLQueryList(SQLChangeMgmtTransVO.class,query.toString(),signOffArgs);
	            
	            if(remarksList!=null && !remarksList.isEmpty())
	            {
	                allremarksList=new ArrayList();
	                allremarksList.add(remarksList);
	            }   
	            if(signOffRemarksList!=null && !signOffRemarksList.isEmpty())    
	                allremarksList.add(signOffRemarksList);
	        } 
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        return allremarksList;
	    }
    public List<ChangeMgmtVO> getAllAttachments (String pStrCRId) throws Exception
    {
        List<SgvaCRMgmtAttach> allAttachments=null;
        List<ChangeMgmtVO> attachVOList=null;
        try
        {
            List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
            criteriaList.add(new GenericDAOQueryCriteria("id.crId",pStrCRId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            criteriaList.add(new GenericDAOQueryCriteria("crtDate","",GenericDAOQueryCriteria.CriteriaOperator.DESC));
            allAttachments =genericDao.findAllByCriteria(SgvaCRMgmtAttach.class,criteriaList);
            if(allAttachments!=null && !allAttachments.isEmpty())
            {                
                attachVOList=new ArrayList<ChangeMgmtVO>(allAttachments.size());
                for( SgvaCRMgmtAttach sgvaCRMgmtAttach:allAttachments)
                {
                    ChangeMgmtVO changeMgmtAttachVO=new ChangeMgmtVO();
                    changeMgmtAttachVO.setCrId(sgvaCRMgmtAttach.getId().getCrId());
                    changeMgmtAttachVO.setLineItemNo(sgvaCRMgmtAttach.getId().getLineItemNo().toString());
                    changeMgmtAttachVO.setAttachFileName(sgvaCRMgmtAttach.getAttachFileName());
                    changeMgmtAttachVO.setCrtDt(sdf.format(sgvaCRMgmtAttach.getCrtDate()));
                    changeMgmtAttachVO.setCrtUsr(this.getEmpNameByEmpId(sgvaCRMgmtAttach.getCrtUser(),"LC1","en_US"));
                    attachVOList.add(changeMgmtAttachVO);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return attachVOList;
    }

    public String getEmpNameByEmpId(String lStrEmpId, String lstrLocId, 
				            String lstrLangId) {
				String empName = null;
				EhfmUsers sgvcEmpMst = 
				genericDao.findById(EhfmUsers.class, String.class, 
				        lStrEmpId);
				if (sgvcEmpMst.getFirstName() != null)
				empName = sgvcEmpMst.getFirstName() + " ";
				if (sgvcEmpMst.getMiddleName() != null)
				empName = empName + sgvcEmpMst.getMiddleName() + " ";
				if (sgvcEmpMst.getLastName() != null)
				empName = empName + sgvcEmpMst.getLastName();
				return empName;
}
    public String getDeptNameByDeptId(String pStrDeptId, String pStrLangId) {
        String lStrDeptName = null;
        EhfmDepts deptMst = 
            genericDao.findById(EhfmDepts.class, String.class, 
                                pStrDeptId);
        if (deptMst != null) {
            lStrDeptName = deptMst.getDeptName();
        }
        return lStrDeptName;
    }
	@Override
	public SgvaCRMgmtAttach getAttachment(String pStrCRId, String lineItemNo) {
		
	       SgvaCRMgmtAttach SgvaCRMgmtAttach=null;
	        try
	        {
	             SgvaCRMgmtAttach=genericDao.findById(SgvaCRMgmtAttach.class,SgvaCRMgmtAttachId.class,new SgvaCRMgmtAttachId(pStrCRId,new Long(lineItemNo)));
	        }
	        catch(Exception e) 
	        {
	            e.printStackTrace();
	        }
	        return SgvaCRMgmtAttach;
	}
    
	
	
	}

