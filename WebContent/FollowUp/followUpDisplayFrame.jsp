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
    <title>:: Follow UP Display Frame ::</title>
    
    <script type="text/javascript">
   
    
    var topMaxFlag=0;
    var rightMaxFlag=0;
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
	if(topMaxFlag==0){
	document.getElementById("mainFrame").rows="0,*";
	topMaxFlag=1;
	}
	else{
		document.getElementById("mainFrame").rows="26%,*";
		topMaxFlag=0;
	}
	
}
    </script>
</head>
<frameset rows="26%,*" frameborder="0"  id="mainFrame" >
   <frame src="/<%=context%>/patCommonDtls.htm?actionFlag=commonDtls&CaseId=${CaseId}" name="topFrame" scrolling="no"></frame>    
   <frame name="bottomFrame" scrolling="yes"></frame>      

  </frameset>
</html>