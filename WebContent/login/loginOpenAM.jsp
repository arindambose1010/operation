<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%> 
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Employees Health Scheme </title>
<link rel="shortcut icon" href="/<%=context%>/images/favicon.ico" >
<link rel="icon" type="/<%=context%>/images/ico" href="/<%=context%>/images/favicon.ico">
<link rel="stylesheet" href="css/login.css" type="text/css" media="screen" />
<script type="text/javascript" >
function fn_validateUser()
{
	var loginName = document.loginForm.username.value;
	var password = document.loginForm.password.value;
	if(loginName == "")
	 {
	  alert("Please Enter User Name");
	  document.loginForm.username.focus();
	 return false;
	 }
	if(password == "")
	 {	
	  alert("Please Enter Password");
	  document.loginForm.password.focus();
	 return;
	 }
	document.forms[0].username.focus();
	//var url='/Operations/loginAction.do?actionFlag=checkLoginOnload';  
	//window.open(url,'EmployeeHealthCareFund','toolbar=no,resizable=no,scrollbars=no,menubar=no,location=no,height=0,width=0,left=200,top=130');
	document.forms[0].action = "/<%=context%>/loginAction.do?actionFlag=checkLogin";  
    document.forms[0].submit();
     
  
}
function capLock(e){
	 kc = e.keyCode?e.keyCode:e.which;
	 sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);
	 if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk))
		 document.getElementById('caps_lock').style.display = 'block';

	 else
		 document.getElementById('caps_lock').style.display = 'none';

	}
function fnValidate(event)
{
	capLock(event);
	//if (event.which == 9 || event.keyCode == 9) { 
		
		//fn_validateUser();
		 //       }
  //var keyCode = document.layers ? event.which : event.keyCode;  
	if (event.which == 13 || event.keyCode == 13) { 
	fn_validateUser();
	        }
 
}
function fn_onloadFocus()
{
document.getElementById("username").focus();
}	
function fn_resetUser(){
document.loginForm.username.value="";
document.loginForm.password.value="";

}
function fn_resetUser1(event){
if (event.which == 13 || event.keyCode == 13) { 
document.loginForm.username.value="";
document.loginForm.password.value="";
}
}	
	function loginOnLoadOpenAm()
{
	var userName='<%=request.getParameter("userName")%>';
	var deployUri='<%=request.getParameter("deployUri")%>';
	
	var entityID='<%=request.getParameter("entityID")%>';
	var spEntityID='<%=request.getParameter("spEntityID")%>';
	var nameId='<%=request.getParameter("nameId")%>';
	var nameValue='<%=request.getParameter("nameValue")%>';
	var sessionIndex='<%=request.getParameter("sessionIndex")%>';
	
	//document.forms[0].username.focus();
	document.loginForm.entityID.value=entityID;
	document.loginForm.spEntityID.value=spEntityID;
	document.loginForm.nameId.value=nameId;
	document.loginForm.nameValue.value=nameValue;
	document.loginForm.sessionIndex.value=sessionIndex;
	document.loginForm.username.value=userName;
	document.forms[0].action = "/Operations/loginAction.do?actionFlag=checkLogin&deployUri="+deployUri;  
    document.forms[0].submit();
}
</script>
</head>
<body onload="loginOnLoadOpenAm()" style="vertical-align: top;" >
<html:form  method="post"  action="/loginAction.do" > 
    <html:hidden property="username" name="loginForm"/>
    <input type="hidden" name="entityID" id="entityID"/>
	<input type="hidden" name="spEntityID" id="spEntityID"/>
	<input type="hidden" name="nameId" id="nameId"/>
	<input type="hidden" name="nameValue" id="nameValue"/>
	<input type="hidden" name="sessionIndex" id="sessionIndex"/>
	<html:hidden name="loginForm" property="meeLogin"  value="${meeLogin}" />
	<html:hidden name="loginForm" property="meePassword"  value="${meePwd}" />
	<html:hidden name="loginForm" property="meeReqId"  value="${meeReqId}" />
	</html:form>
	
</body>
</html>