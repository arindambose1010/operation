<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page  contentType="text/html;charset=utf-8"          
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>
<html> 
<head>
<title>Case Attachments</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="attachments/PreauthScripts.js"></script> 
<script LANGUAGE="JavaScript" type="text/javascript" SRC="attachments/attachments.js"></script> 
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>
<%@ include file="/common/includePatientDetails.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script> 
<LINK href="css/style.css" type="text/css" rel="stylesheet"> 
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<c:if test="${ResultMsg != null}">
    <script type="text/javascript">
       // alert('${ResultMsg}');
        jqueryInfoMsg('Result message','${ResultMsg}');
    </script>
    </c:if>
    	<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.1.custom.css">
    	
    	
	<style>
{ 
display: block;
}
body{font-size:12px !important}

.upload-cont{
background-color: #000000;
}
.add-text{
    display:block;
    font-size:10px;
    font-weight:bold;
    color:#999;
    word-wrap:break-word;
    text-align:center;
    width:100px;
    top:37%;
    left:25%;
    position:absolute;
}
.add-text:hover{ color:black; }


#progressbar {
	margin-left: 20px;
	margin-right: 20px;
	position: relative;
	top: 75%;
}

#droplabel {
	position: relative;
	top: 40%;
}


.styleChange{
}

</style>
<script type="text/javascript">
var attachmentDocType ='';
function fn_caseSheet(arg)
{
//alert(arg);
if(arg=="casesheetattach")
	{
	parent.parent.topFrame.casesheetAttachClick='Y';
	}
}
function uploadNew(type,attchtype)
{ 
	var newBornBaby='${newBornBaby}';
	var cochCount='${cochCount}';
    var attchcnt=document.getElementById(type+'cnt').value;
    var attachNos = null;
    if(checkSimilarUpload(attchcnt,type,'CaseAttachments') == 1 )
    {
    	 for(var i=1;i<=attchcnt;i++)
    	   {
    	      var a = document.getElementById(type+i).name;
    	     var  numbers = a.match(/\d+\.?\d*/g);
    	       
    	     if(attachNos == null)
    	    	 attachNos = numbers;
    	     else
    	    	 attachNos =attachNos+"~"+numbers;
    	   }
    	// alert(attachNos);
    	document.getElementById("upload"+type).disabled=true;
         document.FileAttachment.method="POST";
        document.FileAttachment.attachNos.value=attachNos;
        document.FileAttachment.docType.value=type;
        document.FileAttachment.attachType.value=attchtype;
        fn_loadImage();
        var chronicId="${chronicId}";
        if(chronicId!=null && chronicId!="")
        {
       		 document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=upldEnrollAttach&actionVal=caseAttachments&openWin=${winOpen}&chronicId=${chronicId}&UpdType=${UpdType}&newBornBaby="+newBornBaby;
        }
        else
        {
        	document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=upldEnrollAttach&actionVal=caseAttachments&cochRem=${cochRem}&openWin=${winOpen}&chronicId=${chronicId}&caseId=${caseId}&UpdType=${UpdType}&newBornBaby="+newBornBaby+"&cochCount="+cochCount;
        }
        document.FileAttachment.submit(); 
    }
}

function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
var preauthAttachFlag = "Y";
var dropArray =  new Array();
function fn_maxmizeTop()
{
parent.fn_maxmizeTop();
	/*var url='/OperationsAP/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.parent.caseApprovalFlag+'&errSearchType='+parent.parent.errSearchType+'&disSearchType='+parent.parent.disSearchType+'&module='+parent.parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
   document.forms[0].submit();*/
	}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
function fn_openAtachment(filepath)
{  
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}

function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";
}
var dbFilesCnt=-1;
var dbFilesArray=new Array();

