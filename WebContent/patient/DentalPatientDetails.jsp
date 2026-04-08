<!DOCTYPE html >
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<script src="js/jquery-1.9.1.min.js"></script>
<script  src="js/DentalPatientscripts.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="bootstrap/js/custombox.js"></script>
<script src="bootstrap/js/legacy.js"></script>
<script src="js/select2.min.js"></script>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">

<%@ include file="/common/includeCalendar.jsp"%>  
<style type="text/css">

@keyframes blinker {  
  50% { opacity: 0.0; }
}
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
.heightChange{
height:140px !important;
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

.bgColorCss {
	-webkit-appearance: none;
	background-color: #FFF;
	border: 1px solid #cacece;
	width: 20px; 
  	height: 20px; 
	padding: 9px;
	border-radius: 3px;
	display: inline-block;
	position: relative;
}
.bgColorCss:active, .bgColorCss:checked:active {
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}

.bgColorCss:checked {
	background-color: teal;
	border: 1px solid #adb8c0;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05), inset 15px 10px -12px rgba(255,255,255,0.1);
	color: #99a1a7;
}
.bgColorCss:checked:after {
	content: '\2714';
	font-size: 14px;
	position: absolute;
	top: 0px;
	left: 3px;
	color: #FFF;
}

.redBackground{
    background-color: red;  
    color:green;
}

body {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 12px;
line-height: 1.42857143;
color: #333;
background-color: #fff;
}

</style>
<script>
$("input[type='checkbox']").change(function(){
    if($(this).is(":checked")){
        $(this).addClass("redBackground"); 
    }else{
        $(this).removeClass("redBackground");  
    }
});
</script>
<script>
$( document ).ready(function(){
	document.getElementById("Submit").disabled=true;
	if(document.getElementById("dtrsTd").style.display==""){
		document.getElementById("Submit").disabled=false;
	}
});
var dentalFlg='${dentalFlg}';
var medOrSur='';
var genInventList=new Array();
var consultDataList=new Array();
var spec=new Array();
var genInvestRemove='';
var specRemove='';
var catCount=0;
var updateGenInvestLst=new Array();
var genOldList=new Array();
var procList=new Array();
var drugCount=0;var genInventCount=0;
var updateGenInvestCount=0;
var genInvestAttachId=0;var updGenInvestAttachId=0;
var drugs=new Array();var nonComboProcIds=null;
var existDrugsArr=new Array();
var symptomCount=0;var therapyAttachId=0;
var symptoms=new Array();var dentalOldLst = new Array();
function fn_showSpec(){
	
	if(document.forms[0].softTissue[0].checked==true)
		document.getElementById("gingivaSpec").style.display="";
	else if(document.forms[0].softTissue[0].checked==false)
		document.getElementById("gingivaSpec").style.display="none";
	if(document.forms[0].softTissue[1].checked==true)
		document.getElementById("buccalMucosaSpec").style.display="";
	else if(document.forms[0].softTissue[1].checked==false)
		document.getElementById("buccalMucosaSpec").style.display="none";
	if(document.forms[0].softTissue[2].checked==true)
		document.getElementById("frenalSpec").style.display="";
	else if(document.forms[0].softTissue[2].checked==false)
		document.getElementById("frenalSpec").style.display="none";
	if(document.forms[0].softTissue[3].checked==true)
		document.getElementById("tongueSpec").style.display="";
	else if(document.forms[0].softTissue[3].checked==false)
		document.getElementById("tongueSpec").style.display="none";
	if(document.forms[0].softTissue[4].checked==true)
		document.getElementById("mouthFloorSpec").style.display="";
	else if(document.forms[0].softTissue[4].checked==false)
		document.getElementById("mouthFloorSpec").style.display="none";
	if(document.forms[0].softTissue[5].checked==true)
		document.getElementById("softPalateSpec").style.display="";
	else if(document.forms[0].softTissue[5].checked==false)
		document.getElementById("softPalateSpec").style.display="none";
	if(document.forms[0].softTissue[6].checked==true)
		document.getElementById("hardPalateSpec").style.display="";
	else if(document.forms[0].softTissue[6].checked==false)
		document.getElementById("hardPalateSpec").style.display="none";
	if(document.getElementById("tmjCheck").checked==true)
		document.getElementById("tmj").style.display="";
	else if(document.getElementById("tmjCheck").checked==false)
		document.getElementById("tmj").style.display="none";
	if(document.getElementById("faceCheck").checked==true)
		document.getElementById("face").style.display="";
	else  if(document.getElementById("faceCheck").checked==false)
		document.getElementById("face").style.display="none";
	if(document.getElementById("jawsCheck").checked==true)
		document.getElementById("jaws").style.display="";
	else  if(document.getElementById("jawsCheck").checked==false)
		{
			document.getElementById("jaws").value="";	
			document.getElementById("jaws").style.display="none";
			document.getElementById('mandibleFracture').style.display="none";
			document.getElementById('maxillaFracture').style.display="none";
			document.getElementById('zygomaticFracture').style.display="none";
			document.getElementById('showJawsText').style.display="none";
		}	
	if(document.getElementById("lymphadenopathyCheck").checked==true)
		document.getElementById("lymphadenopathy").style.display="";
	else  if(document.getElementById("lymphadenopathyCheck").checked==false)
		document.getElementById("lymphadenopathy").style.display="none";
	    document.getElementById('lymphapathyType').style.display="none";
	    document.getElementById('lymphapathyText').style.display="none";
}
function fn_showMediacalText(id)
{
	var idvalue=document.getElementById(id).value;
	
	var checkid=document.getElementById(id);
 
	if(idvalue=="CH117" && document.getElementById('medicalDtlsid11').checked)
		{
		 document.getElementById("showMedicalText").style.display="";
		 }
		else if(document.getElementById('medicalDtlsid11').checked)
			{
			document.getElementById("showMedicalText").style.display="";
			}
		else
			{
			document.getElementById("showMedicalText").style.display="none";
			document.getElementById("showMedicalTextval").value="";
			}
	}
	
function fn_refreshJaws()
{
	var category=document.getElementById('jaws').value;	
	
	if(category != null && category == "normal")
	{
		document.getElementById('mandibleFracture').style.display="";
		document.getElementById('maxillaFracture').style.display="none";
		document.getElementById('zygomaticFracture').style.display="none";
		document.getElementById('showJawsText').style.display="none";
	}
	else if(category != null && category == "subkluxation")
	{
		document.getElementById('maxillaFracture').style.display="";
		document.getElementById('mandibleFracture').style.display="none";
		document.getElementById('zygomaticFracture').style.display="none";
		document.getElementById('showJawsText').style.display="none";
	}
	else if(category != null && category == "Zygomatic")
	{
		document.getElementById('zygomaticFracture').style.display="";
		document.getElementById('mandibleFracture').style.display="none";
		document.getElementById('maxillaFracture').style.display="none";
		document.getElementById('showJawsText').style.display="none";
	}
	else if(category != null && category == "Others")
	{
		document.getElementById('showJawsText').style.display="";
		document.getElementById('mandibleFracture').style.display="none";
		document.getElementById('maxillaFracture').style.display="none";
		document.getElementById('zygomaticFracture').style.display="none";
	}
	else
		{
			document.getElementById('mandibleFracture').style.display="none";
			document.getElementById('maxillaFracture').style.display="none";
			document.getElementById('zygomaticFracture').style.display="none";
			document.getElementById('showJawsText').style.display="none";
		}
}
function fn_refreshOcclusion()
{
	var occType =document.getElementById('occlusion').value;
	var mainCompId =occType;
	if(occType != null && occType =="CH81")
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
	    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	    }
	  
	    xmlhttp.onreadystatechange=function()
	    {
	        if(xmlhttp.readyState==4)
	        {
	        	if(occType != null && occType =="CH81")
	        		{
	        		document.getElementById('occlusionTypeDiv').style.display="";
	    			document.getElementById('occlusionType').value="";
	    			document.getElementById('occlusionOtherDiv').style.display="none";
	        		}
	        		
	        		
	        	
	            var resultArray=xmlhttp.responseText;
	            if(resultArray.trim()=="SessionExpired*")
	            {
	            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	             else
	             {
			            	if(resultArray!=null)
			            	{
			            		resultArray = resultArray.replace("[","");
			            		resultArray = resultArray.replace("]*","");            
			            		var dentalList = resultArray.split(","); 
			            		if(dentalList.length>0)
			            		{  
			            			if(occType =="CH81")
			            			{
			            				
				            			for(var i = 0; i<dentalList.length;i++)
				            			{	
				            				var arr=dentalList[i].split("~");
				            				if(arr[1]!=null && arr[0]!=null)
				            				{
				            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
				            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
				            					document.forms[0].occlusionType.options[i] =new Option(val1,val2);
				            				}
				            			}
			            			}
			            		}
			            	}
                   }
	       }
	    }

	    url = "./patientDetails.do?actionFlag=getDentalCaseSheetList&callType=Ajax&mainCompId="+mainCompId;
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
			
		}
	else if(occType != null && occType == "CH83")
		{
			document.getElementById('occlusionTypeDiv').style.display="none";
			document.getElementById('occlusionOtherDiv').style.display="";
		}
	else
		{
			document.getElementById('occlusionTypeDiv').style.display="none";
			document.getElementById('occlusionOtherDiv').style.display="none";
		}
	
}
function fn_refreshPermanentDent()
{
	var premDentition=document.getElementById("permanentDent").length; 
	var mainCompId="";
	for (var x=0;x<premDentition;x++)
	{
		var mainCompId = document.forms[0].permanentDent[x].value;
		if (document.forms[0].permanentDent[x].selected)
		{
			
				
				if(mainCompId == "CH96")
				{
					document.getElementById('cariesDiv').style.display="";
				}
				if(mainCompId == "CH92")
				{
					document.getElementById('rootDiv').style.display="";
				}
				if(mainCompId == "CH93")
				{
					document.getElementById('mobilityDiv').style.display="";
				}
				if(mainCompId == "CH94")
				{
					document.getElementById('attritionDiv').style.display="";
				}
				if(mainCompId == "CH95")
				{
					document.getElementById('missingDiv').style.display="";
				}
				if(mainCompId == "CH91")
					{
					document.getElementById('otherDiv').style.display="";
					//document.getElementById('otherPermntDent').value="";
					}
				
				
		}
		else
			{
				if(mainCompId == "CH96")
				{
					document.getElementById('cariesDiv').style.display="none";
					//document.getElementById('caries').value="";
				}
				if(mainCompId == "CH92")
				{
					document.getElementById('rootDiv').style.display="none";
					//document.getElementById('rootDiv').value="";
				}
				if(mainCompId == "CH93")
				{
					document.getElementById('mobilityDiv').style.display="none";
					//document.getElementById('mobilityDiv').value="";
				}
				if(mainCompId == "CH94")
				{
					document.getElementById('attritionDiv').style.display="none";
					//document.getElementById('attritionDiv').value="";
				}
				if(mainCompId == "CH95")
				{
					document.getElementById('missingDiv').style.display="none";
					//document.getElementById('missingDiv').value="";
				}
				if(mainCompId == "CH91")
				{
				document.getElementById('otherDiv').style.display="none";
				//document.getElementById('otherPermntDent').value = "";
				}
				
			}
		
	}
	

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
	                            	    if(hospGovu!=null && hospGovu!="G")
	                                	
	    		                    		phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"' checked='checked'/>"+""+val1;
	    		                    	else
	    		                    		phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetailsNims(this)' title='"+val1+"' checked='checked'/>"+""+val1;
	    		                    			
		                    	}
                            
                                
		                    	else
		                    		{

                            	    //if(hospId!='EHS34')
                            	    if(hospGovu!=null && hospGovu!="G")
                                	
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
           			var kal='${PatientOpList.docType}';
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
	//var hospGovu = document.getElementById("hospGovu").value;
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
                    		document.forms[0].ipDoctorName.options[0]=new Option("--select--","-1");
                        } 
            		}
          	 		if('${PatientOpList.doctorDtls}'!=null && '${PatientOpList.doctorDtls}'!='')
          	 			{
          	 			document.forms[0].ipDoctorName.value='${PatientOpList.doctorDtls}';
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
function fn_refreshOtherPermDent()
{
	
	var otherPerm = document.getElementById('otherPermntDent').value;
	if(otherPerm != null && (otherPerm =="CH102" || otherPerm == "CH103" || otherPerm == "CH104" || otherPerm == "CH105"))
		{
			document.getElementById('otherPermTextDiv').style.display="";
			document.getElementById('otherPermText').value="";
		}
	else
		{
			document.getElementById('otherPermTextDiv').style.display="none";
		}

}

function fn_refreshDecdDent(id)
{
	var decidusDentition=document.getElementById("deciduousDent").length; 
	
	
	var mainCompId="";

	for (var x=0;x<decidusDentition;x++)
	{
		var mainCompId = document.forms[0].deciduousDent[x].value;
		if (document.forms[0].deciduousDent[x].selected)
			{
				
				if(mainCompId == "CH87")
				{
					document.getElementById('cariesDecdious').style.display="";
				}
				if(mainCompId == "CH88")
				{
					document.getElementById('grosslyDecdious').style.display="";
				}
				if(mainCompId == "CH89")
				{
					document.getElementById('mobileDecdious').style.display="";
				}
				if(mainCompId == "CH90")
				{
					document.getElementById('missingDecdious').style.display="";
				}
		}
		else
			{
				
				if(mainCompId == "CH87")
				{
					document.getElementById('cariesDecdious').style.display="none";
				}
				if(mainCompId == "CH88")
				{
					document.getElementById('grosslyDecdious').style.display="none";
				}
				if(mainCompId == "CH89")
				{
					document.getElementById('mobileDecdious').style.display="none";
				}
				if(mainCompId == "CH90")
				{
					document.getElementById('missingDecdious').style.display="none";
				}
				
				
			}
	}
	
	
}


function fn_refreshRegnLymphathy()
{
	var category=document.getElementById('lymphadenopathy').value;	
	
	if(category != null && (category == "normal" || category =="subkluxation" || category =="normalsubkluxation" ))
	{
		document.getElementById('lymphapathyType').style.display="";
		document.getElementById('lymphapathyText').style.display="none";
		
	}
	else if (category != null && category == "dislocation")
	{
		document.getElementById('lymphapathyText').style.display="";
		document.getElementById('lymphapathyType').style.display="none";
	}
	else
	{
		document.getElementById('lymphapathyType').style.display="none";
		document.getElementById('lymphapathyText').style.display="none";
	}
	
}

function getSubListDental(id,index,type)
{
    var extraoral=document.getElementById(id).value;
	var mainCompId=extraoral;
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
		        	var extraoral=document.getElementById(id).value;
		        	
		        	if(document.getElementById(id).checked==true)
		        		document.getElementById("dntsublist"+index).style.display="";
		        	else
		        		{
		        		document.getElementById("dntsublist"+index).style.display="none";
		        	    document.getElementById("dntsublist"+index).value="";
		        	    if(index=="1")
		        	    	{
		        	    document.getElementById("dntsublistjaws1").style.display="none";
		        		document.getElementById("dntsublistjaws1").value="";
						document.getElementById("dntsublistjawstxt1").style.display="none";
		           	    document.getElementById("dntsublistjawstxt1").value="";
		        	    	}
		        	    if(index=="0")
		        	    	{
						document.getElementById("dntsublistrl0").style.display="none";
		        		document.getElementById("dntsublistrl0").value="";
						document.getElementById("dntsublistrltxt0").style.display="none";
		        	    document.getElementById("dntsublistrltxt0").value="";
		        	    	}
		        	    }
		        		
		        		
		        	
		            var resultArray=xmlhttp.responseText;
		            if(resultArray.trim()=="SessionExpired*")
		            {
		            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
		            }
		             else
		             {
				            	if(resultArray!=null)
				            	{
				            		resultArray = resultArray.replace("[","");
				            		resultArray = resultArray.replace("]*","");            
				            		var dentalList = resultArray.split(","); 
				            		if(dentalList.length>0)
				            		{  
				            			if(extraoral=="CH4")
				            			{
				            				document.forms[0].subdental0.options[0] =new Option("--Select--","NULL1");
					            			for(var i = 0; i<dentalList.length;i++)
					            			{	
					            				var arr=dentalList[i].split("~");
					            				if(arr[1]!=null && arr[0]!=null)
					            				{
					            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
					            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					            					document.forms[0].subdental0.options[i+1] =new Option(val1,val2);
					            				}
					            			}
				            			}
				            			if(extraoral=="CH3")
				            				{ 
				            				document.forms[0].subdental1.options[0] =new Option("--Select--","NULL2");
					            			for(var i = 0; i<dentalList.length;i++)
					            			{	
					            				var arr=dentalList[i].split("~");
					            				if(arr[1]!=null && arr[0]!=null)
					            				{
					            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
					            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					            					document.forms[0].subdental1.options[i+1] =new Option(val1,val2);
					            				}
					            			}
				            				
				            				}
				            			if(extraoral=="CH2")
				            				{
				            				document.forms[0].subdental2.options[0] =new Option("--Select--","NULL3");
					            			for(var i = 0; i<dentalList.length;i++)
					            			{	
					            				var arr=dentalList[i].split("~");
					            				if(arr[1]!=null && arr[0]!=null)
					            				{
					            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
					            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					            					document.forms[0].subdental2.options[i+1] =new Option(val1,val2);
					            				}
					            			}
				            				
				            				}
				            			if(extraoral=="CH1")
				            				{
				            				document.forms[0].subdental3.options[0] =new Option("--Select--","NULL4");
					            			for(var i = 0; i<dentalList.length;i++)
					            			{	
					            				var arr=dentalList[i].split("~");
					            				if(arr[1]!=null && arr[0]!=null)
					            				{
					            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
					            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					            					document.forms[0].subdental3.options[i+1] =new Option(val1,val2);
					            				}
					            			}
				            				}
				            			
				            		}
				            	}
	                   }
		       }
		    }

		    url = "./patientDetails.do?actionFlag=getDentalCaseSheetList&callType=Ajax&mainCompId="+mainCompId+"&type="+type;
		    xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
		
}

