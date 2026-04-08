<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Follow Up Details Prescription Form</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<style type="text/css">
td {
	font-size: 13px;
}
</style>
<script type="text/javascript">
function fn_print(){
	document.getElementById("Print").style.display="none";
	document.getElementById("Close").style.display="none";
	window.print();
}
</script>
</head>
<body>
<html:form action="/followUpAction">
<table class="print_table" width="100%"><tr><td>
	<center>
	<!-- Header table for print and Screen -->
	<table  style="width:100%;margin:0 auto">
	        <tr><td>
	         <table  style="width:70%;margin:0 auto">
			<tr>
			<td>
				<c:if test="${scheme_id eq 'CD202'}">
					<%@ include file="/common/Printheader_tg.html" %>
				</c:if>
				<c:if test="${scheme_id ne 'CD202'}">
					<%@ include file="/common/Printheader.html" %>
				</c:if>
			</td>
		</tr>
			</table>
			</td></tr>
			<tr><td style="text-align:center"><b>FOLLOW UP DETAILS CUM PRESCRIPTION FORM ${currentDateTime}</b></td></tr>
		</table>
	
 <bean:define id="patientDtls" name="followUpForm"
		property="patient"></bean:define>
	<table width="95%" align="center">
		<tr>
			<td align="center" width="100%" class="tbheader print_heading" colspan="4"><b>Patient
			Details</b></td>
		</tr>
		<tr>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Patient
			Name:</b></td>
			<td width="250px" class="tbcellBorder print_cell"><bean:write
				property="firstName" name="patientDtls" /></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Address:</b></td>
			<td width="250px" class="tbcellBorder print_cell"><bean:write
				property="addr1" name="patientDtls" /></td>
		</tr>
		<tr>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Gender:</b></td>
			<td width="250px" class="tbcellBorder print_cell"><bean:write
				property="gender" name="patientDtls" /></td>
			<td width="250px"></td>
			<td width="250px"></td>
		</tr>
	</table>

	<br />

	<bean:define id="followUpDtls" name="followUpForm" property="followUpData"></bean:define>
	<c:set var="clinicalCounter" value="0"/>
	<nested:iterate id="followUp" name="followUpForm" property="followUpData">	
	<c:set var="clinicalCounter" value="${clinicalCounter + 1}"/>
	<table border="1" width="95%" align="center" bordercolor="black"
		style="margin: 5px;">
		<tr><td class="tbheader">
<b>Clincial Note: ${clinicalCounter} </b></td></tr>
<tr><td>
<table border="0" width="99%" align="center" >
		<tr>
			<td align="center" width="100%" class="tbheader print_heading" colspan="8"><b>Blood
			Pressure</b></td>
		</tr>
		<tr>
			<td width="125px" class="labelheading1 tbcellCss print_cell"><b>Respiratory
			Rate:</b></td>
			<td width="125px" class="tbcellBorder print_cell"><bean:write
				property="RESPIRATORY" name="followUp" /></td>
			<td width="125px" class="labelheading1 tbcellCss print_cell"><b>Temperature:</b></td>
			<td width="125px" class="tbcellBorder print_cell"><bean:write
				property="TEMPERATURE" name="followUp" /></td>
			<td width="125px" class="labelheading1 tbcellCss print_cell"><b>Systolic/Diastolic:</b></td>
			<td width="125px" class="tbcellBorder print_cell"><bean:write
				property="BLOODPRESSURE" name="followUp" /></td>
			<td width="125px" class="labelheading1 tbcellCss print_cell"><b>Pulse
			Rate:</b></td>
			<td width="125px" class="tbcellBorder print_cell"><bean:write
				property="PULSE" name="followUp" /></td>
		</tr>
		<tr>
			<td width="125px" class="labelheading1 tbcellCss print_cell"><b>Heart
			Rate:</b></td>
			<td width="125px" class="tbcellBorder print_cell"><bean:write
				property="HEART_RATE" name="followUp" /></td>
			<td width="125px" class="labelheading1 tbcellCss print_cell"><b>Lungs:</b></td>
			<td width="125px" class="tbcellBorder print_cell"><bean:write
				property="LUNGS" name="followUp" /></td>
			<td class="labelheading1 tbcellCss print_cell"><b>Fluid In:</b></td>
			<td class="tbcellBorder print_cell"><bean:write property="FLUIDINPT"
				name="followUp" /></td>
			<td class="labelheading1 tbcellCss print_cell"><b>Fluid Out:</b></td>
			<td class="tbcellBorder print_cell"><bean:write property="FLUIDOUTPUT"
				name="followUp" /></td>
		</tr>
	</table>
	<br />

	<table border="0" width="99%" align="center" bordercolor="black"
		style="margin: 5px;">
		<tr>
			<td align="center" width="100%" class="tbheader print_heading" colspan="10"><b>Drug
			Details</b></td>
		</tr>
		<tr>
			<td width="5%" class="labelheading1 tbcellCss print_cell"><b>S.No</b></td>
			<td width="15%" class="labelheading1 tbcellCss print_cell"><b>Drug
			Type name</b></td>
			<td width="20%" class="labelheading1 tbcellCss print_cell"><b>Drug
			Sub Type Name</b></td>
			<td width="14%" class="labelheading1 tbcellCss print_cell"><b>Drug
			Name</b></td>
			<td width="10%" class="labelheading1 tbcellCss print_cell"><b>Route</b></td>
			<td width="10%" class="labelheading1 tbcellCss print_cell"><b>Strength</b></td>
			<td width="5%" class="labelheading1 tbcellCss print_cell"><b>Dosage (per Day)</b></td>
			<td width="5%" class="labelheading1 tbcellCss print_cell"><b>Medication (Days)</b></td>
			<td width="7%" class="labelheading1 tbcellCss print_cell"><b>Batch Number</b></td>
			<td width="9%" class="labelheading1 tbcellCss print_cell"><b>Drug Expiry Date</b></td>
		</tr>
		<c:set var="counter" value="${1}"></c:set>
		<nested:iterate id="drug" name="followUp" property="drugs">
			<tr>
				<td width="5%" class="tbcellBorder print_cell"><c:out
					value="${counter}" /></td>
				<td width="15%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="drugTypeName" /></td>
				<td width="20%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="drugSubTypeName" /></td>
				<td width="14%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="drugName" /></td>
				<td width="10%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="route" /></td>
				<td width="10%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="strength" /></td>
				<td width="5%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="dosage" /></td>
				<td width="5%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="medicationPeriod" /></td>
				<td width="7%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="batchNumber" /></td>
				<td width="9%" class="tbcellBorder print_cell"><bean:write name="drug"
					property="drugExpiryDt" /></td>	
			</tr>
			<c:set var="counter" value="${counter+1}" />
		</nested:iterate>
	</table></td></tr></table>
