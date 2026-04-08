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
<title>EHS::Chronic OP Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>



<style type="text/css">
body {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 12px;
line-height: 1.42857143;
color: #333;
background-color: #fff;
}

legend {
display: block;
width: 100%;
padding: 0;
margin-bottom: 20px;
font-size: 14px; 
line-height: inherit;
color: #333;
border: 0;
border-bottom: 1px solid #e5e5e5;
}
</style>
<script language="javascript" type="text/javascript">
	function fn_Print() {
		window.print();
	}

function fn_Close()
{
	window.close();
	parent.close();
}

function validateAlphaNumSpace(arg1,input)
{
	var a=input.value;
	//var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
	alert("Blank spaces are not allowed for "+arg1);
	document.getElementById("remarks").focus();
		//jqueryErrorMsg('Alphanumeric Validation',"Blank spaces are not allowed for "+arg1,fr);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		alert(arg1+ " should not start with space");
		//jqueryErrorMsg('Alphanumeric Validation',arg1+ " should not start with space",fr);
		document.getElementById("remarks").focus();
		return false;
		}
	var regAlphaNum=/^[0-9a-zA-Z ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		alert("Only alphanumerics are allowed for "+arg1);
		//jqueryErrorMsg('Alphanumeric Validation',"Only alphanumerics are allowed for "+arg1,fr);
		input.value="";
		document.getElementById("remarks").focus();
		return false;
		}
	else
		input.value=a.trim();
}


