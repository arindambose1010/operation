package com.ahct.login.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;

import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




import com.ahct.claims.util.ClaimsConstants;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.LdapAuthentication;
import com.ahct.common.vo.LabelBean;
import com.ahct.login.form.LoginForm;
import com.ahct.login.service.LoginService;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.login.vo.LoginDtlsVO;
import com.ahct.login.vo.MenuVo;
import com.ahct.flagging.service.FlaggingService;
import com.ahct.common.util.DigitalSignature;
import com.sun.identity.saml2.common.SAML2Utils;
import com.sun.identity.saml2.jaxb.metadata.AssertionConsumerServiceElement;
import com.sun.identity.saml2.jaxb.metadata.IDPSSODescriptorElement;
import com.sun.identity.saml2.jaxb.metadata.SPSSODescriptorElement;
import com.sun.identity.saml2.jaxb.metadata.SingleSignOnServiceElement;
import com.sun.identity.saml2.meta.SAML2MetaManager;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.fsws.worker.FSWorker;
import com.tcs.service.TimeLoggingService;
import com.ahct.login.service.DigitalCertificateServiceImpl;



public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		LoginForm loginForm = (LoginForm)form;
		 Logger logger = Logger.getLogger(LoginAction.class);
        String lStrFlagStatus = request.getParameter("actionFlag");
        ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
        FlaggingService flaggingService = (FlaggingService)ServiceFinder.getContext(request).getBean("flaggingService");
        CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
		Configuration config = configurationService.getConfiguration();
        LdapAuthentication ldapAuth = new LdapAuthentication();
        String lStrResultPage = "";
        String otpCheck="";String digiCheck="";
        otpCheck=request.getParameter("otpCheck");
        digiCheck=request.getParameter("digiCheck");
        String userName="";String password="";
        String checkLogout=null;
        String ceoAPImage=config.getString("ceoAPImage");
        String ceoTGImage=config.getString("ceoTGImage");
       
        String openAMFlag = config.getString("openAMRequired");
        TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
        List<LabelBean>	dgrpList=new ArrayList<LabelBean>();
        List<String> lStrgrpList=new ArrayList<String>();
        if(request.getParameter("actionFlag") !=null && ("checkLogin".equalsIgnoreCase(request.getParameter("actionFlag")) || "checkLoginOnload".equalsIgnoreCase(request.getParameter("actionFlag")) || "getComplaintForm".equalsIgnoreCase(request.getParameter("actionFlag"))|| "logoutSessionExp".equalsIgnoreCase(request.getParameter("actionFlag"))))
        	lStrResultPage = "";
        else
        	lStrResultPage=HtmlEncode.verifySession(request, response);

        if (lStrResultPage.length() > 0)
        {
        	String callType=null;
        	if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
        	{
        		callType=request.getParameter("callType");
        	}
        	if(callType!=null && "Ajax".equals(callType))
        	{
        		//request.setAttribute("AjaxMessage","SessionExpired");
        		//return mapping.findForward("ajaxResult");
        	}
        	else
        		return mapping.findForward("sessionExpire");
        }
        LoginService loginService =(LoginService)ServiceFinder.getContext(request).getBean("loginService");
        HttpSession session = request.getSession ( false ) ;
        List<MenuVo> listMenuHeader = null;
        String lStrLangId = "en_US" ;
		
        //To identify the login url and correspondingly set the CMS url
        if(request.getRequestURL()!=null)
        {
        	String serverType=config.getString("is_prod");
        	if(serverType!=null && "false".equalsIgnoreCase(serverType))
        	{
        		session.setAttribute("CMSUrl", config.getString("cms_stage"));
        	}
        	else if(serverType!=null && "true".equalsIgnoreCase(serverType))
        	{
        		session.setAttribute("CMSUrl", config.getString("cms_prod"));
        	}
        }
		
        if(lStrFlagStatus!= null && "checkLoginOnload".equals(lStrFlagStatus)) 
	        {
		    	return mapping.findForward("onloadHomePage");	
	        }
		if(lStrFlagStatus!= null && "checkLogin".equals(lStrFlagStatus) && openAMFlag.equalsIgnoreCase("false")) 
        {
			String caseStartTime = sds.format(new Date().getTime());
	    	System.out.println(loginForm.getUsername());
	    	System.out.println(request.getParameter("username"));
	    	boolean loLoginFlag = false;
	    	userName=(String) session.getAttribute("userName");
	    	password=(String) session.getAttribute("password");
	    
	    	EmployeeDetailsVO lstEmpDetails=new EmployeeDetailsVO();
	    	if(config.getBoolean("LDAPlogin"))
			{ 
				loLoginFlag = ldapAuth.authenticateUserLDAP(loginForm.getUsername(), loginForm.getPassword());
				
				if(loLoginFlag)
					loLoginFlag=loginService.authenticateUserServiceFlagForOpenAM(loginForm.getUsername());
			}
	    	else
	    	
	    	if(loginForm.getUsername()!=null && !("").equalsIgnoreCase(loginForm.getUsername()))
	    	{
	    	loLoginFlag = loginService.authenticateUser(loginForm.getUsername(), loginForm.getPassword());
	    	}
	    	else if(userName!=null && password!=null)
	    	{
	    		loLoginFlag = loginService.authenticateUser(userName, password);	
	    	}
	    	
	    	if(loLoginFlag)
	    	{
	    		
	    		
	    		if(loginForm.getUsername()!=null && !("").equalsIgnoreCase(loginForm.getUsername()))
		    	{
		    		lstEmpDetails = loginService.checkEmpDetails(loginForm.getUsername(),loginForm.getPassword());
		    	}
	    		else if(userName!=null && password!=null)
		    	{
	    			lstEmpDetails = loginService.checkEmpDetails(userName,password);	
		    	}
	    		
	    		session.setAttribute("ldapLoginFlag", config.getBoolean("LDAPlogin"));
	    		session.setAttribute("password",loginForm.getPassword());
		    	session.setAttribute("UserRole", lstEmpDetails.getUSERROLE());
	    		session.setAttribute("userName", lstEmpDetails.getLOGINNAME());
	    		
	    		session.setAttribute("EmpID",lstEmpDetails.getUSERID());
	    		session.setAttribute("UserID",lstEmpDetails.getUSERID());
	    		session.setAttribute("LangID","en_US");
	    		session.setAttribute("LocId","LC1");
	    		session.setAttribute("UserNo", lstEmpDetails.getUSERNO());
	    		session.setAttribute("DsgId", lstEmpDetails.getDESIGNATIONID());
	    		session.setAttribute("groupList",lstEmpDetails.getGrpList());
	    		session.setAttribute("ServiceFlg",lstEmpDetails.getSERVICEFLAG());
	    		request.setAttribute("fullName", lstEmpDetails.getNAME());
	    		request.setAttribute("openAM","N");
	    		session.setAttribute("openAM","N");
				request.setAttribute("dsgnName", lstEmpDetails.getDSGNNAME());
				request.setAttribute("roleName", lstEmpDetails.getROLENAME());
				session.setAttribute("fullName", lstEmpDetails.getNAME());
				session.setAttribute("dsgnName", lstEmpDetails.getDSGNNAME());
				session.setAttribute("roleName", lstEmpDetails.getROLENAME());			
				session.setAttribute("gender", lstEmpDetails.getGENDER());
				session.setAttribute("rolesList", lstEmpDetails.getRoleList());
				session.setAttribute("groupList", lstEmpDetails.getGrpList());
				session.setAttribute("grpIdList",lstEmpDetails.getGrpIdList());
				session.setAttribute("approvalsTab",lstEmpDetails.getLOGINNAME());
				session.setAttribute("userState", lstEmpDetails.getUserState());
				session.setAttribute("moduleList",lstEmpDetails.getModuleList());
				session.setAttribute("loggedUserState", lstEmpDetails.getUserState());
				if(lstEmpDetails.getMobileNo() != null && !"".equalsIgnoreCase(lstEmpDetails.getMobileNo()))
					session.setAttribute("userMobileNo", lstEmpDetails.getMobileNo());
				  if(lstEmpDetails.getVpnAccess()!=null && "Y".equalsIgnoreCase(lstEmpDetails.getVpnAccess()))
		    		{
		    			/*InetAddress ip = InetAddress.getLocalHost();
						System.out.println("Current IP address : " + ip.getHostAddress());
						String ipAddress =config.getString("ip");
						//String arry= config.getString("grp_Check");
						//String [] arr= arry.split("~");
							
							if(ip.getHostAddress()!=null &&ipAddress!=null&& !ipAddress.equalsIgnoreCase(ip.getHostAddress()))
							{
								return mapping.findForward("noAuthorizationPage");										
							}*/	
							Enumeration en = NetworkInterface.getNetworkInterfaces();
							String ipAddress =config.getString("ip");
							String serverIp = null;
							while(en.hasMoreElements())
							{
							    NetworkInterface ni=(NetworkInterface) en.nextElement();
							    Enumeration ee = ni.getInetAddresses();
							    while(ee.hasMoreElements())
							    {
							        InetAddress ia= (InetAddress) ee.nextElement();
							        System.out.println("server IP in loop ="+ia.getHostAddress());
							        //when trust user logins with 10.10 then we get serverIp value else null
							        if(ipAddress!=null&&ia.getHostAddress()!=null&& ipAddress.equalsIgnoreCase(ia.getHostAddress()))
							        {
							        	serverIp=ia.getHostAddress();
							        	System.out.println("SERVER IP VALUE ="+serverIp);
							        } 
							    }
							 }
							if(serverIp==null)
							{
								return mapping.findForward("noAuthorizationPage");										
							}
		    		}
				
				
				int vistorCount = loginService.getVisitorCount(lstEmpDetails.getUSERID());//for visitor count in landing page
				request.setAttribute("vistorCount",vistorCount);
				
				/*Added for displaying special message*/
				if(lstEmpDetails.getDSGNNAME().equalsIgnoreCase("MEDCO"))
				{
				session.setAttribute("isMedco", "Y");
				}
				
				if(config.getString("ShowDoctorsMessage").equalsIgnoreCase("Y"))
				{
				String msgScheme=lstEmpDetails.getUserState();
				String  messageFlagAP="";String messageFlagTG="";
				boolean medcoFlag=true;boolean ppdFlag=true;int count=0;
				if(msgScheme!=null )
				{
				String messageGrps=config.getString("messageGroups");
				String[] msgGrpsList=StringUtils.split(messageGrps,"~");
				
				
				for(LabelBean grpList:lstEmpDetails.getGrpList())
				{
					for(String msgGrpId:msgGrpsList)
					{
						if(grpList.getID().equalsIgnoreCase(msgGrpId))
						{
							count++;
							if(grpList.getID().equalsIgnoreCase(config.getString("EDC.MEDCO_GROUP")))
							{
							
							msgScheme=loginService.getMedcoScheme(lstEmpDetails.getUSERID());
								if(msgScheme!=null && (config.getString("AP")).equalsIgnoreCase(msgScheme))
								{
									messageFlagAP="Y";
								}
								else if(msgScheme!=null && (config.getString("TG")).equalsIgnoreCase(msgScheme))
								{
									messageFlagTG="Y";
								}
							}
							/*else if (grpList.getID().equalsIgnoreCase(config.getString("preauth_ppd_role")) || grpList.getID().equalsIgnoreCase(config.getString("claim_cpd_role")))
							{
						       
								if(msgScheme!=null && (config.getString("AP")).equalsIgnoreCase(msgScheme))
								{
									messageFlagAP="Y";
								}
								else 
								{
									messageFlagTG="Y";
								}
								
								
						       
							}
							else
							{
								if(msgScheme!=null && (config.getString("AP")).equalsIgnoreCase(msgScheme))
								{
									messageFlagAP="Y";
								}
								else
								{
									messageFlagTG="Y";
								}
							}*/
							
						}
						
						if(count>0)
							break;
						
					}
				}
				}
				
				session.setAttribute("showMessageAP",messageFlagAP);
				session.setAttribute("showMessageTG",messageFlagTG);
				
				}
				
				/*End of special message code*/
				
				/*added to block CMS for MEDCO*/
				if(lstEmpDetails.getDSGNNAME().equalsIgnoreCase("MEDCO"))
				{
					session.setAttribute("isMedco", "Y");
					String checkHospType=loginService.checkHospType(lstEmpDetails.getUSERID());
					if(checkHospType!=null && !"N".equalsIgnoreCase(checkHospType) )
						session.setAttribute("hospDentType", checkHospType);
				}
			/*	Enumeration en = NetworkInterface.getNetworkInterfaces();
				String ipAddress =config.getString("ip");
				String serverIp = null;
				while(en.hasMoreElements())
				{
				    NetworkInterface ni=(NetworkInterface) en.nextElement();
				    Enumeration ee = ni.getInetAddresses();
				    while(ee.hasMoreElements())
				    {
				        InetAddress ia= (InetAddress) ee.nextElement();
				        System.out.println("server IP in loop ="+ia.getHostAddress());
				        //when trust user logins with 10.10 then we get serverIp value else null
				        if(ipAddress!=null&&ia.getHostAddress()!=null&& ipAddress.equalsIgnoreCase(ia.getHostAddress()))
				        {
				        	serverIp=ia.getHostAddress();
				        	System.out.println("SERVER IP VALUE ="+serverIp);
				        } 
				    }
				 }*/

				/*Added for restricting NABH medco's and mithra's*/
				for(LabelBean grpList:lstEmpDetails.getGrpList())
				{

					
					//InetAddress ip = InetAddress.getLocalHost();
				//	System.out.println("Current IP address of server : " + ip.getHostAddress());
					//String ipAddress =config.getString("ip");
				/*	String arry= config.getString("grp_Check");
					String [] arr= arry.split("~");
					for(String checkList: arr)
					{	
						if(grpList.getID()!=null && grpList.getID().equalsIgnoreCase(checkList))
						{
							System.out.println("Trust user got logged in with group "+checkList);
							if(serverIp==null)
							{
							  System.out.println("inside if condition");
							  return mapping.findForward("noAuthorizationPage");		
							}
						}																	
					
					}
					*/
						if(grpList.getID()!=null && (grpList.getID().equalsIgnoreCase(ClaimsConstants.NABH_USERS_GRP)))
						{
							return mapping.findForward("noAuthorizationPage");										
						}																	
				}
			
				/** 
				 * Get cases for which Clinical Notes is not updated 
				**/
				
				/**added for authenticating CEO**/
				
				boolean CeoFlag=false;
				if(lstEmpDetails.getLOGINNAME()!=null && lstEmpDetails.getLOGINNAME().equalsIgnoreCase("CEOTS"))
				{
					if(lstEmpDetails.getMobileNo()!=null)
					loginForm.setMobileNo(lstEmpDetails.getMobileNo());
					if(lstEmpDetails.getEmail()!=null)
					loginForm.setEmailId(lstEmpDetails.getEmail());
					CeoFlag=true;
				}
				
				if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && lstEmpDetails.getDSGNNAME()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && ("MEDCO").equalsIgnoreCase(lstEmpDetails.getDSGNNAME()))
				{
					List<EmployeeDetailsVO> casesList= loginService.checkCasesForClinicalNotes(lstEmpDetails.getUSERID());
					session.setAttribute("ClinicalNotesCasesList", casesList);
					
					/*
					 * Cases to be listed before 5 days to expire at Claim Level
					 */
				
					
					Number flaggedCases=flaggingService.getNoOfFlaggedCases(lstEmpDetails.getUSERID());
					session.setAttribute("flaggedCases", flaggedCases);
					String nimsHosp=loginService.getNimsMedco(lstEmpDetails.getUSERID());
					/*@Author Sravan
					 * Added to perform action on cases which are going to auto cancel by medco
					 */
						String userHospid= commonService.getloggedUserHospId(lstEmpDetails.getUSERID(), lstEmpDetails.getUserState());
						
							String casesFlag = config.getString("AUTO_CANCEL_EXPIRE");
					List<LabelBean> cancledCasesList= new ArrayList<LabelBean>();
					if (casesFlag != null && !"".equalsIgnoreCase(casesFlag) && casesFlag.equalsIgnoreCase("true"))
					{
						cancledCasesList= flaggingService.getCancelledCases(userHospid);
						if(cancledCasesList != null && cancledCasesList.size()>0)
						{
							if(cancledCasesList != null)
							session.setAttribute("cancledCasesList", cancledCasesList);
						}
						else
							session.setAttribute("cancledCasesList", "");
					}
					else
						session.setAttribute("cancledCasesList", "");
						session.setAttribute("nimsHosp", "Y");
				}
				List<LabelBean> userMsgList=null;
				List<LabelBean>  grpMsgList=null;
				List<LabelBean> hospMsgList=null;
				if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getUSERID())){
					
					
				 userMsgList=loginService.getUserMessage(lstEmpDetails.getUSERID(),"CD304");
					if(userMsgList!=null && userMsgList.size()>0){
						session.setAttribute("userShtMsg", userMsgList.get(0).getNATURE());
					}
					session.setAttribute("userMsgList", userMsgList);
					
				}
				
				if(lstEmpDetails.getGrpList()!=null && lstEmpDetails.getGrpList().size()>0){
					List<String> grpList=new ArrayList<String>();
					
					for(int i=0;i<lstEmpDetails.getGrpList().size();i++){
											
						grpList.add(lstEmpDetails.getGrpList().get(i).getID());
						
											}
					
					if(grpList!=null && grpList.size()>0){
						
			          grpMsgList=loginService.getGroupMessage(grpList, "CD304");
						
					}
							
					
					if(grpMsgList!=null && grpMsgList.size()>0){
						session.setAttribute("grpShtMsg", grpMsgList.get(0).getNATURE());
						}
					session.setAttribute("grpMsgList", grpMsgList);
					
					
				}
				
				if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getUSERID())){
					
					
					String hospId=null;
					
					for(int i=0;i<lstEmpDetails.getGrpList().size();i++){
						
						if(lstEmpDetails.getGrpList().get(i).getID().equalsIgnoreCase(config.getString("Group.Mithra"))||lstEmpDetails.getGrpList().get(i).getID().equalsIgnoreCase(config.getString("Group.Medco"))){
							
							
							
						hospId=loginService.getHospitslId(lstEmpDetails.getUSERID(), lstEmpDetails.getGrpList().get(i).getID());
							
						break;
						}
						
						
						
					}
					
					
					if(hospId!=null && !"".equalsIgnoreCase(hospId)){
						
						hospMsgList=loginService.getHospitalMessage(hospId,null,"CD304");
						
						
						if(hospMsgList!=null && hospMsgList.size()>0){
							session.setAttribute("hospShtMsg", hospMsgList.get(0).getNATURE());
							}
						session.setAttribute("hospMsgList", hospMsgList);
					}
					
					
					
				}

				
				
				  		
				

				if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && lstEmpDetails.getDSGNNAME()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && ("MEDCO").equalsIgnoreCase(lstEmpDetails.getDSGNNAME())){
					loginForm.setNoOfNotifications(1+userMsgList.size()+grpMsgList.size()+hospMsgList.size());
					session.setAttribute("NoOfNotifications", 1+userMsgList.size()+grpMsgList.size()+hospMsgList.size());
					
				}else{
					
					
					if(((userMsgList!=null && userMsgList.size()>0)||(grpMsgList!=null && grpMsgList.size()>0)) || (hospMsgList!=null && hospMsgList.size()>0)){
						session.setAttribute("showNotifications", 1);
						loginForm.setNoOfNotifications(userMsgList.size()+grpMsgList.size()+hospMsgList.size());
					}else{
						
						loginForm.setNoOfNotifications(0);
					}
					
				}
				boolean showDashboard=false;
				String[] droleList=null;
				if(session.getAttribute("groupList")!=null){
				if(session.getAttribute("groupList").toString()!=null)
				{
					dgrpList=(List<LabelBean>)session.getAttribute("groupList");
				}
				for(LabelBean labelBean:dgrpList)
				{
					lStrgrpList.add(labelBean.getID());
				}
				
				String dashBoardRoles=config.getString("OpsDashboardRoles");
				if(dashBoardRoles!=null)
					droleList= dashBoardRoles.split("~");
				for(String drole : droleList){
					if(lStrgrpList.contains(drole)){
						showDashboard=true;
					}
				}
				}
				request.setAttribute("showDashboard", showDashboard);
				
					List<String> grpIdList=new ArrayList<String>();
					for(int i=0;i<lstEmpDetails.getGrpList().size();i++)
					{
						if(lstEmpDetails.getGrpList().get(i).getID()!=null)
							grpIdList.add(lstEmpDetails.getGrpList().get(i).getID()); 
					}
					
					
	    		    listMenuHeader = loginService.getRecSubMenuListForEMP( "MD1",grpIdList,lstEmpDetails.getUSERID(),lStrLangId ) ;

	    		  int iMenuSize=0;
	    		  if(listMenuHeader!=null && listMenuHeader.size()>0)
	    		  {
	    		  for(MenuVo menuVo:listMenuHeader)
	    			  iMenuSize=iMenuSize+menuVo.getMODNAME().length()+2;
	    		  request.setAttribute("menuSize", Integer.toString(iMenuSize) );
	    		  session.setAttribute ( "ActionbarModulesLst", listMenuHeader ) ;
	    	      request.setAttribute ( "ActionbarModulesLst", listMenuHeader);
	    		  }
	    		  else
	    		  {
	    			  return mapping.findForward("noAuthorizationPage");
	    		  }
	    	      String lStrTheme=loginService.getTheme(lstEmpDetails.getUSERID());
	    	      
	    	      String ceoFlag="N";
		    		if(lstEmpDetails.getDSGNNAME() != null && config.getString("ACC.CEO").equals(lstEmpDetails.getDSGNNAME()))
					{
		    			ceoFlag="Y";
					}
	    	    
	    	      session.setAttribute("themeColour", lStrTheme);
	    	      
	    	      if(CeoFlag && !("Y").equalsIgnoreCase(otpCheck) && !("Y").equalsIgnoreCase(digiCheck) && ("Y").equalsIgnoreCase(config.getString("otpRequired")))
	    	      {
	    	    	  return mapping.findForward("OTPAuthenticate");
	    	      }
	    	      if(CeoFlag && ("Y").equalsIgnoreCase(otpCheck) && !("Y").equalsIgnoreCase(digiCheck))
	    	      {
	    	    	  
	    	    	  session.setAttribute("ceoAPImage", ceoAPImage);
	    	    	  session.setAttribute("ceoTGImage", ceoTGImage);
	    	    	  return mapping.findForward("frame");
	    	      }
	    	      if(CeoFlag && !("Y").equalsIgnoreCase(otpCheck) && ("Y").equalsIgnoreCase(digiCheck) )
	    	      {
				   session.setAttribute("digiCheck", digiCheck);
					return mapping
					.findForward("digitalCertifiCateAuthenticate");
	    	      }  
	    	      if(lstEmpDetails.getDIGILOGIN()==null || "".equalsIgnoreCase(lstEmpDetails.getDIGILOGIN()) || lstEmpDetails.getDIGILOGIN().equalsIgnoreCase("N"))
	    	      {
	    	    	  String caseEndTime = sds.format(new Date().getTime());
			            loggingService.logTime("LoginLoadingTime", lstEmpDetails.getUSERNO(), caseStartTime, caseEndTime,lstEmpDetails.getUSERID(), "EHS_Operations");
	    	      }
	    	      if (lstEmpDetails != null && "Y".equalsIgnoreCase(lstEmpDetails.getDIGILOGIN()) && !CeoFlag && !("Y").equalsIgnoreCase(otpCheck) )
	    	      {
	    	    	  request.setAttribute("requestToken", new FSWorker().getServerName("10.10.12.166"));
    	    	  		return mapping .findForward("digitalCertifiCateAuthenticate");
	    	      }
	    	     /* else if(ceoFlag.equalsIgnoreCase("Y"))
	    	      {
	    	    	  session.setAttribute("ceoAPImage", ceoAPImage);
	    	    	  session.setAttribute("ceoTGImage", ceoTGImage);
						return mapping.findForward("frameCEO");
	    	      }*/
	    	      else
						return mapping.findForward("frame");
	    		
	    	}else{
	    		loginForm.setMsg("invalid Login and Password");
	    		return mapping.findForward("login");	
	    	}    	
        }
		if(lStrFlagStatus!= null && "checkLogin".equals(lStrFlagStatus) && openAMFlag.equalsIgnoreCase("true")) 
        {
				
			String caseStartTime = sds.format(new Date().getTime());
				userName=(String) session.getAttribute("userName");
		    	password=(String) session.getAttribute("password");
		    	EmployeeDetailsVO lstEmpDetails=new EmployeeDetailsVO();
		    	boolean validate=false;
		    	if(loginForm.getUsername()!=null)
		    		validate=loginService.authenticateUserServiceFlagForOpenAM(loginForm.getUsername());
		    	else if(userName!=null)
		    		validate=loginService.authenticateUserServiceFlagForOpenAM(userName);
		    	if(validate==true)
		    		{
						if((loginForm.getUsername()==null||"".equalsIgnoreCase(loginForm.getUsername())) && (userName==null||"".equalsIgnoreCase(userName)))
							{
								checkLogout="Y";
								lStrFlagStatus="logout";
							}	
						else
						{
							if(loginForm.getUsername()!=null && !("").equalsIgnoreCase(loginForm.getUsername()))
					    	{
					    		lstEmpDetails = loginService.checkEmpDetails(loginForm.getUsername(),loginForm.getPassword());
					    	}
		
				    		else if(userName!=null)
		
					    	{
		
				    			lstEmpDetails = loginService.checkEmpDetails(userName,password);	
					    	}
				    		
						session.setAttribute("password",loginForm.getPassword());
						session.setAttribute ( "UserRole", lstEmpDetails.getUSERROLE());
			    		session.setAttribute("userName", lstEmpDetails.getLOGINNAME());
			    		session.setAttribute("EmpID",lstEmpDetails.getUSERID());
			    		session.setAttribute("UserID",lstEmpDetails.getUSERID());
			    		session.setAttribute("LangID","en_US");
			    		session.setAttribute("LocId","LC1");
			    		session.setAttribute("UserNo", lstEmpDetails.getUSERNO());
			    		session.setAttribute("DsgId", lstEmpDetails.getDESIGNATIONID());
			    		session.setAttribute("groupList",lstEmpDetails.getGrpList());
			    		session.setAttribute("ServiceFlg",lstEmpDetails.getSERVICEFLAG());
			    		request.setAttribute("fullName", lstEmpDetails.getNAME());
						request.setAttribute("dsgnName", lstEmpDetails.getDSGNNAME());
						session.setAttribute("loggedUserState", lstEmpDetails.getUserState());
						
						 if(lstEmpDetails.getVpnAccess()!=null && "Y".equalsIgnoreCase(lstEmpDetails.getVpnAccess()))
				    		{
				    			/*InetAddress ip = InetAddress.getLocalHost();
								System.out.println("Current IP address : " + ip.getHostAddress());
								String ipAddress =config.getString("ip");
								//String arry= config.getString("grp_Check");
								//String [] arr= arry.split("~");
									
									if(ip.getHostAddress()!=null &&ipAddress!=null&& !ipAddress.equalsIgnoreCase(ip.getHostAddress()))
									{
										return mapping.findForward("noAuthorizationPage");										
									}	*/
							 Enumeration en = NetworkInterface.getNetworkInterfaces();
								String ipAddress =config.getString("ip");
								String serverIp = null;
								while(en.hasMoreElements())
								{
								    NetworkInterface ni=(NetworkInterface) en.nextElement();
								    Enumeration ee = ni.getInetAddresses();
								    while(ee.hasMoreElements())
								    {
								        InetAddress ia= (InetAddress) ee.nextElement();
								        System.out.println("server IP in loop ="+ia.getHostAddress());
								        //when trust user logins with 10.10 then we get serverIp value else null
								        if(ipAddress!=null&&ia.getHostAddress()!=null&& ipAddress.equalsIgnoreCase(ia.getHostAddress()))
								        {
								        	serverIp=ia.getHostAddress();
								        	System.out.println("SERVER IP VALUE ="+serverIp);
								        } 
								    }
								 }
								if(serverIp==null)
								{
									return mapping.findForward("noAuthorizationPage");										
								}
				    		}
						
						int vistorCount = loginService.getVisitorCount(lstEmpDetails.getUSERID());//for visitor count in landing page
						request.setAttribute("vistorCount",vistorCount);
						
						/*Added for displaying special message*/
						if(lstEmpDetails.getDSGNNAME().equalsIgnoreCase("MEDCO"))
						{
						session.setAttribute("isMedco", "Y");
						}
						if(config.getString("ShowDoctorsMessage").equalsIgnoreCase("Y"))
						{
						String msgScheme=lstEmpDetails.getUserState();
						String  messageFlagAP="";String messageFlagTG="";
						boolean medcoFlag=true;boolean ppdFlag=true;int count=0;
						if(msgScheme!=null )
						{
						String messageGrps=config.getString("messageGroups");
						String[] msgGrpsList=StringUtils.split(messageGrps,"~");
						
						
					
						
						
						for(LabelBean grpList:lstEmpDetails.getGrpList())
						{
		
							for(String msgGrpId:msgGrpsList)
							{
								if(grpList.getID().equalsIgnoreCase(msgGrpId))
								{
									count++;
									if(grpList.getID().equalsIgnoreCase(config.getString("EDC.MEDCO_GROUP")))
									{
									
									msgScheme=loginService.getMedcoScheme(lstEmpDetails.getUSERID());
										if(msgScheme!=null && (config.getString("AP")).equalsIgnoreCase(msgScheme))
										{
											messageFlagAP="Y";
										}
										else if(msgScheme!=null && (config.getString("TG")).equalsIgnoreCase(msgScheme))
										{
											messageFlagTG="Y";
										}
									}
									/*else if (grpList.getID().equalsIgnoreCase(config.getString("preauth_ppd_role")) || grpList.getID().equalsIgnoreCase(config.getString("claim_cpd_role")))
									{
								       
										if(msgScheme!=null && (config.getString("AP")).equalsIgnoreCase(msgScheme))
										{
											messageFlagAP="Y";
										}
										else 
										{
											messageFlagTG="Y";
										}
										
										
								       
									}
									else
									{
										if(msgScheme!=null && (config.getString("AP")).equalsIgnoreCase(msgScheme))
										{
											messageFlagAP="Y";
										}
										else
										{
											messageFlagTG="Y";
										}
									}*/
									
								}
								
								if(count>0)
									break;
								
							}
						}
						}
						
						session.setAttribute("showMessageAP",messageFlagAP);
						session.setAttribute("showMessageTG",messageFlagTG);
						
						}
						
						/*End of special message code*/
						
						
						
						/*added to block CMS for MEDCO*/
						if(lstEmpDetails.getDSGNNAME().equalsIgnoreCase("MEDCO"))
						{
							session.setAttribute("isMedco", "Y");
							String checkHospType=loginService.checkHospType(lstEmpDetails.getUSERID());
							if(checkHospType!=null && !"N".equalsIgnoreCase(checkHospType) )
								session.setAttribute("hospDentType", checkHospType);
						}
						
				/*		Enumeration en = NetworkInterface.getNetworkInterfaces();
						String ipAddress =config.getString("ip");
						String serverIp = null;
						while(en.hasMoreElements())
						{
						    NetworkInterface ni=(NetworkInterface) en.nextElement();
						    Enumeration ee = ni.getInetAddresses();
						    while(ee.hasMoreElements())
						    {
						        InetAddress ia= (InetAddress) ee.nextElement();
						        System.out.println("server IP in loop ="+ia.getHostAddress());
						        //when trust user logins with 10.10 then we get serverIp value else null
						        if(ipAddress!=null&&ia.getHostAddress()!=null&& ipAddress.equalsIgnoreCase(ia.getHostAddress()))
						        {
						        	serverIp=ia.getHostAddress();
						        	System.out.println("SERVER IP VALUE ="+serverIp);
						        } 
						    }
						 }*/
						
						/*Added for restricting NABH medco's and mithra's*/
						for(LabelBean grpList:lstEmpDetails.getGrpList())
						{
								if(grpList.getID()!=null && (grpList.getID().equalsIgnoreCase(ClaimsConstants.NABH_USERS_GRP)))
								{
									return mapping.findForward("noAuthorizationPage");										
								}	
							/*	String arry= config.getString("grp_Check");
								String [] arr= arry.split("~");
								for(String checkList: arr)
								{	
									if(grpList.getID()!=null && grpList.getID().equalsIgnoreCase(checkList))
									{
										System.out.println("Trust user got logged in with group "+checkList);
										if(serverIp==null)
										{
										  System.out.println("inside if condition");
										  return mapping.findForward("noAuthorizationPage");		
										}
									}																	
								
								}*/
							}
					
						
						/**added for authenticating CEO**/
						
						boolean CeoFlag=false;
						if(lstEmpDetails.getLOGINNAME()!=null && lstEmpDetails.getLOGINNAME().equalsIgnoreCase("CEOAP"))
						{
							if(lstEmpDetails.getMobileNo()!=null)
							loginForm.setMobileNo(lstEmpDetails.getMobileNo());
							if(lstEmpDetails.getEmail()!=null)
							loginForm.setEmailId(lstEmpDetails.getEmail());
							CeoFlag=true;
						}
						
						
						/** 
						 * Get cases for which Clinical Notes is not updated 
						**/
						if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && lstEmpDetails.getDSGNNAME()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && ("MEDCO").equalsIgnoreCase(lstEmpDetails.getDSGNNAME()))
						{
							List<EmployeeDetailsVO> casesList= loginService.checkCasesForClinicalNotes(lstEmpDetails.getUSERID());
							session.setAttribute("ClinicalNotesCasesList", casesList);
							Number flaggedCases=flaggingService.getNoOfFlaggedCases(lstEmpDetails.getUSERID());
							session.setAttribute("flaggedCases", flaggedCases);
							String nimsHosp=loginService.getNimsMedco(lstEmpDetails.getUSERID());
							/*@Author Sravan
							 * Added to perform action on cases which are going to auto cancel by medco
							 */
								String userHospid= commonService.getloggedUserHospId(lstEmpDetails.getUSERID(), lstEmpDetails.getUserState());
										String casesFlag = config.getString("AUTO_CANCEL_EXPIRE");
									List<LabelBean> cancledCasesList= new ArrayList<LabelBean>();
									if (casesFlag != null && !"".equalsIgnoreCase(casesFlag) && casesFlag.equalsIgnoreCase("true"))
									{
										cancledCasesList= flaggingService.getCancelledCases(userHospid);
										if(cancledCasesList != null && cancledCasesList.size()>0)
										{
											if(cancledCasesList != null)
											session.setAttribute("cancledCasesList", cancledCasesList);
										}
										else
											session.setAttribute("cancledCasesList", "");
									}
									else
										session.setAttribute("cancledCasesList", "");
										session.setAttribute("nimsHosp", "Y");
						}
						
						List<LabelBean> userMsgList=null;
						List<LabelBean>  grpMsgList=null;
						List<LabelBean> hospMsgList=null;
						if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getUSERID())){
							
							
						 userMsgList=loginService.getUserMessage(lstEmpDetails.getUSERID(),"CD304");
							if(userMsgList!=null && userMsgList.size()>0){
								session.setAttribute("userShtMsg", userMsgList.get(0).getNATURE());
							}
							session.setAttribute("userMsgList", userMsgList);
							
						}
						
						if(lstEmpDetails.getGrpList()!=null && lstEmpDetails.getGrpList().size()>0){
							List<String> grpList=new ArrayList<String>();
							
							for(int i=0;i<lstEmpDetails.getGrpList().size();i++){
													
								grpList.add(lstEmpDetails.getGrpList().get(i).getID());
								
													}
							
							if(grpList!=null && grpList.size()>0){
								
					          grpMsgList=loginService.getGroupMessage(grpList, "CD304");
								
							}
									
							
							if(grpMsgList!=null && grpMsgList.size()>0){
								session.setAttribute("grpShtMsg", grpMsgList.get(0).getNATURE());
								}
							session.setAttribute("grpMsgList", grpMsgList);
							
							
						}
						
						if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getUSERID())){
							
							
							String hospId=null;
							
							for(int i=0;i<lstEmpDetails.getGrpList().size();i++){
								
								if(lstEmpDetails.getGrpList().get(i).getID().equalsIgnoreCase(config.getString("Group.Mithra"))||lstEmpDetails.getGrpList().get(i).getID().equalsIgnoreCase(config.getString("Group.Medco"))){
									
									
									
								hospId=loginService.getHospitslId(lstEmpDetails.getUSERID(), lstEmpDetails.getGrpList().get(i).getID());
									
								break;
								}
								
								
								
							}
							
							
							if(hospId!=null && !"".equalsIgnoreCase(hospId)){
								
								hospMsgList=loginService.getHospitalMessage(hospId,null,"CD304");
								
								
								if(hospMsgList!=null && hospMsgList.size()>0){
									session.setAttribute("hospShtMsg", hospMsgList.get(0).getNATURE());
									}
								session.setAttribute("hospMsgList", hospMsgList);
							}
							
							
							
						}
		
						
						
						  		
						
		
						if(lstEmpDetails.getUSERID()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && lstEmpDetails.getDSGNNAME()!=null && !"".equalsIgnoreCase(lstEmpDetails.getDSGNNAME()) && ("MEDCO").equalsIgnoreCase(lstEmpDetails.getDSGNNAME())){
							loginForm.setNoOfNotifications(1+userMsgList.size()+grpMsgList.size()+hospMsgList.size());
							session.setAttribute("NoOfNotifications", 1+userMsgList.size()+grpMsgList.size()+hospMsgList.size());
							
						}else{
							
							
							if(((userMsgList!=null && userMsgList.size()>0)||(grpMsgList!=null && grpMsgList.size()>0)) || (hospMsgList!=null && hospMsgList.size()>0)){
								session.setAttribute("showNotifications", 1);
								loginForm.setNoOfNotifications(userMsgList.size()+grpMsgList.size()+hospMsgList.size());
								session.setAttribute("NoOfNotifications", loginForm.getNoOfNotifications());
							}else{
								
								loginForm.setNoOfNotifications(0);
								
								session.setAttribute("NoOfNotifications", 0);
							}
							
							
							
						}
						request.setAttribute("roleName", lstEmpDetails.getROLENAME());
						session.setAttribute("fullName", lstEmpDetails.getNAME());
						session.setAttribute("dsgnName", lstEmpDetails.getDSGNNAME());
						session.setAttribute("roleName", lstEmpDetails.getROLENAME());			
						session.setAttribute("gender", lstEmpDetails.getGENDER());
						session.setAttribute("rolesList", lstEmpDetails.getRoleList());
						session.setAttribute("groupList", lstEmpDetails.getGrpList());
						session.setAttribute("grpIdList",lstEmpDetails.getGrpIdList());
						session.setAttribute("approvalsTab",lstEmpDetails.getLOGINNAME());
						request.setAttribute("openAM", "Y");
			    		session.setAttribute("openAM", "Y");
						session.setAttribute("userState", lstEmpDetails.getUserState());
						session.setAttribute("moduleList",lstEmpDetails.getModuleList());
						
			    		if(lstEmpDetails.getUSERID()!=null){
			    			List<String> grpIdList=new ArrayList<String>();
							for(int i=0;i<lstEmpDetails.getGrpList().size();i++)
							{
								if(lstEmpDetails.getGrpList().get(i).getID()!=null)
									grpIdList.add(lstEmpDetails.getGrpList().get(i).getID()); 
							}
			    		 listMenuHeader = loginService.getRecSubMenuListForEMP( "MD1",grpIdList,lstEmpDetails.getUSERID(),lStrLangId ) ;
			    		  int iMenuSize=0;
			    		  if(listMenuHeader!=null && listMenuHeader.size()>0)
			    		  {
			    		  for(MenuVo menuVo:listMenuHeader)
			    			  iMenuSize=iMenuSize+menuVo.getMODNAME().length()+2;
			    		  request.setAttribute("menuSize", Integer.toString(iMenuSize) );
			    		  session.setAttribute ( "ActionbarModulesLst", listMenuHeader ) ;
			    	      request.setAttribute ( "ActionbarModulesLst", listMenuHeader);
			    		  }
			    		  else
			    		  {
			    			  return mapping.findForward("noAuthorizationPage");
			    		  }
			    	      String lStrTheme=loginService.getTheme(lstEmpDetails.getUSERID());
			    	      session.setAttribute("themeColour", lStrTheme);
						  }
			    		
			    		else
			    		  {
			    			  return mapping.findForward("noAuthorizationPage");
			    		  }
						   session.setAttribute("deployUri",request.getParameter("deployUri"));
						   session.setAttribute("entityID",request.getParameter("entityID"));
						   session.setAttribute("spEntityID",request.getParameter("spEntityID"));
						   session.setAttribute("nameId",request.getParameter("nameId"));
						   session.setAttribute("nameValue",request.getParameter("nameValue"));
						   session.setAttribute("sessionIndex",request.getParameter("sessionIndex"));
						   session.setAttribute("sessionSeqId",request.getParameter("sessionIndex"));
						   if(CeoFlag && !("Y").equalsIgnoreCase(otpCheck) && !("Y").equalsIgnoreCase(digiCheck) )
				    	      {
				    	    	  return mapping.findForward("OTPAuthenticate");
				    	      }  
						   
						   if(CeoFlag && ("Y").equalsIgnoreCase(otpCheck) && !("Y").equalsIgnoreCase(digiCheck))
				    	      {
							   session.setAttribute("ceoAPImage", ceoAPImage);
				    	    	  session.setAttribute("ceoTGImage", ceoTGImage);
				    	    	  return mapping.findForward("frameCEOOpenAm");
				    	      }
						   if(CeoFlag && !("Y").equalsIgnoreCase(otpCheck) && ("Y").equalsIgnoreCase(digiCheck) )
				    	      {
							   session.setAttribute("digiCheck", digiCheck);
								return mapping
								.findForward("digitalCertifiCateAuthenticate");
				    	      }  
						   if(lstEmpDetails.getDIGILOGIN()==null || "".equalsIgnoreCase(lstEmpDetails.getDIGILOGIN()) || lstEmpDetails.getDIGILOGIN().equalsIgnoreCase("N"))
				    	      {
				    	    	  String caseEndTime = sds.format(new Date().getTime());
						            loggingService.logTime("LoginLoadingTime", lstEmpDetails.getUSERNO(), caseStartTime, caseEndTime,lstEmpDetails.getUSERID(), "EHS_Operations");
				    	      }
				    	   if (lstEmpDetails != null && "Y".equalsIgnoreCase(lstEmpDetails.getDIGILOGIN()) && !CeoFlag && !("Y").equalsIgnoreCase(otpCheck) )
						   {
						   request.setAttribute("requestToken", new FSWorker().getServerName("10.10.12.166"));
									return mapping.findForward("digitalCertifiCateAuthenticate");
							}
							else
								return mapping.findForward("frameOpenAm");
						}
				    	
		    		}
		    	else
		    		{
			    		session = request.getSession(false);
			            
			            String entityID=(String)request.getParameter("entityID");
			            String deployuri = request.getRequestURI();
			            int slashLoc = deployuri.indexOf("/", 1);
			            if (slashLoc != -1) {
			                deployuri = deployuri.substring(0, slashLoc);
			            }
			            Map idpMap = getIDPBaseUrlAndMetaAlias(entityID, deployuri);
			            String idpBaseUrl = (String) idpMap.get("idpBaseUrl");
			            String idpMetaAlias = (String) idpMap.get("idpMetaAlias");
			            String spEntityID=(String)request.getAttribute("spEntityID");
			            String fedletBaseUrl = getFedletBaseUrl(spEntityID, deployuri);
			            String logOutUrl=idpBaseUrl + "/IDPSloInit?metaAlias=" + idpMetaAlias + "&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect&RelayState=" + fedletBaseUrl + "/login/HomePageOpenAM.jsp";
						if (session != null) 
			            	{
				                Enumeration lenum = session.getAttributeNames();
				                while (lenum.hasMoreElements()) {
				                    String lStrSessionAttr = (String)lenum.nextElement();
				                    if (lStrSessionAttr != null)
				                        session.removeAttribute(lStrSessionAttr);
				                }
				                session.invalidate();
			            	}
						String alertMsg="You are not an Authorized User.";
			            request.setAttribute("alertMsg", alertMsg);
			            request.setAttribute("openAMLogOutUrl", logOutUrl);
	    	 			return mapping.findForward("openAMLogout");
		    		}
        }
		/*@Author Sravan Reddy
		 *Added for audit purpose User Login Details Start
		 */
			if (lStrFlagStatus != null && "saveLoginDtls".equals(lStrFlagStatus))
			{
				LoginDtlsVO vo = new LoginDtlsVO();
				session = request.getSession(false);
				String ipAdress= request.getParameter("idAdress");
				String empId= (String)session.getAttribute("EmpID");
				String seqId="";
				if(session.getAttribute("sessionSeqId")!=null)
					seqId = (String) session.getAttribute("sessionSeqId");
				vo.setSeqId(seqId);
				vo.setSessionId(session.getId());
				vo.setIpAddress(ipAdress);
				vo.setUserId(empId);
				EmployeeDetailsVO usrDtls = loginService.saveLoginDtls(vo);
				if(usrDtls != null)
				{
					if(!"".equalsIgnoreCase(usrDtls.getSessionId()) && usrDtls.getSessionId() != null )
						session.setAttribute("sessionId", usrDtls.getSessionId());
					if(usrDtls.getSeqId() != null && usrDtls.getSeqId().startsWith("TG") )
						session.setAttribute("sessionSeqId", usrDtls.getSeqId());
				}
				request.setAttribute("AjaxMessage","");
		    	return mapping.findForward("ajaxResult");
			}
			/*
			 * End of  User Login Details 
			 */
		
	/*added for generating OTP and sending SMS*/		
		
		if (lStrFlagStatus != null && "generateOTP".equals(lStrFlagStatus))
			{
				String userId=(String) session.getAttribute("UserID");
				String mobile=request.getParameter("mobileNum");
				String OTP=null;
				//userId=(String) session.getAttribute("userId");
				//mobile=loginForm.getMobileNo();
				String msg=null;
				EmployeeDetailsVO employeeDetailsVO=new EmployeeDetailsVO();
				employeeDetailsVO.setUSERID(userId);
				employeeDetailsVO.setMobileNo(mobile);
				msg=loginService.generateOTPAndSendPswdSms(employeeDetailsVO);
				if(msg!=null)
				{
					OTP=msg;
					System.out.println(OTP);
					request.setAttribute("OTPSent", "Y");
					request.setAttribute("otp",OTP);
				}
				else
				{
					request.setAttribute("OTPSent", "N");
				}
				request.setAttribute("AjaxMessage", OTP);
				return mapping.findForward("ajaxResult"); 
			}
			
		if (lStrFlagStatus != null && "loginWithDigi".equals(lStrFlagStatus)) {
		
			
			session.setAttribute("digiCheck", digiCheck);
			return mapping
			.findForward("digitalCertifiCateAuthenticate");
		}
			
			
			if (lStrFlagStatus != null && "verifyDigiSign".equals(lStrFlagStatus)) {

			
			DigitalSignature ds = (DigitalSignature)ServiceFinder.getContext(request).getBean("DigitalSignatureClientBean");
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("txtData",request.getParameter("txtData"));
                        map.put("signedData",request.getParameter("signedData"));
                        map = ds.verify(map);
                        Date expDate = (Date)map.get("ExpiryDate");
                        Date signTime = (Date)map.get("SignedTime");
                        String serialNo = (String)map.get("Serial_No");
						logger.info("Digital certificate serial no : "+serialNo);
                        String digiVerify=(String) session.getAttribute("digiVerify");
			DigitalCertificateServiceImpl digitalCertificateService = (DigitalCertificateServiceImpl) ServiceFinder
					.getContext(request).getBean("digitalCertificateService");
			String lStrUserID = (String) session.getAttribute("UserID");
			
            
			boolean ldigiAuthenticationFlag = digitalCertificateService
					.authenticateDigitalCertificate(
							serialNo, lStrUserID);
			//ldigiAuthenticationFlag=true;
			if (!ldigiAuthenticationFlag) {
				loginForm.setMsg("Digital Certificate Not Valid");
				
				return mapping.findForward("onload");
			}
			else if ((ldigiAuthenticationFlag) && ("Y").equalsIgnoreCase(digiVerify)) {
				
				
				return mapping.findForward("noAuthorizationPage");
			}

			return mapping.findForward("frameOpenAm");
		}
		if (lStrFlagStatus!= null && lStrFlagStatus.equalsIgnoreCase("ChangePwd")) {
			
			
	    	String lStrPhoneNumber=loginService.getPhoneNumber((String)session.getAttribute("EmpID"));
	    	request.setAttribute("phoneNumber", lStrPhoneNumber);
	    	return mapping.findForward("ChangePasswordPage");
	   }
		
		 if(lStrFlagStatus!= null && "saveTheme".equals(lStrFlagStatus)) 
	        {
		    	String lStrTheme=request.getParameter("themeColour");
		    	 boolean lThemeChangeStatus=loginService.saveTheme(lStrTheme,(String)session.getAttribute ( "EmpID"));
		    	if(lThemeChangeStatus)
		    	 session.setAttribute("themeColour", request.getParameter("themeColour"));
		    	return mapping.findForward("json");
	        }
		 
		 if(lStrFlagStatus!= null && "reloadHomePage".equals(lStrFlagStatus)) 
	        {
			 
			 String ceoFlag="N";
	    		if(session.getAttribute("dsgnName") != null && config.getString("ACC.CEO").equals(session.getAttribute("dsgnName")) && session.getAttribute("userState")!=null && session.getAttribute("userState").equals("CD201"))
				{
	    			ceoFlag="Y";
				}
          if(ceoFlag.equalsIgnoreCase("Y"))
					return mapping.findForward("frameCEO");
				else
					return mapping.findForward("frame");
	        }
		 if(lStrFlagStatus!= null && "reloadPage".equals(lStrFlagStatus)) 
	        {
					return mapping.findForward("frame");
	        }
		 if(lStrFlagStatus!= null && "defaultPage".equals(lStrFlagStatus)) 
	        {
			 List<LabelBean> lStMainMod=loginService.getMainMods((String)session.getAttribute("EmpID"));
			 request.setAttribute("mainMenu", lStMainMod);
		    	return mapping.findForward("defaultPage");
	        }
		 if (lStrFlagStatus!= null && lStrFlagStatus.equalsIgnoreCase("UpdateProfile")){
			 return mapping.findForward("UpdateProfilePage");
		 }
		 if (lStrFlagStatus != null && "getcontactDetailsForEhfmUsers".equals(lStrFlagStatus)) {
				String login=(String) session.getAttribute("userName");
				//to get contact details
				List<EmployeeDetailsVO> lstEmpDetails = loginService.getContactInfoForEhfmUsers(login);
				List<String> unitsList = new ArrayList<String>();
				for (EmployeeDetailsVO labelBean : lstEmpDetails) {
					if (labelBean.getID() != null && labelBean.getID() != null)
							unitsList
									.add(labelBean.getID()
											+ "~"
											+ labelBean.getVALUE());
				}
				if (unitsList != null && unitsList.size() > 0)
				request.setAttribute("AjaxMessage", unitsList);
				return mapping.findForward("ajaxResult"); 
				
				
				
			}
		 if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("logout"))
         {
			 
			 String openAMLogOutUrl=request.getParameter("openAMLogOutUrl");
	         
      	   session = request.getSession(false);
      		/*@Author Sravan Reddy
			 *Added for audit purpose User Login Details Start
			 */
			LoginDtlsVO vo = new LoginDtlsVO();
			String sessionId="";
			String seqId="";
			if(session.getAttribute("sessionId") !=null)
				 sessionId = session.getAttribute("sessionId").toString();
			if(session.getAttribute("sessionSeqId") !=null)
				seqId = (String) session.getAttribute("sessionSeqId");
			 
			
			vo.setSeqId(seqId);
			vo.setSessionId(sessionId);
			
			EmployeeDetailsVO usrDtls = loginService.saveLogOutDtls(vo);
			
			/*
			 * End of  User Login Details 
			 */
         if (session != null) {
             Enumeration lenum = session.getAttributeNames();
             while (lenum.hasMoreElements()) {
                 String lStrSessionAttr = (String)lenum.nextElement();
                 if (lStrSessionAttr != null)
                     session.removeAttribute(lStrSessionAttr);
             }
             session.invalidate();
         }
		 if(openAMLogOutUrl!=null)
	         	{
	        	 	if(openAMLogOutUrl.length()>0)
	        	 		{
	        	 			request.setAttribute("openAMLogOutUrl", openAMLogOutUrl);
	        	 			return mapping.findForward("openAMLogout");
	        	 		}
	         	}
		 if(checkLogout!=null)
		 	{
			 	if("Y".equalsIgnoreCase(checkLogout))
			 		{
			 			return mapping.findForward("indexOpenAM");
			 		}
		 	}
         return mapping.findForward("onload");
         }
	     
		    if ( lStrFlagStatus != null && lStrFlagStatus.equalsIgnoreCase ( "viewAttachment" ) )
		      {
		    	 String newAttachFlg=request.getParameter("newAttchFlg");
       	/**
       	 * to show attachments for special Investigations
       	 */
       	 String lStrFilePath = null;String lStrContentType=null;
       	 if(newAttachFlg!=null && newAttachFlg.length()>0 && newAttachFlg.equalsIgnoreCase("A"))
       	 {
       		lStrFilePath=config.getString("clinicalPenality");
       	 }
       	 
       	 else if(newAttachFlg!=null && newAttachFlg.length()>0 && newAttachFlg.equalsIgnoreCase("Y"))
       		 {
       			lStrFilePath=config.getString("revisedCRPathNew");
       		 }
       		 else
       		 {
       			lStrFilePath=config.getString("revisedCRPath");
       		 }
       	 
       	 
       
       	 
       	  
       	    File file = null ;
			        FileInputStream fis = null ;
			        DataInputStream dis = null ;
			        String lStrType = null;
			        
		          /**
		           * 
		           */
		          String fileExt = lStrFilePath.substring((lStrFilePath.lastIndexOf(".")+1));
		          String lStrFileName=lStrFilePath.substring((lStrFilePath.lastIndexOf("/")+1));
		          String attachMode="attachment";
		         
		              attachMode="inline";
		              //request.setAttribute("data", "Y");
		              if(newAttachFlg!=null &&  newAttachFlg.length()>0 && newAttachFlg.equalsIgnoreCase("A"))
		              {
		            	  lStrContentType="application/pdf";
		              }
		              else if(newAttachFlg!=null &&  newAttachFlg.length()>0 && newAttachFlg.equalsIgnoreCase("Y"))
		              {
		            	  lStrContentType="application/pdf";
		              }
		              else
		              {
		            	  lStrContentType="image/jpeg";
		              }
		          
		      
		           //attachMode="inline";
		   
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
		              if(fis!=null)
		              {
		              ServletOutputStream out = response.getOutputStream();
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
		              out.close();
		              }
		              
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
		           // if(attachMode.equalsIgnoreCase("inline"))
		            request.setAttribute("showModal","Y");
		            return mapping.findForward("ErrorPage");
		            
		          }
		         /* finally
		          {
		        	  out.close();
		          }*/
		          
		      }
		 if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("logoutSessionExp"))
	         {
			 	String openAMLogOutURL=null;
			 	if((session.getAttribute("openAMLogOutURLAfterSessionExpiry"))!=null)
			 		openAMLogOutURL=(String)(session.getAttribute("openAMLogOutURL"));
			 	
				 session = request.getSession(false);
				 	/*@Author Sravan Reddy
					 *Added for audit purpose User Login Details Start
					 */
					LoginDtlsVO vo = new LoginDtlsVO();
					String sessionId="";
					String seqId="";
					if(session.getAttribute("sessionId") !=null)
						 sessionId = session.getAttribute("sessionId").toString();
					if(session.getAttribute("sessionSeqId") !=null)
						seqId = (String) session.getAttribute("sessionSeqId");
					 
					vo.setSeqId(seqId);
					vo.setSessionId(sessionId);
					
					EmployeeDetailsVO usrDtls = loginService.saveLogOutDtls(vo);
					
					/*
					 * End of  User Login Details 
					 */
		         if (session != null) 
		         	{
			             Enumeration lenum = session.getAttributeNames();
			             while (lenum.hasMoreElements()) 
				             {
				                 String lStrSessionAttr = (String)lenum.nextElement();
				                 if (lStrSessionAttr != null)
				                     session.removeAttribute(lStrSessionAttr);
				             }
			             session.invalidate();
		         	}
		         if(openAMLogOutURL!=null && !"".equalsIgnoreCase(openAMLogOutURL) && openAMLogOutURL.length()>0)
		         	{
			        	 request.setAttribute("openAMLogOutUrl", openAMLogOutURL);
	     	 			 return mapping.findForward("openAMLogout");		
		         	}
		         return mapping.findForward("onload");
	         }
		 
		return null;
    }
	public static String getClientIpAddress(HttpServletRequest request) throws UnknownHostException {
		InetAddress IP=InetAddress.getLocalHost();
		System.out.println("IP of my system is := "+IP.getHostAddress());
		return IP.getHostAddress();
	}
	
	public String getMACAddress(String ip){ 
        String str = ""; 
        String macAddress = ""; 
        try { 
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
            InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
            LineNumberReader input = new LineNumberReader(ir); 
            for (int i = 1; i <100; i++) { 
                str = input.readLine(); 
                if (str != null) { 
                    if (str.indexOf("MAC Address") > 1) { 
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
                        break; 
                    } 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(System.out); 
        } 
        return macAddress; 
    }
	@SuppressWarnings("unchecked" )
	public Map getIDPBaseUrlAndMetaAlias(String idpEntityID, String deployuri)
    {
        Map returnMap = new HashMap();
        if (idpEntityID == null) {
            return returnMap;
        }
        String idpBaseUrl = null;
        try {
            // find out IDP meta alias
            SAML2MetaManager manager = new SAML2MetaManager();
            IDPSSODescriptorElement idp =
                manager.getIDPSSODescriptor("/", idpEntityID);
            @SuppressWarnings("rawtypes")
			List ssoServiceList = idp.getSingleSignOnService();
            if ((ssoServiceList != null)
                && (!ssoServiceList.isEmpty())) {
                
				Iterator i = ssoServiceList.iterator();
                while (i.hasNext()) {
                    SingleSignOnServiceElement sso =
                        (SingleSignOnServiceElement) i.next();
                    if ((sso != null) && (sso.getBinding() != null)) {
                        String ssoURL = sso.getLocation();
                        int loc = ssoURL.indexOf("/metaAlias/");
                        if (loc == -1) {
                            continue;
                        } else {
                            returnMap.put("idpMetaAlias",
                                ssoURL.substring(loc + 10));
                            String tmp = ssoURL.substring(0, loc);
                            loc = tmp.lastIndexOf("/");
                            returnMap.put("idpBaseUrl", tmp.substring(0, loc));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            SAML2Utils.debug.error("header.jspf: couldn't get IDP base url:",e);
        }
        return returnMap;
    }
	public String getFedletBaseUrl(String spEntityID, String deployuri)
	    {
	        if (spEntityID == null) {
	            return null;
	        }
	        String fedletBaseUrl = null;
	        try {
	            SAML2MetaManager manager = new SAML2MetaManager();
	            SPSSODescriptorElement sp =
	                manager.getSPSSODescriptor("/", spEntityID);
	            List acsList = sp.getAssertionConsumerService();
	            if ((acsList != null) && (!acsList.isEmpty())) {
	                Iterator j = acsList.iterator();
	                while (j.hasNext()) {
	                    AssertionConsumerServiceElement acs =
	                        (AssertionConsumerServiceElement) j.next();
	                    if ((acs != null) && (acs.getBinding() != null)) {
	                        String acsURL = acs.getLocation();
	                        int loc = acsURL.indexOf(deployuri + "/");
	                        if (loc == -1) {
	                            continue;
	                        } else {
	                            fedletBaseUrl = acsURL.substring(
	                                0, loc + deployuri.length());
	                            break;
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	            SAML2Utils.debug.error(
	                "header.jspf: couldn't get fedlet base url:",e);
	        }
	        return fedletBaseUrl;
	    }
	
}
