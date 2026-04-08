<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/include.jsp"%>
<html> 
<head>
<style>
.modal-body
{
overflow-x:hidden;
}
</style>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 

<title>Home Page</title>    
<!--[if lt IE 9]>   
<script> 
  var e = ("abbr,article,aside,audio,canvas,datalist,details," +
    "figure,footer,header,hgroup,mark,menu,meter,nav,output," +
    "progress,section,time,video").split(',');
  for (var i = 0; i < e.length; i++) {
    document.createElement(e[i]);
  }
</script> 
<style>
.navbar-nav{float:left; margin:0 !important;}
#navbarCollapse{height:auto !important;}
.collapse{display: block !important;}
.nav > LI{display: inline-block !important; *display: inline !important; zoom: 1;} 
.navbar-header{display:none;}
</style>
<![endif]-->
<%@ include file="/common/include.jsp"%>    
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/macID.js"></script>
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link rel="shortcut icon" href="/<%=context%>/images/favicon.ico" >
<link rel="icon" type="/<%=context%>/images/ico" href="/<%=context%>/images/favicon.ico">


<style>
.modal-dialog{
width:800px;
}
.glyphicon glyphicon-bell{
color:red;
 font-size: 2em;

}
.blink{
 text-decoration: blink;
}

span {
   
}


.red{
color:red;
}
</style>
<script>
var vistorCount = '${vistorCount}';
var globalURl="";
function setGlobalUrl(url){
	globalURl=url;
}
$(function(){
	url="";
	  $('#dashboardDiv').on('hidden.bs.modal', function(){
		  $("#dashboardDiv iframe").attr({'src':url,
		        'height': '0',
		        'width': '0'});
		});
	//  class="dd"
		  $('.navbar-collapse li li a').click('.dd', function() {
	//  $('.navbar-collapse li li a').click('li', function() {
	
		  var navbar_toggle = $('.navbar-toggle');
		  if (navbar_toggle.is(':visible')) {
		      navbar_toggle.trigger('click');
		  }});

		  $('.homeIcon').click('li', function() {
				//  $('.navbar-collapse li li a').click('li', function() {
				
					  var navbar_toggle = $('.navbar-toggle');
					  if (navbar_toggle.is(':visible')) {
					      navbar_toggle.trigger('click');
					  }});
		  
	  
	 
});
function getGlobalUrl(){ 
	if (globalURl.indexOf("NaN") !=-1) { 
	    globalURl=globalURl.replace('NaN','');
	}

	document.getElementById("middleFrame").src=globalURl;
}
var attachmentWin = null;
function fn_CloseWins()
{
	if(attachmentWin != null)
		attachmentWin.close();
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
}; 

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

/*var progress = setInterval(function () {
    var $bar = $('.progress-bar');    
    if (($bar.width() >= 400)) {
        clearInterval(progress);
        $('.progress-bar-striped').removeClass('active');
    } else {
        $bar.width($bar.width() + 40);
    }
    $bar.text($bar.width() / 4 + "%"); 
}, 10);*/
}
$(function(){
	setInterval(function()
			{$('[blink]').fadeOut(50, function()
					{$(this).fadeIn(50, function()
							{$(this).fadeOut(50, function()
									{$(this).fadeIn(50, function()
											{$(this).fadeOut(50, function()
													{$(this).fadeIn(50);});});});});});}, 2000);});
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
	 /*var $bar = $('.progress-bar'); 
	 $bar.width("0"); $bar.text("");
	 clearInterval(progress);*/
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
	document.getElementById("middleFrame").src='login/afterLoginTest.jsp'; 
	var newheight= $( document ).height();
	document.getElementById("middleFrame").style.height=(newheight-(120))+"px"; 
		}
}
function fn_changePassword() {
	var userName = '${userName}';
	document.getElementById("username").value=userName;
	document.getElementById("changePasswordDiv").src='/<%=context%>/loginAction.do?actionFlag=ChangePwd&username='+userName;
}
function fn_changeTrustPassword()
	{
		var userName = '${userName}';
		document.getElementById("username").value=userName;
		document.getElementById("changePasswordDiv").src='/<%=context%>/loginAction.do?actionFlag=ChangePwd&username='+userName;
	}
function fn_ahcClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ahcClaimsAction.do?actionFlag=claimPayments&status=CD385';
	}
function fn_biomOtherVisitsReport()
	{
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/bioMetricAction.do?actionFlag=openVisitorsReport';
	}


function fn_casesSearch()  
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501');
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501'; 
  //document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=viewAhcClaimPage&ahcId=99463';
	}


function fn_pendingCases()  
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=claims&pendingFlag=Y');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=claim&pendingFlag=Y';
	
	}
	
// Added for HEOD Oncology Committee Cases CR#4511-SriHari-03/12/24
function fn_highEndDrugsApproval(){
	fn_loadImage();
	setGlobalUrl('/<%=context%>/patientDetails.do?actionFlag=highEndOncologySearch&casesForApproval=Y');
	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=highEndOncologySearch&casesForApproval=Y';
	
}
//Added by ramalakshmi for DME login cases seacrh
function fn_preauthApprovedCases()
{

	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=preauthApprovedCases&caseSearchType=caseIp&patientScheme=CD501');
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=preauthApprovedCases&caseSearchType=caseIp&patientScheme=CD501'; 
	}

function fn_preauthPendingCases()  
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=preauth&pendingFlag=Y');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=preauth&pendingFlag=Y';
	
	}
	function fn_DMEFlagCases()
	{
		
		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=dmeFlagCases&dmeFlag=Y';
		
	}
	function fn_EOUpdatedCases()
	{
		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=eoUpdatedCases&dmeFlag=N';
	}
