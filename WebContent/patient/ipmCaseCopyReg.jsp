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
<title>EHS::Inpatient Form</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>


<script language="javascript" type="text/javascript">

var s$ = jQuery.noConflict();

s$(function() {
	/*
	 * this swallows backspace keys on any non-input element.
	 * stops backspace -> back
	 */
	 s$('input[name=probeDepth]').prop('disabled','disabled');
	 s$('input[name=childCaries]').prop('disabled','disabled');
	 s$('input[name=childMissing]').prop('disabled','disabled');
	 s$('input[name=grosslyDecayed]').prop('disabled','disabled');
	 s$('input[name=childMobile]').prop('disabled','disabled');
	 s$('input[name=caries]').prop('disabled','disabled');
	 s$('input[name=decayed]').prop('disabled','disabled');
	 s$('input[name=mobile]').prop('disabled','disabled');
	 s$('input[name=attrition]').prop('disabled','disabled');
	 s$('input[name=missing]').prop('disabled','disabled');
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

		if (additionList.length > 0 && additionList!='PR6.2') {
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
				} else if (addtn[0] == 'PanChewing') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Pan Chewing :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Pan Chewing :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Pan Chewing :</b> N.A. <br/>';
				}
				else if (addtn[0] == 'Gutka') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Gutka :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Gutka :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Gutka :</b> N.A. <br/>';
				}
				else if (addtn[0] == 'FingerSucking') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Finger Sucking :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Finger Sucking :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Finger Sucking :</b> N.A. <br/>';
				}
				else if (addtn[0] == 'NailBiting') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Nail Biting :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Nail Biting :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Nail Biting :</b> N.A. <br/>';
				}
				else if (addtn[0] == 'TongueBiting') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Tongue Biting :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Tongue Biting :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Tongue Biting :</b> N.A. <br/>';
				}
				else if (addtn[0] == 'MouthBreathing') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Mouth Breathing :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Mouth Breathing :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Mouth Breathing :</b> N.A. <br/>';
				}
				else if (addtn[0] == 'Bruxism') {
					addtn[1] = addtn[1].replace(")", "");
					if (addtn[1] == 'Yes')
						habbits=habbits+'<b>Bruxism :</b> Yes <br/>';
					else if (addtn[1] == 'No')
						habbits=habbits+'<b>Bruxism :</b> No <br/>';
					else if(schemeId=='CD201')
						habbits=habbits+'<b>Bruxism :</b> N.A. <br/>';
				}
//				else
//				{
//					habbits='<b>Alcohol:</b> N.A. <br/> <b>Tobacco:</b> N.A. <br/> <b>Drug Use :</b> N.A. <br/> <b>Betel Nut :</b>  N.A. <br/> <b>Betel Leaf :</b> N.A. <br/>';
//				}
			}
		}
		else
				{
					habbits='N.A.';
				}
		document.getElementById('algy').innerHTML=allergies;
		document.getElementById('hbts').innerHTML=habbits;
		
		//deciduos teeth retriving
// 		var decDenSelArr=[];
		var carries='${deciduousdentdtls.carriesdecidous}';
		if(carries!=null && carries!="")
			{
// 			decDenSelArr.push("CH87");
// 			document.forms[0].deciduousDent.value="CH87";
// 			document.getElementById("cariesDecdious").style.display="";
		var carriesdeciocus=carries.split('~');
		for(var i=0 ; i<carriesdeciocus.length;i++)
		{
			s$('input[type=checkbox][value='+carriesdeciocus[i]+']').prop('checked',true);
		}
			}
		
		var grosslydecade='${deciduousdentdtls.grosslydecadedecidous}';
		if(grosslydecade!=null && grosslydecade!="")
			{
// 			decDenSelArr.push("CH88");
// 			document.forms[0].deciduousDent.value="CH88";
// 			document.getElementById("grosslyDecdious").style.display="";
		var grosslydecadedecidous=grosslydecade.split('~');
		for(var i=0 ; i<grosslydecadedecidous.length;i++)
		{
			s$('input[type=checkbox][value='+grosslydecadedecidous[i]+']').prop('checked',true);
		}
			}
		
		var mobiled='${deciduousdentdtls.mobiledecidous}';
		if(mobiled!=null && mobiled!="")
			{
// 			decDenSelArr.push("CH89");
// 			document.forms[0].deciduousDent.value="CH89";
// 			document.getElementById("mobileDecdious").style.display="";
		var mobiledecidous=mobiled.split('~');
		for(var i=0 ; i<mobiledecidous.length;i++)
		{
			s$('input[type=checkbox][value='+mobiledecidous[i]+']').prop('checked',true);
		}
			}
		
		var missingdes='${deciduousdentdtls.missingdecidous}';
		if(missingdes!=null && missingdes!="")
			{
// 			decDenSelArr.push("CH90");
// 			document.forms[0].deciduousDent.value="CH90";
// 			document.getElementById("missingDecdious").style.display="";
		var missingdecidous=missingdes.split('~');
		for(var i=0 ; i<missingdecidous.length;i++)
		{
			s$('input[type=checkbox][value='+missingdecidous[i]+']').prop('checked',true);
		}
			}
		
		
		// permanent  dentattion retreving
		
