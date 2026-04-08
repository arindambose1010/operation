<%
/**
 * PACKAGE        : Patient
 * FILENAME       : HighEndOncologySearch.jsp
 * FUNCTIONALITY  : Screen for HighEnd Oncology Committee Approval
 * @AUTHOR        : Sri Hari
 * DATE           : 12/12/24
 * Calls to other jsp/java  : 
 * Called by other jsp/java : 
 *-------------------------------------------------------------------------------------------------------------------------------------
 *   CR ID        DATE             AUTHOR                                       DESCRIPTION
 * ------------------------------------------------------------------------------------------------------------------------------------
 **/
%>
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
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Oncology High-End Drugs Committee Request Approval</title>
  
  <!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/select2.min.js"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
  <style type="text/css">
  /* Additional custom styles can be added here */
.radio-container label {
   margin-right: 20px;
}
.checkbox-row label {
  margin-right: 15px;
}
.btn{
border-radius:20px;
}
.modal-header{
background-color:#0286AD;
}
.btn:hover{
border-radius:5px;
}

*{margin:0;padding:0;}

 input:focus {
  outline:#000 dotted 1px; 
}    
select:focus{
  outline:#000 dotted 1px; 
} 
radio:focus{
  outline:#000 dotted 1px; 
}
textarea:focus{
  outline:#000 dotted 1px; 
}
checkbox:focus{
  outline:#000 dotted 1px; 
}

</style>

<style type="text/css">
*{margin:0;padding:0;}

 input:focus {
  outline:#000 dotted 1px; 
}  
.bootbox-close-button{
display:none;
}  
select:focus{
  outline:#000 dotted 1px; 
} 
radio:focus{
  outline:#000 dotted 1px; 
}
textarea:focus{
  outline:#000 dotted 1px; 
}
checkbox:focus{
  outline:#000 dotted 1px; 
}
body {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 12px;
line-height: 1.42857143;
color: #333;
background-color: #fff;
}
.col-xs-2, .col-sm-2, .col-md-2, .col-lg-2{
padding-right: 10px;
}

.action-buttons {
       display: flex;
       gap: 5px;  /* Space between buttons */
       justify-content: center;
       align-items: center;
}   
.remarks-input {
    width: 100%;
    padding: 4px 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}  
.action-btn {
    min-width: 80px;
    padding: 4px 12px;
}
.modal {
    overflow: auto !important;
}

body.modal-open {
    overflow: auto !important;
    padding-right: 0 !important;
}
</style>
<script>
</script>
</head>
<body onload="fn_removeLoadingImage();">
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" enctype="multipart/form-data">
    
<table class="tbheader">
<c:choose>
	<c:when test="${roleId eq 'GP2'}">
		<tr><th style="padding-left:32%"><b><fmt:message key="EHF.Title.MedicalOncologyHighEndDrugsCasesForPreauthInitiation"/></b></th></tr>
	</c:when>
	<c:otherwise>
		<tr><th style="padding-left:32%"><b><fmt:message key="EHF.Title.MedicalOncologyHighEndDrugsApprovalRequest"/></b></th></tr>
	</c:otherwise>
</c:choose>
</table>
<table width="100%">
<tr><td>
<table width="100%" class="tb">
<tr>
<td class="labelheading1 tbcellCss"><b>PatientNo</b></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="patientNo" maxlength="12" styleId="patientNo" onchange="validateNumber('Patient No',this)" title="Enter Patient No" style="WIDTH:70%;"/></td>
<%-- <td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientName"/></b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="patientName" maxlength="100" styleId="patientName" onchange="checkAlphaSpace('patientName','Patient Name')" title="Enter Patient Name" style="WIDTH: 10em;"/></td> --%>
<td class="labelheading1 tbcellCss"><b>HealthCardNo</b></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="cardNo" styleId="cardNo" maxlength="21" title="Enter Health Card No" onchange="validateHealthCard('Health Card No',this)" style="WIDTH: 70%;"/></td>
</tr>

