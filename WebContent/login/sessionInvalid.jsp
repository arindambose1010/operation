<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Employees Health Care Fund </title>
<script>
function fn_close()
{
	//jqueryInfoMsg('Session Expiry','Session Has Been Expired');
	alert("Session Has Been Expired");
	window.parent.location.href= "loginAction.do?actionFlag=logoutSessionExp";
	window.close();
	parent.sessionExpireyClose();
}
</script>
</head>
<body onload="javascript:fn_close();">
   <script type="text/javascript" language="javascript">
   </script>	
</body>
</html>
