 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fraud/CR</title>

 <LINK href="/ASRI/css/style.css" type="text/css" rel="stylesheet">
      <!-- <script LANGUAGE="JavaScript" SRC="/ASRI/admin/scripts/validate.js"></script> -->
  <script language="JavaScript" src ="/script/ContextMenu.js" type="text/javascript"> </script>
<SCRIPT src="scripts/SecureValidations.js" type="text/javascript"></SCRIPT>
<script LANGUAGE="JavaScript" SRC="Preauth/maximizeScreen.js"></script>
<script LANGUAGE="JavaScript" SRC="/scripts/SearchScripts.js"></script>
<link href="css/themes/darkgreen/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">

</head>
<body>
<form name="CmaAuditPage" method="post">

<c:if test="${empty CmaCnt}">
<c:set var="CmaCnt" value="0"></c:set>
</c:if>

<c:if test="${empty Count}">
<c:set var="Count" value="0"></c:set>
</c:if>

<table>
<tr>
<td style="width:100%" align="center" id="topSlide" >
<img id="topImage" src="images/uparrow.png" width="20" style=cursor:hand; alt="Maximize" align="top" onclick="javascript:fn_maxmizeTop()" ></img> 
</td>
<td id="menuSlide" > <img id="menuImage" src="images/rightarrow.png" style=cursor:hand; width="20" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img> </td></tr>
</table>
<table border="0" width=930 align=center class="tabBorder" id="showCMARmks">
	<tr>
 <td colspan="2"  height="40px" bgcolor="#f3f3f3">  
 <b>Name : ${cmaVO.PATIENTNAME} ,&nbsp;${cmaVO.AGE} ,&nbsp;Card NO : ${cmaVO.RATIONCARD} ,&nbsp; </b>
 </td>
</tr>
<tr style="display:${L1DisplayRmrks}">
	<td  valign="center">  DEO Tech Medical Audit Remarks :</td>	
	<c:choose>
    <c:when test="${empty cmaVO.DEORMKS}">
        <td >
	     <textarea name="L1Rmrks" rows="3" cols="20" id="id_L1Rmrks"  class="textfield" style="height:80px;width:550px;" onkeyup="fn_CheckCase(this.id)"; onblur="fn_CheckTextAreaLen('DEO Tech Medical Audit Remarks',L1Rmrks,'4000')">${cmaVO.DEORMKS}</textarea>
		</td>
    </c:when>
    <c:otherwise>
        <td >
	     ${cmaVO.DEORMKS}  
	     <c:if test="${fn:length(cmaVO.DEOCRTDT) gt 0}">
	     	${cmaVO.DEOCRTDT}
	     </c:if>
	</td>
    </c:otherwise>
	</c:choose>	
</tr>

<tr style="display:${L1SubButton}">
	<td  colspan="2" align ="center">
		<input type="button" name="DocSubmit" value="Submit" onclick="javascript:SubmitCMA('${CaseId}');">
	</td>
</tr>
<tr></tr>

<tr style="display:${L2DisplayRmrks}">
	<td  valign="center"> JEO Medical Audit Remarks :	</td>
	<c:choose>
	<c:when test="${empty cmaVO.JEORMKS}">
		<td>
		<textarea name="L2Rmrks" rows="3" cols="20" id="id_L2Rmrks"  class="textfield" style="height:80px;width:550px;" onkeyup="fn_CheckCase(this.id)"; onblur="fn_CheckTextAreaLen('JEO Tech Medical Audit Remarks',L2Rmrks,'4000')">${cmaVO.JEORMKS}</textarea>
		</td>
	</c:when>
	<c:otherwise>
		<td>${cmaVO.JEORMKS}	
		<c:if test="${fn:length(cmaVO.JEOCRTDT) gt 0}">
			${cmaVO.JEOCRTDT}
		</c:if>
		</td>
	</c:otherwise>
	</c:choose>		
</tr>

<tr style="display:${L2SubButton}">

	<td  colspan="2" align ="center">
		<input type="button" name="DocSubmit" value="Submit" onclick="javascript:SubmitCMA('${CaseId}');">
	</td>
</tr>
<tr></tr>


<tr style="display:${L3DisplayRmrks}">
	<td  valign="center"> CMA Remarks :
	</td>
	<c:choose>
	<c:when test="${empty cmaVO.CMARMKS}">
	<td>
	<textarea name="L3Rmrks" rows="3" cols="20" id="id_L3Rmrks"  class="textfield" style="height:80px;width:550px;" onkeyup="fn_CheckCase(this.id)"; onblur="fn_CheckTextAreaLen('CMA Remarks',L3Rmrks,'4000')">${cmaVO.CMARMKS}</textarea>
	</td>
	</c:when>
	<c:otherwise>
	<td>${cmaVO.CMARMKS}	
	<c:if test="${fn:length(cmaVO.CMACRTDT) gt 0}">
		${cmaVO.CMACRTDT}
	</c:if>
	</td>
	</c:otherwise>
	</c:choose>
</tr>

<tr style="display:${L3SubButton}">

	<td  colspan="2" align ="center">
		<input type="button" name="DocSubmit" value="Submit" onclick="javascript:SubmitCMA('${CaseId}');">
	</td>
</tr>

</table>

<!-- Start for Discussion Notes -->

