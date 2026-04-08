<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>Cases Search</title>
	<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/theme.css"
	rel="stylesheet" type="text/css" media="screen">
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
	<style type="text/css">
.centerone {
	padding-left: 6%;
}
</style>
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="scripts/PreauthScripts.js"></script>
	<script src="js/jquery.msgBox.js"></script>

	<%@ include file="/common/includeCalendar.jsp"%>
	<%@ include file="/common/editableComboBox.jsp"%>
	<%@ include file="/common/includePatientDetails.jsp"%>
	<style>
#ui-id-4 {
	width: 15%;
}
.ui-autocomplete-input {
    width: 13em;
}
#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 {
	width: 35%;
}

.custom-combobox-input {
	margin: 0;
	padding: 0.3em;
	background: #fff;
	border: 1px solid #e6e6e6;
}

body {
	font-size: 1.2em !important;
}


</style>

<script>
if('${NODATA}'){
	alert("NO DATA present");
			}
function exportToCsv() {

	document.getElementById("csvBt").onclick =null;
	
	if((((((document.getElementById("patientNo").value!=null&&document.getElementById("patientNo").value!='')||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))
	        || (document.getElementById("healthCardNumber").value!=null&&document.getElementById("healthCardNumber").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='')&&(document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='')))||document.getElementById("scheme").value!="-1" ){




	        	document.forms[0].action="./annualCheckUpAction.do?actionFlag=annualPatientsCSV&search=search&patID="+document.getElementById("patientNo").value+"&PatName="+document.getElementById("name").value+"&healthCardNo="+document.getElementById("healthCardNumber").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage=10"+"&userState="+document.getElementById("scheme").value;
	        	document.forms[0].method="post";
	        		document.forms[0].submit(); 
	       
	            }else{
		
			document.forms[0].action = "./annualCheckUpAction.do?actionFlag=annualPatientsCSV&pageNo=1&rowsPerPage=10";
			document.forms[0].method = "post";
			document.forms[0].submit();

	            }
}
function generatePrint(patientId)
{
	window.open('./annualCheckUpAction.do?actionFlag=printPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=20,width=1000,height=600,toolbar=no,scrollbars=yes');
}

function  refreshParentPage()
{
window.close();
}
function openAppl(patientId){
	var url="/<%=context%>/annualCheckUpAction.do?actionFlag=viewAhcForm&fromPatientsView=Y&patientId="+patientId;
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
	
}

function showinSetsOf(num)
{
	      
		document.forms[0].rowsPerPage.value=num; 
		document.forms[0].showPage.value='1'; 
		fn_pagination(1,num);
	
}

function showPagination(num)
{
		document.forms[0].showPage.value=num; 
		fn_pagination(num,document.forms[0].rowsPerPage.value);
	
		
	}
function fn_pagination(pageNo,rowsPerPage) {
	
	if((((((document.getElementById("patientNo").value!=null&&document.getElementById("patientNo").value!='')||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))
        || (document.getElementById("healthCardNumber").value!=null&&document.getElementById("healthCardNumber").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='')&&(document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='')))||document.getElementById("scheme").value!="-1" ){



        	document.forms[0].action="./annualCheckUpAction.do?actionFlag=getAnnualPatientsView&search=search&patID="+document.getElementById("patientNo").value+"&PatName="+document.getElementById("name").value+"&healthCardNo="+document.getElementById("healthCardNumber").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage+"&userState="+document.getElementById("scheme").value;
        	document.forms[0].method="post";
        		document.forms[0].submit(); 
       
            }else{
	
		document.forms[0].action = "./annualCheckUpAction.do?actionFlag=getAnnualPatientsView&pageNo="+pageNo+"&rowsPerPage"+rowsPerPage;
		document.forms[0].method = "post";
		document.forms[0].submit();

            }
}


function fnSearch(){

	if(((((((document.getElementById("patientNo").value==""&&document.getElementById("name").value=="")&&document.getElementById("healthCardNumber").value=="")&&document.getElementById("state").value=="-1")&&document.getElementById("district").value=="-1")&&document.getElementById("fromDate").value=="")&& document.getElementById("toDate").value=="") && document.getElementById("scheme").value=="-1"){
		alert("Enter Search Criteria");
		return false;
		
	}
		
		document.forms[0].action="./annualCheckUpAction.do?actionFlag=getAnnualPatientsView&search=search&patID="+document.getElementById("patientNo").value+"&PatName="+document.getElementById("name").value+"&healthCardNo="+document.getElementById("healthCardNumber").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&userState="+document.getElementById("scheme").value;
		document.forms[0].method="post";
			document.forms[0].submit(); 
		

	}
		

	
		



