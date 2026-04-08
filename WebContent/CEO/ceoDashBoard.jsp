<!DOCTYPE html>
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en" class="no-touch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>DashBoard</title>
 <script src="js/jquery-1.9.1.js"></script>
 <%@ include file="/common/include.jsp"%>
<link href="/Operations/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/app.v2.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="http://flatfull.com/themes/first/css/font.css">
 <style>
 body{font-size:1.2em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 </style>
 
<script>
function fn_sancWorkFlow()
	{
		fn_loadImage();
		document.forms[0].action = "adminSanction.do?actionFlag=sanctionWorkflow&userId=${UserID}";
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
function fn_PendingAprvl()
{
	//fn_loadImage();
	document.forms[0].action = "ceoWorklist.do?flag=pendingApprvls";
	document.forms[0].submit();
}
function fn_ceoWorkList(){
	//fn_loadImage();
	document.forms[0].action="CeoWorkListAction.do?actionVal=GETCEOWORKLIST";
	document.forms[0].submit();
}
function fn_othrStateCeoWorkList(){
	//fn_loadImage();
	document.forms[0].action="CeoWorkListAction.do?actionVal=GETCEOWORKLIST&menu=othrSt";
	document.forms[0].submit();
}
function fn_ceoEDCWorkList(){	
	//fn_loadImage();
	document.forms[0].action="CeoWorkListAction.do?actionVal=GETEDCCEOWORKLIST";	
	document.forms[0].submit();	
}

function fn_operations()
	{


		document.forms[0].action="casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=preauth&ceoFlag=Y";
		document.forms[0].submit();
		}
</script>
</head>
<body bgcolor="#ffffff">
<html:form action="/adminSanction.do" method="POST" enctype="multipart/form-data">
<br>
<div id="requestsTable"  style="margin:0 auto;">	
<div class="container">



<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
						<h1><b>WORKLIST  DASHBOARD</b></h1></div>

<div class="table-responsive"> 

<section id="content">
		<section class="main padder">
			<div class="row" align="center">
				<div class="col-lg-12">
					<section class="toolbar clearfix m-t-large m-b">
						<a href="javascript:fn_sancWorkFlow()" title="Accounts"
							class="btn btn-warning btn-circle"><i class="fa glyphicon glyphicon-th-list"></i>Accounts
							<b class="badge bg-success">${sanctionsListSize}</b></a> 
						   <a href="javascript:fn_operations()"
							class="btn btn-info btn-circle" title="Operations"><i
							class="fa glyphicon glyphicon-gift"></i>Operations<b class="badge bg-info">${OperationsListSize}</b></a> 
							<a title="CMS"
							href="javascript:fn_PendingAprvl()"
							class="btn btn-primary btn-circle"><i class="fa glyphicon glyphicon-briefcase"></i>CMS
							<b class="badge bg-success">${crIdList}</b></a>
							<a title="EDC"
							href="javascript:fn_ceoEDCWorkList()"
							class="btn btn-inverse btn-circle"><i
							class="fa glyphicon glyphicon-pushpin"></i>EDC
							<b class="badge bg-danger">${EDCListSize}</b>
							</a> 
							<a href="javascript:fn_ceoWorkList()" title="Empanelment"
							class="btn btn-success btn-circle"><i class="fa glyphicon glyphicon-th"></i>Empanelment
							<b class="badge bg-success">${EmpnlListSize}</b></a> 
							<a href="javascript:fn_othrStateCeoWorkList()"
							class="btn btn-danger btn-circle"><i class="fa glyphicon glyphicon-th-large" title="Other State Empanelment"></i>Other State<br>Empanelments
							<b class="badge bg-info">${OthrStListSize}</b></a> 
					</section>
				</div>
				</div>
				
				
		</section>
	</section>
	<!-- .modal -->
				
			
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
</html:form>
</body>

<script src="bootstrap/js/app.v2.js"></script>
	<script src="bootstrap/js/fuelux.js"></script>
	<!-- fullcalendar -->
	<script
		src="bootstrap/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script
		src="bootstrap/js/jquery.ui.touch-punch.min.js"></script>


</html>