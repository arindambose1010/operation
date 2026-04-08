<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	 <%@ include file="/common/include.jsp"%> 

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  

<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
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
<style>
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
Inherited from table.medicalDetailsTable
.medicalDetailsTable {
font-weight: bold;
}

.form-control {
display: block;
width: 100%;
height: 34px;
padding: 6px 12px;
font-size: 14px;
line-height: 1.42857143;
color: #555;
background-color: #fff;
background-image: none;
border: 1px solid #ccc;
border-radius: 4px;
-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}

</style>
<script type="text/javascript">


var heightVar = null;
var weightVar = null;

$(function() { 
    $( "#expiryDt" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
      		showOn: "both", 
            buttonImage: "images/calend.gif",
            buttonText: "Calendar",
            buttonImageOnly: true,
            minDate: 0,
            maxDate: "+5Y"
        }); 
}); 
function browserDetect()
{
	 var objAgent = navigator.userAgent; 
	 var objbrowserName  = navigator.appName;
	 var objOffsetVersion;
	if ((objOffsetVersion=objAgent.indexOf("Chrome"))!=-1) { 
		 objbrowserName = "Chrome"; 
	}
	else if ((objOffsetVersion=objAgent.indexOf("MSIE"))!=-1) { 
		objbrowserName = "Microsoft Internet Explorer"; 
	}
	else if ((objOffsetVersion=objAgent.indexOf("Firefox"))!=-1) { 
		objbrowserName = "Firefox"; 
	}
	return objbrowserName;
}
function checkAlphaNumericCodes(event) {
	browserName=browserDetect();
	//if(navigator.appName=="Microsoft Internet Explorer" || navigator.appVersion=='5.0 (Windows NT 5.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19')
	if(browserName=="Microsoft Internet Explorer" || browserName=="Chrome")
	{
	var charCode=event.keyCode;
	if ((charCode<65 || charCode>90)&&(charCode<97 || charCode>122)&&(charCode<48 || charCode>57)&&(charCode!=13 && charCode!=32))
			    return false; 	
					return true;  
	}
	else if(browserName=="Firefox")
	{
		var inputValue = String.fromCharCode(event.keyCode || event.charCode)
		var regExpr = /^[0-9a-zA-Z\s]+$/;
		if(event.keyCode != 0) {
			if(event.keyCode == 38 || event.keyCode == 40 || event.keyCode == 37 || event.keyCode == 39 ||
				event.keyCode == 8 || event.keyCode == 13 || event.keyCode == 46 || event.keyCode == 36 ||
				event.keyCode == 35 || event.keyCode == 33 || event.keyCode == 34 || event.keyCode == 45 ||
				event.keyCode == 9) {
			} else {
				return false;
			}
		} else if(event.charCode != 0){
			if(!inputValue.match(regExpr)) {
				return false;
			}
		}
		return true;
	}
}
var medOrSur='';
var genInvestAttachId=0;
var updGenInvestAttachId=0;
var ipInvestAttachId=0;
var therapyAttachId=0;
var a=0;
var genInventCount=0;
var updateGenInvestCount=0;
var updateGenInvestLst=new Array();
var genInventList=new Array();
var genOldList=new Array();
var catCount=0;
var spec=new Array();
var specOld=new Array();
var procList=new Array();
var drugCount=0;
var drugs=new Array();
var existDrugsArr=new Array();
var symptomCount=0;
var symptoms=new Array();
var genInvestRemove='';
var specRemove='';
var otherDocDetails=new Array();

