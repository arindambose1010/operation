<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OPD Claim Cases</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style type="text/css">.centerone{padding-left:6%;}</style>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="scripts/PreauthScripts.js"></script>
<script src="js/jquery.msgBox.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%@ include file="/common/editableComboBox.jsp"%>  
<%@ include file="/common/includePatientDetails.jsp"%>  
<style> 
#ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
/* Chandana - 7845 - Added below styles to not showing the up and down arrows */
#deductAmt::-webkit-inner-spin-button,
#deductAmt::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
#deductAmt {
  -moz-appearance: textfield;
}
.mandatory-text {
            animation: blink 1s infinite;
            font-size: 105%;
            text-align: right;
            color: red;
        }
</style>
</head>
<body onload="fn_removeLoadingImage()">
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" name="patientForm" enctype="multipart/form-data"><!-- Chandana - 8441 - Added the name in the form tag -->
<br>
<c:choose>
<c:when test="${dataIsPresent == 'Y' }">
	<table width="95%" style="margin:0 auto">
	<tr><td colspan="4">
	<table class="tbheader">
	<tr><td><b>&nbsp;&nbsp;&nbsp;Card Details</b></td></tr><!-- Chandana - 7845 - Changed from OP Patient Details to Card Details as suggested by testing team -->
	</table>
	<table width="100%">
	<tr>
		<td width="15%" class="labelheading1 tbcellCss"><b>Health Card Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
		<td width="2%">&nbsp;</td><!-- Chandana - 8606 - Changed the with for empty td's from 5 to 2 to adjust the ui look -->
		<td width="15%" class="labelheading1 tbcellCss"><b>Patient Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
		<td width="2%">&nbsp;</td>
		<td width="11%" class="labelheading1 tbcellCss"><b>CR Number</b></td><!-- Chandana - 8606 - Added this td to show the cr number -->
		<td width="11%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="crNumber"/></b></td>
		<td width="2%">&nbsp;</td>
	<c:if test="${not empty opdClaimNo}">
		<c:set var="opdClaimNo" value="${opdClaimNo}"/>
		<td width="15%" class="labelheading1 tbcellCss"><b>OP Claim Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;${opdClaimNo}</b></td>
		<!-- <td width="5%">&nbsp;</td> --><!-- Chandana - 8606 - Commented this td -->
	</c:if>
	</tr>
	</table>
	<br>
	<table class="tbheader">
	<tr><td><b>Patient Details</b></td></tr>
	</table>
	<table width="100%">
	<tr>
	<td width="27%" valign="top">
	<fieldset style="height:20em;">
	<legend class="legendStyle"><b>Patient Details</b></legend>
	<div style="height:20em;overflow:hidden" id='commonDtlsField'>
	<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
	<tr><td class="labelheading1 tbcellCss" width="40%"><b>Name</b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Gender</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Date Of Birth</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="dateOfBirth"/></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Age</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="years"/>Y
	 		<bean:write name="patientForm" property="months"/>M
			<bean:write name="patientForm" property="days"/>D</b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Relationship</b></td><td class="tbcellBorder"><b>&nbsp;<c:if test="${patientForm.relation eq 'New Born Baby'}"  ><b></c:if><bean:write name="patientForm" property="relation"/></b></td></tr>
	 <tr><td class="labelheading1 tbcellCss"><b>Slab</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="slab"/></b></td></tr> 
	<tr><td class="labelheading1 tbcellCss"><b>Designation</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="occupation"/></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Contact No</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="contactno"/></b></td></tr>
	</table>
	</div>
	</fieldset>
	</td>
	<td width="29%" valign="top">
	<fieldset style="height:20em;" >
	 <legend class="legendStyle" ><b>Card Address</b></legend>
	  <div style="height:20em;overflow:hidden" id='cardAddressField'><!-- Chandana - 8036 - Changed 18em to 20 em -->
	<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
	<tr><td class="labelheading1 tbcellCss" width="50%"><b>House No</b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="hno"/></b></td> </tr>
	<tr><td class="labelheading1 tbcellCss"><b>Street</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="street"/></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="state"/></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>District</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="district" /></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Mdl/Mcl</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="mandal" /></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Village</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="village" /></b></td></tr> 
	<tr><td class="labelheading1 tbcellCss"><b>Pin</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="pin" /></b></td></tr>
	</table>
	</div>
	</fieldset>
	</td>
	<td width="29%" valign="top">
	<fieldset style="height:20em;">
	 <legend class="legendStyle"><b>Communication Address</b></legend>
	 <div style="height:20em;overflow:hidden" id='commAddressField'><!-- Chandana - 8036 - Changed 18em to 20 em -->
	<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
	<tr><td class="labelheading1 tbcellCss" width="50%"><b>House No</b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="comm_hno" /></b></td> </tr>
	<tr><td class="labelheading1 tbcellCss"><b>Street</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_street" /></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_state" /></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>District</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_dist" /></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Mdl/Mcl</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></b></td></tr>
	<tr><td class="labelheading1 tbcellCss"><b>Village</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_village" /></b></td></tr> 
	<tr><td class="labelheading1 tbcellCss"><b>Pin</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_pin" /></b></td></tr>
	</table>
	</div>
	</fieldset>
	</td>
	<td width="15%" valign="top">
	<fieldset style="height:20em;" id='photoField'><!-- Chandana - 7845 - changed height from 18 to 20 -->
	 <legend class="legendStyle"><b>Photo</b></legend>
	<table width="80%" height="80%" style="margin:auto auto">
	<tr><td align="center">
	 <logic:notEmpty name="patientForm" property="photoUrl">
	<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')"/>
	</logic:notEmpty>
	<logic:empty name="patientForm" property="photoUrl">
	<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
	</logic:empty>
	</td></tr></table>
	</fieldset>
	</td></tr>
	</table>
	<br>
	<table class="tbheader" style="margin-top:3%;"><!-- Chandana - 7845 - Added style to view the above fields without brekaing the UI -->
	<tr><td><b>Registered Hospital Details</b></td></tr>
	</table>
	<table width="100%">
	<tr>
	<td width="15%" class="labelheading1 tbcellCss"><b>Registered Hospital</b></td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="hospitalName"/></b></td>
	<td width="15%" class="labelheading1 tbcellCss"><b>Date And Time</b> </td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="dtTime"/></b></td>
	<c:if test="${(dentalFlg eq 'Y')}">
	<td width="15%" class="labelheading1 tbcellCss"><b>TreatmentPlanned</b><font color="red" class="onlyAp">*</font></td><td width="35%" class="tbcellBorder">
	<b>
	<html:radio name="patientForm" styleId="treatmentDntl" property="treatmentDntl" value="yes" onclick="javascript:redirecttodental(this.value);" />yes
	<html:radio name="patientForm" styleId="treatmentDntl" property="treatmentDntl" value="no"  />no
	</b></td>
	</c:if>
	</tr>
	</table>
	<br>
	<c:set var="totalDocuments" value="0" />
	<c:set var="billAmount" value="0"/>
	<c:if test="${not empty opClaimBillDtls}">
	<table class="tbheader">
	<tr><td><b>Bill Details</b></td></tr>
	</table>
	<table id="opClaimCasesTable" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<thead><tr>
		<th class="tbheader1" valign="top" style="width:">S No</th>
		<th class="tbheader1" valign="top" style="width:">Bill No</th>
		<th class="tbheader1" valign="top" style="width:">Type</th>
		<c:if test="${roleId ne 'GP9' or roleId ne 'GP3' or roleId ne 'GP29'}"><th class="tbheader1" valign="top" style="width:">Sample No</th></c:if>
		<th class="tbheader1" valign="top" style="width:">Bill Date&Time</th><!-- Chandana - 8602 - Added time also -->
		<th class="tbheader1" valign="top" style="width:">Received Date&Time</th><!-- Chandana - 8602 - Added this to show the crt date -->
		<th class="tbheader1" valign="top" style="width:">Attachment</th>
		<!-- Chandana - 8036 - 29-07-25: Commented below, as we are not going to enter the remarks bill wise -->
		<%-- <c:if test="${roleId eq 'GP9' or roleId eq 'GP3'}"><th class="tbheader1" valign="top" style="width:">Remarks</th></c:if> --%>
		<th class="tbheader1" valign="top" style="width:">Quantity</th>
		<th class="tbheader1" valign="top" style="width:">Amount</th>
	</tr></thead>
	<!-- Chandana - 8441 - Added below for checking all the docs opened by the PEX and CH -->
	<script type="text/javascript">
		    var allBills = [];
		    var documentViewStatus = {};
		    <c:forEach var="opClaimCases" items="${opClaimBillDtls}">
		    allBills.push("${opClaimCases.BILLNO}");
		        documentViewStatus["${opClaimCases.BILLNO}"] = false;
		    </c:forEach>
	</script>
	<tbody id="opdClaimCasesTableBody">
	<c:set var="hasMissingAttachment" value="false" /><!-- Chandana - 25-08-25 -->
	<c:forEach var="opClaimCases" items="${opClaimBillDtls}" varStatus="loop">
	<c:set var="billAmount" value="${opClaimCases.TOTALAMT}"/>
	<c:set var="lastBillNo" value="${opClaimCases.BILLNO}" /><!-- Chandana - Added this for bill number, to check the bill no wise attachments click -->
	 <!-- If any record has empty ATTACHPATH, set flag to false - to hide the submit button -->
    <c:if test="${empty opClaimCases.ATTACHPATH}">
    	<c:set var="hasMissingAttachment" value="true" />
    </c:if>
	<tr>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.BILLNO}</td><!-- Chandana - 7845 - Removed span to bill no -->
		<%-- <span style="color:blue;cursor:pointer;" >${opClaimCases.BILLNO}</span></td> --%>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.TYPE}</td>
		<c:if test="${roleId ne 'GP9' or roleId ne 'GP3' or roleId ne 'GP29'}"><td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.SAMPLENO}</td></c:if>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.BILLDT}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.CRTDT}</td><!-- Chandana - 8602 - Added this to show the crt date -->
		<%-- <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;" onclick='viewDocument("${opClaimCases.ATTACHPATH}", "${opClaimCases.BILLNO}")'>
			<span style="color:blue;cursor:pointer;" >View</span>
		</td> --%>
		<!-- Chandana - 25-08-25 -->
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
    	<c:choose>
	        <c:when test="${not empty opClaimCases.ATTACHPATH}">
	            <span style="color:blue;cursor:pointer;" onclick='viewDocument("${opClaimCases.ATTACHPATH}", "${opClaimCases.BILLNO}")'>
	                View
	            </span>
	        </c:when>
	        <c:otherwise>
			    <span><input type='file' id='attachment' name='attachment' accept='.pdf, .jpeg, .jpg, .png' class='form-control-file' onchange="validateFile(this, 'M'); toggleUploadIcon(this);">
				<span id="uploadIcon" title="Click here to upload" class="glyphicon glyphicon-upload" style="color:blue; cursor:pointer;margin-left:41%; margin-top:-12%; font-size:16px; vertical-align:middle; display:none;"
			      onclick="fn_uploadFile('${opClaimCases.BILLNO}','${opClaimCases.SAMPLENO}','${opClaimCases.DIFF}','${opClaimCases.ID}');"> </span></span> 
			</c:otherwise> 
    	</c:choose>
		</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.COUNT}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.AMOUNT}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