<c:if test="${DiscussionNotesFlag == 'Y'}">
		<c:if test="${fn:length(discussionList) gt 0}">
		<table width="100%" border="0">
		<tr><td height="20px"></td></tr>
		<tr>
			<td>
				<b><center>DISCUSSION NOTES</center></b>
				
				<table width="100%" border="1">
						<tr bgcolor="#d7e8f8" style="border:1px #D8D8D8 solid; padding:2px;">			
							<td width="20%">
								Date & Time
							</td>
							<td width="20%">
								Role & Name
							</td>			
							<td width="30%">
								Remarks
							</td>
							<td width="20%">
								Action
							</td>			
						</tr>
						<!-- for loop jstl-->		
						<c:if test="${not empty discussionList}">
						<c:forEach  items="${discussionList}" varStatus="loop">								
							<c:set value="${discussionList[loop.index]}" var="current"></c:set>		
									<tr>
										<td width="20%">											 
											 <input type="text" value='${current.D_ACTDT}' readonly/>
										</td>
										<td width="20%">
										<input type="text" value='${current.D_ACTBY}' readonly/>										
										</td>
										 
										<td width="30%">											
											<input type="text" value='${current.D_REMARKS}' readonly/>
										</td>
										 
										<td width="20%">											 
											 <input type="text" value='${current.D_ACTIONVALUE}' readonly/>
										</td>																	
									</tr>
						</c:forEach>
						</c:if>
						<!-- end of for loop -->
				</table>	
			</td>
		</tr>
		</table>
		</c:if>
</c:if>					 
<!-- End for Discussion Notes -->																	

<script>	
var ctr=false;
function fn_CheckCase(x)
{ 
  if(document.forms[0].Count.value == 1)
  {
    if(document.forms[0].viewCaseClick.value == "NOT_CLICKED")
  {
	  alert("Please Click on Case No. Link");
          document.getElementById(x).value="" ;
	  return false;
  }
  }
}
//End 001

  function viewRemarks()
  {
	//enabling and displaying remarks.
	//alert("enabling and displaying remarks");
	//window.opener.document.location.reload();
	//window.parent.history.go();
	parent.refresh(0);  
  }

  function SubmitCMA(CaseId )
  { 
	 var cmaAuditcnt = '${cmaCount}';
	var user_role = '${UserRole}';

 if (cmaAuditcnt == 0 )
  {
  if(user_role =='CD15663' )
	{
		if(trim(document.forms[0].L1Rmrks.value) == '')
            {
                alert("Please enter Audit Remarks "); 
                document.forms[0].L1Rmrks.focus();
                return;
            }
		
	}
	else if(user_role =='CD15663')
	{
		if(trim(document.forms[0].L2Rmrks.value) == '')
            {
                alert("Please enter Audit Remarks "); 
                document.forms[0].L2Rmrks.focus();
                return;
            }
	}
	else if (user_role =='CD1500')
	{
		if(trim(document.forms[0].L3Rmrks.value) == '')
            {
                alert("Please enter Audit Remarks "); 
                document.forms[0].L3Rmrks.focus();
                return;
            }
	}
  }
 else if (cmaAuditcnt  == 1 )
  {
	  if(user_role =='CD15663' )
		{
		if(trim(document.forms[0].L2Rmrks.value) == '')
            {
                alert("Please enter Audit Remarks :"); 
                document.forms[0].L2Rmrks.focus();
                return;
            }
		}
	 if(user_role =='CD1500')
		{
		if(trim(document.forms[0].L3Rmrks.value) == '')
            {
                alert("Please enter Audit Remarks :"); 
                document.forms[0].L3Rmrks.focus();
                return;
            }
		}
  } 
            document.forms[0].action="/Operations/patCommonDtls.htm?actionFlag=SaveCMAremarks&CaseId=${CaseId}";
            document.forms[0].submit();
 }

  function ShowCaseDetails(CaseId)
    {  
        ctr=true;//001
        var headFlag;
       //url="/ASRI/FrontServlet?requestType=CaseDetailsRH&actionVal=getCaseDtls&CaseId="+CaseId+"&headFlag=1&RestrictFlag=0"; //001
       url="/ASRI/FrontServlet?requestType=CaseDetailsRH&actionVal=getCaseDtls&CaseId="+CaseId+"&headFlag=1&RestrictFlag=Y"; //001
        my_window=window.open(url,'FollowUpClaimInfo','width=1005,height=650,left=5,top=35,status=1,menubar=no,scrollbars=yes,resizable=no');			                  

    }
</script>
<input type="hidden" name ="Case_Id" value='${CaseId}' id="Case_Id" >
<input type="hidden" name ="cmaCount" value='${cmaCount}' id="cmaCount">
<input type="hidden" name ="Count" value='${Count}' id="Count">
<input type="hidden" name ="actionFlag" id="actionFlag" value="cmaAudit">

       <table align="center" > 
       <tr>
		<td>
			<iframe id="cmsDataFrame" src="" width=999 height=480 scrolling=no frameborder="0" allowTransparency="true"> 
			</iframe>
		 </td>
       </tr>
     </table>
     <script language="javascript" type="text/javascript">
      var Case_Id  ='${CaseId}';
      var url="changeMgmtAction.do?flag1=searchCrsWithCaseId&CaseId="+Case_Id;
     //document.getElementById('cmsDataFrame').src=url;
    </script>
</form>
</body>
</html>