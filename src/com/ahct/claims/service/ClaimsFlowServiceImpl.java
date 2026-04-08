/*Added by venkatesh for claim payments*/


package com.ahct.claims.service;

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



import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.dao.ClaimsFlowDAO;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseClaim;
import com.ahct.model.EhfClaimCexChklist;
import com.ahct.model.EhfClaimCtdChklst;
import com.ahct.model.EhfClaimTechChklst;
import com.ahct.model.EhfClaimTechChklstNew;
import com.ahct.model.EhfErroneousClaim;
import com.ahct.model.EhfFlagDtls;
import com.ahct.model.EhfOpdPatient;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfmDentalProcCriteria;
import com.ahct.model.EhfmDentalProcCriteriaPK;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmEDCHospActionDtls;
import com.ahct.model.EhfmEDCHospActionDtlsId;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
import com.ahct.preauth.vo.PreauthVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhsCeoSendbackDtls;

/**
 * The Class ClaimsFlowServiceImpl.
 *
 * @author Ishank Paharia
 * @class This Class is used as a serviceImpl for claim.
 * @version jdk 1.6
 * @Date 4 April 2013
 **/
public class ClaimsFlowServiceImpl implements ClaimsFlowService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ClaimsFlowServiceImpl.class);

	/** The generic dao. */
	private GenericDAO genericDao;

	/** The claims flow dao. */
	private ClaimsFlowDAO claimsFlowDAO;

	/** The millis per day. */
	final long MILLIS_PER_DAY = 24 * 3600 * 1000;

	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");

	/** The df1. */
	SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");

	/** The configuration service. */
	private static ConfigurationService configurationService;

	/** The config. */
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}

	/**
	 * Gets the generic dao.
	 * 
	 * @return the generic dao
	 */
	public GenericDAO getGenericDao() {
		return genericDao;
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

	/**
	 * Gets the claims flow dao.
	 * 
	 * @return the claims flow dao
	 */
	public ClaimsFlowDAO getClaimsFlowDAO() {
		return claimsFlowDAO;
	}

	/**
	 * Sets the claims flow dao.
	 * 
	 * @param claimsFlowDAO
	 *            the new claims flow dao
	 */
	public void setClaimsFlowDAO(ClaimsFlowDAO claimsFlowDAO) {
		this.claimsFlowDAO = claimsFlowDAO;
	}
	@Override
	public int getDentalErrClm(String caseId) 
	{
		int count = 0;
		
		try
		{
		String args[] = new String[1];
		StringBuffer sb=new StringBuffer();
		args[0] = "S18";
		sb.append(" select count(*) as COUNT from ehf_case_therapy ct,ehf_erroneous_claim er where ct.case_id=er.case_id ");
		sb.append(" and er.case_id='"+caseId+"' and ct.asri_cat_code=? ");

		List<LabelBean> orderList = genericDao.executeSQLQueryList(LabelBean.class, sb.toString(),args);
	
		
		if (!orderList.isEmpty())
			if (orderList.get(0).getCOUNT() != null)
				count = orderList.get(0).getCOUNT().intValue();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	
	/**
	 * Getting claim details for a particular case.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return claimFlowVO
	 */
	@Override
	public ClaimsFlowVO getCaseDtls(ClaimsFlowVO claimFlowVO) {

		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,
				claimFlowVO.getCaseId());
		if (ehfCase != null) {
			claimFlowVO.setSchemeType(ehfCase.getSchemeId());
			//EhfmHospitals ehfmHosp = genericDao.findById(EhfmHospitals.class,String.class,ehfCase.getCaseHospCode());
			EhfmEDCHospActionDtls ehfmEDCHospActionDtls= genericDao.findById(EhfmEDCHospActionDtls.class, EhfmEDCHospActionDtlsId.class,
															new EhfmEDCHospActionDtlsId(ehfCase.getCaseHospCode(),ehfCase.getSchemeId())) ;
			if(ehfmEDCHospActionDtls!=null)
				{
					if(ehfmEDCHospActionDtls.getHospStatus()!=null)
						claimFlowVO.setHospActiveYN(ehfmEDCHospActionDtls.getHospStatus());
				}	
			
			//claimFlowVO.setNabhFlag(ehfmHosp.getNabhFlg());
			//claimFlowVO.setHospActiveYN(ehfmHosp.getHospActiveYN());
			if(ehfCase.getNewBornBaby()!=null)
				claimFlowVO.setNewBornBaby(ehfCase.getNewBornBaby());
			claimFlowVO.setNabhFlag(ehfCase.getNabhFlg());
			claimFlowVO.setPhaseId(ehfCase.getPhaseId().toString());
			claimFlowVO.setCaseStat(ehfCase.getCaseStatus());
			claimFlowVO.setFlagged(ehfCase.getFlagged());
			
			if (ehfCase.getCaseStatus().equalsIgnoreCase(
					config.getString("EHF.Claims.DISCHARGE"))) {
               //calculating day difference between discharge/death date and today's date
				long msDiff;
				if (ehfCase.getCsDisDt() != null)
					msDiff = new Date().getTime()
							- ehfCase.getCsDisDt().getTime();
				else
					msDiff = new Date().getTime()
							- ehfCase.getCsDeathDt().getTime();
				long daysDiff = Math.round(msDiff / ((double) MILLIS_PER_DAY));
				claimFlowVO.setDaysDiff(daysDiff);
			}
			if (ehfCase.getCsAdmDt() != null)
				claimFlowVO.setAdmDate(df1.format(ehfCase.getCsAdmDt()));
			if (ehfCase.getCsDisDt() != null)
				claimFlowVO.setDisDate(df1.format(ehfCase.getCsDisDt()));
			else if (ehfCase.getCsDeathDt() != null)
				claimFlowVO.setDisDate(df1.format(ehfCase.getCsDeathDt()));
			if (ehfCase.getCsSurgDt() != null)
				claimFlowVO.setSurgDate(df1.format(ehfCase.getCsSurgDt()));
			if (ehfCase.getCsPreauthDt() != null)
				claimFlowVO.setPreAuthDate(df.format(ehfCase.getCsPreauthDt()));
			if (ehfCase.getPreauthAprvDate() != null)
				claimFlowVO.setPreAuthApprvDate(df.format(ehfCase.getPreauthAprvDate()));
            
			if(ehfCase.getPenaltyAmount()!=null)
			claimFlowVO.setPenaltyAmount(ehfCase.getPenaltyAmount().toString());
			
			Long totalPack = ehfCase.getPckAppvAmt();
			if (ehfCase.getEnhAppvAmt() != null)
				totalPack =  totalPack + ehfCase.getEnhAppvAmt();
			if (ehfCase.getComorbidAppvAmt() != null)
				totalPack = totalPack + ehfCase.getComorbidAppvAmt();
			claimFlowVO.setPackAmt(totalPack.toString());
			if (ehfCase.getClmSubDt() != null)
				claimFlowVO.setClaimSubDt(df.format(ehfCase.getClmSubDt()));
			if (ehfCase.getCsClAmount() != null)
				claimFlowVO.setClaimPaidAmt(ehfCase.getCsClAmount());
         //details for erroneous 
		if (ehfCase.getErrClaimStatus() != null
				&& !ehfCase.getErrClaimStatus().equalsIgnoreCase(
						ClaimsConstants.EMPTY)) {
			EhfErroneousClaim ehfErroneousClaim = genericDao.findById(
					EhfErroneousClaim.class, String.class,
					claimFlowVO.getCaseId() + "/E");
			claimFlowVO
					.setErrAmt(ehfErroneousClaim.getErrClaimAmt().toString());
			// claimFlowVO.setErrChAprAmt(ehfErroneousClaim.get);
			// claimFlowVO.setErrChRemark();
			if (ehfErroneousClaim.getErrSubDate() != null)
				claimFlowVO.setErrClaimSubDt(df.format(ehfErroneousClaim
						.getErrSubDate()));
			if (ehfErroneousClaim.getCtdAprvAmt() != null)
				claimFlowVO.setErrCtdAprAmt(ehfErroneousClaim.getCtdAprvAmt()
						.toString());
			claimFlowVO.setErrCtdRemark(ehfErroneousClaim.getCtdRemark());
			claimFlowVO.setErrMedcoRemark(ehfErroneousClaim.getMedcoRemark());
			claimFlowVO
					.setErrClaimStatus(ehfErroneousClaim.getErrClaimStatus());
			if (ehfErroneousClaim.getChAprvAmt() != null)
				claimFlowVO.setErrChAprAmt(ehfErroneousClaim.getChAprvAmt()
						.toString());
			claimFlowVO.setErrChRemark(ehfErroneousClaim.getChRemark());
			if (ehfErroneousClaim.getAcoAprAmt() != null)
				claimFlowVO.setErrAcoAprAmt(ehfErroneousClaim.getAcoAprAmt()
						.toString());
			claimFlowVO.setErrAcoRemark(ehfErroneousClaim.getAcoRemrk());

		}
		}
		EhfCaseClaim ehfCaseClaim = genericDao.findById(EhfCaseClaim.class,
				String.class, claimFlowVO.getCaseId());
		if (ehfCaseClaim != null) {
			claimFlowVO.setMedcoRemark(ehfCaseClaim.getMedcoRemrk());
			if (ehfCaseClaim.getClaimInitAmt() != null)
				claimFlowVO.setClaimInitAmt(ehfCaseClaim.getClaimInitAmt()
						.toString());
			if (ehfCaseClaim.getClaimBillAmt() != null)
				claimFlowVO.setBillAmt(ehfCaseClaim.getClaimBillAmt()
						.toString());
			if (ehfCaseClaim.getClaimBillDate() != null)
				claimFlowVO.setBillDt(df1.format(ehfCaseClaim
						.getClaimBillDate()));
			if (ehfCaseClaim.getCexAprAmt() != null)
				claimFlowVO
						.setCexAprAmt(ehfCaseClaim.getCexAprAmt().toString());
			claimFlowVO.setCexRemark(ehfCaseClaim.getCexRmrk());
			if (ehfCaseClaim.getCpdAprAmt() != null)
				claimFlowVO
						.setCpdAprAmt(ehfCaseClaim.getCpdAprAmt().toString());
			claimFlowVO.setClaimPanelRemark(ehfCaseClaim.getCpdRemrk());
			if (ehfCaseClaim.getSecCpdAmt() != null)
				claimFlowVO
						.setCpdAprAmtOrg(ehfCaseClaim.getSecCpdAmt().toString());
			claimFlowVO.setClaimPanelRemarkOrg(ehfCaseClaim.getSeCpdRmk());
			if (ehfCaseClaim.getCtdAprAmt() != null)
				claimFlowVO
						.setCtdAprAmt(ehfCaseClaim.getCtdAprAmt().toString());
			claimFlowVO.setCtdRemark(ehfCaseClaim.getCtdRemrk());
			if (ehfCaseClaim.getChAprAmt() != null)
				claimFlowVO.setChAprAmt(ehfCaseClaim.getChAprAmt().toString());
			if (ehfCaseClaim.getChEntAprAmt() != null)
				claimFlowVO.setChEntAprAmt(ehfCaseClaim.getChEntAprAmt().toString());
			if (ehfCaseClaim.getChNabhAmt() != null)
				claimFlowVO.setChNabhAmt(ehfCaseClaim.getChNabhAmt().toString());
			claimFlowVO.setChRemark(ehfCaseClaim.getChRemrk());
			if (ehfCaseClaim.getEoAprAmt() != null) {
				claimFlowVO.setShowEO(ClaimsConstants.YES);
				claimFlowVO.setEoAprAmt(ehfCaseClaim.getEoAprAmt().toString());
			}
			claimFlowVO.setEoRemark(ehfCaseClaim.getEoRemrk());
			if (ehfCaseClaim.getEoComAprAmt() != null) {
				claimFlowVO.setShowEOCom(ClaimsConstants.YES);
				claimFlowVO.setEoComAprAmt(ehfCaseClaim.getEoComAprAmt()
						.toString());
			}
			claimFlowVO.setEoComRemark(ehfCaseClaim.getEoComRemrk());
			if (ehfCaseClaim.getEoComEntAprAmt() != null)
				claimFlowVO.setEoComEntAprAmt(ehfCaseClaim.getEoComEntAprAmt().toString());
			if (ehfCaseClaim.getEoComNabhAmt() != null)
				claimFlowVO.setEoComNabhAmt(ehfCaseClaim.getEoComNabhAmt().toString());
			if (ehfCaseClaim.getAcoAprAmt() != null)
				claimFlowVO
						.setAcoAprAmt(ehfCaseClaim.getAcoAprAmt().toString());
			claimFlowVO.setAcoRemark(ehfCaseClaim.getAcoRemrk());
			claimFlowVO.setClaimInitFlag("Yes");
		} else {
			claimFlowVO.setClaimInitFlag("NO");
		}

		claimFlowVO = getNonTechCheckListData(claimFlowVO);
		claimFlowVO = getTechCheckListData(claimFlowVO);
		claimFlowVO = getTechCheckListDataNew(claimFlowVO);
		claimFlowVO = getTrustDocCheckListData(claimFlowVO);
		try{
			
			claimFlowVO = claimsFlowDAO.getInvestigationDetails(claimFlowVO);
			claimFlowVO = claimsFlowDAO.getDrugDetails(claimFlowVO);
			
		}catch(Exception e){
			LOGGER.error("" +e.getMessage());
		}
		return claimFlowVO;
	}

	private ClaimsFlowVO getTechCheckListDataNew(ClaimsFlowVO claimFlowVO) {
		// TODO Auto-generated method stub

		EhfClaimTechChklstNew techCheckLstNew = genericDao
				.findById(EhfClaimTechChklstNew.class, String.class,
						claimFlowVO.getCaseId());
		if (techCheckLstNew != null) {
			claimFlowVO.setTechCheckOrg1(techCheckLstNew.getDiagnosisYn());
			claimFlowVO.setTechCheckOrg2(techCheckLstNew.getCasemgmtYn());
			claimFlowVO.setTechCheckOrg3(techCheckLstNew.getEvidenceYn());
			claimFlowVO.setTechCheckOrg4(techCheckLstNew.getMandatoryYn());
			claimFlowVO.setTotalClaim(techCheckLstNew.getTotalClaim());
			claimFlowVO.setDeductionOrg(techCheckLstNew.getDedRecomd());
			claimFlowVO.setStayOrg(techCheckLstNew.getStay());
			claimFlowVO.setStayRemarkOrg(techCheckLstNew.getStayRemark());
			claimFlowVO.setInputsOrg(techCheckLstNew.getInput());
			claimFlowVO.setInputsRmrkOrg(techCheckLstNew.getInputRemark());
			claimFlowVO.setProfFeeOrg(techCheckLstNew.getProfFeeBill());
			claimFlowVO.setProfFeeRmrkOrg(techCheckLstNew.getProfFeeRemark());
			claimFlowVO.setInvestBillOrg(techCheckLstNew.getInvestBill());
			claimFlowVO.setInvestBillRmrkOrg(techCheckLstNew.getInvestBillRemark());
			claimFlowVO.setClaimPanelRemarkOrg(techCheckLstNew.getCpdRemark());
			claimFlowVO.setTechChklstOrg(ClaimsConstants.YES);
		}
		return claimFlowVO;
	
	}
	/**
	 * Gets the trust doc check list data.
	 * 
	 * @param claimFlowVO
	 *            the claim flow vo
	 * @return the trust doc check list data
	 */
	private ClaimsFlowVO getTrustDocCheckListData(ClaimsFlowVO claimFlowVO) {
		EhfClaimCtdChklst ctdCheckLst = genericDao.findById(
				EhfClaimCtdChklst.class, String.class, claimFlowVO.getCaseId());
		if (ctdCheckLst != null) {
			claimFlowVO.setTrustDoc1(ctdCheckLst.getAgreeYn());
			claimFlowVO.setTrustDoc2(ctdCheckLst.getCasemgmtYn());
			claimFlowVO.setTrustDoc3(ctdCheckLst.getEvidenceYn());
			claimFlowVO.setTrustDoc4(ctdCheckLst.getMandatoryYn());
			claimFlowVO.setCtdRemark(ctdCheckLst.getRemarks());
			// claimFlowVO.setChRemark(ctdCheckLst.getChRemarks());
			claimFlowVO.setTrstDocChklst("Yes");
		}
		return claimFlowVO;
	}

	/**
	 * Gets the tech check list data.
	 * 
	 * @param claimFlowVO
	 *            the claim flow vo
	 * @return the tech check list data
	 */
	private ClaimsFlowVO getTechCheckListData(ClaimsFlowVO claimFlowVO) {
		EhfClaimTechChklst techCheckLst = genericDao
				.findById(EhfClaimTechChklst.class, String.class,
						claimFlowVO.getCaseId());
		if (techCheckLst != null) {
			claimFlowVO.setTechCheck1(techCheckLst.getDiagnosisYn());
			claimFlowVO.setTechCheck2(techCheckLst.getCasemgmtYn());
			claimFlowVO.setTechCheck3(techCheckLst.getEvidenceYn());
			claimFlowVO.setTechCheck4(techCheckLst.getMandatoryYn());
			claimFlowVO.setTotalClaim(techCheckLst.getTotalClaim());
			claimFlowVO.setDeduction(techCheckLst.getDedRecomd());
			claimFlowVO.setStay(techCheckLst.getStay());
			claimFlowVO.setStayRemark(techCheckLst.getStayRemark());
			claimFlowVO.setInputs(techCheckLst.getInput());
			claimFlowVO.setInputsRmrk(techCheckLst.getInputRemark());
			claimFlowVO.setProfFee(techCheckLst.getProfFeeBill());
			claimFlowVO.setProfFeeRmrk(techCheckLst.getProfFeeRemark());
			claimFlowVO.setInvestBill(techCheckLst.getInvestBill());
			claimFlowVO.setInvestBillRmrk(techCheckLst.getInvestBillRemark());
			claimFlowVO.setClaimPanelRemark(techCheckLst.getCpdRemark());
			claimFlowVO.setTechChklst(ClaimsConstants.YES);
		}
		return claimFlowVO;
	}

	/**
	 * Gets the non tech check list data.
	 * 
	 * @param claimFlowVO
	 *            the claim flow vo
	 * @return the non tech check list data
	 */
	private ClaimsFlowVO getNonTechCheckListData(ClaimsFlowVO claimFlowVO) {

		EhfClaimCexChklist cexCheckList = genericDao
				.findById(EhfClaimCexChklist.class, String.class,
						claimFlowVO.getCaseId());
		if (cexCheckList != null) {
			claimFlowVO.setNameCheck(cexCheckList.getNameYn());
			claimFlowVO.setGenderCheck(cexCheckList.getGenderYn());
			claimFlowVO.setBenPhotoCheck(cexCheckList.getCardOnbedYn());
			claimFlowVO.setCaseStAdmDt(df1.format(cexCheckList
					.getSheetAdmDate()));
			claimFlowVO.setCaseStDischrgDt(df1.format(cexCheckList
					.getSheetDisdeathDate()));
			claimFlowVO.setCaseStSurgDt(df1.format(cexCheckList
					.getSheetSurgthrDate()));
			claimFlowVO.setAdmDtCheck(cexCheckList.getAdmDateYn());
			claimFlowVO.setDischargeDtCheck(cexCheckList.getDisDeathDateYn());
			claimFlowVO.setSurgDtCheck(cexCheckList.getSurgthrDateYn());
			claimFlowVO.setDocVer1(cexCheckList.getPatSignYn());
			claimFlowVO.setDocVer2(cexCheckList.getPatSatLetterYn());
			claimFlowVO.setDocVer3(cexCheckList.getMandRprtYn());
			claimFlowVO.setDocVer4(cexCheckList.getRprtSignYn());
			claimFlowVO.setDocVer5(cexCheckList.getDatePatnameYn());
			claimFlowVO.setCexRemark(cexCheckList.getCexRemarks());
			claimFlowVO.setNonTechChklst(ClaimsConstants.YES);
		}
		return claimFlowVO;
	}

	/**
	 * Saving claim data in tables.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return claimFlowVO
	 * @throws Exception the exception
	 */
	@Override
	public ClaimsFlowVO saveClaim(ClaimsFlowVO claimFlowVO) throws Exception {
		claimFlowVO = claimsFlowDAO.saveClaim(claimFlowVO);
		return claimFlowVO;
	}

	/**
	 * Gets the audit Trail data for worklist in claim.
	 *
	 * @param claimFlowVO the claim flow vo
	 * @return List of claimFlowVO
	 */
	@Override
	public List<ClaimsFlowVO> getAuditTrail(ClaimsFlowVO claimFlowVO) {
		List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
		String args[] = new String[1];
		args[0] = claimFlowVO.getCaseId();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select au.firstName ||''|| au.lastName as auditName ,a.remarks as cexRemark ,");
			query.append(" to_char(a.crtDt,'DD/MM/YYYY HH12:MI:SS AM') as auditDate,ac.grpShortName as auditRole,ac1.cmbDtlName as auditAction,a.apprvAmt as COUNT ");
			query.append(" from EhfAudit a , EhfmGrps ac , EhfmUsers au,EhfmCmbDtls ac1   ");
			query.append(" where a.actId=ac1.cmbDtlId and a.actBy = au.userId and ac.grpId=a.userRole ");
			query.append(" and a.actId in ( ");
			String[] claimStatus = ClaimsConstants.claimWorkFlowStatus;
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}

			query.append(" ) and a.id.caseId = ? ");
			query.append(" order by a.id.actOrder ");

			lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					query.toString(), args);

		} catch (Exception e) {
			LOGGER.error("Error occured in getAuditTrail() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstWorkList;
	}

	/**
	 * Gets patient Details.
	 *
	 * @param patientId the patient Id
	 * @return PatientVO
	 */
	@Override
	public PatientVO getPatientDtls(String patientId) {
		PatientVO patientVo = new PatientVO();
		EhfPatient ehfPatient = genericDao.findById(EhfPatient.class,
				String.class, patientId);
		if (ehfPatient != null) {
			if(ehfPatient.getContactNo()!=null)
			patientVo.setContactNo(ehfPatient.getContactNo().toString());
			patientVo.setFirstName(ehfPatient.getName());
			patientVo.setEmpCode(ehfPatient.getEmployeeNo());
			patientVo.seteMailId(ehfPatient.getEmailId());
		}
		return patientVo;
	}

	/**
	 * Saving Erroneous Claim
	 *
	 * @param claimFlowVO
	 * @return ClaimsFlowVO
	 */
	@Override
	public ClaimsFlowVO saveErrClaim(ClaimsFlowVO claimFlowVO) {
		claimFlowVO = claimsFlowDAO.saveErrClaim(claimFlowVO);
		return claimFlowVO;

	}

	/**
	 * Getting Cases for Payment
	 *
	 * @param claimFlowVO
	 * @return list of LabelBean
	 */
	@Override
	public List<LabelBean> getCasesForPayments(ClaimsFlowVO claimsFlowVO) {
		List<LabelBean> lstCasesForPayment = new ArrayList<LabelBean>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database = config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			StringBuffer query = new StringBuffer();
			query.append(" select EC.caseNo as ID,EC.caseId as SUBID,to_char(EC.lstUpdDt,'dd-mm-yyyy hh24:mi:ss') as LSTUPDDT, EH.hospName as LVL,ECC.totClaimAmt||'' as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID, EL.locName as EMPNAME,EHB.hospAccName as SUBNAME ");
			query.append(" from EhfCase EC,EhfmHospitals EH,EhfmEDCHospActionDtls EEHA,EhfPatient EP,EhfmLocations EL,EhfmHospBankDtls EHB,EhfCaseClaim ECC ");
			query.append(" where EC.caseHospCode=EEHA.id.hospId and EC.schemeId=EEHA.id.scheme and EEHA.id.hospId=EH.hospId and EP.patientId=EC.casePatientNo and ECC.caseId=EC.caseId");
			query.append(" and EL.id.locId=EP.districtCode and EHB.id.hospId=EEHA.id.hospId and EEHA.hospStatus in ('Y','S','C','D','E','CB') ");
			query.append( " and EC.patientScheme='"+config.getString("Scheme.EHS")+"' ");
			query.append(" and EC.schemeId=EHB.id.scheme ");

			if (claimsFlowVO.getCaseStat() != null
					&& !claimsFlowVO.getCaseStat().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and EC.caseStatus='"
						+ claimsFlowVO.getCaseStat() + "'");
			if (claimsFlowVO.getPatName() != null
					&& !claimsFlowVO.getPatName().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and upper(EP.name) like  upper('%"
						+ claimsFlowVO.getPatName().trim() + "%') ");
			if (claimsFlowVO.getWapNo() != null
					&& !claimsFlowVO.getWapNo().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EP.cardNo) like  upper('%"
						+ claimsFlowVO.getWapNo().trim() + "%') ");
			if (claimsFlowVO.getCaseId() != null
					&& !claimsFlowVO.getCaseId().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EC.caseNo) like  upper('%"
						+ claimsFlowVO.getCaseId().trim() + "%') ");
			if (claimsFlowVO.getFromDate() != null
					&& !claimsFlowVO.getFromDate()
							.equals(ClaimsConstants.EMPTY)
					&& claimsFlowVO.getToDate() != null
					&& !claimsFlowVO.getToDate().equals(ClaimsConstants.EMPTY)) {
				fromDate = claimsFlowVO.getFromDate();
				sqlFromDate = sqlf.format(sdf.parse(fromDate).getTime());
				toDate = claimsFlowVO.getToDate().toString();

				// To include todate in search criteria--adding date+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
				cal.add(Calendar.DAY_OF_YEAR, 1); // <--
				Date tomorrow = cal.getTime();
				String lStrToDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(tomorrow);
				// End of date+1

				sqlToDate = sqlf.format(sdf.parse(toDate).getTime());

				if (database.equalsIgnoreCase("ORACLE"))
					query.append("and EC.crtDt between  TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') and TO_DATE('" + lStrToDate
							+ "','DD-MM-YYYY')");
				else if (database.equalsIgnoreCase("MYSQL"))
					query.append("and EC.crtDt between '" + sqlFromDate
							+ "' and '" + sqlToDate + "'");
			}
			 if(claimsFlowVO.getSchemeType()!=null && !claimsFlowVO.getSchemeType().equalsIgnoreCase("")){
				  query.append(" and EC.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
			  }
			query.append(" order by EC.clmSubDt ");
			lstCasesForPayment = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getCasesForPayments() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstCasesForPayment;
	}

	/**
	 * Getting chronic Cases for Payment
	 *
	 * @param claimFlowVO
	 * @return list of LabelBean
	 */
	@Override
	public List<LabelBean> getChronicCasesForPayments(ClaimsFlowVO claimsFlowVO) {
		List<LabelBean> lstChronicCasesForPayment = new ArrayList<LabelBean>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database = config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			StringBuffer query = new StringBuffer();
			query.append(" select EC.id.chronicId as SUBID,EC.id.chronicNo as ID,EH.hospName as LVL,EC.claimAmount as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID, EL.locName as EMPNAME,EHB.hospAccName as SUBNAME ");
			query.append(" from EhfChronicCaseDtl EC,EhfmHospitals EH,EhfChronicPatientDtl EP,EhfmLocations EL,EhfmHospBankDtls EHB ");
			query.append(" where EC.hospCode=EH.hospId and EP.id.chronicId=EC.id.chronicId ");
			query.append(" and EL.id.locId=EP.districtCode and EHB.id.hospId=EH.hospId and EH.hospActiveYN in ('Y','S','C','D','E','CB') ");
			query.append(" and EC.schemeId=EHB.id.scheme ");

			if (claimsFlowVO.getCaseStat() != null
					&& !claimsFlowVO.getCaseStat().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and EC.chronicStatus='"
						+ claimsFlowVO.getCaseStat() + "'");
			if (claimsFlowVO.getPatName() != null
					&& !claimsFlowVO.getPatName().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and upper(EP.name) like  upper('%"
						+ claimsFlowVO.getPatName().trim() + "%') ");
			if (claimsFlowVO.getWapNo() != null
					&& !claimsFlowVO.getWapNo().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EP.cardNo) like  upper('%"
						+ claimsFlowVO.getWapNo().trim() + "%') ");
			if (claimsFlowVO.getCaseId() != null
					&& !claimsFlowVO.getCaseId().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EC.id.chronicNo) like  upper('%"
						+ claimsFlowVO.getCaseId().trim() + "%') ");
			if (claimsFlowVO.getFromDate() != null
					&& !claimsFlowVO.getFromDate()
							.equals(ClaimsConstants.EMPTY)
					&& claimsFlowVO.getToDate() != null
					&& !claimsFlowVO.getToDate().equals(ClaimsConstants.EMPTY)) {
				fromDate = claimsFlowVO.getFromDate();
				sqlFromDate = sqlf.format(sdf.parse(fromDate).getTime());
				toDate = claimsFlowVO.getToDate().toString();

				// To include todate in search criteria--adding date+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
				cal.add(Calendar.DAY_OF_YEAR, 1); // <--
				Date tomorrow = cal.getTime();
				String lStrToDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(tomorrow);
				// End of date+1

				sqlToDate = sqlf.format(sdf.parse(toDate).getTime());

				if (database.equalsIgnoreCase("ORACLE"))
					query.append("and EC.crtDt between  TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') and TO_DATE('" + lStrToDate
							+ "','DD-MM-YYYY')");
				else if (database.equalsIgnoreCase("MYSQL"))
					query.append("and EC.crtDt between '" + sqlFromDate
							+ "' and '" + sqlToDate + "'");
			}
			 if(claimsFlowVO.getSchemeType()!=null && !claimsFlowVO.getSchemeType().equalsIgnoreCase("")){
				  query.append(" and EC.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
			  }
			query.append(" order by EC.clmSubDt ");
			lstChronicCasesForPayment = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getChronicCasesForPayments() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstChronicCasesForPayment;
	}
	/**
	 * Getting Cases Status for dropDown
	 *
	 * @return list of LabelBean
	 */
	public List<LabelBean> getCaseStatus(String userName) {
		List<LabelBean> caseList = new ArrayList<LabelBean>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (" );
			String[] claimStatus = ClaimsConstants.claimStatues;
			if(userName!=null && (userName.equalsIgnoreCase("C075")||userName.equalsIgnoreCase("D058")))
				claimStatus =  ClaimsConstants.claimStatuesRej;
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}
			query.append( ")");
			query.append(" order by a.cmbDtlName ");
			caseList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getCaseStatus() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
		}

		return caseList;
	}
	
	
	
	public List<LabelBean> getCaseStatusByGrp(String grpId) {
		List<LabelBean> caseList = new ArrayList<LabelBean>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (" );
			String[] claimStatus = ClaimsConstants.claimStatues;
			if(grpId!=null && (grpId.equalsIgnoreCase(config.getString("FIN.AccountsJEOGrp"))))
				claimStatus =  ClaimsConstants.claimStatuesRej;
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}
			query.append( ")");
			query.append(" order by a.cmbDtlName ");
			caseList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getCaseStatus() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
		}

		return caseList;
	}
	
	
	
	/**
	 * Getting chronic Status for dropDown
	 *
	 * @return list of LabelBean
	 */
	public List<LabelBean> getChronicStatus(String userName) {
		List<LabelBean> chronicList = new ArrayList<LabelBean>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (" );
			String[] chronicStatues = ClaimsConstants.chronicStatues;
			if(userName!=null && userName.equalsIgnoreCase("C075"))
				chronicStatues =  ClaimsConstants.claimStatuesRej;
			for (int i = 0; i < chronicStatues.length; i++) {
				query.append(" '" + chronicStatues[i] + "' ");
				if (i != chronicStatues.length - 1)
					query.append(",");
			}
			query.append( ")");
			query.append(" order by a.cmbDtlName ");
			chronicList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getCaseStatus() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
		}

		return chronicList;
	}

	/**
	 * Getting Err Cases Status for dropDown
	 *
	 * @return list of LabelBean
	 */
	public List<LabelBean> getErrCaseStatus(String userName) {
		List<LabelBean> caseList = new ArrayList<LabelBean>();

		try {
			StringBuffer query = new StringBuffer();
			
			query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (" );
			String[] claimStatus = ClaimsConstants.ErrClaimStatues;
			if(userName!=null && userName.equalsIgnoreCase("C075"))
				claimStatus =  ClaimsConstants.ErrClaimStatuesRej;
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}
			query.append(") order by a.cmbDtlName ");
			caseList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getErrCaseStatus() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
		}

		return caseList;
	}

	
	
	public List<LabelBean> getErrCaseStatusByGrp(String grpId) {
		List<LabelBean> caseList = new ArrayList<LabelBean>();

		try {
			StringBuffer query = new StringBuffer();
			
			query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (" );
			String[] claimStatus = ClaimsConstants.ErrClaimStatues;
			if(grpId!=null && grpId.equalsIgnoreCase(config.getString("FIN.AccountsJEOGrp")))
				claimStatus =  ClaimsConstants.ErrClaimStatuesRej;
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}
			query.append(") order by a.cmbDtlName ");
			caseList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getErrCaseStatus() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
		}

		return caseList;
	}
	
	
	/**
	 * Getting Cases Details for Payment
	 *
	 * @param caseNo
	 * @param paymentType
	 * @return CommonDtlsVO
	 */
	@Override
	public CommonDtlsVO getCaseDtlsForPayment(String caseNo, String paymentType) {

		CommonDtlsVO claimsFlowVO = claimsFlowDAO.getCaseDtlsForPayment(caseNo,
				paymentType);
		return claimsFlowVO;
	}

	/**
	 * Updating Claim Statues while Paying
	 *
	 * @param lparamMap
	 * @return HashMap
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap updateClaimStatus(HashMap lparamMap) {

		lparamMap = claimsFlowDAO.updateClaimStatus(lparamMap);
		return lparamMap;
	}

	/**
	 * Getting Err-Cases for Payment
	 *
	 * @param claimFlowVO
	 * @return list of LabelBean
	 */
	@Override
	public List<LabelBean> getErrCasesForPayments(ClaimsFlowVO claimsFlowVO) {
		List<LabelBean> lstCasesForPayment = new ArrayList<LabelBean>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database = config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			StringBuffer query = new StringBuffer();
			query.append(" select ECC.errClaimId as ID,EC.caseNo as DSGNID,EC.caseId as SUBID,EH.hospName as LVL,ECC.totClaimAmt||'' as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID, EL.locName as EMPNAME,EHB.hospAccName as SUBNAME ");
			query.append(" from EhfCase EC,EhfmHospitals EH,EhfPatient EP,EhfmLocations EL,EhfmHospBankDtls EHB,EhfErroneousClaim ECC ");
			query.append(" where EC.caseHospCode=EH.hospId and EP.patientId=EC.casePatientNo and ECC.caseId=EC.caseId");
			query.append(" and EL.id.locId=EP.districtCode and EHB.id.hospId=EH.hospId and EH.hospActiveYN in ('Y','S','C','D','E','CB') ");
			query.append( " and EC.patientScheme='"+config.getString("Scheme.EHS")+"' ");
			query.append(" and EC.schemeId=EHB.id.scheme ");
			
			if (claimsFlowVO.getCaseStat() != null
					&& !claimsFlowVO.getCaseStat().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and ECC.errClaimStatus='"
						+ claimsFlowVO.getCaseStat() + "'");
			if (claimsFlowVO.getPatName() != null
					&& !claimsFlowVO.getPatName().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and upper(EP.name) like  upper('%"
						+ claimsFlowVO.getPatName().trim() + "%') ");
			if (claimsFlowVO.getWapNo() != null
					&& !claimsFlowVO.getWapNo().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EP.cardNo) like  upper('%"
						+ claimsFlowVO.getWapNo().trim() + "%') ");
			if (claimsFlowVO.getCaseId() != null
					&& !claimsFlowVO.getCaseId().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EC.caseNo) like  upper('%"
						+ claimsFlowVO.getCaseId().trim() + "%') ");
			if (claimsFlowVO.getFromDate() != null
					&& !claimsFlowVO.getFromDate()
							.equals(ClaimsConstants.EMPTY)
					&& claimsFlowVO.getToDate() != null
					&& !claimsFlowVO.getToDate().equals(ClaimsConstants.EMPTY)) {
				fromDate = claimsFlowVO.getFromDate();
				sqlFromDate = sqlf.format(sdf.parse(fromDate).getTime());
				toDate = claimsFlowVO.getToDate().toString();

				// To include todate in search criteria--adding date+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
				cal.add(Calendar.DAY_OF_YEAR, 1); // <--
				Date tomorrow = cal.getTime();
				String lStrToDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(tomorrow);
				// End of date+1

				sqlToDate = sqlf.format(sdf.parse(toDate).getTime());

				if (database.equalsIgnoreCase("ORACLE"))
					query.append("and ECC.crtDt between  TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') and TO_DATE('" + lStrToDate
							+ "','DD-MM-YYYY')");
				else if (database.equalsIgnoreCase("MYSQL"))
					query.append("and ECC.crtDt between '" + sqlFromDate
							+ "' and '" + sqlToDate + "'");
			}
			 if(claimsFlowVO.getSchemeType()!=null && !claimsFlowVO.getSchemeType().equalsIgnoreCase("")){
				  query.append(" and ECC.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
			  }    
			query.append(" order by EC.clmSubDt ");
			lstCasesForPayment = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getErrCasesForPayments() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstCasesForPayment;
	}

	/**
	 * Getting TDS-Cases for Payment
	 *
	 * @param claimFlowVO
	 * @return list of LabelBean
	 */
	@Override
	public List<LabelBean> getTDSCasesForPayments(ClaimsFlowVO claimsFlowVO) {
		List<LabelBean> lstCasesForPayment = new ArrayList<LabelBean>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database = config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			StringBuffer query = new StringBuffer();
			StringBuffer schemeQuery = new StringBuffer();
			query.append(" select ECTP.tdsPaymentId as ID,EC.caseNo as DSGNID,EH.hospName as LVL,ECTP.claimAmt||'' as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID");
            // for followup Payment
			if (claimsFlowVO.getPaymentType() != null
					&& !claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY)
					&& claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.FPCLAIM_FORM_PAYMENT)) {
				query.append(" ,ECFC.caseFollowupId as SUBID from EhfCase EC,EhfmHospitals EH,EhfClaimTdsPayment ECTP,EhfPatient EP,EhfCaseFollowupClaim ECFC where ECFC.caseId=EC.caseId and ECFC.caseFollowupId = ECTP.caseFllwUpId ");
				schemeQuery.append(" and ECFC.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
			}             // for Erroneous Payment
			else if (claimsFlowVO.getPaymentType() != null
					&& !claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY)
					&& claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.ERRCLAIM_FORM_PAYMENT)) {
				query.append(" ,ECE.errClaimId as SUBID from EhfCase EC,EhfmHospitals EH,EhfClaimTdsPayment ECTP,EhfPatient EP,EhfErroneousClaim ECE where ECE.caseId=EC.caseId and ECE.caseId = ECTP.caseId ");
				schemeQuery.append(" and ECE.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
			} else {
				query.append(" ,EC.caseId as SUBID from EhfCase EC,EhfmHospitals EH,EhfClaimTdsPayment ECTP,EhfPatient EP where ECTP.caseId=EC.caseId ");
				schemeQuery.append(" and EC.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
			}

			query.append(" and EC.caseHospCode=EH.hospId  and EP.patientId=EC.casePatientNo ");
			query.append(" and  ECTP.paymentStatus in ('"+ ClaimsConstants.CLAIM_READY_PAYMENT+ "','"+ ClaimsConstants.CLAIM_REJ_BANK+ "') and ECTP.caseType='" + ClaimsConstants.TRUST_DEDUCTOR
					+ "' ");

			if (claimsFlowVO.getPaymentType() != null
					&& !claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and ECTP.paymentType ='"
						+ claimsFlowVO.getPaymentType() + "' ");
			if (claimsFlowVO.getPatName() != null
					&& !claimsFlowVO.getPatName().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and upper(EP.name) like  upper('%"
						+ claimsFlowVO.getPatName().trim() + "%') ");
			if (claimsFlowVO.getWapNo() != null
					&& !claimsFlowVO.getWapNo().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EP.cardNo) like  upper('%"
						+ claimsFlowVO.getWapNo().trim() + "%') ");
			if (claimsFlowVO.getCaseId() != null
					&& !claimsFlowVO.getCaseId().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EC.caseNo) like  upper('%"
						+ claimsFlowVO.getCaseId().trim() + "%') ");
			if (claimsFlowVO.getFromDate() != null
					&& !claimsFlowVO.getFromDate()
							.equals(ClaimsConstants.EMPTY)
					&& claimsFlowVO.getToDate() != null
					&& !claimsFlowVO.getToDate().equals(ClaimsConstants.EMPTY)) {
				fromDate = claimsFlowVO.getFromDate();
				sqlFromDate = sqlf.format(sdf.parse(fromDate).getTime());
				toDate = claimsFlowVO.getToDate().toString();

				// To include todate in search criteria--adding date+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
				cal.add(Calendar.DAY_OF_YEAR, 1); // <--
				Date tomorrow = cal.getTime();
				String lStrToDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(tomorrow);
				// End of date+1

				sqlToDate = sqlf.format(sdf.parse(toDate).getTime());

				if (database.equalsIgnoreCase("ORACLE"))
					query.append("and ECTP.crtDate between  TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') and TO_DATE('" + lStrToDate
							+ "','DD-MM-YYYY')");
				else if (database.equalsIgnoreCase("MYSQL"))
					query.append("and ECTP.crtDate between '" + sqlFromDate
							+ "' and '" + sqlToDate + "'");
			}
			if(claimsFlowVO.getSchemeType()!=null && !claimsFlowVO.getSchemeType().equalsIgnoreCase("")){
				query.append(schemeQuery);
			  }
			query.append(" order by EC.clmSubDt ");
			lstCasesForPayment = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getTDSCasesForPayments() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstCasesForPayment;
	}
	/**
	 * Getting Data for TDS dropdown
	 *
	 * @param claimFlowVO
	 * @return list of LabelBean
	 */
	@Override
	public List<LabelBean> getTdsPaymentType() {
		List<LabelBean> tdsPayList = new ArrayList<LabelBean>();

		try {
			StringBuffer query = new StringBuffer();

			query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbHdrId =?");
			query.append(" order by a.cmbDtlName ");
			String args[] = new String[1];
			args[0] = "CH7";
			tdsPayList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tdsPayList;
	}

	/**
	 * Updating Claim Statuses while paying
	 *
	 * @param HashMap
	 * @return HashMap
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap updateTDSClaimStatus(HashMap hParamMap) {
		hParamMap = claimsFlowDAO.updateTDSClaimStatus(hParamMap);
		return hParamMap;
	}

	/**
	 * Update claim status sent by bank.
	 */
	public void updateClaimStatusSentByBank() {
		claimsFlowDAO.updateClaimStatusSentByBank();
	}

	/**
	 * Checking disscussion Cases Condiiton
	 *
	 * @param claimFlowVO
	 * @return String
	 */
	@Override
	public String checkDissCaseFlag(ClaimsFlowVO claimFlowVO) {
		List<LabelBean> dissCaseLst = new ArrayList<LabelBean>();
        String dissCaseFlag = "false";
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select (sysdate - a.lstUpdDt) * 24||'' as VALUE from EhfCase a  where a.caseStatus =? and a.nabhHosp is null ");
			query.append(" and a.lstUpdUsr=? ");
			String args[] = new String[2];
			if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.CTD")))
			{args[0] = config.getString("EHF.Claims.ClaimKeptDiscuCTD");
			 args[1] = claimFlowVO.getUserId();
			}
			else if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("EHF.Claims.CH")))
			{args[0] = config.getString("EHF.Claims.ClaimKeptDiscuCH");
			 args[1] = claimFlowVO.getUserId();
			}
			dissCaseLst = genericDao.executeHQLQueryList(LabelBean.class,query.toString(), args);
			for(LabelBean dissCase : dissCaseLst){
				long disCaseTime = (long) Double.parseDouble(dissCase.getVALUE());
				//if pending from more than 2 days
				if(disCaseTime>=48){
					dissCaseFlag = "true";break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occured in checkDissCaseFlag() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}

		return dissCaseFlag;
	}
	/**
	 * Calculating Claim Amount in ramco login 
	 *
	 * @param claimFlowVO
	 * @return claimFlowVO
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ClaimsFlowVO calculateClaimAmount(ClaimsFlowVO claimFlowVO) {
		List<PreauthVO> procedureLst = new ArrayList<PreauthVO>();
		List<LabelBean> clinicalCount = new ArrayList<LabelBean>();
		Map<String,String> surgerys = new HashMap();
		
		float hospStayAmt =0;
    	float cmnCatAmt =0;
    	float icdAmt =0;
    	float TotAmt = 0;float nabhMaxAmt =0;
    	float hospAmtF =(float) 0;
    	int clinicalNoOfStay=0;int noOfDaysF=0;int finalAmount=0;
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
		args[0] = claimFlowVO.getCaseId();
		//getting no of days from clinical notes 
		query.append(" select distinct TO_DATE(ac.investigationDate,'DD-MM-YYYY')||'' as VALUE from EhfCaseClinicalNotes ac where ac.caseId=? and ac.prePostOperative in ('PRE','POST') ");
		clinicalCount = genericDao.executeHQLQueryList(LabelBean.class,query.toString(), args); 
		if(clinicalCount!=null && clinicalCount.size()>0){
			//if(clinicalCount.get(0).getVALUE()!=null && !clinicalCount.get(0).getVALUE().equalsIgnoreCase(""))
			clinicalNoOfStay = clinicalCount.size();
		}
		query = new StringBuffer();
		query.append(" select a.asriCatCode as asriCatName,f.amount as amount,b.hospstayAmt as hospStayAmt,b.commonCatAmt as commonCatAmt,b.icdAmt as icdAmt,b.noOfDays||'' as days");
		query.append(" from EhfCaseTherapy a,EhfmTherapyProcMst b,EhfPatient c,EhfCase d,EhfmHospitals e,EhfmSlabPackage f where ");
		query.append(" a.icdProcCode=b.id.icdProcCode and e.hospId=d.caseHospCode and c.patientId=d.casePatientNo and a.caseId=d.caseId and f.id.nabhFlag=e.nabhFlg and f.id.slabType=c.slab ");
		query.append(" and a.caseId=? and a.activeyn='Y' and a.asriCatCode=b.id.asriCode ");
		//getting all amounts
		procedureLst = genericDao.executeHQLQueryList(PreauthVO.class,query.toString(), args);
		for(PreauthVO procedure : procedureLst){
			if(procedure.getCommonCatAmt()!= null && Float.valueOf(procedure.getCommonCatAmt()) >0)
        	surgerys.put(procedure.getAsriCatName(),procedure.getCommonCatAmt());
			String icdamt = procedure.getIcdAmt();
    		icdAmt = icdAmt+Float.valueOf(icdamt);
    		String hospAmt = procedure.getHospStayAmt();
    		String noOfDays = procedure.getDays();
    		
    		if(procedure.getAmount() != null && !procedure.getAmount().equalsIgnoreCase(""))
        		hospAmtF = Float.parseFloat(hospAmt);
        		if(nabhMaxAmt < (Integer.parseInt(procedure.getAmount()) * Integer.parseInt(noOfDays)) )
        		{
        			nabhMaxAmt=Integer.parseInt(procedure.getAmount()) * Integer.parseInt(noOfDays);
        			noOfDaysF = Integer.parseInt(noOfDays);
        			finalAmount= Integer.parseInt(procedure.getAmount());
        		}
        		if(hospStayAmt  < hospAmtF )
        		{
        			hospStayAmt =hospAmtF;	
        		}   		
		}
		if(noOfDaysF>clinicalNoOfStay){
			nabhMaxAmt=finalAmount*clinicalNoOfStay;
		}
		Iterator iter = surgerys.entrySet().iterator();		 
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			cmnCatAmt = cmnCatAmt + Float.valueOf(mEntry.getValue().toString())	;
		}
		//adding max of hosp_stay + commonCatAmt+icdAmt + nabh Max amt(clinical days and from db)
		TotAmt = hospStayAmt+cmnCatAmt+icdAmt+nabhMaxAmt;
    	TotAmt = Math.round(TotAmt);
    	
    	EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class,claimFlowVO.getCaseId());
    	if (ehfCase.getEnhAppvAmt() != null)
    		TotAmt = (long) (TotAmt + ehfCase.getEnhAppvAmt());
		if (ehfCase.getComorbidAppvAmt() != null)
			TotAmt = (long) (TotAmt + ehfCase.getComorbidAppvAmt());
    	claimFlowVO.setClaimInitAmt(TotAmt+"");
		return claimFlowVO;
	}

	@Override
	public String updateClaimStatusReady(ClaimsFlowVO claimFlowVO) {
		String lResult = claimsFlowDAO.updateClaimStatusReady(claimFlowVO);
		return lResult;
	}
	
	/*added for chronic claims*/
	@Override
	public String updateChronicClaimStatusReady(ClaimsFlowVO claimFlowVO) {
		String lResult = claimsFlowDAO.updateChronicClaimStatusReady(claimFlowVO);
		return lResult;
	}
	
	@Override
	public List<LabelBean> getRfCasesForPayments(ClaimsFlowVO claimsFlowVO) {

		List<LabelBean> lstCasesForPayment = new ArrayList<LabelBean>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database = config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			StringBuffer query = new StringBuffer();
			query.append(" select ECTP.trustPaymentId as ID,EC.caseNo as DSGNID,EH.hospName as LVL,ECTP.claimAmount||'' as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID");
            // for followup Payment
			if (claimsFlowVO.getPaymentType() != null
					&& !claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY)
					&& claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.FPCLAIM_FORM_PAYMENT)) {
				query.append(" ,ECFC.caseFollowupId as SUBID from EhfCase EC,EhfmHospitals EH,EhfClaimTrustPayment ECTP,EhfPatient EP,EhfCaseFollowupClaim ECFC where ECFC.caseId=EC.caseId and ECFC.caseFollowupId = ECTP.caseFollowupId ");
			}             // for Erroneous Payment
			else if (claimsFlowVO.getPaymentType() != null
					&& !claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY)
					&& claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.ERRCLAIM_FORM_PAYMENT)) {
				query.append(" ,ECE.errClaimId as SUBID from EhfCase EC,EhfmHospitals EH,EhfClaimTrustPayment ECTP,EhfPatient EP,EhfErroneousClaim ECE where ECE.caseId=EC.caseId and ECE.caseId = ECTP.caseId ");
			} else {
				query.append(" ,EC.caseId as SUBID from EhfCase EC,EhfmHospitals EH,EhfClaimTrustPayment ECTP,EhfPatient EP where ECTP.caseId=EC.caseId ");
			}

			query.append(" and EC.caseHospCode=EH.hospId  and EP.patientId=EC.casePatientNo ");
			query.append(" and  ECTP.paymentStatus in ('"+ ClaimsConstants.CLAIM_REJ_BANK+ "') ");

			/*if (claimsFlowVO.getPaymentType() != null
					&& !claimsFlowVO.getPaymentType().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and ECTP.paymentType ='"
						+ claimsFlowVO.getPaymentType() + "' ");*/
			if (claimsFlowVO.getPatName() != null
					&& !claimsFlowVO.getPatName().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and upper(EP.name) like  upper('%"
						+ claimsFlowVO.getPatName().trim() + "%') ");
			if (claimsFlowVO.getWapNo() != null
					&& !claimsFlowVO.getWapNo().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EP.cardNo) like  upper('%"
						+ claimsFlowVO.getWapNo().trim() + "%') ");
			if (claimsFlowVO.getCaseId() != null
					&& !claimsFlowVO.getCaseId().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EC.caseNo) like  upper('%"
						+ claimsFlowVO.getCaseId().trim() + "%') ");
			if (claimsFlowVO.getFromDate() != null
					&& !claimsFlowVO.getFromDate()
							.equals(ClaimsConstants.EMPTY)
					&& claimsFlowVO.getToDate() != null
					&& !claimsFlowVO.getToDate().equals(ClaimsConstants.EMPTY)) {
				fromDate = claimsFlowVO.getFromDate();
				sqlFromDate = sqlf.format(sdf.parse(fromDate).getTime());
				toDate = claimsFlowVO.getToDate().toString();

				// To include todate in search criteria--adding date+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
				cal.add(Calendar.DAY_OF_YEAR, 1); // <--
				Date tomorrow = cal.getTime();
				String lStrToDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(tomorrow);
				// End of date+1

				sqlToDate = sqlf.format(sdf.parse(toDate).getTime());

				if (database.equalsIgnoreCase("ORACLE"))
					query.append("and ECTP.crtDt between  TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') and TO_DATE('" + lStrToDate
							+ "','DD-MM-YYYY')");
				else if (database.equalsIgnoreCase("MYSQL"))
					query.append("and ECTP.crtDt between '" + sqlFromDate
							+ "' and '" + sqlToDate + "'");
			}
			query.append(" order by EC.clmSubDt ");
			lstCasesForPayment = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
		} catch (Exception e) {
			LOGGER.error("Error occured in getRFCasesForPayments() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstCasesForPayment;
	}

	@Override
	public String updateTDSStatusReady(ClaimsFlowVO claimFlowVO) {
		String result = claimsFlowDAO.updateTDSStatusReady(claimFlowVO);
		return result;
	}
	@Override
	public String checkFlaggingForMoneyCollection(ClaimsFlowVO claimFlowVO) {
		String result="N";StringBuffer query = new StringBuffer();
		query.append("select caseId as caseId from EhfFlagDtls where natureOfFlag='CD354' and currentStatusOfFlag not in ('CD370') and caseId='"+claimFlowVO.getCaseId()+"'");
		List<ClaimsFlowVO> lstFlaggedCases = new ArrayList<ClaimsFlowVO>();
		lstFlaggedCases = genericDao.executeHQLQueryList(ClaimsFlowVO.class, query.toString());
		if(lstFlaggedCases!=null && lstFlaggedCases.size()>0)
			result="Y";
		return result;
	}
	
	public boolean verifyClaimPending(String caseId)
	{
	boolean isPending=false;
	String[] claimStatus;
	
	StringBuffer query = new StringBuffer();
	List<LabelBean> verifyList=new ArrayList<LabelBean>();
	try
	{
	query.append("select case_id as ID  from ehf_case ec where ec.case_id='"+caseId+"' and ec.case_status in (");
	claimStatus =  ClaimsConstants.claimsCEOSentBackStatuses;
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}
			query.append(" ) ");
	
	
	verifyList=genericDao.executeSQLQueryList(LabelBean.class,query.toString());
	
	if(verifyList.size()>0)
		isPending=true;
	else
		isPending=false;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	return isPending;
	}
	
	
	public String updateSentBackClaims(ClaimsFlowVO claimFlowVO)
	{
		
		
		String sendBackFlag=claimFlowVO.getSendBackFlag();
		String remarks=claimFlowVO.getRemarks();
		String actionDone=claimFlowVO.getActionDone();
		String claimType=claimFlowVO.getModuleType();
		String nextStatus=null;
		Date date=new Date();
		String msg=null;
		
		if(("claims").equalsIgnoreCase(claimType))
		{
			String caseId=claimFlowVO.getCaseId();
			nextStatus=config.getString("EHF.claims.CEOPendingUpdated");;
			EhfCase ehfCase=new EhfCase();
			Long lActOrder = 0L;
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
			try
			{
			ehfCase=genericDao.findById(EhfCase.class,String.class,caseId);
			if(ehfCase!=null)
			{
				ehfCase.setCaseStatus(nextStatus);
				ehfCase.setLstUpdDt(date);
				ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
				ehfCase.setPendingWith("");
			}

			
			// insert into asrit_audit
			EhfAudit asritAudit = new EhfAudit();
			EhfAuditId asritAuditPK = new EhfAuditId(lActOrder,
					claimFlowVO.getCaseId());
			asritAudit.setId(asritAuditPK);
			asritAudit.setActId(nextStatus);
			asritAudit.setActBy(claimFlowVO.getUserId());
			asritAudit.setCrtUsr(claimFlowVO.getUserId());
			asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			asritAudit.setLangId(ClaimsConstants.LANG_ID);
			asritAudit.setRemarks(remarks);
			//asritAudit.setApprvAmt(approveAmt);
			asritAudit.setUserRole(claimFlowVO.getRoleId());

			genericDao.save(asritAudit);
		    
		    genericDao.save(ehfCase);
		     
		    msg="Case Updated and Sent to CEO Successfully";
			}
			catch(Exception e)
			{
				msg="Failed to Save Remarks.Please try Again";
				e.printStackTrace();
				LOGGER.error("Error occured in updateSentBackClaims() method in claimPaymentServiceImpl"+e.getMessage());
			}
		    
		    
		}
		
		
		/*else if(("followUp").equalsIgnoreCase(claimType))
		{
			String followupId=claimFlowVO.getFollowUpId();
			nextStatus=config.getString("EHF.followUp.CEOPendingUpdated");;
			EhfCaseFollowupClaim ehfCaseFollowupClaim=new EhfCaseFollowupClaim();
			
			try
			{
			ehfCaseFollowupClaim=genericDao.findById(EhfCaseFollowupClaim.class,String.class,followupId);
			if(ehfCaseFollowupClaim!=null)
			{
				ehfCaseFollowupClaim.setFollowUpStatus(nextStatus);
				ehfCaseFollowupClaim.setLstUpdDt(date);
				ehfCaseFollowupClaim.setLstUpdUsr(claimFlowVO.getUserId());
			}
			List<EhsCeoSendbackDtls> ehsCeoSendbackDtls=new ArrayList<EhsCeoSendbackDtls>();
			EhsCeoSendbackDtls CeoSendbackDtls=new EhsCeoSendbackDtls();
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		    criteriaList.add(new GenericDAOQueryCriteria("id.referenceNo",followupId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		    criteriaList.add(new GenericDAOQueryCriteria("acted","N", GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
					    
		    ehsCeoSendbackDtls=genericDao.findAllByCriteria(EhsCeoSendbackDtls.class,criteriaList);
		    criteriaList.removeAll(criteriaList);
		    if(ehsCeoSendbackDtls!=null)
		    CeoSendbackDtls=ehsCeoSendbackDtls.get(0);
		    
		    CeoSendbackDtls.setLstUpdUsr(claimFlowVO.getUserId());
		    CeoSendbackDtls.setLstUpdDt(date);
		    CeoSendbackDtls.setUserRemarks(remarks);
		    CeoSendbackDtls.setActed("Y");
		    
		   genericDao.save(ehfCaseFollowupClaim);
		    genericDao.save(CeoSendbackDtls);
		    msg="Remarks Saved Successfully";
			}
			catch(Exception e)
			{
				msg="Failed to Save Remarks.Please try Again";
				e.printStackTrace();
				LOGGER.error("Error occured in updateSentBackClaims() method in claimPaymentServiceImpl"+e.getMessage());
			}
		    
		    
		}*/
		
		return msg;
		
	}
	/*
	 * Added by Srikalyan to get EhfCase Object
	 */
	public EhfCase getCase(String caseId)
		{
			EhfCase ehfCase =new EhfCase();
			try
				{
					if(caseId!=null)
						{
							ehfCase=genericDao.findById(EhfCase.class, String.class, caseId);
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
					//LOGGER.error("Error occured in getCase() method in claimsFlowServiceImpl"+e.getMessage());
				}
			return ehfCase;
		}
	
	
	public String getCaseStat(String caseId)
	{
		String caseStatus = null;
		try{
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
		if(ehfCase != null)
		{
			caseStatus = ehfCase.getCaseStatus();
		}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return caseStatus;
	}
	@Override
	public int getCount(String caseId,String roleId) 
	{
		int count = 0;
		
		try
		{
		StringBuffer query = new StringBuffer();	
		String args[] = new String[1];
		
		if(roleId!=null && roleId.equalsIgnoreCase(config.getString("EHF.Claims.CTD")))
			args[0] = config.getString("EHF.Claims.TrustDocPending");
		else if(roleId!=null && roleId.equalsIgnoreCase(config.getString("Group.COCH")))
			args[0]= config.getString("EHF.Claims.HeadPending");
		else if(roleId!=null && roleId.equalsIgnoreCase(config.getString("EHF.Claims.CPD")))
			args[0]= config.getString("EHF.Claims.PanelDocPending");
		
		
		query.append("select count(*) as COUNT from ehf_audit where act_id=? and case_id='"+caseId+"' ");

		List<LabelBean> orderList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
	
		
		if (!orderList.isEmpty())
			if (orderList.get(0).getCOUNT() != null)
				count = orderList.get(0).getCOUNT().intValue();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	/***
	  * @author Kalyan
	  * @param Case ID
	  * @return Dental Flag
	  * @method Checks Whether the case is Dental or not  
	  */
	@Override
	 public String checkDentalCase(String caseId)
	 	{
		 	String returnMsg="N";
		 	try 	
		 		{
		 			StringBuffer query=new StringBuffer();
		 			query.append( " select a.asriCatCode as CATID " );
		 			query.append( " from EhfCaseTherapy a " );
		 			query.append( " where a.caseId = '"+caseId+"' " );
		 			query.append( " and a.activeyn = 'Y' and a.asriCatCode ='S18'  " );
		 			
		 			List<CasesSearchVO> list=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
		 			if(list!=null && list.size()>0)
		 				returnMsg="Y";
		 			
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			return returnMsg;
		 		}
		 	return returnMsg;
	 	}
	
	/***
	  * @author Kalyan
	  * @param Case ID
	  * @return List of PreauthVO
	  * @method Get the case Surgical Details   
	  */
	@Override
	public List<PreauthVO> getcaseSurgertDtls(String caseId)  
		{
			List<PreauthVO> lstSurgerydlts = new ArrayList<PreauthVO>();
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
			//String hospId = "";
			if(ehfCase!=null)
				//hospId=ehfCase.getCaseHospCode();
			try{
				StringBuffer query = new StringBuffer();
		
				/**
				 * Added by Srikalyan for Displaying individual Amounts related to Procedures
				 * incase the Slab Amount is Missing 
				 */
				
				String slabAmt=null;
				if(slabAmt==null || (slabAmt!=null && "".equalsIgnoreCase(slabAmt)))
					{
						query.append ( " select c.amount as amount  " );
						query.append( "  from EhfCase a , EhfPatient b , EhfmSlabPackage c" );
						query.append( "  where a.casePatientNo = b.patientId  " );
						query.append( "  and a.caseId = '"+caseId+"' ");
						query.append( "  and b.slab = c.id.slabType and c.id.nabhFlag = 'N' " );//For AP Default Nabh Condition is N
						List<PreauthVO> amtLst =genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
						
						if(amtLst!=null && amtLst.size()>0 && amtLst.get(0)!=null && amtLst.get(0).getAmount()!=null)
							slabAmt=amtLst.get(0).getAmount();
						else
							slabAmt="0";
						
						query.setLength(0);
					}
				
				
				query.append(" select DISTINCT am.disMainName as asriCatName  ,  pm.id.process as process, am.disMainId  as catId ,cm.icdCatName as catName , cm.id.icdCatCode as  icdCatCode ,");
				query.append(" pm.procName as  procName , pm.id.icdProcCode as icdProcCode, cast(ec.caseTherapyId as string) as seqNo," );
				query.append("  ec.docName as docName,ec.docRegNum as docReg,ec.docQual as docQual,ec.docMobileNo as docMobNo,ec.procUnits as opProcUnits , ec.surgProcUnits as surgProcUnits,ec.toothedUnits as toothedUnits");
				//query.append(" z.title||' '||z.splstName as docName ");
				query.append( " ,pm.hospstayAmt as hospStayAmt , pm.commonCatAmt as commonCatAmt , pm.icdAmt as icdAmt,ec.medcoProcUnits as medcoProcUnits " );
				
				/**
				 * Added by Srikalyan for Displaying individual Amounts related to Procedures 
				 */
				query.append( " , to_char((case   " );
				query.append( "  				when  ec.procUnits = '-1'  then (to_number(pm.icdAmt)+to_number(pm.hospstayAmt)) " );
				query.append( "         		else  (to_number(ec.procUnits)*(to_number(pm.icdAmt) +to_number(pm.hospstayAmt)))  ");
				query.append( "   		   end) + to_number(pm.commonCatAmt) + (pm.noOfDays * to_number("+slabAmt+"))) as amount " );
				
				query.append("  ,ec.splInvRemarks as  investRemarks , cast(pm.noOfDays as string) as days, eh.isActvFlg as activeYN ");
				query.append(" ,pm.hospstayAmt as hospStayAmt , pm.commonCatAmt as  commonCatAmt, pm.icdAmt as icdAmt, ec.ctdProcUnits as ctdProcUnits,ec.chProcUnits as chProcUnits, pm.noOfDays as noOfDays ");
				query.append(" from EhfCaseTherapy ec , EhfmTherapyCatMst cm  , EhfmTherapyProcMst pm , EhfmSpecialities am, ");
				query.append("  EhfmHospCatMst eh, EhfCase ecc ");
				//query.append(" EhfmSplstDctrs z  ");
				query.append(" where ec.caseId ='"+caseId+"' and ec.asriCatCode = cm.id.asriCatCode and ec.icdCatCode = cm.id.icdCatCode ");
				//query.append(" and z.id.regNum=ec.docRegNum ");
				//query.append(" and ec.asriCatCode =pm.id.asriCode ");
				query.append(" and ec.icdProcCode=pm.id.icdProcCode ");
				query.append(" and ec.activeyn = 'Y' ");
				query.append(" and cm.id.asriCatCode = am.disMainId and ecc.caseId= ec.caseId and eh.id.hospId= ecc.caseHospCode ");
				query.append(" and eh.id.catId= ec.asriCatCode ");
				query.append(" and (pm.id.state=ecc.schemeId or ecc.schemeId='1') and pm.id.process in ('IP','DOP') ");
				/*if(hospId!=null && !hospId.equalsIgnoreCase(""))
					query.append(" and z.id.hospId='"+hospId+"' ");*/
				lstSurgerydlts = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
				int count=0;
				for(PreauthVO preauthVO:lstSurgerydlts)
				{
					query = new StringBuffer();
					query.append(" select DISTINCT ei.investigationId as therapyId, ti.investDesc as filename, cast(ei.sno as string) as filePath,cast(ei.attachTotalPath as string) as ipOpFlag ");
					query.append(" from EhfCaseTherapyInvest ei , EhfmTherapyInvest ti , EhfCaseTherapy ec ,EhfCase a where ei.caseId  ='"+caseId+"' and ei.icdProcCode = '"+preauthVO.getIcdProcCode()+"'   ");
					query.append(" and ei.investigationId =ti.id.investigationId  and ei.activeYN = 'Y' and ti.id.ICDProcCode = '"+preauthVO.getIcdProcCode()+"'  ");
					query.append("  and ei.caseId=ec.caseId and ec.asriCatCode=ti.id.asriSpec and ti.id.ICDProcCode=ec.icdProcCode ");
					query.append(" and a.schemeId = ti.id.schemeId and a.caseId = ec.caseId ");
					List<PreauthVO> lstsplInvest = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
					preauthVO.setLstSplInvet(lstsplInvest);
					
					if(preauthVO.getIcdProcCode()!=null && preauthVO.getCatId()!=null
							&& preauthVO.getCatId().equalsIgnoreCase(config.getString("DentalSurgeryID"))
							&& preauthVO.getOpProcUnits()!=null && !"-1".equalsIgnoreCase(preauthVO.getOpProcUnits())
							&& config.getString("frameWorkDentalProcs").contains("~"+preauthVO.getIcdProcCode()+"~"))
						{
							EhfmDentalProcCriteriaPK ehfmDentalProcCriteriaPK =new EhfmDentalProcCriteriaPK();
							ehfmDentalProcCriteriaPK.setIcdProcCode(preauthVO.getIcdProcCode());
							ehfmDentalProcCriteriaPK.setSchemeId(config.getString("Scheme.TG.State"));
							EhfmDentalProcCriteria ehfmDentalProcCriteria =genericDao.findById(EhfmDentalProcCriteria.class, EhfmDentalProcCriteriaPK.class, ehfmDentalProcCriteriaPK);
							
							if(ehfmDentalProcCriteria!=null && ehfmDentalProcCriteria.getFrameworkPrice()!=null  
									&& !"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getFrameworkPrice()) 
									&& ehfmDentalProcCriteria.getSubsequentPrice()!=null
									&& !"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getSubsequentPrice()))
								{
									int opProcUnits=Integer.parseInt(preauthVO.getOpProcUnits());
									int frameWorkPrice=Integer.parseInt(ehfmDentalProcCriteria.getFrameworkPrice());
									int subPrice=Integer.parseInt(ehfmDentalProcCriteria.getSubsequentPrice());
		
									if(Integer.parseInt(preauthVO.getOpProcUnits())>2)
										lstSurgerydlts.get(count).setAmount(Integer.toString((frameWorkPrice*2)+subPrice*(opProcUnits-2)));
									else
										lstSurgerydlts.get(count).setAmount(Integer.toString(opProcUnits*frameWorkPrice));
								}
						}
					count++;
				}
				
			}catch(Exception e)
			{
			e.printStackTrace();	
			}
			return lstSurgerydlts;
		}
	
@Override
public ClaimsFlowVO getUniquePrices(ClaimsFlowVO claimsFlowVO) {
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
	try{
			query.append(" select distinct emt.hospstayAmt as hospStayAmt, emt.commonCatAmt as commonCatAmt, emt.icdAmt as icdAmt,ee.schemeId as scheme, ");
			query.append(" emt.noOfDays as noOfDays, emt.id.asriCode as test, emt.id.icdProcCode as testKnown, emt.procName as ipOpFlag, ect.procUnits as opProcUnits ");
			query.append(" ,emt.id.process as process ");
			query.append(" from EhfmTherapyProcMst emt, EhfCaseTherapy ect,EhfCase ee  ");
			query.append(" where ect.caseId = ? and ect.activeyn = ? and to_char(ect.caseTherapyId) = ? and emt.id.asriCode = ect.asriCatCode  ");
			query.append(" and ee.caseId=ect.caseId and emt.id.state=ee.schemeId ");
			query.append(" and emt.id.icdProcCode = ect.icdProcCode and emt.icdCatCode = ect.icdCatCode order by  emt.id.asriCode ");
	
			String[] args=new String[3];
			args[0]=claimsFlowVO.getCaseId();
			args[1]="Y";
			args[2]=claimsFlowVO.getCaseTherapyId();
	
			List<ClaimsFlowVO> surgList= genericDao.executeHQLQueryList(ClaimsFlowVO.class, query.toString(), args);
	
	
			
			String str="";
	    	List<String> list=new ArrayList<String>();
	    	
	    	if(surgList!=null && surgList.size()>0)
	    	{
	    		for(int i=0; i<surgList.size(); i++)
	    		{
	    			
	    			//Added by Srikalyan for Dental TG Special Conditions
	    			if(surgList.get(i)!=null)
	    				{
	    					if(surgList.get(i).getTestKnown()!=null 
	    							&& surgList.get(i).getTest()!=null 
	    							&& surgList.get(i).getScheme()!=null 
	    							&& claimsFlowVO.getCtdApprvdUnits()!=null 
									&& !"".equalsIgnoreCase(claimsFlowVO.getCtdApprvdUnits())
									&& !"-1".equalsIgnoreCase(claimsFlowVO.getCtdApprvdUnits())
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
	    											int opUnits=Integer.parseInt(claimsFlowVO.getCtdApprvdUnits());
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
	        			if(claimsFlowVO.getCtdApprvdUnits()!=null && !"".equalsIgnoreCase(claimsFlowVO.getCtdApprvdUnits()) && !"-1".equalsIgnoreCase(claimsFlowVO.getCtdApprvdUnits()))
	        				surgAmt= surgAmt+ (Long.parseLong(surgList.get(i).getIcdAmt()) * Long.parseLong(claimsFlowVO.getCtdApprvdUnits()));
	        			else
	        				surgAmt= surgAmt+ Long.parseLong(surgList.get(i).getIcdAmt());
	    			}
	        		//End-Get the sum of all icd pocedure amounts
	        		
	    			if(i==0)
	    			{
	    				if(surgList.get(i).getNoOfDays()!=null)
	    					noOfDays= surgList.get(i).getNoOfDays().longValue();
	    				
	    				if(surgList.get(i).getProcess()!=null && !"".equalsIgnoreCase(surgList.get(i).getProcess()))
	    				{
		    				if(!surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
	    						{
				    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()) && Long.parseLong(claimsFlowVO.getCtdApprvdUnits()) >0)
				    					hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
				    				else
				    					hospStayAmount = Long.parseLong(claimsFlowVO.getCtdApprvdUnits());
	    						}
		    				if(surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
								{
			    					if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
				    					{
			    							hospStayAmountDOP= (Long.parseLong(surgList.get(i).getHospStayAmt()) * Long.parseLong(claimsFlowVO.getCtdApprvdUnits()));
			    							//hospStayAmount = hospStayAmount + hospStayAmountDOP;
				    					}
								}
	    				}
	    				
	    			}
	    			if(i>0)
	    			{
	    				// Get the maximum No of Days
	    				if(surgList.get(i).getNoOfDays()!=null)
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
				    				if(hospStayAmount< Long.parseLong(surgList.get(i).getHospStayAmt()))
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
			    						&& claimsFlowVO.getCtdApprvdUnits()!=null && !"".equalsIgnoreCase(claimsFlowVO.getCtdApprvdUnits()) && !"-1".equalsIgnoreCase(claimsFlowVO.getCtdApprvdUnits()))
				    			{
				    				hospStayAmountDOP = hospStayAmountDOP + (Long.parseLong(surgList.get(i).getHospStayAmt()) * Long.parseLong(claimsFlowVO.getCtdApprvdUnits()));
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
	    	
	    	claimsFlowVO.setTotPackgAmt(totAmt.toString());
	}	
	    	catch(Exception e)
	    	{
	    		LOGGER.error("Error"+ e.getMessage());
				e.printStackTrace();
	    	}
	return claimsFlowVO;
}

@Override
public ClaimsFlowVO getTotalPrice(ClaimsFlowVO claimFlowVO) throws Exception {
	claimFlowVO = claimsFlowDAO.getTotalPrice(claimFlowVO);
	return claimFlowVO;
}

@Override
public String getNabhAmnt(String caseId) {
	StringBuffer query= new StringBuffer();
	List<ClaimsFlowVO> nabhAmnt = null;
	query.append(" select to_char(es.amount) as nabhAmnt from EhfPatient ep, EhfmSlabPackage es, EhfCase ec ");
	query.append(" WHERE ec.id.caseId = '"+caseId+"'  and ec.casePatientNo = ep.id.patientId  and es.id.slabType = ep.slab   and ec.nabhFlg = es.id.nabhFlag ");
	nabhAmnt = genericDao.executeHQLQueryList(ClaimsFlowVO.class, query.toString());
	
	return nabhAmnt.get(0).getNabhAmnt().toString();
}


@Override
public String getTherapyId(String caseId) {
	// TODO Auto-generated method stub
	StringBuffer query = new StringBuffer();
	List<ClaimsFlowVO> procList = null;
	String heamFlag="N";
	try
	{
		query.append("select icd_proc_code as ICDPROCCODE from ehf_case_therapy where case_id='"+caseId+"'");
		procList = genericDao.executeSQLQueryList(ClaimsFlowVO.class,query.toString() );
		if(procList.size()>0)
		{
			for(int i=0;i<procList.size();i++)
			{
				if(procList.get(i).getICDPROCCODE().equalsIgnoreCase(config.getString("Heamodialysis")))
				{
					heamFlag="Y";

				}
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return heamFlag;
}

@Override
public String getorgFlag(String caseId) {

	String orgFlag = "N";
	try{
	EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
	if(ehfCase != null)
	{
		if(ehfCase.getOrganTransYN()!=null && !"".equalsIgnoreCase(ehfCase.getOrganTransYN()))
		{
			orgFlag="Y";
		}
	}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return orgFlag;

}
//Chandana - 8602 - Added new method for getting the list of cases
@Override
public List<LabelBean> getOpdClaimCasesListForACO(HashMap hashMap) {
	List<LabelBean> resultList = new ArrayList<LabelBean>();
	try{
		resultList = claimsFlowDAO.getOpdClaimCasesListForACO(hashMap);
	}catch(Exception le){
		le.printStackTrace();
	}
	return resultList;
}
//Chandana - 8602 - New method for getting the status
@Override
public String getNIMSOPDCaseStat(String caseId) {
	String caseStatus = null;
	String patientId = null;
	try{
		List<EhfOpdPatient> ehfOpdPatient = new ArrayList<EhfOpdPatient>();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("opClaimSeq", caseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		ehfOpdPatient = genericDao.findAllByCriteria(EhfOpdPatient.class, criteriaList);
		if(ehfOpdPatient != null){
			caseStatus = ehfOpdPatient.get(0).getClaimStatus();
			patientId = ehfOpdPatient.get(0).getClaimNo();
		}
	}catch(Exception e) {
			e.printStackTrace();	
		}
	return caseStatus + "~" + patientId;
}
//Chandana - 8602 - Implemented new method
@Override
public ClaimsFlowVO saveOpdClaim(ClaimsFlowVO claimFlowVO) throws Exception {
	try{
		claimFlowVO = claimsFlowDAO.saveOpdClaim(claimFlowVO);
	}catch(Exception e){
		e.printStackTrace();
	}
	return claimFlowVO;
}
//Chandana - 8602 - New method for keep pending the claim case by ACO
@Override
public String pendingOpClaimByACO(HashMap hashMap) {
	String result = null;
	try{
		result = claimsFlowDAO.pendingOpClaimByACO(hashMap);
	}catch(Exception le){
		le.printStackTrace();
	}
	return result;
}
	
}
