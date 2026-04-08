 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>

<fmt:bundle basename="Registration">
<html>

<head>
<title>cochlear questionnaire</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>  
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/js/PreauthScripts.js"></script> 
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/js/PreauthScripts.js"></script> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<%@ include file="/common/includeCalendar.jsp"%> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> 


<script>
$(function() { 
    $( "#assessDate" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
      		showOn: "both", 
            buttonImage: "images/calend.gif",
            buttonText: "Calendar",
            buttonImageOnly: true,
            minDate: 0,
            maxDate: "+3M",
            onSelect: function() {}
        }); 
}); 


$(document).ready(function() {
	
	var test = "${viewType}";
	
if((test=="disable"))

{
	
	$(':input','#medco').attr("disabled",true);
	$(':input','#assessDate').attr("display",true);
}
});





</script>
<script>
function validateRadio(arg1)
{


	if(!document.getElementById(arg1).checked)
	{
alert("please select anty radio button");

document.getElementById(arg1).focus();
return false;
	}
}
function validate(arg1)
{
	var value=document.getElementById(arg1).value;

	if(value==null || value=="")
		alert("Age fields must not be empty");
	return false;
	/*if(isNaN(value))
	{
alert("Age must be in numbers");
		document.getElementById(arg1).value='';
		document.getElementById(arg1).focus();
		return false;
	}*/
	/*if(value.length>2)
	{		
alert("please enter valid age");
document.getElementById(arg1).value='';
document.getElementById(arg1).focus();
return false;
	}*/
}
function focusNClear(arg)
{	
  aField = arg;
  aField.value='';
  setTimeout("aField.focus()", 0);  
  var x=getOffset( arg ).top;
  //parent.fn_goToField(x);
}
function save()
{
	
	var assessDate=document.forms[0].assessDate.value;
	var onsetAge=document.forms[0].onsetAge.value;
	var interventionAge=document.forms[0].interventionAge.value;
	var conventionalAids=document.forms[0].conventionalAids.value;
	var benifitFromConventional=document.forms[0].benifitFromConventional.value;
	var auditory=document.forms[0].auditory.value;
	var oralaural=document.forms[0].oralaural.value;
	var speakRead=document.forms[0].speakRead.value;
	var motherAwareness=document.forms[0].motherAwareness.value;
	var audioVerbal=document.forms[0].audioVerbal.value;
	var motivationSpeech=document.forms[0].motivationSpeech.value;
	var motivationAudio=document.forms[0].motivationAudio.value;
	var realisticExpect=document.forms[0].realisticExpect.value;
	var middleEar=document.forms[0].middleEar.value;
	var congenital=document.forms[0].congenital.value;
	var bonyCapsule=document.forms[0].bonyCapsule.value;
	var mileStones=document.forms[0].mileStones.value;
	var speechMechanism=document.forms[0].speechMechanism.value;
	var ADHD=document.forms[0].ADHD.value;
	var stableQuiet=document.forms[0].stableQuiet.value;
	var autistic=document.forms[0].autistic.value;
	var stubborn=document.forms[0].stubborn.value;
	var imitative=document.forms[0].imitative.value;
	var fitUnfit=document.forms[0].fitUnfit.value;











	var conventionalAids=document.forms[0].conventionalAids.value;
	var benifitFromConventional=document.forms[0].benifitFromConventional.value;
	var auditory=document.forms[0].auditory.value;
	var oralaural=document.forms[0].oralaural.value;
	var speakRead=document.forms[0].speakRead.value;
	var motherAwareness=document.forms[0].motherAwareness.value;
	var audioVerbal=document.forms[0].audioVerbal.value;
	var motivationSpeech=document.forms[0].motivationSpeech.value;
	var motivationAudio=document.forms[0].motivationAudio.value;
	var realisticExpect=document.forms[0].realisticExpect.value;
	var middleEar=document.forms[0].middleEar.value;
	var congenital=document.forms[0].congenital.value;
	var bonyCapsule=document.forms[0].bonyCapsule.value;
	var mileStones=document.forms[0].mileStones.value;
	var speechMechanism=document.forms[0].speechMechanism.value;
	var ADHD=document.forms[0].ADHD.value;
	var stableQuiet=document.forms[0].stableQuiet.value;
	var autistic=document.forms[0].autistic.value;
	var stubborn=document.forms[0].stubborn.value;
	var imitative=document.forms[0].imitative.value;
	var fitUnfit=document.forms[0].fitUnfit.value;

	 /*var x=document.getElementsByTagName("input");

	 var count=0;
	 var j=0;
	 for(k=0;k<x.length;k++)
	  {
		  alert(x.length);
		  var type = x[i].type;
		  alert(type);
		  if(type=="radio")
		  {
				j=k;
				break;
		  }
	  }
	 alert(j);
	  for(i=j;i<x.length;i+2)
	  {

		  var type = x[i].type;
		 
		     if (type=="radio" && !x[i].checked && !x[i+1].checked)
		  {
			  x[i].focus();
	  			count++;
	  			
	    }}

	    if(count>0)
	    {
		  alert(count);
alert("All the Radio fields are mandatory.Please enter");
return false;
	    }*/


	
	if((conventionalAids==""||conventionalAids==null)||(benifitFromConventional==""||benifitFromConventional==null)||(auditory==""||auditory==null)||(oralaural==""||oralaural==null)||(speakRead==""||speakRead==null)||
			(motherAwareness==""||motherAwareness==null)||(audioVerbal==""||audioVerbal==null)||(motivationSpeech==""||motivationSpeech==null)||(motivationAudio==""||motivationAudio==null)||(realisticExpect==""||realisticExpect==null)||
			(middleEar==""||middleEar==null)||(congenital==""||congenital==null)||(bonyCapsule==""||bonyCapsule==null)||(mileStones==""||mileStones==null)||(speechMechanism==""||speechMechanism==null)||
			(ADHD==""||ADHD==null)||(stableQuiet==""||stableQuiet==null)||(autistic==""||autistic==null)||(stubborn==""||stubborn==null)||(imitative==""||imitative==null)||(fitUnfit==""||fitUnfit==null))
	{
alert("All the Radio fields are mandatory.Please enter");
return false;
	}
	if(assessDate==null||assessDate=="")
	{
		alert("please enter assess Date");
		document.getElementById('assessDate').value='';
		document.getElementById('assessDate').focus();
		return false;
		}

	if(onsetAge==null||onsetAge=="")
	{
		alert("please enter onset Age");
		document.getElementById('onsetAge').value='';
		document.getElementById('onsetAge').focus();
		return false;
		}

	if(interventionAge==null||interventionAge=="")
	{
		alert("please enter intervention Age");
		document.getElementById('interventionAge').value='';
		document.getElementById('interventionAge').focus();
		return false;
		}

	document.getElementById("saveCochlear").disabled=true;
	document.getElementById("clear").disabled=true;
	var caseId='${caseId}';
	document.forms[0].action='/<%=context%>/preauthDetails.do?actionFlag=saveCochlearQuestionnaire&caseId='+caseId;
	   document.forms[0].submit();	
}
</script>
<script type="text/javascript">
function resetAll()
{

if(confirm("Do you want to reset all the fields"))
{
	 /*var x=document.getElementsByTagName("input");

	 
	  for(i=0;i<x.length;i++)
	  {
		  if ( !x[i].readOnly && !x[i].hidden)
		  {

			  if(x[i].type=="text")
			  x[i].value="";
	 
	    }}*/
	 

		document.getElementById("conventionalAidsY").checked=false;
		document.getElementById("benifitFromConventionalY").checked=false;
		document.getElementById("auditoryY").checked=false;
		document.getElementById("oralauralY").checked=false;
		document.getElementById("speakReadY").checked=false;
		document.getElementById("motherAwarenessY").checked=false;
		document.getElementById("audioVerbalY").checked=false;
		document.getElementById("motivationSpeechY").checked=false;
		document.getElementById("motivationAudioY").checked=false;
		document.getElementById("realisticExpectY").checked=false;
		document.getElementById("middleEarY").checked=false;
		document.getElementById("congenitalY").checked=false;
		document.getElementById("bonyCapsuleY").checked=false;
		document.getElementById("mileStonesY").checked=false;
		document.getElementById("speechMechanismY").checked=false;
		document.getElementById("ADHDY").checked=false;
		document.getElementById("stableQuietY").checked=false;
		document.getElementById("autisticY").checked=false;
		document.getElementById("stubbornY").checked=false;
		document.getElementById("imitativeY").checked=false;
		document.getElementById("fitUnfitY").checked=false;

		document.getElementById("conventionalAidsN").checked=false;
		document.getElementById("benifitFromConventionalN").checked=false;
		document.getElementById("auditoryN").checked=false;
		document.getElementById("oralauralN").checked=false;
		document.getElementById("speakReadN").checked=false;
		document.getElementById("motherAwarenessN").checked=false;
		document.getElementById("audioVerbalN").checked=false;
		document.getElementById("motivationSpeechN").checked=false;
		document.getElementById("motivationAudioN").checked=false;
		document.getElementById("realisticExpectN").checked=false;
		document.getElementById("middleEarN").checked=false;
		document.getElementById("congenitalN").checked=false;
		document.getElementById("bonyCapsuleN").checked=false;
		document.getElementById("mileStonesN").checked=false;
		document.getElementById("speechMechanismN").checked=false;
		document.getElementById("ADHDN").checked=false;
		document.getElementById("stableQuietN").checked=false;
		document.getElementById("autisticN").checked=false;
		document.getElementById("stubbornN").checked=false;
		document.getElementById("imitativeN").checked=false;
		document.getElementById("fitUnfitN").checked=false;

		document.getElementById("onsetAge").value="";
		document.getElementById("interventionAge").value="";
		document.getElementById("assessDate").value="";
		document.getElementById("conventionalAids_remarks").value="";
		document.getElementById("benifitFromConventional_remarks").value="";
		document.getElementById("auditory_remarks").value="";
		document.getElementById("oralaural_remarks").value="";
		document.getElementById("speakRead_remarks").value="";
		document.getElementById("motherAwareness_remarks").value="";
		document.getElementById("audioVerbal_remarks").value="";
		document.getElementById("motivationSpeech_remarks").value="";
		document.getElementById("motivationAudio_remarks").value="";
		document.getElementById("realisticExpect_remarks").value="";
		document.getElementById("middleEar_remarks").value="";
		document.getElementById("congenital_remarks").value="";
		document.getElementById("bonyCapsule_remarks").value="";
		document.getElementById("mileStones_remarks").value="";
		document.getElementById("speechMechanism_remarks").value="";
		document.getElementById("ADHD_remarks").value="";
		document.getElementById("stableQuiet_remarks").value="";
		document.getElementById("autistic_remarks").value="";
		document.getElementById("stubborn_remarks").value="";
		document.getElementById("imitative_remarks").value="";
		document.getElementById("fitUnfit_remarks").value="";

				

		

	  document.getElementById("saveCochlear").disabled=false;
}
else 
{
	return false;

}		
}
</script>
</head>
<body>

