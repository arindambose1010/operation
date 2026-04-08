<%
/**
* FILENAME     : opClaimCaseDtlsACO.jsp
* @AUTHOR      : Chandana Daruri	
* --------------------------------------------------------------
* Change Id       AUTHOR          DESCRIPTION
* ---------------------------------------------------------------
*  8602		   Chandana Daruri    Jsp to view the OPD claim case
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
<body onload="fn_removeLoadingImage();">
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
						<td width="11%" class="labelheading1 tbcellCss"><b>CR Number</b></td>
						<td width="11%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="crNumber"/></b></td>
						<td width="2%">&nbsp;</td>
						<c:if test="${not empty opdClaimNo}">
							<c:set var="opdClaimNo" value="${opdClaimNo}"/>
							<td width="15%" class="labelheading1 tbcellCss"><b>OP Claim Number</b></td>
							<td width="15%" class="tbcellBorder"><b> &nbsp;${opdClaimNo}</b></td>
						</c:if>
					</tr>
				</table><br>
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
									</td></tr>
								</table>
							</fieldset>
						</td></tr>
				</table><br>
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
			</table><br>
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
				<th class="tbheader1" valign="top" style="width:">Bill Date&Time</th>
				<th class="tbheader1" valign="top" style="width:">Received Date&Time</th>
				<th class="tbheader1" valign="top" style="width:">Attachment</th>
				<th class="tbheader1" valign="top" style="width:">Quantity</th>
				<th class="tbheader1" valign="top" style="width:">Amount</th>
			</tr></thead>
			<tbody id="opdClaimCasesTableBody">
				<c:forEach var="opClaimCases" items="${opClaimBillDtls}" varStatus="loop">
				<c:set var="billAmount" value="${opClaimCases.TOTALAMT}"/>
				<c:set var="lastBillNo" value="${opClaimCases.BILLNO}" />
				<tr>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.BILLNO}</td>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.TYPE}</td>
					<c:if test="${roleId ne 'GP9' or roleId ne 'GP3' or roleId ne 'GP29'}"><td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.SAMPLENO}</td></c:if>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.BILLDT}</td>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.CRTDT}</td>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
				    	<c:choose>
					        <c:when test="${not empty opClaimCases.ATTACHPATH}">
					            <span style="color:blue;cursor:pointer;" onclick='viewDocument("${opClaimCases.ATTACHPATH}", "${opClaimCases.BILLNO}")'>
					                View
					            </span>
					        </c:when>
				    	</c:choose>
					</td>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.COUNT}</td>
					<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.AMOUNT}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
<!-- Chandana - 8934 - New div for shwoing the consolidated attachment-->
<table class="tbheader">
	<tr><td><b>Consolidated Bill Details</b></td></tr>
</table>
<c:if test="${empty conslidateBill}">
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

	<c:if test="${not empty additionalAttach}">
		<table class="tbheader"><tr><td><b>Additional Attachments</b></td></tr></table>
		<table id="addtionalAttachTab" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;width:100%;" class="tb">
			<thead><tr>
				<th class="tbheader1" valign="top" style="width:">S No</th>
				<th class="tbheader1" valign="top" style="width:">Type</th>
				<th class="tbheader1" valign="top" style="width:">Description</th>
				<th class="tbheader1" valign="top" style="width:">Remarks</th>
				<th class="tbheader1" valign="top" style="width:">Attachment</th>
			</tr></thead>
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
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<table align="center">
    	<tbody>
        	<tr>
	            <td class="labelheading1 tbcellCss" style="width:10%"><b>Initiated Amount</b></td>
	            <td class="tbcellBorder" style="width:10%"><b style="Margin-left:34%">&nbsp;${billAmount}</b></td>
	            <td class="labelheading1 tbcellCss" style="width:10%"><b>Approved Amount</b></td>
	            <td class="tbcellBorder" style="width:10%"><b style="Margin-left:34%">&nbsp;${approvedAmount}</b></td>
        		<td class="labelheading1 tbcellCss"><b>Remarks</b></td>
            	<td>
	        		<textarea type="text" id="ACOremarks" name="ACOremarks" style="width: 343px; height: 38px;" maxlength="500" onkeyup="validateInput(event)"></textarea>
            	</td>
        	</tr>
        	<tr>	
				<td style="padding-left:44%"><button class="btn btn-warning has-spinner" type="button" id="CHReturn" onclick="fn_buttonClicked('Return');">Return to CH</button></td>
				<td style="padding-left:45%"><button class="btn btn-danger has-spinner" type="button" id="cancel" onclick="fn_MainDashboard();">Back</button></td>		           
    		</tr>
   	 	</tbody>
	</table>
	</td></tr>
	</table>
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

function viewDocument(filePath, billNo) {
    	window.open('/Operations/patientDetails.do?actionFlag=viewInvestigation&filePath=' + filePath+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
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

function fn_MainDashboard(){
	if(parent.parent.globalURl == ''){
		parentSearchUrl = 'ClaimsFlow.do?actionFlag=getNimsOpdClaimCasesforACOApproval';
		parent.setGlobalUrl(parentSearchUrl);
	}
	fn_loadImage();
	document.forms[0].action = 'ClaimsFlow.do?actionFlag=getNimsOpdClaimCasesforACOApproval';
	document.forms[0].submit();	
}
function fn_buttonClicked(action){
	const opdClaimNo = '${opdClaimNo}';
	const patientId = '<bean:write name="patientForm" property="patientNo"/>';
	var remarks = document.getElementById('ACOremarks').value;
	if(remarks == null || remarks == ''){
		bootbox.alert('Please Enter Remarks');
		return false;
	}
	const finalAmt = '${approvedAmount}';
	fn_loadImage();
	var confirmUpload = confirm("Are you sure you want to proceed?");
    if (confirmUpload) {
	 $.ajax({
		url:'/Operations/ClaimsFlow.do?actionFlag=opClaimPendingByACO',
		data:'action='+action+'&opdClaimNo='+opdClaimNo+'&remarks='+remarks+'&patientId='+patientId+'&finalAmt='+finalAmt,
		type: 'POST',
		success: function(data){
			var response = JSON.parse(data);
			fn_removeLoadingImage();
			if(response.length >0){
				if(action=="Return")
					alert("Claim returned to CH");
				fn_MainDashboard();
			}
		}
	});
    }
    else{
    	fn_removeLoadingImage();
    }
}
</script>
</html>
