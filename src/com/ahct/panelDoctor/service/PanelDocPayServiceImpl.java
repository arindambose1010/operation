/**
 * PACKAGE          : com.tcs.accounts.service
 * FILENAME         : PanelDocPayServiceImpl.java
 * FUNCTIONALITY    :
 * @VERSION         : 1
 * @AUTHOR          : Smita Choudhury
 * DATE             : 23/12/2013
 * --------------------------------------------------------------------------------------------------
 *   ChangeId         Date                 Author            Change Desciption
 * --------------------------------------------------------------------------------------------------
 *   
 * -------------------------------------------------------------------------------------------------
 */

package com.ahct.panelDoctor.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import com.ahct.panelDoctor.dao.PanelDocPayDAO;
import com.ahct.panelDoctor.dao.PaymentsDAO;
import com.ahct.panelDoctor.util.PanelDocConstants;
import com.ahct.panelDoctor.vo.PanelDocPayVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfPanelDocAuditDtls;
import com.ahct.model.EhfPanelDocAuditDtlsId;
import com.ahct.model.EhfPanelDocCaseDtls;
import com.ahct.model.EhfPnlDocWrkFlowId;
import com.ahct.model.EhfPnlDocWrkflow;
import com.ahct.model.EhfPnldocTdsPayment;
import com.ahct.model.EhfmBankAccountDtls;
import com.ahct.model.EhfmGrpWorkFlow;
import com.ahct.model.EhfmGrps;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfmWorkFlowGrpMpg;
import com.ahct.model.EhsCeoSendbackDtls;
import com.ahct.model.EhsCeoSendbackDtlsId;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public class PanelDocPayServiceImpl implements PanelDocPayService {
	Logger logger = Logger.getLogger(PanelDocPayServiceImpl.class);
	PanelDocPayDAO panelDocPayDao;
	PaymentsDAO paymentsDao;  
	GenericDAO genericDao;
	CommonService commonService;

	HibernateTemplate hibernateTemplate;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}

	public PanelDocPayDAO getPanelDocPayDao() {
		return panelDocPayDao;
	}

	public void setPanelDocPayDao(PanelDocPayDAO panelDocPayDao) {
		this.panelDocPayDao = panelDocPayDao;
	}
	
	public void setPaymentsDao(PaymentsDAO paymentsDao) {
		this.paymentsDao = paymentsDao;
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
	
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Override
	public List<PanelDocPayVO> getPanelDocCases(PanelDocPayVO panelDocPayVO,
			String currGrp, String prevGrp, int startIndex,int maxResults) {
		// TODO Auto-generated method stub
		List<PanelDocPayVO> panelDocCases = panelDocPayDao
				.generatepanelDocCases(panelDocPayVO, currGrp, prevGrp,startIndex,maxResults);
		return panelDocCases;
	}

	@Override
	public List<PanelDocPayVO> getPanelDocCasesForCEO(
			PanelDocPayVO panelDocPayVO,int startIndex,int maxResults) {
		List<PanelDocPayVO> panelDocCases = panelDocPayDao
				.generatepanelDocCasesForCEO(panelDocPayVO,startIndex,maxResults);
		return panelDocCases;
	}

	@Override
	public List<PanelDocPayVO> getPanelDocDtlCasesList(
			PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId) {
		// TODO Auto-generated method stub
		List<PanelDocPayVO> panelDocCasesList = panelDocPayDao
				.generatepanelDocDtlCasesList(panelDocPayVO, currGrpId,
						prevGrpId);
		return panelDocCasesList;
	}

	@Override
	public List<PanelDocPayVO> getAllSelCasesDetails(
			PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId) {
		// TODO Auto-generated method stub
		List<PanelDocPayVO> panelDocCasesDetails = panelDocPayDao
				.generateAllSelCasesDetails(panelDocPayVO, currGrpId, prevGrpId);
		return panelDocCasesDetails;
	}
	
	@Override
	public List<PanelDocPayVO> getCasesByWrkFlow(
			PanelDocPayVO panelDocPayVO,  String prevGrpId) {
		// TODO Auto-generated method stub
		List<PanelDocPayVO> panelDocCasesDetails = panelDocPayDao.generateCasesByWrkFlow(panelDocPayVO,prevGrpId);
		return panelDocCasesDetails;
	}

	@Override
	public List<PanelDocPayVO> getCaseCountStatus(PanelDocPayVO panelDocPayVO,
			String currGrpId, String prevGrpId) {
		// TODO Auto-generated method stub
		List<PanelDocPayVO> caseCountStatus = panelDocPayDao
				.generatecaseCountStatus(panelDocPayVO, currGrpId, prevGrpId);
		return caseCountStatus;

	}

	/**
	 * Update Audit table for Cases
	 * 
	 * @param cases
	 *            details to be updated
	 * @param userid
	 * @param group
	 *            details
	 * @return PanelDocPayVO
	 */

	@Override
	public PanelDocPayVO insertAuditRecord(List<PanelDocPayVO> panelDocCasesDetails, String userId,PanelDocPayVO panelDocPayVO) {
		long id = 0;
		int count=0;
		String doctor="";
		String grpName = "";
		List wrkFlwId=new ArrayList();
		List docId=new ArrayList();
		String failedCaseList = PanelDocConstants.EMPTY;
		EhfPanelDocAuditDtls ehfPanelDocAuditDtls = new EhfPanelDocAuditDtls();
		EhfPanelDocAuditDtlsId ehfPanelDocAuditDtlsId = new EhfPanelDocAuditDtlsId();
		DateFormat df = new SimpleDateFormat(config.getString("FIN.DateFormat1"));
		String actionPerformed = panelDocPayVO.getActionType();
		try {
		
		Iterator<PanelDocPayVO> itr = panelDocCasesDetails.iterator();
		PanelDocPayVO element = new PanelDocPayVO();
		
			while (itr.hasNext()) {
				element = (PanelDocPayVO) itr.next();
				if(!(docId.contains(element.getDOC_ID()))){
				 String wId=paymentsDao.getSequence("PANEL_DOC_WRKFLW_SEQ");
				 panelDocPayVO.setWRKFLWSETID(wId);
				 wrkFlwId.add(count,wId);
				 docId.add(count,element.getDOC_ID());
				 count++;
				}
				else
				{
					int index=docId.indexOf(element.getDOC_ID());
					panelDocPayVO.setWRKFLWSETID((String)wrkFlwId.get(index));
				}
				panelDocPayVO.setID(element.getID());
				panelDocPayVO.setFlag("insert");
				//to update Status in case details table
				updatePmntStatus(panelDocPayVO, userId);
			}
								
				
				
					
			
			 //Entry in WorkFlow and audit Table 
			  String wrkFlowFlg=null;
			  for(int i=0;i<wrkFlwId.size();i++){
			  id=Long.parseLong((String)wrkFlwId.get(i));
			  doctor=(String)docId.get(i);
			  panelDocPayVO.setWRKFLWSETID((String)wrkFlwId.get(i));
			  panelDocPayVO.setDOC_ID(doctor);
			  
			  ehfPanelDocAuditDtlsId.setPmtId(id);
			  ehfPanelDocAuditDtls.setDoc_id(doctor);
			  ehfPanelDocAuditDtlsId.setActOrder(Long.parseLong(config.getString("FIN.One")));
			  ehfPanelDocAuditDtls.setAssigndDt(df.parse(element.getCASE_DATE()));
			  ehfPanelDocAuditDtls.setGroupId(panelDocPayVO.getPrevOwnr());
		      ehfPanelDocAuditDtls.setActedBy(userId);
			  ehfPanelDocAuditDtls.setActDt(new Date());
			  ehfPanelDocAuditDtls.setCurrWrkflwId(panelDocPayVO.getPrevWrkflowId());
			  // ehfPanelDocAuditDtls.setRemarks(panelDocPayVO.getRemarks());
			  ehfPanelDocAuditDtls.setId(ehfPanelDocAuditDtlsId);
			  ehfPanelDocAuditDtls = genericDao.save(ehfPanelDocAuditDtls);

			   grpName = getgrpName(panelDocPayVO.getPrevOwnr());
			
			  if (actionPerformed.equals("Approve")) {
				panelDocPayVO.setResult(config.getString("FIN.ApproveMsg"));
				panelDocPayVO.setPARTICULARS(grpName + " Approved");
			  } else if (actionPerformed.equals("Reject")) {
				panelDocPayVO.setResult(config.getString("FIN.RejectMsg"));
				panelDocPayVO.setPARTICULARS(grpName + " Rejected");
			  }

			  EhfPnlDocWrkflow ehfPnlDocWrkflow=new EhfPnlDocWrkflow();
			  ehfPnlDocWrkflow=genericDao.findById(EhfPnlDocWrkflow.class, EhfPnlDocWrkFlowId.class,new EhfPnlDocWrkFlowId(id, doctor));
			  if(ehfPnlDocWrkflow != null) {
			  wrkFlowFlg=updateWrkFlowRecord(userId,panelDocPayVO); }
			  else
			  wrkFlowFlg=insertWrkFlowRecord(userId,panelDocPayVO);
			  }
		
		}

		catch (Exception e) {
//			logger.error("@------Exception has raised in PanelDocServiceImpl:insertAuditRecord() "
//					+ e.getMessage());
			e.printStackTrace();
		}
		return panelDocPayVO;
	}
	
	@Override
	public PanelDocPayVO updateApprvdRecord(List<PanelDocPayVO> panelDocCasesDetails, String userId,PanelDocPayVO panelDocPayVO) {
		String grpName = "";
		EhfPanelDocAuditDtls ehfPanelDocAuditDtls = new EhfPanelDocAuditDtls();
		EhfPanelDocAuditDtlsId ehfPanelDocAuditDtlsId = new EhfPanelDocAuditDtlsId();
		DateFormat df = new SimpleDateFormat(config.getString("FIN.DateFormat1"));
		try {
		Iterator<PanelDocPayVO> itr = panelDocCasesDetails.iterator();
		PanelDocPayVO element = new PanelDocPayVO();
			while (itr.hasNext()) {
				element = (PanelDocPayVO) itr.next();
				//panelDocPayVO.setID(element.getID());
				//String objId=element.getWRKFLWSETID();
				//String docId=element.getDOC_ID();

				String objId=element.getwId().toString();
				String docId=element.getDOC_ID();
				if(panelDocPayVO.getPrevOwnr()!=null && !"NA".equalsIgnoreCase(panelDocPayVO.getPrevOwnr()))
					grpName = getgrpName(panelDocPayVO.getPrevOwnr());
				ehfPanelDocAuditDtlsId.setPmtId(Long.parseLong(objId));
				ehfPanelDocAuditDtls.setDoc_id(docId);
				List<EhfPanelDocAuditDtls> lstEhfPanelDocAuditDtls = null;
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.pmtId", Long.parseLong(objId),
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",
						GenericDAOQueryCriteria.CriteriaOperator.DESC));
				lstEhfPanelDocAuditDtls = genericDao.findAllByCriteria(
						EhfPanelDocAuditDtls.class, criteriaList);
				criteriaList.removeAll(criteriaList);
				if (lstEhfPanelDocAuditDtls != null	&& lstEhfPanelDocAuditDtls.size() > 0) 
					{
						Long actOrder = lstEhfPanelDocAuditDtls.get(0).getId().getActOrder();
						ehfPanelDocAuditDtlsId.setActOrder(actOrder + 1);
						if(lstEhfPanelDocAuditDtls.get(0).getActDt()!=null)
							ehfPanelDocAuditDtls.setAssigndDt(lstEhfPanelDocAuditDtls.get(0).getActDt());
						else 
							ehfPanelDocAuditDtls.setAssigndDt(new Date());
					}
				else
					{
						ehfPanelDocAuditDtlsId.setActOrder(Long.parseLong(config.getString("FIN.One")));
						ehfPanelDocAuditDtls.setAssigndDt(new Date());
					}
				/*if(element.getLstUpdDt()==null)
					ehfPanelDocAuditDtls.setAssigndDt(new Date());
				else
					ehfPanelDocAuditDtls.setAssigndDt((element.getLstUpdDt()));
				ehfPanelDocAuditDtls.setAssigndDt((element.getCASE_DATE()));*/
				ehfPanelDocAuditDtls.setGroupId(panelDocPayVO.getPrevOwnr());
				ehfPanelDocAuditDtls.setActedBy(userId);
				ehfPanelDocAuditDtls.setActDt(new Date());
				ehfPanelDocAuditDtls.setCurrWrkflwId(panelDocPayVO.getPrevWrkflowId());
				ehfPanelDocAuditDtls.setId(ehfPanelDocAuditDtlsId);
				if(panelDocPayVO.getCeoRemarksDoc()!=null)
					ehfPanelDocAuditDtls.setRemarks(panelDocPayVO.getCeoRemarksDoc());
				ehfPanelDocAuditDtls = genericDao.save(ehfPanelDocAuditDtls);
				panelDocPayVO.setResult(config.getString("FIN.ApproveMsg"));
				if(panelDocPayVO.getPrevOwnr()!=null && "NA".equalsIgnoreCase(panelDocPayVO.getPrevOwnr()))
					panelDocPayVO.setPARTICULARS(grpName + " Approved");
				panelDocPayVO.setFlag("update");
				panelDocPayVO.setWRKFLWSETID(objId);
				panelDocPayVO.setDOC_ID(docId);
				if (ehfPanelDocAuditDtls != null) {
					{
						String wrkFlowFlg=updateWrkFlowRecord(userId,panelDocPayVO);
						panelDocPayVO.setWrkFlowFlg(wrkFlowFlg);
					}
					
				
				}
			}
			
		}

		catch (Exception e) {
//			logger.error("@------Exception has raised in PanelDocServiceImpl:updateApprvdRecord() "
//					+ e.getMessage());
			e.printStackTrace();
		}
		return panelDocPayVO;

	}
	
	@Override
	public PanelDocPayVO updateRejectRecord(List<PanelDocPayVO> panelDocCasesDetails, String userId,PanelDocPayVO panelDocPayVO) {
		long id = 0;
		String grpName = "";
		int count=0;
		List wrkFlwId=new ArrayList();
		List doctorId=new ArrayList();
		EhfPanelDocAuditDtls ehfPanelDocAuditDtls = new EhfPanelDocAuditDtls();
		EhfPanelDocAuditDtlsId ehfPanelDocAuditDtlsId = new EhfPanelDocAuditDtlsId();
		DateFormat df = new SimpleDateFormat(config.getString("FIN.DateFormat1"));
		try {
		/*Iterator<PanelDocPayVO> itr = panelDocCasesDetails.iterator();
		PanelDocPayVO element = new PanelDocPayVO();
		
			while (itr.hasNext()) {
				element = (PanelDocPayVO) itr.next();
				panelDocPayVO.setID(element.getID());
				String objId=element.getWRKFLWSETID();
				String docId=element.getDOC_ID();
				if(!(wrkFlwId.contains(objId)))
				{
					doctorId.add(count, docId);
					wrkFlwId.add(count,objId);
					count++;
				}
				panelDocPayVO.setFlag("update");
				panelDocPayVO.setActionType("Reject");
				if (ehfPanelDocAuditDtls != null) {
					String result=PanelDocConstants.EMPTY;
					result = updatePmntStatus(panelDocPayVO, userId);
									
				}
			}*/
			 //Entry in WorkFlow Table 
			  String wrkFlowFlg=null;
			  for(PanelDocPayVO panelDocPayVO1:panelDocCasesDetails)
			  	{
						id=Long.parseLong(panelDocPayVO1.getwId().toString());
					    grpName = getgrpName(panelDocPayVO.getPrevOwnr());
						ehfPanelDocAuditDtlsId.setPmtId(id);
						ehfPanelDocAuditDtls.setDoc_id(panelDocPayVO1.getDOC_ID().toString());
						List<EhfPanelDocAuditDtls> lstEhfPanelDocAuditDtls = null;
						List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
						criteriaList.add(new GenericDAOQueryCriteria("id.pmtId", id,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",
								GenericDAOQueryCriteria.CriteriaOperator.DESC));
						lstEhfPanelDocAuditDtls = genericDao.findAllByCriteria(
								EhfPanelDocAuditDtls.class, criteriaList);
						criteriaList.removeAll(criteriaList);
						if (lstEhfPanelDocAuditDtls != null	&& lstEhfPanelDocAuditDtls.size() > 0) 
						{
							Long actOrder = lstEhfPanelDocAuditDtls.get(0).getId().getActOrder();
							ehfPanelDocAuditDtlsId.setActOrder(actOrder + 1);
							
							if(lstEhfPanelDocAuditDtls.get(0).getActDt()!=null)
								ehfPanelDocAuditDtls.setAssigndDt(lstEhfPanelDocAuditDtls.get(0).getActDt());
							else 
								ehfPanelDocAuditDtls.setAssigndDt(new Date());
						} 
						else
						{
							ehfPanelDocAuditDtlsId.setActOrder(Long.parseLong(config.getString("FIN.One")));
							ehfPanelDocAuditDtls.setAssigndDt(new Date());
						}
						//ehfPanelDocAuditDtls.setAssigndDt(df.parse(element.getCASE_DATE()));
						ehfPanelDocAuditDtls.setGroupId(panelDocPayVO.getPrevOwnr());
						ehfPanelDocAuditDtls.setActedBy(userId);
						ehfPanelDocAuditDtls.setActDt(new Date());
						ehfPanelDocAuditDtls.setCurrWrkflwId(panelDocPayVO.getPrevWrkflowId());
						ehfPanelDocAuditDtls.setRemarks("Rejected by "+grpName);
						ehfPanelDocAuditDtls.setId(ehfPanelDocAuditDtlsId);
						ehfPanelDocAuditDtls = genericDao.save(ehfPanelDocAuditDtls);
						
		                panelDocPayVO.setResult(config.getString("FIN.RejectMsg"));
						panelDocPayVO.setPARTICULARS(grpName + " Rejected");
						
					    panelDocPayVO.setWRKFLWSETID(panelDocPayVO1.getwId().toString());
					    panelDocPayVO.setDOC_ID(panelDocPayVO1.getDOC_ID().toString());
					    panelDocPayVO.setFlag("update");
						panelDocPayVO.setActionType("Reject");
						
					    wrkFlowFlg=updateWrkFlowRecord(userId,panelDocPayVO); 
					    panelDocPayVO.setWrkFlowFlg(wrkFlowFlg);
					 }
		}

		catch (Exception e) {
//			logger.error("@------Exception has raised in PanelDocServiceImpl:updateAuditRecord() "
//					+ e.getMessage());
			e.printStackTrace();
		}
		return panelDocPayVO;

	}



	/**
	 * Update ehf_pnldoc_case_dtls table for individual Cases
	 * 
	 * @param Case
	 *            Details
	 * @param user
	 *            Id
	 * @return PanelDocPayVO
	 */
	private String updatePmntStatus(PanelDocPayVO panelDocPayVO, String userId) {
		EhfPanelDocCaseDtls ehfPanelDocCaseDtls = new EhfPanelDocCaseDtls();
		long id = 0;
		String result = "false";

		try {
			id = Long.parseLong(panelDocPayVO.getID());
			ehfPanelDocCaseDtls = genericDao.findById(EhfPanelDocCaseDtls.class, Long.class, id);
			if (panelDocPayVO.getCurrWrkflowId().equals("NA")) {
				if (panelDocPayVO.getActionType().equals("Approve"))
					ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.Approve"));
				else if (panelDocPayVO.getActionType().equals("Reject"))
					ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.Reject"));
			} 
			else {
				ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.InProgress"));
			}
			if(panelDocPayVO.getWorkFlow()!=null)
				{
					if(panelDocPayVO.getWorkFlow().equalsIgnoreCase("I"))
						{
							ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.InProgress"));
							ehfPanelDocCaseDtls.setWorkFlwId(panelDocPayVO.getwId());
						}
				}
			
			if(panelDocPayVO.getFlag().equals("insert"))
				ehfPanelDocCaseDtls.setWorkFlwId(Long.parseLong(panelDocPayVO.getWRKFLWSETID()));
			ehfPanelDocCaseDtls.setLstUpdUsr(userId);
			ehfPanelDocCaseDtls.setLstUpdDt(new Date());
			// ehfPanelDocCaseDtls.setParticulars(panelDocPayVO.getPARTICULARS());
			ehfPanelDocCaseDtls = genericDao.save(ehfPanelDocCaseDtls);
			result = "true";
		}

		catch (Exception e) {
//			logger.error("@------Exception has raised in PanelDocServiceImpl:updatePmntStatus() "
//					+ e.getMessage());
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Update ehf_pnldoc_workflow table for individual Cases
	 * 
	 * @param Case
	 *            Details
	 * @param user
	 *            Id
	 * @return PanelDocPayVO
	 */
	private String insertWrkFlowRecord( String userId,
			PanelDocPayVO panelDocPayVO) {
		String WrkflowInsrtRslt = "false";
		try{
		EhfPnlDocWrkflow ehfPnlDocWrkflow = new EhfPnlDocWrkflow();
		EhfPnlDocWrkFlowId ehfPnlDocWrkflowId=new EhfPnlDocWrkFlowId();
		ehfPnlDocWrkflowId.setwId(Long.parseLong(panelDocPayVO.getWRKFLWSETID()));
		ehfPnlDocWrkflowId.setDocId(panelDocPayVO.getDOC_ID());
		ehfPnlDocWrkflow.setId(ehfPnlDocWrkflowId);
		ehfPnlDocWrkflow.setPrevWrkflwId(panelDocPayVO.getPrevWrkflowId());
		ehfPnlDocWrkflow.setPrevOwnrGrp(panelDocPayVO.getPrevOwnr());
		ehfPnlDocWrkflow.setCurrWrkflwId(panelDocPayVO.getCurrWrkflowId());
		ehfPnlDocWrkflow.setCurrOwnrGrp(panelDocPayVO.getCurrOwnr());
		ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.InProgress"));
		ehfPnlDocWrkflow.setCrtUsr(userId);
		ehfPnlDocWrkflow.setCrtDt(new Date());
		ehfPnlDocWrkflow.setLstUpdUsr(userId);
		ehfPnlDocWrkflow.setLstUpdDt(new Date());
		ehfPnlDocWrkflow.setScheme(panelDocPayVO.getUSERTYPE());
		ehfPnlDocWrkflow = genericDao.save(ehfPnlDocWrkflow);
		WrkflowInsrtRslt = "insert";
		}
		catch (Exception e) {
//			logger.error("@------Exception has raised in PanelDocServiceImpl:insertWrkFlowRecord() "
//					+ e.getMessage());
			e.printStackTrace();
		}
		return WrkflowInsrtRslt;
	}

	private String updateWrkFlowRecord(String userId,
			PanelDocPayVO panelDocPayVO) {
		String WrkflowInsrtRslt = "false";
		try{
			long wId=Long.parseLong(panelDocPayVO.getWRKFLWSETID());
			String docId=panelDocPayVO.getDOC_ID();
		EhfPnlDocWrkflow ehfPnlDocWrkflow = new EhfPnlDocWrkflow();
		ehfPnlDocWrkflow = genericDao.findById(EhfPnlDocWrkflow.class,EhfPnlDocWrkFlowId.class, new EhfPnlDocWrkFlowId(wId,docId));
		ehfPnlDocWrkflow.setPrevWrkflwId(panelDocPayVO.getPrevWrkflowId());
		ehfPnlDocWrkflow.setPrevOwnrGrp(panelDocPayVO.getPrevOwnr());
		ehfPnlDocWrkflow.setCurrWrkflwId(panelDocPayVO.getCurrWrkflowId());
		if (panelDocPayVO.getCurrOwnr() == null
				|| panelDocPayVO.getCurrOwnr() == "")
			panelDocPayVO.setCurrOwnr("");
		ehfPnlDocWrkflow.setCurrOwnrGrp(panelDocPayVO.getCurrOwnr());
		if (panelDocPayVO.getCurrWrkflowId().equals("NA")) {
			if (panelDocPayVO.getActionType().equals("Approve"))
				ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.Approve"));
			else
				ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.Reject"));
		}

		else {
			ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.InProgress"));
		}
		ehfPnlDocWrkflow.setLstUpdUsr(userId);
		ehfPnlDocWrkflow.setLstUpdDt(new Date());
		ehfPnlDocWrkflow = genericDao.save(ehfPnlDocWrkflow);
		WrkflowInsrtRslt = "update";
		}
		catch (Exception e) {
//			logger.error("@------Exception has raised in PanelDocServiceImpl:updateWrkFlowRecord() "
//					+ e.getMessage());
			e.printStackTrace();
		}
		return WrkflowInsrtRslt;
	}

	@Override
	public String getgrpId(String grpName) {
		
		String grpId = "";
		List<EhfmGrps> lstehfmGrps = null;
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("grpName", grpName,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

		lstehfmGrps = genericDao
				.findAllByCriteria(EhfmGrps.class, criteriaList);
		criteriaList.removeAll(criteriaList);
		if (lstehfmGrps.size() > 0) {
			grpId = lstehfmGrps.get(0).getGrpId();
		}
		return grpId;
	}

	@Override
	public String getgrpName(String grpId) {
		String grpName = null;
		EhfmGrps ehfmGrps = genericDao.findById(EhfmGrps.class, String.class,
				grpId);
		grpName = ehfmGrps.getGrpName();
		return grpName;
	}

	@Override
	public List<PanelDocPayVO> getAllRejctdCasesDtls(PanelDocPayVO panelDocPayVO,int startIndex,int maxResults) {
		// TODO Auto-generated method stub
		List<PanelDocPayVO> panelDocRejCaseDtls = panelDocPayDao
				.generateAllRejCaseDtls(panelDocPayVO,startIndex,maxResults);
		return panelDocRejCaseDtls;

	}

	@Override
	public Map updatePanelDocPayStatus(HashMap lparamMap) {
		lparamMap = panelDocPayDao.updatePanelDocPayStatus();
		return lparamMap;
	}

	@Override
	public List<PanelDocPayVO> getPanelDocTDSCases(PanelDocPayVO panelDocPayVO) {
		List<PanelDocPayVO> panelDocTDSDtls = panelDocPayDao
				.generateTDSCases(panelDocPayVO);
		return panelDocTDSDtls;
	}

	/*@Override
	public Map updateTDSClaimStatus(HashMap hParamMap) {
		hParamMap = panelDocPayDao.updateTDSClaimStatus(hParamMap);
		return hParamMap;
	}*/



	
	/**
	 *To get cases rejected by Bank
	 * 
	 *
	 * @return PanelDocPayVO
	 */
	@Override
	public List<PanelDocPayVO> getAllBankRejctdCases(String scheme,int startIndex,int maxResults) {
		List<PanelDocPayVO> panelDocRejCaseDtls = panelDocPayDao
				.generateAllBankRejCases(scheme,startIndex,maxResults);
		return panelDocRejCaseDtls;
	}

	@Override
	public List<PanelDocPayVO> getAllRejctdCasesDtlsCEO(String scheme,int startIndex,int maxResults) {
		List<PanelDocPayVO> panelDocRejCaseCEODtls = panelDocPayDao
				.generateAllRejCasesCEO(scheme,startIndex,maxResults);
		return panelDocRejCaseCEODtls;
	}

	@Transactional
	public boolean updateStatus(PanelDocPayVO panelDocPayVO) throws Exception
	 {
			
		String docId = panelDocPayVO.getDOC_ID();
		String wIDLst= panelDocPayVO.getWorkFlow();
		String scheme=panelDocPayVO.getUSERTYPE();
		boolean result=false;
		EhfPnlDocWrkflow ehfPnlDocWrkflow=new EhfPnlDocWrkflow();
		docId=docId.replace("[~", "");
		wIDLst=wIDLst.replace("[~", "");
		
		List<String> checkdDocList=Arrays.asList(docId.split("~"));
		List<String> workDocList=Arrays.asList(wIDLst.split("~"));
		
		try{
			if(checkdDocList!=null && workDocList!=null)
			{
				
				if(checkdDocList.size()==workDocList.size())
				{
					for (int w=0;w<workDocList.size();w++)
			        {
						String doctorId=checkdDocList.get(w);
						String wrkFlwId=workDocList.get(w);
						
						List<EhfPnlDocWrkflow> lstehfPnlDocWrkflow= new ArrayList<EhfPnlDocWrkflow>();
						List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();

						criteriaList.add(new GenericDAOQueryCriteria("id.wId", Long.parseLong(wrkFlwId),
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						criteriaList.add(new GenericDAOQueryCriteria("id.docId", doctorId,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						criteriaList.add(new GenericDAOQueryCriteria("statusFlg", config.getString("FIN.InProgress"),
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						
						criteriaList.add(new GenericDAOQueryCriteria("scheme", scheme,
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						
						lstehfPnlDocWrkflow = genericDao.findAllByCriteria(EhfPnlDocWrkflow.class, criteriaList);
						criteriaList.removeAll(criteriaList);
						 for (int i=0;i<lstehfPnlDocWrkflow.size();i++) {
							 ehfPnlDocWrkflow=lstehfPnlDocWrkflow.get(i);
							 String panelDocAmt=ehfPnlDocWrkflow.getTotalPnldocAmt().toString();
							 if(panelDocPayVO.getActionType()!=null)
						 	{
							 	if(panelDocPayVO.getActionType().equals("sendBack"))
						 		{
					 			
						 			String prevWrkflwId=getNextWorkFlowId(ehfPnlDocWrkflow.getCurrWrkflwId(),"Pending");
						 			String currWrkflwId=getNextWorkFlowId(prevWrkflwId,"Approve");
						 			String currGrp=getCurrenttOwnerForNewWorkFlow(currWrkflwId);
						 			String prevGrp=ehfPnlDocWrkflow.getCurrOwnrGrp();
						 			if(prevGrp==null||(prevGrp!=null && prevGrp.equalsIgnoreCase("")))
						 				prevGrp=config.getString("FIN.CEOGrp");
						 			
						 			
							 		LabelBean usrDtls=new LabelBean();
						 			if(panelDocPayVO.getSentToSec()!=null)
					 				{
					 					if(!panelDocPayVO.getSentToSec().equalsIgnoreCase("hie"))
					 						{
					 						
									 			usrDtls=commonService.getUserDtls(panelDocPayVO.getSentToUser());
									 			EhsCeoSendbackDtls ehsCeoSendbackDtls=new EhsCeoSendbackDtls();
									 			EhsCeoSendbackDtlsId ehsCeoSendbackDtlsId=new EhsCeoSendbackDtlsId();
									 			
									 			ehsCeoSendbackDtlsId.setId(commonService.getNxtValCEOSent());
									 			ehsCeoSendbackDtlsId.setReferenceNo(wrkFlwId);
									 			ehsCeoSendbackDtls.setFunctionality(config.getString("FIN.pnlDocPmts"));
									 			ehsCeoSendbackDtls.setUnitId(usrDtls.getUNITID());
									 			ehsCeoSendbackDtls.setDepartment(usrDtls.getDeptId());
									 			ehsCeoSendbackDtls.setSchemeId(scheme);
									 			ehsCeoSendbackDtls.setRecievedDate(new Date());
									 			ehsCeoSendbackDtls.setActed("N");
									 			ehsCeoSendbackDtls.setSmsFlag("N");
									 			ehsCeoSendbackDtls.setCrtUsr(panelDocPayVO.getUserId());
									 			ehsCeoSendbackDtls.setCrtDt(new Date());
									 			if(panelDocPayVO.getCeoRemarks()!=null)
									 				ehsCeoSendbackDtls.setCeoRemarks(panelDocPayVO.getCeoRemarks());
									 			else
									 				ehsCeoSendbackDtls.setCeoRemarks("Respective Panel Doctor Payment Sent Back By CEO");
									 			ehsCeoSendbackDtls.setId(ehsCeoSendbackDtlsId);
									 			
									 			genericDao.save(ehsCeoSendbackDtls);
					 						}
					 					else
					 						currGrp=panelDocPayVO.getSentToUser();
					 				}

					 			String sendbackToUser=getUserId(panelDocPayVO.getSentToUser());
					 			
					 			ehfPnlDocWrkflow.setSendbackToUser(sendbackToUser);
					 			ehfPnlDocWrkflow.setPrevOwnrGrp(prevGrp);
					 			ehfPnlDocWrkflow.setPrevWrkflwId(prevWrkflwId);
				 				ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.InProgress"));
					 			ehfPnlDocWrkflow.setCurrWrkflwId(currWrkflwId);
					 			if(panelDocPayVO.getSentToUser()!=null)
					 				ehfPnlDocWrkflow.setCurrOwnrGrp(currGrp);
					 			else 
					 				ehfPnlDocWrkflow.setCurrOwnrGrp("NA");
					 			
					 			ehfPnlDocWrkflow.setSendBackFlag("Y");
					 			
					 			List<EhfPanelDocAuditDtls> ehfPanelDocAuditDtlsLst=new ArrayList<EhfPanelDocAuditDtls>();
					 			
					 			criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					 			
					 			criteriaList.add(new GenericDAOQueryCriteria("id.pmtId", Long.parseLong(wrkFlwId),
					 							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 			criteriaList.add(new GenericDAOQueryCriteria("doc_id", doctorId,
					 							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 			criteriaList.add(new GenericDAOQueryCriteria("id.actOrder","",
					 							GenericDAOQueryCriteria.CriteriaOperator.DESC));
					 			
					 			ehfPanelDocAuditDtlsLst=genericDao.findAllByCriteria(EhfPanelDocAuditDtls.class, criteriaList);
					 			
					 			if(ehfPanelDocAuditDtlsLst!=null)
				 				{
				 					if(ehfPanelDocAuditDtlsLst.size()>0)
			 						{
			 							if(ehfPanelDocAuditDtlsLst.get(0)!=null)
		 								{
		 									long actOrder=ehfPanelDocAuditDtlsLst.get(0).getId().getActOrder();
	 										actOrder=actOrder+1;
		 									
	 										String getName=null;
	 										if(panelDocPayVO.getSentBack()!=null)
 											{
 												getName=config.getString("FIN.NameOf"+panelDocPayVO.getSentBack());
 											}
	 										
	 										
	 										EhfPanelDocAuditDtls ehfPanelDocAuditDtls=new EhfPanelDocAuditDtls();
	 										EhfPanelDocAuditDtlsId ehfPanelDocAuditDtlsId=new EhfPanelDocAuditDtlsId();
	 										ehfPanelDocAuditDtlsId.setPmtId(Long.parseLong(wrkFlwId));
	 										ehfPanelDocAuditDtlsId.setActOrder(actOrder);
		 									ehfPanelDocAuditDtls.setId(ehfPanelDocAuditDtlsId);
		 									ehfPanelDocAuditDtls.setDoc_id(doctorId);
		 									if(panelDocPayVO.getCeoRemarks()!=null)
		 										ehfPanelDocAuditDtls.setRemarks(panelDocPayVO.getCeoRemarks());
		 									else
		 										ehfPanelDocAuditDtls.setRemarks("SENT BACK TO "+getName+" BY CEO");
		 									ehfPanelDocAuditDtls.setGroupId(ehfPnlDocWrkflow.getPrevOwnrGrp());
		 									ehfPanelDocAuditDtls.setCurrWrkflwId(ehfPnlDocWrkflow.getPrevWrkflwId());
		 									ehfPanelDocAuditDtls.setAssigndDt(ehfPanelDocAuditDtlsLst.get(0).getActDt());
		 									ehfPanelDocAuditDtls.setActedBy(panelDocPayVO.getUserId());
		 									ehfPanelDocAuditDtls.setActDt(new Date());
		 									genericDao.save(ehfPanelDocAuditDtls);
		 									
		 								}
			 						}
				 				}
						 			
						 		}
						 	}
							 else
						 	{
							 	if(ehfPnlDocWrkflow.getCurrWrkflwId().equalsIgnoreCase(config.getString("FIN.WorkFlowIdRej")))
							 		{ehfPnlDocWrkflow.setStatusFlg(PanelDocConstants.TempRejSentStatus);}
							 	else
							 		{ehfPnlDocWrkflow.setStatusFlg(PanelDocConstants.TempSentStatus);}
						 	}	
								 
							 ehfPnlDocWrkflow.setLstUpdDt(new Date());
							 ehfPnlDocWrkflow.setLstUpdUsr(panelDocPayVO.getUserId());
							 ehfPnlDocWrkflow = genericDao.save(ehfPnlDocWrkflow);
							 result=true;
							 
							 if (result == true && !(panelDocPayVO.getActionType()!=null && panelDocPayVO.getActionType().equals("sendBack")))
							{
								EhfmBankAccountDtls dtls=genericDao.findById(EhfmBankAccountDtls.class, String.class, config.getString("ACC.BANKBAL_MAIN_AC_TS"));
								if(dtls!=null){
									
									Double procAmt=dtls.getProcessAmount();
				    				if(procAmt!=null && !("").equalsIgnoreCase(procAmt.toString()))
				    				{
				    					dtls.setProcessAmount(procAmt+Double.parseDouble(panelDocAmt));
				    				}
				    				else
				    				{
				    					dtls.setProcessAmount(Double.parseDouble(panelDocAmt));
				    				}
										
				    				dtls.setCeoPdpProcessDt(new java.sql.Timestamp(new Date().getTime()));
				    				genericDao.save(dtls);
								}
							}
						}
			        }
				}
				
			}
			}
		catch (Exception e) {
			logger.error("@------Exception has raised in updateStatus() in PanelDocPayServiceImpl "
					+ e.getMessage());
			e.printStackTrace();
			result=false;
			throw e;
		}
		return result;
	}
	
	public String getNextWorkFlowId(String workflowId,String buttonValue){
		 
		try
		{
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
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
		 
	 }
	
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
	 
	 public String getUserId(String grpId){
			StringBuffer query= new StringBuffer();
			List<PanelDocPayVO> caseCountStatus = new ArrayList<PanelDocPayVO>();
			String finalStr=null;
			query.append(" select eu.userId as userId");
			query.append(" from EhfmUsrGrpsMpg eugm, EhfmUsers eu,EhfmGrps eg");
			query.append(" where eugm.id.grpId='"+grpId+"' and eugm.id.usergrpId=eu.userId");
			query.append(" and eu.userType in ('CD202','CD203') and eg.grpId=eugm.id.grpId");
			query.append(" and eu.serviceFlag='Y' and eu.serviceExpiryDt is null ");
			
			caseCountStatus = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString());
			if(caseCountStatus!=null)
				if(caseCountStatus.size()>0)
					{
						if(caseCountStatus.get(0)!=null)
							{
								if(caseCountStatus.get(0).getUserId()!=null)
									finalStr=caseCountStatus.get(0).getUserId();
							}
					}
			
			
			
			return finalStr; 
		}

	@Override
	public boolean updateTDSStatus(PanelDocPayVO panelDocPayVO) {
		
		String cases = PanelDocConstants.EMPTY ;
		boolean result=false;
		StringBuffer query= new StringBuffer();
		long id = 0;
		String scheme=panelDocPayVO.getUSERTYPE();
		EhfPnldocTdsPayment ehfPnldocTdsPayment=new EhfPnldocTdsPayment();
		String caseList=panelDocPayVO.getCASE_ID();
		caseList=caseList.replace("[~", "");
		List<String> checkdDocList=Arrays.asList(caseList.split("~"));
		try{
			String selectedCondition = " tds_payment_id in ( " ;
		for ( String tdsCaseId:checkdDocList )
        {
			
			ehfPnldocTdsPayment = genericDao.findById(EhfPnldocTdsPayment.class, String.class, tdsCaseId);
			if(ehfPnldocTdsPayment!=null && ehfPnldocTdsPayment.toString()!="")
				ehfPnldocTdsPayment.setPaymentStatus(PanelDocConstants.TempSentStatus);
			ehfPnldocTdsPayment.setLastUpdDate(new Date());
			ehfPnldocTdsPayment.setLastUpdUser(panelDocPayVO.getUserId());
			ehfPnldocTdsPayment= genericDao.save(ehfPnldocTdsPayment);
        }
		
		
			 
		}
		catch (Exception e) {
//			logger.error("@------Exception has raised in updateTDSStatus() "
//					+ e.getMessage());
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	/**
	 * Update ehf_pnldoc_case_dtls table for individual Cases by Kalyan
	 * 
	 * @param panelDocPayVO
	 *            
	 * @return void
	 */
	public String updateCaseDtls(List<PanelDocPayVO> panelDocCasesDetails,String action,String userId) {
		List<EhfPanelDocCaseDtls> ehfPanelDocCaseDtlsLst=new ArrayList<EhfPanelDocCaseDtls>();
		long id = 0;
		String result = "false";
			if(panelDocCasesDetails!=null && panelDocCasesDetails.size()>0)
			{
				for(PanelDocPayVO panelDocPayVO : panelDocCasesDetails)
					{
						try {
							List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
								 criteriaList.add(new GenericDAOQueryCriteria("workFlwId", panelDocPayVO.getwId(),
											GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								 criteriaList.add(new GenericDAOQueryCriteria("docId", panelDocPayVO.getDOC_ID(),
											GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								 ehfPanelDocCaseDtlsLst = genericDao.findAllByCriteria(EhfPanelDocCaseDtls.class, criteriaList);
								 for(EhfPanelDocCaseDtls ehfPanelDocCaseDtls : ehfPanelDocCaseDtlsLst)
								 	{
									 	if (action!=null && !action.equals(""))
									 		{
												if (action.equals("Approve"))
													ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.Approve"));
												else if (action.equals("Reject"))
													ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.Reject"));
												else 
													ehfPanelDocCaseDtls.setPnlDocPmntStatus(config.getString("FIN.InProgress"));
											}
									 	if(userId!=null)
									 		ehfPanelDocCaseDtls.setLstUpdUsr(userId);
										ehfPanelDocCaseDtls.setLstUpdDt(new Date());
										// ehfPanelDocCaseDtls.setParticulars(panelDocPayVO.getPARTICULARS());
										ehfPanelDocCaseDtls = genericDao.save(ehfPanelDocCaseDtls);
								 	}
							
							
							result = "true";
						}
						catch (Exception e) {
//							logger.error("@------Exception has raised in PanelDocServiceImpl:updatePmntStatus() "
//									+ e.getMessage());
							e.printStackTrace();
						}
					}	
			}	
		

		return result;

	}
	
	/* Added by Srikalyan remarks for 
	 * worklists in Panel Doctor payments for CEO
	 * */
	@Override
	public List<PanelDocPayVO> getCEORemarksList(PanelDocPayVO panelDocPayVO, String currGrpId, String prevGrpId)
		{
			List<PanelDocPayVO> panelDocPayVOLst=new ArrayList<PanelDocPayVO>();
			String docId=panelDocPayVO.getDOC_ID();
			Long wId=panelDocPayVO.getwId();
			EhfPnlDocWrkflow ehfPnlDocWrkflow=genericDao.findById(EhfPnlDocWrkflow.class, EhfPnlDocWrkFlowId.class, new EhfPnlDocWrkFlowId(wId,docId));
			if(ehfPnlDocWrkflow!=null)
				if(ehfPnlDocWrkflow.getSendBackFlag()!=null)
					{
						if(ehfPnlDocWrkflow.getSendBackFlag().equalsIgnoreCase("Y"))
								{
									StringBuffer query=new StringBuffer();
									query.append(" select epad.id.pmtId as wId,epad.actDt as lstUpdDt,epad.remarks as ceoRemarks,");
									query.append(" epad.currWrkflwId as WRKFLWSETID,eu.firstName ||' '|| eu.lastName as EMPNAME ");
									query.append(" from EhfPanelDocAuditDtls epad,EhfmUsers eu where ");
									query.append(" epad.id.pmtId='"+wId+"' and epad.doc_id='"+docId+"'");
									query.append(" and epad.currWrkflwId in ('"+config.getString("FIN.CEOKeptPendUpd")+"','"+config.getString("FIN.CEOKeptPend")+"') ");
									query.append(" and eu.userId=epad.actedBy ");
									query.append(" order by epad.id.actOrder ");
									
									panelDocPayVOLst=genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString());
								}
					}
			return panelDocPayVOLst;
		}

	/***
	 * @author Kalyan
	 * @Param User to Which the Statuses needs to be populated
	 * @return Returns all the statuses for a User to Search upon. 
	 */
	public List<LabelBean> getCaseStatus(String user)
		{
			List<LabelBean> retLst=new ArrayList<LabelBean>();
			try
				{
					StringBuffer query=new StringBuffer();
					if(user!=null && user.equalsIgnoreCase("AllStats"))
						{
							query.append( " select a.cmbDtlId as ID , a.cmbDtlName as VALUE " );
							query.append( " from EhfmCmbDtls a " );
							query.append( " where a.cmbHdrId = '"+config.getString("FIN.Status.HDRID")+"' " );
							query.append( " order by a.cmbDtlName "  );
							retLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());	
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					logger.error("@------Exception has raised in PanelDocServiceImpl:getCaseStatus() "
//							+ e.getMessage());
					return retLst;
				}
			return retLst;
		}
	/***
	 * @author Kalyan
	 * @Param Payment Status and Current user Grp or Panel Doctor details 
	 * @return Returns all the Payments related to Search Criteria  
	 */
	@SuppressWarnings("unchecked")
	public List<PanelDocPayVO> getPanelDocCasesSearch(PanelDocPayVO panelDocPayVO)
		{
			List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
			SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session=factory.openSession();
			try
				{
					String dsgnId = config.getString("FIN.PanelDocDesgID");
					String schemeId = panelDocPayVO.getScheme();
					int startIndex = panelDocPayVO.getStartIndex();
					int maxResults = panelDocPayVO.getEndIndex();
					if(schemeId==null)
						schemeId=config.getString("Scheme.TG.State");
					
					StringBuffer query=new StringBuffer();
					query.append(" select eu.firstName as firstName,eu.lastName as DOC_NAME ");
				    query.append(" ,epw.id.wId as wId,epw.id.docId as DOC_ID");
				    query.append(" ,epw.currWrkflwId as currWrkflowId,epw.currOwnrGrp as currOwnr");
				    query.append(" ,epw.statusFlg as DISTINCTSTATUS,epw.scheme as scheme");	    
				    query.append(" ,epw.claimAprvAmt as claimAprvAmt,epw.claimRejAmt as claimRejAmt");
				    query.append(" ,epw.claimPendAmt as claimPendAmt,epw.preauthAprvAmt as preauthAprvAmt");
				    query.append(" ,epw.preauthRejAmt as preauthRejAmt,epw.preauthPendAmt as preauthPendAmt ");
				    query.append(" ,epw.totalPnldocAmt as totalPnldocAmt,to_char(to_date(epw.month,'MM'),'MONTH') as month,epw.year as year");
				    query.append(" ,ecd.cmbDtlName as panelDocPmntStatus,eu.loginName as EMPNAME  ");
				    query.append("  from EhfPnlDocWrkflow epw , EhfmUsers eu , EhfmPnldocStatusMpg eps ");
				    query.append(" 		,EhfmCmbDtls ecd ");
				    query.append(" where eu.userId=epw.id.docId");
				    query.append(" and eu.dsgnId='"+dsgnId+"' and epw.scheme='"+schemeId+"' ");
				    query.append(" and ecd.cmbDtlId = eps.refId and eps.currentWorkflowId = epw.currWrkflwId ");
				    query.append(" and eps.activeYn = 'Y' ");
				    query.append(" and eps.scheme = epw.scheme ");
				    
				    if(panelDocPayVO.getDOC_NAME()!=null && !"".equalsIgnoreCase(panelDocPayVO.getDOC_NAME()))
			    		query.append( " and upper(eu.firstName ||' '||eu.lastName) like '%"+panelDocPayVO.getDOC_NAME().toUpperCase()+"%' " ); 
				    if(panelDocPayVO.getDOC_ID()!=null && !"".equalsIgnoreCase(panelDocPayVO.getDOC_ID()))
			    		query.append( " and upper(eu.loginName) like '%"+panelDocPayVO.getDOC_ID().toUpperCase()+"%' " );
				    if(panelDocPayVO.getMonth()!=null && !"".equalsIgnoreCase(panelDocPayVO.getMonth()))
				    	{
				    		query.append( " and epw.month ='"+panelDocPayVO.getMonth().substring(0,2)+"' " );
				    		query.append( " and epw.year ='"+panelDocPayVO.getMonth().substring(3,7)+"' " );
				    	}
				    if(panelDocPayVO.getAction()!=null && !"".equalsIgnoreCase(panelDocPayVO.getAction())
				    		&& !"00".equalsIgnoreCase(panelDocPayVO.getAction()))
			    		{
				    		//Conditions to get Grp and Workflow ID 
				    		String[] args = config.getString("FIN."+panelDocPayVO.getAction()).split("~");
				    		if(panelDocPayVO.getAction().equalsIgnoreCase(config.getString("FIN.RejectStat")))
				    			{
				    				query.append( " and epw.prevOwnrGrp = '"+args[0]+"' and epw.prevWrkflwId = '"+args[1]+"' and epw.statusFlg = 'R' " );
				    			}
				    		
				    		else
				    			{
				    				//Some Workflows Can have nulls as workflow Ids 
					    			if(args[0]!=null && !args[0].equalsIgnoreCase(""))
				    					query.append( " and epw.currOwnrGrp = '"+args[0]+"' " );
				    				else
				    					query.append( " and epw.currOwnrGrp is null ");
					    			if(args[1]!=null && !args[1].equalsIgnoreCase(""))
				    					query.append( " and epw.currWrkflwId = '"+args[1]+"' ");
				    				else
				    					query.append( " and epw.currWrkflwId is null ");
				    			}
			    		}
				    
				    if(startIndex==0 && maxResults==0)
				    	panelDocCases=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
				    else if (maxResults!=0)
				    	panelDocCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(PanelDocPayVO.class)).list();
				}
			catch (Exception e) 
				{
					e.printStackTrace();
//					logger.error("Exception in method PanelDocPayDAOImpl:generatepanelDocCases--"
//							+ e.getMessage());
				}
			finally 
				{
					session.close();
					factory.close();
				}
		    return panelDocCases;
		}
	
	@Override
	public String getLoginName(String grpId){
		StringBuffer query= new StringBuffer();
		List<PanelDocPayVO> caseCountStatus = new ArrayList<PanelDocPayVO>();
		String finalStr=null;
		query.append(" select upper(eu.lastName) as EMPNAME,upper(eg.grpName) as ID, ");
		query.append(" eu.loginName as DOC_NAME ");
		query.append(" from EhfmUsrGrpsMpg eugm, EhfmUsers eu,EhfmGrps eg");
		query.append(" where eugm.id.grpId='"+grpId+"' and eugm.id.usergrpId=eu.userId");
		query.append(" and eu.userType in ('CD202') and eg.grpId=eugm.id.grpId");
		query.append(" and eu.serviceFlag='Y' and eu.serviceExpiryDt is null ");
		
		caseCountStatus = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString());
		if(caseCountStatus!=null)
			if(caseCountStatus.size()>0)
				{
					if(caseCountStatus.get(0)!=null)
						{
							if(caseCountStatus.get(0).getEMPNAME()!=null)
								finalStr=caseCountStatus.get(0).getEMPNAME();
							if(caseCountStatus.get(0).getID()!=null)
								finalStr=finalStr+" ("+caseCountStatus.get(0).getID()+")";
							if(caseCountStatus.get(0).getDOC_NAME()!=null)
								finalStr=finalStr+" - "+caseCountStatus.get(0).getDOC_NAME();
						}
				}
		
		
		
		return finalStr; 
	}
	
	@Override
	public String getDocId(Long docId)
		{
			String result=null;
			List<EhfPnlDocWrkflow> ehfPnlDocWrkFlowLst=new ArrayList<EhfPnlDocWrkflow>();
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.wId", docId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			ehfPnlDocWrkFlowLst = genericDao
					.findAllByCriteria(EhfPnlDocWrkflow.class, criteriaList);
			criteriaList.removeAll(criteriaList);
			if(ehfPnlDocWrkFlowLst!=null)
				{
					if(ehfPnlDocWrkFlowLst.size()>0)
						{
							if(ehfPnlDocWrkFlowLst.get(0)!=null)
								{	
									if(ehfPnlDocWrkFlowLst.get(0).getId().getDocId()!=null)
										result=ehfPnlDocWrkFlowLst.get(0).getId().getDocId();
								}		
						}		
				}
			
			return result;
		}
	@Override
	public List<PanelDocPayVO> getPnlDocSpclty(String docId)
		{
			List<PanelDocPayVO> panelDocPayVOLst=new ArrayList<PanelDocPayVO>();
			String doctorId=docId;
			StringBuffer query=new StringBuffer();
			query.append(" select eum.id.specialityId as ID,es.disMainName as result");
			query.append(" from EhfmUsrSpecialityMpg eum,EhfmSpecialities es ");
			query.append(" where eum.id.userId='"+doctorId+"'");
			query.append(" and eum.id.grpId in ('GP28') and eum.activeYN='Y' ");
			query.append(" and eum.id.specialityId=es.disMainId ");
			
			panelDocPayVOLst=genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString());
			
			return panelDocPayVOLst;
		}
	@Override
	public String getPnlDocAmt(Long wId,String docId)
		{
			String result="0";
			EhfPnlDocWrkflow ehfPnlDocWrkflow=genericDao.findById(EhfPnlDocWrkflow.class, EhfPnlDocWrkFlowId.class, 
												new EhfPnlDocWrkFlowId(wId,docId));
			if(ehfPnlDocWrkflow!=null)
				{
					if(ehfPnlDocWrkflow.getTotalPnldocAmt()!=null)
						result=ehfPnlDocWrkflow.getTotalPnldocAmt().toString();
				}
			
			return result;
		}
	@Override
	public String getDoctorName(String docId)
		{
			String result=null;
			EhfmUsers ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, docId);
			if(ehfmUsers!=null)
				{
					if(ehfmUsers.getFirstName()!=null)
						result=ehfmUsers.getFirstName().toUpperCase();
					if(ehfmUsers.getLastName()!=null)
						result=result+" "+ehfmUsers.getLastName().toUpperCase();
				}
			
			return result;
		}
	@Override
	public List<PanelDocPayVO> generateCasesByWrkFlow(PanelDocPayVO panelDocPayVO, String prevGrpId) 
		{
			String docId=panelDocPayVO.getDOC_ID();
			String dispType=panelDocPayVO.getDispType();
			String scheme=panelDocPayVO.getUSERTYPE();
			String args[]=new String[4];
			String currOwngrpId=panelDocPayVO.getCurrOwnr();
			
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
		        
				StringBuffer query= new StringBuffer();
			if(dispType.equals("fromDepts"))
			{
				args[0]=prevGrpId;
	   			args[1]=currOwngrpId;
	   			args[2]="P";
				args[3]=scheme;
		    }
	
		    
			
			query.append(" select epw.id.wId as wId,epw.id.docId as DOC_ID");
			query.append(" ,epw.currWrkflwId as currWrkflowId,epw.currOwnrGrp as currOwnr");
			query.append(" ,epw.lstUpdDt as lstUpdDt");
			query.append(" from EhfPnlDocWrkflow epw where");
			query.append(" epw.prevOwnrGrp=? ");
			query.append(" and epw.currOwnrGrp=? ");
			query.append(" and epw.statusFlg=?");
			query.append(" and epw.scheme=? ");
			query.append(" and epw.id.wId='"+panelDocPayVO.getwId()+"' ");
			query.append(selectedCondition);
			
		    panelDocCasesListDtls = genericDao.executeHQLQueryList(PanelDocPayVO.class, query.toString(),args);
		    casesListDtls.addAll(panelDocCasesListDtls);
			
			return casesListDtls;
		
	}
}


