<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>


<html>

<head>
		
  <LINK href="/CSS/style.css" type="text/css" rel="stylesheet">
  <!--
<APPLET 
ARCHIVE=bcmail-jdk14-145.jar,bcprov-jdk14-145.jar,commons.jar,signer.jar,log4j-1.2.11.jar,signApplet.jar 
CODE = JSignApplet.class 
ALT = "Applet not loaded" 
NAME = JSignApplet 
WIDTH = 10 HEIGHT = 10 
TYPE = "application/x-java-applet;jpi-version=1.6"> 
</APPLET> -->


<APPLET 

ARCHIVE=signapplet.jar
CODE = JSignApplet.class 
ALT = "Applet not loaded" 
NAME = JSignApplet 
WIDTH = 10 HEIGHT = 10 
TYPE = "application/x-java-applet;jpi-version=1.6"> 
<PARAM name = "codebase_lookup" value= "false">
<PARAM NAME="java_arguments" VALUE="-Djnlp.packEnabled=true"/>
</APPLET>



</head>
 

<script language="JavaScript">

<% String lStrResult="",lStrSignFlag="";
if(request.getAttribute("ActionFlag")!=null)
   {
     lStrResult = (String)request.getAttribute("ActionFlag");
   }
  
if(("Success").equalsIgnoreCase(lStrResult)){ %>
          alert('Certificate Details saved successfully ');
          window.close();
          <%}else if("Fail".equalsIgnoreCase(lStrResult))
		  {%>
		   alert('Unable to Verify the Certificate Details ');

          <% }
     if(request.getAttribute("SignFlag")!=null)
     {
     lStrSignFlag = (String)request.getAttribute("SignFlag");
     }
    if(("HasSign").equalsIgnoreCase(lStrSignFlag)){%>
     alert('You are already signed');
	window.close();
     <%}%>
     
function Sign()
{

	try
	{
		var data = document.DigiForm.txtData.value;
       // alert("data"+data);

		var  v = document.JSignApplet.updateData(data);
		//alert("Update Data : " +v +"\nResponse Message : " +document.JSignApplet.getErrorMessage());
        // alert("2");
		var st = document.JSignApplet.setStoreType("WINDOWS-MY");
	//	alert("Set store Type : " +st +"\nResponse Message : " +document.JSignApplet.getErrorMessage());
        //  alert("3");
		var st1 = document.JSignApplet.setComments("Test Comments");
	//	alert("Set Comments as Test Comments : " +st1 +"\nResponse Message : " +document.JSignApplet.getErrorMessage());
       //  alert("4");
		var st2 = document.JSignApplet.setDocumentName("Test DocumentName");
	//	alert("Set Document Name as Test Document Name : " +st2 +"\nResponse Message : " +document.JSignApplet.getErrorMessage());

		var st3 = document.JSignApplet.setSignatureFormat(0);
		//alert("Set Signature Format as Base64 : " +st +"\nResponse Message : " +document.JSignApplet.getErrorMessage());

		var is = document.JSignApplet.selectSigningCertFromUI();
	//	alert("Set Signing Cert From UI : " +is +"\nResponse Message : " +document.JSignApplet.getErrorMessage());

		var  signature = document.JSignApplet.sign();
	//	alert("Signing Response Message : " +document.JSignApplet.getErrorMessage());

		//form.txtSignature.value = s;
		//alert("Signatures : \n" +signature);
			if(null != signature && ""!=signature )
      		{
		   
                    document.DigiForm.txtSignature.value = signature;

			//document.DigiForm.submit();
                        document.forms[0].action='/Operations/digitalCertificate.do?actionFlag=registerCertificate';
document.forms[0].submit();
                        
			}
      		else
      		{
      			alert("Token is not plugged in or Certificate is not available !");
				return;
      		}

		//return true;
	}
	catch(e)
	{
		alert("An exception occurred !! Error name: " + e.name + ". Error message: " + e.message);
	}
}
</script>





<body>
<table width="100%">
	
	
	<tr>
	  	<td>
				          	<form name="DigiForm" method="post">

	  		<TABLE id="Table1" cellSpacing="0" cellPadding="2" width="500" align="center" border="0">
			<tr>
			<td colspan="2"><img src="/images/digital-signature.gif">
			 <!--<script type="text/javascript" language="JavaScript">

	var _app = navigator.appName;

	if (_app == 'Netscape')
    {
    	document.write('<embed code="JSignApplet.class"',
                   	 ' name="JSignApplet"',
                    	' archive="bcmail-jdk14-145.jar,bcprov-jdk14-145.jar,commons.jar,signer.jar, log4j-1.2.11.jar, signApplet.jar"',
		           	' width="200"',
                   	' height="200"',
                   	' type="application/x-java-applet;version=1.6">');
   		 }
  		else if (_app == 'Microsoft Internet Explorer') {
    	document.write('<OBJECT ', 'classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"',
                   'width="20" ',
                   'name="JSignApplet"',
                   'height="20"',
					'codebase="http://java.sun.com/update/1.6.0/jinstall-6-windows-i586.cab#Version=6,0,0,99">',
                   '<PARAM name="code" value="JSignApplet.class">',
                   '<PARAM NAME = ARCHIVE VALUE = "bcmail-jdk14-145.jar,bcprov-jdk14-145.jar,commons.jar,signer.jar,log4j-1.2.11.jar,signApplet.jar" >',
                   '<param name = "type" value = "application/x-java-applet;jpi-version=1.6">',
                   '<param name = "scriptable" value = "true">',
                   '</OBJECT>');
   		}
  		else {
    		document.write('<p>Unsupported browser.</p>');
    	}

</script>-->
			</td>
			
			</tr>
			<TR>
				<TD colspan="2" background="/images/bg-key.gif" height="250" valign=top style="PADDING-TOP: 167PX"><BR>
				<TABLE id="Table2" cellSpacing="2" cellPadding="2" width="250" align="right" border="0">
						<TR>
							 <TD><STRONG>Select your Certificate here :</STRONG></TD>
						<!--	<TD><INPUT  type="password" name="txtData"></TD> -->
							
							 <td >
							<a href="javascript:Sign()">
							<img src="/images/SIGN-IN.gif" title="Register"/>
							</td>
						</TR>
						 <tr>
					            <INPUT  type="hidden" name="txtData" value="test">
								  <input type="hidden" name="txtSignature" value="">
                                                       <input type=hidden name="requestType" >
                                                        <input type=hidden name="actionVal" >
					            </tr>

					</TABLE>
					</form>
				</TD>
			</TR>
			<TR>
				<TD></TD>
				<TD></TD>
			</TR><tr>
			<td colspan="2"> &#169;
 2009 Tata Consultancy Services Limited. All Rights Reserved 
</td>
			
			</tr>
		</TABLE>
	    </td>
	</tr>
	
</table>
</body>
</html>