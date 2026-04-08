<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ include file="/common/include.jsp"%>
<%-- <%@ include file="/common/includeCalendar.jsp"%> --%>
<%-- <%@ include file="/common/ScrollBar.jsp"%> --%>
<%-- <%@ include file="/common/editableComboBox.jsp"%>   --%> 
<html>
<head>
	<link rel="stylesheet" href="css/jquery.ui.core.css">
	<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
	<link rel="stylesheet" href="css/jquery.ui.theme.css">
	<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<script src="js/DateTimePicker.js"></script>	
 <style>
.ui-autocomplete-input {
    width: 10em;
}
</style> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<%-- <script src="/<%=context%>/js/jquery.msgBox.js" type="text/javascript"></script>
<link href="/<%=context%>/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/> 
<script type="text/javascript"  src="/<%=context%>/js/jquery.mCustomScrollbar.js" ></script> --%>
<script>

var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

    
	$(function() 
	{
		$( "#fromDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			dateFormat: "dd-mm-yy",
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#toDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
            dateFormat: "dd-mm-yy",
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#fromDate" ).datepicker( "option", "maxDate", selectedDate );
			}
		});

	});
	function validateDate(arg1,input)
	{
		var entered = input.value;
		var id=input.id;
		entered = entered.split("-");
		var byr = parseInt(entered[2]); 
		
		if(isNaN(byr))
		{
			input.value="";
			alert("Please Select Audited"+arg1);
		}
		else
		{
		var bmth = parseInt(entered[1],10); 
		var bdy = parseInt(entered[0],10);
		var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
		DoB=Date.parse(DoB);
		var today= new Date();
		var nowmonth = today.getMonth();
		var nowday = today.getDate();
		var nowyear = today.getFullYear();
		var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
		DoC=Date.parse(DoC);
		if(DoB>DoC)
			{
			input.value="";
			alert(arg1+" should not be greater than Today's Date");
			}
		}
	}
	
function fn_erase(arg)
{
	var id=arg.id;
	document.getElementById(id).value="";		
}
	
function fn_changeSpecs(arg)
{
	var value=document.getElementById("selectionType").value;
	
	if(value=='DEN')
		{
			document.forms[0].groupType.options.length=0;
			document.forms[0].groupType.options[0]=new Option("----Select----","");
			document.forms[0].groupType.options[1]=new Option("Group 4","G4");
		}
	if(value!='DEN')
		{
			document.forms[0].groupType.options.length=0;
			document.forms[0].groupType.options[0]=new Option("----Select----","");
			document.forms[0].groupType.options[1]=new Option("Group 1","G1");
			document.forms[0].groupType.options[2]=new Option("Group 2","G2");
			document.forms[0].groupType.options[3]=new Option("Group 3","G3");
		}
	if(value=='DEN' && document.getElementById("specialityDiv1").style.display!='none')
		{
			document.getElementById("specialityDiv1").style.display='none';
			document.getElementById("specialityDiv2").style.display="";
			
			if(arg=='N')
				fn_loadcategories('specialityDen');
		}
	if(value!='DEN' && document.getElementById("specialityDiv1").style.display=='none')
		{
			document.getElementById("specialityDiv1").style.display="";
			document.getElementById("specialityDiv2").style.display="none";
			
			if(arg=='N')
				fn_loadcategories('speciality');
		}
}
	
function fn_pagination(pageId,end_index)
{
	document.forms[0].action="./medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=no&pageId="+pageId+"&end_index="+end_index+"&backFlag=${backFlag}";
	document.forms[0].method="post";
	document.forms[0].submit();
}

function viewPreviousPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	pageDisplays=pageDisplays+'<ul class="pagination">';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var i=0;
	if(minPageNo>1)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
		//pageDisplays=pageDisplays+'<li> <span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
	}
	/* else
	{
		minPageNo=minPageNo+1;
	} */
	if(minPageNo==0)
		{
			minPageNo=1;
		}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
			//pageDisplays=pageDisplays+' <li class="active"><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
			//pageDisplays=pageDisplays+' <li><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
	//pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
	//pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
			//pageDisplays=pageDisplays+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
			//pageDisplays=pageDisplays+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		
	}
	if(i<noOfPages)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
		//pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function addTooltip(id)
{
var numOptions = document.getElementById(id).options.length;
for ( var i = 0; i < numOptions; i++)
	document.getElementById(id).options[i].title = document
					.getElementById(id).options[i].text;		

}
function fn_loadcategories(type){
	
	var typeOfSpeciality=document.getElementById(type).value;
	
	 var xmlhttp;
	 
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
		  //jqueryAlertMsgTop("Alert","Your browser does not support XMLHTTP!");
		  alert("Your browser does not support XMLHTTP!");
	  }
	  xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
				    	 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		 alert("Session has been expired");
			    		 parent.sessionExpireyClose();
				    	}
				    	else
				    	{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var catList = resultArray1.split(", @"); 
			        }
					
			        if(catList.length>0)
		            {   
						if(document.forms[0].category.options!=null){  
							document.forms[0].category.options.length=0;
							document.forms[0].category.options[0]=new Option("----Select----","");
							document.forms[0].procedure.options.length=0;
							document.forms[0].procedure.options[0]=new Option("----Select----","");
						}
						 for(var i = 0; i<catList.length;i++)
			                {	
			                    var arr=catList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].category.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                  	document.forms[0].category.options[0]=new Option("----Select----","");
										
				                    }
			                }
						  var  numOptions = document.getElementById('category').options.length; 
						 
							
							
							
			               	 for (var j = 0; j < numOptions; j++)
			               		 document.getElementById('category').options[j].title = document.getElementById('category').options[j].text;
		            }
		    	
		    }
		}
		}
	  var url='/<%=context%>/medicalAudit.do?actionFlag=getCategory&specialityType='+typeOfSpeciality;
	 xmlhttp.open("Post",url,true);
	 	xmlhttp.send(null);
	 
	
 }
