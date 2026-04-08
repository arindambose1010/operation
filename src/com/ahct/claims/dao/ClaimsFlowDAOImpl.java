package com.ahct.claims.dao;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.FileDataVO;
import com.ahct.claims.valueobject.TransactionVO;
import com.ahct.common.util.GenerateAsciiFile;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.dao.FollowUpDAO;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseClaim;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfChronicCaseDtl;
import com.ahct.model.EhfChronicCaseDtlPK;
import com.ahct.model.EhfClaimAccounts;
import com.ahct.model.EhfClaimCexChklist;
import com.ahct.model.EhfClaimCtdChklst;
import com.ahct.model.EhfClaimTdsPayment;
import com.ahct.model.EhfClaimTechChklst;
import com.ahct.model.EhfClaimTechChklstNew;
import com.ahct.model.EhfClaimTrustPayment;
import com.ahct.model.EhfErroneousClaim;
import com.ahct.model.EhfFollowUpClaimAccounts;
import com.ahct.model.EhfOpdClaimAudit;
import com.ahct.model.EhfOpdClaimAuditPK;
import com.ahct.model.EhfOpdClaimDtls;
import com.ahct.model.EhfOpdPatient;
import com.ahct.model.EhfPanelDocCaseDtls;
import com.ahct.model.EhfPaymentSlabDtls;
import com.ahct.model.EhfRevHospDeduct;
import com.ahct.model.EhfSliaPayments;
import com.ahct.model.EhfSurplusTdsClaim;
import com.ahct.model.EhfmDentalProcCriteria;
import com.ahct.model.EhfmDentalProcCriteriaPK;
import com.ahct.model.EhfmHospSurgPer;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PreauthVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.EhfPaymentSlabDtlsID;

/**
 * The Class ClaimsFlowDAOImpl.
 * 
 * @author Ishank Paharia
 * @version jdk1.6
 * @class This class is used as dao impl for claims process
 * @Date 4 April 2013
 */

public class ClaimsFlowDAOImpl implements ClaimsFlowDAO {

	/** The claims payment dao. */
	ClaimsPaymentDAO claimsPaymentDao;

	/**
	 * Gets the claims payment dao.
	 *
	 * @return the claims payment dao
	 */
	public ClaimsPaymentDAO getClaimsPaymentDao() {
		return claimsPaymentDao;
	}

	/**
	 * Sets the claims payment dao.
	 *
	 * @param claimsPaymentDao the new claims payment dao
	 */
	public void setClaimsPaymentDao(ClaimsPaymentDAO claimsPaymentDao) {
		this.claimsPaymentDao = claimsPaymentDao;
	}

	/** The chronic flow payment dao. */
	//ChronicFlowPaymentDao chronicFlowPaymentDao;

	/**
	 * Sets the chronic flow payment dao.
	 *
	 * @param chronicFlowPaymentDao the new chronic flow payment dao
	 */
	/*public void setChronicFlowPaymentDao(
			ChronicFlowPaymentDao chronicFlowPaymentDao) {
		this.chronicFlowPaymentDao = chronicFlowPaymentDao;
	}*/

	/** The Constant logger. */
	private static final Logger logger = Logger
			.getLogger(ClaimsFlowDAOImpl.class);

	/** The generic dao. */
	GenericDAO genericDao;

	/** The hibernate template. */
	HibernateTemplate hibernateTemplate;

	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");

	/** The sdf. */
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Gets the hibernate template.
	 * 
	 * @return the hibernate template
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * Sets the hibernate template.
	 * 
	 * @param hibernateTemplate
	 *            the new hibernate template
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Sets the generic dao.
	 * 
	 * @param genericDao
	 *            the new generic dao
	 */
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	/** The configuration service. */
	private static ConfigurationService configurationService;

