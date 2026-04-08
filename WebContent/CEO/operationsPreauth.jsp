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
<title>Preauth Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>  
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/js/PreauthScripts.js"></script> 
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%> 
<%@ include file="/common/includePatientDetails.jsp"%> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> 
<style>body{font-size:12px !important}
.mainDiv{
 position: relative;
width:92%;
float:right;
padding-right:3%;



}
.but{border-radius:16px;}
legend{
font-size:15px;
}
.tbcellBorder{
font-weight: 700;
}
.labelheading1{
font-weight: 700;
}
.rightone{
padding-right:3%;

}
.centerProgress{
width:50%;
align:center;
padding-top:20%;
padding-left:40%;
background-color:none;
border-radius:10%;
}
</style>
<script type="text/javascript">
var drugIdsarray = new Array(); 
var dbFilesCnt=-1;
var dbFilesArray=new Array();
var drugs=new Array();
var drugCount=0;
var splCount =1;
var splList = new Array();
var splInvestdata = new Array();
var casSurglist = null;
var remEnhlist = null;
var totPkgAmt = '';
var enhAmt='';
var splInvestIds = null;
var surgeryId = null;
var surgertIdsarray = new Array();
var categoryIdsarray = new Array();
//var checkInactiveSurgArray= new Array();
var testAmt = null;
var testSurg = null;
var count=0;
var xxx = null;
var yyy = null;
var hospCodeArray = new Array();
var imageologyArray = new Array();
var labInvestArray = new Array();
var drugArray = new Array();
var implantsArray = new Array();
var hospCodeDivArray = new Array();
var amtListArray = new Array();
var enhancementCnt = 0;
var rmks = null;
var enhancementIdsarray = new Array();
//var comorbidAmt = null;
var comorBidVals=null;
var comorbidFlag=null;
var drugQuantAmt = 0;
var drugsDeletedArray = new Array();
var enhQuantCodeArray = new Array();
var viewType='${viewType}';

// get icd cat List
// jquery alerts functions
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
/* function focusBox(arg)
{	
  aField = arg;
  setTimeout("aField.focus()", 0);  
  var x=getOffset( arg ).top;
  //parent.fn_goToField(x);
} */
function focusBox(arg)
{	
	  aField = arg; 
	  setTimeout("aField.focus()", 0); 
	  
 	  /* var x=getOffset( arg ).top;  */
 	  /* var offset = $(aField).offset();
	  var top = offset.top;
	   top = top+elemJqueryScrollTop; 
	   $("body").mCustomScrollbar("update"); 
	   $("body").mCustomScrollbar("scrollTo",top); */  
	  
	
}
// start case attachments functions
function fn_alertResultMsg(msg)
{
	 //jqueryAlertMsg('Attachments check',msg);
	 alert(msg);
	 return;
	}
function fn_CochlearQues()
{
	
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
	 var url = '/<%=context%>/preauthDetails.do?actionFlag=cochlearQuestionnaire&type=enable&CaseId='+caseId;
	    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function fn_viewCochlearQues()
{
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
	
	 var url = '/<%=context%>/preauthDetails.do?actionFlag=cochlearQuestionnaire&type=disable&CaseId='+caseId;
	    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	
}
function chkSpecailCharsAttach(vFileName)
{
	
   var flag =true;  
   var iChars = "*|\":<>[]{}`\';()$#%&^.,!@?/";    
   var iChars1="-_";
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {  
        	flag=false;
           break;
        } 
    }
    if( vFileName.match(/[\-\_]{2}/i))
	{
    	flag=false;
	}
    if (iChars1.indexOf(vFileName.charAt(0))!=-1 || iChars1.indexOf(vFileName.charAt(vFileName.length-1)) != -1)
	{
    	flag=false;
	}
    return flag;
} 

function checkForSimilarUploadAttach(vFileName)
{
	var vSplit;
	vSplit=vFileName.split("\\");
    vFileName = vSplit[(vSplit.length)-1];
    var  rtVal=0;
    for(k=0;k<dbFilesArray.length;k++)      		
    {	
        if( vFileName == dbFilesArray[k])
        {
        	 rtVal++;
        }
    }
    if(rtVal ==0)
    	{
    	return false;
    	}
    else
    	return true;
	}
	function validateCaseAttach(vFileName)
	{	
		var msg='success';
		if(vFileName == null || vFileName=='')
		 {
		 alert('Please upload file');
		 //jqueryAlertMsg('Attachments check',"Please upload file");
		 msg="failure";
		 }
		 vSplit=vFileName.split("\\");
		vFileName = vSplit[(vSplit.length)-1];	
		 var pos=vFileName.lastIndexOf(".");
		var sub=vFileName.substring(pos+1,vFileName.length); 
		if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP') || (sub=='java')||(sub=='JAVA'))
		{
			flag="false";
		    alert("Cannot Upload exe,rar,war files");
			//jqueryAlertMsg('Attachments check',"Cannot Upload exe,rar,war  files");
			msg="failure";
		} 
		if((sub=='jpg')||(sub=='JPG') || (sub=='jpeg')||(sub=='JPEG') || (sub=='gif')||(sub=='GIF')|| (sub=='bmp')||(sub=='BMP')||(sub=='pdf')||(sub=='PDF'))
		{
			  flag="true";
			 
		}
		else
			{
			 flag="false";
			 //jqueryAlertMsg('Attachments check'," Cannot Upload "+sub + " files . Please attach gif,jpg,bmp,pdf //formats only");
			 alert(" Cannot Upload "+sub + " files . Please attach gif,jpg,bmp,pdf formats only");
			 msg="failure";
			}
		var pos1=vFileName.lastIndexOf(".");
		sub1=vFileName.substring(0,pos1);	
		var flag = chkSpecailCharsAttach(sub1);
		if(!flag)
		 {
			// jqueryAlertMsg('Attachments check',"Special characters are not allowed");
		 alert('Special characters are not allowed');
		 msg="failure";
		 }
		flag=checkForSimilarUploadAttach(vFileName);
		if(flag)
		 {
			// jqueryAlertMsg('Attachments check',"Cannot upload similar attachments");
		 alert('Cannot upload similar attachments');
		 msg="failure";
		 }
		return msg;
	}
	function fn_deleteCaseAttachments(iframeName,sno,filename)
	{
		 var fr = partial(document.getElementById(iframeName).contentWindow.fn_delete1,sno,filename);
		 //jqueryConfirmMsg('Attachments','Do you want to delete attachment ? ',fr);
		 if(confirm("Do you want to delete attachment ?"))
		 {
			document.getElementById(iframeName).contentWindow.fn_delete1(sno,filename);
		 }
				
	}
	
// end 
function focusNClear(arg)
{	
  aField = arg;
  aField.value='';
  setTimeout("aField.focus()", 0);  
  var x=getOffset( arg ).top;
  //parent.fn_goToField(x);
}
function disableFields()
{
	fn_disbleComorBid();
	fn_clearFields();
	}
// end of jquery alerts
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
/*function enableCochlear()
{
	
	var cat=document.forms[0].category.value;
	var cochlearYN="${cochlearYN}";
	
	if(cat=="S16")
	{
		if(cochlearYN=='Y')
		{
			return false;
			
		}
	document.getElementById("enableCochlear").style.display = "block";
	}
	else
		document.getElementById("enableCochlear").style.display = "none";	
}*/
function fn_getICDCatList()
{
	var cochlearYN="${cochlearYN}";
	var cat=document.forms[0].category.value;

	  document.getElementById('myDivSpl').style.display="none";
	 document.getElementById('myDivSplUpload').style.display="none";
	
	if(cochlearYN=='Y' && cat!='S16')
	{
		alert("only cochlear procedures are allowed");
		document.forms[0].category.value='-1';
	}
	if(cat=='S16' && cochlearYN!='Y')
	{
		alert("Cochlear procedures cannot be added with existing procedures");
		document.forms[0].category.value='-1';
	}
	if(document.forms[0].category.value == null || document.forms[0].category.value =='' || document.forms[0].category.value=='-1' )
		{
			if(document.forms[0].category.value=='-1' )
			{
				document.forms[0].icdProcCode.options.length=0;
				document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
				document.forms[0].icdCatCode.options.length=0;
				document.forms[0].icdCatCode.options[0]=new Option("--select--","-1");
				document.forms[0].docSpecReg.options.length=0;
				document.forms[0].docSpecReg.options[0]=new Option("--select--","-1");
				document.getElementById('procUnits').value="-1";
				document.forms[0].procUnits.options.length=0;
				document.forms[0].procUnits.options[0]=new Option("--select--","-1");
				document.getElementById("unitsTd").style.display='none';
				document.getElementById("unitsLabelTd").style.display='none';
			}
			return ;
		}
	
	
	
	/* if(document.forms[0].category.value=='S18')
	{
		document.getElementById("unitsTd").style.display='';
		document.getElementById("unitsLabelTd").style.display='';
	}
	else
	{ */
	
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getIcdCatList&catId='+document.forms[0].category.value+'&callType=Ajax&hospId='+document.forms[0].hospitalId.value;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	
		    		var doctorList=null;
		    	    var resultArray=xmlhttp.responseText;
		    	   
			        var resultArray = resultArray.split("*");
			    	if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		// var fr = partial(parent.sessionExpireyClose);
			    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var catList = resultArray1.split(", @"); 
			            
			                       
			          //  var serviceList = resultArray.split(", @"); 
			            
			        }
			        if(resultArray[1]!=null)
			        {	
			            resultArray2 = resultArray[1].replace("[","");
			            resultArray2 = resultArray2.replace("@]","");            
	                	doctorList = resultArray2.split("@,"); 
			           
			                       
			          //  var serviceList = resultArray.split(", @"); 
			            
			        }
					
		            if(catList.length>0)
		            {   
						if(document.forms[0].icdCatCode.options!=null){  
							document.forms[0].icdCatCode.options.length=0;
							document.forms[0].icdCatCode.options[0]=new Option("--select--","-1");
							document.forms[0].icdProcCode.options.length=0;
							document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
							
						}
						
						 for(var i = 0; i<catList.length;i++)
			                {	
			                    var arr=catList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].icdCatCode.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                    	document.forms[0].icdCatCode.options[0]=new Option("--select--","-1");
										
				                    }
			                }
						
		            }
		            // get treating doctor list
					if(doctorList!=null)
					{
		            	if(doctorList.length>0)
                		{  
                 			document.forms[0].docSpecReg.options.length=0;
                    		document.forms[0].docSpecReg.options[0]=new Option("---select---","-1");
            				for(var i = 0; i<doctorList.length;i++)
                				{		
                     				var arr=doctorList[i].split("~");
                     				if(arr[1]!=null && arr[0]!=null)
                     				{
                         				var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         				var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 				document.forms[0].docSpecReg.options[i+1] =new Option(val1,val2);
                     				}
                				}
                		}	
            		}
		    	
		    }}}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
// get Procedures

function fn_getProcedures()
{
	if(document.forms[0].icdCatCode.value == null || document.forms[0].icdCatCode.value =='' || document.forms[0].icdCatCode.value=='-1' )
		{
			if(document.forms[0].icdCatCode.value=='-1' )
			{
				document.forms[0].icdProcCode.options.length=0;
				document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
				document.getElementById('procUnits').value="-1";
				document.forms[0].procUnits.options.length=0;
				document.forms[0].procUnits.options[0]=new Option("--select--","-1");
				document.getElementById("unitsTd").style.display='none';
				document.getElementById("unitsLabelTd").style.display='none';
			}
			return ;
		}
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
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getProcList&IcdcatId='+document.forms[0].icdCatCode.value+'&asriCode='+document.forms[0].category.value+'&callType=Ajax&hospId='+document.forms[0].hospitalId.value+'&caseId='+caseId;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
				    	alert("Session has been expired");
				    	 parent.sessionExpireyClose();
				    	 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
				    	}
				    	else
				    	{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var procList = resultArray1.split(", @"); 
			        }
					
		            if(procList.length>0)
		            {   
						if(document.forms[0].icdProcCode.options!=null){  
							document.forms[0].icdProcCode.options.length=0;
							document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
						}
						 for(var i = 0; i<procList.length;i++)
			                {	
			                    var arr=procList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].icdProcCode.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                    	document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
										
				                    }
			                }
		            }
		    	
		    }
		}// end of if
		}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}

// end of procedures

// get ICD Procs List
function fn_getICDProcedures()
{
	if(document.forms[0].procCode.value == null || document.forms[0].procCode.value =='' || document.forms[0].procCode.value=='-1' )
		{
		return ;
		}
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getProcList&IcdcatId='+document.forms[0].icdCatCode.value+'&procId='+document.forms[0].procCode.value+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var procList = resultArray1.split(","); 
			        }
					
		            if(procList.length>0)
		            {   
						if(document.forms[0].icdProcCode.options!=null){  
							document.forms[0].icdProcCode.options.length=0;
							document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
						}
						 for(var i = 0; i<procList.length;i++)
			                {	
			                    var arr=procList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].icdProcCode.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                    	document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
										
				                    }
			                }
		            }
		    	
		    }
		}// end of if    
		}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
// end of procList

function fn_getSpecialInvestigations_test()
{
	var category=document.getElementById("category").value;
	var icdProcCode=document.getElementById("icdProcCode").value;
	if(category=='S18')
	{
		document.getElementById("unitsTd").style.display='';
		document.getElementById("unitsLabelTd").style.display='';
		
		document.forms[0].procUnits.options.length=0;
		document.forms[0].procUnits.options[0]=new Option("---select---","-1");
	}
	if(icdProcCode=='-1')
	{
		document.getElementById('procUnits').value="-1";
		document.forms[0].procUnits.options.length=0;
		document.forms[0].procUnits.options[0]=new Option("--select--","-1");
		document.getElementById("unitsTd").style.display='none';
		document.getElementById("unitsLabelTd").style.display='none';
	}
	
	getDentalUnits();
	var xmlhttp;
	var url;
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
	//alert(document.forms[0].category.value);
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSpecialInvestigation&icdProcCode='+document.forms[0].icdProcCode.value+'&callType=Ajax&catCode='+document.forms[0].category.value;
	 xmlhttp.onreadystatechange=function()
		{
		 if(xmlhttp.readyState==4)
		    {
			 var resultArray=xmlhttp.responseText;
		        var resultArray = resultArray.split("*");
		        if(resultArray[0]=="SessionExpired"){
					 alert("Session has been expired");
					 parent.sessionExpireyClose();
					 //var fr = partial(parent.sessionExpireyClose);
		    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
					 }
					 else
					 {
		        if(resultArray[0]!=null)
		        {	
		            resultArray1 = resultArray[0].replace("[","");
		            resultArray1 = resultArray1.replace("@]","");            
		            var invstList = resultArray1.split("@,"); 
		        }
		        var my2div = document.getElementById('myDivSplUpload');
                if (my2div != null) 
            	{
            		 while (my2div.hasChildNodes()) 
            		 {  my2div.removeChild(my2div.lastChild);    }
            	 }
                var myDivSpl = document.getElementById('myDivSpl');
                if (myDivSpl != null) 
                	{
                		 while (myDivSpl.hasChildNodes()) 
                		 {  myDivSpl.removeChild(myDivSpl.lastChild);    }
                	 }
                document.getElementById('myDivSpl').style.display="";
				 document.getElementById('myDivSplUpload').style.display="";
				 
					 for(var i=0;i<=dbFilesArray.length;i++)
						{
							//dbFilesArray.splice(i,1);
							delete dbFilesArray[i];
							//A.splice(0, A.length);

						}
					 splList = new Array();	
	            if(invstList.length>0)
	            { 
	            	for(var i = 0; i<invstList.length;i++)
	                {	
	                    var arr=invstList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null)
	                     {
	                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                         var name1 = val1;
	                         var value1 = val2;
	                         var attachTable=document.getElementById("myDivSpl");
								var attachData=attachTable.innerHTML;
								//alert(attachData);
								attachData=attachData.replace("</TBODY>","");
								attachData = attachData + '<table width="100%" border="0" cellpadding="3"><tr><td width="10%" class="labelheading1 tbcellCss"><input  type="checkbox" id="checkspl'+value1+'" name="checkspl'+value1+'" value="'+name1+'" style="word-wrap:break-word;" width="10%"/>  '+name1+' </td></tr><tr><td width="70%" nowrap="nowrap" class="labelheading1 tbcellCss"><iframe id="iframe1'+value1+'" name="iframe1'+value1+'" height="32" style="width:100%;border:1;"  src="/<%=context%>/preauthDetails.do?actionFlag=getCaseAtachments&caseId='+caseId+'&mode=add&uploadType=SplInvest&surgId='+document.forms[0].icdProcCode.value+'&splinvestdesc='+name1+'&splinvestid='+value1+'&spltype=PRE" frameborder=no scrolling=no> </iframe></td></tr></table>'; 
								attachTable.innerHTML = attachData;
								 splList[i] = value1;
								
	                     }
	            }
	            	 /* if(document.forms[0].icdProcCode.options!=null){  
						 surgeryId = document.forms[0].icdProcCode.value;
							document.forms[0].icdProcCode.options.length=0;
							document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
						} */
	            	surgeryId = document.forms[0].icdProcCode.value;
		    }
	            //parent.fn_resizePage();	
		 
		}
		}// end of if
	
	}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
function fn_getSpecialInvestigations()
{
	var xmlhttp;
	var url;
	var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSpecialInvestigation&suregryId='+document.forms[0].surgery.value+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		// var fr = partial(parent.sessionExpireyClose);
			    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var invstList = resultArray1.split(","); 
			        }
		            if(invstList.length>0)
		            {   
						  var my2div = document.getElementById('myDivSplUpload');
	                         if (my2div != null) 
	                     	{
	                     		 while (my2div.hasChildNodes()) 
	                     		 {  my2div.removeChild(my2div.lastChild);    }
	                     	 }
	                         var myDivSpl = document.getElementById('myDivSpl');
	                         if (myDivSpl != null) 
		                     	{
		                     		 while (myDivSpl.hasChildNodes()) 
		                     		 {  myDivSpl.removeChild(myDivSpl.lastChild);    }
		                     	 }
	                         document.getElementById('myDivSpl').style.display="";
							 document.getElementById('myDivSplUpload').style.display="";
	                         
						 for(var i = 0; i<invstList.length;i++)
			                {	
			                    var arr=invstList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         var ni1 = document.getElementById('myDivSpl');
			                         var newdiv1 = document.createElement('div');
			                         var divIdName1 = 'my'+i+'DivSpl';
			                         newdiv1.setAttribute('id',divIdName1);
			                         var divappend = '';
			                         var divappend1 ='';
			                         var name1 = val1;
			                         var value1 = val2;
			                         
			                         newdiv1.innerHTML ='<br><input  type="checkbox" id="'+value1+'" name="checkspl'+value1+'" value="'+name1+'" />  '+name1+'  ';
			                         ni1.appendChild(newdiv1);
			                         // adding frames for attachments
			                          var ni = document.getElementById('myDivSplUpload'); 
			                         var newdiv = document.createElement('div');
                                    var divIdName = 'my'+i+'DivSplUpload';  
 									newdiv.setAttribute('id',divIdName);
 		 newdiv.innerHTML =  '<td nowrap="nowrap"><br><iframe id="iframe1'+value1+'" name="iframe1'+value1+'1" height="32" style="width:100%;border:1;"  src="/<%=context%>/preauthDetails.do?actionFlag=getCaseAtachments&caseId='+caseId+'&mode=add&uploadType=SplInvest&surgId='+document.forms[0].surgery.value+'&splinvestdesc='+name1+'&splinvestid='+value1+'&spltype=PRE" frameborder=no scrolling=no> </iframe></td>';
										ni.appendChild(newdiv);
										    //splList = new Array();
							               // splList[0] = name1;
							                splList[i] = value1;
							   }
			                     
			                }
						 if(document.forms[0].surgery.options!=null){  
							 surgeryId = document.forms[0].surgery.value;
								document.forms[0].surgery.options.length=0;
								document.forms[0].surgery.options[0]=new Option("--select--","-1");
							}
		            }
		    	
		    }
		}// end of if    
		}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	
}
function subStringRemarks(id,remarkLength)
{
	if(document.getElementById(id).value != null && document.getElementById(id).value !='')
	document.getElementById(id).value = document.getElementById(id).value.substr(0, remarkLength-1);
	}
