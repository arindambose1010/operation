<%
/**
* FILENAME     : dlhJournalistClaimCases.jsp
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
<%@ page import="java.util.ArrayList,
				java.util.List,
                 java.util.Locale,
                 java.util.Hashtable,
                 java.util.HashMap,
                 java.util.Enumeration,
                 java.util.StringTokenizer,
                 java.util.ResourceBundle,
				 com.ahct.claims.util.ClaimCases,
                 org.json.JSONArray" %>
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
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
</head>
<body onload="fn_removeLoadingImage();">
<form action="/patientDetails.do" method="post" enctype="multipart/form-data">
<br>
<div> 
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         <span class="glyphicon glyphicon-plus"></span>
         	<span><b>Delhi Journalist Claim Cases</b></span>
		</a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse"> 
    <div class="panel-body">
    <table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" >
	<tr><td>
	<table width="100%" class="tb">
	<tr><td>
	<table width="100%" >
	<tr>
		<td width="16%" class="labelheading1 tbcellCss"><b>Patient Number</b></td>
		<td width="16%" class="tbcellBorder"><input type="text" id="patientNo" name="patientNo" style="width:180px;" maxlength="50" onkeyup="validateInput(event)"></td>
		<td  width="16%" class="labelheading1 tbcellCss"><b>Card Number</b></b></td>
		<td width="16%" class="tbcellBorder"><input type="text" id="cardNo" name="cardNo" style="width:180px;" maxlength="50"></td>
		<!-- <td width="16%" class="labelheading1 tbcellCss"><b>Letter Number</b></td>
		<td width="16%" class="tbcellBorder"><input type="text" id="letterNo" name="letterNo" style="width:180px;" maxlength="50" onkeyup="validateInput(event)"></td> -->
		<td width="16%" class="labelheading1 tbcellCss"><b>Claim Status</b></td>
		<td width="16%" class="tbcellBorder">
	  		<select id="claimsStatus" name="claimsStatus" style="width:180px;">
	  			<option value="-1">--Select--</option>
			    <c:forEach var="status" items="${claimStatusList}">
			        <option value="${status.ID}">${status.VALUE}</option>
			    </c:forEach>
	  		</select>
		</td>
	</tr>
	<tr>
		<td width="16%" class="labelheading1 tbcellCss"><b>Claim Submitted No</b></td>
		<td width="16%" class="tbcellBorder"><input type="text" id="claimSubmittedNo" name="claimSubmittedNo" style="width:180px;" maxlength="50"></td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Claims Date</b></td>
		<td width="16%" class="tbcellBorder"><input type="text" id="claimDate" name="claimDate" style="width:150px;" maxlength="50" onkeyup="validateInput(event)"></td>
	</tr>
	<tr><td>&nbsp;</td>
	</tr>	
	<tr><td align="center" colspan="6">
	<button class="but" type="button" name="Search" value="Search" onclick="javascript:fn_search()">Search</button>
	<button class="but" type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()">Reset</button>
	</td>
	</tr></table></td></tr>
	</table>
</td></tr>
</table>
      </div>
    </div>
  </div>
</div>
<br><br>
<div id="tableContainer">
<c:if test="${not empty opClaimCasesList }">
<table id="opClaimCasesTable" width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<thead><tr>
		<th class="tbheader1" valign="top" style="width:">S No</th>
		<th class="tbheader1" valign="top" style="width:">Patient Number</th>
		<th class="tbheader1" valign="top" style="width:">Card Number</th>
		<th class="tbheader1" valign="top" style="width:">Patient Name</th>
		<!-- <th class="tbheader1" valign="top" style="width:">Letter Number</th> -->
		<th class="tbheader1" valign="top" style="width:">Total Amount</th>
		<th class="tbheader1" valign="top" style="width:">Claim Date</th>
		<th class="tbheader1" valign="top" style="width:">Claim Status</th>
	</tr></thead>
	<tbody id="opdClaimCasesTableBody">
	<%
	    List<String> casesBlocked = ClaimCases.getBlockedCasesList();
	    request.setAttribute("casesBlocked", casesBlocked);
	%>
	<c:forEach var="opClaimCases" items="${opClaimCasesList}" varStatus="loop">
    <tr>
    	<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${loop.index + 1}</td>
        <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">
        	<c:set var="isBlocked" value="false" />
        	<c:forEach var="blockedId" items="${casesBlocked}">
                <c:if test="${opClaimCases.PATIENTID == blockedId}">
                    <c:set var="isBlocked" value="true" />
                </c:if>
            </c:forEach>
            <c:choose>
                <c:when test="${isBlocked}">
                    <span style="color:gray;cursor:not-allowed;" title="This case has already been opened by other user"><img style="width:10%;" src="images/lock1.png"/>${opClaimCases.PATIENTID}</span>
                </c:when>
                <c:otherwise>
                    <span style="color:blue;cursor:pointer;"onclick="fn_openDlhJrnlClaimCaseDtls('${opClaimCases.PATIENTID}','${opClaimCases.CARDNO}','${opClaimCases.ID}');">${opClaimCases.PATIENTID}</span>
                </c:otherwise>
            </c:choose>
        </td>
        <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.CARDNO}</td>
        <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.PNAME}</td>
        <%-- <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.AMOUNT}</td> --%>
        <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${empty opClaimCases.AMOUNT || opClaimCases.AMOUNT == 0 ? '' : opClaimCases.AMOUNT}</td>
        <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.CLAIMDT}</td>
        <td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">${opClaimCases.VALUE}</td>
    </tr>
	</c:forEach>
</tbody>
	
</table>
</c:if>
<c:if test="${empty opClaimCasesList}">
	<div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group" id="noData" style="text-align:center">
		<h5><b>NO RECORDS FOUND</b></h5>
	</div>
</c:if>
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
</body>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css" />
<link href="bootstrap/css/static.jquery.dataTables.css" rel="stylesheet">
<link href="bootstrap/css/buttons_dataTables.css" rel="stylesheet">
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
	$('#claimDate').datepicker({
		format: 'dd/mm/yyyy',
		autoclose: true,
    	endDate: todayDt,
    	orientation: 'top auto',
		todayHighlight: true,
		defaultDate: "+1w",
		changeMonth: true,
		changeYear: true,
		showOn: "both", 
        buttonImage: "images/calend.gif", 
        buttonText: "Calendar",
        buttonImageOnly: true ,
		numberOfMonths: 1,
		maxDate: new Date(y, m, d),
		yearRange: '2000:' + new Date().getFullYear(),
	});
	$('#opClaimCasesTable').DataTable({
        dom: 'lBfrtip',
        buttons: [{
            extend: 'excelHtml5',
            text: 'Excel',
            title: 'opClaimCasesList'
        }],
        columnDefs: [{
            "targets": '_all',
            "orderable": false
        }],
        pageLength: 15
    });  
	document.getElementById('claimsStatus').style.display = 'block';
	document.getElementById('claimsStatus-input').style.display = 'none';
});
function fn_loadImage(){
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage(){
	document.getElementById('processImagetable').style.display="none";  
}
function validateInput(event) {
    const inputField = event.target;
    let value = inputField.value;
    const regex = /^[a-zA-Z0-9 ,.]*$/;
    if (!regex.test(value)) {
        inputField.value = value.slice(0, -1);
    }
}
 var blockedCases = [<%
        List<String> casesBlocked = ClaimCases.getBlockedCasesList();
        for(int i = 0; i < casesBlocked.size(); i++){
            out.print("'" + casesBlocked.get(i) + "'");
            if(i != casesBlocked.size() - 1) out.print(",");
        }
    %>];
function fn_search(){
	var patientId = document.getElementById('patientNo').value;
	var cardNo = document.getElementById('cardNo').value.trim(); 
	var claimDt = document.getElementById('claimDate').value;
	var claimSubmittedNo = document.getElementById('claimSubmittedNo').value;
	var claimStatus = document.getElementById('claimsStatus').value;
	if(claimStatus == '-1' && patientId.trim() == '' && cardNo.trim() == '' && claimDt == '' && claimSubmittedNo ==''){
		jqueryAlertMsg('Cases Search','Please select any search criteria');
		return false;		
	}
	if(claimDt){
		var dateParts = claimDt.split('/');
		claimDt = dateParts[1] + '/' + dateParts[0] + '/' + dateParts[2];
	}
	fn_loadImage();
	$.ajax({
        type: "POST",
        url: "/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=Y",
        data: {
            patientId: patientId,
            cardNo: cardNo,
            claimDt: claimDt,
            claimSubmittedNo: claimSubmittedNo,
            claimStatus: claimStatus,
        },
        success: function(data) {
        	var responseData = JSON.parse(data);
            $('#tableContainer').html('');
            if (responseData && responseData.length > 0) {
                var tableHTML = '<table id="opClaimCasesTable" width="98%" cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">';
                tableHTML += '<thead><tr>';
                tableHTML += '<th class="tbheader1" valign="top">S No</th>';
                tableHTML += '<th class="tbheader1" valign="top">Patient Number</th>';
                tableHTML += '<th class="tbheader1" valign="top">Claim number</th>';
                tableHTML += '<th class="tbheader1" valign="top">Card Number</th>';
                tableHTML += '<th class="tbheader1" valign="top">Patient Name</th>';
                tableHTML += '<th class="tbheader1" valign="top">Total Amount</th>';
                tableHTML += '<th class="tbheader1" valign="top">Claim Date & Time</th>';
                tableHTML += '<th class="tbheader1" valign="top">Claim Status</th>';
                tableHTML += '</tr></thead><tbody id="opdClaimCasesTableBody">';
                $.each(responseData, function(index, opClaimCases) {
                    tableHTML += '<tr>';
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + (index + 1) + '</td>';
                    var isBlocked = blockedCases.indexOf(String(opClaimCases.PATIENTID)) !== -1;
                    if(isBlocked){
                        tableHTML += '<td class="tbcellBorder" style="text-align:center;">' +
                                     '<span style="color:gray;cursor:not-allowed;" title="This case has already been opened by other user"><img style="width:10%;" src="images/lock1.png"/> ' + opClaimCases.PATIENTID +
                                     '</span></td>';
                    } else {
                        tableHTML += '<td class="tbcellBorder" style="text-align:center;">' +
                                     '<span style="color:blue;cursor:pointer;" onclick="fn_openDlhJrnlClaimCaseDtls(\'' + opClaimCases.PATIENTID + '\', \'' + opClaimCases.CARDNO + '\');">' + opClaimCases.PATIENTID + '</span></td>';
                    }
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + (opClaimCases.CLAIMNO ? opClaimCases.CLAIMNO : '') +  '</td>';
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + opClaimCases.CARDNO + '</td>';
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + opClaimCases.PNAME + '</td>';
                    /* tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + opClaimCases.AMOUNT + '</td>'; */
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + (opClaimCases.AMOUNT == 0 ? '' : opClaimCases.AMOUNT) + '</td>';
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + (opClaimCases.CLAIMDT ? opClaimCases.CLAIMDT : '') + '</td>';
                    tableHTML += '<td class="tbcellBorder" style="word-wrap:break-word;padding:3px;text-align: center;">' + opClaimCases.VALUE + '</td>';
                    tableHTML += '</tr>';
                });
                tableHTML += '</tbody></table>';
                $('#tableContainer').html(tableHTML);
                $('#opClaimCasesTable').DataTable({
                    dom: 'lBfrtip',
                    buttons: [{
                        extend: 'excelHtml5',
                        text: 'Excel',
                        title: 'opClaimCasesList'
                    }],
                    columnDefs: [{
                        "targets": '_all',
                        "orderable": false
                    }],
                    pageLength: 15 
                });
                $('#opClaimCasesTable').show();
            } else {
                var noDataHTML = '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group" id="noData" style="text-align:center">';
                noDataHTML += '<h5><b>NO RECORDS FOUND</b></h5></div>';
                $('#tableContainer').html(noDataHTML);
            }
            fn_removeLoadingImage();
        },
        error: function() {
            jqueryAlertMsg('Cases Search', 'An error occurred while processing your request.');
        }
    });
}
function fn_reset(){
	document.getElementById('patientNo').value = '';
    document.getElementById('cardNo').value = '';
    document.getElementById('claimDate').value = '';
    document.getElementById('claimSubmittedNo').value = '';
    document.getElementById('claimsStatus').value = '-1';
	document.getElementById('claimDate').value='';
	if(parent.parent.globalURl == ''){
		parentSearchUrl = '/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=N';
		parent.setGlobalUrl(parentSearchUrl);
	}
	fn_loadImage();
	document.forms[0].action='/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=N';
	document.forms[0].submit();
}

function fn_openDlhJrnlClaimCaseDtls(patientId, cardNo, type){
	fn_loadImage();
	if(patientId == null || patientId == '')
		return false;
	if(parent.parent.globalURl == ''){
		parentSearchUrl = '/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCaseDtls&patientId='+patientId+'&cardNo='+cardNo;
		parent.setGlobalUrl(parentSearchUrl);
	}
	fn_loadImage();
	document.forms[0].action='/Operations/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCaseDtls&patientId='+patientId+'&cardNo='+cardNo+'&type='+type;
	document.forms[0].submit();
}
</script>
</html>