function resetSearch(){

var retval=confirm("Do you want o reset the data?");
if(retval){

document.getElementById("patientNo").value="";
document.getElementById("name").value="";
document.getElementById("healthCardNumber").value="";
document.getElementById("state").value="";$('#state-input').val('');

document.getElementById("district").value="";$('#district-input').val('');

document.getElementById("fromDate").value="";
document.getElementById("toDate").value="";
document.getElementById("scheme").value="";$('#scheme-input').val('');
}
	
}




function stateSelected()
{
	
	var state=null;
	var lStrHdrId='LH6';
	state=document.getElementById("state").value;
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
                		
            			document.forms[0].district.options.length=0;
            			document.forms[0].district.options[0]=new Option("---select---","-1");
            			for(var i = 0; i<districtList.length;i++)
            			{	
            				var arr=districtList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					document.forms[0].district.options[i+1] =new Option(val1,val2);
            				}
            			}
            		}
            	}
            }
        }
    }
	url = "./annualCheckUpAction.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
</script>
	<body>
	<form action="/annualCheckUpAction.do" method="post">
	
	<br>

	<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif"><b> PATIENTS REGISTERED FOR ANNUAL HEALTH CHECKUP</b></span>
         </a></div></div></div>
         
       <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
         <table class="table table-responsive"	class="tb" width="100%" >

		<tr>
			<td colspan="1"  class="labelheading1 tbcellCss " align="center" ><b>Patient No</b></td>
			<td colspan="1" class="tbcellBorder"><html:text name="annualCheckUpForm"
				property="patientNo" maxlength="20" styleId="patientNo"
				 title="Enter Patient No" style="border:1;"/></td>
			<td colspan="1" align="center" class="labelheading1 tbcellCss"><b>Patient Name</b></td>
			<td colspan="1" class="tbcellBorder"><html:text
				name="annualCheckUpForm" property="name" maxlength="100"
				 styleId="name" title="Enter Patient Name" style="border:1;"/></td>
			<td colspan="1" align="center" class="labelheading1 tbcellCss"><b>Health Card No</b></td>
			<td colspan="1" class="tbcellBorder"><html:text
				name="annualCheckUpForm" property="cardNo" 
				styleId="healthCardNumber" maxlength="21"
				title="Enter Health Card No" style="border:1;" /></td>
					<td colspan="1" align="center"  class="labelheading1 tbcellCss"><b>State</b></td>
			<td  colspan="1"  class="tbcellBorder"><html:select style="border:1;"
				name="annualCheckUpForm" property="state" 
				styleId="state" onchange="stateSelected()" title="Select State">
				<html:option value="-1">Select</html:option>
				<html:options property="ID" collection="stateList"
					labelProperty="VALUE"  />
			</html:select></td>
		</tr>




		<tr>
		
			<td  colspan="1" align="center" class="labelheading1 tbcellCss"><b>District</b></td>
			<td colspan="1" class="tbcellBorder"><html:select style="border:1;"
				name="annualCheckUpForm" property="district" 
				styleId="district" title="Select District">
				<html:option value="-1">Select</html:option>

			</html:select></td>
			<td colspan="1" align="center" class="labelheading1 tbcellCss"><b>From Date</b></td>
			<td  colspan="1"   class="tbcellBorder"><html:text style="border:1;"
				name="annualCheckUpForm" property="fromDate" styleId="fromDate"
				title="Enter From Date" 
			
				 /></td>
				<td  colspan="1"  align="center" class="labelheading1 tbcellCss"><b>T0 Date</b></td>
			<td colspan="1"   class="tbcellBorder"><html:text style="border:1;"
				name="annualCheckUpForm" property="toDate" styleId="toDate"
				title="Enter To Date" /></td>
				
<!-- 				<td align="center" colspan="1" class="labelheading1 tbcellCss" id="sch" style="display:none"><b>Scheme</b></td> -->
<%-- 			<td colspan="1" class="tbcellBorder" id="schDD" style="display:none"><html:select style="WIDTH: 10em;border:1;" --%>
<%-- 				name="annualCheckUpForm" property="scheme" styleId="scheme" --%>
<%-- 				  title="Select Scheme"> --%>
<%-- 				<html:option value="-1">Select</html:option> --%>
<%-- 				<html:option value="CD201">AP</html:option> --%>
<%-- 				<html:option value="CD202">TG</html:option> --%>
<%-- 				<html:option value="CD203">BOTH</html:option> --%>
<%-- 			</html:select></td> --%>

		</tr>


		
		<tr align="center">

			<td align="center" colspan="8">
			<table align="center">
				<tr>
					<td>
					<button class="btn but" onclick="javascript:fnSearch()" type="button">Search</button>
					<button class="btn but" type="button" onclick="resetSearch()">Reset</button>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
   </div></div>      
         
         
	
	<br>
	<div style="WIDTH:90%;" align="center">
	
<div class="leftone">
<ul class="pagination">
<li>&nbsp;<b>Showing Results</b>&nbsp;&nbsp; </li>
<li><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
of  <b><c:out value="${totalRecords}" /></b></li>
</ul></div>


