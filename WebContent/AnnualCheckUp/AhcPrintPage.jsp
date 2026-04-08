<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@include file="/common/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AHC Print Page</title>
<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
	
		<script src="js/jquery-1.9.1.min.js"></script>
<script>
function loadPrint(){
	var url='/<%=context%>/annualCheckUpAction.do?actionFlag=printAhcForm&ahcId='+'${ahcId}'; 
	document.forms[0].action=url;   
	document.forms[0].target="bottomFrame";
	document.forms[0].submit();
}
</script>
</head>
<body>
<html:form action="/annualCheckUpAction.do" method="post">
<div class="printPage">
<table style="width:100%">
<tr>
<td colspan="6">
<table style="width:100%"><tr class="tbheader">
<td id="topSlide" width="5%" align="center"><img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img></td>
	<td align="center" >AHC Registration Details</td>
	<td id="menuSlide" width="5%"><img id="topImage" width="30" height="20" align="top" onclick="javascript:fn_maxmizeTop()" alt="Back" title="Back" style="cursor:hand;" src="images/back.jpg"></td>
</tr>
</table>
	
	</td>
</tr>
<tr>
	<td class="labelheading1 tbcellCss">Name</td>
	<td class="tbcellCss" colspan="5"><bean:write name = "annualCheckUpForm" property="name"/>,<bean:write name = "annualCheckUpForm" property="gender"/>,<bean:write name = "annualCheckUpForm" property="years"/> years <bean:write name = "annualCheckUpForm" property="months"/> months <bean:write name = "annualCheckUpForm" property="days"/> days old</td>
	
</tr>
<tr>
	<td class="labelheading1 tbcellCss">Card No</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="cardNo"/></td>
	<td class="labelheading1 tbcellCss">Status</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="status"/></td>
	<td rowspan="4">
	<table width="80%" height="80%" style="margin: auto auto">
						<tr>
							<td align="center"><logic:notEmpty name="annualCheckUpForm"
								property="photoUrl">
								<img id="patImage" src="common/showDocument.jsp" width="150"
									height="150" alt="NO DATA"
									onmouseover="resizePatImage('patImage','200','200')"
									onmouseout="resizePatImage('patImage','150','150')" />
							</logic:notEmpty> <logic:empty name="annualCheckUpForm" property="photoUrl">
								<img src="images/photonot.gif" width="150" height="150"
									alt="NO DATA" />
							</logic:empty></td>
						</tr>
					</table>
	</td>
	</tr>
	<tr>
	<td class="labelheading1 tbcellCss">AHC No</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="patientNo"/></td>
	<td class="labelheading1 tbcellCss">Hospital Name</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="hospitalName"/></td>
	</tr>
<tr>
	<td class="labelheading1 tbcellCss">Registration Date</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="dtTime"/></td>
	<td class="labelheading1 tbcellCss">Contact No</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="contactNo"/></td>
</tr>
<tr>
	<td class="labelheading1 tbcellCss">District</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="district"/></td>
	<td class="labelheading1 tbcellCss">Mandal</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="mandal"/></td></tr>
<tr>
	<td class="labelheading1 tbcellCss">Village</td>
	<td class="tbcellCss"><bean:write name = "annualCheckUpForm" property="village"/></td>
</tr>
<tr><td colspan="3">
			<table width="100%"
						style="table-layout: fixed; word-wrap: break-word;">
						<tr><td colspan="2" class="tbheader" align="center">Card Address</td></tr>
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><fmt:message
								key="EHF.HouseNo" /></td>
							<td class="tbcellBorder" width="50%">&nbsp;<bean:write
								name="annualCheckUpForm" property="houseNo" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Street" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="street" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss">State</td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="state" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.District" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="district" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Mdl/Mcl" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="mandal" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Village" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="village" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Pin" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="pin" /></td>
						</tr>
					</table>
					</td>
				<td colspan="3">
					<table width="100%"
						style="table-layout: fixed; word-wrap: break-word;">
						<tr><td colspan="2" class="tbheader" align="center">Communication Address</td></tr>
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><fmt:message
								key="EHF.HouseNo" /></td>
							<td class="tbcellBorder" width="50%">&nbsp;<bean:write
								name="annualCheckUpForm" property="houseNo" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Street" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="street" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss">State</td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="state" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.District" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="district" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Mdl/Mcl" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="mandal" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Village" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="village" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><fmt:message
								key="EHF.Pin" /></td>
							<td class="tbcellBorder">&nbsp;<bean:write
								name="annualCheckUpForm" property="pin" /></td>
						</tr>
					</table>
					</td>
					</tr>
					<tr></tr>
					
			<tr><td class="tbheader" colspan="7" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Medical Details</b></td></tr>

