<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}'/>  
<fmt:bundle basename="ApplicationResources">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Biometric Attendance Report</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<style type="text/css">.centerone{padding-left:6%;}</style>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">


<script src="js/jquery.msgBox.js"></script>
 

 
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<style>
.but
{
border-radius: 25px;
}
#pieView
{
height:140px;
width:160px;
max-height: 140px;
}
#empDtls
{
color:#626686;
}
#mainDiv
{
width:100%;
align:center;
}
</style>

<script>
function fn_loadImage()
{
	document.getElementById('progressBar').style.display="";
}
function fn_removeLoadingImage()  
{
	document.getElementById('progressBar').style.display='none';
}
function fn_Search()
{
	fn_loadImage();
	var fromDate=document.forms[0].fromDate.value;
	var toDate=document.forms[0].toDate.value;
	if(fromDate==null || fromDate=="" || toDate=="" || toDate==null)
	{
alert("Please Enter From Date And To Date");
return false;
	}
	document.forms[0].action="/Operations/bioMetricAction.do?actionFlag=bioMetricReport&searchFlag=Y";
	document.forms[0].method="post";
	document.forms[0].submit();
}

function fn_getDesignations()
{
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}

	var deptId=document.forms[0].deptId.value;
	if(deptId==null || deptId=="")
	{
		
		
		return false;
	}

	var url='/<%=context%>/bioMetricAction.do?actionFlag=getDsgns&deptId='+deptId;

	xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	    	 var resultArray=xmlhttp.responseText;
	    	 //alert(resultArray);
		        var resultArray = resultArray.split("*");
				if(resultArray[0]=="SessionExpired"){
		    		alert("Session has been expired");
		    		 parent.sessionExpireyClose();
		    		 //var fr = partial(parent.sessionExpireyClose);
		    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
		    		}
		    		else
		    		{
		        if(resultArray[0]!=null && resultArray[0]!="")
		        {	
                    //alert(resultArray[0]);
		            var resultArray1 = resultArray[0].replace("[","");
		            resultArray1 = resultArray1.replace("]",""); 
		            var dsgnList = resultArray1.split("@"); 
		            //var resultArray2= resultArray1.split("~");
		                 
		            //alert(resultArray2[0]);
		           
		           
		           if(dsgnList!=null && dsgnList.length>0)
		           {
		        	document.forms[0].dsgnId.options.length=0;
                   	document.forms[0].dsgnId.options[0]=new Option("---select---","");
					for(var i=0;i<dsgnList.length;i++)
					{
						var arr=dsgnList[i].split("~");
                 		if(arr[1]!=null && arr[0]!=null)
                 		{
                     		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                     		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                     		
                    	 		document.forms[0].dsgnId.options[i+1] =new Option(val1,val2);
                	 		
                 		}
					}
		           }
			            
		            
		     
		           
		        }

		        
		    		}
	    }

	}
    xmlhttp.open("Post",url,true);
	xmlhttp.send(null);	      

}


function fn_back()
{
	//window.opener.location.reload()
	//parent.parent.fn_biometricReport();
	//parent.fn_reloadPage();
	fn_Search();
	//parent.fn_Search();
}
function fn_Search()
{
	
	/**var fromDate=document.forms[0].fromDate.value;	
	var toDate=document.forms[0].toDate.value;*/

	
	fn_loadImage();
	document.forms[0].action="/Operations/bioMetricAction.do?actionFlag=bioMetricReport&searchFlag=Y";
	document.forms[0].method="post";
	document.forms[0].submit();
}
function fn_csv()
{
	var fromDate="${fromDate}";
	var toDate="${toDate}";
	
	var userId="${userId}";
	
	fn_loadImage();
	document.getElementById("csvBut").disabled=true;
	document.forms[0].action="/<%=context%>/bioMetricAction.do?actionFlag=getAttendanceReport&csvFlag=Y&userId="+userId+"&fromDate="+fromDate+"&toDate="+toDate;
	document.forms[0].method="post";
	document.forms[0].submit();
}
// end of procedures