<!-- Chandana - 8874 - New div for shwoing the consolidated attachment-->
	<table class="tbheader">
	<tr><td><b>Consolidated Bill Details</b></td></tr>
	</table>
<c:if test="${empty conslidateBill}"><!-- Chandana - 8934 - Added this if for shwoing no records found for empty consolidated bill details -->
	<div  class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group " style="text-align:center">
		<h5><b>NO RECORDS FOUND</b></h5>
	</div>
</c:if>
<c:if test="${not empty conslidateBill}">
	<table id="opClaimCasesTable" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<thead><tr>
		<th class="tbheader1" valign="top" style="width:">S No</th>
		<th class="tbheader1" valign="top" style="width:">Received Date&Time</th>
		<th class="tbheader1" valign="top" style="width:">Attachment</th>
	</tr></thead>
	<tbody id="opdClaimCasesTableBody">
	<c:set var="hasMissingAttachment" value="false" />
	<c:forEach var="opClaimCases" items="${conslidateBill}" varStatus="loop">
    <c:if test="${empty opClaimCases.ATTACHPATH}">
    	<c:set var="hasMissingAttachment" value="true" />
    </c:if>
	<tr>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.CRTDT}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
    	<c:choose>
	        <c:when test="${not empty opClaimCases.ATTACHPATH}">
	            <span style="color:blue;cursor:pointer;" onclick='viewDocument("${opClaimCases.ATTACHPATH}", "")'>
	                View
	            </span>
	        </c:when>
	        <c:otherwise>
			    <span><input type='file' id='attachment' name='attachment' accept='.pdf, .jpeg, .jpg, .png' class='form-control-file' onchange="validateFile(this, 'M'); toggleUploadIcon(this);">
				<span id="uploadIcon" title="Click here to upload" class="glyphicon glyphicon-upload" style="color:blue; cursor:pointer;margin-left:41%; margin-top:-12%; font-size:16px; vertical-align:middle; display:none;"
			      onclick="fn_uploadFile('${opClaimCases.BILLNO}','${opClaimCases.SAMPLENO}','${opClaimCases.DIFF}','${opClaimCases.ID}');"> </span></span> 
			</c:otherwise> 
    	</c:choose>
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
<!-- Chandana - 8874 - Ending here -->
<!-- Chandana - 8441- -->
	<table class="tbheader" id="othrMainTab" style="display:none;">
	<tr><td><b>Additional Attachments</b></td></tr>
	</table>
	<table id="addOtherAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;display:none;" class="tb">
	<thead><tr>
		<!-- <th class="tbheader1" valign="top" style="width:">S No</th> -->
		<th class="tbheader1" valign="top" style="width:">Description</th>
		<th class="tbheader1" valign="top" style="width:">Remarks</th>
		<th class="tbheader1" valign="top" style="width:">Upload</th>
		<th class="tbheader1" valign="top" style="width:">Action</th>
	</tr></thead>
	<tbody id="otherAttachTab"></tbody>
	</table>
