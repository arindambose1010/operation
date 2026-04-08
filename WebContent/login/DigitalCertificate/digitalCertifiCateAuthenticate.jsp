<!-- Error occures in this JSP -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>

<html>

<head>
<LINK href="/css/style.css" type="text/css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script>
</script>
<script language="JavaScript">
jQuery.support.cors = true;
var jq = jQuery.noConflict();
var tempToken = "${requestToken}";
<%
    Boolean status = (Boolean)session.getAttribute("DigiVerified");
    if(status!=null && !status){
        out.write("alert('Unable to Verify the Certificate Details. Please try again.');");
    }
    
%>

    function checkAndSubmit(){
        var text = ""+Math.random();
        	document.getElementById('txtData').value = text;
            sign(text);
    }
    function sign(text){
		var sign = '';
		var message = '';
		var keyStoretype = "WINDOWS-MY";
		var url = "";
		url = "http://localhost:18080/FormSigner7WinService/FormSignerWinService?largefile=true&pkcs7=true&sigtype=Detached&datatype=text&data=test&datafile=&keyStoretype="+keyStoretype+"&includeChain=1&token="+tempToken;
        /* if(window.location.protocol == "http:") {
			url = "http://localhost:18080/FormSigner7WinService/FormSignerWinService?largefile=true&pkcs7=true&sigtype=Detached&datatype=text&data=test&datafile=&keyStoretype="+keyStoretype+"&includeChain=1&token="+tempToken;
		} else if(window.location.protocol == "https:") {
			url = "https://localhost:18443/FormSigner7WinService/FormSignerWinService?largefile=true&pkcs7=true&sigtype=Detached&datatype=text&data=test&datafile=&keyStoretype="+keyStoretype+"&includeChain=1&token="+tempToken;
		} */
		try {
			//jq('#ajaxCoverDiv, #ajaxLoadingContent').css('display', 'block');
			/*
			* Step 1: In all browsers irrespective of version, we try to get signature using Form signer(new component i.e independent of Java plug in applet) service
			* 
			*/
			jq.ajax({    
				url: url,
				type: 'POST',
				datatype: 'json',
				cache: false,
				contentType: false,
				processData: false,
				success: function(data) {
					if(data['status'] == "200" && data['msg'] == 'OK'){
						sign = data['map']['signature'];
					} else{
						message = data['msg'];
					}
					if(null != sign && ""!=sign ) {
						//jq('#txtSignature').val(sign);						
						document.getElementById("signedData").value = sign;
						document.forms[0].submit();
						return;
					} else {
						alert(message);
						console.log = message;
					}
					//jq('#ajaxCoverDiv, #ajaxLoadingContent').css('display', 'none');
					return false;
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) { 
					alert("Application failed to read signature details. Please ensure that form signer service is running.\n Even after start of service, the issue persists, Please contact system administrator "); 
				} 
			});
		} catch(e){
		}
    }
 
</script>

</head>
<body >
	<TABLE id="Table1" cellSpacing="0" cellPadding="2" width="500" align="center" border="0">
		<tr>
			<td colspan="2"><img src="/Operations/images/digital-signature.gif"></td>
		</tr>
		<tr>
			<td colspan="2" background="/Operations/images/bg-key.gif" height="100" valign=top style="PADDING-TOP: 167PX;background-repeat: no-repeat"><br>
				<TABLE id="Table2" cellSpacing="2" cellPadding="2" width="250" align="right" border="0">
				<TR>
					<TD><STRONG>Select your Certificate here :</STRONG></TD>
					<TD><a href="javascript:checkAndSubmit()"><img src="/Operations/images/SIGN-IN.gif" title="Register"/></a></td>
				</TR>
				</TABLE>
			</TD>
		</TR>
		<TR>
			<TD></TD>
			<TD></TD>
		</TR>
		<tr>
			<td colspan="2"> &#169;2009 Tata Consultancy Services Limited. All Rights Reserved</td>
		</tr>
	</TABLE>
	<form action="loginAction.do?actionFlag=verifyDigiSign" method='post'>
                <input type='hidden' name='actionFlag' value='registerCertificate' />
                <input type="hidden" name="txtData" id="txtData" value="test" />
                <input type="hidden" name="signedData" id="signedData" value="" />
                <input type="hidden" name="LoginName" value="<%=request.getParameter("LoginName")%>" />
                <input type="hidden" name="Password" value="<%=request.getParameter("Password")%>" />
                <input type="hidden" name="LangSel" value="<%=request.getParameter("LangSel")%>" />
                
	</form>
<%	if ((request.getHeader("User-Agent")+"").toLowerCase().indexOf("msie") != -1){%>
            <span>Object</span>

           <OBJECT id =  "JSignApplet"
                    name = "JSignApplet"
                    classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
                    codebase="http://java.sun.com/update/1.6.0/jinstall-6-windows-i586.cab#Version=6,0,0,99"
                    width="100px" height="100px" >
                    <PARAM name="code" value="JSignApplet.class" />
                    <PARAM NAME="ARCHIVE" VALUE="signapplet.jar" />
                    <param name="type" value = "application/x-java-applet;jpi-version=1.6" />
                    <param name="scriptable" value = "true" />
            </OBJECT>

<%	}else{%>
        
               <APPLET ARCHIVE="signapplet.jar"
                        CODE = "JSignApplet.class" 
                        ALT = "Applet not loaded" 
                        NAME = "JSignApplet" 
                        id = "JSignApplet"
                        WIDTH = 150 HEIGHT = 150 
                        TYPE = "application/x-java-applet;jpi-version=1.6"> 
              <PARAM name = "codebase_lookup" value= "false">
              <PARAM NAME="java_arguments" VALUE="-Djnlp.packEnabled=true -Xmx1024m"/>
             </APPLET>
<%	} %>
<script>
var text = ""+Math.random();
var signature = null;
//try{
    signature = sign(text);
    //signature = "01234567890";
    document.getElementById("signedData").value = signature;
    document.getElementById('txtData').value = text;
	if(signature==null){
		throw {
			name:        "Digital Signature Error",
			level:       "Show Stopper", 
			message:     "Either token is not pluged in or certificate is not available.", 
			htmlMessage: "Either token is not pluged in or certificate is not available.",
			toString:    function(){return this.name + " : " + this.message;} 
		};
	}

document.forms[0].submit();

</script>
</body>
</html>