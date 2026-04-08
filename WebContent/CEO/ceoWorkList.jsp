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
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<script src="bootstrap/js/app.v2.js"></script>
<script src="bootstrap/js/bootbox.js"></script>

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
window.history.forward(1);



 
 function openHospRecord(hospid)
 {
	var vMenu = $('#menu').val();
	//var url = 'CeoWorkListAction.do?actionVal=GETHOSPDTLS&hospInfoId='+hospid+'&menu='+vMenu;
	//var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
	//newwindow.focus();	
	document.forms[0].action = 'CeoWorkListAction.do?actionVal=GETHOSPDTLS&hospInfoId='+hospid+'&menu='+vMenu;
	document.forms[0].submit();	
 }
function openRecord(hospid)
{	
		var vMenu = $('#menu').val();			
	if(hospid.indexOf("HSIN") != -1 ){
	
		var url = 'CeoWorkListAction.do?actionVal=GETHOSPDTLS&hospInfoId='+hospid+'&menu='+vMenu;
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();	
		
		/*var url = 'http://empanelment.ehf.gov.in/Empanelments/onlineEmpnlApplication.do?actionVal=ONLINEAPPFORMSIGNIN&hospInfoId='+hospid+'&frmPage=mainlogin&workFlowType=Y';
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();	*/
		/*document.forms[0].action="/<%=context%>/onlineEmpnlApplication.do?actionVal=ONLINEAPPFORMSIGNIN&hospInfoId="+hospid+"&frmPage=mainlogin&workFlowType=Y";
		document.forms[0].method="post";
	    document.forms[0].submit();	*/	
	}
	else if(hospid.indexOf("HSIDC") != -1 ){
	
		var url = 'CeoWorkListAction.do?actionVal=GETHOSPDTLS&hospInfoId='+hospid+'&dentalAppType=C&menu='+vMenu;
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();
		
		/*var url = 'http://empanelment.ehf.gov.in/Empanelments/dentalApplication.do?actionVal=DENTALBASICINFO&hospInfoId='+hospid+'&dentalAppType=C&frmPage=mainlogin&workFlowType=Y';
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();*/	/*document.forms[0].action="/<%=context%>/dentalApplication.do?actionVal=DENTALBASICINFO&hospInfoId="+hospid+"&dentalAppType=C&frmPage=mainlogin&workFlowType=Y";
		document.forms[0].method="post";
	   document.forms[0].submit();*/
	}
	else if(hospid.indexOf("HSID") != -1 ){
	
		var url = 'CeoWorkListAction.do?actionVal=GETHOSPDTLS&hospInfoId='+hospid+'&dentalAppType=H&menu='+vMenu;
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();
		
		/*var url = 'http://empanelment.ehf.gov.in/Empanelments/dentalApplication.do?actionVal=DENTALBASICINFO&hospInfoId='+hospid+'&dentalAppType=H&frmPage=mainlogin&workFlowType=Y';
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();	*/
		/*document.forms[0].action="/<%=context%>/dentalApplication.do?actionVal=DENTALBASICINFO&hospInfoId="+hospid+"&dentalAppType=H&frmPage=mainlogin&workFlowType=Y";
		document.forms[0].method="post";
	   document.forms[0].submit();*/
	}
	else if(hospid.indexOf("HSIP") != -1 ){
	
		var url = 'CeoWorkListAction.do?actionVal=GETHOSPDTLS&hospInfoId='+hospid+'&dentalAppType=H&menu='+vMenu;
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();
	
		/*var url = 'http://empanelment.ehf.gov.in/Empanelments/psychiatryApplication.do?actionVal=PSYCHIATRYBASICINFO&hospInfoId='+hospid+'&workFlowType=Y';
		var newwindow = window.open(url,'Application','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
		newwindow.focus();	*/
		/*document.forms[0].action="/<%=context%>/psychiatryApplication.do?actionVal=PSYCHIATRYBASICINFO&hospInfoId="+hospid+"&workFlowType=Y";
		document.forms[0].method="post";
	   document.forms[0].submit();*/
	}
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
	
	document.forms[0].action = "CeoWorkListAction.do?actionVal=GETCEOWORKLIST&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage;
	document.forms[0].submit();	
}

