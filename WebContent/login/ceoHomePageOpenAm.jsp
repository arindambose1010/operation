<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/include.jsp"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>HOME PAGE</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script src="js/jquery-1.9.1.js"></script>
<link href="/Operations/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/app.v2.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="http://flatfull.com/themes/first/css/font.css">

<style type="text/css">
iframe {
  margin-left: 6%;
}

.img-zoom {
    width: 310px;
    -webkit-transition: all .2s ease-in-out;
    -moz-transition: all .2s ease-in-out;
    -o-transition: all .2s ease-in-out;
    -ms-transition: all .2s ease-in-out;
}
 
.transition {
    -webkit-transform: scale(2); 
    -moz-transform: scale(2);
    -o-transform: scale(2);
    transform: scale(2);
}

</style>
<%@page
import="com.sun.identity.saml2.common.SAML2Exception,
com.sun.identity.saml2.common.SAML2Constants,
com.sun.identity.saml2.assertion.Assertion,
com.sun.identity.saml2.assertion.Subject,
com.sun.identity.saml2.profile.SPACSUtils,
com.sun.identity.saml2.protocol.Response,
com.sun.identity.saml2.assertion.NameID,
com.sun.identity.saml.common.SAMLUtils,
com.sun.identity.shared.encode.URLEncDec,
com.sun.identity.plugin.session.SessionException,
java.io.IOException,
java.util.Iterator,
java.util.List,
java.util.Map,
java.util.HashMap,
java.util.HashSet,
java.util.Set,
java.util.*,
com.sun.identity.saml2.common.SAML2Utils,
com.sun.identity.saml2.jaxb.metadata.AssertionConsumerServiceElement,
com.sun.identity.saml2.jaxb.metadata.IDPSSODescriptorElement,
com.sun.identity.saml2.jaxb.metadata.SPSSODescriptorElement,
com.sun.identity.saml2.jaxb.metadata.SingleSignOnServiceElement,
com.sun.identity.saml2.meta.SAML2MetaManager"
%>

<script type="text/javascript">

$(document).ready(function(){
    $('.img-zoom').hover(function() {
        $(this).addClass('transition');
 
    }, function() {
        $(this).removeClass('transition');
    });
  });

var globalURl="";
function setGlobalUrl(url){
	globalURl=url;
}
$(function(){
	url="";
	  $('#dashboardDiv').on('hidden.bs.modal', function(){
		  $("#dashboardDiv iframe").attr({'src':url,
		        'height': '0',
		        'width': '0'});
		});
	//  class="dd"
		  $('.navbar-collapse li li a').click('.dd', function() {
	//  $('.navbar-collapse li li a').click('li', function() {
	
		  var navbar_toggle = $('.navbar-toggle');
		  if (navbar_toggle.is(':visible')) {
		      navbar_toggle.trigger('click');
		  }});

		  $('.homeIcon').click('li', function() {
				//  $('.navbar-collapse li li a').click('li', function() {
				
					  var navbar_toggle = $('.navbar-toggle');
					  if (navbar_toggle.is(':visible')) {
					      navbar_toggle.trigger('click');
					  }});
		  
	  
	 
});
function getGlobalUrl(){ 
	if (globalURl.indexOf("NaN") !=-1) { 
	    globalURl=globalURl.replace('NaN','');
	}
	document.getElementById("middleFrame").src=globalURl;
}
var attachmentWin = null;
function fn_CloseWins()
{
	if(attachmentWin != null)
		attachmentWin.close();
	}
