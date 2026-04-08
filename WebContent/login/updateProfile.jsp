<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update Profile</title>
<%@ include file="/common/include.jsp"%>
<link href="/<%=context%>/css/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
<script language="javascript" type="text/javascript">

// This function is used to reset all the values
	function clearAll() {
		document.forms[0].firstName.value = "";
		document.forms[0].middleName.value = "";
		document.forms[0].lastName.value = "";
		document.forms[0].mobileNo.value = "";
		document.forms[0].email.value = "";
		document.forms[0].firstName.focus();

	}
// This function is used to save the profile details
	function saveUpdateProfile() {
		var x = validateUpdateProfile();
		if (x == true) {
			document.forms[0].action = "/<%=context%>/updateProfile.do?actionFlag=saveUpdateProfile";
			document.forms[0].submit();

		}

	}
	// function to check validations 
	function validateUpdateProfile() {
		if (document.forms[0].firstName.value == "") {
			alert('Enter First Name');
			document.forms[0].firstName.focus();
			return false;
		}
		// to check for special characters
		if (document.forms[0].firstName.value != null
				&& document.forms[0].firstName.value != "") {
			var chars = "!@*?^|\":<>[]{}`\';()$#%&0123456789+-_=,/~";
			var count = 0;
			name = document.forms[0].firstName.value;
			for ( var i = 0; i < name.length; i++) {
				if (chars.indexOf(name.charAt(i)) != -1) {
					count++;
				}

				if (count > 0) {

					alert("Special characters are not allowed for First Name");
					document.forms[0].firstName.value = "";
					document.forms[0].firstName.focus();
					return false;
				}
			}

		}
		if (document.forms[0].middleName.value != null
				&& document.forms[0].middleName.value != "") {
			var chars = "!@*?^|\":<>[]{}`\';()$#%&0123456789+-_=,/~";
			var count = 0;
			middleName = document.forms[0].middleName.value;
			for ( var i = 0; i < middleName.length; i++) {
				if (chars.indexOf(middleName.charAt(i)) != -1) {
					count++;
				}

				if (count > 0) {

					alert("Special characters are not allowed for middle name");
					document.forms[0].middleName.value = "";
					document.forms[0].middleName.focus();
					return false;
				}
			}

		}
		if (document.forms[0].lastName.value == "") {
			alert("Enter Last Name");
			document.forms[0].lastName.focus();
			return false;
		}
		if (document.forms[0].lastName.value != null
				&& document.forms[0].lastName.value != "") {
			var chars = "!@*?^|\":<>[]{}`\';()$#%&0123456789+-_=,/~";
			var count = 0;
			lastName = document.forms[0].lastName.value;
			for ( var i = 0; i < lastName.length; i++) {
				if (chars.indexOf(lastName.charAt(i)) != -1) {
					count++;
				}

				if (count > 0) {

					alert("Special characters are not allowed for last name");
					document.forms[0].lastName.value = "";
					document.forms[0].lastName.focus();
					return false;
				}
			}

		}
		// validations for mobile number

		val = document.forms[0].mobileNo.value;

		if (val == "") {
			alert('Enter Mobile Number');
			document.forms[0].mobileNo.focus();
			return false;
		}

		if (!(val.charAt(0) == '9' || val.charAt(0) == '8' || val.charAt(0) == '7')) {
			alert('Mobile Number Should Start With 7,8 or 9');
			document.forms[0].mobileNo.focus();
			document.forms[0].mobileNo.value = "";
			return false;
		}

		if (document.forms[0].mobileNo.value != null
				&& document.forms[0].mobileNo.value != "") {
			var flag = testMobileNumber(document.forms[0].mobileNo.value);
			if (!flag) {
				document.forms[0]("mobileNo").value = "";
				return false;
			}
		}
		if (document.forms[0]("email").value != null
				&& document.forms[0]("email").value != "") {
			var flag = testEmail(document.forms[0].email.value);
			if (!flag) {
				alert('Email Should Be In The Format Given In Example');
				document.forms[0].email.focus();
				document.forms[0].email.value = "";

				return false;
			}
		}

		return true;
	}

	function testMobileNumber(mobile) {

		var pattern = /^\d{10}$/;
		if (pattern.test(mobile)) {
			return true;
		}
		alert("Mobile Number Should Be 10-Digit Numeric.");
		document.forms[0].mobileNo.value = "";
		return false;

	}
	// validations for email
	function testEmail(str) {
		var emailId = str;
		var at = "@";
		var dot = ".";
		var lat = str.indexOf(at);
		var lstr = str.length;
		var ldot = str.indexOf(dot);
		var spclChar = "!@#$%^&*()_-+=|\}]{[':<>,?/";
		// check if '@' is at the first position or at last position or absent in given email                 
		if (str.indexOf(at) == -1 || str.indexOf(at) == 0
				|| str.indexOf(at) == lstr) {
			alert("Invalid E-Mail ID");
			return false;
		}
		// check if '.' is at the first position or at last position or absent in given email                
		if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
				|| str.indexOf(dot) == lstr) {
			alert("Invalid E-Mail ID");
			return false;
		}
		// check if '@' is used more than one times in given email 
		if (str.indexOf(at, (lat + 1)) != -1) {
			alert("Invalid E-Mail ID");
			return false;
		}
		// check for the position of '.'                 
		if (str.substring(lat - 1, lat) == dot
				|| str.substring(lat + 1, lat + 2) == dot) {
			alert("Invalid E-Mail ID");
			return false;
		}
		// check if '.' is present after two characters  from location of '@'                 
		if (str.indexOf(dot, (lat + 2)) == -1) {
			alert("Invalid E-Mail ID");
			return false;
		}
		// check for blank spaces in given email                 
		if (str.indexOf(" ") != -1) {
			alert("Invalid E-Mail ID");
			return false;
		}
		//check if any special character is present after '@'
		if (str.indexOf(spclChar, (lat + 1)) != -1) {
			alert("Invalid E-Mail ID");
			return false;
		}
		if (str.indexOf(('""'), (lat + 1)) != -1) {
			alert("Invalid E-Mail ID");
			return false;
		}
		return true;
	}
