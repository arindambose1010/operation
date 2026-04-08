<%
/**
* FILENAME     : dlhJrnlstClaimCaseDtls.jsp
* @AUTHOR      : Chandana Daruri	
* --------------------------------------------------------------
* Change Id       AUTHOR          DESCRIPTION
* ---------------------------------------------------------------
*  8755		   Chandana Daruri    Jsp to view the Delhi journalist claim cases List
*    
* --------------------------------------------------------------
**/
%>
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
<title>Delhi Journalist Claim Cases</title>
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
.input-group-addon{
	padding: 6px 12px;
	font-size: 14px;
    font-weight: 400;
    color: #555;
    text-align: center;
    border-radius: 4px;
}
</style>
</head>
<body onload="fn_removeLoadingImage()">
<div id="middleFrame_content">
<form action="/patientDetailsNew.do" method="post" name="patientForm" enctype="multipart/form-data">
<br>
<c:choose>
<c:when test="${dataIsPresent == 'Y' }">
	<table width="95%" style="margin:0 auto">
	<tr><td colspan="4">
	<table class="tbheader">
	<tr><td><b>&nbsp;&nbsp;&nbsp;Card Details</b></td></tr>
	</table>
	<table width="100%">
	<tr>
		<td width="15%" class="labelheading1 tbcellCss"><b>Health Card Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
		<td width="2%">&nbsp;</td>
		<td width="15%" class="labelheading1 tbcellCss"><b>Patient Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
		<td width="2%">&nbsp;</td>
	<c:if test="${not empty jrnlstDClaimNum}">
		<c:set var="jrnlstDClaimNum" value="${jrnlstDClaimNum}"/>
		<td width="15%" class="labelheading1 tbcellCss"><b>Claim Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;${jrnlstDClaimNum}</b></td>
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
	  <div style="height:20em;overflow:hidden" id='cardAddressField'>
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
	 <div style="height:20em;overflow:hidden" id='commAddressField'>
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
	<fieldset style="height:20em;" id='photoField'>
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
	<table class="tbheader" style="margin-top:3%;">
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
	<c:set var="billAmount" value="${claimAmount}"/>
	<c:if test="${not empty provisionalDtls }">
		<table class="tbheader"><tr><td><b>Category Details</b></td></tr></table>
		<table id="addtionalAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
		<thead><tr>
			<th class="tbheader1" valign="top" style="width:">S No</th>
			<th class="tbheader1" valign="top" style="width:">Category</th>
			<th class="tbheader1" valign="top" style="width:">Diagnosis</th>
		</tr></thead>
		<tbody>
			<c:forEach var="provDtls" items="${provisionalDtls}" varStatus="loop">
			<tr>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${provDtls.LVL}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${provDtls.VALUE}</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</c:if>
	<c:if test="${not empty admissionDtls }">
		<table class="tbheader"><tr><td><b>Admission Details</b></td></tr></table>
		<table id="addtionalAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
		<thead><tr>
			<th class="tbheader1" valign="top" style="width:">S No</th>
			<th class="tbheader1" valign="top" style="width:">Admission Date</th>
			<th class="tbheader1" valign="top" style="width:">Admission Note</th>
			<th class="tbheader1" valign="top" style="width:">Attachment</th>
		</tr></thead>
		<tbody>
			<c:forEach var="admDtls" items="${admissionDtls}" varStatus="loop">
			<tr>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${admDtls.ID}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${admDtls.VALUE}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
				<span style="color:blue;cursor:pointer;" onclick='viewAdditionalDocument("${admDtls.ATTACHPATH}", "${admDtls.PATIENTID}")'>View</span>
			</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</c:if>
	<c:if test="${not empty mandateAttachList}">
    <table class="tbheader"><tr><td><b>Mandatory Attachments</b></td></tr></table>
    <table width="100%" class="tb">
        <thead>
            <tr>
                <th class="tbheader1">S No</th>
                <th class="tbheader1">Type</th>
                <th class="tbheader1">Attachments</th>
            </tr>
        </thead>
        <tbody>
        <c:set var="prevId" value="" />
        <c:set var="serial" value="0" />
        <c:forEach var="row" items="${mandateAttachList}">
            <c:if test="${prevId ne row.ID}">
                <c:set var="serial" value="${serial + 1}" />
                <tr>
                    <td class="tbcellBorder" style="text-align:center;">${serial}</td>
                    <td class="tbcellBorder" style="text-align:center;">${row.VALUE}</td>
                    <td class="tbcellBorder" style="text-align:center;">
                        <!-- Print all attachments belonging to this ID -->
                        <c:forEach var="att" items="${mandateAttachList}">
                            <c:if test="${att.ID eq row.ID}">
                                <div>
                                    <span style="color:blue;cursor:pointer;"
                                          onclick='viewAdditionalDocument("${att.ATTACHPATH}", "${att.COUNT}")'>View
                                          <%-- ${att.ATTACH} --%>
                                    </span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <c:set var="prevId" value="${row.ID}" />
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</c:if>
	<c:if test="${not empty attachmentsList}">
	<c:if test="${empty mandateAttachList}">