function checkBrowser(input)
{
     if(navigator.appName == "Netscape")
    {
		var sizeCheck=checkFileSizeFF(input);
		var fileCheck=checkFileNameMatch(input);
		if(sizeCheck==false || fileCheck==false)
		input.value='';
    }
     if(navigator.appName == "Microsoft Internet Explorer")
    {  
		var fieldName=input.name;
		var fieldId=input.id;  	
		var sizeCheck=checkFileSizeIE(input);
		var fileCheck=checkFileNameMatch(input);
		if(sizeCheck==false || fileCheck==false)
		{
			var oRow = input.parentNode.parentNode; 
			var filecell;
			if(fieldName.charAt(0)=='g' || fieldName.charAt(0)=='u')
			{
				filecell=oRow.cells[2];
			}
			else if(fieldName.charAt(0)=='a')
			{ 
				filecell=oRow.cells[6];
			}
			filecell.innerHTML='<input type="file"  id='+fieldId+' name='+fieldName+' onchange="checkBrowser(this)"/>';
		}
    }
}
function checkFileSizeFF(input)
{
	var filesize=input.files[0].size;
	if((filesize/(1024))>200)
	{
		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
		return false;
	}
}
function checkFileSizeIE(input)
{
	try
	{
 	var myFSO = new ActiveXObject("Scripting.FileSystemObject");
 	var filepath = input.value;
 	var thefile = myFSO.getFile(filepath);
 	var filesize = thefile.size/(1024);
 	if(filesize>200)
	{
 		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
		return false;
	}
	}
	catch(e)
	{
		jqueryInfoMsg('ActiveX Control Enable',"Please Enable ActiveX control.");
		jqueryInfoMsg('Steps To Enable ActiveX Control',"Go To-->Tools-->Internet Options-->Security-->Trusted Sites-->Click on Sites Button-->Add site url to list-->close-->Click on Custom level Button-->Initialize and script ActiveX controls not marked as safe for scripting---Enable");
		return false;
	}
}
function checkFileNameMatch(input)
{
	var curFile=input.value;
	//var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
	
	var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')));
	var fullFileName=curFile.substring(curFile.lastIndexOf('\\')+1);
	var fileName1=curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
	if(rtVal ==0)   
		{
		jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
		return false;
		}
	if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
		{
		jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
		return false;
		}
	
	if( fileName1.match(/[\-\_]{2}/i))
	{
		jqueryErrorMsg('File Name Validation',"File name should not should not contain consecutive special characters");
		return false;
	}
	var extn=curFile.substring(curFile.lastIndexOf('.')+1).toLowerCase();
	if(extn=='jpg' || extn=='jpeg' || extn=='png' || extn=='bmp')  
		{
		var status=true;
		}
	else
		{
		jqueryErrorMsg('File Type Validation',"Can upload jpg,jpeg,png,bmp extension files only");
		return false;
		}
	var matchCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
       	   var fileName = val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
       	   var curFileName = curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
		   if(fileName==curFileName)
		   matchCount++;
		   if(matchCount>1)
			{
				jqueryErrorMsg('File Name Validation',"File with this filename already exists.Cannot upload");
				return false;
			}
       }
    }
	
}
function maxLengthPress(field,maxChars,e)
{
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(field.value.length >= maxChars) 
	{
		if(code==8 || code==9 || code==46 || code==37 || code==38 || code==39 || code==40)
			//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
			{
				e.returnValue=true;
				return true;
			}
		else
			{
				e.returnValue=false;
	        	return false;
		 	}
         }
}
//For validating maxlength onpaste event--For textarea fields
function maxLengthPaste(field,maxChars)
{
      event.returnValue=false;
      if(window.clipboardData)
    	  {
      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
			{
      			jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
    	  }
      if (event.clipboardData) 
      {
    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
    		{
    		jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
      }
}
function pasteIntercept(evt)
 {  
var input=document.getElementById('presentHistory');
maxLengthPaste(input,3000); 
}
function  pasteInterceptRemarks(evt)
{
	var input=document.getElementById('remarks');
	maxLengthPaste(input,3000); 
}
function  pasteInterceptDocRemarks(evt)
{
	var input=document.getElementById('treatingDocRmks');
	maxLengthPaste(input,3000); 
}
function  pasteInterceptOpRemarks(evt)
{
	var input=document.getElementById('opRemarks');
	maxLengthPaste(input,3000); 
}
function fn_saveDetails(){
	var patId=document.getElementById("patientNo").value;

	document.getElementById("drugs").value=drugs;
	//alert(document.getElementById("drugs").value);
	//Mandatory Check validation For Personal History and its sublist
	var personalHistory=document.forms[0].personalHistory;
	var personalCount=0;
	var personalSubCount=0;
	var personalHistVal="";
	var genTestsCount=0;
	var updTestsCount=0;
	var ipTestsCount=0;
	var lErrorMsg='';
	var lField='';
	
	
	//Mandatory Check validation For Examination Findings and its sublist
	
	
	if(genTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"General investigation attachments are mandatory";
	    } 
  	}
	if(updTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		} 	
	
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		var fr=partial(focusBox,document.getElementById(lField));
		//var fr=partial(focusFieldView,lField);
		//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
	   alert(lErrorMsg);
	    	focusBox(document.getElementById(lField));
	    return;
	 }
	else
	{
	var saveFlag="Yes";
	var checkType="Save";
	var fr=partial(registerCase,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save?',fr);
	}}
function fn_savePatientDetails(checkType)
{
	
	document.getElementById("drugs").value=drugs;
	var patId=document.getElementById("patientNo").value;
	var lErrorMsg='';
	var lField='';
	var genTestsCount=0;
	var ipTestsCount=0;
	var updTestsCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
           if(val==null || val=="")
           	{
        	 if(document.forms[0].elements[temp].name.charAt(0)=='g')
        	   genTestsCount=genTestsCount+1;
			else if(document.forms[0].elements[temp].name.charAt(0)=='u')
        		 updTestsCount=updTestsCount+1;
        	 else if(document.forms[0].elements[temp].name.charAt(0)=='a')
        		 ipTestsCount=ipTestsCount+1;
			if(lField=='')
				lField=''+document.forms[0].elements[temp].id+'';
           	}
           else
			{
				var rtVal=chkSpecailChars(val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.')));
				var fullFileName=val.substring(val.lastIndexOf('\\')+1);
				var fileName1=val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
				if(rtVal ==0)   
				{
					jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
					return false;
				}
				if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
				{
					jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
					return false;
				}
				 var sub=val.substring(val.lastIndexOf('.')+1);
				 if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
				  {
					 if(lErrorMsg=='')
		            	{
							lErrorMsg=lErrorMsg+"Cannot upload exe,rar,war files ";
		       	        }
				  } 
				}
			}
         }
	if(checkType=='submit')
	{
		if(genTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		}
		if(updTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		} 
	}
	
	
/* if(genInventList.length==0 && genOldList.length==0 ){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select General Investigations ";
		}
        if(lField=='')
        lField='genInvestigations';   
}*/
//Check to enable dtrs form

 if(checkType=='DTRS')
{
	if(lErrorMsg=='')
	{
		return true;
	}
	else
	{
		return false;
	}
}
else if(checkType=='submit')
{
	//Mandatory Check for Prescription Details
	if(drugCount==0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Add Drug (Drug Name,Dosage,Medication Period Details) ";
			}
	     if(lField=='')
	    	 lField='addDrug';
		}

	//Mandatory Check For Chronic OP Therapy Details
	
	if(document.getElementById("opPkgName").value=="-1")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Therapy OP Package Name ";
		}
        if(lField=='')
        lField='opPkgName'; 
	}
	if(document.getElementById("opIcdName").value=="-1")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Therapy ICD Name ";
		}
        if(lField=='')
        lField='opIcdName'; 
	
}
if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
{
	var fr=partial(focusBox,document.getElementById(lField));
   alert(lErrorMsg);
    	focusBox(document.getElementById(lField));
    return;
 }
