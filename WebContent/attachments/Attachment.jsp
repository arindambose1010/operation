<%@ page  contentType="text/html;charset=utf-8"          
          language="java"
		  import ="com.ahct.attachments.constants.ASRIConstants"
          isErrorPage="false" 
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>
    <c:if test="${ResultMsg != null}">  
    <script type="text/javascript">      
        alert('${ResultMsg}');
    </script>
    </c:if>
	<c:set var="viewAttach" value=""/> 
        <!-- <c:if test="${mode == 'all'}">
            <c:set var="viewAttach" value="none"/>
        </c:if> -->
	
<head>
<title>Attachments</title>

<LINK href="/<%=context%>/css/style.css" type="text/css" rel="stylesheet"> 
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/attachments/PreauthScripts.js"></script>
<script LANGUAGE="JavaScript" type="text/javascript" SRC="/<%=context%>/attachments/SearchScripts.js"></script>
<script LANGUAGE="JavaScript" SRC="/<%=context%>/scripts/popup.js" type="text/javascript"></script>

<style>
#tableID { overflow: scroll; }
</style>

<script type="text/javascript">
function chkSpecialChars(vFileName)
{
	
   var val =1;  
   var iChars = "*|\":<>[]{,}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    
    return val;
} 
function checkKeyCode(evt)
{

var evt = (evt) ? evt : ((event) ? event : null);
var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
if(event.keyCode==116)
{
evt.keyCode=0;
return false;
}
}
document.onkeydown=checkKeyCode;
document.onmousedown=disableclick;

function disableclick(e)
{

  if(event.button==2)
   {
   alert("Right Click Disabled");
     return false;    
   }
}

function removeElement(arg) 
{
  var d = document.getElementById(arg+'div');
  var numi = document.getElementById(arg+'cnt');
  var num = numi.value ;
  var num1 = num -1;
  if(num > 1)
  {
    numi.value = num1;
    var divNum=arg+'div'+num;
    var olddiv = document.getElementById(divNum);
    d.removeChild(olddiv);
    if(num-1 == 1) 
     document.getElementById("Remove"+arg).style.visibility="hidden";
    if(num-1 < 2) 
     document.getElementById("Add"+arg).style.visibility="";     
  }
}
function addCaseElement(arg)
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
  var s;
  if(arg!=null && arg=='sgndApplnForm')
	{ 
		
		
	s =  '<table   align="center" style="padding-top:0px;margin:0px auto;" width="55%">';
	if(navigator.appName=='Microsoft Internet Explorer'){
		
		s= s+'<td colspan=2 align="left" ><input type="file" name="'+name+'" id="'+arg+num+'" class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)" > </input>';
	}
	else
		s= s+'<td colspan=2 align="left" >&nbsp;&nbsp;<input type="file" name="'+name+'" id="'+arg+num+'" class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)" > </input>';
  s=s+'</td></tr></table>';

	}
