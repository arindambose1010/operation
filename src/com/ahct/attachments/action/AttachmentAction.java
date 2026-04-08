package com.ahct.attachments.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ahct.annualCheckUp.service.AhcClaimsService;
import com.ahct.attachments.common.MultipartRequest;
import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.attachments.form.AttachmentForm;
import com.ahct.attachments.service.AttachmentService;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.chronicOp.service.ChronicOPService;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.common.util.HtmlEncode;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.preauth.service.PreauthService;   
import com.tcs.framework.configuration.ConfigurationService;


public class AttachmentAction extends Action{
	  private final static Logger LOGGER = Logger.getLogger(AttachmentAction.class);  
	  
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	    {
		  
	        HttpSession session = request.getSession ( false ) ;
	        String lStrResultPage = HtmlEncode.verifySession(request, response);
	        String empnlFlag = null;
	        ConfigurationService configurationService = (ConfigurationService) ServiceFinder.getContext(request).getBean("configurationService");
			Configuration config = configurationService.getConfiguration();
			  AttachmentService attachmentService = (AttachmentService)ServiceFinder.getContext(request).getBean("attachmentService");
			    AttachmentForm attachmentForm = ( AttachmentForm ) form ;
		        PreauthService preauthService = (PreauthService)ServiceFinder.getContext(request).getBean("preauthService");
		        AhcClaimsService ahcClaimsService = (AhcClaimsService) ServiceFinder.getContext(
						request).getBean("ahcClaimsService");
		        empnlFlag = request.getParameter("empnlFlag");
		        String newBornLocal=null;
	       // if (lStrResultPage.length() > 0)
	          //  return mapping.findForward("sessionExpire");
	        if("empnl".equalsIgnoreCase(empnlFlag))
	        {
	        	//for view attachments of empanelment
	        }
	        else  if (lStrResultPage.length() > 0)
	        {
	        	 request.setAttribute("caseSession", "Y");
	             return mapping.findForward("regSessionExpire");
	        }
	        String lStrEmpId = null ;
		    String lStrLocId = null ;
		    String lStrLangId = null ;
		    String lStrUserName = null;
		    String lStrFlagStatus = null;
		    String lStrUserRole = null;
		    List<LabelBean> rolesList = null;
	        if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
			    lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
			    Date crtDt = new Date () ;
			    if ( ( session.getAttribute ( "LocID" ) != null ) && !( session.getAttribute ( "LocID" ).equals ( "" ) ) )
			    	lStrLocId = ( String ) session.getAttribute ( "LocID" ) ;
			    if ( ( session.getAttribute ( "LangID" ) != null ) && !( session.getAttribute ( "LangID" ).equals ( "" ) ) )
			    	lStrLangId = ( String ) session.getAttribute ( "LangID" ) ;
			    if ( ( session.getAttribute ( "userName" ) != null ) && !( session.getAttribute ( "userName" ).equals ( "" ) ) )
			    	lStrUserName = ( String ) session.getAttribute ( "userName" ) ;
			    if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
			    	rolesList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
			    if ( ( session.getAttribute ( "UserRole" ) != null ) && !( session.getAttribute ( "UserRole" ).equals ( "" ) ) )
			    	lStrUserRole = ( String ) session.getAttribute ( "UserRole" ) ;
			    String roleId = null;
				List<String> lStrgrpList=new ArrayList<String>();
			    for(LabelBean labelBean:rolesList)
				{
					lStrgrpList.add(labelBean.getID());
				}
			    if(lStrgrpList.contains(config.getString("Group.Mithra")))
				{
					roleId=config.getString("Group.Mithra");
				}
				else if(lStrgrpList.contains(config.getString("Group.Medco")))
				{
					roleId=config.getString("Group.Medco");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-EX")))
				{
					roleId=config.getString("Group.AHC-EX");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-TD")))
				{
					roleId=config.getString("Group.AHC-TD");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-CH")))
				{
					roleId=config.getString("Group.AHC-CH");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-ACO")))
				{
					roleId=config.getString("Group.AHC-ACO");
				}
				else if(lStrgrpList.contains(config.getString("Group.AHC-EO")))
				{
					roleId=config.getString("Group.AHC-EO");
				}
				else
				{
					roleId=lStrgrpList.get(0);
				}
			    lStrFlagStatus = request.getParameter("actionFlag");
			  
		        ChronicOPService chronicOPService=(ChronicOPService)ServiceFinder.getContext(request).getBean("chronicOPService");
		        String   updType=null;//start 005
			 String   viewStatus=null;
			 String  mode=null;
			 String  enrollParntId=null;
			 String   upd_type= null;
			 String  lStrActionVal = null;
		     String attachType = null; 
		     String caseId = null;
		     String aadharExists=null;
		           Map<String,Object> lResMap=null;
		          
		           MultipartRequest multipart = null;
		           String lStrContentType = request.getContentType();//start 005
		           lStrContentType = (lStrContentType==null)?ASRIConstants.EMPTY_STRING:lStrContentType;
		           if(lStrContentType.length()>ASRIConstants.ZERO_VALUE)
		           {     
		        	   
		               // multipart = new MultipartRequest(request,"/ASRITemp",100*1024*1024);//007
		                lStrActionVal = request.getParameter(ASRIConstants.ACTION_VAL);
		                attachType=request.getParameter("attachType");
		                
		                updType=request.getParameter("uploadType");//start 005
		                viewStatus=request.getParameter("ViewStatus");
		                mode=request.getParameter("mode");
		                enrollParntId=request.getParameter("EnrollParntId");
		                upd_type= request.getParameter("upd_type") ;
		                aadharExists= request.getParameter("aadharExists");

		            }
		           int doc_count=0;
		           if(request.getParameter("doc_count")!=null && !"".equalsIgnoreCase(request.getParameter("doc_count")))
		           doc_count= Integer.parseInt(request.getParameter("doc_count")) ;
		          
		            request.setAttribute("EnrollParntId",enrollParntId);
		            request.setAttribute("upd_type",upd_type);
		            String userRole=(String)session.getAttribute(ASRIConstants.USER_ROLE);//005
		            request.setAttribute(ASRIConstants.USER_ROLE,userRole);//005
		            
		            if(request.getParameter("cochCount")!=null)
		            		request.setAttribute("cochCount", request.getParameter("cochCount"));

	        /**
	         * embedding the code start 
	         */
	        
	        try
	        {
	            if(lStrFlagStatus!= null && "upldEnrollAttach".equals(lStrFlagStatus)) 
		        {
	            	lStrActionVal = request.getParameter("actionVal");
	            	request.setAttribute("newBornBaby", request.getParameter("newBornBaby"));
	            	 String type = request.getParameter("docType");  
	            	 String upldType = request.getParameter("UpdType");  
	            	 StringBuffer msg = new StringBuffer();
	            	 caseId = request.getParameter("caseId");
	            	 String chronicId=request.getParameter("chronicId");
		              String lStrSharePath = null;
		              String installment=null;
		              String chronicNo=null;
		              if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("caseAttachments")){
		            	  if(upldType!=null && upldType.equalsIgnoreCase("ehfFollowUp")){
		                  SimpleDateFormat sdfw = new SimpleDateFormat("dd-MM-yyyy");
		            	  Date date = new Date();
		          		  String crtDate = sdfw.format(date);
		            	  lStrSharePath = config.getString("mapPath")+config.getString("followUpAttach")+ASRIConstants.SLASH_VALUE+request.getParameter("caseId")+ASRIConstants.SLASH_VALUE+crtDate+ASRIConstants.SLASH_VALUE;
		            	  }
		            	  if(upldType!=null && upldType.equalsIgnoreCase("chronicAttach")){
			                  SimpleDateFormat sdfw = new SimpleDateFormat("dd-MM-yyyy");
			            	  Date date = new Date();
			          		  String crtDate = sdfw.format(date);
			          		  installment= (String) session.getAttribute("installment");
			          		 
			          		  chronicNo=request.getParameter("chronicId")+"/"+installment;
			            	  lStrSharePath = config.getString("mapPath")+config.getString("chronicOpAttach")+ASRIConstants.SLASH_VALUE+chronicNo+ASRIConstants.SLASH_VALUE+crtDate+ASRIConstants.SLASH_VALUE;
			            	  }
		            	  else{
		            	  lStrSharePath = config.getString("mapPath")+config.getString("Preauth_filePath")+request.getParameter("caseId")+ASRIConstants.SLASH_VALUE;
		            	  }
		              }
		              else if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("ahcAttachments")){
		            			          		
		            	  caseId=request.getParameter("ahcId");
		            	  lStrSharePath = config.getString("mapPath")+config.getString("Ahc_filePath")+ASRIConstants.SLASH_VALUE+request.getParameter("ahcId")+ASRIConstants.SLASH_VALUE;
		              }
		              else
		            	  lStrSharePath = config.getString("mapPath")+config.getString("enrollment")+enrollParntId+ASRIConstants.SLASH_VALUE;  
		              String filePath= lStrSharePath + type;    
		             String attachNos = request.getParameter("attachNos");
		             StringTokenizer str = null;
		             if(attachNos!=null && !attachNos.equalsIgnoreCase(""))
		            	 str = new StringTokenizer(attachNos,"~");
		             Long attAllowSize = null ;
		             if(request.getParameter(type+"size")!=null && !request.getParameter(type+"size").equalsIgnoreCase(""))
		            	 attAllowSize = Long.parseLong((String)request.getParameter(type+"size"));
		             String resultMsg = null;
		             String dir = null;
		             int i=0;
		             if(str!=null){
		             while(str.hasMoreTokens())
		             {
		            	 i++;
		            	 FormFile formFileObj = attachmentForm.getAttachedIndex(Integer.parseInt( str.nextToken()) );
		            	 try{
		            	 if(formFileObj.getFileSize() > attAllowSize)//start 006
		                   {
		                     String exceedSize = null;
		                     if(attAllowSize/1024 > 1024)
		                     {
		                       exceedSize = (attAllowSize/(1024*1024)) + "MB";
		                     }
		                     else
		                     {
		                       exceedSize = (attAllowSize/1024) + "KB";
		                     }
		                     
		                     resultMsg = ASRIConstants.ATTCH_SIZE_EXCEED_ERROR+exceedSize+ " in \\'"+ formFileObj.getFileName()+ "\\' \\n";  
		                     msg.append(resultMsg);
		                   }
		            	 else
		            	 {
		            		 java.util.Date ldtToday = new java.util.Date();
		                     String fullPath = filePath ; 
		                     dir =fullPath;
		                     boolean flag = ( new File ( dir ) ).mkdirs () ;
		                     dir = dir +ASRIConstants.SLASH_VALUE+ ldtToday.getTime()+ASRIConstants.UNDERSCORE_VALUE+formFileObj.getFileName();
		                     File lFileFS = new File ( dir ) ;
		                       FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
		                       fos.write ( formFileObj.getFileData() ) ;
		                       AttachmentVO attachmentVO = new AttachmentVO();
		                       attachmentVO.setEmpParentId(enrollParntId);
		                       attachmentVO.setActualName(formFileObj.getFileName());
		                       attachmentVO.setSavedName(dir);
		                       attachmentVO.setCrtUsrName(lStrEmpId);
		                       attachmentVO.setAttachType(attachType);
		                       attachmentVO.setHeading(upldType);
		                       String msg1 = null;
		                       if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("caseAttachments"))
			                       {
			                    	   attachmentVO.setCaseId(caseId);
			                    	   if(upldType!=null && upldType.equalsIgnoreCase("chronicAttach"))
				                    	   {
				                    		   attachmentVO.setChronicId(chronicId);
				                    		   attachmentVO.setChronicNo(chronicNo);
				                    		   msg1 = attachmentService.upldChronicAttachments(attachmentVO); 
				                    		   
				                    	   }
			                    	   else if(upldType!=null && (upldType.equalsIgnoreCase("ehfFollowUp")||
			                    			   					  upldType.equalsIgnoreCase(config.getString("ehfCoch"))))
			                    		   {
			                    		   		msg1 = attachmentService.upldFlupAttachments(attachmentVO);   
			                    		   }
			                    	   else
				                    	   {
				                    	       msg1 = attachmentService.upldCaseAttachments(attachmentVO);    
				                    	   }
		                    	   }
		                       else if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("ahcAttachments"))
			                       {
			                    	   attachmentVO.setCaseId(caseId);
			                    	   msg1 = attachmentService.upldAhcAttachments(attachmentVO);   
			                       }
		                       if(i>1)
		                    	   msg.append(" , " + msg1 );
		                       else
		                    	   msg.append(msg1);   
		            	 }
		            	 }catch(Exception e)
		            	 {
		            		 resultMsg = ASRIConstants.ATTCH_UPLD_FAILURE + "  \\'"+ formFileObj.getFileName()+ "\\' \\n";
		            		e.printStackTrace(); 
		            	 }
		            	 if(request.getParameter("winOpen")!=null && !request.getParameter("winOpen").equalsIgnoreCase("") ){
		            		 request.setAttribute("winOpen",request.getParameter("winOpen"));
		            	 }
		            	  request.setAttribute("ResultMsg",msg.toString()+" uploaded ");
			              
		             }
		             }
	            	 lStrFlagStatus = "onload";	
		        }
	            
	            if(lStrFlagStatus!= null && "removeAttachments".equals(lStrFlagStatus)) 
		        {
	            	request.setAttribute("newBornBaby", request.getParameter("newBornbaby"));
	            	newBornLocal=request.getParameter("newBornbaby");
	                Map<String,Object> hParamMap = new HashMap<String,Object>(); 
	                String doc_seq_id= request.getParameter("docType") ;
	                String msg = attachmentService.removeAttachments(doc_seq_id,lStrEmpId);
	                request.setAttribute("viewAttach", "");
	                request.setAttribute("ResultMsg","Selected attachments are removed successfully");
	                
	                lStrFlagStatus = "onload";	
	            }  
	            if(lStrFlagStatus!= null && "removePreauthAttachments".equals(lStrFlagStatus)) 
		        {
	            	request.setAttribute("newBornBaby", request.getParameter("newBornbaby"));
	            	
	            	newBornLocal=request.getParameter("newBornbaby");
	                Map<String,Object> hParamMap = new HashMap<String,Object>(); 
	                String doc_seq_id= request.getParameter("docType") ;
	                String msg = attachmentService.removePreauthAttachments(doc_seq_id,lStrEmpId);
	                request.setAttribute("viewAttach", "");
	                request.setAttribute("ResultMsg","Selected attachments  removed successfully");
	                
	                lStrFlagStatus = "onload";	
	            } 
	            if(lStrFlagStatus!= null && "removeAhcAttachments".equals(lStrFlagStatus)) 
		        {
	                Map<String,Object> hParamMap = new HashMap<String,Object>(); 
	                String doc_seq_id= request.getParameter("docType") ;
	                String msg = attachmentService.removeAhcAttachments(doc_seq_id,lStrEmpId);
	                request.setAttribute("viewAttach", "");
	                request.setAttribute("ResultMsg","Selected attachments  removed successfully");
	                
	                lStrFlagStatus = "onload";	
	            } 
	            if(lStrFlagStatus!= null && "removefollowUpAttachments".equals(lStrFlagStatus)) 
		        {
	                Map<String,Object> hParamMap = new HashMap<String,Object>(); 
	                String doc_seq_id= request.getParameter("docType") ;
	                String msg = attachmentService.removeFollowUpAttachments(doc_seq_id,lStrEmpId);
	                request.setAttribute("viewAttach", "");
	                request.setAttribute("ResultMsg","Selected attachments  removed successfully");
	                
	                lStrFlagStatus = "onload";	
	            }
	            if(lStrFlagStatus!= null && "removechronicAttachments".equals(lStrFlagStatus)) 
		        {
	                Map<String,Object> hParamMap = new HashMap<String,Object>(); 
	                String doc_seq_id= request.getParameter("docType") ;
	                String msg = attachmentService.removeChronicAttachments(doc_seq_id,lStrEmpId);
	                request.setAttribute("viewAttach", "");
	                request.setAttribute("ResultMsg","Selected attachments  removed successfully");
	                
	                lStrFlagStatus = "onload";	
	            }
	            if(lStrFlagStatus!= null && "onload".equals(lStrFlagStatus)) 
		        {
		        	
		        	      HashMap<String,Object> hParamMap = new HashMap<String,Object>(); 
		        	      String caseAttachmentFlag = request.getParameter("caseAttachmentFlag");
		        	      String newBornBaby=request.getParameter("newBornBaby");
		        	      if(newBornBaby==null)
		        	    	  newBornLocal=newBornBaby;
		        	      request.setAttribute("newBornBaby", newBornBaby);
		        	      String viewFlag = request.getParameter("view");
		        	      String winopenFlag = request.getParameter("openWin");
		        	      request.setAttribute("winOpen", winopenFlag);
		        	      String cochRem=request.getParameter("cochRem");
		        	      request.setAttribute("cochRem", cochRem);
		        	      String chronicFlag=null;
		        	      String opAttach=request.getParameter("opAttach");
		        	      request.setAttribute("opAttach",opAttach);
		        	      /**
		                   * Get case Attachments
		                   */
		        	      if(caseAttachmentFlag != null && caseAttachmentFlag.equalsIgnoreCase("Y"))
		        	      {
		        	    	 String lStrCaseStatus = null; 
		        	    	 String lStrUserGroup = null;
		        	    	 String userGrpType="userGroup_";
		        	    	 /*added to identify followup cases*/
		        	    	 boolean followUpCheck = false;
		        	    	 String chronicStatus=null;
		        	    	 String disableFlag=(String) session.getAttribute("disableFlag");
		        	    	 if(request.getParameter("caseId")!=null)
		        	    	 
		        	    	 followUpCheck = request.getParameter("caseId").contains("/");
		        	    	  /**
						    	 * check for which view should be enabled
						    	 */
		        	    	 if(request.getParameter("caseId") != null && !request.getParameter("caseId").equalsIgnoreCase("") && !followUpCheck)
			      		    	{
			        	    	request.setAttribute("caseId", request.getParameter("caseId"));
			        	    	hParamMap.put("caseId",request.getParameter("caseId"));
			      		    	lStrCaseStatus = preauthService.getCaseStatus(request.getParameter("caseId"));
			        	    	}
		        	    	 /*added for chronic op*/
		        	    	 if(request.getParameter("chronicId") != null && !request.getParameter("chronicId").equalsIgnoreCase(""))
			      		    	{
			        	    	
		        	    		 request.setAttribute("chronicId", request.getParameter("chronicId"));
			        	    	hParamMap.put("chronicId",request.getParameter("chronicId"));
			        	    	//String installment=null;
			        	    	String installment= (String) session.getAttribute("installment");
			        	    	int inst=Integer.parseInt(installment);
				          		  request.setAttribute("inst", inst);
				          		chronicFlag="Y";
			        	    	//System.out.println("installment is : "+installment);
			        	    	if(installment==null)
			        			{
			        				installment="1";
			        			}
			        	    	System.out.println("installment is :"+installment);
			        	    	String chronicNo=request.getParameter("chronicId")+"/"+installment;
			        	    	hParamMap.put("chronicNo",chronicNo);
			      		    	lStrCaseStatus = chronicOPService.getChronicStatus(request.getParameter("chronicId"),chronicNo);
			      		    	chronicStatus=lStrCaseStatus;
			      		    	
			        	    	}
		        	    	 if(request.getParameter("UpdType")!=null && request.getParameter("UpdType").equalsIgnoreCase("chronicAttach")){
		        	    		 
					      		    userGrpType="chronicGroup_";
		        	    	 }
		        	    	 
		        	    	 
		        	    	 /*added for followup*/
		        	    	 if(request.getParameter("UpdType")!=null && request.getParameter("UpdType").equalsIgnoreCase("ehfFollowUp")){
		        	    		 String followUpId="";
		        	    	   if(request.getParameter("followupId") != null && !request.getParameter("followupId").equalsIgnoreCase(""))
					      		    {
		        	    		   followUpId = request.getParameter("followupId");
					      		    }
		        	    	   else if(followUpCheck){
		        	    		   followUpId = request.getParameter("caseId");
		        	    		   
		        	    	   }
		        	    	   if(followUpId!=null)
        	    			   {
	    			   				if(followUpId.length()>0)
	    			   					{
	    			   						int lstIndex=followUpId.lastIndexOf('/');
	    			   						String followUps=followUpId.substring(lstIndex+1, followUpId.length());
	    			   						if(followUps!=null && !"".equalsIgnoreCase(followUps))
	    			   							{
	    			   								if(followUps.equalsIgnoreCase("F"))
	    			   									followUps="0";
	    			   							}
	    			   						request.setAttribute("followUps",followUps);
	    			   					}
        	    			   }
		        	    	        request.setAttribute("caseId", followUpId);
			        	    	    hParamMap.put("caseId",followUpId);
					      		    lStrCaseStatus = preauthService.getFollowUpStatus(followUpId);
					      		    userGrpType="followupGroup_";
		        	    	 }
		        	    	 /*added for Cochlear followup*/
		        	    	 if(request.getParameter("UpdType")!=null && request.getParameter("UpdType").equalsIgnoreCase(config.getString("ehfCoch")))
			        	    	 {
			        	    		 String cochFollowUpId="";
			        	    		 if(request.getParameter("cochFollowupId") != null && !request.getParameter("cochFollowupId").equalsIgnoreCase(""))
			        	    			cochFollowUpId = request.getParameter("cochFollowupId");
				        	    	 else if(followUpCheck)
				        	    	    cochFollowUpId = request.getParameter("caseId");
			        	    		 if(cochFollowUpId!=null)
		        	    			   {
			    			   				if(cochFollowUpId.length()>0)
			    			   					{
			    			   						com.ahct.model.EhfCochlearFollowup ec=attachmentService.getCochFlpDtls(cochFollowUpId);
			    			   						if(ec!=null)
			    			   							{
			    			   								if(ec.getCochlearFollowupCount()!=null)
			    			   									{
			    			   										request.setAttribute("cochfollowUps",ec.getCochlearFollowupCount());
			    			   									}
			    			   							}
			    			   					}
		        	    			   }
			        	    		 request.setAttribute("caseId", cochFollowUpId);
			        	    		 hParamMap.put("caseId",cochFollowUpId);
			        	    		 lStrCaseStatus = preauthService.getFollowUpStatus(cochFollowUpId);
			        	    		 userGrpType="CochlearFlpStatus_";
			        	    	 }
		      		    	lStrUserGroup = config.getString(userGrpType+lStrCaseStatus);
		      		    	System.out.println("lstrcase status in attachment action : "+lStrCaseStatus);
		      		    	System.out.println("lstrUserGroup in attachment action : "+lStrUserGroup);
		      		    	for(LabelBean labelBean:rolesList)
		    		    	{
		      		    		System.out.println("group in attachment action "+labelBean.getID());
		      		    		lStrUserGroup = config.getString(userGrpType+lStrCaseStatus);
		    		    	if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(lStrUserGroup) || 
		    		    				(lStrUserGroup!=null && lStrUserGroup.contains("~"+labelBean.getID()+"~"))))
		    		    	{
		    		    		lStrUserGroup= labelBean.getID();
		    		    		break;	
		    		    	}
		    		    	else
		    		    		lStrUserGroup = null;
		    		    	}
		    		    	if(rolesList == null || rolesList.isEmpty() )
		    		    	{
		    		    		lStrUserGroup = null;	
		    		    	}
		    		    	/*if(request.getParameter("caseApprovalFlag") != null && !request.getParameter("caseApprovalFlag").equalsIgnoreCase("Y"))
		    		    	{
		    		    		lStrUserGroup = null;	
		    		    	}*/
		    		    	if(request.getParameter("UpdType")!=null && request.getParameter("UpdType").equalsIgnoreCase("chronicAttach")){
		    		    	if(disableFlag != null && disableFlag.equalsIgnoreCase("Y"))
		    		    	{
		    		    		lStrUserGroup = null;	
		    		    	}}
		    		    	
		    		    	System.out.println(" final lstrUserGroup in attachment action : "+lStrUserGroup);
						    	if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("preauth_inpatient_caseReg")))
						    	)
						    	{
						    		request.setAttribute("viewType", "medco");
						    	}
						    	/*added for chronic op*/
						    	if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("Chronic.Medco")) && lStrCaseStatus.equalsIgnoreCase(config.getString("CO-MEDCO-SCREENING")))
						    	)
						    	{
						    		request.setAttribute("viewType", "medco");
						    	}
						    	if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("EHF.Claims.DISCHARGE")))
						    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("EHF.Claims.HeadPending")))
						    			
						    	)
						    			
						    	{
						    		request.setAttribute("viewType", "medco");
						    	}
						    	if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("EHF.FollowUP.FlpDone")))
						    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("EHF.FollowUP.HeadPending")))
						                ||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("EHF.FollowUP.FTDPending")))			
						    	)
						    			
						    	{
						    		request.setAttribute("viewType", "medco");
						    	}
						    	if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("Cochlear_FollowUp_Done")))
						    			||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("CochlearCommitteePending")))
						                ||(lStrUserGroup != null && lStrUserGroup.equalsIgnoreCase(config.getString("preauth_medco_role")) && lStrCaseStatus.equalsIgnoreCase(config.getString("CochlearDyEoPending")))			
						    	)
						    			
						    	{
						    		request.setAttribute("viewType", "medco");
						    	}
						    	
		        	    	  hParamMap.put("UpdType",request.getParameter("UpdType"));
			                 
			                  hParamMap.put("userRole",rolesList);
			                  hParamMap.put("viewType","CASE_PRE");
			                  if(request.getParameter("UpdType")!=null && request.getParameter("UpdType").equalsIgnoreCase("chronicAttach")){
			                	  hParamMap.put("viewType","CHRONIC_MEDCO_UPD");
			                  }
			                  hParamMap.put("preauthFlag",request.getParameter("PreauthFlag"));
			                  hParamMap.put("userSpecificRole",lStrUserRole);
			                  hParamMap.put("chronicStatus",chronicStatus);
			                  request.setAttribute("chronicStatusId", request.getParameter("chronicStatusId"));
			                 
			                  request.setAttribute("UpdType", request.getParameter("UpdType"));
			                  if(request.getParameter("UpdType")!=null )
			                  	{
			                	  if(request.getParameter("UpdType").equalsIgnoreCase("ehfFollowUp"))
			                		  request.setAttribute("followUpFlg","Y");
			                	  if(request.getParameter("UpdType").equalsIgnoreCase(config.getString("ehfCoch")))
			                		  request.setAttribute("cochFollowUpFlg","Y");
			                		  
			                  	}
			                  request.setAttribute("userRole", userRole);
			                  request.setAttribute("PreauthFlag", request.getParameter("PreauthFlag"));
			                  hParamMap.put("caseApprovalFlag", request.getParameter("caseApprovalFlag"));
			                  hParamMap.put("newBornBaby", newBornBaby);
			                  //Added to check pharma bills are necessary
			                  String lstrCaseId= request.getParameter("caseId");
			                  String checkPharmAttch = attachmentService.getPharmaBillAttch(lstrCaseId);
			                  request.setAttribute("caseApprovalFlag", request.getParameter("caseApprovalFlag"));
			                  lResMap =   attachmentService.getPreauthViewAttachmentsHql(hParamMap);  
		        	    	  //lResMap =   attachmentService.getPreauthAttachmentsHql(hParamMap);  
		        	    	  if(lResMap!=null && lResMap.size()>0)
		        	    	  {
			                	  List<AttachmentVO> atchmtlist=(List<AttachmentVO>) lResMap.get("addAttachmentList");
			                	  List<AttachmentVO> finAtList=new ArrayList<AttachmentVO>(); 
			                	  if(atchmtlist!=null)
			                	  {
								   String organTransplantAttach=config.getString("organTransAttachments");
			                		  String[] organAttach=organTransplantAttach.split("~");
			                	   String IUIAttachments=config.getString("IUIAttachments");
			                			String[] IUIAttach=IUIAttachments.split("~");
			                		  for(AttachmentVO it:atchmtlist)
			                		  {
			                			  if(!it.getCmbDtlID().equals(config.getString("chronic_attach_Patient_MEDCO_NVM")) && !it.getCmbDtlID().equals(config.getString("chronic_attach_Unwillingness_letter_Drugs")) )
			                			  {
			                				  finAtList.add(it);
			                			  }
			                			  if(!"ehfFollowUp".equalsIgnoreCase(request.getParameter("UpdType")))
			                			  {
				                			  if(!"".equalsIgnoreCase(checkPharmAttch) && "Failure".equalsIgnoreCase(checkPharmAttch))
				                			  {
					                			if(it.getCmbDtlID().equals("CD163"))
					                			{
					                				finAtList.remove(it);
					                			}
				                			  }
			                			  }
			                		  }

			                		  
									  boolean organFlag=false;
									if(organFlag==false)
		                			  { 
			                		  for(AttachmentVO it:atchmtlist)
			                		  {
		                				  if(organAttach.length>0)
		                				  {
		                					  for(int i=0;i<organAttach.length;i++)
		                					  {
		                						  if(it.getCmbDtlID().equalsIgnoreCase(organAttach[i]))
		                								  {
		                							  		finAtList.remove(it);
		                								  }
		                					  }
		                				  }
		                			  }
									  }
										boolean IUIflag = false;
										if(IUIflag==false)
			                			  { 
				                		  for(AttachmentVO it:atchmtlist)
				                		  {
			                				  if(IUIAttach.length>0)
			                				  {
			                					  for(int i=0;i<IUIAttach.length;i++)
			                					  {
			                						  if(it.getCmbDtlID().equalsIgnoreCase(IUIAttach[i]))
		                								  {
		                							  		finAtList.remove(it);
		                								  }
		                					  }
		                				  }
		                			  }
									  }
			                		  lResMap.put("addAttachmentList", finAtList);
			                	  }
			                	  else
			                	  {
			                		  lResMap.put("addAttachmentList", atchmtlist);
			                		  
			                	  }
			                  }
			                  request.setAttribute("RestrictFlag", "0");
		        	    	  
		        	    	  if(request.getParameter("chronicId") != null && !request.getParameter("chronicId").equalsIgnoreCase(""))
			      		    	{
			        	    	  lStrResultPage="chronicAttachments";
			      		    	}
		        	    	  else
		        	    	  {
		        	    		  lStrResultPage="caseAttachments";
		        	    	  }
		        	      }
		        	      /**
		                   * Get Enrollment Attachments
		                   */
		        	      else if (caseAttachmentFlag != null && caseAttachmentFlag.equalsIgnoreCase("CP")){		        	    	  
		        	    	  String upType = request.getParameter("UpdType");
		        	    	  String fromDatePay = request.getParameter("fromDatePay");
		        	    	  String toDatePay = request.getParameter("toDatePay");
		        	    	  String flag = request.getParameter("flag");
		        	    	  String folderFlag = request.getParameter("folderFlag");
		        	    	  
		        	    	  hParamMap.put("UpdType", upType);
		        	    	  hParamMap.put("fromDatePay", fromDatePay);
		        	    	  hParamMap.put("toDatePay", toDatePay);
		        	    	  
		        	    	  lResMap =   attachmentService.getPayResponseAttachment(hParamMap);
		        	    	 		        	    	  
		        	    	  attachmentForm.setFromDate((String) lResMap.get("fromDatePay"));
		        	    	  attachmentForm.setToDate((String) lResMap.get("toDatePay"));
		        	    	  
		        	    	  if(flag!=null && flag.equalsIgnoreCase("Res")){		        	    		  
		        	    		  
		        	    		  String FilePath = ClaimsConstants.EMPTY;
		        	    	         String lStrSrcDir= config.getString("mapPath")
		        	    				+ config.getString("claimPayment_filePath");
		        	    	         if(folderFlag!=null && folderFlag.equalsIgnoreCase("OutFol"))
		        	    	         lStrSrcDir = lStrSrcDir + config.getString("claimPKIOutput_filePath");
		        	    	         else if(folderFlag!=null && folderFlag.equalsIgnoreCase("RecClaimFol"))
		        	    	         lStrSrcDir = lStrSrcDir + config.getString("localRecievedPath");
		        	    	         
		        	    		  String lExistingFiles = vectorToString(listFileNames(lStrSrcDir), "\n");
		        	    		  List<AttachmentVO>	lstNames = (List<AttachmentVO>) lResMap.get("lStrAttachmentList");
		        	    		  List<AttachmentVO> lStrAttachments =  new ArrayList<AttachmentVO>();
		        	    		  for(AttachmentVO attachmentVO:lstNames){
		        	    			  
		        	    			  AttachmentVO attachment = new AttachmentVO();
		        	                  String lStrNewFileName=ClaimsConstants.EMPTY,lStrFileName1=ClaimsConstants.EMPTY,lStrFileName2=ClaimsConstants.EMPTY,lStrFilePath=ClaimsConstants.EMPTY;
		        	                  lStrNewFileName=attachmentVO.getFileName() ;
		        	                  lStrFileName1=lStrNewFileName.substring(ClaimsConstants.ZERO_VAL,lStrNewFileName.lastIndexOf("."));
		        	                  lStrFileName2=lStrNewFileName.substring(lStrNewFileName.lastIndexOf(".")+1,lStrNewFileName.length());
		        	                  lStrNewFileName=lStrFileName1+".R"+lStrFileName2;
		        	                  
		        	                  lStrFilePath=lStrSrcDir + ClaimsConstants.SLASH + lStrNewFileName;//138
		        	                  if(lExistingFiles.contains(lStrNewFileName)){
		        	                  attachment.setFileName(lStrNewFileName);
		        	                  attachment.setFilePath(lStrFilePath);
		        	                  lStrAttachments.add(attachment);    	    
		        	                  }		        	    			  
		        	    		  }
		        	    		  lResMap.put("AttachmentList",lStrAttachments);
		        	    	  }
		        	    	  else{
		        	    		  lResMap.put("AttachmentList", lResMap.get("lStrAttachmentList"));
		        	    	  }
		        	    	  request.setAttribute("RestrictFlag", "0");
		        	    	  request.setAttribute("ClaimPaymentFlag","Y");
		        	    	  request.setAttribute("UpdType",(String) lResMap.get("UpdType"));
		        	    	  request.setAttribute("flag",flag);
		        	    	  request.setAttribute("folderFlag",folderFlag);
		        	    	  lStrResultPage="claimPaymentAttachments";
		        	    	  // lStrResultPage="caseAttachments";
		        	      }
		        	      else if (caseAttachmentFlag != null && caseAttachmentFlag.equalsIgnoreCase("AHC")){		        	    	  
		        	    	
			        	    	 String lStrUserGroup = null;
			        	    	 if(request.getParameter("ahcId") != null && !request.getParameter("ahcId").equalsIgnoreCase("") )
				      		    	{
				        	    	request.setAttribute("ahcId", request.getParameter("ahcId"));
				        	    	hParamMap.put("ahcId",request.getParameter("ahcId"));
				      		    	
				        	    	}
			        	    	 try{
			        	    	 EhfAnnualCaseDtls ahcStatus = ahcClaimsService.getAhcStatusDtls(request.getParameter("ahcId"));
			        	    	 if(ahcStatus!=null){
			        	    		 request.setAttribute("status", ahcStatus.getAhcStatus());
			        	    	 }
			        	    	 }
			        	    	 catch(Exception e){
			        	    		 e.printStackTrace();
			        	    	 }
			        	    	 lStrUserGroup=roleId;
			    		    	
							    if(lStrUserGroup != null && (lStrUserGroup.equalsIgnoreCase(config.getString("ahc_medco_role"))))
							    	{
							    		request.setAttribute("viewType", "medco");
							    	}
							    
							    	
			        	    	  hParamMap.put("UpdType",request.getParameter("UpdType"));
				                  hParamMap.put("userRole",rolesList);
				                  hParamMap.put("viewType","MEDCO_AHC_UPD");
				              
				                  request.setAttribute("UpdType", request.getParameter("UpdType"));
				                  request.setAttribute("userRole", userRole);
				                 
				                  lResMap =   attachmentService.getAhcAttachmentsHql(hParamMap);  
			        	    	  request.setAttribute("RestrictFlag", "0");
			        	    	  if(request.getParameter("winOpen")!=null && !request.getParameter("winOpen").equalsIgnoreCase("") ){
					            		 request.setAttribute("winOpen",request.getParameter("winOpen"));
					            	 }
			        	    	  lStrResultPage="ahcAttachments";
		        	      }
		        	     
		      		    
		        	      else
		        	      {
		        	    	  hParamMap.put("EnrollParntId",request.getParameter("EnrollParntId"));
			                  hParamMap.put("upd_type",request.getParameter("upd_type"));
			                  hParamMap.put("aadharExists", request.getParameter("aadharExists"));
			                  hParamMap.put("AttachType","CD3000");
			                  hParamMap.put("viewFlag",request.getParameter("view"));;
			                  //lResMap =   attachmentService.getEnrollmentAttachments(hParamMap);
			                  lStrResultPage="onload";
		        	      }
		                 
		                  if(lResMap!=null)
		                	  {
		                	  	if(lResMap.get(ASRIConstants.NEWSTORAGE1)!=null)
		                	  		request.setAttribute(ASRIConstants.NEWSTORAGE1,(String)lResMap.get(ASRIConstants.NEWSTORAGE1));   
		                	  }
		                  request.setAttribute("AttachmentMap",lResMap); 
		                  request.setAttribute("mode",upd_type);
		                  request.setAttribute("EnrollParntId",request.getParameter("EnrollParntId"));
				          request.setAttribute("upd_type",request.getParameter("upd_type"));
				         request.setAttribute("aadharExists", request.getParameter("aadharExists"));
				          request.setAttribute("viewFlag",viewFlag);
				          request.setAttribute("chronicFlag",chronicFlag);
		                  hParamMap.clear();
		                  hParamMap=null;  
		        	      
		        	return mapping.findForward(lStrResultPage);
		        }

	            	
	            
	            if ( lStrFlagStatus != null && lStrFlagStatus.equalsIgnoreCase ( "viewAttachment" ) )
			      {
	            	/**
	            	 * to show attachments for special Investigations
	            	 */
	            	 String lStrFilePath = null;
	            	   String docSeqId = request.getParameter("docSeqId");
	            	   String medicalFlg = request.getParameter("medicalFlg");

	            	   if(  (docSeqId != null && !docSeqId.equalsIgnoreCase(""))  && ("".equalsIgnoreCase(medicalFlg) || medicalFlg == null))
	            	   {
	            		   lStrFilePath = attachmentService.getIvestPath(docSeqId);  
	            	   }
	            	   else if( !"".equalsIgnoreCase(medicalFlg) && "Y".equalsIgnoreCase(medicalFlg))
	            	   {
	            		   lStrFilePath = request.getParameter("docSeqId"); 
	            	   }
	            	   else
	            		   lStrFilePath = request.getParameter("filePath"); 
	            	    File file = null ;
				        FileInputStream fis = null ;
				        DataInputStream dis = null ;
				        String lStrType = null;
				        ServletOutputStream out = response.getOutputStream();
			          /**
			           * 
			           */
			          String fileExt = lStrFilePath.substring((lStrFilePath.lastIndexOf(".")+1));
			          String lStrFileName=lStrFilePath.substring((lStrFilePath.lastIndexOf("/")+1));
			          String attachMode="attachment";
			          if(fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG) || fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPEG) 
			        		  || fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF) || fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_PDF)){
			              attachMode="inline";
			          }
			           lStrContentType=ASRIConstants.FILE_EXT.get(fileExt);
			           if(lStrContentType==null || "".equalsIgnoreCase(lStrContentType)){
			        	   lStrContentType="image/jpeg";
			           }
			          
			          /**
			           * 
			           */
			           String dir =  lStrFilePath  ;
			          byte[] lbBytes = null ;
			          try
			          {
			            if ( lStrFileName != null && !lStrFileName.equals ( "" ) )
			            {
			              file = new File ( dir ) ; 
			              fis = new FileInputStream ( file ) ;
			              dis = new DataInputStream ( fis ) ;
			              lbBytes = new byte[ dis.available () ] ;
			              fis.read ( lbBytes ) ;
			              request.setAttribute ( "File", lbBytes ) ;
			              request.setAttribute ( "ContentType", lStrContentType ) ;
			              request.setAttribute ( "FileName", lStrFileName ) ;
			              request.setAttribute ( "Extn", lStrType ) ;
			              response.setContentType ( lStrContentType ) ;
			              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);//006
			              out.write(lbBytes);
			              //out.close();			             
			            }
			            else
			            {
			              lbBytes = new byte[ 0 ] ;
			            }
			          }
			          catch ( Exception e )
			          {
			            e.getMessage () ;
			            e.printStackTrace();
			          }
			          finally
			          {
			        	  out.close();
			          }
			          
			      }
	           
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	            request.setAttribute("IOExMsg","ERROR: Unable to upload attachment");//007
	            
	            LOGGER.error("Exception in AttachmentRH->"+e.getMessage()); 
	        }
	        
	        
	        
		 /**
		  * embedding code end
		  */
		 return null;
		 
	    }
	
	 /** Convert a Vector to a delimited String 033*/
    private String vectorToString (Vector v, String delim) {
            StringBuffer sb = new StringBuffer();
            String s = "";
            for (int i = 0; i < v.size(); i++) {
                    sb.append(s).append((String)v.elementAt(i));
                    s = delim;
            }
            return sb.toString();
    }
    /** 033 Get the list of files in the current directory as a Vector of Strings 
     * (excludes subdirectories) */
   public Vector listFileNames (String filePath) throws IOException{

      File folder = new File(filePath);
      File[] files = folder.listFiles(); 
      Vector v = new Vector();
      if(files !=null)
      {                   
      for (int i = 0; i < files.length; i++) {
              if (!files[i].isDirectory())
                      v.addElement(files[i].getName());
      }
      }
       return v;
   }
	
}