function saveDtls(arg)
{
	var hospInfoIdList="";
	var vMenu = $('#menu').val();
	//var cnfmMsg=confirm("Do you want to "+arg);
	//if(cnfmMsg){
	//alert("hi");
	
	//alert($('input[type=checkbox]:checked'));
		var lengthCheckBox =$("input:checkbox:checked").length;	
	if(lengthCheckBox==0){
		
		alert("Please select any of the checkBoxes");
		return false;
	}
	//$('#chkBoxTr td :checkBox').change(function(){
		$("input:checkbox[name=chkBox]:checked").each(function(){
			if($(this).prop('checked'))
			{
				hospInfoIdList = hospInfoIdList+$(this).val()+"~";
				//alert(hospInfoIdList);
			}
		});
	//});
	
	document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEDTLS&btnType="+arg+"&hospInfoIdList="+hospInfoIdList+"&menu="+vMenu;
	document.forms[0].submit();	
	document.forms[0].approveBtn.disabled = true;	
	document.forms[0].rejectBtn.disabled = true;	
	
	//}
}
function fn_saveRow(hospInfoId,button)
{
	var vMenu = $('#menu').val();
	//var cnfmMsg=confirm("Do you want to "+button);
	//if(cnfmMsg){
		document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEDTLS&btnType="+button+"&hospInfoIdList="+hospInfoId+"&menu="+vMenu;
		document.forms[0].submit();	
		document.forms[0].approveBtn.disabled = true;	
		document.forms[0].rejectBtn.disabled = true;	
	//}
}
function showMsg()
{	var message="${msg}";
	var btnType = "${btn}";
	if(message == "true")
	{
		bootbox.alert("Hospitals "+btnType+"ed successfully");		
		
	}
	else if(message == "false")
	{
		bootbox.alert("Failed to update");		
		
	}
	if(message != "" && message != null)
		fn_pagination(1,10);
}
	/*function checkAll() {
		alert("hi");
		alert($("input[name='chkBoxAll']:checked"));
		if($("input[name='chkBoxAll']:checked"))
		{	alert("in if");
			$('input:checkbox[name=chkBox]').prop('checked',true);
		}
		else
		{alert("in else");
			$('input:checkbox[name=chkBox]').prop('checked',false);
		}
	}*/
