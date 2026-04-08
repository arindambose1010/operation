<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Preauth Menus Bootstrap</title>
<style type="text/css">
body{font-size: 1.13em !important}

/* side menu css */
#content{
height:520px;
border:0;
padding: 5px !important;
/*overflow:auto;*/
background-color: #fafafa;
border: 1px solid #ddd;
border-radius:10px !important;
}
#bottomframe{
  min-height:370px;
}
.output{
    float:right;
}
.output a {
    background: none repeat scroll 0 0 #000000;
    border-radius: 15px 15px 15px 15px;
    display: inline-block;
    margin: 5px 20px 5px 0;
    padding: 3px 15px;
    text-decoration: none;
    color:white;
}
.output a:hover {
    text-decoration:underline;
    color:#ccc;
}

#vtab {
    margin: auto;
    padding: 0;
}
	
#vtab ul li {
    /*background: -moz-linear-gradient(center top , #F5DEB3, #D2B48C);*/
    list-style-type: none;
    display: block;
    text-align: left;
    margin: auto;
    padding-bottom: 10px;
    border: 1px solid #fff;
    position: relative;
    border-right: none;
}
/*.ie.vtablist , .ie.selected , .ie#vtab ul li{
    margin: -3px 0px;
}*/
#vtab ul li a {
    background: none repeat scroll 0 0 transparent;
    /*color: black;*/
    /*font-size: small;*/
    font-weight: bold;
    padding: 8px 8px 8px 8px;
    text-decoration:none;
    display:block;
}

#vtab ul li.selected {
    border: 1px solid #ddd;
    border-right: none;
    z-index: 10;
    background: #fafafa !important;
    background: linear-gradient(top, #fafafa, #fafafa);
    filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#fafafa, endColorstr=#fafafa);
    position: relative;
   
}
#vtab ul {
    float: left;
    text-align: left;
    display: block;
    margin: auto 0;
    padding: 0;
    position: relative;
    top: 80px;
}
#vtab #content {
    background-color: #fafafa;
    border: 1px solid #ddd;
    min-height: 440px;
	margin-left:16.5%;
    padding: 12px;
    position: absolute;
    -moz-border-radius: 20px;
    -webkit-border-radius: 20px;
    border-radius: 20px;
    z-index: 9;
}
#vtab #content {
    padding-top: 5px;
    margin-top: 0;
}

#vtab ul li.selected{
    border-right: 1px solid #fff !important;
}
#vtab ul li {
    border-right: 1px solid #ddd !important;
}
#vtab #content {
    /*z-index: -1 !important;*/
    left:1px;
}
.selected{
background:none;
}
.loading{opacity:0.3;}
#countdown span {
    font-size: 14px;
    font-weight: bold;
    margin: 1px;
    padding: 5px;
    text-align: center;
}
#countdown {
    border: 1px solid #eee;
    color: black;
    left: 2px;
    padding: 0 0 10px 0;
    position: absolute;
    width: 105px;
    z-index: 15;
    text-align: center;
    font-weight: bold;
}
#countdown #minutesID, #countdown #secondsID {
    border: 2px solid #fafafa;
    background: #eee;
}
@media screen and (max-width:768px) { 
 #vtab ul{display:inline-block;top:5%}
 .selected{width:auto !important;}
 #vtab ul li{display:inline-block;padding:0;}
 #vtab ul li.selected{border-right:#ddd;}
 #vtab ul li a{padding:2px;}
 #vtab #content{margin-top: 30%;margin-left:0;}
} 
</style>
<%@ include file="/common/include.jsp"%>
<script src="../js/jquery-1.9.1.min.js"></script>
<link href="/Operations/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">

<script >

