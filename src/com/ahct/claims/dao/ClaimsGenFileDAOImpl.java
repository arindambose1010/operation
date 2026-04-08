package com.ahct.claims.dao;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ahct.claims.util.ClaimsConstants;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfClaimUploadFile;
import com.ahct.model.EhfSliaPayments;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.claims.valueobject.PaymentsVO;
import com.ahct.claims.valueobject.TransactionVO;

public class ClaimsGenFileDAOImpl  {
	
	GenericDAO genericDao;
	ClaimsPaymentDAO claimsPaymentDao;
	
	  public GenericDAO getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	
	public ClaimsPaymentDAO getClaimsPaymentDao() {
		return claimsPaymentDao;
	}
	public void setClaimsPaymentDao(ClaimsPaymentDAO claimsPaymentDao) {
		this.claimsPaymentDao = claimsPaymentDao;
	}



	private static final Logger logger = Logger
			.getLogger(ClaimsGenFileDAOImpl.class);
	
	
	
	
	
	@Transactional
	    public void xmlFileGeneration(){
	        HashMap<String,Object> lParamMap = null;
	        try{ 
	        	List<EhfSliaPayments> ehfSliaPaymentsList = null;
	        	List<GenericDAOQueryCriteria> criteriaList = null;
	            StringBuffer lSb = new StringBuffer();
	            lSb.append(" select file_name NAME,payment_type TYPE,count(1) COUNT,sum(transaction_amount)*100 AMOUNT from ehf_slia_payments where file_flag='N' and file_name is not null group by file_name,payment_type ");
	            List<LabelBean> filesList = genericDao.executeSQLQueryList(LabelBean.class,lSb.toString());
	            for(LabelBean l : filesList){
	                lParamMap = null;
	                ehfSliaPaymentsList = null;
	                lParamMap = new HashMap<String,Object>();
	                criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
	                criteriaList.add(new GenericDAOQueryCriteria("fileName",l.getNAME(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	                ehfSliaPaymentsList = genericDao.findAllByCriteria(EhfSliaPayments.class,criteriaList);
	                if(ehfSliaPaymentsList != null && ehfSliaPaymentsList.size() > 0){
	                	
	                	 StringBuffer qry = new StringBuffer();
	                	 qry.append("select distinct CLIENT_BANK_IFC_CODE as IFSCCODE, CLIENT_AC_NUMBER as ACCOUNTNO from ehf_slia_payments where file_flag = 'N'and file_name is not null and file_name =? and payment_type =?");
	                	 String[] args=new String[2];
	                	 args[0]=l.getNAME();
	                	 args[1]=l.getTYPE();
	                	 List<LabelBean> actDtls= genericDao.executeSQLQueryList(LabelBean.class,qry.toString(),args);
	                	
	                    lParamMap.put("FILE_NAME",l.getNAME());
	                    lParamMap.put("PAYMENT_TYPE",l.getTYPE());
	                    lParamMap.put("CASES_COUNT",l.getCOUNT());
	                    lParamMap.put("TOTAL_AMOUNT",l.getAMOUNT());
	                    if(actDtls!=null && actDtls.size()>0 && actDtls.get(0)!=null)
	                    {
	                    	if(actDtls.get(0).getACCOUNTNO()!=null && actDtls.get(0).getACCOUNTNO().length()>0)
	                    	{
	                    		lParamMap.put("CLIENT_AC_NO",actDtls.get(0).getACCOUNTNO());
	                    	}
	                    	if(actDtls.get(0).getIFSCCODE()!=null && actDtls.get(0).getIFSCCODE().length()>0)
	                    	{
	                    		lParamMap.put("CLIENT_IFSC",actDtls.get(0).getIFSCCODE());
	                    	}

	                    }
	                   
	                }
	                lParamMap.put("EhfSliaPaymentsList",ehfSliaPaymentsList);
	                int i = generateXML(lParamMap);
	                if(i==1){
	                    for(EhfSliaPayments as : ehfSliaPaymentsList){
	                        as.setFileFlag("Y");
	                    }
	                    genericDao.saveAll(ehfSliaPaymentsList);
	                    
	                    EhfClaimUploadFile ehfClaimUploadFile = new EhfClaimUploadFile();
	                    HashMap hParamMap = new HashMap();
	                    hParamMap.put("SentStatus", ClaimsConstants.SENT);
	                    
	                    int lSet_id = claimsPaymentDao.getSetId(hParamMap);
	                    
	    				ehfClaimUploadFile.setFileName(l.getNAME());
	    				ehfClaimUploadFile.setCrtUser(ClaimsConstants.EO_TG_ADMIN_USER_ID);
	    				ehfClaimUploadFile.setFilePath("/storageNAS-Production/UploadClaims/"+l.getNAME());
	    				ehfClaimUploadFile.setCrtDate(new Timestamp(new Date()
	    						.getTime()));
	    				ehfClaimUploadFile.setFileStatus("SENT");
	    				ehfClaimUploadFile.setSetId(lSet_id);
	    				ehfClaimUploadFile = genericDao.save(ehfClaimUploadFile);
	                }
	                
	            }
	        } catch(Exception e){
	        	logger.error(" Error Occured in XML file generation in ClaimsPaymentSchedulerServiceImpl.java "+ e.getStackTrace());
	            e.printStackTrace();
	        }
	    }
	    public int generateXML(HashMap lParamMap) throws Exception{
	        int lResult = 0;
	        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder icBuilder;
	        icBuilder = icFactory.newDocumentBuilder();
	        Document doc = icBuilder.newDocument();
	        Element mainRootElement = doc.createElement("STATE_GOVT_PAYMENTS");
	        doc.appendChild(mainRootElement);
	        // append child elements to root element
	        Node debitAccount = setDebitDetails(doc, lParamMap);
	        mainRootElement.appendChild(debitAccount);
	        Element creditAccounts = doc.createElement("CREDIT_ACCOUNTS");
	        debitAccount.appendChild(creditAccounts);
	        List<EhfSliaPayments> asritSliaPaymentsList = (List<EhfSliaPayments>)lParamMap.get("EhfSliaPaymentsList");
	        for( EhfSliaPayments l :asritSliaPaymentsList){
	            creditAccounts.appendChild(setCreditDetails(doc, l));
	        }
	        // output DOM XML to console 
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
	        DOMSource source = new DOMSource(doc);
	        StreamResult console = new StreamResult(new File("/storageNAS-TS-Production/UploadClaims/"+(String)lParamMap.get("FILE_NAME")));
	        transformer.transform(source, console);
	        StreamResult console1 = new StreamResult(new File("/storageNAS-TS-Production/EHFSLIAPayments/"+(String)lParamMap.get("FILE_NAME")));
	        transformer.transform(source, console1);

	        System.out.println("\nXML DOM Created Successfully..");
	        
	        return lResult+1;
	    }
	    private static Node setDebitDetails(Document doc,HashMap lparamMap) throws Exception{
	        String fileName = ((String)lparamMap.get("FILE_NAME")).substring(0,20);
	        String dateFileNo = fileName.substring(8);
	        String date = dateFileNo.substring(0,8);
	        Element debit = doc.createElement("DEBIT_ACCOUNT");
	        debit.setAttribute("ACCOUNT_DEBIT", (String)lparamMap.get("CLIENT_AC_NO") );
	        debit.setAttribute("IFSC_CODE_DEBIT", (String)lparamMap.get("CLIENT_IFSC") );
	        debit.setAttribute("AGENCY_DR_REF", (String)lparamMap.get("PAYMENT_TYPE")+dateFileNo);
	        debit.setAttribute("BANK_NAME", ClaimsConstants.BANK_NAME);
	        debit.setAttribute("CREDIT_COUNT", lparamMap.get("CASES_COUNT").toString());
	        debit.setAttribute("DEBIT_AMOUNT", lparamMap.get("TOTAL_AMOUNT").toString());
	        debit.setAttribute("DEBIT_NARRATION", (String)lparamMap.get("PAYMENT_TYPE"));
	        debit.setAttribute("DEBIT_REFERENCE", fileName);
	        debit.setAttribute("DISTRICT", ClaimsConstants.DISTRICT);
	        debit.setAttribute("EMAIL", ClaimsConstants.EMAIL);
	        debit.setAttribute("STATE", ClaimsConstants.STATE);
	        debit.setAttribute("TRAN_DATE", date);
	        return debit;
	    }
	    private static Node setCreditDetails(Document doc,EhfSliaPayments ehfSliaPayments) throws Exception {
	        Element credit = doc.createElement("CREDIT_ACCOUNT");
	        credit.setAttribute("ACCOUNT_CREDIT",ehfSliaPayments.getBeneficiaryAccountNo());
	        credit.setAttribute("ADDRESS",ehfSliaPayments.getBeneficiaryAddress());
	        credit.setAttribute("CREDIT_AMOUNT",ehfSliaPayments.getTransactionAmount().intValue()+"00");
	        credit.setAttribute("CREDIT_REFERENCE",ehfSliaPayments.getUniqueId());
	        credit.setAttribute("IFSC_CODE_CREDIT",ehfSliaPayments.getBeneficiaryBankIfsc());
	        credit.setAttribute("NAME",ehfSliaPayments.getBeneficiaryAccountName());
	        credit.setAttribute("NARRATION",ehfSliaPayments.getTransId());
	        credit.setAttribute("NPCI_USER_ID",ClaimsConstants.NPCI_USER_ID);
	        credit.setAttribute("NPCI_USER_NAME",ClaimsConstants.NPCI_USER_NAME);
	        credit.setAttribute("PAYMENT_MODE","A");
	        credit.setAttribute("SCHEME_CODE","EHS");
	        credit.setAttribute("AGENCY_CR_REF","E/"+ehfSliaPayments.getTransId()+((ehfSliaPayments.getRejectCount()!=null && ehfSliaPayments.getRejectCount()>0)?("/R"+ehfSliaPayments.getRejectCount()):""));
	        return credit;
	    }
	

	    

	    
	    
}
