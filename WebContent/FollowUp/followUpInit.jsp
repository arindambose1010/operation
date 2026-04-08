<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<head>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<script src="js/DateTimePicker.js"></script>
<title>Follow Up Initiation</title>
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript">
var drugs=new Array();
var drugCount=0;
var genInvestAttachId=0;
var genInventCount=0;
var genInventList=new Array();

String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
}

//jquery alerts functions
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
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
			if(fieldName.charAt(0)=='g')
			{
				filecell=oRow.cells[2];
			}
			else if(fieldName.charAt(0)=='a')
			{
				filecell=oRow.cells[5];
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
 	//var filepath = document.upload.file.value;
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
		/*var oRow = input.parentNode.parentNode; 
		var filecell=oRow.cells[5];
		filecell.innerHTML='<input type="file"  id='+fieldId+' name='+fieldName+' onchange="checkBrowser(this)"/>';*/
	}
}
function checkFileNameMatch(input)
{
	var curFile=input.value;
	var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
	if(rtVal ==0) 
		{
		jqueryErrorMsg('File Name Validation',"File name should not contain special characters");
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
function addDrugs()
{
if(document.getElementById("drugTypeCode").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugTypeCode"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Drug Type Name",fr);
	return false;
	}
if(document.getElementById("drugSubTypeName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugSubTypeName"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Drug Sub Type Name",fr);
	return false;
	}
if(document.getElementById("drugName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugName"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Drug Name",fr);
	return false;
	}
if(document.getElementById("route").value=="")
	{
	var fr=partial(focusBox,document.getElementById("route"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Drug Name",fr);
	return false;
	}
if(document.getElementById("strength").value=="")
	{
	var fr=partial(focusBox,document.getElementById("strength"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Drug Name",fr);
	return false;
	}
if(document.getElementById("dosage").value=="")
	{
	var fr=partial(focusBox,document.getElementById("dosage"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please enter Dosage",fr);
	return false;
	}
if(document.getElementById("medicatPeriod").value=="")
	{
	var fr=partial(focusBox,document.getElementById("medicatPeriod"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please enter Medication Period",fr);
	return false;
	}


if(document.getElementById("dosage").value*1=="0")
{
	var fr=partial(focusBox,document.getElementById("dosage"));
	jqueryAlertMsg('Drug Addition Required Fields',"Dosage cannot be zero",fr);
    document.getElementById("dosage").value="";
return false;
}
if(document.getElementById("medicatPeriod").value*1=="0")
{
	var fr=partial(focusBox,document.getElementById("medicatPeriod"));
	jqueryAlertMsg('Drug Addition Required Fields',"Medication Period cannot be zero",fr);
    document.getElementById("medicatPeriod").value="";
return false;
}


for(var c=0;c<drugs.length;c++)
{
var drugValues=drugs[c].split("~");
if(drugValues[2]==document.getElementById("drugName").value)
	{
	jqueryErrorMsg('Unique Drug Validation',"Drug Name already added.Please select another Drug Name",fr);
	return false;
	}
}
	var drugTable=document.getElementById("drugsTable");    
	var newRow=drugTable.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td width="3%" class="tbcellBorder">'+parseInt(drugCount+1)+'</td>';
	var newcell=newRow.insertCell(1);
	newcell.innerHTML='<td width="12%" class="tbcellBorder">'+document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(2);
	newcell.innerHTML='<td width="12%" class="tbcellBorder">'+document.getElementById("drugSubTypeName").options[document.getElementById("drugSubTypeName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(3);
	newcell.innerHTML='<td width="12%" class="tbcellBorder">'+document.getElementById("drugName").options[document.getElementById("drugName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(4);
	newcell.innerHTML='<td width="12%" class="tbcellBorder">'+document.getElementById("route").value+'</td>';
	newcell=newRow.insertCell(5);
	newcell.innerHTML='<td width="10%" class="tbcellBorder">'+document.getElementById("strength").value+'</td>';
	newcell=newRow.insertCell(6);
	newcell.innerHTML='<td width="10%" class="tbcellBorder">'+document.getElementById("dosage").value+'</td>';
	newcell=newRow.insertCell(7);
	newcell.innerHTML='<td width="10%" class="tbcellBorder">'+document.getElementById("medicatPeriod").value+'</td>';
	newcell=newRow.insertCell(8);
	newcell.innerHTML='<td width="8%" class="tbcellBorder">'+document.getElementById("batchNumber").value+'</td>';
	newcell=newRow.insertCell(9);
	newcell.innerHTML='<td width="8%" class="tbcellBorder">'+document.getElementById("drugExpiryDt").value+'</td>';
	newcell=newRow.insertCell(10);
	newcell.innerHTML='<td width="5%" class="tbcellBorder"><input class="but" type="button" value="Delete" name='+document.getElementById("drugName").value+' id='+document.getElementById("drugName").value+' onclick="removeDrug(this)"/></td>';

	var deleteButName=document.getElementById("drugName").value;
	 document.getElementById(deleteButName).onclick = function(){
		 confirmRemoveRow(this,"drug");
		 }; 

		 
	var drug=document.getElementById("drugTypeCode").value+"~"+document.getElementById("drugSubTypeName").value+"~"+document.getElementById("drugName").value+"~"+
			 document.getElementById("route").value+"~"+document.getElementById("strength").value+"~"+document.getElementById("dosage").value+"~"+document.getElementById("medicatPeriod").value
			 +"~"+document.getElementById("batchNumber").value+"~"+document.getElementById("drugExpiryDt").value;
    drugs[drugCount]=drug+"@";
    document.getElementById("drugs").value=drugs;
	drugCount++;
	if(drugCount>0)
		{
		document.getElementById("drugsTable").style.display="";
		}
	document.getElementById("drugTypeCode").value="-1";
	document.getElementById("dosage").value="";
	document.getElementById("medicatPeriod").value="";
	document.getElementById("batchNumber").value="";
	document.getElementById("drugExpiryDt").value="";
	getDrugSubTypeList();
	$("body").mCustomScrollbar("update");
	
}
function confirmRemoveRow(src,type)
{
	var fr;
	var fr;
	 if(type=="drug")
		{
		fr=partial(removeDrug,src);
		jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug',fr);
		}
	else if(type=="geninvestigations")
		{
		fr=partial(removeGenInvestigations,src);
		jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation",fr);
		}
	
}
function removeDrug(src)
{
	var oRow=src.parentNode.parentNode;
	drugs.splice(oRow.rowIndex-1,1);
	document.getElementById("drugs").value=drugs;
	document.getElementById("drugsTable").deleteRow(oRow.rowIndex);
	drugCount--;
	for(var i=1;i<=drugCount;i++)
		{
			var newRow=document.getElementById("drugsTable").rows[i];
			var snoCell=newRow.cells[0];
			snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
		}
	if(drugCount==0)
		{
			document.getElementById("drugsTable").style.display="none";
		}
	$("body").mCustomScrollbar("update");
	
}
function addTooltip(input) 
{
	var numOptions = document.getElementById(input).options.length;
	 for ( var i = 0; i < numOptions; i++)
		document.getElementById(input).options[i].title = document
				.getElementById(input).options[i].text;
} 
function addGenInvestigation(){
	if(document.getElementById("genBlockInvestName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("genBlockInvestName"));
	jqueryAlertMsg('General Investigation Addition Required Fields',"Please select Investigation Block",fr);
	return false;
	}
	if(document.getElementById("genInvestigations").value=="-1")
		{
		var fr=partial(focusBox,document.getElementById("genInvestigations"));
		jqueryAlertMsg('General Investigation Addition Required Fields',"Please Select Investigations",fr);
		return false;
		}
	for(var c=0;c<genInventList.length;c++)
	{
	if(genInventList[c].indexOf(document.getElementById("genInvestigations").value) !== -1)
		{
		var fr=partial(focusBox,document.getElementById("genInvestigations"));
		jqueryErrorMsg('Unique Investigation Validation',"Investigation Name already added.Please select another Investigation Name",fr);
		return false;
		}
	}
	if(genInventCount<=15)
	{
		var investTableId=document.getElementById('genTestTable');        
		var newRow=investTableId.insertRow(-1);
		//var newcell=newRow.insertCell(0);
		//newcell.innerHTML='<td width="10%">'+parseInt(genInventCount+1)+'</td>';
		var newcell=newRow.insertCell(0);
		newcell.innerHTML='<td width="30%">'+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+'</td>';
		var newcell=newRow.insertCell(1);
		newcell.innerHTML='<td width="25%">'+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="25%"><input type="file"  id='+document.getElementById("genInvestigations").value+' name="genAttach['+genInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
		newcell=newRow.insertCell(3);
		newcell.innerHTML='<td width="20%"><input class="but" type="button" value="Delete" name='+document.getElementById("genInvestigations").value+'d id='+document.getElementById("genInvestigations").value+'d /></td>';
		var deleteButName=document.getElementById("genInvestigations").value+'d';
		document.getElementById(deleteButName).onclick = function(){
			 //confirmRemoveRow(this,"geninvestigation");
			fr=partial(deleteGenInvest,this,document.getElementById("genInvestigations").value);
			jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
			 }; 
		var genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genInvestigations").value+"~"+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text;
		
		genInventList[genInventCount]=genInvest;
	    genInventCount++;
	    if(genInventCount>0)
			{
			document.getElementById("genTestTable").style.display="";
			}
		
		genInvestAttachId=genInvestAttachId+1;
		
	}
	else
	{
		jqueryAlertMsg('Investigations Maximum Allowed',"Cannot add more than 15 tests");
	}
		$("body").mCustomScrollbar("update");
		$("body").mCustomScrollbar("scrollTo","top");   
}
	function deleteGenInvest(src,investId){

	    var oRow=src.parentNode.parentNode;
		genInventList.splice(oRow.rowIndex-1,1);		
	    document.getElementById("genTestTable").deleteRow(oRow.rowIndex);
		genInventCount--;		
			if(genInventCount==0)
				{
				document.getElementById('genTestTable').style.display='none';				        
				}
			$("body").mCustomScrollbar("update");
			$("body").mCustomScrollbar("scrollTo","top");   
				}

function getDrugSubTypeList()
{
	document.getElementById("drugCode").value="";
	document.forms[0].drugSubTypeName.options.length=1;
	getDrugNameList();
	if(document.getElementById("drugTypeCode").value=="-1")
		{
		return false;
		}
	else
		{
	var drugTypeCode=document.getElementById("drugTypeCode").value;
	document.getElementById("drugCode").value=drugTypeCode;
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
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            if(resultArray!=null)
            {
            	resultArray = resultArray.replace("[","");
                resultArray = resultArray.replace("@]*","");            
                var drugSubList = resultArray.split("@,"); 
                if(drugSubList.length>0)
                {  
                		document.forms[0].drugSubTypeName.options.length=0;
                        document.forms[0].drugSubTypeName.options[0]=new Option("---select---","-1");
                	for(var i = 0; i<drugSubList.length;i++)
               		 {	
                  
                     var arr=drugSubList[i].split("~");
                   
                     if(arr[1]!=null && arr[0]!=null)
                     {
                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 document.forms[0].drugSubTypeName.options[i+1] =new Option(val1,val2);
                     }
                }
            }
            }
        }
        }       
    }
    	
	url = "./followUpAction.do?actionFlag=getDrugSubList&lStrDrugTypeId="+drugTypeCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDrugNameList()
{
	document.getElementById("drugSubTypeCode").value="";
	document.forms[0].drugName.options.length=1;
	getDrugDetails();
	if(document.getElementById("drugSubTypeName").value=="-1")
		{
		return false;
		}
	else
		{
	var drugSubTypeCode=document.getElementById("drugSubTypeName").value;
	document.getElementById("drugSubTypeCode").value=drugSubTypeCode;
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
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            
            if(resultArray!=null)
            {
            	resultArray = resultArray.replace("[","");
                resultArray = resultArray.replace("@]*","");            
                var drugList = resultArray.split("@,"); 
                if(drugList.length>0)
                {  
                		document.forms[0].drugName.options.length=0;
                        document.forms[0].drugName.options[0]=new Option("---select---","-1");
                	for(var i = 0; i<drugList.length;i++)
               		 {	
                  
                     var arr=drugList[i].split("~");
                   
                     if(arr[1]!=null && arr[0]!=null)
                     {
                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 document.forms[0].drugName.options[i+1] =new Option(val1,val2);
                     }
                }
            }
            }
        }
    }
    	
	url = "./followUpAction.do?actionFlag=getDrugList&lStrDrugSubTypeId="+drugSubTypeCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDrugDetails()
{
	document.getElementById("asriDrugCode").value="";
	document.getElementById("route").value="";
	document.getElementById("strength").value="";
	if(document.getElementById("drugName").value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("asriDrugCode").value=document.getElementById("drugName").value;
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
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            
            if(resultArray!=null)
            {
                resultArray = resultArray.replace("*","");  
                var drugDetailsList = resultArray.split("~"); 
                if(drugDetailsList.length>0)
                	{
                	if(drugDetailsList[0]!="null")
                		document.getElementById("route").value=drugDetailsList[0];
                	if(drugDetailsList[1]!="null")
	                	document.getElementById("strength").value=drugDetailsList[1];
                	}
            }
        }
    }    	
	url = "./followUpAction.do?actionFlag=getDrugDetails&lStrDrugCode="+document.forms[0].drugName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function fn_submitDtls(){
	var lErrorMsg='';
	var lField = '';

	var genTestsCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {	   
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
			
           if(val==null || val=="")
           	{
        	 if(document.forms[0].elements[temp].name.charAt(0)=='g')
        	   genTestsCount=genTestsCount+1;
        	 
			if(lField=='')
           		lField=document.forms[0].elements[temp].id;  
           	}
           else
			{
        	   var curFile=val;
   			var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
   	if(rtVal ==0) 
   		{
   		jqueryErrorMsg('File Name Validation',"File name should not contain special characters");
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
		
			}
           }
         }
	if(document.forms[0].bloodPS.value=='-1'){
	if(lErrorMsg=='')
     lErrorMsg=lErrorMsg+"- Select Systolic Blood Pressure <br>"; 
     if(document.forms[0].bloodPS.value=='-1')
    	 if(lField=='') lField="bloodPS";
}
	if(document.forms[0].bloodPD.value=='-1'){
		if(lErrorMsg=='')
		     lErrorMsg=lErrorMsg+"- Select Diastolic Blood Pressure <br>"; 
		     if(document.forms[0].bloodPD.value=='-1')
		    	 if(lField=='') lField="bloodPD";
	}
	if(document.forms[0].pluseRate.value=='-1'){
	if(lErrorMsg=='')
     lErrorMsg=lErrorMsg+"- Select Pulse Rate <br>"; 
    if(lField=='') lField="pluseRate";
}
	if(document.forms[0].temp.value=='-1'){
	if(lErrorMsg=='')
     lErrorMsg=lErrorMsg+"- Select Temperature <br>"; 
    if(lField=='')  lField="temp";
}
	if(document.forms[0].respRate.value=='-1'){
	if(lErrorMsg=='')
     lErrorMsg=lErrorMsg+"- Select Respiration Rate <br>"; 
    if(lField=='') lField="respRate";
}
	if(document.forms[0].heartRate.value=='-1'){
	if(lErrorMsg=='')
     lErrorMsg=lErrorMsg+"- Select Heart Sound <br>"; 
     if(lField=='') lField="heartRate";
}
	if(document.forms[0].lungs.value==""){
 		
 	if(lField=='') lField = "lungs";
 	if(lErrorMsg=='') lErrorMsg=lErrorMsg+"- Please Enter Lungs <br>"; 
}
	if(document.forms[0].fluidIn.value==""){
 		
 		if(lField=='') lField = "fluidIn";
 		if(lErrorMsg=='') lErrorMsg=lErrorMsg+"- Please Enter Fluid In <br>"; 
}
	if(document.forms[0].fluidOut.value==""){
 		
 		if(lField=='') lField = "fluidOut";
 		if(lErrorMsg=='') lErrorMsg=lErrorMsg+"- Please Enter Fluid Out <br>"; 
}
/*	if(document.forms[0].hemoglobin.value=='-1'){
 		if(lErrorMsg==null){
        lErrorMsg='All Fields  marked are mandatory <br>';
      }
     lErrorMsg=lErrorMsg+"- Select Hemoglobin <br>"; 
}*/	
	if(genInventList.length==0){
		 if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select General Investigations <br>";
				}
		        if(lField=='')
		        lField='genInvestigations';   
		}
	 if(genTestsCount>0)
	  	{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory <br>";
		    } 
	  	} 	
	  	
	
   
	if(drugCount==0)
	{
	if(lErrorMsg=='')
     lErrorMsg=lErrorMsg+"- Add Drug (Drug Type,Drug Sub Type,Drug Name,Route,Strength,Dosage,Medication Period,Batch Number,Drug Expiry Date Details) <br>";
      if(lField=='')
    	 lField='addDrug';
	}
	
	var nextFupDt='${nextFupDt}';
	if(nextFupDt!='hide')
		{
			if(document.forms[0].nxtFollowUpDt.value=="" || document.forms[0].nxtFollowUpDt.value==null)
				{
				if(lErrorMsg=='')
				     lErrorMsg=lErrorMsg+" Please select Next FollowUp Date <br>";
				if(lField=='')
			    	 lField='nxtFollowUpDt';
				}
		}	
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		var fr=partial(focusBox,document.getElementById(lField));
		jqueryAlertMsg('FollowUp Mandatory Fields',lErrorMsg,fr);
		
	}
	else{		
			var fr = partial(fn_confirm);
       		jqueryConfirmMsg('FollowUp Submit','Do you want to continue ?',fr);			
	}
}
function fn_confirm(){

	var selGenInvData='';
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
	document.forms[0].action="/Operations/followUpAction.do?actionFlag=saveFollowUpDtls&caseId=${caseId}&selInvest="+selGenInvData;
	document.forms[0].submit(); 
}
function chkSpecailChars(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
}


function fn_onload(){
	var nextFupDt='${nextFupDt}';
	var nxtFollowUpDt='${nxtFollowUpDt}';
	
	if(nxtFollowUpDt!=null)
		{
			//var nxtFollowUpMin=nxtFollowUpDt;
			
			var nxtFollowUpMin = new Date(nxtFollowUpDt.substring(0,4),nxtFollowUpDt.substring(5,7)-1,nxtFollowUpDt.substring(8,10));
			var nxtFollowUpMax = new Date(nxtFollowUpDt.substring(0,4),nxtFollowUpDt.substring(5,7)-1,nxtFollowUpDt.substring(8,10));;
			nxtFollowUpMax.setDate(nxtFollowUpMax.getDate() + 90);
		}
	else 
		var nxtFollowUpMin=new Date();

	$(function() {
		$( "#nxtFollowUpDt" ).datepicker({
			//defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			numberOfMonths: 1,
			showOn: "both", 
	         buttonImage: "images/calend.gif",
	         buttonText: "Calendar",
	         buttonImageOnly: true,
			  maxDate: nxtFollowUpMax,
			minDate: nxtFollowUpMin
		});	
	});
	
	if('${msg}'!="")
		{
		var fr = partial(fn_checkMsg,'${msg}');
		jqueryInfoMsg('FollowUp Information','${msg}',fr);
		
		}
	
	if(nextFupDt=='hide')
		{
			document.getElementById("followUp1").style.display="none";
			document.getElementById("followUp2").style.display="none";
			document.getElementById("followUp3").style.display="none"; 
		}
	
}
function fn_checkMsg(msg){
	if(msg!="Uploaded File Size Should not exceed 200KB")
        window.close();
}
var minDate=new Date();
$(function()
		{
			$("#drugExpiryDt").datepicker(
				{
					changeMonth:true,
					changeYear:true,
					numberOfMonths:1,
					showOn:"both",
					buttonImage: "images/calend.gif",
			        buttonText: "Calendar",
			        buttonImageOnly: true,
			        minDate:minDate,
			        maxDate:"+5y"
				});
		});
		
		
		
function validateAlphaNumSpace(arg1,input)
{
	var fr = partial(focusBox,input);
	var a=input.value;
	if(a.trim()=="")
	{
	input.value="";
	jqueryErrorMsg('Text Validation',"Please Enter "+arg1,fr);
	return false;
	}
	var regAlphaNum=/^[0-9a-zA-Z ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Text Validation',"Only AlphaNumeric are allowed for "+arg1,fr);
		input.value="";
		return false;
		}
}
function chkAlpha(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	 var fr = partial(focusNClear,textbox1);
 		 jqueryAlertMsg('Follow Up mandatory fields','Blank spaces are not allowed in '+arg2,fr);
    	return false;
    	}
    if(textval.charAt(0)==' '){ 
    	var fr = partial(focusNClear,textbox1);
		 jqueryAlertMsg('Follow Up mandatory fields','Starting blank spaces are not allowed in '+arg2,fr);
    	return false;
    }
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ().,/";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
        	   var fr = partial(focusNClear,textbox1);
      		   jqueryAlertMsg('Follow Up mandatory fields',"Only Characters from A-Z,a-z are allowed in "+arg2,fr);
				return false;
			 }
			}
	  
	if( textval.match(/[,.\(\)\/]{2}/i))
		{
		var fr = partial(focusNClear,textbox1);
		   jqueryAlertMsg('Follow Up mandatory fields',"continuous special characters are not allowed in "+arg2,fr);
		}
	 var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?.";
	    if(iChars.indexOf(textval.charAt(0))!= -1){
	    	var fr = partial(focusNClear,textbox1);
			jqueryAlertMsg('Follow Up mandatory fields','Starting special characters are not allowed in '+arg2,fr);	
	    }

	}
	else 
		return false;
}
function chkAlphaNumeric(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	var fr = partial(focusNClear,textbox1);
		jqueryAlertMsg('Follow Up mandatory fields','Blank spaces are not allowed in '+arg2,fr);
    	return false;
    	}
    if(textval.charAt(0)==' '){ 
    	var fr = partial(focusNClear,textbox1);
		jqueryAlertMsg('Follow Up mandatory fields','Starting blank spaces are not allowed in '+arg2,fr);
    	return false;
    }
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789().,/";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
        	   var fr = partial(focusNClear,textbox1);
       		   jqueryAlertMsg('Follow Up mandatory fields',"Only Characters from A-Z,a-z,0-9 are allowed in "+arg2,fr);
				return false;
			 }
			}
	}
	else 
		return false;
}
function validateAllphaNumericComb(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    regExprAlpha = new RegExp("[a-z/_/$]{1}","i");
    regExprNum = new RegExp("[0-9]{1}","i");
    if(!regExprAlpha.test(textval)){
    	var fr = partial(focusNClear,textbox1);
		jqueryAlertMsg('Alphanumeric check','Only AlphaNumeric are allowed for '+arg2,fr);
     return false;
    }
    if(!regExprNum.test(textval)){
    	var fr = partial(focusNClear,textbox1);
		jqueryAlertMsg('Alphanumeric check','Only AlphaNumeric are allowed for '+arg2,fr);
     return false;
    }

	}
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0);  
}
function validateNumber(arg1,input)
{

	var a=input.value;
	var regDigit=/^\d+$/ ;
	var fr = partial(focusBox,input);
	if(a.search(regDigit)==-1)
		{
		input.value="";
		jqueryErrorMsg('Text Validation',"Only Numbers are allowed for "+arg1,fr);
		//focusBox(input);
		//alert("Only Numbers are allowed for "+arg1);
		return false;
		}
}

function focusNClear(arg)
{	
 aField = arg;
 aField.value='';
 setTimeout("aField.focus()", 0);  
}
function fn_resetAll(){
	var fr;var msg='Do you want to reset?';
	fr=partial(fn_resetDtls);
	jqueryConfirmMsg('Case Details Reset Confirmation',msg,fr);
}
function fn_resetDtls(){
	document.getElementById("bloodPS").value=-1;
	document.getElementById("bloodPD").value=-1;
	document.getElementById("pluseRate").value=-1;
	document.getElementById("temp").value=-1;
	document.getElementById("respRate").value=-1;
	document.getElementById("heartRate").value=-1;
	document.forms[0].lungs.value="";
	document.forms[0].fluidIn.value="";
	document.forms[0].fluidOut.value="";

	document.getElementById("genBlockInvestName").value=-1;
	document.getElementById("genInvestigations").value=-1;
	
	genInventCount=0;	
	var genTestTable = document.getElementById("genTestTable");
	for(var count = genTestTable.rows.length-1 ; count>0; count--)
		{
		genTestTable.deleteRow(count);
		}
	document.getElementById("genTestTable").style.display="none";
	genInventList=new Array();

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
	document.getElementById("batchNumber").value="";
	document.getElementById("drugExpiryDt").value="";
	document.getElementById("nxtFollowUpDt").value="";
    drugCount=0;	
	var drugTable = document.getElementById("drugsTable");
	for(var count = drugTable.rows.length-1 ; count>0; count--)
		{
		drugTable.deleteRow(count);
		}
	document.getElementById("drugsTable").style.display="none";
	drugs=new Array();

	$("body").mCustomScrollbar("update");
	$("body").mCustomScrollbar("scrollTo","top");   
}
function getGenInvestigation(){
	if(document.getElementById("genBlockInvestName").value=="-1")
	{
	return false;
	}
else
	{
	var blockId=document.getElementById("genBlockInvestName").value;
	var xmlhttp;var url;
    if (window.XMLHttpRequest)
    {xmlhttp=new XMLHttpRequest();}
    else if (window.ActiveXObject)
    {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
    else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
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
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var opIcdList = resultArray.split("@,"); 
                	if(opIcdList.length>0)
                	{  
                		document.forms[0].genInvestigations.options.length=0;
                        document.forms[0].genInvestigations.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<opIcdList.length;i++)
               		 	{	
                     		var arr=opIcdList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].genInvestigations.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }    	
	url = "./followUpAction.do?actionFlag=getGenInvestList&callType=Ajax&lStrBlockId="+blockId;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}
</script>
</head>
<body onload="fn_onload()">
<html:form action="/followUpAction.do" method="post" enctype="multipart/form-data">
<html:hidden name="followUpForm" property="drugs" styleId="drugs"/>
<table border="0" width="99%" height="100%" align="center" bordercolor="black" style="margin:2px;">
<tr><td colspan="5" class="tbheader">
<b>FOLLOW - UP </b></td></tr>
<tr><td>
<table border="0" width="95%" align="center" bordercolor="blue">
<tr><td class="labelheading1 tbcellCss"><b>Blood Pressure</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:select property="bloodPS" name="followUpForm" title="Blood Pressure-systolic" styleId="bloodPS" style="WIDTH: 8em;">
<html:option value="-1">--Select--</html:option>
<c:forEach var="systolic" begin="50" end="210">
   <option value="${systolic}" title="${systolic}"> < c:out value="${systolic}" /></option>                           
</c:forEach>
</html:select>
<html:select property="bloodPD" name="followUpForm" styleId="bloodPD" style="WIDTH: 8em;" title="Blood Pressure-diastolic">
<html:option value="-1">--Select--</html:option>
<c:forEach var="diastolic" begin="33" end="120">
   <option value="${diastolic}" title="${diastolic}"> < c:out value="${diastolic}" /></option>                           
  </c:forEach>
</html:select>
</td> 
<td class="labelheading1 tbcellCss"><b>Pulse Rate</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:select property="pluseRate" title="Pulse Rate" name="followUpForm" styleId="pluseRate" style="WIDTH: 16em;">
<html:option value="-1">--Select--</html:option>
<c:forEach var="pulseRate" begin="40" end="160">
   <option value="${pulseRate}" title="${pulseRate}"> < c:out value="${pulseRate}" /></option>                           
  </c:forEach>
</html:select></td> 
</tr>

<tr><td class="labelheading1 tbcellCss"><b>Temperature</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:select property="temp" title="Temperature" name="followUpForm" styleId="temp" style="WIDTH: 16em;">
<html:option value="-1">--Select--</html:option>
<c:forEach var="temperature" begin="36" end="42">
   <option value="${temperature}" title="${temperature}"> < c:out value="${temperature}" /></option>                           
  </c:forEach>
</html:select></td> 
	<td class="labelheading1 tbcellCss"><b>Respiration Rate</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:select property="respRate" name="followUpForm" title="Respiration Rate" styleId="respRate" style="WIDTH: 16em;">
<html:option value="-1">--Select--</html:option>
<c:forEach var="respRate" begin="5" end="50">
   <option value="${respRate}" title="${respRate}"> < c:out value="${respRate}" /></option>                           
  </c:forEach>
</html:select></td> 
</tr>
<tr><td class="labelheading1 tbcellCss"><b>Heart Sound</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:select property="heartRate" title="Heart Sound" name="followUpForm" styleId="heartRate" style="WIDTH: 16em;">
<option value="-1">--Select--</option>
<option value="S1" title="S1">S1</option>
<option  value="S2" title="S2">S2</option>
<option  value="S3" title="S3">S3</option>
<option  value="S4" title="S4">S4</option>
<option  value="Murmours" title="Murmours">Murmours</option>
<option  value="ExtraSounds" title="ExtraSounds">ExtraSounds</option>
</html:select></td> 
	<td class="labelheading1 tbcellCss"><b>Lungs</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:text name="followUpForm" styleId="lungs" property="lungs" title="Lungs" onchange="javascript:chkAlpha('lungs','Lungs');" maxlength="10" style="WIDTH: 15em;"/></td> 
</tr>
<tr><td class="labelheading1 tbcellCss"><b>Fluid In</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:text name="followUpForm" styleId="fluidIn" title="Fluid In" property="fluidIn" maxlength="10" onchange="javascript:validateAllphaNumericComb('fluidIn','Fluid In');" style="WIDTH: 15em;"/>
</td> 
<td class="labelheading1 tbcellCss"><b>Fluid Out</b><font color="red">*</font></td>
<td class="tbcellBorder"><html:text name="followUpForm" styleId="fluidOut" title="Fluid Out" property="fluidOut" maxlength="10" onchange="javascript:validateAllphaNumericComb('fluidOut','Fluid Out');" style="WIDTH: 15em;" /></td> 
</tr>
<!-- 
<tr><td class="labelheading1 tbcellCss">Hemoglobin</td>
<td class="tbcellBorder"><html:select property="hemoglobin" name="followUpForm" styleId="hemoglobin" style="WIDTH: 18em;">
<html:option value="-1">--Select--</html:option>
<c:forEach var="hameRate" begin="1" end="30">
   <html:option value="${hameRate}"> < c:out value="${hameRate}" /></html:option>                           
  </c:forEach>
</html:select></td></tr> -->
</table>

<tr><td>
<table width="100%">
<tr><td colspan="4"><b>General Investigations</b> <font color="red">*&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4"><table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="25%"><b>Investigation Block Name</b><font color="red">*</font></td>
<td class="labelheading1 tbcellCss" width="25%"><b>Investigation Name</b><font color="red">*</font></td>
<td width="50%">&nbsp;</td></tr>
<tr>
<td class="tbcellBorder">
<html:select name="followUpForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" onchange="getGenInvestigation();">
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
          <html:options property="ID" collection="investigationsList" labelProperty="VALUE"/>
    </html:select></td>
    <td class="tbcellBorder"><html:select name="followUpForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 17em;"  onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
<td ><input class="but" type="button" name="add" value="Add Tests" onclick="addGenInvestigation()"></input></td>
</tr></table></td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTable" style="display:none" border="1">
     	<tr>
     	 	<td width="30%" ><b>Investigation Block Name</b></td>        
        	<td width="30%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="20%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr></table>
</td></tr>
<tr><td>
<table width="100%">
<tr><td class="labelheading1 tbcellCss"><b><font size="3px"><fmt:message key="EHF.Prescription"/>:</font></b></td></tr>
<tr>
<td width="24%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugTypeName"/></b> <font color="red">*</font></td>
<td width="24%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugTypeCode"/></b> <font color="red">*</font></td>
<td width="24%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugSubTypeName"/></b> <font color="red">*</font></td>
<td width="24%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugSubTypeCode"/></b> <font color="red">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="followUpForm" property="drugTypeCode" styleId="drugTypeCode" style="width:16em;" title="Select Drug type" onchange="getDrugSubTypeList()" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="drugsList" labelProperty="VALUE"/>
</html:select></td>
<td class="tbcellBorder"><html:text name="followUpForm"  property="drugCode" styleId="drugCode" title="Drug Type Code" maxlength="10" style="WIDTH:16em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top">
<html:select name="followUpForm" property="drugSubTypeName" styleId="drugSubTypeName" style="width:16em;" title="Select Drug Sub type" onchange="getDrugNameList()" onmouseover="addTooltip('drugSubTypeName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="followUpForm"  property="drugSubTypeCode" styleId="drugSubTypeCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:16em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugName"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugCode"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b> <font color="red">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="followUpForm" property="drugName" styleId="drugName" style="width:16em;" title="Drug Name" onchange="getDrugDetails()" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<%-- <html:options property="ID" collection="asriDrugsList" labelProperty="VALUE"/> --%>
</html:select></td>
<td class="tbcellBorder"><html:text name="followUpForm"  property="asriDrugCode" styleId="asriDrugCode" title="Drug Code" maxlength="10" style="WIDTH:16em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:text name="followUpForm" property="route" styleId="route" maxlength="20" style="WIDTH:16em" onchange="checkAlphaSpace('route','Route')" title="Enter Route" readonly="true"/></td>
<td class="tbcellBorder"><html:text name="followUpForm" property="strength" styleId="strength" maxlength="10" style="WIDTH:16em" onchange="validateAlphaNumSpace('Strength',this)" title="Enter Strength" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.BatchNumber"/></b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugExpiryDate"/></b> <font color="red">*</font></td>
<td colspan="2">&nbsp;</td>
</tr>
<tr>
<td class="tbcellBorder"><html:text name="followUpForm" property="dosage" styleId="dosage" maxlength="1" style="WIDTH:16em" onchange="validateNumber('Dosage',this)" title="Enter Dosage"/></td>
<td class="tbcellBorder"><html:text name="followUpForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:16em" onchange="validateNumber('Medication Period',this)" title="Enter Medication Period"/></td>
<td class="tbcellBorder"><html:text name="followUpForm" property="batchNumber" styleId="batchNumber" maxlength="10" style="WIDTH:16em" onchange="chkAlphaNumeric('Batch Number',this)" title="Enter Batch Number"/></td>
<td class="tbcellBorder" width="90%"><html:text name="followUpForm" property="drugExpiryDt" styleId="drugExpiryDt" maxlength="10" style="WIDTH:16em" title="Select Drug Expiry Date"/></td>
</tr>
<tr>
<td colspan="2" class="tbcellBorder"><input class="but" type="button" name="addDrug" id="addDrug"  value="Add Drugs" onclick="addDrugs()"></input></td>
</tr>

<tr><td colspan="4">
  <table  width="100%"  id="drugsTable" style="display:none" border="1">
      <tr>  
      	<td width="5%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SNO"/></b></td>        
        <td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugTypeName"/></b></td>   
       	<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugSubTypeName"/></b></td>
        <td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugName"/></b></td>
        <td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b></td>
        <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
        <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.BatchNumber"/></b></td>
        <td width="10%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugExpiryDate"/></b></td>
        <td width="5%" class="labelheading1 tbcellCss">&nbsp;</td>
        </tr></table>
</td></tr>
<tr id="followUp1"><td ><b><font size="2px"><fmt:message key="EHF.NextFollowUpHead"/><font color="red">*</font></font></b></td></tr>
<tr id="followUp2">
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.NextFollowUp"/></b> <font color="red">*</font></td>
</tr>
<tr id="followUp3">
<td class="tbcellBorder"><html:text name="followUpForm" property="nxtFollowUpDt" styleId="nxtFollowUpDt" title="Please select next FollowUp Date"></html:text>
</tr>
</table>
</td></tr>
<!-- 
<tr><td colspan="4" align="center" class="tbcellBorder">
<button type="button" class="but" value="Add Reports"  onclick="fn_addAttachments()">Add Reports</button>
<button type="button" class="but"  value="Add Documents"  onclick="fn_addAttachments()">Add Documents</button>
<button type="button" class="but"  value="Add Photographs"  onclick="fn_addAttachments()">Add Photographs</button></td>
</tr>
 -->
<tr><td colspan="4" align="center" class="tbcellBorder">
<button type="button" class="but" value="Submit" onclick="fn_submitDtls()">Submit</button>
<button type="button" class="but" value="Submit" onclick="fn_resetAll()">Reset</button>
<button type="button" class="but" value="Close" onclick="window.close()">Close</button>
</td></tr>
</table></td></tr>
</table>


</html:form>
</body>
</fmt:bundle>
</html>