	/** The config. */
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	FollowUpDAO followUpDao;
	public FollowUpDAO getFollowUpDao() {
		return followUpDao;
	}
     public void setFollowUpDao(FollowUpDAO followUpDao) {
		this.followUpDao = followUpDao;
	}
	/*
	 * @param claimFlowVO the claim flow vo 
	 * @Desc used to save claim details in table 
	 * @return claimFlowVO
	 */
	@Override
	@Transactional
	public ClaimsFlowVO saveClaim(ClaimsFlowVO claimFlowVO) throws Exception {
		Long lActOrder = 0L;
		Double approveAmt = 0.0;
		String smsMsg = ClaimsConstants.EMPTY;
		String msg = ClaimsConstants.EMPTY;
		String remarks = ClaimsConstants.EMPTY;
		String lStrNextStatus = null;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
		args[0] = claimFlowVO.getCaseId();
		List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
				ClaimsFlowVO.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()
				&& actOrderList.get(0).getCOUNT() != null) {
			if (actOrderList.get(0).getCOUNT().longValue() >= 0)
				lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
		}
		try {
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,claimFlowVO.getCaseId());
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("caseId",claimFlowVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfErroneousClaim> ehfErroneousClaimObj = genericDao.findAllByCriteria(EhfErroneousClaim.class, criteriaList);
			//EhfErroneousClaim ehfErroneousClaimObj = genericDao.findById(EhfErroneousClaim.class,String.class, claimFlowVO.getCaseId());
			if (ehfCase.getCaseStatus() != null
					&& claimFlowVO.getCaseStat() != null
					&& !ehfCase.getCaseStatus().equalsIgnoreCase(
							claimFlowVO.getCaseStat())) {
				msg = ClaimsConstants.ALREADYMESSAGE;
				claimFlowVO.setMsg(msg);
				claimFlowVO.setSmsMsg(ClaimsConstants.EMPTY);
				return claimFlowVO;
			}
            //getting hospital Details
			EhfmHospitals ehfmHosp = genericDao.findById(EhfmHospitals.class,
					String.class, ehfCase.getCaseHospCode());
			String lStrHospName = ehfmHosp.getHospName();

				if (claimFlowVO.getCaseId() != null && ehfCase.getCaseStatus() != null) 
				{
						lStrNextStatus = getNextStatus(ehfCase.getCaseStatus(),claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
						
						//Added by sravan for erroneous claim payment issue in ACO Role
						if (claimFlowVO.getCaseId() != null && (!"".equalsIgnoreCase(ehfCase.getErrClaimStatus()) && config.getString("EHF.Claims.CHAppErr").equalsIgnoreCase(ehfCase.getErrClaimStatus()))) 
						{
							lStrNextStatus = getNextStatus(ehfCase.getErrClaimStatus(),claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
						}
				/**
				 * Added by ramalakshmi fir skipping CPD role in Heamodialysis cases
				 */
					if((claimFlowVO.getHeamFlag()!=null && "Y".equalsIgnoreCase(claimFlowVO.getHeamFlag())
							&&(claimFlowVO.getRoleId()!=null &&
								claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.CEX"))) && (config.getString("EHF.HUB.HOSPITALS")).contains("~"+ehfCase.getCaseHospCode()+"~") ))
					{
						lStrNextStatus=config.getString("EHF.Claimshub.Heamodialysis.Forwarded");
					}
				//End of N18 changes
				if(ehfCase.getCochlearYN()!=null)
					{
						if (claimFlowVO.getCaseId() != null
							&& ehfCase.getCaseStatus() != null
							&& ehfCase.getCochlearYN().equalsIgnoreCase("Y"))
							{	
								if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_initiated")))
									{
										lStrNextStatus=config.getString("Coclear_claim_medco_initiated");
									}
								if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_ch_pending_updated")))
								{
									lStrNextStatus=config.getString("Coclear_Claim_medco_ch_pending_updated");
								}
								if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_cpd_pending_updated")))
								{
									lStrNextStatus=config.getString("Claim_medco_cpd_pending_updated");
								}
								if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_ctd_pending_updated")))
								{
									lStrNextStatus=config.getString("Claim_medco_ctd_pending_updated");
								}
							}	
					}	
				//For organ transplant status
				if(ehfCase.getOrganTransYN()!=null)
				{
					if (claimFlowVO.getCaseId() != null
						&& ehfCase.getCaseStatus() != null
						&& ehfCase.getOrganTransYN().equalsIgnoreCase("Y"))
						{	
							/*if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_initiated")))
								{
									lStrNextStatus=config.getString("Organ_claim_medco_initiated");
								}*/
						if(ehfCase.getCaseStatus()!=null && (!"CD1539".equalsIgnoreCase(ehfCase.getCaseStatus()) && !"CD1540".equalsIgnoreCase(ehfCase.getCaseStatus()) && ((!"CD1111".equalsIgnoreCase(ehfCase.getCaseStatus())) && (ehfCase.getOrganTransYN()!=null && "Y".equalsIgnoreCase(ehfCase.getOrganTransYN())))))
						{
						if(lStrNextStatus.equalsIgnoreCase(config.getString("EHF.Claims.PanelDocApprove")))
						{
							lStrNextStatus=config.getString("EHF.Claims.FirstPanelApprove");
						}
						if(lStrNextStatus.equalsIgnoreCase(config.getString("FIN.RejClaimPanelDocStatus")))
						{
							lStrNextStatus=config.getString("EHF.Claims.SecondPanelApprove");
						}
						if(lStrNextStatus.equalsIgnoreCase(config.getString("EHF.Claims.PanelDocPending")))
						{
							lStrNextStatus=config.getString("EHF.Claims.FirstPanelPending");
						}
						}
						/*if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_initiated")))
						{
							lStrNextStatus=config.getString("Organ_claim_medco_initiated");
						}*/
					/*if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_ch_pending_updated")))
					{
						lStrNextStatus=config.getString("Organ_Claim_medco_ch_pending_updated");
					}
					if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_cpd_pending_updated")))
					{
						lStrNextStatus=config.getString("Organ_medco_cpd_pending_updated");
					}
					if(lStrNextStatus.equalsIgnoreCase(config.getString("Claim_medco_ctd_pending_updated")))
					{
						lStrNextStatus=config.getString("Organ_medco_ctd_pending_updated");
					}*/
						}	
				}
			}
		
			claimFlowVO.setCaseNextStat(lStrNextStatus);
			claimFlowVO.setPatientId(ehfCase.getCasePatientNo());

			//Added by sravan for erroneous claim payment issue in ACO Role
			if (claimFlowVO.getCaseId() != null && (!"".equalsIgnoreCase(ehfCase.getErrClaimStatus()) && config.getString("EHF.Claims.CHAppErr").equalsIgnoreCase(ehfCase.getErrClaimStatus())))
			{	
				if(ehfErroneousClaimObj != null && ehfErroneousClaimObj.size() > 0)
				{
					EhfErroneousClaim EhfErroneousClaimObj = ehfErroneousClaimObj.get(0);
					EhfErroneousClaimObj.setErrClaimStatus(lStrNextStatus);
					EhfErroneousClaimObj.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
					EhfErroneousClaimObj.setLstUpdUsr(claimFlowVO.getUserId());
					EhfErroneousClaimObj = genericDao.save(EhfErroneousClaimObj);
				}
			}
			EhfCaseClaim ehfCaseClaim = genericDao.findById(EhfCaseClaim.class,
					String.class, claimFlowVO.getCaseId());
			if (ehfCaseClaim != null) {
				
				ehfCaseClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfCaseClaim.setLstUpdUsr(claimFlowVO.getUserId());
			} else {
				ehfCaseClaim = new EhfCaseClaim();
				ehfCaseClaim.setCaseId(claimFlowVO.getCaseId());
				ehfCaseClaim.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				ehfCaseClaim.setCrtUsr(claimFlowVO.getUserId());
			}

			// for saving non tech checklist
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CEX"))||
					claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.COC.CEX"))
				||	claimFlowVO.getRoleId().equalsIgnoreCase(
						config.getString("EHF.Claims.ORG.CEX"))) {

				saveCexCheckList(claimFlowVO);
				remarks = claimFlowVO.getCexRemark();
				if (ehfCaseClaim.getClaimBillAmt() != null)
					claimFlowVO.setCexAprAmt(ehfCaseClaim.getClaimBillAmt()
							.toString());
				if (claimFlowVO.getCexAprAmt() != null
						&& !claimFlowVO.getCexAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setCexAprAmt(Double.parseDouble(claimFlowVO
							.getCexAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO.getCexAprAmt());
				}

				ehfCaseClaim.setCexRmrk(remarks);
				if(claimFlowVO.getHeamFlag()!=null && "Y".equalsIgnoreCase(claimFlowVO.getHeamFlag())  
						&& ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Initiated")) &&  (config.getString("EHF.HUB.HOSPITALS")).contains("~"+ehfCase.getCaseHospCode()+"~"))
				{
					//msg = config.getString("EHF.Claims.MSG.CEXFWD.Heam");
					msg = config.getString("EHF.Claims.MSG.CEXFWDCPD.Heam");
					
				}
				else if (claimFlowVO.getHeamFlag()!=null && "Y".equalsIgnoreCase(claimFlowVO.getHeamFlag())  
						&& ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.HUB")) &&  (config.getString("EHF.HUB.HOSPITALS")).contains("~"+ehfCase.getCaseHospCode()+"~"))
				{
					//msg = config.getString("EHF.Claims.MSG.CEXFWD.Heam");
					msg = config.getString("EHF.Claims.MSG.HUBFWDCPD.Heam");
					
				}
				else if (ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.Initiated"))) {
					msg = config.getString("EHF.Claims.MSG.CPDFWD");
				} else if (ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.CHPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.CPDFWD");
				}else if (ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.Coclear.Initiated"))) {
					msg = config.getString("EHF.Claims.MSG.COCINI");
				}else if (ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.Coclear.CHPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.COCINI");
				}else if (ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.Organ.Initiated"))) { //For organ transplant
					msg = config.getString("EHF.Claims.MSG.ORGINI");	
				}else if (ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.Organ.CHPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.ORGINI");
				}
				
				if(claimFlowVO.getDentalFlag()!=null && 
						"Y".equalsIgnoreCase(claimFlowVO.getDentalFlag()))
					{
						if (ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Initiated"))
								|| ehfCase.getCaseStatus().equalsIgnoreCase(
										config.getString("EHF.Claims.CHPendUpdated"))) 
							{
								msg = config.getString("EHF.Claims.MSG.CPDFWD.CTD");
							}	
					}
			}
			// for saving tech checklist
			if ((claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CPD"))
					&& ehfCase.getCaseStatus().equalsIgnoreCase(
							config.getString("EHF.Claims.Forwarded")) 
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.FirstPanelApprove"))
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.SecondPanelApprove"))
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.FirstPanelPendingUpdated"))
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.CPDPendUpdated"))
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.CPDPendNotUpdated")))
							||(claimFlowVO.getRoleId().equalsIgnoreCase(
									config.getString("EHF.Claims.COCCMT"))
									&& ehfCase.getCaseStatus().equalsIgnoreCase(
											config.getString("EHF.Claims.Forwarded.By.COCCEX")))
							||(claimFlowVO.getRoleId().equalsIgnoreCase(
									config.getString("EHF.Claims.ORGCMT"))                //Forwarded by  organ CEX  
									&& ehfCase.getCaseStatus().equalsIgnoreCase(
											config.getString("EHF.Claims.Forwarded.By.ORGCEX")))
							||(ehfCase.getCaseStatus().equalsIgnoreCase(
										config.getString("EHF.Claims.SENDBACKTOCPDBYEOCOM"))))
											 {
				if(!(ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.FirstPanelApprove"))
				|| ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.SecondPanelApprove"))
			/*	|| ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.FirstPanelPendingUpdated")*/
						|| (ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.CPDPendUpdated")) && (ehfCase.getOrganTransYN()!=null && "Y".equalsIgnoreCase(ehfCase.getOrganTransYN())))			
						))
				{
				saveTechCheckList(claimFlowVO);
				}
				if(ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.FirstPanelApprove"))
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.SecondPanelApprove"))
									|| (ehfCase.getCaseStatus().equalsIgnoreCase(
											config.getString("EHF.Claims.CPDPendUpdated")) && (ehfCase.getOrganTransYN()!=null && "Y".equalsIgnoreCase(ehfCase.getOrganTransYN())))			
									)
				{
				saveTechCheckListNew(claimFlowVO);
				}
				if(!(ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.FirstPanelApprove"))
				|| ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.SecondPanelApprove"))
						|| (ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.CPDPendUpdated")) && (ehfCase.getOrganTransYN()!=null && "Y".equalsIgnoreCase(ehfCase.getOrganTransYN())))			
						))
				{
				remarks = claimFlowVO.getClaimPanelRemark();
				if (claimFlowVO.getCpdAprAmt() != null
						&& !claimFlowVO.getCpdAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setCpdAprAmt(Double.parseDouble(claimFlowVO
							.getCpdAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO.getCpdAprAmt());
				}
				ehfCaseClaim.setCpdRemrk(remarks);
				}
				if(ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.FirstPanelApprove"))
				|| ehfCase.getCaseStatus().equalsIgnoreCase(
						config.getString("EHF.Claims.SecondPanelApprove"))
						|| (ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.CPDPendUpdated")) && (ehfCase.getOrganTransYN()!=null && "Y".equalsIgnoreCase(ehfCase.getOrganTransYN())))			
						)
	{
					remarks=claimFlowVO.getClaimPanelRemarkOrg();
				if (claimFlowVO.getCpdAprAmtOrg() != null
						&& !claimFlowVO.getCpdAprAmtOrg().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setSecCpdAmt(Double.parseDouble(claimFlowVO
							.getCpdAprAmtOrg()));
					approveAmt = Double.parseDouble(claimFlowVO.getCpdAprAmtOrg());
				}
				ehfCaseClaim.setSeCpdRmk(claimFlowVO.getClaimPanelRemarkOrg());
				ehfCase.setSecFlag("Y");
	}
				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedAppButton")))
					{
						msg = config.getString("EHF.Claims.MSG.CPDAPP");
						if(ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.COCCEX")))
							msg = config.getString("EHF.Claims.MSG.COCCPDAPP");
						
						if(ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.ORGCEX")))
							msg = config.getString("EHF.Claims.MSG.ORGCPDAPP");    //For organ cpd approve
					}	
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedRejButton")))
					{
						msg = config.getString("EHF.Claims.MSG.CPDREJ");
						if(ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.COCCEX")))
							msg = config.getString("EHF.Claims.MSG.COCCPDREJ");
						if(ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.ORGCEX")))
							msg = config.getString("EHF.Claims.MSG.ORGCPDREJ");   //For organ cpd reject
					}
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedPendButton")))
					{
						msg = config.getString("EHF.Claims.MSG.CPDPED");
						if(ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.COCCEX")))
							msg = config.getString("EHF.Claims.MSG.COCCPDPED");
						if(ehfCase.getCaseStatus().equalsIgnoreCase(
								config.getString("EHF.Claims.Forwarded.By.ORGCEX")))
							msg = config.getString("EHF.Claims.MSG.ORGCPDPED");   //For organ cpd pending
					}
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton"))) 
				{	
					ehfCaseClaim.setCtdRemrk(remarks);
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.CPD")))
						{
							msg = config.getString("EHF.Claims.MSG.CPDPED");
						}
				}	
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.UpdateButton")) && 
						lStrNextStatus.equalsIgnoreCase(config.getString("EHF.Claims.SENDBACKTUPDATEDBYMEDCO")))
				{
					msg = config.getString("EHF.Claims.MSG.EOCOMSENDUPDATED");
					ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
					ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
				}
				
				EhfPanelDocCaseDtls ehfPanelDocCaseDtls= new EhfPanelDocCaseDtls();
				ehfPanelDocCaseDtls.setId(Long.parseLong(getSequence("panel_doc_pmnt_seq")));
				ehfPanelDocCaseDtls.setCaseId(claimFlowVO.getCaseId());
				ehfPanelDocCaseDtls.setDocId(claimFlowVO.getUserId());
				ehfPanelDocCaseDtls.setCasePmntStatus(lStrNextStatus);
				ehfPanelDocCaseDtls.setCaseAppOrPendDate(new java.sql.Timestamp(new Date().getTime()));
				ehfPanelDocCaseDtls.setScheme(ehfCase.getSchemeId());
				ehfPanelDocCaseDtls.setHospId(ehfCase.getCaseHospCode());
				ehfPanelDocCaseDtls.setCrtUsr(claimFlowVO.getUserId());
				ehfPanelDocCaseDtls.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				genericDao.save(ehfPanelDocCaseDtls);
			}
			//vijay for hub dtls
			if ((claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.HUB"))
					&& ehfCase.getCaseStatus().equalsIgnoreCase(
							config.getString("EHF.Claims.Forwarded.HUB")) 
							|| ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.Pending.HUB")))) {
				saveTechCheckList(claimFlowVO);
				remarks = claimFlowVO.getClaimPanelRemark();
				if (claimFlowVO.getCpdAprAmt() != null
						&& !claimFlowVO.getCpdAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setCpdAprAmt(Double.parseDouble(claimFlowVO
							.getCpdAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO.getCpdAprAmt());
				}
				ehfCaseClaim.setCpdRemrk(remarks);
				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedAppButton")))
					{
						msg = config.getString("EHF.Claims.MSG.HUBAPP");
						
					}	
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedRejButton")))
					{
						msg = config.getString("EHF.Claims.MSG.HUBREJ");
						
					}
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton"))) 
				{	
					//ehfCaseClaim.setCtdRemrk(remarks);
					
							msg = config.getString("EHF.Claims.MSG.HUBPEND");
						
				}
				
				
				
			}
			// for saving trust doc checklist
			if ((claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CTD")))||
					claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.COCTD"))||
							claimFlowVO.getRoleId().equalsIgnoreCase(
									config.getString("EHF.Claims.ORGCTD"))
							|| 	ehfCase.getCaseStatus().equalsIgnoreCase(
									config.getString("EHF.Claims.CTDPendNotUpdated"))) {
				saveTrustDocCheckList(claimFlowVO);
				remarks = claimFlowVO.getCtdRemark();
				if (claimFlowVO.getCtdAprAmt() != null
						&& !claimFlowVO.getCtdAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setCtdAprAmt(Double.parseDouble(claimFlowVO
							.getCtdAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO.getCtdAprAmt());
				}

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedAppButton"))) {
					ehfCaseClaim.setCtdRemrk(remarks);
					msg = config.getString("EHF.Claims.MSG.CTDAPP");
					
				} 
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.AppButton")))      //For Cochlear Cases CH Fields are updated with ctd fields
				{
					ehfCaseClaim.setCtdRemrk(remarks);
					//if(claimFlowVO.getCtdAprAmt()!=null && !claimFlowVO.getCtdAprAmt().equalsIgnoreCase(""))
						//ehfCaseClaim.setChEntAprAmt(Double.parseDouble(claimFlowVO.getChEntAprAmt()));
						if(claimFlowVO.getCtdNabhAmt()!=null && !claimFlowVO.getCtdNabhAmt().equalsIgnoreCase(""))
						ehfCaseClaim.setChNabhAmt(Double.parseDouble(claimFlowVO.getCtdNabhAmt()));
					
						if(ehfCase.getCochlearYN()!=null)
							if(ehfCase.getCochlearYN().equalsIgnoreCase("Y"))
								ehfCaseClaim.setChAprAmt(Double.parseDouble(claimFlowVO.getCtdFinAprAmt()));
						
						if(ehfCase.getOrganTransYN()!=null)
							if(ehfCase.getOrganTransYN().equalsIgnoreCase("Y") && claimFlowVO.getCtdFinAprAmt()!=null)
							{
								//logger.info("ctd amount"+claimFlowVO.getCtdFinAprAmt());
								ehfCaseClaim.setChAprAmt(Double.parseDouble(claimFlowVO.getCtdFinAprAmt()));
							}
						EhfClaimAccounts ehfClaimAccounts = new EhfClaimAccounts();
						ehfClaimAccounts.setCaseId(claimFlowVO.getCaseId());
						ehfClaimAccounts.setAprvdAmt(Long.parseLong(claimFlowVO.getCtdFinAprAmt()));
						ehfClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date()
								.getTime()));
						ehfClaimAccounts.setCrtUsr(claimFlowVO.getUserId());
						ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
						
						ehfCase.setCsClAmount(Long.parseLong(claimFlowVO.getCtdFinAprAmt()));
					
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.COCTD")))
						{
							msg = config.getString("EHF.Claims.MSG.COCTDAPP");
							smsMsg = config.getString("EHF.Claims.MSG.COCTD.SMSCLAIM")
								+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN")
								+ lStrHospName + ClaimsConstants.DOT;
						}
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.ORGCTD")))
						{
							msg = config.getString("EHF.Claims.MSG.ORGCTDAPP");
							smsMsg = config.getString("EHF.Claims.MSG.ORGCTD.SMSCLAIM")
								+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN")
								+ lStrHospName + ClaimsConstants.DOT;
						}
					
					if (claimFlowVO.getCtdFinAprAmt() != null
							&& !claimFlowVO.getCtdFinAprAmt().equalsIgnoreCase(
									ClaimsConstants.EMPTY)) {
						ehfCaseClaim.setCtdAprAmt(Double.parseDouble(claimFlowVO
								.getCtdFinAprAmt()));
						
						approveAmt = Double.parseDouble(claimFlowVO.getCtdFinAprAmt());
					}
				}
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedRejButton"))) {
					ehfCaseClaim.setCtdRemrk(remarks);
					msg = config.getString("EHF.Claims.MSG.CTDREJ");
				} 
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RejButton"))) 
				{
					ehfCaseClaim.setCtdRemrk(remarks);
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.COCTD")))
						{
							msg = config.getString("EHF.Claims.MSG.COCTDREJ");
							smsMsg = config.getString("EHF.Claims.MSG.COCTD.SMSREJ")
								+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
								+ ClaimsConstants.DOT;
						}
					//For organ transplant TD reject button
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.ORGCTD")))
						{
							msg = config.getString("EHF.Claims.MSG.ORGCTDREJ");
							smsMsg = config.getString("EHF.Claims.MSG.ORGCTD.SMSREJ")
								+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
								+ ClaimsConstants.DOT;
						}
				}	
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedPendButton"))) {
					ehfCaseClaim.setCtdRemrk(remarks);
					msg = config.getString("EHF.Claims.MSG.CTDPED");
				} 
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton"))) 
				{	
					ehfCaseClaim.setCtdRemrk(remarks);
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.CTD")))
						{
							msg = config.getString("EHF.Claims.MSG.CTDPED");
						}
				//For Cochlear TD Pending
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.COCTD")))
						{
							msg = config.getString("EHF.Claims.MSG.COCTDPED");
							smsMsg = "Cochlear Kept Claim Pending for Case no: "
									+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
									+ ClaimsConstants.DOT;
						}
					//FOR organ TD pending button
					if(claimFlowVO.getRoleId().equalsIgnoreCase(
							config.getString("EHF.Claims.ORGCTD")))
						{
							msg = config.getString("EHF.Claims.MSG.ORGCTDPED");
							smsMsg = "Organ transplantation Claim kept Pending for Case no: "
									+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
									+ ClaimsConstants.DOT;
						}
				}	
				
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.Claims.DiscussionButton"))) {
					approveAmt = (double) 0;
					msg = config.getString("EHF.Claims.MSG.CTDDISK");
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.Claims.ClearButton"))) {
					approveAmt = (double) 0;
					msg = config.getString("EHF.Claims.MSG.CTDDISC");
				}
				}
			//for saving CH entered details
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CH"))) {

				remarks = claimFlowVO.getChRemark();

				if (claimFlowVO.getChEntAprAmt() != null
						&& !claimFlowVO.getChEntAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setChAprAmt(Double.parseDouble(claimFlowVO
							.getChEntAprAmt()));
					
					approveAmt = Double.parseDouble(claimFlowVO.getChEntAprAmt());
				}
				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.AppButton"))) {
					ehfCaseClaim.setChRemrk(remarks);
					if(claimFlowVO.getChEntAprAmt()!=null && !claimFlowVO.getChEntAprAmt().equalsIgnoreCase(""))
					ehfCaseClaim.setChEntAprAmt(Double.parseDouble(claimFlowVO.getChEntAprAmt()));
					if(claimFlowVO.getChNabhAmt()!=null && !claimFlowVO.getChNabhAmt().equalsIgnoreCase(""))
					ehfCaseClaim.setChNabhAmt(Double.parseDouble(claimFlowVO.getChNabhAmt()));
					
					if (approveAmt > 200000) {
						lStrNextStatus = config
								.getString("EHF.Claims.RecomedToEO");
						claimFlowVO.setCaseNextStat(lStrNextStatus);
						ehfCase.setCeoApprovalFlag("Y");
						msg = config.getString("EHF.Claims.MSG.CHAPP");
						smsMsg = config.getString("EHF.Claims.MSG.SMSCLAIM")
								+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN")
								+ lStrHospName + ClaimsConstants.DOT;
					} else {
						msg = config.getString("EHF.Claims.MSG.CHAPP");
						smsMsg = config.getString("EHF.Claims.MSG.SMSCLAIM")
								+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN")
								+ lStrHospName + ClaimsConstants.DOT;
					}
					
					if (claimFlowVO.getChAprAmt() != null
							&& !claimFlowVO.getChAprAmt().equalsIgnoreCase(
									ClaimsConstants.EMPTY)) {
						ehfCaseClaim.setChAprAmt(Double.parseDouble(claimFlowVO
								.getChAprAmt()));
						
						approveAmt = Double.parseDouble(claimFlowVO.getChAprAmt());
					}			
					
					EhfClaimAccounts ehfClaimAccounts = new EhfClaimAccounts();
					ehfClaimAccounts.setCaseId(claimFlowVO.getCaseId());
					ehfClaimAccounts.setPatientScheme(ehfCase.getPatientScheme());
					if(claimFlowVO.getChAprAmt()!=null)
					ehfClaimAccounts.setAprvdAmt(Long.parseLong(claimFlowVO.getChAprAmt()));
					ehfClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date()
							.getTime()));
					ehfClaimAccounts.setCrtUsr(claimFlowVO.getUserId());
					ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
					if(claimFlowVO.getChAprAmt()!=null)
					ehfCase.setCsClAmount(Long.parseLong(claimFlowVO.getChAprAmt()));
					
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RejButton"))) {
					msg = config.getString("EHF.Claims.MSG.CHREJ");
					ehfCaseClaim.setChRemrk(remarks);
					smsMsg = config.getString("EHF.Claims.MSG.SMSREJ")
							+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
							+ ClaimsConstants.DOT;
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton"))) {
					ehfCaseClaim.setChRemrk(remarks);
					msg = config.getString("EHF.Claims.MSG.CHPED");
					/*smsMsg = "Claim Pending by Claim Head for Case no: "
							+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
							+ ClaimsConstants.DOT;*/
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.VerifyButton"))) {
					ehfCaseClaim.setChRemrk(remarks);
					
					if(claimFlowVO.getChEntAprAmt()!=null && !claimFlowVO.getChEntAprAmt().equalsIgnoreCase(""))
						ehfCaseClaim.setChEntAprAmt(Double.parseDouble(claimFlowVO.getChEntAprAmt()));
						if(claimFlowVO.getChNabhAmt()!=null && !claimFlowVO.getChNabhAmt().equalsIgnoreCase(""))
						ehfCaseClaim.setChNabhAmt(Double.parseDouble(claimFlowVO.getChNabhAmt()));
					
					if(claimFlowVO.getChAprAmt()!=null)
							if (claimFlowVO.getChAprAmt() != null
									&& !claimFlowVO.getChAprAmt().equalsIgnoreCase(
											ClaimsConstants.EMPTY)) {
								ehfCaseClaim.setChAprAmt(Double.parseDouble(claimFlowVO
										.getChAprAmt()));
					ehfCaseClaim.setTotClaimAmt(Double.parseDouble(claimFlowVO
							.getChAprAmt()));
						long x = (long) Double.parseDouble(claimFlowVO.getChAprAmt());
						ehfCase.setCsClAmount(x);
							}
					msg = config.getString("EHF.Claims.MSG.CHVER");
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.Claims.DiscussionButton"))) {
					approveAmt = (double) 0;
					msg = config.getString("EHF.Claims.MSG.CHDISK");
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.Claims.ClearButton"))) {
					approveAmt = (double) 0;
					msg =config.getString("EHF.Claims.MSG.CHDISC");
				}
				}
			//for saving EO entered details
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.EO"))) {

				remarks = claimFlowVO.getEoRemark();
				if (claimFlowVO.getEoAprAmt() != null
						&& !claimFlowVO.getEoAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setEoAprAmt(Double.parseDouble(claimFlowVO
							.getEoAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO.getEoAprAmt());
				}
				ehfCaseClaim.setEoRemrk(remarks);

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.AppButton"))) {
					msg = config.getString("EHF.Claims.MSG.EOAPP");
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RejButton"))) {
					msg = config.getString("EHF.Claims.MSG.EOREJ");
				}
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton"))) {
					msg = config.getString("EHF.Claims.MSG.EOPEN");
				}
			}
			//for saving EO-comm entered details
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.EOComm"))) {

				remarks = claimFlowVO.getEoComRemark();
				if (claimFlowVO.getEoComEntAprAmt() != null
						&& !claimFlowVO.getEoComEntAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setEoComAprAmt(Double.parseDouble(claimFlowVO
							.getEoComEntAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO
							.getEoComEntAprAmt());
				}
				ehfCaseClaim.setEoComRemrk(remarks);

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.AppButton"))) {

					if (claimFlowVO.getEoComEntAprAmt() != null
							&& !claimFlowVO.getEoComEntAprAmt().equalsIgnoreCase(
									""))
						ehfCaseClaim.setEoComEntAprAmt(Double
								.parseDouble(claimFlowVO.getEoComEntAprAmt()));
					if (claimFlowVO.getEoComNabhAmt() != null
							&& !claimFlowVO.getEoComNabhAmt().equalsIgnoreCase(""))
						ehfCaseClaim.setEoComNabhAmt(Double
								.parseDouble(claimFlowVO.getEoComNabhAmt()));
						
					if (claimFlowVO.getEoComAprAmt() != null
							&& !claimFlowVO.getEoComAprAmt().equalsIgnoreCase(
									ClaimsConstants.EMPTY)) {
						ehfCaseClaim.setEoComAprAmt(Double.parseDouble(claimFlowVO
								.getEoComAprAmt()));
						approveAmt = Double.parseDouble(claimFlowVO
								.getEoComAprAmt());
					}
					
					EhfClaimAccounts ehfClaimAccounts = genericDao.findById(
							EhfClaimAccounts.class, String.class,
							claimFlowVO.getCaseId());
					ehfClaimAccounts.setAprvdAmt(Long.parseLong(claimFlowVO
							.getEoComAprAmt()));
					ehfClaimAccounts.setLstUpdDt(new java.sql.Timestamp(
							new Date().getTime()));
					ehfClaimAccounts.setLstUpdUsr(claimFlowVO.getUserId());
					ehfClaimAccounts = genericDao.save(ehfClaimAccounts);

					ehfCase.setCsClAmount(Long.parseLong(claimFlowVO
							.getEoComAprAmt()));
					
					msg = config.getString("EHF.Claims.MSG.EOCOMAPP");
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RejButton"))) {
					msg = config.getString("EHF.Claims.MSG.EOCOMREJ");
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.SendTo"))) {
					msg = config.getString("EHF.Claims.MSG.EOCOMSEND");
					//ehfCase.setPaneldocuserid(claimFlowVO.getSennBackRole());
					ehfCase.setPaneldocuserid(claimFlowVO.getSennBackRole());
					ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
					ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
				}
				
			}
			//for saving ACO entered details
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.ACO"))) {
                
				remarks = claimFlowVO.getAcoRemark();
				if (ehfCaseClaim.getEoComAprAmt() != null)
					claimFlowVO.setAcoAprAmt(ehfCaseClaim.getEoComAprAmt()
							.toString());
				else if (ehfCaseClaim.getChAprAmt() != null)					
					claimFlowVO.setAcoAprAmt(ehfCaseClaim.getChAprAmt()
							.toString());

				if (claimFlowVO.getAcoAprAmt() != null
						&& !claimFlowVO.getAcoAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setAcoAprAmt(Double.parseDouble(claimFlowVO
							.getAcoAprAmt()));
					long x = (long) Double.parseDouble(claimFlowVO.getAcoAprAmt());
					ehfCase.setCsClAmount(x);
					approveAmt = Double.parseDouble(claimFlowVO.getAcoAprAmt());

					ehfCaseClaim.setTotClaimAmt(Double.parseDouble(claimFlowVO
							.getAcoAprAmt()));
				}
				ehfCaseClaim.setAcoRemrk(remarks);

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.VerifyButton"))) {

					EhfClaimAccounts ehfClaimAccounts = genericDao.findById(
							EhfClaimAccounts.class, String.class,
							claimFlowVO.getCaseId());

					ehfClaimAccounts.setRemarks(remarks);
					ehfClaimAccounts
							.setTransStatus(ClaimsConstants.TransReadyStatus);
					ehfClaimAccounts.setTimeMilSec((long) 0);
					ehfClaimAccounts.setLstUpdDt(new java.sql.Timestamp(
							new Date().getTime()));
					ehfClaimAccounts.setLstUpdUsr(claimFlowVO.getUserId());
					ehfClaimAccounts = genericDao.save(ehfClaimAccounts);

					msg = config.getString("EHF.Claims.MSG.ACOVER");
					smsMsg = config.getString("EHF.Claims.MSG.SMSACOVER")
							+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
							+ ClaimsConstants.DOT;
				}
			}
			//for saving MEDCO entered details
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.MEDCO"))
					) {
				
				if (claimFlowVO.getBillAmt() != null
						&& !claimFlowVO.getBillAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfCaseClaim.setClaimBillAmt(Double.parseDouble(claimFlowVO
							.getBillAmt()));
					approveAmt = Double.parseDouble(claimFlowVO.getBillAmt());
				}
				if (claimFlowVO.getClaimInitAmt() != null
						&& !claimFlowVO.getClaimInitAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
				ehfCaseClaim.setClaimInitAmt(claimFlowVO.getClaimInitAmt());
				}
				ehfCaseClaim.setMedcoRemrk(claimFlowVO.getMedcoRemark());
				ehfCaseClaim
						.setClaimBillDate(sdf.parse(claimFlowVO.getBillDt()));
				remarks = claimFlowVO.getMedcoRemark();
				if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.CHPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.MEDPEDVER");
					smsMsg = config.getString("EHF.Claims.MSG.SMSMEDPEDVER")
							+ claimFlowVO.getCaseId()
							+ config.getString("EHF.Claims.MSG.SMSCLAIMIN")
							+ lStrHospName
							+ ClaimsConstants.DOT;
					ehfCase.setClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
				}
				else if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.CPDPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.CPDPENDUPDATE");
					ehfCase.setClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
				}
				else if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.Pending.HUB"))) {
					msg = config.getString("EHF.Claims.MSG.HUBPENDUPDATE");
					ehfCase.setClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
				}
				
				else if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.CTDPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.CTDPENDUPDATE");
					ehfCase.setClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
				}
				else if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.EOPendUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.EOPENDUPDATE");
					ehfCase.setClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
				}
				else if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.claims.CEOPendingUpdated"))) {
					msg = config.getString("EHF.Claims.MSG.CEOPENDUPDATE");
				}
				else {
					ehfCase.setClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
					ehfCase.setActualClmSubDt(df.parse(claimFlowVO.getClaimSubDt()));
					smsMsg = config.getString("EHF.Claims.MSG.SMSMEDINIT")
							+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
							+ ClaimsConstants.DOT;
					msg = config.getString("EHF.Claims.MSG.MEDINIT");
					if(msg.equalsIgnoreCase(config.getString("EHF.Claims.MSG.MEDINIT")))
					{
						if(ehfCase.getCochlearYN()!=null)
						{
							if(ehfCase.getCochlearYN().equalsIgnoreCase("Y"))
							msg="Cochlear "+msg;
						}
						if(ehfCase.getOrganTransYN()!=null)
						{
							if(ehfCase.getOrganTransYN().equalsIgnoreCase("Y"))
							msg="Organ Transplantation "+msg;
						}
					}	
				}
			}
			//for saving CEO entered details while sent back to Ch
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CEO"))) {

				remarks = claimFlowVO.getCeoRemark();
				ehfCaseClaim.setCeoRemark(remarks);

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.SentBackButton"))) {
					msg = config.getString("EHF.Claims.MSG.CHSENTBCK");
					smsMsg = config.getString("EHF.Claims.MSG.SMSCHBCK")
							+ claimFlowVO.getCaseId()
							+ config.getString("EHF.Claims.MSG.SMSCLAIMIN")
							+ lStrHospName
							+ ClaimsConstants.DOT;
				}
			}

			claimFlowVO.setSmsMsg(smsMsg);
		

			ehfCase.setCaseStatus(lStrNextStatus);
			ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
			ehfCase.setViewFlag("N");
			ehfCase = genericDao.save(ehfCase);

			ehfCaseClaim = genericDao.save(ehfCaseClaim);

			// insert into asrit_audit
			EhfAudit asritAudit = new EhfAudit();
			EhfAuditId asritAuditPK = new EhfAuditId(lActOrder,
					claimFlowVO.getCaseId());
			asritAudit.setId(asritAuditPK);
			asritAudit.setActId(lStrNextStatus);
			asritAudit.setActBy(claimFlowVO.getUserId());
			asritAudit.setCrtUsr(claimFlowVO.getUserId());
			asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			asritAudit.setLangId(ClaimsConstants.LANG_ID);
			asritAudit.setRemarks(remarks);
			asritAudit.setApprvAmt(approveAmt);
			asritAudit.setUserRole(claimFlowVO.getRoleId());

			genericDao.save(asritAudit);

			 /**
	          * Added By Sravan to Save the Actual Treated Units for Dental Surgery Case at the time of CTD Approval 
	          */
	         if(claimFlowVO.getDentCond()!=null && "Y".equalsIgnoreCase(claimFlowVO.getDentCond())
	         		&& claimFlowVO.getCaseUnits()!=null && !"".equalsIgnoreCase(claimFlowVO.getCaseUnits()) 
	         		&& !"".equalsIgnoreCase(claimFlowVO.getActionDone()) && ("CD32".equalsIgnoreCase(claimFlowVO.getActionDone()) || "CD24".equalsIgnoreCase(claimFlowVO.getActionDone())))
	         	{
	        	 
	         		String[] mainList = claimFlowVO.getCaseUnits().split("~");
	         		//String[] toothedList = preauthClinicalNotesVO.getToothedUnits().split("~");
	         		for (int i=0;i<mainList.length;i++)
	         			{
	         				String[] caseThe=mainList[i].split("@");
	         				//String[] toothedUnits=toothedList[i].split("@");
	         				if(caseThe!=null && caseThe[0]!=null && caseThe[1]!=null)
	         					{
	         						EhfCaseTherapy ehfCaseTherapy=genericDao.findById(EhfCaseTherapy.class, Long.class, Long.parseLong(caseThe[0]));
	         						if(ehfCaseTherapy!=null)
	         							{
	         								//if(toothedUnits.length>1 && toothedUnits[0]!=null && toothedUnits[1]!=null && toothedUnits[0].contains(caseThe[0]))
	         								//	ehfCaseTherapy.setToothedUnits(toothedUnits[1]);
	         								
	         								if(!caseThe[1].equalsIgnoreCase("-1") && (!"".equalsIgnoreCase(claimFlowVO.getRoleId()) && "GP8".equalsIgnoreCase(claimFlowVO.getRoleId())))
	         								{
	         									ehfCaseTherapy.setSurgProcUnits(caseThe[1]);
	         									ehfCaseTherapy.setCtdProcUnits(caseThe[1]);
	         									ehfCaseTherapy.setLstUpdUsr(claimFlowVO.getUserId());
	         									ehfCaseTherapy.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
	         								}
	         								if(!caseThe[1].equalsIgnoreCase("-1") && (!"".equalsIgnoreCase(claimFlowVO.getRoleId()) && "GP9".equalsIgnoreCase(claimFlowVO.getRoleId())))
	         								{
	         									ehfCaseTherapy.setSurgProcUnits(caseThe[1]);
	         									ehfCaseTherapy.setChProcUnits(caseThe[1]);
	         									ehfCaseTherapy.setLstUpdUsr(claimFlowVO.getUserId());
	         									ehfCaseTherapy.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
	         								}
	         								ehfCaseTherapy=genericDao.save(ehfCaseTherapy);
	         							}
	         					}
	         			}
	         	} 
			
			if (ehfCase != null)
				claimFlowVO.setMsg(msg);
			else
				claimFlowVO.setMsg(ClaimsConstants.Failure);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured in saveClaim() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			claimFlowVO.setMsg(ClaimsConstants.Failure);
		}
		
		return claimFlowVO;
	}
	/*
	 * Added by Sravan for getting total price in CTD & CH Logins 
	 */
	
	private void saveTechCheckListNew(ClaimsFlowVO claimFlowVO) {
		// TODO Auto-generated method stub
		EhfClaimTechChklstNew techCheckLst;

		EhfClaimTechChklstNew techCheckLstExist = genericDao
				.findById(EhfClaimTechChklstNew.class, String.class,
						claimFlowVO.getCaseId());
		if (techCheckLstExist != null)
			techCheckLst = techCheckLstExist;
		else
			techCheckLst = new EhfClaimTechChklstNew();
		try {
			techCheckLst.setCaseId(claimFlowVO.getCaseId());
			techCheckLst.setTotalClaim(claimFlowVO.getTotalClaim());
			techCheckLst.setDedRecomd(claimFlowVO.getDeductionOrg());
			techCheckLst.setStay(claimFlowVO.getStay());
			techCheckLst.setStayRemark(claimFlowVO.getStayRemarkOrg());
			techCheckLst.setInput(claimFlowVO.getInputsOrg());
			techCheckLst.setInputRemark(claimFlowVO.getInputsRmrkOrg());
			techCheckLst.setProfFeeBill(claimFlowVO.getProfFeeOrg());
			techCheckLst.setProfFeeRemark(claimFlowVO.getProfFeeRmrkOrg());
			techCheckLst.setInvestBill(claimFlowVO.getInvestBillOrg());
			techCheckLst.setInvestBillRemark(claimFlowVO.getInvestBillRmrkOrg());
			techCheckLst.setDiagnosisYn(claimFlowVO.getTechCheckOrg1());
			techCheckLst.setCasemgmtYn(claimFlowVO.getTechCheckOrg2());
			techCheckLst.setEvidenceYn(claimFlowVO.getTechCheckOrg3());
			techCheckLst.setMandatoryYn(claimFlowVO.getTechCheckOrg4());
			techCheckLst.setCpdRemark(claimFlowVO.getClaimPanelRemarkOrg());
			techCheckLst.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			techCheckLst.setCrtUsr(claimFlowVO.getUserId());
			genericDao.save(techCheckLst);
		} catch (Exception e) {
			logger.error("Error occured in saveTechCheckList() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		
	}
	@Override
	public ClaimsFlowVO getTotalPrice(ClaimsFlowVO claimFlowVO) throws Exception {
		String result="failure";
		StringBuffer query= new StringBuffer();
		Long nabhAmt= 0L;
		Long surgAmt=0L;
		Long dopTeethPrice=0L,deductAmount=0L;
		Long hospStayAmount=0L;
		Long hospStayAmountDOP=0L;
		Long commonCatAmount=0L;
		Long noOfDays=0L;
		Long noOfDaysDOP=0L;
		String tempCategory="";
		Long totAmt=0L;
		float comorbidAmt = 0;
		
		try
		{
			if(claimFlowVO.getDentCond()!=null && "Y".equalsIgnoreCase(claimFlowVO.getDentCond())
	         		&& claimFlowVO.getCaseUnitsPar()!=null && !"".equalsIgnoreCase(claimFlowVO.getCaseUnitsPar()) )
	         		
	         	{
	        	 
	         		String[] mainList = claimFlowVO.getCaseUnitsPar().split("~");
	         		for (int i=0;i<mainList.length;i++)
	         			{
	         				String[] caseThe=mainList[i].split("@");
	         				if(caseThe!=null && caseThe[0]!=null && caseThe[1]!=null)
	         					{
	         						EhfCaseTherapy ehfCaseTherapy=genericDao.findById(EhfCaseTherapy.class, Long.class, Long.parseLong(caseThe[0]));
	         						if(ehfCaseTherapy!=null)
	         							{
	         								
	         								if(!caseThe[1].equalsIgnoreCase("-1") && (!"".equalsIgnoreCase(claimFlowVO.getRoleId()) && "GP8".equalsIgnoreCase(claimFlowVO.getRoleId())))
	         								{
	         									ehfCaseTherapy.setUnitsPartial(caseThe[1]);
	         									
	         								}
	         								if(!caseThe[1].equalsIgnoreCase("-1") && (!"".equalsIgnoreCase(claimFlowVO.getRoleId()) && "GP9".equalsIgnoreCase(claimFlowVO.getRoleId())))
	         								{
	         									ehfCaseTherapy.setUnitsPartial(caseThe[1]);
	         								}
	         								ehfCaseTherapy=genericDao.save(ehfCaseTherapy);
	         								result="success";
	         							}
	         					}
	         			}
	         	
		
	         		if(!"".equalsIgnoreCase(result) && "success".equalsIgnoreCase(result))
					 {
						 
							if(claimFlowVO.getHospStayAmt()!=null && !"".equalsIgnoreCase(claimFlowVO.getHospStayAmt()))
								nabhAmt= Long.parseLong(claimFlowVO.getHospStayAmt());
							/**
							 * Get the list of all active therapies
							 */
							
					    	query.append(" select distinct emt.hospstayAmt as hospStayAmt, emt.commonCatAmt as commonCatAmt, emt.icdAmt as icdAmt,ee.schemeId as scheme, ");
					    	query.append(" emt.noOfDays as noOfDays, emt.id.asriCode as test, emt.id.icdProcCode as testKnown, emt.procName as ipOpFlag, ect.procUnits as opProcUnits ");
					    	query.append(" ,emt.id.process as process, ect.unitsPartial as caseUnitsPar ");
					    	query.append(" from EhfmTherapyProcMst emt, EhfCaseTherapy ect,EhfCase ee  ");
					    	query.append(" where ect.caseId = ? and ect.activeyn = ? and emt.id.asriCode = ect.asriCatCode  ");
					    	query.append(" and ee.caseId=ect.caseId and emt.id.state=ee.schemeId ");
					    	query.append(" and emt.id.icdProcCode = ect.icdProcCode and emt.icdCatCode = ect.icdCatCode order by  emt.id.asriCode ");
					    	
					    	String[] args=new String[2];
					    	args[0]=claimFlowVO.getCaseId();
					    	args[1]="Y";
					    	
					    	List<ClaimsFlowVO> surgList= genericDao.executeHQLQueryList(ClaimsFlowVO.class, query.toString(), args);
					    	
					    	String str="";
					    	List<String> list=new ArrayList<String>();
					    	
					    	if(surgList!=null && surgList.size()>0)
					    	{
					    		for(int i=0; i<surgList.size(); i++)
					    		{
					    			
					    			if(surgList.get(i)!=null)
					    				{
					    					if(surgList.get(i).getTestKnown()!=null 
					    							&& surgList.get(i).getTest()!=null 
					    							&& surgList.get(i).getScheme()!=null 
					    							&& surgList.get(i).getCaseUnitsPar()!=null 
													&& !"".equalsIgnoreCase(surgList.get(i).getCaseUnitsPar())
													&& !"-1".equalsIgnoreCase(surgList.get(i).getCaseUnitsPar())
													&& surgList.get(i).getTest().equalsIgnoreCase(config.getString("DentalSurgeryID"))
					    							&& surgList.get(i).getScheme().equalsIgnoreCase(config.getString("TG")))
					    						{
					    							EhfmDentalProcCriteria ehfmDentalProcCriteria =new EhfmDentalProcCriteria();
					    							EhfmDentalProcCriteriaPK ehfmDentalProcCriteriaPK=new EhfmDentalProcCriteriaPK();
					    							ehfmDentalProcCriteriaPK.setIcdProcCode(surgList.get(i).getTestKnown());
					    							ehfmDentalProcCriteriaPK.setSchemeId(surgList.get(i).getScheme());
					    							ehfmDentalProcCriteria = genericDao.findById(EhfmDentalProcCriteria.class, EhfmDentalProcCriteriaPK.class, ehfmDentalProcCriteriaPK);
					    							if(ehfmDentalProcCriteria!=null && ehfmDentalProcCriteria.getActiveYn()!=null 
					    									&& "Y".equalsIgnoreCase(ehfmDentalProcCriteria.getActiveYn()))
					    								{
					    									if(ehfmDentalProcCriteria.getFrameworkPrice()!=null
					    											&& !"".equalsIgnoreCase(ehfmDentalProcCriteria.getFrameworkPrice())
					    											&& !"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getFrameworkPrice()))
					    										{
					    											int opUnits=Integer.parseInt(surgList.get(i).getCaseUnitsPar());
					    											Long frameWorkPrice=Long.parseLong(ehfmDentalProcCriteria.getFrameworkPrice());
					    											//If Subsequent Price Available
					    											if(ehfmDentalProcCriteria.getSubsequentPrice()!=null && 
					    												!"".equalsIgnoreCase(ehfmDentalProcCriteria.getSubsequentPrice()) &&
					    												!"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getSubsequentPrice()))
					    												{
					    													Long subsequentPrice=Long.parseLong(ehfmDentalProcCriteria.getSubsequentPrice());
					    													
					    													//Frame Work Price for First two Units and Subsequent Price for other Units
					    													if(opUnits>2)
				    															dopTeethPrice=dopTeethPrice+(2*frameWorkPrice)+((opUnits-2)*subsequentPrice);
					    													else
					    														dopTeethPrice=dopTeethPrice+(opUnits*frameWorkPrice);
					    														
					    												}
					    											//If Subsequent Price not Available
					    											else
					    												{
					    													dopTeethPrice=dopTeethPrice+((opUnits)*frameWorkPrice);
					    												}
					    											continue;
					    										}
					    								
					    								}
					    						}
					    				}
					    			//End of Code added by Srikalyan for Dental TG Special Conditions
					    			
					    			
					    			// Get the sum of all icd procedure amounts
					        		if(surgList.get(i).getIcdAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getIcdAmt()))
					    			{
					        			if(surgList.get(i).getCaseUnitsPar()!=null && !"".equalsIgnoreCase(surgList.get(i).getCaseUnitsPar()) && !"-1".equalsIgnoreCase(surgList.get(i).getCaseUnitsPar()))
					        				surgAmt= surgAmt+ (Long.parseLong(surgList.get(i).getIcdAmt()) * Long.parseLong(surgList.get(i).getCaseUnitsPar()));
					        			else
					        				surgAmt= surgAmt+ Long.parseLong(surgList.get(i).getIcdAmt());
					    			}
					        		//End-Get the sum of all icd pocedure amounts
					        		
					    			if(i==0)
					    			{
					    				if(surgList.get(i).getNoOfDays()!=null && Long.parseLong(surgList.get(i).getCaseUnitsPar())>0)
					    					noOfDays= surgList.get(i).getNoOfDays().longValue();
					    				
					    				if(surgList.get(i).getProcess()!=null && !"".equalsIgnoreCase(surgList.get(i).getProcess()))
					    				{
						    				if(!surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
					    						{
								    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()) && Long.parseLong(surgList.get(i).getCaseUnitsPar())>0)
								    					hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
					    						}
						    				if(surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
												{
							    					if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
								    					{
							    							hospStayAmountDOP= (Long.parseLong(surgList.get(i).getHospStayAmt()) * Long.parseLong(surgList.get(i).getCaseUnitsPar()));
								    					}
												}
					    				}
					    				
					    			}
					    			if(i>0)
					    			{
					    				// Get the maximum No of Days
					    				if(surgList.get(i).getNoOfDays()!=null && Long.parseLong(surgList.get(i).getCaseUnitsPar())>0)
					    				{
					    					if(noOfDays<surgList.get(i).getNoOfDays().longValue())
					    						noOfDays= surgList.get(i).getNoOfDays().longValue();
					    				}
					    				
					    				
					    				if(surgList.get(i).getProcess()!=null && !"".equalsIgnoreCase(surgList.get(i).getProcess()))
					    				{
					    					if(!surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
					    					{	
							    				// Get the maximum Hospital Stay Amount non DOPS
							    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
								    			{
								    				if(hospStayAmount< Long.parseLong(surgList.get(i).getHospStayAmt()) &&  Long.parseLong(surgList.get(i).getCaseUnitsPar())>0)
								    				hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
								    			}	
							    				// End-Get the maximum Hospital Stay Amount
					    					}
					    					
					    					if(surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
					    					{
					    						// Get No of Days for each DOP
					    						if(surgList.get(i).getNoOfDays()!=null)
							    				{
							    						noOfDaysDOP = noOfDaysDOP + surgList.get(i).getNoOfDays().longValue();
							    				}
					    						//End-Get No of Days for eachy DOP
					    						
					    						// Get the Hospital Stay Amount for each DOP
							    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt())
							    						&& surgList.get(i).getCaseUnitsPar()!=null && !"".equalsIgnoreCase(surgList.get(i).getCaseUnitsPar()) && !"-1".equalsIgnoreCase(surgList.get(i).getCaseUnitsPar()))
								    			{
								    				hospStayAmountDOP = hospStayAmountDOP + (Long.parseLong(surgList.get(i).getHospStayAmt()) * Long.parseLong(surgList.get(i).getCaseUnitsPar()));
								    			}	
							    				// End-Get Hospital Stay Amount for each DOP
					    					}
					    				}	
					    				
					    			}
					    			if(surgList.get(i).getTest()!=null && !"".equalsIgnoreCase(surgList.get(i).getTest()) && !tempCategory.equalsIgnoreCase(surgList.get(i).getTest()) )
					    			{
					    				if(i==0)
					    				{
					    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
					    						str= surgList.get(i).getCommonCatAmt()+"~";
					    				}
					    				else
					    				{
					    					list.add(str);
					    					str="";
					    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
					    						str= str+ surgList.get(i).getCommonCatAmt()+"~";
					    				}
					    			}
					    			else
					    			{
					    				if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
				    						str= str+ surgList.get(i).getCommonCatAmt()+"~";
					    			}
					    			if(i==surgList.size()-1)
					    			{
					    				list.add(str);
					    			}
					    			if(surgList.get(i).getTest()!=null)
					    				tempCategory= surgList.get(i).getTest();
					    		}
					    	}
					    	for(int len=0; len<list.size(); len++)
					    	{
					    		if(list.get(len)!=null && !"".equalsIgnoreCase(list.get(len)))
					    		{
					    			Long maxCatAmt=0L;
					    			String arr[]= list.get(len).split("~");
					    			for(int i=0; i<arr.length; i++)
					    			{
					    				if(arr[i]!=null && !"".equalsIgnoreCase(arr[i]))
					    				{
					    					if(i==0)
					    					{
					    						maxCatAmt= Long.parseLong(arr[i]);
					    					}
					    					else
					    					{
					    						if(maxCatAmt< Long.parseLong(arr[i]))
					    								maxCatAmt= Long.parseLong(arr[i]);
					    					}
					    				}
					    			}
					    			commonCatAmount= commonCatAmount+maxCatAmt;
					    		}
					    	}
					    	hospStayAmount = hospStayAmount + hospStayAmountDOP;
					    	
					    	totAmt= surgAmt+hospStayAmount+commonCatAmount+(noOfDays*nabhAmt);
					    	//dopTeethPrice would be >0 for only TG Dental Special Proc's
					    	
					    	if(dopTeethPrice>0)
					    		totAmt=totAmt+dopTeethPrice;
					    	
					    	claimFlowVO.setTotPackgAmt(totAmt.toString());
					 }
	 			}
		}	
		 catch(Exception e)
		 {
				e.printStackTrace();
				//logger.error("Error occured in getTotalPrice() in ClaimsFlowDAOImpl class."+ e.getMessage());
		 }
		return claimFlowVO;
	}
	/**
	 * Save trust doc check list.
	 * 
	 * @param claimFlowVO
	 *            the claim flow vo
	 */
	private void saveTrustDocCheckList(ClaimsFlowVO claimFlowVO) {
		EhfClaimCtdChklst ctdCheckLst;

		EhfClaimCtdChklst ctdCheckLstExist = genericDao.findById(
				EhfClaimCtdChklst.class, String.class, claimFlowVO.getCaseId());
		if (ctdCheckLstExist != null)
			ctdCheckLst = ctdCheckLstExist;
		else
			ctdCheckLst = new EhfClaimCtdChklst();

		try {
			ctdCheckLst.setAgreeYn(claimFlowVO.getTrustDoc1());
			ctdCheckLst.setCaseId(claimFlowVO.getCaseId());
			ctdCheckLst.setCasemgmtYn(claimFlowVO.getTrustDoc2());
			ctdCheckLst.setEvidenceYn(claimFlowVO.getTrustDoc3());
			ctdCheckLst.setMandatoryYn(claimFlowVO.getTrustDoc4());
			ctdCheckLst.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			ctdCheckLst.setCrtUsr(claimFlowVO.getUserId());
			ctdCheckLst.setRemarks(claimFlowVO.getCtdRemark());
			genericDao.save(ctdCheckLst);
		} catch (Exception e) {
			logger.error("Error occured in saveTrustDocCheckList() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Save tech check list.
	 * 
	 * @param claimFlowVO
	 *            the claim flow vo
	 */
	private void saveTechCheckList(ClaimsFlowVO claimFlowVO) {
		EhfClaimTechChklst techCheckLst;

		EhfClaimTechChklst techCheckLstExist = genericDao
				.findById(EhfClaimTechChklst.class, String.class,
						claimFlowVO.getCaseId());
		if (techCheckLstExist != null)
			techCheckLst = techCheckLstExist;
		else
			techCheckLst = new EhfClaimTechChklst();
		try {
			techCheckLst.setCaseId(claimFlowVO.getCaseId());
			techCheckLst.setTotalClaim(claimFlowVO.getTotalClaim());
			techCheckLst.setDedRecomd(claimFlowVO.getDeduction());
			techCheckLst.setStay(claimFlowVO.getStay());
			techCheckLst.setStayRemark(claimFlowVO.getStayRemark());
			techCheckLst.setInput(claimFlowVO.getInputs());
			techCheckLst.setInputRemark(claimFlowVO.getInputsRmrk());
			techCheckLst.setProfFeeBill(claimFlowVO.getProfFee());
			techCheckLst.setProfFeeRemark(claimFlowVO.getProfFeeRmrk());
			techCheckLst.setInvestBill(claimFlowVO.getInvestBill());
			techCheckLst.setInvestBillRemark(claimFlowVO.getInvestBillRmrk());
			techCheckLst.setDiagnosisYn(claimFlowVO.getTechCheck1());
			techCheckLst.setCasemgmtYn(claimFlowVO.getTechCheck2());
			techCheckLst.setEvidenceYn(claimFlowVO.getTechCheck3());
			techCheckLst.setMandatoryYn(claimFlowVO.getTechCheck4());
			techCheckLst.setCpdRemark(claimFlowVO.getClaimPanelRemark());
			techCheckLst.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			techCheckLst.setCrtUsr(claimFlowVO.getUserId());
			genericDao.save(techCheckLst);
		} catch (Exception e) {
			logger.error("Error occured in saveTechCheckList() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Save cex check list.
	 * 
	 * @param claimFlowVO
	 *            the claim flow vo
	 */
	private void saveCexCheckList(ClaimsFlowVO claimFlowVO) {

		EhfClaimCexChklist exeCheckList;
		EhfClaimCexChklist cexCheckListExist = genericDao
				.findById(EhfClaimCexChklist.class, String.class,
						claimFlowVO.getCaseId());

		if (cexCheckListExist != null)
			exeCheckList = cexCheckListExist;
		else
			exeCheckList = new EhfClaimCexChklist();

		try {
			exeCheckList.setCaseId(claimFlowVO.getCaseId());
			exeCheckList.setNameYn(claimFlowVO.getNameCheck());
			exeCheckList.setGenderYn(claimFlowVO.getGenderCheck());
			exeCheckList.setCardOnbedYn(claimFlowVO.getBenPhotoCheck());
			exeCheckList
					.setSheetAdmDate(sdf.parse(claimFlowVO.getCaseStAdmDt()));
			exeCheckList.setSheetDisdeathDate(sdf.parse(claimFlowVO
					.getCaseStDischrgDt()));
			exeCheckList.setSheetSurgthrDate(sdf.parse(claimFlowVO
					.getCaseStSurgDt()));
			exeCheckList.setAdmDateYn(claimFlowVO.getAdmDtCheck());
			exeCheckList.setSurgthrDateYn(claimFlowVO.getSurgDtCheck());
			exeCheckList.setDisDeathDateYn(claimFlowVO.getDischargeDtCheck());
			exeCheckList.setPatSignYn(claimFlowVO.getDocVer1());
			exeCheckList.setPatSatLetterYn(claimFlowVO.getDocVer2());
			exeCheckList.setMandRprtYn(claimFlowVO.getDocVer3());
			exeCheckList.setRprtSignYn(claimFlowVO.getDocVer4());
			exeCheckList.setDatePatnameYn(claimFlowVO.getDocVer5());
			exeCheckList.setCexRemarks(claimFlowVO.getCexRemark());
			exeCheckList.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			exeCheckList.setCrtUsr(claimFlowVO.getUserId());
			genericDao.save(exeCheckList);
		} catch (Exception e) {
			logger.error("Error occured in saveCexCheckList() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * @param claimFlowVO the claim flow vo 
	 * @Desc used to save Err-claim details in table 
	 * @return claimFlowVO
	 */
	@Override
	public ClaimsFlowVO saveErrClaim(ClaimsFlowVO claimFlowVO) {
		Long lActOrder = 0L;
		String lStrNextStatus = null;
		String errCaseStatus = ClaimsConstants.EMPTY;
		Double approveAmt = 0.0;
		String remarks = ClaimsConstants.EMPTY;
		String smsMsg = ClaimsConstants.EMPTY;
		String msg = ClaimsConstants.EMPTY;

		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
		args[0] = claimFlowVO.getCaseId();
		List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
				ClaimsFlowVO.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()
				&& actOrderList.get(0).getCOUNT() != null) {
			if (actOrderList.get(0).getCOUNT().longValue() >= 0)
				lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
		}

		try {
			//getting case details
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,
					claimFlowVO.getCaseId());
			//getting hospital details
			EhfmHospitals ehfmHosp = genericDao.findById(EhfmHospitals.class,
					String.class, ehfCase.getCaseHospCode());
			String lStrHospName = ehfmHosp.getHospName();

			claimFlowVO.setPatientId(ehfCase.getCasePatientNo());
			EhfErroneousClaim ehfErroneousClaim = genericDao.findById(
					EhfErroneousClaim.class, String.class,
					claimFlowVO.getCaseId() + "/E");
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.MEDCO"))) {
				if (ehfErroneousClaim != null
						&& !ehfErroneousClaim.getErrClaimStatus()
								.equalsIgnoreCase(
										claimFlowVO.getErrClaimStatus())) {
					msg = ClaimsConstants.ALREADYMESSAGE;
					claimFlowVO.setMsg(msg);
					claimFlowVO.setSmsMsg(ClaimsConstants.EMPTY);
					return claimFlowVO;
				}
			} else {
				if (ehfErroneousClaim.getErrClaimStatus() != null
						&& claimFlowVO.getErrClaimStatus() != null
						&& !ehfErroneousClaim.getErrClaimStatus()
								.equalsIgnoreCase(
										claimFlowVO.getErrClaimStatus())) {
					msg = ClaimsConstants.ALREADYMESSAGE;
					claimFlowVO.setMsg(msg);
					claimFlowVO.setSmsMsg(ClaimsConstants.EMPTY);

					return claimFlowVO;
				}
			}
			if (ehfErroneousClaim != null) {
				ehfErroneousClaim.setLstUpdDt(new java.sql.Timestamp(new Date()
						.getTime()));
				ehfErroneousClaim.setLstUpdUsr(claimFlowVO.getUserId());
				errCaseStatus = ehfErroneousClaim.getErrClaimStatus();
			} else {
				ehfErroneousClaim = new EhfErroneousClaim();
				ehfErroneousClaim.setCaseId(claimFlowVO.getCaseId());
				ehfErroneousClaim.setSchemeId(ehfCase.getSchemeId());
				ehfErroneousClaim.setCrtDt(new java.sql.Timestamp(new Date()
						.getTime()));
				ehfErroneousClaim.setCrtUsr(claimFlowVO.getUserId());
				ehfErroneousClaim.setErrClaimId(claimFlowVO.getCaseId() + "/E");
			}
			if (ehfCase.getErrClaimStatus() != null
					&& !ehfCase.getErrClaimStatus().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				errCaseStatus = ehfCase.getErrClaimStatus();
			else
				errCaseStatus = ehfCase.getCaseStatus();

			if (claimFlowVO.getCaseId() != null
					&& !errCaseStatus.equalsIgnoreCase(ClaimsConstants.EMPTY)) {
				lStrNextStatus = getNextStatus(errCaseStatus,
						claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
			}

			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.MEDCO"))) {

				ehfErroneousClaim.setErrSubDate(df.parse(claimFlowVO
						.getErrClaimSubDt()));

				if (claimFlowVO.getErrAmt() != null
						&& !claimFlowVO.getErrAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfErroneousClaim.setErrClaimAmt(Double
							.parseDouble(claimFlowVO.getErrAmt()));
					ehfErroneousClaim.setClaimAmt(claimFlowVO.getClaimPaidAmt()
							.doubleValue());
					approveAmt = Double.parseDouble(claimFlowVO.getErrAmt());
				}
				ehfErroneousClaim.setMedcoRemark(claimFlowVO
						.getErrMedcoRemark());

				remarks = claimFlowVO.getErrMedcoRemark();
				smsMsg = config.getString("EHF.Claims.MSG.ERRSMSMEDINIT")
						+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName + ClaimsConstants.DOT;
				msg = config.getString("EHF.Claims.MSG.ERRMEDINIT");
				if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.ErrReInit"))) {
					msg = config.getString("EHF.Claims.MSG.ERRCTDPEDUPD");
					smsMsg = config.getString("EHF.Claims.MSG.ERRSMSCTDPEDUPD")
							+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
							+ ClaimsConstants.DOT;
				} else if (lStrNextStatus != null
						&& lStrNextStatus.equalsIgnoreCase(config
								.getString("EHF.Claims.chErrReInit"))) {
					msg = config.getString("EHF.Claims.MSG.ERRCHPENDUPD");
					smsMsg = config.getString("EHF.Claims.MSG.ERRSMSCHPEDUPD")
							+ claimFlowVO.getCaseId() + config.getString("EHF.Claims.MSG.SMSCLAIMIN") + lStrHospName
							+ ClaimsConstants.DOT;
				}
			}

			// for saving trust doc checklist
			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CTD"))) {

				remarks = claimFlowVO.getErrCtdRemark();
				if (claimFlowVO.getErrCtdAprAmt() != null
						&& !claimFlowVO.getErrCtdAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfErroneousClaim.setCtdAprvAmt(Double
							.parseDouble(claimFlowVO.getErrCtdAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO
							.getErrCtdAprAmt());
				}
				ehfErroneousClaim.setCtdRemark(remarks);
				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RedAppButton")))
					msg = config.getString("EHF.Claims.MSG.ERRCTDAPP");
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RejButton")))
					msg = config.getString("EHF.Claims.MSG.ERRCTDREJ");
				else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton")))
					msg = config.getString("EHF.Claims.MSG.ERRCTDPED");
			}

			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.CH"))) {

				remarks = claimFlowVO.getErrChRemark();
				if (claimFlowVO.getErrChAprAmt() != null
						&& !claimFlowVO.getErrChAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfErroneousClaim.setChAprvAmt(Double
							.parseDouble(claimFlowVO.getErrChAprAmt()));

					approveAmt = Double.parseDouble(claimFlowVO
							.getErrChAprAmt());
				}
				ehfErroneousClaim.setChRemark(remarks);

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.AppButton"))) {
					msg = config.getString("EHF.Claims.MSG.ERRCHAPP");
					smsMsg = config.getString("EHF.Claims.MSG.ERRSMSCHAPP")
							+ claimFlowVO.getCaseId()
							+ config.getString("EHF.Claims.MSG.SMSCLAIMIN")
							+ lStrHospName
							+ ClaimsConstants.DOT;
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.RejButton"))) {
					msg = config.getString("EHF.Claims.MSG.ERRCHREJ");
					smsMsg = config.getString("EHF.Claims.MSG.ERRSMSCHREJ")
							+ claimFlowVO.getCaseId()
							+ config.getString("EHF.Claims.MSG.SMSCLAIMIN")
							+ lStrHospName
							+ ClaimsConstants.DOT;
				} else if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.PendButton"))) {
					msg = config.getString("EHF.Claims.MSG.ERRCHPED");
				}
			}

			if (claimFlowVO.getRoleId().equalsIgnoreCase(
					config.getString("EHF.Claims.ACO"))) {

				remarks = claimFlowVO.getErrAcoRemark();
				if (ehfErroneousClaim.getChAprvAmt() != null)
					claimFlowVO.setErrAcoAprAmt(ehfErroneousClaim
							.getChAprvAmt().toString());
				if (claimFlowVO.getErrAcoAprAmt() != null
						&& !claimFlowVO.getErrAcoAprAmt().equalsIgnoreCase(
								ClaimsConstants.EMPTY)) {
					ehfErroneousClaim.setAcoAprAmt(Double
							.parseDouble(claimFlowVO.getErrAcoAprAmt()));
					approveAmt = Double.parseDouble(claimFlowVO
							.getErrAcoAprAmt());
				}
				ehfErroneousClaim.setAcoRemrk(remarks);

				if (claimFlowVO.getActionDone().equalsIgnoreCase(
						config.getString("EHF.VerifyButton"))) {

					EhfClaimAccounts ehfCmAccounts = new EhfClaimAccounts();
					String errId  = claimFlowVO.getCaseId()+"/E";
					ehfCmAccounts.setCaseId(errId);
					String amt = claimFlowVO.getErrAcoAprAmt().substring(0,
							claimFlowVO.getErrAcoAprAmt().indexOf("."));
					Long l = new Long(amt);
					ehfCmAccounts.setAprvdAmt(l);
					ehfCmAccounts.setCrtDt(new java.sql.Timestamp(new Date()
							.getTime()));
					ehfCmAccounts.setCrtUsr(claimFlowVO.getUserId());
					ehfCmAccounts.setRemarks(remarks);
					ehfCmAccounts
							.setTransStatus(ClaimsConstants.TransReadyStatus);
					ehfCmAccounts.setTimeMilSec((long) 0);
					ehfCmAccounts = genericDao.save(ehfCmAccounts);
					Double d = new Double(claimFlowVO.getErrAcoAprAmt());
					ehfErroneousClaim.setTotClaimAmt(d);

					msg = config.getString("EHF.Claims.MSG.ERRACOVER");
					smsMsg = config.getString("EHF.Claims.MSG.ERRSMSACOVER")
							+ claimFlowVO.getCaseId()
							+ config.getString("EHF.Claims.MSG.SMSCLAIMIN")
							+ lStrHospName
							+ ClaimsConstants.DOT;
				}
			}

			ehfErroneousClaim.setErrClaimStatus(lStrNextStatus);
			genericDao.save(ehfErroneousClaim);

			ehfCase.setViewFlag("N");
			ehfCase.setErrClaimStatus(lStrNextStatus);
			genericDao.save(ehfCase);
			// insert into asrit_audit
			EhfAudit asritAudit = new EhfAudit();
			EhfAuditId asritAuditPK = new EhfAuditId(lActOrder,
					claimFlowVO.getCaseId());
			asritAudit.setId(asritAuditPK);
			asritAudit.setActId(lStrNextStatus);
			asritAudit.setActBy(claimFlowVO.getUserId());
			asritAudit.setCrtUsr(claimFlowVO.getUserId());
			asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			asritAudit.setLangId(ClaimsConstants.LANG_ID);
			asritAudit.setRemarks(remarks);
			asritAudit.setApprvAmt(approveAmt);
			asritAudit.setUserRole(claimFlowVO.getRoleId());
			genericDao.save(asritAudit);

			claimFlowVO.setSmsMsg(smsMsg);

			if (ehfErroneousClaim != null)
				claimFlowVO.setMsg(msg);
			else
				claimFlowVO.setMsg(ClaimsConstants.Failure);

		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Error occured in saveErrClaim() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}

		return claimFlowVO;
	}

	/**
	 * Gets the next status according to current status and button clicked.
	 * 
	 * @param pStrCaseStatus
	 *            the str case status
	 * @param pStrUserRole
	 *            the str user role
	 * @param lStrActionDone
	 *            the l str action done
	 * @return the next status
	 */
	private String getNextStatus(String pStrCaseStatus, String pStrUserRole,
			String lStrActionDone) {
		String lStrNextStatus = null;
		try {

			EhfmWorkflowStatus ehfmWorkflowStatus = null;
			ehfmWorkflowStatus = genericDao.findById(EhfmWorkflowStatus.class,
					EhfmWorkflowStatusId.class, new EhfmWorkflowStatusId(
							pStrCaseStatus, pStrUserRole, lStrActionDone));
			if (ehfmWorkflowStatus != null)
				lStrNextStatus = ehfmWorkflowStatus.getNextStatus();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured in getNextStatus() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
		}

		return lStrNextStatus;

	}

	/**
	 * Gets Case details for payment
	 * 
	 * @param caseNo
	 * @param paymentType
	 * @return CommonDtlsVO
	 */
	@Override
	public CommonDtlsVO getCaseDtlsForPayment(String caseNo, String paymentType) {

		StringBuffer queryBuff = new StringBuffer();
		List<CommonDtlsVO> resLst = null;
		try {

			queryBuff
					.append(" select distinct p.name as PATIENTNAME , p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , p.crtDt as date ");
			queryBuff
					.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT ");
			queryBuff
					.append(" ,ec.caseNo  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID ,ec.crtDt||'' as regdate , p.cardType as cardType ");
			queryBuff
					.append("  ,hm.hospName||''||hm.hospDispCode as HOSPNAME , p.gender as GENDER , a.cmbDtlName as STATUS ,ec.caseStatus as CASESTAT   ");
			queryBuff
					.append(" , ec.ipNo as IPNO , cm.catName as DISNAME, sm.subCatName as SURGNAME,ec.cochlearYN as cochlear,ec.csDisDt||'' as disDate,ec.csSurgDt||'' as surgeryDate,ecc.totClaimAmt||'' as claimAmt  ");
			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
				queryBuff
						.append(" , ecc.errClaimId as telephonicId,ecd.cmbDtlName as comorbidName ");
			}
			queryBuff
					.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm  ");
			queryBuff
					.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph , EhfmDiagCategoryMst cm , EhfmDiagSubCatMst sm  ");

			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
				queryBuff
						.append(" ,EhfErroneousClaim ecc,EhfmCmbDtls ecd where ecc.errClaimStatus=ecd.cmbDtlId and ecc.errClaimId = '"
								+ caseNo + "' and ");
			} else {
				queryBuff.append(", EhfCaseClaim ecc where ec.caseNo = '"
						+ caseNo + "' and ");
			}
			queryBuff
					.append("  p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and ecc.caseId=ec.caseId and ");
			queryBuff
					.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId ");
			queryBuff
					.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ec.caseStatus ");
			queryBuff
					.append(" and p.patientId =ph.patientId and ph.categoryCode = cm.id.catCode and ph.mainCatCode = cm.id.mainCatCode ");
			queryBuff
					.append(" and sm.id.catCode =ph.categoryCode and sm.id.subCatCode = ph.subCatCode ");

			resLst = genericDao.executeHQLQueryList(CommonDtlsVO.class,
					queryBuff.toString());

		} catch (Exception e) {
			logger.error("Error occured in getCaseDtlsForPayment() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return resLst.get(0);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void jrnlstGenerateFile(){
		
	    System.out.println("Scheduler to Generate Journalist Claims File Started in TS ");
		String[] stateArray = new String[]{"CD202"};
		
		
		
		
	    for(int stateCount=0;stateCount<stateArray.length;stateCount++){
	    	
	    	
	    	
	    	HashMap hParamMap = new HashMap();
	    	
	    	hParamMap.put("SharePath", config.getString("mapPath"));
	    	hParamMap.put("SentStatus", ClaimsConstants.SENT);
	    	 hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
	    	hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
	    	hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
	    	hParamMap.put("TDS_RATE_PERCENT",(config.getString("tds_rate_percent") != null ? config.getString("tds_rate_percent"):"10"));
	      	hParamMap.put("TDS_HOSP_RATE_PERCENT",(config.getString("tds_hosp_rate_percent") != null ? config.getString("tds_hosp_rate_percent"):"90"));
	        
	    	
	    hParamMap.put("SCHEME_ID", stateArray[stateCount]);
	    ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
	    if(stateArray[stateCount].equalsIgnoreCase(config.getString("TG")))
	    		{
	    	hParamMap.put("CRTUSER", ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    	claimFlowVO.setUserId(ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP_ID);
	    		}
	    if(stateArray[stateCount].equalsIgnoreCase(config.getString("AP")))
	    		{
	    	hParamMap.put("CRTUSER", ClaimsConstants.CEO_AP_USER_ID);
	    	claimFlowVO.setUserId(ClaimsConstants.CEO_AP_USER_ID);
	    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP);
	    		}
		String paymentType = "";String[] caseSelected = null;
		
		
		
		claimFlowVO.setActionDone(ClaimsConstants.PAYNOW_BUTTON);
		
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		/*Starts for normal cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.JHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfCase> ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
	    
	    if(ehfCaseLst!=null && ehfCaseLst.size()>0){
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT); 
	    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_READY_BANK); 
	    	
	    	 hParamMap.put("PaymentType", paymentType);
	    	 hParamMap.put("sentAgain", "N");
	    	 hParamMap.put("patScheme",  config.getString("Scheme.JHS"));
	    	 
	    	 int listCount = ehfCaseLst.size()/50;
	    	 int loopSize = ehfCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[50];
	        		 for(int i=0;i<50;i++){
	     	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateClaimStatus(hParamMap);
	        	    	loopSize=loopSize-50;
	        	 } } }
	    /*Ends for normal cases which are in claim ready status*/
	    /*Starts for normal cases which are in claim ready status(REJ)*/
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfCaseLst = new ArrayList<EhfCase>();
	    criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.JHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_READY_REJ_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
	    if(ehfCaseLst!=null && ehfCaseLst.size()>0){
	    	hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT); 
	    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_READY_REJ_BANK); 
	    	 hParamMap.put("PaymentType", paymentType);
	    	 hParamMap.put("sentAgain", "Y");
	    	 hParamMap.put("patScheme",  config.getString("Scheme.JHS"));
	    	 
	    	 int listCount = ehfCaseLst.size()/50;
	    	 int loopSize = ehfCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[50];
	        		 for(int i=0;i<50;i++){
	     	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateClaimStatus(hParamMap);
	        	    	loopSize=loopSize-50;
	        	 } } }
	    /*Ends for normal cases which are in claim ready status(REJ)*/
	    //generateERRFile();
	   // generateFollowUpFile();
	    generateJrnlstTDSFile(hParamMap,claimFlowVO);
	    System.out.println("Scheduler to Generate Journalist Claims File ended in TS ");
	    }
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateSurplusDeductions() {
		
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		   /*Starts for  cases which are having surplus TDS*/
		String[] stateArray = new String[]{"CD202"};
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.EHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	   	criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	   	criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
	   	criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[0],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	   	List<EhfCase> ehfCaseLst  = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
         @SuppressWarnings("rawtypes")
		HashMap hParamMap = new HashMap();
         String[] caseSelected = null;

    	hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
    	hParamMap.put("SCHEME_ID", stateArray[0]);
    	hParamMap.put("TDS_RATE_PERCENT",(config.getString("tds_rate_percent") != null ? config.getString("tds_rate_percent"):"10"));
      	hParamMap.put("TDS_HOSP_RATE_PERCENT",(config.getString("tds_hosp_rate_percent") != null ? config.getString("tds_hosp_rate_percent"):"90"));
          
    	String paymentType = "";
    hParamMap.put("SCHEME_ID", stateArray[0]);
    ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
	       if(ehfCaseLst!=null && ehfCaseLst.size()>0){
	       	 hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT); 
	       	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED); 
	    	 hParamMap.put("patScheme",  config.getString("Scheme.EHS"));
	       	 hParamMap.put("PaymentType", paymentType);
	       	 hParamMap.put("sentAgain", "N");
	       	String lStrCaseId = ClaimsConstants.EMPTY;
	       	boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
	       	 int loopSize = ehfCaseLst.size();int listTrackerCount=0; 
	       	 caseSelected = new String[loopSize];
	       	 for(int i=0;i<loopSize;i++){
    	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
    	        	listTrackerCount++;
    	        	}
	         claimFlowVO.setCaseSelected(caseSelected);
	         for (String cases : claimFlowVO.getCaseSelected()) {
	        	 try {
	        		 lStrCaseId = cases;
	        		 hParamMap.put("CASE_ID", lStrCaseId);
	     	    	    		 EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,lStrCaseId);
	     	    	    		 if(ehfCase!=null)
	     	    	    		 {
	     	    	    		 criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	     	    	    		    criteriaList.add(new GenericDAOQueryCriteria("id.hospId", ehfCase.getCaseHospCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	     	    	    		   	criteriaList.add(new GenericDAOQueryCriteria("id.deductType",ClaimsConstants.TRUST_DEDUCTOR ,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	     	    	    		  	List<EhfRevHospDeduct> ehfRevHospDeductList  = genericDao.findAllByCriteria(EhfRevHospDeduct.class, criteriaList);
	     	    	    	     
	     	    	    		  	if(ehfRevHospDeductList!=null && ehfRevHospDeductList.size()>0)
	     	    	    		  	{
	     	    	    		  		Long balanceAmount=ehfRevHospDeductList.get(0).getBalanceAmt();
	     	    	    		  		if(balanceAmount>0)
	     	    	    		  		{
	     	    	    		  			
	     	    	    		  		 isInsert = ClaimsConstants.BOOLEAN_FALSE;
	     	    	        			isInsert = claimsPaymentDao
	     	    								.calculateClaimPercentageSurplus(hParamMap);
	     	    	        			if(isInsert)
	     	    	        			{
	     	    	        				EhfSurplusTdsClaim ehfSurplusTdsClaim=new EhfSurplusTdsClaim();
	     	    	        				String tdsFlag=(String)hParamMap.get("TDSActiveFlag");
	     	    	        				String rfFlag=(String)hParamMap.get("RFActiveFlag");
	     	    	        				Double lhospTDSAmt=Double.parseDouble(ClaimsConstants.SURPLUS_AMOUNT);
	     	    	        				Long surplusAmt=0L;
	     	    	        				Double lhospRFAmt=Double.parseDouble(ClaimsConstants.SURPLUS_AMOUNT);
	     	    	        				if(tdsFlag!=null && tdsFlag.equalsIgnoreCase("Y"))
     	    	     	    	       		{
	     	    	        					 lhospTDSAmt=Double.parseDouble((String)hParamMap.get("lhospTDSAmt"));
	     	    	        					if(balanceAmount<lhospTDSAmt)
	     	    	        					{
	     	    	        						lhospTDSAmt=lhospTDSAmt-balanceAmount;	     	    	        						
	     	    	        						surplusAmt=balanceAmount;
	     	    	        					    balanceAmount=0L;
	     	    	        					}
	     	    	        					else
	     	    	        					{ lhospTDSAmt=Double.parseDouble((String)hParamMap.get("lhospTDSAmt"));
	     	    	        						surplusAmt=(long) (lhospTDSAmt-1);
	     	    	        						lhospTDSAmt=(double) 1;
	     	    	        						balanceAmount=balanceAmount-surplusAmt;
	     	    	        					}
	     	    	        					
	     	    	        					
	     	    	        					ehfSurplusTdsClaim.setTdsHospPctAmt(lhospTDSAmt);
	     	    	        					ehfSurplusTdsClaim.setTdsPctAmt((double)surplusAmt);
	     	    	        					ehfSurplusTdsClaim.setTDSRFFflag("Y");
	     	    	        					ehfSurplusTdsClaim.setHospPctAmt((double) 0);
		     	    	        				ehfSurplusTdsClaim.setTrustPctAmt((double) 0);
     	    	     	    	       		}	
	     	    	        				else if(rfFlag!=null && rfFlag.equalsIgnoreCase("Y"))
     	    	     	    	       		{
	     	    	        					lhospRFAmt=Double.parseDouble((String)hParamMap.get("lhospAmt"));
	     	    	        					if(balanceAmount<lhospRFAmt)
	     	    	        					{
	     	    	        						lhospRFAmt=lhospRFAmt-balanceAmount;	     	    	        						
	     	    	        						surplusAmt=balanceAmount;
	     	    	        					    balanceAmount=0L;
	     	    	        					}
	     	    	        					else
	     	    	        					{ lhospRFAmt=Double.parseDouble((String)hParamMap.get("lhospAmt"));
	     	    	        						surplusAmt=(long) (lhospRFAmt-1);
	     	    	        						lhospRFAmt=(double) 1;
	     	    	        						balanceAmount=balanceAmount-surplusAmt;
	     	    	        					}
	     	    	        					
	     	    	        					
	     	    	        					ehfSurplusTdsClaim.setTdsHospPctAmt((double) 0);
	     	    	        					ehfSurplusTdsClaim.setTdsPctAmt((double) 0);
	     	    	        					ehfSurplusTdsClaim.setTDSRFFflag("N");
	     	    	        					ehfSurplusTdsClaim.setHospPctAmt(lhospRFAmt);
		     	    	        				ehfSurplusTdsClaim.setTrustPctAmt((double)surplusAmt);
     	    	     	    	       		}	
	     	    	        					
	     	    	        				ehfSurplusTdsClaim.setCaseId(lStrCaseId);	     	    	        			
	     	    	     	    	       	ehfSurplusTdsClaim.setCrtDt(new Timestamp(new Date().getTime()));
	     	    	     	    	    	ehfSurplusTdsClaim.setCrtUsr("Scheduler");
	     	    	     	    	    	ehfSurplusTdsClaim=genericDao.save(ehfSurplusTdsClaim);
	     	    	     	    	    	 isInsert = ClaimsConstants.BOOLEAN_TRUE;
	     	    	     	    	    	 
	     	    	     	    	    	 if(isInsert)
	     	    	     	    	    	 {
	     	    	     	    	    		ehfRevHospDeductList.get(0).setBalanceAmt(balanceAmount);
	     	    	     	    	    		genericDao.save(ehfRevHospDeductList.get(0));
	     	    	     	    	    		
	     	    	     	    	    		
	     	    	     	    	    		 if(ehfCase!=null)
	     	    	     						{
	     	    	     							ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED);
	     	    	     							ehfCase=genericDao.save(ehfCase);	 //to modify status to surplus deducted status (CD157)    							
	     	    	     						}
	     	    	     	    	    	 }
	     	    	     	    	    	
	     	    	        			}	
	     	    	    		  			
	     	    	    		  			
	     	    	    		  		}
	     	    	    		  		else
	     	    	    		  		{
	     	    	    		  			if(ehfCase!=null)
     	    	     						{
	     	    	    		  			ehfCase.setCaseStatus(ClaimsConstants.CLAIM_READY_BANK);
 	    	     							ehfCase=genericDao.save(ehfCase);	 //to modify status to surplus deducted status (CD157)    	
     	    	     						}
	     	    	    		  		}
	     	    	    		  		
	     	    	    		  	}
	     	    	    		 }
	     	    	    		
	     	    	    		
	     	    	    	
	        			
	        				        			
	        	 }
	        	 catch (Exception ex) {	 			
	 				logger.error("failed cases are in catch block************");	 				
	 				ex.printStackTrace();
	 			}
	         
	            	
	            	
	            	
	           		/* caseSelected = new String[loopSize];
	           	            for(int i=0;i<loopSize;i++){
	           	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
	           	        	listTrackerCount++;
	           	        	}
	           	            claimFlowVO.setCaseSelected(caseSelected);
	           	        	hParamMap.put("claimFlowVO", claimFlowVO);
	           	        	//updating claim Status while paying
	           	        	updateClaimStatus(hParamMap);
	           	        	loopSize=loopSize-loopSize;*/
	           	  } }
	       /*Ends for Starts for  cases which are having surplus TDS*/
		
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
public void generateFile(){
		System.out.println("scheduler to Generate Claims File started in TS ");
	String[] stateArray = new String[]{"CD202"};
	
	
	
    for(int stateCount=0;stateCount<stateArray.length;stateCount++){
    	
    	
    	
    	HashMap hParamMap = new HashMap();
    	
    	hParamMap.put("SharePath", config.getString("mapPath"));
    	hParamMap.put("SentStatus", ClaimsConstants.SENT);
    	 hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
    	hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
    	hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
    	hParamMap.put("TDS_RATE_PERCENT",(config.getString("tds_rate_percent") != null ? config.getString("tds_rate_percent"):"10"));
      	hParamMap.put("TDS_HOSP_RATE_PERCENT",(config.getString("tds_hosp_rate_percent") != null ? config.getString("tds_hosp_rate_percent"):"90"));
        
    	
    hParamMap.put("SCHEME_ID", stateArray[stateCount]);
    ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
    if(stateArray[stateCount].equalsIgnoreCase(config.getString("TG")))
    		{
    	hParamMap.put("CRTUSER", ClaimsConstants.EO_TG_ADMIN_USER_ID);
    	claimFlowVO.setUserId(ClaimsConstants.EO_TG_ADMIN_USER_ID);
    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP_ID);
    		}
    if(stateArray[stateCount].equalsIgnoreCase(config.getString("AP")))
    		{
    	hParamMap.put("CRTUSER", ClaimsConstants.CEO_AP_USER_ID);
    	claimFlowVO.setUserId(ClaimsConstants.CEO_AP_USER_ID);
    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP);
    		}
	String paymentType = "";String[] caseSelected = null;
	
	
	
	claimFlowVO.setActionDone(ClaimsConstants.PAYNOW_BUTTON);
	verifySurplusDeductions();
	List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	/*Starts for normal cases which are in claim ready status*/
	criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.EHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
	criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	List<EhfCase> ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
    
    if(ehfCaseLst!=null && ehfCaseLst.size()>0){
    	 hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT); 
    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_READY_BANK); 
    	
    	 hParamMap.put("PaymentType", paymentType);
    	 hParamMap.put("sentAgain", "N");
    	 hParamMap.put("patScheme",  config.getString("Scheme.EHS"));
    	 int listCount = ehfCaseLst.size()/50;
    	 int loopSize = ehfCaseLst.size();int listTrackerCount=0; 
         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
        	 if(loopSize<=50){
        		 caseSelected = new String[loopSize];
        	            for(int i=0;i<loopSize;i++){
        	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
        	        	listTrackerCount++;
        	        	}
        	            claimFlowVO.setCaseSelected(caseSelected);
        	        	hParamMap.put("claimFlowVO", claimFlowVO);
        	        	//updating claim Status while paying
        	        	updateClaimStatus(hParamMap);
        	        	loopSize=loopSize-loopSize;
        	  }else{
        		 caseSelected = new String[50];
        		 for(int i=0;i<50;i++){
     	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
     	        	listTrackerCount++;
     	        	}
        		    claimFlowVO.setCaseSelected(caseSelected);
        	    	hParamMap.put("claimFlowVO", claimFlowVO);
        	    	//updating claim Status while paying
        	    	updateClaimStatus(hParamMap);
        	    	loopSize=loopSize-50;
        	 } } }
    /*Ends for normal cases which are in claim ready status*/
    
    /*Starts for  cases which are having surplus TDS*/
    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
    criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.EHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
   	criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_SURPLUS_TDS_DED,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
   	criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
   	criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
       
       if(ehfCaseLst!=null && ehfCaseLst.size()>0){
       	 hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT); 
       	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_SURPLUS_TDS_DED); 
    	 hParamMap.put("patScheme",  config.getString("Scheme.EHS"));
       	 hParamMap.put("PaymentType", paymentType);
       	 hParamMap.put("sentAgain", "N");
       	 
       	 int listCount = ehfCaseLst.size()/50;
       	 int loopSize = ehfCaseLst.size();int listTrackerCount=0; 
            for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
           	 if(loopSize<=50){
           		 caseSelected = new String[loopSize];
           	            for(int i=0;i<loopSize;i++){
           	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
           	        	listTrackerCount++;
           	        	}
           	            claimFlowVO.setCaseSelected(caseSelected);
           	        	hParamMap.put("claimFlowVO", claimFlowVO);
           	        	//updating claim Status while paying
           	        	updateClaimStatus(hParamMap);
           	        	loopSize=loopSize-loopSize;
           	  }else{
           		 caseSelected = new String[50];
           		 for(int i=0;i<50;i++){
        	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
        	        	listTrackerCount++;
        	        	}
           		    claimFlowVO.setCaseSelected(caseSelected);
           	    	hParamMap.put("claimFlowVO", claimFlowVO);
           	    	//updating claim Status while paying
           	    	updateClaimStatus(hParamMap);
           	    	loopSize=loopSize-50;
           	 } } }
       /*Ends for Starts for  cases which are having surplus TDS*/
    
    /*Starts for normal cases which are in claim ready status(REJ)*/
    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
    ehfCaseLst = new ArrayList<EhfCase>();
    criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.EHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_READY_REJ_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
	criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
    if(ehfCaseLst!=null && ehfCaseLst.size()>0){
    	hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT); 
    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_READY_REJ_BANK); 
    	 hParamMap.put("PaymentType", paymentType);
    	 hParamMap.put("sentAgain", "Y");
    	 hParamMap.put("patScheme",  config.getString("Scheme.EHS"));
    	 int listCount = ehfCaseLst.size()/50;
    	 int loopSize = ehfCaseLst.size();int listTrackerCount=0; 
         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
        	 if(loopSize<=50){
        		 caseSelected = new String[loopSize];
        	            for(int i=0;i<loopSize;i++){
        	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
        	        	listTrackerCount++;
        	        	}
        	            claimFlowVO.setCaseSelected(caseSelected);
        	        	hParamMap.put("claimFlowVO", claimFlowVO);
        	        	//updating claim Status while paying
        	        	updateClaimStatus(hParamMap);
        	        	loopSize=loopSize-loopSize;
        	  }else{
        		 caseSelected = new String[50];
        		 for(int i=0;i<50;i++){
     	        	caseSelected[i]=ehfCaseLst.get(listTrackerCount).getCaseId();
     	        	listTrackerCount++;
     	        	}
        		    claimFlowVO.setCaseSelected(caseSelected);
        	    	hParamMap.put("claimFlowVO", claimFlowVO);
        	    	//updating claim Status while paying
        	    	updateClaimStatus(hParamMap);
        	    	loopSize=loopSize-50;
        	 } } }
    /*Ends for normal cases which are in claim ready status(REJ)*/
    //generateERRFile();
   // generateFollowUpFile();
    generateTDSFile(hParamMap,claimFlowVO);
    }
    System.out.println("Scheduler to Generate Claims File ended in TS ");
}
	//To change status of surplus deductions cases
	private void verifySurplusDeductions() {
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		
		 String hospIdArray[] = config.getString("SurplusHospitals").split("~");

		 List<String> hospIdList=new ArrayList<String>();
			for(int g=0;g<hospIdArray.length;g++){
				hospIdList.add(hospIdArray[g]);
				
			}
		/*Starts for normal cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.EHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseStatus", ClaimsConstants.CLAIM_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId","CD202" ,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
		criteriaList.add(new GenericDAOQueryCriteria("caseHospCode",hospIdList,GenericDAOQueryCriteria.CriteriaOperator.IN));

		List<EhfCase> ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
		if(ehfCaseLst!=null && ehfCaseLst.size()>0)
		{
			for(int i=0;i<ehfCaseLst.size();i++)
			{
				if(ehfCaseLst.get(i).getCaseHospCode()!=null)
				{
					criteriaList = new ArrayList<GenericDAOQueryCriteria>();
 	    		    criteriaList.add(new GenericDAOQueryCriteria("id.hospId", ehfCaseLst.get(i).getCaseHospCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
 	    		   	criteriaList.add(new GenericDAOQueryCriteria("id.deductType",ClaimsConstants.TRUST_DEDUCTOR ,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
 	    		  	List<EhfRevHospDeduct> ehfRevHospDeductList  = genericDao.findAllByCriteria(EhfRevHospDeduct.class, criteriaList);
 	    	     
 	    		  	if(ehfRevHospDeductList!=null && ehfRevHospDeductList.size()>0)
 	    		  	{
 	    		  		Long balanceAmount=ehfRevHospDeductList.get(0).getBalanceAmt();
 	    		  		if(balanceAmount>0)
 	    		  		{
 	    		  			ehfCaseLst.get(i).setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
 	    					genericDao.save(ehfCaseLst.get(i));
 	    		  		}
				}
				
			}
		}
		
		updateSurplusDeductions();
		
	}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateJrnlstTDSFile(HashMap hParamMap,ClaimsFlowVO claimFlowVO){
		
		String[] claimStatusArray = new String[]{ClaimsConstants.CLAIM_READY_REJ_BANK};
		
		hParamMap.put("TdsRemarks", ClaimsConstants.CLAIM_SENT_RMK);
   	    hParamMap.put("UserRole", ClaimsConstants.TDSPAY_GRP_ID);
   	    claimFlowVO.setRoleId(ClaimsConstants.TDSPAY_GRP_ID);
   	    String[] caseSelected = null;
   	    for(int statusCount=0;statusCount<claimStatusArray.length;statusCount++){
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		hParamMap.put("caseStatus", claimStatusArray[statusCount]);
		if(claimStatusArray[statusCount].equalsIgnoreCase(ClaimsConstants.CLAIM_READY_REJ_BANK))
		hParamMap.put("sentAgain", "Y");
		else if(claimStatusArray[statusCount].equalsIgnoreCase(ClaimsConstants.CLAIM_READY_BANK))
		hParamMap.put("sentAgain", "N");	
		/*Starts for normal TDS cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.JHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("paymentStatus", claimStatusArray[statusCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("paymentType",ClaimsConstants.CLAIM_FORM_PAYMENT,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", hParamMap.get("SCHEME_ID"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    List<EhfClaimTdsPayment> ehfTdsLst = genericDao.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
	    if(ehfTdsLst!=null && ehfTdsLst.size()>0){	    	 
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT);  
	    	 hParamMap.put("patScheme",  config.getString("Scheme.JHS"));
	    	 
	    	 int listCount = ehfTdsLst.size()/100;
	    	 int loopSize = ehfTdsLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=100){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateTDSClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[100];
	        		 for(int i=0;i<100;i++){
	     	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateTDSClaimStatus(hParamMap);
	        	    	loopSize=loopSize-100;
	        	 } } }
	    /*Ends for TDS cases which are in claim ready status*/
	    /*criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfTdsLst = new ArrayList<EhfClaimTdsPayment>();
		Starts for followup TDS cases which are in claim ready status
	    criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.JHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("paymentStatus", claimStatusArray[statusCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("paymentType",ClaimsConstants.FPCLAIM_FORM_PAYMENT,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", hParamMap.get("SCHEME_ID"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    ehfTdsLst = genericDao.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
	    if(ehfTdsLst!=null && ehfTdsLst.size()>0){	    	 
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.FPCLAIM_FORM_PAYMENT);    	 
	    	 int listCount = ehfTdsLst.size()/100;
	    	 int loopSize = ehfTdsLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=100){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateTDSClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[100];
	        		 for(int i=0;i<100;i++){
	     	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateTDSClaimStatus(hParamMap);
	        	    	loopSize=loopSize-100;
	        	 } } }
	    Ends for followup TDS cases which are in claim ready status
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfTdsLst = new ArrayList<EhfClaimTdsPayment>();
		Starts for Erroneous TDS cases which are in claim ready status
	    criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.JHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("paymentStatus", claimStatusArray[statusCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("paymentType",ClaimsConstants.ERRCLAIM_FORM_PAYMENT,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", hParamMap.get("SCHEME_ID"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    ehfTdsLst = genericDao.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
	    if(ehfTdsLst!=null && ehfTdsLst.size()>0){	    	 
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.ERRCLAIM_FORM_PAYMENT);    	 
	    	 int listCount = ehfTdsLst.size()/100;
	    	 int loopSize = ehfTdsLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=100){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateTDSClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[100];
	        		 for(int i=0;i<100;i++){
	     	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateTDSClaimStatus(hParamMap);
	        	    	loopSize=loopSize-100;
	        	 } } }*/
	    /*Ends for Erroneous TDS cases which are in claim ready status*/
   	    }
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateERRFile(){
		
		System.out.println( "Scheduler to Generate Erroneous Claims files  started in TS");
		String[] stateArray = new String[]{"CD202"};
		
		
		
		 for(int stateCount=0;stateCount<stateArray.length;stateCount++){
			 HashMap hParamMap = new HashMap();
		hParamMap.put("SharePath", config.getString("mapPath"));
		hParamMap.put("SentStatus", ClaimsConstants.SENT);
		hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
		hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
		hParamMap.put("TDS_RATE_PERCENT",(config.getString("tds_rate_percent") != null ? config.getString("tds_rate_percent"):"10"));
      	hParamMap.put("TDS_HOSP_RATE_PERCENT",(config.getString("tds_hosp_rate_percent") != null ? config.getString("tds_hosp_rate_percent"):"90"));
        
	
	    ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
	    hParamMap.put("SCHEME_ID", stateArray[stateCount]);
	    if(stateArray[stateCount].equalsIgnoreCase(config.getString("TG")))
	    		{
	    	hParamMap.put("CRTUSER", ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    	claimFlowVO.setUserId(ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP_ID);
	    		}
	    if(stateArray[stateCount].equalsIgnoreCase(config.getString("AP")))
	    		{
	    	hParamMap.put("CRTUSER", ClaimsConstants.CEO_AP_USER_ID);
	    	claimFlowVO.setUserId(ClaimsConstants.CEO_AP_USER_ID);
	    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP);
	    		}
		String paymentType = "";String[] caseSelected = null;
		
		
		
		claimFlowVO.setActionDone(ClaimsConstants.PAYNOW_BUTTON);
		
				
		hParamMap.put("PaymentType", ClaimsConstants.ErroneousClaim);
			
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		/*Starts for Err cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("errClaimStatus", ClaimsConstants.CLAIM_ERR_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfErroneousClaim> ehfErrCaseLst = genericDao.findAllByCriteria(EhfErroneousClaim.class, criteriaList);
		hParamMap.put("FormPaymentType", ClaimsConstants.ERRCLAIM_FORM_PAYMENT);  
	    if(ehfErrCaseLst!=null && ehfErrCaseLst.size()>0){
	    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_ERR_READY_BANK); 
	    	 hParamMap.put("sentAgain", "N");   	 
	    	 
	    	 int listCount = ehfErrCaseLst.size()/50;
	    	 int loopSize = ehfErrCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfErrCaseLst.get(listTrackerCount).getErrClaimId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[50];
	        		 for(int i=0;i<50;i++){
	     	        	caseSelected[i]=ehfErrCaseLst.get(listTrackerCount).getErrClaimId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateClaimStatus(hParamMap);
	        	    	loopSize=loopSize-50;
	        	 } } }
	    /*Ends for Erroneous cases which are in claim ready status*/
	    /*Starts for Erroneous cases which are in claim ready status(REJ)*/
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfErrCaseLst = new ArrayList<EhfErroneousClaim>();
		criteriaList.add(new GenericDAOQueryCriteria("errClaimStatus", ClaimsConstants.CLAIM_ERR_READY_REJ_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		ehfErrCaseLst = genericDao.findAllByCriteria(EhfErroneousClaim.class, criteriaList);
	    if(ehfErrCaseLst!=null && ehfErrCaseLst.size()>0){
	    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_ERR_READY_REJ_BANK); 
	    	 hParamMap.put("sentAgain", "Y");
	    	 
	    	 int listCount = ehfErrCaseLst.size()/50;
	    	 int loopSize = ehfErrCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfErrCaseLst.get(listTrackerCount).getErrClaimId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[50];
	        		 for(int i=0;i<50;i++){
	     	        	caseSelected[i]=ehfErrCaseLst.get(listTrackerCount).getErrClaimId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateClaimStatus(hParamMap);
	        	    	loopSize=loopSize-50;
	        	 } } }}
	    /*Ends for Errorneous cases which are in claim ready status(REJ)*/
		 System.out.println( "Scheduler to Generate Erroneous Claims files  ended in TS");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateFollowUpFile(){
		System.out.println( "Scheduler to Generate follow up claim files  started in TS");
		
		String[] stateArray = new String[]{"CD202"};
		
		for(int stateCount=0;stateCount<stateArray.length;stateCount++){
		HashMap hParamMap = new HashMap();
		
		hParamMap.put("SharePath", config.getString("mapPath"));
		hParamMap.put("SentStatus", ClaimsConstants.SENT);
		 hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
		hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
		hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
		hParamMap.put("TDS_RATE_PERCENT",(config.getString("tds_rate_percent") != null ? config.getString("tds_rate_percent"):"10"));
      	hParamMap.put("TDS_HOSP_RATE_PERCENT",(config.getString("tds_hosp_rate_percent") != null ? config.getString("tds_hosp_rate_percent"):"90"));
        
	
	    ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
	    hParamMap.put("SCHEME_ID", stateArray[stateCount]);
	    if(stateArray[stateCount].equalsIgnoreCase(config.getString("TG")))
	    		{
	    	hParamMap.put("CRTUSER", ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    	claimFlowVO.setUserId(ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP_ID);
	    		}
	    if(stateArray[stateCount].equalsIgnoreCase(config.getString("AP")))
	    		{
	    	hParamMap.put("CRTUSER", ClaimsConstants.CEO_AP_USER_ID);
	    	claimFlowVO.setUserId(ClaimsConstants.CEO_AP_USER_ID);
	    	claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP);
	    		}
		String paymentType = "";String[] caseSelected = null;
		
	
		
		claimFlowVO.setActionDone(ClaimsConstants.PAYNOW_BUTTON);
		hParamMap.put("PaymentType", ClaimsConstants.FollowUp);
				
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		/*Starts for FollowUp cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("followUpStatus", ClaimsConstants.CLAIM_FP_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseFollowupId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfCaseFollowupClaim> ehfFpCaseLst = genericDao.findAllByCriteria(EhfCaseFollowupClaim.class, criteriaList);
		hParamMap.put("FormPaymentType", ClaimsConstants.FPCLAIM_FORM_PAYMENT); 
	    if(ehfFpCaseLst!=null && ehfFpCaseLst.size()>0){
	    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_FP_READY_BANK); 	    	 	    	 
	    	 hParamMap.put("sentAgain", "N");
	    	 
	    	 int listCount = ehfFpCaseLst.size()/50;
	    	 int loopSize = ehfFpCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfFpCaseLst.get(listTrackerCount).getCaseFollowupId();
	        	        	listTrackerCount++;
	        	  
	        	            }
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	try {
								followUpDao.updateFollowupPayments(hParamMap);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[50];
	        		 for(int i=0;i<50;i++){
	     	        	caseSelected[i]=ehfFpCaseLst.get(listTrackerCount).getCaseFollowupId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	try {
							followUpDao.updateFollowupPayments(hParamMap);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        	    	loopSize=loopSize-50;
	        	 } } }
	    /*Ends for FollowUp cases which are in claim ready status*/
	    /*Starts for FollowUp cases which are in claim ready status(REJ)*/
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfFpCaseLst = new ArrayList<EhfCaseFollowupClaim>();
		criteriaList.add(new GenericDAOQueryCriteria("followUpStatus", ClaimsConstants.CLAIM_FP_READY_REJ_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("caseFollowupId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		ehfFpCaseLst = genericDao.findAllByCriteria(EhfCaseFollowupClaim.class, criteriaList);
	    if(ehfFpCaseLst!=null && ehfFpCaseLst.size()>0){
	    	 hParamMap.put("caseStatus", ClaimsConstants.CLAIM_FP_READY_REJ_BANK); 
	    	 hParamMap.put("sentAgain", "Y");
	    	 
	    	 int listCount = ehfFpCaseLst.size()/50;
	    	 int loopSize = ehfFpCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfFpCaseLst.get(listTrackerCount).getCaseFollowupId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	try {
								followUpDao.updateFollowupPayments(hParamMap);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[50];
	        		 for(int i=0;i<50;i++){
	     	        	caseSelected[i]=ehfFpCaseLst.get(listTrackerCount).getCaseFollowupId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	try {
							followUpDao.updateFollowupPayments(hParamMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
	        	    	loopSize=loopSize-50;
	        	 } } } }
	    /*Ends for FollowUp cases which are in claim ready status(REJ)*/
		System.out.println( "Scheduler to Generate follow up claim files  ended in TS");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateTDSFile(HashMap hParamMap,ClaimsFlowVO claimFlowVO){
		
		String[] claimStatusArray = new String[]{ClaimsConstants.CLAIM_READY_REJ_BANK};
		
		hParamMap.put("TdsRemarks", ClaimsConstants.CLAIM_SENT_RMK);
   	    hParamMap.put("UserRole", ClaimsConstants.TDSPAY_GRP_ID);
   	    claimFlowVO.setRoleId(ClaimsConstants.TDSPAY_GRP_ID);
   	    String[] caseSelected = null;
   	    for(int statusCount=0;statusCount<claimStatusArray.length;statusCount++){
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		hParamMap.put("caseStatus", claimStatusArray[statusCount]);
		if(claimStatusArray[statusCount].equalsIgnoreCase(ClaimsConstants.CLAIM_READY_REJ_BANK))
		hParamMap.put("sentAgain", "Y");
		else if(claimStatusArray[statusCount].equalsIgnoreCase(ClaimsConstants.CLAIM_READY_BANK))
		hParamMap.put("sentAgain", "N");	
		/*Starts for normal TDS cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("patientScheme", config.getString("Scheme.EHS"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("paymentStatus", claimStatusArray[statusCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("paymentType",ClaimsConstants.CLAIM_FORM_PAYMENT,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", hParamMap.get("SCHEME_ID"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    List<EhfClaimTdsPayment> ehfTdsLst = genericDao.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
	    if(ehfTdsLst!=null && ehfTdsLst.size()>0){	    	 
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.CLAIM_FORM_PAYMENT);  
	    	 hParamMap.put("patScheme",  config.getString("Scheme.EHS"));
	    	 int listCount = ehfTdsLst.size()/100;
	    	 int loopSize = ehfTdsLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=100){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateTDSClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[100];
	        		 for(int i=0;i<100;i++){
	     	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateTDSClaimStatus(hParamMap);
	        	    	loopSize=loopSize-100;
	        	 } } }
	    /*Ends for TDS cases which are in claim ready status*/
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfTdsLst = new ArrayList<EhfClaimTdsPayment>();
		/*Starts for followup TDS cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("paymentStatus", claimStatusArray[statusCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("paymentType",ClaimsConstants.FPCLAIM_FORM_PAYMENT,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", hParamMap.get("SCHEME_ID"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    ehfTdsLst = genericDao.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
	    if(ehfTdsLst!=null && ehfTdsLst.size()>0){	    	 
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.FPCLAIM_FORM_PAYMENT);    	 
	    	 int listCount = ehfTdsLst.size()/100;
	    	 int loopSize = ehfTdsLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=100){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateTDSClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[100];
	        		 for(int i=0;i<100;i++){
	     	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateTDSClaimStatus(hParamMap);
	        	    	loopSize=loopSize-100;
	        	 } } }
	    /*Ends for followup TDS cases which are in claim ready status*/
	    criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    ehfTdsLst = new ArrayList<EhfClaimTdsPayment>();
		/*Starts for Erroneous TDS cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("paymentStatus", claimStatusArray[statusCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("paymentType",ClaimsConstants.ERRCLAIM_FORM_PAYMENT,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", hParamMap.get("SCHEME_ID"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    ehfTdsLst = genericDao.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
	    if(ehfTdsLst!=null && ehfTdsLst.size()>0){	    	 
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.ERRCLAIM_FORM_PAYMENT);    	 
	    	 int listCount = ehfTdsLst.size()/100;
	    	 int loopSize = ehfTdsLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=100){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	        	        	listTrackerCount++;
	        	        	}
	        	            claimFlowVO.setCaseSelected(caseSelected);
	        	        	hParamMap.put("claimFlowVO", claimFlowVO);
	        	        	//updating claim Status while paying
	        	        	updateTDSClaimStatus(hParamMap);
	        	        	loopSize=loopSize-loopSize;
	        	  }else{
	        		 caseSelected = new String[100];
	        		 for(int i=0;i<100;i++){
	     	        	caseSelected[i]=ehfTdsLst.get(listTrackerCount).getTdsPaymentId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateTDSClaimStatus(hParamMap);
	        	    	loopSize=loopSize-100;
	        	 } } }
	    /*Ends for Erroneous TDS cases which are in claim ready status*/
   	    }
	}
	/**
	 * Updating Claims Status
	 * 
	 * @param HashMap lparamMap
	 * @return HashMap
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap updateClaimStatus(HashMap lparamMap) {
		
		boolean isInsert = false;
		Map lFileMap = new HashMap();
		String lResult = ClaimsConstants.EMPTY, lStrCaseId = null;
		List lFileList = new ArrayList();
		List lCaseIdLst = new ArrayList();
		Map lContentMap = null;
		int lFlag = 0, iUploadStatus = 0;
		List resultList = new ArrayList();
		String lStrNextStatus = null;
		GenerateAsciiFile gaf = new GenerateAsciiFile();
		ArrayList lContentList = new ArrayList();
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		try {
			lStrNextStatus = getNextStatus(
					(String) lparamMap.get("caseStatus"),
					claimFlowVO.getRoleId(), claimFlowVO.getActionDone());

			lparamMap.put("nextCaseStatus", lStrNextStatus);
			claimFlowVO.setCurrStatus((String) lparamMap.get("caseStatus"));
			claimFlowVO.setNextStatus((String) lparamMap.get("nextCaseStatus"));

			lparamMap.put("currentDate",
					new java.sql.Timestamp(new Date().getTime()));
            //updating payment check flag in ehf_case and calculating tds/rf/hosp amount
			isInsert = updatePaymentCheckValue(lparamMap);
			if (isInsert) {
                //getting hospital/rf account details
				lContentList = getHospAccountDtls(lparamMap);

				if (lContentList.size() > 0) {
					//generating ascii file 
					//lFileMap = gaf.generateAsciiFile(lContentList, lparamMap);
					
					//Inserting file content in Table
					lFileMap=insertIntoPaymentTable(lContentList, lparamMap);	
					
					lFlag = Integer.parseInt((String) lFileMap.get("Flag"));

					if (lContentList.size() > 2) {
						lFileList = (ArrayList) lContentList.get(0);
						lCaseIdLst = (ArrayList) lContentList.get(2);
					}
				}

				if (lFlag > 0) {
					int CaseIdLst = lCaseIdLst.size();
					for (int j = 0; j < CaseIdLst; j++) {
						
						lparamMap.put("CASE_ID", (String) lCaseIdLst.get(j));

						// HashMap Contains the Beneficiary Account Information
						lContentMap = new HashMap();
						lContentMap = (HashMap) lFileList.get(j);
						if ((String) lContentMap.get("BENEFICIARY_ACCOUNT_NO") != null) {
							lparamMap.put("SRC_ACCT_NO", (String) lContentMap
									.get("CLAINT_AC_NUMBER"));
							lparamMap.put("DES_ACCT_NO", (String) lContentMap
									.get("BENEFICIARY_ACCOUNT_NO"));
							
							lparamMap.put("TRANSACTION_AMOUNT", (String) lContentMap
									.get("TRANSACTION_AMOUNT"));
							lparamMap.put("ACC_TRANSACTION_AMOUNT", (String) lContentMap
									.get("TRANSACTION_AMOUNT"));
							lparamMap.put("ACC_TOTAL_AMOUNT", (String) lContentMap
									.get("TOTAL_CLAIM_AMOUNT"));
							lparamMap.put("ACC_REST_AMOUNT", (String) lContentMap
									.get("REST_AMOUNT"));
							lparamMap.put("ACC_HOSP_ID", (String) lContentMap
									.get("HOSP_ID"));
							lparamMap.put("ACC_HOSP_TYPE", (String) lContentMap
									.get("HOSP_TYPE"));
							lparamMap.put("TDS_RF_TYPE", (String)lContentMap
									.get("TDS_RF_TYPE"));
							lparamMap.put("REJECTED_PAYMENT", (String)lContentMap
									.get("REJECTED_PAYMENT"));
							
							lStrCaseId = (String) lCaseIdLst.get(j);
							//Chandana - 6444 - 14-02-2025 - Added if condition for spoke, hub, HM and SM
							if(lStrCaseId.endsWith("SPOKE") || lStrCaseId.endsWith("AGENCY") || lStrCaseId.endsWith("HUB") || lStrCaseId.endsWith("SM") || lStrCaseId.endsWith("HM") ){
							}
							else if (lStrCaseId.endsWith(ClaimsConstants.G)
									|| lStrCaseId.endsWith(ClaimsConstants.TDS)
									|| lStrCaseId.endsWith(ClaimsConstants.GD) || lStrCaseId.endsWith("/SD/TDS")) {
								lparamMap.put("TDS_PAYMENT_ID",
										(String) lCaseIdLst.get(j));
								//logger.info("TDS OR RF CASEID : ******************************"+lStrCaseId);
								isInsert = updateClaimAccountDetails(lparamMap); // if the payment is related to TDS or RF
							} else {
								//logger.info("normal payment CASEID : ******************************"+lStrCaseId);
								isInsert = ChangeClaimStatus(lparamMap); // for
								// normal payment
								if (isInsert) {
									lResult = ClaimsConstants.ONE;
								} else {
									lResult = ClaimsConstants.ZERO;
								}
							}

						}

					}
				/*	if (isInsert) {
						//saving file in two folders
						resultList = claimsPaymentDao
								.insertUploadFile(lparamMap);
						iUploadStatus = Integer.parseInt(resultList.get(0)
								.toString());
					}*/
					iUploadStatus = 0;
					lResult = ClaimsConstants.ONE;
				} else {
					lResult = ClaimsConstants.ZERO;
				}
			} else {
				lResult = ClaimsConstants.TWO;
				iUploadStatus = 0;
			}
			lparamMap.put("Message", lResult);
			lparamMap.put("UploadStatus", Integer.toString(iUploadStatus));

		} catch (Exception e) {
			logger.error("Error occured in updateClaimStatus() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
			
		}
		return lparamMap;
	}

	/**
	 * Gets the hosp account dtls.
	 *
	 * @param lparamMap the lparam map
	 * @return the hosp account dtls
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList getHospAccountDtls(HashMap lparamMap) {
		List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
		ArrayList lResList = new ArrayList();
		List lFileDataList = new ArrayList();
		List lCaseList = new ArrayList();
		String temp = ClaimsConstants.EMPTY; //Chandana - 6444 - 11-02-2025
		String hospId = ClaimsConstants.EMPTY; //Chandana - 6444 - 11-02-2025
		String patientScheme=null;
		if(lparamMap.get("patScheme")!=null)
			patientScheme = (String)lparamMap.get("patScheme") ;
		double TrustPctAmt = 0, tdsAmt = 0;
		double TrustPctAmtSur = 0, tdsAmtSur = 0;
		double hubAmt = 0, agencyAmt = 0, hubMnt = 0, spokeMnt = 0 ; //Chandana - 6444 - added new variables
		double lFlagVal = ClaimsConstants.ZERO_DBL_VAL;
		Map lFileMap = new HashMap();
		int lTotalCases = 0;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		String lStrCaseStatus = (String) lparamMap.get("nextCaseStatus");
		String lStrOldCaseStatus = (String) lparamMap.get("caseStatus");
		StringBuffer query = new StringBuffer();
		String paymentType = (String) lparamMap.get("PaymentType");
		String scheme=(String)lparamMap.get("SCHEME_ID");
		String args[] = new String[claimFlowVO.getCasesReady().length];
		for (int i = 0; i < claimFlowVO.getCasesReady().length; i++) {
			args[i] = claimFlowVO.getCasesReady()[i];
		}
		try {

			query.append(" select distinct b.hospCity as hospAdd,e.bankId as bankId,d.bankBranch as bankBranch,d.bankName as bankName,d.ifcCode as ifscCode,d.bankCategory as bankCategory,");
			query.append(" e.accountNo as accNo,e.hospAccName as hospAccName,e.id.hospId as hospId,b.hospActiveYN||'' as hospActiveYN,b.hospType as HOSPTYPE,b.hospEmail as email  ");
			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
				query.append(" ,g.errClaimId as caseId,g.totClaimAmt||'' as totalClaim,g.trustPctAmt as trustPctAmt,g.tdsPctAmt as tdsPctAmt,g.tdsHospPctAmt as tdsHospPctAmt,g.hospPctAmt as hospPctAmt,g.schemeId as schemeType ");
			} else {
				query.append(" ,a.caseId as caseId,c.totClaimAmt||'' as totalClaim,c.trustPctAmt as trustPctAmt,c.tdsPctAmt as tdsPctAmt,c.tdsHospPctAmt as tdsHospPctAmt,c.hospPctAmt as hospPctAmt,a.schemeId as schemeType ");
			}
			//Chandana - 6444 - 11-02-2025
			query.append(" , c.hubAmt AS HUB_AMT, c.agencyPctAmt AS agencyPctAmt, c.hmAmt AS HM_MNT, c.smAmt AS SM_MNT, c.hubspokeFlag AS HUBSPOKEFLAG ");
			query.append(" from EhfCase a,EhfmHospitals b,EhfCaseClaim c,EhfmBankMaster d,EhfmHospBankDtls e, EhfPatient f ");
			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
				query.append(" ,EhfErroneousClaim g ");
			}

			query.append(" where a.caseId = c.caseId and b.hospId = e.id.hospId and d.bankId = e.bankId");
			if (paymentType != null
					&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
					&& paymentType
							.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
				query.append(" and a.caseId = g.caseId and g.errClaimId in ( ");
				for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
					query.append(" ? ");
					if (i == claimFlowVO.getCaseSelected().length - 1)
						query.append(" ) ");
					else
						query.append(" , ");
				}
			} else {
				query.append("  and a.caseId in ( ");
				for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
					query.append(" ? ");
					if (i == claimFlowVO.getCaseSelected().length - 1)
						query.append(" ) ");
					else
						query.append(" , ");
				}
			}
			query.append(" and f.patientId = a.casePatientNo and a.caseHospCode = b.hospId ");
			
			if(scheme!=null && !("").equalsIgnoreCase(scheme))
			{
				query.append( " and e.id.scheme='"+scheme+"' ");
			}
            
			lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					query.toString(), args);

			for (ClaimsFlowVO claimsVO : lstWorkList) {
                //hospital acconuts details for that case
				lFileMap = new HashMap();
				lFileMap.put("UNIQUE_ID", claimsVO.getCaseId());

				lCaseList.add(claimsVO.getCaseId());
				lFileMap.put("BENEFICIARY_ACCOUNT_NAME",claimsVO.getHospAccName());
				lFileMap.put("BENEFICIARY_ID", claimsVO.getHospId());
				lFileMap.put("BENEFICIARY_ADDR", claimsVO.getHospAdd());
				lFileMap.put("BENEFICIARY_BANK_NAME", claimsVO.getBankName());
				lFileMap.put("BANK_BRANCH", claimsVO.getBankBranch());
				lFileMap.put("BENEFICIARY_ACCOUNT_NO", claimsVO.getAccNo());
				lFileMap.put("TOTAL_CLAIM_AMOUNT", claimsVO.getTotalClaim());
				lFileMap.put("HOSP_TYPE",claimsVO.getHOSPTYPE()+"");
				lFileMap.put("HOSP_ID", claimsVO.getHospId());
				lFileMap.put("TRANSACTION_TYPE", claimsVO.getBankCategory());
				lFileMap.put("EMAIL_ID", claimsVO.getEmail());
				//Chandana - 6444 - 11-02-2025
				/*temp = lstWorkList.get(0).getHUBSPOKEFLAG() == null ? ClaimsConstants.EMPTY: lstWorkList.get(0).getHUBSPOKEFLAG();
	                temp = lstWorkList.get(0).getBankName() == null ? ClaimsConstants.EMPTY: lstWorkList.get(0).getBankName();
	                lFileMap.put("BENEFICIARY_BANK_NAME", temp);
	                temp = lstWorkList.get(0).getBankBranch() == null ? ClaimsConstants.EMPTY: lstWorkList.get(0).getBankBranch();
	                lFileMap.put("BANK_BRANCH", temp);
	                temp = lstWorkList.get(0).getBankId() == null ? ClaimsConstants.EMPTY: lstWorkList.get(0).getBankId();
	                lFileMap.put("BENEFICIARY_BANK_ID", temp);
	                temp = lstWorkList.get(0).getIfscCode() == null ? ClaimsConstants.EMPTY: lstWorkList.get(0).getIfscCode();  
	                lFileMap.put("BENEFICIARY_BANK_IFC_CODE", temp);
				*/
				if(lStrOldCaseStatus!=null && lStrOldCaseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
				{
					EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, claimsVO.getCaseId());
					
					if(ehfSurplusTdsClaim!=null)
					{
						if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
						{
							lFileMap.put("TRANSACTION_AMOUNT", ehfSurplusTdsClaim.getTdsHospPctAmt().toString());
							lFileMap.put("REST_AMOUNT", claimsVO.getTdsPctAmt()
									.toString());
						}
						else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
						{
							lFileMap.put("TRANSACTION_AMOUNT", ehfSurplusTdsClaim.getHospPctAmt().toString());
							lFileMap.put("REST_AMOUNT", claimsVO.getTrustPctAmt()
									.toString());
						}
					}
				}
				else
				{
				if (claimsVO.getHospPctAmt() != null
						&& claimsVO.getHospPctAmt() != 0) {
					lFileMap.put("TRANSACTION_AMOUNT", claimsVO.getHospPctAmt()
							.toString());
					lFileMap.put("REST_AMOUNT", claimsVO.getTrustPctAmt()
							.toString());
				} else if(claimsVO
						.getTdsHospPctAmt()!=null && claimsVO
						.getTdsHospPctAmt()!=0){
					lFileMap.put("TRANSACTION_AMOUNT", claimsVO
							.getTdsHospPctAmt().toString());
					lFileMap.put("REST_AMOUNT", claimsVO.getTdsPctAmt()
							.toString());
				}
				}

				lFileMap.put("BENEFICIARY_BANK_ID", claimsVO.getBankId());
				lFileMap.put("BENEFICIARY_BANK_IFC_CODE",claimsVO.getIfscCode());
				lFileMap.put("SCHEME_ID", claimsVO.getSchemeType());
				
				if (paymentType != null
						&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
						&& paymentType
								.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
					
					if(claimsVO.getSchemeType()!=null && claimsVO.getSchemeType().equalsIgnoreCase(config.getString("TG")))
					{
						StringBuffer query1=new StringBuffer();
						query1.append("select a.accountNo as accNo, b.ifcCode as ifscCode from EhfmTrustActDtls a ,EhfmBankMaster b where a.bankId=b.bankId and a.activeYn='Y' and a.actType='"+ClaimsConstants.ERRCLAIM_CLIENT_TG_ACTTYPE+"'");
						List<ClaimsFlowVO> accntNoList=genericDao.executeHQLQueryList(ClaimsFlowVO.class,query1.toString());
						if(accntNoList!=null && accntNoList.size()>0)
						{
							if(accntNoList.get(0)!=null)
							{
								if(accntNoList.get(0).getAccNo()!=null  && !"".equalsIgnoreCase(accntNoList.get(0).getAccNo()))
								{
									lFileMap.put("CLAINT_AC_NUMBER",
											accntNoList.get(0).getAccNo());
								}
								if(accntNoList.get(0).getIfscCode()!=null  && !"".equalsIgnoreCase(accntNoList.get(0).getIfscCode()))
								{
									lFileMap.put("CLIENT_IFSC_CODE",
											accntNoList.get(0).getIfscCode());
								}
								
							}
						}
						
					lFileMap.put("CLAINT_AC_CODE",
							ClaimsConstants.ERRCLAIM_CLIENT_TG_CODE);
					}
					else{
					lFileMap.put("CLAINT_AC_NUMBER",
								ClaimsConstants.ERRCLAIM_CLIENT_AP_ACCNO);
					lFileMap.put("CLAINT_AC_CODE",
							ClaimsConstants.ERRCLAIM_CLIENT_AP_CODE);
					}
					if(lStrOldCaseStatus.equals(ClaimsConstants.CLAIM_ERR_READY_REJ_BANK))
					{
						lFileMap.put("REJECTED_PAYMENT", "true");
					}
					else
					{
						lFileMap.put("REJECTED_PAYMENT", "false");
					}
				} else {
					
					if(claimsVO.getSchemeType()!=null && claimsVO.getSchemeType().equalsIgnoreCase(config.getString("TG")))
						{							
							if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
							{
								StringBuffer query1=new StringBuffer();
								query1.append("select a.accountNo as accNo, b.ifcCode as ifscCode from EhfmTrustActDtls a ,EhfmBankMaster b where a.bankId=b.bankId and a.activeYn='Y' and a.actType='"+ClaimsConstants.CLAIM_JHS_CLIENT_TG_ACTTYPE+"'");
								List<ClaimsFlowVO> accntNoList=genericDao.executeHQLQueryList(ClaimsFlowVO.class,query1.toString());
								if(accntNoList!=null && accntNoList.size()>0)
								{
									if(accntNoList.get(0)!=null)
									{
										if(accntNoList.get(0).getAccNo()!=null  && !"".equalsIgnoreCase(accntNoList.get(0).getAccNo()))
										{
											lFileMap.put("CLAINT_AC_NUMBER",
													accntNoList.get(0).getAccNo());
										}
										if(accntNoList.get(0).getIfscCode()!=null  && !"".equalsIgnoreCase(accntNoList.get(0).getIfscCode()))
										{
											lFileMap.put("CLIENT_IFSC_CODE",
													accntNoList.get(0).getIfscCode());
										}
										
									}
								}
							
								lFileMap.put("CLAINT_AC_CODE",
										ClaimsConstants.CLAIM_JHS_CLIENT_TG_CODE);
							}
						else
							{
							StringBuffer query1=new StringBuffer();
							query1.append("select a.accountNo as accNo, b.ifcCode as ifscCode from EhfmTrustActDtls a ,EhfmBankMaster b where a.bankId=b.bankId and a.activeYn='Y' and a.actType='"+ClaimsConstants.CLAIM_CLIENT_TG_ACTTYPE+"'");
							List<ClaimsFlowVO> accntNoList=genericDao.executeHQLQueryList(ClaimsFlowVO.class,query1.toString());
							if(accntNoList!=null && accntNoList.size()>0)
							{
								if(accntNoList.get(0)!=null)
								{
									if(accntNoList.get(0).getAccNo()!=null  && !"".equalsIgnoreCase(accntNoList.get(0).getAccNo()))
									{
										lFileMap.put("CLAINT_AC_NUMBER",
												accntNoList.get(0).getAccNo());
									}
									if(accntNoList.get(0).getIfscCode()!=null  && !"".equalsIgnoreCase(accntNoList.get(0).getIfscCode()))
									{
										lFileMap.put("CLIENT_IFSC_CODE",
												accntNoList.get(0).getIfscCode());
									}
									
								}
							}
							
							lFileMap.put("CLAINT_AC_CODE",
									ClaimsConstants.CLAIM_CLIENT_TG_CODE);						
							}
							}
							
					if(lStrOldCaseStatus.equals("CD141"))
					{
						lFileMap.put("REJECTED_PAYMENT", "true");
					}
					else
					{
						lFileMap.put("REJECTED_PAYMENT", "false");
					}
						
				}

				if (claimsVO.getTrustPctAmt() != null) {
					TrustPctAmt = claimsVO.getTrustPctAmt();
					if(claimsVO.getTrustPctAmt()>0);
					lFileMap.put("TDS_RF_TYPE",ClaimsConstants.RF);
					
				}
				if (claimsVO.getTdsPctAmt() != null) {
					tdsAmt = claimsVO.getTdsPctAmt();
					if(claimsVO.getTdsPctAmt()>0)
					lFileMap.put("TDS_RF_TYPE",ClaimsConstants.TDS);
					if((claimsVO.getTdsPctAmt()==0 && claimsVO.getTrustPctAmt()==0 ))
					lFileMap.put("TDS_RF_TYPE",ClaimsConstants.TDS);	
					
				}

				lFileDataList.add(lFileMap);
                //getting RF acconts details
				if (TrustPctAmt > 0 || tdsAmt > 0  )
				{ // 093
					lFlagVal = 0;
					HashMap ldataMap = new HashMap();
					ldataMap.put("TrustAmount", TrustPctAmt);
					ldataMap.put("TdsAmount", tdsAmt);
					lFileMap.put("CaseStatus", lStrCaseStatus);
					lFileMap.put("OldCaseStatus", lStrOldCaseStatus);
					lFileMap.put("PaymentType",paymentType);
					lFileMap.put("CaseList", lCaseList);
					lFileMap.put("FileDataList", lFileDataList);
					lFileMap.put("patScheme",patientScheme);
					ldataMap.put("FileMap", lFileMap);
					//Chandana - 6444 - 10-02-2025
					if(claimsVO.getHUB_AMT() != null){
						hubAmt = (Double) claimsVO.getHUB_AMT();
					}
					/*if(claimsVO.getSPOKE_AMT() != null){
						agencyAmt = (Double) claimsVO.getSPOKE_AMT();
					}*/
					
					HashMap lresultMap = claimsPaymentDao.payTDSFund(ldataMap); // Seperate
																				// method
																				// which
																				// will
																				// take
																				// the
																				// TDS
																				// and
																				// Revolving
																				// funds
																				// and
																				// returns
																				// a
																				// payment
																				// hashmap
																				// file
					lCaseList = (ArrayList) lresultMap.get("CaseList");
					lFileDataList = (ArrayList) lresultMap.get("FileDataList");
				}
				else if((lStrOldCaseStatus!=null && lStrOldCaseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED)))
				{
					lFlagVal = 0;
					HashMap ldataMap = new HashMap();
					lFileMap.put("CaseStatus", lStrCaseStatus);
					lFileMap.put("OldCaseStatus", lStrOldCaseStatus);
					lFileMap.put("PaymentType",paymentType);
					lFileMap.put("CaseList", lCaseList);
					lFileMap.put("FileDataList", lFileDataList);
					ldataMap.put("FileMap", lFileMap);
					
					HashMap lresultMap = claimsPaymentDao.payTDSFundSurplus(ldataMap); // Seperate
																				// method
																				// which
																				// will
																				// take
																				// the
																				// TDS
																				// and
																				// Revolving
																				// funds
																				// and
																				// returns
																				// a
																				// payment
																				// hashmap
																				// file
					lCaseList = (ArrayList) lresultMap.get("CaseList");
					lFileDataList = (ArrayList) lresultMap.get("FileDataList");
				}
				//Chandana - 6444 - 11-02-2025
				temp = claimsVO.getHUB_AMT() == null ? ClaimsConstants.EMPTY: claimsVO.getHUB_AMT().toString();
                if (!ClaimsConstants.EMPTY.equalsIgnoreCase(temp))
                {
                  hubAmt = Double.parseDouble(temp);
                }
                temp = claimsVO.getAgencyPctAmt() == null ? ClaimsConstants.EMPTY: claimsVO.getAgencyPctAmt().toString();
                if (!ClaimsConstants.EMPTY.equalsIgnoreCase(temp))
                {
                	agencyAmt = Double.parseDouble(temp);
                }
                temp = claimsVO.getHospId() == null ? ClaimsConstants.EMPTY: claimsVO.getHospId();
                if (!ClaimsConstants.EMPTY.equalsIgnoreCase(temp))
                {
                  hospId = temp;
                }
                temp = claimsVO.getHM_MNT() == null ? ClaimsConstants.EMPTY: claimsVO.getHM_MNT().toString();
                if (!ClaimsConstants.EMPTY.equalsIgnoreCase(temp))
                {
                  hubMnt = Double.parseDouble(temp);
                }
                temp = claimsVO.getSM_MNT() == null ? ClaimsConstants.EMPTY: claimsVO.getSM_MNT().toString();
                if (!ClaimsConstants.EMPTY.equalsIgnoreCase(temp))
                {
                  spokeMnt = Double.parseDouble(temp);
                }
				if(hubAmt > ClaimsConstants.ZERO_VAL){
					HashMap ldataMap = new HashMap();
	                  ldataMap.put("hubAmt", hubAmt);
	                 ldataMap.put("agencyAmt", agencyAmt);
	                 ldataMap.put("hemoHospId", hospId);
	                 ldataMap.put("hubMnt", hubMnt);
	                 ldataMap.put("spokeMnt", spokeMnt);
	                  lFileMap.put("CaseStatus", lStrCaseStatus);
	                  lFileMap.put("PaymentType", paymentType);
	                  lFileMap.put("CaseList", lCaseList);
	                  lFileMap.put("FileDataList", lFileDataList);
	                  ldataMap.put("FileMap", lFileMap);
	                  ldataMap.put("UserState",(String)lparamMap.get("CEOState"));
	                  HashMap lresultMap = payHubSpoke(ldataMap); //Seperate method which will take the TDS and Revolving funds and returns a payment hashmap file
	                  lCaseList = (ArrayList<Object>) lresultMap.get("CaseList");
	                  lFileDataList = (ArrayList<Object>) lresultMap.get("FileDataList");
				}
				lTotalCases++;
			}
			lResList.add(lFileDataList);
			//sequence for claim_upload table Seperated by namratha for JHS
			String smsNextVal=null; 
			if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
				smsNextVal = getSequence("JHSCLAIMUPLOADFILE");
			else
				smsNextVal = getSequence("CLAIMUPLOADFILE");
			lResList.add(smsNextVal);
			lResList.add(lCaseList);
		} catch (Exception e) {
			logger.error("Error occured in getHospAccountDtls() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lResList;
	}
//Chandana - 6444 - 11-02-2025
	/**
     * 
     * @param lmap
     * @return HashMap
     * @Description This method is used for show the different row of Hub and Spoke amount details in payment file.
     */
    public HashMap payHubSpoke(HashMap lmap) throws Exception
    {
    	double lFlagVal=ClaimsConstants.ZERO_DBL_VAL, hubAmt = ClaimsConstants.ZERO_DBL_VAL, agencyAmt = ClaimsConstants.ZERO_DBL_VAL, hubMnt = ClaimsConstants.ZERO_DBL_VAL, spokeMnt = ClaimsConstants.ZERO_DBL_VAL;
        String lbeneficiary_account_name = ClaimsConstants.EMPTY, lbeneficiary_id = ClaimsConstants.EMPTY, lbeneficiary_addr = ClaimsConstants.EMPTY, lbeneficiary_bank_name = ClaimsConstants.EMPTY;
        String lbank_branch = ClaimsConstants.EMPTY, lbeneficiary_account_no = ClaimsConstants.EMPTY, lbeneficiary_bank_id = ClaimsConstants.EMPTY, lbeneficiary_bank_ifc_code = ClaimsConstants.EMPTY;
        String lclaint_ac_code = ClaimsConstants.EMPTY, lclaint_ac_number = ClaimsConstants.EMPTY, ltransaction_type = ClaimsConstants.EMPTY, caseStatus = ClaimsConstants.EMPTY, paymentType = ClaimsConstants.EMPTY, actType = ClaimsConstants.EMPTY, eMail = ClaimsConstants.EMPTY;
        HashMap lresultMap = new HashMap();
        StringBuffer lStrBuffer = null;
        ArrayList<Object> lFileDataList = null;
        ArrayList<Object> lCaseList = null;
        HashMap<Object,Object> lFileMap = null;
        String flupPayment = ClaimsConstants.EMPTY;
        List<ClaimsFlowVO> accountList = new ArrayList<ClaimsFlowVO>();
        String userRole = ClaimsConstants.EMPTY ,hospId = ClaimsConstants.EMPTY;
        FileDataVO fileData = new FileDataVO();
        	if (lmap.get("hubAmt") != null)
        		hubAmt = Double.parseDouble(lmap.get("hubAmt").toString());
            if (lmap.get("agencyAmt") != null)
            	agencyAmt = Double.parseDouble(lmap.get("agencyAmt").toString());
            if(lmap.get("hubMnt") != null)
            	hubMnt = Double.parseDouble(lmap.get("hubMnt").toString());
            if(lmap.get("spokeMnt") != null)
            	spokeMnt = Double.parseDouble(lmap.get("spokeMnt").toString());
            if (lmap.get("FileMap") != null)
            	lFileMap = (HashMap<Object,Object>) lmap.get("FileMap");
            if (lFileMap.get("userRole") != null)
              userRole = (String) lFileMap.get("userRole");
            if (lFileMap.get("CaseStatus") != null)
            caseStatus = (String) lFileMap.get("CaseStatus");
            if (lFileMap.get("PaymentType") != null)
            paymentType = (String) lFileMap.get("PaymentType");
            if (lFileMap.get("EMAIL_ID") != null)
            eMail = (String) lFileMap.get("EMAIL_ID");
            if (lFileMap.get("CaseList") != null)
                lCaseList = (ArrayList<Object>) lFileMap.get("CaseList");
                if (lFileMap.get("FileDataList") != null)
                lFileDataList = (ArrayList<Object>) lFileMap.get("FileDataList");
                String lStrUniqueId = ClaimsConstants.EMPTY;
                String caseid = (String) lFileMap.get("UNIQUE_ID");
                lclaint_ac_code = (String) lFileMap.get("CLAINT_AC_CODE");
                lclaint_ac_number = (String) lFileMap.get("CLAINT_AC_NUMBER");
                lbeneficiary_addr = (String)lFileMap.get("BENEFICIARY_ADDR");
                hospId = (String) lmap.get("hemoHospId");
				// For Hub
                lStrUniqueId = caseid + "/HUB";
                lStrBuffer = new StringBuffer();
                lStrBuffer.append(" SELECT DISTINCT MPG.HUB_HOSP_ID ID, HAD.HUB_ACCOUNT_NO \"accNo\", HAD.HUB_ACCOUNT_NAME NAMEASPERACT,HAD.HUB_BANK_ID \"bankId\",EBM.ifc_code \"ifscCode\",EBM.bank_name \"bankName\", ");
                lStrBuffer.append(" EBM.bank_branch \"bankBranch\",EBM.bank_category \"bankCategory\" FROM EHFM_HOSP_HUB_SPOKE_MPG MPG,EHF_HUB_AGENCY_ACCT_DTLS HAD,EHFM_BANK_MASTER EBM WHERE ");
                lStrBuffer.append(" MPG.HUB_HOSP_ID=HAD.HUB_ID AND EBM.BANK_ID= HAD.HUB_BANK_ID AND MPG.ACTIVE_YN=? AND HAD.ACTIVE_YN=? AND MPG.SPOKE_HOSP_ID=? ");
                String[] arg = new String[3];
                arg[0] = ClaimsConstants.Y;
                arg[1] = ClaimsConstants.Y;
                arg[2] = hospId;
                accountList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrBuffer.toString(),arg);
                if(accountList.size()>0) 
                {
                    for(int i=0;i<accountList.size();i++) 
                    {
                        if(accountList.get(i).getNAMEASPERACT() != null && accountList.get(i).getNAMEASPERACT()!= ClaimsConstants.EMPTY ) 
                        {
                        	fileData.setHospId(accountList.get(i).getID());
                        	fileData.setAccNo(accountList.get(i).getAccNo());
                        	fileData.setNAMEASPERACT(accountList.get(i).getNAMEASPERACT());
                        	fileData.setBankId(accountList.get(i).getBankId());
                        	fileData.setIfscCode(accountList.get(i).getIfscCode());
                        	fileData.setBankName(accountList.get(i).getBankName());
                        	fileData.setBankBranch(accountList.get(i).getBankBranch());
                        	fileData.setBankCategory(accountList.get(i).getBankCategory());
                        	fileData.setUniqueId(lStrUniqueId);
                        	fileData.setEmail(eMail);//need to confirm once with rajesh sir
                        	fileData.setAmount(Double.toString(hubAmt));
                        	fileData.setLbeneficiary_addr(lbeneficiary_addr);
                        	fileData.setLclaint_ac_code(lclaint_ac_code);
                        	fileData.setLclaint_ac_number(lclaint_ac_number);
                        }
                    }
                }
                else
                {
                    lFlagVal++;
                }
                if ( lFlagVal == ClaimsConstants.ZERO_VAL)
                {
                    lFileMap = new HashMap<Object,Object>(); // List for generate file for upload(witch is having the all mandatory filed) 
                    lCaseList.add(lStrUniqueId);
                    lFileDataList.add(setfileMap(fileData));
                }
				//For Agency
                lStrUniqueId = caseid + "/AGENCY";
                lStrBuffer = new StringBuffer();
                lStrBuffer.append(" SELECT DISTINCT MPG.SPOKE_HOSP_ID ID,HAD.AGENCY_ACCOUNT_NO \"accNo\",HAD.AGENCY_ACCOUNT_NAME NAMEASPERACT,HAD.AGENCY_BANK_ID \"bankId\",EBM.ifc_code \"ifscCode\", ");
            	lStrBuffer.append(" EBM.bank_name \"bankName\",EBM.bank_branch \"bankBranch\",EBM.bank_category \"bankCategory\" FROM EHFM_HOSP_HUB_SPOKE_MPG MPG,EHF_HUB_AGENCY_ACCT_DTLS HAD, ");
            	lStrBuffer.append(" EHFM_BANK_MASTER EBM WHERE MPG.HUB_HOSP_ID=HAD.HUB_ID AND EBM.BANK_ID= HAD.AGENCY_BANK_ID AND MPG.ACTIVE_YN=? AND MPG.SPOKE_HOSP_ID=? ");
                arg = new String[2];
                arg[0] = ClaimsConstants.Y;
                arg[1] = hospId;
                accountList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrBuffer.toString(),arg);
                if(accountList.size()>0) 
                {
                    for(int i=0;i<accountList.size();i++) 
                    {
                        if(accountList.get(i).getNAMEASPERACT() != null && accountList.get(i).getNAMEASPERACT()!= ClaimsConstants.EMPTY ) 
                        {
                        	fileData.setHospId(accountList.get(i).getID());
                        	fileData.setAccNo(accountList.get(i).getAccNo());
                        	fileData.setNAMEASPERACT(accountList.get(i).getNAMEASPERACT());
                        	fileData.setBankId(accountList.get(i).getBankId());
                        	fileData.setIfscCode(accountList.get(i).getIfscCode());
                        	fileData.setBankName(accountList.get(i).getBankName());
                        	fileData.setBankBranch(accountList.get(i).getBankBranch());
                        	fileData.setBankCategory(accountList.get(i).getBankCategory());
                        	fileData.setUniqueId(lStrUniqueId);
                        	fileData.setEmail(eMail);//need to confirm once with rajesh sir
                        	fileData.setAmount(Double.toString(agencyAmt));
                        	fileData.setLbeneficiary_addr(lbeneficiary_addr);
                        	fileData.setLclaint_ac_code(lclaint_ac_code);
                        	fileData.setLclaint_ac_number(lclaint_ac_number);
                        }
                    }
                }
                else
                {
                    lFlagVal++;
                }
                if ( lFlagVal == ClaimsConstants.ZERO_VAL)
                {
                    lFileMap = new HashMap<Object,Object>(); // List for generate file for upload(witch is having the all mandatory filed) 
                    lCaseList.add(lStrUniqueId);
                    lFileDataList.add(setfileMap(fileData));
                }
				// For Hub Maintenance
                lStrUniqueId = caseid + "/HM";
                lStrBuffer = new StringBuffer();
                lStrBuffer.append(" SELECT ID as ID,ACCOUNT_NO as \"accNo\",NAME_ASPER_ACT NAMEASPERACT,A.BANK_ID \"bankId\",b.ifc_code \"ifscCode\",b.bank_name \"bankName\",b.bank_branch \"bankBranch\", ");
                lStrBuffer.append(" b.bank_category \"bankCategory\" FROM EHFM_TRUST_ACT_DTLS a,ehfm_bank_master b WHERE a.BANK_ID = b.bank_id AND A.ACT_TYPE = 'EHSHM' ");
                accountList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrBuffer.toString());
                if(accountList.size()>0) 
                {
                    for(int i=0;i<accountList.size();i++) 
                    {
                        if(accountList.get(i).getNAMEASPERACT() != null && accountList.get(i).getNAMEASPERACT()!= ClaimsConstants.EMPTY ) 
                        {
                        	fileData.setHospId(accountList.get(i).getID());
                        	fileData.setAccNo(accountList.get(i).getAccNo());
                        	fileData.setNAMEASPERACT(accountList.get(i).getNAMEASPERACT());
                        	fileData.setBankId(accountList.get(i).getBankId());
                        	fileData.setIfscCode(accountList.get(i).getIfscCode());
                        	fileData.setBankName(accountList.get(i).getBankName());
                        	fileData.setBankBranch(accountList.get(i).getBankBranch());
                        	fileData.setBankCategory(accountList.get(i).getBankCategory());
                        	fileData.setUniqueId(lStrUniqueId);
                        	fileData.setEmail(eMail);//need to confirm once with rajesh sir
                        	fileData.setAmount(Double.toString(hubMnt));
                        	fileData.setLbeneficiary_addr(lbeneficiary_addr);
                        	fileData.setLclaint_ac_code(lclaint_ac_code);
                        	fileData.setLclaint_ac_number(lclaint_ac_number);
                        }
                    }
                }
                else
                {
                    lFlagVal++;
                }
                if ( lFlagVal == ClaimsConstants.ZERO_VAL)
                {
                    lFileMap = new HashMap<Object,Object>(); // List for generate file for upload(witch is having the all mandatory filed) 
                    lCaseList.add(lStrUniqueId);
                    lFileDataList.add(setfileMap(fileData));
                }
				// For Spoke Maintenance
                lStrUniqueId = caseid + "/SM";
                lStrBuffer = new StringBuffer();
                lStrBuffer.append(" SELECT id ID,ACCOUNT_NO \"accNo\",NAME_ASPER_ACT NAMEASPERACT,A.BANK_ID \"bankId\",b.ifc_code \"ifscCode\",b.bank_name \"bankName\",b.bank_branch \"bankBranch\", ");
                lStrBuffer.append(" b.bank_category \"bankCategory\" FROM EHFM_TRUST_ACT_DTLS a,ehfm_bank_master b WHERE a.BANK_ID = b.bank_id AND A.ACT_TYPE = 'EHSSM' ");
                accountList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrBuffer.toString());
                if(accountList.size()>0) 
                {
                    for(int i=0;i<accountList.size();i++) 
                    {
                        if(accountList.get(i).getNAMEASPERACT() != null && accountList.get(i).getNAMEASPERACT()!= ClaimsConstants.EMPTY ) 
                        {
                        	fileData.setHospId(accountList.get(i).getID());
                        	fileData.setAccNo(accountList.get(i).getAccNo());
                        	fileData.setNAMEASPERACT(accountList.get(i).getNAMEASPERACT());
                        	fileData.setBankId(accountList.get(i).getBankId());
                        	fileData.setIfscCode(accountList.get(i).getIfscCode());
                        	fileData.setBankName(accountList.get(i).getBankName());
                        	fileData.setBankBranch(accountList.get(i).getBankBranch());
                        	fileData.setBankCategory(accountList.get(i).getBankCategory());
                        	fileData.setUniqueId(lStrUniqueId);
                        	fileData.setEmail(eMail);//need to confirm once with rajesh sir
                        	fileData.setAmount(Double.toString(spokeMnt));
                        	fileData.setLbeneficiary_addr(lbeneficiary_addr);
                        	fileData.setLclaint_ac_code(lclaint_ac_code);
                        	fileData.setLclaint_ac_number(lclaint_ac_number);
                        }
                    }
                }
                else
                {
                    lFlagVal++;
                }
                if ( lFlagVal == ClaimsConstants.ZERO_VAL)
                {
                    lFileMap = new HashMap<Object,Object>(); // List for generate file for upload(witch is having the all mandatory filed) 
                    lCaseList.add(lStrUniqueId);
                    lFileDataList.add(setfileMap(fileData));
                }
            lresultMap.put("CaseList", lCaseList);
            lresultMap.put("FileDataList", lFileDataList);
            return lresultMap;    	
    }
	// Chandana - 6444 - 12-02-2025 new method for getting the values and sending them to lFileMap 
    public HashMap<Object,Object> setfileMap(FileDataVO fileDataVO){
    	HashMap<Object,Object> lFileMap = new HashMap<Object,Object>(); // List for generate file for upload(witch is having the all mandatory filed) 
         lFileMap.put("UNIQUE_ID", fileDataVO.getUniqueId());
         lFileMap.put("BENEFICIARY_ACCOUNT_NAME", fileDataVO.getNAMEASPERACT());
         lFileMap.put("BENEFICIARY_ID", fileDataVO.getHospId());
         lFileMap.put("BENEFICIARY_ADDR", fileDataVO.getLbeneficiary_addr());
         lFileMap.put("BENEFICIARY_BANK_NAME", fileDataVO.getBankName());
         lFileMap.put("BANK_BRANCH", fileDataVO.getBankBranch());
         lFileMap.put("BENEFICIARY_ACCOUNT_NO", fileDataVO.getAccNo());
         lFileMap.put("TRANSACTION_AMOUNT", fileDataVO.getAmount());
         lFileMap.put("BENEFICIARY_BANK_ID", fileDataVO.getBankId());
         lFileMap.put("BENEFICIARY_BANK_IFC_CODE", fileDataVO.getIfscCode());
         lFileMap.put("CLAINT_AC_CODE", fileDataVO.getLclaint_ac_code());
         lFileMap.put("CLAINT_AC_NUMBER", fileDataVO.getLclaint_ac_number());
         lFileMap.put("EMAIL_ID", fileDataVO.getEmail());
         return lFileMap;
	}

	/**
	 * Update claim account details.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean updateClaimAccountDetails(HashMap lparamMap)
			throws Exception {
		logger.info("Start:updateClaimAccountDetails method in Claimsflowpaymentdaoimpl");
		String lStrCaseId = null;
		String lStrFollowUpId = null;String amount="0";String caseId = "";
		String paymentType = ClaimsConstants.EMPTY;
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		String patientScheme=null;
		if(lparamMap.get("patScheme")!=null)
			patientScheme = (String)lparamMap.get("patScheme") ;
		String caseStatus= (String) lparamMap.get("caseStatus");
		String hosp_id="";	String hosp_type="";
		amount = (String) lparamMap.get("TRANSACTION_AMOUNT");
		EhfClaimAccounts ehfClaimAccounts = null;
		String paymentType1 = (String) lparamMap.get("FormPaymentType");
		lStrCaseId = (String) lparamMap.get("TDS_PAYMENT_ID");
		if (lStrCaseId == null || lStrCaseId.equalsIgnoreCase("")) {
			lStrCaseId = (String) lparamMap.get("CASE_ID");
		}
		caseId=lStrCaseId;
		paymentType = lStrCaseId.substring(
				lStrCaseId.lastIndexOf(ClaimsConstants.SLASH) + 1,
				lStrCaseId.length());
		lStrFollowUpId = lStrCaseId.substring(0,
				lStrCaseId.lastIndexOf(ClaimsConstants.SLASH));
		lStrCaseId = lStrCaseId.substring(0,
				lStrCaseId.indexOf(ClaimsConstants.SLASH));
		try {
			TransactionVO transactionVO = new TransactionVO();
			transactionVO.setPatientScheme(patientScheme);
			transactionVO.setCaseId(lStrFollowUpId);
			transactionVO.setTdsRfId(caseId);
			transactionVO.setNetAmount("");
			transactionVO.setTdsOrRfAmount(amount);
			transactionVO.setScheme((String) lparamMap.get("SCHEME_ID"));
			transactionVO.setLstUpdUsr((String) lparamMap.get("CRTUSER"));
			
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, lStrCaseId);
			if(ehfCase!=null){
				if(ehfCase.getCaseHospCode()!=null){
					EhfmHospitals ehfmHosp = genericDao.findById(EhfmHospitals.class, String.class, ehfCase.getCaseHospCode());
					if(ehfmHosp!=null){
						hosp_id=ehfmHosp.getHospId();
						hosp_type=ehfmHosp.getHospType()+"";
					}
				}
			}
			
			transactionVO.setHospitalId(hosp_id);
			transactionVO.setHospitalType(hosp_type);
			
			
		if (ClaimsConstants.G.equalsIgnoreCase(paymentType)) {
			if (paymentType1!=null && paymentType1.equalsIgnoreCase(ClaimsConstants.ERRCLAIM_FORM_PAYMENT))
			ehfClaimAccounts = genericDao.findById(EhfClaimAccounts.class,
					String.class, lStrFollowUpId);
			else
			ehfClaimAccounts = genericDao.findById(EhfClaimAccounts.class,
						String.class, lStrCaseId);
			
			ehfClaimAccounts.setRevDesAcctNo((String) lparamMap
					.get("DES_ACCT_NO"));
			ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
			//saving file name for RF
			EhfClaimTrustPayment ehfClaimTrustPayment = genericDao.findById(EhfClaimTrustPayment.class, String.class,
					caseId);
			ehfClaimTrustPayment.setFilename((String) lparamMap.get("FileName"));
			ehfClaimTrustPayment.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfClaimTrustPayment.setPaymentStatus((String) lparamMap.get("nextCaseStatus"));
			ehfClaimTrustPayment.setLstUpdUsr((String) lparamMap.get("CRTUSER"));
			ehfClaimTrustPayment.setTransStatus((String) lparamMap.get("SentStatus"));
			ehfClaimTrustPayment.setRemarks("Claim sent For Payment");
			ehfClaimTrustPayment.setBooksUpdatedStatus("N");
			//ehfClaimTrustPayment.setLstUpdUsr(ClaimsConstants.CEO_USER_ID);
			ehfClaimTrustPayment = genericDao.save(ehfClaimTrustPayment);
			
			transactionVO.setNarration("RF");
			transactionVO.setTdsOrRfType(config.getString("ACC.RF"));
			transactionVO.setTransactionType(config.getString("ACC.REGCLAIMS"));
			if (caseId.contains("/E"))
			    transactionVO.setTransactionType(config.getString("ACC.ERRCLAIMS"));
			if (ehfClaimAccounts == null)
			isInsert = ClaimsConstants.BOOLEAN_FALSE;	
			
			/*if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("N")) 
				claimsPaymentDao.submitTdsOrRfPaymentsInAccounts(transactionVO);
			else if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("Y"))
				claimsPaymentDao.submitTdsOrRfPaymentsInAccountsForRej(transactionVO);*/

		} else if (ClaimsConstants.GD.equalsIgnoreCase(paymentType)) {
			ehfClaimAccounts = genericDao.findById(EhfClaimAccounts.class,
					String.class, lStrCaseId);
			ehfClaimAccounts.setRevDesAcctNo((String) lparamMap
					.get("DES_ACCT_NO"));
			ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
			transactionVO.setTransactionType(config.getString("ACC.REGCLAIMS"));
			if (paymentType1.equalsIgnoreCase(ClaimsConstants.ERRCLAIM_FORM_PAYMENT))
			    transactionVO.setTransactionType(config.getString("ACC.ERRCLAIMS"));
			if (ehfClaimAccounts == null)
				isInsert = ClaimsConstants.BOOLEAN_FALSE;
			
			/*if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("N")) 
				claimsPaymentDao.submitTdsOrRfPaymentsInAccounts(transactionVO);
			else if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("Y"))
				claimsPaymentDao.submitTdsOrRfPaymentsInAccountsForRej(transactionVO);*/


		} else if (ClaimsConstants.TDS.equalsIgnoreCase(paymentType)) {
			if (!((String) lparamMap.get("TDS_PAYMENT_ID")).contains("/SD/TDS")){
			transactionVO.setTransactionType(config.getString("ACC.REGCLAIMS"));
			if (paymentType1 != null
					&& (paymentType1
							.equalsIgnoreCase(ClaimsConstants.CLAIM_FORM_PAYMENT))) {
				ehfClaimAccounts = genericDao.findById(EhfClaimAccounts.class,
						String.class, lStrCaseId);
				ehfClaimAccounts.setTdsDesAcctNo((String) lparamMap
						.get("DES_ACCT_NO"));
				ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
				isInsert = ClaimsConstants.BOOLEAN_TRUE;
				
				//saving file name for TDS
				EhfClaimTdsPayment ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,
						caseId);
				ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
				ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
				ehfClaimTdsPayment.setPaymentStatus((String) lparamMap.get("nextCaseStatus"));
				ehfClaimTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
				ehfClaimTdsPayment.setRemarks("Claim sent For Payment");
				ehfClaimTdsPayment.setLastUpdUser((String) lparamMap.get("CRTUSER"));
				ehfClaimTdsPayment.setBooksUpdatedStatus("N");
				//ehfClaimTdsPayment.setLastUpdUser(ClaimsConstants.CEO_USER_ID);
				ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
				
				if (ehfClaimAccounts == null)
					isInsert = ClaimsConstants.BOOLEAN_FALSE; 
				if (paymentType1.equalsIgnoreCase(ClaimsConstants.ERRCLAIM_FORM_PAYMENT))
				    transactionVO.setTransactionType(config.getString("ACC.ERRCLAIMS"));
				
		/*		//added for surplus tds deductions updations
				if(caseStatus!=null && caseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
				{
                  ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,lStrCaseId+"/SD/TDS");
					ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
					ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
					ehfClaimTdsPayment.setPaymentStatus((String) lparamMap.get("nextCaseStatus"));
					ehfClaimTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
					ehfClaimTdsPayment.setRemarks("Claim sent For Payment");
					ehfClaimTdsPayment.setLastUpdUser((String) lparamMap.get("CRTUSER"));
					//ehfClaimTdsPayment.setLastUpdUser(ClaimsConstants.CEO_USER_ID);
					ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
					
				
				}*/
				
				
			} 
			else if (paymentType1 != null
					&& paymentType1
							.equalsIgnoreCase(ClaimsConstants.ERRCLAIM_FORM_PAYMENT)) {
				ehfClaimAccounts = genericDao.findById(EhfClaimAccounts.class,
						String.class, lStrFollowUpId);
				ehfClaimAccounts.setTdsDesAcctNo((String) lparamMap
						.get("DES_ACCT_NO"));
				ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
				isInsert = ClaimsConstants.BOOLEAN_TRUE;
				
				//saving file name for TDS
				EhfClaimTdsPayment ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,
						caseId);
				ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
				ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
				ehfClaimTdsPayment.setPaymentStatus((String) lparamMap.get("nextCaseStatus"));
				ehfClaimTdsPayment.setLastUpdUser((String) lparamMap.get("CRTUSER"));
				ehfClaimTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
				ehfClaimTdsPayment.setRemarks("Claim sent For Payment");
				ehfClaimTdsPayment.setBooksUpdatedStatus("N");
				//ehfClaimTdsPayment.setLastUpdUser(ClaimsConstants.CEO_USER_ID);
				ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
				isInsert = ClaimsConstants.BOOLEAN_TRUE;
				
				
				if (ehfClaimAccounts == null)
					isInsert = ClaimsConstants.BOOLEAN_FALSE; 
				
				transactionVO.setTransactionType(config.getString("ACC.ERRCLAIMS"));
			}
			else if (paymentType1 != null
					&& paymentType1
							.equalsIgnoreCase(ClaimsConstants.FPCLAIM_FORM_PAYMENT)) {
				EhfFollowUpClaimAccounts ehfFpClaimAcc = genericDao.findById(
						EhfFollowUpClaimAccounts.class, String.class,
						lStrFollowUpId);
				ehfFpClaimAcc.setTdsDesAcctNo((String) lparamMap
						.get("DES_ACCT_NO"));
				ehfFpClaimAcc = genericDao.save(ehfFpClaimAcc);
				isInsert = ClaimsConstants.BOOLEAN_TRUE;
				
				//saving file name for TDS
				EhfClaimTdsPayment ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,
						caseId);
				ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
				ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
				ehfClaimTdsPayment.setPaymentStatus((String) lparamMap.get("nextCaseStatus"));
				ehfClaimTdsPayment.setLastUpdUser((String) lparamMap.get("CRTUSER"));
				ehfClaimTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
				ehfClaimTdsPayment.setRemarks("Claim sent For Payment");
				ehfClaimTdsPayment.setBooksUpdatedStatus("N");
				//ehfClaimTdsPayment.setLastUpdUser(ClaimsConstants.CEO_USER_ID);
				ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
				
				if (ehfFpClaimAcc == null)
					isInsert = ClaimsConstants.BOOLEAN_FALSE;
				
				transactionVO.setTransactionType(config.getString("ACC.FOLLCLAIMS"));
			} else
				isInsert = ClaimsConstants.BOOLEAN_FALSE;

			transactionVO.setNarration("TDS");
			transactionVO.setTdsOrRfType(config.getString("ACC.TDS"));
		
		/*if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("N")) 
			claimsPaymentDao.submitTdsOrRfPaymentsInAccounts(transactionVO);
		else if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("Y"))
			claimsPaymentDao.submitTdsOrRfPaymentsInAccountsForRej(transactionVO);
		*//*if(caseStatus!=null && caseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
		{
			transactionVO.setCaseId(lStrCaseId);
			claimsPaymentDao.submitTdsOrRfSurplusPaymentsInAccounts(transactionVO);
		}*/
			}
			//Added for surplus deductions additions
		else if (((String) lparamMap.get("TDS_PAYMENT_ID")).contains("/SD/TDS"))
		{
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
			if(caseStatus!=null && caseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
			{
				EhfClaimTdsPayment ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,
						caseId);
			//	lStrCaseId = (String) lparamMap.get("CASE_ID");
              ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,lStrCaseId+"/SD/TDS");
				if(ehfClaimTdsPayment!=null)
				{
              ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
				ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
				ehfClaimTdsPayment.setPaymentStatus((String) lparamMap.get("nextCaseStatus"));
				ehfClaimTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
				ehfClaimTdsPayment.setRemarks("Claim sent For Payment");
				ehfClaimTdsPayment.setLastUpdUser((String) lparamMap.get("CRTUSER"));
				ehfClaimTdsPayment.setBooksUpdatedStatus("N");
				//ehfClaimTdsPayment.setLastUpdUser(ClaimsConstants.CEO_USER_ID);
				ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
				}
				EhfCaseClaim ehfCaseClaim = genericDao.findById(EhfCaseClaim.class, String.class,
						lStrCaseId);
				
				if(ehfCaseClaim!=null)
				{									
                    EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class,lStrCaseId);
					
					if(ehfSurplusTdsClaim!=null)
					{
						if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
						{
							ehfCaseClaim.setTdsSurAmt(ehfSurplusTdsClaim.getTdsPctAmt());
							
						}
						else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
						{
							ehfCaseClaim.setTdsSurAmt(ehfSurplusTdsClaim.getTrustPctAmt());
						}
						else
						{
							ehfCaseClaim.setTdsSurAmt(0.0);
							 System.out.println("TDS Flag is not set well for case id "+lStrCaseId);
							//logger.error("TDS Flag is not set well for case id "+(String) lparamMap.get("CASE_ID"));
							  ehfCase = genericDao.findById(EhfCase.class, String.class,lStrCaseId);
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
						}
					}
						ehfCaseClaim = genericDao.save(ehfCaseClaim);
					}
				isInsert = ClaimsConstants.BOOLEAN_TRUE;
				transactionVO.setNarration("TDS");				
				transactionVO.setCaseId(lStrCaseId);
				transactionVO.setTdsOrRfType(config.getString("ACC.TDS"));
				transactionVO.setTransactionType(config.getString("ACC.REGCLAIMS"));
				claimsPaymentDao.submitTdsOrRfSurplusPaymentsInAccounts(transactionVO);
				isInsert = ClaimsConstants.BOOLEAN_TRUE;
			
			}
		}
			
		}
		
		}
		catch (Exception e) {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
			logger.error("FAILURE:updateClaimAccountDetails method in ClaimsflowDaoimpl" +e.getMessage());
			e.printStackTrace();
		}
		//logger.info("End:updateClaimAccountDetails method in ClaimsflowDaoimpl");
		return isInsert;
	}
	
	/**
	 * Change claim status.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean ChangeClaimStatus(HashMap lparamMap) throws Exception {
		//logger.info("Start:ChangeClaimStatus method in ClaimsflowDaoimpl");
		Long lActOrder = 0L;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];String amount="0";String caseId = "";
		boolean isInsert = false;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		EhfCase ehfCase = null;String tdsOrRfAmt = "";String totalAmt ="0";
		EhfClaimAccounts ehfClaimAccounts = null;
		EhfErroneousClaim ehfErroneousClaim = null;
		EhfCaseFollowupClaim  ehfCaseFollowupClaim=null;
		
		String hosp_id=(String) lparamMap.get("ACC_HOSP_ID");
		String hosp_type=(String) lparamMap.get("ACC_HOSP_TYPE");
		amount = lparamMap.get("ACC_TRANSACTION_AMOUNT").toString();
		totalAmt =  lparamMap.get("ACC_TOTAL_AMOUNT").toString();
		String TDSorRfType=lparamMap.get("TDS_RF_TYPE").toString();
		String rejectedPayment=lparamMap.get("REJECTED_PAYMENT").toString();
		
		String paymentType = (String) lparamMap.get("PaymentType");
		caseId = (String) lparamMap.get("CASE_ID");
		tdsOrRfAmt =  lparamMap.get("ACC_REST_AMOUNT").toString();
		String patientScheme=null;
		if(lparamMap.get("patScheme")!=null)
			patientScheme = (String)lparamMap.get("patScheme") ;
		
		try {
			TransactionVO transactionVO = new TransactionVO();
			transactionVO.setCaseId(caseId);
			transactionVO.setTdsRfId(caseId);
			transactionVO.setHospitalId(hosp_id);
			transactionVO.setGrossAmount(totalAmt);
			transactionVO.setNetAmount(amount);
			transactionVO.setTdsOrRfAmount(tdsOrRfAmt);		
			transactionVO.setHospitalType(hosp_type); 
			transactionVO.setScheme((String) lparamMap.get("SCHEME_ID"));
			transactionVO.setLstUpdUsr(claimFlowVO.getUserId());
			transactionVO.setTdsOrRfType(TDSorRfType);
			transactionVO.setPatientScheme(patientScheme);
		if (paymentType != null
				&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
				&& paymentType.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
			
			ehfErroneousClaim = genericDao.findById(EhfErroneousClaim.class,
					String.class, (String) lparamMap.get("CASE_ID"));
			ehfErroneousClaim.setErrClaimStatus((String) lparamMap
					.get("nextCaseStatus"));
			ehfErroneousClaim.setFilename((String) lparamMap.get("FileName"));
			ehfErroneousClaim.setPaymentCheck(ClaimsConstants.F);
			ehfErroneousClaim.setPaymentSentDate(new Timestamp(new Date()
					.getTime()));
			ehfErroneousClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfErroneousClaim.setLstUpdUsr(claimFlowVO.getUserId());
			ehfErroneousClaim = genericDao.save(ehfErroneousClaim);

			String lStrCaseId1 = (String) lparamMap.get("CASE_ID");
			String lStrCaseId = lStrCaseId1.substring(0,
					lStrCaseId1.indexOf(ClaimsConstants.SLASH));
			ehfCase = genericDao.findById(EhfCase.class, String.class,
					lStrCaseId);

			ehfCase.setErrClaimStatus((String) lparamMap.get("nextCaseStatus"));
			ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
			ehfCase = genericDao.save(ehfCase);
			
			transactionVO.setNarration("Err-Claim");
			transactionVO.setTransactionType(config.getString("ACC.ERRCLAIMS"));
			
		}
		

		
		
		else {
			ehfCase = genericDao.findById(EhfCase.class, String.class,
					(String) lparamMap.get("CASE_ID"));
			
			ehfCase.setCaseStatus((String) lparamMap.get("nextCaseStatus"));
			ehfCase.setFilename((String) lparamMap.get("FileName"));
			ehfCase.setPaymentCheck(ClaimsConstants.F);
			ehfCase.setPaymentSentDate(new java.sql.Timestamp(new Date().getTime()));
			ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
			ehfCase = genericDao.save(ehfCase);
			
			transactionVO.setNarration("Claim");
			transactionVO.setTransactionType(config.getString("ACC.REGCLAIMS"));
		}
		lparamMap.get("sentAgain");
		if((ehfCase!=null && ((ehfCase.getCaseStatus()!=null && ehfCase.getCaseStatus().equalsIgnoreCase(config.getString("EHF.Claims.sentForPayment"))) 
				|| (ehfCase.getErrClaimStatus()!=null && ehfCase.getErrClaimStatus().equalsIgnoreCase(config.getString("EHF.ERRONEOUS.CLAIM.SENTFORPAYMENT")))))
				&& ("N").equalsIgnoreCase((String)lparamMap.get("sentAgain")))
		{
			//logger.info("updating payment slab........ ");
			boolean isUpdate=false;
			if(ehfCase!=null && ehfCase.getLstUpdUsr()!=null)
			{
				lparamMap.put("lst_upd_usr",ehfCase.getLstUpdUsr());
			}
			isUpdate=getPaymentSlab(lparamMap);
			
		}
		
		
		
		
		
		
		// if (paymentType)
		if ((ehfCase != null && ehfCase.getPaymentSentDate() != null)
				|| (ehfErroneousClaim != null && ehfErroneousClaim.getPaymentSentDate() != null)) {

			ehfClaimAccounts = genericDao.findById(EhfClaimAccounts.class,
					String.class, (String) lparamMap.get("CASE_ID"));

			if(ehfClaimAccounts!=null)
			{
			ehfClaimAccounts.setTransStatus(ClaimsConstants.SENT);
			ehfClaimAccounts.setTimeMilSec((long) 0);
			ehfClaimAccounts
					.setSrcAcctNo((String) lparamMap.get("SRC_ACCT_NO"));
			ehfClaimAccounts
					.setDesAcctNo((String) lparamMap.get("DES_ACCT_NO"));
			ehfClaimAccounts.setLstUpdDt(new java.sql.Timestamp(new Date()
					.getTime()));
			ehfClaimAccounts.setLstUpdUsr(claimFlowVO.getUserId());
			if(ehfClaimAccounts.getPatientScheme()==null)
				ehfClaimAccounts.setPatientScheme(patientScheme);
			ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
			}
		}
		
		String lStrCaseId = (String) lparamMap.get("CASE_ID");
		if (paymentType != null
				&& !paymentType.equalsIgnoreCase(ClaimsConstants.EMPTY)
				&& paymentType
						.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
			lStrCaseId = lStrCaseId.substring(0,
					lStrCaseId.indexOf(ClaimsConstants.SLASH));
		}
		
		
		if (ehfClaimAccounts.getSrcAcctNo() != null) {
			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
			args[0] = lStrCaseId;
			List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
					ClaimsFlowVO.class, lQueryBuffer.toString(), args);
			if (actOrderList != null && !actOrderList.isEmpty()
					&& actOrderList.get(0).getCOUNT() != null) {
				if (actOrderList.get(0).getCOUNT().longValue() >= 0)
					lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
			}

			//inserting into accounts table
			/*if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("N")) 
			claimsPaymentDao.submitClaimPaymentsInAccounts(transactionVO);
			else if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("Y"))
			claimsPaymentDao.submitClaimPaymentsInAccountsForRej(transactionVO);
			*/
			// insert into asrit_audit
			EhfAudit asritAudit = new EhfAudit();
			
			EhfAuditId asritAuditPK = new EhfAuditId(lActOrder, lStrCaseId);
			asritAudit.setId(asritAuditPK);
			asritAudit.setActId(claimFlowVO.getNextStatus());
			asritAudit.setActBy(claimFlowVO.getUserId());
			asritAudit.setCrtUsr(claimFlowVO.getUserId());
			asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			asritAudit.setLangId(ClaimsConstants.LANG_ID);
			asritAudit.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
			if(rejectedPayment!=null && rejectedPayment.length()>0 && rejectedPayment.equalsIgnoreCase("true"))
			{
				asritAudit.setBooksUpdatedStatus("R");
			}
			else
			{
				asritAudit.setBooksUpdatedStatus("N");
			}
			
			if((String) lparamMap.get("FileName")!=null)
			asritAudit.setFilename((String) lparamMap.get("FileName"));
			// asritAudit.setApprvAmt((double) 0);
			asritAudit.setUserRole(claimFlowVO.getRoleId());

			genericDao.save(asritAudit);
			isInsert = true;
		}
		}
		catch (Exception e) {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
			logger.info("FALIURE:ChangeClaimStatus method in ClaimsflowDaoimpl"+e.getMessage());
			e.printStackTrace();
		}
		//logger.info("End:ChangeClaimStatus method in Claimsflowpaymentdaoimpl");
		return isInsert;

	}

	
	
	@SuppressWarnings("unchecked")
	public boolean getPaymentSlab(HashMap lparamMap) throws Exception {

		StringBuffer lStrBuffer = new StringBuffer();
		Map lResMap = new HashMap();
		boolean isInsert = false;
		String paymentType = (String) lparamMap.get("PaymentType");
		List<ClaimsFlowVO> percentatgeList = new ArrayList<ClaimsFlowVO>();
		String[] arg = new String[2];
		arg[0] = (String) lparamMap.get("CASE_ID");
		lStrBuffer
		.append(" select distinct ae.tds_active_yn as TDSACTIVEYN,ae.active_yn as ACTIVEYN,nvl(ae.hosp_percent, 100) as HOSPPERCENT,nvl(ae.trust_percent, 0) as TRUSTPERCENT,");
lStrBuffer
		.append(" nvl(ae.TDS_HOSP_PERCENT, 100) as TDSHOSPPERCENT, nvl(ae.TDS_PERCENT, 0) as TDSPERCENT,nvl(ae.TDS_SURCHARGE_PCT, 0) as  SURCHARGEPERCENT,nvl(ae.TDS_CESS_PCT, 0) as CESSPERCENT, ");
lStrBuffer
		.append(" ad.hosp_type as HOSPTYPE,ae.hosp_id as HOSPITALID,aag.direct_deduction as DIRECTDEDUCTION,aag.slab_amount as SLABAMOUNT,aag.total_tds_amt as TOTALTDSAMT,aag.total_claim_amt as TOTALCLAIMAMT,  ");
lStrBuffer
		.append(" aag.hosp_deductor_id as HOSPDEDUCTORID,aag.rqst_active_yn as RQSTACTIVEYN,aag.SlabAmt_applicable as SLABAMTAPPLICABLE, ab.tot_claim_amt as CLAIMAMOUNT, aah.rev_fund_active as REVFUNDACTIVE ");
lStrBuffer
		.append(" from ehf_case aa,ehfm_hospitals ad,EHFM_HOSP_SURG_PER ae,Ehfm_Hosp_Bank_Dtls aah ");

if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
	lStrBuffer
			.append(" ,EHF_CASE_FOLLOWUP_CLAIM ab , Ehf_Clinical_Flpup_Mpg aag1 , ehf_case_therapy ma, ehfm_followup_packages mb  ");
} else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
	lStrBuffer
			.append(" ,ehf_erroneous_claim ab ,ehf_case_claim ab1 ,ehf_case_therapy ac ");
} else {
	lStrBuffer.append(" ,ehf_case_claim ab ,ehf_case_therapy ac ");
}

