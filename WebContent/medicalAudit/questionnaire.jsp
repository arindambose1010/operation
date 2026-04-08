 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>
<%-- <%@ include file="/common/includeCalendar.jsp"%> 
<%@ include file="/common/includeScrollbar.jsp"%> --%>


<html>

<head>
<title>Questionnaraire</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<!-- <script src="/Operations/js/jquery.msgBox.js" type="text/javascript"></script> -->
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>

<%@ include file="/medicalAudit/includePatientDetailsMA.jsp"%> 

<style type="text/css">
*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}    
select:focus
{
  outline:#000 dotted 1px; 
} 
radio:focus
{
  outline:#000 dotted 1px; 
}
textarea:focus
{
  outline:#000 dotted 1px; 
}
checkbox:focus
{
  outline:#000 dotted 1px; 
}
</style>
<script type="text/javascript">


var mandatoryIdsarray = new Array(); 
function submitChk() 
{
	
	for(var i=0;i<mandatoryIdsarray.length;i++){
		var ques=mandatoryIdsarray[i].split("~");
	
			if((!document.getElementById(ques[1]+"Yes").checked  && !document.getElementById(ques[1]+"No").checked &&
					!document.getElementById(ques[1]+"NA").checked))
			{ 
				//var fr=partial(focusFieldView,document.getElementById(ques[1]+"Yes"));
				//jqueryAlertMsg("Alert", "Please select whether "+decodeURI(ques[0]),fr);
				alert("Please select whether "+decodeURI(ques[0]));
				focusBox(document.getElementById(ques[1]+"Yes"));
				
			return false;
			}
			if(document.getElementById(ques[1]+"Remarks").value==null|| document.getElementById(ques[1]+"Remarks").value==""){
				
				//var fr=partial(focusFieldView,document.getElementById(ques[1]+"Remarks"));
				//jqueryAlertMsg("Alert","Please enter remarks for "+decodeURI(ques[0]),fr);
				alert("Please enter remarks for "+decodeURI(ques[0]));
				focusBox(document.getElementById(ques[1]+"Remarks"));
				return false;
			}
			
			
	}
	return true;
}
function fn_loadImage()
{
	
	document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()
{   

	document.getElementById('processImagetable').style.display="none";
    
}
function focusFieldView(el)
{

//var x=getOffset( el ).top;
var offset = $(el).offset();
var top = offset.top;

//parent.fn_goToField(x);
top = top+elemJqueryScrollTop;
//$("body").mCustomScrollbar("update");
//$("body").mCustomScrollbar("scrollTo",top);
focusBox(el);
}
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0);  

}
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function remarksValidation(){
	
	
	 var finalRemarks =document.getElementById('finalRemarks').value;
	
	if(finalRemarks==null||finalRemarks=="")
	   {
		 // var fr=partial(focusBox,document.getElementById('finalRemarks'));
		 //jqueryAlertMsg("Alert","Please enter remarks ",fr);
		 
		 alert("Please enter remarks ");
		 focusBox(document.getElementById('finalRemarks'));
		 document.getElementById('finalRemarks').value="";
	   return false;
	   }
	
	return true;
	
}
function validateMaxlength(input,e) { 
	
	 var fieldValue=input.value; 
	 
	 var code;    
	  if (!e) 
	 var e = window.event;   
	   if (e.keyCode) 
	 code = e.keyCode;    
	  else if (e.which)
	  code = e.which;  	
	  
	 if(trim(fieldValue).length>4000) 		
	 { 	
		
	input.value=trim(fieldValue).substr(0,3999); 			
	 //var fr=partial(focusBox, document.getElementById("changeReqRemarks"));
	 focusBox(document.getElementById("changeReqRemarks"));
	 //jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 4000 characters.',fr);
	 alert('Exceeded maximum limits of 4000 characters.');
	 if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40) 				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down 				
	 { 				e.returnValue=true; 				
	 } 			
	
	 else 		
	 { 				
		 e.returnValue=false; 			 
	 } 		
	 } 	
	 }
