
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

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">

<%@include file="/common/include.jsp" %>

<title>BioMetric Attendance</title>
<style>
.login {
    background: none repeat scroll 0 0 #EBEBEB;
    border: 1px solid #bcbcbc;
    border-radius: 12px;
    margin-top:2%;
    padding: 10px;
     width: 65%;
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
    font-weight: bold;
    font-family: sans-serif;
    color:#20AFA7;
    padding-bottom:5%;
    
}
.but{
border-radius:16px;
background-color:#fa8564;
padding-top:2px;
}
.centerone{
 border: 2px solid #a1a1a1;
    
 background: none repeat scroll 0 0 #20AFA7;
border-radius: 12px;
width:70%;
padding-bottom:20px;
padding-bottom:10px;
}
.rightone{
padding-right:10px;
float:right;
}
#patImage{
border: 2px solid #a1a1a1;
border-radius: 25px;
}
body
{
color:#fff;

    white-space: nowrap;
}
#upload{
color:#20AFA7;
}
</style>
<!--  <script language="vbscript" type="text/vbscript">  
      Function GetFeatureAccrual (IdOfHiddenTextField)
	
	Dim Obj

	If (IdOfHiddenTextField Is Nothing) Then

		MsgBox "Invalid Hidden Field Argument Passed", vbExclamation, "Web API BMA Application"
	Else

		Set Obj = CreateObject ("Web_API_3.Legend")

		If (Obj Is Nothing) Then

			MsgBox "Unable To Create Instance For Web API, Check Dll Is Registered Properly", vbExclamation, "Web API BMA Application"
		Else

			IdOfHiddenTextField.Value = ""

			Obj.GetFeatureAccrual

			IdOfHiddenTextField.Value = Obj.Feature

			If IdOfHiddenTextField.Value <> "" Then
				MsgBox "Fingerprint Captured Successfully.", vbInformation, "Web API BMA Application"
			Else
				MsgBox "Fingerprint Not Captured Successfully", vbExclamation, "Web API BMA Application"
			End If

		End If

	End If

End Function 
    </script>-->
    


</head>
<body>
<html:form  styleId="bioMetricForm" method="post"  action="/bioMetricAction.do"  enctype="multipart/form-data" > 


<logic:notEmpty name="bioMetricForm" property="photoUpload">

</logic:notEmpty>

<c:if test="${isRegistered eq 'N'}">
<script>
alert("You are not Enrolled for Biometric Attendance.Please try again");
parent.window.location.href= "loginAction.do?actionFlag=checkLogin"; 	

</script>
</c:if>

<c:if test="${isRecord eq true}">
<script>
var msg="${ResultMsg}";
alert(msg);
parent.fn_biometricAtt();

</script>
</c:if>

<c:if test="${isMatched eq false}">
<script>

alert("FingerPrints not Matched.Please try Again");
	

</script>
</c:if>

<c:if test="${macId eq 'N' && roleId eq 'GP1'}">
<script>
alert("Unable to process your request as System MacId is not mapped with the Hospital");
parent.window.location.href= "loginAction.do?actionFlag=checkLogin";
	//parent.fn_biometricAtt();	

</script>
</c:if>

<div class="container login">

	<div class="moduleName col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
  	<h2><span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;&nbsp;Biometric Attendance Screen&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-hand-left"></span>&nbsp;</h2>
  	</div><br><br><br>






