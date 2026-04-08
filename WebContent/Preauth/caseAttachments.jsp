 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
  <%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Case Attachments</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeScrollbar.jsp"%>
<script type="text/javascript">

function right(e) 
{
    if (navigator.appName == 'Netscape' && (e.which == 3 || e.which == 2))
        return false;
    else if (navigator.appName == 'Microsoft Internet Explorer' && (event.button == 2 || event.button == 3)) 
    {
        alert("Sorry, you do not have permission to right click");
        return false;
    }
    return true;
}

	function validate()
	{
		 vFileName = document.getElementById('fileUpload').value;
		 var falg= parent.validateCaseAttach(vFileName);
         if(falg=='success')
           {
        	 document.CaseAttachments.action="/<%=context%>/preauthDetails.do?actionFlag=uploaddCaseAttach&caseId=${caseId}&surgId=${surgeryId}&splinvestid=${investId}&spltype=${spltType}";
            document.CaseAttachments.submit(); 
        	 }
		
	}
	function fn_openAtachment(filepath,fileName)
	{  
	    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath+'&fileName='+fileName;
	    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	}
	function fn_delete(sno,filename)
	{
		parent.fn_deleteCaseAttachments(window.name,sno,filename);
	}
	function fn_delete1(sno,filename)
	{
	
    	for(var i=0;i<=parent.dbFilesArray.length;i++)
		{
		if(parent.dbFilesArray[i]==filename)
			{
			delete parent.dbFilesArray[i];
			}
		}
	 document.CaseAttachments.action="/<%=context%>/preauthDetails.do?actionFlag=deleteSplInvstAttch&caseId=${caseId}&surgId=${surgeryId}&splinvestid=${investId}&spltype=${spltType}&sno="+sno;
     document.CaseAttachments.submit(); 
      
	}
	
	
</script>
</head>
<body>

<form name="CaseAttachments" action="/preauthDetails.do" enctype="multipart/form-data" method="post">
<table border="0" >
<c:if test="${resMsg ne null}" >
<script>
parent.fn_alertResultMsg('${resMsg}');
//alert('${resMsg}');
</script>
</c:if>

<logic:present name="preauthDetailsForm" property="lstAttachments">
<bean:size id="attachSize" name="preauthDetailsForm" property="lstAttachments"/>
<logic:greaterThan value="0" name="attachSize">
<logic:iterate id="result" name="preauthDetailsForm" property="lstAttachments">
<script>
parent.dbFilesCnt++;
		parent.dbFilesArray[parent.dbFilesCnt]=new Array();
	    parent.dbFilesArray[parent.dbFilesCnt][0]="<bean:write name="result" property="fileName"/>";
	    </script>
<tr><td >
<a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('<bean:write name="result" property="savedName"/>','<bean:write name="result" property="savedName"/>');return false;"><bean:write name="result" property="fileName"/></a>
<input type="file" name='attachedIndex[1]' id='fileUpload' class="FieldBlack"   onmousedown="right(this)"  onmouseup="right(this)" > </input>
  <button class="but"   type="button" name="upload" value="upload" id="upload" onclick="javascript:validate()" >Upload</button>
</td>
<td>
<!-- &nbsp;&nbsp;<button class="but"   type="button" name="delete" value="delete" id="delete" onclick="javascript:fn_delete('<bean:write name="result" property="docCount"/>','<bean:write name="result" property="fileName"/>')" >Delete</button> -->
<input type="hidden" name="fileName" value='1' id="fileName" />
<input type="hidden" name="filePath<bean:write name="result" property="surgInvstId"/>" value='<bean:write name="result" property="docCount"/>' id="filePath<bean:write name="result" property="surgInvstId"/>" />
</td></tr>
</logic:iterate>
</logic:greaterThan>
 <logic:equal value="0" name="attachSize"> 
<tr><td nowrap="nowrap">
  <input type="file" name='attachedIndex[1]' id='fileUpload' class="FieldBlack"   onmousedown="right(this)"  onmouseup="right(this)" > </input>
  <button class="but"   type="button" name="upload" value="upload" id="upload" onclick="javascript:validate()" >Upload</button>
  <input type="hidden" name="fileName" value='0' id="fileName" />
  
</td></tr>
</logic:equal> 
</logic:present>

<%-- <c:if test="${fn:length(lstAttachmentVO) gt 0 }">
<c:forEach items="${lstAttachmentVO}" var="attach">
${attach.fileName}
</c:forEach>
</c:if>
<c:if test="${fn:length(lstAttachmentVO) eq 0 }">
<tr><td>
  <input type="file" name='attachedIndex[1]' id='fileUpload' class="FieldBlack"   onmousedown="right(this)"  onmouseup="right(this)" > </input>
  <input type="button" name="upload" value="upload" id="upload" onclick="javascript:validate()"/>
</td></tr>
</c:if> --%>
</table>
</form>
<input type="hidden" name="caseId"  value="${caseId}"/>
<input type="hidden" name="investId"  value="${investId}"/>
<input type="hidden" name="spltType"  value="${spltType}"/>
<input type="hidden" name="surgeryId"  value="${surgeryId}"/>
<input type="hidden" name="splCnt" value="0" id="splCnt" />
</body>
</html>