function fn_loadProcedures(type){
	
	var typeOfCategory=document.getElementById(type).value;
	 var xmlhttp;
	 
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
		  //jqueryAlertMsgTop("Alert","Your browser does not support XMLHTTP!");
		  alert("Your browser does not support XMLHTTP!");
	  }
	  xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
				    	 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		 alert("Session has been expired");
			    		 parent.sessionExpireyClose();
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
						if(document.forms[0].procedure.options!=null){  
							document.forms[0].procedure.options.length=0;
							document.forms[0].procedure.options[0]=new Option("----Select----","");
						}
						 for(var i = 0; i<procList.length;i++)
			                {	
			                    var arr=procList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].procedure.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                  	document.forms[0].procedure.options[0]=new Option("----Select----","");
										
				                    }
			                }
						  var  numOptions = document.getElementById('procedure').options.length; 
						 
							
							
							
			               	 for (var j = 0; j < numOptions; j++)
			               		 document.getElementById('procedure').options[j].title = document.getElementById('procedure').options[j].text;
		            }
		    	
		    }
		}
		}
	  var url='/<%=context%>/medicalAudit.do?actionFlag=getProcedure&categoryType='+typeOfCategory;
	 xmlhttp.open("Post",url,true);
	 	xmlhttp.send(null);
	 
	
 }
 function getHospitalList(){
	 
	 var distName=document.getElementById('distName').value;
		var hospType=document.getElementById('hospType').value;
		
		 if(distName!=-1 && distName !='' && hospType!=-1 && hospType!=''){
			
			 var xmlhttp;
			 
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
				  //jqueryAlertMsgTop("Alert","Your browser does not support XMLHTTP!");
				  alert("Your browser does not support XMLHTTP!");
			  }
			  xmlhttp.onreadystatechange=function()
				{
				    if(xmlhttp.readyState==4)
				    {	
				    	 var resultArray=xmlhttp.responseText;
					        var resultArray = resultArray.split("*");
					        if(resultArray[0]=="SessionExpired"){
						    	 //var fr = partial(parent.sessionExpireyClose);
					    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
					    		 alert("Session has been expired");
					    		 parent.sessionExpireyClose();
						    	}
						    	else
						    	{
					        if(resultArray[0]!=null)
					        {	
					            resultArray1 = resultArray[0].replace("[","");
					            resultArray1 = resultArray1.replace("]","");            
					            var hospitalsList = resultArray1.split(", @"); 
					        }
							
					        if(hospitalsList.length>0)
				            {   
								if(document.forms[0].hospName.options!=null){  
									document.forms[0].hospName.options.length=0;
									document.forms[0].hospName.options[0]=new Option("----Select----","");
								}
								 for(var i = 0; i<hospitalsList.length;i++)
					                {	
					                    var arr=hospitalsList[i].split("~");
					                     if(arr[1]!=null && arr[0]!=null)
					                     {
					                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
					                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					                         document.forms[0].hospName.options[i+1] =new Option(val1,val2);
									   }
					                     else
						                    {
						                  	document.forms[0].hospName.options[0]=new Option("----Select----","");
												
						                    }
					                }
								  var  numOptions = document.getElementById('hospName').options.length; 
								 
									
									
									
					               	 for (var j = 0; j < numOptions; j++)
					               		 document.getElementById('hospName').options[j].title = document.getElementById('hospName').options[j].text;
				            }
				    	
				    }
				}
				}
			  var url='/<%=context%>/medicalAudit.do?actionFlag=gethospitals&districtCode='+distName+'&hospType='+hospType;
			 xmlhttp.open("Post",url,true);
			 	xmlhttp.send(null);
				 
				 
			 }
		 else {
			
			 
			 if((distName==-1 || distName=='') && (hospType!=-1 || hospType!='' )){
				 //alert("Please select Patient District");
			 }
			 if((distName!=-1 || distName !='')&& (hospType==-1 || hospType=='')){
				// alert("Please select Hospital Type");
			 }
		 }
		 
 }
 function resetSearch(){
	 
	 var flag = "${flag}";
	 document.getElementById('selectionType').value="";
	 document.getElementById('distName').value="";
	 document.getElementById('hospType').value="";
	 document.getElementById('groupType').value="";
	 document.getElementById('hospName').value="";
	 document.getElementById('speciality').value="";
     document.getElementById('category').value="";
	 document.getElementById('procedure').value="";
	 document.forms[0].hospName.options.length=0;
		document.forms[0].hospName.options[0]=new Option("----Select----","");
	 document.getElementById('caseNumber').value="";
 	document.getElementById('claimNumber').value="";
 document.forms[0].procedure.options.length=0;
	document.forms[0].procedure.options[0]=new Option("----Select----","");
	document.forms[0].category.options.length=0;
	document.forms[0].category.options[0]=new Option("----Select----","");
 	if(document.getElementById('schemeType')){
 		document.getElementById('schemeType').value ='';
 		
 			}
 	document.getElementById('fromDate').value="";
	document.getElementById('toDate').value="";
	document.getElementById('auditedBy').value="";
		
 	 <%-- if(flag == "worklist")
	 {
 	 document.forms[0].action = '/<%=context%>/medicalAudit.do?actionFlag=getMAworklist&backFlag=no';
	 }
 	if(flag == "auditedList"){
 		document.forms[0].action = '/<%=context%>/medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=no';
 	}
 	 document.forms[0].submit(); --%>
 }
 function focusBox(arg)
 	{
	 	aField=arg;
	 	setTimeout("aField.focus()", 0);
 	}
 function getSampleCases(){
	 
	 
	 if(document.getElementById('selectionType').value == "")
	 	{
			 alert("Please select High Volume/High Cost/Dental in Selection Type");
			 focusBox(document.getElementById("selectionType"));
			 return false;
		 }
	 
	 
	 var fromDate=document.getElementById("fromDate").value;
	 var toDate=document.getElementById("toDate").value;
	 
	 if(fromDate!=null && fromDate!='')
		 {
		 	if(toDate==null || toDate=='')
		 		{
		 			alert('From Date entered.Please select To Date to search');
		 			focusBox(document.getElementById("toDate"));
		 			return false;
		 		}
		 }
	 
	 if(toDate!=null && toDate!='')
	 {
	 	if(fromDate==null || fromDate=='')
	 		{
	 			alert('To Date entered.Please select From Date to search');
	 			focusBox(document.getElementById("fromDate"));
	 			return false;
	 		}
	 }
	 
	 var flag = "${flag}";
	 
	 if(flag == "worklist")
	 {
	 document.forms[0].action = '/<%=context%>/medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=no';
	 }
	 if(flag == "auditedList"){
		 
		 document.forms[0].action = '/<%=context%>/medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=no';
	 }
	 document.forms[0].submit();
	 
 }
 function showCase(caseId){
		//fn_loadImage();
		var fromPage= "${fromPage}"; 
			
		
		document.forms[0].action ='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&caseSearchType=caseIp&casesForApproval=N&ipOpType=IP&errSearchType=N&disSearchType=N&module=medicalAuditworklist&redirectedFrom='+fromPage;
		document.forms[0].submit();
	}