<div class="container center-block">
		<div class="centerone" >				
						<div class="row" id="attendUser">
								
						<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4 " >
							<label  id="name" ><span class="glyphicon glyphicon-user"> </span>&nbsp;Attendance of :</label>
						</div>	
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group tbcellCss3   " >
							<label  class="radio-inline"> 
							<input type="radio" name="attendanceUser" value="s" Id="self" onclick="disableOthers()">Self</label>
							
							<c:if test="${roleId eq 'GP1'}">
							<label  class="radio-inline"> 
							<input type="radio" name="attendanceUser" value="o" Id="others"  onclick="enableOthers();getOthersList();">Others
							</label>	
							</c:if>
								
							
							
					</div>
							
    
    
						</div>
						
							<div class="row" style="display:none;" id="othersList">
								<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4  " >
									<span class="glyphicon glyphicon-briefcase"></span><label>&nbsp;Other Users</label>
								</div>
					
								<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6" >
									  <select class="form-control" id="otherUser" name="otherUser"  >
									  </select>
									</div>
						
							</div>
								<div class="row" >
						<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4  " >
							<span class="glyphicon glyphicon-briefcase"></span><label>&nbsp;Attendance Type :</label>
						</div>
					
					<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6">
									  
									  <select class="form-control" id="attendType" name="attendType" onchange="javascript:validateAttendance();" >
									    <option value="-1">----Select----</option>
									    <option value="0">Log-In Time</option>
									    <option value="1">Log-Out Time</option>
									  </select>
									</div>
							
						</div>	
						
			
						
				
							
						
			<div style="height:1%"></div>		
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center"  id="buttons" style="padding-top: 2%; padding-right: 30%; padding-left: 3%;">
         <button type="button" class="btn but" value="Submit"  onclick="javascript:fn_capture();" align="center">Capture&nbsp;<span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;</button>
         <button type="button" class="btn but" value="Reset" onclick="fn_resetUser();" align="center" >Reset&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;</button>
   </div>								
	
	</div>	</div>				
					<br><br>	
	





						 <input type="hidden" name="size" id="size" value="2097152">
						  <html:hidden name="bioMetricForm" property="userId"></html:hidden>
 <html:hidden name="bioMetricForm" property="loginName"></html:hidden>
  <html:hidden name="bioMetricForm" property="hospId"></html:hidden>
   <html:hidden name="bioMetricForm" property="hospMacId"></html:hidden>
   <html:hidden name="bioMetricForm" property="fingerPrint"></html:hidden>
    <html:hidden name="bioMetricForm" property="photoUrl"></html:hidden>









</html:form>


<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="js/LegendScript.js"></script>
 
    
<script>
$('#bioMetricForm').formValidation({
	framework: 'bootstrap',
	icon: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {

    	address: {
        validators: {
            notEmpty: {
                message: 'Present Address is Mandatory'
            },
            regexp: {
                regexp: /^[a-zA-Z0-9, ]+$/,
                message: 'Present Address can only consist of alphanumeric characters'
            }
        }
    },

    permAddress: {
        validators: {
            notEmpty: {
                message: 'Permanent Address is Mandatory'
            },
            regexp: {
                regexp: /^[a-zA-Z0-9, ]+$/,
                message: 'Permanent Address can only consist of alphanumeric characters'
            }
        }
    },

    
    mobNumber :{
    	validators: {
            notEmpty: {
                message: 'Mobile number is required'
            },
            number: {
                message: 'Input is not a valid mobile number'
            },
            stringLength: {
                min: 10,
                max: 10,
                message: 'Mobile number must be 10 digits.'
            }
        }

       },
       photoAttach :{
       validators: {
    	   notEmpty: {
               message: 'Photo is required'
           },
           file: {
               extension: 'jpeg,png',
               type: 'image/jpeg,image/png',
               maxSize: 2097152,   // 2048 * 1024
               message: 'Selected file is not valid'
           }
       }
       }
    }
});
</script>
    <script type="text/javaScript">


    function fn_resetUser()
    {
document.getElementById('self').checked=false;
document.getElementById('others').checked=false;
document.getElementById('otherUser').value='-1';
document.getElementById('attendType').value='-1';
    }

    function fn_capture()
    {
    	document.getElementById("buttons").disabled=true;
var self=document.getElementById("self").checked;
var roleId='${roleId}';

if(roleId !=null && roleId == 'GP1')
var others=document.getElementById("others").checked;
var attendanceType=document.forms[0].attendType.value;

if(others)
	var otherUser=document.forms[0].otherUser.value;

if(!self && !others)
{
	alert("please select any Radio Button");
	document.getElementById("self").focus();
	return false;
}
if(others && (otherUser==null || otherUser=="" || otherUser==-1))
		{

	alert("please select other User");
	document.getElementById("otherUser").focus();
	return false;

		}
if(attendanceType==null || attendanceType=="" || attendanceType==-1)
{
	alert("please select Attendance Type");
	document.getElementById("attendanceType").focus();
	return false;
}












    	

/* 
var fingerPrint="CVERFDGVNERJIOFNDIOHNERIOJGIODFNBUIONGIFMIOFGBETGEGRTGHDBTFG";
document.forms[0].fingerPrint.value=fingerPrint; */
GetFeature(document.forms[0].fingerPrint);
var fingerPrint=document.forms[0].fingerPrint.value; 
//document.forms[0].fingerPrint.value="WERSGHERRWERTRFFMNWDIORMVOINIOGRMTBIOIOEMKRIOVWE";


if(fingerPrint!=null && fingerPrint!='')
{

    var otherUserId = document.getElementById("otherUser").value;
	document.forms[0].action="/<%=context%>/bioMetricAction.do?actionFlag=recordAttendance&otherUserId="+otherUserId;
	document.forms[0].submit();

        }

    }