// 		var perDenSelArr=[];
		var carriesper='${deciduousdentdtls.carriespermanent}';
		if(carriesper!=null && carriesper!="")
			{
// 			perDenSelArr.push("CH96");
// 			document.forms[0].permanentDent.value="CH96";
// 			document.forms[0].permanentDent.value.selected=true;
// 			document.getElementById("cariesDiv").style.display="";
		var carriespermanent=carriesper.split('~');
		for(var i=0 ; i<carriespermanent.length;i++)
		{
			s$('input[type=checkbox][value='+carriespermanent[i]+']').prop('checked',true);
		}
			}
		
		var rootstumpper='${deciduousdentdtls.rootstumppermannet}';
		if(rootstumpper!=null && rootstumpper!="")
			{
// 			perDenSelArr.push("CH92");
// 			document.forms[0].permanentDent.value="CH92";
// 			document.getElementById("rootDiv").style.display="";
		var rootstumppermannet=rootstumpper.split('~');
		for(var i=0 ; i<rootstumppermannet.length;i++)
		{
			s$('input[type=checkbox][value='+rootstumppermannet[i]+']').prop('checked',true);
		}
			}
		
		var mobilityper='${deciduousdentdtls.mobilitypermanent}';
		if(mobilityper!=null && mobilityper!="")
			{
// 			perDenSelArr.push("CH93");
// 			document.forms[0].permanentDent.value="CH93";
// 			document.getElementById("mobilityDiv").style.display="";
		var mobilitypermanent=mobilityper.split('~');
		for(var i=0 ; i<mobilitypermanent.length;i++)
		{
			s$('input[type=checkbox][value='+mobilitypermanent[i]+']').prop('checked',true);
		}
			}
		
		var attritionper='${deciduousdentdtls.attritionpermanent}';
		if(attritionper!=null && attritionper!="")
			{
// 			perDenSelArr.push("CH94");
// 			document.forms[0].permanentDent.value="CH94";
// 			document.getElementById("attritionDiv").style.display="";
		var attritionpermanent=attritionper.split('~');
		for(var i=0 ; i<attritionpermanent.length;i++)
		{
			s$('input[type=checkbox][value='+attritionpermanent[i]+']').prop('checked',true);
		}
			}
		
		var missingper='${deciduousdentdtls.missingpermanent}';
		if(missingper!=null && missingper!="")
			{
// 			perDenSelArr.push("CH95");
// 			document.forms[0].permanentDent.value="CH95";
// 			document.getElementById("missingDiv").style.display="";
		var missingpermanent=missingper.split('~');
		for(var i=0 ; i<missingpermanent.length;i++)
		{
			s$('input[type=checkbox][value='+missingpermanent[i]+']').prop('checked',true);
		}
			}
		
		 //permanent other dentation 
		var otherper='${deciduousdentdtls.otherpermanent}';
		if(otherper!=null && otherper!="" && otherper!="-1~")
			{
// 			perDenSelArr.push("CH91");
			var otherpermanent=otherper.split('~');
			if(otherpermanent[0]!=null)
				{
// 				document.forms[0].permanentDent.value="CH91";
// 			document.getElementById("otherDiv").style.display="";
// 			document.getElementById("otherPermTextDiv").style.display="";
			var otherVal="";
			if(otherpermanent[0]=="CH104"){
				otherVal="Non Vital";
			}
			if(otherpermanent[0]=="CH103"){
				otherVal="RCT Treated";
			}

			if(otherpermanent[0]=="CH102"){
				otherVal="Retained";
			}

			if(otherpermanent[0]=="CH105"){
				otherVal="Impacted";
			}
 			document.getElementById("otherPermntDent").textContent=otherVal;
 			document.getElementById("otherPermText").innerHTML=otherpermanent[1];
				}
				
			}
		
		var probingdepth='${deciduousdentdtls.probingdepth}';
		if(probingdepth!=null && probingdepth!="")
			{
			var probingids=probingdepth.split('~');
			for(var i=0 ; i<probingids.length ; i++)
				{
				var probingdepthval=probingids[i].split('@');
				
				if(probingdepthval!=null && probingdepthval!="")
				document.getElementById(probingdepthval[0]).value=probingdepthval[1];
				}
			}
		
		
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
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="opNo" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration date and time</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dtTime" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Case No.</strong></td>
				<td width="35%" class="tbcellBorder print_cell">
					<c:if test="${empty highEndOncologyCase || (not empty highEndOncologyCase && highEndOncologyCase eq 'P')}">
						<bean:write name="patientForm" property="caseId" />
					</c:if>
					&nbsp;
				</td>
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
		    
		      
		       <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong> c) BMI</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="bmi" /></td></tr>	
		     
		      
		      
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
		      
		      
		      <c:if test="${dentalOrNot eq 'yes'}">

				<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>vii) Drug history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="drughistory" /></td></tr>
		      
		      <tr><td rowspan="2" width="30%"class="labelheading1 tbcellCss print_cell"><strong>vii) Medical history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Medical history:</strong></td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="medicalHistVal" /></td></tr>	 
		      
		      <tr>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Medical history Other</strong> </td>
		      <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="showMedicalTextval" /></td></tr>		      
			</c:if>	
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">viii) Investigations done</span></strong>
		           <table width="100%">
		           <logic:present name="patientForm" property="investigationLt">
					<bean:size id="size" name="patientForm" property="investigationLt" />
					<logic:greaterThan name="size" value="0">
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
					</logic:greaterThan>
					</logic:present>
					<c:if test="${dentalOrNot eq 'yes'}">
					<tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">ix) Extra Oral Examinations</span></strong>	
			
			<table width="100%">
				<tr>
