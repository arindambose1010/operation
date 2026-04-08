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
<title>:: AHC Claims Display Frame ::</title>    
<script>	
  var topMaxFlag=0;
    var rightMaxFlag=0;
    var ipRefershFlag='';

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

function sessionExpireyClose()
{
	parent.fn_logout();
}
var casesSearchFlag='${casesSearchFlag}';
function resizeframe() 
{
  ff = document.getElementById ? document.getElementById('topFrame') : document.all['topFrame'];
  fc = ff.contentDocument ? ff.contentDocument : document.frames('topFrame').document;
  sf = document.getElementById('mainFrame'); 
  lf = 610-fc.body.offsetHeight;
  sf.rows = fc.body.offsetHeight + ", *";
 
}
function fn_maxmizeTop(){
	parent.getGlobalUrl();
}
</script>
</head>

<frameset rows="0,100%" border="1" id="mainFrame">
   <frame id="topFrame" src="/<%=context%>/annualCheckUp.htm?actionFlag=viewAhcPrintPage&ahcId=${ahcId}&casesSearchFlag=${casesSearchFlag}" name="topFrame" scrolling="auto"></frame> 
   <frame id="leftFrame" src="/<%=context%>/AnnualCheckUp/AhcMenus.jsp" name="leftFrame" scrolling="auto"></frame> 
</frameset>

</html>