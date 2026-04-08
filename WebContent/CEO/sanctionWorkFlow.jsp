<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="display" uri="http://displaytag.sf.net"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Sanction WorkFlow</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<script src="/<%=context%>/js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript"  src="/<%=context%>/js/jquery.mCustomScrollbar.js" ></script>
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


</script>
</head>
<body onload="parent.fn_removeLoadingImage();">
<html:form action="/adminSanction.do" method="POST" enctype="multipart/form-data">
<br>
<table align="center" width="98%" cellpadding="0" cellspacing="0"  style="padding-top:0px;margin:0px auto;">
						<tr class="HeaderRow">
                                  <td width="100%" class="tbcellCss" align="center"><b>ADMIN SANCTION REQUESTS</b></td>
					</tr>	
					</table>

<table id="sancWorkflow"  style="padding-top: 0px; margin: 0px auto;">
<tr>
<td >

<logic:present name="adminSanctionForm"  property="adminSancList">
<bean:size id="size" name="adminSanctionForm" property="adminSancList"/>
<logic:greaterThan name="size" value="0">

<%int i = 1;%> 
  <display:table style="padding-left:20px"   name="adminSanctionForm.adminSancList" id="rowId" requestURI="/adminSanction.do"  pagesize="10" export="false">
                <display:caption>
                <display:setProperty name="export.export name.include_header">true</display:setProperty>
				<display:setProperty name="basic.show.header">false</display:setProperty>
							<thead>
								<tr>
									<td class="tbheader1" style="width:10px"><b>S.No</b></td>
									<td class="tbheader1" style="width:100px"><b>Sanction ID</b></td>
									<td class="tbheader1" style="width:250px"><b>Status</b></td>
									<td class="tbheader1" style="width:100px"><b>Amount</b></td>
									<td class="tbheader1" style="width:160px"><b>Created By</b></td>
									<td class="tbheader1" style="width:160px"><b>Created Date</b></td>
									<td class="tbheader1" style="width:160px"><b>Last Updated By</b></td>
									<td class="tbheader1" style="width:160px"><b>Last Updated Date</b></td>
									</tr>
									</thead>
                </display:caption>
                <display:column  class="tbcellCss"  value="<%=i++ %>" title="S.No" style="text-align:center;"/>
                 <display:column  class="tbcellCss" title="Sanction ID"  style="text-align:center;"><a href="javascript:showStatus('${rowId.sanctionId}');" title="Click to View Admin Sanction Request"><c:out value="${rowId.sanctionId}"/></a>
               </display:column>
                <display:column class="tbcellCss"  title="Status"  property="status" style="text-align:center;"/>
                <display:column class="tbcellCss" title="Amount" property="sanctionAmount" style="text-align:center;"/>
                <display:column  class="tbcellCss"  title="Created By" property="crtUsr" style="text-align:center;"/>
               <display:column  class="tbcellCss"  title="Created Date" property="crtDt" style="text-align:center;"/>
               <display:column  class="tbcellCss"  title="Last Updated By" property="lstUpdUsr" style="text-align:center;"/>
               <display:column  class="tbcellCss"  title="Last Updated Date" property="lstUpdDt" style="text-align:center;"/>
              
               
            </display:table>

</logic:greaterThan>
 <logic:equal name="size" value="0">
 <table width="100%" >
 <tr>
 <td class="tbcell" width="100%" align="center"><b>No Records Found</b></td>
 </tr>

 </table>
 </logic:equal>
</logic:present>
</td>
</tr>
</table>
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
</html:form>
</body>
</html>