<!-- Chandana - 8441 - below if condition is for showing the additional attachments -->
	<c:if test="${not empty additionalAttach}">
	<table class="tbheader"><tr><td><b>Additional Attachments</b></td></tr></table>
	<table id="addtionalAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
		<thead><tr>
			<th class="tbheader1" valign="top" style="width:">S No</th>
			<th class="tbheader1" valign="top" style="width:">Type</th>
			<th class="tbheader1" valign="top" style="width:">Description</th>
			<th class="tbheader1" valign="top" style="width:">Remarks</th>
			<th class="tbheader1" valign="top" style="width:">Attachment</th>
			<c:if test="${roleId eq 'GP2' }"><!-- Chandana - 8441 - Added this if for showing this delete button for medco only -->
            	<c:if test="${claimStatus eq 'CD7303' || claimStatus eq 'CD7329' || claimStatus eq 'CD7315' }">
					<th class="tbheader1" valign="top" style="width:">Action</th>
				</c:if>
			</c:if>
		</tr></thead>
		<!-- Chandana - 8441 - Added the below script for madatory attchments for PEX and CH -->
		<script type="text/javascript">
		    var allAddtionalDocs = [];
		    var documentViewStatus = {};
		    <c:forEach var="addtnalAttach" items="${additionalAttach}">
		        allAddtionalDocs.push("${addtnalAttach.COUNT}");
		        documentViewStatus["${addtnalAttach.COUNT}"] = false;
		    </c:forEach>
		</script>
		<tbody id="additionalAttachTabBody">
		<c:forEach var="addtnalAttach" items="${additionalAttach}" varStatus="loop">
		<tr>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">Additional Attachment</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${addtnalAttach.VALUE}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${addtnalAttach.REMARKS}
			</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
				<span style="color:blue;cursor:pointer;" onclick='viewAdditionalDocument("${addtnalAttach.ATTACHPATH}", "${addtnalAttach.COUNT}")'>View</span>
			</td>
			<c:if test="${roleId eq 'GP2' }"><!-- Chandana - 8441 - Added this if for showing this delete button for medco only -->
            	<c:if test="${claimStatus eq 'CD7303' || claimStatus eq 'CD7329' || claimStatus eq 'CD7315' }">
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;"><button class="btn btn-danger" type="button" onclick='deleteRow("${addtnalAttach.COUNT}" , "D","${addtnalAttach.REMARKS}","${addtnalAttach.VALUE}"  )'>Delete</button></td>
				</c:if>
			</c:if>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${empty opClaimBillDtls}">
		<div  class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group " style="text-align:center">
			<h5><b>NO RECORDS FOUND</b></h5>
		</div>
		<!-- Chandana - 7845 - added this button to go back to the main screen -->
		<div  class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group " style="text-align:center">
			<button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button>
		</div>
	</c:if>
	<%-- <table align="center">
	<tbody>
	<c:if test="${empty opdClaimNo}">
		<tr>
			<td class="labelheading1 tbcellCss"><b>Reamrks</b></td>
			<td><textarea type="text" id="remarks" name="remarks" style="width: 343px; height: 36px;" maxlength="50" onkeyup="validateInput(event)"></textarea>
			<td> 
				<button class="btn btn-primary has-spinner" type="button" id="Submit" onclick="fn_medcoApproveOpClaim();" style="margin-left: 140%;">Submit</button>
			</td>	
			<td>
				<button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();" style="margin-left: 180%;">Cancel</button>
			</td>
		</tr>
	</c:if>	
	<c:if test="${not empty opdClaimNo}">
	<div style="text-align: center;">
		<button class="btn btn-danger has-spinner" type="button" id="back" onclick="fn_MainDashboard();">Back</button>
	</div>
	</c:if>
	</tbody>
	</table> --%>
	<c:if test="${not empty opClaimBillDtls}">
	<table align="center">
    <tbody>
        <tr>
        <!-- Chandana - 8036 - 29-07-25: Commented below, as we are not going to enter the remarks bill wise -->
        <%-- <c:if test="${roleId eq 'GP2' }"> --%>
        	<%-- <c:if test="${claimStatus eq 'CD7303' || claimStatus eq 'CD7329' || claimStatus eq 'CD7315' }"> --%>
	            <td class="labelheading1 tbcellCss" style="width:10%"><b>Initiated Amount</b></td>
	            <td class="tbcellBorder" style="width:10%"><b style="Margin-left:34%">&nbsp;${billAmount}</b></td>
           	<%-- </c:if> --%>
        <%-- </c:if> --%>
        <%-- <c:if test="${roleId eq 'GP9'}">
            <td class="labelheading1 tbcellCss"><b>Initiated Amount</b></td>
            <td class="tbcellBorder"><b>&nbsp;${billAmount}</b></td>
        </c:if> --%>
            <c:choose>
                <c:when test="${roleId == 'GP9' || roleId == 'GP29'}">
                    <td class="labelheading1 tbcellCss"><b>Deduction Amount</b></td>
		            <c:if test="${claimStatus ne 'CD7343' }"><td class="tbcellBorder"><input type="number" id="deductAmt" name="deductAmt" value="0" min="0" oninput="this.value = this.value.replace(/[^\d]/g, '')" onchange="fn_CalcAmt()"></td></c:if>
		            <c:if test="${claimStatus eq 'CD7343' }"><td class="tbcellBorder"><input type="number" id="deductAmt" name="deductAmt" value="${deductedAmount}" min="0" oninput="this.value = this.value.replace(/[^\d]/g, '')" onchange="fn_CalcAmt()"></td></c:if>
		            <td class="labelheading1 tbcellCss"><b>Final Amount</b></td>
		            <c:if test="${claimStatus ne 'CD7343' }"><td class="tbcellBorder"><input type="number" id="finalAmt" name="finalAmt" value="${billAmount}" readonly></td></c:if>
		            <c:if test="${claimStatus eq 'CD7343' }"><td class="tbcellBorder"><input type="number" id="finalAmt" name="finalAmt" value="${approvedAmount}" readonly></td></c:if>
                </c:when>
                <c:otherwise>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </c:otherwise>
            </c:choose>
            <c:if test="${roleId eq 'GP2' }">
            	<c:if test="${claimStatus eq 'CD7303' || claimStatus eq 'CD7329' || claimStatus eq 'CD7315' }">
            <td class="labelheading1 tbcellCss"><b>Remarks</b></td>
            	</c:if>
            </c:if>
            <c:if test="${roleId eq 'GP9' || roleId eq 'GP29'}">
            	<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
            </c:if>
            <c:if test="${roleId eq 'GP3' }">
            	<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
            </c:if>
            <td>
                <c:choose>
				    <c:when test="${empty opdClaimNo && roleId == 'GP2'}">
				    	<c:if test="${roleId eq 'GP2' }">
            				<c:if test="${claimStatus eq 'CD7303' || claimStatus eq 'CD7329' || claimStatus eq 'CD7315' }">
				        		<span><textarea type="text" id="remarks" name="remarks" style="width: 343px; height: 38px;" maxlength="1000" onkeyup="validateInput(event)"></textarea></span>
        						<!-- Chandana - 8441 - Added below span for adding the addtional attachments -->
        						<span><button class="btn btn-primary" type="button" onclick="addRow()" style="margin-top: -4%;">Add Attachments</button></span>
				        	</c:if>
				        </c:if>
				    </c:when>
				    <c:when test="${not empty opdClaimNo && roleId eq 'GP2'}">
				    	<c:if test="${roleId eq 'GP2' }">
            				<c:if test="${claimStatus eq 'CD7303' || claimStatus eq 'CD7329' || claimStatus eq 'CD7315' }">
				        		<span><textarea type="text" id="remarks" name="remarks" style="width: 343px; height: 38px;" maxlength="1000" onkeyup="validateInput(event)"></textarea></span>
        						<!-- Chandana - 8441 - Added below span for adding the addtional attachments -->
        						<span><button class="btn btn-primary" type="button" onclick="addRow()" style="margin-top: -4%;">Add Attachments</button></span>
				        	</c:if>
				        </c:if>
				    </c:when>
				    <c:when test="${not empty opdClaimNo && roleId eq 'GP3'}"><!-- Chandana - 7845 - added this for PEX remarks-->
				        <textarea type="text" id="PEXremarks" name="PEXremarks" style="width: 343px; height: 38px;" maxlength="1000" onkeyup="validateInput(event)"></textarea>
				    </c:when>
				    <c:otherwise>
				    	<c:if test="${roleId eq 'GP9' || roleId eq 'GP29'}">
				        	<textarea type="text" id="CHremarks" name="CHremarks" style="width: 343px; height: 38px;" maxlength="1000" onkeyup="validateInput(event)"></textarea>
				        </c:if>
				    </c:otherwise>
				</c:choose>
            </td>
        </tr>
        <c:choose>
            <c:when test="${roleId == 'GP2'}">
                <tr>
                    <c:choose>
                        <c:when test="${empty opdClaimNo}">
                            <td colspan="6"></td>
                            <c:if test="${not hasMissingAttachment and not empty conslidateBill}"><!-- Chandana - 8934 - Added and not empty condition in the existing  -->
                            <td style="padding-left:34%;">
                                <button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button>
                            </td>
                            <td style="padding-left:17%;"> 
                                <button class="btn btn-primary has-spinner" type="button" id="Submit" onclick="fn_medcoApproveOpClaim('Submit');">Submit</button>
                            </td>
                            </c:if>
                            <c:if test="${hasMissingAttachment}">
                            	<td><span class="mandatory-text">Upload the attachment to submit the claim in <b>Bill Details</b> section</span></td><!-- Chandana - 8599 - Added this note for user understanding about submit button  -->
                            	<td style="padding-left:0%;">
                                <button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button>
                            </td>
                            </c:if>
                            <c:if test="${empty conslidateBill and not hasMissingAttachment}"><!-- Chandana - 8934 - Added this if for shpwing the back button for not having the consolidated bill -->
                            	<td style="padding-left:2%;"><span class="mandatory-text">Consolidated bill details are missing</span></td><!-- Chandana - 8934 - Added this note for user understanding about submit button  -->
                            	<td style="padding-left:0%;">
                                <button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button>
                            </td>
                            </c:if>
                        </c:when>
                        <c:when test="${not empty opdClaimNo}">
                        		<td style="padding-left:54%;">
                                	<button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button>
                            	</td>
                        	<c:if test="${claimStatus eq 'CD7329' }">
	                            <td style="padding-left:37%;"> 
	                                <button class="btn btn-primary has-spinner" type="button" id="Submit" onclick="fn_medcoApproveOpClaim('ReSubmit');">Submit</button>
	                            </td>
                            </c:if>
                            <c:if test="${claimStatus eq 'CD7315' }">
	                            <td style="padding-left:37%;"> 
	                                <button class="btn btn-primary has-spinner" type="button" id="Submit" onclick="fn_medcoApproveOpClaim('ReSubmit');" style="margin-left: 550%;">Submit</button>
	                            </td>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <td colspan="8" style="text-align: center;">
                                <button class="btn btn-danger has-spinner" type="button" id="back" onclick="fn_MainDashboard();" style="margin-left:1000%">Back</button>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:when>
            <c:when test="${roleId == 'GP3'}"><!-- Chandana - 7845 - added this condition for PEX -->
	        	<tr>
	        	<c:if test="${claimStatus eq 'CD7328' }">
		           <td style="padding-left:45%;">
		               <button class="btn btn-primary has-spinner" type="button" id="gp3Submit" onclick="fn_CEXNimsOpClaim('Approve');">Approve</button>
		           </td>
		            <td style="padding-left:43%;">
		                <button class="btn btn-warning has-spinner" type="button" id="gp3Return" onclick="fn_CEXNimsOpClaim('Return');">Pending</button>
		            </td>
	            </c:if>
	            <c:if test="${claimStatus eq 'CD7334' }">
	            	<td style="padding-left:50%;">
		               <button class="btn btn-primary has-spinner" type="button" id="gp3Submit" onclick="fn_CEXNimsOpClaim('Approve');">Approve</button>
		           </td>
	            </c:if>
	        </tr>
    	</c:when>
        <c:otherwise>
        	<tr>
            	<td colspan="4"></td>
				<td colspan="3" style="text-align: center;padding-top:1%; padding-right:6%;">
				<!-- Chandana - 8602 - added c:if for showing the approve and ReApprove -->
					<c:if test="${claimStatus eq 'CD7330' || claimStatus eq 'CD7316' }"><button class="btn btn-primary has-spinner" type="button" id="CHSubmit" onclick="fn_NimsOpClaim('Approve');">Approve</button></c:if>
					<c:if test="${claimStatus eq 'CD7343'}">
						<button class="btn btn-primary has-spinner" type="button" id="CHSubmit" onclick="fn_NimsOpClaim('ReApprove');">Approve</button>
					</c:if>
				    <button class="btn btn-danger has-spinner" type="button" id="CHcancel" onclick="fn_NimsOpClaim('Reject');">Reject</button>
				    <c:if test="${claimStatus eq 'CD7330'}">
				    	<button class="btn btn-warning has-spinner" type="button" id="CHReturn" onclick="fn_NimsOpClaim('Return');">Pending</button>
				    </c:if>
				</td>           
              </tr>
            </c:otherwise>
        </c:choose>
   	 </tbody>
	</table>
	</c:if>
	</td></tr>
	</table>
	<!-- Chandana - 7845 - Changes Start for claims workflow -->
	<c:if test="${not empty workFlowDtls}">
		<table class="tbheader" style="width:95%; margin-left:2.5%;">
			<tr><td><b>OPD Claim Approval Workflow</b></td></tr>
		</table>
		<table id="opWorkFlowTable" width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:95%;" class="tb">
			<thead><tr>
				<th class="tbheader1" valign="top" style="width:">S No</th>
				<th class="tbheader1" valign="top" style="width:">Date & Time</th>
				<th class="tbheader1" valign="top" style="width:">Name & Role</th>
				<th class="tbheader1" valign="top" style="width:">Remarks</th>
				<th class="tbheader1" valign="top" style="width:">Status</th>
				<th class="tbheader1" valign="top" style="width:">Amount</th>
			</tr></thead>
			<tbody id="opdClaimCasesTableBody">
			<c:forEach var="wrkFlwDtls" items="${workFlowDtls}" varStatus="loop">
			<tr>
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${wrkFlwDtls.VALUE}</td>
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${wrkFlwDtls.EMPNAME}</td>
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${wrkFlwDtls.REMARKS}</td>
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${wrkFlwDtls.CASESTATUS}</td>
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${wrkFlwDtls.AMOUNT}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	<!-- Chandana - 7845 - Changes end for claims workflow -->
	</c:when>
	<c:otherwise>
		<div  class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group " style="text-align:center">
			<h5><b>NO RECORDS FOUND</b></h5>
		</div>
	</c:otherwise>
