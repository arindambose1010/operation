 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<title>
	EHS::Discharge Summary Form
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script>
function fn_Print()
{

	window.print();
}
function fn_close()
{
	window.close();
}
</script>

</head>
<body>
<table width="95%" style="margin:0 auto;" class="tb print_table">
	<!-- Title and address -->
       <tr>
			<td>
				<c:if test="${scheme eq 'CD202'}">
					
					<c:if test="${patientScheme eq 'CD502'}">
					<%@ include file="/common/PrintHeaderJouTG.html" %>
				</c:if>
				<c:if test="${patientScheme ne 'CD502'}">
					<%@ include file="/common/Printheader_tg.html" %>
				</c:if>
				
				</c:if>
				
				<c:if test="${scheme ne 'CD202'}">
				
				<c:if test="${patientScheme eq 'CD502'}">
					<%@ include file="/common/PrintHeaderJouAP.html" %>
				</c:if>
				<c:if test="${patientScheme ne 'CD502'}">
					<%@ include file="/common/Printheader.html" %>
				</c:if>
				
				</c:if>
			</td>
		</tr>
		<tr ><th class="tbheader">DISCHARGE SUMMARY FORM  ${lStrCurentDtTime}</th></tr>
		<!-- Network Hospital -->
		<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Network Hospital</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Name</strong></td>
				<td colspan="3" class="tbcellBorder print_cell">${hospDtls.ID}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Village</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${hospDtls.VALUE}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Mandal</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${hospDtls.CONST}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>District</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${hospDtls.LVL}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No.</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${hospDtls.WFTYPE}</td>
				</tr>	   
		   </table>
		</td></tr>
<!--  Aarogyamithra(on duty at the time of discharge) -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong><c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201' }" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya Mithra
							</c:otherwise>
						</c:choose>(on duty at the time of discharge)</strong></td></tr>
<tr><td>
	<table width="100%">
	   <tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"> ${hospDtls.EMPNAME}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Mobile No.</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${hospDtls.DSGNID}</td>
				</tr>
	</table>
</td></tr>
<!-- patient details -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Patient Details</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.patientName}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.age}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.gender}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Village</strong></td>
				<td width="35%" class="tbcellBorder print_cell"> ${PatientOpList.village}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Mandal</strong></td>
				<td width="35%" class="tbcellBorder print_cell"> ${PatientOpList.mandal}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>District</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.district}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.contactNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>IP Number</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.patientIPNo}</td>
				</tr>	
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Case number</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.caseNo}</td>		   
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Card Number</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.rationCard}</td>
				</tr>
				<tr>
		         <td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Claim Number</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${claimNo}</td>				
		   </table>
		</td></tr>
<!-- Treating Doctor/Surgeon -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Treating Doctor/Surgeon</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"> ${SurgDtls.surgeryName}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${SurgDtls.regNo}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Mobile No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${SurgDtls.contactNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date and Time of Admission</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${ipDate}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date and Time of Surgery/Therapy</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${SurgDtls.surgeryDt}&nbsp;${surgStartTime}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date of Discharge</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${discharge.COMPDESC}</td>
				</tr>
		</table>
		</td></tr>
