package com.ahct.annualCheckUp.dao;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.claims.dao.ClaimsFlowDAOImpl;
import com.ahct.claims.dao.ClaimsPaymentDAO;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.claims.valueobject.TransactionVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.GenerateAsciiFile;
import com.ahct.common.util.TimeStamp;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAcctsTransactionDtls;
import com.ahct.model.EhfAhcAudit;
import com.ahct.model.EhfAhcAuditId;
import com.ahct.model.EhfAhcClaimAccounts;
import com.ahct.model.EhfAhcClaimUploadFile;
import com.ahct.model.EhfAhcTdChklst;
import com.ahct.model.EhfAhcexChklst;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.model.EhfAhcCaseClaim;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfClaimAccounts;
import com.ahct.model.EhfClaimUploadFile;
import com.ahct.model.EhfErroneousClaim;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class AhcClaimsDaoImpl implements AhcClaimsDao{
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
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
	
	ClaimsPaymentDAO claimsPaymentDao;

	public ClaimsPaymentDAO getClaimsPaymentDao() {
		return claimsPaymentDao;
	}

	public void setClaimsPaymentDao(ClaimsPaymentDAO claimsPaymentDao) {
		this.claimsPaymentDao = claimsPaymentDao;
	}
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
	private static final Logger logger = Logger
	.getLogger(ClaimsFlowDAOImpl.class);
	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/** The sdf. */
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String getNextStatus(ClaimsFlowVO claimFlowVO){
		EhfmWorkflowStatusId id= new EhfmWorkflowStatusId();
		id.setActionDone(claimFlowVO.getActionDone());
		id.setCurrStatus(claimFlowVO.getCurrStatus());
		id.setCurrRole(claimFlowVO.getRoleId());
		EhfmWorkflowStatus status=genericDao.findById(EhfmWorkflowStatus.class, EhfmWorkflowStatusId.class, id);
		if(status!=null)
			return status.getNextStatus();
		else
			return null;
	}
	
	@Override
	public String saveClaimDtls(ClaimsFlowVO claimFlowVO) {
		// TODO Auto-generated method stub
		String msg=null;
		try{
			
		EhfAnnualCaseDtls caseDtls=genericDao.findById(EhfAnnualCaseDtls.class, String.class, claimFlowVO.getCaseId());
		EhfAhcCaseClaim ehfAhcCaseClaim =genericDao.findById(EhfAhcCaseClaim.class, String.class, claimFlowVO.getCaseId());
		if(ehfAhcCaseClaim==null)
			{
				ehfAhcCaseClaim=new EhfAhcCaseClaim();
				ehfAhcCaseClaim.setAhcId(claimFlowVO.getCaseId());
				ehfAhcCaseClaim.setCrtDt(new Date());
				ehfAhcCaseClaim.setCrtUsr(claimFlowVO.getUserId());
			}
		else
			{
				if(claimFlowVO.getMedcoCTDUpdRemarks()!=null)
					{
						ehfAhcCaseClaim.setCtdUpdRemarks(claimFlowVO.getMedcoCTDUpdRemarks());
						claimFlowVO.setRemarks(claimFlowVO.getMedcoCTDUpdRemarks());
					}
				if(claimFlowVO.getMedcoCHUpdRemarks()!=null)
					{
						ehfAhcCaseClaim.setChUpdRemarks(claimFlowVO.getMedcoCHUpdRemarks());
						claimFlowVO.setRemarks(claimFlowVO.getMedcoCHUpdRemarks());
					}
				
				ehfAhcCaseClaim.setLstUpdDt(new Date());
				ehfAhcCaseClaim.setLstUpdUsr(claimFlowVO.getUserId());
			}
		caseDtls.setAhcStatus(getNextStatus(claimFlowVO));
		if(claimFlowVO.getCurrStatus()!=null && claimFlowVO.getCurrStatus().equalsIgnoreCase(config.getString("AHC-MEDCO-SCR_CMPL"))){
		caseDtls.setAhcClmSubDt(new Date());
		
		caseDtls.setBillDate(sdf.parse(claimFlowVO.getBillDt()));
		caseDtls.setClaimInitAmount(claimFlowVO.getBillAmt());
		caseDtls.setTotPckgAmt(claimFlowVO.getPackAmt());
		
		//String str=claimFlowVO.getTotalClaim().replaceAll(",","");
		//BigDecimal bd=new BigDecimal(str);
		//caseDtls.setPckAppvAmt(bd);
		caseDtls.setClaimInitRemarks(claimFlowVO.getAcoRemark());
		
		String str=claimFlowVO.getBillAmt().replaceAll(",","");
		BigDecimal bd=new BigDecimal(str);
		ehfAhcCaseClaim.setClaimBillAmt(bd);
		ehfAhcCaseClaim.setClaimBillDate(sdf.parse(claimFlowVO.getBillDt()));
		ehfAhcCaseClaim.setMedcoRemarks(claimFlowVO.getAcoRemark());
		
		}
		else if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-CH"))){
			String str=claimFlowVO.getFinalApproveAmt().replaceAll(",","");
			BigDecimal bd=new BigDecimal(str);
			caseDtls.setPckAppvAmt(bd);
			ehfAhcCaseClaim.setChAprvAmt(bd);
			ehfAhcCaseClaim.setChRemarks(claimFlowVO.getRemarks());
			
		}
		else if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-NAM"))){
			ehfAhcCaseClaim.setNamAprvAmt(ehfAhcCaseClaim.getClaimBillAmt());
			ehfAhcCaseClaim.setNamRemarks(claimFlowVO.getRemarks());
		}
		caseDtls.setLstUpdDt(new Date());
		caseDtls.setLstUpdUsr(claimFlowVO.getUserId());
		Long lActOrder = 0L;
		String args[] = new String[1];
		StringBuffer lQueryBuffer = new StringBuffer();
		lQueryBuffer.append(" select max(au.id.actOrder) as COUNT from EhfAhcAudit au where au.id.ahcId=? ");
		args[0] = claimFlowVO.getCaseId();
		List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
				ClaimsFlowVO.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()
				&& actOrderList.get(0).getCOUNT() != null) {
			if (actOrderList.get(0).getCOUNT().longValue() >= 0)
				lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
		}
		if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-EX"))){
			ehfAhcCaseClaim.setCexAprvAmt(ehfAhcCaseClaim.getClaimBillAmt());
			ehfAhcCaseClaim.setCexRemarks(claimFlowVO.getRemarks());
			
			EhfAhcexChklst exeChklist= genericDao.findById(EhfAhcexChklst.class, String.class, claimFlowVO.getCaseId());
			if(exeChklist==null){
				exeChklist= new EhfAhcexChklst();
				exeChklist.setCrtDt(new Date());
				exeChklist.setCrtUsr(claimFlowVO.getUserId());
			
			}
			else{
				exeChklist.setLstUpdDt(new Date());
				exeChklist.setLstUpdUsr(claimFlowVO.getUserId());
			}
			exeChklist.setCexRemarks(claimFlowVO.getRemarks());
			exeChklist.setAcquaintanceAttached(claimFlowVO.getAcquaintanceAttached());
			exeChklist.setAcquaintanceRemarks(claimFlowVO.getAcquaintanceRemarks());
			exeChklist.setAhcId(claimFlowVO.getCaseId());
			exeChklist.setPhotoMatching(claimFlowVO.getPhotoMatch());
			exeChklist.setPhotomatchRemarks(claimFlowVO.getPhotoMatchRemarks());
			exeChklist.setPhotoYn(claimFlowVO.getPhotoAttached());
			exeChklist.setPhotoRemarks(claimFlowVO.getPhotoRemarks());
			exeChklist.setReportsAttached(claimFlowVO.getReportsAttached());
			exeChklist.setReportsRemarks(claimFlowVO.getReportsRemarks());
			genericDao.save(exeChklist);
			
		}
		else if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-TD"))){
			
			
			EhfAhcTdChklst tdChklist= genericDao.findById(EhfAhcTdChklst.class, String.class, claimFlowVO.getCaseId());
			if(tdChklist==null){
				tdChklist= new EhfAhcTdChklst();
				tdChklist.setCrtDt(new Date());
				tdChklist.setCrtUsr(claimFlowVO.getUserId());
			
			}
			else{
				tdChklist.setLstUpdDt(new Date());
				tdChklist.setLstUpdUsr(claimFlowVO.getUserId());
			}
			tdChklist.setAhcId(claimFlowVO.getCaseId());
			tdChklist.setExeRmksVerified(claimFlowVO.getExeRemarksVerified());
			tdChklist.setExeRmksRemarks(claimFlowVO.getExeVerifyRemarks());
			tdChklist.setFinalApproveAmt(claimFlowVO.getFinalApproveAmt());
			tdChklist.setRemarks(claimFlowVO.getRemarks());
			if(claimFlowVO.getFinalApproveAmt()!=null && !"".equalsIgnoreCase(claimFlowVO.getFinalApproveAmt())){
			String str=claimFlowVO.getFinalApproveAmt().replaceAll(",","");
			BigDecimal bd=new BigDecimal(str);
			ehfAhcCaseClaim.setCtdAprvAmt(bd);
			ehfAhcCaseClaim.setCtdRemarks(claimFlowVO.getRemarks());
			//caseDtls.setPckAppvAmt(bd);
			}
			genericDao.save(tdChklist);
			
		}
		else if(claimFlowVO.getRoleId()!=null && claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-ACO"))){
			ehfAhcCaseClaim.setAcoAprvAmt(ehfAhcCaseClaim.getChAprvAmt());
			ehfAhcCaseClaim.setAcoRemarks(claimFlowVO.getRemarks());
			ehfAhcCaseClaim.setTotClaimAmt(ehfAhcCaseClaim.getChAprvAmt());
		}
		
		EhfAhcAudit audit= new EhfAhcAudit();
		audit.setActBy(claimFlowVO.getUserId());
		audit.setActId(caseDtls.getAhcStatus());
		if(claimFlowVO.getCurrStatus()!=null && claimFlowVO.getCurrStatus().equalsIgnoreCase(config.getString("AHC-MEDCO-SCR_CMPL"))){
		audit.setApprvAmt(Double.valueOf(claimFlowVO.getTotalClaim()));
		audit.setRemarks(claimFlowVO.getAcoRemark());
		}
		else{
			audit.setRemarks(claimFlowVO.getRemarks());
		}
		audit.setCrtDt(new Date());
		audit.setCrtUsr(claimFlowVO.getUserId());
		audit.setUserRole(claimFlowVO.getRoleId());
		
		if(claimFlowVO.getRoleId()!=null && ((claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-TD")))|| (claimFlowVO.getRoleId().equalsIgnoreCase(config.getString("Group.AHC-CH"))))){
		audit.setApprvAmt(Double.valueOf(claimFlowVO.getFinalApproveAmt()));
		}
		EhfAhcAuditId asritAuditPK = new EhfAhcAuditId(lActOrder,
				claimFlowVO.getCaseId());
		audit.setId(asritAuditPK);
		
		EhfAnnualCaseDtls res =genericDao.save(caseDtls);
		if(res!=null){
			genericDao.save(audit);
		}
		
		genericDao.save(ehfAhcCaseClaim);
		if(claimFlowVO.getCurrStatus()!=null && claimFlowVO.getCurrStatus().equalsIgnoreCase(config.getString("AHC-MEDCO-SCR_CMPL"))){
			msg="AHC Claim Details initiated successfully";
		}
		else{
			msg="AHC Claim Details updated successfully";
		}
		}
		catch(Exception e){
			e.printStackTrace();
			msg="Failed to update AHC Claim Details";
			
		}
		return msg;
	}
	@Override
	public String updateClaimDtlsforPayment(ClaimsFlowVO claimFlowVO) {
		String msg=null;
		try{
			for(String ahcId : claimFlowVO.getCaseSelected()){
				EhfAnnualCaseDtls caseDtls=genericDao.findById(EhfAnnualCaseDtls.class, String.class, ahcId);
				caseDtls.setAhcStatus(getNextStatus(claimFlowVO));
				caseDtls.setLstUpdDt(new Date());
				caseDtls.setLstUpdUsr(claimFlowVO.getUserId());
				genericDao.save(caseDtls);
			}
				msg=ClaimsConstants.MSG_1;
			
		}
		catch(Exception e){
			e.printStackTrace();
			msg=ClaimsConstants.MSG_0;
		}
		return msg;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void generateAHcFile(){
		String[] stateArray = new String[]{"CD201","CD202"};
		
		HashMap hParamMap = new HashMap();
		hParamMap.put("CRTUSER", config.getString("AHC_EO_ADMIN_USERID"));
		hParamMap.put("SharePath", config.getString("mapPath"));
		hParamMap.put("SentStatus", ClaimsConstants.SENT);
		hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
		//hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
		
	    for(int stateCount=0;stateCount<stateArray.length;stateCount++){
	    hParamMap.put("SCHEME_ID", stateArray[stateCount]);
	    
		String paymentType = "";String[] caseSelected = null;
		ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
		claimFlowVO.setRoleId(ClaimsConstants.CEO_GRP_ID);
		claimFlowVO.setUserId(config.getString("AHC_EO_ADMIN_USERID"));
		claimFlowVO.setActionDone(ClaimsConstants.PAYNOW_BUTTON);
		
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		/*Starts for normal cases which are in claim ready status*/
		criteriaList.add(new GenericDAOQueryCriteria("ahcStatus", ClaimsConstants.AHC_CLAIM_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("ahcId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EhfAnnualCaseDtls> ahcCaseLst = genericDao.findAllByCriteria(EhfAnnualCaseDtls.class, criteriaList);
	    
	    if(ahcCaseLst!=null && ahcCaseLst.size()>0){
	    	 hParamMap.put("FormPaymentType", ClaimsConstants.AHC_CLAIM_FORM_PAYMENT); 
	    	 hParamMap.put("caseStatus", ClaimsConstants.AHC_CLAIM_READY_BANK); 
	    	 hParamMap.put("PaymentType", paymentType);
	    	 hParamMap.put("sentAgain", "N");
	    	 
	    	 int listCount = ahcCaseLst.size()/50;
	    	 int loopSize = ahcCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ahcCaseLst.get(listTrackerCount).getAhcId();
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
	     	        	caseSelected[i]=ahcCaseLst.get(listTrackerCount).getAhcId();
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
	    ahcCaseLst = new ArrayList<EhfAnnualCaseDtls>();
		criteriaList.add(new GenericDAOQueryCriteria("ahcStatus", ClaimsConstants.AHC_CLAIM_REJ_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("ahcId",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		ahcCaseLst = genericDao.findAllByCriteria(EhfAnnualCaseDtls.class, criteriaList);
	    if(ahcCaseLst!=null && ahcCaseLst.size()>0){
	    	hParamMap.put("FormPaymentType", ClaimsConstants.AHC_CLAIM_FORM_PAYMENT); 
	    	 hParamMap.put("caseStatus", ClaimsConstants.AHC_CLAIM_REJ_READY_BANK); 
	    	 hParamMap.put("PaymentType", paymentType);
	    	 hParamMap.put("sentAgain", "Y");
	    	 
	    	 int listCount = ahcCaseLst.size()/50;
	    	 int loopSize = ahcCaseLst.size();int listTrackerCount=0; 
	         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
	        	 if(loopSize<=50){
	        		 caseSelected = new String[loopSize];
	        	            for(int i=0;i<loopSize;i++){
	        	        	caseSelected[i]=ahcCaseLst.get(listTrackerCount).getAhcId();
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
	     	        	caseSelected[i]=ahcCaseLst.get(listTrackerCount).getAhcId();
	     	        	listTrackerCount++;
	     	        	}
	        		    claimFlowVO.setCaseSelected(caseSelected);
	        	    	hParamMap.put("claimFlowVO", claimFlowVO);
	        	    	//updating claim Status while paying
	        	    	updateClaimStatus(hParamMap);
	        	    	loopSize=loopSize-50;
	        	 } } }
	    /*Ends for normal cases which are in claim ready status(REJ)*/
	  //  generateERRFile(hParamMap,claimFlowVO);
	  //  generateFollowUpFile(hParamMap,claimFlowVO);
	}
	
}
	
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
			claimFlowVO.setCurrStatus((String) lparamMap.get("caseStatus"));
			lStrNextStatus = getNextStatus(claimFlowVO);

			lparamMap.put("nextCaseStatus", lStrNextStatus);
			claimFlowVO.setCurrStatus((String) lparamMap.get("caseStatus"));
			claimFlowVO.setNextStatus((String) lparamMap.get("nextCaseStatus"));

			lparamMap.put("currentDate",
					new java.sql.Timestamp(new Date().getTime()));
            //updating payment check flag in ehf_case and calculating tds/rf/hosp amount
			
		
                //getting hospital/rf account details
				lContentList = getHospAccountDtls(lparamMap);

				if (lContentList.size() > 0) {
					//generating ascii file 
					lFileMap = gaf.generateAsciiFile(lContentList, lparamMap);
					lFlag = Integer.parseInt((String) lFileMap.get("Flag"));

					if (lContentList.size() > 2) {
						lFileList = (ArrayList) lContentList.get(0);
						lCaseIdLst = (ArrayList) lContentList.get(2);
					}
				}

				if (lFlag > 0) {
					int CaseIdLst = lCaseIdLst.size();
					for (int j = 0; j < CaseIdLst; j++) {
						
						lparamMap.put("AHC_ID", (String) lCaseIdLst.get(j));

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
							
							
							lStrCaseId = (String) lCaseIdLst.get(j);

					
								isInsert = ChangeClaimStatus(lparamMap); // for
								// normal payment
								if (isInsert) {
									lResult = ClaimsConstants.ONE;
								} else {
									lResult = ClaimsConstants.ZERO;
								}
							

						}

					}
					if (isInsert) {
						//saving file in two folders
						resultList = insertUploadFile(lparamMap);
						iUploadStatus = Integer.parseInt(resultList.get(0)
								.toString());
					}

					lResult = ClaimsConstants.ONE;
				} else {
					lResult = ClaimsConstants.ZERO;
				}
			
			lparamMap.put("Message", lResult);
			lparamMap.put("UploadStatus", Integer.toString(iUploadStatus));

		} catch (Exception e) {
			lResult = ClaimsConstants.TWO;
			iUploadStatus = 0;
			System.out.println("Error occured in updateClaimStatus() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lparamMap;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public boolean updatePaymentCheckValue(HashMap lparamMap) throws Exception {

		String lStrCaseId = ClaimsConstants.EMPTY;
		String lStrStatus = ClaimsConstants.EMPTY;
		
		String failedCaseIdInList = ClaimsConstants.EMPTY;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		boolean isInsert = ClaimsConstants.BOOLEAN_FALSE;
		String paymentType = (String) lparamMap.get("PaymentType");
		EhfCase ehfCase = null;
	
		boolean failedIsInsert = ClaimsConstants.BOOLEAN_FALSE;

		for (String cases : claimFlowVO.getCaseSelected()) {
			try {
				lStrCaseId = cases;
				lparamMap.put("CASE_ID", lStrCaseId);
				isInsert = ClaimsConstants.BOOLEAN_FALSE;

				if (lparamMap.get("caseStatus") != null)
					lStrStatus = (String) lparamMap.get("caseStatus"); // 030

				
				if (ClaimsConstants.CLAIM_READY_REJ_BANK
								.equalsIgnoreCase(lStrStatus)) {
					{
						ehfCase = genericDao.findById(EhfCase.class,
								String.class, lStrCaseId);
						ehfCase.setPaymentCheck(ClaimsConstants.T);
						ehfCase = genericDao.save(ehfCase);
						if (ehfCase.getPaymentCheck() != null
								&& (ClaimsConstants.T).equalsIgnoreCase(ehfCase
										.getPaymentCheck())) {
							isInsert = ClaimsConstants.BOOLEAN_TRUE;
						}
					}
				} else {
					failedCaseIdInList = failedCaseIdInList + lStrCaseId + ",";
					System.out.println("failed cases are in catch block************"
							+ failedCaseIdInList);
					lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				}
			} catch (Exception ex) {
				failedIsInsert = ClaimsConstants.BOOLEAN_TRUE;
				failedCaseIdInList = failedCaseIdInList + lStrCaseId + ",";
				System.out.println("failed cases are in catch block************"
						+ failedCaseIdInList);
				lparamMap.put("failedCaseIdInList", failedCaseIdInList);
				lparamMap.put("failedIsInsert", failedIsInsert);
				ex.printStackTrace();
			}
		}
		return isInsert;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList getHospAccountDtls(HashMap lparamMap) {
		List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
		ArrayList lResList = new ArrayList();
		List lFileDataList = new ArrayList();
		List lCaseList = new ArrayList();
		
		double lFlagVal = ClaimsConstants.ZERO_DBL_VAL;
		Map lFileMap = new HashMap();
		int lTotalCases = 0;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		String lStrCaseStatus = (String) lparamMap.get("nextCaseStatus");
		String lStrOldCaseStatus = (String) lparamMap.get("caseStatus");
		StringBuffer query = new StringBuffer();
		String paymentType = (String) lparamMap.get("PaymentType");
		String args[] = new String[claimFlowVO.getCaseSelected().length];
		for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
			args[i] = claimFlowVO.getCaseSelected()[i];
		}
		try {

			query.append(" select distinct b.hospCity as hospAdd,e.bankId as bankId,d.bankBranch as bankBranch,d.bankName as bankName,d.ifcCode as ifscCode,d.bankCategory as bankCategory,");
			query.append(" e.accountNo as accNo,e.hospAccName as hospAccName,e.hospId as hospId,b.hospActiveYN||'' as hospActiveYN,b.hospType as HOSPTYPE ");
			query.append(" ,a.ahcId as caseId,a.pckAppvAmt||'' as totalClaim,a.schemeId as schemeType ");
			query.append(" from EhfAnnualCaseDtls a,EhfmHospitals b,EhfmBankMaster d,EhfmHospBankDtls e ");
			query.append(" where b.hospId = e.hospId and d.bankId = e.bankId");

				query.append("  and a.ahcId in ( ");
				for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
					query.append(" ? ");
					if (i == claimFlowVO.getCaseSelected().length - 1)
						query.append(" ) ");
					else
						query.append(" , ");
				}
			
			query.append(" and a.ahcHospCode = b.hospId ");
            
			lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					query.toString(), args);

			for (ClaimsFlowVO claimsVO : lstWorkList) {
                //hospital acconuts details for that case
				lFileMap = new HashMap();
				lFileMap.put("UNIQUE_ID", claimsVO.getCaseId());

				lCaseList.add(claimsVO.getCaseId());
				lFileMap.put("BENEFICIARY_ACCOUNT_NAME",
						claimsVO.getHospAccName());
				lFileMap.put("BENEFICIARY_ID", claimsVO.getHospId());
				lFileMap.put("BENEFICIARY_ADDR", claimsVO.getHospAdd());
				lFileMap.put("BENEFICIARY_BANK_NAME", claimsVO.getBankName());
				lFileMap.put("BANK_BRANCH", claimsVO.getBankBranch());
				lFileMap.put("BENEFICIARY_ACCOUNT_NO", claimsVO.getAccNo());
				lFileMap.put("TRANSACTION_AMOUNT", claimsVO.getTotalClaim());
				lFileMap.put("TOTAL_CLAIM_AMOUNT", claimsVO.getTotalClaim());
				lFileMap.put("HOSP_TYPE",claimsVO.getHOSPTYPE()+"");
				lFileMap.put("HOSP_ID", claimsVO.getHospId());
				lFileMap.put("TRANSACTION_TYPE", claimsVO.getBankCategory());
				lFileMap.put("BENEFICIARY_BANK_ID", claimsVO.getBankId());
				lFileMap.put("BENEFICIARY_BANK_IFC_CODE",claimsVO.getIfscCode());
				lFileMap.put("SCHEME_ID", claimsVO.getSchemeType());
				
		
					
					if(claimsVO.getSchemeType()!=null && claimsVO.getSchemeType().equalsIgnoreCase(config.getString("TG")))
						{lFileMap.put("CLAINT_AC_NUMBER",
								ClaimsConstants.AHC_CLAIM_CLIENT_TG_ACCNO);
						lFileMap.put("CLAINT_AC_CODE",
								ClaimsConstants.AHC_CLAIM_CLIENT_TG_CODE);
						}
						else
						{
							lFileMap.put("CLAINT_AC_NUMBER",
									ClaimsConstants.AHC_CLAIM_CLIENT_AP_ACCNO);
							lFileMap.put("CLAINT_AC_CODE",
									ClaimsConstants.AHC_CLAIM_CLIENT_AP_CODE);
						
						}
				

				

				lFileDataList.add(lFileMap);
                //getting RF acconts details
				
				lTotalCases++;
			}
			lResList.add(lFileDataList);
			//sequence for claim_upload table
			String smsNextVal = getSequence("AHCCLAIMUPLOADFILE");

			lResList.add(smsNextVal);
			lResList.add(lCaseList);
		} catch (Exception e) {
			System.out.println("Error occured in getHospAccountDtls() in ClaimsFlowDAOImpl class."
					+ e.getMessage());
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
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean ChangeClaimStatus(HashMap lparamMap) throws Exception {
		System.out.println("Start:ChangeClaimStatus method in ClaimsflowDaoimpl");
		Long lActOrder = 0L;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];String amount="0";String ahcId = "";
		boolean isInsert = false;
		EhfAhcClaimAccounts ehfAhcClaimAccounts=null;
		ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
		String hosp_id=(String) lparamMap.get("ACC_HOSP_ID");
		String hosp_type=(String) lparamMap.get("ACC_HOSP_TYPE");
		amount = lparamMap.get("ACC_TRANSACTION_AMOUNT").toString();
		ahcId = (String) lparamMap.get("AHC_ID");
		String totalAmt =  lparamMap.get("ACC_TOTAL_AMOUNT").toString();
		
		try {
			
			TransactionVO transactionVO = new TransactionVO();
			transactionVO.setCaseId(ahcId);
			transactionVO.setTdsRfId(ahcId);
			transactionVO.setHospitalId(hosp_id);
			transactionVO.setGrossAmount(totalAmt);
			transactionVO.setNetAmount(amount);
			
			transactionVO.setHospitalType(hosp_type); 
			transactionVO.setScheme((String) lparamMap.get("SCHEME_ID"));
			
			EhfAnnualCaseDtls caseDtls= null;
			caseDtls = genericDao.findById(EhfAnnualCaseDtls.class, String.class,
					(String) lparamMap.get("AHC_ID"));
			caseDtls.setAhcStatus((String) lparamMap.get("nextCaseStatus"));
			caseDtls.setFileName((String) lparamMap.get("FileName"));
			caseDtls.setPaymentCheck(ClaimsConstants.F);
			caseDtls.setPaymentSentDate(new Timestamp(new Date().getTime()));
			caseDtls.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			caseDtls.setLstUpdUsr(claimFlowVO.getUserId());
			caseDtls = genericDao.save(caseDtls);
			if (caseDtls != null && caseDtls.getFileName() != null) {

				ehfAhcClaimAccounts = genericDao.findById(EhfAhcClaimAccounts.class,
						String.class, (String) lparamMap.get("AHC_ID"));
				if(ehfAhcClaimAccounts==null){
					ehfAhcClaimAccounts=new EhfAhcClaimAccounts();
					ehfAhcClaimAccounts.setAhcId((String) lparamMap.get("AHC_ID"));
				}
				ehfAhcClaimAccounts.setTransStatus(ClaimsConstants.SENT);
				ehfAhcClaimAccounts.setTimeMilSec((long) 0);
				ehfAhcClaimAccounts.setPayeeName((String) lparamMap.get("CLAINT_AC_CODE"));
				
				ehfAhcClaimAccounts
						.setSrcAcctNo((String) lparamMap.get("SRC_ACCT_NO"));
				ehfAhcClaimAccounts
						.setDesAcctNo((String) lparamMap.get("DES_ACCT_NO"));
				if(amount!=null || !"".equalsIgnoreCase(amount))
					ehfAhcClaimAccounts.setAprvdAmt(Long.valueOf(amount));
				ehfAhcClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date()
						.getTime()));
				ehfAhcClaimAccounts.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
				
				ehfAhcClaimAccounts.setCrtUsr(claimFlowVO.getUserId());
				ehfAhcClaimAccounts = genericDao.save(ehfAhcClaimAccounts);
			}
			if (ehfAhcClaimAccounts.getSrcAcctNo() != null) {
			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfAhcAudit au where au.id.ahcId=? ");
			args[0] = (String) lparamMap.get("AHC_ID");
			List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
					ClaimsFlowVO.class, lQueryBuffer.toString(), args);
			if (actOrderList != null && !actOrderList.isEmpty()
					&& actOrderList.get(0).getCOUNT() != null) {
				if (actOrderList.get(0).getCOUNT().longValue() >= 0)
					lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
			

			//inserting into accounts table
			if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("N")) 
			claimsPaymentDao.submitClaimPaymentsInAccounts(transactionVO);
			//else if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("Y"))
			//claimsPaymentDao.submitClaimPaymentsInAccountsForRej(transactionVO);
			
			// insert into asrit_audit
			EhfAhcAudit ahcAudit = new EhfAhcAudit();
			String lStrAhcId = (String) lparamMap.get("AHC_ID");
			
			EhfAhcAuditId asritAuditPK = new EhfAhcAuditId(lActOrder, lStrAhcId);
			ahcAudit.setId(asritAuditPK);
			ahcAudit.setActId(claimFlowVO.getNextStatus());
			ahcAudit.setActBy(claimFlowVO.getUserId());
			ahcAudit.setCrtUsr(claimFlowVO.getUserId());
			ahcAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			ahcAudit.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
			if((String) lparamMap.get("FileName")!=null)
				ahcAudit.setFilename((String) lparamMap.get("FileName"));
			// asritAudit.setApprvAmt((double) 0);
			ahcAudit.setUserRole(claimFlowVO.getRoleId());

			genericDao.save(ahcAudit);
			isInsert = true;
			}
		}
		}
		catch (Exception e) {
			isInsert = ClaimsConstants.BOOLEAN_FALSE;
			System.out.println("FALIURE:updateClaimAccountDetails method in ClaimsflowDaoimpl");
			e.printStackTrace();
		}
		System.out.println("End:ChangeClaimStatus method in Claimsflowpaymentdaoimpl");
		return isInsert;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ArrayList insertUploadFile(HashMap lparamMap) throws Exception {
		byte[] lFileBytes = null;
		boolean fileCreated = false;
		String lStrFileName = ClaimsConstants.EMPTY;
		String lStrCrtUsr = ClaimsConstants.EMPTY;
		String lStrFilePath = ClaimsConstants.EMPTY;
		String lStrSharePath = ClaimsConstants.EMPTY;
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

			EhfAhcClaimUploadFile ehfAhcClaimUploadFile = null;

			ehfAhcClaimUploadFile = genericDao.findById(EhfAhcClaimUploadFile.class,
					String.class, lStrFileName);
			if (ehfAhcClaimUploadFile != null) {
				System.out.println("Already data avaiblabe with same filename in EHF_AHC_CLAIM_UPLOAD_FILE table");
				System.out
						.println("Already data avaiblabe with same filename in EHF_AHC_CLAIM_UPLOAD_FILE table");
			} else {
				ehfAhcClaimUploadFile = new EhfAhcClaimUploadFile();
				ehfAhcClaimUploadFile.setFileName(lStrFileName);
				ehfAhcClaimUploadFile.setCrtUser(lStrCrtUsr);
				ehfAhcClaimUploadFile.setFilePath(lStrAbsolutePath);
				ehfAhcClaimUploadFile.setCrtDate(new Timestamp(new Date()
						.getTime()));
				ehfAhcClaimUploadFile.setFileStatus((String) lparamMap
						.get("SentStatus"));
				ehfAhcClaimUploadFile.setSetId(lSet_id);
				ehfAhcClaimUploadFile = genericDao.save(ehfAhcClaimUploadFile);
			}
			liResult = 0;
			if (ehfAhcClaimUploadFile != null) {
				liResult = Integer.parseInt("1");
			}
			if (liResult == 1) {
				liResult = 0;
				fileCreated = insertFileInTemp(lStrFileName, lFileBytes);
				if (fileCreated == true) {
					liResult = 1;
				}
			}
		}
		resultList.add(0, Integer.toString(liResult));
		return resultList;
	}
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
				.append(" select nvl(max(setId),0)+1 as MAXSETID from EhfAhcClaimUploadFile where fileStatus=? ");

		String arg[] = new String[1];
		arg[0] = (String) lparamMap.get("SentStatus");
		maxSetList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
				lStrBuffer.toString(), arg);
		if (maxSetList.size() > 0)
			lSet_id = Integer.parseInt(maxSetList.get(0).getMAXSETID()
					.toString());

		return lSet_id;
	}

	
	/// Reading response Files methods start///
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateClaimStatusSentByBank() {
		HashMap lparamMap = new HashMap();
		lparamMap.put("SentStatus", ClaimsConstants.SENT);
		lparamMap.put("RejectedCases", "");
		lparamMap.put(
				"SharePath",
				config.getString("mapPath")
						+ config.getString("claimPayment_filePath"));
		lparamMap.put("ClaimsRecievedPath",
				config.getString("localRecievedPath"));
		// for CaseClaims Payment
		lparamMap.put("ClamsInProgerss", ClaimsConstants.AHC_CLAIM_SENT);
		lparamMap.put("ClamSettled", ClaimsConstants.AHC_CLAIM_PAID);
		lparamMap.put("ClaimFailed", ClaimsConstants.AHC_CLAIM_REJ_BANK);
		lparamMap.put("AcknowledgmentRcvd", ClaimsConstants.AHC_CLAIM_ACK_REC);
		lparamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
		lparamMap.put("TransPaidStatus", ClaimsConstants.TransPaidStatus);		
		lparamMap.put("TransAckStatus", ClaimsConstants.TRANSACKSTATUS);
		lparamMap.put("TransRejStatus", ClaimsConstants.TRANSREJSTATUS);
		
				
		lparamMap.put("ClaimPaidRemarks", ClaimsConstants.CLAIM_PAY_DONE);
		lparamMap.put("ClaimAckRemarks", ClaimsConstants.CLAIM_ACKNOWLEDGED);
		lparamMap.put("REMARKS", "");
		lparamMap.put("CRTUSER",ClaimsConstants.CEO_USER_ID);
		// 007 Erroneous claim payment
		
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
					if (ClaimsConstants.CLAIM_FILE_FLAG.equalsIgnoreCase(fileFlag)) {
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
		}

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
            if (!OrgFileName.substring(0, 4).toUpperCase().equals("EHSP")){
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

			BufferedInputStream bis = null;
			FileInputStream fis = new FileInputStream(new File(destFilePath));
			bis = new BufferedInputStream(fis);

			DataInputStream data_in = new DataInputStream(bis);
			lDatalist = readContents(data_in);
			if (lDatalist.size() > 0) {
				lContentList = (ArrayList) lDatalist.get(0);
				iFlag = Integer.parseInt((String) lDatalist.get(1));
				Count = Integer.parseInt((String) lDatalist.get(2));
				lparamMap.put("FileConut", Integer.toString(iFlag));// to Change
																	// the File
																	// Status
				lparamMap.put("FileName", OrgFileName);// File Name Used to
														// update the File
														// Status
			}

			if (Count > 0 && lContentList.size() > 0) {
				lparamMap.put("DataList", lContentList);
               
					lStrResult = SetClaimStatus(lparamMap);
				
			}
			}
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
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Transactional
	public String SetClaimStatus(HashMap lparamMap) {
		String lResult = "False";
		ArrayList lDataList = new ArrayList();
		int i = 0, lFlag = 0, iStatus = 0, fileStatusConut = 0;
		String lStrAhcid = "",lStrClaimAmt="", lStrACNo = "", lStrStatus = "", lStrTransDt = "", lStrTransId = "", lStrPaymentUid = null;
		String lStrClaimPreStatus = "",lStrClaimStatus = "", lStrRemarks = "", lStrRembsStatus = "", lStrPaidDate = null, lStrRejCode = null;
		String rejectedCases=(String) lparamMap.get("RejectedCases");
		try {
			lDataList = (ArrayList) lparamMap.get("DataList");
			lStrClaimPreStatus = (String) lparamMap.get("ClamsInProgerss");
			fileStatusConut = Integer.parseInt((String) lparamMap
					.get("FileConut"));
            //Reading file content
			int DataList = lDataList.size();
			for (int j = 0; j < DataList; j = j + 17) 
			{
				lFlag = 0;
				lStrClaimAmt = (String) lDataList.get(j + 3);
				lStrTransDt = (String) lDataList.get(j + 5);
				lStrAhcid = (String) lDataList.get(j + 6);
				lStrTransId = (String) lDataList.get(j + 15);
				lStrPaymentUid = (String) lDataList.get(j + 10);
				lStrStatus = (String) lDataList.get(j + 11);
				lStrACNo = (String) lDataList.get(j + 9);
				lStrRemarks = (String) lDataList.get(j + 12);
				lStrPaidDate = (String) lDataList.get(j + 14); 
				lStrRejCode = (String) lDataList.get(j + 16); 
				
				if (lStrPaidDate.trim().length() == 0) {
					lStrPaidDate = null;
				}

				lparamMap.put("TRANS_ID", lStrTransId);
				lparamMap.put("TRANS_DT", lStrTransDt);
				lparamMap.put("AHC_ID", lStrAhcid);
				lparamMap.put("SBH_PAID_DATE", lStrPaidDate);
				lparamMap.put("REJ_CODE", lStrRejCode);
				lparamMap.put("PAYMENT_UID", lStrPaymentUid);
                //For Claim Paid Status
				if (lStrStatus.equals("E")) 
				{   //TDS
				
						//For RF and Hospital Amt
						lStrClaimStatus = (String) lparamMap.get("ClamSettled");
						lStrRembsStatus = (String) lparamMap
								.get("RembsClamPaid"); 
					
					lStrRemarks = (String) lparamMap.get("ClaimPaidRemarks");
					//For Claim Rejected By Bank
				} else if (lStrStatus.equals("R")) // 035
				{   //TDS
					rejectedCases = rejectedCases+lStrAhcid+" , ";
			
						lStrClaimStatus = (String) lparamMap.get("ClaimFailed");
						lStrRembsStatus = (String) lparamMap
								.get("RembsClaimRej");
					
				} else if (lStrStatus.equals("A"))  //For Acknowledge Status
				{   
					lStrClaimStatus = (String) lparamMap
								.get("AcknowledgmentRcvd");
					lStrRembsStatus = (String) lparamMap
								.get("RembsAcknowledgmentRcvd");
					
					lStrRemarks = (String) lparamMap.get("ClaimAckRemarks");
				} else {
					lFlag = 1;

				}
				
				lparamMap.put("REMARKS", lStrRemarks);
				lparamMap.put("TransStatus", lStrStatus);
				lparamMap.put("STAT_ID", lStrClaimStatus);
				lparamMap.put("CASE_FOLLOWUP_ID", lStrAhcid);
				lparamMap.put("CASE_TYPE", "REV_FUND");
				lparamMap.put("RejectedCases",rejectedCases);
				
				if (lFlag != 1) {
					iStatus = 0;
					
					if (lStrTransDt.trim().length() > 1 && lStrTransDt != null) { 
																					
						
					
							iStatus = updatePaymentDetails(lparamMap);        //Updating Claim Details acc to Status  
							if (iStatus > 0 && lStrStatus.equals("R"))
								updateAccountsTransactionNew(lStrAhcid,lStrClaimAmt,         //Reverting details in Accounts tables iF Rejected
										lStrTransDt);
						

					}

					if (iStatus > 0) {
						StringBuffer lStrBuffer = new StringBuffer();

						EhfAnnualCaseDtls ehfAhcCase = genericDao.findById(EhfAnnualCaseDtls.class,
								String.class, lStrAhcid);
						ehfAhcCase.setLstUpdDt(new Timestamp(new Date().getTime()));
						ehfAhcCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
						ehfAhcCase.setAhcStatus(lStrClaimStatus);
						ehfAhcCase = genericDao.save(ehfAhcCase);

						if (ehfAhcCase != null) {
							setAhcAudit(lparamMap);
							lResult = ClaimsConstants.TRUE;
						} else
							lResult = ClaimsConstants.FALSE;
					} 
					else{
						lResult = ClaimsConstants.FALSE;
					}
					
				}

			}

			if (ClaimsConstants.TRUE.equalsIgnoreCase(lResult)) {
				EhfAhcClaimUploadFile ehfAhcClaimUploadFile = null;
				ehfAhcClaimUploadFile = genericDao.findById(
						EhfAhcClaimUploadFile.class, String.class,
						(String) lparamMap.get("FileName"));
				if (ehfAhcClaimUploadFile.getFileStatus() != null
						&& ehfAhcClaimUploadFile.getFileStatus().equalsIgnoreCase(
								(String) lparamMap.get("SentStatus"))) {
					ehfAhcClaimUploadFile.setLstUpdDate(new Timestamp(new Date()
							.getTime()));
					ehfAhcClaimUploadFile.setFileStatus((String) lparamMap
							.get("ClosedStatus"));
				}
				ehfAhcClaimUploadFile = genericDao.save(ehfAhcClaimUploadFile);
			}
		 
			//IF Email required
			if (config.getBoolean("EmailRequired")) {
				if (lparamMap.get("RejectedCases") != null && lparamMap.get("RejectedCases") !="" ) {
					
					String msgEmailTo= config.getString("claimRejectedCaseEmail");
		    		StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
		    		while (token.hasMoreElements()) {
		    			String mailId=(String) token.nextElement();
		    			String[] emailToArray = { mailId };
		    			EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						emailVO.setTemplateName(config
							 	.getString("EhfRejectedCasePayment"));
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("rejectedCasesPayment"));
						Map<String, String> model = new HashMap<String, String>();
						model.put("caseNo",
								(String) lparamMap.get("RejectedCases"));
						//generating Email
						commonService.generateMail(emailVO, model);
		    		}
			}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error:SetClaimStatus method in AhcClaimsDAOImpl with message "+e);
		}

		return lResult;
	} 
	@SuppressWarnings("rawtypes")
	@Transactional
	public int updatePaymentDetails(HashMap lparamMap) throws Exception {
		int iResult = 0;
		
		String lStrStatus = (String) lparamMap.get("TransStatus");
		long timeMillSec = 0;
		String lStrAhcid = "";
		
		EhfAhcClaimAccounts ehfAhcClaimAcc = null;
		try {
			List<String> colValues = new ArrayList<String>();
			TimeStamp t = new TimeStamp();
			timeMillSec = t.getTimeStampFromDate((String) lparamMap
					.get("TRANS_DT"));
			
			lStrAhcid = (String) lparamMap.get("AHC_ID");

			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("ahcId", lStrAhcid,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("timeMilSec",
					timeMillSec, GenericDAOQueryCriteria.CriteriaOperator.LT));
			if (lStrStatus.equals("A")) {
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.equals("E")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));
			} else if (lStrStatus.equals("R")) {
				colValues.add((String) lparamMap.get("TransAckStatus"));
				colValues.add((String) lparamMap.get("SentStatus"));
				colValues.add((String) lparamMap.get("TransPaidStatus"));
				colValues.add((String) lparamMap.get("TransRejStatus"));
				criteriaList
						.add(new GenericDAOQueryCriteria("transStatus",
								colValues,
								GenericDAOQueryCriteria.CriteriaOperator.IN));

			}
			List<EhfAhcClaimAccounts> ehfAhcClaimAccount = genericDao
					.findAllByCriteria(EhfAhcClaimAccounts.class, criteriaList);
			if (ehfAhcClaimAccount.size() > 0) {
				ehfAhcClaimAcc = ehfAhcClaimAccount.get(0);
				ehfAhcClaimAcc
						.setTransactionId((String) lparamMap.get("TRANS_ID"));
				if ((String) lparamMap.get("TRANS_DT") != null)
					ehfAhcClaimAcc.setTransactionDt(df.parse((String) lparamMap
							.get("TRANS_DT")));
				ehfAhcClaimAcc.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
				ehfAhcClaimAcc.setLstUpdDt(new Timestamp(new Date().getTime()));
				ehfAhcClaimAcc.setRemarks((String) lparamMap.get("REMARKS"));
				ehfAhcClaimAcc.setTimeMilSec(timeMillSec);
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					ehfAhcClaimAcc.setSbhPaidDate(sdf.parse((String) lparamMap
							.get("SBH_PAID_DATE")));

				if (lStrStatus.equals("A")) {
					ehfAhcClaimAcc.setTransStatus((String) lparamMap
							.get("TransAckStatus"));
				} else if (lStrStatus.equals("E")) {
					ehfAhcClaimAcc.setTransStatus((String) lparamMap
							.get("TransPaidStatus"));
				} else if (lStrStatus.equals("R")) {
					ehfAhcClaimAcc.setTransStatus((String) lparamMap
							.get("TransRejStatus"));
				}
				ehfAhcClaimAcc = genericDao.save(ehfAhcClaimAcc);

				if (ehfAhcClaimAcc != null) {

					EhfAnnualCaseDtls ehfAnnualCase = genericDao.findById(EhfAnnualCaseDtls.class,
							String.class, lStrAhcid);
					ehfAnnualCase.setLstUpdDt(new Timestamp(new Date().getTime()));
					ehfAnnualCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
					ehfAnnualCase.setCsTransId((String) lparamMap.get("TRANS_ID"));
					ehfAnnualCase.setAhcStatus((String) lparamMap.get("STAT_ID"));
					if ((String) lparamMap.get("TRANS_DT") != null)
						ehfAnnualCase.setCsTransDt(df.parse((String) lparamMap
								.get("TRANS_DT")));
					if ((String) lparamMap.get("SBH_PAID_DATE") != null)
						ehfAnnualCase.setCsSbhDt(sdf.parse((String) lparamMap
								.get("SBH_PAID_DATE")));
					ehfAnnualCase.setCsRemarks((String) lparamMap.get("REMARKS"));
					ehfAnnualCase = genericDao.save(ehfAnnualCase);
					if (ehfAnnualCase != null)
						iResult = 1;
				}
			}
		} catch (Exception e) {
			logger.error("Error:updatePaymentDetails method in ClaimsPaymentDAOImpl with message "+e);
		}
		return iResult;

	}
	@SuppressWarnings("rawtypes")
	@Transactional
	public void setAhcAudit(HashMap lparamMap) throws Exception {
		String lStrCaseId = (String) lparamMap.get("CASE_ID");
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		Long lActOrder = 0L;
		try {
			lQueryBuffer
					.append(" select max(au.id.actOrder) as COUNT from EhfAhcAudit au where au.id.caseId=? ");
			args[0] = lStrCaseId;
			List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
					ClaimsFlowVO.class, lQueryBuffer.toString(), args);
			if (actOrderList != null && !actOrderList.isEmpty()
					&& actOrderList.get(0).getCOUNT() != null) {
				if (actOrderList.get(0).getCOUNT().longValue() >= 0)
					lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
			}

			// insert into ehf_audit
			EhfAhcAudit ahcAudit = new EhfAhcAudit();
			EhfAhcAuditId ahcAuditPK = new EhfAhcAuditId(lActOrder, lStrCaseId);
			ahcAudit.setId(ahcAuditPK);
			ahcAudit.setActId((String) lparamMap.get("STAT_ID"));
			ahcAudit.setActBy((String) lparamMap.get("UPD_USR"));
			ahcAudit.setCrtUsr((String) lparamMap.get("UPD_USR"));
			ahcAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
			ahcAudit.setRemarks((String) lparamMap.get("REMARKS"));
			ahcAudit.setUserRole(ClaimsConstants.CEO_GRP_ID);
			ahcAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				ahcAudit.setSbhPaidDate(sdf.parse((String) lparamMap
						.get("SBH_PAID_DATE")));
			ahcAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
			ahcAudit.setRejCode((String) lparamMap.get("REJ_CODE"));

			genericDao.save(ahcAudit);
		} catch (Exception e) {
			logger.error("Error:setCaseAudit method in ClaimsPaymentDAOImpl with message "+e);
		}
	}
	
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

			if (ehfAccountTrans != null && ehfAccountTrans.size() > 0) {
				EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = ehfAccountTrans
						.get(0);
				if (lStrTransDt != null)
					ehfAcctsTransactionDtlsEntry.setTransDate(df
							.parse(lStrTransDt));
				ehfAcctsTransactionDtlsEntry.setLstUpdDt(new Date());
				ehfAcctsTransactionDtlsEntry.setLstUpdUsr(config
						.getString("ACC.TCS"));
				ehfAcctsTransactionDtlsEntry = genericDao
						.save(ehfAcctsTransactionDtlsEntry);
			} else {

				String lStrSchemeId = "";
				String caseId = lStrCaseid;
				

				EhfAnnualCaseDtls ehfCase = genericDao.findById(EhfAnnualCaseDtls.class,
						String.class, caseId);
				if (ehfCase != null)
					lStrSchemeId = ehfCase.getSchemeId();

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
					ehfAcctsTransactionDtlsEntry.setDebtAccount(config
							.getString("ACC.MAINTG-2316"));
				else
					ehfAcctsTransactionDtlsEntry.setDebtAccount(config
							.getString("ACC.MAINAP-2305"));
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
					ehfAcctsTransactionDtlsEntry.setScheme(config
							.getString("ACC.EHFTGSchemeID"));
				else
					ehfAcctsTransactionDtlsEntry.setScheme(config
							.getString("ACC.EHF"));
				ehfAcctsTransactionDtlsEntry.setSection("");
				ehfAcctsTransactionDtlsEntry.setCaseId(lStrCaseid);
				ehfAcctsTransactionDtlsEntry.setActive_yn(ClaimsConstants.R);
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
				String uniqueTxn = getSequence("ACC_UNIQUE_TXN_SEQ");
				ehfAcctsTransactionDtlsEntry.setUniqueTxn(uniqueTxn);
				ehfAcctsTransactionDtlsEntry = genericDao
						.save(ehfAcctsTransactionDtlsEntry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

}
