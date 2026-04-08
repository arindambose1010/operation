 package com.ahct.common.util;

import java.util.Map;
import java.util.Date;


public class DigitalSignatureImpl implements DigitalSignature{

    
    public Map<String, Object> verify(Map<String, Object> map){
        map.put("Serial_No","1234567");
        map.put("Expiry_Date", new Date());
        map.put("Signed_Time", new Date());
        return map;
    }

    
}
