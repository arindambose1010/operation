<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" import="java.io.*" isErrorPage="true"%>
<html>
	<head>
		<title>Aarogyasri Health Care Trust :: Exception</title>
		<style type= "text/css">
			body {
				color: #666666;
				background-repeat: repeat-x;
				font-family: Tahoma, Arial, Helvetica, sans-serif;
				background: #cedbe9; /* Old browsers */
				background: -moz-linear-gradient(45deg,  #cedbe9 0%, #aac5de 17%, #6199c7 50%, #3a84c3 51%, #419ad6 59%, #4bb8f0 71%, #3a8bc2 84%, #26558b 100%); /* FF3.6+ */
				background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,#cedbe9), color-stop(17%,#aac5de), color-stop(50%,#6199c7), color-stop(51%,#3a84c3), color-stop(59%,#419ad6), color-stop(71%,#4bb8f0), color-stop(84%,#3a8bc2), color-stop(100%,#26558b)); /* Chrome,Safari4+ */
				background: -webkit-linear-gradient(45deg,  #cedbe9 0%,#aac5de 17%,#6199c7 50%,#3a84c3 51%,#419ad6 59%,#4bb8f0 71%,#3a8bc2 84%,#26558b 100%); /* Chrome10+,Safari5.1+ */
				background: -o-linear-gradient(45deg,  #cedbe9 0%,#aac5de 17%,#6199c7 50%,#3a84c3 51%,#419ad6 59%,#4bb8f0 71%,#3a8bc2 84%,#26558b 100%); /* Opera 11.10+ */
				background: -ms-linear-gradient(45deg,  #cedbe9 0%,#aac5de 17%,#6199c7 50%,#3a84c3 51%,#419ad6 59%,#4bb8f0 71%,#3a8bc2 84%,#26558b 100%); /* IE10+ */
				background: linear-gradient(45deg,  #cedbe9 0%,#aac5de 17%,#6199c7 50%,#3a84c3 51%,#419ad6 59%,#4bb8f0 71%,#3a8bc2 84%,#26558b 100%); /* W3C */
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cedbe9', endColorstr='#26558b',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */	
			}
			.exceptionDisplay{
				margin: auto;
				margin-top: 15%;
				width:40%;
				height:30%;
				color:red;
				background: #f6f8f9; /* Old browsers */
				background: -moz-linear-gradient(top,  #f6f8f9 0%, #e5ebee 50%, #d7dee3 75%, #f5f7f9 100%); /* FF3.6+ */
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f6f8f9), color-stop(50%,#e5ebee), color-stop(75%,#d7dee3), color-stop(100%,#f5f7f9)); /* Chrome,Safari4+ */
				background: -webkit-linear-gradient(top,  #f6f8f9 0%,#e5ebee 50%,#d7dee3 75%,#f5f7f9 100%); /* Chrome10+,Safari5.1+ */
				background: -o-linear-gradient(top,  #f6f8f9 0%,#e5ebee 50%,#d7dee3 75%,#f5f7f9 100%); /* Opera 11.10+ */
				background: -ms-linear-gradient(top,  #f6f8f9 0%,#e5ebee 50%,#d7dee3 75%,#f5f7f9 100%); /* IE10+ */
				background: linear-gradient(to bottom,  #f6f8f9 0%,#e5ebee 50%,#d7dee3 75%,#f5f7f9 100%); /* W3C */
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f6f8f9', endColorstr='#f5f7f9',GradientType=0 ); /* IE6-9 */
			}
			#backLink {
				cursor:pointer;
				font-weight:bold;
				color:#2571c2;
				font-size:12px;
			}
		</style>
		<script language="JavaScript" src="empanelment/scripts/jquery-1.8.2.min.js"></script>
		<script type = "text/javascript">
			var jq = jQuery.noConflict();
			jq(document).ready(function(){
				jq('body').on('contextmenu keydown', function(e){
					switch (e.keyCode)  {
						case 116 : //F5 button
							event.returnValue = false;
							event.keyCode = 0;
							return false;
						case 82 : //R button
							if (e.ctrlKey) 
							{
								event.returnValue = false;
								event.keyCode = 0;
								return false;
							}
					}
					return false;
				});
				jq('#backLink').click(function(){
					if("${sessionScope.SelInnerTabId}" != null && "${sessionScope.SelInnerTabId}" != "" ){
						//alert("${sessionScope.SelInnerTabId}");
						window.location="loginAction.do?actionVal=getSubMenus&InnerTabIdVal=${sessionScope.SelInnerTabId}";
					} else if(window.history.length == 1 ) {
						window.close();
					} else{
						window.history.back();
					} 
				});
			});
		</script>
	</head>
	<body style= "margin:auto;">
		<div class = "exceptionDisplay">
			<table width="100%">
				<tr>
					<td align= 'center'>
						<h2>Oops...!! Something went wrong.</h2>
						<h5>We'll getback soon. Deeply regret for the inconvenience caused.</h5>
						<span id="backLink"> Back to work</span>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
<jsp:scriptlet>
							StringWriter errors = new StringWriter();
							exception.printStackTrace(new PrintWriter(errors));
							String a = errors.toString();
							System.out.println("Error message :: "+exception.toString());
							System.out.println("Stack Trace :: "+a);
							
							
</jsp:scriptlet>