</nested:iterate>
	<br />
	<table border="0" width="95%" align="center" bordercolor="black"
		style="margin: 5px;">
		<tr>
			<td class="tbheader print_heading"><b>Declaration by the
			MEDCO/TREATING DOCTOR</b></td>
		</tr>
		<tr>
			<td class="tbcellBorder print_cell">
			<p>The Patient is Examined by the consultant and follow up advice
			is given as per prescription of the treating doctor and as per the
			medical standard practices. The Drugs prescribed are essential for
			the follow up treatment and validity of the expiry is ascertained
			before giving to the patient.Patient is also advised about the
			dosage, timing and precaution while taking the drug and side effects
			that are likely to come.He/She is further advised to report back
			immediately in the event of side effect / adverse reaction.</p>
			</td>
		</tr>
	</table>
	<br />
	<br />
	<br />
	<br />
	<table width="95%" align="center">
		<tr>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Signature
			of MEDCO</b></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Signature
			of Patient/Thumb Impression</b></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Signature
			of Treating Doctor</b></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Signature
			of<c:choose>
							<c:when test="${followUpSchemeId eq 'CD201'}" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Arogya Mithra
							</c:otherwise>
						</c:choose> </b></td>
		</tr>
		<tr>
			<td width="250px" class="tbcellBorder print_cell">${ramcoName}</td>
			<td width="250px" class="tbcellBorder print_cell"><bean:write
				property="firstName" name="patientDtls" /></td>
			<td width="250px" class="tbcellBorder print_cell">&nbsp;</td>
			<td width="250px" class="tbcellBorder print_cell">&nbsp;</td>
		</tr>
		<tr>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Name of
			MEDCO</b></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Name of
			Patient</b></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Name of
			Treating Doctor</b></td>
			<td width="250px" class="labelheading1 tbcellCss print_cell"><b>Name of
			<c:choose>
				<c:when test="${followUpSchemeId eq 'CD201'}" >
					Vaidya Mithra
				</c:when>
				<c:otherwise >
					Arogya Mithra
				</c:otherwise>
			</c:choose></b></td>
		</tr>
	</table>
	<br />
	<table class="print_buttons" width="60%" style="margin: 0 auto">
		<tr>
			<td align="center">
			<button class="but" type="button" id="Print" onclick="fn_print();">Print</button>
			<button class="but" type="button" id="Close"
				onclick="window.close();">Close</button>
			</td>
		</tr>
	</table>
	</center>
	</td></tr></table>
</html:form>
</body>
</html>