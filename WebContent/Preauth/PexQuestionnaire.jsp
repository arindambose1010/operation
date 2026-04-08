 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questionnaire</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<LINK rel="stylesheet" href="css/commonEhfCss.css" type="text/css" media="screen" /> 
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%>
<script type="text/javascript">
function checkRadio(val,type)
{
	var value = "document.forms[0]."+type+".value";
	value=val;
}
function fn_saveDtls()
{
	searchEles = document.getElementById("selectionBlock").children; 
	alertMsg ="Please fill all the questions";
	var cnt =0;
	var cntTemp =0;
	for(var i = 0; i < searchEles.length; i++) 
	{   
		if(searchEles[i].type == 'radio')
		{				
			if(document.getElementById(searchEles[i].id).checked)
			{
				cntTemp=cntTemp+1;
			}
			cnt=cnt+1;
		} 
	}
	cnt = cnt /2; 
    if(cntTemp != cnt)
    {
    	alert(alertMsg);
    	return;
    }
    else
    	{
    	alertMsg ="";
    	}
    if(alertMsg !="")
    	{
    	document.forms[0].action="/<%=context%>/preauthDetails.do?actionFlag=submitQuestions";
    	document.forms[0].submit();		
    	}
	
	}
	function fn_disble()
	{
		if('${disableQues}' != null && '${disableQues}' =='Y')
			{
			document.getElementById('selectionBlock').disabled=true;
			window.opener.document.forms[0].quesFlag.value='Y';
			}
	}
</script>

</head>
<body onload="javascript:fn_disble()">
<html:form action="/preauthDetails.do" enctype="multipart/form-data" method="post">
<c:if test="${quesMsg ne null}">
<script>
alert('${quesMsg}');
window.opener.document.forms[0].quesFlag.value='Y';
</script>
</c:if>
<div id="selectionBlock" >
<table border="0" width="100%"  cellpadding="3" cellspacing="8" align="center" class="tableClass1">
<tr ><td colspan="5" align="center" class="tbheaderLightSeaGreen" ><b>Card Verification</b> </td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td></td><td><b>Enrollment</b></td><td><b>Patient Registration</b></td><td><b>Matching ? </b></td></tr>
<tr><td>Number Verification</td><td>${preauthVO.enrollCardNo }</td><td>${preauthVO.patCardNo }</td>
<td>
<html:radio name="preauthDetailsForm" property="numRadio" styleId="numRadio" value="Y" onclick="javascript:checkRadio('Y','numRadio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="numRadio" styleId="numRadio" value="N" onclick="javascript:checkRadio('N','numRadio')"/>&nbsp;&nbsp;No
</td></tr>

<tr><td>Name Verification</td><td>${preauthVO.enrollName }</td><td>${preauthVO.patName }</td>
<td>
<html:radio name="preauthDetailsForm" property="nameRadio" styleId="nameRadio" value="Y" onclick="javascript:checkRadio('Y','nameRadio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="nameRadio" styleId="nameRadio" value="N" onclick="javascript:checkRadio('N','nameRadio')"/>&nbsp;&nbsp;No
</td></tr>

<tr><td>Age Verification</td><td>${preauthVO.enrollAge }</td><td>${preauthVO.patAge }</td>
<td>
<html:radio name="preauthDetailsForm" property="ageRadio" styleId="ageRadio" value="Y" onclick="javascript:checkRadio('Y','ageRadio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="ageRadio" styleId="ageRadio" value="N" onclick="javascript:checkRadio('N','ageRadio')"/>&nbsp;&nbsp;No
</td></tr>

<tr><td>Sex Verification</td><td>${preauthVO.enrollSex }</td><td>${preauthVO.patSex }</td>
<td>
<html:radio name="preauthDetailsForm" property="genderRadio" styleId="genderRadio" value="Y" onclick="javascript:checkRadio('Y','genderRadio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="genderRadio" styleId="genderRadio" value="N" onclick="javascript:checkRadio('N','genderRadio')"/>&nbsp;&nbsp;No
</td></tr>
<tr><td>Photo Verification</td><td><img src="Common/showDocument.jsp" height=100 width=100  ></td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="photoradio" styleId="photoradio" value="Y" onclick="javascript:checkRadio('Y','photoradio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="photoradio" styleId="photoradio" value="N" onclick="javascript:checkRadio('N','photoradio')"/>&nbsp;&nbsp;No
</td></tr>

</table>

<table border="0" width="100%"  cellpadding="3" cellspacing="8" align="center" class="tableClass1" >
<tr ><td colspan="5" align="center" class="tbheaderLightSeaGreen" ><b>Document Verification</b> </td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td></td><td><b>Online Details</b></td><td><b>Preauth Form Matching</b></td><td><b>Consent & Counselling Form Matching ? </b></td></tr>
<tr><td>Name Verification</td></tr>
<tr><td>Patient</td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="prePat1Radio" styleId="prePat1Radio" value="Y" onclick="javascript:checkRadio('Y','prePat1Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="prePat1Radio" styleId="prePat1Radio" value="N" onclick="javascript:checkRadio('N','prePat1Radio')"/>&nbsp;&nbsp;No
</td>
<td>
<html:radio name="preauthDetailsForm" property="conPat1Radio" styleId="conPat1Radio" value="Y" onclick="javascript:checkRadio('Y','conPat1Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="conPat1Radio" styleId="conPat1Radio" value="N" onclick="javascript:checkRadio('N','conPat1Radio')"/>&nbsp;&nbsp;No
</td></tr>

