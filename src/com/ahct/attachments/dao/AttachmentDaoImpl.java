package com.ahct.attachments.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.AsrimCombo;
import com.ahct.model.EhfAttachDisplay;
import com.ahct.model.EhfAttachType;
import com.ahct.model.EhfCaseDocAttch;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfCochlearFollowup;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class AttachmentDaoImpl implements AttachmentDao{
	GenericDAO genericDao;

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	private final static Logger LOGGER = Logger
			.getLogger(AttachmentDaoImpl.class);

	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	public Map getCasesAttachmentsByHql(HashMap lparamMap) {
		List<Object> lResList = new ArrayList<Object>();
		List<Object> lTempList = null;
		Map<String, Object> lResMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		int licnt = ASRIConstants.ZERO_VALUE;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session = factory.openSession();
		StringBuffer query = new StringBuffer();
		String[][] attachIdList = new String[100][4];
		String updType= (String)lparamMap.get("UpdType");
        String viewType=(String)lparamMap.get("ViewStatus");
        String caseId=(String)lparamMap.get(ASRIConstants.CASE_ID);
        String userRole = "CD9";
        updType ="all";
		viewType = "CASE_PRE";
		caseId="34431";
		
		int idcount = ASRIConstants.ZERO_VALUE;
		try {
			query.append("  from AsrimCombo ac , EhfAttachType aaut where  ");
			query.append("    aaut.id.docType = ac.id.cmbDtlId and aaut.id.updType = '"
					+ updType + "'  ");
			query.append("   and aaut.activeYN ='Y' order by aaut.docName , ac.cmbOrder ");
			Query docList = session.createQuery(query.toString());
			Iterator docListItr = docList.iterate();
			while (docListItr.hasNext()) {
				Object[] pair = (Object[]) docListItr.next();
				AsrimCombo AsrimCombo = (AsrimCombo) pair[0];
				EhfAttachType EhfAttachType = (EhfAttachType) pair[1];
				attachIdList[idcount][ASRIConstants.ZERO_VALUE] = AsrimCombo
						.getId().getCmbDtlId();
				attachIdList[idcount][ASRIConstants.ONE_VALUE] = AsrimCombo
						.getCmbDtlName();
				attachIdList[idcount][2] = AsrimCombo.getCmbDtlVal();
				attachIdList[idcount++][3] = EhfAttachType.getDocName();
			}
			licnt = ASRIConstants.ZERO_VALUE;
			boolean checkList = ASRIConstants.BOOLEAN_FALSE;
			String attId = ASRIConstants.EMPTY_STRING;
			int attListCount = ASRIConstants.ZERO_VALUE;
			query = new StringBuffer();
			query.append("  from EhfCaseDocAttch c , AsrimCombo ac , EhfAttachType aaut, AsrimAttachDisplay aad  where ");
			query.append(" aad.id.viewType = '"+viewType+"' and aad.id.userRole = '"+userRole+"' and c.caseId = '"
					+ caseId
					+ "' and c.attachType = ac.id.cmbDtlId   and c.attachType = aaut.id.docType and c.attachType = aad.id.attachType");
			query.append(" and aaut.id.updType = '"
					+ updType
					+ "'  and aaut.activeYN ='Y' and aad.isVisibleYN='Y'   order by aaut.docName , ac.cmbOrder  ");
			Query empAttachList = session.createQuery(query.toString());
			Iterator empAttachListItr = empAttachList.iterate();
			lTempList = new ArrayList<Object>();
			while (empAttachListItr.hasNext()) {
				checkList = ASRIConstants.BOOLEAN_TRUE;

				Object[] pair = (Object[]) empAttachListItr.next();
				EhfCaseDocAttch EhfCaseDocAttch = (EhfCaseDocAttch) pair[0];
				AsrimCombo AsrimCombo = (AsrimCombo) pair[1];
				EhfAttachType EhfAttachType = (EhfAttachType) pair[2];
				EhfAttachDisplay asrimAttachDisplay = (EhfAttachDisplay) pair[3];
				if (!(attId.equalsIgnoreCase(AsrimCombo.getId().getCmbDtlId()))) {
					if ((ASRIConstants.EMPTY_STRING).equalsIgnoreCase(attId)) {
						attId = AsrimCombo.getId().getCmbDtlId();
						tempMap.put("attach_id", AsrimCombo.getId()
								.getCmbDtlId());
						tempMap.put("heading", AsrimCombo.getCmbDtlName());
						tempMap.put("doctype", AsrimCombo.getCmbDtlVal());
						tempMap.put("attachDispType",
								EhfAttachType.getDocName());
					} else {
						tempMap.put("attachlist", lTempList);
						lResList.add(tempMap);
						lTempList = null;
						lTempList = new ArrayList<Object>();
						attId = AsrimCombo.getId().getCmbDtlId();
						tempMap = null;
						tempMap = new HashMap<String, Object>();
						tempMap.put("attach_id", AsrimCombo.getId()
								.getCmbDtlId());
						tempMap.put("heading", AsrimCombo.getCmbDtlName());
						tempMap.put("doctype", AsrimCombo.getCmbDtlVal());
						tempMap.put("attachDispType",
								EhfAttachType.getDocName());

					}
					while (!attId
							.equalsIgnoreCase(attachIdList[attListCount++][ASRIConstants.ZERO_VALUE])) {
						Map<String, String> missedIdMap = new HashMap<String, String>();
						missedIdMap
								.put("attach_id",
										attachIdList[attListCount - 1][ASRIConstants.ZERO_VALUE]);
						missedIdMap
								.put("heading",
										attachIdList[attListCount - 1][ASRIConstants.ONE_VALUE]);
						missedIdMap.put("doctype",
								attachIdList[attListCount - 1][2]);
						missedIdMap.put("attachDispType",
								attachIdList[attListCount - 1][3]);
						lResList.add(missedIdMap);
					}
				}
				AttachmentVO attachmentVO = new AttachmentVO();
				attachmentVO.setFileName(EhfCaseDocAttch.getActualName());
				attachmentVO.setSavedName(EhfCaseDocAttch.getSavedName());
				attachmentVO.setFileCrtTime(EhfCaseDocAttch.getCrtDt()
						.toString());
				attachmentVO.setAttachDispType(EhfCaseDocAttch.getActualName());
				lTempList.add(attachmentVO);
				/*
				 * if(empAttachListItr.hasNext()) {
				 * checkList=ASRIConstants.BOOLEAN_TRUE; } else {
				 * checkList=ASRIConstants.BOOLEAN_FALSE;
				 * 
				 * }
				 */
			}
			if (lTempList != null && lTempList.size() > 0) {
				tempMap.put("attachlist", lTempList);
				lResList.add(tempMap);
			}
			// lResList.add(tempMap);
			while (attListCount != idcount) {
				Map<String, String> missedIdMap = new HashMap<String, String>();
				missedIdMap.put("attach_id",
						attachIdList[attListCount][ASRIConstants.ZERO_VALUE]);
				missedIdMap.put("heading",
						attachIdList[attListCount][ASRIConstants.ONE_VALUE]);
				missedIdMap.put("doctype", attachIdList[attListCount][2]);
				missedIdMap.put("attachDispType",
						attachIdList[attListCount++][3]);

				lResList.add(missedIdMap);
			}
			lResMap.put("AttachmentList", lResList);
/**
 * get dbFiles
 * 
 * 
 */
		//	query = new StringBuffer();
			
          //  lResMap.put("DBFilesLst",(List<Object>)getDBFiles(caseId));
          //  lResMap.put("PhaseDateMap",(Map<String,Object>)getPhaseDate(caseId,null,lCon));//002
          // lResMap.put("case_status",case_status);
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
			factory.close();
		}
		return lResMap;
	
	}
	
	private String getReqStatForDocumentPending(String pStrCaseId)
	{
		StringBuffer query = new StringBuffer();
        String prevStat = null;
        SessionFactory factory = hibernateTemplate.getSessionFactory();
    	Session session = factory.openSession();
    	try{
        query.append(" select a.actId from  AsritAudit a where a.id.actOrder=(select max(b.id.actOrder)-1  from AsritAudit b where b.id.caseId = '"+pStrCaseId+"') and a.id.caseId = '"+pStrCaseId+"' ");
        Query actList=session.createQuery(query.toString());
        Iterator actListItr=actList.iterate();
        while(actListItr.hasNext())
        {
        	Object str = actListItr.next();
        	if(str != null)
            prevStat = str.toString();
        }
    	}
    	catch(Exception e)
    	{
    	e.printStackTrace();	
    	}
    	finally
    	{
    		session.close();
			factory.close();
    	}
    return prevStat;
	}
	public Map getPreauthViewAttachmentsHql(HashMap<String, Object> lparamMap) {
	SessionFactory factory = hibernateTemplate.getSessionFactory();
	Session session = factory.openSession();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 Map<String,Object> lResMap=new HashMap<String,Object>();
		 /**
		  * get case tab view attachments which are added
		  */
	 List<AttachmentVO>	lstNames = new ArrayList<AttachmentVO>();
	 List<AttachmentVO>	mainList = new ArrayList<AttachmentVO>();
	 List<AttachmentVO>	subList = new ArrayList<AttachmentVO>();
	 List<AttachmentVO> getAllLstNames = new ArrayList<AttachmentVO>();
	 List<LabelBean> rolesList = (List<LabelBean>) lparamMap.get("userRole");
	 StringBuffer query = new StringBuffer();
	 String prevAttachId = null;
	 String caseStatus = null;
	 String chronicStatus=null;
	 String casepatientNo = null;
	 String userSpecRole = (String)lparamMap.get("userSpecificRole");
	 String preauthFlag =(String)lparamMap.get("preauthFlag");
	 String attachType = (String)lparamMap.get("UpdType");
	 String chronicId=(String)lparamMap.get("chronicId");
	 String chronicNo=(String)lparamMap.get("chronicNo");
	 String chronicStatusId =(String)lparamMap.get("chronicStatus");
	 String caseApprovalFlag = (String)lparamMap.get("caseApprovalFlag");
	 String newBornBaby = (String)lparamMap.get("newBornBaby");
		try{
			if(attachType!=null && (attachType.equalsIgnoreCase("ehfFollowUp")||
									attachType.equalsIgnoreCase(config.getString("ehfCoch"))))
			{
            	
            	EhfCaseFollowupClaim ehfCaseFollowUpClaim =  genericDao.findById(EhfCaseFollowupClaim.class,String.class,(String)lparamMap.get("caseId"));
            	if(ehfCaseFollowUpClaim!=null)
            	caseStatus=ehfCaseFollowUpClaim.getFollowUpStatus();
            }
			else{  
			 query.append(" select a.caseStatus , a.casePatientNo from EhfCase a  where a.caseId='"+(String)lparamMap.get("caseId")+"'");
	            Query caseStatusq=session.createQuery(query.toString());
				Iterator caseStatusItr=caseStatusq.iterate();
	            while(caseStatusItr.hasNext())
	            {
	            	Object[] str = (Object[])caseStatusItr.next();
	            	caseStatus = str[0].toString();
	            	casepatientNo = str[1].toString();
	            }
			}
	            if(chronicStatusId !=null && !"".equalsIgnoreCase(chronicStatusId) && !"null".equalsIgnoreCase(chronicStatusId) )
	            {
	            	chronicStatus = chronicStatusId;
	            }
	            
	       /* query =  new StringBuffer();
			query.append(" select ac.id.cmbDtlId as cmbDtlID , ac.cmbDtlName as heading, ac.cmbDtlVal as docType, c.crtDt   as crtDt,");
			query.append(" ac.cmbParntDtlId as cmbprntId, c.actualName as actualName , c.savedName as savedName  ");
			query.append(" from EhfCaseDocAttch c, AsrimCombo ac, EhfAttachType aaut ");
			query.append(" where c.attachType=aaut.id.docType and ac.id.cmbDtlId=aaut.id.docType  and aaut.activeYN='Y' ");
			query.append(" and aad.id.viewType = '"+(String)lparamMap.get("viewType")+"' ");
			query.append(" and aad.id.userRole in ( ");
			 for(int j=0 ; j<rolesList.size () ; j++)
			 {
				 if(j != 0 && j!=rolesList.size ())
		        	{
		        		query.append(" , ");  	
		        	}
				 query.append(" '"+rolesList.get(j).getID()+"' ");  
			 }
	        	query.append(" ) ");   
			query.append(" and c.caseId = '"+(String)lparamMap.get("caseId")+"' ");
	        //query.append(" and c.caseId = '13' ");
			query.append(" and aaut.id.updType = '"+(String)lparamMap.get("UpdType")+"' and c.activeYN='Y' ");
			//query.append(" and ac.attachDocType is not null and ac.attachUpdStatus is not  null ");
			//query.append("order by ac.attachUpdStatus , ac.attachDocType , ac.id.cmbDtlId ");	
			lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
			getAllLstNames.addAll(lstNames);*/
			/**
			 * get patient already attached test list
			 */
			if(preauthFlag == null || !preauthFlag.equalsIgnoreCase("Y"))
			{
			query = new StringBuffer();
			lstNames = new ArrayList<AttachmentVO>();
			query.append(" select distinct ac.cmbDtlId as cmbDtlID , ac.cmbDtlName as heading, ac.cmbAttrVal as docType, apd.crtDt as crtDt , ");
			query.append(" ac.cmbParntDtlId as cmbprntId, apd.actualName as actualName, apd.savedName as savedName, aad.isVisibleYN as attachVisibility ");
			query.append(" , cast(apd.docSeqId as string) as attachDocSeqId ");
			if(attachType!=null && (attachType.equalsIgnoreCase("ehfFollowUp")||
									attachType.equalsIgnoreCase(config.getString("ehfCoch"))))
				{
					query.append(" ,apd.followUpId as followUpId");
					query.append(" ,substring(apd.followUpId,-1,1) as installment  ");
				}
			
			if(attachType!=null && attachType.equalsIgnoreCase("chronicAttach"))
				query.append(" ,substring(apd.chronicNo,-1,1) as installment  ");
			query.append(" from EhfmCmbDtls ac , EhfAttachDisplay  aad , EhfAttachType aau ");
			if(attachType!=null && attachType.equalsIgnoreCase("ehfFollowUp"))
				{
					String caseIdFol=(String)lparamMap.get("caseId"); 
					if(caseIdFol.endsWith("/1"))
						query.append(" , EhfFollowupDocAttch apd where apd.followUpId = '"+(String)lparamMap.get("caseId")+"' ");
					if(caseIdFol.endsWith("/2"))
						{
							caseIdFol=caseIdFol.substring(0,(caseIdFol.length()-2));
							query.append(" , EhfFollowupDocAttch apd where apd.followUpId in  ('"+caseIdFol+"/1','"+caseIdFol+"/2') ");
						}
					if(caseIdFol.endsWith("/3"))
						{
							caseIdFol=caseIdFol.substring(0,(caseIdFol.length()-2));
							query.append(" , EhfFollowupDocAttch apd where apd.followUpId in  ('"+caseIdFol+"/1','"+caseIdFol+"/2','"+caseIdFol+"/3') ");
						}
					if(caseIdFol.endsWith("/4"))
						{
							caseIdFol=caseIdFol.substring(0,(caseIdFol.length()-2));
							query.append(" , EhfFollowupDocAttch apd where apd.followUpId in  ('"+caseIdFol+"/1','"+caseIdFol+"/2','"+caseIdFol+"/3','"+caseIdFol+"/4') ");
						}
				}
			else if(attachType!=null && attachType.equalsIgnoreCase(config.getString("ehfCoch")))
				{
					String caseIdCochFol=(String)lparamMap.get("caseId"),followUpIds=null; 
					int length=0;
					
					if(caseIdCochFol.endsWith("CF"))
						followUpIds="'"+caseIdCochFol+"'";
					else if(caseIdCochFol.endsWith("CF1"))
						length=1;
					else if(caseIdCochFol.endsWith("CF2"))
						length=2;
					else if(caseIdCochFol.endsWith("CF3"))
						length=3;
					else if(caseIdCochFol.endsWith("CF4"))
						length=4;
					if(length>0)
						{
							caseIdCochFol=caseIdCochFol.substring(0,(caseIdCochFol.length()-3));
							followUpIds="'"+caseIdCochFol+"CF'";
							for(int i=1;i<=length;i++)
								{
									followUpIds=followUpIds+",'"+caseIdCochFol+"CF"+i+"'";
								}
						}
					query.append(" , EhfFollowupDocAttch apd where apd.followUpId in  ("+followUpIds+") ");
				}
			else if(attachType!=null && attachType.equalsIgnoreCase("chronicAttach"))
				query.append(" , EhfChronicDocAttch apd where apd.chronicId = '"+(String)lparamMap.get("chronicId")+"'  ");
			else
				query.append(" , EhfCaseDocAttch apd where apd.caseId = '"+(String)lparamMap.get("caseId")+"' ");
			query.append(" and ac.cmbDtlId = apd.attachType and aad.id.attachType =apd.attachType ");
			query.append(" and apd.activeYN ='Y' and aad.id.userRole in (" );
					 for(int j=0 ; j<rolesList.size () ;j++)
					 {
						 if(j != 0 && j!=rolesList.size ())
				        	{
				        		query.append(" , ");  	
				        	}
						 query.append(" '"+rolesList.get(j).getID()+"' ");  
					 }
					
					 query.append(" ) ");   
			query.append(" and aau.id.updType ='"+(String)lparamMap.get("UpdType")+"' and aau.id.docType = ac.cmbDtlId and aau.activeYN='Y' ");
            if(newBornBaby!=null)
            	{
	            	if(!newBornBaby.equalsIgnoreCase("Y"))
	            		{
	            			if(query!=null)
	            				{
	            					if(query.length()>15)
	            						{
	            							if(query.toString().contains("EhfmCmbDtls ac"))
	            								query.append(" and ac.cmbDtlId not in ('"+config.getString("EHF.Claims.newBabyIDStat")+"')");
	            						}
	            				}	
	            		}	
            	}
            else if(query!=null)
				{
					if(query.length()>15)
						{
							if(query.toString().contains("EhfmCmbDtls ac"))
								query.append(" and ac.cmbDtlId not in ('"+config.getString("EHF.Claims.newBabyIDStat")+"')");
						}
				}	
			query.append(" order by ac.cmbDtlName ");
			lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
			if(lstNames != null && lstNames.size() >0 )
			{
				getAllLstNames.addAll(lstNames);
			}
			if( attachType!= null && attachType.equalsIgnoreCase(config.getString("preauth_main_attachType")))
			{
			lstNames = new ArrayList<AttachmentVO>();
			/**
			 * query changed according to new EhfPatientTests table...
			 */
			query = new StringBuffer();
			/**
			 * enable patient test visibility
			 */
			String visibility = "N";
			for(LabelBean labelBean :rolesList )
			{
			if( config.getString("preauth_patient_tests_Visibility").contains(","+labelBean.getID()+","))
			{
				visibility = "Y";	
				break;
			}
			if( chronicId!=null && !"".equalsIgnoreCase(chronicId)&&config.getString("chronic_patient_tests_Visibility").contains(","+labelBean.getID()+","))
			{
				visibility = "Y";	
				break;
			}
			}
			if(attachType!=null && !attachType.equalsIgnoreCase("ehfFollowUp")){
			query.append(" select apt.patientId as cmbDtlID, 'Patient Tests' as heading, ats.invName as docType, ats.invCode as cmbprntId , ");
			query.append(" ats.invName as actualName, apt.attachTotalPath as savedName ,'"+visibility+"' as attachVisibility  ,  apt.crtDt as crtDt ");
			query.append(" from EhfPatientTests apt, EhfmGenInvestigationsMst ats   ");
			query.append(" where apt.patientId ='"+casepatientNo+"' and ats.invCode=apt.testId  ");
			lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
			if(lstNames != null && lstNames.size() >0 )
			{
				getAllLstNames.addAll(lstNames);	
			}
			}						
			}
			
			
			if( attachType!= null && attachType.equalsIgnoreCase(config.getString("chronic_attachType")))
			{
			lstNames = new ArrayList<AttachmentVO>();
			/**
			 * query changed according to new EhfPatientTests table...
			 */
			query = new StringBuffer();
			/**
			 * enable patient test visibility
			 */
			String visibility = "N";
			for(LabelBean labelBean :rolesList )
			{
			
			if( chronicId!=null && !"".equalsIgnoreCase(chronicId)&&config.getString("chronic_patient_tests_Visibility").contains(","+labelBean.getID()+","))
			{
				visibility = "Y";	
				break;
			}
			}
			if(attachType!=null && attachType.equalsIgnoreCase("chronicAttach")){
			query.append(" select apt.chronicId as cmbDtlID, 'Patient Tests' as heading, ats.invName as docType, ats.invCode as cmbprntId , ");
			query.append(" ats.invName as actualName, apt.attachPath as savedName ,'"+visibility+"' as attachVisibility  ,  apt.crtDt as crtDt ");
			query.append(" from EhfChronicPatientTest apt, EhfmGenInvestigationsMst ats   ");
			query.append(" where apt.chronicId ='"+chronicId+"' and apt.chronicNo='"+chronicNo+"' and ats.invCode=apt.testId  ");
			lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
			
			
			
			if(lstNames != null && lstNames.size() >0 )
			{
				getAllLstNames.addAll(lstNames);	
			}
			}						
			}
			
			
			
			
			
			
			
			}
			if(getAllLstNames != null && getAllLstNames.size() > 0)
			{
				prevAttachId = getAllLstNames.get(0).getCmbDtlID();
			}
			int i=0;
			for(AttachmentVO attachmentVO:getAllLstNames)
			{
				AttachmentVO attachmentVO1 = null;
				AttachmentVO subattachmentVO = null ;
				if(prevAttachId.equalsIgnoreCase(attachmentVO.getCmbDtlID()))
				{
					subattachmentVO = new AttachmentVO();
					subattachmentVO.setFileName(attachmentVO.getActualName());
					subattachmentVO.setSavedName(attachmentVO.getSavedName());
					subattachmentVO.setFileCrtTime( sdf.format(attachmentVO.getCrtDt()) );
					subattachmentVO.setAttachVisibility(attachmentVO.getAttachVisibility());
					subattachmentVO.setAttachMode(attachmentVO.getAttachMode());
					subattachmentVO.setDocType(attachmentVO.getDocType());
					subattachmentVO.setAttachDocSeqId(attachmentVO.getAttachDocSeqId());
					subattachmentVO.setInstallment(attachmentVO.getInstallment());
					subList.add(subattachmentVO);	
					if(!prevAttachId.equalsIgnoreCase(attachmentVO.getCmbDtlID())|| i==0 )
					{
						attachmentVO1 =  new AttachmentVO();
						attachmentVO1.setHeading(attachmentVO.getHeading());	
						attachmentVO1.setDocType(attachmentVO.getDocType());
						attachmentVO1.setLstAttachments(subList);
						mainList.add(attachmentVO1);
					}
					i++;
				}
				else
				{
					i=0;
					attachmentVO1 = new AttachmentVO();
					subList = new ArrayList<AttachmentVO>();
					subattachmentVO = new AttachmentVO();
					subattachmentVO.setFileName(attachmentVO.getActualName());
					subattachmentVO.setSavedName(attachmentVO.getSavedName());
					subattachmentVO.setFileCrtTime(sdf.format(attachmentVO.getCrtDt()));
					subattachmentVO.setAttachVisibility(attachmentVO.getAttachVisibility());
					subattachmentVO.setAttachMode(attachmentVO.getAttachMode());
					subattachmentVO.setDocType(attachmentVO.getDocType());
					subattachmentVO.setAttachDocSeqId(attachmentVO.getAttachDocSeqId());
					attachmentVO1.setHeading(attachmentVO.getHeading());
					attachmentVO1.setDocType(attachmentVO.getDocType());
					subattachmentVO.setInstallment(attachmentVO.getInstallment());
					subList.add(subattachmentVO);	
					attachmentVO1.setLstAttachments(subList);
					mainList.add(attachmentVO1);
					i++;
				}
				prevAttachId = attachmentVO.getCmbDtlID();
				
			}
			/**
			 * end of getting view attachments
			 */
			/**
			 * get attachments for adding
			 */
			if(caseApprovalFlag != null && caseApprovalFlag.equalsIgnoreCase("Y"))
			{
	            query =  new StringBuffer();
	            query.append(" select distinct ac.cmbDtlId as cmbDtlID , ac.cmbDtlName as heading, ac.cmbAttrVal as docType ,aau.docName as attachType");
	            query.append(" FROM  EhfCaseStatusAttach ca, EhfmCmbDtls ac, EhfAttachType aau ");
	            query.append(" where ca.id.attachType = ac.id.cmbDtlId and ca.id.userRole in ( ");
	            for(int j=0 ; j<rolesList.size () ;j++)
				 {
					 if(j != 0 && j!=rolesList.size ())
			        	{
			        		query.append(" , ");  	
			        	}
					 query.append(" '"+rolesList.get(j).getID()+"' ");  
				 }
	        	query.append(" ) ");  
	        	if( chronicId!=null && !"".equalsIgnoreCase(chronicId))
	        	{
	        		caseStatus=chronicStatus;
	        	}
	        	
	            query.append("	 AND ca.id.caseStatus ='"+caseStatus+"' and  ");
	            
	            
	        	
	            query.append(" aau.id.updType ='"+(String)lparamMap.get("UpdType")+"' and aau.id.docType = ac.cmbDtlId and aau.activeYN='Y' ");
	            if(newBornBaby!=null)
	            	{
	            		if(!newBornBaby.equalsIgnoreCase("Y"))
	            			query.append(" and ac.cmbDtlId not in ('"+config.getString("EHF.Claims.newBabyIDStat")+"')");
	            	}
	            else if(newBornBaby==null)
					{
	            		query.append(" and ac.cmbDtlId not in ('"+config.getString("EHF.Claims.newBabyIDStat")+"')");
					}
	            query.append(" order by ac.cmbDtlName , aau.docName ");
	            lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
	            
	            
				 
				 //System.out.println("chronic attachment size->"+lstNames.size());
	            lResMap.put("addAttachmentList",lstNames);   
			}
			/**
			 * end 
			 */
			/**
			 * get all db files list
			 */
	            /*query =  new StringBuffer();
	            lstNames = new ArrayList<AttachmentVO>();
	            query.append(" select  acm.cmbDtlName as docType  , acd.actualName as  fileName  ");
	            query.append(" from EhfCaseDocAttch acd, AsrimCombo acm where acd.attachType = acm.id.cmbDtlId and acd.caseId = '"+(String)lparamMap.get("caseId")+"' ");
	            lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
	            getAllLstNames = new ArrayList<AttachmentVO>();
	            getAllLstNames.addAll(lstNames);
	            query =  new StringBuffer();
	            lstNames = new ArrayList<AttachmentVO>();
	            query.append(" select 'On Bed Photo' as docType , appd.actualFileName as  fileName  ");
	            query.append(" from AsritPatientPhotoDoc appd, EhfCase ac  ");
	            query.append(" where appd.patientNo = ac.casePatientNo and ac.caseId = '"+(String)lparamMap.get("caseId")+"'  ");
	            lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
	            getAllLstNames.addAll(lstNames);*/
			
			
			
			/**
			 * for getting signed application form attachment count
			 */
			if(attachType!=null && !attachType.equalsIgnoreCase("ehfFollowUp")){
			 List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			 String rej_cnt="0";
			 query= new StringBuffer();
			    query.append("  select count(ea.actId) as COUNT  from EhfAudit ea  ");
				query.append("   where ea.id.caseId ='"+(String)lparamMap.get("caseId")+"'  and ea.actId in(  '"+config.getString("preauth_partail_save")+"') ");
				List<LabelBean> lstCnt = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
				if(lstCnt != null && lstCnt.size() >0)
				{
				if(lstCnt.get(0).getCOUNT() != null && !lstCnt.get(0).getCOUNT().equals(""))
				{
					rej_cnt = 	 lstCnt.get(0).getCOUNT().toString();
				}
				}
          
				   int cnt = Integer.parseInt(rej_cnt)-1;
				   criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				   criteriaList.add(new GenericDAOQueryCriteria("caseId",(String)lparamMap.get("caseId"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				   criteriaList.add(new GenericDAOQueryCriteria("docCount","~"+cnt,GenericDAOQueryCriteria.CriteriaOperator.LIKE_ENDS_WITH));
				   criteriaList.add(new GenericDAOQueryCriteria("attachType",config.getString("Signedprf_docs"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
				   List<EhfCaseDocAttch> LstAsritCaseDocAttch=genericDao.findAllByCriteria(EhfCaseDocAttch.class, criteriaList);
			       lResMap.put("SignPRFFormList", LstAsritCaseDocAttch);
			}
			/**
			 * 
			 */
			
	            lResMap.put("DBFilesLst", getAllLstNames);   
	            
			lResMap.put("AttachmentList", mainList);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally {
			session.close();
		}
		  return lResMap;
	}
	
	@Override
	public Map getAhcAttachmentsHql(HashMap<String, Object> lparamMap) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 Map<String,Object> lResMap=new HashMap<String,Object>();
			 /**
			  * get case tab view attachments which are added
			  */
		 List<AttachmentVO>	lstNames = new ArrayList<AttachmentVO>();
		 List<AttachmentVO>	mainList = new ArrayList<AttachmentVO>();
		 List<AttachmentVO>	subList = new ArrayList<AttachmentVO>();
		 List<AttachmentVO> getAllLstNames = new ArrayList<AttachmentVO>();
		 List<LabelBean> rolesList = (List<LabelBean>) lparamMap.get("userRole");
		 StringBuffer query = new StringBuffer();
		 String prevAttachId = null;
		 String caseStatus = null;
		
		
		 String attachType = (String)lparamMap.get("UpdType");
		
			try{
				
				
				query = new StringBuffer();
				lstNames = new ArrayList<AttachmentVO>();
				query.append(" select distinct ac.cmbDtlId as cmbDtlID , ac.cmbDtlName as heading, ac.cmbAttrVal as docType, apd.crtDt as crtDt , ");
				//query.append(" ac.cmbParntDtlId as cmbprntId, apd.actualName as actualName, apd.savedName as savedName, aad.isVisibleYN as attachVisibility ");
				query.append(" ac.cmbParntDtlId as cmbprntId, apd.actualName as actualName, apd.savedName as savedName ");
				query.append(" , cast(apd.docSeqId as string) as attachDocSeqId ");
				//query.append(" from EhfmCmbDtls ac , EhfAttachDisplay  aad , EhfAttachType aau ");
				query.append(" from EhfmCmbDtls ac , EhfAttachType aau ");
				query.append(" , EhfAhcDocAttch apd where apd.ahcId = '"+(String)lparamMap.get("ahcId")+"' ");
				//query.append(" and ac.cmbDtlId = apd.attachType and aad.id.attachType =apd.attachType ");
				query.append(" and ac.cmbDtlId = apd.attachType  ");
				query.append(" and apd.activeYN ='Y' " );
				/*query.append(" and apd.activeYN ='Y' and aad.id.userRole in (" );
				
						 for(int j=0 ; j<rolesList.size () ;j++)
						 {
							 if(j != 0 && j!=rolesList.size ())
					        	{
					        		query.append(" , ");  	
					        	}
							 query.append(" '"+rolesList.get(j).getID()+"' ");  
						 }
						
						 query.append(" ) ");   */
				query.append(" and aau.id.updType ='"+(String)lparamMap.get("UpdType")+"' and aau.id.docType = ac.cmbDtlId and aau.activeYN='Y' ");
				query.append(" order by ac.cmbDtlName ");
				lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
				if(lstNames != null && lstNames.size() >0 )
				{
					getAllLstNames.addAll(lstNames);
				}
				/**
				 * enable patient test visibility
				 *//*
				String visibility = "N";
				for(LabelBean labelBean :rolesList )
				{
				if( config.getString("preauth_patient_tests_Visibility").contains(","+labelBean.getID()+","))
				{
					visibility = "Y";	
					break;
				}
				}
				}*/
				
				if(getAllLstNames != null && getAllLstNames.size() > 0)
				{
					prevAttachId = getAllLstNames.get(0).getCmbDtlID();
				}
				int i=0;
				for(AttachmentVO attachmentVO:getAllLstNames)
				{
					AttachmentVO attachmentVO1 = null;
					AttachmentVO subattachmentVO = null ;
					if(prevAttachId.equalsIgnoreCase(attachmentVO.getCmbDtlID()))
					{
						subattachmentVO = new AttachmentVO();
						subattachmentVO.setFileName(attachmentVO.getActualName());
						subattachmentVO.setSavedName(attachmentVO.getSavedName());
						subattachmentVO.setFileCrtTime( sdf.format(attachmentVO.getCrtDt()) );
						//subattachmentVO.setAttachVisibility(attachmentVO.getAttachVisibility());
						subattachmentVO.setAttachVisibility("Y");
						subattachmentVO.setAttachMode(attachmentVO.getAttachMode());
						subattachmentVO.setDocType(attachmentVO.getDocType());
						subattachmentVO.setAttachDocSeqId(attachmentVO.getAttachDocSeqId());
						subList.add(subattachmentVO);	
						if(!prevAttachId.equalsIgnoreCase(attachmentVO.getCmbDtlID())|| i==0 )
						{
							attachmentVO1 =  new AttachmentVO();
							attachmentVO1.setHeading(attachmentVO.getHeading());	
							attachmentVO1.setDocType(attachmentVO.getDocType());
							attachmentVO1.setLstAttachments(subList);
							mainList.add(attachmentVO1);
						}
						i++;
					}
					else
					{
						i=0;
						attachmentVO1 = new AttachmentVO();
						subList = new ArrayList<AttachmentVO>();
						subattachmentVO = new AttachmentVO();
						subattachmentVO.setFileName(attachmentVO.getActualName());
						subattachmentVO.setSavedName(attachmentVO.getSavedName());
						subattachmentVO.setFileCrtTime(sdf.format(attachmentVO.getCrtDt()));
						//subattachmentVO.setAttachVisibility(attachmentVO.getAttachVisibility());
						subattachmentVO.setAttachVisibility("Y");
						subattachmentVO.setAttachMode(attachmentVO.getAttachMode());
						subattachmentVO.setDocType(attachmentVO.getDocType());
						subattachmentVO.setAttachDocSeqId(attachmentVO.getAttachDocSeqId());
						attachmentVO1.setHeading(attachmentVO.getHeading());
						attachmentVO1.setDocType(attachmentVO.getDocType());
						subList.add(subattachmentVO);	
						attachmentVO1.setLstAttachments(subList);
						mainList.add(attachmentVO1);
						i++;
					}
					prevAttachId = attachmentVO.getCmbDtlID();
					
				}
				
		            query =  new StringBuffer();
		            query.append(" select distinct ac.cmbDtlId as cmbDtlID , ac.cmbDtlName as heading, ac.cmbAttrVal as docType ,aau.docName as attachType");
		            query.append(" FROM  EhfAttachDisplay ca, EhfmCmbDtls ac, EhfAttachType aau ");
		           // query.append(" FROM  EhfmCmbDtls ac, EhfAttachType aau ");
		            query.append(" where ca.id.attachType = ac.id.cmbDtlId ");
		            query.append("and ca.id.userRole in ( ");
		            for(int j=0 ; j<rolesList.size () ;j++)
					 {
						 if(j != 0 && j!=rolesList.size ())
				        	{
				        		query.append(" , ");  	
				        	}
						 query.append(" '"+rolesList.get(j).getID()+"' ");  
					 }
		        	query.append(" ) ");   
		         
		            query.append("and ac.cmbDtlId not in (select aad.attachType from EhfAhcDocAttch aad where aad.activeYN='Y' and aad.ahcId = '"+(String)lparamMap.get("ahcId")+"')  and aau.id.updType ='"+(String)lparamMap.get("UpdType")+"' and aau.id.docType = ac.cmbDtlId and aau.activeYN='Y' ");
		            query.append(" order by ac.cmbDtlName ");
		            lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
		            lResMap.put("addAttachmentList",lstNames);   
				
								
		            lResMap.put("DBFilesLst", getAllLstNames);   
		            
				lResMap.put("AttachmentList", mainList);
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}
			
			  return lResMap;
		}
	
	
	public Map getPayResponseAttachment(HashMap<String, Object> hParamMap) {
		
		Map<String,Object> lResMap=new HashMap<String,Object>();
		StringBuffer query = new StringBuffer();
		List<AttachmentVO>	lstNames = new ArrayList<AttachmentVO>();
		String lStrToDate = "";
		String upType = (String) hParamMap.get("UpdType");
		String fromDatePay = (String) hParamMap.get("fromDatePay");
		String toDatePay = (String) hParamMap.get("toDatePay");
		Date today = Calendar.getInstance().getTime(); 
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (fromDatePay == null || fromDatePay.equalsIgnoreCase("")) {
			fromDatePay = df.format(today);
		}
		

		if (toDatePay == null || toDatePay.equalsIgnoreCase("")) {
			toDatePay = df.format(today);
		}
		
			//To include todate in search criteria--adding date+1 
			Calendar cal = Calendar.getInstance();  
        	try {
			cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(toDatePay));
        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
        	Date tomorrow = cal.getTime();  
        	lStrToDate = new SimpleDateFormat("dd/MM/yyyy").format(tomorrow);
        	 //End of date+1 
        	
			query.append(" select a.fileName as fileName,a.filePath as filePath from EhfClaimUploadFile a ");
			query.append(" where a.crtDate between  TO_DATE('"+fromDatePay+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
			if(upType!=null && upType.equalsIgnoreCase("ehfClaim"))
				query.append(" and (a.fileName like 'EHSN%' or a.fileName like 'EHSTDS%') "); //
			if(upType!=null && upType.equalsIgnoreCase("ehfTDS"))
				query.append(" and a.fileName like '%TDS%'");
			if(upType!=null && upType.equalsIgnoreCase("ehfFollowUp"))
				query.append(" and a.fileName like 'EHSF%'");// and a.fileName NOT LIKE '%TDS%'");
			if(upType!=null && upType.equalsIgnoreCase("ehfErr"))
				query.append(" and a.fileName like 'EHSE%'");// and a.fileName NOT LIKE '%TDS%'");
			if(upType!=null && upType.equalsIgnoreCase("ehfChronic"))
				query.append(" and a.fileName like 'EHSC%'");
			query.append(" order by a.crtDate asc "); 
			lstNames= genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
            lResMap.put("lStrAttachmentList",lstNames);  
            lResMap.put("fromDatePay", fromDatePay);
            lResMap.put("toDatePay", toDatePay);
            lResMap.put("UpdType", upType);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	
	
	return lResMap;
    }
	
	/*
	 * Added by Srikalyan for getting Model Class
	 * Object by using Class Name and Id as Parameters 
	 */
	public Map idMethod(String className,String id)
		{
			Map<String,Object> map=new HashMap<String,Object>();
			if(className != null && !"".equalsIgnoreCase(className))
				{
					try 
						{
							Object object =Class.forName(className).newInstance();
							object=genericDao.findById(object.getClass(), String.class, id);
							map.put(className,object);
						}
					catch (Exception e) 
						{
							e.printStackTrace();
							
						}
				}
			return map;
			
		}
	
}