<tr>
<td class="labelheading1 tbcellCss" colspan="3" ><b>History of Present Illness</b></td>
<td  class="tbcellBorder" colspan="3">${otherFindings.presentIllness}</td>

</tr>
</table>  
  <!-- IP Details -->

<!-- past history -->
<table width="100%">
<tr><td class="tbheader" align="center"><b>History of Past Illness</b></td></tr>
</table>
<table border="0" width="100%" align=center>
<tr><td>
<c:set var="loopCount1" value="0" />
<c:forEach  items="${pastHistoryList}" varStatus="loop">								
<c:set value="${pastHistoryList[loop.index].ID}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${otherFindings.pastIllness}" delims="~" varStatus="status" begin="0">
<c:choose>
<c:when test="${tokenName == sample}">
<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
<c:if test="${(loopCount1 % 4) eq 1}">
	<tr>
	</c:if>		
<td class="tbcellBorder" >
<c:out value="${pastHistoryList[loop.index].VALUE}"/>	
	<c:if test="${tokenName == 'PH11' }">
	${otherFindings.pastIllenesOthr}
   <%-- <input type="text" id="${pastHistoryList[loop.index].ID}" name="${otherFindings.pastIllenesOthr}" value="${otherFindings.pastIllenesOthr}" disabled="disabled"/> --%>
   </c:if>
   <c:if test="${tokenName == 'PH8' }">
	( ${otherFindings.pastIllenesCancers})
   </c:if>
   <c:if test="${tokenName == 'PH10' }">
	( ${otherFindings.pastIllenesSurg})
   </c:if>
   <c:if test="${tokenName == 'PH12' }">
	( ${otherFindings.pastIllenesEndDis})
   </c:if>
   </td>
</c:when>
</c:choose>  
</c:forTokens>     	
</c:forEach>
</td></tr>
<c:if test="${fn:length(otherFindings.pastIllness) eq 0}">
<tr><td align="center">History of past illness not found</td></tr>
</c:if>
</table>

<!-- Personal history -->
<table width="100%">
<tr><td class="tbheader" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Personal History</b></td></tr>
</table>

<table border="0" width="100%" >
<c:set  value="${otherFindings.lstPerHis}" var="perHisList"></c:set>

<c:set var="loopCount" value="0" /> 
<c:forEach  items="${otherFindings.lstPersonalHistory}" varStatus="loop">	
<c:set var="loopCount" value="${loopCount + 1}" />
<tr>
<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
		<td width="20%" class="labelheading1 tbcellCss">
<c:set value="${otherFindings.lstPersonalHistory[loop.index].lstSub}" var="sample"></c:set>
<b>&nbsp;&nbsp;${otherFindings.lstPersonalHistory[loop.index].VALUE} </b>
</td>
<c:forEach items="${sample}" varStatus="loop1">
<td  class="tbcellBorder">
<c:set value="${sample[loop1.index].ID}" var="subLstId"></c:set>
<c:if test="${subLstId!='PR6.2' && subLstId!='PR5.2'}">
&nbsp;&nbsp;${sample[loop1.index].VALUE}&nbsp;
</c:if>
<c:if test="${fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId=='PR6.2'}">
No
</c:if>
<c:if test="${subLstId=='PR5.2'}">
No
</c:if>
  <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled" checked="checked"/>  &nbsp;
<c:if test="${subLstId=='PR6.1'}">

<td  class="tbcellBorder"> &nbsp;&nbsp;No <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;</td>

      <table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Alcohol  </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="alcohol" id="alcohol" value="Regular" disabled="disabled"/> &nbsp;Regular&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="alcohol" id="alcohol" value="Occasional" disabled="disabled"/> &nbsp;Occasional&nbsp;</td>
	     <td class="tbcellBorder"> <input type="radio" name="alcohol" id="alcohol" value="Teetotaler" disabled="disabled"/> &nbsp;Teetotaler&nbsp; </td></tr>
	    	<tr><td class="labelheading1 tbcellCss"> &nbsp;b.<b>Tobacco  </b></td><td class="tbcellBorder"><input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Snuff</td>
	      <td class="tbcellBorder"><input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Chewable&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Smoking&nbsp;
	      <span id="smokingTd" style="display:none" >
	      &nbsp;Pack  <input  class="tbcellBorder" type="text" name="packNo" id="packNo" style="width:40px" maxlength="3" title="Smoking Pack No" disabled="disabled"/>
	       &nbsp;Years  <input class="tbcellBorder" type="text" name="smokeYears" id="smokeYears" style="width:40px" maxlength="3" title="Smoking Years" disabled="disabled"/>
	     </span>
	     </td>
	      </tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;c.<b>Drug Use  </b></td><td class="tbcellBorder"><input type="radio" name="drugUse" id="drugUse" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="drugUse" id="drugUse" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;d.<b>Betel nut  </b></td><td class="tbcellBorder"><input type="radio" name="betelNut" id="betelNut" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder"> <input type="radio" name="betelNut" id="betelNut" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;e.<b>Betel leaf  </b></td><td class="tbcellBorder"><input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="betelLeaf" id="betelLeaf" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr></table>      
