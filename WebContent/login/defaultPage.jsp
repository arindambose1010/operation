<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> 
	<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <title>Home Page</title> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
 <%@ include file="/common/include.jsp"%>

    <link href="css/themes/<%=themeColour%>/verticalMenu.css" rel="stylesheet" type="text/css" media="screen">
    <script>

function fn_fillContent(){
	
	
	
}

</script>
</head>



<body>
<table style="width: 100%">
<tr><td valign="top" >
<div  id='cssmenu'  >
	<ul id="nav" >
		<li class='active' >
<a href="#"  > Home</a>
</li>	
			
<c:forEach items="${mainMenu}" varStatus="loop">
			
<c:set var="tmpModVO" value="${mainMenu[loop.index]}" />
<li id="${tmpModVO.ID}" >
<a href="javascript:fn_fillContent('${tmpModVO.ID}')"   > ${tmpModVO.VALUE}</a>
</li>
</c:forEach>
</ul>
</div>
</td>

<td style="width: 100%;" valign="top"  id="content" >&nbsp;
<img  src="images/defaultMenu/inner_middle_banner.gif" /><br>
<%-- <c:forEach items="${mainMenu}" varStatus="loop1">
<c:set var="tmpModVO" value="${mainMenu[loop1.index]}" />
<c:set var="tmpModVO1" value="test" />
<span id="${tmpModVO.ID}"  >
<bean:message key="label.${tmpModVO1}" />
</span>
</c:forEach> --%>
</td>
<td style="float: right;">
<div id='cssmenu'>
	
	
	<ul>
	<li>
	<img  src="images/defaultMenu/anuIssue.jpg" /><br>
	<br>
	</li>
	<li>
	<img  src="images/defaultMenu/claims.jpg" /><br><br>
	</li>
	<li>
	<img  src="images/defaultMenu/help.jpg" /><br><br>
	</li>
	<li>
	<img  src="images/defaultMenu/patientRegistration.jpg" /><br><br>
	</li>
	<li>
	<img  src="images/defaultMenu/postYourComplaint.jpg" /><br><br>
	</li>
	<li>
	<img  src="images/defaultMenu/preauthorization.jpg" /><br><br>
	</li>
	<li>
	<img  src="images/defaultMenu/uploadProfile.jpg" /><br>
	</li>
	
</ul>
</div>
</td>

</tr>
</table>
</body>
</html>