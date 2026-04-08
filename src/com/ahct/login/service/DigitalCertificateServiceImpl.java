package com.ahct.login.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.ahct.login.vo.DigitalCertificateVO;
import com.ahct.model.EhfDigitalSign;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


public class DigitalCertificateServiceImpl implements DigitalCertificateService {

	
    GenericDAO genericDao;
    

    public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }
    
    
//    GenericDAO genericDao;
//    private CallPwdDecryptService decryptPwdService;
//    HibernateTemplate hibernateTemplate;
//
//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }
//
//    public void setGenericDao(GenericDAO genericDao) {
//        this.genericDao = genericDao;
//    }


    /**
     * @Desc This function is used to map digital signature to an employee
     * @param pDigitalCertificateVO
     * @return This returns status of adding digital signature for an employee
     */
//    @Transactional
//    public String addDigitalSignature(DigitalCertificateVO pDigitalCertificateVO) {
//        String lStrSigned_Time = "";
//        String lStrExpiry_Date = "";
//        String lStrOSFlag = "";
//        lStrOSFlag = "Linux";
//
//        try {
//            if (pDigitalCertificateVO.getSignedTime() != null && 
//                !"".equalsIgnoreCase(pDigitalCertificateVO.getSignedTime().trim())) {
//                if ("Linux".equalsIgnoreCase(lStrOSFlag)) {
//                    lStrSigned_Time = pDigitalCertificateVO.getSignedTime();
//                    String lStrSigned_Time_temp1 = 
//                        lStrSigned_Time.substring(0, 20);
//                    String lStrSigned_Time_temp2 = 
//                        lStrSigned_Time.substring(24);
//                    lStrSigned_Time = 
//                            lStrSigned_Time_temp1 + lStrSigned_Time_temp2;
//                } else {
//                    lStrSigned_Time = pDigitalCertificateVO.getSignedTime();
//                    lStrSigned_Time = lStrSigned_Time.substring(0, 22);
//                }
//
//            }
//            if (pDigitalCertificateVO.getExpiryDate() != null && 
//                !"".equalsIgnoreCase(pDigitalCertificateVO.getExpiryDate())) {
//                lStrExpiry_Date = pDigitalCertificateVO.getExpiryDate();
//                String lStrExpiry_Time_temp1 = 
//                    lStrExpiry_Date.substring(0, 20);
//                String lStrExpiry_Time_temp2 = lStrExpiry_Date.substring(24);
//                lStrExpiry_Date = 
//                        lStrExpiry_Time_temp1 + lStrExpiry_Time_temp2;
//            }
//
//            StringBuffer lStrBufQuery = new StringBuffer();
//            String[] args = new String[0];
//            lStrBufQuery.append("SELECT CASE WHEN max(to_number(SIGN_ID)) is null THEN 0 ELSE max(to_number(SIGN_ID)) END AS COUNT FROM SGVC_DIGITAL_SIGN");
//            List<LabelBean> lStCountDtls = 
//                genericDao.executeSQLQueryList(LabelBean.class, 
//                                               lStrBufQuery.toString(), args);
//            Date f = new Date(lStrExpiry_Date);
//            SgvcDigitalSign lSgvcDigitalSign = new SgvcDigitalSign();
//            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String lStrExptDt = sdf.format(f);
//            String lStrSignedTime = sdf.format(new Date(lStrSigned_Time));
//            int iSeqCount = lStCountDtls.get(0).getCOUNT().intValue();
//
//            /**
//     * Saving data to digital master table
//     */
//            lSgvcDigitalSign.setCRT_DT(new java.sql.Timestamp(new Date().getTime()));
//            lSgvcDigitalSign.setCRT_USR(pDigitalCertificateVO.getUserId());
//            lSgvcDigitalSign.setEXPIRY_DATE(sdf.parse(lStrExptDt));
//            lSgvcDigitalSign.setISSUER_DN(pDigitalCertificateVO.getIssueDn());
//            lSgvcDigitalSign.setSERIAL_NO(pDigitalCertificateVO.getSerialNo());
//            lSgvcDigitalSign.setSIGNED_TIME(sdf.parse(lStrSignedTime));
//            lSgvcDigitalSign.setSignId(Integer.toString(++iSeqCount));
//            lSgvcDigitalSign.setUSER_ID(pDigitalCertificateVO.getUserId());
//            lSgvcDigitalSign.setUSER_SIGN(pDigitalCertificateVO.getTextSignature());
//            lSgvcDigitalSign.setUserData(pDigitalCertificateVO.getTextData());
//            lSgvcDigitalSign = genericDao.save(lSgvcDigitalSign);
//
//
//            /** Setting digital flag to "Y" when digital registration done*/
//            SgvcEmpMst lSgvcEmpMst = 
//                genericDao.findById(SgvcEmpMst.class, SgvcEmpMstId.class, 
//                                    new SgvcEmpMstId(pDigitalCertificateVO.getUserId(), 
//                                                     pDigitalCertificateVO.getLocId(), 
//                                                     pDigitalCertificateVO.getLangId()));
//            String dpwd = null;
//            lSgvcEmpMst.setDigiVerifyReq("Y");
//            lSgvcEmpMst.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
//            lSgvcEmpMst.setLstUpdUsr(pDigitalCertificateVO.getUserId());
//            lSgvcEmpMst.setPasswd(dpwd);
//            String lStrPassword = decryptPwdService.execute(lSgvcEmpMst.getLoginName(), "");
//            lSgvcEmpMst.setPasswd(lStrPassword);
//            lSgvcEmpMst=genericDao.save(lSgvcEmpMst);
//            if (lSgvcDigitalSign != null && lSgvcEmpMst != null)
//                return "Success";
//            else
//                return "Failure";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "Failure";
//
//    }

    /**
     * @Desc This function is used to verify gignature and get its details
     * @param pDigitalCertificateVO
     * @return Returns details of digital certificate based on its signature
     */
    public DigitalCertificateVO verifySign(DigitalCertificateVO pDigitalCertificateVO) {
        String lStrtxtData = "";
        String lStrsign = "";
        String lStrSerial_No;
        String lStrIssuer_DN;
        String lStrSigned_Time;
        String lStrExpiry_Date;

        if (pDigitalCertificateVO.getTextData() != null && 
            pDigitalCertificateVO.getTextData().trim().length() > 0) {
            lStrtxtData = pDigitalCertificateVO.getTextData();
        }
        if (pDigitalCertificateVO.getTextSignature() != null && 
            pDigitalCertificateVO.getTextSignature().trim().length() > 0) {
            lStrsign = pDigitalCertificateVO.getTextSignature();
        }
        DigitalCertificateVO lDigitalCertificateVO = 
            new DigitalCertificateVO();
        try {
            HttpClient httpclient = new HttpClient();
            HttpMethod method=new PostMethod("http://10.10.12.166:8080/VerifyApp/SendVerifyResult");
            int j = lStrsign.length();
            int m = j / 250;
            m = m + 2;
            NameValuePair[] pairs = new NameValuePair[m];
            int k = 0, l = 0;
            for (k = 0, l = 0; k <= j; k = k + 250, l++) {
                int b = (k + 250);
                if (b >= j)
                    b = j;
                String s = lStrsign.substring(k, b);
                NameValuePair p = new NameValuePair("s" + l, s);
                pairs[l] = p;
            }
            NameValuePair p = new NameValuePair("txtData", lStrtxtData);
            pairs[l] = p;
            method.setQueryString(pairs);
            int returnCode = httpclient.executeMethod(method);
            if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
                System.err.println("The Post method is not implemented by this URI");
                // still consume the response body
                method.getResponseBodyAsString();
                lDigitalCertificateVO.setResultData("N");
            } else {

                lDigitalCertificateVO.setUserId(pDigitalCertificateVO.getUserId());
                lDigitalCertificateVO.setTextData(pDigitalCertificateVO.getTextData());
                lDigitalCertificateVO.setTextSignature(pDigitalCertificateVO.getTextSignature());
                if (method.getResponseHeader("Serial_No") != null) {
                    Header header = method.getResponseHeader("Serial_No");
                    lStrSerial_No = header.getValue();
                    lDigitalCertificateVO.setSerialNo(lStrSerial_No);
                    lDigitalCertificateVO.setResultData("Y");
                } else {
                    lDigitalCertificateVO.setResultData("N");
                }

                if (method.getResponseHeader("Issuer_DN") != null) {
                    Header Issuer_DN = method.getResponseHeader("Issuer_DN");
                    lStrIssuer_DN = Issuer_DN.getValue();
                    lDigitalCertificateVO.setIssueDn(lStrIssuer_DN);
                }

                if (method.getResponseHeader("Signed_Time") != null) {
                    Header Signed_Time = 
                        method.getResponseHeader("Signed_Time");
                    lStrSigned_Time = Signed_Time.getValue();
                    lDigitalCertificateVO.setSignedTime(lStrSigned_Time);
                }

                if (method.getResponseHeader("Expiry_Date") != null) {
                    Header Expiry_Date = 
                        method.getResponseHeader("Expiry_Date");
                    lStrExpiry_Date = Expiry_Date.getValue();
                    lDigitalCertificateVO.setExpiryDate(lStrExpiry_Date);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            lDigitalCertificateVO.setResultData("N");
        }
        return lDigitalCertificateVO;

    }

    /**
     * @Desc This verifies the mapping of digital signature of the employee
     * @param pStrUserEmpId
     * @return Returns status of existance of digital signature of the employee
     */
    public Boolean verifySignExisting(String pStrUserEmpId) {
    	EhfDigitalSign ehfDigitalSign = 
            genericDao.findFirstByPropertyMatch(EhfDigitalSign.class, 
                                                "USER_ID", pStrUserEmpId);
        if (ehfDigitalSign != null)
            return true;
        else
            return false;
    }


    /**
     * @Desc This authenticates the digital signature
     * @param pStrSerNo
     * @param pStrEmpId
     * @return Status of the authentication of the employee digital signature
     */
    public boolean authenticateDigitalCertificate(String pStrSerNo, 
                                                  String pStrEmpId) {
    	if(pStrEmpId==null||"".equalsIgnoreCase(pStrEmpId)||pStrSerNo==null||"".equalsIgnoreCase(pStrSerNo))
    		return false;
    	Date date=new Date();
    	List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
    	criteriaList.add(new GenericDAOQueryCriteria("USER_ID",pStrEmpId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	criteriaList.add(new GenericDAOQueryCriteria("activeYn","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    	criteriaList.add(new GenericDAOQueryCriteria("EXPIRY_DATE",date,GenericDAOQueryCriteria.CriteriaOperator.GE));
    	
    	List<EhfDigitalSign> EhfDigitalSignList=new ArrayList<EhfDigitalSign>();
    	EhfDigitalSign ehfDigitalSign=new EhfDigitalSign(); 
    	
    	EhfDigitalSignList=genericDao.findAllByCriteria(EhfDigitalSign.class, criteriaList);
    	
    	if(EhfDigitalSignList!=null && EhfDigitalSignList.size()>0)
    	{
    		ehfDigitalSign=EhfDigitalSignList.get(0);
    	}
    	
    	//EhfDigitalSign ehfDigitalSign = genericDao.findFirstByPropertyMatch(EhfDigitalSign.class,"USER_ID", pStrEmpId);
        if (ehfDigitalSign != null && 
            pStrSerNo.equalsIgnoreCase(ehfDigitalSign.getSERIAL_NO()))
            return true;
        else
            return false;
    }


	public String addDigitalSignature(DigitalCertificateVO pDigitalCertificateVO) {
		// TODO Auto-generated method stub
		return null;
	}


	

//    public void setDecryptPwdService(CallPwdDecryptService decryptPwdService) {
//        this.decryptPwdService = decryptPwdService;
//    }
//
//    public CallPwdDecryptService getDecryptPwdService() {
//        return decryptPwdService;
//    }
}
