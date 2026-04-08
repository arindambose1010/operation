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
		<tr valign="top" ><td  style="border:2px solid gray;background-color: white;width:255px;"  >
		<table align="center" 
			style="padding-top: 2px; background-color: white;width:100%" >
		
			<c:if test="${scheme == 'CD202'}">

			<tr><td colspan="3" style="width:100%" align="center"><img src="images/TG_logo.png" width="17%" ></td></tr>

			<tr>
				<td width="17%"></td>
				<td nowrap="nowrap" align="left" width="40%"><b><span style="font-size:10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="label.EHF.Card.TgGovt"/></span><br>
					<fmt:message key="label.EHF.Card.Ehs"/></b></td>
					<td width="15%"></td>

			</tr>
			
			<!--<tr align="center">
				<td nowrap="nowrap" align="center" >&nbsp;<b><fmt:message key="label.EHF.Card.Ehs"/><br> &nbsp;
					<fmt:message key="label.EHF.Card.TgGovt"/></b></td>
			</tr>
			--><tr align="center">
				<td colspan="3"><b><u><fmt:message key="label.EHF.Card.healthCard" /></u></b>
				</td>
			</tr>
			<tr align="center" >
				<td colspan="3"><b>${cardNo}</b>
				</td>
			</tr>	
			</c:if>	
			
		
	</table>
<table style="padding-left: 10px;  background-color: white; width: 245px;auto;">
			<tr>
				<td style="word-break:break-all;" colspan="3"><b>${benificiaryName}</b>,&nbsp;
			<b>
<c:if test="${gender == 'Male'}">
<fmt:message key="label.EHF.Card.Male"/>
</c:if>
<c:if test="${gender == 'Female'}">
<fmt:message key="label.EHF.Card.Female"/> 
</c:if>	
</b></td>
</tr>
<tr align="left" style="height: 10px;padding-top: 5px;">

				<td valign="top" colspan="4" ><b><fmt:message key="label.EHF.Card.DOB" /> &nbsp;: ${dateOfBirth},&nbsp;<fmt:message key="${relation}"/></b></td>
</tr>

<tr align="center">
<td colspan="4"><br> <img id="photo" height="120px;" width="80px"></td>
</tr>
</table>



<table align="center" style="background-color: white; width: 245px;auto;break-word;">
			<tr align="left" style="height: 70px" valign="top" >
			<td style="word-break:break-all;"> <b><fmt:message key="label.EHF.Card.Address"/> :</b>  ${address}
</td>
</tr>
<tr align="left"  >
<td style="padding-top: 10px" style="word-break:break-all;"><b> 
<c:if test="${empType == 'Employee'}">
<b><fmt:message key="label.EHF.Card.Employee"/> : </b>${empName}
</c:if>
<c:if test="${empType == 'Pensioner'}">
<b><fmt:message key="label.EHF.Card.Pensioner"/> : </b>${empName}
</c:if>	
<c:if test="${empType == 'Family Pensioner'}">
<b><fmt:message key="label.EHF.Card.FamilyPensioner"/> : </b>${empName}
</c:if>	
</td>
</tr>

<tr  align="left">
<td style="padding-bottom: 4px"><b>
<c:if test="${empType == 'Employee'}">
<b><fmt:message key="label.EHF.Card.EmpIdNo"/> : </b>${empNo}
</c:if>
<c:if test="${empType == 'Pensioner'}">
<b><fmt:message key="label.EHF.Card.PensionerIdNo"/> : </b>${empNo}
</c:if>
<c:if test="${empType == 'Family Pensioner'}">
<b><fmt:message key="label.EHF.Card.FamilyPensionerIdNo"/> : </b>${empNo}
</c:if>
</td>



<c:if test="${fn:length(aadharId) gt 0 || fn:length(aadharEId) gt 0 }">
<tr align="left"><td valign="top" align="left" ><b><fmt:message key="label.EHF.Aadhar"/>/<fmt:message key="label.EHF.AadharEid"/> :</b></td></tr>
</c:if>
<c:if test="${fn:length(idType) gt 0}">
<c:if test="${idType=='a'}">
<tr><td>${aadharId}&nbsp;</td></tr>
</c:if>
<c:if test="${idType=='e'}">
<tr><td>${aadharEId}&nbsp;</td></tr>
</c:if>
</c:if>
<c:if test="${fn:length(idType) == 0}">
<c:if test="${fn:length(aadharId) gt 0}">
<tr><td>${aadharId}&nbsp;</td></tr>
</c:if>
<c:if test="${fn:length(aadharEId) gt 0}">
<tr><td>${aadharEId}&nbsp;</td></tr>
</c:if>
</c:if>
</table>



</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td style="border:2px solid gray;background-color: white;width:255px; padding-bottom: 8px" valign="bottom" >

<table align="center" 
			style="padding-top: 15px; background-color: white;  width: 235px;">
<tr style="height: 80%" ><td colspan="2"></td></tr>			
<c:if test="${fn:length(apEmployee) eq 0}">
<tr><td valign="top" nowrap="nowrap" align="left" ><b><fmt:message key="label.EHF.Card.Issued"/>  </b><br><fmt:message key="label.EHF.CollectorAndMagistrate"/>,<br><fmt:message key="${district}"/>&nbsp;<fmt:message key="label.EHF.District"/></td></tr>
</c:if>
<c:if test="${fn:length(apEmployee) gt 0}">

<tr><td valign="top" nowrap="nowrap" align="left" ><b><fmt:message key="label.EHF.Card.Issued"/></b><br>${issuedBy}</td></tr>
</c:if>
<tr style="padding-bottom: 55px" ><!--<td valign="top" nowrap="nowrap" align="left" ><b><fmt:message key="label.EHF.Card.valid"/>&nbsp; : &nbsp;05-03-2014&nbsp;<fmt:message key="label.EHF.Card.Till"/></b></td>--></tr>
		
		</table>
		</td></tr>


</table>

	</div>
	<div align="center">
	
	<br>
		
	<button  class="but" type="button"  id="printButton" class="Button" tabindex="7" onclick="fn_printPage('printDiv');">Print</button>

</div>

<br>
<!--<br>
<div>
<p>
<font color="red">
Note <sup>*</sup> : This Temporary Health Card Can Be Utilized Only After Orders Are Issued By GOVT. On Employees Health Scheme
</font>
</p>
</div>
--><script>
var enrolId='${enrolId}';
var seqNo='${seqNo}';
var langId='${lang_id}';

function fn_printPage(divName){

document.getElementById("printButton").style.display="none";
 
    window.print();
    setTimeout("showPrintButton()", 3000)
	
}
function showPrintButton(){
	
	/* document.getElementById("saveButton").style.display=""; */
	document.getElementById("printButton").style.display="";
	
}

/* function fn_savePage(){
	var pageFrom;
	pageFrom='${pageFrom}';
	var empNo='${empNo}';
	if(pageFrom=='Admin'){
		
	var url='/EHS/vendorCardIssue.do?actionFlag=saveCardAsPdf&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId+'&userName='+empNo;
//	window.open(url,"ehfCard",'toolbar=no,resizable=no,scrollbars=no,menubar=no,location=no,height=100,width=100,left=110,top=100');
	
	}
	else{
		var url='/EHS/vendorCardIssue.do?actionFlag=saveCardAsPdf&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId;
	}
	document.forms[0].action=url;
	 document.forms[0].submit();
} */
document.getElementById("photo").src='cardSearchAction.do?actionFlag=viewPhoto&fileName='+'${photo}'; 
</script>
</html:form>
</body>
</fmt:bundle>
</html>
