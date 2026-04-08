<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>Cases Search</title>
	<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
	<style type="text/css">
.centerone {
	padding-left: 6%;
}

.ui-autocomplete-input {
    width: 20em;
}
#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 {
	width: 35%;
}
#ui-id-4 {
	width: 15%;
}
.custom-combobox-input {
	margin: 0;
	padding: 0.3em;
	background: #fff;
	border: 1px solid #e6e6e6;
}
.custom-combobox{
margin-right:-1.2em !important;
}
#state-input{
 width: 13em;
}
#district-input{
 width: 10em;
}

body {
	font-size: 1.2em !important;
}
</style>
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="scripts/PreauthScripts.js"></script>
	<script src="js/jquery.msgBox.js"></script>
	
	<%@ include file="/common/includeCalendar.jsp"%>
	<%@ include file="/common/editableComboBox.jsp"%>
	<%@ include file="/common/includePatientDetails.jsp"%>


	<script>

	if('${NODATA}'){
		alert("NO DATA present");
				}
	function exportToCsv() {

		var casesSearchFlag='${casesSearchFlag}';
		document.getElementById("csvBt").onclick =null;
		if((((((document.getElementById("patientNo").value!=null&&document.getElementById("patientNo").value!='')||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))
		        || (document.getElementById("healthCardNumber").value!=null&&document.getElementById("healthCardNumber").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='')&&(document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='' && document.getElementById("status").value!="-1" )))){


					
		        	document.forms[0].action="./annualCheckUpAction.do?actionFlag=AHCClaimCasesCSV&search=search&patID="+document.getElementById("patientNo").value+"&PatName="+document.getElementById("name").value+"&healthCardNo="+document.getElementById("healthCardNumber").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage=10&casesSearchFlag="+casesSearchFlag;
		        	document.forms[0].method="post";
		        		document.forms[0].submit(); 
		       
		            }else{
			
				document.forms[0].action = "./annualCheckUpAction.do?actionFlag=AHCClaimCasesCSV&pageNo=1&rowsPerPage=10&casesSearchFlag="+casesSearchFlag;
				document.forms[0].method = "post";
				document.forms[0].submit();

		            }

		

	}
function openAppl(ahcId){
	var casesSearchFlag='${casesSearchFlag}';
	url="./annualCheckUpAction.do?actionFlag=viewAhcClaimPage&ahcId="+ahcId+"&casesSearchFlag="+casesSearchFlag;
	//window.open(url);
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
	var casesSearchFlag='${casesSearchFlag}';
	if(((((( document.getElementById("status").value!=null && document.getElementById("status").value!='-1' )||(document.getElementById("patientNo").value!=null&&document.getElementById("patientNo").value!='')||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))
	        || (document.getElementById("healthCardNumber").value!=null&&document.getElementById("healthCardNumber").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='')&&(document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='' )))){



        	document.forms[0].action="./annualCheckUpAction.do?actionFlag=AHCClaimCases&search=search&patID="+document.getElementById("patientNo").value+"&PatName="+document.getElementById("name").value+"&healthCardNo="+document.getElementById("healthCardNumber").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage+"&casesSearchFlag="+casesSearchFlag;
        	document.forms[0].method="post";
        		document.forms[0].submit(); 
       
            }else{
	
		document.forms[0].action = "./annualCheckUpAction.do?actionFlag=AHCClaimCases&pageNo="+pageNo+"&rowsPerPage"+rowsPerPage+"&casesSearchFlag="+casesSearchFlag;
		document.forms[0].method = "post";
		document.forms[0].submit();

            }
}

function focusBox(arg)
	{
		aField =arg;
		setTimeout("aField.focus()",0);
	}