function validateRemarks(arg) { 
	
	//alert(document.getElementById(arg).value);
	var value = document.getElementById(arg).value;
	
	//alert(value.trim());
	//alert(value);
	var len = value.length; 
	var var_length = value.length;
	var str = /\W/g;
	var space=/\s/g;
	var pattern=/^[A-Z a-z 0-9.,()?&]+$/; 	
	var chars="*|\":<>[]{}&`\';!^_*+@$#%~=";
	var allowedSpl =".,()?/\n";
	
	if (value != ""){ 
	  if(value.charCodeAt(0) == 32) 	{ 	
			// var fr=partial(focusBox, document.getElementById(arg));
			//jqueryAlertMsg("Alert","Remarks cannot start with white space",fr);
			alert("Remarks cannot start with white space ");
			focusBox(document.getElementById(arg));
			document.getElementById(arg).value=""; 	return false;  	} 	
		
		else if(trim(value)=="")
		{
			
			// var fr=partial(focusBox, document.getElementById(arg));
			//jqueryAlertMsg("Alert","Remarks cannot have only white space",fr);
			alert("Remarks cannot have only white space ");
			focusBox(document.getElementById(arg));
		return false;
		}
		else if  (len > 4000) 	{ 	
			//var fr=partial(focusBox, document.getElementById(arg));
			//jqueryAlertMsg("Alert","Remarks cannot be more than 4000 characters",fr);
			alert("Remarks cannot be more than 4000 characters ");
			focusBox(document.getElementById(arg));
			return false;  
			} 
	  var field=document.getElementById(arg);
	  if ( field.value.length > 4000 )
		{
		   field.value = field.value.substring( 0, 4000);
		   alert('Remarks length cannot be more than 4000 characters');
		   focusBox(field);
		   return false;
		 }
	  
	  for (var i = 0; i < var_length; i++) 
	    {
	        if(chars.indexOf(value.charAt(i))!=-1)
	        {
	        	//var fr=partial(focusBox, document.getElementById(arg));
				//jqueryAlertMsg("Alert","Remarks cannot have Special Characters except . , () / & ? ",fr);
				alert("Remarks cannot have Special Characters except . , () / & ? ");
				focusBox(document.getElementById(arg));
				return false;
				break;
	            
	        }
	        else {
	        	
	        	 if(allowedSpl.indexOf(value.charAt(i))!=-1)
	 	        {
	        		 if(i<var_length-1){
	        		 if(allowedSpl.indexOf(value.charAt(i+1))!=-1)
	        			 {	// var fr=partial(focusBox, document.getElementById(arg));
	     				//jqueryAlertMsg("Alert","Remarks cannot have Consecutive Special Characters",fr);
	     				alert("Remarks cannot have Consecutive Special Characters ");
	    				focusBox(document.getElementById(arg));
		        			 break;
		        			 return false;
	        			 }
	        		 }
	 	        }
	        }
	       
	       
	       
	    }

	  document.getElementById(arg).value = document.getElementById(arg).value.replace(/  +/g, ' ');
	
		
	
	}	
	return true;
}
function trim(stringToTrim) 
{
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function saveAuditAnswers(actionType){
	
	
	
	 	var result='';
		var resultRem='';
	
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
			if ((type=="radio" && document.forms[0].elements[i].checked) || (type=="textarea" && (trim(document.forms[0].elements[i].value)!='' && trim(document.forms[0].elements[i].value)!="")))
			{
				if(type=="radio")
				{	
					result = result+document.forms[0].elements[i].name+'~'+document.forms[0].elements[i].value+'@';	
				}
				else if(type=="textarea" && !(document.forms[0].elements[i].name=='finalRemarks'))
				{	
					resultRem=resultRem + document.forms[0].elements[i].name + '~' +document.forms[0].elements[i].value+'@';
					var ret=validateRemarks(document.forms[0].elements[i].id);
					if(ret==false)
						return false;
				}
				
				
			}
		}
	
	 if(actionType=="Submit")
		{
		
		var valid = submitChk();
	//var valid = true;
		if(valid == true)
		{
		
			var remarks=remarksValidation();
			
			if(remarks == true)
			{
				var ret=validateRemarks('finalRemarks');
				if(ret==true)
					{	//var fr=partial(onSuccessSave,result,resultRem,actionType);
						//jqueryConfirmMsg("Confirm","Are you sure you want to Submit?",fr);
						var c=confirm("Are you sure you want to Submit?");
						if(c==true)
							onSuccessSave(result,resultRem,actionType);
					}				
			}
		}
		}
	 else {
		 	if(document.getElementById("finalRemarks").value.length>0)
			{
		 		var ret=validateRemarks('finalRemarks');
				if(ret==true)
			 		{	//var fr=partial(onSuccessSave,result,resultRem,actionType);
						//jqueryConfirmMsg("Confirm","Are you sure you want to Save?",fr);
						var c=confirm("Are you sure you want to Save?");
			 			if(c==true)
					 		onSuccessSave(result,resultRem,actionType);
			 		}	
			}
		 	else
		 		{
			 		var c=confirm("Are you sure you want to Save?");
		 			if(c==true)
				 		onSuccessSave(result,resultRem,actionType);
		 		}
		 
		  }
	
}



