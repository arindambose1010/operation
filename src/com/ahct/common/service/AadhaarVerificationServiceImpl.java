package com.ahct.common.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.AbhaAddressDtls;
import com.ahct.model.AbhaResponse;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfJrnlstFamily;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.ahct.patient.vo.CommonDtlsVO;



public class AadhaarVerificationServiceImpl implements AadhaarVerificationService {

	private final static Logger GLOGGER = Logger.getLogger ( AadhaarVerificationServiceImpl.class ) ;
    GenericDAO genericDao;
    private static final String KEY_STRING  = "TrustKeyAadhaarr";
    private static final String ALGORITHM = "AES";
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	@Override
	public JSONObject generateAbhaOtp(Map<String,String> lMap) {
		System.out.println("Entered----------" + lMap.get("aadharNum"));
		JSONObject jsonObj = new JSONObject();
		try {
			String serviceUrl = config.getString("internalAbhaApi");
			String methodName = "/m1/sendotp";
			String url = serviceUrl + methodName;
			
			SSLSocketFactory sf=null;
			SSLContext context=null;
			context=SSLContext.getInstance("TLSv1.2");
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			
			DefaultHttpClient client=new DefaultHttpClient();
			Scheme scheme=new Scheme("https",443,sf);
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			
			HttpPost post = new HttpPost(url);
			// request body
			JSONObject requestBody = new JSONObject();
			requestBody.put("aadhaarNo", lMap.get("aadharNum"));
			
			StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);
			
			post.setHeader("Content-Type", "application/json");
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			
			System.out.println("API response: " + responseBody);
			jsonObj = new JSONObject(responseBody);
			System.out.println("Response Object::" + jsonObj.toString());
		 } catch (Exception e) {
			 e.printStackTrace();
			 try {
				 jsonObj.put("error", e.getLocalizedMessage());
				 } catch (Exception jsonException) {
		            jsonException.printStackTrace();
		        }
		    }
		    return jsonObj;
	}
		 
	@Override
	public  Map<String, String> verifyAbhaOTP(Map<String,String> lMap) {
		String abhaGenReg = lMap.get("abhaGenReg");
		Map<String, String> respMap = new HashMap<String,String>();
		CommonDtlsVO empVO = new CommonDtlsVO();
		String abhaNumber = "", prefAddress="",stateName="", status="";
		String firstName = "", lastName = "", middleName = "", gender="",dob="",mobile="",districtName="",pinCode="",address="",abhaType="", phrAddress="", isNew="";
		JSONObject jsonObj = new JSONObject();
		try {
			String serviceUrl = config.getString("internalAbhaApi");
			String methodName = "/m1/verifyotp";
			String url = serviceUrl + methodName;
			
			SSLSocketFactory sf=null;
			SSLContext context=null;
			context=SSLContext.getInstance("TLSv1.2");
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			
			DefaultHttpClient client=new DefaultHttpClient();
			Scheme scheme=new Scheme("https",443,sf);
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept", "application/json");
			
			JSONObject requestBody = new JSONObject();
			requestBody.put("mobile", lMap.get("mobileNumber"));
			requestBody.put("aadhaarNo", lMap.get("aadharNum"));
			requestBody.put("otpValue", lMap.get("aadharNum_otp"));
			requestBody.put("txnId", lMap.get("abhaTxn"));
			requestBody.put("cardType", lMap.get("cardType"));
			requestBody.put("cardValue", lMap.get("cardValue"));
			requestBody.put("abhaGenReg", lMap.get("abhaGenReg"));
			
			StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			int statusCode = response.getStatusLine().getStatusCode();
			String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("Raw Response: " + responseBody);
			
			jsonObj = new JSONObject(responseBody);
			
			System.out.println("Response Object::" + jsonObj.toString());
			if (responseBody == null || responseBody.trim().isEmpty()) {
				respMap.put("msg","Empty response from server.");
				return respMap;
			}
			try {
				jsonObj = new JSONObject(responseBody);
				//String status  = jsonObj.getString("statusCode");
				if (jsonObj.has("statusCode")) {
			        status = jsonObj.get("statusCode").toString();
			    } else if (jsonObj.has("status")) {
			        status = jsonObj.get("status").toString();
			    }
				respMap.put("statusCode",status);
				if (jsonObj.has("responseObject") && jsonObj.getJSONObject("responseObject").has("error")) {
				    JSONObject errorObj = jsonObj.getJSONObject("responseObject").getJSONObject("error");
				    String errMsg = errorObj.optString("message", "Unknown Error");
				    System.out.println("Error Message => " + errMsg);
				    respMap.put("msg", errMsg);
				    return respMap;
				}
				else if("200".equals(status)){
					isNew = jsonObj.get("isNew").toString();
					
					if (jsonObj.has("responseObject")) {
						JSONObject responseObject = jsonObj.getJSONObject("responseObject");
						abhaNumber = responseObject.getString("ABHANumber");
							abhaNumber = abhaNumber.replaceAll("-", "");
						respMap.put("msg",abhaNumber);
						firstName = responseObject.optString("firstName");
						middleName = responseObject.getString("middleName");
						lastName = responseObject.getString("lastName");
						gender = responseObject.getString("gender");
						dob = responseObject.getString("dob");
						stateName = responseObject.getString("stateName");
						mobile = responseObject.getString("mobile");
						districtName = responseObject.getString("districtName");
						pinCode = responseObject.getString("pinCode");
						address = responseObject.getString("address");
						abhaType = responseObject.getString("abhaType");
						//phrAddress = responseObject.getString("phrAddress");
						JSONArray phrAddressArr = responseObject.getJSONArray("phrAddress");
						prefAddress = responseObject.getString("preferredAddress");
						String abhaPhoto = savePhoto(responseObject.getString("photo"), abhaNumber);
						System.out.println("responseObject::" + responseObject.toString());
						respMap.put("msg",abhaNumber);
						
						empVO.setCARDNO(lMap.get("cardValue"));
						empVO.setAbhaId(abhaNumber);
						empVO.setCardType(lMap.get("cardType"));
						empVO.setAadhaarNum(lMap.get("aadharNum"));
						empVO.setUserId(lMap.get("userId"));
						empVO.setAddress(address);
						empVO.setPinCode(pinCode);
						empVO.setDISTRICT(districtName);
						empVO.setFirstName(firstName);
						empVO.setMiddleName(middleName);
						empVO.setLastName(lastName);
						empVO.setGENDER(gender);
						empVO.setDob(dob);
						empVO.setAbhaType(abhaType);
						empVO.setPrefAbhaAddr(prefAddress);
						empVO.setStateName(stateName);
						empVO.setIsNew(isNew);
						empVO.setAppType(abhaGenReg);
						empVO.setPhoto(abhaPhoto);
						empVO.setAuthType("OTP");
			            if(mobile == null)
			            	empVO.setMobileNo("");
			            else
			            	empVO.setMobileNo(mobile);
			            
			            saveAbhaDetails(empVO, phrAddressArr);
			            }
	                }
	                else if("500".equals(status)){
	                	abhaNumber =  jsonObj.getString("message");  
	                	System.out.println("abhaNumber=>"+abhaNumber);
	                	 respMap.put("msg",abhaNumber);
			             return respMap; 
	                }
	                
		        } catch (Exception e) {
		            System.out.println("Error: Response is not a valid JSON object.");
		            e.printStackTrace();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        respMap.put("msg",e.getMessage());
		    }
		    return respMap;
		}
	 
	 private String saveAbhaDetails(CommonDtlsVO empVO, JSONArray phrAddressArr) {
		 try {
			 String cardNo = empVO.getCARDNO();
			 String empType = empVO.getCardType();
			 EhfEnrollmentFamily ehsEnrollmentFamily = new EhfEnrollmentFamily();
			 EhfJrnlstFamily ehfJrnlstFamily = new EhfJrnlstFamily();
			 AbhaAddressDtls abhaAddressDtls = new AbhaAddressDtls();
			 AbhaResponse abhaResponse = new AbhaResponse();
			 
			 //Saving the abha response
			 abhaResponse.setCardId(empVO.getCARDNO());
			 abhaResponse.setCardType(empVO.getCardType());
			 abhaResponse.setAbhaId(empVO.getAbhaId());
			 abhaResponse.setMobile(empVO.getMobileNo());
			 abhaResponse.setFirstName(empVO.getFirstName());
			 abhaResponse.setMiddleName(empVO.getMiddleName());
			 abhaResponse.setLastName(empVO.getLastName());
			 abhaResponse.setDob(empVO.getDob());
			 abhaResponse.setAadharAddr(empVO.getAddress());
			 abhaResponse.setState(empVO.getStateName());
			 abhaResponse.setGender(empVO.getGENDER());
			 abhaResponse.setEkycPhotoPath(empVO.getPhoto());
			 abhaResponse.setIsAbhaNew(empVO.getIsNew());
			 abhaResponse.setIsEkycVerified("Y");
			 abhaResponse.setActiveYn("Y");
			 abhaResponse.setCrtDt(new Timestamp(System.currentTimeMillis()));
			 abhaResponse.setCrtUsr(empVO.getUserId());
			 abhaResponse.setAppOprNabh(empVO.getAppType());
			 abhaResponse.setAbhaType(empVO.getAbhaType());
			 abhaResponse.setAbhaPrefAddr(empVO.getPrefAbhaAddr());
			 abhaResponse.setAadhaarCard(empVO.getAadhaarNum());
			 abhaResponse.setAuthType(empVO.getAuthType());
			 abhaResponse.setPinCode(empVO.getPinCode());
			 abhaResponse.setDistrict(empVO.getDISTRICT());
			 genericDao.save(abhaResponse);
			 
			//Saving the abhaAddressDtls
			 for (int i = 0; i < phrAddressArr.length(); i++) {
				    String phrAddr = phrAddressArr.getString(i);
				    abhaAddressDtls = new AbhaAddressDtls();
				    abhaAddressDtls.setAbhaId(empVO.getAbhaId());
				    abhaAddressDtls.setAbhaAddress(phrAddr);
				    abhaAddressDtls.setActiveYn("Y");
				    abhaAddressDtls.setCrtDt(new Timestamp(System.currentTimeMillis()));
				    abhaAddressDtls.setCrtUsr(empVO.getUserId());
				    genericDao.save(abhaAddressDtls);
				}
			 String selectQury = null;
			 //CR 9043 Added OR condition for DJ while generated abha no
			 if("J".equalsIgnoreCase(empType) || "DJ".equalsIgnoreCase(empType) ) {
				 selectQury = "SELECT JOURNAL_ENROLL_ID as ID FROM EHF_JRNLST_FAMILY WHERE JOURNAL_CARD_NO ='"+cardNo+"' AND EKYC_DONE_YN ='N' ";
			     List<LabelBean> jrnlDtls = genericDao.executeSQLQueryList(LabelBean.class, selectQury.toString());
			     if(jrnlDtls != null && !jrnlDtls.isEmpty()){
			    	 for (LabelBean label : jrnlDtls) {
			             String enrollId = label.getID();
			             ehfJrnlstFamily = genericDao.findById(EhfJrnlstFamily.class, String.class, enrollId);
			             if (ehfJrnlstFamily != null) {
			                 ehfJrnlstFamily.setEkycDoneYN("Y");
			                 ehfJrnlstFamily.setAbhaId(empVO.getAbhaId());
			                 ehfJrnlstFamily.setAbhaUpdUsr(empVO.getUserId());
			                 ehfJrnlstFamily.setAbhaUpdDt(new Timestamp(System.currentTimeMillis()));
			                 genericDao.save(ehfJrnlstFamily);
			             }
			         }
				 }
			}
			else{
				selectQury = "SELECT ENROLL_ID as ID FROM EHF_ENROLLMENT_FAMILY WHERE EHF_CARD_NO ='"+cardNo+"' AND EKYC_DONE_YN ='N' ";
		     	List<LabelBean> empDtls = genericDao.executeSQLQueryList(LabelBean.class, selectQury.toString());
		     	if(empDtls != null && !empDtls.isEmpty()){
		     		for (LabelBean label : empDtls) {
		                String enrollId = label.getID();
		                ehsEnrollmentFamily = genericDao.findById(EhfEnrollmentFamily.class, String.class, enrollId);
		                if (ehsEnrollmentFamily != null) {
		                	ehsEnrollmentFamily.setEkycDoneYn("Y");
		                	ehsEnrollmentFamily.setAbhaId(empVO.getAbhaId());
		                	ehsEnrollmentFamily.setAbhaUpdUsr(empVO.getUserId());
		                	ehsEnrollmentFamily.setAbhaUpdDt(new Timestamp(System.currentTimeMillis()));
		                    genericDao.save(ehsEnrollmentFamily);
		                }
		            }
				 }
			}
		return "SUCCESS";
		 }catch (Exception e) {
			 e.printStackTrace();
			 return "ERROR: " + e.getMessage();
		 }
	}

	 //Chandana - 8133 - for saving the photo in local
	 public String savePhoto(String photoString, String abhaNumber) {
		    String directoryPath = "/storageNAS-TS-Production/ABHAPhoto/";  
		    File dir = new File(directoryPath);
		    if (!dir.exists())
		        dir.mkdirs();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		    String timestamp = dateFormat.format(new java.util.Date());
		    String filePath = directoryPath + "imgbase64_" + abhaNumber + timestamp + ".txt";
		    BufferedWriter writer = null;
		    try {
		        writer = new BufferedWriter(new FileWriter(filePath));
		        writer.write(photoString);   // writing Base64 string as text
		        writer.close();
		        return filePath;
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        if (writer != null) {
		            try { writer.close(); } catch (IOException e) {}
		        }
		    }
		    return filePath;
		}

	@Override
	public  Map<String, String> verifyAbhaBio(Map<String,String> lMap) {
		String abhaGenReg = lMap.get("abhaGenReg");
		Map<String, String> respMap = new HashMap<String,String>();
		CommonDtlsVO empVO = new CommonDtlsVO();
		String abhaNumber = "", prefAddress="",stateName="", status="";
		String firstName = "", lastName = "", middleName = "", gender="",dob="",mobile="",districtName="",pinCode="",address="",abhaType="", phrAddress="", isNew="";
		JSONObject jsonObj = new JSONObject();
		try {
			String serviceUrl = config.getString("internalAbhaApi");
			String methodName = "/m1/biometric";
			String url = serviceUrl + methodName;
			
			SSLSocketFactory sf=null;
			SSLContext context=null;
			context=SSLContext.getInstance("TLSv1.2");
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			
			DefaultHttpClient client=new DefaultHttpClient();
			Scheme scheme=new Scheme("https",443,sf);
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept", "application/json");
			
			JSONObject requestBody = new JSONObject();
			
			requestBody.put("mobile", lMap.get("mobileNumber"));
	        requestBody.put("aadhaarNo", lMap.get("aadharNum"));
	        requestBody.put("fingerPrintAuthPid", lMap.get("reqBioData"));
	        requestBody.put("cardType", lMap.get("cardType"));
	        requestBody.put("cardValue", lMap.get("cardValue"));
	        requestBody.put("abhaGenReg", lMap.get("abhaGenReg"));
			
			StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			int statusCode = response.getStatusLine().getStatusCode();
			String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("Raw Response: " + responseBody);
			
			jsonObj = new JSONObject(responseBody);
			
			System.out.println("Response Object::" + jsonObj.toString());
			if (responseBody == null || responseBody.trim().isEmpty()) {
				respMap.put("msg","Empty response from server.");
				return respMap;
			}
			try {
				jsonObj = new JSONObject(responseBody);
				if (jsonObj.has("statusCode")) {
			        status = jsonObj.get("statusCode").toString();
			    } else if (jsonObj.has("status")) {
			        status = jsonObj.get("status").toString();
			    }
				respMap.put("statusCode",status);
				
				if("200".equals(status)){
					if (jsonObj.has("responseObject")) {
						JSONObject responseObject = jsonObj.getJSONObject("responseObject");
						abhaNumber = responseObject.getString("ABHANumber");
							abhaNumber = abhaNumber.replaceAll("-", "");
						firstName = responseObject.optString("firstName");
						middleName = responseObject.getString("middleName");
						lastName = responseObject.getString("lastName");
						gender = responseObject.getString("gender");
						dob = responseObject.getString("dob");
						stateName = responseObject.getString("stateName");
						mobile = responseObject.getString("mobile");
						districtName = responseObject.getString("districtName");
						pinCode = responseObject.getString("pinCode");
						address = responseObject.getString("address");
						abhaType = responseObject.getString("abhaType");
						//phrAddress = responseObject.getString("phrAddress");
						JSONArray phrAddressArr = responseObject.getJSONArray("phrAddress");
						System.out.println("phrAddress :: "+phrAddress);
						prefAddress = responseObject.getString("preferredAddress");
						String abhaPhoto = savePhoto(responseObject.getString("photo"), abhaNumber);
						System.out.println("responseObject::" + responseObject.toString());
						respMap.put("msg",abhaNumber);
						
						empVO.setCARDNO(lMap.get("cardValue"));
						empVO.setAbhaId(abhaNumber);
						empVO.setCardType(lMap.get("cardType"));
						empVO.setAadhaarNum(lMap.get("aadharNum"));
						empVO.setUserId(lMap.get("userId"));
						empVO.setAddress(address);
						empVO.setPinCode(pinCode);
						empVO.setDISTRICT(districtName);
						empVO.setFirstName(firstName);
						empVO.setMiddleName(middleName);
						empVO.setLastName(lastName);
						empVO.setGENDER(gender);
						empVO.setDob(dob);
						empVO.setAbhaType(abhaType);
						empVO.setPrefAbhaAddr(prefAddress);
						empVO.setStateName(stateName);
						empVO.setIsNew(isNew);
						empVO.setAppType(abhaGenReg);
						empVO.setPhoto(abhaPhoto);
						empVO.setAuthType("BIO");
			            if(mobile == null)
			            	empVO.setMobileNo("");
			            else
			            	empVO.setMobileNo(mobile);
			            
			            saveAbhaDetails(empVO, phrAddressArr);
			            }
	                }
	                else if("500".equals(status)){
	                	abhaNumber =  jsonObj.getString("message");  
	                	System.out.println("abhaNumber=>"+abhaNumber);
	                	 respMap.put("msg",abhaNumber);
			             return respMap; 
	                }
	                else if (jsonObj.has("responseObject") && jsonObj.getJSONObject("responseObject").has("error")) {
					    JSONObject errorObj = jsonObj.getJSONObject("responseObject").getJSONObject("error");
					    String errMsg = errorObj.optString("message", "Unknown Error");
					    System.out.println("Error Message => " + errMsg);
					    respMap.put("msg", errMsg);
					    return respMap;
					}
		        } catch (Exception e) {
		            System.out.println("Error: Response is not a valid JSON object.");
		            e.printStackTrace();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        respMap.put("msg",e.getMessage());
		    }
		    return respMap;
		}
		
	public static String encrypt(String value) throws Exception {
        Key key = generateKey(KEY_STRING);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes("UTF-8")); // Use UTF-8 encoding
        return new String(Base64.encodeBase64(encryptedBytes)); // Convert byte array to Base64-encoded String
    }
	
	private static Key generateKey(String keyString) {
        byte[] keyData = keyString.getBytes();
        return new SecretKeySpec(keyData, ALGORITHM);
    }

	@Override
	public String checkAadhaar(String aadhaar,String cardType) {

		String result = null;
		try{
			String selectQury = null;
				selectQury = "SELECT IS_EKYC_VERIFIED ID FROM ABHA_RESPONSE WHERE AADHAAR_CARD ='"+aadhaar+"' ";
			List<LabelBean> empDtls = genericDao.executeSQLQueryList(LabelBean.class, selectQury.toString());
	        if (empDtls != null && !empDtls.isEmpty()) 
	        	result = empDtls.get(0).getID();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	
	}
}	
