<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	<%@ include file="/common/include.jsp"%>
	<fmt:setLocale  value='en_US' />
<fmt:bundle basename="Registration">
<html>
<head>
<script type="text/javascript" src="scripts/empEnrollment.js"></script>
<script>
function fn_printPage(divName){

	document.getElementById("printButton").style.display="none";
	    window.print();
	    setTimeout("showPrintButton()", 2000);
		
	}
function showPrintButton(){
	
	document.getElementById("printButton").style.display="";
	
}

</script>

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
	<script src="js/jquery-1.9.1.js"></script> 
<%@ include file="/common/includeScrollbar.jsp"%> 



<script>
		(function($){

			$(window).load(function(){
				$("body").mCustomScrollbar({
					theme:"dark"
					});
				
				$("body").hover(function(){
					$(document).data({"keyboard-input":"enabled"});
					$(this).addClass("keyboard-input");
				},function(){
					$(document).data({"keyboard-input":"disabled"});
					$(this).removeClass("keyboard-input");
				});
				$(document).keydown(function(e){
					if($(this).data("keyboard-input")==="enabled"){
						var activeElem=$(".keyboard-input"),
							activeElemPos=Math.abs($(".keyboard-input .mCSB_container").position().top),
							pixelsToScroll=60;
						if(e.which===38){ //scroll up
							e.preventDefault();
							if(pixelsToScroll>activeElemPos){
								activeElem.mCustomScrollbar("scrollTo","top");
							}else{
								activeElem.mCustomScrollbar("scrollTo",(activeElemPos-pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
							}
						}else if(e.which===40){ //scroll down
							e.preventDefault();
							activeElem.mCustomScrollbar("scrollTo",(activeElemPos+pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
						}
					}
				});
			});
		})(jQuery);
		
</script>
	  <style>
html, body{
 overflow:hidden;
 height:100%;
}


	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Case Details</title>
</head>
<body>
<html:form action="/empPenCaseSearch.do" method="post" enctype="multipart/form-data">
<table  width="100%" class="tbheader" >
<tr ><td align="center" ><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>

<table width="100%">
<div id="printDiv">
<tr>

<tr><td width="30%" valign="top">
<fieldset style="height:15em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
<table>
<tr><td><fmt:message key="EHF.CardIssueDate"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="cardIssueDt"/></td></tr>
<tr><td><fmt:message key="EHF.Name"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="fname" /></td></tr>
<tr><td><fmt:message key="EHF.Gender"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="gender"/></td></tr>
<%--<tr><td><fmt:message key="EHF.DateOfBirth"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="dateOfBirth"/></td></tr> --%>
<tr><td><fmt:message key="EHF.Age"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="years"/>Y
 		<bean:write name="casesSearchForm" property="months"/>M
		<bean:write name="casesSearchForm" property="days"/>D</td></tr>
<tr><td><fmt:message key="EHF.Relationship"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="relation" /></td></tr>
 <tr><td><fmt:message key="EHF.Caste"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="caste"/></td></tr>
<tr><td><fmt:message key="EHF.Designation"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="occupation"/></td></tr>
<tr><td><fmt:message key="EHF.ContactNo"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="contactno"/></td></tr>
</table>
</fieldset>
</td>

<td width="25%" valign="top">
<fieldset style="height:15em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
<table>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td><fmt:message key="EHF.HouseNo"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="comm_hno" /></td> </tr>
<tr><td><fmt:message key="EHF.Street"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="comm_street" /></td></tr>
<tr><td><fmt:message key="EHF.District"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="comm_dist" /></td></tr>
<tr><td><fmt:message key="EHF.Mdl/Mcl"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="comm_mandal"/></td></tr>
<tr><td><fmt:message key="EHF.Village"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="comm_village" /></td></tr> 
<tr><td><fmt:message key="EHF.Pin"/></td><td>:&nbsp;<bean:write name="casesSearchForm" property="comm_pin" /></td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</fieldset>
</td>
<td width="20%" valign="top">
<fieldset style="height:15em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.Photo"/></b></legend>
<table width="80%" height="80%" style="margin:auto auto">
<tr><td align="center">
 <logic:notEmpty name="casesSearchForm" property="photoUrl">
<img src="Common/showDocument.jsp" width="150" height="150" alt="NO DATA"/>
</logic:notEmpty>
<logic:empty name="casesSearchForm" property="photoUrl">
<img src="images/photonot.gif" width="150" height="150" alt="NO DATA"/>
</logic:empty>
</td></tr></table>
</fieldset>
</td></tr>

<tr>
<table  width="100%" class="tbheader" >
<tr ><td align="center" ><b><fmt:message key="EHF.Title.CaseDetails"/></b></td></tr>
</table>
</tr>
<tr>
<table cellpadding=1 cellspacing=1 border=1 width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th><fmt:message key="EHF.CaseNo"/></th>
<th><fmt:message key="EHF.CaseType"/></th>
<th><fmt:message key="EHF.RegisteredHospital"/></th>
<th><fmt:message key="EHF.DateAndTime"/></th>
</tr>
<tr>
<td  align="center">
<bean:write name="casesSearchForm" property="caseNo"/>
</td>
<td  align="center">

<c:if test="${casesSearchForm.caseStatus eq 'CD2'}">
IP case Registered 
</c:if>

<c:if test="${casesSearchForm.caseStatus eq 'CD5'}">
OP Case Registered 
</c:if>
<c:if test="${casesSearchForm.caseStatus eq 'CD4'}">
Chronic Case Registered 
</c:if>
</td>
<td  align="center">
<bean:write name="casesSearchForm" property="hospName"/>
</td>
<td  align="center">
<bean:write name="casesSearchForm" property="SUBMITTEDDATE"/>
</td>
</tr>

</table>
</tr>
<tr>
<table  width="100%" class="tbheader" >
<tr ><td align="center" ><b><fmt:message key="EHF.SystemicExamFnds"/></b></td></tr>
</table>
</tr>
<tr>
<table cellpadding=1 cellspacing=1 border=1 width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th><fmt:message key="EHF.MainSymptomName"/></th>
<th><fmt:message key="EHF.SymptomName"/>


</tr>
<tr>

<td  align="center" >
<bean:write name="casesSearchForm" property="mainSymptom"/>
</td>
<td  align="center" >
<bean:write name="casesSearchForm" property="symptom"/>
</td>
</tr>

</table>

</tr>

<tr>
<logic:notEmpty name="casesSearchForm" property="lstCaseSearch">
<table  width="100%" class="tbheader" >
<tr ><td align="center" ><b><fmt:message key="EHF.GenInvestigations"/></b></td></tr>
</table>
</logic:notEmpty>
</tr>

<tr>

<logic:notEmpty name="casesSearchForm" property="lstCaseSearch">
<table cellpadding=1 cellspacing=1 border=1 width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th><fmt:message key="EHF.TestName"/></th>
<th><fmt:message key="EHF.Attachment"/></th>
</tr>
<logic:iterate name="casesSearchForm" property="lstCaseSearch" id="data">
<tr>

<td align="center">
<bean:write name="data" property="testName"/>
</td>
<td align="center">
<a href="javascript:fn_viewPhoto('<bean:write name="data" property="genTestPath"/>')">View Attachment</a>
</td>

</tr>
</logic:iterate>
</table>

</logic:notEmpty>

</tr>

<tr>
<table  width="100%" class="tbheader" >
<tr><td align="center" ><b><fmt:message key="EHF.Diagnosis"/></b></td></tr>
</table>
</tr>
<tr>
<table width="100%"  border=1 cellpadding=1 cellspacing=1  align="center">
<tr>

<th><fmt:message key="EHF.Diagnosis"/></th>
<th><fmt:message key="EHF.MainCatName"/></th>
<th><fmt:message key="EHF.CatName"/></th>
<th><fmt:message key="EHF.SubCatName"/></th>
<th><fmt:message key="EHF.DiseaseName"/></th>
<th><fmt:message key="EHF.DisAnatomicalName"/></th>
</tr>

<tr>

<td  align="center" ><bean:write name="casesSearchForm" property="diagnosisName"/></td>


<td  align="center" >
<bean:write name="casesSearchForm" property="mainCatName"/>
</td>


<td  align="center" >
<bean:write name="casesSearchForm" property="catName"/>
</td>

<td  align="center" >
<bean:write name="casesSearchForm" property="subCatName"/>
</td>


<td  align="center" >
<bean:write name="casesSearchForm" property="diseaseName"/>
</td>


<td  align="center" >
<bean:write name="casesSearchForm" property="disAnatomicalName"/>
</td>
</tr>

</table>
</tr>
<logic:notEmpty name="casesSearchForm" property="ipTherapyDetails">
<tr>

<table  width="100%" class="tbheader" >

<tr><td align="center" ><b><fmt:message key="EHF.Therapy"/></b></td></tr>
</table>

</tr>
</logic:notEmpty>
<tr>
<logic:notEmpty name="casesSearchForm" property="ipTherapyDetails">

<table border=1 cellpadding=1 cellspacing=1  width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th>
<fmt:message key="EHF.CatName"/>
</th>

<th><fmt:message key="EHF.ICDCatName"/></th>

<th>
<fmt:message key="EHF.ICDProc"/></th>

</tr>
<logic:iterate id="data" name="casesSearchForm" property="ipTherapyDetails">

<tr>
<td  align="center" >
<bean:write name="data" property="disMainName"/>
</td>
<td  align="center" >
<bean:write name="data" property="icdCatName"/>
</td>
<td  align="center" >
<bean:write name="data" property="procName"/>
</td>

</tr>
</logic:iterate>
</table>
</logic:notEmpty>
</tr>

<tr>
<logic:notEmpty name="casesSearchForm" property="ipTherapyInvestigationDtls">
<table  width="100%" class="tbheader" >
<tr><td align="center" ><b><fmt:message key="EHF.Investigations"/></b></td></tr>
</table>
</tr>
</logic:notEmpty>
<tr>

<logic:notEmpty name="casesSearchForm" property="ipTherapyInvestigationDtls">
<table border=1 cellpadding=1 cellspacing=1  width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th><fmt:message key="EHF.TestName"/></th>
<th><fmt:message key="EHF.Attachment"/></th>
</tr>
<logic:iterate id="data" name="casesSearchForm" property="ipTherapyInvestigationDtls">

<tr>
<td align="center">
<bean:write name="data" property="investDesc"/>
</td>
<td align="center">
<a href="javascript:fn_viewPhoto('<bean:write name="data" property="attachTotalPath"/>')">View Attachment</a>

</td>


</tr>
</logic:iterate>
</table>
</logic:notEmpty>
</tr>
<tr>
<logic:notEmpty name="casesSearchForm" property="drugsDetails">
<table  width="100%" class="tbheader" >
<tr><td align="center" ><b><fmt:message key="EHF.Prescription"/></b></td></tr>
</table>
</tr>
</logic:notEmpty>
<tr>

<logic:notEmpty name="casesSearchForm" property="drugsDetails">
<table border=1 cellpadding=1 cellspacing=1  width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th><fmt:message key="EHF.DrugTypeName"/></th>
<th><fmt:message key="EHF.DrugSubTypeName"/></th>
<th><fmt:message key="EHF.DrugName"/></th>
</tr>
<logic:iterate id="data" name="casesSearchForm" property="drugsDetails">

<tr>
<td  align="center" >
<bean:write name="data" property="drugTypeName"/>
</td>
<td  align="center" >
<bean:write name="data" property="drugSubTypeName"/>
</td>

<td  align="center" >
<bean:write name="data" property="drugName"/>
</td>

</tr>
</logic:iterate>
</table>
</logic:notEmpty>

</tr>
<tr>
<logic:notEmpty name="casesSearchForm" property="chronicOpDetails">
<table  width="100%" class="tbheader" >
<tr><td align="center" ><b><fmt:message key="EHF.ChronicOP"/></b></td></tr>
</table>
</tr>
</logic:notEmpty>
<tr>
<logic:notEmpty name="casesSearchForm" property="chronicOpDetails">
<table border=1 cellpadding=1 cellspacing=1  width="100%" style="padding-top:0px;margin:0px auto;">
<tr>
<th><fmt:message key="EHF.OpCatName"/></th>
<th><fmt:message key="EHF.OpPkgName"/></th>
<th><fmt:message key="EHF.ICDName"/></th>
</tr>

<logic:iterate id="data" name="casesSearchForm" property="chronicOpDetails">

<tr>
<td  align="center" >
<bean:write name="data" property="opDrugName"/>
</td>
<td  align="center" >
<bean:write name="data" property="icdOpPkgName"/>
</td>

<td  align="center" >
<bean:write name="data" property="icdOpCatName"/>
</td>

</tr>
</logic:iterate>
</table>
</logic:notEmpty>
</tr>
</table>
</div>

<table align="center" style="padding-top:0px;margin:0px auto;">
 <tr>
 <td colspan=2 align="center" >

  
 <button class="but" type="button" onclick="window.close();" class="ActionButton"  value="Reset"  >Close</button>
 <button  class="but" type="button"  id="printButton" class="Button" onclick="fn_printPage('printDiv');">Print</button>
 </td>

  </tr>
</table>




</html:form>
</body>
</html>
</fmt:bundle>