else{
	s =  '<table  align="center">';
    s= s+'<td colspan=2 align="left" width="32%"><input type="file" name="'+name+'" id="'+arg+num+'" class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)" > </input>';
    s=s+'</td><td colspan=2 align="left" width="32%"></td></tr></table>';
}


  newdiv.innerHTML=s;
  ni.appendChild(newdiv);
  document.getElementById("Remove"+arg).style.visibility="";
  if(num > 2) 
   document.getElementById("Add"+arg).style.visibility="hidden";
  if((arg=="telephonicAttach") && (num>1)){
  document.getElementById("Add"+arg).style.visibility="hidden";
  }
}
function checkSimilarUpload(vAttachCnt,typ,view,val)
{
	
	
   var rtVal = 1;
   var vFileName=''; var vSplit;
   for(i=1;i<=vAttachCnt;i++)
   {
	   
	   document.getElementById('upload'+typ).disabled=true;
        vFileName = document.getElementById(typ+i).value; 
		
        if(vFileName == '')
        {
          // alert("All Attachments are mandatory");
           alert(" All attachments are mandatory");
           document.getElementById('upload'+typ).disabled=false;
           
           rtVal = 0; break;
        }
        else
        {
            vSplit=vFileName.split("\\");
            vFileName = vSplit[(vSplit.length)-1];
            if(view == 'CaseAttachments')
            {
                for(k=0;k<=dbFilesCnt;k++)      		
                {						
                    if( vFileName == dbFilesArray[k][1])
                    {
                    	 alert(" Cannot upload similar documents \n\n \'"+vFileName+"\' is already attached in \'"+dbFilesArray[k][0]+"\'");
                    	 resetFileInput(typ+i);
                    	 document.getElementById('upload'+typ).disabled=false;
                        rtVal=0;
                        break;
                    } 
					rtVal = chkSpecailChars(vFileName);
                    if(rtVal ==0) 
                    {
                    	alert(" File name should not contain special characters");
                    	  document.getElementById('upload'+typ).disabled=false;
                    	resetFileInput(typ+i);
					  rtVal=0;
                      break;
                    }
                }					
				if(rtVal == 0)
				{	
					break;						
				}
            }
            if(view == 'InspectionAttachments' || view == 'PatientPhotoCMO' || view == 'TelePatAttach' || view == 'EnrollmentAttach')
            {
               
                for(j=0;j<=attachcnt;j++)
                {
                    if(vFileName == attachmentArray[j][1])
                    {
                    	alert(" Cannot upload similar documents  \n\n \'"+vFileName+"\' is already attached in \'"+attachmentArray[j][0]+"\'");
                    	resetFileInput(typ+i);
                    	  document.getElementById('upload'+typ).disabled=false;
                        rtVal=0;
                        break;
                    }  
                                                    
                    rtVal = chkSpecialChars(vFileName);
                    if(rtVal ==0) 
                    {
                        
                    	alert(" File name should not contain special characters");
                    	resetFileInput(typ+i);
                    	
                    	 document.getElementById('upload'+typ).disabled=false;
                    	 document.getElementById(typ+i).focus();
                    	 
          			 
                      break;
                    }
                }	
                
                if(attachcnt=="-1"){
                	 
                	  rtVal = chkSpecialChars(vFileName);
                      if(rtVal ==0) 
                      {
                      	alert(" File name should not contain special characters");
                      	resetFileInput(typ+i);
                        document.getElementById('upload'+typ).disabled=false;
                      	
                       
                        break;
                      }
                    }								
				if(rtVal == 0)
				{	
					break;						
				}
            }
        }
   }   
   return rtVal;
}
function resetFileInput(id)
{

    var fld = document.getElementById(id);
    if(fld!=null)
    fld.parentNode.innerHTML = fld.parentNode.innerHTML;
}   
function upload(type,attchtype)
{   
	//document.getElementById("upload${attMap.doctype}").disabled= true;
	
    var attchcnt=document.getElementById(type+'cnt').value;
    var attachNos = null;
  
    if(checkSimilarUpload(attchcnt,type,'EnrollmentAttach',type+'cnt') == 1 && checkSimilarUploads(attchcnt,type) == 1)
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
         document.FileAttachment.method="POST";
        document.FileAttachment.attachNos.value=attachNos;
        document.FileAttachment.docType.value=type;
        document.FileAttachment.attachType.value=attchtype;
       document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=upldEnrollAttach";
        document.FileAttachment.submit(); 
    }
}
function checkSimilarUploads(vAtCnt,typ)
{
  var rtVal = 1;
  var vFileName; var vFileName2;
  var vSplit; var vSplit2;  
  var x;
  for(i=1;i<=vAtCnt;i++) 
  {
     vFileName = document.getElementById(typ+i).value;
     document.getElementById('upload'+typ).disabled=true;
     vSplit=vFileName.split("\\");
     vFileName = vSplit[(vSplit.length)-1];		 
     for(j=i+1;j<=vAtCnt;j++)
     { 
          vFileName2 = document.getElementById(typ+j).value;
          vSplit2=vFileName2.split("\\");
          vFileName2 = vSplit2[(vSplit.length)-1];
          if(vFileName2 == vFileName)
          {
        	  alert(" Cannot upload similar documents");
        	  document.getElementById('upload'+typ).disabled=false;
        	  resetFileInput(typ+i);
             rtVal =0; break;
          }
     }   
     if(rtVal ==0)  { break; }
     var len=vFileName.length;	 
      if(len > 0)
      {
          var pos=vFileName.lastIndexOf(".");
          var sub=vFileName.substring(pos+1,len); 
          if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
          {
              flag="false";
              alert(" Cannot upload exe,rar,war files");
              document.getElementById('upload'+typ).disabled=false;
              resetFileInput(typ+i);
             // alert("Cannot Upload exe,rar,war files");
              rtVal =0; break;
          } 
          if(typ=='onBedPoto' || typ=='aftersurg' || typ == 'dischphoto' || typ=='ClaimClinicalPhoto' || typ == 'ClinicalPhotoPreauth')
          {
                if((sub=="gif")||(sub=="jpeg")||(sub=="jpg")||(sub=="bmp")||(sub=="WMF")
                        ||(sub=="wmf")||(sub=="GIF")||(sub=="JPEG")||(sub=="JPG")||(sub=="BMP"))
                {
                  flag="true";			  
                  rtVal =1; 
                  //break;
                } 
                else
                {				
                  flag="false";
                  alert(" The file is not a valid photo image.\nPlease attach a valid photo image.");
                  document.getElementById('upload'+typ).disabled=false;
                  resetFileInput(typ+i);
                  rtVal =0; break;                            
                }
          }
		  if(typ == 'cardproof' || typ == 'addressAttach' || typ == 'photoAttach' || typ == 'kioskOnbedPhoto' || typ == 'PhotoID' || typ == 'patientPhotoWithDefect' || typ == 'empPhoto') 
		  {
			if((sub=="jpeg") || (sub=="gif") || (sub=="JPEG") ||(sub=="jpg") ||(sub=="GIF") ||(sub=="JPG"))
			{
			  flag="true";			  
			  rtVal =1; 
			  //break; 
			}
			else
			{
			  flag="false";
			  alert(" Please attach JPEG/JPG/GIF format only");
			  resetFileInput(typ+i);
			  document.getElementById('upload'+typ).disabled=false;
			  rtVal =0;
			  break;
			}
		  }
          if(typ=='angiogram')
          {
                if((sub=='WRF')||(sub=='wrf'))
                {
                        flag="true";	
                        rtVal =1;
                        //break;
                }
                else
                {				
                      flag="false";
                      alert(" Attach WRF file only");
                      document.getElementById('upload'+typ).disabled=false;
                      resetFileInput(typ+i);
                      rtVal =0; break;
                }
           }
          if(typ=='billattach' || typ == 'casesheetattach')
          {
                if((sub=='PDF')||(sub=='pdf'))
                {
                        flag="true";	
                        rtVal =1;
                        //break;
                }
                else
                {				
                  flag="false";
                  alert(" Attach PDF file only");
                  document.getElementById('upload'+typ).disabled=false;
                  resetFileInput(typ+i);
                  rtVal =0; break;
                }
          }
     } 
  }
  return rtVal;
}

