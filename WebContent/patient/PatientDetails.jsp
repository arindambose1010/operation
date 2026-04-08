<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	 <%@ include file="/common/include.jsp"%> 

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />


<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<link href="css/select2.min.css" rel="stylesheet">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
<!-- <LINK href="bootstrap/css/custombox.css" rel="stylesheet" type="text/css" media="screen"> -->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  

<script src="js/jquery.msgBox.js" type="text/javascript"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<!-- <script src="bootstrap/js/custombox.js"></script>
<script src="bootstrap/js/legacy.js"></script> -->
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/select2.min.js"></script>

<style type="text/css">
.btn
{
border-radius:20px;
}
.modal-header
{
background-color:#0286AD;
}
.btn:hover
{
border-radius:5px;
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

<style type="text/css">
*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}  
.bootbox-close-button
{
display:none;
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
body {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 12px;
line-height: 1.42857143;
color: #333;
background-color: #fff;
}

</style>
<script type="text/javascript">


var heightVar = null;
var weightVar = null;

$(document).ready(function()
		{
var schemeId='${schemeId}';
var hospType='${hospType}';
var hospId=document.getElementById("hospitalId").value;
var hospGovu = document.getElementById("hospGovu").value;
if(schemeId=='CD201')
$('.onlyAp').css('display','');
if(schemeId=='CD202')
{
	$('.onlyAp').css('display','none');
	if( document.forms[0].patientType[1].checked || hospType!='G')
	{
$('.onlyAp').css('display','');
		}



}

		if(hospId)
			$('.onlyAp').css('display','none');

			});


function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
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

$(function() {

    $( "#ipDate" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
      		showOn: "both", 
            buttonImage: "images/calend.gif",
            buttonText: "Calendar",
            buttonImageOnly: true,
            minDate: 0,
            maxDate: "+3M"
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
var consultDataList=new Array();
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

/*added by venkatesh for getting dental package"*/
/*added for checking whether used dental package is above 50000*/
function verifyUsedPackage()
{
	//document.getElementById("Save").disabled=true;
	//document.getElementById("Save").className='butdisable';
	

		document.getElementById("Save").disabled=true;
		//document.getElementById("Save").className='butdisable';
		document.getElementById("saveDTRS").disabled=true;
		//document.getElementById("saveDTRS").className='butdisable';
		document.getElementById("Reset").disabled=true;
		//document.getElementById("Reset").className='butdisable';
		
		/*Added by Srikalyan for Dental Changes related to TG.  
	*/
	if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
		{
			var localSchemeId=document.getElementById("scheme").value;
			if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
				{
				//(comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
				if(		(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
						(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
						{
							var lErrorMsg=checkDentalTGCond();
							if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' )
								{
									bootbox.alert(lErrorMsg);
									focusBox(document.getElementById('asriCatName'));
									document.getElementById("Save").disabled=false;
									document.getElementById("saveDTRS").disabled=false;
									document.getElementById("Reset").disabled=false;
									return false;
								}	
						}
						
				}								
		}
	/*End for Dental Changes related to TG.  
	*/
var patientId=document.getElementById("patientNo").value;;
var speciality=spec;
  
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
	 bootbox.alert("Your Browser Does Not Support XMLHTTP!");
	}





	 url = '/<%=context%>/patientDetails.do?actionFlag=verifyUtilizedPackage&patientId='+patientId+'&speciality='+speciality+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
		    	 //bootbox.alert(resultArray);
			        var resultArray = resultArray.split("*");
					if(resultArray[0]=="SessionExpired"){
			    		bootbox.alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null && resultArray[0]!="")
			        {	
                       //bootbox.alert(resultArray[0]);
			            var resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]",""); 
			            var resultArray2= resultArray1.split("~");
			                 
			          //  bootbox.alert(resultArray2[0]);
			           
			            var amount=resultArray2[1];
			            var alertFlag=resultArray2[2];
			            var caseBlock=resultArray2[3];
			            //bootbox.alert(caseBlock);
			            if(/*amount>=50000 &&*/ caseBlock=="true")
			            {
                          // bootbox.alert(caseBlock);
                          
                          document.getElementById("Save").disabled=false;
	     			   	  document.getElementById("Save").className='but';
		                  document.getElementById("saveDTRS").disabled=false;
		                  document.getElementById("saveDTRS").className='but';
		                  document.getElementById("Reset").disabled=false;
		                  document.getElementById("Reset").className='but';
                          
			            	document.getElementById("ICDProcName").value="-1";
				            return false;
                           
			            }
			            else
							fn_saveDetails();
				            
			            
			     
			           
			        }

			        else
			        {
			        	fn_saveDetails();
			        }
			    		}
		    }

		}
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	      
	
}
/*added by venkatesh*/

/*added for checking dental procedure validity (1 year,5 years,lifetime) */

function validateDentalProc()
{

var patientId=document.getElementById("patientNo").value;
var icdProcCode=document.getElementById("ICDProcName").value;
var asriCatCode=document.getElementById("asriCatCode").value;

if(asriCatCode=="-1" || icdProcCode=="-1")
{
	return false;
}
if(asriCatCode=='S18')
{

  
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
	 bootbox.alert("Your Browser Does Not Support XMLHTTP!");
	}





	 url = '/<%=context%>/patientDetails.do?actionFlag=getDentalProcValidity&patientId='+patientId+'&procCode='+icdProcCode+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
		    	 //bootbox.alert(resultArray);
			        var resultArray = resultArray.split("*");
					if(resultArray[0]=="SessionExpired"){
			    		bootbox.alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null && resultArray[0]!="")
			        {	
                       //bootbox.alert(resultArray[0]);
			            var resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");        
			            bootbox.alert(resultArray1);
			            document.getElementById("ICDProcName").value="-1";
			            document.getElementById("ICDProcCode").value=" ";
			            
			            return false;
			          
			          
			        }
			        else
			        {
			        	validateDentalWorkflowCases();
			        }

			        
			    		}
		    }

		}
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	      
}
}

