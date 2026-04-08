<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open AM LogOut</title>
</head>
<body onload="javascript:checkLogoutURL();">

</body>
<script>
	function checkLogoutURL()
		{
			var alertMsg='${alertMsg}';
			if(alertMsg!=null && alertMsg!='')
				alert(alertMsg);
		
			var openAMLogOutUrl='${openAMLogOutUrl}';
			if(openAMLogOutUrl!=null)
				{
					if(openAMLogOutUrl!='')
						{
							window.location.href=openAMLogOutUrl;
						}
					else
						window.location.href="operations.ehf.gov.in/Operations";
				}
			else
				window.location.href="operations.ehf.gov.in/Operations";
		}
</script>
</html>