</c:choose>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="width: 135%;margin-left: -18%;">
	  <div class="modal-dialog modal-lg" role="document">
	  <span class="close" onclick="closeModal();" style="margin-top:-2%; color:red; opacity:10">&times;</span>
	    <div class="modal-content" id="modal-content"style="">
	      <div class="modal-body" id="viewDocuments" style="max-height:100%;">
	      </div>
	   	 </div>
 		</div>
	</div>
<!-- Progress Bar -->
<div id="processImagetable" style="top:50%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:400" >
   <tr>
      <td>
        <div id="processImage" align="center">
          <img src="images/Progress.gif" width="100"
                  height="100" border="0"></img>
         </div>
       </td>
     </tr>
  </table>
</div>
</form>
</div>
</body>
<script type="text/javascript">
var $ = jQuery.noConflict();
var todayDt = new Date();
function validateInput(event) {
    const inputField = event.target;
    let value = inputField.value;
    const regex = /^[a-zA-Z0-9 ,.]*$/;
    if (!regex.test(value)) {
        inputField.value = value.slice(0, -1);
        return;
    }
    if (value.length === 1 && (value[0] === ' ' || value[0] === '.' || value[0] === ',')) {
        alert("Remarks should start with alphanumeric characters");
        inputField.value = "";
        return;
    }
}
var additionalAttachCount = "${fn:length(additionalAttach)}";//Chandana - 8441 - Added this line to get the length of the additional attachments
function addRow() { //Chandana - 8441 - Written this function for adding a row on clcking on the additional attachment button
	if(additionalAttachCount >= 5){
		alert("Maximum limit of 5 additional attachments is exceeded");
		return;
	}
    if ($("#otherAttachTab tr.pending").length > 0) {
        alert("Please save or remove the row already added.");
        return;
    }
    $('#addOtherAttachTab').show();
    let newRow =
        "<tr class='pending'>" +
            "<td><input type='text' id='description' name='description' class='form-control'></td>" +
            "<td><input type='text' id='additionalRemarks' name='remarks' class='form-control'></td>" +
            "<td><input type='file' id='attachment' name='attachment' accept='.pdf, .jpeg, .jpg, .png' class='form-control-file'  onchange='validateFile(this,'A')'>"+
            "<span style='display:block; color: red; font-size: 12px; margin-top: 2px;'>Allowed formats: .pdf, .jpeg, .jpg, .png (Max size: 200 KB)</span>" +
            "</td>" +
            "<td><input type='button' name='save' value='Save' class='btn btn-primary' onclick='saveAttachment()' style='margin-right:5px;' />" +
                "<input type='button' name='remove' value='Remove' class='btn btn-warning' onclick='removeRow(this)' />" +
            "</td>" +
        "</tr>";
    $('#otherAttachTab').append(newRow);
}