function onSuccessSave(result,resultRem,actionType){
	
	fn_loadImage();
	document.getElementById("saveBtn").disabled=true;
	document.getElementById("submitBtn").disabled=true;
	
document.forms[0].action = '/<%=context%>/medicalAudit.do?actionFlag=saveAnswers&ques='+result+'&remarks='+resultRem+'&actionType='+actionType;
document.forms[0].submit();
}
function workFlowRemarksValidation(){
	
	 var wrkFlowRmks =document.getElementById('workFlowRemarks').value;
		
		if(wrkFlowRmks==null||wrkFlowRmks=="")
		   {
			// var fr=partial(focusBox,document.getElementById('workFlowRemarks'));
			 //jqueryAlertMsg("Alert","Please enter remarks ",fr);
			 
			 alert("Please enter remarks ");
			 focusBox(document.getElementById('workFlowRemarks'));
			 document.getElementById('workFlowRemarks').value="";
		   return false;
		   }
		
		return true;
}

function showAlert(){
	
	var showQuestionnaire="${showAlert}";
	var userName="${userName}";
	if(showQuestionnaire == "yes"){		
		//jqueryAlertMsg('Alert', "Already Initiated by "+userName);
		alert("Already Initiated by "+userName);
	}
	else {
		document.getElementById("questionnaire").style.display="block";		
		document.getElementById("imageID").style.display="block";
	}
	//$("body").mCustomScrollbar("update");
	//$("body").mCustomScrollbar("scrollTo",top);
}

function startWorkFlow(){
	
	var remarks = workFlowRemarksValidation();
	if(remarks == true)
	{
		var ret=validateRemarks('workFlowRemarks');
		if(ret==true)
	 		{
				var c=confirm("Are you sure you want to Submit?");
				if(c==true)
					{
						document.getElementById("wrkFlwBtn").disabled=true;
			 			document.forms[0].action = '/<%=context%>/medicalAudit.do?actionFlag=startMedicalAuditWorkFlow';
			 			document.forms[0].submit();
					}
	 		}
}
}

function showMessage(){
	
	 var status = "${showStatus}";
	 var cmaStatus = "${cmaDirectSubmit}"; 
	 
		if(status == "workFlowSubmitted") {
			
			// var fr=partial(goBack);
			//jqueryAlertMsg('Alert', " Submitted Successfully",fr);
			alert("Submitted Successfully");
			if(document.getElementById("saveBtn")!=null &&
					document.getElementById("submitBtn")!=null)
				{
					/* document.getElementById("saveBtn").disabled=false;
					document.getElementById("submitBtn").disabled=false; */

				}
			if(cmaStatus=="Y")
				goToInitiateMedAudit();
			else
				goBack();
		}
		if(status == "auditInitiated") {
			
			//var fr=partial(goToInitiateMedAudit);
			//jqueryAlertMsg('Alert', " Submitted Successfully",fr);
			alert("Submitted Successfully");
			if(document.getElementById("saveBtn")!=null &&
					document.getElementById("submitBtn")!=null)
				{
					/* document.getElementById("saveBtn").disabled=false;
					document.getElementById("submitBtn").disabled=false; */
				}
			goToInitiateMedAudit();
		}
		if(status == "saved") {
			
			//var fr=partial(goToInitiateMedAudit);
			//jqueryAlertMsg('Alert', " Submitted Successfully",fr);
			alert("Medical Audit Questionnaires Saved Sucessfully");
			if(document.getElementById("saveBtn")!=null &&
					document.getElementById("submitBtn")!=null)
				{
					
					document.getElementById("saveBtn").disabled=false;
					document.getElementById("submitBtn").disabled=false;
				}
			//goToInitiateMedAudit();
		}
		
		
	
}

