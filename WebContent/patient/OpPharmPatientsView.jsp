<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8" import="java.util.ArrayList,
                              java.util.Iterator,
                              java.util.List,com.ahct.patient.vo.PatientVO" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
     
     <%@ include file="/common/include.jsp"%>

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
 
<html>
<head>
<title >OP Registered Patients View</title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
 input:disabled, select:disabled{
background-color:#ddd;
}
 </style>
<script type="text/javascript">
$("document").ready(function(){
	if(document.getElementById("state").value!=null && document.getElementById("state").value!="-1"){
		if(document.getElementById("state").value=="S35"){
			document.getElementById("stateType").disabled=false;
			if(document.getElementById("stateType").value!=null && document.getElementById("stateType").value!="-1")
			document.getElementById("district").disabled=false;
			else
				document.getElementById("district").disabled=true;
		}
		else{
			document.getElementById("stateType").disabled=true;
			document.getElementById("stateType").value="-1";
			document.getElementById("district").disabled=false;
		}
	}
	else{
		document.getElementById("stateType").disabled=true;
		document.getElementById("district").disabled=true;
	}
});
//parse a date in dd-mm-yyyy format
function parseDate(input) {
  var parts = input.split('-');
  // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
  return new Date(parts[2], parts[1]-1, parts[0]); // Note: months are 0-based
}
function fnSearch()
{
	if( document.forms[0].patientName.value==""  &&
            document.forms[0].cardNo.value==""  &&
            document.forms[0].state.value=="-1" &&
            document.forms[0].stateType.value=="-1" &&
            document.forms[0].district.value=="-1" && 
	         document.forms[0].fromDate.value=="" && 
            document.forms[0].toDate.value=="" &&
		     document.forms[0].patientNo.value=="" &&
			 document.forms[0].caseId.value=="" &&
			 document.forms[0].hospitalId.value=="-1")
            {  
				jqueryAlertMsg('Search Criteria Validation','Please enter any search criteria! ');
                return false;
            }
	var patname=document.forms[0].patientName.value;
	if(patname != '' && document.forms[0].district.value=="-1")
	{
		jqueryAlertMsg('Search Criteria Validation',"Please select District also to continue with Patient Name search");
		return false;
	}
	
	if(document.forms[0].stateType.value!="-1" && document.forms[0].district.value=="-1")
	{
	jqueryAlertMsg('Search Criteria Validation',"Please select District also to continue with district type search");
	return false;
	}
	
	var regFrom=document.forms[0].fromDate.value;
	var regTo=document.forms[0].toDate.value;
	if((regFrom.length > 0 && regTo.length == 0) || (regFrom.length == 0 && regTo.length > 0) )
		{
		jqueryAlertMsg('Search Criteria Validation','Please select none or both Registration From and Registration To dates');
		return false;
		}
	else if(regFrom.length > 0 && regTo.length > 0)
		{     
			if(fnCompareDates(regFrom, regTo) && fnMonthDiff(regFrom, regTo))
				{
					search="true"; 
				}
			else
				{
					return false;      
				}
		} 
	if(document.forms[0].stateType.value!="-1" && document.forms[0].stateType.value=="O"){
		if(parseDate(regFrom)>=parseDate("${bifurcationDate}") || parseDate(regTo)>=parseDate("${bifurcationDate}")){
			alert("Please select from date and to date before "+'${bifurcationDate}'+" for district type Old");
		return false;
		}
		}
		else if(document.forms[0].stateType.value!="-1" && document.forms[0].stateType.value=="N"){
			if(parseDate(regFrom)<parseDate("${bifurcationDate}") || parseDate(regTo)<parseDate("${bifurcationDate}")){
				alert("Please select from date and to date after "+"${bifurcationDate}"+" for district type new");
			return false;
			}
		}
	
	document.getElementById("district").disabled=false;
	document.getElementById("currCaseId").value=document.forms[0].caseId.value;
	document.getElementById("currPatId").value=document.forms[0].patientNo.value;
	document.getElementById("currPatName").value=document.forms[0].patientName.value;
	document.getElementById("currHealthCardNo").value=document.forms[0].cardNo.value;
	document.getElementById("currStateId").value=document.forms[0].state.value;
	document.getElementById("currDistrictId").value=document.forms[0].district.value;
	document.getElementById("currFromDate").value=document.forms[0].fromDate.value;
	document.getElementById("currToDate").value=document.forms[0].toDate.value;
	document.getElementById("currHospId").value=document.forms[0].hospitalId.value;
	fn_pagination(0,'button');
	
}
function openCase(patientId,caseId)
{
		fn_loadImage();
	   document.forms[0].action="./patientDetails.do?actionFlag=pharmaCasePrintForm&pharmaCases=${pharmaCases}&apprPharma=${apprPharma}&patientId="+patientId+"&caseId="+caseId;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
}
function fnCompareDates(FromDate,ToDate)
{
    var FromDateVal;
    var ToDateVal;
    var k = FromDate.indexOf("-");
    var t = FromDate.indexOf("-",3);  
    FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length);
  	k = ToDate.indexOf("-");
    t = ToDate.indexOf("-",3);
    ToDateVal = ToDate.substr(k+1,t-k-1)+"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length);
	 if (Date.parse(FromDateVal) > Date.parse(ToDateVal))
         {
		 jqueryAlertMsg('Search Criteria Validation',"From Date should be less than To Date");
     		return false; 
          } 
    else
      return true;       
}
function fnMonthDiff(FromDate,ToDate)
{
    var FromDateVal;
	var ToDateVal;            
	var k = FromDate.indexOf("-");
	var t = FromDate.indexOf("-",3);   
	FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,t-1); 

	var fromYear = parseInt(FromDate.substr(t+1,t-1));			
	var fromMon = Number(FromDate.substr(k+1,t-k-1));
	var fromDt=Number(FromDate.substr(0,k));	
	k = ToDate.indexOf("-");
	t = ToDate.indexOf("-",3);
	ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,t-1);

	var toYear = Number(ToDate.substr(t+1,t-1));
	var toMon = Number(ToDate.substr(k+1,t-k-1));
	var toDt=Number(ToDate.substr(0,k));
	if(toYear == fromYear)
	{				
		if((toDt - fromDt >=0 && toMon - fromMon <= 2) || (toDt - fromDt <0 && toMon - fromMon <= 3))
		{
			return true;
		}
		else 
		{   
			jqueryAlertMsg('Search Criteria Validation','Can not select more than 3 months difference');
			return false;
		}
	}
	else if(toYear > fromYear)
	{
		if((toDt - fromDt >=0 &&  fromMon - toMon >=10) || (toDt - fromDt <0 && fromMon - toMon >= 9))
		{
			return true;
		}
		else 
		{  
			jqueryAlertMsg('Search Criteria Validation','Can not select more than 3 months difference');
			return false;
		}
	}
	else
	{
		jqueryAlertMsg('Search Criteria Validation','Please select valid From and To Dates');
		return false;
	}
}
function fn_pagination(pageNo,actionType)
{
	var patientScheme='${patientScheme}';
	document.forms[0].advSearch.value="true";
	var stateType=document.getElementById("stateType").value;
	var url="./patientDetails.do?actionFlag=getPharmaOPCases&pharmaCases=${pharmaCases}&apprPharma=${apprPharma}&actionType="+actionType+"&pageNo="+pageNo+"&patientScheme="+patientScheme+"stateType="+stateType;
	document.forms[0].action=url;
	 //document.forms[0].method="post";
	 document.forms[0].submit(); 
}

function validateDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		jqueryErrorMsg('Date Validation',"Please Select "+arg1);
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
		jqueryErrorMsg('Date Validation',arg1+" should not be greater than Today's Date");
		}
	}
}
function resetSearch()
{
	document.getElementById("patientNo").value="";
	document.getElementById("patientName").value="";
	document.getElementById("cardNo").value="";
	//$('#district-input').val('---select---');
	document.getElementById("stateType").value="-1";
	document.getElementById("district").options.length=1;
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	document.getElementById("hospitalId").value="-1";
	//$('#hospitalId-input').val('---select---'); 

}
function validateHealthCard(arg1,input)
{
	var a=input.value;
	if(a.trim()=="")
	{
	input.value="";
	jqueryErrorMsg('Health Card Validation',"Please Enter "+arg1);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Health Card Validation',arg1+ " should not start with space");
		return false;
		}
	var regAlphaNum=/^[0-9a-zA-Z\/\ ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Health Card Validation',"Only alphanumeric are allowed for "+arg1);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
}
function  refreshParentPage()
{
window.close();
}
function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage()  
{
	var returnMsg='${returnMsg}';
	if(returnMsg!=null && returnMsg!='' && returnMsg!=' ' )
		{
			document.getElementById('caseId').value='';
			alert(returnMsg);
			
		}
	document.getElementById('processImagetable').style.display="none";  
}
function viewPreviousPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var action='link';
	var i=0;
	if(minPageNo>0)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+')">Previous</a>&nbsp;&nbsp;';
	}
	else
	{
		minPageNo=minPageNo+1;
	}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+',\''+action+'\')"><b>'+i+'</b></a>&nbsp;&nbsp;';
		}
		
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')">Next</a>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var action='link';
	var i=0;
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+')">Previous</a>&nbsp;&nbsp;';
	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+',\''+action+'\')"><b>'+i+'</b></a>&nbsp;&nbsp;';
		}
		
	}
	if(i<noOfPages)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')">Next</a>';
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function enableStateType(){
	document.getElementById("stateType").value="-1";
	document.getElementById("district").value="-1";
	
	if(document.getElementById("state").value!=null && document.getElementById("state").value!="-1"){
		if(document.getElementById("state").value=="S35"){
			document.getElementById("stateType").disabled=false;
			document.getElementById("district").disabled=true;
		}
		else{
			document.getElementById("stateType").disabled=true;
			document.getElementById("stateType").value="-1";
			stateSelected();
		}
	}
}
function stateSelected()
{
	var state=null;
	var lStrHdrId='LH6';
	state=document.getElementById("state").value;
	var stateType = document.getElementById("stateType").value;
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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var districtList = resultArray.split(","); 
            		if(districtList.length>0)
            		{  
            			document.getElementById("district").disabled=false;
            			document.forms[0].district.options.length=0;
            			document.forms[0].district.options[0]=new Option("---select---","-1");
            			for(var i = 0; i<districtList.length;i++)
            			{	
            				var arr=districtList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					document.forms[0].district.options[i+1] =new Option(val1,val2);
            				}
            			}
            		}
            	}
            }
        }
    }
    url = "./patientDetails.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state+"&stateType="+stateType;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
