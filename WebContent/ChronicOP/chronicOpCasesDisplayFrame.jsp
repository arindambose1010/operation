 <%@ page contentType="text/html;charset=windows-1252"%>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("pragma","no-cache");
    response.setDateHeader("Expires", 0);    
%>
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
 var  disSearchType='${disSearchType}';
 var  module='${module}';
 var fromPage= '${fromPage}';
 var autoActionFlag = '${autoActionFlag}';
 var timerTime = '${timeOutCount}';
 var pendingFlag='${pendingFlag}';

 var ipOpType= '${ipOpType}';
 var clinicalNotes='${clinicalNotes}';
 var roleId='${roleId}';
 var inactiveFlag='${inactiveFlag}';
 var workflowFlag='${workflowFlag}';
 var expiredFlag='${expiredFlag}';

if(workflowFlag=='Y')
{
		alert("Previous Chronic Installment is still in the workflow.You cannot initiate a new installment ");
}
if(inactiveFlag=='Y')
{
	var days='${days}';
alert("Following Chronic OP case is Inactive till "+days);
}
if(expiredFlag=='Y')
{
		alert("All the Installments for this Chronic OP case are completed");
}
 var flagColour='${flagColour}';
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

		parent.getGlobalUrl();
}

function fn_ipRefresh()
{
	ipRefershFlag='Y';
	parent.fn_coClaimCases();
}
function fn_getCaseForApproval()
{
	if(errSearchType!=null && errSearchType=='Y')
		parent.fn_errCasesForApproval();  
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
function sessionExpireyClose()
{
	parent.fn_logout();
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

   <frame id="topFrame" src="/<%=context%>/chronicAction.do?actionFlag=chronicOpDtls&chronicId=${chronicId}" name="topFrame"  scrolling="auto"></frame>
   <frame id="leftFrame" src="/<%=context%>/ChronicOP/chronicOpMenus.jsp" name="leftFrame" scrolling="auto"></frame> 
</frameset>
</html>