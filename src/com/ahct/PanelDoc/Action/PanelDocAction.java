package com.ahct.PanelDoc.Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.PanelDoc.Form.PanelDocForm;
import com.ahct.PanelDoc.Service.PanelDocService;
import com.ahct.PanelDoc.VO.panelDocVo;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class PanelDocAction extends Action {

	private final static Logger glogger=Logger.getLogger ( PanelDocAction.class ) ;
	
	
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("pragma","no-cache");
	response.setDateHeader("Expires", 0);    
	HttpSession session = request.getSession ( false ) ;
	
	
	ConfigurationService configurationService = 
		(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");

	Configuration config = configurationService.getConfiguration();
    
    PanelDocService  panelDocService=(PanelDocService)ServiceFinder.getContext(request).getBean("panelDocService");
    CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");

    PanelDocForm  panelDocForm=(PanelDocForm)form;
    
	String lStrEmpId = null ;
    String lStrLocId = null ;
    String lStrLangId = null ;
    String lStrUserName = null;
    String lStrFlagStatus = null;
    String lStrUserRole = null;
    String userState=null;
    String msg=null;
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
	    if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
	    	userState = ( String ) session.getAttribute ( "userState" ) ;
	    request.setAttribute("userState", userState);
	    
	    String lstrActionVal=request.getParameter("actionFlag");
	    String lstrResultPage=null;
    
    if(lstrActionVal!=null)
    {
    	
    	if(lstrActionVal.equalsIgnoreCase("savePanelDocDtls"))
    	{
    		panelDocVo panelDocVo=new panelDocVo();
    		String result=null;
    		panelDocVo.setUserId(panelDocForm.getUserId());
    		panelDocVo.setAccountNum(panelDocForm.getAccountNum());
    		panelDocVo.setEmpName(panelDocForm.getEmpName());
    		panelDocVo.setBankId(panelDocForm.getBankId());
    		panelDocVo.setPan(panelDocForm.getPan());
    		panelDocVo.setPanName(panelDocForm.getPanName());
    		panelDocVo.setCrtUsr(lStrUserName);
    		panelDocVo.setLoginName(panelDocForm.getLoginName());
    		
    		result=panelDocService.saveAccountDetails(panelDocVo);
    		panelDocForm.setResult(result);
    		request.setAttribute("resultMsg",result);
    		request.setAttribute("bankLst",panelDocService.banksList());
    		/*lstrActionVal="panelDocSearch";
    		
    		lstrResultPage="panelDocDtls";*/
    		lstrResultPage="resultPage";
    	}
    
    	
    	if(lstrActionVal.equalsIgnoreCase("panelDocSearch"))
    	{
    		String searchFlag=null;
    		searchFlag=request.getParameter("searchFlag");
    		panelDocVo panelDocVo=new panelDocVo();
    		String userstate=request.getParameter("userState");
    		panelDocVo.setState(userstate);
    		String loginname=request.getParameter("loginname");
    		request.setAttribute("loginname", loginname);
    		String empname=request.getParameter("empname");
    		request.setAttribute("empname", empname);
    		
    		panelDocVo.setLOGINNAME(loginname);
    		panelDocVo.setEMPLOYEENAME(empname);
    		/*panelDocVo.setLoginName(panelDocForm.getLoginName());
    		panelDocVo.setEmpName(panelDocForm.getEmpName());
    		
    	    panelDocForm.setLoginName(panelDocForm.getLoginName());
    		panelDocForm.setEmpName(panelDocForm.getEmpName());*/
    		
    		
    		/*pagination code*/
			int showPage=1;
			int rowsPerPage=10;
			if(config.getString("PnlDocRowsPerPage")!=null)
				rowsPerPage=Integer.parseInt(config.getString("PnlDocRowsPerPage"));
			int startIndex=0;
			int maxResults=0;
				
			if(panelDocForm.getShowPage()!=null && !("".equalsIgnoreCase(panelDocForm.getShowPage())))
					showPage=Integer.parseInt(panelDocForm.getShowPage());
			if(panelDocForm.getRowsPerPage()!=null && !("".equalsIgnoreCase(panelDocForm.getRowsPerPage())))
			        rowsPerPage=Integer.parseInt(panelDocForm.getRowsPerPage());
				
			startIndex=(showPage-1)*rowsPerPage;
			maxResults=rowsPerPage;
			
			panelDocVo.setStartIndex(startIndex);
			panelDocVo.setMaxResults(maxResults);
			
			/*end of pagination code*/
			
    		
    		List<panelDocVo> panelDocLst=new ArrayList<panelDocVo>();
    		panelDocVo PanelDoc=new panelDocVo();
    		PanelDoc=panelDocService.getPanelDocLst(panelDocVo);
    		panelDocLst=PanelDoc.getPanelDocLst();
    		if(panelDocLst.size()>0)
    		{
    			if(panelDocLst != null)
    			panelDocForm.setPanelDocLst(panelDocLst);
    		}
    		else
    		{
    			request.setAttribute("totalrecords", "N");
    		}
    		
    		
    		
    		
    		int count=(int) PanelDoc.getCount();
			int totalPages=0;
			
			int rem=count%rowsPerPage;
			
			if(rem==0)
			{
				totalPages=(count/rowsPerPage);	
			}
			else
			{
				totalPages=(count/rowsPerPage)+1;	
			}
			int endIndex=0;
			if(totalPages==showPage)
			{
				endIndex=count;
			}
			else
			{
				endIndex= startIndex+rowsPerPage;
			}
    		
    		
    		request.setAttribute("panelDocLst",panelDocLst);
    		panelDocForm.setStartIndex((startIndex+1)+"");
			panelDocForm.setEndIndex((endIndex)+"");
			panelDocForm.setTotalRows(count+"");
			request.setAttribute("liTotalPages",totalPages);
			request.setAttribute("liPageNo",showPage);
			request.setAttribute("searchflag","Y");
			/*if(panelDocForm.getResult()!=null)
			{
				return mapping.findForward("panelDocList");
			}*/
    		
    		lstrResultPage="panelDocList";
    	}
    	
    	if(lstrActionVal.equalsIgnoreCase("panelDocDetails"))
    	{
    		List<panelDocVo> bankList=new ArrayList<panelDocVo>();
    		panelDocVo userDtls=new panelDocVo();
    		bankList=panelDocService.banksList();
    		request.setAttribute("bankLst",bankList);
    		String userId=null;
    		String loginName=request.getParameter("loginName");
    		String newAccount=request.getParameter("newAccount");
    		if(loginName!=null && !("").equalsIgnoreCase(loginName))
    		{
    		userId=panelDocService.getUserId(loginName.toUpperCase());
    		panelDocForm.setLoginNameTemp(loginName.toUpperCase());
    		}
    		else
    	    userId=request.getParameter("userId");	
    		List<panelDocVo> branchList=new ArrayList<panelDocVo>();
    		if(userId!=null && !("").equalsIgnoreCase(userId))
    		{
    		userDtls=panelDocService.getPanelDocDetails(userId);
    		
    		panelDocForm.setUserId(userDtls.getUserId());
    		panelDocForm.setLoginName(userDtls.getLoginName());
    		panelDocForm.setPan(userDtls.getPan());
    		panelDocForm.setPanName(userDtls.getPanName());
    		String empName=userDtls.getEmpName();
    		if(empName.contains("null"))
    		{
    			empName.replaceAll("null"," ");
    		}
    		panelDocForm.setEmpName(empName);
    		if(userDtls.getBankId()!=null)
    		{
    		
    		branchList=panelDocService.bankBranchList(userDtls.getBankName());
    		
    		panelDocForm.setBankId(userDtls.getBankId());
    		}
    		panelDocForm.setBankName(userDtls.getBankName());
    		panelDocForm.setBranchName(userDtls.getBranchName());
    		panelDocForm.setIfscCode(userDtls.getIfscCode());
			panelDocForm.setAccountNum(userDtls.getAccountNum());
			request.setAttribute("newAccount",newAccount);
			//if(newAccount!=null && !("Y").equalsIgnoreCase(newAccount))
			request.setAttribute("updateExisting","Y");
    		
    		}
    		request.setAttribute("branchList",branchList);
    		lstrResultPage="panelDocDtls";
    		
    	}
    	
    	
    }
    
    if(("getBranchList").equalsIgnoreCase(lstrActionVal))
	{
		
		
		String bankName=request.getParameter("bankName");
		List<panelDocVo> branchList=new ArrayList<panelDocVo>();
		List<String> branch=new ArrayList<String>();
		branchList=panelDocService.bankBranchList(bankName);
		if(branchList!=null && branchList.size()>0)
		{
			for(panelDocVo list:branchList)
			{
				branch.add(list.getBankId()+"~"+list.getBranchName()+"@");
			}
		}
		if(branch!=null)
		{
		request.setAttribute("AjaxMessage",branch);
		return mapping.findForward("ajaxResult");
		}
		
	}
    
    if(("getIfsc").equalsIgnoreCase(lstrActionVal))
	{
		String bankId=request.getParameter("bankId");
		String ifscCode=null;
		ifscCode=panelDocService.getIfscCode(bankId);
		if(ifscCode!=null)
		{
			request.setAttribute("AjaxMessage",ifscCode);
			return mapping.findForward("ajaxResult");
		}
    	
	}
    
    if(("validateUser").equalsIgnoreCase(lstrActionVal))
	{
		String loginName=request.getParameter("loginName");
		String loginNameTemp=null;
		if(loginName!=null)
		loginNameTemp=panelDocService.validatePnlDoc(loginName.toUpperCase());
		if(loginNameTemp==null || ("").equalsIgnoreCase(loginNameTemp))
		{
			loginNameTemp="INVALID";
		}
		else
		{
			loginNameTemp=loginName;
		}
			request.setAttribute("AjaxMessage",loginNameTemp);
			return mapping.findForward("ajaxResult");
		
    	
	}
	
    
    return mapping.findForward(lstrResultPage);
    
	}
	
}
