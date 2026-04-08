<html>
<head>
<script> 
window.history.forward();
</script>
</head>
<%
response.setHeader("pragma", "no-cache");
response.setHeader("cache-content", "no-cache");
response.setHeader("expires", "0");
request.setAttribute("themeColour", session.getAttribute("themeColour"));
%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<bean:define id="context" value="Operations"/>
<bean:define id="themeColour" value="${themeColour}"/>
</html>