function check_maxLength(id,remarkLength)
{
	var name = document.getElementById(id).value;
	 if(name != null && name !='' && name.length >= remarkLength)
 	{
 	alert("Remarks length should not exceed " +remarkLength );
 	var fr = partial(subStringRemarks,id,remarkLength);
    //jqueryAlertMsg('Preauth mandatory check',"Remarks length should not exceed " +remarkLength,fr);
 	
 	}	
		/*  if (window.event.keyCode == 86)//Paste 
		 {
		if(name != null && name !='' && name.length >= remarkLength)
		 {   
			alert(name.length);
		alert("Remarks length should not excede " +remarkLength );
		document.getElementById(id).value = document.getElementById(id).value.substr(0, remarkLength-1);
		 }
		 } */
	

}
function checkRemarks(name,remarkLength,id)
{
	var msg = chkSpecailChars(name,remarkLength);
	
	if(msg !=null && msg !='')
	{
		var fr = partial(subStringRemarks,id,3000);
		//jqueryAlertMsg('Preauth mandatory check',msg ,fr);
		alert(msg);
		subStringRemarks(id,3000);
		return;
	}
}
	
function chkSpecailChars(name,remarkLength)
{
    var chars="*|\":<>[]{}`\';$#%&"; //04
    var message1='';
    var msg ='';
    var tbLen = name.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	message1 ="Blank spaces are not allowed in remarks";
    	}
    if(name.charAt(0)==' ')
    	{
    	message1 ="Starting blank spaces are not allowed in remarks";
    	}
    if(remarkLength == 3000)
    	{
    	if(name.length >= 2999)
    	{
    	message1 = "Remarks length should not exceed " +remarkLength ;
    	}
    	}
    else
    	{
    if(name.length >= remarkLength)
    	{
    	message1 = "Remarks length should not exceed " +remarkLength ;
    	}
    	}
    var message='';
    for (var i = 0; i < name.length; i++) 
    {
        if(chars.indexOf(name.charAt(i))!=-1)
        {
            message=" Remarks should not contain special characters ";
            
        }
    }
    return message+message1;
}
	
