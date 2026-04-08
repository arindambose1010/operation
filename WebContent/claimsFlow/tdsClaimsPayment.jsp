<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ page buffer = "25kb"  %> 
<%@ page autoFlush="true" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>TDS Claims Payments</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<style type="text/css">
.displayTable tr td {
    padding: 5px;
    border: 1px solid #D8D8D8;
}
.displayTable {
    border: 1px solid black;
    border-collapse: inherit;
}
.displayTable thead tr th {
    background: none repeat scroll 0 0 #21975D;
    color: white;
    padding: 5px;
}
</style>
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>
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
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0);  

}
function validateDate(arg1,input)
{
	var fr = partial(focusBox,input);	
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		//alert("Please Select "+arg1);
		jqueryErrorMsg('Date Validation',"Please Select "+arg1,fr);
	}
	else
	{
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoB>DoC)
		{
		input.value="";
		//alert(arg1+" should not be greater than Today's Date");
		jqueryErrorMsg('Date Validation',arg1+" should not be greater than Today's Date",fr);
		}
	}
}
function fn_caseSearch()
{
	if(document.getElementById("paymentType").value=="" ){
	    var fr = partial(focusBox,document.getElementById('paymentType'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select TDS payment Type",fr);
		return;  
}
	if(document.getElementById("fromDate").value!="" && document.getElementById("toDate").value==""){
		    var fr = partial(focusBox,document.getElementById('toDate'));
			jqueryErrorMsg('Date Validation',"Select To Date",fr);   
			return;
	}
	if(document.getElementById("toDate").value!="" && document.getElementById("fromDate").value==""){
	    var fr = partial(focusBox,document.getElementById('fromDate'));
		jqueryErrorMsg('Date Validation',"Select From Date",fr);
		return;
}
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType="+document.forms[0].paymentType.value;
     document.forms[0].submit();
}
function fn_reset()
{
	document.forms[0].paymentType.value='';		
	document.forms[0].caseId.value='';		
	document.forms[0].patName.value='';	
	document.forms[0].wapNo.value='';
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType=CD125";
     document.forms[0].submit();
}

function fn_buttonClicked(buttonId){
	var displayMsg='';
	var elements;
	
	if(document.getElementsByClassName) // Returns NodeList here
	{
   	elements = document.getElementsByClassName("selectableCheckbox");
	}

    else if(document.querySelectorAll)
	{
	elements = document.querySelectorAll(".selectableCheckbox");
	}

    var checkFlag = 'N';
    
	for (var i = 0; i < elements.length; i++) {
	  if(elements[i].checked)
		  {checkFlag='Y';break;}
	  else
		  checkFlag='N';
	}
	if(checkFlag!=null && checkFlag=='Y'){
		if (buttonId == '<bean:message key="EHF.Button.Paynow"/>')
		    displayMsg = '<bean:message key="EHF.Button.Msg.Paynow"/>';
		var fr = partial(fn_payNow,buttonId);
	    jqueryConfirmMsg('TDS Payment Confirmation',displayMsg,fr);			
		}
	else{
		var fr = partial(focusBox,document.getElementById('equipNo_id'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select atleast one case for payment",fr);
		return;
		}
	
	
}
function fn_payNow(buttonId){
	document.getElementById("CD99").style.display="none";
	fn_loadImage();
	document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=UpdateTdsClaim&paymentType="+document.forms[0].paymentType.value+"&actionDone="+buttonId+"&tdsStatus="+document.forms[0].caseStatus.value;
    document.forms[0].submit();
}
function checkAll(){
	/*var elements = document.getElementsByClassName("selectableCheckbox");
	for (var i = 0; i < elements.length; i++) {
	  if(document.getElementById("equipNo_id").checked)
	  elements[i].checked=true;
	  else
	  elements[i].checked=false;
	}*/
	var elements;
	if(document.getElementsByClassName) // Returns NodeList here
	{
   	elements = document.getElementsByClassName("selectableCheckbox");
	}
    else if(document.querySelectorAll)
	{
	elements = document.querySelectorAll(".selectableCheckbox");
	}
	for (var i = 0; i < elements.length; i++) {
	  if(document.getElementById("equipNo_id").checked)
	  elements[i].checked=true;
	  else
	  elements[i].checked=false;
	}
	if(document.getElementById("equipNo_id").checked)
	{
	if(document.getElementById("CD99")!=null)
		document.getElementById("CD99").focus();
	}
}
function fn_Refresh()
{
	var schemeTypeSel = '${schemeTypeSel}';
	document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType=CD125&schemeType="+schemeTypeSel;
	document.forms[0].submit();
}
function exportToExcel() {

	if(document.getElementById("paymentType").value=="" ){
	    var fr = partial(focusBox,document.getElementById('paymentType'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select TDS payment Type",fr);
		return;
}
	if(document.getElementById("fromDate").value!="" && document.getElementById("toDate").value==""){
		    var fr = partial(focusBox,document.getElementById('toDate'));
			jqueryErrorMsg('Date Validation',"Select To Date",fr);
			return;
	}
	if(document.getElementById("toDate").value!="" && document.getElementById("fromDate").value==""){
	    var fr = partial(focusBox,document.getElementById('fromDate'));
		jqueryErrorMsg('Date Validation',"Select From Date",fr);
		return;
}
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType="+document.forms[0].paymentType.value+"&genFlag=E";
     document.forms[0].submit();
}
function exportToPdf() {
	if(document.getElementById("paymentType").value=="" ){
	    var fr = partial(focusBox,document.getElementById('paymentType'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select TDS payment Type",fr);
		return;
}
	if(document.getElementById("fromDate").value!="" && document.getElementById("toDate").value==""){
		    var fr = partial(focusBox,document.getElementById('toDate'));
			jqueryErrorMsg('Date Validation',"Select To Date",fr);
			return;
	}
	if(document.getElementById("toDate").value!="" && document.getElementById("fromDate").value==""){
	    var fr = partial(focusBox,document.getElementById('fromDate'));
		jqueryErrorMsg('Date Validation',"Select From Date",fr);
		return;
}
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType="+document.forms[0].paymentType.value+"&genFlag=P";
     document.forms[0].submit();
}
</script>
</head>
<body>
<html:form action="/ClaimsFlow.do">
<div id="processImagetable" style="width:100%;position:absolute;z-index:60;height:100%;display: none">
        <table border="0" align="center" width="100%">
          <tr>
            <td class="labelheading1 tbcellCss" style="height:500px;text-align:center;vertical-align:center;" colspan="6">
              <span style="font-weight: bold; margin: 10px; font-size: 18px;">
					Payment File generation in process.
					<br>
					Please Do not press Back Button or Refresh the page...
				</span>
              <div id="processImage" align="center" style="margin-top:20px;">
                <img src="images/Progress.gif" width="100" height="100" border="0" tabindex="3"></img>
              </div>
            </td>
</tr>
</table>
</div>  
<c:if test="${saveMsg ne null}">
<script>
//var fr = partial(fn_caseSearch());
jqueryInfoMsg('TDS Claim Information','${saveMsg}',fn_Refresh);
</script>
</c:if>
<div id="divOnloadShow" style="width:100%;height: 100%">
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr><td>
<table class="tbheader">
<tr align="center">
<td  colspan="8" align="center"  class="tbheader" ><b> TDS Payment </b>
</td></tr>
</table>
</td></tr>
<tr><td>
<table border="1px"  width="100%">
<tr><td>
<table width="100%">
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>TDS Payment Type</b><font color="red">*</font></td>
<td width="16%" class="tbcellBorder"><html:select  name="claimsFlowForm" property="paymentType" title="Select Payment Type" styleId="paymentType" style="width:200px;">
<option  value="">----Select----</option>
<html:options collection="tdsPaymentList" property="ID" labelProperty="VALUE"  />
</html:select>
</td>


<td width="16%" class="labelheading1 tbcellCss"><b>Case Number</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="caseId" title="Enter Case Number" style="width:110px;" maxlength="50"/></td>
<td  width="16%" class="labelheading1 tbcellCss"><b>Card Number</b></td>
<td width="16%" class="tbcellBorder"><html:text style="width:110px;" name="claimsFlowForm" title="Enter Card Number" property="wapNo" /></td>
</tr>
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>Patient Name</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="patName" title="Enter Patient Name" maxlength="50"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>From Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:100px"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>To Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:100px"/></td>
</tr> 
<tr>	
<td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
<td width="16%" class="tbcellBorder"><html:select property="schemeType" name="claimsFlowForm" style="width:150px;"  title="Please select Scheme">
<html:option value="CD201">Andhra</html:option>
<html:option value="CD202">Telangana</html:option> 
</html:select></td>
</tr>
<tr ><td colspan="8">&nbsp;</td>
</tr>

<tr><td colspan="2">&nbsp;</td><td align="center" colspan="2">
<button class="but"   type="button" name="Search" value="Search" onclick="javascript:fn_caseSearch()"  >Search</button>
<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  >Reset</button>
</td>
<logic:equal value="Y" name="claimsFlowForm" property="genFlag">
	<logic:present name="claimsFlowForm" property="casesForPaymentList">
		<bean:size  id="listSize1" name="claimsFlowForm" property="casesForPaymentList"/>
		<logic:greaterThan value="0" name="listSize1">
<td align="center" colspan="2">
			<b>Download As:</b>
			<img src="images/excel1.png"  onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/>
			<img src="images/pdf1.png" onmouseover="this.src='images/pdf2.png'" onmouseout="this.src='images/pdf1.png'" onclick="javascript:exportToPdf()" />
		</td></logic:greaterThan></logic:present></logic:equal>
</tr></table></td></tr>

</table>

<logic:equal value="Y" name="claimsFlowForm" property="genFlag">
	<logic:present name="claimsFlowForm" property="casesForPaymentList">
		<bean:size  id="listSize" name="claimsFlowForm" property="casesForPaymentList"/>
		<logic:greaterThan value="0" name="listSize">
			<%int i = 1;%>
    		<display:table  id="data1" class="displayTable" name="claimsFlowForm.casesForPaymentList" pagesize="100"  
    		style="width:100%;align:center;margin:15px auto;"  export="false" requestURI="/ClaimsFlow.do"  cellpadding="1" cellspacing="1">
      			<display:setProperty name="export.pdf" value="false"  /> 
      			<display:setProperty name="export.excel" value="false"  /> 
    			<display:setProperty name="export.pdf.filename" value="ClaimPayments.pdf"/>   
    			<display:setProperty name="export.excel.filename" value="ClaimPayments.xls"/>
    			<display:column value="<%=i++ %>" title="S No"   />
    			<display:column   style="word-wrap:break-word;padding:3px;" title="TDS Payment Id" media="html" property="ID" />
    			<display:column  title="TDS Payment Id" media="pdf" >${data1.ID}</display:column>
    			<display:column  title="TDS Payment Id" media="excel" >${data1.ID}</display:column>
    			<display:column   style="word-wrap:break-word;padding:3px;" title="Case Id" media="html" property="DSGNID" />
    			<display:column  style="word-wrap:break-word;padding:3px;" title="Patient Name" property="VALUE"/>
    			<display:column  style="word-wrap:break-word;padding:3px;" title="Hospital Name" property="LVL"/>
    			<display:column  style="word-wrap:break-word;padding:3px;" title="Card Number" property="UNITID" />
    			<display:column   style="word-wrap:break-word;padding:3px;" title="TDS Amount" property="WFTYPE" />
    			<display:column style="word-wrap:break-word;padding:3px;" title="<input type='checkbox' id='equipNo_id' title='select All' name='equipNo' onclick='javascript:checkAll();' >" media="html"  >
                   &nbsp;&nbsp;<html:checkbox name="claimsFlowForm"  title="select" styleClass="selectableCheckbox"  property="caseSelected" value="${data1.ID}" styleId="caseSelected" />
                </display:column>                    
    		 </display:table>
    		 <table width="100%" align="center" border="0">
<tr  align="center"><td align="center">
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<button class="but"   type="button"  value="${EHFbutton.VALUE}"  id="${EHFbutton.ID}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	</c:forEach>
</td><logic:equal value="CD94" name="claimsFlowForm" property="caseStatus">
<td>
<button class="but"   type="button"  value="viewDocument" name="viewDocument" onclick="javascript:fn_attachments();">View Documents</button>
</td>
</logic:equal></tr>
</table>
		</logic:greaterThan>
		<logic:equal value="0" name="listSize">
  				<table width="100%" border="0" cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;">
<tr >
<td align="center" height="50">
<b>No results found</b>
</td>
</tr>
</table>
  		</logic:equal>
</logic:present>
	

</logic:equal>
 <table><tr><td>&nbsp;</td></tr></table>
 <script>
function fn_attachments()
{
	var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfTDS&caseAttachmentFlag=CP";
    parent.parent.parent.claimAttachWindow = window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');	
}
function fn_loadImage()
{
	document.getElementById('divOnloadShow').style.display="none";
	document.getElementById('processImagetable').style.display="";

}
</script></td></tr></table></div>
 <html:hidden property="roleId" name="claimsFlowForm"/>
 <html:hidden property="caseStatus" name="claimsFlowForm"/>
</html:form>
</body>
</html>