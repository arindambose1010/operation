<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.2">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<title>CSV Downloads</title>
<style>
	.noPadding
		{
			padding:0px !important;	
		}
	a:link
		{
			color:#FF0000 !important;
		}
</style>
</head>
<body onload="fn_removeLoadingImage();">

	<form>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tbheader">
				<b><i class="fa fa-briefcase"></i>&nbsp;CSV Downloads for User : ${userEmpName}</b>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
				<logic:notEmpty name="casesSearchFormClaims" property="lstCaseSearch">
					<logic:present name="casesSearchFormClaims" property="lstCaseSearch">
						<table class="" align="center" style="width:60%;margin-top:5%">
							<thead>
								<tr>
									<th class="tbheader1">
										<i class="fa fa-list"></i>&nbsp;S.No
									</th>
									<th class="tbheader1">
										<i class="fa fa-calendar"></i>&nbsp;Requested Date
									</th>
									<th class="tbheader1">
										<i class="fa fa-file"></i>&nbsp;CSV File
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${casesSearchFormClaims.lstCaseSearch}" var ="item" varStatus="status">
									<tr>
										<td class="tbcellBorder" align="center">
											${status.index+1}
										</td>
										<td class="tbcellBorder" align="center">
											<bean:write name="item" property="CREATEDON" />
										</td>
										<td class="tbcellBorder" align="center">
											<font color="red"><a href="javascript:fn_csvFile('<bean:write name="item" property="requestId" />','<bean:write name="item" property="testName" />','<bean:write name="item" property="userId" />')"> <i class="fa fa-download"></i>&nbsp;<bean:write name="item" property="testName" /></a></font>
										</td>
									</tr>
								</c:forEach>	
							</tbody>
						</table>
						
							
					</logic:present>
					<logic:notPresent name="casesSearchFormClaims" property="lstCaseSearch">
					</logic:notPresent>
				</logic:notEmpty>
				<logic:empty name="casesSearchFormClaims" property="lstCaseSearch" >
					<center>
						<div style="margin-top:20%">
							<i class="fa fa-exclamation-triangle"></i>&nbsp;No CSV Download requests are available.
						</div>	
					</center>
				</logic:empty>
			</div>
		</div>
		<input type="hidden" name="requestId" id="requestId" >
		<input type="hidden" name="fname" id="fname" >
		<input type="hidden" name="issueId" id="issueId" >
	</form>
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
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script>
function fn_csvFile(requestId,fileName,userId)
	{
		document.getElementById("requestId").value=requestId;
		document.getElementById("fname").value=fileName;
		document.getElementById("issueId").value=userId;
		
		document.forms[0].action="casesSearchAction.do?actionFlag=getCSVFile";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
function fn_removeLoadingImage()  
	{
		parent.fn_removeLoadingImage();
		document.getElementById('processImagetable').style.display="none";  
	}
</script>
</body>
</html>