$(document).ready(function () {
    
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
	//fn_loadImage();		
	document.forms[0].action ="adminSanction.do?actionFlag=getDashBoard";
	document.forms[0].submit();
}
function changeClr(arg){
	//alert(arg);
	//alert(document.getElementById('chkBox'+arg).checked);
	if(document.getElementById('chkBox'+arg).checked){
	//alert("in if");
		document.getElementById('chkBoxTr'+arg).style.backgroundColor='#CAFFDC';
	}
	else
	{//alert("in else");
		document.getElementById('chkBoxTr'+arg).style.backgroundColor="#FFFFFF";
	}
		
}

</script>
</script>
</head>
<body onload="showMsg();">
<html:form action="/CeoWorkListAction.do" method="POST" enctype="multipart/form-data">
<br>
<div id="requestsTable"  style="margin:0 auto;">	
				<div class="container mainDiv">
<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="right">
<button class="btn but" type="button" value="Back"  onClick="javascript:fn_dashboard();" >Back</button>
</div>
<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<b>EMPANELMENT WORKLIST</b>
</div>
<logic:present name="ceoWorkListForm" property="workList">
	<bean:size id="size" name="ceoWorkListForm" property="workList"/>
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
				<li>&nbsp;<b>Show Page</b>&nbsp;</li>
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
	<logic:present name="ceoWorkListForm" property="workList">
		<bean:size id="size" name="ceoWorkListForm" property="workList" />
		<logic:equal name="size" value="0">
				<table align="center" width="100%">
					<tr align="center">
						<td align="center" class="tbcellBorder" width="100%">
						<center><b>No Records found</b></center>
						</td>
					</tr>
				</table>
		
		</logic:equal>		
	</logic:present>
<br>

<logic:present name="ceoWorkListForm" property="workList">
		<bean:size id="size" name="ceoWorkListForm" property="workList" />
		<logic:greaterThan name="size" value="0">
		
			<table class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table b-t text-small"  style="padding-left:1em; font-size:13px;" border="0" >
				<tr>
					<th class="tbheader1" style="text-align:center" class="visible-lg visible-md"><b>S No</b></th>
					<th class="tbheader1" width="20" style="text-align:center" ><b><input type="checkbox" name="chkBoxAll" id="chkBoxAll" /></b></th>
					<th class="tbheader1" class="col-xs-4 visible-lg visible-md visible-sm" style="text-align:center" ><b>Hospital Name</b></th>
					<th class="tbheader1" class="col-xs-2" style="text-align:center" ><b>Hospital Type</b></th>
					<th class="tbheader1" class="col-xs-2" style="text-align:center" ><b>State</b></th>
					<th class="tbheader1" class="visible-lg visible-md" style="text-align:center" ><b>District</b></th>
					<th class="tbheader1" class="visible-lg visible-md" style="text-align:center" ><b>Location</b></th>
					<th class="tbheader1" align="center" class="visible-lg visible-md" width="100"></th>
				</tr>
			<logic:iterate name="ceoWorkListForm" property="workList" id="empanelHospVO" indexId="cnt">
				<tr id="chkBoxTr${cnt}">
					<td  align="center" class="tbcellBorder visible-lg visible-md">${cnt+1}</td>
					<td  align="center"  class="tbcellBorder"><input type="checkbox" name="chkBox" class="chkBxCls" id="chkBox${cnt}" onchange="changeClr('${cnt}');" value='<bean:write name="empanelHospVO" property="HOSPID" />'/></td>
					<td   class="tbcellBorder col-xs-4"><a href="javascript:openHospRecord('<bean:write name="empanelHospVO" property="HOSPID" />')" onMouseOver="this.style.color='red'" onMouseOut="this.style.color='#00F'" style="text-decoration: underline;font-size: 14px;" title="Click to View Hospital Details"><bean:write name="empanelHospVO" property="HOSPNAME" /></a></td>
						<td class="tbcellBorder col-xs-2 visible-lg visible-md visible-sm"><bean:write
						name="empanelHospVO" property="VALUE" /></td>
					<td  class="tbcellBorder col-xs-2"><bean:write
						name="empanelHospVO" property="STATE" /></td>
					<td class="tbcellBorder visible-lg visible-md"><bean:write
						name="empanelHospVO" property="DISTRICT" /></td>
					<td class="tbcellBorder visible-lg visible-md"><bean:write
						name="empanelHospVO" property="HOSPLOCATION" /></td>  
					<td align="center" class=" tbcellBorder visible-lg visible-md"  width="100">
					<a href="#" id="approveS" onclick="javascript:fn_saveRow('<bean:write name="empanelHospVO" property="HOSPID" />','Approve')" data-toggle="class" title="Click to Approve" width="75">
							<span class=" glyphicon glyphicon-ok text-success text-active"></span>
							<span class=" glyphicon glyphicon-ok text-danger text"></span>
							</a>&nbsp;&nbsp;
					
					<a href="#" id="" onclick="javascript:fn_saveRow('<bean:write name="empanelHospVO" property="HOSPID" />','Reject')" data-toggle="class" title="Click to Reject" >
							<span class=" glyphicon glyphicon-remove text-success text-active"></span>
							<span class=" glyphicon glyphicon-remove text-danger text"></span>
							</a>&nbsp;
					</td>
				</tr>
			</logic:iterate>
			</table>
<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="center">
	<button class="but but" id="approveBtn" type="button" value="Approve"  onClick="saveDtls('Approve')" ><span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>&nbsp;&nbsp;
	<button class="but but" id="rejectBtn" type="button" value="Reject"  onClick="saveDtls('Reject')" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>	
</div>

						
		</logic:greaterThan>
</logic:present>
</div>
</div>

	
<html:hidden property="showPage" name="ceoWorkListForm" />
<html:hidden property="startPage" name="ceoWorkListForm" value="${startPage}" />
<html:hidden property="endPage" name="ceoWorkListForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="ceoWorkListForm" />	
<html:hidden property="menu" name="ceoWorkListForm" styleId="menu" />
</html:form>
</body>
</html>