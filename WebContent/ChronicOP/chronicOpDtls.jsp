 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/common/include.jsp"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>PatientCommonDetails</title>
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="/<%=context%>/js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>
<style>
	.butCms{
	border:1px solid #dfdfdf; -webkit-border-radius: 5px; -moz-border-radius: 5px;border-radius: 5px;font-weight:bold;font-family:arial, helvetica, sans-serif; padding: 3px 15px;  text-align: center; color: #D65F26; background-color: #FBFBFB;
	 background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FBFBFB), color-stop(100%, #EBE9E2));
	 background-image: -webkit-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: -moz-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: -ms-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: -o-linear-gradient(top, #FBFBFB, #EBE9E2);
	 background-image: linear-gradient(top, #FBFBFB, #EBE9E2);filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#FBFBFB, endColorstr=#EBE9E2);
	}
</style>
<c:if test="${resMsg != null }">
<script>
jqueryInfoMsg('Result','${resMsg}');
</script>
</c:if>
<script type="text/javascript">
var ViewType = '${viewType}';
var currStatus='${patCommonDtls.STATUS}';
var caseFlag = parent.caseApprovalFlag;
var caseId = '${caseId}';
var pastHistClicked='';
var loggedInRole='${loggedInRole}';





function openPhoto(arg)
{
 var patphoto = document.getElementById("patPhoto");
 if(patphoto.alt == 'Click here to View Patient Photo')
 {
  patphoto.alt = 'Click to view larger Image';
  patphoto.src = '/ASRI/FrontServlet?requestType=ReadAttachRequestHandler&actionVal=openAtach&filepath='+arg;
  
 }
 else
 {
   fn_openAtach1(arg);
 }
}
function chkSpecailChars(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
}
function sendImage()
{
  var Pid='${patCommonDtls.PATID}';
  var Rno='${patCommonDtls.CARDNO}';
  var CaseStat='${patCommonDtls.CASESTAT}';
  var CaseId='${caseId}';
  var url="/ASRI/FrontServlet?requestType=BlobInsertRH&RestrictFlag=${RestrictFlag}&actionVal=BlobInsertImage&PatientId="+Pid+"&RationNo="+Rno+"&feedback=g=fb&caseStat="+CaseStat+"&CaseId="+CaseId; 
  document.forms[0].action=url;
  document.forms[0].method="post";
  document.forms[0].submit();
}
function Validate(arg)
{
    var validFileobj=eval(document.getElementById(arg));
    var validFile=validFileobj.value;
    if(validFile=="")
    {
          alert("Please  Attach File");
          return;
    }
    else
    {          
          var pos1=validFile.lastIndexOf("\\");
          var sub1=validFile.substring(pos1+1,len);
          newArray=sub1.split(' ');
          if(newArray.length > 1)
          {
            alert('File Name contains spaces please Rename the file name with out spaces..');
            return false;
          }		  
          vSplit=validFile.split("\\");
          vFileName = vSplit[(vSplit.length)-1];      
          var len=validFile.length; 		  
          if(len > 0)
          {
              var pos=validFile.lastIndexOf(".");
              var sub=validFile.substring(pos+1,len);
    
              if((sub=="gif")||(sub=="jpeg")||(sub=="jpg")||(sub=="bmp")||(sub=="WMF")
              ||(sub=="wmf")||(sub=="GIF")||(sub=="JPEG")||(sub=="JPG")||(sub=="BMP"))
              {
                     rtVal = chkSpecailChars(vFileName);
                     if(rtVal ==0) 
                     {
                           alert('File Name should not contain special characters');
                           if(arg == 'Attachment')
                           {
                        	   window.form1.reset();
                        	 
                           }
                           window.location.reload( );                   
                     }
                    else
                    {          
                      if(arg == 'Attachment')
                      {
                    	  fn_UploadFile();
                      }
                    }    
              }
              else
              {
                 if(arg == 'Attachment')
                 {
                	 window.form1.reset();

                 
                 }                 
                 alert("Attach Images only");                 
                 //window.location.reload( );
              }
           }
    }
}
function validateFile()
{
    event.returnValue=false;
}
function right(e) 
{
    if (navigator.appName == 'Netscape' && (e.which == 3 || e.which == 2))
        return false;
    else if (navigator.appName == 'Microsoft Internet Explorer' && (event.button == 2 || event.button == 3)) 
    {
        alert("Sorry, you do not have permission to right click");
        return false;
    }
    return true;
}





function fn_attachments()
{
	var caseApprovalFlag="${caseApprovalFlag}";

	
	var url="/Operations/attachmentAction.do?actionFlag=onload&UpdType=chronicAttach&chronicId=${chronicId}&caseAttachmentFlag=Y&caseApprovalFlag="+caseApprovalFlag;      
	//var url="/Operations/attachmentAction.do?actionFlag=onload&UpdType=chronicAttach&chronicId=${chronicId}&caseAttachmentFlag=Y&caseApprovalFlag="+caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;      
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();	
}
function back()
{
window.parent.location.reload(true);	
}
function fn_maxmizeTop()
{
	parent.fn_maxmizeTop();
}

function fn_ipRefresh()
{
	ipRefershFlag='Y';
	topFrame.location.reload();
}
function fn_getChronicOp()
{	
	if(parent.ipRefershFlag != null && parent.ipRefershFlag !='' && parent.ipRefershFlag=='Y')
		{
		return;
		}
	else
	{
   var url="/Operations/chronicAction.do?actionFlag=casePrintForm&chronicId=${chronicId}&showAll=Y";   
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();
		}
}

function fn_claim()//076
{

var pendingflag='${pendingFlag}';
var url="/Operations/chronicAction.do?actionFlag=Claims&chronicId=${chronicId}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}&caseApprovalFlag="+parent.caseApprovalFlag;;	 
	
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit(); 
		
		//document.getElementById('dataFrame').src ="/Operations/ClaimsFlow.do?actionFlag=Claims&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}";
}
function fn_clinicalNotes()//076
{

		var url="/Operations/chronicAction.do?actionFlag=ClinicalNotes";
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit(); 
		
		//document.getElementById('dataFrame').src ="/Operations/ClaimsFlow.do?actionFlag=Claims&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}";
}
function raiseIssue()
{	
	var url="/Operations/raiseIssueAction.do?flag=raiseCms&caseId=${caseId}&hospId=${patCommonDtls.INTIID}&hospName=${patCommonDtls.HOSPNAME}";
	childWindow= window.open(url, '_blank','toolbar=no,resizable=no,scrollbars=yes,width=600, height=400, top=100,left=50');
}

</script>
</head>
<body onload="javascript:fn_getChronicOp();">
<form  method="post" enctype="multipart/form-data" modelAttribute="uploadItem" id="form1">
<table width="100%">
<tr><td width="100%" valign="top">

 <table width="85%" border="0" align="left" cellpadding="0" cellspacing="4">
 <tr><td width="10%" class="labelheading1 tbcellCss" ><b>Name  </b></td><td width="20%" class="tbcellBorder" colspan="3">${patCommonDtls.PATIENTNAME} ,    ${patCommonDtls.GENDER} ,    ${patCommonDtls.AGE}</td>
 	<c:if test="${patCommonDtls.telephonicId != null && patCommonDtls.telephonicId !='' }">
 <td width="20%" class="labelheading1 tbcellCss"><b>Telephonic ID </b></td>
 <td class="tbcellBorder"><a href="javascript:viewTelephonicInfo('${patCommonDtls.telephonicId}')">${patCommonDtls.telephonicId}</a>  <button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button> </td>
 </c:if>
 <c:if test="${patCommonDtls.telephonicId == null || patCommonDtls.telephonicId =='' }">
  <c:if test="${hideBackButton != 'Y'}">
				<td colspan="1">	 <button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button></td>
	</c:if>		
</c:if>
 </tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Card No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CARDNO}</td><td width="10%" rowspan="1" class="labelheading1 tbcellCss"><b>Case Status </b></td><td width="34%" rowspan="1" class="tbcellBorder"> <b>${patCommonDtls.STATUS}</b></td><td width="8%" class="labelheading1 tbcellCss"><b>District&nbsp;:</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.DISTRICT}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Case No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CASENO}</td><td width="10%" class="labelheading1 tbcellCss"><b>Slab Type </b></td><td width="34%" class="tbcellBorder"> ${patCommonDtls.slabType}</td><td width="8%" class="labelheading1 tbcellCss"><b>Mandal</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.MANDAL}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>IP No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.IPNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>NWH Name </b></td><td width="34%" class="tbcellBorder">${patCommonDtls.HOSPNAME}</td><td width="8%" class="labelheading1 tbcellCss"><b>Village</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.VILLAGE}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Claim No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CLAIMNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>IP Reg Date </b></td><td width="34%" class="tbcellBorder">${patCommonDtls.date}</td><td width="8%" class="labelheading1 tbcellCss"><b>Contact No</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.CONTACT}</td></tr>
 <!-- <tr><td class="labelheading1 tbcellCss"><b>Case Status &nbsp;:</b></td><td colspan="2" class="tbcellBorder"><b>${patCommonDtls.STATUS}</b></td>
 <td></td><td></td><td colspan="1" align="center">
					<c:if test="${hideBackButton != 'Y'}">
					<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>
					</c:if>
					&nbsp;
				</td></tr>-->
 
  
 </table>
 <fieldset style="border:0;">
 <table width="15%" border="0" align="left">
 <tr height="20"><td>&nbsp;</td></tr>
 <tr><td width="100%" >
 <c:choose>
 <c:when test="${patOnBedPic != null && patOnBedPic != 'N'}" >
 <img src="Common/showDocument.jsp" width="110" height="90" alt="NO DATA"  id="patImage" 
  onmouseover="javascript:resizePatImage('patImage','140','110')" onmouseout="resizePatImage('patImage','110','90')" />
 </c:when>
  <c:otherwise>
 
<img src="images/photonot.gif" width="110" height="90" alt="NO DATA" 
/>
  <%-- <form:input  path="fileData" type="file" cssStyle="width:120px" id="Attachment" size='2'  />
  <button class="but"   type="button" onclick="javascript:Validate('Attachment')" value="Upload" > Uplaod </button> --%>   
  
  </c:otherwise>
 </c:choose>
 </td></tr>
 <tr><td>

 </td></tr>
 </table>
 </fieldset>
 </td></tr>
</table>
</form>
</body>
</html>