function getSubListDentalsub(id,index,type)
{
	var subdentid=document.getElementById(id).value;
	var mainCompId=subdentid;
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
        	var extraoral=document.getElementById(id).value;
        	if(subdentid=="CH47" || subdentid=="CH48" || subdentid=="CH52" || subdentid=="CH68"|| subdentid=="CH69" || subdentid=="CH97" ||subdentid=="CH53" || subdentid=="CH70"|| subdentid=="CH49" || subdentid=="CH50"||subdentid=="CH51" || subdentid=="CH71")
        		{
        	if(subdentid=="CH53" || subdentid=="CH48" || subdentid=="CH52")
        		{
        		document.getElementById("dntsublistjaws1").style.display="";
        	    document.getElementById("dntsublistjawstxt1").style.display="none";
        		}
            else if(subdentid=="CH70"|| subdentid=="CH69" || subdentid=="CH97")
            	{
        		document.getElementById("dntsublistrl0").style.display="";
        		document.getElementById("dntsublistrltxt0").style.display="none";
            	}
            else if(subdentid=="CH47" || subdentid=="CH49" || subdentid=="CH50")
            	{
           		document.getElementById("dntsublistjawstxt1").style.display="";
        	    document.getElementById("dntsublistjaws1").style.display="none";
            	}
            else if(subdentid=="CH68")
            	{
        		document.getElementById("dntsublistrltxt0").style.display="";
        		document.getElementById("dntsublistrl0").style.display="none";
            	}
           else if(subdentid=="CH51")
        		{
        	    document.getElementById("dntsublistjaws1").style.display="none";
        		document.getElementById("dntsublistjaws1").value=""; 
        		document.getElementById("dntsublistjawstxt1").style.display="none";
           	    document.getElementById("dntsublistjawstxt1").value="";
        		}
           else if(subdentid=="CH71")
        	   {
                document.getElementById("dntsublistrl0").style.display="none";
        		document.getElementById("dntsublistrl0").value="";
        		document.getElementById("dntsublistrltxt0").style.display="none";
        	    document.getElementById("dntsublistrltxt0").value="";
        		}
        	}
        	else if(subdentid=="NULL1"){
        	    document.getElementById("dntsublistrl0").style.display="none";
        		document.getElementById("dntsublistrl0").value="";
        		document.getElementById("dntsublistrltxt0").style.display="none";
        	    document.getElementById("dntsublistrltxt0").value="";        		
        	}
        	else if(subdentid=="NULL2"){
        		document.getElementById("dntsublistjaws1").style.display="none";
         		document.getElementById("dntsublistjaws1").value=""; 
         		document.getElementById("dntsublistjawstxt1").style.display="none";
            	document.getElementById("dntsublistjawstxt1").value="";
        	}
        	
        	        	
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*")
            {
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
             else
             {
		            	if(resultArray!=null)
		            	{
		            		
		            		resultArray = resultArray.replace("[","");
		            		resultArray = resultArray.replace("]*","");            
		            		var dentalList = resultArray.split(","); 
		            		if(dentalList.length>0)
		            		{  
		            			if(subdentid=="CH53" || subdentid=="CH48" || subdentid=="CH52" )
		            			{
		            				
		            				document.forms[0].subdentaljaws1.options.length=0;
			            			for(var i = 0; i<dentalList.length;i++)
			            			{	
			            				var arr=dentalList[i].split("~");
			            				if(arr[1]!=null && arr[0]!=null)
			            				{
			            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			            					document.forms[0].subdentaljaws1.options[i] =new Option(val1,val2);
			            				}
			            			}
		            			}
		            			
		            			if(subdentid=="CH70" || subdentid=="CH69" || subdentid=="CH97")
		            				{
		            				
			            			for(var i = 0; i<dentalList.length;i++)
			            			{	
			            				var arr=dentalList[i].split("~");
			            				if(arr[1]!=null && arr[0]!=null)
			            				{
			            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			            					document.forms[0].subdentalrl0.options[i] =new Option(val1,val2);
			            				}
			            			}
		            				}
		            			
		            		}
		            	}
               }
       }
    }

    url = "./patientDetails.do?actionFlag=getDentalCaseSheetList&callType=Ajax&mainCompId="+mainCompId+"&type="+type;
    xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
	
}