</script>
</head>
<body onload="fn_removeLoadingImage()">
<form action="/patientDetails.do" method="post">
<table width="95%" style="margin:0 auto"><tr><td>
<table class="tbheader">
<tr><th><b>OP Registered Patients View</b></th></tr>
</table>
<table width="100%">
<tr><td>
<table width="100%" class="tb">
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>Case No</b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="caseId" maxlength="12" styleId="caseId" onchange="validateSpaces('Case No',this)" title="Please enter complete case number Eg: If case number is CASE/EHS100/1234 ,Please enter 1234 as case number" style="WIDTH: 10em;border:0;box-shadow:none;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientNo"/></b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="patientNo" maxlength="12" styleId="patientNo" onchange="validateNumber('Patient No',this)" title="Enter Patient No" style="WIDTH: 10em;border:0;box-shadow:none;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientName"/></b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="patientName" maxlength="100" styleId="patientName" onchange="checkAlphaSpace('patientName','Patient Name')" title="Enter Patient Name" style="WIDTH: 10em;border:0;box-shadow:none;"/></td>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HealthCardNo"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="cardNo" styleId="cardNo" maxlength="21" title="Enter Health Card No" onchange="validateHealthCard('Health Card No',this)" style="WIDTH: 10em;border:0;box-shadow:none;"/></td>

<td class="labelheading1 tbcellCss"><b>State</b></td>
<td class="tbcellBorder"><html:select name="patientForm" property="state" styleId="state" title="Select State" onmouseover="addTooltip('state')" onchange="enableStateType()" style="WIDTH: 10em;border:0;">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="stateList" labelProperty="VALUE"/>
    </html:select></td>

<td class="labelheading1 tbcellCss"><b>District Type</b></td>
<td colspan="" class="tbcellBorder"><html:select name="patientForm" property="stateType" styleId="stateType" title="Select District Type" onmouseover="addTooltip('state')" onchange="stateSelected()" style="WIDTH: 10em;" disabled="true">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:option value="O">Old Districts </html:option>
			<html:option value="N">New Districts </html:option>
    </html:select></td> 
    
</tr>
<tr>
    <td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td>
<td class="tbcellBorder"><html:select name="patientForm" property="district" styleId="district" title="Select District" onmouseover="addTooltip('district')" style="WIDTH: 10em;border:0;" disabled="true">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            <html:options property="ID" collection="districtList" labelProperty="VALUE"/>
</html:select></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Registration"/></b>: <b><fmt:message key="EHF.FromDate"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm" property="fromDate" styleId="fromDate" title="Enter From Date" onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 8em;border:0;box-shadow:none;"/></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ToDate"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm" property="toDate" styleId="toDate" title="Enter To Date" onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 8em;border:0;box-shadow:none;"/></td>

