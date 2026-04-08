package com.ahct.common.service;

import java.util.Map;
import org.json.JSONObject;

public interface AadhaarVerificationService {
	public JSONObject generateAbhaOtp(Map<String,String> lMap);
	public  Map<String, String> verifyAbhaOTP(Map<String,String> lMap);
	public Map<String, String> verifyAbhaBio(Map<String, String> lMap);
	public String checkAadhaar(String aadhaar,String cardType);
}