function fn_sendBack(buttonId){

	
	//document.getElementById("CD99").style.display="none";



	//fn_loadImage();
	var remarks=document.getElementById("remarks").value;
	var chronicNo='${chronicNo}';
	var roleId='${roleId}';
	var chronicStatus='${chronicStatus}';
	if(remarks==null || remarks=="")
	{
	alert("Please enter remarks");
	document.getElementById("remarks").focus();
	return flase;
	}
	//alert(chronicNo);alert(roleId);alert(chronicStatus);
		if(confirm("Do you want to send Back To CH ?"))
	{
	document.forms[0].action="/<%=context%>/ClaimsFlow.do?actionFlag=changeChronicClaimForCases&chronicStatus="+chronicStatus+"&actionDone="+buttonId+"&remarks="+remarks+"&chronicNo="+chronicNo+"&roleId="+roleId;
    document.forms[0].submit();
   // window.close();
	}
	else

	{
return ;
		
}
}






	function setAllergiesAndHabbits() {
		var allergies='', habbits='';
		var alg=false;
		var algsub=false;
		var addition = '${allDtls.habbits}';
		var additionKnown = '${allDtls.allergy}';
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
	function fn_openAtachment(filepath,fileName)
	{  
		var url = "./patientDetails.do?actionFlag=viewAttachment&filePath="+filepath+"&fileName="+fileName;
	    window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	}
	function fn_attachments()
	{
		var caseApprovalFlag="${caseApprovalFlag}";
		
		var url="/Operations/attachmentAction.do?actionFlag=onload&UpdType=chronicAttach&chronicId=${chronicId}&caseAttachmentFlag=Y&caseApprovalFlag="+caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;      
		   document.forms[0].action=url;   
		   document.forms[0].target="bottomFrame";
		   document.forms[0].submit();	
	}
	function fn_Refresh()
	{
parent.fn_Refresh();
		
		window.close();
	}
	function refreshParent() 
	{
		parent.fn_maxmizeTop();
	    //window.parent.parent.location.reload(true);
//window.parent.back();
	    //window.parent.parent.parent.location.href=window.parent.parent.parent.location.href;
		
	}
	$(document).ready(function(){
	    $(".btn").click(function(event){
	        event.preventDefault();
	    });
	});
</script>

<body onload="setAllergiesAndHabbits();" >
	<form action="/chronicAction.do" method="post"
		name="chronicOpForm">
		


		<table width="100%" style="margin: 0 auto" class="tb print_table">
				<c:if test="${type ne 'EoView' }">
		<c:if test="${printFlag eq 'Y'}">
		 <tr>
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
		</tr> 
		</c:if>
		</c:if>
		
		
		
<!--		 <tr><th class="tbheader">OUTPATIENT TREATMENT FORM</th></tr> -->
	<tr>
		<td colspan="5" style="text-align:center;" class="tbheader print_heading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Patient Details</strong></td>
		<c:if test="${printFlag ne 'Y' && type ne 'patView'}">
		<td id="menuSlide" style="text-align:center;" class="tbheader print_heading" width="5%">  
<img id="topImage" src="images/back.jpg" width="30" height="20" style="cursor:hand;" title="Back" alt="Back" align="top" onclick="javascript:refreshParent()">
</td></c:if>

		
		</tr>
<!--		 Patient details -->
		<tr><td>
		
		
		<table width="50%">
<tr>
				<td colspan="1">&nbsp;&nbsp;&nbsp;</td>
				<td width="45%" class="labelheading1 tbcellCss print_cell"><strong>Health Card No</strong></td>
				<td colspan="1">&nbsp;&nbsp;&nbsp;</td>
				<td width="50%" class="tbcellBorder print_cell">${commonDtls.cardNo}</td></tr>
</table>	
		
<div style="overflow:auto" >		
		<table width="100%" >
<tr>

<td width="30%" valign="top">
<fieldset style="height:28em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="overflow:auto" id='commonDtlsField'>


<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word; class="table table-responsive"">
<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="chronicOpForm" property="cardIssueDt"/></b></td></tr> --%> 

<tr><td class="labelheading1 tbcellCss" width="40%"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="chronicOpForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="gender"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="dateOfBirth"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="years"/>Y
 		<bean:write name="chronicOpForm" property="months"/>M
		<bean:write name="chronicOpForm" property="days"/>D</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="relation"/></b></td></tr>
<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="caste"/></b></td></tr> --%>
 <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="slab"/></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="occupation"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="contactno"/></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="30%" valign="top">
<fieldset style="height:28em;" >
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
  <div style="overflow:auto" id='cardAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word; class="table table-responsive"">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="chronicOpForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<c:if test="${type ne 'patView' }">
<td width="30%" valign="top">
<fieldset style="height:28em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
 <div style="overflow:auto" id='commAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word; class="table table-responsive"">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="chronicOpForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="chronicOpForm" property="pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
</c:if>
<td width="10%" valign="top">
<fieldset style="height:20em;" id='photoField'>
 <legend class="legendStyle"><b><fmt:message key="EHF.Photo"/></b></legend>
<table width="80%" height="80%" style="margin:auto auto">
<tr><td align="center">
 <logic:notEmpty name="chronicOpForm" property="photoUrl">
<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')"/>
</logic:notEmpty>
<logic:empty name="chronicOpForm" property="photoUrl">
<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
</logic:empty>
</td></tr></table>
</fieldset>
</td></tr>
</table>
	</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<!-- 
		
		     <table width="100%">
		   
		    
		    
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Health Card No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.cardNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.firstName}</td>
				</tr>
			<tr>
				
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.age}</td>
								<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.gender}</td>

			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.contactNo}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.addr1}</td>
			</tr>
			
			</table>   -->
			</td></tr>
<!--					  Case details -->



		
		<tr><td style="text-align:center;" class="tbheader print_heading" colspan="7"><b>Case&nbsp;Details</b></td></tr>
		<tr><td colspan="6">
			<table width="100%">
			
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Chronic ID</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.chronicID}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.refHospNo}</td>	
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address and contact</strong></td>
				<td width="35%" class="tbcellBorder print_cell">${commonDtls.regHospId}</td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Doctor name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="chronicOpForm" property="doctorName" /></td>

			</tr>
		</table>
		</td></tr>