<table class="tbheader"><tr><td><b>Mandatory Attachments</b></td></tr></table>
<table id="addtionalAttachTab" width="100%" cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
    <thead>
        <tr>
            <th class="tbheader1" valign="top" style="width:">S No</th>
            <th class="tbheader1" valign="top" style="width:">Type</th>
            <th class="tbheader1" valign="top" style="width:">Attachments</th>
        </tr>
    </thead>
    <tbody id="additionalAttachTabBody">
    <c:forEach var="mandateAttach" items="${attachmentsList}" varStatus="loop">
        <tr data-id="${mandateAttach.ID}">
            <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
            <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${mandateAttach.VALUE}</td>
            <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;"> 
                <c:choose>
                    <c:when test="${mandateAttach.ID eq 'CD7347'}">
                        <input type="file" name="benAttach[0]"
                               data-type="CD7347"
                               accept=".pdf,.jpeg,.jpg,.png"
                               onchange="validateFile(this)">
                    </c:when>
                    <c:when test="${mandateAttach.ID eq 'CD7348'}">
                        <input type="file" name="caseSheetAttach[0]"
                               data-type="CD7348"
                               accept=".pdf,.jpeg,.jpg,.png"
                               onchange="validateFile(this)">
                    </c:when>
                    <c:when test="${mandateAttach.ID eq 'CD7350'}">
                        <input type="file" name="invAttach[0]"
                               data-type="CD7350"
                               accept=".pdf,.jpeg,.jpg,.png"
                               onchange="validateFile(this)">
                    </c:when>
                    <c:when test="${mandateAttach.ID eq 'CD7349'}">
                        <input type="file" name="selfCertAttach[0]"
                               data-type="CD7349"
                               accept=".pdf,.jpeg,.jpg,.png"
                               onchange="validateFile(this)">
                    </c:when>
                    <c:otherwise>
                        <input type="file" name="dischargeAttach[0]"
                               data-type="CD012"
                               accept=".pdf,.jpeg,.jpg,.png"
                               onchange="validateFile(this)">
                    </c:otherwise>
                </c:choose>
                <span>
                    <button type="button" name="Add" class="btn btn-primary" style="margin-top: -20px;" value="+" onclick="addElement(this,'${mandateAttach.ID}')">+</button>
                    <button type="button" name="Add" class="btn btn-primary" value="-" style="margin-top: -20px;" onclick="removeElement(this, '${mandateAttach.ID}')">-</button>
                </span>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>	
