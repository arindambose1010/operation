<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
     <%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
    <%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Case List</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<style>
.cellAlignRight{align:right;}
</style>
<script type="text/javascript">
function popitup(url) {
	newwindow=window.open(url,'DownloadFiles','width=500, height=500, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
	if (window.focus) {newwindow.focus();}
	return false;
}


function fn_Close()
{
	window.close();
	}

function exportToExcel()
{
	
	// popitup("panelDocPay.do?actionFlag= getAllCasesStatusInExcel&selPeriod="+document.panelDocPayForm.selPeriod.value+"&dispType="+document.panelDocPayForm.dispType.value+"&docId="+document.panelDocPayForm.docid.value+"&actionType="+document.panelDocPayForm.action.value+"&prevGrp="+document.panelDocPayForm.ID.value+"&docDetails="+document.panelDocPayForm.flag.value+"&scheme="+document.panelDocPayForm.scheme.value+"&wId="+document.panelDocPayForm.wId.value);
	
	document.forms[0].action ="panelDocPay.do?actionFlag=getAllCasesStatusInExcel&selPeriod="+document.panelDocPayForm.selPeriod.value+"&dispType="+document.panelDocPayForm.dispType.value+"&docId="+document.panelDocPayForm.docid.value+"&actionType="+document.panelDocPayForm.action.value+"&prevGrp="+document.panelDocPayForm.ID.value+"&docDetails="+document.panelDocPayForm.flag.value+"&scheme="+document.panelDocPayForm.scheme.value+"&wId="+document.panelDocPayForm.wId.value;  
	document.forms[0].submit();
	
}



</script>
</head>
<body>
<html:form action="/panelDocPay.do" method="post" >
<html:hidden name="panelDocPayForm" property="dispType" />
<html:hidden name="panelDocPayForm" property="selPeriod" />
<html:hidden name="panelDocPayForm" property="fromDate" />
<html:hidden name="panelDocPayForm" property="toDate" />
<html:hidden name="panelDocPayForm" property="docid" />
<html:hidden name="panelDocPayForm" property="action" />
<html:hidden name="panelDocPayForm" property="ID" />
<html:hidden name="panelDocPayForm" property="flag" />
<html:hidden name="panelDocPayForm" property="scheme" />
<html:hidden name="panelDocPayForm" property="wId" />
<table width="95%" style="margin:0 auto"  border="0"><tr><td>
<center>
<logic:notEmpty name="panelDocPayForm"  property="panelDocCasesList">
<table border='0' width=95% align="center"><tr><td>

 <table class="tbheader" border=0 >
<tr><th><b>Cases of <bean:write name="panelDocPayForm"  property="flag"/>&nbsp; </b></th></tr>
</table></td></tr>
</table>

<table border='1' width="30%" align="center">
<tr><td>&nbsp;</td></tr>
<tr>
<td>
<display:table  id="panelDocPayVO" name="panelDocPayForm.caseCountStatus" pagesize="100" style="width:100%;align:center;border:2;rowspan:5" export="false" requestURI="/panelDocPay.do"  cellpadding="2" cellspacing="2">
<display:column  property="DISTINCTSTATUS" title="Status"/>
<display:column  property="COUNT" title="Total Cases"/>
</display:table>
</td>
</tr>
<tr><td>&nbsp;</td></tr>
</table>
<br>
<table border='1' width=95% align="center"><tr><td>
<display:table  id="panelDocPayVO" name="panelDocPayForm.panelDocCasesList" pagesize="100" style="width:100%;align:center;border:2;rowspan:5" export="false" requestURI="/panelDocPay.do"  cellpadding="2" cellspacing="2">
<display:column  property="CASE_DATE" title="Date"/>
<display:column  property="CASE_ID" title="Case ID"/>
<display:column  property="HOSP_NAME" title="Hospital Name"/> 
<display:column  property="AMOUNT" title="Amount"/> 
<display:column  property="PARTICULARS" title="Case Status"   /> 
</display:table>

</td></tr>
<tr>
<td align="right" valign="top"><b>Download Report As:</b>
<!-- <img src="images/csv1.png" onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCsv()"/>
<img src="images/excel1.png"  onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/>
<img src="images/pdf1.png" onmouseover="this.src='images/pdf2.png'" onmouseout="this.src='images/pdf1.png'" onclick="javascript:exportToPdf()"/>
<img src="images/xml1.png" onmouseover="this.src='images/xml2.png'" onmouseout="this.src='images/xml1.png'" onclick="javascript:exportToXml()"/> -->
<!-- <img id="pdfImg" src="images/pdf3.png" onmouseover="this.src='images/pdf4.png'" onmouseout="this.src='images/pdf3.png'" onclick="javascript:exportToPdf()"/> -->
<img id="excelImg" src="images/excel1.png" onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/>
<!-- <img id="xmlImg" src="images/xml3.png" onmouseover="this.src='images/xml4.png'" onmouseout="this.src='images/xml3.png'" onclick="javascript:exportToXml()"/>
 <img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:exportToCsv()"/>-->
</td>
</tr>
<tr align="center">
<td><input id="closebutton" type="submit" value="Close"  onclick="javascript:fn_Close()" /></td>
</tr>
</table>
 
</logic:notEmpty>
<logic:empty name="panelDocPayForm"  property="panelDocCasesList">
<table border='1' width=80% align="center"><tr><td>
<table border="0" width="50%"  cellpadding="1" cellspacing="1" align="center">
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td align="center">
No Records Found
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</td></tr></table>
</logic:empty>
</center>
</td></tr></table>
</html:form>
</body>

</html>