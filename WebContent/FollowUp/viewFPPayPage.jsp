<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>
<html>
<fmt:bundle basename="Claims">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detailed Patient Information</title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<%@ include file="/common/includeScrollbar.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript">
//jquery alerts functions
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function fn_sendBack(buttonId){
		
	if(document.getElementById("ceoRemark").value==null || document.getElementById("ceoRemark").value==''||document.getElementById("ceoRemark").value.trim()==null || document.getElementById("ceoRemark").value.trim()=='')
	{
	var fr = partial(focusBox,document.getElementById('ceoRemark'));
	jqueryErrorMsg('FollowUp Validation',"Please Enter Remarks \n",fr);
	return;
	}
	
	var caseNo= '${patCommonDtls.CASENO}';
	var fr = partial(confirmBox,caseNo);
	jqueryConfirmMsg('FollowUp Submission',"Do you want to Send it back to CH?",fr);	
	
}
function confirmBox(caseNo){
	document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=sendBackToCh&FollowUpId="+document.forms[0].followUpId.value+"&actionDone="+buttonId+"&CaseNo="+caseNo;
    document.forms[0].submit();
}
function focusBox(arg) {
	aField = arg;
	setTimeout("aField.focus()", 0);

}
function fn_Refresh(){
	window.opener.location.reload();
	window.close();
}
function maxLengthPress(field,maxChars,e)
{
	var fr = partial(focusBox,field);
	var name = field.value;
	 if(name != null && name !='' && name.length >= maxChars)
	{
	jqueryErrorMsg('Max Length Validation',"Remarks length should not exceed " +maxChars ,fr);
	field.value = field.value.substr(0, maxChars-1);
	}
}
function validateSplKeyCodes(evt)      
{         
	var charCode = (evt.which) ? evt.which : evt.keyCode       
			if (charCode>31&& (charCode<47 || charCode>58)&&(charCode<65 || charCode>90)
					&&(charCode<97 || charCode>122)&&(charCode!= 63 &&(charCode<39 || charCode>41)
							&&(charCode!=44)&&(charCode!=46)&&(charCode!=91)&&(charCode!=93)&&(charCode!=95)
							&&(charCode!=32)))	
			    return false; 	
				return true;  
}
function validateSpaces(input,arg1)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
    jqueryErrorMsg('Text Validation',"Cannot accept only spaces in "+arg1,fr);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Text Validation',arg1+ " should not start with space",fr);
		return false;
		}
}
</script>
</head>
<body>
<form action="/followUpAction.do" method="post" >
<c:if test="${saveMsg ne null}">
<script>
//alert('${saveMsg}');
jqueryInfoMsg('Follow Up Information', '${saveMsg}', fn_Refresh);
</script>
</c:if>
<table width="100%" align="center" border="1" bordercolor="black" cellpadding="1" cellspacing="1">
<tr><td colspan="2">
<table class="tbheader" align="center" width="100%" border="1">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;Detailed Patient Information</b></td></tr>
</table></td></tr>
<tr><td width="100%" valign="top">

 <table width="100%" border="0" align="left" cellpadding="0" cellspacing="4">
 <tr align="center"><td width="14%" class="labelheading1 tbcellCss" align="left"><b>Case Number</b></td>
 <td width="18%" align="left" class="tbcellBorder">${patCommonDtls.CASENO}</td>
 <td width="14%" class="labelheading1 tbcellCss" align="left"><b>Claim Number</b></td>
<td width="18%"  align="left" class="tbcellBorder">${patCommonDtls.CLAIMNO}</td>
 <td width="14%" class="labelheading1 tbcellCss" align="left"><b>Claim Status</b></td>
<td width="22%"  align="left" class="tbcellBorder">${patCommonDtls.STATUS}</td>
</tr>

 <tr align="center"><td width="15%" class="labelheading1 tbcellCss" align="left"><b>Patient Id</b></td>
 <td width="20%" align="left" class="tbcellBorder">${patCommonDtls.PATID}</td>
 <td width="10%" class="labelheading1 tbcellCss" align="left"><b>Patient Name</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.PATIENTNAME}</td>
 <td width="10%"  align="left" class="labelheading1 tbcellCss"><b>Ration Card No</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.CARDNO}</td>
