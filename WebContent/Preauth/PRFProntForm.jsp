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
<title>Preauth Request Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script>
function fn_Print()
{
	window.print();
}
</script>
</head>
<body>
	<form action="/preauthDetails.do" method="post" name="printPreauthForm">
		<table align="center" width="95%"  style="margin:0 auto;padding:5px;border:1px solid #f6f6f6;line-height:17px;" class="tb print_table">
			<!-- Title and address -->
			<tr>
				<td colspan="4">
					<logic:equal name="preauthDetailsForm" property="state" value="CD202">
						<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/PrintHeaderJouTG.html" %>
						</logic:equal>
						<logic:notEqual name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/Printheader_tg.html" %>
						</logic:notEqual>
					</logic:equal>
					
					<logic:notEqual name="preauthDetailsForm" property="state" value="CD202">
						
						<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/PrintHeaderJouAP.html" %>
						</logic:equal>
						<logic:notEqual name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/Printheader.html" %>
						</logic:notEqual>
					</logic:notEqual>
				</td>
			</tr>
			<!-- end of title and address -->

			<tr>
				<td colspan="4" align="center"><FONT size="2" style="STRONG">
					<b>PRE-AUTHORISATION REQUEST FORM  ${lStrCurentDtTime}</b></FONT>
				</td>
			</tr>
			<tr><td colspan="4">&nbsp;</td></tr>
			<!-- part-1 -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-1(TO BE FILLED BY THE BENEFICIARY)</b></td>
			</tr>
			<tr>
				<td align="left" class="labelheading1 tbcellCss print_cell"><strong>Name of the patient</strong> </td>
				<td class="tbcellBorder print_cell">${PatientOpList.patientName}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td class="tbcellBorder print_cell">${PatientOpList.age}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td class="tbcellBorder print_cell">
					<c:choose>
						<c:when test="${PatientOpList.gender eq 'M'}">
							Male
						</c:when>
						<c:when test="${PatientOpList.gender eq 'F'}">
							Female
						</c:when>
						<c:otherwise>
							${PatientOpList.gender}
						</c:otherwise>
					</c:choose>
				</td>
			
						<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
				<td class="labelheading1 tbcellCss print_cell"><strong>WJHS Health Card No</strong></td>
				        </logic:equal>
				        
				        <logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
				<td class="labelheading1 tbcellCss print_cell"><strong>
				
				<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
				
				
				
				Health Card No</strong></td>
				        </logic:equal>
				        
				<td class="tbcellBorder print_cell">${PatientOpList.rationCard}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>IP No</strong></td>
				<td class="tbcellBorder print_cell" >${PatientOpList.patientIPNo}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Case No</strong></td>
				<td class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="caseNo" /></td>
			</tr>
			<c:if test="${PatientOpList.relationName ne null}">
				<c:if test="${PatientOpList.relationName eq 'New Born Baby'}">
					<tr>
						<td class="labelheading1 tbcellCss print_cell"><strong>Relation</strong></td>
						<td class="tbcellBorder print_cell" ><b>${PatientOpList.relationName}</b></td>
					</tr>
				</c:if>
			</c:if>
			<!-- end of part1 -->
			<!-- postal address -->
			<tr>
				<td colspan="4"  class="tbheader print_heading" style="text-align:left"><b>POSTAL ADDRESS</b></td>
			</tr>
			<tr>
				<td  align="left" class="labelheading1 tbcellCss print_cell" width="25%"><strong>House No</strong></td>
				<td width="25%" class="tbcellBorder print_cell" >${PatientOpList.houseNo}</td>
				<td class="labelheading1 tbcellCss print_cell" width="25%"><strong>Street Name</strong></td>
				<td width="25%" class="tbcellBorder print_cell">${PatientOpList.street}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Village</strong></td>
				<td class="tbcellBorder print_cell" >${PatientOpList.village}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Mandal</strong></td>
				<td class="tbcellBorder print_cell">${PatientOpList.mandal}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>District</strong></td><td  class="tbcellBorder print_cell">${PatientOpList.district}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Pincode</strong></td><td  class="tbcellBorder print_cell">${PatientOpList.pincode}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Patient Tel.No</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.contactNo}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Mobile No</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.contactNo}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Name of the referral PHC/Hospital</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.hospitalName}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>District</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.hospDistrict}</td>
			</tr>
			<!-- end of postal address -->
			<!-- part II -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-II(TO BE FILLED BY THE HOSPITAL)-ALL COLUMNS ARE COMPULSARY</b></td>
			</tr>
			<tr>
				<td colspan="4" ><span class="labelheading1"><strong>1.Hospital Details</strong></span></td>
			</tr>
			<tr>
				<td  align="left" class="labelheading1 tbcellCss print_cell"><strong>Name of the Hospital/Nursing Home</strong></td>
				<td class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm"  property="hospitalName" /></td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Tel No</strong></td>
				<td class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm"  property="hospContactNo" /></td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td colspan="3" class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm"  property="hospAddress" /></td>
			</tr>
			
			<!--
			<tr>
				<td class="labelheading1 tbcellCss print_cell">Name of the Treating Doctor</td><td>
				<bean:write name="preauthDetailsForm"  property="docName" />
				</td>
				<td class="labelheading1 tbcellCss print_cell">Qualification</td><td>
				<bean:write name="preauthDetailsForm"  property="docQual" />
				</td>
			</tr>
			<tr>
				<!--To show Doctor Speciality -->
				<!--<td class="labelheading1 tbcellCss print_cell">Speciality</td>
				<td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docSpec" />
				</td>-->
				<!--
				<td class="labelheading1 tbcellCss print_cell">Reg No</td><td>
				<bean:write name="preauthDetailsForm"  property="docReg" />
				</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell">Tel. No</td><td ></td>
				<td class="labelheading1 tbcellCss print_cell">Mobile No</td><td >
				<bean:write name="preauthDetailsForm"  property="docMobNo" />
				</td>
			</tr>

			<tr><td>&nbsp;<br/><br/></td></tr>
			<tr><td colspan="1" class="labelheading1 tbcellCss print_cell">Signature</td><td></td><td class="labelheading1 tbcellCss print_cell">Date</td><td>${lStrCurentDt}</td></tr>

			-->


			<tr>
				<td colspan="4" ><span class="labelheading1"><strong>2.Online Case Sheet</strong></span></td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell" ><strong>(i)History of present illness</strong></td>
				<td colspan="3" class="tbcellBorder print_cell">${PatientOpList.presentIllness}</td>
			</tr>
			<!-- end of part -II -->
			<!-- History of past illness -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left" ><b>History of Past Illness</b></td>
			</tr>
			<tr>
				<td colspan="4" class="tbcellBorder print_cell">
					<table width="100%">
						<tr>
							<td colspan="2">
								<c:set var="loopCount1" value="0" />
								<c:forEach  items="${pastHistoryList}" varStatus="loop">								
									<c:set value="${pastHistoryList[loop.index].ID}" var="sample"></c:set>	
									<c:forTokens var="tokenName" items="${PatientOpList.pastIllness}" delims="~" varStatus="status" begin="0">
										<c:choose>
											<c:when test="${tokenName == sample}">

												<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
												<c:if test="${(loopCount1 % 2) eq 1}">
													<tr>
												</c:if>		
													<td width="50%" class="tbcellBorder print_cell">
														${loopCount1}. &nbsp;<c:out value="${pastHistoryList[loop.index].VALUE}"/>
														
														<c:if test="${tokenName == 'PH11' }">
															${PatientOpList.pastIllenesOthr}
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
											</c:when>
										</c:choose>  
									</c:forTokens>     	
								</c:forEach>
							</td>
						</tr>
						<c:if test="${fn:length(PatientOpList.pastIllness) eq 0}">
							<tr>
								<td style="text-align:center">History of past illness not found</td>
							</tr>
						</c:if>
					</table>

				</td>
			</tr>

			<!-- end of past history end -->
			<!-- general examination findings -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>General Examination Findings</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table border="0"  align="center" width="100%">
						<tr>
							<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Height</b></td>
							<td width="25%" class="tbcellBorder print_cell">${PatientOpList.height}</td>
							<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Weight</b></td>
							<td width="25%" class="tbcellBorder print_cell">${PatientOpList.weight}</td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"> <b>BMI</b></td>
							<td class="tbcellBorder print_cell">${PatientOpList.bmi}</td>
							<td class="labelheading1 tbcellCss print_cell" ><b>Pallor</b></td>
							<td class="tbcellBorder print_cell" >
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
								</c:choose>
							</td>
						</tr>

						<tr>
							<td class="labelheading1 tbcellCss print_cell" ><b>Cyanosis</b></td>
							<td class="tbcellBorder print_cell">
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
						 
							<td class="labelheading1 tbcellCss print_cell"><b>Clubbing of Fingers/Toes</b></td>
							<td class="tbcellBorder print_cell">
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
							<td class="labelheading1 tbcellCss print_cell"><b>Lymphadenopathy</b></td>
							<td class="tbcellBorder print_cell">
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

							<td class="labelheading1 tbcellCss print_cell"><b>Edema of Feet</b> </td>
							<td class="tbcellBorder print_cell">
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
							<td class="labelheading1 tbcellCss print_cell" ><b>Malnutrition</b></td>
							<td class="tbcellBorder print_cell">
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
							<td class="labelheading1 tbcellCss print_cell"><b>Dehydration</b></td>
							<td class="tbcellBorder print_cell">
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
							<td class="labelheading1 tbcellCss print_cell"><b>Temperature</b> </td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.temperature}</td> 
							<td class="labelheading1 tbcellCss print_cell"><b>Pulse Rate per Minute</b></td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.pulseRate}</td> 
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Respiration Rate</b></td>
							<td class="tbcellBorder print_cell"  >${PatientOpList.respirationRate}</td>
							<td class="labelheading1 tbcellCss print_cell"><b>BP Lt. Arm</b> </td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.bpLmt}</td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell" width="12%"><b>BP Rt. Arm</b></td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.bpRmt}</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of general	examination findings -->
			<!-- Systematic Examination Findings-->

			<tr>
				<td class="tbheader print_heading" colspan="4" style="text-align:left"><b>Systematic Examination Findings</b></td>
			</tr>

			<tr>
				<td colspan="4">
					<table width="100%">
						<c:if test="${fn:length(symptomsList) > 0}">
							<tr>  
								<td width="5%" class="labelheading1 tbcellCss print_cell"><b>S.No</b></td>        
								<td width="35%" class="labelheading1 tbcellCss print_cell"><b>Main Symptom Name</b></td>   
								<td width="30%" class="labelheading1 tbcellCss print_cell"><b>Sub Symptom Name</b></td>
								<td width="30%" class="labelheading1 tbcellCss print_cell"><b>Symptom Name</b></td>
							</tr>
						</c:if>
						<%int i=1; %>
						<c:forEach items="${symptomsList}" var="element"> 
							<tr>
								<td width="5%" class="tbcellBorder print_cell" ><%=i++ %></td>
								<td width="35%" class="tbcellBorder print_cell" >${element.ID}</td>
								<td width="30%" class="tbcellBorder print_cell" >${element.SUBID}</td>
								<td width="30%" class="tbcellBorder print_cell" >${element.VALUE}</td>
							</tr>
						</c:forEach>  
						<c:if test="${fn:length(symptomsList) eq 0}">
							<tr>
								<td colspan="4" align="center" class="tbcellBorder print_cell">
									<b>Systematic Examination Findings not found</b>
								</td>
							</tr>
						</c:if>
					</table>
				</td>
			</tr>
			 
			<tr>
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Investigations</b></td>
				<td class="tbcellBorder print_cell"  >${PatientOpList.investRemarks}</td>
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Patient Diagnosed By</b></td>
				<td  class="tbcellBorder print_cell" >${PatientOpList.docType}</td>
			</tr>


				<!--<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Registration No.</b></td>
				<td   >${PatientOpList.docReg}</td>
				</tr><tr>
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Qualification</b></td>
				<td   >${PatientOpList.docQual}</td>

				</tr>

				<tr>
				<td class="labelheading1 tbcellCss print_cell" width="10%"><b>Mobile No.</b></td>
				<td   >${PatientOpList.docMobNo}</td> -->
			<tr>
				<td class="labelheading1 tbcellCss print_cell" width="10%"><b>Doctor Name</b></td>
				<td class="tbcellBorder print_cell">${PatientOpList.docName}</td> 
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Patient Type</b></td>
				<td class="tbcellBorder print_cell"  >${PatientOpList.patientType}</td> 
			</tr>

			<tr>
				<td colspan="4">
					<table  border="0" width="100%" align=center>
						<tr>
							<td  colspan="6" style="text-align:left" class="tbheader print_heading" ><b> Diagnosis</b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Diagnosis Type</b></td>
							<td class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="diagnosisType" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Main Category Name</b></td><td class="tbcellBorder print_cell"  ><bean:write name="preauthDetailsForm"  property="mainCatName" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Category Name</b></td><td class="tbcellBorder print_cell"  ><bean:write name="preauthDetailsForm"  property="catName" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Sub Category Name</b></td><td  class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="subCatName" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Disease Name</b></td><td  class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="disName" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Disease Anatomical Name</b></td><td class="tbcellBorder print_cell"  ><bean:write name="preauthDetailsForm"  property="disAnatomicalSitename" /></td>
						</tr>
					</table>
				</td>
			</tr>

			<!-- plan of treatment -->
			<tr>
				<td colspan="4" style="text-align:left" class="tbheader print_heading"><b> Plan of Treatment(Surgical)</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table cellpadding="1" cellspacing="1" align="center">
						<tr>
							<td width="10%" style="word-wrap:break-word;" class="labelheading1 tbcellCss print_cell"><b>Category Name</b></td>
							<td width="13%" class="labelheading1 tbcellCss print_cell"><b>ICD Category Name</b></td>
							<td width="12%" class="labelheading1 tbcellCss print_cell"><b>Procedure Name</b></td>
							<td width="12%" class="labelheading1 tbcellCss print_cell"><b>Units</b></td>
							<td width="20%" class="labelheading1 tbcellCss print_cell" ><b>Special Investigations</b> </td>
							<td width="25%" class="labelheading1 tbcellCss print_cell"><b>Remarks</b></td>
							<td width="15%" class="labelheading1 tbcellCss print_cell"><b>Treating Doctor</b></td>
							<!--<td width="10%" class="labelheading1 tbcellCss print_cell"><b>Doctor Reg No</b></td>-->
							<!--<td width="10%" class="labelheading1 tbcellCss print_cell">Days</td>-->
						</tr>
						<logic:present name="preauthDetailsForm" property="lstPreauthVO">
							<bean:size id="caseList" name="preauthDetailsForm" property="lstPreauthVO"/>
							<logic:greaterThan value="0" name="caseList">
								<c:set var="procNamesList" value="" />
								<logic:iterate id="results" name="preauthDetailsForm" property="lstPreauthVO" indexId="index" >
									<tr id="splInvetsDataTable" style="display:true">
										<td class="tbcellBorder print_cell"  style="word-wrap:break-word;">
											<bean:write name="results" property="asriCatName" />(<bean:write name="results" property="catId" />)
										</td>
										<td class="tbcellBorder print_cell"  style="word-wrap:break-word;">
											<bean:write name="results" property="catName" />(<bean:write name="results" property="icdCatCode" />)
										</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;">
											<bean:write name="results" property="procName" />(<bean:write name="results" property="icdProcCode" />)
										</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;">
										<logic:notEqual name="results" property="opProcUnits" value="-1">
											<bean:write name="results" property="opProcUnits" />
										</logic:notEqual>
										<logic:equal name="results" property="opProcUnits" value="-1">
											-NA-
										</logic:equal>
										</td>
										<c:if test="${procNamesList ne ''}">
											<c:set var="procNamesList" value="${procNamesList} , ${results.procName}" />
										</c:if>
										<c:if test="${procNamesList eq ''}">
											<c:set var="procNamesList" value="${results.procName}" />
										</c:if>
										<bean:size id="splattachList" name="results" property="lstSplInvet" />
										<c:set var="splInvstCount" value="1" />
										<logic:greaterThan value="0" name="splattachList" >
											<td  class="tbcellBorder print_cell"  style="word-wrap:break-word;">
												<logic:iterate id="results1" name="results" property="lstSplInvet" indexId="index1" >
													<a href="javascript:javascript:fn_openAtachment('<bean:write name="results1" property="filePath" />')" >
														<bean:write name="results1" property="filename" />
													</a> 
													<c:if test="${fn:length(results.lstSplInvet) ne splInvstCount}">
														,
													</c:if>
													<c:set var="splInvstCount" value="${splInvstCount+1 }" />
												</logic:iterate>
											</td>
										</logic:greaterThan> 
										<logic:equal value="0" name="splattachList">
											<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;    -- &nbsp;</td>
										</logic:equal>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >&nbsp;&nbsp;${results.investRemarks}</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >&nbsp;&nbsp;<bean:write name="results" property="docName" /></td>
									</tr>
								</logic:iterate>
							</logic:greaterThan>
						</logic:present>
					</table>
				</td>
			</tr>
			<br/>
			<!--end of plan of treatment -->
			<!-- declaration -->
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td colspan="6">I hereby declare that I have not requested for the treatment of the same patient/treated the same patient earlier for the same procedure.
								And/or I hereby declare that this preauthorisation request is in continuation of the earlier treatment given.
						</tr>
						<tr>
							<td style="height:80px;text-align:right;vertical-align:bottom;">
								Signature of Treating Doctor with seal 
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of declaration -->
			<!-- admission and financial details -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>Admission and Financial Details</b></td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><b>Admission Type</b></td>
				<td class="tbcellBorder print_cell" align="center" >
					<logic:equal value="PLN" name="preauthDetailsForm" property="admissionType" >
						Planned
					</logic:equal>
					<logic:equal value="EMG" name="preauthDetailsForm" property="admissionType" >
						Emergency
					</logic:equal>
				</td>
				<td class="labelheading1 tbcellCss print_cell"><b>Date of Admission</b></td>
				<td align="center" class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm" property="ipRegDate"/></td>
			</tr>
			<!-- end of admission and financial details -->
			<!-- Cost Estimation Break-up -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>4.Cost Estimation Break-up</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td width="40%">Total Package amount admissible under the scheme :</td>
							<td><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></td>
						</tr>
						<tr>
							<td width="40%">Whether Telephonic Intimation given?:</td>
							<td><bean:write name="preauthDetailsForm" property="telephonicFlag" /></td>
						</tr>
						<tr style="height:80px;vertical-align:bottom;">
							<td width="40%" >Signature of Billing Head with Stamp</td>
							<td>&nbsp;</td>
							<td width="10%" style="text-align:right">Date: </td>
							<td>${lStrCurentDt}</td>
							<td></td>
						</tr>	
						<tr>
							<td colspan="4" style="text-align:justify;">
								The hospital hereby declare that this preauthorisation request is raised for the specific treatment <b> ${procNamesList} </b> of the patient <b>  ${PatientOpList.patientName} </b> with 
								
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
								
								Health Card No. <b>${PatientOpList.rationCard} </b>. 
								The hospital completely owns the responsibility for the diagnosis and treatment / procedure proposed for this patient. The hospital acknowledges that this patient is an 
								
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								Employees
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								Working Journalists
								</logic:equal> 
								Health Scheme beneficiary, hence to be evaluated and treated on cashless basis and the hospital has not charged the patient for clinical and diagnostic evaluation.  The hospital accepts that the preauthorisation given by the Insurance / Trust is for the provision of financial assistance and hence the Insurance / Trust are not responsible for the diagnosis, treatment procedure and its outcome. The hospital followed all the guidelines issued by the Trust from time to time and abides by all the clauses of MOU in raising this preauthorisation.
								The hospital did not treat this patient for the same procedure / treatment under any other scheme and did not receive any financial assistance under any other Government scheme.
							</td>
						</tr>
						<tr>
							<td style="height:80px;vertical-align:bottom;">Signature of MEDCO of the Hospital With seal</td><td>&nbsp;</td>
							<td width="10%" style="text-align:right;height:80px;vertical-align:bottom;">Date: </td><td style="height:80px;vertical-align:bottom;">${lStrCurentDt}</td><td></td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of cost estimatiom break up -->
			<!-- PART-III COUNSELLING OF THE PATIENT/GUARDIAN/ATTENDENT/RELATIVE. -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-III COUNSELLING OF THE PATIENT/GUARDIAN/ATTENDENT/RELATIVE.</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td style="text-align:justify;">I have counselled the patient / guardian / attendants / relatives about the risks and benefits in the surgery / therapy in his / her own language and attached the documents for the same. I have done the video counselling for mandatory cases / high risk cases and the same is attached in the video attachments slot.</td>
						</tr>
						<tr>
							<td style="height:40px;vertical-align:bottom;">Counselling remarks</td>
						</tr>
						<tr>
							<td style="height:40px;vertical-align:bottom;">Name of Counselling Doctor</td>
						</tr>
						<tr>
							<td>Date and Time: ${lStrCurentDtTime}</td>
							<td></td>
						</tr>
						<tr style="height:40px;vertical-align:bottom;">
							<td>Signature</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of PART-III COUNSELLING OF THE PATIENT/GUARDIAN/ATTENDENT/RELATIVE.  -->
			<!-- PART-IV Consent by beneficiary/guardian/attendant. -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-IV Consent by beneficiary/guardian/attendant.</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td colspan="2" style="text-align:justify;">
								I / We hereby declare that I am having 
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
								Health Card no.&nbsp;&nbsp;&nbsp;
								<b>${PatientOpList.rationCard}</b> &nbsp;(Village)
								<b>${PatientOpList.cardVillage}</b>&nbsp;&nbsp;(Mandal)
								<b>${PatientOpList.cardMandal}</b> &nbsp;&nbsp;District <b>${PatientOpList.cardDistrict}</b>&nbsp; by Govt of <logic:equal name="preauthDetailsForm" property="state" value="CD202">Telangana</logic:equal> <logic:notEqual name="preauthDetailsForm" property="state" value="CD202">A.P</logic:notEqual> and is presently residing at (H.No ) <b>${PatientOpList.houseNo} </b> &nbsp;(Village)
								<b> ${PatientOpList.village}</b>&nbsp;&nbsp;(Mandal)
								<b>${PatientOpList.mandal}</b> &nbsp;&nbsp;District  <b>${PatientOpList.district} </b>&nbsp;&nbsp;  .I/We have been explained by treating doctors in my own language the risks and benefits involved in the surgery/therapy and I have given consent for <b>${procNamesList}</b> procedure. I/We further state that I am not covered by any other insurance/reimbursement scheme by government.
							</td>
						</tr>
						<tr style="height:60px;vertical-align:bottom;">
							<td> Signature/Left Thumb impression of patient :</td>
							<td>Name of Patient:${PatientOpList.patientName}</td>
						</tr>
						<tr style="height:60px;vertical-align:bottom;">
							<td>  Signature/Left Thumb impression of patient relative<br />( If patient is child/if patient is unfit to sign )</td>
							<td> Name of the patient relative : </td>
						</tr>
						<tr style="height:20px;vertical-align:bottom;">
							<td> Relationship with patient : </td>
							<td > Mobile No of the relative :</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of PART-IV Consent by beneficiary/guardian/attendant. -->
			
			<!-- PART-V DECLARATION BY AAROGYAMITHRA -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-V DECLARATION BY <c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201'}" >
								VAIDYA MITHRA
							</c:when>
							<c:otherwise >
								AAROGYA MITHRA
							</c:otherwise>
						</c:choose></b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="mitDec" >
						<tr>
							<td colspan="3" style="text-align:justify;">
								The Patient Mr/Ms&nbsp;&nbsp;<b> ${PatientOpList.patientName} </b>
								admitted in&nbsp;&nbsp;&nbsp; <b><bean:write name="preauthDetailsForm"  property="hospitalName" /></b> hospital on&nbsp;<b><bean:write name="preauthDetailsForm" property="admissionDate"/></b> &nbsp; is a card holder &nbsp;with 
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
								Health Card no: <b> ${PatientOpList.rationCard} </b> &nbsp;and belonging to&nbsp;&nbsp;
								<b>${PatientOpList.cardVillage}</b> &nbsp;village&nbsp;&nbsp;<b>${PatientOpList.cardMandal}</b> &nbsp;Mandal&nbsp;&nbsp; <b>${PatientOpList.cardDistrict}</b>
								&nbsp;District. The details have been personally verified by me. I declare that the patient is on bed in the hospital <b><bean:write name="preauthDetailsForm"  property="hospitalName" /></b> and the preauthorisation request is genuine and there is no duplication.
							</td>
						</tr>
						<tr>
							<td style="height:60px;vertical-align:bottom;">Signature of <c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201'}" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya mithra
							</c:otherwise>
						</c:choose></td>
						</tr>
						<tr>
							<td>Name:${mitActorname}</td>
							<td>Code:<bean:write name="preauthDetailsForm" property="hospCode" /></td>
							<td>Date:${mitActDt}</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of PART-V DECLARATION BY AAROGYAMITHRA  -->
			
			<!-- PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor) -->
			<c:if test="${ppdView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table  id="ppdDec" >
							<tr>
								<td style="text-align:justify;">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having 
									<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
									Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the Panel Doctor to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ppdActorname}</td>
							</tr>
							<tr>
								<td>Date:${ppdActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor) -->
			
			<!-- PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor) for Rejection-->
			<c:if test="${ppdRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor)</b></td>
				</tr>
				<tr>
					<td colspan="4" >
						<table id="ppdRejDec" >
							<tr>
								<td >PRE-AUTHORIZATION REJECTED for the following Reasons:<br/> 
									${ppdRejRemarks}
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ppdRejActorname}</td>
							</tr>
							<tr>
								<td>Date:${ppdRejActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
				
			</c:if>
			<!-- end of PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor)-->
			
			<!-- PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			<c:if test="${ptdAprvView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="ptdDec" >
							<tr>
								<td style="text-align:justify;">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having 
									<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
									Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the Trust to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ptdAprvActorname}</td>
							</tr>
							<tr>
								<td>Date:${ptdAprvActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			
			<!-- PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			<c:if test="${ptdRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="ptdRejDec">
							<tr>
								<td>PRE-AUTHORIZATION REJECTED for the following Reasons:${ptdRejRemarks}</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ptdRejActorname}</td>
							</tr>
							<tr>
								<td>Date:${ptdRejActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			
			<!-- PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			<c:if test="${eoAprvView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="eoDec" >
							<tr>
								<td style="text-align:justify;">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having
									 <logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
									  Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the EO to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${eoAprvActorname}</td>
							</tr>
							<tr>
								<td>Date:${eoAprvActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			
			<!-- PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			<c:if test="${eoRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="eoRejDec" >
							<tr>
								<td >
								PRE-AUTHORIZATION REJECTED for the following Reasons:<br/> 
								${eoRejRemarks}
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${eoRejActorname}</td>
							</tr>
							<tr>
								<td>Date:${eoRejActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			
			<!-- PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->
			<c:if test="${ceoAprvView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="ceoDec" >
							<tr>
								<td style="text-align:justify">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having 
									
									<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
								
									Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the CEO to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr><td>Name:${ceoAprvActorname}</td></tr>
							<tr><td>Date:${ceoAprvActDt}</td></tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->	
			
			<!-- PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->	
			<c:if test="${ceoRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="eoRejDec" >
						<tr>
							<td>
								PRE-AUTHORIZATION REJECTED for the following Reasons:<br/> 
								${ceoRejRemarks}
							</td>
						</tr>
						<tr>
							<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
						</tr>
						<tr><td>Name:${ceoRejActorname}</td></tr>
						<tr><td>Date:${ceoRejActDt}</td>
						</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->
			<!-- Print button -->
			<tr class="print_buttons">
				<td colspan="4" style="text-align:center">
					<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>