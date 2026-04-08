package com.ahct.panelDoctor.action;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.ahct.panelDoctor.form.PanelDocPayForm;
import com.ahct.panelDoctor.service.PanelDocPayService;

import com.ahct.panelDoctor.util.PanelDocConstants;

import com.ahct.panelDoctor.vo.PanelDocPayVO;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfmDepts;
import com.ahct.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.ahct.common.service.CommonService;
import com.ahct.common.service.WorkFlowCommonService;

public class PanelDocPayAction extends Action{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		PanelDocPayForm panelDocPayForm= (PanelDocPayForm)form;
		String lStrFlagStatus = request.getParameter("actionFlag");		
		HttpSession session = request.getSession ( false ) ;
		String UserId=null;
		String lStrUserState=null;
		String usrGrpId=null;
				
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0); 
		
		ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		CommonService commonService = (CommonService) ServiceFinder.getContext(request).getBean("commonService");		
		PanelDocPayService panelDocPayService =(PanelDocPayService)ServiceFinder.getContext(request).getBean("panelDocPayService");
		WorkFlowCommonService workFlowCommonService=(WorkFlowCommonService)ServiceFinder.getContext(request).getBean("workFlowCommonService");
		
		/*
		 * Session Timeout
		 */
		String callType=null;
		if (request.getParameter("callType") != null
				&& !PanelDocConstants.EMPTY.equals(request
						.getParameter("callType"))) {
			callType = request.getParameter("callType");
		}
		
		String lStrResultPage = HtmlEncode.verifySession ( request, response ) ;
		 if ( lStrResultPage.length () > 0 ){
			 if(callType != null && "Ajax".equals(callType))
			 {
				request.setAttribute("AjaxMessage", "SessionExpired");
				return mapping.findForward("ajaxResult");	
			 }
			 else{
		     return mapping.findForward ("sessionExpire") ;
			 }
		 }
		/*
		 * End of Session Timeout
		 */
		
		
		if (session.getAttribute("UserID").toString() != null) {
			UserId=(String) session.getAttribute("UserID");
		}
		
		
		if (session.getAttribute("userState").toString() != null) {
			lStrUserState=(String) session.getAttribute("userState");
		}
		
		/*
		 * To get the group list
		 */
		
		List<LabelBean> groupList=(List<LabelBean>)session.getAttribute("groupList");
		
		List<String> grpList=new ArrayList<String>();
		List<LabelBean> paymentStatus=new ArrayList<LabelBean>();
		
		long count=1;
		
		for(int i=0;i<groupList.size();i++){
			String grp=groupList.get(i).getID();
			grpList.add(i, grp);
			
		}
		
		/*
		 * end of group list
		 */
		
		/*
		 * Getting Payment Status depending on Group
		 */
	    
		String status=null;
		long drpDwnListIndex=1;
		List<String> splitList=new ArrayList<String>();
		String singleRowShow=null;
		
	    if(grpList.contains(config.getString("FIN.AccountsEO2Grp")))
	    {
	    	usrGrpId=config.getString("FIN.AccountsEO2Grp");
	    	status=config.getString("FIN.AccountsEO2Status");
	    	splitList=Arrays.asList(status.split("~"));
	    	for(String str:splitList) 
	    		{
	    			if(!str.equalsIgnoreCase("Payments Sent Back by CEO")) 
						{	
							paymentStatus.add(new LabelBean(drpDwnListIndex,str));
							drpDwnListIndex++;
						}
	    			else if(str.equalsIgnoreCase("Payments Sent Back by CEO")
	    					)
	    				{
		    				paymentStatus.add(new LabelBean(drpDwnListIndex,str));
							drpDwnListIndex++;
	    				}
	    				
	    		}	
	    	request.setAttribute("PaymentStatusList", paymentStatus);
	    	
	    }
	    if(grpList.contains(config.getString("FIN.AccountsEOGrp")))
	    {
	    	usrGrpId=config.getString("FIN.AccountsEOGrp");
	    	status=config.getString("FIN.AccountsEOStatus");
	    	splitList=Arrays.asList(status.split("~"));	
			for(String str:splitList) 
			{
				if(!str.equalsIgnoreCase("Payments Sent Back by CEO")) 
					{	
						paymentStatus.add(new LabelBean(drpDwnListIndex,str));
						drpDwnListIndex++;
					}
				else if(str.equalsIgnoreCase("Payments Sent Back by CEO")
						)
					{
	    				paymentStatus.add(new LabelBean(drpDwnListIndex,str));
						drpDwnListIndex++;
					}
			}
			request.setAttribute("PaymentStatusList", paymentStatus);
	    	
	    }
	    if(grpList.contains(config.getString("FIN.AccountsAOGrp")))
	    {
	    	usrGrpId=config.getString("FIN.AccountsAOGrp");
	    	status=config.getString("FIN.AccountsAOStatus");
	    	splitList=Arrays.asList(status.split("~"));	
	    	for(String str:splitList)
	    	{
	    		if(!str.equalsIgnoreCase("Payments Sent Back by CEO")) 
					{	
						paymentStatus.add(new LabelBean(drpDwnListIndex,str));
						drpDwnListIndex++;
					}
				else if(str.equalsIgnoreCase("Payments Sent Back by CEO")
						)
					{
	    				paymentStatus.add(new LabelBean(drpDwnListIndex,str));
						drpDwnListIndex++;
					}
			}
			request.setAttribute("PaymentStatusList", paymentStatus);
	    	
	    }
	    if(grpList.contains(config.getString("FIN.AccountsJEOGrp")))
	    {
	    	usrGrpId=config.getString("FIN.AccountsJEOGrp");
	    	status=config.getString("FIN.AccountsJEOStatus");
	    	splitList=Arrays.asList(status.split("~"));	
	    	for(String str:splitList) 
	    	{
	    		if(!str.equalsIgnoreCase("Payments Sent Back by CEO")) 
					{	
						paymentStatus.add(new LabelBean(drpDwnListIndex,str));
						drpDwnListIndex++;
					}
				else if(str.equalsIgnoreCase("Payments Sent Back by CEO")
						)
					{
	    				paymentStatus.add(new LabelBean(drpDwnListIndex,str));
						drpDwnListIndex++;
					}
			}
			request.setAttribute("PaymentStatusList", paymentStatus);
	    	
	    }
	    
	    if(grpList.contains(config.getString("FIN.CEOGrp")))
	    {
	    	usrGrpId=config.getString("FIN.CEOGrp");
	    	status=config.getString("FIN.AccountsCEOStatus");
	    	splitList=Arrays.asList(status.split("~"));	
	    	for(String str:splitList) {
				paymentStatus.add(new LabelBean(drpDwnListIndex,str));
				drpDwnListIndex++;
				}
			request.setAttribute("PaymentStatusList", paymentStatus);
	    	
	    }
	  
	 	/*
	 	 * End of Group Status
	 	 */
	    
	  /*
	   * To get Scheme Status If user belongs to both states
	   */
	  /*  if(lStrUserState!=null && lStrUserState.equals(PanelDocConstants.AP_TG_State))
	    {
	    	schemeStatus=commonService.getSchemeStatus() ;
	    	request.setAttribute("schemeStatusList",schemeStatus);
	    }*/
	  
	    /*
	     * Home Page 
	     */
	   	if(lStrFlagStatus!= null && "panelDocPayHome".equals(lStrFlagStatus)) 
        {
	   		panelDocPayForm.setPaymentStatusList(paymentStatus);
	   		panelDocPayForm.setUserType(lStrUserState);
	   		if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
	   			panelDocPayForm.setScheme(lStrUserState);
			}
	   		String searchFlag=request.getParameter("searchFlag");
	   		request.setAttribute("searchFlag",searchFlag);
	   		if(searchFlag!=null && "Y".equalsIgnoreCase(searchFlag))
	   			{
	   				paymentStatus=panelDocPayService.getCaseStatus("AllStats");
	   				panelDocPayForm.setPaymentStatusList(paymentStatus);
	   				request.setAttribute("PaymentStatusList", paymentStatus);
	   				panelDocPayForm.setFlag("On Load");
	   				return mapping.findForward("panelDocRept");
	   			}
	   		
	   		return mapping.findForward("panelDocPay");	
        }
	 
	   /*
	    * Home Page for CEO
	    */
	   	if(lStrFlagStatus!= null && "panelDocPayCEOHome".equals(lStrFlagStatus)) 
        {
	   		panelDocPayForm.setPaymentStatusList(paymentStatus);
	   		panelDocPayForm.setUserType(lStrUserState);
	   		if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
	   			panelDocPayForm.setScheme(lStrUserState);
			}
	   		return mapping.findForward("panelDocPmntCEO");	
        }
	   	
	   	
	    /*
	     * Home Page for CEO TDS
	     */
	   	if(lStrFlagStatus!= null && "panelDocTDSPayHome".equals(lStrFlagStatus)) 
        {
	   		panelDocPayForm.setPaymentStatusList(paymentStatus);
	   		panelDocPayForm.setUserType(lStrUserState);
	   		if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
	   			panelDocPayForm.setScheme(lStrUserState);
			}
	   		return mapping.findForward("panelDocTDSPmnt");	
        }
	   	
	   
		/*
		 * Start of get Cases for CEO Approval
		 */
		if(lStrFlagStatus!= null && "getCasesForCEO".equals(lStrFlagStatus))
		{
			float totalAmt=0;
			float amt=0;
			String failedList=null;
			int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
			
			String scheme=panelDocPayForm.getScheme();
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			else
			{
				if(panelDocPayForm.getScheme()==null)
					scheme=PanelDocConstants.AP_State;
				else
					scheme=panelDocPayForm.getScheme();
					
			}
			List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocCases=panelDocPayService.getPanelDocCasesForCEO(panelDocPayVO,startIndex,maxResults);
			
			
			int size=panelDocCases.size();
		
			if(panelDocCases!= null && panelDocCases.size()>0)
			 {
				
				if(size>0)
				{
					failedList=panelDocCases.get(size-1).getFailedList();
					panelDocCases.remove(size-1);
				}
				
				if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							maxResults=Integer.parseInt(request.getParameter("end_index"));
						else
							maxResults=100;
							
					startIndex=maxResults*(pageId-1);
				}
				else
					{
						pageId=1;
						maxResults=100;
						startIndex=0;
					}
				
				noOfRecords=panelDocCases.size();
				if(noOfRecords%maxResults==0)
					noOfPages=noOfRecords/maxResults;
				if(noOfRecords%maxResults!=0)
					noOfPages=(noOfRecords/maxResults)+1;
				
				
				List<PanelDocPayVO> panelDocCasesLst=panelDocPayService.getPanelDocCasesForCEO(panelDocPayVO,startIndex,maxResults);
				
				if(panelDocCasesLst.size()>0)
				{
					failedList=panelDocCasesLst.get(size-1).getFailedList();
					panelDocCasesLst.remove(size-1);
				}
				
				request.setAttribute("noOfRecords",noOfRecords);
				request.setAttribute("lastPage",noOfPages);
				request.setAttribute("start_index",startIndex);
				request.setAttribute("end_index",maxResults);
				request.setAttribute("endresults",startIndex+panelDocCasesLst.size());
				request.setAttribute("pageId",pageId);
				panelDocPayForm.setIndividualPayment("");
				panelDocPayForm.setPanelDocList(panelDocCasesLst);
				if(panelDocCasesLst==null || panelDocCasesLst.size()==0)
					{
						request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				else
					panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
			 }
			else
			{
				if(panelDocCases==null || panelDocCases.size()==0)
					request.setAttribute("list","N");
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
			}
			panelDocPayForm.setRejId("PayNow");
			for(int i=0;i<panelDocCases.size();i++){
				if(panelDocCases.get(i).getTOTAL_PNLDOC_AMT()!=null)
				amt=panelDocCases.get(i).getTOTAL_PNLDOC_AMT().floatValue();
				totalAmt=totalAmt+amt;
			}
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			
			
			if(failedList!=null && failedList!="")
			{
			  request.setAttribute("failAccList", failedList);
				
			}
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			panelDocPayForm.setUserType(lStrUserState);
			panelDocPayForm.setScheme(scheme);
			return mapping.findForward("panelDocPmntCEO");
			
		}
		
		
		if(lStrFlagStatus!= null && "getCEORemarks".equals(lStrFlagStatus))
			{
				PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
				String docId=request.getParameter("docId");
				String wId=request.getParameter("wId");
				if(wId!=null)
					panelDocPayVO.setwId(Long.parseLong(wId));
				panelDocPayVO.setDOC_ID(docId);
				List<PanelDocPayVO> panelDocCasesRemarksLst=panelDocPayService.getCEORemarksList(panelDocPayVO,"","");
				Map<String,Object> jsonData=new HashMap<String,Object>();
				jsonData.put("panelDocCasesRemarksLst", panelDocCasesRemarksLst);
				Gson gson=new Gson();
				String gsonString=gson.toJson(panelDocCasesRemarksLst);
				
				request.setAttribute("AjaxMessage",gsonString);
				return mapping.findForward("ajaxResult");
				
			}
		/**
		 * Added by Srikalyan for Panel Doctor payments Search
		 */
		if(lStrFlagStatus!= null && "getCasesSearch".equals(lStrFlagStatus))
			{
				String searchFlag=request.getParameter("searchFlag");
		   		request.setAttribute("searchFlag",searchFlag);
				int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
				
				List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
				PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
				String scheme=panelDocPayForm.getScheme();
				String type=request.getParameter("type"); 
				
				if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)||scheme==null){
					scheme=lStrUserState;
				}
				
				panelDocPayVO.setUSERTYPE(scheme);
				panelDocPayVO.setAction(panelDocPayForm.getPaymentStatus());
				panelDocPayVO.setMonth(panelDocPayForm.getMonth());
				panelDocPayVO.setDOC_NAME(panelDocPayForm.getDocName());
				panelDocPayVO.setDOC_ID(panelDocPayForm.getDocid());
				panelDocPayVO.setStartIndex(startIndex);
				panelDocPayVO.setEndIndex(maxResults);
				
				panelDocCases=panelDocPayService.getPanelDocCasesSearch(panelDocPayVO);
				
				if(panelDocCases!= null && panelDocCases.size()>0)
				 {
					
					if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
					{
						pageId=Integer.parseInt(request.getParameter("pageId"));
							
						if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
								maxResults=Integer.parseInt(request.getParameter("end_index"));
							else
								maxResults=100;
								
						startIndex=maxResults*(pageId-1);
					}
					else
						{
							pageId=1;
							maxResults=100;
							startIndex=0;
						}
					
					noOfRecords=panelDocCases.size();
					if(noOfRecords%maxResults==0)
						noOfPages=noOfRecords/maxResults;
					if(noOfRecords%maxResults!=0)
						noOfPages=(noOfRecords/maxResults)+1;
					
					panelDocPayVO.setStartIndex(startIndex);
					panelDocPayVO.setEndIndex(maxResults);
					
					List<PanelDocPayVO> panelDocCasesLst=panelDocPayService.getPanelDocCasesSearch(panelDocPayVO);
					if(type!=null)
						{
							if("CEOPending".equalsIgnoreCase(type))
								{
									//List<PanelDocPayVO> panelDocCasesRemarksLst=panelDocPayService.getCEORemarksList(panelDocPayVO,currGrpId,prevGrpId);
									//panelDocPayForm.setSentBackremarksLst(panelDocCasesRemarksLst);
									request.setAttribute("ceoRemarks", "show");
									request.setAttribute("sentBackRemDiv", "show");
								}	
						}
					request.setAttribute("noOfRecords",noOfRecords);
					request.setAttribute("lastPage",noOfPages);
					request.setAttribute("start_index",startIndex);
					request.setAttribute("end_index",maxResults);
					request.setAttribute("endresults",startIndex+panelDocCasesLst.size());
					request.setAttribute("pageId",pageId);
					panelDocPayForm.setIndividualPayment("");
					panelDocPayForm.setPanelDocList(panelDocCasesLst);
					if(panelDocCasesLst==null || panelDocCasesLst.size()==0)
						{
							request.setAttribute("list","N");
							panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
						}
					else
						panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
				 }
				else
				{
					panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					if(panelDocCases==null || panelDocCases.size()==0)
						request.setAttribute("list","N");
				}
				/*for(int i=0;i<panelDocCases.size();i++){
					if(panelDocCases.get(i).getTotalPnldocAmt()!=null)
					amt=panelDocCases.get(i).getTotalPnldocAmt().floatValue();
					totalAmt=totalAmt+amt;
				}*/
				
				paymentStatus=panelDocPayService.getCaseStatus("AllStats");
				panelDocPayForm.setPaymentStatusList(paymentStatus);
   				request.setAttribute("PaymentStatusList", paymentStatus);
   				
				panelDocPayForm.setPaymentStatus(panelDocPayForm.getPaymentStatus());
				panelDocPayForm.setMonth(panelDocPayForm.getMonth());
				panelDocPayForm.setAmount(0f);
				//panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
				
				//panelDocPayForm.setRejId(flag);
				panelDocPayForm.setUserType(lStrUserState);
				panelDocPayForm.setScheme(scheme);
				return mapping.findForward("panelDocRept");
				
			}
		
		
		/*
		 * Start of getCases for TDS approval
		 */
		if(lStrFlagStatus!= null && "getTDSCases".equals(lStrFlagStatus))
		{
			float totalAmt=0;
			String trustAccNo="";
			String scheme=panelDocPayForm.getScheme();
			List<PanelDocPayVO> panelDocTDSCases = new ArrayList<PanelDocPayVO>();
			
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			else
			{
				if(scheme==null)
					scheme=PanelDocConstants.AP_State;
			}
			//List<LabelBean> TDSStatus=commonService.getTDSStatus() ;
	    	//request.setAttribute("tdsStatusList",TDSStatus);
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String tdsStatus=panelDocPayForm.getTdsStatus();
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocTDSCases=panelDocPayService.getPanelDocTDSCases(panelDocPayVO);
			if(scheme.equals(PanelDocConstants.AP_State))
			{
				trustAccNo=PanelDocConstants.TRUST_TDS_ACCNO_AP;
			}
			else if(scheme.equals(PanelDocConstants.TG_State))
			{
				trustAccNo=PanelDocConstants.TRUST_TDS_ACCNO_TG;
			}
			if(panelDocTDSCases!= null && panelDocTDSCases.size()>0)
			 {
				panelDocPayForm.setPanelDocList(panelDocTDSCases);
				panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
			 }
			else
			{
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
			}
			panelDocPayForm.setRejId("PayNow");
			panelDocPayForm.setCaseStatus(PanelDocConstants.CLAIM_READY_PAYMENT);
			for(int i=0;i<panelDocTDSCases.size();i++){
				totalAmt=totalAmt+Float.parseFloat(panelDocTDSCases.get(i).getAMOUNT());
				panelDocTDSCases.get(i).setACCOUNTNO(trustAccNo);
			}
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			panelDocPayForm.setTdsStatusList(paymentStatus);
			panelDocPayForm.setTdsStatus(tdsStatus);
			panelDocPayForm.setUserType(lStrUserState);
			panelDocPayForm.setScheme(scheme);
			return mapping.findForward("panelDocTDSPmnt");
		}
		
		//End of getCases for TDS Approval
		
		//Start of View Rejected Cases
		if(lStrFlagStatus!= null && "viewRejctdCases".equals(lStrFlagStatus))
		{
			String searchFlag=request.getParameter("searchFlag");
	   		request.setAttribute("searchFlag",searchFlag);
			int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
			float totalAmt=0;
			float amt=0;
			List<PanelDocPayVO> panelDocRejCases = new ArrayList<PanelDocPayVO>();
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String monthsel="";
			String scheme=panelDocPayForm.getScheme();
			String dispType=panelDocPayForm.getDispType();
			panelDocPayVO.setDispType(dispType);
			
			//For getting Cases directly
			if(dispType==null)
				dispType="03";
			
			//for month wise selection
			if(dispType.equals("01")){
				monthsel=panelDocPayForm.getMonth();
				panelDocPayVO.setSelperiod(monthsel);
			}
			//End of month wise selection
			
			//for date wise selection
			if(dispType.equals("02")){
				panelDocPayVO.setFromDate(panelDocPayForm.getFromDate());
				panelDocPayVO.setToDate(panelDocPayForm.getToDate());
			}
			//end of date wise selection
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocRejCases=panelDocPayService.getAllRejctdCasesDtls(panelDocPayVO,startIndex,maxResults);
			if(panelDocRejCases!= null && panelDocRejCases.size()>0)
			 {
				
				if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							maxResults=Integer.parseInt(request.getParameter("end_index"));
						else
							maxResults=100;
							
					startIndex=maxResults*(pageId-1);
				}
				else
					{
						pageId=1;
						maxResults=100;
						startIndex=0;
					}
				
				noOfRecords=panelDocRejCases.size();
				if(noOfRecords%maxResults==0)
					noOfPages=noOfRecords/maxResults;
				if(noOfRecords%maxResults!=0)
					noOfPages=(noOfRecords/maxResults)+1;
				
				
				List<PanelDocPayVO> panelDocRejCasesLst=panelDocPayService.getAllRejctdCasesDtls(panelDocPayVO,startIndex,maxResults);
				
				request.setAttribute("noOfRecords",noOfRecords);
				request.setAttribute("lastPage",noOfPages);
				request.setAttribute("start_index",startIndex);
				request.setAttribute("end_index",maxResults);
				request.setAttribute("endresults",startIndex+panelDocRejCasesLst.size());
				request.setAttribute("pageId",pageId);
				
				if(panelDocRejCasesLst==null || panelDocRejCasesLst.size()==0)
					{
						request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				else
					panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
				panelDocPayForm.setPanelDocList(panelDocRejCasesLst);
				
			 }
			else
			{
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
				if(panelDocRejCases==null || panelDocRejCases.size()==0)
					request.setAttribute("list","N");
			}
			for(int i=0;i<panelDocRejCases.size();i++){
				if(panelDocRejCases.get(i).getTotalPnldocAmt()!=null)
				amt=panelDocRejCases.get(i).getTotalPnldocAmt().floatValue();
				totalAmt=totalAmt+amt;
			}
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			panelDocPayForm.setDispType(dispType);
			panelDocPayForm.setMonth(monthsel);
			panelDocPayForm.setAmount(0f);
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			return mapping.findForward("panelDocPay");
		}
		
		//End of get Rejected Cases for CEO
		
		if(lStrFlagStatus!= null && "viewRejctdCasesCEO".equals(lStrFlagStatus))
		{
			
			int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
			
			
			float totalAmt=0;
			float amt=0;
			List<PanelDocPayVO> panelDocRejCases = new ArrayList<PanelDocPayVO>();
			String scheme=panelDocPayForm.getScheme();

			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
							scheme=lStrUserState;
						}
						else
						{
							if(scheme==null)
								scheme=PanelDocConstants.AP_State;
						}
			
			panelDocRejCases=panelDocPayService.getAllRejctdCasesDtlsCEO(scheme,startIndex,maxResults);
			
			int size=panelDocRejCases.size();
			
			if(panelDocRejCases!= null && panelDocRejCases.size()>0)
			 {
				
				if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							maxResults=Integer.parseInt(request.getParameter("end_index"));
						else
							maxResults=100;
							
					startIndex=maxResults*(pageId-1);
				}
				else
					{
						pageId=1;
						maxResults=100;
						startIndex=0;
					}
				
				noOfRecords=panelDocRejCases.size();
				if(noOfRecords%maxResults==0)
					noOfPages=noOfRecords/maxResults;
				if(noOfRecords%maxResults!=0)
					noOfPages=(noOfRecords/maxResults)+1;
				
				
				List<PanelDocPayVO> panelDocRejCasesLst=panelDocPayService.getAllRejctdCasesDtlsCEO(scheme,startIndex,maxResults);
				
				request.setAttribute("noOfRecords",noOfRecords);
				request.setAttribute("lastPage",noOfPages);
				request.setAttribute("start_index",startIndex);
				request.setAttribute("end_index",maxResults);
				request.setAttribute("endresults",startIndex+panelDocRejCasesLst.size());
				request.setAttribute("pageId",pageId);
				
				panelDocPayForm.setPanelDocList(panelDocRejCasesLst);
				if(panelDocRejCasesLst==null || panelDocRejCasesLst.size()==0)
					{
						request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				else
					panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
			 }
			else
			{
				if(panelDocRejCases==null || panelDocRejCases.size()==0)
					request.setAttribute("list","N");
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
			}
			for(int i=0;i<panelDocRejCases.size();i++){
				if(panelDocRejCases.get(i).getTOTAL_PNLDOC_AMT()!=null)
				amt=panelDocRejCases.get(i).getTOTAL_PNLDOC_AMT().floatValue();
				totalAmt=totalAmt+amt;
			}
			
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			panelDocPayForm.setAmount(0f);
			return mapping.findForward("panelDocPmntCEO");
		}
		
		//End of get Rejected Cases
		
		//View Bank Rejected Payments
		if(lStrFlagStatus!= null && "viewBankRejctdCases".equals(lStrFlagStatus))
		{
			int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
			double totalAmt=0;
			List<PanelDocPayVO> panelDocRejCases = new ArrayList<PanelDocPayVO>();
			String scheme=panelDocPayForm.getScheme();

			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
							scheme=lStrUserState;
						}
						else
						{
							if(scheme==null)
								scheme=PanelDocConstants.AP_State;
						}		
			panelDocRejCases=panelDocPayService.getAllBankRejctdCases(scheme,startIndex,maxResults);
			if(panelDocRejCases!= null && panelDocRejCases.size()>0)
			 {
				
				if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							maxResults=Integer.parseInt(request.getParameter("end_index"));
						else
							maxResults=100;
							
					startIndex=maxResults*(pageId-1);
				}
				else
					{
						pageId=1;
						maxResults=100;
						startIndex=0;
					}
				
				noOfRecords=panelDocRejCases.size();
				if(noOfRecords%maxResults==0)
					noOfPages=noOfRecords/maxResults;
				if(noOfRecords%maxResults!=0)
					noOfPages=(noOfRecords/maxResults)+1;
				
				List<PanelDocPayVO> panelDocRejCasesLst=panelDocPayService.getAllBankRejctdCases(scheme,startIndex,maxResults);
				
				
				request.setAttribute("noOfRecords",noOfRecords);
				request.setAttribute("lastPage",noOfPages);
				request.setAttribute("start_index",startIndex);
				request.setAttribute("end_index",maxResults);
				request.setAttribute("endresults",startIndex+panelDocRejCasesLst.size());
				request.setAttribute("pageId",pageId);
				
				if(panelDocRejCasesLst==null || panelDocRejCasesLst.size()==0)
					{
						request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				else
					panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
				panelDocPayForm.setPanelDocList(panelDocRejCases);
			 }
			else
			{
				
				if(panelDocRejCases==null || panelDocRejCases.size()==0)
					request.setAttribute("list","N");
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
			}
			for(int i=0;i<panelDocRejCases.size();i++){
				if(panelDocRejCases.get(i).getTOTAL_PNLDOC_AMT()!=null)
				totalAmt=totalAmt+panelDocRejCases.get(i).getTOTAL_PNLDOC_AMT().doubleValue();
			}
			panelDocPayForm.setRejId("PayRejCase");
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			return mapping.findForward("panelDocPmntCEO");
		}
		
		//End of get Bank Rejected Payments
		
		if(lStrFlagStatus!= null && "getAllCasesStatusInExcel".equals(lStrFlagStatus))
		{
			List<PanelDocPayVO> panelDocCasesList = new ArrayList<PanelDocPayVO>();
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String action=request.getParameter("actionType");
			String scheme=panelDocPayForm.getScheme();
			String prevGrp=request.getParameter("prevGrp");
			String currGrpId=null;
			String prevGrpId=null;
			PanelDocPayVO e=new PanelDocPayVO();
			List<PanelDocPayVO> headerList=new ArrayList<PanelDocPayVO>();
			
			String docId=request.getParameter("docId");	
			panelDocPayVO.setDOC_ID(docId);
			String wId=request.getParameter("wId");	
			panelDocPayVO.setwId(Long.parseLong(wId));
			panelDocPayForm.setwId(Long.parseLong(wId));
			String fromDate="";
			String toDate="";
			String selperiod="";
			String lStrFileName="";
			String dispType=request.getParameter("dispType");
			panelDocPayVO.setDispType(dispType);
			panelDocPayForm.setDispType(dispType);
			
			if(dispType==null)
				dispType="03";
			//for month wise selection
			if(dispType.equals("01")){
			panelDocPayVO.setSelperiod(request.getParameter("selPeriod"));
			panelDocPayForm.setSelPeriod(request.getParameter("selPeriod"));
			}
			//End of month wise selection
			//for date wise selection
			if(dispType.equals("02")){
			fromDate=request.getParameter("fromDate");
			toDate=request.getParameter("toDate");
			panelDocPayForm.setFromDate(fromDate);
			panelDocPayForm.setToDate(toDate);
			panelDocPayVO.setFromDate(fromDate);
			panelDocPayVO.setToDate(toDate);
			}
			//end of date wise selection
			if(action.equalsIgnoreCase(config.getString("FIN.ActionInitiate")))
			{
				currGrpId=config.getString("FIN.AccountsEO2Grp");
			}
			else
			{
				currGrpId="";
				prevGrpId=panelDocPayService.getgrpId(prevGrp);
			}
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			panelDocPayVO.setActionType(action);
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocCasesList=panelDocPayService.getPanelDocDtlCasesList(panelDocPayVO,currGrpId,prevGrpId);
			
			 String doc_name=request.getParameter("docDetails").replaceAll("\\s+", "_");
			 lStrFileName=config.getString("FIN.PanelDoctorTempFile")+"_Case_List_of"+doc_name+config.getString("DOT_VALUE")+config.getString("FIN.XlsExtn");
			 e.setAMOUNT("Amont");
			 e.setDOC_NAME("Doctor Name");
			 e.setCASE_DATE("Date");
			 e.setCASE_ID("Case ID");
			 e.setHOSP_NAME("Hospital Name");
			 e.setPARTICULARS("Case Status");
			 headerList.add(e);
			
		    request.setAttribute("List", panelDocCasesList);
	        request.setAttribute("headerList", headerList);
	        request.setAttribute("FileName", lStrFileName);
		    return mapping.findForward("openExcelFile"); 
		}
		
		//Start of Getting case list details of individual Doctors
		if(lStrFlagStatus!= null && "getAllCasesStatus".equals(lStrFlagStatus))
		{
			List<PanelDocPayVO> panelDocCasesList = new ArrayList<PanelDocPayVO>();
			List<PanelDocPayVO> caseCountStatus = new ArrayList<PanelDocPayVO>();
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String pmntType=request.getParameter("pmntType");
			String scheme=request.getParameter("scheme");
			int indexSpace=0;
			if(pmntType.contains(" "))
				pmntType.lastIndexOf(" ");
			String prevGrp=pmntType.substring(0,indexSpace);
			String action=pmntType.substring(indexSpace+1);
			String currGrpId=null;
			String prevGrpId=null;
			String docId=request.getParameter("docId");	
			String docName=request.getParameter("docName");	
			String wId=request.getParameter("wId");	
			panelDocPayVO.setDOC_ID(docId);
			panelDocPayForm.setDocid(docId);
			panelDocPayVO.setwId(Long.parseLong(wId));
			panelDocPayForm.setwId(Long.parseLong(wId));
			panelDocPayVO.setDOC_NAME(docName);
			String monthsel="";
			String fromDate="";
			String toDate="";
			String dispType=request.getParameter("dispType");
			panelDocPayVO.setDispType(dispType);
			panelDocPayForm.setDispType(dispType);
			if(dispType==null || dispType.equalsIgnoreCase("null"))
				dispType="03";
			//for month wise selection
			if(dispType.equals("01")){
			monthsel=request.getParameter("month");
			panelDocPayVO.setSelperiod(monthsel);
			panelDocPayForm.setSelPeriod(monthsel);
			}
			//End of month wise selection
			//for date wise selection
			if(dispType.equals("02")){
			fromDate=request.getParameter("fromDate");
			toDate=request.getParameter("toDate");
			panelDocPayForm.setFromDate(fromDate);
			panelDocPayForm.setToDate(toDate);
			panelDocPayVO.setFromDate(fromDate);
			panelDocPayVO.setToDate(toDate);
			}
			//end of date wise selection
			if(action.equalsIgnoreCase(config.getString("FIN.ActionInitiate")))
			{
				currGrpId=config.getString("FIN.AccountsEO2Grp");
			}
			else
			{
				currGrpId="";
				prevGrpId=panelDocPayService.getgrpId(prevGrp);
			}
			
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			panelDocPayVO.setActionType(action);
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocCasesList=panelDocPayService.getPanelDocDtlCasesList(panelDocPayVO,currGrpId,prevGrpId);
			caseCountStatus=panelDocPayService.getCaseCountStatus(panelDocPayVO,currGrpId,prevGrpId);
			if(panelDocCasesList!= null && panelDocCasesList.size()>0)
			 {
				
				panelDocPayForm.setPanelDocCasesList(panelDocCasesList);
				panelDocPayForm.setCaseCountStatus(caseCountStatus);
				if(dispType.equals("01")){
				panelDocPayForm.setFlag(" "+docName+" for "+monthsel);
				}
				if(dispType.equals("02")){
					panelDocPayForm.setFlag(" "+docName+" for the period "+fromDate+" to "+toDate);
				}
				if(dispType.equals("PAO"))
					panelDocPayForm.setFlag(" "+docName);
			 }
			else
			{
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
			}
			panelDocPayForm.setAction(action);
			panelDocPayForm.setID(prevGrp);
			panelDocPayForm.setScheme(scheme);
			return mapping.findForward("panelDocCases");
		}
		
		//End of getting details of cases of individual doctors
		
		//Start of save Payment for cases Initiated by Accounts officer2
		if(lStrFlagStatus!= null && "initiatePmnt".equals(lStrFlagStatus))
		{
		String monthsel="";
		String fromDate="";
		String toDate="";	
		String pmntstatus=panelDocPayForm.getPaymentStatus();
		String scheme=panelDocPayForm.getScheme();
		List<PanelDocPayVO> panelDocCasesDetails = new ArrayList<PanelDocPayVO>();
		String currWrkflowId = null;
		String currGrpId=null;
		String prevGrpId=null;
		PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
		String checkedDocValues=request.getParameter("checkedDocValues");
		String wIdValues=request.getParameter("wIdValues");
		String dispType=request.getParameter("dispType");
		panelDocPayVO.setDispType(dispType);
		panelDocPayVO.setDOC_ID(checkedDocValues);
		panelDocPayVO.setID(wIdValues);
		
		
		//for month wise selection
		if(dispType.equals("01")){
			monthsel=panelDocPayForm.getMonth();
			panelDocPayVO.setSelperiod(monthsel);
		}
		//End of month wise selection
		
		//for date wise selection
		if(dispType.equals("02")){
		fromDate=request.getParameter("fromDate");
		toDate=request.getParameter("toDate");
		panelDocPayVO.setFromDate(fromDate);
		panelDocPayVO.setToDate(toDate);
		}
		
		//end of date wise selection
		//panelDocPayVO.setRemarks(panelDocPayForm.getRemarks());
		if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
			scheme=lStrUserState;
		}
		panelDocPayVO.setUSERTYPE(scheme);
		panelDocPayVO.setActionType(config.getString("FIN.ActionApprove"));
		panelDocPayVO.setWorkFlow("I");
		panelDocPayVO.setCurrOwnr(usrGrpId);
		panelDocCasesDetails=panelDocPayService.getCasesByWrkFlow(panelDocPayVO,config.getString("FIN.NA"));
		currWrkflowId=workFlowCommonService.getNextWorkFlowId(panelDocCasesDetails.get(0).getCurrWrkflowId(), config.getString("FIN.ActionApprove"));
		currGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(currWrkflowId);
		prevGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(panelDocCasesDetails.get(0).getCurrWrkflowId());
		if(prevGrpId==null || "".equalsIgnoreCase(prevGrpId))
			prevGrpId="NA";
		
		panelDocPayVO.setPrevOwnr(prevGrpId);
		panelDocPayVO.setPrevWrkflowId(panelDocCasesDetails.get(0).getCurrWrkflowId());
		panelDocPayVO.setCurrOwnr(currGrpId);
		panelDocPayVO.setCurrWrkflowId(currWrkflowId);
		
		panelDocPayVO=panelDocPayService.updateApprvdRecord(panelDocCasesDetails,UserId,panelDocPayVO);
		if(panelDocPayVO.getWrkFlowFlg()!=null)
			if(panelDocPayVO.getWrkFlowFlg().equalsIgnoreCase("update"))
				{
					String action=config.getString("FIN.InProgress");
					panelDocPayService.updateCaseDtls(panelDocCasesDetails,action,UserId);
				}
		/*panelDocPayVO=panelDocPayService.insertAuditRecord(panelDocCasesDetails,UserId,panelDocPayVO);*/
		
		
			panelDocPayForm.setResult(panelDocPayVO.getResult());
		if(config.getString("FIN.ApproveMsg").equals(panelDocPayVO.getResult()))
		{
			panelDocPayForm.setStatus("Approve");
		}
		else if(config.getString("FIN.RejectMsg").equals(panelDocPayVO.getResult()))
		{
			panelDocPayForm.setStatus("Reject");
		}
		else if(config.getString("FIN.TransFailure").equals(panelDocPayVO.getResult()))
		{
			panelDocPayForm.setStatus("Some of Transaction Updation Failed due to technical difficuties");
		}
		
		request.setAttribute("PaymentStatusList", paymentStatus);
		panelDocPayForm.setPaymentStatusList(paymentStatus);
		panelDocPayForm.setPaymentStatus(pmntstatus);
		panelDocPayForm.setDispType(dispType);
		panelDocPayForm.setMonth(monthsel);
		//return mapping.findForward("panelDocPay");
		lStrFlagStatus="getCases";
		}
		
		//End if save payment for initiated cases
		
		//Approved by Panel Doctor
		if(lStrFlagStatus!= null && "approvePmnt".equals(lStrFlagStatus))
		{
			String pmntType=request.getParameter("pmntType");
			String scheme=panelDocPayForm.getScheme();
			String pmntstatus=panelDocPayForm.getPaymentStatus();
			int indexSpace=pmntType.lastIndexOf(" ");
			String prevGrp=pmntType.substring(0,indexSpace);
			String monthsel="";
			String fromDate="";
			String toDate="";	
			List<PanelDocPayVO> panelDocCasesDetails = new ArrayList<PanelDocPayVO>();
			String currWrkflowId = null;
			String currGrpId=null;
			String prevGrpId=null;
			String updCurrGrpId=null;
			String updPrevGrpId=null;
			
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String checkedDocValues=request.getParameter("checkedDocValues");
			String dispType=request.getParameter("dispType");
			String sentBack=request.getParameter("sendBack");
			if(sentBack!=null)
				{
					if(sentBack.equalsIgnoreCase("sendBack")||
						sentBack.equalsIgnoreCase("sendBackReject"))
							prevGrp=config.getString("FIN.PAO");
					if(panelDocPayForm.getCeoRemarksDoc()!=null)
						panelDocPayVO.setCeoRemarksDoc(panelDocPayForm.getCeoRemarksDoc());
				}	
			
			String wIdValues=request.getParameter("wIdValues");
			panelDocPayVO.setID(wIdValues);
			
			panelDocPayVO.setDispType(dispType);
			panelDocPayVO.setDOC_ID(checkedDocValues);
			currGrpId="";
			prevGrpId=panelDocPayService.getgrpId(prevGrp);
			
			//for month wise selection
			if(dispType.equals("01")){
				monthsel=panelDocPayForm.getMonth();
				panelDocPayVO.setSelperiod(monthsel);
			}
			//End of month wise selection
			
			//for date wise selection
			if(dispType.equals("02")){
			fromDate=request.getParameter("fromDate");
			toDate=request.getParameter("toDate");
			panelDocPayVO.setFromDate(fromDate);
			panelDocPayVO.setToDate(toDate);
			}
			
			//end of date wise selection
			//panelDocPayVO.setRemarks(panelDocPayForm.getRemarks());
			
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocPayVO.setWorkFlow("P");
			panelDocPayVO.setCurrOwnr(usrGrpId);
			panelDocCasesDetails=panelDocPayService.getCasesByWrkFlow(panelDocPayVO,prevGrpId);
			currWrkflowId=workFlowCommonService.getNextWorkFlowId(panelDocCasesDetails.get(0).getCurrWrkflowId(), "Approve");
			updCurrGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(currWrkflowId);
			updPrevGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(panelDocCasesDetails.get(0).getCurrWrkflowId());
			if(updPrevGrpId==null && sentBack!=null)
			{
				if(sentBack.equalsIgnoreCase("sendBack") || sentBack.equalsIgnoreCase("sendBackReject"))
					updPrevGrpId=usrGrpId;
			}
			panelDocPayVO.setPrevOwnr(updPrevGrpId);
			panelDocPayVO.setPrevWrkflowId(panelDocCasesDetails.get(0).getCurrWrkflowId());
			panelDocPayVO.setCurrOwnr(updCurrGrpId);
			panelDocPayVO.setCurrWrkflowId(currWrkflowId);
			panelDocPayVO=panelDocPayService.updateApprvdRecord(panelDocCasesDetails,UserId,panelDocPayVO);
			panelDocPayForm.setResult(panelDocPayVO.getResult());
			
			if(config.getString("FIN.ApproveMsg").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Approve");
			}
			else if(config.getString("FIN.RejectMsg").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Reject");
			}
			else if(config.getString("FIN.TransFailure").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Transaction Updation Failed");
			}
			
			
			request.setAttribute("PaymentStatusList", paymentStatus);
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			panelDocPayForm.setPaymentStatus(pmntstatus);
			panelDocPayForm.setDispType(dispType);
			panelDocPayForm.setMonth(monthsel);
						
			lStrFlagStatus="getCases";
		}
		
		//Rejected by Panel Doctor
		if(lStrFlagStatus!= null && "rejectPmnt".equals(lStrFlagStatus))
		{
			String scheme=panelDocPayForm.getScheme();
			String pmntType=request.getParameter("pmntType");
			String pmntstatus=panelDocPayForm.getPaymentStatus();
			
			int indexSpace=pmntType.lastIndexOf(" ");
			String prevGrp=pmntType.substring(0,indexSpace);
			String monthsel="";
			String fromDate="";
			String toDate="";	
			List<PanelDocPayVO> panelDocCasesDetails = new ArrayList<PanelDocPayVO>();
			String currWrkflowId = null;
			String currGrpId=null;
			String prevGrpId=null;
			String updCurrGrpId=null;
			String updPrevGrpId=null;
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String checkedDocValues=request.getParameter("checkedDocValues");
			String dispType=request.getParameter("dispType");
			panelDocPayVO.setDispType(dispType);
			panelDocPayVO.setDOC_ID(checkedDocValues);
			currGrpId="";
			prevGrpId=panelDocPayService.getgrpId(prevGrp);
			String wIdValues=request.getParameter("wIdValues");
			panelDocPayVO.setID(wIdValues);
			
			//for month wise selection
			if(dispType.equals("01")){
				monthsel=panelDocPayForm.getMonth();
				panelDocPayVO.setSelperiod(monthsel);
			}
			//End of month wise selection
			
			//for date wise selection
			if(dispType.equals("02")){
			fromDate=request.getParameter("fromDate");
			toDate=request.getParameter("toDate");
			panelDocPayVO.setFromDate(fromDate);
			panelDocPayVO.setToDate(toDate);
			}
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocPayVO.setCurrOwnr(usrGrpId);
			//end of date wise selection
			//panelDocPayVO.setRemarks(panelDocPayForm.getRemarks());
			//panelDocCasesDetails=panelDocPayService.getAllSelCasesDetails(panelDocPayVO,currGrpId,prevGrpId);
			panelDocCasesDetails=panelDocPayService.getCasesByWrkFlow(panelDocPayVO,prevGrpId);
			currWrkflowId=workFlowCommonService.getNextWorkFlowId(panelDocCasesDetails.get(0).getCurrWrkflowId(), "Reject");
			updCurrGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(currWrkflowId);
			updPrevGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(panelDocCasesDetails.get(0).getCurrWrkflowId());
			panelDocPayVO.setPrevOwnr(updPrevGrpId);
			panelDocPayVO.setPrevWrkflowId(panelDocCasesDetails.get(0).getCurrWrkflowId());
			panelDocPayVO.setCurrOwnr(updCurrGrpId);
			panelDocPayVO.setCurrWrkflowId(currWrkflowId);
			panelDocPayVO=panelDocPayService.updateRejectRecord(panelDocCasesDetails,UserId,panelDocPayVO);
			if(panelDocPayVO.getWrkFlowFlg()!=null)
				if(panelDocPayVO.getWrkFlowFlg().equalsIgnoreCase("update"))
					{
						String action="Reject";
						panelDocPayService.updateCaseDtls(panelDocCasesDetails,action,UserId);
					}
			panelDocPayForm.setResult(panelDocPayVO.getResult());
			
			if(config.getString("FIN.ApproveMsg").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Approve");
			}
			else if(config.getString("FIN.RejectMsg").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Reject");
			}
			else if(config.getString("FIN.TransFailure").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Transaction Updation Failed");
			}
			
			
			request.setAttribute("PaymentStatusList", paymentStatus);
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			panelDocPayForm.setPaymentStatus(pmntstatus);
			panelDocPayForm.setDispType(dispType);
			panelDocPayForm.setMonth(monthsel);
						
			//return mapping.findForward("panelDocPay");
			lStrFlagStatus="getCases";
		}
		/*
		 * Start of Get cases for Approval Workflow action 
		 */
		if(lStrFlagStatus!= null && "getCases".equals(lStrFlagStatus))
		{
			String searchFlag=request.getParameter("searchFlag");
	   		request.setAttribute("searchFlag",searchFlag);
			int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
			
			List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String pmntstatus=panelDocPayForm.getPaymentStatus();
			String scheme=panelDocPayForm.getScheme();
			String pmntType=request.getParameter("pmntType");
			//For getting Cases directly
			if(pmntType==null &&(paymentStatus!=null && paymentStatus.get(0)!=null && paymentStatus.get(0).getVALUE()!=null))
				pmntType=paymentStatus.get(0).getVALUE();
			if(pmntstatus==null &&(paymentStatus!=null && paymentStatus.get(0)!=null && paymentStatus.get(0).getVALUE()!=null))
				pmntstatus=Long.toString(paymentStatus.get(0).getIDVAL());
			
			int indexSpace=pmntType.lastIndexOf(" ");
			String prevGrp=pmntType.substring(0,indexSpace);
			String action=pmntType.substring(indexSpace+1);
			String currGrpId=null;
			String prevGrpId=null;
			String flag=null;
			float totalAmt=0;
			float amt=0;
			String type=request.getParameter("type"); 
			panelDocPayVO.setType(type);
			if(action.equalsIgnoreCase(config.getString("FIN.ActionInitiate")))
			{
				currGrpId=config.getString("FIN.AccountsEO2Grp");
			}
			else
			{
				currGrpId=usrGrpId;
				prevGrpId=panelDocPayService.getgrpId(prevGrp);
				if(prevGrpId.equals(config.getString("FIN.AccountsEO2Grp")))
					flag="Reject";
				if(usrGrpId.equalsIgnoreCase(config.getString("FIN.AccountsEOGrp")))
					if(action!=null)
						if(action.equalsIgnoreCase("CEO"))
							flag="Reject";
			}
			//For getting Cases directly
			if(currGrpId==null)
				currGrpId=usrGrpId;
			
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)||scheme==null){
				scheme=lStrUserState;
			}
			
			String monthsel="";
			String dispType=panelDocPayForm.getDispType();
			//For getting Cases directly
			if(dispType==null)
				dispType="03";
			panelDocPayVO.setDispType(dispType);
			
			//for month wise selection
			if(dispType.equals("01")){
				monthsel=panelDocPayForm.getMonth();
				panelDocPayVO.setSelperiod(monthsel);
			}
			//End of month wise selection
			
			//for date wise selection
			if(dispType.equals("02")){
			panelDocPayVO.setFromDate(panelDocPayForm.getFromDate());
			panelDocPayVO.setToDate(panelDocPayForm.getToDate());
			}
			//end of date wise selection
			panelDocPayVO.setUSERTYPE(scheme);
			
			panelDocCases=panelDocPayService.getPanelDocCases(panelDocPayVO,currGrpId,prevGrpId,startIndex,maxResults);
			
			if(panelDocCases!= null && panelDocCases.size()>0)
			 {
				
				if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							maxResults=Integer.parseInt(request.getParameter("end_index"));
						else
							maxResults=100;
							
					startIndex=maxResults*(pageId-1);
				}
				else
					{
						pageId=1;
						maxResults=100;
						startIndex=0;
					}
				
				noOfRecords=panelDocCases.size();
				if(noOfRecords%maxResults==0)
					noOfPages=noOfRecords/maxResults;
				if(noOfRecords%maxResults!=0)
					noOfPages=(noOfRecords/maxResults)+1;
				
				List<PanelDocPayVO> panelDocCasesLst=panelDocPayService.getPanelDocCases(panelDocPayVO,currGrpId,prevGrpId,startIndex,maxResults);
				if(type!=null)
					{
						if("CEOPending".equalsIgnoreCase(type))
							{
								//List<PanelDocPayVO> panelDocCasesRemarksLst=panelDocPayService.getCEORemarksList(panelDocPayVO,currGrpId,prevGrpId);
								//panelDocPayForm.setSentBackremarksLst(panelDocCasesRemarksLst);
								request.setAttribute("ceoRemarks", "show");
								request.setAttribute("sentBackRemDiv", "show");
							}	
					}
				request.setAttribute("noOfRecords",noOfRecords);
				request.setAttribute("lastPage",noOfPages);
				request.setAttribute("start_index",startIndex);
				request.setAttribute("end_index",maxResults);
				request.setAttribute("endresults",startIndex+panelDocCasesLst.size());
				request.setAttribute("pageId",pageId);
				panelDocPayForm.setIndividualPayment("");
				panelDocPayForm.setPanelDocList(panelDocCasesLst);
				if(panelDocCasesLst==null || panelDocCasesLst.size()==0)
					{
						request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				else
					panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
			 }
			else
			{
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
				if(panelDocCases==null || panelDocCases.size()==0)
					request.setAttribute("list","N");
			}
			for(int i=0;i<panelDocCases.size();i++){
				if(panelDocCases.get(i).getTotalPnldocAmt()!=null)
				amt=panelDocCases.get(i).getTotalPnldocAmt().floatValue();
				totalAmt=totalAmt+amt;
			}
			
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			panelDocPayForm.setPaymentStatus(pmntstatus);
			panelDocPayForm.setDispType(dispType);
			panelDocPayForm.setMonth(monthsel);
			panelDocPayForm.setAmount(0f);
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			if(action!=null)
				if(action.equalsIgnoreCase("CEO"))
					{
							flag="";
							panelDocPayForm.setSendBack("SendBack");	
						if(usrGrpId.equalsIgnoreCase(config.getString("FIN.AccountsEOGrp")))
							action="Rej";
					}
			if(searchFlag!=null && "Y".equalsIgnoreCase(searchFlag))
				action=null;
			panelDocPayForm.setAction(action);
			panelDocPayForm.setRejId(flag);
			panelDocPayForm.setUserType(lStrUserState);
			panelDocPayForm.setScheme(scheme);
			return mapping.findForward("panelDocPay");
			
		}
		if(lStrFlagStatus!= null && "getPayClaimForCases".equals(lStrFlagStatus))
		{
			String scheme=panelDocPayForm.getScheme();
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			else
			{
				if(scheme==null)
					scheme=PanelDocConstants.AP_State;
			}
			String currWrkflowId = null;
			String currGrpId=null;
			String prevGrpId=null;
			String updCurrGrpId=null;
			String updPrevGrpId=null;
			String actionType="";
			String lResult = PanelDocConstants.EMPTY;
			HashMap hParamMap = new HashMap();
			Map lParamMap = new HashMap();
			String docList = request.getParameter("checkedDocValues");
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			panelDocPayVO.setDOC_ID(docList);
			panelDocPayVO.setUserId(UserId);
			
			String action=request.getParameter("action");
			if(action.equals("PayRejCase"))
			{
				actionType=(config.getString("FIN.ActionReject"));
			}
			panelDocPayVO.setActionType(actionType);
			panelDocPayVO.setUSERTYPE(scheme);
			
			/*currWrkflowId=workFlowCommonService.getNextWorkFlowId(config.getString("FIN.WorkFlowIdCEO"), "Approve");
			updCurrGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(currWrkflowId);
			updPrevGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(config.getString("FIN.WorkFlowIdCEO"));
			panelDocPayVO.setPrevOwnr(updPrevGrpId);
			panelDocPayVO.setPrevWrkflowId(config.getString("FIN.WorkFlowIdCEO"));
			panelDocPayVO.setCurrOwnr(updCurrGrpId);
			panelDocPayVO.setCurrWrkflowId(currWrkflowId);
			hParamMap.put("panelDocPayVO", panelDocPayVO);
			hParamMap.put("CRTUSER", UserId);
			hParamMap.put("SentStatus", PanelDocConstants.SENT);
			hParamMap.put("SharePath", config.getString("FIN.MapPath"));
			hParamMap.put("TransReadyStatus", PanelDocConstants.TransReadyStatus);
			hParamMap.put("TDS_CASE_TYPE", PanelDocConstants.TDSTRUST);
			hParamMap.put("TDS_PAYMENT_TYPE", PanelDocConstants.PANELDOCTORS_TDS_PAYTYPE);
			hParamMap.put("CLAIM_STAT_ID",PanelDocConstants.SENT_TO_BANK);*/
			try 
            {
				boolean res=panelDocPayService.updateStatus(panelDocPayVO);
				System.out.print(res);
				lResult = PanelDocConstants.MSG_1;
            }
            catch(Exception e)
            {
            	//logger.error("coming into error*******"+ e.getMessage());
                lResult = "OOps!!! Something went wrong. Please try again after sometime.";
                singleRowShow= "OOps!!! Something went wrong. Please try again after sometime.";
            }
			request.setAttribute("saveMsg", lResult);
			//lParamMap = panelDocPayService.updatePanelDocPayStatus(hParamMap);
			
					/*if (config.getBoolean("EmailRequired")) {
						String mailId = null;
						if (lParamMap.get("failedCaseIdInList") != null) {
							mailId = config.getString("panelDocFailedCaseEmail");
							String[] emailToArray = { mailId };
							EmailVO emailVO = new EmailVO();
							emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
							emailVO.setTemplateName(config
									.getString("EhfFailedCasePayment"));
							emailVO.setFromEmail(config.getString("emailFrom"));
							emailVO.setToEmail(emailToArray);
							emailVO.setSubject(config.getString("failedCasesPayment"));
							Map<String, String> model = new HashMap<String, String>();
							model.put("caseNo",
									(String) lParamMap.get("failedCaseIdInList"));
							commonService.generateMail(emailVO, model);
						}
					}*/
			return mapping.findForward("panelDocPmntCEO");
			
			
		}
		
		if(lStrFlagStatus!= null && "UpdateTdsClaim".equals(lStrFlagStatus))
		{
			HashMap hParamMap = new HashMap();
			Map lParamMap = new HashMap();
			String lResult = PanelDocConstants.EMPTY;
			String caseList = request.getParameter("checkedCaseValues");
			String caseStatus = request.getParameter("caseStatus");
			String scheme=panelDocPayForm.getScheme();
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}
			else
			{
				if(scheme==null)
					scheme=PanelDocConstants.AP_State;
			}
			PanelDocPayVO panelDocPayVO = new PanelDocPayVO();
			panelDocPayVO.setCASE_ID(caseList);
			panelDocPayVO.setUserId(UserId);
			panelDocPayVO.setUSERTYPE(scheme);
			hParamMap.put("panelDocPayVO", panelDocPayVO);
			hParamMap.put("caseStatus", caseStatus);
			hParamMap.put("CRTUSER", UserId);
			hParamMap.put("SentStatus", PanelDocConstants.SENT);
			hParamMap.put("SharePath", config.getString("FIN.MapPath"));
			hParamMap.put("TdsRemarks", PanelDocConstants.CLAIM_SENT_RMK);
			hParamMap.put("TDS_CASE_TYPE", PanelDocConstants.TRUST_DEDUCTOR);
			hParamMap.put("SentStatus", PanelDocConstants.SENT);
			//setting temp values
			boolean res=panelDocPayService.updateTDSStatus(panelDocPayVO);
            //updating tds payment
			//lParamMap = panelDocPayService.updateTDSClaimStatus(hParamMap);

			//if (lParamMap.size() != 0) {
				//lResult = (String) lParamMap.get("Message");
			/*}
			if ("0".equals(lResult)) {
				lResult = PanelDocConstants.MSG_0;
			} else if ("2".equals(lResult)) {
				lResult = PanelDocConstants.MSG_2;
			} else if ("1".equals(lResult)) {
				lResult = PanelDocConstants.MSG_1;

			} else {
				if (lResult == null)
					lResult = PanelDocConstants.MSG_3;
				else
					lResult += PanelDocConstants.MSG_3;
			}*/
			lResult = PanelDocConstants.MSG_1;
			request.setAttribute("saveMsg", lResult);
			
		/*	if (config.getBoolean("EmailRequired")) {
				String mailId = null;
				if (lParamMap.get("failedCaseIdInList") != null) {
					mailId = config.getString("panelDocFailedCaseEmail");
					String[] emailToArray = { mailId };
					EmailVO emailVO = new EmailVO();
					emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
					emailVO.setTemplateName(config.getString("EhfFailedCasePayment"));
					emailVO.setFromEmail(config.getString("emailFrom"));
					emailVO.setToEmail(emailToArray);
					emailVO.setSubject(config.getString("failedCasesPayment"));
					Map<String, String> model = new HashMap<String, String>();
					model.put("caseNo",
							(String) lParamMap.get("failedCaseIdInList"));
					commonService.generateMail(emailVO, model);
				}
			}*/
			return mapping.findForward("panelDocTDSPmnt");
			
		}
		
		if(lStrFlagStatus!= null && "getPayClaimForCasesCeo".equals(lStrFlagStatus))
		{
			String scheme="CD201";
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
				scheme=lStrUserState;
			}

			//String actionType="";
			String fromAction=null;
			String lResult = PanelDocConstants.EMPTY;
			String docList = request.getParameter("checkedDocValues");
			String workList = request.getParameter("checkedWID");
			String singleRow=request.getParameter("singleRow");
			String statusSelected=panelDocPayForm.getPaymentStatus();
			if(statusSelected!=null && !"".equalsIgnoreCase(statusSelected))
				{
					panelDocPayForm.setPaymentStatus(statusSelected);
					panelDocPayForm.setStatusCeo(statusSelected);
					
					if(statusSelected.equalsIgnoreCase("1") ||
								statusSelected.equalsIgnoreCase("2"))
						lStrFlagStatus="getPanelDocPaymentRecrds";
					if(statusSelected.equalsIgnoreCase("3"))
						lStrFlagStatus="viewBankRejctdCasesCeo";
					if(statusSelected.equalsIgnoreCase("4"))
						{
							lStrFlagStatus="getPanelDocPaymentRecrds";
							request.setAttribute("sentBack", "Y");
							fromAction="Y";
						}
				}
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			panelDocPayVO.setDOC_ID(docList);
			panelDocPayVO.setUserId(UserId);
			panelDocPayVO.setWorkFlow(workList);
		
			
			String action=request.getParameter("action");
			if(action!=null)
			{
				if(action.equals("sendBack"))
					{
						panelDocPayVO.setActionType(action);
						panelDocPayVO.setSentBack(panelDocPayForm.getSendBack());
						panelDocPayVO.setCeoRemarks(panelDocPayForm.getCeoRemarks());
						
						if(workList!=null)
							{
								String[] widLst=workList.split("~");
								if(widLst.length>2)
									{
										request.setAttribute("SingleRow", "Selected Panel Doctor Payments have been Sent Back Successfully.");
										singleRowShow= "Selected Panel Doctor Payments have been Sent Back Successfully";
									}
								else 
									{
										request.setAttribute("SingleRow", "Selected Panel Doctor Payment has been Sent Back Successfully.");
										singleRowShow="Selected Panel Doctor Payment has been Sent Back Successfully.";
									}
							}
					}
				else
					{
						if(workList!=null)
						{
							String[] widLst=workList.split("~");
							if(widLst.length>2)
								{
									request.setAttribute("SingleRow", "Selected Panel Doctor Payments have been Approved.");
									singleRowShow="Selected Panel Doctor Payments have been Approved.";
								}
							else
								{
									request.setAttribute("SingleRow", "Selected Panel Doctor Payment has been Approved.");
									singleRowShow="Selected Panel Doctor Payment has been Approved.";
								}
						}
					}
				
			}
		else
			{
				/*if(singleRow!=null)
					{
						if(singleRow.equalsIgnoreCase("Y"))
							request.setAttribute("updatedCase", "show");
					}*/
				if(workList!=null)
					{
						String[] widLst=workList.split("~");
						if(widLst.length>2)
							{
								request.setAttribute("SingleRow", "Selected Panel Doctor Payments have been Approved.");
								singleRowShow="Selected Panel Doctor Payments have been Approved.";
							}
						else
							{
								request.setAttribute("SingleRow", "Selected Panel Doctor Payment has been Approved.");
								singleRowShow="Selected Panel Doctor Payment has been Approved.";
							}
					}
			}
			
			//panelDocPayVO.setActionType(actionType);
			panelDocPayVO.setUSERTYPE(scheme);
			panelDocPayVO.setSentToUser(panelDocPayForm.getAllUsers());
			panelDocPayVO.setSentToSec(panelDocPayForm.getAllDepts());
			
			try 
            {
				boolean res=panelDocPayService.updateStatus(panelDocPayVO);
				System.out.print(res);
				lResult = PanelDocConstants.MSG_1;
            }
            catch(Exception e)
            {
            	//logger.error("coming into error*******"+ e.getMessage());
                lResult = "OOps!!! Something went wrong. Please try again after sometime.";
                singleRowShow= "OOps!!! Something went wrong. Please try again after sometime.";
            }
			request.setAttribute("saveMsg", lResult);
			

			
			//return mapping.findForward("panelDocPmntCEO");
			
			
		}
		
		/*
		 * Start of get Panel Doctor Payments for CEO Approval
		 */
		if (lStrFlagStatus!= null && "getPanelDocPaymentRecrds".equalsIgnoreCase(lStrFlagStatus)) 
			{
				//String usrGrpId=null;
				if(singleRowShow!=null)
					{
						request.setAttribute("singleRowShow", singleRowShow);
					}
				
				//usrGrpId=config.getString("FIN.CEOGrp");
		    	panelDocPayForm.setPaymentStatusList(paymentStatus);
				String paymentStatusSelected=panelDocPayForm.getPaymentStatus();
				
				float totalAmt=0;
				float amt=0;
				String failedList=null;
				String fromAction=null;
				int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
				
				String scheme="CD202";
			
				List<PanelDocPayVO> panelDocCases = new ArrayList<PanelDocPayVO>();
				PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
				if(paymentStatusSelected!=null)
					{
						if(paymentStatusSelected.equalsIgnoreCase("2"))
							panelDocPayVO.setPrevWrkflowId(config.getString("FIN.JEOAPPROVAL"));
						else if(paymentStatusSelected.equalsIgnoreCase("3"))
							panelDocPayVO.setPrevWrkflowId(config.getString("FIN.WorkFlowIdSent"));
						else if(paymentStatusSelected.equalsIgnoreCase("4"))
							panelDocPayVO.setPrevWrkflowId(config.getString("FIN.CEOKeptPendUpd"));
						else
							panelDocPayVO.setPrevWrkflowId("none");
					}	
				
				panelDocPayVO.setUSERTYPE(scheme);
				if(paymentStatusSelected!=null && !"".equalsIgnoreCase(paymentStatusSelected))
				{panelDocPayVO.setPaymentStatusSelected(paymentStatusSelected);}
				
				String sentBackVal=request.getParameter("sentBack");
				if(fromAction!=null)
					{
						if("Y".equalsIgnoreCase(fromAction))
							sentBackVal=fromAction;
					}
					
				if(sentBackVal!=null)
					panelDocPayVO.setSentBackSearch(sentBackVal);
				panelDocCases=panelDocPayService.getPanelDocCasesForCEO(panelDocPayVO,startIndex,maxResults);
				
				//To get Total Panel Doctor Amount Pending at CEO
				int totalPendingAmt=0;
				for(PanelDocPayVO paneldc:panelDocCases)
					{
						if(paneldc.getTOTAL_PNLDOC_AMT()!=null )
							totalPendingAmt=totalPendingAmt+Integer.parseInt(paneldc.getTOTAL_PNLDOC_AMT().toString());
						
						request.setAttribute("totalPendingAmt", totalPendingAmt);
					}
				
				int size=panelDocCases.size();
			
				if(panelDocCases!= null && panelDocCases.size()>0)
					 {
						
						/*if(size>0)
							{
								failedList=panelDocCases.get(size-1).getFailedList();
								panelDocCases.remove(size-1);
							}*/
						
						if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
							{
								pageId=Integer.parseInt(request.getParameter("pageId"));
									
								if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
										maxResults=Integer.parseInt(request.getParameter("end_index"));
									else
										maxResults=100;
										
								startIndex=maxResults*(pageId-1);
							}
						else
							{
								pageId=1;
								maxResults=100;
								startIndex=0;
							}
						
						noOfRecords=panelDocCases.size();
						if(noOfRecords%maxResults==0)
							noOfPages=noOfRecords/maxResults;
						if(noOfRecords%maxResults!=0)
							noOfPages=(noOfRecords/maxResults)+1;
						
						
						List<PanelDocPayVO> panelDocCasesLst=panelDocPayService.getPanelDocCasesForCEO(panelDocPayVO,startIndex,maxResults);
						
						/*if(panelDocCasesLst.size()>0)
							{
								failedList=panelDocCasesLst.get(size-1).getFailedList();
								panelDocCasesLst.remove(size-1);
							}*/
						
						request.setAttribute("noOfRecords",noOfRecords);
						request.setAttribute("lastPage",noOfPages);
						request.setAttribute("start_index",startIndex);
						request.setAttribute("end_index",maxResults);
						request.setAttribute("endresults",startIndex+panelDocCasesLst.size());
						request.setAttribute("pageId",pageId);
						panelDocPayForm.setIndividualPayment("");
						panelDocPayForm.setPanelDocList(panelDocCasesLst);
						if(panelDocCasesLst==null || panelDocCasesLst.size()==0)
							{
								request.setAttribute("list","N");
								panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
							}
						else
							panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
					 }
				else
					{
						if(panelDocCases==null || panelDocCases.size()==0)
							request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				panelDocPayForm.setRejId("PayNow");
				panelDocPayForm.setPaySent("payments");
				
				String sentBackTo=config.getString("FIN.SentBackTO");
				String sentBackGrps=config.getString("FIN.SentBackToGrps");
				String[] sentBackArr=sentBackTo.split("~");
				String[] sentBackGrpsArr=sentBackGrps.split("~");
				
				List<LabelBean> lbLst=new ArrayList<LabelBean>();
				for(int i=0;i<sentBackGrpsArr.length;i++)
					{
						LabelBean lb =new LabelBean();
						lb.setID(sentBackGrpsArr[i]);
						String name=panelDocPayService.getLoginName(sentBackGrpsArr[i]);
						lb.setVALUE(name);
						
						lbLst.add(lb);
					}
				request.setAttribute("sentBackList", lbLst);
				request.setAttribute("sentBackListValues", "show");
				
						
				
				for(int i=0;i<panelDocCases.size();i++)
					{
						if(panelDocCases.get(i).getTOTAL_PNLDOC_AMT()!=null)
						amt=panelDocCases.get(i).getTOTAL_PNLDOC_AMT().floatValue();
						totalAmt=totalAmt+amt;
					}
				panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
				
				//claimsFlowForm.setPaymentStatusList(paymentStatus);
				panelDocPayForm.setUserType(lStrUserState);
				panelDocPayForm.setScheme(scheme);
				
	            request.setAttribute("Action","Y");
				return mapping.findForward("panelDocPmntCEO");
				
			}
				
		if(lStrFlagStatus!= null && "getAllUsers".equalsIgnoreCase(lStrFlagStatus))
		{
			String deptId=request.getParameter("deptId");
			List<LabelBean> usrLst=commonService.getAllUsersFromDepts(deptId);
			List ajax=new ArrayList();
			for(LabelBean lb:usrLst)
				{
					if(lb.getID()!=null && ajax.size()>0)
						{
							ajax.add("@"+lb.getID()+"~"+lb.getEMPNAME().toUpperCase());
						}
					else
						{
							ajax.add(lb.getID()+"~"+lb.getEMPNAME().toUpperCase());
						}
				}
			request.setAttribute("AjaxMessage", ajax);
			return mapping.findForward("ajaxResult");
		}
		
	if(lStrFlagStatus!= null && "getAllUsersforHie".equalsIgnoreCase(lStrFlagStatus))
		{
			
			String sentBackGrps=config.getString("FIN.SentBackToGrps");
			String[] sentBackGrpsArr=sentBackGrps.split("~");
			List ajax=new ArrayList();
		
			for(int i=0;i<sentBackGrpsArr.length;i++)
				{
					String name=panelDocPayService.getLoginName(sentBackGrpsArr[i]);
					if(sentBackGrpsArr[i]!=null && name!=null)
						{
							if(ajax.size()>0)
								ajax.add("@"+sentBackGrpsArr[i]+"~"+name.toUpperCase());
							else
								ajax.add(sentBackGrpsArr[i]+"~"+name.toUpperCase());
						}	
				}

			request.setAttribute("AjaxMessage", ajax);
			return mapping.findForward("ajaxResult");
		}
		if(lStrFlagStatus!= null && "getDepts".equalsIgnoreCase(lStrFlagStatus))
		{
			List<EhfmDepts> ehfmDeptsLst=commonService.getAllDepartments();
			List ajax=new ArrayList();
			for(EhfmDepts ehfmDepts:ehfmDeptsLst)
				{
					if(ehfmDepts.getDeptId()!=null && ajax.size()>0)
						{
							ajax.add("@"+ehfmDepts.getDeptId()+"~"+ehfmDepts.getDeptName().toUpperCase());
						}
					else
						{
							ajax.add("hie"+"~"+"HEIRARCHY");
							ajax.add("@"+ehfmDepts.getDeptId()+"~"+ehfmDepts.getDeptName().toUpperCase());
						}
				}
			request.setAttribute("AjaxMessage", ajax);
			return mapping.findForward("ajaxResult");
		}
		//sent back by respective user
		if(lStrFlagStatus!= null && "sendBackPmnt".equals(lStrFlagStatus))
		{
			String scheme=request.getParameter("scheme");
			List<PanelDocPayVO> panelDocCasesDetails = new ArrayList<PanelDocPayVO>();
			String currWrkflowId = null;
			String currGrpId=null;
			String prevGrpId=null;
			String updCurrGrpId=null;
			String updPrevGrpId=null;
			
			PanelDocPayVO panelDocPayVO=new PanelDocPayVO();
			String dispType=request.getParameter("dispType");
			String docId=request.getParameter("docId");
			String wId=request.getParameter("wId");
		
			panelDocPayVO.setDispType(dispType);
			panelDocPayVO.setDOC_ID(docId);
			panelDocPayVO.setwId(Long.parseLong(wId));
			
			currGrpId=config.getString("Fin.AllDeptsGrp");
			prevGrpId=config.getString("FIN.CEOGrp");
			if(panelDocPayForm.getCeoRemarksDoc()!=null)
				panelDocPayVO.setCeoRemarksDoc(panelDocPayForm.getCeoRemarksDoc());

			panelDocPayVO.setUSERTYPE(scheme);
			panelDocPayVO.setWorkFlow("P");
			panelDocPayVO.setCurrOwnr(currGrpId);
			
			panelDocCasesDetails=panelDocPayService.generateCasesByWrkFlow(panelDocPayVO,prevGrpId);
			currWrkflowId=workFlowCommonService.getNextWorkFlowId(panelDocCasesDetails.get(0).getCurrWrkflowId(), "Approve");
			updCurrGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(currWrkflowId);
			updPrevGrpId=workFlowCommonService.getCurrenttOwnerForNewWorkFlow(panelDocCasesDetails.get(0).getCurrWrkflowId());
			
			panelDocPayVO.setPrevOwnr(updPrevGrpId);
			panelDocPayVO.setPrevWrkflowId(panelDocCasesDetails.get(0).getCurrWrkflowId());
			panelDocPayVO.setCurrOwnr(updCurrGrpId);
			panelDocPayVO.setCurrWrkflowId(currWrkflowId);
			panelDocPayVO=panelDocPayService.updateApprvdRecord(panelDocCasesDetails,UserId,panelDocPayVO);
			panelDocPayForm.setResult(panelDocPayVO.getResult());
			
			if(config.getString("FIN.ApproveMsg").equals(panelDocPayVO.getResult()))
			{
				panelDocPayForm.setStatus("Approve");
			}
			
			panelDocPayForm.setDispType(dispType);
			request.setAttribute("caseApproved","one");
			
			return mapping.findForward("panelDocCases");
		}
	
		//View Bank Rejected Payments
		if(lStrFlagStatus!= null && "viewBankRejctdCasesCeo".equals(lStrFlagStatus))
		{
			if(singleRowShow!=null)
				{
					request.setAttribute("singleRowShow", singleRowShow);
				}
			int startIndex=0,maxResults=0,pageId=0,noOfRecords=0,noOfPages=0;
			double totalAmt=0;
			List<PanelDocPayVO> panelDocRejCases = new ArrayList<PanelDocPayVO>();
			String scheme="CD202";
			
			//usrGrpId=config.getString("FIN.CEOGrp");
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			
			if(!lStrUserState.equals(PanelDocConstants.AP_TG_State)){
							scheme=lStrUserState;
						}
						
			panelDocRejCases=panelDocPayService.getAllBankRejctdCases(scheme,startIndex,maxResults);
			if(panelDocRejCases!= null && panelDocRejCases.size()>0)
			 {
				
				if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
				{
					pageId=Integer.parseInt(request.getParameter("pageId"));
						
					if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
							maxResults=Integer.parseInt(request.getParameter("end_index"));
						else
							maxResults=100;
							
					startIndex=maxResults*(pageId-1);
				}
				else
					{
						pageId=1;
						maxResults=100;
						startIndex=0;
					}
				
				noOfRecords=panelDocRejCases.size();
				if(noOfRecords%maxResults==0)
					noOfPages=noOfRecords/maxResults;
				if(noOfRecords%maxResults!=0)
					noOfPages=(noOfRecords/maxResults)+1;
				
				List<PanelDocPayVO> panelDocRejCasesLst=panelDocPayService.getAllBankRejctdCases(scheme,startIndex,maxResults);
				
				String sentBackTo=config.getString("FIN.SentBackTO");
				String sentBackGrps=config.getString("FIN.SentBackToGrps");
				String[] sentBackArr=sentBackTo.split("~");
				String[] sentBackGrpsArr=sentBackGrps.split("~");
				
				List<LabelBean> lbLst=new ArrayList<LabelBean>();
				for(int i=0;i<sentBackGrpsArr.length;i++)
					{
						LabelBean lb =new LabelBean();
						lb.setID(sentBackGrpsArr[i]);
						String name=panelDocPayService.getLoginName(sentBackGrpsArr[i]);
						lb.setVALUE(name);
						
						lbLst.add(lb);
					}
				request.setAttribute("sentBackList", lbLst);
				request.setAttribute("sentBackListValues", "show");
				
				
				panelDocPayForm.setPaySent("payments");
				request.setAttribute("noOfRecords",noOfRecords);
				request.setAttribute("lastPage",noOfPages);
				request.setAttribute("start_index",startIndex);
				request.setAttribute("end_index",maxResults);
				request.setAttribute("endresults",startIndex+panelDocRejCasesLst.size());
				request.setAttribute("pageId",pageId);
				
				if(panelDocRejCasesLst==null || panelDocRejCasesLst.size()==0)
					{
						request.setAttribute("list","N");
						panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
					}
				else
					panelDocPayForm.setFlag(config.getString("FIN.ListFound"));
				panelDocPayForm.setPanelDocList(panelDocRejCases);
			 }
			else
			{
				
				if(panelDocRejCases==null || panelDocRejCases.size()==0)
					request.setAttribute("list","N");
				panelDocPayForm.setFlag(config.getString("FIN.ListNotFound"));
			}
			for(int i=0;i<panelDocRejCases.size();i++){
				if(panelDocRejCases.get(i).getTOTAL_PNLDOC_AMT()!=null)
				totalAmt=totalAmt+panelDocRejCases.get(i).getTOTAL_PNLDOC_AMT().doubleValue();
			}
			panelDocPayForm.setRejId("PayNow");
			panelDocPayForm.setTotalAmt(String.valueOf(totalAmt));
			panelDocPayForm.setPaymentStatusList(paymentStatus);
			
			request.setAttribute("Action","Y");
			return mapping.findForward("panelDocPmntCEO");
		}
		
		return null;
    }
	

	//File creation 
		private File createFile(String lStrDirPath,String lStrFileName) throws IOException 
		{
			//Making Directory		  
			File file = new File(lStrDirPath);
			if (!file.exists()) 
			{
				file.mkdir();
			}
			File lfile = new File(lStrFileName);
			if(!lfile.exists())
			{
				lfile.createNewFile();
			}
			else
			{
				lfile.delete();
				lfile.createNewFile();
			}
			return lfile;
			
		}

}
