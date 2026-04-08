package com.ahct.claims.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.TransactionVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.TimeStamp;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.model.EhfAcctsTransactionDtls;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseClaim;
import com.ahct.model.EhfCaseClaimHubSpokePayment;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfClaimAccounts;
import com.ahct.model.EhfClaimTdsPayment;
import com.ahct.model.EhfClaimTrustPayment;
import com.ahct.model.EhfClaimUploadFile;
import com.ahct.model.EhfErroneousClaim;
import com.ahct.model.EhfFollowUpAudit;
import com.ahct.model.EhfFollowUpAuditId;
import com.ahct.model.EhfFollowUpClaimAccounts;
import com.ahct.model.EhfHubSpokeAudit;
import com.ahct.model.EhfHubspokeAuditPK;
import com.ahct.model.EhfPaymentSlabDtls;
import com.ahct.model.EhfRevFundAudit;
import com.ahct.model.EhfRevFundAuditId;
import com.ahct.model.EhfSurplusTdsClaim;
import com.ahct.model.EhfTdsAudit;
import com.ahct.model.EhfTdsAuditId;
import com.ahct.model.EhfmHospSurgPer;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

/**
 * The Class ClaimsPaymentDAOImpl
 * 
 * @author Ishank Paharia
 * @class This Class is used for Claim Payment Process
 * @version jdk 1.7
 * @Date 23 November 2013
 */
public class ClaimsPaymentDAOImpl implements ClaimsPaymentDAO {

	/** The Constant logger. */
	boolean medicalEntry=false;
	private static final Logger logger = Logger
			.getLogger(ClaimsFlowDAOImpl.class);

	/** The generic dao. */
	GenericDAO genericDao;
	
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
	CommonService commonService ;
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/** The sdf. */
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/*
	 * @param lparamMap - HashMap
	 * @Desc used to Calculate the Claim Percentage for cases(having Slab concept included)
	 * @return true, if successful
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public boolean calculateClaimPercentage(HashMap lparamMap) throws Exception {

		StringBuffer lStrBuffer = new StringBuffer();
		Map lResMap = new HashMap();
		boolean isInsert = false;
		String paymentType = (String) lparamMap.get("PaymentType");
		List<ClaimsFlowVO> percentatgeList = new ArrayList<ClaimsFlowVO>();
		String[] arg = new String[3];
		String cochYn=null;
		
		arg[0] = (String) lparamMap.get("CASE_ID");
		
		/*Bifurcation changes by venkatesh*/
		String schemeId= (String)lparamMap.get("SCHEME_ID");;
		arg[2]=schemeId;

		lStrBuffer
				.append(" select distinct ae.tds_active_yn as TDSACTIVEYN,ae.active_yn as ACTIVEYN,nvl(ae.hosp_percent, 100) as HOSPPERCENT,nvl(ae.trust_percent, 0) as TRUSTPERCENT,");
		lStrBuffer
				.append(" nvl(ae.TDS_HOSP_PERCENT, 100) as TDSHOSPPERCENT, nvl(ae.TDS_PERCENT, 0) as TDSPERCENT,nvl(ae.TDS_SURCHARGE_PCT, 0) as  SURCHARGEPERCENT,nvl(ae.TDS_CESS_PCT, 0) as CESSPERCENT, ");
		lStrBuffer
				.append(" ad.hosp_type as HOSPTYPE,ae.hosp_id as HOSPITALID,aag.direct_deduction as DIRECTDEDUCTION,aag.slab_amount as SLABAMOUNT,aag.total_tds_amt as TOTALTDSAMT,aag.total_claim_amt as TOTALCLAIMAMT,  ");
		lStrBuffer
				.append(" aag.hosp_deductor_id as HOSPDEDUCTORID,aag.rqst_active_yn as RQSTACTIVEYN,aag.SlabAmt_applicable as SLABAMTAPPLICABLE, ab.tot_claim_amt as CLAIMAMOUNT, aah.rev_fund_active as REVFUNDACTIVE ");
		if (!(ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType) || ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)))// Chandana - 6444 - 04-02-2025: added to get the flag and pkg amount
			lStrBuffer.append(" ,ab.HUBSPOKE_FLAG AS HUBSPOKEFLAG, ab.PKG_AMT AS PKGAMT, ab.CYCLE_CNT CYCLECOUNT"); // Chandana - 6444 - 04-02-2025: added to get the flag and pkg amount
		lStrBuffer.append(" from ehf_case aa,ehfm_hospitals ad,EHFM_HOSP_SURG_PER ae,Ehfm_Hosp_Bank_Dtls aah ");

		if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
			
			
			lStrBuffer
			.append(" ,EHF_CASE_FOLLOWUP_CLAIM ab , ehf_case_therapy ma, ");
			
			StringBuffer query=new StringBuffer();
			
			List<ClaimsFlowVO> lst=new ArrayList<ClaimsFlowVO>();
			query.append(" select a.cochlear_Yn as COCHLEARYN from ehf_case_followup_claim a where a.case_followup_id='"+arg[0]+"'");
			lst=genericDao.executeSQLQueryList(ClaimsFlowVO.class,query.toString());
			
			if(lst!=null && lst.size()>0 && lst.get(0).getCOCHLEARYN()!=null && ("Y").equalsIgnoreCase(lst.get(0).getCOCHLEARYN()))
			{
				cochYn=lst.get(0).getCOCHLEARYN();
			}
		
			if(cochYn!=null && ("Y").equalsIgnoreCase(cochYn))
			{
				lStrBuffer
				.append("  ehfm_coch_followup_packages mb,Ehf_Cochlear_Followup aak  ");
			}
			else
			{
				lStrBuffer
				.append(" ehfm_followup_packages mb  ");
			}
		
		
		
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
					.append(" and ab.case_followup_id = ?  and ae.claim_type=? ");
			
			
			if(cochYn!=null && ("Y").equalsIgnoreCase(cochYn))
			{
				lStrBuffer
				.append(" and ab.case_id=ma.case_id and aa.case_id=ma.case_id and ma.icd_proc_code=mb.cochlear_surgery_id and aa.scheme_id=mb.scheme_id and mb.icd_code_proc_fp=ae.surgery_id      ");
				lStrBuffer
				.append("  and ab.case_followup_id=aak.cochlear_followup_id and aak.followup_proc=ae.surgery_id  ");
			}
			else
			{
				lStrBuffer
				.append(" and ab.case_id=ma.case_id and aa.case_id=ma.case_id and ma.icd_proc_code=mb.surgery_id and aa.scheme_id=mb.scheme_id and mb.icd_code_proc_fp=ae.surgery_id      ");
			}

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
		
		if(schemeId!=null && !("").equalsIgnoreCase(schemeId))
		{
			lStrBuffer.append(" and ae.scheme=aah.scheme and ae.scheme=? ");
		}

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
				
				String caseStatus1=(String) lparamMap.get("caseStatus");
				if(caseStatus1!=null && caseStatus1.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
				{
					EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, (String) lparamMap.get("CASE_ID"));
					
					if(ehfSurplusTdsClaim!=null)
					{
						if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
						{
							lResMap.put("hosp_percent", 0.0);
							//lResMap.put("trust_percent",0.0);
							lResMap.put("tds_hosp_percent",ehfSurplusTdsClaim.getTdsHospPctAmt());
							//lResMap.put("tds_percent", ehfSurplusTdsClaim.getTdsPctAmt());
						}
						else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
						{
							lResMap.put("hosp_percent", ehfSurplusTdsClaim.getHospPctAmt());
							//lResMap.put("trust_percent",ehfSurplusTdsClaim.getTrustPctAmt());
							lResMap.put("tds_hosp_percent",0.0);
							//lResMap.put("tds_percent", 0.0);
						}
						else
						{
						    lResMap.put("hosp_percent", 0.0);
						 
							lResMap.put("tds_hosp_percent",0.0);
							 System.out.println("TDS Flag is not set well for case id "+(String) lparamMap.get("CASE_ID"));
							logger.error("TDS Flag is not set well for case id "+(String) lparamMap.get("CASE_ID"));
							 EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,(String) lparamMap.get("CASE_ID"));
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
						}
					}
					else
					{
					
					lResMap.put("hosp_percent", 0.0);				
					lResMap.put("tds_hosp_percent", 0.0);
					System.out.println("No record present in EhfSurplusTdsClaim with case id "+(String) lparamMap.get("CASE_ID"));	
					logger.error("No record present in EhfSurplusTdsClaim with case id "+(String) lparamMap.get("CASE_ID"));	
					EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, (String) lparamMap.get("CASE_ID"));
					if(ehfCase!=null)
					{
						ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
						ehfCase=genericDao.save(ehfCase);
						
					}
					}
				}
				else
				{
				
				lResMap.put("hosp_percent", percentatgeList.get(i)
						.getHOSPPERCENT());				
				lResMap.put("tds_hosp_percent", percentatgeList.get(i)
						.getTDSHOSPPERCENT());				
				}
				lResMap.put("trust_percent", percentatgeList.get(i)
						.getTRUSTPERCENT());
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
				lResMap.put("HUBSPOKE_FLAG", percentatgeList.get(i).getHUBSPOKEFLAG()); //Chandana - 6444 - 04-02-2025 adding the flag to lResMap
				lResMap.put("PKG_AMT", percentatgeList.get(i).getPKGAMT()); //Chandana - 6444 - 04-02-2025
				lResMap.put ("CYCLE_CNT", percentatgeList.get(i).getCYCLECOUNT());
			}
		}

		lparamMap.put("ResMap", lResMap);
        // To get the TDS/RF/Hospital Amount
		Map lPercentAmtMap = getTDSDeductionDtls(lparamMap);

		if (ClaimsConstants.TRUE.equalsIgnoreCase((String) lPercentAmtMap
				.get("UpdSuccess"))) {
			double lTrustAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TrustAmt"));
			//Chandana - 6444 - 04-02-2025
			String lhubspokeFlag = (String)lPercentAmtMap.get("hubSpokeFlag");
			double lHubAmt = (Double)lPercentAmtMap.get("hubAmt"); 
            double lSpokeAmt = (Double)lPercentAmtMap.get("spokeAmt"); 
            double lAgencyAmt = (Double)lPercentAmtMap.get("agencyAmt");
            double lHubMnt = (Double)lPercentAmtMap.get("hubMaintenance");
            double lSpokeMnt = (Double)lPercentAmtMap.get("spokeMaintenance");
            double lAgencyPctAmt = (Double)lPercentAmtMap.get("agencyPctAmt");
			String caseStatus=(String) lparamMap.get("caseStatus");
			double lHospAmt=0.0;
			double lTDSHospAmt=0.0;
			if(caseStatus!=null && caseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
			{
				EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, (String) lparamMap.get("CASE_ID"));
				
				if(ehfSurplusTdsClaim!=null)
				{
					if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
					{
						 lHospAmt =0.0;
						 lTDSHospAmt = ehfSurplusTdsClaim.getTdsHospPctAmt();					
					}
					else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
					{
						 lHospAmt =ehfSurplusTdsClaim.getHospPctAmt();
						 lTDSHospAmt =0.0;	

					}
					else
					{
						 lHospAmt =0.0;
						 lTDSHospAmt =0.0;
						 System.out.println("TDS Flag is not set well for case id "+(String) lparamMap.get("CASE_ID"));
						 logger.error("TDS Flag is not set well for case id "+(String) lparamMap.get("CASE_ID"));
							EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, (String) lparamMap.get("CASE_ID"));
							if(ehfCase!=null)
							{
								ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
								ehfCase=genericDao.save(ehfCase);
								
							}
					}
				}
				else
				{
					 lHospAmt =0.0;
					 lTDSHospAmt =0.0;
					System.out.println("No record present in EhfSurplusTdsClaim with case id "+(String) lparamMap.get("CASE_ID"));	
					logger.error("No record present in EhfSurplusTdsClaim with case id "+(String) lparamMap.get("CASE_ID"));	
					EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, (String) lparamMap.get("CASE_ID"));
					if(ehfCase!=null)
					{
						ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
						ehfCase=genericDao.save(ehfCase);
						
					}
					
				}
			}
			else{
			 lHospAmt = Double.parseDouble((String) lPercentAmtMap
					.get("HospAmt"));
			 lTDSHospAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TDSHospAmt"));
			}
			double lTDSAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TDSAmt"));
			String lStrTDSActiveFlag = (String) lPercentAmtMap
					.get("TDSActiveFlag");
			double lTaxAmt = Double.parseDouble((String) lPercentAmtMap
					.get("lTaxAmt"));
			String lStrRFActiveFlag = (String) lPercentAmtMap
					.get("RFActiveFlag");
			lparamMap.put("Claim_Amount", Double.toString(lTrustAmt));
			lparamMap.put("TrustAmt", Double.toString(lTrustAmt));
			lparamMap.put("TDSAmt", Double.toString(lTDSAmt));
			lparamMap.put("TDSActiveFlag", lStrTDSActiveFlag);
			lparamMap.put("RFActiveFlag", lStrRFActiveFlag);
			//Chandana - 6444
			lparamMap.put("hubAmt", lHubAmt);
            lparamMap.put("spokeAmt", lSpokeAmt);
            lparamMap.put("hubMaintenance", lHubMnt);
            lparamMap.put("spokeMaintenance", lSpokeMnt);
            lparamMap.put("agencyPctAmt", lAgencyPctAmt);

			lStrBuffer = new StringBuffer();

			EhfCaseFollowupClaim ehfCaseFollowupClaim = null;
			EhfErroneousClaim ehfErroneousClaim = null;
			EhfCaseClaim ehfCaseClaim = null;

			isInsert = ClaimsConstants.BOOLEAN_TRUE;

			if (ClaimsConstants.Y.equalsIgnoreCase(lStrRFActiveFlag)) // If RF active
			{
				if (isInsert && lTrustAmt > ClaimsConstants.ZERO_VAL) {
					isInsert = ClaimsConstants.BOOLEAN_FALSE;
					isInsert = processTrustPayments(lparamMap); //processing the RF amount
				}
				else
				{
					
				}
			}
			if (ClaimsConstants.Y.equalsIgnoreCase(lStrTDSActiveFlag)) // If TDS active
			{
				if (isInsert && lTDSAmt > ClaimsConstants.ZERO_DBL_VAL) {
					isInsert = ClaimsConstants.BOOLEAN_FALSE;
					isInsert = processTDSPayments(lparamMap); //Processing the TDS Amount
				}
				else //for surplus tds for tds exemption amount
				{
					if(caseStatus!=null && (ClaimsConstants.CLAIM_SURPLUS_TDS_DED).equalsIgnoreCase(caseStatus))
					{						
						//tds claims payment
						String surCaseSetId="";
						String lStrCaseId=(String) lparamMap.get("CASE_ID");
						String lStrCaseFollowupId = lStrCaseId;
						 surCaseSetId=( lStrCaseId)+"/SD/TDS";
					   
						EhfClaimTdsPayment	ehfClaimTdsPayment = new EhfClaimTdsPayment();
						ehfClaimTdsPayment.setTdsPaymentId(surCaseSetId);
						ehfClaimTdsPayment.setCaseId(lStrCaseId);
						//ehfClaimTdsPayment.setPaymentStatus(ClaimsConstants.CLAIM_READY_PAYMENT);
						ehfClaimTdsPayment.setPaymentStatus((String) lparamMap
								.get("caseStatus"));
						
						EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, (String) lparamMap.get("CASE_ID"));
						String case_Type = ClaimsConstants.TRUST_DEDUCTOR;
						if(ehfSurplusTdsClaim!=null)
						{
							if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
							{
								ehfClaimTdsPayment.setClaimAmt(ehfSurplusTdsClaim.getTdsPctAmt());
								//lResMap.put("tds_percent", ehfSurplusTdsClaim.getTdsPctAmt());
							}
							else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
							{
								ehfClaimTdsPayment.setClaimAmt(ehfSurplusTdsClaim.getTrustPctAmt());
							}
				/*			else
								ehfClaimTdsPayment.setClaimAmt(0.0);*/
						}
						
						else
							ehfClaimTdsPayment.setClaimAmt(0.0);
						ehfClaimTdsPayment.setRemarks("Claim Ready For Payment");
						ehfClaimTdsPayment.setTransStatus((String) lparamMap
								.get("ReadyStatus"));
						ehfClaimTdsPayment.setTimeMilliSec((long) 0);
						ehfClaimTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
						ehfClaimTdsPayment.setCrtUser((String) lparamMap.get("CRTUSER"));
						ehfClaimTdsPayment.setPaymentCheck(ClaimsConstants.F);
						ehfClaimTdsPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
						//ehfClaimTdsPayment.setCaseFllwUpId(lStrCaseFollowupId);
						ehfClaimTdsPayment.setCaseType("CD525");
						ehfClaimTdsPayment.setPaymentType((String) lparamMap
								.get("TDS_PAYMENT_TYPE"));
						//ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
						ehfClaimTdsPayment.setDeductorType(case_Type);
						ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
				
						//tds audit 
						lparamMap.put("TDS_STAT_ID", ClaimsConstants.CLAIM_SENT);
						String lStrActId = (String) lparamMap.get("TDS_STAT_ID");
						
						List<ClaimsFlowVO> tdsAuditDtls = new ArrayList<ClaimsFlowVO>();
						StringBuffer lQueryBuffer = new StringBuffer();
						lQueryBuffer = new StringBuffer();
						lQueryBuffer
						.append(" select max(au.id.actOrder) as COUNT from EhfTdsAudit au where au.id.tdsPaymentId=? ");

			    arg = new String[1];
			     surCaseSetId="";
				 surCaseSetId=((String) lparamMap.get("CASE_ID")+"/SD/TDS");
				arg[0] = surCaseSetId;

				Long lintActOrder = 1L;
				tdsAuditDtls = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
						lQueryBuffer.toString(), arg);
						 lintActOrder = 1L;
						if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
								&& tdsAuditDtls.get(0).getCOUNT() != null) {
							if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
								lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
						}
						EhfTdsAudit ehfTDSAudit = new EhfTdsAudit();
						EhfTdsAuditId ehfTDSAuditId = new EhfTdsAuditId();
						ehfTDSAuditId.setTdsPaymentId(surCaseSetId);
						ehfTDSAuditId.setActOrder(lintActOrder);
						ehfTDSAudit.setId(ehfTDSAuditId);
						ehfTDSAudit.setActId(lStrActId);
						ehfTDSAudit.setActBy((String) lparamMap.get("CRTUSER"));
						ehfTDSAudit.setRemarks("Claim Sent For Payment");
						ehfTDSAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
						ehfTDSAudit.setCrtDt(new Timestamp(new Date().getTime()));
						ehfTDSAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
						ehfTDSAudit.setLangId(ClaimsConstants.LANG_ID);
						ehfTDSAudit = genericDao.save(ehfTDSAudit);
						
					}
				}
			}

			if (isInsert) {
                // For FollowUp
				if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
					String caseFollowupId = (String) lparamMap
							.get("CASE_FOLLOWUP_ID");
					ehfCaseFollowupClaim = genericDao.findById(
							EhfCaseFollowupClaim.class, String.class,
							caseFollowupId);
					if (ehfCaseFollowupClaim != null) {
						ehfCaseFollowupClaim.setHospPctAmt(lHospAmt);//Hospital payment amt for govt hosp
						ehfCaseFollowupClaim.setTrustPctAmt(lTrustAmt);//RF amount
						ehfCaseFollowupClaim.setTdsPctAmt(lTDSAmt);//Tds amount
						ehfCaseFollowupClaim.setTdsHospPctAmt(lTDSHospAmt);//hosp payment amount after deducting tds
						ehfCaseFollowupClaim.setTdsTaxAmt(lTaxAmt);						
						ehfCaseFollowupClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
						ehfCaseFollowupClaim.setLstUpdUsr((String)lparamMap.get("CRTUSER"));
						ehfCaseFollowupClaim = genericDao
								.save(ehfCaseFollowupClaim);
					}
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
				}
                //For Err-Claim
				else if (ClaimsConstants.ErroneousClaim
						.equalsIgnoreCase(paymentType)) {
					ehfErroneousClaim = genericDao.findById(
							EhfErroneousClaim.class, String.class,
							(String) lparamMap.get("CASE_ID") /* + "/E" */);
					if (ehfErroneousClaim != null) {
						ehfErroneousClaim.setHospPctAmt(lHospAmt);
						ehfErroneousClaim.setTrustPctAmt(lTrustAmt);
						ehfErroneousClaim.setTdsPctAmt(lTDSAmt);
						ehfErroneousClaim.setTdsHospPctAmt(lTDSHospAmt);
						ehfErroneousClaim.setTdsTaxAmt(lTaxAmt);
						
						ehfErroneousClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
						ehfErroneousClaim.setLstUpdUsr((String)lparamMap.get("CRTUSER"));
					}
					ehfErroneousClaim = genericDao.save(ehfErroneousClaim);
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
				} else {
					//For Normal Claim
					ehfCaseClaim = genericDao.findById(EhfCaseClaim.class,
							String.class, (String) lparamMap.get("CASE_ID"));
					if (ehfCaseClaim != null) {
						String caseStatus2=(String) lparamMap.get("caseStatus");
						if(caseStatus2!=null && caseStatus2.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
						{
							EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, (String) lparamMap.get("CASE_ID"));
							
							if(ehfSurplusTdsClaim!=null)
							{
								if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
								{
							ehfCaseClaim.setHospPctAmt(0.0);
							ehfCaseClaim.setTdsHospPctAmt(ehfSurplusTdsClaim.getTdsHospPctAmt());
								}
								else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
								{
									ehfCaseClaim.setHospPctAmt(ehfSurplusTdsClaim.getHospPctAmt());																	
									ehfCaseClaim.setTdsHospPctAmt(0.0);

								}
								else
								{
									System.out.println("TDS Flag not set for case id "+(String) lparamMap.get("CASE_ID"));		
									ehfCaseClaim.setHospPctAmt(0.0);																	
									ehfCaseClaim.setTdsHospPctAmt(0.0);
									logger.error("TDS Flag not set for case id "+(String) lparamMap.get("CASE_ID"));		
									EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, (String) lparamMap.get("CASE_ID"));
									if(ehfCase!=null)
									{
										ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
										ehfCase=genericDao.save(ehfCase);
										
									}
								}
							}
							else
							{
								System.out.println("No record present in EhfSurplusTdsClaim with case id "+(String) lparamMap.get("CASE_ID"));	
								logger.error("No record present in EhfSurplusTdsClaim with case id "+(String) lparamMap.get("CASE_ID"));	
								EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, (String) lparamMap.get("CASE_ID"));
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
								ehfCaseClaim.setHospPctAmt(0.0);																	
								ehfCaseClaim.setTdsHospPctAmt(0.0);
								
								}
						}
						else
						{
						ehfCaseClaim.setHospPctAmt(lHospAmt);						
						ehfCaseClaim.setTdsHospPctAmt(lTDSHospAmt);
						}
						ehfCaseClaim.setTrustPctAmt(lTrustAmt);
						ehfCaseClaim.setTdsPctAmt(lTDSAmt);
						ehfCaseClaim.setTdsTaxAmt(lTaxAmt);
						ehfCaseClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));						
						ehfCaseClaim.setLstUpdUsr((String)lparamMap.get("CRTUSER"));
						//Chandana - 6444 - 04-02-2025 below hub,spoke and agency amount
						ehfCaseClaim.setHubAmt(lHubAmt);
						ehfCaseClaim.setSpokeAmt(lSpokeAmt);
						ehfCaseClaim.setAgencyPctAmt(lAgencyPctAmt);
						ehfCaseClaim.setAgencyAmt(lAgencyAmt);
						ehfCaseClaim.setHmAmt(lHubMnt);
						ehfCaseClaim.setSmAmt(lSpokeMnt);
					}
					ehfCaseClaim = genericDao.save(ehfCaseClaim);
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
					//Chandana - 6444 - 04-02-2025
					if(lhubspokeFlag !=null && !"".equals(lhubspokeFlag) && lHubAmt > ClaimsConstants.ZERO_VAL){
		            	isInsert = processHubSpokePayments(lparamMap);
		            	isInsert = processTDSPayments(lparamMap);
		            }
				}
			}
		} else {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
		}
		return isInsert;
	}