lStrBuffer
		.append(" ,EHFM_PAYMENT_HOSP_DEDUCTOR aaf,EHF_PAYMENT_SLAB_DTLS aag where  ad.hosp_id=aa.case_hosp_code ");
lStrBuffer
		.append(" and ae.hosp_id=aa.case_hosp_code  and aah.hosp_id=ad.hosp_id ");
/*
 * for followup there will be only one record for each hospital in
 * EHFM_HOSP_SURG_PER
 */
if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
	lStrBuffer
			.append(" and ab.case_followup_id = ? and aag1.followup_id=ab.case_followup_id and aag1.case_id=aa.case_id and ae.claim_type=? ");
	lStrBuffer
	.append(" and ab.case_id=ma.case_id and aa.case_id=ma.case_id and ma.icd_proc_code=mb.surgery_id and aa.scheme_id=mb.scheme_id and mb.icd_code_proc_fp=ae.surgery_id      ");
	arg[1] = "FLUP";
} else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
	lStrBuffer
			.append(" and ab.err_claim_id = ? and ab.case_id = aa.case_id and aa.case_id=ab1.case_id  and ac.case_id=aa.case_id and ac.icd_proc_code=ae.surgery_id(+)  and ae.claim_type=? ");
	arg[1] = "GTDS";
} else {
	lStrBuffer
			.append("  and aa.case_id=ab.case_id  and ac.case_id=aa.case_id and ac.icd_proc_code=ae.surgery_id(+) and aa.case_id = ?  and ae.claim_type=? ");
	arg[1] = "GTDS";
}

