<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<html>
<head>
<title>Change Password</title>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/pswdStrengthIndicator.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<style>body{font-size:1.2em !important}</style>
<script>
function passwordStrength(password)
{
        var desc = new Array();

        desc[0] = "Very Weak";

        desc[1] = "Weak";

        desc[2] = "Better";

        desc[3] = "Medium";

        desc[4] = "Strong";

        desc[5] = "Strongest";

        var score   = 0;

        //if password bigger than 6 give 1 point

        if (password.length > 6) score++;

        //if password has both lower and uppercase characters give 1 point      

        if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/) ) ) score++;

        //if password has at least one number give 1 point

        if (password.match(/\d+/)) score++;

        //if password has at least one special caracther give 1 point

        if ( password.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/) ) score++;

        //if password bigger than 12 give another 1 point

        if (password.length > 8) score++;
        if (password.length > 12) {jqueryAlertMsg('Alert',"Password max. length is 12 characters");
        document.forms[0].newPassword.value=document.forms[0].newPassword.value.substr(0,12)
        };

         document.getElementById("passwordStrength").innerHTML = desc[score];

         document.getElementById("passwordStrength").className = "strength" + score;

}

function clearAllPasswords()
{
      document.forms[0].oldPassword.value = "";
      document.forms[0].newPassword.value = "";
      document.forms[0].ReEnterNewPassword.value = "";  
      document.forms[0].oldPassword.focus();
}
function focusBox(arg)
	{
		aField=arg;
		setTimeout("aField.focus()",0);
	}
function validateChangePassword()
{
	val = document.forms[0].oldPassword.value;
	var id=document.forms[0].oldPassword;
      if(val == "")
      {
            alert('<bean:message key="label.PWD.kmsMsgOldPwd" />');
            focusBox(id);
            //clearAllPasswords();
            return false;
      }
    
      val = document.forms[0].newPassword.value;
       id=document.forms[0].newPassword;
        if(val == "")
      {
            alert('<bean:message key="label.PWD.kmsMsgNewPwd" />');
            focusBox(id);
            return false;
      }
      if(val.length<8)
      {
          alert('New password should be atleast of 8 characters');
         focusBox(id);
          return false;
    }
      ret1 = checkForNumber(val);
      if(!ret1)
      {
            alert('<bean:message key="label.PWD.kmsMsgNewPwdNumeric" />');
            focusBox(id);
            return false;		
      }
      ret2 = checkForSpecialChrs(val);
      if(!ret2)
      {
            alert('<bean:message key="label.PWD.kmsMsgNewPwdSplChrs" />');
            focusBox(id);
            return false;		
      }
      ret3 = checkForChrs(val);
       if(!ret3)
      {
            alert('<bean:message key="label.PWD.kmsMsgNewPwdChrs" />');
            focusBox(id);
            return false;		
      }
     

      val = document.forms[0].ReEnterNewPassword.value;
      id=document.forms[0].ReEnterNewPassword;
      if(val == "")
      {
            alert('<bean:message key="label.PWD.kmsMsgConfirmPwd" />');
            focusBox(document.forms[0].ReEnterNewPassword);
            return false;
      }
       if(document.forms[0].newPassword.value == document.forms[0].oldPassword.value)
      {
            alert('<bean:message key="label.PWD.kmsMsgNewPwdOldPwd" />');
            focusBox(id);
            return false;
      }
      ret4=checkForSpace(val);
      if(!ret4)
      {
            alert('<bean:message key="label.PWD.kmspwdspace" />');
            focusBox(id);
            return false;
      }  

       if((document.forms[0].newPassword.value)!=(document.forms[0].ReEnterNewPassword.value))
      {
             alert('<bean:message key="label.PWD.kmsMsgNewPwdMatch" />');
             focusBox(document.forms[0].newPassword);
             return false;
      }
       val = document.forms[0].mobileNo.value
       if(val == "")
     {
           alert('<bean:message key="label.PWD.mobile.mandatory" />');
           focusBox(document.forms[0].mobileNo);
           return false;
     }
       
       if(document.forms[0].mobileNo.value != null && document.forms[0].mobileNo.value !="")
    	   {
    	  var flag =  testMobileNumber(document.forms[0].mobileNo.value);
    	  if(!flag)
    		  {
    		  document.forms[0].mobileNo.value ="";
    		  return false; 
    		  }
    	   }

       if(!(val.charAt(0)=='9' || val.charAt(0)=='8' || val.charAt(0)=='7'))
       {
             alert('Phone number should start with 7,8 or 9');
             document.forms[0].mobileNo.value ="";
             focusBox(document.forms[0].mobileNo);
   		     return false; 		
       }
       val = document.forms[0].emailId.value
       if(val == "")
     {
           alert('<bean:message key="label.PWD.emailId.mandatory" />');
           focusBox(document.forms[0].emailId);
           return false;
     }
       if(document.forms[0].emailId.value != null && document.forms[0].emailId.value !="")
    	   {
    	   var flag = testEmail(document.forms[0].emailId.value);
    	   if(!flag)
 		  {
    	  alert('E-mail should be in the format of Ex:xyz@tcs.com or xyz.123@tcs.co.in');
    	  document.forms[0].emailId.value="";
    	  focusBox(document.forms[0].emailId);
 		  return false; 
 		  }
    	   }
      else 
      {
            //    if(document.ChangePassword.newPassword.value.length < 8 || document.ChangePassword.newPassword.value.length > 16)
            if(document.forms[0].newPassword.value.length < 8 )
            {
                  alert('<bean:message key="label.PWD.kmsMsgPwdLengthVal" />');
                  focusBox(document.forms[0].newPassword);
                  return false;
            }
      } 
     
   	var result = confirm("Do you want to proceed?");
   	if (result==true) {
       
       document.forms[0].action="/Operations/ChangePwdReq.do?";
      document.forms[0].requestType.value;
       document.forms[0].submit(); 
   	}
}
function fn_changePswd(){
    document.forms[0].action="/Operations/ChangePwdReq.do?";
    document.forms[0].requestType.value;
     document.forms[0].submit(); 
}
function checkForNumber(val)
{
	var i;
	for (i = 0; i < val.length; i++) 
      	{ 
            ls_char1 = val.charAt(i); 
            if(ls_char1 >= '0' && ls_char1 <= '9')
                return true;
	}
	return false ;
}
function checkForSpecialChrs(val)
{
	var i;
        var splCharStr="!@#$%^&*()<>,./?\|[]{}'~`+=_";
	for (i = 0; i < val.length; i++) 
      	{ 
            ls_char1 = val.charAt(i);
            if(splCharStr.indexOf(ls_char1) >= 0)
                    return true;
	}
	return false ;
}
function checkForChrs(val)
{
	var i;
        var splCharStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	for (i = 0; i < val.length; i++) 
      	{ 
	    ls_char1 = val.charAt(i);
            if(splCharStr.indexOf(ls_char1) >= 0)
		return true;
	}
	return false ;
}
function checkForSpace(val)
{
     var i;
     for(i =0 ; i < val.length;i++)
     {
          char=val.charAt(i);
          if(char==" ")
            return false;
     }
     return true;
}        

