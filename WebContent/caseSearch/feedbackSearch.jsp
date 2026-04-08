 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<fmt:setLocale value='${langID}'/> 
<fmt:bundle basename="ApplicationResources">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%@ include file="/common/includeScrollbar.jsp"%> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>

<title>Feedback Search</title>

<script type="text/javascript">

function fn_casereset()
	{
		document.getElementById('feedbackId').value="";
		document.getElementById('feedbackId').focus();
	}


function fn_reset()
	{
	document.getElementById("feedbackId").value=""; 
	document.getElementById("fromDate").value="" ;
	document.getElementById("toDate").value="" ;	
	document.forms[0].action="/Operations/casesSearch.do?actionFlag=feedbackSearch";
    document.forms[0].submit();
    document.getElementById("feedbackId").value="";
	}
function checkvalidId(arg1){
		
		var reg=/^[0-9]+$/;
		if(reg.test(document.getElementById(arg1).value)==false){
                if(arg1=="feedbackId"){
			jqueryAlertMsg('FeedbackId check','feedbackId should accept only digits',fn_casereset);
			
                }
                else
                    {
                    jqueryAlertMsg('IssueId check','IssueId should accept only digits',fn_issuereset);
                    
                    }
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
			alert("Please Select "+arg1);
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
			alert(arg1+" should not be greater than Today's Date");
			}
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
function fn_caseSearch()
{
	var msg ='';
	var fr ='';

		if(document.getElementById("feedbackId").value=='' &&
				document.getElementById("fromDate").value=='' &&
				document.getElementById("toDate").value=='' 
			 )
					{
					jqueryAlertMsg('Cases Search','Please select any search criteria');
					return;
					}
				else if ((document.getElementById("fromDate").value=='' && document.getElementById("toDate").value!='' )||(document.getElementById("fromDate").value!='' && document.getElementById("toDate").value===''))
					{ 
					jqueryAlertMsg('Cases Search','Please select from date and to date');
					return;
					}
				else
					{
				
				 document.forms[0].showPage.value ="1";
				 document.forms[0].action='/<%=context%>/casesSearch.do?actionFlag=feedbackSearch&searchFlag=Y&feedbackId='+document.getElementById("feedbackId").value+'&fromdate='+document.getElementById("fromDate").value+'&todate='+document.getElementById("toDate").value;
			     document.forms[0].submit();
		
					}
	}
	function showinSetsOf(num)
	{
	 
	 document.forms[0].action='/<%=context%>/casesSearch.do?actionFlag=feedbackSearch&rowsperpage='+num+'&feedbackId='+document.getElementById("feedbackId").value+'&fromdate='+document.getElementById("fromDate").value+'&todate='+document.getElementById("toDate").value;
     document.forms[0].submit();
	
	}
	function fn_issueWorklist()
	    {
		fn_loadImage();
	  document.getElementById("middleFrame").src='/<%=context%>/casesSearch.do?actionFlag=issueWorklist'; 
		}

	function showPagination(num,rows)
   {
	document.forms[0].action='/<%=context%>/casesSearch.do?actionFlag=feedbackSearch&showpage='+num+'&rowsperpage='+rows+'&fromdate='+document.getElementById("fromDate").value+'&todate='+document.getElementById("toDate").value;
    document.forms[0].submit();
	}	
	function fn_feedbackid(FEEDBACKID)
	{
	
	var url='/<%=context%>/viewFeedbackAction.do?actionFlag=feedbackdetails&viewFeedbackid='+FEEDBACKID;
	childWindow= window.open(url, '_blank','toolbar=no,resizable=no,scrollbars=yes,width=500, height=500, top=100,left=50');
}
</script>
</head>

<body >
<html:form  method="post"  action="/casesSearch.do" > 
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr align="center">
<td  colspan="8" align="center"  class="tbheader" ><b>FeedBack Search</b></td></tr>
</table>
<table border="0" width="100%" align="center">
<tr align="center">
<td width="25%" class="labelheading1 tbcellCss"><b>Feedback ID</b></td>
<td width="25%" class="tbcellBorder"><html:text  property="feedbackId" styleId="feedbackId" style="width:110px;" maxlength="50" title="Please enter Feedback Id" onchange="checkvalidId('feedbackId');"/></td>
</tr>
<tr align="center">
<td width="25%" class="labelheading1 tbcellCss"><b> From Date</b></td>
<td width="25%" class="tbcellBorder">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text  property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:110px"/></td>
<td width="25%" class="labelheading1 tbcellCss"><b> To Date</b></td>
<td width="25%" class="tbcellBorder">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text  property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:110px"/></td>
</tr>
<tr><td align="center" colspan="6">
<button class="but"   type="button" name="Search" value="Search" onclick="javascript:fn_caseSearch()"  >Search</button>
<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  >Reset</button>
</td>
</tr>
</table>
<table  border="0" width="95%"  cellpadding="0" cellspacing="0" align="center" style="padding-top:0px;margin:0px auto;">
<br></br>
<logic:greaterThan value="0" property="issueResultFlagSize" name="casesSearchForm">
<tr><td width="2%"></td>
<td width="10%">Showing Results</td>
<td width="10%"><bean:write name="casesSearchForm" property="startIndex" />  - <bean:write name="casesSearchForm" property="endIndex" /> 
of <bean:write name="casesSearchForm" property="totalRows" /> </td>
<td  width="10%">
Show Page
</td>
</td>
<td width="30%">
<c:forEach items="${pages}" var="page" >
<c:if test="${liPageNo eq page}" >
<c:out value="${page}" /> &nbsp;
</c:if>
<c:if test="${liPageNo ne page}">
<c:if test="${page lt 11}" >
<a href="javascript:showPagination('<c:out value="${page}" />',${lStrRowsperpage})"> <c:out value="${page}" /></a> &nbsp;
</c:if>
</c:if>
</c:forEach>
<logic:notEmpty name="casesSearchForm" property="prev">
<img src="images/left2.png" onclick="javascript:showPagination('<bean:write name="casesSearchForm" property="prev" />')"/>
</logic:notEmpty>
<logic:notEmpty name="casesSearchForm" property="next">
<img src="images/right2.png" onclick="javascript:showPagination('<bean:write name="casesSearchForm" property="next" />')"/>
</logic:notEmpty>

</td>

<td width="10%">
Show in sets of
</td>
<td width="20%">
<c:set var="ListNoSet" value="10,20,50"/> 
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${lStrRowsperpage eq set }" >
${set} &nbsp;
</c:if>
<c:if test="${lStrRowsperpage ne set }" >
<a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /> </a> &nbsp;
</c:if>

</c:forEach>
</td>
</logic:greaterThan>
</tr>
</table>
<logic:greaterThan value="0" property="issueResultFlagSize" name="casesSearchForm">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" style="table-layout: fixed;padding-top:0px;margin:0px auto;">
<tr class="tbheader" rowspan="4">
<td width="5%" valign="top"><b>FEEDBACK ID </b></td>
<td width="5%" valign="top"><b>CREATED DATE</b></td>
</tr>
<tr class="tbheader" rowspan="0"></tr>
<logic:iterate id="result" name="casesSearchForm"  property="issueDetailsList" indexId="index">
<tr  class="border0" > 
<td  style="word-wrap:break-word;" class="tbcellBorder border0"><a href="javascript:fn_feedbackid('<bean:write name="result" property="FEEDBACKID"/>');"  title="click on FeedbackId to view the Feedback details"><bean:write name="result" property="FEEDBACKID" /></a></td>
<td  style="word-wrap:break-word;" class="tbcellBorder border0"><bean:write name="result" property="CREATEDON" /></td>
</tr>
</logic:iterate>
</table>
</logic:greaterThan>
<logic:equal value="0" property="issueResultFlagSize" name="casesSearchForm">
<table width="50%" border="1" cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;">
<tr >
<td align="center" height="50">
<b>No results found</b>
</td>
</tr>
</table>
</logic:equal> 
<html:hidden property="rowsPerPage"  name="casesSearchForm" styleId="rowsPerPage"/>
<html:hidden property="startIndex" name="casesSearchForm" styleId="startIndex"/>
<html:hidden property="showPage" name="casesSearchForm" styleId="showPage"/>
</html:form>
</body>
</fmt:bundle>
</html>