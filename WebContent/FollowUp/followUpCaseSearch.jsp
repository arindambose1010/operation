<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}'/> 
<fmt:bundle basename="ApplicationResources">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<%@ include file="/common/includeCalendar.jsp"%>   
<title>Cases Search</title>
<script type="text/javascript">
var searchType='${caseSearchType}';
//jquery alerts functions
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function fn_caseApproval(caseId,arg)
{		
	document.forms[0].action='/<%=context%>/followUpAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg;		
	document.forms[0].submit();
}
function fn_openFollowUpDtls(caseId,followUPId){

	document.forms[0].action='/<%=context%>/followUpAction.do?actionFlag=getFollowUpDtls&CaseId='+caseId+'&followUpid='+followUPId+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&viewFlag=Y';	
	document.forms[0].submit();
}
function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	caseSearch('noSearch');
	}
function showinSetsOf(num)
	{
	document.forms[0].rowsPerPage.value=num; 
	caseSearch('noSearch');
	}
function caseSearch(button)
	{
	if(document.forms[0].followUpStatus.value=='' &&
			document.forms[0].followUpId.value=='' &&
			document.forms[0].caseNo.value=='' &&	
			document.forms[0].patName.value=='' &&
			document.forms[0].wapNo.value=='' &&
			document.getElementById("fromDate").value=='' &&
			document.getElementById("toDate").value==='' && button=="Search")
				{	
		            if(document.forms[0].showScheme.value=='show'){	
		                  if(document.forms[0].schemeType.value==''){
   		                       jqueryAlertMsg('Cases Search','Please select any search criteria');
			                   return;
	                      }
		                  else{
		                	  document.forms[0].action="/Operations/followUpAction.do?actionFlag=caseSearch&searchFlag=Y&schemeType="+document.forms[0].schemeType.value;
		                      document.forms[0].submit();
		                      }}else{
				jqueryAlertMsg('Follow Up Cases Search','Please select any search criteria');
				return;
	            }
				}
	else if ((document.getElementById("fromDate").value=='' && document.getElementById("toDate").value!='' ))
				{
					jqueryAlertMsg('Cases Search','Please select from date ');
					return;
					}
	else if((document.getElementById("fromDate").value!='' && document.getElementById("toDate").value===''))
		{
		jqueryAlertMsg('Cases Search','Please select to date');
		return;
		}
			else
				{
				if(document.forms[0].showScheme.value=='show'){
		 document.forms[0].action="/Operations/followUpAction.do?actionFlag=caseSearch&searchFlag=Y&schemeType="+document.forms[0].schemeType.value;
				}else{
					document.forms[0].action="/Operations/followUpAction.do?actionFlag=caseSearch&searchFlag=Y";
					}
         document.forms[0].submit();
				}
	}
	function fn_reset()
	{
	document.forms[0].followUpStatus.value='';	
	document.forms[0].followUpId.value='';		
	document.forms[0].caseNo.value='';		
	document.forms[0].patName.value='';	
	document.forms[0].wapNo.value='';
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	
	 document.forms[0].action="/Operations/followUpAction.do?actionFlag=caseSearch&searchFlag=N";
     document.forms[0].submit();
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
	
	function chkOnlyAlpha(arg,arg2)
	{
	    var textbox1 =  eval("document.forms[0]."+arg);    
	    var textval = textbox1.value;
	    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
	    if(tbLen.length == 0)
	    	{
	    	 var fr = partial(focusNClear,textbox1);
	 		 jqueryAlertMsg('Follow Up Cases Search','Blank spaces are not allowed in '+arg2,fr);
	    	return false;
	    	}
	    if(textval.charAt(0)==' '){ 
	    	var fr = partial(focusNClear,textbox1);
			 jqueryAlertMsg('Follow Up Cases Search','Starting blank spaces are not allowed in '+arg2,fr);
	    	return false;
	    }
	   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .";
	   var IsNumber=true;
	   var Char; 
	   if(textval.length > 0)
		{
		  for (i = 0; i < textval.length && IsNumber == true; i++) 
			 { 
		        Char = textval.charAt(i); 
	           if (ValidChars.indexOf(Char) == -1) 
				{
	        	   var fr = partial(focusNClear,textbox1);
	      		   jqueryAlertMsg('Clinical notes mandatory fields',"Only Characters from A-Z,a-z are allowed in "+arg2,fr);
					return false;
				 }
				}
		}
		else 
			return false;
	}
	function focusBox(arg)
	{
	  aField = arg; 
	  setTimeout("aField.focus()", 0);  
	}
	function focusNClear(arg)
	{	
	 aField = arg;
	 aField.value='';
	 setTimeout("aField.focus()", 0);  
	}
</script>
</head>

<body >
<html:form  method="post"  action="/followUpAction.do" > 
<table border="0" width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr><td>
<table class="tbheader">
<logic:equal value="Y" property="casesForApprovalFlag" name="followUpForm">
<tr align="center">
<td  colspan="8" align="center"  class="tbheader" ><b><fmt:message key="EHF.Title.FollowUpApproval" /></b></td></tr>
</logic:equal>
<logic:notEqual value="Y" property="casesForApprovalFlag" name="followUpForm">
<tr align="center">
<td  colspan="8" align="center"  class="tbheader" ><b><fmt:message key="EHF.Title.FollowUpCaseSearch" /> </b></td></tr>
</logic:notEqual>
</table>
</td></tr>
<tr><td>
<table  class="tb" width="100%">
<tr><td>
<table width="100%">
<tr>
<td width="17%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.FollowUpStatus"/></b><font color="red">*</font></td>
<td width="17%" class="tbcellBorder"><html:select name="followUpForm" property="followUpStatus"	styleId="followUpStatus" style="width:140px;" title="Select FollowUp Case Status">
<option value="">----Select----</option> <html:options collection="StatusList" property="ID" labelProperty="VALUE" /></html:select></td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.caseNo" /></b></td>
<td width="17%" class="tbcellBorder"><html:text name="followUpForm" property="caseNo" style="width:140px;" maxlength="50" title="Enter Case Number"/></td>
<td  width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.FollowUpId" /></b></td>
<td width="17%" class="tbcellBorder"><html:text style="width:140px;" name="followUpForm" property="followUpId" title="Enter FollowUp ID" /></td></tr>
<tr>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.patientName" /></b></td>
<td width="17%" class="tbcellBorder"><html:text name="followUpForm" property="patName" maxlength="50" title="Enter Patient Name" style="width:140px;" onchange="javascript:chkOnlyAlpha('patName','patName');"/></td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Title.FromDate" /></b></td>
<td width="17%" class="tbcellBorder"><html:text name="followUpForm" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:120px;"/></td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Title.ToDate" /></b></td>
<td width="17%" class="tbcellBorder"><html:text name="followUpForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:120px;"/></td>
</tr>
<tr>
<td  width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CardNo" /></b></td>
<td width="17%" class="tbcellBorder"><html:text style="width:140px;" name="followUpForm" property="wapNo" title="Enter Card Number"/></td>
 <logic:equal name="followUpForm"  property="showScheme" value="show">
	    <td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
		<td width="16%" class="tbcellBorder"><html:select property="schemeType" name="followUpForm" style="width:150px;"  title="Please select Scheme">
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						<html:option value="CD203">Both</html:option> 
						</html:select></td>
						</logic:equal>	
</tr>
<tr><td align="center" colspan="6">
<button class="but"   type="button" name="Search" value="Search" onclick="javascript:caseSearch('Search')"  ><fmt:message key="label.PWD.searchBtn"/></button>
<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  ><fmt:message key="label.PWD.resetBtn"/></button>
</td>
</tr></table></td></tr>
</table></td></tr>
<tr><td>&nbsp;</td></tr>
</table>
<bean:size id="lstCaseSearchSize" name="followUpForm"  property="lstCaseSearch"/>
<table  border="0" width="95%"  cellpadding="0" cellspacing="0" align="center" style="padding-top:0px;margin:0px auto;">
<logic:greaterThan value="0" name="lstCaseSearchSize">
<tr><td width="2%"></td>
<td width="10%"><fmt:message key="label.caseSearch.ShowResult"/></td>
<td width="10%"><bean:write name="followUpForm" property="startIndex" />  - <bean:write name="followUpForm" property="endIndex" /> 
<fmt:message key="label.caseSearch.of"/> <bean:write name="followUpForm" property="totalRows" /> </td>
<td  width="10%">
<fmt:message key="label.caseSearch.ShowPage"/>
</td>
<td width="30%">
<c:forEach items="${pages}" var="page" >
<c:if test="${liPageNo eq page}" >
<c:out value="${page}" /> &nbsp;
</c:if>
<c:if test="${liPageNo ne page}">
<c:if test="${page lt 11}" >
<a href="javascript:showPagination('<c:out value="${page}" />')"> <c:out value="${page}" /> </a> &nbsp;
</c:if>
</c:if>
</c:forEach>
<logic:notEmpty name="followUpForm" property="prev">
<img src="images/left2.png" onclick="javascript:showPagination('<bean:write name="followUpForm" property="prev" />')"/>
</logic:notEmpty>
<logic:notEmpty name="followUpForm" property="next">
<img src="images/right2.png" onclick="javascript:showPagination('<bean:write name="followUpForm" property="next" />')"/>
</logic:notEmpty>

</td>

<td width="10%">
<fmt:message key="label.caseSearch.ShowinSet"/>
</td>
<td width="20%">
<c:set var="ListNoSet" value="10,20,50,100,1000"/> 
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
<logic:greaterThan value="0" name="lstCaseSearchSize">
<table class="tb" width="98%"  cellpadding="1" cellspacing="1" align="center" style="table-layout: fixed;padding-top:0px;margin:0px auto;">
<tr>
<th class="tbheader1" style="width:8%"><fmt:message key="EHF.FollowUpId" /></th>
<th class="tbheader1" style="width:15%"><fmt:message key="label.caseSearch.caseNo" /></th>
<th class="tbheader1" style="width:12%"><fmt:message key="EHF.CardNo" /></th>
<th class="tbheader1" style="width:20%"><fmt:message key="label.caseSearch.patientName" /></th>
<th class="tbheader1" style="width:20%"><fmt:message key="EHF.FollowUpStatus" /></th>
<th class="tbheader1" style="width:15%"><fmt:message key="EHF.FolowUpDate" /></th>
<th class="tbheader1" style="width:12%"><fmt:message key="label.caseSearch.claimAmt" /></th>
</tr>
<logic:iterate id="result" name="followUpForm"  property="lstCaseSearch" indexId="index">
<tr  class="border<%=index%2 %>" > 
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;" align="left">
<a href="javascript:fn_openFollowUpDtls('<bean:write name="result" property="caseId" />','<bean:write name="result" property="followUpId" />');"><bean:write name="result" property="followUpId" /></a>
<logic:equal value="Y" property="casesForApprovalFlag" name="followUpForm">
<c:if test="${result.viewFlag eq 'Y'}">
<img src="images/lock1.png" height="18" width="18" alt="this case is view by other" title="this case is viewed by other"/>
</c:if></logic:equal>
</td>
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;"><bean:write name="result" property="caseNo" /></td>
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;"><bean:write name="result" property="wapNo" /></td>
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;"><bean:write name="result" property="patName" /></td>
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;"><bean:write name="result" property="followUpStatus" /></td>
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;"><bean:write name="result" property="followUpSubDt" /></td>
<td class="tbcellBorder" style="padding:3px;word-wrap:break-word;"><bean:write name="result" property="claimAmt" /></td>

</tr>
</logic:iterate>
</table>
</logic:greaterThan>
<logic:equal value="0" name="lstCaseSearchSize">
<table width="50%" cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >
<td align="center" height="50">
<b><fmt:message key="label.caseSearch.noResultFnd"/></b>
</td>
</tr>
</table>
</logic:equal>
<html:hidden property="rowsPerPage"  name="followUpForm"/>
<html:hidden property="startIndex" name="followUpForm" />
<html:hidden property="showPage" name="followUpForm" />
<html:hidden property="casesForApprovalFlag" name="followUpForm" />
<html:hidden property="showScheme" name="followUpForm" />
</html:form>
</body>
</fmt:bundle>
</html>