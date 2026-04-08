<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Operations Module</title>
<%@ include file="../common/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/tabcontent.js"></script> 
<script type="text/javascript" src="../js/vmarquee.js"></script> 
<script type="text/javascript" src="../js/jquery.mCustomScrollbar.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/tabcontent.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="../css/InstStyles.css"/>
<link href="../css/themes/<%=themeColour%>/style.css" rel="stylesheet" type="text/css" media="screen">
<link href="../css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">	   
<link href="../css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
<!--[if gte IE 8]>
  <style type="text/css">
    .rightDiv {
       width: 19.7%;
    }
    #content {
     	overflow: hidden;
    }
  </style>
<![endif]-->
<script type="text/javascript">

function getUserIP(onNewIP) { //  onNewIp - your listener function for new IPs
    //compatibility for firefox and chrome
    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
    var pc = new myPeerConnection({
        iceServers: []
    }),
    noop = function() {},
    localIPs = {},
    ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
    key;

    function iterateIP(ip) {
        if (!localIPs[ip]) onNewIP(ip);
        localIPs[ip] = true;
    }

     //create a bogus data channel
    pc.createDataChannel("");

    // create offer and set local description
    pc.createOffer().then(function(sdp) {
        sdp.sdp.split('\n').forEach(function(line) {
            if (line.indexOf('candidate') < 0) return;
            line.match(ipRegex).forEach(iterateIP);
        });
        
        pc.setLocalDescription(sdp, noop, noop);
    })/* .catch(function(reason) {
        // An error occurred, so handle the failure to connect
    }); */

    //listen for candidate events
    pc.onicecandidate = function(ice) {
        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
    };
}

// Usage

getUserIP(function(ip){
	var xmlhttp;
	var url;
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	
		    	var resultArray=xmlhttp.responseText;
	            if(resultArray.trim()=="SessionExpired*")
	            {
	            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	            else
	            {
	            	
	            	if(resultArray!=null)
	            	{
	            	}
	              }
		    	}
		    }
	 	url = '/Operations/loginAction.do?actionFlag=saveLoginDtls&idAdress='+ip;
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	            		
});
</script>
<script>
function fn_application(url) {
	window.parent.location.href=url;  
}
function fn_gotoMail(){
	parent.fn_gotoMail();
}
function fn_emailSubscription(){
	window.open("http://www.ehf.gov.in/EHF/requestingInformation.do?actionFlag=emailSubscription",'window1','toolbar=no,resize=no,scrollbar=no,width=700, height=450, top=80,left=60');
}
function fn_digitalSignatureReg(){
	parent.fn_digitalSignatureReg();
}

function fn_getManuals(id)
{
	
	var url = "/Operations/trainingMaterialsAction.do?actionFlag=getManuals&id="+id;
	window.open(url,'opManuals','width=700, height=450, top=80,left=80,toolbar=no,resize=no,scrollbar=no');	
	 
}

</script>
</head>
<body>
<div id="outerDiv">
<div id="leftDiv">
<h2 class="tbheader1">Applications</h2>
<ul class="leftLinks">
<c:forEach items="${moduleList}" varStatus="loop">
<c:set var="tmpModVO" value="${moduleList[loop.index]}" />
<li class="wrap" id=${tmpModVO.ID}>
<a href="javascript:fn_application('${tmpModVO.VALUE}')"  title="Click to open ${tmpModVO.ID} module"><img src="../images/${tmpModVO.ID}.png" alt=${tmpModVO.ID}>${tmpModVO.ID}</a>
</li>
</c:forEach>
</ul> 
</div> 
<div id="centerDiv">
<div id="noticeboard">
<h2 class="tbheader1">Notice Board</h2>
<div id="marqueecontainer" onmouseout="copyspeed=marqueespeed" onmouseover="copyspeed=pausespeed">
<div id="vmarquee" style="position: absolute; width: 98%;">
<ul>
<c:if test="${isMedco eq 'Y' }">
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span><b style="color:black;">NOTE:Please download the circular <a href="javascript:fn_getManuals(this.id)" id="SCA" style="color:red;">SCA extension up to 15.08.2016</a></b></li>
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span><b style="color:black;">NOTE:Please download the circular <a href="javascript:fn_getManuals(this.id)" id="Circular" style="color:red;">Fraud Alert : Charity Scams</a></b></li>
			</c:if>
	<li>Preauthorizations kept pending has to be updated within 45 days, otherwise Pre authorization request  will be auto cancelled.  </li>
     <li>For Pre authorization approved cases, surgery has to be updated in 60 days, otherwise pre authorization approval will be auto cancelled. </li>
     <li>Surgery updated cases has to discharged in 60 days, otherwise case will be auto cancelled</li>
	
