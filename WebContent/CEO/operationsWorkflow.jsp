<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<html>
<fmt:setLocale value='${langID}'/>  
<fmt:bundle basename="ApplicationResources">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Operations CEO WorkFlow</title>
 <script src="js/jquery-1.9.1.js"></script>
 <%@ include file="/common/include.jsp"%>
 <script src="bootstrap/js/bootstrap.min.js"></script>
 <script src="bootstrap/js/app.v2.js"></script>
  <script src="bootstrap/js/bootbox.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
 <style>
 body{font-size:1.2em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 
.text-active,.active .text{
	display:none !important;
}

.centerone {
padding-left:5%;
}
.rightone{
padding-right:3%;

}



.active .text-active{
	display:inline-block !important;
}

.blink{
 text-decoration: blink;
}
.mainDiv{
 position: relative;
width:92%;
float:right;
padding-right:3%;



}
.centerProgress{
width:50%;
align:center;
padding-top:20%;
padding-left:40%;
background-color:none;
border-radius:10%;
}
.modal-dialog{
width:800px;
}
 </style>
</head>

<body onload="fn_removeLoadingImage();">
<html:form  method="post"  action="/casesSearchAction.do" > 
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

<div id="processImagetable" style="top:50%;width:100%;position:absolute;z-index:60;height:100%">
   <table border="0" align="center" width="100%" style="height:400" >
      <tr>
         <td>
           <div id="processImage" align="center">
             <img src="images/Progress.gif" width="100"
                     height="100" border="0"></img>
            </div>
          </td>
        </tr>
     </table>
</div>
<c:if test="${successFlag eq 'Y' || successFlag eq 'N' || successFlag eq 'P' }">
<script>
document.getElementById('progressBar').style.display='none';
/*$(function () { $('#progressBar').modal({
				hide: true
			   })});*/
</script>
</c:if>





<div id="operations" class="mainDiv">

<button class="but but rightone"  id="backBtn" onclick="javascript:fn_back();" type="button"> </span>&nbsp;&nbsp;Back</button>

<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12">
<b>Operations Preauthorization WorkFlow</b>
</div>
<div class="clearfix"></div>
<bean:size id="lstCaseSearchSize" name="casesSearchFormClaims"  property="lstCaseSearch"/>


<div  class="leftone">
<ul class="pagination">
<logic:greaterThan value="0" name="lstCaseSearchSize">
<li class="lispacing">Showing Results</li>
<li class="lispacing"><bean:write name="casesSearchFormClaims" property="startIndex" />  - <bean:write name="casesSearchFormClaims" property="endIndex" /> 
of <bean:write name="casesSearchFormClaims" property="totalRows" /> </li>
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
</logic:greaterThan>
</ul></div>


<div class="modal fade"   id="progressBar" >
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Processing...</span>
    </div>
  </div>
</div>
</div></div></div>
<logic:greaterThan value="0" name="lstCaseSearchSize">














<table  width="90%"  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table " cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<tr>
	 <td class="tbheader1" width="5%">
      <label><input type="checkbox" id="checkAll" value="" title="click here to select all Cases" onclick="javascript:fn_checkAll();"></label>
    </td>
	<th class="tbheader1" width="15%" valign="top"><fmt:message key="label.caseSearch.caseNo" /></th>

	<th class="tbheader1 visible-lg visible-md visible-sm" width="25%" valign="top"><fmt:message key="label.caseSearch.patientName" /></th>

	<th class="tbheader1 visible-lg visible-md " width="35%" valign="top">Hospital Name</th>
<!-- 	<th class="tbheader1" width="10%" valign="top">Hospital Name</th> -->
	
	
	<th class="tbheader1" width="15%" valign="top">Total Package Amount</th>
	<th class="tbheader1 " width="25%" valign="top">Action</th>

	
	</tr>
	<c:set var="count" value="1"></c:set>
    <logic:iterate id="result" name="casesSearchFormClaims"  property="lstCaseSearch" indexId="index">
	
	<tr   id="row${count}" class="border${result.colorFlag}" > 
	<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	 
	 <td class="tbcellBorder" width="5%">
      <label><input type="checkbox" value="<bean:write name="result" property="caseId"/>" onclick="javascript:checkOrUncheckCase();"></label>
    </td>
    
	<td class="tbcellBorder" width="15%" style="word-wrap:break-word;padding:3px;" nowrap="nowrap">
    <a id="patDtlsImage" style=cursor:pointer; title="Click to View Case Details" align="top" data-toggle="modal" href="#viewDtlsID" onclick="javascript:fn_getPatFullDetails('<bean:write name="result" property="caseId" />')" ><span class="glyphicon glyphicon-folder-open"></span></a>
    <a href="javascript:javascript:fn_preauthDetails('<bean:write name="result" property="caseId" />','<bean:write name="result" property="patientId" />');"><bean:write name="result" property="caseNo" /></a>
    <c:if test="${result.flagged eq 'Y'}">
    <img id="redFlag" src="./images/RedFlag.png" style="cursor:pointer;" title="Flagged"></img>
    </c:if>
    <c:if test="${result.flagged eq 'N'}">
    <img id="greenFlag" src="./images/GreenFlag.png" style="cursor:pointer;" title="DeFlagged" ></img>
    </c:if>
	<c:if test="${result.viewFlag eq 'Y' && is_medco_mithra ne 'Y'}">
	<img src="images/lock1.png" height="18" width="18" alt="this case is view by other" title="this case is viewed by other"/>
	</c:if>
	<c:if test="${result.teleStatus ne '' && result.teleStatus ne null}">
	<span class="glyphicon glyphicon-earphone" title="This is a Telephonic Registered case"></span>
	</c:if>
	<c:if test="${result.grievanceFlag ne '' && result.grievanceFlag ne null}">
	<img src="images/InspectionAttachmentFlag.gif" height="18" width="18" alt="Grievance has been raised against this case" title="Grievance has been raised against this case"/>
	</c:if>		
	</td>
	</logic:equal>
	<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<td class="tbcellBorder" width="12%" style="word-wrap:break-word;padding:3px;" nowrap="nowrap" >
	
	<a id="patDtlsImage" style=cursor:pointer; title="Click to View Case Details" align="top" data-toggle="modal" href="#viewDtlsID" onclick="javascript:fn_getPatFullDetails('<bean:write name="result" property="caseId" />')" ><span class="glyphicon glyphicon-folder-open"></span></a>
    <a href="javascript:fn_preauthDetails('<bean:write name="result" property="caseId" />','<bean:write name="result" property="patientId" />');"><bean:write name="result" property="caseNo" /></a>
	<c:if test="${result.teleStatus ne '' && result.teleStatus ne null}">
	<span class="glyphicon glyphicon-earphone" title="This is a Telephonic Registered case"></span>
	</c:if>
	<c:if test="${result.grievanceFlag ne '' && result.grievanceFlag ne null}">
	<img src="images/InspectionAttachmentFlag.gif" height="18" width="18" alt="Grievance has been raised against this case" title="Grievance has been raised against this case"/>
	</c:if> 
	    <c:if test="${result.flagged eq 'Y'}">
    <img id="redFlag" src="./images/RedFlag.png" style="cursor:pointer;" ></img>
    </c:if>
    <c:if test="${result.flagged eq 'N'}">
    <img id="greenFlag" src="./images/GreenFlag.png" style="cursor:pointer;"  ></img>
    </c:if>
	</td> 
	</logic:notEqual>

	<td  class="tbcellBorder visible-lg visible-md visible-sm" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="patientName" /></td>

    <td  class="tbcellBorder visible-lg visible-md" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="hospName" /></td> 
    <logic:notEmpty  name="result" property="enhFlag">
    <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="enhReqAmt" /> (Enhancement)</td>
    </logic:notEmpty>
     <logic:empty  name="result" property="enhFlag">
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="totPckgAmt" /></td>
	</logic:empty>
	<td align="center" class=" tbcellBorder"  width="100">
							
							<a href="#" id="approveS" onclick="javascript:fn_SubmitSingleRow('<fmt:message key='EHF.Button.Approve' />','Approve','<bean:write name="result" property="caseId"/>')" data-toggle="class" title="Click to Approve" width="75">
							<span class=" glyphicon glyphicon-ok text-success text-active"></span>
							<span class=" glyphicon glyphicon-ok text-danger text"></span>
							</a>&nbsp;
						
							
							<a href="#" id="RejectS" onclick="javascript:fn_SubmitSingleRow('<fmt:message key='EHF.Button.Reject' />','Reject','<bean:write name="result" property="caseId"/>')" data-toggle="class" title="Click to Reject" >
							<span class=" glyphicon glyphicon-remove text-success text-active"></span>
							<span class=" glyphicon glyphicon-remove text-danger text"></span>
							</a>&nbsp;
							
							</td>
	
	
	<c:set var="count" value="${count+1}"></c:set>
	</tr>
</logic:iterate>
</table>

<br><br>
<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="center">
							<button class="but but"  id="approveBtn" onclick="javascript:submitNxtLvlConfirm('<fmt:message key='EHF.Button.Approve' />','Approve')" type="button"> <span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>
							
							&nbsp;<button class="but but"  type="button" id="rejectBtn" onclick="javascript:submitNxtLvlConfirm('<fmt:message key='EHF.Button.Reject' />','Reject')"  ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>
						</div>



</logic:greaterThan>

<logic:equal value="0" name="lstCaseSearchSize">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >
<td align="center" height="50">
<b>No results found</b>
</td>
</tr>
</table>
</logic:equal>
<html:hidden property="rowsPerPage"  name="casesSearchFormClaims"/>
<html:hidden property="startIndex" name="casesSearchFormClaims" />
<html:hidden property="showPage" name="casesSearchFormClaims" />
<html:hidden property="totalRows" name="casesSearchFormClaims" />
<html:hidden property="casesForApprovalFlag" name="casesSearchFormClaims" />
<html:hidden property="casesSelected" name="casesSearchFormClaims" />

<html:hidden property="caseSearchType" name="casesSearchFormClaims" value="${caseSearchType}" />
<html:hidden property="caseForApproval" name="casesSearchFormClaims"  value="Y"/>
<html:hidden property="module" name="casesSearchFormClaims" value="${module}"/>
<html:hidden property="ceoFlag" name="casesSearchFormClaims" value="Y" />

<html:hidden property="showScheme" name="casesSearchFormClaims" /></div>










</html:form>
<script type="text/javascript">
	
function fn_getPatFullDetails(caseIdd){
	var url='/Operations/patCommonDtls.htm?actionFlag=getCaseDetails&CaseId='+caseIdd;
    document.getElementById('complaintFrame').src=url;
	//centerPopup("#popupRaiseComplaint");
	//loadPopup("#popupRaiseComplaint");
	//document.getElementById('popupRaiseComplaint').style.top=elemJqueryScrollTop+"px" ;	
	//document.getElementById('popupRaiseComplaint').style.left="120px";
	//document.getElementById('popupRaiseComplaint').style.right="0px";
}
	parent.fn_removeLoadingImage();
</script>

<script>
var searchType='${caseSearchType}';
var errSearchType='${errSearchType}';
var disSearchType='${disSearchType}';
var module='${module}';
var patientScheme="${patientScheme}";
var ceoFlag="${ceoFlag}";

var caseSearchURl='';


function focusBox(id)
{
	aField=id;
	setTimeout("aField.focus()",0);
}
/*function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";
}*/
function fn_removeLoadingImage()  
{
	document.getElementById('processImagetable').style.display="none";  
}
		
function fn_caseApproval(caseId,arg,ipOpType)
{ 

	
	if(parent.parent.globalURl==''){
		 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
		 parent.setGlobalUrl(caseSearchURl);
	}
	//parent.fn_resizePage2(470);
	document.forms[0].action='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&caseSearchType='+searchType+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ipOpType='+ipOpType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;	
	document.forms[0].submit(); 
}
function fn_caseApprovalAjax(caseId,arg,ipOpType)
{	

	
	if(parent.parent.globalURl==''){
		 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
		 parent.setGlobalUrl(caseSearchURl);
	}
	//parent.fn_resizePage2(470);
	var flag = null;
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 
	
	 url = '/<%=context%>/casesSearchAction.do?actionFlag=setViewFlag&CaseId='+caseId+'&flag=Y';

		 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {
		    	
		    

		    	url='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&caseSearchType='+searchType+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ipOpType='+ipOpType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&ceoFlag=Y';	
		    	window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
		    	fn_removeLoadingImage();	
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		
	
}
function fn_refresh()
{
	
	window.close();
	caseSearch();
	
}
function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	caseSearch();
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




function showinSetsOf(num)
	{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPage.value='1'; 
	caseSearch();
	}


function caseSearch()
{

	document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ceoFlag=Y';
	
     document.forms[0].submit();
}
function onloadCaseSearch()
{

	document.forms[0].action="/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=preauth&ceoFlag=Y";
	
     document.forms[0].submit();
}
</script>
<script>
var casesSelected=[];
function fn_checkAll()
{
	var elements=document.getElementsByTagName("input");
    casesSelected=[];
	var count=0;
	var count2=0;


	for (i=0;i<elements.length;i++)
	{
		if(elements[i].type=="checkbox")
		{
			count++;
		}
	}

	for (i=0;i<elements.length;i++)
	{
		
	if(elements[i].type=="checkbox")
	{
		if(elements[0].checked==true)
		{
			elements[i].checked=true;
			count2++;
			if(count2 < count)
			casesSelected[i]=elements[i+1].value;
			if(i>0)
			{
			document.getElementById("row"+i).style.backgroundColor="#CAFFDC";
			
			}
			document.getElementById('approveBtn').focus();
		}
		else if(elements[0].checked==false)
		{
			elements[i].checked=false;
			casesSelected=null;
			if(i>0)
			{
			document.getElementById("row"+i).style.backgroundColor="#FFFFFF";
			
			}
		}
	
}
	}
	
	document.forms[0].casesSelected.value=casesSelected;

	
	
}
</script>
<script>
function checkOrUncheckCase()
{
var elements=document.getElementsByTagName("input");
	
	var count=0;
	var count2=0;
    casesSelected=[];

	for (i=0;i<elements.length;i++)
	{
		
	if(elements[i].type=="checkbox")
	{
		if(elements[i].checked==true)
		{
			casesSelected[i]=elements[i].value;
			if(i>0)
			document.getElementById("row"+i).style.backgroundColor="#CAFFDC";
			
		}
		else if(i>0)
		{
			
			document.getElementById("row"+i).style.backgroundColor="#FFFFFF";
			
		}
	
     }
	
	}
	
	document.forms[0].casesSelected.value=casesSelected;

	
}

function fn_SubmitSingleRow(buttonId,confirmMsg,caseId)
{
	if(casesSelected!=null)
var length=casesSelected.length;


var count=0;
var caseIdSel;
if(length!=null)
{
	for(i=0;i<length;i++)
	{
	if(casesSelected[i]!=null && casesSelected[i]!="")
	{
		caseIdSel=casesSelected[i];
		count++;
	}
	}
}

if(count>1)
	{
		alert("Multiple Cases are Selected.Please Click Approve/Reject button");
document.getElementById("approveBtn").focus();
		return false;
	}
	else if(count==1)
	{
		if(caseIdSel!=caseId)
		{
		alert("Please select the corresponding checkBox and try Again");
		return false;
		}
		else
		{	
			submitNxtLvlConfirm(buttonId,confirmMsg);

		}
	}
	else if(count==0)
	{
		casesSelected=caseId;
		document.forms[0].casesSelected.value=casesSelected;
		submitNxtLvlConfirm(buttonId,confirmMsg);
	}
	
	}

function submitNxtLvlConfirm(buttonId,confirmMsg)
	{	
		//document.getElementById('buttonBlock').disabled=true;	
		//disableDiv(document.getElementById('buttonBlock'));
		
			var count=0;
			
			if(casesSelected!=null)
			var length=casesSelected.length;
var caseIdSel;
if(length!=null)
{
	for(i=0;i<length;i++)
	{
	if(casesSelected[i]!=null && casesSelected[i]!="")
	{
		caseIdSel=casesSelected[i];
		count++;
	}
	}
}
	
			if(count==0)
{
	alert("No Cases are Selected.Please Select atleast one case");
	return false;
}
			
			document.getElementById('approveBtn').disabled=true;
			document.getElementById('rejectBtn').disabled=true;
			document.getElementById('approveS').disabled=true;
			document.getElementById('RejectS').disabled=true;

			//document.getElementById('progressBar').style.display='block';
			//document.getElementById('progressBar').focus();


			$(function () { $('#progressBar').modal({
				backdrop : 'static',
				keyboard : false,
				show: true
			   })});
		
	    	document.forms[0].action="/<%=context%>/casesSearchAction.do?actionFlag=submitNextLvlCeo&viewType=ceo&buttonVal="+buttonId;
		    document.forms[0].submit();
			
	}
function fn_back()
{
	parent.fn_dashboard(); 
}

function fn_preauthDetails(caseId,patientId)
{	
	 var url1="/<%=context%>/preauthDetails.do?actionFlag=preauthDetailsCeo&caseId="+caseId+"&patientId="+patientId+"&caseApprovalFlag=Y";
		$(function () { $('#progressBar').modal({
			backdrop : 'static',
			keyboard : false,
			show: true
		   })});
	   document.forms[0].action=url1;   
	   //document.forms[0].target="bottomFrame";
	   document.forms[0].submit();
	}
</script>
</body>
</fmt:bundle>
</html>