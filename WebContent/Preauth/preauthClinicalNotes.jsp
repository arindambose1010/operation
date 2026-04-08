 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>

<html>
<head>
<script src=js/jquery-1.9.1.min.js></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<script LANGUAGE="JavaScript" SRC="Preauth/maximizeScreen.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>
<%@ include file="/common/includePatientDetails.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<!--<script LANGUAGE="JavaScript" SRC="scripts/clinicalNotes.js"></script>-->
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/js/clinicalNotes.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">  
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>preauthClinicalNotes</title>
<style>body{font-size:12px !important}</style>
<c:if test="${resMsg  != null && resMsg !='' }">
<script>
//jqueryAlertMsg('Clinical notes mandatory fields','${resMsg}');
alert('${resMsg}');
</script>
</c:if>
<script LANGUAGE="JavaScript">

$(document).ready(function (){
	setInterval(function (){
		//$('#unitsSelectID').toggleClass('hide');
		//$('#unitsSelectIDNew').toggleClass('hide');
		
	},800);
});


var drugs=new Array();
var drugCount=0;
var preOPPostOPFlag = null;
var dentalSurg='${dentalSurg}';
function focusFieldView(el)
{
		var x=getOffset( el ).top;
		/* var offset = $(el).offset();
		var top = offset.top;
		//
		////parent.fn_goToField(x);
		top = top+elemJqueryScrollTop;
		$("body").mCustomScrollbar("update");
		$("body").mCustomScrollbar("scrollTo",top);
		focusBox(el); */

}

function focusBox(arg)
{	
	  aField = arg; 
	 setTimeout("aField.focus()", 0); 
	  
 	   var x=getOffset( arg ).top;  
 	 /* var offset = $(aField).offset();
	  var top = offset.top;
	  top = top+elemJqueryScrollTop;
	  $("body").mCustomScrollbar("update");
	  $("body").mCustomScrollbar("scrollTo",top); */
	  
	
}

function fn_maxmizeTop()
{
parent.fn_maxmizeTop();
/*	var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.parent.caseApprovalFlag+'&errSearchType='+parent.parent.errSearchType+'&disSearchType='+parent.parent.disSearchType+'&module='+parent.parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
   document.forms[0].submit();*/
	}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
function fn_openFollowUp(){

parent.parent.parent.fpInitPage = window.open('/<%=context%>/followUpAction.do?actionFlag=followUpinit&caseId=${caseId}','followUpWin','toolbar=no,resize=yes,scrollbars=yes,width=925, height=500, top=50,left=50');	
}
function fn_openCochlearFollowUp()
	{
		parent.parent.parent.cochFpInitPage = window.open('/<%=context%>/followUpAction.do?actionFlag=cochFollowUpinit&caseId=${caseId}','cochFollowUpWin','toolbar=no,resize=yes,scrollbars=yes,width=1000, height=500, top=50,left=10');		
	}
function eraser(param){
	document.getElementById(param).value="";
}
function fn_DiffBtwSugyStrtEndTime(strtHrs,strtMins,EndHrs,EndMins)
{
	if(strtHrs!="-1" && strtMins!="-1" &&
			EndHrs!="-1" && EndMins!="-1")
	{	
		
		if(parseInt(EndHrs) < parseInt(strtHrs))
		{
			return false;
		}
		if(parseInt(EndHrs)==parseInt(strtHrs) && 
				parseInt(EndMins)<=  parseInt(strtMins))
		{
			return false;
		}
		else
			return true;
	}
	else 
		return false;
}

function fn_onload()
{
	
	
	
	
/* 	alert('addClinicNotesFlag---'+'${addClinicNotesFlag}');
	alert('lStrTrmtMedicalSubmitFlag---'+'${lStrTrmtMedicalSubmitFlag}');
	alert('TrmtSurgSubmitFlag---'+'${TrmtSurgSubmitFlag}');
	alert('medMgmtFlag---'+'${medMgmtFlag}'); 
	alert('enhancementFlag----'+'${enhApprovedFlag}'); */
	//alert('${postAddClinicNotesFlag}');
	//alert('medicalFlag---'+'${medicalFlag}'); 
	document.getElementById("dischargeDiv").style.display='${DischargeRadioDivStyle}';
	document.getElementById("deathDiv").style.display='${DeathRadioDivStyle}';
    var mDate = new Date();
    var surMDate = new Date();
   if('${DischargeSumryButFlag}' =='N' )
	{
	if(document.getElementById('treatmentGvn') != null)
		document.getElementById('treatmentGvn').readOnly="true";
	if(document.getElementById('operatveFindgs') != null)
		document.getElementById('operatveFindgs').readOnly="true";
	if(document.getElementById('postOperatvePerd') != null)
		document.getElementById('postOperatvePerd').readOnly="true";
	if(document.getElementById('postSplInvstgtns') != null)
		document.getElementById('postSplInvstgtns').readOnly="true";
	if(document.getElementById('statusAtDischrg') != null)
		document.getElementById('statusAtDischrg').readOnly="true";
	if(document.getElementById('review') != null)
		document.getElementById('review').readOnly="true";
	if(document.getElementById('advice') != null)
		document.getElementById('advice').readOnly="true";
	if(document.getElementById('causeOfDeath') != null)
		document.getElementById('causeOfDeath').readOnly="true";
	}
	if(document.getElementById('complications') != null && '${clinicalNotesForm.complications}' != null && '${clinicalNotesForm.complications}'=='Yes')
		{
		document.getElementById('complicationsDiv').style.display="";
		}
	if(document.getElementById('specimenRem') != null && '${clinicalNotesForm.specimenRem}' != null && '${clinicalNotesForm.specimenRem}'=='Yes')
		{
		document.getElementById('specimenRemDiv').style.display="";
		}
	
	if('${medMgmtFlag}' !='N'){
		$(".treatmentSugretyTime").attr("disabled","disabled");
	}
	if(document.forms[0].ipRegDate.value !=null && document.forms[0].ipRegDate.value !='')
	{
	var mDate1 = new Date(document.forms[0].ipRegDate.value.substring(6,10),document.forms[0].ipRegDate.value.substring(3,5)-1,document.forms[0].ipRegDate.value.substring(0,2));
	mDate = new Date(mDate1.getFullYear(),mDate1.getMonth(),mDate1.getDate());
	}
	if(document.forms[0].teleRegDate.value !=null && document.forms[0].teleRegDate.value !='')
	{
	var mDate1 = new Date(document.forms[0].teleRegDate.value.substring(6,10),document.forms[0].teleRegDate.value.substring(3,5)-1,document.forms[0].teleRegDate.value.substring(0,2));
	mDate = new Date(mDate1.getFullYear(),mDate1.getMonth(),mDate1.getDate());
	
	}
	if(document.forms[0].surgSaveDate.value !=null && document.forms[0].surgSaveDate.value !='')
	{
		
	var mDate1 = new Date(document.forms[0].surgSaveDate.value.substring(6,10),document.forms[0].surgSaveDate.value.substring(3,5)-1,document.forms[0].surgSaveDate.value.substring(0,2));

	surMDate = new Date(mDate1.getFullYear(),mDate1.getMonth(),mDate1.getDate());

	}
	if(document.getElementById('addClinicalNotes') != null)
		focusFieldView(document.getElementById('addClinicalNotes'));	
	
	//parent.fn_resizePage();
	//$("body").mCustomScrollbar("update");
	// add calendar instances for add clinical notes
	if('${hospType}' == 'G' || '${hospId}' == 'EHS34')
		{
		var startDate = '${startDate}';
		var arr = startDate.split('/');
		var startDt = new Date(arr[2],arr[1]-1,arr[0]);
		$(function() {
			$( "#invesgtnDate" ).datepicker({
				changeMonth: true,
				changeYear: true,
				showOn: "both", 
		         buttonImage: "images/calend.gif",
		         buttonText: "Calendar",
		         buttonImageOnly: true,
		         minDate: startDt,
				numberOfMonths: 1,
				  maxDate: '0'
			});	
		});		
		}
	// add clinical notes for surgeon div 
	
	
	
	
	
	if('${medMgmtFlag}' != null && '${medMgmtFlag}' =='Y' || '${TrmtSurgSubmitFlag}' !='N'
		|| '${enhApprovedFlag}' =='Y')
		{
		$(function() {
			$( "#surgStartDt,#deathDate" ).datepicker({
				minDate: mDate,
				//defaultDate: "+1w",
				changeMonth: true,
				changeYear: true,
				showOn: "both", 
		         buttonImage: "images/calend.gif",
		         buttonText: "Calendar",
		         buttonImageOnly: true,
				 //maxDate: new Date(y, m, d),
				numberOfMonths: 1	,
				 maxDate: '0'
			});	
		});	
		}
	
	/*if('${postAddClinicNotesFlag}' != null && '${postAddClinicNotesFlag}' =='Y' )
	{
		$(function() {
			$( "#invesgtnDate" ).datepicker({
				//defaultDate: "+1w",
				minDate: mDate,
				changeMonth: true,
				changeYear: true,
				showOn: "both", 
		         buttonImage: "images/calend.gif",
		         buttonText: "Calendar",
		         buttonImageOnly: true,
				 //maxDate: new Date(y, m, d),
				numberOfMonths: 1,
				 maxDate: '0'
			});	
		});		
	}*/
	if('${DischargeSumryButFlag}' !='N' )
	{
		if('${DischargeSumryRadioFlag}' !='N' )
		{
			$(function() {
				$( "#disDate" ).datepicker({
					//defaultDate: "+1w",
					minDate: surMDate,
					changeMonth: true,
					changeYear: true,
					showOn: "both", 
					 buttonImage: "images/calend.gif",
					 buttonText: "Calendar",
					 buttonImageOnly: true,
					 //maxDate: new Date(y, m, d),
					numberOfMonths: 1,
					 maxDate: '0'
				});	
			});
		}
		$(function() {
			$( "#nxtFollUpDt" ).datepicker({
				//defaultDate: "+1w",
				changeMonth: true,
				changeYear: true,
				numberOfMonths: 1,
				showOn: "both", 
		         buttonImage: "images/calend.gif",
		         buttonText: "Calendar",
		         buttonImageOnly: true,
				 // maxDate: '+2w',
				minDate: surMDate
				
			});	
		});
	}
	if('${DischargeSumryButFlag}' !='N'  && '${medcoRole}' =='Y' )
	{
		$(function() {
			$( "#disDeathDate" ).datepicker({
				defaultDate: "+1w",
				changeMonth: true,
				changeYear: true,
				showOn: "both", 
		         buttonImage: "images/calend.gif",
		         buttonText: "Calendar",
		         buttonImageOnly: true,
				 maxDate: new Date(y, m, d),
				numberOfMonths: 1	
			});	
		});	
	}
	  
	//$("#ui-datepicker-div").wrap('<div style="position:fixed;top:0px;"></div>');
}
function fn_openAtachment(filepath)
{  
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&docSeqId="+filepath;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function fn_checkSelDate(selDate)
{	   
	    var lCurentDt='';	
	    var lCurentDtTime='${lStrCurentDtTime}';	
		lCurentDt=new Date(lCurentDtTime.substring(6,10),lCurentDtTime.substring(3,5)-1,lCurentDtTime.substring(0,2));
		var surgDate=new Date(selDate.substring(6,10),selDate.substring(3,5)-1,selDate.substring(0,2));	
		if(surgDate > lCurentDt){
	     return false;
	     }
	     else{
	      return true;
	     }
}
// check two dates are equal
function fn_checkSelDateEqual(selDate1,selDate)
{	   
		var selDt = selDate1.replace(/\-/g,"/");	
		selDt = selDt.split("/");
		var selyr = parseInt(selDt[2]); 
		var selectMnth = parseInt(selDt[1],10);
		var seldy = parseInt(selDt[0],10); 
		var registrdDate = selDate.replace(/\-/g,"/");	
		registrdDate=registrdDate.split("/");
		var regYr=parseInt(registrdDate[2]);
		var regMnth=parseInt(registrdDate[1],10); 
		var regDy=parseInt(registrdDate[0],10);	
		
		if(regYr==selyr && regMnth==selectMnth && seldy == regDy)
			{
			return true;
			}
		else
			return false;
}
function checkNextFollowup11Days(selDate,dischargeDt)
{
	//var todays = new Date; 
	//todays.setDate(todays.getDate()+10); 
	var surgDate=new Date(selDate.substring(6,10),selDate.substring(3,5)-1,selDate.substring(0,2));
	var dischargedt =new Date(dischargeDt.substring(6,10),dischargeDt.substring(3,5)-1,dischargeDt.substring(0,2));
	dischargedt.setDate(dischargedt.getDate()+10); 
	if(surgDate >dischargedt)
		{
		return true;
		}
	else
		return false;
	}

function fn_checNxtFollowpDate1Equal(selDate,registrdDate)
{
	var selDate = selDate.replace(/\-/g,"/");	
	selDate = selDate.split("/");
	var selyr = parseInt(selDate[2]); 
	var selectMnth = parseInt(selDate[1],10);
	var seldy = parseInt(selDate[0],10); 
	var registrdDate = registrdDate.replace(/\-/g,"/");	
	registrdDate=registrdDate.split("/");
	var regYr=parseInt(registrdDate[2]);
	var regMnth=parseInt(registrdDate[1],10); 
	var regDy=parseInt(registrdDate[0],10);	
	if(regYr < selyr)
	{
		
	 	return true;
	}
	else if(regYr==selyr)
	{
		if(regMnth < selectMnth)
		{
			return true;
		}
		else if(regMnth==selectMnth && regDy<seldy)
		{	
			return true;
		}
		else
			{
			return false;
			}
	}
	}
	// check start time should be grater than current time
	function fn_checkStartGtCurrTime(surDate,strtHrs,strtMin)
	{
		var todaysTime = null;
		<%Calendar cal=Calendar.getInstance();%> 
		var diffTime=new Date().getTime()-<%=cal.getTimeInMillis()%>;
		var time;
		var date;
		time = new Date().getTime();
		time=time+diffTime;
		date = new Date(time);	
		if(navigator.appName.indexOf("Microsoft")!=-1)
		{
			todaysTime = date.toString().replace("UTC+0530","");
		}
	else
		{
		todaysTime =date.toString().replace("GMT+0530 (India Standard Time)","");
		}
		var serverHrMi = todaysTime.split(" ");
		var todayDay = serverHrMi[2];
		if(navigator.appName.indexOf("Microsoft")!=-1)
		{
			servHi = serverHrMi[3].split(":");	
		}
		else
			servHi = serverHrMi[4].split(":");	
		var servHr=servHi[0];
	    var servMi = servHi[1];
	    var surgyDate =surDate.split("-"); 
	    var month = parseInt(new Date().getMonth())+parseInt(1);
	   // alert(surgyDate[0]==new Date().getDate());
	    if(surgyDate[0]==new Date().getDate() && surgyDate[1]==month && surgyDate[2]==new Date().getFullYear())
	    	{
	    	if(parseInt(strtHrs) > parseInt(servHr))
	    		return true;
	    		else if (parseInt(strtHrs) == parseInt(servHr) && parseInt(strtMin) > parseInt(servMi))
	    			return true;
	    		else
	    			return false;
	    	}
	    else
	    	return false;
	    
	}
	
function fn_checNxtFollowpDate1(selDate,registrdDate)
{
	var selDate = selDate.replace(/\-/g,"/");	
	selDate = selDate.split("/");
	var selyr = parseInt(selDate[2]); 
	var selectMnth = parseInt(selDate[1],10);
	var seldy = parseInt(selDate[0],10); 
	var registrdDate = registrdDate.replace(/\-/g,"/");	
	registrdDate=registrdDate.split("/");
	var regYr=parseInt(registrdDate[2]);
	var regMnth=parseInt(registrdDate[1],10); 
	var regDy=parseInt(registrdDate[0],10);	
	if(regYr < selyr)
	{
		
	 	return true;
	}
	else if(regYr==selyr)
	{
		if(regMnth < selectMnth)
		{
			return true;
		}
		else if(regMnth==selectMnth && regDy<seldy)
		{	
			return true;
		}
		else  if(regMnth==selectMnth && regDy==seldy)
		{
			return true;
		}
		else
			{
			return false;
			}
	}
	}
function fn_checNxtFollowpDate(selDate,registrdDate)
{	
	var selDate = selDate.replace(/\-/g,"/");	
	selDate = selDate.split("/");
	var selyr = parseInt(selDate[2]); 
	var selectMnth = parseInt(selDate[1],10);
	var seldy = parseInt(selDate[0],10); 
	var registrdDate = registrdDate.replace(/\-/g,"/");	
	registrdDate=registrdDate.split("/");
	var regYr=parseInt(registrdDate[2]);
	var regMnth=parseInt(registrdDate[1],10); 
	var regDy=parseInt(registrdDate[0],10);	
	if(regYr < selyr)
	{
		
	 	return true;
	}
	else if(regYr==selyr)
	{
		if(regMnth < selectMnth)
		{
			return true;
		}
		else if(regMnth==selectMnth && regDy<seldy)
		{	
			return true;
		}
		
		else
			{
			return false;
			}
	}
}	 

function fn_savePostClinicalNotes(flag)
{
	document.getElementById('SavePostNotesBut').disabled=true;
	var errMsg='';
	var lField='';
	var investgnDtVal=document.forms[0].invesgtnDate1.value;	
	if(investgnDtVal=="" || document.forms[0].invesgtnDate1.length==0)
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select the date <br> "; 
		if(lField=='')
	        lField='invesgtnDate1'; 
		//alert("Please select the date ");
		//document.forms[0].invesgtnDate1.focus();
		//return;
	}	
	 if (investgnDtVal!="" && !fn_checNxtFollowpDate1(investgnDtVal,document.forms[0].ipRegDate.value))
	{
		 if(errMsg=='')
				errMsg=errMsg+"Date cannot be less than IP registered date <br> "; 
			if(lField=='')
		        lField='invesgtnDate1'; 
	//alert('Date cannot be less than IP registered date');
	//document.forms[0].invesgtnDate1.focus();
	//document.forms[0].invesgtnDate1.value='';
	//return;
	}
	    if( (investgnDtVal!=""  && !fn_checkSelDate(investgnDtVal))  || (investgnDtVal!="" && fn_checNxtFollowpDate1Equal(document.forms[0].surgStartDt.value,investgnDtVal))  )
	{ 
		if('${medMgmtFlag}'=='Y')
			{
			if(errMsg=='')
				errMsg=errMsg+"Selected date should be between case treatment start date and current date <br> "; 
			if(lField=='')
		        lField='invesgtnDate1'; 
			// alert('Selected date should be between case treatment start date and current date');	
			}
               					  
	    else
	    	{
	    	if(errMsg=='')
				errMsg=errMsg+"Selected date should be between case surgery date and current date <br> "; 
			if(lField=='')
		        lField='invesgtnDate1'; 
			 // alert('Selected date should be between case surgery date and current date');
	    	}
              
		//document.forms[0].invesgtnDate1.focus();
		//document.forms[0].invesgtnDate1.value='';
		//return;
	}   
	 if(document.forms[0].systolic1.value=="-1")
	{
		 if(errMsg=='')
				errMsg=errMsg+"Please select systolic BP  "; 
			if(lField=='')
		        lField='systolic1'; 
		//alert("Please select systolic BP");	
		//document.forms[0].systolic1.focus();
		//return;
	} 
	 if(document.forms[0].diastolic1.value=="-1")
	{
		 if(errMsg=='')
				errMsg=errMsg+"Please select diastolic BP  "; 
			if(lField=='')
		        lField='diastolic1'; 
	//	alert("Please select diastolic BP");	
	//	document.forms[0].diastolic1.focus();
	//	return;
	} 
	 if(document.forms[0].pulseRate1.value=="-1")
	{
		 if(errMsg=='')
				errMsg=errMsg+"Please select pulse rate  "; 
			if(lField=='')
		        lField='pulseRate1'; 
		//alert("Please select pulse");	
		//document.forms[0].pulseRate1.focus();
		//return;
	}
	 if(document.forms[0].temperature1.value=="-1")
	{
		 if(errMsg=='')
				errMsg=errMsg+"Please select temperature  "; 
			if(lField=='')
		        lField='temperature1'; 
		//alert("Please select temperature");	
	//document.forms[0].temperature1.focus();
		//return;
	}
	 if(document.forms[0].wardType1.value=="-1")
	{
		 if(errMsg=='')
				errMsg=errMsg+"Please select ward type "; 
			if(lField=='')
		        lField='wardType1'; 
		//alert("Please select ward type");	
		//document.forms[0].wardType1.focus();
		//return;
	}
	 if(document.forms[0].respRate1.value=="-1")
		{
		 if(errMsg=='')
				errMsg=errMsg+"Please select respiratory rate  "; 
			if(lField=='')
		        lField='respRate1'; 
			//alert("Please select respiratory rate");	
			//document.forms[0].respRate1.focus();
			//return;
		}
	 
	 if(document.getElementById('heartRate1').value=='-1')
		 {
		 if(errMsg=='')
				errMsg=errMsg+"Please select heart sounds "; 
			if(lField=='')
		        lField='heartRate1'; 
		//alert('Please select heart rate ');
		//document.forms[0].heartRate1.focus();
		//return;
		 }
	 if(document.forms[0].remarks1.value=='')
	 {
		 if(errMsg=='')
				errMsg=errMsg+"Please enter daily doctor notes "; 
			if(lField=='')
		        lField='remarks1'; 
	 //alert("Please input daily doctor notes ");	
	//	document.forms[0].remarks1.focus();
	//	return;
	 }
	 
	  if(trim(document.getElementById("lungs1").value)=='')
		{
		  if(errMsg=='')
				errMsg=errMsg+"Please enter lungs value "; 
			if(lField=='')
		        lField='lungs1'; 
			//alert("Please enter lungs value");
			//document.getElementById("lungs1").focus();
			//return;
		}
	
	 if(document.forms[0].fluidInput1.value=='')
	 {
		 if(errMsg=='')
				errMsg=errMsg+"Please enter fluid input value "; 
			if(lField=='')
		        lField='fluidInput1'; 
	// alert("Please input fluid input column");	
	//	document.forms[0].fluidInput1.focus();
	//	return;
	 }
	 if(document.forms[0].fluidOutput1.value=='')
	 {
		 if(errMsg=='')
				errMsg=errMsg+"Please enter fluid output value "; 
			if(lField=='')
		        lField='fluidOutput1'; 
	// alert("Please input fluidOutput column");	
	//	document.forms[0].fluidOutput1.focus();
		//return;
	 }
	  if ((document.getElementById("healthy").checked=='' && document.getElementById("healthy").checked==false) && (document.getElementById("notHealthy").checked=='' && document.getElementById("notHealthy").checked==false))
		{
		  if(errMsg=='')
				errMsg=errMsg+"Please select healthy/non healthy radio button "; 
			if(lField=='')
		        lField='healthy'; 
		//alert("Please select healthy/non healthy radio button");	
		//return;
		}
	 if(document.getElementById("notHealthy").checked==true)
	{		
		if(trim(document.forms[0].woundDtls.value)=='')
		{
			 if(errMsg=='')
					errMsg=errMsg+"Please enter non healthy remarks"; 
				if(lField=='')
			        lField='woundDtlsDiv'; 
			//alert("Please enter wound details");	
			//document.forms[0].woundDtls.focus();
			//return;
		}
		if(errMsg !='')
			{
			//var fr = partial(focusBox,document.getElementById(lField));
	 		//jqueryAlertMsg('Clinical notes mandatory fields',errMsg,fr);
	 		alert(errMsg);
			focusBox(document.getElementById(lField));
			}
		if(errMsg =='')
			{
				var fr = partial(savePostClinicalNotesConfirm,flag);
				//jqueryConfirmMsg('clinical notes Confirmation','Do you want to submit post clinical notes ?',fr,null);
				if(confirm('Do you want to submit post clinical notes ?'))
				{
					savePostClinicalNotesConfirm(flag);
				}
			}
		/* else
		{
			
			if(confirm('Do you want to Submit the details?'))
			{
				document.getElementById("SavePostNotesBut").disabled=true;
				document.forms[0].action="/Operations/clinicalNotesAction.do?actionFlag=saveClinicalNotes&caseId=${caseId}&prePostFlag="+flag;
				document.forms[0].submit();
			}
			else
			{
				top.document.close();
			}			
		} */
	}
	
	else
	{
		if(errMsg !='')
		{
		var fr = partial(focusBox,document.getElementById(lField));    //k
 		//jqueryAlertMsg('Clinical notes mandatory fields',errMsg,fr);
 		alert(errMsg);
		focusBox(document.getElementById(lField));
		document.getElementById('SavePostNotesBut').disabled=false;
		}
	if(errMsg =='')
		{
			//var fr = partial(savePostClinicalNotesConfirm,flag);
			//jqueryConfirmMsg('clinical notes Confirmation','Do you want to submit post clinical notes  ?',fr);	
			if(confirm('Do you want to submit post clinical notes?'))
			{
				savePostClinicalNotesConfirm(flag);
			}
		}
		
		/* if(confirm('Do you want to Submit the details?'))
		{
			document.getElementById("SavePostNotesBut").disabled=true;
			document.forms[0].action="/Operations/clinicalNotesAction.do?actionFlag=saveClinicalNotes&caseId=${caseId}&prePostFlag="+flag;
			document.forms[0].submit();
		}
		else
		{
			top.document.close();
		}	 */	
	}
}
function savePostClinicalNotesConfirm(flag)
{
		document.getElementById("SavePostNotesBut").disabled=true;
		document.forms[0].action="/<%=context%>/clinicalNotesAction.do?actionFlag=saveClinicalNotes&caseId=${caseId}&prePostFlag="+flag;
		document.forms[0].submit();
	}
