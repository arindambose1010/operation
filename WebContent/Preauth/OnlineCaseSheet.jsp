 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<title>Case Sheet</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<c:if test="${forPrint eq '' || forPrint eq null}">
<%@ include file="/common/includePatientDetails.jsp"%>
</c:if>
<style type="text/css">
#imageID {
top: 30px;
}
body{font-size:12px !important}
</style>
<script>

function fn_Print()
{
	if('${forPrint}' == 'Y')
	{
		document.getElementById('printCaseSheetHdr').style.display="";
		if(document.getElementById('menuImage'))
			document.getElementById('menuImage').style.display="none";
		if(document.getElementById('topImage'))
			document.getElementById('topImage').style.display="none";
		document.getElementById('printTab').style.display="none";
		window.print();
		document.getElementById('printCaseSheetHdr').style.display="none";
		if(document.getElementById('menuImage'))
			document.getElementById('menuImage').style.display="";
		if(document.getElementById('topImage'))
			document.getElementById('topImage').style.display="";
		document.getElementById('printTab').style.display="";
	}
	else
	{
		url="/<%=context%>/preauthDetails.do?actionFlag=getOnlineCaseSheet&caseId=${caseId}&patientId=${patientId}&caseSheetFlag=Y&closeBut=Y&forPrint=Y";     
		window.open(url,"case_details",'toolbar=no,resizable=yes,scrollbars=yes,menubar=no,location=no,height=700,width=900,left=10,top=15');
	}
	
}
function fn_Close()
{
	window.close();
}
function fn_openAtachment(filepath)
{  
	var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&docSeqId="+filepath;
	childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function fn_openAtachment2(filepath,fileName)
{  
	
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath+"&fileName="+fileName;
    window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function fn_maxmizeTop()
{
parent.fn_maxmizeTop();
/*	var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.parent.caseApprovalFlag+'&errSearchType='+parent.parent.errSearchType+'&disSearchType='+parent.parent.disSearchType+'&module='+parent.parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
   document.forms[0].submit();*/
	}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}

</script>

<body align="center">
<form action="/preauthDetails.do" method="post" name="preauthDetailsForm">
<table width="100%" align="center" border="0" >
	
	<tr>
		<td>
			<c:if test="${caseSheetFlag ne 'Y'}">
				<table align="center" width="100%" border="0">
					<tr>
						<td colspan="6" style="height:340px;text-align:center;vertical-align:center;" class="labelheading1 tbcellCss">
							<b>Case Sheet can be viewed only after the case is discharge updated</b>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${caseSheetFlag eq 'Y'}">
			<c:if test="${forPrint eq '' || forPrint eq null}">
			<c:if test="${closeBut ne 'Y'}">
			<!-- Modal for patient details  -->  
<div class="modal fade" id="viewDtlsID"> 
 <div class="modal-dialog" id="modal-lgx">
   <div class="modal-content">
      <div class="modal-body">
	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  <iframe  id="complaintFrame" width="100%" height="280px" frameborder="no" scrolling="yes" > </iframe>
	  </div>
	  <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	  </div><!-- /.modal-content --> 
	</div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 
<div id="imageID"> 
 <a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
 <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
 <br>Patient Details
 </a>
</div></c:if></c:if>
				<table align="center" width="100%" border="0">
					<tr>
						<td colspan="6">
							<table id="printCaseSheetHdr" width="100%"  style="display:none">
								<tr><td colspan="3">&nbsp;</td></tr>
								<c:if test="${hospDtls.scheme ne 'CD202'}">
									<tr>
										<td width="25%" style="text-align:left;"><img border='0' src="images/aplogo_bw.png"/></td>
										<td width="50%" style="font-size:22px;text-align:center;"><span style="font-size:14px;">ANDHRA PRADESH STATE GOVERNMENT</span><br />EMPLOYEES HEALTH SCHEME</td>
										<td width="25%" style="text-align:right;">&nbsp;</td>
									</tr>
								</c:if>
								<c:if test="${hospDtls.scheme eq 'CD202'}">
									<tr>
										<td width="25%" style="text-align:left;"><img border='0' src="images/telangana-logo-blackwhite.png"/></td>
										<td width="50%" style="font-size:22px;text-align:center;"><span style="font-size:14px;">TELANGANA GOVERNMENT</span><br />EMPLOYEES HEALTH SCHEME</td>
										<td width="25%" style="text-align:right;">&nbsp;</td>
									</tr>
								</c:if>
								<tr><td colspan="3">&nbsp;</td></tr>
								<tr><td colspan="3" align="center" style="text-align:center">
									Aarogyasri Health Care Trust, D.No. 8-2-293/82a/ahct, Road No: 46, Jubilee HIlls,Hyderabad - 500033 <br />
									<!-- Contact : 040-23547107, FAX NO : 040-23555657 --> <!-- Chandana - 7265 - 06-02-2025 - Comenting the contact number -->
								</td></tr>
								
								
								<tr><td colspan="3">&nbsp;</td></tr>
								<tr><td colspan="3" align="center"><font size="3" style="STRONG"><b>Case Sheet</b></font></td></tr>
								<tr><td colspan="3">&nbsp;</td></tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center" class="tbheader print_heading">
						<table width="100%">
						<c:if test="${closeBut ne 'Y'}">
						<tr>
							<td width="3%">
							<img id="menuImage"
							src="images/rightLeftArrow.jpg" title="Maximize/Minimize"
							style="cursor: hand;" width="25" height="20" alt="Hide Menu"
							align="top" onclick="javascript:fn_maxmizeRight()"></img> </td>
							
							<td><FONT size="2" style="STRONG"><b>PATIENT DETAILS</b></FONT></td>
							<td width="3%">
							<!-- <img id="topImage"
							src="images/updownArrow.jpg" width="30" height="20"
							style="cursor: hand;" title="Maximize/Minimize" alt="Maximize"
							align="top" onclick="javascript:fn_maxmizeTop()"></img>-->
							<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
							</td>
						</tr>
						</c:if>
						<c:if test="${closeBut eq 'Y'}">
						<tr>
							<td><FONT size="2" style="STRONG"><b>PATIENT DETAILS</b></FONT></td>
						</tr>
						</c:if>
						</table>
						</td>
						
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td width="85%">
							<table>
								<tr>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="15%">
										<b>Health Card Number</b>
									</td>
									<td colspan="2" align="left" class="tbcellBorder print_cell">
										${PatientOpList.rationCard}
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell"  width="15%">
										<b>Patient Name</b>
									</td>
									<td colspan="2" align="left" width="35%" class="tbcellBorder print_cell">
										${PatientOpList.patientName}
									</td>
								</tr>
								<tr><td colspan="6">&nbsp;</td></tr>
								<tr>
									
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell"  width="10%">
										<b>Age</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell" width="10%">
										${PatientOpList.age}
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell"  width="15%">
										<b>Gender</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell" width="15%">
										${PatientOpList.gender}
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell"  width="15%">
										<b>Phone Number</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell" width="15%">
										${PatientOpList.contactNo}
									</td>
								</tr>
								<tr><td colspan="6">&nbsp;</td></tr>
								<tr>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="15%">
										<b>Address</b>
									</td>
									<td colspan="5" align="left" width="55%" class="tbcellBorder print_cell">
										${PatientOpList.houseNo}, ${PatientOpList.street}, ${PatientOpList.village}, ${PatientOpList.mandal}, ${PatientOpList.district}- ${PatientOpList.pincode}
									</td>
									
								</tr>
							</table>
						</td>
						<td width="15%">
							<table width="15%" border="0" align="left">
								<tr height="10"><td>&nbsp;</td></tr>
								<tr>
									<td width="100%" >
										<c:choose>
											<c:when test="${patOnBedPic != null && patOnBedPic != 'N'}" >
												<img src="Common/showDocument.jsp" width="110" height="100" alt="NO DATA"  id="patImage" 
												onmouseover="javascript:resizePatImage('patImage','140','110')" onmouseout="resizePatImage('patImage','110','100')" />
											</c:when>
											<c:otherwise>
												<img src="images/photonot.gif" width="110" height="100" alt="NO DATA" />
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							 
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%" border="0">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading print_heading">
							<FONT size="2" style="STRONG"><b>ADMISSION DETAILS</b></FONT>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="15%">
							<b>IP Number</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							${PatientOpList.patientIPNo}
						</td>
						<td colspan="1" align="left"  class="labelheading1 tbcellCss print_cell">
							<b>Admission Date</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							${hospDtls.ipRegDate}
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>Admission Type</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							<c:if test="${hospDtls.admissionRadio eq 'PLN'}">
								Planned
							</c:if>
							<c:if test="${hospDtls.admissionRadio eq 'EMG'}">
								Emergency
							</c:if>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="15%">
							<b>Medico Legal case</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell" width="10%">
						<c:if test="${hospDtls.legalCaseCheck eq 'Y'}">
							Yes
						</c:if>
						<c:if test="${hospDtls.legalCaseCheck eq 'N'}">
							No					</c:if>
						</td>
						<c:if test="${hospDtls.legalCaseCheck eq 'Y'}">
							<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="10%">
								<b>Case No</b>
							</td>
							<td colspan="1" align="left" class="tbcellBorder print_cell" width="20%">
								${hospDtls.legalCaseNo}
							</td>
							<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
								<b>Police Station</b>
							</td>
							<td colspan="1" align="left" class="tbcellBorder print_cell" width="25%">
								${hospDtls.policeStatName}
							</td>
						</c:if>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%" border="0">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading print_heading">
							<FONT size="2" style="STRONG"><b>HOSPITAL DETAILS</b></FONT>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>Hospital Name</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							${hospDtls.hospitalName}
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>Address & Contact </b>
						</td>
						<td colspan="3" align="left" class="tbcellBorder print_cell">
							${hospDtls.hospAddress} 
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>Doctor name</b>
						</td>
						<td colspan="5" align="left" class="tbcellBorder print_cell">
							${PatientOpList.docName}
						</td>
						<!--<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>Reg No</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							${hospDtls.docReg}
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="10%">
							<b>Qualification</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							${hospDtls.docQual}
						</td>-->
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<!--<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>Contact No</b>
						</td>
						<td colspan="5" align="left" class="tbcellBorder print_cell">
							${hospDtls.docMobNo}
						</td>
					</tr>-->
				</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>ADMISSION MEDICAL DETAILS</b></FONT>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
							<b>Complaints</b>
						</td>
						<td colspan="2" align="left" class="tbcellBorder print_cell" width="30%">
							${PatientOpList.complaintType} - ${PatientOpList.patComplaint}
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
							<b>History of Present Illness</b>
						</td>
						<td colspan="2" align="left" class="tbcellBorder print_cell" width="30%">
							${PatientOpList.presentIllness}
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
							<b>Known Allergies</b>
						</td>
						<td colspan="5" align="left" class="tbcellBorder print_cell" width="30%">
							<c:set  value="${PatientOpList.lstPerHis}" var="perHisList" />
							<c:if test="${fn:contains(perHisList, 'PR5.1') || fn:contains(perHisList, 'PR5.2')}">
								<c:if test="${fn:contains(perHisList, 'PR5.1')}">
									Yes
									<input type="checkbox" value="Y" disabled="disabled" checked="checked"/>&nbsp;&nbsp;
									No
									<input type="checkbox" value="N" disabled="disabled"/>
									<tr>
										<td colspan="5">
											<table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss print_cell" nowrap="nowrap" width="20%" align="left">
											 &nbsp;a.<b>Allergic to Medicine </b></td><td class="tbcellBorder print_cell" nowrap="nowrap"><input type="radio" name="AllMed" id="AllMed" value="AllMedYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
											 <td class="tbcellBorder print_cell" > <input type="radio" name="AllMed" id="AllMed" value="AllMedNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder print_cell">
												<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="4000"  title="Remark" style="width:70%" disabled="disabled"/></div>
												</td></tr>
												<tr><td class="labelheading1 tbcellCss print_cell" nowrap="nowrap" width="20%" align="left">
											 &nbsp;a.<b>Allergic to Substance other than medicine </b></td><td class="tbcellBorder print_cell" nowrap="nowrap"><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
											 <td class="tbcellBorder print_cell" > <input type="radio" name="AllSub" id="AllSub" value="AllSubNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder print_cell">
												<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="4000" title="Remark" style="width:70%" disabled="disabled"/></div>
												</td></tr></table>
										</td>
									</tr>
								</c:if>
								<c:if test="${fn:contains(perHisList, 'PR5.2')}">
									Yes
									<input type="checkbox" value="Y" disabled="disabled" />&nbsp;&nbsp;
									No
									<input type="checkbox" value="N" disabled="disabled" checked="checked"/>
									
								</c:if>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
							<b>Habits/Addictions</b>
						</td>
						<td colspan="5" align="left" class="tbcellBorder print_cell" width="30%">
							<c:set  value="${PatientOpList.lstPerHis}" var="perHisList" />
							<c:if test="${fn:contains(perHisList, 'PR6.1') || fn:contains(perHisList, 'PR6.2')}">
								<c:if test="${fn:contains(perHisList, 'PR6.1')}">
									Yes
									<input type="checkbox" value="Y" disabled="disabled" checked="checked"/>&nbsp;&nbsp;
									No
									<input type="checkbox" value="N" disabled="disabled"/>
									<table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss print_cell" nowrap="nowrap" width="20%">
									 &nbsp;a.<b>Alcohol  </b></td><td class="tbcellBorder print_cell" nowrap="nowrap"><input type="radio" name="alcohol" id="alcohol" value="Regular" disabled="disabled"/> &nbsp;Regular&nbsp;</td>
									 <td class="tbcellBorder print_cell" > <input type="radio" name="alcohol" id="alcohol" value="Occasional" disabled="disabled"/> &nbsp;Occasional&nbsp;</td>
									 <td class="tbcellBorder print_cell"> <input type="radio" name="alcohol" id="alcohol" value="Teetotaler" disabled="disabled"/> &nbsp;Teetotaler&nbsp; </td></tr>
										<tr><td class="labelheading1 tbcellCss print_cell"> &nbsp;b.<b>Tobacco  </b></td><td class="tbcellBorder print_cell"><input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Snuff</td>
									  <td class="tbcellBorder print_cell"><input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Chewable&nbsp;</td>
									 <td class="tbcellBorder print_cell" > <input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Smoking&nbsp;
									  <span id="smokingTd" style="display:none" >
									  &nbsp;Pack  <input  class="tbcellBorder print_cell" type="text" name="packNo" id="packNo" style="width:40px" maxlength="3" title="Smoking Pack No" disabled="disabled"/>
									   &nbsp;Years  <input class="tbcellBorder print_cell" type="text" name="smokeYears" id="smokeYears" style="width:40px" maxlength="3" title="Smoking Years" disabled="disabled"/>
									 </span>
									 </td>
									  </tr>
									  <tr><td class="labelheading1 tbcellCss print_cell"> &nbsp;c.<b>Drug Use  </b></td><td class="tbcellBorder print_cell"><input type="radio" name="drugUse" id="drugUse" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
									  <td class="tbcellBorder print_cell"><input type="radio" name="drugUse" id="drugUse" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
									  <tr><td class="labelheading1 tbcellCss print_cell"> &nbsp;d.<b>Betel nut  </b></td><td class="tbcellBorder print_cell"><input type="radio" name="betelNut" id="betelNut" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
									 <td class="tbcellBorder print_cell"> <input type="radio" name="betelNut" id="betelNut" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
									  <tr><td class="labelheading1 tbcellCss print_cell"> &nbsp;e.<b>Betel leaf  </b></td><td class="tbcellBorder print_cell"><input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
									  <td class="tbcellBorder print_cell"><input type="radio" name="betelLeaf" id="betelLeaf" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr></table>
								</c:if>
								<c:if test="${fn:contains(perHisList, 'PR6.2')}">
									Yes
									<input type="checkbox" value="Y" disabled="disabled" />&nbsp;&nbsp;
									No
									<input type="checkbox" value="N" disabled="disabled" checked="checked"/>
									
								</c:if>
							</c:if>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
							<b>Past Illness History</b>
						</td>
						<td colspan="2" align="left" class="tbcellBorder print_cell" width="30%">
						<table border="0" width="100%" align="left">
							<tr><td>
							<c:set var="loopCount1" value="0" />
							<c:forEach  items="${pastHistoryList}" varStatus="loop">								
								<c:set value="${pastHistoryList[loop.index].ID}" var="sample"></c:set>	
								<c:forTokens var="tokenName" items="${PatientOpList.pastIllness}" delims="~" varStatus="status" begin="0">
									<c:choose>
										<c:when test="${tokenName == sample}">

										<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
											
											<c:if test="${loopCount1 eq 1}">
												<c:out value="${pastHistoryList[loop.index].VALUE}"/>
											</c:if>	
											<c:if test="${loopCount1 > 1}">
												<c:out value=", ${pastHistoryList[loop.index].VALUE}"/>
											</c:if>	
											<c:if test="${tokenName == 'PH11' }">
												${PatientOpList.pastIllenesOthr}
										   </c:if>
										   <c:if test="${tokenName == 'PH8' }">
											( ${PatientOpList.pastIllenesCancers})
										   </c:if>
										   <c:if test="${tokenName == 'PH10' }">
											( ${PatientOpList.pastIllenesSurg})
										   </c:if>
										   <c:if test="${tokenName == 'PH12' }">
											( ${PatientOpList.pastIllenesEndDis})
										   </c:if>
										</c:when>
									</c:choose>  
								</c:forTokens>     	
							</c:forEach>
							</td></tr>
							<c:if test="${fn:length(PatientOpList.pastIllness) eq 0}">
								<tr><td align="center">History of past illness not found</td></tr>
							</c:if>
						</table>
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
							<b>Family History</b>
						</td>
						<td colspan="2" align="left" class="tbcellBorder print_cell" width="30%">
							<table border="0" width="100%" align=center >
							<tr><td align="left">
							<c:set var="loopCount2" value="0" />
							<c:forEach  items="${familyHistoryList}" varStatus="loop">								
								<c:set value="${familyHistoryList[loop.index].ID}" var="sample"></c:set>	
								<c:forTokens var="tokenName" items="${PatientOpList.familyHis}" delims="~" varStatus="status" begin="0">
									<c:choose>
									<c:when test="${tokenName == sample}">	
									<c:set var="loopCount2" value="${loopCount2 + 1}" /> 
									<c:if test="${loopCount2 eq 1}">
										<c:out value="${familyHistoryList[loop.index].VALUE}"/>
									</c:if>
									<c:if test="${loopCount2 > 1}">
										<c:out value=", ${familyHistoryList[loop.index].VALUE}"/>
									</c:if>
										<c:if test="${tokenName == 'FH11' }">
										${PatientOpList.familyHistoryOthr}
										</c:if>
									</c:when>
									</c:choose>  
								</c:forTokens>     	
							</c:forEach>
							</td></tr>
							<tr><td align="center">
							<c:if test="${fn:length(PatientOpList.familyHis) eq 0 }">
							Family history not found
							</c:if>
							</td></tr>
							</table>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
					<tr>
						<td colspan="6" align="center" class="labelheading1 tbcellCss print_cell">
							<b>General Examination Findings</b>
						
						</td>
					</tr>
					<tr><td colspan="6" class="tbcellBorder print_cell">
						<table border="0" width="100%" align="center">
							 <tr>
							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Height</b></td>
							 <td width="30%" align="left" class="tbcellBorder print_cell">${PatientOpList.height}</td>
							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Weight</b></td>
							 <td width="30%" align="left" class="tbcellBorder print_cell">${PatientOpList.weight}</td>
							</tr>
							<tr>
							<td class="labelheading1 tbcellCss print_cell" width="20%" align="left"> <b>BMI</b></td>
							 <td width="30%" align="left" class="tbcellBorder print_cell">${PatientOpList.bmi}</td>
							  <td class="labelheading1 tbcellCss print_cell" width="10%" align="left"><b>Pallor</b></td>
							 <td align="left" class="tbcellBorder print_cell">
							 <c:choose>
								 <c:when test="${PatientOpList.pallor=='Y'}">
								  <input type="checkbox" name="PallorChkBox" value="Y" checked="checked" disabled="disabled">Yes
								 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
								 </c:when>
								  <c:when test="${PatientOpList.pallor=='N'}">
								  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="PallorChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>
							</tr>

							<tr>
							 
							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Cyanosis</b></td>
							 <td width="30%" align="left" class="tbcellBorder print_cell">
							  <c:choose>
								 <c:when test="${PatientOpList.cyanosis=='Y'}">
								 <input type="checkbox" name="CyanosisChkBox" value="Y" checked="checked" disabled="disabled">Yes
								 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled"> No
								 </c:when>
								 <c:when test="${PatientOpList.cyanosis=='N'}">
								 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="CyanosisChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>
							 
							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Clubbing of Fingers/Toes</b></td>
							  <td align="left" width="30%" class="tbcellBorder print_cell">
							  <c:choose>
								  <c:when test="${PatientOpList.clubbingOfFingers=='Y'}">
								 <input type="checkbox" name="ClubbingChkBox" value="Y" checked="checked" disabled="disabled">Yes
								 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled" >No
								 </c:when>
								   <c:when test="${PatientOpList.clubbingOfFingers=='N'}">
								 <input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="ClubbingChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								 <input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>
							 
							</tr>

							<tr>
							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Lymphadenopathy</b></td>
							 <td align="left" width="30%" class="tbcellBorder print_cell">
							  <c:choose>
								  <c:when test="${PatientOpList.lymphadenopathy=='Y'}">
								 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" checked="checked" disabled="disabled">Yes
								 <input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
								 </c:when>
								 <c:when test="${PatientOpList.lymphadenopathy=='N'}">
								 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="LymphadenopathyChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>

							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Edema of Feet</b> </td>
							  <td align="left" width="30%" class="tbcellBorder print_cell">
							  <c:choose>
								  <c:when test="${PatientOpList.edema=='Y'}">
								 <input type="checkbox" name="EdemaChkBox" value="Y" checked="checked" disabled="disabled">Yes
								 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
								 </c:when>
								 <c:when test="${PatientOpList.edema=='N'}">
								 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="EdemaChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>
							  </tr>
							 <tr>
							 
							 <td class="labelheading1 tbcellCss print_cell" width="12%" align="left"><b>Malnutrition</b></td>
							 <td align="left" class="tbcellBorder print_cell">
							 <c:choose>
								 <c:when  test="${PatientOpList.malNutrition=='Y'}">
								 <input type="checkbox" name="MalnutritionChkBox" value="Y" checked="checked" disabled="disabled">Yes
								 <input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
								 </c:when>
								 <c:when test="${PatientOpList.malNutrition=='N'}">
								 <input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="MalnutritionChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								  <input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>
							 

							 <td class="labelheading1 tbcellCss print_cell" width="20%" align="left"><b>Dehydration</b></td>
							 <td align="left" class="tbcellBorder print_cell">
							 <c:choose>
								 <c:when  test="${PatientOpList.dehydration=='Y'}">
									 <input type="checkbox" name="DehydrationChkBox" value="Y" checked="checked" disabled="disabled">Yes (${ PatientOpList.dehydrationType})
									 <input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
									
								 </c:when>
								 
								 <c:when test="${PatientOpList.dehydration=='N'}">
								 <input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="DehydrationChkBox" value="N" checked="checked" disabled="disabled">No
								 </c:when>
								 <c:otherwise>
								  <input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
								 <input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
								 </c:otherwise>
							 </c:choose>
							 </td>
							 </tr>

							<tr>
							 <td class="labelheading1 tbcellCss print_cell" width="12%" align="left"><b>Temperature</b> </td>
							 <td align="left" class="tbcellBorder print_cell">${PatientOpList.temperature}</td> 

							 <td class="labelheading1 tbcellCss print_cell" width="12%" align="left"><b>Pulse Rate per Minute</b></td>
							 <td align="left" class="tbcellBorder print_cell">${PatientOpList.pulseRate}</td> 
							 </tr>
							 <tr>
							 <td class="labelheading1 tbcellCss print_cell" width="10%" align="left"><b>Respiration Rate</b></td>
							 <td align="left" class="tbcellBorder print_cell">${PatientOpList.respirationRate}</td>

							 <td class="labelheading1 tbcellCss print_cell" width="12%" align="left"><b>BP Lt. Arm</b> </td>
							 <td align="left" class="tbcellBorder print_cell">${PatientOpList.bpLmt}</td>
							  </tr>
							<tr>
							 <td class="labelheading1 tbcellCss print_cell" width="12%" align="left"><b>BP Rt. Arm</b></td>
							 <td align="left" class="tbcellBorder print_cell">${PatientOpList.bpRmt}</td>
							</tr>

							 <tr>
							 <td>&nbsp;</td>
							 
							</tr>
						</table>
					</td>
					</tr>
					<tr>
						<td colspan="6" align="center" class="labelheading1 tbcellCss print_cell">
							<b>Systematic Examination Findings</b>
						
						</td>
					</tr>
					<tr><td colspan="6">
						<table id="sympTab" border="0" width="100%" align="center" class="tbcellBorder print_cell">
							<c:if test="${fn:length(symptomsList) > 0}">
								<tr>  
										<td width="5%" class="labelheading1 tbcellCss print_cell"><b>S.No</b></td>        
										<td width="35%" class="labelheading1 tbcellCss print_cell"><b>Main Symptom Name</b></td>   
										<td width="30%" class="labelheading1 tbcellCss print_cell"><b>Sub Symptom Name</b></td>
										<td width="30%" class="labelheading1 tbcellCss print_cell"><b>Symptom Name</b></td>
								 </tr>
							</c:if>
							<%int i=1; %>
							<c:forEach items="${symptomsList}" var="element"> 
								  <tr>
									<td width="5%" class="tbcellBorder print_cell" ><%=i++ %></td>
									<td width="35%" class="tbcellBorder print_cell" >${element.ID}</td>
									<td width="30%" class="tbcellBorder print_cell" >${element.SUBID}</td>
									<td width="30%" class="tbcellBorder print_cell" >${element.VALUE}</td>
								  </tr>
							</c:forEach>  
							<c:if test="${fn:length(symptomsList) eq 0}">
									<tr>
										<td colspan="4" align="center">
											<b>Systematic Examination Findings not found</b>
										</td>
									</tr>
							</c:if>
						</table>
					</td>
					</tr>
					<tr>
						<td colspan="6" align="center" class="labelheading1 tbcellCss print_cell">
							<b>General Investigations Done and Reports</b>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<table border="0" width="100%" align="center" class="tbcellBorder print_cell">
							
								<tr>
									<td colspan="2" width="33%" class="labelheading1 tbcellCss print_cell"><b>Investigation Block Name</b></td>       
									<td colspan="2" width="33%" class="labelheading1 tbcellCss print_cell"><b>Test Name</b></td>   
									<td colspan="2" width="33%" class="labelheading1 tbcellCss print_cell"><b>Attachments</b></td>
								</tr>
								<c:if test="${fn:length(genInvestList) >0 }">
									<c:forEach items="${genInvestList}" var="genInv">
									<tr>
										<td colspan="2" width="33%" class="tbcellBorder print_cell">${genInv.ID}</td>
										<td colspan="2" width="33%" class="tbcellBorder print_cell">${genInv.VALUE}</td>
										<td colspan="2" width="33%" class="tbcellBorder print_cell"><a href="#" onclick="javascript:fn_openAtachment2('${genInv.LVL}','${genInv.LVL}');">View</a></td>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${fn:length(genInvestList) eq 0 }">
									<tr>
										<td colspan="6" align="center">
											<b>General Investigation Reports not found for this case</b>
										</td>
									</tr>
								</c:if>
							</table>
						</td>
					</tr>
					</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>FINAL DIAGNOSIS</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="labelheading1 tbcellCss print_cell"><b>Diagnosis Type</b></td><td align="left" class="tbcellBorder print_cell">${hospDtls.diagnosisType}</td>
						<td class="labelheading1 tbcellCss print_cell"><b>Main Category Name</b></td><td align="left" class="tbcellBorder print_cell">${hospDtls.mainCatName}</td>
						<td class="labelheading1 tbcellCss print_cell"><b>Category Name</b></td><td align="left" class="tbcellBorder print_cell">${hospDtls.catName}</td>
						</tr>
						<tr><td class="labelheading1 tbcellCss print_cell"><b>Sub Category Name</b></td><td align="left" class="tbcellBorder print_cell">${hospDtls.subCatName}</td>
						<td class="labelheading1 tbcellCss print_cell"><b>Disease Name</b></td><td align="left" class="tbcellBorder print_cell">${hospDtls.disName}</td>
						<td class="labelheading1 tbcellCss print_cell"><b>Disease Anatomical Name</b></td><td align="left" class="tbcellBorder print_cell">${hospDtls.disAnatomicalSitename}</td>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>THERAPY/SURGERY DETAILS</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell">
							<table border="0" width="100%"  cellpadding="1" cellspacing="1" align="center"   style="table-layout: fixed;">

								<tr>
								<td width="10%" style="word-wrap:break-word;" class="labelheading1 tbcellCss print_cell"><b>Category Name</b></td>
								<td width="13%" class="labelheading1 tbcellCss print_cell"><b>ICD Category Name</b></td>
								<td width="12%" class="labelheading1 tbcellCss print_cell"><b>Procedure Name</b></td>
								<td width="12%" class="labelheading1 tbcellCss print_cell"><b>Units</b></td>
								<td width="20%" class="labelheading1 tbcellCss print_cell" ><b>Special Investigations</b> </td>
								<td width="25%" class="labelheading1 tbcellCss print_cell"><b>Remarks</b></td>
								<td width="15%" class="labelheading1 tbcellCss print_cell"><b>Treating Doctor</b></td>
								</tr>
								<c:if test="${fn:length(SurgDtls) > 0}">
									<c:set var="procNamesList" value="" />
									<c:set var="loopCount1" value="0" />
									<c:forEach items="${SurgDtls}" var="results"> 
									<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
										<tr id="splInvetsDataTable${loopCount1}" style="display:true">
										<td style="word-wrap:break-word;" class="tbcellBorder print_cell">${results.asriCatName} (${results.catId})</td>
										<td style="word-wrap:break-word;" class="tbcellBorder print_cell">${results.catName} (${results.icdCatCode})</td>
										<td style="word-wrap:break-word;" class="tbcellBorder print_cell">${results.procName} (${results.icdProcCode})</td>
										<td style="word-wrap:break-word;" class="tbcellBorder print_cell">
											<c:choose>
												<c:when test="${results.opProcUnits eq null || results.opProcUnits eq '-1' || results.opProcUnits eq ''}">
													-NA-
												</c:when>
												<c:otherwise>
													${results.opProcUnits} 
												</c:otherwise>
											</c:choose>
										</td>
										<c:if test="${procNamesList ne ''}">
											<c:set var="procNamesList" value="${procNamesList} , ${results.procName}" />
										</c:if>
										<c:if test="${procNamesList eq ''}">
											<c:set var="procNamesList" value="${results.procName}" />
										</c:if>
										<c:set var="splInvstCount" value="1" />
										<c:if test="${fn:length(results.lstSplInvet) > 0}">
										<td    style="word-wrap:break-word;" class="tbcellBorder print_cell">
										<c:forEach items="${results.lstSplInvet}" var="results1"> 
											<a href="javascript:fn_openAtachment(${results1.filePath})" > ${results1.filename}</a> 
											<c:if test="${fn:length(results.lstSplInvet) ne splInvstCount}">
											,
											</c:if>
											<c:set var="splInvstCount" value="${splInvstCount+1 }" />
										
										</c:forEach>
										</td>
										</c:if>
										<c:if test="${fn:length(results.lstSplInvet) eq 0}">
											<td  style="word-wrap:break-word;" class="tbcellBorder print_cell" align="center"> -- </td>
										</c:if>
										<c:choose>
										<c:when test="${results.investRemarks eq null || results.investRemarks eq ''}">
											<td  style="word-wrap:break-word;" class="tbcellBorder print_cell" align="center"> -- </td>
										</c:when>
										<c:otherwise>
											<td  style="word-wrap:break-word;" class="tbcellBorder print_cell" >&nbsp;&nbsp;${results.investRemarks}</td>
										</c:otherwise>
										</c:choose>
										<c:choose>
										<c:when test="${results.docName eq null || results.docName eq ''}">
											<td  style="word-wrap:break-word;" class="tbcellBorder print_cell" align="center"> -- </td>
										</c:when>
										<c:otherwise>
											<td  style="word-wrap:break-word;" class="tbcellBorder print_cell" >&nbsp;&nbsp;${results.docName}</td>
										</c:otherwise>
										</c:choose>
										
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>CLINICAL NOTES</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell">
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td>
										<c:if test="${fn:length(preClinicalNotes) > 0}">
										<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
											align="center" class="tabBorder">
											<tr>
												<td width="4%" class="labelheading1 tbcellCss print_cell">SNo</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Clinical ID</td>
												<td width="11%" class="labelheading1 tbcellCss print_cell">Date&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">BP</td>
												<td width="5%" class="labelheading1 tbcellCss print_cell">Pulse</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Temperature&nbsp;</td>
												<td width="9%" class="labelheading1 tbcellCss print_cell">Respiratory
													Rate&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Heart
													Sounds&nbsp;</td>
												<td width="7%" class="labelheading1 tbcellCss print_cell">Lungs&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Fluid
													Input&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Fluid
													Output&nbsp;</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Ward
													Type&nbsp;</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Daily
													Progress Notes&nbsp;</td>
												
											</tr>
											<c:forEach  items="${preClinicalNotes}"varStatus="loop" >
											<tr>
												<td class="tbcellBorder print_cell">${loop.count}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].CLINICALID}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].investGnDate}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].BLOODPRESSURE}</td>
												<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].PULSE}</td>
												<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].TEMPERATURE}</td>
												<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].RESPIRATORY}</td>
												<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].HEART_RATE}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].LUNGS}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].FLUIDINPT}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].FLUIDOUTPUT}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].WARD_TYPE}</td>
												<td class="tbcellBorder print_cell">${preClinicalNotes[loop.count-1].REMARKS}</td>
											</tr>
											</c:forEach>
										</table>
										</c:if>
										<c:if test="${fn:length(preClinicalNotes) eq 0}">
											<b><center>Clinical Notes not found for this case</center></b>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>COMORBIDITIES</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell" colspan="6">
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td colspan="2">
										<c:forEach items="${hospDtls.comorbidValues}" var="com" >
										<c:set var="comCount1" value="${comCount1 + 1}" /> 
										<c:if test="${(comCount1 % 2) eq 1}">
										<tr>
										</c:if>	
											<td  align="left" class="tbcellCss print_cell">
													&nbsp;${comCount1}. &nbsp;${com.ID} (${com.CONST})
											</td>
										<c:if test="${(comCount1 % 2) eq 1}">
										</tr>
										</c:if>	
										</c:forEach>
										<c:if test="${fn:length(hospDtls.comorbidValues) eq 0}">
											<b><center>No comorbidities added for this case</center></b>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				
				<table align="center" width="100%" border="0">
					<tr>
						<td colspan="4" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>OPERATION RECORD</b></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="25%">
							<b>vii. Surgeon Name & Reg No </b>
						</td>
						<td colspan="3" align="left" class="tbcellBorder print_cell">
							<c:if test="${caseType eq 'medical'}">
								${clinicalNotes.treatSurgeonName}, ${clinicalNotes.treatSurgeonRegNo}
							</c:if>
							<c:if test="${caseType eq 'surgical'}">
								${clinicalNotes.SURGEON_NAME}, ${clinicalNotes.SURGEON_REGNO}
							</c:if>
							<c:if test="${caseType eq null || caseType eq ''}">
								-NA-
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="25%">
							<b>x. Procedure Consent</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell" width="25%">
							<c:if test="${hospDtls.procedureConsent eq null || hospDtls.procedureConsent eq ''}">
								-NA-
							</c:if>
							<c:if test="${hospDtls.procedureConsent ne null && hospDtls.procedureConsent ne ''}">
								${hospDtls.procedureConsent}
							</c:if>
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="25%" >
							<b>xi. Blood Transfusion</b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell" width="25%">
							<c:if test="${hospDtls.bloodTransfusion eq null || hospDtls.bloodTransfusion eq ''}">
								-NA-
							</c:if>
							<c:if test="${hospDtls.procedureConsent ne null && hospDtls.procedureConsent ne ''}">
								${hospDtls.bloodTransfusion}
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>xii. Medical or cardiology Clearance</b>
						</td>
						<td colspan="3" align="left" class="tbcellBorder print_cell">
							<c:if test="${hospDtls.medCardioClearence eq null || hospDtls.medCardioClearence eq ''}">
								-NA-
							</c:if>
							<c:if test="${hospDtls.medCardioClearence ne null && hospDtls.medCardioClearence ne ''}">
								${hospDtls.medCardioClearence}
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>xiii. Anaesthesiologist Name & Reg No </b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							<c:if test="${clinicalNotes.ANESTNAME eq null || clinicalNotes.ANESTNAME eq ''}">
								-NA-
							</c:if>
							<c:if test="${clinicalNotes.ANESTNAME ne null && clinicalNotes.ANESTNAME ne ''}">
								${clinicalNotes.ANESTNAME}, ${clinicalNotes.ANESTREGNO}
							</c:if>
						</td>
						<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
							<b>xiv. Anaesthesia Type </b>
						</td>
						<td colspan="1" align="left" class="tbcellBorder print_cell">
							<c:if test="${clinicalNotes.anesthesiaType eq null || clinicalNotes.anesthesiaType eq ''}">
								-NA-
							</c:if>
							<c:if test="${clinicalNotes.anesthesiaType ne null && clinicalNotes.anesthesiaType ne ''}">
								${clinicalNotes.anesthesiaType}
							</c:if>
							
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4" class="labelheading1 tbcellCss print_cell" align="center">
							<b>xv. Procedure Details with specific Intra OP findings</b>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table align="center" width="100%" border="0">
								<tr>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="12%">
										<b>a. Incision Type</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell" width="21%">
										<c:if test="${clinicalNotes.incisionType eq null || clinicalNotes.incisionType eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.incisionType ne null && clinicalNotes.incisionType ne ''}">
											${clinicalNotes.incisionType}
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="12%">
										<b>b. Intra OP photos / WebEx taken</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell" width="21%">
										<c:if test="${clinicalNotes.intraOpPotos eq null || clinicalNotes.intraOpPotos eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.intraOpPotos ne null && clinicalNotes.intraOpPotos ne ''}">
											${clinicalNotes.intraOpPotos}
										</c:if> &nbsp;&nbsp; 
										<c:if test="${clinicalNotes.intraOpPotos eq 'Yes'}">
											<a href="javascript:fn_openAtachment2('${intraOpAttachPath}','${intraOpAttachName}')" > View Attachment</a> 
										</c:if>
										
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="12%">
										<b>c. Video recording done-Yes/No</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell" width="22%">
										<c:if test="${clinicalNotes.videoRec eq null || clinicalNotes.videoRec eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.videoRec ne null && clinicalNotes.videoRec ne ''}">
											${clinicalNotes.videoRec}
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
										<b>d. Swab count and Instruments count</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.swabCount eq null || clinicalNotes.swabCount eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.swabCount ne null && clinicalNotes.swabCount ne ''}">
											${clinicalNotes.swabCount}
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" >
										<b>e. Sutures Ligatures</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.sutureLigature eq null || clinicalNotes.sutureLigature eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.sutureLigature ne null && clinicalNotes.sutureLigature ne ''}">
											${clinicalNotes.sutureLigature}
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
										<b>f. Specimen removed</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.specimenRem eq null || clinicalNotes.specimenRem eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.specimenRem ne null && clinicalNotes.specimenRem ne ''}">
											${clinicalNotes.specimenRem}
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
										<b>g. Drainage count</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.drainageCnt eq null || clinicalNotes.drainageCnt eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.drainageCnt ne null && clinicalNotes.drainageCnt ne ''}">
											${clinicalNotes.drainageCnt}
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" >
										<b>h. Blood Loss</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.bloodLosss eq null || clinicalNotes.bloodLosss eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.bloodLosss ne null && clinicalNotes.bloodLosss ne ''}">
											${clinicalNotes.bloodLosss}	
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
										<b>i. Complications</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.complications eq null || clinicalNotes.complications eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.complications ne null && clinicalNotes.complications ne ''}">
											${clinicalNotes.complications}	
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
										<b>j. Surgery starting time</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${surgStartTime eq null || surgStartTime eq ''}">
											-NA-
										</c:if>
										<c:if test="${surgStartTime ne null && surgStartTime ne ''}">
											${surgStartTime}	
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" >
										<b>k. Surgery ending time</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${surgEndTime eq null || surgEndTime eq ''}">
											-NA-
										</c:if>
										<c:if test="${surgEndTime ne null && surgEndTime ne ''}">
											${surgEndTime}	
										</c:if>
									</td>
									<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell">
										<b>l. Post-operative instructions</b>
									</td>
									<td colspan="1" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.postOperativeInst eq null || clinicalNotes.postOperativeInst eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.postOperativeInst ne null && clinicalNotes.postOperativeInst ne ''}">
											${clinicalNotes.postOperativeInst}
										</c:if>	
									</td>
								</tr>
								<tr>
									<td colspan="3" align="left" class="labelheading1 tbcellCss print_cell">
										<b>m. Condition of the patient at the time of shifting</b>
									</td>
									<td colspan="3" align="left" class="tbcellBorder print_cell">
										<c:if test="${clinicalNotes.conditiOfPat eq null || clinicalNotes.conditiOfPat eq ''}">
											-NA-
										</c:if>
										<c:if test="${clinicalNotes.conditiOfPat ne null && clinicalNotes.conditiOfPat ne ''}">
											${clinicalNotes.conditiOfPat}	
										</c:if>	
									</td>
									
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>POST CLINICAL NOTES</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell" colspan="6" >
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td>
										<c:if test="${fn:length(postClinicalNotes) > 0}">
										<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
											align="center" class="tabBorder">
											<tr>
												<td width="4%" class="labelheading1 tbcellCss print_cell">SNo</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Clinical ID</td>
												<td width="11%" class="labelheading1 tbcellCss print_cell">Date&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">BP</td>
												<td width="5%" class="labelheading1 tbcellCss print_cell">Pulse</td>
												<td width="7%" class="labelheading1 tbcellCss print_cell">Temperature&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Respiratory
													Rate&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Heart
													Sounds&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Lungs&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Fluid
													Input&nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Fluid
													Output&nbsp;</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Ward
													Type&nbsp;</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Daily
													Progress Notes&nbsp;</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">&nbsp;Wound Details</td>
											</tr>

											<c:forEach  items="${postClinicalNotes}" varStatus="loop2">
												<tr>
													<td class="tbcellBorder print_cell">${loop2.count}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop2.count-1].CLINICALID}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].investGnDate}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].BLOODPRESSURE}</td>
													<td align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].PULSE}</td>
													<td align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].TEMPERATURE}</td>
													<td align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].RESPIRATORY}</td>
													<td align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].HEART_RATE}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].LUNGS}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].FLUIDINPT}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].FLUIDOUTPUT}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].WARD_TYPE}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].REMARKS}</td>
													<td class="tbcellBorder print_cell">${postClinicalNotes[loop2.count-1].WOUND_DTLS}</td>
												</tr>
											</c:forEach>
										</table>
										</c:if>
										<c:if test="${fn:length(postClinicalNotes) eq 0}">
											<b><center>Post Clinical Notes not found for this case</center></b>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>IP MEDICATION CHART</b></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="left" class="labelheading1 tbcellCss print_cell" >
							<FONT size="2" style="STRONG" ><b>CLINICAL NOTES DRUGS LIST</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell" colspan="6" >
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td>
										<c:if test="${fn:length(drugsLst) > 0}">
										<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
											align="center" class="tabBorder">
											<tr>  
												<td width="4%" class="labelheading1 tbcellCss print_cell">Sno.</td>  
												<td width="8%" class="labelheading1 tbcellCss print_cell">Clinical Id</td>         
												<td width="10%" class="labelheading1 tbcellCss print_cell">Main Group Name</td>   
												<td width="10%" class="labelheading1 tbcellCss print_cell">Therapeutic Main Group Name</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Pharmacological SubGroup Name</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Chemical SubGroup Name</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Chemical Substance Name</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Route Type</td>
												<td width="9%" class="labelheading1 tbcellCss print_cell">Route</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Strength Type</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Strength</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Dosage</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Medication Period</td>
												
											</tr>

											<c:forEach  items="${drugsLst}" varStatus="loop3">
												<tr>
													<td class="tbcellBorder print_cell">${loop3.count}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].clinicalId}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].drugTypeName}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].drugSubTypeName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsLst[loop3.count-1].pSubGrpName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsLst[loop3.count-1].cSubGrpName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsLst[loop3.count-1].drugName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsLst[loop3.count-1].routeTypeName}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].routeName}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].strengthTypeName}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].strengthName}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].dosage}</td>
													<td class="tbcellBorder print_cell">${drugsLst[loop3.count-1].medicationPeriod}</td>
												</tr>
											</c:forEach>
										</table>
										</c:if>
										<c:if test="${fn:length(drugsLst) eq 0}">
											<b><center>Clinical Notes drugs list not found for this case</center></b>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="left" class="labelheading1 tbcellCss print_cell">
							<FONT size="2" style="STRONG" ><b>POST CLINICAL NOTES DRUGS LIST</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell" colspan="6" >
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td>
										<c:if test="${fn:length(drugsPostLst) > 0}">
										<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
											align="center" class="tabBorder">
											<tr>  
												<td width="4%" class="labelheading1 tbcellCss print_cell">Sno.</td>  
												<td width="8%" class="labelheading1 tbcellCss print_cell">Clinical Id</td>         
												<td width="10%" class="labelheading1 tbcellCss print_cell">Main Group Name</td>   
												<td width="10%" class="labelheading1 tbcellCss print_cell">Therapeutic Main Group Name</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Pharmacological SubGroup Name</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Chemical SubGroup Name</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Chemical Substance Name</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Route Type</td>
												<td width="9%" class="labelheading1 tbcellCss print_cell">Route</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Strength Type</td>
												<td width="10%" class="labelheading1 tbcellCss print_cell">Strength</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Dosage</td>
												<td width="6%" class="labelheading1 tbcellCss print_cell">Medication Period</td>
												
											</tr>

											<c:forEach  items="${drugsPostLst}" varStatus="loop4">
												<tr>
													<td class="tbcellBorder print_cell">${loop3.count}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].clinicalId}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].drugTypeName}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].drugSubTypeName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].pSubGrpName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].cSubGrpName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].drugName}</td>
													<td align="center" class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].routeTypeName}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].routeName}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].strengthTypeName}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].strengthName}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].dosage}</td>
													<td class="tbcellBorder print_cell">${drugsPostLst[loop4.count-1].medicationPeriod}</td>
												</tr>
											</c:forEach>
										</table>
										</c:if>
										<c:if test="${fn:length(drugsPostLst) eq 0}">
											<b><center>Post Clinical Notes drugs list not found for this case</center></b>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" align="left" class="tbheader print_heading">
							<FONT size="2" style="STRONG"><b>SUB CUTANEOUS INSULIN CHART</b></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="left" class="labelheading1 tbcellCss print_cell" >
							<FONT size="2" style="STRONG" ><b>CLINICAL NOTES SUB CUTANEOUS INSULIN CHART</b></FONT>
						</td>
					</tr>
					<tr>
						<td class="tbcellBorder print_cell" colspan="6" >
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr>
									<td>
										<c:if test="${fn:length(preClinicalNotes) > 0}">
										<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
											align="center" class="tabBorder">
											<tr>
												<td width="4%" class="labelheading1 tbcellCss print_cell">SNo</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Clinical ID</td>
												<td width="11%" class="labelheading1 tbcellCss print_cell">Date&nbsp;</td>
												<td width="14%" class="labelheading1 tbcellCss print_cell">Doctor Name</td>
												<td width="7%" class="labelheading1 tbcellCss print_cell">BBF(7:00 am )</td>
												<td width="7%" class="labelheading1 tbcellCss print_cell">BL(1:00pm)</td>
												<td width="7%" class="labelheading1 tbcellCss print_cell">BD(7:00pm)&nbsp;</td>
												<td width="11%" class="labelheading1 tbcellCss print_cell">Plasma Blood Glucose MN Rate&nbsp;</td>
												<td width="7%" class="labelheading1 tbcellCss print_cell">BBF(7:00am) &nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">SR(1:00pm)</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">BD(7:00pm) &nbsp;</td>
												<td width="8%" class="labelheading1 tbcellCss print_cell">Insulin Dosage MN</td>
											</tr>

											<c:forEach  items="${preClinicalNotes}" varStatus="loop5">
												<tr>
													<td class="tbcellBorder print_cell">${loop5.count}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].CLINICALID}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].investGnDate}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].docName}</td>
													<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].plasmaBbf}</td>
													<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].plasmaBl}</td>
													<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].plasmaBd}</td>
													<td align="center" class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].plasmaMn}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].insulinBbf}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].insulinSr}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].insulinBd}</td>
													<td class="tbcellBorder print_cell">${preClinicalNotes[loop5.count-1].insulinMn}</td>
												</tr>
											</c:forEach>
										</table>
										</c:if>
										<c:if test="${fn:length(preClinicalNotes) eq 0}">
											<b><center>Clinical Notes Sub Cutaneous Insulin Chart not found for this case</center></b>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="left" class="labelheading1 tbcellCss print_cell" >
							<FONT size="2" style="STRONG" ><b>POST CLINICAL NOTES SUB CUTANEOUS INSULIN CHART</b></FONT>
						</td>
					</tr>
					<tr>
					<td colspan="6" >
						<c:if test="${fn:length(postClinicalNotes) > 0}">
						<table style="table-layout:fixed;word-wrap:break-word;" border="0" width="100%" cellpadding="1" cellspacing="1"
							align="center" class="tabBorder">
							<tr>
								<td width="8%" class="labelheading1 tbcellCss print_cell">SNo</td>
								<td width="10%" class="labelheading1 tbcellCss print_cell">Clinical ID</td>
								<td width="10%" class="labelheading1 tbcellCss print_cell">Date&nbsp;</td>
								<td width="10%" class="labelheading1 tbcellCss print_cell">Doctor Name</td>
								<td width="10%" class="labelheading1 tbcellCss print_cell">BBF(7:00 am )</td>
								<td width="10%" class="labelheading1 tbcellCss print_cell">BL(1:00pm)</td>
								<td width="6%" class="labelheading1 tbcellCss print_cell">BD(7:00pm)&nbsp;</td>
								<td width="9%" class="labelheading1 tbcellCss print_cell">Plasma Blood Glucose MN Rate&nbsp;</td>
								<td width="6%" class="labelheading1 tbcellCss print_cell">BBF(7:00am) &nbsp;</td>
								<td width="10%" class="labelheading1 tbcellCss print_cell">SR(1:00pm)</td>
								<td width="6%" class="labelheading1 tbcellCss print_cell">BD(7:00pm) &nbsp;</td>
								<td width="6%" class="labelheading1 tbcellCss print_cell">Insulin Dosage MN</td>
							</tr>

							<c:forEach  items="${postClinicalNotes}" varStatus="loop6">
								<tr>
									<td class="tbcellBorder print_cell">${loop6.count}</td>
									<td class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].CLINICALID}</td>
									<td width="10%" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].investGnDate}</td>
									<td class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].docName}</td>
									<td align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].plasmaBbf}</td>
									<td align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].plasmaBl}</td>
									<td width="5%" align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].plasmaBd}</td>
									<td width="10%" align="center" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].plasmaMn}</td>
									<td width="10%" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].insulinBbf}</td>
									<td width="10%" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].insulinSr}</td>
									<td width="10%" class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].insulinBd}</td>
									<td class="tbcellBorder print_cell">${postClinicalNotes[loop6.count-1].insulinMn}</td>
								</tr>
							</c:forEach>
						</table>
						</c:if>
						<c:if test="${fn:length(postClinicalNotes) eq 0}">
							<b><center>Post Clinical Notes Sub Cutaneous Insulin Chart not found for this case</center></b>
						</c:if>
					</td>
				</tr>
				</table>
				<br/>
				
					<table id="printTab" width="100%" align="center">
						<tr><td colspan="6">&nbsp;</td></tr>
						<tr>
							<td colspan="6" style="text-align:center">
								<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
								<c:if test="${closeBut eq 'Y'}">
									&nbsp;&nbsp;
									<button class="but"  type="button" id="Close" onclick="fn_Close()">Close</button>
								</c:if>
							</td>
						</tr>
					</table>
			</c:if>	
		</td>
	</tr>
	