<!--		 Medical details -->
<c:if test="${type ne 'patView' }">
		<tr><td  colspan="6" style="text-align:center" class="tbheader print_heading"><b>Medical&nbsp;Details</b></td></tr>
		<tr><td  colspan="6">
		   <table width="100%">

		      <tr><td rowspan="3" width="30%"class="labelheading1 tbcellCss print_cell"><strong>i) Present Illness History</strong></td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>a) Complaint Type</strong></td>
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.asriProcCode}</td></tr>
		      	 <tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>b) Patient Complaint</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell">${allDtls.asriDrugCode}</td></tr>
		      	<tr>
		      	<td width="40%" class="labelheading1 tbcellCss print_cell"><strong>c) Present Illness History</strong></td>
		      	<td width="30%" class="tbcellBorder print_cell">${allDtls.biometricVal}</td></tr>
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>ii) Known Allergies</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='algy'></div></td></tr>
		      
		       <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>iii) Habits or Addictions</strong> </td>
		      <td width="40%" class="tbcellCss print_cell">&nbsp;</td>
		      <td width="30%" class="tbcellBorder print_cell"><div id='hbts'></div></td></tr>	 
		      
		      <tr><td rowspan="5" width="30%"class="labelheading1 tbcellCss print_cell"><strong>iv) Examination Findings</strong> </td>
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
			
			 <tr><td  width="30%"class="labelheading1 tbcellCss print_cell"><strong>v) Past illness history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Past illness history</strong></td>
		      <c:if test="${fn:length(allDtls.ageDays) ne 0}">
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.ageDays}</td>
		       </c:if>
		      <c:if test="${fn:length(allDtls.ageDays) eq 0}">
			<td class="tbcellBorder print_cell">History of past illness not found</td>
				</c:if>	
		     
		      </tr>	  
		      
		      
		      <tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>vi) Family history</strong> </td>
		      <td width="40%" class="labelheading1 tbcellCss print_cell"><strong>Family history</strong></td>
		       <c:if test="${fn:length(allDtls.ageMonths) ne 0 }">
		      <td width="30%" class="tbcellBorder print_cell">${allDtls.ageMonths}</td>
		      </c:if>
		      <c:if test="${fn:length(allDtls.ageMonths) eq 0 }">
				<td class="tbcellBorder print_cell">Family history not found</td>
				</c:if>			 
		      </tr>
		      
		      
		      
		      
		      
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1"><strong>vii) Investigations done</strong></span></strong>
		           
		           
		           
		                      		<c:if test="${showAll eq 'Y' }">
		           <table width="100%">
		           
		           
 	<c:forEach var="i" begin="1" end="${inst}">
 	
 		
         <tr><td  colspan="6" class="tbcellCss"><span class="labelheading1"><strong><font color="#2E5CE6">Installment <c:out value="${i}"/></font></strong></span> </td></tr>
 		
                 
                 
                
						
					
						
						
						
						
						
                 
                      
   
		              <tr>
							<th width="40%" class="tbheader1">Investigation Block Name</th>
							<th width="40%" class="tbheader1">Test name</th>
							<!-- <th width="30%" class="tbheader1">Attachment</th>  -->
							
					 </tr>
					 <c:forEach items="${genInvestLst}" var="inv">
				 <c:set var="counter" value="${0}"></c:set>
				<c:set var="balance" value="${inv.INSTALLMENT}" />
				
 						<fmt:parseNumber var="inst" type="number" value="${balance}" />
 				
					 	<tr>
							<td  width="40%" class="tbcellBorder print_cell">${inv.TEST}</td>
							<td  width="40%" class="tbcellBorder print_cell">${inv.NAME}</td>
							
     <!--   	<td width="30%" class="tbcellBorder print_cell"><a href="javascript:fn_openAtachment('${inv.route}','${inv.route}');" style="text-align:center;" >View</a></td> --> 
		
							
						</tr>
						<c:set var="counter" value="${counter+1}" />
					
					</c:forEach>
					<c:if test="${counter eq 0 }">
							<tr>
							<td colspan="3" width="100%" class="tbcellBorder print_cell" align="center" style="color:red;">No Investigations Found</td>
						</tr>
					</c:if></c:forEach>
		           </table>	 </c:if>  
		           
		           
		           
		           
		           
		           
		           
		           
		           		<c:if test="${showAll ne 'Y' }">
		           <table width="100%">
		              <tr>
		             
							<th width="30%" class="tbheader1">Investigation Block Name</th>
							<th width="30%" class="tbheader1">Test name</th>
							<!--  <c:if test="${DTRS ne 'Y' }">
							<th width="30%" class="tbheader1">view Attachment</th>
							</c:if>  -->
					 </tr>
					 <c:forEach items="${genInvestLst}" var="inv">
					
					 	<tr>
							<td  width="50%" class="tbcellBorder print_cell">${inv.TEST}</td>
							<td  width="50%" class="tbcellBorder print_cell">${inv.NAME}</td>
		<!--	<c:if test="${DTRS ne 'Y' }">			
       	<td width="25%" class="tbcellBorder print_cell"><a href="#" style="text-align:center;" onclick="javascript:fn_openAtachment('${inv.route}','${inv.route}');">View</a></td>
		</c:if>  -->
						
						</tr>
					</c:forEach>
					<c:if test="${fn:length(genInvestLst) eq 0 }">
							<tr>
							<td colspan="2" width="100%" class="tbcellBorder print_cell" align="center"  >No Investigations Found</td>
						</tr>
					</c:if>
		           </table>	 </c:if>     
		      </td></tr>	 
		   </table>
		</td></tr>