function fn_addSurgicalDtls()
{
	var category = document.forms[0].category.value;
    var splInvest = null;
	var ichecked =0;
	var iNotChecked=0;
	var errMsg ='';
	var lField='';
	var remarks = document.forms[0].treatingDocRmks.value;
	/* chcek for all mandatory fields selected*/
	if(category == null || category =='' || category =='-1')
		{
		//alert('Please select the category');
		//document.forms[0].category.focus();
		if(errMsg=='')
				errMsg=errMsg+"Please select the category "; 
			if(lField=='')
		        lField='category';
		}
	 if(category !='-1')
		{	
			if(categoryIdsarray!=null && categoryIdsarray.length>0 &&((categoryIdsarray[0].indexOf("S")=='0' && category.indexOf("S")=='-1') || (categoryIdsarray[0].indexOf("M")=='0' && category.indexOf("M")=='-1')))
			{
				if(errMsg=='')
					errMsg=errMsg+"Please select either medical or surgical category "; 
				if(lField=='')
					lField='category';
			}
		
		} 
	if(document.forms[0].icdCatCode.value == null || document.forms[0].icdCatCode.value =='' || document.forms[0].icdCatCode.value =='-1')
	{
	//alert('Please select the ICD category');
	//document.forms[0].icdCatCode.focus();
	if(errMsg=='')
		errMsg=errMsg+"Please select the ICD category"; 
	if(lField=='')
        lField='icdCatCode';
	}
	if(document.forms[0].icdProcCode.value == null || document.forms[0].icdProcCode.value =='' || document.forms[0].icdProcCode.value =='-1')
	{
	//alert('Please select the  Procedure');
	//document.forms[0].icdProcCode.focus();
	if(errMsg=='')
		errMsg=errMsg+"Please select the  Procedure "; 
	if(lField=='')
        lField='icdProcCode';
	}
	if(document.getElementById('procUnits') && document.getElementById("unitsTd").style.display=='' && (document.forms[0].procUnits.value == null || document.forms[0].procUnits.value =='' || document.forms[0].procUnits.value =='-1'))
	{
	//alert('Please select the  Procedure');
	//document.forms[0].icdProcCode.focus();
	if(errMsg=='')
		errMsg=errMsg+"Please select Units"; 
	if(lField=='')
        lField='procUnits';
	}
	if(document.forms[0].docSpecReg.value == null || document.forms[0].docSpecReg.value =='' || document.forms[0].docSpecReg.value =='-1')
	{
	//alert('Please select the  Procedure');
	//document.forms[0].icdProcCode.focus();
	if(errMsg=='')
		errMsg=errMsg+"Please select the Treating doctor "; 
	if(lField=='')
        lField='docSpecReg';
	}
	if(document.forms[0].docSpecReg.value != '-1')
		{
		if(document.forms[0].docSpecReg.value =='0'){
			 if(document.forms[0].treatDocName.value == null || document.forms[0].treatDocName.value =='' || document.forms[0].treatDocName.value =='-1')
				{
				 if(errMsg=='')
						errMsg=errMsg+"Please enter the  Treating doctor name "; 
					if(lField=='')
				        lField='treatDocName';
				}
			 if(document.forms[0].treatDocRegNo.value == null || document.forms[0].treatDocRegNo.value =='' || document.forms[0].treatDocRegNo.value =='-1')
				{
				 if(errMsg=='')
						errMsg=errMsg+"Please ente the  Treating doctor Reg No. "; 
					if(lField=='')
				        lField='treatDocRegNo';
				}
			 if(document.forms[0].treatDocQualification.value == null || document.forms[0].treatDocQualification.value =='' || document.forms[0].treatDocQualification.value =='-1')
				{
				 if(errMsg=='')
						errMsg=errMsg+"Please enter the  Treating doctor qualification  "; 
					if(lField=='')
				        lField='treatDocQualification';
				}
			
			 if(document.forms[0].treatDocContact.value == null || document.forms[0].treatDocContact.value =='' || document.forms[0].treatDocContact.value =='-1')
				{
				 if(errMsg=='')
						errMsg=errMsg+"Please enter the  Treating doctor contact"; 
					if(lField=='')
				        lField='treatDocContact'; 
				}
		}
		}
	if( surgeryId == null || surgeryId =='')
	{
		if(errMsg=='')
			errMsg=errMsg+"Please select the surgery"; 
	//alert('Please select the surgery');
	//return;
	}
	if(errMsg !='')
		{
		var fr = partial(focusBox,document.getElementById(lField));
        //jqueryAlertMsg('Preauth mandatory fields',errMsg,fr);
		alert(errMsg);
        return;
		}
	//alert(surgertIdsarray.length);
	/**check for already added surgical dtls **/
	for(var i=0; i<surgertIdsarray.length;i++)
		{
		if(surgertIdsarray[i]==surgeryId)
			{
			var fr = partial(fn_clearFields);
	        //jqueryAlertMsg('Preauth mandatory fields','Procedure already added',fr);
	        //return;
			alert('Procedure already added');
			fn_clearFields();
			return;
			}
		}	
	/** check for whether special investigations are checked are not**/
	 for(var cntspl=0;cntspl < splList.length;cntspl++)
    {
      var splInvest1 = eval(document.getElementById("checkspl"+splList[cntspl]));
      if(splInvest1.checked)  
      { 
        ichecked=1; 
      }
      else 
      {   
        iNotChecked=1;                  
      }
    } // end of for 
    if(ichecked == 0 && splList.length > 0)
    {  
      alert("Atleast one special investigation is mandatory");    
     // jqueryAlertMsg('Preauth mandatory fields','Atleast one special investigation is mandatory');
      return;
    }
   
    for(var cntspl=0;cntspl < splList.length;cntspl++)
    {
      var splInvest1 = eval(document.getElementById("checkspl"+splList[cntspl]));
      var cntsplValue=document.getElementById('iframe1'+splList[cntspl]).contentWindow.document.getElementById('fileName').value;
      if(splInvest1.checked && cntsplValue < 1 )
      {
        alert("Selected special investigation attachments are mandatory");
        //jqueryAlertMsg('Preauth mandatory fields','Selected special investigation attachments are mandatory');
        return;
      }
      else
    	  {
    	  }
    } // end of for
    
    /** check for whether special investigations are checked are not**/
	 for(var cntspl=0;cntspl < splList.length;cntspl++)
   {
		 
     var splInvest1 = eval(document.getElementById("checkspl"+splList[cntspl]));
     if(splInvest1.checked)  
     { 
       if(splInvest == null || splInvest=='')
       	{
       	 splInvestIds =splList[cntspl];
       	//  splInvest = splInvest1.value;
       	  var ww=document.getElementById('iframe1'+splList[cntspl]).contentWindow.document.getElementById('filePath'+splList[cntspl]).value;
       	  xxx =  "<a href='javascript:fn_openAtachment("+ww+")'>"+splInvest1.value+"</a>";
         splInvest = xxx;
       	}
     
       else
       	{
       	 splInvestIds =splInvestIds+"$"+splList[cntspl];
     //  	splInvest =splInvest+" , "+splInvest1.value;
       	ww=document.getElementById('iframe1'+splList[cntspl]).contentWindow.document.getElementById('filePath'+splList[cntspl]).value;
       	 yyy =  splInvest +"," +"<a href='javascript:fn_openAtachment("+ww+")'>"+splInvest1.value+"</a>";
       	splInvest = yyy;
       	}
       	
     }
   } // end of for 
    
    
    if(remarks == null || remarks =='' || remarks =='-1')
	{
    	var fr = partial(focusBox,document.getElementById('treatingDocRmks'));
    // jqueryAlertMsg('Preauth mandatory fields','Treating doctor remarks are mandatory',fr);
	alert('Treating doctor remarks are mandatory');
	focusBox(document.getElementById('treatingDocRmks'));
	return;
	}
    else
    	{
    	  msg = chkSpecailChars(remarks,3000);
    	 if(msg !=null && msg !='')
    		 {
    		alert(msg);
			subStringRemarks('treatingDocRmks',3000);
    		 //var fr = partial(subStringRemarks,'treatingDocRmks',3000);
    		  //  jqueryAlertMsg('Preauth mandatory check',msg ,fr);
    		 //document.forms[0].treatingDocRmks.value = document.forms[0].treatingDocRmks.value.substr(0, 2000-1);
    		 return;
    		 }
    	}
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
	
	// disable button block
	 document.getElementById('addSurgicalDtls').disabled=true;
	
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getSurgeryDtls&catCode='+document.forms[0].category.value+'&icdCatCode='+document.forms[0].icdCatCode.value+'&icdProcCode='+surgeryId+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
					if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var invstList = resultArray1.split("~"); 
						
			        } 
		            if(invstList.length>0)
		            {   
						
							 var arr=invstList;
							//alert(arr);
							 var val1 = arr[0].replace(/^\s+|\s+$/g,"")+"( " +document.forms[0].category.value +") ";
							 var val2 = arr[1].replace(/^\s+|\s+$/g,"")+" ( "+document.forms[0].icdCatCode.value +" )";
							 var val3 = arr[2].replace(/^\s+|\s+$/g,"");
							 var val4 = arr[3].replace(/^\s+|\s+$/g,"")+ " ( "+surgeryId +" ) ";
							 var val5 = arr[4].replace(/^\s+|\s+$/g,"");
							 var val6 = arr[5].replace(/^\s+|\s+$/g,"");
							 var val7 = arr[6].replace(/^\s+|\s+$/g,"");
							 var val8 = arr[7].replace(/^\s+|\s+$/g,"");
							 amtListArray[splCount] = new Array();
							 amtListArray[splCount][0]=document.forms[0].category.value;
							 amtListArray[splCount][1]=val6;
							 amtListArray[splCount][2]=val7;
							 amtListArray[splCount][3]=val5;
							 amtListArray[splCount][4]=val8;
							 amtListArray[splCount][5]="*";
							//fn_getTotalPackageAmount();
							if(splInvest == null || splInvest=='')
								splInvest ="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --";
							//alert(splInvest);
							
							 var table=document.getElementById("addSurgdiv");
								     
							 var rowCount = table.rows.length;     
							 var row = table.insertRow(rowCount);  
							 row.id=id="SplInvestRow"+splCount;
							 var cell1 = row.insertCell(0);               
							 cell1.className="tbcellBorder";
							 cell1.width ="10%";  
							 cell1.setAttribute('style',"word-wrap:break-word");
							 cell1.innerHTML =val1;                    
							
							       
							 var cell2 = row.insertCell(1);
							 cell2.width ="10%";
							 cell2.innerHTML =val2;  
							 cell2.setAttribute('style',"word-wrap:break-word");
							 cell2.className="tbcellBorder";
							
							 
							 var cell3 = row.insertCell(2);
							 cell3.className="tbcellBorder";
							 cell3.width ="20%";
							 cell3.setAttribute('style',"word-wrap:break-word");
							 cell3.innerHTML = val4;  
							 
							 
							 var cell4 = row.insertCell(3);
							 cell4.className="tbcellBorder";
							 cell4.width ="20%";
							 cell4.setAttribute('style',"word-wrap:break-word");
							 if(document.getElementById("unitsTd").style.display=='')
								cell4.innerHTML = document.getElementById('procUnits').value; 
							 else
								cell4.innerHTML = "-NA-";
							 
							 var cell5 = row.insertCell(4);  
							 cell5.className="tbcellBorder";
							 cell5.width ="20%";
							 cell5.setAttribute('style',"word-wrap:break-word");
							 cell5.innerHTML = splInvest;        
							
							 
							 var cell6 = row.insertCell(5);  
							 cell6.className="tbcellBorder";
							 cell6.width ="35%";
							 cell6.setAttribute('style',"word-wrap:break-word");
							 cell6.innerHTML = remarks;  
							 
							 var cell7 = row.insertCell(6);  
							 cell7.className="tbcellBorder";
							 cell7.width ="15%";
							 if(document.forms[0].docSpecReg.value =='0')
								{
							 cell7.innerHTML =document.forms[0].treatDocName.value;       
								}
							 else
								 {
								 cell7.innerHTML = document.getElementById("docSpecReg").options[document.getElementById("docSpecReg").selectedIndex].text; 
								 }
							 
							 var cell8 = row.insertCell(7); 
							 cell8.className="tbcellBorder";
							 cell8.width ="5%";
							 cell8.innerHTML = '<a href="javascript:fn_removeSplInvest('+splCount+','+val5+','+splCount+'  )" >Delete</a>';        
							 
//alert(document.forms[0].docSpecReg.value);
								splInvestdata[splCount] = new Array();
								splInvestdata[splCount][0]=document.forms[0].category.value;
								splInvestdata[splCount][1]=document.forms[0].icdCatCode.value;
								splInvestdata[splCount][2]=document.forms[0].icdCatCode.value;
								splInvestdata[splCount][3]=surgeryId;
								splInvestdata[splCount][4]=remarks.replace(/,/g,"&#44;");
								//splInvestdata[splCount][5]=splInvestIds;
								if(document.forms[0].docSpecReg.value =='0')
									{
								splInvestdata[splCount][5]=document.forms[0].treatDocRegNo.value;
								splInvestdata[splCount][6]=document.forms[0].treatDocName.value;
								splInvestdata[splCount][7]=document.forms[0].treatDocQualification.value;
								splInvestdata[splCount][8]=document.forms[0].treatDocContact.value;
								//Save no of units, -1 if no of units is not available
								splInvestdata[splCount][9]=document.forms[0].procUnits.value;
								splInvestdata[splCount][10]="*";
							
									}
								else
									{
									splInvestdata[splCount][5]=document.forms[0].docSpecReg.value;
									splInvestdata[splCount][6]=document.getElementById("docSpecReg").options[document.getElementById("docSpecReg").selectedIndex].text;
									splInvestdata[splCount][7]="null";
									splInvestdata[splCount][8]="null";
									splInvestdata[splCount][9]=document.forms[0].procUnits.value;
									splInvestdata[splCount][10]="*";
									}
								//Reset the value of Units field and hide the field
								document.forms[0].procUnits.value="-1";
								document.getElementById("unitsTd").style.display='none';
								document.getElementById("unitsLabelTd").style.display='none';
								document.forms[0].splInvest.value=splInvestdata;
								surgertIdsarray.push(surgeryId);
								categoryIdsarray.push(category);
								var fr = partial(disableFields);
								//jqueryInfoMsg('Preauth adding procedures', 'Procedure -- '+val3 + '   added' //,fr);
								alert('  Procedure -- '+val3 + '   added   ');
								disableFields();
							//	fn_disbleComorBid();
							//	fn_clearFields();
							 document.getElementById('addSurgicalDtls').disabled=false;
							//parent.fn_resizePage();	
			                
						 splCount++;
		            }
		    	
		    }
		}// end of if    
		}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	}
	function fn_getTotalPackageAmount()
	{
		var flag = "success";
		
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
		
		 url = '/<%=context%>/preauthDetails.do?actionFlag=calcTotalPackageAmt&callType=Ajax&amtList='+amtListArray+'&hospStayAmt='+document.forms[0].hospStayAmt.value;
		 xmlhttp.onreadystatechange=function()
			{
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				       
				        if(resultArray[0]!=null)
				        {	
				        	 if(resultArray[0]=="SessionExpired"){
						    		alert("Session has been expired");
						    		 parent.sessionExpireyClose();
						    		 //var fr = partial(parent.sessionExpireyClose);
						    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
						    		}
				        	 else
				        		 {
				        	 totPkgAmt = resultArray[0].replace(/^\s+|\s+$/g,"");
					        document.forms[0].preauthPckgAmt.value=totPkgAmt;
					        if(document.forms[0].nabhFlg.value != null && document.forms[0].nabhFlg.value!='' && document.forms[0].nabhFlg.value=='Y')
					        	{
					        	totPkgAmt =parseInt(parseInt(totPkgAmt)+ parseInt(totPkgAmt)*2/100);
					        	//alert(totPkgAmt);
					        	}
							 if(totPkgAmt != null && totPkgAmt !='')
								 {
										if(comorbidAmt != null && comorbidAmt !='')
										 document.forms[0].totPkgAmt.value =parseInt(parseInt(totPkgAmt)+parseInt(comorbidAmt));
										else
											 document.forms[0].totPkgAmt.value = totPkgAmt;	
								 }

				        }} 	
			    }}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		
		return flag;
	}
	function fn_removeSplInvest(splcnt,amount,rowCount)
	{
	var msg = fn_deleteSurgId(splInvestdata[splcnt][3]);
	if(msg != null && msg !='')
		{
		alert(msg);
		//jqueryAlertMsg('Preauth mandatory fields',msg);
		return;
		}
	 //var fr = partial(fn_removeSplInvestConfirm,splcnt,amount,rowCount);
	 //jqueryConfirmMsg('Preauth procedure details','Do you want to delete ? ',fr);	
	 if(confirm('Do you want to delete ? '))
	 {
		fn_removeSplInvestConfirm(splcnt,amount,rowCount);	 
	 }
	}
	function fn_removeSplInvestConfirm(splcnt,amount,rowCount)
	{
		//alert(splInvestdata);
		fn_deleteSurgIdConfirm(splInvestdata[splcnt][3]);
		delete splInvestdata[splcnt];
		document.forms[0].splInvest.value=splInvestdata;
		delete amtListArray[splcnt];
		//fn_getTotalPackageAmount();
		fn_disbleComorBid();
		var surgtable=document.getElementById("addSurgdiv");
		//var sampleData=surgtable.innerHTML;
		//var remElement = document.getElementById("SplInvest"+splcnt);
		//surgtable.removeChild(remElement);
		var tr=document.getElementById("SplInvestRow"+rowCount);
			  tr.parentNode.removeChild( tr );

		
		//fn_deleteAmt(amount);	
	}
	function fn_submitPreauth(buttonVal,buttonName,remarksCheck)
	{	
		//close the attachments window
		if(parent.parent.parent.attachmentWin != null)
			parent.parent.parent.attachmentWin.close();
		// check mandatory things
		if(document.forms[0].admissionType.value == null || document.forms[0].admissionType.value =="")
			{
				disableDiv(document.getElementById('buttonBlock'));
				//jqueryAlertMsg('Preauth mandatory fields','Please select the adsmission type');
				alert('Please select the adsmission type');
				enableDiv(document.getElementById('buttonBlock'));
				return;
			}
		if(document.forms[0].admissionDate.value == null || document.forms[0].admissionDate.value =="" )
			{
			var fr = partial(focusBox,document.getElementById('admissionDate'));
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Admission date is mandatory',fr);
			alert('Admission date is mandatory');
			enableDiv(document.getElementById('buttonBlock'));
			return;
			}
		if(!document.forms[0].procedureConsent[0].checked && !document.forms[0].procedureConsent[1].checked)
		{
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Please select the procedure consent type');
			alert('Please select the procedure consent type');
			enableDiv(document.getElementById('buttonBlock'));
			
			return;
		}
		if(!document.forms[0].medCardioClearence[0].checked && !document.forms[0].medCardioClearence[1].checked)
		{
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Please select the Medical or cardiology clearance type');
			alert('Please select the Medical or cardiology clearance type');
			enableDiv(document.getElementById('buttonBlock'));
			return;
		}
		if(!document.forms[0].bloodTransfusion[0].checked && !document.forms[0].bloodTransfusion[1].checked)
		{
		disableDiv(document.getElementById('buttonBlock'));
		//jqueryAlertMsg('Preauth mandatory fields','Please select the blood transfusion type');
		alert('Please select the blood transfusion type');
		enableDiv(document.getElementById('buttonBlock'));
		return;
		}
		if(surgertIdsarray.length == null || surgertIdsarray.length ==0 )
		{
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Atleast one procedure is mandatory. Please add Surgical Details in Plan of Treatment');
			alert('Atleast one procedure is mandatory. Please add Surgical Details in Plan of Treatment');
			enableDiv(document.getElementById('buttonBlock'));
			return;
		}


//added for verifying cochlear quesstionaire

var cochlearQues=document.forms[0].cochlearQuestionnaire.value;
var cochlearYN="${cochlearYN}";
var viewType='${viewType}' ;
if(cochlearYN=='Y' && viewType=='coc' && (cochlearQues==null || cochlearQues=="" ))
{
	alert("please fill the details in cochlear questtionaire");
	return false;
}
		
		//if(document.forms[0].totPkgAmt.value == null || document.forms[0].totPkgAmt.value =="" || document.forms[0].totPkgAmt.value =="0")
		//{
		//	disableDiv(document.getElementById('buttonBlock'));
		//	jqueryAlertMsg('Preauth mandatory fields','Total Package Amount Admissible under  the scheme cannot be equal to zero. Please add Surgical Details in Plan of Treatment');
		//	enableDiv(document.getElementById('buttonBlock'));
		//	return;
		//}
		if(remarksCheck != null)
			{
		 if(document.forms[0].genRemarks.value == null || document.forms[0].genRemarks.value=='')
		{
			var fr = partial(focusBox,document.getElementById('genRemarks'));
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
			alert('Please enter the remarks');
			focusBox(document.getElementById('genRemarks'));
			enableDiv(document.getElementById('buttonBlock'));
			//document.forms[0].genRemarks.focus();
			return;
		}
		else
		{
		var msg = chkSpecailChars(document.forms[0].genRemarks.value,3000);
		if(msg != null && msg !='')
		{
		//alert(msg+'000');
		 //document.forms[0].genRemarks.value = document.forms[0].genRemarks.value.substr(0, 3800-1);
		 //var fr = partial(subStringRemarks,'genRemarks',3800);
		    //jqueryAlertMsg('Preauth mandatory check',msg ,fr);
			alert(msg);
			subStringRemarks('genRemarks',3800);
		    return;
		}
		} 
			}
		if(document.forms[0].medCardioClearence[0].checked && document.getElementById("medCardClearence").value <1)
		{
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Please attach Medical or cardiology clearance attachment ');
			alert('Please attach Medical or cardiology clearance attachment');
			enableDiv(document.getElementById('buttonBlock'));
			return;
		}
		if(document.forms[0].bloodTransfusion[0].checked && document.getElementById("bloodTransfusionAttach").value <1)
		{
			disableDiv(document.getElementById('buttonBlock'));
			//jqueryAlertMsg('Preauth mandatory fields','Please attach blood transfusion attachment');
			alert('Please attach blood transfusion attachment');
			enableDiv(document.getElementById('buttonBlock'));
			return;
		}
		var flag = fn_testMandatoryAttach('submit',buttonVal,buttonName,remarksCheck);
		
	}
	function fn_hideTable(value,casesurg,Seqcount,surgerId, catId)
	{
var pending="${pendingFlag}";

if(catId=='S16' && pending=='Y')
{
	alert("cochlear case cannot be deleted");
	return false;
}
		var msg = fn_deleteSurgId(surgerId);
		if(msg != null && msg !='')
		{
		alert(msg);
		//jqueryAlertMsg('Preauth mandatory fields',msg);
		return;
		}
		 var fr = partial(fn_hideTableConfirm,value,casesurg,Seqcount,surgerId,catId);
		 //jqueryConfirmMsg('Preauth procedure details','Do you want to delete ? ',fr);
		if(confirm('Do you want to delete?'))
		{
			fn_hideTableConfirm(value,casesurg,Seqcount,surgerId,catId);
		}
	}
	function fn_hideTableConfirm(value,casesurg,Seqcount,surgerId,catId)
	{
		fn_deleteSurgIdConfirm(surgerId,catId);
		document.getElementById(value).style.display='none';
		if(casSurglist != null)
			casSurglist = casSurglist+"~"+casesurg;
		else
			casSurglist = casesurg;
		document.forms[0].casSugeryId.value = casSurglist;
		
		//alert(amtListArray);
		delete amtListArray[Seqcount];
		//alert(amtListArray);
		//fn_getTotalPackageAmount();
		fn_disbleComorBid();	
	}
	
	function fn_hideTableEnhc(value,sno,amount,quantId)
	{
		 //var fr = partial(hideEnhTableOnCnfm,value,sno,amount,quantId);
  		  //jqueryConfirmMsg('Enhacement details','Do you want to delete ? ',fr);	
		  if(confirm('Do you want to delete ? '))
		  {
			hideEnhTableOnCnfm(value,sno,amount,quantId);
		  }
	}
	function hideEnhTableOnCnfm(value,sno,amount,quantId)
	{
		document.getElementById(value).style.display='none';
		if(remEnhlist != null)
			remEnhlist = remEnhlist+"~"+sno;
		else
			remEnhlist = sno;
		document.forms[0].remEnhList.value = remEnhlist;
		fn_deleteEnhAmt(amount);
		// remove enhancement list
		 for(var i=0;i<enhancementIdsarray.length;i++)
			{
			if(enhancementIdsarray[i]==sno)
				{
				enhancementIdsarray.splice(i,1);
				}
			}
		 for(var i=0;i<enhQuantCodeArray.length;i++)
			{
			if(enhQuantCodeArray[i]==quantId)
				{
				enhQuantCodeArray.splice(i,1);
				}
			} 
	}
	
	function fn_setTotalAmt()
	{
		
		if(document.forms[0].comorBidAmt.value != null && document.forms[0].comorBidAmt.value !='')
		comorbidAmt=parseInt(document.forms[0].comorBidAmt.value);	
		if('${viewType}' =='medco' ) // || '${viewType}' =='disable'
		{
		//fn_getTotalPackageAmount();
		}
		 if('${viewType}' =='ptd' || '${viewType}' =='ceo' || '${viewType}' =='eo' )
			{
			document.forms[0].totPkgAmt.value = document.forms[0].totPkgAmt.value;
			}
		else
			{
			if(document.forms[0].totPkgAmt.value != null &&  document.forms[0].totPkgAmt.value !='' && comorbidAmt != null && comorbidAmt!='')
				{
				document.forms[0].totPkgAmt.value = parseInt(document.forms[0].totPkgAmt.value)+parseInt(comorbidAmt);	
				}
			else
				document.forms[0].totPkgAmt.value = parseInt(document.forms[0].totPkgAmt.value);
			}
			
		
	}
	function fn_deleteAmt(amount)
	{
		totPkgAmt = parseInt(totPkgAmt)-parseInt(amount);	
		document.forms[0].totPkgAmt.value = totPkgAmt;
	}
	function fn_addAmt(amount)
	{
		//alert(totPkgAmt);
		if(totPkgAmt !='')
			{
			if(parseInt(totPkgAmt) != null && parseInt(totPkgAmt) !='' )
			{
			totPkgAmt = parseInt(totPkgAmt)+parseInt(amount);	
			}	
			else
			{
			totPkgAmt = parseInt(amount);	
			}
			}
		
		else
			{
			totPkgAmt = parseInt(amount);	
			}
	
		document.forms[0].totPkgAmt.value = totPkgAmt;
	}
	// add enhancement amount
	
	
	function fn_setTotalEnhAmt()
	{
		if(document.forms[0].enhAmt != null)
		document.forms[0].enhAmt.value = enhAmt;
		
	}
	function fn_deleteEnhAmt(amount)
	{
		enhAmt = parseInt(enhAmt)-parseInt(amount);	
		document.forms[0].enhAmt.value = enhAmt;
	}
	function fn_addEnhAmt(amount)
	{
		//alert(totPkgAmt);
		if(enhAmt !='')
			{
			if(parseInt(enhAmt) != null && parseInt(enhAmt) !='' )
			{
				enhAmt = parseInt(enhAmt)+parseInt(amount);	
			}	
			else
			{
				enhAmt = parseInt(amount);	
			}
			}
		
		else
			{
			enhAmt = parseInt(amount);	
			}
	
		document.forms[0].enhAmt.value = enhAmt;
	}
	
	
	function fn_deleteSurgId(surgerId){
		var msg =null;
		if(surgertIdsarray.length != null && surgertIdsarray.length ==1)
			{
			msg="Atleast one procedure is mandatory";
			return msg;
			}
		return msg;
	}
	function fn_deleteSurgIdConfirm(surgerId,catId)
	{
		var inactive='N';
		for(var i=0;i<surgertIdsarray.length;i++)
		{
			if(surgertIdsarray[i]==surgerId)
			{
				surgertIdsarray.splice(i,1);
			}
		}
		
		//Start- To remove the deleted surgery from checkInactiveSurgArray list and enable save button if all surgeries are active
		/*for(var i=0; i<checkInactiveSurgArray.length;i++)
		{
			var arr= checkInactiveSurgArray[i].split('~');
			if(arr[0]!=null && arr[0]==catId)
			{	
				if(arr[2]!=null && arr[2]=='N')
				{	
					checkInactiveSurgArray.splice(i,1);
				}
			}
		}
		for(var i=0; i<checkInactiveSurgArray.length;i++)
		{
			var arr= checkInactiveSurgArray[i].split('~');
			if(arr[2]!=null && arr[2]=='N')
			{
				inactive="Y";
			}
		}
		if(inactive=='N')
		{
			document.getElementById('CD71').disabled=false;
		}*/
		//End
	}
	function fn_clearFields()
	{
		
		document.forms[0].treatingDocRmks.value ="";
		document.forms[0].category.value='-1';
		document.forms[0].icdCatCode.options.length=0;
		document.forms[0].icdProcCode.options.length=0;
		document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
		document.forms[0].icdCatCode.options[0]=new Option("--select--","-1");
		document.forms[0].docSpecReg.options.length=0;
    	document.forms[0].docSpecReg.options[0]=new Option("---select---","-1");
		var my2div = document.getElementById('myDivSplUpload');
         if (my2div != null) 
     	{
     		 while (my2div.hasChildNodes()) 
     		 {  my2div.removeChild(my2div.lastChild);    }
     	 }
         var myDivSpl = document.getElementById('myDivSpl');
         if (myDivSpl != null) 
         	{
         		 while (myDivSpl.hasChildNodes()) 
         		 {  myDivSpl.removeChild(myDivSpl.lastChild);    }
         	 }
         document.forms[0].treatDocName.value='';
         document.forms[0].treatDocRegNo.value='';
         document.forms[0].treatDocQualification.value='';
         document.forms[0].treatDocContact.value='';
         if(document.getElementById('TreatingDocOthersId')!=null)
 		{
 		document.getElementById('TreatingDocOthersId').style.display="none";
 		}
         splList = new Array();	
         surgeryId = null;
	}
	
	function fn_addAttachments()
	{
		var caseNo='<bean:write name="preauthDetailsForm" property="caseId" />';
		parent.parent.parent.attachmentWin= window.open("/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId="+caseNo+"&caseAttachmentFlag=Y&openWin=Y&caseApprovalFlag=Y", 'window1',
			'toolbar=no,resizable=yes,scrollbars=yes,width=800, height=600, top=100,left=50');
	}
	function  fn_addEnhAttachments()
	{
		var caseNo='<bean:write name="preauthDetailsForm" property="caseId" />';
		parent.parent.parent.attachmentWin= window.open("/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauthEnhancement&caseId="+caseNo+"&caseAttachmentFlag=Y&openWin=Y&caseApprovalFlag=Y", 'window1',
			'toolbar=no,resizable=yes,scrollbars=yes,width=800, height=600, top=100,left=50');	
	}
	function fn_testMandatoryAttach(varType,buttonVal,buttonName,remarksCheck)
	{
		disableDiv(document.getElementById('buttonBlock'));
		var flag = null;
		var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
		 url = '/<%=context%>/preauthDetails.do?actionFlag=checkMandatoryAttch&caseId='+caseId+'&attachType=ehfPreauth&callType=Ajax';
		 xmlhttp.onreadystatechange=function()
			{
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]=="SessionExpired"){
				    		alert("Session has been expired");
				    		 parent.sessionExpireyClose();
				    		 //var fr = partial(parent.sessionExpireyClose);
				    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
				    		}
				    		else
				    		{
				        if(resultArray[0]!=null)
				        {	
				           if(resultArray[0] =='success')
				        	   {
								 //  var send = confirm('Do you want to submit ? ');
								   //var fr = partial(testMandatorySubmit,varType,buttonVal,remarksCheck);
								   //jqueryConfirmMsg('Preauth Submission','Do you want to '+buttonName+' preauth ?',fr);	
									if(confirm('Do you want to '+buttonName+' preauth ?'))
									{
										testMandatorySubmit(varType,buttonVal,remarksCheck);
									}
				        	   }
				           else
				        	   {
								   alert(resultArray[0]);
								   //jqueryAlertMsg('Preauth mandatory check',resultArray[0]);
								   enableDiv(document.getElementById('buttonBlock'));
								  return;
				        	   }
							   enableDiv(document.getElementById('buttonBlock'));
				        } 
			    }
			}// end of if
			}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		//return flag;
	}
	function testMandatorySubmit(varType,buttonVal,remarksCheck)
	{
		if(parent.parent.parent.attachmentWin != null)
			parent.parent.parent.attachmentWin.close();
    	   document.getElementById('buttonBlock').disabled=true;
		   disableDiv(document.getElementById('buttonBlock'));
    	   if(varType != null && varType=='submit')
    		   {
    		   if(remarksCheck == null || remarksCheck=='')
    			   {
    		   for(var j=0; j<items.length; j++)
    			{
        			if(items[j].checked)	
        				{
        				if(comorBidVals == null || comorBidVals =='') 
        					comorBidVals=items[j].id;
        				else
        					comorBidVals=comorBidVals+'~'+items[j].id;
        				}
    			}
        		document.forms[0].comorBidVals.value = comorBidVals;
    			   }
        		
				document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitPreauthEhf&ceoFlag=Y&buttonVal="+buttonVal;
   			   document.forms[0].submit();	
    		   }
    	   else if(varType != null && varType=='sentForPreauth')
    		   {
    		   document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=sentForPreauth";
   			   document.forms[0].submit();	
    		   }
	}
	function fn_sentPreauth()
	{
		if(!document.getElementById('sentForPreauth').checked)
			{
			//jqueryAlertMsg('Preauth mandatory fields','Please check the sent for preauthorisation checkbox',fr);
			alert('Please check the sent for preauthorisation checkbox');
			return;
			}
		else
			{
			fn_testMandatoryAttach('sentForPreauth',null);
			}
	}
	function fn_setDisable()
	{
		
		
		if('${viewType}' !='medco')
			{
			document.getElementById('selectionBlock').disabled=true;	
           document.getElementById('selectionBlock').style.display="none";	
			
			}
		if('${viewType}' !='medco' ||'${viewType}' !='nam' )
		{
			//document.getElementById('submitPreauth').disabled=true;		
			//document.getElementById('addAttachments').disabled=true;	
		}
		if('${viewType}' =='enhMedco' && '<bean:write name="preauthDetailsForm" property="enhancementFlag" />' =='Y')
		{
			document.getElementById('enhancementBlock').style.display="";	
		}
		if('<bean:write name="preauthDetailsForm" property="enhancementFlag" />' =='Y' && (  '${viewType}' =='enhCeo' ||'${viewType}' =='ctd'  ||'${viewType}' =='cpd'||'${viewType}' =='ch' ||'${fn:length(preauthDetailsForm.lstEnhancementworkFlow)}' >0 ||  '${fn:length(preauthDetailsForm.enhButtonsLst)}' >0 ))
		{

		
			if(document.getElementById('enhDisplaydiv') != null)
			document.getElementById('enhDisplaydiv').style.display="";	
			if(document.getElementById('enhancementAmtDiv') != null)
			document.getElementById('enhancementAmtDiv').style.display="";
			if(document.getElementById('enhButtonBlock') != null)
			document.getElementById('enhButtonBlock').style.display="";	
			if(document.getElementById('enhRemarksBlock') != null )
			document.getElementById('enhRemarksBlock').style.display="";
			if(document.getElementById('genRemarks') != null )
				document.getElementById('genRemarks').style.display="none";
	
			if(document.getElementById('remarksTr') != null )
				document.getElementById('remarksTr').style.display="none";
			if(document.getElementById('remarksTr1') != null )
				document.getElementById('remarksTr1').style.display="none";
			
			if('${fn:length(preauthDetailsForm.enhamcementList)}' >0 )
				{
			document.getElementById('enhancemetTitlesId').style.display="";
			document.getElementById('enhancemetTitlesId1').style.display="";
				}
		}
		if('${fn:length(preauthDetailsForm.drugLt)}' >0)
			{
			document.getElementById("drugsTable").style.display="";
			}
		if('${viewType}' =='disable' || '${viewType}' =='medco')
		{
			if(document.getElementById('remarksTr') !=null)
	document.getElementById('remarksTr').style.display="none";
			if(document.getElementById('remarksTr1') !=null)
	document.getElementById('remarksTr1').style.display="none";
			if(document.getElementById('enhRemarksBlock') !=null)
	document.getElementById('enhRemarksBlock').style.display="none";
		}
		
		if('${viewType}' =='medco11')
		{
		
		// date picker

		$(function() {
			$( "#admissionDate" ).datepicker({
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
		}
		
	}
	function fn_enableEnhDiv()
	{
           
		//alert(<bean:write name="preauthDetailsForm" property="enhancementFlag" />);
		if(document.getElementById('enhancementBlock').style.display == 'none')
		document.getElementById('enhancementBlock').style.display="";	
		else
			document.getElementById('enhancementBlock').style.display="none";
		if(document.getElementById('enhDisplaydiv').style.display == 'none')
		document.getElementById('enhDisplaydiv').style.display="";	
		else
			document.getElementById('enhDisplaydiv').style.display="none";
		if(document.getElementById('enhancementAmtDiv').style.display =="none")
		document.getElementById('enhancementAmtDiv').style.display="";
		else
			document.getElementById('enhancementAmtDiv').style.display="none";
		if(document.getElementById('enhButtonBlock').style.display == 'none')
			document.getElementById('enhButtonBlock').style.display="";	
			else
				document.getElementById('enhButtonBlock').style.display="none";
		if(document.getElementById('enhRemarksBlock').style.display == 'none')
			document.getElementById('enhRemarksBlock').style.display="";	
			else
				document.getElementById('enhRemarksBlock').style.display="none";
		
		 //parent.fn_resizePage();
	}
</script>
<script>
	function fn_SubmitNextLvl(buttonId,buttonVal)
	{
		var cnt =0;
		var cntTemp=0;
		var errMsg='';
		var lField='';
		var alertMsg ='';
		var checklistId='';
		var searchEles = '';
		var preauthAmt='${preauthPkgAmt}';
			var comorbidAmt='${comorbidAmt}';
		   if('${viewType}' =='coc' ||'${viewType}' =='ptd' || '${viewType}' =='ppd' || '${viewType}' =='ceo' || '${viewType}' =='eo')
		  {
			 if('${viewType}' =='ptd') 
			searchEles = document.getElementById("L2CHK_LST").children; 
			 else if('${viewType}' =='ppd'||'${viewType}' =='coc') 
				 searchEles = document.getElementById("L1CHK_LST").children;  
			errMsg="Please fill the complete non-technical checklist";
			
			// adding remarks
			for(var i = 0; i < searchEles.length; i++) 
			{   
				if(searchEles[i].type == 'radio')
				{
					if(document.getElementById(searchEles[i].id).checked)
					{
						cntTemp=cntTemp+1;
					}
					if(!document.getElementById(searchEles[i].name+'_1').checked && !document.getElementById(searchEles[i].name+'_2').checked)
						{
						checklistId =searchEles[i].name+"_1"; 
						}
					
					cnt=cnt+1;
				} 
			}
			cnt = cnt /2; //cnt is divided by 3 as there are 3 elements in each radio group
		    if(cntTemp != cnt)
		    {
		    	//alert(alertMsg);
		    	//alert(checklistId);
		    	//var fr = partial(focusBox,document.getElementById(checklistId));
		    	 //jqueryAlertMsg('Preauth mandatory fields',errMsg,fr);
				 alert(errMsg);
				 focusBox(document.getElementById(checklistId));
				//buttonblock.style.display='';
				//actblock.style.display='none';
		    	return;
		    }
		    else
		    	{
		    	errMsg='';
		    	alertMsg='';
		    	}
			
		    if(buttonVal != null && buttonVal=='Approve' || ('${viewType}' =='eo' && buttonVal=='Recommended Approve')|| ('${viewType}' =='ptd' && buttonVal=='Recommended Approve')
		    		|| ('${viewType}' =='ceo' && buttonVal=='Recommended Approve'))
			  {
				 
			  if(preauthAmt==null || preauthAmt=='' || preauthAmt==0)
				{
					if(errMsg=='')
						errMsg=errMsg+"Package amount cannot be empty or 0 "; 
					if(lField=='')
					        lField='apprvdPckAmt';
				}
			  if(document.forms[0].apprvdPckAmt.value == null || document.forms[0].apprvdPckAmt.value=='')
			  {
				  if(errMsg=='')
						errMsg=errMsg+"Please enter approved package amount "; 
					if(lField=='')
				        lField='apprvdPckAmt';
			 // alert('Please enter approved package amount');
			 // document.forms[0].apprvdPckAmt.focus();
			 // return;
			  } 
			  if(document.forms[0].apprvdPckAmt.value != null)
				  {
				  if(parseInt(document.forms[0].apprvdPckAmt.value) <=0)
					  {
					  if(errMsg=='')
							errMsg=errMsg+"Approved package amount cannot be 0 "; 
						if(lField=='')
					        lField='apprvdPckAmt';
					 // alert('Approved package amount cannot be 0');
					  //document.forms[0].apprvdPckAmt.focus();
					 // return;
					  }
				  }
			  // comorbid amount
			   if(document.forms[0].comorBidAppvAmt.value == null || document.forms[0].comorBidAppvAmt.value=='' && parseInt(comorbidAmt) > 0)
			  {
				   if(errMsg=='')
						errMsg=errMsg+"Please enter approved comorbid amount "; 
					if(lField=='')
				        lField='comorBidAppvAmt';
			//  alert('Please enter approved comorbid amount');
			//  document.forms[0].comorBidAppvAmt.focus();
			//  return;
			  } 
			  if(document.forms[0].comorBidAppvAmt.value != null && parseInt(comorbidAmt) > 0)
				  {
				  if(parseInt(document.forms[0].comorBidAppvAmt.value) <=0)
					  {
					  if(errMsg=='')
							errMsg=errMsg+"Approved comorbid amount cannot be 0 "; 
						if(lField=='')
					        lField='comorBidAppvAmt';
					//  alert('Approved comorbid amount cannot be 0');
					//  document.forms[0].comorBidAppvAmt.focus();
					//  return;
					  }
				  }
			  
			  }
		  if(errMsg !='' )
			  {
			  //var fr = partial(focusBox,document.getElementById(lField));
			  //jqueryAlertMsg('Preauth mandatory fields',errMsg,fr);
			  alert(errMsg);
			  focusBox(document.getElementById(lField));
			  return;
			  }
		    if(document.forms[0].genRemarks.value == null || document.forms[0].genRemarks.value=='')
			{
				
				 //var fr = partial(focusBox,document.getElementById('genRemarks'));
				 //jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
				alert('Please enter the remarks');
				focusBox(document.getElementById('genRemarks'));
				//document.forms[0].genRemarks.focus();
				return;
			}
			else
			{
			var msg = chkSpecailChars(document.forms[0].genRemarks.value,3000);
			if(msg != null && msg !='')
			{
			//alert(msg+'000');
			 //document.forms[0].genRemarks.value = document.forms[0].genRemarks.value.substr(0, 3800-1);
			 //var fr = partial(subStringRemarks,'genRemarks',3800);
			    //jqueryAlertMsg('Preauth mandatory check',msg ,fr);
				alert(msg);
				subStringRemarks('genRemarks',3800);
			    return;
			}
			} 
			 rmks =document.forms[0].genRemarks.value;
			var remks1 = '';
			count++;
			
			if(count==1)
			{
			if(errMsg=='' && alertMsg=='')
			{
					for(var i = 0; i < searchEles.length; i++) 
					{   
						if(searchEles[i].type == 'text')
						{	
							remks1=remks1+document.getElementById(searchEles[i].id).value+"?";						
						}			
						if(searchEles[i].type == 'radio' && document.getElementById(searchEles[i].id).checked)
						{	
							remks1=remks1+document.getElementById(searchEles[i].id).value+"  ";
						}	 
					}
					if(remks1 != null && remks1 !='' )
					document.forms[0].genRemarks.value=rmks+remks1;
					else
						document.forms[0].genRemarks.value=rmks;	
			}
					//alert(document.forms[0].genRemarks.value);
			
		  }
		  }
		
		
		//alert(document.forms[0].genRemarks.value);
		if(errMsg=='' && alertMsg=='')
			{

			var confirmMsg='';
				//var send = confirm('Do you want to submit ? ');
				var fr = partial(submitNxtLvlConfirm,buttonId);
				var failFn=partial(submitNxtLvlConfirmFail,rmks);
				
				if(buttonVal=='Pending')
					confirmMsg ='put pending';
				else
					confirmMsg=buttonVal;
				//jqueryConfirmMsg('Preauth Confirmation','Do you want to '+confirmMsg+' ? ',fr,failFn);	
			
			
					submitNxtLvlConfirm(buttonId);
					submitNxtLvlConfirmFail(rmks);
					
					
				
			}
	}
	</script>
	<script>
	function submitNxtLvlConfirm(buttonId)
	{	
		document.getElementById('buttonBlock').disabled=true;	
		disableDiv(document.getElementById('buttonBlock'));
    	document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitNextLvl&ceoFlag=Y&viewType=${viewType}&buttonVal="+buttonId;
	   document.forms[0].submit();
	}
	function submitNxtLvlConfirmFail(remarks)
	{

		document.forms[0].genRemarks.value="";
		document.forms[0].genRemarks.value=remarks;	
	}
	function fn_openAtachment(filepath)
	{  
	    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&docSeqId="+filepath;
	    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	}
	
	function validateDate(input)
	{
		
		var entered = input.value;
		entered = entered.split("-");
		var byr = parseInt(entered[2]); 
		if(isNaN(byr))
		{
			//input.value="";
			var fr = partial(focusNClear,input);
	        //jqueryAlertMsg('Preauth mandatory fields','Please select admission date',fr);
			alert("Please select admission date ");
			focusNClear(input);
		}
		else
		{
			var msg =fnCompareDates(input.value);
			if(!msg)
				{
				var fr = partial(focusNClear,input);
		        //jqueryAlertMsg('Preauth mandatory fields','Admission date should be greater than or equal to inpatient registered date',fr);
				alert('Admission date should be greater than or equal to inpatient registered date');
				focusNClear(input);
				//input.value="";
				return;
				}
		
		var FromDate = document.forms[0].ipRegDate.value;
		// check for admission date 90 days validation
		var ipRegDt=new Date(FromDate.substring(6,10),FromDate.substring(3,5)-1,FromDate.substring(0,2));
	var admissiondt =new Date(input.value.substring(6,10),input.value.substring(3,5)-1,input.value.substring(0,2));
	ipRegDt.setDate(ipRegDt.getDate()+89); 
	if(admissiondt >ipRegDt)
		{
		var fr = partial(focusNClear,input);
        //jqueryAlertMsg('Preauth mandatory fields','Admission date should not be greater than  90 days from  inpatient registered date',fr);
		alert("Admission date should not be greater than  90 days from  inpatient registered date  ");
		focusNClear(input);
		//input.value="";
		return;
		}
		
		
		}
	}
	function checkRadio(val)
	{
		document.forms[0].admissionType.value=val;
	}
	

	
	function fn_addHospStay(codeId,quanId)
	{
		var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
		var amount = null;
		if(document.getElementById(codeId).value == null || document.getElementById(codeId).value =='' || document.getElementById(codeId).value=='-1')
			{
			var fr = partial(focusBox,document.getElementById(codeId));
	        //jqueryAlertMsg('Preauth mandatory fields','Please select code',fr);
			alert('Please select code');
			focusBox(document.getElementById(codeId));
			//document.getElementById(codeId).focus();
			return;
			}
		if(document.getElementById(quanId).value == null || document.getElementById(quanId).value =='' ||  document.getElementById(quanId).value=='-1')
		{
			var fr = partial(focusBox,document.getElementById(quanId));
	        //jqueryAlertMsg('Preauth mandatory fields','Please select quantity',fr);
		alert('Please select quantity');
		focusBox(document.getElementById(quanId));
		//document.getElementById(quanId).focus();
		return;
		}
		var units=document.getElementById(codeId+"Unit").value;
		if(units == null || units =='' )
		{
			var fr = partial(focusBox,document.getElementById(codeId+"Unit"));
	        //jqueryAlertMsg('Preauth mandatory fields','Please enter no Of units',fr);
		alert('Please enter no Of units');
		focusBox(document.getElementById(codeId+"Unit"));
		return;
		}
		
		// ajax call for getting amount
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
		
		 url = '/<%=context%>/preauthDetails.do?actionFlag=getHospAmt&callType=Ajax&caseId='+caseId+'&quantId='+document.getElementById(quanId).value+'&noOfUnits='+units+'&nabhFlag=${nabhFlag}';
		 xmlhttp.onreadystatechange=function()
			{
			 if(xmlhttp.readyState==4)
			    {
				 
				 // get the total amount
			var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]=="SessionExpired"){
				    		alert("Session has been expired");
				    		 parent.sessionExpireyClose();
				    		 //var fr = partial(parent.sessionExpireyClose);
				    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
				    		}
				    		else
				    		{
				        if(resultArray[0]!=null)
				        {
				        	amount =resultArray[0];
				        }
				    		}
				 
				 
		//alert(document.getElementById(codeId).options[document.getElementById(codeId).selectedIndex].text);
		var Code = document.getElementById(codeId).options[document.getElementById(codeId).selectedIndex].text;
		var Quantity = document.getElementById(quanId).options[document.getElementById(quanId).selectedIndex].text;
		var tableId = codeId+enhancementCnt;
		var divId = null;
		// assigning values to the form
		hospCodeArray[enhancementCnt] = new Array();
		if(codeId != null && codeId =='hospCode')
			{
			divId ="Hospital Stay";
			hospCodeArray[enhancementCnt][0]="Hospital Stay";
			
			}
		if(codeId != null && codeId =='imageCode')
		{
		divId ="Imageology";
		hospCodeArray[enhancementCnt][0]="Imageology";
		}
		
		if(codeId != null && codeId =='labInvestCode')
		{
		divId ="Lab Investigations";
		hospCodeArray[enhancementCnt][0]="Lab Investigations";
		}
		
		if(codeId != null && codeId =='drugCode')
		{
		divId ="Phramacy Drugs";
		hospCodeArray[enhancementCnt][0]="Phramacy Drugs";
		}
		
		if(codeId != null && codeId =='implantCode')
		{
		divId ="Implants";
		hospCodeArray[enhancementCnt][0]="Implants";
		}
		
		
		/**check for unique enhacement requests
		    **/
		    for(var c=0;c<enhQuantCodeArray.length;c++)
			{
			var enhValues=enhQuantCodeArray[c];
			if(enhValues==document.getElementById(quanId).value)
				{
				//jqueryErrorMsg('Unique Enhamcement Validation',"Enhancement request already added .Please select another Enhancement");
				alert("Enhancement request already added .Please select another Enhancement");
				return false;
				}
			}
		 // assigning general values to the array
			hospCodeArray[enhancementCnt][1]=document.getElementById(codeId).value;
			hospCodeArray[enhancementCnt][2]=document.getElementById(quanId).value;
			hospCodeArray[enhancementCnt][3]=units;
			hospCodeArray[enhancementCnt][4]=amount;
			hospCodeArray[enhancementCnt][5]=enhancementCnt;
			hospCodeArray[enhancementCnt][6]='@';
		    document.forms[0].enhancementDtls.value=hospCodeArray;
		    enhQuantCodeArray.push(document.getElementById(quanId).value);
		
		// end of assigning values
		tableId = "EnhancementTable"+enhancementCnt;
		var hospStaytable=document.getElementById("EnhancementDiv");
		var hospData=hospStaytable.innerHTML;
		hospData=hospData.replace("</TBODY>","");
		hospData=hospData+'<table border="0"  width="100%"  cellpadding="1" cellspacing="1" align="center"  id="'+tableId+'" name="'+tableId+'" ><tr align="center"><td width="11%" align="left" class="tbcellBorder" ><b>'+divId+'</b></td><td width="16%" class="tbcellBorder"><b>'+Code+'</b></td><td width="16%" class="tbcellBorder"><b>'+Quantity+'</b></td><td width="12%" class="tbcellBorder"><b>'+units+'</b></td><td width="5%" class="tbcellBorder"><a href="javascript:fn_deleteHospStay('+enhancementCnt+','+amount+')" ><b>Delete</b></a></td></tr></table>';
		hospStaytable.innerHTML=hospData;
		enhancementCnt++;
		// reset values
		fn_addEnhAmt(amount);
		document.getElementById(codeId).value = '-1';
		document.getElementById(quanId).options.length=0;
		document.getElementById(quanId).options[0]=new Option("----Select----","-1");
		document.getElementById(codeId+"Unit").value ='';
		document.getElementById('enhancemetTitlesId').style.display="";
		document.getElementById('enhancemetTitlesId1').style.display="";
		//parent.fn_resizePage();	
			    }
			}		
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		// end of ajax call
	}
	function fn_deleteHospStay(tableId,amount)
	{
		 var fr = partial(fn_deleteHospStayOnCnfm,tableId,amount);
 		 // jqueryConfirmMsg('Enhacement details','Do you want to delete ? ',fr);	
		  if(confirm('Do you want to delete ? '))	
		  {
			fn_deleteHospStayOnCnfm(tableId,amount);
		  }
	}
	function fn_deleteHospStayOnCnfm(tableId,amount)
	{
		var enhctable=null;
		//	var divValue = hospCodeArray[tableId][0];
			enhctable = document.getElementById("EnhancementDiv");
			/* if(divValue != null && divValue=='Hospital Stay')
			{
			}
			if(divValue != null && divValue=='Imageology')
			{
			}
			if(divValue != null && divValue =='Lab Investigations')
			{
			}
			if(divValue != null && divValue =='Phramacy Drugs')
			{
			}
			if(divValue != null && divValue =='Pharmcy Implants')
			{
			} */
			fn_deleteEnhAmt(amount);
			
			//To get the index of the element to be deleted
			var rowId=0;
			for(var val=1; val<hospCodeArray.length; val++)
			{	
				if(hospCodeArray[val][5]==tableId)
				{	
					rowId=val;
					break;
				}
			}
			
			var remElement = document.getElementById("EnhancementTable"+tableId);
			enhctable.removeChild(remElement);
			//enhQuantCodeArray
			for(var i=0;i<enhQuantCodeArray.length;i++)
			{
			if(enhQuantCodeArray[i]==hospCodeArray[rowId][2])
				{
				enhQuantCodeArray.splice(i,1);
				}
			} 
			//delete hospCodeArray[tableId];
			hospCodeArray.splice(rowId,1);
			enhancementCnt--;		
			document.forms[0].enhancementDtls.value = hospCodeArray;	
			// add code for enhancement complete deletion of columns
			var enhencementDtls = document.forms[0].enhancementDtls.value;
		     enhencementDtls=enhencementDtls.replace(/,/g , "");
			if((document.forms[0].enhancementDtls.value == null || document.forms[0].enhancementDtls.value =='') && '${fn:length(preauthDetailsForm.enhamcementList)}' ==0)
		 {
				document.getElementById('enhancemetTitlesId').style.display="none";
				document.getElementById('enhancemetTitlesId1').style.display="none";
		 }
	 if(enhencementDtls.length ==0 && ('${fn:length(preauthDetailsForm.enhamcementList)}' ==0
			  || enhancementIdsarray == null || enhancementIdsarray =='' ))
		 {
		 document.getElementById('enhancemetTitlesId').style.display="none";
			document.getElementById('enhancemetTitlesId1').style.display="none";
		 }
			
			
			
	}
	function fn_numberValidation(inputVal,id)
	{
		 var numbers = /^[0-9]+$/;   
	      if(inputVal.match(numbers))   
	      {
	    	if(parseInt(inputVal,10)==0)  
	    		{
	    		 var fr = partial(focusNClear,document.getElementById(id));
		       	// jqueryAlertMsg('Preauth mandatory fields','Units should not be zero',fr);	
				alert('Units should not be zero');
				focusNClear(document.getElementById(id));
		       	 return false;
	    		}
	      }   
	      else  
	      { 
	    	  var fr = partial(focusNClear,document.getElementById(id));
	       	 //jqueryAlertMsg('Preauth mandatory fields','Please enter numeric characters only',fr);	
	      alert('Please input numeric characters only');   
		  focusNClear(document.getElementById(id));
	    //  document.getElementById(id).value="";
	      return false;   
	      }  	
	}
	function fn_getEnhQuant(enhTypeId)
	{
		var value=document.getElementById(enhTypeId+"Code").value;
		if(value == null || value =='' || value=='-1' )
		{
		return ;
		}
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getEnhQuantList&callType=Ajax&enhTypeId='+value;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		// var fr = partial(parent.sessionExpireyClose);
			    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
						resultArray1=resultArray1.replace(/^\s+|\s+$/g,"");						
			            var procList = resultArray1.split(", @"); 
			        }
					
		            if(procList.length>0)
		            {   
						if(document.getElementById(enhTypeId+"Quantity").options!=null){  
							document.getElementById(enhTypeId+"Quantity").options.length=0;
							document.getElementById(enhTypeId+"Quantity").options[0]=new Option("--select--","-1");
						}
						 for(var i = 0; i<procList.length;i++)
			                {	
			                    var arr=procList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.getElementById(enhTypeId+"Quantity").options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
			                    	 document.getElementById(enhTypeId+"Quantity").options[0]=new Option("--select--","-1");
										
				                     }
			                }
		            }
		    }
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	
	}
	// function button clicked
	
	function fn_buttonClicked(buttonId,buttonVal)
	{
		
		/* if(document.forms[0].genRemarks.value == null || document.forms[0].genRemarks.value=='')
		{
			 var fr = partial(focusBox,document.getElementById('genRemarks'));
			 jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
		//alert('Please enter the remarks');
		//document.forms[0].genRemarks.focus();
		return;
		}
		else
		{
		var msg = chkSpecailChars(document.forms[0].genRemarks.value,4000);
		if(msg != null && msg !='')
		{
		//alert(msg+'000');
		 //document.forms[0].genRemarks.value = document.forms[0].genRemarks.value.substr(0, 3800-1);
		 var fr = partial(subStringRemarks,'genRemarks',3800);
		    jqueryAlertMsg('Preauth mandatory check',msg ,fr);
		    return;
		}
		} */
		
		if(('${viewType}' != null && '${viewType}' =='medco' || '${viewType}' =='saveRemarks') || ('${viewType}' != null && '${viewType}' =='enhMedco'))
			{
				if('${viewType}' =='saveRemarks')
					{
				
					if('${SignPRFForm}' != null && '${SignPRFForm}' =='0' && (document.getElementById('SignPRFForm').value !=null && document.getElementById('SignPRFForm').value=='0'))
						{
						 disableDiv(document.getElementById('buttonBlock'));
						 //jqueryAlertMsg('Preauth mandatory fields','Please attach Signed PRF form');
						 alert('Please attach Signed PRF form');
						 enableDiv(document.getElementById('buttonBlock'));
						 return;
						}
					}

				//added for verifying cochlear quesstionaire

		
				if(buttonVal != null && (buttonVal =='Save' )) /* || buttonVal =='Update'*/
				{	
					//document.forms[0].comorBidAmt.value=parseInt(comorbidAmt);
					var el = document.getElementById('comorbid');
					items = el.getElementsByTagName('input');
					fn_submitPreauth(buttonId,buttonVal,null);
					
				}
				else
				{
					fn_submitPreauth(buttonId,buttonVal,'checkRemarks');
				}
			}
		else if('${viewType}' != null && ('${viewType}' =='nam' || '${viewType}' =='pex'))
		{
			if(document.forms[0].genRemarks.value == null || document.forms[0].genRemarks.value=='')
			{
				 var fr = partial(focusBox,document.getElementById('genRemarks'));
				 disableDiv(document.getElementById('buttonBlock'));
				 //jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
				 alert('Please enter the remarks');
				 focusBox(document.getElementById('genRemarks'));
				 enableDiv(document.getElementById('buttonBlock'));
			//alert('Please enter the remarks');
			//document.forms[0].genRemarks.focus();
			return;
			}
			else
			{
			var msg = chkSpecailChars(document.forms[0].genRemarks.value,3000);
			if(msg != null && msg !='')
			{
			//alert(msg+'000');
			 //document.forms[0].genRemarks.value = document.forms[0].genRemarks.value.substr(0, 3800-1);
			 //var fr = partial(subStringRemarks,'genRemarks',3800);
			   // jqueryAlertMsg('Preauth mandatory check',msg ,fr);
				alert(msg);
				subStringRemarks('genRemarks',3800);
			    return;
			}
			}
			if('${viewType}' =='nam')
				{
					if(!document.getElementById('sentForPreauth').checked)
					{
						 var fr = partial(focusBox,document.getElementById('sentForPreauth'));
						 //jqueryAlertMsg('Preauth mandatory fields','Please check the sent for preauthorisation checkbox',fr);
					alert('Please check the sent for preauthorisation checkbox');
					focusBox(document.getElementById('sentForPreauth'));
					//document.getElementById('sentForPreauth').focus();
					return;
					}
					//var fr = partial(sentForPreauthConfirm,buttonId);
					//jqueryConfirmMsg('Preauth Confirmation','Do you want to forward  ? ',fr);	
					if(confirm('Do you want to forward  ? '))
					{
						sentForPreauthConfirm(buttonId);
					}
				}
			if('${viewType}' =='pex')
			{
				//var fr = partial(sentForPreauthConfirm,buttonId);
				//jqueryConfirmMsg('Preauth Confirmation','Do you want to verify  ? ',fr);
				if(confirm('Do you want to verify  ? '))	
				{
					sentForPreauthConfirm(buttonId);
				}
			}
			//var send = confirm('Do you want to submit ? ');
			
			
			
		}
		else if('${viewType}' != null && ('${viewType}' =='coc' ||'${viewType}' =='ptd' || '${viewType}' =='ppd' || '${viewType}' =='ceo' || '${viewType}' =='eo'))
			{
			
if('${viewType}' =='coc')
{
			var cochlearQues=document.forms[0].cochlearQuestionnaire.value;
			var cochlearYN="${cochlearYN}";
	
			if(cochlearYN=='Y' && (cochlearQues==null || cochlearQues=="" ))
			{
				alert("please fill the details in cochlear questtionaire");
				return false;
			}


}

			fn_SubmitNextLvl(buttonId,buttonVal);	
			
			
			}
		else if('${viewType}' != null && '${viewType}' =='ctd')
		{
			if(document.forms[0].enhApprAmt.value == null || document.forms[0].enhApprAmt.value =='')
				{
				 //jqueryAlertMsg('Preauth mandatory fields','Please enter enhancement approval amount');
				alert('Please enter enhancement approval amount');
				return;
				}
		fn_SubmitNextLvl(buttonId,buttonVal);
		}
		else if('${viewType}' != null && '${viewType}' =='ch')
		{
			fn_SubmitNextLvl(buttonId,buttonVal);	
		}
		
	}
	function sentForPreauthConfirm(buttonId)
	{
		
		document.getElementById('buttonBlock').disabled=true;	
		disableDiv(document.getElementById('buttonBlock'));
    	document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitNextLvl&buttonVal="+buttonId;
		document.forms[0].submit();
	}
	function fn_testEnhApprAmt(enhvalue)
	{
		var amt = enhvalue;
		var enhAmt = document.forms[0].enhAmt.value;
		var numbers = /^[0-9]+$/;  
		
	      if(amt.match(numbers))   
	      {  
	    	if( parseFloat(amt)> parseFloat(enhAmt) )
	    		{
	    		 var fr = partial(focusNClear,document.getElementById('enhApprvAmt'));
			      //jqueryAlertMsg('Preauth mandatory fields','Enhancement approved amount cannot be greater than actual amount',fr);	
	    		alert('Enhancement approved amount cannot be greater than actual amount');
				focusNClear(document.getElementById('enhApprvAmt'));
	    		//document.forms[0].enhApprvAmt.value="";
	    		//document.forms[0].enhApprvAmt.focus();
	    		return;
	    		}
	    	else
	    		{
	    		document.forms[0].enhApprvAmt.value=parseInt(amt,10);
	    		}
	      }   
	      else  
	      { 
	    	  //var fr = partial(focusNClear,document.getElementById('enhApprvAmt'));
		      //jqueryAlertMsg('Preauth mandatory fields','Please enter numeric characters only',fr);	
			alert('Please input numeric characters only');  
			focusNClear(document.getElementById('enhApprvAmt'))		  ;
	     // document.forms[0].enhApprvAmt.value="";
	     // document.forms[0].enhApprvAmt.focus();
	      return false;   
	      }  
	      
	}