</script>
</head>

<body onload="fn_removeLoadingImage();">
<html:form  method="post"  action="/bioMetricAction.do" > 
<!-- Modal for patient details  -->  
<div class="modal fade" id="viewDtlsID"> 
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-body">
      	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <iframe  id="complaintFrame" width="100%" height="300px" frameborder="no" scrolling="yes" > </iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 
<c:if test="${errorMsg ne null && errorMsg ne '' }">
<script>
alert(errorMsg);
</script>
</c:if>

<div id="mainDiv">

<bean:size id="attendanceReptSize" name="bioMetricForm" property="attendanceRept"/>
<div class="tbheader">

<span><b>Biometric Consolidated attendance Report</b>
</span>
</div>
<div id="back">
</div>


<div id="basicDetails">
  <div class="row">
  
  <div id="empDtls" class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
  
  <div class="row">
  
                      <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4  " >
							<label><span class="glyphicon glyphicon-cog"> </span>&nbsp;Login Name :</label>
						</div>	
						
						  <div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8  tbcellCss3   " >
							<label  id="loginName" > 
							<bean:write name="bioMetricForm" property="loginName"></bean:write>	
							</label>
                           </div>
  
  
  
  
  
  </div>
  

  
   <div class="row">
  
                      <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4  " >
							<label><span class="glyphicon glyphicon-user"> </span>&nbsp;Employee Name :</label>
						</div>	
						
						  <div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8 form-group tbcellCss3   " >
							<label  id="empName" > 
							<bean:write name="bioMetricForm" property="empName"></bean:write>	
							</label>
                           </div>
  </div>
  
     <div class="row">
  
                      <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4" >
							<label><span class="glyphicon glyphicon-briefcase"></span>&nbsp;Designation :</label>
						</div>	
						
						  <div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8 form-group tbcellCss3   " >
							<label  id="designation" > 
							<c:choose>
										<c:when test="${loggedUserState eq 'CD201' && bioMetricForm.designation eq 'Aarogya Mithra'}" >
											VAIDYA MITHRA
										</c:when>
										<c:otherwise >
											<bean:write name="bioMetricForm" property="designation"></bean:write>
										</c:otherwise>
									</c:choose>	
							</label>
                           </div>
  
  
  
  
  
  </div>
  
    <div class="row">
  
                      <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4" >
							<label><span class="glyphicon glyphicon-plus"></span>&nbsp;Hospital Name :</label>
						</div>	
						
						  <div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8 form-group tbcellCss3   " >
							<label  id="hospitalName" > 
							<bean:write name="bioMetricForm" property="hospitalName"></bean:write>	
							</label>
                           </div>
  
  
  
  
  
  </div>
  
  
  </div>
  <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
  <div id="pieView" class="col-xs-12 col-sm-12 col-md-10 col-lg-10">

  </div>
  
  <div class="col-lg-6 col-md-6 col-sm-2 col-xs-2"
															id="legendContainer"
															style="font-size: 15px; margin-top: 30px"></div>
															
  </div>
  </div>
</div>

<div  class="leftone">
<b><font color="#800808">Displaying Employee Details Present Between ${fromDate} and ${toDate}</font></b>
</div>
<div class="rightone" style="padding-right: 4%;">
<button type="button" class="but btn  btn-info " id="csvBut" title="Click Here to Download CSV Report" class="but" onclick="fn_csv()">CSV&nbsp;<span class="glyphicon glyphicon-download"></span></button>
&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="but btn  btn-warning"  title="Click Here To Go Back" onclick="fn_back();">Back&nbsp;<span class="glyphicon glyphicon-circle-arrow-left"></span></button>
</div>
<logic:greaterThan value="0" name="attendanceReptSize" >
<table  width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<tr>
	<th class="tbheader1" width="5%" valign="top">S.No</th>
	<th class="tbheader1" width="20%" valign="top">Login date</th>
	<th class="tbheader1" width="25%" valign="top">Scheduled Working Hours(HH:MM)</th>
	<th class="tbheader1" width="25%" valign="top">First Login</th>
	<th class="tbheader1" width="25%" valign="top">Last Logout</th>

 <!-- 	<th class="tbheader1" width="10%" valign="top">Hospital Name</th> -->
	
	
	</tr>
	<c:set var="counter" value="1"></c:set>
    <logic:iterate id="result" name="bioMetricForm" property="attendanceRept"   indexId="index">
	<tr> 

	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">${counter}</td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="LOGINDATE" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">09:00</td>
	
	
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="FIRSTLOGIN" /></td> 
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="LASTLOGOUT" /></td>

	<c:set var="counter" value="${counter+1}"></c:set>
	</tr>
