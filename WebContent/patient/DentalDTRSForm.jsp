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
<title>EHS::Patient Diagnosis Form</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script language="javascript" type="text/javascript">
	var s$ = jQuery.noConflict();

	s$(function() {
		/*
		 * this swallows backspace keys on any non-input element.
		 * stops backspace -> back
		 */
		var rx = /INPUT|SELECT|TEXTAREA/i;

		s$(document).bind(
				"keydown keypress",
				function(e) {
					if (e.which == 8) { // 8 == backspace
						if (!rx.test(e.target.tagName) || e.target.disabled
								|| e.target.readOnly) {
							e.preventDefault();
						}
					}
				});
	});

	function fn_Print() {
		window.print();
	}
	function setAllergiesAndHabbits() {
		var allergies='', habbits='';
		var schemeId='${schemeId}';
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
		if(alg && algsub && schemeId=='CD201')
			allergies='<b>Allergic to Medicines:</b> N.A. <br/> <b>Allergic to Substance:</b> N.A. ';
			else if(alg && algsub && schemeId=='CD202')
				allergies='<b>--</b>';	
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
					else if(schemeId=='CD201')
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
					else if(schemeId=='CD201')
						habbits=habbits+' <b>Tobacco:</b> N.A. <br/>';
				} else if (addtn[0] == 'DrugUse') {
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Drug Use :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Drug Use :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Drug Use :</b> N.A. <br/>';
				} else if (addtn[0] == 'BetelNut') {
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Betel Nut :</b>  Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Betel Nut :</b>  No <br/> ';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Betel Nut :</b>  N.A. <br/> ';
				} else if (addtn[0] == 'BetelLeaf') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Betel Leaf :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Betel Leaf :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Betel Leaf :</b> N.A. <br/>';
				}
				else if(schemeId=='CD201')
				{
					habbits='<b>Alcohol:</b> N.A. <br/> <b>Tobacco:</b> N.A. <br/> <b>Drug Use :</b> N.A. <br/> <b>Betel Nut :</b>  N.A. <br/> <b>Betel Leaf :</b> N.A. <br/>';
				}
				else if(schemeId=='CD202')
				{
					habbits='<b>--</b>';
				}
			}
		}
		document.getElementById('algy').innerHTML=allergies;
		document.getElementById('hbts').innerHTML=habbits;
	}
	
	function closeNrefresh()
	{
		window.close();
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
		<tr><th class="tbheader">DIAGNOSTIC TEST REQUISITION SLIP</th></tr>
		<tr><td style="text-align:left;" class="tbheader print_heading" ><b>1.&nbsp;Patient&nbsp;Details</b></td></tr>
		<!-- Patient details -->
		<tr><td>
		    <table width="100%">
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Health Card No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="cardNo" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patientNo" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patientName" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dateIp" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="ageString" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="gender" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="addr" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="contactno" /></td>
			</tr>
			<logic:notEmpty name="patientForm" property="newBornBaby">
				<logic:equal name="patientForm" property="newBornBaby" value="Y">
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Type</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><b>New Born Baby</b></td>
						<td width="50%">&nbsp;</td>	
					</tr>
				</logic:equal>
			</logic:notEmpty>
		</table>
		</td></tr>
		<!--  Case details -->
		<tr><td style="text-align:left;" class="tbheader print_heading"><b>2.&nbsp;Case&nbsp;Details</b></td></tr>
		<tr><td>
			<table width="100%">
			
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration date and time</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dtTime" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="hospitalName" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Medco name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="doctorName" /></td>
			</tr>
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
		      <logic:notEmpty name="patientForm" property="investigationLt">
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">viii) Investigations done</span></strong>
		           <table width="100%">
		              <tr>
		                   <c:if test="${schemeId eq 'CD201' }">
							 <th width="50%" class="tbheader1">Investigation Block Name</th>  
							<th width="50%" class="tbheader1">Test name</th>
							</c:if>
							
							  <c:if test="${schemeId eq 'CD202' }">
							 
							<th width="100%" class="tbheader1">Test name</th>
							</c:if>
							
						</tr>
					  <logic:iterate id="inv" name="patientForm" property="investigationLt">
					<c:if test="${schemeId eq 'CD201' }">
					<tr>
					 <td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="TEST"/></td>  
					<td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="NAME"/></td>
					</tr>
					</c:if>
					
						<c:if test="${schemeId eq 'CD202' }">
					<tr>
					  
					<td  width="100%" class="tbcellBorder print_cell"><b><bean:write name="inv" property="NAME"/></b></td>
					</tr>
					</c:if>
					
					</logic:iterate>
		           </table>	      
		      </td></tr></logic:notEmpty>
			<logic:empty name="patientForm" property="investigationLt">
				<tr><td colspan="3" class="labelheading1 tbcellCss print_cell">
					<strong>No Investigations Added</strong>
				</td></tr>
			</logic:empty>			  
		   </table>
		</td></tr>
		<tr><td>
		<table width="100%">
			<tr>
			<td width="50%" class="tbcellBorder print_cell" height="50px">Name and Signature of MEDCO/Treating Doctor</td>
			<td width="50%" class="tbcellBorder print_cell" height="50px"><bean:write name="patientForm" property="doctorName" /></td>
			</tr>
			<tr><td class="tbcellBorder" style="color:red;" colspan="2">NOTE:  All the Tests as prescribed by the doctor will be done free of cost. </td></tr>
		</table>
		</td></tr>
		<tr class="print_buttons">
			<td align="center">
				<button class="but" type="button" id="Print" onclick="fn_Print();">Print</button>
				<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
			</td>
		</tr>		
		</table>
		<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
	</form>
</body>
	</html>
</fmt:bundle>