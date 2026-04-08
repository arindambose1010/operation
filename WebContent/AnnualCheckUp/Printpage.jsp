<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@include file="/common/include.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>AHC Print Page</title>
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
<script>

var casesSearchFlag='${casesSearchFlag}';
function loadPrint(){
	var url='/<%=context%>/annualCheckUpAction.do?actionFlag=printAhcForm&casesSearchFlag=${casesSearchFlag}&ahcId='+'${ahcId}'; 
	document.forms[0].action=url;   
	document.forms[0].target="bottomFrame";
	document.forms[0].submit();
}
function fn_attachments()
{
	var url="/Operations/ahcClaimsAction.do?actionFlag=viewAhcAttachments&casesSearchFlag=${casesSearchFlag}&ahcId=${ahcId}";      
	
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();	
}
function fn_claim()
{
	var url="/Operations/ahcClaimsAction.do?actionFlag=claimsPage&casesSearchFlag=${casesSearchFlag}&ahcId=${ahcId}";	 
	document.forms[0].action=url;   
	document.forms[0].target="bottomFrame";
	document.forms[0].submit(); 
}
</script>
</head>
<body onload="javascript:loadPrint();">
<html:form action="/annualCheckUpAction.do" method="post" target="bottomFrame">

</html:form>
</body>
</html>