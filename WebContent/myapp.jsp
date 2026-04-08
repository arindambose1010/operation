<%@page
import="com.sun.identity.saml2.common.SAML2Exception,
com.sun.identity.saml2.common.SAML2Constants,
com.sun.identity.saml2.assertion.Assertion,
com.sun.identity.saml2.assertion.Subject,
com.sun.identity.saml2.profile.SPACSUtils,
com.sun.identity.saml2.protocol.Response,
com.sun.identity.saml2.assertion.NameID,
com.sun.identity.saml.common.SAMLUtils,
com.sun.identity.shared.encode.URLEncDec,
com.sun.identity.plugin.session.SessionException,
java.io.IOException,
java.util.Iterator,
java.util.List,
java.util.Map,
java.util.HashMap,
java.util.HashSet,
java.util.Set"
%>
<%
    String deployuri = request.getRequestURI();
    int slashLoc = deployuri.indexOf("/", 1);
    if (slashLoc != -1) {
        deployuri = deployuri.substring(0, slashLoc);
    }
%>
<html>
<head>
<title>Fedlet Application</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
<form method="post"  action="/loginAction.do">
<%
// BEGIN : following code is a must for Fedlet (SP) side application
Map map;
try {
// invoke the Fedlet processing logic. this will do all the
// necessary processing conforming to SAMLv2 specifications,
// such as XML signature validation, Audience and Recipient
// validation etc.
map = SPACSUtils.processResponseForFedlet(request, response);
String userName=null;
Map attrs = (Map) map.get(SAML2Constants.ATTRIBUTE_MAP);
if (attrs != null) {
    Iterator iter = attrs.keySet().iterator();
    while (iter.hasNext()) {
        String attrName = (String) iter.next();
        Set attrVals = (HashSet) attrs.get(attrName);
		  Set attrVals1 = (HashSet) attrs.get("uid");
        if ((attrVals1 != null) && !attrVals1.isEmpty()) {
            Iterator it1 = attrVals1.iterator();
            while (it1.hasNext()) {
                userName=it1.next().toString();
            }
        }
    }
}

String entityID = (String) map.get(SAML2Constants.IDPENTITYID);
String spEntityID = (String) map.get(SAML2Constants.SPENTITYID);
NameID nameId = (NameID) map.get(SAML2Constants.NAMEID);
String value = nameId.getValue();
String sessionIndex = (String) map.get(SAML2Constants.SESSION_INDEX);
%>
 <jsp:forward page="/login/loginOpenAM.jsp">
	<jsp:param name="userName" value="<%=userName%>"></jsp:param>
	<jsp:param name="deployUri" value="<%=deployuri%>"></jsp:param>
	<jsp:param name="entityID" value="<%=entityID%>"></jsp:param>
	<jsp:param name="spEntityID" value="<%=spEntityID%>"></jsp:param>
	<jsp:param name="nameId" value="<%=nameId%>"></jsp:param>
	<jsp:param name="nameValue" value="<%=value%>"></jsp:param>
	<jsp:param name="sessionIndex" value="<%=sessionIndex%>"></jsp:param>
 </jsp:forward>

<%
} catch (SAML2Exception sme) {
SAMLUtils.sendError(request, response,
response.SC_INTERNAL_SERVER_ERROR, "failedToProcessSSOResponse",
sme.getMessage());
return;
} catch (IOException ioe) {
SAMLUtils.sendError(request, response,
response.SC_INTERNAL_SERVER_ERROR, "failedToProcessSSOResponse",
ioe.getMessage());
return;
} catch (SessionException se) {
SAMLUtils.sendError(request, response,
response.SC_INTERNAL_SERVER_ERROR, "failedToProcessSSOResponse",
se.getMessage());
return;
} catch (ServletException se) {
SAMLUtils.sendError(request, response,
response.SC_BAD_REQUEST, "failedToProcessSSOResponse",
se.getMessage());
return;
}
// END : code is a must for Fedlet (SP) side application

%>
</form>
</body>
</html>