</tr>

 <tr align="center"><td width="15%" class="labelheading1 tbcellCss" align="left"><b>Age</b></td>
 <td width="20%" align="left" class="tbcellBorder">${patCommonDtls.AGE}</td>
 <td width="10%" class="labelheading1 tbcellCss" align="left"><b>Gender</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.GENDER}</td>
 <td width="10%" class="labelheading1 tbcellCss" align="left"><b>District</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.DISTRICT}</td>
</tr>


 <tr align="center"><td width="15%" class="labelheading1 tbcellCss" align="left"><b>Mandal</b></td>
 <td width="20%" align="left" class="tbcellBorder">${patCommonDtls.MANDAL}</td>
 <td width="10%" class="labelheading1 tbcellCss" align="left"><b>Village</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.VILLAGE}</td>
 <td width="10%" class="labelheading1 tbcellCss" align="left"><b>IP Reg Dt</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.date}</td>
</tr>

 <tr align="center"><td width="15%" class="labelheading1 tbcellCss" align="left"><b>Follow-Up Id</b></td>
 <td width="20%" align="left" class="tbcellBorder">${patCommonDtls.IPNO}</td>
 <td width="15%" class="labelheading1 tbcellCss" align="left"><b>Hospital Name</b></td>
 <td width="20%" align="left" class="tbcellBorder">${patCommonDtls.HOSPNAME}</td>
 <td width="10%" class="labelheading1 tbcellCss" align="left"><b>Category</b></td>
<td width="25%"  align="left" class="tbcellBorder">${patCommonDtls.DISNAME}</td>
</tr>

<tr align="center">
 <td width="15%" class="labelheading1 tbcellCss" align="left"><b>Claim Amount</b></td>
<td width="20%"  align="left" class="tbcellBorder">${patCommonDtls.claimAmt}</td>
</tr>



 </table></td></tr>
 
 <tr><td>
<table border="0" width="100%" style="table-layout: fixed;word-wrap:break-word;" cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b><fmt:message key="EHF.workFlow.title"/></b></td></tr>
<tr ><td width="4%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.sno"/></td>
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.Dt"/></td>
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.name"/></td>			
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.role"/></td>
<td width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.reamark"/></td>
<td width="15%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.appAmt"/></td>
<td width="15%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.action"/></td>	
</tr>



<logic:iterate id="results1" name="followUpForm" property="lstworkFlow" indexId="index1" >
<tr>
<td style="word-wrap:break-word;" class="tbcellBorder"><%=index1+1%></td>
<td style="word-wrap:break-word;"  class="tbcellBorder"><bean:write name="results1" property="fpauditDate" /></td>
<td style="word-wrap:break-word;"  class="tbcellBorder"><bean:write name="results1" property="auditName" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditRole" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="cexRemark" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="COUNT" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
</tr>

</logic:iterate>

</table></td></tr>
</table>
<!-- 
<logic:equal value="CD104" property="followUpStatus" name="followUpForm">
<logic:equal value="GP40" property="roleId" name="followUpForm">
<table width="100%" align="center"> 
	<tr ><td  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <html:textarea name="followUpForm" property="ceoRemark"  styleId="ceoRemark" style="WIDTH:80%;height:3em" onkeydown="maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Ceo Remarks')" title="Enter Remarks"/>
    </td></tr>
 </table>
 </logic:equal>
 </logic:equal> -->
<table width="100%" align="center">
<tr><td>&nbsp;</td></tr>
<tr  align="center"><td align="center">
<!-- 	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<button class="but"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_sendBack('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	</c:forEach>-->
<button class="but"   type="button" id="Close" value='Close' onclick = 'window.close()' >Close</button>
</td></tr>
</table>
  <html:hidden property="followUpId" name="followUpForm"/>
  <html:hidden property="followUpStatus" name="followUpForm"/>
  <html:hidden property="roleId" name="followUpForm"/>
</form>
</body>
</fmt:bundle>
</html>