<!-- General Examination Findings -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>General Examination Findings</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Height</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.height}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Weight</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.weight}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>BMI</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.bmi}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Pallor</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
						<c:choose>
							 <c:when test="${PatientOpList.pallor=='Y'}">
							  <input type="checkbox" name="PallorChkBox" value="Y" checked="checked" disabled="disabled">Yes
							 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
							 </c:when>
							  <c:when test="${PatientOpList.pallor=='N'}">
							  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
							 <input type="checkbox" name="PallorChkBox" value="N" checked="checked" disabled="disabled">No
							 </c:when>
							 <c:otherwise>
							  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
							 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
							 </c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Cyanosis</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
				<c:choose>
						 <c:when test="${PatientOpList.cyanosis=='Y'}">
						 <input type="checkbox" name="CyanosisChkBox" value="Y" checked="checked" disabled="disabled">Yes
						 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled"> No
						 </c:when>
						 <c:when test="${PatientOpList.cyanosis=='N'}">
						 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="CyanosisChkBox" value="N" checked="checked" disabled="disabled">No
						 </c:when>
						 <c:otherwise>
						 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled">No
						 </c:otherwise>
					 </c:choose>
					 </td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Clubbing of Fingers/Toes</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
					 <c:choose>
						  <c:when test="${PatientOpList.clubbingOfFingers=='Y'}">
						 <input type="checkbox" name="ClubbingChkBox" value="Y" checked="checked" disabled="disabled">Yes
						 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled" >No
						 </c:when>
						   <c:when test="${PatientOpList.clubbingOfFingers=='N'}">
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
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Lymphadenopathy</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
					 <c:choose>
						  <c:when test="${PatientOpList.lymphadenopathy=='Y'}">
						 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" checked="checked" disabled="disabled">Yes
						 <input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
						 </c:when>
						 <c:when test="${PatientOpList.lymphadenopathy=='N'}">
						 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="LymphadenopathyChkBox" value="N" checked="checked" disabled="disabled">No
						 </c:when>
						 <c:otherwise>
						 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
						 </c:otherwise>
					 </c:choose>
				</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Edema of Feet</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
					 <c:choose>
						  <c:when test="${PatientOpList.edema=='Y'}">
						 <input type="checkbox" name="EdemaChkBox" value="Y" checked="checked" disabled="disabled">Yes
						 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
						 </c:when>
						 <c:when test="${PatientOpList.edema=='N'}">
						 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="EdemaChkBox" value="N" checked="checked" disabled="disabled">No
						 </c:when>
						 <c:otherwise>
						 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
						 </c:otherwise>
					 </c:choose>
				</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Malnutrition</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
					<c:choose>
						 <c:when  test="${PatientOpList.malNutrition=='Y'}">
						 <input type="checkbox" name="MalnutritionChkBox" value="Y" checked="checked" disabled="disabled">Yes
						 <input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
						 </c:when>
						 <c:when test="${PatientOpList.malNutrition=='N'}">
						 <input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="MalnutritionChkBox" value="N" checked="checked" disabled="disabled">No
						 </c:when>
						 <c:otherwise>
						  <input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
						 </c:otherwise>
					 </c:choose>
				</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Dehydration</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
					<c:choose>
						 <c:when  test="${PatientOpList.dehydration=='Y'}">
							 <input type="checkbox" name="DehydrationChkBox" value="Y" checked="checked" disabled="disabled">Yes (${ PatientOpList.dehydrationType})
							 <input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
							
						 </c:when>
						 
						 <c:when test="${PatientOpList.dehydration=='N'}">
						 <input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="DehydrationChkBox" value="N" checked="checked" disabled="disabled">No
						 </c:when>
						 <c:otherwise>
						  <input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
						 <input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
						 </c:otherwise>
					 </c:choose>
				</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Temperature</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.temperature}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Pulse Rate per Minute</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.pulseRate}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Respiration Rate</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.respirationRate}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>BP Lt. Arm</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.bpLmt}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>BP Rt. Arm</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.bpRmt}</td>
				</tr>
		</table>
		</td></tr>
	<!-- History Of Past Illness -->	
		<tr><td style="text-align:left;" class="tbheader print_heading"><strong>History Of Past Illness</strong></td></tr>
		<tr>
				<td width="100%" class="tbcellBorder print_cell">${PatientOpList.pastIllnessValue}</td>
				</tr>
<!-- Systematic Examination Findings -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Systematic Examination Findings</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		 <tr>
						<td style="width:5%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>S.No.</strong></td>
						<td style="width:30%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Main Symptom Name</strong></td>
						<td style="width:30%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Sub Symptom Name</strong></td>
						<td style="width:35%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Symptom Name</strong></td>
				</tr>
				 <%int i=1; %>
					<c:forEach items="${symptomsList}" var="element"> 
				<tr>
				<td width="5%" class="tbcellCss print_cell"><%=i++ %></td>
				<td width="30%" class="tbcellBorder print_cell">${element.ID}</td>
				<td width="30%" class="tbcellCss print_cell">${element.SUBID}</td>
				<td width="35%" class="tbcellBorder print_cell">${element.VALUE}</td>
				</tr> 
				</c:forEach> 
				<c:if test="${fn:length(symptomsList) eq 0}">
					<tr>
						<td colspan="4">
							<b>Systematic Examination Findings not found</b>
						</td>
					</tr>
					</c:if>   
		   </table>
		</td></tr>
		
<tr><td>
    <table width="100%">
 		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Investigations</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.investRemarks}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Diagnosed By</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.docType}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Doctor Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.docName}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Type</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.patientType}</td>
				</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>History of present illness</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.presentIllness}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Investigations during Hospitalisation</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${PatientOpList.investRemarks}</td>
				</tr>
	</table>
	</td></tr>
