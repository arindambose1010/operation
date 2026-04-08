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
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
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
#mainDiv
{
width:100%;
align:center;
}
</style>

<script>
function fn_loadImage()
{
	//document.getElementById('progressBar').style.display="";
	$(function () { $('#progressBar').modal({
		backdrop : 'static',
		keyboard : false,
		show: true
	   })});
}
function onload()
{
	document.forms[0].fromDate.value="${fromDate}";
	document.forms[0].toDate.value="${toDate}";
}
function fn_removeLoadingImage()  
{
	document.getElementById('progressBar').style.display='none';
}

function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	fn_Search();
	}
function showinSetsOf(num)
{
	document.forms[0].fromDate.value="${fromDate}";
	document.forms[0].toDate.value="${toDate}";
document.forms[0].rowsPerPage.value=num; 
document.forms[0].showPage.value='1'; 
fn_Search();
}
//parse a date in mm/dd/yyyy format
function parseDate(input) {
  var parts = input.split('/');
  // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
  return new Date(parts[2], parts[0], parts[1]-1); // Note: months are 0-based
}
function fn_Search()
{
	
	var fromDate=document.forms[0].fromDate.value;	
	var toDate=document.forms[0].toDate.value;
	
	//alert(fromDate);
	//alert(toDate);
	if(fromDate==null || fromDate=="" || toDate=="" || toDate==null)
	{
		document.getElementById("dateAlert").style.display="";
//alert("Please Enter From Date And To Date");
return false;
	}
	if(document.forms[0].stateType.value!="-1" && document.forms[0].stateType.value=="O"){
		if(parseDate(fromDate)>=parseDate("${distBifurcationDate}") || parseDate(toDate)>=parseDate("${distBifurcationDate}")){
			alert("Please select from date and to date before "+'${distBifurcationDate}'+" for district type Old");
		return false;
		}
		}
		else if(document.forms[0].stateType.value!="-1" && document.forms[0].stateType.value=="N"){
			if(parseDate(fromDate)<parseDate("${distBifurcationDate}") || parseDate(toDate)<parseDate("${distBifurcationDate}")){
				alert("Please select from date and to date on or after "+"${distBifurcationDate}"+" for district type new");
			return false;
			}
		}
// 	if(validateBifurDate())
// 		{
	document.getElementById("searchBtn").disabled=true;
	fn_loadImage();
	document.forms[0].action="/Operations/bioMetricAction.do?actionFlag=bioMetricReport&searchFlag=Y";
	document.forms[0].method="post";
	document.forms[0].submit();
// 		}
}
function validateBifurDate()
{
	var stateType = document.getElementById('stateType').value;
	if(stateType== -1){
		alert("Please select District Type first.");
		return false;
	}
	
	var distBifurcationDate = '${distBifurcationDate}';
	var fromDate=document.forms[0].fromDate.value;	
	var toDate=document.forms[0].toDate.value;
	
	
	
	var from = fromDate.split("/");
	var f = new Date(from[2], from[0]-1 , from[1]);
	
	var to = toDate.split("/");
	var t = new Date(to[2], to[0] - 1, to[1]);
	
	
	var bifurcationDate = distBifurcationDate.split("/");
	var bd = new Date(bifurcationDate[2], bifurcationDate[1] - 1, bifurcationDate[0]);
	
	
	if(f>t)
		{
		alert("From date should be less than to date");
		return false;
		}
	
	if(stateType=="O"){
		
		
		if(!(t < bd)){
			alert("For district type Old To date should less than "+distBifurcationDate);
			return false;
		}
		
		
	}
	else if(stateType=="N"){
		
		if((f < bd)){
			alert("For district type New From date should greater than or equal to "+distBifurcationDate);
			return false;
		}
	}
	
	return true;
}
function fn_reset()
{
	//alert(document.forms[0].deptId.value);
	//document.forms[0].reset();
	$("#deptId").select2('val','');
	$("#dsgnId").select2('val','');
	$("#distId").select2('val','');
	document.forms[0].fromDate.value="";
	document.forms[0].toDate.value="";
	
	
}
function fn_keyPress(id)
{
	document.getElementById(id).value="";
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
function fn_viewAttendance(userId,present,absent)
{
	var fromDate="${fromDate}";
	var toDate="${toDate}";
	
	var userId=userId;
	
	
	document.forms[0].loginDays.value=present;
	document.forms[0].notLoginDays.value=absent;
	fn_loadImage();
	document.forms[0].action="/<%=context%>/bioMetricAction.do?actionFlag=getAttendanceReport&userId="+userId+"&fromDate="+fromDate+"&toDate="+toDate;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function fn_reloadPage()
{
	alert("3");
location.reload();
}

function fn_csv()
{
	var fromDate="${fromDate}";
	var toDate="${toDate}";
	
	var userId="${userId}";
	
	//fn_loadImage();
	document.getElementById("csvBut").disabled=true;
	document.forms[0].action="/Operations/bioMetricAction.do?actionFlag=bioMetricReport&searchFlag=Y&csvFlag=Y";
	document.forms[0].method="post";
	document.forms[0].submit();
}
// end of procedures

function stateSelected()
{
	var state=null;
	var lStrHdrId='LH6';
	state="S35";
	var stateType = document.getElementById("stateType").value;
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var districtList = resultArray.split(","); 
            		if(districtList.length>0)
            		{  
            			document.forms[0].distId.options.length=0;
            			document.forms[0].distId.options[0]=new Option("---select---","-1");
            			for(var i = 0; i<districtList.length;i++)
            			{	
            				var arr=districtList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					document.forms[0].distId.options[i+1] =new Option(val1,val2);
            				}
            			}
            		}
            	}
            }
        }
    }
	url = "./bioMetricAction.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state+"&stateType="+stateType;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