</c:if>
	</c:if>
	<table id="addOtherAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;display:none;" class="tb">
	<thead><tr>
		<!-- <th class="tbheader1" valign="top" style="width:">S No</th> -->
		<th class="tbheader1" valign="top" style="width:">Remarks</th>
		<th class="tbheader1" valign="top" style="width:">Upload</th>
		<th class="tbheader1" valign="top" style="width:">Action</th>
	</tr></thead>
	<tbody id="otherAttachTab"></tbody>
	</table>
	<c:if test="${not empty additionalDJHSAttach}">
	<table class="tbheader"><tr><td><b>Additional Attachments</b></td></tr></table>
	<table id="addtionalAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
		<thead><tr>
			<th class="tbheader1" valign="top" style="width:">S No</th>
			<th class="tbheader1" valign="top" style="width:">Type</th>
			<th class="tbheader1" valign="top" style="width:">Remarks</th>
			<th class="tbheader1" valign="top" style="width:">Attachment</th>
            <c:if test="${claimsStatus eq 'CD7351' || claimsStatus eq 'CD7363' || claimsStatus eq 'CD7354' }">
				<th class="tbheader1" valign="top" style="width:">Action</th>
			</c:if>
		</tr></thead>
		<script type="text/javascript">
		    var allAddtionalDocs = [];
		    var documentViewStatus = {};
		    <c:forEach var="addtnalAttach" items="${additionalAttach}">
		        allAddtionalDocs.push("${addtnalAttach.COUNT}");
		        documentViewStatus["${addtnalAttach.COUNT}"] = false;
		    </c:forEach>
		</script>
		<tbody id="additionalAttachTabBody">
		<c:forEach var="addtnalAttach" items="${additionalDJHSAttach}" varStatus="loop">
		<tr>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${addtnalAttach.VALUE}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${addtnalAttach.CONST}
			</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
				<span style="color:blue;cursor:pointer;" onclick='viewAdditionalDocument("${addtnalAttach.ATTACHPATH}", "${addtnalAttach.COUNT}")'>View</span>
			</td>
            <c:if test="${claimsStatus eq 'CD7351' || claimsStatus eq 'CD7363' || claimsStatus eq 'CD7354' }">
				<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;"><button class="btn btn-danger" type="button" onclick='deleteRow("${addtnalAttach.COUNT}" , "D","${addtnalAttach.REMARKS}","${addtnalAttach.VALUE}"  )'>Delete</button></td>
			</c:if>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<table><tbody><tr>
		<td colspan="6"></td>
		<c:if test="${claimsStatus eq 'CD7354' || claimsStatus eq 'CD7351' || claimsStatus eq 'CD7363'}">
			<td align="center"><span><button class="btn btn-primary" type="button" onclick="addRow()" style="margin-top: 1%;">Add Attachments</button></span></td>
		</c:if>
	</tr></tbody></table>
	<table align="center">
    <tbody>
        <tr>
        	<td class="labelheading1 tbcellCss" style="width:10%"><b>Estimated Amount</b></td>
	        <td class="tbcellBorder" style="width:10%"><input type="number" id="estmtdAmount" name="estmtdAmount" value="${estmtdAmnt}" readonly></td>
        	<td class="labelheading1 tbcellCss" style="width:10%"><b>Initiated Amount</b></td>
        	<c:if test="${billAmount eq null}" ><td class="tbcellBorder"><input type="number" id="initiateAmount" name="initiateAmount" value="0" maxlength="9" oninput="this.value = this.value.replace(/[^\d]/g, '')" onchange="checkLength(this);"></td></c:if>
        	<c:if test="${billAmount ne null}" >
        		<%-- <c:if test="${claimsStatus eq 'CD7354' || claimsStatus eq 'CD7363' }"> --%><td class="tbcellBorder" style="width:14%;"><input type="number" id="initiateAmount" name="initiateAmount" value="${billAmount}" readonly></td><%-- </c:if> --%>
        	</c:if>
	        <c:if test="${claimsStatus eq 'CD7354' || claimsStatus eq 'CD7363' }"> 
	        	<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
	        </c:if>
	        <c:if test="${claimsStatus eq 'CD7351' }">
	        	<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
	        </c:if>
            <td>
                <c:choose>
				    <c:when test="${empty jrnlstDClaimNum}">
				        		<span><textarea type="text" id="remarks" name="remarks" style="width: 343px; height: 38px;" maxlength="1000" onkeyup="validateInput(event)"></textarea></span>
				    </c:when>
				    <c:when test="${not empty jrnlstDClaimNum}">
				    	<c:if test="${claimsStatus eq 'CD7354' || claimsStatus eq 'CD7363'}">
				        		<span><textarea type="text" id="remarks" name="remarks" style="width: 343px; height: 38px;" maxlength="1000" onkeyup="validateInput(event)"></textarea></span>
				        </c:if>
				    </c:when>
				</c:choose>
            </td>
        </tr>
        <c:choose>
        	<c:when test="${empty jrnlstDClaimNum}">
        		<tr>
        			<td colspan="2"></td>
        			<c:if test="${not hasMissingAttachment}">
        				<td style="padding-left:26%;">
                         	<button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button>
                        </td>
                        <td style="padding-left:9%;"> 
                            <button class="btn btn-primary has-spinner" type="button" id="Submit" onclick="medcoSubmitDlhJrnsltClaim('Submit');">Submit</button>
                        </td>
                    </c:if>
                </tr>
             </c:when>
             <c:when test="${not empty jrnlstDClaimNum}">
             	<tr>
             		<td style="padding-left:51%;"><button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button></td>
                    <c:if test="${claimsStatus eq 'CD7354' || claimsStatus eq 'CD7363'}">
	                	<td style="padding-left:34%;"><button class="btn btn-primary has-spinner" type="button" id="Submit" onclick="medcoSubmitDlhJrnsltClaim('ReSubmit');">Submit</button></td>
                    </c:if>
                </tr>
            </c:when>
           	<c:otherwise>
           	<tr>
                <td colspan="8" style="text-align: center;">
                    <button class="btn btn-danger has-spinner" type="button" id="back" onclick="fn_MainDashboard();" style="margin-left:1000%">Back</button>
                </td>
            </tr>
           	</c:otherwise>
         </c:choose>
   	 </tbody>
	</table>
	</td></tr>
	</table>
	<c:if test="${not empty jrnlstDworkFlowDtls}">
		<table class="tbheader" style="width:95%; margin-left:2.5%;">
			<tr><td><b>Claim Approval Workflow</b></td></tr>
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
			<c:forEach var="wrkFlwDtls" items="${jrnlstDworkFlowDtls}" varStatus="loop">
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
var additionalAttachCount = "${fn:length(additionalAttach)}";
function addRow() {
    if (additionalAttachCount >= 5) {
        alert("Maximum limit of 5 additional attachments is exceeded");
        return;
    }
    if ($("#otherAttachTab tr.pending").length > 0) {
        alert("Please save or remove the row already added.");
        return;
    }
    $('#othrMainTab').show();
    $('#addOtherAttachTab').show();

    let newRow =
        "<tr class='pending'>" +
            "<td><input type='text' name='remarks' class='form-control additionalRemarks'></td>" +
            "<td>" +
                "<input type='file' name='attachment' class='form-control-file attachment' " +
                "accept='.pdf, .jpeg, .jpg, .png' onchange=\"validateFile(this,'A')\">" +
                "<span style='display:block; color: red; font-size: 12px; margin-top: 2px;'>Allowed formats: .pdf, .jpeg, .jpg, .png (Max size: 200 KB)</span>" +
            "</td>" +
            "<td>" +
                "<input type='button' name='save' value='Save' class='btn btn-primary' onclick='saveAttachment(this)' style='margin-right:5px;' />" +
                "<input type='button' name='remove' value='Remove' class='btn btn-warning' onclick='removeRow(this)' />" +
            "</td>" +
        "</tr>";

    $('#otherAttachTab').append(newRow);
}

