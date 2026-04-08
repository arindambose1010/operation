<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:bundle basename="Claims">
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chronic OP Claims Payments</title>
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
#countTbl
{
align:center;
 margin: 0 auto;
}
</style>
<script src="js/jquery-1.9.1.min.js"></script> 
<%@ include file="/common/includeCalendar.jsp"%> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
 <%@ page buffer = "25kb"  %> 
<%@ page autoFlush="true" %>
<script type="text/javascript">
var caseSelFlag = ""; 
var casesSelected=[];
var caseClaimAmt=[];
var totClaimAmt=0;
var caseCount=0;

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
function viewCaseDtls(caseNo){
	
	var url="/<%=context%>/ClaimsFlow.do?actionFlag=getCaseDetails&CaseNo="+caseNo;
	window.open(url,"claimsPayment",'width=1000,height=800,resizable=yes,top=50,left=100,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
}
function viewChronicDtls(chronicId){
	
	var url="/<%=context%>/chronicAction.do?actionFlag=casePrintForm&printFlag=N&chronicId="+chronicId+"&type=EoView";
	window.open(url,"claimsPayment",'width=1000,height=800,resizable=yes,top=50,left=100,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
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
	
	if(document.getElementById("caseStatus").value=="" ){
	    var fr = partial(focusBox,document.getElementById('caseStatus'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select Case Status",fr);
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
	if(document.forms[0].showScheme.value=='show'){
   	 if(document.forms[0].schemeType.value==''){
   		 jqueryAlertMsg('Cases Search','Please select any search criteria');
			 return;
	    }
   	 else{
  
   		 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds&chronicStatus="+document.forms[0].caseStatus.value+"&schemeType="+document.forms[0].schemeType.value;
		 document.forms[0].submit();}
	}else{	
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds&chronicStatus="+document.forms[0].caseStatus.value;
     document.forms[0].submit();
	}
}
function fn_reset()
{
	document.forms[0].caseStatus.value='';		
	document.forms[0].caseId.value='';		
	document.forms[0].patName.value='';	
	document.forms[0].wapNo.value='';
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds";
     document.forms[0].submit();
}

function fn_buttonClicked(buttonId){
	var displayMsg='';	
    var checkFlag = 'N';
       
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
	  if(elements[i].checked)
		  {checkFlag='Y';break;}
	  else
		  checkFlag='N';
	}
	if(checkFlag!=null && checkFlag=='Y'){
		if (buttonId == 'CD99')
		{
		    displayMsg = '<bean:message key="EHF.Button.Msg.Paynow"/>';
			var fr = partial(fn_payNow,buttonId);
		}
		if (buttonId == 'CD97')
		{
		    displayMsg = '<bean:message key="EHF.Button.Msg.SentBackToCH"/>';

			var fr = partial(fn_backToCh,buttonId);
		}
	   	    jqueryConfirmMsg('Claim Payment Confirmation',displayMsg,fr);			
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
	document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=changeChronicClaimForCases&chronicStatus="+document.forms[0].caseStatus.value+"&actionDone="+buttonId;
    document.forms[0].submit();
}
function fn_backToCh(buttonId){
	document.getElementById("CD97").style.display="none";
	fn_loadImage();
	document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=changeChronicClaimForCases&chronicStatus="+document.forms[0].caseStatus.value+"&actionDone="+buttonId;
    document.forms[0].submit();
}

function checkOrUncheckCase(caseId,claimAmt)
{
var elements=document.getElementsByTagName("input");
	
	var count=0;
	var count2=0;
    casesSelected=[];
    caseClaimAmt=[];
   

	for (i=0;i<elements.length;i++)
	{
		
	if(elements[i].type=="checkbox")
	{
		casesSelected[i]=elements[i].value;
		if(elements[i].checked==true )
		{
          
			
			if(caseId==casesSelected[i])
			{
			totClaimAmt=totClaimAmt+parseFloat(claimAmt);
			caseCount++;
			}
			
			
			
		}
		else if(i>1)
		{
			elements[i].checked=false;
			
			if(caseId==casesSelected[i])
			{
			totClaimAmt=totClaimAmt-claimAmt;
			caseCount--;
			}
			
		}
	
     }
	
	}
	
	document.getElementById("caseCnt").innerHTML="<b><font color=red>"+caseCount+"</font></b>";
	fn_amount(totClaimAmt);
	//document.getElementById("totAmt").innerHTML="<b><font color=red>"+totClaimAmt+"</font></b>";
	
	//alert(totClaimAmt);
	//document.forms[0].casesSelected.value=casesSelected;
	//alert(document.forms[0].casesSelected.value);
	
}

function fn_amount(amount)
{
	var amount=amount.toString();
	var length=amount.length;
	var lengthTemp=length;
	var amtString="";
	var amtTemp="";
	if(length>3)
	{
		amtTemp=amount.substr(length-3,3);
		
		amtString=","+amtTemp;
		lengthTemp=length-3;
	for(var i=0;lengthTemp>0;i++)
	{

	 
		
		
		if(lengthTemp>2)
		{
		amtTemp=amount.substr(lengthTemp-2,2);
		
		amtString=","+amtTemp+amtString;
		lengthTemp=lengthTemp-2;
		}
		else
		{
			if(lengthTemp==2)
		amtTemp=amount.substr(0,2);
			else
		amtTemp=amount.substr(0,1);			
		amtString=amtTemp+amtString;
        break;
		}	
		
	    }
	}
	else
	{
		amtString=amount;
	}
	
	document.getElementById("totAmt").innerHTML="<b><font color=red> Rs "+amtString+"/- </font></b>";
	
	
}

function checkAll(){
	/*var elements = document.getElementsByClassName("selectableCheckbox");

	for (var i = 0; i < elements.length; i++) {
	  if(document.getElementById("equipNo_id").checked)
	  elements[i].checked=true;
	  else
	  elements[i].checked=false;
	}*/
	if(document.getElementById("equipNo_id").checked)
	{
	caseCount=0;
	totClaimAmt=0;
	}
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
	  {
	  elements[i].checked=true;
	  }
	  else
	  {
		
	  elements[i].checked=false;
	  }
	}

	$('.selectableCheckbox').trigger("change");
	
	if(document.getElementById("equipNo_id").checked)
	{
	if(document.getElementById("CD99")!=null)
		document.getElementById("CD99").focus();
	}

	if(!document.getElementById("equipNo_id").checked)
	{
		caseCount=0;
		totClaimAmt=0;
		document.getElementById("caseCnt").innerHTML="<b><font color=red>"+caseCount+"</font></b>";

		fn_amount(totClaimAmt);
		//document.getElementById("totAmt").innerHTML="<b><font color=red>"+totClaimAmt+"</font></b>";
		
	}
}
function fn_Refresh()
{
	var schemeTypeSel = '${schemeTypeSel}';
	var sentBackFlag='${sentBackFlag}';
	if(sentBackFlag!=null && sentBackFlag=='Y')
	{
		
window.opener.location.reload();
window.close();
	}  
	else
	{
	document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds&schemeType="+schemeTypeSel;
	document.forms[0].submit();
	}
}
function exportToExcel() {

	if(document.getElementById("caseStatus").value=="" ){
	    var fr = partial(focusBox,document.getElementById('caseStatus'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select Case Status",fr);
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
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds&chronicStatus="+document.forms[0].caseStatus.value+"&genFlag=E";
     document.forms[0].submit();
}
function exportToPdf() {
	if(document.getElementById("caseStatus").value=="" ){
	    var fr = partial(focusBox,document.getElementById('caseStatus'));
		jqueryErrorMsg('Claim Payment Mandatory Field',"Please select Case Status",fr);
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
	 document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds&chronicStatus="+document.forms[0].caseStatus.value+"&genFlag=P";
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
jqueryInfoMsg('Claim Information','${saveMsg}',fn_Refresh);
</script>
</c:if>
<div id="divOnloadShow" style="width:100%;height: 100%">
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr><td>
<table class="tbheader">
<tr align="center">
<td  colspan="8" align="center"  class="tbheader" ><b>Chronic Claim Payment</b>
</td></tr>
</table>
</td></tr>
<tr><td>
<table border="1px"  width="100%">
<tr><td>
<table width="100%">
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>Case Status</b><font color="red">*</font></td>
<td width="16%" class="tbcellBorder"><html:select  name="claimsFlowForm" property="caseStatus" styleId="caseStatus" style="width:150px;" title="Select Case Status">
<option  value="">----Select----</option>
<html:options collection="StatusList" property="ID" labelProperty="VALUE"  />
</html:select>
</td>
<td width="16%" class="labelheading1 tbcellCss"><b>Chronic Number</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="caseId" style="width:150px;" maxlength="50" title="Enter Case Number"/></td>
<td  width="16%" class="labelheading1 tbcellCss"><b>Card Number</b></td>
<td width="16%" class="tbcellBorder"><html:text style="width:150px;" name="claimsFlowForm" property="wapNo" title="Enter Card Number"/></td>
</tr>
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>Patient Name</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" style="width:150px;" property="patName" maxlength="50" title="Enter Patient Name"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>From Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="fromDate" style="width:120px;" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" /></td>
<td width="16%" class="labelheading1 tbcellCss"><b>To Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="claimsFlowForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:120px;"/></td>
</tr>
<tr>
	 <logic:equal name="claimsFlowForm"  property="showScheme" value="show">
	    <td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
		<td width="16%" class="tbcellBorder"><html:select property="schemeType" name="claimsFlowForm" style="width:150px;" title="Please select Scheme">
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						</html:select></td>
						</logic:equal>
	</tr>
<tr><td colspan="2">&nbsp;</td><td align="center" colspan="2">
<button class="but"   type="button" name="Search" value="Search" onclick="javascript:fn_caseSearch()"  >Search</button>
<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  >Reset</button>
</td>
<logic:equal value="Y" name="claimsFlowForm" property="genFlag">
	<logic:present name="claimsFlowForm" property="casesForPaymentList">
		<bean:size  id="listSize1" name="claimsFlowForm" property="casesForPaymentList"/>
		<logic:greaterThan value="0" name="listSize1">
<td  align="center" colspan="2">
			<b>Download As:</b>
			<img src="images/excel1.png"  onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/>
			<img src="images/pdf1.png" onmouseover="this.src='images/pdf2.png'" onmouseout="this.src='images/pdf1.png'" onclick="javascript:exportToPdf()" />
			
		</td>
		</logic:greaterThan></logic:present></logic:equal>
		</tr></table></td></tr>

</table>

<logic:equal value="Y" name="claimsFlowForm" property="genFlag">
	<logic:present name="claimsFlowForm" property="casesForPaymentList">
		<bean:size  id="listSize" name="claimsFlowForm" property="casesForPaymentList"/>
		<logic:greaterThan value="0" name="listSize">
			<%int i = 1;%>
    		<display:table  id="data1" class="displayTable" name="claimsFlowForm.casesForPaymentList" pagesize="${pageSize}" decorator="com.ahct.claims.service.chronicClaimPaymentDecorator" 
    		style="width:auto;align:center;margin:15px auto;table-layout: fixed;"  export="false" requestURI="/ClaimsFlow.do"  cellpadding="1" cellspacing="1">
      			<display:setProperty name="export.pdf" value="false"  /> 
      			<display:setProperty name="export.excel" value="false"  /> 
      			<display:setProperty name="export.csv" value="false"  /> 
      			<display:setProperty name="export.xml" value="false"  />
    			<display:setProperty name="export.pdf.filename" value="ClaimPayments.pdf"/>   
    			<display:setProperty name="export.excel.filename" value="ClaimPayments.xls"/>
    			<display:setProperty name="export.csv.filename" value="ClaimPayments.csv"/>   
    			<display:setProperty name="export.xml.filename" value="ClaimPayments.xml"/>
    			<display:column value="<%=i++ %>" title="S No" style="width:5%;text-align:center;"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:15%" title="Chronic No" media="html" property="ID" />
    			<display:column  title="Chronic Id" media="pdf" >${data1.ID}</display:column>
    			<display:column  title="Chronic Id" media="excel" >${data1.ID}</display:column>  
    			<display:column  title="Chronic Id" media="csv" >${data1.ID}</display:column>
    			<display:column  title="Chronic Id" media="xml" >${data1.ID}</display:column> 
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:15%" title="Patient Name" property="VALUE"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:15%" title="Hospital Name" property="LVL"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:15%" title="Hospital Account Name" property="SUBNAME"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:10%" title="Card Number" property="UNITID" />
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:10%" title="Claim Amount" property="WFTYPE" />
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:10%" title="District" property="EMPNAME"/>
    			<logic:equal value="CD93" property="caseStatus" name="claimsFlowForm">
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:5%;text-align:center;" title=" <input type='checkbox' id='equipNo_id' name='equipNo' title='select All' onclick='javascript:checkAll();' >" media="html"  >
                   &nbsp;<html:checkbox name="claimsFlowForm"  title="select" styleClass="selectableCheckbox"  property="caseSelected" value="${data1.ID}" styleId="caseSelected" />
                </display:column>
                </logic:equal>
                <logic:equal value="CD98" property="caseStatus" name="claimsFlowForm">
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:5%;text-align:center;" title=" <input type='checkbox' id='equipNo_id' name='equipNo' title='select All' onclick='javascript:checkAll();' >" media="html"  >
                   &nbsp;<html:checkbox name="claimsFlowForm"  title="select" styleClass="selectableCheckbox"  onchange="checkOrUncheckCase('${data1.ID}','${data1.WFTYPE}')"  property="caseSelected" value="${data1.ID}" styleId="caseSelected" />
                </display:column>
                </logic:equal>
    		   <logic:equal value="CD416" property="caseStatus" name="claimsFlowForm">
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:5%;text-align:center;" title="<input type='checkbox' id='equipNo_id' name='equipNo' title='select All' onclick='javascript:checkAll();' >" media="html"  >
                   <html:checkbox name="claimsFlowForm" styleClass="selectableCheckbox"  title="select" property="caseSelected" onchange="checkOrUncheckCase('${data1.ID}','${data1.WFTYPE}')"  value="${data1.ID}" styleId="caseSelected" />
                </display:column>
                </logic:equal>
                 <logic:equal value="CD412" property="caseStatus" name="claimsFlowForm">
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:5%;text-align:center;" title="<input type='checkbox' id='equipNo_id' name='equipNo' title='select All' onclick='javascript:checkAll();' >" media="html"  >
                   <html:checkbox name="claimsFlowForm" styleClass="selectableCheckbox"  title="select" property="caseSelected" value="${data1.ID}" onchange="checkOrUncheckCase('${data1.ID}','${data1.WFTYPE}')"  styleId="caseSelected" />
                </display:column>
                </logic:equal>
      		 </display:table>
      		 
      		 
      		 
      		 
      		 
      		 <table id="countTbl" width="50%" align="center">
      		 <tr >
      		 <td  class="labelheading1 tbcellCss" width="70%"><b>Total Number Of Cases Selected :</b></td>
      		 <td  class="tbcellBorder" id="caseCnt" width="30%"><b><font color="red">0</font></b></td>   
      		
      		 </tr>
      		 
      		  <tr >
      		 <td  class="labelheading1 tbcellCss" width="70%"><b>Total Amount Being Approved :</b></td>
      		 <td class="tbcellBorder" id="totAmt" width="30%"><b><font color="red">Rs 0/-</font></b></td>   		 		 
      		 </tr> 
      		 
      		 </table>
      		 
      		 
      		 
      		 
      		 
      		 
      		 
      		 
      		 
    		 <table width="100%" align="center" border="0">
<tr  align="center"><td align="center">
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<button class="but"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" id="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	</c:forEach>
</td>
<logic:equal value="CD94" name="claimsFlowForm" property="caseStatus">
<td>
<button class="but"   type="button"  value="viewDocument" name="viewDocument" onclick="javascript:fn_attachments('Doc');">View Documents</button>
<button class="but"   type="button"  value="viewResFile" name="viewResFile" onclick="javascript:fn_attachments('Res');">View Response File</button>
</td>
</logic:equal>
<logic:equal value="CD51" name="claimsFlowForm" property="caseStatus">
<td>
<button class="but"   type="button"  value="viewResFile" name="viewResFile" onclick="javascript:fn_attachments('Res');">View Response File</button>
</td>
</logic:equal>
</tr>
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
 <html:hidden property="roleId" name="claimsFlowForm"/>
 <html:hidden property="showScheme" name="claimsFlowForm" />
</td></tr></table>
 <table><tr><td>&nbsp;</td></tr></table>
 <table><tr><td>&nbsp;</td></tr></table>
 <table><tr><td>&nbsp;</td></tr></table></div>
<script>
function fn_attachments(flag)
{
	var folderFlag = "";
	if(document.forms[0].caseStatus.value=="CD94"){
		folderFlag = "OutFol";
	}
	else if(document.forms[0].caseStatus.value=="CD51"){
		folderFlag = "RecClaimFol";
	}
	var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfClaim&caseAttachmentFlag=CP&flag="+flag+"&folderFlag="+folderFlag;
    parent.parent.parent.claimAttachWindow = window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');	
}
function fn_loadImage()
{
	document.getElementById('divOnloadShow').style.display="none";
	document.getElementById('processImagetable').style.display="";

}
</script>

</html:form>
</body>
</html></fmt:bundle>