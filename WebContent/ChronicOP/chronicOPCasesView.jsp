<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}' />
<fmt:bundle basename="ApplicationResources">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>Cases Search</title>
	<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
	<link href="css/themes/<%=themeColour%>/commonEhfCss.css"
		rel="stylesheet" type="text/css" media="screen">
	    
	    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
		<link href="css/select2.min.css" rel="stylesheet">
		<script src="js/jquery-1.9.1.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
	    <script src="js/select2.min.js"></script>
		
		
	
	<style type="text/css">
.centerone {
	padding-left: 6%;
}
.rightone
{
float:right;
}
body {
	font-size: 1.2em !important;
}
.ui-autocomplete-input {
    width: 22em;
}

</style>
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="scripts/PreauthScripts.js"></script> 
	<script src="bootstrap/js/bootstrap-datepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
	<script src="js/jquery.msgBox.js"></script>

	 <%@ include file="/common/includeCalendar.jsp"%>  

	<style>
#ui-id-4 {
	width: 15%;
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


</style>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>
	<script>


	if('${NODATA}'){
alert("NO DATA present");
		}
	function exportToCsv() {

     var caseApprovalFlag='${caseApprovalFlag}';
		document.getElementById("csvBt").onclick =null;
		if((document.getElementById("chronicID").value!=null&&document.getElementById("chronicID").value!='')||(document.getElementById("name").value!=null&&document.getElementById("name").value!='')||(document.getElementById("scheme").value!=null&&document.getElementById("scheme").value!='-1')||(document.getElementById("state").value!="-1")||(document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='') || (document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='') || (document.getElementById("chronicStats").value!="-1" && document.getElementById("chronicStats").value!=null)){


			document.forms[0].action="./chronicAction.do?actionFlag=chronicOPCasesCSVView&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&userState="+document.getElementById("scheme").value+"&caseApprovalFlag="+caseApprovalFlag+"&chronicStatus="+document.getElementById("chronicStats").value;
        	document.forms[0].method="post";
        	document.forms[0].submit(); 
			

			}else{



				document.forms[0].action = "./chronicAction.do?actionFlag=chronicOPCasesCSVView&caseApprovalFlag="+caseApprovalFlag;
				document.forms[0].method = "post";
				document.forms[0].submit();


				}
	}

	function openCase(patientId)
	{
           var caseApprovalFlag='${caseApprovalFlag}';
           var pendingFlag='${pendingFlag}';
           
           if(caseApprovalFlag=='Y')
        	   document.forms[0].action="./chronicAction.do?actionFlag=viewScreenedCases&chronicNo="+patientId+"&caseApprovalFlag=Y&pendingFlag="+pendingFlag;
           else 	   
		       document.forms[0].action="./chronicAction.do?actionFlag=viewScreenedCases&chronicNo="+patientId+"&disableFlag=Y&clinicalNotes=Y";

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


		if((((((document.getElementById("scheme").value!="-1") ||(document.getElementById("chronicID").value!=null&&document.getElementById("chronicID").value!=''))||(document.getElementById("name").value!=null&&document.getElementById("name").value!=''))||document.getElementById("state").value!="-1")||((document.getElementById("fromDate").value!=null&&document.getElementById("fromDate").value!='') && (document.getElementById("toDate").value!=null&&document.getElementById("toDate").value!='')))){


			document.forms[0].action="./chronicAction.do?actionFlag=chronicOPCasesView&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage;
        	document.forms[0].method="post";
        		document.forms[0].submit(); 
			

			}else{



				document.forms[0].action = "./chronicAction.do?actionFlag=chronicOPCasesView&pageNo="+pageNo+"&rowsPerPage"+rowsPerPage;
				document.forms[0].method = "post";
				document.forms[0].submit();


				}


		
	}
	
	
