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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@include file="/common/include.jsp" %>
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="attachments/PreauthScripts.js"></script> 

<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<%@ include file="/common/includePatientDetails.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script> 
<LINK href="css/style.css" type="text/css" rel="stylesheet"> 
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<!--  <script src="bootstrap/js/fileinput.min.js"></script>
<link href="bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen"> -->
<script LANGUAGE="JavaScript" type="text/javascript" SRC="attachments/attachments.js"></script> 
<%@ include file="/common/includeBootstrapCalendar.jsp"%>
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
.form-control {
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
.roundBut
{
border-radius:50%;
}
.glyphicon-plus-sign,.glyphicon-minus-sign
{
font-size:20px;
}
.fileinput-upload-button
{
display:none;
}
.kv-fileinput-caption
{
display:none;
}
.add-text:hover{ color:black; }

#imageID {
    position: fixed;
    top: 35px;
    right: 6px;
    z-index: 99999;
    cursor: pointer;
    padding: 5px;
    background: #fff;
    text-align: center;
    border: 2px solid #E2E2E2;
}







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



function validateFile(object)
{
	
	
	 if(object.files[0]!=null)
	  {
	  var fileSize=Math.round(object.files[0].size/1024);
	  //alert(fileSize+" Kb ");
	  if(fileSize>200)
	  {
     alert("Attachment size Exceeds 200Kb.Please Remove the attachment");
     
	  }
	  }
}

var attachmentDocType ='';
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
	/*var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.parent.caseApprovalFlag+'&errSearchType='+parent.parent.errSearchType+'&disSearchType='+parent.parent.disSearchType+'&module='+parent.parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
   document.forms[0].submit();*/
	}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
 function addChronicCaseElement(arg,flag)
{
	
	  var ni = document.getElementById(arg+'div');
	  var numi = document.getElementById(arg+'cnt');
	  var num = (document.getElementById(arg+'cnt').value -1)+ 2;
	  numi.value = num;
	  var newdiv = document.createElement('div');
	  var divIdName = arg+'div'+num;
	  newdiv.setAttribute('id',divIdName); 
	  var prevNum = num-1;
	  var totattachCnt = (document.getElementById("totattachCnt").value -1)+2;
	  document.getElementById("totattachCnt").value = totattachCnt;
	  var name="attachedIndex["+totattachCnt+"]";
	  var s =  '<table  align="center">';
		  
	    s= s+'<td colspan=2 align="left" width="32%"><input type="file" class="file" name="'+name+'" id="'+arg+num+'" onchange="return validateFile(this);"  ></input>';
	    
	    s=s+'</td><td colspan=2 align="left" width="32%"></td></tr></table>';
	  newdiv.innerHTML=s;
	  ni.appendChild(newdiv);
	  document.getElementById("Remove"+arg).style.visibility="";
	  if(num > 2) 
	   document.getElementById("Add"+arg).style.visibility="hidden";
	  if((arg=="telephonicAttach") && (num>1)){
	  document.getElementById("Add"+arg).style.visibility="hidden";
	  }
	 /* if(flag =='Y')
		  {
		  parent.fn_resizePage();
		  }*/
	}
function fn_openAtachment(filepath)
{  
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function upload(type,attchtype)
{ 
    var attchcnt=document.getElementById(type+'cnt').value;
    var attachNos = null;
    if(checkSimilarUpload(attchcnt,type,'CaseAttachments') == 1 )
    {
    	 for(i=1;i<=attchcnt;i++)
    	   {
    	      var a = document.getElementById(type+i).name;
    	     var  numbers = a.match(/\d+\.?\d*/g)
    	       
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
       		 document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=upldEnrollAttach&actionVal=caseAttachments&openWin=${winOpen}&chronicId=${chronicId}&UpdType=${UpdType}";
        }
        else
        {
        	document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=upldEnrollAttach&actionVal=caseAttachments&openWin=${winOpen}&chronicId=${chronicId}&caseId=${caseId}&UpdType=${UpdType}";
        }
        document.FileAttachment.submit(); 
    }
}
function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";
}
var dbFilesCnt=-1;
var dbFilesArray=new Array();

function fn_removeAttachments(attchtype)
{
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
	document.FileAttachment.method="POST";
	document.FileAttachment.docType.value=result;
	if('${UpdType}'!=null && '${UpdType}'=='ehfFollowUp')
		document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removefollowUpAttachments&openWin=${winOpen}&UpdType=${UpdType}";

		else if(('${UpdType}'!=null && '${UpdType}'=='chronicAttach'))
				document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removechronicAttachments&openWin=${winOpen}&UpdType=${UpdType}&chronicId=${chronicId}";

		else
	    document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removePreauthAttachments&openWin=${winOpen}&UpdType=${UpdType}";
    
    document.FileAttachment.submit();
	}
