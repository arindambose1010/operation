<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript"  src="js/jquery.mCustomScrollbar.js" ></script>
<script type="text/javascript"  src="js/JqueryScrollbar.js" ></script>
<link href="css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style>
body{
 overflow:hidden;
}
html, body{
 height:100%
}
</style>
<script type="text/javascript">
function fn_goToField(el)
{
	var elID="#"+el;
	$("body").mCustomScrollbar("scrollTo",elID);		
}
</script>
</head>
<body></body>
</html>