function fnSearch(){

	var regCaseFlg='${regCaseFlg}';
	/*document.forms[0].showPage.value='1';
	document.forms[0].endPage.value="";*/
if((document.getElementById("chronicID").value==""||document.getElementById("chronicID").value==null)&&
		(document.getElementById("name").value==""||document.getElementById("name").value==null) && 
		(document.getElementById("state").value=="-1") && (document.getElementById("scheme").value=="-1") &&
		(document.getElementById("fromDate").value==""|| document.getElementById("fromDate").value==null)&& 
		(document.getElementById("toDate").value==""|| document.getElementById("toDate").value==null)&&
		(document.getElementById("chronicStats").value=="-1"|| document.getElementById("chronicStats").value==null))
	{

	alert("Please Enter Search Criteria");
	return false;
    }

var caseApprovalFlag='${caseApprovalFlag}';
var url='';
if(regCaseFlg!='Y')
{
	url="./chronicAction.do?actionFlag=chronicOPCasesView&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&userState="+document.getElementById("scheme").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&caseApprovalFlag="+caseApprovalFlag+"&chronicStatus="+document.getElementById("chronicStats").value;
document.forms[0].action="./chronicAction.do?actionFlag=chronicOPCasesView&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&userState="+document.getElementById("scheme").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&caseApprovalFlag="+caseApprovalFlag+"&chronicStatus="+document.getElementById("chronicStats").value;
}
else
{
	url="./chronicAction.do?actionFlag=chronicOPCasesView&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&userState="+document.getElementById("scheme").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&caseApprovalFlag="+caseApprovalFlag;
	document.forms[0].action="./chronicAction.do?actionFlag=chronicOPCasesView&search=search&chronicId="+document.getElementById("chronicID").value+"&PatName="+document.getElementById("name").value+"&state="+document.getElementById("state").value+"&district="+document.getElementById("district").value+"&userState="+document.getElementById("scheme").value+"&fromDate="+document.getElementById("fromDate").value+"&toDate="+document.getElementById("toDate").value+"&pageNo=1&rowsPerPage="+document.forms[0].rowsPerPage.value+"&caseApprovalFlag="+caseApprovalFlag;
}
parent.setGlobalUrl(url);
document.forms[0].method="post";
document.forms[0].submit(); 
	

}


function resetSearch(){

var retval=confirm("Do you want to reset the data?");
if(retval){

document.getElementById("chronicID").value="";
document.getElementById("name").value="";
//document.getElementById("state").value="-1";
//document.getElementById("scheme").value="-1";
//document.getElementById("district").value="-1";

$("#state").select2('val','');
$("#scheme").select2('val','');
$("#district").select2('val','');
$("#chronicStats").select2('val','');

document.getElementById("fromDate").value="";
document.getElementById("toDate").value="";
	
}
	
}

function statechange()
{
	document.getElementById("stateType").value="-1";
	document.getElementById("district").options.length=0;
	document.getElementById("district").value="-1";
	}

function stateSelected()
{
	var state=null;
	var lStrHdrId='LH6';
	var stateType=null;
	
	if(document.getElementById("state")!=null && document.getElementById("state").value!="-1")
		{
		state=document.getElementById("state").value;
		var xmlhttp;
	    var url;
	    stateType=document.getElementById("stateType").value
	    document.getElementById("district").value="";

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
		url = "./annualCheckUpAction.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state+"&stateType="+stateType;
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		}
	else
		{
		alert("Please Select State!");
		document.getElementById("stateType").value="-1";
		return false;
		}
	
}

function generateCasePrint(chronicId)
{
	//var chronicId=document.getElementById("chronicID").value;



	//var caseValues=caseId.split("/");
	//var caseNo=caseValues[2];    

		window.open("./chronicAction.do?actionFlag=casePrintForm&printFlag=Y&chronicId="+chronicId,'PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');	
	//document.forms[0].method="post";
	//document.forms[0].submit();
	//refreshParentPage();
	
}
function openRegCase(patientId)
{
	document.forms[0].action="./chronicAction.do?actionFlag=viewPatientDetails&patientId="+patientId+"&viewType=enable";
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
}