function redirecttodental(input)
{
	
	var dentalredirect=input;
	var patientId=document.getElementById("patientNo").value;
    var caseId=document.getElementById("caseId").value;
	if(input=="no")
	 {
	bootbox.confirm("Do you wish to redirect  to the normal case sheet ?", function(result) {
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
function showintraoraltext(id,index)
{
	if(document.getElementById(id).checked==true)
		{
		document.getElementById("dntsublistoraltd"+index).style.display="";
		}
	else
		{
		document.getElementById("dntsublistoraltd"+index).style.display="none";
		document.getElementById("dntsublistoral"+index).value="";
		}
		
}
function fn_validatenulltext(id,index)
{
	if(document.getElementById(id).checked==true)
		{
		if(document.getElementById("dntsublistoral"+index).value=="" || document.getElementById("dntsublistoral"+index).value==null)
			alert("please write the Hard Palate remarks in the text box");
		}
		
}

function showHardtissueexman(id)
{
	var decidusDentition=document.getElementById("deciduousDent").length; 
	var mainCompId="";

	for (var x=0;x<decidusDentition;x++)
	{
		if (document.forms[0].deciduousDent[x].selected)
		{
			
				mainCompId = document.forms[0].deciduousDent[x].value;
				if(mainCompId == "0")
				{
					document.getElementById('cariesDecdious').style.display="";
				}
				if(mainCompId == "1")
				{
					document.getElementById('grosslyDecdious').style.display="";
				}
				if(mainCompId == "2")
				{
					document.getElementById('mobileDecdious').style.display="";
				}
				if(mainCompId == "3")
				{
					document.getElementById('missingDecdious').style.display="";
				}
				
				
				
		}
		else
			{
				mainCompId = document.forms[0].deciduousDent[x].value;
				if(mainCompId == "0")
				{
					document.getElementById('cariesDecdious').style.display="none";
				}
				if(mainCompId == "1")
				{
					document.getElementById('grosslyDecdious').style.display="none";
				}
				if(mainCompId == "2")
				{
					document.getElementById('mobileDecdious').style.display="none";
				}
				if(mainCompId == "3")
				{
					document.getElementById('missingDecdious').style.display="none";
				}
				
				
			}
	}
}
function showSwellingPusTab(id){
	if(document.getElementById("Swelling").checked==true){
		document.getElementById("swCompTable").style.display="";
	}
	else{
		document.getElementById("swCompTable").style.display="none";
	}
	if(document.getElementById("PusFlashDischarge").checked==true){
		document.getElementById("psCompTable").style.display="";
	}
	else{
		document.getElementById("psCompTable").style.display="none";
	}
}
</script>
<style>
.checkclass{style="width:22px;"}
</style>
</head>
<fmt:bundle basename="Registration">
<body onload="fn_loadProcessImage();fn_ipop();">
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
<form action="/patientDetails.do" method="post" name="myForm" enctype="multipart/form-data">
<br>
<table width="95%" style="margin:0 auto">
<tr><td colspan="4">
<table class="tbheader">
<tr><td align="left"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.PatientRegistration"/></b></td></tr>
</table>
<br>
<table width="100%">
<tr><td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HealthCardNo"/></b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td>
<td  width="15%" class="labelheading1 tbcellCss" ><b><fmt:message key="EHF.PatientNo"/></b></td><td id="patNo" width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>
<logic:equal  name="patientForm" property="telephonicReg" value="Yes">
<td width="15%" class="labelheading1 tbcellCss"><b>Telephonic ID</b></td>
<td width="15%" class="tbcellBorder"><a href="javascript:viewTelephonicInfo('<bean:write name="patientForm" property="telephonicId" />')"><bean:write name="patientForm" property="telephonicId" /></a></td></b>
</logic:equal>
<logic:notEqual  name="patientForm" property="telephonicReg" value="Yes">
<td width="30%">&nbsp;</td>
</logic:notEqual>
</tr>
</table>
<br>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>
<table width="100%">
<tr>
<td width="27%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commonDtlsField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td></tr> --%> 
<tr><td class="labelheading1 tbcellCss" width="40%"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="dateOfBirth"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="years"/>Y
 		<bean:write name="patientForm" property="months"/>M
		<bean:write name="patientForm" property="days"/>D</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b></td><td class="tbcellBorder"><b>&nbsp;<c:if test="${patientForm.relation eq 'New Born Baby'}"  ><b></c:if><bean:write name="patientForm" property="relation"/></b></td></tr>
<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="caste"/></b></td></tr> --%>
 <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="slab"/></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="occupation"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="contactno"/></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="29%" valign="top">
<fieldset style="height:20em;" >
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
  <div style="height:18em;overflow:hidden" id='cardAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="29%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="comm_hno" /></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_street" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_state" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_dist" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="15%" valign="top">
<fieldset style="height:20em;" id='photoField'>
 <legend class="legendStyle"><b><fmt:message key="EHF.Photo"/></b></legend>
<table width="80%" height="80%" style="margin:auto auto">
<tr><td align="center">
 <logic:notEmpty name="patientForm" property="photoUrl">
<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')"/>
</logic:notEmpty>
<logic:empty name="patientForm" property="photoUrl">
<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
</logic:empty>
</td></tr></table>
</fieldset>
</td></tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.CaseDetails"/></b></td></tr>
</table>
<br>
<table width="100%">
<tr>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.RegisteredHospital"/> </b></td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="hospitalName"/></b></td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateAndTime"/> </b> </td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="dtTime"/></b></td>
<c:if test="${(dentalrntdbvalue ne 'yes')}">
<c:if test="${(dentalFlg eq 'Y')}">
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.TreatmentPlanned"/></b><font color="red" class="onlyAp">*</font></td><td width="35%" class="tbcellBorder">
<b>
<html:radio name="patientForm" styleId="treatmentDntl" property="treatmentDntl" value="yes" />yes
<html:radio name="patientForm" styleId="treatmentDntl" property="treatmentDntl" value="no"  onclick="javascript:redirecttodental(this.value);"/>no
</b></td>
</c:if>
</c:if>
</tr>
</table>
<logic:notEmpty name="groupList"> 
<logic:iterate name="groupList" id="labelbean">
<logic:equal name="labelbean" property="ID" value="GP2">
<%-- <logic:equal name="UserRole" value="CD9"> --%>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.MedicalDetails"/></b></td></tr>
</table>
<br>
<table width="100%" class="medicalDetailsTable" border="0">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ComplaintType"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ComplaintCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientComplaint"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientComplaintCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">
<html:select name="patientForm" property="complaints" styleId="complaints" style="WIDTH:17em;;height:5em" title="Select Complaint Type" multiple="multiple" onchange="javascript:getComplaintType('notOnLoad');" onmouseover="addTooltip('complaints')" >
<!--<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>-->
<html:options property="ID" collection="mainComplaintList" labelProperty="VALUE"/>
</html:select></td>
<td  valign="top" class="tbcellBorder"><html:textarea name="patientForm"  property="complaintCode" styleId="complaintCode" style="WIDTH:18em;height:5em"  title="Complaint Code"  onkeydown="validateBackSpace(event)" readonly="true" onchange="compcodeonchange()"/></td>	
<td valign="top" class="tbcellBorder"><html:select name="patientForm" property="patientComplaint" styleId="patientComplaint" style="WIDTH:17em;height:5em" title="Select Patient Complaint" multiple="multiple" onchange="getPatComplaintCode()" onmouseover="addTooltip('patientComplaint')">
    </html:select>
 </td>
 <td  valign="top" class="tbcellBorder"><html:textarea name="patientForm"  property="patComplaintCode" styleId="patComplaintCode" style="WIDTH:18em;height:5em" title="Patient Complaint Code" onkeydown="validateBackSpace(event)" readonly="true" /></td>	
</tr>
<c:if test="${schemeId eq 'CD202' && hospType eq 'G'}">
<tr>
<td class="labelheading1 tbcellCss">Other Complaints</td>
<td colspan="3" class="tbcellBorder" style="height: 70px;"><html:textarea name="patientForm" property="otherComplaint" styleId="otherComplaint" style="width:99%;heigtht:100%;" title="Please enter Other Complaints if Any" styleClass="otherFields" onchange="javascript:check_maxLength('otherComplaint','400');fn_enableHistory();" ></html:textarea></td>
</tr>
</c:if>
<tr>
<td colspan="2" class="tbcellBorder" rowspan="3">
 <fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.PersonalHistory"/></b> <font color="red" class="onlyAp">*</font></legend>
 		<table width="100%" >
	 <logic:iterate id="phistory" name="personalHistoryList">
	 <tr><td width="33%">
 		 <html:multibox name="patientForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
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
</td>
<td colspan="2" valign="top"   class="tbcellBorder">
	<c:if test="${(dentalFlg eq 'Y')}">
		<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.ExaminationFindings"/></b> <font color="red" class="onlyAp">*</font></legend>
		<table width="100%">
		<logic:iterate id="examinFnds" name="examinFndgsList">
		 <tr id="<bean:write name="examinFnds" property='ID'/>ROW">
			<c:if test="${(examinFnds.ID ne 'GE14') ||(examinFnds.ID eq 'GE14' && schemeId eq 'CD201') }">
		 <td width="60%">&nbsp;&nbsp;
		 
 		 <html:multibox name="patientForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       		<bean:write name="examinFnds" property="VALUE"/></td>
       			</c:if>	
       					<c:if test="${examinFnds.ID eq 'GE14'}">
		 <td width="60%">&nbsp;&nbsp;
		
 		 <html:multibox name="patientForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       		BP mm/Hg</td>
       			</c:if>	
       		<td id="<bean:write name="examinFnds" property='ID'/>" width="39%"></td>
       		<td width="1%">
       		<input type="hidden" name="<bean:write name='examinFnds' property='VALUE'/>" id="<bean:write name='examinFnds' property='ID'/>h" value="<bean:write name='examinFnds' property='LVL'/>"/></td>
       		</tr>
       		</logic:iterate>
		</table>
		</fieldset>
	</c:if>
 </td>
</tr>
<tr><td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HistoryOfPresentIllness"/></b> <font color="red" class="onlyAp">*</font></td></tr>
<tr><td valign="top" colspan="2"  class="tbcellBorder">
<html:textarea name="patientForm" property="presentHistory" styleId="presentHistory"  style="WIDTH:49.4em;height:4em" disabled="true" title="Enter History Of Present Illness" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpaces(this,'History Of Present Illness')" />
</td>
<tr><td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DrugHistory"/></b><font color="red" class="onlyAp">*</font></td></tr>
<tr><td valign="top" colspan="2"  class="tbcellBorder">
<html:radio  styleId="drughistoryid" name="patientForm"  value="Yes" property="drughistoryid" onclick="javascript:enableDrugHistory(this.value);"/> Yes
<html:radio styleId="drughistoryid"  name="patientForm"   value="No" property="drughistoryid" onclick="javascript:enableDrugHistory(this.value);"/> No
<html:textarea name="patientForm" rows="2" cols="70"  styleId="drughistory" property="drughistory"  style="display:none"  title="Enter Drug History" onkeydown="maxLengthPress(this,3000,event)" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpaces(this,'Drug History')" />
</td>
</tr>

<tr>
<td colspan="4" class="labelheading1 tbcellCss"><b>Medical History</b><font color="red" class="onlyAp">*</font></td></tr>
<tr><td valign="top" colspan="2" class="tbcellBorder">
<input type="radio" name="medicalhistoryid" value="Yes" onclick="javascript:enableMedicalHistory(this.value);" id="medicalhistoryid"> Yes
<input type="radio" name="medicalhistoryid" value="No" onclick="javascript:enableMedicalHistory(this.value);" id="medicalhistoryid"> No
<table style="width:100%; display:none;" id="medicalhistorytable">
<logic:iterate id="medicalDtls" name="patientForm" property="medicalhsitorydtls" indexId="i">
	 <tr><td style="width:20%;">&nbsp;&nbsp;
 		 <html:checkbox name="patientForm" value="${medicalDtls.ID}" property="medicalDtlsid" styleId="medicalDtlsid${i}" onclick="javascript:fn_showMediacalText(this.id);" title="Medical History" >
      		 <bean:write name="medicalDtls" property="VALUE"/>
      		 
       	</html:checkbox>
       	</td>
       	</tr>
       	</logic:iterate>
       	<tr id="showMedicalText" style="display:none;" ><td width="50%"><input id="showMedicalTextval" style="width:100%" type="text" name="showMedicalTextval"></input></td></tr>
        </table></td></tr>
        
       
       
       
<!-- <tr>
 <td colspan="4" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b>Medical History</b> </legend>
 		<table width="100%">
 		 <tr><td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Hypertension</td>
			<td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Rheumatic Fever</td></tr>
 		   <tr><td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Prosthetic Heart Valve</td>
 		    <td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Pace Maker</td></tr>
 		     <tr><td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Stroke</td>
 		      <td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Asthma</td></tr>
 		       <tr><td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Diabetes</td>
 		        <td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Thyroid Disease</td></tr>
 		        <tr>
 		        <td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Blood Dyscrasias</td>
 		       	<td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Epilepsy</td>
 		        </tr>
 		        <tr><td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory"> Kidney Dialaysis</td>
 		        <td width="50%">&nbsp;</td></tr>
 		        <tr>
 		        <td width="50%"> <input type="checkbox" name="pastHistory" id="pastHistory" onclick="javascript:fn_showMediacalText();"> Others</td>
 		       	<td width="50%"><input type="text" id="showMedicalText" style="display:none;"></input></td>
 		        </tr>
 		        
 		 
</table>
</fieldset>
</td> -->

<!-- <td colspan="2" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.PastHistory"/></b> </legend>
 		<table width="100%">
	 <logic:iterate id="psthistory" name="pastHistoryList" indexId="cnt">
	 <tr><td width="50%">&nbsp;&nbsp;
 		 <html:multibox name="patientForm"  property="pastHistory" styleId="pastHistory" onclick="getOtherText(this)" title="History Of Past Illness">
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
 		 <html:multibox name="patientForm"  property="familyHistory" styleId="familyHistory" onclick="getOtherText(this)">
      		 <bean:write name="fhistory" property="ID"/>
       	</html:multibox>
       		<bean:write name="fhistory" property="VALUE"/></td>
       		<td id="<bean:write name="fhistory" property='ID'/>" width="30%"></td>
       		</tr>
       </logic:iterate>
       </table>
    </fieldset>
</td>
</tr>-->
<!-- <tr>
<td colspan="4" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b>Dental Habits</b> </legend>
 		<table width="100%">
 		 <tr><td width="50%"> <input type="radio" name="childCaries" id="childCaries" value="P" onclick="fn_showHabs();"> Para Functional</td>
			<td width="50%"> <input type="radio" name="childCaries" id="childCaries" value="D" onclick="fn_showHabs();"> Deleterious</td></tr>
 		   
 		   <tr ><td style="width:50%">
 		   <table width="100%" id="dentalHabDiv1" style="display:none;">
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Finger/Thumb Sucking </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Nail/Lip Biting </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Tongue biting/Thrusting </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Mouth Breathing </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Teeth clenching / Bruxism </td></tr>
 		   </table>
 		   </td>
 		   <td style="width:50%" >
 		   <table width="100%" id="dentalHabDiv2" style="display:none;">
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Pan Chewing </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Smoking </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Gutka </td></tr>
 		   <tr><td><input type="checkbox" value="Gingiva" id="dentalHabits" >  Alcohol </td></tr>
 		   </table>
 		   </td>
 		   </tr>
 		 
</table>
</fieldset>
</td>
</tr> -->

<tr><td colspan="4"><b><fmt:message key="EHF.SystemicExamFnds"/></b></td></tr>


    <logic:equal value="CD202" name="patientForm" property="scheme">
     <logic:equal value="G" name="patientForm" property="hosptype">
     <tr>
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="O" title="Click Here to Add other Symptoms" styleId="otherSymptoms" onchange="fn_enableOtherSymptoms();" name="patientForm" property="otherSymptoms" ><b>&nbsp;Other Symptoms</b></html:checkbox>
        </td></tr>
        </logic:equal>
        </logic:equal>
 
 <tr id="otherSym" style="display:none;">
 <td class="labelheading1 tbcellCss">Symptom Name</td>
 <td colspan="2"><html:text style="width:100%" name="patientForm" property="otherSymptomName" styleId="otherSymptomName" styleClass="otherFields"  onchange="javascript:check_maxLength('otherSymptomName','200')"></html:text></td>
 </tr>  
 
      
<tr>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.MainSymptomName"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.MainSymptomCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SubSymptomName"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SubSymptomCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td class="tbcellBorder mainSymptoms"><html:select name="patientForm" property="mainSymptomName" title="Select Main Symptom Name" styleId="mainSymptomName" style="WIDTH: 17em;" onchange="getsubSymptomLst()" onmouseover="addTooltip('mainSymptomName')">
           <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
           <html:options property="ID" collection="mainSymptonLst" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder mainSymptoms"><html:text name="patientForm"  property="mainSymptomCode" styleId="mainSymptomCode" title="Main Symptom Code" style="WIDTH: 17em;" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder mainSymptoms"><html:select name="patientForm" property="subSymptomName" styleId="subSymptomName" title="Select Sub-Symptom Name" style="WIDTH: 17em;" onmouseover="addTooltip('subSymptomName')" onchange="getSymptomLst();">
           <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder mainSymptoms"><html:text name="patientForm"  property="subSymptomCode" styleId="subSymptomCode" title="subSymptom Code" style="WIDTH: 17em;" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SymptomName"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SymptomCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td class="tbcellBorder mainSymptoms"><html:select name="patientForm" property="symptomName" styleId="symptomName" title="Select Symptom Name" style="WIDTH: 17em;" onmouseover="addTooltip('symptomName')" onchange="getSymptomCode();">
           <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder mainSymptoms"><html:text name="patientForm"  property="symptomCode" styleId="symptomCode" title="Symptom Code" style="WIDTH: 17em;" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td colspan="2"><button  class="btn btn-success" type="button" name="addSymptom" id="addSymptom" value="Add Symptoms" onclick="addSymptoms()">Add Symptom&nbsp;<i class="fa fa-plus"></i></button></td>
</tr>
<tr><td colspan="6" class="tbcellBorder ">
  <table  width="100%"  id="symptomsTable" style="display:none" border="1">
      <tr>  
      	<td width="5%"><fmt:message key="EHF.SNO"/></td>        
        <td width="15%"><fmt:message key="EHF.SymptomCode"/></td>   
       	<td width="15%"><fmt:message key="EHF.SymptomName"/></td>
        <td width="15%"><fmt:message key="EHF.SubSymptomCode"/></td>
        <td width="15%"><fmt:message key="EHF.SubSymptomName"/></td>
        <td width="10%"><fmt:message key="EHF.MainSymptomCode"/></td>
        <td width="10%"><fmt:message key="EHF.MainSymptomName"/></td>
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="symList">
        <bean:size  id="symSize" name="patientForm" property="symList"/>
        <logic:greaterThan value="0" name="symSize">
		<%int i = 1;%>
        <logic:iterate id="symlst" name="patientForm" property="symList" indexId="sno" >
        <tr>  
      	<td width="5%"><%=i++ %></td>        
        <td width="15%"><bean:write name="symlst" property="LEVELID" /></td>   
       	<td width="15%"><bean:write name="symlst" property="VALUE" /></td>
        <td width="15%"><bean:write name="symlst" property="DSGNID" /></td>
        <td width="15%"><bean:write name="symlst" property="SUBID" /></td>
        <td width="10%"><bean:write name="symlst" property="UNITID" /></td>
        <td width="10%"><bean:write name="symlst" property="ID" /></td>
        <td width="5%"><button class="btn btn-warning" type="button" value="Delete" style="padding: 3px 5px;" name=<bean:write name='symlst' property='LEVELID' /> id=<bean:write name='symlst' property='LEVELID' /> onclick="javascript:confirmRemoveRow(this,'symptom');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
        </table>
</td></tr>
</table>
</td></tr>
<tr>
<td id="empPastHistory" style="display:none;">
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click Here to View Past History" style="color:#fff; display:block;">
       			<span id="empPastHstry" style="font-size: 1.25em;"><b><i  style="color:#ff0000" class="fa fa-user-plus"></i>&nbsp;&nbsp;Patient Past History&nbsp;&nbsp;<i style="color:#ff0000" class="fa fa-user-plus"></i></b></span>
        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
      
      <table width="95%" style="margin:auto;">
     <logic:notEmpty name="patientForm" property="lstCaseSearch" >
       <tr>
											<th class="tbheader1" width="12%">Case ID</th>
											<th class="tbheader1" width="14%">PatientName</th>
											<th class="tbheader1" width="25%">Hospital Name</th>
											<th class="tbheader1" width="10%">Case Type</th>
											<th class="tbheader1" width="15%">Case Status</th>
											<th class="tbheader1" width="10%">Registered Date</th>		
										<!--	<th class="tbheader1" width="3%">Preauth Amount</th>
											<th class="tbheader1" width="2%">Claim Amount</th>	   -->				
											
										</tr>
										<logic:iterate  name="patientForm" property="lstCaseSearch" id="data" >
												<tr>
													<td align="center" width="12%" class="tbcellBorder" style="word-wrap: break-word;">
														<b><font style="color:#862010"><a  id="pastHstryView"  data-toggle="modal" data-target="#pastHistoryModal" href="#" onclick="javascript:getDiagnosisDetails('<bean:write name="data" property="CASESTATUSID"/>','<bean:write  name="data" property="CATID"/>','<bean:write  name="data" property="HOSPNAME"/>','<bean:write  name="data" property="SUBMITTEDDATE"/>','<bean:write  name="data" property="PATIENTID"/>','<bean:write  name="data" property="CASENO"/>','<bean:write  name="data" property="PATIPOP"/>')"><bean:write  name="data" property="CASENO"/></a></font></b>
													</td>
													<td align="center" width="14%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="PATNAME"/>
													</td>
													<td align="center" width="18%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="HOSPNAME"/>
													</td>
													<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
														<b><font style="color:#B32900"><bean:write  name="data" property="PATIPOP"/></font></</b>
													</td>
													<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="CASESTATUS"/>
													</td>
													<td align="center" width="20%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="SUBMITTEDDATE"/>
													</td>
												<!--	<td align="center" width="3%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="PREAUTHAPPAMT"/>
													</td>
													<td align="center" width="2%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="CLAIMAMT"/>
													</td>  -->
												</tr>
										</logic:iterate>
										</logic:notEmpty>
      <logic:empty name="patientForm" property="lstCaseSearch" >
      <tr>
      <td align="center" style="color:#434645">
      <i class="fa fa-user-times"></i>&nbsp;<b>No Past History Found</b>
      </td>
      </tr>
      </logic:empty>
      </table>
      </div>
      </div>
    </div>
  </div>
</td></tr>
   
<!-- New Dental Op Block -->
<tr id="dopBlock" >
<td colspan="4">
<table width="100%">
<tr><td colspan="4" class="labelheading1 tbcellCss"><b>EXTRA ORAL EXAMINATIONS</b></td></tr>
<tr><td><table style="width:100%;">
<tr>
<logic:iterate id="extraoralID" name="patientForm" property="extraoraldtls" indexId="i">
	 <tr><td style="width:20%;">&nbsp;&nbsp;
 		 <html:checkbox name="patientForm" value="${extraoralID.ID}" property="extraoral" styleId="extraoral${i}" onclick="javascript:getSubListDental(this.id,${i},'mainlst${i}');" title="History Of Past Illness" >
      		  <label id="extraoralLabel${i}"><bean:write name="extraoralID" property="VALUE"/></label>
       	</html:checkbox>
       		</td>
       		 <td id="dntsublist${i}" style="display:none;"  class="tbcellBorder">
       		    <html:select  name="patientForm" property="subdental${i}"  onchange="javascript:getSubListDentalsub(this.id,${i},'sublst${i}');" styleId="subdental${i}"    style="WIDTH: 15em;border:0;">
                <html:options property="ID" collection="Dentalmainlist${i}" labelProperty="VALUE"/>
                </html:select></td>
               
                 <td id="dntsublistjaws${i}" style="display:none;"  class="tbcellBorder">
				            <html:select name="patientForm" property="subdentaljaws${i}"  styleId="subdentaljaws${i}"    style="WIDTH: 15em;border:0;">
				            <html:options property="ID" collection="Dentalsublistjaws1" labelProperty="VALUE"/>
				            </html:select></td> 
				            
				            <td id="dntsublistrl${i}" style="display:none;"   class="tbcellBorder">
				            <html:select name="patientForm" multiple="multiple" property="subdentalrl${i}"  styleId="subdentalrl${i}"    style="WIDTH: 15em;border:0;">
				            <html:options property="ID" collection="Dentalsublistrl0" labelProperty="VALUE"/>
				            
				            <td id="dntsublistrltxt${i}" style="display:none;"   class="tbcellBorder"><html:text styleClass="form-control" name="patientForm" styleId="subdentalrltxt${i}" property="subdentalrltxt"></html:text>
       						  </td>
       						  <td id="dntsublistjawstxt${i}" style="display:none;"   class="tbcellBorder"><html:text styleClass="form-control" name="patientForm" styleId="subdentaljawstxt${i}" property="subdentaljawstxt"></html:text>
							  </td>
							  
				              </html:select></td>
       		 </tr>
       		</logic:iterate>
       		<%--  <td id="<bean:write name="extraoralID" property='ID'/>"></td>  --%>
       		 
				              </tr>
	</table></td></tr>     
            
      

<tr><td colspan="4" class="labelheading1 tbcellCss"><b>INTRA ORAL EXAMINATIONS</b></td></tr>
<tr><td class="labelheading1 tbcellCss" colspan="4"><b>Soft Tissue Examination</b><font color="red" class="onlyAp">*</font></td></tr>
<tr><td valign="top" colspan="2" class="tbcellBorder">
<input type="radio" name="softtissueyesorno" value="Yes" onclick="javascript:enableSoftTissueExamin(this.value);" id="softtissueyesorno"> Yes
<input type="radio" name="softtissueyesorno" value="NAD" onclick="javascript:enableSoftTissueExamin(this.value);" id="softtissueyesorno"> NAD
</td></tr>
<tr><td><table width="100%" id="softtissueexaminblock" style="display:none">
<logic:iterate id="intraoralID" name="patientForm" property="intraoraldtls" indexId="i">
  <tr><td width="50%">&nbsp;&nbsp;
 		 <html:checkbox name="patientForm" value="${intraoralID.ID}" property="intraoral" styleId="intraoral${i}" onclick="javascript:showintraoraltext(this.id,${i});"  title="History Of Past Illness" >
      		 <label id="intraoralLabel${i}" ><bean:write name="intraoralID" property="VALUE"/></label>
       	</html:checkbox>
       	</td>	
       		<%-- <td id="<bean:write name="intraoralID" property='ID'>" width="50%"></td>--%>
       		<td width="50%"id="dntsublistoraltd${i}" style="display:none"  class="tbcellBorder" ><html:text style="width:100%" name="patientForm" styleId="dntsublistoral${i}" property="dntsublistoral${i}"></html:text></td>
		</tr>
      </logic:iterate>

<tr>
<td width="50%">&nbsp;&nbsp;&nbsp;<input type="checkbox" value="Swelling" id="Swelling" onclick="javascript:showSwellingPusTab(this.id);"><label>Swelling</label>&nbsp;<font color="red" class="onlyAp">(Mandatory for IP treatment)</font></td></tr>
<tr>
<td>
<table width="100%" class="" border="0" id="swCompTable" style="display:none">
<tr>
<th class="labelheading1 tbcellCss" id=""><b>Site</b></th>
<th class="labelheading1 tbcellCss" id=""><b>Size</b></th>
<th class="labelheading1 tbcellCss" id=""><b>Extension</b></th>
<th class="labelheading1 tbcellCss" id=""><b>Colour</b></th>
<th class="labelheading1 tbcellCss" id=""><b>Consistency</b></th>
<th class="labelheading1 tbcellCss" id=""><b>Tenderness</b></th>  
<th class="labelheading1 tbcellCss" id=""><b>Borders</b></th>
</tr>
<tr>
<td class="tbcellBorder" id=""><input type="text" id="swSite" name="swSite"/></td>
<td class="tbcellBorder" id=""><input type="text" id="swSize" name="swSize"/></td>
<td class="tbcellBorder" id=""><input type="text" id="swExtension" name="swExtension"/></td>
<td class="tbcellBorder" id=""><input type="text" id="swColour" name="swColour"/></td>
<td class="tbcellBorder" id="">
<select id="swConsistency" name="swConsistency">
<option value="">Select</option>
<option value="Soft">Soft</option>
<option value="Firm">Firm</option>
<option value="Hard">Hard</option>
</select>
</td>
<td class="tbcellBorder" id="">
<select id="swTenderness" name="swTenderness">
<option value="">Select</option>
<option value="Yes">Yes</option>
<option value="No">No</option>
</select>
</td>  
<td class="tbcellBorder" id="">
<select id="swBorders" name="swBorders">
<option value="">Select</option>
<option value="Well defined">Well defined</option>
<option value="Diffused">Diffused</option>
</select>
</td>
</tr>
</table>
</td>
</tr>
<tr><td width="50%">&nbsp;&nbsp;&nbsp;<input type="checkbox" value="Pus/Discharge" id="PusFlashDischarge" onclick="javascript:showSwellingPusTab(this.id);"><label>Pus/Flash Discharge</label>&nbsp;<font color="red" class="onlyAp">(Mandatory for IP treatment)</font></td></tr>
<tr>
<td>
<table width="100%" class="" border="0" id="psCompTable" style="display:none">
<tr>
<th class="labelheading1 tbcellCss" id=""><b>Site</b></th>
<th class="labelheading1 tbcellCss" id=""><b>Discharge</b></th>
</tr>
<tr>
<td class="tbcellBorder" id=""><input type="text" id="psSite" name="psSite"/></td>
<td class="tbcellBorder" id=""><input type="text" id="psDischarge" name="psDischarge"/></td>
</tr>
</table>
<td>
</tr>
</table>
</td>
</tr>

</table></td></tr>

<%-- <tr><td  class="labelheading1 tbcellCss" colspan="4"><b>  Hard Tissue Examination</b></td></tr>
<tr><td><table width="100%">
<tr><td class="labelheading1 tbcellCss" colspan="4"><b>Deciduous Dentition</b></td></tr>


<tr><td width="50%">&nbsp;&nbsp;
 		 <html:select  name="patientForm"  property="deciduousDent" styleId="deciduousDent"  styleClass="form-control" onchange="javascript:fn_refreshDecdDent();" multiple="multiple" title="History Of Past Illness" >
      		<html:options property="ID" collection="deciduousdentdtls" labelProperty="VALUE"/>
      		</html:select>
       	</td>	
       	</tr>
 </table>
</td>
</tr> --%>

<%-- <tr><td><table width="100%">
<tr><td class="labelheading1 tbcellCss" colspan="4"><b>Permanent Dentition</b></td></tr>

<tr><td width="50%">&nbsp;&nbsp;
 		 <html:select  name="patientForm"  property="permanentDent" styleId="permanentDent" styleClass="form-control" onchange="javascript:fn_refreshPermanentDent();" multiple="multiple" title="History Of Past Illness" >
      		<html:options property="ID" collection="permanentdentdtls" labelProperty="VALUE"/>
      		 </html:select>
       		
       	</td>	
       		
		</tr>
 </table>
</td>
</tr>
--%>
<table width="100%">
<tr><th  class="labelheading1 tbcellCss" colspan="4"><b>  Hard Tissue Examination</b><font color="red" class="onlyAp">*</font></th></tr>
<tr><td valign="top" colspan="2" class="tbcellBorder">
<input type="checkbox" name="decidiousdentsel" value="DEC" onclick="javascript:permDeciddentsel();" id="decidiousdentsel"> Deciduous Dentition
<input type="checkbox" name="permanentdentsel" value="PER" onclick="javascript:permDeciddentsel();" id="permanentdentsel"> Permanent Dentition
</td></tr></table>
<tr><td colspan="4" >
<table width="100%" id="decidiousBlock" style="display:none;">
<%-- <c:if test="${(patientForm.years le 14 and patientForm.months eq 0 and patientForm.days eq 0) or patientForm.years lt 14}"> --%>
<tr><td class="labelheading1 tbcellCss" colspan="2" ><b>Deciduous Dentition</b>
<html:select multiple="multiple" name="patientForm"  styleId="deciduousDent" property="deciduousDent" styleClass="form-control" onchange="javascript:fn_refreshDecdDent(this.id);">
<label id="DeciduousDentition"><html:options property="ID" collection="deciduousdentdtls" labelProperty="VALUE"/></label>
</html:select>
</td>
</tr>
<%-- </c:if> --%>
 
<tr id="cariesDecdious" style="display:none;"><td colspan="2" class="labelheading1 tbcellCss">Caries</td>
<td colspan="4" align="center" class="tbcellBorder">
<div style="width:60%;">
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

<tr id="missingDecdious" style="display:none;"><td colspan="2" class="labelheading1 tbcellCss">Missing</td>
<td colspan="4" align="center" class="tbcellBorder">
<div style="width:60%;">
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

<tr id="grosslyDecdious" style="display:none;"><td colspan="2" class="labelheading1 tbcellCss">Grossly Decayed</td>
<td colspan="4" align="center" class="tbcellBorder">
<div style="width:60%;">
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

<tr id="mobileDecdious" style="display:none;"><td colspan="2" class="labelheading1 tbcellCss">Mobile</td>
<td colspan="4" align="center" class="tbcellBorder">
<div style="width:60%;">
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


 </table>

<table width="100%" id="permanentBlock" style="display:none;">
<%-- <c:if test="${patientForm.years ge 14 and ((patientForm.months ge 0 and patientForm.days gt 0) or (patientForm.months gt 0 and patientForm.days ge 0))}"> --%>
<tr><td class="labelheading1 tbcellCss" colspan="2" ><b>Permanent Dentition</b><html:select multiple="multiple" name="patientForm"  styleId="permanentDent" property="permanentDent" styleClass="form-control heightChange" onchange="javascript:fn_refreshPermanentDent();">
<label id="PermanentDentition"><html:options property="ID" collection="permanentdentdtls" labelProperty="VALUE"/></label>
</html:select>
</td>
</tr>
<%-- </c:if> --%>
<tr id="cariesDiv" style="display:none;"><td colspan="2" class="labelheading1 tbcellCss">Caries</td>
<td colspan="4" align="center" class="tbcellBorder"><div style="width:90%;">
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


<tr id="rootDiv" style="display:none"><td colspan="2" class="labelheading1 tbcellCss">Root stump / Grossly Decayed </td>
<td colspan="4" align="center" class="tbcellBorder"><div style="width:90%;">
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


<tr id="mobilityDiv" style="display:none"><td colspan="2" class="labelheading1 tbcellCss">Mobility</td>
<td colspan="4" align="center" class="tbcellBorder"><div style="width:90%;">
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


<tr id="attritionDiv" style="display:none"><td colspan="2" class="labelheading1 tbcellCss">Attrition / Abrasion </td>
<td colspan="4" align="center" class="tbcellBorder"><div style="width:90%;">
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

<tr id="missingDiv" style="display:none"><td colspan="2" class="labelheading1 tbcellCss">Missing</td>
<td colspan="4" align="center" class="tbcellBorder"><div style="width:90%;">
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

<tr id="otherDiv" style="display:none">
<td class="labelheading1 tbcellCss" colspan="2" >Others<html:select name="patientForm"  styleId="otherPermntDent" property="otherPermntDent" styleClass="form-control" onchange="javascript:fn_refreshOtherPermDent();">
<html:option value="-1">---------Select-----------</html:option>
<html:options property="ID" collection="permanentdentdtlsothr" labelProperty="VALUE"/>
</html:select>
</td>

<td id="otherPermTextDiv" class="labelheading1 tbcellCss" colspan="2" style="display:none;">Remarks:
<html:text styleClass="form-control" name="patientForm" styleId="otherPermText" property="otherPermText" onchange="javascript:fn_permDentOthersValidation(this.value)"></html:text>
</td>
</tr>
</table>
<table width="100%">
<tr><td colspan="4" class="labelheading1 tbcellCss">Previous Dental Treatment
<html:textarea styleClass="form-control" name="patientForm" styleId="previousDentalTreatment" property="previousDentalTreatment"></html:textarea></td></tr>
</table>
<table width="50%">
<tr>
<td class="labelheading1 tbcellCss" colspan="2" width="50%" > Occlusion<font color="red" class="onlyAp">*</font>
<html:select name="patientForm"  styleId="occlusion" property="occlusion" styleClass="form-control" onchange="javascript:fn_refreshOcclusion();">
<html:option value="">--Select--</html:option>
<html:options property="ID" collection="occlusiondtls" labelProperty="VALUE"/>
</html:select>
</td>

<td id="occlusionTypeDiv" class="labelheading1 tbcellCss" colspan="2" style="display:none;" width="50%"> Type:
<html:select name="patientForm"  styleId="occlusionType" property="occlusionType" styleClass="form-control">
<html:options property="ID" collection="Dentalsublist" labelProperty="VALUE"/>
</html:select>
</td>

<td id="occlusionOtherDiv" class="labelheading1 tbcellCss" colspan="2" style="display:none;" width="50%">Remarks:
<html:text styleClass="form-control" name="patientForm" styleId="occlusionOther" property="occlusionOther" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpaces(this,'Occlusion remarks')"></html:text>
</td>
</tr>
</table>
<table width="100%">
<tr><td  class="labelheading1 tbcellCss" colspan="4">Clinical Probing Depth (in mm.)<span class="blink_me" style="animation: blinker 3s linear infinite; color: red;">&nbsp;&nbsp;(Mandatory for PeriodontalSurgeries)</span></td></tr>
<tr><td colspan="4" class="tbcellBorder" align="center">
<table border=1>
<tr><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth0" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth6" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth11" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth1" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth7" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth12" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth2" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth8" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth13" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth3" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth9" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth14" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth4" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth10" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth15" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth5" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
<tr><td>8</td><td>7</td><td>6</td><td>5</td><td>4</td><td>3</td><td>2</td><td>1</td><td>1</td><td>2</td>
<td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td></tr>
<tr><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth16" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth22" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth27" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth17" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth23" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth28" name="probeDepth" onchange=javascript:fn_checkprobingdepth(this.id,this.value);></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth18" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth24" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth29" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth19" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth25" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth30" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth20" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth26" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;" maxlength=1 id="probeDepth31" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
<td><input type="text" style="width:20px;" maxlength=1 id="probeDepth21" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
</table>

</td></tr>
<tr><td colspan="4" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.GenInvestigations"/></b> <font color="red" class="onlyAp">*&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4" class="tbcellBorder"><table width="100%" align="center">
<tr>
<td class="labelheading1 tbcellCss" width="25%" id="InvBlockName"><b>Investigation Block Name</b><font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss" width="25%" id="InvName"><b>Investigation Name</b><font color="red" class="onlyAp">*</font></td>
    <logic:equal value="CD202" name="patientForm" property="scheme">
     <logic:equal value="G" name="patientForm" property="hosptype">
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="O"  title="Click Here to Add other Investigations" styleId="invOthers" onchange="fn_enableOthers();" name="patientForm" property="investOthers" ><b>&nbsp;Other Investigations</b></html:checkbox>
        </td>
        </logic:equal>
        </logic:equal>
<td width="25%">&nbsp;</td></tr>
<tr>
<td class="tbcellBorder" id="InvBlckLst">
<html:select name="patientForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" onchange="getGenInvestigation();">
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
          <html:options property="ID" collection="investigationsList" labelProperty="VALUE"/>
    </html:select></td>
    <td class="tbcellBorder" id="invLst"><html:select name="patientForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 17em;"  onchange="javascript:getInvestPrice();" onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
    </tr>
    <tr>
     <td class="labelheading1 tbcellCss" width="25%" Id="otherInvNameHead" style="display:none"><b>Other Investigation Name</b></td>
     </tr>
     <tr>
     <td class="tbCellBorder"><html:text name="patientForm" property="otherInvName" styleId="otherInvName" style="display:none;width:97%" styleClass="otherFields" onchange="javascript:check_maxLength('otherInvName','200')"></html:text></td>
  </tr>
  <tr><td><br></td></tr>
 <tr>
<td colspan="4" style="align:center"><button class="btn btn-success" type="button" name="add" value="Add Tests" onclick="confirmDentalPatientTypeReset()">Add Tests&nbsp;<i class="fa fa-plus"></i></button></td>
</tr></table></td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTableID" style="display:none" border="1">
   <tr><td colspan="4" class="labelheading1 tbcellCss">Previously Added Investigation</td></tr>
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr>
        <logic:present name="patientForm" property="genInvList">
        <bean:size  id="genInvSize" name="patientForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
		<%int j = 1;%>
		<script>var rowNo=2;</script>
        <logic:iterate id="gnInvlst" name="patientForm" property="genInvList" indexId="sno" >
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
       	<td width="20%"><button class="btn btn-warning" type="button" value="Delete" name=<bean:write name="gnInvlst" property="ID" /> id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:confirmRemoveGenInvest(this,'geninvestigations','<bean:write name="gnInvlst" property="ID" />');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
   </table>
</td></tr>
<tr><td colspan="4" align="center">
<c:choose>
<c:when test="${schemeId eq 'CD202' && hospType eq 'G' }">
<button class="btn btn-success"  type="button" id="saveDTRS" onclick="javascript:fn_saveDetailsWithoutMandate('SaveDTRS');">Save and Generate DTRS&nbsp;<i class="fa fa-print"></i></button>
&nbsp;&nbsp;&nbsp;</c:when>
<c:otherwise>
<button class="btn btn-success"  type="button" id="saveDTRS" onclick="javascript:fn_savePatientDetails('SaveDTRS')">Save and Generate DTRS&nbsp;<i class="fa fa-print"></i></button>
</c:otherwise>
</c:choose>

<a id="dtrsTd" href="javascript:generateDTRSPrint('<bean:write name="patientForm" property="caseId" />','<bean:write name="patientForm" property="hospitalId" />')" style="display:none"><b>DTRS Print Form</b></a>
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
<tr><td colspan="4" class="labelheading1 tbcellCss">Diagnosis</td> </tr>
<tr><td colspan="4" class="tbcellBorder"><html:textarea name="patientForm" styleId="diagnosis" property="diagnosis" styleClass="form-control"></html:textarea></td></tr>

<tr><td colspan="4" class="labelheading1 tbcellCss">Treatment Plan</td> </tr>
<tr><td colspan="4" class="tbcellBorder">
 <html:radio name="patientForm" styleId="patientType" property="patientType" value="OP" onchange="enableDentalIPOP();"/> OP
 <html:radio styleId="patientType" name="patientForm"  property="patientType" value="IP" onchange="enableDentalIPOP();"/> IP
 <html:radio styleId="patientType" name="patientForm"  property="patientType" value="DOP" onchange="enableDentalIPOP();"/> DOP (Dental OP)
</table>
</td></tr>
</table>
<tr><td>




<table width="95%" id="consultationDataNew" style="margin:auto;display:none;">
<tbody>
<tr>
<td class="tbheader" colspan="4">Consultation Details&nbsp;&nbsp;&nbsp;<i class="fa fa-medkit"></i></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss" style="width:10%"><b>S.No</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Diagnosed By</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit HOD Name</b></td>

</tr>

</tbody>

</table>

<br><br>

<logic:notEmpty name="patientForm" property="consultData">
<table width="95%" style="margin:auto;" id="consultationDataOld">
<tbody>
<tr>
<td class="tbheader" colspan="5">Previously Added Consultation Details&nbsp;&nbsp;&nbsp;<i class="fa fa-medkit"></i></td></tr>
<tr>
<td class="labelheading1 tbcellCss" style="width:10%"><b>S.No</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Diagnosed By</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit HOD Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Consultation Date</b></td>

</tr>



<c:set var="count" value="1"></c:set>
<logic:iterate id="consultData" name="patientForm" property="consultData">
<tr>
<td class="tbcellBorder" style="width:10%"><b>${count}</b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="unitName"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="diagnoisedBy"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="hodName"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="consultationTime"></bean:write></b></td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</logic:iterate>


</tbody>

</table>
</logic:notEmpty>
<tr><td style="display:none"  colspan="4">
<table style="width:95%;margin: 0 auto;display:none" id="diagnosisData">
<tr><td><b><font ><fmt:message key="EHF.Diagnosis"/>:</font></b></td></tr>
    <logic:equal value="CD202" name="patientForm" property="scheme">
     <logic:equal value="G" name="patientForm" property="hosptype">
    <tr>
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox  title="Click Here to Add Other Diagnosis" styleId="diagOthers" onchange="fn_enableOtherDiag();" name="patientForm" property="diagOthers" value="Y"><b>&nbsp;Other Diagnosis</b></html:checkbox>
        </td>
        <td class="labelheading1 tbcellCss" id="diagOthersHead" style="display:none"><b>Other Diagnosis Name</b></td>
        <td class="tbCellBorder" colspan="2" Id="diagOthersName" style="display:none;"><html:textarea  name="patientForm" property="diagOthersName"  style="width:100%" styleClass="otherFields" onchange="javascript:check_maxLength('otherDiagnosis','4000');"></html:textarea></td>
        </tr> 
        <tr class="existDiag">
<td class="tbcellCss" style="color:#A81C06">
<input type="radio"  name="diagCondition" value="c" Id="contains" onchange="javascript:getDiagListAuto();" checked="checked">&nbsp;&nbsp;<b>Contains</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio"  name="diagCondition" value="s" Id="startsWith" onchange="javascript:getDiagListAuto();">&nbsp;&nbsp;<b>Starts With</b>

</td>
<td  class="tbcellCss">
<html:text name="patientForm" property="diagnosisName" styleId="diagnosisName" onkeyup="javascript:getDiagListAuto();" style="width:100%"></html:text>
</td>
<td  class="labelheading1 tbcellCss existDrugs" id="diagAuto1" colspan="2" style="color:#A81C06">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Please Enter KeyWords to Filter Diagnosis List&nbsp;&nbsp;</font></b><i class="fa fa-list"></i>
</td>
        </tr>
        <tr class="existDiag">
                <td  class="labelheading1 tbcellCss existDrugs" width="10%"><b>	Disease Anatomical Name</b></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="disNameAuto" style="width:17em;" title="Select Diagnosis Name" onkeyup="javascript:getDiagListAuto();" onchange="javascript:getDiagDetailsAuto();" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>

<td  class="labelheading1 tbcellCss existDrugs" id="diagAuto2" colspan="2" style="color:#A81C06;display:none">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Select Diagnosis Name to Auto Populate Data&nbsp;&nbsp;</font></b><i class="fa fa-medkit"></i> 
</td>
        </tr>
        </logic:equal>
        </logic:equal>

<tr class="existDiag">
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagType"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatCode"/></b><font color="red" class="onlyAp">*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="diagType" styleId="diagType" style="WIDTH:17em" title="Select Diagnosis Type" onchange="getDiagnMainCatList('noOnload')" onmouseover="addTooltip('diagType')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            <html:options property="ID" collection="diagnTypeList" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diagCode" styleId="diagCode" title="Diagnosis Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="mainCatName" styleId="mainCatName" style="WIDTH:17em" title="Select Main Category Name" onchange="getDiagnCategoryList()" onmouseover="addTooltip('mainCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
      </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="mainCatCode" styleId="mainCatCode" title="Main Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr class="existDiag">
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="catName" styleId="catName" style="WIDTH:17em" title="Select Category Name" onchange="getDiagnSubCategoryList()" onmouseover="addTooltip('catName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="catCode" styleId="catCode" title="Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="subCatName" styleId="subCatName" style="WIDTH:17em" title="Select Sub Category Name" onchange="getDiagnDiseaseList()" onmouseover="addTooltip('subCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="subCatCode" styleId="subCatCode" title="Sub Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr class="existDiag">
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="diseaseName" styleId="diseaseName" style="WIDTH:17em" title="Select Disease Name" onchange="getDiagnDisAnatomicalList()" onmouseover="addTooltip('diseaseName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
 	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diseaseCode" styleId="diseaseCode" title="Disease Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="disAnatomicalName" styleId="disAnatomicalName" style="WIDTH:17em" title="Select Disease Anatomical Name" onmouseover="addTooltip('disAnatomicalName')" onchange="getDisAnatomicalCode()">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="disAnatomicalCode" styleId="disAnatomicalCode" title="Disease Anatomical Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
</table>
</td></tr>
<tr><td style="display:none"  colspan="4">
<table id="IPHead2" style="width:95%;margin: 0 auto;display:none">
<tr><td><b><font><fmt:message key="EHF.Therapy"/>:</font></b></td></tr>
<tr>
<td width="25%"  class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.CatName"/> </b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.CatCode"/> </b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCatCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="asriCatName" styleId="asriCatName" style="WIDTH: 17em" title="Select Category " onchange="getIcdCategoryCodes()" onmouseover="addTooltip('asriCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="AsricategoryList">
<html:options property="ID" collection="AsricategoryList" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="asriCatCode" styleId="asriCatCode" title="Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="ICDCatName" styleId="ICDCatName" style="WIDTH: 17em" title="Select ICD Category " onchange="javascript:getTherapyICDProcedureList(1);" onmouseover="addTooltip('ICDCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="ICDcategoryList">
<html:options property="ID" collection="ICDcategoryList" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="ICDCatCode" styleId="ICDCatCode" title="ICD Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr id="dentalSpecProc" style="display:none">
<td>
<html:radio styleId="SpecialProc" style="display:none" name="patientForm" property="dentalProc"  value="" onchange="ShowCeoAlert();getTherapyICDProcedureList(1);"><b>&nbsp;&nbsp;Special Procedures&nbsp;&nbsp;</b></html:radio>
</td>
<td>
<html:radio styleId="normalProc" style="display:none" name="patientForm" property="dentalProc"  value="" onchange="getTherapyICDProcedureList(1);" ><b>&nbsp;&nbsp;Normal Procedures&nbsp;&nbsp;</b></html:radio>
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDProc"/></b> <font color="red" class="onlyAp">*</font></td>
<%-- <td><b><fmt:message key="EHF.ProcCode"/></b> <font color="red">*</font></td> --%>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDProcCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss" id="unitsLabelTd" style="display:none"><b>Units</b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b>Treating Doctor</b> <font color="red" class="onlyAp">*</font></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="ICDProcName" styleId="ICDProcName" style="WIDTH:17em" title="Select ICD Procedure" onchange="getProcedureCodes();" onmouseover="addTooltip('ICDProcName')">                        
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="ICDprocedureList">
<html:options property="ID" collection="ICDprocedureList" labelProperty="VALUE"/>
</logic:notEmpty>           
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="ICDProcCode" styleId="ICDProcCode" title="ICD Procedure Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder" id="unitsTd" style="display:none">
<html:select name="patientForm" property="procUnits" styleId="procUnits" style="WIDTH:17em" title="Select No of Units" onchange="javascript:getDentalConditions('','','','unitsCond')"onmouseover="addTooltip('procUnits')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<%-- <html:options property="ID" collection="dentalUnitsList" labelProperty="VALUE"/> --%>
</html:select>
</td>
<td class="tbcellBorder"><html:select name="patientForm" property="docSpecReg" styleId="docSpecReg" style="WIDTH:17em" title="Select Treating Doctor" onmouseover="addTooltip('docSpecReg')" onchange="fn_getIPDoctorsDetails()">                        
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>     
</html:select></td>
</tr>
<tr><td colspan="4"><table width="100%">
<tr id="ipDoctorData" style="display:none">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font><br><html:text name="patientForm" property="ipOtherDocName" styleId="ipOtherDocName" maxlength="50" style="WIDTH:15em" onchange="checkAlphaSpace('ipOtherDocName','Doctor Name')" title="Enter Doctor Name"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Doctor.RegistrationNo"/></b><font color="red">*</font><br><html:text name="patientForm" property="ipDocRegNo" styleId="ipDocRegNo" maxlength="20" style="WIDTH:15em" onchange="validateAlphaNum('Doctor Registration No',this,'Registration No')" title="Enter Doctor Registration No"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.doctor.Qualification"/></b><font color="red">*</font><br><html:text name="patientForm" property="ipDocQual" styleId="ipDocQual" maxlength="30" style="WIDTH:15em" onchange="checkAlpha('ipDocQual','Doctor Qualification','Qualification')" title="Enter Doctor Qualification"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b><font color="red">*</font><br><html:text name="patientForm" property="ipDocMobileNo" styleId="ipDocMobileNo" maxlength="10" onchange="validateMobile(this);" style="WIDTH:15em" title="Enter Doctor Contact No"/></td>
</tr></table></td></tr>
<tr>
<td colspan="4" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Investigations"/></b>&nbsp;&nbsp;&nbsp;<font color="red">(Upload files with size less than 200KB)</font></td>
</tr>
<tr>
<td colspan="4">
<table width="100%">
<tr>
<td width="40%" class="tbcellBorder">
<html:select name="patientForm" property="investigations" styleId="investigations" style="WIDTH: 99%" multiple="multiple" onmouseover="addTooltip('investigations')">
</html:select>
</td>
<td width="20%"><input class="but" type="button" name="addSpeciality" id="addSpeciality" value="Add Speciality" onclick="addSpecialities()"></input></td>
<!-- <td width="20%"><input class="but" type="button" name="deleteSpeciality" id="deleteSpeciality" value="Delete Speciality" onclick="removeProcedure()"></input></td> -->
<td style="display:none" id="procSelectedTd" width="20%">
<select name="procSelected" id="procSelected" style="width:15em" onchange="confirmRemoveRow(this,'procSelected')"></select>
</td>
<td width="40%"  class="tbcellBorder" style="display:none">
      <html:select name="patientForm" property="investigationSel" styleId="investigationSel" style="WIDTH:99%" size="4" onmouseover="addTooltip('investigationSel')">
    	</html:select>
 </td>
</tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="categoryTableID" style="display:none" border="1">
  <tr><td colspan="8" class="labelheading1 tbcellCss">Previously Added Speciality</td></tr>
      <tr>  
      	<!-- <td width="5%" ><fmt:message key="EHF.SNO"/></td> -->      
        <td width="15%" ><b><fmt:message key="EHF.CatName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.ICDCatName"/></b></td>
        <td width="15%"><b><fmt:message key="EHF.ICDProc"/></b></td>
		<td width="5%"><b>Units</b></td>
        <td width="10%"><b>Treating Doctor</b></td>
		<td width="20%" ><b><fmt:message key="EHF.TestName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.Attachment"/></b></td>       	
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="investigationLt">
        <bean:size  id="investSize" name="patientForm" property="investigationLt"/>
        <logic:greaterThan value="0" name="investSize">
		<logic:iterate id="investLst" name="patientForm" property="investigationLt" >
        <tr>        	      
        <td width="15%"><bean:write name="investLst" property="asriCatName" /></td>   
       	<td width="15%"><bean:write name="investLst" property="catName" /></td>  
       	<td width="15%"><bean:write name="investLst" property="procName" /></td>
		<c:if test="${investLst.opProcUnits ne '-1' && investLst.opProcUnits ne null}">
			<td width="5%"><bean:write name="investLst" property="opProcUnits" /></td>
		</c:if>
		<c:if test="${investLst.opProcUnits eq '-1' || investLst.opProcUnits eq null}">
			<td width="5%">-NA-</td>
		</c:if>		
       	<td width="10%"><bean:write name="investLst" property="docName" /></td> 
       	<td width="20%"><bean:write name="investLst" property="filename" /></td>
		<c:if test="${investLst.name eq 'NA'}">
       	<td width="20%"><bean:write name="investLst" property="name" /></td>
       	</c:if>
       	<c:if test="${investLst.name eq 'View'}">
       	<td width="15%"><a href="#" onclick="javascript:fn_openAtachment('<bean:write name="investLst" property="filePath" />','<bean:write name="investLst" property="filePath" />');"><bean:write name="investLst" property="name" /></a></td>
       	</c:if>
       	<td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name="investLst" property="auditName" /> id=<bean:write name="investLst" property="auditName" />  onclick="javascript:confirmRemoveRowOnload(this,'specialityOnload','<bean:write name="investLst" property="icdProcCode"/>','<bean:write name="investLst" property="investRemarks" />','<bean:write name="investLst" property="catId" />');"/></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
        </table></td></tr>

<tr><td colspan="4" class="tbcellBorder">
        <table  width="100%"  id="categoryTable" style="display:none" border="1">
        <!--<tr><td colspan="6" class="labelheading1 tbcellCss">Newly Added Speciality</td></tr>-->
      <tr>  
      	<!-- <td width="5%" ><fmt:message key="EHF.SNO"/></td> -->      
        <td width="15%" ><b><fmt:message key="EHF.CatName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.ICDCatName"/></b></td>
        <td width="15%"><b><fmt:message key="EHF.ICDProc"/></b></td>
		<td width="5%"><b>Units</b></td>
        <td width="10%"><b>Treating Doctor</b></td>
		<td width="20%" ><b><fmt:message key="EHF.TestName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.Attachment"/></b></td>
        <td width="5%">&nbsp;</td>
        </tr></table>
</td></tr>
</table></td></tr>
<tr><td colspan="2"><div id="treatingDocLabel" class="labelheading1 tbcellCss" style="display:none"><b>Treating Doctor Remarks</b><font color="red">*</font></div>
<div id="treatingDocRemarks"></div></td></tr>
<tr><td colspan="4">
<table width="100%" >
<tr><td width="50%" valign="top">
<table width="100%">
<tr><td width="50%" class="labelheading1 tbcellCss"><b id="ipDop"><fmt:message key="EHF.IPNumber"/></b> <font color="red">*</font></td>
<td width="50%" class="labelheading1 tbcellCss" id="admisnType"><b><fmt:message key="EHF.AdmissionType"/></b> <font color="red">*</font></td></tr>
<tr><td width="50%" class="tbcellBorder">
<html:text name="patientForm" property="ipNo" styleId="ipNo" style="width:17em;" maxlength="20" onchange="validateAlphaNum('IP NO',this,'')" title="Enter IP NO"/>
</td>
<td width="50%" class="tbcellBorder" id="admisnTypeSel">
<html:select name="patientForm" property="admissionType" styleId="admissionType" title="Select Admission Type" style="width:17em;" onmouseover="addTooltip('admissionType')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="PLN"><fmt:message key="EHF.Planned"/></html:option>
<html:option value="EMG"><fmt:message key="EHF.Emergency"/></html:option>
</html:select></td></tr>
</table></td>

<td width="50%" valign="top">
<table width="100%">
<tr><td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PropSurgDt"/></b> <font color="red">*</font></td>
<td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font></td></tr>
<tr><td width="50%" class="tbcellBorder">
<html:text name="patientForm" property="ipDate" styleId="ipDate" style="width:14em;" title="Select Proposed Surgery Date" onchange="validatePropSurgeryDate('Proposed Surgery Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>
<td width="50%" class="tbcellBorder">
<html:textarea name="patientForm" property="remarks" styleId="remarks" title="Enter Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'IP Remarks');blockConsecutiveChars('IP Remarks',this,this.value)"/>
</td></tr></table></td></tr>
</table></td></tr>
<tr>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font>
<html:select name="patientForm" property="ipDiagnosedBy" styleId="ipDiagnosedBy" style="width:17em" onchange="fn_getIPDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('ipDiagnosedBy')"> 
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="MEDCO"><fmt:message key="EHF.MEDCO"/></html:option>
</html:select>
</td>
<td valign="top" id="ipDocNameList" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font>
<html:select name="patientForm" property="ipDoctorName" styleId="ipDoctorName" style="WIDTH:17em" title="Select Doctor Name" onmouseover="addTooltip('ipDoctorName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td>&nbsp;</td>
</tr>
<tr>
<td colspan="4" class="labelheading1 tbcellCss" id="admissionDetailsDiv" style="display:none;"> Admission Details : 
<html:radio property="admissionDetails" styleId="admissionDetails" name="patientForm" value="Pre-anaesthetic Checkup"> Pre-anaesthetic Checkup </html:radio>
 <html:radio property="admissionDetails" styleId="admissionDetails" name="patientForm" value="Advanced Investigations"> Advanced Investigations</html:radio>
 <html:textarea styleId="advancedInvestigations" name="patientForm" styleClass="form-control" property="advancedInvestigations" ></html:textarea>
 
</td></tr>
<tr id="medicationDiv1" style=""><td colspan="4" class="labelheading1 tbcellCss">Medication Given<font color="red" class="onlyAp" id="medicGiven"></font></td></tr>
<tr id="medicationDiv2" style=""><td colspan="4" class="tbcellBorder"><html:textarea name="patientForm" styleId="medicationGiven" property="medicationGiven" styleClass="form-control"></html:textarea></td>
</tr>

</tr>
<tr>
<td valign="top" class="labelheading1 tbcellCss" colspan="2"><b>Medico Legal Case,If any</b><font color="red">*</font>
	<html:radio name="patientForm" property="legalCase" value="Y" styleId="legalCase" onclick="enable_legalCase('usercheck')" title="Yes"/><b>Yes</b> 
	<html:radio name="patientForm" property="legalCase" value="N" styleId="legalCase" onclick="enable_legalCase('usercheck')" title="No"/><b>No</b> 
</td>
<td id='legalCaseNoTd' style="display:none" valign="top" class="labelheading1 tbcellCss"><b>Legal Case No</b><br>
	<html:text name="patientForm" property="legalCaseNo" style="WIDTH:17em" styleId="legalCaseNo" title="Legal Case No" maxlength="20" onchange="validateAlphaNum('Legal Case No',this,'Legal Case No')"/> 
</td>
<td id="policeStatNameTd" style="display:none" valign="top" class="labelheading1 tbcellCss"><b>Police Station Name</b><br>
	<html:text name="patientForm" property="policeStatName" style="WIDTH:17em" styleId="policeStatName" title="Police Station Name" maxlength="100" onchange="checkAlphaSpace('policeStatName','Police Station Name')"/> 
</td>
</tr>
<tr><td colspan="3">	
	<div id="ipDoctorDataDiv"> </div></td>
</tr>
</table>
</td></tr>

<!--End Of  Fields For Patient Type IP -->
<tr><td style="display:none"  colspan="4">
<table style="width:95%;margin: 0 auto;display:none" id="prescriptionData">
<tr><td><font ><b><fmt:message key="EHF.Prescription"/>:</b></font></td></tr>
<tr><td colspan="4" id="existDrugsHead" style="display:none" class="labelheading1 tbcellCss"><font size="2px">Existing Drugs</font></td></tr>
<tr><td colspan="4" class="tbcellBorder">
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



    <logic:equal value="CD202" name="patientForm" property="scheme">
     <logic:equal value="G" name="patientForm" property="hosptype">
    <tr>
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="OD" title="Click Here to Add Other Drugs" styleId="drugOthers" onchange="fn_enableOtherDrugs();" name="patientForm" property="drugsOthers" ><b>&nbsp;Other Drugs</b></html:checkbox>
        </td>
        </tr>
        <tr>
        <td  class="labelheading1 tbcellCss existDrugs" width="10%"><b>Chemical Substance Name</b></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="drugNameAuto" style="width:17em;" title="Select Drug Name" onchange="javascript:getDrugDetailsAuto()" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		  <html:options collection="drugsListAuto" property="ID" labelProperty="VALUE"/>
</html:select></td>
<td  class="labelheading1 tbcellCss existDrugs" colspan="2" style="color:#A81C06">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Select Drug Name to Auto Populate Data&nbsp;&nbsp;</font></b><i class="fa fa-medkit"></i> 
</td>
        </tr>
        </logic:equal>
        </logic:equal>

<tr>

<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Main Group Name</b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Main Group Code</b><font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Therapeutic Main Group Name</b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Therapeutic Main Group Code</b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugTypeCode" styleId="drugTypeCode" style="width:17em;" title="Select Drug type" onchange="getDrugSubTypeList()" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="drugsList" labelProperty="VALUE"/>
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="drugCode" styleId="drugCode" title="Drug Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugSubTypeName" styleId="drugSubTypeName" style="width:17em;" title="Select Drug Sub type" onchange="getPharSubGrpLst()" onmouseover="addTooltip('drugSubTypeName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="drugSubTypeCode" styleId="drugSubTypeCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Pharmacological SubGroup Name</b><font color="red" class="onlyAp">*</font> </td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Pharmacological SubGroup Code</b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Chemical SubGroup Name</b><font color="red" class="onlyAp">*</font> </td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Chemical SubGroup Code</b><font color="red" class="onlyAp">*</font> </td>
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="pSubGrpName" styleId="pSubGrpName" style="width:17em;" title="Select Pharmacological SubGroup " onchange="getChemicalSubGrp()" onmouseover="addTooltip('pSubGrpName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="pSubGrpCode" styleId="pSubGrpCode" title="Pharmacological SubGroup Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="cSubGrpName" styleId="cSubGrpName" style="width:17em;" title="Chemical SubGroup" onchange="getDrugNameList()" onmouseover="addTooltip('cSubGrpName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="cSubGrpCode" styleId="cSubGrpCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td class="labelheading1 tbcellCss existDrugs"><b>Chemical Substance Name</b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss existDrugs"><b>Chemical Substance Code</b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss" id="otherDrug" style="display:none"><b>Drug Name</b><font color="red" class="onlyAp">*</font> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b><font color="red" class="onlyAp">*</font> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b><font color="red" class="onlyAp">*</font> </td>   
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="drugName" style="width:17em;" title="Select Drug Name" onchange="getDrugDetails()" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>  
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="asriDrugCode" styleId="asriDrugCode" title="Drug Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td width="50%" id="othDrugName" style="display:none;"><html:text styleClass="otherFields" name="patientForm" property="otherDrugName"  onchange="javascript:check_maxLength('otherDrugName','200')" styleId="otherDrugName" title="Please enter Drug Name"></html:text></td>
<td class="tbcellBorder">
<table >
<tr><td width='50%'><html:select name="patientForm" property="routeType" styleId="routeType" style="width:8em;" title="Select Route Type" onchange="getRouteList()" onmouseover="addTooltip('routeType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="routeTypeLst" labelProperty="VALUE"/>
</html:select></td>

<td width='50%'><html:select name="patientForm" property="route" styleId="route" style="width:8em;" title="Select Route "  onmouseover="addTooltip('route')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td> </tr>
</table></td>
<td class="tbcellBorder">   
<table >
<tr><td width='50%'><html:select name="patientForm" property="strengthType" styleId="strengthType" style="width:8em;" title="Select Strength Type" onchange="getStrengthList()" onmouseover="addTooltip('strengthType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="strengthTypeLst" labelProperty="VALUE"/>
</html:select></td>
<td width='50%'><html:select name="patientForm" property="strength" styleId="strength" style="width:8em;" title="Select Strength "  onmouseover="addTooltip('strength')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td> </tr>
</table></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b><font color="red" class="onlyAp">*</font> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b><font color="red" class="onlyAp">*</font> </td>
<td colspan="2">&nbsp;</td>
</tr> 
<tr>
<td class="tbcellBorder">
<html:select name="patientForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:17em" onchange="validateNumber('Medication Period',this)" title="Enter Medication Period"/></td>
<td colspan="2"><button type="but" class="btn btn-success" name="addDrug" id="addDrug" value="Add Drugs" onclick="addDrugs()">Add Drugs&nbsp;<i class="fa fa-plus-square"></i></button></td>
</tr>

<tr><td colspan="4" class="tbcellBorder"> 
<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
  <table  width="100%"  id="drugsTable" style="display:none" border="1">
      <tr>  
      	<td width="5%"><b><fmt:message key="EHF.SNO"/></b></td>        
        <td width="10%"><b>Main Group Name</b></td>   
       	<td width="10%"><b>Therapeutic Main Group Name</b></td>
        <td width="10%"><b>Pharmacological SubGroup Name</b></td>
		<td width="10%"><b>Chemical SubGroup Name</b></td>
		<td width="10%"><b>Chemical Substance Name</b></td>
        <td width="5%"><b>Route Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="5%"><b>Strength Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Strength"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td width="5%"><%=k++ %></td>        
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPENAME" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGSUBTYPENAME" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="PSUBGRPNAME"/></td>
        <td width="10%"><bean:write name="drugLst" property="CSUBGRPNAME"/></td> 
       	<td width="10%"><bean:write name="drugLst" property="DRUGNAME" /></td> 
       	<td width="5%"><bean:write name="drugLst" property="ROUTETYPENAME" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="ROUTENAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="STRENGTHTYPENAME" /></td>
       	<td width="10%"><bean:write name="drugLst" property="STRENGTHNAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="DOSAGE" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="MEDICATIONPERIOD" /></td> 
       	<td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name='drugLst' property='DRUGCODE' /> id=<bean:write name='drugLst' property='DRUGCODE' /> onclick="javascript:confirmRemoveRow(this,'drug');"/></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>        
        </table></div>
</td></tr>
</table>
</td></tr>
<tr><td style="display:none"  colspan="4">
<table style="width:95%;margin: 0 auto;display:none" id="OPHead2">
<tr>
<td width="20%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.OPNO"/></b> <font color="red">*</font></td>
<td width="20%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.RegistrationDate"/></b> <font color="red">*</font></td>
<td width="40%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font></td>
<td width="20%">&nbsp;</td>
</tr>
<tr>
<td valign="top" class="tbcellBorder"><html:text name="patientForm" property="opNo" styleId="opNo" maxlength="20" style="width:17em" onchange="validateAlphaNum('OP NO',this,'')" title="Enter OP NO"/></td>
<td valign="top" class="tbcellBorder"><html:text name="patientForm" property="opDate" styleId="opDate" style="width:17em" value="${serverDt}" disabled="true"/></td>
<td class="tbcellBorder">
<html:textarea name="patientForm" property="opRemarks" styleId="opRemarks" title="Enter OP Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'OP Remarks');blockConsecutiveChars('OP Remarks',this,this.value)"/>
</td>
<td>&nbsp;</td>
</tr>
</table>
<table width="95%" style="margin:auto;display:none" id="OPDoctor">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font></td>
<td width="25%" id="docName" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font></td>

<c:if test="${schemeId eq 'CD201'}">
<td width="25%">&nbsp;</td>
<td width="25%">&nbsp;</td>
</c:if>

<c:if test="${schemeId eq 'CD202'}">
<td width="25%" class="labelheading1 tbcellCss" id="unitNameHead" style="display:none;"><b>Unit Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss" id="unitHODNameHead" style="display:none;"><b>Unit HOD Name</b> <font color="red">*</font></td>

</c:if>
</tr>
<tr>
<td  class="tbcellBorder">

<c:if test="${schemeId eq 'CD201' || hospType ne 'G'}">
<html:select name="patientForm" property="diagnosedBy" styleId="diagnosedBy" style="width:17em" onchange="fn_getDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('diagnosedBy')"> 
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="MEDCO"><fmt:message key="EHF.MEDCO"/></html:option>
<html:option value="InHouseDoctor"><fmt:message key="EHF.InHouseDoctor"/></html:option>
<html:option value="Consultant"><fmt:message key="EHF.Consultant"/></html:option>
<html:option value="DutyDoctor"><fmt:message key="EHF.DutyDoctor"/></html:option>
</html:select>
</c:if>

<c:if test="${schemeId eq 'CD202' && hospType eq 'G'}">
<html:select name="patientForm" property="diagnosedBy" styleId="diagnosedBy" style="width:17em" onchange="fn_getDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('diagnosedBy')"> 
<html:option value="-1" ><fmt:message key="EHF.Select"/></html:option>
<html:option value="MEDCO" styleId="onlyIp1" style="display:none"><fmt:message key="EHF.MEDCO"/></html:option>
<html:option value="Resident Doctor">Resident Doctor</html:option>
<html:option value="Consultant"><fmt:message key="EHF.Consultant"/></html:option>
<html:option value="DutyDoctor" styleId="onlyIp2" style="display:none"><fmt:message key="EHF.DutyDoctor"/></html:option>
</html:select>
</c:if>
</td>
<c:if test="${schemeId eq 'CD202'}">
<td valign="top" id="unitName" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="unitName" styleId="unitName" maxlength="50" style="WIDTH:17em"  title="Enter Unit Name"/></td>
<td valign="top" id="unitHodName" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="unitHodName" styleId="unitHodName" maxlength="50" style="WIDTH:17em" title="Enter Unit HOD  Name"/></td>
<td valign="top" id="addConsult" style="display:none"  class="labelheading1 tbcellCss"><button class="btn btn-primary" id="addConsult" onclick="javascript:fn_addConsultant();">Add Consultation Details&nbsp;<i class="fa fa-stethoscope"></i></button></td>
</c:if>

<td valign="top" id="docNameList" class="tbcellBorder">
<html:select name="patientForm" property="doctorName" styleId="doctorName" style="WIDTH:17em"  title="Select Doctor Name" onmouseover="addTooltip('doctorName')"> <!-- onchange="fn_getDoctorsDetails()" -->
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td valign="top" id="docNametext" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="otherDocName" styleId="otherDocName" maxlength="50" style="WIDTH:17em" onchange="checkAlphaSpace('otherDocName','Doctor Name')" title="Enter Doctor Name"/></td>
<td width="20%">&nbsp;</td>
<td class="tbcellCss" id="consulFee" style="display:none"><b><font color="#B01000">Rs 150</font></b></td>
</tr>
<tr><td colspan="4" class="tbcellBorder">			
	<div id="doctorDataDiv"> </div></td>
</tr> 
<tr id="doctorData" style="display:none">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Doctor.RegistrationNo"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docRegNo" styleId="docRegNo" styleClass="otherFields" maxlength="20" style="WIDTH:9em"    onchange="javascript:check_maxLength('docRegNo','100')" title="Enter Doctor Registration No"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.doctor.Qualification"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docQual" styleId="docQual" maxlength="30" style="WIDTH:9em" styleClass="otherFields" onchange="javascript:check_maxLength('docQual','100')" title="Enter Doctor Qualification"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docMobileNo" styleId="docMobileNo" maxlength="10" onchange="validateMobile(this);" style="WIDTH:9em" title="Enter Doctor Contact No"/></td>
</tr>
</table>

<!-- <table  style="width:95%;margin: 0 auto;"> -->
<!-- <tr><td colspan="4" class="labelheading1 tbcellCss">Post OP Instructions and Follow up Advice<font color="red" class="onlyAp" id="postOPInstr"></font></td> </tr> -->
<%-- <tr><td colspan="4" class="tbcellBorder"><html:textarea name="patientForm" styleId="followupAdv" property="followupAdv" styleClass="form-control"></html:textarea></td></tr> --%>
<!-- </table> -->

<table width="100%" id="opClaimTable" style="display:none;width:95%;margin: 0 auto;">
<tbody>
<tr>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Consulation Fee</b></td>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Cost of Investigations</b> </td>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Total OP Amount</b></td>
</tr>
<tr>
<td class="tbcellCss" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="consultFee"></bean:write>&nbsp;/-</font></b></td>
<td class="tbcellCss" id="costOfInvest" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="totInvestPrice"></bean:write>&nbsp;/-</font></b></td>
<td class="tbcellCss" id="totalOpCost" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="totalOpCost"></bean:write>&nbsp;/-</font></b></td>
</tr>
</tbody>
</table>


<table style="width:95%;margin: 0 auto;"><tr>
<td colspan="4" id="opNote" style="display:none">
<font color="red"><b>OP Case Should be Submitted before 14 days after Case Registration</b></font>
</td></tr>

<tr><td colspan="4" id="ipNote1">
<font color="red">Please save general mandatory details to generate DTRS Print Form and enable submit.</font>
</td></tr>
<tr><td>&nbsp;</td></tr>

<tr><td colspan="4">
<table width="100%">
<tr>
<td style="height: 1em; font-size:small;" nowrap="nowrap" width=20%>
		<font color="red"><fmt:message key="EHF.MandatoryFields"/> <br /></font>
</td></tr></table></table>


<table width="70%" align="center" style="margin-left:30%">
<tr>


<td align="center" colspan="3"> <button class="btn btn-primary has-spinner"  type="button" id="Submit" onclick="javascript:fn_savePatientDetails('submit')">Submit&nbsp;<span class="spinner"><i class="fa fa-medkit"></i></span></button>
 <button class="btn btn-primary has-spinner"  type="button" id="Save" onclick="javascript:fn_saveDetailsWithoutMandate('save')">Save&nbsp;<span class="spinner"><i class="fa fa-floppy-o"></i></span></button>
 <button class="btn btn-primary"  type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset&nbsp;<i class="fa fa-eraser"></i></button>
<button type="button" class="btn btn-danger has-spinner"    style="display:none" id="cancel" onclick="javascript:fn_cancel()"; data-toggle="tooltip" data-placement="right" title="Click Here To Cancel Chronic Case">Cancel&nbsp;<span class="glyphicon glyphicon-remove"></span></button>
<button type="button" class="btn btn-info"    style="display:none" id="addAttach" onclick="javascript:fn_addAttach()"; data-toggle="modal" data-target="#addAttachModal" title="Click Here To Add Attachments">Add Attachments&nbsp;<i class="fa fa-files-o"></i></button>
</td>


<td width="20%"></td>
</tr>
</table>

<br><br>

<!--  modal for showing attachments -->
<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="attachModal" >
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content">
 
 <div class="modal-body" style="height: 410px;">
 
 <iframe  id="attachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div></div>
 
 
 
 <!-- div for showing past history  -->
 
 <div id="pastHistoryModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
 <div class="modal-dialog" id="modal-1gx" style="
    margin-left: 8%;
    margin-right: 5%;">
 <div class="modal-content" style=";width:140%;align:center;margin:auto;">
 <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Case Details</label>
</div>

 <div class="modal-body" style="height:410px;">
 <iframe id="pastHistoryFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div>
 </div>
 
 
  <!-- div for  adding attachments   -->
 
 <div id="addAttachModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
 <div class="modal-dialog" id="modal-1gx" style="
    margin-left: 8%;
    margin-right: 5%;">
 <div class="modal-content" style=";width:140%;align:center;margin:auto;">
 <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Attachments</label>
</div>

 <div class="modal-body" style="height:410px;">
 <iframe id="addAttachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div>
 </div>
 
 

<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="testsCount" styleId="testsCount"/> 
<html:hidden name="patientForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="patientForm" property="hospitalId" styleId="hospitalId"/>
<input type="hidden" name="investigationsSel" id="investigationsSel">
<html:hidden name="patientForm" property="personalHistVal" styleId="personalHistVal"/>
<html:hidden name="patientForm" property="examFndsVal" styleId="examFndsVal"/>
<html:hidden name="patientForm" property="speciality" styleId="speciality"/>
<html:hidden name="patientForm" property="otherDocDetailsList" styleId="otherDocDetailsList"/>
<html:hidden name="patientForm" property="drugs" styleId="drugs"/>
<html:hidden name="patientForm" property="symptoms" styleId="symptoms"/>
<html:hidden name="patientForm" property="hosptype" styleId="hosptype"/>
<html:hidden name="patientForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="patientForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="patientForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="patientForm" property="therapyType" styleId="therapyType"/>
<html:hidden name="patientForm" property="therapyCategory" styleId="therapyCategory"/>
<html:hidden name="patientForm" property="therapy" styleId="therapy"/>
<html:hidden name="patientForm" property="personalHist" styleId="personalHist"/>
<html:hidden name="patientForm" property="gender" styleId="gender"/>
<html:hidden name="patientForm" property="years" styleId="years"/>
<html:hidden name="patientForm" property="months" styleId="months"/>
<html:hidden name="patientForm" property="days" styleId="days"/>
<html:hidden name="patientForm" property="scheme" styleId="scheme"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="dentalSpecApproval"/>
<html:hidden name="patientForm" property="dentalProc"/>
<html:hidden name="patientForm" property="consultFee" styleId="consultFee"/>
<html:hidden name="patientForm" property="investPrice" styleId="investPrice"/>
<html:hidden name="patientForm" property="totInvestPrice" styleId="totInvestPrice"/>
<html:hidden name="patientForm" property="totalOpCost" styleId="totalOpCost"/>
<html:hidden name="patientForm" property="otherInvestCount" styleId="otherInvestCount"/>
<html:hidden name="patientForm" property="otherDrugCount" styleId="otherDrugCount"/>
<html:hidden name="patientForm" property="opActiveMsg" styleId="opActiveMsg"/>
<html:hidden name="patientForm" property="otherSymptomCount" styleId="otherSymptomCount"/>

<html:hidden name="patientForm" property="postDist" styleId="postDist"/>
<html:hidden name="patientForm" property="stoCode" styleId="stoCode"/>
<html:hidden name="patientForm" property="ddoCode" styleId="ddoCode"/>
<html:hidden name="patientForm" property="deptHod" styleId="deptHod"/>

<html:hidden name="patientForm" property="checkType" styleId="checkType"/>
<html:hidden name="patientForm" property="hosType" styleId="hosType" value="${patientForm.hosType}"/>
<html:hidden name="patientForm" property="hospGovu" styleId= "hospGovu" value="${hospGovu}"/>


<html:hidden name="patientForm" property="carriesdecidous" styleId="carriesdecidous"/>
<html:hidden name="patientForm" property="mobiledecidous" styleId="mobiledecidous"/>
<html:hidden name="patientForm" property="grosslydecadedecidous" styleId="grosslydecadedecidous"/>
<html:hidden name="patientForm" property="missingdecidous" styleId="missingdecidous"/>
<html:hidden name="patientForm" property="carriespermanent" styleId="carriespermanent"/>
<html:hidden name="patientForm" property="rootstumppermannet" styleId="rootstumppermannet"/>
<html:hidden name="patientForm" property="mobilitypermanent" styleId="mobilitypermanent"/>
<html:hidden name="patientForm" property="attritionpermanent" styleId="attritionpermanent"/>
<html:hidden name="patientForm" property="missingpermanent" styleId="missingpermanent"/>
<html:hidden name="patientForm" property="otherpermanent" styleId="otherpermanent"/>
<html:hidden name="patientForm" property="probingdepth" styleId="probingdepth"/>
<script>
var specOld=new Array();
$(function(){
    $('a, button').click(function() {
        $(this).toggleClass('active');
    });
});
<%-- //<% request.setAttribute("carriesdecidous","carriesdecidous"); %>  --%>
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

var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
	document.getElementById("presentHistory").attachEvent("onpaste",pasteIntercept);
	document.getElementById("remarks").attachEvent("onpaste",pasteInterceptRemarks);
	document.getElementById("opRemarks").attachEvent("onpaste",pasteInterceptOpRemarks);
	if(document.getElementById("treatingDocLabel").style.display=='')
		{
		document.getElementById("treatingDocRmks").attachEvent("onpaste",pasteInterceptDocRemarks);
		}
	}
else if(browserName == "Netscape")
	{
	document.getElementById("presentHistory").addEventListener("paste", pasteIntercept, false);
	document.getElementById("remarks").addEventListener("paste", pasteInterceptRemarks, false);
	document.getElementById("opRemarks").addEventListener("paste", pasteInterceptOpRemarks, false);
	if(document.getElementById("treatingDocLabel").style.display=='')
		{
		document.getElementById("treatingDocRmks").addEventListener("paste",pasteInterceptDocRemarks,false);
		}
	}


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

/*var pastHistory=document.forms[0].pastHistory;
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
	}
	}}
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
}*/
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
		
var personalHabits='<table width="100%" border="1"><tr><td style="background: #1E4B89;color: #FFF;text-align: center;">Deleterious Habits</td><tr><td>'+
'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
'<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
'<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
	'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
'<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
'<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
'<div id="smokingTd" style="display:none">'+
'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
'<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
'<tr><td>Pan Chewing:<input type="radio" name="panChewing" id="panChewing" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="panChewing" id="panChewing" value="No" title="No"/>No</td></tr>'+
'<tr><td>Gutka :<input type="radio" name="gutka" id="gutka" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="gutka" id="gutka" value="No" title="No"/>No</td></tr></tr>'+
'<tr><tr><td style="background: #1E4B89;color: #FFF;text-align: center;">Parafunctional Habits</td></tr><td>Finger/Thumb Sucking:<input type="radio" name="fingerSucking" id="fingerSucking" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="fingerSucking" id="fingerSucking" value="No" title="No"/>No</td></tr>'+
'<tr><td>Nail/Lip Biting:<input type="radio" name="nailBiting" id="nailBiting" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="nailBiting" id="nailBiting" value="No" title="No"/>No</td></tr>'+
'<tr><td>Tongue biting/Thrusting:<input type="radio" name="tongueBiting" id="tongueBiting" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="tongueBiting" id="tongueBiting" value="No" title="No"/>No</td></tr>'+
'<tr><td>Mouth Breathing:<input type="radio" name="mouthBreathing" id="mouthBreathing" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="mouthBreathing" id="mouthBreathing" value="No" title="No"/>No</td></tr>'+
'<tr><td>Teeth clenching / Bruxism:<input type="radio" name="bruxism" id="bruxism" value="Yes" title="Yes"/>Yes'+
'<input type="radio" name="bruxism" id="bruxism" value="No" title="No"/>No</td></tr></table>';
document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
}
	
if('${PatientOpList.lstPerHis}'!=null && '${PatientOpList.lstPerHis}'.indexOf("PR8.2",0)!=-1)
	{
	drughst=drughst+'<table width="100%" border="1"><tr><td><div id="drughst" style="display:none">Specify:<input type="text" name="drughstRemrk" id="drughstRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
	 displayTextBox(document.getElementById('PR8.2').id);
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
		    else if(addtn[0]=='PanChewing')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].panChewing[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].panChewing[1].checked='checked';
		    }
		    else if(addtn[0]=='Gutka')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].gutka[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].gutka[1].checked='checked';
		    }
		    else if(addtn[0]=='FingerSucking')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].fingerSucking[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].fingerSucking[1].checked='checked';
		    }
		    else if(addtn[0]=='NailBiting')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].nailBiting[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].nailBiting[1].checked='checked';
		    }
		    else if(addtn[0]=='TongueBiting')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].tongueBiting[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].tongueBiting[1].checked='checked';
		    }
		    else if(addtn[0]=='MouthBreathing')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].mouthBreathing[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].mouthBreathing[1].checked='checked';
		    }
		    else if(addtn[0]=='Bruxism')
		    {
		    	if(addtn[1]=='Yes')
	          	  document.forms[0].bruxism[0].checked='checked';
	            else  if(addtn[1]=='No')
	          	  document.forms[0].bruxism[1].checked='checked';
		    }
		    
	    }
	}
	var HospDental='${dentalFlg}';
	if(HospDental!=null && HospDental!="" && HospDental!="Y")
	{
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
	}