</script>
<body onload="fn_changeSpecs('Y')">
<html:form action="/medicalAudit.do" method="POST" enctype="multipart/form-data">
<table id="Search" align="center"  width="90%" cellpadding="0" cellspacing="0" class="tb" style="padding-top:0px;margin:0px auto;">
						<tr class="HeaderRow">
                                  <td width="90%" class="tbheader"   align="left"><b>Advanced Search</b></td>
					</tr>	
						<tr><td>
						<table id="table2" align="center"  width="100%" cellpadding="0" cellspacing="0"  class="tb"  >
						<tr class="NormalRow">
						<td></td>
						</tr>
						<tr class="NormalRow">
						
						
						 <td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Selection Type<span style="color: #ff0000"> *</span></td>
						<td class="tbcellBorder" style="width:20%">
						<html:select property="selectionType" name="medicalAuditForm" style="width:180px" title="Selection Type" onmouseover="addTooltip('selectionType')" onclick="javascript:fn_changeSpecs('N')" styleId="selectionType">
						 <option  value="">----Select----</option>
						<html:option value="PHV">Procedure High Volume</html:option>
						<html:option value="PHC">Procedure High Cost</html:option>
						<html:option value="DEN">Dental Cases</html:option>
						</html:select>
						
						</td> 
						<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">District</td>
						<td class="tbcellBorder" style="width:20%"><html:select property="distName" name="medicalAuditForm" title="Patient District" onmouseover="addTooltip('distName')" onchange="getHospitalList()" style="width:180px" styleId="distName">
						 <option  value="">----Select----</option>
						 <html:options collection="districtCombo" labelProperty="VALUE" property="ID"/>
						</html:select></td> 
						<td class="labelheading1 tbcellCss" style="padding-left:5px;width:10%">Hospital Type</td>
                        <td class="tbcellBorder" style="width:20%">
                        <html:select property="hospType" name="medicalAuditForm" style="width:180px" title="Hospital Type" onmouseover="addTooltip('hospType')" onchange="getHospitalList()" styleId="hospType">
						 <option  value="">----Select----</option>
						<html:option value="C">Corporate</html:option>
						<html:option value="G">Government</html:option>
						</html:select>
						</td>
						
						
						</tr>
						<tr class="NormalRow">
					 <td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Group Type</td>
						<td class="tbcellBorder" style="width:20%">
						<html:select property="groupType" name="medicalAuditForm" style="width:180px" title="Group Type" onmouseover="addTooltip('groupType')" styleId="groupType">
						 <option  value="">----Select----</option>
						<%--    <html:option value="G1">Group 1</html:option>
								<html:option value="G2">Group 2</html:option>
								<html:option value="G3">Group 3</html:option>
								<html:option value="G4">Group 4</html:option> --%>
						</html:select>
						
						</td> 
						<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Hospital Name</td>
						<td class="tbcellBorder" style="width:20%"><html:select property="hospName" name="medicalAuditForm" title="Hospital Name" onmouseover="addTooltip('hospName')"  style="width:180px" styleId="hospName">
						 <option  value="">----Select----</option>
						 <html:options collection="hospCombo" labelProperty="VALUE" property="ID"/> 
						</html:select></td> 
				
				<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Speciality</td>
						<td class="tbcellBorder" style="width:20%" >
						<div id="specialityDiv1"><html:select property="speciality" name="medicalAuditForm" title="Speciality" onmouseover="addTooltip('speciality')"  onchange="fn_loadcategories('speciality');" style="width:180px" styleId="speciality">
						 <option  value="">----Select----</option>
						 <html:options collection="specialityCombo" labelProperty="VALUE" property="ID"/> 
						</html:select>
						</div>
						<div id="specialityDiv2" style="display:none;"><html:select property="specialityDen" name="medicalAuditForm" title="Speciality" onmouseover="addTooltip('speciality')"  onchange="fn_loadcategories('specialityDen');" style="width:180px" styleId="specialityDen">
						 <option  value="">----Select----</option>
						 <html:option value="S18">DENTAL SURGERY</html:option>
						</html:select>
						</div>
						</td> 
						 
                       </tr>
                       <tr class="NormalRow">
					 <td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Category</td>
						<td class="tbcellBorder" style="width:20%">
						<html:select property="category" name="medicalAuditForm" style="width:180px" title="Category" onmouseover="addTooltip('category')" onchange="fn_loadProcedures('category');" styleId="category">
						 <option  value="">----Select----</option>
						 <html:options collection="categoryCombo" labelProperty="VALUE" property="ID"/> 
						</html:select>
						
						</td> 
						<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Procedure</td>
						<td class="tbcellBorder" style="width:20%"><html:select property="procedure" name="medicalAuditForm" title="Procedure" onmouseover="addTooltip('procedure')"  style="width:180px" styleId="procedure">
						 <option  value="">----Select----</option>
						  <html:options collection="procedureCombo" labelProperty="VALUE" property="ID"/> 
						</html:select></td> 
				
				<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Case Number:</td>
						<td class="tbcellBorder" style="width:20%"><html:text property="caseNumber"  name="medicalAuditForm" title="Case Number" styleId ="caseNumber" style="width:170px"/></td> 
                       </tr>
                       <tr class="NormalRow">
						
						
						 
						<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Claim Number:</td>
						<td class="tbcellBorder" style="width:20%"><html:text property="claimNumber"  name="medicalAuditForm" title="Claim Number" styleId ="claimNumber" style="width:170px"/></td> 
						<logic:equal name="medicalAuditForm"  property="showScheme" value="show"> 
						<td style="width:15%" class="labelheading1 tbcellCss">Scheme:</td>
					 <td class="tbcellBorder" width="12%"><html:select property="schemeType" name="medicalAuditForm" style="width:180px" title="Type of Scheme"  styleId="schemeType">
						 <option  value="">----Select----</option>
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						<html:option value="CD203">Both</html:option> 
						</html:select></td>
						</logic:equal> 
						<td style="padding-left:5px;width:15%;" class="labelheading1 tbcellCss">Audited By:</td>
						<td class="tbcellBorder" style="width:20%"><html:select property="auditedBy" name="medicalAuditForm" title="Please Select Audited By" onmouseover="addTooltip('auditedBy')"  style="width:180px" styleId="auditedBy">
						 <option  value="">----Select----</option>
						  <<html:option value="DEO">DEO</html:option>
						  <<html:option value="JEO">JEO</html:option>
						  <<html:option value="CMA">CMA</html:option>
						</html:select></td> 
						</tr>
                      <tr>
                      		<td class="labelheading1 tbcellCss" style="width:15%;">Audited From Date</td>
                      		<td class="tbcellBorder" style="width:20%;">
                      		<div id='fromDt' style="width:100%;display:inline-block;">
								<html:text name="medicalAuditForm" property="fromDate"  styleId="fromDate" readonly="true" title="Enter From Date" onchange="validateDate('From Date',this)" style="width:117px" />
								<img id="erase1" src="images/eraser.gif" style="float:right;" title="Click To Erase From Date" style=cursor:hand; width="25" height="25" alt="Erase" onclick="javascript:fn_erase(fromDate)" ></img>
							</div>
							</td>	
							<td class="labelheading1 tbcellCss" style="width:15%;">Audited To Date</td>
							<td class="tbcellBorder" style="width:20%;">
							<div id='fromDt' style="width:100%;display:inline-block;">
							<html:text name="medicalAuditForm" property="toDate" styleId="toDate" readonly="true" title="Enter To Date" onchange="validateDate('To Date',this)" style="width:117px" />
							<img id="erase2" src="images/eraser.gif" title="Click To Erase To Date" style=cursor:hand; width="25" height="25" alt="Erase" onclick="javascript:fn_erase(toDate)" ></img>
							</div>
							</td>
					  </tr>
                      <tr></tr>
                      
                       <tr class="NormalRow">
                                   <td colspan="9" align="center"><button class="but"  id="search" name="search" tabindex="0" type="button" onClick="getSampleCases();" title="Search" >Search</button>
                                  <button class="but"  id="reset" name="reset" tabindex="0" type="button"  title="Reset"   onClick="resetSearch();">Reset</button></td>   
                       </tr>
						
					</table>
					</td>
					</tr>
					</table>
					
					<table width="100%" style="padding-top: 0px; margin: 0px auto;">
