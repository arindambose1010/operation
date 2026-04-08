package com.ahct.attachments.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.configuration.CompositeConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.attachments.dao.AttachmentDao;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.AsritCaseSurgeryInvest;
import com.ahct.model.EhfAhcDocAttch;
import com.ahct.model.EhfCaseDocAttch;
import com.ahct.model.EhfCaseTherapyInvest;
import com.ahct.model.EhfChronicDocAttch;
import com.ahct.model.EhfCochlearFollowup;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfFollowupDocAttch;
import com.ahct.model.EhfmSeq;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class AttachmentServiceImpl implements AttachmentService{
	GenericDAO genericDao;
	AttachmentDao attachmentDao;
	HibernateTemplate hibernateTemplate;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	 public Map getEnrollmentAttachments(HashMap lparamMap) 
	    {
		 
		Map lMap = null;
		try {
			//lMap = attachmentDao.getEnrollmentAttachmentsByHql(lparamMap);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lMap;
	}
	 public Map getCasesAttachmentsByHql(HashMap lparamMap) 
	 {
		 Map lMap = null;
			try {
				lMap = attachmentDao.getCasesAttachmentsByHql(lparamMap);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lMap; 
	 }
	
	 public Map getPreauthViewAttachmentsHql(HashMap<String, Object> lparamMap) 
	 {

		 Map lMap = null;
			try {
				lMap = attachmentDao.getPreauthViewAttachmentsHql(lparamMap);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lMap;   
	 }
	 /*public String  upldEnrollmentAttach(AttachmentVO AttachmentVO)
	 {
		 String msg = null;
		 String rej_cnt="0";
         String doc_cnt="0";
		 Date date = new Date();
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		 Session session=factory.openSession();
		 StringBuffer query = new StringBuffer();
		 StringBuffer query1 = new StringBuffer();
		 List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		 try{
			 // CD3018 -- ddo/sto rejected
			 //CD30206 -- executive rejected
			if(AttachmentVO.getAttachType() != null && AttachmentVO.getAttachType().equalsIgnoreCase("CD3000"))
			{
				query.append("  select count(ea.enrollStatus)  from EhfEnrollAudit ea, EhfEnrollmentFamily ef  ");
				query.append("   where ea.enrollParentId ='"+AttachmentVO.getEmpParentId()+"' and ea.id.enrollId = ef.enrollId  and ef.enrollSno = '0' and ea.enrollStatus in(  '"+config.getString("ENROLLMENT_DDO_REJECTION")+"','"+config.getString("ENROLLMENT_TL_REJECTION")+"','"+config.getString("ENROLLMENT_NEW_EXE_REJECT")+"') ");
				Query regList=session.createQuery(query.toString());
				Iterator regListItr=regList.iterate();
	            while(regListItr.hasNext())
	            {
	            	rej_cnt = regListItr.next().toString();
	            }
	            query1.append(" select count(a.docSeqId)  from  EhfEmployeeDocAttach a where ");
	            query1.append( " a.employeeParntId ='"+AttachmentVO.getEmpParentId()+"'  and  a.attachType = '"+AttachmentVO.getAttachType() +"' and a.docCount like '%~"+rej_cnt+"' ");
	            Query docList=session.createQuery(query1.toString());
				Iterator docListItr=docList.iterate();
	            while(docListItr.hasNext())
	            {
	            	//System.out.println(hodListItr.next()); 
	            	doc_cnt = docListItr.next().toString();
	            }
	            
			}
			if(AttachmentVO.getAttachType() != null && AttachmentVO.getAttachType().equalsIgnoreCase("CD3002"))
			{
				 criteriaList.add(new GenericDAOQueryCriteria("attachactiveYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			   criteriaList.add(new GenericDAOQueryCriteria("employeeParntId",AttachmentVO.getEmpParentId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			   criteriaList.add(new GenericDAOQueryCriteria("attachType","CD3002",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			}
			if(AttachmentVO.getAttachType() != null && AttachmentVO.getAttachType().equalsIgnoreCase("CD3000"))
			{
				   int cnt = Integer.parseInt(rej_cnt)-1;
				   criteriaList.add(new GenericDAOQueryCriteria("attachactiveYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				   criteriaList.add(new GenericDAOQueryCriteria("employeeParntId",AttachmentVO.getEmpParentId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				   criteriaList.add(new GenericDAOQueryCriteria("docCount","~"+cnt,GenericDAOQueryCriteria.CriteriaOperator.LIKE_ENDS_WITH));
				   criteriaList.add(new GenericDAOQueryCriteria("attachType","CD3000",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			}
			List<EhfEmployeeDocAttach> LstehfEmployeeDocAttach=genericDao.findAllByCriteria(EhfEmployeeDocAttach.class, criteriaList);
			for(EhfEmployeeDocAttach ehfEmployeeDocAttach : LstehfEmployeeDocAttach)
			{
				ehfEmployeeDocAttach.setAttachactiveYN("N");
				ehfEmployeeDocAttach.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
				ehfEmployeeDocAttach.setLstUpdUsr(AttachmentVO.getCrtUsrName());
				genericDao.save(ehfEmployeeDocAttach);
			}
		 EhfEmployeeDocAttach EhfEmployeeDocAttach = new EhfEmployeeDocAttach();
		 EhfEmployeeDocAttach.setActualName(AttachmentVO.getActualName());
		 EhfEmployeeDocAttach.setAttachactiveYN("Y");
		 EhfEmployeeDocAttach.setAttachType(AttachmentVO.getAttachType());
		 EhfEmployeeDocAttach.setCrtDt(new java.sql.Timestamp(date.getTime()));
		 EhfEmployeeDocAttach.setCrtUser(AttachmentVO.getCrtUsrName());
		 EhfEmployeeDocAttach.setDocCount(doc_cnt+"~"+rej_cnt);
		
		 EhfEmployeeDocAttach.setDocSeqId( Long.parseLong(String.valueOf(getNextVal1("EHF_EMPLOYEE_ATTACH"))));
		 EhfEmployeeDocAttach.setEmployeeParntId(AttachmentVO.getEmpParentId());
		 EhfEmployeeDocAttach.setSavedName(AttachmentVO.getSavedName());
		 EhfEmployeeDocAttach = genericDao.save(EhfEmployeeDocAttach);
		 msg = AttachmentVO.getActualName();
		 
		 }
		
		 catch(Exception e)
		 {
			e.printStackTrace();
			msg ="failure";
			
		 }
		 finally{
			 session.close();
		 }
		   return msg;
	 }*/
	 public Long getNextVal(String SeqName)
	 {
		  Long cnt = (long) 0;
		  try{
			  EhfmSeq SgvcSeqMst = genericDao.findFirstByPropertyMatch(EhfmSeq.class,"tableName",SeqName);
			  if(SgvcSeqMst != null)
			  {
				 cnt =  SgvcSeqMst.getSeqNextVal(); 
			  }
			  SgvcSeqMst.setSeqNextVal(SgvcSeqMst.getSeqNextVal()+1);
			  genericDao.save(SgvcSeqMst);
				
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
			
			  return cnt;
		}
		  public String getNextVal1(String pStrSeqName)
			 {
			  String lStrSeqRetVal = "";   
			    
			    try{
			     
			    	StringBuffer query = new StringBuffer();
			        query.append(" SELECT "+pStrSeqName+".NEXTVAL NEXTVAL  FROM DUAL ");
			        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

			        if(seqList != null){
			        	
			          lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
			        }
			    }catch(Exception e){
			    
			    	e.printStackTrace();
			    	
			    }
			    
			    return lStrSeqRetVal;
			  
	 }
	 public String getContentType(String lStrfileExtension) {
	        String lStrContentType = "";
	        if (lStrfileExtension != null && lStrfileExtension.length() > 0) {
	            if (lStrfileExtension.equalsIgnoreCase("doc"))
	                lStrContentType = "application/msword";
	            else if (lStrfileExtension.equalsIgnoreCase("rtf"))
	                lStrContentType = "application/msword";
	            else if (lStrfileExtension.equalsIgnoreCase("xls"))
	                lStrContentType = "application/vnd.ms-excel";
	            else if (lStrfileExtension.equalsIgnoreCase("ppt"))
	                lStrContentType = "application/vnd.ms-powerpoint";
	            else if (lStrfileExtension.equalsIgnoreCase("pdf"))
	                lStrContentType = "application/pdf";
	            else if (lStrfileExtension.equalsIgnoreCase("txt"))
	                lStrContentType = "text/plain";
	            else if (lStrfileExtension.equalsIgnoreCase("sxw"))
	                lStrContentType = "application/vnd.sun.xml.writer";
	            else if (lStrfileExtension.equalsIgnoreCase("stw"))
	                lStrContentType = "application/vnd.sun.xml.writer.template";
	            else if (lStrfileExtension.equalsIgnoreCase("sxd"))
	                lStrContentType = "application/vnd.sun.xml.draw";
	            else if (lStrfileExtension.equalsIgnoreCase("std"))
	                lStrContentType = "application/vnd.sun.xml.draw.template";
	            else if (lStrfileExtension.equalsIgnoreCase("sxc"))
	                lStrContentType = "application/vnd.sun.xml.calc";
	            else if (lStrfileExtension.equalsIgnoreCase("sxi"))
	                lStrContentType = "application/vnd.sun.xml.impress";
	            else if (lStrfileExtension.equalsIgnoreCase("zip"))
	                lStrContentType = "application/x-zip-compressed";
	            else if (lStrfileExtension.equalsIgnoreCase("png"))
	                lStrContentType = "image/png";
	            else if (lStrfileExtension.equalsIgnoreCase("tif") || 
	                     lStrfileExtension.equalsIgnoreCase("tiff"))
	                lStrContentType = "image/tiff";
	            else
	                lStrContentType = "application/octet-stream";
	        } else
	            lStrContentType = "application/octet-stream";
	        return lStrContentType;
	    }
	 public String removeAttachments(String docSeqId, String userId)
	 {
		 String msg = null;
		 Date date = new Date();
		 try{
			 if(docSeqId!=null && !docSeqId.equalsIgnoreCase("")){
		     String docId[]= docSeqId.split(",");
           for(int i=0; i<docId.length; i++) {
        	   if(docId[i] != null)
        	   {
      			  List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
        		   criteriaList.add(new GenericDAOQueryCriteria("docSeqId",Long.parseLong(docId[i]),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
              	 List<EhfEmployeeDocAttach> LstehfEmployeeDocAttach=genericDao.findAllByCriteria(EhfEmployeeDocAttach.class, criteriaList);
              	 for(EhfEmployeeDocAttach ehfEmployeeDocAttach : LstehfEmployeeDocAttach)
              	 {
              	      ehfEmployeeDocAttach.setAttachactiveYN("N");
                 	  ehfEmployeeDocAttach.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
                 	  ehfEmployeeDocAttach.setLstUpdUsr(userId);
                 	  genericDao.save(ehfEmployeeDocAttach);   
        	   }
         }
           }
           	 
        	 }
        	 msg = "success"; 
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
			msg = "failure";
		 }
       
		 return msg;
	 }
	 public String removePreauthAttachments(String docSeqId,String userId)
	 {
		 String msg = null;
		 Date date = new Date();
		 try{
			 if(docSeqId!=null && !"".equalsIgnoreCase(docSeqId))
			 {
			     String docId[]= docSeqId.split(",");
			     for(int i=0; i<docId.length; i++) 
			     {
			    	 if(docId[i] != null)
			    	 {
	        		    List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	        		    criteriaList.add(new GenericDAOQueryCriteria("docSeqId",Long.parseLong(docId[i]),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	              	 	List<EhfCaseDocAttch> LstEhfCaseDocAttch=genericDao.findAllByCriteria(EhfCaseDocAttch.class, criteriaList);
	              	 	for(EhfCaseDocAttch ehfCaseDocAttch : LstEhfCaseDocAttch)
	              	 	{
		              		ehfCaseDocAttch.setActiveYN("N");
		              		ehfCaseDocAttch.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
		              		ehfCaseDocAttch.setLstUpdUsr(userId);
		                 	genericDao.save(ehfCaseDocAttch);   
	              	 	}
			    	 }
	        	
	           	 
	        	 }
	        	 msg = "success"; 
			 }
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
			msg = "failure";
		 }
       
		 return msg; 
	 }

	 public String removeChronicAttachments(String docSeqId,String userId)
	 {
		 String msg = null;
		 Date date = new Date();
		 try{
			 if(docSeqId!=null && !"".equalsIgnoreCase(docSeqId))
			 {
			     String docId[]= docSeqId.split(",");
			     for(int i=0; i<docId.length; i++) 
			     {
			    	 if(docId[i] != null)
			    	 {
	        		    List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	        		    criteriaList.add(new GenericDAOQueryCriteria("docSeqId",Long.parseLong(docId[i]),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	              	 	List<EhfChronicDocAttch> LstEhfChronicDocAttch=genericDao.findAllByCriteria(EhfChronicDocAttch.class, criteriaList);
	              	 	for(EhfChronicDocAttch ehfChronicDocAttch : LstEhfChronicDocAttch)
	              	 	{
	              	 		ehfChronicDocAttch.setActiveYN("N");
	              	 		ehfChronicDocAttch.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
	              	 		ehfChronicDocAttch.setLstUpdUsr(userId);
		                 	genericDao.save(ehfChronicDocAttch);   
	              	 	}
			    	 }
	        	
	           	 
	        	 }
	        	 msg = "success"; 
			 }
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
			msg = "failure";
		 }
       
		 return msg; 
	 }

	 public String removeAhcAttachments(String docSeqId,String userId)
	 {
		 String msg = null;
		 Date date = new Date();
		 try{
			 if(docSeqId!=null && !"".equalsIgnoreCase(docSeqId))
			 {
			     String docId[]= docSeqId.split(",");
			     for(int i=0; i<docId.length; i++) 
			     {
			    	 if(docId[i] != null)
			    	 {
	        		    List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	        		    criteriaList.add(new GenericDAOQueryCriteria("docSeqId",Long.parseLong(docId[i]),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	              	 	List<EhfAhcDocAttch> LstEhfAhcDocAttch=genericDao.findAllByCriteria(EhfAhcDocAttch.class, criteriaList);
	              	 	for(EhfAhcDocAttch ehfAhcDocAttch : LstEhfAhcDocAttch)
	              	 	{
	              	 		ehfAhcDocAttch.setActiveYN("N");
	              	 		ehfAhcDocAttch.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
	              	 		ehfAhcDocAttch.setLstUpdUsr(userId);
		                 	genericDao.save(ehfAhcDocAttch);   
	              	 	}
			    	 }
	        	
	           	 
	        	 }
	        	 msg = "success"; 
			 }
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
			msg = "failure";
		 }
       
		 return msg; 
	 }

	 public String  upldCaseAttachments(AttachmentVO AttachmentVO)
	 {
		String msg = null;
		Long nextVal =  Long.parseLong( getNextVal1(config.getString("EHF_CASE_DOC_ATTCH_seq")));
		StringBuffer query = new StringBuffer();
		SessionFactory factory= hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		 String rej_cnt="0";
         String doc_cnt="0";
		try{
		Date date = new Date();
		 List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		try{
		if(AttachmentVO.getAttachType() != null && AttachmentVO.getAttachType().equalsIgnoreCase(config.getString("preauth_onbed_cmbDtlId")))
		{
			   criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			   criteriaList.add(new GenericDAOQueryCriteria("caseId",AttachmentVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			   criteriaList.add(new GenericDAOQueryCriteria("attachType",config.getString("preauth_onbed_cmbDtlId"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
		}
		
		
		/**
		 * uploading latest files in consent documents
		 */
		
		if(AttachmentVO.getAttachType() != null && AttachmentVO.getAttachType().equalsIgnoreCase(config.getString("Signedprf_docs")))
		{
			query.append("  select count(ea.actId)  from EhfAudit ea  ");
			query.append("   where ea.id.caseId ='"+AttachmentVO.getCaseId()+"'  and ea.actId in(  '"+config.getString("preauth_partail_save")+"') ");
			Query regList=session.createQuery(query.toString());
			Iterator regListItr=regList.iterate();
            while(regListItr.hasNext())
            {
            	rej_cnt = regListItr.next().toString();
            }
            query = new StringBuffer();
            query.append(" select count(a.docSeqId)  from  EhfCaseDocAttch a where ");
            query.append( " a.caseId ='"+AttachmentVO.getCaseId()+"'  and  a.attachType = '"+AttachmentVO.getAttachType() +"' and a.docCount like '%~"+rej_cnt+"' ");
            Query docList=session.createQuery(query.toString());
			Iterator docListItr=docList.iterate();
            while(docListItr.hasNext())
            {
            	//System.out.println(hodListItr.next()); 
            	doc_cnt = docListItr.next().toString();
            }
            
		}
		
		if(AttachmentVO.getAttachType() != null && AttachmentVO.getAttachType().equalsIgnoreCase(config.getString("Signedprf_docs")))
		{
			   int cnt = Integer.parseInt(rej_cnt)-1;
			   criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			   criteriaList.add(new GenericDAOQueryCriteria("caseId",AttachmentVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			   criteriaList.add(new GenericDAOQueryCriteria("docCount","~"+cnt,GenericDAOQueryCriteria.CriteriaOperator.LIKE_ENDS_WITH));
			   criteriaList.add(new GenericDAOQueryCriteria("attachType",config.getString("Signedprf_docs"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
		}
		
		
		/**
		 * end
		 */
		
		
		
		List<EhfCaseDocAttch> LstAsritCaseDocAttch=genericDao.findAllByCriteria(EhfCaseDocAttch.class, criteriaList);
		for(EhfCaseDocAttch asritCaseDocAttch : LstAsritCaseDocAttch)
		{
			asritCaseDocAttch.setActiveYN("N");
			asritCaseDocAttch.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
			asritCaseDocAttch.setLstUpdUsr(AttachmentVO.getCrtUsrName());
			genericDao.save(asritCaseDocAttch);
		}
		
		EhfCaseDocAttch asritCaseDocAttch = new EhfCaseDocAttch();
		asritCaseDocAttch.setCaseId(AttachmentVO.getCaseId());
		asritCaseDocAttch.setActualName(AttachmentVO.getActualName());
		asritCaseDocAttch.setSavedName(AttachmentVO.getSavedName());
		asritCaseDocAttch.setCrtUsr(AttachmentVO.getCrtUsrName());
		asritCaseDocAttch.setCrtDt(new java.sql.Timestamp(date.getTime()));
		asritCaseDocAttch.setAttachType(AttachmentVO.getAttachType());
		asritCaseDocAttch.setDocSeqId(nextVal.longValue());
		asritCaseDocAttch.setActiveYN("Y");
		asritCaseDocAttch.setDocCount(doc_cnt+"~"+rej_cnt);
		asritCaseDocAttch = genericDao.save(asritCaseDocAttch);
		
		 msg = AttachmentVO.getActualName();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(AttachmentVO.getSpltType() != null )
		{
			AsritCaseSurgeryInvest asritCaseSurgeryInvest = new AsritCaseSurgeryInvest();
			asritCaseSurgeryInvest.setCaseId(AttachmentVO.getCaseId());
			asritCaseSurgeryInvest.setCaseSurgryId(AttachmentVO.getSurgeryId());
			asritCaseSurgeryInvest.setDocSeqId( nextVal);
			asritCaseSurgeryInvest.setPreOpPostOp(AttachmentVO.getSpltType());
			asritCaseSurgeryInvest.setSurgeryInvest(AttachmentVO.getSurgInvstId());
			genericDao.save(asritCaseSurgeryInvest);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
			factory.close();
		}
		return msg;
	 }
	 public String  upldAhcAttachments(AttachmentVO AttachmentVO)
	 {
		String msg = null;
		Long nextVal =  Long.parseLong( getNextVal1("EHF_AHC_DOC_ATTACH_SEQ"));
		
		 String rej_cnt="0";
         String doc_cnt="0";
		try{
		Date date = new Date();
		try{
		
		EhfAhcDocAttch ahcCaseDocAttch = new EhfAhcDocAttch();
		ahcCaseDocAttch.setAhcId(AttachmentVO.getCaseId());
		ahcCaseDocAttch.setActualName(AttachmentVO.getActualName());
		ahcCaseDocAttch.setSavedName(AttachmentVO.getSavedName());
		ahcCaseDocAttch.setCrtUsr(AttachmentVO.getCrtUsrName());
		ahcCaseDocAttch.setCrtDt(new java.sql.Timestamp(date.getTime()));
		ahcCaseDocAttch.setAttachType(AttachmentVO.getAttachType());
		ahcCaseDocAttch.setDocSeqId(nextVal.longValue());
		ahcCaseDocAttch.setActiveYN("Y");
		ahcCaseDocAttch.setDocCount(doc_cnt+"~"+rej_cnt);
		ahcCaseDocAttch = genericDao.save(ahcCaseDocAttch);
		
		 msg = AttachmentVO.getActualName();
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return msg;
	 }
	 public String getIvestPath(String docSeqId)
	 {
		 String path = null;
		 try
		 {
			 EhfCaseTherapyInvest ehfCaseTherapyInvest = genericDao.findById(EhfCaseTherapyInvest.class, Long.class, Long.parseLong(docSeqId) ); 
		if(ehfCaseTherapyInvest != null)
		{
			path =ehfCaseTherapyInvest.getAttachTotalPath(); 	
		}
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
		 }
		 return path;
	 }
	 
	public Map<String, Object> getPayResponseAttachment(
			HashMap<String, Object> hParamMap) {
		 Map lMap = null;
			try {
				lMap = attachmentDao.getPayResponseAttachment(hParamMap);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lMap;   
	}
	
	public String upldFlupAttachments(AttachmentVO attachmentVO) {
		String msg = null;
		Long nextVal =  Long.parseLong( getNextVal1("FOLLOWUP_DOC_ATTCH_seq"));
		try{
		Date date = new Date();
		EhfFollowupDocAttch ehfFlupDocAttch = new EhfFollowupDocAttch();
		ehfFlupDocAttch.setFollowUpId(attachmentVO.getCaseId());
		ehfFlupDocAttch.setActualName(attachmentVO.getActualName());
		ehfFlupDocAttch.setSavedName(attachmentVO.getSavedName());
		ehfFlupDocAttch.setCrtUsr(attachmentVO.getCrtUsrName());
		ehfFlupDocAttch.setCrtDt(new java.sql.Timestamp(date.getTime()));
		ehfFlupDocAttch.setAttachType(attachmentVO.getAttachType());
		ehfFlupDocAttch.setDocSeqId(nextVal.longValue());
		ehfFlupDocAttch.setActiveYN("Y");
		ehfFlupDocAttch = genericDao.save(ehfFlupDocAttch);
		
		 msg = attachmentVO.getActualName();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return msg;
	}
	
	public String upldChronicAttachments(AttachmentVO attachmentVO) {
		String msg = null;
		Long nextVal =  Long.parseLong( getNextVal1("CHRONIC_DOC_ATTCH_seq"));
		try{
		Date date = new Date();
		EhfChronicDocAttch ehfFlupDocAttch = new EhfChronicDocAttch();
	 
		ehfFlupDocAttch.setChronicId(attachmentVO.getChronicId());
		ehfFlupDocAttch.setChronicNo(attachmentVO.getChronicNo());
		ehfFlupDocAttch.setActualName(attachmentVO.getActualName());
		ehfFlupDocAttch.setSavedName(attachmentVO.getSavedName());
		ehfFlupDocAttch.setCrtUsr(attachmentVO.getCrtUsrName());
		ehfFlupDocAttch.setCrtDt(new java.sql.Timestamp(date.getTime()));
		ehfFlupDocAttch.setAttachType(attachmentVO.getAttachType());
		ehfFlupDocAttch.setDocSeqId(nextVal.longValue());
		ehfFlupDocAttch.setActiveYN("Y");
		ehfFlupDocAttch = genericDao.save(ehfFlupDocAttch);
		
		 msg = attachmentVO.getActualName();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return msg;
	}
	
	public String removeFollowUpAttachments(String doc_seq_id, String lStrEmpId) {
		String msg = null;
		 Date date = new Date();
		 try{
		     String docId[]= doc_seq_id.split(",");
          for(int i=0; i<docId.length; i++) {
       	   if(docId[i] != null)
       	   {
     			  List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
       		      criteriaList.add(new GenericDAOQueryCriteria("docSeqId",Long.parseLong(docId[i]),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
             	 List<EhfFollowupDocAttch> LstEhfFPDocAttch=genericDao.findAllByCriteria(EhfFollowupDocAttch.class, criteriaList);
             	 for(EhfFollowupDocAttch ehfFollowUpDocAttch : LstEhfFPDocAttch)
             	 {
             		ehfFollowUpDocAttch.setActiveYN("N");
             		ehfFollowUpDocAttch.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
             		ehfFollowUpDocAttch.setLstUpdUsr(lStrEmpId);
                	  genericDao.save(ehfFollowUpDocAttch);   
       	   }
        }
       	
          	 
       	 }
       	 msg = "success"; 
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
			msg = "failure";
		 }
      
		 return msg; 
	}
	@Override
	public Map<String, Object> getAhcAttachmentsHql(
			HashMap<String, Object> hParamMap) {
		 Map lMap = null;
			try {
				lMap = attachmentDao.getAhcAttachmentsHql(hParamMap);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lMap;   
		
		
	}
	/*
	 * Added by Srikalyan for getting Cochlear 
	 * Followup Details 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EhfCochlearFollowup getCochFlpDtls(String cohclearFlpId)
		{
			EhfCochlearFollowup ec=new EhfCochlearFollowup();
			try
				{
					Map<String,Object> map=attachmentDao.idMethod("com.ahct.model.EhfCochlearFollowup",cohclearFlpId);
					ec=(EhfCochlearFollowup)map.get("com.ahct.model.EhfCochlearFollowup");
				}
			catch(Exception e)
				{
					e.printStackTrace();
				}
			return ec; 
		}
	@Override
	public String getPharmaBillAttch(String lstrCaseId) 
	{
		List<LabelBean> pharmaList=new ArrayList<LabelBean>(); 
		String msg= "Failure";
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append(" select ec.case_id as ID from ehf_case ec, ehf_patient ep, ehf_patient_drugs_nabh epd ");
			query.append(" WHERE ec.case_id = epd.case_id and epd.active_yn = 'Y' and ep.patient_id = ec.case_patient_no  and ep.patient_ipop = 'IPM' ");
			query.append(" and ec.scheme_id='CD202' and ec.case_id='"+lstrCaseId+"' ");
			
			pharmaList=genericDao.executeSQLQueryList ( LabelBean.class,query.toString());
			if(pharmaList.size() >0)
			{
				if(pharmaList != null)
					msg= "Success";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
			msg = "Failure";
		}
		return msg;
	}
	 }
	
