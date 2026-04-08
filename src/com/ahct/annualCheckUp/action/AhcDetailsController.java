package com.ahct.annualCheckUp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ahct.preauth.controller.UploadItem;
@Controller
public class AhcDetailsController {
	@RequestMapping("/annualCheckUp.htm")
	public ModelAndView annualCheckUp(HttpServletRequest request,Model model,UploadItem uploadItem,BindingResult result) 
	{
		String lStrActionFlag=request.getParameter("actionFlag");			
		HttpSession session = request.getSession(false);
		String lStrUserRole=null;	   
		String lStrResultPage=null;	
        
        if(session == null || session.getAttribute("UserID")==null)
        {
             request.setAttribute("Message","Your session has expired. Login again");
             lStrResultPage = "login/regSessionInvalid";//003
        }
        String lStrEmpId = null ;
        if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
		    lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
		String ahcId=null;
		if(request.getParameter ( "ahcId" ) !=null)
		{
			ahcId=request.getParameter ( "ahcId" ) ;
		}
		String casesSearchFlag =request.getParameter("casesSearchFlag");
		request.setAttribute("casesSearchFlag", casesSearchFlag);
		request.setAttribute("ahcId", ahcId);	
		if("viewAhcPrintPage".equalsIgnoreCase(lStrActionFlag)){
			lStrResultPage="AnnualCheckUp/Printpage";
		}
		return new ModelAndView(lStrResultPage);
	}
}
