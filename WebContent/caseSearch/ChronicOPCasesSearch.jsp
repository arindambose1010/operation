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

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<title>Chronic OP Cases Search</title>
<script type="text/javascript">
var searchType='${caseSearchType}';
//By anitha
function fn_caseApproval(caseId,arg)
{		
	document.forms[0].action='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&caseSearchType='+searchType;	
	document.forms[0].submit();
}

function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	caseSearch();
	}
function showinSetsOf(num)
	{
	document.forms[0].rowsPerPage.value=num; 
	caseSearch();
	}
function caseSearch()
	{
		 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType;
         document.forms[0].submit();
	}
	function fn_reset()
	{
	//document.forms[0].claimId.value='';	
	//document.forms[0].catId.value='';	
	//document.forms[0].errStatusId.value='';	
	//document.forms[0].surgyId.value='';	
	document.forms[0].caseNo.value='';	
	//document.forms[0].claimNo.value='';	
	//document.forms[0].patName.value='';	
	document.forms[0].wapNo.value='';	
	 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=N&caseSearchType="+searchType;
     document.forms[0].submit();
	}
</script>
<style>
</style>
</head>

<body >
<html:form  method="post"  action="/casesSearchAction.do" > 
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr ><td  colspan="8" align="center"  class="tbheader" ><b> <fmt:message key="label.caseSearch.caseSearchTitle" /> </b></td></tr>
<tr><td colspan="8">&nbsp;</td></tr>
<tr align="center">
<td width="20%">&nbsp;</td>
<td width="10%"><fmt:message key="label.caseSearch.caseNo" /></td>
<td width="10%"><html:text name="casesSearchFormClaims" property="caseNo" /></td>
<td width="10%">&nbsp;</td>
<td width="10%"><fmt:message key="label.caseSearch.wapNo" /></td>
<td width="10%"><html:text name="casesSearchFormClaims" property="wapNo" /></td>
</tr>
<tr><td colspan="8">&nbsp;</td></tr>
<tr>
<td align="center" colspan="8">
<button class="but"   type="button" name="Search" value="Search" onclick="javascript:caseSearch()"  >Search</button>
<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  >Reset</button>
</td>
</tr>
<!-- <tr><td width="4%"></td>
<td>Case No</td><td>Claim No</td><td>Patient Name</td>
</tr>
<tr><td width="4%"></td>
<td><html:text name="casesSearchFormClaims" property="caseNo" /></td>
<td><html:text name="casesSearchFormClaims" property="claimNo" /></td>
<td><html:text name="casesSearchFormClaims" property="patName" /></td>
</tr>
<tr><td>&nbsp;</td></tr>-->

<tr><td>&nbsp;</td></tr>
</table>
<table  border="0" width="95%"  cellpadding="0" cellspacing="0" align="center" style="padding-top:0px;margin:0px auto;">
<br></br>
<tr><td width="2%"></td>
<bean:size id="lstCaseSearchSize" name="casesSearchFormClaims"  property="lstCaseSearch"/>
<td width="10%">Showing Results</td>
<td width="10%"><bean:write name="casesSearchFormClaims" property="startIndex" />  - <bean:write name="casesSearchFormClaims" property="endIndex" /> 
of <bean:write name="casesSearchFormClaims" property="totalRows" /> </td>
<td  width="10%">
Show Page
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
<logic:notEmpty name="casesSearchFormClaims" property="prev">
<img src="images/left2.png" onclick="javascript:showPagination('<bean:write name="casesSearchFormClaims" property="prev" />')"/>
</logic:notEmpty>
<logic:notEmpty name="casesSearchFormClaims" property="next">
<img src="images/right2.png" onclick="javascript:showPagination('<bean:write name="casesSearchFormClaims" property="next" />')"/>
</logic:notEmpty>

</td>
<logic:greaterThan value="0" name="lstCaseSearchSize">
<td width="10%">
Show in sets of
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
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr class="tbheader"><b><td width="15%"><fmt:message key="label.caseSearch.caseNo" /></td><td width="25%"><fmt:message key="label.caseSearch.patientName" /></td><td width="20%"><fmt:message key="label.caseSearch.wapNo" /></td>
<td width="25%">Case Status </td><td width="15%"><fmt:message key="label.caseSearch.regdate" /></td>
<td width="10%"><fmt:message key="label.caseSearch.claimAmt" /></td>
</b></tr>
<logic:iterate id="result" name="casesSearchFormClaims"  property="lstCaseSearch" indexId="index">
<tr  class="border<%=index%2 %>" > 
<td><a href="javascript:fn_caseApproval('<bean:write name="result" property="caseId" />','');"><bean:write name="result" property="caseNo" /></a></td>
<td><bean:write name="result" property="patientName" /></td>
<td><bean:write name="result" property="wapNo" /></td>
<td><bean:write name="result" property="claimStatus" /></td>
<td><bean:write name="result" property="inpatientCaseSubDt" /></td>
<td><bean:write name="result" property="claimAmt" /></td>

</tr>
</logic:iterate>
</table>
</logic:greaterThan>
<logic:equal value="0" name="lstCaseSearchSize">
<table border="0" width="50%"  cellpadding="1" cellspacing="1" align="center">
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td align="center">
No results found
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</logic:equal>
<html:hidden property="rowsPerPage"  name="casesSearchFormClaims"/>
<html:hidden property="startIndex" name="casesSearchFormClaims" />
<html:hidden property="showPage" name="casesSearchFormClaims" />

</html:form>
</body>
</fmt:bundle>
</html>