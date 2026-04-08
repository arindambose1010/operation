<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page buffer="25kb"%>
<%@ page autoFlush="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FollowUp Claims Payments</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
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
<%@ include file="/common/includeScrollbar.jsp"%>
<%@ include file="/common/includeCalendar.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript">
var child;
//jquery alerts functions
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function viewCaseDtls(followUpId,caseId,cochlearYN){
	var url;
	if(cochlearYN=='Y')
		url="followUpAction.do?actionFlag=openCochlearFPClaim&backBtn=N&followUpId="+followUpId+"&caseId="+caseId+"&casesForApproval=CEO";
	else
		url="/<%=context%>/followUpAction.do?actionFlag=getFpCaseDetails&FollowUpId="+followUpId+"&caseId="+caseId+"&cochlearYN="+cochlearYN;
	child=window.open(url,"fpClaimsPayment",'width=1000,height=800,resizable=yes,top=50,left=100,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
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
	if(document.getElementById("followUpStatus").value=="" ){
	    var fr = partial(focusBox,document.getElementById('caseStatus'));
		jqueryErrorMsg('FollowUp Payment Mandatory Field',"Please select FollowUp Claim Status",fr);
		return;
}
	var fplStat=document.getElementById("followUpStatus").value;
	var flpArr=fplStat.split('~');
	var cochlearSearch=null;
	if(flpArr[1]!=null && flpArr[1]=='Cochlear')
		cochlearSearch='Y';
	 document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus="+flpArr[0]+"&cochlearSearch="+cochlearSearch;
     document.forms[0].submit();
}
function fn_reset()
{
	document.forms[0].followUpStatus.value='';		
	document.forms[0].caseId.value='';
	document.forms[0].followUpId.value='';		
	document.forms[0].patName.value='';	
	document.forms[0].wapNo.value='';
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	
	 document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus=CD104";
     document.forms[0].submit();
}

function fn_buttonClicked(){
	var buttonId='';
	var radioButs=document.getElementsByClassName("radioActionButs");
	var checkFlag = 'N';
	for (var j=0 ;j<radioButs.length;j++)
		{
			if(radioButs[j].checked)
			  {
			  	checkFlag='Y';
			  	buttonId=radioButs[j].id;
			  	break;
			  }
			 else
				  checkFlag='N';
		}
	if(checkFlag == 'N')
		{
			alert('Please select any radio button to proceed ');
			focusBox(document.getElementById("CD99"));
			return false;
		}
	var displayMsg='';
	var elements = document.getElementsByClassName("selectableCheckbox");	
    checkFlag = 'N';
    
	for (var i = 0; i < elements.length; i++)
		{
			 if(elements[i].checked)
				  {
				  	checkFlag='Y';
				  	break;
				  }
			 else
				  checkFlag='N';
		}
	
	if(buttonId=='CD97')
		{
			if(document.getElementById("sendBackRmrks")!=null)
				{
					if(document.getElementById("sendBackRmrks").value=='' || document.getElementById("sendBackRmrks").length==0
							 || document.getElementById("sendBackRmrks").value==null)
						{
							alert('Send Back Remarks cannot be Empty');
							focusBox(document.getElementById("sendBackRmrks"));
							return false;
						}
					else
						{
							fn_checkValue('sendBackRmrks');
							textCounter(document.getElementById("sendBackRmrks"),'Remarks',3000);
						}
				}
		}
	
	if(checkFlag!=null && checkFlag=='Y')
		{
			if (buttonId == '<bean:message key="EHF.Button.Paynow"/>')
			    displayMsg = '<bean:message key="EHF.Button.Msg.Paynow"/>';
			else if(buttonId=='CD97')
				displayMsg='Do You want to Send Back to CH';
			
			var fr = partial(fn_payNow,buttonId);
		    jqueryConfirmMsg('FollowUp Payment Confirmation',displayMsg,fr);			
		}
	else
		{
			var fr = partial(focusBox,document.getElementById('equipNo_id'));
			if(buttonId=='CD99')
				jqueryErrorMsg('FollowUp Payment Mandatory Field',"Please select atleast one case for payment",fr);
			else if(buttonId=='CD97')
				jqueryErrorMsg('FollowUp Payment Mandatory Field',"Please select atleast one case to Send Back to CH",fr);
			return;
		}
	

}
function fn_payNow(buttonId){
	document.getElementById("CD99").style.display="none";
	fn_loadImage();
	document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=changeFPClaimForCases&followUpStatus="+document.forms[0].followUpStatus.value+"&actionDone="+buttonId;
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
	var totalAmount=0;
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
			$('#data1 tr').each(function(){
				 var amountRow=$(this).find(".claimAmtClass").html();
				 if(amountRow!=null && amountRow !='')
					 {
					 	if($(this).find(":checkbox").prop('checked'))
				 			totalAmount=totalAmount+Number(amountRow);
				 		else	
				 			totalAmount=totalAmount-Number(amountRow);
					 }
				 
			  });
		}	
	
	var count=0;
	for (var i = 0; i < elements.length; i++) 
		{
		  if(elements[i].checked)
			  {
			  	count++;
			  }
		}
	
	document.getElementById('casesAmountTD').innerHTML=totalAmount;
	document.getElementById('casesSelectedTD').innerHTML=count;
}
function fn_Refresh()
{
	var schemeTypeSel = '${schemeTypeSel}';
	var flpStat='${flpUpStatus}';
	if(flpStat=='' ||flpStat==null)
		flpStat='CD140';
	 document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus="+flpStat+"&schemeType="+schemeTypeSel;
		document.forms[0].submit();
	}
	function focusBox(arg) {
		aField = arg;
		setTimeout("aField.focus()", 0);

	}

	function exportToExcel() {

		if(document.getElementById("followUpStatus").value=="" ){
		    var fr = partial(focusBox,document.getElementById('caseStatus'));
			jqueryErrorMsg('FollowUp Payment Mandatory Field',"Please select FollowUp Claim Status",fr);
			return;
	}
		
		 document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus="+document.forms[0].followUpStatus.value+"&genFlag=E";
	     document.forms[0].submit();
	}
	function exportToPdf() {
		if(document.getElementById("followUpStatus").value=="" ){
		    var fr = partial(focusBox,document.getElementById('caseStatus'));
			jqueryErrorMsg('FollowUp Payment Mandatory Field',"Please select FollowUp Claim Status",fr);
			return;
	}
		
		 document.forms[0].action="/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus="
				+ document.forms[0].followUpStatus.value + "&genFlag=P";
		document.forms[0].submit();
	}
	/* function checkMsg()
		{
			var msg='${cochlearMsg}';
			if(msg!=null && msg!='' )
				{
					alert(msg);
					window.opener.close();
				}
		} */
	function submitCEOCochlear(arg,cochlearMsg)
		{
			if(cochlearMsg!=null && cochlearMsg!='')
				alert(cochlearMsg);
			if(child!=null)
				child.close(); 
			
			if(arg==null || arg=='')
				arg='CD104';
			document.forms[0].action="followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus="+arg;
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	
</script>
</head>
<body >
<html:form action="/followUpAction.do" method="post">
<div id="processImagetable" style="width:100%;position:absolute;z-index:60;height:100%;display: none">
        <table border="0" align="center" width="100%">
          <tr>
            <td class="labelheading1 tbcellCss" style="height:500px;text-align:center;vertical-align:center;" colspan="6">
              <span style="font-weight: bold; margin: 10px; font-size: 18px;">
					Payment File generation in process.
					<br>
					Please Do not press Back or Refresh the page...
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
	jqueryInfoMsg('Follow Up Information', '${saveMsg}', fn_Refresh);
</script>
	</c:if>
	<div id="divOnloadShow" style="width:100%;height: 100%">
	<table border="0" width="95%" cellpadding="1" cellspacing="1"
		align="center" style="padding-top: 0px; margin: 0px auto;">
		<tr>
			<td>
			<table class="tbheader">
				<tr align="center">
					<td colspan="8" align="center" class="tbheader"><b>Follow
					Up Payment</b></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table border="1px" width="100%">
				<tr>
					<td>
					<table width="100%">
						<tr>
							<td width="16%" class="labelheading1 tbcellCss"><b>FollowUp
							Case Status</b><font color="red">*</font></td>
							<td width="16%" class="tbcellBorder"><html:select
								name="followUpForm" property="followUpStatus"
								styleId="followUpStatus" style="width:150px;"
								title="Select FollowUp Case Status">
								<option value="">----Select----</option>
								<html:options collection="StatusList" property="ID"
									labelProperty="VALUE" />
							</html:select></td>
							<td width="16%" class="labelheading1 tbcellCss"><b>Case
							Number</b></td>
							<td width="16%" class="tbcellBorder"><html:text
								name="followUpForm" property="caseId" style="width:150px;"
								maxlength="50" title="Enter Case Number" /></td>
							<td width="16%" class="labelheading1 tbcellCss"><b>Follow
							Up Id</b></td>
							<td width="16%" class="tbcellBorder"><html:text
								style="width:150px;" name="followUpForm" property="followUpId"
								title="Enter FollowUp Id" /></td>
						</tr>
						<tr>
							<td width="16%" class="labelheading1 tbcellCss"><b>Patient
							Name</b></td>
							<td width="16%" class="tbcellBorder"><html:text
								name="followUpForm" property="patName" maxlength="50"
								style="width:150px;" title="Enter Patient Name" /></td>
							<td width="16%" class="labelheading1 tbcellCss"><b>From
							Date</b></td>
							<td width="16%" class="tbcellBorder"><html:text
								name="followUpForm" property="fromDate" styleId="fromDate"
								title="Enter From Date"
								onchange="validateDate('From Date',this)"
								onkeydown="validateBackSpace(event)" readonly="true"
								style="width:120px" /></td>
							<td width="16%" class="labelheading1 tbcellCss"><b>To
							Date</b></td>
							<td width="16%" class="tbcellBorder"><html:text
								name="followUpForm" property="toDate" styleId="toDate"
								title="Enter To Date" onchange="validateDate('To Date',this)"
								onkeydown="validateBackSpace(event)" readonly="true"
								style="width:120px" /></td>
						</tr>
						<tr>
							<td width="16%" class="labelheading1 tbcellCss"><b>Card
							Number</b></td>
							<td width="16%" class="tbcellBorder"><html:text
								style="width:150px;" name="followUpForm" property="wapNo"
								title="Enter Card Number" /></td>
								 <logic:equal name="followUpForm"  property="showScheme" value="show">
	                         <td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
		                 <td width="16%" class="tbcellBorder"><html:select property="schemeType" name="followUpForm" style="width:150px;" title="Please select Scheme">
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						</html:select></td>
						</logic:equal>
						</tr>
						<tr>
							<td colspan="8">&nbsp;</td>
						</tr>

						<tr>
							<td colspan="2">&nbsp;</td>
							<td align="center" colspan="2">
							<button class="but" type="button" name="Search" value="Search"
								onclick="javascript:fn_caseSearch()">Search</button>
							<button class="but" type="button" name="Reset" value="Reset"
								onclick="javascript:fn_reset()">Reset</button>
							</td>
							<logic:equal value="Y" name="followUpForm" property="genFlag">
								<logic:present name="followUpForm"
									property="casesForPaymentList">
									<bean:size id="listSize1" name="followUpForm"
										property="casesForPaymentList" />
									<logic:greaterThan value="0" name="listSize1">
										<td align="center" colspan="2"><b>Download As:</b> <img
											src="images/excel1.png"
											onmouseover="this.src='images/excel2.png'"
											onmouseout="this.src='images/excel1.png'"
											onclick="javascript:exportToExcel()" /> <img
											src="images/pdf1.png"
											onmouseover="this.src='images/pdf2.png'"
											onmouseout="this.src='images/pdf1.png'"
											onclick="javascript:exportToPdf()" /></td>
									</logic:greaterThan>
								</logic:present>
							</logic:equal>
						</tr>
					</table>
					</td>
				</tr>

			</table>

			<logic:equal value="Y" name="followUpForm" property="genFlag">
				<logic:present name="followUpForm" property="casesForPaymentList">
					<bean:size id="listSize" name="followUpForm"
						property="casesForPaymentList" />
					<logic:greaterThan value="0" name="listSize">
						<%
							int i = 1;
						%>
						<display:table id="data1" class="displayTable"
							name="followUpForm.casesForPaymentList" pagesize="100"
							decorator="com.ahct.followup.service.FpClaimPaymentDecorator"
							style="width:100%;align:center;margin:15px auto;" export="false"
							requestURI="/followUpAction.do" cellpadding="1" cellspacing="1">
							<display:setProperty name="export.pdf" value="false" />
							<display:setProperty name="export.pdf.filename"
								value="FollowUpClaimPayments.pdf" />
							<display:setProperty name="export.excel.filename"
								value="FollowUpClaimPayments.xls" />
							<c:set var="idVal" value="<%=i %>" />	
							<display:column style="word-break:break-all" value="<%=i++ %>" title="S No" />
							<display:column  title="Case Id" property="ID" />
							<display:column  title="FollowUp Id" media="html"
								property="LEVELID" />
							<display:column style="word-break:break-all" title="FollowUp Type" media="html"
								property="followUpType" />
							<display:column style="word-break:break-all" title="FollowUp Id" media="excel">${data1.LEVELID}</display:column>
							<display:column style="word-break:break-all" title="FollowUp Id" media="pdf">${data1.LEVELID}</display:column>
							<display:column style="word-break:break-all" title="Patient Name" property="VALUE" />
							<display:column style="word-break:break-all" title="Hospital Name" property="LVL" />
							<display:column style="word-break:break-all" title="Hospital Account Name" property="SUBNAME" />
							<display:column title="Card Number" property="UNITID" />
							<display:column title="Claim Amount" property="WFTYPE" class="claimAmtClass" />
							<display:column style="word-break:break-all" title="District" property="EMPNAME" />
							<logic:equal value="CD104" property="followUpStatus"
								name="followUpForm">
								<display:column 
									title="<input type='checkbox' id='equipNo_id' title='select All' name='equipNo' onclick='javascript:checkAll();' >"
									media="html">
									<html:checkbox name="followUpForm" title="select"
										styleClass="selectableCheckbox" property="caseSelected"
										value="${data1.LEVELID}" styleId="caseSelected${idVal}" onclick="javascript:singleBoxClick(this.id,'${data1.WFTYPE}');"/>
								</display:column>
							</logic:equal>
							<logic:equal value="CD107" property="followUpStatus"
								name="followUpForm">
								<display:column
									title="<input type='checkbox' id='equipNo_id' title='select All' name='equipNo' onclick='javascript:checkAll();' >"
									media="html">
									<html:checkbox name="followUpForm" title="select"
										styleClass="selectableCheckbox" property="caseSelected"
										value="${data1.LEVELID}" styleId="caseSelected${idVal}" onclick="javascript:singleBoxClick(this.id,'${data1.WFTYPE}');" />
								</display:column>
							</logic:equal>
                            <logic:equal value="CD108" property="followUpStatus"
								name="followUpForm">
								<display:column
									title="<input type='checkbox' id='equipNo_id' title='select All' name='equipNo' onclick='javascript:checkAll();' >"
									media="html">
									<html:checkbox name="followUpForm" title="select"
										styleClass="selectableCheckbox" property="caseSelected"
										value="${data1.LEVELID}" styleId="caseSelected${idVal}" onclick="javascript:singleBoxClick(this.id,'${data1.WFTYPE}');" />
								</display:column>
							</logic:equal>
							<logic:equal value="CD280" property="followUpStatus"
								name="followUpForm">
								<display:column
									title="<input type='checkbox' id='equipNo_id' title='select All' name='equipNo' onclick='javascript:checkAll();' >"
									media="html">
									<html:checkbox name="followUpForm" title="select"
										styleClass="selectableCheckbox" property="caseSelected"
										value="${data1.LEVELID}" styleId="caseSelected${idVal}" onclick="javascript:singleBoxClick(this.id,'${data1.WFTYPE}');" />
								</display:column>
							</logic:equal>
							<logic:equal value="CD290" property="followUpStatus"
								name="followUpForm">
								<display:column
									title="<input type='checkbox' id='equipNo_id' title='select All' name='equipNo' onclick='javascript:checkAll();' >"
									media="html">
									<html:checkbox name="followUpForm" title="select"
										styleClass="selectableCheckbox" property="caseSelected"
										value="${data1.LEVELID}" styleId="caseSelected${idVal}" onclick="javascript:singleBoxClick(this.id,'${data1.WFTYPE}');" />
								</display:column>
							</logic:equal>
							
						</display:table>
						<c:if test="${fn:length(buttons) gt 0}">
							<table width="100%" align="center" border="0">
								<tr >
									<td width="25%">&nbsp;</td>
									<td width="30%"  class="labelheading1 tbcellCss">
										<b>Total Number Of Cases Selected :</b>
									</td>
									<td class="tbcellBorder" id="casesSelectedTD" width="15%" style="color:red;font-weight:bold">
									0
									</td>
									<td width="30%">&nbsp;</td>	
								</tr>
								<tr  id="amountTR">
									<td width="25%">&nbsp;</td>
									<td  class="labelheading1 tbcellCss" width="30%">
										<b>Total Amount Being Approved :</b>
									</td>
									<td class="tbcellBorder" id="casesAmountTD" width="15%" style="color:red;font-weight:bold">
									0
									</td>
									<td width="30%">&nbsp;</td>	
								</tr>
								<tr style="display:none" id="sendBackRmrksTR">
									<td width="25%">&nbsp;</td>
									<td width="30%" class="labelheading1 tbcellCss">
										<b>Send Back Remarks :</b>
									</td>
									<td class="tbcellBorder"width="45%" colspan="2">
										<textarea style="width:98%" onkeyup="fn_checkValue('sendBackRmrks')" onchange="javascript:textCounter(this,'Remarks',3000)" onkeypress="javascript:textCounter(this,'Remarks',3000)" 
	                                                                name="sendBackRmrks" id="sendBackRmrks" title="Enter Send Back Remarks" ></textarea>
									</td>	
								</tr>
							</table>
						</c:if>	
						<table width="100%" align="center" border="0" style="margin-top:10px">
							<tr align="center">
								<td align="center"><c:forEach var="EHFbutton"
									items="${buttons}" varStatus="status">
									<input type="radio" class="radioActionButs" name="paymentRadio" id="${EHFbutton.ID}" value="${EHFbutton.ID}"
									onchange="javascript:paymentRadioSelect('${EHFbutton.ID}')">&nbsp;<b>${EHFbutton.VALUE}</b>
									<%-- <button class="but" type="button" value="${EHFbutton.VALUE}" 
										name="${EHFbutton.ID}" id="${EHFbutton.ID}"
										onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button> --%>
								</c:forEach>
								<c:if test="${fn:length(buttons) gt 0}">
									<button class="but" type="button" value="Submit" 
										name="submitPaySent" id="submitPaySent"
										onclick="javascript:fn_buttonClicked();">Submit</button>
								</c:if>
								</td>
								<%-- <logic:equal value="CD105" name="followUpForm"
									property="followUpStatus"> --%>
								 <c:if test ="${followUpForm.followUpStatus eq 'CD105' || followUpForm.followUpStatus eq 'CD105~Cochlear'}"> 	
									<td>
									<button class="but" type="button" value="viewDocument"
										name="viewDocument"
										onclick="javascript:fn_attachments('Doc');">View
									Documents</button>
									<button class="but" type="button" value="viewResFile"
										name="viewResFile" onclick="javascript:fn_attachments('Res');">View
									Response File</button>
									</td>
								</c:if>	
								<%-- </logic:equal> 
								<logic:equal value="CD194" name="followUpForm"
									property="followUpStatus">--%>
								<c:if test ="${followUpForm.followUpStatus eq 'CD194' || followUpForm.followUpStatus eq 'CD194~Cochlear'}">
									<td>
									<button class="but" type="button" value="viewResFile"
										name="viewResFile" onclick="javascript:fn_attachments('Res');">View
									Response File</button>
									</td>
								</c:if>	
								<%-- </logic:equal>--%>
							</tr>
						</table>	
					</logic:greaterThan>
					<logic:equal value="0" name="listSize">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							align="center" style="padding-top: 0px; margin: 1px auto;">
							<tr>
								<td align="center" height="50"><b>No results found</b></td>
							</tr>
						</table>
					</logic:equal>
				</logic:present>


			</logic:equal> <html:hidden property="roleId" name="followUpForm" />
			 <html:hidden property="showScheme" name="followUpForm" /></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table></div>
	<script>
	function fn_attachments(flag) {
		var folderFlag = "";
		if (document.forms[0].followUpStatus.value == "CD105") {
			folderFlag = "OutFol";
		} else if (document.forms[0].followUpStatus.value == "CD194") {
			folderFlag = "RecClaimFol";
		}
		var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfFollowUp&caseAttachmentFlag=CP&flag="
				+ flag + "&folderFlag=" + folderFlag;
		parent.parent.parent.claimAttachWindow = window
				.open(url, 'window1',
						'toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
	}
	function fn_loadImage()
	{
		document.getElementById('divOnloadShow').style.display="none";
		document.getElementById('processImagetable').style.display="";

	}
	function paymentRadioSelect(arg)
		{
			if(arg=='CD99')
				{
					document.getElementById('amountTR').style.display="";
					document.getElementById('sendBackRmrksTR').style.display="none";
				}
			else if(arg=='CD97')
				{
					document.getElementById('amountTR').style.display="none";
					document.getElementById('sendBackRmrksTR').style.display="";
				}
		}
	function singleBoxClick(id,amount)
		{
			var newAmount=0,totalAmount=0;
			
			var totAmountStr=document.getElementById('casesAmountTD').innerHTML;
			if(amount==null || amount=='')
				newAmount=0;
			else
				newAmount=Number(amount);
			
			if(totAmountStr==null || totAmountStr=='')
				totalAmount=0;
			else
				totalAmount=Number(totAmountStr);
			
			var newId=document.getElementById(id);
			
			var elements = document.getElementsByClassName("selectableCheckbox");	
		    var count = 0;
		    
			for (var i = 0; i < elements.length; i++) 
				{
				  if(elements[i].checked)
					  {
					  	count++;
					  }
				}
			if(newId.checked==true)
				totalAmount=totalAmount+newAmount;
			else
				totalAmount=totalAmount-newAmount;
			
			document.getElementById('casesAmountTD').innerHTML=totalAmount;
			document.getElementById('casesSelectedTD').innerHTML=count;
		}
	function fn_checkValue(id)
		{
			var specialCharName=/^[0-9a-zA-Z .,&()\/\n]*$/;
			var remarks=document.getElementById(id).value;
			if(remarks.length>0)
				{
					if(remarks.charAt(0)==" ")
						{
							alert("Remarks cannot start with a space");
							focusBox(document.getElementById(id));
							return false;
						}
					if(!specialCharName.test(remarks))
						{
							alert("Remarks cannot have special characters ");
							focusBox(document.getElementById(id));
							return false;
						}
				}	
		}	
	
	function textCounter( field, fieldName, maxlimit )
	{
		var specialCharName=/^[0-9a-zA-Z .,&()\/\n]*$/;
		if(field.id=="flagRemarks")
			{
				var remarks=document.getElementById(field.id).value;
				if(remarks.length>0)
					{
						if(remarks.charAt(0)==" ")
							{
								alert("Remarks cannot start with a space");
								focusBox(document.getElementById(field.id));
							}
						if(!specialCharName.test(remarks))
							{
								alert("Remarks cannot have special characters and numbers");
								focusBox(document.getElementById(field.id));
							}
				var count=0;
						for(var i=0;i<remarks.length;i++)
							{
								if(remarks.charAt(i)==" ")
									{
										count++;
									}
							}
						if(count==remarks.length)
							{
								alert("Remarks cannot have only blanks");
								focusBox(document.getElementById(field.id));
							}
					}
			}	
		
		
		if ( field.value.length > maxlimit )
		{
		   field.value = field.value.substring( 0, maxlimit );
		   alert(fieldName+' length cannot be more than '+ maxlimit+' characters');
		   var fieldValue1=field.value;
		   fieldValue1.trim().substring(0,2999);
		   return false;
		 }
			return true;
	}
</script>
</html:form>
</body>
</html>