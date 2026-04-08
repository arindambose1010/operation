<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.ahct.common.vo.LabelBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/include.jsp"%>

<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS::Outpatient Form</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>

<script language="javascript" type="text/javascript">
	function fn_Print() {
		window.print();
	}
	function setAllergiesAndHabbits() {
		var allergies='', habbits='';
		var alg=false;
		var algsub=false;
		var addition = '${allDtls.c_relationshipof_code}';
		var additionKnown = '${allDtls.c_pin_code}';
		addition = addition.replace("PR6.1(", "");
		additionKnown = additionKnown.replace("PR5.1,", "");
		var additionList = addition.split(",");
		var addKnownList = additionKnown.split(",");
		if (addKnownList.length > 0) {
			for ( var i = 0; i < addKnownList.length; i++) {
				var addtn = addKnownList[i].split("$");
				if (addtn[0] == 'AllMed') {
					allergies=allergies+' <b>Allergic to Medicines:</b>';
					var spitedY = addtn[1].split("(");
					if (spitedY[0] == 'AllMedYes') {
						allergies+='Yes <br/>';
						var valueY = spitedY[1].split("@");
						allergies+='&nbsp;&nbsp;&nbsp;&nbsp;<b>Specifically:</b>';
						allergies+=valueY[1]===''?'none':valueY[1];
					} else if (addtn[1] == 'AllMedNo') {
						allergies=allergies+' No ';
					}
					continue;
				}
				else
					alg=true;
				allergies+='<br/>';
				if (addtn[0] == 'AllSub') {
					allergies=allergies+'<b>Allergic Substance:</b>';
					var spitedY = addtn[1].split("(");
					if (spitedY[0] == 'AllSubYes') {
						allergies+=' Yes <br/>';
						var valueY = spitedY[1].split("@");
						allergies+='&nbsp;&nbsp;&nbsp;&nbsp;<b>Specifically:</b>';
						allergies+=valueY[1]===''?'none':valueY[1];
					} else if (addtn[1] == 'AllSubNo') {
						allergies=allergies+' No ';
					}
					continue;
				}
				else
					algsub=true;
			}
		}
		if(alg && algsub)
			allergies='<b>Allergic to Medicines:</b> N.A. <br/> <b>Allergic to Substance:</b> N.A. ';
		if (additionList.length > 0) {
			for ( var i = 0; i < additionList.length; i++) {
				var addtn = additionList[i].split("$");
				if (addtn[0] == 'Alcohol') {
					if (addtn[1] == 'Regular')
						habbits=habbits+' <b>Alcohol:</b> Regular <br/>';
					else if (addtn[1] == 'Occasional')
						habbits=habbits+' <b>Alcohol:</b> Occasional <br/>';
					else if (addtn[1] == 'Teetotaler')
						habbits=habbits+' <b>Alcohol:</b> Teetotaler <br/>';
					else
						habbits=habbits+' <b>Alcohol:</b> N.A. <br/>';
				} else if (addtn[0] == 'Tobacco') {
					var tabacoLst = addtn[1].split("(");
					if (tabacoLst[0] == 'Snuff')
						habbits=habbits+' <b>Tobacco:</b> Snuff <br/>';
					else if (tabacoLst[0] == 'Chewable')
						habbits=habbits+' <b>Tobacco:</b> Chewable <br/>';
					else if (tabacoLst[0] == 'Smoking') {
						habbits=habbits+' <b>Tobacco:</b> Smoking <br/>';
						tabacoLst[1] = tabacoLst[1].replace(")", "");

						var smokSub = tabacoLst[1].split("*");

						if (smokSub.length > 0) {
							for ( var j = 0; j < smokSub.length; j++) {

								var smokeVal = smokSub[j].split("@");

								if (smokeVal[0] == 'PackNo')
									habbits=habbits+' &nbsp;&nbsp;&nbsp;<b>Packs :</b>'+smokeVal[1]+' per day ';
								else
									habbits=habbits+'  &nbsp;&nbsp;&nbsp;<b>Years :</b>'+smokeVal[1]+' years <br/> ';
							}
						}
					}
					else
						habbits=habbits+' <b>Tobacco:</b> N.A. <br/>';
				} else if (addtn[0] == 'DrugUse') {
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Drug Use :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Drug Use :</b> No <br/>';
					else
						habbits=habbits+'<b>Drug Use :</b> N.A. <br/>';
				} else if (addtn[0] == 'BetelNut') {
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Betel Nut :</b>  Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Betel Nut :</b>  No <br/> ';
					else
						habbits=habbits+'<b>Betel Nut :</b>  N.A. <br/> ';
				} else if (addtn[0] == 'BetelLeaf') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Betel Leaf :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Betel Leaf :</b> No <br/>';
					else
						habbits=habbits+'<b>Betel Leaf :</b> N.A. <br/>';
				}
				else
				{
					habbits='<b>Alcohol:</b> N.A. <br/> <b>Tobacco:</b> N.A. <br/> <b>Drug Use :</b> N.A. <br/> <b>Betel Nut :</b>  N.A. <br/> <b>Betel Leaf :</b> N.A. <br/>';
				}
			}
		}
		document.getElementById('algy').innerHTML=allergies;
		document.getElementById('hbts').innerHTML=habbits;
	}
