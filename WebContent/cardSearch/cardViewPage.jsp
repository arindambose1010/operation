<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/includeBootstrap.jsp"%>
<html>  
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
  
<title>Download Health Card</title>   
<fmt:setLocale value='${lang_id}' />
<fmt:bundle basename="ApplicationResources" >
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>


<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<style>
	body{font-family:Arial, Helvetica, sans-serif;font-size:10px;color:#333;}
	input[type="text"] {font-weight:normal;color:#000;font-size:1em;}
	.tbheader {background: #325F95;color:#fff;font-size:1em;font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
	text-align:center;border-radius: 0.4em;line-height: 1.8em;} 
	.page-header {background: #1E4B89;color:#fff;} 
	.heading{background: #447FCF;}
	#faqHead{background:#688652;}
	#trainingHead{background:#6595B9;}
	aside#sideLinks div {background: #447FCF;}
	.modal-header{background:#688652;color:#fff;}
	.custom-nav li a:hover{background:#447FCF;}

	.labelheading1{color:#447bc6;}
	.tbcellCss{margin:2px; padding:3px;background:#e9f2ff;border:1px solid #bcd8ff;}
	.tbcellBorder{margin:2px; padding:3px;background:#fff;border:1px solid #bcd8ff;}
	.but { background-color: #325F95;color:#fff;font-weight:bold;}
	.but:hover{color:#fff;}
	.tbcellCss3{padding:0.4em;background:#e9f2ff;border:0.1em solid #bcd8ff;margin-bottom:o.4em;}
</style>

</head>
<body>
<html:form action="/cardSearchAction.do" method="post" enctype="multipart/form-data" styleId="cardSearchForm">
<div align="center" id="printDiv"  >
<table>
		<tr valign="top" >
		
		
		<td  style="border:2px solid gray;background-color: white;width:255px; padding-bottom: 10px"  >
		<table align="center" style="padding-top: 2px; width:100%;height:60%" >
			
			<c:if test="${scheme == 'CD202'}">

			<tr><td colspan="3" style="width:100%" align="center"><img src="images/TG_logo.png" width="17%" ></td></tr>

			<tr>
				<td width="17%"></td>
				<td nowrap="nowrap" align="left" width="40%"><b><span style="font-size:10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Government of Telangana</span><br>
					<!-- Health Card --></b></td>
					<td width="15%"></td>

			</tr>
			
			<tr align="center">
				<td colspan="3"><b>Working & Retired Journalists Health Scheme</b>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3"><b><u>HEALTH CARD</u></b>
				</td>
			</tr>
			<tr align="center" >
				<td colspan="3"><b>${cardNo}</b>
				</td>
			</tr>	
			</c:if>	
		</table>
	<table align="left" style="padding-top: 2px; width:100%;height:60%">
			<tr align="left"  >
				<td style="text-transform: initial; word-break:break-all;" colspan="3"><b>${benificiaryName}</b>,</td>
</tr>
<tr align="left" style="height: 10px;padding-top: 5px;">

				<td valign="top" colspan="4" ><b>DOB &nbsp;: ${dateOfBirth},&nbsp;
			<b>
<c:if test="${gender == 'Male'}">
Male
</c:if>
<c:if test="${gender == 'Female'}">
Female 
</c:if>	
</b>,&nbsp;<fmt:message key="${relation}"/></b></td>
</tr>

</table>	
<table align="center" style="width:100%">
 
       <tr align="center">
<td><br> <img id="photo" height="80px;" width="60px"></td>
</tr>
</table>
<table align="center" style="padding-top: 2px; background-color:;width:100%">


<tr>
			<td  style="vertical-align:top;"> <b>Address</b></td>
<td>: ${address}</td>
</tr>
</table>

<table align="center" style="padding-top: 8px; width:100%">

<c:if test="${scheme == 'CD202'}">
<tr><td align="left" ><b>Journalist Name &nbsp;&nbsp;:</b>&nbsp;&nbsp;${empName}</td> </tr>
<tr><td align="left"><b>Journalist Code &nbsp;&nbsp;&nbsp;:</b>&nbsp;&nbsp;${empNo}</td> </tr>
<tr><td align="left"><b>Designation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>&nbsp;&nbsp;Journalist</td> </tr>
<tr><td align="left"><b>Organisation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>&nbsp;&nbsp;Press</td> </tr>

<!-- <tr><td align="center">Journalist Code</td> : <td align="center">Journalist Code</td> </tr>
<tr><td align="center"><b>Journalists Health Scheme</b></td></tr>
</c:if>
<tr><td align="center">8-2-293/82/a/ahct,</td></tr>
<tr><td align="center">Near B.R.Ambedkar University,</td></tr>
<tr><td align="center">Road No:46,Jubilee Hills,Hyderabad-500033,</td></tr>
<tr><td align="center">Ph:(040)23547107</td></tr> -->

</table>
</td>
<!--<table style="padding-left: 10px;  background-color: #A3CC29; width: 245px;auto;">
<tr align="center">N.T.R Vaidya Seva</tr>
<tr align="center"><h5>Working Journalists Health Scheme</h5></tr>

</table>


-->
<td></td>
 <td style="border:3px  solid gray;background-color: white;width:255px; padding: 0px;" > 
	
	<table align="center" style="padding-top: 2px; width:100%;height:60%" >
	<tr><td align="left">Valid till 31<font size="1">st</font> Dec, 2016 </td></tr>
	<tr><td align="left"><b>ID Type  &nbsp;:</b>&nbsp;&nbsp;${idName}</td> </tr>
	<tr>
<c:if test="${fn:length(aadharId) gt 0 || fn:length(aadharEId) gt 0 }">
<td align="left"><b>ID Number &nbsp;:</b></c:if>

<c:if test="${fn:length(idType) gt 0}">
<c:if test="${idType=='a'}">
 ${aadharId}
</c:if>
<c:if test="${idType=='e'}">
 ${aadharEId}
</c:if>
</c:if>
<c:if test="${fn:length(idType) == 0}">
<c:if test="${fn:length(aadharId) gt 0}">
 ${aadharId}
</c:if>
<c:if test="${fn:length(aadharEId) gt 0}">
 ${aadharEId}
</c:if>
</c:if>
</td>
</tr>
<c:if test="${SUBDESC eq 'Y'}" >
<tr><td align="left"><b>Aadhar ID  &nbsp;:</b>
${SUBCODE}
</td> 
</tr>
</c:if>
<c:if test="${SUBDESC ne 'Y'}" >
<c:if test="${adhrExts eq 'Y'}" >
<tr><td align="left"><b>Aadhar ID  &nbsp;:</b>

<c:if test="${fn:length(idType) gt 0}">
<c:if test="${idType=='a'}">
 ${aadharId}
</c:if>
<c:if test="${idType=='e'}">
 ${aadharEId}
</c:if>
</c:if>
<c:if test="${fn:length(idType) == 0}">
<c:if test="${fn:length(aadharId) gt 0}">
 ${aadharId}
</c:if>
<c:if test="${fn:length(aadharEId) gt 0}">
 ${aadharEId}
</c:if>
</c:if>
</td> 
</tr>
</c:if>
<c:if test="${adhrExts ne 'Y'}" >
<tr><td align="left"><b>Aadhar ID  &nbsp;:</b>
&nbsp;&nbsp;--NA--
</td> 
</tr>
</c:if>
</c:if>
	<tr>
	
	<tr><td align="left"><b>Accreditation No &nbsp; :</b>&nbsp;&nbsp;${AccNo}</td> </tr>
	<tr><td align="left"><b>Mobile No  &nbsp;:</b>&nbsp;&nbsp;${mobile}</td> </tr>
	<tr><td align="left"><b>E-mail  &nbsp;&nbsp;:</b>&nbsp;&nbsp;<span style="text-transform: lowercase;">${email}</span></td> </tr>
	</tr>
	</table>
	<table style="padding-top: 220px;">
	
	<tr>
	<tr><td align="left">Issued By : </td></tr>
	<tr><td align="left"><b>Commissioner,Information & Public Relation,</td> </tr>
	<tr><td align="left"><b>Govt. of Telangana,Hyderabad.</td> </tr>
	
	
	
	</tr>
	</table>
	
		 </td> 
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

</tr>
</table>

	</div>
	<div align="center">
	
	<br>
		<!--  <button  class="btn but" type="button"  id="saveButton" class="Button" tabindex="6" onclick="fn_savePage();">Save as PDF</button> --> 
	<button  class="btn but" type="button"  id="printButton" class="Button" tabindex="7" onclick="fn_printPage('printDiv');">Print</button>
</div>


<br>
<script>
var enrolId='${enrolId}';
var seqNo='${seqNo}';
var langId='${lang_id}';
var jrnlstNo='${jrnlstNo}'
function fn_printPage(divName){

document.getElementById("printButton").style.display="none";
 /* document.getElementById("saveButton").style.display="none"; */
/* if('<bean:write name="cardSearchForm" property="cfmsFlag"/>'=='Y')
	document.getElementById("editButton").style.display="none"; */
    window.print();
    setTimeout("showPrintButton()", 3000)
	
}
function showPrintButton(){
	
	/* document.getElementById("saveButton").style.display=""; */
	document.getElementById("printButton").style.display="";
	/* if('<bean:write name="cardSearchForm" property="cfmsFlag"/>'=='Y')
		document.getElementById("editButton").style.display=""; */
}
/* function fn_editCardDtls(){
	
var url='vendorCardIssue.do?actionFlag=editCard&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId;

parent.page=window.open(url,"editCard",'toolbar=no,resizable=yes,scrollbars=yes,menubar=no,location=no,height=450,width=900,left=110,top=35');
}
function fn_savePage(){
	var pageFrom;
	pageFrom='${pageFrom}';
	var empNo='${usrId}';
	if(empNo!=null && empNo!=''){
		
	var url='journalEnrollAction.do?actionFlag=saveCardAsPdf&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId+'&userName='+empNo;
//	window.open(url,"ehfCard",'toolbar=no,resizable=no,scrollbars=no,menubar=no,location=no,height=100,width=100,left=110,top=100');
	
	}
	else{
		var url='journalEnrollAction.do?actionFlag=saveCardAsPdf&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId;
	}
	
	document.forms[0].action=url;
	 document.forms[0].submit();
} */
document.getElementById("photo").src='data:image/jpeg;base64,'+'${photo}';
//document.getElementById("photo").src='journalEnrollAction.do?actionFlag=getPhotoDtls&photo='+'${photo}';
</script>
</html:form>
</body>
</fmt:bundle>
</html>