<div id="medco">







<html:form  method="post"  action="/preauthDetails.do" > 



 <div id="basicDetails"  class="table-responsive">
     <div class="col-lg-6"> 
     <table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="table">

<tr >
<td  colspan="6" align="center"  class="tbheader" ><b>EMPLOYEE HEALTH SCHEME</b></td>
</tr>


<tr>
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.CochImplant" /></b></td>
</tr>


<tr >
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.NameOfTheChild" /></b></td>
<td width="40%" class="tbcellBorder"><html:text name="preauthDetailsForm" style="width:90%" property="childName" styleId="childName" readonly="true"/></td>
</tr>

<tr>
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.FatherName" /></b></td>
<td width="16%" class="tbcellBorder"><html:text name="preauthDetailsForm" style="width:90%" property="fatherName" styleId="fatherName" onchange="javascript:chkAlpha('fatherName','father Name');"/></td>
</tr>

<tr>
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.AgeOfTheChild" /></b></td>
<td width="16%" class="tbcellBorder"><html:text name="preauthDetailsForm" style="width:90%" property="childAge" styleId="childAge" readonly="true"/></td>
</tr>

<tr>

<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.NameOfTheNetworkingHospital" /></b></td><!--
 <td width="16%">
 <input type='text' class="form-control" name="hospitalName" id="hospitalName"/>
 </td>