//pavan	
	var drughst='${denatlcasesheet.drugHst}';
	if(drughst=="Yes")
		{
		 document.getElementById("drughistory").style.display="";
		 document.getElementById("drughistory").value='${denatlcasesheet.drugHstVal}';
		}
	
	// medical history
	var medchst='${denatlcasesheet.medicalDtlsidStr}';
	var arr=medchst.split('~');
	for(var i=0 ; i<arr.length;i++)
		{
		$('input[type=checkbox][value='+arr[i]+']').prop('checked',true);
			if(arr[i]=="CH117")
				{
				document.getElementById("showMedicalText").style.display="";
				document.forms[0].showMedicalTextval.value='${denatlcasesheet.showMedicalTextval}';
				}
		}
	if(arr=="" || arr==null ){
		document.forms[0].medicalhistoryid[1].checked=true;
		var yesRno="No";
		enableMedicalHistory(yesRno);
	}
	else{
		document.forms[0].medicalhistoryid[0].checked=true;
		var yesRno="Yes";
		enableMedicalHistory(yesRno);
	}
		//extra oral and intra oral retrival 
	
	var extraoral='${denatlcasesheet.extraoralStr}';
	var subextraoral='${denatlcasesheet.subdentalrl0Str}';
	var lymphtext='${denatlcasesheet.subdentalrltxt}';
	var jawstext='${denatlcasesheet.subdentaljawstxt}';
	var jaws='${denatlcasesheet.subdentaljaws1}';
	//alert(extraoral+"-"+subextraoral+"-"+lymphtext+"-"+jawstext+"-"+jaws);
	//alert(lymphtext);
	if(jaws!=null && jaws!="" )
		{
		document.getElementById("dntsublistjaws1").style.display="";
		document.forms[0].subdentaljaws1.value='${denatlcasesheet.subdentaljaws1}';
		}
	if(jawstext!=null && jawstext!=""){
		document.getElementById("dntsublistjawstxt1").style.display="";
		document.forms[0].subdentaljawstxt1.value=jawstext;	
	}
	var subarr=subextraoral.split('~');
	if(subextraoral!=null && subextraoral!="" )
		{
		document.getElementById("dntsublistrl0").style.display="";
		if(subarr.length>=1)
			{
	for(var i=0; i<subarr.length ; i++)
	{
		for(var j=0; j<document.forms[0].subdentalrl0.length;j++){
		// document.forms[0].subdentalrl0.value=subarr[i];
		if(document.forms[0].subdentalrl0[j].value==subarr[i])
	    document.forms[0].subdentalrl0[j].selected=true;
		}
	}
			}
		}
	var arr=extraoral.split('~');
	for(var i=0 ; i<arr.length;i++)
		{
		$('input[type=checkbox][value='+arr[i]+']').prop('checked',true);
		if(arr[i]=="CH4")
		{
			document.getElementById("dntsublist0").style.display="";
			document.forms[0].subdental0.value='${denatlcasesheet.subdental0}';
			
		}
		if(arr[i]=="CH3")
		{
			document.getElementById("dntsublist1").style.display="";	
			document.forms[0].subdental1.value='${denatlcasesheet.subdental1}';
		}
		if(arr[i]=="CH2")
		{
			document.getElementById("dntsublist2").style.display="";	
			document.forms[0].subdental2.value='${denatlcasesheet.subdental2}';
		}
		if(arr[i]=="CH1")
		{
			document.getElementById("dntsublist3").style.display="";	
			document.forms[0].subdental3.value='${denatlcasesheet.subdental3}';
		}
		}
	var intraoral='${denatlcasesheet.intraoralStr}';
	var arr=intraoral.split('~');
	for(var z=0 ; z<arr.length ; z++)
		{
		$('input[type=checkbox][value='+arr[z]+']').prop('checked',true);
		         if(arr[z]=="CH11")
					{
				document.getElementById("dntsublistoraltd0").style.display="";
				document.forms[0].dntsublistoral0.value='${denatlcasesheet.dntsublistoral0}';
					}
				if(arr[z]=="CH10")
					{
					
				document.getElementById("dntsublistoraltd1").style.display="";
				document.forms[0].dntsublistoral1.value='${denatlcasesheet.dntsublistoral1}';
					}
				 if(arr[z]=="CH9")
					{
				document.getElementById("dntsublistoraltd2").style.display="";
				document.forms[0].dntsublistoral2.value='${denatlcasesheet.dntsublistoral2}';
					}
				if(arr[z]=="CH8")
					{
				document.getElementById("dntsublistoraltd3").style.display="";
				document.forms[0].dntsublistoral3.value='${denatlcasesheet.dntsublistoral3}';
					}
				if(arr[z]=="CH7")
					{
				document.getElementById("dntsublistoraltd4").style.display="";
				document.forms[0].dntsublistoral4.value='${denatlcasesheet.dntsublistoral4}';
					}
				if(arr[z]=="CH6")
					{
				document.getElementById("dntsublistoraltd5").style.display="";
				document.forms[0].dntsublistoral5.value='${denatlcasesheet.dntsublistoral5}';
					}
				if(arr[z]=="CH5")
					{
				document.getElementById("dntsublistoraltd6").style.display="";
				document.forms[0].dntsublistoral6.value='${denatlcasesheet.dntsublistoral6}';
		             } 
		}
	
		var swellSite='${denatlcasesheet.swSite}';
		var swellSize='${denatlcasesheet.swSize}';
		var swellExtension='${denatlcasesheet.swExtension}';
		var swellColour='${denatlcasesheet.swColour}';
		var swellConsistency='${denatlcasesheet.swConsistency}';
		var swellTenderness='${denatlcasesheet.swTenderness}';
		var swellBorders='${denatlcasesheet.swBorders}';

		if(intraoral!="" || swellSite!="" || swellSize!="" || swellExtension!="" || swellColour!="" || swellConsistency!="" || swellTenderness!="" || swellBorders!=""){
			document.forms[0].softtissueyesorno[0].checked=true;
			document.getElementById("softtissueexaminblock").style.display="";
		}
		else{
			document.forms[0].softtissueyesorno[1].checked=true;
			document.getElementById("softtissueexaminblock").style.display="none";
		}
		
		if(swellSite!="" || swellSize!="" || swellExtension!="" || swellColour!="" || swellConsistency!="" || swellTenderness!="" || swellBorders!=""){
			document.forms[0].Swelling.checked=true;
			showSwellingPusTab(this.id);
		}
		if(swellSite!=null && swellSite!=""){
			document.forms[0].swSite.value=swellSite;	
		}
		if(swellSize!=null && swellSize!=""){
			document.forms[0].swSize.value=swellSize;	
		}
		if(swellExtension!=null && swellExtension!=""){
			document.forms[0].swExtension.value=swellExtension;	
		}
		if(swellColour!=null && swellColour!=""){
			document.forms[0].swColour.value=swellColour;	
		}
		if(swellConsistency!=null && swellConsistency!=""){
			document.forms[0].swConsistency.value=swellConsistency;	
		}
		if(swellTenderness!=null && swellTenderness!=""){
			document.forms[0].swTenderness.value=swellTenderness;	
		}
		if(swellBorders!=null && swellBorders!=""){
			document.forms[0].swBorders.value=swellBorders;	
		}
		
		var pusDisSite='${denatlcasesheet.psSite}';
		var pusDisDischarge='${denatlcasesheet.psDischarge}';
		if(pusDisSite!="" || pusDisDischarge!="")
			{
				document.forms[0].PusFlashDischarge.checked=true;
				showSwellingPusTab(this.id);
			}
		if(pusDisSite!=null && pusDisSite!=""){
			document.forms[0].psSite.value=pusDisSite;	
		}
		if(pusDisDischarge!=null && pusDisDischarge!=""){
			document.forms[0].psDischarge.value=pusDisDischarge;	
		}
		
		if(lymphtext!=null && lymphtext!="")
			{
			document.getElementById("dntsublistrltxt0").style.display="";
			document.forms[0].subdentalrltxt0.value='${denatlcasesheet.subdentalrltxt}';
			}
