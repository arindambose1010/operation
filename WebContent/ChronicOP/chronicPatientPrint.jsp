<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
  	 <%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Care Fund</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style>
.btn
{
border-radius:20px;
}
.btn:hover
{
border-radius:5px;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
var s$=jQuery.noConflict();

s$(function(){
   /*
    * this swallows backspace keys on any non-input element.
    * stops backspace -> back
    */
   var rx = /INPUT|SELECT|TEXTAREA/i;

   s$(document).bind("keydown keypress", function(e){
       if( e.which == 8 ){ // 8 == backspace
           if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
               e.preventDefault();
           }
       }
   });
});

function generatePrint(patientId)
{
	refreshParentPage();
	window.open('./patientDetails.do?actionFlag=viewPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
}
function close()
{
	refreshParentPage();
}

function generateCasePrint()
{
	var chronicId=document.getElementById("chronicID").value;

var chronicNo=document.getElementById("chronicNo").value;
var saveFlag="${saveFlag}";
var cancelFlag="${cancelFlag}";
	//var caseValues=caseId.split("/");
	//var caseNo=caseValues[2];    
	if(saveFlag=="Yes" || cancelFlag=="Y")
	{

		refreshParentPage();
	}
	else
	{
		window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');	
	//document.forms[0].method="post";
	//document.forms[0].submit();
	//refreshParentPage();
	
}}

function  refreshParentPage()
{
parent.fn_chronicOPPatientsView();	
}
</script>
<script>
function generateDtrsPrint()
{
	var chronicId=document.getElementById("chronicID").value;
	var chronicNo=document.getElementById("chronicNo").value;
	window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&DTRS=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');	

}
</script>
</head>
<body>
<form action="/chronicAction.do" method="post">
<br><br><br><br><br><br><center><font size="3">
<logic:notEmpty name="chronicOpForm" property="chronicID">
<b>Chronic OP </b> <bean:write name="chronicOpForm" property="msg"/> <b><bean:write name="chronicOpForm" property="chronicNo"/></b><br><br><br>
<button class="btn btn-success"  type="button" data-toggle="tooltip" data-placement="left" title"Click Here to Go Back to Registrations Page" id="generateCasePrintPage" onclick="javascript:generateCasePrint()">OK&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
<c:if test="${checkType eq 'SaveDTRS' }">
<button class="btn btn-primary"  type="button" data-toggle="tooltip" data-placement="right" title"Click Here to Print" id="generateDTRSPrint" onclick="javascript:generateDtrsPrint()">Print DTRS Form&nbsp;<span class="glyphicon glyphicon-print"></span></button>
</c:if>
</logic:notEmpty>

<c:if test="${cancelFlag eq 'Y' }">
<b> <bean:write name="chronicOpForm" property="msg"/> </b><br><br><br>
<button class="btn btn-success"  type="button" data-toggle="tooltip" data-placement="right" title="Click Here to Go Back to Registrations Page" id="generateCasePrintPage" onclick="javascript:generateCasePrint()">OK&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</c:if>

</font></center>
<center><font size="3"><bean:write name="chronicOpForm" property="errMsg"/></font></center>
<html:hidden name="chronicOpForm" property="chronicID" styleId="chronicID"/>
<html:hidden name="chronicOpForm" property="disableFlag" styleId="disableFlag"/>
<html:hidden name="chronicOpForm" property="chronicNo" styleId="chronicNo"/>
</form>

</body>
</html>