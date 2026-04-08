
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,
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
<title >Telephonic Registered Patients</title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script type="text/javascript">
function fn_getRegistration(caseId,arg,cardType)
{		
	document.forms[0].action='/<%=context%>/patientDetails.do?actionFlag=telephonicDirectReg&telephonicId='+caseId+'&cardNo='+arg+'&cardType='+cardType;
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
	 document.forms[0].action='/<%=context%>/telephonicAction.do?actionFlag=telephonicIntimationSearch&searchFlag=Y';
     document.forms[0].submit();
}
function fnSearch()
{
	if( document.forms[0].telephonicId.value=="" &&
        document.forms[0].fromDate.value=="" && 
        document.forms[0].toDate.value=="")
            {  
				jqueryAlertMsg('Search Criteria Validation','Please enter any search criteria! ');
                return false;
            }
	document.forms[0].action='/<%=context%>/telephonicAction.do?actionFlag=telephonicIntimationSearch&searchFlag=Y';
     document.forms[0].submit();
}
function resetSearch()
{
	document.getElementById("telephonicId").value="";
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
}
</script>
</head>
<body>
<html:form  method="post"  action="/telephonicAction.do" > 
<table width="98%" style="margin:0 auto">
<tr><td colspan="6">
<table class="tbheader">
<tr><th><b>Telephonic Registered Patients</b></th></tr>
</table></td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td colspan="6">
<table width="100%">
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>Telephonic ID</b></td>
<td width="16%" class="tbcellBorder"><html:text name="telephonicForm" property="telephonicId" styleId="telephonicId" maxlength="12" onchange="validateNumber('Telephonic ID',this)" title="Enter Telephonic ID" style="WIDTH: 10em;border:0;box-shadow:none;"/></td>
<td width="18%" class="labelheading1 tbcellCss"><b>Registered From Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="telephonicForm" property="fromDate" styleId="fromDate" title="Enter From Date" onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH:8em;border:0;box-shadow:none;"/></td>
<td width="18%" class="labelheading1 tbcellCss"><b>Registered To Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="telephonicForm" property="toDate" styleId="toDate" title="Enter To Date" onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH:8em;border:0;box-shadow:none;"/></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td align="center" colspan="6">
	<button class="but"  type="button" onclick="javascript:fnSearch()">Search</button>
	<button class="but"  type="button" onclick="javascript:resetSearch()">Reset</button>
</td>
</tr>
</table>
</td></tr>
<tr>
<bean:size id="lstCaseSearchSize" name="telephonicForm"  property="lstCaseSearch"/>
<logic:notEmpty name="pages">
<td width="10%">Showing Results</td>
<td width="10%"><bean:write name="telephonicForm" property="startIndex" />  - <bean:write name="telephonicForm" property="endIndex" /> 
of <bean:write name="telephonicForm" property="totalRows" /> </td>
<td  width="10%">
Show Page
</td>
</logic:notEmpty>
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
<logic:notEmpty name="telephonicForm" property="prev">
<img src="images/left2.png" onclick="javascript:showPagination('<bean:write name="telephonicForm" property="prev" />')"/>
</logic:notEmpty>
<logic:notEmpty name="telephonicForm" property="next">
<img src="images/right2.png" onclick="javascript:showPagination('<bean:write name="telephonicForm" property="next" />')"/>
</logic:notEmpty>

</td>
<logic:greaterThan value="0" name="lstCaseSearchSize">
<td width="10%">
Show in sets of
</td>
<td width="30%">
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
<table width="98%" style="margin:0 auto" border="1">
<tr class="tbheader1">
<td width="5%" ><fmt:message key="EHF.TelephonicId" /></td>
<td width="5%"><fmt:message key="EHF.HealthCardNo" /></td>
<td width="10%"><fmt:message key="EHF.PatientName" /></td>
<td width="10%"><fmt:message key='EHF.RegistrationDate' /></td>
<td width="10%"><fmt:message key='EHF.Tele.CaseStatus' /></td>
<td width="10%"><fmt:message key="EHF.CallerName" /></td>
<td width="5%"><fmt:message key="EHF.CallerNo" /></td>
<td width="10%"><fmt:message key="EHF.District" /></td>
<td width="10%"><fmt:message key="EHF.Hospital" /></td>
<td width="10%"><fmt:message key="EHF.DiseaseName" /></td>
<td width="10%"><fmt:message key="EHF.DoctorName" /></td>
<td width="5%"><fmt:message key="EHF.DocPhNo" /></td>
</tr>
<logic:iterate id="result" name="telephonicForm"  property="lstCaseSearch" indexId="index">
<logic:equal value="Telephonic Intimation-Initiated" name="result" property="teleStatus">
<tr> 
<td align="center">
<a href="javascript:fn_getRegistration('<bean:write name="result" property="telephonicId" />','<bean:write name="result" property="healthCardNo" />','<bean:write name="result" property="cardType" />');"><bean:write name="result" property="telephonicId" /></a>
</td>
</logic:equal>
<logic:equal value="Telephonic Intimation Cancelled" name="result" property="teleStatus">
<tr style="background-color: #FFFFD6"> 
<td align="center">
<bean:write name="result" property="telephonicId" />
</td>
</logic:equal>

<td align="center"><bean:write name="result" property="healthCardNo" /></td>
<td><bean:write name="result" property="patientName" /></td>
<td><bean:write name="result" property="crtDt" /></td>
<td><bean:write name="result" property="teleStatus" /></td>
<td><bean:write name="result" property="callerName" /></td>
<td><bean:write name="result" property="callerMobileNo" /></td>
<td><bean:write name="result" property="locName" /></td>
<td><bean:write name="result" property="hospName" /></td>
<td><bean:write name="result" property="icdCatName" /></td>
<td><bean:write name="result" property="doctorName" /></td>
<td><bean:write name="result" property="mobileNo" /></td>
</tr>
</logic:iterate>
</table>
</logic:greaterThan>
<logic:equal value="0" name="lstCaseSearchSize">
<table width="50%" style="margin:1px auto;" class="tb">
<tr>
<td style="text-align:center;height:50px;">
<b><font size="3px">No results found</font></b>
</td>
</tr>
</table>
</logic:equal>
<html:hidden property="rowsPerPage"  name="telephonicForm"/>
<html:hidden property="startIndex" name="telephonicForm" />
<html:hidden property="showPage" name="telephonicForm" />
</html:form>
</body>
</fmt:bundle>
</html>