// 			if(jawstext!=null && jawstext!="")
// 			{
// 			document.getElementById("dntsublistjawstxt1").style.display="";
// 			document.forms[0].subdentaljawstxt.value='${denatlcasesheet.subdentaljawstxt}';
// 			}
			
	//deciduos teeth retriving
	var decDenSelArr=[];
	var carries='${denatlcasesheet.carriesdecidous}';
	if(carries!=null && carries!="")
		{
		decDenSelArr.push("CH87");
		document.forms[0].deciduousDent.value="CH87";
		document.getElementById("cariesDecdious").style.display="";
	var carriesdeciocus=carries.split('~');
	for(var i=0 ; i<carriesdeciocus.length;i++)
	{
	$('input[type=checkbox][value='+carriesdeciocus[i]+']').prop('checked',true);
	}
		}
	
	var grosslydecade='${denatlcasesheet.grosslydecadedecidous}';
	if(grosslydecade!=null && grosslydecade!="")
		{
		decDenSelArr.push("CH88");
		document.forms[0].deciduousDent.value="CH88";
		document.getElementById("grosslyDecdious").style.display="";
	var grosslydecadedecidous=grosslydecade.split('~');
	for(var i=0 ; i<grosslydecadedecidous.length;i++)
	{
	$('input[type=checkbox][value='+grosslydecadedecidous[i]+']').prop('checked',true);
	}
		}
	
	var mobiled='${denatlcasesheet.mobiledecidous}';
	if(mobiled!=null && mobiled!="")
		{
		decDenSelArr.push("CH89");
		document.forms[0].deciduousDent.value="CH89";
		document.getElementById("mobileDecdious").style.display="";
	var mobiledecidous=mobiled.split('~');
	for(var i=0 ; i<mobiledecidous.length;i++)
	{
	$('input[type=checkbox][value='+mobiledecidous[i]+']').prop('checked',true);
	}
		}
	
	var missingdes='${denatlcasesheet.missingdecidous}';
	if(missingdes!=null && missingdes!="")
		{
		decDenSelArr.push("CH90");
		document.forms[0].deciduousDent.value="CH90";
		document.getElementById("missingDecdious").style.display="";
	var missingdecidous=missingdes.split('~');
	for(var i=0 ; i<missingdecidous.length;i++)
	{
	$('input[type=checkbox][value='+missingdecidous[i]+']').prop('checked',true);
	}
		}
	if(decDenSelArr.length>0){
		document.getElementById("decidiousdentsel").checked=true;
		permDeciddentsel();
	}
	for(var m=0; m<decDenSelArr.length;m++){
		for(var n=0; n<deciduousDent.length;n++){
		if(document.forms[0].deciduousDent[n].value==decDenSelArr[m])
		    document.forms[0].deciduousDent[n].selected=true;
			}
	}

	// permanent  dentattion retreving
	
	var perDenSelArr=[];
	var carriesper='${denatlcasesheet.carriespermanent}';
	if(carriesper!=null && carriesper!="")
		{
		perDenSelArr.push("CH96");
		document.forms[0].permanentDent.value="CH96";
		document.forms[0].permanentDent.value.selected=true;
		document.getElementById("cariesDiv").style.display="";
	var carriespermanent=carriesper.split('~');
	for(var i=0 ; i<carriespermanent.length;i++)
	{
	$('input[type=checkbox][value='+carriespermanent[i]+']').prop('checked',true);
	}
		}
	
	var rootstumpper='${denatlcasesheet.rootstumppermannet}';
	if(rootstumpper!=null && rootstumpper!="")
		{
		perDenSelArr.push("CH92");
		document.forms[0].permanentDent.value="CH92";
		document.getElementById("rootDiv").style.display="";
	var rootstumppermannet=rootstumpper.split('~');
	for(var i=0 ; i<rootstumppermannet.length;i++)
	{
	$('input[type=checkbox][value='+rootstumppermannet[i]+']').prop('checked',true);
	}
		}
	
	var mobilityper='${denatlcasesheet.mobilitypermanent}';
	if(mobilityper!=null && mobilityper!="")
		{
		perDenSelArr.push("CH93");
		document.forms[0].permanentDent.value="CH93";
		document.getElementById("mobilityDiv").style.display="";
	var mobilitypermanent=mobilityper.split('~');
	for(var i=0 ; i<mobilitypermanent.length;i++)
	{
	$('input[type=checkbox][value='+mobilitypermanent[i]+']').prop('checked',true);
	}
		}
	
	var attritionper='${denatlcasesheet.attritionpermanent}';
	if(attritionper!=null && attritionper!="")
		{
		perDenSelArr.push("CH94");
		document.forms[0].permanentDent.value="CH94";
		document.getElementById("attritionDiv").style.display="";
	var attritionpermanent=attritionper.split('~');
	for(var i=0 ; i<attritionpermanent.length;i++)
	{
	$('input[type=checkbox][value='+attritionpermanent[i]+']').prop('checked',true);
	}
		}
	
	var missingper='${denatlcasesheet.missingpermanent}';
	if(missingper!=null && missingper!="")
		{
		perDenSelArr.push("CH95");
		document.forms[0].permanentDent.value="CH95";
		document.getElementById("missingDiv").style.display="";
	var missingpermanent=missingper.split('~');
	for(var i=0 ; i<missingpermanent.length;i++)
	{
	$('input[type=checkbox][value='+missingpermanent[i]+']').prop('checked',true);
	}
		}
	
	 //permanent other dentation 
	var otherper='${denatlcasesheet.otherpermanent}';
	if(otherper!=null && otherper!="" && otherper!="-1~")
		{
		perDenSelArr.push("CH91");
		var otherpermanent=otherper.split('~');
		if(otherpermanent[0]!=null)
			{
		document.forms[0].permanentDent.value="CH91";
		document.getElementById("otherDiv").style.display="";
		document.getElementById("otherPermTextDiv").style.display="";
		document.getElementById("otherPermntDent").value=otherpermanent[0];
		document.getElementById("otherPermText").value=otherpermanent[1];
			}
			
		}
	
	if(perDenSelArr.length>0){
		document.getElementById("permanentdentsel").checked=true;
		permDeciddentsel();
	}
	for(var m=0; m<perDenSelArr.length;m++){
		for(var n=0; n<permanentDent.length;n++){
		if(document.forms[0].permanentDent[n].value==perDenSelArr[m])
		    document.forms[0].permanentDent[n].selected=true;
			}
	}
	// previous dnetal treatmnet & occlusion 
	var pervdent='${denatlcasesheet.previousDentalTreatment}';
	var occlusion='${denatlcasesheet.occlusion}';
	var occlusiontype='${denatlcasesheet.occlusionType}';
	var occlusionothr='${denatlcasesheet.occlusionOther}';
	if(pervdent!=null && pervdent!="")
		document.getElementById("previousDentalTreatment").value=pervdent;
	if(occlusion!=null && occlusion!="")
		{
		document.forms[0].occlusion.value=occlusion;
		if(occlusion=="CH81")
			{
			document.getElementById("occlusionTypeDiv").style.display="";
			document.getElementById("occlusionType").value=occlusiontype;
			}
		if(occlusion=="CH83")
		{
		document.getElementById("occlusionOtherDiv").style.display="";
		document.getElementById("occlusionOther").value=occlusionothr;
		}
		}
	
	// probing depth retreving
	
	var probingdepth='${denatlcasesheet.probingdepth}';
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
	//other deatils of dental case sheet 
    var diagnosis='${dentalDtls.diagnosis}';
   
	if(diagnosis!=null && diagnosis!="")
	document.getElementById("diagnosis").value=diagnosis;
    var admissionDetails='${dentalDtls.admissionDetails}';
    if(admissionDetails!=null && admissionDetails!="")
    document.getElementById("admissionDetails").value=admissionDetails;
    var advancedInvestigations='${dentalDtls.advancedInvestigations}';
    if(advancedInvestigations!=null && advancedInvestigations!="")
    document.getElementById("advancedInvestigations").value=advancedInvestigations;
    var followupAdv='${dentalDtls.followupAdv}';
    if(followupAdv!=null && followupAdv!="")
    document.getElementById("followupAdv").value=followupAdv;
    var medicationGiven='${dentalDtls.medicationGiven}';
    if(medicationGiven!=null && medicationGiven!="")
    document.getElementById("medicationGiven").value=medicationGiven;