function fn_changeTheme(themeColour)
{
	document.getElementById('middleFrame').style.marginLeft="6%";

var xmlhttp;
if (window.XMLHttpRequest)
{
xmlhttp=new XMLHttpRequest();
}
else if (window.ActiveXObject)
{		
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
else
{
alert("Your browser does not support XMLHTTP!");
}
xmlhttp.onreadystatechange=function()
{
if(xmlhttp.readyState==4)
{
	    document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=reloadHomePage";
		document.forms[0].submit();
}			
}; 

	var url =  "/<%=context%>/loginAction.do?actionFlag=saveTheme&themeColour="+themeColour;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
}
function sessionExpireyClose(){
	window.close();
}

function fn_loadImage()
{
	document.getElementById('middleFrame').style.marginLeft="3%";
	document.getElementById('processImagetable').style.display="";
}
$(function(){
	setInterval(function()
			{$('[blink]').fadeOut(50, function()
					{$(this).fadeIn(50, function()
							{$(this).fadeOut(50, function()
									{$(this).fadeIn(50, function()
											{$(this).fadeOut(50, function()
													{$(this).fadeIn(50);});});});});});}, 2000);});
function detectBrowser(){
	if ( navigator.appName == 'Opera' ) return 'Opera';
	 if ( navigator.appName == 'Microsoft Internet Explorer' )  return 'MSIE';
	 if ( navigator.userAgent.indexOf( 'Chrome' ) >= 0 ) return 'Chrome';
	 if ( navigator.userAgent.indexOf( 'SeaMonkey' ) >= 0 ) return 'SeaMonkey';  // must test before Firefox
	 if ( navigator.userAgent.indexOf( 'Safari' ) >= 0 ) return 'Safari';
	 if ( navigator.userAgent.indexOf( 'Firefox' ) >= 0 ) return 'Firefox';
	 return navigator.appName;
} 
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 
}
function fn_logout() 
{
	document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=logout";
	document.forms[0].submit();
}

function redirectDefaultPage()
{ 
	document.getElementById('middleFrame').style.marginLeft="6%";
	document.getElementById("middleFrame").src='login/afterLoginTest.jsp'; 
	var newheight= $( document ).height();
	document.getElementById("middleFrame").style.height=(newheight-(120))+"px"; 
}

function fn_redirect()
{
    document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=reloadPage";
	document.forms[0].submit();
}

function fn_changePassword() {
	document.getElementById('middleFrame').style.marginLeft="6%";
	var userName = '${userName}';
	document.getElementById("changePasswordDiv").src='http://www.ehf.gov.in/Home/loginAction.do?actionFlag=checkLogin&subActionFlag=ChangePwd&userName='+userName;

}

  	function showDashboard(){
  		 var url='http://stage.ehf.gov.in/HomePage/noOfPostsAction.do?actionFlag=getDashBoard&schemeType=CD203';
  		 $("#dashboardDiv iframe").attr({'src':url,
 	        'height': '100%',
 	        'width': '100%'});
  	  }
  	
  //Added by G.Namratha for admin sanction workflow
  	function fn_sanctionForm()
  	{
  		fn_loadImage();
  		document.getElementById("middleFrame").src='/<%=context%>/adminSanction.do?actionFlag=sanctionForm';
  	}
  	function fn_sancWorkFlow()
  	{
  		
  		document.getElementById("middleFrame").src='/<%=context%>/adminSanction.do?actionFlag=sanctionWorkflow&userId=${UserID}';
  	}
  	function fn_sancStatus()
  	{
  		fn_loadImage();
  		document.getElementById("middleFrame").src='/<%=context%>/adminSanction.do?actionFlag=viewSanctionStatus';
  	}	

  	function fn_dashboard()
  	{
  		
  		document.getElementById("middleFrame").src='/<%=context%>/adminSanction.do?actionFlag=getDashBoard';
  	}
//Added by Sreedevi for Empanelment
function fn_ceoWorkList(){
	//fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/CeoWorkListAction.do?actionVal=GETCEOWORKLIST';
}
function fn_othrStateCeoWorkList(){
	//fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/CeoWorkListAction.do?actionVal=GETCEOWORKLIST&menu=othrSt';
}
function fn_ceoEDCWorkList(){	
	document.getElementById("middleFrame").src='/<%=context%>/CeoWorkListAction.do?actionVal=GETEDCCEOWORKLIST';		
}
function fn_PendingAprvl()
{
	fn_loadImage();
 	document.getElementById("middleFrame").src='/<%=context%>/ceoWorklist.do?flag=pendingApprvls';
}
	function fn_operations()
  	{
  		
  		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=preauth&ceoFlag=Y';
  	}