function fn_saveDetailsWithoutMandate(checkType){
	document.getElementById("checkType").value=checkType;
	fn_saveDetails();
}
function fn_saveDetails(){
	var patId=document.getElementById("patientNo").value;
	document.getElementById("drugs").value=drugs;
	document.getElementById("symptoms").value=symptoms;
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

	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	//verifyUsedPackage();
	

		document.getElementById("Save").disabled=false;
		//document.getElementById("Save").className='but';
		document.getElementById("saveDTRS").disabled=false;
		//document.getElementById("saveDTRS").className='but';
		document.getElementById("Reset").disabled=false;
		//document.getElementById("Reset").className='but';
		
	
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
	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	for(var i=0;i<examinFnds.length;i++)
	{
		if(examinFnds[i].checked)
		{
			//examinCount++;
			var examinFndsValue=examinFnds[i].value;		
			var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
			var subType=document.forms[0].elements[examinFndsValue].type;
			
			if(examinFndsValue=='GE11'){			
				//if(hospId!=null && hospId!='EHS34')
				if(hospGovu!=null && hospGovu != 'G')
					{
					var tempType= '';
					if(document.forms[0].temp[0].checked==true) tempType='C';
					if(document.forms[0].temp[1].checked==true) tempType='F';
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";					
				}
				else 
					{
				
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";	
					}
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
		
	for (var x=0;x<pastHistory.length;x++)
	{
		if (pastHistory[x].checked)
		{
			if(pastHistory[x].value=="PH10")
			{
				if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
				{
					var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
					document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\r\n/g,' ');
					document.getElementById("pastHistrySurg").value=document.getElementById("pastHistrySurg").value.replace(/\n/g,' ');
					
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
    if(schemeId!='CD202'  && hospType!='G')
    {
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
	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}
    }

	/*Added by Srikalyan for Dental Changes related to TG.  
	*/
	if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
		{
			var localSchemeId=document.getElementById("scheme").value;
			if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
				{
					if((comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
							(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
							(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
								{
									lErrorMsg=checkDentalTGCond();
									if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' && lField=='')
										lField='asriCatName';
								}	
				}								
		}
	/*End for Dental Changes related to TG.  
	*/
    
    /*Added by Venkatesh for NIMS and TG Government hospitals (for submit and DTRS Generation skipping mandatory fields)*/
    var checkType='';
        if(document.getElementById("checkType"))
        	checkType=document.getElementById("checkType").value;
        else
        	checkType="Save";
    	
    var opIP='';
   
    if(document.forms[0].patientType[1].checked)
    {
    	 opIP=document.forms[0].patientType[1].value;
    }
    else if(document.forms[0].patientType[0].checked)
    {
    	 opIP=document.forms[0].patientType[0].value;
    }
    /*else if(document.forms[0].patientType[2].checked)
    {
    	 opIP=document.forms[0].patientType[2].value;
    }*/
    
    if( opIP == 'IPM')
	{
		if(document.getElementById("medicalSpclty").value=="-1" || document.getElementById("medicalSpclty").value=="")
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select Speciality ";
			}
	        if(lField=='')
	        lField='medicalSpclty'; 
		}
		if(document.getElementById("medicalCat").value=="-1" || document.getElementById("medicalCat").value==""){
			if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Category ";
				}
		        if(lField=='')
		        lField='medicalCat'; 
		}
		//Start CR#4511-SriHari-15/11/24
		if(document.getElementById("medicalCat").value=="S12MMD" && document.getElementById("highEndProformaSubmitFlag").value == "N"){
			if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Submit the High End Oncology Proforma Form ";
			}
			if(lField=='')
		        lField='proformaId';
		}
		if(document.getElementById("medicalCat").value=="S12MMD" && document.getElementById("highEndProformaSubmitFlag").value == "Y"){
				if(lErrorMsg==''){
					if(document.getElementById("highEndEvaluationSubmitFlag").value =="P"){
						lErrorMsg=lErrorMsg+"Submit the High End Oncology Evaluation Form ";
					}else if(document.getElementById("highEndEvaluationSubmitFlag").value =="M"){
						lErrorMsg=lErrorMsg+"Oncology Case is already in-progress ";
					}			        
				}
				if(lField=='')
			        lField='proformaId';			
		}
		// End CR#4511-SriHari
	}
    //if(((hospId!=null && hospId=="EHS34")||(schemeId=="CD202" && hospType=="G")) && checkType!="Save" &&  opIP!="ChronicOP")
    if(((schemeId=="CD202" && hospType=="G")) && checkType!="Save" &&  opIP!="ChronicOP")	
    {

        if(checkType=="submit")
        {
    	if(opIP == '')
    	{
    	if(lErrorMsg==''){
    	       lErrorMsg=lErrorMsg+"Select Patient Type";
    		   }
    	        if(lField=='')
    	        lField='patientType';  
    	}
  	   
    	if(opIP == 'IP')
    	{
    		//Mandatory Check for Therapy Details
    		
    		if(catCount==0 && specOld.length==0)
    			{
    			medOrSur='';
    			if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Add Speciality (Category,ICD Category,ICD Procedure Details) ";
    				}
    		     if(lField=='')
    		    	 lField='addSpeciality';
    			}
    		if(document.getElementById("treatingDocLabel").style.display=='')
    		{
    			if(document.getElementById("treatingDocRmks").value=='' || document.getElementById("treatingDocRmks").value==null)
    			{
    				if(lErrorMsg==''){
    					lErrorMsg=lErrorMsg+"Enter Treating Doctor Remarks ";
    					}
    				if(lField=='')
    				lField='treatingDocRmks'; 
    			}
    		}
    	//Mandatory Check for IP No
    	
    	if(document.forms[0].ipNo.value=='' || document.forms[0].ipNo.value==null){
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter IP NO ";
    			}
    	        if(lField=='')
    	        lField='ipNo';   
    		}
    	//Mandatory Check for Admission type
    	if(document.forms[0].admissionType.value=='-1' || document.forms[0].admissionType.value==null){
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Select Admission Type ";
    			}
    	        if(lField=='')
    	        lField='admissionType';   
    		}
    	//Mandatory Check for Proposed Surgery Date
    	var catCode=document.getElementById("asriCatName").value;
    	//if(catCode!=null && catCode.indexOf("S")!=-1 && hospId=="EHS34")
    	if(catCode!=null && catCode.indexOf("S")!=-1 && hospGovu=="G")
    	{
    		if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
    		{
    		 if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
    				}
    		        if(lField=='')
    		        lField='ipDate';   
    		}
    	}
    	//if(hospId!="EHS34")
    	if(hospGovu!=null && hospGovu != 'G')
    	{
    	if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
    	{
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
    			}
    	        if(lField=='')
    	        lField='ipDate';   
    	}
    	}
    	//Mandatory Check for Remarks
    	if(document.forms[0].remarks.value=='' || document.forms[0].remarks.value==null)
    	{
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter Remarks ";
    			}
    	        if(lField=='')
    	        lField='remarks';   
    	}
    	//Mandatory Check for Patient Diagnosed By
    	//if(hospId!=null && hospId!="EHS34")
    	if(hospGovu!=null && hospGovu != 'G')
    			{
    	if(document.forms[0].ipDiagnosedBy.value=='-1' || document.forms[0].ipDiagnosedBy.value==null){
    		  if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select IP Patient Diagnosed by ";
    				 }
    		         if(lField=='')
    		         lField='ipDiagnosedBy';   
    		}
    		//Mandatory Check For Doctor Name Drop Down List
    		if(schemeId=='CD202')
    		{
    	if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select IP Doctor Name ";
    				 }
    		         if(lField=='')
    		         lField='ipDoctorName';
    		    
    			}
    		}
    		
    		}

    	//if(hospId!=null && hospId=="EHS34")
    	if(hospGovu!=null && hospGovu=="G")
    	{
    		if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Enter IP Consultant Name ";
    				 }
    		         if(lField=='')
    		         lField='ipDoctorName';
    		    
    			}
    	}
    			if(!document.forms[0].legalCase[0].checked && !document.forms[0].legalCase[1].checked)
    			{
    				if(lErrorMsg==''){
    						lErrorMsg=lErrorMsg+"Select Medico Legal Case ";
    					}
    					if(lField=='')
    						lField='legalCase';
    			}
    			if(document.forms[0].legalCase[0].checked==true)
    			{
    				if(document.getElementById("legalCaseNo").value==null || document.getElementById("legalCaseNo").value=='')
    				{
    					if(lErrorMsg==''){
    						lErrorMsg=lErrorMsg+"Enter Legal Case No ";
    					}
    					if(lField=='')
    						lField='legalCaseNo';
    				}
    				if(document.getElementById("policeStatName").value==null || document.getElementById("policeStatName").value=='')
    				{
    					if(lErrorMsg==''){
    						lErrorMsg=lErrorMsg+"Enter Police Station Name ";
    					}
    					if(lField=='')
    						lField='policeStatName';
    				}
    			}
    		}

    	if(opIP == 'OP')
    	{
    		//Mandatory Check for Prescription Details
    		var schemeId=document.getElementById("scheme").value;
    		var patientScheme=document.getElementById("patientScheme").value;
    		/*if(schemeId=='CD201')
    		{
    		if(drugCount==0)
    			{
    			if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Add Drug (Main Group Name,Therapeutic Main Group Name,Pharmacologicl SubGroup Name,Chemical SubGroup Name,Chemical Substance Name,Route,Strength,Dosage,Medication Period Details) ";
    				}
    		     if(lField=='')
    		    	 lField='addDrug';
    			}
    		}*/
    		//Mandatory Check For Doctor Details
    	var hospType='${hospType}';
    		/*if(schemeId=='CD201'  || patientScheme!='CD501' || hospType=='C')
    			{
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
    			}*/



    			//Mandatory Check For Doctor Name TG OP
    		
    			//if(schemeId=='CD202' || hospId=="EHS34")
    			if(schemeId=='CD202' || hospGovu=="G")
    			{

    		
    		if(document.getElementById("consultationDataOld")==null && document.getElementById("consultationDataNew").style.display=="none")
    		{
    			 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
    				 }
    		         if(lField=='')
    		         lField='diagnosedBy';   
    			

    		}	
    	if(document.getElementById("consultationDataOld")&& document.getElementById("consultationDataNew"))
    	{

    		if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
    		{

    			  if(lErrorMsg==''){
    			         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
    					 }
    			         if(lField=='')
    			         lField='diagnosedBy';   
    				}
    		
    		
    				if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
    				{
    				if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
    					  if(lErrorMsg==''){
    					         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
    							 }
    					         if(lField=='')
    					         lField='diagnosedBy';   
    						}

    				
    			if(document.forms[0].unitName.value==null || document.forms[0].unitName.value==''){
    				 	 if(lErrorMsg==''){
    				         lErrorMsg=lErrorMsg+"Select Unit Name ";
    						 }
    				         if(lField=='')
    				         lField='unitName';
    				    
    					}
    			if(document.forms[0].unitHodName.value==null || document.forms[0].unitHodName.value==''){
    				 if(lErrorMsg==''){
    			        lErrorMsg=lErrorMsg+"Select Unit HOD Name ";
    					 }
    			        if(lField=='')
    			        lField='unitHodName';
    			   
    				}
    				}
    	}

    			}
    			
    			
    			 
    		}
    	if(opIP == 'OP')
    	{
    		//Mandatory check for OP No
    		if(document.forms[0].opNo.value=='' || document.forms[0].opNo.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter OP NO ";
    			}
    	        if(lField=='')
    	        lField='opNo';   
    		}
    		if(document.forms[0].opRemarks.value=='' || document.forms[0].opRemarks.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter OP Remarks ";
    			}
    	        if(lField=='')
    	        lField='opRemarks';   
    		}
    		if(document.forms[0].opDate.value=='' || document.forms[0].opDate.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Select OP Date ";
    			}
    	        if(lField=='')
    	        lField='opDate';   
    		} 

    	}

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
    		
    		
		var checkType=document.getElementById("checkType").value;
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
    	if(checkType=="SaveDTRS"){
    		var saveFlag="Yes";
	    	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	    	jqueryConfirmMsg('Case Registration Confirmation','Do you want to save and generate DTRS?',fr);

    	}
    	else{
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
	   bootbox.alert(lErrorMsg);
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

function checkDentalTGCond()
{
	var specty=document.getElementById('speciality').value;
	var procs=null;
	var specLst=new Array();

	if(specOld!=null && specty!=null)
		{
			specLst=specOld;
			specLst=specLst.concat(specty.split(","));
		}
	else if((specty=='' || specty==' ' || specty==null) && specOld!=null)
		specLst=specOld;
	else
		specLst=specty.split(",");
	
	if(specLst!=null && specLst!='' && specLst!=' ')
		{
			for(var i=0;i<specLst.length;i++)
				{
					var listValues=specLst[i].split("~");
					if(listValues[0]!=null && listValues[0]!='' && listValues[0]=='S18')
						{
							if(listValues[2]!=null)
								{
									if(procs==null || procs=='' || procs==' ')
										procs=listValues[2]+"~";
									else
										procs=procs+listValues[2]+"~";
								}
						}
					
				}
			//Checking the Presence of Combinational Procs 
			/* if(comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0)
				{
					var procWiseLst=comboProcIds.split("$");
					for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
						{
							var checkCond=0;
							var alertCont=null;
							var indiSpecCombLst=procWiseLst[j].split(",");
							var addedSpecDtls=indiSpecCombLst[0].split("!@#");
							
							var allCombos=indiSpecCombLst[1].split("~");
							for(var k=0;k<allCombos.length;k++)//Checking for every Combo Proc for added Proc
								{
									var splitComboProc=allCombos[k].split("@");
									var comboProcedureId=splitComboProc[0];
									var comboProcedureName=splitComboProc[1];
									if(procs.indexOf(comboProcedureId+"~")==-1)
										{
											checkCond++;
											if(alertCont==null || alertCont=='' || alertCont==' ')
												alertCont=comboProcedureName+"("+comboProcedureId+")";
											else
												alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
										}
								}
							if(checkCond>0)
								{
									var alertMsg="As Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Combinational Procedures "+alertCont+" should be added.";
									return alertMsg;
									break;
								}
						}
				} */
			//Checking the Presence of Non Combinational Procs 
			if(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0)
				{
					var procWiseLst=nonComboProcIds.split("$");
					for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
						{
							var checkCond=0;
							var alertCont=null;
							var indiSpecCombLst=procWiseLst[j].split(",");
							var addedSpecDtls=indiSpecCombLst[0].split("!@#");
							
							var allCombos=indiSpecCombLst[1].split("~");
							for(var k=0;k<allCombos.length;k++)//Checking for every Non Combo Proc for added Proc
								{
									var splitComboProc=allCombos[k].split("@");
									var comboProcedureId=splitComboProc[0];
									var comboProcedureName=splitComboProc[1];
									if(procs.indexOf(comboProcedureId+"~")!=-1)
										{
											checkCond++;
											if(alertCont==null || alertCont=='' || alertCont==' ')
												alertCont=comboProcedureName+"("+comboProcedureId+")";
											else
												alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
										}
								}
							if(checkCond>0)
								{
									var alertMsg="As Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
									return alertMsg;
									break;
								}
						}
					
				}
			//Checking the Presence of Non Combinational Procs for Stand Alone Procedures
			if(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0)
				{
					var procWiseLst=standaloneProcIds.split("$");
					for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
						{
							var checkCond=0;
							var alertCont=null;
							var indiSpecCombLst=procWiseLst[j].split(",");
							var addedSpecDtls=indiSpecCombLst[0].split("!@#");
							
							var allCombos=indiSpecCombLst[1].split("~");
							for(var k=0;k<allCombos.length;k++)//Checking for every Non Combo Proc for added Stand Alone Proc
								{
									var splitComboProc=allCombos[k].split("@");
									var comboProcedureId=splitComboProc[0];
									var comboProcedureName=splitComboProc[1];
									if(procs.indexOf(comboProcedureId+"~")!=-1)
										{
											checkCond++;
											if(alertCont==null || alertCont=='' || alertCont==' ')
												alertCont=comboProcedureName+"("+comboProcedureId+")";
											else
												alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
										}
								}
							if(checkCond>0)
								{
									var alertMsg="As Stand Alone Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
									return alertMsg;
									break;
								}
						}
					
				}
		}
	return "";
}
function fn_savePatientDetails(checkType)
{
	var doctorType=document.forms[0].diagnosedBy.value;
	document.getElementById("drugs").value=drugs;
	document.getElementById("symptoms").value=symptoms;
	var patId=document.getElementById("patientNo").value;
	
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
    var ipCase=document.forms[0].patientType[1].checked;
	
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
		
		//if(hospId!=null && hospId!="EHS34")
		if(hospGovu!=null && hospGovu != 'G')
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
	}
	

	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}
	
	//Mandatory Check for Complaint
	
	//if(hospId!=null && hospId!="EHS34")
	if(hospGovu!=null && hospGovu != 'G')
	{
	if((schemeId=='CD201')||(schemeId=='CD202' && hospType!='G') ||(schemeId=='CD202' && hospType=='G' && ipCase) )
	{
	
	var complaint=document.forms[0].complaints;
	var otherComplaint='';
	if(schemeId!=null && patientScheme!=null && hospType!=null && schemeId=='CD202' &&  patientScheme=='CD501' && hospType=='G' )
	{
		otherComplaint=document.getElementById("otherComplaint").value;
	}

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
			 if(schemeId!=null && patientScheme!=null && hospType!=null && schemeId=='CD202' &&  patientScheme=='CD501' && hospType=='G' )
			 {
		          if(otherComplaint==null || otherComplaint=='')
				  lErrorMsg=lErrorMsg+"Select Complaint or Other Complaint ";
			 }
			 else
        lErrorMsg=lErrorMsg+"Select Complaint ";
		}
        if(lField=='')
        lField='complaints';   
	}
