<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <title>Home Page</title> 
	 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ include file="/common/include.jsp"%>  
    <script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
    <link href="/<%=context%>/css/login.css" rel="stylesheet" type="text/css" media="screen">
    <link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
    <link href="/<%=context%>/css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
    <link href="/<%=context%>/css/themes/<%=themeColour%>/style.css" rel="stylesheet" type="text/css" media="screen">
	<script type="text/javascript" src="/<%=context%>/js/popup.js"></script>
	<link rel="shortcut icon" href="/<%=context%>/images/favicon.ico" >
	<link rel="icon" type="/<%=context%>/images/ico" href="/<%=context%>/images/favicon.ico">
	<!-- Dialog -->
	<script src="js/jquery.ui.core.js"></script> 
	<script src="js/jquery.ui.widget.js"></script> 
    <script src="js/dialog box/jquery.ui.mouse.js"></script> 
	<script src="js/dialog box/jquery.ui.draggable.js"></script>
	<script src="js/dialog box/jquery.ui.position.js"></script>
	<script src="js/dialog box/jquery.ui.resizable.js"></script>
	<script src="js/dialog box/jquery.ui.button.js"></script>
	<script src="js/dialog box/jquery.ui.dialog.js"></script>
	<script src="js/dialog box/jquery.ui.effect.js"></script>
	<script src="js/dialog box/jquery.ui.effect-slide.js"></script>
	<script src="js/dialog box/jquery.dialogextend.js"></script>

	<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="js/dialog box/jquery.ui.button.css">
<link rel="stylesheet" href="js/dialog box/jquery.ui.dialog.css">
<link rel="stylesheet" href="js/dialog box/jquery.ui.resizable.css">
<link rel="stylesheet" href="js/dialog box/jquery.ui.selectable.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<!-- Dialog -->
	<%@page
import="com.sun.identity.saml2.common.SAML2Exception,
com.sun.identity.saml2.common.SAML2Constants,
com.sun.identity.saml2.assertion.Assertion,
com.sun.identity.saml2.assertion.Subject,
com.sun.identity.saml2.profile.SPACSUtils,
com.sun.identity.saml2.protocol.Response,
com.sun.identity.saml2.assertion.NameID,
com.sun.identity.saml.common.SAMLUtils,
com.sun.identity.shared.encode.URLEncDec,
com.sun.identity.plugin.session.SessionException,
java.io.IOException,
java.util.Iterator,
java.util.List,
java.util.Map,
java.util.HashMap,
java.util.HashSet,
java.util.Set,
java.util.*,
com.sun.identity.saml2.common.SAML2Utils,
com.sun.identity.saml2.jaxb.metadata.AssertionConsumerServiceElement,
com.sun.identity.saml2.jaxb.metadata.IDPSSODescriptorElement,
com.sun.identity.saml2.jaxb.metadata.SPSSODescriptorElement,
com.sun.identity.saml2.jaxb.metadata.SingleSignOnServiceElement,
com.sun.identity.saml2.meta.SAML2MetaManager"
%>
<style type="text/css">
#container{border-right:1px solid #ddd;border-left:1px solid #ddd;background:#fff;box-shadow:0px 0px 10px 7px #ccc;width:1000px;margin:0 auto;}
.theme1 {BACKGROUND: url(/<%=context%>/css/themes/green/header.jpg) no-repeat;POSITION: relative;cursor: pointer;}
.theme2 {BACKGROUND: url(/<%=context%>/css/themes/gray/header.jpg) no-repeat; POSITION: relative;cursor: pointer; }
.theme3 {BACKGROUND: url(/<%=context%>/css/themes/greenyellow/header.jpg) no-repeat; POSITION: relative;cursor: pointer;}
.theme4 {BACKGROUND: url(/<%=context%>/css/themes/orange/header.jpg) no-repeat; POSITION: relative;cursor: pointer;}
.theme5 {BACKGROUND: url(/<%=context%>/css/themes/reddish/header.jpg) no-repeat; POSITION: relative;cursor: pointer;}
.theme6 {BACKGROUND: url(/<%=context%>/css/themes/default/header.jpg) no-repeat; POSITION: relative;cursor: pointer;}
.theme7 {BACKGROUND: url(/<%=context%>/css/themes/brown/header.jpg) no-repeat;POSITION: relative;cursor: pointer;}
.theme8 {BACKGROUND: url(/<%=context%>/css/themes/darkgreen/header.jpg) no-repeat;POSITION: relative;cursor: pointer;}
.theme9 {BACKGROUND: url(/<%=context%>/css/themes/navyblue/header.jpg) no-repeat;POSITION: relative;cursor: pointer;}
.modulesList li {
    list-style-type: none;
    padding: 10px 0;
}
.modulesList li a {
    font-weight: bold;
    padding: 0 15px;
}
</style>	
</head>
<script>
var vistorCount = '${vistorCount}';
var globalURl="";
function setGlobalUrl(url){
	globalURl=url;
}
function getGlobalUrl(){
   	if (globalURl.indexOf("NaN") !=-1) {
   	    globalURl=globalURl.replace('NaN','');
   	}
   	fn_loadImage();
   	document.getElementById("middleFrame").src=globalURl;
   }