function fn_dentalPendingCases()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=preauth&pendingFlag=Y');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&dentalFlag=Y&module=preauth&pendingFlag=Y';
}
function fn_followUpPendingCases()
{

	 fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch&casesForApproval=Y&patientScheme=CD501&pendingFlag=Y';
}



function fn_JournalistCasesSearch()  
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502');
  document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502'; 
  //document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=viewAhcClaimPage&ahcId=99463';
	}



function fn_chronicPaymentsSearch(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getChronicClaimsPaymentRecrds';
	}
function fn_enhancePreauthCaseNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&enhanceflag=Y&module=enhancepreauth&autoCaseId='+autoCaseId;
}
function fn_casesForApproval()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=preauth');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=preauth';
	
	}
	//Added by ramalakshmi for hubspoke CR
	function fn_dialysisCasesForApproval(autoCaseId)
	{
		fn_loadImage();
		 document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&diaFlg=Y&module=preauth&autoCaseId='+autoCaseId;
	}
	function fn_dialysisClaimCasesForApproval(autoCaseId)
	{
		fn_loadImage();
		 document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&diaFlg=Y&module=claim&autoCaseId='+autoCaseId;
	}
	
	
	function fn_dialysisPendingCases()
	{
		fn_loadImage();
		setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&diaPendFlg=Y&module=preauth');
		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&diaPendFlg=Y&module=preauth';
		
	}
	//End of hubSpoke change
//For NABH preauth cases 
function fn_NABHPreauth()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=preauth&nabhCase=Y';
}

function fn_enhancePreauthCase()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=enhancepreauth');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&enhanceflag=Y&module=enhancepreauth';
}
function fn_journalistCasesForApproval()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&module=preauthJournal');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&module=preauthJournal';
	
	}

function fn_userDownloads()
	{
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=downloadUserCSV';
	}


function fn_casesForApprovalClaim()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=claim');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&module=claim';
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
function fn_cochlearCasesForApprovalClaims()
{
	fn_loadImage();
	setGlobalUrl('/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=cochlearAco');
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&module=cochlearAco';
	
}

function fn_casesForApprovalNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&medFlag=O&patientScheme=CD501&module=preauth&autoCaseId='+autoCaseId;
}
function fn_JournalistcasesForApprovalNew(autoCaseId)
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&medFlag=O&casesForApproval=Y&patientScheme=CD502&module=preauthJournal&autoCaseId='+autoCaseId;
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
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&medFlag=O&casesForApproval=Y&patientScheme=CD502&module=claimJournal&autoCaseId='+autoCaseId;
	
}


function fn_errCasesForApproval()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&errSearchType=Y&module=claim';  
}
function fn_dentalErroneousCasesForApprovalClaim()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD501&casesForApproval=Y&errDentalSearchType=Y&module=claim';  
}
function fn_JournalistErrCasesForApproval()
{
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&patientScheme=CD502&casesForApproval=Y&errSearchType=Y&module=claimJournal';  
}  



function fn_caseForApprovalFollowUpNew(autoCaseId)
{

	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=OnloadCaseOpen&casesForApproval=Y&module=followUp&patientScheme=CD501&autoCaseId='+autoCaseId;
}

function fn_followUpCaseSearch(){ 
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch&patientScheme=CD501';
	}
  
  function fn_followUpCaseSearchForApproval(){ 
	  fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch&casesForApproval=Y&patientScheme=CD501';
	}



  function fn_JournalistCaseForApprovalFollowUpNew(autoCaseId)
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=OnloadCaseOpen&casesForApproval=Y&module=followUpJournal&patientScheme=CD502&autoCaseId='+autoCaseId;
  }

  function fn_JournalistFollowUpCaseSearch(){ 
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch&patientScheme=CD502';
  	}
    
    function fn_JournalistFollowUpCaseSearchForApproval(){ 
  	  fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=caseSearch&casesForApproval=Y&patientScheme=CD502';
  	}

	
  function fn_claimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getClaimsPaymentRecrds&caseStatus=CD93';
	}
  
  //dental claim Payments
  function fn_dentalClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getClaimsPaymentRecrds&dentFlg=Y&caseStatus=CD93';
	}
  //For medical claim payments
  function fn_claimPaymentsMed()
  {
	  fn_loadImage();
  	
  	document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getClaimsPaymentRecrds&caseStatus=CD93&patScheme=CD501&nabhCase=Y&flgMed=IPM';
  	
  }