function fn_openAtachment(filepath,fileName)
{  
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath+'&fileName='+fileName;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
<fmt:parseNumber var="PhotoAttachCnt" value="${AttachmentMap.EMPLPhotoCnt}" />   
var attachmentArray = new Array();
var attachcnt=-1;
function fn_open()
{
	window.moveTo(0,0);
	window.resizeTo(screen.availWidth,screen.availHeight);  
	//window.toolbar.visible=false;	
	}
function fn_removeAttachments(attchtype)
{
	var elLength = document.FileAttachment.elements.length; 
    var result='';
	var count=0;
    var confirmDtls = confirm("Attachments once deleted, cannot be retrieved back. You need to re-upload in case you remove wrong attachment. Are you sure you want to remove the selected attachments?");  
    	if(confirmDtls)   
	    {
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
				alert("No attachment selected for removal. Please click on the checkbox for the attachment you want to remove.")
				return;
			}
			document.FileAttachment.method="POST";
			document.FileAttachment.docType.value=result;
			document.FileAttachment.attachType.value=attchtype;			
		    document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removeAttachments";
	        document.FileAttachment.submit();
	    }
    	
    	
    
}
function fn_close()
{
	self.close();
	}
</script>
</head>
<body  onload="javascript:fn_open();" >
<form name="FileAttachment" action="/attachmentAction.do" enctype="multipart/form-data" method="post" >
<br>

<table width="98%" border="0"  
              style="padding-top:0px;margin:0px auto;align:center" >
<tr>
<td>
<table width="95%" border="1"  
             style="padding-top:0px;margin:0px auto;align:center;" >
<c:set var="loopCount" value="0" /> 
<c:set var="attachloopCount" value="0" /> 
<c:set var="DispType" value="" />
<c:set var="signedAppForm" value="${fn:length(AttachmentMap.SignedAppList)}"/> 
<c:forEach items="${AttachmentMap.AttachmentList}" var="attMap" begin="0" varStatus="status1" >

