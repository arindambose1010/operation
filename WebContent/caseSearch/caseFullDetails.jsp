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
<title>Cases Full Details</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
<form:form  method="post" id="form1">
<table width="100%">
<tr class="tbheader"><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Case Details</strong></td></tr>
</table>
<table width="100%">
<tr><td width="10%" class="labelheading1 tbcellCss"><b>Case No  </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.caseNo}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Claim No </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.claimNo}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Patient Name </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.patientName}</td></tr>
<tr><td width="10%" class="labelheading1 tbcellCss"><b>Card No  </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.wapNo}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Case Status </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.claimStatus}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Status Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.lstUpdDt}</td></tr>
<tr><td width="10%" class="labelheading1 tbcellCss"><b>Case Registration Date  </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.inpatientCaseSubDt}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>PreAuth Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csPreauthDt}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>PreAuth Apprv Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.preauthAprvDate}</td></tr>
<tr><td width="10%" class="labelheading1 tbcellCss"><b>Discharge Date  </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csDisDt}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Death Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csDeathDt}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Claim Amount </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.claimAmt}</td></tr>
<tr><td width="10%" class="labelheading1 tbcellCss"><b>Claim Submitted Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.clmSubDt}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Transaction Id </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csTransId}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Transaction Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csTransDt}</td></tr>
<tr><td width="10%" class="labelheading1 tbcellCss"><b>Remarks </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csRemarks}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>SBH Paid Date </b></td><td width="20%" class="tbcellBorder">${caseFullDtls.csSbhDt}</td></tr>
</table>
</form:form>
</body>
</html>