function fn_testComorbidApprAmt()
{
	var amt = document.forms[0].comorBidAppvAmt.value;
	var comorBidtotamt = '${comorbidAmt}';
      if(parseInt(amt,10) !=null && parseInt(amt) >=0)   
      {  
    	if( parseInt(amt)> parseInt(comorBidtotamt) )
    		{
    		alert(' Approved comorbid amount can not be greater than actual comorbid amount');
    		//document.forms[0].comorBidAppvAmt.value="";
    		focusNClear(document.getElementById('comorBidAppvAmt'));
    		//var fr = partial(focusNClear,document.getElementById('comorBidAppvAmt'));
      	 // jqueryAlertMsg('Preauth mandatory fields','Approved comorbid amount can not be greater than actual comorbid amount',fr);
    		
    		return;
    		}
else

{
	document.forms[0].comorBidAppvAmt.value=parseInt(amt,10);	
	fn_totalPtdappvAmt();
}
      }   
      else  
      { 
    	  if(amt != null && amt !='')
    		{
    		  //var fr = partial(focusNClear,document.getElementById('comorBidAppvAmt'));
        	  //jqueryAlertMsg('Preauth mandatory fields','Please enter numeric characters only',fr);
			  alert('Please enter numeric characters only');
			  focusNClear(document.getElementById('comorBidAppvAmt'));
    		}
    	  else
    		  {
    			fn_totalPtdappvAmt(); 
    			
    		  }
      return false;  
      }  
      
}
function fn_totalPtdappvAmt()
{
	var enhancementFlag="${enhancementFlag}";
	if(enhancementFlag!="Y"){
		document.forms[0].ptdTotalApprvAmt.value=0;
	if(document.forms[0].apprvdPckAmt.value!='' && document.forms[0].apprvdPckAmt.value!=null)
		document.forms[0].ptdTotalApprvAmt.value=parseInt(document.forms[0].ptdTotalApprvAmt.value)+parseInt(document.forms[0].apprvdPckAmt.value);
	if(document.forms[0].comorBidAppvAmt.value!='' && document.forms[0].comorBidAppvAmt.value!=null)
		document.forms[0].ptdTotalApprvAmt.value=parseInt(document.forms[0].ptdTotalApprvAmt.value)+parseInt(document.forms[0].comorBidAppvAmt.value);
	
	}}