<!-- History of past illness -->
<tr><td  colspan="4"  style="text-align:left;" class="tbheader print_heading"><strong>History of Past Illness</strong></td></tr>
		<tr><td  colspan="4" class="tbcellBorder print_cell">
		   		<table border="0" width="100%">
							
									<c:set var="loopCount1" value="0" />
									<c:forEach  items="${pastHistoryList}" varStatus="loop">								
									<c:set value="${pastHistoryList[loop.index].ID}" var="sample"></c:set>	
									<c:forTokens var="tokenName" items="${PatientOpList.pastIllness}" delims="~" varStatus="status" begin="0">
									<c:choose>
									<c:when test="${tokenName == sample}">

									<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
									<c:if test="${(loopCount1 % 4) eq 1}">
										<tr>
										</c:if>		
											<td  class="tbcellBorder print_cell" align="left">
											${loopCount1}. &nbsp;<c:out value="${pastHistoryList[loop.index].VALUE}"/>
												
												<c:if test="${tokenName == 'PH11' }">
												${PatientOpList.pastIllenesOthr}
											   <%-- <input type="text" id="${pastHistoryList[loop.index].ID}" name="${PatientOpList.pastIllenesOthr}" value="${PatientOpList.pastIllenesOthr}" disabled="disabled"/> --%>
											   </c:if>
											   <c:if test="${tokenName == 'PH8' }">
												( ${PatientOpList.pastIllenesCancers})
											   </c:if>
											   <c:if test="${tokenName == 'PH10' }">
												( ${PatientOpList.pastIllenesSurg})
											   </c:if>
											   <c:if test="${tokenName == 'PH12' }">
												( ${PatientOpList.pastIllenesEndDis})
											   </c:if>
											  </td>
									  <c:if test="${(loopCount1 % 4) eq 1}">
										
										</c:if>	
									</c:when>
									</c:choose>  
									</c:forTokens>     	
									</c:forEach>
									
								
							<c:if test="${fn:length(PatientOpList.pastIllness) eq 0}">
							<tr><td align="center" class="tbcellBorder print_cell">History of past illness not found</td></tr>
							</c:if>
								 
						</table>
		</td></tr>
<!-- Associated Comorbidity Condition, if any -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Associated Comorbidity Condition, if any</strong></td></tr>
<tr><td>
	<table width="100%">
	    <c:set var="loopCount1" value="0" />
							<c:forEach items="${comorbidDtls}" var="com" >
								<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
								<c:if test="${(loopCount1 % 2) eq 1}">
								<tr>
								</c:if>	
								<td  class="tbcellBorder print_cell" align="left">
										&nbsp;${loopCount1}. &nbsp;${com.ID} (${com.VALUE})
								</td>
								<c:if test="${(loopCount1 % 2) eq 1}">
								</tr>
								</c:if>
							</c:forEach>
							<c:if test="${fn:length(comorbidDtls) eq 0}">
							<tr><td align="center" class="tbcellBorder print_cell">No Comorbidity Conditions added for this case</td></tr>
								
							</c:if>		
	</table>
</td></tr>
<!--ICD9 Code for Comorbidity Condition  -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>ICD9 Code for Comorbidity Condition</strong></td></tr>
<tr><td>
    <table width="100%">
    	<c:forEach items="${comorbidDtls}" var="com" >
								<c:set var="loopCount2" value="${loopCount2 + 1}" /> 
								<tr>
								
								<td  class="tbcellBorder print_cell" align="left">
										&nbsp;${loopCount2}. &nbsp;${com.CONST}
								</td>
								
								</tr>
								
							</c:forEach>
							<c:if test="${fn:length(comorbidDtls) eq 0}">
							<tr><td align="center" class="tbcellBorder print_cell">No Comorbidity Conditions added for this case</td></tr>							
							</c:if>
    
    </table>
</td></tr>
<!-- Diagnosis -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Diagnosis</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Diagnosis Type</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.diagnosisType}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Main Category Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.mainCatName}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Category Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.catName}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Sub Category Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.subCatName}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Disease Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.disName}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Disease Anatomical Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.disAnatomicalSitename}</td>
				</tr>
		</table>
		</td></tr>
<!--ICD10 Diagnosis Code-->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>ICD10 Diagnosis Code</strong></td></tr>
		<tr><td>
		   <table width="100%">
		   		<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Diagnosis Type</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.CARDNO}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Main Category</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.cardType}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Category</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.CASESTAT}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Sub Category</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.HOSPNAME}</td>
				</tr>
				<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Disease Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.HOSPADDR}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Disease Anatomical Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${otherDtls.ADMTYPE}</td>
				</tr>
		</table>
		</td></tr>
