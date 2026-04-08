<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/include.jsp"%>
<html:html>  


<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Claims">  
<title>Claims Flow Home</title>   
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<%@ include file="/common/includeBootstrapCalendar.jsp"%>  
<script SRC="/<%=context%>/Preauth/maximizeScreen.js"></script> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">

<style type="text/css">
#imageID {
top: 45px;
}

.tbheader{
    line-height:2 !important;

}
.modal-header{
background: #325F95 !important;}
.btn{
padding:4px 12px !important;
font-size:13px !important;
}
.form-control{font-size:1.1em !important;font-weight:normal}
body{font-size:12px !important}
</style>
<script >

function fn_viewAttachments(){
	var ahcId = '<bean:write name="ahcClaimsForm" property="ahcId" />';
	var url="/Operations/ahcClaimsAction.do?actionFlag=viewAhcAttachments&ahcId="+ahcId+"&winOpen=N&headerShow=N";
	$("#attachDiv iframe").attr({'src':url,
    'height': '100%',
    'width': '100%'});
}
String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
}
var roleId='${UserRole}';

function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function fn_maxmizeTop()
{
	parent.fn_maxmizeTop();
}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
function validateSpaces(input,arg1)
{
	var a=input.value;

	if(a.trim()=="")
	{
	input.value="";
    alert("Cannot accept only spaces in "+arg1);
    focusBox(input);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		alert(arg1+ " should not start with space");
		focusBox(input);
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

function validateTodayDate(arg1,input){

	var fr = focusBox(input);	
    var entered = input.value;
	entered = entered.split("/");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	
	var billDate=""+(cmth)+"/"+ cdy +"/"+ cyr;
	billDate=Date.parse(billDate);
	var claimDate= document.forms[0].claimDt.value;
	if(claimDate!=null && claimDate!=''){
		claimDate = claimDate.split("-");
		var cmth1 = parseInt(claimDate[1],10); 
		var cdy1 = parseInt(claimDate[2],10);
		var cyr1 = parseInt(claimDate[0],10);
			var disDate = ""+(cmth1)+"/"+ cdy1 +"/"+ cyr1;
			disDate=Date.parse(disDate);
			if(billDate<disDate)
			{
			input.value="";
			//alert(arg1+" should be between today's date and discharge Date("+document.forms[0].onlineDschrgeDate.value+")");
			alert("Bill date should not be less than claim Date");
			focusBox(input);
			}
	}
	var regtDate = document.forms[0].regDate.value;
	regtDate = regtDate.split("-");
	var cmth1 = parseInt(regtDate[1],10); 
	var cdy1 = parseInt(regtDate[2],10);
	var cyr1 = parseInt(regtDate[0],10);
		var disDate = ""+(cmth1)+"/"+ cdy1 +"/"+ cyr1;
		disDate=Date.parse(disDate);
		if(billDate<disDate)
		{
		input.value="";
		//alert(arg1+" should be between today's date and discharge Date("+document.forms[0].onlineDschrgeDate.value+")");
		alert("Bill date should not be less than registration Date");
		focusBox(input);
		}
		else{
			focusBox(document.getElementById("medcoRemarks"));
			
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

function validateAlphaNumSpace(arg1,input)
{
	var a=input.value;
	var fr = focusBox(input);
	var regAlphaNum=/^[0-9a-zA-Z ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		if(a.trim().length==0){
			alert("Only Spaces are not allowed in "+arg1);
			focusBox(input);	
			input.value="";
			input.focus();
			return false;
			
		}
		alert("Only AlphaNumeric are allowed for "+arg1);
		focusBox(input);	
		//input.focus();
		return false;
		}
}
function validateNumber(arg1,input)
{
	var a=input.value;
	var regDigit=/^\d+$/ ;
	if(a.search(regDigit)==-1)
		{
		input.value="";
		focusBox(input);
		alert("Only Numbers are allowed for "+arg1);
		return false;
		}
}
function  pasteInterceptPhotoMatch(evt)
{
	var input=document.getElementById('photoMatchRemarks');
	maxLengthPaste(input,4000,evt); 
}
function  pasteInterceptPhoto(evt)
{
	var input=document.getElementById('photoRemarks');
	maxLengthPaste(input,4000,evt); 
}
function  pasteInterceptAcq(evt)
{
	var input=document.getElementById('acquaintanceRemarks');
	maxLengthPaste(input,4000,evt); 
}
function  pasteInterceptReports(evt)
{
	var input=document.getElementById('reportsRemarks');
	maxLengthPaste(input,4000,evt); 
}
function  pasteInterceptExeVerify(evt)
{
	var input=document.getElementById('exeVerifyRemarks');
	maxLengthPaste(input,4000,evt); 
}
function  pasteInterceptRemarks(evt)
{
	var input=document.getElementById('remarks');
	maxLengthPaste(input,4000,evt); 
}
function  pasteInterceptMedco(evt)
{
	var input=document.getElementById('medcoRemarks');
	maxLengthPaste(input,4000,evt); 
}
function fn_claim()
{
	var url="/Operations/ahcClaimsAction.do?actionFlag=claimsPage&ahcId=${ahcId}";	 
	document.forms[0].action=url;   
	document.forms[0].target="bottomFrame";
	document.forms[0].submit(); 
}

</script>
<script>
var saveMsg= '${saveMsg}';
var status= '${status}';

if(saveMsg!='')
{
	alert('${saveMsg}');
	parent.parent.parent.fn_ahcClaimCases();
}
</script>
</head>

<body>

<form action="/ahcClaimsAction.do" method="post" name="ahcClaimsForm">
<div id="middleFrame_content">
<logic:notEmpty name="ahcClaimsForm" property="claimInfo">
<bean:define id="claimDtls" name="ahcClaimsForm" property="claimInfo" />
<c:set var="buttons" value="${claimDtls.buttonList}"/>
</logic:notEmpty>




<logic:empty name="ahcClaimsForm" property="msg">
<table width="100%" style="margin:0 auto">
<tr><td>
<table border="0" width="100%" >
<tr class="tbheader">
<td id="topSlide" width="5%" align="center">
<img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img>
</td>
<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><b><fmt:message key="EHF.title.ClaimDtls"/></b></strong></td>
<td id="menuSlide" width="4%"> 
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
</td>
</tr></table>
<table width="100%">
<tr class="tbheader"><td colspan="6" align="center">Medco Remarks</td>
<tr><td width="20%" align="left" class="labelheading1 tbcellCss"><b>AHC Package Amount :&nbsp;&nbsp;</b></td>
<td width="15%" align="left" class="labelheading1 tbcellCss"><b><bean:write name="ahcClaimsForm" property="packageAmt" /></b></td>
<td width="15%" align="left" class="labelheading1 tbcellCss"><b>Registered Date:&nbsp;&nbsp;</b></td>
<td width="15%" align="left" class="labelheading1 tbcellCss"><b><bean:write name="ahcClaimsForm" property="regDate" /></b></td>
<td width="25%" align="left" class="labelheading1 tbcellCss"><b>Claim submitted Date:&nbsp;&nbsp;</b></td>
<td width="10%" align="left" class="labelheading1 tbcellCss"><b><bean:write name="ahcClaimsForm" property="claimDt" /></b></td>
</tr>
<tr>
<td width="20%" align="left"  class="labelheading1 tbcellCss"><b>Bill amount<font color="red">*</font>:&nbsp;&nbsp;</b></td>
<td width="15%" align="left"  class="labelheading1 tbcellCss" ><html:text name="ahcClaimsForm" styleClass="span2 form-control" property="billAmt" styleId="billAmt" maxlength="6"  onchange="validateNumber('Bill Amount',this)" title="Enter Bill Amount" style="width:90%"/></td>
<td width="15%" align="left"  class="labelheading1 tbcellCss" ><span style="float:left"><b>Bill Date<font color="red">*</font>:&nbsp;&nbsp;</b></span> </td>
<td width="50%" align="left"  class="labelheading1 tbcellCss" colspan="3"><div style="float:left" class="input-append date" id="billDtDiv"><html:text name="ahcClaimsForm" styleClass="span2 form-control"  property="billDt"   styleId="billDt" title="Enter Bill Date" onchange="validateTodayDate('Bill Date',this)" readonly="true" style="float:left;width:50%"/> &nbsp;&nbsp;<span class="add-on">&nbsp;<span style="float:left;margin-left:5px;margin-top:10px;"class="glyphicon glyphicon-calendar"></span></span></div> </td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss"><span  style="float:left;" ><b>Remarks : <font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></span></td>
<td colspan="5"><html:textarea   name="ahcClaimsForm" property="medcoRemarks" styleClass="form-control" styleId="medcoRemarks" style="float:left;width:100%;overflow-y:auto;resize:none;height:3em" cols="30" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" onkeydown="return maxLengthPress(this,4000,event)"  title="Enter Remarks"/></td>
</tr>


<logic:present name ="ahcClaimsForm" property="AHCCNAMRemarks">
		<logic:notEmpty name ="ahcClaimsForm" property="AHCCNAMRemarks">
		<tr class="tbheader">
			<td colspan="6" align="center">
				Mithra Remarks
		</td>
		</tr>
			<tr>
				<td class="labelheading1 tbcellCss" ><b>Remarks :</b></td>
				<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea disabled onchange="check_maxLength(this.id,'3000')"  name="AHCCNAMRemarks" class="form-control" id="AHCCNAMRemarks">${ahcClaimsForm.AHCCNAMRemarks}</textarea></td>
			</tr>
		</logic:notEmpty>
	</logic:present>
<!-- For AHC EXECUTIVE-->
<c:if test="${status!='CD373' && status!='CD374' }">

<tr>
<tr><td colspan="6" class="tbheader" align="center"><b>Executive Remarks</b> </td></tr>
<tr>
<td colspan="6">
	<table style="width:100%">
	<tr>
		<td class="labelheading1 tbcellCss" align="center"><b>Claim check list</b></td>
		<td class="labelheading1 tbcellCss" align="center"><b>Matching</b></td>
		<td class="labelheading1 tbcellCss" align="center"><b>Remarks</b></td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss"><b>Photo Verification</b><br/><br/>
		1) Photo and Health card photo matching : <font color="red">*</font><br/><br/>
		1) Patient Photograph along with Medco exists : <font color="red">*</font><br/><br/>
		</td>
		<td class="tbcellCss"><html:radio name ="ahcClaimsForm" value="Y" styleId="photoMatch" property="photoMatch"/> Yes
		<html:radio name ="ahcClaimsForm" value="N" styleId="photoMatch" property="photoMatch"/> No
		<br/><br/>
		<html:radio name ="ahcClaimsForm" value="Y" styleId="photoAttached" property="photoAttached"/> Yes
		<html:radio name ="ahcClaimsForm" value="N" styleId="photoAttached" property="photoAttached"/> No
		</td>
		<td class="labelheading1 tbcellCss"><html:textarea name="ahcClaimsForm" property="photoMatchRemarks" styleClass="form-control" styleId="photoMatchRemarks" style="overflow-y:auto;resize:none;height:3em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" title="Enter Photo Matching Remarks"/>
		<br/><html:textarea name="ahcClaimsForm" property="photoRemarks" styleClass="form-control" styleId="photoRemarks" style="overflow-y:auto;resize:none;height:3em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" title="Enter Photo Remarks"/>
		</td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss"><b>Document Verification</b><br/><br/>
		1) Acquaintance Form-Fully signed and stamped copy : <font color="red">*</font><br/>
		</td>
		<td class="tbcellCss"><br/><br/><html:radio name ="ahcClaimsForm" value="Y" styleId="acquaintanceAttached" property="acquaintanceAttached"/> Yes
		<html:radio name ="ahcClaimsForm" value="N" styleId="acquaintanceAttached" property="acquaintanceAttached"/> No</td>
		<td class="labelheading1 tbcellCss"><html:textarea name="ahcClaimsForm" property="acquaintanceRemarks" styleClass="form-control" styleId="acquaintanceRemarks" onkeydown="return maxLengthPress(this,4000,event)" style="overflow-y:auto;resize:none;height:3em" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" title="Enter Photo Matching Remarks"/></td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss"><b>Investigation Reports Verification</b><br/><br/>
		1) Reports attached : <font color="red">*</font><br/><br/>
		</td>
		<td class="tbcellCss"><br/><br/><html:radio name ="ahcClaimsForm" value="Y" styleId="reportsAttached" property="reportsAttached"/> Yes
		<html:radio name ="ahcClaimsForm" value="N" styleId="reportsAttached" property="reportsAttached"/> No</td>
		<td class="labelheading1 tbcellCss"><html:textarea name="ahcClaimsForm" property="reportsRemarks" styleClass="form-control" styleId="reportsRemarks" onkeydown="return maxLengthPress(this,4000,event)" style="overflow-y:auto;resize:none;height:3em" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" title="Enter Photo Matching Remarks"/></td>
	</tr>
	
	</table>
