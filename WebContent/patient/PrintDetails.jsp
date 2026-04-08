<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
  	 <%@ include file="/common/include.jsp"%>
  	 
  	 <fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
 <script language="javascript" type="text/javascript">	
 var s$=jQuery.noConflict();
 
 window.onload = function() {
     var cardType = document.getElementById('card_type').value;
     //alert("cardType::"+cardType);
    /*  if (cardType === "DJ") {
         document.getElementById("djSection").style.display = "table-row-group"; 
     }  */
 };

 s$(function(){
    /*
     * this swallows backspace keys on any non-input element.
     * stops backspace -> back
     */
    var rx = /INPUT|SELECT|TEXTAREA/i;

    s$(document).bind("keydown keypress", function(e){
        if( e.which == 8 ){ // 8 == backspace
            if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
                e.preventDefault();
            }
        }
    });
 });
 
 
function fn_Print()
{
window.print();
}
/* function closeNrefresh()
{
	window.opener.refreshParentPage();
	window.close();
} */
function closeNrefresh() {
    var cardType = document.getElementById('card_type').value;

    if (cardType === "DJ" || cardType === "DAB") {
        if (window.opener) {
            window.opener.location.href = '/<%=context%>/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=N';
        }
        window.close(); 
    } else {
        
        if (window.opener && typeof window.opener.refreshParentPage === "function") {
            window.opener.refreshParentPage();
        }
        window.close();
    }
}
//pravalika
/* CR 9043 onclick for view document */
function openFile(fileName) {
    window.open(
        '/<%=context%>/patientDetails.do?actionFlag=viewDlhJrnlstDoc&fileName=' + fileName,
        '_blank'
    );
}
/* end */

</script>
</head>
<body>
<form action="/patientDetails.do" method="post" name="printForm">
<table width="95%" style="margin:0 auto" class="tb print_table">
<tr>
<logic:notEqual name="patientForm" property="patientScheme" value="CD502">
<logic:notEqual name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/Printheader.html" %>
</td>
</logic:notEqual>
<logic:equal name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/Printheader_tg.html" %>
</td>
</logic:equal>
</logic:notEqual>
<logic:equal name="patientForm" property="patientScheme" value="CD502">
<logic:notEqual name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/PrintHeaderJouAP.html" %>
</td>
</logic:notEqual>
<logic:equal name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/PrintHeaderJouTG.html" %>
</td>
</logic:equal>
</logic:equal>
</tr>
<tr><th class="tbheader">PATIENT REGISTRATION FORM</th></tr>
<tr><td style="text-align:center;border-bottom:1px solid #c5f7de;"><strong><fmt:message key="EHF.PatientNo"/> : <bean:write name="patientForm" property="patientNo"/></strong></td></tr>
<!--  personal details -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>PERSONAL DETAILS</b></td></tr>
<tr><td>
		 <table width="100%" cellspacing="2px" cellpadding="2px" class="tb">
			<tr height="45px"><td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.HealthCardNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="cardNo"/></td>
		<%-- <td width="25%"><b><fmt:message key="EHF.CardIssueDate"/>:&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td> --%>
				<td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Name"/>:</b></span>&nbsp;<bean:write name="patientForm" property="fname" /></td>
				<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Gender"/>:</b></span>&nbsp;<bean:write name="patientForm" property="gender"/></td>
				<td width="25%" rowspan="3" style="text-align:center" class="tbcellCss">
				 <logic:notEmpty name="patientForm" property="photoUrl">
				<img src="common/showDocument.jsp" width="150" height="120" alt="NO DATA"/>
				</logic:notEmpty>
				<logic:empty name="patientForm" property="photoUrl">
				<img src="images/photonot.gif" width="150" height="120" alt="NO DATA"/>
				</logic:empty>
			</td>
		</tr>
		<tr height="45px">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Age"/>:</b></span>&nbsp;<bean:write name="patientForm" property="years"/>Y
		 		<bean:write name="patientForm" property="months"/>M
				<bean:write name="patientForm" property="days"/>D</td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Relationship"/>:</b></span>&nbsp;<c:if test="${patientForm.relation eq 'New Born Baby'}"  ><b></c:if><bean:write name="patientForm" property="relation" /></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Slab"/>:</b></span>&nbsp;<bean:write name="patientForm" property="slab"/></td>
		</tr>
		<tr height="45px">
		<!-- <td class="tbcellCss"><b><fmt:message key="EHF.Caste"/>:</b>&nbsp;<bean:write name="patientForm" property="caste"/></td> -->
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Designation"/>:</b></span>&nbsp;<bean:write name="patientForm" property="occupation"/></td>
			<td colspan="2" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.ContactNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="contactno"/></td>
		</tr>
		</table>