<tr>
<td >

<logic:present name="medicalAuditForm"  property="auditList">
<bean:size id="size" name="medicalAuditForm" property="auditList"/>
<bean:define id="auditListPag" name="medicalAuditForm" property="auditList" />
<logic:greaterThan name="size" value="0">

<c:if test="${noOfRecords ne 0}">
	<table width="100%" border="0" align="center">
	<tr>
		 <!-- <td> -->
		<%-- 	<display:table name="${deathCasesList}" id="rowId" pagesize="10" style="width:100%;align:center;" export="false" requestURI="/casesSearchAction.do?actionFlag=viewDeathCases" cellpadding="1" cellspacing="1"> --%>
	<td width="25%">Total No of records found:${noOfRecords}</td>	 
	<td width="25%">Displaying records from ${start_index+1} to ${endresults}</td>
	<td align="center" colspan="1" id="pageNoDisplay"  style="width:25%">
&nbsp;&nbsp;&nbsp;
<%
int noOfPages = ((Integer) request.getAttribute("lastPage")).intValue();
int selectedPage = ((Integer) request.getAttribute("pageId")).intValue();
int end_index=((Integer) request.getAttribute("end_index")).intValue();
int pageNo=0;
int a=selectedPage/10;
int minVal=(a*10);
if(selectedPage%10==0)
	{
		minVal=minVal-10;
	}
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages;
}
if(minVal>=10&&minVal!=noOfPages)
	{
		minVal=minVal+1;
		%>
		<a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Prev</a>&nbsp;
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<=maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
			<b><%=pageNo%></b>
			<%
		}
		else
		{
			%>
			<a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><b><%=pageNo%></b></a>&nbsp;
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		<a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Next</a>
		<%
	}
		%>
		</td>
		<td width="25%">Show results in sets of <c:if test="${end_index ne 10}"><a href="javascript:fn_pagination(1,10)">10</a></c:if>
												<c:if test="${end_index eq 10}">10</c:if>	
												<c:if test="${end_index ne 20}"><a href="javascript:fn_pagination(1,20)">20</a></c:if>
												<c:if test="${end_index eq 20}">20</c:if>
												<c:if test="${end_index ne 50}"><a href="javascript:fn_pagination(1,50)">50</a></c:if>
												<c:if test="${end_index eq 50}">50</c:if>
												<c:if test="${end_index ne 100}"><a href="javascript:fn_pagination(1,100)">100</a></c:if>
												<c:if test="${end_index eq 100}">100</c:if></td>
	</tr>	
	</table>
	</c:if>
	<c:if test="${noOfRecords ne 0}">
	<table width="100%" border="0" align="center">

