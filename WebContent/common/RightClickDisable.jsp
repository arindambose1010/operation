<%@ page import="java.util.Locale,java.util.ResourceBundle" %>
<%@ include file="/common/include.jsp"%>

<script type="text/javascript" src="/<%=context%>/Common/script/ContextMenu.js"></script>
<style type="text/css">
#cMenuDiv {position:absolute; background-color:#FEFDEB; layer-background-color:#C0C0C0; border:3px outset; z-index:1000; visibility:hidden;}
.cMenuTable, .cMenuTable TD {margin:0; border:0; color:#000000; font-family:Arial, sans-serif; white-space: nowrap;}
.cMenuTable TD.title {font-weight:bold; text-decoration:underline;}
.cMenuTable TD.hr {border:1px outset; background-color:#D0D0D0;}
.cMenuTable A, .cMenuTable A:active, .cMenuTable A:visited, .cMenuTable A:link {color:#000000; font-family:Arial, sans-serif; font-size:0.9em;}
.cMenuTable A:hover {color:#FF0000; font-family:Arial, sans serif; font-size:0.9em;}
</style>



<div id="cMenuDiv"> 
&nbsp;Right Click Disabled&nbsp;
</div>
<html>
  <body onload="initCMenu(); if(document.body) document.body.oncontextmenu=new Function('return false;');" onKeyDown="onKeyDownEvent (event);" >
  </body>
</html>