<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.ahct.common.vo.LabelBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="CSS/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script language="javascript" type="text/javascript">
	var s$ = jQuery.noConflict();

	s$(function() {
		/*
		 * this swallows backspace keys on any non-input element.
		 * stops backspace -> back
		 */
		var rx = /INPUT|SELECT|TEXTAREA/i;

		s$(document).bind(
				"keydown keypress",
				function(e) {
					if (e.which == 8) { // 8 == backspace
						if (!rx.test(e.target.tagName) || e.target.disabled
								|| e.target.readOnly) {
							e.preventDefault();
						}
					}
				});
	});

	function fn_Print() {
		window.print();
	}
</script>
</head>
<body>
	<form action="/patientDetails.do" method="post"
		name="caseGeneratedForm">
		<br>
		<br>
		<br>
		<br>
		<br>
		<center>
			<b><u><font size="3px">Employee Health Scheme</font></u></b>
		</center>
		<table width="60%" style="margin: 0 auto">
			<tr>
				<td><b><fmt:message key="EHF.Name" /></b></td>
				<td>:&nbsp;<bean:write name="patientForm"
						property="patientName" /></td>
			</tr>
			<tr>
				<td><b><fmt:message key="EHF.HealthCardNo" /></b></td>
				<td>:&nbsp;<bean:write name="patientForm" property="cardNo" /></td>
			</tr>
			<tr>
				<td><b><fmt:message key="EHF.PatientNo" /></b></td>
				<td>:&nbsp;<bean:write name="patientForm" property="patientNo" /></td>
			</tr>
			<tr>
				<td><b><fmt:message key="EHF.Gender" /></b></td>
				<td>:&nbsp;<bean:write name="patientForm" property="gender" /></td>
			</tr>
			<tr>
				<td><b><fmt:message key="EHF.Age" /></b></td>
				<td>:&nbsp;<bean:write name="patientForm" property="ageString" /></td>
			</tr>
			<tr>
				<td><b>Name and Signature Of MEDCO/Treating Doctor</b></td>
				<td>:&nbsp;</td>
			</tr>
			<tr>
				<td><b><fmt:message key="EHF.DateAndTime" /></b></td>
				<td>:&nbsp;<b><bean:write name="patientForm"
							property="dtTime" /></b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center">
					<button class="but" type="button" id="Print" onclick="fn_Print()()">Print</button>
				</td>
			</tr>
		</table>
	</form>
</body>
	</html>
</fmt:bundle>