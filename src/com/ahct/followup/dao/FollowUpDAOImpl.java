package com.ahct.followup.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.dao.ClaimsPaymentDAO;
import com.ahct.claims.dao.ClaimsFlowDAO;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.TransactionVO;
import com.ahct.common.util.GenerateAsciiFile;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseClinicalNotes;
import com.ahct.model.EhfCaseFlpupDrug;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfClaimTdsPayment;
import com.ahct.model.EhfClaimTrustPayment;
import com.ahct.model.EhfClinicalFlpupMpg;
import com.ahct.model.EhfClinicalFlpupMpgId;
import com.ahct.model.EhfCochlearFollowup;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfErroneousClaim;
import com.ahct.model.EhfFollowUpAudit;
import com.ahct.model.EhfFollowUpAuditId;
import com.ahct.model.EhfFollowUpClaimAccounts;
import com.ahct.model.EhfFollowupChklst;
import com.ahct.model.EhfFollowupTests;
import com.ahct.model.EhfSsrMedinpData;
import com.ahct.model.EhfmDrugsMst;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.ahct.preauth.vo.AttachmentVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.DrugsVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class FollowUpDAOImpl implements FollowUpDAO{
	
	

	
	ClaimsPaymentDAO claimsPaymentDao;
	public ClaimsPaymentDAO getClaimsPaymentDao() {
		return claimsPaymentDao;
	}

	public void setClaimsPaymentDao(ClaimsPaymentDAO claimsPaymentDao) {
		this.claimsPaymentDao = claimsPaymentDao;
	}
	
	
	ClaimsFlowDAO claimsFlowDAO;
	
	
	
	public ClaimsFlowDAO getClaimsFlowDAO() {
		return claimsFlowDAO;
	}

	public void setClaimsFlowDAO(ClaimsFlowDAO claimsFlowDAO) {
		this.claimsFlowDAO = claimsFlowDAO;
	}


	GenericDAO genericDao;
	
	
	public GenericDAO getGenericDao() {
		return genericDao;
	}


	private static final Logger logger = Logger
	.getLogger(FollowUpDAOImpl.class);
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
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	@Transactional
	public String saveFollowUp(FollowUpVO followUpVo, String pStrUserId) {
		
		String lStrFollowUpId = "";String followUpOrder="";
		Long lStrClinicalId = getNextVal(config.getString("EHF_CASE_CLINICAL_NOTES_seq"));
		followUpVo.setCLINICALID("CC"+lStrClinicalId);
		
		EhfCaseClinicalNotes ehfCaseClinicalNotes=new EhfCaseClinicalNotes();
		ehfCaseClinicalNotes.setClinicalId("CC"+lStrClinicalId);
		ehfCaseClinicalNotes.setBpSystolic(followUpVo.getBLOODPRESSURE());
		ehfCaseClinicalNotes.setCaseId(followUpVo.getCASEID());
		ehfCaseClinicalNotes.setCrtDt(new java.sql.Timestamp ( new Date().getTime () ) );
		ehfCaseClinicalNotes.setCrtUsr(pStrUserId);
		ehfCaseClinicalNotes.setFluidInput(followUpVo.getFLUIDINPT());
		ehfCaseClinicalNotes.setFluidOutput(followUpVo.getFLUIDOUTPUT());
		ehfCaseClinicalNotes.setHeartRate(followUpVo.getHEART_RATE());
		ehfCaseClinicalNotes.setLungsCondition(followUpVo.getLUNGS());
		ehfCaseClinicalNotes.setPrePostOperative(followUpVo.getFlag());
		ehfCaseClinicalNotes.setPulse(followUpVo.getPULSE());
		ehfCaseClinicalNotes.setRespiratory(followUpVo.getRESPIRATORY());
		ehfCaseClinicalNotes.setTemperature(followUpVo.getTEMPERATURE());
		genericDao.save(ehfCaseClinicalNotes);
		
		if(followUpVo.getFollowUpSeq()!=null && !followUpVo.getFollowUpSeq().equalsIgnoreCase(""))
		lStrFollowUpId = followUpVo.getCASEID()+"/"+followUpVo.getFollowUpSeq();//getNextFollowUpId(followUpVo);
		
		followUpVo.setFollowUpId(lStrFollowUpId);
		Long actualPackamt = getPackAmt(followUpVo);
		
		//followUpOrder = getFollowUpOrder(followUpVo);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		
		EhfClinicalFlpupMpg ehfClinicalFlpupMpg = new EhfClinicalFlpupMpg();
		ehfClinicalFlpupMpg.setCaseId(followUpVo.getCASEID());
		ehfClinicalFlpupMpg.setId(new EhfClinicalFlpupMpgId(lStrFollowUpId,"CC"+lStrClinicalId));
		
		ehfClinicalFlpupMpg.setCrtUser(pStrUserId);
		ehfClinicalFlpupMpg.setCrtDt(new java.sql.Timestamp ( new Date().getTime () ) );
		if(followUpVo.getNxtFollowUpDt()!=null)
			{
				if(!"".equalsIgnoreCase(followUpVo.getNxtFollowUpDt()))
					{
						try
							{
								ehfClinicalFlpupMpg.setNxtFollowupDt(dateFormat.parse(followUpVo.getNxtFollowUpDt()));	
							}
						catch(Exception e)
							{
								e.printStackTrace();
								System.out.println("Error occured while saving the Followup details");
								logger.error("Error occured while getting the Followup date"+e.getMessage());
							}
					}
			}
			
		
		genericDao.save(ehfClinicalFlpupMpg);
		
		EhfCaseFollowupClaim ehfCaseFollowupClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class, lStrFollowUpId);
			if(ehfCaseFollowupClaim==null){
				
				ehfCaseFollowupClaim = new EhfCaseFollowupClaim();
				ehfCaseFollowupClaim.setCaseFollowupId(lStrFollowUpId);
				ehfCaseFollowupClaim.setCaseId(followUpVo.getCASEID());
				EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, followUpVo.getCASEID());
				if(ehfCase!=null && ehfCase.getSchemeId()!=null && !ehfCase.getSchemeId().equalsIgnoreCase("1") &&
						ehfCase.getPatientScheme()!=null)
					{
						ehfCaseFollowupClaim.setSchemeId(ehfCase.getSchemeId());
						ehfCaseFollowupClaim.setPatientScheme(ehfCase.getPatientScheme());
					}
				else{
					String schemeId = getSchemeIdForCases(followUpVo.getCASEID());
					ehfCaseFollowupClaim.setSchemeId(schemeId);
				}					
				ehfCaseFollowupClaim.setFollowUpStatus(config.getString("EHF.FollowUP.FlpDone"));
				ehfCaseFollowupClaim.setClaimAmount((double) 0);
				ehfCaseFollowupClaim.setActualPack((long) actualPackamt);
				ehfCaseFollowupClaim.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				ehfCaseFollowupClaim.setCrtUsr(pStrUserId);
				ehfCaseFollowupClaim = genericDao.save(ehfCaseFollowupClaim);
			}
			
		EhfCaseFlpupDrug ehfCaseFlpupDrug=null;		
		for(DrugsVO drugsVO : followUpVo.getDrugs())
          {
			ehfCaseFlpupDrug = new EhfCaseFlpupDrug();
			Date drugExpriryDate=new Date();
			if(drugsVO.getDrugExpiryDt()!=null)
				{
					if(!"".equalsIgnoreCase(drugsVO.getDrugExpiryDt()))
						{
							try
								{
									SimpleDateFormat sdsf=new SimpleDateFormat("dd-MM-yyyy");
									drugExpriryDate=sdsf.parse(drugsVO.getDrugExpiryDt());
								}
							catch(Exception e)
								{
									e.printStackTrace();
									System.out.println("Error occured in Parsing date in saveFollowUp method");
									logger.error("Error occured in Parsing date in saveFollowUp method "+e.getMessage());
								}
						}
				}
			ehfCaseFlpupDrug.setSrlId(drugsVO.getDrugId().toString());
			ehfCaseFlpupDrug.setClinicalId(followUpVo.getCLINICALID());
			ehfCaseFlpupDrug.setCrtUser(pStrUserId);
			ehfCaseFlpupDrug.setCrtDt(new java.sql.Timestamp ( new Date().getTime () ) );
			ehfCaseFlpupDrug.setDosage(drugsVO.getDosage());
			ehfCaseFlpupDrug.setDrugCode(drugsVO.getDrugName());
			ehfCaseFlpupDrug.setDrupTypeCode(drugsVO.getDrugTypeName());
			ehfCaseFlpupDrug.setMedication(drugsVO.getMedicationPeriod());
			ehfCaseFlpupDrug.setBatchNumber(drugsVO.getBatchNumber());
			ehfCaseFlpupDrug.setDrugExpiryDate(drugExpriryDate);
			ehfCaseFlpupDrug = genericDao.save(ehfCaseFlpupDrug);
          }
		 
		 String lStrTestDesc=null;
		 List<AttachmentVO> lAttachList=followUpVo.getGenAttachmentsList();
		 
         for(AttachmentVO attachmentVO:lAttachList){
             
        	 EhfFollowupTests followUpTests=new EhfFollowupTests();
        	 followUpTests.setSno(Long.parseLong(attachmentVO.getAttachId()));
        	 followUpTests.setTestId(attachmentVO.getTestId());
           if(lStrTestDesc!=null){
             lStrTestDesc=lStrTestDesc+","+attachmentVO.getTestName();
           }
           else{
               lStrTestDesc=attachmentVO.getTestName();  
           }
           followUpTests.setFollowupId(lStrFollowUpId);
           followUpTests.setClinicalId("CC"+lStrClinicalId);
           followUpTests.setAttachTotalPath(attachmentVO.getFileReportPath());   
           followUpTests.setCrtUsr(pStrUserId);
           followUpTests.setCrtDt(new java.sql.Timestamp ( new Date().getTime () ) );
           genericDao.save(followUpTests);
           
         }	
         if(ehfCaseFlpupDrug!=null)
        	 return lStrFollowUpId;
         else
        	 return null;
		/*if(ehfCaseFlpupDrug!=null)
			return "Followup submitted successfully with FollowUp ID : "+lStrFollowUpId;
		else
			return "faliure";*/		
	}

	private String getSchemeIdForCases(String caseid) {
		StringBuffer lQueryBuffer = new StringBuffer();
		String schemeType="";
		String args[] = new String[1];
		lQueryBuffer
		.append(" select c.scheme as schemeType from EhfCase a,EhfPatient b,EhfEnrollment c where a.casePatientNo=b.patientId and b.employeeNo=c.empCode and a.caseId=?  ");
		args[0] = caseid;

		List<FollowUpVO> caseList = genericDao.executeHQLQueryList(
				FollowUpVO.class, lQueryBuffer.toString(), args);
		if (caseList != null && !caseList.isEmpty()) {
				schemeType = caseList.get(0).getSchemeType();
		}
		return schemeType;
	}

	private String getFollowUpOrder(FollowUpVO followUpVo) {
		StringBuffer lQueryBuffer = new StringBuffer();
		Long lActOrder=1L;
		String args[] = new String[1];
		lQueryBuffer
		.append(" select * from EhfClinicalFlpupMpg au where au.id.followUpId=? ");
		args[0] = followUpVo.getFollowUpId();

		List<EhfClinicalFlpupMpg> actOrderList = genericDao.executeHQLQueryList(
				EhfClinicalFlpupMpg.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()) {
			if (actOrderList.size() >= 0)
				lActOrder = (long) (actOrderList.size() + 1);
		}
		return lActOrder+"";
	}

	private Long getPackAmt(FollowUpVO followUpVo) {
		Long actualPack = 0L;
		String[] args = new String[1];
		args[0] = followUpVo.getCASEID();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append(" SELECT SUM(AFP2.packageAmt) as totalPackAmt,SUM(AFP2.firstInstPkg)  as  fistInstAmt, ");
		queryBuff.append(" SUM(AFP2.subsequentInstPkg)  as  nextInstAmt FROM EhfCaseTherapy ACS, EhfmFollowupPackages AFP2,EhfCase EC ");
		queryBuff.append(" WHERE EC.caseId = ? and ACS.activeyn='Y' and AFP2.activeYN='Y' and AFP2.effEndDt is null  ");
		queryBuff.append(" AND ACS.icdProcCode = AFP2.id.surgeryId AND EC.caseId=ACS.caseId ");  // ACS.icdProcCode IN (SELECT AFP.id.surgeryId FROM EhfmFollowupPackages AFP) AND
		queryBuff.append(" AND EC.schemeId=AFP2.id.schemeId GROUP BY ACS.caseId");
		List<FollowUpVO> casePackLst = genericDao.executeHQLQueryList(FollowUpVO.class, queryBuff.toString(),args);
		if(casePackLst!=null && casePackLst.size()>0){
			if(followUpVo.getFollowUpId().contains("/1")){
				actualPack = casePackLst.get(0).getFistInstAmt();
			}
			else{
				actualPack = casePackLst.get(0).getNextInstAmt();
			}
		}
		return actualPack;
	}
	@Override
	public Long getNextFollowUpId(FollowUpVO followUpVo) {
		Long lFollowUpOrder=0L;
		String followUpId=ClaimsConstants.EMPTY;
		/** The millis per day. */
		final long MILLIS_PER_DAY = 24 * 3600 * 1000;
		try{
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,
					followUpVo.getCASEID());
			if (ehfCase != null) {
				long msDiff;
				if (ehfCase.getCsDisDt() != null)
					msDiff = new Date().getTime()
							- ehfCase.getCsDisDt().getTime();
				else
					msDiff = new Date().getTime()
							- ehfCase.getCsDeathDt().getTime();
				long daysDiff = Math.round(msDiff / ((double) MILLIS_PER_DAY));
			if(daysDiff>=0 && daysDiff<=90)
				lFollowUpOrder=1L;
			else if(daysDiff>90 && daysDiff<=180)
				lFollowUpOrder=2L;
			else if(daysDiff>180 && daysDiff<=270)
				lFollowUpOrder=3L;
			else if(daysDiff>270 && daysDiff<=360)
				lFollowUpOrder=4L;
			}/*
			StringBuffer lQueryBuffer=new StringBuffer();        
	        lQueryBuffer.append(" select count(*) as COUNT from EhfClinicalFlpupMpg au where au.caseId='"+followUpVo.getCASEID()+"'");
	        List<LabelBean> follwUpList=genericDao.executeHQLQueryList(LabelBean.class,lQueryBuffer.toString());
	        if(follwUpList!=null && !follwUpList.isEmpty() && follwUpList.get(0).getCOUNT()!=null)
	        {
	            	lFollowUpOrder=follwUpList.get(0).getCOUNT().longValue()+1;
	        }*/
	       //followUpId=lFollowUpOrder+"";
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return lFollowUpOrder;
	}
	 private Long getNextVal(String SeqName)
	    {
	    	String lStrSeqRetVal = "";   
	    	Long cnt = (long) 0;
	    	  try{
	    		  StringBuffer query = new StringBuffer();
	    		  query.append(" SELECT "+SeqName+".NEXTVAL NEXTVAL  FROM DUAL ");
	     	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
	     	        if(seqList != null){	        	
	     	          lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
	     	         cnt = Long.parseLong(lStrSeqRetVal);
	    	  }
	    	  }
	    catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	  return cnt;
	    }
	@Override
	@Transactional
	public FollowUpVO saveFollowUpClaim(FollowUpVO followUpVO) {
		logger.info("In saveFollowupClaim dao");
			Long lActOrder = 1L;
			String OnloadCaseOpen=null;
			if(followUpVO.getOnloadCaseOpen()!=null)
				OnloadCaseOpen=followUpVO.getOnloadCaseOpen();
			String lStrNextStatus = null;String lStrHospName =null;
			String smsMsg = ClaimsConstants.EMPTY;
			String remarks=ClaimsConstants.EMPTY;
			Double apprAmt=0.0;
			String msg="Failure";
			StringBuffer lQueryBuffer = new StringBuffer();
			String args[] = new String[1];
			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfFollowUpAudit au where au.id.caseFollowupId=? ");
			args[0] = followUpVO.getFollowUpId();
			List<FollowUpVO> actOrderList = genericDao.executeHQLQueryList(
					FollowUpVO.class, lQueryBuffer.toString(), args);
			if (actOrderList != null && !actOrderList.isEmpty()
					&& actOrderList.get(0).getCOUNT() != null) {
				if (actOrderList.get(0).getCOUNT().longValue() >= 0)
					lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
			}
			try {
				//EhfClinicalFlpupMpg ehfClinicalFlpupMpg = genericDao.findById(EhfClinicalFlpupMpg.class, String.class,
				//		followUpVO.getFollowUpId());

				EhfCaseFollowupClaim caseFollowUpClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class,
						followUpVO.getFollowUpId());
				
				if(caseFollowUpClaim.getFollowUpStatus() != null && caseFollowUpClaim.getFollowUpStatus() != null && !caseFollowUpClaim.getFollowUpStatus().equalsIgnoreCase(followUpVO.getFollowUpStatus()))
				{
					msg = "Case has already been updated by other User.";
					followUpVO.setMsg(msg);
					if(OnloadCaseOpen!=null)
						if(OnloadCaseOpen.equalsIgnoreCase("Y"))
							{
								if(followUpVO.getRoleId()!=null)
								{
									if(!followUpVO.getRoleId().equalsIgnoreCase(config.getString("preauth_nam_role"))
											&&!followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.MEDCO")))
										followUpVO.setUnlockCase("Y");
								}
								
							}
							
					followUpVO.setSmsMsg(ClaimsConstants.EMPTY);
					return followUpVO;
				}	
				
				if(caseFollowUpClaim.getCaseId()!=null){
				EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,
						caseFollowUpClaim.getCaseId());
				EhfmHospitals ehfmHosp = genericDao.findById(EhfmHospitals.class, String.class, ehfCase.getCaseHospCode());
				lStrHospName = ehfmHosp.getHospName();
				followUpVO.setPatientId(ehfCase.getCasePatientNo());
				}
				
				if (followUpVO.getFollowUpId() != null
						&& caseFollowUpClaim.getFollowUpStatus() != null) {
					lStrNextStatus = getNextStatus(caseFollowUpClaim.getFollowUpStatus(),
							followUpVO.getRoleId(), followUpVO.getActionDone());
				}	
				if (followUpVO.getFollowUpId() != null
						&& caseFollowUpClaim.getFollowUpStatus() != null && "CD63".equalsIgnoreCase(caseFollowUpClaim.getFollowUpStatus()))
				{
					lStrNextStatus = config.getString("EHF.FollowUP.FCXForwarded");
				}
				
				
				if(caseFollowUpClaim!=null){
					caseFollowUpClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
					caseFollowUpClaim.setLstUpdUsr(followUpVO.getUserId());
				}
				else
					{
						EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,caseFollowUpClaim.getCaseId());
						caseFollowUpClaim = new EhfCaseFollowupClaim();
						
						caseFollowUpClaim.setCaseFollowupId(followUpVO.getFollowUpId());
						caseFollowUpClaim.setCaseId(followUpVO.getCaseId());
						if(ehfCase!=null)
							if(ehfCase.getPatientScheme()!=null)
								caseFollowUpClaim.setPatientScheme(ehfCase.getPatientScheme());
						caseFollowUpClaim.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
						caseFollowUpClaim.setCrtUsr(followUpVO.getUserId());
						caseFollowUpClaim.setFlupClaimSubDate(new java.sql.Timestamp(new Date().getTime()));
						}
				
							
				
				if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.MEDCO"))){
					logger.info("In saveFollowupClaim medco");
					if(followUpVO.getClaimNwhAmt()!=null){
					caseFollowUpClaim.setClaimNwhAmount(Double.parseDouble(followUpVO.getClaimNwhAmt()));
					apprAmt=Double.parseDouble(followUpVO.getClaimNwhAmt());
					
					if(followUpVO.getFollowUpId()!=null)
					{
						
								double totalClaimAmt=0;
								double totalPckAmt=0;
								double prevCarryFwd=0;
								double prsntCarryFwd=0;
								double actualPackAmt=0;
								List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
								criteriaList.add(new GenericDAOQueryCriteria("caseId",followUpVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("crtDt",followUpVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.DESC));
								List<EhfCaseFollowupClaim> followUpClaimList = genericDao.findAllByCriteria(EhfCaseFollowupClaim.class, criteriaList);
									if(followUpClaimList!=null)
										{
											if(followUpClaimList.size()>0)
												{
												
													for(int i=0;i<followUpClaimList.size();i++)
													{
														if(followUpClaimList.get(i).getCaseFollowupId().equalsIgnoreCase(followUpVO.getFollowUpId()))
														{
															if(i==followUpClaimList.size()-1)
															{
																if(caseFollowUpClaim.getActualPack()!=null)
																	caseFollowUpClaim.setCarryFwdAmt(caseFollowUpClaim.getActualPack()-apprAmt);
																else
																	caseFollowUpClaim.setCarryFwdAmt((double)0);
															}
															else
															{
															System.out.println(followUpClaimList.get(i).getCaseFollowupId());
															System.out.println(followUpClaimList.get(i+1).getCaseFollowupId());
															System.out.println(followUpClaimList.get(i+1).getCarryFwdAmt());
															System.out.println("Actual pack :"+followUpClaimList.get(i).getActualPack());
															prevCarryFwd=followUpClaimList.get(i+1).getCarryFwdAmt();
															actualPackAmt=followUpClaimList.get(i).getActualPack();
															totalPckAmt=actualPackAmt+prevCarryFwd;
															prsntCarryFwd=totalPckAmt-apprAmt;
															System.out.println("prsntCarryFwd :"+prsntCarryFwd);
																	
															caseFollowUpClaim.setCarryFwdAmt(prsntCarryFwd);
															}
														}
													}
													
												
											
															
															
														
														
													
												}
										}
								
							
					}
					}
					
					caseFollowUpClaim.setNwhRemark(followUpVO.getMedcoRemarks());
					remarks=followUpVO.getMedcoRemarks();
					if (lStrNextStatus!=null &&  lStrNextStatus.equalsIgnoreCase(config.getString("EHF.FollowUP.CHPendUpdated"))) 
					{
						msg = "CH pending updated successfully by MEDCO.";
						smsMsg = "CH pending updated successfully by MEDCO for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";						
					}
					else if (lStrNextStatus!=null &&  lStrNextStatus.equalsIgnoreCase(config.getString("EHF.FollowUP.FTDPendUpdated")))
					{
						msg = "FTD pending updated successfully by MEDCO.";
						smsMsg = "FTD pending updated successfully by MEDCO for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";
					}
					else
					{
						
						if(lStrNextStatus.equalsIgnoreCase(config.getString("EHF.FollowUP.Initiated")))
							caseFollowUpClaim.setFlupClaimSubDate(new java.sql.Timestamp(new Date().getTime()));
						
						msg = "Follow UP Claim Initiated.";
						smsMsg = "Follow UP Claim initiated successfully by MEDCO with FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";
					}
					logger.info("In saveFollowupClaim after save");
				}
				logger.info("In saveFollowupClaim not save in medco");
				if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("preauth_nam_role"))){
					caseFollowUpClaim.setNamRemark(followUpVO.getNamRemarks());
					remarks=followUpVO.getNamRemarks();
					if(caseFollowUpClaim.getClaimNwhAmount()!=null)
					followUpVO.setClaimNamAmt(caseFollowUpClaim.getClaimNwhAmount().toString());
					if(followUpVO.getClaimNamAmt()!=null){
					caseFollowUpClaim.setClaimNamAmount(Double.parseDouble(followUpVO.getClaimNamAmt()));
					apprAmt=Double.parseDouble(followUpVO.getClaimNamAmt());
					}
										
					msg = "Follow UP Claim Forwarded to FCX.";
					
				}
				if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.FollowUP.FCX"))){
					saveCheckListFP(followUpVO);
					caseFollowUpClaim.setFcxRemark(followUpVO.getFcxRemark());
					remarks=followUpVO.getFcxRemark();
					
					caseFollowUpClaim.setCexPharmacyBill(followUpVO.getClaimFcxPharmBill());
					caseFollowUpClaim.setCexConsultBill(followUpVO.getClaimFcxConsulBill());
					caseFollowUpClaim.setCexInvestBill(followUpVO.getClaimFcxInvestBill());
					if(followUpVO.getClaimFcxAmt()!=null)
						caseFollowUpClaim.setClaimFcxAmount(Double.valueOf(followUpVO.getClaimFcxAmt()));
					else
						caseFollowUpClaim.setClaimFcxAmount(0.0);
						
					apprAmt=caseFollowUpClaim.getClaimFcxAmount();
					
					//if(followUpVO.getClaimFcxAmt()!=null){
					//	caseFollowUpClaim.setClaimFcxAmount(Long.parseLong(followUpVO.getClaimFcxAmt()));
					//	apprAmt=Long.parseLong(followUpVO.getClaimFcxAmt());
					//}
					/*if(caseFollowUpClaim.getClaimNwhAmount()!=null){
						caseFollowUpClaim.setClaimFcxAmount(caseFollowUpClaim.getClaimNwhAmount());
						apprAmt=caseFollowUpClaim.getClaimNwhAmount();
						}*/
					msg = "Follow UP Claim Verified.";
				}
				if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.FollowUP.FTD"))){
					saveCheckListFP(followUpVO);
					caseFollowUpClaim.setFtdRemark(followUpVO.getFtdRmks());
					remarks=followUpVO.getFtdRmks();
					if(followUpVO.getClaimFtdAmt()!=null && !"".equalsIgnoreCase(followUpVO.getClaimFtdAmt())) {
						caseFollowUpClaim.setClaimFtdAmount(Double.parseDouble(followUpVO.getClaimFtdAmt()));
						apprAmt=Double.parseDouble(followUpVO.getClaimFtdAmt());
						
						if(followUpVO.getClaimFTDPharmBill()!=null)
							caseFollowUpClaim.setFTDPharmacyBill(followUpVO.getClaimFTDPharmBill());
						if(followUpVO.getClaimFTDConsulBill()!=null)
							caseFollowUpClaim.setFTDConsultBill(followUpVO.getClaimFTDConsulBill());
						if(followUpVO.getClaimFTDInvestBill()!=null)
							caseFollowUpClaim.setFTDInvestBill(followUpVO.getClaimFTDInvestBill());
						
						}
					if(followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.RedAppButton")))
						msg = "Follow UP Claim Approved by FTD.";
						else if(followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.RejButton")))
							{msg = "Follow UP Claim Rejected by FTD.";
							smsMsg = "Follow UP Claim Rejected by FTD for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";
							caseFollowUpClaim.setCarryFwdAmt(caseFollowUpClaim.getActualPack().doubleValue());
							}
						else if(followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.PendButton")))
							{msg = "Follow UP Claim Pending by FTD.";
							smsMsg = "Follow UP Claim kept Pending by FTD for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";
							//caseFollowUpClaim.setPendingBy(followUpVO.getRoleId());
							}
					
					
				
				}
				
				if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.CH"))){
					caseFollowUpClaim.setChRemark(followUpVO.getChRemark());
					remarks=followUpVO.getChRemark();
					if(followUpVO.getClaimChAmt()!=null && !"".equalsIgnoreCase(followUpVO.getClaimChAmt())){
						caseFollowUpClaim.setClaimChAmount(Double.parseDouble(followUpVO.getClaimChAmt()));
						apprAmt=Double.parseDouble(followUpVO.getClaimChAmt());
						
							
						
						if(followUpVO.getClaimCHPharmBill()!=null)
							caseFollowUpClaim.setCHPharmacyBill(followUpVO.getClaimCHPharmBill());
						if(followUpVO.getClaimCHConsulBill()!=null)
							caseFollowUpClaim.setCHConsultBill(followUpVO.getClaimCHConsulBill());
						if(followUpVO.getClaimCHInvestBill()!=null)
							caseFollowUpClaim.setCHInvestBill(followUpVO.getClaimCHInvestBill());
					}
					if(followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.AppButton"))){
						msg = "Follow UP Claim Approved by CH.";
						smsMsg = "Follow UP Claim Approved by Claim Head for followup ID : "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";
						//caseFollowUpClaim.setTotClaimAmt(Long.parseLong(followUpVO
						//		.getClaimChAmt()));
						//ehfClinicalFlpupMpg.setClaimAmount(Long.parseLong(followUpVO.getClaimChAmt()));
					}
						else if(followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.RejButton")))
							{msg = "Follow UP Claim Rejected by CH.";
					        smsMsg = "Follow UP Claim Rejected by Claim Head for followup ID : "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";}
						else if(followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.PendButton")))
							{msg = "Follow UP Claim Pending by CH.";
					        smsMsg = "Follow UP Claim kept pending by Claim Head for followup ID : "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";
					        //caseFollowUpClaim.setPendingBy(followUpVO.getRoleId());
					        //caseFollowUpClaim.setCarryFwdAmt(0.0);
					        }
						else if (followUpVO.getActionDone().equalsIgnoreCase(config.getString("EHF.VerifyButton"))) {
							msg = "Follow UP Claim Verified by CH.";
							//caseFollowUpClaim.setTotClaimAmt(Long.parseLong(followUpVO
							//		.getClaimChAmt()));
						}
										
				}
				
				if (followUpVO.getRoleId().equalsIgnoreCase(
						config.getString("EHF.Claims.ACO"))) {

					remarks = followUpVO.getAcoRemark();
					if(caseFollowUpClaim.getClaimChAmount()!=null)
						followUpVO.setAcoAprAmt(caseFollowUpClaim.getClaimChAmount().toString());					

					if (followUpVO.getAcoAprAmt() != null
							&& !followUpVO.getAcoAprAmt().equalsIgnoreCase(ClaimsConstants.EMPTY)) {
						
						caseFollowUpClaim.setAcoAprAmt(Double.parseDouble(followUpVO
								.getAcoAprAmt()));
						if(followUpVO.getAcoAprAmt()!=null)
						apprAmt = Double.parseDouble(followUpVO.getAcoAprAmt());
						
						caseFollowUpClaim.setTotClaimAmt(Double.parseDouble(followUpVO
								.getAcoAprAmt()));
					}
					caseFollowUpClaim.setAcoRemrk(remarks);

					if (followUpVO.getActionDone().equalsIgnoreCase(
							config.getString("EHF.VerifyButton"))) {

						EhfFollowUpClaimAccounts ehfFpClaimAccounts = new EhfFollowUpClaimAccounts();
						ehfFpClaimAccounts.setCaseFollowUpId(followUpVO.getFollowUpId());
						if(followUpVO.getAcoAprAmt()!=null)
						ehfFpClaimAccounts.setAprvdAmt(Double.parseDouble(followUpVO
								.getAcoAprAmt()));
						ehfFpClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date()
								.getTime()));
						ehfFpClaimAccounts.setCrtUsr(followUpVO.getUserId());
						ehfFpClaimAccounts.setRemarks(remarks);
						ehfFpClaimAccounts.setTransStatus(ClaimsConstants.TransReadyStatus);
						ehfFpClaimAccounts.setTimeMilSec((long) 0);
						ehfFpClaimAccounts = genericDao.save(ehfFpClaimAccounts);

						caseFollowUpClaim.setClaimAmount(Double.parseDouble(followUpVO.getAcoAprAmt()));
						
						msg = "Verified Succesfully by ACO.";
						smsMsg = "Verified successfully by ACO for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";					
					}
					if (followUpVO.getActionDone().equalsIgnoreCase(
							config.getString("EHF.RevertButton"))) {

					

						msg = "Followup Claim Reverted by ACO";
						smsMsg = "Followup Claim Reverted by ACO for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";					
					}
				}
				
				
				if (followUpVO.getRoleId().equalsIgnoreCase(
						config.getString("EHF.Claims.CEO"))) {

					remarks = followUpVO.getCeoRemark();
					caseFollowUpClaim.setCeoRemark(remarks);

					if (followUpVO.getActionDone().equalsIgnoreCase(
							config.getString("EHF.SentBackButton"))) {
						msg = "Successfully Sent back to CH.";
						smsMsg = "Successfully Sent back to Claim Head for FollowUp ID: "+followUpVO.getFollowUpId()+" in "+lStrHospName+".";				
					}
				}
				
				
				followUpVO.setSmsMsg(smsMsg);
				
				EhfFollowUpAudit folowUpAudit = new EhfFollowUpAudit();
				EhfFollowUpAuditId folowUpAuditPK = new EhfFollowUpAuditId(lActOrder,
						followUpVO.getFollowUpId(),"CLAIM");
				folowUpAudit.setId(folowUpAuditPK);
				folowUpAudit.setActId(lStrNextStatus);
				folowUpAudit.setActBy(followUpVO.getUserId());
				folowUpAudit.setCrtUsr(followUpVO.getUserId());
				folowUpAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				folowUpAudit.setLangId(ClaimsConstants.LANG_ID);
				folowUpAudit.setRemarks(remarks);
				folowUpAudit.setApprAmt(apprAmt);
				folowUpAudit.setUserRole(followUpVO.getRoleId());
				
				caseFollowUpClaim.setFollowUpStatus(lStrNextStatus);
				//ehfClinicalFlpupMpg.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				//ehfClinicalFlpupMpg.setLstUpdUser(followUpVO.getUserId());
				caseFollowUpClaim.setViewFlag("N");	
				caseFollowUpClaim.setCaseBlockedDt(new java.sql.Timestamp(new Date().getTime()));
				
				genericDao.save(folowUpAudit);
				//genericDao.save(followUpchcklst);
				genericDao.save(caseFollowUpClaim);
				//genericDao.save(ehfClinicalFlpupMpg);
				
				if(OnloadCaseOpen!=null)
					if(OnloadCaseOpen.equalsIgnoreCase("Y"))
						{
							if(followUpVO.getRoleId()!=null)
							{
								if(!followUpVO.getRoleId().equalsIgnoreCase(config.getString("preauth_nam_role"))
										&& !followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.MEDCO"))
										&& !followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.CEO")))
									followUpVO.setUnlockCase("Y");
							}
							
						}
				
				
				followUpVO.setMsg(msg);
				logger.info("In saveFollowupClaim end of method");
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error occured in saveFollowUpClaim() in FollowUpDaoimpl class."+e.getMessage());
				followUpVO.setMsg("Failure");
			}
			logger.info("In saveFollowupClaim retur method");
			return followUpVO;
		}
	
	private void saveCheckListFP(FollowUpVO followUpVO) {
		
		EhfFollowupChklst followUpchcklst;
		EhfFollowupChklst followUpchcklstExist = genericDao.findById(EhfFollowupChklst.class, String.class, followUpVO.getFollowUpId());
		
		if(followUpchcklstExist!=null)
			{
			followUpchcklst = followUpchcklstExist;
			followUpchcklst.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			followUpchcklst.setLstUpdUsr(followUpVO.getUserId());
			}
		else{			
			followUpchcklst = new EhfFollowupChklst();			
			followUpchcklst.setCaseFollowupId(followUpVO.getFollowUpId());
			followUpchcklst.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			followUpchcklst.setCrtUsr(followUpVO.getUserId());
		}
		
		try {
			if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.FollowUP.FCX"))){
			followUpchcklst.setExeAcqverifyRemark(followUpVO.getExeAcqverifyRemark());
			followUpchcklst.setExeAcqvrifyChklst(followUpVO.getExeAcqvrifyChklst());
			followUpchcklst.setExeDisphotoChklst(followUpVO.getExeDisphotoChklst());
			followUpchcklst.setExeDisphotoremark(followUpVO.getExeDisphotoremark());
			followUpchcklst.setExeMedverifyChklst(followUpVO.getExeMedverifyChklst());
			followUpchcklst.setExeMedVerifyRemark(followUpVO.getExeMedVerifyRemark());
			followUpchcklst.setExePatphotoChklst(followUpVO.getExePatphotoChklst());
			followUpchcklst.setExePatphotoRemark(followUpVO.getExePatphotoRemark());
			followUpchcklst.setExeQuantverifyChklst(followUpVO.getExeQuantverifyChklst());
			followUpchcklst.setExeQuantverifyRemark(followUpVO.getExeQuantverifyRemark());
			followUpchcklst.setExereprtcheckChklst(followUpVO.getExereprtcheckChklst());
			followUpchcklst.setExeReprtcheckRemark(followUpVO.getExeReprtcheckRemark());
			
			}
			if (followUpVO.getRoleId().equalsIgnoreCase(config.getString("EHF.FollowUP.FTD"))){
			followUpchcklst.setFtdBeneficiryChklst(followUpVO.getFtdBeneficiryChklst());
			followUpchcklst.setFtdBeneficiryRemark(followUpVO.getFtdBeneficiryRemark());
			followUpchcklst.setFtdRemarkvrifedChklst(followUpVO.getFtdRemarkvrifedChklst());
			followUpchcklst.setFtdRemarkvrifedRemark(followUpVO.getFtdRemarkvrifedRemark());
			
			}			
			genericDao.save(followUpchcklst);
		} catch (Exception e) {
			logger.error("Error occured in saveCexCheckList() in FollowUpDaoimpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public String getNextStatus(String pStrCaseStatus, String pStrUserRole,
			String lStrActionDone) {
		String lStrNextStatus = null;
		try {

			EhfmWorkflowStatus ehfmWorkflowStatus = null;
			ehfmWorkflowStatus = genericDao.findById(
						EhfmWorkflowStatus.class, EhfmWorkflowStatusId.class,
						new EhfmWorkflowStatusId(pStrCaseStatus,
								pStrUserRole, lStrActionDone));
			if (ehfmWorkflowStatus != null)
				lStrNextStatus = ehfmWorkflowStatus.getNextStatus();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured in getNextStatusForCase() in FollowUpDaoimpl class."+e.getMessage());
		}

		return lStrNextStatus;

	}
	@Override
	public FollowUpVO getListCases(FollowUpVO followUpVO) {
		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
		FollowUpVO retCasesSearchVO = new FollowUpVO();
		List<String>  caseStatus = new ArrayList<String>();
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database=config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		  Session session=factory.openSession();
		  int startIndex=Integer.parseInt(followUpVO.getStartIndex());
		  int maxResult = (Integer.parseInt(followUpVO.getRowsPerPage()));
		  String pendingFlag=followUpVO.getPendingFlag();
		  try{
			 if(followUpVO.getShowPage() != null && Integer.parseInt(followUpVO.getShowPage()) > 1 )
			 {
				 startIndex =  Integer.parseInt(followUpVO.getRowsPerPage()) * (Integer.parseInt(followUpVO.getShowPage())-1);
			 }
			 /**
			  * get cases status for approval
			  */
			 for(LabelBean labelBean:followUpVO.getGrpList())
 		    {
 		     String caseSts =  config.getString("followup_caseStatus_"+labelBean.getID());
 		     if(caseSts != null && !caseSts.equalsIgnoreCase(ClaimsConstants.EMPTY))
 		     {
 		     StringTokenizer str = new StringTokenizer(caseSts,"~");
 			  while(str.hasMoreTokens())
 			  {
 				 caseStatus.add(str.nextElement().toString());  
 			  }
 			   
 		     }
 		    }
			 /**
			  * end of getting status for cases for approval
			  */
			  query.append(" select distinct ac.caseNo as caseNo ,ac.caseId as caseId,ecfc.caseFollowupId as followUpId,  ap.name as patName ,ac.pendingFlag as pendingFlag,  acb.cmbDtlName as followUpStatus ,  ap.cardNo as wapNo  ");
			  query.append(" ,ecfc.crtDt as followUpSubDt , ecfc.claimAmount||'' as claimAmt,ecfc.viewFlag as viewFlag  "); 
			  query1.append(" from EhfmCmbDtls acb,  EhfmHospitals ah ");
			 
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
				  query1.append(",    EhfmMedcoDetails anu ");
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				  query1.append(",    EhfmHospMithraDtls anu "); 
			 
            	  query1.append(" ,EhfCase ac,  EhfPatient ap , EhfClinicalFlpupMpg acdm,EhfCaseFollowupClaim ecfc ");
            	  query1.append("  where ap.patientId = ac.casePatientNo   and acb.cmbDtlId = ecfc.followUpStatus  and ah.hospId = ac.caseHospCode and acdm.caseId=ac.caseId and ecfc.caseFollowupId=acdm.id.followUpId ");
			 			  			  			 
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && (followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
			    query1.append("  and anu.id.userId = '"+followUpVO.getUserId()+"' and anu.id.hospId = ac.caseHospCode and anu.effEndDt is null and (ac.discStatus is null or ac.discStatus = 'CD485')"); 
			  
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				  query1.append(" and anu.id.mithraId = '"+followUpVO.getUserId()+"' and anu.id.hospId = ac.caseHospCode and anu.endDt is null and (ac.discStatus is null or ac.discStatus = 'CD485')");
			  
			  /**
				 * cases for approval appending case status to query
				 */
				  
				  if(followUpVO.getCasesForApprovalFlag() != null && followUpVO.getCasesForApprovalFlag().equalsIgnoreCase("Y") && !("Y").equalsIgnoreCase(pendingFlag))
					 {
					  if(caseStatus != null && caseStatus.size() >0)
						  query1.append(" and ecfc.followUpStatus in ( ");  
					 for(int k=0; k<caseStatus.size() ;k++)
					 {	
					   if(k!=0 && k!=caseStatus.size())
					     {
						   query1.append(" , ");  
					     }
					   query1.append(" '"+caseStatus.get(k)+"' ");	
					 }
					  if(caseStatus != null && caseStatus.size() >0)
					   query1.append(" ) ");  
					  else
						 query1.append(" and ecfc.followUpStatus in ('') ");  	 
					 }
			  
			  if(followUpVO.getSearchFlag() != null && followUpVO.getSearchFlag().equalsIgnoreCase("Y"))
			 {
				  if(followUpVO.getFollowUpStatus() != null && !followUpVO.getFollowUpStatus().equals(""))
				  {
					  query1.append("  and ecfc.followUpStatus = '"+followUpVO.getFollowUpStatus()+"' ");
				  }
				 if(followUpVO.getCaseNo() != null && !followUpVO.getCaseNo().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(ac.caseNo) like  upper('%"+followUpVO.getCaseNo().trim()+"%') "); 
				  }
				 if(followUpVO.getFollowUpId() != null && !followUpVO.getFollowUpId().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(acdm.id.followUpId) like  upper('%"+followUpVO.getFollowUpId().trim()+"%') "); 
				  }
				 if(followUpVO.getWapNo() != null && !followUpVO.getWapNo().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(ap.cardNo) like  upper('%"+followUpVO.getWapNo().trim()+"%') "); 
				  }
				 if(followUpVO.getPatName() != null && !followUpVO.getPatName().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(ap.name) like  upper('%"+followUpVO.getPatName().trim()+"%') "); 
				  }
				 
				  if(followUpVO.getFromDate()!=null && !followUpVO.getFromDate().equals(ClaimsConstants.EMPTY) && followUpVO.getToDate()!=null && !followUpVO.getToDate().equals(ClaimsConstants.EMPTY))
					{ 
						fromDate=followUpVO.getFromDate();
						sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
						toDate=followUpVO.getToDate().toString();
						
						//To include todate in search criteria--adding date+1 
						Calendar cal = Calendar.getInstance();  
			        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
			        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
			        	Date tomorrow = cal.getTime();  
			        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
			        	 //End of date+1 
						 
						sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
						
						if(database.equalsIgnoreCase("ORACLE"))
							query1.append("and ecfc.crtDt between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
						else if(database.equalsIgnoreCase("MYSQL"))
							query1.append("and ecfc.crtDt between '"+sqlFromDate+"' and '"+sqlToDate+"'");
					}
			 }
			  
			  /*added by venkatesh for pending cases CEO*/
			  if(pendingFlag!=null && ("Y").equalsIgnoreCase(pendingFlag))
			  {
				  
				  String[] claimStatus=null;
				  claimStatus =  ClaimsConstants.followupCEOSentBackStatuses;
					
				  query1.append(" and ecfc.followUpStatus in ( ");
				  for (int i = 0; i < claimStatus.length; i++) {
						query1.append(" '" + claimStatus[i] + "' ");
						if (i != claimStatus.length - 1)
							query1.append(",");
				  }
						query1.append(" ) ");
				  
				  
				  query1.append("   and ecfc.pendingWith in ('"+followUpVO.getUserId()+"') ");  
			 
			  }
			  
			  
			  /*added for separating cases for JHS and EHS*/
			  if(followUpVO.getPatientScheme()!=null && !followUpVO.getPatientScheme().equalsIgnoreCase(""))
				{
					query1.append(" and ac.patientScheme in ('"
								+ followUpVO.getPatientScheme() + "') ");
				}
			  /*end of condition for JHS and EHS*/  
			  
			  if(followUpVO.getSchemeType()!=null && !followUpVO.getSchemeType().equalsIgnoreCase("")){
				  if(followUpVO.getSchemeType().equalsIgnoreCase("CD203"))
				  query1.append(" and ecfc.schemeId in ('CD201','CD202') ");
				  else 
				  query1.append(" and ecfc.schemeId in ('"+followUpVO.getSchemeType()+"') ");
			  }
			 query1.append(" order by ecfc.crtDt asc "); 
			  query.append(query1);
	       
			  lstCases=session.createQuery(query.toString()).setFirstResult(startIndex).setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(FollowUpVO.class)).list();
	        
			  query = new StringBuffer();
	         query.append( " select count(distinct ecfc.caseFollowupId) ");
	         query.append(query1);
	         
	         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
	         System.out.println(count);
	         

	         retCasesSearchVO.setTotRowCount(Long.toString(count));
		  }catch(Exception e)
		  {
			e.printStackTrace();  
		  }
		  finally{
				session.close();
			}
		  retCasesSearchVO.setLstCaseSearch(lstCases);
		  retCasesSearchVO.setStartIndex(Integer.toString(startIndex));
		  retCasesSearchVO.setShowPage(followUpVO.getShowPage());
		
		return retCasesSearchVO;
	}
	/*
	 * This method is used to get the Approval Cases for follopwUp without Pagination
	 */
	@Override
	public List<CasesSearchVO> getAllListCases(FollowUpVO followUpVO)
	{
		List<CasesSearchVO> lstCases = new ArrayList<CasesSearchVO>();	
		//FollowUpVO retCasesSearchVO = new FollowUpVO();
		List<String>  caseStatus = new ArrayList<String>();
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		/*String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database=config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");*/
		 SessionFactory factory= hibernateTemplate.getSessionFactory();
		  Session session=factory.openSession();
		  try{
			 /**
			  * get cases status for approval
			  */
			 for(LabelBean labelBean:followUpVO.getGrpList())
 		    {
 		     String caseSts =  config.getString("followup_caseStatus_"+labelBean.getID());
 		     if(caseSts != null && !caseSts.equalsIgnoreCase(ClaimsConstants.EMPTY))
 		     {
 		     StringTokenizer str = new StringTokenizer(caseSts,"~");
 			  while(str.hasMoreTokens())
 			  {
 				 caseStatus.add(str.nextElement().toString());  
 			  }
 			   
 		     }
 		    }
			 /**
			  * end of getting status for cases for approval
			  */
			  query.append(" select distinct ac.caseNo as caseNo ,ac.caseId as caseId,ecfc.caseFollowupId as followUpId,  ap.name as patName ,  acb.cmbDtlName as followUpStatus ,  ap.cardNo as wapNo  ");
			  query.append(" ,ecfc.crtDt as followUpSubDt , ecfc.claimAmount||'' as claimAmt1,ecfc.viewFlag as viewFlag  "); 
			  query1.append(" from EhfmCmbDtls acb,  EhfmHospitals ah ");
			 
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role")))
				  query1.append(",    EhfmMedcoDetails anu ");
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				  query1.append(",    EhfmHospMithraDtls anu "); 
			 
            	  query1.append(" ,EhfCase ac,  EhfPatient ap , EhfClinicalFlpupMpg acdm,EhfCaseFollowupClaim ecfc ");
            	  query1.append("  where ap.patientId = ac.casePatientNo   and acb.cmbDtlId = ecfc.followUpStatus  and ah.hospId = ac.caseHospCode and acdm.caseId=ac.caseId and ecfc.caseFollowupId=acdm.id.followUpId ");
			 			  			  			 
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && (followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_medco_role"))))
			    query1.append("  and anu.id.userId = '"+followUpVO.getUserId()+"' and anu.id.hospId = ac.caseHospCode and anu.effEndDt is null and (ac.discStatus is null or ac.discStatus = 'CD485')"); 
			  
			  if(followUpVO.getUserRole()!=null && !followUpVO.getUserRole().equalsIgnoreCase(ClaimsConstants.EMPTY) && followUpVO.getUserRole().equalsIgnoreCase(config.getString("preauth_mithra_role")))
				  query1.append(" and anu.id.mithraId = '"+followUpVO.getUserId()+"' and anu.id.hospId = ac.caseHospCode and anu.endDt is null and (ac.discStatus is null or ac.discStatus = 'CD485')");
			  
			  /**
				 * cases for approval appending case status to query
				 */
				  
				  if(followUpVO.getCasesForApprovalFlag() != null && followUpVO.getCasesForApprovalFlag().equalsIgnoreCase("Y"))
					 {
					  if(caseStatus != null && caseStatus.size() >0)
						  query1.append(" and ecfc.followUpStatus in ( ");  
					 for(int k=0; k<caseStatus.size() ;k++)
					 {
					   if(k!=0 && k!=caseStatus.size())
					     {
						   query1.append(" , ");  
					     }
					   query1.append(" '"+caseStatus.get(k)+"' ");	
					 }
					  if(caseStatus != null && caseStatus.size() >0)
					   query1.append(" ) ");  
					  else
						 query1.append(" and ecfc.followUpStatus in ('') ");  	 
					 }
			  
			  /*if(followUpVO.getSearchFlag() != null && followUpVO.getSearchFlag().equalsIgnoreCase("Y"))
			 {
				  if(followUpVO.getFollowUpStatus() != null && !followUpVO.getFollowUpStatus().equals(""))
				  {
					  query1.append("  and ecfc.followUpStatus = '"+followUpVO.getFollowUpStatus()+"' ");
				  }
				 if(followUpVO.getCaseNo() != null && !followUpVO.getCaseNo().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(ac.caseNo) like  upper('%"+followUpVO.getCaseNo().trim()+"%') "); 
				  }
				 if(followUpVO.getFollowUpId() != null && !followUpVO.getFollowUpId().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(acdm.id.followUpId) like  upper('%"+followUpVO.getFollowUpId().trim()+"%') "); 
				  }
				 if(followUpVO.getWapNo() != null && !followUpVO.getWapNo().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(ap.cardNo) like  upper('%"+followUpVO.getWapNo().trim()+"%') "); 
				  }
				 if(followUpVO.getPatName() != null && !followUpVO.getPatName().equals(ClaimsConstants.EMPTY))
				  {
					 query1.append("  and upper(ap.name) like  upper('%"+followUpVO.getPatName().trim()+"%') "); 
				  }
				 
				  if(followUpVO.getFromDate()!=null && !followUpVO.getFromDate().equals(ClaimsConstants.EMPTY) && followUpVO.getToDate()!=null && !followUpVO.getToDate().equals(ClaimsConstants.EMPTY))
					{ 
						fromDate=followUpVO.getFromDate();
						sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
						toDate=followUpVO.getToDate().toString();
						
						//To include todate in search criteria--adding date+1 
						Calendar cal = Calendar.getInstance();  
			        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
			        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
			        	Date tomorrow = cal.getTime();  
			        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
			        	 //End of date+1 
						 
						sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
						
						if(database.equalsIgnoreCase("ORACLE"))
							query1.append("and ecfc.crtDt between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
						else if(database.equalsIgnoreCase("MYSQL"))
							query1.append("and ecfc.crtDt between '"+sqlFromDate+"' and '"+sqlToDate+"'");
					}
			 }*/
				  
					/*added for separating cases for JHS and EHS*/
				  if(followUpVO.getPatientScheme()!=null && !followUpVO.getPatientScheme().equalsIgnoreCase(""))
					{
						query1.append(" and ac.patientScheme in ('"
									+ followUpVO.getPatientScheme() + "') ");
					}
				  /*end of condition for JHS and EHS*/  
				  
				  
			  if(followUpVO.getSchemeType()!=null && !followUpVO.getSchemeType().equalsIgnoreCase("")){
				  if(followUpVO.getSchemeType().equalsIgnoreCase("CD203"))
				  query1.append(" and ecfc.schemeId in ('CD201','CD202') ");
				  else 
				  query1.append(" and ecfc.schemeId in ('"+followUpVO.getSchemeType()+"') ");
			  }
			 query1.append(" order by ecfc.crtDt asc "); 
			  query.append(query1);
	       
			  lstCases=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(CasesSearchVO.class)).list();
	        
			  query = new StringBuffer();
	         query.append( " select count(distinct ecfc.caseFollowupId) ");
	         query.append(query1);
	         
	         Long count = (Long) session.createQuery(query.toString()).uniqueResult();
	         System.out.println(count);
	         

	         //retCasesSearchVO.setTotRowCount(Long.toString(count));
		  }catch(Exception e)
		  {
			e.printStackTrace();  
		  }
		  finally{
				session.close();
				factory.close();
			}
		  //retCasesSearchVO.setLstCaseSearch(lstCases);
		  //retCasesSearchVO.setShowPage(followUpVO.getShowPage());
		
		return lstCases;
	}
	@Override
	public List<CommonDtlsVO> getPatientCommonDtls(String lStrCaseId,String lStrFollowUpId) {
		StringBuffer queryBuff = new StringBuffer();		
		List<CommonDtlsVO> resLst = null;
		try
		{
            queryBuff.append(" select distinct p.name as PATIENTNAME , p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , p.crtDt as date ");
			queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT,ec.schemeId as scheme ,ec.patientScheme as patientScheme");
			queryBuff.append(" ,ec.caseId  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID ,ec.crtDt as date , p.cardType as cardType ");
			queryBuff.append(" ,to_char(ec.csDisDt,'dd/mm/yyyy') as csDisDt,to_char(ec.csSurgDt,'dd/mm/yyyy') as csSurgDt,to_char(ec.csAdmDt,'dd/mm/yyyy') as csAdmDt ");
			queryBuff.append("  ,hm.hospName||''||hm.hospDispCode as HOSPNAME , p.gender as GENDER , a.cmbDtlName as STATUS ,ec.caseStatus as CASESTAT   ");
			queryBuff.append(" , acdm.id.followUpId as IPNO , cm.catName as DISNAME, sm.subCatName as SURGNAME,p.patientIpop as patientIpop ");
			queryBuff.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm,EhfClinicalFlpupMpg acdm,EhfCaseFollowupClaim ecfc ");
			queryBuff.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph , EhfmDiagCategoryMst cm , EhfmDiagSubCatMst sm  ");
			queryBuff.append(" where p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and ecfc.caseFollowupId=acdm.id.followUpId and ");
			queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId  and ec.caseId = '"+lStrCaseId+"' ");
			queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ecfc.followUpStatus  and acdm.caseId=ec.caseId and acdm.id.followUpId= '"+lStrFollowUpId+"' ");
			queryBuff.append(" and p.patientId =ph.patientId and ph.categoryCode = cm.id.catCode and ph.mainCatCode = cm.id.mainCatCode ");
			queryBuff.append(" and sm.id.catCode =ph.categoryCode and sm.id.subCatCode = ph.subCatCode ");
			resLst = genericDao.executeHQLQueryList(CommonDtlsVO.class, queryBuff.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resLst;		
	}
	@Override
	public List<FollowUpVO> getFollowUpList(String lStrCaseId,String lStrFollowUpId) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");
		List<FollowUpVO> followUpLst = null;
		List<FollowUpVO> followUpLstTemp = null;
		List<FollowUpVO> casePackLst = null;
		List<FollowUpVO> listFolowFinal = new ArrayList<FollowUpVO>();
		StringBuffer queryBuff = new StringBuffer();
		StringBuffer queryBuff1 = new StringBuffer();
		String[] args = new String[1];
		args[0] = lStrCaseId;
		Long totalPackAmt= 0L;
		Long fistInstAmt = 0L;
		Long nextInstAmt= 0L;
		try 
		{
			//finding all followUp associated with caseId
			queryBuff.append(" select ecfc.caseFollowupId as followUpId, ecfc.crtDt as followUpSubDt,NVL(ecfc.claimAmount,0)||'' as claimAmt,NVL(ecfc.claimNwhAmount,0)||'' as claimNwhAmt,ecfc.followUpStatus as followUpStatus,ecfc.caseId as caseId");
			queryBuff.append(",NVL(ecfc.carryFwdAmt,0) as claimFwdAmt,to_char(ecfc.actualPack) as actualAmt ");
			//queryBuff.append(" cast(ac.csDisDt as string) as csDisDt,cast(ac.csSurgDt as string) ascsSurgDt,cast(ac.csAdmDt as string) as csAdmDt ");
			queryBuff.append(" from EhfCase ac,EhfCaseFollowupClaim ecfc where ac.caseId=ecfc.caseId  and ac.caseId=? order by ecfc.caseFollowupId asc");
			followUpLst = genericDao.executeHQLQueryList(FollowUpVO.class, queryBuff.toString(),args);
			//findind the packege amount for a caseId
			queryBuff1.append(" select ac.schemeId as schemeType from EhfCase ac where case_id='"+lStrCaseId+"'");
			followUpLstTemp = genericDao.executeHQLQueryList(FollowUpVO.class, queryBuff1.toString());
			String scheme=followUpLstTemp.get(0).getSchemeType();
			
			queryBuff = new StringBuffer();
			queryBuff.append(" SELECT SUM(AFP2.packageAmt) as totalPackAmt,SUM(AFP2.firstInstPkg)  as  fistInstAmt, ");
			queryBuff.append(" SUM(AFP2.subsequentInstPkg)  as  nextInstAmt FROM EhfCaseTherapy ACS, EhfmFollowupPackages AFP2,EhfCase EC ");
			if(scheme.equalsIgnoreCase("1"))
				{
					queryBuff.append(" ,EhfPatient ep,EhfEnrollment ee,EhfEnrollmentFamily  eef ");
				}
			queryBuff.append(" WHERE ACS.caseId = ? ");
			queryBuff.append(" AND ACS.icdProcCode IN (SELECT AFP.id.surgeryId FROM EhfmFollowupPackages AFP) AND ACS.icdProcCode = AFP2.id.surgeryId ");
			if(scheme.equalsIgnoreCase("1"))
				{
					queryBuff.append(" and EC.caseId=ACS.caseId and ep.patientId = EC.casePatientNo and ep.cardNo = eef.ehfCardNo and ee.enrollPrntId = eef.enrollPrntId and ee.scheme = AFP2.id.schemeId");
				}
			else
				{
					queryBuff.append(" and AFP2.id.schemeId=EC.schemeId and EC.caseId=ACS.caseId");
				}
			
			queryBuff.append(" GROUP BY ACS.caseId ");
			
			casePackLst = genericDao.executeHQLQueryList(FollowUpVO.class, queryBuff.toString(),args);
			
			if(casePackLst!=null && casePackLst.size()>0){
				totalPackAmt = casePackLst.get(0).getTotalPackAmt();
				fistInstAmt = casePackLst.get(0).getFistInstAmt();
				nextInstAmt = casePackLst.get(0).getNextInstAmt();
			}	
			Boolean firstTimer =true;
			Long pevCaryFwd = 0L;
			Long pevBalAvail = 0L;
			Long totalClaimAmt = 0L;
			Long totalClaimNwhAmt = 0L;
			for(FollowUpVO followUpfinal : followUpLst){

				//Long claimFwd = 0L;
				Long balAvail = 0L;
				FollowUpVO followUpvo = new FollowUpVO();
				String followUpId=followUpfinal.getFollowUpId();
				followUpvo.setFollowUpId(followUpfinal.getFollowUpId());
				followUpvo.setFollowUpSubDt(sdf.parse(followUpfinal.getFollowUpSubDt()));
				followUpvo.setClaimAmt(followUpfinal.getClaimAmt());
				followUpvo.setClaimNwhAmt(followUpfinal.getClaimNwhAmt());
				followUpvo.setCaseId(followUpfinal.getCaseId());
				if(followUpId!=null)
					{
						StringBuffer sb=new StringBuffer();
						sb.append(" select to_char(ecfm.nxtFollowupDt,'dd/MM/yyyy') as nxtFollowUpDt ");
						sb.append(" from EhfClinicalFlpupMpg ecfm where ecfm.id.followUpId='"+followUpId+"' ");
						sb.append(" order by crtDt");
						List<FollowUpVO> followUpVOLst=new ArrayList<FollowUpVO>();
						followUpVOLst=genericDao.executeHQLQueryList(FollowUpVO.class, sb.toString());
						if(followUpVOLst!=null)
							{
								if(followUpVOLst.size()>0)
									{
										if(followUpVOLst.get(0).getNxtFollowUpDt()!=null)
											followUpvo.setNxtFollowUpDt(followUpVOLst.get(0).getNxtFollowUpDt());
										else
											followUpvo.setNxtFollowUpDt("NA");
									}
							}
					}
				totalClaimNwhAmt=totalClaimNwhAmt+Long.parseLong(followUpfinal.getClaimNwhAmt());
				totalClaimAmt=totalClaimAmt+Long.parseLong(followUpfinal.getClaimAmt());
				if(followUpId.contains("/1")){
					firstTimer = false;
					followUpvo.setActualAmt(fistInstAmt.toString());
					if(!followUpfinal.getClaimNwhAmt().equalsIgnoreCase("0")){
						//claimFwd = fistInstAmt-Long.parseLong(followUpfinal.getClaimAmt());
						//pevCaryFwd = claimFwd;
						pevCaryFwd =(long) followUpfinal.getClaimFwdAmt();
						//followUpvo.setClaimFwd(claimFwd.toString());
						int clmFwdAmt= (int) followUpfinal.getClaimFwdAmt();
						followUpvo.setClaimFwd(Integer.toString(clmFwdAmt));
						balAvail = totalPackAmt - Long.parseLong(followUpfinal.getClaimNwhAmt());
						pevBalAvail = balAvail;
						followUpvo.setBalAvail(balAvail.toString());						
					}
					else{
						if(!followUpfinal.getFollowUpStatus().equalsIgnoreCase(config.getString("EHF.FollowUP.HeadReject"))){
						followUpvo.setClaimAmt("--");
						followUpvo.setClaimFwd("--");
						balAvail = totalPackAmt;
						pevBalAvail = balAvail;
						followUpvo.setBalAvail(balAvail.toString());
						}
						/*else{
							followUpvo.setClaimAmt("0");
							followUpvo.setClaimFwd(fistInstAmt.toString());
							pevCaryFwd=fistInstAmt;
							balAvail = totalPackAmt;
							pevBalAvail = balAvail;
							followUpvo.setBalAvail(balAvail.toString());
						}*/
					}
				}
				else
				{
					followUpvo.setActualAmt(nextInstAmt.toString());
						if(!followUpfinal.getClaimNwhAmt().equalsIgnoreCase("0"))
						{
						
							pevCaryFwd =(long) followUpfinal.getClaimFwdAmt();
							
							int clmFwdAmt= (int) followUpfinal.getClaimFwdAmt();
							followUpvo.setClaimFwd(Integer.toString(clmFwdAmt));
							
						}
						if(followUpId.contains("/2") && firstTimer)
							{
							firstTimer = false;
								followUpvo.setClaimFwdOld(fistInstAmt+"");
									System.out.println("Actual : "+Long.parseLong(followUpvo.getActualAmt())+" next1 : "+nextInstAmt+" next2 : "+nextInstAmt+" claimAmt : "+Long.parseLong(followUpfinal.getClaimNwhAmt()));
									balAvail = Long.parseLong(followUpvo.getActualAmt())+nextInstAmt+nextInstAmt-Long.parseLong(followUpfinal.getClaimNwhAmt());
									pevBalAvail = balAvail;
									followUpvo.setBalAvail(balAvail.toString());
								
							}
						else if(followUpId.contains("/2") && !firstTimer)
						{
								balAvail = pevBalAvail - Long.parseLong(followUpfinal.getClaimNwhAmt());
								pevBalAvail = balAvail;
								followUpvo.setBalAvail(balAvail.toString());
							
						}
						else if(followUpId.contains("/3") && firstTimer)
						{
							firstTimer = false;
								followUpvo.setClaimFwdOld((fistInstAmt+nextInstAmt)+"");
								balAvail = Long.parseLong(followUpvo.getActualAmt())+nextInstAmt - Long.parseLong(followUpfinal.getClaimNwhAmt());
								pevBalAvail = balAvail;
								followUpvo.setBalAvail(balAvail.toString());
							
						}
						else if(followUpId.contains("/3") && !firstTimer)
						{
								Long totalTestBal=0L;
								Long totalClaimBal=0L;
								for(int j=0;j<followUpLst.size();j++)
								{
									if(followUpLst.get(j).getFollowUpId().contains("/1"))
									{
										System.out.println(followUpLst.get(j).getBalAvail());
										if(followUpLst.get(j).getBalAvail()!=null)
										totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
										else
											totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt());
										
										totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
										}
									if(followUpLst.get(j).getFollowUpId().contains("/2"))
									{
										System.out.println(followUpLst.get(j).getBalAvail());
										if(followUpLst.get(j).getBalAvail()!=null)
										totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
										else
											totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt());
											
										totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
									}
									if(followUpLst.get(j).getFollowUpId().contains("/3"))
									{
										System.out.println(followUpLst.get(j).getBalAvail());
										if(followUpLst.get(j).getBalAvail()!=null)
										totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
										else
											totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt())+Long.parseLong(followUpLst.get(j).getActualAmt());
											
										totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
									}
									
								}
							
								balAvail = totalTestBal-totalClaimBal;
								pevBalAvail = balAvail;
								followUpvo.setBalAvail(balAvail.toString());
							
						}
						else if(followUpId.contains("/4") && firstTimer)
						{
							firstTimer = false;
								balAvail = Long.parseLong(followUpvo.getActualAmt()) - Long.parseLong(followUpfinal.getClaimNwhAmt());
								pevBalAvail = balAvail;
								followUpvo.setBalAvail(balAvail.toString());
							
						}
						else if(followUpId.contains("/4") && !firstTimer)
						{
							
							Long totalTestBal=0L;
							Long totalClaimBal=0L;
							for(int j=0;j<followUpLst.size();j++)
							{
								if(followUpLst.get(j).getFollowUpId().contains("/1"))
								{
									System.out.println(followUpLst.get(j).getBalAvail());
									if(followUpLst.get(j).getBalAvail()!=null)
									totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
									else
										totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt());
									
									totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
									}
								if(followUpLst.get(j).getFollowUpId().contains("/2"))
								{
									System.out.println(followUpLst.get(j).getBalAvail());
									if(followUpLst.get(j).getBalAvail()!=null)
									totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
									else
										totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt());
										
									totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
								}
								if(followUpLst.get(j).getFollowUpId().contains("/3"))
								{
									System.out.println(followUpLst.get(j).getBalAvail());
									if(followUpLst.get(j).getBalAvail()!=null)
									totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
									else
										totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt());
										
									totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
								}
								if(followUpLst.get(j).getFollowUpId().contains("/4"))
								{
									System.out.println(followUpLst.get(j).getBalAvail());
									if(followUpLst.get(j).getBalAvail()!=null)
									totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
									else
										totalTestBal=totalTestBal+Long.parseLong(followUpLst.get(j).getActualAmt());
										
									totalClaimBal=totalClaimBal+Long.parseLong(followUpLst.get(j).getClaimNwhAmt());
								}
								
							}
						
							balAvail = totalTestBal-totalClaimBal;
							pevBalAvail = balAvail;
							followUpvo.setBalAvail(balAvail.toString());
							
								/*Long totalTestBal=0L;
								for(int j=0;j<followUpLst.size();j++)
								{
									if(followUpLst.get(j).getFollowUpId().contains("/1"))
									{
										System.out.println(followUpLst.get(j).getBalAvail());
										totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
												
									}
									if(followUpLst.get(j).getFollowUpId().contains("/2"))
									{
										System.out.println(followUpLst.get(j).getBalAvail());
										totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
												
									}
									if(followUpLst.get(j).getFollowUpId().contains("/3"))
									{
										System.out.println(followUpLst.get(j).getBalAvail());
										totalTestBal=Long.parseLong(followUpLst.get(j).getBalAvail());
												
									}
								}
								balAvail = totalTestBal-Long.parseLong(followUpfinal.getClaimNwhAmt());
								pevBalAvail = balAvail;
								followUpvo.setBalAvail(balAvail.toString());*/
								
							}
							
					}
				listFolowFinal.add(followUpvo);
				}
				
			
			FollowUpVO totalFollowVo = new FollowUpVO();
			totalFollowVo.setFollowUpId("Total");
			totalFollowVo.setActualAmt(totalPackAmt.toString());
			totalFollowVo.setClaimAmt(totalClaimAmt.toString());
			totalFollowVo.setClaimNwhAmt(totalClaimNwhAmt.toString());
			listFolowFinal.add(totalFollowVo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listFolowFinal;
	}
	@Override
	public String checkValidForFollowup(String lStrCaseId) {
		
		String msg=ClaimsConstants.EMPTY;
		long msDiff;
	    final long MILLIS_PER_DAY = 24 * 3600 * 1000;
		try{
			StringBuffer lQueryBuffer=new StringBuffer();        
	        lQueryBuffer.append(" select au.crtDt as crtDt from EhfClinicalFlpupMpg au where au.caseId='"+lStrCaseId+"' order by au.crtDt Desc");
	        List<LabelBean> follwUpList=genericDao.executeHQLQueryList(LabelBean.class,lQueryBuffer.toString());
	        if(follwUpList!=null)
	        {
	        	if(follwUpList.size()>=4)
	        	msg="Already 4 FollowUp has been done for this case";
	        	else{
	        		if(follwUpList.size()>0 && follwUpList.get(0).getCrtDt()!=null){
	        		msDiff= new Date().getTime() - follwUpList.get(0).getCrtDt().getTime();
	        		long daysDiff = Math.round(msDiff / ((double)MILLIS_PER_DAY));
	        		if(daysDiff<90)
	        			msg="Follow-Up Cannot initiate.Please wait for 90 days from previous followUp date.";
	        		}
	        	}
	        }
	        	       
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return msg;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap updateFollowupPayments(HashMap lParamMap) throws Exception
	  {
        String lResult = ClaimsConstants.EMPTY;
        ArrayList lCaseIdLst = new ArrayList();
        ArrayList lContentList = new ArrayList();
        List lFileList = new ArrayList();
        HashMap lFileMap = new HashMap();
        HashMap lContentMap = null;
        String lStrNextStatus = null;
      
        int i = ClaimsConstants.ZERO_VAL, lFlag = ClaimsConstants.ZERO_VAL, iUploadStatus = ClaimsConstants.ZERO_VAL;
        ArrayList resultList = new ArrayList();
        GenerateAsciiFile gaf = new GenerateAsciiFile();
        ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lParamMap.get("claimFlowVO");
        boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
        try
        {
        	lStrNextStatus =
   			 getNextStatus((String)lParamMap.get("caseStatus"),
   			    claimFlowVO.getRoleId(), claimFlowVO.getActionDone());

        	lParamMap.put("nextCaseStatus",lStrNextStatus);
   			claimFlowVO.setCurrStatus((String) lParamMap.get("caseStatus"));
   			claimFlowVO.setNextStatus((String) lParamMap.get("nextCaseStatus"));
   			
   			lParamMap.put("currentDate",new java.sql.Timestamp(new Date().getTime()));
   			
          
          boolean flupPayResult = updateFollowupPaymentCheckValue(lParamMap);
          if (flupPayResult)
          {

            lContentList = getFollowupPaymentDtls(lParamMap);
            if (lContentList.size() > ClaimsConstants.ZERO_VAL)
            {
              
            	//Inserting into Payments Table
            	lFileMap=claimsFlowDAO.insertIntoPaymentTable(lContentList, lParamMap);		
            
              // lFileMap = gaf.generateAsciiFile(lContentList, lParamMap);
             
              lFlag = Integer.parseInt((String) lFileMap.get("Flag"));

              if (lContentList.size() > 2)
              {
                lFileList = (ArrayList) lContentList.get(ClaimsConstants.ZERO_VAL);
                lCaseIdLst = (ArrayList) lContentList.get(2);
              }

            }
            //071
            if (lFlag > ClaimsConstants.ZERO_VAL)
            {
             
            	int CaseIdLst = lCaseIdLst.size();
              //071
              String lStrCaseId = null;
              for (int j = 0; j < CaseIdLst; j++) 
              {
            	  i = 0;
            	  //lParamMap=new HashMap();
                lParamMap.put("CASE_FOLLOWUP_ID", (String) lCaseIdLst.get(j));
                lParamMap.put("CASE_ID", (String) lCaseIdLst.get(j));
                //HashMap Contains the Beneficiary Account Information
                lContentMap = new HashMap();
                lContentMap = (HashMap) lFileList.get(j);
                if ((String) lContentMap.get("BENEFICIARY_ACCOUNT_NO") != null)
                {
                  lParamMap.put("SRC_ACCT_NO", (String) lContentMap.get("CLAINT_AC_NUMBER"));
                  lParamMap.put("DES_ACCT_NO", (String) lContentMap.get("BENEFICIARY_ACCOUNT_NO"));
                  lParamMap.put("nextCaseStatus",lStrNextStatus);
                  lParamMap.put("TRANSACTION_AMOUNT", (String) lContentMap
							.get("TRANSACTION_AMOUNT"));
                  lParamMap.put("ACC_TRANSACTION_AMOUNT", (String) lContentMap
							.get("TRANSACTION_AMOUNT"));
                  lParamMap.put("ACC_TOTAL_AMOUNT", (String) lContentMap
							.get("TOTAL_CLAIM_AMOUNT"));
                  lParamMap.put("ACC_REST_AMOUNT", (String) lContentMap
							.get("REST_AMOUNT"));
                  lParamMap.put("ACC_HOSP_ID", (String) lContentMap
							.get("HOSP_ID"));
                  lParamMap.put("ACC_HOSP_TYPE", (String) lContentMap
							.get("HOSP_TYPE"));
                  lParamMap.put("FILENAME",(String)lFileMap.get("FILENAME"));
                  lParamMap.put("TDS_RF_TYPE", (String)lContentMap
							.get("TDS_RF_TYPE"));
                  lParamMap.put("REJECTED_PAYMENT", (String)lContentMap
							.get("REJECTED_PAYMENT"));
                  
					
                  lStrCaseId = (String) lCaseIdLst.get(j);
                  if (lStrCaseId.endsWith(ClaimsConstants.G) || lStrCaseId.endsWith(ClaimsConstants.TDS))
                  { // 098
                    isInsert = updateFlupTdsAccountDetails(lParamMap); //if the payment is related to TDS or Revolving fund
                  }
                  else
                  { // 098
                    isInsert = ChangeFollowupClaimStatus(lParamMap); // for normal followup payment
                    if (isInsert)
                    {
                    
                    }
                  }
                }

              } // 098

            /*  if (isInsert)
              { // Added
                 resultList = claimsPaymentDao.insertUploadFile(lParamMap); // Added //128
                 iUploadStatus = Integer.parseInt(resultList.get(ClaimsConstants.ZERO_VAL).toString()); //Added
                
              } //Added              
*/
              lResult = ClaimsConstants.ONE;
            }
            else // 098
            {
              lResult = ClaimsConstants.ZERO;
            }
          }
          else // 098
          {
            lResult = ClaimsConstants.ZERO;
          }
          lParamMap.put("Message", lResult);
          lParamMap.put("UploadStatus", Integer.toString(iUploadStatus));//128
          //lParamMap.put("FtpStatus", Integer.toString(lFtpuploadStatus)); //Added  //128
        }
        catch (Exception e)
        {
        	e.printStackTrace();
          logger.error("Exception in method updateFollowupPayments--" + e.getMessage());
        }
        
        return lParamMap;
	  }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList getFollowupPaymentDtls(HashMap lParamMap) {
		List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
		ArrayList lResList = new ArrayList();
		List lFileDataList = new ArrayList();
		List lCaseList = new ArrayList();
		double TrustPctAmt = 0, tdsAmt = 0;
		double lFlagVal = ClaimsConstants.ZERO_DBL_VAL;
		Map lFileMap = new HashMap();
		int lTotalCases = ClaimsConstants.ZERO_VAL;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lParamMap.get("claimFlowVO");
		String lStrCaseStatus = (String) lParamMap.get("nextCaseStatus");
		String lStrOldCaseStatus = (String) lParamMap.get("caseStatus");
		StringBuffer query = new StringBuffer();
		String args[] = new String[claimFlowVO.getCasesReady().length];
		for (int i = 0; i <claimFlowVO.getCasesReady().length; i++) {
			args[i] = claimFlowVO.getCasesReady()[i];
		}
		try {

			query.append(" select distinct c.caseFollowupId as caseId,b.hospCity as hospAdd,e.bankId as bankId,d.bankBranch as bankBranch,d.bankName as bankName,d.ifcCode as ifscCode,d.bankCategory as bankCategory,b.hospType as HOSPTYPE,c.schemeId as schemeType,");
			query.append(" e.accountNo as accNo,e.hospAccName as hospAccName,e.id.hospId as hospId,b.hospActiveYN||'' as hospActiveYN,c.claimAmount||'' as totalClaim,c.trustPctAmt as trustPctAmt,c.tdsPctAmt as tdsPctAmt,c.tdsHospPctAmt as tdsHospPctAmt,c.hospPctAmt as hospPctAmt");
			query.append(" from EhfCase a,EhfmHospitals b,EhfCaseFollowupClaim c,EhfmBankMaster d,EhfmHospBankDtls e, EhfPatient f ");
			query.append(" where  b.hospId = e.id.hospId and d.bankId = e.bankId and e.id.scheme = a.schemeId and  c.caseId=a.caseId and ");
			query.append("  f.patientId = a.casePatientNo and a.caseHospCode = b.hospId and c.caseFollowupId in (");
			for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
				query.append(" ? ");
				if (i == claimFlowVO.getCaseSelected().length - 1)
					query.append(" ) ");
				else
					query.append(" , ");
			}
			lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					query.toString(), args);

			for (ClaimsFlowVO claimsVO : lstWorkList) {

				lFileMap = new HashMap(); // List for generate file for
											// upload(witch is having the all
											// mandatory filed)

				lFileMap.put("UNIQUE_ID", claimsVO.getCaseId());
				lCaseList.add(claimsVO.getCaseId());
				lFileMap.put("BENEFICIARY_ACCOUNT_NAME",
						claimsVO.getHospAccName());
				lFileMap.put("BENEFICIARY_ID", claimsVO.getHospId());
				lFileMap.put("BENEFICIARY_ADDR", claimsVO.getHospAdd());
				lFileMap.put("BENEFICIARY_BANK_NAME", claimsVO.getBankName());
				lFileMap.put("BANK_BRANCH", claimsVO.getBankBranch());
				lFileMap.put("BENEFICIARY_ACCOUNT_NO", claimsVO.getAccNo());
				lFileMap.put("TOTAL_CLAIM_AMOUNT", claimsVO.getTotalClaim());
				lFileMap.put("HOSP_TYPE",claimsVO.getHOSPTYPE()+"");
				lFileMap.put("HOSP_ID", claimsVO.getHospId());
				if(claimsVO.getHospPctAmt()!=null && claimsVO.getHospPctAmt() != 0){
					lFileMap.put("TRANSACTION_AMOUNT",claimsVO.getHospPctAmt().toString());
					lFileMap.put("REST_AMOUNT", claimsVO.getTrustPctAmt()
							.toString());
				}
				else{
					lFileMap.put("TRANSACTION_AMOUNT",claimsVO.getTdsHospPctAmt().toString());
					lFileMap.put("REST_AMOUNT", claimsVO.getTdsPctAmt()
							.toString());
				}
				lFileMap.put("SCHEME_ID", claimsVO.getSchemeType());
				lFileMap.put("BENEFICIARY_BANK_ID", claimsVO.getBankId());
				lFileMap.put("BENEFICIARY_BANK_IFC_CODE",
						claimsVO.getIfscCode());
				
				if(lStrOldCaseStatus.equals(ClaimsConstants.CLAIM_FP_READY_REJ_BANK))
				{
					lFileMap.put("REJECTED_PAYMENT", "true");
				}
				else
				{
					lFileMap.put("REJECTED_PAYMENT", "false");
				}
				
				if(claimsVO.getSchemeType()!=null && claimsVO.getSchemeType().equalsIgnoreCase(config.getString("TG")))
					{
					StringBuffer query1=new StringBuffer();
					query1.append("select a.accountNo as accNo, b.ifcCode as ifscCode from EhfmTrustActDtls a ,EhfmBankMaster b where a.bankId=b.bankId and a.activeYn='Y' and a.actType='"+ClaimsConstants.FPCLAIM_CLIENT_TG_ACTTYPE+"'");
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
					//lFileMap.put("CLAINT_AC_NUMBER",ClaimsConstants.FPCLAIM_CLIENT_TG_ACCNO);
					lFileMap.put("CLAINT_AC_CODE", ClaimsConstants.FPCLAIM_CLIENT_TG_CODE);
					}
					/*else{
					lFileMap.put("CLAINT_AC_NUMBER",
								ClaimsConstants.FPCLAIM_CLIENT_AP_ACCNO);	
					lFileMap.put("CLAINT_AC_CODE", ClaimsConstants.FPCLAIM_CLIENT_AP_CODE);
					}*/
				
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
				
				 if (TrustPctAmt > ClaimsConstants.ZERO_VAL || tdsAmt > ClaimsConstants.ZERO_VAL)
		         { //093
		           lFlagVal = ClaimsConstants.ZERO_VAL;
		           HashMap ldataMap = new HashMap();
		           ldataMap.put("TrustAmount", TrustPctAmt);
		           ldataMap.put("TdsAmount", tdsAmt);
		           lFileMap.put("PaymentType", (String) lParamMap.get("PaymentType"));
		           lFileMap.put("CaseStatus", lStrCaseStatus);
		           //lFileMap.put("PaymentType", "CD545");		
		           lFileMap.put("CaseList", lCaseList);
		           lFileMap.put("FileDataList", lFileDataList);
		           ldataMap.put("FileMap", lFileMap);
		           lFileMap.put("OldCaseStatus", lStrOldCaseStatus);
		           HashMap lresultMap = claimsPaymentDao.payTDSFund(ldataMap); //Seperate method which will take the TDS and Revolving funds and returns a payment hashmap file
		           lCaseList = (ArrayList) lresultMap.get("CaseList");
		           lFileDataList = (ArrayList) lresultMap.get("FileDataList");
		         }
				 lTotalCases++;
			}
			lResList.add(lFileDataList);
			String smsNextVal = getSequence("CLAIMUPLOADFILE");
			
			lResList.add(smsNextVal);
			lResList.add(lCaseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lResList;
	}
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
    @SuppressWarnings("unchecked")
	public boolean updateFollowupPaymentCheckValue(HashMap lParamMap)
    {
        
         boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
         ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lParamMap.get("claimFlowVO");
         String lStrCaseIds=ClaimsConstants.EMPTY,lStrCaseId=ClaimsConstants.EMPTY;
         StringBuffer lStrSQLQuery=null;
         String failedCaseIdInList = ClaimsConstants.EMPTY ;
         boolean failedIsInsert = ClaimsConstants.BOOLEAN_FALSE ;
         int count=0;
 		 int size=claimFlowVO.getCaseSelected().length;
 		 String[] casesReady = new String[size];
         
         try{       
        	 for (String cases : claimFlowVO.getCaseSelected()) {
        		 try
                 {    
                   lStrCaseId = cases;
                   
                   lParamMap.put("CASE_FOLLOWUP_ID",lStrCaseId);
                   lParamMap.put("CASE_ID",lStrCaseId);
                   String lStrStatus = ( String ) lParamMap.get("caseStatus") ;
                   if(!( (ClaimsConstants.CLAIM_FP_READY_REJ_BANK.equalsIgnoreCase(lStrStatus))))//030
                    isInsert=claimsPaymentDao.calculateClaimPercentage(lParamMap);                //calculateClaimPercentage(lParamMap);
                   if(isInsert || ClaimsConstants.CLAIM_FP_READY_REJ_BANK.equalsIgnoreCase(lStrStatus) )
                   {
                       isInsert=ClaimsConstants.BOOLEAN_FALSE;
                      
                       EhfCaseFollowupClaim ehfCaseFPClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class,
       						lStrCaseId);
                       ehfCaseFPClaim.setPaymentCheck(ClaimsConstants.T);
                       ehfCaseFPClaim = genericDao.save(ehfCaseFPClaim);
                       
       				if (ehfCaseFPClaim.getPaymentCheck() != null
       						&& (ClaimsConstants.T).equalsIgnoreCase(ehfCaseFPClaim
       								.getPaymentCheck())) {
       					isInsert = ClaimsConstants.BOOLEAN_TRUE;
       					casesReady[count]=lStrCaseId;
						count++;
       				}
                   }
                   else
                   {
                	   boolean isUpdate=false;
                	   failedCaseIdInList = failedCaseIdInList+lStrCaseId+",";
                	   isUpdate=updateFailedCaseStatus(lStrCaseId);
                		if(!isUpdate)
    					{
    						logger.error("failed to update case status of failed claim with case id : "+lStrCaseId);
    					}
                       System.out.println("failed cases are********{}****"+failedCaseIdInList);
                       lParamMap.put("failedCaseIdInList", failedCaseIdInList);
           			
                   }}
        		 catch (Exception ex){
				       failedIsInsert = ClaimsConstants.BOOLEAN_TRUE;
				       ex.printStackTrace();
                     failedCaseIdInList = failedCaseIdInList+lStrCaseId+",";
                     System.out.println("failed cases are in catch block************"+failedCaseIdInList);
                     lParamMap.put("failedCaseIdInList",failedCaseIdInList);
                     lParamMap.put("failedIsInsert",failedIsInsert);
			}		
             }
           }
          catch(Exception le)
          {
            le.printStackTrace();
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
      return isInsert;     // 098
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean ChangeFollowupClaimStatus(HashMap lParamMap) throws Exception
    {
      
    	logger.info("Start:ChangeFollowupClaimStatus method in FollowUpDaoimpl");
		Long lActOrder = 0L;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		boolean isInsert = false;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lParamMap.get("claimFlowVO");
		EhfCaseFollowupClaim ehfCaseFPClaim = null;
		EhfFollowUpClaimAccounts ehfFPClaimAccounts = null;
		//String lStrClaimStatus = claimFlowVO.getCurrStatus();
		String hosp_id=(String) lParamMap.get("ACC_HOSP_ID");
		String hosp_type=(String) lParamMap.get("ACC_HOSP_TYPE");
		String amount = lParamMap.get("ACC_TRANSACTION_AMOUNT").toString();
		String totalAmt =  lParamMap.get("ACC_TOTAL_AMOUNT").toString();
		String tdsOrRfAmt = (String) lParamMap.get("ACC_REST_AMOUNT");
		String TDSorRfType=lParamMap.get("TDS_RF_TYPE").toString();
		String lStrFPId = (String) lParamMap.get("CASE_ID");
		String rejectedPayment=(String) lParamMap.get("REJECTED_PAYMENT");
		String lStrCaseId = lStrFPId.substring(ClaimsConstants.ZERO_VAL, lStrFPId.indexOf(ClaimsConstants.SLASH));
		
		TransactionVO transactionVO = new TransactionVO();
		//transactionVO.setCaseId(lStrCaseId);
		transactionVO.setCaseId(lStrFPId);
		transactionVO.setTdsRfId(lStrFPId);
		transactionVO.setHospitalId(hosp_id);
		transactionVO.setGrossAmount(totalAmt);
		transactionVO.setNetAmount(amount);
		transactionVO.setTdsOrRfAmount(tdsOrRfAmt);		
		transactionVO.setHospitalType(hosp_type);
		transactionVO.setNarration("FollowUp-Claim");
		transactionVO.setTransactionType(config.getString("ACC.FOLLCLAIMS"));
		transactionVO.setScheme((String) lParamMap.get("SCHEME_ID"));
		transactionVO.setTdsOrRfType(TDSorRfType);
		transactionVO.setLstUpdUsr(claimFlowVO.getUserId());
		
		ehfCaseFPClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class,
				(String) lParamMap.get("CASE_ID"));
		
		ehfCaseFPClaim.setFilename((String) lParamMap.get("FileName"));
		ehfCaseFPClaim.setPaymentStatus((String) lParamMap.get("nextCaseStatus"));
		ehfCaseFPClaim.setPaymentCheck("F");
		ehfCaseFPClaim.setPaymentSentDate(new Timestamp(new Date().getTime()));
		ehfCaseFPClaim.setFollowUpStatus((String) lParamMap.get("nextCaseStatus"));
		ehfCaseFPClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
		ehfCaseFPClaim.setLstUpdUsr(claimFlowVO.getUserId());
		ehfCaseFPClaim = genericDao.save(ehfCaseFPClaim);

		
		/*EhfClinicalFlpupMpg ehfClinicalFlpupMpg = genericDao.findById(EhfClinicalFlpupMpg.class, String.class,
				(String) lParamMap.get("CASE_FOLLOWUP_ID"));
		ehfClinicalFlpupMpg.setFollowUpStatus((String) lParamMap.get("nextCaseStatus"));
		ehfClinicalFlpupMpg.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
		ehfClinicalFlpupMpg.setLstUpdUser(claimFlowVO.getUserId());
		ehfClinicalFlpupMpg = genericDao.save(ehfClinicalFlpupMpg);*/
		
		if (ehfCaseFPClaim.getPaymentSentDate() != null) {
			ehfFPClaimAccounts = genericDao.findById(EhfFollowUpClaimAccounts.class,
					String.class, (String) lParamMap.get("CASE_FOLLOWUP_ID"));

			ehfFPClaimAccounts.setTransStatus(ClaimsConstants.SENT);
			ehfFPClaimAccounts.setTimeMilSec((long) 0);
			ehfFPClaimAccounts
					.setSrcAcctNo((String) lParamMap.get("SRC_ACCT_NO"));
			ehfFPClaimAccounts
					.setDesAcctNo((String) lParamMap.get("DES_ACCT_NO"));
			ehfFPClaimAccounts.setLstUpdDt(new java.sql.Timestamp(new Date()
					.getTime()));
			ehfFPClaimAccounts.setLstUpdUsr(claimFlowVO.getUserId());
			ehfFPClaimAccounts = genericDao.save(ehfFPClaimAccounts);
		}
		
		
		if((ehfCaseFPClaim!=null && ehfCaseFPClaim.getFollowUpStatus()!=null && ehfCaseFPClaim.getFollowUpStatus().equalsIgnoreCase(config.getString("EHF.FollowUP.ClaimSentForPayment")))
				&&  ("N").equalsIgnoreCase((String)lParamMap.get("sentAgain")))
		{
			logger.info("updating payment slab in followup........ ");
			boolean isUpdate=false;
			if(ehfCaseFPClaim!=null && ehfCaseFPClaim.getLstUpdUsr()!=null)
			{
				lParamMap.put("lst_upd_usr",ehfCaseFPClaim.getLstUpdUsr());
			}
			isUpdate=claimsFlowDAO.getPaymentSlab(lParamMap);
			
		}
		
		
		
		
		
		
		if (ehfFPClaimAccounts.getSrcAcctNo() != null) {
			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfFollowUpAudit au where au.id.caseFollowupId=? ");
			args[0] = (String) lParamMap.get("CASE_FOLLOWUP_ID");
			List<FollowUpVO> actOrderList = genericDao.executeHQLQueryList(
					FollowUpVO.class, lQueryBuffer.toString(), args);
			if (actOrderList != null && !actOrderList.isEmpty()
					&& actOrderList.get(0).getCOUNT() != null) {
				if (actOrderList.get(0).getCOUNT().longValue() >= 0)
					lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
			}
		
			//inserting into accounts table
			//claimsPaymentDao.submitClaimPaymentsInAccounts(transactionVO);
			//inserting into accounts table
			/*if(((String)lParamMap.get("sentAgain"))!=null && ((String)lParamMap.get("sentAgain")).equalsIgnoreCase("N")) 
			claimsPaymentDao.submitClaimPaymentsInAccounts(transactionVO);
			else if(((String)lParamMap.get("sentAgain"))!=null && ((String)lParamMap.get("sentAgain")).equalsIgnoreCase("Y"))
			claimsPaymentDao.submitClaimPaymentsInAccountsForRej(transactionVO);
			*/
			EhfFollowUpAudit folowUpAudit = new EhfFollowUpAudit();
			EhfFollowUpAuditId folowUpAuditPK = new EhfFollowUpAuditId(lActOrder,
					(String) lParamMap.get("CASE_FOLLOWUP_ID"),"CLAIM");
			folowUpAudit.setId(folowUpAuditPK);
			folowUpAudit.setActId(claimFlowVO.getNextStatus());
			folowUpAudit.setActBy(claimFlowVO.getUserId());
			folowUpAudit.setCrtUsr(claimFlowVO.getUserId());
			folowUpAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			folowUpAudit.setLangId(ClaimsConstants.LANG_ID);
			folowUpAudit.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
			if(rejectedPayment!=null && rejectedPayment.length()>0 && rejectedPayment.equalsIgnoreCase("true"))
			{
				folowUpAudit.setBooksUpdatedStatus("R");
			}
			else
			{
				folowUpAudit.setBooksUpdatedStatus("N");
			}
			
			if((String) lParamMap.get("FileName")!=null)
				folowUpAudit.setFileName((String) lParamMap.get("FileName"));
			//folowUpAudit.setApprAmt(apprAmt);
			folowUpAudit.setUserRole(claimFlowVO.getRoleId());
			
		    genericDao.save(folowUpAudit);
			isInsert = true;
		}

		logger.info("End:ChangeFollowupClaimStatus method in FollowUpDaoimpl");
		return isInsert;

    }
@Transactional
    public boolean updateFlupTdsAccountDetails(HashMap lParamMap) throws Exception
    {
        logger.info("Start:updateFlupTdsAccountDetails method in FollowUpDaoimpl" );
        String lStrFPId = null;String hosp_id="";String hosp_type="";
        String paymentType = ClaimsConstants.EMPTY;String lStrTrustPaymentId="";
        boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
        EhfFollowUpClaimAccounts ehfFPClaimAccounts = null;
        lStrFPId = (String) lParamMap.get("CASE_ID");
        lStrTrustPaymentId = (String) lParamMap.get("CASE_FOLLOWUP_ID");
        paymentType = lStrTrustPaymentId.substring(lStrTrustPaymentId.lastIndexOf(ClaimsConstants.SLASH) + 1, lStrTrustPaymentId.length());
        String lStrCaseId = lStrFPId.substring(ClaimsConstants.ZERO_VAL, lStrFPId.indexOf(ClaimsConstants.SLASH));
        String caseFlupId=lStrFPId.substring(ClaimsConstants.ZERO_VAL, lStrTrustPaymentId.lastIndexOf(ClaimsConstants.SLASH));
        String amount = (String) lParamMap.get("TRANSACTION_AMOUNT");
        ehfFPClaimAccounts = genericDao.findById(EhfFollowUpClaimAccounts.class,String.class,caseFlupId);
        
        TransactionVO transactionVO = new TransactionVO();
		transactionVO.setCaseId(caseFlupId);
		transactionVO.setTdsRfId(lStrTrustPaymentId);
		transactionVO.setNetAmount("");
		transactionVO.setTdsOrRfAmount(amount);	
		transactionVO.setTransactionType(config.getString("ACC.FOLLCLAIMS"));
		transactionVO.setScheme((String) lParamMap.get("SCHEME_ID"));
		transactionVO.setLstUpdUsr((String) lParamMap.get("CRTUSER"));
		
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
        
        if (ClaimsConstants.G.equalsIgnoreCase(paymentType))
        {
        	ehfFPClaimAccounts.setRevDesAcctNo((String) lParamMap.get("DES_ACCT_NO"));
            isInsert = ClaimsConstants.BOOLEAN_TRUE;
            transactionVO.setNarration("RF");
			transactionVO.setTdsOrRfType(config.getString("ACC.RF"));
			
			//saving file name for RF
			EhfClaimTrustPayment ehfClaimTrustPayment = genericDao.findById(EhfClaimTrustPayment.class, String.class,
					lStrTrustPaymentId);
			ehfClaimTrustPayment.setFilename((String) lParamMap.get("FileName"));
			ehfClaimTrustPayment.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfClaimTrustPayment.setPaymentStatus((String) lParamMap.get("nextCaseStatus"));
			ehfClaimTrustPayment.setLstUpdUsr((String) lParamMap.get("CRTUSER"));
			ehfClaimTrustPayment.setTransStatus((String) lParamMap.get("SentStatus"));
			ehfClaimTrustPayment.setRemarks("Claim sent For Payment");
			ehfClaimTrustPayment.setBooksUpdatedStatus("N");
			//ehfClaimTrustPayment.setLstUpdUsr(ClaimsConstants.CEO_USER_ID);
			ehfClaimTrustPayment = genericDao.save(ehfClaimTrustPayment);
        }
        else if (ClaimsConstants.GD.equalsIgnoreCase(paymentType))
        {
        	ehfFPClaimAccounts.setRevDesAcctNo((String) lParamMap.get("DES_ACCT_NO"));
            isInsert = ClaimsConstants.BOOLEAN_TRUE;
        }
        else if (ClaimsConstants.TDS.equalsIgnoreCase(paymentType))
        {
        	ehfFPClaimAccounts.setTdsDesAcctNo( (String) lParamMap.get("DES_ACCT_NO"));
            isInsert = ClaimsConstants.BOOLEAN_TRUE;
            transactionVO.setNarration("TDS");
			transactionVO.setTdsOrRfType(config.getString("ACC.TDS"));
			
			//saving file name for TDS
			EhfClaimTdsPayment ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class, String.class,
					lStrFPId);
			ehfClaimTdsPayment.setFileName((String) lParamMap.get("FileName"));
			ehfClaimTdsPayment.setLastUpdDate(new java.sql.Timestamp(new Date().getTime()));
			ehfClaimTdsPayment.setPaymentStatus((String) lParamMap.get("nextCaseStatus"));
			ehfClaimTdsPayment.setTransStatus((String) lParamMap.get("SentStatus"));
			ehfClaimTdsPayment.setRemarks("Claim sent For Payment");
			ehfClaimTdsPayment.setLastUpdUser((String) lParamMap.get("CRTUSER"));
			ehfClaimTdsPayment.setBooksUpdatedStatus("N");
			//ehfClaimTdsPayment.setLastUpdUser(ClaimsConstants.CEO_USER_ID);
			ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
        }
       /* if(((String)lParamMap.get("sentAgain"))!=null && ((String)lParamMap.get("sentAgain")).equalsIgnoreCase("N")) 
			claimsPaymentDao.submitTdsOrRfPaymentsInAccounts(transactionVO);
		else if(((String)lParamMap.get("sentAgain"))!=null && ((String)lParamMap.get("sentAgain")).equalsIgnoreCase("Y"))
			claimsPaymentDao.submitTdsOrRfPaymentsInAccountsForRej(transactionVO);
        */
        //claimsPaymentDao.submitTdsOrRfPaymentsInAccounts(transactionVO);
        
        ehfFPClaimAccounts = genericDao.save(ehfFPClaimAccounts);
        if(ehfFPClaimAccounts==null) 
        {
        	 isInsert = ClaimsConstants.BOOLEAN_FALSE;
        }
        logger.info("End:updateFlupTdsAccountDetails method in FollowUpDaoimpl" );
        return isInsert;
    }
@Override
public CommonDtlsVO getFPDtlsForPayment(String lStrCaseId,String lStrFollowUpId) {
	StringBuffer queryBuff = new StringBuffer();
	HashMap<Object, String> lQueryVal = new HashMap<Object, String>();
	int i = 0;
	List<CommonDtlsVO> resLst = null;
	try {

		queryBuff.append(" select distinct p.name as PATIENTNAME , p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , p.crtDt as date ");
		queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT ");
		queryBuff.append(" ,ec.caseId  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID ,ec.crtDt as date , p.cardType as cardType ");
		queryBuff.append("  ,hm.hospName||''||hm.hospDispCode as HOSPNAME , p.gender as GENDER , a.cmbDtlName as STATUS ,ecf.followUpStatus as CASESTAT   ");
		queryBuff.append(" , acdm.id.followUpId as IPNO , cm.catName as DISNAME, sm.subCatName as SURGNAME,ecf.totClaimAmt||'' as claimAmt  ");
		queryBuff.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm,EhfClinicalFlpupMpg acdm ");
		queryBuff.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph , EhfmDiagCategoryMst cm , EhfmDiagSubCatMst sm,EhfCaseFollowupClaim ecf ");
		queryBuff.append(" where p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and ecf.caseFollowupId=acdm.id.followUpId and ");
		queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId ");// and ec.caseId = '"+lStrCaseId+"' ");
		queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ecf.followUpStatus  and ecf.caseId=ec.caseId and acdm.id.followUpId= '"+lStrFollowUpId+"' ");
		queryBuff.append(" and p.patientId =ph.patientId and ph.categoryCode = cm.id.catCode and ph.mainCatCode = cm.id.mainCatCode ");
		queryBuff.append(" and sm.id.catCode =ph.categoryCode and sm.id.subCatCode = ph.subCatCode ");

		resLst = genericDao.executeHQLQueryList(CommonDtlsVO.class,
				queryBuff.toString());

	} catch (Exception e) {
		e.printStackTrace();
	}
	return resLst.get(0);
}

