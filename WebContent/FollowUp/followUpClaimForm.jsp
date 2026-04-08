<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>

<fmt:bundle basename="FollowUp">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script LANGUAGE="JavaScript" type="text/javascript" SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<style>
body{font-size:1.3em !important;}
.butBack{
background:#000000 !important;
color:#FFFFFF;
}
.butBack:hover{
background : #B6B6B4 !important;
}
.btn-primary:hover{
background : #00315e !important;
}
.btn-danger:hover{
background : #AF0000 !important;
}
.glyphicon-plus{
color:#FF4444 !important;
}
.glyphicon-new-window{
color:#FF4444 !important;
}
</style>
<script type="text/javascript">
var ftdFlag='${ftdFlag}';
$(function()
{
/* $("#claimNwhAmt").attr('placeholder', '0');
$("#medcoRemarks").attr('placeholder', 'Enter Remarks'); */
});
function resizePatImage(id,width,height)
{
var patImage=document.getElementById(id);
patImage.setAttribute("width",width);
 patImage.setAttribute("height",height);
} 
function fn_printDtrs()
{
var scheme_id= '${scheme_id}';
var url="/<%=context%>/followUpAction.do?actionFlag=printDTRS&followUpId="+document.forms[0].followUpId.value+"&caseId="+document.forms[0].caseId.value+"&scheme_id="+scheme_id;
window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function fn_printPrescription()
{
var scheme_id= '${scheme_id}';
var url="/<%=context%>/followUpAction.do?actionFlag=printPrescription&followUpId="+document.forms[0].followUpId.value+"&caseId="+document.forms[0].caseId.value+"&scheme_id="+scheme_id;
window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
String.prototype.trim = function () {
return this.replace(/^\s*/, "").replace(/\s*$/, "");
}
//jquery alerts functions
function partial(func /*, 0..n args */) {
 var args = new Array();
 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
return function() {
var allArguments = args.concat(Array.prototype.slice.call(arguments));
return func.apply(this, allArguments);
};
}
function fn_openAtachment(filepath,fileName)
{
var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath+'&fileName='+fileName;
childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function CasesView(){
var patientScheme="${patientScheme}";
var caseApprovalFlag = '${casesForApproval}';
document.forms[0].action="/Operations/followUpAction.do?actionFlag=caseSearch&refreshFlag=Y&casesForApproval="+caseApprovalFlag+"&viewFlag=N&patientScheme="+patientScheme;
document.forms[0].submit();
}
function validateNumber(arg1,input)
{
var a=input.value;
var regDigit=/^\d+$/ ;
var fr = partial(focusBox,input);
if(a.search(regDigit)==-1)
{
input.value="";
jqueryErrorMsg('Text Validation',"Only Numbers are allowed for "+arg1,fr);
return false;
}
}
function validateNumberAmt(arg1,input)
{
var a=input.value;
var regDigit=/^\d+$/ ;
var fr = partial(focusBox,input);
if(a.search(regDigit)==-1)
{
input.value="0";
jqueryErrorMsg('Text Validation',"Only Numbers are allowed for "+arg1,fr);
return false;
}
else
return true;
}
function focusBox(arg)
{
aField = arg; 
setTimeout("aField.focus()", 0);
}
function fn_openFollowUpDtls(caseId,followUPId){

var url='/<%=context%>/followUpAction.do?actionFlag=getFollowUpDtls&CaseId='+caseId+'&backFlg=N&followUpid='+followUPId+'&viewFlag=Y';
//document.forms[0].submit();
childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
}
function validateAlphaNumSpace(arg1,input)
{
var fr = partial(focusBox,input);
var textbox1 =input;
var textval = textbox1.value;
var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
if(tbLen.length == 0)
{
//alert('Blank spaces are not allowed in '+arg1);
jqueryErrorMsg('Text Validation',"Blank spaces are not allowed in "+arg1,fr);
textbox1.focus();
textbox1.value='';
return false;
}
if(textval.charAt(0)==' '){ 
//alert('Starting blank spaces are not allowed in '+arg1);
jqueryErrorMsg('Text Validation',"Starting blank spaces are not allowed in "+arg1,fr);
textbox1.focus();
textbox1.value='';
return false;
}
 var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789().,/";
 var IsNumber=true;
 var Char; 
 if(textval.length > 0)
{
for (i = 0; i < textval.length && IsNumber == true; i++) 
 { 
Char = textval.charAt(i); 
 if (ValidChars.indexOf(Char) == -1) 
{
//alert("Only Characters from A-Z,a-z,0-9 are allowed in "+arg1);
jqueryErrorMsg('Text Validation',"Only Characters from A-Z,a-z,0-9 are allowed in "+arg1,fr);
textbox1.value= '';
textbox1.focus();
return false;
 }
}
}
else 
return false;

}
function validateMaxlength(input,e)
{
var name = input.value;
 if(name != null && name !='' && name.length >= 3000)
{
//alert("Remarks length should not exceed " +3000 );
jqueryErrorMsg('Text Validation',"Remarks length should not exceed 3000");
input.value = input.value.substr(0, 3000-1);
}
}
function validateBackSpace(e)
{
var code;
if (!e) var e = window.event;
if (e.keyCode) code = e.keyCode;
else if (e.which) code = e.which;
if( code== 8 )
{
e.returnValue=false;
  }
}

var forw=-1;
var tot=-1;
var claim=-2;
var claimInit='N';

function fn_viewClinicalData(){

var url = "/<%=context%>/followUpAction.do?actionFlag=viewClinicalData&followUpId="+document.forms[0].followUpId.value;
parent.parent.fpClinicalWindow= window.open(url,"",'width=800,height=500,resizable=yes,top=100,left=100,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
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
function validateSplKeyCodes(evt)
{ 
var charCode = (evt.which) ? evt.which : evt.keyCode; 
if (charCode>31&& (charCode<47 || charCode>58)&&(charCode<65 || charCode>90)
&&(charCode<97 || charCode>122)&&(charCode!= 63 &&(charCode<39 || charCode>41)
&&(charCode!=44)&&(charCode!=46)&&(charCode!=91)&&(charCode!=93)&&(charCode!=95)
&&(charCode!=32)&&(charCode!=38)&&(charCode!=39)&&(charCode!=40)))
return false; 
return true;
} 
function maxLengthPress(field,maxChars,e)
{
var code;
if (!e) var e = window.event;
if (e.keyCode) code = e.keyCode;
else if (e.which) code = e.which; 
if(field.value.length >= maxChars) 
{
if(code==8 || code==9 || code==46 || code==37 || code==38 || code==39 || code==40)
//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
{
e.returnValue=true;
return true;
}
else
{
e.returnValue=false;
return false;
 }
 }
}
//For validating maxlength onpaste event--For textarea fields
function maxLengthPaste(field,maxChars)
{
var fr = partial(focusBox,field);
event.returnValue=false;
if(window.clipboardData)
{
if((field.value.length +window.clipboardData.getData("Text").length) > maxChars) 
{
//alert("Characters should not exceed 3000");
jqueryErrorMsg('Max Length Validation',"Remarks length should not exceed " +maxChars ,fr);
return false;
}
event.returnValue=true;
}
if (event.clipboardData) 
{
if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
{
//alert("Characters should not exceed 300");
jqueryErrorMsg('Max Length Validation',"Remarks length should not exceed " +maxChars ,fr);
return false;
}
event.returnValue=true;
}
}

function fn_caseApproval(caseId,arg,ipOpType)
{ 
url='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&casesForApproval=N&ipOpType='+ipOpType;
childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
}
function findTotalAmt()
{
var pharmRes=false;
var consultRes=false;
var investRes=false;
if(document.getElementById("claimFcxPharmBill").value!=null)
{
if(document.getElementById("claimFcxPharmBill").value!='')
{
pharmRes=validateNumberAmt('Pharmacy Bill',document.getElementById("claimFcxPharmBill"));
if(pharmRes==false)
{
document.getElementById("claimFcxAmt").value=(Number(document.getElementById("claimFcxConsulBill").value)
+ Number(document.getElementById("claimFcxInvestBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimFcxPharmBill").value='0';
pharmRes=true;
}
}
if(document.getElementById("claimFcxConsulBill").value!=null)
{
if(document.getElementById("claimFcxConsulBill").value!='')
{
consultRes=validateNumberAmt('Consultation Bill',document.getElementById("claimFcxConsulBill"));
if(consultRes==false)
{
document.getElementById("claimFcxAmt").value=(Number(document.getElementById("claimFcxPharmBill").value)
+ Number(document.getElementById("claimFcxInvestBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimFcxConsulBill").value='0';
consultRes=true;
}
}

if(document.getElementById("claimFcxInvestBill").value!=null)
{
if(document.getElementById("claimFcxInvestBill").value!='')
{
investRes=validateNumberAmt('Investigation Bill',document.getElementById("claimFcxInvestBill"));
if(investRes==false)
{
document.getElementById("claimFcxAmt").value=(Number(document.getElementById("claimFcxPharmBill").value)
+ Number(document.getElementById("claimFcxConsulBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimFcxInvestBill").value='0';
investRes=true;
}
}
if(pharmRes==true && consultRes==true && investRes==true)
{
var totalAmt=(Number(document.getElementById("claimFcxPharmBill").value)+
Number(document.getElementById("claimFcxConsulBill").value)+
Number(document.getElementById("claimFcxInvestBill").value));
 if(totalAmt > document.forms[0].newtotAmt.value)
{
alert("Total Amount cannot be greater than the sum of package amount and Carry Forward Amount");
document.getElementById("claimFcxPharmBill").value=0;
document.getElementById("claimFcxConsulBill").value=0;
document.getElementById("claimFcxInvestBill").value=0;
document.getElementById("claimFcxAmt").value=0;
return false;
}
 else
 {
 document.getElementById("claimFcxAmt").value=totalAmt.toString();
 }
}

}

function findTotalAmtFTD()
{
var pharmRes=false;
var consultRes=false;
var investRes=false;
if(document.getElementById("claimFTDPharmBill").value!=null)
{
if(document.getElementById("claimFTDPharmBill").value!='')
{
pharmRes=validateNumberAmt('Pharmacy Bill',document.getElementById("claimFTDPharmBill"));
if(pharmRes==false)
{
document.getElementById("claimFtdAmt").value=(Number(document.getElementById("claimFTDConsulBill").value)
+ Number(document.getElementById("claimFTDInvestBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimFTDPharmBill").value='0';
pharmRes=true;
}
}
if(document.getElementById("claimFTDConsulBill").value!=null)
{
if(document.getElementById("claimFTDConsulBill").value!='')
{
consultRes=validateNumberAmt('Consultation Bill',document.getElementById("claimFTDConsulBill"));
if(consultRes==false)
{
document.getElementById("claimFtdAmt").value=(Number(document.getElementById("claimFTDPharmBill").value)
+ Number(document.getElementById("claimFTDInvestBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimFTDConsulBill").value='0';
consultRes=true;
}
}
if(document.getElementById("claimFTDInvestBill").value!=null)
{
if(document.getElementById("claimFTDInvestBill").value!='')
{
investRes=validateNumberAmt('Investigation Bill',document.getElementById("claimFTDInvestBill"));
if(investRes==false)
{
document.getElementById("claimFtdAmt").value=(Number(document.getElementById("claimFTDPharmBill").value)
+ Number(document.getElementById("claimFTDConsulBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimFTDInvestBill").value='0';
investRes=true;
}
}
if(pharmRes==true && consultRes==true && investRes==true)
{
var totalAmt=(Number(document.getElementById("claimFTDPharmBill").value)+
Number(document.getElementById("claimFTDConsulBill").value)+
Number(document.getElementById("claimFTDInvestBill").value));
 if(totalAmt > document.forms[0].newFTDtotAmt.value)
{
alert("Total Amount cannot be greater than the sum of package amount and Carry Forward Amount");
document.getElementById("claimFTDPharmBill").value=0;
document.getElementById("claimFTDConsulBill").value=0;
document.getElementById("claimFTDInvestBill").value=0;
document.getElementById("claimFtdAmt").value=0;
return false;
}
 else
 {
 document.getElementById("claimFtdAmt").value=totalAmt.toString();
 }
}

}

function findTotalAmtCH()
{
var pharmRes=false;
var consultRes=false;
var investRes=false;
if(document.getElementById("claimCHPharmBill").value!=null)
{
if(document.getElementById("claimCHPharmBill").value!='')
{ 
pharmRes=validateNumberAmt('Pharmacy Bill',document.getElementById("claimCHPharmBill"));
if(pharmRes==false)
{
document.getElementById("claimChAmt").value=(Number(document.getElementById("claimCHConsulBill").value)
+ Number(document.getElementById("claimCHInvestBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimCHPharmBill").value='0';
pharmRes=true;
}
}
if(document.getElementById("claimCHConsulBill").value!=null)
{
if(document.getElementById("claimCHConsulBill").value!='')
{
consultRes=validateNumberAmt('Consultation Bill',document.getElementById("claimCHConsulBill"));
if(consultRes==false)
{
document.getElementById("claimChAmt").value=(Number(document.getElementById("claimCHPharmBill").value)
+ Number(document.getElementById("claimCHInvestBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimCHConsulBill").value='0';
consultRes=true;
}
}
if(document.getElementById("claimCHInvestBill").value!=null)
{
if(document.getElementById("claimCHInvestBill").value!='')
{ 
investRes=validateNumberAmt('Investigation Bill',document.getElementById("claimCHInvestBill"));
if(investRes==false)
{
document.getElementById("claimChAmt").value=(Number(document.getElementById("claimCHPharmBill").value)
+ Number(document.getElementById("claimCHConsulBill").value)).toString();
return false;
}
}
else
{
document.getElementById("claimCHInvestBill").value='0';
investRes=true;
}
}
if(pharmRes==true && consultRes==true && investRes==true)
{
var totalAmt=(Number(document.getElementById("claimCHPharmBill").value)+
Number(document.getElementById("claimCHConsulBill").value)+
Number(document.getElementById("claimCHInvestBill").value));
 if(totalAmt > document.forms[0].newCHtotAmt.value)
{
alert("Total Amount cannot be greater than the sum of package amount and Carry Forward Amount");
document.getElementById("claimCHPharmBill").value=0;
document.getElementById("claimCHConsulBill").value=0;
document.getElementById("claimCHInvestBill").value=0;
document.getElementById("claimChAmt").value=0;
return false;
}
 else
 {
 document.getElementById("claimChAmt").value=totalAmt.toString();
 }
}

}
</script>
</head>
<bean:define id="buttonDtls" name="followUpForm" property="buttonList" />
<c:set var="buttons" value="${buttonDtls}"/>
<body onload="fn_onloadDisabled();">
<form action="/followUpAction.do" method="post"enctype="multipart/form-data">
<html:hidden property="followUpId" name="followUpForm"/>
<html:hidden property="caseId" name="followUpForm"/>
<html:hidden property="clinicalId" name="followUpForm"/>
<html:hidden property="packageAmt" name="followUpForm"/>
<html:hidden property="followUpDt" name="followUpForm"/>
<html:hidden property="roleId" name="followUpForm"/>
<html:hidden property="followUpStatus" name="followUpForm"/>
<html:hidden property="newClmFwdAmt" name="followUpForm"/>

<table width="100%">
<tr>
<td>
<table class="tbheader">
<tr>
<td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.FollowDetailsTitle"/></b>
</td>
</tr>
</table>
</td>
</tr>
<tr><td>

 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4">

<tr>

 <td width="12%" class="labelheading1 tbcellCss" ><b>Name </b></td>
 <td width="50%" class="tbcellBorder" colspan="3">${patCommonDtls.PATIENTNAME} ,${patCommonDtls.GENDER} ,${patCommonDtls.AGE}</td>
  <td width="10%" class="labelheading1 tbcellCss"><b>Card No &nbsp; </b></td>
  <td width="14%" class="tbcellBorder">${patCommonDtls.CARDNO}</td>
 <td width="14%"align="right"><c:if test="${hideBackButton != 'Y'}">
<!-- <button class="but" type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button> -->
<button type="button" class="btn butBack" title="Click to go Back" id="Back" value='Back' onclick = 'CasesView()' >Back</button>
</c:if>
</td>
</tr>


<tr> <td width="12%" class="labelheading1 tbcellCss"><b>NWH Name &nbsp;</b></td>
 <td width="50%" class="tbcellBorder" colspan="3">${patCommonDtls.HOSPNAME}</td>
 <td width="10%" class="labelheading1 tbcellCss"><b>Case No&nbsp;</b></td>
  <td width="14%" class="tbcellBorder"><a title="Click to view Case Details" href="javascript:fn_caseApproval('${patCommonDtls.CASENO}','','${patCommonDtls.patientIpop}');"><font color="#FF4444">${patCommonDtls.CASENO}</font>&nbsp;<span class="glyphicon glyphicon-new-window"></span></a></td>
  <td width="14%" style="text-align:center" rowspan="5" class="tbcellBorder" id="patPhoto">
<logic:notEmpty name="followUpForm" property="photoUrl">
<img id="patImage" src="common/showDocument.jsp" width="120" height="110"onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','120','110')"/>
</logic:notEmpty>
<logic:empty name="followUpForm" property="photoUrl">
<img src="images/photonot.gif" width="120" height="110" alt="NO DATA"/>
</logic:empty>
</td>
</tr>
<tr>
<td width="12%" rowspan="1" class="labelheading1 tbcellCss"><b>Catogory Name&nbsp;</b></td>
 <td width="25%" rowspan="1" class="tbcellBorder"> ${patCommonDtls.DISNAME}</td>
<td width="10%" class="labelheading1 tbcellCss"><b>Follow-Up ID&nbsp;</b></td>
<td width="15%" class="tbcellBorder">${patCommonDtls.IPNO}</td>
 <td width="10%" rowspan="1" class="labelheading1 tbcellCss"><b>Case Status &nbsp;</b></td>
 <td width="14%" rowspan="1" class="tbcellBorder"> ${patCommonDtls.STATUS}</td>
 
</tr>
 <tr>
 <td width="12%" class="labelheading1 tbcellCss"><b>Claim No&nbsp;</b></td>
 <td width="25%" class="tbcellBorder">${patCommonDtls.CLAIMNO}</td>
 <td width="10%" class="labelheading1 tbcellCss"><b>District&nbsp;</b></td>
 <td width="15%" class="tbcellBorder">${patCommonDtls.DISTRICT}</td>
 <td width="10%" class="labelheading1 tbcellCss"><b>Mandal&nbsp;</b></td>
 <td width="14%" class="tbcellBorder">${patCommonDtls.MANDAL}</td>
</tr>
<tr>
 <td width="12%" class="labelheading1 tbcellCss"><b>IP Reg Dt &nbsp;</b></td>
 <td width="25%" class="tbcellBorder">${patCommonDtls.date}</td>
 <td width="10%" class="labelheading1 tbcellCss"><b>Village&nbsp;</b></td>
  <td width="15%" class="tbcellBorder">${patCommonDtls.VILLAGE}</td>
 <td width="10%" class="labelheading1 tbcellCss"><b>Contact No&nbsp;</b></td>
 <td width="14%" class="tbcellBorder">${patCommonDtls.CONTACT}</td>
</tr>
<tr>
 <td width="12%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.AdmissionDt"/>&nbsp;</b></td>
 <td width="25%" class="tbcellBorder" ><bean:write name="followUpForm" property="csAdmDt" /></td> <%-- <html:text name="followUpForm" property="csAdmDt" styleId="csAdmDt" title="Date of Admission" disabled="true"></html:text></td> --%>
 <%-- <html:text name="followUpForm" property="csSurgDt" styleId="csSurgDt" title="Date of Surgery" disabled="true"></html:text></td> --%>
 <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SurgDt"/>&nbsp;</b></td>
 <td width="15%" class="tbcellBorder"><bean:write name="followUpForm" property="csSurgDt" /> </td>
 <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisDt"/>&nbsp;</b></td>
 <td width="14%" class="tbcellBorder"> <bean:write name="followUpForm" property="csDisDt" /> </td><%-- <html:text name="followUpForm" property="csDisDt" styleId="csDisDt" title="Date of Discharge" disabled="true"></html:text></td> --%>
</tr>
 <tr><td colspan="10"></td></tr>
 <!-- 
 <tr><td colspan="10" align="center">
<c:if test="${hideBackButton != 'Y'}">
<button class="but" type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>
</c:if>
&nbsp;
</td></tr>
 -->
 </table>
</td></tr></table>

<c:if test="${saveMsg ne null}">
<script>
jqueryInfoMsg('FollowUp Information','${saveMsg}');
var OnloadCaseOpen='${OnloadCaseOpen}';
var UnlockCase='${UnlockCase}';
var autoCaseId='${autoCaseId}';
var patientScheme='${patientScheme}';

if(OnloadCaseOpen=='Y' && UnlockCase=='Y')
if(patientScheme=='CD501')
parent.parent.fn_caseForApprovalFollowUpNew(autoCaseId);
if(patientScheme=='CD502')
parent.parent.fn_JournalistCaseForApprovalFollowUpNew(autoCaseId);
</script>
</c:if>

<logic:notEmpty name="followUpForm" property="msg">
<div style="margin: 150px 0px 0px 300px; border: 1px solid; width: 30%; height: 20px; text-align: center;padding:15px;font-weight:bold;color:black;">
<bean:write name="followUpForm" property="msg"/>
</div>
</logic:notEmpty>
<logic:empty name="followUpForm" property="msg"> 
<table width="100%" style="margin:0 auto">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.FollowUpClaimForm"/></b></td></tr>
</table>
<br>
<table border="0" width="100%">
<tr>
<td width="10%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.FollowUpId"/></b></td>
<td width="17%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.FollowUpDt"/></b></td>
<td width="13%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.NextFollowUp"/></b></td>
<td width="15%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.ActualPkg"/></b></td>
<td width="15%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.ClaimPaid"/></b></td>
<td width="15%" class="labelheading1 tbcellCss" align="center"><b>Medco Initiated Amount</b></td>
<td width="15%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.CarryFwd"/></b></td>
<td width="15%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.BalanceAvailb"/></b></td>
</tr>
<logic:iterate id="results1" name="followUpForm" property="followUPLst" indexId="index1" >
<tr>
<td class="tbcellBorder"align="center">
<c:if test="${followUpID ne results1.followUpId }"><c:if test="${results1.followUpId ne 'Total'}"><a title="Click to view FollowUp Details" href="javascript:fn_openFollowUpDtls('<bean:write name="results1" property="caseId" />','<bean:write name="results1" property="followUpId" />');"><font color="#FF4444"><bean:write name="results1" property="followUpId" /></font>&nbsp;<span class="glyphicon glyphicon-new-window"></span></a></c:if></c:if>
<c:if test="${followUpID eq results1.followUpId || results1.followUpId eq 'Total'}"><bean:write name="results1" property="followUpId" /></c:if>
</td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="followUpSubDt" /></td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="nxtFollowUpDt" /></td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="actualAmt" /></td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="claimAmt" /></td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="claimNwhAmt" /></td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="claimFwd" /></td>
<td class="tbcellBorder" align="center"><bean:write name="results1" property="balAvail" /></td>
<script>
if(document.forms[0].followUpId.value=='<bean:write name="results1" property="followUpId" />'){

if(forw!=-1)
tot=parseInt('<bean:write name="results1" property="actualAmt" />')+forw;
else if('<bean:write name="results1" property="claimFwdOld" />'=='')
tot=parseInt('<bean:write name="results1" property="actualAmt" />')+0;
else
tot=parseInt('<bean:write name="results1" property="actualAmt" />')+parseInt('<bean:write name="results1" property="claimFwdOld" />');

if(claim==-1)
claimInit='Y';


}
if('<bean:write name="results1" property="claimFwd" />'!='--'){
forw=parseInt('<bean:write name="results1" property="claimFwd" />');
}
else
forw=-1;
if('<bean:write name="results1" property="claimAmt" />'!="--"){
claim=parseInt('<bean:write name="results1" property="claimAmt" />');
}
else
claim=-1;
</script>
</tr>
</logic:iterate>
</table>
<table width="100%">

<tr>
<td width="100%">
<table class="tbheader">
<tr>
<td><b><fmt:message key="EHF.Attachments"/></b>
</td>
</tr>
</table>
</td>
</tr>
</table>
<!--<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ForCompareDocs"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ForComparePhotos"/></b></td></tr>-->
<table width="100%" >
<tr>
<td class="labelheading1 tbcellCss" width="20%" align="center">
<div class="panel-group" id="accordion">
<div class="panel panel-default">
<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" title="Click to see Investigation Reports"><b><font color="#FF4444" ><fmt:message key="EHF.ForInvestRpts"/></font></b>&nbsp;<span class="glyphicon glyphicon-plus"></span></a>
</div>
</div>
</td>
<td class="labelheading1 tbcellCss" width="30%" align="center">
<div class="panel-group" id="accordion">
<div class="panel panel-default">
<%-- <b><fmt:message key="EHF.ForCompDocuments"/></b> --%>
<a data-toggle="collapse" data-parent="#accordion" href="#collapse2" title="Click to see Comparative Documents"><b><font color="#FF4444" ><fmt:message key="EHF.ForCompDocuments"/></font></b>&nbsp;<span class="glyphicon glyphicon-plus"></span></a>
</div>
</div>
</td>
<td class="labelheading1 tbcellCss" width="30%" align="center">
<div class="panel-group" id="accordion">
<div class="panel panel-default">
<%-- <b><fmt:message key="EHF.ForCompPhotographs"/></b> --%>
<a data-toggle="collapse" data-parent="#accordion" href="#collapse3" title="Click to see Comparative Photographs"><b><font color="#FF4444" ><fmt:message key="EHF.ForCompPhotographs"/></font></b>&nbsp;<span class="glyphicon glyphicon-plus"></span></a>
</div>
</div>
</td>
<td class="labelheading1 tbcellCss" width="20%" align="center">
<div class="panel-group" id="accordion">
<div class="panel panel-default">
<%-- <b><fmt:message key="EHF.ForCompPhotographs"/></b> --%>
<a data-toggle="collapse" data-parent="#accordion" href="#collapse4" title="Click to see Comparative DTRS Attachments"><b><font color="#FF4444" ><fmt:message key="EHF.ForCompDTRSAttachments"/></font></b>&nbsp;<span class="glyphicon glyphicon-plus"></span></a>
</div>
</div>
</td>

</tr>
<tr>
<%-- <td >
<div id="collapse1" class="panel-collapse collapse"> 
<table width="100%">
<logic:iterate id="result" name="followUpForm" property="lstAttachments">
<tr>
<td>
<a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('<bean:write name="result" property="savedName"/>','<bean:write name="result" property="savedName"/>');return false;"><bean:write name="result" property="actualName"/></a>
</td>
</tr>
</logic:iterate>
</table>
</div>
</td> --%>
<td >
<div id="collapse1" class="panel-collapse collapse"> 
<c:forEach var="h" begin="1" end="4">

<table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="100%" align="left">
FollowUp:<c:out value="${h}"/>
</td>
</tr>
<logic:present name="followUpForm" property="lstAttachments">
<logic:iterate id="result" name="followUpForm" property="lstAttachments">
<c:if test="${result.inst eq h}">
<tr>
<td >
<a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('<bean:write name="result" property="savedName"/>','<bean:write name="result" property="savedName"/>');return false;"><bean:write name="result" property="actualName"/></a>
</td>
</tr>
</c:if>
</logic:iterate>
</logic:present>
</table>
</c:forEach>
</div>

</td>
<td >
<div id="collapse2" class="panel-collapse collapse"> 
<c:forEach var="i" begin="1" end="4">

<table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="100%" align="left">
FollowUp:<c:out value="${i}"/>
</td>
</tr>
<logic:present name="followUpForm" property="lstCompDocs">
<logic:iterate id="resultC" name="followUpForm" property="lstCompDocs">
<c:if test="${resultC.inst eq i}">
<tr>
<td >
<a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('<bean:write name="resultC" property="savedName"/>','<bean:write name="resultC" property="savedName"/>');return false;"><bean:write name="resultC" property="actualName"/></a>
</td>
</tr>
</c:if>
</logic:iterate>
</logic:present>
</table>
</c:forEach>
</div>

</td>
<td>

<div id="collapse3" class="panel-collapse collapse"> 
<c:forEach var="j" begin="1" end="4">
<table width="100%" >
<tr>
<td class="labelheading1 tbcellCss" width="100%" align="left">
FollowUp:<c:out value="${j}"/>
</td>
</tr>
<logic:present name="followUpForm" property="lstCompPhotos">
<logic:iterate id="resultD" name="followUpForm" property="lstCompPhotos">
<c:if test="${resultD.inst eq j}">
<tr>
<td>
<a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('<bean:write name="resultD" property="savedName"/>','<bean:write name="resultD" property="savedName"/>');return false;"><bean:write name="resultD" property="actualName"/></a>
</td>
</tr>
</c:if>
</logic:iterate>
</logic:present>
</table>
</c:forEach>
</div>

</td>
<td >
<div id="collapse4" class="panel-collapse collapse"> 
<c:forEach var="l" begin="1" end="4">

<table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="100%" align="left">
FollowUp:<c:out value="${l}"/>
</td>
</tr>
<logic:present name="followUpForm" property="lstCompDTRS">
<logic:iterate id="resultE" name="followUpForm" property="lstCompDTRS">
<c:if test="${resultC.inst eq l}">
<tr>
<td >
<a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('<bean:write name="resultE" property="savedName"/>','<bean:write name="resultE" property="savedName"/>');return false;"><bean:write name="resultE" property="actualName"/></a>
</td>
</tr>
</c:if>
</logic:iterate>
</logic:present>
</table>
</c:forEach>
</div>

</td>
</tr>
</table>
<br>
<button class="but btn btn-primary" type="button"value="View Clinical Data" name="ViewClinicalData" onclick="javascript:fn_viewClinicalData();">View Clinical Data</button>

<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.NWHFollowupClaimEntry"/></b></td></tr>
</table>
<table width="100%">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.FollowUpId"/></b>&nbsp;&nbsp;<bean:write name="followUpForm" property="followUpId"/></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.FollowUpDt"/></b>&nbsp;&nbsp;<bean:write name="followUpForm" property="followUpDt"/></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.PackAmt"/></b>&nbsp;&nbsp;<bean:write name="followUpForm" property="packageAmt"/></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Carry Forward Amount</b>&nbsp;&nbsp;<bean:write name="followUpForm" property="newClmFwdAmt"/></td> 
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ClaimAmt"/><font color="red">*</font></b>&nbsp;&nbsp;<html:text name="followUpForm" property="claimNwhAmt" styleId="claimNwhAmt" style="WIDTH:12em;" title="Claim Amount" maxlength="6" onchange="validateNumber('Claim Amount',this)"/></td>
<td colspan="2" class="labelheading1 tbcellCss">
<table>
<tr><td ><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td>
<td><html:textarea name="followUpForm" property="medcoRemarks" styleId="medcoRemarks"style="overflow-y:auto;WIDTH:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr> 
</table>
</td>
</tr>

</table>
<logic:equal value="Yes" name="followUpForm" property="showNam">
<table class="tbheader">
<tr><td><b>
<c:if test="${followUPSchemeID ne 'CD201' }"><fmt:message key="EHF.Title.NAM"/></c:if>
<c:if test="${followUPSchemeID eq 'CD201' }"><fmt:message key="EHF.Title.APNAM"/></c:if>
</b>
</td></tr>
</table>
<table width="100%">
<!-- <tr>
<td colspan="2" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/></b>&nbsp;&nbsp;<html:text name="followUpForm"property="claimNamAmt" styleId="claimNamAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td>
</tr> -->
<tr>
<td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td><td width="80%" class="tbcellBorder"><html:textarea name="followUpForm" property="namRemarks" styleId="namRemarks"style="overflow-y:auto;WIDTH:60em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr></table></logic:equal>
<logic:equal value="Yes" name="followUpForm" property="showFcx">
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.NonTech"/></b></td></tr>
</table>
<table width="100%" border="0">
<tr >
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.PharmacyBill"/><font color="red">*</font>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFcxPharmBill" styleId="claimFcxPharmBill" size="12"title="Pharmacy Bill" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.ConsultationBill"/><font color="red">*</font>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFcxConsulBill" styleId="claimFcxConsulBill" size="12"title="Consultation Bill" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.InvestigationBill"/><font color="red">*</font>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFcxInvestBill" styleId="claimFcxInvestBill" size="12"title="Investigation Bill" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.TotalBillAmt"/><font color="red">*</font>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFcxAmt" styleId="claimFcxAmt" disabled="true" size="12" title="Total Bill Amount" maxlength="6" onchange="validateNumber('Total Bill Amount',this)"/>
</td>
</tr>
<!-- </table>
<table width="100%" border="0" style="display:none" id="fcxAmtTable"> -->
<tr style="display:none" id="fcxAmtTable">
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.PkgAmount"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newPckAmt" styleId="newPckAmt" disabled="true" size="12"title="Package Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.ClaimFwdAmount"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newFwdAmt" styleId="newFwdAmt" disabled="true" size="12"title="Claim Forwarded Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:50%" colspan="3">
<fmt:message key="EHF.MaxAmtCanBeRaised"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newtotAmt" styleId="newtotAmt" disabled="true" size="12"title="Total Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
</tr>
</table>
<table width="100%" border="0" height="150px"> 
<tr><th width="40%" class="labelheading1 tbcellCss"><fmt:message key="EHF.FollowUpCliamChkList"/></th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Matching"/></th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Remarks"/></th></tr>
<tr>
<td width="40%" class="labelheading1 tbcellCss"><b>Photo verification</b><br>&nbsp;<br><fmt:message key="EHF.NonTechChkListPhoto1"/><font color="red">*</font><br>&nbsp;<br><fmt:message key="EHF.NonTechChkListPhoto2"/><font color="red">*</font></td>
<td width="30%" align="center" class="tbcellBorder">
<html:radio name="followUpForm" property="exeDisphotoChklst" value="Y" title="Yes" styleId="exeDisphotoChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="exeDisphotoChklst" value="N" title="No" styleId="exeDisphotoChklst" /><fmt:message key="EHF.Claims.No"/>
<br>&nbsp;<br>
<html:radio name="followUpForm" property="exePatphotoChklst" value="Y" title="Yes" styleId="exePatphotoChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="exePatphotoChklst" value="N" title="No" styleId="exePatphotoChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td width="30%" class="tbcellBorder">
<html:textarea name="followUpForm" property="exeDisphotoremark" styleId="exeDisphotoremark"style="overflow-y:auto;WIDTH:25em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Photo Verification Remarks"/>
<html:textarea name="followUpForm" property="exePatphotoRemark" styleId="exePatphotoRemark"style="overflow-y:auto;WIDTH:25em;height:3em"onkeydown="returnmaxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Photo Verification Remarks"/>
</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Document Verification</b><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListDoc1"/><font color="red">*</font><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListDoc2"/><font color="red">*</font><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListDoc3"/><font color="red">*</font><br>&nbsp;<br>
<td align="center" class="tbcellBorder">
<html:radio name="followUpForm" property="exeAcqvrifyChklst" value="Y" title="Yes" styleId="exeAcqvrifyChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="exeAcqvrifyChklst" value="N" title="No" styleId="exeAcqvrifyChklst" /><fmt:message key="EHF.Claims.No"/>
<br>&nbsp;<br>
<html:radio name="followUpForm" property="exeMedverifyChklst" value="Y" title="Yes" styleId="exeMedverifyChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="exeMedverifyChklst" value="N" title="No" styleId="exeMedverifyChklst" /><fmt:message key="EHF.Claims.No"/>
<br>&nbsp;<br>
<html:radio name="followUpForm" property="exeQuantverifyChklst" value="Y" title="Yes" styleId="exeQuantverifyChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="exeQuantverifyChklst" value="N" title="No" styleId="exeQuantverifyChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td class="tbcellBorder">
<html:textarea name="followUpForm" property="exeAcqverifyRemark" styleId="exeAcqverifyRemark"style="overflow-y:auto;WIDTH:25em;height:3em" onkeydown="returnmaxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Document Verification Remarks"/>
<html:textarea name="followUpForm" property="exeMedVerifyRemark" styleId="exeMedVerifyRemark"style="overflow-y:auto;WIDTH:25em;height:3em"onkeydown="returnmaxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Document Verification Remarks"/>
<html:textarea name="followUpForm" property="exeQuantverifyRemark" styleId="exeQuantverifyRemark"style="overflow-y:auto;WIDTH:25em;height:3em" onkeydown="returnmaxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Document Verification Remarks"/>
</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Investigation Reports Verification</b><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListInvest1"/><font color="red">*</font></td>
<td align="center" class="tbcellBorder">
<html:radio name="followUpForm" property="exereprtcheckChklst" value="Y" title="Yes" styleId="exereprtcheckChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="exereprtcheckChklst" value="N" title="No" styleId="exereprtcheckChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td class="tbcellBorder"><html:textarea name="followUpForm" property="exeReprtcheckRemark" styleId="exeReprtcheckRemark"style="overflow-y:auto;WIDTH:25em;height:3em" onkeydown="returnmaxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Investigation Reports Verification Remarks"/></td>
</tr>
<!-- <tr>
<td colspan="5" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/></b>&nbsp;&nbsp;<html:text name="followUpForm"property="claimFcxAmt" styleId="claimFcxAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td>
</tr> -->
<tr>
<td colspan="5" class="labelheading1 tbcellCss">
<table>
<tr><td><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b>&nbsp;&nbsp;</td>
<td><html:textarea name="followUpForm" property="fcxRemark" styleId="fcxRemark"style="overflow-y:auto;WIDTH:40em;height:3em" onkeydown="returnmaxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'FCX Remarks')" title="Remarks"/></td>
</tr> 
</table>
</td>
</tr>
</table>
</logic:equal>
<logic:equal value="Yes" name="followUpForm" property="showFtd">
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.TrustDoc"/></b></td></tr>
</table>
<table width="100%" border="0">
<tr >
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.PharmacyBill"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFTDPharmBill" styleId="claimFTDPharmBill" size="12" title="Pharmacy Bill" maxlength="6" onchange="findTotalAmtFTD()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.ConsultationBill"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFTDConsulBill" styleId="claimFTDConsulBill" size="12" title="Consultation Bill" maxlength="6" onchange="findTotalAmtFTD()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.InvestigationBill"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimFTDInvestBill" styleId="claimFTDInvestBill" size="12" title="Investigation Bill" maxlength="6" onchange="findTotalAmtFTD()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
	<fmt:message key="EHF.Claims.FAprAmt"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:15%">
	<html:text name="followUpForm" size="12"property="claimFtdAmt" styleId="claimFtdAmt" disabled="true" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/>
</td> 

</tr>
<!-- </table>
<table width="100%" border="0" style="display:none" id="ftdAmtTable"> -->
<tr style="display:none" id="ftdAmtTable">
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.PkgAmount"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newFTDPckAmt" styleId="newFTDPckAmt" size="12" disabled="true" title="Package Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.ClaimFwdAmount"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newFTDFwdAmt" styleId="newFTDFwdAmt" size="12" disabled="true"title="Claim Forwarded Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:50%" colspan="3">
<fmt:message key="EHF.MaxAmtCanBeRaised"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newFTDtotAmt" styleId="newFTDtotAmt" size="12" disabled="true"title="Total Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
</tr>
</table>
<table width="100%" border="0" height="150px"> 
<tr><th width="40%" class="labelheading1 tbcellCss"><fmt:message key="EHF.FollowUpCliamChkList"/></th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Matching"/></th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Remarks"/></th></tr>
<tr>
<td width="40%" class="labelheading1 tbcellCss"><fmt:message key="EHF.TrustDoctChkList1"/><font color="red">*</font></td>
<td width="30%" align="center"class="tbcellBorder">
<html:radio name="followUpForm" property="ftdRemarkvrifedChklst" value="Y" title="Yes" styleId="ftdRemarkvrifedChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="ftdRemarkvrifedChklst" value="N" title="No" styleId="ftdRemarkvrifedChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td width="30%" class="tbcellBorder"><html:textarea name="followUpForm" property="ftdRemarkvrifedRemark" styleId="ftdRemarkvrifedRemark"style="overflow-y:auto;overflow-y:auto;WIDTH:25em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="FCX Remarks Verified"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><fmt:message key="EHF.TrustDoctChkList2"/><font color="red">*</font></td>
<td align="center" class="tbcellBorder">
<html:radio name="followUpForm" property="ftdBeneficiryChklst" value="Y" title="Yes" styleId="ftdBeneficiryChklst"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="followUpForm" property="ftdBeneficiryChklst" value="N" title="No" styleId="ftdBeneficiryChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td class="tbcellBorder"><html:textarea name="followUpForm" property="ftdBeneficiryRemark" styleId="ftdBeneficiryRemark"style="overflow-y:auto;WIDTH:25em;height:3em"onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Beneficiary – Periodic Telephonic Enquiry')"title="Beneficiary – Periodic Telephonic Enquiry"/></td>
</tr>
<tr>
<%-- <tdclass="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/><font color="red">*</font></b></td>
<td align="left" class="tbcellBorder" colspan="2"><html:text name="followUpForm"property="claimFtdAmt" styleId="claimFtdAmt" disabled="true" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td> --%>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b>
<td colspan="5" class="tbcellBorder"><html:textarea name="followUpForm" property="ftdRmks" styleId="ftdRmks"style="overflow-y:auto;WIDTH:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr>
</table>
</logic:equal>
<logic:equal value="Yes" name="followUpForm" property="showCh">
<table class="tbheader">
<tr><td colspan="2"><b><fmt:message key="EHF.Title.CH"/></b></td></tr>
</table>
<table width="100%" border="0">
<tr >
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.PharmacyBill"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimCHPharmBill" styleId="claimCHPharmBill" size="12" title="Pharmacy Bill" maxlength="6" onchange="findTotalAmtCH()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.ConsultationBill"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimCHConsulBill" styleId="claimCHConsulBill" size="12"title="Consultation Bill" maxlength="6" onchange="findTotalAmtCH()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.InvestigationBill"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="claimCHInvestBill" styleId="claimCHInvestBill" size="12"title="Investigation Bill" maxlength="6" onchange="findTotalAmtCH()"/>
</td>
<td style="width:15%" class="labelheading1 tbcellCss">
<fmt:message key="EHF.Claims.FAprAmt"/><font color="red">*</font><br>&nbsp;<br>
</td>
<td style="width:10%" class="tbcellBorder" >
	<html:text name="followUpForm" size="12" property="claimChAmt" styleId="claimChAmt" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)" disabled="true" />
</td> 

</tr>
<!-- </table>
<table width="100%" border="0" style="display:none" id="chAmtTable"> -->
<tr style="display:none" id="chAmtTable">
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.PkgAmount"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newCHPckAmt" styleId="newCHPckAmt" size="12"disabled="true" title="Package Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:15%">
<fmt:message key="EHF.ClaimFwdAmount"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newCHFwdAmt" styleId="newCHFwdAmt" size="12"disabled="true"title="Claim Forwarded Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
<td class="labelheading1 tbcellCss" style="width:50%" colspan="3">
<fmt:message key="EHF.MaxAmtCanBeRaised"/>
</td>
<td class="tbcellBorder" style="width:10%">
<html:text name="followUpForm"property="newCHtotAmt" styleId="newCHtotAmt" size="12"disabled="true"title="Total Amount" maxlength="6" onchange="findTotalAmt()"/>
</td>
</tr>
</table>
<table width="100%" border="0" >
<tr>
<%-- <td width="40%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/><font color="red">*</font></b></td>
<td width="60%" align="left" class="tbcellBorder" ><html:text name="followUpForm"property="claimChAmt" styleId="claimChAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td> --%>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td>
<td><html:textarea name="followUpForm" property="chRemark" styleId="chRemark"style="overflow-y:auto;WIDTH:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr>
</table></logic:equal>

<logic:equal value="Yes" name="followUpForm" property="showAco">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.Account"/></b></td></tr>
</table></td></tr>
<!--<tr><td>
<table width="100%">
 <tr>
<td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="followUpForm" property="acoAprAmt" styleId="acoAprAmt" maxlength="6" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
</tr>
</table>
</td></tr>-->
<tr><td class="labelheading1 tbcellCss">
<table width="100%">
<tr ><td width="15%" ><b><fmt:message key="EHF.Remarks"/></b>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><html:textarea name="followUpForm" property="acoRemark" styleId="acoRemark" style="overflow-y:auto;WIDTH:80%;height:3em;resize: none" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr>
</table></td></tr>
</logic:equal>

</td></tr>
<tr><td colspan="5" align="center">&nbsp;</td></tr>

<c:if test="${fn:length(buttons) > 0}">
<tr>
<td colspan="6" align="center">
<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
<button class="but btn btn-danger" type="button"value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
</c:forEach>
<logic:equal value="Yes" name="followUpForm" property="addAttach">
<button class="but btn btn-success" type="button"value="addAttachment" name="addAttachment" onclick="javascript:fn_attachments();">Add/View Attachments</button>
<button class="but btn btn-primary" type="button"value="printPrescription" name="printPrescription" onclick="javascript:fn_printPrescription();">View Prescription</button>
<button class="but btn btn-primary" type="button"value="printDtrs" name="printDtrs" onclick="javascript:fn_printDtrs();">View DTRS</button>
</logic:equal>
<logic:equal value="Yes" name="followUpForm" property="viewAttach">
<button class="but btn btn-primary" type="button"value="viewAttachment" name="viewAttachment" onclick="javascript:fn_attachments();">View Attachments</button>
</logic:equal>

</td>
</tr>
</c:if>
<tr><td colspan="5" align="center">&nbsp;</td></tr>
<logic:present name="followUpForm" property="lstworkFlow">
<bean:size id="wrkList" name="followUpForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="1" width="100%" style="table-layout: fixed;" cellpadding="1" cellspacing="1" align="center">
<trclass="tbheader"align="center"><td colspan="7">&nbsp;<b>Work Flow</b></td></tr></table></td></tr>
<tr><td colspan="2">
<table border="1" width="100%" style="table-layout: fixed;" cellpadding="1" cellspacing="1" align="center"id="testtable">
<trclass="tbheader" ><td width="3%" class="labelheading1 tbcellCss"><b>SNo</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Date & Time</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Name</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Role</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Remarks</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Approve Amount</b></td>
<td width="12%" class="labelheading1 tbcellCss"><b>Action</b></td>
</tr>
<logic:iterate id="results1" name="followUpForm" property="lstworkFlow" indexId="index1" >
<tr>
<td style="word-wrap:break-word;"class="tbcellBorder"><%=index1+1%></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="fpauditDate" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditName" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder">
<c:choose>
<c:when test="${followUPSchemeID eq 'CD201' && results1.auditRole eq 'MITHRA'}" >
VAIDYA MITHRA
</c:when>
<c:otherwise >
<bean:write name="results1" property="auditRole" />
</c:otherwise>
</c:choose> </td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="cexRemark" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="COUNT" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
</tr>
</logic:iterate>
</table>
</td></tr>
</logic:greaterThan>
</logic:present>
</table>
</logic:empty>


<c:if test="${sentBack eq true }">
<tr><td><br><br></td></tr>
<tr>
<td>



<table width="100%">
<tr ><td width="100%" class="labelheading1 tbcellCss">Remarks :&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea name="followUpForm" property="ceoRemark"styleId="ceoRemarks" style="WIDTH:90%;height:4em;overflow-y:fixed" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr>






<tr><td colspan="3"align="center">
<button class="but" type="button"id="approveBtn" onclick="javascript:fn_submitSentBackNxtLvl();" >Submit</button>

</td>
</tr>
</table>

</td></tr>

</c:if>


<script type="text/javascript">
var roleId='${UserRole1}';
var lField = '';
var displayMsg='';
var OnloadCaseOpen='${OnloadCaseOpen}';
if(OnloadCaseOpen=='Y')
{

}


/*added by venkatesh for send back functionality*/

function fn_submitSentBackNxtLvl()
{
var followupId='${followUpID}';
var followupStatus='${followupStatus}';
var sendBackFlag="${sentBack}";
var remarks=document.getElementById("ceoRemarks").value;
document.getElementById('approveBtn').disabled=true;

var xmlhttp;
var url;
if (window.XMLHttpRequest)
{
 xmlhttp=new XMLHttpRequest();
}
else if (window.ActiveXObject)
{
 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
else
{
 alert("Your Browser Does Not Support XMLHTTP!");
}
 url = "/Operations/followUpAction.do?actionFlag=updateSentBackCases&followupId="+followupId+"&followupStatus="+followupStatus+"&remarks="+remarks+"&sendBackFlag="+sendBackFlag+"&moduleType=followUp";
 xmlhttp.onreadystatechange=function()
{
if(xmlhttp.readyState==4)
{
 var resultArray=xmlhttp.responseText;
var resultArray = resultArray.split("*");
if(resultArray[0]=="SessionExpired"){
alert("Session has been expired");
 parent.sessionExpireyClose();
// var fr = partial(parent.sessionExpireyClose);
// jqueryInfoMsg('Session details',"Session has been expired",fr);
}
else
{
alert(resultArray[0]);
fn_back();
}
}}
 xmlhttp.open("Post",url,true);
xmlhttp.send(null);
}

function fn_back()
{
parent.parent.parent.fn_followUpPendingCases(); 
}




function fn_attachments()
{
var caseApprovalFlag = '${casesForApproval}';
var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfFollowUp&followupId="+document.forms[0].followUpId.value+"&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag=Y";
parent.parent.parent.claimAttachWindow = window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
}
function fn_onloadDisabled(){

if('${followupStatus}' != '' && ('${followupStatus}' == 'CD67' || '${followupStatus}' == 'CD70'))
{

if(roleId=='<fmt:message key="EHF.Claims.Role.MEDCO"/>')
{
if(document.getElementById("claimNwhAmt")!=null)
document.getElementById("claimNwhAmt").disabled=true;
}
}
if('${disableAll}'=='Yes'){

if(roleId==''){
	
	if(ftdFlag!=null && ftdFlag=='Y')
		{
disableMedco();
disableNAM();
disableFCX();
disableFTD();
disableCH();
disableACO();
		}
	if(ftdFlag!=null && ftdFlag=='N')
	{
disableMedco();
disableNAM();
disableFCX();
//disableFTD();
disableCH();
disableACO();
	}
}
if(roleId=='<fmt:message key="EHF.Claims.Role.MEDCO"/>'){

 disableMedco();
}
//if(roleId=='<fmt:message key="EHF.Claims.Role.NAM"/>'){
if(roleId=='<fmt:message key="EHF.Claims.Role.NAM"/>'){
disableMedco();
disableNAM();
}
if(roleId=='<fmt:message key="EHF.Claims.Role.FCX"/>'){
disableMedco();
disableNAM();
disableFCX();
}
if(roleId=='<fmt:message key="EHF.Claims.Role.FTD"/>'){
disableMedco();
disableNAM();
disableFCX();
disableFTD();
}
if(ftdFlag!=null && ftdFlag=='Y')
	{
if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
disableMedco();
disableNAM();
disableFCX();
disableFTD();
disableCH();
}
	}
if(ftdFlag!=null && ftdFlag=='N')
{
if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
disableMedco();
disableNAM();
disableFCX();
disableCH();
}
}
if(ftdFlag!=null && ftdFlag=='Y')
	{
if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>'){
disableMedco();
disableNAM();
disableFCX();
disableFTD();
disableCH();
disableACO();
}
}
if(ftdFlag!=null && ftdFlag=='N')
	{
	if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>'){
	disableMedco();
	disableNAM();
	disableFCX();
	//disableFTD();
	disableCH();
	disableACO();
	}
	}
}
else{
if(roleId=='<fmt:message key="EHF.Claims.Role.NAM"/>'){
disableMedco();
}
if(roleId=='<fmt:message key="EHF.Claims.Role.FCX"/>'){
disableMedco();
disableNAM();
enableFCXAMT();
}
if(roleId=='<fmt:message key="EHF.Claims.Role.FTD"/>'){
disableMedco();
disableNAM();
disableFCX();
enableFTDAMT();
}
if(ftdFlag!=null && ftdFlag=='Y')
	{
if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
disableMedco();
disableNAM();
disableFCX();
disableFTD();
enableCHAMT();
}
	}
if(ftdFlag!=null && ftdFlag=='N')
{
if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
disableMedco();
disableNAM();
disableFCX();
//disableFTD();
enableCHAMT();
}
}
if(ftdFlag!=null && ftdFlag=='Y')
	{
if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>'){
disableMedco();
disableNAM();
disableFCX();
disableFTD();
disableCH();
}
}
if(ftdFlag!=null && ftdFlag=='N')
{
if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>'){
disableMedco();
disableNAM();
disableFCX();
//disableFTD();
disableCH();
}
}
}
}

function disableMedco(){
if(document.getElementById("claimNwhAmt")!=null)
document.getElementById("claimNwhAmt").disabled=true;
if(document.getElementById("medcoRemarks")!=null)
document.getElementById("medcoRemarks").readOnly=true;document.getElementById("medcoRemarks").style.background='#ECE9D8';document.getElementById('medcoRemarks').style.color='#ACA899';
}
function disableNAM(){
//if(document.getElementById("claimNamAmt")!=null)
//document.getElementById("claimNamAmt").disabled=true;
if(document.getElementById("namRemarks")!=null)
document.getElementById("namRemarks").readOnly=true;document.getElementById("namRemarks").style.background='#ECE9D8';document.getElementById('namRemarks').style.color='#ACA899';
}
function disableFCX(){

if(document.getElementById("claimFcxPharmBill")!=null)
{
document.getElementById("claimFcxPharmBill").readOnly=true;
document.getElementById("claimFcxPharmBill").style.background='#ECE9D8';
document.getElementById('claimFcxPharmBill').style.color='#ACA899';
}

if(document.getElementById("claimFcxConsulBill")!=null)
{
document.getElementById("claimFcxConsulBill").readOnly=true;
document.getElementById("claimFcxConsulBill").style.background='#ECE9D8';
document.getElementById('claimFcxConsulBill').style.color='#ACA899';
}

if(document.getElementById("claimFcxInvestBill")!=null)
{
document.getElementById("claimFcxInvestBill").readOnly=true;
document.getElementById("claimFcxInvestBill").style.background='#ECE9D8';
document.getElementById('claimFcxInvestBill').style.color='#ACA899';
}

if(document.forms[0].exeDisphotoChklst!= null){
 document.forms[0].exeDisphotoChklst[0].disabled=true;
 document.forms[0].exeDisphotoChklst[1].disabled=true;
}
if(document.forms[0].exePatphotoChklst!=null){
document.forms[0].exePatphotoChklst[0].disabled=true;
document.forms[0].exePatphotoChklst[1].disabled=true;
}
if(document.forms[0].exeAcqvrifyChklst!=null){
document.forms[0].exeAcqvrifyChklst[0].disabled=true;
document.forms[0].exeAcqvrifyChklst[1].disabled=true;
}
if(document.forms[0].exeMedverifyChklst!=null){
document.forms[0].exeMedverifyChklst[0].disabled=true;
document.forms[0].exeMedverifyChklst[1].disabled=true;
}
if(document.forms[0].exeQuantverifyChklst!=null){
document.forms[0].exeQuantverifyChklst[0].disabled=true;
document.forms[0].exeQuantverifyChklst[1].disabled=true;
}
if(document.forms[0].exereprtcheckChklst!=null){
document.forms[0].exereprtcheckChklst[0].disabled=true;
document.forms[0].exereprtcheckChklst[1].disabled=true;
}
if(document.getElementById("exeDisphotoremark")!=null)
document.getElementById("exeDisphotoremark").readOnly=true;document.getElementById("exeDisphotoremark").style.background='#ECE9D8';document.getElementById('exeDisphotoremark').style.color='#ACA899';
if(document.getElementById("exePatphotoRemark")!=null)
document.getElementById("exePatphotoRemark").readOnly=true;document.getElementById("exePatphotoRemark").style.background='#ECE9D8';document.getElementById('exePatphotoRemark').style.color='#ACA899';
if(document.getElementById("exeAcqverifyRemark")!=null)
document.getElementById("exeAcqverifyRemark").readOnly=true;document.getElementById("exeAcqverifyRemark").style.background='#ECE9D8';document.getElementById('exeAcqverifyRemark').style.color='#ACA899';
if(document.getElementById("exeMedVerifyRemark")!=null)
document.getElementById("exeMedVerifyRemark").readOnly=true;document.getElementById("exeMedVerifyRemark").style.background='#ECE9D8';document.getElementById('exeMedVerifyRemark').style.color='#ACA899';
if(document.getElementById("exeQuantverifyRemark")!=null)
document.getElementById("exeQuantverifyRemark").readOnly=true;document.getElementById("exeQuantverifyRemark").style.background='#ECE9D8';document.getElementById('exeQuantverifyRemark').style.color='#ACA899';
if(document.getElementById("exeReprtcheckRemark")!=null)
document.getElementById("exeReprtcheckRemark").readOnly=true;document.getElementById("exeReprtcheckRemark").style.background='#ECE9D8';document.getElementById('exeReprtcheckRemark').style.color='#ACA899';
//if(document.getElementById("claimFcxAmt")!=null)
 // document.getElementById('claimFcxAmt').disabled=true;
if(document.getElementById("fcxRemark")!=null)
document.getElementById("fcxRemark").readOnly=true;document.getElementById("fcxRemark").style.background='#ECE9D8';document.getElementById('fcxRemark').style.color='#ACA899';
}
function enableFCXAMT()
{
document.getElementById("fcxAmtTable").style.display="";

if(document.forms[0].packageAmt.value!=null)
document.getElementById("newPckAmt").value=document.forms[0].packageAmt.value;
else
document.getElementById("newPckAmt").value=0;

if(document.forms[0].newClmFwdAmt.value!=null)
document.getElementById("newFwdAmt").value=document.forms[0].newClmFwdAmt.value;
else
document.getElementById("newFwdAmt").value=0;

document.getElementById("newtotAmt").value=(Number(document.getElementById("newPckAmt").value)+Number(document.getElementById("newFwdAmt").value)).toString();;

}
function enableFTDAMT()
{
document.getElementById("ftdAmtTable").style.display="";
if(document.forms[0].packageAmt.value!=null)
document.getElementById("newFTDPckAmt").value=document.forms[0].packageAmt.value;
else
document.getElementById("newFTDPckAmt").value=0;

if(document.forms[0].newClmFwdAmt.value!=null)
document.getElementById("newFTDFwdAmt").value=document.forms[0].newClmFwdAmt.value;
else
document.getElementById("newFTDFwdAmt").value=0;

document.getElementById("newFTDtotAmt").value=(Number(document.getElementById("newFTDPckAmt").value)+Number(document.getElementById("newFTDFwdAmt").value)).toString();;

}
function enableCHAMT()
{
document.getElementById("chAmtTable").style.display="";
if(document.forms[0].packageAmt.value!=null)
document.getElementById("newCHPckAmt").value=document.forms[0].packageAmt.value;
else
document.getElementById("newCHPckAmt").value=0;

if(document.forms[0].newClmFwdAmt.value!=null)
document.getElementById("newCHFwdAmt").value=document.forms[0].newClmFwdAmt.value;
else
document.getElementById("newCHFwdAmt").value=0;

document.getElementById("newCHtotAmt").value=(Number(document.getElementById("newCHPckAmt").value)+Number(document.getElementById("newCHFwdAmt").value)).toString();;

}
function disableFTD(){

if(document.getElementById("claimFTDPharmBill")!=null)
{
document.getElementById("claimFTDPharmBill").readOnly=true;
document.getElementById("claimFTDPharmBill").style.background='#ECE9D8';
document.getElementById('claimFTDPharmBill').style.color='#ACA899';
}

if(document.getElementById("claimFTDConsulBill")!=null)
{
document.getElementById("claimFTDConsulBill").readOnly=true;
document.getElementById("claimFTDConsulBill").style.background='#ECE9D8';
document.getElementById('claimFTDConsulBill').style.color='#ACA899';
}

if(document.getElementById("claimFTDInvestBill")!=null)
{
document.getElementById("claimFTDInvestBill").readOnly=true;
document.getElementById("claimFTDInvestBill").style.background='#ECE9D8';
document.getElementById('claimFTDInvestBill').style.color='#ACA899';
}


if(document.forms[0].ftdRemarkvrifedChklst!=null){
document.forms[0].ftdRemarkvrifedChklst[0].disabled=true;
document.forms[0].ftdRemarkvrifedChklst[1].disabled=true;
}
if(document.forms[0].ftdBeneficiryChklst!=null){
document.forms[0].ftdBeneficiryChklst[0].disabled=true;
document.forms[0].ftdBeneficiryChklst[1].disabled=true;
}
if(document.getElementById("ftdRemarkvrifedRemark")!=null)
document.getElementById("ftdRemarkvrifedRemark").readOnly=true;document.getElementById("ftdRemarkvrifedRemark").style.background='#ECE9D8';document.getElementById('ftdRemarkvrifedRemark').style.color='#ACA899';
if(document.getElementById("ftdBeneficiryRemark")!=null)
document.getElementById("ftdBeneficiryRemark").readOnly=true;document.getElementById("ftdBeneficiryRemark").style.background='#ECE9D8';document.getElementById('ftdBeneficiryRemark').style.color='#ACA899';
if(document.getElementById("claimFtdAmt")!=null)
document.getElementById("claimFtdAmt").disabled=true;
if(document.getElementById("ftdRmks")!=null)
document.getElementById("ftdRmks").readOnly=true;document.getElementById("ftdRmks").style.background='#ECE9D8';document.getElementById('ftdRmks').style.color='#ACA899';
}
function disableCH(){

if(document.getElementById("claimCHPharmBill")!=null)
{
document.getElementById("claimCHPharmBill").readOnly=true;
document.getElementById("claimCHPharmBill").style.background='#ECE9D8';
document.getElementById('claimCHPharmBill').style.color='#ACA899';
}

if(document.getElementById("claimCHConsulBill")!=null)
{
document.getElementById("claimCHConsulBill").readOnly=true;
document.getElementById("claimCHConsulBill").style.background='#ECE9D8';
document.getElementById('claimCHConsulBill').style.color='#ACA899';
}

if(document.getElementById("claimCHInvestBill")!=null)
{
document.getElementById("claimCHInvestBill").readOnly=true;
document.getElementById("claimCHInvestBill").style.background='#ECE9D8';
document.getElementById('claimCHInvestBill').style.color='#ACA899';
}

if(document.getElementById("claimChAmt")!=null)
document.getElementById("claimChAmt").disabled=true;
if(document.getElementById("chRemark")!=null)
document.getElementById("chRemark").readOnly=true;document.getElementById("chRemark").style.background='#ECE9D8';document.getElementById('chRemark').style.color='#ACA899';
}
function disableACO(){
//if(document.getElementById("acoAprAmt")!=null)
//document.getElementById('acoAprAmt').disabled=true;
if(document.getElementById("acoRemark")!=null)
document.getElementById("acoRemark").readOnly=true;document.getElementById("acoRemark").style.background='#ECE9D8';document.getElementById('acoRemark').style.color='#ACA899';
}

function fn_buttonClicked(buttonId){
var errMsg ='';


if(claimInit=='Y'){
alert("Failed to Initiate because previous Followup has not been initiated.");
//jqueryAlertMsg('FollowUp Information','Failed to Initiate because previous Followup Payment has not been done.');
return;
}
if(roleId=='<fmt:message key="EHF.Claims.Role.MEDCO"/>')
errMsg = validateNWH();
if(roleId=='<fmt:message key="EHF.Claims.Role.NAM"/>')
errMsg = validateNam();
if(roleId=='<fmt:message key="EHF.Claims.Role.FCX"/>')
errMsg = validateNonTechCheckLst();
if(roleId=='<fmt:message key="EHF.Claims.Role.FTD"/>')
errMsg = validateTrustDocCheckLst(buttonId);
if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>')
errMsg = validateClaimHead(buttonId);
if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>')
errMsg = validateACOList(buttonId);

if (buttonId == '<fmt:message key="EHF.Button.Initiate"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Initiate"/>';
if (buttonId == '<fmt:message key="EHF.Button.Forward"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Forward"/>';
if (buttonId == '<fmt:message key="EHF.Button.Approve"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Approve"/>';
if (buttonId == '<fmt:message key="EHF.Button.Reject"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Reject"/>';
if (buttonId == '<fmt:message key="EHF.Button.Pending"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Pending"/>';
if (buttonId == '<fmt:message key="EHF.Button.RecmdApp"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.RecmdApp"/>';
if (buttonId == '<fmt:message key="EHF.Button.RecmdRej"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.RecmdRej"/>';
if (buttonId == '<fmt:message key="EHF.Button.RecmdPend"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.RecmdPend"/>';
if (buttonId == '<fmt:message key="EHF.Button.Save"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Save"/>';
if (buttonId == '<fmt:message key="EHF.Button.Submit"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Submit"/>';
if (buttonId == '<fmt:message key="EHF.Button.Verify"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.Verify"/>';
if (buttonId == '<fmt:message key="EHF.Button.update"/>')
displayMsg = '<fmt:message key="EHF.Button.Msg.update"/>';
if (buttonId == '<fmt:message key="EHF.Button.Revert"/>')
	displayMsg = '<fmt:message key="EHF.Button.Msg.revert"/>';

if(!errMsg=='')
{
var fr = partial(focusFieldView,document.getElementById(lField));
jqueryAlertMsg('FollowUp mandatory fields',errMsg,fr);
return false;
}
else{

var xmlhttp;
var url;
if (window.XMLHttpRequest) {
xmlhttp = new XMLHttpRequest();
} else if (window.ActiveXObject) {
xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
} else {
jqueryAlertMsg('Browser Validation',
"Your Browser Does Not Support XMLHTTP!");
}

url = '/<%=context%>/followUpAction.do?actionFlag=checkMandatoryAttch&followUpId='+document.forms[0].followUpId.value+'&attachType=ehfFollowUp&callType=Ajax';
 xmlhttp.onreadystatechange=function()
{ 
if(xmlhttp.readyState==4)
{
 var resultArray=xmlhttp.responseText;
var resultArray = resultArray.split("*");
if(resultArray[0]!=null)
{

if(resultArray[0]=="SessionExpired"){
var fr = partial(parent.sessionExpireyClose());
jqueryInfoMsg('Session Validation', "Session has been expired" ,fr);

}
else if(resultArray[0] =='success')
 {
var fr = partial(fn_confirmMsg,buttonId);
 jqueryConfirmMsg('FollowUp Submission',displayMsg,fr);
 
 }
 else
 {
 jqueryAlertMsg('FollowUp mandatory check',resultArray[0]);
return;
 }
} 
}
}
 xmlhttp.open("Post",url,true);
xmlhttp.send(null);

}
}
function fn_confirmMsg(buttonId){

var caseApprovalFlag = '${casesForApproval}';
var OnloadCaseOpen='${OnloadCaseOpen}';
if(document.getElementById("claimFcxAmt")!=null)
{
if(document.getElementById("claimFcxAmt").disabled=true)
document.getElementById("claimFcxAmt").disabled=false;
}
if(document.getElementById("claimFtdAmt")!=null)
{
if(document.getElementById("claimFtdAmt").disabled=true)
document.getElementById("claimFtdAmt").disabled=false;
}
if(document.getElementById("claimChAmt")!=null)
{
if(document.getElementById("claimChAmt").disabled=true)
document.getElementById("claimChAmt").disabled=false;
}
/* if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>' && (buttonId=='CD31' ||buttonId=='CD30'))
{
document.getElementById("claimCHPharmBill").value=0;
document.getElementById("claimCHConsulBill").value=0;
document.getElementById("claimCHInvestBill").value=0;
document.getElementById("claimChAmt").value=0;
}
if(roleId=='<fmt:message key="EHF.Claims.Role.FTD"/>' && (buttonId=='CD31' ||buttonId=='CD30'))
{


document.getElementById("claimFTDPharmBill").value=0;
document.getElementById("claimFTDConsulBill").value=0;
document.getElementById("claimFTDInvestBill").value=0;
document.getElementById("claimFtdAmt").value=0; 
} */
document.forms[0].action="/Operations/followUpAction.do?actionFlag=saveFollowupClaim&followUpid="+document.forms[0].followUpId.value+"&CaseId="+document.forms[0].caseId.value+"&clinicalId="+document.forms[0].clinicalId.value+"&actionDone="+buttonId+"&casesForApproval="+caseApprovalFlag+"&OnloadCaseOpen="+OnloadCaseOpen; 
document.forms[0].submit(); 
}
function validateNWH(){
var errMsg ='';lField='';
var billAmt = document.getElementById("claimNwhAmt").value;
var PackAmt = document.forms[0].packageAmt.value;
if(document.getElementById("claimNwhAmt").value==null || document.getElementById("claimNwhAmt").value=='')
{ if(lField=='') 
lField="claimNwhAmt";
if(errMsg=='') 
 errMsg=errMsg+"Please Enter Claim Amount <br>";
 }

/*else {
if(billAmt*1=="0"){
if(lField=='') 
lField="claimNwhAmt"; 
document.getElementById("claimNwhAmt").value="";
errMsg=errMsg+"Claim Amount cannot be zero <br>";
}
}*/
else
	{
	if(document.getElementById("medcoRemarks").value==null || document.getElementById("medcoRemarks").value==''||document.getElementById("medcoRemarks").value.trim()==null || document.getElementById("medcoRemarks").value.trim()=='')
	{ 
		if(lField=='') lField="medcoRemarks"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>"; 
	}
	else if(parseInt(billAmt*1) >(Number(document.forms[0].packageAmt.value) + Number(document.forms[0].newClmFwdAmt.value)))
	{
	var finalAmt=Number(document.forms[0].packageAmt.value) + Number(document.forms[0].newClmFwdAmt.value);
	if(lField=='') lField="claimNwhAmt"; if(errMsg=='') errMsg=errMsg+"Claim Amount should be Less than sum of Package Amount and Carry Forward amount i.e "+finalAmt;
	document.getElementById("claimNwhAmt").value="0";
	}
	else 
	{
	var totBillAmt=document.getElementById("claimNwhAmt").value;
	if(totBillAmt*1<"2")
		{
		if(lField=='') lField="claimAmt";
		if(errMsg=='') errMsg=errMsg+"Bill Amount cannot be less than Rs.2<br>";
		}
	}
	}

/* else
if(parseInt(billAmt*1)>parseInt(tot*1))
{ if(lField=='') lField="claimNwhAmt"; if(errMsg=='') errMsg=errMsg+"Claim Amount should be Less than sum of Package Amount and Carry Forward amount i.e "+tot;
document.getElementById("claimNwhAmt").value="";
} */

return errMsg;
}
function validateNam(){
var errMsg='';lField='';

//if(parseInt(document.getElementById("claimNamAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
//{ if(lField=='') lField="claimNamAmt"; errMsg=errMsg+"Final Approve Amount should be Less than or equal to Claim Amount Approved by Medco. <br>";}

//if(document.getElementById("claimNamAmt").value==null || document.getElementById("claimNamAmt").value=='')
//{ if(lField=='') lField="claimNamAmt"; errMsg=errMsg+"Please Enter Final Approve Amount <br>";}
if(document.getElementById("namRemarks").value==null || document.getElementById("namRemarks").value==''||document.getElementById("namRemarks").value.trim()==null || document.getElementById("namRemarks").value.trim()=='')
{ if(lField=='') lField="namRemarks"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}
return errMsg;
}
function validateNonTechCheckLst(){

var errMsg='';lField='';
if(document.getElementById("claimFcxPharmBill").value==null || document.getElementById("claimFcxPharmBill").value=='')
{
if(lField=='') lField="claimFcxPharmBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Pharmacy Bill Amount <br>";
}
if(document.getElementById("claimFcxConsulBill").value==null || document.getElementById("claimFcxConsulBill").value=='')
{
if(lField=='') lField="claimFcxConsulBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Consultation Bill Amount <br>";
}
if(document.getElementById("claimFcxInvestBill").value==null || document.getElementById("claimFcxInvestBill").value=='')
{
if(lField=='') lField="claimFcxInvestBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Bill Amount <br>";
}
if(document.getElementById("claimFcxAmt").value==null || document.getElementById("claimFcxAmt").value=='' || document.getElementById("claimFcxAmt").value=='0')
{
if(lField=='') lField="claimFcxAmt";
if(errMsg=='') errMsg=errMsg+"Please Enter Total Bill Amount <br>";
}
	else 
		{
		var totBillAmt=document.getElementById("claimFcxAmt").value;
		if(totBillAmt*1<"2")
			{
			if(lField=='') lField="claimFcxAmt";
			if(errMsg=='') errMsg=errMsg+"Bill Amount cannot be less than Rs.2<br>";
			}
		}
	
if (!document.forms[0].exeDisphotoChklst[0].checked && !document.forms[0].exeDisphotoChklst[1].checked)
{if(lField=='') lField="exeDisphotoChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Photo verification First checklist <br>";} 
if (!document.forms[0].exePatphotoChklst[0].checked && !document.forms[0].exePatphotoChklst[1].checked)
{if(lField=='') lField="exePatphotoChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Photo verification Second checklist <br>";}
if (!document.forms[0].exeAcqvrifyChklst[0].checked && !document.forms[0].exeAcqvrifyChklst[1].checked)
{if(lField=='') lField="exeAcqvrifyChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document Verification First checklist <br>";}
if (!document.forms[0].exeMedverifyChklst[0].checked && !document.forms[0].exeMedverifyChklst[1].checked)
{if(lField=='') lField="exeMedverifyChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document Verification Second checklist <br>";}
if (!document.forms[0].exeQuantverifyChklst[0].checked && !document.forms[0].exeQuantverifyChklst[1].checked)
{if(lField=='') lField="exeQuantverifyChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document Verification Third checklist <br>";}
if (!document.forms[0].exereprtcheckChklst[0].checked && !document.forms[0].exereprtcheckChklst[1].checked)
{if(lField=='') lField="exereprtcheckChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Investigation Reports Verification checklist <br>";}
if(document.getElementById("exeDisphotoremark").value==null || document.getElementById("exeDisphotoremark").value==''||document.getElementById("exeDisphotoremark").value.trim()==null || document.getElementById("exeDisphotoremark").value.trim()=='')
{ if(lField=='') lField="exeDisphotoremark"; if(errMsg=='') errMsg=errMsg+"Please Enter Photo Verification First checklist Remarks <br>"; }
if(document.getElementById("exePatphotoRemark").value==null || document.getElementById("exePatphotoRemark").value==''||document.getElementById("exePatphotoRemark").value.trim()==null || document.getElementById("exePatphotoRemark").value.trim()=='')
{ if(lField=='') lField="exePatphotoRemark";if(errMsg=='') errMsg=errMsg+"Please Enter Photo Verification Second checklist Remarks <br>";}
if(document.getElementById("exeAcqverifyRemark").value==null || document.getElementById("exeAcqverifyRemark").value==''||document.getElementById("exeAcqverifyRemark").value.trim()==null || document.getElementById("exeAcqverifyRemark").value.trim()=='')
{ if(lField=='') lField="exeAcqverifyRemark";if(errMsg=='') errMsg=errMsg+"Please Enter Document verification First checklist Remarks <br>";}
if(document.getElementById("exeMedVerifyRemark").value==null || document.getElementById("exeMedVerifyRemark").value==''||document.getElementById("exeMedVerifyRemark").value.trim()==null || document.getElementById("exeMedVerifyRemark").value.trim()=='')
{ if(lField=='') lField="exeMedVerifyRemark";if(errMsg=='') errMsg=errMsg+"Please Enter Document verification Second checklist Remarks <br>";}
if(document.getElementById("exeQuantverifyRemark").value==null || document.getElementById("exeQuantverifyRemark").value==''||document.getElementById("exeQuantverifyRemark").value.trim()==null || document.getElementById("exeQuantverifyRemark").value.trim()=='')
{ if(lField=='') lField="exeQuantverifyRemark";if(errMsg=='') errMsg=errMsg+"Please EnterDocument verification Third checklist Remarks <br>";}
if(document.getElementById("exeReprtcheckRemark").value==null || document.getElementById("exeReprtcheckRemark").value==''||document.getElementById("exeReprtcheckRemark").value.trim()==null || document.getElementById("exeReprtcheckRemark").value.trim()=='')
{ if(lField=='') lField="exeReprtcheckRemark";if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Reports Verification Remarks <br>";}

//if(document.getElementById("claimFcxAmt").value==null || document.getElementById("claimFcxAmt").value=='')
//{ if(lField=='') lField="claimFcxAmt"; errMsg=errMsg+"Please Enter Final Approval Amount <br>";}
if(document.getElementById("fcxRemark").value==null || document.getElementById("fcxRemark").value==''||document.getElementById("fcxRemark").value.trim()==null || document.getElementById("fcxRemark").value.trim()=='')
{ if(lField=='') lField="fcxRemark"; if(errMsg=='') errMsg=errMsg+"Please EnterRemarks <br>";}

if(parseInt(document.getElementById("claimFcxAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
{ if(lField=='') lField="claimFcxAmt"; errMsg=errMsg+"Final Approve Amount should be Less than or equal to Claim Amount Approved by Medco. <br>";}

return errMsg;
}
function validateTrustDocCheckLst(buttonId){
var errMsg='';lField='';
var buttonVal=buttonId;

if(buttonVal!=null && buttonVal!='' && buttonVal!='CD31' && buttonVal!='CD30')
{
if(document.getElementById("claimFTDPharmBill").value==null || document.getElementById("claimFTDPharmBill").value=='')
{
if(lField=='') lField="claimFTDPharmBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Pharmacy Bill Amount <br>";
}
if(document.getElementById("claimFTDConsulBill").value==null || document.getElementById("claimFTDConsulBill").value=='')
{
if(lField=='') lField="claimFTDConsulBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Consultation Bill Amount <br>";
}
if(document.getElementById("claimFTDInvestBill").value==null || document.getElementById("claimFTDInvestBill").value=='')
{
if(lField=='') lField="claimFTDInvestBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Bill Amount <br>";
}
if(document.getElementById("claimFtdAmt").value==null || document.getElementById("claimFtdAmt").value=='' || document.getElementById("claimFtdAmt").value=='0')
{
if(lField=='') lField="claimFtdAmt";if(errMsg=='') errMsg=errMsg+"Please Enter Final Approval Amount <br>"; 
}
		else 
		{
		var totBillAmt=document.getElementById("claimFtdAmt").value;
		if(totBillAmt*1<"2")
			{
			if(lField=='') lField="claimFtdAmt";
			if(errMsg=='') errMsg=errMsg+"Bill Amount cannot be less than Rs.2<br>";
			}
		}
}

if (!document.forms[0].ftdRemarkvrifedChklst[0].checked && !document.forms[0].ftdRemarkvrifedChklst[1].checked)
{if(lField=='') lField="ftdRemarkvrifedChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for FCX Remarks verified <br>";}
if (!document.forms[0].ftdBeneficiryChklst[0].checked && !document.forms[0].ftdBeneficiryChklst[1].checked)
{if(lField=='') lField="ftdRemarkvrifedChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Beneficiary – Periodic Telephonic Enquiry <br>";}
if(document.getElementById("ftdRemarkvrifedRemark").value==null || document.getElementById("ftdRemarkvrifedRemark").value==''||document.getElementById("ftdRemarkvrifedRemark").value.trim()==null || document.getElementById("ftdRemarkvrifedRemark").value.trim()=='')
{ if(lField=='') lField="ftdRemarkvrifedRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Fcx Remarks Verified Remarks <br>"; }
if(document.getElementById("ftdBeneficiryRemark").value==null || document.getElementById("ftdBeneficiryRemark").value==''||document.getElementById("ftdBeneficiryRemark").value.trim()==null || document.getElementById("ftdBeneficiryRemark").value.trim()=='')
{ if(lField=='') lField="ftdBeneficiryRemark";if(errMsg=='') errMsg=errMsg+"Please Enter Beneficiary Remarks <br>"; }

if(document.getElementById("ftdRmks").value==null || document.getElementById("ftdRmks").value==''||document.getElementById("ftdRmks").value.trim()==null || document.getElementById("ftdRmks").value.trim()=='')
{ if(lField=='') lField="ftdRmks"; if(errMsg=='') errMsg=errMsg+"Please EnterRemarks <br>";}

 if(document.getElementById("claimFtdAmt").value!=null || document.getElementById("claimFtdAmt").value!='')
{
if(parseInt(document.getElementById("claimFtdAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
{ 
if(lField=='') lField="claimFtdAmt"; if(errMsg=='') errMsg=errMsg+"Final Approve Amount should be Less than or equal to Claim Amount Approved by Medco. <br>";
}
}
else
document.getElementById("claimFtdAmt").value=0; 

return errMsg;
}
function validateClaimHead(buttonId){
var errMsg='';lField='';
var buttonVal=buttonId;
if(buttonVal!=null && buttonVal!='' && buttonVal!='CD31' && buttonVal!='CD30')
{
if(document.getElementById("claimCHPharmBill").value==null || document.getElementById("claimCHPharmBill").value=='')
{
if(lField=='') lField="claimCHPharmBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Pharmacy Bill Amount <br>";
}
if(document.getElementById("claimCHConsulBill").value==null || document.getElementById("claimCHConsulBill").value=='')
{
if(lField=='') lField="claimCHConsulBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Consultation Bill Amount <br>";
}
if(document.getElementById("claimCHInvestBill").value==null || document.getElementById("claimCHInvestBill").value=='')
{
if(lField=='') lField="claimCHInvestBill";
if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Bill Amount <br>";
}
if(document.getElementById("claimChAmt").value==null || document.getElementById("claimChAmt").value=='' || document.getElementById("claimChAmt").value=='0')
{ 
if(lField=='') lField="claimChAmt"; if(errMsg=='') errMsg=errMsg+"Please Enter Final Approval Amount <br>";
}
			else 
			{
			var totBillAmt=document.getElementById("claimChAmt").value;
			if(totBillAmt*1<"2")
				{
				if(lField=='') lField="claimChAmt";
				if(errMsg=='') errMsg=errMsg+"Bill Amount cannot be less than Rs.2<br>";
				}
			}
}
if(document.getElementById("chRemark").value==null || document.getElementById("chRemark").value==''||document.getElementById("chRemark").value.trim()==null || document.getElementById("chRemark").value.trim()=='')
{ if(lField=='') lField="chRemark"; if(errMsg=='') errMsg=errMsg+"Please EnterRemarks <br>";}
if(document.getElementById("claimChAmt").value!=null || document.getElementById("claimChAmt").value!='')
{ 
if(parseInt(document.getElementById("claimChAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
{ 
if(lField=='') lField="claimChAmt";if(errMsg=='') errMsg=errMsg+"Final Approve Amount should be Less than or equal to Claim Amount Approved by Medco. <br>";
}
}
else
document.getElementById("claimChAmt").value=0; 

return errMsg;
}
function validateACOList(buttonId){
var errMsg='';lField='';
var buttonVal=buttonId;
//if(document.getElementById("acoAprAmt").value==null || document.getElementById("acoAprAmt").value=='')
//{if(lField=='') lField="acoAprAmt"; errMsg=errMsg+"Please Enter Final Approve Amount <br>"; }
if(document.getElementById("acoRemark").value==null || document.getElementById("acoRemark").value==''||document.getElementById("acoRemark").value.trim()==null || document.getElementById("acoRemark").value.trim()=='')
{if(lField=='') lField="acoRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}


//if(parseInt(document.getElementById("acoAprAmt").value*1)>parseInt(document.getElementById("claimChAmt").value*1))
//{if(lField=='') lField="acoAprAmt"; errMsg=errMsg+"Final Approve Amount cannot be greater than CH Approve Amount <br>"; }

return errMsg;
}
function pasteIntercept(evt){var input=document.getElementById('medcoRemarks');maxLengthPaste(input,3000);}
function pasteInterceptCex(evt){var input=document.getElementById('namRemarks');maxLengthPaste(input,3000);}
function pasteStayRemark(evt){var input=document.getElementById('exeDisphotoremark');maxLengthPaste(input,3000);}
function pasteInputsRmrk(evt){var input=document.getElementById('exePatphotoRemark');maxLengthPaste(input,3000);}
function pasteProfFeeRmrk(evt){var input=document.getElementById('exeAcqverifyRemark');maxLengthPaste(input,3000);}
function pasteInvestBillRmrk(evt){var input=document.getElementById('exeMedVerifyRemark');maxLengthPaste(input,3000);}
function pasteClaimPanelRemark(evt){var input=document.getElementById('exeQuantverifyRemark');maxLengthPaste(input,3000);}
function pasteErrCtdRemark(evt){var input=document.getElementById('exeReprtcheckRemark');maxLengthPaste(input,3000);}
function pasteCtdRemark(evt){var input=document.getElementById('fcxRemark');maxLengthPaste(input,3000);}
function pasteErrChRemark(evt){var input=document.getElementById('ftdRemarkvrifedRemark');maxLengthPaste(input,3000);}
function pasteChRemark(evt){var input=document.getElementById('ftdBeneficiryRemark');maxLengthPaste(input,3000);}
function pasteEoRemark(evt){var input=document.getElementById('ftdRmks');maxLengthPaste(input,3000);}
function pasteEoComRemark(evt){var input=document.getElementById('chRemark');maxLengthPaste(input,3000);}
function pasteErrAcoRemark(evt){var input=document.getElementById('acoRemark');maxLengthPaste(input,3000);}

var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
{
//For validating maxlength onpaste event--For textarea fields
if(document.getElementById("medcoRemarks")!=null) {document.getElementById("medcoRemarks").attachEvent("onpaste",pasteIntercept);}
if(document.getElementById("namRemarks")!=null) {document.getElementById("namRemarks").attachEvent("onpaste",pasteInterceptCex);}
if(document.getElementById("exeDisphotoremark")!=null) {document.getElementById("exeDisphotoremark").attachEvent("onpaste",pasteStayRemark);}
if(document.getElementById("exePatphotoRemark")!=null) {document.getElementById("exePatphotoRemark").attachEvent("onpaste",pasteInputsRmrk);}
if(document.getElementById("exeAcqverifyRemark")!=null) {document.getElementById("exeAcqverifyRemark").attachEvent("onpaste",pasteProfFeeRmrk);}
if(document.getElementById("exeMedVerifyRemark")!=null) {document.getElementById("exeMedVerifyRemark").attachEvent("onpaste",pasteInvestBillRmrk);}
if(document.getElementById("exeQuantverifyRemark")!=null) {document.getElementById("exeQuantverifyRemark").attachEvent("onpaste",pasteClaimPanelRemark);}
if(document.getElementById("exeReprtcheckRemark")!=null) {document.getElementById("exeReprtcheckRemark").attachEvent("onpaste",pasteErrCtdRemark);}
if(document.getElementById("fcxRemark")!=null) {document.getElementById("fcxRemark").attachEvent("onpaste",pasteCtdRemark);}
if(document.getElementById("ftdRemarkvrifedRemark")!=null) {document.getElementById("ftdRemarkvrifedRemark").attachEvent("onpaste",pasteErrChRemark);}
if(document.getElementById("ftdBeneficiryRemark")!=null) {document.getElementById("ftdBeneficiryRemark").attachEvent("onpaste",pasteChRemark);}
if(document.getElementById("ftdRmks")!=null) {document.getElementById("ftdRmks").attachEvent("onpaste",pasteEoRemark);}
if(document.getElementById("chRemark")!=null) {document.getElementById("chRemark").attachEvent("onpaste",pasteEoComRemark);}
if(document.getElementById("acoRemark")!=null) {document.getElementById("acoRemark").attachEvent("onpaste",pasteErrAcoRemark);}
}
else if(browserName == "Netscape")
{
if(document.getElementById("medcoRemarks")!=null) {document.getElementById("medcoRemarks").addEventListener("paste", pasteIntercept, false);}
if(document.getElementById("namRemarks")!=null) {document.getElementById("namRemarks").addEventListener("paste", pasteInterceptCex, false);}
if(document.getElementById("exeDisphotoremark")!=null) {document.getElementById("exeDisphotoremark").addEventListener("onpaste",pasteStayRemark);}
if(document.getElementById("exePatphotoRemark")!=null) {document.getElementById("exePatphotoRemark").addEventListener("onpaste",pasteInputsRmrk);}
if(document.getElementById("exeAcqverifyRemark")!=null) {document.getElementById("exeAcqverifyRemark").addEventListener("onpaste",pasteProfFeeRmrk);}
if(document.getElementById("exeMedVerifyRemark")!=null) {document.getElementById("exeMedVerifyRemark").addEventListener("onpaste",pasteInvestBillRmrk);}
if(document.getElementById("exeQuantverifyRemark")!=null) {document.getElementById("exeQuantverifyRemark").addEventListener("onpaste",pasteClaimPanelRemark);}
if(document.getElementById("exeReprtcheckRemark")!=null) {document.getElementById("exeReprtcheckRemark").addEventListener("onpaste",pasteErrCtdRemark);}
if(document.getElementById("fcxRemark")!=null) {document.getElementById("fcxRemark").addEventListener("onpaste",pasteCtdRemark);}
if(document.getElementById("ftdRemarkvrifedRemark")!=null) {document.getElementById("ftdRemarkvrifedRemark").addEventListener("onpaste",pasteErrChRemark);}
if(document.getElementById("ftdBeneficiryRemark")!=null) {document.getElementById("ftdBeneficiryRemark").addEventListener("onpaste",pasteChRemark);}
if(document.getElementById("ftdRmks")!=null) {document.getElementById("ftdRmks").addEventListener("onpaste",pasteEoRemark);}
if(document.getElementById("chRemark")!=null) {document.getElementById("chRemark").addEventListener("onpaste",pasteEoComRemark);}
if(document.getElementById("acoRemark")!=null) {document.getElementById("acoRemark").addEventListener("onpaste",pasteErrAcoRemark);}
}
function focusBox(arg)
{
aField = arg;
setTimeout("aField.focus()", 0);
}
function focusFieldView(el)
{
var x=getOffset( el ).top;
focusBox(el);
}
</script>

</form>
</body>
</fmt:bundle>
</html>