//Chandana - 8441 - Added below function to remove the row
function removeRow(button) {
    $(button).closest("tr").remove();
    if ($("#otherAttachTab tr").length === 0)
        $("#addOtherAttachTab").hide();
}
function saveAttachment(button){
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	var action = "save";
	var description = document.getElementById("description").value.trim();
    var rmks = document.getElementById("additionalRemarks").value.trim();
    if (!description) {
        alert("Please enter description.");
        document.getElementById("description").focus();
        return;
    }
    if (!rmks) {
        alert("Please enter remarks.");
        document.getElementById("additionalRemarks").focus();
        return;
    }
    const fileInput = document.getElementById('attachment');
    var selectedFile = fileInput.files[0];    
    if (!selectedFile) {
        alert("Please upload file");
        return;
    }
    var fileName = selectedFile.name.toLowerCase();
    if (!(fileName.endsWith(".pdf") || fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {
        alert("Please upload the file in jpeg/ jpg/ png/ pdf formats only");
        fileInput.value = '';
        return;
    }
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to save?");
    if (confirmUpload) {
        var url = "/Operations/patientDetails.do?actionFlag=updateAdditnalAttach";
        var data = new FormData();
        data.append('attachment', selectedFile);
        data.append('fname', selectedFile.name);
        data.append('action', action);
        data.append('remarks', rmks);
        data.append('msg', description);
        data.append('patientNo', patientId);
        var xhttps = new XMLHttpRequest();
        xhttps.onload = function () {
            if (this.status === 200) {
                var responseMsg = JSON.parse(this.responseText);
                if (responseMsg === "success") {
                	alert("Record added successfully.");
                	fn_openOpdClaimCaseDtls();
                    fileInput.value = '';
                }else{
                	alert("Failed to add attachment.");
                    fileInput.value = '';
                    fn_removeLoadingImage();
                }
            }
            else{
            	alert("Error uploading file.");
            }
        };
        xhttps.open("POST", url, true);
        xhttps.send(data);
    }	
    else{
    	fn_removeLoadingImage();
    }
}
//Chandana - 8441 - Below function is to delete the attachment (making the active yn flag to N)
function deleteRow(seqId, action, remarks, description){
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	var action = "delete";
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to delete?");
    if (confirmUpload) {
	$.ajax({
		url:'/Operations/patientDetails.do?actionFlag=updateAdditnalAttach',
		data:'patientNo='+patientId+'&seqId='+seqId+'&action='+action+'&msg='+description+'&remarks='+remarks,
		type:'POST',
		success: function(data){
			var finalResult = JSON.parse(data);
			fn_removeLoadingImage();
			if (finalResult.length > 0) {
            	alert("Record deleted.");
            	//window.location.reload();
            	fn_openOpdClaimCaseDtls();
			}
		}
	});
    }	
    else{
    	fn_removeLoadingImage();
    }
}
function fn_loadImage(){
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage(){
	document.getElementById('processImagetable').style.display="none";  
}
function fn_CalcAmt() {
	var billAmount = ${billAmount != null ? billAmount : 0};
    //var deductAmt = document.getElementById('deductAmt').value;
    var deductAmtField = document.getElementById('deductAmt');
    var deductAmt = deductAmtField.value;
   if(deductAmt > billAmount){
	   alert("Deduction amount cannot be greater than initiated amount");
	   deductAmtField.value = '';
	   deductAmtField.focus();
	   document.getElementById('finalAmt').value = billAmount;
	   return;
   }
    var finalAmt = billAmount - (deductAmt ? parseFloat(deductAmt) : 0);
    if (finalAmt < 0) {
        finalAmt = 0;
    }
    document.getElementById('finalAmt').value = finalAmt.toFixed(2);
}
function fn_MainDashboard(){
	if(parent.parent.globalURl == ''){
		parentSearchUrl = '/Operations/patientDetails.do?actionFlag=getOpClaimCasesList';
		parent.setGlobalUrl(parentSearchUrl);
	}
	fn_loadImage();
	document.forms[0].action = '/Operations/patientDetails.do?actionFlag=getOpClaimCasesList';
	document.forms[0].submit();	
}
//Chandana - Commented below code, as it not working
 /* var totalDocuments = ${totalDocuments != null ? totalDocuments : 0};
if(totalDocuments > 0){
	var documentViewStatus = new Array(totalDocuments).fill(false);
	console.log("Doc: "+documentViewStatus);
}  */

function fn_medcoApproveOpClaim(args){//Chandana - 7845 - taking the input from function to differentiate submit or resubmit
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	if ($("#otherAttachTab tr.pending").length > 0) {//Chandana - 8441 - Before submitting the claim from medco, checked any additonal attachment is added and not filled
        alert("Please save or remove the row already added.");
        return;
    }
	var remarks = document.getElementById('remarks').value;
	if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
	if(remarks.length < 5){//Chandana - 8602 - Checking the length, min 5 chars
		alert("Remarks should be at least 5 characters long");
		return false;
	}
	if(patientId == null || patientId == ''){
		alert('No Patient Id is found');
		return false;
	}
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
	$.ajax({
		url:'/Operations/patientDetails.do?actionFlag=updateMedcoOpClaim',
		data:'patientId='+patientId+'&remarks='+remarks+'&status='+args,
		type:'POST',
		success: function(data){
			var finalResult = JSON.parse(data);
			fn_removeLoadingImage();
			if (finalResult.length > 0) {
            	alert('Case is submitted successfully for claims with Id:'+finalResult);
            	fn_MainDashboard();
			}
		}
	});
    }	
    else{
    	fn_removeLoadingImage();
    }
}
function fn_OpenOpClaimDocs(filePath, index) {	
      const url = "/Operations/patientDetails.do?actionFlag=viewOpClaimDoc&filePath="+filePath;
      fn_loadImage();
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          var response = this.responseText;
          var modalContent = document.getElementById("viewDocuments");
          var fileExt = filePath.substr(filePath.lastIndexOf('.') + 1).toLowerCase();
          fn_removeLoadingImage();
          if (fileExt === "pdf") {
            modalContent.innerHTML = '<iframe src="data:application/pdf;base64,'+response+'" frameBorder="0" scrolling="auto" height="500px " width="100%" style="visibility: visible; height:680px"></iframe>';
          } else if (fileExt === "jpg" || fileExt === "jpeg" || fileExt === "png" || fileExt === "gif") {
            modalContent.innerHTML = '<img src="data:image/jpeg;base64,' + response + '" style="height:500px; width:100%">';
          } else {
            modalContent.innerHTML = '<p>Unsupported file type</p>';
          }
          documentViewStatus[index - 1] = true;
          $('#myModal').modal('show');
        }
      };
      xhttp.open("GET", url, true);
      xhttp.send();
}
function fn_NimsOpClaimCasesforApproval(){
	 if(parent.parent.globalURl == ''){
			parentSearchUrl = '/Operations/patientDetails.do?actionFlag=getNimsOpClaimCasesforApproval';
			parent.setGlobalUrl(parentSearchUrl);
		}
		fn_loadImage();
		document.forms[0].action = '/Operations/patientDetails.do?actionFlag=getNimsOpClaimCasesforApproval';
		document.forms[0].submit();	
}
function fn_NimsOpClaim(args){
	const opdClaimNo = '${opdClaimNo}';
	const initiateAmnt = '${billAmount}';
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	let finalAmt = null;
	let deductAmt = '';
	var remarks = document.getElementById('CHremarks').value;
	if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
	if(remarks.length < 5){//Chandana - 8602 - Checking the length, min 5 chars
		alert("Remarks should be at least 5 characters long");
		return false;
	}
	const allViewed = allBills.every(bill => documentViewStatus[bill]);
	const allAddtionalViewed = allAddtionalDocs.every(seqId => documentViewStatus[String(seqId)]);//Chandana - 8441 - Added this for checking all the documents are checke dor not
    if (!allViewed) {
        bootbox.alert('Please view all documents');
        return false;
    }
    if(!allAddtionalViewed){//Chandana - 8441 - Added this to show the alert
    	bootbox.alert('Please view all addtional documents');
        return false;
    }
	if(args=='Return'){
		document.getElementById('deductAmt').value = '';
        document.getElementById('finalAmt').value = initiateAmnt;
        finalAmt = document.getElementById('finalAmt').value;
	}else if(args=='Approve' || args=='ReApprove'){ //Chandana - 8602 - Added Reapprove in the if condition
		finalAmt = document.getElementById('finalAmt').value;
		deductAmt = document.getElementById('deductAmt').value;
	}
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
	$.ajax({
		url:'/Operations/patientDetails.do?actionFlag=updateCHOpClaim',
		data:'status='+args+'&opdClaimNo='+opdClaimNo+'&finalAmt='+finalAmt+'&deductAmt='+deductAmt+'&remarks='+remarks+'&patientId='+patientId+'&initiateAmnt='+initiateAmnt,
		type: 'POST',
		success: function(data){
			var response = JSON.parse(data);
			fn_removeLoadingImage();
			if(response.length >0){
				if(args=='Approve' || args=='ReApprove')//Chandana - 8602 - Added Reapprove also to show the alert
					alert("Claim verified and forwarded to ACO");
				else if(args=='Return')
					alert("Claim returned to Medco");
				else
					alert("Claim rejected by CH");
				fn_NimsOpClaimCasesforApproval();
			}
		}
	});
    }
    else{
    	fn_removeLoadingImage();
    }
}