@Override
public List<FollowUpVO> getClinicalData(FollowUpVO followUpVO) {
		Session session = null;List<FollowUpVO> listClinicalNotes = new ArrayList<FollowUpVO>();
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		StringBuffer sql_query = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			sql_query.append(" from EhfClinicalFlpupMpg a,EhfCaseClinicalNotes b where b.clinicalId=a.id.clinicalId and a.caseId=b.caseId and a.id.followUpId='"+followUpVO.getFollowUpId()+"'");
			session = factory.openSession();
			Query query1 = session.createQuery(sql_query.toString());
			Iterator ite = query1.list().iterator();
			while(ite.hasNext())
			{
				Object[] pair = (Object[]) ite.next();
				EhfClinicalFlpupMpg ehfClinicalFlpupMpg = (EhfClinicalFlpupMpg)pair[0];
				EhfCaseClinicalNotes ehfCaseClinicalNotes=(EhfCaseClinicalNotes)pair[1];
				FollowUpVO followUpVODtls = new FollowUpVO(); 
				followUpVODtls.setCLINICALID(ehfClinicalFlpupMpg.getId().getClinicalId());
				followUpVODtls.setFromDate(sdf.format(ehfClinicalFlpupMpg.getCrtDt()));
				followUpVODtls.setBLOODPRESSURE(ehfCaseClinicalNotes.getBpSystolic());
				followUpVODtls.setFLUIDINPT(ehfCaseClinicalNotes.getFluidInput());
				followUpVODtls.setFLUIDOUTPUT(ehfCaseClinicalNotes.getFluidOutput());
				followUpVODtls.setHEART_RATE(ehfCaseClinicalNotes.getHeartRate());
				followUpVODtls.setLUNGS(ehfCaseClinicalNotes.getLungsCondition());
				followUpVODtls.setPULSE(ehfCaseClinicalNotes.getPulse());
				followUpVODtls.setRESPIRATORY(ehfCaseClinicalNotes.getRespiratory());
				followUpVODtls.setTEMPERATURE(ehfCaseClinicalNotes.getTemperature());
				followUpVODtls.setWARDTYPE(ehfCaseClinicalNotes.getWardType());
				followUpVODtls.setCRTUSR(ehfCaseClinicalNotes.getCrtUsr());
				
				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				drugsList = getDrugsDtls(followUpVO,ehfClinicalFlpupMpg.getId().getClinicalId());
				followUpVODtls.setDrugs(drugsList);
				
				List<AttachmentVO> lstAttachments = getFollowUPAttach(followUpVO.getFollowUpId(),ehfClinicalFlpupMpg.getId().getClinicalId());
				followUpVODtls.setGenAttachmentsList(lstAttachments);								
				listClinicalNotes.add(followUpVODtls);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	return listClinicalNotes;
}

public List<AttachmentVO> getFollowUPAttach(String followUpId,String clinicalId) {
	List<AttachmentVO> lstAttachment = new ArrayList<AttachmentVO>();
	String args[] = new String[2];
	args[0] =followUpId;
	args[1] =clinicalId;
	try{
		StringBuffer query = new StringBuffer();
		query.append(" select ats.invMainCode as testId,ats.invMainName as testName,ats.invName as fileName, ats.invCode as fileExtsn ,ats.invName as fileContentType, eft.attachTotalPath as filePath ,eft.crtDt||'' as fileReportPath  ");
		query.append(" from EhfFollowupTests eft,EhfmGenInvestigationsMst ats where eft.testId =ats.invCode and eft.followupId=? and eft.clinicalId=? ");
		lstAttachment = genericDao.executeHQLQueryList(AttachmentVO.class, query.toString(), args);
		
	}catch(Exception e)
	{
		e.printStackTrace();	
		}
	return lstAttachment;
}
@Override
public List<DrugsVO> getDrugsDtls(FollowUpVO followUpVO, String clincialId) {
	List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
	Session session = null;
	SessionFactory factory = hibernateTemplate.getSessionFactory();
	StringBuffer sql_query = new StringBuffer();
	Long drugSno=0L;
	try {
		sql_query.append(" from EhfmDrugsMst a,EhfCaseFlpupDrug b,EhfClinicalFlpupMpg c where a.id.drugTypeCode=b.drupTypeCode and a.id.drugCode=b.drugCode and c.id.clinicalId=b.id.clinicalId and c.id.followUpId='"+followUpVO.getFollowUpId()+"' and c.id.clinicalId='"+clincialId+"'");
		session = factory.openSession();
		Query query1 = session.createQuery(sql_query.toString());
		Iterator ite = query1.list().iterator();
		while(ite.hasNext())
		{
			Object[] pair = (Object[]) ite.next();
			SimpleDateFormat sdsf=new SimpleDateFormat("dd-MM-yyyy");
			
			EhfmDrugsMst ehfmDrugMst = (EhfmDrugsMst)pair[0];
			EhfCaseFlpupDrug ehfCaseFpDrug = (EhfCaseFlpupDrug)pair[1];
			DrugsVO drugVo =  new DrugsVO();
			drugSno=drugSno+1;
			drugVo.setDosage(ehfCaseFpDrug.getDosage());
			drugVo.setMedicationPeriod(ehfCaseFpDrug.getMedication());
			drugVo.setDrugTypeName(ehfmDrugMst.getDrugTypeName());
			drugVo.setDrugSubTypeName(ehfmDrugMst.getDrugSubTypeName());
			drugVo.setDrugName(ehfmDrugMst.getDrugName());
			drugVo.setRoute(ehfmDrugMst.getRouteName());
			drugVo.setStrength(ehfmDrugMst.getStrength());
			drugVo.setDrugId(drugSno);
			if(ehfCaseFpDrug.getBatchNumber()==null)
				drugVo.setBatchNumber("--NA--");
			else if("".equalsIgnoreCase(ehfCaseFpDrug.getBatchNumber()))
				drugVo.setBatchNumber("--NA--");
			else 
				drugVo.setBatchNumber(ehfCaseFpDrug.getBatchNumber());
			
			if(ehfCaseFpDrug.getDrugExpiryDate()==null)
				drugVo.setDrugExpiryDt("--NA--");
			else if("".equalsIgnoreCase(ehfCaseFpDrug.getDrugExpiryDate().toString()))
				drugVo.setDrugExpiryDt("--NA--");
			else 
				drugVo.setDrugExpiryDt((sdsf.format(ehfCaseFpDrug.getDrugExpiryDate())));
			
			drugsList.add(drugVo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		session.close();
		factory.close();
	}
	return drugsList;
}

@Override
public String updateFPClaimStatusReady(ClaimsFlowVO claimFlowVO) {
	String lResult = "";
	String lStrNextStatus = getNextStatus(
			claimFlowVO.getCaseStat(),
			claimFlowVO.getRoleId(), claimFlowVO.getActionDone());
	try{
	for(String followUpId : claimFlowVO.getCaseSelected()){
		
		EhfCaseFollowupClaim ehfCaseFpClaim = new EhfCaseFollowupClaim();
		ehfCaseFpClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class, followUpId);
		ehfCaseFpClaim.setFollowUpStatus(lStrNextStatus);
		
		ehfCaseFpClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
		ehfCaseFpClaim.setLstUpdUsr(claimFlowVO.getUserId());
		
		
		Long lActOrder=0L;
		EhfFollowUpAudit folowUpAudit = new EhfFollowUpAudit();
		lActOrder = getMaxActOrderFollowup(followUpId)+1;
		EhfFollowUpAuditId folowUpAuditPK = new EhfFollowUpAuditId(lActOrder,
				followUpId,"CLAIM");
		folowUpAudit.setId(folowUpAuditPK);
		folowUpAudit.setActId(lStrNextStatus);
		folowUpAudit.setActBy(claimFlowVO.getUserId());
		folowUpAudit.setCrtUsr(claimFlowVO.getUserId());
		folowUpAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
		folowUpAudit.setLangId(ClaimsConstants.LANG_ID);
		
		if(ehfCaseFpClaim.getCochlearYN()!=null && ehfCaseFpClaim.getCochlearYN().equalsIgnoreCase("Y"))
			{
				if(config.getString("EHF.FollowUP.ClaimReadyToSendToBank").equalsIgnoreCase(lStrNextStatus))
					{
						ehfCaseFpClaim.setCeoRemark("Cochlear "+config.getString("ClaimReadyToSendToBankRemarks"));
						folowUpAudit.setRemarks("Cochlear "+config.getString("ClaimReadyToSendToBankRemarks"));
						folowUpAudit.setApprAmt(ehfCaseFpClaim.getClaimDyEoAmt());
						ehfCaseFpClaim.setClaimAmount(ehfCaseFpClaim.getClaimDyEoAmt());
					}
				else if	(config.getString("CochlearTDSB").equalsIgnoreCase(lStrNextStatus))
					{
						if(claimFlowVO.getSendBackRmrks()!=null)
							{
								folowUpAudit.setRemarks(claimFlowVO.getSendBackRmrks());
								ehfCaseFpClaim.setCeoRemark(claimFlowVO.getSendBackRmrks());
							}
						else
							{
								ehfCaseFpClaim.setCeoRemark("Cochlear "+config.getString("ClaimSenttoCTD"));
								folowUpAudit.setRemarks("Cochlear "+config.getString("ClaimSenttoCTD"));
							}
						folowUpAudit.setApprAmt(0.0);
						ehfCaseFpClaim.setClaimAmount(0.0);
					}
			}	
			
		/*if(claimFlowVO.getRoleId()!=null && (config.getString("EHF.Claims.CEO")).equalsIgnoreCase(claimFlowVO.getRoleId()))
			folowUpAudit.setUserRole(config.getString("Group.CEO"));
		else*/
		folowUpAudit.setUserRole(claimFlowVO.getRoleId());
		//folowUpAudit.setApprAmt(ApprvAmt);
		genericDao.save(ehfCaseFpClaim);
		genericDao.save(folowUpAudit);
	    
		lResult = ClaimsConstants.ONE;
		if	(config.getString("CochlearTDSB").equalsIgnoreCase(lStrNextStatus))
			lResult = ClaimsConstants.FIVE;	
	}
	}catch(Exception ex){
		ex.printStackTrace();
		lResult = ClaimsConstants.ZERO;
	}		
	return lResult;
}

private Long getMaxActOrderFollowup(String pStrCaseId)
{
	Long actOrder = (long) 0;
	try
	{
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    criteriaList.add(new GenericDAOQueryCriteria("id.caseFollowupId",pStrCaseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
	    actOrder= genericDao.getMaxByPropertyCriteria(EhfFollowUpAudit.class, "id.actOrder",criteriaList);
		
	}
	catch(Exception e)
	{
		logger.error("actOrder"+actOrder);
		logger.error("CaseId"+pStrCaseId);
		logger.error("Error in method getMaxActOrderFollowup of class ValidateCaseImpl.java--> "+ e.getMessage());
	}
	
	
	if(actOrder.intValue()==0)
	{
		StringBuffer query = new StringBuffer();
		try{
			query.append(" select max(a.id.actOrder) as IDVAL from EhfFollowUpAudit a where a.id.caseFollowupId ='"+pStrCaseId+"' ");
			List<LabelBean> lstMax = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			if(lstMax != null && lstMax.size() >0)
			{
				actOrder = 	lstMax.get(0).getIDVAL();
			}
		}catch(Exception e)
		{
			logger.error("actOrder"+actOrder);
			logger.error("CaseId"+pStrCaseId);
			logger.error("Error in method getMaxActOrder of class PreauthServiceImpl.java--> "+ e.getMessage());	
		}
	}	
	return actOrder;
}

@Override
public List<LabelBean> getDrugSubList(String drugTypeCode) {
	List<LabelBean> drugSubList = null;	
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select distinct edm.id.drugSubTypeCode as  ID , edm.drugSubTypeName as VALUE from EhfmDrugsMst edm where edm.id.drugTypeCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.drugSubTypeName asc");
		drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return drugSubList;
}

@Override
public String getDrugDetails(String drugCode) {
	String drugDetails="";
	Iterator drugItr=null;
	EhfmDrugsMst ehfmDrugsMst=null;
	try
	{
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
		criteriaList.add(new GenericDAOQueryCriteria("activeYN",config.getString("ActiveYn"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("id.drugCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfmDrugsMst> drugsList = genericDao.findAllByCriteria(EhfmDrugsMst.class, criteriaList);
		drugItr=drugsList.iterator();
		if(drugItr.hasNext())
		{
			ehfmDrugsMst=(EhfmDrugsMst)drugItr.next();   
			drugDetails=ehfmDrugsMst.getRouteCode()+"("+ehfmDrugsMst.getRouteName()+")~"+ehfmDrugsMst.getStrength();
			criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("id.inpCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfSsrMedinpData> lstEhfSsrMedinpData = genericDao.findAllByCriteria(EhfSsrMedinpData.class, criteriaList);
			if(lstEhfSsrMedinpData != null && lstEhfSsrMedinpData.size() > 0)
			drugDetails= drugDetails+"~"+lstEhfSsrMedinpData.get(0).getUnitPrice();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return drugDetails;
}

@Override
public List<String> getInvestigations(String lStrBlockId) {
	List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
	List<String> investList=new ArrayList<String>(); 
	StringBuffer query =null;
	try
	{
		query = new StringBuffer();
		query.append("select eim.invCode as ID,eim.invName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN='Y' ");
		query.append(" and eim.invMainCode='"+lStrBlockId+"' order by eim.invName asc");
		investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		for(LabelBean invest:investigationsList)
		{
			investList.add(invest.getID()+"~"+invest.getVALUE()+"@");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return investList;
}
/*
 * This method is used to retrieve the patient photo
 * based upon the caseId.
 */
@Override
public String getPatientPhoto(String caseId)
	{
		String photo=null,cardNo=null,enrollParntId=null;
		List<FollowUpVO> followUpLst=new ArrayList<FollowUpVO>();
		StringBuffer query=new StringBuffer();
		query.append(" select ep.cardNo as cardNo,eef.enrollPrntId as enrollParntId, ");
		query.append(" eef.enrollPhoto as enrollPhoto ");
		query.append(" from EhfCase ec,EhfPatient ep,EhfEnrollmentFamily eef ");
		query.append(" where ec.casePatientNo=ep.patientId and ec.caseId='"+caseId+"'");
		query.append(" and ep.cardNo=eef.ehfCardNo");
		followUpLst=genericDao.executeHQLQueryList(FollowUpVO.class,query.toString());
		
		if(followUpLst!=null && followUpLst.size()>0)
			{
				if(followUpLst.get(0).getCardNo()!=null)
					cardNo=followUpLst.get(0).getCardNo();
				if(followUpLst.get(0).getCardNo()!=null)
					enrollParntId=followUpLst.get(0).getEnrollParntId();
			}
		if(cardNo!=null && !"".equalsIgnoreCase(cardNo))
			{
				if(cardNo.endsWith("01"))
					{
						Map<String, Object> photoMap=new HashMap<String, Object>();
						photoMap.put("employeeParntId", enrollParntId);
						photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
						photoMap.put("attachactiveYN","Y");
						EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
						
						if(ehfEmployeeDocAttach!=null)
							{
								if(ehfEmployeeDocAttach.getSavedName()!=null &&
											!"".equalsIgnoreCase(ehfEmployeeDocAttach.getSavedName()))
									{
										photo=ehfEmployeeDocAttach.getSavedName();
									}
							}
					}
				else
					{
						if(followUpLst.get(0).getEnrollPhoto()!=null
								&& !"".equalsIgnoreCase(followUpLst.get(0).getEnrollPhoto()))
							{
								photo=followUpLst.get(0).getEnrollPhoto();
							}
					}
			}
		return photo;
	}

/*
 * This method is used to get the all the range for next followup for a case
 */
@Override
public long getFollowUpLst(String caseId)
	{
		List<LabelBean> followUpList=new ArrayList<LabelBean>();
		EhfCase ehfCase=new EhfCase();
		long daysDiff=0L;
		final long MILLIS_PER_DAY = 24 * 3600 * 1000;
		if(caseId!=null)
			ehfCase=genericDao.findById(EhfCase.class,String.class,caseId);
		if(ehfCase!=null)
			{
				long msDiff;
				if (ehfCase.getCsDisDt() != null)
					msDiff = new Date().getTime()
							- ehfCase.getCsDisDt().getTime();
				else
					msDiff = new Date().getTime()
							- ehfCase.getCsDeathDt().getTime();
				daysDiff = Math.round(msDiff / ((double) MILLIS_PER_DAY));
			}
		return daysDiff;
	}
/*
 * This method is used to get the next follow up date
 */
@Override
public Date getNxtFollowUpDt(String caseId,String offset)
	{
		Date nxtFollowUpDate=null;
		int offset1=0;
		if(offset!=null)
			offset1=Integer.parseInt(offset);
		List<FollowUpVO> followUpList=new ArrayList<FollowUpVO>();
		StringBuffer sb =new StringBuffer();
		sb.append(" select ec.cs_Dis_Dt+"+offset1+" as csDisDt");
		sb.append(" from Ehf_Case ec where case_Id='"+caseId+"'");
		//sb.append("select sysdate-90 as csDisDt from DUAl");
		followUpList=genericDao.executeSQLQueryList(FollowUpVO.class, sb.toString());
			if(followUpList!=null)
				{
					if(followUpList.size()>0)
						{
							if(followUpList.get(0)!=null)
							{
								if(followUpList.get(0).getCSDISDT()!=null)
									{
										nxtFollowUpDate=followUpList.get(0).getCSDISDT();
										
									}
							}
						}
				}
		return nxtFollowUpDate;
	}

public boolean updateFailedCaseStatus(String caseId)
{
	
	boolean isUpdate=false;

	
	EhfCaseFollowupClaim ehfCaseFollowupClaim=new EhfCaseFollowupClaim();
	ehfCaseFollowupClaim=genericDao.findById(EhfCaseFollowupClaim.class, String.class, caseId);
	if(ehfCaseFollowupClaim!=null)
	{
	try{
		ehfCaseFollowupClaim.setFollowUpStatus(config.getString("EHF.Claims.ClaimFailed"));
		ehfCaseFollowupClaim.setPaymentStatus(config.getString("EHF.Claims.ClaimFailed"));
		ehfCaseFollowupClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
		ehfCaseFollowupClaim=genericDao.save(ehfCaseFollowupClaim);
		isUpdate=true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		isUpdate=false;
	}
	}

	
	return isUpdate;
	}
	/*
	 * Added by Srikalyan for Saving
	 * Cochlear FollowUp Details
	 */
	public String saveCochlearFollowUpDetails(EhfCochlearFollowup ehfCochlearFollowup)
		{
			String result ="Failed";
			try
				{
					EhfCochlearFollowup ehfCochlearFollowupLoc=new EhfCochlearFollowup();
					ehfCochlearFollowupLoc=genericDao.save(ehfCochlearFollowup);
					if(ehfCochlearFollowupLoc!=null)
						result="Success";
				}
			catch(Exception e)
				{
					e.printStackTrace();
					logger.error("Exception Occured in saveCochlearFollowUpDetails method of FollowUpDaoImpl"+e.getMessage());
				}
			return result;
		}
	/*
	 * Added by Srikalyan for getting
	 * Case Object
	 */
	public EhfCase getCase(String caseId)
		{
			EhfCase ehfCase=new EhfCase();
			try
				{
					if(caseId!=null)
						ehfCase=genericDao.findById(EhfCase.class, String.class, caseId);
				}
			catch(Exception e)
				{
					e.printStackTrace();
					logger.error("Exception Occured in getCase method of FollowUpDaoImpl"+e.getMessage());
				}
				return ehfCase;
		}
	
	
	/*
	 * Added by Srikalyan for Saving 
	 * Cochlear Case Claim Object
	 */
	public String saveCochCaseClaim(EhfCaseFollowupClaim ehfCaseFollowupClaim)
		{
			String result="Failed";
			try
				{
				EhfCaseFollowupClaim ehfCaseFollowupClaimLoc=new EhfCaseFollowupClaim();
					ehfCaseFollowupClaimLoc=genericDao.save(ehfCaseFollowupClaim);
					if(ehfCaseFollowupClaimLoc!=null)
						result="Success";
				}
			catch(Exception e)
				{
					e.printStackTrace();
					logger.error("Exception Occured in saveCochCaseClaim method of FollowUpDaoImpl"+e.getMessage());
				}
			return result;
		}
	/*
	 * Added by Srikalyan for getting
	 * Cochlear FollowUp Details
	 */
	@Override
	public List<FollowUpVO> getCochlearDetails(String caseId,int cochlearCount)
		{
			List<FollowUpVO> followUpVOLst=new ArrayList<FollowUpVO>();
			StringBuffer query=new StringBuffer();
			try
				{
					query.append(" select ecf.postopPeriod as postOP,ecf.postopRemarks as postOPRemarks,ecf.woundSight as woundSight,");
					query.append(" to_char(ecf.dateOfSwitchOn,'DD/MM/YYYY') as switchOnDate,ecf.audiologist as audiologist,");
					query.append(" cast(ecf.cochlearFollowupCount,int) as cochlearCount,to_char(ecf.reviewDate,'DD/MM/YYYY') as reviewDate,");
					query.append(" ecf.childProgressRemarks as childProgressRemarks,ecf.avtheraphyAudiologist as audiologistName,");
					query.append(" to_char(ecf.avTheraphyFromDate,'DD/MM/YYYY') as fromDate,to_char(ecf.avTheraphyToDate,'DD/MM/YYYY') as toDate ");
					query.append(" from EhfCochlearFollowup ecf ");
					query.append(" where ecf.caseId='"+caseId+"' order by ecf.cochlearFollowupCount");
				}
			catch(Exception e)
				{
					e.printStackTrace();
					logger.error("Exception Occured in saveCochCaseClaim method of FollowUpDaoImpl"+e.getMessage());
				}
			followUpVOLst=genericDao.executeHQLQueryList(FollowUpVO.class,query.toString());
			
			return followUpVOLst;
		}
	
	/*
	 * Added by Srikalyan for getting
	 * Case Details
	 */
	@Override
	public EhfCase getCaseDtls(String caseId)
		{
			EhfCase ehfCase =new EhfCase();
			try
				{
					ehfCase=genericDao.findById(EhfCase.class,String.class,caseId);
				}
			catch(Exception e)
				{
					e.printStackTrace();
					logger.error("Exception Occured in getCaseDtls method of FollowUpDaoImpl"+e.getMessage());
				}
			return ehfCase;
		}
	
	/*
	 * Added by Srikalyan for getting FollowUp Claim cases 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FollowUpVO> executeQuery(String query,int startIndex,int maxResults)
	 	{
		 	List<FollowUpVO> flpLst=new ArrayList<FollowUpVO>();
		 	SessionFactory factory=hibernateTemplate.getSessionFactory();
		 	Session session=factory.openSession();
		 	try
			 	{
		 			if(startIndex==0 && maxResults==0)
		 				flpLst=session.createQuery(query).setResultTransformer(Transformers.aliasToBean(FollowUpVO.class)).list();
		 			else
		 				flpLst=session.createQuery(query).setFirstResult(startIndex).setMaxResults(maxResults).
			 				setResultTransformer(Transformers.aliasToBean(FollowUpVO.class)).list();
			 	}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			logger.error("Exception Occured in executeQuery method of FollowUpDaoImpl"+e.getMessage());
		 		}
		 	finally
		 		{
		 			session.close();
				factory.close();
		 		}
		 	return flpLst;
	 	}
	
	/*
	 * Added by Srikalyan to Execute Query for 
	 * Cochlear FollowUp Workflow Cases
	 */
	@Override
	 public List<FollowUpVO> executeNormalQuery(String query)
	 	{
		 	List<FollowUpVO> flpLst=new ArrayList<FollowUpVO>();
		 	try
		 		{
		 			flpLst=genericDao.executeHQLQueryList(FollowUpVO.class, query);
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			logger.error("Exception Occured in executeNormalQuery method of FollowUpDaoImpl"+e.getMessage());
		 		}
		 	return flpLst;
	 	}
	 

	 /*
	 * Added by Srikalyan to Obtain Case FollowUp 
	 * Claim Object for Cochlear Case 
	 */
	@Override
	 public EhfCaseFollowupClaim getCaseClaim(String followUpId)
	 	{
		 	EhfCaseFollowupClaim claimObj=new EhfCaseFollowupClaim();
		 	try
		 		{
		 			claimObj=genericDao.findById(EhfCaseFollowupClaim.class, String.class, followUpId);
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			logger.error("Exception Occured in getCaseClaim method of FollowUpDaoImpl"+e.getMessage());
		 		}
		 	
		 	return claimObj;
		 	
	 	}
	
	 /*
	 * Added by Srikalyan to Insert Cochelar   
	 * FollowUp Claim Objects in Data Base 
	 */
	@Override
	 public String saveObj(HashMap<String,Object> saveObj,String saveName)
		{
			String returnStr="Failed";
			try
				{
					if(saveObj!=null && saveName!=null)
						{
							genericDao.save(saveObj.get(saveName));
							returnStr="Success"; 
						}
				}
			catch(Exception e)
				{
					returnStr="Failed";
					e.printStackTrace();
					logger.error("Exception Occured in saveObj method of FollowUpDaoImpl"+e.getMessage());
				}
			return returnStr;
		}
}