//Mandatory Check for Patient treatment type
var x='${dentalrntdbvalue}';
if(x==null)
	{
var dentalnr=document.forms[0].treatmentDntl.value;
if(dentalnr!="yes" && dentalnr!="no" )
	{
	lErrorMsg=lErrorMsg+"Treatment Dental or not";
	//document.getElementById("dentalrnt").focus();
	}
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

	 if(schemeId!=null && patientScheme!=null && hospType!=null && schemeId!='CD202' &&  patientScheme!='CD501' && hospType!='G' )
	 {
        lErrorMsg=lErrorMsg+"Select Patient Complaint ";
      }
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
					document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\r\n/g,' ');
					document.getElementById("pastHistrySurg").value=document.getElementById("pastHistrySurg").value.replace(/\n/g,' ');
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
var schemeId='${schemeId}';
for(var i=0;i<examinFnds.length;i++)
{
	if(examinFnds[i].checked)
	{
		//examinCount++;
		var examinFndsValue=examinFnds[i].value;		
		var examinFndsName=document.forms[0].elements[examinFndsValue].title;		
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
		else if(examinFndsValue=='GE15' && schemeId=='CD201'){
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
if(symptomCount==0)
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Add Symptom ";
		}
     if(lField=='')
    	 lField='addSymptom';
	}


	}}