</script>

</head>
<body>
	<html:form method="post" action="/updateProfile.do">

		<logic:notEmpty name="loginRequestForm" property="msgs">
			<logic:equal name="loginRequestForm" property="msgs" value="yes">
				<script type="text/javascript">
					alert('<bean:message key="label.UP.updateSuccess" />');
					alert('<bean:message key="label.UP.communicationMessage" />')
					parent.disablePopup('#popupContact');
				</script>
			</logic:equal>
			<logic:equal name="loginRequestForm" property="msgs" value="no">
				<script type="text/javascript">
					alert('<bean:message key="label.UP.couldnotUpdate" />')
				</script>
			</logic:equal>
		</logic:notEmpty>
		<table border=0 width="100%" cellpadding="2" cellspacing="0"
			style="border-collapse: collapse">
			<tr class="tbheader">
				<td colspan="3"><bean:message key="label.UP.updateProfileTitle" /></td>
			</tr>
			<tr>
				<td width="100%" colspan="2" align="center" class="tbcell"></td>
			</tr>
			<tr>
				<td width="100%" colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td width="40%" height="25" align="left" class="tbcell">&nbsp;<bean:message
						key="label.UP.firstName" />&nbsp;<span style="color: red;">*</span></td>
				<td width="40%" height="25" colspan="10">:&nbsp; <html:text
						indexed="firstName" name="loginRequestForm" property="firstName"
						size="20" tabindex="3" /></td>

			</tr>
			<tr>
				<td width="40%" height="25" align="left" class="tbcell">&nbsp;<bean:message
						key="label.UP.middleName" />&nbsp;
				</td>
				<td width="40%" height="25" colspan="10">:&nbsp; <html:text
						indexed="middleName" name="loginRequestForm" property="middleName"
						size="20" tabindex="3" /></td>

			</tr>

			<tr>
				<td width="40%" height="25" align="left" class="tbcell">&nbsp;<bean:message
						key="label.UP.lastName" />&nbsp;<span style="color: red;">*</span></td>
				<td width="40%" height="25" colspan="10">:&nbsp;&nbsp;<html:text
						indexed="lastName" name="loginRequestForm" property="lastName"
						size="20" tabindex="3" /></td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell">&nbsp;<bean:message
						key="label.UP.mobileNo" />&nbsp;<span style="color: red;">*</span></td>
				<td width="40%" height="25" colspan="10">:&nbsp;&nbsp;<input
					type="text" name="mobileNo" size="20" tabindex="3" maxlength="10"></td>
			</tr>
			<tr>
				<td width="40%" align="left" height="25" class="tbcell">&nbsp;<bean:message
						key="label.UP.email" />&nbsp;
				</td>
				<td width="40%" height="25" colspan="10">:&nbsp;&nbsp;<input
					type="text" name="email" size="20" colspan="10" tabindex="3">
				</td>
			</tr>

			<tr>
				<td width="40%" align="left" height="25" class="tbcell"></td>
				<td width="60%" height="25" colspan="2"><font face="Verdana"
					size="1" color="grey"> (eg: xyz@tcs.com or xyz@tcs.co.in)</font></td>
			</tr>
			<tr>
				<td width="100%" colspan="3" align="center"><span
					style="font-weight: 400"> <font color="#FF0000" size="1"
						face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

							<span style="width: 30px;"> </span> All fields marked with an ' *
							' have to be filled in.
					</font></span></td>
			</tr>
			<tr>
				<td width="100%" colspan="3" align="center">
					<button class="but" type="button"
						onclick="javascript:saveUpdateProfile()" name="save" tabindex="4">
						<bean:message key="label.UP.saveBtn" />
					</button>
					<button class="but" type="button" class="Button" tabindex="6"
						onclick="javascript:clearAll()" name="reset" tabindex="4">
						<bean:message key="label.UP.resetBtn" />
					</button>

				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3" align="center">&nbsp; <font
					color="#FF0000" size="1" face="Verdana">Note: All
						communications will be made through above mobile number and
						e-mail.</font>
				</td>
			</tr>



		</table>
	</html:form>
</body>
</html>