</c:if>
<c:if test="${subLstId=='PR5.1'}">

<td  class="tbcellBorder"> &nbsp;&nbsp; No
<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;</td>

      <table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Allergic to Medicine </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="AllMed" id="AllMed" value="AllMedYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="AllMed" id="AllMed" value="AllMedNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder">
	    	<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="4000"  title="Remark" style="width:70%" disabled="disabled"/></div>
			</td></tr>
			<tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Allergic to Substance other than medicine </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="AllSub" id="AllSub" value="AllSubNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder">
	    	<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="4000" title="Remark" style="width:70%" disabled="disabled"/></div>
			</td></tr></table>			
			</c:if>
</c:if>
<c:if test="${!fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId!='PR6.2'}">
<!-- <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp; -->
<c:if test="${subLstId=='PR6.1'}">
<c:if test="${!fn:contains(perHisList,'PR6.2') }" >
No<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;
</c:if>
</c:if>
</c:if>
</c:if>
<c:if test="${!fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId!='PR5.2'}">
<!--<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp; -->
<c:if test="${subLstId=='PR5.1'}">
<c:if test="${!fn:contains(perHisList,'PR5.2') }" >
No<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;
</c:if>
</c:if>
</c:if>
</c:if>
</td>
</c:forEach>
</tr>
</c:forEach>

</table>

<!-- Family History -->
<table width="100%">
<tr><td class="tbheader" align="center"><b>Family History</b></td></tr>
</table> 
<table border="0" width="100%" align=center >
<tr><td align="center">
<c:set var="loopCount2" value="0" />
<c:forEach  items="${familyHistoryList}" varStatus="loop">								
<c:set value="${familyHistoryList[loop.index].ID}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${otherFindings.familyHis}" delims="~" varStatus="status" begin="0">
<c:choose>
<c:when test="${tokenName == sample}">	
<c:set var="loopCount2" value="${loopCount2 + 1}" /> 
<c:if test="${(loopCount2 % 4) eq 1}">
	<tr>
	</c:if>		
<td class="tbcellBorder" >
<c:out value="${familyHistoryList[loop.index].VALUE}"/>
<c:if test="${tokenName == 'FH11' }">
${otherFindings.familyHistoryOthr}
</c:if>
</c:when>
</c:choose>  
</c:forTokens>     	
</c:forEach>
</td></tr>
<tr><td align="center">
<c:if test="${fn:length(otherFindings.familyHis) eq 0 }">
Family history not found
</c:if>
</td></tr>
</table>

<!-- General Examination Findings -->
<table width="100%">
<tr><td class="tbheader" align="center"><b>General Examination Findings</b></td></tr>
</table>
 
 <table border="0" width="100%" align=center>
 <tr>
 
 <td class="labelheading1 tbcellCss" width="20%"><b>Weight</b></td>
 <td  class="tbcellBorder" width="30%">${otherFindings.weight}</td>
  <td class="labelheading1 tbcellCss" width="10%"><b>Pallor</b></td>
 <td  class="tbcellBorder">
 <c:choose>
	 <c:when test="${otherFindings.pallor=='Y'}">
	  <input type="checkbox" name="PallorChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
	 </c:when>
	  <c:when test="${otherFindings.pallor=='N'}">
	  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="PallorChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
</tr>

<tr> 
 <td class="labelheading1 tbcellCss" width="20%"><b>Cyanosis</b></td>
 <td  class="tbcellBorder" width="30%">
  <c:choose>
	 <c:when test="${otherFindings.cyanosis=='Y'}">
	 <input type="checkbox" name="CyanosisChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled"> No
	 </c:when>
	 <c:when test="${otherFindings.cyanosis=='N'}">
	 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="CyanosisChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
 <td class="labelheading1 tbcellCss" width="20%"><b>Clubbing of Fingers/Toes</b></td>
  <td  class="tbcellBorder" width="30%">
  <c:choose>
	  <c:when test="${otherFindings.clubbingOfFingers=='Y'}">
	 <input type="checkbox" name="ClubbingChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled" >No
	 </c:when>
	   <c:when test="${otherFindings.clubbingOfFingers=='N'}">
	 <input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="ClubbingChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