var opIP='';
if(document.forms[0].patientType[1].checked)
{
	 opIP=document.forms[0].patientType[1].value;
}
else if(document.forms[0].patientType[0].checked)
{
	 opIP=document.forms[0].patientType[0].value;
}
//Commented chronic OP
/*else if(document.forms[0].patientType[2].checked)
{
	 opIP=document.forms[0].patientType[2].value;
}*/	   
//if(hospId !=null && hospId!="EHS34")
if(hospGovu!=null && hospGovu != 'G')
{
 if(genInventList.length==0 && genOldList.length==0 && (opIP !='' && opIP != 'OP' && opIP != 'ChronicOP')){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select General Investigations ";
		}
        if(lField=='')
        lField='genInvestigations';   
}
}
//Check to enable dtrs form
if(checkType=='SaveDTRS')
{
	if(opIP == '')
	{
	if(lErrorMsg==''){
	       lErrorMsg=lErrorMsg+"Select Patient Type";
		   }
	        if(lField=='')
	        lField='patientType';  
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
if(opIP == '')
{
if(lErrorMsg==''){
       lErrorMsg=lErrorMsg+"Select Patient Type";
	   }
        if(lField=='')
        lField='patientType';  
}
else
{
	//Mandatory Check for Diagnosis Details
	var hospType='${hospType}';
	
	if( opIP == 'IPM')
	{
		if(document.getElementById("medicalSpclty").value=="-1" || document.getElementById("medicalSpclty").value=="")
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select Speciality ";
			}
	        if(lField=='')
	        lField='medicalSpclty'; 
		}
		if(document.getElementById("medicalCat").value=="-1" || document.getElementById("medicalCat").value=="")
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select Category ";
			}
	        if(lField=='')
	        lField='medicalCat'; 
		}
	
	
	}
	//if(hospId !=null && hospId!="EHS34")
	if(hospGovu!=null && hospGovu != 'G')
	{
	if((schemeId=='CD201' && opIP == 'OP' && '${dentalFlg}'!='Y' )||(opIP=='IP')||(hospType!='G'))
	{
		
	if(document.getElementById("diagType").value=="-1" || document.getElementById("diagType").value=="")
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select Diagnosis Type ";
			}
	        if(lField=='')
	        lField='diagType'; 
		}
	if(document.getElementById("mainCatName").value=="-1" || document.getElementById("mainCatName").value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Main Category Name ";
		}
        if(lField=='')
        lField='mainCatName'; 
	}
	if(document.getElementById("catName").value=="-1" || document.getElementById("catName").value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Category Name ";
		}
        if(lField=='')
        lField='catName'; 
	}
	if(document.getElementById('subCatName').value=="-1" || document.getElementById('subCatName').value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Sub Category Name ";
		}
        if(lField=='')
        lField='subCatName'; 
	}
	if(document.getElementById("diseaseName").value=="-1" || document.getElementById("diseaseName").value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Disease Name ";
		}
        if(lField=='')
        lField='diseaseName'; 
	}
	if(document.getElementById("disAnatomicalName").value=="-1" || document.getElementById("disAnatomicalName").value=="")
	{
	if(lErrorMsg==''){
       lErrorMsg=lErrorMsg+"Select Diagnosis Disease Anatomical Name ";
	   }
        if(lField=='')
        lField='disAnatomicalName'; 
	}
	}
	}
	
}
if(opIP == 'IP')
{
	//Mandatory Check for Therapy Details
	
	if(catCount==0 && specOld.length==0)
		{
		medOrSur='';
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Add Speciality (Category,ICD Category,ICD Procedure Details) ";
			}
	     if(lField=='')
	    	 lField='addSpeciality';
		}
	else
		{
			/*Added by Srikalyan for Dental Changes related to TG.  
			*/
				 if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
					{
					 //(comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
					 if(		(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
								(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
									{
										lErrorMsg=checkDentalTGCond();
										if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' && lField=='')
											lField='asriCatName';
									}	
					}	 
			/*End for Dental Changes related to TG.  
			*/
		}
	if(document.getElementById("treatingDocLabel").style.display=='')
	{
		if(document.getElementById("treatingDocRmks").value=='' || document.getElementById("treatingDocRmks").value==null)
		{
			if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Treating Doctor Remarks ";
				}
			if(lField=='')
			lField='treatingDocRmks'; 
		}
	}
//Mandatory Check for IP No
if(document.forms[0].ipNo.value=='' || document.forms[0].ipNo.value==null){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter IP NO ";
		}
        if(lField=='')
        lField='ipNo';   
	}
//Mandatory Check for Admission type
if(document.forms[0].admissionType.value=='-1' || document.forms[0].admissionType.value==null){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Admission Type ";
		}
        if(lField=='')
        lField='admissionType';   
	}
//Mandatory Check for Proposed Surgery Date
var catCode=document.getElementById("asriCatName").value;
//if(catCode!=null && catCode.indexOf("S")!=-1 && hospId=="EHS34")
if(catCode!=null && catCode.indexOf("S")!=-1 && hospGovu=="G")
{
	if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
	{
	 if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
			}
	        if(lField=='')
	        lField='ipDate';   
	}
}
//if(hospId!="EHS34")
if(hospGovu!=null && hospGovu != 'G')
{
if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
{
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
		}
        if(lField=='')
        lField='ipDate';   
}
}
//Mandatory Check for Remarks
if(document.forms[0].remarks.value=='' || document.forms[0].remarks.value==null)
{
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter Remarks ";
		}
        if(lField=='')
        lField='remarks';   
}
//Mandatory Check for Patient Diagnosed By
//if(hospId!=null && hospId!="EHS34")
if(hospGovu!=null && hospGovu != 'G')
		{
if(document.forms[0].ipDiagnosedBy.value=='-1' || document.forms[0].ipDiagnosedBy.value==null){
	  if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Patient Diagnosed by ";
			 }
	         if(lField=='')
	         lField='ipDiagnosedBy';   
	}
	//Mandatory Check For Doctor Name Drop Down List
	if(schemeId=='CD202')
	{
if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Doctor Name ";
			 }
	         if(lField=='')
	         lField='ipDoctorName';
	    
		}
	}
	
	}

//if(hospId!=null && hospId=="EHS34")
if(hospGovu!=null && hospGovu=="G")
{
	if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Consultant Name ";
			 }
	         if(lField=='')
	         lField='ipDoctorName';
	    
		}
}


		
		if(!document.forms[0].legalCase[0].checked && !document.forms[0].legalCase[1].checked)
		{
			if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Select Medico Legal Case ";
				}
				if(lField=='')
					lField='legalCase';
		}
		if(document.forms[0].legalCase[0].checked==true)
		{
			if(document.getElementById("legalCaseNo").value==null || document.getElementById("legalCaseNo").value=='')
			{
				if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Enter Legal Case No ";
				}
				if(lField=='')
					lField='legalCaseNo';
			}
			if(document.getElementById("policeStatName").value==null || document.getElementById("policeStatName").value=='')
			{
				if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Enter Police Station Name ";
				}
				if(lField=='')
					lField='policeStatName';
			}
		}
	}
if(opIP == 'OP' || opIP == 'ChronicOP')
{
	//Mandatory Check for Prescription Details
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	if(schemeId=='CD201')
	{
	if(drugCount==0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Add Drug (Main Group Name,Therapeutic Main Group Name,Pharmacologicl SubGroup Name,Chemical SubGroup Name,Chemical Substance Name,Route,Strength,Dosage,Medication Period Details) ";
			}
	     if(lField=='')
	    	 lField='addDrug';
		}
	}
	//Mandatory Check For Doctor Details
var hospType='${hospType}';
	if(schemeId=='CD201'  || patientScheme!='CD501' || hospType=='C')
		{
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
		}



		//Mandatory Check For Doctor Name TG OP
		else
		{
		if(schemeId=='CD202')
		{

	
	if(document.getElementById("consultationDataOld")==null && document.getElementById("consultationDataNew").style.display=="none")
	{
		 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
			 }
	         if(lField=='')
	         lField='diagnosedBy';   
		

	}	
if(document.getElementById("consultationDataOld")&& document.getElementById("consultationDataNew"))
{

	if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
	{

		  if(lErrorMsg==''){
		         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
				 }
		         if(lField=='')
		         lField='diagnosedBy';   
			}
	
	
			if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
			{
			if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
				  if(lErrorMsg==''){
				         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
						 }
				         if(lField=='')
				         lField='diagnosedBy';   
					}

			
		if(document.forms[0].unitName.value==null || document.forms[0].unitName.value==''){
			 	 if(lErrorMsg==''){
			         lErrorMsg=lErrorMsg+"Select Unit Name ";
					 }
			         if(lField=='')
			         lField='unitName';
			    
				}
		if(document.forms[0].unitHodName.value==null || document.forms[0].unitHodName.value==''){
			 if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Unit HOD Name ";
				 }
		        if(lField=='')
		        lField='unitHodName';
		   
			}
			}
}

		}
		
		
		} 
	}
