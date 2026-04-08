<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Bootstrap After Login Page</title> 
<style>
.arrow{font-size:8px; padding:0 5px 0 0;} 
@media screen and (max-width:768px) { 
 #applications{display:none;}
} 
</style>
<!--[if lt IE 9]> 
<script>
  var e = ("abbr,article,aside,audio,canvas,datalist,details," +
    "figure,footer,header,hgroup,mark,menu,meter,nav,output," + 
    "progress,section,time,video").split(',');
  for (var i = 0; i < e.length; i++) {
    document.createElement(e[i]); 
  }
</script>
<![endif]-->
<!--[if IE 7]>
<style>
.otherLinks div#applications{margin-top:0 !important;}
</style>
<![endif]-->
<%@ include file="/common/include.jsp"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<link rel="stylesheet" type="text/css" media="screen" href="../css/InstStyles.css"/>
<link href="/<%=context%>/css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="../js/vmarquee.js"></script>    
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
function fn_emailSubscription(){
	document.getElementById("emailIframe").src='http://www.ehf.gov.in/EHF/requestingInformation.do?actionFlag=emailSubscription';
}
function fn_gotoMail(){
	var UserID = '${UserID}';
	//document.getElementById("mailListIframe").src='http://ehf.telangana.gov.in/Home/mailAction.do?actionFlag=openmail&UserID='+UserID;
	 var url='https://ehf.telangana.gov.in/Home/mailAction.do?actionFlag=openmail&UserID='+UserID;
	 var child= window.open( url,'mailBox','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
}
function fn_digitalSignatureReg(){
	var UserID = '${UserID}';
	/*Live URL */
	document.getElementById("digitalSignIframe").src='https://ehf.telangana.gov.in/Home/digitalCertificate.do?actionFlag=signUpPage&UserID='+UserID;
	
	/*Local URL */
	//document.getElementById("digitalSignIframe").src='http://172.25.147.51:8080/Home/digitalCertificate.do?actionFlag=signUpPage&UserID='+UserID;
	
	/*Stage URL */
	//document.getElementById("digitalSignIframe").src='http://stage.ehf.gov.in//Home/digitalCertificate.do?actionFlag=signUpPage&UserID='+UserID;
}
function fn_getManuals(id)
{	
	var url = "/Operations/trainingMaterialsAction.do?actionFlag=getManuals&id="+id;
	window.open(url,'opManuals','width=700, height=450, top=80,left=80,toolbar=no,resize=no,scrollbar=no');	
	 
}
function fn_dentalGuidelines(id)
{
	var url = "/Operations/trainingMaterialsAction.do?actionFlag=dentalGuidelines&id="+id;
	window.open(url,'opManuals','width=700, height=450, top=80,left=80,toolbar=no,resize=no,scrollbar=no');	
}
function fn_NWHGuidelines(id)
{
	var url = "/Operations/trainingMaterialsAction.do?actionFlag=getManuals&id="+id;
	window.open(url,'opManuals','width=700, height=450, top=80,left=80,toolbar=no,resize=no,scrollbar=no');	
}
function showCMS()
	{	
	   //document.getElementById("cmdFrameId").src='http://ehf.telangana.gov.in/CMS';
	 var url="https://ehf.telangana.gov.in/CMS/login.htm?flag=showChangeMgmt";
	 var child= window.open( url,'ChangeManagement','width=900, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes');
	}
</script>

</head>
<body>
<div>
	<c:if test="${openAM eq 'Y'}">
	
	 <div id="applications" class="pull-left col-xs-12 col-sm-12 col-md-3 col-lg-3">
	<ul class="list-group">
	<li class="list-group-item heading"><h2>Applications</h2></li>
	  	<c:forEach items="${moduleList}" varStatus="loop">
		<c:set var="tmpModVO" value="${moduleList[loop.index]}" />
		<li class="list-group-item" id=${tmpModVO.ID}>
		<a href="javascript:fn_application('${tmpModVO.VALUE}')"  title="Click to open ${tmpModVO.ID} module"><img src="../images/${tmpModVO.ID}.png" alt=${tmpModVO.ID}>  ${tmpModVO.ID}</a>
		</li>
		</c:forEach>
	</ul>
	</div> 
	
	<div id="noticeboard" class="pull-right col-xs-12 col-sm-12 col-md-9 col-lg-9">
	    
	  <c:if test="${showMessageAP eq 'Y' }">
	   
		<marquee SCROLLAMOUNT=3 onmouseover="this.stop();"  onmouseout="this.start();">
        <div><a href="#" onclick="fn_dentalGuidelines(this.id)" id="Dental Guidelines" title="Dental Guidelines Manual(PDF that opens in a new window)"><b><font color="#005000">Guidelines to NWHs regarding Dental Specialty (S18).Click here To Download</font></b></a></div>
		</marquee>
		</c:if>
		
	<c:if test="${showMessageTG eq 'Y' }">

		<marquee SCROLLAMOUNT=3 onmouseover="this.stop();"  onmouseout="this.start();">
        <div>
        	<c:if test="${hospDentType eq 'clinic' || hospDentType eq 'hospital' }">
	        	<a href="#" onclick="fn_NWHGuidelines(this.id)" id="Gudielines03_03_2016" title="Revised Dental Guide lines(PDF that opens in a new window)"><b><font color="#005000">Revised Dental Guide lines (Click here for details) </font></b></a>
	        	<a href="#" onclick="fn_NWHGuidelines(this.id)" id="utilizationGuidelines" title="Revised Dental Guide lines AnnexureII(PDF that opens in a new window)"><b><font color="#005000">(Click here for AnnexureII) </font></b></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        </c:if>	
        	 <a href="#" onclick="fn_NWHGuidelines(this.id)" id="EHS" title="NWH Guidelines Manual(PDF that opens in a new window)"><b><font color="#005000">05.10.2015:Circular for all NWH empanelment of all available specialities in the NWHs under Aarogyasri and EHS(click here for details)</font></b></a> 
        </div>
		</marquee>
		</c:if> 

<!-- 
<c:if test="${showMessageTG eq 'Y' }">
			<marquee SCROLLAMOUNT=3 onmouseover="this.stop();"  onmouseout="this.start();">
				<div>	
					<a style="cursor:pointer;" onClick="window.open('revisedCR.jpg', '_blank')">
						<b><font color="#005000">Revised Time line validation for pending Updation of preauthorization, Surgery update, Discharge update, Claim doctor pending, JEO Pending and EO pending claims of both Aarogyasri and EHS cases (Click here for details)</font></b>
					</a>
					
				</div>
			</marquee>
		</c:if>
	 -->	
		<h2>Notice Board</h2>
		
		<div id="marqueecontainer" onmouseout="copyspeed=marqueespeed" onmouseover="copyspeed=pausespeed">
		<div id="vmarquee" style="position: absolute; width: 98%;">
		<ul>
		
		<!-- <c:if test="${showMessage eq 'Y' }">
		    <li><span class="glyphicon glyphicon-chevron-right arrow"></span>WISH YOU ALL A "HAPPY DOCTOR'S DAY' - Dr.A.Ravi Shankar, CEO Employee Health Scheme </li>
		</c:if> -->
		<c:if test="${isMedco eq 'Y' }">
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span><b style="color:black;">NOTE:Please download the circular <a href="#" onclick="javascript:fn_getManuals(this.id)" id="SCA" style="color:red;">SCA extension up to 31.08.2016</a></b></li>
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span><b style="color:black;">NOTE:Please download the circular<a href="#" onclick="fn_getManuals(this.id)" id="Circular"  title="Circular for fraud alert related to charity scams" style="color:red;"> Fraud Alert : Charity Scams</a></b><img src="../images/new-icon.gif" alt="New" height="22" width="35"></li>
			</c:if>
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span>Preauthorizations kept pending has to be updated within 30 days, otherwise Pre authorization request  will be auto cancelled.  </li>
		    <li><span class="glyphicon glyphicon-chevron-right arrow"></span>For Pre authorization approved cases, surgery has to be updated in 30 days, otherwise pre authorization approval will be auto cancelled. </li>
		    <li><span class="glyphicon glyphicon-chevron-right arrow"></span>Surgery updated cases has to discharged in 60 days, otherwise case will be auto cancelled</li>	
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span>Claims kept pending has to be updated within 30 days, otherwise cases will be reverted back to the respective users who kept the case pending.</li>	
		</ul>
		</div>
		</div>
	</div>
	</c:if>
	<c:if test="${openAM eq 'N'}">
		<div id="noticeboard" class="pull-right col-xs-12 col-sm-12 col-md-12 col-lg-12">
	    
	        <c:if test="${showMessageAP eq 'Y' }">
	   
		<marquee SCROLLAMOUNT=3 onmouseover="this.stop();"  onmouseout="this.start();">
        <div><a href="#" onclick="fn_dentalGuidelines(this.id)" id="Dental Guidelines" onclick="fn_dentalGuidelines(this.id)" title="Dental Guidelines Manual(PDF that opens in a new window)"><b><font color="#005000">Guidelines to NWHs regarding Dental Specialty (S18).Click here To Download</font></b></a></div>
		</marquee>
		</c:if>
		
		 <c:if test="${showMessageTG eq 'Y' }">

		<marquee SCROLLAMOUNT=3 onmouseover="this.stop();"  onmouseout="this.start();">
					
        <div>
        
        	<c:if test="${hospDentType eq 'clinic' || hospDentType eq 'hospital' }">
	        	<a href="#" onclick="fn_NWHGuidelines(this.id)" id="Gudielines03_03_2016" title="Revised Dental Guide lines(PDF that opens in a new window)"><b><font color="#005000">Revised Dental Guide lines (Click here for details) </font></b></a>
	        	<a href="#" onclick="fn_NWHGuidelines(this.id)" id="utilizationGuidelines" title="Revised Dental Guide lines AnnexureII(PDF that opens in a new window)"><b><font color="#005000">(Click here for AnnexureII) </font></b></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        </c:if>	
        	<a href="#" onclick="fn_NWHGuidelines(this.id)" id="EHS" title="NWH Guidelines Manual(PDF that opens in a new window)"><b><font color="#005000">05.10.2015:Circular for all NWH empanelment of all available specialities in the NWHs under Aarogyasri and EHS(click here for details)</font></b></a>
        </div>
		</marquee>
		</c:if> 
		<!--<c:if test="${showMessageTG eq 'Y' }">
			<marquee SCROLLAMOUNT=3 onmouseover="this.stop();"  onmouseout="this.start();">
				<div>	
					<a style="cursor:pointer;" onClick="window.open('revisedCR.jpg', '_blank')">
						<b><font color="#005000">Revised Time line validation for pending Updation of preauthorization, Surgery update, Discharge update, Claim doctor pending, JEO Pending and EO pending claims of both Aarogyasri and EHS cases (Click here for details)</font></b>
					</a>
					
				</div>
			</marquee>
		</c:if> -->
		<h2>Notice Board</h2>
		<div id="marqueecontainer" onmouseout="copyspeed=marqueespeed" onmouseover="copyspeed=pausespeed">
		<div id="vmarquee" style="position: absolute; width: 98%;">
		<ul>
		<!--<c:if test="${showMessage eq 'Y' }">
		    <li><span class="glyphicon glyphicon-chevron-right arrow"></span>WISH YOU ALL A "HAPPY DOCTOR'S DAY' - Dr.A.Ravi Shankar, CEO Employee Health Scheme </li>
		</c:if> -->
		<c:if test="${isMedco eq 'Y' }">
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span><b style="color:black;">NOTE:Please download the circular <a href="#" onclick="javascript:fn_getManuals(this.id)" id="SCA" style="color:red;">SCA extension up to 15.08.2016</a></b><img src="../images/new-icon.gif" alt="New" height="22" width="35"></li>
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span><b style="color:black;">NOTE:Please download the circular<a href="#" onclick="fn_getManuals(this.id)" id="Circular" style="color:red;"> Fraud Alert : Charity Scams</a></b><img src="../images/new-icon.gif" alt="New" height="22" width="35"></li>
			</c:if>
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span>Preauthorizations kept pending has to be updated within 30 days, otherwise Pre authorization request  will be auto cancelled.  </li>
		    <li><span class="glyphicon glyphicon-chevron-right arrow"></span>For Pre authorization approved cases, surgery has to be updated in 30 days, otherwise pre authorization approval will be auto cancelled. </li>
		    <li><span class="glyphicon glyphicon-chevron-right arrow"></span>Surgery updated cases has to discharged in 60 days, otherwise case will be auto cancelled</li>	
			<li><span class="glyphicon glyphicon-chevron-right arrow"></span>Claims kept pending has to be updated within 30 days, otherwise cases will be reverted back to the respective users who kept the case pending.</li>	
		</ul>
		</div>
		</div>
	</div>
	</c:if>
	<div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<c:if test="${openAM eq 'Y'}">
	<div class="otherLinks pull-right col-xs-12 col-sm-12 col-md-9 col-lg-9">
</c:if>
<c:if test="${openAM eq 'N'}">
	<div class="otherLinks pull-right col-xs-12 col-sm-12 col-md-12 col-lg-12">
</c:if>

<div id="imageLinks" class="pull-right col-xs-12 col-sm-12 col-md-4 col-lg-4">
<ul class="list-group">
  	<li><a href="#email" onclick="javascript:fn_emailSubscription();" data-toggle="modal"><span class="glyphicon glyphicon-envelope"></span>Email Alerts</a></li>
  	<li><a href="#digitalSign" onclick="javascript:fn_digitalSignatureReg();" data-toggle="modal"><span class="glyphicon glyphicon-certificate"></span>Digital Certificate Registration</a></li>
	<c:if test="${openAM eq 'Y'}">
	 <li><a href="#mailList" onclick="javascript:fn_gotoMail();" data-toggle="modal"><span class="glyphicon glyphicon-inbox"></span>Mailbox</a></li>
	<!-- <li><a href="#digitalSign" onclick="javascript:fn_digitalSignatureReg();" data-toggle="modal"><span class="glyphicon glyphicon-certificate"></span>Digital Certificate Registration</a></li> -->
	<li><a href="#cms" onclick="javascript:showCMS();" data-toggle="modal"><span class="glyphicon glyphicon-cog"></span>CMS</a></li> 
	</c:if>
	<!--<li><a href="#KC" data-toggle="modal"><span class="glyphicon glyphicon-book"></span>Knowledge Center</a></li>-->
	<li><a href="#104Services" data-toggle="modal"><span class="glyphicon glyphicon-phone-alt"></span>Need Help</a></li> 
</ul>
</div>
<div class="panel-group pull-right col-xs-12 col-sm-12 col-md-8 col-md-8" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading" id="trainingHead">
      <h2 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
          FAQ's
        </a>
      </h2>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in"> 
      <div class="panel-body">
        <ul>
				<li><span class="glyphicon glyphicon-chevron-right arrow"></span><a href="#">What is Registration ?</a>
				<ul><li><span class="glyphicon glyphicon-chevron-right arrow"></span>Registration is the process involving collection of data about new patients to start generating a medical record.</li></ul></li>
				<li><span class="glyphicon glyphicon-chevron-right arrow"></span><a href="#">Who will do the registration in hospital ?</a>
				<ul><li><span class="glyphicon glyphicon-chevron-right arrow"></span>A facilitator called 
				<c:choose><c:when test="${loggedUserState eq 'CD201' }" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya Mithra
							</c:otherwise>
						</c:choose> will be available in the hospitals to register, facilitate the treatment and guide you for all the services.</li></ul></li>
		</ul>
		<p style="float:right;padding:0 5px"><a href="#faq" data-toggle="modal">...more</a></p>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" id="trainingHead">
      <h2 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
          Training Material
        </a>
      </h2>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse">
      <div class="panel-body">
        
      <ul>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Claims Processing"  title="Claims Processing(PDF that opens in a new window)"> 1. Claims Processing material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Followup Claims" title="Followup Claims(PDF that opens in a new window)"> 2. Followup Claims material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Registration Usermanual" title="Registration Usermanual(PDF that opens in a new window)"> 3. Registration  material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="IP and OP Registration and Preauthorisation Process" title="IP & OP Registration and Preauthorisation(PDF that opens in a new window)"> 4. IP and OP Registration and Preauthorisation Process material</a></li>
				<li><a href="#" onclick="fn_getManuals(this.id)" id="Chronic OP User Manual" title="Chronic OP User Manual(PDF that opens in a new window)"> 5. Chronic OP User Manual</a></li>
		
		</ul>
      
      </div>
    </div>
  </div>
</div>

<div class="clearfix"></div>
</div>

<!-- MODALS  -->
<!-- Modal for FAQ  --> 
<div class="modal fade" id="faq"> 
  <div class="modal-dialog">
    <div class="modal-content"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">FAQ's</h2>
      </div>
      <div class="modal-body">
    	  <c:choose>
    	  		<c:when test="${loggedUserState eq 'CD201' }" >
					<iframe src="../staticpages/FAQAP.html" frameborder="0" width="100%" height="345px"></iframe>		
				</c:when>
				<c:otherwise >
					<iframe src="../staticpages/FAQ.html" frameborder="0" width="100%" height="345px"></iframe>			
				</c:otherwise>
		  </c:choose>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Modal for 104 services  -->  
<div class="modal fade" id="104Services"> 
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Medical health related Services available to the residents @ 104</h2>
      </div>
      <div class="modal-body">
        <iframe src="../staticpages/Services104.html" frameborder="0" width="100%" height="345px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Modal for CMS 
<div class="modal fade" id="cms"> 
  <div class="modal-dialog  modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">CMS</h2>
      </div>
      <div class="modal-body">
        <iframe id="cmdFrameId" frameborder="0" width="100%" height="400px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>/.modal-content --> 
  <!--</div> /.modal-dialog -->
<!--</div> /.modal -->
<!-- Modal for Knowledge Center  -->
<div class="modal fade" id="KC"> 
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Knowledge Center</h2>
      </div>
      <div class="modal-body">
        <iframe src="../staticpages/KnowledgeCentre.html" frameborder="0" width="100%" height="345px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Modal for Email  -->
<div class="modal fade" id="email"> 
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Email Subscription</h2>
      </div>
      <div class="modal-body">
        <iframe id="emailIframe" frameborder="0" width="100%" height="345px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Modal for mailbox 
<div class="modal fade" id="mailList"> 
  <div class="modal-dialog modal-lg">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Mailbox</h2>
      </div>
      <div class="modal-body">
        <iframe id="mailListIframe" frameborder="0" width="100%" height="400px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div> /.modal-content --> 
  <!--</div> /.modal-dialog -->
<!--</div> /.modal -->
<!-- Modal for Digital Signature  -->
<div class="modal fade" id="digitalSign"> 
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title">Digital Certificate Registration</h2>
      </div>
      <div class="modal-body">
        <iframe id="digitalSignIframe" frameborder="0" width="100%" height="345px"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>