function fn_getPatDetails(){

	var chronicId='${chronicId}';
	
	var url="/<%=context%>/chronicAction.do?actionFlag=casePrintForm&chronicId="+chronicId+"&type=patView";
	document.getElementById("complaintFrame").src=url;
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
<div class="modal fade" id="viewDtlsID"> 
 <div class="modal-dialog" style="width:90%;margin-right:5%;height:400px" id="modal-lgx">
   <div class="modal-content" style="height:350px;">
      <div class="modal-body" >
	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  <iframe  id="complaintFrame" width="100%" height="250px" frameborder="no" scrolling="yes" > </iframe>
	  </div>
	  <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	  </div><!-- /.modal-content --> 
	</div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 


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

<div id="imageID" style="margin-left:85%;"> 
 <a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
 <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
 <br>Patient Details
 </a>
</div>


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
	<c:if test="${AddattMap.attachType eq 'NM' }"><td width="25%" class="labelheading1 tbcellCss"> <b>${AddattMap.heading} </b> </td></c:if>
	<c:if test="${AddattMap.attachType eq 'M' }"><td width="25%" class="labelheading1 tbcellCss"><b> ${AddattMap.heading}  <font color="red">*</font></b>   </c:if>
	</td>
	<td  width="40%" nowrap="nowrap"  class="tbcellBorder">
	
  &nbsp;<input type="file"  class="file" name='attachedIndex[${attachCount}]' id='${AddattMap.docType}1'  onchange="validateFile(this)"/>
						
						<c:if test="${AddattMap.docType != 'onBedPoto'}">			
                       <span  class="glyphicon glyphicon-plus-sign"     style="color:#008FF4;width: 30px;" id="Add${AddattMap.docType}"  value="+" onclick="javascript:addChronicCaseElement('${AddattMap.docType}','Y');" class="FieldBlack" title="click to browse attachment"></span>
                       <span  class="glyphicon glyphicon-minus-sign"    style="color:#EA5E5E;width: 30px;"  id="Remove${AddattMap.docType}"  value="-" onclick="removeElement('${AddattMap.docType}','Y')" style="visibility:hidden" class="FieldBlack" title="click to delete attachment"></span>						
						</c:if>
						<c:if test="${AddattMap.docType == 'onBedPoto'}">	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						</td>
						<td >
						
						<button class="btn btn-success"   type="button"  id="upload${AddattMap.docType}" style="width:90px" value="Upload" title="*Cannot Attach more than 200KB file" onclick="javascript:upload('${AddattMap.docType}','${AddattMap.cmbDtlID}')">Upload&nbsp;<span class="glyphicon glyphicon-upload"></span></button>
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


<c:set var="counter" value="${1}"></c:set> 	
 <c:forEach var="i" begin="1" end="${inst}">
	  
<tr>
<td>

<div class="panel-group" id="accordion${i}" style="width: 810px;align:center;">

  <div class="panel panel-default">
    <div class="tbheader" style="height:20px;width:810px;" style="color:#FFF;align:center;" >
        <a data-toggle="collapse" data-parent="#accordion${i}" href="#collapse${i}" title="Click to View" style="color:#FFF; display:block;">
         <span class="glyphicon glyphicon-plus" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;size:10px"><b> Installment <c:out value="${i}"/></b></span>
         </a></div>


      <div id="collapse${i}" class="panel-collapse collapse">
  
<script>
var currentInst="${inst}";
var counter="${counter}";
if(currentInst==counter)
{
var id="collapse"+counter;
	document.getElementById(id).className ='panel-collapse collapse in';
}

</script>

      <div class="panel-body">
     
	<table align="center" class=".table table-responsive" width="100%"
		class="tb">



<c:if test="${fn:length(AttachmentMap.AttachmentList) lt 1}" >
<tr><td colspan="2" align="center"> No Attachments Found </td></tr>
<tr><td>&nbsp;</td></tr>
</c:if>



<c:set var="loopCount" value="0" />
<c:forEach items="${AttachmentMap.AttachmentList}" var="attMap" begin="0" varStatus="status1">



<c:set var="loopCount" value="${loopCount + 1}" /> 

	<tr>
	
	
	<td width="48%" valign="top">
	<table width="100%" border="0" valign="top" cellpadding="0" cellspacing="0" style="padding-top:5px;" class="tbcellBorder"  ><tr>
                <td width="50%" align="left" valign="middle" 
                    style="padding-left:6px;" colspan="2"  class="labelheading1 tbcellCss">
                 
              <b> ${attMap.heading}</b>
                </td>
             
           
</tr>
<tr><td>
<c:forEach items="${attMap.lstAttachments}" var="subAttMap" begin="0" varStatus="status1">
<c:set var="chronicFlag" value="${chronicFlag }"/>


<c:set var="balance" value="${subAttMap.installment}" />

 						<fmt:parseNumber var="installment" type="number" value="${balance}" />
 						<c:if test="${installment eq i }">

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
</td></c:forEach></table></div></div></div></div></div></td></tr>
<c:set var="counter" value="${counter+1}" />
</c:forEach>
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
<c:if test="${viewType =='medco' }">  
<button class="btn btn-danger" type="button" id="Remove" name="Remove"  class="blueButton" value="Remove Attachments" onclick="fn_removeAttachments('${attMap.attach_id}');" >Remove Attachments&nbsp;<span class="glyphicon glyphicon-remove"></span></button>
</c:if>
</c:if>
</td></tr>
<tr><td align="center">
<c:if test="${viewType =='medco' &&(winOpen =='' || winOpen!='Y')  }">
<button class="btn btn-danger" type="button" id="Remove" name="Remove"  class="blueButton" value="Remove Attachments" onclick="fn_removeAttachments('${attMap.attach_id}');" >Remove Attachments&nbsp;<span class="glyphicon glyphicon-remove"></span></button>
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