//Chandana - 8036 - new function for updating the claims for CEX role
function fn_CEXNimsOpClaim(args){
	const opdClaimNo = '${opdClaimNo}';
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	var remarks = document.getElementById('PEXremarks').value;
	const allViewed = allBills.every(bill => documentViewStatus[bill]);
	const allAddtionalViewed = allAddtionalDocs.every(seqId => documentViewStatus[String(seqId)]);//Chandana - 8441 - Added this for additional attachment mandatory click
    if (!allViewed) {
        bootbox.alert('Please view all documents');
        return false;
    }
    if(!allAddtionalViewed){//Chandana - 8441 - Added this if condition to show the mandatory alert for additional attachments
    	bootbox.alert('Please view all addtional documents');
        return false;
    }
	if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
	if(remarks.length < 5){//Chandana - 8602 - Added condition to check the remarks length
		alert("Remarks should be at least 5 characters long.");
		return false;
	}
	const finalAmt = '${billAmount}';
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
	 $.ajax({
		url:'/Operations/patientDetails.do?actionFlag=updatePEXOpClaim',
		data:'status='+args+'&opdClaimNo='+opdClaimNo+'&remarks='+remarks+'&patientId='+patientId+'&finalAmt='+finalAmt,
		type: 'POST',
		success: function(data){
			var response = JSON.parse(data);
			fn_removeLoadingImage();
			if(response.length >0){
				if(args=="Approve")
					alert("Claim verified and forwarded to CH");
				else if(args=="Return")
					alert("Claim returned to Medco");
				fn_NimsOpClaimCasesforApproval();
			}
		}
	});
    }
    else{
    	fn_removeLoadingImage();
    }
}
//Chandana - 7845 - for viewdocs
function viewDocument(filePath, billNo) {
    	window.open('/Operations/patientDetails.do?actionFlag=viewInvestigation&filePath=' + filePath+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
    	documentViewStatus[billNo] = true;
}
function viewAdditionalDocument(filePath, seqId) {//Chandana - 8441 - Written this function to open the attachment based on the path and seqId
    fn_loadImage();
    const url = "/Operations/patientDetails.do?actionFlag=viewDocuments&filePath=" + filePath;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            fn_removeLoadingImage();
            if (this.status == 200) {
                var response = this.responseText;
                var fileExt = filePath.substr(filePath.lastIndexOf('.') + 1).toLowerCase();
                /* var newWindow = window.open("", "_blank", "width=1150,height=700,resizable=yes,scrollbars=yes"); */
                var newWindow = window.open("", "_blank", "width=1150,height=700,resizable=yes,scrollbars=yes");
                var html = "<html><head><title>Document</title></head><body style='margin:0'>";
                if (fileExt === "pdf") {
                    html += '<embed src="data:application/pdf;base64,' + response + '" type="application/pdf" width="100%" height="100%"/>';
                } else if (fileExt === "jpg" || fileExt === "jpeg" || fileExt === "png" || fileExt === "gif") {
                    html += '<img src="data:image/jpeg;base64,' + response + '" style="width:100%;height:auto;"/>';
                } else {
                    html += "<p>Unsupported file type</p>";
                }

                html += "</body></html>";
                newWindow.document.open();
                newWindow.document.write(html);
                newWindow.document.close();
                documentViewStatus[String(seqId)] = true;
            } else {
                alert("Failed to load document. Please try again.");
            }
        }
    };

    xhttp.open("GET", url, true);
    xhttp.send();
}
function closeModal() {
	$('#myModal').modal('hide');
}
//Chandana - 8441 - Added below function to get the data after save or delete the additional attachment
function fn_openOpdClaimCaseDtls(){
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	const crNo = '<bean:write name="patientForm" property="crNumber"/>';//Chandana - 8606 - Added this to get the cr number
	fn_loadImage();
	document.forms[0].action='/Operations/patientDetails.do?actionFlag=getOpClaimCaseDtls&patientId='+patientId+'&crNo='+crNo;//Chandana - 8606 - Added cr number to url
	document.forms[0].submit();	
}
//Chandana - 8441 - To validate the uploaded file
function validateFile(input, flag) {//Chandana - 8559 - Added flag
    const file = input.files[0];
    var allowedExtensions = null;
    if (!file) return;
    if(flag == "A")//Chandana - 8599 - Added this if condition, as i am going to using the same function for addtional and missing attachments
    	allowedExtensions = ['jpeg', 'jpg', 'png', 'pdf'];
    else
    	allowedExtensions = ['pdf'];
    const fileName = file.name.toLowerCase();
    const fileSizeKB = file.size / 1024; // size in KB
    const fileExtension = fileName.split('.').pop();
    if (!allowedExtensions.includes(fileExtension) && flag == "A") {
        alert("Please upload the file in jpeg/ jpg/ png/ pdf formats only");
        input.value = ""; 
        return false;
    }
    else if(!allowedExtensions.includes(fileExtension) && flag == "M"){//Chandana - 8599 - Added this for showing the mandatory alert for missing attahcments
    	alert("Please upload the file in pdf format only");
        input.value = ""; 
        return false;
    }
    if (fileSizeKB > 200) {
        alert("File size must not exceed 200 KB.");
        input.value = "";
        return false;
    }
    return true;
}
let activeUploadRecord = null;//Chandana - 8599
//Chandana - 8599 - Added below toggle function to show the upload icon span
let lastUploadedIndex = 0;
function toggleUploadIcon(fileInput) {
    const uploadInputs = document.querySelectorAll("input[type='file'][name='attachment']");
    const uploadArray = Array.from(uploadInputs);
    const currentIndex = uploadArray.indexOf(fileInput) + 1;
    const uploadIcon = fileInput.parentElement.querySelector(".glyphicon-upload");
    if (currentIndex > lastUploadedIndex + 1) {
        alert("Please upload the records sequence wise");
        fileInput.value = "";
        return;
    }
    if (fileInput.files.length > 0) {
        activeUploadRecord = fileInput;
        if (uploadIcon) 
        	uploadIcon.style.display = "inline-block";
    } else {
        if (uploadIcon) uploadIcon.style.display = "none";
        activeUploadRecord = null;
    }
}

