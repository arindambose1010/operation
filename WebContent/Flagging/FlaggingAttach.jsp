<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flagging Attachments</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
</head>
<script>
	function openAttach(caseId,flagId,flagDocId)
		{
		
		 	var url = "/Operations/flaggingAction.do?actionFlag=getAttachments&caseId="+caseId+"&flagId="+flagId+"&flagDocId="+flagDocId;
		    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
		
			/* document.forms[0].action="/Operations/flaggingAction.do?actionFlag=getAttachments&caseId="+caseId+"&flagId="+flagId+"&flagDocId="+flagDocId;
			document.forms[0].method="post";
			document.forms[0].submit(); */
		}
</script>
<body>
	<form name="flaggingForm">
		<table height="100%" width="100%">
			<tr class="HeaderRow" >
				<td class="tbheader" width="100%">
					<b>Attachments</b>
				</td>				
			</tr>
		</table>
		<div align="center" style="margin:30px 0px 0px 0px">
				<b>Click on the name of the image to download</b>
			</div>
		<div id="attachments" style="margin:30px 0px 0px 350px">
			<table height="100%" width="40%">
					<logic:notPresent name="flaggingForm" property="attachVal">
						<logic:iterate name="flagAttachments" id="flagAttach" indexId="eid">
							<tr>
								<td class="labelheading1 tbcellCss" align="center" >
									<font color="black"><%= eid.intValue()+1 %>.</font><a href="#" onclick="javascript:openAttach('<bean:write name="flagAttach" property="caseId"/>',
																		   '<bean:write name="flagAttach" property="flagId"/>',
																		   '<bean:write name="flagAttach" property="flagDocId"/>')">
												<bean:write name="flagAttach" property="attachPath"/></a>
								</td>					
							</tr>						
						</logic:iterate>	
					</logic:notPresent>
			</table>	
		</div>
		
	</form>
</body>
</html>