function goToInitiateMedAudit(){
	
	parent.parent.parent.document.getElementById("middleFrame").src = '/<%=context%>/medicalAudit.do?actionFlag=getSampleCasesForAudit&backFlag=yes';
	 //document.forms[0].submit();
	
}
function goBack(){
	
	parent.parent.parent.document.getElementById("middleFrame").src = '/<%=context%>/medicalAudit.do?actionFlag=getMAworklist&backFlag=yes';
	 //document.forms[0].submit();
}

function goToBegin(){
	
	 var goBackTo = "${backTo}"; 
	
	 if(goBackTo == "initiateMedicalAudit") {
		 
		 goToInitiateMedAudit();
	 }
	if(goBackTo == "workList") {
		 
		goBack();
	 }
	if(goBackTo == "auditCases") {
		 
		auditedCases();
	 }
	
}
function auditedCases(){
	
	parent.parent.parent.document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=yes';
	
}
</script>
<body onload="fn_removeLoadingImage();showAlert();showMessage();">
<form name = "medicalAuditForm" method="post" action="/medicalAudit.do" enctype="multipart/form-data">
 
<div id="imageID"  > 
<img id="patDtlsImage" src="images/patient_dtls.jpg"  style=cursor:hand; title="Click to View Patient Details" alt="Patient Details" align="top" onclick="javascript:fn_getPatDetails()" ></img>
<div id="popupRaiseComplaint" style="position: absolute; width: 825px; height:250px; overflow:hidden;display:none" >
   <a id="popupRaiseCloseComplaint" title="close">X</a>
   <!-- <iframe  id="complaintFrame" width="100%" height="100%" frameborder="no" scrolling="no" > </iframe> -->
   <iframe  id="complaintFrame" width="100%" height="250px" frameborder="no" scrolling="yes" > </iframe>
   </div> 
</div>
<table id="questionnaire" style="display:none" width="100%"> 
<tr>
<td>
<table width="920" style="width:98%;margin:0 auto;" >
 <tr><td colspan="6">
 <table class="tbheader"><tr>
 <td><b>Medical Audit Questionnaire</b></td>
 <td colspan="1" id="menuSlide" width="5%">  
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:goToBegin()" ></img>
</td>
</tr>
</table>
 </td>
 </tr>

<tr>
<c:set var="temp" value="" />
<c:forEach items="${questionList}" var="attMap" >
<script>
			mandatoryIdsarray.push(encodeURI('${attMap.VALUE}')+'~'+'${attMap.ID}');
			</script>
			<c:if test="${attMap.LVL ne temp}"> 
			
			<tr>	
				<td colspan="5" class="tbheader1" ><center><b>${attMap.LVL}</b></center></td>
				<td colspan="1" class="tbheader1" ><center><b>Remarks</b></center></td>		
			</tr>		
			</c:if>
			 <tr>
				<td class="labelheading1 tbcellCss" width="7%">
					${attMap.ID}&nbsp;
				</td>
				<td class="labelheading1 tbcellCss">
				 
					${attMap.VALUE}<br>
				</td>
			
				<c:set var="tempVal" value="${attMap.CONST}"/>
				<td class="labelheading1 tbcellCss" width="7%" style="text-align:center;">
				<input type="hidden" name="questionDesc" value="${attMap.VALUE}"> 
						<input type="radio" id="${attMap.ID}Yes"  name="${attMap.ID}" value="Yes"  />Yes
					</td>
					<td class="labelheading1 tbcellCss" width="7%" style="text-align:center;">
						<input type="radio" id="${attMap.ID}No" name="${attMap.ID}"  value="No" /> No
					</td>
					<td class="labelheading1 tbcellCss"  width="7%" style="text-align:center;">
						<input type="radio" id="${attMap.ID}NA" name="${attMap.ID}"  value="NA" /> NA
					</td>
					<c:if test="${tempVal !=null && tempVal ne ''}">
					<script>
						document.getElementById('${attMap.ID}'+'${tempVal}').checked=true;
					</script>
					</c:if>
					
				<td class="labelheading1 tbcellCss" >
					<textarea id="${attMap.ID}Remarks" name="${attMap.ID}"  rows="2" cols="30"  onchange="validateRemarks('${attMap.ID}Remarks');" onkeypress="validateMaxlength(this,event);" >${attMap.REMARKS}</textarea>
				</td>
				
			</tr> 
	<c:set var="temp" value="${attMap.LVL}" />
	