function fn_removeAttachments(attchtype)
{
	var newBornbaby='${newBornBaby}';
	var elLength = document.FileAttachment.elements.length; 
    var result='';
	var count=0;
    	    for (i=0; i<elLength; i++)
		    {
		        var type = FileAttachment.elements[i].type;	
				
		        if (type=="checkbox" && FileAttachment.elements[i].checked)
		        {	
			    	result = result + FileAttachment.elements[i].value+',';
					count++;
					
		        }
		        else {
					
		        }
				
		    }
			if(count==0)
			{
				 jqueryAlertMsg('Message','No attachment selected for removal. Please click on the checkbox for the attachment you want to remove.');
				//alert("No attachment selected for removal. Please click on the checkbox for the attachment you want to remove.")
				return;
			}
			if(count > 0 )
				{
				 var fr = partial(fn_deleteAttachmentsConfirm,result);
				 jqueryConfirmMsg('Attachments','Do you want to delete attachment ? ',fr);
				}
			
    
}
function fn_deleteAttachmentsConfirm(result)
{
	var newBornbaby='${newBornBaby}';
	var cochCount='${cochCount}';
	document.FileAttachment.method="POST";
	document.FileAttachment.docType.value=result;
	if('${UpdType}'!=null && ('${UpdType}'=='ehfFollowUp' || '${UpdType}'=='ehfCochlearFollowUp'))
		document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removefollowUpAttachments&cochRem=${cochRem}&openWin=${winOpen}&UpdType=${UpdType}&newBornbaby="+newBornbaby+"&cochCount="+cochCount;

		else if(('${UpdType}'!=null && '${UpdType}'=='chronicAttach'))
				document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removechronicAttachments&cochRem=${cochRem}&openWin=${winOpen}&UpdType=${UpdType}&chronicId=${chronicId}&newBornbaby="+newBornbaby;

		else
	    document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removePreauthAttachments&cochRem=${cochRem}&openWin=${winOpen}&UpdType=${UpdType}&newBornbaby="+newBornbaby;
    
    document.FileAttachment.submit();
	}

</script>

</head>
<body >
<div id="processImagetable" style="top:40%;z-index:50;position:absolute;left:45%;display:none;">
        <table border="0" align="center" width="100%" style="height:100" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="/<%=context%>/images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
<form name="FileAttachment" action="/attachmentAction.do" enctype="multipart/form-data" method="post">
<!-- Modal for patient details  -->  
<div class="modal fade" id="viewDtlsID"> 
 <div class="modal-dialog" id="modal-lgx">
   <div class="modal-content">
      <div class="modal-body">
	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  <iframe  id="complaintFrame" width="100%" height="250px" frameborder="no" scrolling="yes" > </iframe>
	  </div>
	  <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	  </div><!-- /.modal-content --> 
	</div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 
<c:if test="${opAttach ne 'Y' }">
<div id="imageID"> 
 <a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
 <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
 <br>Patient Details
 </a>
</div>
</c:if>
<div id="backgroundPopup"></div>
<c:forEach items="${AttachmentMap.DBFilesLst}" var="DBFileLst"  >		
	<script type="text/javascript">
		dbFilesCnt++;
		dbFilesArray[dbFilesCnt]=new Array();
	    dbFilesArray[dbFilesCnt][0]="${DBFileLst.heading}";                   
	    dbFilesArray[dbFilesCnt][1]="${DBFileLst.actualName}";
	    //alert('${DBFileLst.actualName}');
	</script>
</c:forEach>
<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-top:0px;margin:0px auto;" >
<tr><td>
<table border="0" width="100%" >
<c:if test="${winOpen !='' && winOpen=='Y' }">
<tr class="tbheader">
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Attachments</strong></td>
</tr>
</c:if>
<c:if test="${winOpen =='' || winOpen==null }">
<tr class="tbheader">
<td id="topSlide" width="5%">
<img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img>
</td>
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Attachments</strong></td>
<td id="menuSlide" width="5%">
<!--<img id="topImage" src="images/updownArrow.jpg" width="30" height="20" style=cursor:hand; title="Maximize/Minimize" alt="Maximize" align="top" onclick="javascript:fn_maxmizeTop()" ></img>-->
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img> 
</td>
</tr>
</c:if>


</table>
</td></tr>
<tr><td> 

<table   width="95%" border="0" align="center" cellpadding="0"  cellspacing="0" style="padding-top:5px;" >
<c:set var="AddloopCount" value="0" />
<c:set var="SignPRFForm" value="${fn:length(AttachmentMap.SignPRFFormList)}"/> 
<% int i=1; %>
<c:forEach items="${AttachmentMap.addAttachmentList}" var="AddattMap" begin="0" varStatus="Addstatus">
<script type="text/javascript">
                      if( window.opener!=null && window.opener.document.getElementById('${AddattMap.docType}'))
                    	  {
							window.opener.document.getElementById('${AddattMap.docType}').value = 0;  
                    	  }
					</script> 
