<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">

<title>Cochlear FollowUp Claim Cases</title>
<style>
	body{
	font-size:13px !important;
	font-family:"Helvetica Neue", Helvetica, Arial, sans-serif !important;
	}
	.noPadding{
	padding:0px !important;
	}
	.tbcellCss{
	margin:0px !important;
	}
	.tbcellBorder{
	margin: 0px !important;
	}
	.tableCss1 th{
	font-size:14px !important;
	} 
	.tableCss1 td{
	font-size:12px !important;
	}
	.tablePaddingHead{
	padding-top:10px !important;
	padding-bottom:10px !important;
	}
	.tablePaddingBody{
	padding-top:6px !important;
	padding-bottom:6px !important;
	}
	.labelheading1{
	padding-top:5px !important;
	padding-bottom:5px !important;
	}
	.inStyle{
	padding-left:6px !important;
	padding-top:0px !important;
	}
	.form-group{
	margin:0px !important;
	}
	.bootbox-alert .modal-content .modal-body .bootbox-close-button{
	margin-top: -5px !important;
	}
	.datepicker table{
	font-size:14px !important;
	}
	.centerProgress{
	width:60%;
	align:center;
	padding-top:20%;
	padding-left:38%;
	background-color:none;
	border-radius:10%;
	}
	.topArrow{
	position:fixed;
	top:440px;
	right:20px;
	display:none;
	width:40px;
	height:30px;
	color:blue;
	cursor:pointer;
	background-color:rgba(0, 0, 255, 0.55);
	color:white;
	clear:both;
	border-radius:10px;
	}
	.scrollIcon{
	padding-top:2px !important;
	padding-left:9px !important;
	padding-right:5px !important;
	}