function testEmail(var1)
{
	 var regex = /^\w+([-+.+_]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	 return regex.test(var1);
}

function testMobileNumber(mobile){
	
	
	 var pattern = /^\d{10}$/;
     if (pattern.test(mobile)) {
         return true;
     } 
     alert("Mobile number should be 10-digit numeric");
     document.forms[0].mobileNo.focus();
         return false;
	
}

//edit the alert popup sameep
function popupdisp()
{
document.getElementById("hide1").style.visibility="visible";
document.getElementById("changelabel").innerHTML="PassWord Rules ";
document.getElementById("changequot").innerHTML="Your Password Should Have:";
document.getElementById("changelist").innerHTML="<li>At least 3 Characters and not more than 12 Characters.</li><li>At least one Character [A-Z]/[a-z].</li><li>At least one Number (0-9).</li><li>At least one SpecialCharcter out of these('! @ # $ ^ & * ~')etc.</li> <li>Dont allow same Password again which already used last 3 times.</li> </ul>";
Randomcolor();

}
function popupdisp1()
{
document.getElementById("hide1").style.visibility="visible";
document.getElementById("changelabel").innerHTML="Important Notification";
document.getElementById("changequot").innerHTML="Notes available:-";
document.getElementById("changelist").innerHTML="<li>1.All communication will be made through above mobile number and e-mail.</li> <li>2.All fields marked with an ` * ' have to be filled in.</li>";
Randomcolor();
}
function Randomcolor()
{
	var color=new Array();
	color[0]="red";
	color[1]="chocolate";
	color[2]="cornflowerblue";
	color[3]="salmon";
	color[4]="sandybrown";
	for(var i=0;i<=color.length;i++)
		{
		var c=parseInt(Math.random()*10);
		if(c<5)
			{
			document.getElementById("changequot").style.color=color[c];
			document.getElementById("changelist").style.color=color[c];
			break;
			}
		else
			{
			document.getElementById("changequot").style.color="brown";
		document.getElementById("changelist").style.color="brown";
		break;
			}
		}
}
//jquery alerts functions
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function FocusOnOldPwd(){
	document.getElementById("oldPassword").focus();

}
</script>
</head>
<body onload="FocusOnOldPwd()">
	<html:form method="post" action="/ChangePwdReq.do" styleClass="form-group">		
		<logic:notEmpty name="loginRequestForm" property="displayAlert">
			<logic:equal name="loginRequestForm" property="displayAlert"
				value="Yes">
				<script type="text/javascript">alert('Info','<bean:message key="label.login.firstLoginChngPWD" />')</script>
			</logic:equal>
		</logic:notEmpty>

		<logic:notEmpty name="loginRequestForm" property="changeStatus">
			<logic:equal name="loginRequestForm" property="changeStatus"
				value="no">
				<script type="text/javascript">alert('<bean:message key="label.PWD.kmsMsgPwdNotChanged" />')</script>
			</logic:equal>
			<logic:equal name="loginRequestForm" property="changeStatus"
				value="yes">
				<script type="text/javascript">	
                alert('<bean:message key="label.PWD.pwdSuccess" />');
                parent.disablePopup('#popupContact');
                parent.changePswd=true;
                </script>
			</logic:equal>
			<logic:equal name="loginRequestForm" property="changeStatus"
				value="WrongPswd">

				<script type="text/javascript">
                alert('<bean:message key="label.PWD.kmsMsgCorrectOldPwd" />')</script>
			</logic:equal>
			<logic:equal name="loginRequestForm" property="changeStatus"
				value="No Prev 3 Pwd">
				<script type="text/javascript">alert("New password shouldn't match any of the previous 3 passwords");</script>
			</logic:equal>
		</logic:notEmpty>

<div class="form-group form-group-sm pull-left col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <label for="password" class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label pull-left"><bean:message key="label.PWD.oldpassword" /></label>
    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 pull-right">
      <input type="password" required="required" id="password" name="oldPassword" title="Enter old password" class="form-control FieldBlack" class="FieldBlack" tabindex="1" onfocus="popupdisp1()">
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<div class="form-group form-group-sm pull-left col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <label for="newPassword" class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label pull-left"><bean:message key="label.PWD.newpassword" /></label>
    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 pull-right">
      <input type="password" required="required"  id="newPassword" name="newPassword" title="Enter new password" class="form-control FieldBlack" tabindex="2" maxlength="8" onkeyup="passwordStrength(this.value)" onfocus="popupdisp()">
    </div>
    <div class="clearfix"></div>
    <div id="passwordStrength" class="strength0" align="center">Password not entered</div>
	<div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<div class="form-group form-group-sm pull-left col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <label for="ReEnterNewPassword" class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label pull-left"><bean:message key="label.PWD.reenternewpwd" /></label>
    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 pull-right">
      <input type="password" required="required"  id="ReEnterNewPassword" name="ReEnterNewPassword" title="Re-enter new password" class="form-control FieldBlack" tabindex="3" maxlength="8" onfocus="popupdisp1()">
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<div class="form-group form-group-sm pull-left col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <label for="mobileNo" class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label pull-left">Mobile No.</label>
    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 pull-right"> 
      <input type="text" required="required"  id="mobileNo" name="mobileNo" title="Enter mobile number" value="${phoneNumber}" class="form-control FieldBlack" tabindex="3" maxlength="10" onfocus="popupdisp1()">
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<div class="form-group form-group-sm pull-left col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <label for="emailId" class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label pull-left">Email-Id</label>
    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 pull-right">
      <input type="text" required="required"  name="emailId" id="emailId" value="${emailId}" class="form-control FieldBlack" title="Enter emailId" tabindex="3" onfocus="popupdisp1()">
    </div>
    <div class="clearfix"></div>
    <div align="right">(eg: xyz@tcs.com or xyz@tcs.co.in)</div>
</div>
<div class="clearfix"></div>
<div class="form-group form-group-sm col-lg-12 col-md-12" align="center">
<button class="btn btn-success" type="button" onclick="return validateChangePassword();" name="ok" title="click here to save new password" tabindex="4" onfocus="popupdisp1()">
		<bean:message key="label.PWD.saveBtn" />
</button>
<button class="btn btn-warning" type="button" class="Button" tabindex="6" onclick="javascript:clearAllPasswords()" title="Reset all fileds" onfocus="popupdisp1()">
		<bean:message key="label.PWD.resetBtn" />
</button>
</div>
<div class="bg-warning col-lg-12 col-md-12 col-sm-12 col-xs-12">
<div id="hide1" style="visibility: hidden">
	<p style="text-align:center;font-weight:bold" id="changelabel">PassWord Rules</p>
		<p style="font-weight:bold;" id="changequot">Your Password Should Have:-</p>
		<ul class="change_alert_pwd_popup" id="changelist">
			<li>At least 3 Characters and not more than 12 Characters.</li>
			<li>At least one Character [A-Z]/[a-z].</li>
			<li>At least one Number (0-9).</li>
			<li>At least one SpecialCharcter out of these('! @ # $ ^ & * ~')etc.</li>
			<li>Dont allow same Password again which already used last 3 times.</li>
		</ul>
</div>
</div>

		<!--<table border=0 class="form-group table table-striped table-responsive" width="100%" cellpadding="2" cellspacing="0" style="border-collapse: collapse">
			<tr>
				<td width="40%" height="25" align="left" class="tbcell">&nbsp;<bean:message
						key="label.PWD.oldpassword" />&nbsp;<span style="color: red;">*</span></td>
				<td width="60%" height="25" colspan="2">&nbsp;<input
					type="password" name="oldPassword" title="Enter old password"
					size="20" class="FieldBlack" tabindex="1" style="width: 150px;"
					onfocus="popupdisp1()">
				</td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell">&nbsp;<bean:message
						key="label.PWD.newpassword" />&nbsp;<span style="color: red;">*</span></td>
				<td width="30%" height="25">&nbsp;<input type="password"
					name="newPassword" title="Enter new password" size="20"
					style="width: 150px;" class="FieldBlack" tabindex="2" maxlength="8"
					onkeyup="passwordStrength(this.value)" onfocus="popupdisp()">

				</td>
				<td width="30%">
					<div id="passwordStrength" class="strength0" align="center"
						style="height: 15px; font-weight: normal;">Password not
						entered</div>
				</td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell">&nbsp;<bean:message
						key="label.PWD.reenternewpwd" />&nbsp;<span style="color: red;">*</span></td>
				<td width="60%" height="25" colspan="2">&nbsp;<input
					type="password" name="ReEnterNewPassword"
					title="Re-enter new password" size="20" style="width: 150px;"
					class="FieldBlack" tabindex="3" maxlength="8"
					onfocus="popupdisp1()"></td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell">&nbsp;Mobile
					No.&nbsp;<span class="UserText" style="color: red;">*</span>
				</td>
				<td width="60%" height="25" colspan="2">&nbsp;<input
					type="text" name="mobileNo" title="Enter mobile number"
					value="${phoneNumber}" size="20" style="width: 150px;" tabindex="3"
					maxlength="10" onfocus="popupdisp1()"></td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell">&nbsp;Email-Id&nbsp;<span
					class="UserText" style="color: red;">*</span></td>
				<td width="60%" height="25" colspan="2">&nbsp;<input
					type="text" name="emailId" id="emailId" value="${emailId}"
					size="20" title="Enter emailId" style="width: 150px;" tabindex="3"
					onfocus="popupdisp1()"></td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell"></td>
				<td width="60%" height="25" colspan="2"><font face="Verdana"
					size="1" color="grey"> (eg: xyz@tcs.com or xyz@tcs.co.in)</font></td>
			</tr>

			<tr>
				<td width="100%" colspan="3" align="center">
					<button class="btn btn-success" type="button"
						onclick="return validateChangePassword();" name="ok"
						title="click here to save new password" tabindex="4"
						onfocus="popupdisp1()">
						<bean:message key="label.PWD.saveBtn" />
					</button>

					<button class="btn btn-warning" type="button" class="Button" tabindex="6"
						onclick="javascript:clearAllPasswords()" title="Reset all fileds"
						onfocus="popupdisp1()">
						<bean:message key="label.PWD.resetBtn" />
					</button> <br>
				<br>
				</td>

			</tr>
			  add these contents 
			<tr>
				<td colspan="3" class="bg-warning">
					<div id="hide1" style="visibility: hidden">
						<label style="float: center;" id="changelabel"
							style="font-weight:bold;">PassWord Rules </label> <br>
						<h4 style="float: left;" id="changequot">
							<b>Your Password Should Have:-</b>
						</h4>
						<br>
						<ul class="change_alert_pwd_popup" id="changelist"
							style="font-size: 13px;">
							<li>At least 3 Characters and not more than 12 Characters.</li>
							<li>At least one Character [A-Z]/[a-z].</li>
							<li>At least one Number (0-9).</li>
							<li>At least one SpecialCharcter out of these('! @ # $ ^ & *
								~')etc.</li>
							<li>Dont allow same Password again which already used last 3
								times.</li>

						</ul>
					</div>
				</td>
			</tr>

			   the 2 things 
		</table>

		--><input type="hidden" name="Flag" value="">
		<input type=hidden name=viewId>
		<input type=hidden name="pageNo">
		<input type=hidden name="totalPages">
		<input type=hidden name="totalRows">
		<input type=hidden name=actionVal>
		<input type=hidden name=requestType>
		<input type=hidden name="onGetDocs">
		<html:hidden name="loginRequestForm" property="displayAlert" />
	</html:form>
	<script>
    popupdisp();
    </script>
</body>
</html>
