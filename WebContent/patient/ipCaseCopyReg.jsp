<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.ahct.common.vo.LabelBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<%@ include file="/common/include.jsp"%>
<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS::Inpatient Form</title>
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
		var addition = '<bean:write name="patientForm" property="habbits" />';
		var additionKnown = '<bean:write name="patientForm" property="allergy" />';
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
						allergies+='&nbsp;&nbsp;&nbsp;&nbsp;<b>Specifically:</b>'
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
					allergies=allergies+'<b>Allergic Substance:</b>'
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
		<tr>
	<logic:notEqual name="patientForm" property="patientScheme" value="CD502">
		<logic:notEqual name="patientForm" property="scheme" value="CD202">
			<td>
					<%@ include file="/common/Printheader.html" %>
			</td>
		</logic:notEqual>
		<logic:equal name="patientForm" property="scheme" value="CD202">
			<td>
					<%@ include file="/common/Printheader_tg.html" %>
			</td>
		</logic:equal>
	</logic:notEqual>
	<logic:equal name="patientForm" property="patientScheme" value="CD502">
		<logic:notEqual name="patientForm" property="scheme" value="CD202">
			<td>
					<%@ include file="/common/PrintHeaderJouAP.html" %>
			</td>
		</logic:notEqual>
		<logic:equal name="patientForm" property="scheme" value="CD202">
			<td>
					<%@ include file="/common/PrintHeaderJouTG.html" %>
			</td>
		</logic:equal>
	</logic:equal>
		</tr>
		<tr><th class="tbheader">INPATIENT TREATMENT FORM</th></tr>
		<tr><td style="text-align:left;" class="tbheader print_heading" ><b>1.&nbsp;Patient&nbsp;Details</b></td></tr>
		<!-- Patient details -->
		<tr><td>
		    <table width="100%">
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Health Card No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="cardNo" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dateIp" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patientName" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="ageString" /></td>

			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="gender" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="addr" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="contactno" /></td>
			</tr>
		</table>
		</td></tr>
		<!--  Case details -->
		<tr><td style="text-align:left;" class="tbheader print_heading"><b>2.&nbsp;Case&nbsp;Details</b></td></tr>
		<tr><td>
			<table width="100%">
			
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>IP Number</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="ipNo" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration date and time</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dtTime" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Case No.</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="caseId" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Admission type</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="admissionType" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="hospitalName" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address and contact</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="hospaddr" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Doctor name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="doctorName" /></td>
			</tr>