//patient deatils
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
	
	if('${PatientOpList.bpRmt}'!='NA' && '${PatientOpList.bpRmt}'!='' && '${PatientOpList.bpRmt}'!=null && '${PatientOpList.bpRmt}'!='/')
		{var bpRmt='${PatientOpList.bpRmt}'.split("/");
		
		if(document.getElementById("GE15"))
		{
		document.forms[0].GE15.value=bpRmt[0];
		document.forms[0].BP2.value=bpRmt[1];	}}
	else
	{
		var schemeId='${schemeId}';
		if(schemeId=='CD202')
		{if(document.getElementById("GE15ROW"))
			document.getElementById("GE15ROW").style.display="none";
		}
	}	
		if('${symptomsLst}'!=null && '${symptomsLst}'!='')
		{
			document.getElementById("symptomsTable").style.display="";
			symptoms='${symptomsLst}'.split("#");
			symptomCount=symptoms.length-1;
		}
		
		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
		document.getElementById("genTestTableID").style.display="";
		genOldList='${genInvestLst}'.split("@");
		genOldList.splice(genOldList.length-1,1);
		if(genOldList.length>0)
			document.forms[0].patientType[1].disabled=false;
		/*genInventCount=genInventList.length-1;*/
		}
		
		if('${dentalImpactFlag}'!=null && '${dentalImpactFlag}'!=''){
			document.getElementById("impactTable").style.display="";
			dentalOldLst='${dentalImpactLst}'.split("@");
			dentalOldLst.splice(dentalOldLst.length-1,1);
			}
		if('${PatientOpList.ipOpFlag}'!=null){
			if('${PatientOpList.ipOpFlag}'=='IP'){
			document.forms[0].patientType[1].disabled=false;
			document.forms[0].patientType[1].checked=true;
			document.getElementById("ipDop").innerHTML="IP Number";
			document.getElementById('IPHead2').style.display='';
			document.getElementById("diagnosisData").style.display="";
			document.getElementById('admissionDetailsDiv').style.display='';
			getDiagDtlsOnload();
			if ('${PatientOpList.docType}'!=null){
				setTimeout(function(){fn_getIPDoctorsList();}, 1999);}
			if('${invetLst}'!='')
    		{
    			document.getElementById("categoryTableID").style.display="";
    			specOld='${invetLst}'.split("@");
    			specOld.splice(specOld.length-1,1);    			
    			if(specOld.length>0){
        			
                var specialApprovalFlag="${dentalSpecialAppvFlag}";
               if(specialApprovalFlag!=null)
        			{
        			    document.getElementById("normalProc").disabled=true;
        				
        				document.getElementById("SpecialProc").checked=true;
        			}
        			else
        			{
        				document.getElementById("SpecialProc").disabled=true;
        				
        				document.getElementById("normalProc").checked=true;
        			}
                    for(var i=0;i<specOld.length;i++){
                    	var inSpec = specOld[i].split("~");
                    	medOrSur=inSpec[0].substr(0,1);
                    }
                	
        		}  
    			/*catCount=spec.length-1;*/
    		}
			}
	        else if ('${PatientOpList.ipOpFlag}'=='OP')
	        	{
	        	document.getElementById("OPHead2").style.display="";
	    		document.getElementById("prescriptionData").style.display="";
	    		document.getElementById("OPDoctor").style.display="";
	    		document.getElementById("diagnosisData").style.display="";

	    		//document.getElementById('IPHead2').style.display='none';
				//document.getElementById("diagnosisData").style.display="none";
	    		
	    		getDiagDtlsOnload();
	    		if ('${PatientOpList.docType}'!=null){
					setTimeout(function(){fn_getDoctorsList();}, 1999);}
	    		if('${drugsLst}'!='')
	    		{
	    			document.getElementById("drugsTable").style.display="";
	    			drugs='${drugsLst}'.split("#");
	    			drugCount=drugs.length-1;
	    		}	    		
	    		}
	        else if ('${PatientOpList.ipOpFlag}'=='DOP')
        	{
	        	document.getElementById("ipDop").innerHTML="DOP Number";
	        	document.forms[0].patientType[2].disabled=false;
				document.forms[0].patientType[2].checked=true;
				document.getElementById('IPHead2').style.display='';
				document.getElementById("diagnosisData").style.display="";
				document.getElementById("admisnTypeSel").style.display="none";
				document.getElementById("admisnType").style.display="none";
				document.getElementById('admissionDetailsDiv').style.display='';
				getDiagDtlsOnload();
				if ('${PatientOpList.docType}'!=null){
					setTimeout(function(){fn_getIPDoctorsList();}, 1999);}
				if('${invetLst}'!='')
	    		{
					document.getElementById("categoryTableID").style.display="";
	    			specOld='${invetLst}'.split("@");
	    			specOld.splice(specOld.length-1,1);    			
	    			if(specOld.length>0){
	        			
	                var specialApprovalFlag="${dentalSpecialAppvFlag}";
	              	if(specialApprovalFlag!=null)
	        			{
	        			    document.getElementById("normalProc").disabled=true;
	        				
	        				document.getElementById("SpecialProc").checked=true;
	        			}
	        			else
	        			{
	        				document.getElementById("SpecialProc").disabled=true;
	        				
	        				document.getElementById("normalProc").checked=true;
	        			}
	                    for(var i=0;i<specOld.length;i++){
	                    	var inSpec = specOld[i].split("~");
	                    	medOrSur=inSpec[0].substr(0,1);
	                    }
	    				
	        		}  
	    			/*catCount=spec.length-1;*/
	    		}
    		}
			
		}
	
		function getDiagDtlsOnload()
		{
			if('${PatientOpList.diagnosisType}'!=null && '${PatientOpList.diagnosisType}'!='' && '${PatientOpList.diagnosisType}'!='-1')
			{
				getDiagnMainCatList();
				setTimeout(function()
				{		                                                     		  	           
					if('${PatientOpList.mainCatName}'!=null && '${PatientOpList.mainCatName}'!='' && '${PatientOpList.mainCatName}'!='-1')
					{
						document.getElementById("mainCatCode").value='${PatientOpList.mainCatName}';
					    document.forms[0].mainCatName.value='${PatientOpList.mainCatName}';
					    getDiagnCategoryList();
					    setTimeout(function()
						{
							if('${PatientOpList.catId}'!=null && '${PatientOpList.catId}'!='' && '${PatientOpList.catId}'!='-1')
							{
								document.getElementById("catCode").value='${PatientOpList.catId}';
						    	document.forms[0].catName.value='${PatientOpList.catId}';
						    	getDiagnSubCategoryList();
						    	setTimeout(function()
								{
									if('${PatientOpList.subCatName}'!=null && '${PatientOpList.subCatName}'!='' && '${PatientOpList.subCatName}'!='-1')
									{
										document.getElementById("subCatCode").value='${PatientOpList.subCatName}';
										document.forms[0].subCatName.value='${PatientOpList.subCatName}';
										getDiagnDiseaseList();
										setTimeout(function()
										{
											if('${PatientOpList.disMainId}'!=null && '${PatientOpList.disMainId}'!='')
											{
												document.getElementById("diseaseCode").value='${PatientOpList.disMainId}';
												document.forms[0].diseaseName.value='${PatientOpList.disMainId}';
												getDiagnDisAnatomicalList();
											}
											setTimeout(function()
											{
												if('${PatientOpList.disAnatomicalSitename}'!=null && '${PatientOpList.disAnatomicalSitename}'!=''&& '${PatientOpList.disAnatomicalSitename}'!='-1')
												{
													document.getElementById("disAnatomicalCode").value='${PatientOpList.disAnatomicalSitename}';
													document.forms[0].disAnatomicalName.value='${PatientOpList.disAnatomicalSitename}';
												}
											},1999);
										},1999);
									}
								}, 1999);
							   }
					    }, 1999);
					}
				}, 1999);
			}	
		}