function enableOthers()
{
	document.getElementById("othersList").style.display="";
}
function disableOthers(){

	document.getElementById("othersList").style.display="none";
}






function validateAttendance()
{
if(document.getElementById("self").checked)
var userType=document.getElementById("self").value;
else
var userType=document.getElementById("others").value;
var attendType=document.forms[0].attendType.value;
var hospId=document.forms[0].hospId.value;
var otherUser=document.forms[0].otherUser.value;
	if(document.getElementById("attendType").value=="-1")
		{
		return false;
		}
	else
		{
	
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
         
            if((resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null && resultArray!="")
            	{
                	if(resultArray=="0")
                    	alert("You have Already recorded LogIn Details");
                	else if(resultArray=="1")
                    	alert("Logout Details cannot be recorded prior to Login Details");
                	else if(resultArray=="2")
                    	alert("You have Already recorded LogOut Details");
                	 
                	document.getElementById("attendType").value="-1";
                
            	}
        	}
        }
    }
    	
	url = "/<%=context%>/bioMetricAction.do?actionFlag=validateAttendance&userType="+userType+"&attendType="+attendType+"&hospId="+hospId+"&otherUser="+otherUser;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}

function getOthersList()
	{
		var hospId=document.forms[0].hospId.value;
		var otherUser=document.forms[0].otherUser.value;
		
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
	    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
			alert("Your browser does not support XMLHTTP!");
	    }
	  
	    xmlhttp.onreadystatechange=function()
		{
    		if(xmlhttp.readyState==4)
    		{	
    			document.forms[0].otherUser.options[0]=new Option('-------- Select --------',"");
       	 		var resultArray=xmlhttp.responseText;

        		if(resultArray!= null)
        		{
        			 resultArray2 = resultArray.replace("[","");
	         		resultArray2 = resultArray2.replace("]","");   
	         		var addList = resultArray2.split(", @");
	         		if(addList.length>0)
	         		{  
	         			var i=0;
	                	for(i = 0; i<addList.length;i++)
	                	{	
	                   		 var arr=addList[i].split("~");
	                 
	                     	if(arr[1]!=null && arr[0]!=null)
	                    	 {
	                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                     	 	document.forms[0].otherUser.options[i+1] = new Option(val1,val2);  
					     	} 
	                	}
	                	
	            	} 
        		}
    		}			
		}
		
		url = "/<%=context%>/bioMetricAction.do?actionFlag=getOthersList&hospId="+hospId+"&otherUser="+otherUser;
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		
	}

function checkOthers()
{


var hospId=document.forms[0].hospId.value;
var otherUser=document.forms[0].otherUser.value;
	if(document.getElementById("otherUser").value=="-1")
		{
		return false;
		}
	else
		{
	
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
         
            if((resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null && resultArray!="")
            	{
                	if(resultArray=="0")
                    	alert("No District Coordinator is mapped to this Hospital");
                	else if(resultArray=="1")
                    	alert("No District Manager is mapped to this Hospital");
                	else if(resultArray=="2")
                    	alert("No Network TeamLeader is mapped to this Hospital");
                	 
                	document.getElementById("otherUser").value="-1";
                
            	}
        	}
        }
    }
    	
	url = "/<%=context%>/bioMetricAction.do?actionFlag=checkOthers&hospId="+hospId+"&otherUser="+otherUser;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}


    </script>


</body>
</html>