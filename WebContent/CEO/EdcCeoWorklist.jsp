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
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-select.min.css">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/font-awesome.css" rel="stylesheet">
<script  src="bootstrap/js/modernizr.min.js"></script>
<script  src="bootstrap/js/css3-mediaqueries.js"></script> 
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/js/bootbox.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="bootstrap/js/fileinput.min.js"></script>
<link href="bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />

	<style>
 body{font-size:1.2em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 
.text-active,.active .text{
	display:none !important;
}
.mainDiv{

width:92%;
float:right;
padding-right:3%;

}
.active .text-active{
	display:inline-block !important;
}
.centerone {
padding-left:5%;
}
.rightone{
padding-right:3%;

}
 
</style>

<title>Insert title here</title>
<script>

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
	 var fr=partial(focusBox, document.getElementById("edcReqRemarks"));
	 jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 3000 characters.',fr);
	 if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40) 				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down 				
	 { 				e.returnValue=true; 				
	 } 			
	
	 else 		
	 { 				
		 e.returnValue=false; 			 
	 } 		
	 } 	
	 }
function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}


function fn_removeLoadingImage()
{   
   document.getElementById('processImagetable').style.display="none";
}
function showStatus(actionId,searchFlagEdc,hospId,hospName){
	
	
	document.forms[0].action = 'CeoWorkListAction.do?actionVal=EDCCEOREMARKS&serachType='+searchFlagEdc+'&actionId='+actionId+'&hospId='+hospId+'&hospName='+hospName;;
	document.forms[0].submit();	
	
	
} 
	

$(document).on("keydown", function (e) {
    if (e.which === 8 && !$(e.target).is("input, textarea")) {
        e.preventDefault();
    }
});

function showinSetsOf(num)
{   
	 
		document.forms[0].rowsPerPage.value=num; 
		document.forms[0].showPage.value='1'; 
		fn_pagination(1,num);
}

function showPagination(num)
{
		document.forms[0].showPage.value=num; 
		fn_pagination(num,document.forms[0].rowsPerPage.value);		
}
	
function fn_pagination(pageNo,rowsPerPage)
{
	
	document.forms[0].action = "CeoWorkListAction.do?actionVal=GETEDCCEOWORKLIST&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage;
	document.forms[0].submit();	
}

function saveCEORejDtls(btnType,actionId,hospId){
	
if(btnType=="Reject"){
		
		
		
			 
			 actionIdList =hospId+"@"+actionId +"~";
		
				document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEEDCCEODTLS&actionIdList="+actionIdList+"&btnType="+btnType;
				document.forms[0].submit();	
			
			 
		
	}
	
	
}
function saveCEODtls(btnType,actionId,hospId){
	
	
	
	if(btnType=="Approve"){
		
		
	
			 actionIdList =hospId+"@"+actionId +"~";
		
				
			document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEEDCCEODTLS&actionIdList="+actionIdList+"&btnType="+btnType;
			document.forms[0].submit();
			 
		
		
		
	}
	
	
	
	 
}

function saveDtls(arg)
{
	
var lengthCheckBox =$("input:checkbox:checked").length;
	
	
	if(lengthCheckBox==0){
		
		alert("Select any of the checkBoxes");
		document.forms[0].action = "CeoWorkListAction.do?actionVal=GETEDCCEOWORKLIST";
	    document.forms[0].submit();
		
		return false;
	}
	var actionIdList="";

	
		$("input:checkbox[name=chkBox]:checked").each(function(){
			if($(this).prop('checked'))
			{
				actionIdList = actionIdList+$(this).val()+"~";
				
				
			}
		});

	

	
	document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEEDCCEODTLS&actionIdList="+actionIdList+"&btnType="+arg;
	document.forms[0].submit();	
	
		
}




function showMsg()
{	

	var message="${msg}";
	if(message == "true")
	{
		bootbox.alert("Hospitals Approved successfully");
		 document.forms[0].action = "CeoWorkListAction.do?actionVal=GETEDCCEOWORKLIST&pageNo=1&rowsPerPage=10";
		  document.forms[0].submit();	

	}
	else if(message == "false")
	{
		bootbox.alert("Hospitals Rejected successfully");
		 document.forms[0].action = "CeoWorkListAction.do?actionVal=GETEDCCEOWORKLIST&pageNo=1&rowsPerPage=10";
		  document.forms[0].submit();	

	}
     

	
}
	
