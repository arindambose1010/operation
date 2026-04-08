<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<title>Cochlear FollowUp Claim</title>
<style>
	body{
	font-size:13px !important;
	font-family:"Helvetica Neue", Helvetica, Arial, sans-serif !important;
	}
	.noPadding{
	padding:0px !important;
	}
	.minPadding{
	padding:5px !important;
	}
	.minSubPadding{
	padding-left:5px !important;
	padding-right:5px !important;
	padding-top:0px !important;
	padding-bottom:0px !important;
	}
	.lowPadding{
	padding:2px !important;
	}
	.paddingForAmts{
	padding-top:20px !important;
	padding-bottom:20px !important;
	}
	.tbcellCss{
	margin:0px !important;
	}
	.tbcellBorder{
	margin: 0px !important;
	}
	.form-group{
	margin:0px !important;
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
<body onload="checkMsg();">
	<form id="followUpClaimCochlear" name="followUpForm">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="detailsDiv">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minPadding" id="patDetailsDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Patient Details</b>
					</div>
					<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
							
								<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
									<div class="labelheading1 tbcellCss">
										<b>Name :</b>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8 lowPadding" >
									<div class="tbcellBorder">
										<b>${followUpDtls.patName}</b>
									</div>
								</div>
								<c:if test="${backBtn ne 'N'}">
									<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 noPadding ">
										<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4 lowPadding ">
											<div class="labelheading1 tbcellCss">
												<b>Gender :</b>
											</div>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8 lowPadding ">
											<div class="tbcellBorder">
												<b>${followUpDtls.gender}</b>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${backBtn eq 'N'}">
									<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding ">
										<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4 lowPadding ">
											<div class="labelheading1 tbcellCss">
												<b>Gender :</b>
											</div>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8 lowPadding ">
											<div class="tbcellBorder">
												<b>${followUpDtls.gender}</b>
											</div>
										</div>
									</div>
								</c:if>		
								<div class="col-lg-1 col-md-1 hidden-sm hidden-xs lowPadding ">
									<c:if test="${backBtn ne 'N'}">
										<input type="button" class="btn btn-primary" value="Back" title="Click to Go Back" style="width:100%;padding:3px !important" onclick="javascript:fn_goBack();"/>
									</c:if>
									
								</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
							<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
								<div class="labelheading1 tbcellCss">
									<b>Age :</b>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
								<div class="tbcellBorder">
									<b>${followUpDtls.age}</b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
								<div class="labelheading1 tbcellCss">
									<b>Card No :</b>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
								<div class="tbcellBorder">
									<b>${followUpDtls.cardNo}</b>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
								<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
									<div class="labelheading1 tbcellCss">
										<b>District :</b>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
									<div class="tbcellBorder">
										<b>${followUpDtls.district}</b>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
									<div class="labelheading1 tbcellCss">
										<b>Mandal :</b>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
									<div class="tbcellBorder">
										<b>${followUpDtls.mandal}</b>
									</div>
								</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
							<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
								<div class="labelheading1 tbcellCss">
									<b>Village :</b>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
								<div class="tbcellBorder">
									<b>${followUpDtls.village}</b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
								<div class="labelheading1 tbcellCss">
									<b>Contact No :</b>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
								<div class="tbcellBorder">
									<b>${followUpDtls.contactNo}</b>
								</div>
							</div>
						</div>	
					</div>	
					<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 noPadding" align="center">
							<logic:notEmpty name="followUpForm" property="photoUrl">
								<img  id="patImage" src="common/showDocument.jsp" width="120" height="125" />
							</logic:notEmpty>
							<logic:empty name="followUpForm" property="photoUrl">
								<img src="images/photonot.gif" width="120" height="125" alt="NO DATA"/>
							</logic:empty>
					</div>		
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 minPadding" id="casedetailsDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Case Details</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss">
								<b>Follow-Up ID :</b>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder">
								<b>${followUpDtls.followUpId}</b>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss">
								<b>Case ID :</b>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder">
								<b><a title="Click to view Case Details" href="javascript:fn_caseApproval('${followUpDtls.caseId}','','${followUpDtls.patientIpop}');"><font color="#EC0009">${followUpDtls.caseId}&nbsp;<span class="glyphicon glyphicon-new-window"></span></font></a></b>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss">
								<b>Case Status :</b>
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder">
								<b>${followUpDtls.followUpStatus}</b>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss">
								<b>Claim No :</b>
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder">
								<b>${followUpDtls.claimNo}</b>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss">
								<b>IP Regn Dt :</b>
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder">
								<b>${followUpDtls.ROUTE}</b>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss">
								<b>Procedure :</b>
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder">
								<b>${followUpDtls.procCode}</b>
							</div>
						</div>
					</div>		
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 minPadding" id="hospDetailsDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Hospital Details</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-2 col-md-2 col-sm-4 col-xs-4 lowPadding ">
							<div class="labelheading1 tbcellCss" style="min-height: 60px;padding-top: 15px;">
								<b>Name :</b>
							</div>
						</div>
						<div class="col-lg-10 col-md-10 col-sm-8 col-xs-8 lowPadding ">
							<div class="tbcellBorder" style="min-height: 60px;">
								<b>${followUpDtls.hospName}</b>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">	
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 lowPadding ">
							<div class="labelheading1 tbcellCss" >
								<b>Hospital Location :</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-6 col-xs-6 lowPadding ">
							<div class="tbcellBorder" >
								<b>${followUpDtls.hospCity}</b>
							</div>
						</div>
					</div>	
						
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 lowPadding ">
							<div class="labelheading1 tbcellCss" >
								<b>Date of Surgery :</b>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding ">
							<div class="tbcellBorder" >
								<b>${followUpDtls.csSurgDt}</b>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 lowPadding ">
							<div class="labelheading1 tbcellCss" >
								<b>Date of Discharge :</b>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding ">
							<div class="tbcellBorder" >
								<b>${followUpDtls.csDischDt}</b>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 lowPadding ">
							<div class="labelheading1 tbcellCss" >
								<b>Date of Admission :</b>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding ">
							<div class="tbcellBorder" >
								<b>${followUpDtls.csAdmDt}</b>
							</div>
						</div>
					</div>	
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minPadding" id="prevDetailsDiv">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
					<b>FollowUp Details</b>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="details">
					<c:if test="${followUpDtls.cochlearCountStr eq '0'}">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="line1">
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Post OP Period : </b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.postOP}</b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Wound Sight : </b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.woundSight}</b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Date of Switch On : </b>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.switchOnDate}</b>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="line2">
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-6 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Name of Audiologist : </b>
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.audiologist}</b>
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-6 col-xs-6 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Remarks:</b>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.postOPRemarks}</b>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${followUpDtls.cochlearCountStr gt '0' && followUpDtls.cochlearCountStr lt '5'}">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="line1">
							<div class="col-lg-3 col-md-3 col-sm-8 col-xs-8 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Audio Verbal Therapy From Date :</b>
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-4 col-xs-4 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.fromDate}</b>
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-8 col-xs-8 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Audio Verbal Therapy To Date :</b>
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-4 col-xs-4 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.toDate}</b>
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-8 col-xs-8 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Cochlear Committee Review Date : </b>
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-4 col-xs-4 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.reviewDate}</b>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="line2">
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Name of Audiologist : </b>
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.audiologistName}</b>
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-sm-3 col-xs-3 lowPadding">
								<div class="labelheading1 tbcellCss" >
									<b>Remarks:</b>
								</div>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-9 col-xs-9 lowPadding">
								<div class="tbcellBorder" >
									<b>${followUpDtls.childProgressRemarks}</b>
								</div>
							</div>
						</div>
					</c:if>	
				</div>
			</div>	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minPadding" id="prevDetailsDiv">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
					<b>FollowUp Claim Form</b>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">	
					<logic:notEmpty name="followUpForm" property="prevFlpDtls">
						<logic:present name="followUpForm" property="prevFlpDtls">
							<table class="" width="100%">
								<thead>
									<tr>
										<th class="labelheading1 tbcellCss">
											Follow Up Id
										</th>
										<th class="labelheading1 tbcellCss">
											Follow Up Type
										</th>
										<th class="labelheading1 tbcellCss hidden-sm hidden-xs">
											Follow Up Status
										</th>
										<th class="labelheading1 tbcellCss">
											Actual Package
										</th>
										<th class="labelheading1 tbcellCss">
											Claim Amount
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="result" items="${followUpForm.prevFlpDtls}" varStatus="status">
										<tr>
											<td class="tbcellBorder">
												<c:if test="${followUpDtls.followUpId ne result.followUpId}">
													<a href="javascript:openCase('<bean:write name="result" property="followUpId" />','<bean:write name="result" property="caseId" />','<bean:write name="result" property="statusId" />');" title="Click to Open Case Details"><font color="#EC0009"><b><bean:write name="result" property="followUpId" /> <i class="glyphicon glyphicon-new-window"></i></b></font></a>
												</c:if>
												<c:if test="${followUpDtls.followUpId eq result.followUpId}">
													<b><bean:write name="result" property="followUpId" /></b>
												</c:if>
											</td>
											<td class="tbcellBorder">
												<bean:write name="result" property="followUptype" />
											</td>
											<td class="tbcellBorder hidden-sm hidden-xs">
												<bean:write name="result" property="followUpStatus" />
											</td>
											<td class="tbcellBorder">
												<bean:write name="result" property="actualPack" />
											</td>
											<td class="tbcellBorder">
												<font color="green"><b><bean:write name="result" property="claimAmt" /></b></font>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</logic:present>
					</logic:notEmpty>
				</div>	
			</div>
		</div>
		<html:hidden name="followUpForm" property="searchFollowUpStatus" styleId="searchFollowUpStatus"/>
		<html:hidden name="followUpForm" property="searchCaseNo" styleId="searchCaseNo"/>
		<html:hidden name="followUpForm" property="searchFollowUpId" styleId="searchFollowUpId"/>
		<html:hidden name="followUpForm" property="searchPatName" styleId="searchPatName"/>
		<html:hidden name="followUpForm" property="searchFromDate" styleId="searchFromDate"/>
		<html:hidden name="followUpForm" property="searchToDate" styleId="searchToDate"/>
		<html:hidden name="followUpForm" property="searchCardNo" styleId="searchCardNo"/>
		<html:hidden name="followUpForm" property="searchSchemeType" styleId="searchSchemeType"/>	
	</form>
	
	<c:if test="${nwhCochlearForm eq 'show' }">
		<form id="nwhCochlearForm" name="followUpForm" action="followUpAction.do">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>NWH Cochlear FollowUp Claim Entry</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding ">
							<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss" >
									<b>Cochlear Follow Up ID :</b>
								</div>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder" >
									${followUpId}
								</div>
							</div>				
						</div>
						<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 noPadding ">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss" >
									<b>Cochlear Follow Up Date :</b>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder" >
									${followUpDate}
								</div>
							</div>		
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 noPadding ">
							<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6 lowPadding ">
								<div class="labelheading1 tbcellCss" >
									<b>Package Amount :</b>
								</div>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-6 col-xs-6 lowPadding ">
								<div class="tbcellBorder" >
									${actualPack}
								</div>
							</div>	
						</div>	
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Claim Amount :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-5 col-md-5 col-sm-6 col-xs-6 lowPadding ">
								<div class="tbcellBorder " style="padding-top:13px !important;padding-bottom:13px !important;" >
									<input type="text" name="claimNwhAmt"  id="claimNwhAmt" maxlength="10" style="height:35px !important;"  class="form-control" title="Enter Claim Amount" value="${followUpForm.claimNwhAmt}"/>
								</div>	
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Claim Remarks :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder " >
									<textarea name="medcoRemarks" id="medcoRemarks" style="" class="form-control" title="Enter Claim Remarks" onclick="" >${followUpForm.medcoRemarks}</textarea> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
			
		</form>
		<c:if test="${casesForApproval eq 'Y' && medcoYN eq 'Y'}">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding " id="buttonsDiv2" align="center" style="margin-bottom:20px;margin-top:15px;">
				<logic:notEmpty name="followUpForm" property="buttonList">
					<logic:present name="followUpForm" property="buttonList">
						<c:forEach var="buttons" items="${followUpForm.buttonList}">
							<button title="Clcick to Perform Action:${buttons.VALUE}" class="btn btn-danger actionButtons" value="${buttons.VALUE}" name="${buttons.ID}" id="${buttons.ID}" 
								onclick="javascript:fn_mainButtonClick('${buttons.ID}','${buttons.VALUE}')">${buttons.VALUE}</button>
						</c:forEach>
					</logic:present>
				</logic:notEmpty>
				<c:if test="${attachBut ne 'N' }">		
				<button class="btn btn-primary" name="attachBut" id="attachBut" title="Click to Open Attachments" onclick="javascript:fn_attachments()"><c:if test="${MedcoYN eq 'Y'}">Add/</c:if>View Attachments</button>
				</c:if>		
			</div>
		</c:if>
	</c:if>
	<c:if test="${namCochlearForm eq 'show' }">
		<form id="namCochlearForm">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b><c:if test="${loggedUserState ne 'CD201'}">Aarogya Mithra</c:if>
						   <c:if test="${loggedUserState eq 'CD201'}">Vaidya Mithra</c:if>
						</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
							<div class="labelheading1 tbcellCss paddingForAmts" >
								<b>Approved Remarks :</b> <font color="red">*</font>
							</div>	
						</div>
						<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding form-group">
							<div class="tbcellBorder " >
								<textarea name="namRemarks" id="namRemarks" style="" class="form-control" title="Enter Remarks" >${followUpForm.namRemarks}</textarea> 
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
			
		</form>
	</c:if>
	<c:if test="${cocCmtCochlearForm eq 'show' }">
		<form id="cocCmtCochlearForm" name="followUpForm" action="followUpAction.do">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Cochlear Committee</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Approved Amount :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-5 col-md-5 col-sm-6 col-xs-6 lowPadding ">
								<div class="tbcellBorder " style="padding-top:13px !important;padding-bottom:13px !important;" >
									<input type="text" name="claimCocCmtAmt"  id="claimCocCmtAmt" maxlength="10" style="height:35px !important;"  class="form-control" title="Enter Claim Amount" value="${followUpForm.claimCocCmtAmt}"/>
								</div>	
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Remarks :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder " >
									<textarea name="coccmtRemark" id="coccmtRemark" style="" class="form-control" title="Enter Remarks" onclick="" >${followUpForm.coccmtRemark}</textarea> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
			
		</form>
	</c:if>
	<c:if test="${dyEOCochlearForm eq 'show' }">
		<form id="dyEOCochlearForm" name="followUpForm" action="followUpAction.do">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Cochlear Trust Doctor</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Apporved Amount :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-5 col-md-5 col-sm-6 col-xs-6 lowPadding ">
								<div class="tbcellBorder " style="padding-top:13px !important;padding-bottom:13px !important;" >
									<input type="text" name="claimDyEoAmt"  id="claimDyEoAmt" maxlength="10" style="height:35px !important;"  class="form-control" title="Enter Approved Amount" value="${followUpForm.claimDyEoAmt}"/>
								</div>	
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Remarks :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder " >
									<textarea name="dyeoRemark" id="dyeoRemark" style="" class="form-control" title="Enter Remarks" onclick="" >${followUpForm.dyeoRemark}</textarea> 
								</div>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${casesForApproval eq 'Y' && medcoYN ne 'Y' && cochlearCHSentBack eq 'Show'}">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding " id="buttonsDiv2" align="center" style="margin-bottom:20px;margin-top:15px;">
						<logic:notEmpty name="followUpForm" property="buttonList">
							<logic:present name="followUpForm" property="buttonList">
								<c:forEach var="buttons" items="${followUpForm.buttonList}">
									<button title="Click to Perform Action:${buttons.VALUE}" class="btn btn-danger actionButtons" value="${buttons.VALUE}" name="${buttons.ID}" id="${buttons.ID}" 
										onclick="javascript:fn_mainButtonClick('${buttons.ID}','${buttons.VALUE}')">${buttons.VALUE}</button>
								</c:forEach>
							</logic:present>
						</logic:notEmpty>
						<c:if test="${attachBut ne 'N' }">
							<button class="btn btn-primary" name="attachBut" id="attachBut" title="Click to Open Attachments" onclick="javascript:fn_attachments()"><c:if test="${MedcoYN eq 'Y'}">Add/</c:if>View Attachments</button>
						</c:if>		
					</div>
				</c:if>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
			
		</form>
	</c:if>
	<c:if test="${acoCochlearForm eq 'show' }">
		<form id="acoCochlearForm" name="followUpForm" action="followUpAction.do">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Accounts Officer</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Remarks :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder " >
									<textarea name="acoRemark" id="acoRemark" style="" class="form-control" title="Enter Remarks" onclick="" >${followUpForm.acoRemark}</textarea> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
			
		</form>
	</c:if>
	<c:if test="${ceoCochlearForm eq 'show' }">
		<form id="ceoCochlearForm" name="followUpForm" action="followUpAction.do">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>Chief Executive Officer</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Remarks :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder " >
									<textarea name="ceoRemark" id="ceoRemark" style="" class="form-control" title="Enter Remarks" onclick="" >${followUpForm.ceoRemark}</textarea> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
		</form>
	</c:if>
	<c:if test="${cochlearSentBackForm eq 'show' }">
		<form id="cochlearSentBackForm" name="followUpForm" action="followUpAction.do">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minSubPadding" id="hospClaimDiv">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader">
						<b>SendBack(Updation)</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding form-group">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lowPadding ">
								<div class="labelheading1 tbcellCss paddingForAmts" >
									<b>Remarks :</b> <font color="red">*</font>
								</div>	
							</div>
							<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 lowPadding ">
								<div class="tbcellBorder " >
									<textarea name="sendBackRmrks" id="sendBackRmrks" style="" class="form-control" title="Enter Remarks" onclick="" >${followUpForm.sendBackRmrks}</textarea> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="followUpIdHidden" id="followUpIdHidden">
			<input type="hidden" name="caseIdHidden" id="caseIdHidden">
			<input type="hidden" name="casesForApprovalFlag" id="casesForApprovalFlag">
			<input type="hidden" name="lstrUsrGrp" id="lstrUsrGrp">
			<input type="hidden" name="submitButton" id="submitButton">
			
		</form>
	</c:if>
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 minPadding" style="display:none" id ="initiateFailAlert">
		<div class="alert alert-danger fade in noPadding" style="margin-bottom:0px !important;padding-top:5px !important ;padding-bottom:5px !important;width:90%" >
			&nbsp;<strong>Failed!</strong>&nbsp;Claim Cannot be Initiated as some of the Previous Follow Ups still needs to be Processed.
			<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('initiateFailAlert')"></i>
		</div>
	</div>
	<c:if test="${casesForApproval ne 'Y' }">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding " id="buttonsDivSub" align="center" style="margin-bottom:20px;margin-top:15px;">
				<c:if test="${ceoCochlearFormDisabled eq 'false'}">
					<button class="btn btn-danger actionButtons" name="CD97" id="CD97" value="Sent Back to CH" title="Click to Sent Back Payment" onclick="javascript:fn_mainButtonClick('CD97','Sent Back to CH')">Send Back to CH</button>
				</c:if>
			<button class="btn btn-primary" name="attachBut" id="attachBut" title="Click to Open Attachments" onclick="javascript:fn_attachments()">View Attachments</button>
		</div>
	</c:if>
	<c:if test="${casesForApproval eq 'Y' && medcoYN ne 'Y' && cochlearCHSentBack ne 'Show'}">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding " id="buttonsDiv2" align="center" style="margin-bottom:20px;margin-top:15px;">
			<logic:notEmpty name="followUpForm" property="buttonList">
				<logic:present name="followUpForm" property="buttonList">
					<c:forEach var="buttons" items="${followUpForm.buttonList}">
						<button title="Click to Perform Action:${buttons.VALUE}" class="btn btn-danger actionButtons" value="${buttons.VALUE}" name="${buttons.ID}" id="${buttons.ID}" 
							onclick="javascript:fn_mainButtonClick('${buttons.ID}','${buttons.VALUE}')">${buttons.VALUE}</button>
					</c:forEach>
				</logic:present>
			</logic:notEmpty>
			<c:if test="${attachBut ne 'N' }">
				<button class="btn btn-primary" name="attachBut" id="attachBut" title="Click to Open Attachments" onclick="javascript:fn_attachments()"><c:if test="${MedcoYN eq 'Y'}">Add/</c:if>View Attachments</button>
			</c:if>		
		</div>
	</c:if>
	<logic:notEmpty name = "followUpForm" property = "auditLst" >
			<logic:present name = "followUpForm" property = "auditLst"> 
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding tbheader headerLoc" >
				<b>Cochlear Follow UP Claim Work Flow</b>
			</div>
			
				<table  style="vertical-align: middle;width:100%;align:center">
					<thead>
						<tr>
							<th class="tbheader1" width="5%">
								S.No
							</th>
							<c:if test="${viewCochFlpAuditNames eq 'Y' }">
								<th class="tbheader1" width="18%">
									Name
								</th>
							</c:if>
							<th class="tbheader1" width="10%">
								Role
							</th>
							<th class="tbheader1" width="20%">
								Action
							</th>
							<th class="tbheader1" width="10%">
								Approve Amount
							</th>
							<th class="tbheader1" width="20%">
								Remarks
							</th>
							<th class="tbheader1" width="17%">
								Date and Time 
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items = "${followUpForm.auditLst}" var = "workflow" varStatus="status">
							<tr>
								<td class="tbcellBorder">
									${status.index+1}
								</td>
								<c:if test="${viewCochFlpAuditNames eq 'Y' }">
									<td class="tbcellBorder">
										<bean:write name="workflow" property="name" />
									</td>
								</c:if>	
								<td class="tbcellBorder">
									<font color="blue">
									  <b>
										<c:choose>
											<c:when test="${loggedUserState eq 'CD201' && workflow.userGrp eq 'MITHRA'}" >
												VAIDYA MITHRA
											</c:when>
											<c:otherwise >
												<bean:write name="workflow" property="userGrp" />
											</c:otherwise>
										</c:choose>
									  </b>
									</font>
								</td>
								<td class="tbcellBorder">
									<bean:write name="workflow" property="followUpStatus" />
								</td>
								<td class="tbcellBorder">
									<font color="#FF0000">
										<b><bean:write name="workflow" property="claimAmt" /></b>
									</font>
								</td>
								<td class="tbcellBorder">
									<bean:write name="workflow" property="remarks" />
								</td>
								<td class="tbcellBorder">
									<bean:write name="workflow" property="auditDate" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</logic:present>
		</logic:notEmpty>
	<%-- <logic:notEmpty name = "followUpForm" property = "auditLst" >
		<logic:present name = "followUpForm" property = "auditLst">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding " id="buttonsDiv" align="center" style="margin-bottom:20px;">
				<center ><button title="Click to See Workflow" class="btn btn-primary workFlowBut" data-toggle="modal" data-target="#dialog-workflow">Click to See Work Flow</button></center>
			</div>	
		</logic:present>
	</logic:notEmpty>	 --%>
	
	<div class="modal fade" id="dialog-workflow"  > 
	  <div class="modal-dialog" style="width:90%" > 
	    <div class="modal-content" >
	      <div class="modal-header bghdr"  >
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h2 class="modal-title" align="center"><font color="white">Cochlear Follow UP Claim Work Flow</font></h2>
	      </div>
	      <div class="modal-body" style="overflow-y:scroll;height:300px">
					<logic:notEmpty name = "followUpForm" property = "auditLst" >
						<logic:present name = "followUpForm" property = "auditLst">
							<table  style="vertical-align: middle;width:100%;align:center">
								<thead>
									<tr>
										<th class="tbheader1" width="5%">
											S.No
										</th>
										<c:if test="${viewCochFlpAuditNames eq 'Y' }">
											<th class="tbheader1" width="18%">
												Name
											</th>
										</c:if>
										<th class="tbheader1" width="10%">
											Role
										</th>
										<th class="tbheader1" width="20%">
											Action
										</th>
										<th class="tbheader1" width="10%">
											Approve Amount
										</th>
										<th class="tbheader1" width="20%">
											Remarks
										</th>
										<th class="tbheader1" width="17%">
											Date and Time 
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items = "${followUpForm.auditLst}" var = "workflow" varStatus="status">
										<tr>
											<td class="tbcellBorder">
												${status.index+1}
											</td>
											<c:if test="${viewCochFlpAuditNames eq 'Y' }">
												<td class="tbcellBorder">
													<bean:write name="workflow" property="name" />
												</td>
											</c:if>	
											<td class="tbcellBorder">
												<font color="blue"><b><bean:write name="workflow" property="userGrp" /></b></font>
											</td>
											<td class="tbcellBorder">
												<bean:write name="workflow" property="followUpStatus" />
											</td>
											<td class="tbcellBorder">
												<font color="#FF0000">
													<b><bean:write name="workflow" property="claimAmt" /></b>
												</font>
											</td>
											<td class="tbcellBorder">
												<bean:write name="workflow" property="remarks" />
											</td>
											<td class="tbcellBorder">
												<bean:write name="workflow" property="auditDate" />
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</logic:present>
					</logic:notEmpty>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	     </div>
	   </div>
	</div>
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
	<script src="bootstrap/js/bootstrap-datepicker.js"></script>
	<script src="bootstrap/js/formValidation.min.js"></script>
	<script src="bootstrap/validator/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootbox.min.js"></script>
	<script>
		var windowObj;
		function fn_mainButtonClick(id,value)
			{
				document.getElementById(id).disabled=true;
				$(".actionButtons").attr("disabled", "disabled");
				var result=false;
				var formId=null;
				
				var lStrUserGrpForCochFlp='${lStrUserGrpForCochFlp}';
				var PendingFlag='${PendingFlag}';
				
				if(PendingFlag!=null && PendingFlag=='Y')
					{
						if(lStrUserGrpForCochFlp=='GP2' || 
								lStrUserGrpForCochFlp== 'GP1' || lStrUserGrpForCochFlp=='GP71' ||
								 lStrUserGrpForCochFlp=='GP17')
							{
								$('#cochlearSentBackForm').formValidation().formValidation('validate');
								result=$('#cochlearSentBackForm').data('formValidation').isValid();
								formId="cochlearSentBackForm";
							}
						else if(lStrUserGrpForCochFlp== 'GP73')
							{
								$('#dyEOCochlearForm').formValidation().formValidation('validate');
								result=$('#dyEOCochlearForm').data('formValidation').isValid();
								formId="dyEOCochlearForm";
							}
					}
				else
					{
						if(lStrUserGrpForCochFlp=='GP2')
							{
								var checkStatus='${checkStatus}';
								if(checkStatus!=null && checkStatus!='' && checkStatus=='N')
									{
										bootbox.alert('Claim Cannot be Initiated as some of the Previous Follow Ups still needs to be Processed.',function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
											document.getElementById("initiateFailAlert").style.display="";
											//$("html,body").animate({scrollTop:document.body.scrollHeight},500);
										});
										return false;
									}
									
								var claimNwhAmt=$('#claimNwhAmt').val();
								var pckAmt='${actualPack}';
								var claimNwhAmtNum=0,pckAmtNum=0;
								
								if(claimNwhAmt!=null && pckAmt!=null)
									{
										claimNwhAmtNum=Number(claimNwhAmt);
										pckAmtNum=Number(pckAmt);
									}
								else
									{
										bootbox.alert('Please Check Package Amount and Claim Amount and try Again.',function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
										});
										return false;
									}
								if(claimNwhAmtNum>pckAmtNum)
									{
										bootbox.alert('Claim Amount cannot be Greater than Package Amount',function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
										});
										return false;
									}
								$('#nwhCochlearForm').formValidation().formValidation('validate');
								result=$('#nwhCochlearForm').data('formValidation').isValid();
								formId="nwhCochlearForm";
							}
						else if(lStrUserGrpForCochFlp=='GP1')
							{
								$('#namCochlearForm').formValidation().formValidation('validate');
								result=$('#namCochlearForm').data('formValidation').isValid();
								formId="namCochlearForm";
							}
						else if(lStrUserGrpForCochFlp=='GP71')
							{
								if(id=='CD33' || id=='CD31')
									{
										$('#cocCmtCochlearForm').formValidation('enableFieldValidators','claimCocCmtAmt',false);
									}
								else if(id=='CD32')
									{
										$('#cocCmtCochlearForm').formValidation('enableFieldValidators','claimCocCmtAmt',true);
									}					
							
								$('#cocCmtCochlearForm').formValidation().formValidation('validate');
								result=$('#cocCmtCochlearForm').data('formValidation').isValid();
								formId="cocCmtCochlearForm";
								
								var claimCocCmtAmt=$('#claimCocCmtAmt').val();
								var pckAmt='${actualPack}';
								var claimCocCmtAmtNum=0,pckAmtNum=0;
								var claimAmt=document.getElementById("claimNwhAmt").value;
								var regexp=/^[0-9]+$/;
								if(claimCocCmtAmt==null || claimCocCmtAmt=='')
									claimCocCmtAmt='0';
								if(!claimCocCmtAmt.match(regexp))
									{
										bootbox.alert("Enter Valid Numbers into Approved Amount",function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
											focusBox(document.getElementById("claimCocCmtAmt"));	
										});
										return false;
									}
								
								if(claimCocCmtAmt!=null && pckAmt!=null && claimAmt!=null)
									{
										claimCocCmtAmtNum=Number(claimCocCmtAmt);
										pckAmtNum=Number(pckAmt);
									}
								else
									{
										bootbox.alert('Please Check Package Amount and Cochlear Committee Approved Amount and try Again.',function(){
											focusBox(document.getElementById("claimCocCmtAmt"));
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
										});
										return false;
									}
								if(claimCocCmtAmtNum>pckAmtNum)
									{
										bootbox.alert('Cochlear Committee Approved Amount cannot be Greater than Package Amount',function(){
											focusBox(document.getElementById("claimCocCmtAmt"));
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
										});
										return false;
									}
								if(claimCocCmtAmtNum>Number(claimAmt))
									{
										bootbox.alert('Cochlear Committee Approved Amount cannot be Greater than Claim Amount',function(){
											focusBox(document.getElementById("claimCocCmtAmt"));
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
										});
										return false;
									}
							}
						else if(lStrUserGrpForCochFlp=='GP73')
							{
								if(id=='CD30' || id=='CD31')
									{
										$('#dyEOCochlearForm').formValidation('enableFieldValidators','claimDyEoAmt',false);
									}
								else if(id=='CD24' || id=='CD130')
									{
										$('#dyEOCochlearForm').formValidation('enableFieldValidators','claimDyEoAmt',true);
									}
								
								$('#dyEOCochlearForm').formValidation().formValidation('validate');
								result=$('#dyEOCochlearForm').data('formValidation').isValid();
								formId="dyEOCochlearForm";
								
								var claimDyEoAmt=$('#claimDyEoAmt').val();
								var pckAmt='${actualPack}';
								var claimDyEoAmtNum=0,pckAmtNum=0;
								var claimAmt=document.getElementById("claimNwhAmt").value;
								
								var regexp=/^[0-9]+$/;
								if(claimDyEoAmt==null || claimDyEoAmt=='')
									claimDyEoAmt='0';
								if(!claimDyEoAmt.match(regexp))
									{
										bootbox.alert("Enter Valid Numbers into Approved Amount",function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
											focusBox(document.getElementById("claimDyEoAmt"));	
										});
										return false;
									}
								
								if(claimDyEoAmt!=null && pckAmt!=null && claimAmt!=null)
									{
										claimDyEoAmtNum=Number(claimDyEoAmt);
										pckAmtNum=Number(pckAmt);
									}
								else
									{
										bootbox.alert('Please Check Package Amount and Cochlear Committee Approved Amount and try Again.',function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
											focusBox(document.getElementById("claiDyEoAmt"));	
										});
										
										return false;
									}
								if(claimDyEoAmtNum>pckAmtNum)
									{
										bootbox.alert('Cochlear Committee Approved Amount cannot be Greater than Package Amount.',function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
											focusBox(document.getElementById("claimDyEoAmt"));	
										});
										return false;
									}
								if(claimDyEoAmtNum>Number(claimAmt))
									{
										bootbox.alert('Cochlear Committee Approved Amount cannot be Greater than Claim Amount',function(){
											document.getElementById(id).disabled=false;
											$(".actionButtons").attr("disabled", false);
											focusBox(document.getElementById("claimDyEoAmt"));	
										});
										return false;
									}
							}
						else if(lStrUserGrpForCochFlp=='GP17')
							{
								$('#acoCochlearForm').formValidation().formValidation('validate');
								result=$('#acoCochlearForm').data('formValidation').isValid();
								formId="acoCochlearForm";
							}
						else if(lStrUserGrpForCochFlp=='GP40')
							{
								$('#ceoCochlearForm').formValidation().formValidation('validate');
								result=$('#ceoCochlearForm').data('formValidation').isValid();
								formId="ceoCochlearForm";
							}
					}
				
				if(result==true && formId!=null)
					{
						var confirmMsg='';
						if(value=='Pending')
							confirmMsg ='put Pending';
						else
							confirmMsg =value;
						
						var xmlhttp;
						var url;
						if (window.XMLHttpRequest) 
							{
								xmlhttp = new XMLHttpRequest();
							}	
						else if (window.ActiveXObject) 
							{
								xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
							}
						else 
							{
								bootbox.alert("Your Browser Does Not Support XMLHTTP!");
							}

						url = 'followUpAction.do?actionFlag=checkMandatoryAttch&followUpId=${followUpId}&attachType=ehfCochlearFollowUp&callType=Ajax';
						 xmlhttp.onreadystatechange=function()
							{ 
							    if(xmlhttp.readyState==4)
								    {	
								    	var resultArray=xmlhttp.responseText;
								    	var resultArray = resultArray.split("*");
								        if(resultArray[0]!=null)
									        {	  
									        	if(resultArray[0]=="SessionExpired")
									        		{
										    			var fr = partial(parent.sessionExpireyClose());
										    			bootbox.alert("Session has been expired" ,fr);
										    			return false;
										    		}
									    		else if(resultArray[0] =='success')
									        	    {
										    			if(id=='CD22' || id=='CD130')
											    			if(windowObj!=null)
																if(windowObj.closed==false)
																	windowObj.close();
									    			
														bootbox.confirm("Do you want to "+confirmMsg+" ?",function (result)
																{
																	if(result==true)
																		{
																			var followUpId='${followUpId}';
																			var caseId='${caseId}';
																			var caseForAppr='${casesForApproval}';
																			var lstrUserGrp='${lStrUserGrpForCochFlp}';
																			
																			if(document.getElementById(formId).followUpIdHidden!=null)
																				document.getElementById(formId).followUpIdHidden.value=followUpId;
																			if(document.getElementById(formId).caseIdHidden!=null)
																				document.getElementById(formId).caseIdHidden.value=caseId;
																			if(document.getElementById(formId).casesForApprovalFlag!=null)
																				document.getElementById(formId).casesForApprovalFlag.value=caseForAppr;
																			if(document.getElementById(formId).lstrUsrGrp!=null)
																				document.getElementById(formId).lstrUsrGrp.value=lstrUserGrp;
																			if(document.getElementById(formId).submitButton!=null)
																				document.getElementById(formId).submitButton.value=id;
																			
																			if(windowObj!=null)
																				if(windowObj.closed==false)
																					windowObj.close();
																			
																			fn_loadingImage();
																			var casesForApproval='${casesForApproval}';
																			if(casesForApproval!=null && casesForApproval=='CEO')
																				document.getElementById(formId).action="followUpAction.do?actionFlag=saveCochlearFlpClaim&casesForApproval="+casesForApproval+"&PendingFlag="+PendingFlag;
																				
																			document.getElementById(formId).action="followUpAction.do?actionFlag=saveCochlearFlpClaim&cochlearCHSentBack=${cochlearCHSentBack}casesForApproval=Y&PendingFlag="+PendingFlag;
																			document.getElementById(formId).method="post";
																			document.getElementById(formId).submit();
																		}
																	else
																		{
																			document.getElementById(id).disabled=false;
																			$(".actionButtons").attr("disabled", false);
																		}
																});
									        	   	}
									            else
									        	   	{
									        	   		bootbox.alert(resultArray[0],function(){
									        	   			document.getElementById(id).disabled=false;	
									        	   			$(".actionButtons").attr("disabled", false);
									        	   		});
														return false;
									        	   	}
									        } 
								    }
							}
				 		xmlhttp.open("Post",url,true);
						xmlhttp.send(null);	
					
					}
				else
					{
						//$("html,body").animate({scrollTop: document.body.scrollHeight},500);
						document.getElementById(id).disabled=false;
						$(".actionButtons").attr("disabled", false);
						return false;
					}
					
			}
		function checkMsg()
			{
				fn_removeLoadingImage();
				var cochlearMsg='${cochlearMsg}';
				var CEOSentBack='${CEOSentBackStatus}';
				if(CEOSentBack!=null && CEOSentBack!='')
					window.opener.submitCEOCochlear(CEOSentBack,cochlearMsg);
				else
					{
						disableFields();
						if(cochlearMsg!=null && cochlearMsg!='')
							bootbox.alert(cochlearMsg,function(){
								return true;
						});
					}
			}
		function disableFields()
			{
				if('${cochlearCHSentBack}'=='Show')
					$('#dyEOCochlearForm').formValidation('enableFieldValidators','claimDyEoAmt',true);
			
				if('${nwhCochlearDisabled}'=='true')
					{
						if(document.getElementById("nwhCochlearForm").claimNwhAmt!=null 
								&& document.getElementById("nwhCochlearForm").medcoRemarks!=null)
							{
								document.getElementById("nwhCochlearForm").claimNwhAmt.disabled=true;
								document.getElementById("nwhCochlearForm").medcoRemarks.disabled=true;
							}	
					}
				if('${namCochlearDisabled}'=='true')
					{
						if(document.getElementById("namCochlearForm").namRemarks!=null)
							{
								document.getElementById("namCochlearForm").namRemarks.disabled=true;
							}	
					}
				if('${cocCmtCochlearDisabled}'=='true')
					{
						if(document.getElementById("cocCmtCochlearForm").claimCocCmtAmt!=null
								&& document.getElementById("cocCmtCochlearForm").coccmtRemark!=null)
							{
								document.getElementById("cocCmtCochlearForm").claimCocCmtAmt.disabled=true;
								document.getElementById("cocCmtCochlearForm").coccmtRemark.disabled=true;
							}	
					}
				if('${dyEOCochlearFormDisabled}'=='true')
					{
						if(document.getElementById("dyEOCochlearForm").claimDyEoAmt!=null
								&& document.getElementById("dyEOCochlearForm").dyeoRemark!=null)
							{
								document.getElementById("dyEOCochlearForm").claimDyEoAmt.disabled=true;
								document.getElementById("dyEOCochlearForm").dyeoRemark.disabled=true;
							}		
					}
				 if('${acoCochlearFormDisabled}'=='true')
					{
						if(document.getElementById("acoCochlearForm").acoRemark!=null)
							{
								document.getElementById("acoCochlearForm").acoRemark.disabled=true;
							}		
					}
				 if('${ceoCochlearFormDisabled}'=='true')
					{
						if(document.getElementById("ceoCochlearForm").ceoRemark!=null)
							{
								document.getElementById("ceoCochlearForm").ceoRemark.disabled=true;
							}		
					}
				 if('${cochlearSentBackFormDisabled}'=='true')
					{
						if(document.getElementById("cochlearSentBackForm").sendBackRmrks!=null)
							{
								document.getElementById("cochlearSentBackForm").sendBackRmrks.disabled=true;
							}		
					}
				 
			}
		$(document).ready(function() {
			
			$('[data-toggle="tooltip"]').tooltip(); 
			
			$('#nwhCochlearForm').formValidation({
				
				framework	:'bootstrap',
				icon    	:	{
									valid     	:'fa fa-check',
									invalid	  	:'fa fa-times',
									validating	:'fa fa-refresh'
								},
				excluded	:[':disabled'],
				fields		:{
					
									claimNwhAmt 	:	{
															validators		:{
																					notEmpty		:{
																											message:'Claim Amount is Mandatory'
																									 },
																					regexp			:{
																											regexp:/^[0-9]+$/,
																											message:'Enter Valid Amount'
																									 },
																					greaterThan		:{
																											value:1,
																											message:'Claim Amount Should be greater than 0'
																									 },
																					callback		:{
																											message:'Claim Amount cannot be Greater than Package Amount',
																											callback: function(value,validator,$field)
																												{
																													var exp=/^[0-9]+$/;
																													if(value.match(exp))
																														{
																															var pckAmt='${actualPack}';
																															var pckNum=0,valueNum=0;
																															if(pckAmt!=null)
																																pckNum=Number(pckAmt);
																															if(value!=null)
																																valueNum=Number(value);
																															if(valueNum>pckNum)
																																	return false;
																															else
																																return true;
																														}
																													else 
																														return true;
																												},
																												
																									 },				 
																			 },										
														},
									medcoRemarks	:	{
															validators		:{
																					notEmpty 		 :{
																											message:'Medco Remarks are Mandatory'
																									  },
																					regexp			 :{
																											regexp: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																											message:'Medco Remarks cannot have special Characters other than .,-=&%/',
																									  },
																					stringLength	 :{
																											max:3000,
																											message:'Medco Remarks can have a Maximum of 3000 Characters Only'
																									  },
																					callback		 :{
																											message:'Medco Remarks cannot have Consecutive Special Characters',
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
								
							 }
				
			});
			var mithraRem='Mithra Remarks are Mandatory',mithraLength='Mithra Remarks can have a Maximum of 3000 Characters Only';
			var mithraSpec='Mithra Remarks cannot have special Characters other than .,-=&%/';
			var mithraCons='Mithra Remarks cannot have Consecutive Special Characters';
			if('${loggedUserState}'=='CD201')
				{
					mithraRem='Vaidya Mithra Remarks are Mandatory';
					mithraLength='Vaidya Mithra Remarks can have a Maximum of 3000 Characters Only';
					mithraSpec='Vaidya Mithra Remarks cannot have special Characters other than .,-=&%/';
					mithraCons='Vaidya Mithra Remarks cannot have Consecutive Special Characters';
				}
			else if('${loggedUserState}'!=null && '${loggedUserState}'!='')
				{
					mithraRem='Aarogya Mithra Remarks are Mandatory';
					mithraLength='Aarogya Mithra Remarks can have a Maximum of 3000 Characters Only';
					mithraSpec='Aarogya Mithra Remarks cannot have special Characters other than .,-=&%/';
					mithraCons='Aarogya Mithra Remarks cannot have Consecutive Special Characters';
				}
			$('#namCochlearForm').formValidation({
				
					framework	:'bootstrap',
					excluded	:[':disabled'],
					icon 		:
									{
										valid		:'fa fa-check',
										invalid		:'fa fa-times',
										validating	:'fa fa-refresh',
									},
					fields		:	{
										namRemarks	:	{
															validators:		{
																				notEmpty		:	{
																										message		:mithraRem
																									},
																				regexp			:	{
																										regexp		: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																										message		:mithraSpec
																									},
																				stringLength	:	{
																										max			:3000,
																										message		:mithraLength
																									},
																				callback	    :	{
																										message:mithraCons,
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
									},
				
			});
			$('#cocCmtCochlearForm').formValidation({
				
					framework	:'bootstrap',
					excluded	:[':disabled'],
					icon 		:
									{
										valid		:'fa fa-check',
										invalid		:'fa fa-times',
										validating	:'fa fa-refresh',
									},
					fields		:	{
										coccmtRemark	:	{
																validators:		{ 
																					notEmpty		:	{
																											message		:'Cochlear Committee Remarks are Mandatory',
																										},
																					regexp			:	{
																											regexp		: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																											message		:'Cochlear Committee Remarks cannot have special Characters other than .,-=&%/',
																										},
																					stringLength	:	{
																											max			:3000,
																											message		:'Cochlear Committee Remarks can have a Maximum of 3000 Characters Only'
																										},
																					callback	    :	{
																											message:'Cochlear Committee Remarks cannot have Consecutive Special Characters',
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
										claimCocCmtAmt 	:	{
																enabled: false,
																validators		:{
																						notEmpty		:{
																												message:'Cochlear Committee Amount is Mandatory'
																										 },
																						regexp			:{
																												regexp:/^[0-9]+$/,
																												message:'Enter Valid Amount'
																										 },
																						greaterThan		:{
																												value:1,
																												message:'Approved Amount Should be greater than 0'
																										 },
																						callback		:{
																												message:'Approved Amount cannot be Greater than Package Amount and Claim Amount',
																												callback: function(value,validator,$field)
																													{
																														var exp=/^[0-9]+$/;
																														if(value.match(exp))
																															{
																																var pckAmt='${actualPack}';
																																var claimAmt=document.getElementById("claimNwhAmt").value;
																																var pckNum=0,valueNum=0,claimAmtNum=0;
																																if(pckAmt!=null)
																																	pckNum=Number(pckAmt);
																																if(value!=null)
																																	valueNum=Number(value);
																																if(claimAmt!=null)
																																	claimAmtNum=Number(claimAmt);
																																if(valueNum>pckNum)
																																	return false;
																																else
																																	{
																																		if(valueNum>claimAmtNum)
																																			return false;
																																		else
																																			return true;	
																																	}
																																	
																															}
																														else 
																															return true;
																													},
																													
																										 },				 
																				 },										
															},					
									},				
				
				
			});
			$('#dyEOCochlearForm').formValidation({
				
				framework	:'bootstrap',
				excluded	:[':disabled'],
				icon 		:
								{
									valid		:'fa fa-check',
									invalid		:'fa fa-times',
									validating	:'fa fa-refresh',
								},
				fields		:	{
										dyeoRemark	:	{
															validators:		{ 
																				notEmpty		:	{
																										message		:'Cochlear Trust Doctor Remarks are Mandatory',
																									},
																				regexp			:	{
																										regexp		: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																										message		:'Cochlear Trust Doctor Remarks cannot have special Characters other than .,-=&%/',
																									},
																				stringLength	:	{
																										max			:3000,
																										message		:'Cochlear Trust Doctor Remarks can have a Maximum of 3000 Characters Only'
																									},
																				callback	    :	{
																										message:'Cochlear Trust Doctor Remarks cannot have Consecutive Special Characters',
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
										claimDyEoAmt 	:	{
															enabled: false,
															validators		:{
																					notEmpty		:{
																											message:'Cochlear Trust Doctor Amount is Mandatory'
																									 },
																					regexp			:{
																											regexp:/^[0-9]+$/,
																											message:'Enter Valid Amount'
																									 },
																					greaterThan		:{
																											value:1,
																											message:'Approved Amount Should be greater than 0'
																									 },																										 
																					callback		:{
																											message:'Approved Amount cannot be Greater than Package Amount and Claim Amount',
																											callback: function(value,validator,$field)
																												{
																													var exp=/^[0-9]+$/;
																													if(value.match(exp))
																														{
																															var pckAmt='${actualPack}';
																															var claimAmt=document.getElementById("claimNwhAmt").value;
																															var pckNum=0,valueNum=0,claimAmtNum=0;
																															if(pckAmt!=null)
																																pckNum=Number(pckAmt);
																															if(value!=null)
																																valueNum=Number(value);
																															if(claimAmt!=null)
																																claimAmtNum=Number(claimAmt);
																															if(valueNum>pckNum)
																																return false;
																															else
																																{
																																	if(valueNum>claimAmtNum)
																																		return false;
																																	else
																																		return true;	
																																}
																																
																														}
																													else 
																														return true;
																												},
																												
																									 },				 
																			 },										
														},					
								},				
			
			
		});
			$('#acoCochlearForm').formValidation({
				
				framework	:'bootstrap',
				excluded	:[':disabled'],
				icon 		:
								{
									valid		:'fa fa-check',
									invalid		:'fa fa-times',
									validating	:'fa fa-refresh',
								},
				fields		:	{
									acoRemark	:	{
														validators:		{
																			notEmpty		:	{
																									message		:'Accounts Officer Remarks are Mandatory',
																								},
																			regexp			:	{
																									regexp		: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																									message		:'Accounts Officer Remarks cannot have special Characters other than .,-=&%/',
																								},
																			stringLength	:	{
																									max			:3000,
																									message		:'Accounts Officer Remarks can have a Maximum of 3000 Characters Only'
																								},
																			callback	    :	{
																									message:'Accounts Officer Remarks cannot have Consecutive Special Characters',
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
								},
			
		});
			$('#cochlearSentBackForm').formValidation({
				
				framework	:'bootstrap',
				excluded	:[':disabled'],
				icon 		:
								{
									valid		:'fa fa-check',
									invalid		:'fa fa-times',
									validating	:'fa fa-refresh',
								},
				fields		:	{
									sendBackRmrks	:	{
														validators:		{
																			notEmpty		:	{
																									message		:'Send Back Updation Remarks are Mandatory',
																								},
																			regexp			:	{
																									regexp		: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																									message		:'Send Back Updation Remarks cannot have special Characters other than .,-=&%/',
																								},
																			stringLength	:	{
																									max			:3000,
																									message		:'Send Back Updation Remarks can have a Maximum of 3000 Characters Only'
																								},
																			callback	    :	{
																									message:'Send Back Updation Remarks cannot have Consecutive Special Characters',
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
								},
			
		});
			$('#ceoCochlearForm').formValidation({
							
							framework	:'bootstrap',
							excluded	:[':disabled'],
							icon 		:
											{
												valid		:'fa fa-check',
												invalid		:'fa fa-times',
												validating	:'fa fa-refresh',
											},
							fields		:	{
												ceoRemark	:	{
																	validators:		{
																						notEmpty		:	{
																												message		:'Send Back Remarks are Mandatory',
																											},
																						regexp			:	{
																												regexp		: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																												message		:'Send Back Remarks cannot have special Characters other than .,-=&%/',
																											},
																						stringLength	:	{
																												max			:3000,
																												message		:'Send Back Remarks can have a Maximum of 3000 Characters Only'
																											},
																						callback	    :	{
																												message:'Send Back Remarks cannot have Consecutive Special Characters',
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
											},
						
					});
		});
		
		function openCase(flpId,caseId,flpStatus)
			{
				var casesForApproval="N";
				url="followUpAction.do?actionFlag=openCochlearFPClaim&followUpId="+flpId+"&caseId="+caseId+"&casesForApproval="+casesForApproval+"&backBtn=N&attachBut=N";
				childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=1000, height=500, top=50,left=10');
			}
		
		function fn_caseApproval(caseId,arg,ipOpType)
			{ 
				url='casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&casesForApproval=N&ipOpType='+ipOpType;
				childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
			}
		function fn_goBack()
			{
				fn_loadingImage();
				var casesForApproval='${casesForApproval}';
				var PendingFlag='${PendingFlag}';
				document.forms[0].action="followUpAction.do?actionFlag=cochlearFPClaim&fromBack=Y&casesForApproval="+casesForApproval+"&PendingFlag="+PendingFlag;
				document.forms[0].method="post";
				document.forms[0].submit();
			}
		function focusBox(arg)
			{
				aField=arg;
				setTimeout("aField.focus()", 0);  
			}
		function openModal()
			{
				$('#dialog-workflow').modal({
					backdrop : 'static',
					keyboard : false,
					show : true
				},'show');
			}
		function fn_attachments()
			{	
				var cochCount='${cochCount}';
				var casesForApproval='${casesForApproval}';
				var cochRem='N';
				
				if(casesForApproval!=null && casesForApproval=='Y')
					cochRem='${cochRem}';
				var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfCochlearFollowUp&cochFollowupId=${followUpId}&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&cochRem="+cochRem+"&caseApprovalFlag=${casesForApproval}&cochCount="+cochCount;
				windowObj = window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
			}
		function fn_close(arg)
			{
				document.getElementById(arg).style.display="none";	
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
	</script>
</body>
</html>