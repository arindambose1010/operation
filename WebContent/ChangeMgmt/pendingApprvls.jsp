<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
<title>CEO Pending Approvals</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <script src="js/jquery-1.9.1.js"></script>
 <%@ include file="/common/include.jsp"%>
 <script src="bootstrap/js/bootstrap.min.js"></script>
 <script src="bootstrap/js/app.v2.js"></script>
  <script src="bootstrap/js/bootbox.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<style>
 body{font-size:1.2em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 
.text-active,.active .text{
	display:none !important;
}
.mainDiv{

width:96%;
float:right;
padding-right:5%;

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
  
</head>
<body onload="showMsg();">
<html:form action="/ceoWorklist.do" method="POST" enctype="multipart/form-data">
<div id="requestsTable"  style="margin:0 auto;">
<div class="container mainDiv">
 <div  class="col-lg-11 col-md-11 col-xs-12 col-sm-12" align="right">
<button class="btn but" type="button" value="Back"  onClick="javascript:fn_dashboard();" >Back</button>
</div>

<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<b>Change Requests Worklist</b>
</div>
 <logic:present name="ceoApprovalsForm" property="myCrRqstAprvlList">
 <bean:size id="size" name="ceoApprovalsForm" property="myCrRqstAprvlList"/>
    <logic:greaterThan value="0" name="size"> 
	 <div class="table-responsive">
		<div class="leftone visible-md visible-lg">
			<ul class="pagination">
				<li>&nbsp;<b>Showing Results</b>&nbsp;&nbsp; </li>
				<li><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> of 
				 <b><c:out value="${totalRecords}" /></b></li>
			</ul>
		</div>
		<div class="centerone">
								<ul class="pagination">
									<li>&nbsp;<b>Show Page</b>&nbsp;
									</li>
									<logic:notEmpty name="ceoApprovalsForm" property="prev">
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

										<logic:notEmpty name="ceoApprovalsForm" property="next">
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
	<logic:present name="ceoApprovalsForm" property="myCrRqstAprvlList">
		<bean:size id="size" name="ceoApprovalsForm" property="myCrRqstAprvlList" />
		<logic:equal name="size" value="0">
				<table align="center" width="100%">
					<tr align="center">
						<td align="center" class="tbcellBorder" width="100%">
						<b>No Records found</b>
						</td>
					</tr>
				</table>
		
		</logic:equal>		
	</logic:present>
<br>
     <logic:present name="ceoApprovalsForm" property="myCrRqstAprvlList">
 <bean:size id="size" name="ceoApprovalsForm" property="myCrRqstAprvlList"/>
    <logic:greaterThan value="0" name="size"> 
		<table class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table  b-t text-small" >
    	<tr>
			<th align="center"  class="visible-lg visible-md tbheader1" style="width:6%"><b>S.No</b></th>
    		<th align="center"  class="tbheader1 " style="width:4%"><b><input type="checkbox" name="chkBoxAll" id="chkBoxAll" /></b></th>
    		<th align="left"  class="tbheader1" style="width:10%">Change Request ID</th>
    		<th align="left"  class="visible-lg visible-md tbheader1" style="width:10%">CR Title</th>
    		<th align="left"  class="visible-lg visible-md visible-sm tbheader1" style="width:30%">CR Description</th>
    		<th align="left"  class="tbheader1" style="width:10%">CR Raised Department</th>
			<th align="left"  class="visible-lg visible-md tbheader1" style="width:10%">CR Raised By</th>
			<th align="left" class="visible-lg visible-md tbheader1" style="width:10%">Approve/Reject</th>
    	</tr>
			  <logic:iterate name="ceoApprovalsForm" property="myCrRqstAprvlList" id="rowId" indexId="cnt">
   <tr id="chkBoxTr${cnt}">
				<td align="center"  class="tbcellBorder visible-lg visible-md" style="width:6%">${cnt+1}</td>
               	<td align="center">
               	<input type="checkbox" class="chkBxCls" name="chkBox" id="chkBox${cnt}" onchange="changeClr('${cnt}')" value='<bean:write name="rowId" property="CR_ID" />'/></td>
              <td align="left" class="tbcellBorder" style="width:10%"><a href="javascript:viewChangeRqsttDtls('<bean:write name="rowId" property="CR_ID" />');('<bean:write name="rowId" property="CR_ID" />')" onMouseOver="this.style.color='red'" onMouseOut="this.style.color='#00F'" style="text-decoration: underline;font-size: 14px;" title="Click to CR Details">
            	<bean:write name="rowId" property="CR_ID" /></a></td>
                <td align="left" style="width:10%"  class="tbcellBorder visible-lg visible-md">
                <bean:write name="rowId" property="CR_TITLE" /></td>
                <td align="left" style="width:30%"  class="tbcellBorder visible-lg visible-md visible-sm ">
                <bean:write name="rowId" property="CR_DESC" /></td>
               <td align="left" style="width:10%"  class="tbcellBorder">
               <bean:write name="rowId" property="CR_RAISED_DEPT" /></td>
                <td align="left"  style="width:10%" class="tbcellBorder visible-lg visible-md">
                <bean:write name="rowId" property="EMP_FULL_NAME" /></td>
                		<td align="center" style="width:10%" class=" tbcellBorder visible-lg visible-md"  >
					<a href="#" id="approveS" onclick="javascript:fn_saveRow('<bean:write name="rowId" property="CR_ID" />','Approve')" data-toggle="class" title="Click to Approve" >
							<span class=" glyphicon glyphicon-ok text-success text-active"></span>
							<span class=" glyphicon glyphicon-ok text-danger text"></span>
							</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<a href="#" id="" onclick="javascript:fn_saveRow('<bean:write name="rowId" property="CR_ID" />','Reject')" data-toggle="class" title="Click to Reject" >
							<span class=" glyphicon glyphicon-remove text-success text-active"></span>
							<span class=" glyphicon glyphicon-remove text-danger text"></span>
							</a>&nbsp;
					</td>
            </tr>
			</logic:iterate>
          
       </table>
       </logic:greaterThan> 
	  </logic:present>
      
   <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="center">
			<button class="btn but" id="approveBtn" type="button" value="Approve"  onClick="fn_crRqstAction('Approve')" ><span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>
			<button class="btn but" id="rejectBtn" type="button" value="Reject"  onClick="fn_crRqstAction('Reject')" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>	
</div>  
      

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
	
<html:hidden property="showPage" name="ceoApprovalsForm" />
<html:hidden property="startPage" name="ceoApprovalsForm" value="${startPage}" />
<html:hidden property="endPage" name="ceoApprovalsForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="ceoApprovalsForm" />


</html:form>
<script  language="javascript" type="text/javascript">

function viewChangeRqsttDtls(crId)
{
	
	document.forms[0].action ='ceoWorklist.do?flag=CRView&crId='+crId;
	document.forms[0].submit();	
   /*  window.open(url,"CMS_View",'toolbar=no,resizable=yes,scrollbar=no,menubar=no,location=no,height=650,width=800,left=110,top=35'); */
}
function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}

function fn_removeLoadingImage()
{   
   document.getElementById('processImagetable').style.display="none";
}
 
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

	document.forms[0].action= "ceoWorklist.do?flag=pendingApprvls&pageNo="+pageNo+"&rowsPerPage="+rowsPerPage;
	document.forms[0].submit();	
}
function fn_crRqstAction(arg)
{
	var crIdList="";
$("input:checkbox[name=chkBox]:checked").each(function(){
		
			if($(this).prop('checked'))
			{
				crIdList = crIdList+$(this).val()+"~";
			
			}
			
		});

	document.forms[0].action = "ceoWorklist.do?flag=requestApprove&btnType="+arg+"&crIdList="+crIdList;
	document.getElementById("approveBtn").style.display="none";
	document.getElementById("rejectBtn").style.display="none";
 	document.forms[0].submit();	
 	
}
function showMsg()
{	var message="${msg}";
	if(message == "Approve")
	{
		alert("Approved successfully and Pending with MR/MS Madineni Sivakumar(246433) ");
		
	}
	else if(message == "Reject")
	{
		alert("Rejected successfully");
		
	}
	
	if(message != "" && message != null){

		document.forms[0].action  = "ceoWorklist.do?flag=pendingApprvls&pageNo=1&rowsPerPage=10";
		document.forms[0].submit();	
		
		
		
	}
		
}
function fn_saveRow(lStrCrId,button)
{
	var vMenu = $('#menu').val();
	var cnfmMsg=confirm("Do you want to "+button);
	if(cnfmMsg){
		document.forms[0].action = "ceoWorklist.do?flag=requestApprove&btnType="+button+"&crIdList="+lStrCrId+"&menu="+vMenu;
		document.forms[0].submit();	
		document.forms[0].approveBtn.disabled = true;	
		document.forms[0].rejectBtn.disabled = true;	
	}
}

$(document).ready(function () {
    
	$("#chkBoxAll").change(function () 
			{
		window.scrollTo(0,document.body.scrollHeight);
	 $(".chkBxCls").prop('checked', $(this).prop('checked'));
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
</body>
</html>