var attachmentWin = null;
function fn_digitalSignatureReg(){
	    var UserID = '${UserID}';
	    var url = 'http://www.ehf.gov.in/Home/digitalCertificate.do?actionFlag=signUpPage&UserID='+UserID;
		window.open(url,'window1','toolbar=no,resize=no,scrollbar=no,width=700, height=450, top=80,left=60');
	}
function fn_CloseWins()
{
	if(attachmentWin != null)
		attachmentWin.close();
	}
function fn_changePassword() {
	var userName = '${userName}';
	document.getElementById("username").value=userName;
	document.getElementById("changePasswordDiv").src='http://www.ehf.gov.in/Home/loginAction.do?actionFlag=checkLogin&subActionFlag=ChangePwd&userName='+userName+'&username='+userName;
	centerPopup("#popupContact");
	loadPopup("#popupContact");
}
function fn_pnlDocBankDtls()
{
fn_loadImage();
document.getElementById("middleFrame").src='/<%=context%>/panelDoc.do?actionFlag=panelDocSearch';
} 
function fn_ahcClaimCases(){
	fn_loadImage();
	setGlobalUrl('/<%=context%>/annualCheckUpAction.do?actionFlag=AHCClaimCases&casesSearchFlag=N');
	 document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=AHCClaimCases&casesSearchFlag=N';
		
	}
function fn_ahcCasesSearch(){
	fn_loadImage();
	setGlobalUrl('/<%=context%>/annualCheckUpAction.do?actionFlag=AHCClaimCases&casesSearchFlag=Y');
	 document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=AHCClaimCases&casesSearchFlag=Y';
		
	}	
function fn_changeSettings() {
	
	centerPopup("#popupTheme");
	loadPopup("#popupTheme");
}
function fn_updateProfile() {
	document.getElementById("changePasswordDiv").src='/<%=context%>/loginAction.do?actionFlag=UpdateProfile';
	centerPopup("#popupContact");
	loadPopup("#popupContact");
}

//Function for units
function fn_changeTheme(themeColour)
{
var xmlhttp;
if (window.XMLHttpRequest)
{
xmlhttp=new XMLHttpRequest();
}
else if (window.ActiveXObject)
{		
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
else
{
alert("Your browser does not support XMLHTTP!");
}
xmlhttp.onreadystatechange=function()
{
if(xmlhttp.readyState==4)
{
	    document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=reloadHomePage";
		document.forms[0].submit();

}			
}
	var url =  "/<%=context%>/loginAction.do?actionFlag=saveTheme&themeColour="+themeColour;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function sessionExpireyClose(){
	window.close();
}

function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";
}

function detectBrowser(){
	if ( navigator.appName == 'Opera' ) return 'Opera';
	 if ( navigator.appName == 'Microsoft Internet Explorer' )  return 'MSIE';
	 if ( navigator.userAgent.indexOf( 'Chrome' ) >= 0 ) return 'Chrome';
	 if ( navigator.userAgent.indexOf( 'SeaMonkey' ) >= 0 ) return 'SeaMonkey';  // must test before Firefox
	 if ( navigator.userAgent.indexOf( 'Safari' ) >= 0 ) return 'Safari';
	 if ( navigator.userAgent.indexOf( 'Firefox' ) >= 0 ) return 'Firefox';
	 return navigator.appName;
} 
function fn_removeLoadingImage()  
{
	document.getElementById('processImagetable').style.display="none";  
}
function fn_logout() 
{
	document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=logout";
	document.forms[0].submit();
}
function redirectDefaultPage()
{
	var dsgId='${DsgId}';
	if(dsgId !=null && dsgId =='DG1046')
		{
		 document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=preauthApprovedCases&caseSearchType=caseIp&patientScheme=CD501'; 

		var newheight= $( document ).height();
		document.getElementById("middleFrame").style.height=(newheight-(120))+"px"; 
		}
	else
		{
	document.getElementById("middleFrame").src='login/afterLogin.jsp'; 
	var newheight= $( document ).height();
	document.getElementById("middleFrame").style.height=(newheight-(145))+"px";
		}
}

function fn_casesSearch()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp');
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp'; 
	}
function fn_JournalistCasesSearch()  
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502');
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502'; 
  //document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=viewAhcClaimPage&ahcId=99463';
	}


function fn_casesForApproval()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=preauth');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=preauth';
	}
function fn_journalistCasesForApproval()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&module=preauthJournal');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&module=preauthJournal';
	
	}


function fn_casesForApprovalClaim()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=claim');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=claim';
	}
	//vijay