function fn_testPckApprAmt()
{
	var amt = document.forms[0].apprvdPckAmt.value;
	var pckAmt='${preauthPkgAmt}';
	if(pckAmt==null || pckAmt=='' || pckAmt=='0')
	{
		var fr = partial(focusNClear,document.getElementById('apprvdPckAmt'));
		//jqueryAlertMsg('Preauth mandatory fields','Package amount cannot be empty or 0');
		alert('Package amount cannot be empty or 0');
		return false;
	}
    if(parseInt(amt,10) !=null && parseInt(amt) >=0)   
    {  
    	if( parseInt(amt)> parseInt(pckAmt) )
    	{
    		//document.forms[0].nabhAmt.value='';
			document.forms[0].ptdTotalApprvAmt.value=''; 
    		var fr = partial(focusNClear,document.getElementById('apprvdPckAmt'));
			//jqueryAlertMsg('Preauth mandatory fields','Approved package amount can not be greater than actual package amount',fr);
    		alert(' Approved package amount can not be greater than actual package amount');
			focusNClear(document.getElementById('apprvdPckAmt'));
    		// document.forms[0].apprvdPckAmt.value="";
    		return;
    	}
		else if( parseInt(amt)<= parseInt(pckAmt) )
		{
			document.forms[0].apprvdPckAmt.value=parseInt(amt,10);
			fn_totalPtdappvAmt();
		}
	}   
	else  
	{ 
		//document.forms[0].nabhAmt.value='';
		document.forms[0].ptdTotalApprvAmt.value=''; 
		//var fr = partial(focusNClear,document.getElementById('apprvdPckAmt'));
		//jqueryAlertMsg('Preauth mandatory fields','Please enter numeric characters only',fr);	
	  
		  alert('Please input numeric characters only');  
		  focusNClear(document.getElementById('apprvdPckAmt'));
		//  document.forms[0].apprvdPckAmt.value="";
		return false;   
	}  
      
}

function fn_enableEnhAmt()
{
	document.getElementById('enhancementAmtDiv').style.display="";
}
function getTitles(styleId)
{
	 var numOptions = document.getElementById(styleId).options.length; 
	
	for (var i = 0; i < numOptions; i++)
		document.getElementById(styleId).options[i].title = document.getElementById(styleId).options[i].text;
}
function fnCompareDates(ToDate)
{
	var FromDate = document.forms[0].ipRegDate.value;
    var FromDateVal;
    var ToDateVal;
    var k = FromDate.indexOf("-");
    var t = FromDate.indexOf("-",3);  
    FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length);
  	k = ToDate.indexOf("-");
    t = ToDate.indexOf("-",3);
    ToDateVal = ToDate.substr(k+1,t-k-1)+"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length);
	 if (Date.parse(FromDateVal) > Date.parse(ToDateVal))
         {
     		return false; 
          } 
    else
      return true;       
}
function fn_enhbuttonClicked(buttonId,buttonVal)
{	
	disableDiv(document.getElementById('enhButtonBlock'));
	// for enmedco view type chcek for atleast one enhancement request
	document.forms[0].drugs.value = drugs;
	document.forms[0].drugDeletionString.value=drugsDeletedArray;
	var enhencementDtls = document.forms[0].enhancementDtls.value;
	enhencementDtls=enhencementDtls.replace(/,/g , "");
	//alert(drugsDeletedArray.length);
    if('${viewType}' =='enhMedco' ||'${viewType}' =='cpd')
	{
		if((drugs == null || drugs=='')&& (document.forms[0].enhancementDtls.value == null || document.forms[0].enhancementDtls.value =='' || document.forms[0].enhancementDtls.value.length <2) && '${fn:length(preauthDetailsForm.enhamcementList)}' ==0  && '${fn:length(preauthDetailsForm.drugLt)}' ==0  )
		{	
			if(document.getElementById('hospCode').value == '-1')
				var fr = partial(focusBox,document.getElementById('hospCode'));
			else if(document.getElementById('hospQuantity').value == '-1') 
				var fr = partial(focusBox,document.getElementById('hospQuantity'));
			else
				var fr = partial(focusBox,document.getElementById('hospCodeUnit')); 
				//jqueryAlertMsg('Preauth mandatory fields','Please add the enhancement request details (code,quantity,units)',fr);	
			alert('Please select the enhancement request details (code,quantity,units)'); 
			focusBox(document.getElementById('hospCode'));
			enableDiv(document.getElementById('enhButtonBlock'));
			return;
		}
		 /*  if(enhencementDtls.length ==0 && ('${fn:length(preauthDetailsForm.enhamcementList)}' ==0
				  || enhancementIdsarray == null || enhancementIdsarray =='' ) && (drugs == null || drugs==''))
			 {
			 jqueryAlertMsg('Preauth mandatory fields','Please add the enhancement request details (code,quantity,units)');	
			// alert('Please select the enhancement request details'); 
				return;
			 }  */
		if(( '${fn:length(preauthDetailsForm.drugLt)}' == '0'|| ('${fn:length(preauthDetailsForm.drugLt)}' > 0 && ('${fn:length(preauthDetailsForm.drugLt)}' == drugsDeletedArray.length) )) && (enhancementIdsarray == '' || enhancementIdsarray == null || enhancementIdsarray.length==0) && (document.forms[0].enhancementDtls.value == null || document.forms[0].enhancementDtls.value =='') && (drugs == null || drugs==''))
		{ 
			if(document.getElementById('hospCode').value == '-1')
				var fr = partial(focusBox,document.getElementById('hospCode'));
			else if(document.getElementById('hospQuantity').value == '-1') 
				var fr = partial(focusBox,document.getElementById('hospQuantity'));
			else
				var fr = partial(focusBox,document.getElementById('hospCodeUnit')); 
			//jqueryAlertMsg('Preauth mandatory fields','Please add the enhancement request details (code,quantity,units)',fr);
			alert('Please add the enhancement request details (code,quantity,units)');	
			focusBox(document.getElementById('hospCode'));
			enableDiv(document.getElementById('enhButtonBlock'));
			return;
		}
		// check enhancement amount limit for 2 lakhs
		var amt = document.forms[0].enhAmt.value;
	   /*  if(amt != null &&  parseInt(amt,10) >'${preauthEnhAmt}')
		    {
	    	 jqueryAlertMsg('Preauth mandatory fields','Enhancement amount should not exceed 2 lakhs');
			//alert('Enhancement amount should not excede 2 lakhs');
			return;
		    } */
		    
	}
    var enhApprAmt=parseInt(document.forms[0].enhApprvAmt.value);
	var enhamt='${enhAmt}';
    if(buttonVal != null && (buttonVal=='Recommended Approve' || buttonVal=='Approve'))
    {
    	if(document.forms[0].enhApprvAmt.value == null || document.forms[0].enhApprvAmt.value =='')
	    {
			//var fr = partial(focusBox,document.getElementById('enhApprvAmt'));
			//jqueryAlertMsg('Preauth mandatory fields','Please enter enhancement approval amount',fr);
			alert('Please enter enhancement approval amount');
			focusBox(document.getElementById('enhApprvAmt'));
			enableDiv(document.getElementById('enhButtonBlock'));
			return;
	    }
	    if(document.forms[0].enhApprvAmt.value != null || document.forms[0].enhApprvAmt.value !='')
	    {
			if(parseInt(document.forms[0].enhApprvAmt.value) == 0)
			{
				//var fr = partial(focusBox,document.getElementById('enhApprvAmt'));
			    //jqueryAlertMsg('Preauth mandatory fields','Enhancement approval amount cannot be 0',fr);
				alert('Enhancement approval amount cannot be 0');
				focusBox(document.getElementById('enhApprvAmt'));
				enableDiv(document.getElementById('enhButtonBlock'));
				return;
			}

			if(parseInt(document.forms[0].enhApprvAmt.value) > parseInt(enhamt)) 
			{
				//var fr = partial(focusBox,document.getElementById('enhApprvAmt'));
			    //jqueryAlertMsg('Preauth mandatory fields','Enhancement approval amount cannot be 0',fr);
				alert('Enhancement approval amount cannot be greater than Total Enhancement Amount');
				focusBox(document.getElementById('enhApprvAmt'));
				enableDiv(document.getElementById('enhButtonBlock'));
				return;
			}
			 
	    }
    }
	if(document.forms[0].enhRemarks.value == null || document.forms[0].enhRemarks.value=='')
	{
		//var fr = partial(focusBox,document.getElementById('enhRemarks'));
		//jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
		alert('Please enter the remarks');
		focusBox(document.getElementById('enhRemarks'));
		enableDiv(document.getElementById('enhButtonBlock'));
		return;
	}
	else
	{
	var msg = chkSpecailChars(document.forms[0].enhRemarks.value,3000);
	if(msg != null && msg !='')
		{
		//alert(msg);
		//jqueryAlertMsg('Preauth mandatory fields',msg);
		alert(msg);
		 document.forms[0].enhRemarks.value = document.forms[0].enhRemarks.value.substr(0, 3800-1);
		enableDiv(document.getElementById('enhButtonBlock'));
		return;
		}
	
	}// end of else
	// add form submission for enhancement preauth....
	test_enh_attachments(buttonId,buttonVal);
	}
	function test_enh_attachments(buttonId,buttonVal)
	{
		var flag = null;
		var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
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
		 url = '/<%=context%>/preauthDetails.do?actionFlag=checkMandatoryAttch&caseId='+caseId+'&attachType=ehfPreauthEnhancement&callType=Ajax';
		 xmlhttp.onreadystatechange=function()
			{
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]=="SessionExpired"){
				    		alert("Session has been expired");
				    		 parent.sessionExpireyClose();
				    		// var fr = partial(parent.sessionExpireyClose);
				    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
				    		}
				    		else
				    		{
				        if(resultArray[0]!=null)
				        {	
				           if(resultArray[0] =='success')
				        	   {
				        	   var fr = partial(testEnhButtonSubmitConfirm,buttonId);
				        	   var msg = '';
				        	   if(buttonVal=='Recommended Approve' || buttonVal=='Approve')
				        		   {
				        		   msg ="approve";
				        		   }
				        	   if(buttonVal=='Initiate')
			        		   {
				        		   msg ="initiate";  
			        		   }
				        	   if(buttonVal=='Reject')
			        		   {
				        		   msg ="reject";     
			        		   }
				        	   if(buttonVal=='Pending')
			        		   {
				        		   msg ="put pending";      
			        		   }
				        	   if(buttonVal=='Update')
			        		   {
				        		   msg ="update";      
			        		   }
				        	  
					       		
									testEnhButtonSubmitConfirm(buttonId);
								  
				        	   }
				           else
				        	   {
								//jqueryAlertMsg('Enhancement mandatory attachments',resultArray[0]);
								alert(resultArray[0]);
								enableDiv(document.getElementById('enhButtonBlock'));
				        	  // alert(resultArray[0]);
				        	  return;
				        	   }
							   enableDiv(document.getElementById('enhButtonBlock'));
								
				        } 
			    }
			}// end of if
			}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		//return flag;	
	}
