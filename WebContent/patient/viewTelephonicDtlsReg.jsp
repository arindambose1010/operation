<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
   
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
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script>
/* var s$=jQuery.noConflict();

s$(function(){ */
   /*
    * this swallows backspace keys on any non-input element.
    * stops backspace -> back
    */
   /* var rx = /INPUT|SELECT|TEXTAREA/i;

   s$(document).bind("keydown keypress", function(e){
       if( e.which == 8 ){ // 8 == backspace
           if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
               e.preventDefault();
           }
       }
   });
});
 */
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
<form action="/patientDetails.do" method="post" name="InPatientForm">
<table width="95%" style="margin:0 auto" border="1"><tr><td>
<br>
<table class="tbheader">
<tr><th><b><fmt:message key="EHF.Title.TelephonicPatientRegistration"/></b></th></tr>
</table>
<table width="75%">
<tr>
<td  class="labelheading1 tbcellCss" width="20%"><b><fmt:message key="EHF.HealthCardNo"/></b></td>
<td  class="tbcellBorder" width="20%">&nbsp;<b><bean:write name="patientForm" property="cardNo"/></b></td>
<td width="20%">&nbsp;</td>
<td  class="labelheading1 tbcellCss" width="20%"><b>Telephonic ID</b></td>
<td  class="tbcellBorder" width="20%">&nbsp;<b><bean:write name="patientForm" property="telephonicId"/></b></td>
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
<%-- <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td></tr> --%> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="dateOfBirth"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="years"/>Y
 		<bean:write name="patientForm" property="months"/>M
		<bean:write name="patientForm" property="days"/>D</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="relation"/></b></td></tr>
<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="caste"/></b></td></tr> --%>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="occupation"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="contactno"/></b></td></tr>
</table>
</fieldset>
</td>
<td width="35%" valign="top">
<fieldset style="height:18em;">
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
<div style="height:17em;overflow:hidden" class="commdetails">
<table width="100%" height="180px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="pin" /></b></td></tr>
</table>
</fieldset>
</td>
<td width="35%" valign="top">
<fieldset style="height:18em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
<div style="height:17em;overflow:hidden" class="commdetails">
<table width="100%" height="180px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_hno" /></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_street" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_dist" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_pin" /></b></td></tr>
<!-- <tr><td colspan="2" class="labelheading1 tbcellCss">&nbsp;</td></tr>
<tr><td colspan="2" class="labelheading1 tbcellCss">&nbsp;</td></tr> -->
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
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Hospital"/> </td><td width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="hospitalName" /></td>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Designation"/> </td><td width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="teleCallerDesgn" /></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.CallerName"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="teleCallerName" /></td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.PhoneNo"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="telePhoneNo" /></td>
</tr>
</table>
<fieldset>
 <legend class="legendStyle" style="font-size:1.2em"><b><fmt:message key="EHF.Diagnosis"/></b></legend>
<table width="100%" class="contentTable">
<tr>
<td  width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.DiagType"/></td><td  width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="diagType"/></td>
<td width="25%"  class="labelheading1 tbcellCss"><fmt:message key="EHF.MainCatName"/></td><td width="25%"  class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="mainCatName"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.CatName"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="catName"/></td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.SubCatName"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="subCatName"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.DiseaseName"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="diseaseName"/></td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.DisAnatomicalName"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="disAnatomicalName"/></td>
</tr>
</table>
</fieldset>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.ProvAppDetails"/></b></td></tr>
</table>
<table class="contentTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.CatName"/></td><td width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="asriCatName" /></td>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.ICDCatName"/></td><td width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="ICDCatName" /></td>
<%-- <td style="width:50%;padding:3px;"><b><fmt:message key="EHF.ICDSubCatName"/></b> <font color="red">*</font>: &nbsp;<bean:write name="patientForm" property="ICDSubCatName" /></td> --%>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.ICDProc"/></td><td class="tbcellBorder">&nbsp;<bean:write name="patientForm" property="ICDProcName" /></td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.Indication"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="indication"/></td>
</tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.ApprovalBy"/></b></td></tr>
</table>
<table class="contentTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.DocName"/> </td><td width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="teleDocName"/></td>
<td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Designation"/> </td><td width="25%" class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="teleDocDesgn"/></td></tr>

<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.PhoneNo"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="teleDocPhoneNo"/></td>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.DateOfInt"/></td><td class="tbcellBorder"> &nbsp;<bean:write name="patientForm" property="dtTime"/></td></tr>

<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.Remarks"/></td><td colspan="3" class="tbcellBorder"> &nbsp;This is a Provisonal Approval given for  <bean:write name="patientForm"  property="remarksProcedure"/> 
therapy(ies) for <bean:write name="patientForm"  property="remarksDiagnosis"/> based on the indication stated by the treating doctor over phone.The Preauthorization has to be raised within 72 Hrs with complete clinical 
and documentary evidence from this telephonic intimation ID only.</td>
</tr>
</table>
<table width="100%">
<tr>

<td colspan="5" align="center" > <button class="but"  type="button" id="close" onclick="window.close()">Close</button></td>

</tr>
</table>
</td></tr></table>
<html:hidden name="patientForm" property="disableFlag" styleId="disableFlag"/>
<html:hidden name="patientForm" property="errMsg" styleId="errMsg"/>
<html:hidden name="patientForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="patientForm" property="ageString" styleId="ageString"/>
<html:hidden  name="patientForm" property="head" styleId="head"/>
</form>
<script>
//on_load();
</script>
</div>
</body>
</html>
</fmt:bundle>




