package com.ahct.panelDoctor.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.panelDoctor.util.GenerateAsciiFile;
import com.ahct.panelDoctor.util.PanelDocConstants;
import com.ahct.panelDoctor.vo.PanelDocPayVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.panelDoctor.vo.TransactionVO;
import com.ahct.common.util.TimeStamp;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.EhfAcctsTransactionDtls;
import com.ahct.model.EhfPnlDocWrkFlowId;
import com.ahct.model.EhfPnlDocWrkflow;
import com.ahct.model.EhfPnldocTdsPayment;
import com.ahct.model.EhfClaimUploadFile;
import com.ahct.model.EhfPanelDocAuditDtls;
import com.ahct.model.EhfPanelDocAuditDtlsId;
import com.ahct.model.EhfPanelDocCaseDtls;
import com.ahct.model.EhfPanelDocPayments;
import com.ahct.model.EhfPnldocTdsAudit;
import com.ahct.model.EhfPnldocTdsAuditId;


public class PaymentsDAOImpl implements PaymentsDAO{
	GenericDAO genericDao;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	private static final Logger logger = Logger.getLogger(PaymentsDAOImpl.class);
	
	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/** The sdf. */
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
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
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList getDoctorDetails(HashMap lparamMap) {
		Map lFileMap = null ;
		String lSbFileName="";
		String clientAccNo="";
		String clientCode="";
		ArrayList lresList=new ArrayList();
		String failedCaseList="";
		String schemeCode="";
		String scheme=(String) lparamMap.get("SCHEME_ID");
		//creating file name
		DateFormat df = new SimpleDateFormat(config.getString("FIN.DateFormat1"));
		Date date = new Date();
		 String lCurrntDate = df.format(date);
         String lStrDay = lCurrntDate.substring ( 0, 2 ) ;
         String lStrMonth = lCurrntDate.substring ( 3, 5 ) ;
         String lStrYear = lCurrntDate.substring ( 8, 10 ) ;
        String upldSeq= getPmntUpldId(scheme);
        if ( upldSeq != null && ! PanelDocConstants.EMPTY.equalsIgnoreCase(upldSeq) )
        {
          int FileSNOLength = upldSeq.length ();
          if (FileSNOLength <= 1 )
          {
        	  upldSeq = PanelDocConstants.DOUBLE_ZERO + upldSeq ;
          }
          else if ( FileSNOLength > 1 && FileSNOLength <= 2 )
          {
        	  upldSeq = PanelDocConstants.ZERO + upldSeq ;
          }
        } 
        if(scheme.equals(PanelDocConstants.AP_State)){
       // lSbFileName=PanelDocConstants.PANELDOC_CLIENT_CODE_AP+
        //					lStrDay + lStrMonth + lStrYear+PanelDocConstants.DOT+upldSeq;
        clientAccNo=PanelDocConstants.PANELDOC_CLIENT_ACCNO_AP;
        schemeCode=PanelDocConstants.AP_State_Code;
        clientCode=PanelDocConstants.PANELDOC_CLIENT_CODE_AP;
        }
        else if (scheme.equals(PanelDocConstants.TG_State))
        {
        	// lSbFileName=PanelDocConstants.PANELDOC_CLIENT_CODE_TG+
 			//		lStrDay + lStrMonth + lStrYear+PanelDocConstants.DOT+upldSeq;
        	 clientAccNo=PanelDocConstants.PANELDOC_CLIENT_ACCNO_TG;
        	 schemeCode=PanelDocConstants.TG_State_Code;
        	 clientCode=PanelDocConstants.PANELDOC_CLIENT_CODE_TG;
        }
        lparamMap.put("FILENAME", lSbFileName);
      
		List<PanelDocPayVO> panelDocAccListDtls = new ArrayList<PanelDocPayVO>();
		PanelDocPayVO getDocListDtls=new PanelDocPayVO();
		List<PanelDocPayVO> docAccDtls=new ArrayList<PanelDocPayVO>();
		List allDocDtls=new ArrayList();
		Map panelDocAllAmtDtls=new HashMap();
		PanelDocPayVO  doc_claim_dtls=new PanelDocPayVO();
		StringBuffer query= new StringBuffer();
		String[] args = new String[2];
		Double lPanelDoctorAmt = 0.0;
		int createPmntFlg=0;
		try{
		PanelDocPayVO panelDocPayVO=(PanelDocPayVO) lparamMap.get("panelDocPayVO");
		String selectedCondition = " and u.user_id in ( " ;
		String docId=panelDocPayVO.getDOC_ID();
		
		docId=docId.replace("[~", "");
		List<String> checkdDocList=Arrays.asList(docId.split("~"));
        String doctors = PanelDocConstants.EMPTY ;
        for ( String doctorId:checkdDocList )
        {
        doctors = doctors + PanelDocConstants.SINGLE_QUOTE + doctorId + PanelDocConstants.SINGLE_QUOTE_COMA ;
        }
        doctors = doctors.substring ( 0, doctors.lastIndexOf ( PanelDocConstants.COMA ) ) ;
        selectedCondition = selectedCondition + doctors + " )" ;
        
        String selectedWorkflowCondition="and pw.W_ID in( ";
        String wfId=panelDocPayVO.getWRKFLWSETID();
        wfId=wfId.replace("[~", "");
        List<String> checkdWfList=Arrays.asList(wfId.split("~"));
        String workFlows = PanelDocConstants.EMPTY ;
        for ( String wId:checkdWfList )
        {
        	workFlows = workFlows  + wId + PanelDocConstants.COMA ;
        }
        workFlows = workFlows.substring ( 0, workFlows.lastIndexOf ( PanelDocConstants.COMA ) ) ;
        selectedWorkflowCondition = selectedWorkflowCondition + workFlows + " )" ;
        args[0]=config.getString("FIN.CEOGrp");
        args[1]=scheme;
		query.append(" select u.user_id DOC_ID,u.first_name || '-' || u.last_name EMPNAME,to_char(pw.total_pnldoc_amt) AMOUNT,");
		query.append(" pam.name_as_per_acc ACCNAME,pw.w_id WID,bm.bank_branch BANKBRANCH,bm.bank_category BANKCATEGORY,bm.ifc_code BANKIFSC,");
		query.append(" bm.bank_id BANKID,pam.account_no ACCOUNTNO,bm.bank_name BANKNAME");
		query.append(" from ehfm_users u,ehfm_bank_master bm,ehfm_pnldoc_acct_dtls pam,ehf_pnldoc_workflow pw");
		query.append(" where u.user_id=pam.user_id and bm.bank_id=pam.bank_id and pam.active_yn='Y' ");
		query.append(" and pw.current_owner_grp=? and pw.scheme=? and u.user_id=pw.doc_id ");
		//and pw.w_id=pd.WRKFLW_ID and u.user_id=pd.doc_id 
		query.append(selectedCondition);
		query.append(selectedWorkflowCondition);
		//query.append(" group by u.user_id,pam.account_no,pam.name_as_per_acc,bm.bank_name,bm.bank_branch,bm.bank_category,bm.ifc_code ,bm.bank_id,u.first_name || '-' || u.last_name");
		panelDocAccListDtls = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
		
	
 		lparamMap.put("doc_acc_dtls",panelDocAccListDtls);
		if(panelDocAccListDtls!=null && !panelDocAccListDtls.equals(""))
		{
			docAccDtls=(List) lparamMap.get("doc_acc_dtls");
			for(int i=0;i<docAccDtls.size();i++)
			{
				//to set a case id for bulk doc cases
				String SetId=getCaseSetId(schemeCode);
				String caseSetId=SetId+"/P";
				docAccDtls.get(i).setCaseSetId(caseSetId);
				String TDSPaymentId=SetId+PanelDocConstants.SLASH_PTDS;
				docAccDtls.get(i).setCaseSetId(caseSetId);
				docAccDtls.get(i).setTdsPaymentId(TDSPaymentId);
				lparamMap.put("Client_ACC_No", clientAccNo);
				lparamMap.put("CLIENT_AC_CODE", clientCode);
				lparamMap.put("TDS_CASE_TYPE", PanelDocConstants.TDSTRUST);
				lparamMap.put("TDS_ACC_NO", PanelDocConstants.TDSTRUST);
				
				/*Added by Kalyan for TDS Destination Account Number*/
				String schemeId=(String)lparamMap.get("SCHEME_ID");
				String lStrClientAcCode = null,actType=null;
				List<PanelDocPayVO> accountList = new ArrayList<PanelDocPayVO>();
				if(schemeId.equals(PanelDocConstants.AP_State))
					{
						lStrClientAcCode = PanelDocConstants.CLAIMTDS_CLIENT_CODE_AP;
						actType = PanelDocConstants.TDSEHS_AP;
					}
				else if(schemeId.equals(PanelDocConstants.TG_State))
					{
						lStrClientAcCode = PanelDocConstants.CLAIMTDS_CLIENT_CODE_TG;
						actType = PanelDocConstants.TDSEHS_TG;
					}
				
				StringBuffer sb = new StringBuffer();
				sb.append(" select id TDSID, t.account_no ACCOUNTNO,t.name_Asper_Act ACCNAME,t.address ADDRESS,");
				sb.append(" t.bank_Id BANKID,b.ifc_Code BANKIFSC,b.bank_Name BANKNAME,b.bank_Branch BANKBRANCH, b.bank_Category BANKCATEGORY");
				sb.append(" FROM EHFM_TRUST_ACT_DTLS t,EHFM_BANK_MASTER b where t.bank_Id = b.bank_Id  and t.act_Type = ? and t.active_Yn = ? and t.scheme_id=?");
				String[] arg = new String[3];
				arg[0] = actType;
				arg[1] = PanelDocConstants.Y;
				arg[2]=schemeId;
				accountList = genericDao.executeSQLQueryList(
							PanelDocPayVO.class, sb.toString(), arg);
					
				lparamMap.put("TDSACCNO", accountList.get(0).getACCOUNTNO());
				lparamMap.put("CLIENT_IFSC_CODE", accountList.get(0).getBANKIFSC());
				/*Added by Kalyan for TDS Destination Account Number*/
				
				doc_claim_dtls=(PanelDocPayVO) docAccDtls.get(i);
				docAccDtls.get(i).setCurrOwnr(lparamMap.get("CRTUSER").toString());
				panelDocAllAmtDtls=getDeductionDetails(doc_claim_dtls,lparamMap);
				
				if((Boolean) panelDocAllAmtDtls.get("result")){
				getDocListDtls=(PanelDocPayVO) panelDocAllAmtDtls.get("panel_doc_deductn");
				allDocDtls.add(panelDocAllAmtDtls.get("panel_doc_deductn"));
				lPanelDoctorAmt=getDocListDtls.getPanelDocAmt();
				if(lPanelDoctorAmt==0)
				{
					break;
				}
				else{
					createPmntFlg=createPanelDocPmnt(panelDocAllAmtDtls);
				}
				//For TDS
				
				PanelDocPayVO TDSList=new PanelDocPayVO();
				TDSList.setTdsPaymentId(TDSPaymentId);
				TDSList.setTDSAmt(getDocListDtls.getTDSAmt());
				TDSList.setCASE_ID(caseSetId);
				TDSList.setFlag(scheme);
				panelDocAllAmtDtls=getTdsAccountDtlsNew(TDSList,panelDocAllAmtDtls);
				updateTDSAccountDetailsNew(TDSList);
				allDocDtls.add(panelDocAllAmtDtls.get("TDSFILE"));
				}
				else{
					failedCaseList=failedCaseList+docAccDtls.get(i).getDOC_ID()+",";
				}
			}
			if(createPmntFlg==1){
			panelDocAllAmtDtls.put("allDocDetails", allDocDtls);
			lFileMap = PaymentFileCreation ( panelDocAllAmtDtls ) ;
			}
			
		}
		
		lresList.add(lFileMap.get("FileMap"));
		lresList.add(upldSeq);
		lresList.add(lFileMap.get("lCaseList"));
		lresList.add(failedCaseList);
		
		}
		catch (Exception e) {
			logger.error("Error occured in getDoctorDetails() in PaymentsDAOImpl class."
					+ e.getMessage());
//			e.printStackTrace();
		}
		return lresList;
	}