function testEnhButtonSubmitConfirm(buttonId)
{
	document.getElementById('enhButtonBlock').disabled=true;	
	   disableDiv(document.getElementById('enhButtonBlock'));
    	document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitPreauthEhf&ceoFlag=Y&buttonVal="+buttonId+"&enhFlag=Y";
	   document.forms[0].submit();		
	}
	// ajax call for disabling comorbid check boxes
	function fn_disbleComorBid()
	{
		//alert(comorbidFlag);
		if('${viewType}' =='medco')
		{
		var flag = "success";
		
		if('${viewType}' =='medco' && document.forms[0].comorBidVals.value != null && document.forms[0].comorBidVals.value !='' && comorbidFlag ==null)
		{
			checkcomorbidConditions();	
		}
		
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
		
		 url = '/<%=context%>/preauthDetails.do?actionFlag=getDisbledComorbidList&callType=Ajax&procList='+surgertIdsarray;
		 xmlhttp.onreadystatechange=function()
			{
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]!=null)
				        {	
				        	 if(resultArray[0]=="SessionExpired"){
						    		alert("Session has been expired");
						    		 parent.sessionExpireyClose();
						    		 //var fr = partial(parent.sessionExpireyClose);
						    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
						    		}
				        	 else
				        		 {
					        
				        		 if(resultArray[0]!=null)
							        {	
							            resultArray1 = resultArray[0].replace("[","");
							            resultArray1 = resultArray1.replace("]","");            
							            var procList = resultArray1.split(","); 
							        }
				        		 if(document.getElementById('comorbid'))
			                     	{
			                     		var el = document.getElementById('comorbid');
			                     		items = el.getElementsByTagName('input');
			                     		for(var j=0; j<items.length; j++)
		                     			{
			                     			items[j].disabled=false;	
		                     			}
			                     		for(var i = 0; i<procList.length;i++)
			                     		{
			                     			arrComId= procList[i].split("~");
			                     			for(var j=0; j<items.length; j++)
			                     			{	
			                     				if(arrComId[0].replace(/^\s+|\s+$/g,"")==items[j].id)
			                     				{	
			                     						if(items[j].checked)
			                     							{
			                     					//fn_deleteComorbidAmt(items[j].id);
		                     						items[j].checked=false;
			                     							}
			                     					items[j].disabled=true;
			                     					
			                     				}
			                     			}
			                     		}
			                     	}	// end of if
				        }// end of else
				        	 } 	
			    }}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		
		return flag;
	}
		
		// if view type not equal to medco
		if('${viewType}' !='medco' && document.forms[0].comorBidVals && document.forms[0].comorBidVals.value != null && document.forms[0].comorBidVals.value !='' && document.forms[0].comorBidVals.value != 'null')
		{
			checkcomorbid();	
		}
		else
			{
			var el = document.getElementById('comorbid');
			el.style.display="none";
			}
		
	}
	function checkcomorbid()
	{
	
		var el = document.getElementById('comorbid');
		el.style.display="none";
		document.getElementById('comorbidDisplayBlock').style.display="";
		
	}
	function checkcomorbidConditions()
	{
		if(document.forms[0].comorBidVals.value != null && document.forms[0].comorBidVals.value !='')
		{
		var el = document.getElementById('comorbid');
 		items = el.getElementsByTagName('input');
 		procList= document.forms[0].comorBidVals.value.split("~");
 		for(var i = 0; i<procList.length;i++)
 		{
 			for(var j=0; j<items.length; j++)
 			{	
 				if(procList[i]==items[j].id)
 				{	
 					items[j].disabled=true;
 					items[j].checked=true;
 				}
 				else
 					{
 					items[j].disabled=true;
 					}
 			}
 		}
		}
	}
	function getComorbidAmt(comorBidId)
	{
		comorbidFlag='Y';
		if(document.getElementById(comorBidId) != null && document.getElementById(comorBidId).checked==true)	
			{
			if(comorbidAmt == null || comorbidAmt =='')
				comorbidAmt = Math.round(document.getElementById(comorBidId).value);
			else
				comorbidAmt = comorbidAmt+Math.round(document.getElementById(comorBidId).value);
			}
		if(document.getElementById(comorBidId) != null && document.getElementById(comorBidId).checked==false)	
		{
		if(comorbidAmt != null || comorbidAmt !='')
			comorbidAmt = comorbidAmt-Math.round(document.getElementById(comorBidId).value);
		}
		 //document.forms[0].totPkgAmt.value =parseInt(totPkgAmt)+parseInt(comorbidAmt);
	}
	function fn_deleteComorbidAmt(comorBidId)
	{
		if(document.getElementById(comorBidId) != null )	
		{
			if(comorbidAmt != null || comorbidAmt !='')
				comorbidAmt = comorbidAmt-parseInt(document.getElementById(comorBidId).value);	
			//document.forms[0].totPkgAmt.value =parseInt(totPkgAmt)+parseInt(comorbidAmt);
		}
	}
	
	// start of drugs code
	

function validateNumber(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	var regDigit=/^\d+$/ ;
	if(a.search(regDigit)==-1)
		{
		input.value="";
		//jqueryErrorMsg('Number Validation','Only numbers are allowed for '+arg1,fr);
		alert('Only numbers are allowed for '+arg1);
		focusBox(input);
		return false;
		}
	if(a.charAt(0)=="0")
	{
		input.value="";
		//jqueryErrorMsg('Number Validation',arg1+ ' should not start with 0',fr);
		alert('Number Validation',arg1+ ' should not start with 0');
		focusBox(input);
		return false;
	}
}

function addTooltip(input) 
{
	var numOptions = document.getElementById(input).options.length;
	 for ( var i = 0; i < numOptions; i++)
		document.getElementById(input).options[i].title = document
				.getElementById(input).options[i].text;
}

function confirmRemoveRow(src,type)
{
	var fr;
	
	 if(type=="drug")
		{
			//fr=partial(removeDrug,src);
			//jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
			if(confirm('Do you want to delete Drug?'))
			{
				removeDrug(src);
			}
		}
	
	
		//parent.fn_resizePage();
}
function confirmDeleteRow(src,editType,drugCode,amount)
{
	//fr=partial(editDrug,src,drugCode,amount);
	//jqueryConfirmMsg('Edit Drug Confirmation',"Do you want to delete drug?",fr);
	if(confirm('Do you want to delete Drug?'))
	{
		editDrug(src,drugCode,amount);
	}
}
function editDrug(src,drugCode,amount)
{
	//alert(drugsDeletedArray);
	var oRow=src.parentNode.parentNode;
	document.getElementById("drugs").value=drugs;
	document.getElementById("drugsTable").deleteRow(oRow.rowIndex);
	fn_deleteEnhAmt(amount);
	drugsDeletedArray.push(drugCode);
	if((drugs == null || drugs=='') && ( '${fn:length(preauthDetailsForm.drugLt)}' > 0 && ('${fn:length(preauthDetailsForm.drugLt)}' == drugsDeletedArray.length) ))
		{
		document.getElementById("drugsTable").style.display="none";
		}
	//parent.fn_resizePage();
}
function fn_enableTreatingDocDiv()
{
	if(document.getElementById('docSpecReg').value =='0')
		{
	if(document.getElementById('TreatingDocOthersId')!=null)
		{
		document.getElementById('TreatingDocOthersId').style.display="";
		}
		}
	else
		{
		if(document.getElementById('TreatingDocOthersId')!=null)
		{
		document.getElementById('TreatingDocOthersId').style.display="none";
		}
		}
	}

// end of drug code

function disableDiv(arg) // To disable all buttons in the div
{	
	var elInput = arg.getElementsByTagName("button");
	for(i=0;i<elInput.length;i++)
	{	
	   elInput[i].disabled=true;
	}
}
function enableDiv(arg) // To enable all buttons in the div
{	
	var elInput = arg.getElementsByTagName("button");
	for(i=0;i<elInput.length;i++)
	{	
	   elInput[i].disabled=false;
	}
}

//To calculate no of Units in Prescription
function calculateUnits()
{
	var dosageCnt= document.getElementById('dosage').value;
	var medicatPeriod= document.getElementById('medicatPeriod').value;
	
	if(dosageCnt!=null && dosageCnt!='' && medicatPeriod!=null && medicatPeriod!='')
	{
		document.getElementById('drugQuantity1').value= parseInt(dosageCnt) * parseInt(medicatPeriod);
	}
}
function refresh()
{
	var msg='${ResultMsg}';

	if(msg!=null && msg!='')
	{
		
parent.parent.fn_refresh();
	}
}
function fn_back()
{
	parent.parent.fn_operations(); 
}
//end of functions

</script>
</head>
<body onload="javascript:fn_setDisable();fn_setTotalEnhAmt();fn_disbleComorBid();refresh();fn_totalPtdappvAmt();" id="preauthDetailsId">
<!-- <c:if test="${ResultMsg != null}">
<script>
        alert('${ResultMsg}');
       parent.fn_ipRefresh();
       // var fr = partial(parent.fn_ipRefresh);
      //  jqueryInfoMsg('Preauth Result','${ResultMsg}',fr);
    </script>
    </c:if> -->
<html:form action="/preauthDetails.do" enctype="multipart/form-data" method="post">
<!-- Modal for patient details  -->  
<div class="mainDiv">


<div class="modal fade"   id="progressBar" >
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Processing...</span>
    </div>
  </div>
</div>
</div></div></div>


<button class="but but rightone"  id="backBtn" onclick="javascript:fn_back();" type="button"> </span>&nbsp;&nbsp;&nbsp;Back</button>
<br><br>
<div class="modal fade" id="viewDtlsID"> 
 <div class="modal-dialog" id="modal-lgx">
   <div class="modal-content">
      <div class="modal-body">
	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  <iframe  id="complaintFrame" width="100%" height="250px" frameborder="no" scrolling="yes" > </iframe>
	  </div>
	  <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	  </div><!-- /.modal-content --> 
	</div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 
<div id="imageID" style="display:none"> 
 <a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
 <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
 <br>Patient Details
 </a>
</div>







<table class="tbheader" >
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>
<div width="100%" >
<div class="row" style="overflow-Y:scroll;">
<div  valign="top" class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commonDtlsField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="preauthDetailsForm" property="cardIssueDt"/></b></td></tr> --%> 
<tr><td class="labelheading1 tbcellCss" width="40%"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="preauthDetailsForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="gender"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="dateOfBirth"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="years"/>Y
 		<bean:write name="preauthDetailsForm" property="months"/>M
		<bean:write name="preauthDetailsForm" property="days"/>D</b></td></tr>

<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="caste"/></b></td></tr> --%>
 <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="slab"/></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="occupation"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="contactno"/></b></td></tr>
</table>
</div>
</fieldset>
</div>
<div   class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
<fieldset style="height:20em;" >
 <legend class="legendStyle" ><b>Patient Address</b></legend>
  <div style="height:20em;overflow:hidden" id='cardAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="preauthDetailsForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="preauthDetailsForm" property="pin" /></b></td></tr>
</table>
</div>
</fieldset>
</div>

<div   class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
<fieldset style="height:15em;" id='photoField'>
 <legend class="legendStyle"><b><fmt:message key="EHF.Photo"/></b></legend>
<table width="80%" height="80%" style="margin:auto auto">
<tr><td align="center">
 <logic:notEmpty name="preauthDetailsForm" property="photoUrl">
<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')"/>
</logic:notEmpty>
<logic:empty name="preauthDetailsForm" property="photoUrl">
<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
</logic:empty>
</td></tr></table>
</fieldset>
</div></div>
</div>













<br>


<table border="0" width="100%" class="table-responsive">
<tr class="tbheader">
<td id="topSlide" width="5%"  style="display:none;">
<img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img>
</td>
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong> Registration Details </strong>

</td>
<td id="menuSlide" width="5%" style="display:none;">
<!--<img id="topImage" src="images/updownArrow.jpg" width="30" height="20" style=cursor:hand; title="Maximize/Minimize" alt="Maximize" align="top" onclick="javascript:fn_maxmizeTop()" ></img> -->
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
</td>
</tr></table>
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" class="tableClass1 table-responsive">
<!-- <tr ><td  colspan="6" align="center"  class="tbheader" ><b> Preauth Details </b></td></tr>
<tr><td  colspan="6" align="left"  class="tbheader" ><b> NWH Details </b></td></tr> -->

<tr><td  class="labelheading1 tbcellCss"><b>Hopital Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="hospitalName" /></td>
<td class="labelheading1 tbcellCss"><b>Address</b></td><td colspan="3"  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="hospAddress" /></td>
<td class="labelheading1 tbcellCss"><b>Card Number</b></td><td colspan="3"  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="cardNo" /></td></tr>
<tr>
<td  class="labelheading1 tbcellCss"><b>Case Number</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="caseNo" /></td>
<td class="labelheading1 tbcellCss"><b>IP Registration Date</b></td><td colspan="3"  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="dtTime" /></td>
<td class="labelheading1 tbcellCss"><b>IP Number</b></td><td colspan="3"  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="ipNo" /></td>
</tr></table>
<%-- <tr><td colspan="6" align="left" class="tbheader" ><b>Treating Doctor Details</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docName" /></td>
<td class="labelheading1 tbcellCss"><b>Reg No</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docReg" /></td>
<td class="labelheading1 tbcellCss"><b>Type</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docType" /></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b>Mobile Number</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docMobNo" /></td>
<td class="labelheading1 tbcellCss"><b>Qualification</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docQual" /></td>
</tr> 
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" class="tableClass1 table-responsive">
<tr ><td  colspan="6" align="center"  class="tbheader" ><b> Diagnosis and Treatment </b></td></tr>
<!-- <tr ><td  colspan="6" align="center"  class="labelheading1 tbcellCss"><b> Diagnosis </b></td></tr> -->

<tr><td class="labelheading1 tbcellCss"><b>Diagnosis Type</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="diagnosisType" /></td>
<td class="labelheading1 tbcellCss"><b>Main Category Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="mainCatName" /></td>
<td class="labelheading1 tbcellCss"><b>Category Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="catName" /></td>
</tr>
<tr><td class="labelheading1 tbcellCss"><b>Sub Category Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="subCatName" /></td>
<td class="labelheading1 tbcellCss"><b>Disease Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="disName" /></td>
<td class="labelheading1 tbcellCss"><b>Disease Anatomical Name</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="disAnatomicalSitename" /></td>
</tr>
<logic:equal value="Y" name="preauthDetailsForm" property="telephonicFlag">
<tr><td class="labelheading1 tbcellCss"><b>Telephonic Id</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="telephonicId" /></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>Telephonic Remarks</b></td><td  class="tbcellBorder"><bean:write name="preauthDetailsForm" property="telephonicRemarks" ></bean:write></td></tr>
</logic:equal>
</table>
--%>
<br>
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"  id="addSurgdiv"  style="table-layout: fixed;" class="table-responsive">
<c:if test="${viewType eq 'medco' }" >
<tr  class="tbheader"  align="center"><td colspan="8">&nbsp;<b>Treatment Protocol</b></td></tr>
</c:if>
<c:if test="${viewType ne 'medco' }" >
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b>Treatment Protocol</b></td></tr>
</c:if>

<tr  >
<td width="10%" style="word-wrap:break-word;" class="labelheading1 tbcellCss"><b>Category Name</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>ICD Category Name</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Procedure Name</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Units</b></td>
<!-- <td width="10%">ICD Procedure Name</td> -->
<td width="20%" class="labelheading1 tbcellCss" ><b>Special Investigations</b> </td>
<td width="35%" class="labelheading1 tbcellCss"><b>Remarks</b></td>
<td width="15%" class="labelheading1 tbcellCss"><b>Treating Doctor name</b></td>

<!-- <td width="10%">Amount</td>-->
<c:if test="${viewType eq 'medco' }" >
<td width="5%" class="labelheading1 tbcellCss"></td>
</c:if>

</tr>
<logic:present name="preauthDetailsForm" property="lstPreauthVO">
<bean:size id="caseList" name="preauthDetailsForm" property="lstPreauthVO"/>
<logic:greaterThan value="0" name="caseList">
<logic:iterate id="results" name="preauthDetailsForm" property="lstPreauthVO" indexId="index" >
<tr id="splInvetsDataTable<%=index+1%>" style="display:true">
<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="asriCatName" />(<bean:write name="results" property="catId" />)</td>
<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="catName" />(<bean:write name="results" property="icdCatCode" />)</td>
<!-- <td><bean:write name="results" property="procCode" /></td>-->
<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="procName" />(<bean:write name="results" property="icdProcCode" />)</td>
<td  class="tbcellBorder" style="word-wrap:break-word;">
<logic:notEqual name="results" property="opProcUnits" value="-1">
	<bean:write name="results" property="opProcUnits" />
</logic:notEqual>
<logic:equal name="results" property="opProcUnits" value="-1">
	-NA-
</logic:equal>
</td>

<bean:size id="splattachList" name="results" property="lstSplInvet" />
<c:set var="splInvstCount" value="1" />
<logic:greaterThan value="0" name="splattachList" >
<td  class="tbcellBorder" style="word-wrap:break-word;">
<logic:iterate id="results1" name="results" property="lstSplInvet" indexId="index1" >

<bean:write name="results1" property="filename" /></a>
<c:if test="${fn:length(results.lstSplInvet) ne splInvstCount}">

</c:if>
<c:set var="splInvstCount" value="${splInvstCount+1 }" />


</logic:iterate>
</td>
</logic:greaterThan> 
<logic:equal value="0" name="splattachList">
<td  style="word-wrap:break-word;" class="tbcellBorder">&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;    -- &nbsp;</td>
</logic:equal>
<!--Sneha: removed bean:write to get , wherever &#44; is present  -->
<td  style="word-wrap:break-word;" class="tbcellBorder">&nbsp;&nbsp;${results.investRemarks}</td>
 <td class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="docName" /></td> 

<c:if test="${viewType eq 'medco' }" >
<td  style="word-wrap:break-word;" class="tbcellBorder"><a href="javascript:fn_hideTable('splInvetsDataTable<%=index+1%>','<bean:write name="results" property="seqNo" />','<%=index+1%>','<bean:write name="results" property="icdProcCode" />','<bean:write name="results" property="catId" />')" >Delete</a></td>
</c:if>

<script>
/* if(totPkgAmt == null || totPkgAmt=='')
totPkgAmt = parseInt('<bean:write name="results" property="amount" />');
else
	totPkgAmt = totPkgAmt+parseInt('<bean:write name="results" property="amount" />'); */
	 splCount='<%=index+1%>';
     surgertIdsarray.push('<bean:write name="results" property="icdProcCode" />');
     categoryIdsarray.push('<bean:write name="results" property="catId" />');
	 amtListArray[splCount] = new Array();
	 amtListArray[splCount][0]='<bean:write name="results" property="catId" />';
	 amtListArray[splCount][1]='<bean:write name="results" property="commonCatAmt" />';
	 amtListArray[splCount][2]='<bean:write name="results" property="icdAmt" />';
	 amtListArray[splCount][3]='<bean:write name="results" property="hospStayAmt" />';
	 amtListArray[splCount][4]='<bean:write name="results" property="days" />';
	 amtListArray[splCount][5]="*";
	 splCount++;
	 /*checkInactiveSurgArray.push('<bean:write name="results" property="catId" />~<bean:write name="results" property="asriCatName" />~<bean:write name="results" property="activeYN" />');*/
</script>
</tr>
</logic:iterate>
</logic:greaterThan>
</logic:present>
</table>




<div id="selectionBlock" style="display:none">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" class="table-responsive" >
<tr><td class="tbheader" align="left" colspan="4"><b>Plan Of Treatment(Therapy)</b></td></tr>
<tr><td colspan="2" width="50%" class="labelheading1 tbcellCss">&nbsp; <b>Category Name:</b> <font color="red">*</font></td><td colspan="2" width="50%" class="labelheading1 tbcellCss"><b>ICD Category Name : </b><font color="red">*</font></td></tr>
<tr><td colspan="2" class="tbcellBorder">&nbsp;<html:select title="Please select category name" name="preauthDetailsForm" property="category"  styleId="category" style="width:400px;" onchange="javascript:fn_getICDCatList();" onmousemove="javascript:getTitles('category')">
<option  value="-1">----Select----</option>
<html:options collection="catList" property="ID" labelProperty="VALUE"  />
</html:select></td>
<td colspan="2" align="left" style="width:420px;" class="tbcellBorder">
&nbsp;<html:select title="Please select ICD category name" name="preauthDetailsForm" property="icdCatCode"  styleId="icdCatCode" style="width:400px;" onchange="javascript:fn_getProcedures()" onmousemove="javascript:getTitles('icdCatCode')">
<option  value="-1">----Select----</option>
</html:select>		
</td>
</tr>
<tr>
<!-- <td >&nbsp;Procedure Name : <font color="red">*</font></td> -->
<td colspan="2" class="labelheading1 tbcellCss" ><b>ICD Procedure Name:</b><font color="red">*</font></td>
<td  colspan="1" class="labelheading1 tbcellCss" id="unitsLabelTd" style="display:none"><b>Units</b> <font color="red">*</font></td>
<td colspan="1" class="labelheading1 tbcellCss"><b>Treating Doctor</b><font color="red">*</font></td>
</tr>
<tr>
<!-- <td >
&nbsp;<html:select  name="preauthDetailsForm" property="procCode" styleId="procCode" style="width:400px;" onchange="javascript:fn_getICDProcedures()">
<option  value="">----Select----</option>
</html:select>
</td> -->
<td colspan="2" class="tbcellBorder">
&nbsp;<html:select title="Please select ICD procedure name" name="preauthDetailsForm" property="icdProcCode"  styleId="icdProcCode" style="width:400px;" onchange="javascript:fn_getSpecialInvestigations_test()" onmousemove="javascript:getTitles('icdProcCode')">
<option  value="-1">----Select----</option>
</html:select>
</td>
<td colspan="1" class="tbcellBorder" id="unitsTd" style="display:none">
<html:select name="preauthDetailsForm" property="procUnits" styleId="procUnits" style="width:100px;" title="Select No of Units" onmouseover="addTooltip('procUnits')">
<html:option value="-1">----Select----</html:option>
<%-- <html:options collection="dentalUnitsList" property="ID" labelProperty="VALUE"  />  --%>
</html:select>
</td>