<c:set var="AddloopCount" value="${AddloopCount + 1}" /> 
<c:set var="attachCount" value="${AddloopCount + 1}" />
<c:set var="attachmentDocType" value="${attachmentDocType}~${AddattMap.docType}" />

	<tr  >
	<td colspan="2">
	<table width="100%"  id="dropbox<%=i++%>" title="${AddattMap.heading}" >
	<script>
	dropArray.push('${AddattMap.docType}'+'~'+'${AddattMap.cmbDtlID}'+'~'+'${caseId}');
	</script>
	<tr height="30"><td width="10%" class="labelheading1 tbcellCss">
	<c:if test="${AddattMap.attachType eq 'NM' }"><td width="27%" class="labelheading1 tbcellCss"> <b>${AddattMap.heading} </b> </td></c:if>
	<c:if test="${AddattMap.attachType eq 'M' }"><td width="27%" class="labelheading1 tbcellCss"><b> ${AddattMap.heading}  <font color="red">*</font></b>   </c:if>
	</td>
	<td  width="38%" nowrap="nowrap"  class="tbcellBorder">
	
  &nbsp;<input type="file" name='attachedIndex[${attachCount}]' id='${AddattMap.docType}1' class="FieldBlack"    > </input>
						
						<c:if test="${AddattMap.docType != 'onBedPoto'}">			
                       <button class="but"   type="button"  id="Add${AddattMap.docType}" value="+" onclick="javascript:addCaseElement('${AddattMap.docType}','Y');" style="width:40px" class="FieldBlack" title="click to browse attachment">+</button>
                       <button class="but"   type="button"  id="Remove${AddattMap.docType}" value="-" onclick="removeElement('${AddattMap.docType}','Y')" style="width:40px;visibility:hidden" class="FieldBlack" title="click to delete attachment">-</button>						
						</c:if>
						<c:if test="${AddattMap.docType == 'onBedPoto'}">	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						</td>
						<td >
						<button class="but"   type="button"  id="upload${AddattMap.docType}" style="width:90px" value="Upload" title="*Cannot Attach more than 200KB file" onclick="javascript:uploadNew('${AddattMap.docType}','${AddattMap.cmbDtlID}')">Upload</button>
                        <input type="hidden"  name="${AddattMap.docType}cnt" id="${AddattMap.docType}cnt" value="1"/>						
						 <c:choose>
						
						 <c:when test="${AddattMap.docType =='webExRec'}">
						  <input type="hidden" name="${AddattMap.docType}size" id="${AddattMap.docType}size" value="15728640">
						 </c:when>
						  <c:when test="${AddattMap.docType =='ClaimWebexRecording'}">
						  <input type="hidden" name="${AddattMap.docType}size" id="${AddattMap.docType}size" value="15728640">
						 </c:when>
						 <c:when test="${AddattMap.docType =='casesheetattach'}">
						  <input type="hidden" name="${AddattMap.docType}size" id="${AddattMap.docType}size" value="10500000">
						 </c:when>
						  <c:when test="${AddattMap.docType =='billattach'}">
						  <input type="hidden" name="${AddattMap.docType}size" id="${AddattMap.docType}size" value="10500000">
						 </c:when>
						 <c:otherwise>
						 <input type="hidden" name="${AddattMap.docType}size" id="${AddattMap.docType}size" value="204800">
						 </c:otherwise>
						 </c:choose>
                    </td></tr>
         <tr><td >  </td><td >  </td>
	<td   nowrap="nowrap" align="right">
    <div id="${AddattMap.docType}div">
    </div>
    </td></tr>
	</table>
    </td></tr>
    
</c:forEach>

</table>
<br><br>
<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  >
<tr><td align="center" colspan="4"  class="tbheader"><b>View Attachments </b></td></tr>
<tr><td>&nbsp;</td></tr>
<c:if test="${followUpFlg ne 'Y' && cochFollowUpFlg ne 'Y' }">
<c:if test="${fn:length(AttachmentMap.AttachmentList) lt 1}" >
<tr><td colspan="2" align="center"> No Attachments Found </td></tr>
<tr><td>&nbsp;</td></tr>
</c:if>
<c:set var="loopCount" value="0" />