<%-- <tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.FromDate"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 10em;"/></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ToDate"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 10em;"/></td>
</tr> --%>

<tr>
<td class="labelheading1 tbcellCss"><b>Cases Type</b></td>
<td class="tbcellBorder">
	<select id="caseType" name="caseType" style="width:50%">
		<option value="-1" selected>---Select---</option>
		<c:choose>
			<c:when test="${roleId eq 'GP2'}">
				<option value="InProgress">Approval In-progress</option>
				<option value="CmteRejected">Committe Rejected</option>
				<option value="PreauthInit">Preauth Initiated</option>
			</c:when>
			<c:otherwise>
				<option value="Y">Approval</option>
				<option value="N">Rejected</option>
			</c:otherwise>
		</c:choose>
	</select>
</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="right">  <button class="but"  type="button" onclick="fnSearch();">Search</button></td>
	<td colspan="2"> <button class="but"  type="button" onclick="resetSearch();">Reset</button></td>
</tr>
</table>
</td></tr>
</table>
    <br><br>
    
<div id="tableContainer">
<c:if test="${not empty oncologyApprovalCasesLst }">
<table id="oncologyApprovalTable" width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<thead><tr>
		<th class="tbheader1" valign="top" style="width:">S No</th>
		<th class="tbheader1" valign="top" style="width:">Patient Number</th>
		<th class="tbheader1" valign="top" style="width:">Patient Name</th>
		<th class="tbheader1" valign="top" style="width:">Card Number</th>		
		<th class="tbheader1" valign="top" style="width:">Hospital Name</th>
		<th class="tbheader1" valign="top" style="width:">Gender</th>
		<th class="tbheader1" valign="top" style="width:">Age</th>
		<th class="tbheader1" valign="top" style="width:">Registered Date</th>
		<th class="tbheader1" valign="top" style="width:">Prescription Proforma</th>
		<th class="tbheader1" valign="top" style="width:">Response Evaluation Assessment</th>
		<th class="tbheader1" valign="top" style="width:">Remarks</th>
		<th class="tbheader1" valign="top" style="width:">Action</th>
	</tr></thead>
	
	<tbody id="opdClaimCasesTableBody">
	<c:forEach var="oncologyCases" items="${oncologyApprovalCasesLst}" varStatus="loop">
	<tr>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.PATIENTID}</td>
		<%-- <span style="color:blue;cursor:pointer;" onclick="fn_openOpdClaimCaseDtls('${opClaimCases.PATIENTID}');">${opClaimCases.PATIENTID}</span> --%>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.PNAME}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.CARDNO}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.HOSPNAME}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.EGENDER}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.COUNT}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${oncologyCases.CRTDT}</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
			<%-- <button type="button" class="btn btn-info" id="proformaId" onclick="proformaForm('${oncologyCases.PATIENTID}')" data-toggle="modal" data-target="#proformaModal" title="Click Here To View Proforma Form">View<i class="fa fa-files-o"></i></button> --%>
			<button type="button" class="btn btn-info" id="proformaId" onclick="proformaForm('${oncologyCases.PATIENTID}','${oncologyCases.SEQID}')" title="Click Here To View Proforma Form">View<i class="fa fa-files-o"></i></button>			
		</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
			<c:if test="${oncologyCases.ONCOLOGYFLAG eq 'YES'}">
				<button type="button" class="btn btn-info" id="evolutionId" onclick="evaluationForm('${oncologyCases.PATIENTID}')" title="Click Here To View Evaluation Form">View<i class="fa fa-files-o"></i></button>
			</c:if>
		</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
			<input type="text" name="remarks_${oncologyCases.PATIENTID}" id="remarks_${oncologyCases.PATIENTID}" onkeyup="validateInput(event);">
		</td>
		<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
			<c:choose>
				<c:when test="${roleId eq 'GP2'}">
					<div class="action-buttons">
				        <button class="btn btn-primary has-spinner action-btn" type="button" onclick="fn_submitPreauthInit('${oncologyCases.PATIENTID}', '${oncologyCases.ONCOLOGYFLAG}','${oncologyCases.SEQID}')">Initiate Preauth</button>
				    </div>
				</c:when>
				<c:otherwise>
					<div class="action-buttons">
				        <button class="btn btn-primary has-spinner action-btn" type="button" onclick="fn_generateOTP('${oncologyCases.PATIENTID}','Approve', '${oncologyCases.ONCOLOGYFLAG}')"> Approve </button>
				        <button class="btn btn-danger has-spinner action-btn" type="button" onclick="fn_generateOTP('${oncologyCases.PATIENTID}','Reject', '${oncologyCases.ONCOLOGYFLAG}')"> Reject </button>
				    </div>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>