//For surgical claim payments
  function fn_claimPaymentsSurg()
  {
	  fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getClaimsPaymentRecrds&caseStatus=CD93&patScheme=CD501&nabhCase=Y&flgMed=IP';
  	
  }
  
  function fn_RejClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getClaimsPaymentRecrds&caseStatus=CD98';
	}
	
	
  function fn_ErrClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getErrPaymentRecrds&errStatusId=CD109';	
	}
		
  function fn_RejErrClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getErrPaymentRecrds&errStatusId=CD117';	
	}	

	
  function fn_TdsClaimPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getTdsPaymentRecrds&paymentType=CD125';  	
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

  function fn_RejFollowUpPayments(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&followUpStatus=CD108';
		}

	
  function fn_followUpApprovals(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/followUpAction.do?actionFlag=getFPPaymentRecrds&userGrp=GP9&followUpStatus=CD65';
		}
  function fn_panelDocPayments()
	{
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/panelDocPay.do?actionFlag=getCases';
	}
  function fn_panelDocPaymentsSearch()
	{
	  fn_loadImage();
	  document.getElementById("middleFrame").src='/<%=context%>/panelDocPay.do?actionFlag=panelDocPayHome&searchFlag=Y';
	}
	
	function fn_panelDocPaymentsForCEO()
	{
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/panelDocPay.do?actionFlag=getPanelDocPaymentRecrds';
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
  function fn_maWorklistByJeo(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getMAworklist&backFlag=no&cmaDeoFlag=N';
		
	}
  function fn_maWorklistByDeoToCma(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getMAworklist&backFlag=no&cmaDeoFlag=Y';
		
	}
  
  function fn_maWorklistByCma(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getMAworklist&backFlag=no&cmaDeoFlag=C';
		
	}
  
  function fn_maAuditedCases(){
		fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/medicalAudit.do?actionFlag=getAuditedCaseslist&backFlag=no';
		
	}
  function fn_CasesForDiss(){
	  fn_loadImage();
		document.getElementById("middleFrame").src='/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=caseIp&casesForApproval=Y&disSearchType=Y&module=claim';
	}
  function showCMS()
	{	
	   //document.getElementById("cmdFrameId").src='http://cms.ehf.gov.in/CMS';
	   var url="https://ehf.telangana.gov.in/CMS";
	   var child= window.open( url,'ChangeManagement','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
	}
  function showSendBackCases()
  	{
	  	var url="https://ehf.telangana.gov.in/AdminTG/userSendBack.htm";
	  	window.open(url,"_blank",'width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
  	}
  function fn_registerPatient()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=openPatRegForm';
  }
  //pravalika - 8755
   function fn_registerPatientDJHS()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=openPatRegFormDLH';
  }
  function fn_viewRegisteredPatients()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=ViewRegisteredPatients&advSearch=false';
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
  function fn_opRegPatientsView()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getOPCases&advSearch=false&patientScheme=CD501';
  }
  function fn_opRegPatientsViewJHS()
  {
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getOPCases&advSearch=false&patientScheme=CD502';
  }
  function fn_opdClaimsCases(){
		fn_loadImage();
		<%-- setGlobalUrl('/<%=context%>/patientDetails.do?actionFlag=getOpdClaimCasesList'); --%>
	 	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getOpClaimCasesList'; 
	}
  //Chandana - 8036 - For PEX and CH mod url function
  function fn_NimsOpdClaimCasesforApproval(){
	  fn_loadImage();
	 <%-- document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getNimsOpClaimCasesforApproval&status=CD7303'; --%>
	 document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=getNimsOpClaimCasesforApproval';
  }
//Chandana - 9033 - Added below function for CEX and CH
  function fn_DlhJrnlstCasesforApproval(){
	  fn_loadImage();
	 document.getElementById("middleFrame").src='/<%=context%>/patientDetailsNew.do?actionFlag=getDlhClaimCasesforApproval';
  }
//Chandana - 9033 - Added below function for ACO
  function fn_DlhJrnlstCasesforApprovalACO(){
	  fn_loadImage();
	 document.getElementById("middleFrame").src='/<%=context%>/patientDetailsNew.do?actionFlag=getDlhClaimsforACOApproval&ajaxCall=N';
  }
//Chandana - 8602 - new function for NIMS OP Claim ases for approvals mod
  function fn_NimsOpdClaimsforACOApproval(){
	  fn_loadImage();
	 document.getElementById("middleFrame").src='/<%=context%>/ClaimsFlow.do?actionFlag=getNimsOpdClaimCasesforACOApproval';
  }
//Chandana - 8755 - New function for getting the list of delhi journalist cases list
function fn_dlhJrnlstClaimsCases(){
	fn_loadImage();
	document.getElementById("middleFrame").src='/<%=context%>/patientDetailsNew.do?actionFlag=getDlhJrnlstClaimCasesList&ajaxCall=N'; 
}
function fn_chronicOPPatientsView(){
	//document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=viewChronicOPPatients';
	setGlobalUrl('/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView&regCaseFlg=Y');
	document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView&regCaseFlg=Y';
	fn_loadImage();
}

function fn_chronicOPCasesView(){
	fn_loadImage();
	setGlobalUrl('/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView');
	document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView';
	
}
function fn_coClaimCases(){
	fn_loadImage();
	setGlobalUrl('/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView&caseApprovalFlag=Y');
	//document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=COClaimCases';
	document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView&caseApprovalFlag=Y';
	
}