<div class="centerone">

<ul class="pagination"> 
<li>&nbsp;<b>Show Page</b>&nbsp;</li>
 <logic:notEmpty name="annualCheckUpForm" property="prev"> 
<li><span class="glyphicon glyphicon-backward" onclick="javascript:showPagination('prev')"></span></li>
</logic:notEmpty>
<c:set var="startPage1" scope="session" value="${startPage}"/>
<c:set var="endPage1" scope="session" value="${endPage}"/>
<c:set var="pages1" scope="session" value="${pages}"/>
<c:if test="${pages>=startPage1}">

<c:forEach var="page" begin="${startPage1}" end="${endPage1}">
   <c:if test="${liPageNo eq page}" >
<li class="active"><span><c:out value="${page}" /></span></li> 
</c:if>
<c:if test="${liPageNo ne page}">
<c:if test="${page le pages1}" >
<li><a href="javascript:showPagination('<c:out value="${page}" />')"> <c:out value="${page}" /></a></li>
</c:if>
</c:if> 
 
</c:forEach>

<logic:notEmpty name="annualCheckUpForm" property="next">
<li><span class="glyphicon glyphicon-forward" onclick="javascript:showPagination('next') "></span></li>
</logic:notEmpty> 
</c:if>

<li><b>&nbsp;&nbsp;Download Report As&nbsp;<img src="images/csv1.png" onmouseover="this.src='images/csv2.png'" title="Click to Download AS CSV File" onmouseout="this.src='images/csv1.png'" id="csvBt" onclick="javascript:exportToCsv()"/></b> </li>
</ul></div>



<div class="rightone">
<ul class="pagination">
<li>&nbsp;<b>Show in sets of</b>&nbsp;</li>
<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${rowsPerPage eq set }" >
<li class="active"><span><c:out value="${set}" /></span></li> 
</c:if>
<c:if test="${rowsPerPage ne set }" >
<li><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
</c:if>
</c:forEach>



</ul></div>

</div>
<br>


<logic:present name="annualCheckUpForm" property="annualList">
<bean:size id="size" name="annualCheckUpForm" property="annualList" />

<logic:equal name="size" value="0">
<table align="center" width="100%">
			<tr align="center">
				<td align="center" class="tbcellBorder" width="100%">
				<center><b>No Records found</b></center>
				</td>
			</tr>
		</table>
</logic:equal>
</logic:present>

	<logic:notPresent name="annualCheckUpForm" property="annualList">
		<table align="center">
			<tr align="center">
				<td align="center" class="tbcellBorder" width="100%">
				<center><b>No Records found</b></center>
				</td>
			</tr>
		</table>
	</logic:notPresent> 
	<logic:present name="annualCheckUpForm" property="annualList">
		<bean:size id="size" name="annualCheckUpForm" property="annualList" />
		<logic:greaterThan name="size" value="0">

			<table class=".table table-responsive" width="100%">
				<tr>
					<th class="tbheader" >PATIENT NO</th>
					<th class="tbheader" >EMPLOYEE NO</th>
					<th class="tbheader" >PATIENT NAME</th>
					
					<th class="tbheader">DISTRICT</th>
					<th class="tbheader" >GENDER</th>
					<th class="tbheader" >AGE</th>
					<th class="tbheader" >REGISTRATION DATE</th>
					<th class="tbheader" >PRINT</th>
				</tr>
			


			<logic:iterate name="annualCheckUpForm" property="annualList" 	id="annualList">
			
				<tr>
					<td align="center" colspan="1" class="tbcellBorder"><a
						href="javascript:openAppl('<bean:write name="annualList" property="patientNo" />')"><bean:write name="annualList" property="patientNo" /></a></td>
					
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="employeeNo" /></td>
					
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="name" /></td>
					

					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="district" /></td>
                     <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="gender" /></td>
                          <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="age" /></td>
						 <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="registrationDate" /></td>
                     	 <td align="center" colspan="1" class="tbcellBorder" ><a href="javascript:generatePrint('<bean:write name="annualList" property="patientNo"/>')">
                     		<span class="glyphicon glyphicon-print"></span></a></td>
				</tr>



			</logic:iterate>



</table>



		</logic:greaterThan>
	</logic:present>
	
<html:hidden property="showPage" name="annualCheckUpForm" />
<html:hidden property="startPage" name="annualCheckUpForm" value="${startPage}" />
<html:hidden property="endPage" name="annualCheckUpForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="annualCheckUpForm" />	
<html:hidden name="annualCheckUpForm" property="scheme" styleId="scheme" value="CD202"/>
	</form>
	</body>
<script>
if('${userState}'=="CD203"){

	document.getElementById("sch").style.display ="";
	document.getElementById("schDD").style.display ="";
	
	}
</script>

</html>