</td>
</tr>
<logic:present name ="ahcClaimsForm" property="AHCCEXRemarks">
		<logic:notEmpty name ="ahcClaimsForm" property="AHCCEXRemarks">
			<tr>
				<td class="labelheading1 tbcellCss" ><b>Remarks :</b></td>
				<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea disabled  name="AHCCEXRemarks" onchange="check_maxLength(this.id,'3000')" class="form-control" id="AHCCEXRemarks">${ahcClaimsForm.AHCCEXRemarks}</textarea></td>
			</tr>
		</logic:notEmpty>
	</logic:present>
<script>
if(status!='CD373' && status!='CD374' && status!='CD387'){
	document.forms[0].photoMatch[0].disabled=true;
	document.forms[0].photoMatch[1].disabled=true;
	document.getElementById("photoMatchRemarks").disabled=true;
	document.forms[0].photoAttached[0].disabled=true;
	document.forms[0].photoAttached[1].disabled=true;
	document.getElementById("photoRemarks").disabled=true;
	document.forms[0].acquaintanceAttached[0].disabled=true;
	document.forms[0].acquaintanceAttached[1].disabled=true;
	document.getElementById("acquaintanceRemarks").disabled=true;
	document.forms[0].reportsAttached[0].disabled=true;
	document.forms[0].reportsAttached[1].disabled=true;
	document.getElementById("reportsRemarks").disabled=true;
}


	
</script>
</c:if>

