package com.ahct.attachments.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ahct.attachments.common.MultipartRequest;
import com.ahct.attachments.constants.ASRIConstants;


public class AttachmentProcessRequest {

	  public String processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception
	    {
	        String resultPage=null;
	        try
	        {
	        	 String lStrContentType = request.getContentType();//start 005
	        	  MultipartRequest multipart = null;
	        	 if(lStrContentType.length()>ASRIConstants.ZERO_VALUE)
		           {     
		                multipart = new MultipartRequest(request,"/ASRITemp",100*1024*1024);//007
		           }
	        	
	        	
	        }
	        catch(Exception e)
	        {
	        e.printStackTrace();	
	        }
	        return null;
}
}