	private void updateTDSAccountDetailsNew(PanelDocPayVO tDSList) {
		try{
		String scheme=tDSList.getFlag();
		TransactionVO transactionVO = new TransactionVO();
		transactionVO.setCaseSetId(tDSList.getCASE_ID());
		transactionVO.setTdsId(tDSList.getTdsPaymentId());
		transactionVO.setNetAmount("");
		transactionVO.setTdsAmount(String.valueOf(tDSList.getTDSAmt()));		
		
		if(scheme.equals(PanelDocConstants.AP_State))
			transactionVO.setScheme(config.getString("FIN.EHFAPSchemeID"));
		else if(scheme.equals(PanelDocConstants.TG_State))
			transactionVO.setScheme(config.getString("FIN.EHFTGSchemeID"));
		else
			transactionVO.setScheme(config.getString("FIN.EHF"));
   		transactionVO.setTransactionType("TDS Payment");
	    transactionVO.setNarration("TDS");
		transactionVO.setTdsType(config.getString("FIN.TDS"));
		submitTdsOrRfPaymentsInAccounts(transactionVO);
		}
		catch (Exception e) {
			logger.error("Error occured in updateTDSAccountDetailsNew() in PaymentsDAOImpl class."
					+ e.getMessage());
//			e.printStackTrace();
		}
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList getRejDoctorDetails(HashMap lparamMap) {
		Map lFileMap = null ;
		String isInsert=null;
		ArrayList lresList=new ArrayList();
		List lCaseList=new ArrayList();
		List FileMap=new ArrayList();
		PanelDocPayVO allDocDetails=new PanelDocPayVO();
		List<PanelDocPayVO> panelDocAccListDtls = new ArrayList<PanelDocPayVO>();
		PanelDocPayVO getDocListDtls=new PanelDocPayVO();
		List<PanelDocPayVO> docAccDtls=new ArrayList<PanelDocPayVO>();
		List allDocDtls=new ArrayList();
		Map panelDocAllAmtDtls=new HashMap();
		PanelDocPayVO  doc_claim_dtls=new PanelDocPayVO();
		StringBuffer query= new StringBuffer();
		String[] args = new String[2];
		Double lPanelDoctorAmt = 0.0;
		int createPmntFlg=0;
		String failedCaseList="";
		String lSbFileName="";
		String clientAccNo="";
		String clientCode="";
		String clientIFSC="";
		
		try{
		String scheme=(String) lparamMap.get("SCHEME_ID");
		//creating file name
		DateFormat df = new SimpleDateFormat(config.getString("FIN.DateFormat1"));
		Date date = new Date();
		 String lCurrntDate = df.format(date);
         String lStrDay = lCurrntDate.substring ( 0, 2 ) ;
         String lStrMonth = lCurrntDate.substring ( 3, 5 ) ;
         String lStrYear = lCurrntDate.substring ( 8, 10 ) ;
        String upldSeq= getPmntUpldId(scheme);
        if ( upldSeq != null && ! PanelDocConstants.EMPTY.equalsIgnoreCase(upldSeq) )
        {
          int FileSNOLength = upldSeq.length ();
          if (FileSNOLength <= 1 )
          {
        	  upldSeq = PanelDocConstants.DOUBLE_ZERO + upldSeq ;
          }
          else if ( FileSNOLength > 1 && FileSNOLength <= 2 )
          {
        	  upldSeq = PanelDocConstants.ZERO + upldSeq ;
          }
        } 
        if(scheme.equals(PanelDocConstants.AP_State)){
           // lSbFileName=PanelDocConstants.PANELDOC_CLIENT_CODE_AP+lStrDay + lStrMonth + lStrYear+PanelDocConstants.DOT+upldSeq;
            clientAccNo=PanelDocConstants.PANELDOC_CLIENT_ACCNO_AP;
       	    clientCode=PanelDocConstants.PANELDOC_CLIENT_CODE_AP;
        
        }
            else  if(scheme.equals(PanelDocConstants.TG_State)){
           // lSbFileName=PanelDocConstants.PANELDOC_CLIENT_CODE_TG+lStrDay + lStrMonth + lStrYear+PanelDocConstants.DOT+upldSeq;
            clientAccNo=PanelDocConstants.PANELDOC_CLIENT_ACCNO_TG;
     	    clientCode=PanelDocConstants.PANELDOC_CLIENT_CODE_TG;
     	   clientIFSC=PanelDocConstants.PANELDOC_CLIENT_IFSC_TG;
        }
            lparamMap.put("FILENAME", lSbFileName);
			
		PanelDocPayVO panelDocPayVO=(PanelDocPayVO) lparamMap.get("panelDocPayVO");
		
		String selectedCondition = " and u.user_id in ( " ;
		String docId=panelDocPayVO.getDOC_ID();
		
		docId=docId.replace("[~", "");
		List<String> checkdDocList=Arrays.asList(docId.split("~"));
        String doctors = PanelDocConstants.EMPTY ;
        for ( String doctorId:checkdDocList )
        {
        doctors = doctors + PanelDocConstants.SINGLE_QUOTE + doctorId + PanelDocConstants.SINGLE_QUOTE_COMA ;
        }
        doctors = doctors.substring ( 0, doctors.lastIndexOf ( PanelDocConstants.COMA ) ) ;
        selectedCondition = selectedCondition + doctors + " )" ;
        
        
        String selectedWorkflowCondition="and epw.W_ID in( ";
        String wfId=panelDocPayVO.getWRKFLWSETID();
        wfId=wfId.replace("[~", "");
        List<String> checkdWfList=Arrays.asList(wfId.split("~"));
        String workFlows = PanelDocConstants.EMPTY ;
        for ( String wId:checkdWfList )
        {
        	workFlows = workFlows + wId + PanelDocConstants.COMA ;
        }
        workFlows = workFlows.substring ( 0, workFlows.lastIndexOf ( PanelDocConstants.COMA ) ) ;
        selectedWorkflowCondition = selectedWorkflowCondition + workFlows + " )" ;
        args[0]=PanelDocConstants.CLAIM_REJ_BANK;
        args[1]=scheme;
        query.append(" select pd.cases_set_id CASE_ID,pd.doctor_id DOC_ID,u.first_name || '-' || u.last_name EMPNAME,pd.pnldoc_amt||'' AMOUNT,");
		query.append(" pam.name_as_per_acc ACCNAME,bm.bank_branch BANKBRANCH,bm.bank_category BANKCATEGORY,bm.ifc_code BANKIFSC,");
		query.append(" bm.bank_id BANKID,pam.account_no ACCOUNTNO,bm.bank_name BANKNAME");
		query.append(" from ehfm_users u,ehfm_bank_master bm,ehfm_pnldoc_acct_dtls pam,ehf_pnldoc_payments pd,ehf_pnldoc_workflow epw");
		query.append(" where u.user_id=pam.user_id and u.user_id=pd.doctor_id and bm.bank_id=pam.bank_id and epw.doc_id=u.user_id and pam.active_yn='Y'");
		query.append(" and pd.payment_status=? and pd.scheme=?  and pd.amount=epw.total_pnldoc_amt");
		query.append(selectedCondition);
		query.append(selectedWorkflowCondition);
		//query.append(" group by pd.cases_set_id,pam.account_no,pam.name_as_per_acc,bm.bank_name,bm.bank_branch,bm.bank_category,bm.ifc_code ,bm.bank_id,u.first_name || '-' || u.last_name,pd.doctor_id");
		panelDocAccListDtls = genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
		for(int i=0;i<panelDocAccListDtls.size();i++)
		{
			allDocDetails = panelDocAccListDtls.get(i);
			lFileMap = new HashMap();
			allDocDetails.setPanelDocAmt(Double.parseDouble(allDocDetails.getAMOUNT()));
			lFileMap.put("UNIQUE_ID", allDocDetails.getCASE_ID());
			lFileMap.put("BENEFICIARY_ACCOUNT_NAME",allDocDetails.getACCNAME());
			lFileMap.put("BENEFICIARY_ID",allDocDetails.getDOC_ID());
			lFileMap.put("BENEFICIARY_ADDR",allDocDetails.getEMPNAME());
			lFileMap.put("BENEFICIARY_BANK_NAME",allDocDetails.getBANKNAME());
			lFileMap.put("BANK_BRANCH",allDocDetails.getBANKBRANCH());
			lFileMap.put("BENEFICIARY_ACCOUNT_NO",allDocDetails.getACCOUNTNO());
			Double d=allDocDetails.getPanelDocAmt();
			String amt=String.valueOf(d.intValue());
			lFileMap.put("TRANSACTION_AMOUNT",amt);
			lFileMap.put("BENEFICIARY_BANK_ID",allDocDetails.getBANKID());
			lFileMap.put("BENEFICIARY_BANK_IFC_CODE",allDocDetails.getBANKIFSC());
			lFileMap.put("CLAINT_AC_CODE",clientCode);
			lFileMap.put("CLAINT_AC_NUMBER", clientAccNo);
			lFileMap.put("CLIENT_IFSC_CODE", clientIFSC);
			lFileMap.put("TRANSACTION_TYPE",allDocDetails.getBANKCATEGORY());
			lFileMap.put("EMAIL_ID",PanelDocConstants.CEO_MAILID);
			lFileMap.put("REJECTED_PAYMENT", "true");
			FileMap.add(lFileMap);
			lCaseList.add(allDocDetails.getCASE_ID());
			allDocDetails.setFILEID(lSbFileName);
			isInsert=updatePanelDocPaymentFile(allDocDetails);
			if(isInsert.equals("true"))
			{
				updateRejectionAccount(allDocDetails,scheme);
			}
		}
		if(FileMap.size()>0){
		lresList.add(FileMap);
		lresList.add(upldSeq);
		lresList.add(lCaseList);
		lresList.add(failedCaseList);
		}
		else{
			
		}
		}
		catch (Exception e) {
			logger.error("Error occured in getRejDoctorDetails() in PaymentsDAOImpl class."
					+ e.getMessage());
//			e.printStackTrace();
		}
		return lresList;
	}
	private void updateRejectionAccount(PanelDocPayVO allDocDetails,String scheme) {
		TransactionVO transactionVO=new TransactionVO();
	try{
		
		EhfAcctsTransactionDtls ehfAcctsTransactionDtls=new EhfAcctsTransactionDtls();
		 List<EhfAcctsTransactionDtls> lStAcctsTransactionDtls=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("transId", allDocDetails.getCASE_ID(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("active_yn", PanelDocConstants.RejectFlag,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("transDate", "",
					GenericDAOQueryCriteria.CriteriaOperator.DESC));
		 lStAcctsTransactionDtls=genericDao.findAllByCriteria(EhfAcctsTransactionDtls.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	
		 
		 for(int i=0;i<lStAcctsTransactionDtls.size();i++){
			 String voucherID=lStAcctsTransactionDtls.get(i).getVoucherId();
			 ehfAcctsTransactionDtls=genericDao.findById(EhfAcctsTransactionDtls.class, String.class, voucherID);
			 ehfAcctsTransactionDtls.setActive_yn(PanelDocConstants.N);
			 ehfAcctsTransactionDtls.setLstUpdDt(new Date());
			 ehfAcctsTransactionDtls.setLstUpdUsr("TCS");
			 ehfAcctsTransactionDtls=genericDao.save(ehfAcctsTransactionDtls);
		 }
		    String amount=String.valueOf(allDocDetails.getPanelDocAmt());
			String uniqueTxn = getNextUniqSeqVal();
			transactionVO.setUniqueTxn(uniqueTxn);
			transactionVO.setTransactionId(allDocDetails.getCASE_ID());
			transactionVO.setDebtAccount(config.getString("FIN.REJGL"));
			
			transactionVO.setVoucherType(config.getString("FIN.Payment"));
			transactionVO.setAmount(amount);
			transactionVO.setFlag(config.getString("FIN.P"));
			transactionVO.setNarration("Panel Doctor Payment");
			if(scheme.equals(PanelDocConstants.AP_State)){
				transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
				transactionVO.setScheme(config.getString("FIN.EHFAPSchemeID"));
			}
			else if(scheme.equals(PanelDocConstants.TG_State)){
				transactionVO.setCreditAccount(config.getString("FIN.MAIN-2316"));
				transactionVO.setScheme(config.getString("FIN.EHFTGSchemeID"));
			}
			else{
				transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
				transactionVO.setScheme(config.getString("FIN.EHF"));
			}
			transactionVO.setCaseSetId(allDocDetails.getCASE_ID());
			transactionVO.setTransactionType("PD Payment");
			transactionVO.setSection("");
			//transactionVO.setActiveYn(PanelDocConstants.RejectFlag);
			transactionVO = insertRecord(transactionVO);
			
		
		
	}
	catch (Exception e) {
//		e.printStackTrace();
		logger.error("updateRejectionAccount method in PaymentsDAOImpl with message"+e);
	}
		
	}

	private String updatePanelDocPaymentFile(PanelDocPayVO allDocDetails) {
		String flag="false";
		//long timeInMiSec=0;
		try{
	EhfPanelDocPayments ehfPanelDocPayments=new EhfPanelDocPayments();
	EhfPnlDocWrkFlowId ehfPnlDocWrkFlowId=new EhfPnlDocWrkFlowId();
	ehfPanelDocPayments=genericDao.findById(EhfPanelDocPayments.class,String.class,allDocDetails.getCASE_ID());
	ehfPanelDocPayments.setFileName(allDocDetails.getFILEID());
	ehfPanelDocPayments.setTransStatus(PanelDocConstants.SENT);
	ehfPanelDocPayments.setPmntStatus(PanelDocConstants.SENT_TO_BANK);
	ehfPanelDocPayments.setRemarks(PanelDocConstants.CLAIM_SENT_RMK);
	ehfPanelDocPayments.setLstUpdDt(new Date());
	ehfPanelDocPayments.setLstUpdUsr(PanelDocConstants.CEO_USER_ID);
	//ehfPanelDocPayments.setTimeInMilSec(timeInMiSec);
	ehfPanelDocPayments = genericDao.save(ehfPanelDocPayments);
	
	getCaseDtlsAfterRejection(allDocDetails);
	flag="true";
		}
		catch (Exception e) {
			logger.error("Error occured in updatePanelDocPaymentFile() in PaymentsDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	private void getCaseDtlsAfterRejection(PanelDocPayVO allDocDetails) {
		
		List docList=new ArrayList();
		List workFlwList=new ArrayList();
		PanelDocPayVO auditDtls=new PanelDocPayVO();
		String caseDate="";
		int count=0;
		try
		{
			 EhfPanelDocCaseDtls ehfPanelDocCaseDtls=new EhfPanelDocCaseDtls();
		 List<EhfPanelDocCaseDtls> lStEhfPanelDocCaseDtls=null;
		 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		 criteriaList.add(new GenericDAOQueryCriteria("pmntId", allDocDetails.getCASE_ID(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 lStEhfPanelDocCaseDtls=genericDao.findAllByCriteria(EhfPanelDocCaseDtls.class,criteriaList);
		 criteriaList.removeAll(criteriaList);	
		 for(int i=0;i<lStEhfPanelDocCaseDtls.size();i++){
			 long pID=lStEhfPanelDocCaseDtls.get(i).getId();
			 ehfPanelDocCaseDtls=genericDao.findById(EhfPanelDocCaseDtls.class, Long.class, pID);
			 ehfPanelDocCaseDtls.setPnlDocPmntStatus(PanelDocConstants.SENT_TO_BANK);
			 caseDate=df.format(lStEhfPanelDocCaseDtls.get(i).getLstUpdDt());
			 ehfPanelDocCaseDtls.setLstUpdDt(new Date());
			 ehfPanelDocCaseDtls.setLstUpdUsr(PanelDocConstants.CEO_USER_ID);
			 ehfPanelDocCaseDtls=genericDao.save(ehfPanelDocCaseDtls);
			 if(!(workFlwList.contains( lStEhfPanelDocCaseDtls.get(i).getWorkFlwId()))){
				 workFlwList.add(count,lStEhfPanelDocCaseDtls.get(i).getWorkFlwId());
				 docList.add(count,lStEhfPanelDocCaseDtls.get(i).getDocId());
				 count++;
				 }
		 }
		 //To update WorkFlow Table and audit table
		 EhfPnlDocWrkflow ehfPnlDocWrkflow=new EhfPnlDocWrkflow();
		 for(int i=0;i<workFlwList.size();i++){
		    long wId=Long.parseLong(workFlwList.get(i).toString());
			 String docId=docList.get(i).toString();
			 auditDtls.setDOC_ID(docId);
		     auditDtls.setID(String.valueOf(wId));
		     auditDtls.setCASE_DATE(caseDate);
			 auditDtls.setUserId(PanelDocConstants.CEO_USER_ID);
			 auditDtls.setPrevWrkflowId(config.getString("FIN.WorkFlowIdSent"));
			 auditDtls.setFILEID(allDocDetails.getFILEID());
			 updateAudit(auditDtls);
			ehfPnlDocWrkflow=genericDao.findById(EhfPnlDocWrkflow.class, EhfPnlDocWrkFlowId.class, new EhfPnlDocWrkFlowId(wId,docId));
			ehfPnlDocWrkflow.setPrevWrkflwId(config.getString("FIN.WorkFlowIdCEO"));
			ehfPnlDocWrkflow.setPrevOwnrGrp(config.getString("FIN.CEOGrp"));
			ehfPnlDocWrkflow.setCurrWrkflwId(config.getString("FIN.WorkFlowIdSent"));
			ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.InProgress"));
			ehfPnlDocWrkflow.setLstUpdDt(new Date());
			ehfPnlDocWrkflow.setLstUpdUsr(PanelDocConstants.CEO_USER_ID);
			ehfPnlDocWrkflow=genericDao.save(ehfPnlDocWrkflow);
		 }
		}
		
		catch (Exception e) {
//			logger.error("Error occured in updatePanelDocPaymentFile() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getDeductionDetails(PanelDocPayVO doc_claim_dtls, HashMap lparamMap)
	{
		
		boolean isInsert=false;
		PanelDocPayVO  percent_tds_dtls=new PanelDocPayVO();
		percent_tds_dtls=getTDSDeduction(doc_claim_dtls);
		String activeTDS=percent_tds_dtls.getTdsActive();
		lparamMap.put("panel_doc_deductn", percent_tds_dtls);
		if(activeTDS.equals("Y")){
			isInsert = false ;
			//isInsert = processTDSPayments(percent_tds_dtls,lparamMap) ; //Commented for sending TDS Payment along with main Payment
			System.out.println("enter deductions");
			isInsert = processTDSPaymentsNew(percent_tds_dtls,lparamMap) ;
	      	}
		else
			isInsert = true;
		lparamMap.put("result", isInsert);
		return lparamMap;
		
	}
	
	

	private PanelDocPayVO getTDSDeduction(PanelDocPayVO doc_claim_dtls) {
		double lStrTmpUserAmt=0;
        String TDS_RATE_PERCENT = (String)(config.getString("tds_rate_percent") != null ? config.getString("tds_rate_percent"):"10");
        double lTDSPercentage=Double.parseDouble(TDS_RATE_PERCENT);
        //double lTDSPercentage=Double.parseDouble(PanelDocConstants.TDS_PERCENT);
		//double lUserPercentage=Double.parseDouble(PanelDocConstants.PANELDOC_PERCENT);
		double lTaxAmt=0,lTDSAmt=0,lDocAmt=0;
		String tdsActiveYn="Y";
		try{
			lStrTmpUserAmt=Double.parseDouble(doc_claim_dtls.getAMOUNT());
			if(tdsActiveYn.equals("Y")){
				lTaxAmt = (lStrTmpUserAmt * lTDSPercentage) / 100;
				lTDSAmt = lTaxAmt;

				if (((lTDSAmt * 10) % 10) > 0)
					lTDSAmt = new Double((int) lTDSAmt + 1);

				lDocAmt = lStrTmpUserAmt - lTDSAmt;
				doc_claim_dtls.setPanelDocAmt(lDocAmt);  
				doc_claim_dtls.setTDSAmt(lTDSAmt);
				doc_claim_dtls.setSurchargeAmt(PanelDocConstants.SURCHARGE_AMT);
				doc_claim_dtls.setCessAmt(PanelDocConstants.CESS_AMT);
				doc_claim_dtls.setTaxAmt(lTDSAmt);
				doc_claim_dtls.setTdsActive(tdsActiveYn);
			}
			else
			{
				lDocAmt=lStrTmpUserAmt;
				lTDSAmt=0;
				doc_claim_dtls.setPanelDocAmt(lDocAmt);  
				doc_claim_dtls.setTDSAmt(lTDSAmt);
				doc_claim_dtls.setSurchargeAmt(PanelDocConstants.SURCHARGE_AMT);
				doc_claim_dtls.setCessAmt(PanelDocConstants.CESS_AMT);
				doc_claim_dtls.setTaxAmt(lTDSAmt);
				doc_claim_dtls.setTdsActive(tdsActiveYn);
			}
			
		}
		catch (Exception e) {
//			logger.error("Error occured in getDeductionDetails() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return doc_claim_dtls;
	}
	
	
	/*
	 * Commented for sending main payment file along with TDS file
	 */
	/*@SuppressWarnings( { "rawtypes", "unchecked"} )
	private boolean processTDSPayments(PanelDocPayVO percent_tds_dtls, HashMap lparamMap) {
		
		boolean isExecuted = PanelDocConstants.BOOLEAN_FALSE;
		EhfPnldocTdsPayment ehfPnldocTdsPayment = new EhfPnldocTdsPayment();
		try{
		String tdsPmntId=percent_tds_dtls.getTdsPaymentId();
		ehfPnldocTdsPayment = genericDao.findById(EhfPnldocTdsPayment.class,String.class,tdsPmntId );
		if (ehfPnldocTdsPayment != null) {
			
			ehfPnldocTdsPayment.setPaymentStatus(PanelDocConstants.CLAIM_READY_PAYMENT);
			ehfPnldocTdsPayment.setClaimAmt(percent_tds_dtls.getTDSAmt());
			ehfPnldocTdsPayment.setRemarks(PanelDocConstants.CLAIM_READY_PAYMENT_REMARKS);
			ehfPnldocTdsPayment.setTransStatus(PanelDocConstants.TransReadyStatus );
			ehfPnldocTdsPayment.setTimeMilliSec((long) 0);
			ehfPnldocTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
			ehfPnldocTdsPayment.setCrtUser(percent_tds_dtls.getCurrOwnr());
			ehfPnldocTdsPayment.setPaymentCheck(PanelDocConstants.F);
			ehfPnldocTdsPayment.setCaseType(PanelDocConstants.TDSTRUST);
			ehfPnldocTdsPayment.setPaymentType(PanelDocConstants.PANELDOCTORS_TDS_PAYTYPE);
			ehfPnldocTdsPayment.setCaseSetId(percent_tds_dtls.getCaseSetId());
			ehfPnldocTdsPayment.setScheme((String) lparamMap.get("SCHEME_ID"));
			ehfPnldocTdsPayment = genericDao.save(ehfPnldocTdsPayment);
			System.out.println("Exception in method processTDSPayments--ORA-00001: unique constraint");
			logger.info("Exception in method processTDSPayments--ORA-00001: unique constraint");
		}
		else
		{
			ehfPnldocTdsPayment = new EhfPnldocTdsPayment();
			ehfPnldocTdsPayment.setTdsPaymentId(tdsPmntId);
			ehfPnldocTdsPayment.setPaymentStatus(PanelDocConstants.CLAIM_READY_PAYMENT);
			ehfPnldocTdsPayment.setClaimAmt(percent_tds_dtls.getTDSAmt());
			ehfPnldocTdsPayment.setRemarks(PanelDocConstants.CLAIM_READY_PAYMENT_REMARKS);
			ehfPnldocTdsPayment.setTransStatus(PanelDocConstants.TransReadyStatus );
			ehfPnldocTdsPayment.setTimeMilliSec((long) 0);
			ehfPnldocTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
			ehfPnldocTdsPayment.setCrtUser(percent_tds_dtls.getCurrOwnr());
			ehfPnldocTdsPayment.setPaymentCheck(PanelDocConstants.F);
			ehfPnldocTdsPayment.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
			ehfPnldocTdsPayment.setPaymentType((String) lparamMap.get("TDS_PAYMENT_TYPE"));
			ehfPnldocTdsPayment.setDeductorType("CD525");
			ehfPnldocTdsPayment.setCaseSetId(percent_tds_dtls.getCaseSetId());
			ehfPnldocTdsPayment.setScheme((String) lparamMap.get("SCHEME_ID"));
			ehfPnldocTdsPayment = genericDao.save(ehfPnldocTdsPayment);
		}
		if (ehfPnldocTdsPayment != null) {
			isExecuted = PanelDocConstants.BOOLEAN_TRUE;
		} 
		else {
			isExecuted = PanelDocConstants.BOOLEAN_FALSE;
		}
		if (isExecuted) {
			
			lparamMap.put("TDS_STAT_ID", PanelDocConstants.CLAIM_READY_PAYMENT);
			lparamMap.put("TdsRemarks", PanelDocConstants.CLAIM_READY_PAYMENT_REMARKS);
			lparamMap.put("TDS_PAYMENT_ID",tdsPmntId);
			setTDSAuditDetails(lparamMap);
			isExecuted=true;
		}
		}
		catch (Exception e) {
			logger.error("Error occured in setTDSAuditDetails() in PaymentsDAOImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return isExecuted;
	}*/

	@SuppressWarnings( { "rawtypes", "unchecked"} )
	private boolean processTDSPaymentsNew(PanelDocPayVO percent_tds_dtls, HashMap lparamMap) {
		System.out.println("Enter processTDSPaymentsNew");
		boolean isExecuted = PanelDocConstants.BOOLEAN_FALSE;
		EhfPnldocTdsPayment ehfPnldocTdsPayment = new EhfPnldocTdsPayment();
		try{
		String tdsPmntId=percent_tds_dtls.getTdsPaymentId();
		ehfPnldocTdsPayment = genericDao.findById(EhfPnldocTdsPayment.class,String.class,tdsPmntId );
		if (ehfPnldocTdsPayment != null) {
			
			ehfPnldocTdsPayment.setPaymentStatus(PanelDocConstants.SENT_TO_BANK);
			ehfPnldocTdsPayment.setClaimAmt(percent_tds_dtls.getTDSAmt());
			ehfPnldocTdsPayment.setRemarks(PanelDocConstants.CLAIM_SENT_RMK);
			ehfPnldocTdsPayment.setTransStatus(PanelDocConstants.SENT );
			ehfPnldocTdsPayment.setTimeMilliSec((long) 0);
			ehfPnldocTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
			ehfPnldocTdsPayment.setCrtUser(percent_tds_dtls.getCurrOwnr());
			ehfPnldocTdsPayment.setPaymentCheck(PanelDocConstants.F);
			ehfPnldocTdsPayment.setCaseType(PanelDocConstants.TDSTRUST);
			ehfPnldocTdsPayment.setPaymentType(PanelDocConstants.PANELDOCTORS_TDS_PAYTYPE);
			ehfPnldocTdsPayment.setCaseSetId(percent_tds_dtls.getCaseSetId());
			ehfPnldocTdsPayment.setScheme((String) lparamMap.get("SCHEME_ID"));
			ehfPnldocTdsPayment.setFileName((String) lparamMap.get("FILENAME"));
			ehfPnldocTdsPayment = genericDao.save(ehfPnldocTdsPayment);
			System.out.println("Exception in method processTDSPayments--ORA-00001: unique constraint");
			logger.info("Exception in method processTDSPayments--ORA-00001: unique constraint");
		}
		else
		{
			ehfPnldocTdsPayment = new EhfPnldocTdsPayment();
			ehfPnldocTdsPayment.setTdsPaymentId(tdsPmntId);
			ehfPnldocTdsPayment.setPaymentStatus(PanelDocConstants.SENT_TO_BANK);
			ehfPnldocTdsPayment.setClaimAmt(percent_tds_dtls.getTDSAmt());
			ehfPnldocTdsPayment.setRemarks(PanelDocConstants.CLAIM_SENT_RMK);
			ehfPnldocTdsPayment.setTransStatus(PanelDocConstants.SENT );
			ehfPnldocTdsPayment.setTimeMilliSec((long) 0);
			ehfPnldocTdsPayment.setCrtDate(new Timestamp(new Date().getTime()));
			ehfPnldocTdsPayment.setCrtUser(percent_tds_dtls.getCurrOwnr());
			ehfPnldocTdsPayment.setPaymentCheck(PanelDocConstants.F);
			ehfPnldocTdsPayment.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
			ehfPnldocTdsPayment.setPaymentType((String) lparamMap.get("TDS_PAYMENT_TYPE"));
			ehfPnldocTdsPayment.setDeductorType("CD525");
			ehfPnldocTdsPayment.setCaseSetId(percent_tds_dtls.getCaseSetId());
			ehfPnldocTdsPayment.setScheme((String) lparamMap.get("SCHEME_ID"));
			ehfPnldocTdsPayment.setFileName((String) lparamMap.get("FILENAME"));
			ehfPnldocTdsPayment = genericDao.save(ehfPnldocTdsPayment);
		}
		if (ehfPnldocTdsPayment != null) {
			isExecuted = PanelDocConstants.BOOLEAN_TRUE;
		} 
		else {
			isExecuted = PanelDocConstants.BOOLEAN_FALSE;
		}
		if (isExecuted) {
			System.out.println("save processTDSPaymentsNew");
			lparamMap.put("TDS_STAT_ID", PanelDocConstants.SENT_TO_BANK);
			lparamMap.put("TdsRemarks", PanelDocConstants.CLAIM_SENT_RMK);
			lparamMap.put("TDS_PAYMENT_ID",tdsPmntId);
			setTDSAuditDetails(lparamMap);
			isExecuted=true;
		}
		}
		catch (Exception e) {
//			logger.error("Error occured in setTDSAuditDetails() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return isExecuted;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int createPanelDocPmnt(Map lparamMap) {
		boolean isexecuted=false;
		TransactionVO transactionVO=new TransactionVO();
		String docId="";
		String wId="";
		String scheme=(String)lparamMap.get("SCHEME_ID");
		String TDSAccNo=(String)lparamMap.get("TDSACCNO");
		List<PanelDocPayVO> caseList=new ArrayList<PanelDocPayVO>();
		try{
		EhfPanelDocPayments ehfPanelDocPayments=new EhfPanelDocPayments();
		PanelDocPayVO getDocListDtls=new PanelDocPayVO();
		getDocListDtls=(PanelDocPayVO) lparamMap.get("panel_doc_deductn");
		docId=getDocListDtls.getDOC_ID();
		wId=getDocListDtls.getWID().toString();
		ehfPanelDocPayments.setCaseSetId(getDocListDtls.getCaseSetId());
		ehfPanelDocPayments.setDocId(getDocListDtls.getDOC_ID());
		ehfPanelDocPayments.setAmount(Double.parseDouble(getDocListDtls.getAMOUNT()));
		ehfPanelDocPayments.setFileName((String)lparamMap.get("FILENAME"));
		ehfPanelDocPayments.setTransStatus((String)lparamMap.get("SentStatus"));
		ehfPanelDocPayments.setTimeInMilSec(Long.parseLong(PanelDocConstants.ZERO));
		ehfPanelDocPayments.setRemarks("Claim Sent For Payment");
		ehfPanelDocPayments.setCrtUsr((String) lparamMap.get("CRTUSER"));
		ehfPanelDocPayments.setCrtDt(new Date());
		ehfPanelDocPayments.setSrcAccNo((String) lparamMap.get("Client_ACC_No"));
		ehfPanelDocPayments.setDestAccNo(getDocListDtls.getACCOUNTNO());
		ehfPanelDocPayments.setTdsDesAcc(TDSAccNo);
		ehfPanelDocPayments.setPnlDocAmt(getDocListDtls.getPanelDocAmt());
		ehfPanelDocPayments.setTdsAmt(getDocListDtls.getTDSAmt());
		ehfPanelDocPayments.setTdsCessAmt(getDocListDtls.getCessAmt());
		ehfPanelDocPayments.setTdsSurchargeAmt(getDocListDtls.getSurchargeAmt());
		ehfPanelDocPayments.setTdsTaxAmt(getDocListDtls.getTaxAmt());
		ehfPanelDocPayments.setPmntStatus((String)lparamMap.get("CLAIM_STAT_ID"));
		ehfPanelDocPayments.setScheme((String)lparamMap.get("SCHEME_ID"));
		ehfPanelDocPayments = genericDao.save(ehfPanelDocPayments);
		
		
		transactionVO.setTransactionId(getDocListDtls.getCaseSetId());
		transactionVO.setGrossAmount(getDocListDtls.getAMOUNT());
		transactionVO.setNetAmount(String.valueOf(getDocListDtls.getPanelDocAmt()));
		transactionVO.setTdsAmount(String.valueOf(getDocListDtls.getTDSAmt()));
		transactionVO.setCaseSetId(getDocListDtls.getCaseSetId());
		transactionVO.setDocId(docId);
		if(scheme.equals(PanelDocConstants.AP_State))
			transactionVO.setScheme(config.getString("FIN.EHFAPSchemeID"));
		else if(scheme.equals(PanelDocConstants.TG_State))
			transactionVO.setScheme(config.getString("FIN.EHFTGSchemeID"));
		else
			transactionVO.setScheme(config.getString("FIN.EHF"));
		isexecuted=true;
		if(isexecuted)
		{
			caseList=getAllCaseList(docId,scheme,wId);
			lparamMap.put("PmntId", getDocListDtls.getCaseSetId());
			lparamMap.put("CaseList", caseList);
			updatePnlDocCaseDtls(lparamMap);
			submitPnlDocPaymentsInAccounts(transactionVO);
		}
		}
		catch (Exception e) {
//			logger.error("Error occured in createPanelDocPmnt() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return 1;
	}

	

	
	private void submitPnlDocPaymentsInAccounts(TransactionVO transactionVO) throws ParseException{
		// TODO Auto-generated method stub
		try{
		String trans_id=transactionVO.getTransactionId();
		String uniqueTxn = getNextUniqSeqVal();
		transactionVO.setUniqueTxn(uniqueTxn);
		transactionVO.setDebtAccount(config.getString("FIN.REGPNLDOCACCOUNT"));
		transactionVO.setCreditAccount(config.getString("FIN.PANELDOC").concat(transactionVO.getDocId()));
		transactionVO.setVoucherType(config.getString("FIN.Journal"));
		transactionVO.setAmount(transactionVO.getGrossAmount());
		transactionVO.setFlag(config.getString("FIN.JV"));
		transactionVO.setNarration("Panel Doctor Payment");
		transactionVO.setTransactionType("Gross Payment");
		transactionVO = insertRecord(transactionVO);
		
		transactionVO.setUniqueTxn(uniqueTxn);
		String setId=transactionVO.getTransactionId().substring( 0, transactionVO.getTransactionId().lastIndexOf ( "/P" ));
		transactionVO.setTransactionId(setId+PanelDocConstants.SLASH_PTDS);
		transactionVO.setDebtAccount(config.getString("FIN.PANELDOC").concat(transactionVO.getDocId()));
		transactionVO.setCreditAccount(config.getString("FIN.REMTDSACCOUNT"));
		transactionVO.setVoucherType(config.getString("FIN.Journal"));
		transactionVO.setAmount(transactionVO.getTdsAmount());
		transactionVO.setFlag(config.getString("FIN.JV"));
		transactionVO.setNarration("Panel Doctor Payment");
		transactionVO.setTransactionType("TDS Payment");
		transactionVO.setSection(config.getString("FIN.TDSSECTION"));
		transactionVO = insertRecord(transactionVO);
		
		transactionVO.setUniqueTxn(uniqueTxn);
		transactionVO.setTransactionId(trans_id);
		transactionVO.setDebtAccount(config.getString("FIN.PANELDOC").concat(transactionVO.getDocId()));
		if(transactionVO.getScheme().equals(config.getString("FIN.EHFAPSchemeID")))
			transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
		else if(transactionVO.getScheme().equals(config.getString("FIN.EHFTGSchemeID")))
			transactionVO.setCreditAccount(config.getString("FIN.MAIN-2316"));
		else
			transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
		transactionVO.setVoucherType(config.getString("FIN.Payment"));
		transactionVO.setAmount(transactionVO.getNetAmount());
		transactionVO.setFlag(config.getString("FIN.P"));
		transactionVO.setNarration("Panel Doctor Payment");
		transactionVO.setTransactionType("PD Payment");
		transactionVO.setSection("");
		transactionVO = insertRecord(transactionVO);
		
		transactionVO.setUniqueTxn(uniqueTxn);
		String setIdNew=transactionVO.getTransactionId().substring( 0, transactionVO.getTransactionId().lastIndexOf ( "/P" ));
		transactionVO.setTransactionId(setIdNew+PanelDocConstants.SLASH_PTDS);
		transactionVO.setDebtAccount(config.getString("FIN.REMTDSACCOUNT"));
		if(transactionVO.getScheme().equals(config.getString("FIN.EHFAPSchemeID")))
			transactionVO.setCreditAccount(config.getString("FIN.TDS-2307"));
		else if(transactionVO.getScheme().equals(config.getString("FIN.EHFTGSchemeID")))
			transactionVO.setCreditAccount(config.getString("FIN.TDS-2322"));
		else
			transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
		transactionVO.setVoucherType(config.getString("FIN.Payment"));
		transactionVO.setAmount(transactionVO.getTdsAmount());
		transactionVO.setFlag(config.getString("FIN.P"));
		transactionVO.setNarration("TDS");
		transactionVO.setTransactionType("TDS Payment");
		transactionVO.setSection(config.getString("FIN.TDSSECTION"));
		transactionVO = insertRecord(transactionVO);
		
		}
		catch (Exception e) {
//			logger.error("Error occured in submitPnlDocPaymentsInAccounts() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		
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
	private TransactionVO insertRecord(TransactionVO transactionVO)	throws ParseException {
		try{
		EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();

		ehfAcctsTransactionDtlsEntry
				.setVoucherId(getNextTransSeqVal(transactionVO.getFlag()));
		ehfAcctsTransactionDtlsEntry.setTransId(transactionVO.getTransactionId());
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
		//ehfAcctsTransactionDtlsEntry.setScheme(config.getString("FIN.EHF"));
		ehfAcctsTransactionDtlsEntry.setScheme(transactionVO.getScheme());
		ehfAcctsTransactionDtlsEntry.setSection(transactionVO.getSection());
		ehfAcctsTransactionDtlsEntry.setCaseId(transactionVO.getCaseSetId());
		ehfAcctsTransactionDtlsEntry.setCrtDt(new Date());
		if(transactionVO.getScheme().equalsIgnoreCase(config.getString("FIN.EHFAPSchemeID")))
				ehfAcctsTransactionDtlsEntry.setCrtUsr(PanelDocConstants.AP_CEO_USRID);
		else if(transactionVO.getScheme().equalsIgnoreCase(config.getString("FIN.EHFTGSchemeID")))
			ehfAcctsTransactionDtlsEntry.setCrtUsr(PanelDocConstants.AP_TG_USRID);
		else
			ehfAcctsTransactionDtlsEntry.setCrtUsr(config.getString("FIN.TCS"));
		ehfAcctsTransactionDtlsEntry.setUniqueTxn(transactionVO.getUniqueTxn());
		 if(transactionVO.getActiveYn()!=null && (PanelDocConstants.RejectFlag).equals(transactionVO.getActiveYn()))
			 ehfAcctsTransactionDtlsEntry.setActive_yn(transactionVO.getActiveYn());
		 else
		ehfAcctsTransactionDtlsEntry.setActive_yn(PanelDocConstants.Y);

		ehfAcctsTransactionDtlsEntry = genericDao.save(ehfAcctsTransactionDtlsEntry);
		if (ehfAcctsTransactionDtlsEntry != null) {
			transactionVO.setResult(config.getString("FIN.TransSuccess")+ehfAcctsTransactionDtlsEntry.getVoucherId());
		} else {
			transactionVO.setResult(config.getString("FIN.TransFailure"));
		}
		}
		catch (Exception e) {
//			logger.error("Error occured in insertRecord() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return transactionVO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map PaymentFileCreation(Map lparamMap) {
		List<PanelDocPayVO> getDocDetails=(List<PanelDocPayVO>) lparamMap.get("allDocDetails");
		PanelDocPayVO allDocDetails=new PanelDocPayVO() ;
		Map lFileMap = new HashMap();
		List FileMap=new ArrayList();
		List lCaseList=new ArrayList();
		for(int i=0;i<getDocDetails.size();i++)
		{
			allDocDetails=getDocDetails.get(i);
			lFileMap = new HashMap();
			lCaseList.add(allDocDetails.getCaseSetId());
			lFileMap.put("UNIQUE_ID", allDocDetails.getCaseSetId());
			lFileMap.put("BENEFICIARY_ACCOUNT_NAME",allDocDetails.getACCNAME());
			lFileMap.put("BENEFICIARY_ID",allDocDetails.getDOC_ID());
			lFileMap.put("BENEFICIARY_ADDR",allDocDetails.getEMPNAME());
			lFileMap.put("BENEFICIARY_BANK_NAME",allDocDetails.getBANKNAME());
			lFileMap.put("BANK_BRANCH",allDocDetails.getBANKBRANCH());
			lFileMap.put("BENEFICIARY_ACCOUNT_NO",allDocDetails.getACCOUNTNO());
			Double amt=allDocDetails.getPanelDocAmt();
			lFileMap.put("TRANSACTION_AMOUNT",String.valueOf(amt.intValue()));
			lFileMap.put("BENEFICIARY_BANK_ID",allDocDetails.getBANKID());
			lFileMap.put("BENEFICIARY_BANK_IFC_CODE",allDocDetails.getBANKIFSC());
			lFileMap.put("CLAINT_AC_CODE",lparamMap.get("CLIENT_AC_CODE"));
			lFileMap.put("CLAINT_AC_NUMBER", lparamMap.get("Client_ACC_No"));
			lFileMap.put("CLIENT_IFSC_CODE",lparamMap.get("CLIENT_IFSC_CODE"));
			lFileMap.put("TRANSACTION_TYPE",allDocDetails.getBANKCATEGORY());
			lFileMap.put("EMAIL_ID",PanelDocConstants.CEO_MAILID);
			
			FileMap.add(lFileMap);
		}
		lparamMap.put("CaseList", lCaseList);
		lparamMap.put("FileMap", FileMap);
		return lparamMap;
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private boolean updatePnlDocCaseDtls(Map lparamMap) {
		boolean isexec=false;
		List<PanelDocPayVO> caseDtlList=new ArrayList<PanelDocPayVO>();
		PanelDocPayVO workFlwDetails=new PanelDocPayVO();
		caseDtlList= (List<PanelDocPayVO>) lparamMap.get("CaseList");
		workFlwDetails= (PanelDocPayVO) lparamMap.get("panelDocPayVO");
		EhfPanelDocCaseDtls ehfPanelDocCaseDtls=new EhfPanelDocCaseDtls();
		EhfPnlDocWrkflow ehfPnlDocWrkflow=new EhfPnlDocWrkflow();
		
		try{
			for(int i=0;i<caseDtlList.size();i++)
			{
				//to save in workflow
				long wId=Long.parseLong(caseDtlList.get(i).getWRKFLWSETID());
				String docId=caseDtlList.get(i).getDOC_ID();
				ehfPnlDocWrkflow=genericDao.findById(EhfPnlDocWrkflow.class, EhfPnlDocWrkFlowId.class, new EhfPnlDocWrkFlowId(wId,docId));
				caseDtlList.get(i).setCASE_DATE(df.format(ehfPnlDocWrkflow.getLstUpdDt()));
				ehfPnlDocWrkflow.setPrevWrkflwId(workFlwDetails.getPrevWrkflowId());
				ehfPnlDocWrkflow.setPrevOwnrGrp(config.getString("FIN.CEOGrp"));
				ehfPnlDocWrkflow.setCurrWrkflwId(workFlwDetails.getCurrWrkflowId());
				ehfPnlDocWrkflow.setCurrOwnrGrp("");
				ehfPnlDocWrkflow.setStatusFlg(config.getString("FIN.InProgress"));
				ehfPnlDocWrkflow.setLstUpdDt(new Date());
				ehfPnlDocWrkflow.setLstUpdUsr((String)lparamMap.get("CRTUSER"));
				caseDtlList.get(i).setUserId((String)lparamMap.get("CRTUSER"));
				caseDtlList.get(i).setID(caseDtlList.get(i).getWRKFLWSETID());
				caseDtlList.get(i).setDOC_ID(docId);
				caseDtlList.get(i).setFILEID((String)lparamMap.get("FILENAME"));
				ehfPnlDocWrkflow=genericDao.save(ehfPnlDocWrkflow);
				caseDtlList.get(i).setPrevWrkflowId(workFlwDetails.getPrevWrkflowId());
				updateAudit(caseDtlList.get(i));
				List<EhfPanelDocCaseDtls> lstehfPanelDocCaseDtls=new ArrayList<EhfPanelDocCaseDtls>();
				Long id=Long.parseLong(caseDtlList.get(i).getWRKFLWSETID());
				List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("workFlwId", id,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				lstehfPanelDocCaseDtls=genericDao.findAllByCriteria(EhfPanelDocCaseDtls.class, criteriaList);
				//ehfPanelDocCaseDtls=genericDao.findById(EhfPanelDocCaseDtls.class, Long.class, id);
				for(int k=0;k<lstehfPanelDocCaseDtls.size();k++){
					long pID=lstehfPanelDocCaseDtls.get(k).getId();
			    ehfPanelDocCaseDtls=genericDao.findById(EhfPanelDocCaseDtls.class, Long.class, pID);
				ehfPanelDocCaseDtls.setPmntId((String)lparamMap.get("PmntId"));
				ehfPanelDocCaseDtls.setPnlDocPmntStatus((String)lparamMap.get("CLAIM_STAT_ID"));
				ehfPanelDocCaseDtls.setLstUpdDt(new Date());
				ehfPanelDocCaseDtls.setLstUpdUsr((String)lparamMap.get("CRTUSER"));
				ehfPanelDocCaseDtls=genericDao.save(ehfPanelDocCaseDtls);
				}
				isexec=true;
			}
		}
		catch (Exception e) {
//			logger.error("Error occured in updatePnlDocCaseDtls() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return isexec;
	}

	
@Transactional
	private void updateAudit(PanelDocPayVO caseDtlList) {
		long id=0;	
		EhfPanelDocAuditDtls ehfPanelDocAuditDtls=new EhfPanelDocAuditDtls();
		EhfPanelDocAuditDtlsId ehfPanelDocAuditDtlsId=new EhfPanelDocAuditDtlsId();
		//DateFormat df = new SimpleDateFormat(config.getString("FIN.DateFormat1"));
		
		try{
		
			id=Long.parseLong(caseDtlList.getID());
			String docId=caseDtlList.getDOC_ID();
			ehfPanelDocAuditDtlsId.setPmtId(id);
			ehfPanelDocAuditDtls.setDoc_id(docId);
			List<EhfPanelDocAuditDtls> lstEhfPanelDocAuditDtls=null;
			List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.pmtId", id,
			GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.actOrder", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
			lstEhfPanelDocAuditDtls=genericDao.findAllByCriteria(EhfPanelDocAuditDtls.class, criteriaList);
			criteriaList.removeAll(criteriaList);
			 if(lstEhfPanelDocAuditDtls != null && lstEhfPanelDocAuditDtls.size() > 0) {
				 Long actOrder=lstEhfPanelDocAuditDtls.get(0).getId().getActOrder();
				 ehfPanelDocAuditDtlsId.setActOrder(actOrder+1);
			 }
			 else{
				 ehfPanelDocAuditDtlsId.setActOrder(Long.parseLong(config.getString("FIN.One"))); 
				 }
			 if(caseDtlList.getPrevWrkflowId()!=null && !"".equals(caseDtlList.getPrevWrkflowId()))
			 {
				 ehfPanelDocAuditDtls.setCurrWrkflwId(caseDtlList.getPrevWrkflowId());
			 }
			ehfPanelDocAuditDtls.setAssigndDt(df.parse(caseDtlList.getCASE_DATE()));
			ehfPanelDocAuditDtls.setActedBy(caseDtlList.getUserId());
			ehfPanelDocAuditDtls.setActDt(new Date());
			ehfPanelDocAuditDtls.setId(ehfPanelDocAuditDtlsId);
			if(caseDtlList.getRemarks()!=null && !"".equals(caseDtlList.getRemarks()))
			{
				ehfPanelDocAuditDtls.setRemarks(caseDtlList.getRemarks());
			}
			
			if(caseDtlList.getREGCODE()!=null && (!"".equals(caseDtlList.getREGCODE())) && (!" ".equals(caseDtlList.getREGCODE())))
			{
				ehfPanelDocAuditDtls.setRegCode(caseDtlList.getREGCODE());
				ehfPanelDocAuditDtls.setCurrWrkflwId(config.getString("FIN.WorkFlowIdRej"));
			}
			if(caseDtlList.getFILEID()!=null && !"".equals(caseDtlList.getFILEID()))
			{
				ehfPanelDocAuditDtls.setFileId(caseDtlList.getFILEID());
			}
			if(caseDtlList.getPaymentUID()!=null && !"".equals(caseDtlList.getPaymentUID()))
			{
				ehfPanelDocAuditDtls.setPaymentUID(caseDtlList.getPaymentUID());
			}
			ehfPanelDocAuditDtls = genericDao.save(ehfPanelDocAuditDtls);
		
		}
		
		catch (Exception e) {
//			logger.error("Error occured in updateAudit() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private List<PanelDocPayVO> getAllCaseList(String docId,String scheme,String wId) {
		
		StringBuffer query= new StringBuffer();
		String[] args = new String[4];
		List<PanelDocPayVO> caseList=new ArrayList<PanelDocPayVO>();
		try{
			args[0]=config.getString("FIN.CEOGrp");
			args[1]=docId;
			args[2]=scheme;
			args[3]=wId;
			query.append(" select distinct(pw.w_id)||'' WRKFLWSETID,pc.doc_id DOC_ID,TO_CHAR(pw.lst_upd_dt, 'DD/MM/YYYY HH:mm:ss')||'' CASE_DATE from ehf_pnldoc_case_dtls pc,ehf_pnldoc_workflow pw ");
			query.append(" where pc.wrkflw_id=pw.w_id and pw.current_owner_grp=? and pc.doc_id=? and pw.scheme=? and pw.w_id=?");
			caseList=genericDao.executeSQLQueryList(PanelDocPayVO.class, query.toString(),args);
			
		}
		catch (Exception e) {
//			logger.error("Error occured in getCaseDetails() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return caseList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ArrayList insertUploadFile(HashMap lparamMap) throws Exception {
		byte[] lFileBytes = null;
		boolean fileCreated = false;
		String lStrFileName = PanelDocConstants.EMPTY;
		String lStrCrtUsr = PanelDocConstants.EMPTY;
		String lStrFilePath = PanelDocConstants.EMPTY;
		String lStrSharePath = PanelDocConstants.EMPTY;
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

			EhfClaimUploadFile ehfClaimUploadFile = null;

			ehfClaimUploadFile = genericDao.findById(EhfClaimUploadFile.class,
					String.class, lStrFileName);
			if (ehfClaimUploadFile != null) {
				logger.info("Already data avaiblabe with same filename in EHF_CLAIM_UPLOAD_FILE table");
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
				if(lparamMap.get("SCHEME_ID")!=null )
					{
						String schemeId=(String)lparamMap.get("SCHEME_ID");
						if(schemeId.equalsIgnoreCase(config.getString("AP")))
							fileCreated=true;
						else
							fileCreated = insertFileInTemp(lStrFileName, lFileBytes);		
					}
				
				if (fileCreated == true) {
					liResult = 1;
				}
			}
		}
		resultList.add(0, Integer.toString(liResult));
		return resultList;
	}


	@SuppressWarnings("rawtypes")
	@Transactional
	public int getSetId(HashMap lparamMap) throws Exception {
		int lSet_id = 0;
		List<PanelDocPayVO> maxSetList = new ArrayList<PanelDocPayVO>();
		// Getting Max Set_id value from EhfClaimUploadFile to update this
		// max value to corresponding file
		StringBuffer lStrBuffer = new StringBuffer();
		lStrBuffer.append("select (nvl(max(setId),0)+1)||'' as ID from EhfClaimUploadFile where fileStatus=? ");

		String arg[] = new String[1];
		arg[0] = (String) lparamMap.get("SentStatus");
		maxSetList = genericDao.executeHQLQueryList(PanelDocPayVO.class,
				lStrBuffer.toString(), arg);
		if (maxSetList.size() > 0)
			lSet_id = Integer.parseInt(maxSetList.get(0).getID()
					.toString());

		return lSet_id;
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
		String PaymentTempPath = config.getString("FIN.MapPath")
				+ config.getString("FIN.claimPayment_filePath")
				+ config.getString("FIN.claimPKIInput_filePath");
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
	
	private String getCaseSetId(String schemeCode) {
		
		StringBuffer query= new StringBuffer();
		String setIdSeq=null;
		try{
		List<LabelBean> seqList = new ArrayList<LabelBean>();
		if(schemeCode.equals(PanelDocConstants.AP_State_Code))
		query.append("select PANEL_DOC_CASESET_SEQ_AP.NEXTVAL||'' ID from dual ");
		else if(schemeCode.equals(PanelDocConstants.TG_State_Code))
			query.append("select PANEL_DOC_CASESET_SEQ_TG.NEXTVAL||'' ID from dual ");	
		seqList= genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		
		if(seqList!= null && seqList.size()>0)
			setIdSeq = schemeCode+seqList.get(0).getID();
		}
		catch (Exception e) {
//			logger.error("Error occured in getCaseSetId() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return setIdSeq;
		
		}
	
	
private String getPmntUpldId(String scheme) {
		
		StringBuffer query= new StringBuffer();
		String setIdSeq=null;
		try{
		List<LabelBean> seqList = new ArrayList<LabelBean>();
		if(scheme.equals(PanelDocConstants.AP_State))
		query.append("select PANELDOC_PMNT_UPLD_SEQ_AP.NEXTVAL||'' ID from dual ");
		else if(scheme.equals(PanelDocConstants.TG_State))
			query.append("select PANELDOC_PMNT_UPLD_SEQ_TG.NEXTVAL||'' ID from dual ");
		seqList= genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		
		if(seqList!= null && seqList.size()>0)
			setIdSeq = seqList.get(0).getID();
		}
		catch (Exception e) {
//			logger.error("Error occured in getPmntUpldId() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
		return setIdSeq;
		
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
	if (transType.equals(config.getString("FIN.P"))) {
		query.append("select LPAD(ACC_PAYMENT_SEQ.NEXTVAL,8,'0') ID from dual ");
	} else if (transType.equals(config.getString("FIN.R"))) {
		query.append("select  LPAD(ACC_RECEIPT_SEQ.NEXTVAL,8,'0') ID from dual ");
	} else if (transType.equals(config.getString("FIN.JV"))) {
		query.append("select LPAD(ACC_JOURNAL_SEQ.NEXTVAL,8,'0') ID from dual ");
	} else if (transType.equals(config.getString("FIN.CV"))) {
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

@Override
public ArrayList updateTdsPayments(HashMap hParamMap) {
	TransactionVO transactionVO=new TransactionVO();
	try{
		PanelDocPayVO panelDocPayVO=(PanelDocPayVO) hParamMap.get("panelDocPayVO");
		String selectedCondition = " and u.user_id in ( " ;
		String caseId=panelDocPayVO.getCASE_ID();
		
		caseId=caseId.replace("[~", "");
		List<String> checkdDocList=Arrays.asList(caseId.split("~"));
        String caseList = PanelDocConstants.EMPTY ;
        for ( String doctorId:checkdDocList )
        {
        	caseList = caseList + PanelDocConstants.SINGLE_QUOTE + doctorId + PanelDocConstants.SINGLE_QUOTE_COMA ;
        }
        caseList = caseList.substring ( 0, caseList.lastIndexOf ( PanelDocConstants.COMA ) ) ;
        selectedCondition = selectedCondition + caseList + " )" ;
	}
        catch (Exception e) {
//			logger.error("Error occured in getCaseSetId() in PaymentsDAOImpl class."
//					+ e.getMessage());
			e.printStackTrace();
		}
	
	return null;

}

/**
 * To get TDS/RF Accounts Details.
 * 
 * @param HashMap
 *            the lmap
 * @return HashMap
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })

public HashMap payTDSFund(HashMap lmap) {
	logger.info("Start:payTDSFund method in PaymentsDAOImpl");
	double lFlagVal = PanelDocConstants.ZERO_DBL_VAL, TrustPctAmt = PanelDocConstants.ZERO_DBL_VAL, tdsAmt = PanelDocConstants.ZERO_DBL_VAL, amount = PanelDocConstants.ZERO_DBL_VAL;
	String lbeneficiary_account_name = PanelDocConstants.EMPTY, lbeneficiary_id = PanelDocConstants.EMPTY, lbeneficiary_addr = PanelDocConstants.EMPTY, lbeneficiary_bank_name = PanelDocConstants.EMPTY;
	String lbank_branch = PanelDocConstants.EMPTY, lbeneficiary_account_no = PanelDocConstants.EMPTY, lbeneficiary_bank_id = PanelDocConstants.EMPTY, lbeneficiary_bank_ifc_code = PanelDocConstants.EMPTY;
	String lclaint_ac_code = PanelDocConstants.EMPTY, lclaint_ac_number = PanelDocConstants.EMPTY, ltransaction_type = PanelDocConstants.EMPTY, caseStatus = PanelDocConstants.EMPTY, paymentType = PanelDocConstants.EMPTY, actType = PanelDocConstants.EMPTY, eMail = PanelDocConstants.EMPTY;
	HashMap lresultMap = new HashMap();
	StringBuffer query = null;String oldCaseStatus="";
	ArrayList lFileDataList = null;
	ArrayList lCaseList = null;
	ArrayList lFileList = null;
	HashMap lFileMap = null;
	String scheme=null;
		
	List<PanelDocPayVO> accountList = new ArrayList<PanelDocPayVO>();

	if (lmap.get("TdsAmount") != null)
		tdsAmt = Double.parseDouble(lmap.get("TdsAmount").toString());

	if (lmap.get("FileMap") != null)
		lFileMap = (HashMap) lmap.get("FileMap");

	if (lFileMap.get("CaseStatus") != null)
		caseStatus = (String) lFileMap.get("CaseStatus");
	
	if (lFileMap.get("OldCaseStatus") != null)
		oldCaseStatus = (String) lFileMap.get("OldCaseStatus");
	
	if (lFileMap.get("EMAIL_ID") != null)
		eMail = (String) lFileMap.get("EMAIL_ID");

	scheme=(String) lFileMap.get("SCHEME_ID");
	boolean status_Cond = (!oldCaseStatus.equals(PanelDocConstants.CLAIM_REJ_BANK));
	
           
	if (lFileMap.get("CaseList") != null)
		lCaseList = (ArrayList) lFileMap.get("CaseList");

	if (lFileMap.get("FileDataList") != null)
		lFileDataList = (ArrayList) lFileMap.get("FileDataList");

	String lStrUniqueId = PanelDocConstants.EMPTY;
	String caseid = (String) lFileMap.get("UNIQUE_ID");

	if(scheme.equals(PanelDocConstants.AP_State))
		actType = PanelDocConstants.TDSEHS_AP;
	else if(scheme.equals(PanelDocConstants.TG_State))
			actType = PanelDocConstants.TDSEHS_TG;
			amount = tdsAmt;
			lStrUniqueId = caseid;// + PanelDocConstants.SLASH_TDS;
		
       
		     lFlagVal = PanelDocConstants.ZERO_VAL;

			if (( tdsAmt > PanelDocConstants.ZERO_VAL) && (status_Cond))
			{
								//getting bank accounts details for TDS
				query = new StringBuffer();
				query.append(" select id TDSID, t.account_no ACCOUNTNO,t.name_Asper_Act ACCNAME,t.address ADDRESS,");
				query.append(" t.bank_Id BANKID,b.ifc_Code BANKIFSC,b.bank_Name BANKNAME,b.bank_Branch BANKBRANCH, b.bank_Category BANKCATEGORY");
				query.append(" FROM EHFM_TRUST_ACT_DTLS t,EHFM_BANK_MASTER b where t.bank_Id = b.bank_Id  and t.act_Type = ? and t.active_Yn = ? and t.scheme_id=?");
				String[] arg = new String[3];
				arg[0] = actType;
				arg[1] = PanelDocConstants.Y;
				arg[2]=scheme;
				accountList = genericDao.executeSQLQueryList(
						PanelDocPayVO.class, query.toString(), arg);
				if (accountList.size() > 0) {
					for (int i = 0; i < accountList.size(); i++) {
						if (accountList.get(i).getACCNAME() != null
								&& accountList.get(i).getACCNAME() != PanelDocConstants.EMPTY) {
							lbeneficiary_account_name = accountList.get(i)
									.getACCNAME().toString();
							lbeneficiary_id = accountList.get(i)
									.getTDSID();
							lbeneficiary_addr = accountList.get(i)
									.getADDRESS();
							lbeneficiary_bank_name = accountList.get(i)
									.getBANKNAME();
							lbank_branch = accountList.get(i)
									.getBANKBRANCH();
							lbeneficiary_account_no = accountList.get(i)
									.getACCOUNTNO();
							lbeneficiary_bank_id = accountList.get(i)
									.getBANKID();
							lbeneficiary_bank_ifc_code = accountList.get(i)
									.getBANKIFSC();
							lclaint_ac_code = (String) lFileMap
									.get("CLAINT_AC_CODE");
							lclaint_ac_number = (String) lFileMap
									.get("CLAINT_AC_NUMBER");
							ltransaction_type = accountList.get(i)
									.getBANKCATEGORY();
						}
					}
				} 
				
				 else {
					lFlagVal++;
				}
				if ((tdsAmt > PanelDocConstants.ZERO_VAL)
						&& lFlagVal == PanelDocConstants.ZERO_VAL) {
					lFileList = new ArrayList();
					lFileMap = new HashMap(); // List for generate file for
												// upload(which is having
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
							amount);
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

				}
			
			}

	lresultMap.put("CaseList", lCaseList);
	lresultMap.put("FileDataList", lFileDataList);
	logger.info("End:payTDSFund method in PaymentsDAOImpl");
	return lresultMap;
}

/**
 * Gets the tds account dtls.
 * @param tDSList 
 *
 * @param panelDocAllAmtDtls the lparam map
 * @return the tds account dtls
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public Map getTdsAccountDtlsNew(PanelDocPayVO tDSList, Map panelDocAllAmtDtls)// 126
{
	ArrayList lResList = null;
	List lFileDataList = null;
	List lCaseList = null;
	String scheme=null;
	String smsNextVal=null;
	String 	actType = null;
	Map lFileMap = new HashMap();
	PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
	StringBuffer query = null;
	String  lStrMailId = PanelDocConstants.EMPTY, lStrTdsType = PanelDocConstants.EMPTY, lStrFormPaymentType = PanelDocConstants.EMPTY, lStrTdsDeductor = PanelDocConstants.EMPTY;
	int lTotalCases = 0;
	List<PanelDocPayVO> accountList = new ArrayList<PanelDocPayVO>();
	double tdsAmt = 0;
	try {
		
		String lStrClientAcCode = null;
		//lStrFormPaymentType = (String) lparamMap.get("FormPaymentType");
		lStrTdsType = (String) panelDocAllAmtDtls.get("TdsType");
		lStrMailId = (String) panelDocAllAmtDtls.get("MailId");
		lStrTdsDeductor = (String) panelDocAllAmtDtls.get("TDS_CASE_TYPE");
		scheme=(String) panelDocAllAmtDtls.get("SCHEME_ID");
		if(scheme.equals(PanelDocConstants.AP_State)){
			lStrClientAcCode = PanelDocConstants.CLAIMTDS_CLIENT_CODE_AP;
		actType = PanelDocConstants.TDSEHS_AP;
		}
		else if(scheme.equals(PanelDocConstants.TG_State)){
		lStrClientAcCode = PanelDocConstants.CLAIMTDS_CLIENT_CODE_TG;
		actType = PanelDocConstants.TDSEHS_TG;
		}
		
		 query = new StringBuffer();
		 query.append(" select id TDSID, t.account_no ACCOUNTNO,t.name_Asper_Act ACCNAME,t.address ADDRESS,");
			query.append(" t.bank_Id BANKID,b.ifc_Code BANKIFSC,b.bank_Name BANKNAME,b.bank_Branch BANKBRANCH, b.bank_Category BANKCATEGORY");
			query.append(" FROM EHFM_TRUST_ACT_DTLS t,EHFM_BANK_MASTER b where t.bank_Id = b.bank_Id  and t.act_Type = ? and t.active_Yn = ? and t.scheme_id=?");
			String[] arg = new String[3];
			arg[0] = actType;
			arg[1] = PanelDocConstants.Y;
			arg[2]=scheme;
			accountList = genericDao.executeSQLQueryList(
					PanelDocPayVO.class, query.toString(), arg);
		lFileMap = new HashMap();
		panelDocPayVO.setCaseSetId(tDSList.getTdsPaymentId());
		panelDocPayVO.setACCNAME(accountList.get(0).getACCNAME());
		panelDocPayVO.setDOC_ID(accountList.get(0).getTDSID());
		panelDocPayVO.setEMPNAME(accountList.get(0).getADDRESS());
		panelDocPayVO.setBANKNAME(accountList.get(0).getBANKNAME());
		panelDocPayVO.setBANKBRANCH(accountList.get(0).getBANKBRANCH());
		panelDocPayVO.setACCOUNTNO(accountList.get(0).getACCOUNTNO());
		panelDocPayVO.setBANKIFSC(accountList.get(0).getBANKIFSC());
		panelDocPayVO.setBANKID(accountList.get(0).getBANKID());
		panelDocPayVO.setPanelDocAmt(tDSList.getTDSAmt());
		panelDocPayVO.setBANKCATEGORY(accountList.get(0).getBANKCATEGORY());
		
		panelDocPayVO.setFlag("TDS");
		panelDocAllAmtDtls.put("TDSFILE", panelDocPayVO);
		panelDocAllAmtDtls.put("TDSACCNO", accountList.get(0).getACCOUNTNO());
		
	}
	catch (Exception le) {
		logger.error("Exception in method getTdsAccountDtls in PaymentsDAOImpl--"
				+ le.getMessage());
	}
		
	return panelDocAllAmtDtls;
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
	String scheme=null;
	String smsNextVal=null;
	Map lFileMap = new HashMap();
	
	StringBuffer query = null;
	String  lStrMailId = PanelDocConstants.EMPTY, lStrTdsType = PanelDocConstants.EMPTY, lStrFormPaymentType = PanelDocConstants.EMPTY, lStrTdsDeductor = PanelDocConstants.EMPTY;
	int lTotalCases = 0;
	List<PanelDocPayVO> accountList = new ArrayList<PanelDocPayVO>();
	double tdsAmt = 0;
	try {
		
		String lStrClientAcCode = null;
		//lStrFormPaymentType = (String) lparamMap.get("FormPaymentType");
		lStrTdsType = (String) lparamMap.get("TdsType");
		lStrMailId = (String) lparamMap.get("MailId");
		lStrTdsDeductor = (String) lparamMap.get("TDS_CASE_TYPE");
		scheme=(String) lparamMap.get("SCHEME_ID");
		if(scheme.equals(PanelDocConstants.AP_State))
			lStrClientAcCode = PanelDocConstants.CLAIMTDS_CLIENT_CODE_AP;
		else if(scheme.equals(PanelDocConstants.TG_State))
		lStrClientAcCode = PanelDocConstants.CLAIMTDS_CLIENT_CODE_TG;
		query = new StringBuffer();

		
		 query.append(" select tp.tds_payment_id CASE_ID,tp.claim_amount||'' AMOUNT,pp.src_acct_no SRCACCNO");
		 query.append(" from ehf_pnldoc_tds_payment tp,ehf_pnldoc_payments pp ");
		 query.append(" where pp.cases_set_id=tp.case_set_id and tp.payment_status=? and tp.payment_Check=? and tp.scheme=?");
		 
			
		
		String[] arg = new String[3];
		arg[0] = PanelDocConstants.TempSentStatus;
		arg[1] = PanelDocConstants.T;
		arg[2]=scheme;
		accountList = genericDao.executeSQLQueryList(PanelDocPayVO.class,query.toString(), arg);
		lparamMap.put("TDS_STAT_ID", PanelDocConstants.SENT_TO_BANK);

		lFileDataList = new ArrayList();
		lResList = new ArrayList();
		lCaseList = new ArrayList();
		
		for (PanelDocPayVO panelDocVO : accountList) {
			lFileMap = new HashMap();

			lFileMap.put("UNIQUE_ID", panelDocVO.getCASE_ID());
			// lCaseList.add(claimsVO.getTdsPaymentId());
			lFileMap.put("CLAINT_AC_CODE", lStrClientAcCode);
			lFileMap.put("CLAINT_AC_NUMBER", panelDocVO.getSRCACCNO());
			lFileMap.put("EMAIL_ID", lStrMailId);
			lFileMap.put("SCHEME_ID", scheme);
			// lFileDataList.add(lFileMap);
			// the TDS pct amount to be deducted and to be sent to a TDS
			// account
			tdsAmt = Double.parseDouble(panelDocVO.getAMOUNT());

			if (tdsAmt > 0) {
				HashMap ldataMap = new HashMap();
				ldataMap.put("TdsAmount", tdsAmt);
				lFileMap.put("CaseStatus",
						PanelDocConstants.CLAIM_READY_PAYMENT);
				lFileMap.put("PaymentType", "CD545");
				lFileMap.put("CaseList", lCaseList);
				lFileMap.put("FileDataList", lFileDataList);
				ldataMap.put("FileMap", lFileMap);
				
				// lFileMap.put("TdsAmount", tdsAmt);
				// lFileMap.put("TdsType", lStrTdsType);
				// lFileMap.put("FileDataList", lFileDataList);

				Map lresultMap = payTDSFund(ldataMap); 
				lCaseList = (ArrayList) lresultMap.get("CaseList");
				lFileDataList = (ArrayList) lresultMap.get("FileDataList");
			}

			lTotalCases++;
		}
		lResList.add(lFileDataList);
		if(scheme.equals(PanelDocConstants.AP_State))
		smsNextVal = getSequence("CLAIMUPLOADFILE_AP");
		else if(scheme.equals(PanelDocConstants.TG_State))
		smsNextVal = getSequence("CLAIMUPLOADFILE_TG");
		else
			smsNextVal = getSequence("CLAIMUPLOADFILE");
		lResList.add(smsNextVal);
		lResList.add(lCaseList);
	} catch (Exception le) {
		logger.error("Exception in method getTdsAccountDtls in PaymentsDAOImpl--"
				+ le.getMessage());
	}

	return lResList;
}

/**
 * Insert record into Accounts table for TDS and RF
 * 
 * @return TransactionVO
 * @throws ParseException
 *             the parse exception
 */
public TransactionVO submitTdsOrRfPaymentsInAccounts(TransactionVO transactionVO) throws ParseException {
	try{
	String uniqueTxn = getNextUniqSeqVal();
	transactionVO.setUniqueTxn(uniqueTxn);
	transactionVO.setTransactionId(transactionVO.getTdsId());
	
	if(transactionVO.getScheme().equals(config.getString("FIN.EHFAPSchemeID"))){
		transactionVO.setDebtAccount(config.getString("FIN.TDS-2307"));
		transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
	}
	else if(transactionVO.getScheme().equals(config.getString("FIN.EHFTGSchemeID"))){
		transactionVO.setDebtAccount(config.getString("FIN.TDS-2322"));
		transactionVO.setCreditAccount(config.getString("FIN.MAIN-2316"));
	}
	else{
		transactionVO.setDebtAccount(config.getString("FIN.TDS-2307"));
		transactionVO.setCreditAccount(config.getString("FIN.MAIN-2305"));
	}
	transactionVO.setVoucherType(config.getString("FIN.Contra"));
	transactionVO.setAmount(transactionVO.getTdsAmount());
	transactionVO.setFlag(config.getString("FIN.CV"));
	transactionVO.setSection(config.getString("FIN.TDSSECTION"));
	transactionVO = insertRecord(transactionVO);
	
	
	//Commented for not entering bank remunation TDS Amount
	/*if(transactionVO.getScheme().equals(config.getString("FIN.EHFAPSchemeID"))){
		transactionVO.setDebtAccount(config.getString("FIN.REMTDSACCOUNT"));
		transactionVO.setCreditAccount(config.getString("FIN.TDS-2307"));
		
	}
	else if(transactionVO.getScheme().equals(config.getString("FIN.EHFTGSchemeID"))){
		transactionVO.setDebtAccount(config.getString("FIN.REMTDSACCOUNT"));
		transactionVO.setCreditAccount(config.getString("FIN.TDS-2322"));
	}
	else{
		transactionVO.setDebtAccount(config.getString("FIN.REMTDSACCOUNT"));
		transactionVO.setCreditAccount(config.getString("FIN.TDS-2307"));
	}
	
	
	transactionVO.setVoucherType(config.getString("FIN.Payment"));
	transactionVO.setAmount(transactionVO.getTdsAmount());
	transactionVO.setFlag(config.getString("FIN.P"));
	transactionVO = insertRecord(transactionVO);*/
}
	catch (Exception le) {
		logger.error("Exception in method submitTdsOrRfPaymentsInAccounts in PaymentsDAOImpl--"
				+ le.getMessage());
	}
	return transactionVO;
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
//		    	logger.error("Exception in method getSequence in PaymentsDAOImpl--"
//		    			+ e.getMessage());
		    	e.printStackTrace();	    	
		    }	    
		    return lStrSeqRetVal;
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
public void setTDSAuditDetails(HashMap lparamMap) throws Exception {
	String lStrCaseNo = (String) lparamMap.get("TDS_PAYMENT_ID");
	String lStrActId = (String) lparamMap.get("TDS_STAT_ID");
	List<PanelDocPayVO> tdsAuditDtls = new ArrayList<PanelDocPayVO>();
	StringBuffer lQueryBuffer = new StringBuffer();
try{
	lQueryBuffer
			.append(" select max(au.id.actOrder)||'' as COUNT from EhfPnldocTdsAudit au where au.id.tdsPaymentId=? ");

	String[] arg = new String[1];
	arg[0] = lStrCaseNo;

	tdsAuditDtls = genericDao.executeHQLQueryList(PanelDocPayVO.class,lQueryBuffer.toString(), arg);
	
	Long lintActOrder = 1L;
	if (tdsAuditDtls != null && !tdsAuditDtls.isEmpty()
			&& tdsAuditDtls.get(0).getCOUNT() != null) {
		if (Long.parseLong(tdsAuditDtls.get(0).getCOUNT()) >= 0)
			lintActOrder = Long.parseLong(tdsAuditDtls.get(0).getCOUNT()) + 1;
	}

	EhfPnldocTdsAudit ehfTDSAudit = new EhfPnldocTdsAudit();
	EhfPnldocTdsAuditId ehfTDSAuditId = new EhfPnldocTdsAuditId();

	ehfTDSAuditId.setTdsPaymentId((String) lparamMap.get("TDS_PAYMENT_ID"));
	ehfTDSAuditId.setActOrder(lintActOrder);
	ehfTDSAudit.setId(ehfTDSAuditId);
	ehfTDSAudit.setActId(lStrActId);
	ehfTDSAudit.setActBy((String) lparamMap.get("CRTUSER"));
	ehfTDSAudit.setRemarks((String) lparamMap.get("TdsRemarks"));
	ehfTDSAudit.setCaseType((String) lparamMap.get("TDS_CASE_TYPE"));
	ehfTDSAudit.setCrtDt(new Timestamp(new Date().getTime()));
	ehfTDSAudit.setCrtUsr((String) lparamMap.get("CRTUSER"));
	ehfTDSAudit.setRejCode((String) lparamMap.get("REJ_CODE"));
	ehfTDSAudit.setLangId(PanelDocConstants.LANG_ID);
	ehfTDSAudit.setTransactionId((String) lparamMap.get("TRANS_ID"));
	if ((String) lparamMap.get("SBH_PAID_DATE") != null)
		ehfTDSAudit.setSbhPaidDate(sdf.parse((String) lparamMap
				.get("SBH_PAID_DATE")));
	if ((String) lparamMap.get("FILENAME") != null)
		ehfTDSAudit.setFilename((String) lparamMap
				.get("FILENAME"));
	ehfTDSAudit.setPaymentUid((String) lparamMap.get("PAYMENT_UID"));
	ehfTDSAudit = genericDao.save(ehfTDSAudit);
}
catch (Exception le) {
	logger.error("Exception in method setTDSAuditDetails in PaymentsDAOImpl--"
			+ le.getMessage());
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
public String SetPanelDocStatus(HashMap lparamMap) {
	String lResult = "False";
	ArrayList lDataList = new ArrayList();
	int i = 0, lFlag = 0, iStatus = 0, fileStatusConut = 0;
	String lStrCaseid = "", lStrACNo = "", lStrStatus = "", lStrTransDt = "", lStrTransId = "", lStrPaymentUid = null;
	String lStrClaimPreStatus = "", lStrClaimStatus = "", lStrRemarks = "", lStrRembsStatus = "", lStrPaidDate = null, lStrRejCode = null;
	String lstramount="";
	boolean charContains = false, charContainsTDS = true;
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
				lstramount = (String) lDataList.get(j + 7);
				double clmAmt=Double.parseDouble(lstramount);
				clmAmt=clmAmt/100;
				lstramount=String.valueOf(clmAmt);
				
				
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
			lparamMap.put("CLAIM_AMOUNT", lstramount);
            //For Claim Paid Status
			if (lStrStatus.equals("S00")) 
			{   //TDS
				if (lStrCaseid.endsWith("PTDS")) {
				
						lStrClaimStatus = (String) lparamMap.get("PnlDocPaidStatus");
						lparamMap.put("TDS_PAYMENT_ID", lStrCaseid);
					
				} 
				else{
					lStrClaimStatus=(String)lparamMap.get("PnlDocPaidStatus");
				}
				lStrRemarks = (String) lparamMap.get("ClaimPaidRemarks");
				//For Claim Rejected By Bank
			}
			
			else if (lStrStatus.contains("E")) // 035
			{   //TDS
				lStrClaimStatus = (String) lparamMap.get("PnlDocRejStatus");
					
				
			} else if (lStrStatus.equals("S01"))  //For Acknowledge Status
			{    //TDS
				lStrClaimStatus = (String) lparamMap.get("PnlDocAckStatus");
				lStrRemarks = (String) lparamMap.get("ClaimAckRemarks");
			} else {
				lFlag = 1;

			}
			
			lparamMap.put("REMARKS", lStrRemarks);
			lparamMap.put("TransStatus", lStrStatus);
			lparamMap.put("STAT_ID", lStrClaimStatus);
			if(lStrCaseid.endsWith("PTDS"))
			{
				lparamMap.put("TDS_STAT_ID", lStrClaimStatus);
				lparamMap.put("TdsRemarks", lStrRemarks);
				lparamMap.put("TDS_PAYMENT_ID", lStrCaseid);
			}
			

			if (lFlag != 1) {
				iStatus = 0;
				charContains = false;
				charContainsTDS = false;
				if (lStrTransDt.trim().length() > 1 && lStrTransDt != null) { 
																				
					
					if (lStrCaseid.endsWith(PanelDocConstants.PTDS)) {
						charContainsTDS = true;
					}
											
					if (charContainsTDS) {
						iStatus = updateTDSPaymentDetails(lparamMap);     //Updating TDS Details acc to Status
						if (iStatus > 0 && lStrStatus.contains("E"))
							updateAccountsTransaction(lStrCaseid,        //Reverting details in Accounts tables iF Rejected
									lStrTransDt,lstramount);
					}
					if (charContains == false && charContainsTDS == false) {
						iStatus = updatePanelDocPaymentDetails(lparamMap);        //Updating Claim Details acc to Status  
						if (iStatus > 0 && lStrStatus.contains("E"))
							updateAccountsTransaction(lStrCaseid,         //Reverting details in Accounts tables iF Rejected
									lStrTransDt,lstramount);
					}

				}

				if (iStatus > 0                              //&& (lStrCaseid.endsWith("R") == false)
						&& charContains == false
						&& charContainsTDS == false) {
					StringBuffer lStrBuffer = new StringBuffer();

					setCaseDtls(lparamMap);
					lResult = PanelDocConstants.TRUE;
				}
					  else if (iStatus > 0 && charContainsTDS == true) {
						  setTDSAuditDetails(lparamMap);
						lResult = PanelDocConstants.TRUE;
				}
				
			}

		}

		if (PanelDocConstants.TRUE.equalsIgnoreCase(lResult)) {
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
		e.printStackTrace();
//		logger.error("Error:SetPanelDocStatus method in PaymentsDAOImpl with message "+e);
	}

	return lResult;
} 

private void setCaseDtls(HashMap lparamMap) throws Exception {
	
	Long pId=0L;
	String caseId="";
	String caseDate="";
	String docId="";
	long wrkflwId=0;
	int count=0;
	List wrkFlwIdList=new ArrayList();
	List docIdList=new ArrayList();
	try{
	PanelDocPayVO auditDtls=new PanelDocPayVO();
	String lStrStatus = (String) lparamMap.get("STAT_ID");
	String transStatus=(String)lparamMap.get("TransStatus");
	String lStrPmntid = (String) lparamMap.get("CASE_ID");
	EhfPanelDocCaseDtls ehfPanelDocCase=null;
	List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	criteriaList.add(new GenericDAOQueryCriteria("pmntId", lStrPmntid,
			GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	List<EhfPanelDocCaseDtls> ehfCaseDtls = genericDao
			.findAllByCriteria(EhfPanelDocCaseDtls.class, criteriaList);
	if (ehfCaseDtls != null) {
	for(int i=0;i<ehfCaseDtls.size();i++)
	{
		    pId=ehfCaseDtls.get(i).getId();
		    caseId=ehfCaseDtls.get(i).getCaseId();
		    wrkflwId=ehfCaseDtls.get(i).getWorkFlwId();
		    docId=ehfCaseDtls.get(i).getDocId();
		    caseDate=df.format(ehfCaseDtls.get(i).getLstUpdDt()).toString();
		    ehfPanelDocCase = ehfCaseDtls.get(i);
			ehfPanelDocCase.setLstUpdDt(new Timestamp(new Date().getTime()));
			ehfPanelDocCase.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
			ehfPanelDocCase.setPnlDocPmntStatus(lStrStatus);
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
			ehfPanelDocCase.setPmntDate(sdf.parse((String) lparamMap.get("SBH_PAID_DATE")));
		
			if(!(wrkFlwIdList.contains(wrkflwId)))
			{
				wrkFlwIdList.add(count, wrkflwId);
				docIdList.add(count, docId);
				count++;
			}
			ehfPanelDocCase = genericDao.save(ehfPanelDocCase);
	}
	for(int i=0;i<wrkFlwIdList.size();i++){
		String FileNameNew="";
		String currWrkFlwId="";
				if(transStatus.equals("S01"))
					{
						currWrkFlwId=config.getString("FIN.WorkFlowIdAck");
						if(lparamMap.get("FileNameNew")!=null)
						FileNameNew=(String)lparamMap.get("FileNameNew");
					}
				else if(transStatus.equals("S00"))
					{
						currWrkFlwId=config.getString("FIN.WorkFlowIdPaid");
						if(lparamMap.get("FileNameNew")!=null)
							FileNameNew=(String)lparamMap.get("FileNameNew");
					}
				else if(transStatus.contains("E"))
					{
						currWrkFlwId=config.getString("FIN.WorkFlowIdRej");
						if(lparamMap.get("FileNameNew")!=null)
							FileNameNew=(String)lparamMap.get("FileNameNew");
					}
	long wId= Long.parseLong(wrkFlwIdList.get(i).toString());
	String doctor=(String) docIdList.get(i);
	
	if(FileNameNew!=null && !"".equalsIgnoreCase(FileNameNew))
		 auditDtls.setFILEID(FileNameNew);
	 auditDtls.setDOC_ID(doctor);
     auditDtls.setID(String.valueOf(wId));
     auditDtls.setCASE_DATE(caseDate);
	 auditDtls.setREGCODE((String) lparamMap.get("REJ_CODE"));
	 auditDtls.setRemarks((String) lparamMap.get("REMARKS"));
	 auditDtls.setUserId((String) lparamMap.get("UPD_USR"));
	 auditDtls.setPaymentUID((String) lparamMap.get("PAYMENT_UID"));
	 auditDtls.setPrevWrkflowId(currWrkFlwId);
	 updateAudit(auditDtls);
	 
	EhfPnlDocWrkflow ehfPnlDocWrkflow=new EhfPnlDocWrkflow();
	ehfPnlDocWrkflow=genericDao.findById(EhfPnlDocWrkflow.class, EhfPnlDocWrkFlowId.class, new EhfPnlDocWrkFlowId(wId,doctor));
	ehfPnlDocWrkflow.setPrevWrkflwId(config.getString("FIN.WorkFlowIdSent"));
	ehfPnlDocWrkflow.setCurrWrkflwId(currWrkFlwId);
	ehfPnlDocWrkflow.setLstUpdDt(new Date());
	ehfPnlDocWrkflow.setLstUpdUsr((String)lparamMap.get("UPD_USR"));
	ehfPnlDocWrkflow=genericDao.save(ehfPnlDocWrkflow);
	}	
	}
	}
	catch (Exception e) {
		e.printStackTrace();
//		logger.error("Error:setCaseDtls method in PaymentsDAOImpl with message "+e);
	}
}

 

/**
 * This Method is to update the Payment Details into ehf_pnldoc_tds_payment
 * 
 * @param lparamMap
 *            the lparam map
 * @return the int
 * @throws Exception
 *             the exception
 */
@SuppressWarnings("rawtypes")
public int updateTDSPaymentDetails(HashMap lparamMap) throws Exception {

	int iResult = 0;
	String lStrStatus = (String) lparamMap.get("TransStatus");
	long timeMillSec = 0;
	List<String> colValues = new ArrayList<String>();
	EhfPnldocTdsPayment ehfPnldocTdsPay = null;
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
		List<EhfPnldocTdsPayment> ehfPnldocTdsPayment = genericDao
				.findAllByCriteria(EhfPnldocTdsPayment.class, criteriaList);
		if (ehfPnldocTdsPayment.size() > 0) {

			ehfPnldocTdsPay = ehfPnldocTdsPayment.get(0);
			ehfPnldocTdsPay.setTransId((String) lparamMap.get("TRANS_ID"));
			if ((String) lparamMap.get("TRANS_DT") != null)
				ehfPnldocTdsPay.setTransDate(sdf.parse((String) lparamMap
						.get("TRANS_DT")));
			ehfPnldocTdsPay
					.setLastUpdUser((String) lparamMap.get("UPD_USR"));
			ehfPnldocTdsPay.setLastUpdDate(new Timestamp(new Date()
					.getTime()));
			ehfPnldocTdsPay.setRemarks((String) lparamMap.get("REMARKS"));
			ehfPnldocTdsPay.setTimeMilliSec(timeMillSec);
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				ehfPnldocTdsPay.setSbhPaidDate(sdf.parse((String) lparamMap
						.get("SBH_PAID_DATE")));
			ehfPnldocTdsPay.setPaymentStatus((String) lparamMap
					.get("STAT_ID"));

			if (lStrStatus.equals("S01")) {
				ehfPnldocTdsPay.setTransStatus((String) lparamMap.get("TransAckStatus"));
				ehfPnldocTdsPay.setTdsStatus(PanelDocConstants.CLAIM_ACK_REC);
			} else if (lStrStatus.equals("S00")) {
				ehfPnldocTdsPay.setTransStatus((String) lparamMap.get("TransPaidStatus"));
				ehfPnldocTdsPay.setTdsStatus(PanelDocConstants.CLAIM_PAID);
			} else if (lStrStatus.contains("E")) {
				ehfPnldocTdsPay.setTransStatus((String) lparamMap.get("TransRejStatus"));
				ehfPnldocTdsPay.setTdsStatus(PanelDocConstants.CLAIM_REJ_BANK);
			}
			ehfPnldocTdsPay = genericDao.save(ehfPnldocTdsPay);
			if (ehfPnldocTdsPay != null) {
				iResult = 1;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
//		logger.error("Error:updateTDSPaymentDetails method in PaymentsDAOImpl with message"+e);
	}
	return iResult;

}

/**
 * This method is used to update ehf_accts_transaction_dtls table if payment got rejected.
 * 
 * @param lStrCaseid
 *            the lStr caseid
 * @param lStrTransDt
 *            the lStrTrans dt
 */

private void updateAccountsTransaction(String lStrCaseid, String lStrTransDt,String lstramount) {
try{
	String trans_id=lStrCaseid;
	
	
			
	EhfAcctsTransactionDtls ehfAcctsTransactionDtlsEntry = new EhfAcctsTransactionDtls();
	
	EhfAcctsTransactionDtls ehfAcctsTransactionDtls=new EhfAcctsTransactionDtls();
	 List<EhfAcctsTransactionDtls> lStAcctsTransactionDtls=null;
	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
	 criteriaList.add(new GenericDAOQueryCriteria("transId", lStrCaseid,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 criteriaList.add(new GenericDAOQueryCriteria("active_yn", PanelDocConstants.RejectFlag,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	 criteriaList.add(new GenericDAOQueryCriteria("transDate", "",GenericDAOQueryCriteria.CriteriaOperator.DESC));
	 lStAcctsTransactionDtls=genericDao.findAllByCriteria(EhfAcctsTransactionDtls.class,criteriaList);
	 criteriaList.removeAll(criteriaList);	
	 if(lStAcctsTransactionDtls!=null && lStAcctsTransactionDtls.size() > 0){
		 String voucherID=lStAcctsTransactionDtls.get(0).getVoucherId();
		 ehfAcctsTransactionDtls=genericDao.findById(EhfAcctsTransactionDtls.class, String.class, voucherID);
		 ehfAcctsTransactionDtls.setTransDate(df.parse(lStrTransDt));
		 ehfAcctsTransactionDtls.setLstUpdDt(new Date());
		 ehfAcctsTransactionDtls.setLstUpdUsr("TCS");
		 ehfAcctsTransactionDtls=genericDao.save(ehfAcctsTransactionDtls);
	 }
	 else{  
	
	String uniqueTxn = getNextUniqSeqVal();
    ehfAcctsTransactionDtlsEntry.setVoucherId(getNextTransSeqVal(config.getString("FIN.R")));
	ehfAcctsTransactionDtlsEntry.setTransId(trans_id);
	if(trans_id.substring(0, 2).equals(PanelDocConstants.CASEAP)){
		ehfAcctsTransactionDtlsEntry.setScheme(config.getString("FIN.EHFAPSchemeID"));
		ehfAcctsTransactionDtlsEntry.setDebtAccount(config.getString("FIN.MAIN-2305"));
	}
	else if(trans_id.substring(0, 2).equals(PanelDocConstants.CASETG)){
		ehfAcctsTransactionDtlsEntry.setScheme(config.getString("FIN.EHFTGSchemeID"));
		ehfAcctsTransactionDtlsEntry.setDebtAccount(config.getString("FIN.MAIN-2316"));
	}
	else
	{
		ehfAcctsTransactionDtlsEntry.setScheme(config.getString("FIN.EHF"));
		ehfAcctsTransactionDtlsEntry.setDebtAccount(config.getString("FIN.MAIN-2305"));
	}
	
	ehfAcctsTransactionDtlsEntry.setCreditAccount(config.getString("FIN.REJGL"));
	ehfAcctsTransactionDtlsEntry.setAmount(Float.parseFloat(lstramount));
	ehfAcctsTransactionDtlsEntry.setNarration("Rejected");
	
	if(lStrTransDt!=null)
		ehfAcctsTransactionDtlsEntry.setTransDate(df.parse(lStrTransDt));
	else
	ehfAcctsTransactionDtlsEntry.setTransDate(new Date());
	
	ehfAcctsTransactionDtlsEntry.setPaymentType("PD Payment");
	ehfAcctsTransactionDtlsEntry.setVoucherType(config.getString("FIN.Receipt"));
	ehfAcctsTransactionDtlsEntry.setSection("");
	if (lStrCaseid.endsWith(PanelDocConstants.PTDS)){
		String caseSetId=trans_id.substring(0, trans_id.indexOf(PanelDocConstants.SLASH_PTDS));
		ehfAcctsTransactionDtlsEntry.setCaseId(caseSetId);
	}
	else{
		ehfAcctsTransactionDtlsEntry.setCaseId(trans_id);
	}
	ehfAcctsTransactionDtlsEntry.setCrtDt(new Date());
	ehfAcctsTransactionDtlsEntry.setCrtUsr(config.getString("FIN.TCS"));
	ehfAcctsTransactionDtlsEntry.setUniqueTxn(uniqueTxn);
	ehfAcctsTransactionDtlsEntry.setActive_yn(PanelDocConstants.RejectFlag);
	
	ehfAcctsTransactionDtlsEntry = genericDao.save(ehfAcctsTransactionDtlsEntry);
	 }
}
catch (Exception e) {
	e.printStackTrace();
//	logger.error("Error:updateAccountsTransaction method in PaymentsDAOImpl with message"+e);
}
}

/**
 * This Method is to update the Payment Details into PanelDoctor-Accounts Table
 * 
 * @param lparamMap
 *            the lparam map
 * @return the int
 * @throws Exception
 *             the exception
 */
@SuppressWarnings("rawtypes")
public int updatePanelDocPaymentDetails(HashMap lparamMap) throws Exception {
	int iResult = 0;
	
	String lStrStatus = (String) lparamMap.get("TransStatus");
	long timeMillSec = 0;
	String lStrCaseid = "";
	
	EhfPanelDocPayments ehfPanelDocAcc = new EhfPanelDocPayments();
	
	try {
		List<String> colValues = new ArrayList<String>();
		TimeStamp t = new TimeStamp();
		timeMillSec = t.getTimeStampFromDate((String) lparamMap.get("TRANS_DT"));
		
		lStrCaseid = (String) lparamMap.get("CASE_ID");

		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("caseSetId", lStrCaseid,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("timeInMilSec",timeMillSec, GenericDAOQueryCriteria.CriteriaOperator.LT));
		/*if (lStrStatus.equals("A")) {
			colValues.add((String) lparamMap.get("SentStatus"));
			colValues.add((String) lparamMap.get("TransRejStatus"));
			criteriaList.add(new GenericDAOQueryCriteria("transStatus",colValues,GenericDAOQueryCriteria.CriteriaOperator.IN));
		} else if (lStrStatus.equals("E")) {
			colValues.add((String) lparamMap.get("TransAckStatus"));
			colValues.add((String) lparamMap.get("SentStatus"));
			colValues.add((String) lparamMap.get("TransRejStatus"));
			criteriaList
					.add(new GenericDAOQueryCriteria("transStatus",colValues,GenericDAOQueryCriteria.CriteriaOperator.IN));
		} else if (lStrStatus.equals("R")) {
			colValues.add((String) lparamMap.get("TransAckStatus"));
			colValues.add((String) lparamMap.get("SentStatus"));
			colValues.add((String) lparamMap.get("TransPaidStatus"));
			colValues.add((String) lparamMap.get("TransRejStatus"));
			criteriaList
					.add(new GenericDAOQueryCriteria("transStatus",colValues,GenericDAOQueryCriteria.CriteriaOperator.IN));

		}*/
		List<EhfPanelDocPayments> ehfPanelDocPayments = genericDao.findAllByCriteria(EhfPanelDocPayments.class, criteriaList);
		if (ehfPanelDocPayments.size() > 0) {
			ehfPanelDocAcc = ehfPanelDocPayments.get(0);
			ehfPanelDocAcc.setTransId((String) lparamMap.get("TRANS_ID"));
				
			if ((String) lparamMap.get("TRANS_DT") != null)
				ehfPanelDocAcc.setTransDate(sdf.parse((String) lparamMap
						.get("TRANS_DT")));
			ehfPanelDocAcc.setLstUpdUsr((String) lparamMap.get("UPD_USR"));
			ehfPanelDocAcc.setLstUpdDt(new Timestamp(new Date().getTime()));
			ehfPanelDocAcc.setRemarks((String) lparamMap.get("REMARKS"));
			ehfPanelDocAcc.setTimeInMilSec(timeMillSec);
			if ((String) lparamMap.get("SBH_PAID_DATE") != null)
				ehfPanelDocAcc.setSbhPaidDate(sdf.parse((String) lparamMap.get("SBH_PAID_DATE")));

			if (lStrStatus.equals("S01")) {
				ehfPanelDocAcc.setTransStatus((String) lparamMap.get("TransAckStatus"));
				ehfPanelDocAcc.setPmntStatus((String) lparamMap.get("PnlDocAckStatus"));
			} else if (lStrStatus.equals("S00")) {
				ehfPanelDocAcc.setTransStatus((String) lparamMap.get("TransPaidStatus"));
				ehfPanelDocAcc.setPmntStatus((String) lparamMap.get("PnlDocPaidStatus"));
			} else if (lStrStatus.contains("E")) {
				ehfPanelDocAcc.setTransStatus((String) lparamMap.get("TransRejStatus"));
				ehfPanelDocAcc.setPmntStatus((String) lparamMap.get("PnlDocRejStatus"));
			}
			
			ehfPanelDocAcc = genericDao.save(ehfPanelDocAcc);
			
				if(ehfPanelDocAcc!=null)
					iResult = 1;
				
		}
	} catch (Exception e) {
		logger.error("Error:updatePanelDocPaymentDetails method in PaymentsDAOImpl with message "+e);
	}
	return iResult;

}


}