function removeRow(button) {
    $(button).closest("tr").remove();
    if ($("#otherAttachTab tr").length === 0)
        $("#addOtherAttachTab").hide();
}
function saveAttachment(button) {
    const patientId = '<bean:write name="patientForm" property="patientNo"/>';
    var action = "save";
    var row = button.closest("tr");
    var remarksInput = row.querySelector(".additionalRemarks");
    var rmks = remarksInput.value.trim();
    if (!rmks) {
        alert("Please enter remarks.");
        remarksInput.focus();
        return;
    }
    var fileInput = row.querySelector(".attachment");
    if (!fileInput || fileInput.files.length === 0) {
        alert("Please upload file");
        return;
    }
    var selectedFile = fileInput.files[0];
    // File type validation
    var fileName = selectedFile.name.toLowerCase();
    if (!(fileName.endsWith(".pdf") || fileName.endsWith(".png") ||
          fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {
        alert("Please upload the file in jpeg/ jpg/ png/ pdf formats only");
        fileInput.value = '';
        return;
    }

    fn_loadImage();
    var confirmUpload = confirm("Are you sure you want to save?");
    if (!confirmUpload) {
        fn_removeLoadingImage();
        return;
    }
    var url = "/Operations/patientDetailsNew.do?actionFlag=updDlhJrnlAdditnalAttach";
    var data = new FormData();

    data.append('attachment', selectedFile);
    data.append('fname', selectedFile.name);
    data.append('action', action);
    data.append('remarks', rmks);
    data.append('patientNo', patientId);
    var xhttps = new XMLHttpRequest();
    xhttps.onload = function () {
        if (this.status === 200) {
            var responseMsg = JSON.parse(this.responseText);
            if (responseMsg === "success") {
                alert("Record added successfully.");
                fn_openDlhJrnlClaimCaseDtls();
                fileInput.value = '';
            } else {
                alert("Failed to add attachment.");
                fileInput.value = '';
                fn_removeLoadingImage();
            }
        } else {
            alert("Error uploading file.");
        }
    };
    xhttps.open("POST", url, true);
    xhttps.send(data);
}

function deleteRow(seqId, action, remarks, description){
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	var action = "delete";
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to delete?");
    if (confirmUpload) {
	$.ajax({
		url:'/Operations/patientDetailsNew.do?actionFlag=updDlhJrnlAdditnalAttach',
		data:'patientNo='+patientId+'&seqId='+seqId+'&action='+action+'&msg='+description+'&remarks='+remarks,
		type:'POST',
		success: function(data){
			var finalResult = JSON.parse(data);
			fn_removeLoadingImage();
			if (finalResult.length > 0) {
            	alert("Record deleted.");
            	fn_openDlhJrnlClaimCaseDtls();
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

function fn_MainDashboard(){
	if(parent.parent.globalURl == ''){
		parentSearchUrl = '/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=N';
		parent.setGlobalUrl(parentSearchUrl);
	}
	fn_loadImage();
	document.forms[0].action = '/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=N';
	document.forms[0].submit();	
}

function medcoSubmitDlhJrnsltClaim(args) {
    const patientId = '<bean:write name="patientForm" property="patientNo"/>';
    let remarks = document.getElementById('remarks').value.trim();
    let initAmnt = document.getElementById('initiateAmount').value;
    let estmtdAmount = document.getElementById('estmtdAmount').value;
    var claimNo = '${jrnlstDClaimNum}';
    let formData = new FormData();
	if(args == 'Submit'){
	    $("tr[data-id]").each(function () {
	        let typeId = $(this).data("id");
	        let inputs = $(this).find("input[type='file']");
	        let hasFile = false;
	        let index = 0;
	        inputs.each(function () {
	            if (this.files.length > 0) {
	                hasFile = true;
	                let file = this.files[0];
	                let fieldName = "";
	                switch (typeId) {
	                    case "CD012": fieldName = "dischargeAttach[" + index + "]"; break;
	                    case "CD7347": fieldName = "benAttach[" + index + "]"; break;
	                    case "CD7348": fieldName = "caseSheetAttach[" + index + "]"; break;
	                    case "CD7349": fieldName = "selfCertAttach[" + index + "]"; break;
	                    case "CD7350": fieldName = "invAttach[" + index + "]"; break;
	                }
	                formData.append(fieldName, file);
	                index++;
	            }
	        });
	        if (!hasFile) {
	            alert("Please upload all mandatory attachments.");
	            throw "Missing attachment";
	        }
	        formData.append("attachTypeIds", typeId);
	    });
	}
    if(initAmnt == '0'){
    	bootbox.alert("Initiated amount should be greater than 0");
    	return false;
    }
    if(initAmnt < 3){
    	bootbox.alert("Initiated amount must be 3 digits");
    	return false;
    }
    if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
    if (remarks.length < 5) {
        alert("Remarks should be at least 5 characters long");
        return;
    }
    formData.append("patientId", patientId);
    formData.append("remarks", remarks);
    formData.append("action", args);
    formData.append("initAmnt", initAmnt);
    formData.append("claimNo", claimNo);
    formData.append("estmtdAmount", estmtdAmount);
    var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
    	fn_loadImage();
	    $.ajax({
	        url: "/Operations/patientDetailsNew.do?actionFlag=updateMedcoDlhJrnlstClaim",
	        type: "POST",
	        data: formData,
	        contentType: false,
	        processData: false,
	        success: function (data) {
	            let finalResult = JSON.parse(data);
	            alert("Case submitted successfully with Claim Number: " + finalResult);
	            fn_removeLoadingImage();
	            fn_MainDashboard();
	        }
	    });
    }else{
    	fn_removeLoadingImage();
    }
}

function viewAdditionalDocument(filePath, seqId) {
    fn_loadImage();
    const url = "/Operations/patientDetailsNew.do?actionFlag=viewDocuments&filePath=" + filePath;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            fn_removeLoadingImage();
            if (this.status == 200) {
                var response = this.responseText;
                var fileExt = filePath.substr(filePath.lastIndexOf('.') + 1).toLowerCase();
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
function fn_openDlhJrnlClaimCaseDtls(){
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	const cardNo = '<bean:write name="patientForm" property="crNumber"/>';
	fn_loadImage();
	document.forms[0].action='/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCaseDtls&patientId='+patientId+'&cardNo='+cardNo;
	document.forms[0].submit();
}
function validateFile(input, flag) {
    const file = input.files[0];
    var allowedExtensions = null;
    if (!file) return;
    if(flag == "A")
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
    else if(!allowedExtensions.includes(fileExtension) && flag == "M"){
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

function addElement(button, typeId) {
    let row = $(button).closest('td');
    let count = row.find("input[type='file'][data-type='" + typeId + "']").length;
    if (count >= 5) {
        alert("Maximum 5 files allowed for this attachment type.");
        return;
    }
    let newIndex = count;

    let newInput = $('<input type="file" accept=".pdf,.jpeg,.jpg,.png">')
        .attr("name", typeIdToFieldName(typeId, newIndex))
        .attr("data-type", typeId)
        .addClass("file-input")
        .on("change", function () { validateFile(this); });

    row.append("<br/>");
    row.append(newInput);
}

function typeIdToFieldName(typeId, index) {
    switch(typeId) {
        case "CD102": return "dischargeAttach[" + index + "]";
        case "CD7347": return "benAttach[" + index + "]";
        case "CD7348": return "caseSheetAttach[" + index + "]";
        case "CD7349": return "selfCertAttach[" + index + "]";
        case "CD7350": return "invAttach[" + index + "]";
        default: return "dischargeAttach[" + index + "]";
    }
}

function removeElement(button, typeId) {
    let row = $(button).closest("td");
    let inputs = row.find("input[type='file'][data-type='" + typeId + "']");
    if (inputs.length <= 1) {
        alert("At least one attachment is required for this record.");
        return;
    }
    inputs.last().prev("br").remove();
    inputs.last().remove();
    row.find("input[type='file'][data-type='" + typeId + "']").each(function (idx) {
        $(this).attr("name", typeIdToFieldName(typeId, idx));
    });
}

function checkLength(){
	
}

</script>
</html>
