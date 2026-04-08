<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    <%@ include file="/common/include.jsp"%>
<%
int liTabIndex = 0;
%>
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<title >Telephonic Registration</title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%>
<script>

 (function($){	   
	$(window).load(function(){
		$(".commdetails").mCustomScrollbar({
			theme:"dark-thin"
			});	
	});	
})(jQuery);
</script>
</head>
<body>
<div id="middleFrame_content">
<form action="/preauthDetails.do" method="post">
<table width="95%" style="margin:0 auto" border="1"><tr><td>
<br>
<table class="tbheader">
<tr><th><b><fmt:message key="EHF.Title.TelephonicPatientRegistration"/></b></th></tr>
</table>
<table width="75%">
<tr>
<td  class="labelheading1 tbcellCss" width="20%"><b><fmt:message key="EHF.HealthCardNo"/></b></td>
<td  class="tbcellBorder" width="20%">&nbsp;<b>${telDtls.cardNo}</b></td>
<td width="20%">&nbsp;</td>
<td  class="labelheading1 tbcellCss" width="20%"><b>Telephonic ID</b></td>
<td  class="tbcellBorder" width="20%">&nbsp;<b>${telDtls.telephonicId}</b></td>
</tr>
</table>
<br>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>
<table width="100%">
<tr><td width="30%" valign="top">
<fieldset style="height:18em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="height:17em;overflow:hidden" class="commdetails">
<table width="100%" height="180px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Name"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.firstName}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.gender}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.dateOfBirth}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.age} Y
 		${telDtls.ageMonths} M
		${telDtls.ageDays} D</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.relOthers}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.occOthers}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.telePhoneNo}</b></td>
</tr>
</table>
</fieldset>
</td>
<td width="35%" valign="top">
<fieldset style="height:18em;">
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
<div style="height:17em;overflow:hidden" class="commdetails">
<table width="100%" height="180px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.addr1}</b></td> 
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.addr2}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td>
	<td class="tbcellBorder"><b>&nbsp;${state}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${district}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${mandal}</b></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${village}</b></td>
</tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td>
	<td class="tbcellBorder"><b>&nbsp;${telDtls.pincode}</b></td>
</tr>
</table>
</fieldset>
</td>
<td width="35%" valign="top">
<fieldset style="height:18em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
<div style="height:17em;overflow:hidden" class="commdetails">
<table width="100%" height="180px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder"><b>&nbsp;${telDtls.permAddr1}</b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;${telDtls.permAddr2}</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;${cState}</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;${cDist}</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;${cMandal}</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;${cVillage}</b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;${telDtls.c_pin_code}</b></td></tr>
</table>
</fieldset>
</td>
</tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.CallerDetails"/></b></td></tr>
</table>
<table class="contentTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Hospital"/> </td><td width="25%" class="tbcellBorder"> &nbsp;${telDtls.regHospDt}</td>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Designation"/> </td><td width="25%" class="tbcellBorder"> &nbsp;${telDtls.teleCallerDesgn}</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.CallerName"/></td><td class="tbcellBorder"> &nbsp;${telDtls.teleCallerName}</td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.PhoneNo"/></td><td class="tbcellBorder"> &nbsp;${telDtls.telePhoneNo}</td>
</tr>
</table>
<fieldset>
 <legend class="legendStyle" style="font-size:1.2em"><b><fmt:message key="EHF.Diagnosis"/></b></legend>
<table width="100%" class="contentTable">
<tr>
<td  width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.DiagType"/></td><td  width="25%" class="tbcellBorder"> &nbsp;${telDtls.diagnosisType}</td>
<td width="25%"  class="labelheading1 tbcellCss"><fmt:message key="EHF.MainCatName"/></td><td width="25%"  class="tbcellBorder"> &nbsp;${telDtls.mainCatName}</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.CatName"/></td><td class="tbcellBorder"> &nbsp;${telDtls.catName}</td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.SubCatName"/></td><td class="tbcellBorder"> &nbsp;${telDtls.subCatName}</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.DiseaseName"/></td><td class="tbcellBorder"> &nbsp;${telDtls.diseaseName}</td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.DisAnatomicalName"/></td><td class="tbcellBorder"> &nbsp;${telDtls.disAnatomicalName}</td>
</tr>
</table>
</fieldset>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.ProvAppDetails"/></b></td></tr>
</table>
<table class="contentTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.CatName"/></td><td width="25%" class="tbcellBorder"> &nbsp;${telDtls.asriCatCode}</td>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.ICDCatName"/></td><td width="25%" class="tbcellBorder"> &nbsp;${telDtls.ICDcatName}</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.ICDProc"/></td><td class="tbcellBorder">&nbsp;${telDtls.ICDprocName}</td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.Indication"/></td><td class="tbcellBorder"> &nbsp;${telDtls.indication}</td>
</tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.ApprovalBy"/></b></td></tr>
</table>
<table class="contentTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.DocName"/> </td><td width="25%" class="tbcellBorder"> &nbsp;${telDtls.teleDocName}</td>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Designation"/> </td><td width="25%" class="tbcellBorder"> &nbsp;${telDtls.teleDocDesgn}</td></tr>

<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.PhoneNo"/></td><td class="tbcellBorder"> &nbsp;${telDtls.teleDocPhoneNo}</td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.DateOfInt"/></td><td class="tbcellBorder"> &nbsp;${telDtls.crtDt}</td></tr>

<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.Remarks"/></td><td colspan="3" class="tbcellBorder"> &nbsp;This is a Provisonal Approval given for  ${RemarksProcedure} 
therapy(ies) for ${RemarksDiagnosis} based on the indication stated by the treating doctor over phone.The Preauthorization has to be raised within 72 Hrs with complete clinical 
and documentary evidence from this telephonic intimation ID only.</td>
</tr>
</table>
<table width="100%">
<tr>

<td colspan="5" align="center" > <button class="but"  type="button" id="close" onclick="window.close()">Close</button></td>

</tr>
</table>
</td></tr></table>
</form>
</div>
</body>
</html>
</fmt:bundle>




