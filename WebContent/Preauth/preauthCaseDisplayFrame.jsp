 <%@ page contentType="text/html;charset=windows-1252"%>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("pragma","no-cache");
    response.setDateHeader("Expires", 0);    
%>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jquery-1.9.1.min.js"></script>  

<title>:: Cases Display Frame ::</title>    
<script type="text/javascript">	

  var topMaxFlag=0;
    var rightMaxFlag=0;
    var ipRefershFlag='';
    
    var ff = '';
    var fc = '';
    var sf = ''; 
    var lf = '';
    
 var caseApprovalFlag = '${casesForApprovalFlag}';
 var errSearchType='${errSearchType}';
 var denErrSearchType='${denErrSearchType}';
 var errDentalSearchType='${errDentalSearchType}';
 var  disSearchType='${disSearchType}';
 var  module='${module}';

 var fromPage= '${fromPage}';
 var autoActionFlag = '${autoActionFlag}';
 var timerTime = '${timeOutCount}';
 
 var ipOpType= '${ipOpType}';
 /* sai krishna:#CR8566:flag to hide the dialysis cycle to hide for other than M6 */
 var speciality='${speciality}';
 console.log("sai krishna:"+speciality);
 var onlineCaseSheetFlag= '${onlineCaseSheetFlag}';
 var caseId = '${CaseId}'; 
 var fromCMS= '${fromCMS}';
 var flagColour='${flagColour}';
 var flagged='${flagged}';
 var clinicalTab='${clinicalTab}';
 var ceoFlag='${ceoFlag}';

 
    function fn_maxmizeRight(){
    	if(rightMaxFlag==0){
	document.getElementById("subFrame").cols="*,0";
	rightMaxFlag=1;
}
else{
	document.getElementById("subFrame").cols="*,16%";
	rightMaxFlag=0;
}	
}

function fn_maxmizeTop(){
	if(fromCMS!=null && fromCMS!='' && fromCMS=='Y')
	{	
	}
	else
		parent.getGlobalUrl();
}
function fn_ipRefresh()
{
	ipRefershFlag='Y';
	topFrame.location.reload();
}
function fn_getCaseForApproval()
{
	if(errSearchType!=null && errSearchType=='Y')
		parent.fn_errCasesForApproval();  
	else if((denErrSearchType!=null && denErrSearchType=='Y') || errDentalSearchType!=null && errDentalSearchType=='Y')
		parent.fn_dentalErroneousCasesForApprovalClaim();  
	else
	    parent.fn_casesForApprovalClaim();  
}
function fn_casesForApprovalClaim()
{ 
	parent.fn_casesForApprovalClaim();    
}
function fn_getCaseForDisscussion()
{
	parent.fn_CasesForDiss();  
}
function fn_casesForApprovalNew(){
	
	parent.fn_casesForApprovalNew(caseId);
}
function fn_casesForApprovalClaimNew(){
	
	parent.fn_casesForApprovalClaimNew(caseId);
}
/**
 * Added by ramalakshmi for hubspoke CR
 */
 function fn_dialysisCasesForApproval()
 {
	 parent.fn_dialysisCasesForApproval();
 }
 //End of hubspoke CR
function sessionExpireyClose()
{
	parent.fn_logout();
}
function fn_journalistCasesForApproval()
{
	parent.fn_journalistCasesForApproval();
}
function fn_JournalistcasesForApprovalClaim()
{
	parent.fn_JournalistcasesForApprovalClaim();
}
function fn_JournalistcasesForApprovalNew()
{
	parent.fn_JournalistcasesForApprovalNew(caseId);
}
function fn_JournalistcasesForApprovalClaimNew()
{
	parent.fn_JournalistcasesForApprovalClaimNew(caseId);
}
function fn_dentalCasesForApprovalClaimNew()
{
	parent.fn_dentalCasesForApprovalClaimNew(caseId);
}
function fn_dentalCasesForApprovalClaim()
{
	parent.fn_dentalCasesForApprovalClaim();
}
function fn_JrnlstDentalCases()
{
	parent.fn_JrnlstDentalCases();
}
function fn_JrnlstDentalCases()
{
	parent.fn_JrnlstDentalCases(caseId);
}
function fn_casesForApprovalOrg(){
	
	parent.fn_casesForApprovalOrg(caseId);
}
function fn_casesForApprovalOrgClaim(){
	
	parent.fn_casesForApprovalOrgClaim(caseId);
}

function fn_refresh()
{
window.opener.fn_refresh();
self.close();
}
function resizeframe() 
{
  ff = document.getElementById ? document.getElementById('topFrame') : document.all['topFrame'];
  fc = ff.contentDocument ? ff.contentDocument : document.frames('topFrame').document;
  sf = document.getElementById('mainFrame'); 
  lf = 610-fc.body.offsetHeight;
  sf.rows = fc.body.offsetHeight + ", *";
 
}
var attachmentWin = null;
function fn_CloseWins()
{
	//parent.fn_CloseWins();
	if(attachmentWin != null)
		attachmentWin.close();
}
</script>
</head>
<frameset rows="0,100%" frameborder="0"  id="mainFrame">
   <frame id="topFrame" src="/<%=context%>/patCommonDtls.htm?actionFlag=commonDtls&CaseId=${CaseId}&caseStartTime=${caseStartTime}&speciality=${speciality}" name="topFrame" scrolling="auto"></frame>
   <frame id="leftFrame" src="/<%=context%>/Preauth/preauthMenus.jsp" name="leftFrame" scrolling="auto"></frame> 
</frameset>
</html>