</style>
</head>
<body onload="javascript:checkMsg();">
	<form id="followUpFormSearch">
		<html:hidden name="followUpForm" property="csvFollowUpStatus" styleId="csvFollowUpStatus" />
		<html:hidden name="followUpForm" property="csvCaseNo" styleId="csvCaseNo" />
		<html:hidden name="followUpForm" property="csvFollowUpId" styleId="csvFollowUpId" />
		<html:hidden name="followUpForm" property="csvPatName" styleId="csvPatName" />
		<html:hidden name="followUpForm" property="csvFromDate" styleId="csvFromDate" />
		<html:hidden name="followUpForm" property="csvToDate" styleId="csvToDate" />
		<html:hidden name="followUpForm" property="csvCardNo" styleId="csvCardNo" />
		<html:hidden name="followUpForm" property="csvSchemeType" styleId="csvSchemeType" />
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="searchDiv">
			<c:choose>
				<c:when test="${casesForApproval eq 'Y'}">
					<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" >
						<b>Cochlear FollowUp Cases For Approval </b>
					</div>
				</c:when>
				<c:otherwise>
					<div class="tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" >
						<b>Cochlear FollowUp Cases Search</b>
					</div>
				</c:otherwise>
			</c:choose>	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" style="margin-top:5px !important;">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="fa fa-sitemap"></i>&nbsp; <b>FollowUp Status :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group" style="padding-top:2px !important;">
							<html:select name="followUpForm" property="searchFollowUpStatus" styleId="searchFollowUpStatus"  title="Select FollowUp Status" style="width:95%">
								<html:option value="">Select</html:option>
								<html:options collection="flpStatusLst" labelProperty="value" property="id"/> 
							</html:select>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="fa fa-database"></i>&nbsp;<b>Case Number :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<input type="text" name="searchCaseNo" class="form-control" value="${followUpForm.searchCaseNo}" id="searchCaseNo" title="Enter case Number" style="width:95%">
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="fa fa-exclamation-triangle"></i>&nbsp;<b>FollowUp ID :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<input type="text" name="searchFollowUpId" class="form-control" value="${followUpForm.searchFollowUpId}"  id="searchFollowUpId" style="width:95%" title="Enter FollowUp ID">
						</div>
					</div>
				</div>	
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" style="margin-top:5px !important;">	
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;&nbsp;<i class="fa fa-male"></i>&nbsp; <b>Patient Name :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<input type="text" name="searchPatName" class="form-control" value="${followUpForm.searchPatName}" id="searchPatName"  style="width:95%" title="Enter Patient Name">
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="glyphicon glyphicon-calendar"></i> <b>FollowUp From Date :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<input type="text" name="searchFromDate" class="form-control" value="${followUpForm.searchFromDate}"  id="searchFromDate" style="width:95%" title="Select/Enter From Date">
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="glyphicon glyphicon-calendar"></i> <b>FollowUp To Date :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<input type="text" name="searchToDate" class="form-control" value="${followUpForm.searchToDate}"   id="searchToDate" style="width:95%" title="Select/Enter To Date">
						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" style="margin-top:5px !important;">	
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="glyphicon glyphicon-list-alt"></i>&nbsp; <b>Card No :</b>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<input type="text" name="searchCardNo" class="form-control" value="${followUpForm.searchCardNo}" id="searchCardNo" style="width:95%" title="Enter Card Number">
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding">
						<c:if test="${lStrUserState eq 'CD203'}">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding labelheading1 tbcellCss">
							&nbsp;<i class="fa fa-flag"></i>&nbsp;<b>Scheme :</b>
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 noPadding inStyle form-group">
							<html:select name="followUpForm" property="searchSchemeType" styleId="searchSchemeType" title="Select Scheme" style="width:95%">
								<html:option value="CD203">Both</html:option>
								<html:option value="CD201">Andhra Pradesh</html:option>
								<html:option value="CD202">Telangana</html:option>
							</html:select>
						</div>
						</c:if>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" align="center" style="margin-top:5px !important;">
					<input type="button" name="searchResults" id="searchResults" onclick="javascript:searchCases();" class="btn btn-primary" value="Search" title="Click to Search Results"/>
					<input type="button" name="resetFields" id="resetFields" onclick="javascript:resetFieldsNew();" class="btn btn-danger" value="Reset"  title="Click to Reset all Fields "/>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding"  id ="warningAlert" align="center">
					<div class="col-lg-3 col-md-3 hidden-sm hidden-xs">&nbsp;</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<!-- <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9"> -->
							<div class="alert alert-danger fade in noPadding" style="margin-bottom:0px !important;margin-top:5px !important;padding-top:5px !important ;padding-bottom:5px !important;">
								Enter data into <Strong>Any of Above Fields</Strong> to Search.
								<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('warningAlert')"></i> 
							</div>
						<!-- </div> -->
							
					</div>
					<div class="col-lg-3 col-md-3 hidden-sm hidden-xs">
						<logic:notEmpty name="followUpForm" property="followUpCochClaimList">
							<logic:present name="followUpForm" property="followUpCochClaimList">
								<div id="csvDiv" style="clear:both;float:right;margin-right:40px;">
									<button id="csvImg" type="button" class="btn btn-danger" data-toggle="tooltip" title="CSV Download" onclick="javascript:exportToCSV()" style="margin-top:2px;padding-top:2px;padding-bottom:2px;padding-left:5px ;padding-right:5px"><i class="fa fa-download"></i>&nbsp;CSV</button>
									<!-- <img id="csvImg" src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCSV()"/> -->
								</div>
							</logic:present>
						</logic:notEmpty>
					</div>	
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="resultsDiv">
			<logic:notEmpty name="followUpForm" property="followUpCochClaimList">
				<logic:notPresent name="followUpForm" property="followUpCochClaimList">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center" style="top:10%">No Records Found.</div>
				</logic:notPresent>
				<logic:present name="followUpForm" property="followUpCochClaimList">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 noPadding" style="padding-top:25px !important;">
						Showing Results :${startIndex+1} - ${endResults} of ${totalRecords}
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5 col-xs-6 noPadding" align="center" id="pageNoDisplay" style="padding-top:5px !important;">
						<ul class="pagination">
							<li>
								Pages :&nbsp;&nbsp;
							</li>	
						<% int selectedPage=Integer.parseInt(request.getAttribute("pageId").toString());  
								  int totalPages=Integer.parseInt(request.getAttribute("totalPages").toString());
								  int totalRecords=Integer.parseInt(request.getAttribute("totalRecords").toString());
								  int endIndex=Integer.parseInt(request.getAttribute("endIndex").toString());
								  
								  int a=0,minVal=0,maxVal=0;
								  a=selectedPage/10;
								  minVal=a*10;
								  if(selectedPage%10==0)
									  minVal=minVal-10;
								  maxVal=minVal+10;
								  if(maxVal>=totalPages)
									  maxVal=totalPages; 
								  minVal=minVal+1;
								  if(minVal > 10)
								  	{
									  %>
										<li><a href="#"><span class="glyphicon glyphicon-backward" 
											onclick="javascript:viewPreviousPages(<%=minVal%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer;"></span></a></li>
									  <%  
								  	}
								  for(int i=minVal;i<=maxVal ;i++)
								  	{
									  	if(i==selectedPage)
										  	{
												%>
												<li class="active"><span><%=i%></span></li> 
												<%
											}
									  	else
											{
												%>
												<li><a href="javascript:fn_pagination(<%=i%>,<%=endIndex%>)"><%=i%></a></li>
												<%
											}
									  		
								  	}
								  if(maxVal<totalPages)
								  	{
									  	%>
										<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=maxVal+1%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer;"></span></li>
										<%
								  	}
							   %>
						</ul>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">		
								<ul class="pagination">
									<li class="lispacing">Show results in sets of </li><c:if test="${endIndex ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
										<c:if test="${endIndex eq 10}"><li class="active"><span>10</span></li></c:if>	
										<c:if test="${endIndex ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
										<c:if test="${endIndex eq 20}"><li class="active"><span>20</span></li></c:if>
										<c:if test="${endIndex ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
										<c:if test="${endIndex eq 50}"><li class="active"><span>50</span></li></c:if>
										<c:if test="${endIndex ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
										<c:if test="${endIndex eq 100}"><li class="active"><span>100</span></li></c:if>
								</ul> 
					</div>
					<table class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tableCss1 noPadding">
						<thead>
							<tr>
								<th class="tbheader1 tablePaddingHead ">
									Cochlear FollowUp ID
								</th>
								<th class="tbheader1 tablePaddingHead hidden-sm hidden-xs">
									Case Number
								</th>
								<th class="tbheader1 tablePaddingHead hidden-sm hidden-xs">
									Card Number
								</th>
								<th class="tbheader1 tablePaddingHead hidden-sm hidden-xs">
									Patient Name
								</th>
								<th class="tbheader1 tablePaddingHead">
									FollowUp Status
								</th>
								<th class="tbheader1 tablePaddingHead hidden-sm hidden-xs">
									FollowUp Date
								</th>
								<th class="tbheader1 tablePaddingHead">
									Claim Amount
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var ="result" items="${followUpForm.followUpCochClaimList}">
								<tr>
									<td class="tbcellBorder tablePaddingBody ">
										<a href="javascript:openCase('<bean:write name="result" property="followUpId" />','<bean:write name="result" property="caseId" />','<bean:write name="result" property="followUpStatus" />');" title="Click to Open Case Details"><font color="#EC0009"><b><i class="glyphicon glyphicon-new-window"></i><bean:write name="result" property="followUpId" /></b></font></a>
									</td>
									<td class="tbcellBorder tablePaddingBody  hidden-sm hidden-xs">
										<bean:write name="result" property="caseNo" />
									</td>
									<td class="tbcellBorder tablePaddingBody  hidden-sm hidden-xs">
										<bean:write name="result" property="cardNo" />
									</td>
									<td class="tbcellBorder tablePaddingBody  hidden-sm hidden-xs">
										<bean:write name="result" property="patName" />
									</td>
									<td class="tbcellBorder tablePaddingBody" title="FollowUp Status">
										<font color="blue"><i class="fa fa-info-circle"></i><b><bean:write name="result" property="followUpStatus" /></b></font>
									</td>
									<td class="tbcellBorder tablePaddingBody  hidden-sm hidden-xs">
										<bean:write name="result" property="dateStr" />
									</td>
									<td class="tbcellBorder tablePaddingBody ">
										<font color="green"><b><bean:write name="result" property="claimAmt" /></b></font>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</logic:present> 
			</logic:notEmpty>
			<logic:empty name="followUpForm" property="followUpCochClaimList">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center" style="margin-top:50px !important">No Records Found.</div>
			</logic:empty>
		</div>
		
	</form>	
	<div class="modal fade"   id="progressBar" >
		<div class="modal-dialog modal-lg">
		    <div class="modal-body">
				 <div class="centerProgress" style="margin-right:45%;">
					 <div class="progress">
					    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
					      <span>Please Wait...</span>
					    </div>
					 </div>
				</div>
			</div>
		</div>
	</div>
	<div class="topArrow" onclick="javascript:goLeftRight('top')" data-toggle="tooltip" title="Go to Top" id="topArrow" ><i class="fa fa-arrow-circle-up fa-2x scrollIcon"></i><!-- <p align="center">Go Top</p> --></div>
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="bootstrap/js/bootstrap-datepicker.js"></script>
	<script src="bootstrap/js/formValidation.min.js"></script>
	<script src="bootstrap/validator/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootbox.min.js"></script>
	
	<script type="text/javascript">

	function goLeftRight(arg)
		{
			if(arg=='top')
				$('html , body').animate({scrollTop:0},1000);
			
		}
	$(document).on('scroll' , function () {
		if($(window).scrollTop()>100)
			$('#topArrow').fadeIn();
		else
			$('#topArrow').fadeOut();
	});
	function checkMsg()
		{
			fn_removeLoadingImage();
			var cochlearMsg='${cochlearMsg}';
			if(cochlearMsg!=null && cochlearMsg!='')
				bootbox.alert(cochlearMsg,function(){
					return true;
				}); 
		}
	function viewPreviousPages(minVal,totalPages,selectedPage,endIndex)
		{
			var newMinVal=minVal-10;
			var pageNoDisplay='';
			pageNoDisplay=pageNoDisplay+'<ul>';
			if(newMinVal >1)
				{
					pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+newMinVal+','+totalPages+','+selectedPage+','+endIndex+')" style="cursor: pointer;"></span></li>';
				}
			for(var i=newMinVal;i<minVal;i++)
				{
					if(selectedPage==i)
					{
						pageNoDisplay=pageNoDisplay+' <li class="active"><a href="javascript:fn_pagination('+i+','+endIndex+')">'+i+'</a></li>';
					}
				else
					{
						pageNoDisplay=pageNoDisplay+' <li><a href="javascript:fn_pagination('+i+','+endIndex+')">'+i+'</a></li>';
					}
				}
			
			pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+minVal+','+totalPages+','+selectedPage+','+endIndex+')" style="cursor: pointer;"></span></li></ul>';
			document.getElementById("pageNoDisplay").innerHTML=pageNoDisplay;
	
		}
	function fn_pagination(pageId,endIndex)
		{
			fn_loadingImage();
			var casesForApproval='${casesForApproval}';
			var PendingFlag='${PendingFlag}';
			document.getElementById("followUpFormSearch").action="followUpAction.do?actionFlag=cochlearFPClaim&endIndex="+endIndex+"&pageId="+pageId+"&casesForApproval="+casesForApproval+"&PendingFlag="+PendingFlag;
			document.getElementById("followUpFormSearch").method="Post";
			document.getElementById("followUpFormSearch").submit();
		}
	function fn_blockScreen()
		{
			$(function () { 
				 var $modal = $('#progressBar'),
			    $bar = $modal.find('.progress-bar progress-bar-striped active');
			
			$modal.modal('show');
			$bar.addClass('animate');
			
			setTimeout(function() {
			  $bar.removeClass('animate');
			  $modal.modal('hide');
			}, 30000);
			});	
		}
	function exportToCSV()
		{
			fn_blockScreen();
			var id=document.getElementById("csvImg");
			var casesForApproval='${casesForApproval}';
 			var PendingFlag='${PendingFlag}';
			id.onclick=fn_alreadyDownloaded;
			document.getElementById("followUpFormSearch").action="followUpAction.do?actionFlag=cochlearFPClaim&csvFlag=Y&startIndex=0&endIndex=0&pageId=1&casesForApproval="+casesForApproval+"&PendingFlag="+PendingFlag;
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_alreadyDownloaded()
		{
			bootbox.alert("CSV File has already been downloaded once.");
			return false;
		}
	function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
		{
			var pageNoDisplay='';
			pageNoDisplay=pageNoDisplay+'<ul>';
			var minVal=pageNo;
			var maxVal=minVal+9;
			if(maxVal>noOfPages)
				maxVal=noOfPages;
			pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
			for( var i=minVal ; i<=maxVal ; i++)
				{
					if(selectedPage==i)
						{
							pageNoDisplay=pageNoDisplay+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
						}
					else
						{
							pageNoDisplay=pageNoDisplay+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
						}
				}
			if(maxVal<noOfPages)
				{
					pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
				}
			document.getElementById("pageNoDisplay").innerHTML=pageNoDisplay;
		}
	function searchCases()
		{	
		
			if('${lStrUserState}' == 'CD203')
				{
					 if( (document.getElementById("searchFollowUpStatus").value==null     || document.getElementById("searchFollowUpStatus").value=='')     &&
						 (document.getElementById("searchCaseNo").value==null     		  || document.getElementById("searchCaseNo").value=='')     		&& 
						 (document.getElementById("searchFollowUpId").value==null 		  || document.getElementById("searchFollowUpId").value=='')			&&
						 (document.getElementById("searchPatName").value==null    		  || document.getElementById("searchPatName").value=='')   			&& 
						 (document.getElementById("searchFromDate").value==null   		  || document.getElementById("searchFromDate").value=='')   		&&
						 (document.getElementById("searchToDate").value==null     		  || document.getElementById("searchToDate").value==''  )   		&& 
						 (document.getElementById("searchCardNo").value==null     		  || document.getElementById("searchCardNo").value=='')     		&&
						 (document.getElementById("searchSchemeType").value==null     	  || document.getElementById("searchSchemeType").value=='')     	&&
						 ($('#followUpStatus').val()==null || $('#followUpStatus').val()=='') )
						 	{
						 		
						 		 bootbox.alert('Enter data into Any of the Fields to Search.',function(){
						 				document.getElementById('warningAlert').style.display="";
						 		}); 
						 		return false;
						 	}
				}
			else
				{
					if( (document.getElementById("searchFollowUpStatus").value==null     || document.getElementById("searchFollowUpStatus").value=='')     &&
							 (document.getElementById("searchCaseNo").value==null     		  || document.getElementById("searchCaseNo").value=='')     		&& 
							 (document.getElementById("searchFollowUpId").value==null 		  || document.getElementById("searchFollowUpId").value=='')			&&
							 (document.getElementById("searchPatName").value==null    		  || document.getElementById("searchPatName").value=='')   			&& 
							 (document.getElementById("searchFromDate").value==null   		  || document.getElementById("searchFromDate").value=='')   		&&
							 (document.getElementById("searchToDate").value==null     		  || document.getElementById("searchToDate").value==''  )   		&& 
							 (document.getElementById("searchCardNo").value==null     		  || document.getElementById("searchCardNo").value=='')     		&&
							 ($('#followUpStatus').val()==null || $('#followUpStatus').val()=='') )
							 	{
							 		
							 		 bootbox.alert('Enter data into Any of the Fields to Search.',function(){
							 				document.getElementById('warningAlert').style.display="";
							 		}); 
							 		return false;
							 	}
				}
				
			 
			 if((document.getElementById("searchFromDate").value!=null && document.getElementById("searchFromDate").value!='') &&
					 (document.getElementById("searchToDate").value==null || document.getElementById("searchToDate").value==''))
				 	{
				 		bootbox.alert('To Date is required as From Date is entered');
				 		return false;
				 	}
			 if((document.getElementById("searchToDate").value!=null && document.getElementById("searchToDate").value!='') &&
					 (document.getElementById("searchFromDate").value==null || document.getElementById("searchFromDate").value==''))
				 	{
				 		bootbox.alert('From Date is required as To Date is entered');
				 		return false;
				 	}
			 
			 
			 	$('#followUpFormSearch').formValidation().formValidation('validate');
			 	var result=$('#followUpFormSearch').data('formValidation').isValid();
				
			 	if(result==true)
			 		{
			 			var casesForApproval='${casesForApproval}';
			 			fn_loadingImage();
			 			var PendingFlag='${PendingFlag}';
						document.getElementById("followUpFormSearch").action="followUpAction.do?actionFlag=cochlearFPClaim&startIndex=0&endIndex=10&pageId=1&casesForApproval="+casesForApproval+"&PendingFlag="+PendingFlag;
						document.getElementById("followUpFormSearch").method="post";
						document.getElementById("followUpFormSearch").submit();
			 		}
			 	else
			 		{
				 		 bootbox.alert('Data entered into the Fields is invalid.Please enter valid data'); 
				 		 return false;
			 		}
				
		}
	function fn_close(arg)
		{
			document.getElementById(arg).style.display="none";
		}
	function resetFieldsNew()
		{
			$('#followUpFormSearch').formValidation('resetForm',true);
			$('#followUpFormSearch').data('formValidation').resetForm();
		}
	function openCase(flpId,caseId,flpStatus)
		{
			var casesForApproval='${casesForApproval}';
			var PendingFlag='${PendingFlag}';
			fn_loadingImage();
			document.getElementById("followUpFormSearch").action="followUpAction.do?actionFlag=openCochlearFPClaim&followUpId="+flpId+"&caseId="+caseId+"&casesForApproval="+casesForApproval+"&PendingFlag="+PendingFlag;
			document.getElementById("followUpFormSearch").method="post";
			document.getElementById("followUpFormSearch").submit();
		}
	function fn_removeLoadingImage()
		{   
			document.getElementById('progressBar').style.display='none';
		}
	function fn_loadingImage()
		{
				$(function () { $('#progressBar').modal({
					backdrop : 'static',
					keyboard : false,
					show: true
				   });});
		}
	$(document).ready(function (){
		
		$('[data-toggle="tooltip"]').tooltip();
		
		$('#searchFromDate').datepicker({
			autoclose:true,
			format:'dd/mm/yyyy',
			todayHighlight:true,
			clearBtn:true,
			startDate:'01/01/1990',
			endDate:new Date(),
		}).on('changeDate',function(selected){
			if(selected.date!=null && selected.date!='' && selected.date!=undefined)
				$('#searchToDate').datepicker('setStartDate',new Date(selected.date.valueOf()));
			else if(selected.date==undefined || selected.date!=null || selected.date!='' )
				$('#searchToDate').datepicker('setStartDate','01/01/1900');		
			
			$('#followUpFormSearch').formValidation('revalidateField','searchFromDate');
		});
		$('#searchToDate').datepicker({
			startDate: '01/01/1900',
			endDate:new Date(),
			todayHighlight:true,
			format:'dd/mm/yyyy',
			autoclose:true,
			clearBtn:true
		}).on('changeDate',function(selected){
		if(selected.date!=null && selected.date!='' && selected.date!=undefined)
			$('#searchFromDate').datepicker('setEndDate',new Date(selected.date.valueOf()));
		else if(selected.date==undefined || selected.date!=null || selected.date!='' )
			$('#searchFromDate').datepicker('setEndDate',new Date());
		
			$('#followUpFormSearch').formValidation('revalidateField','searchToDate');
		});
		
		$('#searchFollowUpStatus').select2();
		$('#searchSchemeType').select2();
		
		
 		$('#followUpFormSearch').formValidation({	
			framework: 'bootstrap',
			excluded : [':disabled'],
			icon     : {
							valid       : 'fa fa-check',
							invalid     : 'fa fa-times',
							validating  : 'fa fa-refresh',
					   },
			fields   : {
									searchCaseNo         	:  {
																	validators    :{
																						regexp		:{
																											regexp:/^[a-zA-Z0-9\/]+$/,
																											message:'Enter Valid Case No',	
																									 }
																				   },
													 			 },
									searchFollowUpId	   :  {
																	validators 	  :{
																						regexp		:{
																											regexp:/^[a-zA-Z0-9\/]+$/,
																											message:'Enter Valid FollowUp ID',	
																					 				 }
																				   },
													  			},
									searchPatName  	   		:  {
																	validators	  :{
																						regexp		:{
																											regexp:/^[a-zA-Z0-9 .]+$/,
																											message:'Enter Valid Patient Name',	
																	 				 				 },
																	 				 	stringLength:{
																											max			:100,
																											message		:'Patient Name can have a Maximum of 100 Characters Only'
																									 },			 
																			  			callback	:{
																											message:'Patient Name cannot have Consecutive Special Characters',
																											callback: function(value,validator,$field)
																												{
																													if(value.match(/(?=([\/.,\-=&%]{2}))/g))
																														return false;
																													else
																														return true;
																												}
																  									 },
																				   },
													 			 },
									searchFromDate	   		:  {
																	validators	  :{
																						date		:{
																											format:'DD/MM/YYYY',
																											message:'Enter Valid From Date',
																									 }
																				   },
																  },
								 	searchToDate	 	   :  {
																	validators	  :{
																						date		:{
																											format:'DD/MM/YYYY',
																											message:'Enter Valid To Date',
																									 }
																				   },
															  },
									searchCardNo         	:  {
																	validators    :{
																						regexp		:{
																											regexp:/^[a-zA-Z0-9\/]+$/,
																											message:'Enter Valid Card No',	
																									 }
																				   },
													  			},
					   }			   
		}).on('success.form.fv',function(e){ e.preventDefault();});
		
	
	});
	</script>
</body>
</html>