$(document).ready(function () {
	  
	var cnt=0;
	$("#chkBoxAll").change(function () {
        $(".chkBxCls").prop('checked', $(this).prop('checked'));
              
        for(var i=0;i<document.body.scrollHeight;i++){
        	if($(this).prop('checked')){
        	 document.getElementById('chkBoxTr'+i).style.backgroundColor='#CAFFDC';
			 }
			 else
			 {
			 document.getElementById('chkBoxTr'+i).style.backgroundColor='#FFFFFF';
			 }
        }
       
		window.scrollTo(0,document.body.scrollHeight);
    }); 
	
	
	
});
function fn_dashboard()
{
	fn_loadImage();		
	document.forms[0].action ="adminSanction.do?actionFlag=getDashBoard";
	document.forms[0].submit();
}

function changeClr(arg){

	if(document.getElementById('chkBox'+arg).checked){

		document.getElementById('chkBoxTr'+arg).style.backgroundColor='#CAFFDC';
	}
	else
	{
		document.getElementById('chkBoxTr'+arg).style.backgroundColor="#FFFFFF";
	}
		
}



</script>

</head>
<body onload="showMsg();">
<html:form action="/CeoWorkListAction.do" method="POST" enctype="multipart/form-data">
<div id="requestsTable"  style="margin:0 auto;">	
<div class="container mainDiv"><div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="right">
<button class="btn but" type="button" value="Back"  onClick="javascript:fn_dashboard();" >Back</button>
</div>
<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<b>EDC WORKLIST</b>
</div>

<logic:present name="ceoWorkListForm" property="edcCeoWorklist">
	<bean:size id="size" name="ceoWorkListForm" property="edcCeoWorklist"/>
<logic:greaterThan name="size" value="0">
			
    <div class="table-responsive">
		<div class="leftone visible-md visible-lg">
			<ul class="pagination">
				<li>&nbsp;<b>Showing Results</b>&nbsp;&nbsp; </li>
				<li><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
					of  <b><c:out value="${totalRecords}" /></b></li>
			</ul>
		</div>
							<div class="centerone">
								<ul class="pagination">
									<li>&nbsp;<b>Show Page</b>&nbsp;
									</li>
									<logic:notEmpty name="ceoWorkListForm" property="prev">
										<li><span class="glyphicon glyphicon-backward"
											onclick="javascript:showPagination('prev')"></span></li>
									</logic:notEmpty>
									<c:set var="startPage1" scope="session" value="${startPage}" />
									<c:set var="endPage1" scope="session" value="${endPage}" />
									<c:set var="pages1" scope="session" value="${pages}" />
									<c:if test="${pages>=startPage1}">

										<c:forEach var="page" begin="${startPage1}" end="${endPage1}">
											<c:if test="${liPageNo eq page}">
												<li class="active"><span><c:out value="${page}" /></span></li>
											</c:if>
											<c:if test="${liPageNo ne page}">
												<c:if test="${page le pages1}">
													<li><a
														href="javascript:showPagination('<c:out value="${page}" />')">
															<c:out value="${page}" />
													</a></li>
												</c:if>
											</c:if>

										</c:forEach>

										<logic:notEmpty name="ceoWorkListForm" property="next">
											<li><span class="glyphicon glyphicon-forward"
												onclick="javascript:showPagination('next') "></span></li>
										</logic:notEmpty>
									</c:if>

								</ul>
							</div>
							<div class="rightone visible-md visible-lg">
			<ul class="pagination">
				<li>&nbsp;<b>Show in sets of</b>&nbsp;</li>
					<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
					<c:forEach var="set" items="${ListNoSet}"  >
						<c:if test="${rowsPerPage eq set }" >
							<li class="active"><span><c:out value="${set}" /></span></li> 
						</c:if>
						<c:if test="${rowsPerPage ne set }" >
							<li><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
						</c:if>
					</c:forEach>
			</ul>
		</div>
	</div>
	</logic:greaterThan>
</logic:present>
<br>
	<logic:present name="ceoWorkListForm" property="edcCeoWorklist">
		<bean:size id="size" name="ceoWorkListForm" property="edcCeoWorklist" />
		<logic:equal name="size" value="0">
			<div  class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group " style="text-align:center">
<h5><b>NO RECORDS FOUND</b></h5>
</div>
		
		</logic:equal>		
	</logic:present>
<br>