function fn_enableWoundDtls()
{		
	if((document.getElementById("notHealthy").checked!='' && document.getElementById("notHealthy").checked==true))
		document.getElementById('woundDtlsDiv').style.display = '';
	else
		document.getElementById('woundDtlsDiv').style.display = 'none';	
}
function fn_addAttachments(attachType)
{
var url = null;
if(attachType != null && attachType =='surgery')
	{
	if(	(document.forms[0].deathDate != null && document.forms[0].deathDate.value != null && document.forms[0].deathDate.value != ''))
		{
		url = "/<%=context%>/attachmentAction.do?actionFlag=onload&UpdType=ehfClinicalSugeryDeath&caseId=${caseId}&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag=Y";
		}
	else
		url = "/<%=context%>/attachmentAction.do?actionFlag=onload&UpdType=ehfClinicalSugery&caseId=${caseId}&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag=Y";
	}
if(attachType != null && attachType =='discharge')
{
		url = "/<%=context%>/attachmentAction.do?actionFlag=onload&UpdType=ehfClinicaldischarge&caseId=${caseId}&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag=Y";
			
}
if(attachType != null && attachType =='death')
{
		url = "/<%=context%>/attachmentAction.do?actionFlag=onload&UpdType=ehfClinicaldischargeDeath&caseId=${caseId}&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag=Y";	
}


	 window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');	
}

function enableAddNotesDiv(type)
{
	preOPPostOPFlag=type;
	if(document.getElementById("addClinicalNotes").checked!='' && document.getElementById("addClinicalNotes").checked==true)
	{
		document.getElementById('addNotesDiv').style.display = '';
		document.getElementById('addNotesDiv1').style.display = '';
	}
	else
	{
		document.getElementById('addNotesDiv').style.display = 'none';
		document.getElementById('addNotesDiv1').style.display = 'none';
	}
	//parent.fn_resizePage();
	//$("body").mCustomScrollbar("update");
}

function enablePostAddNotesDiv()
{
	if(document.getElementById("postAddClinicalNotes").checked!='' && document.getElementById("postAddClinicalNotes").checked==true)
	{
		document.getElementById('postAddNotesDiv').style.display = '';
		document.getElementById('postAddNotesDiv1').style.display = '';
	}
	else
	{
		document.getElementById('postAddNotesDiv').style.display = 'none';
		document.getElementById('postAddNotesDiv1').style.display = 'none';
	}
	//parent.fn_resizePage();
	//$("body").mCustomScrollbar("update");
}

/* 
function enableComments()
{	
	if((document.getElementById("Complictns").checked!='' && document.getElementById("Complictns").checked==true)
			|| (document.getElementById("BComplictns").checked!='' && document.getElementById("BComplictns").checked==true)
			|| (document.getElementById("RComplictns").checked!='' && document.getElementById("RComplictns").checked==true))
		document.getElementById('commentsDiv').style.display = '';
	else
		document.getElementById('commentsDiv').style.display = 'none';	
}


function enableTheraphyDiv()
{	
	if(document.forms[0].ChemoTheraphy.checked!='' && document.forms[0].ChemoTheraphy.checked==true)
	{		
		document.getElementById('ChemoDiv').style.display = '';
		document.getElementById('BloodDiv').style.display = 'none';
		document.getElementById('RadiationDiv').style.display = 'none';
		document.forms[0].BloodTheraphy.disabled=true;
		document.forms[0].RadiationTheraphy.disabled=true;
	}
	
	else if(document.forms[0].BloodTheraphy.checked!='' && document.forms[0].BloodTheraphy.checked==true)
	{
		document.getElementById('ChemoDiv').style.display = 'none';
		document.getElementById('BloodDiv').style.display = '';
		document.getElementById('RadiationDiv').style.display = 'none';
		document.forms[0].ChemoTheraphy.disabled=true;
		document.forms[0].RadiationTheraphy.disabled=true;
	}
	else if(document.forms[0].RadiationTheraphy.checked!='' && document.forms[0].RadiationTheraphy.checked==true)
	{
		document.getElementById('ChemoDiv').style.display = 'none';
		document.getElementById('BloodDiv').style.display = 'none';
		document.getElementById('RadiationDiv').style.display = '';
		document.forms[0].BloodTheraphy.disabled=true;
		document.forms[0].ChemoTheraphy.disabled=true;
	}	
	else
	{		
		document.forms[0].ChemoTheraphy.disabled=false;
		document.forms[0].BloodTheraphy.disabled=false;
		document.forms[0].RadiationTheraphy.disabled=false;		
		document.getElementById('ChemoDiv').style.display = 'none';
		document.getElementById('BloodDiv').style.display = 'none';
		document.getElementById('RadiationDiv').style.display = 'none';
	}
} */


function fn_getPackageAmt(hospStayAmt,commonCatAmt,icdAmt,nodays,units,idCount,process, slabAmt)
{
	var totamt=0;
	if(process == "IP")
		{
		 totamt=parseFloat(icdAmt*units)+parseFloat(hospStayAmt)+parseFloat(commonCatAmt)+parseFloat(nodays*slabAmt);
		}
	else if (process == "DOP")
		{
		totamt=parseFloat(icdAmt*units)+parseFloat(hospStayAmt*units)+parseFloat(commonCatAmt)+parseFloat(nodays*slabAmt);
		}
  //	var totamt=parseFloat(hospStayAmt*nodays)+parseFloat(commonCatAmt)+parseFloat(icdAmt*units);
	document.getElementById(idCount).innerHTML = totamt;
}


function fn_saveClinicalNotes(flag)
{
	document.getElementById('SaveNotesBut').disabled=true;
	var investgnDtVal=document.forms[0].invesgtnDate.value;
	var bflag=true ;
	var lmedMgmtFlag='${medMgmtFlag}';
	var errMsg = '';
	var lField = '';
	document.forms[0].drugs.value=drugs;
	if(lmedMgmtFlag!='' && document.forms[0].surgStartDt !== undefined)
	{
		if(document.forms[0].surgStartDt.value != null && document.forms[0].surgStartDt.value !='')
		bflag=fn_checNxtFollowpDate(investgnDtVal,document.forms[0].surgStartDt.value);
	}
	if(investgnDtVal=="" || document.forms[0].invesgtnDate.length==0)
	{
			if(errMsg=='')
				errMsg=errMsg+"Please select the date"; 
			if(lField=='')
		        lField='invesgtnDate'; 
		
	}
	else if (investgnDtVal!="" && !fn_checNxtFollowpDate1(investgnDtVal,document.forms[0].ipRegDate.value))
		{
		if(errMsg=='')
			errMsg=errMsg+"Date cannot be less than IP registered date "; 
		if(lField=='')
	        lField='invesgtnDate'; 
		
		}
	if(preOPPostOPFlag != null && preOPPostOPFlag=='PRE')
		{
	 if((investgnDtVal!=""  && !fn_checkSelDate(investgnDtVal))  || 
			(investgnDtVal!="" && !bflag))
	{  
		if('${medMgmtFlag}'=='Y')	
			{
			if(errMsg=='')
				errMsg=errMsg+"Selected Date should be between case treatment start date and current date  "; 
			 
			}
              					  
	    else
	    	{
	    	if(errMsg=='')
				errMsg=errMsg+"Selected Date should be between ip case registered date  and current date  "; 
	    	
	    	}
		if(lField=='')
	        lField='invesgtnDate';      
		
	}
		}
	if(preOPPostOPFlag != null && preOPPostOPFlag=='POST')
	{
		if (investgnDtVal!="" && !fn_checNxtFollowpDate1(investgnDtVal,document.forms[0].ipRegDate.value))
		{
			 if(errMsg=='')
					errMsg=errMsg+"Date cannot be less than IP registered date "; 
				if(lField=='')
			        lField='invesgtnDate'; 
		
		}
		    if( (investgnDtVal!=""  && !fn_checkSelDate(investgnDtVal))  || (investgnDtVal!="" && fn_checNxtFollowpDate1Equal(document.forms[0].surgStartDt.value,investgnDtVal))  )
		{ 
			if('${medMgmtFlag}'=='Y')
				{
				if(errMsg=='')
					errMsg=errMsg+"Selected date should be between case treatment start date and current date  "; 
				if(lField=='')
			        lField='invesgtnDate'; 
				
				}
	               					  
		    else
		    	{
		    	if(errMsg=='')
					errMsg=errMsg+"Selected date should be between case surgery date and current date "; 
				if(lField=='')
			        lField='invesgtnDate'; 
				 
		    	}
	              
			
		} 	
	}
	
	/*
	else if(document.getElementById("Complictns").checked==true || 
			document.getElementById("BComplictns").checked==true ||
			document.getElementById("RComplictns").checked==true)
	{		
		if(trim(document.forms[0].comments.value)=='')
		{
			alert("Please enter comments");	
			document.forms[0].comments.focus();
		}
		else
		{
			document.forms[0].action="/Operations/clinicalNotesAction.do?actionFlag=saveClinicalNotes&caseId=${caseId}&prePostFlag="+"PRE";
			document.forms[0].submit();
		}			
	} */
	
	
	
	 if(document.forms[0].systolic.value=="-1")
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select systolic BP  "; 
		if(lField=='')
	        lField='systolic'; 
	} 
	else if(document.forms[0].diastolic.value=="-1") 
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select diastolic BP  "; 
		if(lField=='')
	        lField='diastolic';
		
	} 
	else if(document.forms[0].pulseRate.value=="-1")
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select pulse rate  "; 
		if(lField=='')
	        lField='pulseRate';
		
	}
	else if(document.forms[0].temperature.value=="-1")
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select temperature  "; 
		if(lField=='')
	        lField='temperature';
		
	}
	else if(document.forms[0].wardType.value=="-1")
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select ward type "; 
		if(lField=='')
	        lField='wardType';
		
	}
	else if(document.forms[0].respRate.value=="-1")
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select respiratory Rate  "; 
		if(lField=='')
	        lField='respRate';
		
	}
	else if(document.forms[0].heartRate.value=="-1")
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select heart sounds "; 
		if(lField=='')
	        lField='heartRate';
	}
	else if(trim(document.getElementById("lungs").value)=='')
	{
		if(errMsg=='')
			errMsg=errMsg+"Please enter lungs value "; 
		if(lField=='')
	        lField='lungs';
		
	}
	else if(trim(document.getElementById("fluidInput").value)=='')
	{
		if(errMsg=='')
			errMsg=errMsg+"Please enter fluid input value "; 
		if(lField=='')
	        lField='fluidInput';
		
	}
	else if(trim(document.getElementById("fluidOutput").value)=='')
	{
		if(errMsg=='')
			errMsg=errMsg+"Please enter fluid output value "; 
		if(lField=='')
	        lField='fluidOutput';
	}
	else if(trim(document.getElementById("docName").value)=='')
	{
		if(errMsg=='')
			errMsg=errMsg+"Please enter doctor name"; 
		if(lField=='')
	        lField='docName';
	}
	else if(trim(document.getElementById("remarks").value)=='')
	{
		if(errMsg=='')
			errMsg=errMsg+"Please enter daily doctor notes "; 
		if(lField=='')
	        lField='remarks';
	}
	else if(preOPPostOPFlag != null && preOPPostOPFlag=='POST')
		{
	if ((document.getElementById("healthy").checked=='' && document.getElementById("healthy").checked==false) && (document.getElementById("notHealthy").checked=='' && document.getElementById("notHealthy").checked==false))
	{
	  if(errMsg=='')
			errMsg=errMsg+"Please select healthy/non healthy radio button "; 
		if(lField=='')
	        lField='healthy'; 
	}
 if(document.getElementById("notHealthy").checked==true)
{		
	if(document.forms[0].woundDtls.value=='')
	{
		 if(errMsg=='')
				errMsg=errMsg+"Please enter non healthy remarks "; 
			if(lField=='')
		        lField='woundDtlsDiv'; 
	}}
		}
	//else if(drugs == null || drugs=='')
	// {
	// if(errMsg=='')
	//		errMsg=errMsg+"Please select drugs<br> "; 
	// lField='drugTypeCode'; 
	// }
	if(errMsg =='')
	{
		document.getElementById('SaveNotesBut').disabled=false;
		//var fr = partial(saveClinicalNotesConfirm,preOPPostOPFlag);
		//jqueryConfirmMsg('Add clinical notes Confirmation','Do you want to add clinical notes ?',fr);	
		if(confirm('Do you want to add clinical notes ?'))
		{
			saveClinicalNotesConfirm(preOPPostOPFlag);
		}
	}
	else
	{
		var fr = partial(focusBox,document.getElementById(lField));		
 		//jqueryAlertMsg('Clinical notes mandatory fields',errMsg,fr);
		alert(errMsg);
		focusBox(document.getElementById(lField));
		document.getElementById('SaveNotesBut').disabled=false;
	}
}
function saveClinicalNotesConfirm(flag)
{
	document.getElementById("SaveNotesBut").disabled=true;
	document.forms[0].action="/<%=context%>/clinicalNotesAction.do?actionFlag=saveClinicalNotes&caseId=${caseId}&prePostFlag="+flag;
	document.forms[0].submit();
	}
