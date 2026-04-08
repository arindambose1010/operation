package com.ahct.panelDoctor.dao;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java .util.Calendar;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.Session;

import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.ahct.panelDoctor.util.GenerateAsciiFile;
import com.ahct.panelDoctor.util.PanelDocConstants;
import com.ahct.panelDoctor.vo.PanelDocPayVO;
import com.ahct.panelDoctor.vo.TransactionVO;
import com.ahct.claims.dao.ClaimsFlowDAO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.model.EhfPanelDocPayments;
import com.ahct.model.EhfPnldocTdsPayment;
import com.ahct.model.EhfPnlDocWrkflow;
import com.ahct.model.EhfPnlDocWrkFlowId;
import com.ahct.model.EhfPanelDocCaseDtls;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria.CriteriaOperator;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PanelDocPayDAOImpl implements PanelDocPayDAO {
	GenericDAO genericDao;
	PaymentsDAO paymentsDao;
	HibernateTemplate hibernateTemplate;
	ClaimsFlowDAO claimsFlowDAO;
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	private static final Logger logger = Logger.getLogger(PanelDocPayDAOImpl.class);
	
	static 
	{
		configurationService = ConfigurationService.getInstance(); 
		config = configurationService.getConfiguration();
	}
	
	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}


	public void setPaymentsDao(PaymentsDAO paymentsDao) {
		this.paymentsDao = paymentsDao;
	}

	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	
	public ClaimsFlowDAO getClaimsFlowDAO() {
		return claimsFlowDAO;
	}

	public void setClaimsFlowDAO(ClaimsFlowDAO claimsFlowDAO) {
		this.claimsFlowDAO = claimsFlowDAO;
	}

	@Override
	public List<PanelDocPayVO> generatepanelDocCases(PanelDocPayVO panelDocPayVO,String currGrp,String prevGrp,int startIndex,int maxResults) {
		// TODO Auto-generated method stub
		String dispType=panelDocPayVO.getDispType();
		
		String selPeriod="";
		String fromDate="";
		String toDate="";
		String args[]=new String[7];

		if(dispType.equals("01"))
		{
			selPeriod=panelDocPayVO.getSelperiod();
		}
		else if(dispType.equals("02"))
		{
			fromDate=panelDocPayVO.getFromDate();
			toDate=panelDocPayVO.getToDate();
		}
		List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
		StringBuffer query= new StringBuffer();
		 SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session=factory.openSession();
		try{
	    
	    if(currGrp.equals(config.getString("FIN.AccountsEO2Grp")))
	    	{
	    		args[0]=config.getString("FIN.PanelDocDesgID");
	    		args[1]=panelDocPayVO.getUSERTYPE();
	    		args[2]=config.getString("FIN.Initiation");
	    		args[3]=config.getString("FIN.WorkFlowIdInit");
			    args[4]=config.getString("FIN.AccountsEO2Grp");
			    
			    if(panelDocPayVO.getType()!=null)
			    	{
						if("CEOPending".equalsIgnoreCase(panelDocPayVO.getType()))
							{
								args[2]=config.getString("FIN.InProgress");
							}
			    	}	
	    	}
	    else 
	    	{
		    	args[0]=config.getString("FIN.PanelDocDesgID");
	    		args[1]=panelDocPayVO.getUSERTYPE();
	    		args[2]=config.getString("FIN.InProgress");
	    		args[3]=prevGrp;
			    args[4]=currGrp;
	    	}
		    
	    if(dispType.equals("01"))
	  		{
	  	    	 if(currGrp.equals(config.getString("FIN.AccountsEO2Grp")))
	  	    	 {
	  		    	 if(selPeriod!=null)
	  		    	 	{
	  		    		 	if(selPeriod.length()>6)
	  		    		 		{
	  		    		 			args[5]=selPeriod.substring(0,2);
	  		    		 			args[6]=selPeriod.substring(3, selPeriod.length());
	  		    		 		}	
	  		    		 	 else
		  			  	    	 {
		  			  	    		 args[5]=selPeriod;  
		  			  	    		 args[6]=selPeriod; 
		  			  	    	 }
	  		    	 	}
	  	    	 }
	  	    	 else
	  	    	 {
	  	    		if(selPeriod!=null)
  		    	 	{
  		    		 	if(selPeriod.length()>6)
  		    		 		{
  		    		 			args[5]=selPeriod.substring(0,2);
  		    		 			args[6]=selPeriod.substring(3, selPeriod.length());
  		    		 		}	
  		    		 	 else
	  			  	    	 {
	  			  	    		 args[5]=selPeriod;  
	  			  	    		 args[6]=selPeriod; 
	  			  	    	 }
  		    	 	}
	  	    	 }
	  	    	
	  	    }
	    
	    query.append(" select eu.firstName as firstName,eu.lastName as DOC_NAME ");
	    query.append(" ,epw.id.wId as wId,epw.id.docId as DOC_ID , epw.crtDt as crtDt  ");
	    query.append(" ,epw.currWrkflwId as currWrkflowId,epw.currOwnrGrp as currOwnr");
	    query.append(" ,epw.statusFlg as DISTINCTSTATUS,epw.scheme as scheme");	    
	    query.append(" ,epw.claimAprvAmt as claimAprvAmt,epw.claimRejAmt as claimRejAmt");
	    query.append(" ,epw.claimPendAmt as claimPendAmt,epw.preauthAprvAmt as preauthAprvAmt");
	    query.append(" ,epw.preauthRejAmt as preauthRejAmt,epw.preauthPendAmt as preauthPendAmt ");
	    query.append(" ,epw.totalPnldocAmt as totalPnldocAmt,to_char(to_date(epw.month,'MM'),'MONTH') as month,epw.year as year");
	    query.append(" from EhfPnlDocWrkflow epw,EhfmUsers eu");
	    query.append(" where eu.userId=epw.id.docId");
	    query.append(" and eu.dsgnId='"+args[0]+"' ");
	    query.append(" and epw.scheme='"+args[1]+"' and epw.statusFlg='"+args[2]+"'");
	    
	    if(panelDocPayVO.getType()!=null)
	    	{
				if("CEOPending".equalsIgnoreCase(panelDocPayVO.getType()))
					{
						query.append(" and epw.sendBackFlag='Y' and epw.currWrkflwId='"+config.getString("FIN.CEOKeptPendUpd")+"' and epw.prevOwnrGrp='"+config.getString("FIN.CEOGrp")+"' and epw.currOwnrGrp='"+args[4]+"'");
					}
	    	}
	    else
	    	{
			    if(currGrp.equals(config.getString("FIN.AccountsEO2Grp")))
			    	{
			    		query.append(" and epw.currWrkflwId='"+args[3]+"' and epw.currOwnrGrp='"+args[4]+"'");
			    	}	
			    else
			    	{
				    	query.append(" and epw.prevOwnrGrp='"+args[3]+"' and epw.currOwnrGrp='"+args[4]+"'");
			    	}	
	    	}
	    if(dispType!=null && !dispType.equals("03") && !dispType.equals("00"))
	    	query.append(" and epw.month='"+args[5]+"' and epw.year='"+args[6]+"'");
	    
	    query.append( " order by epw.crtDt " );
	    

	   
	    if(startIndex==0 && maxResults==0)
	    	panelDocCases=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
	    else if (maxResults!=0)
	    	panelDocCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		}
		catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception in method PanelDocPayDAOImpl:generatepanelDocCases--"
					+ e.getMessage());
		}
		finally {
			session.close();
			factory.close();
		}
	    return panelDocCases;
	}

	@Override
	public List<PanelDocPayVO> generatepanelDocCasesForCEO(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults) {
		
		String scheme="";
		List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
		List<PanelDocPayVO> panelDocAccDtls = new ArrayList<PanelDocPayVO>();
		String failAccList="";
		try{
			
			scheme=panelDocPayVO.getUSERTYPE();
			StringBuffer query= new StringBuffer();
			
		    query.append(" select eu.first_name as EMPNAME,eu.last_name as DOC_NAME ");
		    query.append(" ,epw.W_ID as WID,epw.DOC_ID as DOC_ID");
		    query.append(" ,epw.CURRENT_WORKFLOW_ID as CURRWRKFLWID,epw.CURRENT_OWNER_GRP as CURROWNR");
		    query.append(" ,epw.STATUS_FLAG as DISTINCTSTATUS,epw.SCHEME as SCHEME,epw.PREVIOUS_WORKFLOW_ID PREVWRKFLWID ");	    
		    query.append(" ,epw.CLAIM_APRV_AMT as CLAIM_APRV_AMT,epw.CLAIM_REJ_AMT as CLAIM_REJ_AMT");
		    query.append(" ,epw.CLAIM_PEND_AMT as CLAIM_PEND_AMT,epw.PREAUTH_APRV_AMT as PREAUTH_APRV_AMT");
		    query.append(" ,epw.PREAUTH_REJ_AMT as PREAUTH_REJ_AMT,epw.PREAUTH_PEND_AMT as PREAUTH_PEND_AMT ");
		    query.append(" ,epw.TOTAL_PNLDOC_AMT as TOTAL_PNLDOC_AMT,epw.YEAR as YEAR");
		    query.append(" ,decode(epw.MONTH,'01','JANUARY','02','FEBRUARY','03','MARCH','04','APRIL','05','MAY','06','JUNE',");
		    query.append("'07','JULY','08','AUGUST','09','SEPTEMBER','10','OCTOBER','11','NOVEMBER','12','DECEMBER') as MONTH");
		    query.append(" ,epac.ACCOUNT_NO as ACCOUNTNO");
		    query.append(" from Ehfm_Users eu,EHF_PNLDOC_WORKFLOW epw,EHFM_PNLDOC_ACCT_DTLS epac");
		    query.append(" where epw.doc_Id=epac.user_Id ");
		    query.append(" and epw.doc_Id=eu.user_Id and eu.dsgn_Id='"+config.getString("FIN.PanelDocDesgID")+"' ");
		    
		    
		    query.append(" and epw.status_Flag='"+config.getString("FIN.InProgress")+"' and epw.scheme='"+scheme+"' ");
		    query.append(" and epac.active_yn='Y' ");
		    if(panelDocPayVO.getPaymentStatusSelected()==null ||
		    			panelDocPayVO.getPaymentStatusSelected().equalsIgnoreCase("1"))
		    	{
		    		query.append(" and (epw.current_Owner_Grp in ('"+config.getString("FIN.CEOGrp")+"') or epw.current_Owner_Grp is null ) " );
		    		query.append(" and epw.current_workflow_id in ('"+config.getString("FIN.WorkFlowIdCEO")+"','"+config.getString("FIN.WorkFlowIdRej")+"') ");
		    	}
		    else
		    	{
		    		query.append(" and epw.current_Owner_Grp = '"+config.getString("FIN.CEOGrp")+"' " );
		    		query.append(" and epw.current_workflow_id='"+config.getString("FIN.WorkFlowIdCEO")+"' ");
		    	}
		    
		    if(panelDocPayVO.getPrevWrkflowId()!=null)
		    	if(!panelDocPayVO.getPrevWrkflowId().equalsIgnoreCase("none"))
		    		query.append(" and epw.previous_workflow_id='"+panelDocPayVO.getPrevWrkflowId()+"' ");
		    
		    if(panelDocPayVO.getSentBackSearch()!=null)
		    	if("Y".equalsIgnoreCase(panelDocPayVO.getSentBackSearch()))
		    		query.append(" and epw.send_back_flag='Y' ");
		    
		    query.append(" order by epw.year , epw.month ");
		    
		    //panelDocCases = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString());
		    
		    SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session=factory.openSession();
		    if(startIndex==0 && maxResults==0)
		    	panelDocCases=session.createSQLQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		    else if (maxResults!=0)
		    	panelDocCases=session.createSQLQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		    
		    
		   /*if(panelDocCases.size()>0 )
			   {
				    for(int i=0;i<panelDocCases.size();i++)
					    {
					    	if(panelDocCases.get(i).getACCOUNTNO() == "" || panelDocCases.get(i).getACCOUNTNO()==null )
						    	{
						    		failAccList=failAccList + panelDocCases.get(i).getDOC_NAME()+ ",";
						       	}
					    	else
					    		panelDocAccDtls.add(panelDocCases.get(i));
					    		
					    }
				    int size=panelDocAccDtls.size();
				    panelDocPayVO.setFailedList(failAccList);
				    panelDocAccDtls.add(size,panelDocPayVO);
			   
			   }*/
		}
		catch (Exception e) 
			{
				e.printStackTrace();
				logger.error("Exception in method PanelDocPayDAOImpl:generatepanelDocCasesForCEO--"
						+ e.getMessage());
			}
	    return panelDocCases;
	}
	
	
	@Override
	public List<PanelDocPayVO> generatepanelDocDtlCasesList(PanelDocPayVO panelDocPayVO,String currGrpId, String prevGrpId) {
		// TODO Auto-generated method stub
		String docId=panelDocPayVO.getDOC_ID();
		String dispType=panelDocPayVO.getDispType();
		String selPeriod="";
		String fromDate="";
		String toDate="";
		String action=panelDocPayVO.getActionType();
		String userType=panelDocPayVO.getUSERTYPE();
		Long wId=panelDocPayVO.getwId();
		String args[]=new String[4];
		if(dispType.equals("01"))
		{
			selPeriod=panelDocPayVO.getSelperiod();
		}
		else if(dispType.equals("02"))
		{
			fromDate=panelDocPayVO.getFromDate();
			toDate=panelDocPayVO.getToDate();
		}
		
		List<PanelDocPayVO> panelDocCasesList = new ArrayList<PanelDocPayVO>();
		StringBuffer query= new StringBuffer();
		
		if(dispType.equals("01") || dispType.equals("03"))
		{
		
		if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
			 args = new String[4];
	    	 args[0]=config.getString("FIN.Initiation");
	    	 args[1]=docId;
	    	 args[2]=userType;
	 		 args[3]=wId.toString();
		}
		
		else{
			 args = new String[4];
			 //args[0]=prevGrpId; 
			 if(action.equals("Rejected"))
			 args[0]=config.getString("FIN.Reject");
			 else
			 args[0]=config.getString("FIN.InProgress");
			 args[1]=docId;
			 args[2]=userType;
			 args[3]=wId.toString();
		}
		
	    }
		else if(dispType.equals("00") || dispType.equalsIgnoreCase("null"))
			{
				 args = new String[3];	
				 args[0]=docId;
				 args[1]=userType;
				 args[2]=wId.toString();
			}
	   
	    else if(dispType.equals("02"))
	    {
	    args = new String[6];
	    
	    if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
	    	 args = new String[3];
	    	 args[0]=config.getString("FIN.Initiation");
	    	 args[1]=docId;
	    	 args[2]=userType;
	    	 /*args[3]=fromDate;
	 	     args[4]=toDate;*/
		}
	    
		else{
			args = new String[4];
			 //args[0]=prevGrpId; 
			 if(action.equals("Rejected"))
			 args[0]=config.getString("FIN.Reject");
			 else
			 args[0]=config.getString("FIN.InProgress");
			 args[1]=docId;
			 args[2]=userType;
	         /*args[4]=fromDate;
	         args[5]=toDate;*/
	    }
	    }
	    else if(dispType.equals("PAO"))
		{
	    	args = new String[4];
	    	//args[0]=prevGrpId; 
	    	 if(action.equals("Rejected"))
				 args[0]=config.getString("FIN.Reject");
				 else
	    	args[0]=config.getString("FIN.InProgress");
			 args[1]=docId;
			 args[2]=userType;
			 args[3]=wId.toString();
		}
	    else if(dispType.equals("fromDepts"))
		{
	    	args = new String[6];
	    	args[0]=config.getString("FIN.InProgress");
			 args[1]=docId;
			 args[2]=userType;
			 args[3]=wId.toString();
			 args[4]=prevGrpId;
	    	 args[5]=currGrpId;
		}
		
	    	
		
			query.append(" select to_char(p.amount) AMOUNT,eu.last_name DOC_NAME,to_char(p.case_app_or_pend_dt,'dd/mm/yyyy') CASE_DATE,p.case_id CASE_ID, h.hosp_name HOSP_NAME,p.particulars PARTICULARS");
		    query.append(" from ehf_pnldoc_case_dtls p,ehfm_hospitals h,ehfm_users eu ");
		    /*if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
		    	query.append(" where p.pnl_doc_pmnt_status=? ");
		    }
		    else
		    {*/
		    	query.append(",ehf_pnldoc_workflow pw");
		    	query.append(" where  pw.w_id=p.wrkflw_id ");
		    	if(!dispType.equals("00") && !dispType.equalsIgnoreCase("null"))
		    		query.append(" and pw.status_flag=?");
		    
		    query.append(" and h.hosp_id=p.hosp_id and p.doc_id=? and p.scheme=? and eu.user_id=p.DOC_ID");
		   
		   /* if(dispType.equals("01"))
			   {
				   query.append(" and to_date(to_char(p.case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY')= to_date(?,'MM-YYYY') ");  
			   }
			   else if(dispType.equals("02"))
			   {
				   query.append(" and to_date(p.case_app_or_pend_dt) >= to_date(?, 'DD-MM-YYYY HH24:MI:SS') and to_date(p.case_app_or_pend_dt) <= to_date(?, 'DD-MM-YYYY HH24:MI:SS') ");  
			   }*/
			 query.append(" and p.wrkflw_id=?");  
			 if(dispType.equalsIgnoreCase("fromDepts"))
				   query.append(" and pw.PREVIOUS_OWNER_GRP=? and pw.CURRENT_OWNER_GRP=? ");
		    query.append(" order by CASE_DATE");
		    
	    panelDocCasesList = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    
		return panelDocCasesList;
	}
	
	@Override
	public List<PanelDocPayVO> generatecaseCountStatus(PanelDocPayVO panelDocPayVO,String currGrpId, String prevGrpId) {
		// TODO Auto-generated method stub
		String docId=panelDocPayVO.getDOC_ID();
		String dispType=panelDocPayVO.getDispType();
		String selPeriod="";
		String fromDate="";
		String toDate="";
		String action=panelDocPayVO.getActionType();
		String scheme=panelDocPayVO.getUSERTYPE();
		String args[]=new String[5];
		Long wId=panelDocPayVO.getwId();
		if(dispType.equals("01"))
		{
			selPeriod=panelDocPayVO.getSelperiod();
		}
		else if(dispType.equals("02"))
		{
			fromDate=panelDocPayVO.getFromDate();
			toDate=panelDocPayVO.getToDate();
		}
		if(dispType==null || (dispType!=null && dispType.equalsIgnoreCase("null")))
			dispType="00";
		List<PanelDocPayVO> caseCountStatus = new ArrayList<PanelDocPayVO>();
		StringBuffer query= new StringBuffer();
		if(dispType.equals("01"))
			{
				
				if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
					 args = new String[4];
			    	 args[0]=config.getString("FIN.Initiation");
			    	 args[1]=docId;
			      	 args[2]=scheme;
			 		 args[3]=wId.toString();
				}
				else if((currGrpId==null||currGrpId.equalsIgnoreCase("")) &&
							(prevGrpId==null||prevGrpId.equalsIgnoreCase("")))
					{
						 args = new String[4];
				    	 args[0]=config.getString("FIN.InProgress");
				    	 args[1]=docId;
				      	 args[2]=scheme;
				 		 args[3]=wId.toString();
					}
				else{
					 args = new String[5];
					 args[0]=prevGrpId; 
					 if(action.equals("Rejected"))
					 args[1]=config.getString("FIN.Reject");
					 else
					 args[1]=config.getString("FIN.InProgress");
					 args[2]=docId;
					 args[3]=scheme;
					 args[4]=wId.toString();
				}
		
	    }
		else if(dispType.equals("00"))
			{
				args = new String[3];
		    	 args[0]=docId;
		      	 args[1]=scheme;
		 		 args[2]=wId.toString();	
			}
	    else if(dispType.equals("02"))
		    {
				    args = new String[4];
				    
				    if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
				    	 args = new String[2];
				    	 args[0]=config.getString("FIN.Initiation");
				    	 args[1]=docId;
				    	/* args[2]=fromDate;
				 	     args[3]=toDate;*/
					}
					else{
						args = new String[3];
						 args[0]=prevGrpId; 
						 if(action.equals("Rejected"))
						 args[1]=config.getString("FIN.Reject");
						 else
						 args[1]=config.getString("FIN.InProgress");
						 args[2]=docId;
				         /*args[3]=fromDate;
				         args[4]=toDate;*/
				    }
		    }
	    else if(dispType.equals("PAO"))
			{
	    	args = new String[4];
	    	//args[0]=prevGrpId; 
	    	if(action.equals("Rejected"))
				 args[0]=config.getString("FIN.Reject");
				 else
	    	args[0]=config.getString("FIN.InProgress");
			 args[1]=docId;
			 args[2]=scheme;
			 args[3]=wId.toString();
		}
	    else if(dispType.equalsIgnoreCase("fromDepts"))
    	{
    		 args=new String[6];
    		 args[0]=config.getString("FIN.InProgress");
    		 args[1]=docId;
			 args[2]=scheme;
			 args[3]=wId.toString();
			 args[4]=prevGrpId;
	    	 args[5]=currGrpId;
    	}
	    	
			
		
			query.append(" select distinct(particulars) distinctStatus,count(*)||'' count from ehf_pnldoc_case_dtls p");
		   
		    if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
		    	query.append(" where p.pnl_doc_pmnt_status=? ");
		    }
		    else if((currGrpId==null||currGrpId.equalsIgnoreCase("")) &&
					(prevGrpId==null||prevGrpId.equalsIgnoreCase("")))
		    	{
		    		query.append(",ehf_pnldoc_workflow pw");
		    		query.append(" where pw.w_id=p.wrkflw_id  ");
		    		if(!dispType.equals("00"))
		    			query.append(" and pw.status_flag=? ");
		    	}
		    else
		    {
		    	query.append(",ehf_pnldoc_workflow pw");
		    	query.append(" where pw.previous_owner_grp=? and pw.w_id=p.wrkflw_id and pw.status_flag=? ");
		    }
		    query.append(" and p.doc_id=? and p.scheme=?");
		    /*if(dispType.equals("01"))
			   {
				   query.append(" and to_date(to_char(case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY')= to_date(?,'MM-YYYY') ");  
			   }
			   else if(dispType.equals("02"))
			   {
				   query.append(" and to_date(case_app_or_pend_dt) >= to_date(?, 'DD-MM-YYYY HH24:MI:SS') and to_date(case_app_or_pend_dt) <= to_date(?, 'DD-MM-YYYY HH24:MI:SS') ");  
			   }*/
		    query.append(" and p.wrkflw_id=?"); 
		    if(dispType.equalsIgnoreCase("fromDepts"))
	    		query.append(" and pw.PREVIOUS_OWNER_GRP=? and pw.CURRENT_OWNER_GRP=? ");
		    query.append(" group by particulars");
	    
	    caseCountStatus = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    
		return caseCountStatus;
		
		
	}

	@Override
	public List<PanelDocPayVO> generateAllSelCasesDetails(PanelDocPayVO panelDocPayVO,String currGrpId, String prevGrpId) {
		// TODO Auto-generated method stub
		String selPeriod="";
		String fromDate="";
		String toDate="";
		
		String docId=panelDocPayVO.getDOC_ID();
		String dispType=panelDocPayVO.getDispType();
		String scheme=panelDocPayVO.getUSERTYPE();
		
		String args[]=new String[4];
		if(dispType.equals("01"))
		{
			selPeriod=panelDocPayVO.getSelperiod();
		}
		else if(dispType.equals("02"))
		{
			fromDate=panelDocPayVO.getFromDate();
			toDate=panelDocPayVO.getToDate();
		}
		
		
		List<PanelDocPayVO> panelDocCasesListDtls = new ArrayList<PanelDocPayVO>();
		List<PanelDocPayVO> casesListDtls = new ArrayList<PanelDocPayVO>();
		String selectedCondition = " and p.doc_id in ( " ;
		docId=docId.replace("[~", "");
		List<String> checkdDocList=Arrays.asList(docId.split("~"));
		 String doctors = PanelDocConstants.EMPTY ;
		for(String doctorId:checkdDocList) {
			doctors = doctors + PanelDocConstants.SINGLE_QUOTE + doctorId + PanelDocConstants.SINGLE_QUOTE_COMA ;
	        
		}
		 doctors = doctors.substring ( 0, doctors.lastIndexOf ( PanelDocConstants.COMA ) ) ;
	     selectedCondition = selectedCondition + doctors + " )" ;
	        
			StringBuffer query= new StringBuffer();
		if(dispType.equals("01"))
		{
		args = new String[3];
		
		if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
	    	 args[0]=config.getString("FIN.Initiation");
		}
		else{
			 args[0]=prevGrpId; 
		}
		    args[1]=scheme;
			args[2]=selPeriod;
			
	    }
	   
	    else if(dispType.equals("02"))
	    {
	    args = new String[4];
	    
	    if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
	    	 args[0]=config.getString("FIN.Initiation");
		}
		else{
			 args[0]=prevGrpId; 
		}
	    args[1]=scheme;
	    args[2]=fromDate;
	    args[3]=toDate;
	   
	    }
	    
		query.append(" select p.id||'' ID,p.doc_id DOC_ID,p.case_id CASE_ID, p.amount||'' AMOUNT,p.wrkflw_id||'' WRKFLWSETID");
	    
	    if(currGrpId.equals(config.getString("FIN.AccountsEO2Grp"))){
	    	query.append(" ,to_char(p.case_app_or_pend_dt,'dd/mm/yyyy') CASE_DATE from ehf_pnldoc_case_dtls p  ");
	    	query.append(" where p.pnl_doc_pmnt_status=? and p.scheme=? ");
	    }
	    else
	    {
	    	query.append(" ,to_char(pw.lst_upd_dt,'dd/mm/yyyy') CASE_DATE,pw.current_workflow_id CURRWRKFLWID from ehf_pnldoc_case_dtls p,ehf_pnldoc_workflow pw  ");
	    	query.append(" where pw.previous_owner_grp=? and pw.w_id=p.wrkflw_id and pw.status_flag='P' and p.scheme=? ");
	    }
	    query.append(selectedCondition);
	    if(dispType.equals("01"))
		   {
			   query.append(" and to_date(to_char(p.case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY')>= to_date(?,'MM-YYYY') ");
			   query.append(" and trunc(p.case_app_or_pend_dt) < to_date('21/'|| to_char(sysdate, 'mm/yyyy'), 'dd/mm/yyyy') ");
		   }
		   else if(dispType.equals("02"))
		   {
			   query.append(" and to_date(p.case_app_or_pend_dt) >= to_date(?, 'DD-MM-YYYY HH24:MI:SS') and to_date(p.case_app_or_pend_dt) <= to_date(?, 'DD-MM-YYYY HH24:MI:SS') ");  
		   }
	    query.append(" order by ID");
	    panelDocCasesListDtls = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    casesListDtls.addAll(panelDocCasesListDtls);
		
		return casesListDtls;
	}
	
	
	@Override
	public List<PanelDocPayVO> generateCasesByWrkFlow(PanelDocPayVO panelDocPayVO, String prevGrpId) {
		// TODO Auto-generated method stub
		String selPeriod="";
		String fromDate="";
		String toDate="";
		
		String docId=panelDocPayVO.getDOC_ID();
		String wrkId=panelDocPayVO.getID();
		String dispType=panelDocPayVO.getDispType();
		String scheme=panelDocPayVO.getUSERTYPE();
		String args[]=new String[4];
		if(dispType.equals("01"))
		{
			selPeriod=panelDocPayVO.getSelperiod();
		}
		else if(dispType.equals("02"))
		{
			fromDate=panelDocPayVO.getFromDate();
			toDate=panelDocPayVO.getToDate();
		}
		
		
		List<PanelDocPayVO> panelDocCasesListDtls = new ArrayList<PanelDocPayVO>();
		List<PanelDocPayVO> casesListDtls = new ArrayList<PanelDocPayVO>();
		String selectedCondition = " and epw.id.docId in ( " ;
		docId=docId.replace("[~", "");
		List<String> checkdDocList=Arrays.asList(docId.split("~"));
		 String doctors = PanelDocConstants.EMPTY ;
		for(String doctorId:checkdDocList) {
			doctors = doctors + PanelDocConstants.SINGLE_QUOTE + doctorId + PanelDocConstants.SINGLE_QUOTE_COMA ;
	        
		}
		 doctors = doctors.substring ( 0, doctors.lastIndexOf ( PanelDocConstants.COMA ) ) ;
	     selectedCondition = selectedCondition + doctors + " )" ;
	     
	     String selectedWFCondition = " and epw.id.wId in ( " ;
	     	wrkId=wrkId.replace("[~", "");
			List<String> checkdWidList=Arrays.asList(wrkId.split("~"));
			 String wids = PanelDocConstants.EMPTY ;
			for(String wid:checkdWidList) {
				wids = wids + PanelDocConstants.SINGLE_QUOTE + wid + PanelDocConstants.SINGLE_QUOTE_COMA ;
		        
			}
				wids = wids.substring ( 0, wids.lastIndexOf ( PanelDocConstants.COMA ) ) ;
				selectedWFCondition = selectedWFCondition + wids + " )" ;
	     
			StringBuffer query= new StringBuffer();
		if(dispType.equals("01") || dispType.equals("00"))
		{
			if(dispType.equals("00"))
				args = new String[4];
			else
				args = new String[6];
			if(prevGrpId.equalsIgnoreCase(config.getString("FIN.NA")))
				args[0]=config.getString("FIN.PnlDocSch.PrevWID");
			else
				args[0]=prevGrpId;
		    if(panelDocPayVO.getWorkFlow()!=null)
		    	{
		    		if(panelDocPayVO.getWorkFlow().equalsIgnoreCase("I"))
		    			args[1]="I";
		    		else
		    			args[1]="P";
		    	}
		    else
		    	args[1]="P";

			args[2]=scheme;
			args[3]=panelDocPayVO.getCurrOwnr();
		    
		    if(selPeriod!=null && selPeriod.length()>6 && dispType.equals("01"))
		    	{
		    		args[4]=selPeriod.substring(0, 2);
		    		args[5]=selPeriod.substring(3,selPeriod.length());
		    	}
		    else if (dispType.equals("01"))
		    	{
			    	args[4]=selPeriod;
			    	args[5]=selPeriod;
		    	}
		    	
	    }
	   
	    
	    if(args[0]==null)
		args[0]="GP59";
		
		query.append(" select epw.id.wId as wId,epw.id.docId as DOC_ID");
		query.append(" ,epw.currWrkflwId as currWrkflowId,epw.currOwnrGrp as currOwnr");
		query.append(" ,epw.lstUpdDt as lstUpdDt");
		query.append(" from EhfPnlDocWrkflow epw where");
		
		if(prevGrpId.equalsIgnoreCase(config.getString("FIN.NA")))
			query.append(" epw.prevWrkflwId=? ");
		else
			query.append(" epw.prevOwnrGrp=? ");
		
		query.append("  and epw.statusFlg=? and epw.scheme=? and epw.currOwnrGrp=? ");
		if(!dispType.equals("00"))
			query.append(" and epw.month=? and epw.year=?  ");
		query.append(selectedCondition);
		query.append(selectedWFCondition);
		
	    panelDocCasesListDtls = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString(),args);

	    casesListDtls.addAll(panelDocCasesListDtls);
		
		return casesListDtls;
		
		
		//query.append(" select distinct(pw.w_id)||'' WRKFLWSETID,pw.doc_id DOC_ID");
	    //query.append(" ,to_char(pw.lst_upd_dt,'dd/mm/yyyy') CASE_DATE,pw.current_workflow_id CURRWRKFLWID from ehf_pnldoc_case_dtls p,ehf_pnldoc_workflow pw  ");
	    //query.append(" where pw.previous_owner_grp=? and pw.w_id=p.wrkflw_id and pw.status_flag='P' and pw.scheme=?");
	    //query.append(selectedCondition);
	    /*if(dispType.equals("01"))
		   {
			   query.append(" and to_date(to_char(p.case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY')= to_date(?,'MM-YYYY') ");  
		   }
		   else if(dispType.equals("02"))
		   {
			   query.append(" and to_date(p.case_app_or_pend_dt) >= to_date(?, 'DD-MM-YYYY HH24:MI:SS') and to_date(p.case_app_or_pend_dt) <= to_date(?, 'DD-MM-YYYY HH24:MI:SS') ");  
		   }*/
	    

	}

	@Override
	public String getStatus(String actionPerformed) {
		StringBuffer query= new StringBuffer();
		String args[]=new String[2];
		 args[0]=config.getString("FIN.PanelDocCmbHdrId");
		 args[1]=actionPerformed;
		query.append(" select cmb_dtl_id from ehfm_cmb_dtls where cmb_hdr_id=? and cmb_dtl_name=? ");
		
		return null;
	}

	

	@Override
	public String generateGrpId(String grpName) {
		String grpId="";
		List<PanelDocPayVO> grpIdList = new ArrayList<PanelDocPayVO>();
		StringBuffer query= new StringBuffer();
		String args[]=new String[1];
		 args[0]=grpName;
		 query.append(" select grp_id GRPID from ehfm_grps where grp_name=? ");
		 grpIdList=genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
		 if(grpIdList!= null && grpIdList.size()>0)
			 grpId = grpIdList.get(0).getGRPID();
			else
				grpId="NOT FOUND";
		return grpId;
		
	}

	@Override
	public List<PanelDocPayVO> generateAllRejCaseDtls(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults) {
		// TODO Auto-generated method stub
		String selPeriod="";
		String fromDate="";
		String toDate="";
		
		String dispType=panelDocPayVO.getDispType();
		String scheme=panelDocPayVO.getUSERTYPE();
		String args[]=new String[6];
		if(dispType.equals("01"))
		{
			//args = new String[10];
			selPeriod=panelDocPayVO.getSelperiod();
		}
		/*else if(dispType.equals("02"))
		{
			args = new String[11];
			fromDate=panelDocPayVO.getFromDate();
			toDate=panelDocPayVO.getToDate();
		}*/
		
		List<PanelDocPayVO> panelDocCasesListDtls = new ArrayList<PanelDocPayVO>();
		List<PanelDocPayVO> casesListDtls = new ArrayList<PanelDocPayVO>();
		 SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session=factory.openSession();
		
		StringBuffer query= new StringBuffer();
		
		 /*args[0]=config.getString("FIN.AppClaimPanelDocStatus");
		    args[1]=config.getString("FIN.RejClaimPanelDocStatus");
		    args[2]=config.getString("FIN.PendClaimPanelDocStatus");
		    args[3]=config.getString("FIN.AppPreauthPanelDocStatus");
		    args[4]=config.getString("FIN.RejPreauthPanelDocStatus");
		    args[5]=config.getString("FIN.PendPreauthPanelDocStatus");
		    args[6]=config.getString("FIN.PanelDocDesgID");
		    args[7]=scheme;
		if(dispType.equals("01"))
		{
		
		args[8]=config.getString("FIN.Reject");		
		args[9]=selPeriod;
	    }
	   
	    else if(dispType.equals("02"))
	    {
	    
	    args[8]=config.getString("FIN.Reject");		
	    args[9]=fromDate;
	    args[10]=toDate;
	    }
		 query.append("select distinct(name) DOC_NAME ,doc_id DOC_ID,sum(cl_app_amount)||'' CL_APP_AMT,sum(cl_rej_amount)||'' CL_REJ_AMT,sum(cl_pend_amount)||'' CL_PEND_AMT, ");
		    query.append("sum(pr_app_amount)||'' PR_APP_AMT,sum(pr_rej_amount)||'' PR_REJ_AMT,sum(pr_pend_amount)||'' PR_PEND_AMT,sum(total_amount)||'' AMOUNT from ");
		    query.append("(select (u.first_name || ' ' || u.last_name) name,p.doc_id doc_id, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_app_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_rej_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_pend_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_app_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_rej_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_pend_amount,p.amount||'' total_amount ");
		    query.append("from ehfm_users u,ehf_pnldoc_case_dtls p,ehf_pnldoc_prcnt_info i ");
		    query.append(" where u.user_id=p.doc_id and u.dsgn_id=? and p.scheme=? and p.pnl_doc_pmnt_status=? ");
	    if(dispType.equals("01"))
		   {
			   query.append(" and to_date(to_char(p.case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY')= to_date(?,'MM-YYYY')) ");  
		   }
		   else if(dispType.equals("02"))
		   {
			   query.append(" and to_date(p.case_app_or_pend_dt) >= to_date(?, 'DD-MM-YYYY HH24:MI:SS') and to_date(p.case_app_or_pend_dt) <= to_date(?, 'DD-MM-YYYY HH24:MI:SS')) ");  
		   }
	    query.append(" group by name,DOC_ID");*/
	    try{
	 	args = new String[5];
	    args[0]=config.getString("FIN.PanelDocDesgID");
	    args[1]=panelDocPayVO.getUSERTYPE();
	    args[2]=config.getString("FIN.Reject");
		    
	    if(dispType.equals("01"))
	  		{
  		    	 if(selPeriod!=null)
  		    	 	{
  		    		 	if(selPeriod.length()>6)
  		    		 		{
  		    		 			args[3]=selPeriod.substring(0,2);
  		    		 			args[4]=selPeriod.substring(3, selPeriod.length());
  		    		 		}	
  		    		 	 else
	  			  	    	 {
	  			  	    		 args[3]=selPeriod;  
	  			  	    		 args[4]=selPeriod; 
	  			  	    	 }
  		    	 	}
	  	    }
	    
	    query.append(" select eu.firstName as firstName,eu.lastName as DOC_NAME ");
	    query.append(" ,epw.id.wId as wId,epw.id.docId as DOC_ID");
	    query.append(" ,epw.currWrkflwId as currWrkflowId,epw.currOwnrGrp as currOwnr");
	    query.append(" ,epw.statusFlg as DISTINCTSTATUS,epw.scheme as scheme");	    
	    query.append(" ,epw.claimAprvAmt as claimAprvAmt,epw.claimRejAmt as claimRejAmt");
	    query.append(" ,epw.claimPendAmt as claimPendAmt,epw.preauthAprvAmt as preauthAprvAmt");
	    query.append(" ,epw.preauthRejAmt as preauthRejAmt,epw.preauthPendAmt as preauthPendAmt ");
	    query.append(" ,epw.totalPnldocAmt as totalPnldocAmt,epw.month as month,epw.year as year");
	    query.append(" from EhfPnlDocWrkflow epw,EhfmUsers eu");
	    query.append(" where eu.userId=epw.id.docId");
	    query.append(" and eu.dsgnId='"+args[0]+"' ");
	    query.append(" and epw.scheme='"+args[1]+"' and epw.statusFlg='"+args[2]+"' ");
	    
	    if(dispType!=null && !dispType.equals("03") && !dispType.equals("00"))
	    	query.append(" and epw.month='"+args[3]+"' and epw.year='"+args[4]+"' ");
	    //query.append(" group by epw.id.docId");
	   	
	    
	    
	    if(startIndex==0 && maxResults==0)
	    	panelDocCasesListDtls=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
	    else if (maxResults!=0)
	    	panelDocCasesListDtls=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
	    
	    
	    //panelDocCasesListDtls = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    casesListDtls.addAll(panelDocCasesListDtls);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
			session.close();
			factory.close();
		}
		return casesListDtls;

	}
	
	
	

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public HashMap updatePanelDocPayStatus() {
		System.out.println("Scheduler to Update Panel Doctor Payments status sent by bank started in TG");
		boolean isInsert = false;
		Map lFileMap = new HashMap();
		String lResult =PanelDocConstants.EMPTY;
		List lFileList = new ArrayList();
		List lCaseIdLst= new ArrayList();
		List lDocIdLst = new ArrayList();
		Map lContentMap = new HashMap();
		int lFlag = 0, iUploadStatus = 0;
		List resultList = new ArrayList();
		String lStrNextStatus = null;
		GenerateAsciiFile gaf = new GenerateAsciiFile();
		ArrayList lContentList = new ArrayList();
		HashMap hParamMap = new HashMap();
		hParamMap.put("CRTUSER", PanelDocConstants.CEO_USER_ID);
		hParamMap.put("SentStatus", PanelDocConstants.SENT);
		hParamMap.put("SharePath", config.getString("FIN.MapPath"));
		hParamMap.put("TransReadyStatus", PanelDocConstants.TransReadyStatus);
		hParamMap.put("TDS_CASE_TYPE", PanelDocConstants.TDSTRUST);
		hParamMap.put("TDS_PAYMENT_TYPE", PanelDocConstants.PANELDOCTORS_TDS_PAYTYPE);
		hParamMap.put("CLAIM_STAT_ID",PanelDocConstants.SENT_TO_BANK);
		String[] stateArray = new String[]{"CD202"};
		
		
		
		
		try { 
			
			hParamMap.put("currentDate",new java.sql.Timestamp(new Date().getTime()));
			for(int stateCount=0;stateCount<stateArray.length;stateCount++){
				HashMap tempCasesVO= getTempCases(stateArray[stateCount]);
				PanelDocPayVO sentCases=(PanelDocPayVO) tempCasesVO.get("SentPayment");
				PanelDocPayVO resendRejCases=(PanelDocPayVO) tempCasesVO.get("ResendPayment");
				hParamMap.put("SCHEME_ID", stateArray[stateCount]);	
			if(tempCasesVO.get("ResendPayment")!=null && tempCasesVO.get("ResendPayment")!="")
				
			{
				hParamMap.put("panelDocPayVO", tempCasesVO.get("ResendPayment"));
				//PanelDocPayVO panelDocPayVO=(PanelDocPayVO) hParamMap.get("panelDocPayVO");
				lContentList=paymentsDao.getRejDoctorDetails(hParamMap);
				
				//Inserting into SLIA PAYMENTS TABLE
				if (lContentList.size() > 0) {
					 //lFileMap = gaf.generateAsciiFile ( lContentList,hParamMap ) ;
					lFileMap = claimsFlowDAO.insertIntoPaymentTable(lContentList, hParamMap);
					 
					 lFlag = Integer.parseInt((String) lFileMap.get("Flag"));

						if (lContentList.size() > 2) {
							lFileList = (ArrayList) lContentList.get(0);
							lCaseIdLst = (ArrayList) lContentList.get(2);
							// hParamMap.put("failedCaseIdInList", lContentList.get(3));
						}
						//resultList = paymentsDao.insertUploadFile(hParamMap);
						iUploadStatus = 0; 
						/*Integer.parseInt(resultList.get(0)
								.toString());*/
						//tempCasesVO.get("ResendPayment")=null;
					 }
					 else
					 {
						
					 }
					
					
			}
			if(tempCasesVO.get("SentPayment")!=null && tempCasesVO.get("SentPayment")!=""){
				hParamMap.put("panelDocPayVO", tempCasesVO.get("SentPayment"));
			 lContentList=paymentsDao.getDoctorDetails(hParamMap);
			 if (lContentList.size() > 0) {
				// lFileMap = gaf.generateAsciiFile ( lContentList,hParamMap ) ;
				 lFileMap = claimsFlowDAO.insertIntoPaymentTable(lContentList, hParamMap);
				 lFlag = Integer.parseInt((String) lFileMap.get("Flag"));

					if (lContentList.size() > 2) {
						lFileList = (ArrayList) lContentList.get(0);
						lCaseIdLst = (ArrayList) lContentList.get(2);
						// hParamMap.put("failedCaseIdInList", lContentList.get(3));
					}
					//resultList = paymentsDao.insertUploadFile(hParamMap);
					iUploadStatus = 0;
					/*Integer.parseInt(resultList.get(0)
							.toString());*/
				 }
				 else
				 {
					
				 }
				
				
			}
			//For TDS Updation
			updateTDSClaimStatus(stateArray[stateCount]);
			}
			
			
			//To read bank files
			updateClaimStatusSentByBank();
			System.out.println("Scheduler to Update Panel Doctor Payments status sent by bank ended in TG");
			
			 
		}
		catch (Exception e) {
			logger.error("Error occured in updatePanelDocPayStatus() in PanelDocPayDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return hParamMap;
	}

	private HashMap getTempCases(String stateArray) {
		
		String args[]=new String[3];
		HashMap hParamMap = new HashMap();
		String doc_checked_sent="[";
		String doc_workflow_sent="[";
		int countSent=0;
		String doc_checked_resend_rej="[";
		String doc_workflow_resend_rej="[";
		int countRej=0;
		try{
		List<PanelDocPayVO> panelDocCaseForPmnt = new ArrayList<PanelDocPayVO>();
		PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
		StringBuffer query= new StringBuffer();
		args[0]=PanelDocConstants.TempSentStatus;
		args[1]=PanelDocConstants.TempRejSentStatus;
		args[2]=stateArray;
	    query.append(" select doc_id DOC_ID,status_flag ID,w_id WORKFLW_SET_ID from ehf_pnldoc_workflow where status_flag in(?,?) and scheme=?");
	   	   
	    panelDocCaseForPmnt = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    for(int i=0;i<panelDocCaseForPmnt.size();i++)
	    {
	    	if(panelDocCaseForPmnt.get(i).getID().equals(PanelDocConstants.TempSentStatus)){
	    	doc_checked_sent=doc_checked_sent+"~"+panelDocCaseForPmnt.get(i).getDOC_ID();
	    	doc_workflow_sent=doc_workflow_sent+"~"+panelDocCaseForPmnt.get(i).getWORKFLW_SET_ID();
	    	countSent++;
	    	}
	    	else if(panelDocCaseForPmnt.get(i).getID().equals(PanelDocConstants.TempRejSentStatus)){
	    		doc_checked_resend_rej=doc_checked_resend_rej+"~"+panelDocCaseForPmnt.get(i).getDOC_ID();
	    		doc_workflow_resend_rej=doc_workflow_resend_rej+"~"+panelDocCaseForPmnt.get(i).getWORKFLW_SET_ID();
	    		countRej++;
	    	}
	    	}
	   
	    	if(countSent>0){
	    	 panelDocPayVO.setDOC_ID(doc_checked_sent);
	    	 panelDocPayVO.setUserId(PanelDocConstants.CEO_USER_ID);
			    panelDocPayVO.setCurrWrkflowId(config.getString("FIN.WorkFlowIdSent"));
			    panelDocPayVO.setPrevWrkflowId(config.getString("FIN.WorkFlowIdCEO"));
			    panelDocPayVO.setCurrOwnr("");
			    panelDocPayVO.setPrevOwnr(config.getString("FIN.CEOGrp"));
			    panelDocPayVO.setActionType(config.getString("FIN.CEOGrp"));
			    panelDocPayVO.setWRKFLWSETID(doc_workflow_sent);
			    hParamMap.put("SentPayment", panelDocPayVO);
	    	}
	    	
	    	
	    	if(countRej>0){
	    		panelDocPayVO=new PanelDocPayVO();
	    		panelDocPayVO.setDOC_ID(doc_checked_resend_rej);
	    		panelDocPayVO.setUserId(PanelDocConstants.CEO_USER_ID);
	 		    panelDocPayVO.setCurrWrkflowId(config.getString("FIN.WorkFlowIdSent"));
	 		    panelDocPayVO.setPrevWrkflowId(config.getString("FIN.WorkFlowIdCEO"));
	 		    panelDocPayVO.setCurrOwnr("");
	 		    panelDocPayVO.setPrevOwnr(config.getString("FIN.CEOGrp"));
	 		    panelDocPayVO.setActionType(config.getString("FIN.CEOGrp"));
	 		   panelDocPayVO.setWRKFLWSETID(doc_workflow_resend_rej);
	 		   hParamMap.put("ResendPayment", panelDocPayVO);
	    	}
	    	
		}
	    
		catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception in method updateTDSClaimStatus--"
					+ e.getMessage());
		}
	       
	    return hParamMap;
	}

	@Override
	public List<PanelDocPayVO> generateTDSCases(PanelDocPayVO panelDocPayVO) {
		// TODO Auto-generated method stub
		String args[]=new String[2];
		String scheme="";
		List<PanelDocPayVO> panelDocTDSCases = new ArrayList<PanelDocPayVO>();
		try{
		scheme=panelDocPayVO.getUSERTYPE();
		StringBuffer query= new StringBuffer();
		args[0]=PanelDocConstants.CLAIM_READY_PAYMENT;
		args[1]=scheme;
	    query.append(" select tp.tds_payment_id CASE_ID,tp.claim_amount||'' AMOUNT,pp.doctor_id DOC_ID,(u.first_name || ' ' || u.last_name) DOC_NAME ");
	    query.append(" from ehf_pnldoc_tds_payment tp,ehf_pnldoc_payments pp,ehfm_users u");
	    query.append(" where pp.cases_set_id=tp.case_set_id and tp.payment_status=? and u.user_id = pp.doctor_id and tp.scheme=?");
	    query.append(" order by CASE_ID");
	   
	    panelDocTDSCases = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
		}
		catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception in method generateTDSCases--"
					+ e.getMessage());
		}
	    return panelDocTDSCases;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })

	public HashMap updateTDSClaimStatus(String stateArray) {
		// TODO Auto-generated method stub
		
		//For getting Temp Values Approved
		HashMap hParamMap = new HashMap();
		PanelDocPayVO sentCases=new PanelDocPayVO();
		try {
		HashMap tempCasesVO= getTempTDSCases(stateArray);
		if(tempCasesVO.get("SentTDSPayment")!=null && tempCasesVO.get("SentTDSPayment")!="" ){
			sentCases=(PanelDocPayVO) tempCasesVO.get("SentTDSPayment");
		
		
		hParamMap.put("panelDocPayVO",  tempCasesVO.get("SentTDSPayment"));
		hParamMap.put("caseStatus",  sentCases.getCASE_ID().toString());
		hParamMap.put("CRTUSER", sentCases.getUserId().toString());
		hParamMap.put("SentStatus", PanelDocConstants.SENT);
		hParamMap.put("SharePath", config.getString("FIN.MapPath"));
		hParamMap.put("TdsRemarks", PanelDocConstants.CLAIM_SENT_RMK);
		hParamMap.put("TDS_CASE_TYPE", PanelDocConstants.TRUST_DEDUCTOR);
		hParamMap.put("SentStatus", PanelDocConstants.SENT);
		hParamMap.put("SCHEME_ID", stateArray);	
		//end
		String lResult = PanelDocConstants.EMPTY, lStrCaseId = null;
		List lCaseIdLst = new ArrayList();
		
		List lContentList = new ArrayList();
		Map lFileMap = new HashMap();
		int lFlag = 0, iUploadStatus = 0;
		List resultList = new ArrayList();
		List lFileList = new ArrayList();
		Map lContentMap = null;

		boolean isInsert = PanelDocConstants.BOOLEAN_FALSE;
		
			
			GenerateAsciiFile gaf = new GenerateAsciiFile();
            //updating payment check flag in tds table 
			isInsert = updateTdsPayChkValue(hParamMap);

			if (isInsert) { // getting tds accounts details
				lContentList = paymentsDao.getTdsAccountDtls(hParamMap);

				if (lContentList.size() > 0) { //generating ascii file values
					lFileMap = gaf.generateAsciiFile((ArrayList) lContentList,
							hParamMap);
					lFlag = Integer.parseInt((String) lFileMap.get("Flag"));

					if (lContentList.size() > 2) {
						lFileList = (ArrayList) lContentList.get(0);
						lCaseIdLst = (ArrayList) lContentList.get(2);
						// paymentList = (ArrayList) lContentList.get(3);//133
					}
				}

				if (lFlag > 0) {

					int CaseIdLst = lCaseIdLst.size();
					for (int j = 0; j < CaseIdLst; j++) {
						lStrCaseId = (String) lCaseIdLst.get(j);
						hParamMap.put("TDS_PAYMENT_ID", lStrCaseId); // for
																		// setTDSCaseAudit

						lContentMap = new HashMap();
						lContentMap = (HashMap) lFileList.get(j);
						if ((String) lContentMap.get("BENEFICIARY_ACCOUNT_NO") != null) {
							hParamMap.put("SRC_ACCT_NO", (String) lContentMap
									.get("CLAINT_AC_NUMBER"));
							hParamMap.put("DES_ACCT_NO", (String) lContentMap
									.get("BENEFICIARY_ACCOUNT_NO"));
							hParamMap.put("TRANSACTION_AMOUNT",
									(Double) lContentMap
											.get("TRANSACTION_AMOUNT")); 																			

							if (lStrCaseId.endsWith(PanelDocConstants.PTDS)) {
								isInsert = updateTDSAccountDetails(hParamMap); // if
																					// the
																					// payment
																					// is
																					// related
																					// to
																					// TDS

								if (isInsert) {
									isInsert = PanelDocConstants.BOOLEAN_FALSE;
									isInsert = ChangeTDSClaimStatus(hParamMap);
									paymentsDao.setTDSAuditDetails(hParamMap); //for tds auditing purpose
								}
								if (isInsert)
									lResult = PanelDocConstants.ONE;
								else
									lResult = PanelDocConstants.ZERO;
							}
						}
					}

					if (isInsert) {
						resultList = paymentsDao.insertUploadFile(hParamMap);  //saving file in two folder
						iUploadStatus = Integer.parseInt(resultList.get(0).toString());
					}

					lResult = PanelDocConstants.ONE;
				} else {
					lResult = PanelDocConstants.ZERO;
				}

			} // end if
			else {
				lResult = PanelDocConstants.TWO;
				iUploadStatus = 0;
			}

			hParamMap.put("Message", lResult);
			hParamMap.put("UploadStatus", Integer.toString(iUploadStatus));
		}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception in method updateTDSClaimStatus--"
					+ e.getMessage());
		}
		return hParamMap;
		
	}
	
	//For TDS intiated from temp status
