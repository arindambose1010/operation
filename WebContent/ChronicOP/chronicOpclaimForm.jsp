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
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style>
.btn
{
border-radius:20px;
}
#imageID {
    position: fixed;
    top: 35px;
    right: 6px;
    z-index: 99999;
    cursor: pointer;
    padding: 5px;
    background: #fff;
    text-align: center;
    border: 2px solid #E2E2E2;
}

</style>
<script type="text/javascript">
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

function focusBox(arg)
{	
  aField = arg; 
  setTimeout("aField.focus()", 0);  
}
function validateAlphaNumSpace(arg1,input)
{
	    var fr = partial(focusBox,input);
	    var textbox1 =  input;    
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
      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
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

function fn_getPatDetails(){

	var chronicId='${chronicId}';
	
	var url="/<%=context%>/chronicAction.do?actionFlag=casePrintForm&chronicId="+chronicId+"&type=patView";
	document.getElementById("complaintFrame").src=url;
}
function refreshParent() 
{
	parent.fn_maxmizeTop();
    //window.parent.parent.location.reload(true);
//window.parent.back();
    //window.parent.parent.parent.location.href=window.parent.parent.parent.location.href;
	
}
</script>
</head>
<bean:define id="buttonDtls" name="chronicOpForm" property="buttonList" />
<c:set var="buttons" value="${buttonDtls}"/>
<body onload="fn_onloadDisabled();">
<form action="/chronicAction.do" method="post"  enctype="multipart/form-data">
<html:hidden property="claimAmt" name="chronicOpForm"/>
<html:hidden property="packageAmt" name="chronicOpForm"/>
<html:hidden property="roleId" name="chronicOpForm"/>
<html:hidden property="acoAprAmt" styleId="acoAprAmt" name="chronicOpForm"/>


<c:if test="${saveMsg ne null}">
<script>
jqueryInfoMsg('FollowUp Information','${saveMsg}');
</script>
</c:if>

<c:if test="${claimMsg eq 'N' }">
<div style=padding-top:"50%">



<table width="100%" border="0" align="center">
<tbody>
<tr>
<td class="labelheading1 tbcellCss" style="height:480px;text-align:center;vertical-align:center;" colspan="6">
<b><fmt:message key="EHF.chronic.claimMsg"/></b>
</td>

</tr>
</tbody>
</table>




</div>
</c:if>


<c:if test="${claimMsg ne 'N' }">

<div class="modal fade" id="viewDtlsID"> 
 <div class="modal-dialog" style="width:90%;margin-right:5%;height:400px" id="modal-lgx">
   <div class="modal-content" style="height:350px;">
      <div class="modal-body" >
	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  <iframe  id="complaintFrame" width="100%" height="250px" frameborder="no" scrolling="yes" > </iframe>
	  </div>
	  <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	  </div><!-- /.modal-content --> 
	</div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 

<div id="imageID" style="margin-left:85%;"> 
 <a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
 <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
 <br>Patient Details
 </a>
</div>

<table class="tbheader">

<tr>
<td colspan="5" style="text-align:center;" class="tbheader print_heading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><fmt:message key="EHF.chronicClaim"/></strong></td>
		
		<td id="menuSlide" style="text-align:center;" class="tbheader print_heading" width="5%">  
<img id="topImage" src="images/back.jpg" width="30" height="20" style="cursor:hand;" title="Back" alt="Back" align="top" onclick="javascript:refreshParent()">
</td>
</tr>


</table>


<logic:equal value="Yes" name="chronicOpForm" property="showMedco">
<table width="100%">
<tr>
<td width="30%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.chronicID"/></b>&nbsp;&nbsp;<bean:write name="chronicOpForm" property="chronicID"/></td>
<td width="30%" class="labelheading1 tbcellCss"><b>Bill Date</b>&nbsp;&nbsp;<bean:write name="chronicOpForm" property="followUpDt"/></td>
<td width="30%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.PackAmt"/></b>&nbsp;&nbsp;<bean:write name="chronicOpForm" property="packageAmt"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ClaimAmt"/><font color="red">*</font></b>&nbsp;&nbsp;<html:text name="chronicOpForm"  property="claimNwhAmt" styleId="claimNwhAmt" style="WIDTH:12em;" title="Claim Amount" maxlength="6" onchange="validateNumber('Claim Amount',this)"/></td>
<td colspan="2" class="labelheading1 tbcellCss">
<table>
<tr><td ><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td>
<td><html:textarea name="chronicOpForm" property="medcoRemarks" styleId="medcoRemarks"  style="overflow-y:auto;WIDTH:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr> 
</table>
</td>
</tr>
</table>
<table width="100%">
<tr><td><br></td></tr>
<tr>
<td align="center">
<div id="AttachBut" style="display:none">
<button class="btn btn-success" type="button" onclick="javascript:validateclaimInt();">Initiate&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
<button class="btn btn-primary" type="button" onclick="javascript:addAttachments();">Add Attachments&nbsp;<span class="glyphicon glyphicon-file"></span></button>
</div>
</td>

<c:if test="${updateFlag == 'Y'}">
	<td  align="center" >
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<c:if test="${EHFbutton.ID eq 'CD130'}">
	<button class="btn btn-primary"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	<button id="AttachBut2" class="btn btn-warning" type="button" onclick="javascript:addAttachments();">View Attachments&nbsp;<span class="glyphicon glyphicon-file"></span></button>
	</c:if>
	</c:forEach>
	
	</td>
</c:if>

</tr>
<tr><td><br></td></tr>
</table>
</logic:equal>
<logic:equal value="Yes" name="chronicOpForm" property="showNam">
<table class="tbheader">
<tr><td><b>
			<c:if test="${chronicCaseScheme ne 'CD201' }"><fmt:message key="EHF.Title.NAM"/></c:if>
			<c:if test="${chronicCaseScheme eq 'CD201' }"><fmt:message key="EHF.Title.APNAM"/></c:if>
		</b></td></tr>
</table>
<table width="100%">
<!-- <tr>
<td colspan="2" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/></b>&nbsp;&nbsp;<html:text name="chronicOpForm"  property="claimNamAmt" styleId="claimNamAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td>
</tr> -->
<tr>
<td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td><td width="80%" class="tbcellBorder"><html:textarea name="chronicOpForm" property="namRemarks" styleId="namRemarks"  style="overflow-y:auto;WIDTH:50em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr></table></logic:equal>
<logic:equal value="Yes" name="chronicOpForm" property="showFcx">
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.NonTech"/></b></td></tr>
</table>
<table width="100%" border="0" height="150px"> 
<tr><th width="40%" class="labelheading1 tbcellCss">CHRONIC OP Claim CheckList</th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Matching"/></th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Remarks"/></th></tr>
<tr>
<td width="40%" class="labelheading1 tbcellCss"><b>Photo verification</b><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListPhotoChronic"/><font color="red">*</font></td>
<td width="30%" align="center" class="tbcellBorder">
<html:radio name="chronicOpForm" property="exePatphotoChklst" value="Y" title="Yes" styleId="exePatphotoChklst"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="chronicOpForm" property="exePatphotoChklst" value="N" title="No" styleId="exePatphotoChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td width="30%" class="tbcellBorder">

<html:textarea name="chronicOpForm" property="exePatphotoRemark" styleId="exePatphotoRemark"  style="overflow-y:auto;WIDTH:25em;height:3em"  onkeydown="return  maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Photo Verification Remarks"/>
</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Document Verification</b><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListDoc1"/><font color="red">*</font><br>&nbsp;<br>

<td align="center" class="tbcellBorder">
<html:radio name="chronicOpForm" property="exeAcqvrifyChklst" value="Y" title="Yes" styleId="exeAcqvrifyChklst"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="chronicOpForm" property="exeAcqvrifyChklst" value="N" title="No" styleId="exeAcqvrifyChklst" /><fmt:message key="EHF.Claims.No"/>
<br>&nbsp;<br>

</td>
<td class="tbcellBorder">
<html:textarea name="chronicOpForm" property="exeAcqverifyRemark" styleId="exeAcqverifyRemark"  style="overflow-y:auto;WIDTH:25em;height:3em" onkeydown="return  maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Document Verification Remarks"/>

</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Investigation Reports Verification</b><br>&nbsp;<br>
<fmt:message key="EHF.NonTechChkListInvest1"/><font color="red">*</font></td>
<td align="center" class="tbcellBorder">
<html:radio name="chronicOpForm" property="exereprtcheckChklst" value="Y" title="Yes" styleId="exereprtcheckChklst"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="chronicOpForm" property="exereprtcheckChklst" value="N" title="No" styleId="exereprtcheckChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td class="tbcellBorder"><html:textarea name="chronicOpForm" property="exeReprtcheckRemark" styleId="exeReprtcheckRemark"  style="overflow-y:auto;WIDTH:25em;height:3em" onkeydown="return  maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Investigation Reports Verification Remarks"/></td>
</tr>
<!-- <tr>
<td colspan="5" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/></b>&nbsp;&nbsp;<html:text name="chronicOpForm"  property="claimFcxAmt" styleId="claimFcxAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td>
</tr> -->
<tr>
<td colspan="5" class="labelheading1 tbcellCss">
<table>
<tr><td><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b>&nbsp;&nbsp;</td>
<td><html:textarea name="chronicOpForm" property="fcxRemark" styleId="fcxRemark"  style="overflow-y:auto;WIDTH:40em;height:3em" onkeydown="return  maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr> 
</table>
</td>
</tr>
</table>
</logic:equal>
<logic:equal value="Yes" name="chronicOpForm" property="showFtd">
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.TrustDoc"/></b></td></tr>
</table>
<table width="100%" border="0" height="150px"> 
<tr><th width="40%" class="labelheading1 tbcellCss">CHRONIC OP Claim CheckList</th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Matching"/></th>
<th width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Remarks"/></th></tr>
<tr>
<td width="40%" class="labelheading1 tbcellCss"><fmt:message key="EHF.TrustDoctChkList1"/><font color="red">*</font></td>
<td width="30%" align="center"  class="tbcellBorder">
<html:radio name="chronicOpForm" property="ftdRemarkvrifedChklst" value="Y" title="Yes" styleId="ftdRemarkvrifedChklst"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="chronicOpForm" property="ftdRemarkvrifedChklst" value="N" title="No" styleId="ftdRemarkvrifedChklst" /><fmt:message key="EHF.Claims.No"/>
</td>
<td width="30%" class="tbcellBorder"><html:textarea name="chronicOpForm" property="ftdRemarkvrifedRemark" styleId="ftdRemarkvrifedRemark"  style="overflow-y:auto;overflow-y:auto;WIDTH:25em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="FCX Remarks Verified"/></td>
</tr>


<tr>
<td  class="labelheading1 tbcellCss"><b>Claim Amount</b></td>
<td align="left" class="tbcellBorder" colspan="2"><html:text name="chronicOpForm"  property="claimNwhAmt"  styleId="claimNwhAmt2" style="WIDTH:12em;" title="claim Amount" readonly="true" /></td>
</tr>

<tr>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/><font color="red">*</font></b></td>
<td align="left" class="tbcellBorder" colspan="2"><html:text name="chronicOpForm"  property="claimFtdAmt" styleId="claimFtdAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td>
<td colspan="5" class="tbcellBorder"><html:textarea name="chronicOpForm" property="ftdRmks" styleId="ftdRmks"  style="overflow-y:auto;WIDTH:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr>
</table>
</logic:equal>
<logic:equal value="Yes" name="chronicOpForm" property="showCh">
<table class="tbheader">
<tr><td colspan="2"><b><fmt:message key="EHF.Title.CH"/></b></td></tr>
</table>
<table width="100%" border="0" >
<tr>
<td width="40%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claims.FAprAmt"/><font color="red">*</font></b></td>
<td width="60%" align="left" class="tbcellBorder" ><html:text name="chronicOpForm"  property="claimChAmt" styleId="claimChAmt" style="WIDTH:12em;" title="Final Approve Amount" maxlength="6" onchange="validateNumber('Final Approve Amount',this)"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/><font color="red">*</font></b></td>
<td><html:textarea name="chronicOpForm" property="chRemark" styleId="chRemark"  style="overflow-y:auto;WIDTH:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Remarks"/></td>
</tr>

<c:if test="${updateFlag == 'Y'}">
<tr>
	<td  colspan="2" align="center" >
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<c:if test="${EHFbutton.ID eq 'CD73'}">
	<button class="btn btn-primary"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	<button id="AttachBut2" class="btn btn-warning" type="button" onclick="javascript:addAttachments();">View Attachments&nbsp;<span class="glyphicon glyphicon-file"></span></button>
	</c:if>
	</c:forEach>
	
	</td>
	</tr>
</c:if>


</table></logic:equal>

<logic:equal value="Yes" name="chronicOpForm" property="showAco">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.Account"/></b></td></tr>
</table></td></tr>
<!--<tr><td>
<table width="100%">
 <tr>
<td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="chronicOpForm" property="acoAprAmt" styleId="acoAprAmt" maxlength="6" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
</tr>
</table>
</td></tr>-->
<tr><td class="labelheading1 tbcellCss">
<table width="100%">
<tr ><td class="labelheading1 tbcellCss" width="15%" ><b><fmt:message key="EHF.Remarks"/></b>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td ><html:textarea name="chronicOpForm" property="acoRemark" styleId="acoRemark" style="overflow-y:auto;WIDTH:80%;height:3em;resize: none" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr>
</table></td></tr>










</logic:equal>
<c:if test="${pendingFlag ne 'Y' && caseApprovalFlag != 'N'}">
<table width="100%" align="center" style="padding-left:20%">
<c:if test="${fn:length(buttons) > 0}">
<c:if test="${attachMsg eq 'yes' }">
	<tr>
<td><br><br></td>
	
	<c:if test="${updateFlag != 'Y'}">
	<td  colspan="3" style="textalign:center" >
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >

	<button class="btn btn-primary"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	
	</c:forEach>
    <button id="AttachBut2" class="btn btn-warning" type="button" onclick="javascript:addAttachments();">View Attachments&nbsp;<span class="glyphicon glyphicon-file"></span></button>


	</td>
	</c:if>




	<td><br><br></td>
	</tr>
	</c:if>
	</c:if>	
</table>

</c:if>







<c:if test="${pendingFlag eq 'Y' && caseApprovalFlag != 'N' && UserRole!='GP9'}">
	<tr><td><br><br></td></tr>
<tr>
<td>



<table class="tbheader" width="100%">
<tr ><td width="100%" align="center" ><b>CEO ChronicOP Claim Pending Remarks&nbsp;&nbsp;&nbsp;</b></td></tr>
</table>
<table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="15%" ><b><fmt:message key="EHF.Remarks"/></b>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
<html:textarea   name="chronicOpForm" property="ceoRemark"  styleId="ceoRemarks" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="check_maxLength('ceoRemarks',3000)" onchange="checkRemarks(this.value,3000,'ceoRemarks')" title="Enter Remarks"/>
</td></tr>



			
	
	
	<tr><td colspan="2"  >
							<button class="btn btn-primary"   style="margin-left:50%" type="button"  id="approveBtn" onclick="javascript:fn_submitSentBackNxtLvl();" >Submit</button>
							
							</td>
						</tr>	
						
						</table>		
				
	</c:if>



<logic:present name="chronicOpForm" property="lstworkFlow">
<bean:size id="wrkList" name="chronicOpForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="1" width="100%" style="table-layout: fixed;" cellpadding="1" cellspacing="1" align="center">
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b>Work Flow</b></td></tr></table></td></tr>
<tr><td colspan="2">
<table border="1" width="100%" style="table-layout: fixed;" cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr  class="tbheader" ><td width="3%" class="labelheading1 tbcellCss"><b>SNo</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Date & Time</b></td>
<c:if test="${showUserNames eq 'Y' }">
<td width="20%" class="labelheading1 tbcellCss"><b>Name</b></td>
</c:if>			
<td width="10%" class="labelheading1 tbcellCss"><b>Role</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Remarks</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Approve Amount</b></td>
<td width="12%" class="labelheading1 tbcellCss"><b>Action</b></td>	
</tr>
<logic:iterate id="results1" name="chronicOpForm" property="lstworkFlow" indexId="index1" >
<tr>
<td style="word-wrap:break-word;"  class="tbcellBorder"><%=index1+1%></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="chronicAuditDate" /></td>
<c:if test="${showUserNames eq 'Y' }">
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditName" /></td>
</c:if>
<td style="word-wrap:break-word;" class="tbcellBorder">
			<c:choose>
				<c:when test="${chronicCaseScheme eq 'CD201' && results1.auditRole eq 'MITHRA'}" >
					VAIDYA MITHRA
				</c:when>
				<c:otherwise >
					<bean:write name="results1" property="auditRole" />
				</c:otherwise>
			</c:choose> 
</td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="cexRemark" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="COUNT" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
</tr>
</logic:iterate>
</table>
</td></tr>
</logic:greaterThan>
</logic:present>
</c:if>



<script type="text/javascript">
var roleId='${UserRole1}';

var lField = '';
var displayMsg='';


function check_maxLength(id,remarkLength)
{
	var name = document.getElementById(id).value;
	 if(name != null && name !='' && name.length >= remarkLength)
 	{
 	alert("Remarks length should not exceed " +remarkLength );
 	var fr = partial(subStringRemarks,id,remarkLength);
		}
}


function fn_attachments()
{	
	var caseApprovalFlag = '${casesForApproval}';
	var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfFollowUp&followupId="+document.forms[0].followUpId.value+"&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag=Y";
    parent.parent.parent.claimAttachWindow = window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
}


function fn_submitSentBackNxtLvl()
{
	var chronicNo='${chronicNo}';
	
	var pendingFlag="${pendingFlag}";
	var remarks=document.getElementById("ceoRemarks").value;
	var chronicStatus='${chronicStatus}';


	var xmlhttp;
    var url;
    check_maxLength('ceoRemarks',4000);
    if(remarks.length>=3000)
    {
  return false;
    }
    if(remarks==null || remarks=="")
    {
alert("Please Enter Remarks");
return false;
    }
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
 url = "/Operations/chronicAction.do?actionFlag=updateSentBackCases&chronicNo="+chronicNo+"&remarks="+remarks+"&pendingFlag="+pendingFlag+"&chronicStatus="+chronicStatus;
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
 document.getElementById('approveBtn').disabled=true;
 xmlhttp.open("Post",url,true);
	xmlhttp.send(null);	
}

function fn_back()
{
	parent.parent.parent.fn_coClaimCasesPending(); 
}
function Refresh() {
    window.parent.location = window.parent.location.href;
}
function validateclaimInt()
{
	alert("Please Add the Mandatory Attachments To Initiate The Claim Process");
	return ;
}
function fn_onloadDisabled(){
	var msg="${msg}";
	var autoCases="${autoActionFlag}";
	var autoCaseId="${chronicNo}";
	if(msg.length>0)
	{
		alert(msg);
		if(autoCases=="OnloadCaseOpen"){
			parent.parent.parent.fn_chronicCasesForApprovalNew(autoCaseId);
			}
			else
			{
				parent.fn_ipRefresh();
					
			}
	
	}
	if('${attachMsg}'=="no")
	{
		

document.getElementById("AttachBut").style.display="";
disableMedco();
disableNAM();
disableFCX();	
disableFTD();
disableCH();
disableACO();

	}



	if('${disableAll}'=='Yes'){
		
		if(roleId==''){
			disableMedco();
			disableNAM();
			disableFCX();	
			disableFTD();
			disableCH();
			disableACO();			
			}
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.Medco"/>'){
	               disableMedco();
			}		
		//if(roleId=='<fmt:message key="EHF.ChronicOp.Role.NAM"/>'){
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.NAM"/>'){
			disableMedco();
			disableNAM();
			}
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COEX"/>'){
			disableMedco();
			disableNAM();
			disableFCX();	
		}
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COTD"/>'){
			
			disableMedco();
			disableNAM();
			disableFCX();
			disableFTD();	
		}
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COCH"/>'){
			disableMedco();
			disableNAM();
			disableFCX();
			disableFTD();
			disableCH();
		}
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COACO"/>'){
			disableMedco();
			disableNAM();
			disableFCX();
			disableFTD();
			disableCH();
			disableACO();
		}
		
	}
	else{

		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.Medco"/>'){
           
            disableNAM();
			disableFCX();
			disableFTD();
			disableCH();
			disableACO();
		}	
		
		if(roleId=='<fmt:message key="EHF.ChronicOp.Role.NAM"/>'){
			disableMedco();
			disableFCX();
			disableFTD();
			disableCH();
			disableACO();
			}
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COEX"/>'){
		disableMedco();
		disableNAM();
		disableFTD();
		disableCH();
		disableACO();
	}
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COTD"/>'){
		disableMedco();
		disableNAM();
		disableFCX();	
		disableCH();
		disableACO();
	}
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COCH"/>'){
		disableMedco();
		disableNAM();
		disableFCX();
		disableFTD();
		disableACO();
	}
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COACO"/>'){
		disableMedco();
		disableNAM();
		disableFCX();
		disableFTD();
		disableCH();
	}
	}
	
	}