function fn_saveSurgeryDtls()
{	
	document.getElementById('SaveSurgeryBut').disabled=true;
	document.getElementById('addViewAttachSur').disabled=true;
	var lmedMgmtFlag='${medMgmtFlag}';
	var lAfterSurgPhoCnt='${PreauthClinicalNotesAttachCntVO.AFSURG_PHT_CNT}';	
	var surgerySelDate=document.forms[0].surgStartDt.value;
	var selDeathDt=document.forms[0].deathDate.value;		
	//surgery time dtls in case of surgeon not trting doc
	document.forms[0].surgSaveDate.value = document.forms[0].surgStartDt.value;
	var medicalFlag = '${medicalFlag}';
	var attachType = "ehfClinicalSugery";
	var errMsgF='';
	var errMsg='';
	var lField='';
	if(medicalFlag != null && medicalFlag =='Y')
		{
		if(document.forms[0].treatSurgeonName.value=='')
		{
			if(errMsgF=='')
				errMsgF=errMsgF+"Please enter treating doctor name  "; 
			if(lField=='')
		        lField='treatSurgeonName'; 
			//alert("Please enter treating doctor name");	
			//document.forms[0].treatSurgeonName.focus();
			//return;
		}
		 if(document.forms[0].treatSurgeonRegNo.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter treating doctor Reg No  "; 
				if(lField=='')
			        lField='treatSurgeonRegNo'; 
			//alert("Please enter treating doctor Reg No");	
			//document.forms[0].treatSurgeonRegNo.focus();
			//return;
		}
		 if(document.forms[0].treatSurgeonQual.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter treating doctor qualification "; 
				if(lField=='')
			        lField='treatSurgeonQual'; 
			//alert("Please enter treating doctor qualification");	
			//document.forms[0].treatSurgeonQual.focus();
			//return;
		}
		 if(document.forms[0].treatSurgeonCnctNo.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter treating doctor contact no"; 
				if(lField=='')
			        lField='treatSurgeonCnctNo'; 
			//alert("Please enter treating doctor contact no");	
			//document.forms[0].treatSurgeonCnctNo.focus();
			//return;
		}
		/*  if(document.forms[0].treatSurgStartDt.value=='' || document.forms[0].treatSurgStartDt.value == 'undefined')
		{
			alert("Please enter treatment date");	
			document.forms[0].treatSurgStartDt.focus();	
			return;	
		}
		 if(document.forms[0].treatSurgStartDt.value!='' && fn_checNxtFollowpDate(document.forms[0].ipRegDate.value,document.forms[0].treatSurgStartDt.value) )
		{
			alert(" Surgery date should be greater than in patient case registered date");	
			document.forms[0].treatSurgStartDt.focus();	
			document.forms[0].treatSurgStartDt.value='';
			return;	
		}
		 if(document.forms[0].treatSurgStartDt.value!=null && document.forms[0].treatSurgStartDt.value!='' && !fn_checkSelDate(document.forms[0].treatSurgStartDt.value))
			{
			 	alert('Surgery date cannot be future date');	
			 	document.forms[0].treatSurgStartDt.focus();	
			 	document.forms[0].treatSurgStartDt.value='';
			 	return; 	
			}  */
		 /* if(document.forms[0].treatDeathDate.value!='' && fn_checNxtFollowpDate(document.forms[0].ipRegDate.value,document.forms[0].treatDeathDate.value) )
			{
				alert(" Death date should be greater than in patient case registered date");	
				document.forms[0].treatDeathDate.focus();	
				document.forms[0].treatDeathDate.value='';
				return;	
			}
		 if(document.forms[0].treatDeathDate.value!=null && document.forms[0].treatDeathDate.value!='' && !fn_checkSelDate(document.forms[0].treatDeathDate.value))
			{
			 	alert('Death date cannot be future date');	
			 	document.forms[0].treatDeathDate.focus();
			 	document.forms[0].treatDeathDate.value='';
			 	return; 	
			}
		 if(document.forms[0].treatDeathDate.value!=null && document.forms[0].treatDeathDate.value!='' )
			{
			attachType="ehfClinicalSugeryDeath"; 
			} */
		 if(document.forms[0].treatAsstSurName.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant doctor name"; 
				if(lField=='')
			        lField='treatAsstSurName'; 
			//alert("Please enter assistant doctor name ");	
			//document.forms[0].treatAsstSurName.focus();
			//return;
		}
		 if(document.forms[0].treatAsstSurRegNo.value=='')
			{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant doctor reg no "; 
				if(lField=='')
			        lField='treatAsstSurRegNo'; 
			// alert("Please enter assistant doctor reg no ");	
			//	document.forms[0].treatAsstSurRegNo.focus();
			//	return;
			}
		 if(document.forms[0].treatAsstSurQual.value=='')
			{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant doctor qualification  "; 
				if(lField=='')
			        lField='treatAsstSurQual'; 
			// alert("Please enter assistant doctor qualification ");	
			//	document.forms[0].treatAsstSurQual.focus();
			//	return;
			}
		 if(document.forms[0].treatAsstSurContctNo.value=='')
			{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant doctor contact no  "; 
				if(lField=='')
			        lField='treatAsstSurContctNo'; 
			// alert("Please enter assistant doctor contact no ");	
			//	document.forms[0].treatAsstSurContctNo.focus();
			//	return;
			}
		 if(document.forms[0].treatParadMedicName.value=='')
			{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter   paramedic name "; 
				if(lField=='')
			        lField='treatParadMedicName'; 
			// alert("Please enter   paramedic name ");	
				//document.forms[0].treatParadMedicName.focus();
				//return;
			}
		 if(document.forms[0].treatNurseName.value=='')
			{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter  nurse name "; 
				if(lField=='')
			        lField='treatNurseName'; 
			// alert("Please enter  nurse name ");	
			//	document.forms[0].treatNurseName.focus();
			//	return;
			}
		 if(document.forms[0].treatExpHospStay.value=='-1')
			{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please select  excpected hospital stay "; 
				if(lField=='')
			        lField='treatExpHospStay'; 
			 //alert("Please select  excpected hospital stay name ");	
			//	document.forms[0].treatExpHospStay.focus();
			//	return;
			}
		
		} // end of treatment...details
		
		 /* var count=0;
		 var subVal=null,toothUnits=null;
		if(medicalFlag == null || medicalFlag =='' || medicalFlag !='Y' || lmedMgmtFlag=='N')
			{
				if(dentalSurg!=null && dentalSurg=='Y')
					{
						document.getElementById('caseUnits').value='';
						document.getElementById('toothedUnits').value='';
						var alertCont='';
						for(var i=0;i<100;i++)
							{
								if(document.getElementById('unitsDropDown'+i)!=null)
									{
										if(document.getElementById('unitsDropDown'+i).value=='-1')
											{
												alertCont='Please select Actual Treated Units';
												focusId='unitsDropDown'+i;
											}
										if(alertCont!=null && alertCont.length==0 && 
												document.getElementById('unitstxtArea'+i).value=='')
											{
												alertCont='Please enter Data in to Teeth No/Quadrant No';
												focusId='unitstxtArea'+i;
											}
										else if(alertCont!=null && alertCont.length==0)
											{
												var valueVal=document.getElementById('unitstxtArea'+i).value;
												var newCount=0;
												for(var j=0;j<valueVal.length;j++)
													{
														if(valueVal[j]==' ')
															newCount++;
													}
												if(newCount==valueVal.length)
													{
														alertCont='Data in Teeth No/Quadrant No cannot have only spaces.';
														focusId='unitstxtArea'+i;
													}
											}
										if(alertCont.length>0 && alertCont!='' && focusId!='' && focusId.length>0)
											{
												alert(alertCont);
												focusBox(document.getElementById(focusId));
												document.getElementById('SaveSurgeryBut').disabled=false;
												document.getElementById('addViewAttachSur').disabled=false;
												count++;
												return false;
											}
									}
								else 
									{
										break;
									}
							}
						if(count==0)
							{
								for(var i=0;i<100;i++)
									{
										if(document.getElementById('unitsDropDown'+i)!=null && 
												document.getElementById('unitstxtArea'+i)!=null)
											{
												if(subVal==null)
													subVal=document.getElementById('unitsDropDown'+i).getAttribute("name")+'@'+document.getElementById('unitsDropDown'+i).value;
												else
													subVal+='~'+document.getElementById('unitsDropDown'+i).getAttribute("name")+'@'+document.getElementById('unitsDropDown'+i).value;
												
												if(toothUnits==null)
													toothUnits=document.getElementById('unitstxtArea'+i).getAttribute("name")+'@'+document.getElementById('unitstxtArea'+i).value;
												else
													toothUnits+='~'+document.getElementById('unitstxtArea'+i).getAttribute("name")+'@'+document.getElementById('unitstxtArea'+i).value;
											}
										else
											break;
									}	
							}
					}
				
				document.getElementById('dentalSurg').value=dentalSurg;
				document.getElementById('caseUnits').value=subVal;
				document.getElementById('toothedUnits').value=toothUnits;
			}  */	
		
		if(medicalFlag == null || medicalFlag =='' || medicalFlag !='Y' || lmedMgmtFlag=='N')
		{
			if(document.forms[0].surgeonName.value=='')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please enter surgeon name  "; 
					if(lField=='')
				        lField='surgeonName';
				//alert("Please enter surgeon name");	
				//document.forms[0].surgeonName.focus();
				//return;
			}
			 if(document.forms[0].surgeonRegNo.value=='')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please enter surgeon Reg No "; 
					if(lField=='')
				        lField='surgeonRegNo';
			//	alert("Please enter surgeon Reg No");	
			//	document.forms[0].surgeonRegNo.focus();
			//	return;
			}
			 if(document.forms[0].surgeonQual.value=='')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please enter surgeon qualification "; 
					if(lField=='')
				        lField='surgeonQual';
				//alert("Please enter surgeon qualification");	
				//document.forms[0].surgeonQual.focus();
				//return;
			}
			 if(document.forms[0].surgeonCnctNo.value=='')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please enter surgeon contact no  "; 
					if(lField=='')
				        lField='surgeonCnctNo';
				//alert("Please enter surgeon contact no");	
				//document.forms[0].surgeonCnctNo.focus();
				//return;
			}	
		}// end of surgical details
		
	//Anesthetist chk conditions
	if(medicalFlag == null || medicalFlag =='' || medicalFlag !='Y' || lmedMgmtFlag=='N')
	{
		 if(lmedMgmtFlag=='N' && document.forms[0].anesthetistName.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter anesthetist name  "; 
				if(lField=='')
					lField='anesthetistName';
			//alert("Please enter anesthetist name");	
			//document.forms[0].anesthetistName.focus();adi tappa..
			
			//return;
		}
		 if(lmedMgmtFlag=='N' && document.forms[0].anesthetistRegNo.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter anesthetist reg no "; 
				if(lField=='')
					lField='anesthetistRegNo';
			//alert("Please enter anesthetist reg no");	
			//document.forms[0].anesthetistRegNo.focus();
			//return;
		}
		 if(lmedMgmtFlag=='N' && document.forms[0].anesthetistMbNo.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter anesthetist mobile no "; 
				if(lField=='')
					lField='anesthetistMbNo';
			//alert("Please enter anesthetist mobile no");	
			//document.forms[0].anesthetistMbNo.focus();
			//return;
		}
		 if(lmedMgmtFlag=='N' && document.forms[0].anesthesiaType.value=='-1')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please select anesthesia Type "; 
					if(lField=='')
						lField='anesthesiaType';
				//alert("Please enter anesthetist mobile no");	
				//document.forms[0].anesthetistMbNo.focus();
				//return;
			}
		 
		 if(document.forms[0].asstSurName.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant surgeon name "; 
				if(lField=='')
					lField='asstSurName';
			//alert("Please enter assistant surgeon name");	
			//document.forms[0].asstSurName.focus();
			//return;
		}
		 if(document.forms[0].asstSurRegNo.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant surgeon reg no "; 
				if(lField=='')
					lField='asstSurRegNo';
			//alert("Please enter assistant surgeon reg no");	
			//document.forms[0].asstSurRegNo.focus();
			//return;
		}
		 if(document.forms[0].asstSurQual.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant surgeon qualification "; 
				if(lField=='')
					lField='asstSurQual';
			//alert("Please enter assistant surgeon qualification");	
			//document.forms[0].asstSurQual.focus();
			//return;
		}
		 if(document.forms[0].asstSurContctNo.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter assistant surgeon contact no"; 
				if(lField=='')
					lField='asstSurContctNo';
			//alert("Please enter assistant surgeon contact no");	
			//document.forms[0].asstSurContctNo.focus();
			//return;
		}
		 if(document.forms[0].paradMedicName.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter paramedic name "; 
				if(lField=='')
					lField='paradMedicName';
			//alert("Please enter paradMedic name");	
			//document.forms[0].paradMedicName.focus();
			//return;
		}
		 if(document.forms[0].nurseName.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter nurse name "; 
				if(lField=='')
					lField='nurseName';
			//alert("Please enter nurse name");	
			//document.forms[0].nurseName.focus();
			//return;
		}
		 if(document.forms[0].expHospStay.value=="-1")
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please select expected hospital stay "; 
				if(lField=='')
					lField='expHospStay';
			//alert("Please select expected hospital stay");	
			//document.forms[0].expHospStay.focus();
			//return;
		}	
	}
	
	 if(document.forms[0].surgStartDt.value=='' || document.forms[0].surgStartDt.value == 'undefined')
	{
		 if(errMsgF=='')
				errMsgF=errMsgF+"Please enter surgery date "; 
			if(lField=='')
		        lField='surgStartDt';
		//alert("Please enter surgery date");	
		//document.forms[0].surgStartDt.focus();	
		//return;	
	}
	 if(document.forms[0].surgStartDt.value!='' && fn_checNxtFollowpDate(document.forms[0].ipRegDate.value,document.forms[0].surgStartDt.value) )
	{
		 if(document.forms[0].teleRegDate.value ==null && document.forms[0].teleRegDate.value =='')
			{
			 if(errMsgF=='')
				errMsgF=errMsgF+"Surgery date should be greater than in patient case registered date "; 
			 if(lField=='')
		        lField='surgStartDt';
			}
		//alert(" Surgery date should be greater than in patient case registered date");	
		//document.forms[0].surgStartDt.focus();	
		//document.forms[0].surgStartDt.value = '';
		//return;	
	}
	 if(surgerySelDate!=null && surgerySelDate!='' && !fn_checkSelDate(surgerySelDate))
	{
		 if(errMsgF=='')
				errMsgF=errMsgF+"Surgery date cannot be future date  "; 
			if(lField=='')
		        lField='surgStartDt';
	 	//alert('Surgery date cannot be future date');	
	 	//return; 	
	} 
	 if(lmedMgmtFlag=='N' && document.forms[0].surgStrHrs.value=='-1')
	{
		 if(errMsgF=='')
				errMsgF=errMsgF+"Please select surgery start hrs  "; 
			if(lField=='')
		        lField='surgStrHrs';
		//alert("Please select surgery start hrs");	
		//document.forms[0].surgStrHrs.focus();
		//return;
	}
	if(lmedMgmtFlag=='N' && document.forms[0].surgStrMins.value=='-1')
	{
		 if(errMsgF=='')
				errMsgF=errMsgF+"Please select surgery start mins  "; 
			if(lField=='')
		        lField='surgStrMins';
		//alert("Please select surgery start mins");	
		//document.forms[0].surgStrMins.focus();
		//return;
	}
   if(lmedMgmtFlag=='N' && document.forms[0].surgEndHrs.value=='-1')
	{
	   if(errMsgF=='')
			errMsgF=errMsgF+"Please select surgery end hrs  "; 
		if(lField=='')
	        lField='surgEndHrs';
		//alert("Please select surgery end hrs");	
		//document.forms[0].surgEndHrs.focus();
		//return;
	}
   if(lmedMgmtFlag=='N')
	{
	// check if surgert date equal to ip registered date
	if(document.forms[0].teleRegDate.value ==null || document.forms[0].teleRegDate.value =='')
	{
	 if(fn_checkSelDateEqual(surgerySelDate,document.forms[0].ipRegDate.value))
			 {
		 var time = '${ipRegDateTime}'.split(":");
	   if(!fn_DiffBtwSugyStrtEndTime(time[0],time[1],document.forms[0].surgStrHrs.value,document.forms[0].surgStrMins.value))
		{
		   if(errMsgF=='')
				errMsgF=errMsgF+"Please check surgery start time should be greater than IP registered time  "; 
			if(lField=='')
		        lField='surgStrHrs';
		  // alert("Please check surgery start time should be greater than IP registserd time");
		  // document.forms[0].surgStrHrs.focus();
			//return;
		}
	   
			 }
	}
	if(document.forms[0].teleRegDate.value !=null && document.forms[0].teleRegDate.value !='')
	{
		if(fn_checkSelDateEqual(surgerySelDate,document.forms[0].teleRegDate.value))
		 {
	 var time = '${teleRegDateTime}'.split(":");
  if(!fn_DiffBtwSugyStrtEndTime(time[0],time[1],document.forms[0].surgStrHrs.value,document.forms[0].surgStrMins.value))
	{
	   if(errMsgF=='')
			errMsgF=errMsgF+"Please check surgery start time should be greater than Telephonic registered time  "; 
		if(lField=='')
	        lField='surgStrHrs';
	  // alert("Please check surgery start time should be greater than IP registserd time");
	  // document.forms[0].surgStrHrs.focus();
		//return;
	}
  
		 }
	}
	 if(fn_checkStartGtCurrTime(surgerySelDate,document.forms[0].surgStrHrs.value,document.forms[0].surgStrMins.value))
	   {
	   if(errMsgF=='')
			errMsgF=errMsgF+"Please check surgery start time should be less than current time "; 
		if(lField=='')
	        lField='surgStrHrs';
	   }
	}
	 if(lmedMgmtFlag=='N' && document.forms[0].surgEndMins.value=='-1')
	{
		 if(errMsgF=='')
				errMsgF=errMsgF+"Please select surgery end mins  "; 
			if(lField=='')
		        lField='surgEndMins';
		//alert("Please select surgery end mins");	
		//document.forms[0].surgEndMins.focus();
		//return;
	}
	 if(lmedMgmtFlag=='N')
		{
	 if(!fn_DiffBtwSugyStrtEndTime(document.forms[0].surgStrHrs.value,document.forms[0].surgStrMins.value,document.forms[0].surgEndHrs.value,document.forms[0].surgEndMins.value))
	{
		 if(errMsgF=='')
				errMsgF=errMsgF+"Please check surgery end time should be greater than surgery start time  "; 
			if(lField=='')
		        lField='surgEndMins';
			//alert("Please check surgery end time should be greater than surgery start time");	
		//return;
	}
	 if(fn_checkStartGtCurrTime(surgerySelDate,document.forms[0].surgEndHrs.value,document.forms[0].surgEndMins.value))
	   {
	   if(errMsgF=='')
			errMsgF=errMsgF+"Please check surgery end time should be less than current time "; 
		if(lField=='')
	        lField='surgEndMins';
	   }
	
		}

	/* else if(lAfterSurgPhoCnt==0 || lAfterSurgPhoCnt=='')
	{				
        alert('Please add After surgery/Treatment photo');
        return;
	}  */ 
	 if(selDeathDt!=null && selDeathDt!='')
	{				  		 
		if(!(fn_checkSelDate(selDeathDt)))
		 {
			if(errMsgF=='')
				errMsgF=errMsgF+"Death date cannot be future Date "; 
			if(lField=='')
		        lField='deathDate';
		 	//alert('Death date cannot be future Date');
		 	//document.forms[0].deathDate.focus();
		 	//document.forms[0].deathDate.value='';
		 	//return;
		 }
		
		 if(surgerySelDate!=null && surgerySelDate!='')
		 {			 
			 if(fn_checNxtFollowpDate(surgerySelDate,selDeathDt))
			 {
				 if(errMsgF=='')
						errMsgF=errMsgF+"Death date cannot be less than the surgery/therapy Date,\nsurgery/therapy date updated is "+document.forms[0].surgStartDt.value ; 
					if(lField=='')
				        lField='deathDate';
			 	//alert('Death date cannot be less than the surgery/therapy Date,\nsurgery/therapy date updated is '+document.forms[0].surgStartDt.value );
			 	//document.forms[0].deathDate.focus();
			 	//document.forms[0].deathDate.value='';
			 	//return;
			 }
			/*  else if(fn_checkRegSelDate(surgerySelDate,selDeathDt))
			 {
				 alert("Death date cannot be Less than Treatment OR SURGERY Start Date"+surgerySelDate);
				 return;
			}	 */			 
		 }
	  
		/* if(Ext.getCmp('deathDateField').getValue()!=null  && Ext.getCmp('deathDateField').getValue()!='' &&
													(arr.lDthSumryAtchCnt=='' || arr.lDthSumryAtchCnt==0) &&
													(document.getElementById('dthsummary').value!=null 
												  && document.getElementById('dthsummary').value==0) */
		//DEATH_CERTI_CNT,DEATH_SUMMRY_CN		
		var lDeathCerCnt='${PreauthClinicalNotesAttachCntVO.DEATH_CERTI_CNT}';
		var lDeathSummryCnt='${PreauthClinicalNotesAttachCntVO.DEATH_SUMMRY_CNT}';
		/* alert('lAfterSurgPhoCnt'+lAfterSurgPhoCnt);
		alert('lDeathCerCnt'+lDeathCerCnt);
		alert('lDeathSummryCnt'+lDeathSummryCnt); */
		
		/* if(lDeathSummryCnt=='' || lDeathSummryCnt==0)
			{
			  alert("Please Add Death Summary");
			  return;
			} */
	}
	
	// To check for the Intra OP Attachments for only Surgical cases
	if(medicalFlag == null || medicalFlag =='' || medicalFlag !='Y' || lmedMgmtFlag=='N')
	{
		if(document.forms[0].intraOpPotos[0] != null && document.forms[0].intraOpPotos[0].checked && document.getElementById('webExRec').value <1)
			{
			if(errMsgF=='')
				errMsgF=errMsgF+"Please attach Intra OP Photos/Web Ex Taken attachments" ;

			 	lField='intraOpPotos[0] ';

			 	lField='webExRec';

			}
		if(document.forms[0].videoRec[0] != null && document.forms[0].videoRec[0].checked && document.getElementById('videoRecAttach').value <1)
		{
		if(errMsgF=='')
			errMsgF=errMsgF+"Please attach Video Recording attachments" ;

		 	lField='videoRec[0]';

		 	lField='videoRecAttach';
		}
	}
	//alert(errMsgF);
	if(errMsgF !='')
		{
		//var fr = partial(focusBox(document.getElementById(lField)));  //k
		//jqueryAlertMsg('Clinical notes mandatory fields',errMsgF,fr);
		alert(errMsgF);
		focusBox(document.getElementById(lField));
		document.getElementById('SaveSurgeryBut').disabled=false;
		document.getElementById('addViewAttachSur').disabled=false;
		return;
		}
	 if(document.forms[0].deathDate.value!=null && document.forms[0].deathDate.value!='' )
		{
		attachType="ehfClinicalSugeryDeath"; 
		}
	 if(errMsgF =='')
		{
	    fn_testMandatoryAttach('surgery',attachType,null);
		}
		
					
	
}

function fn_enableDischargeDeathDiv()
{
	if((document.getElementById("discharge").checked!='' && document.getElementById("discharge").checked==true))
		document.getElementById('dischargeDiv').style.display = '';
	else
		document.getElementById('dischargeDiv').style.display = 'none';	
	
	if((document.getElementById("death").checked!='' && document.getElementById("death").checked==true))
		document.getElementById('deathDiv').style.display = '';
	else
		document.getElementById('deathDiv').style.display = 'none';	
	//parent.fn_resizePage();
	//$("body").mCustomScrollbar("update");
}
function saveDischargeSummaryConfirm(url)
{
	document.getElementById("SaveDisSumryBut").disabled=true;
	if(document.getElementById("SubmitDisSumryBut"))
	{
		document.getElementById("SubmitDisSumryBut").disabled=true;
	}
	document.getElementById("addViewAttachDis").disabled=true;
	document.forms[0].action=url;
	document.forms[0].submit();
}


function fn_printDischargeSumry()
{
if(confirm("Do you Wish To Print Discharge Summary Form ?"))
{
	
	var url="/<%=context%>/clinicalNotesAction.do?actionFlag=printDischargeForm&caseId=${caseId}";	
	childWindow=window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=960, height=600, top=100,left=50');
}
else 
{
	return false;
}
	/*document.forms[0].action=url;
	document.forms[0].submit();*/
}



