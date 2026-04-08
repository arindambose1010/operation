<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html >
<html>
<head>
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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Claims Payments</title>
	<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<%@ include file="/common/includeBootstrapCalendar.jsp"%>  
</head>
<body style="font-size:1.1em;">
	<html:form action="/ahcClaimsAction.do" method="POST">
	
	<div >
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
alert('${saveMsg}');
fn_Refresh();
</script>
</c:if>
		<div class="panel-group" id="accordion">
  			<div class="panel panel-default">
    			<div class="tbheader" style="text-align:center;">
        			<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         			<span class="glyphicon glyphicon-plus" style="line-height: 2;"><b>ANNAUL HEALTH CHECKUP CLAIM PAYMENTS</b></span>
         			</a>
         		</div>
         	</div>
         </div>
       <div id="collapseOne" class="panel-collapse collapse"> 
      		<div class="panel-body">
				<table class="table table-responsive tb" style="font-size:1.2em;">
				<tr>
<td  class="labelheading1 tbcellCss"><b >Case Status</b><font color="red">*</font></td>
<td  class="tbcellBorder"><html:select  name="ahcClaimsForm" property="status" styleClass="form-control" styleId="status"  title="Select Case Status">
<option  value="">----Select----</option>
<option  value="CD385" selected="selected">ACO Approved</option>

</html:select>
</td>
<td class="labelheading1 tbcellCss"><b>Case Number</b></td>
<td  class="tbcellBorder"><html:text name="ahcClaimsForm" property="ahcId"  styleClass="form-control" maxlength="50" title="Enter Case Number"/></td>
<td  class="labelheading1 tbcellCss"><b>Card Number</b></td>
<td class="tbcellBorder"><html:text  name="ahcClaimsForm" property="cardNo" styleClass="form-control" title="Enter Card Number"/></td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss"><b>Patient Name</b></td>
<td  class="tbcellBorder"><html:text name="ahcClaimsForm"  property="name" styleClass="form-control" maxlength="50" title="Enter Patient Name"/></td>
<td  class="labelheading1 tbcellCss"><b>From Date</b></td>
<td class="tbcellBorder"><html:text name="ahcClaimsForm" property="fromDate"  styleClass="form-control" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" /></td>
<td class="labelheading1 tbcellCss"><b>To Date</b></td>
<td class="tbcellBorder"><html:text name="ahcClaimsForm" property="toDate" styleClass="form-control" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" /></td>
</tr>
<tr>
	 <logic:equal name="ahcClaimsForm"  property="showScheme" value="show">
	    <td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
		<td width="16%" class="tbcellBorder"><html:select property="scheme" name="ahcClaimsForm"  title="Please select Scheme">
						<html:option value="CD201">Andhra Pradesh</html:option>
						<html:option value="CD202">Telangana</html:option> 
						</html:select></td>
						</logic:equal>
	</tr>
				<tr><td colspan="2">&nbsp;</td><td align="center" colspan="2">
<button class="btn but"   type="button" name="Search" value="Search" onclick="javascript:fn_caseSearch()"  >Search</button>
<button class="btn but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  >Reset</button>
</td>
<div id="divOnloadShow">
<logic:equal value="Y" name="ahcClaimsForm" property="genFlag">
	<logic:present name="ahcClaimsForm" property="casesForPaymentList">
		<bean:size  id="listSize1" name="ahcClaimsForm" property="casesForPaymentList"/>
		<logic:greaterThan value="0" name="listSize1">
<td  align="center" colspan="2">
			<b>Download As:</b>
			<img src="images/excel1.png"  onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/>
			<img src="images/pdf1.png" onmouseover="this.src='images/pdf2.png'" onmouseout="this.src='images/pdf1.png'" onclick="javascript:exportToPdf()" />
			
		</td>
		</logic:greaterThan></logic:present></logic:equal>
		</tr></table>
			</div>
		</div>
		