var elemJqueryScrollTop=0;
var casesSearchFlag=parent.casesSearchFlag;
function sessionExpireyClose(){
	
	parent.sessionExpireyClose();
}
var rightMaxFlag=0;
var topMaxFlag=0;
function fn_maxmizeTop(){
	if(casesSearchFlag!=null && casesSearchFlag=='Y')
		parent.parent.fn_ahcCasesSearch();
	else
		parent.parent.fn_ahcClaimCases();
	
}
function loadPrint()
{  
	fn_loadImage();
	
	parent.topFrame.loadPrint(); 
}
function fn_attachments()
{
	fn_loadImage();
	
	parent.topFrame.fn_attachments();
}
function fn_claims(){
	parent.topFrame.fn_claim();
}
function fn_maxmizeRight()
{
	if(rightMaxFlag==0){
	document.getElementById("vtabul").style.display="none";
	document.getElementById("content").style.width='99%';
	document.getElementById("content").style.margin='0';
	rightMaxFlag=1;
	}
	else
		{
		document.getElementById("vtabul").style.display="block";
		document.getElementById("content").style.width='83%';
		//document.getElementById("content").style.margin='0 0 0 110px';
		document.getElementById("content").style.marginLeft='16.5%';
		rightMaxFlag=0;
		}
	}
	
	/**
	Loading Image
	*/
	function fn_loadImage()
	{
		document.getElementById('processImagetable').style.display="";

	}
	function detectBrowser(){
		if ( navigator.appName == 'Opera' ) return 'Opera';
		 if ( navigator.appName == 'Microsoft Internet Explorer' )  return 'MSIE';
		 if ( navigator.userAgent.indexOf( 'Chrome' ) >= 0 ) return 'Chrome';
		 if ( navigator.userAgent.indexOf( 'SeaMonkey' ) >= 0 ) return 'SeaMonkey';  // must test before Firefox
		 if ( navigator.userAgent.indexOf( 'Safari' ) >= 0 ) return 'Safari';
		 if ( navigator.userAgent.indexOf( 'Firefox' ) >= 0 ) return 'Firefox';
		 return navigator.appName;
	}
	function fn_removeLoadingImage()
	{   
		document.getElementById('processImagetable').style.display="none";
		var browserName=detectBrowser();
		if(browserName == "Chrome"){
		      document.getElementById("bottomFrame").style.height="99%";
		 }
		 else{
		      document.getElementById("bottomFrame").style.height="100%";
		 }
	}

function fn_attachments()
{
	fn_loadImage();
	
	parent.topFrame.fn_attachments();
}


	function fn_highlight(id)
	{
	
	   var arr=new Array("regTab","attachments","claims");
	   for(i=0;i<=arr.length-1;i++)
	   { 
	       var x=document.getElementById(id).id;
	         if(arr[i] == x)
	            {	     
	            document.getElementById(id).className ="selected";
	            }
	             else
	            {
	        	document.getElementById(arr[i]).className =" ";
	        	}
		}
	}

	function fn_claims(){
		parent.topFrame.fn_claim();
	}

function closeAttachmentWin()
{
	parent.fn_CloseWins();
}


   var preventDefault = function(e) { 
	   e.preventDefault(); 
	   e.stopPropagation(); 
	   } ;
   var onDivMouseEnter = function() { 
	   $(window).bind('DOMMouseScroll.myNameSpace', preventDefault); 
	   $(document).bind('mousewheel.myNameSpace', preventDefault); 
	   } ;
   var onDivMouseLeave = function() { 
	   $(window).unbind('DOMMouseScroll.myNameSpace'); 
	   $(document).unbind('mousewheel.myNameSpace'); 
	   } ;
		(function($){
			$('.vtablist').click(function (e) {
			    e.preventDefault();
			    $(this).tab('show');
			  });
			      	
		})(jQuery);
		function fn_resizePage1(){
			
			var browserName=parent.parent.detectBrowser();
		     if(browserName == "Chrome"){
		      var height=document.getElementById("bottomFrame").contentDocument.documentElement.scrollHeight;
		     }
		     else{
		      var height=document.getElementById("bottomFrame").contentWindow.document.body.scrollHeight;
		         }
		    //var height=document.getElementById("bottomFrame").contentWindow.document .body.scrollHeight;
		    height=height*1+parseInt(10*1);
			document.getElementById("bottomFrame").height=height;	
			//$("#content").mCustomScrollbar("update");
			//$("#content").mCustomScrollbar("scrollTo","top");			
		}		