--><td width="16%" class="tbcellBorder"><html:text name="preauthDetailsForm" style="width:90%" property="hospitalName" styleId="hospitalName" readonly="true"/></td>
</tr>

<tr>
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.DateOfCICommitteeAssessment" /></b><font color="red">*</font></td>
<td>
<html:text name="preauthDetailsForm" property="assessDate" styleId="assessDate" style="width:14em;" title="Select Assess Date" onchange="validatePropSurgeryDate('Proposed Surgery Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>

</td>

</tr>

<tr>
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.CaseNo" /></b></td>
<td width="16%" class="tbcellBorder"><html:text name="preauthDetailsForm" style="width:14em;" property="caseNo" styleId="caseNo" readonly="true"/></td>
</tr>

<tr>
<td width="60%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.ClaimNo" /></b></td>
<td width="16%" class="tbcellBorder"><html:text name="preauthDetailsForm" style="width:14em;" property="claimNo" styleId="claimNo" readonly="true"/></td>
</tr>



</table></div></div> 
<br><br>

     
<div id="basicDetails"  class="table-responsive">
     <div class="col-lg-6"> 
     <table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="table">
<tr >
<td  colspan="6" align="center"  class="tbheader" ><b><fmt:message key="Preauth.AudiologicalCriterion" /> </b></td>
</tr>