</script>
<body onload="setAllergiesAndHabbits();">
	<form action="/patientDetails.do" method="post"
		name="caseGeneratedForm">
		<table width="90%" style="margin: 0 auto" class="tb print_table">
		<%-- <tr>
		<c:if test="${commonDtls.schemeId ne 'CD202'}">
			<td>
				<%@ include file="/common/Printheader.html" %>
			</td>
		</c:if>
		<c:if test="${commonDtls.schemeId eq 'CD202'}">
			<td>
				<%@ include file="/common/Printheader_tg.html" %>
			</td>
		</c:if>
		</tr> --%>
		<!-- <tr><th class="tbheader">OUTPATIENT TREATMENT FORM</th></tr> -->
		<tr><td style="text-align:center;" class="tbheader print_heading" ><b>Patient&nbsp;Details</b></td></tr>
		<!-- Patient details -->
		<tr><td>
		    <table width="100%">
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Health Card No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.cardNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.opDate}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.firstName}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.age}</td>

			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.gender}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.addr1}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.contactNo}</td>
			</tr>
			</table>
			</td></tr>
					<!--  Case details -->
		<tr><td style="text-align:center;" class="tbheader print_heading"><b>Case&nbsp;Details</b></td></tr>
		<tr><td>
			<table width="100%">
			
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>OP Number</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.ipNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration date and time</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.ipDate}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Case No.</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${allDtls.caseId}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.refHospNo}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address and contact</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.regHospId}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Doctor name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${allDtls.doctorName}</td>

			</tr>
		</table>
		</td></tr>
		<!-- Medical details -->
		<tr><td style="text-align:center" class="tbheader print_heading"><b>Medical&nbsp;Details</b></td></tr>
		<tr><td>
		   <table width="100%">
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>i) Main Signs and Symptoms</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.asriCatCode}</td></tr>
		      <tr><td rowspan="3" width="30%"class="labelheading1 tbcellCss print_cell"><strong>ii) Present Illness History</strong></td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>a) Complaint Type</strong></td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.asriProcCode}</td></tr>
		      	 <tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Patient Complaint</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell">${allDtls.asriDrugCode}</td></tr>
		      	<tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Present Illness History</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell">${allDtls.biometricVal}</td></tr>
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iii) Known Allergies</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='algy'></div></td></tr>
		      
		       <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iv) Habits or Addictions</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='hbts'></div></td></tr>	 
		      
		      <tr><td rowspan="5" width="30%"class="labelheading1 tbcellCss print_cell"><strong>v) Examination Findings</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> a) Temperature in (&deg;C/&deg;F</strong>)</td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.addr1}</td></tr>	
		      
		      <tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Pulse rate (/minute)</strong></td>
				<td width="30%" class="tbcellBorder print_cell">${allDtls.addr2}</td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Respiration rate (/minute)</strong></td>
				<td  width="30%" class="tbcellBorder print_cell">${allDtls.addr3}</td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>d) Blood-Pressure Lt.Arm (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell">${allDtls.admissionType}</td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>e) Blood-Pressure Rt.Arm (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell">${allDtls.age}</td>
			</tr> 
			
			 <tr><td  width="30%"class="labelheading1 tbcellCss print_cell"><strong>vi) Past illness history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history</strong></td>
		      <c:if test="${fn:length(allDtls.ageDays) ne 0}">
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.ageDays}</td>
		       </c:if>
		      <c:if test="${fn:length(allDtls.ageDays) eq 0}">
			<td class="tbcellBorder print_cell">History of past illness not found</td>
				</c:if>	
		     
		      </tr>	  
		      
		      
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>vii) Family history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history</strong></td>
		       <c:if test="${fn:length(allDtls.ageMonths) ne 0 }">
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.ageMonths}</td>
		      </c:if>
		      <c:if test="${fn:length(allDtls.ageMonths) eq 0 }">
				<td class="tbcellBorder print_cell">Family history not found</td>
				</c:if>			 
		      </tr>
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1"><strong>viii) Investigations done</strong></span></strong>
		           <table width="100%">
		              <tr>
							<th width="50%" class="tbheader1">Investigation Block Name</th>
							<th width="50%" class="tbheader1">Test name</th>
					 </tr>
					 <c:forEach items="${investList}" var="inv">
					 	<tr>
							<td  width="50%" class="tbcellBorder print_cell">${inv.test}</td>
							<td  width="50%" class="tbcellBorder print_cell">${inv.name}</td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(investList) eq 0 }">
							<tr>
							<td colspan="2" width="100%" class="tbcellBorder print_cell" align="center">No Investigations Found</td>
						</tr>
					</c:if>
		           </table>	      
		      </td></tr>	 
		   </table>
		</td></tr>
		<!--  diagnosis -->
		<tr><td style="text-align:center;" class="tbheader print_heading"><strong>Diagnosis</strong></td></tr>
		<tr><td>
		   <table width="100%">
		      <tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Diagnosis Type</b></td>
				<td  width="50%" class="tbcellBorder print_cell">${allDtls.c_relationshipof_code}</td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Main Category Name</b></td>
				<td  width="50%" class="tbcellBorder print_cell">${allDtls.c_village_code}</td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Category name</b></td>
				<td  width="50%" class="tbcellBorder print_cell">${allDtls.catName}</td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Sub-Category Name</b></td>
				<td  width="50%" class="tbcellBorder print_cell">${allDtls.subCatName}</td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Disease name</b></td>
				<td  width="50%" class="tbcellBorder print_cell">${allDtls.diseaseName}</td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Disease anatomical name</b></td>
				<td  width="50%" class="tbcellBorder print_cell">${allDtls.disAnatomicalName}</td>
			</tr>
		   </table>
		</td></tr>
		<!--  treatment details -->
		<tr><td style="text-align:center"  class="tbheader print_heading"><b>Treatment details</b></td></tr>
		<tr><td class="tbcellCss"><span class="labelheading1"><strong>i) Drug details</strong></span>
		  <table width="100%" style="table-layout:fixed;">
		  <tr>
							<td style="width:5%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Sr. No.</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Main Group Name</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Therapeutic Main Group Name</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Pharmacological SubGroup Name</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Chemical SubGroup Name</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Chemical Substance Name</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Route Type</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Route</strong></td>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Strength Type</strong></td>
							<td style="width:5%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Strength</strong></td>
							<td style="width:5%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Dosage(per day)</strong></td>
							<td style="width:5%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Medication Period(Days)</strong></td>
						</tr>
						<c:set var="counter" value="${1}"></c:set>
						<c:forEach items="${drugsList}" var="drug">
							<tr>
								<td style="width:5%;word-wrap:break-word;" class="tbcellBorder  print_cell">${counter}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.drugTypeName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.drugSubTypeName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.pSubGrpName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.cSubGrpName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.drugName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.routeTypeName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.routeName}</td>
								<td style="width:10%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.strengthTypeName}</td>
								<td style="width:5%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.strength}</td>
								<td style="width:5%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.dosage}</td>
								<td style="width:5%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.medicationPeriod}</td>
							</tr>
							<c:set var="counter" value="${counter+1}" />
						</c:forEach>
		  		
		<!-- 			<tr>
				<td colspan="4" class="tbheader1">iv) Follow up Information-</td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Next Follow Up Date:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px">Floor:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Block Name (OPD):</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px">Room:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Consultant Name:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px">Mobile Number:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss" height="50px">Designation:</td>
				<td width="35%" class="tbcellCss" height="50px"></td>
				<td width="15%" class="tbcellCss" height="50px"></td>
				<td width="35%" class="tbcellCss" height="50px"></td>
			</tr> -->
			<tr>
				<td  colspan="12" class="tbcellBorder" style="color:red;">*The Investigations/ tests were done free of
					cost.</td>
			</tr>
		  </table>
		</td></tr>
		
		</table>		
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
	</form>
</body>
	</html>
</fmt:bundle>