function fn_saveSubDischargeSumry(flag)
{	
	var errMsgF='';
	var lField='';
	var dischargeDeathFlag;
	if(document.getElementById("SaveDisSumryBut"))
			document.getElementById("SaveDisSumryBut").disabled=true;
	if(document.getElementById("SubmitDisSumryBut"))
			document.getElementById("SubmitDisSumryBut").disabled=true;
	if(document.getElementById("addViewAttachDis"))
			document.getElementById("addViewAttachDis").disabled=true;
	
	var medicalFlag = '${medicalFlag}';
	var lmedMgmtFlag='${medMgmtFlag}';
	var count=0;
	var subVal=null,toothUnits=null;
	if(medicalFlag == null || medicalFlag =='' || medicalFlag !='Y' || lmedMgmtFlag=='N' )
		{
			if(dentalSurg!=null && dentalSurg=='Y' && flag=='submit')
				{
					document.getElementById('caseUnits').value='';
					document.getElementById('toothedUnits').value='';
					var alertCont='';
					for(var i=0;i<100;i++)
						{
							if(document.getElementById('unitsDropDown'+i)!=null)
								{
									if(document.getElementById('unitsDropDown'+i).value=='-1')
										{
											alertCont='Please select Actual Treated Units';
											focusId='unitsDropDown'+i;
										}
									if(alertCont!=null && alertCont.length==0 && 
											document.getElementById('unitstxtArea'+i).value=='')
										{
											alertCont='Please enter Data in to Teeth No/Quadrant No';
											focusId='unitstxtArea'+i;
										}
									else if(alertCont!=null && alertCont.length==0)
										{
											var valueVal=document.getElementById('unitstxtArea'+i).value;
											var newCount=0;
											for(var j=0;j<valueVal.length;j++)
												{
													if(valueVal[j]==' ')
														newCount++;
												}
											if(newCount==valueVal.length)
												{
													alertCont='Data in Teeth No/Quadrant No cannot have only spaces.';
													focusId='unitstxtArea'+i;
												}
										}
									if(alertCont.length>0 && alertCont!='' && focusId!='' && focusId.length>0)
										{
											alert(alertCont);
											focusBox(document.getElementById(focusId));
											document.getElementById('SaveDisSumryBut').disabled=false;
											document.getElementById('SubmitDisSumryBut').disabled=false;
											document.getElementById('addViewAttachDis').disabled=false;
											count++;
											return false;
										}
								}
							else 
								{
									break;
								}
						}
					if(count==0)
						{
							for(var i=0;i<100;i++)
								{
									if(document.getElementById('unitsDropDown'+i)!=null && 
											document.getElementById('unitstxtArea'+i)!=null)
										{
											if(subVal==null)
												subVal=document.getElementById('unitsDropDown'+i).getAttribute("name")+'@'+document.getElementById('unitsDropDown'+i).value;
											else
												subVal+='~'+document.getElementById('unitsDropDown'+i).getAttribute("name")+'@'+document.getElementById('unitsDropDown'+i).value;
											
											if(toothUnits==null)
												toothUnits=document.getElementById('unitstxtArea'+i).getAttribute("name")+'@'+document.getElementById('unitstxtArea'+i).value;
											else
												toothUnits+='~'+document.getElementById('unitstxtArea'+i).getAttribute("name")+'@'+document.getElementById('unitstxtArea'+i).value;
										}
									else
										break;
								}	
						}
				}
			
			document.getElementById('dentalSurg').value=dentalSurg;
			document.getElementById('caseUnits').value=subVal;
			document.getElementById('toothedUnits').value=toothUnits;
		} 
	
			
	if((document.getElementById("discharge") != null && document.getElementById("discharge").checked!='' && document.getElementById("discharge").checked==true))
		dischargeDeathFlag="discharge";
	if((document.getElementById("death") != null && document.getElementById("death").checked!='' && document.getElementById("death").checked==true))
		dischargeDeathFlag="death";
	if(document.getElementById("death") == null || document.getElementById("death") =='undefined')
		{
		if(document.getElementById("deathDiv").style.display=='block')
			{
			dischargeDeathFlag="death";
			}
		else
			dischargeDeathFlag="discharge";
		}
	var url='/<%=context%>/clinicalNotesAction.do?actionFlag=dischSumryUpdate&saveSubmitFlag='+flag+'&disDeathFlag='+dischargeDeathFlag;
	var surgerySelDate=document.forms[0].surgStartDt.value;
	//if save no validations
	if(flag=='save')
	{
		//var fr = partial(saveDischargeSummaryConfirm,url);
		//jqueryConfirmMsg('Clinical notes Confirmation','Case status is still Surgery Update/Treatment Schedule Update. Discharge details can be saved any number of times by clicking the Save button. Complete the discharge details and click Submit button to change the status of the case to Discharge Update. Once the status is Discharge update no more changes can be done to discharge summary.Do you want to Save the details ?',fr);
		
		//fr
		for(var i=0;i<100;i++)
			{
				if(document.getElementById('unitsDropDown'+i)!=null && 
						document.getElementById('unitstxtArea'+i)!=null)
					{
						if(subVal==null)
							subVal=document.getElementById('unitsDropDown'+i).getAttribute("name")+'@'+document.getElementById('unitsDropDown'+i).value;
						else
							subVal+='~'+document.getElementById('unitsDropDown'+i).getAttribute("name")+'@'+document.getElementById('unitsDropDown'+i).value;
						
						if(toothUnits==null)
							toothUnits=document.getElementById('unitstxtArea'+i).getAttribute("name")+'@'+document.getElementById('unitstxtArea'+i).value;
						else
							toothUnits+='~'+document.getElementById('unitstxtArea'+i).getAttribute("name")+'@'+document.getElementById('unitstxtArea'+i).value;
					}
				else
					break;
			}	

		document.getElementById('dentalSurg').value=dentalSurg;
		document.getElementById('caseUnits').value=subVal;
		document.getElementById('toothedUnits').value=toothUnits;
		
		
		 if(confirm('Case status is still Surgery Update/Treatment Schedule Update. Discharge details can be saved any number of times by clicking the Save button. Complete the discharge details and click Submit button to change the status of the case to Discharge Update. Once the status is Discharge update no more changes can be done to discharge summary.Do you want to Save the details?'))
		{
			saveDischargeSummaryConfirm(url);
		}
		
		document.getElementById("SaveDisSumryBut").disabled=false;
		if(document.getElementById("SubmitDisSumryBut"))
		{
			document.getElementById("SubmitDisSumryBut").disabled=false;
		}
		document.getElementById("addViewAttachDis").disabled=false;
		
	}
	else
	{				
		//common discharge summary validations
		if('${dischargeSumryFlag}' == 'Y')
		{
		var pendCnt="${PostOpNotespPendCnt}";		
		if(pendCnt!=null && pendCnt!='') 
		{	
			alert("Please enter "+pendCnt+" Post Op. Notes to Contine");
			document.getElementById("SaveDisSumryBut").disabled=false;
			if(document.getElementById("SubmitDisSumryBut"))
			{
				document.getElementById("SubmitDisSumryBut").disabled=false;
			}
			return;
		}
		if(document.forms[0].treatmentGvn.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter treatment Given  "; 
				if(lField=='')
			        lField='treatmentGvn'; 
			//alert('Please enter treatment Given');	
			//document.forms[0].treatmentGvn.focus();
			//return;
		}
		 if(document.forms[0].operatveFindgs.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter operative findings  "; 
				if(lField=='')
			        lField='operatveFindgs'; 
			//alert('Please enter operative findings');	
			//document.forms[0].operatveFindgs.focus();
			//return;
		}
		 if(document.forms[0].postOperatvePerd.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter post operative period  "; 
				if(lField=='')
			        lField='postOperatvePerd'; 
			//alert('Please enter post operative period');	
			//document.forms[0].postOperatvePerd.focus();
			//return;
		}
		 if(document.forms[0].postSplInvstgtns.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter post special invstigations  "; 
				if(lField=='')
			        lField='postSplInvstgtns'; 
			//alert('Please enter post special invstigations');	
			//document.forms[0].postSplInvstgtns.focus();
			//return;
		}
		else if(document.forms[0].statusAtDischrg.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter status at the time of discharge "; 
				if(lField=='')
			        lField='statusAtDischrg'; 
			//alert('Please enter status at the time of discharge');	
			//document.forms[0].statusAtDischrg.focus();
			//return;
		}
		 if(document.forms[0].review.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter review "; 
				if(lField=='')
			        lField='review'; 
			//alert('Please enter review');	
			//document.forms[0].review.focus();
			//return;
		}
		 if(document.forms[0].advice.value=='')
		{
			 if(errMsgF=='')
					errMsgF=errMsgF+"Please enter advice "; 
				if(lField=='')
			        lField='advice'; 
			//alert('Please enter advice');	
			//document.forms[0].advice.focus();
			//return;
		}
	}
		if(document.getElementById("discharge") != null)
			{
		//For discharge radio validations
		 if((document.getElementById("discharge").checked!='' && document.getElementById("discharge").checked==true))
		{
			var selDischargeDt=document.forms[0].disDate.value;
			var selNxtFupDt=document.forms[0].nxtFollUpDt.value;
			if(selDischargeDt=='')
			{
				if(errMsgF=='')
					errMsgF=errMsgF+"Please select discharge date "; 
				if(lField=='')
			        lField='disDate'; 
				//alert('Please select discharge date');	
				//document.forms[0].disDate.focus();
				//return;
			}			
			/* else if(selDischargeDt!=null && selDischargeDt!='' && !(fn_checkSelDate(selDischargeDt)))
			{				  		 
			
			 	alert('Discharge Date cannot be Future Date');
			 	return;
			} */
			else if(selDischargeDt!=null && selDischargeDt!='' && surgerySelDate!=null && surgerySelDate!='' && fn_checNxtFollowpDate(surgerySelDate,selDischargeDt))
			{
				if(errMsgF=='')
					errMsgF=errMsgF+"Discharge date cannot be less than the surgery/therapy date,\nsurgery/therapy Date updated is "+document.forms[0].surgStartDt.value; 
				if(lField=='')
			        lField='disDate'; 
			 	//alert('Discharge date cannot be less than the surgery/therapy date,\nsurgery/therapy Date updated is '+document.forms[0].surgStartDt.value );
			 //	document.forms[0].disDate.focus();
				//document.forms[0].disDate.value='';
			 	//return;
			}
			else if(selNxtFupDt=='')
			{
				if(errMsgF=='')
					errMsgF=errMsgF+"Please select next follow up date "; 
				if(lField=='')
			        lField='nxtFollUpDt'; 
				//alert('Please select next follow up date');	
				//document.forms[0].nxtFollUpDt.focus();
				//document.forms[0].nxtFollUpDt.value='';
				//return;
			}
			 else if(selNxtFupDt!=null && selNxtFupDt!='' && (fn_checkSelDate(selNxtFupDt)))			
			 {
				 if(errMsgF=='')
						errMsgF=errMsgF+"Next follow up date should be future date "; 
					if(lField=='')
				        lField='nxtFollUpDt'; 
			 //	alert('Next follow up date should be future date');
			 	//document.forms[0].nxtFollUpDt.focus();
				//document.forms[0].nxtFollUpDt.value='';
			 //	return;
			 }			
			 else if(selNxtFupDt!=null && selNxtFupDt!='' && surgerySelDate!=null && surgerySelDate!='' && fn_checNxtFollowpDate(surgerySelDate,selNxtFupDt))
			 {
				 if(errMsgF=='')
						errMsgF=errMsgF+"Next follow up date cannot be less than the surgery/therapy date,\nsurgery/therapy date updated is "+document.forms[0].surgStartDt.value; 
					if(lField=='')
				        lField='nxtFollUpDt'; 
			 	//alert('Next follow up date cannot be less than the surgery/therapy date,\nsurgery/therapy date updated is '+document.forms[0].surgStartDt.value );
			 	//document.forms[0].nxtFollUpDt.focus();
				//document.forms[0].nxtFollUpDt.value='';
			 	//return;
			 }
			 else if(selNxtFupDt != null && selNxtFupDt!='' && !(checkNextFollowup11Days(selNxtFupDt,selDischargeDt)))
				{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Next follow up date should be greater than 11 days from discharge date   "; 
					if(lField=='')
				        lField='nxtFollUpDt'; 
				// alert('Next follow up date should be greater than 11 days from discharge date ');
				// document.forms[0].nxtFollUpDt.focus();
				//	document.forms[0].nxtFollUpDt.value='';	
				// return;
				}
			else if(document.forms[0].blockName.value=='')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please enter consult at block name  "; 
					if(lField=='')
				        lField='blockName'; 
				//alert('Please enter block name');	
				//document.forms[0].blockName.focus();
				//return;
			}
			else if(document.forms[0].disfloor.value=='')
			{
				 if(errMsgF=='')
						errMsgF=errMsgF+"Please enter consult floor "; 
					if(lField=='')
				        lField='disfloor'; 
				//alert('Please enter consult floor');	
				//document.forms[0].disfloor.focus();
				//return;
			}
			else if(document.forms[0].disroomNo.value=='')
			{
				if(errMsgF=='')
					errMsgF=errMsgF+"Please enter consult room No.  "; 
				if(lField=='')
			        lField='disroomNo'; 
				//alert('Please enter consult room No.');	
				//document.forms[0].disroomNo.focus();
				//return;
			}
			if(errMsgF!='')
			{
				//var fr = partial(focusBox,document.getElementById(lField));
				//jqueryAlertMsg('Clinical notes mandatory fields',errMsgF,fr);
				alert(errMsgF);
				focusBox(document.getElementById(lField));		//k
				document.getElementById("SaveDisSumryBut").disabled=false;
				if(document.getElementById("SubmitDisSumryBut"))
				{
					document.getElementById("SubmitDisSumryBut").disabled=false;
				}
				document.getElementById("addViewAttachDis").disabled=false;
			}
			if(errMsgF=='')
			{
				 fn_testMandatoryAttach('discharge','ehfClinicaldischarge',url);
				
				
			}			
		}
		
		//For death radio validations
		 if((document.getElementById("death").checked!='' && document.getElementById("death").checked==true))
		{		
			var selDisDeathDt=document.forms[0].disDeathDate.value;
			if(selDisDeathDt=='')
			{
				if(errMsgF=='')
					errMsgF=errMsgF+"Please select death Date  "; 
				if(lField=='')
			        lField='disDeathDate'; 
			}
			else if(selDisDeathDt!=null &selDisDeathDt!='' && !(fn_checkSelDate(selDisDeathDt)))	
			 {
				if(errMsgF=='')
					errMsgF=errMsgF+"Death date cannot be future date  "; 
				if(lField=='')
			        lField='disDeathDate'; 
			 }			
			else if(selDisDeathDt!=null &selDisDeathDt!='' && surgerySelDate!=null && surgerySelDate!='' && fn_checNxtFollowpDate(surgerySelDate,selDisDeathDt))
			 {
				if(errMsgF=='')
					errMsgF=errMsgF+"Death date cannot be less than the surgery/therapy date,\nsurgery/therapy date updated is "+document.forms[0].surgStartDt.value ; 
				if(lField=='')
			        lField='disDeathDate';
			 	
			 }	
			
			if(errMsgF!='')
			{
			var fr = partial(focusBox,document.getElementById(lField));
			//jqueryAlertMsg('Clinical notes mandatory fields',errMsgF,fr);
			alert(errMsgF);
			/* focusBox(document.getElementById(lField)); */			//k
			
			}
		if(errMsgF=='')
		{
				 fn_testMandatoryAttach('death','ehfClinicaldischargeDeath',url);
		}
			
		}
			}
		else 
			{
			// add for discharge
			// add for death div submit
			if(document.getElementById('deathDiv').style.display =='block')
				{
				var selDisDeathDt=document.forms[0].disDeathDate.value;
				if(selDisDeathDt=='')
				{
					if(errMsgF=='')
						errMsgF=errMsgF+"Please select death date  "; 
					if(lField=='')
				        lField='disDeathDate';
					
				}
				else if(selDisDeathDt!=null &selDisDeathDt!='' && !(fn_checkSelDate(selDisDeathDt)))	
				 {
					if(errMsgF=='')
						errMsgF=errMsgF+"Death Date cannot be future date  "; 
					if(lField=='')
				        lField='disDeathDate';
				 	//alert('Death Date cannot be future date');
				 	//document.forms[0].disDeathDate.value='';
				 	//document.forms[0].disDeathDate.focus();
				 	//return;
				 }			
				else if(selDisDeathDt!=null &selDisDeathDt!='' && surgerySelDate!=null && surgerySelDate!='' && fn_checNxtFollowpDate(surgerySelDate,selDisDeathDt))
				 {
					if(errMsgF=='')
						errMsgF=errMsgF+"Death date cannot be less than the surgery/therapy date,\nsurgery/therapy date updated is "+document.forms[0].surgStartDt.value; 
					if(lField=='')
				        lField='disDeathDate';
				 //	alert('Death date cannot be less than the surgery/therapy date,\nsurgery/therapy date updated is '+document.forms[0].surgStartDt.value );
				 //	document.forms[0].disDeathDate.value='';
				 //	document.forms[0].disDeathDate.focus();
				 //	return;
				 }	
				if(errMsgF!='')
					{
					var fr = partial(focusBox,document.getElementById(lField));
					//jqueryAlertMsg('Clinical notes mandatory fields',errMsgF,fr);
					alert(errMsgF);
					/* focusBox(document.getElementById(lField));			 *///k
					}
				if(errMsgF=='')
				{
					 fn_testMandatoryAttach('death','ehfClinicaldischargeDeath',url);
					
					
				}
				}
			}
	}
}

//

function fn_testMandatoryAttach(varType,attachType,dischargeUrl)
	{
		var flag = null;
		var caseId = '${caseId}';
		var xmlhttp;
		var url;
		if (window.XMLHttpRequest)
		{
		 xmlhttp=new XMLHttpRequest();
		}
		else if (window.ActiveXObject)
		{		
		 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else
		{
		 alert("Your Browser Does Not Support XMLHTTP!");
		}
		 url = '/<%=context%>/preauthDetails.do?actionFlag=checkMandatoryAttch&caseId='+caseId+'&attachType='+attachType;
		 xmlhttp.onreadystatechange=function()
			{
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]!=null)
				        {	
				           if(resultArray[0] =='success')
				        	   {
				        	 
				        	   if(varType != null && varType=='surgery')
				        		   {
									   document.getElementById("SaveSurgeryBut").disabled=false;
									  // var fr = partial(saveSurgeryConfirm);
									  //jqueryConfirmMsg('Clinical notes Confirmation','Do you want to submit ?',fr);
										if(confirm('Do you want to submit ?'))	
										{
											saveSurgeryConfirm();
										}										
										document.getElementById('SaveSurgeryBut').disabled=false;
										document.getElementById('addViewAttachSur').disabled=false;
				        		   }
				        	   else if(varType != null && varType=='discharge' || varType=='death')
				        		   {
								    if(document.getElementById("SubmitDisSumryBut"))
									{
										document.getElementById("SubmitDisSumryBut").disabled=false;
									}
									//var fr = partial(saveDischargeConfirm,dischargeUrl);
				        			//jqueryConfirmMsg('Clinical notes Confirmation','Do you want to submit ?',fr,null);
									if(confirm('Do you want to submit ?'))	
									{
										saveDischargeConfirm(dischargeUrl);
									}
									document.getElementById("SaveDisSumryBut").disabled=false;
									if(document.getElementById("SubmitDisSumryBut"))
									{
										document.getElementById("SubmitDisSumryBut").disabled=false;
									}
									document.getElementById("addViewAttachDis").disabled=false;

								   }
				        	  
				        	   }
				           else
				        	   {
				        	   //alert(resultArray[0]);
				        	   //jqueryAlertMsg('Clinical notes mandatory check',resultArray[0]);
				        	   alert(resultArray[0]);
							   if(varType != null && varType=='surgery')
							    {
									document.getElementById('SaveSurgeryBut').disabled=false;
									document.getElementById('addViewAttachSur').disabled=false;
								}
							   if(varType != null && varType=='discharge' || varType=='death')
							   {
								document.getElementById('SaveDisSumryBut').disabled=false;
								if(document.getElementById("SubmitDisSumryBut"))
								{
									document.getElementById('SubmitDisSumryBut').disabled=false;
								}
								document.getElementById("addViewAttachDis").disabled=false;

							    }
								return;
				        	   }
				        } 
			    }
			}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		//return flag;
	}
function saveSurgeryConfirm()
{
			document.getElementById("SaveSurgeryBut").disabled=true;
			document.getElementById('addViewAttachSur').disabled=true;
			document.forms[0].action="/<%=context%>/clinicalNotesAction.do?actionFlag=saveSurgeryDate&caseId=${caseId}&medicalFlag=${medicalFlag}";
			document.forms[0].submit();
	}
function saveDischargeConfirm(dischargeUrl)
{
	if(document.getElementById("SubmitDisSumryBut"))
	{
		document.getElementById("SubmitDisSumryBut").disabled=true;
	}
	document.forms[0].action=dischargeUrl;
	document.forms[0].submit();
}
// end of functions
// date functions


// start of jquery alerts related function
function refreshPageBottom()
{
	parent.fn_ipRefresh();
	var url="/<%=context%>/clinicalNotesAction.do?actionFlag=clinicalNotes&caseId=${caseId}&notesType=PRE";
	document.forms[0].action=url;
	document.forms[0].target="bottomFrame";
	document.forms[0].submit();	
	}

function refreshPage()
{
	parent.fn_ipRefresh();
	var url="/<%=context%>/clinicalNotesAction.do?actionFlag=clinicalNotes&caseId=${caseId}&notesType="+'PRE';
	document.forms[0].action=url;   
	document.forms[0].submit();
	}
function getTitles(styleId)
{
	 var numOptions = document.getElementById(styleId).options.length; 
	
	for (var i = 0; i < numOptions; i++)
		document.getElementById(styleId).options[i].title = document.getElementById(styleId).options[i].text;
}
function fn_generateSatisfactionLetter()
{
	var url="/<%=context%>/clinicalNotesAction.do?actionFlag=generateSatisfactionLetter&caseId=${caseId}";
	childWindow=window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=960, height=600, top=100,left=50');
}


</script>
</head>

<body onload="fn_onload();">

	<html:form action="/clinicalNotesAction.do" method="post">

		<!-- For surgery update success msg -->
		<c:if
			test="${surgeryUpdateSuccess=='Y' || dischargeUpdateSuccess=='Y'}">
			<logic:notEmpty name="clinicalNotesForm" property="resultMsg">
				<script>
					alert('<bean:write name="clinicalNotesForm" property="resultMsg"/>');
					//parent.fn_resizePage();
					//$("body").mCustomScrollbar("update");
					refreshPageBottom();
					/* var fr = partial(refreshPageBottom);
					jqueryInfoMsg('Clinical notes result message','<bean:write name="clinicalNotesForm" property="resultMsg"/>',fr); */

				</script>
			</logic:notEmpty>
		</c:if>

		<!-- For add clinical notes success msg -->
		<c:if test="${empty surgeryUpdateSuccess}">
			<logic:notEmpty name="clinicalNotesForm" property="resultMsg">
				<script>
alert('<bean:write name="clinicalNotesForm" property="resultMsg"/>');
//parent.fn_resizePage();
//$("body").mCustomScrollbar("update");
refreshPage();
if('${dischargeUpdateSuccess}'!=null && '${dischargeUpdateSuccess}'!='' && '${dischargeUpdateSuccess}'=='S')
{
	if('${DischargeRadioDivStyle}'!=null && '${DischargeRadioDivStyle}'== 'block' )
	{
		fn_generateSatisfactionLetter();
	}
	var url="/<%=context%>/clinicalNotesAction.do?actionFlag=printDischargeForm&caseId=${caseId}";	
    //parent.parent.parent.attachmentWin= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=960, height=600, top=100,left=50');
	childWindow=window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=960, height=600, top=100,left=50');
}
/* var fr = partial(refreshPage);
jqueryInfoMsg('Clinical notes result message','<bean:write name="clinicalNotesForm" property="resultMsg"/>',fr); */

</script>
			</logic:notEmpty>
		</c:if>
		<!-- Modal for patient details  -->  
				<div class="modal fade" id="viewDtlsID"> 
				  <div class="modal-dialog" id="modal-lgx">
				    <div class="modal-content">
				      <div class="modal-body">
				      	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <iframe  id="complaintFrame" width="100%" height="280px" frameborder="no" scrolling="yes" > </iframe>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
				    </div><!-- /.modal-content --> 
				  </div><!-- /.modal-dialog --> 
				</div><!-- /.modal --> 
<div id="imageID"> 
						<a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
						<span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
						<br>Patient Details
						</a>
					</div>

		<table border="0" width="100%">
			<tr class="tbheader">
				<td id="topSlide" width="5%"><img id="menuImage"
					src="images/rightLeftArrow.jpg" title="Maximize/Minimize"
					style="cursor: hand;" width="25" height="25" alt="Hide Menu"
					align="top" onclick="javascript:fn_maxmizeRight()"></img></td>
				<td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Clinical
						Notes</strong>
				</td>
				<td id="menuSlide" width="5%"><!-- <img id="topImage"
					src="images/updownArrow.jpg" width="30" height="20"
					style="cursor: hand;" title="Maximize/Minimize" alt="Maximize"
					align="top" onclick="javascript:fn_maxmizeTop()"></img> -->
					<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
					</td>
			</tr>
		</table>

		<logic:notPresent name="clinicalNotesForm"
			property="clinicalNotesList">
			<table width="50%" align="center" border="0" style="padding-top:0px;margin:1px auto;">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center"><b>No Clinical Notes Found</b></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</logic:notPresent>
		<!-- To display existing clinical notes -->
		<logic:present name="clinicalNotesForm" property="clinicalNotesList">
			<%
				int i = 1;
			%>
			<bean:size id="size" name="clinicalNotesForm"
				property="clinicalNotesList" />
			<logic:greaterThan value="0" name="size">
					<table width="100%" cellpadding="1" cellspacing="1">
						<!--             <tr><td colspan="10" align="center" class="tbheader"><strong><b><font size="2">Clinical Notes</font></b></strong></td></tr> -->
						<tr>
							<td>
								<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr>
										<td width="4%" class="labelheading1 tbcellCss">SNo</td>
										<td width="9%" class="labelheading1 tbcellCss">Clinical ID</td>
										<td width="10%" class="labelheading1 tbcellCss">Date&nbsp;</td>
										<td width="6%" class="labelheading1 tbcellCss">BP</td>
										<td width="5%" class="labelheading1 tbcellCss">Pulse</td>
										<td width="10%" class="labelheading1 tbcellCss">Temperature&nbsp;</td>
										<td width="9%" class="labelheading1 tbcellCss">Respiratory
											Rate&nbsp;</td>
										<td width="6%" class="labelheading1 tbcellCss">Heart
											Sounds&nbsp;</td>
										<td width="7%" class="labelheading1 tbcellCss">Lungs&nbsp;</td>
										<td width="8%" class="labelheading1 tbcellCss">Fluid
											Input&nbsp;</td>
										<td width="8%" class="labelheading1 tbcellCss">Fluid
											Output&nbsp;</td>
										<td width="10%" class="labelheading1 tbcellCss">Ward
											Type&nbsp;</td>
											
										<!-- <td>&nbsp;In Time</td>
            <td>&nbsp;Out Time</td> -->
										<td width="10%" class="labelheading1 tbcellCss">Daily
											Progress Notes&nbsp;</td>
										<!--<td>View&nbsp;</td>-->
									</tr>

									<logic:iterate id="result" name="clinicalNotesForm"
										property="clinicalNotesList" indexId="sno">
										<tr class="border<%=sno % 2%>">
											<td class="tbcellBorder"><%=sno + 1%></td>
											<td class="tbcellBorder"><bean:write name="result"
													property="CLINICALID" />
											</td>
											<td class="tbcellBorder"><bean:write
													name="result" property="investGnDate" />
											</td>
											<td class="tbcellBorder"><bean:write name="result"
													property="BLOODPRESSURE" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="PULSE" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="TEMPERATURE" />&nbsp;</td>
											<td  align="center" class="tbcellBorder"><bean:write
													name="result" property="RESPIRATORY" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="HEART_RATE" />
											</td>
											<td class="tbcellBorder"><bean:write
													name="result" property="LUNGS" />
											</td>
											<td class="tbcellBorder"><bean:write
													name="result" property="FLUIDINPT" />
											</td>
											<td class="tbcellBorder"><bean:write
													name="result" property="FLUIDOUTPUT" />
											</td>
											<td class="tbcellBorder"><bean:write name="result"
													property="WARD_TYPE" />
											</td>
											<!--<input type="hidden" name="preWardType" value='<bean:write name="result" property="WARD_TYPE"/>'> -->
											<!--<td>&nbsp;<bean:write name="result" property="WARDINTIME"/></td>
            <td>&nbsp;<bean:write name="result" property="WARDOUTTIME"/></td> -->
											<td class="tbcellBorder"><bean:write name="result"
													property="REMARKS" />
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</table>
			</logic:greaterThan>
			
			
			<logic:greaterThan value="0" name="size">
					<table width="100%" cellpadding="1" cellspacing="1">
						<!--             <tr><td colspan="10" align="center" class="tbheader"><strong><b><font size="2">Clinical Notes</font></b></strong></td></tr> -->
						<tr>
							<td>
								<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr class="tbheader"><td colspan="12"><b>Plasma Blood Glucose and Insulin Dosage Chart</b></td></tr>
									<tr>
										<td width="2%" class="labelheading1 tbcellCss">SNo</td>
										<td width="6%" class="labelheading1 tbcellCss">Clinical ID</td>
										<td width="10%" class="labelheading1 tbcellCss">Date&nbsp;</td>
										<td width="9%" class="labelheading1 tbcellCss">Doctor Name</td>
										<td width="5%" class="labelheading1 tbcellCss">BBF(7:00 am )</td>
										<td width="5%" class="labelheading1 tbcellCss">BL(1:00pm)</td>
										<td class="labelheading1 tbcellCss">BD(7:00pm)&nbsp;</td>
										<td width="10%" class="labelheading1 tbcellCss">Plasma Blood Glucose MN
											Rate&nbsp;</td>
										<td width="7%" class="labelheading1 tbcellCss">BBF(7:00am)
											&nbsp;</td>
										<td width="8%" class="labelheading1 tbcellCss">SR(1:00pm)</td>
										<td width="8%" class="labelheading1 tbcellCss">BD(7:00pm)
											&nbsp;</td>
										<td width="8%" class="labelheading1 tbcellCss">Insulin Dosage MN</td>
										
										
									</tr>

									<logic:iterate id="result" name="clinicalNotesForm"
										property="clinicalNotesList" indexId="sno1">
										<tr class="border<%=sno1 % 2%>">
											<td class="tbcellBorder"><%=sno1 + 1%></td>
											<td class="tbcellBorder"><bean:write name="result"
													property="CLINICALID" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="investGnDate" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="docName" />
											</td>
											<td class="tbcellBorder"><bean:write name="result"
													property="plasmaBbf" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="plasmaBl" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="plasmaBd" />&nbsp;</td>
											
											<td width="10%" align="center" class="tbcellBorder"><bean:write
													name="result" property="plasmaMn" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="insulinBbf" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="insulinSr" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="insulinBd" />
											</td>
											<td class="tbcellBorder"><bean:write name="result"
													property="insulinMn" />
											</td>
											
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</table>
			</logic:greaterThan>
			
		</logic:present>
<br>



