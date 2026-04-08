<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Admin Sanction WorkFlow</title>
 <script src="js/jquery-1.9.1.js"></script>
 <%@ include file="/common/include.jsp"%>
 <script src="bootstrap/js/bootstrap.min.js"></script>
 <script src="bootstrap/js/app.v2.js"></script>
  <script src="bootstrap/js/bootbox.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
 <style>
 body{font-size:1.2em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 
.text-active,.active .text{
	display:none !important;
}
.mainDiv{

width:92%;
float:right;
padding-right:3%;

}
.active .text-active{
	display:inline-block !important;
}
.centerone {
padding-left:5%;
}
.rightone{
padding-right:3%;

}
 </style>
 
<script>
function showStatus(sanctionId){
	fn_loadImage();
	document.forms[0].action ='/<%=context%>/adminSanction.do?actionFlag=getSanctionDetails&sanctionId='+sanctionId;
	document.forms[0].submit();
}
function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}

function fn_removeLoadingImage()
 {   
   document.getElementById('processImagetable').style.display="none";
 }

function checkOrUncheckAllBoxes()
{
	var NoofRecords = document.getElementById('NoofRecords').value;
	var TotalRecords = document.getElementById('TotalRecords').value; 	
	var iterator = "";
	var count=0;
	if(parseInt(NoofRecords)>parseInt(TotalRecords)){
		iterator = TotalRecords;
	}
	else{
		iterator = NoofRecords;
	}
	if(document.getElementById("allCheckBoxes").checked)
	{		
		for(var i=0;i<iterator;i++){			
			document.getElementById("CheckBox"+i).checked = true;
			document.getElementById("rowTr"+i).style.backgroundColor="#CAFFDC";
			++count;
		}
	
		window.scrollTo(0,document.body.scrollHeight);
	}	
	else
	{
		for(var i=0;i<iterator;i++){
			document.getElementById("CheckBox"+i).checked = false;
			document.getElementById("rowTr"+i).style.backgroundColor="#FFFFFF";
			count = 0;
			amount = 0;
		}
	}
	
}

function countRecordSelected()
{
	var NoofRecords = document.getElementById('NoofRecords').value;
	var TotalRecords = document.getElementById('TotalRecords').value; 	
	var iterator = "";
	var count = 0;
	if(parseInt(NoofRecords)>parseInt(TotalRecords)){
		iterator = TotalRecords;
	}
	else{
		iterator = NoofRecords;
	}
	for(var i=0;i<iterator;i++){
		if(document.getElementById("CheckBox"+i).checked){
			document.getElementById("rowTr"+i).style.backgroundColor="#CAFFDC";
		 ++count;		 
		}
		else
		{
			document.getElementById("rowTr"+i).style.backgroundColor="#FFFFFF";
		}
	}
	
	if(count == iterator){
		document.getElementById("allCheckBoxes").checked = true;
	}
	else{
		document.getElementById("allCheckBoxes").checked = false;
	}	
}

function changeNumberOfRecords(NumberOfRecords){
	document.forms[0].action = 'adminSanction.do?actionFlag=sanctionWorkflow&NoofRecords='+NumberOfRecords;
	document.forms[0].submit();
	
}



function submitAdminSancDetails(buttonValue)
{	
	var NoofRecords = document.getElementById('NoofRecords').value;
	var totalRecords = document.getElementById('TotalRecords').value; 	
	var iterator = "";
	var sancId="";
	if(parseInt(NoofRecords)>parseInt(totalRecords)){
		iterator = totalRecords;
	}
	else{
		iterator = NoofRecords;
	}
	var count = 0;
	
	for(var i=0;i<iterator;i++){				
		 
		if(document.getElementById('CheckBox'+i).checked){	
			count++;	
			sancId = sancId+"~"+document.getElementById('SANCID'+i).value;	
			
		}	
	}	
	if(count == 0){
		bootbox.alert("Please select atleast one check box.");
		
	}
	else
		{
    		  fn_sbt(buttonValue,sancId);        
		}
     
}
function fn_SubmitSingleRow(sancId,buttonValue)
{	
	 sancId="~"+sancId+"~";
	 fn_sbt(buttonValue,sancId);      
}
function fn_sbt(buttonValue,sancId)
{		
	 document.forms[0].action = '/<%=context%>/adminSanction.do?actionFlag=submitAdminSancDetailsCEO&sancId='+sancId+'&buttonValue='+buttonValue;
		document.forms[0].submit();
		document.forms[0].approveBtn.disabled = true;
			document.forms[0].pendingBtn.disabled = true;
			document.forms[0].rejectBtn.disabled = true;
			
			
			 if(buttonValue=="Approve"){
				 $("#approveBtn").button('loading').delay(1000).queue(function() {
			         $("#approveBtn").button('reset');
				 	});
			 }
				 if(buttonValue=="Reject"){
					 $("#rejectBtn").button('loading').delay(1000).queue(function() {
				         $("#rejectBtn").button('reset');
					 	});
				 }
				 	if(buttonValue=="Pending"){
				 		 $("#pendingBtn").button('loading').delay(1000).queue(function() {
					         $("#pendingBtn").button('reset');
						 	});
				 		}

	
}