<logic:notEmpty name="hospList">
<td class="labelheading1 tbcellCss"><b>Hospitals</b></td>
<td class="tbcellBorder">
	<html:select name="patientForm" property="hospitalId" styleId="hospitalId" style="WIDTH: 10em;border:0;" title="Select Hospital" onmouseover="addTooltip('hospitalId')">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="hospList" labelProperty="VALUE"/>
    </html:select></td>
</logic:notEmpty>
<logic:empty name="hospList">
<td class="labelheading1 tbcellCss" style="display:none"><b>Hospitals</b></td>
<td class="tbcellBorder" style="display:none">
	<html:select name="patientForm" property="hospitalId" styleId="hospitalId" style="WIDTH: 10em;border:0;" title="Select Hospital" onmouseover="addTooltip('hospitalId')">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
</logic:empty>
</tr>
<tr>

<td colspan="3" align="right">  <button class="but"  type="button" onclick="javascript:fnSearch()">Search</button></td>
<td colspan="3"> <button class="but"  type="button" onclick="resetSearch()">Reset</button></td>
</tr>
</table>
</td></tr>
</table>
<br>
<html:hidden name="patientForm" property="startIndex" styleId="startIndex"/>

<logic:notEmpty name="registeredPatientsList">
<table  width="100%" class="tb">
<tr style="height:2em">
<td align="center" colspan="3" id="pageNoDisplay">
<%
int noOfPages = ((Integer) request.getAttribute("noOfPages")).intValue();
int selectedPage = ((Integer) request.getAttribute("selectedPage")).intValue();
int a=selectedPage/10;
int pageNo=0;
int minVal=(a*10);
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages+1;
}
if(minVal>=10)
	{
		%>
		<a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>)">Previous</a>&nbsp;
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
			<b><%=pageNo%></b>
			<%
		}
		else
		{
			%>
			<a href="javascript:fn_pagination(<%=pageNo%>,'link')"><b><%=pageNo%></b></a>&nbsp;
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		<a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>)">Next</a>
		<%
	}
%>
<td colspan="5">
	<b>&nbsp;&nbsp;&nbsp;Displaying <bean:write name="recordsList"/></b>&nbsp;&nbsp;&nbsp;&nbsp;
	<b>Total no of records: <bean:write name="totalRecords" scope="request"/></b>&nbsp;&nbsp;&nbsp;
</td>
</td>
</tr>
<tr >
<th class="tbheader1" style="width:15%">Case No</th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.PatientNo"/></th>
<th class="tbheader1" style="width:30%"><fmt:message key="EHF.PatientName"/></th>
<th class="tbheader1" style="width:15%"><fmt:message key="EHF.Employee/PensionerCardNo"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.District"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.Gender"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.RegistrationDate"/></th>
</tr>
	<logic:iterate name="registeredPatientsList" id="patientVO">
<tr>
<td class="tbcellBorder" align="center"><a href="javascript:openCase('<bean:write name="patientVO" property="patientId"/>','<bean:write name="patientVO" property="caseId"/>')" title="Click On Case No to view Case Details"><b><bean:write name="patientVO" property="caseId"/></b></a></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="patientId"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="firstName"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="rationCard"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="districtCode"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="gender"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="caseRegDate"/></td>
</tr>
</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="registeredPatientsList">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >
<td align="center" height="50">
<b><bean:write name="patientForm" property="errMsg"/></b>
</td>
</tr>
</table>
</logic:empty>
<input type="hidden" name="advSearch" value="false"/>
<html:hidden name="patientForm" property="currCaseId" styleId="currCaseId"/>  
<html:hidden name="patientForm" property="currPatId" styleId="currPatId"/>
<html:hidden name="patientForm" property="currPatName" styleId="currPatName"/>  
<html:hidden name="patientForm" property="currHealthCardNo" styleId="currHealthCardNo"/>
<html:hidden name="patientForm" property="currStateId" styleId="currStateId"/>   
<html:hidden name="patientForm" property="currDistrictId" styleId="currDistrictId"/>  
<html:hidden name="patientForm" property="currFromDate" styleId="currFromDate"/>  
<html:hidden name="patientForm" property="currToDate"  styleId="currToDate"/> 
<html:hidden name="patientForm" property="currHospId"  styleId="currHospId"/>  
<%-- <html:hidden name="patientForm" property="state" styleId="state" value="S35"/> --%>
<html:hidden name="patientForm" property="noOfPages" styleId="noOfPages"/>
</td></tr></table>
</form>
</body>
<div id="processImagetable" style="top:50%;width:100%;position:absolute;z-index:60;height:100%">
   <table border="0" align="center" width="100%" style="height:400" >
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
</html>
</fmt:bundle>