enable_legalCase('onload');
enable_dtrsform();

</script>

<script>
$("#genBlockInvestName").select2();
$("#genInvestigations").select2();
$("#drugNameAuto").select2();
$("#disNameAuto").select2();
var tablength=0;
var dentalCnt=0;
var dentalNewLst= new Array();
function addImpactedDentition(){
	if(document.getElementById("impactType").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("impactType"));
	alert("Please select Type of Impact");
	partial(focusBox,document.getElementById("impactType"));
	return false;
	}
	if(document.getElementById("dentitionType").value==-1 )
	{
		var fr=partial(focusBox,document.getElementById("dentitionType"));
		alert("Please select Dentition Type");
		partial(focusBox,document.getElementById("dentitionType"));
		return false;
	}
		if(document.getElementById("teeth").selectedIndex==-1 )
		{
			var fr=partial(focusBox,document.getElementById("teeth"));
			alert("Please Select Tooth number");
			partial(focusBox,document.getElementById("teeth"));
			return false;
		}
	/*
	for(var c=0;c<oldImpactList.length;c++){
		//var symptomsValues=genInventList[c].split("~");
		if(oldImpactList[c].indexOf(document.getElementById("teeth").value) !== -1)
			{
			var fr=partial(focusBox,document.getElementById("teeth"));
			alert("Investigation Name already added.Please select another Investigation Name");
			partial(focusBox,document.getElementById("teeth"));
			return false;
			}
		}*/
	//alert(genInventList.length);
	for(var c=0;c<dentalNewLst.length;c++)
	{
	//var symptomsValues=genInventList[c].split("~");
	
	var type=dentalNewLst[c].split('~');
	
	if(type[0]==document.getElementById("impactType").value && type[1]==document.getElementById("dentitionType").value
			&& type[0]==document.getElementById("impactType").value)
	//if(dentalNewLst[c].indexOf(document.getElementById("teeth").value) !== -1)
		{
		var fr=partial(focusBox,document.getElementById("teeth"));
		jqueryErrorMsg('Unique Dental Impact Validation',"Impact for this teeth is already added.Please select another.",fr);
		partial(focusBox,document.getElementById("teeth"));
		return false;
		}
	}
	 document.getElementById('impactTable').style.display="";
	var impactTableId=document.getElementById('impactTable');
    
	var newRow=impactTableId.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td width="30%">'+tablength+'</td>';
	newcell.innerHTML='<td width="30%">'+document.getElementById("impactType").options[document.getElementById("impactType").selectedIndex].text+'</td>';
	var newcell=newRow.insertCell(1);
	newcell.innerHTML='<td width="30%">'+document.getElementById("dentitionType").options[document.getElementById("dentitionType").selectedIndex].text+'</td>';
	var newcell=newRow.insertCell(2);
	newcell.innerHTML='<td width="25%">'+document.getElementById("teeth").options[document.getElementById("teeth").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(3);
	newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+document.getElementById("impactType").value+document.getElementById("teeth").value+' id='+document.getElementById("impactType").value+document.getElementById("teeth").value+'  /></td>';
	
	var deleteButName=document.getElementById("impactType").value+document.getElementById("teeth").value;
	document.getElementById(deleteButName).onclick = function(){
		 //confirmRemoveRow(this,"geninvestigation");
		fr=partial(deleteImpactedTeeth,this,document.getElementById("teeth").value);
		jqueryConfirmMsg('Delete Impacted Tooth Confirmation',"Do you want to delete Impacted Tooth?",fr);
		 }; 
		var genInvest=document.getElementById("impactType").value+"~"+document.getElementById("dentitionType").value+"~"+document.getElementById("teeth").value;
		
		 dentalNewLst[dentalCnt]=genInvest;tablength++;dentalCnt++;
		
}
function deleteImpactedTeeth(src,investId){

    var oRow=src.parentNode.parentNode;
   dentalNewLst.splice(oRow.rowIndex-1,1);		
    document.getElementById("impactTable").deleteRow(oRow.rowIndex);
   tablength--;
	dentalCnt--;
		if(tablength==0)
			{
					//document.getElementById("opPkgName").disabled=false;
					document.getElementById('impactTable').style.display='none';
			        
			}
			//parent.fn_resizePage();
			}