function fn_coClaimCasesPending(){
	fn_loadImage();
	setGlobalUrl('/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView&caseApprovalFlag=Y&pendingFlag=Y');
	//document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=COClaimCases';
	document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=chronicOPCasesView&caseApprovalFlag=Y&pendingFlag=Y';
	
}
function fn_chronicCasesForApprovalNew(autoCaseId)
{
	fn_loadImage();
	
	document.getElementById("middleFrame").src='/<%=context%>/chronicAction.do?actionFlag=OnloadCaseOpen&caseSearchType=caseIp&casesForApproval=Y&module=preauth&autoCaseId='+autoCaseId;
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
  function fn_annualRegisteredPatientsView(){
	  fn_loadImage();
	  setGlobalUrl('/<%=context%>/annualCheckUpAction.do?actionFlag=getAnnualPatientsView');
	  document.getElementById("middleFrame").src='/<%=context%>/annualCheckUpAction.do?actionFlag=getAnnualPatientsView';
	
	  }
  function fn_telephonicEntry(){
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/patientDetails.do?actionFlag=telephonicEntry';
  }
  function fn_pnlDocBankDtls()
  {
fn_loadImage();
document.getElementById("middleFrame").src='/<%=context%>/panelDoc.do?actionFlag=panelDocSearch';
  } 
  function fn_telephonicIntimation(){
  	 fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/telephonicAction.do?actionFlag=telephonicIntimationSearch';
  }
  function fn_telephonicIntimationRaised(){
  	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/telephonicAction.do?actionFlag=telephonicIntimationRaised';
  }
  function fn_viewFlaggedCases()
  {
	  fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/flaggingAction.do?actionFlag=viewFlaggedCases&flaggedCasesForApproval=N&newFlag=N';  
  }
  function fn_flaggedCasesForApproval()
  {
	  fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/flaggingAction.do?actionFlag=viewFlaggedCases&flaggedCasesForApproval=Y&newFlag=Y';  
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
  function fn_openSchedulerLinks()
	  {
		  fn_loadImage();
	  	  document.getElementById("middleFrame").src='/<%=context%>/schedulersAction.do?actionFlag=openSchedulers';  
	  }
  	function showDashboard(){
  		 var url='http://stage.ehf.gov.in/HomePage/noOfPostsAction.do?actionFlag=getDashBoard&schemeType=CD203';
  		 $("#dashboardDiv iframe").attr({'src':url,
 	        'height': '100%',
 	        'width': '100%'});
  	  }
	  function refresh()
	  {
		 // alert(document.URL);
		  //window.location.assign(document.URL);

		  document.forms[0].action='/<%=context%>/loginAction.do?actionFlag=checkLogin&refreshFlag=Y';
		  document.forms[0].method="post";
		  document.forms[0].submit();
		 // window.location.href = window.location.protocol +'//'+ window.location.host + window.location.pathname;
		  //window.location = window.location.href;
//window.location.reload();
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
function fn_cardSearch()
{
	fn_loadImage();
  	document.getElementById("middleFrame").src='/<%=context%>/cardSearchAction.do?actionFlag=cardSearch';  
}
</script>
</head>
<body onload="javascript:fn_diableClose();redirectDefaultPage();">
<html:form  method="post"  action="/loginAction.do" >
<html:hidden property="username" name="loginForm" styleId="username" />
<header> 
<div class="navbar navbar-default page-header">
	<div class="pull-left">
      <h1>Employees Health Scheme</h1>
    </div>
    <div class="navbar-buttons pull-right" role="navigation">
    <ul class="nav custom-nav">
                       
	                       <c:if test="${isMedco ne 'Y' && DsgId ne 'DG1046'}">
	                       <li >
								<a href="" onclick="javascript:showCMS();" data-toggle="modal" title="CMS">
									<span class="glyphicon glyphicon-cog"></span>CMS
								</a>
							</li> </c:if>
							<c:if test="${DsgId ne 'DG1046'}">
								<li >
									<a href="" onclick="javascript:showSendBackCases();" data-toggle="modal" title="Click to Open CEO SendBack Cases">
										<span class="glyphicon glyphicon-cog"></span>CEO SendBack Cases
									</a>
								</li>
							</c:if>
							
							<c:if test="${DsgId eq 'DG1046'}">
								<li >
									<a>	<span >No of Visits : ${vistorCount} </span></a>
								</li>
							</c:if>
								 
						<c:if test="${ldapLoginFlag ne 'true'}">	
							<c:if test="${showDashboard}">
							
							
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" title="Dashboards List">
									<span class="glyphicon glyphicon-list-alt"></span> Dashboard
								</a>
	
								<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close" id="dashboardList">
										<li class="dropdown-header"><i class="glyphicon glyphicon-tasks"></i> Dashboards List</li>
										
										<li>
										<a data-target="#dashboardDiv" data-toggle="modal" onclick="javascript:showDashboard();" href="#" title="Click to open Operations Dashboard"><span style="color:#000;font-size:0.9em;">Operations Dashboard</span></a>
										</li>
										
								</ul>
							</li>
							
							</c:if>
						</c:if>	
									<li >
											<a href="#changePassword" onclick="javascript:fn_changeTrustPassword()" data-toggle="modal" title="Change Password">
												<span class="glyphicon glyphicon-lock">
												</span> 
													Change Password
											</a>
									</li>
					<!--	<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" title="Modules List">
								<span class="glyphicon glyphicon-tasks"></span> Modules
							</a>
							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close" id="modulesList">
									<li class="dropdown-header"><i class="glyphicon glyphicon-tasks"></i> Modules List</li>
									<c:forEach items="${moduleList}" varStatus="loop">
									<c:set var="tmpModVO" value="${moduleList[loop.index]}" />
									<li id=${tmpModVO.ID}>
									<a href="javascript:fn_application('${tmpModVO.VALUE}')"  title="Click to open ${tmpModVO.ID} module">${tmpModVO.ID}</a>
									</li>
									</c:forEach>	
							</ul>
						</li> -->

						<li class="dropdown" style="display:none;" id="medcoNotice">
							<a href="#dialog-MEDCO" class="dropdown-toggle" data-toggle="modal" title="Notice">
								
								
									<c:set var="NoOfNotifications" scope="session" value="${NoOfNotifications}"/>				
								
								<span class="glyphicon glyphicon-bell red "></span>
							
							
							<c:choose>
                <c:when test="${NoOfNotifications > 0}">
               <span  blink class="badge badge-important red"><bean:write name="loginForm" property="noOfNotifications"/></span>
             
                </c:when>
            <c:otherwise>
              <span   class="badge badge-important red"><bean:write name="loginForm" property="noOfNotifications"/></span>
             </c:otherwise>
            </c:choose>
							
							
							</a>
						</li> 

						<!--<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<span class="glyphicon glyphicon-envelope"></span>
								<span class="badge badge-success">5</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="glyphicon glyphicon-envelope"></i>
									5 Messages
								</li>
							</ul>
						</li>

						--><li class="dropdown">
							<a class="dropdown-toggle" href="#" data-toggle="dropdown">
								<span class="user-info">
									<small>Welcome,${fullName}</small><br>
									<c:choose>
										<c:when test="${loggedUserState eq 'CD201' &&
													 (dsgnName eq 'MITHRA' || dsgnName eq 'Aarogya Mithra' || dsgnName eq 'Mithra' || dsgnName eq 'NAM' )}" >
											VAIDYA MITHRA
										</c:when>
										<c:otherwise >
											${dsgnName}
										</c:otherwise>
									</c:choose> 
								</span>
								<span class="caret"></span>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-caret dropdown-close">
								
								<!--<li>
									<a href="#">
										<i class="glyphicon glyphicon-cog"></i>
										Settings
									</a>
								</li>

								<li>
									<a href="#">
										<i class="glyphicon glyphicon-user"></i>
										Profile
									</a>
								</li>

								<li class="divider"></li>
-->
								<li>
									<a href="javascript:fn_logout()">
										<i class="glyphicon glyphicon-off"></i>
										Logout
									</a>
								</li>
							</ul>
						</li>
					</ul>
    </div>
</div>
</header>
<nav class="navbar navbar-default navbar-collapse" role="navigation">  
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button id="collapseBtn" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="nav navbar-nav">
      	<li class="homeIcon"><a href="javascript:redirectDefaultPage();" ><span class="glyphicon glyphicon-home"></span></a></li>
		<c:forEach items="${ActionbarModulesLst}" varStatus="loop">	
				<c:set var="tmpModVO" value="${ActionbarModulesLst[loop.index]}" />
				<c:if test="${fn:length(tmpModVO.listSubMenu) gt 0}">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> ${tmpModVO.MODNAME}<span class="caret"></span></a>
				</c:if> <c:if test="${fn:length(tmpModVO.listSubMenu) eq 0}">
				<li><a href="${tmpModVO.MODURL}" data-toggle="collapse" data-target="#navbarCollapse"> ${tmpModVO.MODNAME}</a>
				</c:if>
				<c:set var="lSubMenuList" value="${tmpModVO.listSubMenu}" />
				<c:if test="${fn:length(lSubMenuList) gt 0}">
					<ul class="dropdown-menu multi-level" role="menu">
						<c:forEach items="${lSubMenuList}" varStatus="status">
						
							<c:set var="tmpSubModVO" value="${lSubMenuList[status.index]}" />
							
							<c:if test="${fn:length(tmpSubModVO.listSubMenu) gt 0}">
								<li class="dropdown-submenu"><a href="#" > ${tmpSubModVO.MODNAME} </a>
								
							</c:if> <c:if test="${fn:length(tmpSubModVO.listSubMenu) eq 0}">
								<li class="dd"><a href="${tmpSubModVO.MODURL}" > ${tmpSubModVO.MODNAME} </a>
							</c:if>
							<c:set var="lSecndLvlMenuList" value="${tmpSubModVO.listSubMenu}" />
							<c:if test="${fn:length(lSecndLvlMenuList) gt 0}">
								<ul class="dropdown-menu">
									<c:forEach items="${lSecndLvlMenuList}" varStatus="statusTwo">
				 					
										<c:set var="tmpSecondVO"
											value="${lSecndLvlMenuList[statusTwo.index]}" />
						 				<c:if
											test="${fn:length(tmpSecondVO.listSubMenu) gt 0}">
											<li class="dropdown-submenu"><a href="#"> ${tmpSecondVO.MODNAME} </a>
				 						</c:if> <c:if test="${fn:length(tmpSecondVO.listSubMenu) eq 0}">
											<li><a href="${tmpSecondVO.MODURL}" class="dd"> ${tmpSecondVO.MODNAME} 
											</a>
										</c:if>
										<c:set var="lThirdLvlMenuList"
											value="${tmpSecondVO.listSubMenu}" />
										<c:if test="${fn:length(lThirdLvlMenuList) gt 0}">
											<ul class="dropdown-menu">
												<c:forEach items="${lThirdLvlMenuList}"
													varStatus="statusThree">
													
													<c:set var="tmpThirdVO"
														value="${lThirdLvlMenuList[statusThree.index]}" />
													<c:if
														test="${fn:length(tmpThirdVO.listSubMenu) gt 0}">
														<li class="dropdown-submenu"><a href="#"> ${tmpThirdVO.MODNAME} </a>
													</c:if> <c:if test="${fn:length(tmpThirdVO.listSubMenu) eq 0}">
														<li><a href="${tmpThirdVO.MODURL}" class="dd">
														${tmpThirdVO.MODNAME} </a>
													</c:if>
													<c:set var="lFourthLvlMenuList"
														value="${tmpThirdVO.listSubMenu}" />
													<c:if test="${fn:length(lFourthLvlMenuList) gt 0}">
														<ul class="dropdown-menu">
															<c:forEach items="${lFourthLvlMenuList}"
																varStatus="statusFour">
																
																<c:set var="tmpFourthVO"
																	value="${lFourthLvlMenuList[statusFour.index]}" />
																<c:if
																	test="${fn:length(tmpFourthVO.listSubMenu) gt 0}">
																	<li class="dropdown-submenu"><a href="#"> ${tmpFourthVO.MODNAME} </a>
																</c:if> <c:if test="${fn:length(tmpFourthVO.listSubMenu) eq 0}">
																	<li><a href="${tmpFourthVO.MODURL}" class="dd">
																	${tmpFourthVO.MODNAME} </a>
																</c:if>
																</li>
															</c:forEach>
														</ul>
														
													</c:if>
													<c:set var="lFifthLvlMenuList"
														value="${tmpFourthVO.subMenuLst}" />
													<c:if test="${fn:length(lFifthLvlMenuList) gt 0}">
														<ul class="dropdown-menu">
															<c:forEach items="${lFifthLvlMenuList}"
																varStatus="statusFive">
																
																<c:set var="tmpFifthVO"
																	value="${lFifthLvlMenuList[statusFive.index]}" />
																<c:if
																	test="${fn:length(tmpFifthhVO.subMenuLst) gt 0}">
																	<li class="dropdown-submenu"><a href="#"> ${tmpFifthVO.MODNAME} </a>
																</c:if> <c:if test="${fn:length(tmpFifthVO.subMenuLst) eq 0}">
																	<li><a href="${tmpFifthVO.MODURL}" class="dd">
																	${tmpFifthhVO.MODNAME} </a>
																</c:if>
																</li>
															</c:forEach>
														</ul>
														
													</c:if>
													<c:set var="lSixthLvlMenuList"
														value="${tmpFifthVO.subMenuLst}" />
													<c:if test="${fn:length(lSixthLvlMenuList) gt 0}">
														<ul class="dropdown-menu">
															<c:forEach items="${lSixthLvlMenuList}"
																varStatus="statusSix">
																
																<c:set var="tmpSixthVO"
																	value="${lSixthLvlMenuList[statusSix.index]}" />
																<c:if
																	test="${fn:length(tmpFifthhVO.subMenuLst) gt 0}">
																	<li class="dropdown-submenu"><a href="#"> ${tmpSixthVO.MODNAME} </a>
																</c:if> <c:if test="${fn:length(tmpSixthVO.subMenuLst) eq 0}">
																	<li><a href="${tmpSixthVO.subMenuLst}" class="dd">
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
	</div><!-- /.navbar-collapse -->
</nav>
<!--<aside id="sideLinks">
<div>
<ul>
 <li><a href="#changeTheme" data-toggle="modal" title="Change Theme"><span class="glyphicon glyphicon-list-alt"></span> Change Theme</a></li>
<li><a href="#changePassword" onclick="javascript:fn_changePassword()" data-toggle="modal" title="Change Password"><span class="glyphicon glyphicon-cog"></span> Change Password</a></li>
<li><a href="javascript:fn_logout()" title="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a> </li> 
</ul>
</div>
</aside>-->
<!-- Modal for medco  --> 
<div class="modal fade" id="dialog-MEDCO"  > 
  <div class="modal-dialog" > 
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title" >Notifications</h2>
      </div>
            <div class="modal-body" style="overflow-y:scroll;height:500px;align:center" >
        <table  style="vertical-align: middle;width:100%;align:center">
        	<tr>
			<td border="1px" style="color:black;">
		<table style="width:99%" >
		
		<tr id="clinic-dialog2" style="display:none;">
			<c:choose>
			<c:when test="${fn:length(cancledCasesList) gt 0}">
				<td><b><font size="3" color="red">Following cases are going to be auto cancelled please update the status</font>:</b></td>
			</c:when>
			<c:otherwise>
				<td><b>No cases are going to be auto cancelled.</b></td>
			</c:otherwise>
			</c:choose>
			
	     </tr></table></td>
		</tr>
		<tr id="clinic-dialog3" style="display:none;">
			<td border="1px" style="color:black;" >
				<table style="width:99%" border="1px"  >
					<c:forEach items="${cancledCasesList}" var="caseslist" varStatus="x">
						<c:if test="${x.count%5 eq 1}">
						
						<tr>	
						</c:if>
							<td border="1px" style="color:black;">
							<a href="javascript:fn_caseApprovalAjax('${caseslist.CASEID}','','${caseslist.PATIENTTYPE}','');" title="Click to view case details"> 
						 <b > ${caseslist.CASEID} </b></a>
							</td>
					</c:forEach>
				</table>
			</td>
		</tr>

		<tr>
			<td border="1px" style="color:black;">
		<table style="width:99%" >
		<tr id="clinic-dialog" style="display:none;">
			<c:choose>
			<c:when test="${fn:length(ClinicalNotesCasesList) gt 0}">
				<td><b><font size="3" color="#ff6666">Please enter Clinical Notes for the following cases</font>:</b></td>
			</c:when>
			<c:otherwise>
				<td><b>No case is pending for updation of clinical notes today </b></td>
			</c:otherwise>
			</c:choose>
			
	     </tr></table></td>
		</tr>
		
		<tr id="clinic-dialog1" style="display:none;">
			<td border="1px" style="color:black;" >
				<table style="width:99%" border="1px"  >
					<c:forEach items="${ClinicalNotesCasesList}" var="list" varStatus="x">
						<c:if test="${x.count%5 eq 1}">
						
						<tr>	
						</c:if>
							<td border="1px" style="color:black;">
							<a href="javascript:fn_caseApprovalAjax('${list.ID}','','IP','preauth');" title="Click to view case details"> 
								<b > ${list.ID} </b></a> 
							</td>
					</c:forEach>
				</table>
			</td>
		</tr>
		
		<tr>
		<td>
		
		<table style="width:99%">
			
		<c:choose>
			<c:when test="${fn:length(userMsgList) eq 0}">
				<b></b>
			</c:when>
			<c:otherwise>
			
			
			<c:forEach items="${userMsgList}" var="list1" varStatus="x1">
			
			<tr >	
                    <td style="align:center">
                    <h4 style="color:green;font-family:Raavi"><span style="color:red;"><b>|</b></span><b> &nbsp; ${list1.NATURE}</b></h4> </td></tr> 
                              
                    
		          <tr >	
                    <td >
								<b > ${list1.VALUE}</b> <span class="active" style="color:grey;">For More Info:${ list1.NATUREID}</span>
							</td></tr>
      
   <td>----------------------------------------------------------------------------------------------------------------------------------------------</td>
							
					</c:forEach>
				
			</c:otherwise>
			</c:choose>
		
		</table>
		
		</td>
		</tr>
		
		
	
		<tr>
		<td>
		<table style="border:1px;width:99%">
			
		<c:choose>
			<c:when test="${fn:length(grpMsgList) eq 0}">
				<b></b>
			</c:when>
			<c:otherwise>
			
			
			<c:forEach items="${grpMsgList}" var="list2" varStatus="x2">
			
			<tr >	
                    <td >
                    <h4 style="color:green;font-family:Raavi"><span style="color:red;"><b>|</b></span><b> &nbsp; ${list2.NATURE}</b></h4>  
								
							</td></tr>
							
							
		          <tr >	
                    <td >
								<b > ${list2.VALUE} </b><span class="active" style="color:grey;">For More Info:${ list2.NATUREID}</span>
							</td></tr>
          
   <td>----------------------------------------------------------------------------------------------------------------------------------------------</td>
							
					</c:forEach>
				
			</c:otherwise>
			</c:choose>
		
		</table>
		</td>
		</tr>
		
		
		
		<tr>
		<td>
		<table style="border:1px;width:99%">
			
		<c:choose>
			<c:when test="${fn:length(hospMsgList) eq 0}">
				<b></b>
			</c:when>
			<c:otherwise>
			
			
			<c:forEach items="${hospMsgList}" var="list3" varStatus="x3">
			
			
			 <tr style="border:1px;">	
                    <td style="align:center">
                    <h4 style="color:green;font-family:Raavi"><span style="color:red;"><b>|</b></span><b> &nbsp; ${list3.NATURE}</b></h4>  
								
							</td></tr>
			
			
		          <tr style="border:1px;">	
                    <td style="align:center">
								<b> ${list3.VALUE} </b><span class="active" style="color:grey;">For More Info:${ list3.NATUREID}</span>
							</td></tr>
          
    <td>----------------------------------------------------------------------------------------------------------------------------------------------</td>
					
					</c:forEach>
				
			</c:otherwise>
			</c:choose>
		
		</table>
		</td>
		</tr>
	
		
	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->
<!-- Modal for change theme  --> 
<div class="modal fade" id="changeTheme"> 
  <div class="modal-dialog"> 
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Change Theme</h2>
      </div>
      <div class="modal-body">
        <ul class="themes">
        <li class="theme1"><a href="#" onclick="fn_changeTheme('default');">default</a></li>
        <li class="theme2"><a href="#" onclick="fn_changeTheme('navyblue');">blue</a></li>
        <li class="theme3"><a href="#" onclick="fn_changeTheme('darkgreen');">green</a></li></ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->





<!--  modal for showing attachments -->
<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="attachModal" >
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content">
 <div class="modal-header"style="height: 75px;color:#FFF;">
<span > </span><label>CIRCULAR TO NWH ON AUTO DEDUCTION FOR NON UPDATION OF CLINICAL NOTE UNDER EJHS</label>
</div>
 

 <div class="modal-body" style="height: 410px;">

 <iframe  id="attachFrameLatest" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <p align="left">
  <html:checkbox property="checkClose"  styleId="checkClose"  onchange="fn_enableClose();"/><b> I have read the circular and agree to terms and conditions.</b></p>
 <button type="button" class="btn btn-warning" data-dismiss="modal" id="closeBut" onclick="fn_enableClose();fn_showCRImg();fn_diableClose1();">Close</button>
 
  </div>
 </div></div></div>




<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="attachModalLatest" >
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content">
 <div class="modal-header"style="height: 75px;color:#FFF;">
<span > </span><label>Revised Time line validation for pending Updation of preauthorization, Surgery update, Discharge update, Claim doctor pending, JEO Pending and EO pending claims of both Aarogyasri and EHS cases (Click here for details)</label>
</div>
 

 <div class="modal-body" style="height: 410px;">

 <iframe  id="attachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <p align="left">
  <html:checkbox property="checkClose"  styleId="checkClose1"  onchange="fn_enableClose1();"/><b> I have read the circular and agree to terms and conditions.</b></p>
 <button type="button" class="btn btn-warning" data-dismiss="modal" id="closeBut1" onclick="fn_enableClose1();fn_getNewAttach();fn_diableClose2();">Close</button>
 
  </div>

 </div></div></div>









<!-- end of attachment modal  -->
<!--  modal for showing attachments -->
<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="attachModalNew" >
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content">
 <div class="modal-header"style="height: 75px;color:#FFF;">
<span > </span><label>CIRCULAR ON MEDICAL MANAGMENET CASES BEYOND RS.50,0000 FOR NON CRITICAL CASES AND RS.1,00,000 FOR CRITICAL CASES BY NABH SUPER SPECIALITY HOSPITALS UNDER EJHS</label>
</div>
 

 <div class="modal-body" style="height: 410px;">

 <iframe  id="attachFrameNew" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <p align="left">
  <html:checkbox property="checkClose"  styleId="checkClose2"  onchange="fn_enableClose2();"/><b> I have read the circular and agree to terms and conditions.</b></p>
 <button type="button" class="btn btn-warning" data-dismiss="modal" id="closeBut2" onclick="fn_enableClose2();fn_loadClinincalNotes();">Close</button>
 
  </div>
 </div></div></div>

<!-- end of attachment modal  -->


<!-- Modal for change password  -->
<div class="modal fade" id="changePassword"> 
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Change Password</h2>
      </div>
      <div class="modal-body">
        <iframe id="changePasswordDiv" frameborder="0" width="100%" height="420px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog --> 
</div><!-- /.modal -->
<!-- Progress Bar -->
<div id="processImagetable" style="top:50%;position:absolute;z-index:60;height:100%">
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
<div>
  <iframe id="middleFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0" onload="javascript:fn_removeLoadingImage();"></iframe>
</div>
<footer>
<div id='footer'><span class="pull-left">Employees Health Scheme </span>
<span class="pull-right">© 2014 All Rights reserved. Aarogyasri Health Care Trust</span>
<span class="clearfix"></span></div>
</footer>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>

function fn_getClinicData(caseId)
{
	var url="/<%=context%>/clinicalNotesAction.do?actionFlag=clinicalNotes&caseId="+caseId+"&caseApprovalFlag=Y";
	window.open(url,"_blank",'width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
}

function fn_caseApprovalAjax(caseId,arg,ipOpType,module)
{	

	fn_loadImage();
	if(parent.parent.globalURl==''){
		 caseSearchURl="/<%=context%>/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&module="+module+"&casesForApproval=Y";
		 parent.setGlobalUrl(caseSearchURl);
	}
	//parent.fn_resizePage2(470);
	var flag = null;
	var xmlhttp;
	var url;
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 
	
	 url = '/<%=context%>/casesSearchAction.do?actionFlag=setViewFlag&CaseId='+caseId+'&flag=Y';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {

		    	var url="/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId="+caseId+"&status="+arg+"&casesForApproval=Y&ipOpType="+ipOpType+"&module="+module;
			  	window.open(url,"_blank",'width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
		    	   	
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		
	
}

function fn_showCRImgNew()
{
	var url='/<%=context%>/loginAction.do?actionFlag=viewAttachment&newAttchFlg=A';
	document.getElementById("attachFrameLatest").src=url;
}

function fn_showCRImg()
{
	
	$('#attachModalLatest').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	},'show');
	var url='/<%=context%>/loginAction.do?actionFlag=viewAttachment&newAttchFlg=N';
	document.getElementById("attachFrame").src=url;
}

function fn_getNewAttach()
{
	$('#attachModalNew').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	},'show');

	var url='/<%=context%>/loginAction.do?actionFlag=viewAttachment&newAttchFlg=Y';
	document.getElementById("attachFrameNew").src=url;
	
}

 function fn_diableClose()
{
	  document.getElementById("closeBut").disabled = true;
	  document.getElementById("checkClose").checked = false;	  
}
 
function fn_enableClose()
{
	if(document.getElementById("checkClose").checked==true)
	{
	document.getElementById("closeBut").disabled = false;
	}
	if(document.getElementById("checkClose").checked==false)
	{
	document.getElementById("closeBut").disabled = true;
	}
}

function fn_diableClose1()
{
	  document.getElementById("closeBut1").disabled = true;
	  document.getElementById("checkClose1").checked = false;	  
}

function fn_enableClose1()
{
	if(document.getElementById("checkClose1").checked==true)
	{
	document.getElementById("closeBut1").disabled = false;
	}
	if(document.getElementById("checkClose1").checked==false)
	{
	document.getElementById("closeBut1").disabled = true;
	}
}

function fn_diableClose2()
{
	  document.getElementById("closeBut2").disabled = true;
	  document.getElementById("checkClose2").checked = false;	  
}

function fn_enableClose2()
{
	if(document.getElementById("checkClose2").checked==true)
	{
	document.getElementById("closeBut2").disabled = false;
	}
	if(document.getElementById("checkClose2").checked==false)
	{
	document.getElementById("closeBut2").disabled = true;
	}
}


function fn_loadClinincalNotes()
{
	if('${flaggedCases}'>0)
		alert("Few cases have been flagged. Please view the path Cases > View Flagged Cases and coordinate with DC to Deflag");
	
	document.getElementById("medcoNotice").style.display="";
	document.getElementById("clinic-dialog").style.display="";
	document.getElementById("clinic-dialog1").style.display="";
	if(document.getElementById("clinic-dialog2")!=null)
	document.getElementById("clinic-dialog2").style.display="";
	if(document.getElementById("clinic-dialog3")!=null)
	document.getElementById("clinic-dialog3").style.display="";
	
	$('#dialog-MEDCO').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	},'show');
	
}
if('${dsgnName}'!=null && '${dsgnName}'!="" && '${dsgnName}'=="MEDCO"){

	
	$('#attachModal').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	},'show');
	fn_showCRImgNew();

}else{
	
	document.getElementById("medcoNotice").style.display="";
	document.getElementById("clinic-dialog").style.display="none";
	document.getElementById("clinic-dialog1").style.display="none";
	
	if(document.getElementById("clinic-dialog2")!=null)
	document.getElementById("clinic-dialog2").style.display="none";
	if(document.getElementById("clinic-dialog3")!=null)
	document.getElementById("clinic-dialog3").style.display="none";
	

	if('${showNotifications}'){

		$('#dialog-MEDCO').modal({
			backdrop : 'static',
			keyboard : false,
			show : true
		},'show');
		$('#canceldCases-MEDCO').modal({
			backdrop : 'static',
			keyboard : false,
			show : true
		},'show');

		}
	

}
</script>
</html:form>
<div class="modal fade bs-example-modal-md" id="dashboardDiv"  tabindex="-1" role="dialog"  aria-hidden=true style="width:100%;height:100%;padding:0px;margin:0px;">
	<div class="modal-dialog modal-md " style="width:100%;height:100%;padding:0px;margin:0px;">
	
    	<div class="modal-content " style="width:100%;height:100%;padding:0px;margin:0px;" >
   	 		<div class="modal-body " style="width:100%;height:100%;padding:0px;margin:0px;" >
   				<iframe src="/"  class="embed-responsive-item" seamless="" width=" 100%" height="100%" ></iframe>
   			 </div>
   			 
		</div>
		
    </div>
</div>
</body>
</html>