<!--		  diagnosis -->
		<c:if test="${DTRS ne 'Y' }">
		
		
		
		
		
		
		
		

		<tr><td  colspan="6" style="text-align:center;" class="tbheader print_heading"><strong>Chronic OP Therapy Details</strong></td></tr>
		<tr><td  colspan="6">
		   <table width="100%">
		 
			
			<tr>
				<td  width="25%" class="labelheading1 tbcellCss print_cell"><b>OP Package Name</b></td>
				<td  width="40%" class="tbcellBorder print_cell">${allDtls.opPackageName}</td>
				<td  width="25%" class="labelheading1 tbcellCss print_cell"><b>OP Package Code</b></td>
				<td  width="10%" class="tbcellBorder print_cell">${allDtls.opPkgCode}</td>
			</tr>
			<tr>
				<td  width="25%" class="labelheading1 tbcellCss print_cell"><b>ICD Name</b></td>
				<td  width="40%" class="tbcellBorder print_cell">${allDtls.opCatName}</td>
				<td  width="25%" class="labelheading1 tbcellCss print_cell"><b>ICD Code</b></td>
				<td  width="10%" class="tbcellBorder print_cell">${allDtls.opIcdCode}</td>
			</tr>

		   </table>
		</td></tr>
			<tr>
				<td  colspan="12" class="tbcellBorder" style="color:red;">*The Investigations/ tests were done free of
					cost.</td>

</tr>



</c:if>

<!--  treatment details -->
<c:if test="${DTRS ne 'Y' }">






		 <tr><td  colspan="6" style="text-align:center"  class="tbheader print_heading"><b>Treatment details</b></td></tr>
		
		

		
		
		
		<tr><td  colspan="6" class="tbcellCss"><span class="labelheading1"><strong>i) Drug details</strong></span>
		  <br>
		  
		  		<c:if test="${showAll eq 'Y' }">
		
		<script></script>
		 <c:forEach var="i" begin="1" end="${instDrug}">
 	
 		
         <tr><td  colspan="6" class="tbcellCss"><span class="labelheading1"><strong><font color="#2E5CE6">Installment <c:out value="${i}"/></font></strong></span> </td></tr>
   
		  <table width="100%" style="table-layout:fixed;">
		  
	
		  <tr>
							<td style="width:10%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Sr. No.</strong></td>
							<td style="width:20%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Drug Name</strong></td>
							<td style="width:15%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Dosage(per day)</strong></td>
							<td style="width:25%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Medication Period(Days)</strong></td>
							<td style="width:15%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Batch Number</strong></td>
							<td style="width:20%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Expiry Date</strong></td>
						</tr>
						<c:set var="counter" value="${1}"></c:set>
						
					
						<c:forEach items="${drugsList}" var="drug">
						
						
						
						<c:set var="balance" value="${drug.installment}" />
 						<fmt:parseNumber var="inst" type="number" value="${balance}" />
 						<c:if test="${inst eq i }">
						
							<tr>
								<td style="width:5%;word-wrap:break-word;" class="tbcellBorder  print_cell">${counter}</td>
								 <td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.drugName}</td>
								<td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.dosage}</td>
								<td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.medicationPeriod}</td>
								<td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.batchNo}</td>
								<td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.expiryDt}</td>
							</tr>
					
							<c:set var="counter" value="${counter+1}" /></c:if>
							
							
						</c:forEach>
		  		
		  		<tr><td><br></td></tr>
		  		
	



		 </table> </c:forEach></c:if>
		  
		  <c:if test="${showAll ne 'Y' }">
		   <table width="100%" style="table-layout:fixed;">
		  <tr>
							<td style="width:5%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Sr. No.</strong></td>
							<td style="width:30%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Drug Name</strong></td>
							
							<td style="width:30%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Dosage(per day)</strong></td>
							<td style="width:30%;word-wrap:break-word;" class="tbheader1 print_cell"><strong>Medication Period(Days)</strong></td>
						</tr>
						<c:set var="counter" value="${1}"></c:set>
						<c:forEach items="${drugsList}" var="drug">
							<tr>
								<td style="width:5%;word-wrap:break-word;" class="tbcellBorder  print_cell">${counter}</td>
								 <td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.drugName}</td>
								<td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.dosage}</td>
								<td style="width:30%;word-wrap:break-word;" class="tbcellBorder  print_cell">${drug.medicationPeriod}</td>
							</tr>
							
							<c:set var="counter" value="${counter+1}" />
						</c:forEach>
		  		
		  		<tr><td><br></td></tr>
		  		
	
		  		
					

		  </table></c:if>
		  
