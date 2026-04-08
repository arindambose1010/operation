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

<link href="css/select2.min.css" rel="stylesheet">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  

<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/select2.min.js"></script>
<style type="text/css">
.btn
{
border-radius:20px;
}
.btn:hover
{
border-radius:5px;
}
body
{
font-family: Tahoma, Helvetica, sans-serif;
font-size: 14px;

}
*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}    
select:focus
{
  outline:#000 dotted 1px; 
} 
.centerProgress{
width:50%;
align:center;
padding-top:20%;
padding-left:40%;
background-color:none;
border-radius:10%;
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

<script>



$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>
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

var serverDt='${serverDt}';

var serverDate;
//alert("server date new ");
//alert(serverDt);


var mDate1 = new Date(serverDt.substring(6,10),serverDt.substring(3,5)-1,serverDt.substring(0,2));
//alert(mDate1);
serverDate = new Date(mDate1.getFullYear(),mDate1.getMonth(),mDate1.getDate());
//alert(serverDate);
/*$(function () {
    $('#expiryDt').datepicker(
    {
    todayHighlight:true,
    autoclose:true,
    clearBtn:true,
    startDate:serverDate
    
    
});});
$(function () {
    $('#expCal').datepicker(
    {
    todayHighlight:true,
    autoclose:true,
    clearBtn:true,
    startDate:serverDate
    
    
});});*/

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

function fn_saveDetailsWithoutMandate(checkType)
{
document.getElementById("checkType").value=checkType;
fn_saveDetails();
}

function fn_saveDetails(){

	//calculateDrugsAmt();

	   var checkType='';
        if(document.getElementById("checkType"))
        	checkType=document.getElementById("checkType").value;
        else
        	checkType="save";
    
	var patId=document.getElementById("patientNo").value;
	//document.getElementById("opIcdName").disabled=false;
	document.getElementById("opPkgName").disabled=false;
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
	
	for(var i=0;i<personalHistory.length;i++)
	{
		if(personalHistory[i].checked)
		{
		var personalHistValue=personalHistory[i].value;
		var personalHistSubList=document.forms[0].elements[personalHistValue];
		for(var j=0;j<personalHistSubList.length;j++)
		{
			if(personalHistSubList[j].checked)	
			{
			personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
			personalSubCount++;
			if(personalHistSubList[j].value=='PR5.1'){
				if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
				{
					
				}
				else if(document.forms[0].AllMed[0].checked){
					if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
						{
						personalHistVal = personalHistVal+",AllMed$AllMedYes";					
						}
						else {
						personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
						}
						
					}
				else
				{
				var AllMedList=document.forms[0].AllMed;
				for(var z=0;z<AllMedList.length;z++)
					{
					if(AllMedList[z].checked)
						{
						personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
						}
					}
				}
				if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
				{
					
				}
				else if(document.forms[0].AllSub[0].checked){
					if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
						{
						personalHistVal = personalHistVal+",AllSub$AllSubYes";				
						}
						else {
						personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
						}
						
					}
				else
				{
				var AllSubList=document.forms[0].AllSub;
				for(var z=0;z<AllSubList.length;z++)
					{
					if(AllSubList[z].checked)
						{
						personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
						}
					}
				}
			}
			
			if(personalHistSubList[j].value=='PR6.1')
				{
				if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
					{
						 
					}
				else
					{
					var alcoholSubList=document.forms[0].alcohol;
					for(var z=0;z<alcoholSubList.length;z++)
						{
						if(alcoholSubList[z].checked)
							{
							personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
							}
						}
					}
				if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
				{
					
				}
				else if(document.forms[0].tobacco[2].checked)
					{
					if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
						{
						 
						}
					else
						{
						personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
						}
					if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
					{
					
					}
					else
						{
						personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
						}
				}
				else
				{
					var tobaccoSubList=document.forms[0].tobacco;
					for(var z=0;z<tobaccoSubList.length;z++)
						{
						if(tobaccoSubList[z].checked)
							{
							personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
							}
						}
				}
						
				
					var drugUseSubList=document.forms[0].drugUse;
					for(var z=0;z<drugUseSubList.length;z++)
						{
						if(drugUseSubList[z].checked)
							{
							personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
							}
						}
				
				
					var betelNutSubList=document.forms[0].betelNut;
					for(var z=0;z<betelNutSubList.length;z++)
						{
						if(betelNutSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
							}
						}
				  var betelLeafSubList=document.forms[0].betelLeaf;
					for(var z=0;z<betelLeafSubList.length;z++)
						{
						if(betelLeafSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
							}
						}			
			  }
			}
		}
		
		if(personalSubCount==0)
			{
			
			}
		personalSubCount=0;
		personalHistVal = personalHistVal+"#";
	     }
		else
			{
			personalCount++;
			}
	}

	//Mandatory Check validation For Examination Findings and its sublist
	var examinFnds=document.forms[0].examinationFnd;
	var examinCount=0;
	var examinSubCount=0;
	var examFndsVal="";
	for(var i=0;i<examinFnds.length;i++)
	{
		if(examinFnds[i].checked)
		{
			//examinCount++;
			var examinFndsValue=examinFnds[i].value;		
			var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
			var subType=document.forms[0].elements[examinFndsValue].type;
			
			if(examinFndsValue=='GE11'){			
					var tempType= '';
					if(document.forms[0].temp[0].checked==true) tempType='C';
					if(document.forms[0].temp[1].checked==true) tempType='F';
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";					
				}
			else if(examinFndsValue=='GE14'){
				    examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
					examFndsVal = examFndsVal+"#";
				}
			else if(examinFndsValue=='GE15'){
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
				}
			else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){				
			if(subType=="text" )
			{
            examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
			}
			else
			{
				var examinFndsSubList=document.forms[0].elements[examinFndsValue];
				
				
				for(var j=0;j<examinFndsSubList.length;j++)
				{
				if(examinFndsSubList[j].checked)	
					{
					examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
					examinSubCount++; 
					if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
						{
						var d;
							var dehydSubList=document.forms[0].dehydrationY;
							 for(d=0;d<dehydSubList.length;d++)
								{
								if(dehydSubList[d].checked)
									{
									examFndsVal = examFndsVal+dehydSubList[d].value;
									}
								}
						}
					}
				}
				examinSubCount=0;
			}
			examFndsVal = examFndsVal+"#";
		}
		}
		else
			{
			examinCount++;
			}
		}
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryCnt=0;	
	for (var x=0;x<pastHistory.length;x++)
	{
		if (pastHistory[x].checked)
		{
			if(pastHistory[x].value=="PH10")
			{
				if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
				{
					var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
					document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
				}
			}
		}
	}
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
	/*if(genTestsCount>0)
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
		} */	
	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}




var scheme="${hospScheme}";

if(scheme!=null && scheme=="CD202" && checkType!=null && checkType!="save")
{


	
		//Check to enable dtrs form
		if(checkType=='SaveDTRS')
		{
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
				var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
				jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save and generate DTRS Form?',fr);
			}
		}
		else if(checkType=='DTRS')
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

		//Mandatory Check for Patient Diagnosed By

			//Mandatory Check For Doctor Name Drop Down List


			//Mandatory Check for Prescription Details
			/*if(drugCount==0)
				{
				if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Add Drug (Drug Name,Dosage,Medication Period Details) ";
					}
			     if(lField=='')
			    	 lField='addDrug';
				}*/
			//Mandatory Check For Doctor Details

			
			

			
			
			



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
			//Mandatory Check For Investigations
			if(genInventList.length==0 && genOldList.length==0 ){
				 if(lErrorMsg==''){
				        lErrorMsg=lErrorMsg+"Select General Investigations ";
						}
				        if(lField=='')
				        lField='genInvestigations';   
				}







			 


			/*Added to validate prescribed investigations*/
				var investCheck=0;
				for(var i=0;i<genInventList.length;i++)
				{
					
					var invValues=genInventList[i].split("~");
					var invstId=invValues[1];
					if(invValues[1].indexOf("OI")!=-1)
						investCheck++;
				}
				for(var i=0;i<genOldList.length;i++)
				{
					var invValues=genOldList[i].split("~");
					var invstId=invValues[1];
					if(invValues[1].indexOf("OI")!=-1)
						investCheck++;
				} 
			var invSize=parseInt(genInventList.length)+parseInt(genOldList.length);
			if(investCheck==parseInt(genInventList.length)+parseInt(genOldList.length))
			{
				 if(lErrorMsg==''){
				        lErrorMsg=lErrorMsg+"Please Select atleast one prescribed Investigation ";
						}
				        if(lField=='')
				        lField='genInvestigations'; 
			}		
			
			
			if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
			  if(lErrorMsg==''){
			         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
					 }
			         if(lField=='')
			         lField='diagnosedBy';   
				}
			
				if(document.forms[0].doctorName.value==null || document.forms[0].doctorName.value=='' || document.forms[0].doctorName.value=='-1'){
			 	 if(lErrorMsg==''){
			         lErrorMsg=lErrorMsg+"Select OP Doctor Name ";
					 }
			         if(lField=='')
			         lField='doctorName';
			    
				} 
		if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
		{
			var fr=partial(focusBox,document.getElementById(lField));
			//var fr=partial(focusFieldView,lField);
			//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
		   bootbox.alert(lErrorMsg);
		    	focusBox(document.getElementById(lField));
		    return;
		 }
		else
		{
			var saveFlag="Submit";
			var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
			jqueryConfirmMsg('Case Registration Confirmation','Do you want to register patient case?',fr);
		}
		}







	

}
	else
	{
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
	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save?',fr);
	}}
}
function fn_savePatientDetails(checkType)
{
	//document.getElementById("opIcdName").disabled=false;
	document.getElementById("opPkgName").disabled=false;
	var doctorType=document.forms[0].diagnosedBy.value;
	document.getElementById("drugs").value=drugs;
	//document.getElementById("symptoms").value=symptoms;
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
	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}
	//Mandatory Check for Complaint
	var complaint=document.forms[0].complaints;

	var complaintCnt=0;
	for (var x=0;x<complaint.length;x++)
	{
		if (complaint[x].selected)
		{
			complaintCnt++;
		}
	}
	if(complaintCnt==0)
	{
		if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Complaint ";
		}
        if(lField=='')
        lField='complaints';   
	}

//Mandatory Check for Patient Complaint
var patientComplaint=document.forms[0].patientComplaint;

var patientComplaintCnt=0;
for (var x=0;x<patientComplaint.length;x++)
{
  if (patientComplaint[x].selected)
  {
   patientComplaintCnt++;
  }
}
if(patientComplaintCnt==0){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Patient Complaint ";
      }
        if(lField=='')
        lField='patientComplaint';   
}
//Mandatory check for Present History
if(document.forms[0].presentHistory.value=='' || document.forms[0].presentHistory.value==null){
	if(lErrorMsg==''){
             lErrorMsg=lErrorMsg+"Enter History Of Present Illness ";
		}
        if(lField=='')
        lField='presentHistory';   
}
//Mandatory check for History Of Past Illness
var pastHistory=document.forms[0].pastHistory;
var pastHistoryCnt=0;
for (var x=0;x<pastHistory.length;x++)
{
  if (pastHistory[x].checked)
  {
   pastHistoryCnt++;
   if(pastHistory[x].value=="PH11")
	   {
	   if(document.getElementById("pastHistryOthr").value=='' || document.getElementById("pastHistryOthr").value==null)
		   {
		   if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Enter Other History Of Past Illness value ";
		      }
		        if(lField=='')
		        lField='pastHistryOthr'; 
		   }
	   }
	   if(pastHistory[x].value=="PH8")
	   {
	   if(document.getElementById("pastHistryCancers").value=='' || document.getElementById("pastHistryCancers").value==null)
		   {
		   if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Cancers value ";
		      }
		        if(lField=='')
		        lField='pastHistryCancers'; 
		   }
	   }
	   if(pastHistory[x].value=="PH12")
	   {
	   if(document.getElementById("pastHistryEndDis").value=='' || document.getElementById("pastHistryEndDis").value==null)
		   {
		   if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Endocrine Diseases value ";
		      }
		        if(lField=='')
		        lField='pastHistryEndDis'; 
		   }
	   }
	   if(pastHistory[x].value=="PH10")
	   {
	   if(document.getElementById("pastHistrySurg").value=='' || document.getElementById("pastHistrySurg").value==null)
		   {
		   if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Surgeries value ";
		      }
		        if(lField=='')
		        lField='pastHistrySurg'; 
		   }
		 if(checkType=='SaveDTRS')
			{
			if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
				{
					var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
					document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
				}
			}
	   }
  }
}
//Mandatory Check validation For Personal History and its sublist
var personalHistory=document.forms[0].personalHistory;
var personalCount=0;
var personalSubCount=0;
var personalHistVal="";

