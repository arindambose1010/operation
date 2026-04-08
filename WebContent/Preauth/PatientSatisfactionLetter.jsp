 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/include.jsp"%>

<html>
	<head>
		<title>
			Patient Satisfaction Letter
		</title>
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
		<table width="800px" style="margin:0 auto;" class="tb print_table">
			<!-- Title and address -->
			<tr>
				<td colspan="4">
					<c:if test="${scheme eq 'CD202'}">
						<%@ include file="/common/Printheader_tg.html" %>
					</c:if>
					<c:if test="${scheme ne 'CD202'}">
						<%@ include file="/common/Printheader.html" %>
					</c:if>
				</td>
			</tr>
			<!-- end of title and address -->
			<tr>
				<td colspan="4" style="height:30px;vertical-align:bottom;" align="center"><FONT size="3" style="STRONG">
					<b><u><i>Patient Satisfaction Certificate<i></u></b></FONT>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<!--Patient Details-->
			<tr align="center">
				<td colspan="4">
					<table width="800px" align="center">
						<tr>
							<td width="20%" align="left" class="labelheading1 tbcellCss print_cell">
								<strong>Name of the Patient</strong>
							</td>
							<td colspan="3" align="left" class="tbcellBorder print_cell">
								${PatientOpList.patientName}
							</td>
						</tr>
						<tr>
							<td align="left" class="labelheading1 tbcellCss print_cell">
								<strong>Age</strong>
							</td>
							<td align="left" class="tbcellBorder print_cell">
								${PatientOpList.age}
							</td>
							<td align="left" width="20%" class="labelheading1 tbcellCss print_cell">
								<strong>Gender</strong>
							</td>
							<td align="left" class="tbcellBorder print_cell">
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
						</tr>
						<tr>
							<td align="left" class="labelheading1 tbcellCss print_cell">
								<strong>Health Card No</strong>
							</td>
							<td align="left" class="tbcellBorder print_cell">
								${PatientOpList.rationCard}
							</td>
							<td align="left" class="labelheading1 tbcellCss print_cell">
								<strong>Hospital Name</strong>
							</td>
							<td align="left" class="tbcellBorder print_cell">
								${hospDtls.ID}
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td colspan="4">
					<table width="800px" align="center">
						<tr>
							<td align="left" colspan="4">
								I <b>${PatientOpList.patientName}</b> hereby declare that, I have undergone treatment of 
								<c:forEach items="${surgeryDtls}" var="com">
									<b>${com.procName}&nbsp; (${com.icdProcCode})</b>
								</c:forEach>
								under 
								<c:forEach items="${surgeryDtls}" var="com">
									<b>${com.asriCatName}</b>
								</c:forEach>
								for <b>Rs. ${totPckgAmt}</b> at <b>${hospDtls.ID}, ${hospDtls.LVL}</b> under Employees
								Health Scheme. I have not paid any amount for Tests, Investigations & Drugs to the
								Network Hospital during the period of my treatment. I am satisfied with the services
								provided by the Network Hospital during my stay in the hospital.
							</td>
						</tr>
						<tr>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="left" width="28%" class="labelheading1 tbcellCss print_cell" style="height:60px;vertical-align:middle;">
								<strong>Suggestions/Remarks, if any:</strong>
							</td>
							<td colspan="3" align="left" class="tbcellBorder print_cell">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%">
									<tr>
										<td width="5%" class="tbheader1  print_cell"><strong>Sr.No</strong></td>
										<td width="15%" class="tbheader1 print_cell"><strong> Designation</strong></td>
										<td width="30%" class="tbheader1 print_cell"><strong> Name</strong></td>
										<td width="50%" class="tbheader1 print_cell"><strong>Signature/ Thumb impression</strong></td>
									</tr>
									<tr>
										<td width="5%" align="center" class="tbcellCss print_cell" height="50px">1</td>
										<td width="15%" align="center" class="tbcellCss print_cell"  height="50px">Patient</td>
										<td width="30%" align="center" class="tbcellCss print_cell" height="50px">${PatientOpList.patientName}</td>
										<td width="50%" align="center" class="tbcellBorder print_cell" height="50px"></td>
									</tr>

									<tr>
										<td width="5%" align="center" class="tbcellCss print_cell" height="50px">2</td>
										<td width="15%" align="center" class="tbcellCss print_cell" height="50px"><c:choose>
														<c:when test="${scheme eq 'CD201' }" >
															Vaidya Mithra
														</c:when>
														<c:otherwise >
															Aarogya Mithra
														</c:otherwise>
													</c:choose></td>
										<td width="30%" align="center" class="tbcellCss print_cell" height="50px">${hospDtls.EMPNAME}</td>
										<td width="50%" align="center" class="tbcellBorder print_cell" height="50px"></td>
									</tr>
									<tr>
										<td width="5%" align="center" class="tbcellCss print_cell" height="50px">3</td>
										<td width="15%" align="center" class="tbcellCss print_cell" height="50px">MEDCO</td>
										<td width="30%" align="center" class="tbcellCss print_cell" height="50px">${usrDtls.VALUE}</td>
										<td width="50%" align="center" class="tbcellBorder print_cell" height="50px"></td>
									</tr>
								  
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="left" width="15%" class="labelheading1 tbcellCss print_cell">
								<strong>Date</strong>
							</td>
							<td align="left" width="33%" class="tbcellBorder print_cell">
								${lStrCurentDtTime}
							</td>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
						
					</table>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr class="print_buttons" align="center">
				<td colspan="4" style="text-align:center">
					<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
				</td>
			</tr>
		</table>
	</body>
</html>