<!-- 				<td rowspan="4" width="30%"class="labelheading1 tbcellCss print_cell"><strong>ix) Extra Oral Examinations</strong> </td> -->
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Regional Lymphadenopathy</strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><strong><bean:write name="patientForm" property="regionalLymphadenopathyDtrsSub" /></strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="regionalLymphadenopathyDtrsTxt" /></td>
		      </tr>
		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Jaws</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong><bean:write name="patientForm" property="jawsDtrsSub" /></strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="jawsDtrsTxt" /></td></tr>	
		      		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>TMJ</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong><bean:write name="patientForm" property="tmjDtrsSub" /></strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="tmjDtrsTxt" /></td></tr>	
						      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Face</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong><bean:write name="patientForm" property="faceDtrsSub" /></strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="faceDtrsTxt" /></td></tr>
		      </table></td></tr>
		      
		       <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">x) Intra Oral Examinations</span></strong>
		      <table width="100%">
		      <tr><td colspan="4"class="labelheading1 tbcellCss print_cell"><strong>Soft Tissue Examinations</strong> </td></tr>
		   	  	<logic:iterate id="intraoralID" name="patientForm" property="intraoraldtls" indexId="i">
			  		<tr><td  width="50%" class="labelheading1 tbcellCss print_cell">&nbsp;&nbsp;
			 		 	
			      		 <strong><label id="intraoralLabel${i}" ><bean:write name="intraoralID" property="VALUE"/></label></strong>
			       			
			       	</td>	
			       		<%-- <td id="<bean:write name="intraoralID" property='ID'>" width="50%"></td>--%>
			       		<td width="50%"id="dntsublistoraltd${i}"  class="tbcellBorder" ><bean:write name="patientForm" property="dntsublistoral${i}"/></td>
					</tr>
	      		</logic:iterate>
	      		<tr><td width="30%"class="labelheading1 tbcellCss print_cell" rowspan="7"><strong>Swelling</strong> </td>
		      
		      <td width="30%" class="tbcellBorder print_cell"><strong>Site:</strong>&nbsp;<bean:write name="patientForm" property="swSite" /></td></tr>	
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Size:</strong>&nbsp;<bean:write name="patientForm" property="swSize" /></td></tr>
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Extension:</strong>&nbsp;<bean:write name="patientForm" property="swExtension" /></td></tr>
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Colour:</strong>&nbsp;<bean:write name="patientForm" property="swColour" /></td></tr>
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Consistency:</strong>&nbsp;<bean:write name="patientForm" property="swConsistency" /></td></tr>
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Tenderness:</strong>&nbsp;<bean:write name="patientForm" property="swTenderness" /></td></tr>
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Borders:</strong>&nbsp;<bean:write name="patientForm" property="swBorders" /></td></tr>
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell" rowspan="2"><strong>Pus/Discharge</strong> </td>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Site:</strong>&nbsp;<bean:write name="patientForm" property="psSite" /></td></tr>	
		      <tr>
		      <td width="30%" class="tbcellBorder print_cell"><strong>Discharge:</strong>&nbsp;<bean:write name="patientForm" property="psDischarge" /></td></tr>
		      </table></td></tr>
		      
		      <tr><td colspan="4" class="labelheading1 tbcellCss print_cell"><strong>Hard Tissue examinations</strong></td></tr>
		      <c:if test="${fn:length(deciduousdentdtls.carriesdecidous) ne 0 or fn:length(deciduousdentdtls.missingdecidous) ne 0 or fn:length(deciduousdentdtls.grosslydecadedecidous) ne 0 or fn:length(deciduousdentdtls.mobiledecidous) ne 0}">
		      <tr><td colspan="4" class="labelheading1 tbcellCss print_cell"><strong>Decidious Dentation</strong></td></tr>
		      <tr><td colspan="4" class="tbcellBorder print_cell">
		      
		      <table width="100%" id="decidiousBlock">
		      <c:if test="${fn:length(deciduousdentdtls.carriesdecidous) ne 0}"> 
				<tr id="cariesDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Caries</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="childCaries1" name="childCaries" value="c1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries2" value="c2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries3" name="childCaries" value="c3" > C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries4" value="c4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries5" name="childCaries" value="c5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries6" value="c6" > A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries7" value="c7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries8" name="childCaries" value="c8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries9" value="c9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries10" name="childCaries" value="c10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries11" name="childCaries" value="c11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries12" value="c12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries13" name="childCaries" value="c13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries14" value="c14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries15" name="childCaries" value="c15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries16" value="c16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries17" value="c17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries18" name="childCaries" value="c18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries19" value="c19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries20" name="childCaries" value="c20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(deciduousdentdtls.missingdecidous) ne 0}">
				<tr id="missingDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Missing</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="missingTeeth1" name="childMissing" value="m1" > E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth2" value="m2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth3" name="childMissing" value="m3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth4" value="m4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth5" name="childMissing" value="m5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth6" value="m6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth7" value="m7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth8" name="childMissing" value="m8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth9" value="m9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth10" name="childMissing" value="m10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth11" name="childMissing" value="m11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth12" value="m12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth13" name="childMissing" value="m13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth14" value="m14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth15" name="childMissing" value="m15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth16" value="m16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth17" value="m17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth18" name="childMissing" value="m18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth19" value="m19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth20" name="childMissing" value="m20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(deciduousdentdtls.grosslydecadedecidous) ne 0}">
				<tr id="grosslyDecdious"><td colspan="2" class="labelheading1 tbcellCss">Grossly Decayed</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="grosslyDecayed1" name="grosslyDecayed" value="g1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed2" value="g2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed3" name="grosslyDecayed" value="g3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed4" value="g4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed5" name="grosslyDecayed" value="g5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed6" value="g6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed7" value="g7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed8" name="grosslyDecayed" value="g8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed9" value="g9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed10" name="grosslyDecayed" value="g10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed11" name="grosslyDecayed" value="g11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed12" value="g12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed13" name="grosslyDecayed" value="g13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed14" value="g14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed15" name="grosslyDecayed" value="g15"> A
				 				| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed16" value="g16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed17" value="g17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed18" name="grosslyDecayed" value="g18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed19" value="g19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed20" name="grosslyDecayed" value="g20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(deciduousdentdtls.mobiledecidous) ne 0}">
				<tr id="mobileDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Mobile</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss"  id="childMobile1"  name="childMobile" value="mm1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile2" value="mm2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile3" name="childMobile" value="mm3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile4" value="mm4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile5" name="childMobile" value="mm5"> A
				 				| 
				 <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile6" value="mm6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile7" value="mm7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile8" name="childMobile" value="mm8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile9" value="mm9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile10" name="childMobile" value="mm10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile11" name="childMobile" value="mm11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile12" value="mm12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile13" name="childMobile" value="mm13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile14" value="mm14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile15" name="childMobile" value="mm15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile16" value="mm16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile17" value="mm17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile18" name="childMobile" value="mm18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile19" value="mm19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile20" name="childMobile" value="mm20"> E
				</div></td>
				</tr>
				</c:if>
				
				</table>
		      </td></tr>
		      </c:if>
		      
		      <c:if test="${fn:length(deciduousdentdtls.carriespermanent) ne 0 or fn:length(deciduousdentdtls.rootstumppermannet) ne 0 or fn:length(deciduousdentdtls.mobilitypermanent) ne 0 or fn:length(deciduousdentdtls.attritionpermanent) ne 0 or fn:length(deciduousdentdtls.missingpermanent) ne 0 or fn:length(deciduousdentdtls.otherpermanent) gt 3}">
		      <tr><td colspan="4" class="labelheading1 tbcellCss print_cell"><strong>Permanent Dentation</strong></td></tr>
		      
		      
		      <tr><td colspan="4" class="tbcellBorder print_cell">
		      <table width="100%" id="permanentBlock" >
		<c:if test="${fn:length(deciduousdentdtls.carriespermanent) ne 0}">
		<tr id="cariesDiv" ><td colspan="1" class="labelheading1 tbcellCss">Caries</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries1" name="caries" value="pc1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries2" name="caries" value="pc2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries3" name="caries" value="pc3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries4" name="caries" value="pc4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries5" name="caries" value="pc5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries6" name="caries" value="pc6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries7" name="caries" value="pc7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries8" name="caries" value="pc8"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries9" name="caries" value="pc9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries10" name="caries" value="pc10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries11" name="caries" value="pc11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries12" name="caries" value="pc12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries13" name="caries" value="pc13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries14" name="caries" value="pc14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries15" name="caries" value="pc15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries16" name="caries" value="pc16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries17" name="caries" value="pc17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries18" name="caries" value="pc18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries19" name="caries" value="pc19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries20" name="caries" value="pc20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries21" name="caries" value="pc21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries22" name="caries" value="pc22"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries23" name="caries" value="pc23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries24" name="caries" value="pc24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries25" name="caries" value="pc25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries26" name="caries" value="pc26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries27" name="caries" value="pc27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries28" name="caries" value="pc28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries29" name="caries" value="pc29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries30" name="caries" value="pc30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries31" name="caries" value="pc31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries32" name="caries" value="pc32">8
		</div></td></tr>
		</c:if>
		<c:if test="${fn:length(deciduousdentdtls.rootstumppermannet) ne 0}">
		<tr id="rootDiv" ><td colspan="1" class="labelheading1 tbcellCss">Root stump <br>/ Grossly Decayed </td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed1" name="decayed" value="pr1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed2" name="decayed" value="pr2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed3" name="decayed" value="pr3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed4" name="decayed" value="pr4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed5" name="decayed" value="pr5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed6" name="decayed" value="pr6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed7" name="decayed" value="pr7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed8" name="decayed" value="pr8"> 1
		 				|
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed9" name="decayed" value="pr9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed10" name="decayed" value="pr10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed11" name="decayed" value="pr11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed12" name="decayed" value="pr12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed13" name="decayed" value="pr13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed14" name="decayed" value="pr14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed15" name="decayed" value="pr15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed16" name="decayed" value="pr16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed17" name="decayed" value="pr17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed18" name="decayed" value="pr18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed19" name="decayed" value="pr19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed20" name="decayed" value="pr20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed21" name="decayed" value="pr21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed22" name="decayed" value="pr22"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed23" name="decayed" value="pr23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed24" name="decayed" value="pr24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed25" name="decayed" value="pr25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed26" name="decayed" value="pr26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed27" name="decayed" value="pr27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed28" name="decayed" value="pr28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed29" name="decayed" value="pr29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed30" name="decayed" value="pr30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed31" name="decayed" value="pr31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed32" name="decayed" value="pr32">8
		</div></td>
		</tr>
		</c:if>
		<c:if test="${fn:length(deciduousdentdtls.mobilitypermanent) ne 0}">
		<tr id="mobilityDiv" ><td colspan="1" class="labelheading1 tbcellCss">Mobility</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile1" name="mobile" value="pm1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile2" name="mobile" value="pm2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile3" name="mobile" value="pm3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile4" name="mobile" value="pm4"> 5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile5" name="mobile" value="pm5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile6" name="mobile" value="pm6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile7" name="mobile" value="pm7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile8" name="mobile" value="pm8"> 1 
						|
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile9" name="mobile" value="pm9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile10" name="mobile" value="pm10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile11" name="mobile" value="pm11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile12" name="mobile" value="pm12"> 4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile13" name="mobile" value="pm13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile14" name="mobile" value="pm14"> 6
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile15" name="mobile" value="pm15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile16" name="mobile" value="pm16"> 8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile17" name="mobile" value="pm17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile18" name="mobile" value="pm18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile19" name="mobile" value="pm19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile20" name="mobile" value="pm20"> 5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile21" name="mobile" value="pm21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile22" name="mobile" value="pm22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile23" name="mobile" value="pm23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile24" name="mobile" value="pm24"> 1
						 | 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile25" name="mobile" value="pm25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile26" name="mobile" value="pm26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile27" name="mobile" value="pm27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile28" name="mobile" value="pm28"> 4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile29" name="mobile" value="pm29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile30" name="mobile" value="pm30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile31" name="mobile" value="pm31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile32" name="mobile" value="pm32"> 8
		</div></td>
		</tr>
		</c:if>
		<c:if test="${fn:length(deciduousdentdtls.attritionpermanent) ne 0}">
		<tr id="attritionDiv" ><td colspan="1" class="labelheading1 tbcellCss">Attrition <br>/ Abrasion </td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition1" name="attrition" value="pa1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition2" name="attrition" value="pa2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition3" name="attrition" value="pa3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition4" name="attrition" value="pa4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition5" name="attrition" value="pa5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition6" name="attrition" value="pa6"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition7" name="attrition" value="pa7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition8" name="attrition" value="pa8"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition9" name="attrition" value="pa9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition10" name="attrition" value="pa10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition11" name="attrition" value="pa11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition12" name="attrition" value="pa12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition13" name="attrition" value="pa13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition14" name="attrition" value="pa14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition15" name="attrition" value="pa15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition16" name="attrition" value="pa16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition17" name="attrition" value="pa17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition18" name="attrition" value="pa18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition19" name="attrition" value="pa19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition20" name="attrition" value="pa20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition21" name="attrition" value="pa21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition22" name="attrition" value="pa22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition23" name="attrition" value="pa23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition24" name="attrition" value="pa24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition25" name="attrition" value="pa25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition26" name="attrition" value="pa26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition27" name="attrition" value="pa27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition28" name="attrition" value="pa28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition29" name="attrition" value="pa29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition30" name="attrition" value="pa30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition31" name="attrition" value="pa31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition32" name="attrition" value="pa32">8
		</div></td>
		</tr>
		</c:if> 
		<c:if test="${fn:length(deciduousdentdtls.missingpermanent) ne 0}">
		<tr id="missingDiv" ><td colspan="1" class="labelheading1 tbcellCss">Missing</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing1" name="missing" value="pmi1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing2" name="missing" value="pmi2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing3" name="missing" value="pmi3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing4" name="missing" value="pmi4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing5" name="missing" value="pmi5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing6" name="missing" value="pmi6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing7" name="missing" value="pmi7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing8" name="missing" value="pmi8"> 1
		 				| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing9" name="missing" value="pmi9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing10" name="missing" value="pmi10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing11" name="missing" value="pmi11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing12" name="missing" value="pmi12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing13" name="missing" value="pmi13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing14" name="missing" value="pmi14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing15" name="missing" value="pmi15"> 7  <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing16" name="missing" value="pmi16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing17" name="missing" value="pmi17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing18" name="missing" value="pmi18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing19" name="missing" value="pmi19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing20" name="missing" value="pmi20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing21" name="missing" value="pmi21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing22" name="missing" value="pmi22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing23" name="missing" value="pmi23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing24" name="missing" value="pmi24"> 1
		 				| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing25" name="missing" value="pmi25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing26" name="missing" value="pmi26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing27" name="missing" value="pmi27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing28" name="missing" value="pmi28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing29" name="missing" value="pmi29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing30" name="missing" value="pmi30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing31" name="missing" value="pmi31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing32" name="missing" value="pmi32">8
		</div></td>
		</tr>
		</c:if> 
		<c:if test="${fn:length(deciduousdentdtls.otherpermanent) gt 3}">
		<tr id="otherDiv">
		<td class="labelheading1 tbcellCss" colspan="1" id="">Others-<span id="otherPermntDent"></span>
		</td>
		<td id="otherPermText" class="tbcellBorder print_cell" colspan="2" >
		</td>
		</tr>
		</c:if>
		</table></td></tr>
		</c:if>	
		<tr><td colspan="4">
		   <table width="100%">
		<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>Previous Dental Treatment</strong> </td>
		    <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="previousDentalTreatment" /></td></tr>	
		
		<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>Occlusion</strong> </td>
			<td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="occlusionTxt" /></td>		
		    <td width="30%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="occlusionTypeTxt" /></td></tr>
		</table></td></tr>
		<c:if test="${fn:length(deciduousdentdtls.probingdepth) ne 0}">    
		<tr><td width="100%"class="labelheading1 tbcellCss print_cell"><strong>Clinical Probing</strong></td></tr>
		<tr><td colspan="4" class="tbcellBorder" align="center">
