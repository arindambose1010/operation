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
<fmt:bundle basename="Registration">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Preauth Approved Cases Search</title>
<%@ include file="/common/include.jsp"%>

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style type="text/css">.centerone{padding-left:6%;}</style>





    <link href="CEO/ceoFilesNew/bootstrap.min.css" rel="stylesheet">
    <link href="CEO/ceoFilesNew/bootstrap-reset.css" rel="stylesheet">
    <link href="CEO/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<script src="CEO/ceoFilesNew/jquery.js"></script>
	<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- Custom styles for this template -->
  
    <link href="CEO/ceoFilesNew/style-responsive.css" rel="stylesheet">
<link href="CEO/ceoFilesNew/style.css" rel="stylesheet">
	<link href="CEO/ceoFilesNew/select2.min.css" rel="stylesheet">
  <script src="bootstrap/js/bootbox.js"></script>


<style>body{font-size:12px !important}
.mainDiv{
 position: relative;
width:98;
align:center;





}
.modal-backdrop {
  z-index: -1;
}
.but{border-radius:16px;}
legend{
font-size:15px;
}

.labelheading1{
font-weight: 700;
}
.rightone{
padding-right:3%;

}
.centerProgress{
width:50%;
align:center;
padding-top:20%;
padding-left:40%;
background-color:none;
border-radius:10%;
}


.tbheader  {
background:url('./images/ceoTbBackground.png')
repeat-x; 
color: #fff;
text-align: left;
}



body {
  font-family: sans-serif;
    color: #767676;
}
td {
  font-size: 13px;
}

#apprvdPckAmt,#comorBidAppvAmt {
 background:#F7BCAC;
 font-weight:700;
}
legend.legendStyle {
    border: solid 1px #DDD !important;
    padding: 0 10px 10px 10px;
    border-bottom: none;
    background-color: #2a7c9e;
    color: #fff;
    padding-top: 0.2em;
    padding-bottom: 0.2em;
}

legend.legendStyle {
    width: auto !important;
    border: none;
    font-size: 14px;
    background-color: #2a7c9e;
    color: #fff;
}
</style>
<script type="text/javascript">
	function fn_submit(caseId)
	{
		var remarks=document.getElementById("genRemarks").value;
		if(remarks==null ||  remarks == '')
			{
			alert("please enter remarks to proceed");
			}
		
		else
			{
		 var url1="/Operations/casesSearchAction.do?actionFlag=submitDME&caseId="+caseId+"&remarks="+remarks;
		   document.forms[0].action=url1;   
		   //document.forms[0].target="bottomFrame";
		   document.forms[0].submit();
			}
	}
	function fn_update(caseId)
	{
		var remarks=document.getElementById("genRemarks1").value;
		if(remarks==null || remarks == '')
			{
			alert("Please enter remarks to proceed");
			}
		
		else
			{
		 var url1="/Operations/casesSearchAction.do?actionFlag=updateDME&caseId="+caseId+"&remarks="+remarks;
		   document.forms[0].action=url1;   
		   //document.forms[0].target="bottomFrame";
		   document.forms[0].submit();
			}
	}
	function fn_success()
	{
		var msg="${msg}";
		var dme="${dme}";
		var mainPage = "${mainPage}";
		var eoFlag="${eoFlag}";
		var casestatus = "${casestatus}";
		//alert(dme);alert(eoFlag);alert(casestatus);
		
		if(msg == "success")
			{
			document.getElementById("myDiv").style.opacity = "0.2";
			var alertmsg="Query has been raised and send to EO Operations ";
			bootbox.alert({
			     message: alertmsg,
			     callback: function () {
			    	 parent.fn_preauthApprovedCases();
			     }
			 });
			}
		if(msg == "failure")
			{
			alert("Failure");
			}
	}
	function fn_back()
	{
		if("${mainPage}" == 'Y')
			{
			parent.fn_preauthApprovedCases();
			}
		if("${dme}" == 'Y')
			{
			parent.fn_DMEFlagCases();
			}
		if("${eoFlag}" == 'Y')
			{
			parent.fn_EOUpdatedCases();
			}
	}
	