function generatePrint(patientId)
{
var regCaseFlg='${regCaseFlg}';
if(regCaseFlg=='Y')
{
	window.open('./chronicAction.do?actionFlag=printPatientDetails&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=1000,height=600,toolbar=no,resize=no,scrollbars=yes');
}
else
{
	window.open('./chronicAction.do?actionFlag=casePrintForm&chronicId='+patientId+'&printFlag=Y','PatientRegPrintPage','left=50,top=50,width=1000,height=600,toolbar=no,resize=no,scrollbars=yes');
}
}
function fn_onload()
{
	var regCaseFlg='${regCaseFlg}';
	if(regCaseFlg!=null && regCaseFlg=='Y')
	{
		document.getElementById("chronicStat").style.display="none";
	    document.getElementById("chronicStatus").style.display="none";
	}
}
function viewPreviousPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var action='link';
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(minPageNo>0)
	{
		pageDisplays=pageDisplays+'<li> <span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li>';
	}
	else
	{
		minPageNo=minPageNo+1;
	}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+' <li class="active"><a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		else
		{
			pageDisplays=pageDisplays+' <li><a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		
	}
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var action='link';
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li>';

	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<li class="active"><a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		else
		{
			pageDisplays=pageDisplays+'<li> <a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		
	}
	if(i<noOfPages)
	{
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li></ul>';
		//pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')">Next</a>';
		
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}


</script>
<script>

</script>
	<body onload="fn_onload();">
	<form action="/chronicAction.do" method="post">
	
	<br>
	
	
<div class="panel-group" id="accordion">
  <div class="panel panel-default"> 
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
          <c:if test="${regCaseFlg eq 'Y' }">
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif"><b> CHRONIC OP REGISTERED PATIENTS</b></span>
         </c:if>
         
         <c:if test="${regCaseFlg ne 'Y' }">
         <c:if test="${caseApprovalFlag ne 'Y' }">
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif"><b> CHRONIC OP CASES VIEW</b></span>
         </c:if>
         <c:if test="${caseApprovalFlag eq 'Y' }">
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif"><b> CHRONIC OP CLAIM CASES</b></span>
         </c:if>
         </c:if>
         
         </a></div></div></div>
         
     
          <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
	<table align="center" class=".table table-responsive" width="100%"
		class="tb">

		<tr >
			<td colspan="1"  class="labelheading1 tbcellCss"><b>Chronic
			ID</b></td>
			<td colspan="1" class="tbcellBorder"><html:text
				name="chronicOpForm" property="chronicID" maxlength="20"
				styleId="chronicID" title="Enter Patient No"  style="WIDTH: 10em;;border:1;" /></td>
			<td colspan="1" class="labelheading1 tbcellCss"><b>Patient
			Name</b></td>
			<td colspan="1"  class="tbcellBorder"><html:text
				name="chronicOpForm" property="name" maxlength="100" styleId="name"
				 title="Enter Patient Name" style="WIDTH: 14em;border:1;"  /></td>
		</tr>

		<tr >
		
		<td colspan="1"  class="labelheading1 tbcellCss"><b>District Type</b></td>
		<td colspan="1" class="tbcellBorder"><html:select style="WIDTH: 10em;;border:1;" 
				name="chronicOpForm" property="stateType" styleId="stateType"
				onchange="stateSelected()"  title="Select District Type">
				<html:option value="-1">Select</html:option>
				<html:option value="O">Old Districts</html:option>
				<html:option value="N">New Districts</html:option>
			</html:select></td>
		
			<td colspan="1"  class="labelheading1 tbcellCss"><b>District</b></td>
			<td colspan="1" class="tbcellBorder"><html:select style="WIDTH: 10em;;border:1;" 
				name="chronicOpForm" property="district" styleId="district"
				title="Select District" >
				<html:option value="-1">Select</html:option>
				<html:options collection="districtList" property="ID" labelProperty="VALUE"></html:options>

			</html:select></td>			

		</tr>
          <tr >
           <td colspan="1"  class="labelheading1 tbcellCss"><b>Registration From Date</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 10em;;border:1;" 
				name="chronicOpForm" property="fromDate" styleId="fromDate"
				title="Enter From Date" 
				/>
			
				</td>
			<td colspan="1" class="labelheading1 tbcellCss"><b>Registration To Date</b></td>
			<td colspan="1" class="tbcellBorder"><html:text style="WIDTH: 10em;;border:1;" 
				name="chronicOpForm" property="toDate" styleId="toDate"
				title="Enter To Date" 
				 />				
				 </td>
				<td  colspan="1" class="labelheading1 tbcellCss" id="sch" style="display:none"><b>Scheme</b></td>
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
				 <c:if test="${pendingFlag ne 'Y'}">
			<td colspan="1"   id="chronicStat"  class="labelheading1 tbcellCss"><b>Chronic Status</b></td>
            <td colspan="1" Id="chronicStatus" class="tbcellBorder" ><html:select styleId="chronicStats" style="WIDTH: 10em;;border:1;"
            
            name="chronicOpForm" property="chronicStatus" 
				title="Select Chronic Status">
				<html:option value="-1">Select</html:option>
				<html:options collection="chronicStatus" property="ID" labelProperty="VALUE"></html:options></html:select></td>
		    </c:if>
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

