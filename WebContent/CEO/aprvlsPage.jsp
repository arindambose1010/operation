<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.js"></script>
<title>Hospital Details Page</title>
<style>
.mainDiv {
	width: 92%;
    float: right;
	padding-right: 3%;
}

.but {
	border-radius: 16px;
}
body {
	font-size: 1.3em !important;
	font-family:Arial;
}
.btn{
padding:1px 10px;
}

</style>
<script>

function saveRmks(btnType,hospInfoId)
{	//alert("in function");
	var vMenu = $('#menu').val();
	//alert(vMenu);
	if(document.getElementById("ceoRmks").value==null||document.getElementById("ceoRmks").value==""){
		alert("please enter remarks");
		return false;
	}

	document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEDTLS&btnType="+btnType+"&hospInfoIdList="+hospInfoId+"&menu="+vMenu+"&fromPage=aprvlsPage";
	document.forms[0].submit();	
	document.forms[0].approveBtn.disabled = true;	
	document.forms[0].rejectBtn.disabled = true;	
	
}
function validateMaxlength(input,e) { 
	
	 var fieldValue=input.value; 
	 
	 var code;    
	  if (!e) 
	 var e = window.event;   
	   if (e.keyCode) 
	 code = e.keyCode;    
	  else if (e.which)
	  code = e.which;  	
	  
	 if(fieldValue.trim().length>4000) 		
	{ 	
		
		input.value=fieldValue.trim().substr(0,3999); 			
		var fr=partial(focusBox, document.getElementById("ceoRmks"));
		jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 3000 characters.',fr);
		if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40) 				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down 				
		{ 				
			e.returnValue=true; 				
		} 			
		else 		
		{ 				
		 e.returnValue=false; 			 
		} 		
	} 	
}
/*function showRsltMsg()
{
	var rsltMsg = "${msg}";
	var btnType = "${btn}";
	if(rsltMsg != "" && rsltMsg != null)
	{
		if(rsltMsg == "true"){
		alert("Hospital "+btnType+"ed Successfully");
		}
		else if(rsltMsg == "false"){
		alert("Failed to update");
		}		
		fn_pagination(1,10);
	}
		
	
}
*/

function fn_pagination(pageNo,rowsPerPage)
{	
	document.forms[0].action = "CeoWorkListAction.do?actionVal=GETCEOWORKLIST&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage;
	document.forms[0].submit();	
}
</script>
</head>
<body>
<html:form action="/CeoWorkListAction.do" method="POST"  styleId="ceoAprvlForm" enctype="multipart/form-data">
<c:set var="hospDtlsList"  value="${ceoWorkListForm.hospDtlsList}"/>
<c:set var="record"  value="${hospDtlsList[0]}"/>
<c:if test="${record.VALUE eq 'CD10000'}">
	<c:set var="showNormal" value="display:block"/>
	<c:set var="showDental" value="display:none"/>
</c:if>
<c:if test="${record.VALUE eq 'CD10001' || record.VALUE eq 'CD10002'}">
	<c:set var="showNormal" value="display:none"/>
	<c:set var="showDental" value="display:block"/>
