<!DOCTYPE html>
<%@page import="com.lowagie.text.Document"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
<head>
<fmt:setLocale value='${langID}'/>  

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Preauth Approved Cases Search</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style type="text/css">.centerone{padding-left:6%;}</style>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="scripts/PreauthScripts.js"></script>
<script src="js/jquery.msgBox.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
 
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<script type="text/javascript">

function fn_preauthDetails(caseId,patientId)
{	
	 var url1="/Operations/casesSearchAction.do?actionFlag=preauthDetailsDME&UpdType=ehfPreauth&DME=Y&caseId="+caseId+"&patientId="+patientId;
	   document.forms[0].action=url1;   
	   //document.forms[0].target="bottomFrame";
	   document.forms[0].submit();
}
function fn_success()
{
	var msg="${eoMsg}";
	var noRec = "${noRec}";
	if(msg == "success")
		{
		var alertmsg="DME flagged case has been updated ";
		bootbox.alert({
			     message: alertmsg,
			     callback: function () {
			    	 parent.fn_DMEFlagCases();
			     }
			 });
		
		}
	if(msg == "failure")
		{
		alert("Failure");
		}
}
function showinSetsOf(num)
{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPages.value='1'; 
	fn_pagination(1,num);	
}
function showPagination(num)
{	
	document.forms[0].showPages.value=num; 
	fn_pagination(num,document.forms[0].rowsPerPage.value);	
}
function fn_pagination(pageNo,rowsPerPage) 
{								
	document.forms[0].action = '/Operations/casesSearchAction.do?actionFlag=dmeFlagCases&pageNo='+pageNo+'&rowsPerPage='+rowsPerPage+'&dmeFlag=Y';
	document.forms[0].submit();
}
</script>
</head>

<body onload="fn_success()">
	<html:form    action="/casesSearchAction.do" enctype="multipart/form-data" method="post">
		<div class="container-fluid">
			<div class="tbheader"><b>DME Flagged Cases</b></div> 
			<div>	
			<div class="clearfix"></div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<logic:present name="casesSearchFormClaims" property="preauthCaseSearch">
		 	<bean:size id="PreauthCaseSearchSize" name="casesSearchFormClaims"  property="preauthCaseSearch"/>
				<logic:greaterThan value="0" name="PreauthCaseSearchSize"> 
				<div class=" col-sm-12 col-xs-12 col-lg-12 col-md-12">
							<div class="leftone col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<ul class="pagination">
								<li class="lispacing">&nbsp;<b>Showing Results</b>&nbsp; </li>
								<li class="lispacing"><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
								of  <b><c:out value="${totalRecords}" /></b></li>
							</ul>
						</div>
						<div class="centerone col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<ul class="pagination"> 
							<li><b>Show Page</b>&nbsp;</li>
 							<logic:notEmpty name="casesSearchFormClaims" property="prev"> 
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
							<logic:notEmpty name="casesSearchFormClaims" property="next">
							<li><span class="glyphicon glyphicon-forward" onclick="javascript:showPagination('next') "></span></li>
							</logic:notEmpty> 
							</c:if>
							</ul>
						</div>
						<div class="rightone visible-md visibel-lg col-lg-4 col-md-4 col-sm-4 col-xs-4">
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
								</ul>
							</div>
						</div>
						<%Integer i = Integer.parseInt(request.getAttribute("startIndex").toString());%>
				
					<table  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
						<tr>
							<th class="tbheader1" width="5%" valign="top"><b>S.NO</b>
							<th class="tbheader1" width="8%" valign="top">Case ID</th>
							<th class="tbheader1" width="10%" valign="top">PATIENT NAME</th>
							<th class="tbheader1" width="10%" valign="top">Card Number</th>
							<th class="tbheader1" width="15%" valign="top">Hospital Name</th>
							<th class="tbheader1" width="10%" valign="top">Preauth Approved Date</th>
							<th class="tbheader1" width="20%" valign="top">DME Remarks</th>
							
						</tr>
						 <logic:iterate id="preauthCaseSearch" name="casesSearchFormClaims"  property="preauthCaseSearch" >
						 <tr>
						 	<td class="tbcellBorder"><%=i++ %></td>
						 	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">
						 	<a href="javascript:fn_preauthDetails('<bean:write name="preauthCaseSearch" property="CASEID" />','<bean:write name="preauthCaseSearch" property="PATIENTID" />');">
						 	<bean:write name="preauthCaseSearch" property="CASEID" /></a></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="PNAME" /></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="CARDNO" /></td>

							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="HOSPNAME" /></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="PREAUTHDATE" /></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="GENREMARKS" /></td>
						</tr>
						 </logic:iterate>
					</table>
				 </logic:greaterThan> 
				</logic:present>
			</div></div>
			
			<html:hidden property="showPages" name="casesSearchFormClaims" />
			<html:hidden property="startPage" name="casesSearchFormClaims" value="${startPage}" />
			<html:hidden property="endPage" name="casesSearchFormClaims" value="${endPage}"/>
			<html:hidden property="rowsPerPage" name="casesSearchFormClaims" />	
			
		<c:if test="${(noRec == 'Y' )}">	
		<div class="container-fluid center-block">
		<logic:empty name="casesSearchFormClaims"  property="preauthCaseSearch">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss" align="center">
				
				<label><b>No Records found</b></label>
			</div>
		</logic:empty>
		</div></c:if>
		</div>
	</html:form>
</body>

</html>