</script>
</head>
<body > 
<div id="countdown" class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
<p class="tb">Time Remaining</p>
<div style="float:left;width:50%;">mins<br><br><span id="minutesID"></span></div>
<div style="float:left;width:50%;">sec<br><br><span id="secondsID"></span></div>
</div>  
<div id="vtab" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

<ul id="vtabul" class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
<li id="regTab" class="selected" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content" onclick="javascript:loadPrint();">AHC Registration Details</a>
</li>

<li  id="attachments" class="vtablist" onclick="javascript:fn_highlight(this.id);">
<a href="#" rel="load-content1" onclick="javascript:fn_attachments();">Attachments</a>
</li>

<li  id="claims" class="vtablist" onclick="javascript:fn_highlight(this.id);" >
<a href="#" rel="load-content4" onclick="javascript:fn_claims();">Claims</a>
</li>
</ul>


 <div id="processImagetable" style="top:10%;position:absolute;z-index:60;height:10%">
        <table border="0" align="center" width="100%" style="height:400" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img class="img-responsive" src="../images/Progress.gif" border="0"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
        
<div id="content" class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
<iframe name="bottomFrame" scrolling="auto" id="bottomFrame" style="background:white;" height="100%" width="100%" frameBorder="0" onload="fn_removeLoadingImage();" ></iframe>
</div>
<div style="height:0px;width:0px;margin:0px;padding:0px;clear:both;">&nbsp;</div>
</div>
<script >

//set the date we're counting down to
var target_date = new Date().getTime();
 
// variables for time units
var days, hours, minutes, seconds, ms_step=10;
 
// get tag element
var countdown = document.getElementById('countdown');
var autoAction = parent.autoActionFlag;
var timoutTimer = parent.timerTime;

setInterval(function () {
	if(autoAction!=null && autoAction=="OnloadCaseOpen"){
		if(window.bottomFrame.document.getElementById("topImage")!=null){
            window.bottomFrame.document.getElementById("topImage").style.display='none';
		}
		if(window.bottomFrame.document.getElementById("menuImage")!=null)
			window.bottomFrame.document.getElementById("menuImage").style.display='none';
    var current_date = new Date().getTime();
    var seconds_left = (target_date - current_date) / 1000;
    seconds_left=seconds_left+parseInt(timoutTimer);
    days = parseInt(seconds_left / 86400);
    seconds_left = seconds_left % 86400;
    hours = parseInt(seconds_left / 3600);
    seconds_left = seconds_left % 3600;
    min = parseInt(seconds_left / 60);
    sec = parseInt(seconds_left % 60);
    ms = parseInt(target_date-current_date);
    // format countdown string + set tag value
    if(min<=0 && sec<=0){
    	minutesID.innerHTML = '00';
		secondsID.innerHTML = '00';
        
   	    if(window.bottomFrame.document.getElementById('buttonVisibleId')!=null){
   		 window.bottomFrame.document.getElementById('buttonVisibleId').style.display='block';
   		window.bottomFrame.document.getElementById('buttonVisibleId').align='center';
        }
        if(window.bottomFrame.document.getElementById('buttonVisibleEnhId')!=null){
      		 window.bottomFrame.document.getElementById('buttonVisibleEnhId').style.display='block';
        		window.bottomFrame.document.getElementById('buttonVisibleEnhId').align='center';
             }
    }
    else {
   			minutesID.innerHTML = min;
   			secondsID.innerHTML = sec;
    }
	}
	else{
		document.getElementById('countdown').style.display='none';
		if(window.bottomFrame.document.getElementById('buttonVisibleId')!=null){
	   		 window.bottomFrame.document.getElementById('buttonVisibleId').style.display='block';
	   		window.bottomFrame.document.getElementById('buttonVisibleId').align='center';
	        }
	        if(window.bottomFrame.document.getElementById('buttonVisibleEnhId')!=null){
	      		 window.bottomFrame.document.getElementById('buttonVisibleEnhId').style.display='block';
	        		window.bottomFrame.document.getElementById('buttonVisibleEnhId').align='center';
	             }
		}
}, ms_step);
</script>
</body>
</html>     