</logic:iterate>
</table>

</logic:greaterThan>

<logic:equal value="0" name="lstBiometricReport">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >

<td align="center" height="50">
<b>No results found</b>
</td>
</tr>
</table>
</logic:equal>

<html:hidden property="rowsPerPage"  name="bioMetricForm"/>
<html:hidden property="startIndex" name="bioMetricForm" />
<html:hidden property="showPage" name="bioMetricForm" />
<html:hidden property="totalRows" name="bioMetricForm" />
<html:hidden property="userId" name="bioMetricForm" />
<html:hidden property="fromDate" name="bioMetricForm" />
<html:hidden property="toDate" name="bioMetricForm" />
<html:hidden property="distId" name="bioMetricForm" />
<html:hidden property="dsgnId" name="bioMetricForm" />
<html:hidden property="deptId" name="bioMetricForm" />



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
</div></div></div></div>
</html:form>

<script src="js/select2.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>


        <script src="js/jquery.flot.js"></script>      
        <script src="js/jquery.flot.pie.min.js"></script>  
   		<script src="js/jquery.flot.tooltip.min.js"></script>
		<script src="js/jquery.flot.resize.js"></script>
		<script src="js/jquery.flot.pie.resize.js"></script>   
  <!--   <script src="js/jquery.flot.pie.resize.js"></script>   -->   
        <script>
		var previousPoint = null,
	    previousLabel = null;
	
	var data = [
	            { label: "Present Days : "+"${loginDays}",  data: Number("${loginDays}") ,color : '#B74934'},
	            { label: "Not Logged Days : "+"${notLoginDays}",  data: Number("${notLoginDays}") ,color : '#577492'}
	            
	            
	        ];
	

	$(document).ready(function () {
		  
	  $.plot($(' #pieView'),data,{
		series:{
			pie:{
				show:true,
				innerRadius:0.0
			}
		},
		grid:{
			hoverable:true
		},
		legend:{
			container : '#legendContainer'			
		} 
		

	});  
	 $("#pieView").bind("plothover", pieHover);
	  
	  
	});
	function fn_showTooltip(x, y,color, contents) { 
        $('<div id="toolTip" style="color:#000000;font-weight:bold;">'+contents+'</div>').css( {
        	 position: 'absolute',
            display: 'none',
            top: y - 60,
            left: x-30 ,
            border: '1px solid #fdd',
            padding: '2px',
            'background-color': '#ffffff', 
            opacity: 0.80
        }).appendTo("body").fadeIn(200);
    }


	function pieHover(event, pos, item) {
		
		if (item) {
	      			  if ((previousLabel != item.series.label) || (previousPoint != item.dataIndex)) 
	      			  {
						$("#pieHover").html('<span style="font-weight: bold;color:#3B9C9C;">'+item.series.label+'</span>');		      				  
			            previousPoint = item.dataIndex;
			            previousLabel = item.series.label;
			            $("#toolTip").remove();
			
			            var x = item.datapoint[0].toFixed(2);
			            var y = item.datapoint[1];
			
			            var color = item.series.color;
	            		fn_showTooltip(pos.pageX, pos.pageY,color,item.series.label);
	            		
	            		}
					} 
		else 		
			{
			
			        $("#toolTip").remove();
		            previousPoint = null;  
			 }
	} 
        </script>
        
</body>
</fmt:bundle>
</html>