</td></tr>
<!--  end of personal details -->
<!--  card address -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>CARD ADDRESS</b></td></tr>
<tr><td>
	<table width="100%" height="80px" class="tb">
	<tr>
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.HouseNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="hno"/></td> 
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Street"/>:</b></span>&nbsp;<bean:write name="patientForm" property="street"/></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.District"/>:</b></span>&nbsp;<bean:write name="patientForm" property="district" /></td>
	</tr>
	<tr>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Mdl/Mcl"/>:</b></span>&nbsp;<bean:write name="patientForm" property="mandal" /></td>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Village"/>:</b></span>&nbsp;<bean:write name="patientForm" property="village" /></td> 
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Pin"/>:</b></span>&nbsp;<bean:write name="patientForm" property="pin" /></td>
	</tr>
	</table>
</td></tr>
<!--  end of card address -->
<!-- communication address -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>COMMUNICATION ADDRESS</b></td></tr>
<tr><td>
	<table width="100%" height="80px" class="tb">
	<tr>
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.HouseNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_hno" /></td> 
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Street"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_street" /></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.District"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_dist" /></td>
	</tr>
	<tr>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Mdl/Mcl"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></td>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Village"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_village" /></td> 
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Pin"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_pin" /></td>
	</tr>
	</table>
</td></tr>
<!-- end of communication address -->
<!--registered hospital details  -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>REGISTERED HOSPITAL DETAILS</b></td></tr>
<tr><td>
	<table width="100%"  height="40px" class="tb">
	<tr>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.RegisteredHospital"/>:</b></span>&nbsp;<bean:write name="patientForm" property="hospitalName"/></td>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.DateAndTime"/>:</b></span>&nbsp;<bean:write name="patientForm" property="dtTime"/></td>
	</tr>
	</table>
</td></tr>
<!--  end of registered hospital details -->
<!--pravalika  registered Category details  -->
<c:if test="${patientForm.card_type eq 'DJ'}">
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>REGISTERED Category DETAILS</b></td></tr>
<tr><td>
	<table width="100%"  height="40px" class="tb">
	<tr>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Category"/>:</b></span>&nbsp;<bean:write name="patientForm" property="diagnosisCategory"/></td>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Diagnosis"/>:</b></span>&nbsp;<bean:write name="patientForm" property="dialysis"/></td>
	</tr>
	</table>
</td></tr>

<!-- CR 9043 Displaying Admission Details in Print form -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>REGISTERED ADMISSION DETAILS</b></td></tr>
<tr><td>
	<table width="100%"  height="40px" class="tb">
	
	<tr>
	<td width="20%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.AdmissionDate"/>:</b></span>&nbsp;<bean:write name="patientForm" property="admissionDate"/></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.AdmissionNote"/>:</b></span>&nbsp;<bean:write name="patientForm" property="admissionNote"/></td>
	<td width="20%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.EstimatedAmt"/>:</b></span>&nbsp;<bean:write name="patientForm" property="age"/></td>
	<%-- <td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Attachments"/>:</b></span>&nbsp;<bean:write name="patientForm" property="fileName"/></td> --%>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Attachments"/>:</b></span>&nbsp;
	<a href="#" onclick="openFile('<bean:write name="patientForm" property="fileName"/>'); return false;">
   		<bean:write name="patientForm" property="fileName"/>
	</a>
	</td>
	</tr>

	</table>
</td></tr>
<!--CR 9043 end -->
</c:if>
<tr class="print_buttons">
<td style="text-align:center">
<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
</tr>
</table>
<input type="hidden" value='<bean:write name="patientForm" property="card_type"/>' id="card_type"/>
</form>
</body>
</html>
</fmt:bundle>