<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>
<html> 
<head>
<title>Cases Payment Attachments</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<LINK href="css/style.css" type="text/css" rel="stylesheet"> 
<script src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker1.js"></script>
	<script src="js/DateTimePicker.js"></script>  
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
var UpdType = '${UpdType}';
var folderFlag = '${folderFlag}';
var flag = '${flag}';
function fn_generateFiles(){

	var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType="+UpdType+"&caseAttachmentFlag=CP&fromDatePay="+document.forms[0].fromDate.value+"&toDatePay="+document.forms[0].toDate.value+"&flag="+flag+"&folderFlag="+folderFlag;
	document.forms[0].action=url;   
	document.forms[0].submit(); 
}
function fn_openAtachment(filepath)
{  
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
</script>
</head>
<body>
<html:form action="/attachmentAction.do" enctype="multipart/form-data" method="post">
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;">
<tr><td>&nbsp;</td></tr>
<tr><td>
<table class="tbheader">
<tr align="center">
<td  colspan="8" align="center"  class="tbheader" ><b>Claim Payment Files</b>
</td></tr>
</table>
</td></tr>
<tr><td>
<table border="1px"  width="100%">
<tr><td>
<table width="100%">
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b>From Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="attachmentForm" property="fromDate" style="width:150px;" styleId="fromDate" title="Enter From Date"  readonly="true" /></td>
<td width="16%" class="labelheading1 tbcellCss"><b>To Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="attachmentForm" property="toDate" styleId="toDate" title="Enter To Date"  readonly="true" style="width:150px;"/></td>
</tr>
<tr ><td colspan="8">&nbsp;</td></tr>
<tr ><td colspan="8" align="center">
<button class="but" id="generate"  type="button"  value="generate" name="generate" onclick="javascript:fn_generateFiles();">Generate</button>
</td></tr>

</table></td></tr></table></td></tr>
<tr><td>
<table border="1px"  width="100%" >
<c:if test="${fn:length(AttachmentMap.AttachmentList) lt 1}" >
<tr><td colspan="2" align="center"> No Attachments Found </td></tr>
</c:if>
<c:set var="loopCount" value="0" />
<c:forEach items="${AttachmentMap.AttachmentList}" var="attMap" begin="0" varStatus="status1">
<c:set var="loopCount" value="${loopCount + 1}" /> 
<c:if test="${(loopCount % 3) eq 1}">
	<tr>
	</c:if>	
	<td valign="top" align="center" style="padding: 5px;">
 <a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('${attMap.filePath}');return false;" title="${attMap.fileName}">${attMap.fileName}&nbsp;&nbsp;</a>
 </td>
 <c:if test="${(loopCount % 3) eq 0}">
	</tr>
	</c:if>
</c:forEach>
</table>
</td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td colspan="5" align="center"><button class="but" type="button" id="close" name="close"  class="but" value="close" onclick="window.close();" >Close</button></td></tr>
</table>
<script>

var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

    
	$(function() {
		$( "#fromDate" ).datepicker({
			defaultDate: "+1w",
			dateFormat: "dd/mm/yy",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#toDate" ).datepicker({
			defaultDate: "+1w",
			dateFormat: "dd/mm/yy",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
            buttonImage: "images/calend.gif", 
            buttonText: "Calendar",
            buttonImageOnly: true ,
			numberOfMonths: 1,
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear(),
			onClose: function( selectedDate ) {
				$( "#fromDate" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
		
	});
	</script>
</html:form>
</body>
</html>