<c:set var="attachDispType" value="${attMap.attachDispType}"/>
 <c:set var="attachCount" value="${attachloopCount + 1}" /> 
<c:set var="loopCount" value="${loopCount + 1}" />
<c:if test="${DispType ne attachDispType}">
	<tr>
	<td colspan="2" class="tbheader" ><b>${attachDispType}</b>
	</td>
	</tr>
<c:set var="loopCount" value="1" />	
	</c:if>
		<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
	<td width="48%" >
<table width="100%" border="0" align="center" cellpadding="0" 
             cellspacing="0" style="padding-top:0px;margin:0px auto;" >
<tr>
<td>
<table width="100%" border="1" align="center" cellpadding="0"
             cellspacing="0" style="padding-top:5px;align:center;margin:0px auto;" >
              <tr>
               <!--  <td width="0%" align="right">
                  <img src="images/r1ghtColumn_Header_leftImg.gif" width="4"
                       height="30" title="Aarogyasri Health Care Trust" alt=""/>
                </td> -->
                <td width="100%" align="left" valign="middle" class="tbheader" 
                    style="padding-left:6px;">
                  <img src="images/bullet2.gif" width="8" height="9"
                       title="Aarogyasri Health Care Trust" alt=""/>
                  &nbsp;&nbsp;
                  <b>${attMap.heading}</b>
                </td>
               
              </tr>
            </table>
</td></tr>

    <tr>
        <td  >
            <table width="95%" border="0" cellspacing="0" cellpadding="6" style="align:center;height: 40px;overflow: scroll;" id="tableID" >
            <script type="text/javascript">	         
                      if(window.opener.document.getElementById('${attMap.doctype}'))
                          {  window.opener.document.getElementById('${attMap.doctype}').value = 0; 
						  }
                    
					</script> <tr> 
                        <td  width="100%" colspan=6 align="center">		
               <div style="overflow: auto;height: 60px;display: " >
                <c:forEach items="${attMap.attachlist}" var="attvo" varStatus="status">
               		 <script type="text/javascript">
                      if('${status.index}' == 0 && window.opener.document.getElementById('${attMap.doctype}'))
                    	  window.opener.document.getElementById('${attMap.doctype}').value = '${(status.index)+1}';  
					</script>  
					<c:if test="${attMap.attach_id == 'CD3000' && status.index gt signedAppForm-1 }" >
               <SCRIPT>
               window.opener.document.getElementById('${attMap.doctype}').value = 1; 
               </SCRIPT>
               </c:if>	
               <c:if test="${(attMap.attach_id == 'CD3000') && (status.index lt signedAppForm-1) || (attMap.attach_id == 'CD3000') && (status.index eq signedAppForm-1)}" >
				  <SCRIPT>
               window.opener.document.getElementById('${attMap.doctype}').value = 0; 
               </SCRIPT>
				</c:if>		
							
                    		
							<font size="2" ><a href="#" onclick="javascript:this.style.color = 'brown';fn_openAtachment('${attvo.savedName}','${attvo.fileName}');return false;">${attvo.fileName}&nbsp;&nbsp;${attvo.fileCrtTime}</a></font>
						   <c:if test="${fn:length(viewFlag) eq 0}" >
						     <input type="checkbox" name="rmvAtt" id="rmvAtt" title="Select to remove attachments"  title="Click here to select this attachment for removal" value='${attvo.attachDocSeqId}'>
						     </c:if>
						<br>
					 <script type="text/javascript"> 
                       attachcnt++; 
                       attachmentArray[attachcnt]=new Array();
                       attachmentArray[attachcnt][0]="${attMap.heading}";                   
                       attachmentArray[attachcnt][1]="${attvo.fileName}";
                   </script>
				 </c:forEach> 
				 </td>
						
                    </tr></div>
			
				<fmt:formatNumber var="attLen" value="${fn:length(attMap.attachlist)}" />
                <c:if test="${attLen eq 0 && mode =='all'}">
                <tr>
                    <td>
                        <center>  <font size="2">No Attachments Available</font></center>
                    </td>
                </tr>
                </c:if>
    <tr>
        <td  align="center">

            <table  >
                <tr>
                    <td colspan=2 align="center" width="32%">
                      <font size="2">  </font>
                    </td>
                    
                    <td colspan=2 align="left" width="34%">
                    </td>
                </tr>
                <tr>
                 <c:if test="${fn:length(viewFlag) eq 0}" >
                    <td colspan="1" align="right" width="10%">
                       <input type="file" name='attachedIndex[${attachCount}]' title="Click browse to select file" id='${attMap.doctype}1' class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)"/>
                    
                    
                    
                     <c:if test="${attMap.doctype == 'empPhoto'}">
                     <button class="but"   type="button" id="Add${attMap.doctype}" value="+" title="Add"  onclick="addCaseElement('${attMap.doctype}')" style="width:55px;visibility:hidden" class="FieldBlack">+</button>
                      <button class="but"   type="button" id="Remove${attMap.doctype}" value="-" title="Remove" onclick="removeElement('${attMap.doctype}')" style="width:55px;visibility:hidden" class="FieldBlack">-</button>						
                     </c:if>
                    			
						<c:if test="${attMap.doctype != 'empPhoto'}">			
                       <button class="but"   type="button" id="Add${attMap.doctype}" value="+" title="Add" onclick="addCaseElement('${attMap.doctype}')" style="width:55px" class="FieldBlack">+</button>
                      <button class="but"   type="button" id="Remove${attMap.doctype}" value="-" title="Remove" onclick="removeElement('${attMap.doctype}')" style="width:55px;visibility:hidden" class="FieldBlack">-</button>				
						</c:if>
						<button class="but"  title="Upload" type="button" id="upload${attMap.doctype}" style="width:120" value="Upload" title="*Cannot Attach more than 200KB file" onclick="javascript:upload('${attMap.doctype}','${attMap.attach_id}')">Upload</button>
                        <input type="hidden" name="${attMap.doctype}cnt" id="${attMap.doctype}cnt" value="1"/>						
						<input type="hidden" name="${attMap.doctype}size" id="${attMap.doctype}size" value="204800">
                    </td>
                    </c:if>
                </tr>
            </table>
        </td>
    </tr>  
    <tr><td colspan=6>
    <div id="${attMap.doctype}div"   style="overflow: auto;height: 52px;"    >
    </div></td></tr>
 
    <!--start 001-->
	<tr style="display:none"><!--style="display:${viewAttach}">-->
	<td colspan=2 width="25%"></td> 
	<td colspan=2 width="25%" align="center">
    <font color="red" size="1">*Cannot Attach more than 200KB file</font>
	</td><td colspan=2 width="25%"></td></tr><!--end 001-->
            </table>
        </td>
	</tr>
	</table></td>
	<c:set var="DispType" value="${attachDispType}" /> 
	<c:if test="${(loopCount% 2) eq 0 }">
	</tr>
	</c:if>
	<div id="${attachCount}div" />
	
	<c:set var="attachloopCount" value="${attachloopCount+1 }" />