<c:forEach items="${AttachmentMap.AttachmentList}" var="attMap" begin="0" varStatus="status1">
<c:set var="loopCount" value="${loopCount + 1}" /> 
<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
	
	<td width="48%" valign="top">
	<table width="99%" border="0" valign="top" cellpadding="0" cellspacing="0" style="padding-top:5px;" class="tbcellBorder"  ><tr>
                <td width="100%" align="left" valign="middle" 
                    style="padding-left:6px;" colspan="2"  class="labelheading1 tbcellCss">
                 
              <b> ${attMap.heading}</b>
                </td>
             
           
</td></tr>
<tr><td>
<c:forEach items="${attMap.lstAttachments}" var="subAttMap" begin="0" varStatus="status1">

<c:if test="${winOpen !='' && winOpen=='Y' }">
<script>
if(window.opener.document.getElementById('${attMap.docType}') != null)
	{
window.opener.document.getElementById('${attMap.docType}').value = 1; 
	}
</script>
<c:if test="${attMap.docType == 'SignPRFForm' && status1.index gt SignPRFForm-1 }" >
<script>
window.opener.document.getElementById('${attMap.docType}').value = 1; 
</SCRIPT>
</c:if>

<c:if test="${(attMap.docType == 'SignPRFForm') && (status1.index lt SignPRFForm-1) || (attMap.docType == 'SignPRFForm') && (status1.index eq SignPRFForm-1)}" >
  <SCRIPT>
           window.opener.document.getElementById('${attMap.docType}').value = 0; 
        </SCRIPT>
</c:if>	




</c:if>

<table >
<tr><td>
 <c:choose>                            

<c:when test="${subAttMap.attachVisibility == 'Y' }"> <!--002-->  

<c:if test="${viewType =='medco' }">  
<c:if test="${fn:contains(attachmentDocType,attMap.docType) }" >
 <input type="checkbox" name="rmvAtt" id="rmvAtt"   title="Click here to select this attachment for removal" value='${subAttMap.attachDocSeqId}'>                             
</c:if>
</c:if>
<font size="2"><a href="#" onclick="javascript:this.style.color = 'brown';fn_caseSheet('${subAttMap.docType}');fn_openAtachment('${subAttMap.savedName}');return false;">${subAttMap.fileName}&nbsp;&nbsp;${subAttMap.fileCrtTime}</a></font>
 </c:when> 
<c:otherwise> 
      
<font color="#027397" size="2"><u>${subAttMap.fileName}&nbsp;&nbsp;${subAttMap.fileCrtTime}</u></font> 
</c:otherwise>
</c:choose>
</td></tr>
</table>
</c:forEach>
</td></tr>
</table>
</c:forEach>
</c:if>

<c:if test="${cochFollowUpFlg eq 'Y' }">    <!-- Added to show attatchments for Cochlear FollowUp in Installment View -->
<c:forEach var="i" begin="0" end="${cochfollowUps}">
<c:choose>
	<c:when test = "${i eq '0'}" >
		<c:set var = "headerText" value ="Cochlear Follow Up:Initial Mapping and Switch On" />
	</c:when>
	<c:when test = "${i eq '1'}" >
		<c:set var = "headerText" value ="Cochlear Follow Up:Audio Verbal Therapy First Installment" />
	</c:when>
	<c:when test = "${i eq '2'}" >
		<c:set var = "headerText" value ="Cochlear Follow Up:Audio Verbal Therapy Second Installment" />
	</c:when>
	<c:when test = "${i eq '3'}" >
		<c:set var = "headerText" value ="Cochlear Follow Up:Audio Verbal Therapy Third Installment" />
	</c:when>
	<c:when test = "${i eq '4'}" >
		<c:set var = "headerText" value ="Cochlear Follow Up:Audio Verbal Therapy Fourth Installment" />
	</c:when>
	<c:otherwise>
		<c:set var = "headerText" value ="Cochlear Follow Up" />
	</c:otherwise>
</c:choose>
<tr><td class="tbheader" width="100%" colspan="2"><strong>${headerText}</strong></td></tr>