<!-- For Trust Doctor -->
<logic:notEqual name="ahcClaimsForm" property="status" value="CD380">
	<c:if test="${ahcClaimsForm.medcoCTDUpdRemarks ne null && ahcClaimsForm.medcoCTDUpdRemarks ne '' &&  ahcClaimsForm.medcoCTDUpdRemarks ne ' ' }">
	<tr class="tbheader">
	<td colspan="6" align="center">
		Medco Remarks(CTD Pending)
	</td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" ><b>Remarks :<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
		<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea disabled onchange="check_maxLength(this.id,'3000')" name="medcoCTDUpdRemarks" class="form-control" id="medcoCTDUpdRemarks">${ahcClaimsForm.medcoCTDUpdRemarks}</textarea></td>
	</tr>
	</c:if>
</logic:notEqual>
<c:if test="${status!='CD373' && status!='CD374' && status!='CD387'}">
<tr><td colspan="6" class="tbheader" align="center"><b>Trust Doctor Remarks</b></td></tr>
<tr>
<td colspan="6">
	<table style="width:100%">
	<tr>
		<td class="labelheading1 tbcellCss" align="center"><b>Claim check list</b></td>
		<td class="labelheading1 tbcellCss" align="center"><b>Matching</b></td>
		<td class="labelheading1 tbcellCss" align="center"><b>Remarks</b></td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss">
		<b>Executive Remarks Verified : <font color="red">*</font></b><br/><br/>
		
		</td>
		<td class="tbcellCss"><html:radio name ="ahcClaimsForm" value="Y" styleId="exeRemarksVerified" property="exeRemarksVerified"/> Yes
		<html:radio name ="ahcClaimsForm" value="N" styleId="exeRemarksVerified" property="exeRemarksVerified"/> No
		</td>
		<td class="labelheading1 tbcellCss"><html:textarea name="ahcClaimsForm" property="exeVerifyRemarks" styleClass="form-control" styleId="exeVerifyRemarks" style="overflow-y:auto;resize:none;height:3em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" title="Enter Photo Matching Remarks"/></td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss">
		<b>Final Approve Amount : </b> <font color="red">*</font><br/>
		</td>
		<td class="tbcellCss" colspan="2"><html:text name ="ahcClaimsForm" styleClass="form-control" styleId="finalApproveAmt" property="finalApproveAmt"/> 
	</tr>
	
	
	
	</table>