</script>
<body onload="fn_success()">
<div class="container-fluid" id="myDiv">
	<html:form    action="/casesSearchAction.do" enctype="multipart/form-data" method="post">
		<div class="tbheader1"> 
			 <b>Case Details</b> 
			 <span style="float:right"><button class="btn btn-warning"  id="backBtn" onclick="javascript:fn_back();" type="button" style="padding: 0px 3px;"> </span><i class="glyphicon glyphicon-share-alt" style="transform: scaleX(-1);"></i></button></span>
			 </div>
		<div>	
			
				<table>
			<tr>
			
			<td  class="labelheading1 tbcellCss" style="width: 50%;"><b>Case Number : </b></td>
			
			<td class="tbcellBorder" style="width: 50%;"><b><bean:write name="casesSearchFormClaims"  property="CASEID" /></b></td></tr>
			</table>

			<table border="0" align="center" width="100%" style="border-collapse: collapse;">
			<tr>
			<div class="tbheader1"> 
			 <b><fmt:message key="EHF.FrameSet.PatientDetails"/></b>
			 </div></tr>
			<tr>
			<td width="35%" valign="top">
			
			
			 <div style="overflow:hidden" id='commonDtlsField'>
			<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
			<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="cardIssueDt"/></b></td></tr> --%> 
			<tr><td class="labelheading1 tbcellCss" width="40%"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="PNAME" /></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="gender"/></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="dateOfBirth"/></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="years"/>Y
			 		<bean:write name="casesSearchFormClaims" property="months"/>M
					<bean:write name="casesSearchFormClaims" property="days"/>D</b></td></tr>
			
			<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="caste"/></b></td></tr> --%>
			 <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="slab"/></b></td></tr> 
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="occupation"/></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="contactno"/></b></td></tr>
			</table>
			</div>
			</td>
			<td width="45%" valign="top" >
			
			 
			  <div style="overflow:hidden" id='cardAddressField'>
			<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
			<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="hno"/></b></td> </tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="street"/></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="state"/></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="district" /></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="mandal" /></b></td></tr>
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="village" /></b></td></tr> 
			<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="pin" /></b></td></tr>
			</table>
			
			</div>
			
			</td></tr></table>
			
			<table border="0" width="100%" class="table-responsive">
				<tr >
				<div class="tbheader1">
				<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Registration Details </b>
				</div>
				
				
				</tr>
			</table>
			<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center" class="tableClass1 table-responsive">
				<!-- <tr ><td  colspan="6" align="center"  class="tbheader" ><b> Preauth Details </b></td></tr>
				<tr><td  colspan="6" align="left"  class="tbheader" ><b> NWH Details </b></td></tr> -->
				
				<tr><td  class="labelheading1 tbcellCss"><b>Hospital Name</b></td><td  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="HOSPNAME" /></td>
				<td class="labelheading1 tbcellCss"><b>Card Number</b></td><td colspan="3"  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="CARDNO" /></td></tr>
				<tr>
				<td  class="labelheading1 tbcellCss"><b>Preauth Approved Amount</b></td><td  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="PREAUTHAMT" /></td>
				<td class="labelheading1 tbcellCss"><b>Preauth Approved Date</b></td><td colspan="3"  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="PREAUTHDATE" /></td>
				</tr>
				<tr>
				<td  class="labelheading1 tbcellCss"><b>Admission Date</b></td><td  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="ADMNDATE" /></td>
				<td  class="labelheading1 tbcellCss"><b>Surgery Date</b></td><td  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="SURGDATE" /></td>
				
				</tr>
				<tr><td  class="labelheading1 tbcellCss"><b>Discharge Date</b></td><td  class="tbcellBorder"><bean:write name="casesSearchFormClaims"  property="DISCDATE" /></td></tr>
			</table>
			<table border="0" width="100%" class="table-responsive">
				<tr >
					<div class="tbheader1"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Treatment Protocol </b>
					</div>
					<logic:present name="casesSearchFormClaims" property="preauthCaseSearch">
		 	<bean:size id="PreauthCaseSearchSize" name="casesSearchFormClaims"  property="preauthCaseSearch"/>
				<logic:greaterThan value="0" name="PreauthCaseSearchSize"> 
					<table  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
						<tr>
							<th class="tbheader1" width="12%" valign="top" style="background-color: #447bc6;">Category Name</th>
							<th class="tbheader1" width="10%" valign="top" style="background-color: #447bc6;">Icd Category Name</th>
							<th class="tbheader1" width="15%" valign="top" style="background-color: #447bc6;">Procedure Name</th>
							<th class="tbheader1" width="10%" valign="top" style="background-color: #447bc6;">Units</th>
							
							<th class="tbheader1" width="10%" valign="top" style="background-color: #447bc6;">Treating Doctor name</th>

						</tr>
						 <logic:iterate id="preauthCaseSearch" name="casesSearchFormClaims"  property="preauthCaseSearch" >
						 <tr>
						 	
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="asriCatName" /></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="catName" /></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="procName" /></td>
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">
							<logic:notEqual name="preauthCaseSearch" property="opProcUnits" value="-1">
								<bean:write name="preauthCaseSearch" property="opProcUnits" />
							</logic:notEqual>
							<logic:equal name="preauthCaseSearch" property="opProcUnits" value="-1">
									-NA-
							</logic:equal>
							</td>
							
							<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="preauthCaseSearch" property="docName" /></td>
						</tr>
						 </logic:iterate>
					</table>
				 </logic:greaterThan> 
				</logic:present>
				
				</tr>
			</table><br/>
			
			
			<c:if test="${( dme=='Y')}">
			<table border="0" width="100%" class="table-responsive">
			<tr >
					<div class="tbheader1"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Remarks </b>
					</div>
					</tr>
				<tr >
				<td  class="labelheading1 tbcellCss" style="width: 25%;"><b>DME Remarks </b></td>
				<td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="REMARKS" /></b></td>
				</tr>
			</c:if><br/>
			
			<c:if test="${( eoFlag=='Y')}">
			<table border="0" width="100%" class="table-responsive">
			<tr >
					<div class="tbheader1"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Remarks </b>
					</div>
					</tr>
				
				<tr >
				<td  class="labelheading1 tbcellCss" style="width: 25%;"><b>DME Remarks </b></td>
				<td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="REMARKS" /></b></td>
				</tr>
				<c:if test="${( casestatus=='N' && eoFlag=='Y')}">
				<tr >
				<td  class="labelheading1 tbcellCss" style="width: 25%;"><b>EO Remarks </b></td>
				<td class="tbcellBorder"><b>&nbsp;<bean:write name="casesSearchFormClaims" property="GENREMARKS" /></b></td>
				</tr></c:if>
				<tr></tr>
			</table>
			</c:if>
			
			
			<table style="width:100%">
			<c:if test="${(dme !='Y' && eoFlag!='Y')}">
			
			<tr id="remarksTr">
				<td  colspan="2" class="labelheading1 tbcellCss "> &nbsp; Remarks  :<font color="red">*</font> </td></tr>
				<tr id="remarksTr1"><td  colspan="2" class="tbcellBorder">
				 &nbsp;<html:textarea name="casesSearchFormClaims" property="genRemarks" styleId="genRemarks" style="width:98%;"  title="Please enter remarks"></html:textarea>
				</td>
				</tr>
			</c:if>
			<c:if test="${(dme =='Y')}">
				<tr >
				<td  colspan="2" class="labelheading1 tbcellCss "> &nbsp;EO Remarks  :<font color="red">*</font> </td></tr>
				<tr ><td  colspan="2" class="tbcellBorder">
				 &nbsp;<html:textarea name="casesSearchFormClaims" property="genRemarks1" styleId="genRemarks1" style="width:98%;"  title="Please enter remarks"></html:textarea>
				</td>
				</tr></c:if>
			</table>
			</table>
			
			<c:if test="${(dme !='Y' && eoFlag!='Y')}">
			</table></div><div>
			<table style="width:100%">
					<tr>
						<td style="text-align:center"><button class="but" type="button"  value="submit"  title="submit" onclick="fn_submit('<bean:write name="casesSearchFormClaims"  property="CASEID" />');">Raise Query to Aarogyasri Healthcare Trust</button></td>
					</tr></table>

			
			</table></c:if></div>
			
			<c:if test="${(dme =='Y' )}"><br/>
			</table></div><div>
			<table style="width:100%">
					<tr>
						<td style="text-align:center"><button class="but" type="button"  value="submit"  title="submit" onclick="fn_update('<bean:write name="casesSearchFormClaims"  property="CASEID" />');">Update</button></td>
					</tr></table>

			
			</table></c:if></div>
			
			
			
	</html:form>
	</div>
</body>
</fmt:bundle>
</html>