function fn_showAlert()
{
		 var status = "${alertMsg}"; 
			if( status== "absent") {
			}
			else if(status== "failure")
				{
				bootbox.alert("Error occurred while processing data,Please try later");
				}
			else
				{
				bootbox.alert("Selected Transaction Id's "+status+ "  Successfully");
				}
	 parent.fn_removeLoadingImage();
	 	
	}
	
function showPagination(num)
{
		document.forms[0].showPage.value=num; 
		fn_pagination(num,document.forms[0].rowsPerPage.value);
	}
	
function fn_pagination(pageNo,rowsPerPage){
	document.forms[0].action = "adminSanction.do?actionFlag=sanctionWorkflow&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage;
	document.forms[0].submit();	
}
function showinSetsOf(num)
{
	      
		document.forms[0].rowsPerPage.value=num; 
		document.forms[0].showPage.value='1'; 
		fn_pagination(1,num);
	
}
  
function showStatus(sanctionId){
	fn_loadImage();
	document.forms[0].action ='/<%=context%>/adminSanction.do?actionFlag=getSanctionDetails&sanctionId='+sanctionId;
	document.forms[0].submit();
}


function goBack(){
 	parent.fn_dashboard(); 
 	}
</script>
</head>
<body onload="fn_showAlert();"  bgcolor="#ffffff">
<html:form action="/adminSanction.do" method="POST" enctype="multipart/form-data">

<div id="requestsTable"  style="margin:0 auto;">	
				<div class="container mainDiv">

<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="right">
<button class="btn but" type="button" value="Back"  onClick="javascript:goBack();" >Back</button>
</div>
<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<b>ADMIN SANCTION REQUESTS</b>
					</div>				

<logic:present name="adminSanctionForm"  property="adminSancList">
<bean:size id="size" name="adminSanctionForm" property="adminSancList"/>
<logic:greaterThan name="size" value="0">

<div class="table-responsive"> 

<div class="leftone visible-md visible-lg">
<ul class="pagination">
<li class="lispacing">&nbsp;<b>Showing Results</b>&nbsp;&nbsp; </li>
<li class="lispacing"><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
of  <b><c:out value="${totalRecords}" /></b></li>
</ul></div>

<div class="centerone">

<ul class="pagination"> 
<li>&nbsp;<b>Show Page</b>&nbsp;</li>
 <logic:notEmpty name="adminSanctionForm" property="prev"> 
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

<logic:notEmpty name="adminSanctionForm" property="next">
<li><span class="glyphicon glyphicon-forward" onclick="javascript:showPagination('next') "></span></li>
</logic:notEmpty> 
</c:if></ul>
							</div>
													
							
<div class="rightone visible-md visible-lg" >
<ul class="pagination">
					<li>&nbsp;<b>Show in sets of</b>&nbsp;</li>