function fn_cochlearCasesForApprovalClaims()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=cochlearAco');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=cochlearAco';
	
}
function fn_casesForApprovalOrg(autoCaseId)
{
	fn_loadImage();	
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&medFlag=S&casesForApproval=Y&module=preauthOrg&autoCaseId='+autoCaseId;
	//document.getElementById("middleFrame").src='casesSearchAction.do?actionFlag=OnloadCaseOpen&medFlg=S&caseSearchType=caseIp&casesForApproval=Y&module=preauthOrg&autoCaseId='+autoCaseId;
}
function fn_casesForApprovalOrgClaim(autoCaseId)
{
	fn_loadImage();	
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&medFlag=S&casesForApproval=Y&module=claimOrg&autoCaseId='+autoCaseId;
	//document.getElementById("middleFrame").src='casesSearchAction.do?actionFlag=OnloadCaseOpen&medFlg=S&caseSearchType=caseIp&casesForApproval=Y&module=claimOrg&autoCaseId='+autoCaseId;
}	
function fn_dentalCasesForApprovalClaim()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&dentalFlag=Y&patientScheme=CD501&casesForApproval=Y&module=claim');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&dentalFlag=Y&patientScheme=CD501&casesForApproval=Y&module=claim';
	}
function fn_JournalistcasesForApprovalClaim()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&module=claimJournal');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&module=claimJournal';
	}

function fn_userDownloads()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=downloadUserCSV';
}

function fn_followUpCaseSearch(){ 
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch';
	}
  
  function fn_followUpCaseSearchForApproval(){
	  fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch&casesForApproval=Y';
	}
  function fn_claimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getClaimsPaymentRecrds&caseStatus=CD93';
	}

  function fn_cochlearFollowUpPendCases()
  {
    	fn_loadImage();
    	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=cochlearFPClaim&casesForApproval=Y&PendingFlag=Y';
  }

  function fn_cochlearFollowUpCases()
	{
	  	fn_loadImage();
	  	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=cochlearFPClaim&casesForApproval=Y';
	}
function fn_cochlearFollowUpCasesSearch()
	{
	  	fn_loadImage();
	  	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=cochlearFPClaim&casesForApproval=N';
	}
  function fn_ErrClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getErrPaymentRecrds&errStatusId=CD109';	
	}	

  function fn_TdsClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType=CD125';  	
	}

	
  function fn_errCasesForApproval()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&errSearchType=Y&module=claim';  
  }
  function fn_dentalErroneousCasesForApprovalClaim()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&errDentalSearchType=Y&module=claim';  
  }
  function fn_JournalistErrCasesForApproval()
  { 
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&errSearchType=Y&module=claimJournal';  
  }

  
  function fn_viewDeathCase()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=viewDeathCases';  
  }
  function fn_followUpPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus=CD104';
		}
  
  function fn_panelDocPayments()
	{
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/panelDocPay.do?actionFlag=panelDocPayHome';
	}
	
	function fn_panelDocPaymentsForCEO()
	{
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/panelDocPay.do?actionFlag=getCasesForCEO';
	}
	
	function fn_panelDocTDSPayments()
	{
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/panelDocPay.do?actionFlag=getTDSCases';
	}
  
	function fn_medicalAudit(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=medicalAuditSearchPage';
		
	}
  function fn_maWorklist(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getMAworklist&backFlag=no';
		
	}
  
  function fn_maAuditedCases(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=no';
		
	}
  function fn_CasesForDiss(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&disSearchType=Y&module=claim';
	}
  function fn_gotoMail(){
	    var UserID = '${UserID}';
		document.getElementById("changeMailDiv").src='http://www.ehf.gov.in/Home/mailAction.do?actionFlag=openmail&UserID='+UserID;
		centerPopup("#popupMail");
		loadPopup("#popupMail"); 
	}
	function showCMS(UserRole)
	{	
	   var url="${CMSUrl}/changeMgmtAction.do?flag=showChangeMgmt";
	   var child= window.open( url,'ChangeManagement','width=800, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
	   if (window.focus) {child.focus();
		}
	  
	}
	function fn_application(url) {
		window.parent.location.href=url;  
	}
function fn_casesForApprovalNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&medFlag=O&module=preauth&autoCaseId='+autoCaseId;
}
function fn_JournalistcasesForApprovalNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&medFlag=O&patientScheme=CD502&module=preauthJournal&autoCaseId='+autoCaseId;
}
function fn_JrnlstDentalCases(autoCaseId)
{
	fn_loadImage();
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&patientScheme=CD502&dentalFlag=Y&module=claimDentalJournal&autoCaseId='+autoCaseId;
}