<!-- get drug details -->
<logic:present name="clinicalNotesForm" property="drugsLst">
        <bean:size  id="drugSize" name="clinicalNotesForm" property="drugsLst"/>
        <logic:greaterThan value="0" name="drugSize">
 <table  width="100%"  id="drugsTable1"  border="0" style="table-layout:fixed;word-wrap:break-word;">
 <tr class="tbheader"><td colspan="13"><b>Drugs List</b></td> </tr>
      <tr>  
      	<td width="5%" class="labelheading1 tbcellCss">Sno.</td>  
      	<td width="10%" class="labelheading1 tbcellCss">Clinical Id</td>         
        <td width="10%" class="labelheading1 tbcellCss">Main Group Name</td>   
       	<td width="10%" class="labelheading1 tbcellCss">Therapeutic Main Group Name</td>
        <td width="10%" class="labelheading1 tbcellCss">Pharmacological SubGroup Name</td>
        <td width="10%" class="labelheading1 tbcellCss">Chemical SubGroup Name</td>
        <td width="10%" class="labelheading1 tbcellCss">Chemical Substance Name</td>
        <td width="5%" class="labelheading1 tbcellCss">Route Type</td>
        <td width="10%" class="labelheading1 tbcellCss">Route</td>
        <td width="5%" class="labelheading1 tbcellCss">Strength Type</td>
        <td width="10%" class="labelheading1 tbcellCss">Strength</td>
        <td width="5%" class="labelheading1 tbcellCss">Dosage</td>
        <td width="5%" class="labelheading1 tbcellCss">Medication Period</td>
        
        </tr>
        
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="clinicalNotesForm" property="drugsLst" >
        <tr>  
      	<td width="5%" class="tbcellBorder"><%=k++ %></td>    
      	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="clinicalId" /></td>       
        <td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="drugTypeName" /></td>   
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="drugSubTypeName" /></td> 
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="pSubGrpName"/></td>
        <td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="cSubGrpName"/></td> 
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="drugName" /></td> 
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="routeTypeName" /></td> 
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="routeName" /></td>  
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="strengthTypeName" /></td>
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="strengthName" /></td>  
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="dosage" /></td>  
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="medicationPeriod" /></td> 
       
        </tr>
        </logic:iterate>  
</table>
</logic:greaterThan></logic:present>

		<!-- To add clinical notes -->
		<table border="0" width="100%" cellpadding="1" cellspacing="1"
			align="center" class="tabBorder">
			<c:if test="${addClinicNotesFlag == 'Y'}">
				<tr>
					<td colspan="6" align="left" class="tbheader"><input
						type="checkbox" id="addClinicalNotes"
						onclick="enableAddNotesDiv('PRE')" /><strong>Add Clinical
							Notes</strong>
					</td>
				</tr>
			</c:if>
			<c:if test="${postAddClinicNotesFlag == 'Y'}">
				<tr>
					<td colspan="6" align="left" class="tbheader"><input
						type="checkbox" id="addClinicalNotes"
						onclick="enableAddNotesDiv('POST')" /><strong>Add
									Post-OP/Therapy notes</strong>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="4">
					<div id="addNotesDiv" style="display: none">
						<table border="0" width="100%" cellpadding="1" cellspacing="1"
							align="center" class="tabBorder">
							<tr>
								<td class="labelheading1 tbcellCss" width="25%">Date<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss" width="25%">BP<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss" width="25%">&nbsp;Pulse Rate<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss" width="25%">&nbsp;Temperature<span
									class="mandatory">*</span>
								</td>
								
								
							<tr>
							<tr>
							  <c:if test="${hospType eq 'G' || hospId eq 'EHS34'}">
								<td class="tbcellBorder"><html:text style="width:85px"
										name="clinicalNotesForm" property="invesgtnDate"
										styleId="invesgtnDate"  readonly="true" title="Please select date" value="${currDt}"></html:text> <!--   <img src="/Operations/images/calend.gif" align="absmiddle" width="21" height="21" onclick="javascript:window_open('invesgtnDate','150','470')" alt="Calendar" title="Calendar"></img> -->
								</td>
                               </c:if>
                               <c:if test="${hospType eq 'C'}">
								<td class="tbcellBorder"><html:text style="width:85px"
										name="clinicalNotesForm" property="invesgtnDate"
										styleId="invesgtnDate"  readonly="true" title="Please select date" value="${currDt}"></html:text> <!--   <img src="/Operations/images/calend.gif" align="absmiddle" width="21" height="21" onclick="javascript:window_open('invesgtnDate','150','470')" alt="Calendar" title="Calendar"></img> -->
								</td>
                               </c:if>
								<td class="tbcellBorder"><select title="Please select systolic pressure" name="systolic" onmousemove="javascript:getTitles('systolic')"
									id="systolic" style="width: 55px;">
										<option value="-1">--:--</option>
										<c:forEach var="systolic" begin="50" end="210">
											<option value="${systolic}">< c:out
												value="${systolic}" /></option>
										</c:forEach>
								</select>&nbsp;/&nbsp; <select title="Please select diastolic pressure" name="diastolic" id="diastolic" onmousemove="javascript:getTitles('diastolic')"
									style="width: 55px;">
										<option value="-1">--:--</option>
										<c:forEach var="diastolic" begin="33" end="120">
											<option value="${diastolic}">< c:out
												value="${diastolic}" /></option>
										</c:forEach>
								</select></td>
								<td class="tbcellBorder"><select name="pulseRate" title="Please select pulse rate" onmousemove="javascript:getTitles('pulseRate')"
									id="pulseRate" style="width: 95%">
										<option value="-1">-Select-</option>
										<c:forEach var="pulseRate" begin="40" end="160">
											<option value="${pulseRate}">< c:out
												value="${pulseRate}" /></option>
										</c:forEach>
								</select></td>
								<td class="tbcellBorder"><select title="Please select temperature" name="temperature" onmousemove="javascript:getTitles('temperature')"
									id="temperature" style="width: 95%">
										<option value="-1">-Select-</option>
										<c:forEach var="temperature" begin="36" end="42">
											<option value="${temperature}">< c:out
												value="${temperature}" /></option>
										</c:forEach>
								</select></td>

								
								<!-- 
<td>
<select name="wardInTimeHrs" id="wardInTimeHrs" style="width:45px;">
<option value="-1">-:-</option>
 <c:forEach var="wardInTimeHrs" begin="0" end="23"> 
   <option value="${wardInTimeHrs}"> < c:out value="${wardInTimeHrs}" /></option>                           
  </c:forEach>
</select>HH&nbsp;
<select name="wardInTimeMins" id="wardInTimeMins" style="width:45px;">
<option value="-1">-:-</option>
 <c:forEach var="wardInTimeMins" begin="0" end="59">
   <option value="${wardInTimeMins}"> < c:out value="${wardInTimeMins}" /></option>                           
  </c:forEach>
</select>MM
</td>

<td>
<select name="wardOutTimeHrs" id="wardOutTimeHrs" style="width:45px;">
<option value="-1">-:-</option>
 <c:forEach var="wardOutTimeHrs" begin="0" end="23">
   <option value="${wardOutTimeHrs}"> < c:out value="${wardOutTimeHrs}" /></option>                           
  </c:forEach>
</select>HH&nbsp;
<select name="wardOutTimeMins" id="wardOutTimeMins" style="width:45px;">
<option value="-1">-:-</option>
 <c:forEach var="wardOutTimeMins" begin="0" end="59">
   <option value="${wardOutTimeMins}"> < c:out value="${wardOutTimeMins}" /></option>                           
  </c:forEach>
</select>MM
</td>
 -->
 
							</tr>
<tr>


<td class="labelheading1 tbcellCss">&nbsp;Ward Type<span
									class="mandatory">*</span>
								</td>
								<!-- <td>&nbsp;Ward In Time</td>	
<td>&nbsp;Ward Out Time</td> -->
								<td class="labelheading1 tbcellCss">Respiratory Rate<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss">Heart Sounds<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss">Lungs<span
									class="mandatory">*</span>
								</td>
</tr>
							<tr>
							<td class="tbcellBorder"><select title="Please select ward type" name="wardType" onmousemove="javascript:getTitles('wardType')"
									id="wardType" style="width: 95%">
										<option value="-1">-----Select-----</option>
										<option id="PrivateWard">Private Ward</option>
										<option id="SemiPrivateWard">Semi-Private Ward</option>
										<option id="ICU">ICU</option>
										<option id="Post-OP">Post-OP</option>
										<option id="StepDown">StepDown</option>
								</select></td>

								<td class="tbcellBorder"><select title="Please select respiratory rate" name="respRate" onmousemove="javascript:getTitles('respRate')"
									id="respRate">
										<option value="-1">-Select-</option>
										<c:forEach var="respRate" begin="5" end="50">
											<option value="${respRate}">< c:out
												value="${respRate}" /></option>
										</c:forEach>
								</select></td>
								<td class="tbcellBorder"><select title="Please select heart sounds" name="heartRate" onmousemove="javascript:getTitles('heartRate')"
									id="heartRate">
										<option value="-1">-Select-</option>
										<option id="S1">S1</option>
										<option id="S2">S2</option>
										<option id="S3">S3</option>
										<option id="S4">S4</option>
										<option id="Murmours">Murmours</option>
										<option id="ExtraSounds">ExtraSounds</option>
								</select></td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="lungs" property="lungs" title="Please select lungs"
										maxlength="50" style="width:90%"
										onchange="javascript:chkAlpha('lungs','Lungs');" />
								</td>
								</tr>
							<tr>
							
							
								
								<td class="labelheading1 tbcellCss">Fluid Input<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss">Fluid Output<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss">Doctor Name<span
									class="mandatory">*</span>
								</td>
								<td class="labelheading1 tbcellCss" >Daily
									Doctor Notes<span class="mandatory">*</span></td>
							</tr>
							<tr>
								
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="fluidInput" title="Please select fluid input"
										property="fluidInput" maxlength="50"
										onchange="javascript:chkAlphaNumeric('fluidInput','Fluid Input');validateAllphaNumericComb('fluidInput','Fluid Input')" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="fluidOutput" title="Please select fluid output"
										property="fluidOutput" maxlength="50"
										onchange="javascript:chkAlphaNumeric('fluidOutput','Fluid Output');validateAllphaNumericComb('fluidOutput','Fluid Output')" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="docName" title="Please enter Doctors Name "
										property="docName" maxlength="50"
										onchange="javascript:chkAlpha('docName','Treating doctor Name');" />
								</td>
								<td class="tbcellBorder" >&nbsp;<html:textarea
										name="clinicalNotesForm" property="remarks" styleId="remarks"
										rows="3" cols="20" title="Please enter remarks"
										onchange="javascript:chkAlphaNumeric('remarks','daily doctor progress notes');func_maxlen('Remarks',this,3000);"
										styleClass="textfield"></html:textarea>
								</td>
							</tr>
							<c:if test="${postAddClinicNotesFlag == 'Y'}">
							
							<tr>
										<td colspan="7" class="tbcellBorder"><input type="radio"
											name="woundStatus" id="healthy" value="Healthy"
											onclick="fn_enableWoundDtls()" title="Please select healthy/non-healthy radio button">Healthy</input> <input
											type="radio" name="woundStatus" id="notHealthy"
											value="Not Healthy" onclick="fn_enableWoundDtls()" title="Please select healthy/non-healthy radio button">NotHealthy</input>
											<html:textarea property="woundDtls" name="clinicalNotesForm"
												styleId="woundDtlsDiv" rows="3" cols="30" 
												style="display:none" title="Please enter remarks"
												onchange="javascript:chkAlphaNumeric('woundDtls','wound Details');func_maxlen('Not Healthy Remarks',this,3000);"
												></html:textarea>
											<!-- <input id="woundDtlsDiv" style="display:none" type="text"  name="woundDtls" maxlength="800" onchange="javascript:chkAlphaNumeric('woundDtls','wound Details');"/> -->
										</td>
									</tr>
							
							</c:if>
							<tr>
					<td colspan="6" align="left" class="tbheader"><strong>Plasma/Blood Glucose</strong>
					</td>
				</tr>
							<tr>
								<td class="labelheading1 tbcellCss">BBF(7:00am)
								</td>
								<td class="labelheading1 tbcellCss">BL(1:00pm)
								</td>
								<td class="labelheading1 tbcellCss">BD(7:00pm)
								</td>
								<td class="labelheading1 tbcellCss">MN
								</td>
								
								</tr>
								<tr>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="plasmaBbf" title="Please enter Plasma/Blood Glucose BBF(7:00am) "
										property="plasmaBbf" maxlength="10"
										onchange="javascript:chkAlphaNumeric('plasmaBbf','Plasma/Blood Glucose BBF 7:00am ');" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="plasmaBl" title="Please enter Plasma/Blood Glucose BL(1:00pm) "
										property="plasmaBl" maxlength="10"
										onchange="javascript:chkAlphaNumeric('plasmaBl','Plasma/Blood Glucose BL 1:00pm ');" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="plasmaBd" title="Please enter Plasma/Blood Glucose BD(7:00pm) "
										property="plasmaBd" maxlength="10"
										onchange="javascript:chkAlphaNumeric('plasmaBd','Plasma/Blood Glucose BD 7:00pm ');" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="plasmaMn" title="Please enter Plasma/Blood Glucose MN "
										property="plasmaMn" maxlength="10"
										onchange="javascript:chkAlphaNumeric('plasmaMn','Plasma/Blood Glucose MN');" />
								</td>
								
							
								</tr>
								<tr>
					<td colspan="6" align="left" class="tbheader"><strong>Insulin Dosage</strong>
					</td>
				</tr>


<tr>
								<td class="labelheading1 tbcellCss">BBF(7:00am)
								</td>
								<td class="labelheading1 tbcellCss">SR(1:00pm)
								</td>
								<td class="labelheading1 tbcellCss">BD(7:00pm)
								</td>
								<td class="labelheading1 tbcellCss">MN
								</td>
								
								</tr>
								<tr>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="insulinBbf" title="Please enter Insulin Dosage BBF(7:00am) "
										property="insulinBbf" maxlength="10"
										onchange="javascript:chkAlphaNumeric('insulinBbf','Insulin Dosage BBF 7:00am');" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="insulinSr" title="Please enter Insulin Dosage SR(1:00pm) "
										property="insulinSr" maxlength="10"
										onchange="javascript:chkAlphaNumeric('insulinSr','Insulin Dosage SR 1:00pm ');" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="insulinBd" title="Please enter Insulin Dosage BD(7:00pm) "
										property="insulinBd" maxlength="10"
										onchange="javascript:chkAlphaNumeric('insulinBd','Insulin Dosage BD 7:00pm ');" />
								</td>
								<td class="tbcellBorder"><html:text
										name="clinicalNotesForm" styleId="insulinMn" title="Please enter Insulin Dosage MN "
										property="insulinMn" maxlength="10"
										onchange="javascript:chkAlphaNumeric('insulinMn','Insulin Dosage MN');" />
								</td>
								
							
								</tr>
								
								
								
								<tr><td  id="prescriptionData" colspan="4">
<table width="100%">
<tr><td class="tbheader" colspan="4"><font ><b>Medication Chart</b></font></td></tr>

<tr>
<td width="25%" class="labelheading1 tbcellCss"><b>Main Group Name</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Main Group Code</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Therapeutic Main Group Name</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Therapeutic Main Group Code</b></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="clinicalNotesForm" property="drugTypeCode" styleId="drugTypeCode" style="width:14em;" title="Select Drug type" onchange="getDrugSubTypeList()" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1">----Select-----</html:option>
		<html:options property="ID" collection="drugsList" labelProperty="VALUE"/>
</html:select></td>
<td class="tbcellBorder"><html:text name="clinicalNotesForm"  property="drugCode" styleId="drugCode" title="Drug Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder">
<html:select name="clinicalNotesForm" property="drugSubTypeName" styleId="drugSubTypeName" style="width:14em;" title="Select Drug Sub type" onchange="getPharSubGrpLst()" onmouseover="addTooltip('drugSubTypeName')">
		<html:option value="-1">----Select-----</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="clinicalNotesForm"  property="drugSubTypeCode" styleId="drugSubTypeCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td width="25%" class="labelheading1 tbcellCss"><b>Pharmacological SubGroup Name</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Pharmacological SubGroup Code</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Chemical SubGroup Name</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Chemical SubGroup Code</b></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="clinicalNotesForm" property="pSubGrpName" styleId="pSubGrpName" style="width:14em;" title="Select Pharmacological SubGroup " onchange="getChemicalSubGrp()" onmouseover="addTooltip('pSubGrpName')">
		<html:option value="-1">----Select-----</html:option>		
</html:select></td>
<td class="tbcellBorder"><html:text name="clinicalNotesForm"  property="pSubGrpCode" styleId="pSubGrpCode" title="Pharmacological SubGroup Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder">
<html:select name="clinicalNotesForm" property="cSubGrpName" styleId="cSubGrpName" style="width:14em;" title="Chemical SubGroup" onchange="getDrugNameList()" onmouseover="addTooltip('cSubGrpName')">
		<html:option value="-1">----Select-----</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="clinicalNotesForm"  property="cSubGrpCode" styleId="cSubGrpCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td class="labelheading1 tbcellCss"><b>Chemical Substance Name</b></td>
<td class="labelheading1 tbcellCss"><b>Chemical Substance Code</b></td>
<td class="labelheading1 tbcellCss"><b>Route</b> </td>
<td class="labelheading1 tbcellCss"><b>Strength</b> </td>   
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="clinicalNotesForm" property="drugName" styleId="drugName" style="width:14em;" title="Select Drug Name" onchange="getDrugDetails()" onmouseover="addTooltip('drugName')">
		<html:option value="-1">----Select-----</html:option>  
</html:select></td>
<td class="tbcellBorder"><html:text name="clinicalNotesForm"  property="asriDrugCode" styleId="asriDrugCode" title="Drug Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder">
<table >
<tr><td width='50%'><html:select name="clinicalNotesForm" property="routeType" styleId="routeType" style="width:7em;" title="Select Route Type" onchange="getRouteList()" onmouseover="addTooltip('routeType')">
		<html:option value="-1">---- Select-----</html:option>
		<html:options property="ID" collection="routeTypeLst" labelProperty="VALUE"/>
</html:select></td>
<td width='50%'><html:select name="clinicalNotesForm" property="route" styleId="route" style="width:7em;" title="Select Route "  onmouseover="addTooltip('route')">
		<html:option value="-1">---- Select-----</html:option>		
</html:select></td> </tr>
</table></td>
<td class="tbcellBorder">   
<table >
<tr><td width='50%'><html:select name="clinicalNotesForm" property="strengthType" styleId="strengthType" style="width:7em;" title="Select Strength Type" onchange="getStrengthList()" onmouseover="addTooltip('strengthType')">
		<html:option value="-1">----Select-----</html:option>
		<html:options property="ID" collection="strengthTypeLst" labelProperty="VALUE"/>
</html:select></td>
<td width='50%'><html:select name="clinicalNotesForm" property="strength" styleId="strength" style="width:7em;" title="Select Strength "  onmouseover="addTooltip('strength')">
		<html:option value="-1">---- Select-----</html:option>		
</html:select></td> </tr>
</table></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Dosage</b> </td>
<td class="labelheading1 tbcellCss"><b>Medication Period</b> </td>
<td colspan="2">&nbsp;</td>
</tr>
<tr>
<td class="tbcellBorder">
<html:select name="clinicalNotesForm" property="dosage" styleId="dosage" style="width:14em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1">----Select------</html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="clinicalNotesForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:14em" onchange="validateNumber('Medication Period',this)" title="Enter Medication Period"/></td>
<td colspan="2"><input class="but" type="button" name="addDrug" id="addDrug" value="Add Drugs" onclick="addDrugs()"></input></td>
</tr>
</table>
</td>
</tr>

<tr><td colspan="4" class="tbcellBorder" >  
  <table  width="99%"  id="drugsTable" style="display:none" border="1" style="table-layout:fixed;">
      <tr class="tbheader">  
      	 <td width="2%" class="labelheading1 tbcellCss">SNo.</td>      
        <td width="10%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Main Group Name</td>   
       	<td width="10%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Therapeutic Main Group Name</td>
        <td width="10%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Pharmacological SubGroup Name</td>
         <td width="10%" class="labelheading1 tbcellCss">Chemical SubGroup Name</td> 
        <td width="10%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Chemical Substance Name</td>
        <td width="5%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Route Type</td>
        <td width="5%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Route</td>
        <td width="5%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Strength Type</td>
        <td width="5%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Strength</td>
        <td width="5%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Dosage</td>
        <td width="2%" class="labelheading1 tbcellCss" style="word-wrap:break-word;">Days</td>
        <td width="10%" class="labelheading1 tbcellCss">&nbsp;</td>
        </tr>
        </table>
        </td>
        </tr>
	<tr><td>&nbsp;</td></tr>							
								
								
								
								
								
								
								
								
								

							<!-- 
<tr>
<td><input type="checkbox" id="theraphyType" value="Y" name="ChemoTheraphy" onclick="enableTheraphyDiv()">Chemo Theraphy</td>
<td><input type="checkbox" id="theraphyType" value="Y" name="BloodTheraphy" onclick="enableTheraphyDiv()">Blood and Products</td>
<td><input type="checkbox" id="theraphyType" value="Y" name="RadiationTheraphy" onclick="enableTheraphyDiv()">Radiation Theraphy</td>
</tr>
 -->
						</table>
					</div></td>
			</tr>


			<!-- chemo theraphy -->
			<!-- 
<tr><td>
<div id="ChemoDiv" style="display:none">
<table>
<tr>
<td>Medicines<span class="mandatory">*</span></td>
<td>Dosages</td>
<td>Condition at Start of infusion<span class="mandatory">*</span></td>	
<tr>

<tr>
<td><html:text name="clinicalNotesForm" property="medicines"/></td>
<td><html:text name="clinicalNotesForm" property="dosages"/></td>
<td><html:text name="clinicalNotesForm" styleId="condStrtInfuC" property="cndtnStrInf"/></td>
</tr>

<tr>
<td>Infusion Started at<span class="mandatory">*</span></td>
<td>Time</td>	
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="infsnStrtDtC" styleId="infsnStrtDtC" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('infsnStrtDtC','150','470')" alt="Calendar"></img></td>
<td>
<select name="infsnStrHrs" id="infsnStrHrsC">
 <c:forEach var="infsnStrHrs" begin="0" end="23">
   <option value="${infsnStrHrs}"> < c:out value="${infsnStrHrs}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="infsnStrMins" id="infsnStrMinsC">
 <c:forEach var="infsnStrMins" begin="0" end="59">
   <option value="${infsnStrMins}"> < c:out value="${infsnStrMins}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>


<tr>
<td>Infusion Ended at<span class="mandatory">*</span></td>
<td>Time</td>
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="infsnEndDtC" styleId="infsnEndDtC" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('infsnEndDtC','150','470')" alt="Calendar"></img></td>
<td>
<select name="infsnEndHrs" id="infsnEndHrsC">
 <c:forEach var="infsnEndHrs" begin="0" end="23">
   <option value="${infsnEndHrs}"> < c:out value="${infsnEndHrs}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="infsnEndMins" id="infsnEndMinsC">
 <c:forEach var="infsnEndMins" begin="0" end="59">
   <option value="${infsnEndMins}"> < c:out value="${infsnEndMins}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>

<tr>
<td>Condition after infusion</td>
<td><input type="radio" name="cndtnEndInf" id="satisFctry"  value="S" checked="checked" onclick="enableComments()">Satisfactory</td>
<td><input type="radio" name="cndtnEndInf" id="Complictns"  value="C" onclick="enableComments()">Complications</td>
</tr>
</table></div>
</td></tr>
-->
			<!-- Blood theraphy -->
			<!-- 
<tr><td>
<div id="BloodDiv" style="display:none">
<table>
<tr>
<td>Condition at Start of infusion<span class="mandatory">*</span></td>	
<tr>

<tr>
<td><html:text name="clinicalNotesForm" styleId="condStrtInfuB" property="cndtnStrInf"/></td>
</tr>

<tr>
<td>Infusion Started at<span class="mandatory">*</span></td>
<td>Time<span class="mandatory">*</span></td>	
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="infsnStrtDtB" styleId="infsnStrtDtB" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('infsnStrtDtB','150','470')" alt="Calendar"></img></td>
<td>
<select name="infsnStrHrs" id="infsnStrHrsB">
 <c:forEach var="infsnStrHrsB" begin="0" end="23">
   <option value="${infsnStrHrsB}"> < c:out value="${infsnStrHrsB}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="infsnStrMins" id="infsnStrMinsB">
 <c:forEach var="infsnStrMinsB" begin="0" end="59">
   <option value="${infsnStrMinsB}"> < c:out value="${infsnStrMinsB}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>


