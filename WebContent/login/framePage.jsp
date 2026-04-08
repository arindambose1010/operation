<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<style type="text/css">
.theme1 {
	BACKGROUND: url(css/themes/green/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.theme2 {
	BACKGROUND: url(css/themes/gray/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.theme3 {
	BACKGROUND: url(css/themes/greenyellow/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.theme4 {
	BACKGROUND: url(css/themes/orange/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.theme5 {
	BACKGROUND: url(css/themes/reddish/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.theme6 {
	BACKGROUND: url(css/themes/default/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.theme7 {
	BACKGROUND: url(css/themes/brown/header.jpg) no-repeat;
	POSITION: relative;
	cursor: pointer;
}

.placeholder {
	color: #aaa;
}

#content {
	width: 100%;
	float: left;
	height: 610px;
	border: 0;
	overflow: auto;
}

.ie#content {
	height: 610px;
}

body {
	overflow: hidden;
	height: 100%
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="/js/jquery-1.9.1.js"></script>
<link href="/Operations/css/login.css" rel="stylesheet" type="text/css"
	media="screen">

<script type="text/javascript" src="/js/popup.js"></script>

<%@page import="java.util.Calendar"%>
<%@ include file="/common/include.jsp"%>
<link href="/Operations/css/themes/<%=themeColour%>/theme.css"
	rel="stylesheet" type="text/css" media="screen">
<link href="/Operations/css/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
<link href="/Operations/css/themes/<%=themeColour%>/style.css"
	rel="stylesheet" type="text/css" media="screen">
<link href="css/jquery.mCustomScrollbar.css" rel="stylesheet"
	type="text/css" />
<title>Employees Health Scheme</title>
<style>
@charset "utf-8";
/* CSS Document */
</style>
<style>
</style>

<script type="text/javascript">
var page;
var fpInitPage;
//placholder to be worked in IE
(function($) {
	  $.fn.placeholder = function() {
	    
	      $('[placeholder]').keypress(function() {
	        var input = $(this);
	        if (input.val() == input.attr('placeholder')) {
	          input.val('');
	          input.removeClass('placeholder');
	        }
	      }).blur(function() {
	        var input = $(this);

	        if (input.val() == '' || input.val() == input.attr('placeholder')) {
	          input.addClass('placeholder');
	          input.val(input.attr('placeholder'));
	        }
	      }).blur().parents('form').submit(function() {
	        $(this).find('[placeholder]').each(function() {
	          var input = $(this);
	          if (input.val() == input.attr('placeholder')) {
	            input.val('');
	          }
	      });
	    });
	  
	};
	})(jQuery);
	
function fn_open()
{
	window.moveTo(0,0);
	window.resizeTo(screen.availWidth,screen.availHeight);  
	window.toolbar.visible=false;
	}
	function curTime() {
		var now = new Date()
		var hrs = now.getHours()
		var min = now.getMinutes()
		var sec = now.getSeconds()
		var don = "AM"
		if (hrs >= 12) {
			don = "PM"
		}
		if (hrs > 12) {
			hrs -= 12
		}
		if (hrs == 0) {
			hrs = 12
		}
		if (hrs < 10) {
			hrs = "0" + hrs
		}
		if (min < 10) {
			min = "0" + min
		}
		if (sec < 10) {
			sec = "0" + sec
		}
		document.getElementById("clock").innerHTML = hrs + ":" + min + ":"
				+ sec + " " + don
		setTimeout("curTime()", 1000)
	}
	function fn_changePassword() {
		document.getElementById("changePasswordDiv").src='/<%=context%>/loginAction.do?actionFlag=ChangePwd';
		centerPopup("#popupContact");
		loadPopup("#popupContact");
	}
	
	function fn_changeSettings() {
		
		centerPopup("#popupContactTrouble");
		loadPopup("#popupContactTrouble");
	}

	function fn_logout()
	{
	
	if(page!=null){
		page.close();
		page=null;
	}
	if(fpInitPage!=null){
		fpInitPage.close();
		fpInitPage=null;
	}
		document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=logout";
		document.forms[0].submit();
		self.close();
		
	}

var changePswd=false;
function fn_htmlPages()
{
	window.history.forward();
	var pswdChngTim='${changePwdTime}';
	document.getElementById("menuFramePage").src='/<%=context%>/loginAction.do?actionFlag=getMenus&loginType='+document.forms[0].loginType.value;
	redirectDefaultPage();
}
function redirectDefaultPage(){
	if(document.forms[0].msg.value!=null && document.forms[0].msg.value!=""){
		document.getElementById("middleFrame").src=document.forms[0].msg.value;
		
	}
	else{
		
	if('${roleName}' != "" && '${roleName}' == 'Pensioner')
		{
		document.getElementById("middleFrame").src='/<%=context%>/login/PensionerInstructions.jsp';
		}
	else if('${roleName}' != "" && '${roleName}' == 'Family Pensioner')
	{
	document.getElementById("middleFrame").src='/<%=context%>/login/PensionerInstructions.jsp';
	}
	else if('${roleName}' != "" && '${roleName}' == 'Employee')
	{
		document.getElementById("middleFrame").src='/<%=context%>/login/EmployeeInstructions.jsp';	
	}
	else if('${roleName}' != "" && '${roleName}' == 'DDO')
	{
		
		document.getElementById("middleFrame").src='/<%=context%>/empEnrolmntAction.do?actionFlag=getDDOPendingData';	
	}
	else if('${roleName}' != "" && '${roleName}' == 'STO')
	{
		
		document.getElementById("middleFrame").src='/<%=context%>/empEnrolmntAction.do?actionFlag=getDDOPendingData';	
	}
	else if('${roleName}' != "" && '${roleName}' == 'MEDCO' ||'EHF-administration'||'PEX'||'PTD'||'NAM'||'CAO'||'CPD'||'ACCOUNTS'||'CEX'||'CTD'||'CH'||'FTD1'||'FCX1')
	{
		document.getElementById("middleFrame").src='/<%=context%>/login/LoginFramePage.jsp';
	}
		
	}
}
function fn_loadImage()
{
	if(page!=null){
		page.close();
		page=null;
	}
	document.getElementById('processImagetable').style.display="";

}
var menuSize='${menuSize}';
function fn_removeLoadingImage()
{   
	document.getElementById('processImagetable').style.display="none";
    var height=document.getElementById("middleFrame").contentWindow.document .body.scrollHeight;
    
    if(height<610)
    	height=height*1+parseInt((610*1-height*1));
    else
    	height=height*1+parseInt(10*1);
	document.getElementById("middleFrame").height=height;	

	$("#content").mCustomScrollbar("update");
	$("#content").mCustomScrollbar("scrollTo","top");
}
function fn_resizePage(){
	
    var height=document.getElementById("middleFrame").contentWindow.document .body.scrollHeight;
    if(height<610)
    	height=height*1+parseInt((610*1-height*1));
    else
    	height=height*1+parseInt(10*1);
	document.getElementById("middleFrame").height=height;	
	$("#content").mCustomScrollbar("update");
	
}

//Function for units
function fn_changeTheme(themeColour)
{
	var xmlhttp;
 	if (window.XMLHttpRequest)
 	{
  		xmlhttp=new XMLHttpRequest();
 	}
 	else if (window.ActiveXObject)
 	{		
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
 	}
 	else
 	{
  		alert("Your browser does not support XMLHTTP!");
 	}
 	xmlhttp.onreadystatechange=function()
 	{
 		if(xmlhttp.readyState==4)
 		{
	 		var frameUrl=document.getElementById("middleFrame").src;
	 		document.forms[0].msg.value=frameUrl;
	    	document.forms[0].action="/<%=context%>/loginAction.do?actionFlag=reloadHomePage";
			document.forms[0].submit();
  
 		}			
 	}
	var url =  "/<%=context%>/loginAction.do?actionFlag=saveTheme&themeColour="+themeColour;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}

function sessionExpireyClose(){
	window.close();
}

function fn_type()
{
	$('#sframe').slideUp(200);
	$('#empid').addClass('placeholder');
	$('#empid').val($('#empid').attr('placeholder'));
}
function fn_slideUp()
{
	$('#sframe').slideUp(100);
}
function fn_clear()
{
	document.getElementById('empid').value="";
}

function fn_keypressed(e)
{
	var code;
	if (!e) var e = window.event;
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	if( code== 13 )
	{ 
		fn_search();
		e.returnValue=false;
	}
}
	
function openUrl(url)
{
	document.getElementById("middleFrame").src='/<%=context%>/' + url;
}
</script>

<script type="text/javascript"
	src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script> 
	(function($) {
		$(window)
				.load(
						function() {
							$("#content").mCustomScrollbar({
								theme : "dark"
							});
							$("#content").hover(function() {
								$(document).data({
									"keyboard-input" : "enabled"
								});
								$(this).addClass("keyboard-input");
							}, function() {
								$(document).data({
									"keyboard-input" : "disabled"
								});
								$(this).removeClass("keyboard-input");
							});
							$(document)
									.keydown(
											function(e) {
												if ($(this).data(
														"keyboard-input") === "enabled") {
													var activeElem = $(".keyboard-input"), activeElemPos = Math
															.abs($(
																	".keyboard-input .mCSB_container")
																	.position().top), pixelsToScroll = 60;
													if (e.which === 38) { //scroll up
														e.preventDefault();
														if (pixelsToScroll > activeElemPos) {
															activeElem
																	.mCustomScrollbar(
																			"scrollTo",
																			"top");
														} else {
															activeElem
																	.mCustomScrollbar(
																			"scrollTo",
																			(activeElemPos - pixelsToScroll),
																			{
																				scrollInertia : 400,
																				scrollEasing : "easeOutCirc"
																			});
														}
													} else if (e.which === 40) { //scroll down
														e.preventDefault();
														activeElem
																.mCustomScrollbar(
																		"scrollTo",
																		(activeElemPos + pixelsToScroll),
																		{
																			scrollInertia : 400,
																			scrollEasing : "easeOutCirc"
																		});
													}
												}
											});
						});
	})(jQuery);
</script>
</head>
<body onload="javascript:fn_htmlPages()">
	<html:form method="post" action="/loginAction.do">
		<div id="container" style="padding-top: 0px; margin: 0px auto;">
			<div id="headerDiv" class="wrap1" style="height: 56px" align="right">

				<table style="height: 56px; width: 60%">
					<tr class="timeStyle">
						<td align="left" width="294px" valign="top" class="details">
							<p>
								<b>Welcome &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;</b>${fullName}&nbsp;
							</p>
							<p>
								<b>Designation &nbsp;:&nbsp;</b>${dsgnName}
							</p>
							<p id="timeSpan" class="timeStyle"></p>
						</td>

						<td class="menu">
							<div class="item">
								<a class="link icon_mail"></a>
								<div class="item_content">
									<a href="javascript:fn_logout()" title="Logout">Logout</a>
								</div>
							</div>
							<div class="item">
								<a class="link icon_help"></a>
								<div class="item_content">
									<a href="#" title="Help">Help</a>
								</div>
							</div>
							<div class="item">
								<a class="link icon_find"></a>
								<div class="item_content item_diff">
									<a href="javascript:fn_changePassword()"
										title="Change Password">Change<br>Password
									</a>
								</div>
							</div>
							<div class="item">
								<a class="link icon_photos"></a>
								<div class="item_content item_diff">
									<a href="javascript:fn_changeSettings()" title="Change Theme">Change<br>Theme
									</a>
								</div>
							</div>
							<div class="item">
								<a class="link icon_home"></a>
								<div class="item_content">
									<a href="#" onclick="redirectDefaultPage()" title="Home">Home</a>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>

			<div id="menuLog" class="wrap" style="z-index: 999; width: 100%">
				<!-- <a href="#"><img src="images/login/home.jpeg" height=30px width=30px title="Back to Home"style="float:left;" onclick="redirectDefaultPage()"/></a>-->
				<ul id="nav" style="vertical-align: bottom;">

					<li><img style="vertical-align: bottom;"
						src="images/ajax-loader.gif" /></li>
				</ul>
				<script>
					showSearch();
				</script>
				<div id="srch" style="display: none">
					<ul style="float: right" id="nav1">
						<li><select name="searchType" id="searchType"
							style="height: 23px;" onchange="fn_type()">
								<option value="-1">
									<b>--Employee Type--</b>
								</option>
								<option value="emp">Employee</option>
								<option value="pen">Pensioner</option>
						</select></li>
						<li><input type="text" id="empid"
							placeholder="Enter Name/Emp ID"
							onkeypress="return fn_keypressed(event)" /> <script
								type="text/javascript">
								$.fn.placeholder();
							</script> <span title="Search"> <img src="images/searchicon.png"
								onclick="fn_search()"></img>
						</span></li>
						<li><span title="Advanced Search"> <img
								id="searchArrow" src="images/downarro.png" height="20"
								width="20" onclick="fn_adSearch()"></img></span>
							<ul id="sframe">
								<li id="searchPage"><iframe width="430px" height="170px"
										name="searchFrame" id="searchFrame"></iframe></li>
							</ul></li>
					</ul>
				</div>

				<div class="clearboth">&nbsp;</div>
			</div>

			<div id="menuFrameDiv" align="center" style="display: none;">
				<iframe style="border: 0; width: 100%;" id="menuFramePage"
					src="www.google.co.in" scrolling="yes" name="menuFramePage">
					<!-- onload="javascript:fn_removeLoadingImage();"  -->
					>
				</iframe>
			</div>

			<div id="content">
				<iframe id="middleFrame" scrolling="auto" width="100%" height="100%"
					name="middleFrame">
					<!-- onload="javascript:fn_removeLoadingImage();" -->
				</iframe>
			</div>
		</div>


		<div id="backgroundPopup" style="z-index: 1000"></div>

		<!-- START FOR FORGOT PASSWORD POPUP -->
		<!-- here change the height -->
		<div id="popupContact" style="z-index: 1001">
			<a id="popupContactClose"><img src="/Operations/images/fileclose.png" /></a><br>
			<iframe style="border: 0; width: 100%; height: 390px"
				id="changePasswordDiv" scrolling="no" name="changePasswordDiv">
			</iframe>
		</div>
		<!-- END FOR FORGOT PASSWORD POPUP -->


		<!-- START FOR SETTINGS POPUP -->
		<div id="popupContactTrouble" style="z-index: 1011">

			<table align="center" style="vertical-align: middle;" height="650px">

				<tr class="theme1" valign="baseline">
					<td style="width: 700px; height: 70px" align="right"
						onclick="fn_changeTheme('green');"></td>
				</tr>

				<tr class="theme2" onclick="fn_changeTheme('gray');">
					<td style="width: 700px; height: 70px" align="right"></td>
				</tr>

				<tr class="theme7" onclick="fn_changeTheme('brown');">
					<td style="width: 700px; height: 70px" align="right"></td>
				</tr>
				<tr class="theme3" onclick="fn_changeTheme('greenyellow');">
					<td style="width: 700px; height: 70px" align="right"></td>
				</tr>


				<tr class="theme4" onclick="fn_changeTheme('orange');">
					<td style="width: 700px; height: 70px" align="right"></td>
				</tr>


				<tr class="theme5" onclick="fn_changeTheme('reddish');">
					<td style="width: 500px; height: 70px" align="right"></td>
				</tr>

				<tr class="theme6" onclick="fn_changeTheme('default');">
					<td style="width: 500px; height: 70px" align="right"></td>
				</tr>

			</table>
		</div>
		<!-- END FOR SETTINGS POPUP -->
		<script type="text/javascript">
			
		<%Calendar cal = Calendar.getInstance();%>
			var diffTime = new Date().getTime() -
		<%=cal.getTimeInMillis()%>
			;
			var time;
			var date;

			window.setInterval(function() {
				time = new Date().getTime();
				time = time + diffTime;
				date = new Date(time);
				if (navigator.appName.indexOf("Microsoft") != -1)
					document.getElementById('timeSpan').innerHTML = date
							.toString().replace("UTC+0530", "")
							+ "&nbsp;&nbsp;";
				else
					document.getElementById('timeSpan').innerHTML = date
							.toString().replace(
									"GMT+0530 (India Standard Time)", "")
							+ "&nbsp;&nbsp;";
			}, 1000);

			function allignMenu(menuHTML, pMenuSize) {
				document.getElementById("nav").innerHTML = menuHTML;
				menuSize = pMenuSize;
				$('#nav>li').hover(function() {
					$('ul:first', this).slideDown(200);
					$(this).children('a:first').addClass("hov");
				}, function() {
					$('ul', this).slideUp(100);
					$(this).children('a:first').removeClass("hov");
				});
				$('#nav li ul li').hover(function() {
					$('ul:first', this).slideDown(200);
					$(this).children('a:first').addClass("hovinner");
				}, function() {
					$('ul', this).slideUp(100);
					$(this).children('a:first').removeClass("hovinner");
				});

			}
		</script>
		<script src="js/jquery-animate-css-rotate-scale.js"
			type="text/javascript"></script>
		<script>
			$('.item').hover(function() {
				var $this = $(this);
				expand($this);
			}, function() {
				var $this = $(this);
				collapse($this);
			});
			function expand($elem) {
				var angle = 0;
				var t = setInterval(function() {
					if (angle == 1440) {
						clearInterval(t);
						return;
					}
					angle += 40;
					$('.link', $elem).stop().animate({
						rotate : '+=-0deg'
					}, 0);
				}, 10);
				$elem.stop().animate({
					width : '115px'
				}, 1000).find('.item_content').fadeIn(400, function() {
					$(this).find('p').stop(true, true).fadeIn(600);
				});
			}
			function collapse($elem) {
				var angle = 1440;
				var t = setInterval(function() {
					if (angle == 0) {
						clearInterval(t);
						return;
					}
					angle -= 40;
					$('.link', $elem).stop().animate({
						rotate : '+=0deg'
					}, 0);
				}, 10);
				$elem.stop().animate({
					width : '30px'
				}, 1000).find('.item_content').stop(true, true).fadeOut().find(
						'p').stop(true, true).fadeOut();
			}
		</script>
		<html:hidden name="loginForm" property="msg" />
		<html:hidden name="loginForm" property="loginType" />
	</html:form>
</body>
</html>