<table border=1>
<tr><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth0" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth6" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth11" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth1" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth7" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth12" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth2" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth8" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth13" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth3" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth9" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth14" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth4" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth10" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth15" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth5" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
<tr><td style="text-align:center">8</td><td style="text-align:center">7</td><td style="text-align:center">6</td><td style="text-align:center">5</td><td style="text-align:center">4</td><td style="text-align:center">3</td><td style="text-align:center">2</td><td style="text-align:center">1</td><td style="text-align:center">1</td><td style="text-align:center">2</td>
<td style="text-align:center">3</td><td style="text-align:center">4</td><td style="text-align:center">5</td><td style="text-align:center">6</td><td style="text-align:center">7</td><td style="text-align:center">8</td></tr>
<tr><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth16" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth22" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth27" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth17" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth23" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth28" name="probeDepth" onchange=javascript:fn_checkprobingdepth(this.id,this.value);></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth18" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth24" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth29" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth19" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth25" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth30" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth20" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth26" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth31" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth21" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
</table>
		  </c:if>    
		   </c:if>   
							
					
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
		<tr><td colspan="4">
		   <table width="100%">
				<td   class="labelheading1 tbcellCss"><b>Speciality</b></td>
				<td   class="tbcellBorder"><bean:write name="patientForm" property="medicalSpclty" /></td>
				<td   class="labelheading1 tbcellCss"><b>Category</b></td>
				<td   class="tbcellBorder"><bean:write name="patientForm" property="medicalCat" /></td>
			</table>
			</td>
			</tr>
		
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