else
{
	var saveFlag="Submit";
	var fr=partial(registerCase,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to submit chronic op clinical notes?',fr);
}
}
}  
function validateMedicationPeriod()
{
var mediPeriod=document.forms[0].medicatPeriod.value;
if(mediPeriod>90)
{
	alert("Medication Period cannot be greater than 90 Days");
//document.forms[0].medicatPeriod.value='';
return false;
}
}
function registerCase(patId,saveFlag,checkType)
{
    var selInvData='';
	 var selGenInvData='';
	 var updateGenInvData='';
	 var selectedList1  = document.getElementById('addTests');  
	 //var selectedList2  = document.getElementById('investigationSel');

	 for(var i=0;i<genInventList.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var investInfo = genInventList[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
          if((selGenInvData!=null || selGenInvData!='') && selGenInvData.length>0)
          {
       	   selGenInvData=selGenInvData+'~';
          }          
          selGenInvData=selGenInvData+ltext+'$'+lId;  
    	}
 	for(var i=0;i<updateGenInvestLst.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var investInfo = updateGenInvestLst[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
          if((updateGenInvData!=null || updateGenInvData!='') && updateGenInvData.length>0)
          {
       	   updateGenInvData=updateGenInvData+'~';
          }          
          updateGenInvData=updateGenInvData+ltext+'$'+lId;  
    	}
		document.forms[0].investigationsSel.value=selInvData;
		$('button').prop('disabled', true);
   		document.getElementById("Submit").disabled=true;
		document.getElementById("Reset").disabled=true;

		
		var url="./chronicAction.do?actionFlag=saveChronicInstallment&chronicId="+patId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType+"&genInvestRemove="+genInvestRemove+"&specRemove="+specRemove;
	
		/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
		document.forms[0].action=url;
		document.forms[0].method="post";
		document.forms[0].submit();
}

function fn_resetAll(){
	var fr;var msg='Do you want to reset?';
	fr=partial(resetMedicalDetails);
	if(genOldList.length>0 && specOld.length>0)
		msg='Previously Added Investigation and Speciality Cannot be reset.Please Delete them Manually.\nDo you want to reset?';
	else if	(genOldList.length>0)
		msg='Previously Added Investigation Cannot be reset.Please Delete them Manually.\nDo you want to reset?';	
	else if	(specOld.length>0)
		msg='Previously Added Investigation Cannot be reset.Please Delete them Manually.\nDo you want to reset?';			
	 jqueryConfirmMsg('Case Details Reset Confirmation',msg,fr);
}
function resetMedicalDetails()
{
	
	document.getElementById("drugName").value ="-1";
	document.getElementById("asriDrugCode").value="";

	document.getElementById("dosage").value="-1";
	document.getElementById("medicatPeriod").value="";

	document.getElementById("genBlockInvestName").value="-1";
	document.getElementById("genInvestigations").value="-1";
	
	
	genInventCount=0;	
	var genTestTable = document.getElementById("genTestTable");
	for(var count = genTestTable.rows.length-1 ; count>0; count--)
		{
		genTestTable.deleteRow(count);
		}
	document.getElementById("genTestTable").style.display="none";
	genInventList=new Array();
	document.getElementById("testsCount").value=0;	
	
	drugCount=0;
	var drugsTable=document.getElementById("drugsTable");
	for(var count=drugsTable.rows.length-1; count>0;count--)
		{
		alert(count);
		drugsTable.deleteRow(count);
		}
	document.getElementById("drugsTable").style.display="none";
	drugs=new Array();
	document.getElementById("drugs").value=0;

	
}

function getSubListHistory(input,button)
{	
	var parntCode=input.value;
	var prop = document.getElementById(parntCode+"p").name;
	if(input.checked)
	{
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
		   jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	   }   
	   url = "./patientDetails.do?actionFlag=getPersonalHistory&callType=Ajax&parntCode="+parntCode;    
	   xmlhttp.onreadystatechange=function()
	   {
	       if(xmlhttp.readyState==4)
	       {
          		var resultArray=xmlhttp.responseText;
	    	    if(resultArray.trim()=="SessionExpired*"){
	    	    	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	           else
	           {
	           		resultArray = resultArray.replace("[","");
	           		resultArray = resultArray.replace("]*","");
	           		var pHistoryList = resultArray.split(","); 
	           		if(pHistoryList.length>0)
	           		{
	        	   		var phistory="";
	               		for(var i = 0; i<pHistoryList.length;i++)
	               		{	
	                    	var arr=pHistoryList[i].split("~");                     
	                    	if(arr[1]!=null && arr[0]!=null)
	                    	{
	                        	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                        	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                        	
                                if(button!='reset' && '${PatientOpList.lstPerHis}'!=null && '${PatientOpList.lstPerHis}'.indexOf(val2,0)!=-1)	                                         
		                    	{
    		                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"' checked='checked'/>"+""+val1;
                                 }
		                    	else
		                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"'/>"+""+val1;	
			             	}
	               		}
	               		document.getElementById(parntCode).innerHTML=phistory+"<span id='"+prop+"Td'></span>";
	            	}
	         	}
	       }			
	   }
		xmlhttp.open("Post",url,false);
		xmlhttp.send(null);
	}
	else
		{
		document.getElementById(parntCode).innerHTML="";
		}
		 //parent.fn_resizePage();
}


function fn_loadProcessImage()
{
	document.getElementById('processImagetable').style.display="";
	setTimeout(function()
	{
		document.getElementById('processImagetable').style.display="none";
	},4000)
	document.forms[0].patientType[2].checked=true;
	document.getElementById('patientType').value='ChronicOP';
}
function fn_getChronicOp()
{	
	if(parent.ipRefershFlag != null && parent.ipRefershFlag !='' && parent.ipRefershFlag=='Y')
		{
		return;
		}
	else
		{
		var chronicId=document.getElementById("patientNo").value;
	
   var url="/Operations/chronicAction.do?actionFlag=casePrintForm&chronicId="+chronicId;   
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();
		}
}

function fn_prescription()
{
    var chronicId="${chronicId}";
	window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&prescription=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
}


</script>
<script>
$(document).ready(function() {
	
	var test = "${viewType}";
	
if((test=="disable"))

{
	
	$(':input','#middleFrame_content').attr("disabled",true);
	$(":button").hide();
}
});

</script>
<c:if test="${inst ne 'Y' }">
<script>
var msg="${msg}";
alert(msg);
</script>
</c:if>
<c:if test="${inst eq 'Y' }">
<script>
alert("Chronic Op installment initiated successfully");
var chronicId="${chronicId}";

//var chronicNo=document.getElementById("chronicNo").value;
refreshParentPage();
window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&DTRS=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');	


function refreshParentPage()
{
	parent.parent.parent.fn_chronicOPCasesView();	
}

</script>
</c:if>
</head>
<body onload="fn_loadProcessImage()">
<div id="processImagetable"
		style="top: 50%; width: 100%; position: absolute; z-index: 60;">
	<table border="0" align="center" width="100%">
		<tr>
			<td>
			<div id="processImage" align="center"><img
				src="images/Progress.gif" width="100" height="100" border="0"
				tabindex="3"></img></div>
			</td>
		</tr>
	</table>
	</div>
	
<div id="middleFrame_content">

<form action="/chronicAction.do" method="post" enctype="multipart/form-data">
<br>

<table width="70%" style="margin:0 auto" >
<tr><td colspan="4">
<table width="80%" class="tbheader" style="width: 810px;">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;Clinical Notes</b></td></tr>
</table>
</td></tr>
<!--chronic op therapies block-->
<tr><td  id="ChronicOPTherapy" colspan="4">
<table width="70%">
<td width="25%" >
<legend class="legendStyle"><b><fmt:message key="EHF.Therapy"/></b> </legend></td>

<tr>

<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.OpPkgName"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.OpPkgCode"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDName"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCode"/></b> <font color="red">*</font></td>
</tr>
<tr>

<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="opPkgName" styleId="opPkgName" style="width:14em;" title="Select OP Package Name" onchange="getChronicIcdList();getChronicInvestList();getChronicDrugsList();" onmouseover="addTooltip('opPkgName')" disabled="true">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="opCategoryList" labelProperty="VALUE"/>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="opPkgCode" styleId="opPkgCode" title="OP Package Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)"  readonly="true"/></td>

<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="opIcdName" styleId="opIcdName" style="width:14em;" title="Select ICD Name" onchange="getOpIcdCode()" onmouseover="addTooltip('opIcdName')"  >
		
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="opIcdCode" styleId="opIcdCode" title="ICD Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td colspan="2">&nbsp;</td>
</tr>


<!--investigations block-->


<tr><td colspan="4"><b><fmt:message key="EHF.GenInvestigations"/></b> <font color="red">&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4"><table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="25%"><b>Investigation Block Name</b></td>
<td class="labelheading1 tbcellCss" width="25%"><b>Investigation Name</b></td>
<td width="50%">&nbsp;</td></tr>
<tr>
<td class="tbcellBorder">
<html:select name="chronicOpForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 14em;" onmouseover="addTooltip('genBlockInvestName')" onchange="getChronicGenInvestigation();">
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
       
    </html:select></td>
    <td class="tbcellBorder"><html:select name="chronicOpForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 14em;"  onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
<td>
<button type="button" class="btn btn-primary"   onclick="confirmPatientTypeReset()";>Add Tests</button>

</tr></table></td></tr>

</table>



 <table  width="70%"  id="genTestTableID" style="display:none" border="1">
   <tr><td colspan="4" class="labelheading1 tbcellCss">Previously Added Investigation</td></tr>
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr>
        <logic:present name="chronicOpForm" property="genInvList">
        <bean:size  id="genInvSize" name="chronicOpForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
		<%int j = 1;%>
		<script>var rowNo=2;</script>
        <logic:iterate id="gnInvlst" name="chronicOpForm" property="genInvList" indexId="sno" >
        <tr>  
      	<!-- <td width="10%"><%=j++ %></td>  -->
      	<td width="30%" ><bean:write name="gnInvlst" property="ACTION" /></td>       
        <td width="25%"><bean:write name="gnInvlst" property="VALUE" /></td> 
		<logic:empty name="gnInvlst" property="LVL">
		<script>
			var updateGenInvest="<bean:write name="gnInvlst" property="ACTION" />~<bean:write name="gnInvlst" property="VALUE" />~<bean:write name="gnInvlst" property="ID" />";
			updateGenInvestLst[updateGenInvestCount]=updateGenInvest;
			updateGenInvestCount++;
			var investTableId=document.getElementById('genTestTableID');   
			var newRow=investTableId.rows[rowNo];
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="25%"><input type="file"  id=<bean:write name="gnInvlst" property="ID" /> name="updateGenAttach['+updGenInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
			updGenInvestAttachId++;
		</script>
		</logic:empty>
		<logic:notEmpty  name="gnInvlst" property="LVL">
       	<td width="25%"><a href="#" onclick="javascript:fn_openAtachment('<bean:write name="gnInvlst" property="LVL" />','<bean:write name="gnInvlst" property="LVL" />');">View</a></td>
		</logic:notEmpty>
		<script>rowNo++;</script>
		
       	<td width="20%">
       	<button type="button" class="btn btn-primary"    id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:confirmRemoveGenInvest(this,'geninvestigations','<bean:write name="gnInvlst" property="ID" />');">Delete</button>
       	
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
   </table>
</td></tr>
<tr><td colspan="4" >
  <table  width="70%"  id="genTestTable" style="display:none" border="1">
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr> 
  </table>
</td></tr>

<!--prescription  block-->
<tr>
<td  id="ChronicOPPrescription" >
<table width="70%" border="0">
<tr>

<td width="25%" >
<legend class="legendStyle"><b><fmt:message key="EHF.Prescription"/></b> </legend></td>
</tr>
<tr><td colspan="4" id="existDrugsHead" style="display:none" class="labelheading1 tbcellCss"><font size="2px">Existing Drugs</font></td></tr>
<tr><td>
<table  width="90%"  id="existDrugs" style="display:none" border="1">
      <tr>  
      	<td width="5%"><fmt:message key="EHF.SNO"/></td>        
        <td width="15%"><fmt:message key="EHF.DrugTypeName"/></td>   
       	<td width="15%"><fmt:message key="EHF.DrugSubTypeName"/></td>
        <td width="15%"><fmt:message key="EHF.DrugName"/></td>
        <td width="15%"><fmt:message key="EHF.Route"/></td>
        <td width="10%"><fmt:message key="EHF.Strength"/></td>
        <td width="10%"><fmt:message key="EHF.Dosage"/></td>
        <td width="10%"><fmt:message key="EHF.MedicationPeriod"/></td>
        <td width="5%">&nbsp;</td>
        </tr></table>
</td></tr>


<tr>
<td class="labelheading1 tbcellCss"><b>Drug Name</b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b>Drug Code</b> <font color="red">*</font></td>


<!--  <td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b> <font color="red">*</font></td> -->  
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="drugName" styleId="drugName" style="width:14em;" title="Select Drug Name" onchange="getRouteTypeList();" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>  
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="asriDrugCode" styleId="asriDrugCode" title="Drug Code" maxlength="10" style="WIDTH:14em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b> <font color="red">*</font></td>
<!--  <td class="labelheading1 tbcellCss"><b>Batch Number</b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b>Expiry date</b> <font color="red">*</font></td>--></tr>  
<tr>
<td class="tbcellBorder">
<html:select name="chronicOpForm" property="dosage" styleId="dosage" style="width:14em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:14em" onchange="validateMedicationPeriod();validateNumber('Medication Period',this)" title="Enter Medication Period"/></td>
<!-- <td class="tbcellBorder"><html:text name="chronicOpForm" property="batchNo" styleId="batchNo"  style="WIDTH:14em" onchange="validateAlphaNum('Drug Batch Number',this,'Drug Batch Num')" title="Enter Batch Number"/></td>
<td width="50%" class="tbcellBorder"><html:text name="chronicOpForm" property="expiryDt" styleId="expiryDt" style="width:14em;" title="Select Expiry Date"   onkeydown="validateBackSpace(event)" readonly="true"/></td>   -->
</tr>
<tr>
<td colspan="1"></td><td>
<button type="button" class="btn btn-primary"   name="addDrug"  id="addDrug" onclick="addChronicDrugs()">Add Drugs</button>
<!-- <button type="button" class="btn btn-warning"   name="prescription"  id="prescription" onclick="fn_prescription()" data-toggle="tooltip" data-placement="bottom" title="Click Here To Print Prescription">Print Prescription&nbsp;<span class="glyphicon glyphicon-print"></span></button> -->
</td></tr>

</tr>


<tr><td colspan="4" > 
<div id='drugsContent' style="width:717px;overflow:auto; overflow-y:hidden"> 
  <table  width="80%"  id="drugsTable" style="display:none" border="1">
        <tr>  
      	<td width="3%"><b><fmt:message key="EHF.SNO"/></b></td> 
		<td width="8%"><b>Drug Name</b></td>
        <td width="5%"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
   <!--    <td width="10%"><b><fmt:message key="EHF.BatchNo"/></b></td>
        <td width="10%"><b><fmt:message key="EHF.ExpiryDt"/></b></td>  -->   
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="chronicOpForm" property="drugLt">
        <bean:size  id="drugSize" name="chronicOpForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="chronicOpForm" property="drugLt" >
        <tr>  
      	<td width="5%"><%=k++ %></td> 
       	<td width="10%"><bean:write name="drugLst" property="drugName" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="dosage" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="medicationPeriod" /></td> 
           <!--  <td width="5%"><bean:write name="drugLst" property="batchNo" /></td>  
 	<td width="5%"><bean:write name="drugLst"  property="expDt" /></td>   -->
       	<td width="5%">
       	<button type="button" class="btn btn-primary"   name=<bean:write name='drugLst' property='drugCode' /> id=<bean:write name='drugLst' property='drugCode' /> onclick="javascript:confirmRemoveRow(this,'drug');";>Delete</button>
       </td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>        
        </table></div>
</td></tr>
</table></td>


<br><br><br>
<tr><td colspan="4">
<table width="100%">
<tr>
<td style="height: 1em; font-size:small;" nowrap="nowrap" width=20%>
		<font color="red"><fmt:message key="EHF.MandatoryFields"/> <br /></font>
</td>
<td align="center" colspan="3"> 
<button type="button" class="btn btn-primary"    id="Submit" onclick="javascript:fn_savePatientDetails('submit')";>Submit</button>

<button type="button" class="btn btn-primary"    id="Reset" onclick="javascript:fn_resetAll()";>Reset</button>
</td>
<td width="20%"></td>
</tr>
</table>
</td></tr>
</table>

<div style="display:none">
<table width="80%">
<tr><td  width="15%"><b><fmt:message key="EHF.PatientType"/></b><font color="red">*</font></td>
<td id="patientType1" width="10%" ><html:radio name="chronicOpForm" property="patientType" value="IP" title="In Patient" styleId="patientType" disabled="true" onclick="enableIPOP()"/><b><fmt:message key="EHF.IP"/></b></td>
<td id="patientType2" width="10%"><html:radio name="chronicOpForm" property="patientType" value="OP" title="Out Patient" styleId="patientType" onclick="enableIPOP()"/><b><fmt:message key="EHF.OP"/></b></td>
<td id="patientType3" width="10%" class="labelheading1 tbcellCss"><html:radio name="chronicOpForm" property="patientType" value="ChronicOP" title="Chronic Out Patient" styleId="patientType" onclick="enableIPOP()"/><b><fmt:message key="EHF.ChronicOP"/></b></td>
<td  width="40%" class="labelheading1 tbcellCss"><button class="but"  type="button" id="saveDTRS" onclick="javascript:fn_savePatientDetails('SaveDTRS')">Save and Generate DTRS</button></td>
<td id="dtrsTd" width="15%"  class="labelheading1 tbcellCss" style="display:none">
<a href="javascript:generateDTRSPrint('<bean:write name="chronicOpForm" property="caseId" />','<bean:write name="chronicOpForm" property="hospitalId" />')"><b>DTRS Print Form</b></a>
</td>
</tr>
</table>
</div>



<html:hidden name="chronicOpForm" property="caseId" styleId="caseId"/>
<html:hidden name="chronicOpForm" property="testsCount" styleId="testsCount"/> 
<html:hidden name="chronicOpForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="chronicOpForm" property="hospitalId" styleId="hospitalId"/>
<input type="hidden" name="investigationsSel" id="investigationsSel">
<html:hidden name="chronicOpForm" property="otherDocDetailsList" styleId="otherDocDetailsList"/>
<html:hidden name="chronicOpForm" property="drugs" styleId="drugs"/>
<html:hidden name="chronicOpForm" property="hosptype" styleId="hosptype"/>
<html:hidden name="chronicOpForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="chronicOpForm" property="scheme" styleId="scheme"/>
<html:hidden name="chronicOpForm" property="opPkgCode" styleId="opPkgCode"/>
<html:hidden name="chronicOpForm" property="opIcdCode" styleId="opIcdCode"/>
<html:hidden name="chronicOpForm" property="packageDrugAmt" styleId="packageDrugAmt"/>
<script>

var browserName=navigator.appName;

		
		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
		document.getElementById("genTestTableID").style.display="";
		genOldList='${genInvestLst}'.split("@");
		genOldList.splice(genOldList.length-1,1);
		if(genOldList.length>0)
			document.forms[0].patientType[0].disabled=false;
		/*genInventCount=genInventList.length-1;*/
		}
		
	
	       
	    		
	    		gettherapyDtlsOnload();
	    		
		


			function gettherapyDtlsOnload()
			{
				
					setTimeout(function()
					{		                                                     		  	           
						if('${PatientOpList.opPkgCode}'!=null && '${PatientOpList.opPkgCode}'!='' && '${PatientOpList.opPkgCode}'!='-1')
						{
							document.getElementById("opPkgCode").value='${PatientOpList.opPkgCode}';
						    document.forms[0].opPkgName.value='${PatientOpList.opPkgCode}';

						    getChronicIcdList();
						   
						    setTimeout(function()
							{
								if('${PatientOpList.opIcdCode}'!=null && '${PatientOpList.opIcdCode}'!='' && '${PatientOpList.opIcdCode}'!='-1')
								{
									
							    	document.getElementById("opIcdCode").value='${PatientOpList.opIcdCode}';
								    document.getElementById("opIcdName").value='${PatientOpList.opIcdCode}';
							    	
							    	
								   }
						    }, 1999);

						    getChronicInvestList();
						    getChronicDrugsList();
						}}
					, 1999);
				}	
		
			$(document).ready(function() {
				$('#opIcdName').attr('disabled', true); });

</script>
<%--</logic:equal>--%>


</form>
</div>
</body>
</fmt:bundle>
</html>