<%-- 			<tr>
				<td width="15%" class="tbcellCss print_cell">Registration no:</td>
				<td width="35%" class="tbcellCss print_cell"><bean:write name="patientForm" property="docRegNo" /></td>
				<td width="15%" class="tbcellCss print_cell">Qualification:</td>
				<td width="35%" class="tbcellCss print_cell"><bean:write name="patientForm" property="docQual" /></td>
			</tr>
			<tr>
				<td width="15%" class="tbcellCss print_cell">Contact:</td>
				<td width="35%" class="tbcellCss print_cell"><bean:write name="patientForm"
						property="docMobileNo" /></td>
			</tr> --%>
		</table>
		</td></tr>
		<!-- Medical details -->
		<tr><td style="text-align:left" class="tbheader print_heading"><b>3.&nbsp;Medical&nbsp;Details</b></td></tr>
		<tr><td>
		   <table width="100%">
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>i) Main Signs and Symptoms</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="symptomName" /></td></tr>
		      <tr><td rowspan="3" width="30%"class="labelheading1 tbcellCss print_cell"><strong>ii) Present Illness History</strong></td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>a) Complaint Type</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="complType" /></td></tr>
		      	 <tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Patient Complaint</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="complaints" /></td></tr>
		      	<tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Present Illness History</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="presentHistory" /></td></tr>
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iii) Known Allergies</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='algy'></div></td></tr>
		      
		       <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iv) Habits or Addictions</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='hbts'></div></td></tr>	 
		      
		      		      
		      <c:if test="${schemeId eq 'CD201' }">
		      <tr><td rowspan="5" width="30%"class="labelheading1 tbcellCss print_cell"><strong>v) Examination Findings</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> a) Temperature in (&deg;C/&deg;F</strong>)</td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patTemp" /></td></tr>	
		      
		      <tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Pulse rate (/minute)</strong></td>
				<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patPulse" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Respiration rate (/minute)</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="respir" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>d) Blood-Pressure Lt.Arm (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bloodPrLt" /></td>
			</tr>
		
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>e) Blood-Pressure Rt.Arm (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bloodPrRt" /></td>
			</tr> 
			</c:if>
			
			
			
			
		    <c:if test="${schemeId eq 'CD202' }">
		      <tr><td rowspan="7" width="30%"class="labelheading1 tbcellCss print_cell"><strong>v) Examination Findings</strong> </td>
		      
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> a) Height</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="height" /></td></tr>	
		      
		      
		       <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> b) Weight</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="weight" /></td></tr>	
		      </tr>
		      
		       <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> c) BMI</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bmi" /></td></tr>	
		      </tr>
		      
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> d) Temperature in (&deg;C/&deg;F</strong>)</td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patTemp" /></td></tr>	
		      </tr>
		      
		      <tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>e) Pulse rate</strong></td>
				<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patPulse" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>f) Respiratory rate </strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="respir" /></td>
			</tr>
			<tr>
				<td  width="40%" class="labelheading1 tbcellCss print_cell"><strong>g) Blood-Pressure (Systolic/Diastolic) mmHg</strong></td>
				<td  width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bloodPrLt" /></td>
			</tr>
		
		 
			</c:if>
			 <tr><td rowspan="2" width="30%"class="labelheading1 tbcellCss print_cell"><strong>vi) Past illness history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="pastIllnessValue" /></td></tr>	 
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history Other</strong> </td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="pastHistryOthr" /></td></tr>
		      
		      <tr><td rowspan="2" width="30%"class="labelheading1 tbcellCss print_cell"><strong>vii) Family history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history:</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="familyHistValue" /></td></tr>	 
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history Other</strong> </td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="familyHistoryOthr" /></td></tr>	
		      
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">viii) Investigations done</span></strong>
		           <table width="100%">
		              <tr>
							<th width="50%" class="tbheader1">Investigation Block Name</th>
							<th width="50%" class="tbheader1">Test name</th>
						</tr>
					  <logic:iterate id="inv" name="patientForm" property="investigationLt">
					<tr>
					<td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="TEST"/></td>
					<td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="NAME"/></td>
					</tr>
					</logic:iterate>
		           </table>	      
		      </td></tr>	 
		   </table>
		</td></tr>
		<!--  diagnosis -->
		<tr><td style="text-align:left;" class="tbheader print_heading"><strong>4.&nbsp;Diagnosis</strong></td></tr>
		<tr><td>
		   <table width="100%">
		      <tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Diagnosis Type</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="diagType" /></td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Main Category Name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="mainCatName" /></td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Category name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="catName" /></td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Sub-Category Name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="subCatName" /></td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Disease name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="diseaseName" /></td>
			</tr>
			<tr>
				<td  width="50%" class="labelheading1 tbcellCss print_cell"><b>Disease anatomical name</b></td>
				<td  width="50%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="disAnatomicalName" /></td>
			</tr>
		   </table>
		</td></tr>
		<!--  treatment details -->
		<tr><td style="text-align:left"  class="tbheader print_heading"><b>5.Treatment details</b></td></tr>
		<tr><td class="tbcellCss"><span class="labelheading1"><strong>i) Procedure details</strong></span>
		  <table width="100%">
		  		<tr>
							<td style="width:5%" class="tbheader1 print_cell"><strong>Sr.No.</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>Category Code</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>Category Name</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>ICD Category Code</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>ICD Category Name</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>ICD Procedure Code</strong></td>
							<td style="width:15%" class="tbheader1 print_cell"><strong>ICD Procedure Name</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>Units</strong></td>
							<!-- <td style="width:10%" class="tbheader1 print_cell">Procedure Date and time</td> -->
							<td style="width:10%" class="tbheader1 print_cell"><strong>Investigations</strong></td>
							<td style="width:10%" class="tbheader1 print_cell"><strong>Doctor Name</strong></td>
						</tr>
						<c:set var="counter" value="${1}"></c:set>
							<nested:iterate id="proc" name="patientForm" property="procList">
								<tr>
									<td style="width:5%" class="tbcellBorder  print_cell"><c:out value="${counter}"/></td>
									<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="catId"/></td>
									<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="asriCatName"/></td>
									<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="icdCatCode"/></td>
									<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="catName"/></td>
									<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="icdProcCode"/></td>
									<td style="width:15%" class="tbcellBorder print_cell"><bean:write name="proc" property="procName"/></td>
									<c:if test="${proc.opProcUnits ne '-1' && proc.opProcUnits ne null}">
										<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="opProcUnits" /></td>
									</c:if>
									<c:if test="${proc.opProcUnits eq '-1' || proc.opProcUnits eq null}">
										<td style="width:10%" class="tbcellBorder print_cell">-NA-</td>
									</c:if>	
									<!-- <td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="date"/></td> -->
									<td style="width:10%" class="tbcellBorder print_cell">
										<nested:iterate id="invest" name="proc" property="lstSplInvet">
											<bean:write name="invest" property="filename"/>(<bean:write name="invest" property="therapyId"/>),
										</nested:iterate>
									</td>
									<td style="width:10%" class="tbcellBorder print_cell"><bean:write name="proc" property="docName"/></td>
								</tr>
								<c:set var="counter" value="${counter+1}"/>
					</nested:iterate>
				<tr>
				<td  colspan="5" class="labelheading1 tbcellCss print_cell"><b>ii)Remarks</b></td>
				<td  colspan="4" class="tbcellBorder print_cell"><bean:write name="patientForm" property="remarks" /></td>
		</tr>
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
				<td  colspan="9" class="tbcellBorder" style="color:red;">*The Investigations/ tests were done free of
					cost/ Medicines were given for free.</td>
			</tr>
		  </table>
		</td></tr>
		<!--Declaration should not be displayed if the page is opened from past history or employee/pensioners login -->
		<c:if test="${decFlag ne 'N'}">
		<!-- Declaration -->
		<tr>
				<td  style="text-align:left;" class="tbheader print_heading"><b>6.Declaration by MEDCO/ TREATING DOCTOR</b></td>
			</tr>
			<tr>
				<td  style="text-align:justify;line-height:20px;"class="tbcellBorder print_cell">The procedure is done with informed written
					consent of the patient/ guardian. The patient is examined by the
					consultant and follow up advice is given as per the prescription of
					the treating doctor in accordance to the standard medical
					practices. The prescribed medicines are essential for the follow up
					treatment and the validity of the expiry date is ascertained before
					the medicines are given to the patient. The patient has been
					properly advised about the post-discharge care including the
					dosage, timing, duration of medication and precautions to be taken
					regarding the complications/ side effects that are likely to occur.
					He/ She is further advised to report immediately in case of
					experiencing any complication/ side effect/ adverse reactions.</td>
			</tr>
		<tr><td>
		  <table width="100%">
		  		<tr>
				<td style="width:5%" class="tbheader1  print_cell"><strong>Sr.No</strong></td>
				<td style="width:15%" class="tbheader1 print_cell"><strong> Designation</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong> Name</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong>Signature/ Thumb impression</strong></td>
			</tr>
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">1</td>
				<td width="15%" class="tbcellCss print_cell"  height="50px">MEDCO</td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="patientForm" property="doctorName" /></td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