<c:if test="${type eq 'EoView' }">		
<logic:present name="chronicOpForm" property="lstworkFlow">
<bean:size id="wrkList" name="chronicOpForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="1" width="100%" style="table-layout: fixed;" cellpadding="1" cellspacing="1" align="center">
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b>Work Flow</b></td></tr></table></td></tr>
<tr><td colspan="2">
<table border="1" width="100%" style="table-layout: fixed;" cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr  class="tbheader" ><td width="3%" class="labelheading1 tbcellCss"><b>SNo</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Date & Time</b></td>
<c:if test="${(UserRole1 ne 'GP2' && UserRole1 ne 'GP1') && (UserRole1 ne null && UserRole1 ne '') }">
<td width="20%" class="labelheading1 tbcellCss"><b>Name</b></td>
</c:if>			
<td width="10%" class="labelheading1 tbcellCss"><b>Role</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Remarks</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Approve Amount</b></td>
<td width="12%" class="labelheading1 tbcellCss"><b>Action</b></td>	
</tr>
<logic:iterate id="results1" name="chronicOpForm" property="lstworkFlow" indexId="index1" >
<tr>
<td style="word-wrap:break-word;"  class="tbcellBorder"><%=index1+1%></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="chronicAuditDate" /></td>
<c:if test="${(UserRole1 ne 'GP2' && UserRole1 ne 'GP1') && (UserRole1 ne null && UserRole1 ne '') }">
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditName" /></td>
</c:if>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditRole" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="cexRemark" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="COUNT" /></td>
<td style="word-wrap:break-word;" class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
</tr>
</logic:iterate>
</table>
</td></tr>
</logic:greaterThan>
</logic:present>

<table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="10%"><b>Remarks : </b></td>
<td width="90%"><html:textarea style="width: 95%;" property="remarks" onchange="javascript:validateAlphaNumSpace('Remarks',this)" styleId="remarks" name="chronicOpForm"></html:textarea>
</td></tr>
<tr><td><br><br></td></tr>
</table>