</td>
</tr>

<logic:notEqual name ="ahcClaimsForm" property="status" value="CD590">
<logic:present name ="ahcClaimsForm" property="AHCCTDRemarks">
		<logic:notEmpty name ="ahcClaimsForm" property="AHCCTDRemarks">
			<tr>
				<td class="labelheading1 tbcellCss" ><b>Remarks :</b></td>
				<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea disabled onchange="check_maxLength(this.id,'3000')" name="AHCCTDRemarks" class="form-control" id="AHCCTDRemarks">${ahcClaimsForm.AHCCTDRemarks}</textarea></td>
			</tr>
		</logic:notEmpty>
	</logic:present>
</logic:notEqual>	
<script>
if(status!='CD373' && status!='CD374' && status!='CD387' && status!='CD375' && status!='CD376' && status!='CD377'){
	document.forms[0].exeRemarksVerified[0].disabled=true;
	document.forms[0].exeRemarksVerified[1].disabled=true;
	document.getElementById("exeVerifyRemarks").disabled=true;
	document.getElementById("finalApproveAmt").disabled=true;
}


	
</script>
</c:if>
<logic:equal name="ahcClaimsForm" property="status" value="CD380">
	
	<tr class="tbheader">
	<td colspan="6" align="center">
		Medco Remarks(CTD Pending)
	</td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" ><b>Remarks :<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
		<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea onchange="check_maxLength(this.id,'3000')"  name="medcoCTDUpdRemarks" class="form-control" id="medcoCTDUpdRemarks"></textarea></td>
	</tr>
</logic:equal>


<logic:equal name="UserRole" value="GP1" scope="request">
<tr class="tbheader">
	<td colspan="6" align="center">
		<c:if test="${ahcCaseSchemeId ne 'CD201'}">Mithra Remarks</c:if>
		<c:if test="${ahcCaseSchemeId eq 'CD201'}">Vaidya Mithra Remarks</c:if>
	</td>
</logic:equal>
<logic:notEqual name="ahcClaimsForm" property="status" value="CD384">

	<c:if test="${ahcClaimsForm.medcoCHUpdRemarks ne null && ahcClaimsForm.medcoCHUpdRemarks ne '' &&  ahcClaimsForm.medcoCHUpdRemarks ne ' ' }">
	<tr class="tbheader">
	<td colspan="6" align="center">
		Medco Remarks(CH Pending)
	</td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" ><b>Remarks :<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
		<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea disabled onchange="check_maxLength(this.id,'3000')" name="medcoCHUpdRemarks" class="form-control" id="medcoCHUpdRemarks">${ahcClaimsForm.medcoCHUpdRemarks}</textarea></td>
	</tr>
	</c:if>
</logic:notEqual>
<c:if test="${status eq 'CD378' || status eq 'CD382' || status eq 'CD379' || status eq 'CD390' || status eq 'CD384'}">
<tr class="tbheader"><td colspan="6" align="center">Claim Head Remarks</td>
<tr>
		<td class="labelheading1 tbcellCss" colspan="2">
		<b>Final Approve Amount : </b> <font color="red">*</font><br/>
		</td>
		<td class="tbcellCss" colspan="4"><html:text name ="ahcClaimsForm" styleClass="form-control" styleId="chApproveAmt" property="chApproveAmt" value="${ahcClaimsForm.AHCCHAmt}"/> 
	</tr>
	<logic:notEqual name ="ahcClaimsForm" property="status" value="CD390">
	<logic:present name ="ahcClaimsForm" property="AHCCHRemarks">
		<logic:notEmpty name ="ahcClaimsForm" property="AHCCHRemarks">
			<tr>
				<td class="labelheading1 tbcellCss" ><b>Remarks :</b></td>
				<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea disabled onchange="check_maxLength(this.id,'3000')" name="AHCCHRemarks" class="form-control" id="AHCCHRemarks">${ahcClaimsForm.AHCCHRemarks}</textarea></td>
			</tr>
		</logic:notEmpty>
	</logic:present>
	</logic:notEqual>
	<script>
if(status!='CD378'  && status!='CD379' && status!='CD390' ){
	document.getElementById("chApproveAmt").disabled=true;
}

</script>
<logic:equal name="ahcClaimsForm" property="status" value="CD384">
	
	<tr class="tbheader">
	<td colspan="6" align="center">
		Medco Remarks(CH Pending)
	</td>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" ><b>Remarks :<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
		<td colspan="5" class="labelheading1 tbcellCss" align="center"><textarea onchange="check_maxLength(this.id,'3000')"  name="medcoCHUpdRemarks" class="form-control" id="medcoCHUpdRemarks"></textarea></td>
	</tr>
