<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
     
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel Doctor Payments</title>
<script src="js/jquery-1.9.1.js"></script>
    <%@ include file="/common/include.jsp"%> 
    <%@ include file="/common/includeCalendar.jsp"%> 
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript">

var checkedValue="[";
var amount= parseFloat("0");

$(function() { 
    $( "#selectDate" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
      		showOn: "button", 
            buttonImage: "images/calend.gif", 
            buttonImageOnly: true,
            dateFormat: "dd-mm-yy",
            minDate:new Date(2012, 03, 01),
            maxDate:new Date()
    }); 
});
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

function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";

}

function validateMaxlength(input,e)
{
	var fieldValue=input.value;
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(fieldValue.trim().length>3000)
		{
			input.value=fieldValue.trim().substr(0,3000);
			jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 3000 words.');	
	
			if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40)
				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
				{
				e.returnValue=true;
				}
			else
				{
				e.returnValue=false;
			 	}
		}
	
}

function goToHomePage()
{
	
	document.forms[0].action = "panelDocPay.do?actionFlag=getTDSCases";  
	document.forms[0].submit();
}

function fn_reset()
{
	
}

function resetList(arg)
{
	var obj =  arg;
	if (obj == null) return;
	if (obj.options == null) return;
	while (obj.options.length > 1) {
		obj.remove(1);
	}
}



function validateDate(arg1,input)
{
	
	var entered = input.value;
	
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	
	if(isNaN(byr))
	{
		
		input.value="";
		jqueryShowContentMsg('Date Validation',"Please Select "+arg1);
		return false;
	}
}

