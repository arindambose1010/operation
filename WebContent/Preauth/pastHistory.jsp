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
<title>Cases Search</title>
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
</script>

</head>

<body>
<html:form  method="post"  action="/casesApprovalAction.do" > 
<table width="100%" align="center">
<tr ><td  colspan="20" align="center"  class="tbheader" ><fmt:message key='label.pastHistory.carUrilizationHis' /></td></tr>
</table>
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center">
<bean:size property="cardUtilList" name="casesApprovalActionForm" id="size" />
<logic:greaterThan value="0" name="size">
<tr class="tbheader" ><td width="15%"><fmt:message key='label.pastHistory.caseNo' /></td>
<td width="15%"><fmt:message key='label.pastHistory.patName' /></td>
<td width="10%"><fmt:message key='label.pastHistory.regDate' /></td>
<td width="5%"><fmt:message key='label.pastHistory.policyPeriod' /></td>
<td width="5%"><fmt:message key='label.pastHistory.preauthStat' /> </td>
<td width="5%"><fmt:message key='label.pastHistory.onBedStat' /></td>
<td width="5%"><fmt:message key='label.pastHistory.caseStatus' /></td>
<td width="5%"><fmt:message key='label.pastHistory.claimPaidCost' /></td>
<td width="5%"><fmt:message key='label.pastHistory.pkgPrice' /></td>
<td width="10%"><fmt:message key='label.pastHistory.cat' /></td>
<td width="10%"><fmt:message key='label.pastHistory.subCat' /></td>
<td width="10%"><fmt:message key='label.pastHistory.therapy' /></td>
</tr>
<logic:iterate id="results" property="cardUtilList" name="casesApprovalActionForm">
<tr>
<td><bean:write name="results" property="caseNo" /></td>
<td><bean:write name="results" property="name" /></td>
 <td><bean:write name="results" property="regDt" /></td> 
<!-- <td><bean:write name="results" property="phase" /></td> -->
<td><bean:write name="results" property="policyPeriod" /></td>
<td><bean:write name="results" property="preauthStatus" /></td>
<td><bean:write name="results" property="onBedStatus" /></td>
<td><bean:write name="results" property="caseStatus" /></td>
<td><bean:write name="results" property="claimPaidCost" /></td>
<td><bean:write name="results" property="pkgPrice" /></td>
<td><bean:write name="results" property="category" /></td>
<td><bean:write name="results" property="subCat" /></td>
<td><bean:write name="results" property="therapy" /></td>
</tr>
</logic:iterate>
</logic:greaterThan>
<logic:notPresent  property="cardUtilList" name="casesApprovalActionForm">
<tr><td>
<fmt:message key='label.pastHistory.noRecFound' />
</td>
</tr>
</logic:notPresent>
</table>
<br></br>
<table border="0" align="center" cellpadding="1" cellspacing="1" width="95%" >
<tr class="tbheader" ><td colspan="6" align="center"><fmt:message key='label.pastHistory.hospHis' /></td></tr>
<tr><td>&nbsp;</td></tr>
<tr class="tbheader"><td colspan="6" align="left"><fmt:message key='label.pastHistory.nwhDtls' /> </td></tr>
<tr><td><fmt:message key='label.pastHistory.nwhName' /></td> <td><bean:write name="casesApprovalActionForm" property="nwhname" /></td>
<td><fmt:message key='label.pastHistory.address' /></td><td><bean:write name="casesApprovalActionForm" property="address" /></td>
<td><fmt:message key='label.pastHistory.contactNo' /></td><td><bean:write name="casesApprovalActionForm" property="contactNo" /></td>
</tr>
<tr class="tbheader"><td colspan="6" align="left"> <fmt:message key='label.pastHistory.bedDtls' /></td></tr>
<tr><td><fmt:message key='label.pastHistory.hospBedCap' /></td><td><bean:write name="casesApprovalActionForm" property="hospBedCap" /></td>
<td><fmt:message key='label.pastHistory.currBedOcc' /></td><td><bean:write name="casesApprovalActionForm" property="currBedOccu" /></td>
</tr>
<tr><td>&nbsp;</td></tr>
</table>
<br></br>
<table border="0" align="center" cellpadding="1" cellspacing="1" width="95%" >
<tr class="tbheader"><td colspan="3" align="center"><b> <fmt:message key='label.pastHistory.specialities' /> </b>  </td></tr>
<tr><td>&nbsp;</td></tr>
 <c:forEach var="disl" items="${lstSpecialities}" begin="0" varStatus ="status">
 <c:set var="temp" value="${lstSpecialities[status.index]}" />
<c:if test="${(status.count % 2) eq 1}">
<tr ><td colspan=1></td>
</c:if>
<td colspan=1>
${temp.VALUE}
 </td>								
<c:if test="${status.count eq fn:length(lstSpecialities)-1 || (status.count % 2) eq 0 }">
</tr>
</c:if>
</c:forEach>
</table>
</html:form>
</body>
</fmt:bundle>
</html>