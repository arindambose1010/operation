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
		<c:if test="${commonDtls.patientScheme ne 'CD502'}">
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
		</c:if>
		<c:if test="${commonDtls.patientScheme eq 'CD502'}">
			<c:if test="${commonDtls.schemeId ne 'CD202'}">
				<td>
					<%@ include file="/common/PrintHeaderJouAP.html" %>
				</td>
			</c:if>
			<c:if test="${commonDtls.schemeId eq 'CD202'}">
				<td>
					<%@ include file="/common/PrintHeaderJouTG.html" %>
				</td>
			</c:if>
		</c:if>
		</tr>
		<tr><th class="tbheader">INPATIENT TREATMENT FORM</th></tr>
		<tr><td style="text-align:left;" class="tbheader print_heading" ><b>1.&nbsp;Patient&nbsp;Details</b></td></tr>
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
				<c:if test="${commonDtls.relation ne null}">
					<c:if test="${commonDtls.relation eq 'New Born Baby'}">
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Relation</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><strong>${commonDtls.relation}</strong></td>
					</c:if>	
				</c:if>
			</tr>
		</table>
		</td></tr>
		<!--  Case details -->
		<tr><td style="text-align:left;" class="tbheader print_heading"><b>2.&nbsp;Case&nbsp;Details</b></td></tr>
		<tr><td>
			<table width="100%">
			
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>IP Number</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.ipNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration date and time</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.ipDate}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Case No.</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${allDtls.caseId}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Admission type</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.admissionType}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.refHospNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address and contact</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.regHospId}</td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Doctor name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${allDtls.doctorName}</td>
			</tr>
		</table>
		</td></tr>
		<!-- Medical details -->
		<tr><td style="text-align:left" class="tbheader print_heading"><b>3.&nbsp;Medical&nbsp;Details</b></td></tr>
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
			
			 <tr><td rowspan="2" width="30%"class="labelheading1 tbcellCss print_cell"><strong>vi) Past illness history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history</strong></td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.ageDays}</td></tr>	 
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history Other</strong> </td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.c_district_code}</td></tr>
		      
		      <tr><td rowspan="2" width="30%"class="labelheading1 tbcellCss print_cell"><strong>vii) Family history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history:</strong></td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.ageMonths}</td></tr>	 
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history Other</strong> </td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.c_mandal_code}</td></tr>	
		      
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">viii) Investigations done</span></strong>
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
		
		
		<tr><td style="text-align:left"  class="tbheader print_heading"><b>5.Treatment details</b></td></tr>
		<c:if test="${ IPM eq 'Y' }">
		 <table width="100%">
		 <tr>
		 <td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Speciality</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.medicalSpclty}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Category</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.medicalCat}</td>
				</tr>
			</table>
		
		</c:if>
		<c:if test="${ IPM eq 'N' }">
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
					<td style="width:10%" class="tbheader1 print_cell"><strong>Investigations</strong></td>
					<td style="width:10%" class="tbheader1 print_cell"><strong>Doctor Name</strong></td>
				</tr>
				<c:set var="counter" value="${1}"></c:set>
				<c:forEach items="${procDtls}" var="proc">
					<tr>
						<td style="width:5%" class="tbcellBorder  print_cell"><c:out value="${counter}"/></td>
						<td style="width:10%" class="tbcellBorder print_cell">${proc.catId}</td>
						<td style="width:10%" class="tbcellBorder print_cell">${proc.asriCatName}</td>
						<td style="width:10%" class="tbcellBorder print_cell">${proc.icdCatCode}</td>
						<td style="width:10%" class="tbcellBorder print_cell">${proc.catName}</td>
						<td style="width:10%" class="tbcellBorder print_cell">${proc.icdProcCode}</td>
						<td style="width:15%" class="tbcellBorder print_cell">${proc.procName}</td>
						<c:if test="${proc.opProcUnits ne '-1' && proc.opProcUnits ne null}">
							<td style="width:10%" class="tbcellBorder print_cell">${proc.opProcUnits}</td>
						</c:if>
						<c:if test="${proc.opProcUnits eq '-1' || proc.opProcUnits eq null}">
							<td style="width:10%" class="tbcellBorder print_cell">-NA-</td>
						</c:if>	
						<td style="width:10%" class="tbcellBorder print_cell">
							<c:forEach items="${proc.lstSplInvet}" var="invest">
								${invest.filename}(${invest.therapyId}),
							</c:forEach>
						</td>
						<td style="width:10%" class="tbcellBorder print_cell">${proc.docName}</td>
					</tr>
				<c:set var="counter" value="${counter+1}"/>
				</c:forEach>				
					
				<tr>
					<td  colspan="5" class="labelheading1 tbcellCss print_cell"><b>ii)Remarks</b></td>
					<td  colspan="5" class="tbcellBorder print_cell">${allDtls.c_phc_code}</td>
				</tr>
		
			<tr>
				<td  colspan="10" class="tbcellBorder" style="color:red;">*The Investigations/ tests were done free of
					cost/ Medicines were given for free.</td>
			</tr>
		  </table>
		</td></tr>
		</c:if>
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
					<td width="40%" class="tbcellCss print_cell" height="50px">${allDtls.doctorName}</td>
					<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
				</tr>

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
					<td width="40%" class="tbcellCss print_cell" height="50px">${allDtls.msg}</td>
					<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
				</tr>
				<tr>
					<td width="5%" class="tbcellCss print_cell" height="50px">3</td>
					<td width="15%" class="tbcellCss print_cell" height="50px">Patient</td>
					<td width="40%" class="tbcellCss print_cell" height="50px">${commonDtls.firstName}</td>
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
	</form>
</body>
	</html>
</fmt:bundle>