private HashMap getTempTDSCases(String stateArray) {
		
		String args[]=new String[2];
		HashMap hParamMap = new HashMap();
		String doc_checked_sent="[";
		int countSent=0;
		
		try{
		List<PanelDocPayVO> panelDocCaseForPmnt = new ArrayList<PanelDocPayVO>();
		PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
		StringBuffer query= new StringBuffer();
		args[0]=PanelDocConstants.TempSentStatus;
		args[1]=stateArray;
	    query.append(" select TDS_PAYMENT_ID DOC_ID from ehf_pnldoc_tds_payment where payment_status=? and scheme=?");
	   	   
	    panelDocCaseForPmnt = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    for(int i=0;i<panelDocCaseForPmnt.size();i++)
	    {
	    	
	    	doc_checked_sent=doc_checked_sent+"~"+panelDocCaseForPmnt.get(i).getDOC_ID();
	    	countSent++;
	   	}
	   
	    	if(countSent>0){
	    	 panelDocPayVO.setCASE_ID(doc_checked_sent);
	    	 panelDocPayVO.setUserId(PanelDocConstants.CEO_USER_ID);
			 hParamMap.put("SentTDSPayment", panelDocPayVO);
	    	}
	    	
	    		    	
		}
	    
		catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception in method updateTDSClaimStatus--"
					+ e.getMessage());
		}
	       
	    return hParamMap;
	}

	/**
	 * Change tds claim status.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean ChangeTDSClaimStatus(HashMap lparamMap) throws Exception {
		logger.info("Start:ChangeTDSClaimStatus method in PanelDocPayDaoimpl");
		
		boolean isInsert = false;
		try{
		PanelDocPayVO panelDocPayVO = (PanelDocPayVO) lparamMap.get("panelDocPayVO");
		EhfPnldocTdsPayment ehfPnldocTdsPayment = null;

		ehfPnldocTdsPayment = genericDao.findById(EhfPnldocTdsPayment.class,
				String.class, (String) lparamMap.get("TDS_PAYMENT_ID"));
		ehfPnldocTdsPayment.setPaymentStatus((String) lparamMap
				.get("TDS_STAT_ID"));
		ehfPnldocTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
		ehfPnldocTdsPayment.setFileName((String) lparamMap.get("FileName"));
		ehfPnldocTdsPayment.setRemarks(PanelDocConstants.CLAIM_SENT_RMK);
		ehfPnldocTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date()
				.getTime()));
		ehfPnldocTdsPayment.setLastUpdUser(panelDocPayVO.getUserId());
		ehfPnldocTdsPayment.setPaymentCheck(PanelDocConstants.F);
		ehfPnldocTdsPayment = genericDao.save(ehfPnldocTdsPayment);
		if (ehfPnldocTdsPayment != null)
			isInsert = PanelDocConstants.BOOLEAN_TRUE;
		}
		catch (Exception le) {
			logger.error("Exception in method ChangeTDSClaimStatus--"
					+ le.getMessage());
		}
		return isInsert;

	}

	/**
	 * Update tds pay chk value.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public boolean updateTdsPayChkValue(HashMap lparamMap) throws Exception {

		String lStrTdsPaymentId = PanelDocConstants.EMPTY;
		PanelDocPayVO panelDocPayVO = (PanelDocPayVO) lparamMap.get("panelDocPayVO");
		boolean isInsert = PanelDocConstants.BOOLEAN_FALSE;
		String paymentType = (String) lparamMap.get("PaymentType");
		EhfPnldocTdsPayment EhfPnldocTdsPayment = null;
		String failedCaseIdInList = PanelDocConstants.EMPTY;
		boolean failedIsInsert = PanelDocConstants.BOOLEAN_FALSE;
		//String selectedCondition = " and u.user_id in ( " ;
		String caseId=panelDocPayVO.getCASE_ID();
		
		caseId=caseId.replace("[~", "");
		List<String> checkdCaseList=Arrays.asList(caseId.split("~"));
		for (String cases :checkdCaseList) {
			try {
				lStrTdsPaymentId = cases;
				isInsert = PanelDocConstants.BOOLEAN_FALSE;
                //checking tds accomnt number
				isInsert = checkTDSAccNo(lparamMap);

				if (isInsert) {
					EhfPnldocTdsPayment = genericDao.findById(EhfPnldocTdsPayment.class, String.class,lStrTdsPaymentId);
					EhfPnldocTdsPayment.setPaymentCheck(PanelDocConstants.T);
					EhfPnldocTdsPayment = genericDao.save(EhfPnldocTdsPayment);
					if (EhfPnldocTdsPayment.getPaymentCheck() != null && (PanelDocConstants.T).equalsIgnoreCase(EhfPnldocTdsPayment.getPaymentCheck())) {
						isInsert = PanelDocConstants.BOOLEAN_TRUE;
					}
				} else {
					failedCaseIdInList = failedCaseIdInList + lStrTdsPaymentId
							+ ",";
//					logger.error("failed cases are in else block************"
//							+ failedCaseIdInList+" ");
					lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				}
			} catch (Exception ex) {
				failedIsInsert = PanelDocConstants.BOOLEAN_TRUE;
				failedCaseIdInList = failedCaseIdInList + lStrTdsPaymentId
						+ ",";
				System.out
						.println("failed cases are in catch block************"
								+ failedCaseIdInList);
				lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				lparamMap.put("failedIsInsert", failedIsInsert);
			}
		}

		return isInsert;
	}
	
	/**
	 * Check tds acc no.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkTDSAccNo(HashMap lparamMap) {
		StringBuffer lStrBuffer = null;
		List<PanelDocPayVO> accountList = new ArrayList<PanelDocPayVO>();
		boolean isExist = PanelDocConstants.BOOLEAN_TRUE;
		try {
			// For checking whether TDS account details are present are not
			String case_Type = (String) lparamMap.get("TDS_CASE_TYPE");
			String act_Type = PanelDocConstants.EMPTY;
			String scheme=(String) lparamMap.get("SCHEME_ID");
			if (PanelDocConstants.TRUST_DEDUCTOR.equals(case_Type)) {
				if(scheme.equals(PanelDocConstants.AP_State))
				act_Type = PanelDocConstants.TDSEHS_AP;// "TDSAS2";
				else if(scheme.equals(PanelDocConstants.TG_State))
					act_Type = PanelDocConstants.TDSEHS_TG;// "TDSAS2";
			} 
			lStrBuffer = new StringBuffer();
			lStrBuffer
					.append(" select t.accountNo as ACCOUNTNO FROM EhfmTrustActDtls t,EhfmBankMaster b ");
			lStrBuffer
					.append(" where t.bankId=b.bankId and t.actType=? and t.activeYn=? and t.schemeId=?");

			String[] arg = new String[2];
			arg[0] = act_Type;
			arg[1] = PanelDocConstants.Y;
			arg[2]=scheme;

			accountList = genericDao.executeHQLQueryList(PanelDocPayVO.class,
					lStrBuffer.toString(), arg);

			if (accountList.size() == 0) {
//				logger.error("No TDS Account Details provided");
				isExist = PanelDocConstants.BOOLEAN_FALSE;
			}
		} catch (Exception le) {
			logger.error("Exception in method checkTDSAccNo--"
					+ le.getMessage());
		}

		return isExist;
	}


	

	
	/**
	 * Update TDS account details.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean updateTDSAccountDetails(HashMap lparamMap)
			throws Exception {
		logger.info("updateTDSAccountDetails method in PanelDocPayDAOImpl");
		String lStrCaseId = null;
		String amount="0";String caseId = "";String scheme="";
		String paymentType = PanelDocConstants.EMPTY;
		boolean isInsert = PanelDocConstants.BOOLEAN_FALSE;
		amount = String.valueOf( lparamMap.get("TRANSACTION_AMOUNT"));
		scheme=(String)lparamMap.get("SCHEME_ID");
		EhfPanelDocPayments ehfPanelDocPayments=new EhfPanelDocPayments();
		lStrCaseId = (String) lparamMap.get("TDS_PAYMENT_ID");
		if (lStrCaseId == null || lStrCaseId.equalsIgnoreCase("")) {
			lStrCaseId = (String) lparamMap.get("CASE_ID");
		}
		caseId=lStrCaseId;
		paymentType = lStrCaseId.substring(
				lStrCaseId.lastIndexOf(PanelDocConstants.SLASH) ,
				lStrCaseId.length());
		lStrCaseId = lStrCaseId.substring(0,
				lStrCaseId.indexOf(PanelDocConstants.SLASH))+"/P";
		try {
			TransactionVO transactionVO = new TransactionVO();
			transactionVO.setCaseSetId(lStrCaseId);
			transactionVO.setTdsId(caseId);
			transactionVO.setNetAmount("");
			transactionVO.setTdsAmount(amount);		
			
			if(scheme.equals(PanelDocConstants.AP_State))
				transactionVO.setScheme(config.getString("FIN.EHFAPSchemeID"));
			else if(scheme.equals(PanelDocConstants.TG_State))
				transactionVO.setScheme(config.getString("FIN.EHFTGSchemeID"));
			else
				transactionVO.setScheme(config.getString("FIN.EHF"));
	 if (PanelDocConstants.SLASH_PTDS.equalsIgnoreCase(paymentType)) {
			transactionVO.setTransactionType("TDS Payment");
			
			ehfPanelDocPayments = genericDao.findById(EhfPanelDocPayments.class,
						String.class, lStrCaseId);
			ehfPanelDocPayments.setTdsDesAcc((String) lparamMap.get("DES_ACCT_NO"));
			ehfPanelDocPayments = genericDao.save(ehfPanelDocPayments);
				isInsert = PanelDocConstants.BOOLEAN_TRUE;
				if (ehfPanelDocPayments == null)
					isInsert = PanelDocConstants.BOOLEAN_FALSE; 
							
			transactionVO.setNarration("TDS");
			transactionVO.setTdsType(config.getString("FIN.TDS"));
		}
		paymentsDao.submitTdsOrRfPaymentsInAccounts(transactionVO);
		
		}
		catch (Exception e) {
			isInsert = PanelDocConstants.BOOLEAN_FALSE;
			logger.info("FALIURE:updateTDSAccountDetails method in PanelDocPayDAOImpl");
			e.printStackTrace();
		}
		logger.info("End:updateTDSAccountDetails method in PanelDocPayDAOImpl");
		return isInsert;
	}
	
	
	/**
	 * To read bank sent files
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateClaimStatusSentByBank() {
		HashMap lparamMap = new HashMap();
		lparamMap.put("SentStatus", PanelDocConstants.SENT);
		lparamMap.put(
				"SharePath",
				config.getString("FIN.MapPath")
						+ config.getString("FIN.claimPayment_filePath"));
		lparamMap.put("ClaimsRecievedPath",
				config.getString("FIN.localRecievedPath"));
		// for CaseClaims Payment
		lparamMap.put("ClamsInProgerss", PanelDocConstants.SENT_TO_BANK);
		lparamMap.put("PnlDocPaidStatus", PanelDocConstants.CLAIM_PAID);
		lparamMap.put("PnlDocRejStatus", PanelDocConstants.CLAIM_REJ_BANK);
		lparamMap.put("PnlDocAckStatus", PanelDocConstants.CLAIM_ACK_REC);
		lparamMap.put("TransReadyStatus", PanelDocConstants.TransReadyStatus);
		lparamMap.put("TransPaidStatus", PanelDocConstants.TransPaidStatus);		
		lparamMap.put("TransAckStatus", PanelDocConstants.TRANSACKSTATUS);
		lparamMap.put("TransRejStatus", PanelDocConstants.TRANSREJSTATUS);
		lparamMap.put("ClaimPaidRemarks", PanelDocConstants.CLAIM_PAY_DONE);
		lparamMap.put("ClaimAckRemarks", PanelDocConstants.CLAIM_ACKNOWLEDGED);
		lparamMap.put("REMARKS", "");
		lparamMap.put("TDS_CASE_TYPE",PanelDocConstants.TDSTRUST);
		lparamMap.put("CRTUSER",PanelDocConstants.CEO_USER_ID);
		lparamMap.put("UPD_USR", PanelDocConstants.CEO_USER_ID);
		lparamMap.put("LANG_ID", PanelDocConstants.LANG_ID);
		lparamMap.put("ClosedStatus", PanelDocConstants.CLOSED);

		try {
			// pkioutput folder path
			String FilePath = PanelDocConstants.EMPTY;
			String lStrSrcDir = config.getString("FIN.MapPath")
					+ config.getString("FIN.claimPayment_filePath")
					+ config.getString("FIN.claimPKIOutput_filePath");
			File destination = null;

			File srcDir = new File(lStrSrcDir);
			if (!srcDir.exists()) {
				srcDir.mkdirs();
			}
            //getting filename from PKIOutput folder
			String lExistingFiles = vectorToString(listFileNames(lStrSrcDir),
					"\n");
			if (lExistingFiles.trim().length() > 0 && lExistingFiles != null) {
				String Files[] = lExistingFiles.split("\n");

				for (int FileCnt = Files.length - 1; FileCnt >= 0; FileCnt--) {
					String fileFlag = Files[FileCnt].substring(0, 2)
							.toUpperCase();
					String[] fileNme=Files[FileCnt].split("_");
					StringBuffer qry=new StringBuffer();
					String[] args=new String[1];
					args[0]=fileNme[0]+".xml";
					qry.append("select distinct paymentType as  paymentType from  EhfSliaPayments  where fileName=? and paymentType in ('EHSPTG'))");
					List<ClaimsFlowVO> fileType=genericDao.executeHQLQueryList(ClaimsFlowVO.class,qry.toString(),args);
					if(fileType!=null && fileType.size()>0 && fileType.get(0)!=null && fileType.get(0).getPaymentType()!=null && !"".equalsIgnoreCase(fileType.get(0).getPaymentType()))
					{
						FilePath = Files[FileCnt];
						destination = new File(FilePath);
						//will download and save in receivedClaim folder and do the process
						downLoadFile(lStrSrcDir, Files[FileCnt],
								destination.toString(), lparamMap);
						break;
					}
			
				}
			}
		} catch (Exception e) {
			logger.error("@Exception has raised in updateClaimStatusSentByBank() due to--->"
					+ e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 033 Get the list of files in the current directory as a Vector of Strings
	 * (excludes subdirectories).
	 *
	 * @param filePath the file path
	 * @return the vector
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector listFileNames(String filePath) throws IOException {

		File folder = new File(filePath);
		File[] files = folder.listFiles();
		Vector v = new Vector();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory())
					v.addElement(files[i].getName());
			}
		}
		return v;
	}
	
	/**
	 * Convert a Vector to a delimited String.
	 *
	 * @param v the v
	 * @param delim the delim
	 * @return the string
	 */
	@SuppressWarnings("rawtypes")
	private String vectorToString(Vector v, String delim) {
		StringBuffer sb = new StringBuffer();
		String s = "";
		for (int i = 0; i < v.size(); i++) {
			sb.append(s).append((String) v.elementAt(i));
			s = delim;
		}
		return sb.toString();
	}
	
	/**
	 * Download file from PKIOUTPut To ReceivedClaims and do process.
	 *
	 * @param lStrSrcDir the l str src dir
	 * @param src the src
	 * @param dest the dest
	 * @param lparamMap the lparam map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void downLoadFile(String lStrSrcDir, String src, String dest,
			HashMap lparamMap) throws IOException {
		ArrayList lContentList = new ArrayList();
		ArrayList lDatalist = new ArrayList();
		int iFlag = 0, Count = 0;
		String lStrResult = PanelDocConstants.EMPTY, lClaimsRecvPath = PanelDocConstants.EMPTY, lStrSharePath = PanelDocConstants.EMPTY;
		String lStrDestDir = PanelDocConstants.EMPTY, srcFilePath = PanelDocConstants.EMPTY, destFilePath = PanelDocConstants.EMPTY;
		int RIndex = src.indexOf(PanelDocConstants.DOT);
		String OrgFileName=null;
	/*	String secondPart=src.substring(RIndex + 1,src.length());
		
		if(secondPart.contains(PanelDocConstants.DOT))
			{
				 OrgFileName = src.substring(0, RIndex + 1)
						+	secondPart.substring(1,secondPart.indexOf(PanelDocConstants.DOT));
			}
		else
			{
				 OrgFileName = src.substring(0, RIndex + 1)
					+ src.substring(RIndex + 2, src.length());
			}*/
		
		lClaimsRecvPath = (String) lparamMap.get("ClaimsRecievedPath");
		lStrSharePath = (String) lparamMap.get("SharePath");
		lStrDestDir = lStrSharePath + lClaimsRecvPath;
		srcFilePath = lStrSrcDir + PanelDocConstants.SLASH + src;
		try {
			destFilePath = lStrSharePath + lClaimsRecvPath
					+ PanelDocConstants.SLASH + dest;
			/*if (OrgFileName.substring(0, 4).toUpperCase().equals(PanelDocConstants.PANELDOC_CLIENT_CODE)) {*/
			movingFileSrcToDest(srcFilePath, destFilePath, lStrDestDir); // moves
																			// the
																			// src
																			// file
																			// from
																			// SBHOutTemp
																			// folder
																			// to
																			// a
																			// destination
																			// folder.

		/*	BufferedInputStream bis = null;
			FileInputStream fis = new FileInputStream(new File(destFilePath));
			bis = new BufferedInputStream(fis);

			DataInputStream data_in = new DataInputStream(bis);*/
			
			 File inputFile = new File(destFilePath);
			 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
			
			lDatalist = readContents(doc);
			if (lDatalist.size() > 0) {
				lContentList = (ArrayList) lDatalist.get(0);
				iFlag = Integer.parseInt((String) lDatalist.get(1));
				Count = Integer.parseInt((String) lDatalist.get(2));
				lparamMap.put("FileConut", Integer.toString(iFlag));// to Change
																	// the File
																	// Status
				lparamMap.put("FileName", (String) lDatalist.get(4));// File Name Used to
														// update the File
														// Status
				lparamMap.put("FileNameNew", (String) lDatalist.get(4));	
				OrgFileName=(String) lDatalist.get(3);
			}

			if (Count > 0 && lContentList.size() > 0) {
				lparamMap.put("DataList", lContentList);
				if (OrgFileName.substring(0, 4).toUpperCase().equals(PanelDocConstants.PANELDOC_CLIENT_CODE)) {
					lStrResult = paymentsDao.SetPanelDocStatus(lparamMap);
				}
			}
			/*}*/
		} catch (Exception e) {
			logger.error("@Exception has raised in downLoadFile() due to--->"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Moves the src file from PKIOutput folder to a destination folder.
	 *
	 * @param src the src
	 * @param dest the dest
	 * @param destDirPath the dest dir path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void movingFileSrcToDest(String src, String dest, String destDirPath)
			throws IOException {
		String flag = PanelDocConstants.FALSE;
		File source = new File(src);
		File destination = new File(dest);
		File destDir = new File(destDirPath);

		boolean lbDir = PanelDocConstants.BOOLEAN_FALSE;
		if (!destDir.exists()) {
			lbDir = destDir.mkdirs();
		} else {
			lbDir = PanelDocConstants.BOOLEAN_TRUE;
		}

		if (lbDir) {
			if(destination.exists()){
				 File newFile = new File(dest+"_"+uniqueCurrentTimeMS());
				 if(destination.renameTo(newFile)){
						System.out.println("Rename succesful");
					}else{
						System.out.println("Rename failed");
					}
			}
			copyFile(source, destination);// Copying the file from src to dest
			flag = PanelDocConstants.TRUE;
		}

		if (flag == PanelDocConstants.TRUE) {
			//System.out.println("File or directory moved successfully.");
			
			if (source.exists()) {
				source.delete();// Deleting the src file, once the file is moved
								// to a destination folder
			}
		} else {
			logger.error("File or directory is not moved successfully.");
			//System.out.println("File or directory is not moved successfully.");
		}
	}
	
	/**
	 * Copy file.
	 *
	 * @param source the source
	 * @param dest the dest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void copyFile(File source, File dest) throws IOException { // 033
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			in.close();
			out.close();
		}
	}
	
	/**
	 * Read contents.
	 *
	 * @param data_in the data_in
	 * @return the array list
	 */
/*	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public ArrayList readContents(DataInputStream data_in) {
		ArrayList lContentList = new ArrayList();
		ArrayList lDataList = new ArrayList();
		int lFlag = 0, Count = 0;

		try {
			String line = "";

			while ((line = data_in.readLine()) != null) {

				int lSize = 0;
				String[] lfields = line.split(",");
				int len = lfields.length;
				if (len >= 12) {
					if (len == 12) {
						lSize = 5;
					} else if (len == 13) {
						lSize = 4;
					} else if (len == 14) {
						lSize = 3;
					} else if (len == 15) {
						lSize = 2;
					} else if (len == 16) {
						lSize = 1;
					}
					for (int i = 0; i < lfields.length; i++) {
						lContentList.add(lfields[i]);
						if (len - 1 == i) {
							for (int n = 0; n < lSize; n++) {
								lContentList.add(" ");
							}
						}
					}// end for loop
					Count++;
				} else if (len > 17 || len < 12) {
					Count = 0;
					lContentList.add("Data File Incorrect");
					break;
				}
			}
			lDataList.add(lContentList);
			lDataList.add(Integer.toString(lFlag));
			lDataList.add(Integer.toString(Count));

		} catch (Exception e) {
			logger.error(e);
		}

		return lDataList;
	}*/
	
	  public ArrayList<Object> readContents(Document data_in) {
	        ArrayList<String> lContentList = new ArrayList<String>();
	        ArrayList<Object> lDataList = new ArrayList<Object>();
	        int lFlag = 0, Count = 0;
	        try {
	            NodeList debitEntry = data_in.getElementsByTagName("DEBIT_ACCOUNT");
	            String paymentType = ((Element)debitEntry.item(0)).getAttribute("AGENCY_DR_REF");
	            String fileName = ((Element)debitEntry.item(0)).getAttribute("DEBIT_REFERENCE");
	            String srcAcc = ((Element)debitEntry.item(0)).getAttribute("ACCOUNT_DEBIT");
	            NodeList entries = data_in.getElementsByTagName("CREDIT_ACCOUNT");
	            String date = "";
	            int num = entries.getLength();
	            for (int i=0; i<num; i++) {
	                Element node = (Element) entries.item(i);
	                String caseId=node.getAttribute("AGENCY_CR_REF");
	                if(caseId!=null && !"".equalsIgnoreCase(caseId) && caseId.startsWith("E/"))
	                caseId=caseId.substring(2);
	                lContentList.add(caseId);
	                lContentList.add(node.getAttribute("CREDIT_PAYMENT_REFERENCE"));
	                date = node.getAttribute("CREDIT_TRAN_DATE");
	                lContentList.add(date.substring(0,2)+"/"+date.substring(2,4)+"/"+date.substring(4));
	                lContentList.add(node.getAttribute("CREDIT_STATUS_CODE"));
	                lContentList.add(node.getAttribute("CREDIT_REMARKS"));
	                lContentList.add(node.getAttribute("ACCOUNT_CREDIT"));
	                lContentList.add(node.getAttribute("IFSC_CODE_CREDIT"));
	                lContentList.add(node.getAttribute("CREDIT_AMOUNT"));
	                lContentList.add(srcAcc);
	                Count++;
	            }
	            lDataList.add(lContentList);
	            lDataList.add(Integer.toString(lFlag));
	            lDataList.add(Integer.toString(Count));
	            lDataList.add(paymentType);
	            lDataList.add(fileName);
	        } catch (Exception e) {
	            logger.error(e);
	        }
	        return lDataList;
	    }

	@Override
	public List<PanelDocPayVO> generateAllBankRejCases(String scheme,int startIndex,int maxResults) {
		
		List<PanelDocPayVO> panelDocRejCasesListDtls = new ArrayList<PanelDocPayVO>();
		List<PanelDocPayVO> casesListDtls = new ArrayList<PanelDocPayVO>();
		StringBuffer query= new StringBuffer();
		try{
			query.append(" select eu.firstName as EMPNAME,eu.lastName as DOC_NAME ");
		    query.append(" ,epw.id.wId as WID,epw.id.docId as DOC_ID");
		    query.append(" ,epw.currWrkflwId as CURRWRKFLWID,epw.currOwnrGrp as CURROWNR,epw.prevWrkflwId as PREVWRKFLWID ");
		    query.append(" ,epw.statusFlg as DISTINCTSTATUS,epw.scheme as SCHEME");	    
		    query.append(" ,epw.claimAprvAmt as CLAIM_APRV_AMT,epw.claimRejAmt as CLAIM_REJ_AMT");
		    query.append(" ,epw.claimPendAmt as CLAIM_PEND_AMT,epw.preauthAprvAmt as PREAUTH_APRV_AMT");
		    query.append(" ,epw.preauthRejAmt as PREAUTH_REJ_AMT,epw.preauthPendAmt as PREAUTH_PEND_AMT ");
		    query.append(" ,epw.totalPnldocAmt as TOTAL_PNLDOC_AMT,epw.month as MONTH,epw.year as YEAR");
		    query.append(" ,epad.id.accountNo as ACCOUNTNO");
		    query.append(" from EhfPnlDocWrkflow epw,EhfmUsers eu,EhfmPnlDocAcctDtls epad");
		    query.append(" where eu.userId=epw.id.docId and epad.id.userId=epw.id.docId");
		    query.append(" and eu.dsgnId='"+config.getString("FIN.PanelDocDesgID")+"'");
		    query.append(" and epw.scheme='"+scheme+"' and epw.statusFlg='"+config.getString("FIN.InProgress")+"'");
		    query.append(" and epw.currWrkflwId='"+config.getString("FIN.WorkFlowIdRej")+"'");
		    
		    
		    SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session=factory.openSession();
		    if(startIndex==0 && maxResults==0)
		    	panelDocRejCasesListDtls=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		    else if (maxResults!=0)
		    	panelDocRejCasesListDtls=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		    
		    
		    //panelDocRejCasesListDtls = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString(),args);
		    casesListDtls.addAll(panelDocRejCasesListDtls);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return casesListDtls;

	}

	@Override
	public List<PanelDocPayVO> generateAllRejCasesCEO(String scheme,int startIndex,int maxResults) {
		
		List<PanelDocPayVO> panelDocCasesListDtls = new ArrayList<PanelDocPayVO>();
		List<PanelDocPayVO> casesListDtls = new ArrayList<PanelDocPayVO>();
		StringBuffer query= new StringBuffer();
		SessionFactory factory=hibernateTemplate.getSessionFactory();
	    Session session=factory.openSession();
		try{
	        /*args[0]=config.getString("FIN.AppClaimPanelDocStatus");
		    args[1]=config.getString("FIN.RejClaimPanelDocStatus");
		    args[2]=config.getString("FIN.PendClaimPanelDocStatus");
		    args[3]=config.getString("FIN.AppPreauthPanelDocStatus");
		    args[4]=config.getString("FIN.RejPreauthPanelDocStatus");
		    args[5]=config.getString("FIN.PendPreauthPanelDocStatus");
		    args[6]=config.getString("FIN.PanelDocDesgID");
			args[7]=config.getString("FIN.Reject");		
			args[8]=scheme;
		    query.append("select distinct(name) DOC_NAME ,doc_id DOC_ID,sum(cl_app_amount)||'' CL_APP_AMT,sum(cl_rej_amount)||'' CL_REJ_AMT,sum(cl_pend_amount)||'' CL_PEND_AMT, ");
		    query.append("sum(pr_app_amount)||'' PR_APP_AMT,sum(pr_rej_amount)||'' PR_REJ_AMT,sum(pr_pend_amount)||'' PR_PEND_AMT,sum(total_amount)||'' AMOUNT from ");
		    query.append("(select (u.first_name || ' ' || u.last_name) name,p.doc_id doc_id, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_app_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_rej_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_pend_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_app_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_rej_amount, ");
		    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_pend_amount,p.amount||'' total_amount ");
		    query.append("from ehfm_users u,ehf_pnldoc_case_dtls p,ehf_pnldoc_prcnt_info i ");
		    query.append(" where u.user_id=p.doc_id and u.dsgn_id=? and p.pnl_doc_pmnt_status=? and p.scheme=?)");
	        query.append(" group by name,DOC_ID");*/
	        
		    query.append(" select eu.firstName as EMPNAME,eu.lastName as DOC_NAME ");
		    query.append(" ,epw.id.wId as WID,epw.id.docId as DOC_ID");
		    query.append(" ,epw.currWrkflwId as CURRWRKFLWID,epw.currOwnrGrp as CURROWNR");
		    query.append(" ,epw.statusFlg as DISTINCTSTATUS,epw.scheme as SCHEME");	    
		    query.append(" ,epw.claimAprvAmt as CLAIM_APRV_AMT,epw.claimRejAmt as CLAIM_REJ_AMT");
		    query.append(" ,epw.claimPendAmt as CLAIM_PEND_AMT,epw.preauthAprvAmt as PREAUTH_APRV_AMT");
		    query.append(" ,epw.preauthRejAmt as PREAUTH_REJ_AMT,epw.preauthPendAmt as PREAUTH_PEND_AMT ");
		    query.append(" ,epw.totalPnldocAmt as TOTAL_PNLDOC_AMT,epw.month as MONTH,epw.year as YEAR");
		    query.append(" from EhfPnlDocWrkflow epw,EhfmUsers eu");
		    query.append(" where eu.userId=epw.id.docId");
		    query.append(" and eu.dsgnId='"+config.getString("FIN.PanelDocDesgID")+"'");
		    query.append(" and epw.scheme='"+scheme+"' and epw.statusFlg='"+config.getString("FIN.Reject")+"'");
	       
		    
		    
		    if(startIndex==0 && maxResults==0)
		    	panelDocCasesListDtls=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		    else if (maxResults!=0)
		    	panelDocCasesListDtls=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
		    
		    
	    //panelDocCasesListDtls = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString(),args);
	    casesListDtls.addAll(panelDocCasesListDtls);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
		session.close();
		factory.close();
	}
		return casesListDtls;

	}

	
	private static final AtomicLong LAST_TIME_MS = new AtomicLong();
	public static long uniqueCurrentTimeMS() {
	    long now = System.currentTimeMillis();
	    while(true) {
	        long lastTime = LAST_TIME_MS.get();
	        if (lastTime >= now)
	            now = lastTime+1;
	        if (LAST_TIME_MS.compareAndSet(lastTime, now))
	            return now;
	    }
	}

	
	/*
	 * To get scheme dropdown
	 * @see com.ehs.finance.dao.PanelDocPayDAO#getSchemeStatus()
	 */
	@Override
	public List<PanelDocPayVO> getSchemeStatus() {
		
		return null;
	}
	
	/*
	 * Scheduler to Insert new rows in Panel Doctor WorkFlow Table 
	 * and update Case Details Table of Panel Doctor
	 * with the newly generated WID
	 * based on month for First Date of Every Month 
	 */
	@Override
	public void panelDocInitialisation()
		{
		System.out.println("Scheduler to Initialize Panel Doctor payments started in TG");
		try{
			Calendar presentDate=Calendar.getInstance();
			int year=presentDate.get(Calendar.YEAR);
			int month=presentDate.get(Calendar.MONTH)+1;
			String date=null,lastMonthStr=null,monthStr=null;
			int lastMonth=0,lastYear=0;
			StringBuffer query=new StringBuffer();
			if(month < 13 && month > 1)
				{
					lastMonth=month-1;
					if(lastMonth<10)
						lastMonthStr="0"+lastMonth;
					else
						lastMonthStr=Integer.toString(lastMonth);
					if(month<10)
						monthStr="0"+month;
					else
						monthStr=Integer.toString(month);
					
					lastYear=year;
					date=lastMonthStr+"-"+Integer.toString(year);
				}
			else if(month==1)
				{
					lastMonth=12;
					lastYear=year-1;
					if(lastMonth<10)
						lastMonthStr="0"+lastMonth;
					else
						lastMonthStr=Integer.toString(lastMonth);
					if(month<10)
						monthStr="0"+month;
					else
						monthStr=Integer.toString(month);
					
					date=lastMonthStr+"-"+Integer.toString(lastYear);
				}
			
			
				String args[]=new String[17];
			    args[0]=config.getString("FIN.AppClaimPanelDocStatus");
			    args[1]=config.getString("FIN.RejClaimPanelDocStatus");
			    args[2]=config.getString("FIN.PendClaimPanelDocStatus");
			    args[3]=config.getString("FIN.AppPreauthPanelDocStatus");
			    args[4]=config.getString("FIN.RejPreauthPanelDocStatus");
			    args[5]=config.getString("FIN.PendPreauthPanelDocStatus");
			    
			    args[6]=config.getString("FIN.OrgCpdAppClaimPanelDocStatus");
			    args[7]=config.getString("FIN.OrgCpdRejClaimPanelDocStatus");
			    args[8]=config.getString("FIN.OrgCpdPendClaimPanelDocStatus");
			    args[9]=config.getString("FIN.OrgPpdAppPreauthPanelDocStatus");
			    args[10]=config.getString("FIN.OrgPpdRejPreauthPanelDocStatus");
			    args[11]=config.getString("FIN.OrgPpdPendPreauthPanelDocStatus");
			   
			    args[12]=config.getString("FIN.HoldCasePendingPanelDocStatus");
			    args[13]=config.getString("FIN.HoldCaseReleasedPanelDocStatus");
			    
			    args[14]=config.getString("FIN.PanelDocDesgID");
			    args[15]=config.getString("FIN.Initiation");
				args[16]=date;

				query.append("select distinct(doc_id) DOC_ID,name DOC_NAME,schemeId SCHEME,sum(cl_app_amount+org_cl_cpd_app_amount)||'' CL_APP_AMT,sum(cl_rej_amount+org_cl_cpd_rej_amount)||'' CL_REJ_AMT,sum(cl_pend_amount+org_cl_cpd_pend_amount)||'' CL_PEND_AMT, ");
			    query.append("sum(pr_app_amount+ org_pr_ppd_app_amount)||'' PR_APP_AMT,sum(pr_rej_amount+ org_pr_ppd_rej_amount)||'' PR_REJ_AMT,sum(pr_pend_amount+org_pr_ppd_pend_amount)||'' PR_PEND_AMT,sum(total_amount)||'' AMOUNT, "
			    		+ " sum(hold_pend_amount)||'' HD_PEND,sum(hold_release_amount)||'' HD_RELEASE  from ");
			    query.append("(select (u.first_name || ' ' || u.last_name) name,p.doc_id doc_id,p.SCHEME schemeId,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_app_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_rej_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_pend_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_app_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_rej_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_pend_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') org_cl_cpd_app_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') org_cl_cpd_rej_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') org_cl_cpd_pend_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') org_pr_ppd_app_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') org_pr_ppd_rej_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') org_pr_ppd_pend_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') hold_pend_amount,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') hold_release_amount,");
			    query.append("p.amount||'' total_amount ");
			    query.append("from ehfm_users u,ehf_pnldoc_case_dtls p,EHFM_PNLDOC_ACCT_DTLS epac ");
		    	query.append("where u.user_id=p.doc_id and u.dsgn_id=? and p.pnl_doc_pmnt_status=? and epac.user_id=p.doc_id and  epac.active_yn='Y' and p.SCHEME='CD202' ");
	    		query.append(" and to_date(to_char(p.case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY') >= to_date(?,'MM-YYYY') ");   
	    		query.append(" and trunc(p.case_app_or_pend_dt) < to_date('21/'|| to_char(sysdate, 'mm/yyyy'), 'dd/mm/yyyy')) ");  
			    query.append("group by name,DOC_ID,schemeId");
			    
			    List<PanelDocPayVO> panelDocCases = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
			     
			    
			    
			    if(panelDocCases!=null)
			    		{
			    		if(panelDocCases.size()>0)
			    			{
								System.out.println("No of Panel docs Initiating" +panelDocCases.size()); 
				    			PanelDocPayVO panelDocPayVOWrkLst=new PanelDocPayVO();
				    			panelDocPayVOWrkLst.setMonth(monthStr);
				    			panelDocPayVOWrkLst.setYear(Integer.toString(year));
				    			panelDocPayVOWrkLst.setLastMonth(lastMonthStr);
				    			panelDocPayVOWrkLst.setLastYear(Integer.toString(lastYear));
				    			String ret=insertWrkFlowRecord(panelDocCases,panelDocPayVOWrkLst);
				    			if(ret.equalsIgnoreCase("true"))
				    				logger.info("Workflow Records are successfully inserted and cases are updated in PanelDocInitialisation Scheduler for Month"+month);
				    			else if(ret.equalsIgnoreCase("false"))
				    				logger.error("Either Workflow Records not inserted or cases are not updated in PanelDocInitialisation Scheduler for Month"+month);
			    			}
			    		}
		}	
		catch(Exception e){e.printStackTrace();}
		System.out.println("Scheduler to Initialize Panel Doctor payments ended in TG");
		}
	/*
	 * Method to Insert new rows in Panel Doctor WorkFlow Table 
	 * based on month for First Date of Every Month 
	 */
	@Override
	public String insertWrkFlowRecord(List<PanelDocPayVO> panelDocCases,PanelDocPayVO panelDocPayVOWrkLst)
		{
		String ret="true";
		    if(panelDocCases!=null)
	    	{
	    	 	if(panelDocCases.size()>0)
	    	 		{
	    	 			for(PanelDocPayVO panelDocPayVO:panelDocCases)
	    	 				{
		    	 				try
		    	 					{
			    	 					List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			    	 					
			    	 					criteriaList.add(new GenericDAOQueryCriteria("month",panelDocPayVOWrkLst.getMonth(),
			    	 							CriteriaOperator.EQUALS));
			    	 					criteriaList.add(new GenericDAOQueryCriteria("id.docId",panelDocPayVO.getDOC_ID(),
			    	 							CriteriaOperator.EQUALS));
			    	 					criteriaList.add(new GenericDAOQueryCriteria("scheme",panelDocPayVO.getSCHEME(),
			    	 							CriteriaOperator.EQUALS));
			    	 					criteriaList.add(new GenericDAOQueryCriteria("year",panelDocPayVOWrkLst.getYear(),
			    	 							CriteriaOperator.EQUALS));
			    	 					
			    	 					List<EhfPnlDocWrkflow> ehfPnlDocWrkflowLst=genericDao.findAllByCriteria(EhfPnlDocWrkflow.class, criteriaList);
			    	 					EhfPnlDocWrkflow ehfPnlDocWrkflow=new EhfPnlDocWrkflow();
			    	 					EhfPnlDocWrkFlowId ehfPnlDocWrkFlowId=new EhfPnlDocWrkFlowId();
			    	 					String wId=null;
			    	 					if(ehfPnlDocWrkflowLst!=null)
			    	 						{
												System.out.println(" Panel Doc already initiated "+panelDocPayVO.getDOC_ID());
			    	 							if(ehfPnlDocWrkflowLst.size()>0)
			    	 								{
			    	 									continue;
			    	 								}
			    	 							else
			    	 								{
				    	 								wId=paymentsDao.getSequence("PANEL_DOC_WRKFLW_SEQ");
							    	 					ehfPnlDocWrkFlowId.setwId(Long.parseLong(wId));
							    	 					if(panelDocPayVO.getDOC_ID()!=null)
							    	 						ehfPnlDocWrkFlowId.setDocId(panelDocPayVO.getDOC_ID());
							    	 					ehfPnlDocWrkflow.setId(ehfPnlDocWrkFlowId);
			    	 								}
			    	 						}
			    	 					else
			    	 						{
				    	 						wId=paymentsDao.getSequence("PANEL_DOC_WRKFLW_SEQ");
					    	 					ehfPnlDocWrkFlowId.setwId(Long.parseLong(wId));
					    	 					if(panelDocPayVO.getDOC_ID()!=null)
					    	 						ehfPnlDocWrkFlowId.setDocId(panelDocPayVO.getDOC_ID());
					    	 					ehfPnlDocWrkflow.setId(ehfPnlDocWrkFlowId);
												System.out.println(" Panel Doc initiated"+panelDocPayVO.getDOC_ID()+" W ID" +wId);
			    	 						}
			    	 					
			    	 					ehfPnlDocWrkflow.setPrevWrkflwId(config.getString("FIN.PnlDocSch.PrevWID"));
			    	 					ehfPnlDocWrkflow.setPrevOwnrGrp(config.getString("FIN.NA"));
			    	 					ehfPnlDocWrkflow.setCurrWrkflwId(config.getString("FIN.WorkFlowIdInit"));
			    	 					ehfPnlDocWrkflow.setCurrOwnrGrp(config.getString("FIN.AccountsEO2Grp"));
			    	 					ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.Initiation"));
			    	 					ehfPnlDocWrkflow.setCrtUsr(config.getString("FIN.TCS"));
			    	 					ehfPnlDocWrkflow.setCrtDt(new Date());
			    	 					/*ehfPnlDocWrkflow.setLstUpdDt(lstUpdDt);
			    	 					ehfPnlDocWrkflow.setLstUpdUsr(lstUpdUsr);*/
			    	 					if(panelDocPayVO.getSCHEME()!=null)
			    	 						ehfPnlDocWrkflow.setScheme(panelDocPayVO.getSCHEME());
			    	 					if(panelDocPayVO.getCL_APP_AMT()!=null)
			    	 						ehfPnlDocWrkflow.setClaimAprvAmt(Long.parseLong(panelDocPayVO.getCL_APP_AMT()));
			    	 					if(panelDocPayVO.getCL_PEND_AMT()!=null)
			    	 						ehfPnlDocWrkflow.setClaimPendAmt(Long.parseLong(panelDocPayVO.getCL_PEND_AMT()));
			    	 					if(panelDocPayVO.getCL_REJ_AMT()!=null)
			    	 						ehfPnlDocWrkflow.setClaimRejAmt(Long.parseLong(panelDocPayVO.getCL_REJ_AMT()));
			    	 					if(panelDocPayVO.getPR_APP_AMT()!=null)
			    	 						ehfPnlDocWrkflow.setPreauthAprvAmt(Long.parseLong(panelDocPayVO.getPR_APP_AMT()));
			    	 					if(panelDocPayVO.getPR_PEND_AMT()!=null)
			    	 						ehfPnlDocWrkflow.setPreauthPendAmt(Long.parseLong(panelDocPayVO.getPR_PEND_AMT()));
			    	 					if(panelDocPayVO.getPR_REJ_AMT()!=null)
			    	 						ehfPnlDocWrkflow.setPreauthRejAmt(Long.parseLong(panelDocPayVO.getPR_REJ_AMT()));
			    	 					if(panelDocPayVO.getHD_PEND()!=null)
			    	 					{
			    	 						ehfPnlDocWrkflow.setHoldPendingAmt(Long.parseLong(panelDocPayVO.getHD_PEND()));
			    	 					}
			    	 					if(panelDocPayVO.getHD_RELEASE()!=null)
			    	 					{
			    	 						ehfPnlDocWrkflow.setHoldReleasedAmt(Long.parseLong(panelDocPayVO.getHD_RELEASE()));
			    	 					}
			    	 					if(panelDocPayVO.getAMOUNT()!=null)
			    	 						ehfPnlDocWrkflow.setTotalPnldocAmt(Long.parseLong(panelDocPayVO.getAMOUNT()));
			    	 					
			    	 					ehfPnlDocWrkflow.setMonth(panelDocPayVOWrkLst.getMonth());
			    	 					ehfPnlDocWrkflow.setYear(panelDocPayVOWrkLst.getYear());

			    	 					ehfPnlDocWrkflow=genericDao.save(ehfPnlDocWrkflow);
			    	 					if(ehfPnlDocWrkflow!=null)
				    	 					{
				    	 						panelDocPayVOWrkLst.setwId(Long.parseLong(wId));
				    	 						panelDocPayVOWrkLst.setDOC_ID(panelDocPayVO.getDOC_ID());
				    	 						String retrn=modifyPnlDocCaseDtls(panelDocPayVOWrkLst);
				    	 						if(retrn=="false")
				    	 							{
				    	 								ret="false";
				    	 								logger.error("Workflow Records are inserted but cases are not updated in PanelDocInitialisation Scheduler for WID"+wId);	
				    	 							}
				    	 					}
			    	 					else
			    	 						{
			    	 							ret="false";
			    	 							logger.error("Workflow Records are not inserted although PanelDocInitialisation Scheduler executed for doctor"+panelDocPayVO.getDOC_ID());
			    	 						}	
		    	 					}
		    	 				catch(Exception e)
		    	 					{
//		    	 						e.printStackTrace();
		    	 						logger.error("Issue occured in panelDocInitialisation Scheduler in insertWrkFlowRecord method"+e.getMessage());
		    	 					}
		    	 				
	    	 				}
	    	 			
	    	 		 }
	    	 	  
	    	 }
		    return ret;
		    
		 }
	
	
	/*
	 * Method to update Case Details Table of Panel Doctor
	 * with the newly generated WID
	 * based on month for First Date of Every Month 
	 */
	@Override
	public String modifyPnlDocCaseDtls(PanelDocPayVO panelDocPayVOWrkLst)
		{
			String ret="true";
			if(panelDocPayVOWrkLst!=null)
				{
					if(panelDocPayVOWrkLst.getDOC_ID()!=null && panelDocPayVOWrkLst.getLastMonth()!=null
							&& panelDocPayVOWrkLst.getLastYear()!=null)
						{
							try
								{
									StringBuffer query=new StringBuffer();
									String date=panelDocPayVOWrkLst.getLastMonth()+"-"+panelDocPayVOWrkLst.getLastYear();
									query.append(" select epdc.ID as IDNUM,epdc.CASE_ID as CASE_ID,epdc.DOC_ID as DOC_ID from EHF_PNLDOC_CASE_DTLS epdc,");
									query.append(" EHF_PNLDOC_WORKFLOW epw where epw.SCHEME=epdc.SCHEME");
									query.append(" and epdc.DOC_ID='"+panelDocPayVOWrkLst.getDOC_ID()+"'");
									query.append(" and epw.DOC_ID='"+panelDocPayVOWrkLst.getDOC_ID()+"'");
									query.append(" and epw.MONTH='"+panelDocPayVOWrkLst.getMonth()+"'");
									query.append(" and epw.W_ID='"+panelDocPayVOWrkLst.getwId()+"'");
									query.append(" and epdc.PNL_DOC_PMNT_STATUS='I' ");
									if(panelDocPayVOWrkLst.getCaseType()!=null && !"".equalsIgnoreCase(panelDocPayVOWrkLst.getCaseType()) && panelDocPayVOWrkLst.getCaseType().equalsIgnoreCase("OldCases"))
									{
										query.append(" and to_date(to_char(epdc.CASE_APP_OR_PEND_DT,'MM-YYYY'),'MM-YYYY')<= to_date('"+date+"','MM-YYYY') ");
									}
									else {
										query.append(" and to_date(to_char(epdc.CASE_APP_OR_PEND_DT,'MM-YYYY'),'MM-YYYY')>= to_date('"+date+"','MM-YYYY') ");
										query.append(" and trunc(epdc.case_app_or_pend_dt) < to_date('21/'|| to_char(sysdate, 'mm/yyyy'), 'dd/mm/yyyy') ");
									}
									List<PanelDocPayVO> panelDocPayVOLst=genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString());
									if(panelDocPayVOLst!=null)
										{
											if(panelDocPayVOLst.size()>0)
												{
													for(PanelDocPayVO panelDocPayVOItr:panelDocPayVOLst)
														{
															EhfPanelDocCaseDtls ehfPanelDocCaseDtls=new EhfPanelDocCaseDtls();
															Long id=panelDocPayVOItr.getIDNUM().longValue();
															ehfPanelDocCaseDtls=genericDao.findById(EhfPanelDocCaseDtls.class,Long.class,id);
															if(ehfPanelDocCaseDtls!=null)
																{
																	ehfPanelDocCaseDtls.setWorkFlwId(panelDocPayVOWrkLst.getwId());
																	ehfPanelDocCaseDtls.setLstUpdDt(new Date());
																	ehfPanelDocCaseDtls.setLstUpdUsr(config.getString("FIN.TCS"));
																	ehfPanelDocCaseDtls=genericDao.save(ehfPanelDocCaseDtls);
																}	
															if(ehfPanelDocCaseDtls==null)
																{
																	ret="false";
																	logger.error("A CaseID has been failed to Update in panelDocInitialisation Scheduler in insertWrkFlowRecord method");
																}
														}
												}
										}
								}
							catch(Exception e)
								{
//									e.printStackTrace();
	    	 						logger.error("Issue occured in panelDocInitialisation Scheduler in modifyPnlDocCaseDtls method "+e.getMessage());
								}
						}
				}
		return ret;
		}	
	
	
	/* 
	 * Scheduler to Insert new rows in Panel Doctor WorkFlow Table 
	 * and update Case Details Table of Panel Doctor
	 * with the newly generated WID
	 * for doctor ids specified in CONFIG
	 */
	@Override
	public void panelDocInitialisationOld()
		{
		logger.info("Scheduler to Initialize old Panel Doctor payments started in TG ");
		try{
			Calendar presentDate=Calendar.getInstance();
			int year=0000;
			int size=0;
			int month=0;
			int currYear=presentDate.get(Calendar.YEAR);
			
			year=currYear;
			
					
			String doctorId="";
			String selectedCondition="";
			String doctorsListString=config.getString("FIN.DOCTORIDSOLD");
			if(doctorsListString!=null && !doctorsListString.equals(""))
			{							
			List<String> doctorsList=Arrays.asList(doctorsListString.split("~"));
			 selectedCondition = " and  p.doc_id in ( " ;
			 for ( String docId:doctorsList )
		        {
				 doctorId = doctorId + "'" + docId + "'," ;
		        }
			 doctorId = doctorId.substring ( 0, doctorId.lastIndexOf ( "," ) ) ;
		     selectedCondition = selectedCondition + doctorId + " )" ;
			}
			
				month=presentDate.get(Calendar.MONTH)+1;
				logger.info("Panel Doctor Initialization for the month  : "+month+" started");
			String date=null,lastMonthStr=null,monthStr=null;
			int lastMonth=0,lastYear=0;
			StringBuffer query=new StringBuffer();
			if(month < 13 && month > 1)
				{
					lastMonth=month-1;
					if(lastMonth<10)
						lastMonthStr="0"+lastMonth;
					else
						lastMonthStr=Integer.toString(lastMonth);
					if(month<10)
						monthStr="0"+month;
					else
						monthStr=Integer.toString(month);
					
					lastYear=year;
					date=lastMonthStr+"-"+Integer.toString(year);
				}
			else if(month==1)
				{
					lastMonth=12;
					lastYear=year-1;
					if(lastMonth<10)
						lastMonthStr="0"+lastMonth;
					else
						lastMonthStr=Integer.toString(lastMonth);
					if(month<10)
						monthStr="0"+month;
					else
						monthStr=Integer.toString(month);
					
					date=lastMonthStr+"-"+Integer.toString(lastYear);
				}
			
			
				String args[]=new String[9];
			    args[0]=config.getString("FIN.AppClaimPanelDocStatus");
			    args[1]=config.getString("FIN.RejClaimPanelDocStatus");
			    args[2]=config.getString("FIN.PendClaimPanelDocStatus");
			    args[3]=config.getString("FIN.AppPreauthPanelDocStatus");
			    args[4]=config.getString("FIN.RejPreauthPanelDocStatus");
			    args[5]=config.getString("FIN.PendPreauthPanelDocStatus");
			    args[6]=config.getString("FIN.PanelDocDesgID");
			    args[7]=config.getString("FIN.Initiation");
				args[8]=date;

				query.append("select distinct(doc_id) DOC_ID,name DOC_NAME,schemeId SCHEME,sum(cl_app_amount)||'' CL_APP_AMT,sum(cl_rej_amount)||'' CL_REJ_AMT,sum(cl_pend_amount)||'' CL_PEND_AMT, ");
			    query.append("sum(pr_app_amount)||'' PR_APP_AMT,sum(pr_rej_amount)||'' PR_REJ_AMT,sum(pr_pend_amount)||'' PR_PEND_AMT,sum(total_amount)||'' AMOUNT from ");
			    query.append("(select (u.first_name || ' ' || u.last_name) name,p.doc_id doc_id,p.SCHEME schemeId,");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_app_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_rej_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') cl_pend_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_app_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_rej_amount, ");
			    query.append("decode(p.case_pmnt_status,?,p.amount,'0') pr_pend_amount,p.amount||'' total_amount ");
			    query.append("from ehfm_users u,ehf_pnldoc_case_dtls p,EHFM_PNLDOC_ACCT_DTLS epac ");
		    	query.append("where u.user_id=p.doc_id and u.dsgn_id=? and p.pnl_doc_pmnt_status=? and epac.user_id=p.doc_id and epac.active_yn='Y' and p.SCHEME='CD202' ");
		    	if(selectedCondition!=null &&  !selectedCondition.equals(""))
		    		 query.append(selectedCondition);
	    		query.append(" and to_date(to_char(p.case_app_or_pend_dt,'MM-YYYY'),'MM-YYYY')<= to_date(?,'MM-YYYY')) ");  
			    query.append("group by name,DOC_ID,schemeId");
			    
			    List<PanelDocPayVO> panelDocCases = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
			    
			    
			    if(panelDocCases!=null)
			    		{
			    		if(panelDocCases.size()>0)
			    			{
				    			PanelDocPayVO panelDocPayVOWrkLst=new PanelDocPayVO();
				    			panelDocPayVOWrkLst.setMonth(monthStr);//10
				    			panelDocPayVOWrkLst.setYear(Integer.toString(year));
				    			panelDocPayVOWrkLst.setLastMonth(lastMonthStr);//09
				    			panelDocPayVOWrkLst.setLastYear(Integer.toString(lastYear));
				    			panelDocPayVOWrkLst.setCaseType("OldCases");
				    			String ret=insertWrkFlowRecord(panelDocCases,panelDocPayVOWrkLst);
				    			if(ret.equalsIgnoreCase("true"))
				    				logger.info("Workflow Records are successfully inserted and cases are updated in PanelDocInitialisation Scheduler for Old Cases");
				    			else if(ret.equalsIgnoreCase("false"))
				    				logger.error("Either Workflow Records not inserted or cases are not updated in PanelDocInitialisation Scheduler for Old Cases");
			    			}
			    		}
			    logger.info("Scheduler to Initialize old Panel Doctor payments ended in TG ");
			   // logger.info("Panel Doctor Initialization for the month  : "+month+" ended");
		
		}
		catch(Exception e){e.printStackTrace();}
		
		}
	
	


}
