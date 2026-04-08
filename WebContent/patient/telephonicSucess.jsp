<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
  	 <%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Care Fund</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
var s$=jQuery.noConflict();

s$(function(){
   /*
    * this swallows backspace keys on any non-input element.
    * stops backspace -> back
    */
   var rx = /INPUT|SELECT|TEXTAREA/i;

   s$(document).bind("keydown keypress", function(e){
       if( e.which == 8 ){ // 8 == backspace
           if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
               e.preventDefault();
           }
       }
   });
});
function  refreshParentPage()
{
parent.fn_telephonicIntimationRaised();	
}
</script>
</head>
<body>
<br><br><br><br><br><br><center><font size="3">
<logic:notEmpty name="patientForm" property="telephonicId">
Patient details has been successfully captured with Telephonic ID : <b><bean:write name="patientForm" property="telephonicId"/></b>
<br><br><br>
<button class="but"  type="button" id="telephonicList" onclick="refreshParentPage()">OK</button>
</logic:notEmpty>
</font></center>
</body>
</html>