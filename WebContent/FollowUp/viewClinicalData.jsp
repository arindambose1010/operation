<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/RightClickDisable.jsp"%>
<html>
<fmt:bundle basename="Registration">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Clinical Data For FollowUp</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
function fn_openAtachment(filepath,fileName)
{  
	
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath+'&fileName='+fileName;
    window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
</script>
<style>
body{font-size:1.3em !important;}
</style>
</head>
<body>
<center>
<form action="/followUpAction.do" method="post"  enctype="multipart/form-data">
<table border="0" width="99%" align="center" bordercolor="black" style="margin:5px;">
<tr><td colspan="5" class="tbheader">
<b>FOLLOW - UP </b></td></tr></table>
<c:set var="clinicalCounter" value="0"/>
<c:forEach items="${followUpVOList}" var="followUpVO" varStatus="loop">
<c:set var="clinicalCounter" value="${clinicalCounter + 1}"/>
<table border="1" width="95%" align="center" bordercolor="black" style="margin:5px;">
<tr><td class="tbheader">
<b>Clincial Note: ${clinicalCounter} </b></td></tr>
<tr><td>
<table border="0" width="99%" align="center" >
<tr><td colspan="5" class="tbheader">
<b></b></td></tr>
<tr><td class="labelheading1 tbcellCss" width="25%"><b>Clinical ID</b></td>
<td class="tbcellBorder" width="25%">${followUpVO.CLINICALID}</td> 
<td class="labelheading1 tbcellCss" width="25%"><b>Clinical Submitted Date</b></td>
<td class="tbcellBorder" width="25%">${followUpVO.fromDate}</td> 
</tr>
<tr><td class="labelheading1 tbcellCss" width="25%"><b>Blood Pressure</b></td>
<td class="tbcellBorder" width="25%">${followUpVO.BLOODPRESSURE}</td> 
<td class="labelheading1 tbcellCss" width="25%"><b>Pulse Rate</b></td>
<td class="tbcellBorder" width="25%">${followUpVO.PULSE}</td> 
</tr>
<tr><td class="labelheading1 tbcellCss" width="25%"><b>Temperature</b></td>
<td class="tbcellBorder" width="25%">${followUpVO.TEMPERATURE}</td> 
	<td class="labelheading1 tbcellCss" width="25%"><b>Respiration Rate</b></td>
<td class="tbcellBorder" width="25%">${followUpVO.RESPIRATORY}</td> 
</tr>
<tr><td class="labelheading1 tbcellCss"><b>Heart Sound</b></td>
<td class="tbcellBorder">${followUpVO.HEART_RATE}</td> 
	<td class="labelheading1 tbcellCss"><b>Lungs</b></td>
<td class="tbcellBorder">${followUpVO.LUNGS}</td> 
</tr>
<tr><td class="labelheading1 tbcellCss"><b>Fluid In</b></td>
<td class="tbcellBorder">${followUpVO.FLUIDINPT}</td> 
<td class="labelheading1 tbcellCss"><b>Fluid Out</b></td>
<td class="tbcellBorder">${followUpVO.FLUIDOUTPUT}</td> 
</tr>
</table>
<br/>
<table width="99%" align="center">
<tr><td  colspan="6" align="left"><b>Investigations</b></td></tr>
 <tr><td width="5%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SNO"/></b></td>   
             <td width="15%" class="labelheading1 tbcellCss"><b>Investigation Block Code</b></td>  
        	<td width="30%" class="labelheading1 tbcellCss"><b>Investigation Block Name</b></td>      
        	<td width="10%" class="labelheading1 tbcellCss"><b>Investigation Code</b></td>  
        	<td width="30%" class="labelheading1 tbcellCss"><b>Investigation Name</b></td>   
        	<td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Attachment"/></b></td>
 </tr>
<c:set var="counter" value="0"/>
<c:forEach items="${followUpVO.genAttachmentsList}" var="attachElement" varStatus="loop"> 
<c:set var="counter" value="${counter + 1}"/>
<tr>
            <td width="10%" class="tbcellBorder">${counter}</td>   
            <td width="10%" class="tbcellBorder">${attachElement.testId}</td>  
        	<td width="30%" class="tbcellBorder">${attachElement.testName}</td>        
        	<td width="10%" class="tbcellBorder">${attachElement.fileExtsn}</td>  
        	<td width="30%" class="tbcellBorder">${attachElement.fileName}</td>   
        	<td width="10%" class="tbcellBorder"><a href="#" onclick="javascript:fn_openAtachment('${attachElement.filePath}','${attachElement.filePath}');">View Attach</a></td>
            </tr>
</c:forEach>
</table>
<br/>
<table width="99%"  align="center">
<tr><td  colspan="8" align="left"><b>Drugs</b></td></tr>
 <tr>  
      	<td width="5%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SNO"/></b></td>        
        <td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugTypeName"/></b></td>   
       	<td width="13%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugSubTypeName"/></b></td>
        <td width="13%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugName"/></b></td>
        <td width="13%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="8%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b></td>
        <td width="8%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
        <td width="7%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.BatchNumber"/></b></td>
        <td width="8%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugExpiryDate"/></b></td>
 </tr>
<c:forEach items="${followUpVO.drugs}" var="element"> 
  <tr>
    <td width="5%" class="tbcellBorder">${element.drugId}</td>
    <td width="15%" class="tbcellBorder">${element.drugTypeName}</td>
    <td width="13%" class="tbcellBorder">${element.drugSubTypeName}</td>
    <td width="13%" class="tbcellBorder" >${element.drugName}</td>
    <td width="13%" class="tbcellBorder">${element.route}</td>
    <td width="8%" class="tbcellBorder">${element.strength}</td>
    <td width="8%" class="tbcellBorder">${element.dosage}</td>
    <td width="10%" class="tbcellBorder">${element.medicationPeriod}</td>
    <td width="7%" class="tbcellBorder">${element.batchNumber}</td>
    <td width="8%" class="tbcellBorder">${element.drugExpiryDt}</td>
  </tr>
</c:forEach>  
</table></td></tr></table><br/>
</c:forEach>
<table width="99%" align="center">
<tr><td colspan="4" align="center" >
<button  class="btn btn-primary" type="button"  value="Close" onclick="window.close();">Close</button>
</td></tr>
</table>
</form>
</center>
</body>
</fmt:bundle>
</html>