<logic:equal value="Y" name="ahcClaimsForm" property="genFlag">
	<logic:present name="ahcClaimsForm" property="casesForPaymentList">
		<bean:size  id="listSize" name="ahcClaimsForm" property="casesForPaymentList"/>
		<logic:greaterThan value="0" name="listSize">
			<%int i = 1;%>
    		<display:table  id="data1" class="displayTable" name="ahcClaimsForm.casesForPaymentList" pagesize="${pageSize}" 
    		style="width:auto;align:center;margin:15px auto;table-layout: fixed;font-size:1.2em;"  export="false" requestURI="/ClaimsFlow.do"  cellpadding="1" cellspacing="1">
      			<display:setProperty name="export.pdf" value="false"  /> 
      			<display:setProperty name="export.excel" value="false"  /> 
      			<display:setProperty name="export.csv" value="false"  /> 
      			<display:setProperty name="export.xml" value="false"  />
    			<display:setProperty name="export.pdf.filename" value="ClaimPayments.pdf"/>   
    			<display:setProperty name="export.excel.filename" value="ClaimPayments.xls"/>
    			<display:setProperty name="export.csv.filename" value="ClaimPayments.csv"/>   
    			<display:setProperty name="export.xml.filename" value="ClaimPayments.xml"/>
    			<display:column value="<%=i++ %>" title="S No" style="width:5%;text-align:center;"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:12%;text-align:center" title="AHC Id" media="html" property="ID" />
    			<display:column  title="Ahc Id" media="pdf" >${data1.ID}</display:column>
    			<display:column  title="Ahc Id" media="excel" >${data1.ID}</display:column>  
    			<display:column  title="Ahc Id" media="csv" >${data1.ID}</display:column>
    			<display:column  title="Ahc Id" media="xml" >${data1.ID}</display:column> 
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:18%;text-align:center;" title="Name" property="VALUE"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:15%;text-align:center;" title="Hospital Name" property="LVL"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:15%;text-align:center;" title="Hospital Account Name" property="SUBNAME"/>
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:10%;text-align:center;" title="Card Number" property="UNITID" />
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:10%;text-align:center;" title="Claim Amount" property="WFTYPE" />
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:10%;text-align:center;" title="District" property="EMPNAME"/>
    			
    			<c:if test="${status!='CD385' && status!='CD391' }">
    			<display:column  style="word-wrap:break-word; word-break: break-all;padding:3px;width:5%;text-align:center;" title=" <input type='checkbox' id='equipNo_id' name='equipNo' title='select All' onclick='javascript:checkAll();' >" media="html"  >
                   &nbsp;<html:checkbox name="ahcClaimsForm"  title="select" styleClass="selectableCheckbox"  property="caseSelected" value="${data1.ID}" styleId="caseSelected" />
                </display:column>
                </c:if>
               
               
      		 </display:table>
    		 <table width="100%" align="center" border="0">
<tr  align="center"><td align="center">
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<button class="btn but"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" id="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	</c:forEach>
</td>
<logic:equal value="CD94" name="ahcClaimsForm" property="status">
<td>
<button class="btn but"   type="button"  value="viewDocument" name="viewDocument" onclick="javascript:fn_attachments('Doc');">View Documents</button>
<button class="btn but"   type="button"  value="viewResFile" name="viewResFile" onclick="javascript:fn_attachments('Res');">View Response File</button>
</td>
</logic:equal>
<logic:equal value="CD51" name="ahcClaimsForm" property="status">
<td>
<button class="btn but"   type="button"  value="viewResFile" name="viewResFile" onclick="javascript:fn_attachments('Res');">View Response File</button>
</td>
</logic:equal>
</tr>
</table>
		</logic:greaterThan>
		<logic:equal value="0" name="listSize">
  			<table width="100%" border="0"  align="center" style="padding-top:0px;margin:1px auto;">
<tr >
<td align="center" height="50">
<b>No results found</b>
</td>
</tr>
</table>
  		</logic:equal>
  	
</logic:present>

</logic:equal>
</div>
 <html:hidden property="roleId" name="ahcClaimsForm"/>
 <html:hidden property="showScheme" name="ahcClaimsForm" />

 <table><tr><td>&nbsp;</td></tr></table>
 <table><tr><td>&nbsp;</td></tr></table>
 <table><tr><td>&nbsp;</td></tr></table>