//Chandana - 6444 - 04-02-2025
	/**
	    * 
	    * @param lparamMap
	    * @return
	    * @Description This method is used for inserting Hub and Spoke amount of Dialysis cases in governament hospitals.
	    */
	   public boolean processHubSpokePayments(HashMap lparamMap) throws Exception
	   {
		   boolean isResult = ClaimsConstants.BOOLEAN_FALSE;
           boolean isInsert=ClaimsConstants.BOOLEAN_FALSE;
           String lStrCaseId=(String)lparamMap.get("CASE_ID");            
           String lStrHubPaymentId=ClaimsConstants.EMPTY;
           String lStrAgencyPaymentId=ClaimsConstants.EMPTY;
           String lStrHubMntId=ClaimsConstants.EMPTY;
           String lStrSpokeMntId=ClaimsConstants.EMPTY;
           lStrHubPaymentId=lStrCaseId+"/HUB";
           lStrAgencyPaymentId=lStrCaseId+"/AGENCY";
           lStrHubMntId =lStrCaseId+"/HM";
           lStrSpokeMntId =lStrCaseId+"/SM";
           String lStrRemarks=ClaimsConstants.CLAIM_SENT_RMK;
           String lStrStatusId=(String) lparamMap.get("nextCaseStatus");
           String lSentStatus=ClaimsConstants.SENT;
           Double hubAmt = Double.parseDouble(lparamMap.get("hubAmt").toString());
           Double spokeAmt = Double.parseDouble(lparamMap.get("spokeAmt").toString());
           Double agencyAmt = Double.parseDouble(lparamMap.get("agencyPctAmt").toString());
           Double hubMnt = Double.parseDouble(lparamMap.get("hubMaintenance").toString());
           Double spokeMnt = Double.parseDouble(lparamMap.get("spokeMaintenance").toString());
           Date currentDate = (Date)lparamMap.get("currentDate");
           EhfCaseClaimHubSpokePayment hubPayment = null;
           hubPayment = genericDao.findById(EhfCaseClaimHubSpokePayment.class,String.class,lStrHubPaymentId);
           if(hubPayment == null) 
           {
        	   hubPayment = new EhfCaseClaimHubSpokePayment();
        	   hubPayment.setHubspokePaymentId(lStrHubPaymentId);
           }        
    	   hubPayment.setCaseId(lStrCaseId);
    	   hubPayment.setPaymentStatus(lStrStatusId);
    	   hubPayment.setClaimAmount(hubAmt);
    	   hubPayment.setRemarks(lStrRemarks);
    	   hubPayment.setTransStatus(lSentStatus);
    	   hubPayment.setCrtDt(currentDate);
    	   hubPayment.setCrtUsr((String)lparamMap.get("CRTUSER"));
    	   hubPayment.setTimeMilSec(ClaimsConstants.ZERO_DBL_VAL);
    	   hubPayment = genericDao.save(hubPayment);
           if(hubPayment != null && hubPayment.getHubspokePaymentId().length() >0)
           {
               isInsert = ClaimsConstants.BOOLEAN_TRUE;
           }
           else
           {
               isInsert = ClaimsConstants.BOOLEAN_FALSE;
           }
           if(isInsert)
           {
        	   int i=0;
               String lStrActId=(String)lparamMap.get("STAT_ID");
               String lStrQuery = "SELECT ACT_ORDER ACTORDER,ACT_ID ACTID FROM EHF_HUBSPOKE_AUDIT WHERE hubspoke_payment_id=? ORDER BY ACT_ORDER DESC ";
                String[] arg = new String[1];
                arg[0] = lStrHubPaymentId;
                List<ClaimsFlowVO> auditList = new ArrayList<ClaimsFlowVO>();
                String lStrPrevStatId=""; Long lintActOrder=0L;
                auditList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrQuery.toString(),arg);
                if(auditList.size()>0) 
                {
                    lintActOrder = Long.parseLong(auditList.get(i).getACTORDER().toString());
                    lStrPrevStatId = auditList.get(i).getACTID();
                    lStrPrevStatId = (auditList==null)?"":lStrPrevStatId;
                }
               if(!lStrPrevStatId.equals(lStrActId))
               {
                  lintActOrder=lintActOrder + 1;
                  EhfHubSpokeAudit ehfHubspokeAudit = new EhfHubSpokeAudit();
                  EhfHubspokeAuditPK ehfHubspokeAuditPK = new EhfHubspokeAuditPK();
                  ehfHubspokeAuditPK.setHubspokePaymentId(lStrHubPaymentId);
                  ehfHubspokeAuditPK.setActOrder(lintActOrder);
                   ehfHubspokeAudit.setId(ehfHubspokeAuditPK);
                   ehfHubspokeAudit.setActId(lStrStatusId);
                   ehfHubspokeAudit.setRemarks(lStrRemarks);
                   ehfHubspokeAudit.setCrtDt(currentDate);
                   ehfHubspokeAudit.setCrtUsr((String)lparamMap.get("CRTUSER"));
                   ehfHubspokeAudit.setLangId("en_US");
                   ehfHubspokeAudit.setAcctsConsumed(ClaimsConstants.N);
                   genericDao.save(ehfHubspokeAudit);
               }
               isResult=ClaimsConstants.BOOLEAN_TRUE;
           }
           EhfCaseClaimHubSpokePayment spokePayment = null;
           spokePayment = genericDao.findById(EhfCaseClaimHubSpokePayment.class,String.class,lStrAgencyPaymentId);
           if(spokePayment == null) 
           {
        	   spokePayment = new EhfCaseClaimHubSpokePayment();
        	   spokePayment.setHubspokePaymentId(lStrAgencyPaymentId);
           }        
    	   spokePayment.setCaseId(lStrCaseId);
    	   spokePayment.setPaymentStatus(lStrStatusId);
    	   spokePayment.setClaimAmount(agencyAmt);
    	   spokePayment.setRemarks(lStrRemarks);
    	   spokePayment.setTransStatus(lSentStatus);
    	   spokePayment.setCrtDt(currentDate);
    	   spokePayment.setCrtUsr((String)lparamMap.get("CRTUSER"));
    	   spokePayment.setTimeMilSec(ClaimsConstants.ZERO_DBL_VAL);
    	   spokePayment = genericDao.save(spokePayment);
           if(spokePayment != null && spokePayment.getHubspokePaymentId().length() >0)
           {
               isInsert = ClaimsConstants.BOOLEAN_TRUE;
           }
           else
           {
               isInsert = ClaimsConstants.BOOLEAN_FALSE;
           }
           if(isInsert)
           {
        	   int i=0;
               String lStrActId=(String)lparamMap.get("STAT_ID");
               String lStrQuery = "SELECT ACT_ORDER ACTORDER,ACT_ID ACTID FROM EHF_HUBSPOKE_AUDIT WHERE hubspoke_payment_id=? ORDER BY ACT_ORDER DESC ";
                String[] arg = new String[1];
                arg[0] = lStrAgencyPaymentId;
                List<ClaimsFlowVO> auditList = new ArrayList<ClaimsFlowVO>();
                String lStrPrevStatId=""; Long lintActOrder=0L;
                auditList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrQuery.toString(),arg);
                if(auditList.size()>0) 
                {
                    lintActOrder = Long.parseLong(auditList.get(i).getACTORDER().toString());
                    lStrPrevStatId = auditList.get(i).getACTID();
                    lStrPrevStatId = (auditList==null)?"":lStrPrevStatId;
                }
               if(!lStrPrevStatId.equals(lStrActId))
               {
                  lintActOrder=lintActOrder + 1;
                  EhfHubSpokeAudit ehfHubspokeAudit = new EhfHubSpokeAudit();
                  EhfHubspokeAuditPK ehfHubspokeAuditPK = new EhfHubspokeAuditPK();
                  ehfHubspokeAuditPK.setHubspokePaymentId(lStrAgencyPaymentId);
                  ehfHubspokeAuditPK.setActOrder(lintActOrder);
                   ehfHubspokeAudit.setId(ehfHubspokeAuditPK);
                   ehfHubspokeAudit.setActId(lStrStatusId);
                   ehfHubspokeAudit.setRemarks(lStrRemarks);
                   ehfHubspokeAudit.setCrtDt(currentDate);
                   ehfHubspokeAudit.setCrtUsr((String)lparamMap.get("CRTUSER"));
                   ehfHubspokeAudit.setLangId("en_US");
                   ehfHubspokeAudit.setAcctsConsumed(ClaimsConstants.N);
                   genericDao.save(ehfHubspokeAudit);
               }
               isResult=ClaimsConstants.BOOLEAN_TRUE;
           }
           EhfCaseClaimHubSpokePayment hubMnts = null;
           hubMnts = genericDao.findById(EhfCaseClaimHubSpokePayment.class,String.class,lStrHubMntId);
           if(hubMnts == null) 
           {
        	   hubMnts = new EhfCaseClaimHubSpokePayment();
        	   hubMnts.setHubspokePaymentId(lStrHubMntId);
           }        
           hubMnts.setCaseId(lStrCaseId);
           hubMnts.setPaymentStatus(lStrStatusId);
           hubMnts.setClaimAmount(hubMnt);
           hubMnts.setRemarks(lStrRemarks);
           hubMnts.setTransStatus(lSentStatus);
           hubMnts.setCrtDt(currentDate);
           hubMnts.setCrtUsr((String)lparamMap.get("CRTUSER"));
           hubMnts.setTimeMilSec(ClaimsConstants.ZERO_DBL_VAL);
           hubMnts = genericDao.save(hubMnts);
           if(hubMnts != null && hubMnts.getHubspokePaymentId().length() >0)
           {
               isInsert = ClaimsConstants.BOOLEAN_TRUE;
           }
           else
           {
               isInsert = ClaimsConstants.BOOLEAN_FALSE;
           }
           if(isInsert)
           {
        	   int i=0;
               String lStrActId=(String)lparamMap.get("STAT_ID");
               String lStrQuery = "SELECT ACT_ORDER ACTORDER,ACT_ID ACTID FROM EHF_HUBSPOKE_AUDIT WHERE hubspoke_payment_id=? ORDER BY ACT_ORDER DESC ";
                String[] arg = new String[1];
                arg[0] = lStrHubMntId;
                List<ClaimsFlowVO> auditList = new ArrayList<ClaimsFlowVO>();
                String lStrPrevStatId=""; Long lintActOrder=0L;
                auditList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrQuery.toString(),arg);
                if(auditList.size()>0) 
                {
                    lintActOrder = Long.parseLong(auditList.get(i).getACTORDER().toString());
                    lStrPrevStatId = auditList.get(i).getACTID();
                    lStrPrevStatId = (auditList==null)?"":lStrPrevStatId;
                }
               if(!lStrPrevStatId.equals(lStrActId))
               {
                  lintActOrder=lintActOrder + 1;
                  EhfHubSpokeAudit ehfHubspokeAudit = new EhfHubSpokeAudit();
                  EhfHubspokeAuditPK ehfHubspokeAuditPK = new EhfHubspokeAuditPK();
                  ehfHubspokeAuditPK.setHubspokePaymentId(lStrHubMntId);
                  ehfHubspokeAuditPK.setActOrder(lintActOrder);
                   ehfHubspokeAudit.setId(ehfHubspokeAuditPK);
                   ehfHubspokeAudit.setActId(lStrStatusId);
                   ehfHubspokeAudit.setRemarks(lStrRemarks);
                   ehfHubspokeAudit.setCrtDt(currentDate);
                   ehfHubspokeAudit.setCrtUsr((String)lparamMap.get("CRTUSER"));
                   ehfHubspokeAudit.setLangId("en_US");
                   ehfHubspokeAudit.setAcctsConsumed(ClaimsConstants.N);
                   genericDao.save(ehfHubspokeAudit);
               }
               isResult=ClaimsConstants.BOOLEAN_TRUE;
           }
           EhfCaseClaimHubSpokePayment spokeMnts = null;
           spokeMnts = genericDao.findById(EhfCaseClaimHubSpokePayment.class,String.class,lStrSpokeMntId);
           if(spokeMnts == null) 
           {
        	   spokeMnts = new EhfCaseClaimHubSpokePayment();
        	   spokeMnts.setHubspokePaymentId(lStrSpokeMntId);
           }        
           spokeMnts.setCaseId(lStrCaseId);
           spokeMnts.setPaymentStatus(lStrStatusId);
           spokeMnts.setClaimAmount(spokeMnt);
           spokeMnts.setRemarks(lStrRemarks);
           spokeMnts.setTransStatus(lSentStatus);
           spokeMnts.setCrtDt(currentDate);
           spokeMnts.setCrtUsr((String)lparamMap.get("CRTUSER"));
           spokeMnts.setTimeMilSec(ClaimsConstants.ZERO_DBL_VAL);
           spokeMnts = genericDao.save(spokeMnts);
           if(spokeMnts != null && spokeMnts.getHubspokePaymentId().length() >0)
           {
               isInsert = ClaimsConstants.BOOLEAN_TRUE;
           }
           else
           {
               isInsert = ClaimsConstants.BOOLEAN_FALSE;
           }
           if(isInsert)
           {
        	   int i=0;
               String lStrActId=(String)lparamMap.get("STAT_ID");
               String lStrQuery = "SELECT ACT_ORDER ACTORDER,ACT_ID ACTID FROM EHF_HUBSPOKE_AUDIT WHERE hubspoke_payment_id=? ORDER BY ACT_ORDER DESC ";
                String[] arg = new String[1];
                arg[0] = lStrSpokeMntId;
                List<ClaimsFlowVO> auditList = new ArrayList<ClaimsFlowVO>();
                String lStrPrevStatId=""; Long lintActOrder=0L;
                auditList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,lStrQuery.toString(),arg);
                if(auditList.size()>0) 
                {
                    lintActOrder = Long.parseLong(auditList.get(i).getACTORDER().toString());
                    lStrPrevStatId = auditList.get(i).getACTID();
                    lStrPrevStatId = (auditList==null)?"":lStrPrevStatId;
                }
               if(!lStrPrevStatId.equals(lStrActId))
               {
                  lintActOrder=lintActOrder + 1;
                  EhfHubSpokeAudit ehfHubspokeAudit = new EhfHubSpokeAudit();
                  EhfHubspokeAuditPK ehfHubspokeAuditPK = new EhfHubspokeAuditPK();
                  ehfHubspokeAuditPK.setHubspokePaymentId(lStrSpokeMntId);
                  ehfHubspokeAuditPK.setActOrder(lintActOrder);
                   ehfHubspokeAudit.setId(ehfHubspokeAuditPK);
                   ehfHubspokeAudit.setActId(lStrStatusId);
                   ehfHubspokeAudit.setRemarks(lStrRemarks);
                   ehfHubspokeAudit.setCrtDt(currentDate);
                   ehfHubspokeAudit.setCrtUsr((String)lparamMap.get("CRTUSER"));
                   ehfHubspokeAudit.setLangId("en_US");
                   ehfHubspokeAudit.setAcctsConsumed(ClaimsConstants.N);
                   genericDao.save(ehfHubspokeAudit);
               }
               isResult=ClaimsConstants.BOOLEAN_TRUE;
           }
       return isResult;
	   }
	/*
	 * @param lparamMap - HashMap
	 * @Desc used to Calculate the Claim Percentage for cases(having Slab concept included)
	 * @return true, if successful
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	@Override
	public boolean calculateClaimPercentageSurplus(HashMap lparamMap) throws Exception {

		StringBuffer lStrBuffer = new StringBuffer();
		Map lResMap = new HashMap();
		boolean isInsert = false;
		String paymentType = (String) lparamMap.get("PaymentType");
		List<ClaimsFlowVO> percentatgeList = new ArrayList<ClaimsFlowVO>();
		String[] arg = new String[3];
		String cochYn=null;
		
		arg[0] = (String) lparamMap.get("CASE_ID");
		
		/*Bifurcation changes by venkatesh*/
		String schemeId= (String)lparamMap.get("SCHEME_ID");;
		arg[2]=schemeId;

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
			.append(" ,EHF_CASE_FOLLOWUP_CLAIM ab , ehf_case_therapy ma, ");
			
			StringBuffer query=new StringBuffer();
			
			List<ClaimsFlowVO> lst=new ArrayList<ClaimsFlowVO>();
			query.append(" select a.cochlear_Yn as COCHLEARYN from ehf_case_followup_claim a where a.case_followup_id='"+arg[0]+"'");
			lst=genericDao.executeSQLQueryList(ClaimsFlowVO.class,query.toString());
			
			if(lst!=null && lst.size()>0 && lst.get(0).getCOCHLEARYN()!=null && ("Y").equalsIgnoreCase(lst.get(0).getCOCHLEARYN()))
			{
				cochYn=lst.get(0).getCOCHLEARYN();
			}
		
			if(cochYn!=null && ("Y").equalsIgnoreCase(cochYn))
			{
				lStrBuffer
				.append("  ehfm_coch_followup_packages mb,Ehf_Cochlear_Followup aak  ");
			}
			else
			{
				lStrBuffer
				.append(" ehfm_followup_packages mb  ");
			}
		
		
		
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
					.append(" and ab.case_followup_id = ?  and ae.claim_type=? ");
			
			
			if(cochYn!=null && ("Y").equalsIgnoreCase(cochYn))
			{
				lStrBuffer
				.append(" and ab.case_id=ma.case_id and aa.case_id=ma.case_id and ma.icd_proc_code=mb.cochlear_surgery_id and aa.scheme_id=mb.scheme_id and mb.icd_code_proc_fp=ae.surgery_id      ");
				lStrBuffer
				.append("  and ab.case_followup_id=aak.cochlear_followup_id and aak.followup_proc=ae.surgery_id  ");
			}
			else
			{
				lStrBuffer
				.append(" and ab.case_id=ma.case_id and aa.case_id=ma.case_id and ma.icd_proc_code=mb.surgery_id and aa.scheme_id=mb.scheme_id and mb.icd_code_proc_fp=ae.surgery_id      ");
			}

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
		
		if(schemeId!=null && !("").equalsIgnoreCase(schemeId))
		{
			lStrBuffer.append(" and ae.scheme=aah.scheme and ae.scheme=? ");
		}

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
				lResMap.put("tds_hosp_percent", percentatgeList.get(i)
						.getTDSHOSPPERCENT());								
				lResMap.put("trust_percent", percentatgeList.get(i)
						.getTRUSTPERCENT());
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
		}

		lparamMap.put("ResMap", lResMap);
        // To get the TDS/RF/Hospital Amount
		Map lPercentAmtMap = getTDSDeductionDtls(lparamMap);

		if (ClaimsConstants.TRUE.equalsIgnoreCase((String) lPercentAmtMap
				.get("UpdSuccess"))) {
			double lTrustAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TrustAmt"));
			double lHospAmt=0.0;
			double lTDSHospAmt=0.0;
		
			 lHospAmt = Double.parseDouble((String) lPercentAmtMap
					.get("HospAmt"));
			 lTDSHospAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TDSHospAmt"));
			
			double lTDSAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TDSAmt"));
			String lStrTDSActiveFlag = (String) lPercentAmtMap
					.get("TDSActiveFlag");
			double lTaxAmt = Double.parseDouble((String) lPercentAmtMap
					.get("lTaxAmt"));
			String lStrRFActiveFlag = (String) lPercentAmtMap
					.get("RFActiveFlag");
		
			lparamMap.put("lhospAmt", Double.toString(lHospAmt));//Hosp amount after deducting RF Amount
			lparamMap.put("lhospTDSAmt", Double.toString(lTDSHospAmt)); //Hosp amount after deducting TDS Amount
			lparamMap.put("TrustAmt", Double.toString(lTrustAmt));//RF Amount
			lparamMap.put("TDSAmt", Double.toString(lTDSAmt)); //TDS Amount
			lparamMap.put("TDSActiveFlag", lStrTDSActiveFlag); //TDS Flag
			lparamMap.put("RFActiveFlag", lStrRFActiveFlag); //RF Flag
			isInsert=ClaimsConstants.BOOLEAN_TRUE;
			
		} else {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
		}
		return isInsert;
	}
	/*
	 * @param lparamMap - HashMap
	 * @Desc used to Calculate the Claim Percentage for cases(without Slab concept)
	 * @return true, if successful
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  boolean  calculateClaimPercentageNew(HashMap lparamMap)
			throws Exception {

		StringBuffer lStrBuffer = new StringBuffer();
        Map lResMap = new HashMap();
		boolean isInsert = false;		
	
		String paymentType = (String) lparamMap.get("PaymentType");
		List<ClaimsFlowVO> percentatgeList = new ArrayList<ClaimsFlowVO>();
		String[] arg = new String[1];
		arg[0] = (String) lparamMap.get("CASE_ID");

		lStrBuffer
				.append(" select distinct ad.hosp_type as HOSPTYPE,ad.hosp_id as HOSPITALID,ab.tot_claim_amt as CLAIMAMOUNT ");
		lStrBuffer.append(" from ehf_case aa,ehfm_hospitals ad ");

		if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
			lStrBuffer
					.append(" ,EHF_CASE_FOLLOWUP_CLAIM ab  ");
		} else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
			lStrBuffer
					.append(" ,ehf_erroneous_claim ab ,ehf_case_claim ab1 ,ehf_case_therapy ac ");
		} else {
			lStrBuffer.append(" ,ehf_case_claim ab ,ehf_case_therapy ac ");
		}

		lStrBuffer.append(" where  ad.hosp_id=aa.case_hosp_code ");

		/*
		 * for followup there will be only one record for each hospital in
		 * EHFM_HOSP_SURG_PER
		 */
		if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
			lStrBuffer
					.append(" and ab.case_followup_id = ? ");
		} else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
			lStrBuffer
					.append(" and ab.err_claim_id = ? and ab.case_id = aa.case_id and aa.case_id=ab1.case_id  and ac.case_id=aa.case_id ");
		} else {
			lStrBuffer
					.append("  and aa.case_id=ab.case_id  and ac.case_id=aa.case_id and aa.case_id = ?  ");
		}

		lStrBuffer.append(" and ab.Tot_claim_amt is not null ");

		// Here we get all the percentages (TDS or Revolving fund) to be
		// deducted and whether they are active or not (from TDS master)

		percentatgeList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,
				lStrBuffer.toString(), arg);

		if (percentatgeList.size() > 0) {
			for (int i = 0; i < percentatgeList.size(); i++) {

				String hospType = percentatgeList.get(i).getHOSPTYPE() + "";
				String hospCode = percentatgeList.get(i).getHOSPITALID();
				String tdsActiveYN = "N", rfActiveYN = "N", hospPer = "0", trustPer = "0", tdsHospPer = "0", tdsPer = "0";
				if (hospType != null && hospType.equalsIgnoreCase("C")) {
					tdsActiveYN = "Y";
					tdsHospPer = ClaimsConstants.TDS_HOSP_PERCENT; 
					tdsPer = ClaimsConstants.TDS_PERCENT; 
				} else if (hospType != null && hospType.equalsIgnoreCase("G")) {
					if(hospCode!=null && (hospCode.equalsIgnoreCase("EHS69") ||  hospCode.equalsIgnoreCase("EHS34") || hospCode.equalsIgnoreCase("EHS13"))){
						tdsActiveYN = "Y";
						tdsHospPer = ClaimsConstants.TDS_HOSP_PERCENT; 
						tdsPer = ClaimsConstants.TDS_PERCENT; 
						hospType = "C";
					}else{
					rfActiveYN = "Y";
					hospPer = ClaimsConstants.RF_HOSP_PER; 
					trustPer = ClaimsConstants.RF_PER;
					}
				}
				lResMap.put("tds_active_yn", tdsActiveYN);
				lResMap.put("RF_active_yn", rfActiveYN);
				lResMap.put("hosp_percent", hospPer);
				lResMap.put("trust_percent", trustPer);
				lResMap.put("tds_hosp_percent", tdsHospPer);
				lResMap.put("tds_percent", tdsPer);
				lResMap.put("hosp_type", hospType);
				lResMap.put("claim_amount", percentatgeList.get(i)
						.getCLAIMAMOUNT());
				lResMap.put("direct_deduction", "N");
				lResMap.put("slab_amount", "0");
				lResMap.put("total_amt", "0");
				lResMap.put("tds_amt", "0");
				lResMap.put("Hosp_Deduct_Id", "");
				lResMap.put("rqst_active_yn", "N");
				lResMap.put("SlabAmt_applicable", "N");
				lResMap.put("hosp_id", percentatgeList.get(i).getHOSPITALID());
			}
		}

		lparamMap.put("ResMap", lResMap);
        // To get the TDS/RF/Hospital Amount according to Percentage
		Map lPercentAmtMap = getTDSDeductionDtlsNew(lparamMap);

		if (ClaimsConstants.TRUE.equalsIgnoreCase((String) lPercentAmtMap
				.get("UpdSuccess"))) {
			double lTrustAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TrustAmt"));
			double lHospAmt = Double.parseDouble((String) lPercentAmtMap
					.get("HospAmt"));
			double lTDSHospAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TDSHospAmt"));
			double lTDSAmt = Double.parseDouble((String) lPercentAmtMap
					.get("TDSAmt"));
			String lStrTDSActiveFlag = (String) lPercentAmtMap
					.get("TDSActiveFlag");
			double lTaxAmt = Double.parseDouble((String) lPercentAmtMap
					.get("lTaxAmt"));
			String lStrRFActiveFlag = (String) lPercentAmtMap
					.get("RFActiveFlag");
			lparamMap.put("Claim_Amount", Double.toString(lTrustAmt));
			lparamMap.put("TrustAmt", Double.toString(lTrustAmt));
			lparamMap.put("TDSAmt", Double.toString(lTDSAmt));
			lparamMap.put("TDSActiveFlag", lStrTDSActiveFlag);
			lparamMap.put("RFActiveFlag", lStrRFActiveFlag);

			lStrBuffer = new StringBuffer();

			EhfCaseFollowupClaim ehfCaseFollowupClaim = null;
			EhfErroneousClaim ehfErroneousClaim = null;
			EhfCaseClaim ehfCaseClaim = null;

			isInsert = ClaimsConstants.BOOLEAN_TRUE;

			if (ClaimsConstants.Y.equalsIgnoreCase(lStrRFActiveFlag)) // If RF active																		
			{
				if (isInsert && lTrustAmt > ClaimsConstants.ZERO_VAL) {
					isInsert = ClaimsConstants.BOOLEAN_FALSE;
					isInsert = processTrustPayments(lparamMap); //Processing RF payment
				}
			}
			if (ClaimsConstants.Y.equalsIgnoreCase(lStrTDSActiveFlag)) // If TDS active
			{
				if (isInsert && lTDSAmt > ClaimsConstants.ZERO_DBL_VAL) {
					isInsert = ClaimsConstants.BOOLEAN_FALSE;
					isInsert = processTDSPayments(lparamMap); //processing TDS Payment
				}
			}

			if (isInsert) {
                //For Followup
				if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
					String caseFollowupId = (String) lparamMap
							.get("CASE_FOLLOWUP_ID");
					ehfCaseFollowupClaim = genericDao.findById(
							EhfCaseFollowupClaim.class, String.class,
							caseFollowupId);
					if (ehfCaseFollowupClaim != null) {
						ehfCaseFollowupClaim.setHospPctAmt(lHospAmt);
						ehfCaseFollowupClaim.setTrustPctAmt(lTrustAmt);
						ehfCaseFollowupClaim.setTdsPctAmt(lTDSAmt);
						ehfCaseFollowupClaim.setTdsHospPctAmt(lTDSHospAmt);
						ehfCaseFollowupClaim.setTdsTaxAmt(lTaxAmt);
						ehfCaseFollowupClaim = genericDao
								.save(ehfCaseFollowupClaim);
					}
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
				}
                //For Err-Claim
				else if (ClaimsConstants.ErroneousClaim
						.equalsIgnoreCase(paymentType)) {
					ehfErroneousClaim = genericDao.findById(
							EhfErroneousClaim.class, String.class,
							(String) lparamMap.get("CASE_ID") /* + "/E" */);
					if (ehfErroneousClaim != null) {
						ehfErroneousClaim.setHospPctAmt(lHospAmt);
						ehfErroneousClaim.setTrustPctAmt(lTrustAmt);
						ehfErroneousClaim.setTdsPctAmt(lTDSAmt);
						ehfErroneousClaim.setTdsHospPctAmt(lTDSHospAmt);
						ehfErroneousClaim.setTdsTaxAmt(lTaxAmt);
					}
					ehfErroneousClaim = genericDao.save(ehfErroneousClaim);
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
				} else {
					//For Normal Claim
					ehfCaseClaim = genericDao.findById(EhfCaseClaim.class,
							String.class, (String) lparamMap.get("CASE_ID"));
					if (ehfCaseClaim != null) {
						ehfCaseClaim.setHospPctAmt(lHospAmt);
						ehfCaseClaim.setTrustPctAmt(lTrustAmt);
						ehfCaseClaim.setTdsPctAmt(lTDSAmt);
						ehfCaseClaim.setTdsHospPctAmt(lTDSHospAmt);
						ehfCaseClaim.setTdsTaxAmt(lTaxAmt);
						
						ehfCaseClaim.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));

					}
					ehfCaseClaim = genericDao.save(ehfCaseClaim);
					isInsert = ClaimsConstants.BOOLEAN_TRUE;
				}
			}
		} else {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
		}
		return isInsert;
	}

	/**
	 * Gets the tDS/RF deduction dtls.
	 * 
	 * @param HashMap
	 *            the lparam map
	 * @return the tDS deduction dtls (HashMap)
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Transactional
	public Map getTDSDeductionDtlsNew(HashMap lParamMap) throws Exception {
		
		StringBuffer lStrBuffer = new StringBuffer();
		String lStrClaimAmt = ClaimsConstants.EMPTY,  lStrTDSActive = ClaimsConstants.EMPTY, lStrRFActive = ClaimsConstants.EMPTY;
        String lStrTotalAmt = ClaimsConstants.EMPTY, lStrTotalTDSAmt = ClaimsConstants.EMPTY;

		double TotalAmt = 0, TotalTDSAmt = 0;
		double lHospPercentage = ClaimsConstants.ZERO_DBL_VAL, lTrustPercentage = ClaimsConstants.ZERO_DBL_VAL, lHospAmt = ClaimsConstants.ZERO_DBL_VAL, lTrustAmt = ClaimsConstants.ZERO_DBL_VAL, claimAmt = ClaimsConstants.ZERO_DBL_VAL, lTDSPercentage = ClaimsConstants.ZERO_DBL_VAL, lTDSHospPercentage = ClaimsConstants.ZERO_DBL_VAL, lTDSHospAmt = ClaimsConstants.ZERO_DBL_VAL, lTDSAmt = ClaimsConstants.ZERO_DBL_VAL;
		double lTaxAmt = ClaimsConstants.ZERO_DBL_VAL;
		Map lTDSDeductMap = new HashMap();
		Map lResMap = (HashMap) lParamMap.get("ResMap");
		//If no records are present in haspmap regarding Percentage then we will take hosp_amount as 100% and TDS/RF as 0%
		String ldataTable = " EhfCaseClaim ";
		String ldataCol = " caseId=? ";
		String lselectColumn = " totClaimAmt as CLAIMAMOUNT ";
		String paymentType = (String) lParamMap.get("PaymentType");
																	
		String lStrInsert = ClaimsConstants.FALSE;
		String[] arg = null;

		List<ClaimsFlowVO> claimAmountList = new ArrayList<ClaimsFlowVO>();
     
		if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
			ldataTable = " EhfCaseFollowupClaim ";
			ldataCol = " caseFollowupId=? ";
			lselectColumn = " totClaimAmt as CLAIMAMOUNT ";
		} else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType))
		{
			ldataTable = " EhfErroneousClaim ";
			ldataCol = " errClaimId=? ";
			lselectColumn = " totClaimAmt as CLAIMAMOUNT ";

		}
		
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

			lStrClaimAmt = lResMap.get("claim_amount").toString();
			claimAmt = Double.parseDouble(lStrClaimAmt);
			
			lStrTotalAmt = lResMap.get("total_amt").toString();
			if (lStrTotalAmt != null)
				TotalAmt = Double.parseDouble(lStrTotalAmt);
			lStrTotalTDSAmt = lResMap.get("tds_amt").toString();
			if (lStrTotalTDSAmt != null)
				TotalTDSAmt = Double.parseDouble(lStrTotalTDSAmt);
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
			}
		}
        //If TDS is active
		if ("Y".equalsIgnoreCase(lStrTDSActive)) {
			lTaxAmt = (claimAmt * lTDSPercentage) / 100;
			lTDSAmt = lTaxAmt;

			if (((lTDSAmt * 10) % 10) > 0)
				lTDSAmt = new Double((int) lTDSAmt + 1);

			lHospAmt = claimAmt - lTDSAmt;
			TotalAmt = TotalAmt + lHospAmt;
			TotalTDSAmt = TotalTDSAmt + lTDSAmt;
			lParamMap.put("HospCmpAmt", lHospAmt);
			lStrInsert = ClaimsConstants.TRUE;
		} else if (ClaimsConstants.Y.equalsIgnoreCase(lStrRFActive)) {
			 //If RF is active
			lHospAmt = (claimAmt * lHospPercentage) / 100;
			lTrustAmt = (claimAmt * lTrustPercentage) / 100;
			lStrInsert = ClaimsConstants.TRUE;
		} else if ((!ClaimsConstants.Y.equalsIgnoreCase(lStrRFActive))
				&& (!ClaimsConstants.Y.equalsIgnoreCase(lStrTDSActive))) {
			//If both are inactive hosp_amt 100%
			lHospAmt = claimAmt;
			lTrustAmt = ClaimsConstants.ZERO_DBL_VAL;
			lTDSHospAmt = ClaimsConstants.ZERO_DBL_VAL;
			lTaxAmt = ClaimsConstants.ZERO_DBL_VAL;
			lTDSAmt = ClaimsConstants.ZERO_DBL_VAL;
			lParamMap.put("HospCmpAmt", lHospAmt);
			lStrInsert = ClaimsConstants.FALSE;
		}
       if (ClaimsConstants.Y.equalsIgnoreCase(lStrTDSActive)) {
			lTDSHospAmt = lHospAmt;
			lHospAmt = ClaimsConstants.ZERO_DBL_VAL;
		}

		lTDSDeductMap.put("HospAmt", new Double(lHospAmt).toString());
		lTDSDeductMap.put("TrustAmt", new Double(lTrustAmt).toString());
		lTDSDeductMap.put("TDSHospAmt", new Double(lTDSHospAmt).toString());
		lTDSDeductMap.put("TDSAmt", new Double(lTDSAmt).toString());
		lTDSDeductMap.put("lTaxAmt", new Double(lTaxAmt).toString());
		lTDSDeductMap.put("TDSActiveFlag", lStrTDSActive);
		lTDSDeductMap.put("RFActiveFlag", lStrRFActive);
		lTDSDeductMap.put("UpdSuccess", lStrInsert);

		return lTDSDeductMap;
	}
	@Transactional
	public Map getTDSDeductionDtls(HashMap lParamMap) throws Exception {
		//DecimalFormat myFormatter = new DecimalFormat("0");
		StringBuffer lStrBuffer = new StringBuffer();
		String temp = ClaimsConstants.EMPTY, lStrClaimAmt = ClaimsConstants.EMPTY, lStrHospType = ClaimsConstants.EMPTY, lStrTDSActive = ClaimsConstants.EMPTY, lStrRFActive = ClaimsConstants.EMPTY;//, lStrRevDefActive = ClaimsConstants.EMPTY
		String lStrDirectDeduction = ClaimsConstants.EMPTY, lStrTotalAmt = null, lStrTotalTDSAmt = null;
		String lStrHospDeductId = null, lStrSlabAmt = null, lStrDoneFlag = null, lStrHospId = ClaimsConstants.EMPTY;
		String hubSpokeFlag=ClaimsConstants.EMPTY;//Chandana - 6444 - 04-02-2025 
		double hospPecent = ClaimsConstants.ZERO_DBL_VAL, agencyAmt=ClaimsConstants.ZERO_DBL_VAL,hubAmt =ClaimsConstants.ZERO_DBL_VAL,spokeAmt =ClaimsConstants.ZERO_DBL_VAL,pkgAmt =ClaimsConstants.ZERO_DBL_VAL;//Chandana - 6444 - 04-02-2025
		double TotalAmt = 0, TotalTDSAmt = 0, SlabAmt = 0;
		double lHospPercentage = ClaimsConstants.ZERO_DBL_VAL, lTrustPercentage = ClaimsConstants.ZERO_DBL_VAL, lHospAmt = ClaimsConstants.ZERO_DBL_VAL, lTrustAmt = ClaimsConstants.ZERO_DBL_VAL, claimAmt = ClaimsConstants.ZERO_DBL_VAL, lTDSPercentage = ClaimsConstants.ZERO_DBL_VAL, lTDSHospPercentage = ClaimsConstants.ZERO_DBL_VAL, lTDSHospAmt = ClaimsConstants.ZERO_DBL_VAL,lTDSAgencyAmt = ClaimsConstants.ZERO_DBL_VAL, lTDSAmt = ClaimsConstants.ZERO_DBL_VAL;
		double lSurchargeAmt = ClaimsConstants.ZERO_DBL_VAL, lCessAmt = ClaimsConstants.ZERO_DBL_VAL,  lCessPct = ClaimsConstants.ZERO_DBL_VAL, lTaxAmt = ClaimsConstants.ZERO_DBL_VAL;//lSurchargePct = ClaimsConstants.ZERO_DBL_VAL,
		double lTempClaimAmt = ClaimsConstants.ZERO_DBL_VAL;
		Map lTDSDeductMap = new HashMap();
		Map lResMap = (HashMap) lParamMap.get("ResMap");
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

		//Chandana - 6444 - 05-02-2025
		double hubMaintenance= 0 ;
		double spokeMaintenance = 0 ;
		 int cycleCount = 0;
		if(lResMap.get("CYCLE_CNT") != null){
			cycleCount = Integer.parseInt(lResMap.get("CYCLE_CNT").toString());
		}
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
			//Chandana - 6444			
			if(lResMap.get("HUBSPOKE_FLAG") != null)
            	hubSpokeFlag = (String) lResMap.get("HUBSPOKE_FLAG");
			if(lResMap.get("PKG_AMT") != null)
				pkgAmt = Double.parseDouble(lResMap.get("PKG_AMT").toString());
			lStrHospType = lResMap.get("hosp_type").toString();
			lStrClaimAmt = lResMap.get("claim_amount").toString();
			claimAmt = Double.parseDouble(lStrClaimAmt);
			
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
					if (claimAmountList.get(i)
							.getCLAIMAMOUNT()!=null && Double.parseDouble(claimAmountList.get(i)
							.getCLAIMAMOUNT().toString()) > 0)
						claimAmt = Double.parseDouble(claimAmountList.get(i)
								.getCLAIMAMOUNT().toString());
				}
			}
		}
		
		if("Y".equalsIgnoreCase(lStrTDSActive)){
			
			if (ClaimsConstants.Y.equalsIgnoreCase(lStrDoneFlag)) {
				if (!"Y".equalsIgnoreCase(lStrSlabAplFlag)) 
				{
					if(claimAmt==2)
					{
						lHospAmt=1;
						lTaxAmt=1;
						lTDSAmt = lTaxAmt;
						TotalAmt=TotalAmt + lHospAmt;
						TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					}
					else
					{
					lTaxAmt = (claimAmt * lTDSPercentage) / 100;
					lTDSAmt = lTaxAmt;

					if (((lTDSAmt * 10) % 10) > 0)
						lTDSAmt = new Double((int) lTDSAmt + 1);

					lHospAmt = claimAmt - lTDSAmt;
					TotalAmt = TotalAmt + lHospAmt;
					TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					}
					lParamMap.put("HospCmpAmt", lHospAmt);
					lStrInsert = ClaimsConstants.TRUE;
				} else if ("Y".equalsIgnoreCase(lStrDirectDeduction)) 																	
				{
					if(claimAmt==2)
					{
						lHospAmt=1;
						lTaxAmt=1;
						lTDSAmt = lTaxAmt;
						TotalAmt=TotalAmt + lHospAmt;
						TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					}
					else
					{
					lTaxAmt = (claimAmt * lTDSPercentage) / 100;
					lTDSAmt = lTaxAmt;

					if (((lTDSAmt * 10) % 10) > 0)
						lTDSAmt = new Double((int) lTDSAmt + 1);

					lHospAmt = claimAmt - lTDSAmt;
					TotalAmt = TotalAmt + lHospAmt;
					TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					}
					lParamMap.put("HospCmpAmt", lHospAmt);
					lStrInsert = ClaimsConstants.TRUE;
				}
				// First time when the slabAmt < totalAmt paid,update
				// Direct_deduction flag
				else if ("N".equalsIgnoreCase(lStrDirectDeduction)&& "Y".equalsIgnoreCase(lStrDoneFlag)
						&& (TotalAmt + claimAmt > SlabAmt)) {
					
					double remainClaimAmt = SlabAmt - TotalAmt;
					double lRemainTdsAmt = (remainClaimAmt * lTDSPercentage) / 100;
					if (((lRemainTdsAmt * 10) % 10) > 0)
						lRemainTdsAmt = new Double((int) lRemainTdsAmt + 1);
					
					/*EhfPaymentSlabDtls ehfPaymentSlabDtls = null;
					ehfPaymentSlabDtls = genericDao.findById(EhfPaymentSlabDtls.class,
							String.class, lStrHospDeductId);

					ehfPaymentSlabDtls.setTotalClaimAmt(new Double(TotalAmt+remainClaimAmt));
					ehfPaymentSlabDtls.setTotalTdsAmt(new Double(TotalTDSAmt+lRemainTdsAmt));
					ehfPaymentSlabDtls = genericDao.save(ehfPaymentSlabDtls);
					if (ehfPaymentSlabDtls != null) {
*/						lStrInsert = ClaimsConstants.TRUE;
					
					
					boolean isInsert = true;
						/*updateGovtHospSurg(lStrHospId,
							lStrHospDeductId, lParamMap);*/
					if (isInsert) {
						//lTDSPercentage = Double.parseDouble(ClaimsConstants.TDS_PERCENT);
						lTDSPercentage = Double.parseDouble(lParamMap.get("TDS_RATE_PERCENT").toString());
						if(claimAmt==2)
						{
							lHospAmt=1;
							lTaxAmt=1;
							lTDSAmt = lTaxAmt;
							TotalAmt=TotalAmt + lHospAmt;
							TotalTDSAmt = TotalTDSAmt + lTDSAmt;
						}
						else
						{
						lTempClaimAmt = (TotalAmt + claimAmt) - SlabAmt;
						lTaxAmt = (lTempClaimAmt * lTDSPercentage) / 100;
						lTDSAmt = lTaxAmt;

						if (((lTDSAmt * 10) % 10) > 0)
							lTDSAmt = new Double((int) lTDSAmt + 1);
						
						lTDSAmt = lTDSAmt + lRemainTdsAmt;
						lHospAmt = claimAmt - lTDSAmt;
						TotalAmt = TotalAmt + lHospAmt;
						TotalTDSAmt = TotalTDSAmt + lTDSAmt;
						}
						lParamMap.put("HospCmpAmt", lHospAmt);
						lStrInsert = ClaimsConstants.TRUE;
						lStrTDSActive = ClaimsConstants.Y;
					}
				} else // First time when a case is paid
					{
					if(claimAmt==2)
					{
						lHospAmt=1;
						lTaxAmt=1;
						lTDSAmt = lTaxAmt;
						TotalAmt=TotalAmt + lHospAmt;
						TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					}
					else
					{
					lTaxAmt = (claimAmt * lTDSPercentage) / 100;
					lTDSAmt = lTaxAmt;

					if (((lTDSAmt * 10) % 10) > 0)
						lTDSAmt = new Double((int) lTDSAmt + 1);

					lHospAmt = claimAmt - lTDSAmt;
					TotalAmt = TotalAmt + lHospAmt;
					TotalTDSAmt = TotalTDSAmt + lTDSAmt;
					}
					lParamMap.put("HospCmpAmt", lHospAmt);
					lStrInsert = ClaimsConstants.TRUE;
				}

				//if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
				//	lStrInsert = ClaimsConstants.TRUE;
				//}
			} else {
				if(claimAmt==2)
				{
					lHospAmt=1;
					lTaxAmt=1;
					lTDSAmt = lTaxAmt;
					TotalAmt=TotalAmt + lHospAmt;
					TotalTDSAmt = TotalTDSAmt + lTDSAmt;
				}
				else
				{
				lTaxAmt = (claimAmt * lTDSPercentage) / 100;
				lTDSAmt = lTaxAmt;

				if (((lTDSAmt * 10) % 10) > 0)
					lTDSAmt = new Double((int) lTDSAmt + 1);

				lHospAmt = claimAmt - lTDSAmt;
				TotalAmt = TotalAmt + lHospAmt;
				TotalTDSAmt = TotalTDSAmt + lTDSAmt;
				}
				lParamMap.put("HospCmpAmt", lHospAmt);
				lStrInsert = ClaimsConstants.TRUE;
			}		
			
			
		}
		//Chandana - 6444 - 04-02-2025 : Trifurcation calculations
		else if(("OLD".equalsIgnoreCase(hubSpokeFlag) || "NEW".equalsIgnoreCase(hubSpokeFlag)) && pkgAmt > 0 ){
			lHospPercentage=ClaimsConstants.ZERO_DBL_VAL;
        	if("OLD".equalsIgnoreCase(hubSpokeFlag)){
        		hubAmt = (config.getDouble("HUB_SHARE_OLD") *claimAmt/pkgAmt);
        		spokeAmt = (config.getDouble("SPOKE_SHARE_OLD") *claimAmt/pkgAmt);
        	}else{
        		hubAmt = (config.getDouble("HUB_SHARE_NEW") *claimAmt/pkgAmt);
        		spokeAmt = (config.getDouble("SPOKE_SHARE_NEW") *claimAmt/pkgAmt);
        	}
        	hubAmt = new Double((int)hubAmt); 
        	spokeAmt = new Double((int)spokeAmt); 
			// For Bifurcation
        	if(cycleCount > 0){
        		hubMaintenance= (config.getDouble("HUB_MAINTENANCE")) * cycleCount ;
   			 	spokeMaintenance = config.getDouble("SPOKE_MAINTENANCE") * cycleCount ;
        	}
        	else if(cycleCount <= 0){
        		hubMaintenance = 200;
        		spokeMaintenance = 200;
        	}
			if(hubMaintenance >= hubAmt){
				hubMaintenance = hubAmt-1;
				hubAmt = 1.0 ;
			}else{
			  hubAmt = hubAmt - hubMaintenance;
			}
			if(spokeMaintenance >= spokeAmt){
				spokeMaintenance = spokeAmt - 1;
				spokeAmt = 1.0 ;
			}else{
				spokeAmt = spokeAmt - spokeMaintenance ; 
			}
        	agencyAmt =claimAmt- hubAmt-spokeAmt-hubMaintenance-spokeMaintenance;
        	lStrBuffer = new StringBuffer();
        	List<ClaimsFlowVO> perentList = new ArrayList<ClaimsFlowVO>();
			lStrBuffer.append(" SELECT DISTINCT HUB_HOSP_ID AS HOSPITALID, ead.tds_percentage AS TDSHOSPPERCENT FROM EHFM_HOSP_HUB_SPOKE_MPG EHH,EHF_HUB_AGENCY_ACCT_DTLS ead ");
			lStrBuffer.append(" WHERE EHH.HUB_HOSP_ID = ead.hub_id AND EHH.SPOKE_HOSP_ID = ? AND EHH.ACTIVE_YN = ? AND ead.active_yn = ? ");
			arg = new String[3];
			arg[0] = lStrHospId;
			arg[1] = ClaimsConstants.Y;
			arg[2] = ClaimsConstants.Y;
			perentList = genericDao.executeSQLQueryList(ClaimsFlowVO.class, lStrBuffer.toString(), arg);
			String hospID = perentList.get(0).getHOSPITALID();
			hospPecent = ((Number) perentList.get(0).getTDSHOSPPERCENT()).doubleValue();
			lTaxAmt = ( agencyAmt * hospPecent ) / 100 ;
        	lTDSAmt = lTaxAmt;
            if(((lTDSAmt*10)%10) > 0)
            lTDSAmt = new Double((int)lTDSAmt + 1);
            lTDSAgencyAmt = agencyAmt - lTDSAmt ;
            lTDSHospAmt = spokeAmt;
             lParamMap.put ( "HospCmpAmt", lTDSHospAmt ) ;
            lStrInsert = ClaimsConstants.TRUE;
		}
		else if (ClaimsConstants.Y.equalsIgnoreCase(lStrRFActive))
		{
			if(claimAmt==2)
			{
				lHospAmt=1;
				lTrustAmt=1;
				
			}
			else
			{
			lHospAmt = (claimAmt * lHospPercentage) / 100;
			lTrustAmt = (claimAmt * lTrustPercentage) / 100;
			}
			lStrInsert = ClaimsConstants.TRUE;

	} else if ((!ClaimsConstants.Y.equalsIgnoreCase(lStrRFActive))
			&& (!ClaimsConstants.Y.equalsIgnoreCase(lStrTDSActive))) {
		lHospAmt = claimAmt;
		lTrustAmt = ClaimsConstants.ZERO_DBL_VAL;
		lTDSHospAmt = ClaimsConstants.ZERO_DBL_VAL;
		lTaxAmt = ClaimsConstants.ZERO_DBL_VAL;
		lTDSAmt = ClaimsConstants.ZERO_DBL_VAL;
		lParamMap.put("HospCmpAmt", lHospAmt);
		//lStrInsert = ClaimsConstants.TRUE;
		lStrInsert = ClaimsConstants.FALSE;
	}
		
		
		Double tdsRatePercent = Double.parseDouble(lParamMap.get("TDS_RATE_PERCENT").toString());
		if (ClaimsConstants.Y.equalsIgnoreCase(lStrDoneFlag)
				&& lTDSPercentage < tdsRatePercent 
				&& "Y".equalsIgnoreCase(lStrSlabAplFlag)) {
			lStrBuffer = new StringBuffer();
			
			/*EhfPaymentSlabDtls ehfPaymentSlabDtls = null;
			ehfPaymentSlabDtls = genericDao.findById(EhfPaymentSlabDtls.class,
					String.class, lStrHospDeductId);

			ehfPaymentSlabDtls.setTotalClaimAmt(new Double(TotalAmt));
			ehfPaymentSlabDtls.setTotalTdsAmt(new Double(TotalTDSAmt));
			ehfPaymentSlabDtls = genericDao.save(ehfPaymentSlabDtls);
			if (ehfPaymentSlabDtls != null) {*/
				lStrInsert = ClaimsConstants.TRUE;
			
		}
		
		
		if (ClaimsConstants.Y.equalsIgnoreCase(lStrTDSActive)) {
			lTDSHospAmt = lHospAmt;
			lHospAmt = ClaimsConstants.ZERO_DBL_VAL;
		}

		lTDSDeductMap.put("HospAmt", new Double(lHospAmt).toString() );
		lTDSDeductMap.put("TrustAmt", new Double(lTrustAmt).toString());
		lTDSDeductMap.put("TDSHospAmt",new Double(lTDSHospAmt).toString());
		lTDSDeductMap.put("TDSAmt", new Double(lTDSAmt).toString());
		lTDSDeductMap.put("lTaxAmt", new Double(lTaxAmt).toString());
		lTDSDeductMap.put("TDSActiveFlag", lStrTDSActive);
		lTDSDeductMap.put("RFActiveFlag", lStrRFActive);
		lTDSDeductMap.put("UpdSuccess", lStrInsert);
		// Chandana - 6444
		lTDSDeductMap.put("hubSpokeFlag", hubSpokeFlag);
		lTDSDeductMap.put("hubAmt", hubAmt);
		lTDSDeductMap.put("spokeAmt", spokeAmt);
		lTDSDeductMap.put("agencyPctAmt", lTDSAgencyAmt);
		lTDSDeductMap.put("agencyAmt", agencyAmt);
		lTDSDeductMap.put("hubMaintenance", hubMaintenance);
		lTDSDeductMap.put("spokeMaintenance", spokeMaintenance);

		return lTDSDeductMap;
	}

	/**
	 * Update govt hosp surg(Applicable in Case OF Slab Amount).
	 * 
	 * @param lStrHospId
	 *            the l str hosp id
	 * @param lStrHospDeductId
	 *            the l str hosp deduct id
	 * @param lparamMap
	 *            the lparam map
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
/*	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean updateGovtHospSurg(String lStrHospId,
			String lStrHospDeductId, HashMap lparamMap) throws Exception {
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;

		EhfPaymentSlabDtls ehfPaymentSlabDtls = null;
		ehfPaymentSlabDtls = genericDao.findById(EhfPaymentSlabDtls.class,
				String.class, lStrHospDeductId);

		ehfPaymentSlabDtls.setDirectDeduction(ClaimsConstants.Y);
		ehfPaymentSlabDtls = genericDao.save(ehfPaymentSlabDtls);

		if (ehfPaymentSlabDtls != null) {
			 
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.hospId", lStrHospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.claimType","GTDS",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.deductorType","CD525",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmHospSurgPer> ehfmHospSurgPer = genericDao
					.findAllByCriteria(EhfmHospSurgPer.class, criteriaList);
			
			for(EhfmHospSurgPer ehfmHospSurg : ehfmHospSurgPer){
				ehfmHospSurg.setTdsHospPercent(Double.parseDouble(ClaimsConstants.TDS_HOSP_PERCENT.toString()));
				ehfmHospSurg.setTdsPercent(Double.parseDouble(ClaimsConstants.TDS_PERCENT.toString()));
				ehfmHospSurg.setTdsActiveYn(ClaimsConstants.Y);
				ehfmHospSurg = genericDao.save(ehfmHospSurg);
			}
			
			 * AsritGovtHospSurg asritGovtHospSurg = null; AsritGovtHospSurgPK
			 * asritGovtHospSurgPK = new AsritGovtHospSurgPK();
			 * asritGovtHospSurgPK.setHospId(lStrHospId);
			 * asritGovtHospSurgPK.setSurgeryId("1");
			 * asritGovtHospSurgPK.setClaimType("CD525");
			 * asritGovtHospSurgPK.setDeductorType
			 * ((String)lparamMap.get("TDS_CASE_TYPE"));
			 * 
			 * asritGovtHospSurg =
			 * genericDao.findById(AsritGovtHospSurg.class,AsritGovtHospSurgPK
			 * .class,asritGovtHospSurgPK);
			 * asritGovtHospSurg.setTdsHospPercent(Double
			 * .parseDouble(ClaimsConstants.TDS_HOSP_PERCENT.toString()));
			 * asritGovtHospSurg
			 * .setTdsPercent(Double.parseDouble(ClaimsConstants
			 * .TDS_PERCENT.toString()));
			 * asritGovtHospSurg.setTdsActiveYn(ClaimsConstants.Y);
			 * asritGovtHospSurg = genericDao.save(asritGovtHospSurg);
			 * 
			 * if(asritGovtHospSurg != null) {
			 
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
			// }

		}
		return isInsert;
	}*/

	/**
	 * Process trust payments(Inserting into tables for RF Payment).
	 * 
	 * @param HashMap
	 *            the lparam map
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public boolean  processTrustPayments(HashMap lparamMap) throws Exception {
		logger.info("Start:processTrustPayments method in ClaimsPaymentDAOImpl");
		
		String lStrCaseFollowupId = null;
		boolean isResult = ClaimsConstants.BOOLEAN_FALSE;
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		String caseStatus = (String) lparamMap.get("caseStatus");

		String lStrCaseId = (String) lparamMap.get("CASE_ID");
		boolean charContains = lStrCaseId.contains(ClaimsConstants.SLASH);
		String lStrTrustPaymentId = ClaimsConstants.EMPTY;

		lStrTrustPaymentId = lStrCaseId + ClaimsConstants.SLASH_G;
		lparamMap.put("CASE_FOLLOWUP_ID", lStrCaseId);
		if (charContains
				&& ClaimsConstants.ErroneousClaim
						.equalsIgnoreCase((String) lparamMap.get("PaymentType"))) {
			lStrTrustPaymentId = lStrCaseId /* + "/E" */
					+ ClaimsConstants.SLASH_G;
			lStrCaseFollowupId = lStrCaseId;
			lStrCaseId = lStrCaseId.substring(ClaimsConstants.ZERO_VAL,
					lStrCaseId.indexOf(ClaimsConstants.SLASH));

		}

		if (charContains
				&& ClaimsConstants.FollowUp.equalsIgnoreCase((String) lparamMap
						.get("PaymentType"))) {
			lStrTrustPaymentId = lStrCaseId + ClaimsConstants.SLASH_G;
			lStrCaseFollowupId = lStrCaseId;
			lStrCaseId = lStrCaseId.substring(ClaimsConstants.ZERO_VAL,
					lStrCaseId.indexOf(ClaimsConstants.SLASH));
		}

	
		String lStrRevFndRemarks = (String) lparamMap.get("REMARKS");
		
		String lStrStatusId = (String) lparamMap.get("caseStatus");
		String patientScheme=null;
		if(lparamMap.get("patScheme")!=null)
			patientScheme = (String)lparamMap.get("patScheme") ;

		Double trustAmt = Double.parseDouble(lparamMap.get("TrustAmt")
				.toString());
		Date currentDate = (Date) lparamMap.get("currentDate");

		EhfClaimTrustPayment ehfClaimTrustPayment = null;
		//For checking whether RF account details are present are no;
		ehfClaimTrustPayment = genericDao.findById(EhfClaimTrustPayment.class,
				String.class, lStrTrustPaymentId);

		if (ehfClaimTrustPayment != null) {
			
			ehfClaimTrustPayment.setCaseId(lStrCaseId);
			ehfClaimTrustPayment.setPaymentStatus(lStrStatusId);
			ehfClaimTrustPayment.setClaimAmount(trustAmt);
			ehfClaimTrustPayment.setRemarks(lStrRevFndRemarks);
			ehfClaimTrustPayment.setTransStatus((String) lparamMap
					.get("ReadyStatus"));
			ehfClaimTrustPayment.setCrtDt(currentDate);
			ehfClaimTrustPayment.setPatientScheme(patientScheme);
			ehfClaimTrustPayment.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfClaimTrustPayment.setPaymentCheck(ClaimsConstants.F);
			ehfClaimTrustPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
			ehfClaimTrustPayment.setCaseFollowupId(lStrCaseFollowupId);
			ehfClaimTrustPayment.setTimeMilSec((double) 0);
			ehfClaimTrustPayment = genericDao.save(ehfClaimTrustPayment);

			//System.out
				//	.println("Exception in method processTrustPayments--ORA-00001: unique constraint");
			//logger.info("Exception in method processTrustPayments--ORA-00001: unique constraint");
		} else {
			ehfClaimTrustPayment = new EhfClaimTrustPayment();
			ehfClaimTrustPayment.setTrustPaymentId(lStrTrustPaymentId);
			ehfClaimTrustPayment.setCaseId(lStrCaseId);
			ehfClaimTrustPayment.setPaymentStatus(lStrStatusId);
			ehfClaimTrustPayment.setClaimAmount(trustAmt);
			ehfClaimTrustPayment.setRemarks(lStrRevFndRemarks);
			ehfClaimTrustPayment.setPatientScheme(patientScheme);
			ehfClaimTrustPayment.setTransStatus((String) lparamMap
					.get("ReadyStatus"));
			ehfClaimTrustPayment.setTimeMilSec((double) 0);
			ehfClaimTrustPayment.setCrtDt(currentDate);
			ehfClaimTrustPayment.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfClaimTrustPayment.setPaymentCheck(ClaimsConstants.F);
			ehfClaimTrustPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
			ehfClaimTrustPayment.setCaseFollowupId(lStrCaseFollowupId);

			ehfClaimTrustPayment = genericDao.save(ehfClaimTrustPayment);

		}
		
		
		if(caseStatus!=null && caseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
		{
		    String surCaseSetId="";
		    if(lStrTrustPaymentId!=null)
			 surCaseSetId=( lStrTrustPaymentId.substring(0, lStrTrustPaymentId.indexOf("/G"))+"/SD/G");
			
		    ehfClaimTrustPayment = new EhfClaimTrustPayment();
		    ehfClaimTrustPayment.setTrustPaymentId(surCaseSetId);
			ehfClaimTrustPayment.setCaseId(lStrCaseId);
			ehfClaimTrustPayment.setPaymentStatus(lStrStatusId);
			EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, (String) lparamMap.get("CASE_ID"));
			
			if(ehfSurplusTdsClaim!=null)
			{
				if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
				{
					ehfClaimTrustPayment.setClaimAmount(ehfSurplusTdsClaim.getTdsPctAmt());
					//lResMap.put("tds_percent", ehfSurplusTdsClaim.getTdsPctAmt());
				}
				else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
				{
					ehfClaimTrustPayment.setClaimAmount(ehfSurplusTdsClaim.getTrustPctAmt());
				}
	/*			else
					ehfClaimTrustPayment.setClaimAmount(0.0);*/
			}
			
			else
				ehfClaimTrustPayment.setClaimAmount(0.0);					
			//ehfClaimTrustPayment.setClaimAmount(trustAmt);
			ehfClaimTrustPayment.setRemarks(lStrRevFndRemarks);
			ehfClaimTrustPayment.setTransStatus((String) lparamMap
					.get("ReadyStatus"));
			ehfClaimTrustPayment.setTimeMilSec((double) 0);
			ehfClaimTrustPayment.setCrtDt(currentDate);
			ehfClaimTrustPayment.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfClaimTrustPayment.setPaymentCheck(ClaimsConstants.F);
			ehfClaimTrustPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
			ehfClaimTrustPayment.setCaseFollowupId(lStrCaseFollowupId);

			ehfClaimTrustPayment = genericDao.save(ehfClaimTrustPayment);

		}

		
		if (ehfClaimTrustPayment != null) {
			isInsert = ClaimsConstants.BOOLEAN_TRUE;
		} else {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
		}
		if (isInsert) {
			lparamMap.put("Trust_PAYMENT_ID", lStrTrustPaymentId);
			//lparamMap.put("Trust_STAT_ID", ClaimsConstants.CLAIM_READY_PAYMENT);
			lparamMap.put("Trust_STAT_ID", ClaimsConstants.CLAIM_SENT);
			//lparamMap.put("TrustRemarks", "Claim Ready For Payment");
			lparamMap.put("TrustRemarks", "Claim Sent For Payment");
			setTrustAuditDetails(lparamMap);
			isResult = ClaimsConstants.BOOLEAN_TRUE;
		}
		logger.info("End:processTrustPayments method in ClaimsPaymentDAOImpl");
		return isResult;
	}

	/**
	 * Process tds payments(Inserting into Tables for TDS Payment).
	 * 
	 * @param HashMap
	 *            the lparam map
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public boolean processTDSPayments(HashMap lparamMap) throws Exception {
		logger.info("Start:processTDSPayments method in ClaimsPaymentDAOImpl");
		
		String lStrCaseFollowupId = null;
		boolean charContains = ClaimsConstants.BOOLEAN_FALSE;
		boolean isExecuted = ClaimsConstants.BOOLEAN_FALSE;
		String paymentType = (String) lparamMap.get("PaymentType");
		String caseStatus = (String) lparamMap.get("caseStatus");
		String lStrCaseId = ClaimsConstants.EMPTY, lStrTDSPaymentId = ClaimsConstants.EMPTY;
		
	    
		String case_Type = ClaimsConstants.TRUST_DEDUCTOR;
		if (ClaimsConstants.FollowUp.equalsIgnoreCase(paymentType)) {
			lparamMap.put("TDS_PAYMENT_TYPE", ClaimsConstants.FPCLAIM_FORM_PAYMENT);
			lStrCaseId = (String) lparamMap.get("CASE_ID");
			charContains = lStrCaseId.contains(ClaimsConstants.SLASH);
			lStrTDSPaymentId = lStrCaseId + ClaimsConstants.SLASH_TDS;
		} else if (ClaimsConstants.ErroneousClaim.equalsIgnoreCase(paymentType)) {
			lparamMap.put("TDS_PAYMENT_TYPE",ClaimsConstants.ERRCLAIM_FORM_PAYMENT);
			lStrCaseId = (String) lparamMap.get("CASE_ID");
			charContains = lStrCaseId.contains(ClaimsConstants.SLASH);
			lStrTDSPaymentId = lStrCaseId + ClaimsConstants.SLASH_TDS;
		} else {
			lparamMap.put("TDS_PAYMENT_TYPE", ClaimsConstants.CLAIM_FORM_PAYMENT);
			lStrCaseId = (String) lparamMap.get("CASE_ID");
			charContains = lStrCaseId.contains(ClaimsConstants.SLASH);
			lStrTDSPaymentId = lStrCaseId + ClaimsConstants.SLASH_TDS;
		}
		
		String patientScheme=null;
		if(lparamMap.get("patScheme")!=null)
			patientScheme = (String)lparamMap.get("patScheme") ;

		lparamMap.put("TDS_PAYMENT_ID", lStrTDSPaymentId);// CASE_FOLLOWUP_ID
		
		if (charContains) {
			lStrCaseFollowupId = lStrCaseId;
			lStrCaseId = lStrCaseId.substring(ClaimsConstants.ZERO_VAL,
					lStrCaseId.indexOf(ClaimsConstants.SLASH));
		}

		EhfClaimTdsPayment ehfClaimTdsPayment = null;
		//For checking whether TDS account details are present are no;
		ehfClaimTdsPayment = genericDao.findById(EhfClaimTdsPayment.class,
				String.class, lStrTDSPaymentId);
		if (ehfClaimTdsPayment != null) {
			
			ehfClaimTdsPayment.setCaseId(lStrCaseId);
			//ehfClaimTdsPayment
			//		.setPaymentStatus(ClaimsConstants.CLAIM_READY_PAYMENT);
			ehfClaimTdsPayment.setPaymentStatus((String) lparamMap
					.get("caseStatus"));
			/*ehfClaimTdsPayment
			.setPaymentStatus(ClaimsConstants.CLAIM_SENT);*/
			ehfClaimTdsPayment.setClaimAmt(Double
					.parseDouble((String) lparamMap.get("TDSAmt")));
			ehfClaimTdsPayment.setRemarks("Claim Ready For Payment");
			ehfClaimTdsPayment.setTransStatus((String) lparamMap
					.get("ReadyStatus"));
			ehfClaimTdsPayment.setTimeMilliSec((long) 0);
			ehfClaimTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
			ehfClaimTdsPayment.setPatientScheme(patientScheme);
			ehfClaimTdsPayment.setCrtUser((String) lparamMap.get("CRTUSER"));
			ehfClaimTdsPayment.setPaymentCheck(ClaimsConstants.F);
			ehfClaimTdsPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
			ehfClaimTdsPayment.setCaseFllwUpId(lStrCaseFollowupId);
			ehfClaimTdsPayment.setCaseType("CD525");
			ehfClaimTdsPayment.setPaymentType((String) lparamMap
					.get("TDS_PAYMENT_TYPE"));
			//ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
			ehfClaimTdsPayment.setDeductorType(case_Type);
			ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
			//System.out
				//	.println("Exception in method processTDSPayments--ORA-00001: unique constraint");
			//logger.info("Exception in method processTDSPayments--ORA-00001: unique constraint");
		} else {
			ehfClaimTdsPayment = new EhfClaimTdsPayment();
			ehfClaimTdsPayment.setTdsPaymentId(lStrTDSPaymentId);
			ehfClaimTdsPayment.setCaseId(lStrCaseId);
			//ehfClaimTdsPayment.setPaymentStatus(ClaimsConstants.CLAIM_READY_PAYMENT);
			ehfClaimTdsPayment.setPaymentStatus((String) lparamMap
					.get("caseStatus"));
			ehfClaimTdsPayment.setClaimAmt(Double
					.parseDouble((String) lparamMap.get("TDSAmt")));
			ehfClaimTdsPayment.setRemarks("Claim Ready For Payment");
			ehfClaimTdsPayment.setTransStatus((String) lparamMap
					.get("ReadyStatus"));
			ehfClaimTdsPayment.setTimeMilliSec((long) 0);
			ehfClaimTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
			ehfClaimTdsPayment.setPatientScheme(patientScheme);
			ehfClaimTdsPayment.setCrtUser((String) lparamMap.get("CRTUSER"));
			ehfClaimTdsPayment.setPaymentCheck(ClaimsConstants.F);
			ehfClaimTdsPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
			ehfClaimTdsPayment.setCaseFllwUpId(lStrCaseFollowupId);
			ehfClaimTdsPayment.setCaseType("CD525");
			ehfClaimTdsPayment.setPaymentType((String) lparamMap
					.get("TDS_PAYMENT_TYPE"));
			//ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
			ehfClaimTdsPayment.setDeductorType(case_Type);
			ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
		}
			if(caseStatus!=null && caseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED))
			{
			    String surCaseSetId="";
			    if(lStrTDSPaymentId!=null)
				 surCaseSetId=( lStrTDSPaymentId.substring(0, lStrTDSPaymentId.indexOf("/TDS"))+"/SD/TDS");
				
				ehfClaimTdsPayment = new EhfClaimTdsPayment();
				ehfClaimTdsPayment.setTdsPaymentId(surCaseSetId);
				ehfClaimTdsPayment.setCaseId(lStrCaseId);
				//ehfClaimTdsPayment.setPaymentStatus(ClaimsConstants.CLAIM_READY_PAYMENT);
				ehfClaimTdsPayment.setPaymentStatus((String) lparamMap
						.get("caseStatus"));
				
				EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, (String) lparamMap.get("CASE_ID"));
				
				if(ehfSurplusTdsClaim!=null)
				{
					if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
					{
						ehfClaimTdsPayment.setClaimAmt(ehfSurplusTdsClaim.getTdsPctAmt());
						//lResMap.put("tds_percent", ehfSurplusTdsClaim.getTdsPctAmt());
					}
					else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
					{
						ehfClaimTdsPayment.setClaimAmt(ehfSurplusTdsClaim.getTrustPctAmt());
					}
		/*			else
						ehfClaimTdsPayment.setClaimAmt(0.0);*/
				}
				
				else
					ehfClaimTdsPayment.setClaimAmt(0.0);
				ehfClaimTdsPayment.setRemarks("Claim Ready For Payment");
				ehfClaimTdsPayment.setTransStatus((String) lparamMap
						.get("ReadyStatus"));
				ehfClaimTdsPayment.setTimeMilliSec((long) 0);
				ehfClaimTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
				ehfClaimTdsPayment.setCrtUser((String) lparamMap.get("CRTUSER"));
				ehfClaimTdsPayment.setPaymentCheck(ClaimsConstants.F);
				ehfClaimTdsPayment.setSchemeId((String) lparamMap.get("SCHEME_ID"));
				ehfClaimTdsPayment.setCaseFllwUpId(lStrCaseFollowupId);
				ehfClaimTdsPayment.setCaseType("CD525");
				ehfClaimTdsPayment.setPaymentType((String) lparamMap
						.get("TDS_PAYMENT_TYPE"));
				//ehfClaimTdsPayment.setFileName((String) lparamMap.get("FileName"));
				ehfClaimTdsPayment.setDeductorType(case_Type);
				ehfClaimTdsPayment = genericDao.save(ehfClaimTdsPayment);
			}

	
		if (ehfClaimTdsPayment != null) {
			isExecuted = ClaimsConstants.BOOLEAN_TRUE;
		} else {
			isExecuted = ClaimsConstants.BOOLEAN_FALSE;
		}
		if (isExecuted) {
			//lparamMap.put("TDS_STAT_ID", ClaimsConstants.CLAIM_READY_PAYMENT);
			lparamMap.put("TDS_STAT_ID", ClaimsConstants.CLAIM_SENT);
			lparamMap.put("TdsRemarks", "Claim Sent For Payment");
			setTDSAuditDetails(lparamMap);
		}
		//logger.info("End:processTDSPayments method in ClaimsPaymentDAOImpl");

		return isExecuted;
	}

	/**
	 * Inserting details into table for uplaoded file and saving file in UploadClaim Folder and PKIInput Folder.
	 * 
	 * @param HashMap
	 *            the lparam map
	 * @return ArrayList
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ArrayList insertUploadFile(HashMap lparamMap) throws Exception {
		byte[] lFileBytes = null;
		boolean fileCreated = false;
		String lStrFileName = ClaimsConstants.EMPTY;
		String lStrCrtUsr = ClaimsConstants.EMPTY;
		String lStrFilePath = ClaimsConstants.EMPTY;
		String lStrSharePath = ClaimsConstants.EMPTY;
		String schemeId=ClaimsConstants.EMPTY;
		int liResult = 0, lSet_id = 0;
		ArrayList resultList = new ArrayList();
		File lFile = null;
		File lDir = null;
		FileOutputStream lFileOutputStream = null;

		if (lparamMap.get("FilePath").toString().length() > 0) {
			lStrFilePath = (String) lparamMap.get("FilePath");
		}
		if (lparamMap.get("FileName").toString().length() > 0) {
			lStrFileName = (String) lparamMap.get("FileName");
		}
		if (lparamMap.get("CRTUSER").toString().length() > 0) {
			lStrCrtUsr = (String) lparamMap.get("CRTUSER");
		}
		if (lparamMap.get("FileBytes").toString().length() > 0) {
			lFileBytes = (byte[]) lparamMap.get("FileBytes");
		}
		if (lparamMap.get("SharePath").toString().length() > 0) {
			lStrSharePath = (String) lparamMap.get("SharePath");
		}
		if(lparamMap.get("SCHEME_ID").toString().length()>0)
		{
			schemeId=lparamMap.get("SCHEME_ID").toString();
		}
        //saving into uplaod claim Folder
		String lStrRelativePath = lStrFilePath + "/" + lStrFileName;
		String lStrAbsolutePath = lStrSharePath + lStrRelativePath;
		String lStrDirPath = lStrSharePath + lStrFilePath;
		lFile = new File(lStrAbsolutePath);
		lDir = new File(lStrDirPath);
		boolean lbDir = false;

		if (!lDir.exists()) {
			lbDir = lDir.mkdirs();
		} else {
			lbDir = true;
		}
		if (lbDir) {
			if (lFile.exists()) {
				lFile.delete();
			}
			lFileOutputStream = new FileOutputStream(lFile);
			lFileOutputStream.write(lFileBytes);
			lFileOutputStream.flush();
			lFileOutputStream.close();
			lFileOutputStream = null;

			// Calling getSetId(HashMap lparamMap,Connection lCon) method to get
			// Max SetId
			lSet_id = getSetId(lparamMap);

			EhfClaimUploadFile ehfClaimUploadFile = null;

			ehfClaimUploadFile = genericDao.findById(EhfClaimUploadFile.class,
					String.class, lStrFileName);
			if (ehfClaimUploadFile != null) {
				//logger.info("Already data avaiblabe with same filename in EHF_CLAIM_UPLOAD_FILE table");
				System.out
						.println("Already data avaiblabe with same filename in EHF_CLAIM_UPLOAD_FILE table");
			} else {
				ehfClaimUploadFile = new EhfClaimUploadFile();
				ehfClaimUploadFile.setFileName(lStrFileName);
				ehfClaimUploadFile.setCrtUser(lStrCrtUsr);
				ehfClaimUploadFile.setFilePath(lStrAbsolutePath);
				ehfClaimUploadFile.setCrtDate(new Timestamp(new Date()
						.getTime()));
				ehfClaimUploadFile.setFileStatus((String) lparamMap
						.get("SentStatus"));
				ehfClaimUploadFile.setSetId(lSet_id);
				ehfClaimUploadFile = genericDao.save(ehfClaimUploadFile);
			}
			liResult = 0;
			if (ehfClaimUploadFile != null) {
				liResult = Integer.parseInt("1");
			}
			if (liResult == 1) {
				liResult = 0;
				if(schemeId!=null && !config.getString("AP").equalsIgnoreCase(schemeId))
				{
				fileCreated = insertFileInTemp(lStrFileName, lFileBytes);
				if (fileCreated == true) {
					liResult = 1;
				}
				}
				else if(schemeId!=null && config.getString("AP").equalsIgnoreCase(schemeId))
				{
					liResult = 1;
				}
			}
		}
		resultList.add(0, Integer.toString(liResult));
		return resultList;
	}

	/**
	 * Insert file in temp(PKIINPUT Folder).
	 * 
	 * @param lStrFileName
	 *            the l str file name
	 * @param lFileBytes
	 *            the l file bytes
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	@Transactional
	public boolean insertFileInTemp(String lStrFileName, byte[] lFileBytes)
			throws Exception {
		boolean fileCreated = false;
		File lFile = null;
		File lDir = null;
		FileOutputStream lFileOutputStream = null;
		String PaymentTempPath = config.getString("mapPath")
				+ config.getString("claimPayment_filePath")
				+ config.getString("claimPKIInput_filePath");
		String lStrAbsolutePath = PaymentTempPath + "/" + lStrFileName;
		String lStrDirPath = PaymentTempPath;
		lFile = new File(lStrAbsolutePath);
		lDir = new File(lStrDirPath);
		boolean lbDir = false;

		if (!lDir.exists()) {
			lbDir = lDir.mkdirs();
		} else {
			lbDir = true;
		}
		if (lbDir) {
			if (lFile.exists()) {
				lFile.delete();
			}
			lFileOutputStream = new FileOutputStream(lFile);
			lFileOutputStream.write(lFileBytes);
			lFileOutputStream.flush();
			lFileOutputStream.close();
			lFileOutputStream = null;
			fileCreated = true;
		}
		return fileCreated;
	}

	/**
	 * Getting Max Set_id value from EhfClaimUploadFile.
	 * 
	 * @param lparamMap
	 *            the lparam map
	 * @return the sets the id
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public int getSetId(HashMap lparamMap) throws Exception {
		int lSet_id = 0;
		List<ClaimsFlowVO> maxSetList = new ArrayList<ClaimsFlowVO>();
		// Getting Max Set_id value from EhfClaimUploadFile to update this
		// max value to corresponding file
		StringBuffer lStrBuffer = new StringBuffer();
		lStrBuffer
				.append(" select nvl(max(setId),0)+1 as MAXSETID from EhfClaimUploadFile where fileStatus=? ");

		String arg[] = new String[1];
		arg[0] = (String) lparamMap.get("SentStatus");
		maxSetList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
				lStrBuffer.toString(), arg);
		if (maxSetList.size() > 0)
			lSet_id = Integer.parseInt(maxSetList.get(0).getMAXSETID()
					.toString());

		return lSet_id;
	}

	/**
	 * To get TDS/RF Accounts Details.
	 * 
	 * @param HashMap
	 *            the lmap
	 * @return HashMap
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Transactional
	public HashMap payTDSFund(HashMap lmap) throws Exception {
		//logger.info("Start:payTDSFund method in ClaimsPaymentDAOImpl");
		double lFlagVal = ClaimsConstants.ZERO_DBL_VAL, TrustPctAmt = ClaimsConstants.ZERO_DBL_VAL, tdsAmt = ClaimsConstants.ZERO_DBL_VAL, amount = ClaimsConstants.ZERO_DBL_VAL;
		String lbeneficiary_account_name = ClaimsConstants.EMPTY, lbeneficiary_id = ClaimsConstants.EMPTY, lbeneficiary_addr = ClaimsConstants.EMPTY, lbeneficiary_bank_name = ClaimsConstants.EMPTY;
		String lbank_branch = ClaimsConstants.EMPTY, lbeneficiary_account_no = ClaimsConstants.EMPTY, lbeneficiary_bank_id = ClaimsConstants.EMPTY, lbeneficiary_bank_ifc_code = ClaimsConstants.EMPTY;
		String lclaint_ac_code = ClaimsConstants.EMPTY, lclaint_ac_number = ClaimsConstants.EMPTY, ltransaction_type = ClaimsConstants.EMPTY, caseStatus = ClaimsConstants.EMPTY, paymentType = ClaimsConstants.EMPTY, actType = ClaimsConstants.EMPTY, eMail = ClaimsConstants.EMPTY;
		String client_ifsc=ClaimsConstants.EMPTY;
		HashMap lresultMap = new HashMap();String lStrSchemeId = "";
		StringBuffer lStrBuffer = null;String oldCaseStatus="";
		ArrayList lFileDataList = null;
		ArrayList lCaseList = null;
		ArrayList lFileList = null;
		HashMap lFileMap = null;
		String patientScheme=null;
		List<ClaimsFlowVO> accountList = new ArrayList<ClaimsFlowVO>();

		if (lmap.get("TrustAmount") != null)
			TrustPctAmt = Double
					.parseDouble(lmap.get("TrustAmount").toString());

		if (lmap.get("TdsAmount") != null)
			tdsAmt = Double.parseDouble(lmap.get("TdsAmount").toString());

		if (lmap.get("FileMap") != null)
			lFileMap = (HashMap) lmap.get("FileMap");
		
		if(lFileMap.get("patScheme")!=null)
			patientScheme = (String)lFileMap.get("patScheme") ;

		if (lFileMap.get("CaseStatus") != null)
			caseStatus = (String) lFileMap.get("CaseStatus");
		
		if (lFileMap.get("OldCaseStatus") != null)
			oldCaseStatus = (String) lFileMap.get("OldCaseStatus");

		if (lFileMap.get("PaymentType") != null)
			paymentType = (String) lFileMap.get("PaymentType");

		if (lFileMap.get("EMAIL_ID") != null)
			eMail = (String) lFileMap.get("EMAIL_ID");
		
		if (lFileMap.get("SCHEME_ID") != null)
			lStrSchemeId = (String) lFileMap.get("SCHEME_ID");
		
		boolean status_Cond = (!oldCaseStatus.equals("CD141"));
		if(paymentType!=null && !paymentType.equalsIgnoreCase("")){
			if(paymentType.equalsIgnoreCase(ClaimsConstants.FollowUp)){
				status_Cond = (!oldCaseStatus.equals(ClaimsConstants.CLAIM_FP_READY_REJ_BANK));
			}
			else if(paymentType.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)){
				status_Cond = (!oldCaseStatus.equals(ClaimsConstants.CLAIM_ERR_READY_REJ_BANK));
			}
		}
               
		if (lFileMap.get("CaseList") != null)
			lCaseList = (ArrayList) lFileMap.get("CaseList");

		if (lFileMap.get("FileDataList") != null)
			lFileDataList = (ArrayList) lFileMap.get("FileDataList");

		String lStrUniqueId = ClaimsConstants.EMPTY;
		String lStrUniqueIdSur = ClaimsConstants.EMPTY;
		String caseid = (String) lFileMap.get("UNIQUE_ID");

		Set lSrchSet = lmap.keySet();
		Iterator lSrch = lSrchSet.iterator();

		while (lSrch.hasNext()) {
			lStrBuffer = new StringBuffer();
			String lStrSrchKey = (String) lSrch.next();

			if (lStrSrchKey.equalsIgnoreCase("TdsAmount")
					&& tdsAmt > ClaimsConstants.ZERO_VAL) {
				/*if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
					actType = ClaimsConstants.APTDSEHS;// "TDSAS2";
				else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
					actType = ClaimsConstants.TGTDSEHS;// "TDSAS2";
*/				
				if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
				{
					actType = ClaimsConstants.TGTDSJHS;// Journalist TG
		 		}
		    	else
				{
					if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
						actType = ClaimsConstants.APTDSEHS;// "TDSAS2";
					else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
						actType = ClaimsConstants.TGTDSEHS;// "TDSAS2";						
				}
				
				amount = tdsAmt;
				if(!caseid.contains("TDS")){
				lStrUniqueId = caseid + ClaimsConstants.SLASH_TDS;
				lStrUniqueIdSur=caseid+"/SD/TDS";
				}
				else
				{
				lStrUniqueId=caseid;
				lStrUniqueIdSur=(caseid.substring(0, caseid.indexOf("/TDS"))+"/SD/TDS");
				}
			} else if (lStrSrchKey.equalsIgnoreCase("TrustAmount")
					&& TrustPctAmt > ClaimsConstants.ZERO_VAL) {
				
				if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
				{
					actType = ClaimsConstants.JHSTGTRUST;// Journalist TG
				}
			  else
				{
					if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
						actType = ClaimsConstants.APTRUST;// "TDSAS2";
					else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
						actType = ClaimsConstants.TGTRUST;// "TDSAS2";
				}
				
				if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
					actType = ClaimsConstants.APTRUST;// "TDSAS2";
				else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
					actType = ClaimsConstants.TGTRUST;// "TDSAS2";
				amount = TrustPctAmt;
				lStrUniqueId = caseid + ClaimsConstants.SLASH_G;
				lStrUniqueIdSur=caseid+"/SD/G";
			}
           
			if (((lStrSrchKey.equalsIgnoreCase("TdsAmount") && tdsAmt > ClaimsConstants.ZERO_VAL) || ((lStrSrchKey
							.equalsIgnoreCase("TrustAmount") && TrustPctAmt > ClaimsConstants.ZERO_VAL)))) {
				lFlagVal = ClaimsConstants.ZERO_VAL;

				if (((TrustPctAmt > ClaimsConstants.ZERO_VAL && (status_Cond)) || (tdsAmt > ClaimsConstants.ZERO_VAL && (status_Cond))))
				{
					//getting bank accounts details for TDS/rf
					lStrBuffer
							.append("  select t.id as hospId,t.accountNo as accNo,t.nameAsperAct as  hospAccName,t.address as hospAdd,t.bankId as bankId,b.ifcCode as ifscCode, t.mailId as email , ") ;
					lStrBuffer
							.append("  b.bankName as  bankName,b.bankBranch as bankBranch,b.bankCategory as bankCategory FROM EhfmTrustActDtls t,EhfmBankMaster b ");
					lStrBuffer
							.append("  where t.bankId=b.bankId and t.actType=? and t.activeYn=? and t.schemeId=? ");

					String[] arg = new String[3];
					arg[0] = actType;
					arg[1] = ClaimsConstants.Y;
					arg[2] = lStrSchemeId;

					accountList = genericDao.executeHQLQueryList(
							ClaimsFlowVO.class, lStrBuffer.toString(), arg);

					if (accountList.size() > 0) {
						for (int i = 0; i < accountList.size(); i++) {
							if (accountList.get(i).getHospAccName() != null
									&& accountList.get(i).getHospAccName() != ClaimsConstants.EMPTY) {
								lbeneficiary_account_name = accountList.get(i)
										.getHospAccName().toString();
								lbeneficiary_id = accountList.get(i)
										.getHospId();
								lbeneficiary_addr = accountList.get(i)
										.getHospAdd();
								lbeneficiary_bank_name = accountList.get(i)
										.getBankName();
								lbank_branch = accountList.get(i)
										.getBankBranch();
								lbeneficiary_account_no = accountList.get(i)
										.getAccNo();
								lbeneficiary_bank_id = accountList.get(i)
										.getBankId();
								lbeneficiary_bank_ifc_code = accountList.get(i)
										.getIfscCode();
								lclaint_ac_code = (String) lFileMap
										.get("CLAINT_AC_CODE");
								lclaint_ac_number = (String) lFileMap
										.get("CLAINT_AC_NUMBER");
								ltransaction_type = accountList.get(i)
										.getBankCategory();
								eMail=accountList.get(i).getEmail();
								client_ifsc=(String) lFileMap.get("CLIENT_IFSC_CODE");
							}
						}
					} else {
						lFlagVal++;
					}
					if ((TrustPctAmt > ClaimsConstants.ZERO_VAL || tdsAmt > ClaimsConstants.ZERO_VAL)
							&& lFlagVal == ClaimsConstants.ZERO_VAL) {
						lFileList = new ArrayList();
						lFileMap = new HashMap(); // List for generate file for
													// upload(witch is having
													// the all mandatory filed)

						lFileMap.put("UNIQUE_ID", lStrUniqueId);
						lCaseList.add(lFileMap.get("UNIQUE_ID"));
						lFileMap.put("BENEFICIARY_ACCOUNT_NAME",
								lbeneficiary_account_name);
						lFileMap.put("BENEFICIARY_ID", lbeneficiary_id);
						lFileMap.put("BENEFICIARY_ADDR", lbeneficiary_addr);
						lFileMap.put("BENEFICIARY_BANK_NAME",
								lbeneficiary_bank_name);
						lFileMap.put("BANK_BRANCH", lbank_branch);
						lFileMap.put("BENEFICIARY_ACCOUNT_NO",
								lbeneficiary_account_no);
						lFileMap.put("TRANSACTION_AMOUNT",
								Double.toString(amount));
						lFileMap.put("BENEFICIARY_BANK_ID",
								lbeneficiary_bank_id);
						lFileMap.put("BENEFICIARY_BANK_IFC_CODE",
								lbeneficiary_bank_ifc_code);
						lFileMap.put("CLAINT_AC_CODE", lclaint_ac_code);
						lFileMap.put("CLAINT_AC_NUMBER", lclaint_ac_number);
						lFileMap.put("TRANSACTION_TYPE", ltransaction_type);
						// if client provide email id we can pass heare or we
						// can Beneficiary email id(It's an optional )
						lFileMap.put("EMAIL_ID", eMail);
						lFileMap.put("CLIENT_IFSC_CODE", client_ifsc);
						lFileDataList.add(lFileMap);
						
					}
					if(oldCaseStatus!=null && oldCaseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED) && lFlagVal == ClaimsConstants.ZERO_VAL)
					{
						lFileMap = new HashMap(); //Surplus Deductions List for generate file
													// for
						// upload(witch is having
						// the all mandatory fields)

						lFileMap.put("UNIQUE_ID", lStrUniqueIdSur);
						lCaseList.add(lFileMap.get("UNIQUE_ID"));
						lFileMap.put("BENEFICIARY_ACCOUNT_NAME",
								lbeneficiary_account_name);
						lFileMap.put("BENEFICIARY_ID", lbeneficiary_id);
						lFileMap.put("BENEFICIARY_ADDR", lbeneficiary_addr);
						lFileMap.put("BENEFICIARY_BANK_NAME",
								lbeneficiary_bank_name);
						lFileMap.put("BANK_BRANCH", lbank_branch);
						lFileMap.put("BENEFICIARY_ACCOUNT_NO",
								lbeneficiary_account_no);
						
						EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, caseid);
						
						if(ehfSurplusTdsClaim!=null)
						{
							if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
							{
								lFileMap.put("TRANSACTION_AMOUNT",ehfSurplusTdsClaim.getTdsPctAmt().toString());
								//lResMap.put("tds_percent", ehfSurplusTdsClaim.getTdsPctAmt());
							}
							else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
							{
								lFileMap.put("TRANSACTION_AMOUNT",ehfSurplusTdsClaim.getTrustPctAmt().toString());
							}
						else
						{
								lFileMap.put("TRANSACTION_AMOUNT","0");
								System.out.println("Tds flag not set in EhfSurplusTdsClaim for case id "+caseid);
								//logger.error("Tds flag not set in EhfSurplusTdsClaim for case id "+caseid);
								EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseid);
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
								
						}
						}
						else
						{
								lFileMap.put("TRANSACTION_AMOUNT","0");
								System.out.println("No record present in EhfSurplusTdsClaim for case id "+caseid);
								//logger.error("No record present in EhfSurplusTdsClaim with case id "+caseid);	
								EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseid);
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
								
						}
						/*lFileMap.put("TRANSACTION_AMOUNT",
								Double.toString(amount));*/
						lFileMap.put("BENEFICIARY_BANK_ID",
								lbeneficiary_bank_id);
						lFileMap.put("BENEFICIARY_BANK_IFC_CODE",
								lbeneficiary_bank_ifc_code);
						lFileMap.put("CLAINT_AC_CODE", lclaint_ac_code);
						lFileMap.put("CLAINT_AC_NUMBER", lclaint_ac_number);
						lFileMap.put("TRANSACTION_TYPE", ltransaction_type);
						// if client provide email id we can pass heare or
						// we
						// can Beneficiary email id(It's an optional )
						lFileMap.put("EMAIL_ID", eMail);
						lFileMap.put("CLIENT_IFSC_CODE",client_ifsc);
						lFileDataList.add(lFileMap);
					}
				}

			}
		} // end of while

		lresultMap.put("CaseList", lCaseList);
		lresultMap.put("FileDataList", lFileDataList);
		//logger.info("End:payTDSFund method in ClaimsPaymentDAOImpl");
		return lresultMap;
	}
	/**
	 * Pay surplus tds fund  for tDS exemption hospitals
	 * 
	 * @param HashMap
	 *            the lmap
	 * @return HashMap
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Transactional
	public HashMap payTDSFundSurplus(HashMap lmap) throws Exception {
		//logger.info("Start:payTDSFundSurplus method in ClaimsPaymentDAOImpl");
		double lFlagVal = ClaimsConstants.ZERO_DBL_VAL, amount = ClaimsConstants.ZERO_DBL_VAL;
		String lbeneficiary_account_name = ClaimsConstants.EMPTY, lbeneficiary_id = ClaimsConstants.EMPTY, lbeneficiary_addr = ClaimsConstants.EMPTY, lbeneficiary_bank_name = ClaimsConstants.EMPTY;
		String lbank_branch = ClaimsConstants.EMPTY, lbeneficiary_account_no = ClaimsConstants.EMPTY, lbeneficiary_bank_id = ClaimsConstants.EMPTY, lbeneficiary_bank_ifc_code = ClaimsConstants.EMPTY;
		String lclaint_ac_code = ClaimsConstants.EMPTY, lclaint_ac_number = ClaimsConstants.EMPTY, ltransaction_type = ClaimsConstants.EMPTY, caseStatus = ClaimsConstants.EMPTY, paymentType = ClaimsConstants.EMPTY, actType = ClaimsConstants.EMPTY, eMail = ClaimsConstants.EMPTY;
		HashMap lresultMap = new HashMap();String lStrSchemeId = "";
		StringBuffer lStrBuffer = null;String oldCaseStatus="";
		ArrayList lFileDataList = null;
		ArrayList lCaseList = null;
		ArrayList lFileList = null;
		HashMap lFileMap = null;
		
		List<ClaimsFlowVO> accountList = new ArrayList<ClaimsFlowVO>();

		if (lmap.get("FileMap") != null)
			lFileMap = (HashMap) lmap.get("FileMap");

		if (lFileMap.get("CaseStatus") != null)
			caseStatus = (String) lFileMap.get("CaseStatus");
		
		if (lFileMap.get("OldCaseStatus") != null)
			oldCaseStatus = (String) lFileMap.get("OldCaseStatus");

		if (lFileMap.get("PaymentType") != null)
			paymentType = (String) lFileMap.get("PaymentType");

		if (lFileMap.get("EMAIL_ID") != null)
			eMail = (String) lFileMap.get("EMAIL_ID");
		
		if (lFileMap.get("SCHEME_ID") != null)
			lStrSchemeId = (String) lFileMap.get("SCHEME_ID");
		
		boolean status_Cond = (!oldCaseStatus.equals("CD141"));
		if(paymentType!=null && !paymentType.equalsIgnoreCase("")){
			if(paymentType.equalsIgnoreCase(ClaimsConstants.FollowUp)){
				status_Cond = (!oldCaseStatus.equals(ClaimsConstants.CLAIM_FP_READY_REJ_BANK));
			}
			else if(paymentType.equalsIgnoreCase(ClaimsConstants.ErroneousClaim)){
				status_Cond = (!oldCaseStatus.equals(ClaimsConstants.CLAIM_ERR_READY_REJ_BANK));
			}
		}
               
		if (lFileMap.get("CaseList") != null)
			lCaseList = (ArrayList) lFileMap.get("CaseList");

		if (lFileMap.get("FileDataList") != null)
			lFileDataList = (ArrayList) lFileMap.get("FileDataList");

		String lStrUniqueId = ClaimsConstants.EMPTY;
		String lStrUniqueIdSur = ClaimsConstants.EMPTY;
		String caseid = (String) lFileMap.get("UNIQUE_ID");

		Set lSrchSet = lmap.keySet();
		Iterator lSrch = lSrchSet.iterator();

		while (lSrch.hasNext()) {
			lStrBuffer = new StringBuffer();
			String lStrSrchKey = (String) lSrch.next();

				if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
					actType = ClaimsConstants.APTDSEHS;// "TDSAS2";
				else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
					actType = ClaimsConstants.TGTDSEHS;// "TDSAS2";
				

				if(!caseid.contains("TDS")){
				lStrUniqueId = caseid + ClaimsConstants.SLASH_TDS;
				lStrUniqueIdSur=caseid+"/SD/TDS";
				}
				else
				{
				lStrUniqueId=caseid;
				lStrUniqueIdSur=(caseid.substring(0, caseid.indexOf("/TDS"))+"/SD/TDS");
				}
		
           
			if (oldCaseStatus!=null && oldCaseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED)) {
				lFlagVal = ClaimsConstants.ZERO_VAL;

				if (oldCaseStatus!=null && oldCaseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED) )
				{
					//getting bank accounts details for TDS/rf
					lStrBuffer
							.append("  select t.id as hospId,t.accountNo as accNo,t.nameAsperAct as  hospAccName,t.address as hospAdd,t.bankId as bankId,b.ifcCode as ifscCode, ");
					lStrBuffer
							.append("  b.bankName as  bankName,b.bankBranch as bankBranch,b.bankCategory as bankCategory FROM EhfmTrustActDtls t,EhfmBankMaster b ");
					lStrBuffer
							.append("  where t.bankId=b.bankId and t.actType=? and t.activeYn=? and t.schemeId=? ");

					String[] arg = new String[3];
					arg[0] = actType;
					arg[1] = ClaimsConstants.Y;
					arg[2] = lStrSchemeId;

					accountList = genericDao.executeHQLQueryList(
							ClaimsFlowVO.class, lStrBuffer.toString(), arg);

					if (accountList.size() > 0) {
						for (int i = 0; i < accountList.size(); i++) {
							if (accountList.get(i).getHospAccName() != null
									&& accountList.get(i).getHospAccName() != ClaimsConstants.EMPTY) {
								lbeneficiary_account_name = accountList.get(i)
										.getHospAccName().toString();
								lbeneficiary_id = accountList.get(i)
										.getHospId();
								lbeneficiary_addr = accountList.get(i)
										.getHospAdd();
								lbeneficiary_bank_name = accountList.get(i)
										.getBankName();
								lbank_branch = accountList.get(i)
										.getBankBranch();
								lbeneficiary_account_no = accountList.get(i)
										.getAccNo();
								lbeneficiary_bank_id = accountList.get(i)
										.getBankId();
								lbeneficiary_bank_ifc_code = accountList.get(i)
										.getIfscCode();
								lclaint_ac_code = (String) lFileMap
										.get("CLAINT_AC_CODE");
								lclaint_ac_number = (String) lFileMap
										.get("CLAINT_AC_NUMBER");
								ltransaction_type = accountList.get(i)
										.getBankCategory();
							}
						}
					} else {
						lFlagVal++;
					}
					
					if(oldCaseStatus!=null && oldCaseStatus.equalsIgnoreCase(ClaimsConstants.CLAIM_SURPLUS_TDS_DED) && lFlagVal == ClaimsConstants.ZERO_VAL)
					{
						lFileMap = new HashMap(); //Surplus Deductions List for generate file
													// for
						// upload(witch is having
						// the all mandatory fields)

						lFileMap.put("UNIQUE_ID", lStrUniqueIdSur);
						lCaseList.add(lFileMap.get("UNIQUE_ID"));
						lFileMap.put("BENEFICIARY_ACCOUNT_NAME",
								lbeneficiary_account_name);
						lFileMap.put("BENEFICIARY_ID", lbeneficiary_id);
						lFileMap.put("BENEFICIARY_ADDR", lbeneficiary_addr);
						lFileMap.put("BENEFICIARY_BANK_NAME",
								lbeneficiary_bank_name);
						lFileMap.put("BANK_BRANCH", lbank_branch);
						lFileMap.put("BENEFICIARY_ACCOUNT_NO",
								lbeneficiary_account_no);
						
						EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, caseid);
						
						if(ehfSurplusTdsClaim!=null)
						{
							if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
							{
								lFileMap.put("TRANSACTION_AMOUNT",ehfSurplusTdsClaim.getTdsPctAmt().toString());
								//lResMap.put("tds_percent", ehfSurplusTdsClaim.getTdsPctAmt());
							}
							else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
							{
								lFileMap.put("TRANSACTION_AMOUNT",ehfSurplusTdsClaim.getTrustPctAmt().toString());
							}
						else
						{
								lFileMap.put("TRANSACTION_AMOUNT","0");
								System.out.println("Tds flag not set in EhfSurplusTdsClaim for case id "+caseid);
								//logger.error("Tds flag not set in EhfSurplusTdsClaim for case id "+caseid);
								EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseid);
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
								
						}
						}
						else
						{
								lFileMap.put("TRANSACTION_AMOUNT","0");
								System.out.println("No record present in EhfSurplusTdsClaim for case id "+caseid);
								//logger.error("No record present in EhfSurplusTdsClaim with case id "+caseid);	
								EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseid);
								if(ehfCase!=null)
								{
									ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
									ehfCase=genericDao.save(ehfCase);
									
								}
								
						}
						/*lFileMap.put("TRANSACTION_AMOUNT",
								Double.toString(amount));*/
						lFileMap.put("BENEFICIARY_BANK_ID",
								lbeneficiary_bank_id);
						lFileMap.put("BENEFICIARY_BANK_IFC_CODE",
								lbeneficiary_bank_ifc_code);
						lFileMap.put("CLAINT_AC_CODE", lclaint_ac_code);
						lFileMap.put("CLAINT_AC_NUMBER", lclaint_ac_number);
						lFileMap.put("TRANSACTION_TYPE", ltransaction_type);
						// if client provide email id we can pass heare or
						// we
						// can Beneficiary email id(It's an optional )
						lFileMap.put("EMAIL_ID", eMail);
						lFileDataList.add(lFileMap);
					}
				}

			}
		} // end of while

		lresultMap.put("CaseList", lCaseList);
		lresultMap.put("FileDataList", lFileDataList);
		//logger.info("End:payTDSFund method in ClaimsPaymentDAOImpl");
		return lresultMap;
	}

	/**
	 * Auditing purpose for TDS
	 * 
	 * @param HashMap
	 *            the lparamMap
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public void setTDSAuditDetails(HashMap lparamMap) throws Exception {
		String lStrCaseNo = (String) lparamMap.get("TDS_PAYMENT_ID");
		String lStrActId = (String) lparamMap.get("TDS_STAT_ID");
		String caseStatus = (String) lparamMap.get("caseStatus");
		List<ClaimsFlowVO> tdsAuditDtls = new ArrayList<ClaimsFlowVO>();
		StringBuffer lQueryBuffer = new StringBuffer();

		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfTdsAudit au where au.id.tdsPaymentId=? ");

		String[] arg = new String[1];
		arg[0] = lStrCaseNo;

		tdsAuditDtls = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
				lQueryBuffer.toString(), arg);
		
		Long lintActOrder = 1L;
		if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
				&& tdsAuditDtls.get(0).getCOUNT() != null) {
			if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
				lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
		}

		EhfTdsAudit ehfTDSAudit = new EhfTdsAudit();
		EhfTdsAuditId ehfTDSAuditId = new EhfTdsAuditId();

		ehfTDSAuditId.setTdsPaymentId((String) lparamMap.get("TDS_PAYMENT_ID"));
		ehfTDSAuditId.setActOrder(lintActOrder);
		ehfTDSAudit.setId(ehfTDSAuditId);
		ehfTDSAudit.setActId(lStrActId);
		ehfTDSAudit.setActBy((String) lparamMap.get("CRTUSER"));
		ehfTDSAudit.setRemarks((String) lparamMap.get("TdsRemarks"));
		ehfTDSAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
		ehfTDSAudit.setCrtDt(new Timestamp(new Date().getTime()));
		ehfTDSAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
		ehfTDSAudit.setLangId(ClaimsConstants.LANG_ID);
		ehfTDSAudit = genericDao.save(ehfTDSAudit);
		
		if(caseStatus!=null && (ClaimsConstants.CLAIM_SURPLUS_TDS_DED).equalsIgnoreCase(caseStatus))
		{
			lQueryBuffer = new StringBuffer();
			lQueryBuffer
			.append(" select max(au.id.actOrder) as COUNT from EhfTdsAudit au where au.id.tdsPaymentId=? ");

    arg = new String[1];
    String surCaseSetId="";
    if(lStrCaseNo!=null)
	 surCaseSetId=(lStrCaseNo.substring(0, lStrCaseNo.indexOf("/TDS"))+"/SD/TDS");
	arg[0] = surCaseSetId;


	tdsAuditDtls = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
			lQueryBuffer.toString(), arg);
			 lintActOrder = 1L;
			if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
					&& tdsAuditDtls.get(0).getCOUNT() != null) {
				if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
					lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
			}
			 ehfTDSAudit = new EhfTdsAudit();
			 ehfTDSAuditId = new EhfTdsAuditId();

			ehfTDSAuditId.setTdsPaymentId(surCaseSetId);
			ehfTDSAuditId.setActOrder(lintActOrder);
			ehfTDSAudit.setId(ehfTDSAuditId);
			ehfTDSAudit.setActId(lStrActId);
			ehfTDSAudit.setActBy((String) lparamMap.get("CRTUSER"));
			ehfTDSAudit.setRemarks((String) lparamMap.get("TdsRemarks"));
			ehfTDSAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
			ehfTDSAudit.setCrtDt(new Timestamp(new Date().getTime()));
			ehfTDSAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfTDSAudit.setLangId(ClaimsConstants.LANG_ID);
			ehfTDSAudit = genericDao.save(ehfTDSAudit);
			
		}
		
	}

	/**
	 * Sets the trust audit details.
	 * 
	 * @param lparamMap
	 *            the new trust audit details
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public void setTrustAuditDetails(HashMap lparamMap) throws Exception {
		String lStrCaseNo = (String) lparamMap.get("Trust_PAYMENT_ID");
		//String lStrActId = (String) lparamMap.get("Trust_STAT_ID");
		List<ClaimsFlowVO> tdsAuditDtls = new ArrayList<ClaimsFlowVO>();
		Date currentDate = (Date) lparamMap.get("currentDate");
		StringBuffer lQueryBuffer = new StringBuffer();
		String caseStatus = (String) lparamMap.get("caseStatus");
		
		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfRevFundAudit au where au.id.rfPaymentId=? ");

		String[] arg = new String[1];
		arg[0] = lStrCaseNo;

		tdsAuditDtls = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
				lQueryBuffer.toString(), arg);
		
		Long lintActOrder = 1L;
		if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
				&& tdsAuditDtls.get(0).getCOUNT() != null) {
			if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
				lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
		}

		EhfRevFundAudit ehfRfAudit = new EhfRevFundAudit();
		EhfRevFundAuditId ehfRfAuditId = new EhfRevFundAuditId();

		ehfRfAuditId.setRfPaymentId((String) lparamMap.get("Trust_PAYMENT_ID"));
		ehfRfAuditId.setActOrder(lintActOrder);

		ehfRfAudit.setId(ehfRfAuditId);
		;
		ehfRfAudit.setActId((String) lparamMap.get("nextCaseStatus"));
		ehfRfAudit.setActBy((String) lparamMap.get("CRTUSER"));
		ehfRfAudit.setRemarks((String) lparamMap.get("TrustRemarks"));
		ehfRfAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
		ehfRfAudit.setCrtDt(currentDate);
		ehfRfAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
		ehfRfAudit.setLangId(ClaimsConstants.LANG_ID);
		ehfRfAudit = genericDao.save(ehfRfAudit);
		
		if(caseStatus!=null && (ClaimsConstants.CLAIM_SURPLUS_TDS_DED).equalsIgnoreCase(caseStatus))
		{
			lQueryBuffer = new StringBuffer();
			lQueryBuffer
			.append(" select max(au.id.actOrder) as COUNT from EhfTdsAudit au where au.id.tdsPaymentId=? ");

    arg = new String[1];
    String surCaseSetId="";
    if(lStrCaseNo!=null)
	 surCaseSetId=(lStrCaseNo.substring(0, lStrCaseNo.indexOf("/G"))+"/SD/G");
	arg[0] = surCaseSetId;


	tdsAuditDtls = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
			lQueryBuffer.toString(), arg);
			 lintActOrder = 1L;
			if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
					&& tdsAuditDtls.get(0).getCOUNT() != null) {
				if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
					lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
			}
			ehfRfAudit = new EhfRevFundAudit();
			ehfRfAuditId = new EhfRevFundAuditId();

			ehfRfAuditId.setRfPaymentId(surCaseSetId);
			ehfRfAuditId.setActOrder(lintActOrder);
			ehfRfAudit.setId(ehfRfAuditId);
			ehfRfAudit.setActId((String) lparamMap.get("nextCaseStatus"));
			ehfRfAudit.setActBy((String) lparamMap.get("CRTUSER"));
			ehfRfAudit.setRemarks((String) lparamMap.get("TrustRemarks"));
			ehfRfAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
			ehfRfAudit.setCrtDt(new Timestamp(new Date().getTime()));
			ehfRfAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfRfAudit.setLangId(ClaimsConstants.LANG_ID);
			ehfRfAudit = genericDao.save(ehfRfAudit);
			
		}
		
	}

	/**
	 * Reading files sent by bank and changing the status
	 * 
	 * @param HashMap
	 *            the lparamMap
	 * @return String
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Transactional
	public String SetClaimStatus(HashMap lparamMap) {
		String lResult = "False";
		ArrayList lDataList = new ArrayList();
		int i = 0, lFlag = 0, iStatus = 0, fileStatusConut = 0;
		String lStrCaseid = "",lStrClaimAmt="", lStrACNo = "", lStrStatus = "", lStrTransDt = "", lStrTransId = "", lStrPaymentUid = null;
		String lStrClaimPreStatus = "",lStrClaimStatus = "", lStrRemarks = "", lStrRembsStatus = "", lStrPaidDate = null, lStrRejCode = null;
		List<Map<String,String>> rejCaseList=new ArrayList<Map<String,String>>();
		String rejectedCasesRemarks=null;
		boolean charContains = false, charContainsTDS = true;String rejectedCases=(String) lparamMap.get("RejectedCases");
		try {
			lDataList = (ArrayList) lparamMap.get("DataList");
			lStrClaimPreStatus = (String) lparamMap.get("ClamsInProgerss");
			fileStatusConut = Integer.parseInt((String) lparamMap
					.get("FileConut"));
            //Reading file content
			int DataList = lDataList.size();
			for (int j = 0; j < DataList; j = j + 9) 
			{
				lFlag = 0;
				lStrCaseid = (String) lDataList.get(j);
				lStrClaimAmt = (String) lDataList.get(j + 7);
				double clmAmt=Double.parseDouble(lStrClaimAmt);
				clmAmt=clmAmt/100;
				lStrClaimAmt=String.valueOf(clmAmt);
				
				
				lStrTransDt = (String) lDataList.get(j + 2);
			
				lStrTransId = (String) lDataList.get(j + 1);
				lStrPaymentUid = (String) lDataList.get(j + 1);
				
				lStrStatus = (String) lDataList.get(j + 3);
				lStrACNo = (String) lDataList.get(j + 5);
				lStrRemarks = (String) lDataList.get(j + 4);
				lStrPaidDate = (String) lDataList.get(j + 2); 
				String rejCode=(String) lDataList.get(j + 3);
				if(rejCode!=null && rejCode.length()>0 && rejCode.contains("E"))
				lStrRejCode = rejCode; 
				
				if (lStrPaidDate.trim().length() == 0) {
					lStrPaidDate = null;
				}

				lparamMap.put("TRANS_ID", lStrTransId);
				lparamMap.put("TRANS_DT", lStrTransDt);
				lparamMap.put("CASE_ID", lStrCaseid);
				lparamMap.put("SBH_PAID_DATE", lStrPaidDate);
				lparamMap.put("REJ_CODE", lStrRejCode);
				lparamMap.put("PAYMENT_UID", lStrPaymentUid);
                //For Claim Paid Status
				if (lStrStatus.equals("S00")) 
				{   //TDS
					if (lStrCaseid.endsWith("TDS")) {
						//Err-TDS
						if (lStrCaseid.endsWith("/E/TDS")) {
							lStrClaimStatus = (String) lparamMap
									.get("ErroneousClamPaid");
						} else if (lStrCaseid.endsWith("/1/TDS")
								|| lStrCaseid.endsWith("/2/TDS")
								|| lStrCaseid.endsWith("/3/TDS")
								|| lStrCaseid.endsWith("/4/TDS")) { //FollowUp_TDS
							lStrClaimStatus = (String) lparamMap
									.get("FlupClamPaid");
						} else {
							//normal_tds
							lStrClaimStatus = (String) lparamMap
									.get("ClamSettled");
						}
					} else {
						//For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap.get("ClamSettled");
						lStrRembsStatus = (String) lparamMap
								.get("RembsClamPaid"); 
					}
					lStrRemarks = (String) lparamMap.get("ClaimPaidRemarks");
					//For Claim Rejected By Bank
				} else if (lStrStatus.contains("E")) // 035
				{   //TDS
					rejectedCases = rejectedCases+lStrCaseid+" , ";
					if(rejectedCasesRemarks!=null)
						rejectedCasesRemarks=rejectedCasesRemarks+","+lStrRemarks;
					else
						rejectedCasesRemarks=lStrRemarks;
					
					if (lStrCaseid.endsWith("TDS")) {
						if (lStrCaseid.endsWith("/E/TDS")) {  //Err-TDS
							lStrClaimStatus = (String) lparamMap
									.get("ErroneousClaimRej");
						} else if (lStrCaseid.endsWith("/1/TDS")
								|| lStrCaseid.endsWith("/2/TDS")
								|| lStrCaseid.endsWith("/3/TDS")
								|| lStrCaseid.endsWith("/4/TDS")) {  //FollowUp_TDS
							lStrClaimStatus = (String) lparamMap
									.get("FlupClaimRej");
						} else {
							//normal_tds
							lStrClaimStatus = (String) lparamMap
									.get("ClaimFailed");
						}
					} else { //For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap.get("ClaimFailed");
						lStrRembsStatus = (String) lparamMap
								.get("RembsClaimRej");
					}
				} else if (lStrStatus.equals("S01"))  //For Acknowledge Status
				{    //TDS
					if (lStrCaseid.endsWith("TDS")) {
						if (lStrCaseid.endsWith("/E/TDS")) {  //Err-TDS
							lStrClaimStatus = (String) lparamMap
									.get("ErroneousClaimAck");
						} else if (lStrCaseid.endsWith("/1/TDS")
								|| lStrCaseid.endsWith("/2/TDS")
								|| lStrCaseid.endsWith("/3/TDS")
								|| lStrCaseid.endsWith("/4/TDS")) {   //FollowUp_TDS
							lStrClaimStatus = (String) lparamMap
									.get("FlupAcknowledgmentRcvd");
						} else { //normal_tds
							lStrClaimStatus = (String) lparamMap
									.get("AcknowledgmentRcvd");
						}
					} else { //For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap
								.get("AcknowledgmentRcvd");
						lStrRembsStatus = (String) lparamMap
								.get("RembsAcknowledgmentRcvd");
					}
					lStrRemarks = (String) lparamMap.get("ClaimAckRemarks");
				} else {
					lFlag = 1;

				}
				
				lparamMap.put("REMARKS", lStrRemarks);
				lparamMap.put("TransStatus", lStrStatus);
				lparamMap.put("STAT_ID", lStrClaimStatus);
				lparamMap.put("CASE_FOLLOWUP_ID", lStrCaseid);
				lparamMap.put("CASE_TYPE", "REV_FUND");
				
				lparamMap.put("RejectedCases",rejectedCases);
				
				if (lFlag != 1) {
					iStatus = 0;
					charContains = false;
					charContainsTDS = false;
					if (lStrTransDt.trim().length() > 1 && lStrTransDt != null) { 
																					
						if (lStrCaseid.endsWith(ClaimsConstants.G)) {
							charContains = true;
						}
						if (lStrCaseid.endsWith(ClaimsConstants.TDS)) {
							charContainsTDS = true;
						}
												
						if (charContains) {
							iStatus = updateRevolvingFundsDetails(lparamMap);  //Updating RF Details acc to Status
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccTdsRfNew(lStrCaseid,lStrClaimAmt,lStrTransDt);
						}
						
						if (charContainsTDS) {
							iStatus = updateTDSPaymentDetails(lparamMap);     //Updating TDS Details acc to Status
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccTdsRfNew(lStrCaseid,lStrClaimAmt,lStrTransDt);
						}
						if (charContains == false && charContainsTDS == false) {
							iStatus = updatePaymentDetails(lparamMap);        //Updating Claim Details acc to Status  
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccountsTransactionNew(lStrCaseid,lStrClaimAmt,         //Reverting details in Accounts tables iF Rejected
										lStrTransDt);
						}

					}

					if (iStatus > 0                              //&& (lStrCaseid.endsWith("R") == false)
							&& charContains == false
							&& charContainsTDS == false) {
						StringBuffer lStrBuffer = new StringBuffer();

						EhfCase ehfCase = genericDao.findById(EhfCase.class,
								String.class, lStrCaseid);
						ehfCase.setLstUpdDt(new Timestamp(new Date().getTime()));
						ehfCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
						ehfCase.setCaseStatus(lStrClaimStatus);
						ehfCase = genericDao.save(ehfCase);

						if (ehfCase != null) {
							setCaseAudit(lparamMap);
							lResult = ClaimsConstants.TRUE;
						} else
							lResult = ClaimsConstants.FALSE;
					} else if (iStatus > 0 && charContains == true) {
						setRevFundAuditDetails(lparamMap);
						lResult = ClaimsConstants.TRUE;
					} else if (iStatus > 0 && charContainsTDS == true) {
						    setTDSAudit(lparamMap);
							lResult = ClaimsConstants.TRUE;
					}
					else{
						lResult = ClaimsConstants.FALSE;
					}
					
				}

			}

			if (ClaimsConstants.TRUE.equalsIgnoreCase(lResult)) {
				
				String fileName=(String) lparamMap.get("FileName")+".xml";
					EhfClaimUploadFile ehfClaimUploadFile = null;
					ehfClaimUploadFile = genericDao.findById(
							EhfClaimUploadFile.class, String.class,fileName);
					if (ehfClaimUploadFile.getFileStatus() != null
							&& ehfClaimUploadFile.getFileStatus().equalsIgnoreCase(
									(String) lparamMap.get("SentStatus"))) {
						ehfClaimUploadFile.setLstUpdDate(new Timestamp(new Date()
								.getTime()));
						ehfClaimUploadFile.setFileStatus((String) lparamMap
								.get("ClosedStatus"));
					}
					ehfClaimUploadFile = genericDao.save(ehfClaimUploadFile);
					
			}
		 
			//IF Email required
			if (config.getBoolean("EmailRequired")) {
				if (lparamMap.get("RejectedCases") != null && lparamMap.get("RejectedCases") !="" ) {
					
					
					if(rejectedCases!=null && !"".equalsIgnoreCase(rejectedCases))
					{
						StringTokenizer rc=new StringTokenizer(rejectedCases," , ");
						String hospName=null,hospId=null;
						String[] rejRemarks=null;
						int ct=0;
						if(rejectedCasesRemarks!=null)
							rejRemarks=rejectedCasesRemarks.split(",");
						while(rc.hasMoreElements())
							{
								Map<String,String> rejCase=new HashMap<String,String>();
								StringBuilder sbl=new StringBuilder();
								String caseIdRej=rc.nextElement().toString();
								if(caseIdRej!=null)
									rejCase.put("caseId",caseIdRej);
								StringBuffer sb =new StringBuffer();
								sb.append(" select eh.hospName as VALUE,eh.hospId as ID ");
								sb.append(" from EhfCase ec,EhfmHospitals eh where ec.caseHospCode=eh.hospId");
								sb.append(" and ec.caseId='"+caseIdRej+"'");
								List<LabelBean> lb=new ArrayList<LabelBean>();
								lb=genericDao.executeHQLQueryList(LabelBean.class,sb.toString());
								if(lb!=null && lb.size()>0)
									{
										for(LabelBean lb1:lb)
											{
												if(lb1.getVALUE()!=null)
													{
														hospName=lb1.getVALUE();
														sbl.append(hospName);
													}
												if(lb1.getID()!=null)
													{
														hospId=lb1.getID();
														sbl.append("("+hospId+")");
														
														rejCase.put("hospDtls",sbl.toString());
													}
												if(lb1.getVALUE()!=null||lb1.getID()!=null)
													{
														//sbl.append("\t-\t"+rejRemarks[ct]+"");
														rejCase.put("remarks",rejRemarks[ct]);
													}
												//sbl.append("\r\n");
											}
									}
								ct++;
								rejCaseList.add(rejCase);
							}
					}
					
					String msgEmailTo= config.getString("claimRejectedCaseEmail");
					
		    		//StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
		    		//while (token.hasMoreElements()) {
		    			//String mailId=(String) token.nextElement();
		    			//String[] emailToArray = { mailId };
					if(msgEmailTo!=null)
					{
						String[] emailToArray=msgEmailTo.split("#");
		    			EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						emailVO.setTemplateName(config
							 	.getString("EhfRejectedCasePayment"));
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("rejectedCasesPayment"));
						Map<String, List<Map<String,String>>> model = new HashMap<String, List<Map<String,String>>>();
						model.put("caseNo",
								rejCaseList );//lparamMap.get("RejectedCases")
						//generating Email
						commonService.generateNonImageMail(emailVO, model);
		    		}
			}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Error:SetClaimStatus method in ClaimsPaymentDAOImpl with message "+e);
		}

		return lResult;
	} 
	/**
	 * This method is used to update ehf_accts_transaction_dtls table if paymentgot rejected.
	 * 
	 * @param lStrCaseid
	 *            the lStr caseid
	 * @param lStrTransDt
	 *            the lStrTrans dt
	 */
	
	@Transactional
	private void updateAccountsTransaction(String lStrCaseid, String lStrTransDt) {
		
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("transId", lStrCaseid,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("active_yn",
				ClaimsConstants.Y,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfAcctsTransactionDtls> ehfAccountTrans = genericDao
				.findAllByCriteria(EhfAcctsTransactionDtls.class, criteriaList);
		if (ehfAccountTrans != null && ehfAccountTrans.size() > 0) {
			for (EhfAcctsTransactionDtls ehfAcctTranDtls : ehfAccountTrans) {
				EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();

				ehfAcctsTransactionDtlsEntry.setVoucherId(ehfAcctTranDtls
						.getVoucherId() + "/R");
				ehfAcctsTransactionDtlsEntry.setTransId(ehfAcctTranDtls
						.getTransId());
				ehfAcctsTransactionDtlsEntry.setDebtAccount(ehfAcctTranDtls
						.getCreditAccount());
				ehfAcctsTransactionDtlsEntry.setCreditAccount(ehfAcctTranDtls
						.getDebtAccount());
				ehfAcctsTransactionDtlsEntry.setAmount(ehfAcctTranDtls
						.getAmount());
				ehfAcctsTransactionDtlsEntry.setNarration(ehfAcctTranDtls
						.getNarration());
				ehfAcctsTransactionDtlsEntry.setTransDate(new Date());
				ehfAcctsTransactionDtlsEntry.setPaymentType(ehfAcctTranDtls
						.getPaymentType());
				ehfAcctsTransactionDtlsEntry.setVoucherType(ehfAcctTranDtls
						.getVoucherType());
				ehfAcctsTransactionDtlsEntry.setScheme(ehfAcctTranDtls
						.getScheme());
				ehfAcctsTransactionDtlsEntry.setSection(ehfAcctTranDtls
						.getSection());
				ehfAcctsTransactionDtlsEntry.setCaseId(ehfAcctTranDtls
						.getCaseId());
				ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.N);
				ehfAcctsTransactionDtlsEntry.setCrtDt(new Date());
				try {
					if (lStrTransDt != null)
						ehfAcctsTransactionDtlsEntry.setTransDate(df
								.parse(lStrTransDt));
					else
						ehfAcctsTransactionDtlsEntry.setTransDate(new Date());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ehfAcctsTransactionDtlsEntry.setCrtUsr(config
						.getString("ACC.TCS"));
				ehfAcctsTransactionDtlsEntry.setUniqueTxn(ehfAcctTranDtls
						.getUniqueTxn());
				ehfAcctsTransactionDtlsEntry = genericDao
						.save(ehfAcctsTransactionDtlsEntry);

				ehfAcctTranDtls.setActive_yn(ClaimsConstants.N);
				ehfAcctTranDtls = genericDao.save(ehfAcctTranDtls);
			}
		}
	}
	/**
	 * This Method is to update the Payment Details into Claims-Accounts Table
	 * 
	 * @param lparamMap
	 *            the lparam map
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public int updatePaymentDetails(HashMap lparamMap) throws Exception {
		int iResult = 0;
		
		String lStrStatus = (String) lparamMap.get("TransStatus");
		long timeMillSec = 0;
		String lStrCaseid = "";
		
		EhfClaimAccounts ehfClaimAcc = null;
		try {
			List<String> colValues = new ArrayList<String>();
			TimeStamp t = new TimeStamp();
			timeMillSec = t.getTimeStampFromDate((String) lparamMap
					.get("TRANS_DT"));
			
			lStrCaseid = (String) lparamMap.get("CASE_ID");

			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("caseId", lStrCaseid,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("timeMilSec",
					timeMillSec, GenericDAOQueryCriteria.CriteriaOperator.LT));
			if (lStrStatus.equals("S01")) {
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.equals("S00")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.contains("E")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			}
			List<EhfClaimAccounts> ehfClaimAccount = genericDao
					.findAllByCriteria(EhfClaimAccounts.class, criteriaList);
			if (ehfClaimAccount.size() > 0) {
				ehfClaimAcc = ehfClaimAccount.get(0);
				ehfClaimAcc
						.setTransactionId((String) lparamMap.get("TRANS_ID"));
				if ((String) lparamMap.get("TRANS_DT") != null)
					ehfClaimAcc.setTransactionDt(sdf.parse((String) lparamMap
							.get("TRANS_DT")));
				ehfClaimAcc.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
				ehfClaimAcc.setLstUpdDt(new Timestamp(new Date().getTime()));
				ehfClaimAcc.setRemarks((String) lparamMap.get("REMARKS"));
				ehfClaimAcc.setTimeMilSec(timeMillSec);
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					ehfClaimAcc.setSbhPaidDate(sdf.parse((String) lparamMap
							.get("SBH_PAID_DATE")));

				if (lStrStatus.equals("S01")) {
					ehfClaimAcc.setTransStatus((String) lparamMap
							.get("TransAckStatus"));
				} else if (lStrStatus.equals("S00")) {
					ehfClaimAcc.setTransStatus((String) lparamMap
							.get("TransPaidStatus"));
				} else if (lStrStatus.contains("E")) {
					ehfClaimAcc.setTransStatus((String) lparamMap
							.get("TransRejStatus"));
				}
				ehfClaimAcc = genericDao.save(ehfClaimAcc);

				if (ehfClaimAcc != null) {

					EhfCase ehfCase = genericDao.findById(EhfCase.class,
							String.class, lStrCaseid);
					ehfCase.setLstUpdDt(new Timestamp(new Date().getTime()));
					ehfCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
					ehfCase.setCsTransId((String) lparamMap.get("TRANS_ID"));
					if ((String) lparamMap.get("TRANS_DT") != null)
						ehfCase.setCsTransDt(sdf.parse((String) lparamMap
								.get("TRANS_DT")));
					if ((String) lparamMap.get("SBH_PAID_DATE") != null)
						ehfCase.setCsSbhDt(sdf.parse((String) lparamMap
								.get("SBH_PAID_DATE")));
					ehfCase.setCsRemarks((String) lparamMap.get("REMARKS"));
					ehfCase = genericDao.save(ehfCase);
					if (ehfCase != null)
						iResult = 1;
				}
			}
		} catch (Exception e) {
			logger.error("Error:updatePaymentDetails method in ClaimsPaymentDAOImpl with message "+e);
			e.printStackTrace();
		}
		return iResult;

	}

	/**
	 * Sets the case audit.
	 * 
	 * @param lparamMap
	 *            the new case audit
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public void setCaseAudit(HashMap lparamMap) throws Exception {
		String lStrCaseId = (String) lparamMap.get("CASE_ID");
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		Long lActOrder = 0L;
		try {
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

			// insert into ehf_audit
			EhfAudit asritAudit = new EhfAudit();
			EhfAuditId asritAuditPK = new EhfAuditId(lActOrder, lStrCaseId);
			asritAudit.setId(asritAuditPK);
			asritAudit.setActId((String) lparamMap.get("STAT_ID"));
			asritAudit.setActBy((String) lparamMap.get("UPD_USR"));
			asritAudit.setCrtUsr((String) lparamMap.get("UPD_USR"));
			asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			asritAudit.setLangId(ClaimsConstants.LANG_ID);
			asritAudit.setRemarks((String) lparamMap.get("REMARKS"));
			asritAudit.setUserRole(ClaimsConstants.CEO_GRP_ID);
			asritAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				asritAudit.setSbhPaidDate(sdf.parse((String) lparamMap
						.get("SBH_PAID_DATE")));
			asritAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
			asritAudit.setRejCode((String) lparamMap.get("REJ_CODE"));
			if((String) lparamMap.get("FileName")!=null)
			asritAudit.setFilename((String) lparamMap.get("SRC_FILE"));

			genericDao.save(asritAudit);
		} catch (Exception e) {
			logger.error("Error:setCaseAudit method in ClaimsPaymentDAOImpl with message "+e);
			e.printStackTrace();
		}
	}

	/*
	 * This Method is to change the status from SentForClaimSettled to ClaimSettled or ClaimFailed for Erroneous Claims
	  * @param HashMap
	 *            the lparamMap
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String processErroneousClaims(HashMap lparamMap) {

		String lResult = "False";
		ArrayList lDataList = new ArrayList();
		int lFlag = 0, iStatus = 0;
		String lStrClaimAmt = "", lStrCaseid = null, lStrACNo = null, lStrStatus = null, lStrTransDt = null, lStrTransId = null;
		String lStrClaimStatus = null, lStrRemarks = null, lStrPaidDate = null, lStrRejCode = null, lStrPaymentUid = null;
		boolean charContainsTDS = false, charContainsG = false;

		try {

			lDataList = (ArrayList) lparamMap.get("DataList");

			int DataList = lDataList.size();
			for (int j = 0; j < DataList; j = j + 9) 
			{
				lFlag = 0;
				lStrCaseid = (String) lDataList.get(j);
				lStrClaimAmt = (String) lDataList.get(j + 7);
				double clmAmt=Double.parseDouble(lStrClaimAmt);
				clmAmt=clmAmt/100;
				lStrClaimAmt=String.valueOf(clmAmt);
				
				
				lStrTransDt = (String) lDataList.get(j + 2);
			
				lStrTransId = (String) lDataList.get(j + 1);
				lStrPaymentUid = (String) lDataList.get(j + 1);
				
				lStrStatus = (String) lDataList.get(j + 3);
				lStrACNo = (String) lDataList.get(j + 5);
				lStrRemarks = (String) lDataList.get(j + 4);
				lStrPaidDate = (String) lDataList.get(j + 2); 
				
				String rejCode=(String) lDataList.get(j + 3); 
				if(rejCode!=null && rejCode.length()>0 && rejCode.contains("E"))
			    lStrRejCode = rejCode; 
			
				if (lStrPaidDate.trim().length() == 0) {
					lStrPaidDate = null;
				}

				lparamMap.put("CASE_ID", lStrCaseid);
				lparamMap.put("TRANS_ID", lStrTransId);
				lparamMap.put("TRANS_DT", lStrTransDt);
				lparamMap.put("SBH_PAID_DATE", lStrPaidDate);
				lparamMap.put("REJ_CODE", lStrRejCode);
				lparamMap.put("PAYMENT_UID", lStrPaymentUid);

				if (lStrStatus.equals("S00")) {
					lStrClaimStatus = (String) lparamMap
							.get("ErroneousClamPaid");
					lStrRemarks = (String) lparamMap.get("ClaimPaidRemarks");

				} else if (lStrStatus.contains("E")) {
					lStrClaimStatus = (String) lparamMap
							.get("ErroneousClaimRej");

				} else if (lStrStatus.equals("S01")) {
					lStrClaimStatus = (String) lparamMap
							.get("ErroneousClaimAck");
					lStrRemarks = (String) lparamMap.get("ClaimAckRemarks");

				} else {
					lFlag = 1;

				}

				lparamMap.put("REMARKS", lStrRemarks);
				lparamMap.put("TransStatus", lStrStatus);
				lparamMap.put("STAT_ID", lStrClaimStatus);
				lparamMap.put("CASE_ID", lStrCaseid);

				if (lFlag != 1) {
					iStatus = 0;
					charContainsTDS = lStrCaseid.endsWith("TDS");
					charContainsG = lStrCaseid.endsWith("G");
					if (charContainsTDS) {
						iStatus = updateTDSPaymentDetails(lparamMap);
						if (iStatus > 0 && lStrStatus.contains("E"))
							updateAccTdsRfNew(lStrCaseid,lStrClaimAmt,lStrTransDt);
						if (iStatus > 0) {
							setTDSAudit(lparamMap);
							lResult = "True";
						} else {
							lResult = "False";
						}
					} else if (charContainsG)
					{
						lparamMap.put("CASE_TYPE", "REV_FUND");
						lparamMap.put("CASE_FOLLOWUP_ID", lStrCaseid);
						iStatus = updateRevolvingFundsDetails(lparamMap);
						if (iStatus > 0 && lStrStatus.contains("E"))
							updateAccTdsRfNew(lStrCaseid,lStrClaimAmt,lStrTransDt);
						if (iStatus > 0) {
							setRevFundAuditDetails(lparamMap);
							lResult = "True";
						} else {
							lResult = "False";
						}
					}
					else {
						lStrCaseid = lStrCaseid.substring(0,
								lStrCaseid.indexOf("/"));
						lparamMap.put("CASE_ID", lStrCaseid);
						iStatus = 0;
						if (lStrTransDt.trim().length() > 1
								&& lStrTransDt != null) {
							iStatus = updateErroneousPayments(lparamMap);
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccountsTransactionNew(lStrCaseid,lStrClaimAmt,         //Reverting details in Accounts tables iF Rejected
										lStrTransDt);
						}

						if (iStatus > 0) {
							setCaseAudit(lparamMap);
							lResult = "True";
						} else {
							lResult = "False";
						}
					}
				}

			}
			
			if (ClaimsConstants.TRUE.equalsIgnoreCase(lResult)) { 
				String fileName=(String) lparamMap.get("FileName")+".xml";
				EhfClaimUploadFile ehfClaimUploadFile = null;
				ehfClaimUploadFile = genericDao.findById(
						EhfClaimUploadFile.class, String.class,fileName);
				if (ehfClaimUploadFile.getFileStatus() != null
						&& ehfClaimUploadFile.getFileStatus().equalsIgnoreCase(
								(String) lparamMap.get("SentStatus"))) {
					ehfClaimUploadFile.setLstUpdDate(new Timestamp(new Date()
							.getTime()));
					ehfClaimUploadFile.setFileStatus((String) lparamMap
							.get("ClosedStatus"));
				}
				ehfClaimUploadFile = genericDao.save(ehfClaimUploadFile);
			
			}

		} catch (Exception e) {
			logger.error("Error:processErroneousClaims method in ClaimsPaymentDAOImpl with message "+e);
			e.printStackTrace();
		}

		return lResult;

	}
	/**
	 *  This Method is to update the Payment Details into Erroneous Claims Tables
	 * 
	 * @param lparamMap
	 *            the lparam map
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public int updateErroneousPayments(HashMap lparamMap) throws Exception {
		int iResult = 0;
		String lStrStatus = (String) lparamMap.get("TransStatus");
		long timeMillSec = 0;
		String lStrCaseid = (String) lparamMap.get("CASE_ID");
		List<String> colValues = new ArrayList<String>();
		try {
			TimeStamp t = new TimeStamp();
			timeMillSec = t.getTimeStampFromDate((String) lparamMap
					.get("TRANS_DT"));
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("caseId", lStrCaseid,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			if (lStrStatus.equals("S01")) {
				colValues.add((String) lparamMap.get("ErroneousClamsSent"));
				colValues.add((String) lparamMap.get("ErroneousClaimRej"));
				criteriaList
						.add(new GenericDAOQueryCriteria("errClaimStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.equals("S00")) {
				colValues.add((String) lparamMap.get("ErroneousClaimAck"));
				colValues.add((String) lparamMap.get("ErroneousClamsSent"));
				colValues.add((String) lparamMap.get("ErroneousClaimRej"));
				criteriaList
						.add(new GenericDAOQueryCriteria("errClaimStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.contains("E")) {
				colValues.add((String) lparamMap.get("ErroneousClaimAck"));
				colValues.add((String) lparamMap.get("ErroneousClamsSent"));
				colValues.add((String) lparamMap.get("ErroneousClaimPaid"));
				colValues.add((String) lparamMap.get("ErroneousClaimRej"));
				criteriaList
						.add(new GenericDAOQueryCriteria("errClaimStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			}
			List<EhfErroneousClaim> ehfClaimErrClaim = genericDao
					.findAllByCriteria(EhfErroneousClaim.class, criteriaList);

			if (ehfClaimErrClaim.size() > 0) {
				EhfErroneousClaim ehfErrClaim = ehfClaimErrClaim.get(0);
				if (ehfErrClaim != null) {
					EhfClaimAccounts ehfClaimAccounts = genericDao.findById(
							EhfClaimAccounts.class, String.class, lStrCaseid
									+ "/E");
					if (ehfClaimAccounts.getTimeMilSec() < timeMillSec) {
						ehfClaimAccounts.setTransactionId((String) lparamMap
								.get("TRANS_ID"));
						if ((String) lparamMap.get("TRANS_DT") != null)
							ehfClaimAccounts.setTransactionDt(sdf
									.parse((String) lparamMap.get("TRANS_DT")));
						ehfClaimAccounts.setLstUpdDt(new Timestamp(new Date()
								.getTime()));
						ehfClaimAccounts.setLstUpdUsr((String) lparamMap
								.get("UPD_USR"));
						ehfClaimAccounts.setRemarks((String) lparamMap
								.get("REMARKS"));
						if ((String) lparamMap.get("SBH_PAID_DATE") != null)
							ehfClaimAccounts.setSbhPaidDate(sdf
									.parse((String) lparamMap
											.get("SBH_PAID_DATE")));
						ehfClaimAccounts.setTimeMilSec(timeMillSec);
						if (lStrStatus.equals("S01")) {
							ehfClaimAccounts.setTransStatus((String) lparamMap
									.get("TransAckStatus"));
						} else if (lStrStatus.equals("S00")) {
							ehfClaimAccounts.setTransStatus((String) lparamMap
									.get("TransPaidStatus"));
						} else if (lStrStatus.contains("E")) {
							ehfClaimAccounts.setTransStatus((String) lparamMap
									.get("TransRejStatus"));
						}
						ehfClaimAccounts = genericDao.save(ehfClaimAccounts);
						if (ehfClaimAccounts != null) {
							ehfErrClaim.setErrClaimStatus((String) lparamMap
									.get("STAT_ID"));

						}
					}
				}
			}

			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,
					(String) lparamMap.get("CASE_ID"));
			ehfCase.setLstUpdDt(new Timestamp(new Date().getTime()));
			ehfCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
			ehfCase.setErrClaimStatus((String) lparamMap.get("STAT_ID"));
			ehfCase = genericDao.save(ehfCase);
			if (ehfCase != null)
				iResult = 1;

		} catch (Exception e) {
			logger.error("Error:updateErroneousPayments method in ClaimsPaymentDAOImpl with message "+e);
			e.printStackTrace();
		}
		return iResult;

	}
	/**
	 * Reading files sent by bank and changing the status for followup
	 * 
	 * @param HashMap
	 *            the lparamMap
	 * @return String
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Transactional
	public String SetFollowupClaimStatus(HashMap lparamMap) {
		String lResult = "False";
		ArrayList lDataList = new ArrayList();
		int i = 0, lFlag = 0, iStatus = 0, fileStatusConut = 0, lPaymentCount = 0;
		String lStrCaseid = "", lStrACNo = "", lStrStatus = "", lStrTransDt = "", lStrTransId = "", lStrPaymentUid = null;
		String lStrClaimPreStatus = "", lStrClaimStatus = "", lStrRemarks = "", lStrPaidDate = "", lStrRejCode = null;
		String lStrClaimType = "";
		String lStrClaimAnt = "";

		try {
			lStrClaimType = (String) lparamMap.get("CASE_TYPE");
			lDataList = (ArrayList) lparamMap.get("DataList");
			lStrClaimPreStatus = (String) lparamMap.get("FlupClamsSent");

			fileStatusConut = Integer.parseInt((String) lparamMap
					.get("FileConut"));

			int DataList = lDataList.size();
			for (int j = 0; j < DataList; j = j + 9) 
			{
				lFlag = 0;
				lStrCaseid = (String) lDataList.get(j);
				lStrClaimAnt = (String) lDataList.get(j + 7);
				double clmAmt=Double.parseDouble(lStrClaimAnt);
				clmAmt=clmAmt/100;
				lStrClaimAnt=String.valueOf(clmAmt);
				
				lStrTransDt = (String) lDataList.get(j + 2);
			
				lStrTransId = (String) lDataList.get(j + 1);
				lStrPaymentUid = (String) lDataList.get(j + 1);
				
				lStrStatus = (String) lDataList.get(j + 3);
				lStrACNo = (String) lDataList.get(j + 5);
				lStrRemarks = (String) lDataList.get(j + 4);
				lStrPaidDate = (String) lDataList.get(j + 2); 
				
				String rejCode=(String) lDataList.get(j + 3); 
				if(rejCode!=null && rejCode.length()>0 && rejCode.contains("E"))
			    lStrRejCode = rejCode; 
				
				if (lStrPaidDate.trim().length() == 0) {
					lStrPaidDate = null;
				}

				lparamMap.put("TRANS_ID", lStrTransId);
				lparamMap.put("TRANS_DT", lStrTransDt);
				lparamMap.put("CASE_FOLLOWUP_ID", lStrCaseid);
				lparamMap.put("SBH_PAID_DATE", lStrPaidDate);
				lparamMap.put("REJ_CODE", lStrRejCode);
				lparamMap.put("PAYMENT_UID", lStrPaymentUid);
				lparamMap.put("transAmount", lStrClaimAnt);

				if (lStrStatus.equals("S00")) {
					lStrClaimStatus = (String) lparamMap.get("FlupClamPaid");
					lStrRemarks = (String) lparamMap.get("ClaimPaidRemarks");
				} else if (lStrStatus.contains("E")) {
					lStrClaimStatus = (String) lparamMap.get("FlupClaimRej");
				} else if (lStrStatus.equals("S01")) {
					lStrClaimStatus = (String) lparamMap
							.get("FlupAcknowledgmentRcvd");
					lStrRemarks = (String) lparamMap.get("ClaimAckRemarks");
				} else {
					lFlag = 1;

				}
				lparamMap.put("REMARKS", lStrRemarks);
				lparamMap.put("TransStatus", lStrStatus);
				lparamMap.put("STAT_ID", lStrClaimStatus);

				if (lFlag != 1) {
					iStatus = 0;
					lPaymentCount = 0;
					if (lStrTransDt.trim().length() > 1 && lStrTransDt != null) {
						if (lStrCaseid.endsWith("G")) {
							lPaymentCount = 1;
						}
						if (lStrCaseid.endsWith("TDS")) {
							lPaymentCount = 2;
						}
						if (lPaymentCount == 1) {
							lparamMap.put("CASE_TYPE", "REV_FUND");
							iStatus = updateRevolvingFundsDetails(lparamMap);
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccTdsRfNew(lStrCaseid,lStrClaimAnt,lStrTransDt);
						} else if (lPaymentCount == 2) {
							lparamMap.put("CASE_ID", lStrCaseid);
							iStatus = updateTDSPaymentDetails(lparamMap);
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccTdsRfNew(lStrCaseid,lStrClaimAnt,lStrTransDt);
						} else {
							iStatus = updateFolloupPaymentDetails(lparamMap);
							if (iStatus > 0 && lStrStatus.contains("E"))
								updateAccountsTransactionNew(lStrCaseid,lStrClaimAnt,         //Reverting details in Accounts tables iF Rejected
										lStrTransDt);
						}

					}

					if (iStatus > 0) {
						if (lPaymentCount == 2) {
							lparamMap.put("CASE_TYPE", "CD525");
							setTDSAudit(lparamMap);
						} else if (lPaymentCount == 1) {
							setRevFundAuditDetails(lparamMap);
						} else {
							setFolloupAuditDetails(lparamMap);
						}
						lResult = "True";
					} else {
						lResult = "False";
					}

				}

			}

			if (ClaimsConstants.TRUE.equalsIgnoreCase(lResult)) {
				String fileName=(String) lparamMap.get("FileName")+".xml";
				EhfClaimUploadFile ehfClaimUploadFile = null;
				ehfClaimUploadFile = genericDao.findById(
						EhfClaimUploadFile.class, String.class,fileName);
				if (ehfClaimUploadFile.getFileStatus() != null
						&& ehfClaimUploadFile.getFileStatus().equalsIgnoreCase(
								(String) lparamMap.get("SentStatus"))) {
					ehfClaimUploadFile.setLstUpdDate(new Timestamp(new Date()
							.getTime()));
					ehfClaimUploadFile.setFileStatus((String) lparamMap
							.get("ClosedStatus"));
				}
				ehfClaimUploadFile = genericDao.save(ehfClaimUploadFile);
				
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return lResult;
	}

	/**
	 * This Method is to update the Payment Details into ehf_claim_trust_payment TABLE for err
	 * 
	 * @param lparamMap
	 *            the lparam map
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public int updateRevolvingFundsDetails(HashMap lparamMap) throws Exception {

		int iResult = 0;
		String lStrStatus = (String) lparamMap.get("TransStatus");
		double timeMillSec = 0;
		EhfClaimTrustPayment ehfClaimTrustPay = null;
		String lStrStatusId = (String) lparamMap.get("STAT_ID");// start:032
		if (ClaimsConstants.ERRCLAIM_ACK.equalsIgnoreCase(lStrStatusId)) {
			lStrStatusId = (String) lparamMap.get("AcknowledgmentRcvd");
		}
		if (ClaimsConstants.ERRCLAIM_PAID.equalsIgnoreCase(lStrStatusId)) {
			lStrStatusId = (String) lparamMap.get("ClamSettled");
		}
		if (ClaimsConstants.CLAIM_ERR_REJ.equalsIgnoreCase(lStrStatusId)) {
			lStrStatusId = (String) lparamMap.get("ClaimFailed");
		}// end:032
		try {
			List<String> colValues = new ArrayList<String>();

			TimeStamp t = new TimeStamp();
			timeMillSec = t.getTimeStampFromDate((String) lparamMap
					.get("TRANS_DT"));
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("trustPaymentId",
					(String) lparamMap.get("CASE_FOLLOWUP_ID"),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("timeMilSec",
					timeMillSec, GenericDAOQueryCriteria.CriteriaOperator.LT));
			if (lStrStatus.equals("S01")) {
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.equals("S00")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.contains("E")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			}
			List<EhfClaimTrustPayment> ehfClaimTrustPayment = genericDao
					.findAllByCriteria(EhfClaimTrustPayment.class, criteriaList);
			if (ehfClaimTrustPayment.size() > 0) {
				ehfClaimTrustPay = ehfClaimTrustPayment.get(0);
				ehfClaimTrustPay.setTransactionId((String) lparamMap
						.get("TRANS_ID"));
				if ((String) lparamMap.get("TRANS_DT") != null)
					ehfClaimTrustPay.setTransactionDt(sdf
							.parse((String) lparamMap.get("TRANS_DT")));
				ehfClaimTrustPay
						.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
				ehfClaimTrustPay
						.setLstUpdDt(new Timestamp(new Date().getTime()));
				ehfClaimTrustPay.setRemarks((String) lparamMap.get("REMARKS"));
				ehfClaimTrustPay.setTimeMilSec((double) timeMillSec);
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					ehfClaimTrustPay.setSbhPaidDate(sdf
							.parse((String) lparamMap.get("SBH_PAID_DATE")));
				ehfClaimTrustPay.setPaymentStatus(lStrStatusId);

				if (lStrStatus.equals("S01")) {
					ehfClaimTrustPay.setTransStatus((String) lparamMap
							.get("TransAckStatus"));
				} else if (lStrStatus.equals("S00")) {
					ehfClaimTrustPay.setTransStatus((String) lparamMap
							.get("TransPaidStatus"));
				} else if (lStrStatus.contains("E")) {
					ehfClaimTrustPay.setTransStatus((String) lparamMap
							.get("TransRejStatus"));
				}
				ehfClaimTrustPay = genericDao.save(ehfClaimTrustPay);
				if (ehfClaimTrustPay != null)
					iResult = 1;
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return iResult;

	}
	/**
	 * This Method is to update the Payment Details into ehf_claim_tds_payment
	 * 
	 * @param lparamMap
	 *            the lparam map
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public int updateTDSPaymentDetails(HashMap lparamMap) throws Exception {

		int iResult = 0;
		String lStrStatus = (String) lparamMap.get("TransStatus");
		long timeMillSec = 0;
		List<String> colValues = new ArrayList<String>();
		EhfClaimTdsPayment ehfClaimTdsPay = null;
		try {
			TimeStamp t = new TimeStamp();
			timeMillSec = t.getTimeStampFromDate((String) lparamMap
					.get("TRANS_DT"));
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("tdsPaymentId",
					(String) lparamMap.get("CASE_ID"),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("timeMilliSec",
					timeMillSec, GenericDAOQueryCriteria.CriteriaOperator.LT));
			if (lStrStatus.equals("S01")) {
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			} else if (lStrStatus.equals("S00")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.contains("E")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			}
			List<EhfClaimTdsPayment> ehfClaimTdsPayment = genericDao
					.findAllByCriteria(EhfClaimTdsPayment.class, criteriaList);
			if (ehfClaimTdsPayment.size() > 0) {

				ehfClaimTdsPay = ehfClaimTdsPayment.get(0);
				ehfClaimTdsPay.setTransId((String) lparamMap.get("TRANS_ID"));
				if ((String) lparamMap.get("TRANS_DT") != null)
					ehfClaimTdsPay.setTransDate(sdf.parse((String) lparamMap
							.get("TRANS_DT")));
				ehfClaimTdsPay
						.setLastUpdUser((String) lparamMap.get("UPD_USR"));
				ehfClaimTdsPay.setLastUpdDate(new Timestamp(new Date()
						.getTime()));
				ehfClaimTdsPay.setRemarks((String) lparamMap.get("REMARKS"));
				ehfClaimTdsPay.setTimeMilliSec(timeMillSec);
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					ehfClaimTdsPay.setSbhPaidDate(sdf.parse((String) lparamMap
							.get("SBH_PAID_DATE")));
				ehfClaimTdsPay.setPaymentStatus((String) lparamMap
						.get("STAT_ID"));

				if (lStrStatus.equals("S01")) {
					ehfClaimTdsPay.setTransStatus((String) lparamMap
							.get("TransAckStatus"));
					//ehfClaimTdsPay.setTdsStatus("CD661");
				} else if (lStrStatus.equals("S00")) {
					ehfClaimTdsPay.setTransStatus((String) lparamMap
							.get("TransPaidStatus"));
					//ehfClaimTdsPay.setTdsStatus("CD662");
				} else if (lStrStatus.contains("E")) {
					ehfClaimTdsPay.setTransStatus((String) lparamMap
							.get("TransRejStatus"));
					//ehfClaimTdsPay.setTdsStatus("CD661");
				}
				ehfClaimTdsPay = genericDao.save(ehfClaimTdsPay);
				if (ehfClaimTdsPay != null) {
					iResult = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return iResult;

	}
	/**
	 * This Method is to update the Payment Details into  folloup payment details.
	 * 
	 * @param lparamMap
	 *            the lparam map
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public int updateFolloupPaymentDetails(HashMap lparamMap) throws Exception {
		int iResult = 0;
		List<EhfFollowUpClaimAccounts> ehfFPClaimAccounts = null;
		String lStrStatus = (String) lparamMap.get("TransStatus");
		long timeMillSec = 0;
		List<String> colValues = new ArrayList<String>();
		EhfFollowUpClaimAccounts ehfFollowUpClaimAcc = null;
		try {
			TimeStamp t = new TimeStamp();
			timeMillSec = t.getTimeStampFromDate((String) lparamMap
					.get("TRANS_DT"));
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("caseFollowUpId",
					(String) lparamMap.get("CASE_FOLLOWUP_ID"),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("timeMilSec",
					timeMillSec, GenericDAOQueryCriteria.CriteriaOperator.LT));
			if (lStrStatus.equals("S01")) {
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			} else if (lStrStatus.equals("S00")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			} else if (lStrStatus.contains("E")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			}

			ehfFPClaimAccounts = genericDao.findAllByCriteria(
					EhfFollowUpClaimAccounts.class, criteriaList);
			if (ehfFPClaimAccounts.size() > 0) {
				ehfFollowUpClaimAcc = ehfFPClaimAccounts.get(0);

				ehfFollowUpClaimAcc.setTransactionId((String) lparamMap
						.get("TRANS_ID"));
				if ((String) lparamMap.get("TRANS_DT") != null)
					ehfFollowUpClaimAcc.setTransactionDt(sdf
							.parse((String) lparamMap.get("TRANS_DT")));
				ehfFollowUpClaimAcc.setLstUpdUsr((String) lparamMap
						.get("UPD_USR"));
				ehfFollowUpClaimAcc.setLstUpdDt(new Timestamp(new Date()
						.getTime()));
				ehfFollowUpClaimAcc.setRemarks((String) lparamMap
						.get("REMARKS"));
				ehfFollowUpClaimAcc.setTimeMilSec(timeMillSec);
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					ehfFollowUpClaimAcc.setSbhPaidDate(sdf
							.parse((String) lparamMap.get("SBH_PAID_DATE")));

				if (lStrStatus.equals("S01")) {
					ehfFollowUpClaimAcc.setTransStatus((String) lparamMap
							.get("TransAckStatus"));
				} else if (lStrStatus.equals("S00")) {
					ehfFollowUpClaimAcc.setTransStatus((String) lparamMap
							.get("TransPaidStatus"));
				} else if (lStrStatus.contains("E")) {
					ehfFollowUpClaimAcc.setTransStatus((String) lparamMap
							.get("TransRejStatus"));
				}
				ehfFollowUpClaimAcc = genericDao.save(ehfFollowUpClaimAcc);

				if (ehfFollowUpClaimAcc != null) {
					EhfCaseFollowupClaim ehfCaseFPClaim = genericDao.findById(
							EhfCaseFollowupClaim.class, String.class,
							(String) lparamMap.get("CASE_FOLLOWUP_ID"));
					ehfCaseFPClaim.setPaymentStatus((String) lparamMap
							.get("STAT_ID"));
					ehfCaseFPClaim.setLstUpdDt(new Timestamp(new Date()
							.getTime()));
					ehfCaseFPClaim.setLstUpdUsr((String) lparamMap
							.get("UPD_USR"));
					ehfCaseFPClaim = genericDao.save(ehfCaseFPClaim);
					if (ehfCaseFPClaim != null) {
						EhfCaseFollowupClaim ehfCaseFollowUpClaim = genericDao
								.findById(EhfCaseFollowupClaim.class,
										String.class,
										(String) lparamMap.get("CASE_FOLLOWUP_ID"));
						ehfCaseFollowUpClaim.setFollowUpStatus((String) lparamMap
								.get("STAT_ID"));
						ehfCaseFollowUpClaim.setLstUpdDt(new Timestamp(new Date()
								.getTime()));
						ehfCaseFollowUpClaim.setLstUpdUsr((String) lparamMap
								.get("UPD_USR"));
						ehfCaseFollowUpClaim = genericDao
								.save(ehfCaseFollowUpClaim);
					}
					iResult = 1;
				}
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return iResult;

	}

	/**
	 * This method is to insert TDS details in AUDIT.
	 * 
	 * @param lparamMap
	 *            the new tDS audit
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public void setTDSAudit(HashMap lparamMap) throws Exception {
		logger.info("Start:setTDSAudit method in ClaimsPaymentDAOImpl");
		List<ClaimsFlowVO> tdsAuditDtls = new ArrayList<ClaimsFlowVO>();
		Long lintActOrder = 0L;
		try {
			String lStrCaseNo = (String) lparamMap.get("CASE_ID");
			String lStrActId = (String) lparamMap.get("STAT_ID");
			StringBuffer lQueryBuffer = new StringBuffer();
			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfTdsAudit au where au.id.tdsPaymentId=? ");

			String[] arg = new String[1];
			arg[0] = lStrCaseNo;

			tdsAuditDtls = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					lQueryBuffer.toString(), arg);

			if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
					&& tdsAuditDtls.get(0).getCOUNT() != null) {
				if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
					lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
			}

			EhfTdsAudit ehfTdsAudit = new EhfTdsAudit();
			EhfTdsAuditId ehfTdsAuditId = new EhfTdsAuditId();

			ehfTdsAuditId.setTdsPaymentId((String) lparamMap.get("CASE_ID"));
			ehfTdsAuditId.setActOrder(lintActOrder);

			ehfTdsAudit.setId(ehfTdsAuditId);
			ehfTdsAudit.setActId((String) lparamMap.get("STAT_ID"));
			ehfTdsAudit.setActBy((String) lparamMap.get("CRTUSER"));
			ehfTdsAudit.setRemarks((String) lparamMap.get("REMARKS"));
			ehfTdsAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
			ehfTdsAudit.setCrtDt(new Timestamp(new Date().getTime()));
			ehfTdsAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfTdsAudit.setLangId(ClaimsConstants.LANG_ID);
			ehfTdsAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				ehfTdsAudit.setSbhPaidDate(sdf.parse((String) lparamMap
						.get("SBH_PAID_DATE")));
			ehfTdsAudit.setRejCode((String) lparamMap.get("REJ_CODE"));
			ehfTdsAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
			if((String) lparamMap.get("FileName")!=null)
				ehfTdsAudit.setFileName((String) lparamMap.get("SRC_FILE"));
			ehfTdsAudit = genericDao.save(ehfTdsAudit);

		} catch (Exception e) {
			logger.error("Error:setTDSAudit method in ClaimsPaymentDAOImpl"+e);
			e.printStackTrace();
		}
		logger.info("End:setTDSAudit method in ClaimsPaymentDAOImpl");
	}

	/**
	 * This method is to insert Followup Case  details in
	 * AUDIT.
	 * 
	 * @param lparamMap
	 *            the new folloup audit details
	 * @throws Exception
	 *             the exception
	 */
	@Transactional
	public void setFolloupAuditDetails(HashMap lparamMap) throws Exception {
		logger.info("Start:setFolloupAuditDetails method in ClaimsPaymentDAOImpl");
		try {
			String lStrCaseNo = (String) lparamMap.get("CASE_FOLLOWUP_ID");
			String lStrActId = (String) lparamMap.get("STAT_ID");
			StringBuffer lQueryBuffer = new StringBuffer();
			String args[] = new String[1];
			Long lActOrder = 0L;

			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfFollowUpAudit au where au.id.caseFollowupId=? ");
			args[0] = lStrCaseNo;
			List<FollowUpVO> actOrderList = genericDao.executeHQLQueryList(
					FollowUpVO.class, lQueryBuffer.toString(), args);
			if (actOrderList != null && !actOrderList.isEmpty()
					&& actOrderList.get(0).getCOUNT() != null) {
				if (actOrderList.get(0).getCOUNT().longValue() >= 0)
					lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
			}

			EhfFollowUpAudit folowUpAudit = new EhfFollowUpAudit();
			EhfFollowUpAuditId folowUpAuditPK = new EhfFollowUpAuditId(
					lActOrder, lStrCaseNo, "CLAIM");
			folowUpAudit.setId(folowUpAuditPK);
			folowUpAudit.setActId((String) lparamMap.get("STAT_ID"));
			folowUpAudit.setActBy((String) lparamMap.get("CRTUSER"));
			folowUpAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
			folowUpAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			folowUpAudit.setLangId(ClaimsConstants.LANG_ID);
			folowUpAudit.setUserRole(ClaimsConstants.CEO_GRP_ID);
			folowUpAudit.setRemarks((String) lparamMap.get("REMARKS"));
			folowUpAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				folowUpAudit.setSbhPaidDt(sdf.parse((String) lparamMap
						.get("SBH_PAID_DATE")));
			folowUpAudit.setRejCode((String) lparamMap.get("REJ_CODE"));
			folowUpAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
			if((String) lparamMap.get("FileName")!=null)
				folowUpAudit.setFileName((String) lparamMap.get("SRC_FILE"));
			genericDao.save(folowUpAudit);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.info("End:setFolloupAuditDetails method in ClaimsPaymentDAOImpl");
	}

	/**
	 * This method is to insert RF in
	 * AUDIT_TRAIL.
	 * 
	 * @param lparamMap
	 *            the new rev fund audit details
	 * @throws Exception
	 *             the exception
	 */
	// //020
	@SuppressWarnings("rawtypes")
	@Transactional
	public void setRevFundAuditDetails(HashMap lparamMap) throws Exception {
		logger.info("Start:setRevFundAuditDetails method in ClaimsPaymentDAOImpl");

		try {
			String lStrCaseNo = (String) lparamMap.get("CASE_FOLLOWUP_ID");
			String lStrActId = (String) lparamMap.get("STAT_ID");
			StringBuffer lQueryBuffer = new StringBuffer();
			String args[] = new String[1];

			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfRevFundAudit au where au.id.rfPaymentId=? ");

			String[] arg = new String[1];
			arg[0] = lStrCaseNo;

			List<ClaimsFlowVO> tdsAuditDtls = genericDao.executeHQLQueryList(
					ClaimsFlowVO.class, lQueryBuffer.toString(), arg);
			
			Long lintActOrder = 0L;
			if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
					&& tdsAuditDtls.get(0).getCOUNT() != null) {
				if (tdsAuditDtls.get(0).getCOUNT().longValue() >= 0)
					lintActOrder = tdsAuditDtls.get(0).getCOUNT().longValue() + 1;
			}

			EhfRevFundAudit ehfRfAudit = new EhfRevFundAudit();
			EhfRevFundAuditId ehfRfAuditId = new EhfRevFundAuditId();
			ehfRfAuditId.setRfPaymentId(lStrCaseNo);
			ehfRfAuditId.setActOrder(lintActOrder);
			ehfRfAudit.setId(ehfRfAuditId);
			ehfRfAudit.setActId((String) lparamMap.get("STAT_ID"));
			ehfRfAudit.setActBy((String) lparamMap.get("CRTUSER"));
			ehfRfAudit.setRemarks((String) lparamMap.get("REMARKS"));
			ehfRfAudit.setCaseType(ClaimsConstants.TRUST_DEDUCTOR);
			ehfRfAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			ehfRfAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
			ehfRfAudit.setLangId(ClaimsConstants.LANG_ID);
			ehfRfAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				ehfRfAudit.setSbhPaidDate(sdf.parse((String) lparamMap
						.get("SBH_PAID_DATE")));
			ehfRfAudit.setRejCode((String) lparamMap.get("REJ_CODE"));
			ehfRfAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
			if((String) lparamMap.get("FileName")!=null)
				ehfRfAudit.setFileName((String) lparamMap.get("SRC_FILE"));
			ehfRfAudit = genericDao.save(ehfRfAudit);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		//logger.info("Start:setRevFundAuditDetails method in ClaimsPaymentDAOImpl");
	}

	/**
	 * Insert record into Accounts table
	 * 
	 * @param transactionVO
	 *            the transaction vo
	 * @return the transaction vo
	 * @throws ParseException
	 *             the parse exception
	 */
	@Transactional
	private TransactionVO insertRecord(TransactionVO transactionVO)
			throws ParseException {
		EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();

		ehfAcctsTransactionDtlsEntry
				.setVoucherId(getNextTransSeqVal(transactionVO.getFlag()));
		try{
		if(medicalEntry)
		{
		ehfAcctsTransactionDtlsEntry.setTransId(transactionVO.getTdsRfId()+"/DRUG");
		}
		else
		{
			ehfAcctsTransactionDtlsEntry.setTransId(transactionVO.getTdsRfId());
		}
		ehfAcctsTransactionDtlsEntry.setDebtAccount(transactionVO
				.getDebtAccount());
		ehfAcctsTransactionDtlsEntry.setCreditAccount(transactionVO
				.getCreditAccount());
		ehfAcctsTransactionDtlsEntry.setAmount(Float.parseFloat(transactionVO
				.getAmount()));
		ehfAcctsTransactionDtlsEntry.setNarration(transactionVO.getNarration());
		ehfAcctsTransactionDtlsEntry.setTransDate(new Date());
		ehfAcctsTransactionDtlsEntry.setPaymentType(transactionVO
				.getTransactionType());
		ehfAcctsTransactionDtlsEntry.setVoucherType(transactionVO
				.getVoucherType());	
			if(transactionVO.getPatientScheme()!=null && transactionVO.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS"))
			&& transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
		ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.JHSTGSchemeID"));	
	else if(transactionVO.getPatientScheme()!=null && transactionVO.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.EHS"))
			&& transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
		ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.EHFAPSchemeID"));
	else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
		ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.EHFTGSchemeID"));	
	else
			
			ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.EHF"));		
		ehfAcctsTransactionDtlsEntry.setSection(transactionVO.getSection());
		ehfAcctsTransactionDtlsEntry.setCaseId(transactionVO.getCaseId());
		ehfAcctsTransactionDtlsEntry.setCrtDt(new Date());
		ehfAcctsTransactionDtlsEntry.setCrtUsr(transactionVO.getLstUpdUsr());
		ehfAcctsTransactionDtlsEntry.setUniqueTxn(transactionVO.getUniqueTxn());
		ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.Y);

		ehfAcctsTransactionDtlsEntry = genericDao
				.save(ehfAcctsTransactionDtlsEntry);
}
catch(Exception e)
{
	e.printStackTrace();
}
		if (ehfAcctsTransactionDtlsEntry != null) {
			transactionVO.setResult(config.getString("ACC.TransSuccess")
					+ ehfAcctsTransactionDtlsEntry.getVoucherId());
		} else {
			transactionVO.setResult(config.getString("ACC.TransFailure"));
		}
		return transactionVO;
	}

	/**
	 * Generating VoucherId 
	 * 
	 * @param seqIdentifier
	 *            the seq identifier
	 * @return the next trans seq val
	 */
	private String getNextTransSeqVal(String seqIdentifier) {
		StringBuffer query = new StringBuffer();
		String transType = seqIdentifier;
		String seq = null;
		Calendar cal = Calendar.getInstance();
		List<LabelBean> seqList = new ArrayList<LabelBean>();
		if (transType.equals(config.getString("ACC.P"))) {
			query.append("select LPAD(ACC_PAYMENT_SEQ.NEXTVAL,8,'0') ID from dual ");
		} else if (transType.equals(config.getString("ACC.R"))) {
			query.append("select  LPAD(ACC_RECEIPT_SEQ.NEXTVAL,8,'0') ID from dual ");
		} else if (transType.equals(config.getString("ACC.JV"))) {
			query.append("select LPAD(ACC_JOURNAL_SEQ.NEXTVAL,8,'0') ID from dual ");
		} else if (transType.equals(config.getString("ACC.CV"))) {
			query.append("select LPAD(ACC_CONTRA_SEQ.NEXTVAL,8,'0') ID from dual ");
		}
		seqList = genericDao.executeSQLQueryList(LabelBean.class,
				query.toString());

		if (seqList != null && seqList.size() > 0)
			seq = seqList.get(0).getID();
		seq = seqIdentifier + seq + '/' + cal.get(Calendar.YEAR);
		return seq;
	}

	/**
	 * Gets the next Sequence
	 * 
	 * @return the next uniq seq val
	 */
	private String getNextUniqSeqVal() {
		StringBuffer query = new StringBuffer();
		String uniqseq = null;
		List<LabelBean> seqList = new ArrayList<LabelBean>();
		query.append("select ACC_UNIQUE_TXN_SEQ.NEXTVAL||'' ID from dual ");
		seqList = genericDao.executeSQLQueryList(LabelBean.class,
				query.toString());

		if (seqList != null && seqList.size() > 0)
			uniqseq = seqList.get(0).getID();

		return uniqseq;
	}

	/**
	 * Insert record into Accounts table for normal claim
	 *  
	 * @return TransactionVO
	 * @throws ParseException
	 *             the parse exception
	 */
	public TransactionVO submitClaimPaymentsInAccounts(
			TransactionVO transactionVO) throws ParseException {
		String patientScheme = transactionVO.getPatientScheme();
		if(transactionVO.getHospitalId()!=null && (transactionVO.getHospitalId().equalsIgnoreCase("EHS69") ||  transactionVO.getHospitalId().equalsIgnoreCase("EHS34") )){
			transactionVO.setHospitalType("C");
		}
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);

		if (transactionVO.getTransactionType()!=null&& config.getString("ACC.FOLLCLAIMS").equals(
				transactionVO.getTransactionType()))
			transactionVO.setDebtAccount(config
					.getString("ACC.FOLLCLAIMSACCOUNT"));
		else
			transactionVO.setDebtAccount(config
					.getString("ACC.REGCLAIMSACCOUNT"));

		transactionVO.setCreditAccount(config.getString("ACC.CLAIMHOSP")
				.concat(transactionVO.getHospitalId()));
		transactionVO.setVoucherType(config.getString("ACC.Journal"));
		transactionVO.setAmount(transactionVO.getGrossAmount());
		transactionVO.setFlag(config.getString("ACC.JV"));

		transactionVO = insertRecord(transactionVO);


		transactionVO.setDebtAccount(config.getString("ACC.CLAIMHOSP").concat(
				transactionVO.getHospitalId()));
		if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
		{
			transactionVO.setCreditAccount(config.getString("ACC.JHSMAINAP-2331"));
		}
	else
		{
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
		}	
		/*if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));	*/	
		transactionVO.setVoucherType(config.getString("ACC.Payment"));
		transactionVO.setSection("");
		transactionVO.setAmount(transactionVO.getNetAmount());
		transactionVO.setFlag(config.getString("ACC.P"));

		transactionVO = insertRecord(transactionVO);
		
		
		transactionVO.setDebtAccount(config.getString("ACC.CLAIMHOSP").concat(
				transactionVO.getHospitalId()));
		if (config.getString("ACC.GOVT")
				.equals(transactionVO.getHospitalType()))
			transactionVO.setCreditAccount(config.getString("ACC.GOVTACCOUNT"));
		else
			transactionVO.setCreditAccount(config.getString("ACC.CORPACCOUNT"));
		transactionVO.setVoucherType(config.getString("ACC.Journal"));
		transactionVO.setAmount(transactionVO.getTdsOrRfAmount());
		transactionVO.setFlag(config.getString("ACC.JV"));
	
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType()))
		{
		transactionVO.setSection("194J");
		transactionVO.setTdsRfId(transactionVO.getTdsRfId()+"/TDS");
		
		}
		transactionVO = insertRecord(transactionVO);
		

		return transactionVO;
	}
	
	public TransactionVO submitChronicClaimPaymentsInAccounts(
			TransactionVO transactionVO) throws ParseException {
		
		if(transactionVO.getHospitalId()!=null && (transactionVO.getHospitalId().equalsIgnoreCase("EHS69") ||  transactionVO.getHospitalId().equalsIgnoreCase("EHS34") )){
			transactionVO.setHospitalType("C");
		}
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);
		
		transactionVO.setTransactionType((config
				.getString("ACC.CHRONICPAYMENT")));

		/* JOURNAL ENTRY 1 */
		medicalEntry=false;
		transactionVO.setDebtAccount(config.getString("ACC.REGCLAIMSACCOUNT"));
		transactionVO.setCreditAccount(config.getString("ACC.CLAIMHOSP").concat(transactionVO.getHospitalId()));
		transactionVO.setVoucherType(config.getString("ACC.Journal"));
		transactionVO.setAmount(transactionVO.getGrossAmount());
		transactionVO.setFlag(config.getString("ACC.JV"));
		transactionVO.setNarration("Being Claim approved for payment");
		transactionVO.setLstUpdUsr(transactionVO.getCrtUser());
		transactionVO = insertRecord(transactionVO);
		
		/* JOURNAL ENTRY 2 */
		medicalEntry=true;
		transactionVO.setDebtAccount(config.getString("ACC.REGCLAIMSACCOUNT"));
		if (config.getString("ACC.GOVT").equals(transactionVO.getHospitalType()))
			transactionVO.setCreditAccount(config.getString("ACC.CHRONICMEDICALHOSP").concat(transactionVO.getHospitalId()));
		transactionVO.setVoucherType(config.getString("ACC.Journal"));
		transactionVO.setAmount(transactionVO.getDrugAmount());
		transactionVO.setFlag(config.getString("ACC.JV"));
		transactionVO.setNarration("Being Claim approved for payment");
		transactionVO = insertRecord(transactionVO);
		
		/* PAYMENT ENTRY TOWARDS HOSPITAL */
		medicalEntry=false;
		transactionVO.setDebtAccount(config.getString("ACC.CLAIMHOSP").concat(transactionVO.getHospitalId()));		
		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));		
		transactionVO.setVoucherType(config.getString("ACC.Payment"));
		transactionVO.setSection("");
		transactionVO.setAmount(transactionVO.getGrossAmount());
		transactionVO.setNarration(" Being Claim amount paid to----------network hospital");
		transactionVO.setFlag(config.getString("ACC.P"));

		transactionVO = insertRecord(transactionVO);
		
		
		/* CONTRA ENTRY FOR MEDICINES */
		
		medicalEntry=true;
		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
		{
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
			transactionVO.setDebtAccount(config.getString("ACC.CHRONICMEDICAL.AP"));
		}
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
		{
			transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
			transactionVO.setDebtAccount(config.getString("ACC.CHRONICMEDICAL.TG"));
		}
		transactionVO.setVoucherType(config.getString("ACC.Contra"));
		transactionVO.setSection("");
		transactionVO.setAmount(transactionVO.getDrugAmount());
		transactionVO.setFlag(config.getString("ACC.CV"));
		transactionVO.setNarration("Being passed  JV t/d deduction of Medicines cost----------network hospital");
		transactionVO = insertRecord(transactionVO);

		return transactionVO;
	}
	
	@Transactional
	private void updateAccountsTransactionNew(String lStrCaseid,String lStrClaimAmt, String lStrTransDt) {
		
		try {

			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("transId", lStrCaseid,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("active_yn",
					ClaimsConstants.R,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfAcctsTransactionDtls> ehfAccountTrans = genericDao
					.findAllByCriteria(EhfAcctsTransactionDtls.class,
							criteriaList);

			/*if (ehfAccountTrans != null && ehfAccountTrans.size() > 0) {
				EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = ehfAccountTrans
						.get(0);
				if (lStrTransDt != null)
					ehfAcctsTransactionDtlsEntry.setTransDate(df
							.parse(lStrTransDt));
				ehfAcctsTransactionDtlsEntry.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfAcctsTransactionDtlsEntry.setLstUpdUsr(config
						.getString("ACC.TCS"));
				ehfAcctsTransactionDtlsEntry = genericDao
						.save(ehfAcctsTransactionDtlsEntry);
			} else {
*/
				String lStrSchemeId = "";
				String patScheme="";
				String caseId = lStrCaseid;
				if (lStrCaseid.contains(ClaimsConstants.SLASH))
					caseId = lStrCaseid.substring(0,
							lStrCaseid.indexOf(ClaimsConstants.SLASH));

				EhfCase ehfCase = genericDao.findById(EhfCase.class,
						String.class, caseId);
				if (ehfCase != null)
				{
					lStrSchemeId = ehfCase.getSchemeId();
					patScheme = ehfCase.getPatientScheme();
				}
				EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();

				ehfAcctsTransactionDtlsEntry
						.setVoucherId(getNextTransSeqVal(config
								.getString("ACC.R")));
				ehfAcctsTransactionDtlsEntry.setTransId(lStrCaseid);
				if (lStrSchemeId != null
						&& lStrSchemeId.equalsIgnoreCase("CD201"))
					ehfAcctsTransactionDtlsEntry.setDebtAccount(config
							.getString("ACC.MAINAP-2305"));
				else if (lStrSchemeId != null
						&& lStrSchemeId.equalsIgnoreCase("CD202"))
				{
					if(patScheme!=null && patScheme.equalsIgnoreCase("CD501"))
					ehfAcctsTransactionDtlsEntry.setDebtAccount(config
							.getString("ACC.MAINTG-2316"));
					else
						ehfAcctsTransactionDtlsEntry.setDebtAccount(config
								.getString("ACC.JHSMAINAP-2331"));
				}
					else
					ehfAcctsTransactionDtlsEntry.setDebtAccount(config
							.getString("ACC.MAINTG-2316"));
				ehfAcctsTransactionDtlsEntry.setCreditAccount(config
						.getString("ACC.REJGL"));
				ehfAcctsTransactionDtlsEntry.setAmount(Float
						.parseFloat(lStrClaimAmt));
				ehfAcctsTransactionDtlsEntry.setNarration("Rejection");
				ehfAcctsTransactionDtlsEntry.setPaymentType("");
				ehfAcctsTransactionDtlsEntry.setVoucherType(config
						.getString("ACC.Payment"));
				if (lStrSchemeId != null
						&& lStrSchemeId.equalsIgnoreCase("CD201"))
					ehfAcctsTransactionDtlsEntry.setScheme(config
							.getString("ACC.EHFAPSchemeID"));
				else if (lStrSchemeId != null
						&& lStrSchemeId.equalsIgnoreCase("CD202"))
				{
					if(patScheme!=null && patScheme.equalsIgnoreCase("CD501"))
					ehfAcctsTransactionDtlsEntry.setScheme(config
							.getString("ACC.EHFTGSchemeID"));
					else
						ehfAcctsTransactionDtlsEntry.setScheme(config
								.getString("ACC.JHSTGSchemeID"));
				}
					else
					ehfAcctsTransactionDtlsEntry.setScheme(config
							.getString("ACC.EHF"));
				ehfAcctsTransactionDtlsEntry.setSection("");
				ehfAcctsTransactionDtlsEntry.setCaseId(lStrCaseid);
				ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.R);
				ehfAcctsTransactionDtlsEntry.setCrtDt(new Date());
				try {
					if (lStrTransDt != null)
						ehfAcctsTransactionDtlsEntry.setTransDate(sdf
								.parse(lStrTransDt));
					else
						ehfAcctsTransactionDtlsEntry.setTransDate(new Date());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ehfAcctsTransactionDtlsEntry.setCrtUsr(config
						.getString("ACC.TCS"));
				String uniqueTxn = getNextUniqSeqVal();
				ehfAcctsTransactionDtlsEntry.setUniqueTxn(uniqueTxn);
				ehfAcctsTransactionDtlsEntry = genericDao
						.save(ehfAcctsTransactionDtlsEntry);
			/*}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public TransactionVO submitClaimPaymentsInAccountsForRej(TransactionVO transactionVO) throws ParseException{
		String patientScheme=transactionVO.getPatientScheme();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("transId", transactionVO.getTdsRfId(),
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("active_yn",
				ClaimsConstants.R,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfAcctsTransactionDtls> ehfAccountTrans = genericDao
				.findAllByCriteria(EhfAcctsTransactionDtls.class,
						criteriaList);

		/*if (ehfAccountTrans != null && ehfAccountTrans.size() > 0) {
			EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = ehfAccountTrans.get(0);
			ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.P);
			ehfAcctsTransactionDtlsEntry.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfAcctsTransactionDtlsEntry.setLstUpdUsr(transactionVO.getLstUpdUsr());
			ehfAcctsTransactionDtlsEntry = genericDao
					.save(ehfAcctsTransactionDtlsEntry);
		}*/
		
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);
		if(transactionVO.getHospitalId()!=null && (transactionVO.getHospitalId().equalsIgnoreCase("EHS69") ||  transactionVO.getHospitalId().equalsIgnoreCase("EHS34") )){
			transactionVO.setHospitalType("C");
		}
		transactionVO.setDebtAccount(config.getString("ACC.REJGL"));
		if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
		{
			transactionVO.setCreditAccount(config.getString("ACC.JHSMAINAP-2331"));
		}
	else
		{
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
			else 
				transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
		}	
/*		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
		else 
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));*/
		transactionVO.setVoucherType("Rejection");
		transactionVO.setAmount(transactionVO.getNetAmount());
		transactionVO.setFlag(config.getString("ACC.P"));
		transactionVO.setLstUpdUsr(config.getString("bankerId"));
		transactionVO = insertRecord(transactionVO);
		return transactionVO;
	}
	/**
	 * Insert record into Accounts table for TDS and RF
	 * 
	 * @return TransactionVO
	 * @throws ParseException
	 *             the parse exception
	 */
	public TransactionVO submitTdsOrRfPaymentsInAccounts(
			TransactionVO transactionVO) throws ParseException {
		if(transactionVO.getHospitalId()!=null && (transactionVO.getHospitalId().equalsIgnoreCase("EHS69") ||  transactionVO.getHospitalId().equalsIgnoreCase("EHS34"))){
			transactionVO.setHospitalType("C");
		}
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);
		String patientScheme = null;
		patientScheme = transactionVO.getPatientScheme();
		
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
			
			
			
			//Only for Journalist AP Payments
			if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
				{
					transactionVO.setDebtAccount(config.getString("ACC.JHSTDSTG-2332"));
				}
			else
				{
					if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
						transactionVO.setDebtAccount(config.getString("ACC.TDSAP-2307"));
					else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
						transactionVO.setDebtAccount(config.getString("ACC.TDSTG-2322"));
					else
						transactionVO.setDebtAccount(config.getString("ACC.TDSAP-2322"));	
				}
			
			
/*			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setDebtAccount(config.getString("ACC.TDSAP-2307"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setDebtAccount(config.getString("ACC.TDSTG-2322"));
			else
				transactionVO.setDebtAccount(config.getString("ACC.TDSAP-2307"));*/
			transactionVO.setSection("194J");
								
		}
		else{
			if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
			{
				transactionVO.setDebtAccount(config.getString("ACC.JHSRFTG-2333"));
			}
			else
			{
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setDebtAccount(config.getString("ACC.RFAP-2306"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setDebtAccount(config.getString("ACC.RFTG-2321"));
			else
				transactionVO.setDebtAccount(config.getString("ACC.RFAP-2321"));
			}
		}
		
		if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
		{
			transactionVO.setCreditAccount(config.getString("ACC.JHSMAINAP-2331"));
		}
		else
		{
		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
		else
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2316"));
		}
		transactionVO.setVoucherType(config.getString("ACC.Contra"));
		transactionVO.setAmount(transactionVO.getTdsOrRfAmount());
		transactionVO.setFlag(config.getString("ACC.CV"));

		transactionVO = insertRecord(transactionVO);
		
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
			
		if (config.getString("ACC.GOVT")
				.equals(transactionVO.getHospitalType()))
			transactionVO.setDebtAccount(config.getString("ACC.GOVTACCOUNT"));
		else
			transactionVO.setDebtAccount(config.getString("ACC.CORPACCOUNT"));
		
		
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
			
			if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
			{
				transactionVO.setCreditAccount(config.getString("ACC.JHSTDSTG-2332"));
			}
		else
			{
			
			
		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.TDSAP-2307"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.TDSTG-2322"));
		else 
			transactionVO.setCreditAccount(config.getString("ACC.TDSAP-2307"));
		}
		}
		else{
			
			if(patientScheme!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientScheme))
			{
				transactionVO.setCreditAccount(config.getString("ACC.JHSRFTG-2333"));
			}
			else
			{
			
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setCreditAccount(config.getString("ACC.RFAP-2306"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setCreditAccount(config.getString("ACC.RFTG-2321"));
			else
				transactionVO.setCreditAccount(config.getString("ACC.RFAP-2306"));
		}
		}
		transactionVO.setVoucherType(config.getString("ACC.Payment"));
		transactionVO.setAmount(transactionVO.getTdsOrRfAmount());
		transactionVO.setFlag(config.getString("ACC.P"));

		transactionVO = insertRecord(transactionVO);
 }
		return transactionVO;
	}
	/**
	 * Insert record into Accounts table for Surplus TDS and RF
	 * 
	 * @return TransactionVO
	 * @throws ParseException
	 *             the parse exception
	 */
	public TransactionVO submitTdsOrRfSurplusPaymentsInAccounts(
			TransactionVO transactionVO) throws ParseException {
		if(transactionVO.getHospitalId()!=null && (transactionVO.getHospitalId().equalsIgnoreCase("EHS69") ||  transactionVO.getHospitalId().equalsIgnoreCase("EHS34") )){
			transactionVO.setHospitalType("C");
		}
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);
		
		//Contra Entry
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setDebtAccount(config.getString("ACC.TDSAP-2307"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setDebtAccount(config.getString("ACC.TDSTG-2322"));
			else
				transactionVO.setDebtAccount(config.getString("ACC.TDSAP-2307"));
			transactionVO.setSection("194J");
		}
		else{
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setDebtAccount(config.getString("ACC.RFAP-2306"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setDebtAccount(config.getString("ACC.RFTG-2321"));
			else
				transactionVO.setDebtAccount(config.getString("ACC.RFAP-2306"));
		}
		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
		else
			transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2305"));
		transactionVO.setVoucherType(config.getString("ACC.Contra"));
		
		EhfSurplusTdsClaim ehfSurplusTdsClaim = genericDao.findById(EhfSurplusTdsClaim.class,String.class, transactionVO.getCaseId());
		
		if(ehfSurplusTdsClaim!=null)
		{
			if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("Y").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
			{
				transactionVO.setAmount(ehfSurplusTdsClaim.getTdsPctAmt().toString());					
			}
			else if(ehfSurplusTdsClaim.getTDSRFFflag()!=null && ("N").equalsIgnoreCase(ehfSurplusTdsClaim.getTDSRFFflag()))
			{
				transactionVO.setAmount(ehfSurplusTdsClaim.getTrustPctAmt().toString());				
			}
			else
			{
				transactionVO.setAmount("0");				
				 System.out.println("TDS Flag is not set well for case id " +transactionVO.getCaseId());
				 
				 //logger.error("TDS Flag is not set well for case id " +transactionVO.getCaseId());
				 
				 EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, transactionVO.getCaseId());
					if(ehfCase!=null)
					{
						ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
						ehfCase=genericDao.save(ehfCase);
						
					}
						
				 
			}
		}
		else
		{
			transactionVO.setAmount("0");			
			//System.out.println("No record present in EhfSurplusTdsClaim with case id "+transactionVO.getCaseId());
			//logger.error("No record present in EhfSurplusTdsClaim with case id "+transactionVO.getCaseId());
			 
			 EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, transactionVO.getCaseId());
				if(ehfCase!=null)
				{
					ehfCase.setCaseStatus(ClaimsConstants.CLAIM_SURPLUS_TDS_DED_SKIPPED);
					ehfCase=genericDao.save(ehfCase);
					
				}
		}
		transactionVO.setTdsRfId(transactionVO.getCaseId()+"/SD/TDS");
		//transactionVO.setAmount(transactionVO.getTdsOrRfAmount());
		transactionVO.setFlag(config.getString("ACC.CV"));

		transactionVO = insertRecord(transactionVO);
		//Payment Entry
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
			
		if (config.getString("ACC.GOVT")
				.equals(transactionVO.getHospitalType()))
			transactionVO.setDebtAccount(config.getString("ACC.GOVTACCOUNT"));
		else
			transactionVO.setDebtAccount(config.getString("ACC.CORPACCOUNT"));
		
		
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
		if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
			transactionVO.setCreditAccount(config.getString("ACC.TDSAP-2307"));
		else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
			transactionVO.setCreditAccount(config.getString("ACC.TDSTG-2322"));
		else 
			transactionVO.setCreditAccount(config.getString("ACC.TDSAP-2307"));
		}
		else{
			if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
				transactionVO.setCreditAccount(config.getString("ACC.RFAP-2306"));
			else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
				transactionVO.setCreditAccount(config.getString("ACC.RFTG-2321"));
			else
				transactionVO.setCreditAccount(config.getString("ACC.RFAP-2306"));
		}
		transactionVO.setVoucherType(config.getString("ACC.Payment"));
		transactionVO.setAmount(transactionVO.getAmount());
		transactionVO.setFlag(config.getString("ACC.P"));

		transactionVO = insertRecord(transactionVO);
 }
		//Journal Entry
		if (transactionVO.getTdsOrRfType() != null
				&& config.getString("ACC.TDS").equals(
						transactionVO.getTdsOrRfType())){
			
		if (config.getString("ACC.GOVT")
				.equals(transactionVO.getHospitalType()))
			transactionVO.setCreditAccount(config.getString("ACC.GOVTACCOUNT"));
		else
			transactionVO.setCreditAccount(config.getString("ACC.CORPACCOUNT"));
		
		transactionVO.setDebtAccount(config.getString("ACC.CLAIMHOSP")+transactionVO.getHospitalId());
	
		transactionVO.setVoucherType(config.getString("ACC.Journal"));
		transactionVO.setAmount(transactionVO.getAmount());
		transactionVO.setFlag(config.getString("ACC.JV"));

		transactionVO = insertRecord(transactionVO);
 }
		
		return transactionVO;
	}
	
	@Transactional
	private void updateAccTdsRfNew(String lStrCaseid,String lStrClaimAmt, String lStrTransDt) {		
		try {
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("transId", lStrCaseid,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("active_yn",
					ClaimsConstants.R,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfAcctsTransactionDtls> ehfAccountTrans = genericDao
					.findAllByCriteria(EhfAcctsTransactionDtls.class,
							criteriaList);

			if (ehfAccountTrans != null && ehfAccountTrans.size() > 0) {
				EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = ehfAccountTrans
						.get(0);
				if (lStrTransDt != null)
					ehfAcctsTransactionDtlsEntry.setTransDate(df
							.parse(lStrTransDt));
				ehfAcctsTransactionDtlsEntry.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				ehfAcctsTransactionDtlsEntry.setLstUpdUsr(config
						.getString("ACC.TCS"));
				ehfAcctsTransactionDtlsEntry = genericDao
						.save(ehfAcctsTransactionDtlsEntry);
			} 
			else{
		String lStrSchemeId="";String caseId = "";String lStrFollowUpId ="";
		caseId = lStrCaseid.substring(0,lStrCaseid.indexOf(ClaimsConstants.SLASH));
		lStrFollowUpId = lStrCaseid.substring(0,lStrCaseid.lastIndexOf(ClaimsConstants.SLASH));
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
		if(ehfCase!=null)
			lStrSchemeId=ehfCase.getSchemeId();
				
		EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();

		ehfAcctsTransactionDtlsEntry.setVoucherId(getNextTransSeqVal(config.getString("ACC.R")));
		ehfAcctsTransactionDtlsEntry.setTransId(lStrCaseid);
		if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
			ehfAcctsTransactionDtlsEntry.setDebtAccount(config.getString("ACC.MAINAP-2305"));
		else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
			ehfAcctsTransactionDtlsEntry.setDebtAccount(config.getString("ACC.MAINTG-2316"));
		else
			ehfAcctsTransactionDtlsEntry.setDebtAccount(config.getString("ACC.MAINAP-2305"));
		ehfAcctsTransactionDtlsEntry.setCreditAccount(config.getString("ACC.REJGL"));
		ehfAcctsTransactionDtlsEntry.setAmount(Float.parseFloat(lStrClaimAmt));
		ehfAcctsTransactionDtlsEntry.setNarration("Rejection");
		ehfAcctsTransactionDtlsEntry.setPaymentType("");
		ehfAcctsTransactionDtlsEntry.setVoucherType(config.getString("ACC.Payment"));
		if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD201"))
			ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.EHFAPSchemeID"));
		else if(lStrSchemeId!=null && lStrSchemeId.equalsIgnoreCase("CD202"))
			ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.EHFTGSchemeID"));	
		else
			ehfAcctsTransactionDtlsEntry.setScheme(config.getString("ACC.EHF"));	
		ehfAcctsTransactionDtlsEntry.setSection("");
		ehfAcctsTransactionDtlsEntry.setCaseId(lStrFollowUpId);
		ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.R);
		ehfAcctsTransactionDtlsEntry.setCrtDt(new Date());
		try {
			if (lStrTransDt != null)
				ehfAcctsTransactionDtlsEntry.setTransDate(sdf
						.parse(lStrTransDt));
			else
				ehfAcctsTransactionDtlsEntry.setTransDate(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ehfAcctsTransactionDtlsEntry.setCrtUsr(config.getString("ACC.TCS"));
		String uniqueTxn = getNextUniqSeqVal();
		ehfAcctsTransactionDtlsEntry.setUniqueTxn(uniqueTxn);
		ehfAcctsTransactionDtlsEntry = genericDao.save(ehfAcctsTransactionDtlsEntry);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TransactionVO submitTdsOrRfPaymentsInAccountsForRej(
			TransactionVO transactionVO) {
		String patientScheme = transactionVO.getPatientScheme();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("transId", transactionVO.getTdsRfId(),
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("active_yn",
				ClaimsConstants.R,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfAcctsTransactionDtls> ehfAccountTrans = genericDao
				.findAllByCriteria(EhfAcctsTransactionDtls.class,
						criteriaList);

		if (ehfAccountTrans != null && ehfAccountTrans.size() > 0) {
			EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = ehfAccountTrans.get(0);
			ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.P);
			ehfAcctsTransactionDtlsEntry.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			ehfAcctsTransactionDtlsEntry.setLstUpdUsr(config
					.getString("ACC.TCS"));
			ehfAcctsTransactionDtlsEntry = genericDao
					.save(ehfAcctsTransactionDtlsEntry);
		}
		
		if(transactionVO.getHospitalId()!=null && (transactionVO.getHospitalId().equalsIgnoreCase("EHS69") ||  transactionVO.getHospitalId().equalsIgnoreCase("EHS34") )){
			transactionVO.setHospitalType("C");
		}
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);
		try {
		transactionVO.setDebtAccount(config.getString("ACC.REJGL"));

		if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
			{
				transactionVO.setCreditAccount(config.getString("ACC.JHSMAINAP-2331"));
			}
		else
			{		
				if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD201"))
					transactionVO.setCreditAccount(config.getString("ACC.JHSMAINAP-2331"));
				else if(transactionVO.getScheme()!=null && transactionVO.getScheme().equalsIgnoreCase("CD202"))
					transactionVO.setCreditAccount(config.getString("ACC.MAINTG-2316"));
				else 
					transactionVO.setCreditAccount(config.getString("ACC.MAINAP-2316"));
			}	
		
		transactionVO.setVoucherType(config.getString("ACC.Payment"));
		transactionVO.setAmount(transactionVO.getTdsOrRfAmount());
		transactionVO.setFlag(config.getString("ACC.P"));
		transactionVO.setSection("194J");
		
		transactionVO = insertRecord(transactionVO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return transactionVO;
	}

	@Override
	public String SetJHSClaimStatus(HashMap lparamMap) {

		String lResult = "False";
		ArrayList lDataList = new ArrayList();
		int i = 0, lFlag = 0, iStatus = 0, fileStatusConut = 0;
		String lStrCaseid = "",lStrClaimAmt="", lStrACNo = "", lStrStatus = "", lStrTransDt = "", lStrTransId = "", lStrPaymentUid = null;
		String lStrClaimPreStatus = "",lStrClaimStatus = "", lStrRemarks = "", lStrRembsStatus = "", lStrPaidDate = null, lStrRejCode = null;
		List<Map<String,String>> rejCaseList=new ArrayList<Map<String,String>>();
		String rejectedCasesRemarks=null;
		boolean charContains = false, charContainsTDS = true;String rejectedCases=(String) lparamMap.get("RejectedCases");
		try {
			lDataList = (ArrayList) lparamMap.get("DataList");
			lStrClaimPreStatus = (String) lparamMap.get("ClamsInProgerss");
			fileStatusConut = Integer.parseInt((String) lparamMap
					.get("FileConut"));
            //Reading file content
			int DataList = lDataList.size();
			for (int j = 0; j < DataList; j = j + 9) 
			{
				lFlag = 0;
				lStrCaseid = (String) lDataList.get(j);
				lStrClaimAmt = (String) lDataList.get(j + 7);
				double clmAmt=Double.parseDouble(lStrClaimAmt);
				clmAmt=clmAmt/100;
				lStrClaimAmt=String.valueOf(clmAmt);
				
				
				lStrTransDt = (String) lDataList.get(j + 2);
			
				lStrTransId = (String) lDataList.get(j + 1);
				lStrPaymentUid = (String) lDataList.get(j + 1);
				
				lStrStatus = (String) lDataList.get(j + 3);
				lStrACNo = (String) lDataList.get(j + 5);
				lStrRemarks = (String) lDataList.get(j + 4);
				lStrPaidDate = (String) lDataList.get(j + 2); 
				
				String rejCode=(String) lDataList.get(j + 3); 
				if(rejCode!=null && rejCode.length()>0 && rejCode.contains("E"))
			    lStrRejCode = rejCode; 
			
				
				if (lStrPaidDate.trim().length() == 0) {
					lStrPaidDate = null;
				}

				lparamMap.put("TRANS_ID", lStrTransId);
				lparamMap.put("TRANS_DT", lStrTransDt);
				lparamMap.put("CASE_ID", lStrCaseid);
				lparamMap.put("SBH_PAID_DATE", lStrPaidDate);
				lparamMap.put("REJ_CODE", lStrRejCode);
				lparamMap.put("PAYMENT_UID", lStrPaymentUid);
                //For Claim Paid Status
				if (lStrStatus.equals("S00")) 
				{   //TDS
					if (lStrCaseid.endsWith("TDS")) {
						//Err-TDS
						if (lStrCaseid.endsWith("/E/TDS")) {
							lStrClaimStatus = (String) lparamMap
									.get("ErroneousClamPaid");
						} else if (lStrCaseid.endsWith("/1/TDS")
								|| lStrCaseid.endsWith("/2/TDS")
								|| lStrCaseid.endsWith("/3/TDS")
								|| lStrCaseid.endsWith("/4/TDS")) { //FollowUp_TDS
							lStrClaimStatus = (String) lparamMap
									.get("FlupClamPaid");
						} else {
							//normal_tds
							lStrClaimStatus = (String) lparamMap
									.get("ClamSettled");
						}
					} else {
						//For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap.get("ClamSettled");
						lStrRembsStatus = (String) lparamMap
								.get("RembsClamPaid"); 
					}
					lStrRemarks = (String) lparamMap.get("ClaimPaidRemarks");
					//For Claim Rejected By Bank
				} else if (lStrStatus.contains("E")) // 035
				{   //TDS
					rejectedCases = rejectedCases+lStrCaseid+" , ";
					if(rejectedCasesRemarks!=null)
						rejectedCasesRemarks=rejectedCasesRemarks+","+lStrRemarks;
					else
						rejectedCasesRemarks=lStrRemarks;
					
					if (lStrCaseid.endsWith("TDS")) {
						if (lStrCaseid.endsWith("/E/TDS")) {  //Err-TDS
							lStrClaimStatus = (String) lparamMap
									.get("ErroneousClaimRej");
						} else if (lStrCaseid.endsWith("/1/TDS")
								|| lStrCaseid.endsWith("/2/TDS")
								|| lStrCaseid.endsWith("/3/TDS")
								|| lStrCaseid.endsWith("/4/TDS")) {  //FollowUp_TDS
							lStrClaimStatus = (String) lparamMap
									.get("FlupClaimRej");
						} else {
							//normal_tds
							lStrClaimStatus = (String) lparamMap
									.get("ClaimFailed");
						}
					} else { //For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap.get("ClaimFailed");
						lStrRembsStatus = (String) lparamMap
								.get("RembsClaimRej");
					}
				} else if (lStrStatus.equals("S01"))  //For Acknowledge Status
				{    //TDS
					if (lStrCaseid.endsWith("TDS")) {
						if (lStrCaseid.endsWith("/E/TDS")) {  //Err-TDS
							lStrClaimStatus = (String) lparamMap
									.get("ErroneousClaimAck");
						} else if (lStrCaseid.endsWith("/1/TDS")
								|| lStrCaseid.endsWith("/2/TDS")
								|| lStrCaseid.endsWith("/3/TDS")
								|| lStrCaseid.endsWith("/4/TDS")) {   //FollowUp_TDS
							lStrClaimStatus = (String) lparamMap
									.get("FlupAcknowledgmentRcvd");
						} else { //normal_tds
							lStrClaimStatus = (String) lparamMap
									.get("AcknowledgmentRcvd");
						}
					} else { //For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap
								.get("AcknowledgmentRcvd");
						lStrRembsStatus = (String) lparamMap
								.get("RembsAcknowledgmentRcvd");
					}
					lStrRemarks = (String) lparamMap.get("ClaimAckRemarks");
				} else {
					lFlag = 1;

				}
				
				lparamMap.put("REMARKS", lStrRemarks);
				lparamMap.put("TransStatus", lStrStatus);
				lparamMap.put("STAT_ID", lStrClaimStatus);
				lparamMap.put("CASE_FOLLOWUP_ID", lStrCaseid);
				lparamMap.put("CASE_TYPE", "REV_FUND");
				
				lparamMap.put("RejectedCases",rejectedCases);
				
				if (lFlag != 1) {
					iStatus = 0;
					charContains = false;
					charContainsTDS = false;
					if (lStrTransDt.trim().length() > 1 && lStrTransDt != null) { 
																					
						if (lStrCaseid.endsWith(ClaimsConstants.G)) {
							charContains = true;
						}
						if (lStrCaseid.endsWith(ClaimsConstants.TDS)) {
							charContainsTDS = true;
						}
												
						if (charContains) {
							iStatus = updateRevolvingFundsDetails(lparamMap);  //Updating RF Details acc to Status
							if (iStatus > 0 && lStrStatus.equals("R"))
								updateAccTdsRfNew(lStrCaseid,lStrClaimAmt,lStrTransDt);
						}
						
						if (charContainsTDS) {
							iStatus = updateTDSPaymentDetails(lparamMap);     //Updating TDS Details acc to Status
							if (iStatus > 0 && lStrStatus.equals("R"))
								updateAccTdsRfNew(lStrCaseid,lStrClaimAmt,lStrTransDt);
						}
						if (charContains == false && charContainsTDS == false) {
							iStatus = updatePaymentDetails(lparamMap);        //Updating Claim Details acc to Status  
							if (iStatus > 0 && lStrStatus.equals("R"))
								updateAccountsTransactionNew(lStrCaseid,lStrClaimAmt,         //Reverting details in Accounts tables iF Rejected
										lStrTransDt);
						}

					}

					if (iStatus > 0                              //&& (lStrCaseid.endsWith("R") == false)
							&& charContains == false
							&& charContainsTDS == false) {
						StringBuffer lStrBuffer = new StringBuffer();

						EhfCase ehfCase = genericDao.findById(EhfCase.class,
								String.class, lStrCaseid);
						ehfCase.setLstUpdDt(new Timestamp(new Date().getTime()));
						ehfCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
						ehfCase.setCaseStatus(lStrClaimStatus);
						ehfCase = genericDao.save(ehfCase);

						if (ehfCase != null) {
							setCaseAudit(lparamMap);
							lResult = ClaimsConstants.TRUE;
						} else
							lResult = ClaimsConstants.FALSE;
					} else if (iStatus > 0 && charContains == true) {
						setRevFundAuditDetails(lparamMap);
						lResult = ClaimsConstants.TRUE;
					} else if (iStatus > 0 && charContainsTDS == true) {
						    setTDSAudit(lparamMap);
							lResult = ClaimsConstants.TRUE;
					}
					else{
						lResult = ClaimsConstants.FALSE;
					}
					
				}

			}

			if (ClaimsConstants.TRUE.equalsIgnoreCase(lResult)) {
				String fileName=(String) lparamMap.get("FileName")+".xml";
				EhfClaimUploadFile ehfClaimUploadFile = null;
				ehfClaimUploadFile = genericDao.findById(
						EhfClaimUploadFile.class, String.class,fileName);
				if (ehfClaimUploadFile.getFileStatus() != null
						&& ehfClaimUploadFile.getFileStatus().equalsIgnoreCase(
								(String) lparamMap.get("SentStatus"))) {
					ehfClaimUploadFile.setLstUpdDate(new Timestamp(new Date()
							.getTime()));
					ehfClaimUploadFile.setFileStatus((String) lparamMap
							.get("ClosedStatus"));
				}
				ehfClaimUploadFile = genericDao.save(ehfClaimUploadFile);
				
			}
		 
			//IF Email required
			if (config.getBoolean("EmailRequired")) {
				if (lparamMap.get("RejectedCases") != null && lparamMap.get("RejectedCases") !="" ) {
					
					
					if(rejectedCases!=null && !"".equalsIgnoreCase(rejectedCases))
					{
						StringTokenizer rc=new StringTokenizer(rejectedCases," , ");
						String hospName=null,hospId=null;
						String[] rejRemarks=null;
						int ct=0;
						if(rejectedCasesRemarks!=null)
							rejRemarks=rejectedCasesRemarks.split(",");
						while(rc.hasMoreElements())
							{
								Map<String,String> rejCase=new HashMap<String,String>();
								StringBuilder sbl=new StringBuilder();
								String caseIdRej=rc.nextElement().toString();
								if(caseIdRej!=null)
									rejCase.put("caseId",caseIdRej);
								StringBuffer sb =new StringBuffer();
								sb.append(" select eh.hospName as VALUE,eh.hospId as ID ");
								sb.append(" from EhfCase ec,EhfmHospitals eh where ec.caseHospCode=eh.hospId");
								sb.append(" and ec.caseId='"+caseIdRej+"'");
								List<LabelBean> lb=new ArrayList<LabelBean>();
								lb=genericDao.executeHQLQueryList(LabelBean.class,sb.toString());
								if(lb!=null && lb.size()>0)
									{
										for(LabelBean lb1:lb)
											{
												if(lb1.getVALUE()!=null)
													{
														hospName=lb1.getVALUE();
														sbl.append(hospName);
													}
												if(lb1.getID()!=null)
													{
														hospId=lb1.getID();
														sbl.append("("+hospId+")");
														
														rejCase.put("hospDtls",sbl.toString());
													}
												if(lb1.getVALUE()!=null||lb1.getID()!=null)
													{
														//sbl.append("\t-\t"+rejRemarks[ct]+"");
														rejCase.put("remarks",rejRemarks[ct]);
													}
												//sbl.append("\r\n");
											}
									}
								ct++;
								rejCaseList.add(rejCase);
							}
					}
					
					String msgEmailTo= config.getString("claimRejectedCaseEmail");
					
		    		//StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
		    		//while (token.hasMoreElements()) {
		    			//String mailId=(String) token.nextElement();
		    			//String[] emailToArray = { mailId };
					if(msgEmailTo!=null)
					{
						String[] emailToArray=msgEmailTo.split("#");
		    			EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						emailVO.setTemplateName(config
							 	.getString("EhfRejectedCasePayment"));
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("rejectedCasesPayment"));
						Map<String, List<Map<String,String>>> model = new HashMap<String, List<Map<String,String>>>();
						model.put("caseNo",
								rejCaseList );//lparamMap.get("RejectedCases")
						//generating Email
						commonService.generateNonImageMail(emailVO, model);
		    		}
			}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error:SetClaimStatus method in ClaimsPaymentDAOImpl with message "+e);
		}

		return lResult;
	
	}
}