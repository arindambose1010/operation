<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/RightClickDisable.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Print DTRS</title>
<script type="text/javascript">
function fn_print(){
	document.getElementById("Print").style.display="none";
	document.getElementById("Close").style.display="none";
	window.print();
}
</script>
<style type="text/css">
td{
 font-size: 13px;
}
</style>
</head>
<body>
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
			<tr><td style="text-align:center"><b>DIAGNOSTIC TEST REQUISITION SLIP (FOLLOW - UP)	${currentDateTime}</b></td></tr>
		</table>

<table width="95%" border="0" align="center">
	<tr>
<td class="tbheader print_heading" width="100%" align="center" colspan="4">
<b>Patient Details</b>
</td>
</tr>
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Name:</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.VALUE}</td>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Date:</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.LVL}</td>
		</tr>
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Health Card</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.ID}</td>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Age:</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.EMPID}</td>
		</tr>
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Sex:</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.CONST}</td>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Claim No:</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.UNITID}</td>
		</tr>
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Case Id:</b></td>
			<td class="tbcellBorder print_cell" width="25%">&nbsp;${dtrsData.EMPNAME}</td>
		</tr>
	</table>
	<br />

	<table width="95%" border="0" align="center">
	<tr>
				<td align="center" width="100%" class="tbheader print_heading" colspan="3"><b>Investigations</b></td>
			</tr>
		<tr>
			<td width="5%" class="labelheading1 tbcellCss print_cell"><b>S. No</b></td>
			<td width="45%" class="labelheading1 tbcellCss print_cell"><b>Investigation
					Block Name</b></td>
			<td width="50%" class="labelheading1 tbcellCss print_cell"><b>Investigation
					Name</b></td>
		</tr>
		<c:set var="counter" value="0" />
		<logic:iterate id="value" name="followUpForm"
			property="lstAttachments">
			<c:set var="counter" value="${counter + 1}" />
			<tr>
				<td width="5%" class="tbcellBorder print_cell">${counter}</td>
				<td width="45%" class="tbcellBorder print_cell"><bean:write name="value"
						property="remarks" /></td>
				<td width="50%" class="tbcellBorder print_cell"><bean:write name="value"
						property="docType" /></td>
			</tr>
		</logic:iterate>
	</table>
	<br/>

	
	<table width="95%" align="center" border="0">
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Name of MEDCO</b></td>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Signature of MEDCO</b></td>
			<td width="50%">&nbsp;</td>
		</tr>
		<tr>
			<td class="tbcellBorder print_cell" width="25%">${medcoName}</td>
			<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Date:</b></td>
			<td width="50%">&nbsp;</td>
		</tr>
	</table>
	<br>
	<table class="print_buttons" width="60%" style="margin: 0 auto">
			<tr>
				<td align="center">
					<button class="but" type="button" id="Print" onclick="fn_print();">Print</button>
					<button class="but" type="button" id="Close" onclick="window.close();">Close</button>
				</td>
			</tr>
		</table>
</center>
</td></tr></table>
</body>
</html>