<script>
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0);  

}
function viewCaseDtls(ahccId){
	
	var url="/<%=context%>/ahcClaimsAction.do?actionFlag=getCaseDetails&ahcId="+ahcId;
	window.open(url,"claimsPayment",'width=1000,height=800,resizable=yes,top=50,left=100,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
}
function fn_caseSearch()
{
	
	if(document.getElementById("status").value=="" ){
		alert("Please select Case Status");
		focusBox(document.getElementById('status'));
		return;
}	
	
	if(document.getElementById("fromDate").value!="" && document.getElementById("toDate").value==""){
		    alert("Please Select To Date");
			focusBox(document.getElementById('toDate'));
			return;
	}
	if(document.getElementById("toDate").value!="" && document.getElementById("fromDate").value==""){
	  	 alert("Please Select From Date");
			focusBox(document.getElementById('fromDate'));
		return;
}
	if(document.forms[0].showScheme.value=='show'){
   	 if(document.forms[0].schemeType.value==''){
   		 alert("Please Selectany search criteria");
			 return;
	    }
   	 else{
   		 document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=getClaimsPaymentRecrds&status="+document.forms[0].status.value+"&schemeType="+document.forms[0].schemeType.value;
		 document.forms[0].submit();}
	}else{	
	 document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=getClaimsPaymentRecrds&status="+document.forms[0].status.value;
     document.forms[0].submit();
	}
}
function fn_reset()
{
	document.forms[0].status.value='';		
	document.forms[0].ahcId.value='';		
	document.forms[0].patName.value='';	
	document.forms[0].wapNo.value='';
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	
	 document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=getClaimsPaymentRecrds&status=CD93";
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
		if (buttonId == '<bean:message key="EHF.Button.Paynow"/>')
		    displayMsg = '<bean:message key="EHF.Button.Msg.Paynow"/>';
		
		if(confirm(displayMsg)==true){
			fn_payNow(buttonId);
		}
		}
	else{
		
		alert("Please select atleast one case for payment");
		focusBox(document.getElementById('equipNo_id'));
		return;
		}
}
function fn_payNow(buttonId){
	document.getElementById("CD99").style.display="none";
	fn_loadImage();
	document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=updateClaimForPayment&status="+document.forms[0].status.value+"&actionDone="+buttonId;
    document.forms[0].submit();
}
function checkAll(){
	
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
	document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=claimPayments&status=CD385&schemeType="+schemeTypeSel;
	document.forms[0].submit();
}
function exportToExcel() {

	if(document.getElementById("status").value=="" ){
	    	 alert("Please Select Status");
			focusBox(document.getElementById('status'));
		return;
}	
	
	if(document.getElementById("fromDate").value!="" && document.getElementById("toDate").value==""){
		    	 alert("Please Select To Date");
				focusBox(document.getElementById('toDate'));
			return;
	}
	if(document.getElementById("toDate").value!="" && document.getElementById("fromDate").value==""){
	   	 alert("Please Select From Date");
			focusBox(document.getElementById('fromDate'));
		return;
}
	 document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=claimPayments&status="+document.forms[0].status.value+"&genFlag=E";
     document.forms[0].submit();
}
function exportToPdf() {
	if(document.getElementById("status").value=="" ){
	     alert("Please Select Status");
			focusBox(document.getElementById('status'));
		return;
}	
	
	if(document.getElementById("fromDate").value!="" && document.getElementById("toDate").value==""){
		   		 alert("Please Select To Date");
				focusBox(document.getElementById('toDate'));
			return;
	}
	if(document.getElementById("toDate").value!="" && document.getElementById("fromDate").value==""){
	  	 alert("Please Select From Date");
			focusBox(document.getElementById('fromDate'));
		return;
}
	 document.forms[0].action="/<%=context%>/ahcClaimsAction.do?actionFlag=claimPayments&status="+document.forms[0].status.value+"&genFlag=P";
     document.forms[0].submit();
}

function fn_attachments(flag)
{
	var folderFlag = "";
	if(document.forms[0].status.value=="CD94"){
		folderFlag = "OutFol";
	}
	else if(document.forms[0].status.value=="CD51"){
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
</div>
	</html:form>
</body>
</html>