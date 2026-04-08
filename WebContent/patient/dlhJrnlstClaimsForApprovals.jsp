<%
/**
* FILENAME     : dlhJrnlstClaimsForApprovals.jsp
* @AUTHOR      : Chandana Daruri	
* --------------------------------------------------------------
* Change Id       AUTHOR          DESCRIPTION
* ---------------------------------------------------------------
*  9033		   Chandana Daruri    Jsp to view the Delhi journalist claim cases List for approvals for cex and CH
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
<form action="/patientDetails.do" method="post" name="patientForm" enctype="multipart/form-data">
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
	<c:if test="${not empty claimNo}">
		<c:set var="claimNo" value="${claimNo}"/>
		<td width="15%" class="labelheading1 tbcellCss"><b>OP Claim Number</b></td>
		<td width="15%" class="tbcellBorder"><b> &nbsp;${claimNo}</b></td>
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
	<c:if test="${not empty admissionDtls }"><!-- Chandana - 9045 - Added this for showing the admission details -->
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
			</td><!-- ${admDtls.ATTACHPATH} -->
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</c:if>
	<c:if test="${not empty attachDtls}">
    <table class="tbheader"><tr><td><b>Mandatory Attachments</b></td></tr></table>
    <table width="100%" class="tb">
        <thead>
            <tr>
                <th class="tbheader1">S No</th>
                <th class="tbheader1">Type</th>
                <th class="tbheader1">Action</th>
            </tr>
        </thead>
        <script type="text/javascript">
			var allBills = [];
		    var documentViewStatus = {};
		    <c:forEach var="attachDtls" items="${attachDtls}">
		    	allBills.push("${attachDtls.COUNT}");
		        documentViewStatus["${attachDtls.COUNT}"] = false;
		    </c:forEach>
		</script>
        <tbody>
        <c:set var="prevId" value="" />
        <c:set var="serial" value="0" />
        <c:forEach var="row" items="${attachDtls}">
            <c:if test="${prevId ne row.ID}">
                <c:set var="serial" value="${serial + 1}" />
                <tr>
                    <td class="tbcellBorder" style="text-align:center;">${serial}</td>
                    <td class="tbcellBorder" style="text-align:center;">${row.VALUE}</td>
                    <td class="tbcellBorder" style="text-align:center;">
                        <!-- Print all attachments belonging to this ID -->
                        <c:forEach var="att" items="${attachDtls}">
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
<!-- For Additional attachments -->
<script>
	var allAddtionalDocs = [];
	var documentViewStatus = {};
</script>
<c:if test="${not empty additionalAttach}">
<table class="tbheader"><tr><td><b>Additional Attachments View</b></td></tr></table>
<table id="addtionalAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
	<thead><tr>
		<th class="tbheader1" valign="top" style="width:">S No</th>
		<th class="tbheader1" valign="top" style="width:">Type</th>
		<th class="tbheader1" valign="top" style="width:">Attachment</th>
		</tr></thead>
		<!-- Chandana - 8441 - Added the below script for madatory attchments for PEX and CH -->
	<script type="text/javascript">
		  <c:forEach var="addtnalAttach" items="${additionalAttach}">
		        allAddtionalDocs.push("${addtnalAttach.COUNT}");
		        documentViewStatus["${addtnalAttach.COUNT}"] = false;
		    </c:forEach>
	</script>
	<tbody id="additionalAttachTabBody">
	<c:forEach var="additionalAttach" items="${additionalAttach}" varStatus="loop">
		<tr>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${additionalAttach.VALUE}</td>
			<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
				<span style="color:blue;cursor:pointer;" onclick='viewAdditionalDocument("${additionalAttach.ATTACHPATH}", "${additionalAttach.COUNT}")'>View</span>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	
	<c:if test="${not empty attachDtls}">
	<table align="center" style="margin-top:1%;">
    <tbody>
        <tr>
	        <td class="labelheading1 tbcellCss" style="width:10%"><b>Estimated Amount</b></td>
	        <td class="tbcellBorder" style="width:10%"><b>&nbsp;${estmtdAmnt}</b></td>
	        <td class="labelheading1 tbcellCss" style="width:10%"><b>Initiated Amount</b></td>
	        <td class="tbcellBorder" style="width:10%"><b>&nbsp;${billAmount}</b></td>
            <c:choose>
                <c:when test="${roleId == 'GP9' || roleId == 'GP29'}">
                    <td class="labelheading1 tbcellCss"><b>Deduction Amount</b></td>
		            <c:if test="${claimStatus ne 'CD7357' }"><td class="tbcellBorder"><input type="number" id="deductAmt" name="deductAmt" value="0" min="0" oninput="this.value = this.value.replace(/[^\d]/g, '')" onchange="fn_CalcAmt()"></td></c:if>
		            <c:if test="${claimStatus eq 'CD7357' }"><td class="tbcellBorder"><input type="number" id="deductAmt" name="deductAmt" value="${deductedAmount}" min="0" oninput="this.value = this.value.replace(/[^\d]/g, '')" onchange="fn_CalcAmt()"></td></c:if>
		            <td class="labelheading1 tbcellCss"><b>Final Amount</b></td>
		            <c:if test="${claimStatus ne 'CD7357' }"><td class="tbcellBorder"><input type="number" id="finalAmt" name="finalAmt" value="${billAmount}" readonly></td></c:if>
		            <c:if test="${claimStatus eq 'CD7357' }"><td class="tbcellBorder"><input type="number" id="finalAmt" name="finalAmt" value="${approvedAmount}" readonly></td></c:if>
                </c:when>
                <c:otherwise>
                	<td></td>
                	<!-- <td></td>
                	<td></td>
                	<td></td> -->
                </c:otherwise>
            </c:choose>
            <c:if test="${roleId eq 'GP9' || roleId eq 'GP29'}">
            	<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
            </c:if>
            <c:if test="${roleId eq 'GP3' }">
            	<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
            </c:if>
            <td>
                <c:choose>
				    <c:when test="${not empty claimNo && roleId eq 'GP3'}">
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
            <c:when test="${roleId == 'GP3'}"><!-- Chandana - 7845 - added this condition for PEX -->
	        	<tr>
	        	<c:if test="${claimStatus eq 'CD7352' }">
		           <td style="padding-left:45%;">
		               <button class="btn btn-primary has-spinner" type="button" id="gp3Submit" onclick="fn_CEXDlhJrnlstClaim('Approve');">Approve</button>
		           </td>
		            <td style="padding-left:43%;">
		                <button class="btn btn-warning has-spinner" type="button" id="gp3Return" onclick="fn_CEXDlhJrnlstClaim('Return');">Pending</button>
		            </td>
	            </c:if>
	            <c:if test="${claimStatus eq 'CD7364' }">
	            	<td style="padding-left:45%;">
		               <button class="btn btn-primary has-spinner" type="button" id="gp3Submit" onclick="fn_CEXDlhJrnlstClaim('Approve');">Approve</button>
		           </td>
	            </c:if>
	        </tr>
    	</c:when>
        <c:otherwise>
        	<tr>
            	<td colspan="4"></td>
				<td colspan="5" style="text-align: center;padding-top:1%; padding-right:2%;">
					<c:if test="${claimStatus eq 'CD7353' || claimStatus eq 'CD7355' }">
						<button class="btn btn-primary has-spinner" type="button" id="CHSubmit" onclick="fn_CHDlhJrnlstClaim('Approve');">Approve</button>
					</c:if>
					<c:if test="${claimStatus eq 'CD7357'}">
						<button class="btn btn-primary has-spinner" type="button" id="CHSubmit" onclick="fn_CHDlhJrnlstClaim('ReApprove');">Approve</button>
					</c:if>
				    <button class="btn btn-danger has-spinner" type="button" id="CHcancel" onclick="fn_CHDlhJrnlstClaim('Reject');">Reject</button>
				    <c:if test="${claimStatus eq 'CD7353'}">
				    	<button class="btn btn-warning has-spinner" type="button" id="CHReturn" onclick="fn_CHDlhJrnlstClaim('Return');">Pending</button>
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
	<!-- Chandana - 9033 - Changes Start for claims workflow -->
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
		parentSearchUrl = '/Operations/patientDetailsNew.do?actionFlag=getDlhClaimCasesforApproval';
		parent.setGlobalUrl(parentSearchUrl);
	}
	fn_loadImage();
	document.forms[0].action = '/Operations/patientDetailsNew.do?actionFlag=getDlhClaimCasesforApproval';
	document.forms[0].submit();	
}
function fn_CHDlhJrnlstClaim(args){
	const claimNo = '${claimNo}';
	const initiateAmnt = '${billAmount}';
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	let finalAmt = null;
	let deductAmt = '';
	const claimStatus = '${claimStatus}';
	var remarks = document.getElementById('CHremarks').value;
	if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
	if(remarks.length < 5){
		alert("Remarks should be at least 5 characters long");
		return false;
	}
    const allViewed = allBills.every(bill => documentViewStatus[bill]);
     if (!allViewed) {
         bootbox.alert('Please view all documents');
         return false;
     }
 // Check only if additional documents exist
    if (allAddtionalDocs.length > 0) {
        const allAddtionalViewed = allAddtionalDocs.every(seqId => documentViewStatus[String(seqId)]);
        if (!allAddtionalViewed) {
            bootbox.alert('Please view all additional documents');
            return false;
        }
    }
	if(args=='Return'){
		document.getElementById('deductAmt').value = '';
        document.getElementById('finalAmt').value = initiateAmnt;
        finalAmt = document.getElementById('finalAmt').value;
	}else if(args=='Approve' || args=='ReApprove'){
		finalAmt = document.getElementById('finalAmt').value;
		deductAmt = document.getElementById('deductAmt').value;
	}
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
	$.ajax({
		url:'/Operations/patientDetailsNew.do?actionFlag=updDlhJrnlstCHClaim',
		data:'status='+args+'&claimNo='+claimNo+'&finalAmt='+finalAmt+'&deductAmt='+deductAmt+'&remarks='+remarks+'&patientId='+patientId+'&initiateAmnt='+initiateAmnt+'&claimStatus='+claimStatus,
		type: 'POST',
		success: function(data){
			var response = JSON.parse(data);
			fn_removeLoadingImage();
			if(response.length >0){
				if(args=='Approve' || args=='ReApprove')
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
//Chandana - 9033 - new function for updating the claims for CEX role
function fn_CEXDlhJrnlstClaim(args){
	const claimNo = '${claimNo}';
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	var remarks = document.getElementById('PEXremarks').value;
	const claimStatus = '${claimStatus}';
    const allViewed = allBills.every(bill => documentViewStatus[bill]);
    if (!allViewed) {
        bootbox.alert('Please view all documents');
        return false;
    }
    if (allAddtionalDocs.length > 0) {
        const allAddtionalViewed = allAddtionalDocs.every(seqId => documentViewStatus[String(seqId)]);
        if (!allAddtionalViewed) {
            bootbox.alert('Please view all additional documents');
            return false;
        }
    }
	if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
	if(remarks.length < 5){
		alert("Remarks should be at least 5 characters long.");
		return false;
	}
	const finalAmt = '${billAmount}';
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
	 $.ajax({
		url:'/Operations/patientDetailsNew.do?actionFlag=updCEXDlhJrnlstClaim',
		data:'status='+args+'&claimNo='+claimNo+'&remarks='+remarks+'&patientId='+patientId+'&finalAmt='+finalAmt+'&claimStatus='+claimStatus,
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
function viewDocument(filePath, billNo) {
    	window.open('/Operations/patientDetails.do?actionFlag=viewInvestigation&filePath=' + filePath+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
    	documentViewStatus[billNo] = true;
}
function viewAdditionalDocument(filePath, seqId) {
    fn_loadImage();
    const url = "/Operations/patientDetails.do?actionFlag=viewDocuments&filePath=" + filePath;
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
function fn_openOpdClaimCaseDtls(){
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	const crNo = '<bean:write name="patientForm" property="crNumber"/>';//Chandana - 22-10-2025 - Added this to get the cr number
	fn_loadImage();
	document.forms[0].action='/Operations/patientDetails.do?actionFlag=getOpClaimCaseDtls&patientId='+patientId+'&crNo='+crNo;//Chandana - 22-10-25- Added cr number to url
	document.forms[0].submit();	
}
</script>
</html>