<logic:present name="ceoWorkListForm" property="edcCeoWorklist">
		<bean:size id="size" name="ceoWorkListForm" property="edcCeoWorklist" />
		<logic:greaterThan name="size" value="0">
		
			<table class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table b-t text-small"  style="padding-left:1em; font-size:13px;" border="0" >
				<tr>
					<th class="tbheader1" style="text-align:center" class="visible-lg visible-md"><b>S No</b></th>
					<th class="tbheader1" width="20" style="text-align:center" ><b><input type="checkbox" name="chkBoxAll" id="chkBoxAll" onchange="checkBxClr();"/></b></th>
					<th class="tbheader1"  style="text-align:center" ><b>Action ID</b></th>
					<th class="tbheader1" class=" visible-lg visible-md " style="text-align:center" ><b>Hospital Name</b></th>
					<th class="tbheader1" class=" visible-lg visible-md " style="text-align:center" ><b>Type Of Action</b></th>
					<th class="tbheader1" class="visible-lg visible-md" style="text-align:center" ><b>Action Intiated Date</b></th>
					<th class="tbheader1" align="center" class="visible-lg visible-md" width="100">Take Action</th>
				</tr>
			<logic:iterate name="ceoWorkListForm" property="edcCeoWorklist" id="edcCeoWorklist" indexId="cnt">
				<tr id="chkBoxTr${cnt}">
					<td  align="center" class="tbcellBorder visible-lg visible-md">${cnt+1}</td>
					<td  align="center"  class="tbcellBorder"><input type="checkbox" name="chkBox" class="chkBxCls" id="chkBox${cnt}" onchange="changeClr('${cnt}');"value='<bean:write name="edcCeoWorklist" property="actionAndHospId" />'/></td>
					<td   class="tbcellBorder col-xs-2"><a href="javascript:showStatus('<bean:write name="edcCeoWorklist" property="actionId" />','N','<bean:write name="edcCeoWorklist" property="hospId" />','<bean:write name="edcCeoWorklist" property="hospName" />');" ><bean:write name="edcCeoWorklist" property="actionId" /></a></td>
					<td  class="tbcellBorder col-xs-4 visible-lg visible-md "><bean:write
						name="edcCeoWorklist" property="hospName" /></td>
					<td  class="tbcellBorder col-xs-2"><bean:write
						name="edcCeoWorklist" property="actionType" /></td>
					<td  class="tbcellBorder visible-lg visible-md"><bean:write
						name="edcCeoWorklist" property="crtDt" /></td> 
					<td class=" tbcellBorder visible-lg visible-md"  width="100">
					<a href="#" id="approveS" onclick="javascript:saveCEODtls('Approve','<bean:write name="edcCeoWorklist" property="actionId" />','<bean:write name="edcCeoWorklist" property="hospId" />')" data-toggle="class" title="Click to Approve" width="75">
							<span class=" glyphicon glyphicon-ok text-success text-active"></span>
							<span class=" glyphicon glyphicon-ok text-danger text"></span>
							</a>&nbsp;&nbsp;
					
					<a href="#" id="" onclick="javascript:saveCEORejDtls('Reject','<bean:write name="edcCeoWorklist" property="actionId" />','<bean:write name="edcCeoWorklist" property="hospId" />')" data-toggle="class" title="Click to Reject" >
							<span class=" glyphicon glyphicon-remove text-success text-active"></span>
							<span class=" glyphicon glyphicon-remove text-danger text"></span>
							</a>&nbsp;
					</td>  
				</tr>
			</logic:iterate>
			</table>
		
		</logic:greaterThan>
</logic:present>

<logic:present name="ceoWorkListForm" property="edcCeoWorklist">
		<bean:size id="size" name="ceoWorkListForm" property="edcCeoWorklist" />
		<logic:greaterThan name="size" value="0">
<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="center">
	<button class="but but" id="approveBtn" type="button" value="Approve"  onClick="saveDtls('Approve')" ><span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>
	<button class="but but" id="rejectBtn" type="button" value="Reject"  onClick="saveDtls('Reject')" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>

</div>
</logic:greaterThan>
</logic:present>
</div>
</div>
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
	
	
	
<html:hidden property="showPage" name="ceoWorkListForm" />
<html:hidden property="startPage" name="ceoWorkListForm" value="${startPage}" />
<html:hidden property="endPage" name="ceoWorkListForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="ceoWorkListForm" />	

</html:form>
</body>
</html>