</ul>
</div>
</div> 
</div> 
<ul class="tabs"> 
	<li class="list1 selected"><a href="#view1" class="wrap">FAQ's</a></li> 
	<li class="list2"><a href="#view2" class="wrap">Training Material</a></li> 
</ul>  
	<div class="tabcontents">   
	<div id="view1"> 
		<div class="faq"><ul>
				<li><a href="#">What is Registration ?</a>
				<ul><li>Registration is the process involving collection of data about new patients to start generating a medical record.</li></ul></li>
				<li><a href="#">Who will do the registration in hospital ?</a>
				<ul><li>A facilitator called Aarogyamithra will be available in the hospitals to register, facilitate the treatment and guide you for all the services.</li></ul></li>
				<li><a href="#">How can I recognize Aarogyamithra ?</a>
				<ul><li>Aarogyamithras are provided with uniform with a scheme related logo on the apron for easy identification 	and placed at Aarogyasri/EHS kiosk, a special help desk situated at reception in the hospital.</li></ul></li>
		</ul></div>
		<p style="float:right;"><a href="#" id="FAQ">More</a></p>
	</div> 
	<div id="view2"> 
		<div class="training">
			<ul>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Claims Processing"  title="Claims Processing(PDF that opens in a new window)"> 1. Claims Processing material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Followup Claims" title="Followup Claims(PDF that opens in a new window)"> 2. Followup Claims material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Registration Usermanual" title="Registration Usermanual(PDF that opens in a new window)"> 3. Registration  material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="IP and OP Registration and Preauthorisation Process" title="IP & OP Registration and Preauthorisation(PDF that opens in a new window)"> 4. IP and OP Registration and Preauthorisation Process material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Chronic OP User Manual" title="Chronic OP User Manual(PDF that opens in a new window)"> 5. Chronic OP User Manual</a></li>
		
		</ul>
		
		
		
		<!-- <img width="500" height="180" style="vertical-align: middle; margin-left: 4px;" alt="Page under construction" src="../images/underConstruction.png"> -->
		</div>
	</div> 
	</div>
</div>
<div class="rightDiv tbheader1"> 
<ul class="rightLinks"> 
	<li><a href="javascript:fn_emailSubscription();" ><img title="Get email alerts" src="../images/emailalerts.jpg" alt="Get email alerts"></a></li>
	<li id="mailList"><a href="javascript:fn_gotoMail();" ><img title="Open mail box" src="../images/mailbox.jpg" alt="Open mail box"></a></li>
	<li id="digitalSign"><a href="javascript:fn_digitalSignatureReg();" ><img title="Digital Certificate Registration" src="../images/digitalsignature2.jpg" alt="Digital Certificate Registration"></a></li>
    <li><a href="#" id="KC"><img src="../images/s_KC.jpg" alt="Knowledge Centre" style="margin-left:5px;"/></a></li>
	<li><a href="#" id="104Services"><img src="../images/needhelp.jpg" alt="Need Help" style="margin-left:5px"/></a></li>
   </ul>  
</div>
<div class="Clear">&nbsp;</div>
</div>  
<script type="text/javascript">
$("#KC").click(function(e){
	$("#centerDiv").load('../staticpages/KnowledgeCentre.html #KC_tabs');
	e.preventDefault();
} 
);
/* Script to load 104 Services */
$("#104Services").click(function(){
   $("#centerDiv").load('../staticpages/Services104.html #popupServices104', function(){
		             $("#centerDiv #Services104list_static").mCustomScrollbar({theme:"dark",autoHideScrollbar:true});

	 });
}); 
/* Script to load FAQ */ 
$("#FAQ").click(function(){
   $("#centerDiv").load('../staticpages/FAQ.html #popupFAQ', function(){
		             $("#centerDiv #FAQlist_static").mCustomScrollbar({theme:"dark",autoHideScrollbar:true});

	 });
});

</script>
</body>
</html>