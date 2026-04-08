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

<title>Panel Doctor Details</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
<link href="css/select2.min.css" rel="stylesheet">
<style type="text/css">.centerone{padding-left:6%;}</style>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">


<script src="js/jquery.msgBox.js"></script>
 
<style>
.modal-header
{
background-color:#0286AD;
}
html, body {
    max-width: 100%;
    overflow-x: hidden;
}
#note
{
text-align:left;
}
a:visited {
    text-decoration: none;
}
</style>
 
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



<script src="js/select2.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>



<script>

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
    /* var resultMsg="${result}";
  
    alert(resultMsg);
     if(resultMsg=="Panel Doctor account details saved successfully"){
    	 window.reload();
     } */
});

        $('#panelDocForm').formValidation({
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
  

        
function fn_loadImage()
{
	//document.getElementById('progressBar').style.display="";
	$(function () { $('#progressBar').modal({
		backdrop : 'static',
		keyboard : false,
		show: true
	   })});
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

document.forms[0].rowsPerPage.value=num; 
document.forms[0].showPage.value='1'; 
fn_Search();
}
function fn_Search()
{
	
	
	document.getElementById("searchBtn").disabled=true;
	fn_loadImage();
	var loginname=document.forms[0].loginName.value.toUpperCase();;
	var empname=document.forms[0].empName.value;
	
	document.forms[0].action="/<%=context%>/panelDoc.do?actionFlag=panelDocSearch&searchFlag=Y&loginname="+loginname+"&empname="+empname;
	document.forms[0].method="post";
	document.forms[0].submit();
	
}
function fn_reset()
{
	//alert(document.forms[0].deptId.value);
	//document.forms[0].reset();
	document.forms[0].loginName.value="";
	document.forms[0].empName.value="";
	return false;
	//fn_Search();
	
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


function fn_pnlDocDetails(userId)
{
	//alert(userId);
	var userId=userId;
	var url="/<%=context%>/panelDoc.do?actionFlag=panelDocDetails&newAccount=N&userId="+userId;
	document.getElementById("panelDocDtls").src=url;
}
function hideModal()
{
	$('#panelDocDetails').modal('hide');
	//fn_Search();
}

function  searchPage(){
	//alert("hi");
	document.forms[0].action="/<%=context%>/panelDoc.do?actionFlag=panelDocSearch";
	document.forms[0].method="post";
	document.forms[0].submit();
}

// end of procedures


	/* var result= '${resultMsg}';
	if(result !=null )
	{
		alert(result);
		parent.fn_pnlDocBankDtls();
	} */
	
	

</script>



</head>

<body onload="fn_removeLoadingImage();">
<form  method="post"  action="/panelDoc.do" name="panelDocForm" id="panelDocForm"  enctype="multipart/form-data"> 
<logic:notEmpty name="panelDocForm" property="result">
    <script  type="text/javascript">
    var x = '${panelDocForm.result}';
    alert(x);
    searchPage();
    </script>
    </logic:notEmpty>
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

<%-- <c:if test="${resultMsg ne null && resultMsg ne '' }">
<script>
var errorMsg="${resultMsg}";
alert(errorMsg);
parent.fn_pnlDocBankDtls();

//document.getElementById('msgAlert').style.display='';
</script>
</c:if> --%>

<div id="mainDiv">

<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         <span class="glyphicon glyphicon-plus"></span>

			<span><b>Panel Doctors Bank Account Details</b></span>

        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in"> 
      <div class="panel-body">
      
      <div class="row">
      <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12"  align="center">
                        <div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<span style="color:#577492" class="glyphicon glyphicon-cog"></span><label>&nbsp;Login Name :</label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >	
							<input type="text" class="form-control"  data-toggle="tooltip" data-placement="bottom" id="loginName" title="Login Name" name="loginName"></input>
						</div>
						
						
						<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<span style="color:#577492" class="glyphicon glyphicon-pencil"> </span><label>&nbsp;Employee Name :</label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2  " >
							<input type="text" class="form-control"  data-toggle="tooltip" data-placement="bottom" id="empName"  title="Employee Name" name="empName"></input>
						</div>
						
						<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<button class="btn btn-primary"   type="button"  rel="tooltip" data-placement="bottom" id="addBtn" name="addBtn" title="Click Here To Add New Account" value="add" data-toggle="modal" data-target="#panelDocDetails" onclick="javascript:fn_pnlDocDetails('');"  ><span class="glyphicon glyphicon-user"></span>&nbsp;Add New Account
						&nbsp;<span class="glyphicon glyphicon-plus"></span>
						</button>
						
						</div>
							
							</div>
							
	
	
	</div>
	
	<div class="row">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12"  align="center">
	
<!-- 	<button id="newSearch" onClick="fn_Search();">Search</button> -->
	<button class="but btn btn-info" type="button" id="searchBtn" name="Search" value="Search"  data-toggle="tooltip" data-placement="left" title="Click Here To Search" onclick="fn_Search();"><span class="glyphicon glyphicon-zoom-out"></span>&nbsp;Search</button>
<button class="but btn btn-warning"   type="button" id="resetBtn" name="Reset" value="Reset"  data-toggle="tooltip" data-placement="right" title="Click Here to Reset" onclick="fn_reset();"  ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reset</button>
	</div>
	</div>
      
      
      <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <div class="alert alert-block alert-danger fade in" id="msgAlert" style="display:none;word-wrap: break-word;">
                                 <a class="close" onclick="$('#msgAlert').hide()">×</a>  
                              
                                <strong> <b><c:out value="${resultMsg}" /></b>  </strong>
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

<logic:present name="panelDocForm" property="panelDocLst">
<bean:size id="pnlDocLst" name="panelDocForm"  property="panelDocLst"/>

<logic:greaterThan value="0" name="pnlDocLst">
<div  class="leftone">
<ul class="pagination">

<li class="lispacing">Showing Results</li>
<li class="lispacing"><bean:write name="panelDocForm" property="startIndex" />  - <bean:write name="panelDocForm" property="endIndex" /> 
of <bean:write name="panelDocForm" property="totalRows" /> </li>
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

	%>
<li class="lispacing">Show Page : </li>
<%

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
			<li><a href="javascript:showPagination(<%=pageNo%>)" title="Show Page"><%=pageNo%> </a></li>
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

</ul></div>

<!--  
<div id="csvDiv" style="clear:both;float:right;margin-right:25px;">
<img id="csvImg" src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCSV()"/>
</div> -->

<table  width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<tr>
	<th class="tbheader1" width="5%" valign="top">S.NO</th>
	<th class="tbheader1" width="5%" valign="top">Login Name</th>
	<th class="tbheader1" width="15%" valign="top">Employee Name</th>
	<th class="tbheader1" width="15%" valign="top">Account Number</th>
	<th class="tbheader1" width="20%" valign="top">Bank Name</th>
	<th class="tbheader1" width="10%" valign="top">Created User</th>
	<th class="tbheader1" width="10%" valign="top">Created Date</th>
	<th class="tbheader1" width="10%" valign="top">Last Updated User</th>
	<th class="tbheader1" width="10%" valign="top">Last Updated Date</th>
 <!-- 	<th class="tbheader1" width="10%" valign="top">Hospital Name</th> -->
	
	
	</tr>
	<c:set var="counter" value="1"></c:set>
    <logic:iterate id="result" name="panelDocForm" property="panelDocLst" indexId="index">
	<tr> 

	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">${counter}</td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><a href="#"  data-toggle="modal"  title="click Here to Edit Account Details of <bean:write name="result" property="LOGINNAME" />" data-target="#panelDocDetails" onclick="javascript:fn_pnlDocDetails('<bean:write name="result" property="USERID" />')";><bean:write name="result" property="LOGINNAME" /></a></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="EMPLOYEENAME" /></td>
	
	
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="ACCOUNTNUMBER" /></td> 
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="BANKNAME" /></td>
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="CREATEUSER" /></td>
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="CREATEDATE" /></td>
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="LASTUPDTDUSER" /></td>
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="LASTUPDATEDDATE" /></td>
	<c:set var="counter" value="${counter+1}"></c:set>
	</tr>
</logic:iterate>
</table>

</logic:greaterThan>
</logic:present>

<c:if test="${totalrecords eq 'N' }">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >

<td align="center" height="50">
<font color="#1560A1"><b><span class="glyphicon glyphicon-warning-sign"></span>&nbsp;No results found</b></font>
</td>
</tr>
</table>
</c:if>
<html:hidden property="rowsPerPage"  name="panelDocForm" styleId="rowsPerPage"/>
<html:hidden property="startIndex" name="panelDocForm" styleId="startIndex"/>
<html:hidden property="showPage" name="panelDocForm"  styleId="showPage"/>
<html:hidden property="totalRows" name="panelDocForm" styleId="totalRows" />


<div class="modal fade" id="panelDocDetails">
<div class="modal-dialog modal-lg">
<div class="modal-content">
<div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Panel Doctor Account Updation</label>
</div>
<div class="modal-body">
<iframe id="panelDocDtls"  width="100%" height="250px" frameborder="no" scrolling="yes"></iframe>
</div>

<div class="modal-footer">

 <button type="button" class="btn btn-warning" class="btn btn-default" data-dismiss="modal" >Close</button>
</div>
</div>
</div></div>

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
</form>






        
</body>
</fmt:bundle>
</html>