for(var i=0;i<personalHistory.length;i++)
{
	if(personalHistory[i].checked)
	{
	//personalCount++;
	var personalHistValue=personalHistory[i].value;
	var personalHistSubList=document.forms[0].elements[personalHistValue];
	for(var j=0;j<personalHistSubList.length;j++)
	{
		if(personalHistSubList[j].checked)	
		{
		personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
		personalSubCount++;
		if(personalHistSubList[j].value=='PR5.1'){
			if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Medicine) Options ";
		     	 }
		        if(lField=='')
		        lField='AllMed';   
			}
			else if(document.forms[0].AllMed[0].checked){
				if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
					{
						if(lErrorMsg==''){
					        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Medicine) Specify Reason ";
					      }
						 if(lField=='') lField='AllMedRemrk'; 						
					}
					else {
					personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
					}
					
				}
			else
			{
			var AllMedList=document.forms[0].AllMed;
			for(var z=0;z<AllMedList.length;z++)
				{
				if(AllMedList[z].checked)
					{
					personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
					}
				}
			}
			if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Substance other than medicine) Options ";
		     	 }
		        if(lField=='')
		        lField='AllSub';   
			}
			else if(document.forms[0].AllSub[0].checked){
				if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
					{
						if(lErrorMsg==''){
					        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Substance other than Medicine) Specify Reason ";
					      }
						 if(lField=='') lField='AllSubRemrk'; 						
					}
					else {
					personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
					}
					
				}
			else
			{
			var AllSubList=document.forms[0].AllSub;
			for(var z=0;z<AllSubList.length;z++)
				{
				if(AllSubList[z].checked)
					{
					personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
					}
				}
			}
		}
		
		if(personalHistSubList[j].value=='PR6.1')
			{
			if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
				{
					if(lErrorMsg==''){
			         lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Alcohol options ";
			      	}
			        if(lField=='')
			        lField='alcohol';   
				}
			else
				{
				var alcoholSubList=document.forms[0].alcohol;
				for(var z=0;z<alcoholSubList.length;z++)
					{
					if(alcoholSubList[z].checked)
						{
						personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
						}
					}
				}
			if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Tobacco Options ";
		     	 }
		        if(lField=='')
		        lField='tobacco';   
			}
			else if(document.forms[0].tobacco[2].checked)
				{
				if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
					{
					if(lErrorMsg==''){
				        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking PackNo ";
				      }
				     if(lField=='')
				        lField='packNo';   
					}
				else
					{
					personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
					}
				if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
				{
				if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking Years ";
			      }
			      if(lField=='')
			        lField='smokeYears';   
				}
				else
					{
					personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
					}
			}
			else
			{
				var tobaccoSubList=document.forms[0].tobacco;
				for(var z=0;z<tobaccoSubList.length;z++)
					{
					if(tobaccoSubList[z].checked)
						{
						personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
						}
					}
			}
					
			if (!document.forms[0].drugUse[0].checked && !document.forms[0].drugUse[1].checked)
			{
				if(lErrorMsg==''){
		       lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Drug Use Options ";
		     	 }
		        if(lField=='')
		        lField='drugUse';   
			}
			else
			{
				var drugUseSubList=document.forms[0].drugUse;
				for(var z=0;z<drugUseSubList.length;z++)
					{
					if(drugUseSubList[z].checked)
						{
						personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
						}
					}
			}
			if (!document.forms[0].betelNut[0].checked && !document.forms[0].betelNut[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Nut Options ";
		     	 }
		        if(lField=='')
		        lField='betelNut';   
			}
			else
			{
				var betelNutSubList=document.forms[0].betelNut;
				for(var z=0;z<betelNutSubList.length;z++)
					{
					if(betelNutSubList[z].checked)
						{
						personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
						}
					}
			}
			if (!document.forms[0].betelLeaf[0].checked && !document.forms[0].betelLeaf[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Leaf Options ";
		      	}
		        if(lField=='')
		        lField='betelLeaf';   
			}
			else
			{
				var betelLeafSubList=document.forms[0].betelLeaf;
				for(var z=0;z<betelLeafSubList.length;z++)
					{
					if(betelLeafSubList[z].checked)
						{
						personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
						}
					}
			}
		  }
		}
	}
	
	if(personalSubCount==0)
		{
		if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Select Personal History "+personalHistSubList[0].name+" Options ";
				}
        if(lField=='')
        lField=personalHistValue;  
		}
	personalSubCount=0;
	personalHistVal = personalHistVal+"#";
     }
	else
		{
		personalCount++;
		}
}
if(personalCount>0)
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select All Personal History Options ";
		}
        if(lField=='')
        lField='personalHistory';  
	}
//Mandatory Check validation For Examination Findings and its sublist
var examinFnds=document.forms[0].examinationFnd;
var examinCount=0;
var examinSubCount=0;
var examFndsVal="";
for(var i=0;i<examinFnds.length;i++)
{
	if(examinFnds[i].checked)
	{
		//examinCount++;
		var examinFndsValue=examinFnds[i].value;		
		var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
		var subType=document.forms[0].elements[examinFndsValue].type;
		
		if(examinFndsValue=='GE11'){
			if(document.forms[0].temp[0].checked==false && document.forms[0].temp[1].checked==false){
				if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Please select C or F option in "+examinFndsName+"";
					}
					if(lField=='')
					lField=examinFndsValue; 
				}
			if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
			}
			if(lField=='')
			lField=examinFndsValue; 
			}
			else{
				var tempType= '';
				if(document.forms[0].temp[0].checked==true) tempType='C';
				if(document.forms[0].temp[1].checked==true) tempType='F';
				examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
				examFndsVal = examFndsVal+"#";
				}
			}
		else if(examinFndsValue=='GE14'){
			if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null
					||document.getElementById("BP1").value==""||document.getElementById("BP1").value==null )
				
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
			}
			if(lField=='')
			lField=examinFndsValue; 
			}
			else{
				
				examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
				examFndsVal = examFndsVal+"#";
				}
			}
		else if(examinFndsValue=='GE15'){
			if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null||
					document.getElementById("BP2").value==""||document.getElementById("BP2").value==null)
				
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
			}
			if(lField=='')
			lField=examinFndsValue; 
			}
			else{
				examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
           }
			}
		else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){
			
		if(subType=="text" )
		{
		if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
		{
		if(lErrorMsg==''){
		lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
		}
		if(lField=='')
		lField=examinFndsValue; 
		}
		else{
			
			examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
			
			}
		}
		else
		{
			var examinFndsSubList=document.forms[0].elements[examinFndsValue];
			
			
			for(var j=0;j<examinFndsSubList.length;j++)
			{
			if(examinFndsSubList[j].checked)	
				{
				examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
				examinSubCount++; 
				if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
					{
					if(!document.forms[0].dehydrationY[0].checked && !document.forms[0].dehydrationY[1].checked && !document.forms[0].dehydrationY[2].checked)
						{
						if(lErrorMsg==''){
							lErrorMsg=lErrorMsg+"Select Examination Findings Dehydration Sub Options ";
							}
							if(lField=='')
							lField='dehydrationY'; 
						}
					else
						{
						var d;
						var dehydSubList=document.forms[0].dehydrationY;
						 for(d=0;d<dehydSubList.length;d++)
							{
							if(dehydSubList[d].checked)
								{
								examFndsVal = examFndsVal+dehydSubList[d].value;
								}
							}
 
						}
					}
				}
			}
			if(examinSubCount==0)
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Select Examination Findings "+examinFndsSubList[0].name+" Options ";
			}
			if(lField=='')
			lField=examinFndsValue;  
			}
			examinSubCount=0;
		}
		examFndsVal = examFndsVal+"#";
	}
	}
	else
		{
		examinCount++;
		}
	}
	
if(examinCount>0)
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select All Examination Findings Options ";
		}
        if(lField=='')
        lField='examinationFnd'; 
	}
//Mandatory Check validation For Family History 
var familyHistory=document.forms[0].familyHistory;
var familyHistCount=0;
for(var i=0;i<familyHistory.length;i++)
{
	if(familyHistory[i].checked)
	{
	familyHistCount++;
	if(familyHistory[i].value=="FH11")
	   {
	   if(document.getElementById("familyHistoryOthr").value=='' || document.getElementById("familyHistoryOthr").value==null)
		   {
		   if(lErrorMsg==''){
		     lErrorMsg=lErrorMsg+"Enter Other Family History Value ";
			 }
		        if(lField=='')
		        lField='familyHistoryOthr'; 
		   }
	   }
	}
}


//Commented chronic OP
/*else if(document.forms[0].patientType[2].checked)
{
	 opIP=document.forms[0].patientType[2].value;
}*/
var pkgCode=document.getElementById("opPkgName").value;
var catCode=document.getElementById("opIcdName").value;
if(pkgCode==null || pkgCode=='-1' || pkgCode=='')
{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Chronic Package Code ";
		}
        if(lField=='')
        lField='opPkgName';   

}

if(catCode==null || catCode=='-1' || catCode=='')
{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Chronic Category ";
		}
        if(lField=='')
        lField='opIcdName';   

}



 if(genInventList.length==0 && genOldList.length==0 ){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select General Investigations ";
		}
        if(lField=='')
        lField='genInvestigations';   
}
//Check to enable dtrs form
if(checkType=='SaveDTRS')
{
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
		var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
		jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save and generate DTRS Form?',fr);
	}
}
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

//Mandatory Check for Patient Diagnosed By

	//Mandatory Check For Doctor Name Drop Down List


	//Mandatory Check for Prescription Details
	if(drugCount==0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Add Drug (Drug Name,Dosage,Medication Period Details) ";
			}
	     if(lField=='')
	    	 lField='addDrug';
		}
	//Mandatory Check For Doctor Details

	if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
	  if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
			 }
	         if(lField=='')
	         lField='diagnosedBy';   
		}
	
		if(document.forms[0].doctorName.value==null || document.forms[0].doctorName.value=='' || document.forms[0].doctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select OP Doctor Name ";
			 }
	         if(lField=='')
	         lField='doctorName';
	    
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
	//var fr=partial(focusFieldView,lField);
	//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
   alert(lErrorMsg);
    	focusBox(document.getElementById(lField));
    return;
 }
