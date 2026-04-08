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
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

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
	window.open('./patientDetails.do?actionFlag=viewPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
}
function printDtrsForm()
{
	var caseId=document.getElementById("caseId").value;
	var caseValues=caseId.split("/");
	var caseNo=caseValues[2];
	parent.page=window.open("./patientDetails.do?actionFlag=dtrsPrintForm&caseId="+caseNo+"&printFormType=DTRS",'PatientDTRSPrintPage','left=50,top=50,width=900,height=700,toolbar=no,resize=no,scrollbars=yes');
}
 
function generateCasePrint(){
	
	var caseId=document.getElementById("caseId").value;
	var caseValues=caseId.split("/");
	var caseNo=caseValues[2];
	var highEndOncologyCase = '${highEndOncologyCase}';
	if(caseId.indexOf("/D",0)!=-1){
		refreshParentPage();
	}
	else if(caseId.indexOf("CHRONIC",0)!=-1){
		refreshParentPage();
	}
	else{
		if(highEndOncologyCase =='P')
			parent.fn_highEndDrugsApproval();
		else
			refreshParentPage();
		window.open("./patientDetails.do?actionFlag=casePrintForm&caseId="+caseId+"&highEndOncologyCase="+highEndOncologyCase,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
		//window.open("./patientDetails.do?actionFlag=casePrintForm&caseId="+caseId,'left=50,top=50,width=900,height=700,toolbar=no,resize=no,scrollbars=yes');
	//document.forms[0].action="./patientDetails.do?actionFlag=casePrintForm&caseId="+caseId;
	//document.forms[0].method="post";
	//document.forms[0].submit();
	} 
}

function  refreshParentPage(){
	parent.fn_viewRegisteredPatients();	
}
</script>


<style type="text/css">
.btn
{
border-radius:20px;
}
.btn:hover
{
border-radius:5px;
}
</style>
</head>
<body>
<form action="/patientDetails.do" method="post">
<br><br><br><br><br><br><center><font size="3">
<logic:notEmpty name="patientForm" property="patientNo">
Patient Registered Successfully with Patient ID : <b><bean:write name="patientForm" property="patientNo"/></b><br><br><br>
<button class="btn btn-success"  type="button" id="generatePrintPage" onclick="javascript:generatePrint('<bean:write name="patientForm" property="patientNo"/>')">OK&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</logic:notEmpty>
<logic:notEmpty name="patientForm" property="caseId">
<b><bean:write name="patientForm" property="patientType"/></b> <bean:write name="patientForm" property="msg"/>
<c:choose>
<c:when test="${chronicFlg eq 'Y' }">
 <b><bean:write name="patientForm" property="chronicNo"/></b>
</c:when>
<c:otherwise>
	<c:if test="${empty highEndOncologyCase || (not empty highEndOncologyCase && highEndOncologyCase eq 'P')}">
		<b><bean:write name="patientForm" property="caseId"/></b>
	</c:if>
</c:otherwise>
</c:choose>


<br><br><br>
<%-- <logic:equal name="patientForm" property="patientType" value="IP"> --%>
<button class="btn btn-primary"  type="button" id="generateCasePrintPage" onclick="javascript:generateCasePrint()">OK&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
<button class="btn btn-success"  type="button" id="generateDTRSPage" onclick="javascript:printDtrsForm()" style="display:none">Print DTRS Form&nbsp;&nbsp;<span class="glyphicon glyphicon-print"></span></button>
</logic:notEmpty>
</font></center>
<center><font size="3"><bean:write name="patientForm" property="errMsg"/></font></center>
<html:hidden name="patientForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="disableFlag" styleId="disableFlag"/>
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