function disableMedco(){
	if(document.getElementById("claimNwhAmt")!=null)
	document.getElementById("claimNwhAmt").disabled=true;
	if(document.getElementById("medcoRemarks")!=null)
	{
		document.getElementById("medcoRemarks").disabled=true;
		document.getElementById("medcoRemarks").style.background='#ECE9D8';
		document.getElementById('medcoRemarks').style.color='#ACA899';
	}
}
function disableNAM(){
	//if(document.getElementById("claimNamAmt")!=null)
	//document.getElementById("claimNamAmt").disabled=true;
	if(document.getElementById("namRemarks")!=null)
	{
		document.getElementById("namRemarks").disabled=true;
		document.getElementById("namRemarks").style.background='#ECE9D8';
		document.getElementById('namRemarks').style.color='#ACA899';
	}
}
function disableFCX(){
	
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
	
	if(document.forms[0].exereprtcheckChklst!=null){
	document.forms[0].exereprtcheckChklst[0].disabled=true;
	document.forms[0].exereprtcheckChklst[1].disabled=true;
	}
	
	if(document.getElementById("exePatphotoRemark")!=null)
	{
		document.getElementById("exePatphotoRemark").disabled=true;
		document.getElementById("exePatphotoRemark").style.background='#ECE9D8';
		document.getElementById('exePatphotoRemark').style.color='#ACA899';
	}
	if(document.getElementById("exeAcqverifyRemark")!=null)
		{
		document.getElementById("exeAcqverifyRemark").disabled=true;
		document.getElementById("exeAcqverifyRemark").style.background='#ECE9D8';
		document.getElementById('exeAcqverifyRemark').style.color='#ACA899';
		}
	
	if(document.getElementById("exeReprtcheckRemark")!=null)
		{
		document.getElementById("exeReprtcheckRemark").disabled=true;
		document.getElementById("exeReprtcheckRemark").style.background='#ECE9D8';
		document.getElementById('exeReprtcheckRemark').style.color='#ACA899';
		}
	//if(document.getElementById("claimFcxAmt")!=null)
	 //   document.getElementById('claimFcxAmt').disabled=true;
	if(document.getElementById("fcxRemark")!=null)
	{
		document.getElementById("fcxRemark").disabled=true;
		document.getElementById("fcxRemark").style.background='#ECE9D8';
		document.getElementById('fcxRemark').style.color='#ACA899';
	}
}
function disableFTD(){
	if(document.forms[0].ftdRemarkvrifedChklst!=null){
		document.forms[0].ftdRemarkvrifedChklst[0].disabled=true;
		document.forms[0].ftdRemarkvrifedChklst[1].disabled=true;
}

	if(document.getElementById("ftdRemarkvrifedRemark")!=null)
		{
		document.getElementById("ftdRemarkvrifedRemark").disabled=true;
		document.getElementById("ftdRemarkvrifedRemark").style.background='#ECE9D8';
		document.getElementById('ftdRemarkvrifedRemark').style.color='#ACA899';
		}
	
	if(document.getElementById("claimFtdAmt")!=null)
		document.getElementById("claimFtdAmt").disabled=true;
	if(document.getElementById("ftdRmks")!=null)
	{
		document.getElementById("ftdRmks").disabled=true;
		document.getElementById("ftdRmks").style.background='#ECE9D8';
		document.getElementById('ftdRmks').style.color='#ACA899';
	}
}
function disableCH(){
	if(document.getElementById("claimChAmt")!=null)
	document.getElementById("claimChAmt").disabled=true;
	if(document.getElementById("chRemark")!=null)
	{
		document.getElementById("chRemark").disabled=true;
		document.getElementById("chRemark").style.background='#ECE9D8';
		document.getElementById('chRemark').style.color='#ACA899';
	}
}
function disableACO(){
	//if(document.getElementById("acoAprAmt")!=null)
	//document.getElementById('acoAprAmt').disabled=true;
	if(document.getElementById("acoRemark")!=null)
	{
		document.getElementById("acoRemark").disabled=true;
		document.getElementById("acoRemark").style.background='#ECE9D8';
		document.getElementById('acoRemark').style.color='#ACA899';
	}
}
</script>
<script>
function fn_buttonClicked(buttonId){
	var errMsg ='';

	
if(claimInit=='Y'){
//alert("Failed to Initiate because previous Followup Payment has not been done.");
jqueryAlertMsg('FollowUp Information','Failed to Initiate because previous Followup Payment has not been done.');
return;
}
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.Medco"/>')
		errMsg = validateNWH();	
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.NAM"/>')
		errMsg = validateNam();
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COEX"/>')
		errMsg = validateNonTechCheckLst();
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COTD"/>')
		errMsg = validateTrustDocCheckLst(buttonId);
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COCH"/>')
		errMsg = validateClaimHead(buttonId);
	if(roleId=='<fmt:message key="EHF.ChronicOp.Role.COACO"/>')
		errMsg = validateACOList();

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
		if (buttonId == '<fmt:message key="EHF.Button.Paynow"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Paynoe"/>';
			
	if(!errMsg==''  )
	{
		var fr = partial(focusFieldView,document.getElementById(lField));
        jqueryAlertMsg('FollowUp mandatory fields',errMsg,fr);
		return false;
	}
	else
	{
fn_confirmMsg(buttonId);	
	}
}
function fn_confirmMsg(buttonId){
	if(confirm(displayMsg))
	{
		$('button').prop('disabled', true);
	document.forms[0].action="./chronicAction.do?actionFlag=saveChronicClaim&actionDone="+buttonId;   
	 document.forms[0].method="post";
	document.forms[0].submit(); 
	Refresh();
	}

}

function validateNWH(){
	var errMsg ='';lField='';
	var billAmt = document.getElementById("claimNwhAmt").value;
	var PackAmt ="${packageAmt}";
	var claimAmt=document.forms[0].claimAmt.value;
	
	if(document.getElementById("claimNwhAmt").value==null || document.getElementById("claimNwhAmt").value=='')
		{ if(lField=='') 
			lField="claimNwhAmt";
		if(errMsg=='') 
		 errMsg=errMsg+"Please Enter Claim Amount <br>";}


	if(parseInt(document.getElementById("claimNwhAmt").value*1)>parseInt(PackAmt*1))
	{ if(lField=='') lField="claimNwhAmt"; if(errMsg=='') errMsg=errMsg+"Claim Amount Must be Less than or Equal to Package Amount <br>";
	
	return errMsg;
	}
	

	//alert(billAmt);
if(billAmt*1=="0"){
	if(lField=='') 
		lField="claimNwhAmt"; 
	document.getElementById("claimNwhAmt").value="";
	errMsg=errMsg+"Claim Amount cannot be zero ";
}
	else
	if(document.getElementById("medcoRemarks").value==null || document.getElementById("medcoRemarks").value==''||document.getElementById("medcoRemarks").value.trim()==null || document.getElementById("medcoRemarks").value.trim()=='')
	{ if(lField=='') lField="medcoRemarks"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>"; }
	
	
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

	if (!document.forms[0].exePatphotoChklst[0].checked && !document.forms[0].exePatphotoChklst[1].checked)
		{if(lField=='') lField="exePatphotoChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Photo verification First checklist <br>";}
	if (!document.forms[0].exeAcqvrifyChklst[0].checked && !document.forms[0].exeAcqvrifyChklst[1].checked)
		{if(lField=='') lField="exeAcqvrifyChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document Verification Second checklist <br>";}
	if (!document.forms[0].exereprtcheckChklst[0].checked && !document.forms[0].exereprtcheckChklst[1].checked)
	{if(lField=='') lField="exereprtcheckChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Investigation Reports Verification Third checklist <br>";}
	

	if(document.getElementById("exePatphotoRemark").value==null || document.getElementById("exePatphotoRemark").value==''||document.getElementById("exePatphotoRemark").value.trim()==null || document.getElementById("exePatphotoRemark").value.trim()=='')
	{ if(lField=='') lField="exePatphotoRemark";	if(errMsg=='') errMsg=errMsg+"Please Enter Photo Verification First checklist Remarks <br>";}
	if(document.getElementById("exeAcqverifyRemark").value==null || document.getElementById("exeAcqverifyRemark").value==''||document.getElementById("exeAcqverifyRemark").value.trim()==null || document.getElementById("exeAcqverifyRemark").value.trim()=='')
	{ if(lField=='') lField="exeAcqverifyRemark";	if(errMsg=='') errMsg=errMsg+"Please Enter Document verification Second checklist Remarks <br>";}
	
	if(document.getElementById("exeReprtcheckRemark").value==null || document.getElementById("exeReprtcheckRemark").value==''||document.getElementById("exeReprtcheckRemark").value.trim()==null || document.getElementById("exeReprtcheckRemark").value.trim()=='')
	{ if(lField=='') lField="exeReprtcheckRemark";	if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Reports Verification Remarks <br>";}

	//if(document.getElementById("claimFcxAmt").value==null || document.getElementById("claimFcxAmt").value=='')
	//{ if(lField=='') lField="claimFcxAmt"; errMsg=errMsg+"Please Enter Final Approval Amount <br>";	}
	if(document.getElementById("fcxRemark").value==null || document.getElementById("fcxRemark").value==''||document.getElementById("fcxRemark").value.trim()==null || document.getElementById("fcxRemark").value.trim()=='')
	{ if(lField=='') lField="fcxRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter  Remarks <br>";}

	//if(parseInt(document.getElementById("claimFcxAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
	//{ if(lField=='') lField="claimFcxAmt"; errMsg=errMsg+"Final Approve Amount should be Less than or equal to Claim Amount Approved by Medco. <br>";}
	
	return errMsg;
}
function validateTrustDocCheckLst(buttonId){
	var errMsg='';lField='';
	if (!document.forms[0].ftdRemarkvrifedChklst[0].checked && !document.forms[0].ftdRemarkvrifedChklst[1].checked)
		{if(lField=='') lField="ftdRemarkvrifedChklst"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for FCX Remarks verified <br>";}
	
	if(document.getElementById("ftdRemarkvrifedRemark").value==null || document.getElementById("ftdRemarkvrifedRemark").value==''||document.getElementById("ftdRemarkvrifedRemark").value.trim()==null || document.getElementById("ftdRemarkvrifedRemark").value.trim()=='')
	{ if(lField=='') lField="ftdRemarkvrifedRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Fcx Remarks Verified Remarks <br>"; }
	if ((buttonId == '<fmt:message key="EHF.Button.RecmdApp"/>')||  (buttonId == '<fmt:message key="EHF.Button.Approve"/>'))
	{
	if(document.getElementById("claimFtdAmt").value==null || document.getElementById("claimFtdAmt").value=='')
	{ 
		if(lField=='') lField="claimFtdAmt";	if(errMsg=='') errMsg=errMsg+"Please Enter Final Approval Amount <br>"; }	

	if(parseInt(document.getElementById("claimFtdAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
	{ if(lField=='') lField="claimFtdAmt"; if(errMsg=='') errMsg=errMsg+"Final Approve Amount should be Less than or equal to Medco Bill Amount. <br>";}

	if(document.getElementById("claimFtdAmt").value=="0"){
		if(lField=='') 
			lField="claimFtdAmt"; 
		document.getElementById("claimFtdAmt").value="";
		errMsg=errMsg+"Claim Amount cannot be zero ";
	}
	
	}
	if(document.getElementById("ftdRmks").value==null || document.getElementById("ftdRmks").value==''||document.getElementById("ftdRmks").value.trim()==null || document.getElementById("ftdRmks").value.trim()=='')
	{ if(lField=='') lField="ftdRmks"; 	if(errMsg=='') errMsg=errMsg+"Please Enter  Remarks <br>";}

	
	return errMsg;
}
function validateClaimHead(buttonId){
	var errMsg='';lField='';


	if(document.getElementById("chRemark").value==null || document.getElementById("chRemark").value==''||document.getElementById("chRemark").value.trim()==null || document.getElementById("chRemark").value.trim()=='')
	{ if(lField=='') lField="chRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter  Remarks <br>";}
	
	if ((buttonId == '<fmt:message key="EHF.Button.RecmdApp"/>')||  (buttonId == '<fmt:message key="EHF.Button.Approve"/>'))
	{
	
	if(document.getElementById("claimChAmt").value==null || document.getElementById("claimChAmt").value=='')
	{ if(lField=='') lField="claimChAmt"; if(errMsg=='') errMsg=errMsg+"Please Enter Final Approval Amount <br>";	 }

    //alert(document.getElementById("claimNwhAmt").value);
	if(parseInt(document.getElementById("claimChAmt").value*1)>parseInt(document.getElementById("claimNwhAmt").value*1))
	{ if(lField=='') lField="claimChAmt";	if(errMsg=='') errMsg=errMsg+"Final Approve Amount should be Less than or equal to Medco Bill Amount. <br>";}


	if(document.getElementById("claimChAmt").value=="0"){
		if(lField=='') 
			lField="claimChAmt"; 
		document.getElementById("claimChAmt").value="";
		errMsg=errMsg+"Claim Amount cannot be zero ";
	}
	}

	return errMsg;
}
function validateACOList(){
	var errMsg='';lField='';
	document.getElementById("acoAprAmt").value=parseInt(document.getElementById("claimChAmt").value*1);
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

function pasteInputsRmrk(evt){var input=document.getElementById('exePatphotoRemark');maxLengthPaste(input,3000);}
function pasteProfFeeRmrk(evt){var input=document.getElementById('exeAcqverifyRemark');maxLengthPaste(input,3000);}

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
	
	if(document.getElementById("exePatphotoRemark")!=null) {document.getElementById("exePatphotoRemark").attachEvent("onpaste",pasteInputsRmrk);}
	if(document.getElementById("exeAcqverifyRemark")!=null) {document.getElementById("exeAcqverifyRemark").attachEvent("onpaste",pasteProfFeeRmrk);}
	
	if(document.getElementById("exeReprtcheckRemark")!=null) {document.getElementById("exeReprtcheckRemark").attachEvent("onpaste",pasteErrCtdRemark);}
	if(document.getElementById("fcxRemark")!=null) {document.getElementById("fcxRemark").attachEvent("onpaste",pasteCtdRemark);}
	if(document.getElementById("ftdRemarkvrifedRemark")!=null) {document.getElementById("ftdRemarkvrifedRemark").attachEvent("onpaste",pasteErrChRemark);}
	
	if(document.getElementById("ftdRmks")!=null) {document.getElementById("ftdRmks").attachEvent("onpaste",pasteEoRemark);}
	if(document.getElementById("chRemark")!=null) {document.getElementById("chRemark").attachEvent("onpaste",pasteEoComRemark);}
	if(document.getElementById("acoRemark")!=null) {document.getElementById("acoRemark").attachEvent("onpaste",pasteErrAcoRemark);}
	}
else if(browserName == "Netscape")
	{
	if(document.getElementById("medcoRemarks")!=null) {document.getElementById("medcoRemarks").addEventListener("paste", pasteIntercept, false);}	
	if(document.getElementById("namRemarks")!=null) {document.getElementById("namRemarks").addEventListener("paste", pasteInterceptCex, false);}	
	//if(document.getElementById("exeDisphotoremark")!=null) {document.getElementById("exeDisphotoremark").addEventListener("onpaste",pasteStayRemark);}
	if(document.getElementById("exePatphotoRemark")!=null) {document.getElementById("exePatphotoRemark").addEventListener("onpaste",pasteInputsRmrk);}
	if(document.getElementById("exeAcqverifyRemark")!=null) {document.getElementById("exeAcqverifyRemark").addEventListener("onpaste",pasteProfFeeRmrk);}
	if(document.getElementById("exeReprtcheckRemark")!=null) {document.getElementById("exeReprtcheckRemark").addEventListener("onpaste",pasteErrCtdRemark);}
	if(document.getElementById("fcxRemark")!=null) {document.getElementById("fcxRemark").addEventListener("onpaste",pasteCtdRemark);}
	if(document.getElementById("ftdRemarkvrifedRemark")!=null) {document.getElementById("ftdRemarkvrifedRemark").addEventListener("onpaste",pasteErrChRemark);}
	
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
function addAttachments()
{
	var frame=parent.parent.frames["leftFrame"];
	
	var ele1=frame.document.getElementById("attachments");
	var ele2=frame.document.getElementById("claims");
	$(ele2).removeClass("selected");	
	ele1.className ="selected";
	parent.fn_attachments();
	
}
</script>

</form>
</body>
</fmt:bundle>
</html>