<!-- Plan of Treatment(Surgical) -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>Plan of Treatment(Surgical)</strong></td></tr>
<tr><td>
 <table width="100%">
		   		 <tr>
						<td style="width:20%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Category Name</strong></td>
						<td style="width:20%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>ICD Category Name</strong></td>
						<td style="width:20%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Procedure Name</strong></td>
						<td style="width:40%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Remarks</strong></td>
				</tr>
					<c:if test="${fn:length(surgeryDtls) > 0}">
								<c:set var="procNamesList" value="" />
									<c:forEach items="${surgeryDtls}" var="com" >
									<tr>
										<td class="tbcellBorder print_cell"  style="word-wrap:break-word;">${com.asriCatName} (${com.catId})</td>
										<td class="tbcellBorder print_cell"  style="word-wrap:break-word;">${com.catName}</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;">${com.procName} (${com.icdProcCode})</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >${com.investRemarks}</td>
									 
									</tr>
									</c:forEach>
							</c:if>
							<c:if test="${fn:length(surgeryDtls) eq 0}">
							<tr>
								<td align="center" class="tbcellBorder print_cell">Plan of Treatment(Surgical) not found</td>
							</tr>
				</c:if>
				
</table>
</td></tr>
<!-- ICD9 Procedure Code -->
<tr><td style="text-align:left;" class="tbheader print_heading"><strong>ICD9 Procedure Code</strong></td></tr>
<tr><td>
	<table width="100%">
			<c:forEach items="${surgeryDtls}" var="com" varStatus="x">
				<tr>
					<td class="tbcellBorder print_cell" align="left">
							${x.count}.&nbsp; ${com.procName}&nbsp; (${com.icdProcCode})
					</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(surgeryDtls) eq 0}">
			<tr>
				<td  align="center" class="tbcellBorder print_cell"><b>Procedure Details not found</b></td>
			</tr>
			</c:if>
	</table>
</td></tr>
<!-- tratment given -->
<tr><td>
   <table width="100%">
   		<tr>
				<td width="30%" class="labelheading1 tbcellCss print_cell"><strong>Treatment Given</strong></td>
				<td width="70%" class="tbcellBorder print_cell">${discharge.ACTION}</td>
			</tr>
			<tr>
				<td width="30%" class="labelheading1 tbcellCss print_cell"><strong>Status at the time of discharge</strong></td>
				<td width="70%" class="tbcellBorder print_cell">${discharge.ATTACH}</td>
			</tr>
			<tr>
				<td width="30%" class="labelheading1 tbcellCss print_cell"><strong>Advice on Discharge</strong></td>
				<td width="70%" class="tbcellBorder print_cell">${discharge.COMPON}</td>
			</tr>
			<tr>
				<td width="30%" class="labelheading1 tbcellCss print_cell"><strong>Summary of cause of death in case of Mortality</strong></td>
				<td width="70%" class="tbcellBorder print_cell">${discharge.COMPROLE}</td>
			</tr>
   </table>
</td></tr>
<!-- To hide Signatures part if form opens from Cases Search -->
<c:if test="${decFlag ne 'N'}">
<!-- signatures -->
<tr><td>
		  <table width="100%">
		  	
		  		<tr>
				<td style="width:5%" class="tbheader1  print_cell"><strong> S.No</strong></td>
				<td style="width:15%" class="tbheader1 print_cell"><strong> Designation</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong> Name</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong>Signature/ Thumb impression</strong></td>
			</tr>
			<%int count=1; %>
			<c:if test="${fn:length(discharge.COMPDESC) gt 0}">			
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px"><% out.println(count); count++;%></td>
				<td width="15%" class="tbcellCss print_cell"  height="50px">Patient Name</td>
				<td width="40%" class="tbcellCss print_cell" height="50px">${PatientOpList.patientName}</td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
			</c:if>
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px"><% out.println(count); count++;%></td>
				<td width="15%" class="tbcellCss print_cell" height="50px">Treating Doctor Name</td>
				<td width="40%" class="tbcellCss print_cell" height="50px">${SurgDtls.surgeryName}</td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px"><% out.println(count); count++;%></td>
				<td width="15%" class="tbcellCss print_cell" height="50px">MEDCO Name</td>
				<td width="40%" class="tbcellCss print_cell" height="50px">${usrDtls.VALUE}</td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px"><% out.println(count); %></td>
				<td width="15%" class="tbcellCss print_cell" height="50px"><c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201' }" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya Mithra
							</c:otherwise>
						</c:choose> Name</td>
				<td width="40%" class="tbcellCss print_cell" height="50px">${hospDtls.EMPNAME}</td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
		  
		  </table>
		</td></tr>
</c:if>
<!-- print button -->
<tr >
			<td style="text-align:center">
			<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
	
			<button class="but"  type="button" id="close" onclick="fn_close()">Close</button></td>
</tr>
</table>
	
</body>
</html>