<c:if test="${empty oncologyApprovalCasesLst}">
	<div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group" id="noData" style="text-align:center">
		<h5><b>NO RECORDS FOUND</b></h5>
	</div>
</c:if>
</div>

<div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group" id="noData" style="text-align:center; display:none;">
		<h5><b>NO RECORDS FOUND</b></h5>
</div>
<!-- Progress Bar -->
<div id="processImagetable" style="top:50%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:400" >
   <tr>
      <td>
        <div id="processImage" align="center">
          <img src="images/Progress.gif" width="100" height="100" border="0"></img>
         </div>
       </td>
     </tr>
  </table>
</div>

<div class="modal fade" id="proformaModal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 900px;">
        <div class="modal-content">
            <div class="modal-header" style="height: 36px; color: #FFF;">
                <span class="glyphicon glyphicon-user"></span>
                <label>&nbsp;High End Oncology Drugs Proforma Form</label>
            </div>
            <div class="modal-body" style="height: 410px;">
                <iframe id="proformaFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" onclick="$('#proformaModal').modal('hide');">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="evaluationModal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 900px;">
        <div class="modal-content">
            <div class="modal-header" style="height: 36px; color: #FFF;">
                <span class="glyphicon glyphicon-user"></span>
                <label>&nbsp;Oncology Evaluation Response Form</label>
            </div>
            <div class="modal-body" style="height: 410px;">
                <iframe id="evaluationFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" onclick="$('#evaluationModal').modal('hide');">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="otpModal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header" style="height: 36px; color: #FFF; background-color: #007bff;">
                <span class="glyphicon glyphicon-lock"></span>
                <label>&nbsp;OTP Verification</label>
                <!-- <button type="button" class="close" onclick="cancelOtpVerification()" aria-label="Close">&times;</button> -->
            </div>
            <div class="modal-body" style="padding: 20px;">
                <p>Please enter the OTP sent to your registered contact number.</p>
                <input type="text" id="otpInput" class="form-control" maxlength="6" placeholder="Enter OTP" style="width: 100%; margin-bottom: 10px;" onkeyup="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 6);" />
                <div id="otpTimer" style="margin-bottom: 10px; color: red; font-weight: bold;">Time remaining: 5:00</div>
                <button id="verifyOtpBtn" class="btn btn-success" onclick="verifyOtp(); return false;">Verify OTP</button>
                <button id="resendOtpBtn" class="btn btn-link" onclick="resendOtp(); return false;" disabled>Resend OTP</button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" onclick="cancelOtpVerification()">Cancel</button>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="roleId" name="roleId" value="${roleId}">
</form>
</div> 
</body>

<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css" />
<link href="bootstrap/css/static.jquery.dataTables.css" rel="stylesheet">
<link href="bootstrap/css/buttons_dataTables.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootbox.js"></script>
<script type="text/javascript" src="bootstrap/js/bootbox.min.js"></script>
<script type="text/javascript" src="bootstrap/js/jquery-dataTables.min.js"></script>
<script type="text/javascript" src="bootstrap/js/dataTables-buttons.min.js"></script>
<script type="text/javascript" src="bootstrap/js/buttons-html5.min.js"></script>
<script type="text/javascript" src="bootstrap/js/vfs_fonts.js"></script>
<script type="text/javascript" src="bootstrap/js/jszip.min.js"></script>
<script type="text/javascript" src="bootstrap/js/pdfmake.min.js"></script>
<script src="bootstrap/js/static.jquery.dataTables.js"></script>