<!-- 			<tr>
				<td width="5%" class="tbcellCss" height="50px">2</td>
				<td width="15%" class="tbcellCss" height="50px">Treating doctor</td>
				<td width="40%" class="tbcellCss" height="50px"></td>
				<td width="40%" class="tbcellCss" height="50px"></td>
			</tr>
			<tr>
				<td width="5%" class="tbcellCss" height="50px">3</td>
				<td width="15%" class="tbcellCss" height="50px">Dispensary in-charge</td>
				<td width="40%" class="tbcellCss" height="50px"></td>
				<td width="40%" class="tbcellCss" height="50px"></td>
			</tr> -->
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">2</td>
				<td width="15%" class="tbcellCss print_cell" height="50px"><c:choose>
							<c:when test="${caseSchemeId eq 'CD201'}" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya Mithra
							</c:otherwise>
						</c:choose></td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="patientForm" property="mithra" /></td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">3</td>
				<td width="15%" class="tbcellCss print_cell" height="50px">Patient</td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="patientForm" property="patientName" /></td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
		  
		  </table>
		</td></tr>
		</c:if>
		<tr class="print_buttons">
				<td align="center">
					<button class="but" type="button" id="Print" onclick="fn_Print();">Print</button>
				</td>
			</tr>
		</table>
		<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
	</form>
</body>
	</html>
</fmt:bundle>