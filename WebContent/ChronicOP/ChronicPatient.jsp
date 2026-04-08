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
	window.open('./chronicAction.do?actionFlag=printPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=1000,height=600,toolbar=no,resize=no,scrollbars=yes');
}
/* function printDtrsForm()
{
	var caseId=document.getElementById("caseId").value;
	var caseValues=caseId.split("/");
	var caseNo=caseValues[2];
	parent.page=window.open("./patientDetails.do?actionFlag=dtrsPrintForm&caseId="+caseNo+"&printFormType=DTRS",'PatientDTRSPrintPage','left=50,top=50,width=900,height=700,toolbar=no,resize=no,scrollbars=yes');
}
 
function generateCasePrint()
{
	var caseId=document.getElementById("caseId").value;
	var caseValues=caseId.split("/");
	var caseNo=caseValues[2];
	if(caseId.indexOf("/D",0)!=-1)
	{
		refreshParentPage();
	}
	else
	{
		window.open("./patientDetails.do?actionFlag=casePrintForm&caseId="+caseId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
		//window.open("./patientDetails.do?actionFlag=casePrintForm&caseId="+caseId,'left=50,top=50,width=900,height=700,toolbar=no,resize=no,scrollbars=yes');
	//document.forms[0].action="./patientDetails.do?actionFlag=casePrintForm&caseId="+caseId;
	//document.forms[0].method="post";
	//document.forms[0].submit();
	} 
} */

function  refreshParentPage()
{
parent.fn_chronicOPPatientsView();	
}
</script>
</head>
<body>
<form action="/chronicAction.do" method="post">
<br><br><br><br><br><br><center><font size="3">
<logic:notEmpty name="chronicOpForm" property="patientNo">
Chronic Patient Registered Successfully with Chronic ID : <b><bean:write name="chronicOpForm" property="patientNo"/></b><br><br><br>
<button class="btn btn-success"  type="button" data-toggle="tooltip" data-placement="left" title"Click Here to Go Back to Registrations Page" id="refreshPage" onclick="javascript:refreshParentPage()">OK&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
<button class="btn btn-primary"  type="button" id="generatePrintPage" data-toggle="tooletip" data-placement="right" title="Click Here to Print" onclick="javascript:generatePrint('<bean:write name="chronicOpForm" property="patientNo"/>')">PRINT&nbsp;<span class="glyphicon glyphicon-print"></span></button>
</logic:notEmpty>
<%-- <logic:notEmpty name="chronicOpForm" property="caseId">
<b><bean:write name="chronicOpForm" property="patientType"/></b> <bean:write name="chronicOpForm" property="msg"/> <b><bean:write name="chronicOpForm" property="caseId"/></b><br><br><br>
<logic:equal name="patientForm" property="patientType" value="IP">
<button class="but"  type="button" id="generateCasePrintPage" onclick="javascript:generateCasePrint()">OK</button>
<button class="but"  type="button" id="generateDTRSPage" onclick="javascript:printDtrsForm()" style="display:none">Print DTRS Form</button>
</logic:notEmpty> --%>
</font></center>
<center><font size="3"><bean:write name="chronicOpForm" property="errMsg"/></font></center>
<html:hidden name="chronicOpForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="chronicOpForm" property="caseId" styleId="caseId"/>
<html:hidden name="chronicOpForm" property="disableFlag" styleId="disableFlag"/>
</form>
<script>
var checkType=document.getElementById("disableFlag").value;
if(checkType=='SaveDTRS')
{
	document.getElementById("generateDTRSPage").style.display='';
}
</script>
</body>
</html>