//Chandana - 8599 - Added below function to upload a file
function fn_uploadFile(billNo, sampleNo,seqId, crNo){
	var uploadIcon = document.getElementById("uploadIcon"); 
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	const fileInput = document.getElementById('attachment');
    var selectedFile = fileInput.files[0];    
    if (!selectedFile) {
        alert("Please upload file");
        return;
    }
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to save?");
	if (confirmUpload) {
		fn_loadImage();
        var url = "/Operations/patientDetails.do?actionFlag=uploadMissingAttachments";
        var data = new FormData();
        data.append('attachment', selectedFile);
        data.append('fname', selectedFile.name);
        data.append('patientNo', patientId);
        data.append('billNo', billNo);
        data.append('sampleNo', sampleNo);
        data.append('seqId', seqId);
        data.append('crNo', crNo);
        var xhttps = new XMLHttpRequest();
        xhttps.onload = function () {
            if (this.status === 200) {
                var responseMsg = JSON.parse(this.responseText);
                if (responseMsg === "success") {
                	alert("Record added successfully.");
                	activeUploadRecord = null;
                	fn_removeLoadingImage();
                	fn_openOpdClaimCaseDtls();
                    fileInput.value = '';
                }else{
                	alert("Failed to add attachment.");
                    fileInput.value = '';
                    uploadIcon.style.display = "none";
                    fn_removeLoadingImage();
                }
            }
            else{
            	alert("Error uploading file.");
            }
        };
        xhttps.open("POST", url, true);
        xhttps.send(data);
    }	
    else{
    	fn_removeLoadingImage();
    }
}
</script>
</html>