<logic:present name="chronicOpForm" property="chronincOPPatList" >	
	<div style="WIDTH:100%;" align="center"">
	
<div class="leftone">
<ul class="pagination">
<li>&nbsp;<b>Showing Results</b>&nbsp;&nbsp; </li>
<li><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
of  <b><c:out value="${totalRecords}" /></b></li>
</ul></div>







<div id="pageNoDisplay" class="centerone">

<ul class="pagination"> 


<%
int noOfPages = ((Integer) request.getAttribute("pages")).intValue();
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





















<!-- 

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
<li class="active" style="z-index:0"><span><c:out value="${page}" /></span></li> 
</c:if>
<c:if test="${liPageNo ne page}">
<c:if test="${page le pages1}" >
<li><a href="javascript:showPagination('<c:out value="${page}" />')"> <c:out value="${page}" /></a></li>
</c:if>
</c:if> 
 
</c:forEach>

<logic:notEmpty name="chronicOpForm" property="next">
<li><span class="glyphicon glyphicon-forward" onclick="viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>)"></span></li>
</logic:notEmpty> 
</c:if>


</ul>
</div>

 -->

<div class="rightone">
<ul class="pagination">
<li>&nbsp;<b>Show in sets of</b>&nbsp;</li>
<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${rowsPerPage eq set }" >
<li class="active" style="z-index:0;"><span><c:out value="${set}" /></span></li> 
</c:if>
<c:if test="${rowsPerPage ne set }" >
<li><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
</c:if>
</c:forEach>



</ul></div>
</div>
<br><br>
<div style="width:100%" class="rightone">
<ul>
<li class="rightone"><b>&nbsp;&nbsp;Download Report As&nbsp;<img src="images/csv1.png" onmouseover="this.src='images/csv2.png'" title="Click to Download AS CSV File" onmouseout="this.src='images/csv1.png'"  id="csvBt" onclick="javascript:exportToCsv()"/></b> </li>
</ul>
</div>
</logic:present>

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
		<logic:notPresent name="chronicOpForm" property="chronincOPPatList">
		<table align="center" width="100%">
			<tr align="center">
				<td align="center" class="tbcellBorder" width="100%">
				<center><b>No Records found</b></center>
				</td>
			</tr>
		</table>
	</logic:notPresent>

	
	
<br>

