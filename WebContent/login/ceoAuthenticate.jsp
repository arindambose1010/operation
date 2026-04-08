
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<fmt:setLocale value='en_US'/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OTP authentication </title>
<%@include file="/common/include.jsp" %>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-select.min.css">
<link href="/<%=context%>/bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="/<%=context%>/css/themes/navyblue/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="/<%=context%>/bootstrap/css/font-awesome.css" rel="stylesheet">
<script  src="/<%=context%>/bootstrap/js/modernizr.min.js"></script>
<script  src="/<%=context%>/bootstrap/js/css3-mediaqueries.js"></script> 
<script  src="/<%=context%>/bootstrap/js/html5.js"></script>
<script src="/<%=context%>/bootstrap/js/respond.min.js"></script>
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="/<%=context%>/bootstrap/js/bootstrap.min.js"></script>
<script src="/<%=context%>/bootstrap/js/formValidation.min.js"></script>
<script src="/<%=context%>/bootstrap/validator/bootstrap.min.js"></script>

<script src="/<%=context%>/bootstrap/js/fileinput.min.js"></script>
<link href="/<%=context%>/bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen">
<%@ include file="/common/includeBootstrapCalendar.jsp"%> 
	
<style>
.page-header{
	background-color : #F8F8F8;
	background-height : 15%;
}
.list-group-item{
border:0;
}
#fdiv2 .table,th,tr,td{border:0;}
.header{
	
	color:#135B95;
	font-size:2em;
	font-family:Times New Roman;
	text-align:center;
}
.modal-content {
    /* ... */
    border: 0.1em solid #428BCA;border-radius:3px;
    overflow-y: auto;
    overflow-x:scroll;
}
.step{background-color:#428BCA;border-radius:3px;color:#ffffff;font-weight:bold}
.header sup{
	color:#F10104;
}

.list-group a{
	color : #ffffff;
	text-decoration:none;
}

.list-group a:hover{
	color : #ffffff;
	text-decoration:underline;
}
.login-container{
	border-radius:10px;
}
</style>
<style>

.login div.form-group {
    margin: 0;
    border-left: 1px solid #bcbcbc;
}
.login div.form-group a {
    margin:  0 0 0;
}
.login div.form-group label {
    padding: 5px;
}
.moduleName {
    text-align: center;
}
.moduleName h1 {
    font-size: 25px;
    padding: 10px;
    text-shadow: 2px 2px 1px white;
}
.moduleName h2 {
    font-size: 20px;
    
    color: #428bca;
    text-shadow: 2px 2px 1px white;
}
.signIn label {
    font-size: 20px;
    font-style: italic;
    color: #8F0E0E;
    text-shadow: 2px 2px 1px white;
}
.login {
    background: none repeat scroll 0 0 #BBDDFF;
    border: 1px solid #bcbcbc;
    border-radius: 12px;
    margin-top:8%;
    padding: 10px;
     width: 85%;
}


.but{
border-radius:16px;
}
</style>

<script>

$(document).ready(function() {
	 
	 /* Initialize your widget via javascript as follows */
	
	
   $('#loginForm').formValidation({
   	framework: 'bootstrap',
       icon: {
           valid: 'glyphicon glyphicon-ok',
           invalid: 'glyphicon glyphicon-remove',
           validating: 'glyphicon glyphicon-refresh'
       },
       fields: {
           otp: {
               validators: {
                   notEmpty: {
                       message: 'OTP is required'
                   },
                   identical: {
                       field: 'otpTemp',
                       message: 'OTP is not matched with the sent one.Please check'
                   }
               }
           } }
    });
});









var otp=null;
function enableOtp()
{
	
	if(document.getElementById("otpVerify").checked)
	{

		generateOtp();
		document.getElementById("otpLabel").style.display="block";
		document.getElementById("buttons").style.display="block";
	}
}
function disableOtp()
{
	
	if(document.getElementById("digiVerify").checked)
	{

		document.getElementById("otpLabel").style.display="none";
		document.getElementById("otpLabel").disabled=true;
		document.getElementById("otpSent").style.display="none";
		document.getElementById("otpFail").style.display="none";
		document.getElementById("otpValue").value="";
		document.getElementById("buttons").style.display="block";
	}
}

function generateOtp(){


	var mobileNum=document.getElementById("mobileNum").value;
	
	var xmlhttp;var url;
   if (window.XMLHttpRequest)
   {xmlhttp=new XMLHttpRequest();}
   else if (window.ActiveXObject)
   {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
   else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
 xmlhttp.onreadystatechange=function()
   {
       if(xmlhttp.readyState==4)
       {
           var resultArray=xmlhttp.responseText;
           
           if(resultArray.trim()=="SessionExpired*"){
           	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
           }
           else
           {
           	if(resultArray!=null)
           	{
           		
             otp=resultArray;         
               
               	if(otp.length>0)
               	{  
                  document.forms[0].otpTemp.value=otp;  
               	 document.getElementById('otpSent').style.display="block";
                     //alert("An OTP has been successfully generated and  sent to your Registered Mobile number");
               	
               		
               		
           		}
               	if(otp.length==0)
               	{  
                    
                   // alert("An error occured while generating OTP.Please Try Again");
               	 document.getElementById('otpFail').style.display="block";
               		
               		
               		
           		}
           	}
       	}
       }
   }

	url = '/Operations/loginAction.do?actionFlag=generateOTP&callType=Ajax&mobileNum='+mobileNum;
	
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
}
</script>	
<script>
function fn_loginUser()
{

	var length=null;
if(otp!=null){
	length=otp.length;
}	

	if(document.getElementById("digiVerify").checked)
	{


		document.forms[0].action='/<%=context%>/loginAction.do?actionFlag=checkLogin&digiCheck=Y' ;
		
		document.forms[0].submit(); 
	}


	if(document.getElementById("otpVerify").checked &&  length!=null && length>0)
	{
var otpGenerated=otp;

var otpEntered=document.forms[0].otp.value;

if(otpEntered==null || otpEntered=="")
{
	 document.getElementById("otpValue").focus();
return false;
}
if(otpEntered!=otpGenerated)
{
	 document.getElementById("otpValue").value="";
	 document.getElementById("otpValue").focus();
return false ;

}
else
{
	

	document.forms[0].action='/<%=context%>/loginAction.do?actionFlag=checkLogin&otpCheck=Y';

	document.forms[0].submit(); 
}
		
	}
}
function fn_resetUser(){
	document.loginForm.otp.value="";
	document.getElementById("otpVerify").checked=false;
	document.getElementById("digiVerify").checked=false;
	
	return false;
}
function close(){
	document.getElementById('otpSent').style.display="none";
}
function close1(){
	document.getElementById('otpFail').style.display="none";
}
function close2(){
	document.getElementById('otpMismatch').style.display="none";
}
function close3(){
	document.getElementById('otpEmpty').style.display="none";
}
function removeAlert()
{
	document.getElementById('otpSent').style.display="none";
	document.getElementById('otpFail').style.display="none";
}
</script>

	</head>
	
<body>
	
	

	
<html:form  styleId="loginForm" method="post"  action="/loginAction.do" > 
<div class="container login">

		<div class="moduleName col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
  	<h2>Employees Health Scheme</h2>
  	</div><br>
  	<div class="row">
  	<div class=" signIn form-group col-xs-12 col-sm-12 col-md-12 col-lg-12  " >
  	<img src="/<%=context%>/images/lock1.png" style="width:25px;height:25px;"><label id="signIn">Sign In</label>
  </div>
  	</div>
	<br><br>
				
						<div class="row">
								
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-2  " >
							<label  id="mobile" ><span class="glyphicon glyphicon-phone"></span>Mobile Number </label>
						</div>	
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-3 form-group tbcellCss3   " >
							<label  id="mobileNo" > 
								<html:text name="loginForm"  styleClass="form-control" property="mobileNo" title="Mobile Number"  styleId="mobileNum"  style="width:255px" disabled="true"></html:text>
								
							</label>
							
							</div>
						
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-2  " >
							<label  id="email"><span class="glyphicon glyphicon-envelope"></span> Email Id </label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-5 form-group   " >	
							<label  id="emailId" > 
								<html:text name="loginForm"  styleClass="form-control" property="emailId" title="emailId" styleId="emailId" style="width:255px" disabled="true"></html:text>
								
							</label>
							
							</div>
							
						</div>	
				<br><br>						
						
						<div class="row">	
						<div class="col-xs-12 col-sm-12  col-md-12 col-lg-12 " >
							
							
							<label  id="digiRadio" > 
								<html:radio name="loginForm"   property="otpAuthenticate" title="click here to login using Digital Certificate"  onchange="javascript:disableOtp();" styleId="digiVerify" value="Y">Yes,I Have Digital Certificate   <span class="glyphicon glyphicon-certificate"></span></html:radio>
								
							</label>
							
							</div>
		 	  			</div>
		 	<br>  				
		 	  				<div class="row">		 		
		            	<div class="col-xs-12 col-sm-12  col-md-12 col-lg-12 form-group" >
							
				
							<label  id="otpRadio" > 
								<html:radio name="loginForm"   property="otpAuthenticate" title="click Here to send OTP"  onchange="javascript:enableOtp();" styleId="otpVerify" value="N">No,I dont have Digital Certificate.Authenticate using OTP (One-Time Password)  <span class="glyphicon glyphicon-phone"></span></html:radio>
								
							</label>

							
							
							
								<label  id="otpLabel" style="display:none" > Enter OTP here <span class="glyphicon glyphicon-circle-arrow-down"></span>
								<html:text name="loginForm"    styleClass="form-control" property="otp" title="enter OTP Here "  onkeypress="javascript:removeAlert();" styleId="otpValue"  style="width:150px" maxlength="8"></html:text>
								
							</label>
							
							<label  id="otpLabel2" style="display:none" > 
								<html:text name="loginForm"    styleClass="form-control" property="otpTemp" title="OTPTemp"  styleId="otpValue2"  maxlength="8"></html:text>
								
							</label>
							
							
								<label  id="otpSent" style="display:none" ><i><font color="#317531">An OTP has been successfully generated and  sent to your Registered Mobile number</font></i>
							    </label>
							
								<label  id="otpFail" style="display:none" ><i><font color="#317531">An error occured while generating OTP.Please Try Again</font></i>
							    </label>
						

							
							
							
							</div>
						</div>
						
						<br><br>
						
						
						
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center" style="display:none;" id="buttons">
         <button type="button" class="btn but" value="Submit"  onclick="javascript:fn_loginUser();" align="center">Login</button>
         <button type="button" class="btn but" value="Reset" onclick="fn_resetUser();" align="center" >Reset</button>
   </div>
						
						
						
					
		 	  				 		
								</div></html:form></body>
							
						 
	</html>