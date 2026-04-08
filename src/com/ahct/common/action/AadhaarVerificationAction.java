package com.ahct.common.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ahct.common.service.AadhaarVerificationService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.util.AadhaarABHAUtility;
import com.google.gson.Gson;
import com.tcs.framework.configuration.ConfigurationService;
import org.json.JSONObject;

public class AadhaarVerificationAction extends Action {

	private final static Logger GLOGGER = Logger.getLogger(AadhaarVerificationAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){

		String lStrActionFlag = null;
		String lStrResultPage = null;
		response.setHeader("Pragma", "");
		response.setHeader("Cache-Control", "private");
		response.setDateHeader("Expires", Long.MAX_VALUE);
		HttpSession session = null;
		session = request.getSession(false);
		String serverIP ="http://172.17.7.44:7080";
		AadhaarVerificationService syntObj =(AadhaarVerificationService)ServiceFinder.getContext(request).getBean("AadhaarVerificationService");
		try{
			lStrActionFlag = request.getParameter("actionFlag");
			
			if("checkIfAadhaarExists".equalsIgnoreCase(lStrActionFlag)){
				String aadhaar = request.getParameter("aadharNumber");
            	String cardNo = request.getParameter("cardNo");
            	String cardType = request.getParameter("cardType");
            	String ehsEnrol = syntObj.checkAadhaar(aadhaar,cardType);
            	response.getWriter().write(new Gson().toJson(ehsEnrol));
            	 return null ; 
			}
			//Generate Mobile OTP
			/*if("genMobileotp".equals(lStrActionFlag)){
				String mobileNo = request.getParameter("phoneNumber");
          	  	String lStrResponse=syntObj.genMobileotp(mobileNo); 
          	  	System.out.println("lStrResponse::"+lStrResponse);
          	    response.setHeader("alert_msg",lStrResponse);
			}*/
			
			//Validate Mobile OTP
			/*if("validateMobileotp".equals(lStrActionFlag)){
				String mobileNo = request.getParameter("phoneNumber");
				String otp = request.getParameter("otp");
				long mobileNum = Long.parseLong(mobileNo);
          	  	String lStrResponse=syntObj.validateMobileotp(mobileNum,otp); 
          	    System.out.println("lStrResponse::"+lStrResponse);
          	    response.setHeader("alert_msg","SUCCESS");
			}*/
		
			//Generate aadharNum OTP
			/*if("genotp".equals(lStrActionFlag)){
				String aadharNum = AadhaarABHAUtility.checkNullObj(request.getParameter("aadharNum"));
				String respMsg = syntObj.generateAuthOtp(serverIP,aadharNum);
				System.out.println("respMsg::"+respMsg);
				String alert_msg = "",otpTransId="", remarks="";
	    		
	    		if(respMsg!=null && !respMsg.equals("") && respMsg.indexOf("~")!=-1)
	    		{
	    			String mydata[]=respMsg.split("~");	    			
	    			alert_msg = mydata[0];
	    			otpTransId = mydata[1];
	    			remarks = mydata[2];    			
	    			session.setAttribute("aadharNum_txnOtp", otpTransId);
	    			response.setHeader("alert_msg", alert_msg);
	    		}
			}*/
			
			//Validate aadharNum OTP
			/*if("validateekycotp".equals(lStrActionFlag)){
				String alert_msg = "";
				String mobileNumer = AadhaarABHAUtility.checkNullObj(request.getParameter("mobileNumer"));
				String aadharNum = AadhaarABHAUtility.checkNullObj(request.getParameter("aadharNum"));
				String aadharNum_otp = AadhaarABHAUtility.checkNullObj(request.getParameter("aadharNum_otp"));
				String aadharNum_txnOtp = AadhaarABHAUtility.checkNullObj(session.getAttribute("aadharNum_txnOtp"));
				String respMsg = syntObj.AuthWithEkycOTP(serverIP,mobileNumer,aadharNum,aadharNum_otp,aadharNum_txnOtp);
				
				
				if(respMsg!=null && !respMsg.equals("") && respMsg.indexOf("~")!=-1)
	    		{
	    			String mydata[]=respMsg.split("~");	    			
	    			alert_msg = mydata[0];
	    		}
				System.out.println("If alert_msg-----"+alert_msg);				
				response.setHeader("alert_msg", alert_msg);	
			}*/
			
			//Validate Bio Auth
			/*if("ekycbioauth".equals(lStrActionFlag)){
				String alert_msg = "";
				String phoneNumber = AadhaarABHAUtility.checkNullObj(request.getParameter("phoneNumber"));
				String aadharNum = AadhaarABHAUtility.checkNullObj(request.getParameter("aadhaarNumber"));
				String strReqBioData = AadhaarABHAUtility.checkNullObj(request.getParameter("uidauth_model_bioirisdata"));
				System.out.println(aadharNum+"::"+strReqBioData);
				String respMsg = syntObj.AuthWithEkycBioMatric(serverIP, phoneNumber, aadharNum, strReqBioData);
				if(respMsg!=null && !respMsg.equals("") && respMsg.indexOf("~")!=-1)
	    		{
	    			String mydata[]=respMsg.split("~");	    			
	    			alert_msg = mydata[0];
	    		}
				System.out.println("If alert_msg-----"+alert_msg);				
				response.setHeader("alert_msg", alert_msg);
			}*/			
			
			//Generate aadharNum OTP
			if("genAbhaotp".equals(lStrActionFlag)){
				Map<String,String> lMap = new HashMap<String,String>();
				String aadharNum = AadhaarABHAUtility.checkNullObj(request.getParameter("aadharNum"));
				lMap.put("aadharNum", aadharNum);
				JSONObject respMsg = syntObj.generateAbhaOtp(lMap);
				response.setHeader("alert_msg", respMsg.toString());
				System.out.println("respMsg::"+respMsg);
				}
			
			//Validate aadharNum OTP
			if("validateAbhaotp".equals(lStrActionFlag)){
				String userId=(String) session.getAttribute("UserID");
				Map<String,String> lMap = new HashMap<String,String>();
				lMap.put("mobileNumber",AadhaarABHAUtility.checkNullObj(request.getParameter("mobileNumber")));
				lMap.put("aadharNum_otp",AadhaarABHAUtility.checkNullObj(request.getParameter("aadharNum_otp")));
				lMap.put("aadharNum",AadhaarABHAUtility.checkNullObj(request.getParameter("aadharNum")));
				lMap.put("abhaTxn",AadhaarABHAUtility.checkNullObj(request.getParameter("abhaTxn")));
				lMap.put("cardType",AadhaarABHAUtility.checkNullObj(request.getParameter("cardType")));
				lMap.put("cardValue",AadhaarABHAUtility.checkNullObj(request.getParameter("cardValue")));//Card number
				lMap.put("abhaGenReg",AadhaarABHAUtility.checkNullObj(request.getParameter("abhaGenReg")));
				lMap.put("userId", userId);
				Map<String,String> output = syntObj.verifyAbhaOTP(lMap);
				System.out.println("OutPut:: "+output);
				String status = output.get("statusCode");
				if("200".equals(status)){
					response.setHeader("alert_msg", "SUCCESS");
					response.setHeader("abhaNo", output.get("msg"));
					response.setHeader("abhaDone", "Y");
				}
				else{
					response.setHeader("alert_msg", "FAIL");
					response.setHeader("abhaNo", output.get("msg"));
					response.setHeader("abhaDone", "N");
				}
			}
			
			if("abhaBioAuth".equals(lStrActionFlag)){
				Map<String,String> lMap = new HashMap<String,String>();
				lMap.put("mobileNumber",AadhaarABHAUtility.checkNullObj(request.getParameter("bioMobileNumber")));
				lMap.put("aadharNum",AadhaarABHAUtility.checkNullObj(request.getParameter("aadhaarNumber")));
				lMap.put("reqBioData",AadhaarABHAUtility.checkNullObj(request.getParameter("uidauth_model_bioirisdata")));
				lMap.put("cardType",AadhaarABHAUtility.checkNullObj(request.getParameter("cardTypeBio")));
				lMap.put("cardValue",AadhaarABHAUtility.checkNullObj(request.getParameter("cardValueBio")));
				lMap.put("abhaGenReg",AadhaarABHAUtility.checkNullObj(request.getParameter("abhaGenRegBio")));
				Map<String,String> output = syntObj.verifyAbhaBio(lMap);
				System.out.println("output::"+output);
				String status = output.get("statusCode");
				if("200".equals(status)){
					response.setHeader("alert_msg", "SUCCESS");
					response.setHeader("abhaNo", output.get("msg"));
					response.setHeader("abhaDone", "Y");
				}
				else{
					response.setHeader("alert_msg", "FAIL");
					response.setHeader("abhaNo", output.get("msg"));
					response.setHeader("abhaDone", "N");
				}					
			}
			//Generate Mobile OTP
			if("genSessionForAbha".equals(lStrActionFlag)){
          	    response.setHeader("alert_msg","Success");
			}
		}
        catch (Exception e){
        	e.printStackTrace();
		}
		return mapping.findForward(lStrResultPage);
	}
}