<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${rowsPerPage eq set }" >
<li class="active"><span><c:out value="${set}" /></span></li> 
</c:if>
<c:if test="${rowsPerPage ne set }" >
<li ><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
</c:if>
</c:forEach>			
							</ul></div>
							
						<table class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table  b-t text-small"  style="padding-left:1em;" border="0">
							
							
							
							<tr>
							 

							<th align="left" style="text-align: left;" class="visible-lg visible-md tbheader1" width="6%">Sl No.</th>
							<th align="left" style="text-align: left;"  class="tbheader1 " width="4%"  onclick = 'checkOrUncheckAllBoxes()' ><input type="checkbox" id = 'allCheckBoxes'></th>
							<th align="left"  style="text-align: left;" class="col-xs-2 tbheader1 " width="10%">Sanction ID</th>
							<th align="left" style="text-align: left;" class="col-xs-4 visible-lg visible-md visible-sm tbheader1" width="34%">Subject</th>
							<th align="left"  style="text-align: left;" class="col-xs-2 tbheader1" width="6%">Sanction Amount</th>
							<th align="left" style="text-align: left;"  class="visible-lg visible-md tbheader1" width="10%">Account Head</th> 
							<th align="left" style="text-align: left;"  class="visible-lg visible-md tbheader1" width="20%">Vendor Name</th> 
							<th align="left" style="text-align: left;" class="visible-lg visible-md tbheader1" ></th>

							

						
							</tr>
						
							<%int i =(Integer)request.getAttribute("startIndex"); ;%>
							<logic:iterate name="adminSanctionForm" property="adminSancList" id="rowId"  indexId='recordsLength'>
							<tr id="rowTr${recordsLength}">
							<td align="left"  class="tbcellBorder visible-lg visible-md"><%=i++ %></td>
							<td><html:checkbox name = 'adminSanctionForm' indexed = 'true' styleId = 'CheckBox${recordsLength}' onclick = 'countRecordSelected()' property="isSelected"/></td>
							<td align="left" class="tbcellBorder col-xs-2">
							 <html:hidden name='adminSanctionForm' indexed='true' property = 'SANCTIONID' value='${rowId.SANCTIONID}' styleId = 'SANCID${recordsLength}' /> 
							<a href="javascript:showStatus('${rowId.SANCTIONID}');"  onMouseOver="this.style.color='red'" onMouseOut="this.style.color='#00F'" style="text-decoration: underline;font-size: 14px;" title="Click to View Admin Sanction Request"><bean:write name="rowId"  property="SANCTIONID"/></a></td>
							<td align="left"  class="tbcellBorder col-xs-4 visible-lg visible-md visible-sm"><bean:write name="rowId" property="SUBJECT"/></td>
							<td align="left"  class="tbcellBorder col-xs-2"><bean:write name="rowId" property="SANCAMOUNT"/></td>
							<td align="left"  class="tbcellBorder visible-lg visible-md"><bean:write name="rowId" property="ACCCODE"/></td>
							<td align="left"  class="tbcellBorder visible-lg visible-md"><bean:write name="rowId" property="VENDORNAME"/></td>
							<td align="left" class=" tbcellBorder visible-lg visible-md"  width="100">
							
							<a href="#" id="approveS" onclick="javascript:fn_SubmitSingleRow('${rowId.SANCTIONID}','Approve')" data-toggle="class" title="Click to Approve" width="75">
							<span class=" glyphicon glyphicon-ok text-success text-active"></span>
							<span class=" glyphicon glyphicon-ok text-danger text"></span>
							</a>&nbsp;
							
							
							
							<a href="#" id="" onclick="javascript:fn_SubmitSingleRow('${rowId.SANCTIONID}','Reject')" data-toggle="class" title="Click to Reject" >
							<span class=" glyphicon glyphicon-remove text-success text-active"></span>
							<span class=" glyphicon glyphicon-remove text-danger text"></span>
							</a>&nbsp;
							
							</td>
							</tr>
							</logic:iterate>
						</table>					
					</div>

<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="center">
							<button class="but but"  id="approveBtn" onclick="javascript:submitAdminSancDetails('Approve')" type="button"> <span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>
							
							&nbsp;<button class="but but"  type="button" id="rejectBtn" onclick="javascript:submitAdminSancDetails('Reject')"  ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>
						</div>


</logic:greaterThan>
 <logic:equal name="size" value="0">
 <table width="100%" >
 <tr>
 <td class="tbcell" width="100%" align="center"><b>No Records Found</b></td>
 </tr>

 </table>
 </logic:equal>
</logic:present>



</div>
</div>

<div id="processImagetable" style="top:45%;z-index:50;position:absolute;left:45%;display:none">
        <table border="0" align="center" width="100%" style="height:100" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="/<%=context%>/images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
         
         <input type = 'hidden' name = 'NoofRecords' id = 'NoofRecords' value = '${RowsDisplay}'/>
		<input type = 'hidden' name = 'TotalRecords' value = "<bean:write name = 'TotalRecords'></bean:write>"  id = 'TotalRecords' />
		
			<html:hidden property="showPage" name="adminSanctionForm" />
<html:hidden property="startPage" name="adminSanctionForm" value="${startPage}" />
<html:hidden property="endPage" name="adminSanctionForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="adminSanctionForm" />	
		
							
</html:form>
</body>
</html>