<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>1.<fmt:message key="Preauth.AgeOfTheOnsetOfTheProblem" /></b><font color="red">*</font></td>
<td width="16%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="onsetAge" title="Enter Card Number" styleId="onsetAge" onchange="javascript:validate('onsetAge');"/></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.AgeOfTheIntervention" /></b><font color="red">*</font></td>
<td width="16%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="interventionAge" title="Enter Card Number" styleId="interventionAge" onchange="javascript:validate('interventionAge');"/></td>
</tr>

<tr><td></td><td></td><td>Remarks</td></tr>

<tr>
<td width="55%" class="labelheading1 tbcellCss"><b>2.<fmt:message key="Preauth.HasTheChildUsedConventionalHearingAid" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="conventionalAids" title="Enter Card Number" styleId="conventionalAidsY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="conventionalAids" title="Enter Card Number" styleId="conventionalAidsN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="conventionalAids_remarks" title="Enter Card Number" styleId="conventionalAids_remarks" onchange="javascript:chkAlphaNumeric('conventionalAids_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>3.<fmt:message key="Preauth.BenefitFromTheUseOfConventionalHearingAid" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="benifitFromConventional" title="Enter Card Number" styleId="benifitFromConventionalY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="benifitFromConventional" title="Enter Card Number" styleId="benifitFromConventionalN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="benifitFromConventional_remarks" title="Enter Card Number" styleId="benifitFromConventional_remarks" onchange="javascript:chkAlphaNumeric('benifitFromConventional_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>4.<fmt:message key="Preauth.DevelopmentOfAnyAuditoryComprehension" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="auditory" title="Enter Card Number" styleId="auditoryY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="auditory" title="Enter Card Number" styleId="auditoryN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="auditory_remarks" title="Enter Card Number" styleId="auditory_remarks" onchange="javascript:chkAlphaNumeric('auditory_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>5.<fmt:message key="Preauth.AttemptsToCommunicateInOralAuralMethod" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="oralaural" title="Enter Card Number" styleId="oralauralY" value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="oralaural" title="Enter Card Number" styleId="oralauralN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="oralaural_remarks" title="Enter Card Number" styleId="oralaural_remarks" onchange="javascript:chkAlphaNumeric('oralaural_remarks','Remarks');"/></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>6.<fmt:message key="Preauth.DoesTheChildSpeechRead" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="speakRead" title="Enter Card Number" styleId="speakReadY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="speakRead" title="Enter Card Number" styleId="speakReadN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="speakRead_remarks" title="Enter Card Number" styleId="speakRead_remarks" onchange="javascript:chkAlphaNumeric('speakRead_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>7.<fmt:message key="Preauth.AwarenessOfTheMotherAboutTheProblem" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="motherAwareness" title="Enter Card Number" styleId="motherAwarenessY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="motherAwareness" title="Enter Card Number" styleId="motherAwarenessN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="motherAwareness_remarks" title="Enter Card Number" styleId="motherAwareness_remarks" onchange="javascript:chkAlphaNumeric('motherAwareness_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>8.<fmt:message key="Preauth.AwareOfImpTech" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="audioVerbal" title="Enter Card Number" styleId="audioVerbalY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="audioVerbal" title="Enter Card Number" styleId="audioVerbalN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="audioVerbal_remarks" title="Enter Card Number" styleId="audioVerbal_remarks" onchange="javascript:chkAlphaNumeric('audioVerbal_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>9.<fmt:message key="Preauth.MotivSpeech" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="motivationSpeech" title="Enter Card Number" styleId="motivationSpeechY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="motivationSpeech" title="Enter Card Number" styleId="motivationSpeechN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="motivationSpeech_remarks" title="Enter Card Number" styleId="motivationSpeech_remarks" onchange="javascript:chkAlphaNumeric('motivationSpeech_remarks','Remarks');"/></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>10.<fmt:message key="Preauth.MotiVerbal" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="motivationAudio" title="Enter Card Number" styleId="motivationAudioY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="motivationAudio" title="Enter Card Number" styleId="motivationAudioN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="motivationAudio_remarks" title="Enter Card Number" styleId="motivationAudio_remarks" onchange="javascript:chkAlphaNumeric('motivationAudio_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>11.<fmt:message key="Preauth.RealExpct" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="realisticExpect" title="Enter Card Number" styleId="realisticExpectY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="realisticExpect" title="Enter Card Number" styleId="realisticExpectN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="realisticExpect_remarks" title="Enter Card Number" styleId="realisticExpect_remarks" onchange="javascript:chkAlphaNumeric('realisticExpect_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>12.<fmt:message key="Preauth.MidEarInf" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="middleEar" title="Enter Card Number" styleId="middleEarY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="middleEar" title="Enter Card Number" styleId="middleEarN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="middleEar_remarks" title="Enter Card Number" styleId="middleEar_remarks" onchange="javascript:chkAlphaNumeric('middleEar_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>13.<fmt:message key="Preauth.CongenAbnorm" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="congenital" title="Enter Card Number" styleId="congenitalY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="congenital" title="Enter Card Number" styleId="congenitalN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="congenital_remarks" title="Enter Card Number" styleId="congenital_remarks" onchange="javascript:chkAlphaNumeric('congenital_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>14.<fmt:message key="Preauth.NormalBonyCapsule" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="bonyCapsule" title="Enter Card Number" styleId="bonyCapsuleY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="bonyCapsule" title="Enter Card Number" styleId="bonyCapsuleN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="bonyCapsule_remarks" title="Enter Card Number" styleId="bonyCapsule_remarks" onchange="javascript:chkAlphaNumeric('bonyCapsule_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>15.<fmt:message key="Preauth.NormalMile" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="mileStones" title="Enter Card Number" styleId="mileStonesY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="mileStones" title="Enter Card Number" styleId="mileStonesN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="mileStones_remarks" title="Enter Card Number" styleId="mileStones_remarks" onchange="javascript:chkAlphaNumeric('mileStones_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>16.<fmt:message key="Preauth.NormSpMech" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="speechMechanism" title="Enter Card Number" styleId="speechMechanismY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="speechMechanism" title="Enter Card Number" styleId="speechMechanismN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="speechMechanism_remarks" title="Enter Card Number" styleId="speechMechanism_remarks" onchange="javascript:chkAlphaNumeric('speechMechanism_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>17.<fmt:message key="Preauth.BehavProb" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="ADHD" title="Enter Card Number" styleId="ADHDY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="ADHD" title="Enter Card Number" styleId="ADHDN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="ADHD_remarks" title="Enter Card Number" styleId="ADHD_remarks" onchange="javascript:chkAlphaNumeric('ADHD_remarks','Remarks');"/></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>18.<fmt:message key="Preauth.StableBehavr" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="stableQuiet" title="Enter Card Number" styleId="stableQuietY" value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="stableQuiet" title="Enter Card Number" styleId="stableQuietN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="stableQuiet_remarks" title="Enter Card Number" styleId="stableQuiet_remarks" onchange="javascript:chkAlphaNumeric('stableQuiet_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>19.<fmt:message key="Preauth.AutisTend" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="autistic" title="Enter Card Number" styleId="autisticY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="autistic" title="Enter Card Number" styleId="autisticN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="autistic_remarks" title="Enter Card Number" styleId="autistic_remarks" onchange="javascript:chkAlphaNumeric('autistic_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>20.<fmt:message key="Preauth.StubbornBehavr" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="stubborn" title="Enter Card Number" styleId="stubbornY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="stubborn" title="Enter Card Number" styleId="stubbornN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="stubborn_remarks" title="Enter Card Number" styleId="stubborn_remarks" onchange="javascript:chkAlphaNumeric('stubborn_remarks','Remarks');"/></td>
</tr>


<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>21.<fmt:message key="Preauth.ImitativBehavr" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="imitative" title="Enter Card Number" styleId="imitativeY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="imitative" title="Enter Card Number" styleId="imitativeN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="imitative_remarks" title="Enter Card Number" styleId="imitative_remarks" onchange="javascript:chkAlphaNumeric('imitative_remarks','Remarks');"/></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.RecForFurtherEval" /></b><font color="red">*</font></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.FitOrUnfit" /></b><font color="red">*</font></td>
<td width="10%" class="tbcellBorder">
Yes&nbsp;<html:radio  name="preauthDetailsForm" property="fitUnfit" title="Enter Card Number" styleId="fitUnfitY"  value="Y"/>&nbsp;&nbsp;
No&nbsp;<html:radio  name="preauthDetailsForm" property="fitUnfit" title="Enter Card Number" styleId="fitUnfitN"  value="N"/></td>
<td width="35%" class="tbcellBorder"><html:text style="width:250px;" name="preauthDetailsForm" property="fitUnfit_remarks" title="Enter Card Number" styleId="fitUnfit_remarks" onchange="javascript:chkAlphaNumeric('fitUnfit_remarks','Remarks');"/></td>
</tr>

<tr>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="Preauth.Member" /></b></td>
</tr>

</table></div></div>
<br><br>

<div id="audiologicalCriterion"  class="table-responsive">
    <div class="col-lg-6">
<table width="100%" align="center" style="padding-left:42%">
<tr>
<td colspan="1" style="textalign:center">
<button type="button" class="btn btn-primary"    id="saveCochlear" onclick="javascript:save()";>Save</button>&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary"  id="clear"  onclick="javascript:resetAll()";>Reset</button>
</td></tr>
</table></div></div>
<html:hidden name="preauthDetailsForm" property="caseId" value="${caseId}"/>
<html:hidden name="preauthDetailsForm" property="caseNo" value="${caseNo}"/>
<html:hidden name="preauthDetailsForm" property="childName" value="${childName }"/>
<html:hidden name="preauthDetailsForm" property="childAge" value="${childAge }"/>
<html:hidden name="preauthDetailsForm" property="hospitalName" value="${hospitalName }"/>
<html:hidden name="preauthDetailsForm" property="claimNo" value="${claimNo }"/>

<c:if test="${status eq 'success'}">
<script>
//window.opener.location.reload();
//window.opener.document.getElementById('cochlearQuestionnaire').value="Y";
window.opener.document.forms[0].cochlearQuestionnaire.value='Y';
document.getElementById("saveCochlear").disabled=true;
alert("Cochlear questionnaire saved successfully");

var caseId = "${caseId}";

document.forms[0].action='/<%=context%>/preauthDetails.do?actionFlag=cochlearQuestionnaire&type=enable&CaseId='+caseId;
document.forms[0].method="post";
document.forms[0].submit();
</script>
</c:if>

<c:if test="${status eq 'fail'}">
<script>
opener.cochlearQues= "Y";
document.forms[0].reset();
document.getElementById("saveCochlear").disabled=true;
alert("An error occured in saving the cochlear questionnaire");
</script>
</c:if>

</html:form>
</div>

</body>
</html>
</fmt:bundle>