<logic:present name="chronicOpForm" property="chronincOPPatList">
		<bean:size id="size" name="chronicOpForm" property="chronincOPPatList" />
		<logic:greaterThan name="size" value="0">
			<table class=".table table-responsive" width="100%">
				<tr>
					<th class="tbheader1" >CHRONIC ID</th>
					<c:if test="${regCaseFlg ne 'Y'}">
					<th class="tbheader1" >CHRONIC NO</th>
					</c:if>
					<th class="tbheader1" >EMPLOYEE/PENSIONER CARD NO</th>
					<th class="tbheader1" >PATIENT NAME</th>
					<c:if test="${regCaseFlg ne 'Y'}">
					<th class="tbheader1" >CHRONIC STATUS</th>
					<th class="tbheader1" >CLAIM AMOUNT</th>
					</c:if>
					<th class="tbheader1" >DISTRICT</th>
					<th class="tbheader1">GENDER</th>
					<th class="tbheader1" >AGE</th>
					<c:if test="${regCaseFlg ne 'Y'}">
					<th class="tbheader1" >CLAIM SUBMITTED DATE</th>
					</c:if>
					<c:if test="${regCaseFlg eq 'Y'}">
					<th class="tbheader1" >REGISTRATION DATE</th>
					</c:if>
					<th class="tbheader1" >Chronic FORM</th>
				</tr>

           <logic:iterate name="chronicOpForm" property="chronincOPPatList" id="chronincOPPatList">
			
				<tr>
				    <c:if test="${regCaseFlg ne 'Y' }">
					<td align="center" colspan="1" class="tbcellBorder"><a
						href="javascript:openCase('<bean:write name="chronincOPPatList" property="chronicNo"/>')"><bean:write name="chronincOPPatList" property="chronicSubID" /></a></td>
					 </c:if>  
					    <c:if test="${regCaseFlg eq 'Y' }">
					<td align="center" colspan="1" class="tbcellBorder"><a
						href="javascript:openRegCase('<bean:write name="chronincOPPatList" property="chronicNo"/>')"><bean:write name="chronincOPPatList" property="chronicSubID" /></a></td>
					 </c:if> 
					    <c:if test="${regCaseFlg ne 'Y'}">
						<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="chronicNo" /></td>
						</c:if>
						
						<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="cardNo" /></td>
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="name" /></td>
						
					    <c:if test="${regCaseFlg ne 'Y'}">						  
							<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="chronicStatus" /></td>
						<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="claimAmount" /></td>
						</c:if>
						
					<td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="district" /></td>
                     
                     <td align="center" colspan="1" class="tbcellBorder">
                     <logic:equal name="chronincOPPatList" property="gender" value="M">
                     Male
                     </logic:equal>
                     <logic:equal  name="chronincOPPatList" property="gender" value="F">
                     Female
                     </logic:equal>
                     </td>
                          <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="patAge" /></td>
						
						<c:if test="${regCaseFlg eq 'Y'}">
						 <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="regnDt" /></td>
						</c:if>
						
						
							<c:if test="${regCaseFlg ne 'Y'}">
						 <td align="center" colspan="1" class="tbcellBorder"><bean:write
						name="chronincOPPatList" property="clmSubDt" /></td>
						</c:if>
						
						
						 <td align="center" colspan="1" class="tbcellBorder"><span class="glyphicon glyphicon-print" title="Click Here to Print Registration Form" style="cursor: pointer;" onclick=" generatePrint('<bean:write name="chronincOPPatList" property="chronicSubID"/>')"></span>
						 </td>
                     
				</tr>



			</logic:iterate>





			</table>



		</logic:greaterThan>
	</logic:present>
	<html:hidden property="showPage" name="chronicOpForm" />
<html:hidden property="startPage" name="chronicOpForm" value="${startPage}" />
<html:hidden property="endPage" name="chronicOpForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="chronicOpForm" />	
<html:hidden property="caseApprovalFlag" name="chronicOpForm" />
<html:hidden property="regCaseFlg" name="chronicOpForm" />	
<html:hidden property="state" name="chronicOpForm"  styleId="state" value="S35" />	

	</form>
	</body>
</fmt:bundle>
<script>
if('${userState}'=="CD203"){

	document.getElementById("sch").style.display ="";
	document.getElementById("schDD").style.display ="";
	
	}
</script>
<script src="js/select2.min.js"></script>
<script>

$("#state").select2();
$("#district").select2();
$("#scheme").select2();
$("#chronicStats").select2();

</script>
</html>