</c:if>
<div class="container mainDiv">
<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="right">
<button class="btn but" type="button" value="Back"  onClick="javascript:fn_pagination(1,10);" >Back</button>
</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group text-center">
			<h4 style="color:#008544"><b>HOSPITAL DETAILS</b></h4>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-5 col-sm-12 col-xs-12 labelheading1 " style="border-top:1px solid #027397;border-left:1px solid #027397;border-bottom:1px solid #027397;border-bottom:1px solid #027397;">
			<label for="hospName" >Hospital Name </label><br>${record.HOSPNAME}
		</div>
		<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12  labelheading1  " style="border-top:1px solid #027397;border-left:1px solid #027397;border-bottom:1px solid #027397;border-right:1px solid #027397;">
			<label for="hospLoc" >Hospital Location :</label><br>${record.HOSPLOCATION}
		</div>	
		<div class="col-md-5 col-lg-2 col-xs-12 col-sm-12 labelheading1 " style="border-top:1px solid #027397;border-left:1px solid #027397;border-bottom:1px solid #027397;border-bottom:1px solid #027397;">
			<label for="hospBedStrngth" >Hospital Bed Strength :</label><br>${record.BEDSTRNGTH}
		</div>
		<div class="col-md-5 col-lg-2 col-xs-12 col-sm-12  labelheading1 " style="border-top:1px solid #027397;border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;${showNormal}">
			<label for="appType" >Hospital Type :</label><br>${record.HOSPTYPE}
		</div>	
		<div class="col-md-5 col-lg-2 col-xs-12 col-sm-12  labelheading1 " style="border-top:1px solid #027397;border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;${showDental}">
			<label for="appType" >Hospital Type :</label><br>${record.APPTYPE}
		</div>
	</div>
	<div class="row" style="display:none">
		<div class="col-lg-3 col-md-6 col-sm-12 col-xs-12 labelheading1 " style="border-left:1px solid #027397;border-bottom:1px solid #027397;">
			<label for="hospName" >Medco Count</label><br>3
		</div>
		<div class="col-lg-3 col-md-6 col-sm-12 col-xs-12  labelheading1  " style="border-left:1px solid #027397;border-bottom:1px solid #027397;">
			<label for="hospLoc" >Duty Doctors Count :</label><br>${ceoWorkListForm.dutyDctrCnt}
		</div>	
		<div class="col-md-6 col-lg-3 col-xs-12 col-sm-12 labelheading1 " style="border-left:1px solid #027397;border-bottom:1px solid #027397;">
			<label for="hospBedStrngth" >Paramedics Count :</label><br>${ceoWorkListForm.paramdcCnt}
		</div>
		<div class="col-md-6 col-lg-3 col-xs-12 col-sm-12  labelheading1 " style="border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;">
			<label for="splties" >Specilists Count:</label><br>${ceoWorkListForm.splstCnt}
		</div>			
	</div>
	<div class="row" >
		<div class="col-md-10 col-lg-12 col-xs-12 col-sm-12  labelheading1 " style="border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;border-top:1px solid #027397;">
			<label for="splties" >EDC Recommended Specilties :</label><br>${record.EOJEORECOMMENDED}
		</div>
	</div>
	<div class="row" >
		<div class="col-md-10 col-lg-12 col-xs-12 col-sm-12  labelheading1 " style="border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;border-top:1px solid #027397;">
			<label for="InspctnRmks" >Inspection Remarks :</label><br>${record.INSPRMKS}		
		</div>
	</div>
	<div class="row" >
		<div class="col-md-10 col-lg-12 col-xs-12 col-sm-12  labelheading1 " style="border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;border-top:1px solid #027397;">
			<label for="EdcRemarks" >EDC Remarks :</label><br>${record.EDCRMKS}		
		</div>
	</div>
	<div class="row" >
		<div class="col-md-10 col-lg-12 col-xs-12 col-sm-12  labelheading1 " style="border-left:1px solid #027397;border-right:1px solid #027397;border-bottom:1px solid #027397;border-top:1px solid #027397;">
			<label for="ScaRemarks" >SCA Remarks :</label><br>${record.SCARMKS}		
		</div>
	</div>
	<div class="row col-md-10 col-lg-12 col-xs-12 col-sm-12  labelheading1 ">
		<div>
			<label for="CeoRemarks">CEO Remarks :</label>
			<textarea class="form-control" rows="5" id="ceoRmks" onkeypress="validateMaxlength(this,event)">${record.CEORMKS}</textarea>			
		</div>
		
	</div>
	
	<div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div style="height:2%"></div>	
		<div class="col-lg-offset-4 col-md-offset-4 col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<button class="btn but" id="approveBtn" type="button" value="Approve"  onClick="saveRmks('Approve','${record.HOSPID}')" ><b style="color:white"><span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>&nbsp;&nbsp;
				<button class="btn but" id="rejectBtn" type="button" value="Reject"  onClick="saveRmks('Reject','${record.HOSPID}')" ><b style="color:white"><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>
		</div>
	</div>
</div>

<html:hidden property="menu" name="ceoWorkListForm" styleId="menu" />
</html:form>

</body>
</html>