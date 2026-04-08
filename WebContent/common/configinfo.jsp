
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.configuration.CompositeConfiguration" %>
<%@page import="org.apache.commons.configuration.Configuration"%>
<%@page import="com.tcs.framework.configuration.*"%>
<%@page import="com.tcs.framework.persistanceConfiguration.ServiceFinder"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
</head>
<body>
<h3>Configuration Information</h3>
<%  
try{
ConfigurationService configurationService =  null;
		try{
         configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
        }catch(Exception ee){
        ee.printStackTrace();
        }
		CompositeConfiguration configuration = configurationService.getConfiguration();
    	for (int i=0; i<configuration.getNumberOfConfigurations();i++) {
			Configuration c = configuration.getConfiguration(i);
			Iterator keys = c.getKeys();
			while(keys.hasNext()) {
				String key = (String)keys.next();
				try {
				out.println(key +"="+c.getString(key)+"<br>");
				} catch (Exception e)
                {
					out.println(key +"=<br>");
				}
			}		
			out.println("<hr>");
		}
		}catch(Exception e){
        e.printStackTrace();
        }

%>		
<div><button name="refresh" onclick="window.location='/Operations/common/refreshconfig.jsp'">Refresh Config</button></div>	
</body>
</html>