<script type="text/javascript">
var $ = jQuery.noConflict();
var todayDt = new Date();

$(document).ready(function(){
	$("#oncologyApprovalTable").DataTable({
		dom: 'lBfrtip',
	    buttons: [
	        {
	            extend: 'excelHtml5',
	            text: 'Export',
	            title: 'Oncology Request File'
	        }],
        columnDefs: [{
            "targets": '_all',
            "orderable": false
        }]
	});
});

function validateInput(event) {
    const inputField = event.target;
    let value = inputField.value;

    const regex = /^[a-zA-Z0-9/,.\s]*$/;

    if (!regex.test(value)) {
        inputField.value = value.slice(0, -1);
    }
}

function fn_loadImage(){
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage(){
	document.getElementById('processImagetable').style.display="none";  
}

var proformaViewed = {};
var evaluationViewed = {};

function proformaForm(patientId, proformaId) {
	fn_loadImage();
    var url = "patientDetails.do?actionFlag=getProformaFormHighEnd&proformaId=" + proformaId;
    var iframe = document.getElementById("proformaFrame");

    iframe.src = url;
    iframe.onload = function () {
        proformaViewed[patientId] = true;
        fn_removeLoadingImage();
    };

    $('#proformaModal').modal({
        backdrop: 'static',
        keyboard: false,
        show: true
    });
}

function evaluationForm(patientId) {
	fn_loadImage();
    var url = "patientDetails.do?actionFlag=getOncologyEvaluationResponse&patientId=" + patientId;
    var iframe = document.getElementById("evaluationFrame");

    iframe.src = url;
    iframe.onload = function () {
        evaluationViewed[patientId] = true;
        fn_removeLoadingImage();
    };

    $('#evaluationModal').modal({
        backdrop: 'static',
        keyboard: false,
        show: true
    });
}

var otpCountdown;
var remainingTime = 300; // 5 minutes in seconds
var currentPatientId = null;
var currentAction = null;

function fn_generateOTP(patientId, action, requiresEvaluation) {
	if (!proformaViewed[patientId]) {
        bootbox.alert("Please view the Proforma Form before submitting.");
        return;
    }

    // Check if evaluation form is required and viewed
    if (requiresEvaluation=='YES' && !evaluationViewed[patientId]) {
        bootbox.alert("Please view the Evaluation Response Form before submitting.");
        return;
    }
    var remarks = document.getElementById("remarks_" + patientId).value;

    if (remarks == null || remarks.trim() === '') {
        bootbox.alert("Please Enter the Remarks");
        return;
    }
    currentPatientId = patientId;
    currentAction = action;
    
    $.ajax({
        url: "./patientDetails.do?actionFlag=generateOTP",
        data: {
            patientId: patientId
        },
        type: "POST",
        success: function(response) {
        	var result = JSON.parse(response);
            if (result == "success") {
            	
                var remarksField = document.getElementById("remarks_" + patientId);
                remarksField.readOnly = true;

                var actionButtons = remarksField.closest('tr').querySelector('.action-buttons')/* .getElementsByTagName('button') */;
                for (var i = 0; i < actionButtons.length; i++) {
                    actionButtons[i].disabled = true;
                }
                
                $('#otpModal').modal('show');
                document.getElementById('otpInput').value = '';
                $('#otpModal').modal({
                    backdrop: 'static',
                    keyboard: false,
                    show: true
                });
                startOtpCountdown();

                document.getElementById('resendOtpBtn').disabled = true;
                remainingTime = 300; // Reset timer
            } else {
            	bootbox.alert("Error sending OTP. Please try again.");
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX Error: " + error);
            bootbox.alert("An error occurred while processing your request.");
        }
    });

}

function startOtpCountdown() {
	clearInterval(otpCountdown);
    otpCountdown = setInterval(function () {
        if (remainingTime <= 0) {
            clearInterval(otpCountdown);
            document.getElementById('otpTimer').innerHTML = "Time expired. You can resend the OTP.";
            document.getElementById('resendOtpBtn').disabled = false;
        } else {
            remainingTime--;
            var minutes = Math.floor(remainingTime / 60);
            var seconds = remainingTime % 60;
            document.getElementById('otpTimer').innerHTML = "Time remaining: " + minutes + ":" + (seconds < 10 ? '0' + seconds : seconds);
        }
    }, 1000);
}

function verifyOtp() {
    const otpInput = document.getElementById('otpInput').value.trim();

    if (otpInput.length !== 6 || isNaN(otpInput)) {
    	bootbox.alert("Please enter a valid 6-digit OTP.");
        return;
    }
 $.ajax({
        url: "./patientDetails.do?actionFlag=verifyOtp",
        data: {
            otp: otpInput,
            patientId: currentPatientId
        },
        type: "POST",
        success: function(response) {
        	var result = JSON.parse(response);
            if (result == "success") {
                bootbox.alert("OTP Verified Successfully!");
                    $('#otpModal').modal('hide');
                    fn_Submit(currentPatientId, currentAction);
               
            } else {
                bootbox.alert(result);
                document.getElementById('otpInput').value = '';
            }
        },
        error: function() {
            bootbox.alert("An error occurred during OTP verification.");
        }
    });
}

function resendOtp() {
    $.ajax({
        url: "./patientDetails.do?actionFlag=generateOTP",
        data: { patientId: currentPatientId },
        type: "POST",
        success: function(response) {
        	var result = JSON.parse(response);
            if (result === "success") {
                bootbox.alert("OTP Resent Successfully!");
                remainingTime = 300;
                startOtpCountdown();
                document.getElementById('otpInput').value = '';
                document.getElementById('resendOtpBtn').disabled = true;
            } else {
                bootbox.alert("Error resending OTP. Please try again.");
            }
        },
        error: function() {
            bootbox.alert("An error occurred while resending the OTP.");
        }
    });
}

function cancelOtpVerification() {
    clearInterval(otpCountdown);
    $('#otpModal').modal('hide');
    document.getElementById('otpInput').value = '';
    var remarksField = document.getElementById("remarks_" + currentPatientId);
    remarksField.readOnly = false;
    
    var actionButtons = remarksField.closest('tr').querySelector('.action-buttons');
    for (var i = 0; i < actionButtons.length; i++) {
        actionButtons[i].disabled = false;
    }
    bootbox.alert("OTP verification canceled. You can make changes again.");
}

function fn_Submit(patientId, status) {
    var remarks = document.getElementById("remarks_" + patientId).value;

    if (remarks == null || remarks.trim() === '') {
        bootbox.alert("Please Enter the Remarks");
        return;
    }

    $.ajax({
        url: "./patientDetails.do?actionFlag=saveOncologyCMTEResp",
        data: {
            patientId: patientId,
            remarks: remarks,
            status: status
        },
        type: "POST",
        success: function(response) {
        	var result = JSON.parse(response);
            if (result == "approved" || result == "rejected") {
            	
                var remarksField = document.getElementById("remarks_" + patientId);
                remarksField.readOnly = true;

                /* var buttonContainer = remarksField.parentElement.nextElementSibling; */
                var buttonContainer = remarksField.closest('tr').querySelector('.action-buttons');
                buttonContainer.innerHTML = "";

                var statusColor = status === "Approve" ? "green" : "red";
                buttonContainer.innerHTML = "<span style='color: " + statusColor + "; font-weight: bold;'>" + 
                    (status === "Approve" ? "Approved" : "Rejected") + 
                    "</span>";
            } else {
                bootbox.alert("Error: " + result);
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX Error: " + error);
            bootbox.alert("An error occurred while processing your request.");
        }
    });
}

function resetSearch(){
	parent.fn_highEndDrugsApproval();
}

function fnSearch(){
	var patientId = document.getElementById("patientNo").value;
	var cardNo = document.getElementById("cardNo").value;
	var caseType = document.getElementById("caseType").value;
	var roleId = '${roleId}';
	
	if(patientId=='' && cardNo=='' && caseType=='-1'){
		bootbox.alert("Please enter any search criteria");
		return;
	}
	
	fn_loadImage();
	 $.ajax({
	        url: "./patientDetails.do?actionFlag=highEndOncologySearch&ajaxFlag=Y",
	        data: {
	            patientId: patientId,
	            cardNo: cardNo,
	            caseType: caseType
	        },
	        type: "POST",
	        success: function(response) {
	        	 if (response.length > 0) {
	             	var finalResult = JSON.parse(response);
	             	$('#tableContainer').html('');
	             	$('#noData').hide();
	             	if(finalResult != null && finalResult.length > 0){
	                     
	                    var tableHTML = '<table id="oncologyApprovalTable" width="98%" cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">';
	     		        tableHTML += '<thead><tr>';
	     		        tableHTML += '<th class="tbheader1" valign="top">S No</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Patient Number</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Patient Name</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Card Number</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Hospital Name</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Gender</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Age</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Registered Date</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Prescription Proforma</th>';
	     		        tableHTML += '<th class="tbheader1" valign="top">Response Evaluation Assessment</th>';
	     		       if(roleId != 'GP2' && caseType == '-1'){
	     		    	  tableHTML += '<th class="tbheader1" valign="top">Remarks</th>';
		     		      tableHTML += '<th class="tbheader1" valign="top">Action</th>';
	     		       }else if(roleId == 'GP2' && caseType == 'CmteRejected'){
	     		    	  tableHTML += '<th class="tbheader1" valign="top">Remarks</th>';
	     		       }else if(roleId == 'GP2' && caseType == '-1'){
	     		    	  tableHTML += '<th class="tbheader1" valign="top">Remarks</th>';
		     		      tableHTML += '<th class="tbheader1" valign="top">Action</th>';
		     		   }
	     		        tableHTML += '</tr></thead><tbody id="OncoRows">';
	     		        
	     		       $.each(finalResult, function(index, oncologyCases) {
	   		            tableHTML += '<tr>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + (index + 1) + '</td>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		            			 oncologyCases.PATIENTID + '</td>';	   		            
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		                         oncologyCases.PNAME + '</td>';	   		            
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		                         oncologyCases.CARDNO + '</td>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		                         oncologyCases.HOSPNAME + '</td>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		                         oncologyCases.EGENDER + '</td>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		                         oncologyCases.COUNT + '</td>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
	   		                         oncologyCases.CRTDT + '</td>';
	   		            tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
	   		                         '<button type="button" class="btn btn-info" onclick="proformaForm(\'' + oncologyCases.PATIENTID + '\',\''+oncologyCases.SEQID+'\')"> View <i class="fa fa-files-o"></i></button></td>';
	   		            if(oncologyCases.ONCOLOGYFLAG == 'YES')
	   		            	tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
	   		                         '<button type="button" class="btn btn-info" onclick="evaluationForm(\'' + oncologyCases.PATIENTID + '\')"> View <i class="fa fa-files-o"></i></button></td>';
		                else
		                	 tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;"></td>';
	   		            
	                	 if(roleId != 'GP2' && caseType == '-1'){
	                		 tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
	                                   '<input type="text" name="remarks_' + oncologyCases.PATIENTID + '" id="remarks_' + oncologyCases.PATIENTID + '" onkeyup="validateInput(event)"></td>';	   		     
	                		 tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
				                         '<div class="action-buttons">' +
				                         '<button class="btn btn-primary has-spinner action-btn" type="button" onclick="fn_generateOTP(\'' + oncologyCases.PATIENTID + '\',\'Approve\',\''+oncologyCases.ONCOLOGYFLAG+'\')">Approve</button>' +
				                         '<button class="btn btn-danger has-spinner action-btn" type="button" onclick="fn_generateOTP(\'' + oncologyCases.PATIENTID + '\',\'Reject\',\''+oncologyCases.ONCOLOGYFLAG+'\')">Reject</button>' +
				                         '</div></td>';
	                	 }else if(roleId == 'GP2' && caseType == 'CmteRejected'){
	                		 tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + 
		                         oncologyCases.REMARKS + '</td>';
	                	 }else if(roleId == 'GP2' && caseType == '-1'){
	                		 tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
	                         			'<input type="text" name="remarks_' + oncologyCases.PATIENTID + '" id="remarks_' + oncologyCases.PATIENTID + '" onkeyup="validateInput(event)"></td>';	   		     
	                		 tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
				                        '<div class="action-buttons">' +
				                        '<button class="btn btn-primary has-spinner action-btn" type="button" onclick="fn_submitPreauthInit(\'' + oncologyCases.PATIENTID + '\',\''+oncologyCases.ONCOLOGYFLAG+'\',\''+oncologyCases.SEQID+'\')">Initiate Preauth</button>' +
				                        '</div></td>';
	                	 }
		               	/* if(caseType == -1){
		               		tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
		                         '<input type="text" name="remarks_' + oncologyCases.PATIENTID + '" id="remarks_' + oncologyCases.PATIENTID + '" onkeyup="validateInput(event)"></td>';	   		     
				            
		                    if(roleId == 'GP2')
				            	tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
		                        '<div class="action-buttons">' +
		                        '<button class="btn btn-primary has-spinner action-btn" type="button" onclick="fn_submitPreauthInit(\'' + oncologyCases.PATIENTID + '\',\''+oncologyCases.ONCOLOGYFLAG+'\',\''+oncologyCases.SEQID+'\')">Initiate Preauth</button>' +
		                        '</div></td>';
				            else	
				            	tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' +
				                         '<div class="action-buttons">' +
				                         '<button class="btn btn-primary has-spinner action-btn" type="button" onclick="fn_generateOTP(\'' + oncologyCases.PATIENTID + '\',\'Approve\',\''+oncologyCases.ONCOLOGYFLAG+'\')">Approve</button>' +
				                         '<button class="btn btn-danger has-spinner action-btn" type="button" onclick="fn_generateOTP(\'' + oncologyCases.PATIENTID + '\',\'Reject\',\''+oncologyCases.ONCOLOGYFLAG+'\')">Reject</button>' +
				                         '</div></td>';
		               	} */	   		            	   		            
	   		            tableHTML += '</tr>';
	   		        });
	   		        
	   		        tableHTML += '</tbody></table>';
	   		        
	   		        $('#tableContainer').html(tableHTML);
		   		     $('#oncologyApprovalTable').DataTable({
				            dom: 'lBfrtip',
				            buttons: [{
				                extend: 'excelHtml5',
				                text: 'Excel',
				                title: 'OncologyApprovalList'
				            }],
				            columnDefs: [{
				                "targets": '_all',
				                "orderable": false
				            }]
				        });
				        $('#oncologyApprovalTable').show();
	             	}else{
	             		$('#noData').show();
	             	}
	        	 }
	        	 fn_removeLoadingImage();
	        },
	        error: function(xhr, status, error) {
	            console.error("AJAX Error: " + error);
	            bootbox.alert("An error occurred while processing your request.");
	        }
	 });
}


function fn_submitPreauthInit(patientId, requiresEvaluation, profromaId) {
	if (!proformaViewed[patientId]) {
        bootbox.alert("Please view the Proforma Form before submitting.");
        return;
    }

    if (requiresEvaluation=='YES' && !evaluationViewed[patientId]) {
        bootbox.alert("Please view the Evaluation Response Form before submitting.");
        return;
    }
    var remarks = document.getElementById("remarks_" + patientId).value;

    if (remarks == null || remarks.trim() === '') {
        bootbox.alert("Please Enter the Remarks");
        return;
    }
    fn_loadImage();
    document.forms[0].action = "./patientDetails.do?actionFlag=savePreauthInitforOncologyCases&patientId="+patientId+"&proformaId="+profromaId+"&remarks="+remarks;
    document.forms[0].method="POST";
    document.forms[0].submit();
}
</script>

</html>
</fmt:bundle>