<tr><td>Treating doctor</td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="preDoc1Radio" styleId="preDoc1Radio" value="Y" onclick="javascript:checkRadio('Y','preDoc1Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="preDoc1Radio" styleId="preDoc1Radio" value="N" onclick="javascript:checkRadio('N','preDoc1Radio')"/>&nbsp;&nbsp;No
</td>
<td>
<html:radio name="preauthDetailsForm" property="conDoc1Radio" styleId="conDoc1Radio" value="Y" onclick="javascript:checkRadio('Y','conDoc1Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="conDoc1Radio" styleId="conDoc1Radio" value="N" onclick="javascript:checkRadio('N','conDoc1Radio')"/>&nbsp;&nbsp;No
</td></tr>

<tr><td>RAMCO</td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="preRamco1Radio" styleId="preRamco1Radio" value="Y" onclick="javascript:checkRadio('Y','preRamco1Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="preRamco1Radio" styleId="preRamco1Radio" value="N" onclick="javascript:checkRadio('N','preRamco1Radio')"/>&nbsp;&nbsp;No
</td>
<td>
<html:radio name="preauthDetailsForm" property="conRamco1Radio" styleId="conRamco1Radio" value="Y" onclick="javascript:checkRadio('Y','conRamco1Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="conRamco1Radio" styleId="conRamco1Radio" value="N" onclick="javascript:checkRadio('N','conRamco1Radio')"/>&nbsp;&nbsp;No
</td></tr>
<tr><td>Signature Verification</td></tr>
<tr><td>Patient</td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="prePat2Radio" styleId="prePat2Radio" value="Y" onclick="javascript:checkRadio('Y','prePat2Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="prePat2Radio" styleId="prePat2Radio" value="N" onclick="javascript:checkRadio('N','prePat2Radio')"/>&nbsp;&nbsp;No
</td>
<td>
<html:radio name="preauthDetailsForm" property="conPat2Radio" styleId="conPat2Radio" value="Y" onclick="javascript:checkRadio('Y','conPat2Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="conPat2Radio" styleId="conPat2Radio" value="N" onclick="javascript:checkRadio('N','conPat2Radio')"/>&nbsp;&nbsp;No
</td></tr>
<tr><td>Treating doctor</td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="preDoc2Radio" styleId="preDoc2Radio" value="Y" onclick="javascript:checkRadio('Y','preDoc2Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="preDoc2Radio" styleId="preDoc2Radio" value="N" onclick="javascript:checkRadio('N','preDoc2Radio')"/>&nbsp;&nbsp;No
</td>
<td>
<html:radio name="preauthDetailsForm" property="conDoc2Radio" styleId="conDoc2Radio" value="Y" onclick="javascript:checkRadio('Y','conDoc2Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="conDoc2Radio" styleId="conDoc2Radio" value="N" onclick="javascript:checkRadio('N','conDoc2Radio')"/>&nbsp;&nbsp;No
</td></tr>
<tr><td>RAMCO</td><td></td>
<td>
<html:radio name="preauthDetailsForm" property="preRamco2Radio" styleId="preRamco2Radio" value="Y" onclick="javascript:checkRadio('Y','preRamco2Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="preRamco2Radio" styleId="preRamco2Radio" value="N" onclick="javascript:checkRadio('N','preRamco2Radio')"/>&nbsp;&nbsp;No
</td>
<td>
<html:radio name="preauthDetailsForm" property="conRamco2Radio" styleId="conRamco2Radio" value="Y" onclick="javascript:checkRadio('Y','conRamco2Radio')"/>&nbsp;&nbsp;Yes
	<html:radio name="preauthDetailsForm" property="conRamco2Radio" styleId="conRamco2Radio" value="N" onclick="javascript:checkRadio('N','conRamco2Radio')"/>&nbsp;&nbsp;No
</td></tr>

<tr><td>&nbsp;</td></tr>
<c:if test="${disableQues ne 'Y'}" > 
<tr><td colspan="5" align="center"><input type="button" name="saveData" value="Save" style="width:100px" onclick="javascript:fn_saveDtls()"  /></td></tr>
</c:if>
</table>
</div>

<html:hidden name="preauthDetailsForm" property="enrollCardNo" value="${preauthVO.enrollCardNo }"/>
<html:hidden name="preauthDetailsForm" property="patCardNo" value="${preauthVO.patCardNo }"/>
<html:hidden name="preauthDetailsForm" property="enrollName" value="${preauthVO.enrollName }"/>
<html:hidden name="preauthDetailsForm" property="patName" value="${preauthVO.patName }"/>
<html:hidden name="preauthDetailsForm" property="enrollAge" value="${preauthVO.enrollAge }"/>
<html:hidden name="preauthDetailsForm" property="patAge" value="${preauthVO.patAge }"/>
<html:hidden name="preauthDetailsForm" property="enrollSex" value="${preauthVO.enrollSex }"/>
<html:hidden name="preauthDetailsForm" property="patSex" value="${preauthVO.patSex }"/>
<html:hidden name="preauthDetailsForm" property="enrollPotoAttach" value=""/>
<html:hidden name="preauthDetailsForm" property="patPotoAttch" value=""/>
<html:hidden name="preauthDetailsForm" property="patientId" />
<html:hidden name="preauthDetailsForm" property="quesFlag" />

<input type="hidden" name="caseId" id="caseId" value="${preauthVO.caseId}" />
</html:form>
</body>
</html>