</script>
</head>

<body onload="fn_removeLoadingImage();onload();">
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
var errorMsg="${errorMsg}";
document.getElementById("msgAlert").style.display="";
</script>
</c:if>

<div id="mainDiv">
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         <span class="glyphicon glyphicon-plus"></span>

			<span><b>Biometric Attendance Report</b></span>

        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in"> 
      <div class="panel-body">
      
      <div class="row">
                        <div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<span style="color:#577492" class="glyphicon glyphicon-briefcase"></span><label>&nbsp;Department :</label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >	
							<html:select  name="bioMetricForm" property="deptId" styleId="deptId" style="width:140px; " onchange="fn_getDesignations();" title="Please select Department">
		                    <option  value="">----Select----</option>
		                    <html:options collection="deptLst" property="deptId" labelProperty="deptName" />
		                    </html:select>
						</div>
						
						
						<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<span style="color:#577492" class="glyphicon glyphicon-user"> </span><label>&nbsp;Designation :</label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<html:select  name="bioMetricForm" property="dsgnId" styleId="dsgnId" style="width:140px; " title="Please select Designation">
		                    <option  value="">----Select----</option>
		                    <html:options collection="dsgnLst" property="dsgnId" labelProperty="dsgnName"/>
		                   
		                    </html:select>
						</div>
						
							<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  ">
								<span  style="color:#577492" class="glyphicon glyphicon-map-marker"></span><label>&nbsp;District Type:</label>
							</div>
							<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  ">
								<html:select name="bioMetricForm" property="stateType" styleId="stateType" onchange="stateSelected()" style="width:140px; ">
									<option value="-1">----select----</option>
									<html:option value="O">Old Districts</html:option>
									<html:option value="N">New Districts</html:option>
								</html:select>
							</div>
						
						
						
							
							</div>
							
	<div class="row">
	
	<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<span  style="color:#577492" class="glyphicon glyphicon-map-marker"></span><label>&nbsp;District :</label>
						</div>
	
	<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >	
							 <html:select  name="bioMetricForm" property="distId" styleId="distId" style="width:140px; "  title="Please select District">
		                     <option  value="-1">----Select----</option>
		                     <html:options collection="districtList" property="ID" labelProperty="VALUE"  />
		                     </html:select>
						</div>
	
	<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
	<span  style="color:#577492"  class="glyphicon glyphicon-calendar"></span><label>&nbsp;From Date :<font color="red">*</font></label>
	</div>
	<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
	
	<div class='input-group date' id='datetimepicker1'>
                    <input type='text' id="fromDate" name="fromDate"  class="form-control" style="cursor:pointer;" onkeypress="fn_keyPress('fromDate')" />
                    <span class="input-group-addon" style="cursor:pointer;">
                        <span  style="color:#577492"   class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
	
	</div>
	
	<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
	<span style="color:#577492" class="glyphicon glyphicon-calendar"></span><label>&nbsp;To Date :<font color="red">*</font></label>
	</div>
	<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
	
	<div class='input-group date' id='datetimepicker2'>
                    <input type='text' id="toDate" name="toDate"  class="form-control" style="cursor:pointer;" onkeypress="fn_keyPress('toDate')" />
                    <span class="input-group-addon" style="cursor:pointer;">
                        <span  style="color:#577492"   class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
	
	</div>
	
	</div>
	
	<div class="row">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12"  align="center">
	<button class="but btn btn-primary"   type="button" id="searchBtn" name="Search" value="Search" onclick="javascript:fn_Search()"  ><span class="glyphicon glyphicon-zoom-out"></span>&nbsp;Search</button>
	<button class="but btn btn-danger"   type="button" id="resetBtn" name="Reset" value="Reset" onclick="javascript:fn_reset()"  ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reset</button>
	</div>
	</div>
      
      
      <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <div class="alert alert-block alert-danger fade in" id="msgAlert" style="display:none;word-wrap: break-word;">
                                 <a class="close" onclick="$('#msgAlert').hide()">×</a>  
                              
                                <strong> <b><c:out value="${errorMsg}" /></b>  </strong>
                            </div> 

