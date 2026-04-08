<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/common/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Patient Common Details</title>
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="/<%=context%>/js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>
<style>
	.butCms{
	border:1px solid #dfdfdf; -webkit-border-radius: 5px; -moz-border-radius: 5px;border-radius: 5px;font-weight:bold;font-family:arial, helvetica, sans-serif; padding: 3px 15px;  text-align: center; color: #D65F26; background-color: #FBFBFB;
	 background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FBFBFB), color-stop(100%, #EBE9E2));
	 background-image: -webkit-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: -moz-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: -ms-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: -o-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: linear-gradient(top, #FBFBFB, #EBE9E2);filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#FBFBFB, endColorstr=#EBE9E2);
	}
</style>
<c:if test="${resMsg != null }">
<script>
jqueryInfoMsg('Result','${resMsg}');
</script>
</c:if>
</head>
<body>
<form:form  method="post" enctype="multipart/form-data" id="form1">
<table width="100%">
<tr class="tbheader"><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Patient Details</strong></td></tr>
</table>
<table width="100%">
<tr><td width="100%" valign="top">
 <table width="83%" border="0" align="left" cellpadding="0" cellspacing="4">
 
 <tr><td width="10%" class="labelheading1 tbcellCss" ><b>Name  </b></td><td width="20%" class="tbcellBorder" colspan="3">${patCommonDtls.PATIENTNAME} ,    ${patCommonDtls.GENDER} ,    ${patCommonDtls.AGE}</td>
 	<c:if test="${patCommonDtls.telephonicId != null && patCommonDtls.telephonicId !='' }">
 <td width="20%" class="labelheading1 tbcellCss"><b>Telephonic ID </b></td>
 <td class="tbcellBorder"><a href="javascript:viewTelephonicInfo('${patCommonDtls.telephonicId}')">${patCommonDtls.telephonicId}</a>  <!--<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>--> </td>
 </c:if>
 <c:if test="${patCommonDtls.telephonicId == null || patCommonDtls.telephonicId =='' }">	
</c:if>
 </tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Card No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CARDNO}</td><td width="10%" rowspan="1" class="labelheading1 tbcellCss"><b>Case Status </b></td><td width="34%" rowspan="1" class="tbcellBorder"> <b>${patCommonDtls.STATUS}</b></td><td width="8%" class="labelheading1 tbcellCss"><b>District&nbsp;:</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.DISTRICT}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Case No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CASENO}</td><td width="10%" class="labelheading1 tbcellCss"><b>Slab Type </b></td><td width="34%" class="tbcellBorder"> ${patCommonDtls.slabType}</td><td width="8%" class="labelheading1 tbcellCss"><b>Mandal</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.MANDAL}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>IP No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.IPNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>NWH Name </b></td><td width="34%" class="tbcellBorder">${patCommonDtls.HOSPNAME}</td><td width="8%" class="labelheading1 tbcellCss"><b>Village</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.VILLAGE}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Claim No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CLAIMNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>IP Reg Date </b></td><td width="34%" class="tbcellBorder">${patCommonDtls.date}</td><td width="8%" class="labelheading1 tbcellCss"><b>Contact No</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.CONTACT}</td></tr>
 <!-- <tr><td class="labelheading1 tbcellCss"><b>Case Status &nbsp;:</b></td><td colspan="2" class="tbcellBorder"><b>${patCommonDtls.STATUS}</b></td>
 <td></td><td></td><td colspan="1" align="center">
					<c:if test="${hideBackButton != 'Y'}">
					<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>
					</c:if>
					&nbsp;
				</td></tr>-->
 
  
 </table>
 <fieldset style="border:0; margin-top:2px">
 <table width="17%" border="0" align="left">
 <tr height="20"><td>&nbsp;</td></tr>
 <tr><td width="100%" >
 <c:choose>
 <c:when test="${patOnBedPic != null && patOnBedPic != 'N'}" >
 <img src="Common/showDocument.jsp" width="110" height="90" alt="NO DATA"  id="patImage" 
  onmouseover="javascript:resizePatImage('patImage','140','110')" onmouseout="resizePatImage('patImage','110','90')" />
 </c:when>
  <c:otherwise>
 
<img src="images/photonot.gif" width="110" height="90" alt="NO DATA" 
/>
  <%-- <form:input  path="fileData" type="file" cssStyle="width:120px" id="Attachment" size='2'  />
  <button class="but"   type="button" onclick="javascript:Validate('Attachment')" value="Upload" > Uplaod </button> --%>   
  
  </c:otherwise>
 </c:choose>
 </td></tr>
 <tr><td>

 </td></tr>
 </table>
 </fieldset>
 </td></tr>
</table>
</form:form>
</body>
</html>