</tr>
<tr>
 
 <td class="labelheading1 tbcellCss" width="20%"><b>Oedema of Feet</b> </td>
  <td  class="tbcellBorder" width="30%">
  <c:choose>
	  <c:when test="${otherFindings.oedema=='Y'}">
	 <input type="checkbox" name="EdemaChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
	 </c:when>
	 <c:when test="${otherFindings.oedema=='N'}">
	 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="EdemaChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
 <td class="labelheading1 tbcellCss" width="12%"><b>Temperature</b> </td>
 <td  class="tbcellBorder">${otherFindings.temperature}</td> 
  </tr>
 <tr>
 
 
 </tr>
<tr>
 

 <td class="labelheading1 tbcellCss" width="12%"><b>Pulse Rate per Minute</b></td>
 <td  class="tbcellBorder">${otherFindings.pulseRate}</td> 

 <td class="labelheading1 tbcellCss" width="10%"><b>Respiration Rate</b></td>
 <td  class="tbcellBorder">${otherFindings.respirationRate}</td>
 </tr>
 <tr>
 <td class="labelheading1 tbcellCss" width="12%"><b>BP mm/HG</b> </td>
 <td  class="tbcellBorder">${otherFindings.bpLmt}</td>
  </tr>


</table>

<script >
var addition='${otherFindings.addiction}';var additionKnown='${otherFindings.allergy}';
if(document.getElementById("habitsTd") != null)
document.getElementById("habitsTd").style.display='block';
addition=addition.replace("PR6.1(","");
additionKnown=additionKnown.replace("PR5.1,","");
var additionList = addition.split(",");
var addKnownList = additionKnown.split(",");
if(addKnownList.length>0){
	for(var i = 0; i<addKnownList.length;i++)
    {	    
		var addtn = addKnownList[i].split("$");
		if(addtn[0]=='AllMed'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllMedYes'){
				
				document.forms[0].AllMed[0].checked='checked';
				document.getElementById("AllMedDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllMedRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllMedNo'){
				document.forms[0].AllMed[1].checked='checked';
		}
	   }
		if(addtn[0]=='AllSub'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllSubYes'){
				
				document.forms[0].AllSub[0].checked='checked';
				document.getElementById("AllSubDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllSubRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllSubNo'){
				document.forms[0].AllSub[1].checked='checked';
		}
	   }
}
}
if(additionList.length>0)
{
	for(var i = 0; i<additionList.length;i++)
    {	
	    var addtn = additionList[i].split("$");
	    if(addtn[0]=='Alcohol')
	    	{if(addtn[1]=='Regular')
	    		document.forms[0].alcohol[0].checked='checked';
	    	else if (addtn[1]=='Occasional')
	    		document.forms[0].alcohol[1].checked='checked';
	    	else if (addtn[1]=='Teetotaler')
	    		document.forms[0].alcohol[2].checked='checked';
	    	}
	    else if(addtn[0]=='Tobacco')
		    {
	    	var tabacoLst = addtn[1].split("(");
	    	
	    	if(tabacoLst[0]=='Snuff')
	    		document.forms[0].tobacco[0].checked='checked';
	    	else if (tabacoLst[0]=='Chewable')
	    		document.forms[0].tobacco[1].checked='checked';
	    	else if (tabacoLst[0]=='Smoking')
		    	{
	    		document.forms[0].tobacco[2].checked='checked';
	    		tabacoLst[1] = tabacoLst[1].replace(")","");
	    		
	    		document.getElementById("smokingTd").style.display='block';
	    		
	    		var smokSub = tabacoLst[1].split("*");
	    	
	    		if(smokSub.length>0)
		    		{
                       for(var j=0;j<smokSub.length;j++){
                    	   
                    	  var smokeVal= smokSub[j].split("@");
                    	  
                    	  if(smokeVal[0]=='PackNo')
                    		  document.forms[0].packNo.value=smokeVal[1];
                    	  else
                    		  document.forms[0].smokeYears.value=smokeVal[1];
                           } 
		    		}
		    	}
             }
	    else if(addtn[0]=='DrugUse')
		    {
              if(addtn[1]=='Yes')
            	  document.forms[0].drugUse[0].checked='checked';
              else  if(addtn[1]=='No')
            	  document.forms[0].drugUse[1].checked='checked';
            }
	    else if(addtn[0]=='BetelNut')
	    {
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelNut[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelNut[1].checked='checked';
	    }
	    else if(addtn[0]=='BetelLeaf')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelLeaf[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelLeaf[1].checked='checked';
	    }
    }
}
</script>

			
		
	</table>
</div>
</html:form>
</body>
<script>
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
function fn_maxmizeTop()
{
parent.fn_maxmizeTop();

	}
</script>
</html>
</fmt:bundle>
