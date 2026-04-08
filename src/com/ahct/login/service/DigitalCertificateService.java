package com.ahct.login.service;

import com.ahct.login.vo.DigitalCertificateVO;


public interface DigitalCertificateService {

    public DigitalCertificateVO verifySign(DigitalCertificateVO pDigitalCertificateVO);
    
    public String addDigitalSignature(DigitalCertificateVO pDigitalCertificateVO);
    
    public Boolean verifySignExisting(String pStrUserEmpId);
    
    public boolean authenticateDigitalCertificate(String pStrSerNo,String pStrEmpId);

}
