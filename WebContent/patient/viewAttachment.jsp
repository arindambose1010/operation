<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View document</title>
</head>
<body>
<iframe id="pdfViewFrame" 
					src="data:application/pdf;base64,${base64DataDoc}" frameBorder="0" scrolling="auto"
					height="500px " width="100%" margin-left="100px"
					style="visibility: visible; height:680px"></iframe>
</body>
</html>