</logic:equal>

</c:if>

<logic:equal name="UserRole" value="GP17">
<tr class="tbheader"><td colspan="6" align="center">Accounts Officer Remarks</td>

</logic:equal>
<%-- <logic:equal name="UserRole" value="GP2" scope="request">
<tr class="tbheader"><td colspan="6" align="center">Medco Remarks</td>
</logic:equal> --%>
<logic:notEqual name="ahcClaimsForm" property="status" value="CD373">
<logic:notEqual name="ahcClaimsForm" property="status" value="CD380">
<logic:notEqual name="ahcClaimsForm" property="status" value="CD384">

<c:if test="${(casesSearchFlag eq null || (casesSearchFlag ne null && casesSearchFlag ne 'Y'))}">
	<tr>
		<td  class="labelheading1 tbcellCss"><b>Remarks : <font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b>
		<td colspan="5" class="labelheading1 tbcellCss"><html:textarea name="ahcClaimsForm" property="remarks" styleClass="form-control" styleId="remarks" style="overflow-y:auto;resize:none;height:3em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" title="Enter Remarks"/>
		</td>
	</tr>
</c:if>	
</logic:notEqual>
</logic:notEqual>	
</logic:notEqual>

</table>

	<c:if test="${fn:length(buttons) > 0}">
	<tr id="buttonVisibleId" style="display: none;">
	<td colspan="6" align="center">
	<div id="buttonBlock" style="float:left;diplay:inline-block;width:auto;align:center">	
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<button class="btn but" id="${EHFbutton.ID}"  type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	</c:forEach>
	<button type="button" class="btn but" value="Attachments" id="attBtn" title="View Attachments" data-target="#attachDiv" data-toggle="modal" onclick="javascript:fn_viewAttachments()">Attachments</button>
	</div>
	</td>
	</tr>
	</c:if>	
	
<tr><td colspan="5" align="center">&nbsp;</td></tr>
	
<logic:present name="ahcClaimsForm" property="lstworkFlow">
<bean:size id="wrkList" name="ahcClaimsForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="0" width="100%" style="table-layout: fixed;word-wrap:break-word;" cellpadding="1" cellspacing="1" align="center">
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b><fmt:message key="EHF.workFlow.title"/></b></td></tr>
</table>
<table border="0" width="100%" style="table-layout: fixed;word-wrap:break-word;" cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr ><td width="4%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.sno"/></td>
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.Dt"/></td>
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.name"/></td>			
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.role"/></td>
<td width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.reamark"/></td>
<td width="15%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.appAmt"/></td>
<td width="15%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.action"/></td>	
</tr>



<logic:iterate id="results1" name="ahcClaimsForm" property="lstworkFlow" indexId="index1" >
<tr>
<td  class="tbcellBorder"><%=index1+1%></td>
<td  class="tbcellBorder"><bean:write name="results1" property="auditDate" /></td>
<td  class="tbcellBorder"><bean:write name="results1" property="auditName" /></td>
<td  class="tbcellBorder">
						<c:choose>
							<c:when test="${ahcCaseSchemeId eq 'CD201' && results1.auditRole eq 'MITHRA'}" >
								VAIDYA MITHRA
							</c:when>
							<c:otherwise >
								<bean:write name="results1" property="auditRole" />
							</c:otherwise>
						</c:choose> 
</td>
<td  class="tbcellBorder"><bean:write name="results1" property="cexRemark" /></td>
<td  class="tbcellBorder"><bean:write name="results1" property="COUNT" /></td>
<td  class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
</tr>

</logic:iterate>

</table>
</td></tr>
</logic:greaterThan>
</logic:present>	
</table>
<c:if test="${status=='CD382' }">
<script>
document.getElementById("chApproveAmt").disabled=true;
</script>
</c:if>
</logic:empty>
<script>

var flag = null;
var lField = '';
var date = new Date();
if(status!='CD373'){
	document.getElementById("billAmt").disabled=true;
	document.getElementById("billDt").disabled=true;
	document.getElementById("medcoRemarks").disabled=true;
}