function fnSearch(){

	if(((((((document.getElementById("patientNo").value==""&&document.getElementById("name").value=="")&&document.getElementById("healthCardNumber").value=="")&&(document.getElementById("state").value=="-1" || document.getElementById("state").value==""))&&(document.getElementById("district").value=="-1" || document.getElementById("district").value==""))&&document.getElementById("fromDate").value=="")&& document.getElementById("toDate").value=="") && (document.getElementById("status").value=="-1"  ||  document.getElementById("status").value=="")){
		alert("Enter Search Criteria");
		return false;
		
	}
	if((document.getElementById("fromDate")!=null && document.getElementById("fromDate").value!="") &&
				(document.getElementById("toDate")==null || (document.getElementById("toDate")!=null && document.getElementById("toDate").value=="")) )
		{
			alert("Please enter To Date");
			focusBox(document.getElementById("toDate"));
			return false;
		}
	if((document.getElementById("toDate")!=null && document.getElementById("toDate").value!="") &&
			(document.getElementById("fromDate")==null || (document.getElementById("fromDate")!=null && document.getElementById("fromDate").value=="")) )
		{
			alert("Please enter From Date");
			focusBox(document.getElementById("fromDate"));
			return false;
		}
	var casesSearchFlag='${casesSearchFlag}';
		document.forms[0].action="./annualCheckUpAction.do?actionFlag=AHCClaimCases&search=search&patID="+document.getElementById("patientNo").value+"&PatName="+document.getElementById("name").value+"&healthCardNo="+document.getElementById("healthCardNumber").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&casesSearchFlag="+casesSearchFlag;
		document.forms[0].method="post";
			document.forms[0].submit(); 
		

	}
		

	
		