</c:forEach>
</tr>
</table>
<table style="${showBtn}"   style="width:98%;margin:0 auto;">
<tr>
<td class="labelheading1 tbcellCss">Final Remarks</td>
<td>
<html:textarea  name="medicalAuditForm" styleId="finalRemarks" property="finalRemarks" rows="3" cols="90"  onchange="validateRemarks('finalRemarks');" onkeypress="validateMaxlength(this,event);" title="Remarks" style="height: 5em; width: 62em;"  ></html:textarea>
</td>
</tr>
</table>

	<table align="center" border="0" style="padding-top:0px;margin:0px auto;">
		<tr>
<td align="center">
 <button class="but"   style="${showBtn}" id="saveBtn" name="saveBtn" tabindex="0" type="button" onClick="saveAuditAnswers('Save')">Save</button>
 <td> <button class="but"  style="${showBtn}" id="submitBtn" name="submitBtn" tabindex="0" type="button" onClick="saveAuditAnswers('Submit')"  title="Submit" >Submit</button>
</td>
</tr>
</table>

 <table style="${showRemarks}" style="width:90%;margin:0px auto;">
<tr>
<td>
<logic:present name="medicalAuditForm"  property="remarksList">
<bean:size id="size" name="medicalAuditForm" property="remarksList"/>
<logic:greaterThan name="size" value="0">
   <table class="tbheader" style="width:97%;margin:0px auto;">
            <tr >
            <%-- <td >
<img id="menuImage" src="/<%=context%>/images/updownArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:expandOrCollapse()" ></img>
</td> --%>
<td  align="center" width="95%" ><b>Previous Remarks</b>
</td>
</tr>
</table>
      
<table style="width:98%;margin:0px auto;" >
<tr>
<td>
 <div id="remarksDiv"  style="height:6em;overflow:auto;width:68em" > 
<!-- <div id="remarksDiv" style="width:880px;height:10em;overflow:auto;padding-left:2em;"> -->
      
<table style="width:98%;margin:0px auto;" >

<tr>
	
<td class="tbcellBorder"  style="width:3em;" align="left" ><b>SNo</b></td> 
<td class="tbcellBorder"  style="width:80em;" align="left" ><b>Role</b></td> 
<td  class="tbcellBorder"  style="width:100em;" align="left"><b>Name</b></td> 
<td class="tbcellBorder"  style="width:280em;" align="left" ><b>Remarks</b></td>
<td  class="tbcellBorder"  style="width:10em;" align="left"><b>Date</b></td> 
 
</tr>
	<logic:iterate id="data" name="medicalAuditForm" property="remarksList" indexId="index">
	
<c:choose>
     <c:when test='${(index+1)%2 eq 0}'>
      <c:set var="rowColor" value="text_class"scope="page"/>
    </c:when>
    <c:otherwise>
      <c:set var="rowColor" value="text_class1" scope="page"/>
    </c:otherwise>
  </c:choose>


	<tr class="${rowColor}">	
	
	<td class="tbcellBorder" align="left" style="width:3em;" >${index + 1} </td>
	<td  class="tbcellBorder" align="left" style="width:80em;" ><bean:write name="data" property="role"/></td>
	<td class="tbcellBorder" align="left" style="width:100em;" ><bean:write name="data" property="updatedBy"/></td>
	<td class="tbcellBorder" align="left" style="width:280em;" ><bean:write name="data" property="prevRemarks"  />
	<td  class="tbcellBorder" align="left" style="width:10em;" ><bean:write name="data" property="remarksDt"/></td>
	</td>
	</tr>
	