var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
var displayMsg='';
function validateMedcoNew()
	{
	var errMsg='';
	lField='';
	var id='medcoCTDUpdRemarks';
	if(document.getElementById('medcoCHUpdRemarks')!=null && document.getElementById('medcoCHUpdRemarks').disabled==false)
		id='medcoCHUpdRemarks';
	
	if(document.getElementById(id).value==null || document.getElementById(id).value==''||document.getElementById(id).value.trim()==null || document.getElementById(id).value.trim()=='')
	{ if(lField=='') lField=id;
	if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks \n";}
	return errMsg;
	}
function validateMedco(){
	
	var errMsg='';
	lField='';
	if(document.getElementById("billAmt").value==null || document.getElementById("billAmt").value=='')
	{ 
		if(lField=='') 
			lField="billAmt";
		if(errMsg=='') 
			errMsg=errMsg+"Please Enter Bill Amount \n";
	}
	else{
			
			var billAmt = parseInt(document.getElementById("billAmt").value,10);
			var PackAmt = document.forms[0].packageAmt.value;
			
		if(billAmt==0){
			if(lField=='') lField="billAmt"; 
			document.getElementById("billAmt").value="";
			if(errMsg=='')  errMsg=errMsg+"Bill Amount cannot be zero \n";
		}
		
		if(billAmt>PackAmt){
			if(errMsg=='')  errMsg=errMsg+"Bill Amount Cannot be greater than Package Amount. \n";
			if(lField=='') lField="billAmt";
			}		
		}
	
		
	
	if(document.getElementById("billDt").value==null || document.getElementById("billDt").value=='')
	{ if(lField=='') lField="billDt";
	if(errMsg=='') errMsg=errMsg+"Please Select Bill Date \n";}
	if(document.getElementById("medcoRemarks").value==null || document.getElementById("medcoRemarks").value==''||document.getElementById("medcoRemarks").value.trim()==null || document.getElementById("medcoRemarks").value.trim()=='')
	{ if(lField=='') lField="medcoRemarks";
	if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks \n";}

	
	return errMsg;
}


function validateCHList(){
	
	var errMsg='';
	lField='';
	if(document.getElementById("chApproveAmt").value==null || document.getElementById("chApproveAmt").value=='')
	{ 
		if(lField=='')
			 lField="chApproveAmt";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Final approve amount.";
	}
	else 
	{
		if(parseInt(document.getElementById("chApproveAmt").value,10)<=0)
		{
			if(lField=='')
				 lField="chApproveAmt";
			errMsg=errMsg+"Final approve amount cannot be zero.";
		}
			if(parseInt(document.getElementById("chApproveAmt").value,10)>parseInt(document.getElementById("packageAmt").value,10))
			{
				if(lField=='')
					 lField="chApproveAmt";
				errMsg=errMsg+"Final approve amount cannot be greater than package amount.";
			}
			else if(parseInt(document.getElementById("chApproveAmt").value,10)>parseInt(document.getElementById("billAmt").value,10))
			{
				if(lField=='')
					 lField="chApproveAmt";
				errMsg=errMsg+"Final approve amount cannot be greater than bill amount.";
			}
	}
	if(document.getElementById("remarks").value==null || document.getElementById("remarks").value==''||document.getElementById("remarks").value.trim()==null || document.getElementById("remarks").value.trim()=='')
	{ if(lField=='') lField="remarks";
	if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks.";}

	
	return errMsg;
}
function validateMithra(){
	
	var errMsg='';
	lField='';
	
	if(document.getElementById("remarks").value==null || document.getElementById("remarks").value==''||document.getElementById("remarks").value.trim()==null || document.getElementById("remarks").value.trim()=='')
	{ if(lField=='') lField="remarks";
	if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks.";}

	
	return errMsg;
}

function validateExe(){
	
	var errMsg='';
	lField='';
	if(document.forms[0].photoMatch[0].checked==false && document.forms[0].photoMatch[1].checked==false){
		if(lField=='') 
			lField="photoMatch";
		if(errMsg=='') 
			errMsg=errMsg+"Please check whether healthcard photo and patient photo are matching or not. \n";

	}
	if(document.getElementById("photoMatchRemarks").value==null || document.getElementById("photoMatchRemarks").value=='')
	{ 
		if(lField=='')
			 lField="photoMatchRemarks";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Photo match related Remarks.";
	}
	if(document.forms[0].photoAttached[0].checked==false && document.forms[0].photoAttached[1].checked==false){
		if(lField=='') 
			lField="photoAttached";
		if(errMsg=='') 
			errMsg=errMsg+"Please check whether patient photo with medco is attached or not. \n";

	}
	if(document.getElementById("photoRemarks").value==null || document.getElementById("photoRemarks").value=='')
	{ 
		if(lField=='')
			 lField="photoRemarks";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Photo related Remarks.";
	}
	if(document.forms[0].acquaintanceAttached[0].checked==false && document.forms[0].acquaintanceAttached[1].checked==false){
		if(lField=='') 
			lField="acquaintanceAttached";
		if(errMsg=='') 
			errMsg=errMsg+"Please check whether acquaintance form is attached or not. \n";

	}
	if(document.getElementById("acquaintanceRemarks").value==null || document.getElementById("acquaintanceRemarks").value=='')
	{ 
		if(lField=='')
			 lField="acquaintanceRemarks";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Document verification remarks.";
	}
	if(document.forms[0].reportsAttached[0].checked==false && document.forms[0].reportsAttached[1].checked==false){
		if(lField=='') 
			lField="reportsAttached";
		if(errMsg=='') 
			errMsg=errMsg+"Please check whether reports are attached or not. \n";

	}
	if(document.getElementById("reportsRemarks").value==null || document.getElementById("reportsRemarks").value=='')
	{ 
		if(lField=='')
			 lField="reportsRemarks";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Reports Remarks.";
	}

	
	if(document.getElementById("remarks").value==null || document.getElementById("remarks").value=='')
	{ if(lField=='') lField="remarks";
	if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks.";}

	
	return errMsg;
}

function validateAhcTDList(){
	
	var errMsg='';
	lField='';
	if(document.forms[0].exeRemarksVerified[0].checked==false && document.forms[0].exeRemarksVerified[1].checked==false){
		if(lField=='') 
			lField="exeRemarksVerified";
		if(errMsg=='') 
			errMsg=errMsg+"Please check whether executive remarks verified or not. \n";

	}
	if(document.getElementById("exeVerifyRemarks").value==null || document.getElementById("exeVerifyRemarks").value=='')
	{ 
		if(lField=='')
			 lField="exeVerifyRemarks";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter verification Remarks.";
	}
	
	if(document.getElementById("finalApproveAmt").value==null || document.getElementById("finalApproveAmt").value=='')
	{ 
		if(lField=='')
			 lField="finalApproveAmt";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Final approve amount.";
	}
	else 
	{
			if(parseInt(document.getElementById("finalApproveAmt").value,10)>parseInt(document.getElementById("packageAmt").value,10))
			{
				if(lField=='')
					 lField="finalApproveAmt";
				errMsg=errMsg+"Final approve amount cannot be greater than package amount.";
			}
			else if(parseInt(document.getElementById("finalApproveAmt").value,10)>parseInt(document.getElementById("billAmt").value,10))
			{
				if(lField=='')
					 lField="finalApproveAmt";
				errMsg=errMsg+"Final approve amount cannot be greater than bill amount.";
			}
	}
	if(document.getElementById("remarks").value==null || document.getElementById("remarks").value=='')
	{ 
		if(lField=='') 
			lField="remarks";
		if(errMsg=='')  
			errMsg=errMsg+"Please Enter Remarks.";
	}
	
	return errMsg;
}
function disableDiv(arg) // To disable all buttons in the div
{	
	var elInput = arg.getElementsByTagName("button");
	for(i=0;i<elInput.length;i++)
	{	
	   elInput[i].disabled=true;
	}
}
function enableDiv(arg) // To enable all buttons in the div
{	
	var elInput = arg.getElementsByTagName("button");
	for(i=0;i<elInput.length;i++)
	{	
	   elInput[i].disabled=false;
	}
}
function fn_buttonClicked(buttonId){
	disableDiv(document.getElementById('buttonBlock'));
 var errMsg='';
 
	if(roleId=='GP2')
		{
			if (buttonId == '<fmt:message key="EHF.Button.update"/>')
				errMsg= validateMedcoNew();
			else
				errMsg= validateMedco();
		}	
	else if(roleId=='GP1')
		errMsg = validateMithra();
	else if(roleId=='GP300')
		errMsg = validateExe();
	else if(roleId=='GP5')
			errMsg = validateAhcTDList();
	else if(roleId=='GP9')
		{
		
		errMsg = validateCHList();
		}
	else if(roleId=='GP17')
		//errMsg = validateACOList();
		errMsg = validateMithra();	 
	else if(roleId=='GP29')
		errMsg= validateEO();
	
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
		 						
		if (!errMsg == '') {
			alert(errMsg);
			enableDiv(document.getElementById('buttonBlock'));
			focusFieldView(lField);
			return false;
		} else {
			if(roleId=='GP2'){
			var ahcId = '<bean:write name="ahcClaimsForm" property="ahcId" />';
			var xmlhttp;
			var url;
			if (window.XMLHttpRequest) {
				xmlhttp = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else {
				alert("Your Browser Does Not Support XMLHTTP!");
			}

			url = '/<%=context%>/ahcClaimsAction.do?actionFlag=checkMandatoryAttch&ahcId='+ahcId+'&attachType=ahcTestAttach&callType=Ajax';
			 xmlhttp.onreadystatechange=function()
			{ 
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]!=null)
				        {	  
					        
				        	if(resultArray[0]=="SessionExpired"){
					    		
					    		alert("Session has been expired" );
					    		parent.sessionExpireyClose();
					    		
					    		}
				    		else if(resultArray[0] =='success')
				        	   {
					        	   
				    			
				    			if(confirm(displayMsg)==true){
				    				claimSubmitMedco(buttonId);
				    			}
				    				else
				    					enableDiv(document.getElementById('buttonBlock'));
				    			
				        	   }
				           else
				        	   {
				        	   alert(resultArray[0]);	
				        	   enableDiv(document.getElementById('buttonBlock'));    	  
				        	  return;
				        	   }
				        } 
			    }
			}
			xmlhttp.open("Post",url,true);
			xmlhttp.send(null);		
		}
		else{
			claimSubmit(buttonId,displayMsg);
		}
	}
		
}
function claimSubmitMedco(buttonId,displayMsg)
{
	
	  var url ="/Operations/ahcClaimsAction.do?actionFlag=saveClaimDtls&actionDone="+buttonId;
		  document.forms[0].action=url;		    
		  document.forms[0].submit(); 

}

function claimSubmit(buttonId,displayMsg)
{
	if(confirm(displayMsg)==true){
	
	  var url ="/Operations/ahcClaimsAction.do?actionFlag=saveClaimDtls&actionDone="+buttonId;
		  document.forms[0].action=url;		    
		  document.forms[0].submit(); 
	}
	else
		enableDiv(document.getElementById('buttonBlock'));
}



function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0);  

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
function maxLengthPaste(field,maxChars,event)
{
	
      event.returnValue=false;
      if(window.clipboardData)
    	  {
      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
			{
      			alert("Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
    	  }
      if (event.clipboardData) 
      {
    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
    		{
    		alert("Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
      }
}
function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
}
if(status=='CD590' ){
	document.getElementById("finalApproveAmt").disabled=false;
	document.forms[0].exeRemarksVerified[0].disabled=false;
	document.forms[0].exeRemarksVerified[1].disabled=false;
	document.getElementById("exeVerifyRemarks").disabled=false;
}
else if(status=='CD390')
	{
	
	document.getElementById("chApproveAmt").disabled=false;
	}
	
if(status=='CD378' || status=='CD382' ){

	document.forms[0].exeRemarksVerified[0].disabled=true;
	document.forms[0].exeRemarksVerified[1].disabled=true;
	document.getElementById("exeVerifyRemarks").disabled=true;
	document.getElementById("finalApproveAmt").disabled=true;
}
var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
	if(document.getElementById("photoMatchRemarks")!=null && document.getElementById("photoMatchRemarks")!='')
		document.getElementById("photoMatchRemarks").attachEvent("paste", pasteInterceptPhotoMatch, false);
	
	if(document.getElementById("photoRemarks")!=null && document.getElementById("photoRemarks")!='')
		document.getElementById("photoRemarks").attachEvent("paste", pasteInterceptPhoto, false);
	if(document.getElementById("acquaintanceRemarks")!=null && document.getElementById("acquaintanceRemarks")!='')
		document.getElementById("acquaintanceRemarks").attachEvent("paste", pasteInterceptAcq, false);
	if(document.getElementById("reportsRemarks")!=null && document.getElementById("reportsRemarks")!='')
		document.getElementById("reportsRemarks").attachEvent("paste", pasteInterceptReports, false);
	if(document.getElementById("exeVerifyRemarks")!=null && document.getElementById("exeVerifyRemarks")!='')
		document.getElementById("exeVerifyRemarks").attachEvent("paste", pasteInterceptExeVerify, false);
	if(document.getElementById("remarks")!=null && document.getElementById("remarks")!='')
		document.getElementById("remarks").attachEvent("paste", pasteInterceptRemarks, false);
	document.getElementById("medcoRemarks").attachEvent("paste", pasteInterceptMedco, false);
	
	}
else if(browserName == "Netscape")
	{
	
	if(document.getElementById("photoMatchRemarks")!=null && document.getElementById("photoMatchRemarks")!='')
		document.getElementById("photoMatchRemarks").addEventListener("paste", pasteInterceptPhotoMatch, false);
	
	if(document.getElementById("photoRemarks")!=null && document.getElementById("photoRemarks")!='')
		document.getElementById("photoRemarks").addEventListener("paste", pasteInterceptPhoto, false);
	if(document.getElementById("acquaintanceRemarks")!=null && document.getElementById("acquaintanceRemarks")!='')
		document.getElementById("acquaintanceRemarks").addEventListener("paste", pasteInterceptAcq, false);
	if(document.getElementById("reportsRemarks")!=null && document.getElementById("reportsRemarks")!='')
		document.getElementById("reportsRemarks").addEventListener("paste", pasteInterceptReports, false);
	if(document.getElementById("exeVerifyRemarks")!=null && document.getElementById("exeVerifyRemarks")!='')
		document.getElementById("exeVerifyRemarks").addEventListener("paste", pasteInterceptExeVerify, false);
	if(document.getElementById("remarks")!=null && document.getElementById("remarks")!='')
		document.getElementById("remarks").addEventListener("paste", pasteInterceptRemarks, false);
	document.getElementById("medcoRemarks").addEventListener("paste", pasteInterceptMedco, false);
	}

</script>
<html:hidden name="ahcClaimsForm" property="ahcId" styleId="ahcId"/>
<html:hidden name="ahcClaimsForm" property="billAmt1" styleId="billAmt1"/>
<html:hidden name="ahcClaimsForm" property="packageAmt" styleId="packageAmt"/>
<html:hidden name="ahcClaimsForm" property="status" styleId="status"/>
<html:hidden name="ahcClaimsForm" property="regDate" styleId="regDate"/>
<html:hidden name="ahcClaimsForm" property="claimDt" styleId="claimDt"/>

</div>
<div class="modal fade bs-example-modal-md" id="attachDiv"  tabindex="-1" role="dialog"  aria-hidden=true style="width:100%;height:100%;" >
	<div class="modal-dialog modal-md " style="width:90%;height:100%" >
	<div class="modal-content " style="width:100%;height:100%;" >
	<div class="modal-header" >
	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
    	<h2 class="modal-title" align="center">Attachments</h2>
    </div>
    <div class="modal-body " style="width:100%;height:86%;" >
   	 		
   				<iframe src="/"  class="embed-responsive-item" seamless="" width="100%" height="100%" ></iframe>
   			
   			 
	</div>
		<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
       </div>
    </div>
</div>
<script>
$(function() {
	$( "#billDt").datepicker({
		 autoclose: true,
		 todayHighlight: true,
		 format: "dd/mm/yyyy",
		 startDate: new Date(y, m, d)	,
		 endDate: new Date(y, m, d)		
	});	
	$('#billAmt,#finalApproveAmt,#chApproveAmt').keypress(function(e) {
		var a = [];
	    var k = e.which;

	    for (i = 48; i < 58; i++)
	        a.push(i);
	    a.push(8);a.push(9);

	    if (!($.inArray(k,a)>=0))
	        e.preventDefault();
	});	
	
}); 
function check_maxLength(id,remarkLength)
	{
		var name = document.getElementById(id).value;
		 if(name != null && name !='' && name.length >= remarkLength)
	 	{
		 	alert("length should not exceed " +remarkLength );
		 	  document.getElementById(id).value='';
		 		//document.getElementById(id).focus();
		 		focusBox(id);
	 	}
	   if(name.trim().length==0){
		alert("Multiple Spaces are not allowed");
		document.getElementById(id).value='';
		//document.getElementById(id).focus();
		focusBox(id);
		   }
	 
	
	}


</script>
</form>

</body>
</fmt:bundle>
</html:html>