<div class="alert alert-block alert-danger   fade in" id="dateAlert" style="display:none;">
                                <a class="close" onclick="$('#dateAlert').hide()">×</a>  
                                <strong>From Date and To Date are Mandatory.Please Enter</strong>
                            </div>  
                            </div>
      
      
      
      </div>
      
      
      
      
      
      
      
      
      
      
      
      
      
 






      </div>
    </div>
  </div>

<div class="clearfix"></div>

<logic:present name="bioMetricForm" property="lstBiometricSearch">
<bean:size id="lstBiometricReport" name="bioMetricForm"  property="lstBiometricSearch"/>




<div  class="leftone">
<ul class="pagination">
<logic:greaterThan value="0" name="lstBiometricReport">
<li class="lispacing">Showing Results</li>
<li class="lispacing"><bean:write name="bioMetricForm" property="startIndex" />  - <bean:write name="bioMetricForm" property="endIndex" /> 
of <bean:write name="bioMetricForm" property="totalRows" /> </li>
</ul></div>

<div id="pageNoDisplay" class="centerone">

<ul class="pagination"> 


<%
int noOfPages = ((Integer) request.getAttribute("liTotalPages")).intValue();
int selectedPage = ((Integer) request.getAttribute("liPageNo")).intValue();
int a=selectedPage/10;
int pageNo=0;
int minVal=(a*10);
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages+1;
}
if(minVal>=10)
	{
		%>
		
		<li><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>)" style="cursor: pointer;"></span></a></li>
		
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
		<li class="active"><span><%=pageNo%></span></li> 
			<%
		}
		else
		{
			%>
			<li><a href="javascript:showPagination(<%=pageNo%>)"><%=pageNo%></a></li>
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		
		<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>)" style="cursor: pointer;"></span></li>
		
		<%
	}
%>









</ul></div>
<div class="rightone">
<ul class="pagination">
<li class="lispacing">Show in sets of</li>
<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${lStrRowsperpage eq set }" >
<li class="active"><span><c:out value="${set}" /></span></li> 
</c:if>
<c:if test="${lStrRowsperpage ne set }" >
<li><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
</c:if>
</c:forEach>

</ul>
&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="but btn  btn-info " id="csvBut" title="Click Here to Download CSV Report" class="but" onclick="fn_csv()">CSV&nbsp;<span class="glyphicon glyphicon-download"></span></button>
</logic:greaterThan>
</div>


