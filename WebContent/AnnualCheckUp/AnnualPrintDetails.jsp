<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
  	 <%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
 <script language="javascript" type="text/javascript">	
 var s$=jQuery.noConflict();

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
function closeNrefresh()
{
	window.opener.refreshParentPage();
	window.close();
}
</script>
</head>
<body>
<form action="/chronicAction.do" method="post" name="printForm">
<div class="panel panel-default">
<table width="95%" style="margin:0 auto" class="tb print_table">
<tr>
<logic:notEqual name="annualCheckUpForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/Printheader.html" %>
</td>
</logic:notEqual>
<logic:equal name="annualCheckUpForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/Printheader_tg.html" %>
</td>
</logic:equal>
</tr>
<tr><td><div class="tbheader"><b>ANNUAL HEALTH CHECKUP REGISTRATION FORM</b></div></td></tr>
<tr><td style="text-align:center;border-bottom:1px solid #c5f7de;"><strong>AHC ID : <bean:write name="annualCheckUpForm" property="patientNo"/></strong></td></tr>
<!--  personal details -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>PERSONAL DETAILS</b></td></tr>
<tr><td>
		 <table width="100%" cellspacing="2px" cellpadding="2px" class="tb">
			<tr height="45px"><td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>Health Card No:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="cardNo"/></td>
		<%-- <td width="25%"><b><fmt:message key="EHF.CardIssueDate"/>:&nbsp;<bean:write name="annualCheckUpForm" property="cardIssueDt"/></b></td> --%>
				<td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>Name:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="name" /></td>
				<td class="tbcellCss print_cell"><span class="labelheading1"><b>Gender:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="gender"/></td>
				<td width="25%" rowspan="3" style="text-align:center" class="tbcellCss">
				 <logic:notEmpty name="annualCheckUpForm" property="photoUrl">
				<img src="common/showDocument.jsp" width="150" height="120" alt="NO DATA"/>
				</logic:notEmpty>
				<logic:empty name="annualCheckUpForm" property="photoUrl">
				<img src="images/photonot.gif" width="150" height="120" alt="NO DATA"/>
				</logic:empty>
			</td>
		</tr>
		<tr height="45px">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Age:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="years"/>Y
		 		<bean:write name="annualCheckUpForm" property="months"/>M
				<bean:write name="annualCheckUpForm" property="days"/>D</td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Relationship:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="relation" /></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Slab:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="slab"/></td>
		</tr>
		<tr height="45px">
		<!-- <td class="tbcellCss"><b><fmt:message key="EHF.Caste"/>:</b>&nbsp;<bean:write name="annualCheckUpForm" property="caste"/></td> -->
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Designation:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="designation"/></td>
			<td colspan="2" class="tbcellCss print_cell"><span class="labelheading1"><b>Contact No:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="contactno"/></td>
		</tr>
		</table>
</td></tr>
<!--  end of personal details -->
<!--  card address -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>CARD ADDRESS</b></td></tr>
<tr><td>
	<table width="100%" height="80px" class="tb">
	<tr>
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b>House No:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="hno"/></td> 
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b>Street:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="street"/></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b>District:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="district" /></td>
	</tr>
	<tr>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b>Mandal/Municipality:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="mandal" /></td>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b>City/Town/Village:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="village" /></td> 
	<td class="tbcellCss print_cell"><span class="labelheading1"><b>Pin code:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="pin" /></td>
	</tr>
	</table>
</td></tr>
<!--  end of card address -->
<!-- communication address -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>COMMUNICATION ADDRESS</b></td></tr>
<tr><td>
	<table width="100%" height="80px" class="tb">
	<tr>
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b>House No:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="comm_hno" /></td> 
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b>Street:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="comm_street" /></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b>District:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="comm_dist" /></td>
	</tr>
	<tr>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b>Mandal/Municipality:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="comm_mandal"/></td>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b>City/Town/Village:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="comm_village" /></td> 
	<td class="tbcellCss print_cell"><span class="labelheading1"><b>Pin code:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="comm_pin" /></td>
	</tr>
	</table>
</td></tr>
<!-- end of communication address -->
<!--registered hospital details  -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>REGISTERED HOSPITAL DETAILS</b></td></tr>
<tr><td>

	<table width="100%"  height="40px" class="tb">
	<tr>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Hospital:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="hospitalName"/></td>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Date and Time of Registration:</b></span>&nbsp;<bean:write name="annualCheckUpForm" property="dtTime"/></td>
	</tr>
	</table>
	</td></tr>
	<!--  end of registered hospital details -->
<tr><td>	
	<table width="100%" class="tb">
	<tr>
		<td><b>Prescribed investigations:</b><br></td>
	</tr>
	<tr>	
		<td class="tbcellCss print_cell" width="33%">1.CBP(Complete Blood Picture) Test/HAEMOGRAM </td>	
		<td class="tbcellCss print_cell" width="33%">2.ESR(Erythrocyte Sedimentation Rate)</td>	
		<td class="tbcellCss print_cell" width="33%">3.Blood Sugar</td>
	</tr>
	<tr>
		<td class="tbcellCss print_cell">4.Glycosylated Haemoglobin</td>
		<td class="tbcellCss print_cell">5.HbsAg(Surface Antigen of Hepatitis B Virus)</td>
		<td class="tbcellCss print_cell">6.Lipid Profile</td>
	</tr>	
	<tr>
		<td class="tbcellCss print_cell">7.Serum Urea(Kidney Function test)</td>
		<td class="tbcellCss print_cell">8.Serum Electrolytes</td>
		<td class="tbcellCss print_cell">9.Serum Creatinine</td>
	</tr>
	<tr>
		
		<td class="tbcellCss print_cell" width="33%">10.Liver Function Test</td>
		<td class="tbcellCss print_cell" width="33%">11.Complete Urine Examination</td>
		<td class="tbcellCss print_cell">12.Ultrasound Abdomen</td>
	</tr>
	<tr>
		<td class="tbcellCss print_cell">13.Chest X-ray PA View</td>
		<td class="tbcellCss print_cell">14.Pulmonary Function Test</td>
		<td class="tbcellCss print_cell">15.ECG(ElectroCardioGraphy)</td>
	</tr>
	<tr>
		
		<td class="tbcellCss print_cell">16.2D-Echo</td>
		<td class="tbcellCss print_cell">17.TMT- TreadMill Test(wherever applicable)</td>
		<td class="tbcellCss print_cell">18.Retinopathy</td>
	</tr>
	<tr>
		
		<td class="tbcellCss print_cell">19.Fundoscopy</td>
		<td class="tbcellCss print_cell">20.Audiometry</td>
		<td class="tbcellCss print_cell">21.SGOT(Serum Glutamic Oxaloacetic Transaminase),<br>
						  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SGPT(Serum Glutamic Pyruvic Transaminase)</td>
	</tr>
	<c:if test="${annualCheckUpForm.gender eq 'Female'}"><tr><td class="tbcellCss print_cell" width="34%">22.Gynecology Examination</td></tr></c:if>
	</table>
	</td>
	</tr>


<tr class="print_buttons">
<td style="text-align:center">
<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
</tr>
</table>
</div>
</form>
</body>
</html>