</table>
<script type="text/javascript">
var addition='${PatientOpList.test}';
var additionKnown='${PatientOpList.testKnown}';

if(document.getElementById("habitsTd") != null)
document.getElementById("habitsTd").style.display='block';
addition=addition.replace("PR6.1(","");

additionKnown=additionKnown.replace("PR5.1,","");
var additionList = addition.split(",");

var addKnownList = additionKnown.split(",");
if(addKnownList.length>0){
	for(var i = 0; i<addKnownList.length;i++)
    {
	    
		var addtn = addKnownList[i].split("$");
        
		if(addtn[0]=='AllMed'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllMedYes'){
				
				document.forms[0].AllMed[0].checked='checked';
				document.getElementById("AllMedDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllMedRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllMedNo'){
				document.forms[0].AllMed[1].checked='checked';
		}
	   }
		if(addtn[0]=='AllSub'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllSubYes'){
				
				document.forms[0].AllSub[0].checked='checked';
				document.getElementById("AllSubDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllSubRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllSubNo'){
				document.forms[0].AllSub[1].checked='checked';
		}
	   }
}
}
if(additionList.length>0)
{
	for(var i = 0; i<additionList.length;i++)
    {	
	
	    var addtn = additionList[i].split("$");
	    if(addtn[0]=='Alcohol')
	    	{if(addtn[1]=='Regular')
	    		document.forms[0].alcohol[0].checked='checked';
	    	else if (addtn[1]=='Occasional')
	    		document.forms[0].alcohol[1].checked='checked';
	    	else if (addtn[1]=='Teetotaler')
	    		document.forms[0].alcohol[2].checked='checked';
	    	}
	    else if(addtn[0]=='Tobacco')
		    {
	    	var tabacoLst = addtn[1].split("(");
	    	
	    	if(tabacoLst[0]=='Snuff')
	    		document.forms[0].tobacco[0].checked='checked';
	    	else if (tabacoLst[0]=='Chewable')
	    		document.forms[0].tobacco[1].checked='checked';
	    	else if (tabacoLst[0]=='Smoking')
		    	{
	    		document.forms[0].tobacco[2].checked='checked';
	    		tabacoLst[1] = tabacoLst[1].replace(")","");
	    		
	    		document.getElementById("smokingTd").style.display='block';
	    		
	    		var smokSub = tabacoLst[1].split("*");
	    	
	    		if(smokSub.length>0)
		    		{
                       for(var j=0;j<smokSub.length;j++){
                    	   
                    	  var smokeVal= smokSub[j].split("@");
                    	  
                    	  if(smokeVal[0]=='PackNo')
                    		  document.forms[0].packNo.value=smokeVal[1];
                    	  else
                    		  document.forms[0].smokeYears.value=smokeVal[1];
                           } 
		    		}
		    	}
             }
	    else if(addtn[0]=='DrugUse')
		    {
              if(addtn[1]=='Yes')
            	  document.forms[0].drugUse[0].checked='checked';
              else  if(addtn[1]=='No')
            	  document.forms[0].drugUse[1].checked='checked';
            }
	    else if(addtn[0]=='BetelNut')
	    {
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelNut[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelNut[1].checked='checked';
	    }
	    else if(addtn[0]=='BetelLeaf')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelLeaf[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelLeaf[1].checked='checked';
	    }
    }
}

/*if('${forPrint}' == 'Y')
{
	document.getElementById('printCaseSheetHdr').style.display="";
	if(document.getElementById('menuImage'))
		document.getElementById('menuImage').style.display="none";
	if(document.getElementById('topImage'))
		document.getElementById('topImage').style.display="none";
	document.getElementById('printTab').style.display="none";
	alert('here');
	window.print();
	alert('here2');
	document.getElementById('printCaseSheetHdr').style.display="none";
	if(document.getElementById('menuImage'))
		document.getElementById('menuImage').style.display="";
	if(document.getElementById('topImage'))
		document.getElementById('topImage').style.display="";
	document.getElementById('printTab').style.display="";
	alert('here3');
	
}*/

</script>
</form>
</body>
</head>
</html>