<%int i = ((Integer) request.getAttribute("start_index")).intValue()+1;%>



<%-- <%int i = 1;%>  
  <display:table style="padding-left:20px"   name="medicalAuditForm.auditList" id="rowId" requestURI="/medicalAudit.do"  pagesize="5" export="false">
                <display:caption>
                <display:setProperty name="export.export name.include_header">true</display:setProperty>
				<display:setProperty name="basic.show.header">false</display:setProperty>
							<thead>--%>
								<tr>
									<td class="tbheader1" ><b>SNO</b></td>
									<td class="tbheader1" ><b>CASE NO</b></td>
									<td class="tbheader1" ><b>CARD NUMBER</b></td>
									<td class="tbheader1" ><b>PATIENT NAME</b></td>
									<td class="tbheader1" ><b>DISTRICT</b></td>
									<td class="tbheader1" ><b>HOSPITAL NAME</b></td>
									<td class="tbheader1" ><b>GROUP</b></td>
									<td class="tbheader1" ><b>SPECIALITY</b></td>
									<td class="tbheader1" ><b>CASE TYPE</b></td>
									<%-- <c:if test="${selectType eq 'PHC'}">
									<td class="tbheader1" >COST AMOUNT</td>
									</c:if>
									<c:if test="${selectType eq 'PHV'}">
									<td class="tbheader1" >PROCEDURE</td>
									</c:if> --%>
								</tr>
				<%-- 					</thead>
                </display:caption> --%>
                
                
               <%--  <display:column  class="tbcellBorder"  value="<%=i++ %>" title="S.No" style="text-align:center;"/>
              
                 
                <display:column class="tbcellBorder"  title="Case No"  property="CASENO" style="text-align:center;"/> 
                 <display:column  class="tbcellBorder" title="Case No"  style="text-align:center;"><a href="javascript:showCase('${rowId.CASEID}');" title="Click to View Case Details"><c:out value="${rowId.CASENO}"/></a>
               </display:column>
                <display:column class="tbcellBorder" title="RATION CARD NUMBER" property="RATIONCARDNO" style="text-align:center;"/>
                <display:column  class="tbcellBorder"  title="PATIENT NAME" property="PATIENTNAME" style="text-align:center;"/>
                 <display:column  class="tbcellBorder"  title="DISTRICT" property="DISTRICTNAME" style="text-align:center;"/>
                  <display:column  class="tbcellBorder"  title="HOSPITAL NAME" property="HOSPITALNAME" style="text-align:center;"/>
                   <display:column  class="tbcellBorder"  title="GROUP" property="GRP" style="text-align:center;"/>
                   <display:column  class="tbcellBorder"  title="SPECIALITY" property="SPECIALITYNAME" style="text-align:center;"/>
               <display:column  class="tbcellBorder"  title="CASE TYPE" property="caseType" style="text-align:center;"/>
                 <%--  <c:if test="${selectType eq 'PHC'}">
                   <display:column  class="tbcellBorder"  title="COST AMOUNT" property="CLAIMAMOUNT" style="text-align:center;"/>
               </c:if>
               <c:if test="${selectType eq 'PHV'}">
                   <display:column  class="tbcellBorder"  title="PROCEDURE NAME" property="PROCEDURENAME" style="text-align:center;"/>
               </c:if>
               </display:table> --%>
             <logic:iterate id="auditList" name="auditListPag">
                 <tr>
            <td class="tbcellBorder"><%=i++ %></td>
			<td class="tbcellBorder"><a href="javascript:showCase('${auditList.CASEID}');" title="Click to View Case Details"><c:out value="${auditList.CASENO}"/></a>
			<td class="tbcellBorder"><bean:write name="auditList" property="RATIONCARDNO"/>	</td>
            <td class="tbcellBorder"><bean:write name="auditList" property="PATIENTNAME"/>	</td>
            <td class="tbcellBorder"><bean:write name="auditList" property="DISTRICTNAME"/>	</td>
            <td class="tbcellBorder"><bean:write name="auditList" property="HOSPITALNAME"/>	</td>
            <td class="tbcellBorder"><bean:write name="auditList" property="GRP"/>	</td>
            <td class="tbcellBorder"><bean:write name="auditList" property="SPECIALITYNAME"/>	</td>
            <td class="tbcellBorder"><c:if test="${auditList.GRP eq 'G4'}">DENTAL</c:if>
            						 <c:if test="${auditList.GRP ne 'G4'}"><bean:write name="auditList" property="caseType"/></c:if></td>
            </tr>
            </logic:iterate>
</table>
</c:if>

</logic:greaterThan>
 <logic:equal name="size" value="0">
 <table width="100%" >
 <tr>
 <td class="tbcell" width="100%" align="center"><b>No Records Found</b></td>
 </tr>
 <tr><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td></tr>
   <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
     <tr><td>&nbsp;</td></tr>
     <tr><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td></tr>
   <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
     <tr><td>&nbsp;</td></tr>
 </table>
 </logic:equal>
</logic:present>
</td>
</tr>
</table>
					</html:form>
</body>
</html>