<td colspan="1" class="tbcellBorder" >
<html:select name="preauthDetailsForm" property="docSpecReg" styleId="docSpecReg" style="WIDTH:15em" title="Select Treating Doctor" onchange="javascript:fn_enableTreatingDocDiv()" >                        
<html:option value="-1">-----Select-----</html:option>     
</html:select>
</td>
</tr>

<tr id="TreatingDocOthersId" style="display:none">
<td colspan="4">
<table width="100%" class="table-responsive">
<tr><td class="labelheading1 tbcellCss" > Treating Doctor Name </td><td class="labelheading1 tbcellCss"> Treating Doctor Reg No</td>
<td class="labelheading1 tbcellCss" > Treating Doctor Qualification </td><td class="labelheading1 tbcellCss"> Treating Doctor Contact No</td></tr>
<tr><td class="tbcellBorder" > <html:text property="treatDocName" name="preauthDetailsForm" title="Treating doctor Name" maxlength="50" onchange="javascript:chkAlpha('treatDocName','Treating doctor Name');"></html:text> </td>
<td class="tbcellBorder"> <html:text property="treatDocRegNo" name="preauthDetailsForm"  title="Treating doctor Reg No" maxlength="20" onchange="javascript:chkAlphaNumeric('treatDocRegNo','Treating doctor RegNo');"></html:text></td>
<td class="tbcellBorder" > <html:text property="treatDocQualification" name="preauthDetailsForm"  title="Treating doctor qualification" maxlength="20" onchange="javascript:chkAlpha('treatDocQualification','Treating doctor Qualification');"></html:text> </td>
<td class="tbcellBorder"> <html:text property="treatDocContact" name="preauthDetailsForm"  title="Treating doctor contact number" maxlength="11" onchange="javascript:checkcontact(this,'Treating doctor Contact no');"></html:text></td></tr>
</table>

<tr>

<tr>
<td colspan="4"><table width="100%" border="0">
<tr>
<td class="labelheading1 tbcellCss" >&nbsp; <b>Special Investigation :</b><font color="red">*</font>&nbsp;&nbsp;</td>
<td nowrap="nowrap" colspan="3" height="32" rowspan="2">&nbsp;&nbsp;<div id="myDivSpl">    </div></td>
 <td nowrap="nowrap" colspan="6">&nbsp;&nbsp;<div id="myDivSplUpload">    </div></td></tr>
</table></td>
</tr>
<tr><td  colspan="4" class="labelheading1 tbcellCss"> &nbsp;<b>Treating Doctor Remarks  :</b><font color="red">*</font> </td></tr>
<tr><td  colspan="4" class="tbcellBorder">
 &nbsp;<html:textarea name="preauthDetailsForm" property="treatingDocRmks" style="width:98%;" styleId="treatingDocRmks" onkeydown="check_maxLength('treatingDocRmks',3000)" 
 onchange="checkRemarks(this.value,3000,'treatingDocRmks')" title="Please enter remaks"></html:textarea>
</td></tr>
<tr align="center"><td colspan="4"><button class="but"   type="button" name="addSurgicalDtls" id="addSurgicalDtls" value="Add Surgical Details"  onclick="javascript:fn_addSurgicalDtls()"  >Add Surgical Details</button></td></tr>
</table>
</div>

<div id="selectionBlock" >
<br>
<c:set var="loopCount" value="0" />
<table id="comorbid" width="100%" border="1" cellspacing="0" cellpadding="0" style="display:block">
<tr>						
	 <td   align="center" valign="top"  class="tbheader" colspan="3">
		<b>Comorbid Conditions</b>
	 </td>
 </tr>						 
 <tr>
	
	<c:forEach items="${lstComorbid}" var="com" >
	<c:set var="loopCount" value="${loopCount + 1}" /> 
	<c:if test="${(loopCount % 3) eq 1}">
	<tr>
	</c:if>	
	<td  align="left" class="tbcellCss">
			<input type="checkbox" id='${com.comorbidId}' value='${com.comorbidVal}' title="${com.comorbidName}"  />&nbsp;&nbsp;${com.comorbidName} &nbsp;&nbsp;
	</td>
	
	</c:forEach>
 </tr>
</table>
<c:set var="loopCount1" value="0" />
<table id="comorbidDisplayBlock" width="100%" border="1" cellspacing="0" cellpadding="0" style="display:none">
<tr>						
	 <td   align="center" valign="top"  class="tbheader" colspan="3">
		<b>Comorbid Conditions</b>
	 </td>
 </tr>						 
 <tr>
	
	<c:forEach items="${preauthDetailsForm.comorbidValues}" var="com" >
	<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
	<c:if test="${(loopCount1 % 3) eq 1}">
	<tr>
	</c:if>	
	<td  align="left" class="tbcellCss">
			&nbsp;${loopCount1}. &nbsp;${com.ID} (${com.CONST}) <!--(Rs ${com.VALUE+((com.VALUE%1>0.5)?(1-(com.VALUE%1))%1:-(com.VALUE%1))}) -->

	</td>
	
	</c:forEach>
 </tr>
</table>
<br>
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"  id="testtable" class="table-responsive" >
<tr  class="tbheader"  align="center"><td colspan="5">&nbsp;<b>Admission Details</b></td></tr>
<tr><td class="labelheading1 tbcellCss">Admission Type <font color="red">*</font> &nbsp;&nbsp;&nbsp;</td>
<td class="labelheading1 tbcellCss">
<c:if test="${viewType eq 'medco' }" >
<html:radio property="admissionRadio" value="PLN" name="preauthDetailsForm" styleId="admissionRadio" onclick="javascript:checkRadio('PLN')" title="Planned">Planned</html:radio>
&nbsp;
<html:radio property="admissionRadio" value="EMG" name="preauthDetailsForm" styleId="admissionRadio" onclick="javascript:checkRadio('EMG')" title="Emergency">Emergency</html:radio>
&nbsp;
</c:if>
<c:if test="${viewType ne 'medco' }" >
<html:radio property="admissionRadio" value="PLN" name="preauthDetailsForm" styleId="admissionRadio" disabled="true" title="Planned">Planned</html:radio>
&nbsp;
<html:radio property="admissionRadio" value="EMG" name="preauthDetailsForm" styleId="admissionRadio" disabled="true" title="Emergency">Emergency</html:radio>
&nbsp;
</c:if>
</td>
<td class="labelheading1 tbcellCss">
Admission Date <font color="red">*</font></td>
<td class="labelheading1 tbcellCss">
<c:if test="${viewType eq 'medco' }" >
<html:text name="preauthDetailsForm" property="admissionDate" readonly="true" styleId="admissionDate" disabled="true" onchange="validateDate(this)" title="Please select date"/> 
<!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('admissionDate','430','350')" ></img> -->
</c:if>
<c:if test="${viewType ne 'medco' }" >
  <html:text name="preauthDetailsForm" property="admissionDate" readonly="true" styleId="admissionDate" disabled="true"/> 
</c:if>

</td>
</tr>
<tr><td class="labelheading1 tbcellCss">Procedure Consent <font color="red">*</font></td>
<td class="labelheading1 tbcellCss">
<c:if	test="${ viewType eq 'medco'}">
<html:radio property="procedureConsent" value="Yes" name="preauthDetailsForm" styleId="procedureConsent" onclick="javascript:checkRadio1(this,'Yes')" title="Planned">Yes</html:radio>
&nbsp;
<html:radio property="procedureConsent" value="No" name="preauthDetailsForm" styleId="procedureConsent" onclick="javascript:checkRadio1(this,'No')" title="Emergency">No</html:radio>
&nbsp;
</c:if>
<c:if	test="${preauthDetailsForm.procedureConsent!='' &&  viewType ne 'medco'}" >
<html:radio property="procedureConsent" value="Yes" name="preauthDetailsForm" styleId="procedureConsent" onclick="javascript:checkRadio1(this,'Yes')" title="Planned" disabled="true">Yes</html:radio>
&nbsp;
<html:radio property="procedureConsent" value="No" name="preauthDetailsForm" styleId="procedureConsent" onclick="javascript:checkRadio1(this,'No')" title="Emergency" disabled="true">No</html:radio>
&nbsp;
</c:if>
</td>
<td class="labelheading1 tbcellCss">Medical or cardiology clearance <font color="red">*</font> </td>
<td class="labelheading1 tbcellCss">
<c:if	test="${ viewType eq 'medco'}">
<html:radio property="medCardioClearence" value="Yes" name="preauthDetailsForm" styleId="medCardioClearence" onclick="javascript:checkRadio1(this,'Yes')" title="Planned">Yes</html:radio>
&nbsp;
<html:radio property="medCardioClearence" value="No" name="preauthDetailsForm" styleId="medCardioClearence" onclick="javascript:checkRadio1(this,'No')" title="Emergency">No</html:radio>
&nbsp;
</c:if>
<c:if	test="${preauthDetailsForm.procedureConsent!='' &&  viewType ne 'medco'}">
<html:radio property="medCardioClearence" value="Yes" name="preauthDetailsForm" styleId="medCardioClearence" onclick="javascript:checkRadio1(this,'Yes')" title="Planned" disabled="true">Yes</html:radio>
&nbsp;
<html:radio property="medCardioClearence" value="No" name="preauthDetailsForm" styleId="medCardioClearence" onclick="javascript:checkRadio1(this,'No')" title="Emergency" disabled="true">No</html:radio>
&nbsp;
</c:if>

</td></tr>
<tr><td  class="labelheading1 tbcellCss">
Blood transfusion <font color="red">*</font></td>
<td class="labelheading1 tbcellCss">
<c:if	test="${viewType eq 'medco'}">
<html:radio property="bloodTransfusion" value="Yes" name="preauthDetailsForm" styleId="bloodTransfusion" onclick="javascript:checkRadio1(this,'Yes')" title="Planned">Yes</html:radio>
&nbsp;
<html:radio property="bloodTransfusion" value="No" name="preauthDetailsForm" styleId="bloodTransfusion" onclick="javascript:checkRadio1(this,'No')" title="Emergency">No</html:radio>
&nbsp;
</c:if>
<c:if	test="${preauthDetailsForm.bloodTransfusion!='' &&  viewType ne 'medco'}">
<html:radio property="bloodTransfusion" value="Yes" name="preauthDetailsForm" styleId="bloodTransfusion" onclick="javascript:checkRadio1(this,'Yes')" title="Planned" disabled="true">Yes</html:radio>
&nbsp;
<html:radio property="bloodTransfusion" value="No" name="preauthDetailsForm" styleId="bloodTransfusion" onclick="javascript:checkRadio1(this,'No')" title="Emergency" disabled="true">No</html:radio>
&nbsp;
</c:if>
</td></tr>



</table>
</div>
<c:if test="${viewType eq 'ppd'||viewType eq 'coc'}">
<table width="100%" border="0" cellspacing="2" cellpadding="1" class="table-responsive" >
<tr><td>&nbsp;</td></tr>
							<tr><td align="center"><B>&nbsp;Non Technical Check List</B></td></tr>
							<tr><td align="center">
							<div id="L1CHK_LST">
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="1) Is diagnosis supported by sufficient evidence" id="L1_Q2">&nbsp;<input type="radio" id="L1Diag_1" value="YES" name="L1Diag">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L1Diag_2" value="NO" name="L1Diag">No<br>
							<input type="text" style="width:400px;border:0px;font-size:12px;" value="2) Is the Suggested line of Treatment Supported by Sufficient evidence" id="L1_Q3">&nbsp;<input type="radio" id="L1Inv_1" value="YES" name="L1Inv">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L1Inv_2" value="No" name="L1Inv">No<br>
							</div>
							</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</table>
</c:if>
<c:if test="${viewType eq 'ptd'}">
<table width="100%" border="0" cellspacing="2" cellpadding="1" >
<tr><td>&nbsp;</td></tr>
							<tr><td align="center" class="labelheading1 tbcellCss"><B>&nbsp;Non Technical Check List <font color="red">*</font> </B></td></tr>
							<tr><td align="center">
							<div id="L2CHK_LST">
							<input type="text" readonly="readonly" style="width:400px;border:0px;font-size:12px;" value="1) Beneficiary is identified" id="L2_Q1" title="Beneficiary is identified">&nbsp;<input type="radio" value="YES" id="L2Chk_1" name="L2Chk" title="Yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Chk_2" value="NO" name="L2Chk" title="No">No<br>
							<input type="text" readonly="readonly" style="width:400px;border:0px;font-size:12px;" value="2) Investigations are genuine" id="L2_Q2" title="Investigations are genuine">&nbsp;<input type="radio" value="YES" id="L2Chk1_1" name="L2Chk1" title="Yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Chk1_2" value="NO" name="L2Chk1" title="No">No<br>
							<input type="text"  readonly="readonly" style="width:400px;border:0px;font-size:12px;" value="3) Diagnosis is supported by evidence" id="L2_Q3" title="Diagnosis is supported by evidence">&nbsp;<input type="radio" id="L2Eligi_1" value="YES" name="L2Eligi" title="Yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Eligi_2" value="NO" name="L2Eligi" title="No">No<br>
							<input type="text" readonly="readonly" style="width:400px;border:0px;font-size:12px;" value="4) Therapies are indicated" id="L2_Q5" title="Therapies are indicated">&nbsp;<input type="radio" id="L2Inv_1" value="YES" name="L2Inv" title="Yes">Yes&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  id="L2Inv_2" value="NO" name="L2Inv" title="No">No<br>
							</div>
							</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</table>
</c:if>

<table width="100%" border="0" class="table-responsive" >
<tr><td>&nbsp;</td></tr>
<!-- <tr><td>Previously consumed Amount during the financial year </td>
<td><html:text property="prevConAmt" name="preauthDetailsForm" readonly="true"></html:text></td></tr>-->










<logic:present name="preauthDetailsForm" property="lstworkFlow">
<bean:size id="wrkList" name="preauthDetailsForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"  id="testtable" class="table-responsive"  >
<tr  class="tbheader"  align="center"><td colspan="6">&nbsp;<b>Work Flow</b></td></tr>
<tr   ><td width="5%" class="labelheading1 tbcellCss"><b>SNo</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Date & Time</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Role & Name</b></td>			
<td width="30%" class="labelheading1 tbcellCss"><b>Remarks</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Action</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Amount</b></td>		
</tr>

<logic:iterate id="results1" name="preauthDetailsForm" property="lstworkFlow" indexId="index1" >
<tr>
<td class="tbcellBorder"><%=index1+1%></td>

<td class="tbcellBorder"><bean:write name="results1" property="auditDate" /></td>
<td class="tbcellBorder"><bean:write name="results1" property="auditRole" /> 
<c:if test="${viewAuditNames eq 'Y'}">
( <bean:write name="results1" property="actby" />)
</c:if>
</td>
<c:choose>

<c:when test="${results1.auditComboRole eq 'CD424' && userRole ne 'CD9' &&  userRole ne 'CD10'}" >
<td class="tbcellBorder"><a href="#" onclick="javascript:fn_pexQuestions();"><bean:write name="results1" property="auditRemarks" /></a></td>
</c:when>
<c:otherwise  >
<td class="tbcellBorder"><bean:write name="results1" property="auditRemarks" /></td>
</c:otherwise>
</c:choose>
<td class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
<td class="tbcellBorder"><bean:write name="results1" property="auditAmount" /></td>
</tr>

</logic:iterate>

</table>
</td></tr>
</logic:greaterThan>
</logic:present>

















<c:if test="${viewType ne 'ptd' and viewType ne 'ceo' and viewType ne 'eo' }">
<tr><td width="50%" class="labelheading1 tbcellCss">Total Package Amount Admissible under  the scheme  Rs.</td>
<td width="50%" class="tbcellBorder labelheading1">
<bean:write property="preauthTotalPackageAmt" name="preauthDetailsForm" ></bean:write></td></tr>
<%-- </c:if> 
<c:if	test="${medcoFlg eq 'Y'}"> --%>
<%-- <tr><td width="50%" class="labelheading1 tbcellCss">NABH Amount that can be recieved  Rs.</td>
<td width="50%" class="tbcellBorder">
<html:text property="preauthNabhAmt" name="preauthDetailsForm" readonly="true" title="NABH Amount"></html:text></td></tr> --%>
<!-- <table width="100%" border="0">
<tr>
		<td width="100%">
			<b>Note: NABH Amount is only for AP Cases and will vary according
							 to the final claim amount approved for this particular case.</b>
		</td>
	</tr> 
</table> <br>	-->

</c:if>
<c:if test="${viewType eq 'ptd' || viewType eq 'ceo' || viewType eq 'eo' }">

<tr  class="tbheader"  align="center"><td colspan="6">&nbsp;<b>Preauthorization Details</b></td></tr>

<tr><td width="50%" class="labelheading1 tbcellCss">Total Package Amount Admissible under  the scheme  Rs.</td>
<td width="50%" class="labelheading1 tbcellBorder" Id="preauthPckgAmt">
<bean:write property="preauthPckgAmt"  name="preauthDetailsForm" ></bean:write></td></tr> 
<tr><td width="50%" class="labelheading1 tbcellCss">Approved Package Amount Rs. <font color="red">*</font></td>
<td width="50%" class="tbcellBorder">
<html:text property="apprvdPckAmt" styleId="apprvdPckAmt" name="preauthDetailsForm"  maxlength="10" onchange="javascript:fn_testPckApprAmt()" title="Please enter approved package amount" ></html:text></td></tr>
<tr><td width="50%" class="labelheading1 tbcellCss">Comorbid Amount Rs.</td>
<td width="50%" class="labelheading1 tbcellBorder">
<bean:write property="comorBidAmt" name="preauthDetailsForm"></bean:write></td></tr>
<tr><td width="50%" class="labelheading1 tbcellCss">Approved Comorbid Amount Rs.</td>
<td width="50%" class="tbcellBorder">
<html:text property="comorBidAppvAmt" name="preauthDetailsForm" styleId="comorBidAppvAmt" onchange="javacsript:fn_testComorbidApprAmt()" maxlength="10" title="Please enter approved comorbid amount"></html:text></td></tr>
<!--<logic:equal value="Y" name="preauthDetailsForm" property="nabhFlg" >
<tr><td width="50%" class="labelheading1 tbcellCss">NABH Amount Rs.</td>
<td width="50%" class="tbcellBorder">
<html:text property="nabhAmt" name="preauthDetailsForm" styleId="nabhAmt" readonly="true" title="NABH amount"></html:text></td></tr>
</logic:equal>
<logic:notEqual value="Y" name="preauthDetailsForm" property="nabhFlg">
<html:hidden property="nabhAmt" styleId="nabhAmt" name="preauthDetailsForm" value="0"/>
</logic:notEqual>-->
<tr><td width="50%" class="labelheading1 tbcellCss">Total Approved Amount Rs.</td>
<td width="50%" class="tbcellBorder">
<html:text property="ptdTotalApprvAmt" name="preauthDetailsForm" styleId="ptdTotalApprvAmt" readonly="true" title="Total approved amount"></html:text></td></tr>