function fn_casesForApprovalClaimNew(autoCaseId)
{
	fn_loadImage();
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&medFlag=O&patientScheme=CD501&module=claim&autoCaseId='+autoCaseId;
}
function fn_dentalCasesForApprovalClaimNew(autoCaseId)
{
	fn_loadImage();
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&patientScheme=CD501&dentalFlag=Y&module=claimDental&autoCaseId='+autoCaseId;
}
function fn_JournalistcasesForApprovalClaimNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&medFlag=O&patientScheme=CD502&module=claimJournal&autoCaseId='+autoCaseId;
	
}


function fn_caseForApprovalFollowUpNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=OnloadCaseOpen&patientScheme=CD501&casesForApproval=Y&module=followUp&autoCaseId='+autoCaseId;
}

function fn_regAnnualPatient()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=openAnnualPatRegForm';
}
function fn_RegisterChronicOp()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=openChronicPatRegForm';
}
function fn_PharmaOPCasesView()
{	
fn_loadImage();
document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getPharmaOPCases&advSearch=false&patientScheme=CD501&pharmaCases=Y&apprPharma=N';  
}
function fn_PharmaOPCasesAppr()
{
	fn_loadImage();
document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getPharmaOPCases&advSearch=false&patientScheme=CD501&pharmaCases=Y&apprPharma=Y';
}
function fn_openSchedulerLinks()
{
	  fn_loadImage();
  function fn_biomOtherVisitsReport()
	{
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/bioMetricAction.do?actionFlag=openVisitorsReport';
	}
	  document.getElementById("middleFrame").src='/<%=context%>/schedulersAction.do?actionFlag=openSchedulers';  
}
function fn_cardSearch()
{
	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/cardSearchAction.do?actionFlag=cardSearch';  
}
 function fn_biometricCap()
 {
 var userId="${userId}";
 var isMacId=null;
 var sysmacids;
 	
 	 isMacId=biometricAttendance(userId);
 	
 if(isMacId)
 {     fn_loadImage();
 	  document.getElementById("middleFrame").src='/<%=context%>/bioMetricAction.do?actionFlag=bioCapture&sysMacId='+isMacId;
 }  
 }
 function fn_biometricAtt()
 {var userId="${userId}";
 var isMacId=null;
 var sysmacids;
 	
 	 isMacId=biometricAttendance(userId);
 	
 if(isMacId)
 { 
 	 fn_loadImage();
  	  document.getElementById("middleFrame").src='/<%=context%>/bioMetricAction.do?actionFlag=bioMetricAttendance&sysMacId='+isMacId;
 }}
 function fn_biometricReport()
 {
 	 fn_loadImage();
 	  document.getElementById("middleFrame").src='/<%=context%>/bioMetricAction.do?actionFlag=bioMetricReport';
 }
//Added by ramalakshmi for DME login cases seacrh
function fn_preauthApprovedCases()
{

	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=preauthApprovedCases&caseSearchType=caseIp&patientScheme=CD501');
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=preauthApprovedCases&caseSearchType=caseIp&patientScheme=CD501'; 
	}
function fn_DMEFlagCases()
{
	
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=dmeFlagCases&dmeFlag=Y';
	
}
function fn_dialysisClaimCasesForApproval(autoCaseId)
{
	fn_loadImage();
	 document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&diaFlg=Y&module=claim&autoCaseId='+autoCaseId;
}

function fn_EOUpdatedCases()
{
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=eoUpdatedCases&dmeFlag=N';
}
//Added by ramalakshmi for hubspoke CR
function fn_dialysisCasesForApproval(autoCaseId)
	{
		fn_loadImage();
		 document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&diaFlg=Y&module=preauth&autoCaseId='+autoCaseId;
	}