function fn_refresh()
{
	
	fn_operations();
	
}
</script>
</head>
<body style="" onload="javascript:redirectDefaultPage();fn_dashboard();">
<% if(session.getAttribute("userName")!= null || session == null) {
%>
<html:form  method="post"  action="/loginAction.do" >
	<!-- header -->
	<%!
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
%><%!
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
%>
	<%
    String deployuri = request.getRequestURI();
    int slashLoc = deployuri.indexOf("/", 1);
    if (slashLoc != -1) {
        deployuri = deployuri.substring(0, slashLoc);
    }
%>
<%
String deployUri=(String)session.getAttribute("deployUri");
String entityID=(String)session.getAttribute("entityID");
String spEntityID=(String)session.getAttribute("spEntityID");
String nameId=(String)session.getAttribute("nameId");
String value=(String)session.getAttribute("nameValue");
String sessionIndex=(String)session.getAttribute("sessionIndex");
Map idpMap = getIDPBaseUrlAndMetaAlias(entityID, deployuri);
String idpBaseUrl = (String) idpMap.get("idpBaseUrl");
String idpMetaAlias = (String) idpMap.get("idpMetaAlias");
String fedletBaseUrl = getFedletBaseUrl(spEntityID, deployuri);
String logOutUrl=idpBaseUrl + "/IDPSloInit?metaAlias=" + idpMetaAlias + "&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect&RelayState=" + fedletBaseUrl + "/login/HomePageOpenAM.jsp";
%>
	
	
	
	
	<header id="header" class="navbar bg bg-black">
		<ul class="nav navbar-nav navbar-avatar pull-right">
		
			<li class="dropdown"><a
				href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <span
					class="hidden-xs-only"><small>Welcome,${fullName}</small></span> <span
					class="thumb-small avatar inline">
					
					<c:if test="${userState == 'CD202'}">
								
					<img src="images/${ceoTGImage}" alt="${fullName}" class="img-zoom img-circle">
					        	</c:if>
                            <c:if test="${userState == 'CD201'}">
					<img src="images/${ceoAPImage}" alt="${fullName}" class="img-zoom img-circle">
					        	</c:if>
       </span> <b class="caret hidden-xs-only"></b>
			</a>
			
				<ul class="dropdown-menu pull-right">
					
					<li><a href="#changeTheme" data-toggle="modal" title="Change Theme"><span class="glyphicon glyphicon-list-alt"></span> Change Theme</a></li>
<!--  <li><a href="#changePassword" data-toggle="modal" title="Change Password"><span class="glyphicon glyphicon-cog"></span> Change Password</a></li>-->

					<li>									<a href=<%=logOutUrl%> onclick="return confirm('Do you want to logout?')">
					<span class="glyphicon glyphicon-off"></span>
										Logout
									</a></li>
				</ul></li>
				
		</ul>
			<a class="navbar-brand"
			href="#">Employees Health Scheme</a>
		
		<button type="button" class="btn btn-link pull-left nav-toggle visible-xs" data-toggle="class:slide-nav slide-nav-right" data-target="body"> 
		<i class="fa glyphicon glyphicon-align-justify fa-lg text-default"></i> </button>
		
		<ul class="nav navbar-nav hidden-xs">
		
			
		
			<li class="dropdown shift" data-toggle="shift:appendTo"
				data-target=".nav-primary .nav"><a
				href="#"
				class="dropdown-toggle" data-toggle="dropdown"><i
					class="fa glyphicon glyphicon-cog fa-lg visible-xs visible-xs-inline"></i>Settings <b
					class="caret hidden-sm-only"></b></a>
				<ul class="dropdown-menu">
					<li><a
						href="#"
						data-toggle="class:bg bg-black" data-target=".navbar">Navbar <span
							class="text-active">inverse</span> <span class="text">white</span>
					</a></li>
					<li><a
						href="#"
						data-toggle="class:bg-light" data-target="#nav">Nav <span
							class="text-active">inverse</span> <span class="text">light</span>
					</a></li>
				</ul></li>
		</ul>
	
	</header>
	<!-- / header -->
	<!-- nav -->
	<br/>
<nav id="nav" class="nav-primary hidden-xs nav-vertical">
		<ul class="nav affix-top" data-spy="affix" data-offset-top="50">
	<li><a href="javascript:fn_dashboard();" ><i class="glyphicon glyphicon-home"></i><span></span></a></li> 
  <!--	<li><a href="javascript:fn_dashboard()"><i
					class="glyphicon glyphicon-dashboard"></i><span>Dashboard</span></a></li>-->
				 	<!--<li><a href="javascript:fn_dashboard()"><i
					class="glyphicon glyphicon-dashboard"></i><span>Dashboard</span></a></li>-->
					<li><a href="javascript:fn_sancWorkFlow()" title="Accounts">
					<i class="glyphicon glyphicon-signal"></i><span>Accounts</span>
							</a> </li>
					<li><a href="javascript:fn_operations()" title="Operations"><i
							class="fa glyphicon glyphicon-gift"></i>Operations</a></li>

				<li><a title="CMS"
							href="javascript:fn_PendingAprvl()"><i class="fa glyphicon glyphicon-briefcase"></i>CMS
							</a></li>
				<li><a title="EDC"
							href="javascript:fn_ceoEDCWorkList()"><i
							class="fa glyphicon glyphicon-pushpin"></i>EDC		
							</a> </li>
							<li><a href="javascript:fn_ceoWorkList()" title="Empanelment"><i class="fa glyphicon glyphicon-hdd"></i>Empanelment
							</a> </li>
							<li><a href="javascript:fn_othrStateCeoWorkList()"><i class="fa glyphicon glyphicon-th-large" title="Other State Empanelment"></i>Other State<br>Empanelments
							</a> </li>
				

		</ul>
	</nav>

<!-- Modal for change theme  --> 
<div class="modal fade" id="changeTheme"> 
  <div class="modal-dialog"> 
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Change Theme</h2>
      </div>
      <div class="modal-body">
        <ul class="themes">
        <li class="theme1"><a href="#" onclick="fn_changeTheme('default');">default</a></li>
        <li class="theme2"><a href="#" onclick="fn_changeTheme('navyblue');">blue</a></li>
        <li class="theme3"><a href="#" onclick="fn_changeTheme('darkgreen');">green</a></li></ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->
<!-- Modal for change password  -->
<div class="modal fade" id="changePassword"> 
  <div class="modal-dialog">
    <div class="modal-content" id="changePasswordcontent">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Change Password</h2>
      </div>
      <div class="modal-body" id="changePasswordbody">
        	<iframe id="changePasswordDiv" scrolling="no" width="110%" height="430px" frameborder="0" ></iframe>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog --> 
</div><!-- /.modal -->
<!-- Progress Bar -->
<div id="processImagetable" style="top:50%;width:100%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:400" >
   <tr>
      <td>
        <div id="processImage" align="center">
          <img src="images/Progress.gif" width="100"
                  height="100" border="0"></img>
         </div>
       </td>
     </tr>
  </table>
</div>
<section>

<div>
  <iframe id="middleFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0" onload="javascript:fn_removeLoadingImage();"></iframe>
</div>
</section>

</html:form>

<% } else
        {  %>      
		<script type="text/javascript" language="javascript">	
	parent.location.href='/Accounts/loginAction.do?actionFlag=logout';	
</script>	
        <% } %>  
</body>
	<!-- / footer -->
	<!-- Bootstrap -->
	<!-- Sparkline Chart -->
	<!-- Easy Pie Chart -->
	<!-- app -->
<script src="bootstrap/js/app.v2.js"></script>
	<script src="bootstrap/js/fuelux.js"></script>
	<!-- fullcalendar -->
	<script
		src="bootstrap/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script
		src="bootstrap/js/jquery.ui.touch-punch.min.js"></script>



</html>