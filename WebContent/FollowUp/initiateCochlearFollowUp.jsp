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
<meta name="viewport" content="width=device-width,initial-scale=1.2">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<title>Cochlear FollowUp</title>
<style>
	.centerProgress{
	width:60%;
	align:center;
	padding-top:30%;
	padding-left:38%;
	background-color:none;
	border-radius:10%;
	}
	.noPadding{
	 padding:0px !important;
	}
	.tbcellCss{
	margin:0px !important;
	}
	.lineMargin{
	margin-top:5px !important;
	/* margin-bottom:5px !important; */
	}
	.marginLeft{
	margin-left:20px !important;
	}
	.paddingLeft{
	padding-left:20px !important;
	}
	.defaultMargin{
	margin-top:5px !important;
	margin-bottom:5px !important;
	}
	.form-group{
	margin:0px !important;
	}
	.help-block{
	 margin-bottom:0px !important;
	  margin-left:30px !important;
	}
	.labelheading1{
	padding:5px !important;
	}
	.marginTop{
	margin-top:2px !important;
	}
	.radioInline{
	height:20px !important;
	width:18px !important;
	display:inline !important;
	box-shadow: none !important;
	}
	input[type='radio']{
	top:5px !important;
	}
	.form-control {
	padding: 0px !important;
	}
	body{
	font-size:14px;
	}
	input{
	font-size:14px;
	}
	.wrapper {
    display:inline-block;
    margin-top:80px;
    padding:15px;
    width:100%;
	}
	.error-wrapper h1 {
    font-size:90px;
    font-weight:300;
    margin:-141px 0 0 0;
    text-align:center;
	}
	.error-wrapper h2 {
	    font-size:58px;
	    font-weight:300;
	    margin:0;
	    text-transform:uppercase;
	}
	.boxed-page .container .wrapper {
    background:#F1F2F7;
    min-height:900px;
	}
	.lock-wrapper {
    margin:10% auto;
    max-width:500px;
	}.lock-wrapper img {
    position:absolute;
    left:33%;
    border-radius:50%;
    -webkit-border-radius:50%;
    border:10px solid #fff;
    width:160px;
    height:160px;
    background: #fff;
	}
	.radio{
	margin-top: 0px !important;
	margin-bottom: 0px !important;
	}
	#followUpForm .radioBlock .help-block{
	clear:both !important;
	}
	.bootbox-confirm .modal-content{
	top:50% !important;
	}
	.bootbox-confirm .modal-content .modal-body .bootbox-close-button{
	margin-top: -5px !important;
	}
	.bootbox-alert .modal-content{
	top:50% !important;
	}
	.bootbox-alert .modal-content .modal-body .bootbox-close-button{
	margin-top: -5px !important;
	}
	.topArrow{
	position:fixed;
	top:550px;
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
<body onload="javascript:fn_checkMsg();fn_removeLoadingImage();">
<form  id="followUpForm">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tbheader">
		<b>Cochlear FollowUp</b> 
	</div>
	<div class="alert alert-danger fade in noPadding" id="instructions1" style="margin-top:2px !important;margin-bottom:5px !important;padding-top:5px !important ;padding-bottom:5px !important;float:left;width:90%" align="left">
		<Strong>Note : </Strong> Date of Switch On must be after 30 days following Surgery Updation
		<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('instructions1')"></i>
	</div>
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tbheader">
		<b>Initial Mapping and Switch On</b>
	</div>
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="display:none" id ="initialInitiatedFailAlert">
		<div class="alert alert-danger fade in noPadding" style="margin-bottom:10px !important;padding-top:5px !important ;padding-bottom:5px !important;width:90%" >
			&nbsp;<strong>Failed!</strong>&nbsp;Initial Mapping and Switch On can be done only once.
			<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('initialInitiatedFailAlert')"></i>
		</div>
	</div>	
	
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">	
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin " style="margin-bottom:0px !important;">
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Post OP Period is Mandatory">
				<b>Post OP Period : </b><font color="red">*</font>
			</div>
			<div class=" col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding marginTop" >
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding form-group radioBlock" >
					<div class="radio" style="float:left; margin-left:20px">
						<label>
							<html:radio  name="followUpForm" property="postOP" styleClass="form-control radioInline" title="Select Post OP Period" styleId="uneventful" value="UNEVENTFUL"><p style="margin-top:10px">UnEventful</p></html:radio>
						</label>	
					</div>	
					<div class="radio" style="float:left; margin-left:20px">
						<label>		
							<html:radio name="followUpForm" property="postOP" styleClass="form-control radioInline" title="Select Post OP Period" styleId="complications" value="COMPLICATIONS"><p style="margin-top:10px">Complications(If Any)</p></html:radio>
						</label>	
					</div>			
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group marginTop">
				&nbsp;
			</div>
		</div>	
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin " style="margin-bottom:0px !important;">	
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Post OP Period is Mandatory">
				<b>Post OP Period Remarks: </b><font color="red">*</font>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 form-group marginTop">
				<html:textarea name="followUpForm" title="Enter Complications(If Any)" styleClass="form-control" property="postOPRemarks" styleId="postOPRemarks" style="width:100%;"></html:textarea>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin ">
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Wound Sight is Mandatory">
				<b>Wound Sight : </b><font color="red">*</font>
			</div>	
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding marginTop" >
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding form-group radioBlock"  >
						<div class="radio" style="float:left;margin-left:20px">
							<label>
								<html:radio name="followUpForm" property="woundSight" title="Select Wound Sight" styleId="healthy" value="HEALTHY" styleClass="form-control radioInline"><p style="margin-top:10px">Healthy</html:radio>
							</label>
						</div>		
						<div class="radio" style="float:left; margin-left:40px">
							<label>	
								<html:radio name="followUpForm" property="woundSight" title="Select Wound Sight" styleId="unhealthy" value="UNHEALTHY" styleClass="form-control radioInline"><p style="margin-top:10px">Unhealthy</p></html:radio>
							</label>
						</div>		
				</div>	
			</div>
			<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
				&nbsp;
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding  lineMargin ">
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Date Of Switch On is Mandatory">
				<b>Date of Switch On : </b><font color="red">*</font>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 form-group  marginTop" >
				<div class="input-group date " id="switchOnDateDP">
					<html:text name="followUpForm" property="switchOnDate" title="Select Date Of Switch On" style="width:102%" styleId="switchOnDate" styleClass="form-control "></html:text>
					<span class="input-group-addon">
				 		<font size="3em">
							<i class="fa fa-calendar"  style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select Date Of Switch On"></i>
						</font>
					</span>					
				</div>		
			</div>	
			<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
				&nbsp;
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding lineMargin ">
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Name of Audiologist is Mandatory">
				<b>Name of Audiologist : </b><font color="red">*</font>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 form-group  marginTop" >
				<html:text maxlength="50" name="followUpForm" property="audiologist" title="Enter Name Of Audiologist"  style="width:100%" styleId="audiologist" styleClass="form-control "></html:text>
			</div>	
			<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
				&nbsp;
			</div>
		</div>	
	</div>
	<div id ="initialButtonsDiv" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group" align="center" style="margin-top:2px !important;">
		<button name="submitCochlearInitialButton" id="submitCochlearInitialButton" title="Click to Submit the Initial Mapping and Switch ON" class="btn btn-primary" onclick="javascript:submitCochlear('followUpForm','0',this.id);">Submit</button>
		<button name="submitCochlearResetButton" id="submitCochlearResetButton" title="Click to Reset all the Fields" class="btn btn-danger formnovalidate" onclick="javascript:resetInitial('followUpForm');">Reset</button>
	</div>
	<c:if test="${cochlearCount ne '0'}">
		<div class="alert alert-danger fade in noPadding" id="instructions2" style="margin-top:2px !important;margin-bottom:5px !important;padding-top:5px !important ;padding-bottom:5px !important;float:left;width:90%" align="left">
			<Strong>Note : </Strong> From Date and To Date for Period of Audio Verbal Therapy Should have a minimum Time gap of 90 Days. 
			<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('instructions2')"></i>
		</div>
	</c:if>	
	<logic:notEmpty name="followUpForm" property="followUpCochList">
		<logic:present name="followUpForm" property="followUpCochList"> 
			<%-- <logic:iterate name="followUpForm" property="followUpCochList" id="cochlearFollowup"> --%>
			<c:forEach items="${followUpForm.followUpCochList}" var="cochlearFollowup" varStatus="status">
				<c:if test="${cochlearFollowup.cochlearCount gt 0 && cochlearFollowup.cochlearCount lt 5 }">
					<c:choose>
						<c:when test="${cochlearFollowup.cochlearCount eq 1}">
							<c:set var="quarter" value="1st" />
						</c:when>
						<c:when test="${cochlearFollowup.cochlearCount eq 2}">
							<c:set var="quarter" value="2nd" />
						</c:when>
						<c:when test="${cochlearFollowup.cochlearCount eq 3}">
							<c:set var="quarter" value="3rd" />
						</c:when>
						<c:when test="${cochlearFollowup.cochlearCount eq 4}">
							<c:set var="quarter" value="4rth" />
						</c:when>
					</c:choose>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tbheader">
						<b>Audio Verbal Therapy(${quarter} Quarter)</b>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">	
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin ">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding  labelheading1 tbcellCss defaultMargin  marginTop" title="Period of Audio Verbal Therapy is Mandatory">
								<b>Period of Audio Verbal Therapy : </b><font color="red">*</font>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding defaultMargin form-group  marginTop" >
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">	
									<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 noPadding">
										<label class="paddingLeft" title="From Date is Mandatory" style="margin-top:8px">From Date :<font color="red">*</font></label>
									</div>
									<div class="input-group" style="padding-right: 15px;padding-left:15px"> 
										<%-- <html:text name="followUpForm" title="Select from Date" style="width:102%" property="fromDate" styleClass="form-control" styleId="fromDate" ></html:text> --%>
										<input type="text" name="fromDate${status.index}" disabled="disabled" title="From Date" style="width:102%" id="fromDate${status.index}" class="form-control"  value="${cochlearFollowup.fromDate}"/>
										<span class="input-group-addon">
											<font size="3em">
													<i class="fa fa-calendar" id="fromDtadd1" style="cursor:pointer;float:left;color:#139bd7;padding-left:10px;"  title="Select From Date"></i>
											</font>
										</span>	
									</div>
								</div>		
							</div> 
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding defaultMargin form-group  marginTop">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding ">
									<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 noPadding">
										<label class="paddingLeft" title="To Date is Mandatory" style="margin-top:8px">To Date : <font color="red">*</font></label>
									</div>
									<div class="input-group" style="padding-right: 15px;padding-left:15px"> 
										<%-- <html:text name="followUpForm" styleClass="form-control" style="width:102%" title="Select To Date" property="toDate" styleId="toDate"></html:text> --%>
										<input type="text" name="toDate${status.index}" disabled="disabled" title="To Date" style="width:102%" id="toDate${status.index}" class="form-control"  value="${cochlearFollowup.toDate}"/>
										<span class="input-group-addon">
											<font size="3em">
													<i class="fa fa-calendar" id="toDtadd1" style="cursor:pointer;float:left;color:#139bd7;padding-left:10px;"  title="Select To Date"></i>
											</font>
										</span>
									</div>	
								</div>	
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding lineMargin ">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Progress of Child is Mandatory">
								<b>Progress of Child Remarks : </b><font color="red">*</font>
							</div>	
							<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 noPadding form-group  marginTop">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group">
									<%-- <html:textarea name="followUpForm" title="Enter Remarks about Progress Of Child " style="width:100%;" property="childProgressRemarks" styleId="childProgressRemarks" styleClass="form-control"></html:textarea> --%>
									<textarea name="childProgressRemarks${status.index}" disabled="disabled" title="Remarks about Progress Of Child " style="width:100%" id="childProgressRemarks${status.index}" class="form-control"   >${cochlearFollowup.childProgressRemarks}</textarea>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding  lineMargin ">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Date of Review by Cochlear Committee is Mandatory"> 
								<b>Date of Review by Cochlear Committee : </b><font color="red">*</font>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding form-group  marginTop">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="input-group">
										<%-- <html:text name="followUpForm" title="Select Date of Review by Cochlear Committee" property="reviewDate" style="width:102%" styleId="reviewDate" styleClass="form-control "></html:text> --%>
										<input type="text" name="reviewDate${status.index}" disabled="disabled" title="Date of Review by Cochlear Committee" style="width:102%" id="reviewDate${status.index}" class="form-control"  value="${cochlearFollowup.reviewDate}"/>
									<span class="input-group-addon">
										<font size="3em">
											<i class="fa fa-calendar" id="reviewDt" style="cursor:pointer;float:left;color:#139bd7;padding-left:10px;"  title="Select Date of Review by Cochlear Committee"></i>
										</font>
									</span>	
									</div>
								</div>	
							</div>		
							<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
								&nbsp;
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin  ">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding labelheading1 tbcellCss" title="Name of Audiologist is Mandatory">
								<b>Name of Audiologist : </b><font color="red">*</font>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  form-group ">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<%-- <html:text name="followUpForm" property="audiologistName" styleClass="form-control" title="Enter Name of Audiologist " style="width:100%" styleId="audiologistName" ></html:text> --%>
									<input maxlength="50" type="text" name="audiologistName${status.index}" disabled="disabled" title="Name of Audiologist" style="width:100%" id="audiologistName${status.index}" class="form-control"  value="${cochlearFollowup.audiologistName}"/>
								</div>	
							</div>	
							<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
								&nbsp;
							</div>
						</div>
						<c:if test="${cochlearFollowup.cochlearCount eq 4}">
						
						<br>&nbsp;<br><br>
						
						</c:if>
					</div>
				</c:if>	
			</c:forEach>	
			<%-- </logic:iterate> --%>
		 </logic:present>
	</logic:notEmpty>
</form>
	
	<c:if test="${cochlearCount ne null && toStartFUS ne null}">
		<c:if test="${(cochlearCount>toStartFUS)}">
			<c:forEach var="index" begin="${toStartFUS}"  end="${cochlearCount-1}" varStatus="status">
				<form id="followUpFormOld${status.index}">
					<c:if test="${index ne 0}">
						<c:choose>
							<c:when test="${index eq 1}">
								<c:set var="quarter" value="1st" />
								<c:set var="value" value="1" />
							</c:when>
							<c:when test="${index eq 2}">
								<c:set var="quarter" value="2nd" />
								<c:set var="value" value="2" />
							</c:when>
							<c:when test="${index eq 3}">
								<c:set var="quarter" value="3rd" />
								<c:set var="value" value="3" />
							</c:when>
							<c:when test="${index eq 4}">
								<c:set var="quarter" value="4rth" />
								<c:set var="value" value="4" />
							</c:when>
						</c:choose>	
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tbheader">
							<b>Audio Verbal Therapy(${quarter} Quarter)</b>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="display:none" id ="formOldFailAlert${status.index}">
							  <div class="alert alert-danger fade in noPadding" style="margin-bottom:10px !important;padding-top:5px !important ;padding-bottom:5px !important;width:90%">
							  	  <c:if test="${quarter ne '1st' }">
								  	<strong>Failed!</strong> ${quarter} Audio Verbal Theraphy cannot be initiated as previous Theraphies needs to be initiated before this.
								  </c:if>
								  <c:if test="${quarter eq '1st' }">	
								  	&nbsp;<strong>Failed!</strong> ${quarter} Audio Verbal Theraphy cannot be initiated as Initial Mapping and Switch On needs to be Completed before this.
								  </c:if>
								  <i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('formOldFailAlert${status.index}')"></i>
							  </div>
							  
						 </div> 
						 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="display:none" id ="formOldSuccessAlert${status.index}">
							  <div class="alert alert-success noPadding" style="margin-bottom:10px !important;padding-top:5px !important ;padding-bottom:5px !important;width:90%">
							  		&nbsp;<strong>Success!</strong> ${quarter} Audio Verbal Theraphy has been Successfully initiated.
							  		<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('formOldSuccessAlert${status.index}')"></i> 
							  </div>
						 </div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">	
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin ">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding  labelheading1 tbcellCss defaultMargin  marginTop" title="Period of Audio Verbal Therapy is Mandatory">
									<b>Period of Audio Verbal Therapy : </b><font color="red">*</font>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding defaultMargin form-group  marginTop" id="div1">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">	
										<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 noPadding">
											<label class="paddingLeft" title="From Date is Mandatory" style="margin-top:8px">From Date :<font color="red">*</font></label>
										</div>
										<div class="input-group  date " id="fromDatePrevDP[${status.index}]" style="padding-right: 15px;padding-left:15px"> 
											<html:text name="followUpForm" title="Select From Date" style="width:102%" property="fromDatePrev[${status.index}]" styleClass="form-control fromDatePrev" styleId="fromDatePrev[${status.index}]" ></html:text>
											<span class="input-group-addon">
												<font size="3em">
														<i class="fa fa-calendar"  style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select From Date"></i>
												</font>
											</span>	
										</div>
									</div>		
								</div> 
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding defaultMargin form-group  marginTop">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding ">
										<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 noPadding">
											<label class="paddingLeft" title="To Date is Mandatory" style="margin-top:8px">To Date : <font color="red">*</font></label>
										</div>
										<div class="input-group date " id="toDatePrevDP[${status.index}]" style="padding-right: 15px;padding-left:15px">
											<html:text name="followUpForm" styleClass="form-control toDatePrev" style="width:102%" title="Select To Date" property="toDatePrev[${status.index}]" styleId="toDatePrev[${status.index}]"  ></html:text>
											<span class="input-group-addon">
												<font size="3em">
														<i class="fa fa-calendar"  style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select To Date"></i>
												</font>
											</span>
										</div>	
									</div>	
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding lineMargin ">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Progress of Child is Mandatory">
									<b>Progress of Child Remarks : </b><font color="red">*</font>
								</div>	
								<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 noPadding form-group  marginTop">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<html:textarea name="followUpForm" title="Enter Remarks about Progress Of Child " style="width:100%;" property="childProgressRemarksPrev[${status.index}]" styleId="childProgressRemarksPrev[${status.index}]" styleClass="form-control childProgressRemarksPrev"></html:textarea>
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding  lineMargin ">
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Date of Review by Cochlear Committee is Mandatory"> 
									<b>Date of Review by Cochlear Committee : </b><font color="red">*</font>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding form-group  marginTop">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="input-group date" id="reviewDatePrevDP[${status.index}]" >
											<html:text name="followUpForm" readonly="true"  title="Select Date of Review by Cochlear Committee" property="reviewDatePrev[${status.index}]" style="width:102%" styleId="reviewDatePrev[${status.index}]" styleClass="form-control reviewDatePrev" ></html:text>
										<span class="input-group-addon">
											<font size="3em">
												<i class="fa fa-calendar"  style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select Date of Review by Cochlear Committee"></i>
												
											</font>
										</span>	
										</div>
									</div>	
								</div>		
								<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
									&nbsp;
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin  ">
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding labelheading1 tbcellCss" title="Name of Audiologist is Mandatory">
									<b>Name of Audiologist : </b><font color="red">*</font>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  form-group ">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<html:text maxlength="50" name="followUpForm" property="audiologistNamePrev[${status.index}]" styleClass="form-control audiologistNamePrev" title="Enter Name of Audiologist " style="width:100%" styleId="audiologistNamePrev[${status.index}]" ></html:text>
									</div>	
								</div>	
								<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding" align="center">
									&nbsp;
								</div>
							</div>
							<div id ="buttonsDiv"  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center" style="margin-top:2px">
								<button name="submitCochlearPrevButton" id="submitCochlearPrevButton${value}" title="Click to Submit the Cochlear Follow Up" class="btn btn-primary" onclick="javascript:submitCochlear('followUpFormOld',${value},this.id);">Submit</button>
								<button name="cochlearPrevResetButton"  id="cochlearPrevResetButton${value}" title="Click to Reset all the Fields" class="btn btn-danger" onclick="javascript:resetInitial('followUpFormOld${value}');">Reset</button>
							</div>	
						</div>
					</c:if>
				</form>
			</c:forEach>
			<br>
		</c:if>	
	</c:if>

<form id="followUpFormPresent">
	<c:if test="${cochlearCount gt 0 && cochlearCount lt 5 && buttonsDiv ne null}">
		<c:if test="${buttonsDiv eq 'show'}">
			<c:choose>
				<c:when test="${cochlearCount eq 1}">
					<c:set var="quarter" value="1st"/>
				</c:when>
				<c:when test="${cochlearCount eq 2}">
					<c:set var="quarter" value="2nd"/>
				</c:when>
				<c:when test="${cochlearCount eq 3}">
					<c:set var="quarter" value="3rd"/>
				</c:when>
				<c:when test="${cochlearCount eq 4}">
					<c:set var="quarter" value="4rth"/>
				</c:when>
			</c:choose>		
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tbheader">
				<b>Audio Verbal Therapy(${quarter} Quarter)</b>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="display:none" id ="formPresentFailAlert">
				  <div class="alert alert-danger fade in noPadding" style="margin-bottom:10px !important;padding-top:5px !important ;padding-bottom:5px !important;width:90%">
				  	  <c:if test="${quarter ne '1st' }">
					  	<strong>Failed!</strong> ${quarter} Audio Verbal Theraphy cannot be initiated as previous Theraphies needs to be initiated before this.
					  </c:if>
					  <c:if test="${quarter eq '1st' }">	
					  	&nbsp;<strong>Failed!</strong> ${quarter} Audio Verbal Theraphy cannot be initiated as Initial Mapping and Switch On needs to be Completed before this.
					  </c:if>
					  <i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('formPresentFailAlert')"></i>
				  </div>
				  
			 </div> 
			 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="display:none" id ="formPresentSuccessAlert">
				  <div class="alert alert-success noPadding" style="margin-bottom:10px !important;padding-top:5px !important ;padding-bottom:5px !important;width:90%">
				  		&nbsp;<strong>Success!</strong> ${quarter} Audio Verbal Theraphy has been Successfully initiated.
				  		<i class="fa fa-times" style="float:right;cursor:pointer;font-size: 18px;" onclick="javascript:fn_close('formPresentSuccessAlert')"></i> 
				  </div>
			 </div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">	
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin ">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding  labelheading1 tbcellCss defaultMargin  marginTop" title="Period of Audio Verbal Therapy is Mandatory">
						<b>Period of Audio Verbal Therapy : </b><font color="red">*</font>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding defaultMargin form-group  marginTop" >
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">	
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 noPadding">
								<label class="paddingLeft" title="From Date is Mandatory" style="margin-top:8px">From Date :<font color="red">*</font></label>
							</div>
							<div class="input-group date" id="fromDateDP" style="padding-right: 15px;padding-left:15px"> 
								<html:text name="followUpForm" title="Select from Date" style="width:102%" property="fromDate" styleClass="form-control " styleId="fromDate" ></html:text>
								<span class="input-group-addon">
									<font size="3em">
											<i class="fa fa-calendar"  style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select From Date"></i>
									</font>
								</span>	
							</div>
						</div>		
					</div> 
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding defaultMargin form-group  marginTop">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding ">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 noPadding">
								<label class="paddingLeft" title="To Date is Mandatory" style="margin-top:8px">To Date : <font color="red">*</font></label>
							</div>
							<div class="input-group date " id="toDateDP" style="padding-right: 15px;padding-left:15px">
								<html:text name="followUpForm" styleClass="form-control" style="width:102%" title="Select To Date" property="toDate" styleId="toDate"></html:text>
								<span class="input-group-addon">
									<font size="3em">
											<i class="fa fa-calendar"  style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select To Date"></i>
									</font>
								</span>
							</div>	
						</div>	
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  noPadding lineMargin ">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Progress of Child is Mandatory">
						<b>Progress of Child Remarks : </b><font color="red">*</font>
					</div>	
					<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 noPadding form-group  marginTop">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<html:textarea name="followUpForm" title="Enter Remarks about Progress Of Child " style="width:100%;" property="childProgressRemarks" styleId="childProgressRemarks" styleClass="form-control"></html:textarea>
						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding  lineMargin ">
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  labelheading1 tbcellCss marginTop" title="Date of Review by Cochlear Committee is Mandatory"> 
						<b>Date of Review by Cochlear Committee : </b><font color="red">*</font>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding form-group  marginTop">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="input-group date " id="reviewDateDP">
								<html:text name="followUpForm" readonly="true" title="Select Date of Review by Cochlear Committee" property="reviewDate" style="width:102%" styleId="reviewDate" styleClass="form-control "></html:text>
							<span class="input-group-addon">
								<font size="3em">
									<i class="fa fa-calendar" style="cursor:pointer;float:left;color:#139bd7;padding-left:0px;"  title="Select Date of Review by Cochlear Committee"></i>
								</font>
							</span>	
							</div>
						</div>	
					</div>		
					<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
						&nbsp;
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding lineMargin  ">
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding labelheading1 tbcellCss" title="Name of Audiologist is Mandatory">
						<b>Name of Audiologist : </b><font color="red">*</font>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 noPadding  form-group ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<html:text name="followUpForm" property="audiologistName" styleClass="form-control" title="Enter Name of Audiologist " maxlength="50" style="width:100%" styleId="audiologistName" ></html:text>
						</div>	
					</div>	
					<div class="col-lg-4 col-md-4 hidden-sm hidden-xs noPadding">
						&nbsp;
					</div>
				</div>	
			</div>
		</c:if>
	</c:if>
	<c:if test="${buttonsDiv ne null && cochlearCount gt 0 && cochlearCount lt 5}">
		<c:if test="${buttonsDiv eq 'show'}">
			<div id ="buttonsDiv" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center" style="margin-top:5px">
				<button name="submitCochlearPresButton" id="submitCochlearPresButton" title="Click to Submit the Cochlear Follow Up" class="btn btn-primary" onclick="javascript:submitCochlear('followUpFormPresent',${cochlearCount},this.id);">Submit</button>
				<button name="cochlearPresResetButton" id="cochlearPresResetButton" title="Click to Reset all the Fields" class="btn btn-danger" onclick="javascript:resetInitial('followUpFormPresent');">Reset</button>
			</div>
		</c:if>
	</c:if>	
	
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
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="bootstrap/js/bootbox.min.js"></script>
	
	<script>

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
	function fn_removeLoadingImage()
		{   
			parent.document.getElementById('progressBar').style.display='none';
		}
	function fn_loadingImage()
		{
				$(function () { $('#progressBar').modal({
					backdrop : 'static',
					keyboard : false,
					show: true
				   });});
		}
	var surgDtArr='${surgUpdDtforCoch}'.split('/');
	var surgDtCoch=new Date(surgDtArr[2],Number(surgDtArr[1])-1,surgDtArr[0]);
	
	var swithOnDtArr='${switchOnDt}'.split('/');
	var swithOnDt=new Date(swithOnDtArr[2],Number(swithOnDtArr[1])-1,swithOnDtArr[0]);
	
	var prevReviewDtArr='${prevReviewDt}'.split('/');
	var prevReviewDt=new Date(prevReviewDtArr[2],Number(prevReviewDtArr[1])-1,prevReviewDtArr[0]);
	function getStrtDtEndDt(date,val,arg)
		{
			var dt=new Date();
			if(arg=='startDt')
				{
					if(val==1)
						dt=swithOnDt;
					else
						dt=prevReviewDt;
						//dt=new Date(surgDtCoch.getTime()+((val-1)*90)*24*60*60*1000);
				}
			else if(arg=='endDt')
				{
					if(val==1)
						dt=new Date(swithOnDt.getTime()+364*24*60*60*1000);
					else
						dt=new Date(prevReviewDt.getTime()+364*24*60*60*1000);
						//dt=new Date(surgDtCoch.getTime()+(89+(val-1)*90)*24*60*60*1000);
				}
			
			/* if(new Date() < dt)
				dt=new Date(); */
			if(date!=null)
				dt=new Date(dt.getTime()+90*24*60*60*1000);
			return dt;			
		}
	function fn_onloadDP(){
		var switchOnDt=new Date(surgDtCoch.getTime()+30*24*60*60*1000);
		var switchOnEndDt=new Date(switchOnDt.getTime()+364*24*60*60*1000);
		
		/* if(new Date() < switchOnDt)
			switchOnDt=new Date();
		if(new Date() < switchOnEndDt)
			switchOnEndDt=new Date(); */
		
		var toStartFUS='${toStartFUS}';
		var missedFUS='${missedFUS}';
		var cochlearCount='${cochlearCount}';
		
		if(missedFUS!=null && cochlearCount!=null && toStartFUS!=null)
			{	
				var missed=Number(missedFUS);
				var count=Number(cochlearCount);
				var start=Number(toStartFUS);
				if(missed>0)
					{
						for(var i=start;i<(count);i++)
							{
								if(i>0)
									{
										var fromDtStrtDt=new Date('2000','00','01');
										var commEndDt=new Date();
											
										if(i==toStartFUS)
											{
												fromDtStrtDt=getStrtDtEndDt(null,i,'startDt');
												commEndDt=getStrtDtEndDt(null,i,'endDt');	
											}
										
										$('#fromDatePrevDP\\['+ i +'\\]').datepicker({
											startDate: fromDtStrtDt,
											endDate:   commEndDt,
											todayHighlight:true,
											format:'dd/mm/yyyy',
											autoclose:true,
											clearBtn:true
										}).on('changeDate',function(selected){
											var id=$(this).attr('id');
											var num=0;
											if(id.length>0)
												{
													num=id.indexOf('[');
													num++;
												}
											var value =id.charAt(num);
											if(selected.date!=null && selected.date!='' && selected.date!=undefined)
												$('#toDatePrevDP\\['+value+'\\]').datepicker('setStartDate',new Date(new Date(selected.date.valueOf()).getTime()+90*24*60*60*1000));
											else if(selected.date==undefined || selected.date!=null || selected.date!='' )
												{
													var srtDt=getStrtDtEndDt(value,'startDt');
													$('#toDatePrevDP\\['+value+'\\]').datepicker('setStartDate',srtDt);
												}
											
											$('#followUpFormOld'+value+'').formValidation('revalidateField','fromDatePrev['+value+']');
										});
										
										if(i==toStartFUS)
											{
												fromDtStrtDt=getStrtDtEndDt('addDate',i,'startDt');
												commEndDt=getStrtDtEndDt('addDate',i,'endDt');	
											}
										
										$('#toDatePrevDP\\['+i+'\\]').datepicker({
											startDate: fromDtStrtDt,
											endDate:   commEndDt,
											todayHighlight:true,
											format:'dd/mm/yyyy',
											autoclose:true,
											clearBtn:true
										}).on('changeDate',function(selected){
											var id=$(this).attr('id');
											var num=0;
											if(id.length>0)
												{
													num=id.indexOf('[');
													num++;
												}
											var value =id.charAt(num);
											$('#reviewDatePrevDP\\['+value+'\\]' ).datepicker( "remove" );
											$('#reviewDatePrev\\['+value+'\\]').val('');
											if(selected.date!=null && selected.date!='' && selected.date!=undefined)
												{
													$('#fromDatePrevDP\\['+value+'\\]').datepicker('setEndDate',new Date(new Date(selected.date.valueOf()).getTime()-90*24*60*60*1000));
													$('#reviewDatePrev\\['+value+'\\]').attr('readonly',false);
													$('#reviewDatePrevDP\\['+value+'\\]').datepicker({
														startDate:new Date(selected.date.valueOf()),
														endDate:new Date(new Date(selected.date.valueOf()).getTime()+365*24*60*60*1000),
														todayHighlight:true,
														autoclose:true,
														clearBtn:true,
														format:'dd/mm/yyyy'
													}).on('changeDate',function(e){
														var id=$(this).attr('id');
														var num=0;
														if(id.length>0)
															{
																num=id.indexOf('[');
																num++;
															}
														var value =id.charAt(num);
														$('#followUpFormOld'+value+'').formValidation('revalidateField','reviewDatePrev['+value+']');
													});
												}
											else if(selected.date==undefined || selected.date==null || selected.date=='' )
												{
													var endDt=getStrtDtEndDt(value,'endDt');
													$('#fromDatePrevDP\\['+value+'\\]').datepicker('setEndDate',endDt);
													$('#reviewDatePrev\\['+value+'\\]').attr('readonly',true);
												}
											
											$('#followUpFormOld'+value+'').formValidation('revalidateField','reviewDatePrev['+value+']');
											$('#followUpFormOld'+value+'').formValidation('revalidateField','toDatePrev['+value+']');
											
										});

										/* $('#reviewDatePrevDP\\['+i+'\\]').datepicker({
											startDate:fromDtStrtDt,
											endDate:commEndDt,
											todayHighlight:true,
											autoclose:true,
											clearBtn:true,
											format:'dd/mm/yyyy'
										}).on('changeDate',function(e){
											var id=$(this).attr('id');
											var num=0;
											if(id.length>0)
												{
													num=id.indexOf('[');
													num++;
												}
											var value =id.charAt(num);
											$('#followUpFormOld'+value+'').formValidation('revalidateField','reviewDatePrev['+value+']');
										}); */
									}
							}
					}
			}
		if(document.forms[0].switchOnDate!=null)
			{
				if(document.forms[0].switchOnDate.value==null || document.forms[0].switchOnDate.value =='')
					{
						$('#switchOnDateDP').datepicker({
							startDate: new Date(switchOnDt),
							endDate:   new Date(switchOnEndDt),
							todayHighlight:true,
							format:'dd/mm/yyyy',
							autoclose:true,
							clearBtn:true
						});
					}			
			}
		
		var fromDtStrtDt=new Date();
		var commEndDt=new Date();
		
		fromDtStrtDt=getStrtDtEndDt(null,count,'startDt');
		commEndDt=getStrtDtEndDt(null,count,'endDt');
			
		$('#fromDateDP').datepicker({
			startDate: fromDtStrtDt,
			endDate:commEndDt,
			todayHighlight:true,
			format:'dd/mm/yyyy',
			autoclose:true,
			clearBtn:true
		}).on('changeDate',function(selected){
			if(selected.date!=null && selected.date!='')
				$('#toDateDP').datepicker('setStartDate',new Date(new Date(selected.date.valueOf()).getTime()+90*24*60*60*1000));
			else
				{
					var newFromDtStrtDt=getStrtDtEndDt(count,'startDt');
					$('#toDateDP').datepicker('setStartDate',newFromDtStrtDt);
				}
											});
		
		fromDtStrtDt=getStrtDtEndDt('addDate',count,'startDt');
		commEndDt=getStrtDtEndDt('addDate',count,'endDt');
		
		$('#toDateDP').datepicker({
			startDate: fromDtStrtDt,
			endDate:commEndDt,
			todayHighlight:true,
			format:'dd/mm/yyyy',
			autoclose:true,
			clearBtn:true
		}).on('changeDate',function(selected){
			
			$('#reviewDateDP' ).datepicker( "remove" );
			$('#reviewDate').val('');
			if(selected.date!=null && selected.date!='')
				{
					$('#fromDateDP').datepicker('setEndDate',new Date(new Date(selected.date.valueOf()).getTime()-90*24*60*60*1000));
					$('#reviewDate').attr('readonly',false);
					 $('#reviewDateDP').datepicker({
							startDate: new Date(selected.date.valueOf()),
							endDate:new Date(new Date(selected.date.valueOf()).getTime()+364*24*60*60*1000),
							todayHighlight:true,
							format:'dd/mm/yyyy',
							autoclose:true,
							clearBtn:true
						});
				}	
			else
				{
					$('#reviewDate').attr('readonly',true);
					$('#fromDateDP').datepicker('setEndDate',getStrtDtEndDt(count,'endDt'));
				}
											});
		
		/* $('#reviewDateDP').datepicker({
			startDate: fromDtStrtDt,
			endDate:commEndDt,
			todayHighlight:true,
			format:'dd/mm/yyyy',
			autoclose:true,
			clearBtn:true
		}); */
	}
		$(document).ready(function()
				{
			$('[data-toggle="tooltip"]').tooltip();	
					
					$('#followUpFormPresent').find('[name="toDate"]').change(function (e){
						$('#followUpFormPresent').formValidation('revalidateField','toDate');	
					}).end();
					
					$('#followUpForm').find('[name="switchOnDate"]').change(function (e){
						$('#followUpForm').formValidation('revalidateField','switchOnDate');	
					}).end();
					
					$('#followUpFormPresent').find('[name="fromDate"]').change(function (e){
						$('#followUpFormPresent').formValidation('revalidateField','fromDate');	
					}).end();
					
					$('#followUpFormPresent').find('[name="reviewDate"]').change(function (e){
						$('#followUpFormPresent').formValidation('revalidateField','reviewDate');	
					}).end();
					
					
					
				$('#cochlearPresResetButton').on('click',function(e){
					e.preventDefault();
					return false;
				}); 
				$('#submitCochlearPresButton').on('click',function(e){
					e.preventDefault();
					return false;
				});
				$('#submitCochlearResetButton').on('click', function(e) {
			        e.preventDefault();
			        return false;
			    });
				$('#submitCochlearInitialButton').on('click', function(e) {
			        e.preventDefault();
			        return false;
			    });
					
					
					/* $('#followUpFormOld').formValidation({
								framework	: 'bootstrap',
								icon		:	{
													valid:'fa fa-check',
													invalid:'fa fa-times',
													validating:'fa fa-refresh'
												},
								excluded	: [':disabled'],
								fields   	: {
													fromDatePrev:{
																	 selector:'.fromDatePrev',
																	 validators: {
																		 	     notEmpty:{
																		 	    	 		message:'From Date is Mandatory'
														 	        				      }
													             				 }
									               				 },	
									               	toDatePrev  :{
										               				selector:'.toDatePrev',
										               				validators:{
										               							notEmpty:{
										               									  message:'To Date is Mandatory'
										               							         }
										               				           }
									               			     },
									               	childProgressRemarksPrev:{
										               							  selector:'.childProgressRemarksPrev',
										               							  validators:{
										               								  		  notEmpty:{
										               								  			  		message:'Progress of Child Remarks are Mandatory',
										               								  		  		   },
										               								  		  	regexp:  {
																				            	      regexp: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
																				            	      message:'Progress of Child Remarks cannot have special Characters other than .,-=&%/'
																			            			 },	   
										               							  			 }
									               						     },
									               	reviewDatePrev:{
										               					selector:'.reviewDatePrev',
										               					validators:{
										               								notEmpty:{
										               										  message:'Review Date by Cochlear Committee is Mandatory'
										               										 }		
										               							   }
									               				   },
									                audiologistNamePrev:{
																			selector:'.audiologistNamePrev',
																			validators:{
																						notEmpty:{
																			        		      message:'Audiologist Name is Mandatory'
																			                     },
																			             regexp: {         
																			            	 		regexp:/^[a-zA-Z ]+$/,
																			            	 		message:'Audiologist Name should have only alphabets'
																			            		 }       
																					   }
																	},					               						     
													               		
											  }
					}); */
					$('#followUpFormPresent').formValidation(
							{
								framework:'bootstrap'   ,
								excluded :[':disabled'] ,
								icon:   {
											valid: 'fa fa-check',
											invalid: 'fa fa-times',
											validating: 'fa fa-refresh'
										},
								fields:{
											fromDate:{
												validators:{
															notEmpty:{
												        		     	 message:'From Date is Mandatory'
												                     },
															date    :{
																		 format:'DD/MM/YYYY',
																		 message:'Enter Valid Date'
																	 }
														   }
													},
											toDate:{
														validators:{
																	notEmpty:{
														        		    	 message:'To Date is Mandatory'
														                     },
												                     date    :{
																				 format:'DD/MM/YYYY',
																				 message:'Enter Valid Date'
																			  }         
																   }
													},
											childProgressRemarks:{
																validators:{
																			notEmpty:{
																        		      message:'Progress of Child Remarks are Mandatory'
																                     },
														                     regexp:  {
															            	      regexp: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
															            	      message:'Progress of Child Remarks cannot have special Characters other than .,-=&%/'
														            			 },
																            stringLength:{
																            				max:3000,
																            				message:'Progress of Child Remarks can have 3000 Characters only'
																            			 },
																            callback	    :	{
																									message:'Progress of Child Remarks cannot have Consecutive Special Characters',
																									callback: function(value,validator,$field)
																										{
																											if(value.match(/(?=([\/.,\-=&%]{2}))/g))
																												return false;
																											else
																												return true;
																										}
																		  						},			 
															
																		   }
																},
											reviewDate:{
														validators:{
																	notEmpty:{
														        		      message:'Review Date by Cochlear Committee is Mandatory'
														                     },
																	date:	 {
																			  format:'DD/MM/YYYY',
																			  message:'Enter Valid date Format' 
																             }
																   }
														},		
											audiologistName:{
															validators:{
																		notEmpty:{
															        		      message:'Audiologist Name is Mandatory'
															                     },
															             regexp: {         
															            	 		regexp:/^[a-zA-Z .]+$/,
															            	 		message:'Audiologist Name should have only alphabets'
															            		 },
													                     stringLength:{
															            				max:50,
															            				message:'Audiologist Name can have 50 Characters only'
															            			 },
															             callback	    :	{
																								message:'Audiologist Name cannot have Consecutive Special Characters',
																								callback: function(value,validator,$field)
																									{
																										if(value.match(/(?=([.]{2}))/g))
																											return false;
																										else
																											return true;
																									}
																	  						},			 
																	   }
															},
								      }
							});			
					
					$('#followUpForm').formValidation(
						{
							framework:'bootstrap'   ,
							
							excluded :[':disabled'] ,
							icon:   {
										valid: 'fa fa-check',
										invalid: 'fa fa-times',
										validating: 'fa fa-refresh'
									},
							fields:{
									postOP:	{
												validators:{
															notEmpty:	
																	{
																	message:'Select any Option for Post OP Period'
																	}
														   }
											},
									postOPRemarks:{
													validators:{
																notEmpty:{
																		  message:'Post OP Period Remarks are Mandatory'
																		 },
																 regexp:  {
												            	      regexp: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
												            	      message:'Post OP Remarks cannot have special Characters other than .,-=&%/'
											            			 },
											                     stringLength:{
													            				max:3000,
													            				message:'Post OP Remarks Remarks can have 3000 Characters only'
													            			 },
													            callback	    :	{
																					message:'Post OP Remarks Remarks cannot have Consecutive Special Characters',
																					callback: function(value,validator,$field)
																						{
																							if(value.match(/(?=([\/.,\-=&%]{2}))/g))
																								return false;
																							else
																								return true;
																						}
														  						},
															   }
												  },
									woundSight:{
												validators:{
															notEmpty:{
																	 	  message:'Select any option for Wound Sight'
																	 }
														   }
											   },
									switchOnDate:{
												validators:{
													        notEmpty:{
													        			  message:'Switch On Date is Mandatory'
													                 },
											                 date    :{
																		 format:'DD/MM/YYYY',
																		 message:'Enter Valid Date'
																	  }
														   }
												 },
									audiologist:{
													validators:{
																	notEmpty:{
														        		      	message:'Audiologist Name is Mandatory'
														                     },
													             	regexp: {         
														            	 		regexp:/^[a-zA-Z .]+$/,
														            	 		message:'Audiologist Name should have only alphabets'
														            		 },
												                     stringLength:{
														            				max:50,
														            				message:'Audiologist Name can have 50 Characters only'
														            			 },
														             callback	    :	{
																						message:'Audiologist Name cannot have Consecutive Special Characters',
																						callback: function(value,validator,$field)
																							{
																								if(value.match(/(?=([.]{2}))/g))
																									return false;
																								else
																									return true;
																							}
															  						},
															   }
										},
								   }
						});
					
					/*
					 Start for creating Dynamical Validations
					*/
					
				var fromDatePrev   				={
													validators: {
														 	    	notEmpty: {
														 	    		 		 message:'From Date is Mandatory'
										 	        				      	  },
								 	        				      	date    :{
																				 format:'DD/MM/YYYY',
																				 message:'Enter Valid Date'
																			  }      	  
								         				 		}
									 			 };
				var toDatePrev					={	
						               				validators:{
						               								notEmpty:{
						               											 message:'To Date is Mandatory'
						               								         },
								 	        				      	date    :{
																				 format:'DD/MM/YYYY',
																				 message:'Enter Valid Date'
																			  }     						               								         
						               				           }
		           			     				 }		
               	var childProgressRemarksPrev    ={
													validators:{
															  		notEmpty:{
															  			  	     message:'Progress of Child Remarks are Mandatory',
															  		     	 },
													  		     	regexp:  {
														            	      regexp: /^[\na-zA-Z0-9 \/.,\-=&%]+$/,
														            	      message:'Progress of Child Remarks cannot have special Characters other than .,-=&%/'
													            			 },
												                    stringLength:{
														            				max:3000,
														            				message:'Progress of Child Remarks can have 3000 Characters only'
														            			 },
														            callback	    :	{
																						message:'Progress of Child Remarks cannot have Consecutive Special Characters',
																						callback: function(value,validator,$field)
																							{
																								if(value.match(/(?=([\/.,\-=&%]{2}))/g))
																									return false;
																								else
																									return true;
																							}
															  						},			 
													  			}
											     }
				var reviewDatePrev              ={
													validators:{
																	notEmpty:{
																			  message:'Review Date by Cochlear Committee is Mandatory'
																			 },
								 	        				      	date    :{
																				 format:'DD/MM/YYYY',
																				 message:'Enter Valid Date'
																			  }     		 
															   }
									   			 }
				var audiologistNamePrev         ={
													validators:{
																	notEmpty:{
														        		      message:'Audiologist Name is Mandatory'
														                     },
														             regexp: {         
														            	 		regexp:/^[a-zA-Z .]+$/,
														            	 		message:'Audiologist Name should have only alphabets'
														            		 },
												                     stringLength:{
														            				max:50,
														            				message:'Audiologist Name can have 50 Characters only'
														            			 },
														             callback	    :	{
																						message:'Audiologist Name cannot have Consecutive Special Characters',
																						callback: function(value,validator,$field)
																							{
																								if(value.match(/(?=([.]{2}))/g))
																									return false;
																								else
																									return true;
																							}
															  						},
															   }
						}
					
					
					var toStart='${toStartFUS}';
					var end='${cochlearCount-1}';
					
					for (var current=toStart;current<=end;current++)
						{
						
							$('#followUpFormOld'+current+'').formValidation(
								{
									framework:'bootstrap'   ,
									excluded :[':disabled'] ,
									icon:   {
												valid: 'fa fa-check',
												invalid: 'fa fa-times',
												validating: 'fa fa-refresh'
											},
									fields  :{
											}		
								});
							$('#submitCochlearPrevButton'+current+'').on('click',function(e){
								e.preventDefault();
								return false; 
							});

							$('#cochlearPrevResetButton'+current+'').on('click',function(e){
								e.preventDefault();
								return false; 
							});
						
							$('#followUpFormOld'+current+'').formValidation('addField','fromDatePrev['+current+']',fromDatePrev);
							$('#followUpFormOld'+current+'').formValidation('addField','toDatePrev['+current+']',toDatePrev);
							$('#followUpFormOld'+current+'').formValidation('addField','childProgressRemarksPrev['+current+']',childProgressRemarksPrev);
							$('#followUpFormOld'+current+'').formValidation('addField','reviewDatePrev['+current+']',reviewDatePrev);
							$('#followUpFormOld'+current+'').formValidation('addField','audiologistNamePrev['+current+']',audiologistNamePrev);
							
						}	
					/*
					 End for creating Dynamical Validations
					*/
				});
		
		function submitCochlear(form,value,id)
			{	
				
				var caseId='${caseId}';
				var toStartFUS='${toStartFUS}';
				var disableFields='${disableFields}';
				var formLocal=form;
				if(toStartFUS==null)
					toStartFUS=0;
				
				if(disableFields!=null)
					{
						if(value=='0' && disableFields=='Y')
							{
								document.getElementById('initialInitiatedFailAlert').style.display="";
								return false;
							}
					}
					
				
				if(form=='followUpFormPresent')
					{
						var coch='${cochlearCount}';
						if(coch==null || coch=='')
							coch='0';
						if(Number(toStartFUS)<Number(coch))
							{
								document.getElementById('formPresentFailAlert').style.display="";
								return false;
							}
					}
			
				var result=false;	
				if(form=='followUpFormOld')
					{
						formLocal=form+value;
						if(toStartFUS<value)					
							{
								document.getElementById('formOldFailAlert'+value+'').style.display="";
								return false;
							}	
					
						$('#'+formLocal+'').formValidation().formValidation('validate');
						result=$('#'+formLocal+'').data('formValidation').isValid();
					}
				else
					{
						formLocal=form;
						$('#'+formLocal+'').formValidation().formValidation('validate');
						result=$('#'+formLocal+'').data('formValidation').isValid();					
					}
				
				if(result)
					{
						document.getElementById(id).disabled=true;
						bootbox.confirm("Do you Want to Submit Cochlear FollowUp ?", function(result) {
							if(result==true)
								{
									var cochlearCount='${cochlearCount}';
									fn_loadingImage();
									document.getElementById(formLocal).action="followUpAction.do?actionFlag=initiateCochlearFollowUp&cochlearCount="+cochlearCount+"&caseId="+caseId+"&toStartFUS="+toStartFUS+"&currentValue="+value;
									document.getElementById(formLocal).method="post";
									document.getElementById(formLocal).submit();	
								}
							else if(result==false)
								{
									document.getElementById(id).disabled=false;
								}
						});
					}
			}
		function fn_checkMsg()
			{
				var returnMsg='${returnMsg}';
				var check='${close}';
				if(returnMsg!=null && returnMsg!='')
					{
						bootbox.alert(returnMsg, function() {
								if(check!=null)
									if(check=='Y')
										window.close();
												});
					}
				else
					{
						if(check!=null)
							if(check=='Y')
								window.close();
					}
				
				disableFields();
				fn_onloadDP();
			}
		function disableFields()
			{
				var disableFields='${disableFields}';
				if(disableFields!=null && disableFields!='')
					{
						if(disableFields=='Y')
							{
								if(document.forms[0].postOP!=null)
									{
										document.getElementById("uneventful").disabled=true;
										document.getElementById("complications").disabled=true;
									}
								if(document.forms[0].postOPRemarks!=null)
									document.forms[0].postOPRemarks.disabled=true;
								if(document.forms[0].woundSight!=null)
									{
										document.getElementById("healthy").disabled=true;
										document.getElementById("unhealthy").disabled=true;
									}
								if(document.forms[0].switchOnDate!=null)
									{
										$('#switchOnDateDP').datepicker('remove');
										document.forms[0].switchOnDate.disabled=true;
									}
								if(document.forms[0].audiologist!=null)
									document.forms[0].audiologist.disabled=true;
								
								document.getElementById("initialButtonsDiv").style.display="none";
								
							}
					}
			}
		
		function resetInitial(arg)
			{
				bootbox.confirm("Do you want to Reset all the Fields",function (result){
					if(result == true)
						{
							$('#'+arg+'').formValidation('resetForm', true);
						}
				});
			 	
			}
		function fn_close(id)
			{
				document.getElementById(id).style.display="none";
			}
	</script>
</form>	
</body>
</html>