function fn_dialysisPendingCases()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&diaPendFlg=Y&module=preauth');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&diaPendFlg=Y&module=preauth';
	
}   //End of hubSpoke change
</script>
<body onload="javascript:redirectDefaultPage();"> 
<html:form  method="post"  action="/loginAction.do" >
<html:hidden property="username" name="loginForm" styleId="username" />
<%!
    public Map getIDPBaseUrlAndMetaAlias(String idpEntityID, String deployuri)
    {
        Map returnMap = new HashMap();
        if (idpEntityID == null) {
            return returnMap;
        }
        String idpBaseUrl = null;
        try {
            // find out IDP meta alias
            SAML2MetaManager manager = new SAML2MetaManager();
            IDPSSODescriptorElement idp =
                manager.getIDPSSODescriptor("/", idpEntityID);
            List ssoServiceList = idp.getSingleSignOnService();
            if ((ssoServiceList != null)
                && (!ssoServiceList.isEmpty())) {
                Iterator i = ssoServiceList.iterator();
                while (i.hasNext()) {
                    SingleSignOnServiceElement sso =
                        (SingleSignOnServiceElement) i.next();
                    if ((sso != null) && (sso.getBinding() != null)) {
                        String ssoURL = sso.getLocation();
                        int loc = ssoURL.indexOf("/metaAlias/");
                        if (loc == -1) {
                            continue;
                        } else {
                            returnMap.put("idpMetaAlias",
                                ssoURL.substring(loc + 10));
                            String tmp = ssoURL.substring(0, loc);
                            loc = tmp.lastIndexOf("/");
                            returnMap.put("idpBaseUrl", tmp.substring(0, loc));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            SAML2Utils.debug.error("header.jspf: couldn't get IDP base url:",e);
        }
        return returnMap;
    }
%><%!
    public String getFedletBaseUrl(String spEntityID, String deployuri)
    {
        if (spEntityID == null) {
            return null;
        }
        String fedletBaseUrl = null;
        try {
            SAML2MetaManager manager = new SAML2MetaManager();
            SPSSODescriptorElement sp =
                manager.getSPSSODescriptor("/", spEntityID);
            List acsList = sp.getAssertionConsumerService();
            if ((acsList != null) && (!acsList.isEmpty())) {
                Iterator j = acsList.iterator();
                while (j.hasNext()) {
                    AssertionConsumerServiceElement acs =
                        (AssertionConsumerServiceElement) j.next();
                    if ((acs != null) && (acs.getBinding() != null)) {
                        String acsURL = acs.getLocation();
                        int loc = acsURL.indexOf(deployuri + "/");
                        if (loc == -1) {
                            continue;
                        } else {
                            fedletBaseUrl = acsURL.substring(
                                0, loc + deployuri.length());
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            SAML2Utils.debug.error(
                "header.jspf: couldn't get fedlet base url:",e);
        }
        return fedletBaseUrl;
    }
%>
	<%
    String deployuri = request.getRequestURI();
    int slashLoc = deployuri.indexOf("/", 1);
    if (slashLoc != -1) {
        deployuri = deployuri.substring(0, slashLoc);
    }
%>
<%
String deployUri=(String)session.getAttribute("deployUri");
String entityID=(String)session.getAttribute("entityID");
String spEntityID=(String)session.getAttribute("spEntityID");
String nameId=(String)session.getAttribute("nameId");
String value=(String)session.getAttribute("nameValue");
String sessionIndex=(String)session.getAttribute("sessionIndex");
Map idpMap = getIDPBaseUrlAndMetaAlias(entityID, deployuri);
String idpBaseUrl = (String) idpMap.get("idpBaseUrl");
String idpMetaAlias = (String) idpMap.get("idpMetaAlias");
String fedletBaseUrl = getFedletBaseUrl(spEntityID, deployuri);
String logOutUrl=idpBaseUrl + "/IDPSloInit?metaAlias=" + idpMetaAlias + "&binding=urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect&RelayState=" + fedletBaseUrl + "/login/HomePageOpenAM.jsp";
%>
<div id="container" style="padding-top:0px;margin:0px auto;">
	<div id="headerdivnew"   class="wrap1"  style="height:  56px;"  align="right" >
	 
	 <table style="height: 56px; width:60%" >
	   <tr class="timeStyle">
		<td align="left"  width="289px" valign="top" class="details">
	   <p><b>&nbsp;&nbsp;&nbsp;&nbsp;Welcome &nbsp;:&nbsp;</b>${fullName}</p>
	   <b> &nbsp;&nbsp;&nbsp;&nbsp;Designation &nbsp;:&nbsp;</b>
	   <p>
	   <c:choose>
			<c:when test="${loggedUserState eq 'CD201' &&
			(dsgnName eq 'MITHRA' || dsgnName eq 'Aarogya Mithra' || dsgnName eq 'Mithra' || dsgnName eq 'NAM' )}" >
			VAIDYA MITHRA
			</c:when>
			<c:otherwise >
			${dsgnName}
			</c:otherwise>
		</c:choose></p>
	  </td> 
   
   <td class="menu">
            <div class="item">
                <a class="link icon_mail" title="Click here to log out" ></a>
                <div class="item_content">
                    <!--<a href="javascript:fn_logout()" title="Logout" >Logout</a> -->
                   <a href=<%=logOutUrl%> onclick="return confirm('Do you want to logout?')"><b class="timeStyle">Logout</b></a>			
                </div>
            </div>
            <c:if test="${DsgId eq 'DG1046'}">
								<li >
									<a>	<span >No of Visits : ${vistorCount} </span></a>
								</li>
							</c:if>
            <div class="item">
                <a class="link icon_find"></a>
                <div class="item_content item_diff">
                    <a href="javascript:fn_changePassword()" title="Change Password">Change<br />Password</a>
                </div>
            </div>
            
			<div class="item"><a class="link icon_cms" title="CMS"></a>
				<div class="item_content"><a href='javascript:showCMS("<% session.getAttribute("roleName"); %>")' 
					title="showCMS">CMS</a>
				</div>
			</div>
            <div class="item">
                <a class="link icon_photos "></a>
                <div class="item_content item_diff">
                    <a href="javascript:fn_changeSettings()" title="Change Theme">Change<br />Theme</a>
                </div>
            </div>
			
			</div> 
            <div class="item">
                <a class="link icon_home"></a>
                <div class="item_content">
                    <a href="#" onclick="redirectDefaultPage()" title="Home">Home</a>
                </div>
            </div> 	 
   
   </td>
   </tr>
 </table>
</div>


<div id="menuLog" class="wrap"  style="z-index: 999;width: 100%"   >
<ul id="nav">			
<c:forEach items="${ActionbarModulesLst}" varStatus="loop">			
			<li>
				<c:set var="tmpModVO" value="${ActionbarModulesLst[loop.index]}" />
				<c:if test="${fn:length(tmpModVO.listSubMenu) gt 0}">
				  	<a href="#"> ${tmpModVO.MODNAME}</a>
				</c:if> <c:if test="${fn:length(tmpModVO.listSubMenu) eq 0}">
				<a href="${tmpModVO.MODURL}"> ${tmpModVO.MODNAME}</a>
				</c:if>
				<c:set var="lSubMenuList" value="${tmpModVO.listSubMenu}" />
				<c:set var="count" value="${fn:length(lSubMenuList)-1}"/>
				<c:if test="${fn:length(lSubMenuList) gt 0}">
					<ul>
						<c:forEach items="${lSubMenuList}" varStatus="status">
						<c:if test="${count == status.index}">
						<li style='z-index:7;' class="lastelement" >
						</c:if>
						<c:if test="${count != status.index}">
						<li style='z-index:7;' >
						</c:if>	
							<c:set var="tmpSubModVO" value="${lSubMenuList[status.index]}" />
							
							<c:if test="${fn:length(tmpSubModVO.listSubMenu) gt 0}">
								<a href="#"> ${tmpSubModVO.MODNAME} </a>
							</c:if> <c:if test="${fn:length(tmpSubModVO.listSubMenu) eq 0}">
								<a href="${tmpSubModVO.MODURL}"> ${tmpSubModVO.MODNAME} </a>
							</c:if>
							<c:set var="lSecndLvlMenuList" value="${tmpSubModVO.listSubMenu}" />
							<c:if test="${fn:length(lSecndLvlMenuList) gt 0}">
								<ul>
									<c:forEach items="${lSecndLvlMenuList}" varStatus="statusTwo">
									<li>
										<c:set var="tmpSecondVO"
											value="${lSecndLvlMenuList[statusTwo.index]}" />
										<c:if
											test="${fn:length(tmpSecondVO.listSubMenu) gt 0}">
											<a href="#"> ${tmpSecondVO.MODNAME} </a>
										</c:if> <c:if test="${fn:length(tmpSecondVO.listSubMenu) eq 0}">
											<a href="${tmpSecondVO.MODURL}"> ${tmpSecondVO.MODNAME} 
											</a>
										</c:if>
										<c:set var="lThirdLvlMenuList"
											value="${tmpSecondVO.listSubMenu}" />
										<c:if test="${fn:length(lThirdLvlMenuList) gt 0}">
											<ul>
												<c:forEach items="${lThirdLvlMenuList}"
													varStatus="statusThree">
													<li>
													<c:set var="tmpThirdVO"
														value="${lThirdLvlMenuList[statusThree.index]}" />
													<c:if
														test="${fn:length(tmpThirdVO.listSubMenu) gt 0}">
														<a href="#"> ${tmpThirdVO.MODNAME} </a>
													</c:if> <c:if test="${fn:length(tmpThirdVO.listSubMenu) eq 0}">
														<a href="${tmpThirdVO.MODURL}">
														${tmpThirdVO.MODNAME} </a>
													</c:if>
													<c:set var="lFourthLvlMenuList"
														value="${tmpThirdVO.listSubMenu}" />
													<c:if test="${fn:length(lFourthLvlMenuList) gt 0}">
														<ul>
															<c:forEach items="${lFourthLvlMenuList}"
																varStatus="statusFour">
																<li>
																<c:set var="tmpFourthVO"
																	value="${lFourthLvlMenuList[statusFour.index]}" />
																<c:if
																	test="${fn:length(tmpFourthVO.listSubMenu) gt 0}">
																	<a href="#"> ${tmpFourthVO.MODNAME} </a>
																</c:if> <c:if test="${fn:length(tmpFourthVO.listSubMenu) eq 0}">
																	<a href="${tmpFourthVO.MODURL}">
																	${tmpFourthVO.MODNAME} </a>
																</c:if>
																</li>
															</c:forEach>
														</ul>
														
													</c:if>
													<c:set var="lFifthLvlMenuList"
														value="${tmpFourthVO.subMenuLst}" />
													<c:if test="${fn:length(lFifthLvlMenuList) gt 0}">
														<ul>
															<c:forEach items="${lFifthLvlMenuList}"
																varStatus="statusFive">
																<li>
																<c:set var="tmpFifthVO"
																	value="${lFifthLvlMenuList[statusFive.index]}" />
																<c:if
																	test="${fn:length(tmpFifthhVO.subMenuLst) gt 0}">
																	<a href="#"> ${tmpFifthVO.MODNAME} </a>
																</c:if> <c:if test="${fn:length(tmpFifthVO.subMenuLst) eq 0}">
																	<a href="${tmpFifthVO.MODURL}">
																	${tmpFifthhVO.MODNAME} </a>
																</c:if>
																</li>
															</c:forEach>
														</ul>
														
													</c:if>
													<c:set var="lSixthLvlMenuList"
														value="${tmpFifthVO.subMenuLst}" />
													<c:if test="${fn:length(lSixthLvlMenuList) gt 0}">
														<ul>
															<c:forEach items="${lSixthLvlMenuList}"
																varStatus="statusSix">
																<li>
																<c:set var="tmpSixthVO"
																	value="${lSixthLvlMenuList[statusSix.index]}" />
																<c:if
																	test="${fn:length(tmpFifthhVO.subMenuLst) gt 0}">
																	<a href="#"> ${tmpSixthVO.MODNAME} </a>
																</c:if> <c:if test="${fn:length(tmpSixthVO.subMenuLst) eq 0}">
																	<a href="${tmpSixthVO.subMenuLst}">
																	${tmpSixthVO.MODNAME} </a>
																</c:if>
																</li>
															</c:forEach>
														</ul>
													</c:if>
													</li>
												</c:forEach>
											</ul>
										</c:if>
										</li>
									</c:forEach>
								</ul>		
							</c:if>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				</li>
			</c:forEach>			
		</ul>	 
		<ul>
		    <li style="float:right;list-style-type:none;font-weight:bold;margin:7px 10px 0 0;"><a href="#" id="modules">Modules List</a>
			<ul class="modulesList" style="display:none;">
			<c:forEach items="${moduleList}" varStatus="loop">
			<c:set var="tmpModVO" value="${moduleList[loop.index]}" />
			<li id=${tmpModVO.ID} class="wrap">
			<a href="javascript:fn_application('${tmpModVO.VALUE}')"  title="Click to open ${tmpModVO.ID} module">${tmpModVO.ID}</a>
			</li>
			</c:forEach>
			</ul>
			</li>
		</ul>		
		</div>
<div id="processImagetable" style="top:50%;width:100%;position:absolute;z-index:60;height:100%">
   <table border="0" align="center" width="100%" style="height:400" >
      <tr>
         <td>
           <div id="processImage" align="center">
             <img src="images/Progress.gif" width="100"
                     height="100" border="0"></img>
            </div>
          </td>
        </tr>
     </table>
</div>
<div class="clearboth">&nbsp;</div>
<iframe width="100%" height="100%" id="middleFrame" scrolling="auto" name="middleFrame" onload="javascript:fn_removeLoadingImage();" ></iframe>
<div class="clearboth">&nbsp;</div>
<div id='footer'>Employees Health Scheme <br />&#169; 2013 All Rights reserved. Aarogyasri Health Care Trust</div>
</div>
<script type="text/javascript">
$(document).ready(function() {	
	$('#nav>li').hover(function() {
		$('ul:first', this).slideDown(200);
		$(this).children('a:first').addClass("hov");
	}, function() {
		$('ul', this).slideUp(100);
		$(this).children('a:first').removeClass("hov");		
	});
	$('#nav li ul li').hover(function() {
		$('ul:first', this).slideDown(200);
		$(this).children('a:first').addClass("hovinner");
	}, function() {
		$('ul', this).slideUp(100);
		$(this).children('a:first').removeClass("hovinner");		
	});
});
</script>

<div id="backgroundPopup"  style="z-index: 1000" >
</div>
<!-- START FOR FORGOT PASSWORD POPUP -->
<div id="popupContact"   style="z-index: 1001">
<a id="popupContactClose" ><img  src="images/fileclose.png"/></a><br>
<iframe style="border:0;width: 100%;height: 400px" id="changePasswordDiv" scrolling="no" name="changePasswordDiv" >
</iframe>
	</div>
	<!-- END FOR FORGOT PASSWORD POPUP -->
	<!-- START FOR Mail POPUP -->
		<div id="popupMail" style="z-index: 1001" >
			<a id="popupMailClose"><img src="images/fileclose.png" />
			</a><br>
			<iframe align="middle" style="border: 0; width: 100%; height: 100%"
				id="changeMailDiv" scrolling="no" name="changeMailDiv"> </iframe>
		</div>
		<!-- END FOR Mail POPUP -->
		<!-- START FOR SETTINGS POPUP -->
<div id="popupTheme"   style="z-index: 1011">
<a id="popupThemeClose" ><img  src="images/fileclose.png"/></a><br>       
<table align="center" style="vertical-align:middle;"   height="540px">
<tr class="theme9"><td style="width: 700px;height:40px" onclick="fn_changeTheme('navyblue');" ></td></tr>
<tr class="theme1"><td style="width: 700px;height:40px" onclick="fn_changeTheme('green');" ></td></tr>
<tr class="theme2"><td style="width: 700px;height:40px" onclick="fn_changeTheme('gray');" ></td></tr>
<tr class="theme3"><td style="width: 700px;height:40px" onclick="fn_changeTheme('greenyellow');"  ></td></tr>
<tr class="theme4"><td style="width: 700px;height:40px" onclick="fn_changeTheme('orange');" ></td></tr>
<tr class="theme5"><td style="width: 700px;height:40px" onclick="fn_changeTheme('reddish');" ></td></tr>
<tr class="theme6"><td style="width: 700px;height:40px" onclick="fn_changeTheme('default');"></td></tr>
<tr class="theme8"><td style="width: 700px;height:40px" onclick="fn_changeTheme('darkgreen');" ></td></tr>
<tr class="theme7"><td style="width: 700px;height:40px" onclick="fn_changeTheme('brown');" ></td></tr>
</table>


	</div>
	<!-- Dialog -->
		<div id="dialog-MEDCO" class="Med" title="Notice" style="display:none;">
		<table align="center" style="vertical-align: middle;" width="100%">
		<tr>
			<td class="labelheading1 tbcellCss">
			<c:choose>
			<c:when test="${fn:length(ClinicalNotesCasesList) gt 0}">
				<b>Please enter Clinical Notes for the following cases:</b>
			</c:when>
			<c:otherwise>
				<b>No case is pending for updation of clinical notes today </b>
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1px" width="98%">
					<c:forEach items="${ClinicalNotesCasesList}" var="list" varStatus="x">
						<c:if test="${x.count%5 eq 1}">
						<tr>	
						</c:if>
							<td class="tbcellBorder">
								<b><center> ${list.ID} </center></b>
							</td>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</div>
<div id="dialog-EXECUTIVE" class="Med" title="Notice" style="display:none;">
	<p>dialogExe</p>
</div>
<!--Dialog  -->

	<!-- END FOR SETTINGS POPUP -->
	<script src="/<%=context%>/js/jquery-animate-css-rotate-scale.js" type="text/javascript"></script>
        <script>
            $('.item').hover(
                function(){
                    var $this = $(this);
                    expand($this);
                },
                function(){
                    var $this = $(this);
                    collapse($this);
                }
            );
            function expand($elem){
                var angle = 0;
                var t = setInterval(function () {
                    if(angle == 1440){
                        clearInterval(t);
                        return;
                    }
                    angle += 40;
                    $('.link',$elem).stop().animate({rotate: '+=-0deg'}, 0);
                },10);
                $elem.stop().animate({width:'115px'}, 1000)
                .find('.item_content').fadeIn(400,function(){
                    $(this).find('p').stop(true,true).fadeIn(600);
                });
            }
            function collapse($elem){
                var angle = 1440;
                var t = setInterval(function () {
                    if(angle == 0){
                        clearInterval(t);
                        return;
                    }
                    angle -= 40;
                    $('.link',$elem).stop().animate({rotate: '+=0deg'}, 0);
                },10);
                $elem.stop().animate({width:'30px'}, 1000)
                .find('.item_content').stop(true,true).fadeOut().find('p').stop(true,true).fadeOut();
            }
			function dialogMsg(roleName){
                var role = "dialog-"+roleName;               
            	$( "#"+role ).dialog({maxHeight: 250, minWidth: 450,
            		position: { my: "left bottom", at: "left bottom"},
            		closeOnEscape: false,
            		modal: false,
            		show: {
            			effect: "slide",
            			direction: "down" 
            		},
            		hide: {
            			effect: "slide",
            			direction: "down"
            		}
            	}).dialogExtend({
                	 maximizable: true,  
                	 minimizable: true, 
                	 collapsable: false,
                	 minimizeLocation: "left"
                    });
            	$( "#"+role ).css('display', 'block');
            	}
				
				if('${dsgnName}'!=null && '${dsgnName}'!="" && '${dsgnName}'=="MEDCO"){
					dialogMsg('${dsgnName}');
				}
            $("#modules").click(function(){
				$( ".modulesList" ).dialog({maxHeight:250,maxWidth:255,minHeight:250,minWidth:255,
            		position: { my: "right top", at: "right top", of: "#middleFrame"},
            		closeOnEscape: true,
            		modal: false,
            		show: {
            			effect: "slide",
            			direction: "up"  
            		},
            		hide: {
            			effect: "slide",
            			direction: "up"
            		} 
            	}, "height", 250, "width", 255);
				});
        </script>
</html:form>
</body>
</html>