if(opIP == 'OP')
{
	//Mandatory check for OP No
	if(document.forms[0].opNo.value=='' || document.forms[0].opNo.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter OP NO ";
		}
        if(lField=='')
        lField='opNo';   
	}
	if(document.forms[0].opRemarks.value=='' || document.forms[0].opRemarks.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter OP Remarks ";
		}
        if(lField=='')
        lField='opRemarks';   
	}
	if(document.forms[0].opDate.value=='' || document.forms[0].opDate.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select OP Date ";
		}
        if(lField=='')
        lField='opDate';   
	} 

}
if(opIP == 'ChronicOP')
{
	//Mandatory Check For Chronic OP Therapy Details
	if(document.getElementById("opCatName").value=="-1")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Therapy OP Category Name ";
		}
        if(lField=='')
        lField='opCatName'; 
	}
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
function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
{
	
	//document.getElementById("Save").disabled=true;
	//document.getElementById("Save").className='butdisable';
    var selInvData='';
	 var selGenInvData='';
	 var updateGenInvData='';
	
	 var consultationData='';
	 var selectedList1  = document.getElementById('addTests');  
	 //var selectedList2  = document.getElementById('investigationSel');

	 for(var i=0;i<genInventList.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var price='';
        var investInfo = genInventList[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
	   	   price= investInfo[3]; 
          if((selGenInvData!=null || selGenInvData!='') && selGenInvData.length>0)
          {
       	   selGenInvData=selGenInvData+'~';
          }
                 
          selGenInvData=selGenInvData+ltext+'$'+lId+'$'+price;  
  			
    	}


	 /*Added by venkatesh to save consultation doctors details*/
	 
	
	 for(var i=0;i<consultDataList.length;i++)
	 	{
		 	
     
     var consultInfo = consultDataList[i].split("~");
     
       if((consultationData!=null || consultationData!='') && consultationData.length>0)
       {
    	   consultationData=consultationData+'~';
       }
              
       consultationData=consultationData+consultInfo[0]+'$'+consultInfo[1]+'$'+consultInfo[2];

       
			
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
   		document.getElementById("Submit").disabled=true;
		document.getElementById("Submit").className='butdisable';
		document.getElementById("Save").disabled=true;
		document.getElementById("Save").className='butdisable';
		document.getElementById("saveDTRS").disabled=true;
		document.getElementById("saveDTRS").className='butdisable';
		document.getElementById("Reset").disabled=true;
		document.getElementById("Reset").className='butdisable';
		
		var url="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType+"&consultationData="+consultationData+"&genInvestRemove="+genInvestRemove+"&specRemove="+specRemove
		+"&dentalFlg="+'${dentalFlg}';
		if(document.getElementById("treatingDocLabel").style.display=='')
		{
		url=url+"&treatingDocRmks="+document.getElementById("treatingDocRmks").value;
		}
		if(document.forms[0].caseId.value!=''){
			url=url+"&caseId="+document.forms[0].caseId.value;
		}
		if(document.getElementById("highEndProformaId") && document.getElementById("highEndProformaId").value !=''){
			url= url+"&highEndProformaId="+document.getElementById("highEndProformaId").value;
		}
		
		/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
		document.forms[0].action=url;
		document.forms[0].method="post";
		document.forms[0].submit();
}


function redirecttodental(input)
{
	var dentalredirect=input;
	var patientId=document.getElementById("patientNo").value;
    var caseId=document.getElementById("caseId").value;
    if(input=="yes")
	 {
	bootbox.confirm("Do you wish to redirect to the dental case sheet ?", function(result) {
	if(result)
		{
	if(caseId=='NA' || caseId== "")
		{
		 url = "./patientDetails.do?actionFlag=redirecttodental&dentalredirect="+dentalredirect+"&patientId="+patientId;
	   	document.forms[0].action=url;
		document.forms[0].method="post";
		document.forms[0].submit();
		}
		}
	
	});
	 }
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
function getSubFieldType(input)
{
	
	var hospGovu = document.getElementById("hospGovu").value;
	var examinField="";
	var id=input.value;	
	var maxlength=5;
	var subTypeField=document.getElementById(id+"h").value;
	var prop = document.getElementById(id+"h").name;
	var hospId=document.getElementById("hospitalId").value;
	
	
	if(prop=="Height (in Cm)")
	{maxlength=10;}
     else if(prop=="Weight (in Kg)")
	{maxlength=10;}
    else if(prop=="BMI")
	maxlength=21;
			
	if(input.checked)
	{
	if(subTypeField=='T')
		{
		if(prop!='BP Lt.Arm mm/Hg'&& prop!='BP Rt.Arm mm/Hg'&& prop!='Temperature(C/F)')
			{examinField=examinField+"<input type='text' name='"+id+"' id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'onchange='validateInnerNum(this)'";
			 if(prop=="BMI") 
				 examinField=examinField+" readOnly/> ";
			 else
				examinField=examinField+" /> ";
			}
		
		else if(prop=='BP Lt.Arm mm/Hg')
			{
			
			examinField=examinField+"<input type='text' style='width:37%' name='"+id+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> / <input type='text' name='BP1' id='BP1' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			
			}
		else if(prop=='BP Rt.Arm mm/Hg') 
			examinField=examinField+"<input type='text' style='width:37%' name='"+id+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> / <input type='text' name='BP2' id='BP2' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
		else if(prop=='Temperature(C/F)')
			{
			
			//if(hospId!=null && hospId!='EHS34')
			if(hospGovu!=null && hospGovu!="G")
				
			   {
				
				examinField=examinField+"<input type='radio' name='temp' id='temp' value='C' title='Centigrade' onclick='showTemp()'/>C<input type='radio'  name='temp' id='temp' value='F' title='ForeignHeat' onclick='showTemp()' />F&nbsp;&nbsp;&nbsp;<input type='text' style='width:57%;' name='"+id+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field' onchange='validateInnerNum(this)'/>";
			   }
		    else
            {
				examinField=examinField+"<input type='text' style='width:57%;' name='"+id+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field' onchange='validateInnerNum(this)'/>";
			
			    }
		     }
		}
		
	else if(subTypeField=='R')
		{
		
			examinField=examinField+"<input type='radio' name='"+prop+"' id='"+id+"' value='Y' title='Yes' onclick='getExaminSubCatValue(this)'/>Yes<input type='radio'  name='"+prop+"' id='"+id+"' value='N' title='No' onclick='getExaminSubCatValue(this)'/>No<br /><span id='"+prop+"Sub'></span>";
		}
	
	document.getElementById(id).innerHTML=examinField;
	}
	else
	{
	document.getElementById(id).innerHTML="";
	}
	//parent.fn_resizePage();
}
function resetMedicalDetails()
{
	//document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+document.getElementById("patientNo").value+"&caseId=";
	// document.forms[0].method="post";
	//document.forms[0].submit();
	var complainLen=document.getElementById("complaints").length;
	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
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
	document.getElementById("mainSymptomName").value=-1;
	document.getElementById("mainSymptomCode").value= "";
	document.getElementById("symptomName").options.length=1;
	document.getElementById("symptomCode").value="";
	symptomCount=0;	
	
	var symptomsTable = document.getElementById("symptomsTable");
	for(var count = symptomsTable.rows.length-1 ; count>0; count--)
		{
		symptomsTable.deleteRow(count);
		}
	document.getElementById("symptomsTable").style.display="none";
	symptoms=new Array();
	
	//if(hospId!="EHS34")
	if(hospGovu!=null && hospGovu != 'G')
	{
	document.getElementById("diagType").value=-1;
	document.getElementById("diagCode").value="";
	document.getElementById("mainCatName").options.length = 1;
	document.getElementById("mainCatCode").value = "";
	document.getElementById("catName").options.length = 1;
	document.getElementById("catCode").value= "";
	document.getElementById("subCatName").options.length = 1;
	document.getElementById("subCatCode").value = "";
	document.getElementById("diseaseName").options.length = 1;
	document.getElementById("diseaseCode").value = "";
	document.getElementById("disAnatomicalName").options.length = 1;
	document.getElementById("disAnatomicalCode").value = "";
	
	genInventCount=0;	
	var genTestTable = document.getElementById("genTestTable");
	for(var count = genTestTable.rows.length-1 ; count>0; count--)
		{
		genTestTable.deleteRow(count);
		}
	if(document.getElementById("genTestTable"))
	document.getElementById("genTestTable").style.display="none";
	}
	genInventList=new Array();
	
	document.getElementById("testsCount").value=0;	
	document.forms[0].patientType[1].disabled=true;
	
	if(document.getElementById("treatingDocLabel").style.display=='')
	{
	document.getElementById("treatingDocLabel").style.display='none';
	document.getElementById("treatingDocRemarks").innerHTML='';
	}
	
	if(document.forms[0].patientType[1].checked==true)
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
	document.getElementById("ipDate").value="";
	if(document.getElementById("ipDiagnosedBy"))
	document.getElementById("ipDiagnosedBy").value="-1";
	//if(hospId!="EHS34")
	if(hospGovu!=null && hospGovu != 'G')
	document.getElementById("ipDoctorName").options.length= 1;
	else
		document.getElementById("ipDoctorName").value="";	
	document.getElementById("remarks").value="";	
	
	if(specOld.length==0){	
		if(document.getElementById("ipDocNameList"))	
	document.getElementById("ipDocNameList").style.display="";
		if(document.getElementById("IPHead2"))	
	document.getElementById("IPHead2").style.display="none";
	document.getElementById("diagnosisData").style.display="none";
	document.forms[0].patientType[1].checked=false;
	if(genOldList.length>0)
		document.forms[0].patientType[1].disabled=false;
	else
		document.forms[0].patientType[1].disabled=true;
	}else{
		document.forms[0].patientType[1].checked=true;
		document.forms[0].patientType[1].disabled=false;		
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
else if(document.forms[0].patientType[0].checked==true)
	{

	//if(hospId!=null && hospId!="EHS34")
	if(hospGovu!=null && hospGovu != 'G')
	{
	var existDrugsTable = document.getElementById("existDrugs");
	for(var count = existDrugsTable.rows.length - 1 ; count>0; count--)
		{
		existDrugsTable.deleteRow(count);
		}
	document.getElementById("existDrugs").style.display="none";
	document.getElementById("existDrugsHead").style.display="none";
	
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
	}
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
	document.getElementById("docQual").value="";
	document.getElementById("docMobileNo").value="";
	
	document.forms[0].patientType[0].checked=false;
	
	if(document.getElementById("empPastHistory"))
		document.getElementById("empPastHistory").style.display="none";
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
		var hospId = document.getElementById("hospitalId").value;
		var hospGovu = document.getElementById("hospGovu").value;
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
	                            	    //if(hospId!='EHS34')
	                            	    if(hospGovu!=null && hospGovu != 'G')
	    		                    		phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"' checked='checked'/>"+""+val1;
	    		                    	else
	    		                    		phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetailsNims(this)' title='"+val1+"' checked='checked'/>"+""+val1;
		                    	}
                                
		                    	else
		                    		{
                            	    //if(hospId!='EHS34')
                            	    if(hospGovu!=null && hospGovu != 'G')
                            	    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"'/>"+""+val1;	
    		                    	else
    		                    	{
    		                    		if(val2 != 'PR3.3' && val2 !=  'PR2.3')
    		                    			phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetailsNims(this)' title='"+val1+"'/>"+""+val1;
    		                    	}
			             	
		                    		}
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
	var hospGovu = document.getElementById("hospGovu").value;
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

      	 			document.forms[0].unitName.value='${PatientOpList.unitName}';
      	 			document.forms[0].unitHodName.value='${PatientOpList.unitHodName}';
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
	var hospGovu = document.getElementById("hospGovu").value;
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
                		 else
                		{
                    		document.forms[0].doctorName.options[0]=new Option("--select--","-1");
                        } 
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

function fn_loadProcessImage()
{
	document.getElementById('processImagetable').style.display="";
	setTimeout(function()
	{
		document.getElementById('processImagetable').style.display="none";
	},4000);
}

function fn_ipop()
{
	
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;

	if(schemeId!=null && schemeId!='' && schemeId=='CD202')
		onloadDentalTGCond();
	

	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;

	var surgProc="${surgProc}";

var opActiveMsg='${opActiveMsg}';

	//if( (schemeId=="CD202" && patientScheme=="CD501" && hospType=="G") || (hospId=="EHS34") )
	if( (schemeId=="CD202" && patientScheme=="CD501" && hospType=="G") || (hospGovu=="G") )
	{
		
	if( document.forms[0].patientType[0].checked)
	{
	if(opActiveMsg!=null && opActiveMsg!='')
	{
	bootbox.alert(opActiveMsg);

	$('#middleFrame_content').find('input, textarea, button, select').attr('disabled','disabled');
	$('.globalEnable').prop('disabled',false);

	document.getElementById("Submit").disabled=false;
	document.getElementById("Save").disabled=false;
	document.getElementById("addAttach").disabled=false;
	document.getElementById("Reset").style.display="none";
	
	
	

	
	
	}
	document.getElementById("ipNote1").style.display="none";
	//document.getElementById("ipNote2").style.display="none";
	document.getElementById("opNote").style.display="";
	document.getElementById("docNameList").style.display="none";
	document.getElementById("docName").style.display="none";
	document.getElementById("doctorName").style.display="none";
	document.getElementById("unitNameHead").style.display="";
	document.getElementById("unitHODNameHead").style.display="";
	document.getElementById("unitName").style.display="";
	document.getElementById("unitHodName").style.display="";
	document.getElementById("addConsult").style.display="";
	document.getElementById("empPastHistory").style.display="";
	document.getElementById("addAttach").style.display="";
	document.getElementById("prescriptionData").style.display="";
	
		}
	
	else  if(document.forms[0].patientType[1].checked)
	{
		document.getElementById("opNote").style.display="none";
		
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
		
		document.getElementById("unitNameHead").style.display="none";
		document.getElementById("unitHODNameHead").style.display="none";
		document.getElementById("unitName").style.display="none";
		document.getElementById("unitHodName").style.display="none";
		document.getElementById("addConsult").style.display="none";
		document.getElementById("empPastHistory").style.display="none";
		document.getElementById("addAttach").style.display="none";
		document.getElementById("prescriptionData").style.display="none";

		//if(hospId!=null && hospId=="EHS34" && surgProc!=null && surgProc=="Y")
		if(hospGovu!=null && hospGovu=="G" && surgProc!=null && surgProc=="Y")
		{
		
document.getElementById("surgDate").style.display="";
document.getElementById("surgDateHead").style.display="";
		}
		else
			
		{   
			if(document.getElementById("surgDate"))
			document.getElementById("surgDate").style.display="none";
		    if(document.getElementById("surgDateHead"))
			document.getElementById("surgDateHead").style.display="none";
		}
	}

		
	}

	

	
	fn_enableHistory();
	fn_enableDiag();
	
}

function fn_enableOthers()
{
if(document.getElementById("invOthers"))
{
	if(document.getElementById("invOthers").checked)
	{
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
function fn_enableDiag()
{
	var otherDiagName='${otherDiag}';

	if(otherDiagName!=null && otherDiagName!='' && otherDiagName=='Y')
	{
		if(document.getElementById("diagOthers"))
		document.getElementById("diagOthers").checked=true;
		fn_enableOtherDiag();
	}
}
function fn_enableOtherSymptoms()
{
	var elements=document.getElementsByClassName("mainSymptoms");
	for(var i=0;i<elements.length;i++)
	{
		if(document.getElementById("otherSymptoms").checked)
		{
			document.getElementById("otherSym").style.display="";
             elements[i].style.display="none";
		}
		else
		{
			 elements[i].style.display="";
			 document.getElementById("otherSym").style.display="none";
		}
			
	}
}
function fn_enableOtherDrugs()
{
	
	var elements=document.getElementsByClassName("existDrugs");

	for(var i=0;i<elements.length;i++)
	{
		if(document.getElementById("drugOthers").checked)
		{
        elements[i].style.display="none";
        document.getElementById("otherDrug").style.display="";
        document.getElementById("othDrugName").style.display="";
        document.getElementById("otherDrugName").value="";
        document.getElementById("otherDrugName").focus();
		}
		else
		{
		 elements[i].style.display="";
		 document.getElementById("otherDrug").style.display="none";
	     document.getElementById("othDrugName").style.display="none";
	     document.getElementById("otherDrugName").value="";
		}
			
	}
}

function fn_enableOtherDiag()
{
	
	var elements=document.getElementsByClassName("existDiag");

	for(var i=0;i<elements.length;i++)
	{
		if(document.getElementById("diagOthers").checked)
		{
        elements[i].style.display="none";
        document.getElementById("diagOthersHead").style.display="";
        document.getElementById("diagOthersName").style.display="";
        document.getElementById("diagOthersName").value="";
        document.getElementById("diagOthersName").focus();
		}
		else
		{
		 elements[i].style.display="";
		  document.getElementById("diagOthersHead").style.display="none";
	        document.getElementById("diagOthersName").style.display="none";
	        document.getElementById("diagOthersName").value="";
		}
			
	}
}

function fn_PastData()
{
   //var url="/Operations/casesApprovalAction.do?actionFlag=getPastHistory&caseId=${caseId}&cardNo=${patCommonDtls.CARDNO}";   
   var url="/Operations/empPenCaseSearch.do?actionFlag=caseSearch&employeeNo=${patCommonDtls.EMPLOYEENO}&fromPastHistory=Y";
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();	
}
function getDiagnosisDetails(caseStatus,caseId,hospName,regisDate,patientId,caseNo)
{
	
	if(caseStatus!=null && caseStatus!='' && caseStatus=='CD34')
	{
		
		document.getElementById("pastHistoryFrame").src=bootbox.alert("Cannot show the case details as case is in Case Drafted status.");
		//jqueryAlertMsg("Alert","Cannot show the case details as case is in Case Drafted status.");
		return;
	}
	else
	{
		var url;
		var pastHistory='${fromPastHistory}';

		/*$(function() {
			
	
	        Custombox.open({
	            target: '#pastHistoryModal',
	            effect: 'superscaled'
	        });
	       
	});*/

		
		if(caseStatus!=null && caseStatus!='' && caseStatus=='CD2')
		{
			if(pastHistory!=null && pastHistory!='' && pastHistory=='Y')
				url="/<%=context%>/preauthDetails.do?actionFlag=getOnlineCaseSheet&caseId="+caseId+"&patientId="+patientId+"&caseSheetFlag=Y&closeBut=Y";     
			else
				url="/<%=context%>/clinicalNotesAction.do?actionFlag=printDischargeForm&caseId="+caseId+"&decFlag=N";	
		}
		else
			url="/<%=context%>/patientDetails.do?actionFlag=casePrintForm&patientId="+patientId+"&caseId="+caseNo+"&decFlag=N";
			//var url='/<%=context%>/empPenCaseSearch.do?actionFlag=getDiagnosisDetails&patientId='+patientId+'&caseNo='+caseNo+'&hospName='+hospName+'&regisDate='+regisDate+'&caseId='+caseId+'&caseStatus='+caseStatus;
		//window.open(url,"case_details",'toolbar=no,resizable=yes,scrollbars=yes,menubar=no,location=no,height=700,width=900,left=10,top=15');
		document.getElementById("pastHistoryFrame").src=url;


	
		
		
	}
	
}


/*Added for TG OP Changes - by Venkatesh*/

function fn_addConsultant()
{
	
	$(document).ready(function(){
	    $(".btn").click(function(event){
	        event.preventDefault();
	    });
	});
	
	var diagnoisedBy=document.getElementById("diagnosedBy").value;
	var unitName=document.forms[0].unitName.value;
	var unitHodName=document.forms[0].unitHodName.value;
	var consultData='';

	var oldConsultData='${consultationData}';
	
	if(diagnoisedBy==null || diagnoisedBy=='' || diagnoisedBy=='-1')
		{
		bootbox.alert("Please Select Diagnosed By");
		document.getElementById("diagnosedBy").focus();
		return false;
		
		}
	
	if(unitName==null || unitName=='')
	{
	bootbox.alert("Please Enter Unit Name");
	document.getElementById("unitName").focus();
	return false;
	
	}
	
	if(unitHodName==null || unitHodName=='' )
	{
	bootbox.alert("Please Enter Unit HOD Name");
	document.getElementById("unitHodName").focus();
	return false;
	
	}
	
	if(oldConsultData!=null && oldConsultData!='' && oldConsultData.length>0)
	{
     var consultArray=new Array();
     consultArray=oldConsultData.split("$");

				for(var i=0;i<consultArray.length;i++)
				{
				var docDtls=consultArray[i].split("~");
				var unitNameTemp=docDtls[0].replace("@","").trim();
			    var hodNameTemp=docDtls[1].replace("@","").trim();

				if((unitNameTemp.toUpperCase()==unitName.toUpperCase())&&(hodNameTemp.toUpperCase()==unitHodName.toUpperCase()))
				{
					bootbox.alert("Consultation Details already entered");
					document.getElementById("diagnosedBy").value=-1;
					document.forms[0].unitName.value='';
					document.forms[0].unitHodName.value='';
					return false;
				}

	}
	}


	if(consultDataList!=null && consultDataList!='' && consultDataList.length>0)
	{
    

				for(var i=0;i<consultDataList.length;i++)
				{
				var docDtls=consultDataList[i].split("~");
				var unitNameTemp=docDtls[0].replace("@","").trim();
			    var hodNameTemp=docDtls[2].replace("@","").trim();

				if((unitNameTemp.toUpperCase()==unitName.toUpperCase())&&(hodNameTemp.toUpperCase()==unitHodName.toUpperCase()))
				{
					bootbox.alert("Consultation Details already entered");
					document.getElementById("diagnosedBy").value=-1;
					document.forms[0].unitName.value='';
					document.forms[0].unitHodName.value='';
					return false;
				}

	}
	}
	
	
	
	document.getElementById("addConsult").style.disabled=true;
   
	
	var docTable=document.getElementById('consultationDataNew');

	var newRow=docTable.insertRow(-1);
	
	

	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td class="tbcellBorder"><b>'+consultCount+'</b></td>';
	newcell=newRow.insertCell(1);
	newcell.innerHTML='<td class="tbcellBorder"><b>'+unitName+'</b></td>';
	newcell=newRow.insertCell(2);
	newcell.innerHTML='<td class="tbcellBorder"><b>'+diagnoisedBy+'</b></td>';
	newcell=newRow.insertCell(3);
	newcell.innerHTML='<td class="tbcellBorder"><b>'+unitHodName+'</b></td>';
	
	

	document.getElementById("diagnosedBy").value="-1";
	document.forms[0].unitName.value="";
	document.forms[0].unitHodName.value="";
	document.getElementById("addConsult").style.disabled=false;
	
	
	


	consultData=unitName+"~"+diagnoisedBy+"~"+unitHodName;
	
	consultDataList[consultCount-1]=consultData;
	consultCount++;
	
	if(consultCount>1)
	{
	document.getElementById("consultationDataNew").style.display="";
	}
    else
	{
	document.getElementById("consultationDataNew").style.display="none";
	}
}

function fn_addAttach()


{
	var url="/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId=${caseId}&caseAttachmentFlag=Y&caseApprovalFlag=Y&module=Operations&opAttach=Y";
	
		document.getElementById("addAttachFrame").src=url;
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

$(function() {
	blinkeffect('#empPastHstry');
	})
	function blinkeffect(selector) {
	$(selector).fadeOut('slow', function() {
	$(this).fadeIn('slow', function() {
	blinkeffect(this);
	});
	});
	}

$(function() {
	blinkeffect('.autoData');
	})
	function blinkeffect(selector) {
	$(selector).fadeOut('slow', function() {
	$(this).fadeIn('slow', function() {
	blinkeffect(this);
	});
	});
	}

$(document).ready(function()
		{
$('.otherFields').keyup(function(){
	var text=$(this).val();

 
    var textLength=text.length;
    if(textLength==1)
    {
    if(text.indexOf(" ")!=-1)
    {
	  bootbox.alert("should not Start with Spaces");
      $(this).val("");
	  return false;
    }
    }

    var regExp1=/^\s*[a-zA-Z0-9,.\n\ s]+\s*$/;

    if(!regExp1.test(text))
    {
    alert("Special Characters are not allowed");
    
    return false;
    }

    

	});
		});



$(document).ready(function(){
    $(".btn").click(function(event){
        event.preventDefault();
    });
});

function saveChronicOP()
{
	var chronicYn='${chronicYn}';
	if(chronicYn!=null && chronicYn!='' && chronicYn=='Y')
	{
	bootbox.alert("A Chronic OP Case is already initiated for the following case");
	document.getElementById("patientType[2]").checked=false;
	return false;
	}
	else
	{
	bootbox.confirm("Do You Wish to Convert this case to ChronicOP ?", function(result) {
		
		if(result)
			fn_saveDetailsWithoutMandate("SaveChronicOP");
		
		}); 
	}
	
}
function fn_enableHistory()
{
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	if(schemeId!='CD201' && hospType=='G' )
	{
	var otherComplaint=document.getElementById("otherComplaint").value;
	var patComplaintCode=document.getElementById("patComplaintCode").value;
	if(otherComplaint!=null && otherComplaint!='')
	{
		document.forms[0].presentHistory.disabled=false;
	}
	else
	{
		if(patComplaintCode==null || patComplaintCode=='')
		document.forms[0].presentHistory.disabled=true;
		else
			return false;
	}
	}
			
}


function getDrugDetailsAuto()
{
	var chemicalCode=document.getElementById("drugNameAuto").value;
	
	var xmlhttp;
    var url;


if(chemicalCode=="-1")
{
	document.getElementById("drugTypeCode").value="-1";
	document.getElementById("drugCode").value="";

	document.getElementById("drugSubTypeName").value="-1";
	document.getElementById("drugSubTypeCode").value="";

	document.getElementById("pSubGrpName").value="-1";
	document.getElementById("pSubGrpCode").value="";

	document.getElementById("cSubGrpName").value="-1";
	document.getElementById("cSubGrpCode").value="";

	document.getElementById("drugName").value="-1";
	document.getElementById("asriDrugCode").value="";

	return false;
}
    
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

	xmlhttp.onreadystatechange=function(){

		if(xmlhttp.readyState==4)
   		{
			var result=xmlhttp.responseText;
		
			result = result.replace("*","");
			
			if(result.trim()=="SessionExpired*"){
					jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
			}
			else
			{
				var jsonData=JSON.parse(result);

				
				document.getElementById("drugTypeCode").value=jsonData.mainGrpCode;
				document.getElementById("drugCode").value=jsonData.mainGrpCode;

				document.getElementById("drugSubTypeName").options[0]=new Option(jsonData.therMainGrpName,jsonData.therMainGrpCode);
				document.getElementById("drugSubTypeCode").value=jsonData.therMainGrpCode;

				document.getElementById("pSubGrpName").options[0]=new Option(jsonData.pharSubGrpName,jsonData.pharSubGrpCode);
				document.getElementById("pSubGrpCode").value=jsonData.pharSubGrpCode;

				document.getElementById("cSubGrpName").options[0]=new Option(jsonData.cheSubGrpName,jsonData.cheSubGrpCode);
				document.getElementById("cSubGrpCode").value=jsonData.cheSubGrpCode;

				document.getElementById("drugName").options[0]=new Option(jsonData.chemicalName,jsonData.chemicalCode);
				document.getElementById("asriDrugCode").value=jsonData.chemicalCode;
				
				
			
			}
			
   		}
	}
	url = "./patientDetails.do?actionFlag=getOpDrugDtlsAuto&callType=Ajax&chemicalCode="+chemicalCode; 
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}



		
function getDiagListAuto()
{
	var xmlhttp;
	var url;
	var diagName=document.getElementById("diagnosisName").value;
	var diagType=document.forms[0].diagCondition.value;
	
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	  else
		{
		jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		}
	 
	document.getElementById("disNameAuto").options.length=0;
	document.getElementById("disNameAuto").options[i]=new Option("----SELECT----","-1");
	//document.getElementById("disNameAuto").value="-1";
	$("#disNameAuto").select2('val','-1');
	
	
if(diagName.length<=3)
{
	document.getElementById("diagAuto1").style.display="";
	document.getElementById("diagAuto2").style.display="none";
	return false;
}
else
{
	
	xmlhttp.onreadystatechange=function(){

if(xmlhttp.readyState==4)
{
var result=xmlhttp.responseText;
result = result.replace("*","");
if(result.trim()=="SessionExpired*"){
	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
}
else
{
var jsonData=JSON.parse(result);
if(jsonData.length>0)
{
document.getElementById("diagAuto1").style.display="none";
document.getElementById("diagAuto2").style.display="";
}
else
{
	document.getElementById("diagAuto1").style.display="";
	document.getElementById("diagAuto2").style.display="none";
}
for(var i=0;i<jsonData.length;i++)
{
	document.getElementById("disNameAuto").options[i+1]=new Option(jsonData[i].VALUE,jsonData[i].ID);

}
}	
}	
	};
	url = "./patientDetails.do?actionFlag=getdiagListAuto&callType=Ajax&diagName="+diagName+"&diagType="+diagType; 
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
}

function getDiagDetailsAuto()
{
	var disAnatomicalCode=document.getElementById("disNameAuto").value;
	
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
	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	} 


	if(disAnatomicalCode=='-1')
	{
return false;
	}
	else
	{
	xmlhttp.onreadystatechange=function(){

		if(xmlhttp.readyState==4)
   		{
			var result=xmlhttp.responseText;
		
			result = result.replace("*","");
			
			if(result.trim()=="SessionExpired*"){
					jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
			}
			else
			{
				var jsonData=JSON.parse(result);

				
				
				document.getElementById("diagType").value=jsonData.DIAGNOSISCODE;
				document.getElementById("diagCode").value=jsonData.DIAGNOSISCODE;

				document.getElementById("mainCatName").options[0]=new Option(jsonData.MAINCATNAME,jsonData.MAINCATCODE);
				document.getElementById("mainCatCode").value=jsonData.MAINCATCODE;

				document.getElementById("catName").options[0]=new Option(jsonData.CATNAME,jsonData.CATCODE);
				document.getElementById("catCode").value=jsonData.CATCODE;

				document.getElementById("subCatName").options[0]=new Option(jsonData.SUBCATNAME,jsonData.SUBCATCODE);
				document.getElementById("subCatCode").value=jsonData.SUBCATCODE;

				document.getElementById("diseaseName").options[0]=new Option(jsonData.DISEASENAME,jsonData.DISEASECODE);
				document.getElementById("diseaseCode").value=jsonData.DISEASECODE;

				document.getElementById("disAnatomicalName").options[0]=new Option(jsonData.ANATOMICALNAME,jsonData.ANATOMICALCODE);
				document.getElementById("disAnatomicalCode").value=jsonData.ANATOMICALCODE;
				
				
			
			}
			
   		}};
	}
	url = "./patientDetails.do?actionFlag=getdiagDtlsAuto&callType=Ajax&disAnatomicalCode="+disAnatomicalCode; 
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}

/* Function to segregate Dental Clinics and Dental Hospitals by providing the option of IP  */
<%--function fn_dentalCheck()
{
	if(document.getElementById("patNo"))
	{
	var data=document.getElementById("patNo");
	var patientId=data.innerText;
	patientId=patientId.replace(/[^a-z0-9\s]/gi, '');
	patientId=patientId.trim();
	var uid= '<%=session.getAttribute("UserID") %>' ;
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
			jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
			}   
		url = "./patientDetails.do?actionFlag=checkClinic&callType=Ajax&userId="+uid+"&patientId="+patientId; 
		xmlhttp.onreadystatechange=function()
			{
	   		if(xmlhttp.readyState==4)
	   		{
	   			var result=xmlhttp.responseText;
	   			result=result.replace(/[^a-z0-9\s]/gi, '');
	   			result=result.trim();
	   			if(result!="hospital")
	   			{
	   				document.getElementById("patientType1").style.visibility = "hidden";
	   			
	   			}
	   			
	   		}
		}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}--%>

function fn_enableIp()
{
	var invName=document.getElementById("otherInvName").value;
	if(invName!=null || invName!="")
	{
document.forms[0].patientType[1].disabled=false;
	}
	else
	{
		document.forms[0].patientType[1].disabled=true;
	}
}

function validateInnerNum(input)
{	
	
	var hospGovu = document.getElementById("hospGovu").value;

	
	var a=input.value;
	var fr=partial(focusBox,input);
	var regAlphaNum=/^[0-9.]+$/;
	var hospId=document.getElementById("hospitalId").value;
	//if(hospId!=null && hospId!='EHS34')
	 if(hospGovu!=null && hospGovu!="G")
		{
		 
     if(input.id=='GE1' || input.id=='GE2'){
     	 document.getElementById('GE3').innerHTML='<input type="text" id="GE3" value="" readOnly/>';
     }}
		
	if(a.trim()=="")
		{
		jqueryErrorMsg('Number Validation',"Blank spaces are not allowed for "+input.title,fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	 
	if(a.charAt(0)=="." || a.charAt(0)==" " || a.charAt(0)=="0")
	{
		jqueryErrorMsg('Number Validation',input.title+ " should not start with . or space or 0",fr);
		partial(focusBox,input);
		input.value="";
		return false;
	}
	 
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Number Validation',"Only numbers and . are allowed for "+input.title,fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
	
	if(input.id=='GE1' || input.id=='GE2' || input.id=='GE11' || input.id=='GE12' || input.id=='GE13' || input.id=='GE14' || input.id=='GE15' || input.id=='BP1' || input.id=='BP2')
if(input.value.split(".").length-1>1){
	jqueryErrorMsg('Number Validation',"Please Enter Correct Value in "+input.title,fr);
	partial(focusBox,input);
	input.value="";
	return false;
}
	
	if(input.id=='GE1'){
		if(input.value>264){
			jqueryErrorMsg('Number Validation'," Height Should be in range of 0- 264 cm.",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
			
		heightVar=input.value;
		
		
		var weightVar=document.forms[0].GE2.value;
		//if(hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")
		{
		if(heightVar!=null && weightVar!='' && weightVar!=null){
			var heightVarr=heightVar*1/100;
			var bmiCal=((weightVar*1)/(heightVarr*heightVarr)).toFixed(2);
			document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
			}
		}}
	
	if(input.id=='GE2'){
		if(input.value>300){
			jqueryErrorMsg('Number Validation', " Weight Should be in range of 0- 300 Kg.",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		
		//if(hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")
		{
		weightVar=input.value;
		var heightVar=document.forms[0].GE1.value;
		if(heightVar!=null && heightVar!='' && weightVar!=null){
		var heightVarw=heightVar*1/100;
		var bmiCal=((weightVar*1)/(heightVarw*heightVarw)).toFixed(2);
		document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
		}}			
		}
	if(input.id=='GE12'){
		if(input.value>250){
			jqueryErrorMsg('Number Validation', " Pulse rate should be in range of 0-250 per minute",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}		
		}
	if(input.id=='GE13'){
		if(input.value>60){
			jqueryErrorMsg('Number Validation', " Respiration should be in range of 0-60 per minute",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}		
		}
	if(input.id=='GE14'||input.id=='GE15'||input.id=='BP1'||input.id=='BP2'){
		if(input.value>300 || input.value<0){
			jqueryErrorMsg('Number Validation',"BP range should be between 0-300 ",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		}	
	if(input.id=='GE11'){	
		
		//if(hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")
		
			{
			
		var a=input.value;
		var fr=partial(focusBox,input);
		var regAlphaNumT=/^[0-9.CF]+$/;
		var inputlength=input.value.length;
		var mainStrlength=inputlength-1;
		var substr=input.value.slice(-1);
		var mainstr=input.value.substring(0,mainStrlength);
		
		if(document.forms[0].temp[0].checked==true){
			
			if(input.value<24 || input.value>45){
				jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-45 C",fr);
				input.value="";
				return false;
				}
			}
	   else if(document.forms[0].temp[1].checked==true){
			if(input.value<75 || input.value>111){
				jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 75-111 F",fr);
				input.value="";
				return false;
				}
			}
		else{
			jqueryErrorMsg('Temperature Validation',"Please Select C or F",fr);
			input.value="";
			return false;
			}				
		}
		//else if(hospId!=null && hospId =="EHS34")
		else if(hospGovu!=null && hospGovu=="G")
			{
			
    
 	 
			var a=input.value;
			var fr=partial(focusBox,input);
			var regAlphaNumT=/^[0-9.CF]+$/;
			var inputlength=input.value.length;
			var mainStrlength=inputlength-1;
			var substr=input.value.slice(-1);
			var mainstr=input.value.substring(0,mainStrlength);

				if(input.value<24 || input.value>111){
					jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-111 C/F",fr);
					input.value="";
					return false;
					}
				
			
					
			}
	}
}
</script>





</head>
</html>