function popitup(url) {
	
	newwindow=window.open(url,'PanelDoctor','width=1000, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
	if (window.focus) {newwindow.focus();}
	return false;
}


function popUp(docId,docName)
{
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	var type=document.panelDocPayForm.dispType.value;
	
	if(type == "01")
	{
	popitup("panelDocPay.do?actionFlag=getAllCasesStatus&docId="+docId+"&docName="+docName+"&dispType="+type+"&month="+document.panelDocPayForm.month.value+"&year="+document.panelDocPayForm.year.value+"&pmntType="+pmntStatusLabel);
	}
	else if(type == "02")
	{
	popitup("panelDocPay.do?actionFlag=getAllCasesStatus&docId="+docId+"&docName="+docName+"&dispType="+type+"&fromDate="+document.panelDocPayForm.fromDate.value+"&toDate="+document.panelDocPayForm.toDate.value+"&pmntType="+pmntStatusLabel);
		
	}
}
function fn_allValidations()
{
	 
	 return true;
}


function fn_addCheckValues(arg,amt){
	var aInputs = document.getElementsByName('checkId');
	aInputs[0].checked=false;
	if(!checkedValue.match(arg))
		{
	checkedValue = checkedValue+'~'+arg;
	amount=amount+parseFloat(amt);
		}
	else
		{
		checkedValue = checkedValue.replace('~'+arg, '');
		amount = amount-parseInt(amt);
		
		}
	 document.panelDocPayForm.amount.value=amount;
	
}

function checkAll(arg){
	var chckAmt=0;
	var chckDoc='';
	var spltArg;
	 var aInputs = document.getElementsByName('checkId');
	    for (var i=1;i<=aInputs.length;i++) {
	    	
		        spltArg=aInputs[i].value.split('@');
        		chckDoc=spltArg[0];
        		chckAmt=spltArg[1];
        	
	        	if(arg.checked && !aInputs[i].checked)
	    		{
	        		aInputs[i].checked = arg.checked;
	        		checkedValue = checkedValue+'~'+chckDoc;
	        		amount=amount+parseFloat(chckAmt);
	    		}
	    	else if(!arg.checked && aInputs[i].checked)
	    		{
	    		aInputs[i].checked = arg.checked;
	    	    checkedValue = checkedValue.replace('~'+chckDoc, '');
	    		amount = amount-parseFloat(chckAmt);
	    		} 
        		
	        	 document.panelDocPayForm.amount.value=amount;
	        }
	    
}

function fn_submitOnConfirm()
{
	document.forms[0].action="panelDocPay.do?actionFlag=UpdateTdsClaim&checkedCaseValues="+checkedValue+"&caseStatus="+document.panelDocPayForm.caseStatus.value;
    document.forms[0].submit();
}
function fn_generateFile()
{
	var checkFlag = 'N';
	 var aInputs = document.getElementsByName('checkId');
	for (var i = 0; i < aInputs.length; i++) {
	  if(aInputs[i].checked)
		  {checkFlag='Y';
		 
		  break;}
	  else{
		  checkFlag='N';
		 
	  }
	}
	
	if(checkFlag!=null && checkFlag=='Y'){
	jqueryConfirmMsg('Claim Payment Confirmation','Do you want to proceed?',fn_submitOnConfirm);
	
	}
	else{
		var fr = partial(focusBox,document.getElementsByName('checkId'));
		jqueryErrorMsg('Panel Doctor Payment Mandatory Field',"Please select atleast one case for payment",fr);
		return;
	}
}


function getTDSCases(){
	fn_loadImage();
	
	 if(document.panelDocPayForm.scheme.value == null || document.panelDocPayForm.scheme.value == ""){
		 document.forms[0].action = "panelDocPay.do?actionFlag=getTDSCases";  
	 }
	 else{
		 document.forms[0].action = "panelDocPay.do?actionFlag=getTDSCases&scheme="+document.panelDocPayForm.scheme.value;  
	 }
		 
  		 document.forms[0].submit();
		
	
}
</script>
</head>

<body style="height:100%" >
<center>
<html:form action="/panelDocPay.do" method="post" >
<html:hidden name="panelDocPayForm" property="result" />
<html:hidden name="panelDocPayForm" property="totalAmt" />
<html:hidden name="panelDocPayForm" property="actionSelect" />
<html:hidden name="panelDocPayForm" property="caseStatus" />
<table width="95%" style="margin:0 auto"  border="1"><tr><td>
<br>
<table class="tbheader" border=1 >
<tr><th><b>Panel Doctor TDS Payment</b></th></tr>
</table>
<logic:equal value="CD203" name="panelDocPayForm" property="userType" >
<table class="contentTable">
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td style="WIDTH: 5em"></td>
<logic:notEqual value="CD203" name="panelDocPayForm" property="userType" >
<td style="WIDTH: 20em"></td>
</logic:notEqual>
<%-- <td  class="labelheading1 tbcellCss"><b>Payment Status : &nbsp;</b></td>
<td class="tbcellBorder" >
<html:select name="panelDocPayForm" property="tdsStatus" style="WIDTH: 200px" onchange="javascript:fn_pmntType()">
 <logic:notEmpty name="panelDocPayForm" property="tdsStatusList">
<html:options collection="tdsStatusList" property="ID" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select>
</td> --%>

<td style="WIDTH: 4em"></td>
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 12em"></td>
<td class="labelheading1 tbcellCss" style="WIDTH: 100px"><b>Scheme : &nbsp;</b></td>
<td class="tbcellBorder" >
<html:select name="panelDocPayForm" property="scheme" style="WIDTH: 150px">
<html:option value="CD201" key="CD201">Andhra Pradesh</html:option>
<html:option value="CD202" key="CD202">Telengana</html:option>
 <%-- <logic:notEmpty name="panelDocPayForm" property="schemeList">
<html:options collection="schemeStatusList" property="IDVAL" labelProperty="VALUE"/>
</logic:notEmpty> --%>
</html:select>
</td>


</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
</table>
<table class="contentTable">
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 25em"></td><td><button class="but"  type="button" id="getcases" onclick="javascript:getTDSCases()">Get Cases</button>
</td>
<td style="WIDTH: 2em"></td><td><button class="but"  type="button" id="reset" onclick="javascript:Reset()">Reset</button>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
</table>
</td></tr>
</table>
</logic:equal>
 <div id="processImagetable" style="top:60%;width:90%;position:absolute;z-index:60;height:100%;display:none">
   <table border="0" align="center" width="90%" style="height:400" >
      <tr>
         <td>
           <div id="processImage" align="center">
             <img src="images/Progress.gif" width="100"
                     height="100" border="0"></img>
            </div>
          </td>
        </tr>
     </table>
</div>
<logic:equal value="List Not Found" name="panelDocPayForm" property="flag">
<table border='0' width=95% align="center"><tr><td>
</td></tr>
<tr><td>
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center">
<tr align="center">
<td>
No Records Found
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</td></tr></table>
</logic:equal>
<logic:equal value="List Found" name="panelDocPayForm" property="flag" >
 <logic:notEmpty name="panelDocPayForm"  property="panelDocList"> 
<table border='0' width=95% align="center"><tr><td>
 </td></tr>
</table>

 <table border='1' width=95% align="center" >
 
 <tr><td>
<%int i = 1;%>
<display:table  id="panelDocPayVO"  name="panelDocPayForm.panelDocList" pagesize="100" style="width:100%;align:center;border:2;rowspan:5" export="false" requestURI="/panelDocPay.do"  cellpadding="2" cellspacing="2" decorator="com.ahct.panelDoctor.util.PanelDocDecorator">
<display:column value="<%=i++ %>" title="S No"  style="text-align:center;width:5%"  />
<display:column   property="CASE_ID" title="TDS Payment Id" />
<display:column   property="DOC_NAME" title="Panel Doctor" />
<display:column  property="AMOUNT" title="Total Amount" style="text-align:right;width:15%"/>
<display:column  property="ACCOUNTNO" title="Account No" style="text-align:center" />
<display:column property="TDSID" title="<input type='checkbox' name='checkId' id='check_id' title='select All' onclick='javascript:checkAll(this);' />" media="html"  style="text-align:center;width:3%">
</display:column>
</display:table>
<center>
<table border='0' width="30%" align="center">
<tr><td>&nbsp;</td></tr>


<tr>
<td style="WIDTH: 4em"></td>
<td class="labelheading1 tbcellCss" style="WIDTH: 14em"><b>Sum of amount :</b></td>
<td  class="labelheading1 tbcellCss Label5" style="WIDTH: 2em" ><b><html:text name="panelDocPayForm"  property="amount" readonly="true" /></b>
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<%-- <tr>
<td class="labelheading1 tbcellCss" ><b>Remarks : </b></td>
<td  class="labelheading1 tbcellCss Label5" style="WIDTH: 2em" ><b><html:textarea  name="panelDocPayForm"  property="remarks" style="WIDTH: 190px"  onkeypress="validateMaxlength(this,event);"> </html:textarea></b>
</td>
</tr> --%>
</table> 
</center>

<logic:equal value="PayNow" name="panelDocPayForm" property="rejId" >
<table class="contentTable">
<tr><td>&nbsp;</td></tr>
<tr>
<td style="WIDTH: 8em"></td>

<td style="WIDTH: 25em"></td><td ><button class="but"  type="button" id="actionSubmit" onclick="javascript:fn_generateFile()">Pay Now</button></td>
</tr>
</table>
</logic:equal>
</td></tr>
</table> 
</logic:notEmpty> 
</logic:equal>
<c:if test="${saveMsg ne null}">
<script>
jqueryInfoMsg('Panel Doctor TDS Information','${saveMsg}',goToHomePage);
</script>
</c:if>
</html:form>
</center>
</body>
</html>