</logic:iterate>
</table>

 </div> 
</td>
</tr>

</table>

</logic:greaterThan>

  <logic:equal name="size" value="0">
 <table>
 <tr>
 <td>No Records Found</td>
 </tr>
 
 </table>
 </logic:equal>  
</logic:present>
</td>
</tr>
</table>

<table  style="${showRemarksTextArea}"  style="width:98%;margin:0 auto;"  align="center" border="0"  >
<tr><td  >	
			Remarks on <script language="JavaScript">
			HoldDate=new Date();
			document.write(HoldDate.getDate() + "/" + (HoldDate.getMonth()+1) + "/" + HoldDate.getFullYear());
        </script> :
			<html:textarea  name="medicalAuditForm" styleId="workFlowRemarks" property="workFlowRemarks" title="Remarks" rows="5" cols="120" onchange="validateRemarks('workFlowRemarks');" onkeypress="validateMaxlength(this,event);"  style="height: 60px; width: 820px"  ></html:textarea>
			</td></tr>
			</table>
			


<table align="center" border="0" style="padding-top:0px;margin:0px auto;">
		<tr>
<td align="center">
 <button class="but"   style="${showWrkFlwSubBtn}" id="wrkFlwBtn" name="wrkFlwBtn" tabindex="0" type="button" onClick="startWorkFlow()">Submit</button>
 
</td>
</tr>
</table>
</td>
</tr>
 </table> 		
<html:hidden  name="medicalAuditForm"  property="caseId" styleId="caseId" />
<script>
var browserName=navigator.appName; 

if(browserName=="Microsoft Internet Explorer")
	{
	 
	 
	document.getElementById("finalRemarks").attachEvent("onpaste",pasteIntercept);
	document.getElementById("workFlowRemarks").attachEvent("onpaste",pasteDoctorRemarks);
	}
else if(browserName == "Netscape")
	{
	document.getElementById("finalRemarks").addEventListener("paste", pasteIntercept, false);
	document.getElementById("workFlowRemarks").addEventListener("paste", pasteIntercept, false);
	}
function pasteIntercept(evt)
{  
	 
var input=document.getElementById('finalRemarks');
maxLengthPaste(input,4000); 
}

function pasteDoctorRemarks(evt){
	 
	 var input=document.getElementById('workFlowRemarks');
	 maxLengthPaste(input,4000); 
}
function maxLengthPaste(field,maxChars)
{
	
     event.returnValue=false;
     if(window.clipboardData)
   	  {
     		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
			{
     		 //var fr=partial(focusBox, field);
     			//jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 4000",fr);
     			alert("Characters should not exceed 4000");
     			focusBox(field);
       	return false;
       	}
     		event.returnValue=true;
   	  }
     if (event.clipboardData) 
     {
   	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
   		{
   		 // var fr=partial(focusBox, field);
   		//jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 4000",fr);
   		alert("Characters should not exceed 4000");
			focusBox(field);
       	return false;
       	}
     		event.returnValue=true;
     }
}
if("${disabled}"=="yes"){
for (var i=0; i<document.forms[0].elements.length; i++)
{	
	var type = document.forms[0].elements[i].type;
	if ((type=="radio")|| type=="textarea"){
		
		if(!(document.forms[0].elements[i].name=='workFlowRemarks')) {
		document.forms[0].elements[i].disabled = "disabled";
		}
	}
	}
}
</script>
<%--  <div id="processImagetable" style="top:95%;z-index:90;position:absolute;left:40%">
        <table border="0" align="center" width="100%" style="height:400" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="/<%=context%>/images/Progress.gif" width="100" height="100" border="0" tabindex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div> --%>
         <div id="processImagetable" style="top:40%;z-index:50;position:absolute;left:45%;display:none;">
        <table border="0" align="center" width="100%" style="height:100" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="/<%=context%>/images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
</form>

</body>
</html>