<tr>
<td>Infusion ended at<span class="mandatory">*</span></td>
<td>Time<span class="mandatory">*</span></td>
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="infsnEndDtB" styleId="infsnEndDtB" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('infsnEndDtB','150','470')" alt="Calendar"></img></td>
<td>
<select name="infsnEndHrs" id="infsnEndHrsB">
 <c:forEach var="infsnEndHrsB" begin="0" end="23">
   <option value="${infsnEndHrsB}"> < c:out value="${infsnEndHrsB}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="infsnEndMins" id="infsnEndMinsB">
 <c:forEach var="infsnEndMinsB" begin="0" end="59">
   <option value="${infsnEndMinsB}"> < c:out value="${infsnEndMinsB}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>

<tr>
<td>Condition after infusion</td>
<td><input type="radio" name="cndtnEndInfB" accesskey="BSatisFctry" id="BSatisFctry" value="S" checked="checked" onclick="enableComments()">Satisfactory</td>
<td><input type="radio" name="cndtnEndInfB" accesskey="BComplictns"  id="BComplictns" value="C"  onclick="enableComments()">Complications</td>
</tr>
</table></div>
</td></tr>
-->
			<!-- Radiation theraphy -->
			<!-- 
<tr><td>
<div id="RadiationDiv" style="display:none">
<table>
<tr>
<td>Condition at Start of RT<span class="mandatory">*</span></td>	
<tr>

<tr>
<td><html:text name="clinicalNotesForm" styleId="condStrtInfuR" property="cndtnStrInf"/></td>
</tr>

<tr>
<td>Infusion Started at<span class="mandatory">*</span></td>
<td>Time<span class="mandatory">*</span></td>	
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="infsnStrtDtR" styleId="infsnStrtDtR" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('infsnStrtDtR','150','470')" alt="Calendar"></img></td>
<td>
<select name="infsnStrHrs" id="infsnStrHrsR">
 <c:forEach var="infsnStrHrsR" begin="0" end="23">
   <option value="${infsnStrHrsR}"> < c:out value="${infsnStrHrsR}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="infsnStrMins" id="infsnStrMinsR">
 <c:forEach var="infsnStrMinsR" begin="0" end="59">
   <option value="${infsnStrMinsR}"> < c:out value="${infsnStrMinsR}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>


<tr>
<td>Infusion End date<span class="mandatory">*</span></td>
<td>Time<span class="mandatory">*</span></td>
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="infsnEndDtR" styleId="infsnEndDtR" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('infsnEndDtR','150','470')" alt="Calendar"></img></td>
<td>
<select name="infsnEndHrs" id="infsnEndHrsR">
 <c:forEach var="infsnEndHrsR" begin="0" end="23">
   <option value="${infsnEndHrsR}"> < c:out value="${infsnEndHrsR}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="infsnEndMins" id="infsnEndMinsR">
 <c:forEach var="infsnEndMinsR" begin="0" end="59">
   <option value="${infsnEndMinsR}"> < c:out value="${infsnEndMinsR}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>

<tr>
<td>Field<span class="mandatory">*</span></td>
<td>Site & Field Size</td>
</tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="field" /></td>
<td><html:text style="width:80px" name="clinicalNotesForm" property="siteFieldSize" /></td>
</tr>

<tr>
<td>Date<span class="mandatory">*</span></td>
<td>Time<span class="mandatory">*</span></td>
<tr>

<tr>
<td><html:text style="width:80px" name="clinicalNotesForm" property="fieldDt" styleId="fieldDt" readonly="true"></html:text>
            <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('fieldDt','150','470')" alt="Calendar"></img></td>
<td>
<select name="fieldHrs" id="fieldHrs">
 <c:forEach var="fieldHrs" begin="0" end="23">
   <option value="${fieldHrs}"> < c:out value="${fieldHrs}" /></option>                           
  </c:forEach>
</select>HH&nbsp;&nbsp;&nbsp;
<select name="fieldMins" id="fieldMins">
 <c:forEach var="fieldMins" begin="0" end="59">
   <option value="${fieldMins}"> < c:out value="${fieldMins}" /></option>                           
  </c:forEach>
</select>MM
</td>
</tr>

<tr>
<td>Daily Dose</td>
<td>Instructions</td>
<td>Administered By</td>
<tr>

<tr>
<td>
<select name="dailyDose" id="dailyDose">
 <c:forEach var="dailyDose" begin="0" end="100" step="10">
    <fmt:formatNumber value="${dailyDose}" var="test" type="number" pattern="#.##" maxIntegerDigits="2" minIntegerDigits="1"/> 
   <option><c:out value="${test}"/></option>                           
  </c:forEach>
</select>
</td>
<td><html:text style="width:80px" name="clinicalNotesForm" property="instructions"/> </td>
<td><html:text style="width:80px" name="clinicalNotesForm" property="admisteredBy"/></td>
<tr>

<tr>
<td>Tech Checked<input type="checkbox" name="techChecked" id="techChecked" value="Y"/></td>
<td>Physician Checked<input type="checkbox" name="phyChecked" id="phyChecked" value="Y"/></td>
<td>MD Checked<input type="checkbox" name="mdChecked" id="mdChecked" value="Y"/></td>
</tr>

<tr>
<td>Condition after RT</td>
<td><input type="radio" name="cndtnEndInfR" accesskey="RSatisFctry" id="RSatisFctry" value="S" checked="checked" onclick="enableComments()">Satisfactory</td>
<td><input type="radio" name="cndtnEndInfR" accesskey="RComplictns" id="RComplictns" value="C"  onclick="enableComments()">Complications</td>
</tr>
</table></div>
</td></tr>

<tr><td>
<div id="commentsDiv" style="display:none">
<html:text name="clinicalNotesForm" property="comments"/>
</div>
</td></tr>
-->

			<tr>
				<td colspan="4" align="center">
					<div id="addNotesDiv1" style="display: none">
						<button type="button" class="but" id="SaveNotesBut" value="Submit"
							onclick="javascript:fn_saveClinicalNotes('PRE')">Submit</button>
					</div></td>
			</tr>

			<!-- To add treating doctor details -->
			<c:if test="${surgViewFlag== 'Y'}">


				<c:if test="${medicalFlag=='Y' }">
					<tr>
						<td colspan="4" align="left" class="tbheader"><strong>Treating
								Doctor Details</strong>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table border="0" width="100%" cellpadding="1" cellspacing="1"
								align="center" class="tabBorder">
								<tr>
									<td class="labelheading1 tbcellCss">Name<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;APMC Regn No<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;Qualification<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;Contact no<span
										class="mandatory">*</span>
									</td>
									<!-- <td class="labelheading1 tbcellCss">&nbsp;Treatment Start Date<span class="mandatory">*</span></td> -->
								</tr>

								<tr>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatSurgeonName" styleId="treatSurgeonName" title="Please enter treating doctor name"
											onchange="javascript:chkAlpha('treatSurgeonName','Treating doctor Name');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatSurgeonRegNo" styleId="treatSurgeonRegNo" title="Please enter treating doctor reg no"
											onchange="javascript:chkAlphaNumeric('treatSurgeonRegNo','Treating doctor RegNo');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatSurgeonQual" styleId="treatSurgeonQual" title="Please enter treating doctor qualification"
											onchange="javascript:chkAlpha('treatSurgeonQual','Treating doctor Qualification');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatSurgeonCnctNo" styleId="treatSurgeonCnctNo" title="Please enter treating doctor contact No"
											maxlength="10"
											onchange="javascript:checkcontact(this,'Treating doctor Contact no');"></html:text>
									</td>
									<%-- <td class="labelheading1 tbcellCss">&nbsp;
 <html:text style="width:70px" name="clinicalNotesForm" property="treatSurgStartDt" styleId="treatSurgStartDt" readonly="true"></html:text>
           <c:if test="${lStrTrmtMedicalSubmitFlag !='Y' }" >   <!-- <img src="/Operations/images/calend.gif" onclick="javascript:window_open('treatSurgStartDt','150','470')" alt="Calendar" title="Calendar"></img> -->
           </c:if>
</td> --%>
								</tr>
								<!--<tr>

 <td class="labelheading1 tbcellCss">&nbsp;Death Date<span class="mandatory"></span></td> 
</tr> -->


								<%-- <tr> <td class="labelheading1 tbcellCss">&nbsp;
 <html:text style="width:70px" name="clinicalNotesForm" property="treatDeathDate" styleId="treatDeathDate" readonly="true"></html:text>
      <c:if test="${lStrTrmtMedicalSubmitFlag !='Y' }" >    <!--  <img src="/Operations/images/calend.gif" onclick="javascript:window_open('treatDeathDate','150','470')" alt="Calendar" title="Calendar"></img> -->
      </c:if>