</c:forEach>

</table>
</td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
 <c:if test="${fn:length(viewFlag) eq 0}" >
	<td align="center" >
			<button class="but" type="button" id="Remove" name="Remove" title="Remove Attachments" class="blueButton" value="Remove Attachments" onclick="fn_removeAttachments('${attMap.attach_id}');" >Remove Attachments</button>
	  <button class="but" type="button" id="close" name="close" value="close" title="Close"  onclick="javascript:fn_close()" >Close</button>
	</td>
	</c:if>
	<c:if test="${fn:length(viewFlag) ne 0}" >
	<td align="center">
	<button class="but" type="button" id="close" name="close" value="close" title="Close"  onclick="javascript:fn_close()" >Close</button>
	
	</td>
	</c:if>
</tr>
</table>
<input type="hidden" name="totattachCnt" id="totattachCnt" value="${attachCount}" />
<input type="hidden" value="" name="docType">
<input type="hidden" value="" name="actionVal">
<input type="hidden" value="" name="requestType">
<input type="hidden" value="" name="attachNos">
<input type="hidden" value="${upd_type}" name="upd_type">
<input type="hidden" value="${EnrollParntId}" name="EnrollParntId">
<input type="hidden" value="" name="PatientType">
<input type="hidden" value="" name="attachType">
<input type="hidden" name="phase" id="phase" value="${AttachmentMap.PhaseDateMap.phase}"/>
<input type="hidden" name="pCrtDt" id="pCrtDt" value="${AttachmentMap.PhaseDateMap.pCrtDt}"/>
<input type="hidden" value="${disabled}" name="disabled">
<input type="hidden" value="${aadharExists}" name="aadharExists">
</form>
</body>
</html>