function resetSearch(){

var retval=confirm("Do you want to reset the data?");
if(retval){

document.getElementById("patientNo").value="";
document.getElementById("name").value="";
document.getElementById("healthCardNumber").value="";
document.getElementById("status").value="-1";
$('#status-input').val('');
document.getElementById("status").value="";$('#scheme-input').val();
//document.getElementById("scheme").value="";$('#scheme-input').val('');
document.getElementById("state").value="";$('#state-input').val('');

document.getElementById("district").value="";$('#district-input').val('');
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
            		resultArray = resultArray.replace("]","");
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
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif"><b> ANNUAL HEALTH CHECKUP CLAIM CASES</b></span>
         </a></div></div></div>
       <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
	
	<table class="table table-responsive" width="100%" class="tb">

		<tr >
			<td align="center" colspan="1" class="labelheading1 tbcellCss" ><b>Case Status</b></td>
		 	<td colspan="1" class="tbcellBorder" ><html:select style="WIDTH: 10em;border:1;"
				name="annualCheckUpForm" property="status" styleId="status"
				  title="Select Scheme">
				<html:option value="-1">Select</html:option>
				<c:if test="${statusLst ne null and fn:length(statusLst)>0}">
					<html:options collection="statusLst" property="ID" labelProperty="VALUE"></html:options>
				</c:if>
			</html:select></td> 
			<td colspan="1" width="5%" align="center" class="labelheading1 tbcellCss"><b>Patient No</b></td>
			<td colspan="1" class="tbcellBorder"><html:text name="annualCheckUpForm"
				property="patientNo" maxlength="20" styleId="patientNo"
				 title="Enter Patient No" style="WIDTH: 12em;border:1;" /></td>
			<td colspan="1" width="7%"  align="center" class="labelheading1 tbcellCss"><b>Patient Name</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 12em;border:1;"
				name="annualCheckUpForm" property="name" maxlength="100"
				 styleId="name" title="Enter Patient Name" /></td>
			<td colspan="1"  width="7%" align="center" class="labelheading1 tbcellCss"><b>Health Card No</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 12em;border:1;"
				name="annualCheckUpForm" property="cardNo" 
				styleId="healthCardNumber" maxlength="21"
				title="Enter Health Card No" /></td>
				<td align="center" colspan="1" class="labelheading1 tbcellCss" id="sch" style="display:none"><b>Scheme</b></td>
			<%-- <td colspan="1" class="tbcellBorder" id="schDD" style="display:none"><html:select style="WIDTH: 10em;border:1;"
				name="annualCheckUpForm" property="scheme" styleId="scheme"
				  title="Select Scheme">
				<html:option value="-1">Select</html:option>
				<html:option value="CD201">AP</html:option>
				<html:option value="CD202">TG</html:option>
				<html:option value="CD203">BOTH</html:option>
			</html:select></td> --%>
		</tr>




		<tr >
			<td colspan="1" align="center" class="labelheading1 tbcellCss"><b>State</b></td>
			<td colspan="1"  class="tbcellBorder"><html:select 
				name="annualCheckUpForm" property="state" 
				styleId="state" onchange="stateSelected()" title="Select State">
				<html:option value="-1">Select</html:option>
				<html:options property="ID" collection="stateList"
					labelProperty="VALUE" />
			</html:select></td>
			<td colspan="1"align="center" class="labelheading1 tbcellCss"><b>District</b></td>
			<td  colspan="1" class="tbcellBorder"><html:select 
				name="annualCheckUpForm" property="district" 
				styleId="district" title="Select District">
				<html:option value="-1">Select</html:option>

			</html:select></td>
			<td colspan="1" align="center" class="labelheading1 tbcellCss"><b>From Date</b></td>
			<td colspan="1"  class="tbcellBorder"><html:text style="WIDTH: 10em;;border:1;"
				name="annualCheckUpForm" property="fromDate" styleId="fromDate"
				title="Enter From Date" 
			
				 /></td>
				<td colspan="1" align="center"  class="labelheading1 tbcellCss"><b>To Date</b></td>
			<td  colspan="1" class="tbcellBorder"><html:text style="WIDTH: 10em;;border:1;"
				name="annualCheckUpForm" property="toDate" styleId="toDate"
				title="Enter To Date" /></td>


		</tr>


		
		<tr align="center">

			<td align="center" colspan="8">
			<table align="center">
				<tr>
					<td>
					<button class="but" onclick="javascript:fnSearch()" type="button">Search</button>
					<button class="but" type="button" onclick="resetSearch()">Reset</button>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table></div></div>

	<br>
	<div><div style="WIDTH:90%;" align="center">
	
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

<li><b>&nbsp;&nbsp;Download Report As&nbsp;<img src="images/csv1.png" onmouseover="this.src='images/csv2.png'" title="Click to Download AS CSV File" onmouseout="this.src='images/csv1.png'"  id="csvBt" onclick="javascript:exportToCsv()"/></b> </li>
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

<logic:present name="annualCheckUpForm" property="annualList">
		<bean:size id="size" name="annualCheckUpForm" property="annualList" />
		<logic:greaterThan name="size" value="0">

	
	<table class=".table table-responsive" width="100%">
				<tr>
					<th class="tbheader1" >PATIENT NO</th>
					<th class="tbheader1" >HEALTH CARD NO</th>
					<th class="tbheader1" >PATIENT NAME</th>
					<th class="tbheader1" >CASE STATUS</th>
					<th class="tbheader1" >DISTRICT</th>
					<th class="tbheader1" >GENDER</th>
					<th class="tbheader1" >AGE</th>
					<th class="tbheader1" >REGISTRATION DATE</th>
				</tr>
			
	

			
<logic:iterate name="annualCheckUpForm" property="annualList" 	id="annualList">
			
				<tr>
					<td align="center" colspan="1" class="tbcellBorder"><a href="#"
						onclick="javascript:openAppl('<bean:write name="annualList" property="patientNo" />')"><bean:write name="annualList" property="patientNo" /></a></td>
					
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="employeeNo" /></td>
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="name" /></td>
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="caseProcCodes" /></td>

					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="district" /></td>
                     <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="gender" /></td>
                          <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="age" /></td>
						 <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="annualList" property="registrationDate" /></td>
                     
				</tr>



			</logic:iterate>


</table>
		





		</logic:greaterThan>
	</logic:present>
	
<logic:notPresent name="annualCheckUpForm" property="annualList">
		<table align="center" width="100%">
			<tr align="center">
				<td align="center" class="tbcellBorder" width="100%">
				<center><b>No Records found</b></center>
				</td>
			</tr>
		</table>
	</logic:notPresent> 
	<html:hidden property="showPage" name="annualCheckUpForm" />
<html:hidden property="startPage" name="annualCheckUpForm" value="${startPage}" />
<html:hidden property="endPage" name="annualCheckUpForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="annualCheckUpForm" />	
	</form>
	</body>
<script>
if('${userState}'=="CD203"){

	document.getElementById("sch").style.display ="";
	document.getElementById("schDD").style.display ="";
	
	}
</script>

</html>