</td> </tr>--%>

								<tr>
									<td colspan="4" align="left" class="tbheader"><strong>Assistant
											Treating Doctor Details</strong>
									</td>
								</tr>

								<tr>
									<td class="labelheading1 tbcellCss">Name<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;APMC Regn No<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;Qualification<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;Contact no<span
										class="mandatory">*</span>
									</td>
									
								<tr>
								<tr>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatAsstSurName" styleId="treatAsstSurName" title="Please enter assistant treating doctor  name"
											onchange="javascript:chkAlpha('treatAsstSurName','Assistant treating dcotor Name');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatAsstSurRegNo" styleId="treatAsstSurRegNo" title="Please enter assistant doctor reg No"
											onchange="javascript:chkAlphaNumeric('treatAsstSurRegNo','Assistant treating doctor RegNo');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatAsstSurQual" styleId="treatAsstSurQual" title="Please enter assistant doctor qualification"
											onchange="javascript:chkAlpha('treatAsstSurQual','Assistant treating doctor qualification');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatAsstSurContctNo" styleId="treatAsstSurContctNo" title="Please enter assistant doctor contact no"
											maxlength="10"
											onchange="javascript:checkcontact(this,'Assistant treating doctor contact No');"></html:text>
									</td>
									
								</tr>
								<tr>
								<td class="labelheading1 tbcellCss">&nbsp;Paramedic Name<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;Nurse Name<span
										class="mandatory">*</span>
									</td>
									<td class="labelheading1 tbcellCss">&nbsp;Expected
										Hospital Stay<span class="mandatory">*</span>
									</td>
								</tr>
								<tr>
								<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatParadMedicName" styleId="treatParadMedicName" title="Please enter paramedic name"
											onchange="javascript:chkAlpha('treatParadMedicName','Paramedic Name');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp;<html:text
											name="clinicalNotesForm" property="treatNurseName" styleId="treatNurseName" title="Please enter treating nurse name"
											onchange="javascript:chkAlpha('treatNurseName','Nurse Name');"
											maxlength="50"></html:text>
									</td>
									<td class="tbcellBorder">&nbsp; 
									
									<c:set
											value="${expHospStay1}" var="sample"></c:set> <select title="Please select hospital stay"
										name="treatExpHospStay" id="treatExpHospStay" onmousemove="javascript:getTitles('treatExpHospStay')">
											<option value="-1">--Select--</option>
											<c:forEach var="expHospStay" begin="1" end="99">
												<option value="${expHospStay}">< c:out
													value="${expHospStay}" /></option>
												<c:if test="${expHospStay==sample}">
													<option value="${expHospStay}" selected="selected">
														< c:out value="${expHospStay}" /></option>
												</c:if>
											</c:forEach>
									</select>
									
									
									</td>
								</tr>
							</table>
				</c:if>

				<!--  end of Medical Treatment Flag -->

				<c:if test="${medMgmtFlag== 'Y'}">
					<tr>
						<td colspan="4" align="left" class="tbheader"><strong>Treating
								Doctor Details</strong>
						</td>
					</tr>
				</c:if>

				<c:if test="${medMgmtFlag== 'N'}">
					 <tr >
						<td colspan="4" >
							<!-- Treatment Protocol shown Only for Dental case -->
							<c:if test="${dentalSurg eq 'Y' }">
								 <logic:present name="clinicalNotesForm" property="lstPreauthVO">
								 
								 
								<table width="100%">
									<tr>
										<td colspan="10" align="left" class="tbheader"><strong>Treatment Protocol 
												</strong>
										</td>
									</tr>
									<tr>
										<td width="10%" style="word-wrap:break-word;" class="labelheading1 tbcellCss"><b>Category Name</b></td>
										<td width="13%" class="labelheading1 tbcellCss"><b>ICD Category Name</b></td>
										<td width="14%" class="labelheading1 tbcellCss"><b>Procedure Name</b></td>
										<td width="8%" class="labelheading1 tbcellCss"><p id="unitsSelectID"><b>Approved Units</b></p></td>
										<%-- <c:if test="${dentalSurgUpd eq 'Y'}"> --%>
											<td width="9%" class="labelheading1 tbcellCss" align="center"><p id="unitsSelectIDNew"><b><font color="red"><i class="fa fa-exclamation-circle"></i>Units Done*</font></b></p></td>
										<%-- </c:if> --%>
										<td width="15%" class="labelheading1 tbcellCss" ><b>Special Investigations</b> </td>
										<!-- <td width="15%" class="labelheading1 tbcellCss"><b>Remarks</b></td> -->
										<td width="15%" class="labelheading1 tbcellCss"><b><font color="red">Teeth No/Quadrant No*</font></b></td>
										<td width="9%" class="labelheading1 tbcellCss"><b>Treating Doctor name</b></td>
										<c:if test="${priceView eq 'Y'}"> 
											<td width="7%" class="labelheading1 tbcellCss"><b>Package Amount</b></td>
										</c:if>
									</tr>
									<bean:size id="caseList" name="clinicalNotesForm" property="lstPreauthVO"/>
									<logic:greaterThan value="0" name="caseList">
									<logic:iterate id="results" name="clinicalNotesForm" property="lstPreauthVO" indexId="index" >
									<tr id="splInvetsDataTable<%=index+1%>" style="display:true">
										<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="asriCatName" />(<bean:write name="results" property="catId" />)</td>
										<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="catName" />(<bean:write name="results" property="icdCatCode" />)</td>
										<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="procName" />(<bean:write name="results" property="icdProcCode" />)</td>
										<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
											<logic:notEqual name="results" property="opProcUnits" value="-1">
												<%-- <bean:write name="results" property="opProcUnits" /> --%>
												
													${results.opProcUnits}
											</logic:notEqual>
											<logic:equal name="results" property="opProcUnits" value="-1">
												-NA-
											</logic:equal>
										</td>
										<c:if test="${dentalSurgUpd eq 'Y'}">
											<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
											${results.surgProcUnits}
											</td>
										</c:if>	
										
										<c:if test="${dentalSurgUpd ne 'Y'}">
											<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
													<select style="width:100%" id="unitsDropDown${index}" name="${results.seqNo}" title="Please Select The Number of Units to which treatment Done">
														<option value="-1">Select</option>
														<c:if test="${ results.opProcUnits ne '-1'}">
														<c:forEach begin="0" end="${results.opProcUnits}" varStatus="stats">
															<option value="${stats.index}">${stats.index}</option>
															<%-- <c:choose>	
																<c:when test="${results.surgProcUnits eq stats.index}">
																	<option value="${stats.index}" selected="selected">${stats.index}</option>
																</c:when>
																<c:otherwise>
																	<option value="${stats.index}">${stats.index}</option>
																</c:otherwise>
															</c:choose> --%>
															
														</c:forEach>
														</c:if>
													</select>
											</td>		
										</c:if>
										
										<bean:size id="splattachList" name="results" property="lstSplInvet" />
										<c:set var="splInvstCount" value="1" />
										<logic:greaterThan value="0" name="splattachList" >
											<td  class="tbcellBorder" style="word-wrap:break-word;">
												<logic:iterate id="results1" name="results" property="lstSplInvet" indexId="index1" >
												<a href="javascript:javascript:fn_openAtachment('<bean:write name="results1" property="filePath" />')" >
												<bean:write name="results1" property="filename" /></a>
												<c:if test="${fn:length(results.lstSplInvet) ne splInvstCount}">
												,
												</c:if>
												<c:set var="splInvstCount" value="${splInvstCount+1 }" />
												
												
												</logic:iterate>
											</td>
										</logic:greaterThan> 
										<logic:equal value="0" name="splattachList">
										<td  style="word-wrap:break-word;" class="tbcellBorder">&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;    -- &nbsp;</td>
										</logic:equal>
										<!--Sneha: removed bean:write to get , wherever &#44; is present  -->
										<%-- <td   style="word-wrap:break-word;" class="tbcellBorder">&nbsp;&nbsp;${results.investRemarks}</td> --%>
										<td   style="word-wrap:break-word;" class="tbcellBorder">
											<%-- &nbsp;&nbsp;${results.investRemarks} --%>
											<c:if test="${dentalSurgUpd ne 'Y'}">
												<textarea rows="3" maxlength="3000" id="unitstxtArea${index}" name="unitstxtArea${results.seqNo}" title="Please enter Teeth No/Quadrant No Details">${results.toothedUnits}</textarea>
											</c:if>
											<c:if test="${dentalSurgUpd eq 'Y'}">
												<textarea rows="3"  disabled="disabled" maxlength="3000" id="unitstxtArea${index}" name="unitstxtArea${results.seqNo}" >${results.toothedUnits}</textarea>
											</c:if>	
										</td> 
										<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="docName" /></td> 
										<c:if test="${priceView eq 'Y'}">
										<td  class="tbcellBorder" id="packgAmt${index}" style="word-wrap:break-word;"> </td>
										<script>
										fn_getPackageAmt('<bean:write name="results" property="hospStayAmt" />','<bean:write name="results" property="commonCatAmt" />','<bean:write name="results" property="icdAmt" />','<bean:write name="results" property="noOfDays" />','<bean:write name="results" property="opProcUnits" />','packgAmt${index}','<bean:write name="results" property="process" />','${slabAmount}');
										</script>
										</c:if>
									</tr>
									</logic:iterate>
									</logic:greaterThan>
								</table>	
								</logic:present> 
							</c:if>
						</td>
					</tr> 
					
					<tr>
						<td colspan="4" align="left" class="tbheader"><strong>Surgeon 
								Details</strong>
						</td>
					</tr>

					<tr>
						<td colspan="4">
							<div id="addTreatingDocDiv" style="display: ''">
								<table border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr>
										<td class="labelheading1 tbcellCss">Name<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;APMC Regn No<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Qualification<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Contact no<span
											class="mandatory">*</span>
										</td>
									</tr>

									<tr>
										<td>&nbsp;<html:text name="clinicalNotesForm"
												property="surgeonName" styleId="surgeonName" title="Please enter sureon name"
												onchange="javascript:chkOnlyAlpha('surgeonName','Surgeon Name');" 
												maxlength="50"></html:text>
										</td>
										<td>&nbsp;<html:text name="clinicalNotesForm"
												property="surgeonRegNo" styleId="surgeonRegNo" title="Please enter APMC Regn No"
												onchange="javascript:chkAlphaNumeric('surgeonRegNo','Surgeon RegNo');"
												maxlength="50"></html:text>
										</td>
										<td>&nbsp;<html:text name="clinicalNotesForm"
												property="surgeonQual" styleId="surgeonQual" title="Please enter surgeon qualification"
												onchange="javascript:chkAlpha('surgeonQual','Surgeon Qualification');"
												maxlength="50"></html:text>
										</td>
										<td>&nbsp;<html:text name="clinicalNotesForm"
												property="surgeonCnctNo" maxlength="10" styleId="surgeonCnctNo" title="Please enter surgeon caontact No"
												onchange="javascript:checkcontact(this,'Surgeon Contact no');"></html:text>
										</td>
									</tr>
									</c:if>

								</table>
							</div>
						</td>
					</tr>
					

					<!-- Anesthetist Details -->
					<c:if test="${medMgmtFlag== 'N'}">
						<tr>
							<td colspan="4">
								<table border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center">
									<tr>
										<td colspan="4" align="left" class="tbheader"><strong>Anesthetist
												Details</strong>
										</td>
									</tr>
									<tr>
										<td class="labelheading1 tbcellCss" width="25%">Anesthetist Name<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss" width="25%">&nbsp;APMC Reg
											No<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss" width="25%">&nbsp;Anesthetist
											Mobile No<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss" width="25%">&nbsp;Anesthesia Type
											<span class="mandatory">*</span>
										</td>
										<td width="25%"></td>
									</tr>

									<tr>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="anesthetistName" styleId="anesthetistName" title="Please enter anesthetist name"
												onchange="javascript:chkOnlyAlpha('anesthetistName','anesthetist name');"
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="anesthetistRegNo" styleId="anesthetistRegNo" title="Please enter anesthetist reg No"
												onchange="javascript:chkAlphaNumeric('anesthetistRegNo','anesthetist RegNo');" 
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="anesthetistMbNo" styleId="anesthetistMbNo" title="Please enter anesthetist mobile No"
												maxlength="10"
												onchange="javascript:checkcontact(this,'anesthetist mobile No');"></html:text>
										</td>
										<td class="tbcellBorder">
										<c:if
											test="${clinicalNotesForm.anesthesiaType=='' || enhApprovedFlag =='Y' || clinicalNotesForm.anesthesiaType == null}">
										<select title="Please select Anesthesia Type" name="anesthesiaType" id="anesthesiaType" onmousemove="javascript:getTitles('anesthesiaType')"
									        id="anesthesiaType" style="width: 95%">
										<option value="-1">-----Select-----</option>
										<option id="Local">Local</option>
										<option id="General">General</option>
										<option id="Spinal">Spinal</option>
										<option id="Others">Others</option>
								</select>
								</c:if>
								<c:if
											test="${clinicalNotesForm.anesthesiaType!='' && enhApprovedFlag !='Y' && clinicalNotesForm.anesthesiaType != null}">
										<html:text name="clinicalNotesForm" property="anesthesiaType" styleId="anesthesiaType" value="${clinicalNotesForm.anesthesiaType}"/>
											</c:if>
								</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:if>

					<!-- Assistant Teating Doctor Details -->
					<tr>
						<td>
							<table border="0" width="100%" cellpadding="1" cellspacing="1"
								align="center" class="tabBorder">
								<c:if test="${medMgmtFlag== 'Y'}">
									<tr>
										<td colspan="5" align="left" class="tbheader"><strong>Assistant
												Treating Doctor Details</strong>
										</td>
									</tr>
								</c:if>

								<c:if test="${medMgmtFlag== 'N'}">
									<tr>
										<td colspan="5" align="left" class="tbheader"><strong>Assistant
												Surgeon Details</strong>
										</td>
									</tr>

									<tr>
										<td class="labelheading1 tbcellCss">Name<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;APMC Regn No<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Qualification<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Contact no<span
											class="mandatory">*</span>
										</td>
										
									<tr>
									<tr>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="asstSurName" styleId="asstSurName" title="Please enter assistant surgeon name"
												onchange="javascript:chkAlpha('asstSurName','Assistant Surgeon Name');"
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="asstSurRegNo" styleId="asstSurRegNo" title="Please enter assistant surgeon reg No"
												onchange="javascript:chkAlphaNumeric('asstSurRegNo','Assistant Surgeon RegNo');"
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="asstSurQual" styleId="asstSurQual" title="Please enter assistant surgeon qualification"
												onchange="javascript:chkAlpha('asstSurQual','Assistant Surgeon Qualification');"
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="asstSurContctNo" styleId="asstSurContctNo" title="Please enter assistant surgeon contact No"
												maxlength="10"
												onchange="javascript:checkcontact(this,'Assistant Surgeon Contact no');"></html:text>
										</td>
										
									</tr>

									<tr>
									<td class="labelheading1 tbcellCss">&nbsp;Paramedic Name<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Nurse Name<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Expected
											Hospital Stay<span class="mandatory">*</span>
										</td>
									</tr>

									<tr>
									<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="paradMedicName" styleId="paradMedicName" title="Please enter paramedic name"
												onchange="javascript:chkAlpha('paradMedicName','Paramedic Name');"
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="nurseName" styleId="nurseName" title="Please enter nurse name"
												onchange="javascript:chkAlpha('nurseName','Nurse Name');"
												maxlength="50"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp; 
										<c:if
											test="${TrmtSurgSubmitFlag!='N' || (TrmtSurgSubmitFlag=='N' && enhApprovedFlag =='Y')}">
										<c:set
												value="${expHospStay1}" var="sample"></c:set> <select title="Please select hospital stay"
											name="expHospStay" id="expHospStay" onmousemove="javascript:getTitles('expHospStay')">
												<option value="-1">--Select--</option>
												<c:forEach var="expHospStay" begin="1" end="99">
													<option value="${expHospStay}">< c:out
														value="${expHospStay}" /></option>
													<c:if test="${expHospStay==sample}">
														<option value="${expHospStay}" selected="selected">
															< c:out value="${expHospStay}" /></option>
													</c:if>
												</c:forEach>
										</select>
										</c:if>
										<c:if
											test="${TrmtSurgSubmitFlag=='N' && enhApprovedFlag !='Y'}">
											<input type="text" value="${expHospStay1}" />
											</c:if>
										</td>
									</tr>
								</c:if>
								<c:if test="${medMgmtFlag== 'N'}">
									<tr class="tbheader">
									<td colspan="4"><b>Procedure Details with Specific Intra OP Findings</b></td>
									</tr>
									<tr>
									<td class="labelheading1 tbcellCss">Incision Type</td>
									<td class="labelheading1 tbcellCss">Intra OP Photos/Web Ex Taken</td>
									<td class="labelheading1 tbcellCss">Video Recording done </td>
									<td class="labelheading1 tbcellCss">Swab count and Instruments counts </td>
									</tr>
									<tr>
									<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="incisionType" styleId="incisionType" title="Please enter Incision Type"
												maxlength="50"></html:text>
										</td>
										<td>
										<c:if
											test="${clinicalNotesForm.intraOpPotos=='' || enhApprovedFlag =='Y' || clinicalNotesForm.intraOpPotos == null}">
										<html:radio property="intraOpPotos" value="Yes" styleId="intraOpPotos" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'Yes')" title="Please select Intra OP photos/Web Ex taken radio button" >Yes</html:radio>
										<html:radio property="intraOpPotos" value="No" styleId="intraOpPotos" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'No')" title="Please select Intra OP photos/Web Ex taken radio button">No</html:radio>
										</c:if>
										<c:if
											test="${clinicalNotesForm.intraOpPotos!='' && enhApprovedFlag !='Y' && clinicalNotesForm.intraOpPotos!=null}">
										<html:radio property="intraOpPotos" value="Yes" styleId="intraOpPotos" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Intra OP photos/Web Ex taken radio button" disabled="true">Yes</html:radio>
										<html:radio property="intraOpPotos" value="No" styleId="intraOpPotos" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Intra OP photos/Web Ex taken radio button" disabled="true">No</html:radio>
										</c:if>
										</td>
										<td>
										
										<c:if
											test="${clinicalNotesForm.videoRec=='' || enhApprovedFlag =='Y' || clinicalNotesForm.videoRec == null}">
										<html:radio property="videoRec" value="Yes" styleId="videoRec" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'Yes')" title="Please select Video recording taken radio button" >Yes</html:radio>
										<html:radio property="videoRec" value="No" styleId="videoRec" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'No')" title="Please select Video recording taken radio button">No</html:radio>
										</c:if>
										
										<c:if
											test="${clinicalNotesForm.videoRec!='' && enhApprovedFlag !='Y' && clinicalNotesForm.videoRec!=null}">
											<html:radio property="videoRec" value="Yes" styleId="videoRec" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Video recording taken radio button" disabled="true">Yes</html:radio>
										<html:radio property="videoRec" value="No" styleId="videoRec" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Video recording taken radio button" disabled="true">No</html:radio>
											</c:if>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="swabCount" styleId="swabCount" title="Please enter Swab Count and Instruments Count"
												onchange="javascript:validateNumber('Swab Count and Instruments Count',this,0);"
												maxlength="10"></html:text>
										</td>
										</tr>
										
										
										<tr>
									<td class="labelheading1 tbcellCss">Sutures ligatures</td>
									<td class="labelheading1 tbcellCss">Specimen removed</td>
									<td class="labelheading1 tbcellCss">Drainage count</td>
									<td class="labelheading1 tbcellCss">Blood loss</td>
									</tr>
									<tr>
									<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="sutureLigature" styleId="sutureLigature" title="Please enter Suture Ligature"
												onchange="javascript:chkAlphaNumeric('sutureLigature','Suture Ligature');"
												maxlength="50"></html:text>
										</td>
										<td>
										<c:if	test="${clinicalNotesForm.specimenRem=='' || enhApprovedFlag =='Y' || clinicalNotesForm.specimenRem == null}">
											<html:radio property="specimenRem" value="Yes" styleId="specimenRem" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'Yes')" title="Please select Specimen removed radio button" >Yes</html:radio>
										<html:radio property="specimenRem" value="No" styleId="specimenRem" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'No')" title="Please select Specimen removed radio button">No</html:radio>
										</c:if>
										<c:if	test="${clinicalNotesForm.specimenRem!='' && enhApprovedFlag !='Y' && clinicalNotesForm.specimenRem != null}">
										<html:radio property="specimenRem" value="Yes" styleId="specimenRem" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Specimen removed radio button" disabled="true" >Yes</html:radio>
										<html:radio property="specimenRem" value="No" styleId="specimenRem" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Specimen removed radio button" disabled="true">No</html:radio>
										</c:if>
										<div id="specimenRemDiv" style="display:none">
										<html:text
												name="clinicalNotesForm" property="specimenName" styleId="specimenName" title="Please enter Specimen Name"
												onchange="javascript:chkAlphaNumeric('specimenName','Specimen Name');"
												maxlength="50"></html:text>
										</div>
										
										</td>
										<td>
										
										<html:text
												name="clinicalNotesForm" property="drainageCnt" styleId="drainageCnt" title="Please enter Drainage count"
												onchange="javascript:validateNumber('Drainage count',this,0);"
												maxlength="10"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="bloodLosss" styleId="bloodLosss" title="Please enter Blood Loss"
												onchange="javascript:chkAlphaNumeric('bloodLosss','Blood Loss');"
												maxlength="50"></html:text>
										</td>
										</tr>
										
										
										<tr>
									<td class="labelheading1 tbcellCss">Complications if any</td>
									<td class="labelheading1 tbcellCss">Post Operative Instructions</td>
									<td class="labelheading1 tbcellCss">Condition of the Patient at the time of Shifting</td>
									
									</tr>
									<tr>
									<td class="tbcellBorder">&nbsp;
									<c:if	test="${clinicalNotesForm.complications=='' || enhApprovedFlag =='Y' || clinicalNotesForm.complications == null}">
									<html:radio property="complications" value="Yes" styleId="complications" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'Yes')" title="Please select Complications if any radio button" >Yes</html:radio>
										<html:radio property="complications" value="No" styleId="complications" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this,'No')" title="Please select Complications if any radio button">No</html:radio>
									</c:if>
									<c:if test="${clinicalNotesForm.complications!='' && enhApprovedFlag !='Y' && clinicalNotesForm.complications!=null}">
											<html:radio property="complications" value="Yes" styleId="complications" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Complications if any radio button" disabled="true">Yes</html:radio>
										<html:radio property="complications" value="No" styleId="complications" name="clinicalNotesForm" onclick="javascript:fn_radioClick(this)" title="Please select Complications if any radio button" disabled="true">No</html:radio>
											</c:if>
									<div id="complicationsDiv" style="display:none">
									
									<html:textarea
												name="clinicalNotesForm" property="complicationsRemarks" rows="3" styleId="complicationsRemarks" title="Please enter complication remarks"
												cols="20"
												onchange="javascript:chkAlphaNumeric('complicationsRemarks','Complication Remarks');func_maxlen('Remarks',this,1000);"
												styleClass="textfield"></html:textarea>
									
									</div>
										</td>
										
										<td>
										
										<html:text
												name="clinicalNotesForm" property="postOperativeInst" styleId="postOperativeInst" title="Please enter Post Operative Instructions"
												onchange="javascript:chkAlphaNumeric('postOperativeInst','Post Operative Instructions');"
												maxlength="100"></html:text>
										</td>
										<td class="tbcellBorder">&nbsp;<html:text
												name="clinicalNotesForm" property="conditiOfPat" styleId="conditiOfPat" title="Please enter >Condition of the Patient at the time of Shifting"
												onchange="javascript:chkAlphaNumeric('conditiOfPat','Condition of the patient');"
												maxlength="100"></html:text>
										</td>
										</tr>
										
									</c:if>
								<!--  If not surgery -->
								<c:if test="${medMgmtFlag== 'Y'}">
									<tr>
										<td class="labelheading1 tbcellCss">&nbsp;Treatment Start
											Date<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Death Date<span
											class="mandatory">*</span>
										</td>
									</tr>

									<tr>
										<td class="tbcellBorder">&nbsp; <html:text
												style="width:70px" name="clinicalNotesForm" title="Please select Treatment Start date"
												property="surgStartDt" styleId="surgStartDt"  readonly="true"></html:text>
											<!--   <img src="/Operations/images/calend.gif" onclick="javascript:window_open('surgStartDt','150','470')" alt="Calendar" title="Calendar"></img> -->
										</td>
										<td class="tbcellBorder">&nbsp; <html:text
												style="width:70px" name="clinicalNotesForm" title="Please select death date"
												property="deathDate" styleId="deathDate"  readonly="true"></html:text>
											<!--   <img src="/Operations/images/calend.gif" onclick="javascript:window_open('deathDate','150','470')" alt="Calendar" title="Calendar"></img> -->
										</td>
									</tr>
								</c:if>
								<!--  If surgery -->
								<c:if test="${medMgmtFlag== 'N' || medicalFlag== 'Y'}">
									<tr>
									<tr>
										<td colspan="5" align="left" class="tbheader"><strong>Treatment/Surgery
												Date</strong>
										</td>
									</tr>
									<tr>
										<td class="labelheading1 tbcellCss">&nbsp;Treatment/Surgery
											Date<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Surgery Start
											Time</td>
										<td class="labelheading1 tbcellCss">&nbsp;Surgery End
											Time</td>
										<td class="labelheading1 tbcellCss">&nbsp;Death Date<span
											class="mandatory"></span>
										</td>
									<tr>

										<c:if
											test="${TrmtSurgSubmitFlag!='N' || (TrmtSurgSubmitFlag=='N' && enhApprovedFlag =='Y')}">
											<tr>
												<td class="tbcellBorder">&nbsp; <html:text
														style="width:70px" name="clinicalNotesForm"
														property="surgStartDt"  styleId="surgStartDt" title="Please select Treatment/Surgery date"
														readonly="true"></html:text> <!--   <img src="/Operations/images/calend.gif" onclick="javascript:window_open('surgStartDt','150','470')" alt="Calendar" title="Calendar"></img> -->
												</td>


												<td class="tbcellBorder"><select name="surgStrHrs" title="Please select surgery start hrs" onmousemove="javascript:getTitles('surgStrHrs')"
													id="surgStrHrs" style="width: 45px" class="treatmentSugretyTime" >
														<option value="-1">-:-</option>
														<c:forEach var="surgStrHrs" begin="0" end="23">
															<option value="${surgStrHrs}">< c:out
																value="${surgStrHrs}" /></option>
															<c:if test="${surgStrHrs==lStrSurgStrHrs}">
																<option value="${surgStrHrs}" selected="selected">
																	< c:out value="${surgStrHrs}" /></option>
															</c:if>
														</c:forEach>
												</select>HH&nbsp; <select name="surgStrMins" title="Please select surgery start mins" id="surgStrMins" onmousemove="javascript:getTitles('surgStrMins')"
													style="width: 45px" class="treatmentSugretyTime">
														<option value="-1">-:-</option>
														<c:forEach var="surgStrMins" begin="0" end="59">
															<option value="${surgStrMins}">< c:out
																value="${surgStrMins}" /></option>
															<c:if test="${surgStrMins==lStrSurgStrMins}">
																<option value="${surgStrMins}" selected="selected">
																	< c:out value="${surgStrMins}" /></option>
															</c:if>
														</c:forEach>
												</select>MM</td>

												<td class="tbcellBorder"><select name="surgEndHrs" title="Please select surgery end hrs " onmousemove="javascript:getTitles('surgEndHrs')"
													id="surgEndHrs" style="width: 45px" class="treatmentSugretyTime">
														<option value="-1">-:-</option>
														<c:forEach var="surgEndHrs" begin="0" end="23">
															<option value="${surgEndHrs}">< c:out
																value="${surgEndHrs}" /></option>
															<c:if test="${surgEndHrs==lStrSurgEndHrs}">
																<option value="${surgEndHrs}" selected="selected">
																	< c:out value="${surgEndHrs}" /></option>
															</c:if>
														</c:forEach>
												</select>HH&nbsp; <select name="surgEndMins" id="surgEndMins" title="Please select surgery end mins" onmousemove="javascript:getTitles('surgEndMins')"
													style="width: 45px" class="treatmentSugretyTime">
														<option value="-1">-:-</option>
														<c:forEach var="surgEndMins" begin="0" end="59">
															<option value="${surgEndMins}">< c:out
																value="${surgEndMins}" /></option>
															<c:if test="${surgEndMins==lStrSurgEndMins}">
																<option value="${surgEndMins}" selected="selected">
																	< c:out value="${surgEndMins}" /></option>
															</c:if>
														</c:forEach>
												</select>MM</td>


												<td class="tbcellBorder">&nbsp; <html:text
														style="width:70px" name="clinicalNotesForm"
														property="deathDate" styleId="deathDate"  readonly="true" title="Please select death date"></html:text>
														<img  src="images/eraser.png" title="click to clear date" height="20" width="20" onclick="javascript:eraser('deathDate');"></img>&nbsp;
													<!--  <img src="/Operations/images/calend.gif" onclick="javascript:window_open('deathDate','150','470')" alt="Calendar" title="Calendar"></img> -->
												</td>
											</tr>
										</c:if>

										<c:if
											test="${TrmtSurgSubmitFlag=='N' && enhApprovedFlag !='Y'}">
											<tr>
												<td class="tbcellBorder"><html:text
														name="clinicalNotesForm" property="surgStartDt"
														styleId="surgStartDt"  readonly="true" />
												</td>
												<td class="tbcellBorder"><input type="text"
													readonly="readonly" value="${surgStartTime}" />
												</td>
												<td class="tbcellBorder"><input type="text"
													readonly="readonly" value="${surgEndTime}" />
												</td>
												<td class="tbcellBorder"><html:text
														name="clinicalNotesForm" property="deathDate"
														 readonly="true" />
												</td>
											</tr>
										</c:if>
								</c:if>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="5" align="center">
			
										<c:if test="${surgButFlag eq 'Y'}">
											<c:if test="${TrmtSurgSubmitFlag!='N' || (lStrTrmtMedicalSubmitFlag != null && lStrTrmtMedicalSubmitFlag !='Y')}">

												<button type="button" class="but" id="SaveSurgeryBut"
													value="Submit" onclick="fn_saveSurgeryDtls()">Submit
												</button>
												<c:if test="${(lStrTrmtMedicalSubmitFlag != null && lStrTrmtMedicalSubmitFlag !='Y') || TrmtSurgSubmitFlag!='N' }">
													<button type="button" class="but" value="AddView Attachments" id="addViewAttachSur"
														onclick="fn_addAttachments('surgery')">Add/View
														Attachments</button>
												</c:if>
											</c:if> 
										<c:if test="${enhApprovedFlag =='Y' && casesForApprovalFlag!='N'}">
											
											<button type="button" class="but" id="SaveSurgeryBut"
												value="Submit" onclick="fn_saveSurgeryDtls()">Submit
											</button>
											<button type="button" class="but" value="AddView Attachments" id="addViewAttachSur"
												onclick="fn_addAttachments('surgery')">Add/View
												Attachments</button>
										</c:if>
										</c:if> 
										
										</td>
								</tr>


							</table>
						</td>
					</tr>
				</c:if>

				<!-- To display existing POST OP clinical notes -->
				<logic:present name="clinicalNotesForm"
					property="postClinicalNotesList">
					<%
						int i = 1;
					%>
					<bean:size id="size" name="clinicalNotesForm"
						property="postClinicalNotesList" />
					<logic:greaterThan value="0" name="size">
							<table align="center" width="100%" class="tabBorder"
								cellpadding="1" cellspacing="1">
								<tr>
									<td colspan="9" align="left" class="tbheader"><strong><b><font
												size="2">Post Clinical Notes</font>
										</b>
									</strong>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" width="100%" cellpadding="1" cellspacing="1"
											align="center" class="tabBorder">
											<tr>
												<td width="4%" class="labelheading1 tbcellCss">SNo</td>
												<td width="6%" class="labelheading1 tbcellCss">Clinical ID</td>
												<td width="11%" class="labelheading1 tbcellCss">Date&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss">BP</td>
												<td width="5%" class="labelheading1 tbcellCss">Pulse</td>
												<td width="7%" class="labelheading1 tbcellCss">Temperature&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss">Respiratory
													Rate&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss">Heart
													Sounds&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss">Lungs&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss">Fluid
													Input&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss">Fluid
													Output&nbsp;</td>
												<td width="10%" class="labelheading1 tbcellCss">Ward
													Type&nbsp;</td>
												<!-- <td>&nbsp;In Time</td>
            <td>&nbsp;Out Time</td> -->
												<td width="10%" class="labelheading1 tbcellCss">Daily
													Progress Notes&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss">&nbsp;Wound Details</td>
												<!--<td>View&nbsp;</td>-->
											</tr>

											<logic:iterate id="result" name="clinicalNotesForm"
												property="postClinicalNotesList" indexId="sno">
												<tr class="border<%=sno % 2%>">
													<td class="tbcellBorder"><%=sno + 1%></td>
													<td class="tbcellBorder"><bean:write name="result"
															property="CLINICALID" />
													</td>
													<td class="tbcellBorder"><bean:write
															name="result" property="investGnDate" />
													</td>
													<td class="tbcellBorder"><bean:write name="result"
															property="BLOODPRESSURE" />
													</td>
													<td align="center" class="tbcellBorder"><bean:write
															name="result" property="PULSE" />
													</td>
													<td align="center" class="tbcellBorder"><bean:write
															name="result" property="TEMPERATURE" />&nbsp;</td>
													<td align="center" class="tbcellBorder"><bean:write
															name="result" property="RESPIRATORY" />
													</td>
													<td align="center" class="tbcellBorder"><bean:write
															name="result" property="HEART_RATE" />
													</td>
													<td class="tbcellBorder"><bean:write
															name="result" property="LUNGS" />
													</td>
													<td class="tbcellBorder"><bean:write
															name="result" property="FLUIDINPT" />
													</td>
													<td class="tbcellBorder"><bean:write
															name="result" property="FLUIDOUTPUT" />
													</td>
													<td class="tbcellBorder"><bean:write name="result"
															property="WARD_TYPE" />
													</td>
													<!--<input type="hidden" name="preWardType" value='<bean:write name="result" property="WARD_TYPE"/>'> -->
													<!--<td>&nbsp;<bean:write name="result" property="WARDINTIME"/></td>
            <td>&nbsp;<bean:write name="result" property="WARDOUTTIME"/></td> -->
													<td class="tbcellBorder"><bean:write name="result"
															property="REMARKS" />
													</td>
													<td class="tbcellBorder"><bean:write name="result"
															property="WOUND_DTLS" />
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
					</logic:greaterThan>
						<logic:greaterThan value="0" name="size">
					<table width="100%" cellpadding="1" cellspacing="1">
						<!--             <tr><td colspan="10" align="center" class="tbheader"><strong><b><font size="2">Clinical Notes</font></b></strong></td></tr> -->
						<tr>
							<td>
								<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr class="tbheader"><td colspan="12"><b>Plasma Blood Glucose and Insulin Dosage Chart</b></td></tr>
									<tr>
										<td width="2%" class="labelheading1 tbcellCss">SNo</td>
										<td width="6%" class="labelheading1 tbcellCss">Clinical ID</td>
										<td width="9%" class="labelheading1 tbcellCss">Date&nbsp;</td>
										<td width="9%" class="labelheading1 tbcellCss">Doctor Name</td>
										<td width="5%" class="labelheading1 tbcellCss">BBF(7:00 am )</td>
										<td width="5%" class="labelheading1 tbcellCss">BL(1:00pm)</td>
										<td class="labelheading1 tbcellCss">BD(7:00pm)&nbsp;</td>
										<td width="10%" class="labelheading1 tbcellCss">Plasma Blood Glucose MN
											Rate&nbsp;</td>
										<td width="7%" class="labelheading1 tbcellCss">BBF(7:00am)
											&nbsp;</td>
										<td width="8%" class="labelheading1 tbcellCss">SR(1:00pm)</td>
										<td width="8%" class="labelheading1 tbcellCss">BD(7:00pm)
											&nbsp;</td>
										<td width="8%" class="labelheading1 tbcellCss">Insulin Dosage MN</td>
										
										
									</tr>

									<logic:iterate id="result" name="clinicalNotesForm"
										property="postClinicalNotesList" indexId="sno1">
										<tr class="border<%=sno1 % 2%>">
											<td class="tbcellBorder"><%=sno1 + 1%></td>
											<td class="tbcellBorder"><bean:write name="result"
													property="CLINICALID" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="investGnDate" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="docName" />
											</td>
											<td class="tbcellBorder"><bean:write name="result"
													property="plasmaBbf" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="plasmaBl" />
											</td>
											<td align="center" class="tbcellBorder"><bean:write
													name="result" property="plasmaBd" />&nbsp;</td>
											
											<td width="10%" align="center" class="tbcellBorder"><bean:write
													name="result" property="plasmaMn" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="insulinBbf" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="insulinSr" />
											</td>
											<td width="10%" class="tbcellBorder"><bean:write
													name="result" property="insulinBd" />
											</td>
											<td class="tbcellBorder"><bean:write name="result"
													property="insulinMn" />
											</td>
											
											
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</table>
			</logic:greaterThan>
				</logic:present>
<br>

<logic:present name="clinicalNotesForm" property="drugsLst">
        <bean:size  id="drugSize" name="clinicalNotesForm" property="drugsLstPre"/>
        <logic:greaterThan value="0" name="drugSize">
 <table  width="100%"  id="drugsTable1"  border="0" style="table-layout:fixed;word-wrap:break-word;">
 <tr class="tbheader"><td colspan="13"><b>Drugs List</b></td> </tr>
      <tr>  
      	<td width="5%" class="labelheading1 tbcellCss">Sno.</td>  
      	<td width="10%" class="labelheading1 tbcellCss">Clinical Id</td>         
        <td width="10%" class="labelheading1 tbcellCss">Main Group Name</td>   
       	<td width="10%" class="labelheading1 tbcellCss">Therapeutic Main Group Name</td>
        <td width="10%" class="labelheading1 tbcellCss">Pharmacological SubGroup Name</td>
        <td width="10%" class="labelheading1 tbcellCss">Chemical SubGroup Name</td>
        <td width="10%" class="labelheading1 tbcellCss">Chemical Substance Name</td>
        <td width="5%" class="labelheading1 tbcellCss">Route Type</td>
        <td width="10%" class="labelheading1 tbcellCss">Route</td>
        <td width="5%" class="labelheading1 tbcellCss">Strength Type</td>
        <td width="10%" class="labelheading1 tbcellCss">Strength</td>
        <td width="5%" class="labelheading1 tbcellCss">Dosage</td>
        <td width="5%" class="labelheading1 tbcellCss">Medication Period</td>
        
        </tr>
        
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="clinicalNotesForm" property="drugsLstPre" >
        <tr>  
      	<td width="5%" class="tbcellBorder"><%=k++ %></td>    
      	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="clinicalId" /></td>       
        <td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="drugTypeName" /></td>   
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="drugSubTypeName" /></td> 
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="pSubGrpName"/></td>
        <td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="cSubGrpName"/></td> 
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="drugName" /></td> 
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="routeTypeName" /></td> 
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="routeName" /></td>  
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="strengthTypeName" /></td>
       	<td width="10%" class="tbcellBorder"><bean:write name="drugLst" property="strengthName" /></td>  
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="dosage" /></td>  
       	<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="medicationPeriod" /></td> 
       
        </tr>
        </logic:iterate>  