else
{
	var saveFlag="Submit";
	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to register patient case?',fr);
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
function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
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
 	
	 for(var c=0;c<spec.length;c++)
		{
			var specValues=spec[c].split("~");
			if(specValues[8]!='NA' && specValues[7]!='NA')
			{
				if((selInvData!=null || selInvData!='') && selInvData.length>0)
		           {
		           selInvData=selInvData+'~';
		           }          
		           selInvData=selInvData+specValues[8]+'$'+specValues[7]+'$'+specValues[2]; 
			}				   
		}
		//document.forms[0].addTests.value=selGenInvData;
		document.forms[0].investigationsSel.value=selInvData;
		document.forms[0].personalHistVal.value=personalHistVal;
   		document.forms[0].examFndsVal.value=examFndsVal;
   	
		$('button').prop('disabled', true);

		
		var url="./chronicAction.do?actionFlag=saveCaseDetails&chronicId="+patId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType+"&genInvestRemove="+genInvestRemove+"&specRemove="+specRemove;
	
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
	//document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+document.getElementById("patientNo").value+"&caseId=";
	// document.forms[0].method="post";
	//document.forms[0].submit();
	var complainLen=document.getElementById("complaints").length;
	for (var x=0;x<complainLen;x++)
	{
		if (document.forms[0].complaints[x].selected)
		{
			document.forms[0].complaints[x].selected=false;
		}
	}
	document.getElementById("complaintCode").value="";
	document.getElementById("presentHistory").value="";
	document.getElementById("patientComplaint").options.length=0;
	document.getElementById("patComplaintCode").value="";
	var pastHistory=document.forms[0].pastHistory;
	for(var i=0;i<pastHistory.length;i++)
	{
		if(pastHistory[i].checked)
		{
        pastHistory[i].checked=false;
        getOtherText(pastHistory[i]);
		}
	}
	var personalHistory=document.forms[0].personalHistory;
	for(var i=0;i<personalHistory.length;i++)
	{
		if(personalHistory[i].checked)
		{
        personalHistory[i].checked=true;
        getSubListHistory(personalHistory[i],'reset');
		}
	}
	var examinationFnd=document.forms[0].examinationFnd;
	for(var i=0;i<examinationFnd.length;i++)
	{
		if(examinationFnd[i].checked)
		{
			examinationFnd[i].checked=true;
			getSubFieldType(examinationFnd[i]);
		}
	}
	var familyHistory=document.forms[0].familyHistory;
	for(var i=0;i<familyHistory.length;i++)
	{
		if(familyHistory[i].checked)
		{
			familyHistory[i].checked=false;
			getOtherText(familyHistory[i]);
		}
	}
	
	
	
	//document.getElementById("catName").options.length = 1;
	//document.getElementById("catCode").value= "";
	document.getElementById("drugName").value ="-1";
	document.getElementById("asriDrugCode").value="";

	document.getElementById("dosage").value="";
	document.getElementById("medicatPeriod").value="";

	document.getElementById("opPkgName").value="-1";
	document.getElementById("opPkgCode").value="";
	document.getElementById("opIcdName").value="-1";
	document.getElementById("opIcdCode").value="";
	document.getElementById("diagnosedBy").value="-1";
	document.forms[0].doctorName.options.length=1;
	
	//document.getElementById("subCatName").options.length = 1;
	//document.getElementById("subCatCode").value = "";
	//document.getElementById("diseaseName").options.length = 1;
	//document.getElementById("diseaseCode").value = "";
	//document.getElementById("disAnatomicalName").options.length = 1;
	//document.getElementById("disAnatomicalCode").value = "";
	
	genInventCount=0;	
	var genTestTable = document.getElementById("genTestTable");
	for(var count = genTestTable.rows.length-1 ; count>0; count--)
		{
		genTestTable.deleteRow(count);
		}
	document.getElementById("genTestTable").style.display="none";
	genInventList=new Array();
	document.getElementById("testsCount").value=0;	
	document.forms[0].patientType[0].disabled=true;
	
	/*if(document.getElementById("treatingDocLabel").style.display=='')
	{
	document.getElementById("treatingDocLabel").style.display='none';
	document.getElementById("treatingDocRemarks").innerHTML='';
	}*/
	if(document.forms[0].patientType[0].checked==true)
	{
	document.getElementById("asriCatName").value=-1;
	document.getElementById("asriCatCode").value="";
	document.getElementById("ICDCatName").options.length = 1;
	document.getElementById("ICDCatCode").value="";
	document.getElementById("ICDProcName").options.length = 1;
	document.getElementById("ICDProcCode").value="";
	
	document.getElementById("procUnits").value=-1;
	document.getElementById("unitsTd").style.display='none';
	document.getElementById("unitsLabelTd").style.display='none';
	
	document.getElementById("docSpecReg").options.length = 1;
	document.getElementById("ipDoctorData").style.display='none';
	document.getElementById("ipOtherDocName").value="";
    document.getElementById("ipDocRegNo").value="";
    document.getElementById("ipDocQual").value="";
    document.getElementById("ipDocMobileNo").value="";
	
	document.getElementById("investigations").options.length = 0;
	catCount=0;
	var catTable = document.getElementById("categoryTable");
	for(var count = catTable.rows.length - 1 ; count>0; count--)
		{
		catTable.deleteRow(count);
		}
	document.getElementById("categoryTable").style.display="none";
	spec=new Array();
	document.getElementById("speciality").value=spec;
	otherDocDetails=new Array();
	document.getElementById("otherDocDetailsList").value=otherDocDetails;
	procList=new Array();
	document.getElementById("procSelectedTd").style.display="none";
	
	/* var table = document.getElementById("testTable");  
	 for(var i = table.rows.length - 1; i > 0; i--){ 
	  	   table.deleteRow(i);
	   }
	document.getElementById("investigations").options.length=0;
	document.getElementById("investigationSel").options.length=0;
	document.getElementById("testTable").style.display='none'; */
	
	document.getElementById("ipNo").value="";
	document.getElementById("admissionType").value="-1";
	document.getElementById("expiryDt").value="";
	
	document.getElementById("ipDiagnosedBy").value="-1";
	document.getElementById("ipDoctorName").options.length= 1;
	document.getElementById("remarks").value="";	

	if(specOld.length==0){		
	document.getElementById("ipDocNameList").style.display="";
	document.getElementById("IPHead2").style.display="none";
	document.getElementById("diagnosisData").style.display="none";
	document.forms[0].patientType[0].checked=false;
	if(genOldList.length>0)
		document.forms[0].patientType[0].disabled=false;
	else
		document.forms[0].patientType[0].disabled=true;
	}else{
		document.forms[0].patientType[0].checked=true;
		document.forms[0].patientType[0].disabled=false;		
	}	
	if(document.forms[0].legalCase[0].checked==true)
	{
		document.getElementById("legalCaseNoTd").style.display='none';
		document.getElementById("legalCaseNo").value='';
		document.getElementById("policeStatNameTd").style.display='none';
		document.getElementById("policeStatName").value='';
		document.forms[0].legalCase[0].checked=false;
	}
	else
		document.forms[0].legalCase[1].checked=false;
	}
else if(document.forms[0].patientType[1].checked==true)
	{
	
	var existDrugsTable = document.getElementById("existDrugs");
	for(var count = existDrugsTable.rows.length - 1 ; count>0; count--)
		{
		existDrugsTable.deleteRow(count);
		}
	document.getElementById("existDrugs").style.display="none";
	document.getElementById("existDrugsHead").style.display="none";
	
	
	document.getElementById("drugName").options.length = 1;
	document.getElementById("asriDrugCode").value="";
	//document.getElementById("route").value="";
	//document.getElementById("strength").value="";
	document.getElementById("dosage").value="";
	document.getElementById("medicatPeriod").value="";
	document.getElementById("opPkgName").options.length=1;
	document.getElementById("opPkgCode").value="";
	document.getElementById("opIcdName").options.length=1;
	document.getElementById("opIcdCode").value="";
	
	drugCount=0;
	
	var drugTable = document.getElementById("drugsTable");
	for(var count = drugTable.rows.length-1 ; count>0; count--)
		{
		drugTable.deleteRow(count);
		}
	document.getElementById("drugsTable").style.display="none";
	drugs=new Array();
	
	document.getElementById("opNo").value="";
	document.getElementById("opRemarks").value="";
	document.getElementById("diagnosisData").style.display="none";
	document.getElementById("OPHead2").style.display="none";
	document.getElementById("prescriptionData").style.display="none";
	document.getElementById("OPDoctor").style.display="none";
	document.getElementById("diagnosedBy").value="-1";
	document.forms[0].doctorName.options.length=1;
	document.getElementById("docNameList").style.display="";
	document.getElementById("docNametext").style.display="none";
	document.getElementById("otherDocName").value="";
	document.getElementById("doctorDataDiv").style.display="none";
	document.getElementById("doctorData").style.display="none";
	document.getElementById("docRegNo").value="";
	document.getElementById("docQual").vlaue="";
	document.getElementById("docMobileNo").value="";
	
	document.forms[0].patientType[1].checked=false;
	}
	//Commented Chronic OP
/*else if(document.forms[0].patientType[2].checked==true)
{
	document.getElementById("drugTypeCode").value=-1;
	document.getElementById("drugCode").value="";
	document.getElementById("drugSubTypeName").options.length=1;
	document.getElementById("drugSubTypeCode").value="";
	document.getElementById("drugName").options.length = 1;
	document.getElementById("asriDrugCode").value="";
	document.getElementById("route").value="";
	document.getElementById("strength").value="";
	document.getElementById("dosage").value="";
	document.getElementById("medicatPeriod").value="";
	
	drugCount=0;
	
	var drugTable = document.getElementById("drugsTable");
	for(var count = drugTable.rows.length-1 ; count>0; count--)
		{
		drugTable.deleteRow(count);
		}
	document.getElementById("drugsTable").style.display="none";
	drugs=new Array();
	
	document.getElementById("opCatName").value=-1;
	document.getElementById("opCatCode").value="";
	document.getElementById("opPkgName").options.length=1;
	document.getElementById("opPkgCode").value="";
	document.getElementById("opIcdName").options.length=1;
	document.getElementById("opIcdCode").value="";
	
	document.getElementById("diagnosisData").style.display="none";
	document.getElementById("prescriptionData").style.display="none";
	document.getElementById("OPDoctor").style.display="none";
	document.getElementById("ChronicOPTherapy").style.display="none";
	//document.forms[0].patientType[2].checked=false;
	}*/
	//parent.fn_resizePage();
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
function fn_getDoctorsList(){
	document.getElementById("docNameList").style.display='';
 	document.getElementById("docNametext").style.display='none';
 	document.getElementById("doctorData").style.display='none';
 	document.getElementById('doctorDataDiv').style.display='none';
 	document.getElementById('doctorDataDiv').innerHTML="";
	var hospId = document.getElementById("hospitalId").value;
	var xmlhttp;
    var url;
    var docType; 
    if(document.forms[0].diagnosedBy.value=='-1')
	   {
	   document.forms[0].doctorName.options.length=1;
	   return false;
	   }
   else
	   {
		docType=document.forms[0].diagnosedBy.value;	
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
   		 url = "./patientDetails.do?actionFlag=getDoctorListAjax&callType=Ajax&docTypeVal="+docType+"&hospId="+hospId;    
   		 xmlhttp.onreadystatechange=function()
   		 {
       		if(xmlhttp.readyState==4)
       		{
    	   		var resultArray=xmlhttp.responseText;
           		if(resultArray.trim()=="SessionExpired*")
           		{
           			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
           		}
           		else
           		{
           			resultArray = resultArray.replace("[","");
           			resultArray = resultArray.replace("]*","");
           			var ldocDetailsList = resultArray.split(","); 
           			if(ldocDetailsList.length>0)
           			{
               			document.forms[0].doctorName.options.length=0;
              			document.forms[0].doctorName.options[0]=new Option("--select--","-1");
               			for(var i = 0; i<ldocDetailsList.length;i++)
               			{	
                    		var arr=ldocDetailsList[i].split("~");                     
                   			if(arr[1]!=null && arr[0]!=null)
                    		{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                                               
                        		document.forms[0].doctorName.options[i+1] =new Option(val1,val2);
                    		}
                    /* else
                    {
                        document.forms[0].doctorName.options[0]=new Option("--select--","-1");
                      
                    } */
                		}
            		}
           			if('${PatientOpList.doctorDtls}'!=null && '${PatientOpList.doctorDtls}'!='')
      	 			{document.forms[0].doctorName.value='${PatientOpList.doctorDtls}';
      	 			//fn_getDoctorsDetails();
      	 			}
         		}
       		}			
   		}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	   }
}

function fn_getIPDoctorsList()
{
	var patientType="IP";
	document.getElementById("ipDocNameList").style.display='';
	var hospId = document.getElementById("hospitalId").value;
	var xmlhttp;
	var url;
	var docType; 
	if(document.forms[0].ipDiagnosedBy.value=="-1")
		{
		document.forms[0].ipDoctorName.options.length=1;
		return false;
		}
	else
		{
	docType=document.forms[0].ipDiagnosedBy.value;	
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
	//url = "./patientDetails.do?actionFlag=getDoctorListAjax&docTypeVal="+docType+"&hospId="+hospId+"&patientType="+patientType+"&therapyCategory="+therapyCat;
	url = "./patientDetails.do?actionFlag=getDoctorListAjax&callType=Ajax&docTypeVal="+docType+"&hospId="+hospId; 
	xmlhttp.onreadystatechange=function()
		{
   		if(xmlhttp.readyState==4)
   		{
  	 		var resultArray=xmlhttp.responseText;
   			if(resultArray.trim()=="SessionExpired*"){
   				jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
       			resultArray = resultArray.replace("[","");
       			resultArray = resultArray.replace("]*","");
       			var ldocDetailsList = resultArray.split(","); 
       			if(ldocDetailsList.length>0)
       			{
           			document.forms[0].ipDoctorName.options.length=0;
           			document.forms[0].ipDoctorName.options[0]=new Option("--select--","-1");
          	 		for(var i = 0; i<ldocDetailsList.length;i++)
           			{	
                		var arr=ldocDetailsList[i].split("~");                     
                		if(arr[1]!=null && arr[0]!=null)
                		{
                    		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                    		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                         
                    		document.forms[0].ipDoctorName.options[i+1] =new Option(val1,val2);
                		}
                		/* else
                		{
                    		document.forms[0].doctorName.options[0]=new Option("--select--","-1");
                        } */
            		}
          	 		if('${PatientOpList.doctorDtls}'!=null && '${PatientOpList.doctorDtls}'!='')
          	 			{document.forms[0].ipDoctorName.value='${PatientOpList.doctorDtls}';
          	 			//fn_getIPDoctorsDetails();
          	 			}
        		} 
   		  	}
   		}			
	}
xmlhttp.open("Post",url,true);
xmlhttp.send(null);
		}
	//}
}

function fn_loadingImage()
{
	 $(function () { 
		 var $modal = $('#progressBar'),
	     $bar = $modal.find('.progress-bar progress-bar-striped active');
	 
	 $modal.modal('show');
	 $bar.addClass('animate');

	 setTimeout(function() {
	   $bar.removeClass('animate');
	   $modal.modal('hide');
	 }, 4000);
	 });
}
function fn_removeLoadingImage()
{
	document.getElementById('progressBar').style.display='none';
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
function fn_cancel()
{
if(confirm("Do you want to cancel the following CHRONIC OP Case ? "))
{
	var chronicId=document.getElementById("patientNo").value;
	document.forms[0].action="./chronicAction.do?actionFlag=cancelCase&chronicId="+chronicId;
	document.forms[0].method="post";
	document.forms[0].submit();
}
else
	return false;
}
function loadPackageDtlsOnload()
{
	
	var packageName=document.getElementById("opPkgName").options[document.getElementById("opPkgName").selectedIndex].text;
	var pkgCode=document.getElementById("opPkgCode").value;
	var catName=document.getElementById("opIcdName").options[document.getElementById("opIcdName").selectedIndex].text;
	var catCode=document.getElementById("opIcdCode").value;
	if(packageName!=null && packageName!=" " && pkgCode!=null && pkgCode!="-1" && catName!=null && catName!=" " && catCode!=null && catCode!=" ")
	{
		document.getElementById("packageDiv").style.display="";
	}
}

function addChronicPackage(addType)
{
	var packageName=document.getElementById("opPkgName").options[document.getElementById("opPkgName").selectedIndex].text;
	var pkgCode=document.getElementById("opPkgCode").value;
	var catName=document.getElementById("opIcdName").options[document.getElementById("opIcdName").selectedIndex].text;
	var catCode=document.getElementById("opIcdCode").value;


if(addType=="addNew")
{
	if(document.getElementById("packageDiv").style.display!="none")
	{
		alert("Only one Chronic Package can be Added.Please Remove the Existing Package and proceed ");
		return false;
	}
}
	
if(packageName!=null && packageName!="-1" && pkgCode!=null && pkgCode!="" && document.getElementById("opIcdName").value!=null && document.getElementById("opIcdName").value!="-1" && catCode!=null && catCode!=" ")
{

    document.getElementById("packageDiv").style.display="";

    if(document.getElementById("packageDivRow"))
    document.getElementById("packageDivRow").style.display="none";

    document.getElementById("addPckgFlg").value="Y";
   // alert(  document.getElementById("addPckgFlg").value);
   // alert(document.forms[0].addPckgFlg.value);
	var table=document.getElementById("pckgTable");
	var newRow=table.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+packageName+'</td>';
	var newcell=newRow.insertCell(1);
	newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+pkgCode+'</td>';
	var newcell=newRow.insertCell(2);
	newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+catName+'</td>';
	var newcell=newRow.insertCell(3);
	newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+catCode+'</td>';
	newcell=newRow.insertCell(4);
	newcell.innerHTML='<td class="tbcellBorder tbcellCss"><button type="button" class="btn btn-danger"  onclick="deleteChronicPackage();" name='+document.getElementById("drugName").value+' id='+document.getElementById("drugName").value+'>Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
	getChronicInvestList();
	getChronicDrugsList();
	document.getElementById("chronicPackageCode").value=pkgCode;
	document.getElementById("chronicCatCode").value=catCode;
}

else
{
	if(document.getElementById("opPkgName").value==null || document.getElementById("opPkgName").value=="-1")
alert("Chronic Package cannot be added as Chronic OP Package  is not selected");
	else if(document.getElementById("opIcdName").value==null || document.getElementById("opIcdName").value=="-1")
alert("Chronic Package cannot be added as Chronic OP Category  is not selected");
}
	}

function deleteChronicPackage()
{
	var investLen=genInventList.length;
	var investOldLen=genOldList.length;
	if(investLen>0 || investOldLen>0)
	{
		alert("Please Remove Added Investigations To Delete Chronic Package");
		return false;
	}
	else
	{
	if(confirm("Drug/Prescription Details Will Be Removed Completely.Do You Want To Delete Chronic Package ? "))
			{



		var table=document.getElementById('drugsTable');
		var invTable=document.getElementById('genTestTable');
		var invElements=invTable.getElementsByTagName('tr');
		var elements=table.getElementsByTagName('tr');
		
		if(elements.length>1)
		{
		for(var i=1;i<=elements.length;i++)
			{
			table.deleteRow(1);
			}
		}
		
		if(invElements.length>1)
		{
		for(var i=1;i<=invElements.length;i++)
			{
			invTable.deleteRow(1);
			}
		}
		document.getElementById("drugName").value="-1";
		document.getElementById("drugName").options.length=1;
		document.getElementById("genTestTable").style.display="none";
		document.getElementById("genTestTableID").style.display="none";
		//document.getElementById("drugs").value="";
		document.forms[0].asriDrugCode.value="";
		document.getElementById("genBlockInvestName").value="-1";
		document.getElementById("genBlockInvestName").options.length=1;
		document.getElementById("genInvestigations").value="-1";
		document.getElementById("genInvestigations").options.length=1;
		genInventList="";
		genOldList="";


		
		document.getElementById('genTestTableID').style.display='none';
		document.getElementById("drugs").value="";
		document.getElementById("drugsTable").style.display="none";

		document.getElementById("packageDiv").style.display="none";
		
		genInventCount=0;
		updateGenInvestCount=0;
		updateGenInvestLst=new Array();
		genInventList=new Array();
		
		genInventList.length=0;
		genOldList=new Array();
		drugCount=0;
		drugs=new Array();
		existDrugsArr=new Array();
		
		var elmtTable = document.getElementById('pckgTable');
		var tableRows = elmtTable.getElementsByTagName('tr');
		var rowCount = tableRows.length;
       // alert(rowCount);
		for (var x=rowCount-1; x>0; x--) {
		   //elmtTable.removeChild(tableRows[x]);
			elmtTable.deleteRow(x);
		}

		
		document.getElementById("opPkgName").value="-1";
		document.getElementById("asriDrugCode").value="";
		document.getElementById("dosage").value="-1";
		document.getElementById("medicatPeriod").value="";
		document.getElementById("chronicPackageCode").value="";
		document.getElementById("chronicCatCode").value="";
		//document.getElementById("batchNo").value="";
		//document.getElementById("expiryDt").value="";
		validateChronicOpPkg();
		document.forms[0].addPckgFlg.value="";
			}

		else
		{
	     return false;
		}

	}

}

function generateDtrsPrint()
{
	var chronicId=document.getElementById("patientNo").value;
	
	window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&DTRS=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');	

}


function fn_prescription()
{
	 var chronicId=document.getElementById("patientNo").value;
	window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&prescription=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
}

/*(function($){	   
	$(window).load(function(){
		$("#commonDtlsField").mCustomScrollbar({
			theme:"dark-thin"
			});	
		$("#cardAddressField").mCustomScrollbar({
			theme:"dark-thin"
			});	
		$("#commAddressField").mCustomScrollbar({
			theme:"dark-thin"
			});
	});	
})(jQuery)*/;

function fn_enableOthers()
{
	var pkgDisplay=document.getElementById("packageDiv").style.display;
if(document.getElementById("invOthers"))
{
	if(document.getElementById("invOthers").checked)
	{
		if(pkgDisplay=="none")
		{
bootbox.alert("Please Add Package Details to Add Investigations");
document.getElementById("invOthers").checked=false;
return false;

		}
		
	document.getElementById("InvBlockName").style.display="none";	
	document.getElementById("InvName").style.display="none";
	document.getElementById("invLst").style.display="none";	
	document.getElementById("InvBlckLst").style.display="none";
    document.getElementById("otherInvName").style.display="";
    document.getElementById("otherInvName").value="";
    document.getElementById("otherInvName").focus();
    document.getElementById("otherInvNameHead").style.display="";
	}
	else
	{
		document.getElementById("InvBlockName").style.display="";	
		document.getElementById("InvName").style.display="";
		document.getElementById("invLst").style.display="";	
		document.getElementById("InvBlckLst").style.display="";
	    document.getElementById("otherInvName").style.display="none";
	    document.getElementById("otherInvNameHead").style.display="none";
	    document.getElementById("otherInvName").value="";
	}
}
}

function check_maxLength(id,remarkLength)
{
	var name = document.getElementById(id).value;
	 if(name != null && name !='' && name.length >= remarkLength)
 	{
	 	bootbox.alert("length should not exceed " +remarkLength );
	 	  document.getElementById(id).value='';
	 		document.getElementById(id).focus();
 	}
   if(name.trim().length==0){
	bootbox.alert("Multiple Spaces are not allowed");
	document.getElementById(id).value='';
	document.getElementById(id).focus();
	   }
 

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


$(document).ready(function(){
var schemeId='${hospScheme}';

	if(schemeId!=null && schemeId=="CD202")
	{
		$('.onlyAp').css('display','none');
	}
	else
	{
		$('.onlyAp').css('display','');
		$('#collapseOne').addClass("in");
	}

});



</script>
</head>
<body onload="fn_loadingImage()">

<!--  
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
	-->
<div id="middleFrame_content">

<form action="/chronicAction.do" method="post" enctype="multipart/form-data">
<br>

<table width="90%" style="margin:0 auto" class="table table-responsive ">
<tr><td colspan="4">
<div class="col-lg-6"> 
<table class="tbheader  table-responsive ">
<tr><td align="left"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.PatientRegistration"/></b></td></tr>
</table></div>

<table width="100%" class="table table-responsive">
<tr><td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HealthCardNo"/></b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="chronicOpForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td>
<td width="15%" class="labelheading1 tbcellCss"><b>Chronic ID</b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="chronicOpForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>

<logic:equal  name="chronicOpForm" property="telephonicReg" value="Yes"><b><td width="15%" class="labelheading1 tbcellCss">Telephonic ID</td>
<td width="15%" class="tbcellBorder"><a href="javascript:viewTelephonicInfo('<bean:write name="chronicOpForm" property="telephonicId" />')"><bean:write name="chronicOpForm" property="telephonicId" /></a></td></b>
</logic:equal>
<logic:notEqual  name="chronicOpForm" property="telephonicReg" value="Yes">
<td width="30%">&nbsp;</td>
</logic:notEqual>
</tr>
</table>

<div class="col-lg-6"> 
<table class="tbheader table-responsive">
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table></div>
<table width="100%" class="table table-responsive">
<tr>
<td width="27%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commonDtlsField'>
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
<td width="29%" valign="top">
<fieldset style="height:20em;" >
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
  <div style="height:18em;overflow:hidden" id='cardAddressField'>
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
<td width="29%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commAddressField'>
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
<td width="15%" valign="top">
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
<div class="col-lg-6"> 
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.CaseDetails"/></b></td></tr>
</table></div>
<br>
<table width="100%">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.RegisteredHospital"/> </b></td><td width="25%" class="tbcellBorder"><b><bean:write name="chronicOpForm" property="hospitalName"/></b></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateAndTime"/> </b> </td><td width="25%" class="tbcellBorder"><b><bean:write name="chronicOpForm" property="dtTime"/></b></td>
</tr>
</table>
<logic:notEmpty name="groupList"> 
<logic:iterate name="groupList" id="labelbean">
<logic:equal name="labelbean" property="ID" value="GP2">
<%-- <logic:equal name="UserRole" value="CD9"> --%>
<div class="col-lg-6"> 
<table class="tbheader ">
</table></div>
<br>

<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click Here to View Medical Details" style="color:#fff; display:block;">
         

			<span id="medicalDtls" style="font-size: 1.25em;"><b>&nbsp;&nbsp;Medical Details&nbsp;&nbsp;<span class="glyphicon glyphicon-plus"></span></b></span>

        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
      
<table width="100%" class="medicalDetailsTable" border="0">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ComplaintType"/></b> <font class="onlyAp" color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ComplaintCode"/></b> <font class="onlyAp" color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientComplaint"/></b> <font class="onlyAp" color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientComplaintCode"/></b> <font class="onlyAp" color="red">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder"><html:select name="chronicOpForm" property="complaints" styleId="complaints" style="WIDTH:17em;;height:5em" title="Select Complaint Type" multiple="multiple" onchange="javascript:getComplaintType('notOnLoad');" onmouseover="addTooltip('complaints')" >
<!--<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>-->
<html:options property="ID" collection="mainComplaintList" labelProperty="VALUE"/>
</html:select></td>
<td  valign="top" class="tbcellBorder"><html:textarea name="chronicOpForm"  property="complaintCode" styleId="complaintCode" style="WIDTH:18em;height:5em"  title="Complaint Code"  onkeydown="validateBackSpace(event)" readonly="true" /></td>	
<td valign="top" class="tbcellBorder"><html:select name="chronicOpForm" property="patientComplaint" styleId="patientComplaint" style="WIDTH:17em;height:5em" title="Select Patient Complaint" multiple="multiple" onchange="getPatComplaintCode()" onmouseover="addTooltip('patientComplaint')">
    </html:select>
 </td>
 <td  valign="top" class="tbcellBorder"><html:textarea name="chronicOpForm"  property="patComplaintCode" styleId="patComplaintCode" style="WIDTH:18em;height:5em" title="Patient Complaint Code" onkeydown="validateBackSpace(event)" readonly="true" /></td>	
</tr>
<tr>
<td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HistoryOfPresentIllness"/></b> <font class="onlyAp" color="red">*</font></td>
<td colspan="2" valign="top" rowspan="3"  class="tbcellBorder">
	<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.ExaminationFindings"/></b> <font class="onlyAp" color="red">*</font></legend>
		<table width="100%">
		<logic:iterate id="examinFnds" name="examinFndgsList">
		 <tr><td width="60%">&nbsp;&nbsp;
 		 <html:multibox name="chronicOpForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       		<bean:write name="examinFnds" property="VALUE"/></td>
       		<td id="<bean:write name="examinFnds" property='ID'/>" width="39%"></td>
       		<td width="1%">
       		<input type="hidden" name="<bean:write name='examinFnds' property='VALUE'/>" id="<bean:write name='examinFnds' property='ID'/>h" value="<bean:write name='examinFnds' property='LVL'/>"/></td>
       		</tr>
       		</logic:iterate>
       </table>    
    </fieldset>
 </td>
</tr>
<tr>
<td valign="top" colspan="2"  class="tbcellBorder">
<html:textarea name="chronicOpForm" property="presentHistory" styleId="presentHistory"  style="WIDTH:38.5em;height:5em" disabled="true" title="Enter History Of Present Illness" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpaces(this,'History Of Present Illness')" />
</td>
</tr>
<tr><td colspan="2" class="tbcellBorder">
 <fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.PersonalHistory"/></b> <font class="onlyAp" color="red">*</font></legend>
 		<table width="100%" >
	 <logic:iterate id="phistory" name="personalHistoryList">
	 <tr><td width="33%">
 		 <html:multibox name="chronicOpForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
      		 <bean:write name="phistory" property="ID"/>
       	</html:multibox>
       		<bean:write name="phistory" property="VALUE"/></td>
       		<td id="<bean:write name="phistory" property='ID'/>" width="59%" height="1em"></td>
       		<td width="1%">
       		<input type="hidden" name="<bean:write name='phistory' property='VALUE'/>" id="<bean:write name='phistory' property='ID'/>p"/></td>
       		</tr>
      </logic:iterate>
      <tr><td colspan="3">&nbsp;</td></tr>
     <!--  <tr>
      <td  id="habitsTd" colspan="3">
      </td></tr> -->
      </table>
    </fieldset>
</td></tr>
<tr>
<td colspan="2" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.PastHistory"/></b> </legend>
 		<table width="100%">
	 <logic:iterate id="psthistory" name="pastHistoryList" indexId="cnt">
	 <tr><td width="50%">&nbsp;&nbsp;
 		 <html:multibox name="chronicOpForm"  property="pastHistory" styleId="pastHistory" onclick="getOtherText(this)" title="History Of Past Illness">
      		 <bean:write name="psthistory" property="ID"/>
       	</html:multibox>
       		<bean:write name="psthistory" property="VALUE"/></td>
       		<td id="<bean:write name="psthistory" property='ID'/>" width="60%"></td>
		</tr>
      </logic:iterate>
      
      </table>
    </fieldset>
</td>
<td colspan="2" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.FamilyHistory"/></b> </legend>
 		<table width="100%">
	 <logic:iterate id="fhistory" name="familyHistoryList">
	 <tr><td width="40%">&nbsp;&nbsp;
 		 <html:multibox name="chronicOpForm"  property="familyHistory" styleId="familyHistory" onclick="getOtherText(this)">
      		 <bean:write name="fhistory" property="ID"/>
       	</html:multibox>
       		<bean:write name="fhistory" property="VALUE"/></td>
       		<td id="<bean:write name="fhistory" property='ID'/>" width="30%"></td>
       		</tr>
       </logic:iterate>
       </table>
    </fieldset>
</td>
</tr>

</table>
</div></div></div></div>
</td></tr>




<tr><td colspan="4" class="labelheading1 tbcellCss">
<!--<table width="80%">
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



--><tr><td  id="prescriptionData" colspan="4">

</td></tr>



<!--chronic op therapies block-->

<tr><td  id="ChronicOPTherapy" colspan="4">
<table width="100%">
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
<html:select name="chronicOpForm" property="opPkgName" styleId="opPkgName" style="width:17em;" title="Select OP Package Name" onchange="validateChronicOpPkg();" onmouseover="addTooltip('opPkgName')"  >
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="opCategoryList" labelProperty="VALUE"/>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="opPkgCode" styleId="opPkgCode" title="OP Package Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>

<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="opIcdName" styleId="opIcdName" style="width:17em;" title="Select ICD Name" onchange="getOpIcdCode()" onmouseover="addTooltip('opIcdName')" >
		
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="opIcdCode" styleId="opIcdCode" title="ICD Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td colspan="2">&nbsp;</td>
</tr>
<tr><td><br></td></tr>
<tr>
<td colspan="4" align="center">
<button type="button" class="btn btn-primary" onclick="javascript:addChronicPackage('addNew');" id="addPackage" data-toggle="tooltip" data-placement="bottom" title="Click Here To Add Chronic Package">Add Package&nbsp;<span class="glyphicon glyphicon-plus-sign"></span></button></td>
</tr>

<tr><td><br></td></tr>

<tr>
<td  colspan="4" id="packageDiv" style="display:none">
<table style="width:100%;" id="pckgTable">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b>Package Name</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Package Code</b></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Category Name</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Category Code</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Action</b></td>

</tr>

<tr id="packageDivRow">
<td class="tbcellCss"><bean:write name="chronicOpForm" property="opPkgName"></bean:write></td>
<td class="tbcellCss"><bean:write name="chronicOpForm" property="opPkgCode"></bean:write></td>
<td class="tbcellCss"><bean:write name="chronicOpForm" property="opCatName"></bean:write></td>
<td class="tbcellCss"><bean:write name="chronicOpForm" property="opCatCode"></bean:write></td>
<td class="tbcellCss"><button type="button" class="btn btn-danger"  onclick="deleteChronicPackage();" name='+document.getElementById("drugName").value+' id='+document.getElementById("drugName").value+'>Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button></td>
</tr>


</table>
</td>
</tr>


<!--investigations block-->


<tr><td colspan="4"><b><fmt:message key="EHF.GenInvestigations"/></b> <font color="red">*&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4"><table width="100%">
    <c:if test="${hospScheme eq 'CD202' }">
    
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="O"  title="Click Here to Add other Investigations" styleId="invOthers" onchange="fn_enableOthers();" name="chronicOpForm" property="investOthers" ><b>&nbsp;Other Investigations</b></html:checkbox>
        </td>
     </c:if>
<tr>
<td class="labelheading1 tbcellCss" width="25%" id="InvBlockName"><b>Investigation Block Name</b><font color="red">*</font></td>
<td class="labelheading1 tbcellCss" width="25%" id="InvName"><b>Investigation Name</b><font color="red">*</font></td>
<td width="50%">&nbsp;</td></tr>
<tr>
<td class="tbcellBorder" id="InvBlckLst">
<html:select name="chronicOpForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" onchange="getChronicGenInvestigation();">
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
       
    </html:select></td>
    <td class="tbcellBorder" id="invLst"><html:select name="chronicOpForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 17em;"  onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
    </tr>
    
        <tr>
   
     <td class="labelheading1 tbcellCss" width="25%" Id="otherInvNameHead" style="display:none"><b>Other Investigation Name</b></td>
     </tr>
     <tr>
     <td class="tbCellBorder"><html:text name="chronicOpForm" property="otherInvName" styleId="otherInvName" style="display:none;width:97%" styleClass="otherFields" onchange="javascript:check_maxLength('otherInvName','200')"></html:text></td>
  </tr>
    <tr><td><br></td></tr>
<tr>    
<td  align="center"><button type="button" class="btn btn-primary"   onclick="confirmPatientTypeReset()"; data-toggle="tooltip" data-placement=bottom title="Click Here To Add Investigations">Add Tests&nbsp;<span class="glyphicon glyphicon-plus-sign"></span></button></td>



<c:choose>
<c:when test="${hospScheme eq 'CD202' }">
<td  align="center"><button type="button" class="btn btn-warning"  id="saveDTRS"  onclick="javascript:fn_saveDetailsWithoutMandate('SaveDTRS')"; data-toggle="tooltip" data-placement="bottom" title="Click Here To Save and Generate DTRS Form">Save and Generate DTRS &nbsp;<span class="glyphicon glyphicon-save"></span></button></td>
</c:when>
<c:otherwise>
<td  align="center"><button type="button" class="btn btn-warning"  id="saveDTRS"  onclick="javascript:fn_savePatientDetails('SaveDTRS')"; data-toggle="tooltip" data-placement="bottom" title="Click Here To Save and Generate DTRS Form">Save and Generate DTRS &nbsp;<span class="glyphicon glyphicon-save"></span></button></td>
</c:otherwise>
</c:choose>



<td id="dtrsTd" style="display:none;margin-left:10%" align="left"><a href="javascript:generateDtrsPrint();"><b>DTRS Print Form</b></a>
</tr>
</table>
</td>
</tr></table></td></tr>

</table>




 <table  width="100%"  id="genTestTableID" style="display:none" border="1">
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
		<script>
		
		var rowNo=2;</script>
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
       	<td width="25%"><a href="javascript:fn_openAtachment('<bean:write name="gnInvlst" property="LVL" />','<bean:write name="gnInvlst" property="LVL" />');">View</a></td>
		</logic:notEmpty>
		<script>rowNo++;</script>
		
       	<td width="20%">
       	<button type="button" class="btn btn-danger"    id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:confirmRemoveChronicInvest(this,'geninvestigations','<bean:write name="gnInvlst" property="ID" />');">Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button>
       	
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
   </table>
</td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTable" style="display:none" border="1">
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr> 
  </table>
</td></tr>

<!--prescription  block-->
<td  id="ChronicOPPrescription" colspan="4">
<table width="100%" border="0">
<tr>

<td width="25%" >
<legend class="legendStyle"><b><fmt:message key="EHF.Prescription"/></b> </legend></td>
</tr>
<tr><td colspan="4" id="existDrugsHead" style="display:none" class="labelheading1 tbcellCss"><font size="2px">Existing Drugs</font></td></tr>
<tr><td>
<table  width="100%"  id="existDrugs" style="display:none" border="1">
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
<!--<tr>
<td width="25%" class="labelheading1 tbcellCss"><b>Main Group Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Main Group Code</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Therapeutic Main Group Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Therapeutic Main Group Code</b> <font color="red">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="drugTypeCode" styleId="drugTypeCode" style="width:17em;" title="Select Drug type" onchange="getDrugSubTypeList()" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="drugCode" styleId="drugCode" title="Drug Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="drugSubTypeName" styleId="drugSubTypeName" style="width:17em;" title="Select Drug Sub type" onchange="getPharSubGrpLst()" onmouseover="addTooltip('drugSubTypeName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="drugSubTypeCode" styleId="drugSubTypeCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td width="25%" class="labelheading1 tbcellCss"><b>Pharmacological SubGroup Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Pharmacological SubGroup Code</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Chemical SubGroup Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b>Chemical SubGroup Code</b> <font color="red">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="pSubGrpName" styleId="pSubGrpName" style="width:17em;" title="Select Pharmacological SubGroup " onchange="getChemicalSubGrp()" onmouseover="addTooltip('pSubGrpName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="pSubGrpCode" styleId="pSubGrpCode" title="Pharmacological SubGroup Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="cSubGrpName" styleId="cSubGrpName" style="width:17em;" title="Chemical SubGroup" onchange="getDrugNameList()" onmouseover="addTooltip('cSubGrpName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="cSubGrpCode" styleId="cSubGrpCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>-->

<tr>
<td class="labelheading1 tbcellCss"><b>Drug Name</b> <font class="onlyAp" color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b>Drug Code</b> <font class="onlyAp" color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b> <font class="onlyAp" color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b> <font class="onlyAp" color="red">*</font></td>
<!--  <td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b> <font color="red">*</font></td> -->  
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="drugName" styleId="drugName" style="width:17em;" title="Select Drug Name" onchange="getRouteTypeList();" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>  
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm"  property="asriDrugCode" styleId="asriDrugCode" title="Drug Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder">
<html:select name="chronicOpForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="chronicOpForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:17em" onchange="validateMedicationPeriod();validateNumber('Medication Period',this)" title="Enter Medication Period"/></td></tr>
<!--  
<tr>
<td class="labelheading1 tbcellCss"><b>Batch Number</b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b>Expiry date</b> <font color="red">*</font></td></tr>
<tr>



<td class="tbcellBorder"><html:text name="chronicOpForm" property="batchNo" styleId="batchNo"  style="WIDTH:17em" onchange="validateBatchNum('Drug Batch Number',this)" title="Enter Batch Number"/></td>

<td width="100%" class="tbcellBorder input-group date">

<html:text name="chronicOpForm" property="expiryDt" styleId="expiryDt" style="width:14em;" title="Select Expiry Date"   onkeydown="validateBackSpace(event)" readonly="true"/>

                     
                
</td> 
</tr>

-->

<tr >
<td colspan="4" align="center">
<button type="button" class="btn btn-primary"   name="addDrug"  id="addDrug" onclick="addChronicDrugs()" data-toggle="tooltip" data-placement="bottom" title="Click Here To Add Chronic Drugs">Add Drugs&nbsp;<span class="glyphicon glyphicon-plus-sign"></span></button>
<button type="button" class="btn btn-warning"   name="prescription"  id="prescription" onclick="fn_prescription()" data-toggle="tooltip" data-placement="bottom" title="Click Here To Print Prescription">Print Prescription&nbsp;<span class="glyphicon glyphicon-print"></span></button>
</td></tr>
<!-- <td class="tbcellBorder">
 <table >
<tr><td width='50%'><html:select name="chronicOpForm" property="routeType" styleId="routeType" style="width:8em;" title="Select Route Type" onchange="getRouteList()" onmouseover="addTooltip('routeType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="routeTypeLst" labelProperty="VALUE"/>
		
</html:select></td>
<td width='50%'><html:select name="chronicOpForm" property="route" styleId="route" style="width:8em;" title="Select Route "  onmouseover="addTooltip('route')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		
</html:select></td> </tr>
</table></td>
<td class="tbcellBorder">   
<table >
<tr><td width='50%'><html:select name="chronicOpForm" property="strengthType" styleId="strengthType" style="width:8em;" title="Select Strength Type" onchange="getStrengthList()" onmouseover="addTooltip('strengthType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="strengthTypeLst" labelProperty="VALUE"/>		
	
</html:select></td>
<td width='50%'><html:select name="chronicOpForm" property="strength" styleId="strength" style="width:8em;" title="Select Strength "  onmouseover="addTooltip('strength')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td> </tr>
</table></td>-->
</tr>
<tr>

<td colspan="2">&nbsp;</td>
</tr>

<tr><td colspan="4" class="tbcellBorder"> 
<div id='drugsContent' style="width:980px;overflow:auto; overflow-y:hidden"> 
  <table  width="100%"  align="center" id="drugsTable" style="display:none" border="1">
      <tr>  
      	<td width="5%"><b><fmt:message key="EHF.SNO"/></b></td>        
      
		<td width="10%"><b>Drug Name</b></td>
         <!--<td width="5%"><b>Route Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="5%"><b>Strength Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Strength"/></b></td>-->
        <td width="10%"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="15%"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
      <!--   <td width="10%"><b><fmt:message key="EHF.BatchNo"/></b></td>
        <td width="10%"><b><fmt:message key="EHF.ExpiryDt"/></b></td>   -->
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
       	 <!--<td width="5%"><bean:write name="drugLst" property="routeTypeName" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="routeName" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="strengthTypeName" /></td>
       	<td width="10%"><bean:write name="drugLst" property="strengthName" /></td> --> 
       	<td width="10%"><bean:write name="drugLst" property="dosage" /></td>  
       	<td width="15%"><bean:write name="drugLst" property="medicationPeriod" /></td> 
    <!-- 	<td width="5%"><bean:write name="drugLst" property="batchNo" /></td>  
       	<td width="5%"><bean:write name="drugLst"  property="expiryDt" /></td>    -->   
       	<td width="5%">
       	<button type="button" class="btn btn-danger"   name=<bean:write name='drugLst' property='drugCode' /> id=<bean:write name='drugLst' property='drugCode' /> onclick="javascript:confirmRemoveRow(this,'drug');";>Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button>
       </td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>        
        </table></div>
</td></tr>
</table></td>
<tr><td id="OPDoctor" colspan="4">
<table width="100%">
<tr>
<td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font></td>
<td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font></td>

</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="chronicOpForm" property="diagnosedBy" styleId="diagnosedBy" style="width:17em" onchange="fn_getDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('diagnosedBy')"> 
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="MEDCO"><fmt:message key="EHF.MEDCO"/></html:option>
<html:option value="InHouseDoctor"><fmt:message key="EHF.InHouseDoctor"/></html:option>
<html:option value="Consultant"><fmt:message key="EHF.Consultant"/></html:option>
<html:option value="DutyDoctor"><fmt:message key="EHF.DutyDoctor"/></html:option>
</html:select>
</td>
<td valign="top" id="docNameList" class="tbcellBorder">
<html:select name="chronicOpForm" property="doctorName" styleId="doctorName" style="WIDTH:17em"  title="Select Doctor Name" onmouseover="addTooltip('doctorName')"> <!-- onchange="fn_getDoctorsDetails()" -->
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td valign="top" id="docNametext" style="display:none"  class="labelheading1 tbcellCss"><html:text name="chronicOpForm" property="otherDocName" styleId="otherDocName" maxlength="50" style="WIDTH:17em" onchange="checkAlphaSpace('otherDocName','Doctor Name')" title="Enter Doctor Name"/></td>
</tr>
<tr><td colspan="4" class="tbcellBorder">			
	<div id="doctorDataDiv"> </div></td>
</tr>
<tr id="doctorData" style="display:none">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Doctor.RegistrationNo"/></b><font color="red">*</font>&nbsp;
<html:text name="chronicOpForm" property="docRegNo" styleId="docRegNo" maxlength="20" style="WIDTH:9em" onchange="validateAlphaNum('Doctor Registration No',this,'Registration No')" title="Enter Doctor Registration No"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.doctor.Qualification"/></b><font color="red">*</font>&nbsp;
<html:text name="chronicOpForm" property="docQual" styleId="docQual" maxlength="30" style="WIDTH:9em" onchange="checkAlpha('docQual','Doctor Qualification','Qualification')" title="Enter Doctor Qualification"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b><font color="red">*</font>&nbsp;
<html:text name="chronicOpForm" property="docMobileNo" styleId="docMobileNo" maxlength="10" onchange="validateMobile(this);" style="WIDTH:9em" title="Enter Doctor Contact No"/></td>
</tr>
</table></td></tr>
<tr><td colspan="4">

</td></tr>
<tr><td>&nbsp;</td></tr>

<tr><td colspan="4">
<table width="100%">
<tr>
<td style="height: 1em; font-size:small;" nowrap="nowrap" width=20%>
		<font color="red"><fmt:message key="EHF.MandatoryFields"/> <br /></font>
</td>


<td align="center" colspan="3"> 

<c:choose>
<c:when test="${hospScheme eq 'CD202' }">
<button type="button" class="btn btn-primary"    id="Submit" onclick="javascript:fn_saveDetailsWithoutMandate('submit')"; data-toggle="tooltip" data-placement="left" title="Click Here To Submit">Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
<button type="button" class="btn btn-primary"    id="save" onclick="javascript:fn_saveDetailsWithoutMandate('save')"; data-toggle="tooltip" data-placement="bottom" title="Click Here To Save">Save&nbsp;<span class="glyphicon glyphicon-save"></span></button>
</c:when>
<c:otherwise>
<button type="button" class="btn btn-primary"    id="Submit" onclick="javascript:fn_savePatientDetails('submit')"; data-toggle="tooltip" data-placement="left" title="Click Here To Submit">Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
<button type="button" class="btn btn-primary"    id="save" onclick="javascript:fn_saveDetails()"; data-toggle="tooltip" data-placement="bottom" title="Click Here To Save">Save&nbsp;<span class="glyphicon glyphicon-save"></span></button>
</c:otherwise>
</c:choose>


<button type="button" class="btn btn-primary"    id="Reset" onclick="javascript:fn_resetAll()"; data-toggle="tooltip" data-placement="bottom" title="Click Here To Reset">Reset&nbsp;<span class="glyphicon glyphicon-minus-sign"></span></button>
<button type="button" class="btn btn-warning"    id="cancel" onclick="javascript:fn_cancel()"; data-toggle="tooltip" data-placement="right" title="Click Here To Cancel Chronic Case">Cancel&nbsp;<span class="glyphicon glyphicon-remove"></span></button>

</td>

<td width="20%"></td>
</tr>
<tr>
<td style="height: 1em; font-size:small;" nowrap="nowrap" width=20%>
		<font color="red"><fmt:message key="EHF.submitEnable"/> <br /></font>
</td></tr>
</table>
</td></tr>
</table>

<div style="display:none">
<table width="80%">
<tr><td  width="15%"><b><fmt:message key="EHF.PatientType"/></b><font color="red">*</font></td>
<td id="patientType1" width="10%" ><html:radio name="chronicOpForm" property="patientType" value="IP" title="In Patient" styleId="patientType" disabled="true" onclick="enableIPOP()"/><b><fmt:message key="EHF.IP"/></b></td>
<td id="patientType2" width="10%"><html:radio name="chronicOpForm" property="patientType" value="OP" title="Out Patient" styleId="patientType" onclick="enableIPOP()"/><b><fmt:message key="EHF.OP"/></b></td>
<td id="patientType3" width="10%" class="labelheading1 tbcellCss"><html:radio name="chronicOpForm" property="patientType" value="ChronicOP" title="Chronic Out Patient" styleId="patientType" onclick="enableIPOP()"/><b><fmt:message key="EHF.ChronicOP"/></b></td>
<td  width="40%" class="labelheading1 tbcellCss"><button class="but"  type="button" id="saveDTRS" onclick="javascript:fn_savePatientDetails('SaveDTRS')">Save and Generate DTRS </button></td>
<td id="dtrsTd" width="15%"  class="labelheading1 tbcellCss" style="display:none">
<a href="javascript:generateDTRSPrint('<bean:write name="chronicOpForm" property="caseId" />','<bean:write name="chronicOpForm" property="hospitalId" />')"><b>DTRS Print Form</b></a>
</td>
</tr>
</table>
</div>



<div class="modal fade"   id="progressBar" >
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Please Wait...</span>
    </div>
  </div>
</div>
</div></div></div>


<html:hidden name="chronicOpForm" property="caseId" styleId="caseId"/>
<html:hidden name="chronicOpForm" property="testsCount" styleId="testsCount"/> 
<html:hidden name="chronicOpForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="chronicOpForm" property="hospitalId" styleId="hospitalId"/>
<input type="hidden" name="investigationsSel" id="investigationsSel">
<html:hidden name="chronicOpForm" property="personalHistVal" styleId="personalHistVal"/>
<html:hidden name="chronicOpForm" property="examFndsVal" styleId="examFndsVal"/>
<html:hidden name="chronicOpForm" property="speciality" styleId="speciality"/>
<html:hidden name="chronicOpForm" property="otherDocDetailsList" styleId="otherDocDetailsList"/>
<html:hidden name="chronicOpForm" property="drugs" styleId="drugs"/>
<html:hidden name="chronicOpForm" property="symptoms" styleId="symptoms"/>
<html:hidden name="chronicOpForm" property="hosptype" styleId="hosptype"/>
<html:hidden name="chronicOpForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="chronicOpForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="chronicOpForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="chronicOpForm" property="therapyType" styleId="therapyType"/>
<html:hidden name="chronicOpForm" property="therapyCategory" styleId="therapyCategory"/>
<html:hidden name="chronicOpForm" property="therapy" styleId="therapy"/>
<html:hidden name="chronicOpForm" property="personalHist" styleId="personalHist"/>
<html:hidden name="chronicOpForm" property="gender" styleId="gender"/>
<html:hidden name="chronicOpForm" property="years" styleId="years"/>
<html:hidden name="chronicOpForm" property="months" styleId="months"/>
<html:hidden name="chronicOpForm" property="days" styleId="days"/>
<html:hidden name="chronicOpForm" property="scheme" styleId="scheme"/>
<html:hidden name="chronicOpForm" property="addPckgFlg" styleId="addPckgFlg"/>
<html:hidden name="chronicOpForm" property="packageDrugAmt" styleId="packageDrugAmt"/>
<html:hidden name="chronicOpForm" property="chronicPackageCode" styleId="chronicPackageCode"/>
<html:hidden name="chronicOpForm" property="chronicCatCode" styleId="chronicCatCode"/>
<html:hidden name="chronicOpForm" property="chronicPkgList" styleId="chronicPkgList"/>
<html:hidden name="chronicOpForm" property="chronicPkgCount" styleId="chronicPkgCount"/>
<html:hidden name="chronicOpForm" property="checkType" styleId="checkType" value="save"/>
<html:hidden name="chronicOpForm" property="otherInvestCount" styleId="otherInvestCount"/>
<html:hidden name="chronicOpForm" property="otherInvestCount" styleId="chronicNo"/>
<html:hidden name="chronicOpForm" property="otherInvestCount" styleId="hospScheme"/>

<script>
var personalHistory=document.forms[0].personalHistory;

for(var i=0;i<personalHistory.length;i++)
    {
	if('${PatientOpList.personalHis}'==''){
		personalHistory[i].checked=true;
	      getSubListHistory(personalHistory[i],'NA');
	}
	else{
		if('${PatientOpList.personalHis}'.indexOf(personalHistory[i].value,0)!=-1){
	        personalHistory[i].checked=true;
            getSubListHistory(personalHistory[i],'NA'); 
	}
     }
    }
	var examinationFnd=document.forms[0].examinationFnd;
	for(var i=0;i<examinationFnd.length;i++)
	{
		if('${PatientOpList.examFindg}'==''){
			examinationFnd[i].checked=true;
			getSubFieldType(examinationFnd[i]);
		}
		else{
			if('${PatientOpList.examFindg}'.indexOf(examinationFnd[i].value,0)!=-1){
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]); 
		}
	     }
	}

/*function getChronicOPType()
{
	if(document.getElementById("hosptype").value!="G"||document.getElementById("hosptype").value=="G")
		{
		document.getElementById("patientType3").style.display="none";
		}
}*/
//getChronicOPType();
var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
	document.getElementById("presentHistory").attachEvent("onpaste",pasteIntercept);
	//document.getElementById("remarks").attachEvent("onpaste",pasteInterceptRemarks);
	//document.getElementById("opRemarks").attachEvent("onpaste",pasteInterceptOpRemarks);
	}
else if(browserName == "Netscape")
	{
	document.getElementById("presentHistory").addEventListener("paste", pasteIntercept, false);
	//document.getElementById("remarks").addEventListener("paste", pasteInterceptRemarks, false);
	//document.getElementById("opRemarks").addEventListener("paste", pasteInterceptOpRemarks, false);
	}
//Added to remove S4 speciality -Gynaecology and  Obstretics if the patient is male	


//Added to populate complaints 
var compCodes = document.getElementById("complaintCode").value.split("~");
var complaint=document.forms[0].complaints;
for (var x=0;x<complaint.length;x++)
{
	for(var j=0;j<compCodes.length;j++)
	{
		if(compCodes[j]==document.forms[0].complaints[x].value)
			document.forms[0].complaints[x].selected = true;                
	}
}

if(document.getElementById("complaintCode").value!=null && document.getElementById("complaintCode").value!="")
	getComplaintType("onLoad");

var pastHistory=document.forms[0].pastHistory;
var pastHistoryVal='${PatientOpList.pastIllness}'.split("~");
for(var i=0;i<pastHistory.length;i++)
    {
	for(var j=0;j<pastHistoryVal.length;j++)
	{
	if(pastHistory[i].value==pastHistoryVal[j])
	{
		pastHistory[i].checked=true;
			if(pastHistory[i].value=='PH11' || pastHistory[i].value=='PH8' || pastHistory[i].value=='PH12' || pastHistory[i].value=='PH10'){
			getOtherText(pastHistory[i]);
			if(pastHistory[i].value=='PH11' && '${PatientOpList.pastIllenesOthr}'!='')
				document.getElementById('pastHistryOthr').value='${PatientOpList.pastIllenesOthr}';
			if(pastHistory[i].value=='PH8' && '${PatientOpList.pastIllenesCancers}'!='')
				document.getElementById('pastHistryCancers').value='${PatientOpList.pastIllenesCancers}';
			if(pastHistory[i].value=='PH12' && '${PatientOpList.pastIllenesEndDis}'!='')
				document.getElementById('pastHistryEndDis').value='${PatientOpList.pastIllenesEndDis}';
			if(pastHistory[i].value=='PH10' && '${PatientOpList.pastIllenesSurg}'!='')
				document.getElementById('pastHistrySurg').value='${PatientOpList.pastIllenesSurg}';
			}
	}}}
var familyHistory=document.forms[0].familyHistory;
var famHistoryVal;
if('${PatientOpList.familyHis}'!='')
{
	famHistoryVal='${PatientOpList.familyHis}'.split("~");
	for(var i=0;i<familyHistory.length;i++)
    {
		for(var j=0;j<famHistoryVal.length;j++)
		{
			if(familyHistory[i].value==famHistoryVal[j])
			{
				familyHistory[i].checked=true;
				if(familyHistory[i].value=='FH11'){	
					getOtherText(familyHistory[i]);
					if('${PatientOpList.familyHistoryOthr}'!='')
						document.getElementById('familyHistoryOthr').value='${PatientOpList.familyHistoryOthr}';
				}
			}
		}
	} 
}

if('${PatientOpList.lstPerHis}'!=null && '${PatientOpList.lstPerHis}'.indexOf("PR5.1",0)!=-1)
	{ 
		var KnownAllg='<table width="100%" border="1"><tr><td>'+
	'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
	'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
	'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
	'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
	'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
	'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
    
	document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
	}

	if('${PatientOpList.lstPerHis}'!=null && '${PatientOpList.lstPerHis}'.indexOf("PR6.1",0)!=-1)
{
var personalHabits='<table width="100%" border="1"><tr><td>'+
'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
'<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
'<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
	'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
'<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
'<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
'<div id="smokingTd" style="display:none">'+
'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
'<tr><td>Drug Use:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
'<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
'<tr><td>Betel leaf:<input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="betelLeaf" id="betelLeaf" value="No" title="No"/>No</td></tr></table>';


document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
}
var addition='${PatientOpList.test}';var additionKnown='${PatientOpList.testKnown}';
	addition=addition.replace("PR6.1(","");
	additionKnown=additionKnown.replace("PR5.1,","");
	var additionList = addition.split(",");
	var addKnownList = additionKnown.split(",");
	if(addKnownList.length>0){
		
		for(var i = 0; i<addKnownList.length;i++)
	    {	            			    
			var addtn = addKnownList[i].split("$");	
			           		        
			if(addtn[0]=='AllMed'){
				var spitedY = addtn[1].split("(");	
				if(spitedY[0]=='AllMedYes'){	               						
					document.forms[0].AllMed[0].checked='checked';
					document.getElementById("AllMedDiv").style.display='block';
					if(spitedY.length>1){
					var valueY = spitedY[1].split("@");					
					document.getElementById("AllMedRemrk").value=valueY[1];
					}
				}
				else if(addtn[1]=='AllMedNo'){
					document.forms[0].AllMed[1].checked='checked';
			}
		   }
			if(addtn[0]=='AllSub'){
				var spitedY = addtn[1].split("(");	
				if(spitedY[0]=='AllSubYes'){
					
					document.forms[0].AllSub[0].checked='checked';
					document.getElementById("AllSubDiv").style.display='block';
					if(spitedY.length>1){
						var valueY = spitedY[1].split("@");					
						document.getElementById("AllSubRemrk").value=valueY[1];
						}
				}
				else if(addtn[1]=='AllSubNo'){
					document.forms[0].AllSub[1].checked='checked';
			}
		   }
	}
	}
	if(additionList.length>0)
	{
		for(var i = 0; i<additionList.length;i++)
	    {	
		
		    var addtn = additionList[i].split("$");
		    if(addtn[0]=='Alcohol')
		    	{if(addtn[1]=='Regular')
		    		document.forms[0].alcohol[0].checked='checked';
		    	else if (addtn[1]=='Occasional')
		    		document.forms[0].alcohol[1].checked='checked';
		    	else if (addtn[1]=='Teetotaler')
		    		document.forms[0].alcohol[2].checked='checked';
		    	}
		    else if(addtn[0]=='Tobacco')
			    {
		    	var tabacoLst = addtn[1].split("(");
		    	
		    	if(tabacoLst[0]=='Snuff')
		    		document.forms[0].tobacco[0].checked='checked';
		    	else if (tabacoLst[0]=='Chewable')
		    		document.forms[0].tobacco[1].checked='checked';
		    	else if (tabacoLst[0]=='Smoking')
			    	{
		    		document.forms[0].tobacco[2].checked='checked';
		    		tabacoLst[1] = tabacoLst[1].replace(")","");
		    		
		    		document.getElementById("smokingTd").style.display='block';
		    		
		    		var smokSub = tabacoLst[1].split("*");
		    	
		    		if(smokSub.length>0)
			    		{
	                       for(var j=0;j<smokSub.length;j++){
	                    	   
	                    	  var smokeVal= smokSub[j].split("@");
	                    	  
	                    	  if(smokeVal[0]=='PackNo')
	                    		  document.forms[0].packNo.value=smokeVal[1];
	                    	  else
	                    		  document.forms[0].smokeYears.value=smokeVal[1];
	                           } 
			    		}
			    	}
	             }
		    else if(addtn[0]=='DrugUse')
			    {
	              if(addtn[1]=='Yes')
	            	  document.forms[0].drugUse[0].checked='checked';
	              else  if(addtn[1]=='No')
	            	  document.forms[0].drugUse[1].checked='checked';
	            }
		    else if(addtn[0]=='BetelNut')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].betelNut[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].betelNut[1].checked='checked';
		    }
		    else if(addtn[0]=='BetelLeaf')
		    {
		    	addtn[1] = addtn[1].replace(")","");
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].betelLeaf[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].betelLeaf[1].checked='checked';
		    }
	    }
	}
		
	if('${PatientOpList.height}'!='NA' && '${PatientOpList.height}'!=''){
		document.forms[0].GE1.value='${PatientOpList.height}';
	
		heightVar='${PatientOpList.height}';
	}
	if('${PatientOpList.weight}'!='NA' && '${PatientOpList.weight}'!=''){
		document.forms[0].GE2.value='${PatientOpList.weight}';
		weightVar='${PatientOpList.weight}';
		}
	if('${PatientOpList.bmi}'!='NA' && '${PatientOpList.bmi}'!='')
		document.forms[0].GE3.value='${PatientOpList.bmi}';
	if('${PatientOpList.pallor}'!=''){
			if('${PatientOpList.pallor}'=='Y')
	      	  document.forms[0].GE4[0].checked='checked';
	        else  if('${PatientOpList.pallor}'=='N')
	      	  document.forms[0].GE4[1].checked='checked';
		}
	if('${PatientOpList.cyanosis}'!=''){
		if('${PatientOpList.cyanosis}'=='Y')
      	  document.forms[0].GE5[0].checked='checked';
        else  if('${PatientOpList.cyanosis}'=='N')
      	  document.forms[0].GE5[1].checked='checked';
	}
	if('${PatientOpList.clubbingOfFingers}'!=''){
		if('${PatientOpList.clubbingOfFingers}'=='Y')
      	  document.forms[0].GE6[0].checked='checked';
        else  if('${PatientOpList.clubbingOfFingers}'=='N')
      	  document.forms[0].GE6[1].checked='checked';
	}
	if('${PatientOpList.lymphadenopathy}'!=''){
		if('${PatientOpList.lymphadenopathy}'=='Y')
      	  document.forms[0].GE7[0].checked='checked';
        else  if('${PatientOpList.lymphadenopathy}'=='N')
      	  document.forms[0].GE7[1].checked='checked';
	}
	if('${PatientOpList.edema}'!=''){
		if('${PatientOpList.edema}'=='Y')
      	  document.forms[0].GE8[0].checked='checked';
        else  if('${PatientOpList.edema}'=='N')
      	  document.forms[0].GE8[1].checked='checked';
	}
	if('${PatientOpList.malNutrition}'!=''){
		if('${PatientOpList.malNutrition}'=='Y')
      	  document.forms[0].GE9[0].checked='checked';
        else  if('${PatientOpList.malNutrition}'=='N')
      	  document.forms[0].GE9[1].checked='checked';
	}
	if('${PatientOpList.dehydration}'!=''){
		if('${PatientOpList.dehydration}'=='Y'){
      	  document.forms[0].GE10[0].checked='checked';
      	  var examinField="<input type='radio' name='dehydrationY' id='dehydrationY' value='Mild' title='Mild'/>Mild<input type='radio'  name='dehydrationY' id='dehydrationY' value='Moderate' title='Moderate'/>Moderate<input type='radio'  name='dehydrationY' id='dehydrationY' value='Severe' title='Severe'/>Severe";
	      document.getElementById("DehydrationSub").innerHTML=examinField;
      	  
      	  if('${PatientOpList.dehydrationType}'!=''){
      		if('${PatientOpList.dehydrationType}'=='Mild')
      		document.forms[0].dehydrationY[0].checked='checked';
      		if('${PatientOpList.dehydrationType}'=='Moderate')
      		document.forms[0].dehydrationY[1].checked='checked';
      		if('${PatientOpList.dehydrationType}'=='Severe')
      		document.forms[0].dehydrationY[2].checked='checked';
          }
		}
        else  if('${PatientOpList.dehydration}'=='N')
      	  document.forms[0].GE10[1].checked='checked';
	}	
	if('${PatientOpList.temperature}'!='NA' && '${PatientOpList.temperature}'!=''){
		var temp = '${PatientOpList.temperature}';
		var tempType = temp.charAt(temp.length - 1);
		temp = temp.slice(0,str.length-1);
		document.forms[0].GE11.value=temp;	
		
		if(tempType=="C"){
			document.forms[0].temp[0].checked='checked';
			}
		else if(tempType=="F")
			document.forms[0].temp[1].checked='checked';			
	}	
	if('${PatientOpList.pulseRate}'!='NA' && '${PatientOpList.pulseRate}'!='')
		document.forms[0].GE12.value='${PatientOpList.pulseRate}';
	if('${PatientOpList.respirationRate}'!='NA' && '${PatientOpList.respirationRate}'!='')
		document.forms[0].GE13.value='${PatientOpList.respirationRate}';
	if('${PatientOpList.bpLmt}'!='NA' && '${PatientOpList.bpLmt}'!=''){
		var bpLmt='${PatientOpList.bpLmt}'.split("/");
		document.forms[0].GE14.value=bpLmt[0];
		document.forms[0].BP1.value=bpLmt[1];	
	}	
	if('${PatientOpList.bpRmt}'!='NA' && '${PatientOpList.bpRmt}'!='')
		{var bpRmt='${PatientOpList.bpRmt}'.split("/");

		if(document.getElementById("GE15"))
		{
		document.forms[0].GE15.value=bpRmt[0];
		document.forms[0].BP2.value=bpRmt[1];	}}

		
		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
		document.getElementById("genTestTableID").style.display="";
		genOldList='${genInvestLst}'.split("@");
		genOldList.splice(genOldList.length-1,1);
		if(genOldList.length>0)
			document.forms[0].patientType[0].disabled=false;
		/*genInventCount=genInventList.length-1;*/
		}
		
		if('${PatientOpList.ipOpFlag}'!=null){
			if('${PatientOpList.ipOpFlag}'=='IP'){
			document.forms[0].patientType[0].disabled=false;
			document.forms[0].patientType[0].checked=true;
			document.getElementById('IPHead2').style.display='';
			document.getElementById("diagnosisData").style.display="";
			getDiagDtlsOnload();
			if ('${PatientOpList.docType}'!=null){
				setTimeout(function(){fn_getIPDoctorsList();}, 1999);}
			
			if('${invetLst}'!='')
    		{
    			document.getElementById("categoryTableID").style.display="";
    			specOld='${invetLst}'.split("@");
    			specOld.splice(specOld.length-1,1);    			
    			if(specOld.length>0){
                    for(var i=0;i<specOld.length;i++){
                    	var inSpec = specOld[i].split("~");
                    	medOrSur=inSpec[0].substr(0,1);
                    }
    				
        		}  
    			/*catCount=spec.length-1;*/
    		}
			}
	



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
						    
						}
					}, 1999);

					var invFlag="${invSize}";


					if('${PatientOpList.opPkgCode}'!=null && '${PatientOpList.opPkgCode}'!='' && '${PatientOpList.opPkgCode}'!='-1')
					{
						if('${PatientOpList.opIcdCode}'!=null && '${PatientOpList.opIcdCode}'!='' && '${PatientOpList.opIcdCode}'!='-1')
						{
							$(document).ready(function() {

								setTimeout( function() {
							    //addChronicPackage('onLoad');
							    document.getElementById("addPckgFlg").value="Y";
							    document.getElementById("packageDiv").style.display="";
								},0);
							    
							    });
						}}
				   /*if(invFlag>0)
					   {
				 

		$(document).ready(function() {
		$('#opPkgName').attr('disabled', true); });

		//document.getElementById("opPkgName").disabled=true;
						   
					   }	*/
				    
				}


			if ('${PatientOpList.ipOpFlag}'=='chronicOP')
        	{
    		
        	gettherapyDtlsOnload();
        	//loadPackageDtlsOnload();
    		if ('${PatientOpList.docType}'!=null){
				setTimeout(function(){fn_getDoctorsList();}, 1999);}
    		if('${drugsLst}'!='')
    		{
    			document.getElementById("drugsTable").style.display="";
    			drugs='${drugsLst}'.split("#");
    			drugCount=drugs.length-1;
    		}
    		else
    		{
    			document.getElementById("prescription").disabled=true;
    			document.getElementById("prescription").className='butdisable';
    		}	    		
    		
        	}
				


		setTimeout(function()
				{
			enable_ChronicDtrsform();
				},5000);

		}
        	


		








			
			



		

</script>
<%--</logic:equal>--%>
</logic:equal> 
</logic:iterate> 
</logic:notEmpty> 
</td></tr></table>
</form>
</div>
</body>
</fmt:bundle>
</html>