<c:if test="${fn:length(AttachmentMap.AttachmentList) lt 1}" >
<tr><td colspan="2" align="center"> No Attachments Found </td></tr>
<tr><td>&nbsp;</td></tr>
</c:if>
<c:set var="loopCount" value="0" />
<c:forEach items="${AttachmentMap.AttachmentList}" var="attMap" begin="0" varStatus="status1">
<c:set var="loopCount" value="${loopCount + 1}" /> 
<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
	
	<td width="48%" valign="top">
	<table width="99%" border="0" valign="top" cellpadding="0" cellspacing="0" style="padding-top:5px;" class="tbcellBorder"  ><tr>
                <td width="100%" align="left" valign="middle" 
                    style="padding-left:6px;" colspan="2"  class="labelheading1 tbcellCss">
                 
              <b> ${attMap.heading}</b>
                </td>
             
           
</td></tr>
<tr><td>
<c:forEach items="${attMap.lstAttachments}" var="subAttMap" begin="0" varStatus="status1">
<c:set var="check" value="${subAttMap.installment}" />
<c:if test="${subAttMap.installment != '' && subAttMap.installment == 'F' }"><c:set var="check" value="0"/></c:if>
<fmt:parseNumber var="follow" type="number" value="${check}" />
<c:if test="${(follow eq i)}">

<c:if test="${winOpen !='' && winOpen=='Y' }">
<script>
if(window.opener.document.getElementById('${attMap.docType}') != null)
	{
window.opener.document.getElementById('${attMap.docType}').value = 1; 
	}
</script>
<c:if test="${attMap.docType == 'SignPRFForm' && status1.index gt SignPRFForm-1 }" >
<script>
window.opener.document.getElementById('${attMap.docType}').value = 1; 
</SCRIPT>
</c:if>

<c:if test="${(attMap.docType == 'SignPRFForm') && (status1.index lt SignPRFForm-1) || (attMap.docType == 'SignPRFForm') && (status1.index eq SignPRFForm-1)}" >
  <SCRIPT>
           window.opener.document.getElementById('${attMap.docType}').value = 0; 
        </SCRIPT>
</c:if>	




</c:if>

<table >
<tr><td>
 <c:choose>                            
<c:when test="${subAttMap.attachVisibility == 'Y' }"> <!--002-->  

<c:if test="${viewType =='medco' }">  
<c:if test="${fn:contains(attachmentDocType,attMap.docType) }" >
<c:if test="${i eq cochCount && (cochRem=='' || cochRem==null || (cochRem!=null && cochRem!='' && cochRem=='Y')) }">
 <input type="checkbox" name="rmvAtt" id="rmvAtt"   title="Click here to select this attachment for removal" value='${subAttMap.attachDocSeqId}'>                             
</c:if>
</c:if>
</c:if>
<font size="2"><a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('${subAttMap.savedName}');return false;">${subAttMap.fileName}&nbsp;&nbsp;${subAttMap.fileCrtTime}</a></font>
 </c:when> 
<c:otherwise> 
      
<font color="#027397" size="2"><u>${subAttMap.fileName}&nbsp;&nbsp;${subAttMap.fileCrtTime}</u></font> 
</c:otherwise>
</c:choose>
</td></tr>
</table></c:if>
</c:forEach>
</td></tr>
</table>
</c:forEach>
</c:forEach>
</c:if>

<c:if test="${followUpFlg eq 'Y' }">    <!-- Added to show attatchments for FollowUp in Installment View -->
<c:forEach var="i" begin="1" end="${followUps}">
<tr><td class="tbheader" width="100%" colspan="2"><strong>FollowUp No:<c:out value="${i}"/></strong></td></tr>
<c:if test="${fn:length(AttachmentMap.AttachmentList) lt 1}" >
<tr><td colspan="2" align="center"> No Attachments Found </td></tr>
<tr><td>&nbsp;</td></tr>
</c:if>
<c:set var="loopCount" value="0" />
<c:forEach items="${AttachmentMap.AttachmentList}" var="attMap" begin="0" varStatus="status1">
<c:set var="loopCount" value="${loopCount + 1}" /> 
<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
	
	<td width="48%" valign="top">
	<table width="99%" border="0" valign="top" cellpadding="0" cellspacing="0" style="padding-top:5px;" class="tbcellBorder"  ><tr>
                <td width="100%" align="left" valign="middle" 
                    style="padding-left:6px;" colspan="2"  class="labelheading1 tbcellCss">
                 
              <b> ${attMap.heading}</b>
                </td>
             
           