</c:if>
		 
		</td></tr></c:if>	
				  		<c:if test="${DTRS eq 'Y' }">
		  		<tr><td>
		<table width="100%">
			<tr>
			<td width="50%" class="tbcellBorder print_cell" height="50px">Name and Signature of MEDCO/Treating Doctor</td>
			<td width="50%" class="tbcellBorder print_cell" height="50px"><bean:write name="chronicOpForm" property="doctorName" /></td>
			</tr>
			<tr><td class="tbcellBorder" style="color:red;" colspan="2">NOTE:  All the Tests as prescribed by the doctor will be done free of cost. </td></tr>
		</table>
		</td></tr></c:if>
	
		 	
		  		<c:if test="${DTRS ne 'Y' && printFlag eq 'Y' }">
		  		<tr>
				<td  style="text-align:left;" class="tbheader print_heading"><b>6.Declaration by MEDCO/ TREATING DOCTOR</b></td>
			</tr>
			<tr>
				<td  style="text-align:justify;line-height:20px;"class="tbcellBorder print_cell">The patient is examined by the consultant and
					follow up advice is given as per the prescription of the treating
					doctor in accordance to the standard medical practices. The
					prescribed medicines are essential for the follow up treatment and
					the validity of the expiry date is ascertained before the medicines
					are given to the patient. The patient has been properly advised
					about the dosage, timing, duration and precautions while consuming
					the medicines along with the side effects that are likely to occur.
					He/ She is further advised to report immediately in case of
					experiencing any side effect/ adverse reactions.</td>
			</tr>
			
		  		
		  		<tr>
		  		<td>
		  			  <table width="100%">
		  
	
			
		  		<tr>
				<td style="width:5%" class="tbheader1  print_cell"><strong>Sr.No</strong></td>
				<td style="width:15%" class="tbheader1 print_cell"><strong> Designation</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong> Name</strong></td>
				<td style="width:40%" class="tbheader1 print_cell"><strong>Signature/ Thumb impression</strong></td>
			</tr>
			
			
					  	<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">1</td>
				<td width="15%" class="tbcellCss print_cell" height="50px"><c:choose>
							<c:when test="${chronicCaseScheme eq 'CD201'}" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya Mithra
							</c:otherwise>
						</c:choose></td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="chronicOpForm" property="mithra" /></td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
			
			<tr>
				<td width="5%" class="tbcellCss print_cell" height="50px">2</td>
				<td width="15%" class="tbcellCss print_cell"  height="50px"><bean:write name="chronicOpForm" property="diagnosedBy" /></td>
				<td width="40%" class="tbcellCss print_cell" height="50px"><bean:write name="chronicOpForm" property="doctorName" /></td>
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
				<td width="5%" class="tbcellCss print_cell" height="50px">3</td>
				<td width="15%" class="tbcellCss print_cell" height="50px">Patient</td>
				<td width="40%" class="tbcellCss print_cell" height="50px">${commonDtls.firstName}</td>
				<td width="40%" class="tbcellBorder print_cell" height="50px"></td>
			</tr>
		  
		  </table>
		  
		  
		  
		  		
		  		</td></tr></c:if>
		</c:if>  		
		  		<c:if test="${printFlag ne 'Y' && printFlag ne ''  && printFlag ne null}">
		  		 <tr class="print_buttons">
				<td>
				<div class="row" style=" margin-left:40%">
			
		  		 <button type="button" class="btn btn-primary"     id="Print" onclick="fn_Print()";>Print&nbsp;<span class="glyphicon glyphicon-print"></span></button>&nbsp;&nbsp;&nbsp;
		  		
		  		 <c:if test="${type eq 'EoView' }">	
				 <button type="button" class="btn btn-primary"     id="sendBack" onclick="javascript:fn_sendBack('CD97')";>Send Back To CH&nbsp;<span class="glyphicon glyphicon-hand-left"></span></button>&nbsp;&nbsp;&nbsp;
				 <button type="button" class="btn btn-danger"    id="close" onclick="window.close();";>Close&nbsp;<span class="glyphicon glyphicon-remove"></span></button>&nbsp;&nbsp;&nbsp;
				 </c:if>
		  		</div>
		  		</td>
			</tr> 
			</c:if>	
			
		  			<c:if test="${printFlag eq 'Y'}">
		 <tr class="print_buttons">
				<td  >

				<button type="button" class="btn btn-primary"  style=" margin-left:50%"  id="Print" onclick="fn_Print()";>Print&nbsp;<span class="glyphicon glyphicon-print"></span></button>&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-danger"    id="Close" onclick="fn_Close()";>Close&nbsp;<span class="glyphicon glyphicon-remove"></span></button>&nbsp;&nbsp;&nbsp;
				</td>
			</tr> 
			</c:if>	
		
		</table>
	</form>
</body>
	</html>
</fmt:bundle>