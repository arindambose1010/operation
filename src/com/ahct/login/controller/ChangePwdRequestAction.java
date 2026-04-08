package com.ahct.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.common.util.HtmlEncode;
import com.ahct.common.util.LdapAuthentication;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.login.form.LoginRequestForm;
import com.ahct.login.service.ChangePwdRequestService;
import com.ahct.login.service.LoginService;
import com.tcs.framework.configuration.ConfigurationService;


//TODO: Auto-generated Javadoc
/**
* 
* @author 350262
* @version jdk 1.5
* @description This 
*/
public class ChangePwdRequestAction extends Action
{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
		LoginRequestForm loginRequestForm = ( LoginRequestForm ) form ;
		Logger oslogger = Logger.getLogger ( ChangePwdRequestAction.class );
		String lStrResultPage = "" ;
		String loLoginFlag = null ;
		String lStractionFlag = request.getParameter("actionFlag"); 
		//Session timeout
		lStrResultPage = HtmlEncode.verifySession ( request, response ) ;
		if ( lStrResultPage.length () > 0 ){

			return mapping.findForward ( "sessionExpire" ) ;
		}
		//End session timeout
		if((lStractionFlag!=null &&  !"sendPassword".equalsIgnoreCase(lStractionFlag))){
			if(lStractionFlag!=null &&  "sendPassword".equalsIgnoreCase(lStractionFlag)){
				String  sessionResultPage = HtmlEncode.verifySession(request, response);
				if (sessionResultPage.length() > 0)
					return mapping.findForward("sessionExpire");
			}
		}
		ChangePwdRequestService changePwdRequestService = ( ChangePwdRequestService ) ServiceFinder.getContext ( request ).getBean ( "changePwdRequestService" ) ;
		LoginService loginService = ( LoginService ) ServiceFinder.getContext ( request ).getBean ( "loginService" ) ;


		HttpSession session = request.getSession ( false ) ;

		/**For reset password and send via SMS **/
		if(lStractionFlag!=null &&  "sendPassword".equalsIgnoreCase(lStractionFlag)){
			String lStrUserId = request.getParameter ( "userId") ;
			String lStrLoginType = request.getParameter ("loginType") ;
			String lStrMobile = request.getParameter ( "mobNo") ;
			String lStrEmailId = request.getParameter ( "emailId") ;
			String lStrResponse="";
			LabelBean lChangPswdVO=new LabelBean();
			lChangPswdVO.setEMPID(lStrUserId);
			lChangPswdVO.setHOUSENO(lStrMobile);
			lChangPswdVO.setEMAILID(lStrEmailId);
			lChangPswdVO.setWFTYPE(lStrLoginType);
			lStrResponse=changePwdRequestService.validateAndSendPswdSms(lChangPswdVO); 
			request.setAttribute("json",lStrResponse);
			return mapping.findForward ( "json" ) ;
		}
		else{
			try
			{           
				String lStrLocId =(String)session.getAttribute("LocID");
				String lStrLangId =(String)session.getAttribute("LangID");
				String lStrEmpId = (String)session.getAttribute("EmpID");
				String userName = (String)session.getAttribute("userName");
				// This is true when after reset pwd, user login very first time.
				if ( ( request.getParameter ( "DisplayAlertMsg") ) != null &&  
						( request.getParameter ( "DisplayAlertMsg") ).equalsIgnoreCase("Yes") )
				{
					loginRequestForm.setDisplayAlert("Yes");
					lStrResultPage = "ChangePasswordPage";
					return mapping.findForward(lStrResultPage);
				}
				//This is true when after user ver first time can successfully login.
				if ( ( request.getParameter ( "displayAlert") ) != null &&
						( request.getParameter ( "displayAlert") ).equalsIgnoreCase("Yes")  )           
					request.setAttribute("frst_Login","YES");
				String lStrNewPassword = request.getParameter ( "ReEnterNewPassword") ;
				String lStrOldPassword = request.getParameter ( "oldPassword") ;   
				String lStrMobNum =  request.getParameter ( "mobileNo") ;   
				String lStrEmailId=request.getParameter ( "emailId") ;  
				LabelBean lChangPswdVO=new LabelBean();
				lChangPswdVO.setEMPID(lStrEmpId);
				lChangPswdVO.setID(lStrOldPassword);
				lChangPswdVO.setVALUE(lStrNewPassword);
				lChangPswdVO.setHOUSENO(lStrMobNum);
				lChangPswdVO.setEMAILID(lStrEmailId);
				lChangPswdVO.setWFTYPE((String) session.getAttribute("loginType"));
				lChangPswdVO.setUNITNAME(userName);
				loLoginFlag=changePwdRequestService.changePassword(lChangPswdVO,request); 
				loginRequestForm.setChangeStatus(loLoginFlag);
			}
			catch(Exception e)   
			{
				e.printStackTrace();
			}
			return mapping.findForward ( "ChangePasswordPage" ) ;
		}
	}

}