</c:if>

<tr id="remarksTr">
<td  colspan="2" class="labelheading1 tbcellCss"> &nbsp; Remarks  :<font color="red">*</font> </td></tr>
<tr id="remarksTr1"><td  colspan="2" class="tbcellBorder">
 &nbsp;<html:textarea name="preauthDetailsForm" property="genRemarks" styleId="genRemarks" style="width:98%;" onkeydown="check_maxLength('genRemarks',3000)" onchange="checkRemarks(this.value,3000,'genRemarks')" title="Please enter remarks"></html:textarea>
</td>
</tr>

<tr><td>&nbsp;</td></tr>



<tr>
<table width="100%">
<!-- <tr><td>&nbsp;</td></tr> -->

<c:set var="buttons" value="${preauthDetailsForm.buttonsLst}"/>
<c:if test="${fn:length(buttons) > 0}">

<tr><td><br></td></tr>
	<tr id="buttonVisibleId" >
	<td colspan="6" align="center">
	<div id="buttonBlock" style="align:center">
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
	<!--<button class="but"   type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" id="${EHFbutton.ID}" style="width:180px" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}','${EHFbutton.VALUE}');">${EHFbutton.VALUE}</button>-->
	<button class="but but" id="${EHFbutton.ID}" type="button" name="${EHFbutton.ID}" value="${EHFbutton.VALUE}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}','${EHFbutton.VALUE}');" >&nbsp;${EHFbutton.VALUE}</button>	
	</c:forEach>


	</div>
	</td>
	</tr>
	<tr><td>&nbsp;</td></tr>

	</c:if>	

<c:if test="${cancelBut==true}" >
<tr id="cancelRemarksTD">
<td  colspan="2" class="labelheading1 tbcellCss"> &nbsp; Remarks for cancellation   :<font color="red">*</font> </td></tr>
<tr id="cancelRemarksTD1"><td  colspan="2" class="tbcellBorder">
 &nbsp;<html:textarea name="preauthDetailsForm" property="cancelRemarks" styleId="cancelRemarks" style="width:98%;" onkeydown="check_maxLength('cancelRemarks',3000)" onchange="checkRemarks(this.value,3000,'cancelRemarks')" title="Please enter remarks"></html:textarea>
</td>
</tr>

<tr>
	<td colspan="6" align="center">
<button class="but" type="button" name="cancelPreauth" id="cancelPreauth" value="cancelPreauth"  onclick="javascript:fn_cancelPreauth()" >Cancel Preauthorization</button>
</td></tr>
</c:if>
</table>
</tr>

<%-- <c:if test="${viewType eq 'ppd'}">
<tr><td colspan="2" align="center">
<button class="but" type="button" name="recomendedApproval" id="recomendedApproval" value="Recomended Approval"  onclick="javascript:fn_SubmitNextLvl('ppd','Approve')" >Recomended Approval</button>
<button class="but" type="button" name="recomendedRejection" id="recomendedRejection" value="Recomended Rejection"  onclick="javascript:fn_SubmitNextLvl('ppd','Reject')" >Recomended Rejection</button>
<button class="but" type="button" name="ppdPending" id="ppdPending" value="Pending" onclick="javascript:fn_SubmitNextLvl('ppd','pending')" >Pending</button>

</td></tr>
</c:if> --%>

<tr><td>&nbsp;</td></tr>


<tr align="center" ><td colspan="2">

</td></tr>


</table>

<div id="enhancementBlock" style="display:none">

</div>
<div id="enhDisplaydiv" style="display:none">
<table  cellpadding="1" cellspacing="1" align="center" width="100%" border="0">


	<c:if test="${ viewType ne 'enhMedco'  }" >
		<tr  class="tbheader"  align="center" id="enhancemetTitlesId" style="display:none">
			<td colspan="4">&nbsp;<b>Enhancement Requested List</b></td>
		</tr>
	</c:if>
	<tr  align="center" id="enhancemetTitlesId1" style="display:none">
		<!--<td width="5%" class="labelheading1 tbcellCss"><b>Sno</b></td>-->
		<td width="11%" class="labelheading1 tbcellCss"><b>Type</b></td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Code</b></td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Quantity</b></td>
		<td width="12%" class="labelheading1 tbcellCss"><b>Units</b></td>
		<!-- <td width="15%">Amount</td> -->
		<c:if test="${ viewType eq 'enhMedco'  }" >
			<td width="5%" class="labelheading1 tbcellCss"></td>
		</c:if>
	</tr>
	<logic:present name="preauthDetailsForm" property="enhamcementList">
		<bean:size id="enhList" name="preauthDetailsForm" property="enhamcementList"/>
		<logic:greaterThan value="0" name="enhList">
			<logic:iterate id="enhResults" name="preauthDetailsForm" property="enhamcementList" indexId="index" >
				<tr id="EnhancementDataTable<%=index+1%>" style="display:true" align="center" >
					<!--<td class="tbcellBorder">< %=index+1%></td>-->
					<td class="tbcellBorder"><bean:write name="enhResults" property="enhType" /></td>
					<td class="tbcellBorder"><bean:write name="enhResults" property="enhCode" /></td>
					<td class="tbcellBorder"><bean:write name="enhResults" property="enhQuant" /></td>
					<td class="tbcellBorder"><bean:write name="enhResults" property="enhUnits" /></td>
					<!-- <td><bean:write name="enhResults" property="enhAmt" /></td> -->

					<c:if test="${ viewType eq 'enhMedco'  }" >
						<td class="tbcellBorder"><a href="javascript:fn_hideTableEnhc('EnhancementDataTable<%=index+1%>','<bean:write name="enhResults" property="enhSno" />','<bean:write name="enhResults" property="enhAmt" />','<bean:write name="enhResults" property="enhQuantCode" />')" >Delete</a></td>
					</c:if>

					<script>
					if(enhAmt == null || enhAmt=='')
						enhAmt = parseInt('<bean:write name="enhResults" property="enhAmt" />');
					else
						enhAmt = enhAmt+parseInt('<bean:write name="enhResults" property="enhAmt" />');
					enhancementIdsarray.push('<bean:write name="enhResults" property="enhSno" />');
					enhQuantCodeArray.push('<bean:write name="enhResults" property="enhQuantCode" />');

					</script>
				</tr>
			</logic:iterate>
		</logic:greaterThan>
	</logic:present>

	<table width="100%" border="0">
		<tr>
			<td>
				<div id="EnhancementDiv">
				</div>
			</td>
		</tr>
	</table>
	<tr>
		<td>
			<table  width="100%">
				<tr>
					<td  id="prescriptionData" colspan="4">
						
					</td>
				</tr>

				<tr>
					<td colspan="4" class="tbcellBorder">
						<table  width="100%"  id="drugsTable"  border="0" style="display:none">
							<tr  class="tbheader"  align="center" id="drugsTableId" >
								<td colspan="10">&nbsp;<b>Drugs List</b></td>
							</tr>
							<tr>  
								<!--<td width="5%" class="labelheading1 tbcellCss"><b>SNo.</b></td> -->       
								<td width="15%" class="labelheading1 tbcellCss"><b>Drug Type Name</b></td>   
								<td width="15%" class="labelheading1 tbcellCss"><b>Drug Sub Type Name</b></td>
								<td width="15%" class="labelheading1 tbcellCss"><b>Drug name</b></td>
								<td width="15%" class="labelheading1 tbcellCss"><b>Route</b></td>
								<td width="10%" class="labelheading1 tbcellCss"><b>Strength</b></td>
								<td width="10%" class="labelheading1 tbcellCss"><b>Dosage</b></td>
								<td width="10%" class="labelheading1 tbcellCss"><b>Medication Period</b></td>
								<td width="5%" class="labelheading1 tbcellCss"><b>Units</b></td>
								<td width="5%" class="labelheading1 tbcellCss">&nbsp;</td>
							</tr>
							<logic:present name="preauthDetailsForm" property="drugLt">
								<bean:size  id="drugSize" name="preauthDetailsForm" property="drugLt"/>
								<logic:greaterThan value="0" name="drugSize">
									<%int k = 1;%>
									<logic:iterate id="drugLst" name="preauthDetailsForm" property="drugLt" >
										<tr>  
											<!--<td width="5%">< %=k++ %></td>    -->    
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="drugTypeName" /></td>   
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="drugSubTypeName" /></td>  
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="drugName" />(<bean:write name="drugLst" property="drugCode" />)</td>  
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="route" /></td>  
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="strength" /></td>  
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="dosage" /></td>  
											<td width="13%" class="tbcellBorder"><bean:write name="drugLst" property="medicationPeriod" /></td> 
											<td width="5%" class="tbcellBorder"><bean:write name="drugLst" property="noOfUnit" /></td>
											<c:if test="${ viewType eq 'enhMedco'  }" >
												<td width="5%" class="tbcellBorder"><input class="but" type="button" value="Delete" name=<bean:write name='drugLst' property='drugCode' /> id=<bean:write name='drugLst' property='drugCode' /> onclick="javascript:confirmDeleteRow(this,'editDrug','<bean:write name='drugLst' property='drugId' />','<bean:write name='drugLst' property='totAmt' />');"/></td>
											</c:if>
											<script>
											if(enhAmt == null || enhAmt=='')
										   enhAmt = parseInt('<bean:write name="drugLst" property="totAmt" />');
										   else
										   enhAmt = enhAmt+parseInt('<bean:write name="drugLst" property="totAmt" />');
										   drugIdsarray.push(encodeURI('<bean:write name="drugLst" property="drugCode" />'));
										   </script>
										</tr>
									</logic:iterate>
								</logic:greaterThan>
							</logic:present>        
						</table>
					</td>
				</tr>


			</table>
		</td>
	</tr>

</table>



</div>

<div id="enhButtonBlock" style="display:none">


<logic:present name="preauthDetailsForm" property="lstEnhancementworkFlow">
<bean:size id="wrkList1" name="preauthDetailsForm" property="lstEnhancementworkFlow"/>
<logic:greaterThan value="0" name="wrkList1">
<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr  class="tbheader"  align="center"><td colspan="6">&nbsp;<b>Enhancement Work Flow</b></td></tr>
<tr   ><td width="5%" class="labelheading1 tbcellCss"><b>SNo</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Date & Time</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Role & Name</b></td>			
<td width="30%" class="labelheading1 tbcellCss"><b>Remarks</b></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Action</b></td>
<td width="10%" class="labelheading1 tbcellCss"><b>Amount</b></td>		
</tr>

<logic:iterate id="resultsEnh" name="preauthDetailsForm" property="lstEnhancementworkFlow" indexId="index1" >
<tr>
<td class="tbcellBorder"><%=index1+1%></td>
<td class="tbcellBorder"><bean:write name="resultsEnh" property="auditDate" /></td>
<td class="tbcellBorder"><bean:write name="resultsEnh" property="auditRole" /><bean:write name="resultsEnh" property="auditName" />
<c:if test="${viewAuditNames eq 'Y'}">
( <bean:write name="resultsEnh" property="actby" />)
</c:if>
</td>
<td class="tbcellBorder"><bean:write name="resultsEnh" property="auditRemarks" /></td>
<td class="tbcellBorder"><bean:write name="resultsEnh" property="auditAction" /></td>
<td class="tbcellBorder"><bean:write name="resultsEnh" property="auditAmount" /></td>
</tr>
</logic:iterate>
</table>
</logic:greaterThan>
</logic:present>






<c:if test="${ viewType eq 'ctd' ||viewType eq 'ch' ||viewType eq 'enhMedco' || enhancementFlag eq 'Y' }" >
<table width="100%" border="0">
<c:if test="${ viewType eq 'enhMedco'  }" >
<tr><td colspan="2" align="right">
<!-- <button class="but"   type="button" name="enhAmtgen" id="enhAmtgen" value="enhAmtgen" onclick="javascript:fn_enableEnhAmt()">Generate Amount</button> -->
</td></tr>
</c:if>

<tr>
<td colspan="2">
<div id="enhancementAmtDiv" style="display:none" >
<table width="100%">
<tr><td class="labelheading1 tbcellCss" width="50%">Total Enhancement Amount  Rs.</td>
<td class="labelheading1" width="50%">
<bean:write property="enhAmt" name="preauthDetailsForm"></bean:write></td>
</tr>
<c:if test="${viewType == 'ctd'  || viewType =='ch'  || viewType =='enhCeo'  || viewType =='enhEo' || viewType =='cpd' }" >
<tr><td class="labelheading1 tbcellCss" width="50%">Enhancement Approved Amount  Rs. <font color="red">*</font></td>
<td class="labelheading1" width="50%"><html:text property="enhApprvAmt" name="preauthDetailsForm" styleId="enhApprvAmt" onchange="javacsript:fn_testEnhApprAmt(this.value)" maxlength="10" title="Please enter enhancement approved amount"></html:text></td>
</tr>

</c:if>
</table></div>
</td>
</tr>

<tr>
<td colspan="2">
<div id="enhRemarksBlock" style="display:none">
<table width="100%">
<tr><td  colspan="2" class="labelheading1 tbcellCss"> &nbsp; Remarks  :<font color="red">*</font> </td></tr>
<tr><td  colspan="2" class="tbcellBorder">
 &nbsp;<html:textarea name="preauthDetailsForm" property="enhRemarks" styleId="enhRemarks" style="width:98%;" 
 onchange="checkRemarks(this.value,3000,'enhRemarks')" onkeydown="check_maxLength('enhRemarks',3000)" title="Please enter enhancement remarks"></html:textarea>
</td></tr>
</table></div>
</td></tr>
</table>
</c:if>









<logic:empty name="preauthDetailsForm" property="enhApprvDt">
<table width="100%">
<tr><td>&nbsp;</td></tr>

<c:set var="enhButtons" value="${preauthDetailsForm.enhButtonsLst}"/>

<c:if test="${fn:length(enhButtons) > 0}">

	<tr id="buttonVisibleEnhId" >
	<td colspan="6" align="center">
	<div id="enhButtonBlock">
	<c:forEach var="EHFenhButton" items="${enhButtons}" varStatus="status" >
	<button class="but"   type="button"  value="${EHFenhButton.VALUE}" name="${EHFenhButton.ID}" id="${EHFenhButton.ID}"  onclick="javascript:fn_enhbuttonClicked('${EHFenhButton.ID}','${EHFenhButton.VALUE}');">${EHFenhButton.VALUE}</button>
	</c:forEach>

	</div>
	</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	</c:if>	
</table>
</logic:empty>

</div>

<html:hidden property="finalDig" value="0"/>

<html:hidden  name="preauthDetailsForm" property="splInvest"   />
<html:hidden  name="preauthDetailsForm" property="caseId"   />
<html:hidden  name="preauthDetailsForm" property="casSugeryId"   />
<html:hidden name="preauthDetailsForm" property="quesFlag" />
<html:hidden name="preauthDetailsForm" property="patientId" />
<html:hidden name="preauthDetailsForm" property="hospStay" />
<html:hidden name="preauthDetailsForm" property="imageology" />
<html:hidden name="preauthDetailsForm" property="labInvest" />
<%-- <html:hidden name="preauthDetailsForm" property="drugs" styleId="drugs" /> --%>
<html:hidden name="preauthDetailsForm" property="implants" />
<html:hidden name="preauthDetailsForm" property="enhancementDtls" />
<html:hidden name="preauthDetailsForm" property="admissionType" />

<html:hidden name="preauthDetailsForm" property="caseStatus" />
<html:hidden name="preauthDetailsForm" property="remEnhList" />
<html:hidden name="preauthDetailsForm" property="ipRegDate" />
<html:hidden name="preauthDetailsForm" property="enhancementFlag" />
<html:hidden name="preauthDetailsForm" property="enhApprvDt" />
<html:hidden name="preauthDetailsForm" property="drugDeletionString" />
<html:hidden name="preauthDetailsForm" property="comorBidVals" />
<html:hidden name="preauthDetailsForm" property="cochlearYN" />
<html:hidden name="preauthDetailsForm" property="schemeId" />
<html:hidden name="preauthDetailsForm" property="cochlearQuestionnaire" />
<html:hidden name="preauthDetailsForm" property="enhancementFlag"/>

<html:hidden name="preauthDetailsForm" property="hospitalId" />
<html:hidden name="preauthDetailsForm" property="drugs" styleId="drugs"/>
<c:if test="${viewType ne 'ptd' and viewType ne 'ceo' and viewType ne 'eo'}">
<html:hidden name="preauthDetailsForm" property="comorBidAmt" styleId="comorBidAmt" value="${comorbidAmt}"/>
<html:hidden name="preauthDetailsForm" property="preauthPckgAmt" value="${preauthPkgAmt}"/>
</c:if>
<c:if test="${viewType eq 'ptd' || viewType eq 'ceo' || viewType eq 'eo'}">
<html:hidden name="preauthDetailsForm" property="totPkgAmt" />
</c:if>
<html:hidden name="preauthDetailsForm" property="nabhFlg" />
<html:hidden name="preauthDetailsForm" property="hospStayAmt" />

<input type="hidden" name="SignPRFForm" id="SignPRFForm" value="${SignPRFForm}" />
<input type="hidden" name="medCardClearence" id="medCardClearence" value="${medCardClearence}" />
<input type="hidden" name="bloodTransfusionAttach" id="bloodTransfusionAttach" value="${bloodTransfusionAttach}" />
<script>
	//Added to remove S8 speciality -Paediatric surgery and M4 - Paediatrics if the patient age is above 14 yrs
	if('${patientAge}'>=14)
		{	
			var selectSpeciality=document.getElementById('category');
			for (var ss=0;ss<selectSpeciality.length;ss++) {
				if (selectSpeciality.options[ss].value=='M4') {
					selectSpeciality.remove(ss);
				}
			}
			for (var ss=0;ss<selectSpeciality.length;ss++) {
					if (selectSpeciality.options[ss].value=='S8') {
					selectSpeciality.remove(ss);
				}
			}
			for (var ss=0;ss<selectSpeciality.length;ss++) {
				if (selectSpeciality.options[ss].value=='S16') {
					selectSpeciality.remove(ss);
				}
			}
		}	
		
		//Disable Save button if any category in the list is inactive during Preauth initiation
		/*var currStatus='<bean:write name="preauthDetailsForm" property="caseStatus" />';
		if('${viewType}'=='medco' && currStatus=='CD2')
		{
			for(var i=0; i<checkInactiveSurgArray.length;i++)
			{
				var arr= checkInactiveSurgArray[i].split('~');
				if(arr[2]!=null && arr[2]=='N')
				{	
					document.getElementById('CD71').disabled=true;
					alert(arr[1]+' is inactivated for this hospital. So Preauthorisation cannot be initiated for this category.');
				}
			}
		}*/
</script>
<c:if test="${ResultMsg != null}">
<script>
	       
	/*alert('${ResultMsg}');
	var msg='${ResultMsg}';
  var successFlag='${successFlag}';
  var autoAction = parent.parent.autoActionFlag;
  var module=parent.parent.module;
  var ceoFlag='Y';

if(msg!=null && msg!='' && (msg=='Saved Successfully' || msg=='Preauthorization details saved successfully'))
{
	parent.parent.fn_refresh();
     printPrf();
}
if(successFlag!=null && successFlag!='' && successFlag=='Y' && msg!='Preauthorization details saved successfully')
{	
	if(autoAction!=null && autoAction=="OnloadCaseOpen")
	{
		if(module=="preauth"){
			parent.parent.fn_refresh();
		}
		else if(module=="preauthJournal")
		{
			parent.parent.fn_refresh();
			}
	}

	else if(ceoFlag=='Y')
	{
		
parent.parent.fn_refresh();

	}

	else{
		parent.parent.fn_refresh();
		fn_maxmizeTop();
		}
	
}*/
function printPrf()
{
	var caseId= '<bean:write name="preauthDetailsForm" property="caseId" />';
	var patientId= '<bean:write name="preauthDetailsForm" property="patientId" />';
	var url="/<%=context%>/preauthDetails.do?actionFlag=preauthDetailsEhf&printFlag=Y&caseId="+caseId+"&patientId="+patientId;
	childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=980, height=800, top=100,left=50');
}   

    </script>
    </c:if></div>
</html:form>
</body>



</html></fmt:bundle>