<!--  
<div id="csvDiv" style="clear:both;float:right;margin-right:25px;">
<img id="




Img" src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCSV()"/>
</div> -->
<logic:greaterThan value="0" name="lstBiometricReport">
<table  width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<tr>
	<th class="tbheader1" width="5%" valign="top">S.No</th>
	<th class="tbheader1" width="8%" valign="top">Login Name</th>
	<th class="tbheader1" width="18%" valign="top">Employee Name</th>
	<th class="tbheader1" width="10%" valign="top">Designation</th>
	<th class="tbheader1" width="25%" valign="top">Hospital Name</th>
	<th class="tbheader1" width="20%" valign="top">Mac ID</th>
	<th class="tbheader1" width="6%" valign="top">Logged In Days</th>
	<th class="tbheader1" width="8%" valign="top">Not Logged In Days</th>
 <!-- 	<th class="tbheader1" width="10%" valign="top">Hospital Name</th> -->
	
	
	</tr>
	<c:set var="counter" value="1"></c:set>
    <logic:iterate id="result" name="bioMetricForm"  property="lstBiometricSearch" indexId="index">
	<tr> 

	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">${counter}</td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="LOGINNAME" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="empName" /></td>
	
	
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><c:choose>
										<c:when test="${loggedUserState eq 'CD201' && result.DESIGNATION eq 'Aarogya Mithra'}" >
											VAIDYA MITHRA
										</c:when>
										<c:otherwise >
											<bean:write name="result" property="DESIGNATION" />
										</c:otherwise>
									</c:choose></td> 
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="hospitalName" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="hospMacId" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;" align="center"><b><a href="#" onclick="javascript:fn_viewAttendance('<bean:write name="result" property="userId"/>' ,'<bean:write name="result" property="loginDays" />','<bean:write name="result" property="notLoginDays" format="######"/>')" title="Click Here To View detailed Report"><bean:write name="result" property="loginDays" /></a></b></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="notLoginDays" format="######"/></td>
	<c:set var="counter" value="${counter+1}"></c:set>
	</tr>
</logic:iterate>
</table>

</logic:greaterThan>

<logic:equal value="0" name="lstBiometricReport">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >

<td align="center" height="50">
<font color="#1560A1"><b><span class="glyphicon glyphicon-warning-sign"></span>&nbsp;No results found</b></font>
</td>
</tr>
</table>
</logic:equal>
</logic:present>
<html:hidden property="rowsPerPage"  name="bioMetricForm"/>
<html:hidden property="startIndex" name="bioMetricForm" />
<html:hidden property="showPage" name="bioMetricForm" />
<html:hidden property="totalRows" name="bioMetricForm" />
<html:hidden property="userId" name="bioMetricForm" />
<html:hidden property="loginDays" name="bioMetricForm" />
<html:hidden property="notLoginDays" name="bioMetricForm" />
<!--<html:hidden property="fromDate" name="bioMetricForm" />
<html:hidden property="toDate" name="bioMetricForm" />
<html:hidden property="distId" name="bioMetricForm" />
<html:hidden property="dsgnId" name="bioMetricForm" />
<html:hidden property="deptId" name="bioMetricForm" />  -->




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



<script>
$("#deptId").select2();
$("#dsgnId").select2();
$("#distId").select2();
</script>

   <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datepicker(
                {
                todayHighlight:true,
                autoclose:true,
                clearBtn:true
                
                
            });});
            $(function () {
                $('#datetimepicker2').datepicker(
                {
                todayHighlight:true,
                autoclose:true,
                clearBtn:true
                });  
            });
        </script>
        <script>
        
        $('#bioMetricForm').formValidation({
        	framework: 'bootstrap',
        	icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {

            	fromDate: {
                validators: {
                    notEmpty: {
                        message: 'From Date is Mandatory'
                    }
                    
                }
            },

               toDate:{
                validators: {
                notEmpty: {
                message:'To Date is Mandatory'
            }
            }
            }
            }
        });
        
        </script>
        
</body>
</fmt:bundle>
</html>
