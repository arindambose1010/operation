<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.configuration.CompositeConfiguration" %>
<%@page import="org.apache.commons.configuration.Configuration"%>
<%@page import="com.tcs.framework.configuration.*"%>
<%@page import="com.ahct.common.util.ServiceFinder"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
</head>
<body>
<h3>Configuration Information</h3>
<%
  ConfigurationService  configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
  configurationService.loadConfig();
%>		
<div>Configuration has been refreshed</div>	
</body>
</html>