function fn_showHabs(){
if(document.forms[0].childCaries.value=="P"){
	document.getElementById('dentalHabDiv1').style.display='';
document.getElementById('dentalHabDiv2').style.display='none';
	
}
else if(document.forms[0].childCaries.value=="D"){
	document.getElementById('dentalHabDiv2').style.display='';
document.getElementById('dentalHabDiv1').style.display='none';
	
}
}
</script>

<script>

 



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
</script>
<script>
function enableDrugHistory(input)
{
	 if(input =="Yes")
	 {
		 document.getElementById("drughistory").style.display="";
	 }
	 else
		 {
		 document.getElementById("drughistory").style.display="none";
		 document.getElementById("drughistory").value="";
		 }
	 
}
</script>
<%--</logic:equal>--%>
</logic:equal> 
</logic:iterate> 
</logic:notEmpty> 
</td></tr></table>
<script>
function fn_resetAll(){
	$("#patientComplaint option").remove();	
 $("input[type=text], textarea,select").val("");
 $('input[type=radio]').prop('checked', function () {
	    return this.getAttribute('checked') == false;
	});
 $('input[type=checkbox]').prop('checked', function () {
	    return this.getAttribute('checked') == false;
	});
 document.getElementById("dntsublist0").style.display="none";
 document.getElementById("subdental0").value="";
 document.getElementById("dntsublistrl0").style.display="none";
 document.getElementById("subdentalrl0").value="";
 document.getElementById("dntsublist1").style.display="none";
 document.getElementById("subdental1").value="";
 document.getElementById("dntsublistjaws1").style.display="none";
 document.getElementById("subdentaljaws1").value="";
 document.getElementById("dntsublist2").style.display="none";
 document.getElementById("subdental2").value="";
 document.getElementById("dntsublist3").style.display="none";
 document.getElementById("subdental3").value="";
 document.getElementById("dntsublistoraltd0").style.display="none";
 document.getElementById("dntsublistoraltd1").style.display="none";
 document.getElementById("dntsublistoraltd2").style.display="none";
 document.getElementById("dntsublistoraltd3").style.display="none";
 document.getElementById("dntsublistoraltd4").style.display="none";
 document.getElementById("dntsublistoraltd5").style.display="none";
 document.getElementById("dntsublistoraltd6").style.display="none";
 document.getElementById("drughistory").style.display="none";
 document.getElementById("showMedicalTextval").style.display="none";
 document.getElementById("cariesDecdious").style.display="none";
 document.getElementById("grosslyDecdious").style.display="none";
 document.getElementById("mobileDecdious").style.display="none";
 document.getElementById("missingDecdious").style.display="none";
 document.getElementById("cariesDiv").style.display="none";
 document.getElementById("rootDiv").style.display="none";
 document.getElementById("mobilityDiv").style.display="none";
 document.getElementById("attritionDiv").style.display="none";
 document.getElementById("missingDiv").style.display="none";
 document.getElementById("otherDiv").style.display="none";
 
 $("#genTestTableID tr").remove();
 document.getElementById("genTestTableID").style.display="none";
 $("#categoryTableID tr").remove();
 document.getElementById("categoryTableID").style.display="none";
}

// function compcodeonchange(){
// 	var c=document.getElementById("complaintCode").value.split("~");
// 	for(var i=0; i<c.length;i++){
// 		if(c[i]=="S18.19"){
// 			alert("Swelling");
// 		}
// 	}
// }
</script>
</form>
</body>
</div>

</fmt:bundle>
</html>
