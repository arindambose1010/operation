package com.ahct.chronicOp.dao;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.chronicOp.vo.ChronicOPVO;
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
import com.ahct.model.EhfChronicAudit;
import com.ahct.model.EhfChronicAuditPK;
import com.ahct.model.EhfChronicClaimAccount;
import com.ahct.model.EhfChronicClaimUploadFile;
import com.ahct.model.EhfChronicCaseDtl;
import com.ahct.model.EhfChronicCaseDtlPK;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfChronicDrugClaimAcct;
import com.ahct.model.EhfChronicDrugClaimAcctPK;
import com.ahct.model.EhfErroneousClaim;
import com.ahct.model.EhfmDrugVendorBankDtl;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


public class chronicOpClaimsDaoImpl implements chronicOpClaimsDao {
	
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

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void generateChronicFile(){
			System.out.println( "The Scheduler to Generate  Chronic-OP Claims files started in TS");
			String[] stateArray = new String[]{"CD201"};
			
			HashMap hParamMap = new HashMap();
		
			hParamMap.put("SharePath", config.getString("mapPath"));
			hParamMap.put("SentStatus", ClaimsConstants.SENT);
			hParamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
			//hParamMap.put("TDS_CASE_TYPE", config.getString("EHF.Claims.Trust"));
			
		    for(int stateCount=0;stateCount<stateArray.length;stateCount++){
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
	
			/*claimFlowVO.setRoleId(ClaimsConstants.EO_ADMIN_GRP_ID);
			claimFlowVO.setUserId(config.getString("CHRONIC_EO_ADMIN_USERID"));*/
			claimFlowVO.setActionDone(ClaimsConstants.PAYNOW_BUTTON);
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			/*Starts for normal cases which are in claim ready status*/
			criteriaList.add(new GenericDAOQueryCriteria("chronicStatus", ClaimsConstants.CHRONIC_CLAIM_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("crtDt",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
			criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfChronicCaseDtl> chronicCaseLst = genericDao.findAllByCriteria(EhfChronicCaseDtl.class, criteriaList);
		    
		    if(chronicCaseLst!=null && chronicCaseLst.size()>0){
		    	 hParamMap.put("FormPaymentType", ClaimsConstants.CHRONIC_CLAIM_FORM_PAYMENT); 
		    	 hParamMap.put("caseStatus", ClaimsConstants.CHRONIC_CLAIM_READY_BANK); 
		    	 hParamMap.put("PaymentType", paymentType);
		    	 hParamMap.put("sentAgain", "N");
		    	 
		    	 int listCount = chronicCaseLst.size()/50;
		    	 int loopSize = chronicCaseLst.size();int listTrackerCount=0; 
		         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
		        	 if(loopSize<=50){
		        		 caseSelected = new String[loopSize];
		        	            for(int i=0;i<loopSize;i++){
		        	        	caseSelected[i]=chronicCaseLst.get(listTrackerCount).getId().getChronicNo();
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
		     	        	caseSelected[i]=chronicCaseLst.get(listTrackerCount).getId().getChronicNo();
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
		    chronicCaseLst = new ArrayList<EhfChronicCaseDtl>();
			criteriaList.add(new GenericDAOQueryCriteria("chronicStatus", ClaimsConstants.CHRONIC_CLAIM_REJ_READY_BANK,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("crtDt",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
			criteriaList.add(new GenericDAOQueryCriteria("schemeId", stateArray[stateCount],GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			chronicCaseLst = genericDao.findAllByCriteria(EhfChronicCaseDtl.class, criteriaList);
		    if(chronicCaseLst!=null && chronicCaseLst.size()>0){
		    	hParamMap.put("FormPaymentType", ClaimsConstants.CHRONIC_CLAIM_FORM_PAYMENT); 
		    	 hParamMap.put("caseStatus", ClaimsConstants.CHRONIC_CLAIM_REJ_READY_BANK); 
		    	 hParamMap.put("PaymentType", paymentType);
		    	 hParamMap.put("sentAgain", "Y");		    	 
		    	 int listCount = chronicCaseLst.size()/50;
		    	 int loopSize = chronicCaseLst.size();int listTrackerCount=0; 
		         for(int loopCounter=0;loopCounter <= listCount;loopCounter++){
		        	 if(loopSize<=50){
		        		 caseSelected = new String[loopSize];
		        	            for(int i=0;i<loopSize;i++){
		        	        	caseSelected[i]=chronicCaseLst.get(listTrackerCount).getId().getChronicNo();
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
		     	        	caseSelected[i]=chronicCaseLst.get(listTrackerCount).getId().getChronicNo();
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
			System.out.println( "The Scheduler to Generate  Chronic-OP Claims files ended in TS");
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
			String[] failedCases = new String[100];
			ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
			
			try {
				claimFlowVO.setCurrStatus((String) lparamMap.get("caseStatus"));
				lStrNextStatus = getNextStatus(claimFlowVO);

				lparamMap.put("nextCaseStatus", lStrNextStatus);
				claimFlowVO.setCurrStatus((String) lparamMap.get("caseStatus"));
				claimFlowVO.setNextStatus((String) lparamMap.get("nextCaseStatus"));

				lparamMap.put("currentDate",
						new java.sql.Timestamp(new Date().getTime()));
	            
				
			
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
					
					
					
					
					
					/*Added by venkatesh for blocking failed case status updation*/
					
					String failedCaseList=(String) lparamMap.get("failedCaseIdInList");
					if(failedCaseList!=null && failedCaseList.length()>0)
					{
						if(failedCaseList.contains(","))
						failedCases=failedCaseList.split(",");
						else
						failedCases[0]=failedCaseList;	
						 String paymentType=(String) lparamMap.get("PaymentType");
						for(int k=0;k<failedCases.length;k++)
						{
							 boolean isUpdate=false;
							 if(failedCases[k]!=null && !("").equalsIgnoreCase(failedCases[k]))
							 {
							isUpdate=updateFailedCaseStatus(failedCases[k].trim(),paymentType);
							if(!isUpdate)
							{
								logger.error("failed to update case status of failed claim with case id : "+failedCases[k]);
							}
							 }
						}
						
						
					}

					/*end of code for  failed case status updation*/
					

					if (lFlag > 0) {
						int CaseIdLst = lCaseIdLst.size();
						int caseCount=0;
						for (int j = 0; j < CaseIdLst; j++) {
							
							lparamMap.put("CHRONIC_NO", (String) lCaseIdLst.get(j));

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
								lparamMap.put("ACC_DRUG_AMOUNT", (String) lContentMap
										.get("DRUG_AMOUNT"));
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
										caseCount++;
									} 
								
									
									
							}

						}
						if(caseCount>0 && caseCount<=lCaseIdLst.size())
						{
							lResult = ClaimsConstants.ONE;
						}
						else	
						{
							lResult = ClaimsConstants.ZERO;	
						}
						if (lResult!=null && lResult.equalsIgnoreCase(ClaimsConstants.ONE)) {
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
			String scheme=(String)lparamMap.get("SCHEME_ID");
			String args[] = new String[claimFlowVO.getCaseSelected().length];
			for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
				args[i] = claimFlowVO.getCaseSelected()[i];
			}
			try {

				query.append(" select distinct b.hospCity as hospAdd,e.bankId as bankId,d.bankBranch as bankBranch,d.bankName as bankName,d.ifcCode as ifscCode,d.bankCategory as bankCategory,");
				query.append(" e.accountNo as accNo,e.hospAccName as hospAccName,e.id.hospId as hospId,b.hospActiveYN||'' as hospActiveYN,b.hospType as HOSPTYPE ");
				query.append(" ,a.id.chronicNo as caseId,a.pckAppvAmt||'' as totalClaim,a.totDrugAmount||'' as drugAmount,a.schemeId as schemeType ");
				query.append(" from EhfChronicCaseDtl a,EhfmHospitals b,EhfmBankMaster d,EhfmHospBankDtls e ");
				query.append(" where b.hospId = e.id.hospId and d.bankId = e.bankId and e.id.scheme='"+scheme+"' ");

					query.append("  and a.id.chronicNo in ( ");
					for (int i = 0; i < claimFlowVO.getCaseSelected().length; i++) {
						query.append(" ? ");
						if (i == claimFlowVO.getCaseSelected().length - 1)
							query.append(" ) ");
						else
							query.append(" , ");
					}
				
				query.append(" and a.hospCode = b.hospId ");
	            
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
					lFileMap.put("DRUG_AMOUNT", claimsVO.getDrugAmount());
					lFileMap.put("TOTAL_CLAIM_AMOUNT", claimsVO.getTotalClaim());
					lFileMap.put("HOSP_TYPE",claimsVO.getHOSPTYPE()+"");
					lFileMap.put("HOSP_ID", claimsVO.getHospId());
					lFileMap.put("TRANSACTION_TYPE", claimsVO.getBankCategory());
					lFileMap.put("BENEFICIARY_BANK_ID", claimsVO.getBankId());
					lFileMap.put("BENEFICIARY_BANK_IFC_CODE",claimsVO.getIfscCode());
					lFileMap.put("SCHEME_ID", claimsVO.getSchemeType());
					
			
						
						if(claimsVO.getSchemeType()!=null && claimsVO.getSchemeType().equalsIgnoreCase(config.getString("TG")))
							{lFileMap.put("CLAINT_AC_NUMBER",
									ClaimsConstants.CLAIM_CLIENT_TG_ACCNO);
							lFileMap.put("CLAINT_AC_CODE",
									ClaimsConstants.CHRONIC_CLIENT_TG_CODE);
							}
							else
							{
								lFileMap.put("CLAINT_AC_NUMBER",
										ClaimsConstants.CLAIM_CLIENT_AP_ACCNO);
								lFileMap.put("CLAINT_AC_CODE",
										ClaimsConstants.CHRONIC_CLIENT_AP_CODE);
							
							}
						lFileDataList.add(lFileMap);
                       if(claimsVO.getDrugAmount()!=null )
                    	   
                       {
                    	 if( Long.valueOf(claimsVO.getDrugAmount()) >0)
                    	 {
                    	 HashMap lresultMap = new HashMap();
                    	 HashMap ldataMap = new HashMap();
     					 
                    	 ldataMap.put("DrugAmount", claimsVO.getDrugAmount());
     					 lFileMap.put("CaseStatus", lStrCaseStatus);
     					 lFileMap.put("OldCaseStatus", lStrOldCaseStatus);
     					 lFileMap.put("PaymentType",paymentType);
     					 lFileMap.put("CaseList", lCaseList);
     					 lFileMap.put("FileDataList", lFileDataList);
     					 ldataMap.put("FileMap", lFileMap);
                    	 
     					 lresultMap=payChronicDrug(ldataMap);
                    	 if(lresultMap!=null && !lresultMap.isEmpty())
                    	 {
                    	 lCaseList = (ArrayList) lresultMap.get("CaseList");
     					 lFileDataList = (ArrayList) lresultMap.get("FileDataList");
                    	 }
                    	 
                    	   
                       }
					

					
	                //getting RF acconts details
					
					lTotalCases++;
				}}
				lResList.add(lFileDataList);
				//sequence for claim_upload table
				String smsNextVal = getSequence("CHRONICCLAIMUPLOADFILE");

				lResList.add(smsNextVal);
				lResList.add(lCaseList);
			} catch (Exception e) {
				System.out.println("Error occured in getHospAccountDtls() in ClaimsFlowDAOImpl class."
						+ e.getMessage());
				e.printStackTrace();
			}
			return lResList;
		}
		
		
		
		public HashMap payChronicDrug(HashMap lmap) throws Exception {
			HashMap lResultMap=new HashMap();
			HashMap lFileMap=null;
			ArrayList caseIdList=null;
			ArrayList lFileDataList=null;
			
			String lbeneficiary_account_name = ClaimsConstants.EMPTY, lbeneficiary_id = ClaimsConstants.EMPTY, lbeneficiary_addr = ClaimsConstants.EMPTY, lbeneficiary_bank_name = ClaimsConstants.EMPTY;
			String lbank_branch = ClaimsConstants.EMPTY, lbeneficiary_account_no = ClaimsConstants.EMPTY, lbeneficiary_bank_id = ClaimsConstants.EMPTY, lbeneficiary_bank_ifc_code = ClaimsConstants.EMPTY;
			String lclaint_ac_code = ClaimsConstants.EMPTY, lclaint_ac_number = ClaimsConstants.EMPTY, ltransaction_type = ClaimsConstants.EMPTY, caseStatus = ClaimsConstants.EMPTY, paymentType = ClaimsConstants.EMPTY, actType = ClaimsConstants.EMPTY, eMail = ClaimsConstants.EMPTY;
			
			HashMap lDataMap=lmap;
			lFileMap=(HashMap) lmap.get("FileMap");
			
			String caseId=null;String drugAmt=null;String uniqueId=null;String schemeId=null;
			
			caseId=(String) lFileMap.get("UNIQUE_ID");
			drugAmt=(String) lFileMap.get("DRUG_AMOUNT");
			schemeId=(String) lFileMap.get("SCHEME_ID");
			caseIdList=(ArrayList) lFileMap.get("CaseList");
			lFileDataList=(ArrayList) lFileMap.get("FileDataList");
			uniqueId=caseId+"/DRUG";
			try
			{
			if(schemeId!=null && schemeId.equalsIgnoreCase(config.getString("AP")))
			{
				
			   
			   
			   List<ChronicOPVO> accountList=new ArrayList<ChronicOPVO>();
			   StringBuffer query=new StringBuffer();
			   query.append( " select a.benfAccountName as benfActName,a.benficiaryId as benficiaryId,a.benficiaryAddress as benfAddress, ");
			   query.append( " b.bankName as bankName,b.bankBranch as bankBranch,a.accountNo as accountNum,b.bankId as bankId, " );
			   query.append( " b.ifcCode as ifscCode,b.bankCategory as bankCategory ");
			   query.append(" from EhfmDrugVendorBankDtl a,EhfmBankMaster b where a.schemeId='"+schemeId+"' and a.bankId=b.bankId ");
			   accountList=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString());
				
			   if(accountList!=null && accountList.size()>0)
			   {
				    
				   
				   
				   
				   
				   /*added for saving drug payment file details*/ 
				   EhfChronicDrugClaimAcct ehfChronicDrugClaimAcct=new EhfChronicDrugClaimAcct();

				   ehfChronicDrugClaimAcct.setChronicDrugId(uniqueId);
				   ehfChronicDrugClaimAcct.setChronicNo(caseId);
					ehfChronicDrugClaimAcct.setTransStatus(ClaimsConstants.TransReadyStatus);
					ehfChronicDrugClaimAcct.setTimeMilSec(BigDecimal.ZERO);
					if(drugAmt!=null || !"".equalsIgnoreCase(drugAmt))
						ehfChronicDrugClaimAcct.setDrugAmt(BigDecimal.valueOf(Long.valueOf(drugAmt)));
					ehfChronicDrugClaimAcct.setCrtDt(new java.sql.Timestamp(new Date()
							.getTime()));
					ehfChronicDrugClaimAcct.setRemarks(ClaimsConstants.CLAIM_READY_RMK);
					
					ehfChronicDrugClaimAcct.setCrtUsr((String)lmap.get("CRTUSER"));
					genericDao.save(ehfChronicDrugClaimAcct);
				   
				    
				   
				   
				   
				   
				   
				   
				   lbeneficiary_account_name = accountList.get(0).getBenfActName();
					lbeneficiary_id = accountList.get(0).getBenficiaryId();
					lbeneficiary_addr = accountList.get(0).getBenfAddress();
					lbeneficiary_bank_name = accountList.get(0).getBankName();
					lbank_branch = accountList.get(0).getBankBranch();
					lbeneficiary_account_no = accountList.get(0).getAccountNum();
					lbeneficiary_bank_id = accountList.get(0).getBankId();
					lbeneficiary_bank_ifc_code = accountList.get(0).getIfscCode();
					lclaint_ac_code = (String) lFileMap.get("CLAINT_AC_CODE");
					lclaint_ac_number = (String) lFileMap.get("CLAINT_AC_NUMBER");
					ltransaction_type = accountList.get(0).getBankCategory();
				
				
					lFileMap = new HashMap();
					lFileMap.put("UNIQUE_ID", uniqueId);
					caseIdList.add(lFileMap.get("UNIQUE_ID"));
					
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
							drugAmt);
					lFileMap.put("TOTAL_CLAIM_AMOUNT", drugAmt);
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
					
					lFileDataList.add(lFileMap);
				
					lResultMap.put("FileDataList",lFileDataList);
					lResultMap.put("CaseList",caseIdList);
				
				
				
			   }
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("error occured in  payChronicDrug() method in chronicOpClaimsDaoImpl() Class**");
			}
			
			
		
			//logger.info("End of payChronicDrug() method in chronicOpClaimsDaoImpl() Class**");
			
			
			
			
			return lResultMap;
		
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
			String args[] = new String[1];String amount="0";String chronicNo = "";String drugAmount="0";
			boolean isInsert = false;
			EhfChronicClaimAccount ehfChronicClaimAccounts=null;
			EhfChronicDrugClaimAcct ehfChronicDrugClaimAcct=null;
			ClaimsFlowVO claimFlowVO = (ClaimsFlowVO) lparamMap.get("claimFlowVO");
			String hosp_id=(String) lparamMap.get("ACC_HOSP_ID");
			String hosp_type=(String) lparamMap.get("ACC_HOSP_TYPE");
			amount = (String)lparamMap.get("ACC_TRANSACTION_AMOUNT");
			drugAmount=(String) lparamMap.get("ACC_DRUG_AMOUNT");
			chronicNo = (String) lparamMap.get("CHRONIC_NO");
			String totalAmt =  lparamMap.get("ACC_TOTAL_AMOUNT").toString();
			
			try {
				
				TransactionVO transactionVO = new TransactionVO();
				transactionVO.setCaseId(chronicNo);
				transactionVO.setTdsRfId(chronicNo);
				transactionVO.setHospitalId(hosp_id);
				transactionVO.setGrossAmount(totalAmt);
				transactionVO.setNetAmount(amount);
				transactionVO.setDrugAmount(drugAmount);
				transactionVO.setHospitalType(hosp_type); 
				transactionVO.setScheme((String) lparamMap.get("SCHEME_ID"));
				transactionVO.setCrtUser((String)lparamMap.get("CRTUSER"));
				
				if(chronicNo!=null && !chronicNo.contains("/DRUG"))
				{
				String chronicId=chronicNo.substring(0, chronicNo.length()-2);
				System.out.println("chronic id is : "+chronicId);
				EhfChronicCaseDtl caseDtls= null;
				EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
				
				
				caseDtls = genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class,
						ehfChronicCaseDtlPK);
				caseDtls.setChronicStatus((String) lparamMap.get("nextCaseStatus"));
				caseDtls.setFileName((String) lparamMap.get("FileName"));
				caseDtls.setPaymentCheck(ClaimsConstants.F);
				caseDtls.setPaymentSentDate(new Timestamp(new Date().getTime()));
				caseDtls.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
				caseDtls.setLstUpdUsr(claimFlowVO.getUserId());
				caseDtls = genericDao.save(caseDtls);
				if (caseDtls != null && caseDtls.getFileName() != null) {

					ehfChronicClaimAccounts = genericDao.findById(EhfChronicClaimAccount.class,String.class, chronicNo);
					if(ehfChronicClaimAccounts==null){
						ehfChronicClaimAccounts=new EhfChronicClaimAccount();
						ehfChronicClaimAccounts.setChronicNo((String) lparamMap.get("CHRONIC_NO"));
					}
					ehfChronicClaimAccounts.setTransStatus(ClaimsConstants.SENT);
					ehfChronicClaimAccounts.setTimeMilSec(0L);
					ehfChronicClaimAccounts.setPayeeName((String) lparamMap.get("CLAINT_AC_CODE"));
					
					ehfChronicClaimAccounts
							.setSrcAcctNo((String) lparamMap.get("SRC_ACCT_NO"));
					ehfChronicClaimAccounts
							.setDesAcctNo((String) lparamMap.get("DES_ACCT_NO"));
					if(amount!=null || !"".equalsIgnoreCase(amount))
						ehfChronicClaimAccounts.setAprvdAmt(BigDecimal.valueOf(Long.valueOf(amount)));
					ehfChronicClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date()
							.getTime()));
					ehfChronicClaimAccounts.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
					
					ehfChronicClaimAccounts.setCrtUsr(claimFlowVO.getUserId());
					genericDao.save(ehfChronicClaimAccounts);
					
					
					
					
					
				}
				if (ehfChronicClaimAccounts.getSrcAcctNo() != null) {
				lQueryBuffer
						.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");
				args[0] = (String) lparamMap.get("CHRONIC_NO");
				List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
						ClaimsFlowVO.class, lQueryBuffer.toString(), args);
				if (actOrderList != null && !actOrderList.isEmpty()
						&& actOrderList.get(0).getCOUNT() != null) {
					if (actOrderList.get(0).getCOUNT().longValue() >= 0)
						lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
				

				//inserting into accounts table
				if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("N"))
					
					transactionVO=claimsPaymentDao.submitChronicClaimPaymentsInAccounts(transactionVO);
				//else if(((String)lparamMap.get("sentAgain"))!=null && ((String)lparamMap.get("sentAgain")).equalsIgnoreCase("Y"))
				//claimsPaymentDao.submitClaimPaymentsInAccountsForRej(transactionVO);
				
				// insert into ehf_chronic_audit
				EhfChronicAudit ehfChronicAudit = new EhfChronicAudit();
				EhfChronicAuditPK ehfChronicAuditPK = new EhfChronicAuditPK(chronicId,chronicNo,lActOrder);
				ehfChronicAudit.setId(ehfChronicAuditPK);
				ehfChronicAudit.setActId(claimFlowVO.getNextStatus());
				ehfChronicAudit.setActBy(claimFlowVO.getUserId());
				ehfChronicAudit.setCrtUsr(claimFlowVO.getUserId());
				ehfChronicAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				ehfChronicAudit.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
				if((String) lparamMap.get("FileName")!=null)
					ehfChronicAudit.setFileName((String) lparamMap.get("FileName"));
				ehfChronicAudit.setApprvAmt(0);
				ehfChronicAudit.setUserRole(claimFlowVO.getRoleId());
				ehfChronicAudit.setLangId("en_US");

				genericDao.save(ehfChronicAudit);
				isInsert = true;
				}
			}}
				
				
				
				/*added for saving drug payment file details*/
				if(chronicNo!=null && chronicNo.contains("/DRUG"))
				{
				ehfChronicDrugClaimAcct = genericDao.findById(EhfChronicDrugClaimAcct.class,
						String.class,chronicNo );
				if(ehfChronicDrugClaimAcct==null){
					ehfChronicDrugClaimAcct=new EhfChronicDrugClaimAcct();
					ehfChronicDrugClaimAcct.setChronicDrugId(chronicNo);
				}
				ehfChronicDrugClaimAcct.setTransStatus(ClaimsConstants.SENT);
				ehfChronicDrugClaimAcct.setTimeMilSec(BigDecimal.ZERO);
				ehfChronicDrugClaimAcct.setPayeeName((String) lparamMap.get("CLAINT_AC_CODE"));
				
				ehfChronicDrugClaimAcct
						.setSrcAcctNo((String) lparamMap.get("SRC_ACCT_NO"));
				ehfChronicDrugClaimAcct
						.setDesAcctNo((String) lparamMap.get("DES_ACCT_NO"));
				if(amount!=null || !"".equalsIgnoreCase(amount))
					ehfChronicDrugClaimAcct.setDrugAmt(BigDecimal.valueOf(Long.valueOf(amount)));
					
				ehfChronicDrugClaimAcct.setCrtDt(new java.sql.Timestamp(new Date()
						.getTime()));
				ehfChronicDrugClaimAcct.setRemarks(ClaimsConstants.CLAIM_SENT_BANK_RMK);
				
				ehfChronicDrugClaimAcct.setCrtUsr(claimFlowVO.getUserId());
				genericDao.save(ehfChronicDrugClaimAcct);
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
			String schemeId=ClaimsConstants.EMPTY;

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
	        //saving into upload claim Folder
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

				EhfChronicClaimUploadFile ehfChronicClaimUploadFile = new EhfChronicClaimUploadFile();

				ehfChronicClaimUploadFile = genericDao.findById(EhfChronicClaimUploadFile.class,
						String.class, lStrFileName);
				if (ehfChronicClaimUploadFile != null) {
					System.out.println("Already data avaiblabe with same filename in EHF_CHRONIC_CLAIM_UPLOAD_FILE table");
					System.out
							.println("Already data avaiblabe with same filename in EHF_CHRONIC_CLAIM_UPLOAD_FILE table");
				} else {
					ehfChronicClaimUploadFile = new EhfChronicClaimUploadFile();
					ehfChronicClaimUploadFile.setFileName(lStrFileName);
					ehfChronicClaimUploadFile.setCrtUsr(lStrCrtUsr);
					ehfChronicClaimUploadFile.setFilePath(lStrAbsolutePath);
					ehfChronicClaimUploadFile.setCrtDt(new Timestamp(new Date()
							.getTime()));
					ehfChronicClaimUploadFile.setFileStatus((String) lparamMap
							.get("SentStatus"));
					ehfChronicClaimUploadFile.setSetId(lSet_id);
					ehfChronicClaimUploadFile = genericDao.save(ehfChronicClaimUploadFile);
				}
				liResult = 0;
				if (ehfChronicClaimUploadFile != null) {
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
					.append(" select nvl(max(setId),0)+1 as MAXSETID from EhfChronicClaimUploadFile where fileStatus=? ");

			String arg[] = new String[1];
			arg[0] = (String) lparamMap.get("SentStatus");
			maxSetList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					lStrBuffer.toString(), arg);
			if (maxSetList.size() > 0)
				lSet_id = Integer.parseInt(maxSetList.get(0).getMAXSETID()
						.toString());
			else
				lSet_id=1;

			return lSet_id;
		}

		
		/// Reading response Files methods start///
		
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void updateClaimStatusSentByBank() {
			System.out.println("Scheduler toUpdate Chronic OP Claims status sent by bank started in TS");
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
			lparamMap.put("ClamsInProgerss", ClaimsConstants.CHRONIC_CLAIM_SENT);
			lparamMap.put("ClamSettled", ClaimsConstants.CHRONIC_CLAIM_PAID);
			lparamMap.put("ClaimFailed", ClaimsConstants.CHRONIC_CLAIM_REJ_BANK);
			lparamMap.put("AcknowledgmentRcvd", ClaimsConstants.CHRONIC_CLAIM_ACK_REC);
			lparamMap.put("TransReadyStatus", ClaimsConstants.TransReadyStatus);
			lparamMap.put("TransPaidStatus", ClaimsConstants.TransPaidStatus);		
			lparamMap.put("TransAckStatus", ClaimsConstants.TRANSACKSTATUS);
			lparamMap.put("TransRejStatus", ClaimsConstants.TRANSREJSTATUS);
			
					
			lparamMap.put("ClaimPaidRemarks", ClaimsConstants.CLAIM_PAY_DONE);
			lparamMap.put("ClaimAckRemarks", ClaimsConstants.CLAIM_ACKNOWLEDGED);
			lparamMap.put("REMARKS", "");
			//lparamMap.put("CRTUSER",ClaimsConstants.EO_ADMIN_USER_ID);
			// 007 Erroneous claim payment
			
			lparamMap.put("ClosedStatus", ClaimsConstants.CLOSED);
			//lparamMap.put("UPD_USR", ClaimsConstants.EO_ADMIN_USER_ID);
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
						String fileFlag = Files[FileCnt].substring(0, 4)
								.toUpperCase();
						if (ClaimsConstants.CHRONIC_CLAIM_CLIENT_CODE.equalsIgnoreCase(fileFlag)) {
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
			System.out.println("Scheduler toUpdate Chronic OP Claims status sent by bank ended in TS");
		}

		/**
		 * Download file from PKIOUTPut To ReceivedClaims and do process.
		 *
		 * @param lStrSrcDir the lstr src dir
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
	            if (OrgFileName.substring(0, 4).toUpperCase().equals("EHSC")){
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
				e.printStackTrace();
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
			List<Map<String,String>> rejCaseList=new ArrayList<Map<String,String>>();
			int i = 0, lFlag = 0, iStatus = 0, fileStatusConut = 0;
			String rejectedCasesRemarks=null;
			String lStrChronicNo = "",lStrClaimAmt="", lStrACNo = "", lStrStatus = "", lStrTransDt = "", lStrTransId = "", lStrPaymentUid = null,lstrBenfId=null;
			String lStrClaimPreStatus = "",lStrClaimStatus = "", lStrRemarks = "", lStrRembsStatus = "", lStrPaidDate = null, lStrRejCode = null;
			String rejectedCases=(String) lparamMap.get("RejectedCases");
			String rejectedCasesMail="";
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
					lstrBenfId=(String)lDataList.get(j + 1);
					lStrClaimAmt = (String) lDataList.get(j + 3);
					lStrTransDt = (String) lDataList.get(j + 5);
					lStrChronicNo = (String) lDataList.get(j + 6);
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
					lparamMap.put("CHRONIC_NO", lStrChronicNo);
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
						rejectedCases = rejectedCases+lStrChronicNo+" , ";
						if(rejectedCasesRemarks!=null)
							rejectedCasesRemarks=rejectedCasesRemarks+","+lStrRemarks;
						else
							rejectedCasesRemarks=lStrRemarks;
						String hospName=null;
						hospName=getHospName(lstrBenfId);				
						rejectedCasesMail=rejectedCasesMail+lStrChronicNo+"-"+hospName+"-"+lStrRemarks+"\n";
							lStrClaimStatus = (String) lparamMap.get("ClaimFailed");
							lStrRembsStatus = (String) lparamMap
									.get("RembsClaimRej");
							lparamMap.put("RejectedCasesMail", rejectedCasesMail);
						
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
					lparamMap.put("CASE_FOLLOWUP_ID", lStrChronicNo);
					lparamMap.put("CASE_TYPE", "REV_FUND");
					lparamMap.put("RejectedCases",rejectedCases);
					
					
					String chronicId=lStrChronicNo.substring(0, lStrChronicNo.length()-2);
					lparamMap.put("CHRONIC_ID", chronicId);
					System.out.println("chronic id is : "+chronicId);
					EhfChronicCaseDtl caseDtls= null;
					EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,lStrChronicNo);
					
					
					if (lFlag != 1) {
						iStatus = 0;
						
						if (lStrTransDt.trim().length() > 1 && lStrTransDt != null) { 
																						
							
						
								iStatus = updatePaymentDetails(lparamMap);        //Updating Claim Details acc to Status  
								if (iStatus > 0 && lStrStatus.equals("R"))
									updateAccountsTransactionNew(lStrChronicNo,lStrClaimAmt,         //Reverting details in Accounts tables iF Rejected
											lStrTransDt);
							

						}

						if (iStatus > 0) {
							StringBuffer lStrBuffer = new StringBuffer();

							EhfChronicCaseDtl ehfChronicCase = genericDao.findById(EhfChronicCaseDtl.class,
									EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
							ehfChronicCase.setLstUpdDt(new Timestamp(new Date().getTime()));
							ehfChronicCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
							ehfChronicCase.setChronicStatus(lStrClaimStatus);
							ehfChronicCase = genericDao.save(ehfChronicCase);

							if (ehfChronicCase != null) {
								setChronicAudit(lparamMap);
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
					EhfChronicClaimUploadFile ehfChronicClaimUploadFile = null;
					ehfChronicClaimUploadFile = genericDao.findById(
							EhfChronicClaimUploadFile.class, String.class,
							(String) lparamMap.get("FileName"));
					if (ehfChronicClaimUploadFile.getFileStatus() != null
							&& ehfChronicClaimUploadFile.getFileStatus().equalsIgnoreCase(
									(String) lparamMap.get("SentStatus"))) {
						ehfChronicClaimUploadFile.setLstUpdDt(new Timestamp(new Date()
								.getTime()));
						ehfChronicClaimUploadFile.setFileStatus((String) lparamMap
								.get("ClosedStatus"));
					}
					ehfChronicClaimUploadFile = genericDao.save(ehfChronicClaimUploadFile);
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
									sb.append(" from EhfChronicCaseDtl ec,EhfmHospitals eh where ec.caseHospCode=eh.hospId");
									sb.append(" and ec.id.chronicNo='"+caseIdRej+"'");
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
			    		StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
			    		while (token.hasMoreElements()) {
			    			
			    			String mailId=(String) token.nextElement();
			    			String[] emailToArray = { mailId };
			    			EmailVO emailVO = new EmailVO();
							emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
							emailVO.setTemplateName(config.getString("EhfRejectedCasePayment"));
							
							emailVO.setFromEmail(config.getString("emailFrom"));
							emailVO.setToEmail(emailToArray);
							emailVO.setSubject(config.getString("rejectedChronicCasesPayment"));
							Map<String, List<Map<String,String>>> model = new HashMap<String, List<Map<String,String>>>();
							model.put("caseNo",
									rejCaseList );
							//generating Email
							commonService.generateMail(emailVO, model);
			    		}
				}
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}

			return lResult;
		} 
		@SuppressWarnings("rawtypes")
		@Transactional
		public int updatePaymentDetails(HashMap lparamMap) throws Exception {
			int iResult = 0;
			
			String lStrStatus = (String) lparamMap.get("TransStatus");
			long timeMillSec = 0;
			String lStrChronicNo = "";String chronicId="";
			
			EhfChronicClaimAccount ehfChronicClaimAcc = null;
			try {
				List<String> colValues = new ArrayList<String>();
				TimeStamp t = new TimeStamp();
				if((String) lparamMap
						.get("TRANS_DT")!=null)
				timeMillSec = t.getTimeStampFromDate((String) lparamMap
						.get("TRANS_DT"));
				
				chronicId=(String) lparamMap.get("CHRONIC_ID");
				lStrChronicNo = (String) lparamMap.get("CASE_FOLLOWUP_ID");

				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			
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
				
				
				if(lStrChronicNo!=null && !lStrChronicNo.contains("DRUG"))
				{
					
					criteriaList.add(new GenericDAOQueryCriteria("chronicNo", lStrChronicNo,
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
				List<EhfChronicClaimAccount> ehfChronicClaimAccount = genericDao
						.findAllByCriteria(EhfChronicClaimAccount.class, criteriaList);
				if (ehfChronicClaimAccount.size() > 0) {
					ehfChronicClaimAcc = ehfChronicClaimAccount.get(0);
					ehfChronicClaimAcc
							.setTransactionId((String) lparamMap.get("TRANS_ID"));
					if ((String) lparamMap.get("TRANS_DT") != null)
						ehfChronicClaimAcc.setTransactionDt(df.parse((String) lparamMap
								.get("TRANS_DT")));
					ehfChronicClaimAcc.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
					ehfChronicClaimAcc.setLstUpdDt(new Timestamp(new Date().getTime()));
					ehfChronicClaimAcc.setRemarks((String) lparamMap.get("REMARKS"));
				    
					ehfChronicClaimAcc.setTimeMilSec((timeMillSec));
					if ((String) lparamMap.get("SBH_PAID_DATE") != null)
						ehfChronicClaimAcc.setSbhPaidDate(sdf.parse((String) lparamMap
								.get("SBH_PAID_DATE")));

					if (lStrStatus.equals("A")) {
						ehfChronicClaimAcc.setTransStatus((String) lparamMap
								.get("TransAckStatus"));
					} else if (lStrStatus.equals("E")) {
						ehfChronicClaimAcc.setTransStatus((String) lparamMap
								.get("TransPaidStatus"));
					} else if (lStrStatus.equals("R")) {
						ehfChronicClaimAcc.setTransStatus((String) lparamMap
								.get("TransRejStatus"));
					}
					ehfChronicClaimAcc = genericDao.save(ehfChronicClaimAcc);
				}
				}
				
				
				
				else if(lStrChronicNo!=null && lStrChronicNo.contains("DRUG"))
				{
					EhfChronicDrugClaimAcct ehfChronicDrugClaimAcct=new EhfChronicDrugClaimAcct();
					
					
					criteriaList.add(new GenericDAOQueryCriteria("chronicDrugId", lStrChronicNo,
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
					List<EhfChronicDrugClaimAcct> ehfChronicDrugClaimAcctLst = genericDao
					.findAllByCriteria(EhfChronicDrugClaimAcct.class, criteriaList);
			   if (ehfChronicDrugClaimAcctLst.size() > 0) {
				
				   ehfChronicDrugClaimAcct = ehfChronicDrugClaimAcctLst.get(0);
				   ehfChronicDrugClaimAcct
						.setTransactionId((String) lparamMap.get("TRANS_ID"));
				if ((String) lparamMap.get("TRANS_DT") != null)
					ehfChronicDrugClaimAcct.setTransactionDt(df.parse((String) lparamMap
							.get("TRANS_DT")));
				ehfChronicDrugClaimAcct.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
				ehfChronicDrugClaimAcct.setLstUpdDt(new Timestamp(new Date().getTime()));
				ehfChronicDrugClaimAcct.setRemarks((String) lparamMap.get("REMARKS"));
			    
				ehfChronicDrugClaimAcct.setTimeMilSec(BigDecimal.valueOf(timeMillSec));
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					ehfChronicDrugClaimAcct.setSbhPaidDate(sdf.parse((String) lparamMap
							.get("SBH_PAID_DATE")));

				if (lStrStatus.equals("A")) {
					ehfChronicDrugClaimAcct.setTransStatus((String) lparamMap
							.get("TransAckStatus"));
				} else if (lStrStatus.equals("E")) {
					ehfChronicDrugClaimAcct.setTransStatus((String) lparamMap
							.get("TransPaidStatus"));
				} else if (lStrStatus.equals("R")) {
					ehfChronicDrugClaimAcct.setTransStatus((String) lparamMap
							.get("TransRejStatus"));
				}
				ehfChronicDrugClaimAcct = genericDao.save(ehfChronicDrugClaimAcct);	
				}
				}
			   
			   
			   
					if (ehfChronicClaimAcc != null) {

						EhfChronicCaseDtlPK ehfChronicCaseDtlpk=new EhfChronicCaseDtlPK(chronicId,lStrChronicNo);
						EhfChronicCaseDtl ehfChronicCaseDtl = genericDao.findById(EhfChronicCaseDtl.class,
								EhfChronicCaseDtlPK.class, ehfChronicCaseDtlpk);
						if(ehfChronicCaseDtl!=null)
						{
						ehfChronicCaseDtl.setLstUpdDt(new Timestamp(new Date().getTime()));
						ehfChronicCaseDtl.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
						ehfChronicCaseDtl.setChTransId((String) lparamMap.get("TRANS_ID"));
						ehfChronicCaseDtl.setChronicStatus((String) lparamMap.get("STAT_ID"));
						if ((String) lparamMap.get("TRANS_DT") != null)
							ehfChronicCaseDtl.setChSubDt(df.parse((String) lparamMap
									.get("TRANS_DT")));
						if ((String) lparamMap.get("SBH_PAID_DATE") != null)
							ehfChronicCaseDtl.setChSbhDt(sdf.parse((String) lparamMap
									.get("SBH_PAID_DATE")));
						//ehfAnnualCase.setCsRemarks((String) lparamMap.get("REMARKS"));
						ehfChronicCaseDtl = genericDao.save(ehfChronicCaseDtl);
						if (ehfChronicCaseDtl != null)
							iResult = 1;
						}
					}
				
			} catch (Exception e) {
				logger.error("Error:updatePaymentDetails method in chronicOpClaimsDAOImpl with message "+e);
			}
			return iResult;

		}
		@SuppressWarnings("rawtypes")
		@Transactional
		public void setChronicAudit(HashMap lparamMap) throws Exception {
			String lStrCaseNo = (String) lparamMap.get("CHRONIC_NO");
			StringBuffer lQueryBuffer = new StringBuffer();
			String args[] = new String[1];
			Long lActOrder = 0L;
			try {
				lQueryBuffer
						.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");
				args[0] = lStrCaseNo;
				List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
						ClaimsFlowVO.class, lQueryBuffer.toString(), args);
				if (actOrderList != null && !actOrderList.isEmpty()
						&& actOrderList.get(0).getCOUNT() != null) {
					if (actOrderList.get(0).getCOUNT().longValue() >= 0)
						lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
				}
				
				
				String chronicId=lStrCaseNo.substring(0, lStrCaseNo.length()-3);
				System.out.println("chronic id is : "+chronicId);
				EhfChronicAuditPK EhfChronicAuditpk=new EhfChronicAuditPK(chronicId,lStrCaseNo,lActOrder);
				
				
				// insert into ehf_audit
				EhfChronicAudit chronicAudit = new EhfChronicAudit();

				chronicAudit.setId(EhfChronicAuditpk);
				chronicAudit.setActId((String) lparamMap.get("STAT_ID"));
				chronicAudit.setActBy((String) lparamMap.get("UPD_USR"));
				chronicAudit.setCrtUsr((String) lparamMap.get("UPD_USR"));
				chronicAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
				chronicAudit.setRemarks((String) lparamMap.get("REMARKS"));
				chronicAudit.setUserRole(ClaimsConstants.EO_ADMIN_GRP_ID);
				chronicAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
				chronicAudit.setLangId("en_US");
				if ((String) lparamMap.get("SBH_PAID_DATE") != null)
					chronicAudit.setSbhPaidDate(sdf.parse((String) lparamMap
							.get("SBH_PAID_DATE")));
				chronicAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
				chronicAudit.setRejCode((String) lparamMap.get("REJ_CODE"));

				genericDao.save(chronicAudit);
			} catch (Exception e) {
				logger.error("Error:setCaseAudit method in ClaimsPaymentDAOImpl with message "+e);
			}
		}
		
		private void updateAccountsTransactionNew(String lStrCaseNo,String lStrClaimAmt, String lStrTransDt) {
			
			
			
			try {

				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("transId", lStrCaseNo,
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
					String chronicId=lStrCaseNo.substring(0, lStrCaseNo.length()-3);
					System.out.println("chronic id is : "+chronicId);
					EhfChronicCaseDtlPK ehfChronicCaseDtlPk=new EhfChronicCaseDtlPK(chronicId,lStrCaseNo);

					EhfChronicCaseDtl ehfCase = genericDao.findById(EhfChronicCaseDtl.class,
							EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPk);
					if (ehfCase != null)
						lStrSchemeId = ehfCase.getSchemeId();

					EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();

					ehfAcctsTransactionDtlsEntry
							.setVoucherId(getNextTransSeqVal(config
									.getString("ACC.R")));
					ehfAcctsTransactionDtlsEntry.setTransId(lStrCaseNo);
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
					ehfAcctsTransactionDtlsEntry.setCaseId(lStrCaseNo);
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

	
	private String getHospName(String hospId)
	{
		StringBuffer query=new StringBuffer();
		String hospname=null;
		List<ChronicOPVO> vo=new ArrayList<ChronicOPVO>();
		query.append("select hospName as name from EhfmHospitals where hospId='"+hospId+"' ");
		vo=genericDao.executeHQLQueryList(ChronicOPVO.class,
				query.toString());
		
		if(vo.size()>0)
		{
			hospname=vo.get(0).getName();
		}
		else
		{
			hospname=hospId;
		}
		return hospId;
		
	}
	
	
	public boolean updateFailedCaseStatus(String caseId,String paymentType)
	{
		
		boolean isUpdate=false;

		EhfChronicCaseDtl ehfCase=new EhfChronicCaseDtl();
		ehfCase=genericDao.findById(EhfChronicCaseDtl.class, String.class, caseId);
		if(ehfCase!=null)
		{
		try{
			ehfCase.setChronicStatus(config.getString("EHF.Claims.ClaimFailed"));
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
	
		
		return isUpdate;
		}
}

	
	



