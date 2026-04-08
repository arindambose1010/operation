<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}' />
<fmt:bundle basename="ApplicationResources">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>Cases Search</title>
	<link href="css/themes/<%=themeColour%>/commonEhfCss.css"
		rel="stylesheet" type="text/css" media="screen">
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
		<script src="js/jquery-1.9.1.js"></script>
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

	<style>
#ui-id-4 {
	width: 15%;
}

#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 {
	width: 35%;
}
.ui-autocomplete-input {
    width: 22em;
}
.custom-combobox-input {
	margin: 0;
	padding: 0.3em;
	background: #fff;
	border: 1px solid #e6e6e6;
}

body {
	font-size: 1.3em !important;
}
.rightone
{
float:right;
}
</style>

	<script>

	

	
	if('${NODATA}'){

		alert("NO DATA present");
				}
	function exportToCsv() {
		
			
		document.getElementById("csvBt").onclick =null;
		  

		if(((((document.getElementById("chronicID").value!=null&&document.getElementById("chronicID").value!='')||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='') && (document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='')))||document.getElementById("scheme").value!='-1'){


			document.forms[0].action="./chronicAction.do?actionFlag=COPatientsCSV&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage=10"+"&userState="+document.getElementById("scheme").value;
        	document.forms[0].method="post";
        		document.forms[0].submit(); 
			

			}else{



				document.forms[0].action = "./chronicAction.do?actionFlag=COPatientsCSV&pageNo=1&rowsPerPage=10";
				document.forms[0].method = "post";
				document.forms[0].submit();


				}


		
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

	function generatePrint(patientId)
	{
		window.open('./chronicAction.do?actionFlag=printPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=1000,height=600,toolbar=no,resize=no,scrollbars=yes');
	}
	function  refreshParentPage()
	{
	window.close();
	}
	function fn_pagination(pageNo,rowsPerPage) {


		if((((((document.getElementById("scheme").value!="-1") ||(document.getElementById("chronicID").value!=null&&document.getElementById("chronicID").value!=''))||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='') && (document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='')))){


			document.forms[0].action="./chronicAction.do?actionFlag=viewChronicOPPatients&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage+"&userState="+document.getElementById("scheme").value;
        	document.forms[0].method="post";
        		document.forms[0].submit(); 
			

			}else{



				document.forms[0].action = "./chronicAction.do?actionFlag=viewChronicOPPatients&pageNo="+pageNo+"&rowsPerPage"+rowsPerPage;
				document.forms[0].method = "post";
				document.forms[0].submit();


				}


		
	}
	
	
function fnSearch(){



	
if(((((document.getElementById("chronicID").value==""||document.getElementById("chronicID").value==null)&&(document.getElementById("name").value==""||document.getElementById("name").value==null)) && (document.getElementById("scheme").value=="-1"))&& (document.getElementById("state").value=="-1")) && ( (document.getElementById("fromDate").value==""|| document.getElementById("fromDate").value==null)&& (document.getElementById("toDate").value==""|| document.getElementById("toDate").value==null))){

	alert("Enter Search Criteria");
	return false;
	
}


document.forms[0].action="./chronicAction.do?actionFlag=viewChronicOPPatients&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&userState="+document.getElementById("scheme").value;
document.forms[0].method="post";
	document.forms[0].submit(); 
	
	
}


function resetSearch(){

var retval=confirm("Do you want o reset the data?");
if(retval){

document.getElementById("chronicID").value="";
document.getElementById("name").value="";
document.getElementById("state").value="-1";
document.getElementById("scheme").value="-1";
document.getElementById("district").value="-1";

document.getElementById("fromDate").value="";
document.getElementById("toDate").value="";
	
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
function openCase(patientId)
{
	
	document.forms[0].action="./chronicAction.do?actionFlag=viewPatientDetails&patientId="+patientId+"&viewType=enable";
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
}
</script>
	<body>
	<form action="/AnnualCheckUp.do" method="post">
	
	
	<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif"><b> CHRONIC OP PATIENTS VIEW</b></span>
         </a></div></div></div>
         
         
       <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
      
	<table  class="tb" align="center" class=".table table-responsive" width="100%">
		

		<tr>
			<td colspan="1" class="labelheading1 tbcellCss" align="center" ><b>Chronic
			ID</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 10em;border:1;"
				name="chronicOpForm" property="chronicID" maxlength="20"
				styleId="chronicID" title="Enter Patient No"  /></td>
			<td colspan="1" class="labelheading1 tbcellCss" align="center" ><b>Patient
			Name</b></td>
			<td colspan="1" colspan="2" class="tbcellBorder"><html:text style="WIDTH: 10em;border:1;"
				name="chronicOpForm" property="name" maxlength="100" styleId="name"
				 title="Enter Patient Name" /></td>
			<td align="center" colspan="1" class="labelheading1 tbcellCss"><b>State</b></td>
			<td colspan="1" class="tbcellBorder"><html:select style="WIDTH: 10em;border:1;"
				name="chronicOpForm" property="state" styleId="state"
				onchange="stateSelected()"  title="Select State">
				<html:option value="-1">Select</html:option>
				<html:options property="ID" collection="stateList"
					labelProperty="VALUE" />
			</html:select></td>
			
			<td align="center" colspan="1" class="labelheading1 tbcellCss" id="sch" style="display:none"><b>Scheme</b></td>
			<td colspan="1" class="tbcellBorder" id="schDD" style="display:none"><html:select style="WIDTH: 10em;border:1;"
				name="chronicOpForm" property="scheme" styleId="scheme"
				  title="Select Scheme">
				<html:option value="-1">Select</html:option>
				<html:option value="CD201">AP</html:option>
				<html:option value="CD202">TG</html:option>
				<html:option value="CD203">BOTH</html:option>
			</html:select></td>
		</tr>




		<tr>

			<td align="center"  colspan="1" class="labelheading1 tbcellCss"><b>District</b></td>
			<td colspan="1" class="tbcellBorder"><html:select style="WIDTH: 10em;;border:1;"
				name="chronicOpForm" property="district" styleId="district"
				title="Select District">
				<html:option value="-1">Select</html:option>

			</html:select></td>
			<td align="center"colspan="1" class="labelheading1 tbcellCss"><b>From Date</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 10em;;border:1;" 
				name="chronicOpForm" property="fromDate" styleId="fromDate"
				title="Enter From Date" 
				/></td>
			<td align="center" colspan="1" class="labelheading1 tbcellCss"><b>To Date</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 10em;;border:1;"
				name="chronicOpForm" property="toDate" styleId="toDate"
				title="Enter To Date" 
				 /></td>

		</tr>


		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr align="center">

			<td align="center" colspan="8">
			<table align="center">
				<tr>
					<td>
					<button class="btn btn-primary" onclick="javascript:fnSearch()" type="button">Search&nbsp;<span class="glyphicon glyphicon-search"></span></button>
					<button class="btn btn-danger" type="button" onclick="resetSearch()">Reset&nbsp;<span class="glyphicon glyphicon-remove"></span></button>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table></div></div>

	<br>
<div style="WIDTH:80%;" align="center">
	
<div class="leftone">


<ul class="pagination">
<li>&nbsp;<b>Showing Results</b>&nbsp;&nbsp; </li>
<li><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
of  <b><c:out value="${totalRecords}" /></b></li>
</ul></div>


<div class="centerone">

<ul class="pagination"> 
<li>&nbsp;<b>Show Page</b>&nbsp;</li>
 <logic:notEmpty name="chronicOpForm" property="prev"> 
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

<logic:notEmpty name="chronicOpForm" property="next">
<li><span class="glyphicon glyphicon-forward" onclick="javascript:showPagination('next') "></span></li>
</logic:notEmpty> 
</c:if>


</ul>

</div>
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

<div class="rightone">
<ul>
<li><b>&nbsp;&nbsp;Download Report As&nbsp;<img src="images/csv1.png" onmouseover="this.src='images/csv2.png'" title="Click to Download AS CSV File" onmouseout="this.src='images/csv1.png'" id="csvBt" onclick="javascript:exportToCsv()"/></b> </li>
</ul>
</div>
 <logic:present name="chronicOpForm" property="chronincOPPatList">
		<bean:size id="size" name="chronicOpForm" property="chronincOPPatList" />
		<logic:equal name="size" value="0">
		<table align="center" width="100%">
			<tr align="center">
				<td align="center" class="tbcellBorder" width="100%">
				<center><b>No Records found</b></center>
				</td>
			</tr>
		</table>
		
		</logic:equal></logic:present>


<br>
	<logic:notPresent name="chronicOpForm" property="chronincOPPatList">
		<table align="center" width="100%">
			<tr align="center">
				<td align="center" class="tbcellBorder" width="100%">
				<center><b>No Records found</b></center>
				</td>
			</tr>
		</table>
	</logic:notPresent> <logic:present name="chronicOpForm" property="chronincOPPatList">
		<bean:size id="size" name="chronicOpForm" property="chronincOPPatList" />
		<logic:greaterThan name="size" value="0">
			<table class=".table table-responsive" width="100%">
				<tr>
					<th class="tbheader1" >CHRONIC ID</th>
					<th class="tbheader1" >EMPLOYEE NO</th>
					<th class="tbheader1" >PATIENT NAME</th>
					
					<th class="tbheader1" >DISTRICT</th>
					<th class="tbheader1">GENDER</th>
					<th class="tbheader1" >AGE</th>
					<th class="tbheader1" >REGISTRATION DATE</th>
					<th class="tbheader1" >PRINT</th>
				</tr>

           <logic:iterate name="chronicOpForm" property="chronincOPPatList" id="chronincOPPatList">
			
				<tr>
					<td align="center" colspan="1" class="tbcellBorder" title="Click on Chronic ID to view patient details"><a
						href="javascript:openCase('<bean:write name="chronincOPPatList" property="chronicID"/>')"><bean:write name="chronincOPPatList" property="chronicSubID" /></a></td>
					
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="employeeNo" /></td>
					
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="name" /></td>
					

					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="district" /></td>
                     <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="gender" /></td>
                          <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="age" /></td>
						 <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="registrationDate" /></td>
                     	<td align="center" colspan="1" class="tbcellBorder" title="Click Here To Print Patient Registration Form"><a href="javascript:generatePrint('<bean:write name="chronincOPPatList" property="chronicID"/>') " >
                     		<span class="glyphicon glyphicon-print"></span></a></td>
				</tr>



			</logic:iterate>





			</table>



		</logic:greaterThan>
	</logic:present>
	<html:hidden property="showPage" name="chronicOpForm" />
<html:hidden property="startPage" name="chronicOpForm" value="${startPage}" />
<html:hidden property="endPage" name="chronicOpForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="chronicOpForm" />	
	
	
	
	</form>
	</body>
</fmt:bundle>
<script>
if('${userState}'=="CD203"){

	document.getElementById("sch").style.display ="";
	document.getElementById("schDD").style.display ="";
	
	}
</script>
</html>