lStrBuffer
		.append(" and aaf.hosp_id=ae.hosp_id and aaf.hosp_deductor_id = aag.hosp_deductor_id(+)  and ab.Tot_claim_amt is not null ");

lStrBuffer.append(" and aag.scheme=aa.scheme_id and ae.scheme = aa.scheme_id ");

// Here we get all the percentages (TDS or Revolving fund) to be
// deducted and whether they are active or not (from TDS master)

		percentatgeList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,
				lStrBuffer.toString(), arg);

		if (percentatgeList.size() > 0) {
			for (int i = 0; i < percentatgeList.size(); i++) {

				lResMap.put("tds_active_yn", percentatgeList.get(i)
						.getTDSACTIVEYN());
				lResMap.put("RF_active_yn", percentatgeList.get(i)
						.getACTIVEYN());
				lResMap.put("hosp_percent", percentatgeList.get(i)
						.getHOSPPERCENT());
				lResMap.put("trust_percent", percentatgeList.get(i)
						.getTRUSTPERCENT());
				lResMap.put("tds_hosp_percent", percentatgeList.get(i)
						.getTDSHOSPPERCENT());
				lResMap.put("tds_percent", percentatgeList.get(i)
						.getTDSPERCENT());
				lResMap.put("hosp_type", percentatgeList.get(i).getHOSPTYPE());
				lResMap.put("claim_amount", percentatgeList.get(i)
						.getCLAIMAMOUNT());
				lResMap.put("direct_deduction", percentatgeList.get(i)
						.getDIRECTDEDUCTION());
				lResMap.put("slab_amount", percentatgeList.get(i)
						.getSLABAMOUNT());
				lResMap.put("total_amt", percentatgeList.get(i)
						.getTOTALCLAIMAMT());
				lResMap.put("tds_amt", percentatgeList.get(i).getTOTALTDSAMT());
				lResMap.put("Hosp_Deduct_Id", percentatgeList.get(i)
						.getHOSPDEDUCTORID());
				lResMap.put("rqst_active_yn", percentatgeList.get(i)
						.getRQSTACTIVEYN());
				lResMap.put("SlabAmt_applicable", percentatgeList.get(i)
						.getSLABAMTAPPLICABLE());
				lResMap.put("hosp_id", percentatgeList.get(i).getHOSPITALID());
			}
		

		lparamMap.put("ResMap", lResMap);
	 
		
        // To get the TDS/RF/Hospital Amount
		if(("N").equalsIgnoreCase(percentatgeList.get(0)
						.getDIRECTDEDUCTION())  && ("Y").equalsIgnoreCase(percentatgeList.get(0)
								.getRQSTACTIVEYN()))
		{
			Map lPercentAmtMap = updatePaymentSlab(lparamMap);
		
		if (ClaimsConstants.TRUE.equalsIgnoreCase((String) lPercentAmtMap
				.get("UpdSuccess"))) {
			
			isInsert=true;
		}
		}
		else
		{
			isInsert=true;
		}
		}
		return isInsert;
	}

	
	
	
	
	@Transactional
	public Map updatePaymentSlab(HashMap lParamMap) throws Exception {
		//DecimalFormat myFormatter = new DecimalFormat("0");
		StringBuffer lStrBuffer = new StringBuffer();
		String temp = ClaimsConstants.EMPTY, lStrClaimAmt = ClaimsConstants.EMPTY, lStrHospType = ClaimsConstants.EMPTY, lStrTDSActive = ClaimsConstants.EMPTY, lStrRFActive = ClaimsConstants.EMPTY;//, lStrRevDefActive = ClaimsConstants.EMPTY
		String lStrDirectDeduction = ClaimsConstants.EMPTY, lStrTotalAmt = null, lStrTotalTDSAmt = null;
		String lStrHospDeductId = null, lStrSlabAmt = null, lStrDoneFlag = null, lStrHospId = ClaimsConstants.EMPTY;
		
		double TotalAmt = 0, TotalTDSAmt = 0, SlabAmt = 0;
		double lHospPercentage = ClaimsConstants.ZERO_DBL_VAL, lTrustPercentage = ClaimsConstants.ZERO_DBL_VAL, lHospAmt = ClaimsConstants.ZERO_DBL_VAL, lTrustAmt = ClaimsConstants.ZERO_DBL_VAL, claimAmt = ClaimsConstants.ZERO_DBL_VAL, lTDSPercentage = ClaimsConstants.ZERO_DBL_VAL, lTDSHospPercentage = ClaimsConstants.ZERO_DBL_VAL, lTDSHospAmt = ClaimsConstants.ZERO_DBL_VAL, lTDSAmt = ClaimsConstants.ZERO_DBL_VAL;
		double lSurchargeAmt = ClaimsConstants.ZERO_DBL_VAL, lCessAmt = ClaimsConstants.ZERO_DBL_VAL,  lCessPct = ClaimsConstants.ZERO_DBL_VAL, lTaxAmt = ClaimsConstants.ZERO_DBL_VAL;//lSurchargePct = ClaimsConstants.ZERO_DBL_VAL,
		double lTempClaimAmt = ClaimsConstants.ZERO_DBL_VAL;
		Map lTDSDeductMap = new HashMap();
		Map lResMap = (HashMap) lParamMap.get("ResMap");
		String schemeId=(String) lParamMap.get("SCHEME_ID");
		Map lRevDefMap = null;
		String ldataTable = " EhfCaseClaim ";
		String ldataCol = " caseId=? ";
		String lselectColumn = " totClaimAmt as CLAIMAMOUNT ";
		String paymentType = (String) lParamMap.get("PaymentType");// lClaimPmtVo.getPaymentType
																	// () ;
		String lStrInsert = ClaimsConstants.FALSE;
		String[] arg = null;

		List<ClaimsFlowVO> claimAmountList = new ArrayList<ClaimsFlowVO>();

		if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
			ldataTable = " EhfCaseFollowupClaim ";
			ldataCol = " caseFollowupId=? ";
			lselectColumn = " totClaimAmt as CLAIMAMOUNT ";
		}	
		else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType))// If no record
																// is present in
																// asrit_govt_hosp_surg//018
		{
			ldataTable = " EhfErroneousClaim ";
			ldataCol = " errClaimId=? ";
			lselectColumn = " totClaimAmt as CLAIMAMOUNT ";			
			
		}
		String lStrSlabAplFlag = ClaimsConstants.EMPTY;// SlabAmt Applicable Flag

		if (!lResMap.isEmpty()) {
			lStrTDSActive = (String) lResMap.get("tds_active_yn");
			lStrRFActive = (String) lResMap.get("RF_active_yn");
			lHospPercentage = Double.parseDouble(lResMap.get("hosp_percent")
					.toString());
			lTrustPercentage = Double.parseDouble(lResMap.get("trust_percent")
					.toString());
			lTDSHospPercentage = Double.parseDouble(lResMap.get(
					"tds_hosp_percent").toString());
			lTDSPercentage = Double.parseDouble(lResMap.get("tds_percent")
					.toString());
			
			lStrHospType = lResMap.get("hosp_type").toString();
			lStrClaimAmt = lResMap.get("claim_amount").toString();
			claimAmt = Double.parseDouble(lStrClaimAmt);
			
			//logger.info("claim amount in resmap!empty.........."+claimAmt);
			
			lStrDirectDeduction = (String) lResMap.get("direct_deduction");
			if(lResMap.get("slab_amount")!=null)
			    lStrSlabAmt = lResMap.get("slab_amount").toString();
			if (lStrSlabAmt != null)
				SlabAmt = Double.parseDouble(lStrSlabAmt);
			if(lResMap.get("total_amt")!=null)
			lStrTotalAmt = lResMap.get("total_amt").toString();
			if (lStrTotalAmt != null)
				TotalAmt = Double.parseDouble(lStrTotalAmt);
			if(lResMap.get("tds_amt")!=null)
			    lStrTotalTDSAmt = lResMap.get("tds_amt").toString();
			if (lStrTotalTDSAmt != null)
				TotalTDSAmt = Double.parseDouble(lStrTotalTDSAmt);
			lStrDoneFlag = (String) lResMap.get("rqst_active_yn");
			lStrHospDeductId = (String) lResMap.get("Hosp_Deduct_Id");
			lStrSlabAplFlag = (String) lResMap.get("SlabAmt_applicable");
			lStrHospId = (String) lResMap.get("hosp_id");
		} else {
			lStrBuffer = new StringBuffer();
			lStrBuffer.append("select ").append(lselectColumn).append(" from ")
					.append(ldataTable);
			lStrBuffer.append(" where ").append(ldataCol);


				arg = new String[1];
				arg[0] = (String) lParamMap.get("CASE_ID");


			claimAmountList = genericDao.executeHQLQueryList(
					ClaimsFlowVO.class, lStrBuffer.toString(), arg);
			if (claimAmountList.size() > 0) {
				for (int i = 0; i < claimAmountList.size(); i++) {
					if (Double.parseDouble(claimAmountList.get(i)
							.getCLAIMAMOUNT().toString()) > 0)
						claimAmt = Double.parseDouble(claimAmountList.get(i)
								.getCLAIMAMOUNT().toString());
				}
				//logger.info("claim amount in resmap is empty.........."+claimAmt);
			}
		}
		
		if("Y".equalsIgnoreCase(lStrTDSActive)){
			
			if (ClaimsConstants.Y.equalsIgnoreCase(lStrDoneFlag)) {
				if (!"Y".equalsIgnoreCase(lStrSlabAplFlag)) 
				{				
					lStrInsert = ClaimsConstants.TRUE;
				} else if ("Y".equalsIgnoreCase(lStrDirectDeduction)) 																	
				{
					lTaxAmt = (claimAmt * lTDSPercentage) / 100;
					lTDSAmt = lTaxAmt;

					if (((lTDSAmt * 10) % 10) > 0)
						lTDSAmt = new Double((int) lTDSAmt + 1);

					lHospAmt = claimAmt - lTDSAmt;
					TotalAmt = TotalAmt + lHospAmt;
					TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					lParamMap.put("HospCmpAmt", lHospAmt);
					lStrInsert = ClaimsConstants.TRUE;
					
					//logger.info("total claim amount 1:........."+TotalAmt);
				}
				// First time when the slabAmt < totalAmt paid,update
				// Direct_deduction flag
				else if ("N".equalsIgnoreCase(lStrDirectDeduction)
						&& (TotalAmt + claimAmt > SlabAmt)) {
					
					double remainClaimAmt = SlabAmt - TotalAmt;
					double lRemainTdsAmt = (remainClaimAmt * lTDSPercentage) / 100;
					Date date=new Date();
					if (((lRemainTdsAmt * 10) % 10) > 0)
						lRemainTdsAmt = new Double((int) lRemainTdsAmt + 1);
					
					EhfPaymentSlabDtls ehfPaymentSlabDtls = null;
					EhfPaymentSlabDtlsID EhfPaymentSlabDtlsId=new EhfPaymentSlabDtlsID(lStrHospDeductId,schemeId);
					ehfPaymentSlabDtls = genericDao.findById(EhfPaymentSlabDtls.class,
							EhfPaymentSlabDtlsID.class, EhfPaymentSlabDtlsId);

					ehfPaymentSlabDtls.setTotalClaimAmt(new Double(TotalAmt+remainClaimAmt));
					ehfPaymentSlabDtls.setTotalTdsAmt(new Double(TotalTDSAmt+lRemainTdsAmt));
					String lstUpdUsr=(String) lParamMap.get("lst_upd_usr");
					if(lstUpdUsr!=null)
					{
						ehfPaymentSlabDtls.setLstUpdUsr(lstUpdUsr);
					}
					ehfPaymentSlabDtls.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
					ehfPaymentSlabDtls = genericDao.save(ehfPaymentSlabDtls);
					if (ehfPaymentSlabDtls != null) {
						lStrInsert = ClaimsConstants.TRUE;
					}
					
					boolean isInsert = updateGovtHospSurg(lStrHospId,
							lStrHospDeductId, lParamMap);
					if (isInsert) {
						//lTDSPercentage = Double.parseDouble(ClaimsConstants.TDS_PERCENT);
						lTDSPercentage = Double.parseDouble(lParamMap.get("TDS_RATE_PERCENT").toString());
						
						lTempClaimAmt = (TotalAmt + claimAmt) - SlabAmt;
						lTaxAmt = (lTempClaimAmt * lTDSPercentage) / 100;
						lTDSAmt = lTaxAmt;

						if (((lTDSAmt * 10) % 10) > 0)
							lTDSAmt = new Double((int) lTDSAmt + 1);
						
						lTDSAmt = lTDSAmt + lRemainTdsAmt;
						lHospAmt = claimAmt - lTDSAmt;
						TotalAmt = TotalAmt + lHospAmt;
						TotalTDSAmt = TotalTDSAmt + lTDSAmt;
						lParamMap.put("HospCmpAmt", lHospAmt);
						lStrInsert = ClaimsConstants.TRUE;
						lStrTDSActive = ClaimsConstants.Y;
						//logger.info("total claim amount 2:........."+TotalAmt);
					}
				} else // First time when a case is paid
				{	
					lTaxAmt = (claimAmt * lTDSPercentage) / 100;
					lTDSAmt = lTaxAmt;

					if (((lTDSAmt * 10) % 10) > 0)
						lTDSAmt = new Double((int) lTDSAmt + 1);

					lHospAmt = claimAmt - lTDSAmt;
					TotalAmt = TotalAmt + lHospAmt;
					TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					lParamMap.put("HospCmpAmt", lHospAmt);
					lStrInsert = ClaimsConstants.TRUE;
					//logger.info("total claim amount 3:........."+TotalAmt);
				}

				//if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
				//	lStrInsert = ClaimsConstants.TRUE;
				//}
			} else {
				
				lTaxAmt = (claimAmt * lTDSPercentage) / 100;
				lTDSAmt = lTaxAmt;

				if (((lTDSAmt * 10) % 10) > 0)
					lTDSAmt = new Double((int) lTDSAmt + 1);

				lHospAmt = claimAmt - lTDSAmt;
				TotalAmt = TotalAmt + lHospAmt;
				TotalTDSAmt = TotalTDSAmt + lTDSAmt;
				lParamMap.put("HospCmpAmt", lHospAmt);
				lStrInsert = ClaimsConstants.TRUE;
				//logger.info("total claim amount 4:........."+TotalAmt);
			}		
			
			
		}
			
		Double tdsRatePercent = Double.parseDouble(lParamMap.get("TDS_RATE_PERCENT").toString());
		if (ClaimsConstants.Y.equalsIgnoreCase(lStrDoneFlag)
				&& lTDSPercentage < tdsRatePercent 
				&& "Y".equalsIgnoreCase(lStrSlabAplFlag)) {
			lStrBuffer = new StringBuffer();
			Date date=new Date();
			EhfPaymentSlabDtls ehfPaymentSlabDtls = null;
			EhfPaymentSlabDtlsID EhfPaymentSlabDtlsId=new EhfPaymentSlabDtlsID(lStrHospDeductId,schemeId);
			ehfPaymentSlabDtls = genericDao.findById(EhfPaymentSlabDtls.class,
					EhfPaymentSlabDtlsID.class, EhfPaymentSlabDtlsId);

			ehfPaymentSlabDtls.setTotalClaimAmt(new Double(TotalAmt));
			ehfPaymentSlabDtls.setTotalTdsAmt(new Double(TotalTDSAmt));
			String lstUpdUsr=(String) lParamMap.get("lst_upd_usr");
			if(lstUpdUsr!=null)
			{
				ehfPaymentSlabDtls.setLstUpdUsr(lstUpdUsr);
			}
			ehfPaymentSlabDtls.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfPaymentSlabDtls = genericDao.save(ehfPaymentSlabDtls);
			if (ehfPaymentSlabDtls != null) {
				lStrInsert = ClaimsConstants.TRUE;
			}
			//logger.info("total claim amount 5:........."+TotalAmt);
		}
			

		lTDSDeductMap.put("HospAmt", new Double(lHospAmt).toString() );
		lTDSDeductMap.put("TrustAmt", new Double(lTrustAmt).toString());
		lTDSDeductMap.put("TDSHospAmt",new Double(lTDSHospAmt).toString());
		lTDSDeductMap.put("TDSAmt", new Double(lTDSAmt).toString());
		lTDSDeductMap.put("lTaxAmt", new Double(lTaxAmt).toString());
		lTDSDeductMap.put("TDSActiveFlag", lStrTDSActive);
		lTDSDeductMap.put("RFActiveFlag", lStrRFActive);
		lTDSDeductMap.put("UpdSuccess", lStrInsert);

		return lTDSDeductMap;
	}
	
	
	@Transactional
	public boolean updateGovtHospSurg(String lStrHospId,
			String lStrHospDeductId, HashMap lparamMap) throws Exception {
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		String schemeId=(String) lparamMap.get("SCHEME_ID");
		EhfPaymentSlabDtls ehfPaymentSlabDtls = null;
		//logger.info("** deductor id in updateGovtHospSurg **** :"  +lStrHospDeductId);
		//logger.info("** deductor id in updateGovtHospSurg **** :"  +schemeId);
		EhfPaymentSlabDtlsID EhfPaymentSlabDtlsId=new EhfPaymentSlabDtlsID(lStrHospDeductId,schemeId);
		ehfPaymentSlabDtls = genericDao.findById(EhfPaymentSlabDtls.class,
				EhfPaymentSlabDtlsID.class, EhfPaymentSlabDtlsId);

		ehfPaymentSlabDtls.setDirectDeduction(ClaimsConstants.Y);
		ehfPaymentSlabDtls = genericDao.save(ehfPaymentSlabDtls);

		if (ehfPaymentSlabDtls != null) {
			List<String> colValues = new ArrayList<String>();
			Date date=new Date();
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.scheme", schemeId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
			criteriaList.add(new GenericDAOQueryCriteria("id.hospId", lStrHospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.deductorType","CD525",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmHospSurgPer> ehfmHospSurgPer = genericDao
					.findAllByCriteria(EhfmHospSurgPer.class, criteriaList);
			
			int size=ehfmHospSurgPer.size();
			//logger.info("**********size of EhfmHospSurgPer in updateGovtHospSurg is : "+size );
			
			for(EhfmHospSurgPer ehfmHospSurg : ehfmHospSurgPer){
				/*ehfmHospSurg.setTdsHospPercent(Double.parseDouble(ClaimsConstants.TDS_HOSP_PERCENT.toString()));
				ehfmHospSurg.setTdsPercent(Double.parseDouble(ClaimsConstants.TDS_PERCENT.toString()));*/
				ehfmHospSurg.setTdsHospPercent(Double.parseDouble(lparamMap.get("TDS_HOSP_RATE_PERCENT").toString()));
				ehfmHospSurg.setTdsPercent(Double.parseDouble(lparamMap.get("TDS_RATE_PERCENT").toString()));
				ehfmHospSurg.setTdsActiveYn(ClaimsConstants.Y);
				ehfmHospSurg.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfmHospSurg = genericDao.save(ehfmHospSurg);
			}
			
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
			// }

		}
		return isInsert;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Update payment check value.
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "null" })
	@Transactional
	public boolean updatePaymentCheckValue(HashMap lparamMap) throws Exception {

		String lStrCaseId = ClaimsConstants.EMPTY;
		String lStrStatus = ClaimsConstants.EMPTY;
		
		String failedCaseIdInList = ClaimsConstants.EMPTY;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		String paymentType = (String) lparamMap.get("PaymentType");
		EhfCase ehfCase = null;
		EhfErroneousClaim ehfErroneousClaim = null;
		boolean failedIsInsert = ClaimsConstants.BOOLEAN_FALSE;
		int count=0;
		int size=claimFlowVO.getCaseSelected().length;
		String[] casesReady = new String[size];

		for (String cases : claimFlowVO.getCaseSelected()) {
			try {
				lStrCaseId = cases;
				lparamMap.put("CASE_ID", lStrCaseId);
				isInsert = ClaimsConstants.BOOLEAN_FALSE;

				if (lparamMap.get("caseStatus") != null)
					lStrStatus = (String) lparamMap.get("caseStatus"); // 030

				if ((!(ClaimsConstants.CLAIM_READY_REJ_BANK
						.equalsIgnoreCase(lStrStatus))) && (!(ClaimsConstants.CLAIM_ERR_READY_REJ_BANK
								.equalsIgnoreCase(lStrStatus)))) // 030
				{ //calculating percentage
					isInsert = claimsPaymentDao
							.calculateClaimPercentage(lparamMap);
				}
				if (isInsert
						|| ClaimsConstants.CLAIM_READY_REJ_BANK
								.equalsIgnoreCase(lStrStatus) || ClaimsConstants.CLAIM_ERR_READY_REJ_BANK
								.equalsIgnoreCase(lStrStatus)) {
					if (paymentType != null
							&& !paymentType
									.equalsIgnoreCase(ClaimsConstants.EMPTY)
							&& paymentType
									.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)) {
						ehfErroneousClaim = genericDao.findById(
								EhfErroneousClaim.class, String.class,
								lStrCaseId);
						ehfErroneousClaim.setPaymentCheck(ClaimsConstants.T);
						ehfErroneousClaim = genericDao.save(ehfErroneousClaim);
						if (ehfErroneousClaim.getPaymentCheck() != null
								&& (ClaimsConstants.T)
										.equalsIgnoreCase(ehfErroneousClaim
												.getPaymentCheck())) {
							isInsert = ClaimsConstants.BOOLEAN_TRUE;
							casesReady[count]=lStrCaseId;
							count++;
						}
					} else {
						ehfCase = genericDao.findById(EhfCase.class,
								String.class, lStrCaseId);
						ehfCase.setPaymentCheck(ClaimsConstants.T);
						ehfCase = genericDao.save(ehfCase);
						if (ehfCase.getPaymentCheck() != null
								&& (ClaimsConstants.T).equalsIgnoreCase(ehfCase
										.getPaymentCheck())) {
							isInsert = ClaimsConstants.BOOLEAN_TRUE;
							casesReady[count]=lStrCaseId;
							count++;
						}
					}
				} else {
					boolean isUpdate=false;
					failedCaseIdInList = failedCaseIdInList + lStrCaseId + ",";
					logger.error("failed cases are in catch block************"
							+ failedCaseIdInList);
					isUpdate=updateFailedCaseStatus(lStrCaseId,paymentType);
					if(!isUpdate)
					{
						logger.error("failed to update case status of failed claim with case id : "+lStrCaseId);
					}
					lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				}
			} catch (Exception ex) {
				failedIsInsert = ClaimsConstants.BOOLEAN_TRUE;
				failedCaseIdInList = failedCaseIdInList + lStrCaseId + ",";
				logger.error("failed cases are in catch block************"
						+ failedCaseIdInList);
				lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				lparamMap.put("failedIsInsert", failedIsInsert);
				ex.printStackTrace();
			}
		}
		
		if(count>0)
		{
			isInsert=ClaimsConstants.BOOLEAN_TRUE;	
			claimFlowVO.setCasesReady(casesReady);
		}
		else
		{
			isInsert=ClaimsConstants.BOOLEAN_FALSE;	
		}
		return isInsert;
	}

	/**
	 * Update TDS-Claim Details
	 *
	 * @param lparamMap the lparam map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public HashMap updateTDSClaimStatus(HashMap lparamMap) {
		String lResult = ClaimsConstants.EMPTY, lStrCaseId = null;
		List lCaseIdLst = new ArrayList();
		
		List lContentList = new ArrayList();
		Map lFileMap = new HashMap();
		int lFlag = 0, iUploadStatus = 0;
		List resultList = new ArrayList();
		List lFileList = new ArrayList();
		Map lContentMap = null;
		

		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		try {
			GenerateAsciiFile gaf = new GenerateAsciiFile();
            //updating payment check flag in tds table 
			isInsert = updateTdsPayChkValue(lparamMap);

			if (isInsert) { // getting tds accounts details
				lContentList = getTdsAccountDtls(lparamMap);

				if (lContentList.size() > 0) { //generating ascii file values
					lFileMap = gaf.generateAsciiFile((ArrayList) lContentList,
							lparamMap);
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
						lparamMap.put("TDS_PAYMENT_ID", lStrCaseId); // for
																		// setTDSCaseAudit

						lContentMap = new HashMap();
						lContentMap = (HashMap) lFileList.get(j);
						if ((String) lContentMap.get("BENEFICIARY_ACCOUNT_NO") != null) {
							lparamMap.put("SRC_ACCT_NO", (String) lContentMap
									.get("CLAINT_AC_NUMBER"));
							lparamMap.put("DES_ACCT_NO", (String) lContentMap
									.get("BENEFICIARY_ACCOUNT_NO"));
							lparamMap.put("TRANSACTION_AMOUNT",
									(String) lContentMap
											.get("TRANSACTION_AMOUNT")); 																			

							if (lStrCaseId.endsWith(ClaimsConstants.TDS)) {
								isInsert = updateClaimAccountDetails(lparamMap); // if
																					// the
																					// payment
																					// is
																					// related
																					// to
																					// TDS

								if (isInsert) {
									isInsert = ClaimsConstants.BOOLEAN_FALSE;
									isInsert = ChangeTDSClaimStatus(lparamMap);
									claimsPaymentDao
											.setTDSAuditDetails(lparamMap); //for tds auditing purpose
								}
								if (isInsert)
									lResult = ClaimsConstants.ONE;
								else
									lResult = ClaimsConstants.ZERO;
							}
						}
					}

					if (isInsert) {
						resultList = claimsPaymentDao
								.insertUploadFile(lparamMap);  //saving file in two folder
						iUploadStatus = Integer.parseInt(resultList.get(0)
								.toString());
					}

					lResult = ClaimsConstants.ONE;
				} else {
					lResult = ClaimsConstants.ZERO;
				}

			} // end if
			else {
				lResult = ClaimsConstants.TWO;
				iUploadStatus = 0;
			}

			lparamMap.put("Message", lResult);
			lparamMap.put("UploadStatus", Integer.toString(iUploadStatus));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in method updateTDSClaimStatus--"
					+ e.getMessage());
		}
		return lparamMap;
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

		String lStrTdsPaymentId = ClaimsConstants.EMPTY;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		String paymentType = (String) lparamMap.get("PaymentType");
		EhfClaimTdsPayment ehfClaimTdsPayment = null;
		String failedCaseIdInList = ClaimsConstants.EMPTY;
		boolean failedIsInsert = ClaimsConstants.BOOLEAN_FALSE;

		for (String cases : claimFlowVO.getCaseSelected()) {
			try {
				lStrTdsPaymentId = cases;
				isInsert = ClaimsConstants.BOOLEAN_FALSE;
                //checking tds accomnt number
				isInsert = checkTDSAccNo(lparamMap);

				if (isInsert) {
					ehfClaimTdsPayment = genericDao.findById(
							EhfClaimTdsPayment.class, String.class,
							lStrTdsPaymentId);
					ehfClaimTdsPayment.setPaymentCheck(ClaimsConstants.T);
					ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
					if (ehfClaimTdsPayment.getPaymentCheck() != null
							&& (ClaimsConstants.T)
									.equalsIgnoreCase(ehfClaimTdsPayment
											.getPaymentCheck())) {
						isInsert = ClaimsConstants.BOOLEAN_TRUE;
					}
				} else {
					failedCaseIdInList = failedCaseIdInList + lStrTdsPaymentId
							+ ",";
					logger.error("failed cases are in catch block************"
							+ failedCaseIdInList+" ");
					lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				}
			} catch (Exception ex) {
				failedIsInsert = ClaimsConstants.BOOLEAN_TRUE;
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
		List<ClaimsFlowVO> accountList = new ArrayList<ClaimsFlowVO>();
		boolean isExist = ClaimsConstants.BOOLEAN_TRUE;
		try {
			// For checking whether TDS account details are present are not
			String case_Type = (String) lparamMap.get("TDS_CASE_TYPE");
			String schemeId = (String) lparamMap.get("SCHEME_ID");
			String act_Type = ClaimsConstants.EMPTY;
			if (ClaimsConstants.TRUST_DEDUCTOR.equals(case_Type)) {
				if(schemeId!=null && schemeId.equalsIgnoreCase("CD202"))
				    act_Type = ClaimsConstants.APTDSEHS;// "TDSAS2";
				else if(schemeId!=null && schemeId.equalsIgnoreCase("CD202"))
					act_Type = ClaimsConstants.TGTDSEHS;// "TDSAS2";
			} else if (ClaimsConstants.CMCO_DEDUCTOR.equals(case_Type)) {
				act_Type = ClaimsConstants.TDSCMO;// "TDSCMO";
			} else if (ClaimsConstants.INSURANCE_DEDUCTOR.equals(case_Type)) {
				act_Type = ClaimsConstants.TDSAS2;// "TDSAS1";
			}

			lStrBuffer = new StringBuffer();
			lStrBuffer
					.append(" select t.accountNo as accNo FROM EhfmTrustActDtls t,EhfmBankMaster b ");
			lStrBuffer
					.append(" where t.bankId=b.bankId and t.actType=? and t.activeYn=? ");

			String[] arg = new String[2];
			arg[0] = act_Type;
			arg[1] = ClaimsConstants.Y;

			accountList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					lStrBuffer.toString(), arg);

			if (accountList.size() == 0) {
				//logger.error("No TDS Account Details provided");
				isExist = ClaimsConstants.BOOLEAN_FALSE;
			}
		} catch (Exception le) {
			logger.error("Exception in method checkTDSAccNo--"
					+ le.getMessage());
		}

		return isExist;
	}

	/**
	 * Gets the tds account dtls.
	 *
	 * @param lparamMap the lparam map
	 * @return the tds account dtls
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTdsAccountDtls(HashMap lparamMap)// 126
	{
		ArrayList lResList = null;
		List lFileDataList = null;
		List lCaseList = null;
		String patientScheme=null;
		if(lparamMap.get("patScheme")!=null)
			patientScheme = (String)lparamMap.get("patScheme") ;
		Map lFileMap = new HashMap();
		
		StringBuffer lStrBuffer = null;
		String  lStrMailId = ClaimsConstants.EMPTY, lStrTdsType = ClaimsConstants.EMPTY, lStrFormPaymentType = ClaimsConstants.EMPTY, lStrTdsDeductor = ClaimsConstants.EMPTY;
		int lTotalCases = 0;
		List<ClaimsFlowVO> accountList = new ArrayList<ClaimsFlowVO>();
		double tdsAmt = 0;String lSchemeId = (String) lparamMap.get("SCHEME_ID");
		try {
			String lStrClientAcCode = ClaimsConstants.CLAIMTDS_CLIENT_CODE;
			if(lSchemeId!=null && lSchemeId.equalsIgnoreCase(config.getString("TG")))
			    lStrClientAcCode=ClaimsConstants.CLAIMTDS_CLIENT_TG_CODE;			
			else				
			{
				if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
					lStrClientAcCode=ClaimsConstants.CLAIMTDS_JHS_CLIENT_AP_CODE;
				else	
					lStrClientAcCode=ClaimsConstants.CLAIMTDS_CLIENT_TG_CODE;
			}	
			lStrFormPaymentType = (String) lparamMap.get("FormPaymentType");
			lStrTdsType = (String) lparamMap.get("TdsType");
			lStrMailId = (String) lparamMap.get("MailId");
			lStrTdsDeductor = (String) lparamMap.get("TDS_CASE_TYPE");

			lStrBuffer = new StringBuffer();

			if (ClaimsConstants.CLAIM_FORM_PAYMENT
					.equalsIgnoreCase(lStrFormPaymentType)
					&& ClaimsConstants.TRUST_DEDUCTOR
							.equalsIgnoreCase(lStrTdsDeductor)) {// 133
				lStrBuffer
						.append(" select tp.schemeId as schemeType, tp.tdsPaymentId as tdsPaymentId,tp.claimAmt as CLAIMAMOUNT,ca.srcAcctNo as accNo,tp.paymentType  as paymentType from EhfCase ac, EhfClaimAccounts ca, ");
				lStrBuffer
						.append(" EhfClaimTdsPayment tp where ac.caseId=ca.caseId and ac.caseId= tp.caseId and ");
				lStrBuffer
						.append(" tp.paymentStatus in (?) and tp.caseFllwUpId is null and tp.paymentCheck=? ");

				lparamMap.put("TDS_STAT_ID", ClaimsConstants.CLAIM_SENT);

			}
			if (ClaimsConstants.FPCLAIM_FORM_PAYMENT
					.equalsIgnoreCase(lStrFormPaymentType)
					&& ClaimsConstants.TRUST_DEDUCTOR
							.equalsIgnoreCase(lStrTdsDeductor)) {

				lStrBuffer
						.append(" select tp.schemeId as schemeType,tp.tdsPaymentId as tdsPaymentId,tp.claimAmt as CLAIMAMOUNT,fca.srcAcctNo as accNo,tp.paymentType  as paymentType");
				lStrBuffer
						.append(" from EhfCase ac, EhfCaseFollowupClaim fc, EhfClaimTdsPayment tp,EhfFollowUpClaimAccounts fca ");
				lStrBuffer
						.append(" where ac.caseId = fc.caseId and tp.caseFllwUpId = fc.caseFollowupId and fca.caseFollowUpId=fc.caseFollowupId ");
				lStrBuffer.append(" and tp.paymentStatus in (?)");
				lStrBuffer
						.append(" and tp.caseFllwUpId is not null  and tp.paymentCheck = ? ");

				lparamMap.put("TDS_STAT_ID", ClaimsConstants.CLAIM_FP_SENT);
				lStrClientAcCode = ClaimsConstants.FPTDS_CLIENT_CODE;
				if(lSchemeId!=null && lSchemeId.equalsIgnoreCase(config.getString("TG")))
				    lStrClientAcCode=ClaimsConstants.FPTDS_CLIENT_TG_CODE;			
				else
					lStrClientAcCode=ClaimsConstants.FPTDS_CLIENT_AP_CODE;	
			}
			if (ClaimsConstants.ERRCLAIM_FORM_PAYMENT
					.equalsIgnoreCase(lStrFormPaymentType)
					&& ClaimsConstants.TRUST_DEDUCTOR
							.equalsIgnoreCase(lStrTdsDeductor)) {

				lStrBuffer
						.append(" select tp.schemeId as schemeType,tp.tdsPaymentId as tdsPaymentId, tp.claimAmt as CLAIMAMOUNT, ca.srcAcctNo as accNo,tp.paymentType as paymentType ");
				lStrBuffer
						.append(" from EhfCase ac, EhfErroneousClaim er, EhfClaimTdsPayment tp,EhfClaimAccounts ca ");
				lStrBuffer
						.append(" where ac.caseId = er.caseId and er.errClaimId = tp.caseFllwUpId and ca.caseId=er.errClaimId ");
				lStrBuffer.append(" and tp.paymentStatus in (?) ");
				lStrBuffer
						.append(" and tp.caseFllwUpId is not null  and tp.paymentCheck = ? ");
				lparamMap.put("TDS_STAT_ID", ClaimsConstants.CLAIM_ERR_SENT);
				
				if(lSchemeId!=null && lSchemeId.equalsIgnoreCase(config.getString("TG")))
				    lStrClientAcCode=ClaimsConstants.ERRTDS_CLIENT_TG_CODE;			
				else
					lStrClientAcCode=ClaimsConstants.ERRTDS_CLIENT_AP_CODE;	
			}
			String[] arg = new String[2];
			arg[0] = (String)lparamMap.get("caseStatus");
			//arg[1] = ClaimsConstants.CLAIM_REJ_BANK;
			arg[1] = ClaimsConstants.T;

			accountList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					lStrBuffer.toString(), arg);

			lFileDataList = new ArrayList();
			lResList = new ArrayList();
			lCaseList = new ArrayList();
			
			for (ClaimsFlowVO claimsVO : accountList) {
				lFileMap = new HashMap();

				lFileMap.put("UNIQUE_ID", claimsVO.getTdsPaymentId());
				// lCaseList.add(claimsVO.getTdsPaymentId());
				lFileMap.put("CLAINT_AC_CODE", lStrClientAcCode);
				lFileMap.put("CLAINT_AC_NUMBER", claimsVO.getAccNo());
				lFileMap.put("EMAIL_ID", lStrMailId);
				lFileMap.put("SCHEME_ID", claimsVO.getSchemeType());
				// lFileDataList.add(lFileMap);
				// the TDS pct amount to be deducted and to be sent to a TDS
				// account
				tdsAmt = (Double) claimsVO.getCLAIMAMOUNT();

				if (tdsAmt > 0) {
					HashMap ldataMap = new HashMap();
					ldataMap.put("TdsAmount", tdsAmt);
					lFileMap.put("CaseStatus",
							(String)lparamMap.get("caseStatus"));
					lFileMap.put("PaymentType", "CD545");
					lFileMap.put("CaseList", lCaseList);
					lFileMap.put("FileDataList", lFileDataList);
					ldataMap.put("FileMap", lFileMap);
					// lFileMap.put("TdsAmount", tdsAmt);
					// lFileMap.put("TdsType", lStrTdsType);
					// lFileMap.put("FileDataList", lFileDataList);

					Map lresultMap = claimsPaymentDao.payTDSFund(ldataMap); // Seperate
																			// method
																			// which
																			// will
																			// take
																			// the
																			// TDS
																			// and
																			// returns
																			// a
																			// payment
																			// hashmap
																			// file
					lCaseList = (ArrayList) lresultMap.get("CaseList");
					lFileDataList = (ArrayList) lresultMap.get("FileDataList");
				}

				lTotalCases++;
			}
			lResList.add(lFileDataList);

			String smsNextVal=null;
			if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
				smsNextVal = getSequence("JHSCLAIMUPLOADFILE");
			else
				smsNextVal = getSequence("CLAIMUPLOADFILE");

			lResList.add(smsNextVal);
			lResList.add(lCaseList);
		} catch (Exception le) {
			logger.error("Exception in method getTdsAccountDtls in ClaimsflowDaoimpl--"
					+ le.getMessage());
		}

		return lResList;
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
		//logger.info("Start:ChangeTDSClaimStatus method in ClaimsflowDaoimpl");
		
		boolean isInsert = false;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		EhfClaimTdsPayment ehfClaimTdsPayment = null;

		ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class,
				String.class, (String) lparamMap.get("TDS_PAYMENT_ID"));
		ehfClaimTdsPayment.setPaymentStatus((String) lparamMap
				.get("TDS_STAT_ID"));
		ehfClaimTdsPayment.setTransStatus((String) lparamMap.get("SentStatus"));
		ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
		ehfClaimTdsPayment.setRemarks(ClaimsConstants.CLAIM_SENT_RMK);
		ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date()
				.getTime()));
		ehfClaimTdsPayment.setLastUpdUser(claimFlowVO.getUserId());
		ehfClaimTdsPayment.setPaymentCheck(ClaimsConstants.F);
		ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
		if (ehfClaimTdsPayment != null)
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
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
		System.out.println("Scheduler to updateClaimStatusSentByBank is started in TS");
		HashMap lparamMap = new HashMap();
		lparamMap.put("SentStatus", ClaimsConstants.SENT);
		lparamMap.put("ReadyStatus", ClaimsConstants.TransReadyStatus);
		lparamMap.put("RejectedCases", "");
		lparamMap.put(
				"SharePath",
				config.getString("mapPath")
						+ config.getString("claimPayment_filePath"));
		lparamMap.put("ClaimsRecievedPath",
				config.getString("localRecievedPath"));
		// for CaseClaims Payment
		lparamMap.put("ClamsInProgerss", ClaimsConstants.CLAIM_SENT);
		lparamMap.put("ClamSettled", ClaimsConstants.CLAIM_PAID);
		lparamMap.put("ClaimFailed", ClaimsConstants.CLAIM_REJ_BANK);
		lparamMap.put("AcknowledgmentRcvd", ClaimsConstants.CLAIM_ACK_REC);
		lparamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
		lparamMap.put("TransPaidStatus", ClaimsConstants.TransPaidStatus);		
		lparamMap.put("TransAckStatus", ClaimsConstants.TRANSACKSTATUS);
		lparamMap.put("TransRejStatus", ClaimsConstants.TRANSREJSTATUS);
		lparamMap.put("REV_FND_REMARKS", ClaimsConstants.REVFUND_REMARK);
		// Followup claim payment
		lparamMap.put("FlupClamsSent", ClaimsConstants.CLAIM_FP_SENT);
		lparamMap.put("FlupClamPaid", ClaimsConstants.FPCLAIM_PAID);
		lparamMap.put("FlupClaimRej", ClaimsConstants.FPCLAIM_REJ_BANK);
		lparamMap.put("FlupAcknowledgmentRcvd", ClaimsConstants.FPCLAIM_ACK);
		
		lparamMap.put("ClaimPaidRemarks", ClaimsConstants.CLAIM_PAY_DONE);
		lparamMap.put("ClaimAckRemarks", ClaimsConstants.CLAIM_ACKNOWLEDGED);
		lparamMap.put("REMARKS", "");
		lparamMap.put("CRTUSER",ClaimsConstants.CEO_USER_ID);
		// 007 Erroneous claim payment
		lparamMap.put("ErroneousClamsSent", ClaimsConstants.CLAIM_ERR_SENT);
		lparamMap.put("ErroneousClamPaid", ClaimsConstants.ERRCLAIM_PAID);
		lparamMap.put("ErroneousClaimRej", ClaimsConstants.CLAIM_ERR_REJ);
		lparamMap.put("ErroneousClaimAck", ClaimsConstants.ERRCLAIM_ACK);
		lparamMap.put("ClosedStatus", ClaimsConstants.CLOSED);
		lparamMap.put("UPD_USR", ClaimsConstants.CEO_USER_ID);
		lparamMap.put("LANG_ID", ClaimsConstants.LANG_ID);

		try {
			// pkioutput folder path
			String FilePath = ClaimsConstants.EMPTY;
			String lStrSrcDir = config.getString("mapPath")
					+ config.getString("claimPayment_filePath")
					+ config.getString("claimPKIOutput_filePath");
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
					qry.append("select distinct paymentType as  paymentType from  EhfSliaPayments  where fileName=? and paymentType in ('EHSETG','EHSFTG','EHSNTG','JHSNTG'))");
					List<ClaimsFlowVO> fileType=genericDao.executeHQLQueryList(ClaimsFlowVO.class,qry.toString(),args);
					if(fileType!=null && fileType.size()>0 && fileType.get(0)!=null && fileType.get(0).getPaymentType()!=null && !"".equalsIgnoreCase(fileType.get(0).getPaymentType()))
					{
						FilePath = Files[FileCnt];
						destination = new File(FilePath);
						//will download and save in receivedClaim folder and do the process
						lparamMap.put("RejectedCases", "");
						downLoadFile(lStrSrcDir, Files[FileCnt],
								destination.toString(), lparamMap);
					}	
				}
			}
		} catch (Exception e) {
			logger.error("@Exception has raised in updateClaimStatusSentByBank() due to--->"
					+ e.getMessage());
		}
		System.out.println("Scheduler to updateClaimStatusSentByBank is ended in TS");
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
		String lStrResult = ClaimsConstants.EMPTY, lClaimsRecvPath = ClaimsConstants.EMPTY, lStrSharePath = ClaimsConstants.EMPTY;
		String lStrDestDir = ClaimsConstants.EMPTY, srcFilePath = ClaimsConstants.EMPTY, destFilePath = ClaimsConstants.EMPTY;
		int RIndex = src.indexOf(ClaimsConstants.DOT);
		String OrgFileName = src.substring(0, RIndex + 1)
				+ src.substring(RIndex + 2, src.length());
		lClaimsRecvPath = (String) lparamMap.get("ClaimsRecievedPath");
		lStrSharePath = (String) lparamMap.get("SharePath");
		lStrDestDir = lStrSharePath + lClaimsRecvPath;
		srcFilePath = lStrSrcDir + ClaimsConstants.SLASH + src;
		try {
			destFilePath = lStrSharePath + lClaimsRecvPath
					+ ClaimsConstants.SLASH + dest;
       /*     if (OrgFileName.substring(0, 4).toUpperCase().equalsIgnoreCase(ClaimsConstants.CLAIM_CLIENT_CODE)
            		||OrgFileName.substring(0, 4).toUpperCase().equalsIgnoreCase(ClaimsConstants.CLAIM_CLIENT_CODE_JHS)
            		||OrgFileName.substring(0, 6).toUpperCase().equalsIgnoreCase(ClaimsConstants.CLAIMTDS_CLIENT_CODE)            		
            		||OrgFileName.substring(0, 6).toUpperCase().equalsIgnoreCase(ClaimsConstants.CLAIMTDS_CLIENT_CODE_JHS)
            		||OrgFileName.substring(0, 4).toUpperCase().equalsIgnoreCase(ClaimsConstants.ERRCLAIM_CLIENT_CODE)
            		||OrgFileName.substring(0, 7).toUpperCase().equalsIgnoreCase(ClaimsConstants.ERRTDS_CLIENT_CODE)
            		||OrgFileName.substring(0, 4).toUpperCase().equalsIgnoreCase(ClaimsConstants.FPCLAIM_CLIENT_CODE)
            		||OrgFileName.substring(0, 7).toUpperCase().equalsIgnoreCase(ClaimsConstants.FPTDS_CLIENT_CODE)
            		||OrgFileName.substring(0, 4).toUpperCase().equalsIgnoreCase(ClaimsConstants.CHRONIC_CLAIM_CLIENT_CODE)
            		||OrgFileName.substring(0, 5).toUpperCase().equalsIgnoreCase(ClaimsConstants.AHC_CLAIM_CLIENT_CODE))
            {*/
            	/*!OrgFileName.substring(0, 4).toUpperCase().equals("EHSP")*/
        		
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

			DataInputStream data_in = new DataInputStream(bis);
			lDatalist = readContents(data_in);*/
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
				OrgFileName=(String) lDatalist.get(3);
			}

			if (Count > 0 && lContentList.size() > 0) {
				lparamMap.put("DataList", lContentList);
                //if followup
				if (OrgFileName.substring(0, 4).toUpperCase().equals(ClaimsConstants.FPCLAIM_CLIENT_CODE)) {
					lStrResult = claimsPaymentDao
							.SetFollowupClaimStatus(lparamMap);
				} //if erroneous
				else if (OrgFileName.substring(0, 4).toUpperCase().equals(ClaimsConstants.ERRCLAIM_CLIENT_CODE))// 024
				{
					lStrResult = claimsPaymentDao
							.processErroneousClaims(lparamMap);
				}//if chrnicOp
				else if (OrgFileName.substring(0, 4).toUpperCase().equals("EHSC"))// 024
				{
					//lStrResult = chronicFlowPaymentDao
					//		.processChronicClaims(lparamMap);
				} 				
				else if (OrgFileName.substring(0, 4).toUpperCase().equals(ClaimsConstants.CLAIM_CLIENT_CODE_JHS)){ //if normal claim
					lStrResult = claimsPaymentDao.SetJHSClaimStatus(lparamMap);
				}
				else { //if normal claim
					lStrResult = claimsPaymentDao.SetClaimStatus(lparamMap);
				}
			}
			/*}*/
		} catch (Exception e) {
			logger.error("@Exception has raised in downLoadFile() due to--->"
					+ e.getMessage());
		}
	}

	/**
	 * Read contents.
	 *
	 * @param data_in the data_in
	 * @return the array list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
/*	public ArrayList readContents(DataInputStream data_in) {
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
            for (int i=0; i<num; i++) 
            {
                Element node = (Element) entries.item(i);
                String caseId=node.getAttribute("AGENCY_CR_REF");
                if(caseId!=null && !"".equalsIgnoreCase(caseId) && caseId.startsWith("E/"))
                {
       			  caseId=caseId.substring(2);
    			 
	    			 if(caseId.contains("/R"))
	    			 {
	    				 if(caseId.endsWith("/TDS") || caseId.endsWith("/G"))
	    				 {
	    					 
	    					if(caseId.endsWith("/TDS")) 
	    					{
	    						 String temp=caseId.substring(caseId.indexOf("/R"),caseId.indexOf("/TDS"));
	    						 caseId=caseId.replace(temp, "");
	    					}
	    					else if(caseId.endsWith("/G"))
	    					{
	    						String temp=caseId.substring(caseId.indexOf("/R"),caseId.indexOf("/G"));
	    						 caseId=caseId.replace(temp, "");
	    					}
	    				 }
	    				 else
	    				 {
	    					 caseId=caseId.split("/R")[0];
	    					 System.out.println(caseId);
	    				 }
	    			 }
                }
              
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
		String flag = ClaimsConstants.FALSE;
		File source = new File(src);
		File destination = new File(dest);
		File destDir = new File(destDirPath);

		boolean lbDir = ClaimsConstants.BOOLEAN_FALSE;
		if (!destDir.exists()) {
			lbDir = destDir.mkdirs();
		} else {
			lbDir = ClaimsConstants.BOOLEAN_TRUE;
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
			flag = ClaimsConstants.TRUE;
		}

		if (flag == ClaimsConstants.TRUE) {
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
	 * Gets the sequence.
	 *
	 * @param pStrSeqName the str seq name
	 * @return the sequence
	 * @throws Exception the exception
	 */
	public String getSequence(String pStrSeqName) throws Exception {
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
	
	@Override
	@Transactional
	public String updateClaimStatusReady(ClaimsFlowVO claimFlowVO) {
		String lResult = "";
		String lStrNextStatus = getNextStatus(
				claimFlowVO.getCaseStat(),
				claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
		try{
		for(String caseId : claimFlowVO.getCaseSelected()){
			if (claimFlowVO.getPaymentType() != null
					&& !claimFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY)
					&& claimFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.ErroneousClaim)) {
				
			EhfErroneousClaim ehfErroneousClaim = new EhfErroneousClaim();
			ehfErroneousClaim = genericDao.findById(EhfErroneousClaim.class, String.class, caseId);
			ehfErroneousClaim.setErrClaimStatus(lStrNextStatus);
			ehfErroneousClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfErroneousClaim.setLstUpdUsr(claimFlowVO.getUserId());
			genericDao.save(ehfErroneousClaim);
			
			String lStrCaseId = caseId.substring(0,caseId.indexOf(ClaimsConstants.SLASH));
			
			EhfCase ehfCase = new EhfCase();
			ehfCase = genericDao.findById(EhfCase.class, String.class, lStrCaseId);
			ehfCase.setErrClaimStatus(lStrNextStatus);  
			ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
			genericDao.save(ehfCase);
			lResult = ClaimsConstants.ONE;
			
			}else{
				EhfCase ehfCase = new EhfCase();
				ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
				ehfCase.setCaseStatus(lStrNextStatus);  
				ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
				genericDao.save(ehfCase);
				if("CD1411".equalsIgnoreCase(lStrNextStatus)){
					Long lActOrder = 0L;
					StringBuffer lQueryBuffer = new StringBuffer();
					String args[] = new String[1];
					lQueryBuffer.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
					args[0] = caseId;
					List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(ClaimsFlowVO.class, lQueryBuffer.toString(), args);
					if (actOrderList != null && !actOrderList.isEmpty() && actOrderList.get(0).getCOUNT() != null) {
						if (actOrderList.get(0).getCOUNT().longValue() >= 0)
							lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
					}
					EhfAudit asritAudit = new EhfAudit();
					EhfAuditId asritAuditPK = new EhfAuditId(lActOrder,	caseId);
					asritAudit.setId(asritAuditPK);
					asritAudit.setActId(lStrNextStatus);
					asritAudit.setActBy(claimFlowVO.getUserId());
					asritAudit.setCrtUsr(claimFlowVO.getUserId());
					asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
					asritAudit.setLangId(ClaimsConstants.LANG_ID);
					asritAudit.setRemarks("Verified by Accounts JEO");
					asritAudit.setUserRole(claimFlowVO.getRoleId());
					genericDao.save(asritAudit);
				}
				lResult = ClaimsConstants.ONE;
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
			lResult = ClaimsConstants.ZERO;
		}		
		return lResult;
	}
	
	
	
	
	/*added for chronic claims by venkatesh*/
	
	@Override
	@Transactional
	public String updateChronicClaimStatusReady(ClaimsFlowVO claimFlowVO) {
		String lResult = "";
		String lStrNextStatus = getNextStatus(
				claimFlowVO.getCaseStat(),
				claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
		try{
		for(String chronicNo : claimFlowVO.getCaseSelected()){
			
				
			/*EhfErroneousClaim ehfErroneousClaim = new EhfErroneousClaim();
			ehfErroneousClaim = genericDao.findById(EhfErroneousClaim.class, String.class, caseId);
			ehfErroneousClaim.setErrClaimStatus(lStrNextStatus);
			ehfErroneousClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfErroneousClaim.setLstUpdUsr(claimFlowVO.getUserId());
			genericDao.save(ehfErroneousClaim);*/
			
			//String lStrCaseId = chronicId.substring(0,chronicId.indexOf(ClaimsConstants.SLASH));
			String chronicId=null;
			
				int occurance = StringUtils.countOccurrencesOf(chronicNo, "/");
				if(occurance==3)
				{
					chronicId=chronicNo.substring(0, chronicNo.lastIndexOf('/'));	
				
				}
				EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
			
				EhfChronicCaseDtl ehfChronicCaseDtl = genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
			if(ehfChronicCaseDtl!=null)
			{
				ehfChronicCaseDtl.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfChronicCaseDtl.setLstUpdUsr(claimFlowVO.getUserId());
				ehfChronicCaseDtl.setChronicStatus(lStrNextStatus);
				
				
			}
			genericDao.save(ehfChronicCaseDtl);
			lResult = ClaimsConstants.ONE;
			
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			lResult = ClaimsConstants.ZERO;
		}
		if(lResult!=null){
			if(lStrNextStatus.equalsIgnoreCase(config.getString("CLAIM-SENT-FOR-PAYMENT")))	
			{
				lResult = ClaimsConstants.ONE;
			}
			if(lStrNextStatus.equalsIgnoreCase(config.getString("CO-CLAIM-SENT-BACK-TO-CH")))	
			{
				lResult = ClaimsConstants.FOUR;
			}
			}
		return lResult;
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
	@Override
	@Transactional
	public String updateTDSStatusReady(ClaimsFlowVO claimFlowVO) {
		String lResult = "";
		
		try{
		for(String caseId : claimFlowVO.getCaseSelected()){
			EhfClaimTdsPayment ehfTdsPayment = new EhfClaimTdsPayment();
			ehfTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class, caseId);
			String lStrNextStatus = getNextStatus(
					ehfTdsPayment.getPaymentStatus(),
					claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
			ehfTdsPayment.setPaymentStatus(lStrNextStatus);  
			ehfTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
			ehfTdsPayment.setLastUpdUser(claimFlowVO.getUserId());
			genericDao.save(ehfTdsPayment);
			lResult = ClaimsConstants.ONE;			
		}
		}catch(Exception ex){
			ex.printStackTrace();
			lResult = ClaimsConstants.ZERO;
		}		
		return lResult;
	}
	
	public boolean updateFailedCaseStatus(String caseId,String paymentType)
	{
		
		boolean isUpdate=false;
		if(paymentType
				.equalsIgnoreCase(ClaimsConstants.ErroneousClaim))
		{
			EhfErroneousClaim ehfErroneousClaim=new EhfErroneousClaim();
			ehfErroneousClaim=genericDao.findById(EhfErroneousClaim.class, String.class, caseId);
			if(ehfErroneousClaim!=null)
			{
			try{
				ehfErroneousClaim.setErrClaimStatus(config.getString("EHF.Claims.ClaimFailed"));
				ehfErroneousClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfErroneousClaim=genericDao.save(ehfErroneousClaim);
				isUpdate=true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				isUpdate=false;
			}
			}
			
		}
		else
		{
		EhfCase ehfCase=new EhfCase();
		ehfCase=genericDao.findById(EhfCase.class, String.class, caseId);
		if(ehfCase!=null)
		{
		try{
			ehfCase.setCaseStatus(config.getString("EHF.Claims.ClaimFailed"));
			ehfCase.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfCase=genericDao.save(ehfCase);
			isUpdate=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			isUpdate=false;
		}
		}
	}
		
		return isUpdate;
		}
	//To get consumable details
	public ClaimsFlowVO getInvestigationDetails(ClaimsFlowVO claimsFlowVO) throws Exception{
		List<LabelBean> investDetails = null;
		StringBuffer queryBuff = new StringBuffer();
		//To get consumable details
		if(!"".equalsIgnoreCase(claimsFlowVO.getNimsFlag()) && "N".equalsIgnoreCase(claimsFlowVO.getNimsFlag()))
		{	
			queryBuff.append(" select type WFTYPE, quant_name NAME, sum(no_of_units) COUNT, ehq.amount UNITAMOUNT, sum(ec.amount) AMOUNT,ec.attach_path ATTACH   from ehf_case_enhancement_dtls ec, ehfm_hosp_quantity_nabh ehq where enh_quan_code = quant_id  and ec.active_y_n='Y'  and case_id = ? group by type, quant_name ,ehq.amount,ec.attach_path ");
		}
		else
		{
			queryBuff.append(" select type WFTYPE, item_desc NAME, sum(no_of_units) COUNT, ehq.amount UNITAMOUNT, sum(ec.amount) AMOUNT,ec.attach_path ATTACH   from ehf_case_enhancement_dtls ec, EHFM_HOSP_QUANTITY_NIMS ehq where enh_quan_code = item_code  and ec.active_y_n='Y'  and case_id = ? group by type, item_desc ,ehq.amount,ec.attach_path ");
		}
		investDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimsFlowVO.getCaseId()} );
			
		if(investDetails != null && investDetails.size() > 0 ){
			claimsFlowVO.setInvestDetails(investDetails);
		}
		//To get total consumable amount
		queryBuff = new StringBuffer();
		queryBuff.append("  select sum(amount) as TOTCONSUMABLEAMOUNT from ehf_case_enhancement_dtls where  case_id = ? and active_y_n='Y' ");
		investDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimsFlowVO.getCaseId()} );
			
		if(investDetails != null && investDetails.size() > 0 ){
			claimsFlowVO.setTotalConsumableAmount(investDetails.get(0).getTOTCONSUMABLEAMOUNT());
		}
		
		return claimsFlowVO;
	}
//To get drug details
	@Override
	public ClaimsFlowVO getDrugDetails(ClaimsFlowVO claimFlowVO) {

		List<LabelBean> drugDetails = null;
		StringBuffer queryBuff = new StringBuffer();
		//To get Medical Surgical Flag 
		queryBuff.append(" select patient_ipop MEDISURGFLAG from ehf_patient,ehf_case where case_id=? and patient_id=case_patient_no ");
		drugDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimFlowVO.getCaseId()} );
        //If medical Flag
		if(drugDetails!=null && drugDetails.get(0).getMEDISURGFLAG().equalsIgnoreCase(ClaimsConstants.MEDICAL_FLAG))
		{
			//To retreive medical details 
			queryBuff = new StringBuffer();
			claimFlowVO.setMedicalSurgFlag(ClaimsConstants.MEDICAL_FLAG);	
			queryBuff.append(" select ee.caseId as CASEID, cast(ee.amount as string )  as DRUGAMOUNT, cast(ee.id.sno as string) as SEQID,  ee.attachName as ATTACHNAME, ee.attachPath as ATTACHPATH, to_char(ee.crtDt,'DD-MM-YYYY hh24:mi:ss') as CRTDT ");
			queryBuff.append(" from EhfPatientDrugsNabh ee  WHERE ee.activeYN = 'Y' and ee.caseId = '"+claimFlowVO.getCaseId()+"' ");
			drugDetails=genericDao.executeHQLQueryList(LabelBean.class, queryBuff.toString());	
			
			//queryBuff.append(" select ehq.route_name WFTYPE, ehq.drug_name NAME, sum(ec.units) COUNT,   ec.amountperunit DRUGUNITAMOUNT, sum(ec.tot_amt) AMOUNT   from EHF_CASE_ENH_PATIENT_DRUGS ec, ehfm_drugs_mst ehq where ec.asri_drug_code = ehq.drug_code and case_id = ? and ec.activeyn='Y' group by ehq.route_name, ehq.drug_name, ec.amountperunit ");
			//drugDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimFlowVO.getCaseId()} );
			
			if(drugDetails != null && drugDetails.size() > 0 ){
				claimFlowVO.setDrugDetails(drugDetails);
			}
			//To get total drug amount
			queryBuff = new StringBuffer();
			queryBuff.append("  select sum(AMOUNT) as TOTALDRUGAMT from EHF_PATIENT_DRUGS_NABH where  case_id = ? and ACTIVE_YN='Y' ");
			drugDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimFlowVO.getCaseId()} );
				
			if(drugDetails != null && drugDetails.size() > 0 ){
				claimFlowVO.setTotalDrugAmt(drugDetails.get(0).getTOTALDRUGAMT());
			}
		}
		else
		{
			claimFlowVO.setMedicalSurgFlag(ClaimsConstants.SURGICAL_FLAG);			
		}
		
		
		
		return claimFlowVO;
	}
	
	
	
    public HashMap insertIntoPaymentTable(ArrayList lContentList,HashMap lparamMap)
    {
    	logger.info("In insertIntoPaymentTable method of ClaimsFlowDaoImpl");
    	try{
    		
        String lStrBankId="";         
        String lStrUniqueId="",lStrBnfActName="",lStrBnfActId="",lStrBnfAddr="";
        String lStrBnfBankName="",lStrBnfBankBranch="",lStrBnfActNo="",lStrTransAmt="";
        String lStrSrcActNo="",lStrTrans_Type="",lStrIfcCode="",lStrEmailId="",lStrSrcIfscNo="";
        String lStrClaintCode=""; 
        int FileListSize = 0,flag = 0;
        ArrayList lFileList=new ArrayList(); 
        List<EhfSliaPayments> ehfSliaPaymentsList = new ArrayList<EhfSliaPayments>();
        EhfSliaPayments ehfSliaPayments = null;
        Map lFileMap = new HashMap();
        if(lContentList.size()>0) {
            lFileList=(ArrayList)lContentList.get(0);
            FileListSize  = lFileList.size();
            for(int i=0;i<FileListSize;i++)
            {
                //HashMap Contains the Beneficiary Account Information
                lFileMap=(HashMap)lFileList.get(i);  
                   
                lStrUniqueId=(String)lFileMap.get("UNIQUE_ID");
                lStrBnfActName=(String)lFileMap.get("BENEFICIARY_ACCOUNT_NAME");
                lStrBnfActId=(String)lFileMap.get("BENEFICIARY_ID");
                lStrBnfAddr=(String)lFileMap.get("BENEFICIARY_ADDR");
                lStrBnfBankName=(String)lFileMap.get("BENEFICIARY_BANK_NAME");
                lStrBnfBankBranch=(String)lFileMap.get("BANK_BRANCH");
                lStrBnfActNo=(String)lFileMap.get("BENEFICIARY_ACCOUNT_NO");
                lStrTransAmt=(String)lFileMap.get("TRANSACTION_AMOUNT");
                lStrBankId=(String)lFileMap.get("BENEFICIARY_BANK_ID");
                lStrIfcCode=(String)lFileMap.get("BENEFICIARY_BANK_IFC_CODE");
                lStrClaintCode=(String)lFileMap.get("CLAINT_AC_CODE");
                lStrSrcActNo=(String)lFileMap.get("CLAINT_AC_NUMBER");
                lStrSrcIfscNo=(String)lFileMap.get("CLIENT_IFSC_CODE");
                
                
              //  lStrTrans_Type=(String)lFileMap.get("TRANSACTION_TYPE");
                lStrEmailId=(String)lFileMap.get("EMAIL_ID");                
                    if(lStrBnfBankBranch.trim().length()<1)
                    lStrBnfBankBranch=lStrBnfBankBranch.trim();
                    if(lStrBnfActName.length()>0) {
                        lStrBnfActName=lStrBnfActName.replaceAll(","," ");
                        lStrBnfActName=lStrBnfActName.trim();                
                    }    
                    if(lStrBnfBankBranch.length()>0) {
                        lStrBnfBankBranch=lStrBnfBankBranch.replaceAll(","," ");
                        lStrBnfBankBranch=lStrBnfBankBranch.trim();                
                    }
                    if(lStrBnfAddr.length()>0) {
                        lStrBnfAddr=lStrBnfAddr.replaceAll(","," ");
                        lStrBnfAddr=lStrBnfAddr.trim();                
                    }
                    String lStrStatus = lFileMap.get("REJECTED_PAYMENT")!=null?(String)lFileMap.get("REJECTED_PAYMENT"):"TN";
                   
                    if(lStrUniqueId.length()>0 && lStrBnfActName.length()>0 && lStrBnfActId.length()>0 && lStrBnfAddr.length()>0 && lStrBankId.length()>0 &&
                    lStrBnfBankName.length()>0 && lStrBnfBankBranch.length()>0 && lStrBnfActNo.length()>0 && lStrIfcCode.length()>0 && lStrSrcActNo.length()>0) {
                        ehfSliaPayments = genericDao.findById(EhfSliaPayments.class,String.class,lStrUniqueId);
                        if(ehfSliaPayments!=null){
                            if(lStrStatus.equalsIgnoreCase("true")){
                            	lStrStatus="TR";
                                ehfSliaPayments.setRejectCount((ehfSliaPayments.getRejectCount()!=null?ehfSliaPayments.getRejectCount():0L)+1L);
                                ehfSliaPayments.setFileName("");
                            }
                        }else{
                            ehfSliaPayments = new EhfSliaPayments(); 
                            lStrStatus="TN"; 
                        }                        
                        ehfSliaPayments.setTransId(lStrUniqueId);
                        ehfSliaPayments.setStatus(lStrStatus);
                        ehfSliaPayments.setPaymentType(lStrClaintCode);
                        ehfSliaPayments.setFileFlag("N") ;
                        ehfSliaPayments.setBeneficiaryId(lStrBnfActId);
                        ehfSliaPayments.setBeneficiaryAddress(lStrBnfAddr);
                        ehfSliaPayments.setBeneficiaryAccountName(lStrBnfActName);
                        ehfSliaPayments.setBeneficiaryAccountNo(lStrBnfActNo);
                        ehfSliaPayments.setBeneficiaryBankId(lStrBankId) ;
                        ehfSliaPayments.setBeneficiaryBankIfsc(lStrIfcCode) ;
                        ehfSliaPayments.setBeneficiaryBankName(lStrBnfBankName) ;
                        ehfSliaPayments.setBankBranch(lStrBnfBankBranch) ;
                        ehfSliaPayments.setTransactionAmount(Double.parseDouble(lStrTransAmt));
                        ehfSliaPayments.setClientAccountNo(lStrSrcActNo);
                        ehfSliaPayments.setClientIfsc(lStrSrcIfscNo);
                        ehfSliaPayments.setPaymentMode((lStrIfcCode.contains("SBIN")?"I":"N"));
                        ehfSliaPayments.setEmailId(lStrEmailId);
                        ehfSliaPayments.setCrtDate((Date)lparamMap.get("currentDate"));
                       
                        ehfSliaPaymentsList.add(ehfSliaPayments);
                    }
                }
                if(ehfSliaPaymentsList.size() > 0 ){
                    genericDao.saveAll(ehfSliaPaymentsList);
                    flag++;
                }
            lparamMap.put("Flag",Integer.toString(flag));
        }
    	}
    	catch(Exception e)
    	{
    		logger.error("Error in insertIntoPaymentTable in CliamsFlowDaoImpl"+e.getMessage());
    		e.printStackTrace();
    	}
        return lparamMap;   
    }
  //Chandana - 8602 - New method for getting the claim cases list
    @Override
	public List<LabelBean> getOpdClaimCasesListForACO(HashMap hashMap) {
    	String claimDt  = (String) hashMap.get("claimDt");
		String patientId  = (String) hashMap.get("patId");
		String crNo = (String) hashMap.get("crNo"); 
		String letterNo = (String) hashMap.get("letterNo");;
		String claimStatus = (String) hashMap.get("clmStatus");
		String opClaimNo = (String) hashMap.get("opClaimNo");
		String cardNo = (String) hashMap.get("cardNo");
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
        int cnt=0; 
        boolean hasFilter = false;
        try{
        	query.append(" SELECT CLAIM_NO as PATIENTID, eop.SEQ_ID AS COUNT, ecd.CMB_DTL_ID AS ID, CR_NO AS CARDNO, PATIENT_NAME AS PNAME, LETTER_NO as LETTERNO, CLAIM_ORG as CLAIMORG,eop.OP_CLAIM_SEQ AS CLAIMNO, eocd.CLAIM_AMOUNT as SNO, ");
        	query.append(" TO_CHAR(CLAIM_DT, 'DD/MM/YYYY HH24:MI:SS') AS CLAIMDT, TOTAL_BILL AS AMOUNT, TO_CHAR(EOP.MEDCO_CLM_DT, 'DD/MM/YYYY HH24:MI:SS') AS CRTDT, ep.CARD_NO AS EHFCARDNO,ecd.CMB_DTL_NAME AS VALUE ");
        	query.append(" FROM EHF_OPD_PATIENTS eop, EHF_PATIENT ep,EHFM_CMB_DTLS ecd,EHF_OPD_CLAIM_DTLS eocd WHERE EP.PATIENT_ID = EOP.CLAIM_NO AND ecd.CMB_DTL_ID = eop.CLAIM_STATUS AND ep.REG_HOSP_ID = 'EHS34' ");
        	query.append(" AND eocd.OP_CLAIM_ID = eop.OP_CLAIM_SEQ ");
        	if(patientId != null && !"".equalsIgnoreCase(patientId))
        		query.append(" AND eop.CLAIM_NO ='"+patientId+"' ");
        	if(cardNo != null && !"".equalsIgnoreCase(cardNo))
        		query.append(" AND ep.CARD_NO ='"+cardNo+"' ");
        	if(claimStatus != null && !"-1".equalsIgnoreCase(claimStatus))
        		query.append(" AND EOP.CLAIM_STATUS = '"+claimStatus+"' ");
        	else
        		query.append(" AND eop.CLAIM_STATUS in ('CD7305','CD7344') ");
        	if(opClaimNo != null && !"".equalsIgnoreCase(opClaimNo))
        		query.append(" AND eop.OP_CLAIM_SEQ ='"+opClaimNo+"' ");
        	if(claimDt != null && !"".equalsIgnoreCase(claimDt))
        		query.append(" AND TO_CHAR(EOP.CLAIM_DT, 'DD/MM/YYYY') = '"+claimDt+"' ");
        	
        	query.append(" ORDER BY EOP.MEDCO_CLM_DT ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception e){
        	e.printStackTrace();
        }
		return resList;
	}
    //Chandana - 8602 - New method
    @Override
	@Transactional
	public ClaimsFlowVO saveOpdClaim(ClaimsFlowVO claimFlowVO) throws Exception {

		String result = null;
		double deductAmount = 0;
		double finalAmount = 0;
		String opdClaimNo = claimFlowVO.getCaseId();
		String claimStatus = claimFlowVO.getCaseStat();
		String userId = claimFlowVO.getUserId();
		String patientId = claimFlowVO.getPatientId();
		try{
			List<EhfOpdPatient> ehfOpdPatientLst = new ArrayList<EhfOpdPatient>();
			if(opdClaimNo != null && !"".equalsIgnoreCase(opdClaimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimSeq", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdPatientLst = genericDao.findAllByCriteria(EhfOpdPatient.class, criteriaList);
				
				if(ehfOpdPatientLst != null && !ehfOpdPatientLst.isEmpty()){
					for(EhfOpdPatient ehfopdDtls : ehfOpdPatientLst){
						ehfopdDtls.setClaimStatus(claimStatus);	
						ehfopdDtls.setLstUpdDt(new Date());
						ehfopdDtls.setLstUpdUsr(userId);
						genericDao.save(ehfopdDtls);
						result = "Success";
					}
				}
				/*boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId, patientId);*/
				List<EhfOpdClaimDtls> ehfOpdClaimDtlLst = new ArrayList<EhfOpdClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimId", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdClaimDtlLst = genericDao.findAllByCriteria(EhfOpdClaimDtls.class, criteriaList);
				
				if(!ehfOpdClaimDtlLst.isEmpty()){
					for(EhfOpdClaimDtls claimDtls : ehfOpdClaimDtlLst){
						claimDtls.setClaimStatus(claimStatus);
						claimDtls.setAcoApprvAmount(claimDtls.getClaimAmount());
						claimDtls.setAcoApprvDt(new Date());
						claimDtls.setAcoApprvUsr(userId);
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, claimFlowVO.getAcoRemark(), userId, patientId, finalAmount, claimFlowVO.getRoleId());
			}
			
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return claimFlowVO;
    }
  //Chandana - 8602 - New method for keep pending the claim case by ACO
	@Transactional
	public String pendingOpClaimByACO(HashMap hashMap) throws Exception {
		String result = null;
		String claimStatus = hashMap.get("nextStatus").toString();
		String opdClaimNo = hashMap.get("opdClaimNo").toString();
		String remarks = hashMap.get("remarks").toString();
		String finalAmt = hashMap.get("finalAmt").toString();
		String userId = hashMap.get("userId").toString();
		String patientId = hashMap.get("patientId").toString();
		String userRole = hashMap.get("userRole").toString();
		double finalAmount = 0;
		if(finalAmt != null)
			finalAmount = Double.parseDouble(finalAmt);
		try{
			List<EhfOpdPatient> ehfOpdPatientLst = new ArrayList<EhfOpdPatient>();
			if(opdClaimNo != null && !"".equalsIgnoreCase(opdClaimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimSeq", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdPatientLst = genericDao.findAllByCriteria(EhfOpdPatient.class, criteriaList);
				
				if(ehfOpdPatientLst != null && !ehfOpdPatientLst.isEmpty()){
					for(EhfOpdPatient ehfopdDtls : ehfOpdPatientLst){
						ehfopdDtls.setClaimStatus(claimStatus);												
						genericDao.save(ehfopdDtls);
						result = "Success";
					}
				}
				/*boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId, patientId);*/
				List<EhfOpdClaimDtls> ehfOpdClaimDtlLst = new ArrayList<EhfOpdClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimId", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdClaimDtlLst = genericDao.findAllByCriteria(EhfOpdClaimDtls.class, criteriaList);
				
				if(!ehfOpdClaimDtlLst.isEmpty()){
					for(EhfOpdClaimDtls claimDtls : ehfOpdClaimDtlLst){
						claimDtls.setClaimStatus(claimStatus);
						//claimDtls.setClaimAmount(finalAmount);
						claimDtls.setAcoApprvAmount(finalAmount);
						claimDtls.setAcoApprvUsr(userId);
						claimDtls.setAcoApprvDt(new Date());
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId, patientId, finalAmount, userRole);
			}
			
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return result;
	}
    //Chandana - 8602 - New method for inserting the audit for NIMS OPD claims
    private boolean insertAuditRecord(String opdClaimSeq, String status, String remarks, String userId, String patientId, Double claimAmnt, String userRole) {
		boolean response = false;
		try{
			List<EhfOpdClaimAudit> ehfOpdClaimAuditLst = new ArrayList<EhfOpdClaimAudit>();
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.opClaimSeq", opdClaimSeq, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfOpdClaimAuditLst = genericDao.findAllByCriteria(EhfOpdClaimAudit.class, criteriaList);
			double maxActOrder = 0;
			if(!ehfOpdClaimAuditLst.isEmpty()){
				for(EhfOpdClaimAudit audit : ehfOpdClaimAuditLst){
					double currentActOrder = audit.getId().getActOrder();
					if(currentActOrder > maxActOrder)
						maxActOrder = currentActOrder;
				}
			}
			double newActOrder = maxActOrder +1;
			EhfOpdClaimAuditPK ehfOpdClaimAuditPK = new EhfOpdClaimAuditPK();
			ehfOpdClaimAuditPK.setOpClaimSeq(opdClaimSeq);
			ehfOpdClaimAuditPK.setActOrder(newActOrder);
			EhfOpdClaimAudit ehfOpdClaimAudit = new EhfOpdClaimAudit();
			ehfOpdClaimAudit.setId(ehfOpdClaimAuditPK);
			ehfOpdClaimAudit.setClaimStatus(status);
			ehfOpdClaimAudit.setPatientId(patientId);
			ehfOpdClaimAudit.setRemarks(remarks);
			ehfOpdClaimAudit.setActBy(userId);
			ehfOpdClaimAudit.setCrtUsr(userId);
			ehfOpdClaimAudit.setCrtDt(new Date());	
			ehfOpdClaimAudit.setClaimAmount(claimAmnt);
			ehfOpdClaimAudit.setUserRole(userRole);
			genericDao.save(ehfOpdClaimAudit);
			response = true;
		}catch(Exception le){
			le.printStackTrace();
			response = false;
		}
		return response;
	}
}
