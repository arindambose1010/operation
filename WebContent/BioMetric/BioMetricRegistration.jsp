
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
<meta http-equiv="X-UA-Compatible" content=" IE=edge">

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<%@include file="/common/include.jsp" %>


<title>BioMetric Registration</title>
<style>
.login {
    background: none repeat scroll 0 0 #EBEBEB;
    border: 1px solid #bcbcbc;
    border-radius: 12px;
    margin-top:2%;
    padding: 1px;
     width: 85%;
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
.leftone{
 border: 2px solid #a1a1a1;
    padding: 20px; 
  background: none repeat scroll 0 0 #20AFA7;
    border-radius: 25px;


width:60%;
float:left;
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
<script>
alert("Photo Uploaded successfully");
</script>
</logic:notEmpty>

<c:if test="${macId eq 'N' && roleId eq 'GP1'}">
<script>
alert("Unable to process your request as System MacId is not mapped with the Hospital");
parent.window.location.href= "loginAction.do?actionFlag=checkLogin";     


</script>
</c:if>

<c:if test="${isRegistered eq 'Y'}">
<script>
alert("Biometric data is already captured");
//location.reload();
	parent.window.location.href= "loginAction.do?actionFlag=checkLogin";

</script>
</c:if>

<c:if test="${isEnroll eq true}">
<script>
alert("Biometric data captured Successfully ");
parent.window.location.href= "loginAction.do?actionFlag=checkLogin";
</script>
</c:if>


<c:if test="${isEnroll eq false}">
<script>
alert("Biometric data capture Failed.Please try Again");
parent.fn_biometricCap();
</script>
</c:if>

<div class="container login">

	<div class="moduleName col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
  	<h2><span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;&nbsp;Welcome To Biometric Enrollment Screen&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-hand-left"></span>&nbsp;</h2>
  	</div><br><br><br>






<div class="container center-block">
		<div class="leftone">				
						<div class="row">
								
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6  " >
							<label  id="name" ><span class="glyphicon glyphicon-user"> </span>&nbsp;User Name :</label>
						</div>	
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group tbcellCss3   " >
							<label  id="userName" > 
							<bean:write name="bioMetricForm" property="userName"></bean:write>
								
								
							</label>
							
							</div>
							
    
    
						</div>
						
						
								<div class="row">
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6  " >
							<span class="glyphicon glyphicon-briefcase"></span><label>&nbsp;Department :</label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group   " >	
							<label > 
								<bean:write name="bioMetricForm" property="department"></bean:write>
								
							</label>
							
							</div>
							
						</div>	
						
						<div class="row">
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6  " >
							<span class="glyphicon glyphicon-pencil"></span><label>&nbsp;Designation :</label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group   " >	
							<label > 
									<c:choose>
										<c:when test="${loggedUserState eq 'CD201' && bioMetricForm.designation eq 'Aarogya Mithra'}" >
											VAIDYA MITHRA
										</c:when>
										<c:otherwise >
											<bean:write name="bioMetricForm" property="designation"></bean:write>
										</c:otherwise>
									</c:choose>
								
							</label>
							
							</div>
							
						</div>
						
				
					
						
						<div class="row">
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6  " >
							<span class="glyphicon glyphicon-phone"></span><label>&nbsp;Mobile Number :<font color="red"> *</font></label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group   " >	
							<label > 
								<html:text name="bioMetricForm"  styleClass="form-control" property="mobNumber" title="Mobile Number" styleId="mobNumber"></html:text>
								
							</label>
							
							</div>
							
						</div>	
						
						<div class="row">
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6  " >
							<span class="glyphicon glyphicon-map-marker"></span><label>&nbsp;Present Address :<font color="red"> *</font></label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group   " >	
							<label > 
								<html:textarea name="bioMetricForm"  styleClass="form-control" property="address" title="Present Address" styleId="address" ></html:textarea>
								
							</label>
							
							</div>
							
						</div>	
						
						<div class="row">
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6  " >
							<span class="glyphicon glyphicon-home"></span><label>&nbsp;Permanent Address :<font color="red"> *</font></label>
						</div>
						
						<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 form-group   " >	
							<label > 
								<html:textarea name="bioMetricForm"  styleClass="form-control" property="permAddress" title="Permanent Address" styleId="permAddress"  ></html:textarea>
								
							</label>
							
							</div>
							
						</div>
						
				
							
						</div>
					
					
	<div class="rightone" style="width: 320px;">				
					
						<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3 form-group tbcellCss3   " >					
<logic:notEmpty name="bioMetricForm" property="photoUrl">
<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')" style="margin-left: 30px;"/>
</logic:notEmpty>
<logic:empty name="bioMetricForm" property="photoUrl">
<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
  <div id="upload">
		<span class="glyphicon glyphicon-camera"></span><label >&nbsp;Upload Your Photo </label>
		</div>
	
		<div >
		 <input type="file" id="photoInputFile"  name="photoAttach" id="photoAttach" data-show-preview="true">
    </div><br>
    <div id="uploadButton" style="align:center;margin-left: 30px;" >
 <button type="button" class="btn but" value="Submit"  onclick="javascript:fn_uploadPhoto();" align="center">Upload&nbsp;<span class="glyphicon glyphicon-upload"></span>&nbsp;</button>
</div>
</logic:empty>	
</div>
	</div>	</div>				
					<br><br>	
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center"  id="buttons">
         <button type="button" class="btn but" value="Submit"  onclick="javascript:fn_enroll();" align="center">Enroll&nbsp;<span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;</button>
         <button type="button" class="btn but" value="Reset" onclick="fn_resetUser();" align="center" >Reset&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;</button>
   </div>		
<br><br>

</div>


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
            notNull: {
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
               extension: 'jpeg,png,jpg',
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
    function fn_uploadPhoto()
    {
    var photo=document.forms[0].photoAttach.value;
    if(photo==null)
    {
        alert("please select any photo to upload");
        return;
    }
    else
    {

    	//$('#bioMetricForm').formValidation().formValidation('validate');
    	//var x=$('#bioMetricForm').data('formValidation').isValid();
        //if(x){
document.forms[0].action="/<%=context%>/bioMetricAction.do?actionFlag=uploadPhoto";
document.forms[0].submit();
        //}
    }
    }

    function fn_resetUser()
    {
document.forms[0].mobNumber.value="";
document.forms[0].address.value="";
document.forms[0].permAddress.value="";
    }

    function fn_enroll()
    {

document.getElementById("buttons").disabled=true;

var photo=document.forms[0].photoUrl.value;


//var fingerPrint="CVERFDGVNERJIOFNDIOHNERIOJGIODFNBUIONGIFMIOFGBETGEGRTGHDBTFG";
//document.forms[0].fingerPrint.value=fingerPrint;
GetFeatureAccrual(document.forms[0].fingerPrint);
var fingerPrint=document.forms[0].fingerPrint.value;
//document.forms[0].fingerPrint.value="WERSGHERRWERTRFFMNWDIORMVOINIOGRMTBIOIOEMKRIOVWE";



$('#bioMetricForm').formValidation().formValidation('validate');
var x=$('#bioMetricForm').data('formValidation').isValid();

if(fingerPrint!=null && fingerPrint!='' && x && photo!=null)
{


	document.forms[0].action="/<%=context%>/bioMetricAction.do?actionFlag=enrollUser";
	document.forms[0].submit();

        }

    }
    </script>
<script type="text/javascript" src="js/patientscripts.js"></script>

</body>
</html>