</table>
</logic:greaterThan></logic:present>
				<!-- To add post clinical notes -->
				<table border="0" width="100%" cellpadding="1" cellspacing="1"
					align="center" class="tabBorder">
					<c:if test="${postAddClinicNotesFlag == 'YY'}">
						<tr>
							<td colspan="6" align="left" class="tbheader"><input
								type="checkbox" id="postAddClinicalNotes"
								onclick="enablePostAddNotesDiv()" /><strong>Add
									Post-OP/Therapy notes</strong>
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="6">
							<div id="postAddNotesDiv" style="display: none">
								<table border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr>
										<td class="labelheading1 tbcellCss">Date<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">BP<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Pulse Rate<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Temperature<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Ward Type<span
											class="mandatory">*</span>
										</td>
										<!-- <td>&nbsp;Ward In Time</td>	
<td>&nbsp;Ward Out Time</td> -->
										<td class="labelheading1 tbcellCss">Respiratory Rate<span
											class="mandatory">*</span>
										</td>
									</tr>
									<tr>

										<td class="tbcellBorder"><html:text style="width:80px"
												name="clinicalNotesForm" property="invesgtnDate1" title="Please select date"
												styleId="invesgtnDate1"  readonly="true"></html:text> <!-- <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('invesgtnDate1','150','470')" alt="Calendar" title="Calendar"></img> -->
										</td>
										<td class="tbcellBorder"><select name="systolic1" title="Please select systolic pressure" onmousemove="javascript:getTitles('systolic1')"
											id="systolic1" style="width: 55px">
												<option value="-1">--:--</option>
												<c:forEach var="systolic1" begin="50" end="210">
													<option value="${systolic1}">< c:out
														value="${systolic1}" /></option>
												</c:forEach>
										</select>&nbsp;/&nbsp; <select name="diastolic1" title="Please select diastolic pressure" id="diastolic1" onmousemove="javascript:getTitles('diastolic1')"
											style="width: 55px">
												<option value="-1">--:--</option>
												<c:forEach var="diastolic1" begin="33" end="120">
													<option value="${diastolic1}">< c:out
														value="${diastolic1}" /></option>
												</c:forEach>
										</select></td>
										<td class="tbcellBorder"><select name="pulseRate1" title="Please select pulse rate" onmousemove="javascript:getTitles('pulseRate1')"
											id="pulseRate1" style="width: 95%">
												<option value="-1">-Select-</option>
												<c:forEach var="pulseRate1" begin="40" end="160">
													<option value="${pulseRate1}">< c:out
														value="${pulseRate1}" /></option>
												</c:forEach>
										</select></td>
										<td class="tbcellBorder"><select name="temperature1" title="Please select temperature" onmousemove="javascript:getTitles('temperature1')"
											id="temperature1" style="width: 95%">
												<option value="-1">-Select-</option>
												<c:forEach var="temperature1" begin="36" end="42">
													<option value="${temperature1}">< c:out
														value="${temperature1}" /></option>
												</c:forEach>
										</select></td>

										<td class="tbcellBorder"><select name="wardType1" title="Please select ward type" onmousemove="javascript:getTitles('wardType1')"
											id="wardType1" style="width: 95%">
												<option value="-1">-----Select-----</option>
												<option id="PrivateWard">Private Ward</option>
										        <option id="SemiPrivateWard">Semi-Private Ward</option>
												<option id="ICU">ICU</option>
												<option id="Post-OP">Post-OP</option>
												<option id="StepDown">StepDown</option>
										</select></td>

										<td class="tbcellBorder"><select name="respRate1" title="Please select respiratory rate" onmousemove="javascript:getTitles('respRate1')"
											id="respRate1">
												<option value="-1">-Select-</option>
												<c:forEach var="respRate1" begin="5" end="50">
													<option value="${respRate1}">< c:out
														value="${respRate1}" /></option>
												</c:forEach>
										</select></td>

										<!-- 
<td>
<select name="wardInTimeHrs1" id="wardInTimeHrs1"  style="width:45px">
<option value="-1">-:-</option>
 <c:forEach var="wardInTimeHrs1" begin="0" end="23"> 
   <option value="${wardInTimeHrs1}"> < c:out value="${wardInTimeHrs1}" /></option>                           
  </c:forEach>
</select>HH&nbsp;
<select name="wardInTimeMins1" id="wardInTimeMins1" style="width:45px">
<option value="-1">-:-</option>
 <c:forEach var="wardInTimeMins1" begin="0" end="59">
   <option value="${wardInTimeMins1}"> < c:out value="${wardInTimeMins1}" /></option>                           
  </c:forEach>
</select>MM
</td>

<td>
<select name="wardOutTimeHrs1" id="wardOutTimeHrs1" style="width:45px">
<option value="-1">-:-</option>
 <c:forEach var="wardOutTimeHrs1" begin="0" end="23">
   <option value="${wardOutTimeHrs1}"> < c:out value="${wardOutTimeHrs1}" /></option>                           
  </c:forEach>
</select>HH&nbsp;
<select name="wardOutTimeMins1" id="wardOutTimeMins1" style="width:45px">
<option value="-1">-:-</option>
 <c:forEach var="wardOutTimeMins1" begin="0" end="59">
   <option value="${wardOutTimeMins1}"> < c:out value="${wardOutTimeMins1}" /></option>                           
  </c:forEach>
</select>MM
</td>-->
									</tr>

									<tr>
										<td class="labelheading1 tbcellCss">Heart Sounds<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">Lungs<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">Fluid Input<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">Fluid Output<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss" colspan="2">Daily
											Doctor Notes<span class="mandatory">*</span>
										</td>
									</tr>
									<tr>
										<td class="tbcellBorder"><select name="heartRate1" title="Please select heart sounds"
											id="heartRate1" onmousemove="javascript:getTitles('heartRate1')">
												<option value="-1">-Select-</option>
												<option id="S1">S1</option>
												<option id="S2">S2</option>
												<option id="S3">S3</option>
												<option id="S4">S4</option>
												<option id="Murmours">Murmours</option>
												<option id="ExtraSounds">ExtraSounds</option>
										</select></td>
										<td class="tbcellBorder"><html:text
												name="clinicalNotesForm" styleId="lungs1" property="lungs1" title="Please enter lungs"
												maxlength="50"
												onchange="javascript:chkAlphaNumeric('lungs1','Lungs');validateAllphaNumericComb('lungs1','Lungs');" />
										</td>
										<td class="tbcellBorder"><html:text
												name="clinicalNotesForm" styleId="fluidInput1" title="Please enter fluid input"
												property="fluidInput1" maxlength="50"
												onchange="javascript:chkAlphaNumeric('fluidInput1','Fluid Input');validateAllphaNumericComb('fluidInput1','Fluid Input');" />
										</td>
										<td class="tbcellBorder"><html:text
												name="clinicalNotesForm" styleId="fluidOutput1" title="Please enter fluid output"
												property="fluidOutput1" maxlength="50"
												onchange="javascript:chkAlphaNumeric('fluidOutput1','Fluid Output');validateAllphaNumericComb('fluidOutput1','Fluid Output');" />
										</td>
										<td class="tbcellBorder" colspan="2">&nbsp;<html:textarea
												name="clinicalNotesForm" property="remarks1" rows="3" styleId="remarks1" title="Please enter remarks"
												cols="20"
												onchange="javascript:chkAlphaNumeric('remarks1','daily progress notes');func_maxlen('Remarks',this,3000);"
												styleClass="textfield"></html:textarea>
										</td>

									<tr>
										<td colspan="6" class="tbcellBorder"><input type="radio"
											name="woundStatus" id="healthy" value="Healthy"
											onclick="fn_enableWoundDtls()" title="Please select healthy/non-healthy radio button">Healthy</input> <input
											type="radio" name="woundStatus" id="notHealthy"
											value="Not Healthy" onclick="fn_enableWoundDtls()" title="Please select healthy/non-healthy radio button">NotHealthy</input>
											<html:textarea property="woundDtls" name="clinicalNotesForm"
												styleId="woundDtlsDiv" rows="3" cols="30" 
												style="display:none" title="Please enter remarks"
												onchange="javascript:chkAlphaNumeric('woundDtls','wound Details');func_maxlen('Not Healthy Remarks',this,3000);"
												></html:textarea>
											<!-- <input id="woundDtlsDiv" style="display:none" type="text"  name="woundDtls" maxlength="800" onchange="javascript:chkAlphaNumeric('woundDtls','wound Details');"/> -->
										</td>
									</tr>
									<tr>
										<td colspan="6">&nbsp;</td>
									</tr>
									</tr>

								</table>
							</div>
						</td>	
					</tr>


					<tr>
						<td colspan="4" align="center">
							<div id="postAddNotesDiv1" style="display: none">
								<button type="button" class="but" id="SavePostNotesBut"
									value="Submit"
									onclick="javascript:fn_savePostClinicalNotes('POST')">Submit</button>
							</div></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>	
					<!-- For discharge summary panel or div-->
				
					
							<table border="0" width="100%" cellpadding="1" cellspacing="1"
								align="center" class="tabBorder">
								<c:if test="${dischargeSumryFlag == 'Y'}">
									<tr>
										<td colspan="4" align="left" class="tbheader"><strong>Discharge
												Summary</strong>
										</td>
									</tr>

									<tr>
										<td class="labelheading1 tbcellCss">Treatment Given<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Operative
											Findings<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Post Operative
											Period<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Post
											Surgery/Theraphy<br> Special Investigations Given<span
											class="mandatory">*</span>
										</td>
									</tr>

									<tr>
										<td class="tbcellBorder"><html:textarea
												name="clinicalNotesForm" property="treatmentGvn" rows="2" styleId="treatmentGvn"
												cols="20" title="Please enter treatment given" 
												onchange="javascript:chkAlphaNumeric('treatmentGvn','Treatment Given');func_maxlen('Treatment Given',this,3000)"
												 />
										</td>
										<td class="tbcellBorder"><html:textarea
												name="clinicalNotesForm" property="operatveFindgs" rows="2" styleId="operatveFindgs"
												cols="20" title="Please enter operative findings"
												onchange="javascript:chkAlphaNumeric('operatveFindgs','Operative Findings');func_maxlen('Operative Findings',this,3000)"
												 />
										</td>
										<td class="tbcellBorder"><html:textarea rows="2" cols="20" title="Please enter post operative period"
												name="clinicalNotesForm" property="postOperatvePerd" styleId="postOperatvePerd"
												onchange="javascript:chkAlphaNumeric('postOperatvePerd','Post Operative Period');func_maxlen('Post Operative Period',this,3000)" />
										</td>
										<td class="tbcellBorder"><html:textarea rows="2" cols="20" title="Please enter special investigations"
												name="clinicalNotesForm" property="postSplInvstgtns" styleId="postSplInvstgtns"
												onchange="javascript:chkAlphaNumeric('postSplInvstgtns','Post Special Investigations Given');func_maxlen('Post Special Investigations Given',this,3000)" />
										</td>
									</tr>

									<tr>
										<td class="labelheading1 tbcellCss">&nbsp;Status at the
											time of Discharge<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Review<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Advice<span
											class="mandatory">*</span>
										</td>
										<c:if test="${DischargeSumryRadioFlag!='N'}">
											<td class="labelheading1 tbcellCss"><input type="radio"
												name="dischargeDeath" id="discharge" value="Discharge"
												onclick="fn_enableDischargeDeathDiv()" title="Discharge">Discharge</input> <input
												type="radio" name="dischargeDeath" id="death" value="Death"
												onclick="fn_enableDischargeDeathDiv()" title="Death">Death</input></td>
										</c:if>

										<c:if test="${DischargeSumryRadioFlag=='N'}">
											<td class="labelheading1 tbcellCss">
												<c:if test="${DischargeRadioSel=='checked'}">
													<input type="radio" name="dischargeDeath" id="discharge"
														value="Discharge" disabled="disabled" title="Discharge"
														checked="${DischargeRadioSel}">Discharge</input>
													<input type="radio" name="dischargeDeath" id="death"
														value="Death" disabled="disabled" title="Death">Death</input>
												</c:if> 
												<c:if test="${DeathRadioSel=='checked'}">
													<input type="radio" name="dischargeDeath" id="discharge" title="Discharge"
														value="Discharge" disabled="disabled">Discharge</input>
													<input type="radio" name="dischargeDeath" id="death" title="Death"
														value="Death" disabled="disabled" 
														checked="${DeathRadioSel}">Death</input>
												</c:if>
											</td>
										</c:if>
									</tr>

									<tr>
										<td class="tbcellBorder"><html:textarea rows="2" cols="20"
												name="clinicalNotesForm" property="statusAtDischrg" styleId="statusAtDischrg" title="Please enter status at the time of discharge"
												onchange="javascript:chkAlphaNumeric('statusAtDischrg','status at the time of discharge');func_maxlen('status at the time of discharge',this,3000)" />
										</td>
										<td class="tbcellBorder"><html:textarea rows="2" cols="20"
												name="clinicalNotesForm" property="review"  styleId="review" title="Please enter review"
												onchange="javascript:chkAlphaNumeric('review','Review');func_maxlen('Review',this,3000)" />
										</td>
										<td class="tbcellBorder"><html:textarea
												name="clinicalNotesForm" property="advice" rows="2" styleId="advice"
												cols="20"
												onchange="javascript:chkAlphaNumeric('advice','Advice');func_maxlen('Advice',this,3000)"
												  title="Please enter adivce " />
										</td>
									</tr>
								</c:if>
							</table>
					<!-- For discharge panel or div -->
					<tr>
						<td colspan="4">
							<div id="dischargeDiv" style="display:none">
								<table border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr>
										<td colspan="4" align="left" class="tbheader"><b>Discharge</b></td>
									</tr>
									<tr>
										<td class="labelheading1 tbcellCss">&nbsp;Discharge Date<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Next Follow Up
											Date<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Consult at
											Block Name<span class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Floor<span
											class="mandatory">*</span>
										</td>
										
									</tr>
									

									<tr>
										<td class="tbcellBorder"><html:text style="width:80px"
												name="clinicalNotesForm" property="disDate" title="Please select discharge date"
												styleId="disDate"  readonly="true"></html:text> <c:if
												test="${DischargeSumryButFlag!='N'}">
												<!--   <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('disDate','150','470')" alt="Calendar" title="Calendar"></img> -->
											</c:if></td>
										<td class="tbcellBorder"><html:text style="width:80px"
												name="clinicalNotesForm" property="nxtFollUpDt" title="Please select next follow up date"
												styleId="nxtFollUpDt"  readonly="true"></html:text> <c:if
												test="${DischargeSumryButFlag!='N'}">
												<!--  <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('nxtFollUpDt','150','470')" alt="Calendar" title="Calendar"></img> -->
											</c:if></td>
										<td class="tbcellBorder"><html:text
												name="clinicalNotesForm" styleId="blockName" maxlength="90" title="Please enter consult at block name"
												property="blockName"
												onchange="javascript:chkAlphaNumeric('blockName','Consult at block Name');" />
										</td>
										<td class="tbcellBorder"><html:text
												name="clinicalNotesForm" styleId="disfloor" maxlength="100" title="Please enter floor "
												property="disfloor"
												onchange="javascript:chkAlphaNumeric('disfloor','Floor');" />
										</td>
										
									</tr>
									<tr>
									<td class="labelheading1 tbcellCss">&nbsp;Room No.<span
											class="mandatory">*</span>
										</td>
									</tr>
									<tr>
									<td class="tbcellBorder"><html:text title="Please enter room no"
												name="clinicalNotesForm" styleId="disroomNo" maxlength="40"
												property="disroomNo"
												onchange="javascript:chkAlphaNumeric('disroomNo','Room No');" />
										</td>
									</tr>

									<tr>
										<td colspan="4" align="center">
											<!-- <button type="button" class="but" value="Add/View Discharge Photo"  onclick="fn_addAttachments()">Add/View Discharge Photo</button> -->
											<!-- <button type="button" class="but"  value="Add/View Discharge Summary Attachments"  onclick="fn_addAttachments()">Add/View Discharge Summary Attachments</button> -->
											<!-- <button type="button" class="but"  value="Add/View Letters"  onclick="fn_addAttachments()">Add/View Letters</button> -->
										</td>
									</tr>

									<c:if test="${DischargeSumryButFlag!='N' && medcoRole =='Y' && casesForApprovalFlag!='N'}">
										<tr>
											<td colspan="4" align="center">
												
												<button type="button" class="but" id="SaveDisSumryBut"
													value="Save" onclick="fn_saveSubDischargeSumry('save')">Save</button>
												<c:if test="${ehfDischargeSummarySize eq '1'}">
												<button type="button" class="but" id="SubmitDisSumryBut"
													value="Submit" onclick="fn_saveSubDischargeSumry('submit')">Submit</button>
												</c:if>
											<button type="button" class="but" id="addViewAttachDis"
													value="Add/View Death Summary Attachments"
													onclick="fn_addAttachments('discharge')">Add/View
													Attachments</button>
											<button type="button" class="but"
													value="Generate Patient Satisfaction Letter"
													onclick="fn_generateSatisfactionLetter()">Generate Patient Satisfaction Letter
											</button>
											</td>
										</tr>
									

									<tr>
										<td colspan="4"><font color="red"> Note: 1) Once
												the Discharge date/Death Date is updated in the discharge
												summary, it can not be modified at any point of time.<br>
												2) The Claim can be initiated by the network hospital only
												after 11 days from the date of <font color="blue">Discharge
													update</font> Status.</font></td>
									</tr>
									</c:if>
									
				
							
									
									
								</table>
							</div></td>
					</tr>


					<!-- For death panel or div -->
					<tr>
						<td colspan="5">
							<div id="deathDiv" style="display:none">
								<table border="0" width="100%" cellpadding="1" cellspacing="1"
									align="center" class="tabBorder">
									<tr>
										<td colspan="4" align="left" class="tbheader"><b>Death</b></td>
									</tr>
									<tr>
										<td class="labelheading1 tbcellCss">&nbsp;Death Date<span
											class="mandatory">*</span>
										</td>
										<td class="labelheading1 tbcellCss">&nbsp;Cause of Death</td>
										<td class="labelheading1 tbcellCss">&nbsp;</td>
										<td class="labelheading1 tbcellCss">&nbsp;</td>
									</tr>

									<tr>
										<td class="tbcellBorder"><html:text style="width:80px"
												name="clinicalNotesForm" property="disDeathDate" title="Please select death date"
												styleId="disDeathDate"  readonly="true"></html:text> <c:if
												test="${DischargeSumryButFlag!='N' && medcoRole =='Y'}">
												<!--   <img src="/Operations/images/calend.gif" align="absmiddle" onclick="javascript:window_open('disDeathDate','150','470')" alt="Calendar" title="Calendar"></img> -->
											</c:if></td>
										<td class="tbcellBorder"><html:textarea rows="2" cols="20"
												name="clinicalNotesForm" styleId="causeOfDeath" title="Please enter cause of death"
												property="causeOfDeath"
												onchange="javascript:chkAlphaNumeric('causeOfDeath','Cause of death');func_maxlen('cause of death',this,3000)" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>

									<tr>
										<td colspan="4" align="center">
											<!-- <button type="button" class="but" value="Add/View Death Photo"  onclick="fn_addAttachments()">Add/View Death Photo</button> -->

											<!-- <button type="button" class="but"  value="Add/View Letters"  onclick="fn_addAttachments()">Add/View Letters</button></td> -->
									</tr>

									<c:if test="${ DischargeSumryButFlag!='N' && medcoRole =='Y'}">
										<tr>
											<td colspan="4" align="center">
												<button type="button" class="but" value="Save" id="SaveDisSumryBut"
													onclick="fn_saveSubDischargeSumry('save')">Save</button>
												<c:if test="${ehfDischargeSummarySize eq '1'}">
													<button type="button" class="but" value="Submit" id="SubmitDisSumryBut"
													onclick="fn_saveSubDischargeSumry('submit')">Submit</button>
												</c:if>
													<button type="button" class="but"
													value="Add/View Death Summary Attachments"
													onclick="fn_addAttachments('death')">Add/View
													Attachments</button>
											</td>
										</tr>
									

									<tr>
										<td colspan="4"><font color="red"> Note: 1) Once
												the Discharge date/Death Date is updated in the discharge
												summary, it can not be modified at any point of time.<br>
												2) The Claim can be initiated by the network hospital only
												after 11 days from the date of <font color="blue">Discharge
													update</font> Status. 
										</td>
									</tr>
									</c:if>
								</table>
							</div></td>
					</tr>
				<tr><td><br></td></tr>

				<c:if test="${DischargeSumryPrintFlag=='Y'}">
				<table border="0" width="100%" cellpadding="1" cellspacing="1"
						align="center" >
											<td colspan="4" align="center" >
												
												<button type="button" class="but" id="SaveDisSumryBut"
													value="Print" onclick="fn_printDischargeSumry()">Print Discharge Summary Form</button>
													</td>
													
													
													
						</table>							
													
				</c:if>	
				
					
				<c:if test="${lStrFollowupFlag=='Y'}">
					<table border="0" width="100%" cellpadding="1" cellspacing="1"
						align="center" class="tabBorder">
						<tr>
							<td colspan="4" align="left" class="tbheader"><b>Follow Up</b></td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td align="left">
								<button type="button" class="but" value="followUp"
									onclick="fn_openFollowUp()">Add Follow Up</button></td>
						</tr>
					</table>
				</c:if>
				<c:if test="${lStrCochlearFollowUpFlag=='Y'}">
					<table border="0" width="100%" cellpadding="1" cellspacing="1"
						align="center" class="tabBorder">
						<tr>
							<td colspan="4" align="left" class="tbheader"><b>Cochlear Follow Up</b></td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td align="left">
								<button type="button" class="but" value="cochlearFollowUp"
									onclick="fn_openCochlearFollowUp()">Add Cochlear Follow Up</button></td>
						</tr>
					</table>
				</c:if>
		</table>


		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>



		<input type="hidden" name="caseId" value="${caseId}" />

		<input type="hidden" name="opnotesattach" id="opnotesattach"
			value="${opnotesattach}"></input>
		<input type="hidden" name="aftersurg" id="aftersurg"
			value="${aftersurg}"></input>
		<input type="hidden" name="dthsummary" id="dthsummary"
			value="${dthsummary}"></input>
		<input type="hidden" name="DisSummaryAttach" id="DisSummaryAttach"></input>
		<input type="hidden" name="dischphoto" id="dischphoto" value="0"></input>
		<input type="hidden" name="DeathCertificate" id="DeathCertificate"
			value="${PreauthClinicalNotesAttachCntVO.DEATH_CERTI_CNT}"></input>
		<input type="hidden" name="lStrCurentDtTime" id="lStrCurentDtTime"></input>
		<%-- <html:hidden name="clinicalNotesForm" property="surgStartDt" styleId="surgStartDt" /> --%>
		<html:hidden name="clinicalNotesForm" property="ipRegDate"
			styleId="ipRegDate" />
		<html:hidden name="clinicalNotesForm" property="surgSaveDate"
			styleId="surgSaveDate" />
		<input type="hidden" value="${caseApprovalFlag}"
			name="caseApprovalFlag" />
		<input type="hidden" value="${resMsg}" id="successMsg"
			name="successMsg" />
		<input type="hidden" value="${webExRec}" id="webExRec" name="webExRec" />
		<input type="hidden" value="${videoRecAttach}" id="videoRecAttach"	name="videoRecAttach" />
		<html:hidden name="clinicalNotesForm" property="drugs" styleId="drugs"/>	
		<html:hidden name="clinicalNotesForm" property="teleRegDate" styleId="teleRegDate" />
		<html:hidden name="clinicalNotesForm" property="teleFlag" styleId="teleFlag" />
		<html:hidden name="clinicalNotesForm" property="caseUnits" styleId="caseUnits" />
		<html:hidden name="clinicalNotesForm" property="dentalSurg" styleId="dentalSurg" />
		<html:hidden name="clinicalNotesForm" property="toothedUnits" styleId="toothedUnits" />
		
		
	</html:form>
</body>
</html>