</td></tr>
<tr><td>
<c:forEach items="${attMap.lstAttachments}" var="subAttMap" begin="0" varStatus="status1">
<c:set var="check" value="${subAttMap.installment}" />
<fmt:parseNumber var="follow" type="number" value="${check}" />
<c:if test="${follow eq i}">

<c:if test="${winOpen !='' && winOpen=='Y' }">
<script>
if(window.opener.document.getElementById('${attMap.docType}') != null)
	{
window.opener.document.getElementById('${attMap.docType}').value = 1; 
	}
</script>
<c:if test="${attMap.docType == 'SignPRFForm' && status1.index gt SignPRFForm-1 }" >
<script>
window.opener.document.getElementById('${attMap.docType}').value = 1; 
</SCRIPT>
</c:if>

<c:if test="${(attMap.docType == 'SignPRFForm') && (status1.index lt SignPRFForm-1) || (attMap.docType == 'SignPRFForm') && (status1.index eq SignPRFForm-1)}" >
  <SCRIPT>
           window.opener.document.getElementById('${attMap.docType}').value = 0; 
        </SCRIPT>
</c:if>	




</c:if>

<table >
<tr><td>
 <c:choose>                            

<c:when test="${subAttMap.attachVisibility == 'Y' }"> <!--002-->  

<c:if test="${viewType =='medco' }">  
<c:if test="${fn:contains(attachmentDocType,attMap.docType) }" >
 <input type="checkbox" name="rmvAtt" id="rmvAtt"   title="Click here to select this attachment for removal" value='${subAttMap.attachDocSeqId}'>                             
</c:if>
</c:if>
<font size="2"><a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('${subAttMap.savedName}');return false;">${subAttMap.fileName}&nbsp;&nbsp;${subAttMap.fileCrtTime}</a></font>
 </c:when> 
<c:otherwise> 
      
<font color="#027397" size="2"><u>${subAttMap.fileName}&nbsp;&nbsp;${subAttMap.fileCrtTime}</u></font> 
</c:otherwise>
</c:choose>
</td></tr>
</table></c:if>
</c:forEach>
</td></tr>
</table>
</c:forEach>
</c:forEach>
</c:if>
</table>
</td>
</tr>
<tr>
<td>
<c:if test="${fn:length(AttachmentMap.addAttachmentList) eq 0  }" >
<table width="100%">
<br>
<!-- <tr><td align="center">
No Attachments Found
</td></tr>-->
<tr><td>&nbsp;</td></tr>
</table>
</c:if>
</td></tr>
<tr><td align="center">&nbsp;</td></tr>
<tr><td align="center">

<c:if test="${winOpen !='' && winOpen=='Y' }">
<button class="but"   type="button" value="close" onclick="self.close();">Close</button>
<c:if test="${viewType =='medco' && (cochRem=='' || cochRem==null || (cochRem!=null && cochRem!='' && cochRem=='Y')) }">  
<button class="but" type="button" id="Remove" name="Remove"  class="blueButton" value="Remove Attachments" onclick="fn_removeAttachments('${attMap.attach_id}');" >Remove Attachments</button>
</c:if>
</c:if>
</td></tr>
<tr><td align="center">
<c:if test="${viewType =='medco' &&(winOpen =='' || winOpen!='Y')  }">
<button class="but" type="button" id="Remove" name="Remove"  class="blueButton" value="Remove Attachments" onclick="fn_removeAttachments('${attMap.attach_id}');" >Remove Attachments</button>
</c:if>
</td></tr>
</table>

<input type="hidden" name="totattachCnt" id="totattachCnt" value="${attachCount}" />
<input type="hidden" value="" name="attachNos">
<input type="hidden" value="" name="docType">
<input type="hidden" value="" name="actionVal">
<input type="hidden" value="" name="attachType">
<input type="hidden" value="${caseId}" name="caseId">
<input type="hidden" value="Y" name="caseAttachmentFlag">
<input type="hidden" value="${UpdType}" name="UpdType">
<input type="hidden" value="${userRole}" name="userRole">
<input type="hidden" value="${PreauthFlag}" name="PreauthFlag">
<input type="hidden" value="${caseApprovalFlag}" name="caseApprovalFlag" >

</form>
</body>
</html>