<!DOCTYPE html>
<%@ page  contentType="text/html;charset=utf-8"          
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>
<html> 
<head>
<title>Case Attachments</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script SRC="attachments/PreauthScripts.js"></script> 
<script SRC="attachments/attachments.js"></script> 
<script src="/<%=context%>/js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script  SRC="/<%=context%>/Preauth/maximizeScreen.js"></script> 
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

.tdClass{
		border:1px solid #bcd8ff;
		width:25% !important;
	}	
.styleChange{
}
.tbheader{
background:#325F95;
}
.modal-header{
background:#325F95;
color:white;
}

</style>
<script >
var attachmentDocType ='';

function fn_upload(type,attchtype)
{ 
    var attchcnt=document.getElementById(type+'cnt').value;
    var attachNos = null;
    if(checkSimilarUpload(attchcnt,type,'ahcAttachments') == 1 )
    {
    	 for(i=1;i<=attchcnt;i++)
    	   {
    	      var a = document.getElementById(type+i).name;
    	     var  numbers = a.match(/\d+\.?\d*/g);
    	     if(attachNos == null){
    	    	 attachNos = numbers;
    	     }
    	     else{
    	    	 attachNos =attachNos+"~"+numbers;
    	     }
	    	
    	   }
    	// alert(attachNos);
    	document.getElementById("upload"+type).disabled=true;
         document.FileAttachment.method="POST";
        document.FileAttachment.attachNos.value=attachNos;
        document.FileAttachment.docType.value=type;
        document.FileAttachment.attachType.value=attchtype;
        fn_loadImage();
        document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=upldEnrollAttach&actionVal=ahcAttachments&casesSearchFlag=${casesSearchFlag}&ahcId=${ahcId}&UpdType=${UpdType}";
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


var dropArray =  new Array();
function fn_maxmizeTop()
{
parent.fn_maxmizeTop();

	}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
function fn_openAtachment(filepath)
{  
    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&filePath="+filepath;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function addAhcElement(arg,flag)
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
    s= s+'<td colspan=2 align="left" width="32%" class="tbcellCss"><input type="file" name="'+name+'" id="'+arg+num+'" class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)" > </input>';
    s=s+'</td><td colspan=2 align="left" width="32%"></td></tr></table>';
  newdiv.innerHTML=s;
  ni.appendChild(newdiv);
  document.getElementById("Remove"+arg).style.visibility="";
  if(num > 4){
   document.getElementById("Add"+arg).style.visibility="hidden";
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
	document.FileAttachment.action="/<%=context%>/attachmentAction.do?actionFlag=removeAhcAttachments&UpdType=${UpdType}&ahcId=${ahcId}";
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
<form name="FileAttachment" action="/ahcClaimsAction.do" enctype="multipart/form-data" method="post">
<!-- Modal for patient details  -->  

<div id="backgroundPopup"></div>

<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-top:0px;margin:0px auto;" >
<tr><td>
<table border="0" width="100%" >
<c:if test="${winOpen !='' && winOpen=='Y' && headerShow ne 'N' }">
<tr class="tbheader">
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Attachments</strong></td>
</tr>
</c:if>
<c:if test="${winOpen =='' || winOpen==null && headerShow ne 'N'}">
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

<table  width="95%" border="0" align="center" cellpadding="0"  cellspacing="0" style="padding-top:5px;" >
		<c:if test="${investList ne null}">
				<c:if test="${fn:length(investList)>0}">
						
					<table style="width:100%" border="0">
						<tr>
							<c:forEach items="${investList}" var="iterator" varStatus="iterateInd">
								<c:if test="${((iterateInd.index)%5)==0}">
								</tr>
								<tr>
								</c:if>
								<td class="tbcellBorder" width="20%">
								<html:checkbox name="ahcClaimsForm" property="testCheck" value="${iterator.ID }" styleId="testCheck" onclick="javascript:fn_addTestReport(this,'${iterator.VALUE }','${iterator.CONST }');"/>
								${iterator.VALUE }
								</td>
								
							</c:forEach>	
						</tr>
					</table>
				</c:if>
			</c:if>

</table><br/>
<table width="100%" id="ahcInvestAttachTable" style="padding-right:10em;padding-left:10em;">
				<tr id="emptyRow" style="line-height:0px !important;">
					
					<td width="50%">&nbsp;</td>
					<td width="50%">&nbsp;</td>
					
				</tr>
			</table>

<br>

<table width="100%">

<c:if test="${viewType eq 'medco' && status eq 'CD373' && (casesSearchFlag eq null || (casesSearchFlag ne null && casesSearchFlag ne 'Y'))}">
<tr>
<td align="center" ><input class="but blueButton" type="button" value="Upload" onclick="javascript:fn_uploadAttachments();"></input></td>
</tr>
</c:if>
</table>
<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  >
<tr><td align="center" colspan="4"  class="tbheader"><b>View Attachments </b></td></tr>
<tr><td>&nbsp;</td></tr>

<c:if test="${fn:length(attachList) lt 1}" >
<tr><td colspan="2" align="center"> No Attachments Found </td></tr>
<tr><td>&nbsp;</td></tr>
</c:if>
<c:if test="${attachList ne null }">
				<c:if test="${fn:length(attachList) gt 0}">
					
					<table width="100%">
					
						<tbody>
						<tr><td class="tbheader" colspan="6" align="center"><b>INVESTIGATION ATTACHMENTS</b></td></tr>
							<tr>
							<c:set var="listLength" value="${fn:length(investList)}" />
							<c:if test="${listLength < 4 }">
								<c:set var="tdWidth" value="${180/(listLength)}" />
							</c:if>	
							<c:if test="${listLength > 3 }">
								<c:set var="tdWidth" value="16" />
							</c:if>
								<c:forEach items="${attachList }" var ="iterator" varStatus="item">
									<c:if test="${((item.index)%4)==0 }">
										<tr>
									</c:if>
										<td class="labelheading1 tbcellCss" width="${tdWidth}%">
										<b>${item.index+1} . ${iterator.ATTACH}</b>
										</td>
										<td class="tbcellBorder" width="${tdWidth}%"> 
											  <!--  &nbsp; &nbsp; &nbsp; --><a  style="color:red" href="javascript:fn_openAttachment('${iterator.caseId}','${iterator.VALUE}')" title="click to open Attachment">
											
											 ${iterator.ID} 
											</a>
										</td>
								</c:forEach>	
							</tr>
						</tbody>	
					</table>
				</c:if>
			</c:if>
			
	<c:if test='${oldConsultList ne null}'>
					<c:if test='${fn:length(oldConsultList) gt 0 }'>
					<br>
					<table style="width:100%;"  id="consultDtlsDataId">	
				<thead id="heading" >
					<tr id="heading1">
						<td colspan="5" align="center" class="tbheader">	
							<b>CONSULTATIONS ATTACHMENTS</b>
						</td>	
					</tr>
					<tr id="heading2" style="width:100%">
					<td class=" labelheading1 tbcellCss" width="5%">
							<b>Sno.</b>
						</td>
						<td class=" labelheading1 tbcellCss" width="15%">
							<b>Consultation By</b>
						</td>
						<td class=" labelheading1 tbcellCss" width="20%">
							<b>Doctor Name</b>
						</td>
						<td class=" labelheading1 tbcellCss" width="30%">
							<b>Remarks</b> 
						</td>
						<td class=" labelheading1 tbcellCss" width="20%">
							<b>Attachment</b> 
						</td>
						<!-- <td class="tbcellBorder labelheading1" width="10%">
							<b>Action</b>
						</td> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${oldConsultList}" var ="iterator1" varStatus="item">
						<tr>
							<td class="tbcellBorder" >${item.index+1}</td>
							<td class="tbcellBorder" >${iterator1.UNITTYPE}</td>       
        					<td class="tbcellBorder">${iterator1.UNITNAME} </td> 
							<td class="tbcellBorder">${iterator1.REMARKS} </td> 
							<td class="tbcellBorder"><a style="color:red;cursor:pointer;" onclick="javascript:fn_openAttachment('${iterator1.ID}','${iterator1.PATH}');">View</a></td>
							<!-- <td class="tbcellBorder"><button class="btn btn-warning" type="button" value="Delete" onclick="javascript:fn_deleteConsultOnload(this,'${iterator1.COUNT}');">Delete&nbsp;<i class="fa fa-times"></i></button></td> -->
							</tr></c:forEach>	
				</tbody>
			</table>
			</c:if>
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

</c:if>
</td></tr>
</table>

<input type="hidden" name="totattachCnt" id="totattachCnt" value="${attachCount}" />
<input type="hidden" value="" name="attachNos">
<input type="hidden" value="" name="docType">
<input type="hidden" value="" name="actionVal">
<input type="hidden" value="" name="attachType">
<input type="hidden" value="${caseId}" name="caseId">
<input type="hidden" value="AHC" name="caseAttachmentFlag">
<input type="hidden" value="${UpdType}" name="UpdType">
<input type="hidden" value="${userRole}" name="userRole">

<div class="modal fade" id="attachDiv"> 
  <div class="modal-dialog"> 
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title" align="center">Attachments</h2>
      </div>
      <div class="modal-body" style="overflow-y:scroll;height:350px;align:center" >
        <iframe src="/"  class="embed-responsive-item" seamless="" id="" width=" 100%" height="100%" ></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->
</form>
</body>
<script>
var investAttachLength=0;
var cellPosition=0;
var arrayLst=new Array();var ahcInvestList=new Array();
var testIdLst= new Array();
var testNameLst= new Array();
<c:if test="${attachList ne null}">
	<c:forEach items="${attachList}" var ="item">
	arrayLst.push("${item.ID}");
	testIdLst.push("${item.CALLID}");
	testNameLst.push("${item.ATTACH}");
	</c:forEach>
</c:if>
$('input[name="testCheck"]').attr("disabled", true);
//alert(arrayLst);alert(testIdLst);alert(testNameLst);

$.each(testIdLst, function (index, value) {
	  $('input[name="testCheck"][value="' + value.toString() + '"]').prop("checked", true);
	if('${viewType}'=='medco' && '${status}'=='CD373')
	  fn_addTestReport(testNameLst[index]);
	});


function fn_addTestReport(val){
	
	var casesSearchFlag= '${casesSearchFlag}';
	if(casesSearchFlag == null || (casesSearchFlag != null && casesSearchFlag != 'Y'))
		{
			
		
	var tableId=document.getElementById('ahcInvestAttachTable');
	if(tableId !=null)
	{
		//Creates a new Row at the Last Position of Table
		
		/*if(val.checked==true)
		{
			
			for (var i=0;i<arrayLst.length;i++){
			if(val.value==testIdLst[i]){
					alert("Investigation for this Test is already added");
					val.checked=false;
					return false;
				}

			}*/
		var row;
		//if((cellPosition%2)==0)
			row=tableId.insertRow(-1);
		//else
		//	row=tableId.rows[tableId.rows.length-1];
		row.id=val.value;
		if(row!=null)
			{
				var cell=row.insertCell(-1);
				cell.innerHTML='<td width="50%" class="tdClass" style="border:1px solid;">'+val+'</td>';
				cell.className='tdClass';
				var cell=row.insertCell(-1);
				cell.innerHTML='<td width="50%" class="tdClass" style="border:1px solid;"><input type="file" style="width:100%" onchange="javascript:checkBrowser(this)" data-toggle="tooltip" title="Choose File to Add Attachment" name="investAttach['+investAttachLength+']" id="investAttach['+investAttachLength+']"></td>';
				cell.className='tdClass';
				cellPosition++;
				//var  ahcInvest=val.value+"~"+name+"~"+price;
				//ahcInvestList[investAttachLength]=ahcInvest;
				investAttachLength++;
			//	alert(investAttachLength);
				
			}
	//}
	
		else if(val.checked==false){
			
				var row = document.getElementById(val.value);
				if(row!=null){
			    row.parentNode.removeChild(row);
			    //alert("deleted");
			   // var delInvest=ahcInvestList.indexOf(val.value+"~"+name+"~"+price);
			   
			   // ahcInvestList.splice(delInvest,1);
			 //   ahcInvestList.splice(row.rowIndex-1,1);
				investAttachLength--;
					//	cellPosition--;
			
				}
			
		

		}	
	}
		}
	
	}



function fn_openAttachment(caseId,attachPath)
{
	$('#attachDiv').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	},'show');
	if(caseId!=null && attachPath !=null)
		{
			var url = "./annualCheckUpAction.do?actionFlag=openAttachmentInvest&callType=Ajax&caseId="+caseId+"&attachPath="+attachPath+"&fromPatientsView=${fromPatientsView}";
			$("#attachDiv iframe").attr({'src':url,
	 	        'height': '100%',
	 	        'width': '100%'});
		}
}
function fn_uploadAttachments(){
	 /*var value = $("[name=testCheck]:checked").length;
	 
		if(value<=0 && '${attachList}'==null){
			 lErrorMsg=lErrorMsg+"Select atleast one investigation ";
			 lField="testCheck";
			}
*/
	
	for (var i=0;i<investAttachLength;i++)
	{
		var id='investAttach['+i+']';
		if(document.getElementById(id)!=null)
			{
				if(document.getElementById(id).value==null ||
						document.getElementById(id).value=='')
					{
						if((i==0 && arrayLst!=null && arrayLst.length>0) || (i>0))
							{ 
								alert('Please add all Investigation Attachments');
								focusBox(document.getElementById(id));
								return false;									
							}
					}
			}
	}
	if(confirm('Do you want to upload the attachments?')==true){
	var testIds="";
	 for(var i=0;i<testIdLst.length;i++)
	 	{
    	if(i==(testIdLst.length-1))
		 	testIds=testIds+testIdLst[i];
    	else
    		testIds=testIds+testIdLst[i]+"~";
			
 	}
	 var url="./ahcClaimsAction.do?actionFlag=uploadAhcAttachments&ahcId=${ahcId}&addTests="+testIds;
		//alert(url);
	 document.forms[0].action=url;
	 document.forms[0].submit();
	 
	}
	
}
function focusBox(arg)
{	
  aField = arg; 
  setTimeout("aField.focus()", 0);
  var x=getOffset( arg ).top;  

}
function checkBrowser(input)
{
	
     if(navigator.appName == "Netscape")
    {
		var sizeCheck=checkFileSizeFF(input);
		var fileCheck=checkFileNameMatch(input);
		if(sizeCheck==false || fileCheck==false)
		input.value='';
    }
     if(navigator.appName == "Microsoft Internet Explorer")
    {  
		var fieldName=input.name;
		var fieldId=input.id;  	
		var sizeCheck=checkFileSizeIE(input);
		var fileCheck=checkFileNameMatch(input);
		if(sizeCheck==false || fileCheck==false)
		{
			var oRow = input.parentNode.parentNode; 
			var filecell;
			if(fieldName.charAt(0)=='g' || fieldName.charAt(0)=='u')
			{
				filecell=oRow.cells[2];
			}
			else if(fieldName.charAt(0)=='a')
			{ 
				filecell=oRow.cells[6];
			}
			filecell.innerHTML='<input type="file"  id='+fieldId+' name='+fieldName+' onchange="checkBrowser(this)"/>';
		}
    }
}
function checkFileSizeFF(input)
{
	var filesize=input.files[0].size;
	if((filesize/(1024))>200)
	{
		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
		return false;
	}
}
function checkFileSizeIE(input)
{
	try
	{
 	var myFSO = new ActiveXObject("Scripting.FileSystemObject");
 	var filepath = input.value;
 	var thefile = myFSO.getFile(filepath);
 	var filesize = thefile.size/(1024);
 	if(filesize>200)
	{
 		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
		return false;
	}
	}
	catch(e)
	{
		jqueryInfoMsg('ActiveX Control Enable',"Please Enable ActiveX control.");
		jqueryInfoMsg('Steps To Enable ActiveX Control',"Go To-->Tools-->Internet Options-->Security-->Trusted Sites-->Click on Sites Button-->Add site url to list-->close-->Click on Custom level Button-->Initialize and script ActiveX controls not marked as safe for scripting---Enable");
		return false;
	}
}
function checkFileNameMatch(input)
{
	var curFile=input.value;
	//var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
	
	var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')));
	var fullFileName=curFile.substring(curFile.lastIndexOf('\\')+1);
	var fileName1=curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
	if(rtVal ==0)   
		{
		jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
		return false;
		}
	if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
		{
		jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
		return false;
		}
	
	if( fileName1.match(/[\-\_]{2}/i))
	{
		jqueryErrorMsg('File Name Validation',"File name should not should not contain consecutive special characters");
		return false;
	}
	var extn=curFile.substring(curFile.lastIndexOf('.')+1).toLowerCase();
	if(extn=='jpg' || extn=='jpeg' || extn=='png' || extn=='bmp')  
		{
		var status=true;
		}
	else
		{
		jqueryErrorMsg('File Type Validation',"Can upload jpg,jpeg,png,bmp extension files only");
		return false;
		}
	var matchCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
       	   var fileName = val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
       	   var curFileName = curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
		   if(fileName==curFileName)
		   matchCount++;
		   if(matchCount>1)
			{
				jqueryErrorMsg('File Name Validation',"File with this filename already exists.Cannot upload");
				return false;
			}
		   
       }
    }
	if(arrayLst!=null && arrayLst.length>0)
		{
			for(var i=0 ;i<arrayLst.length;i++)
				{
					if(arrayLst[i].substring(arrayLst[i].lastIndexOf('\\')+1,arrayLst[i].lastIndexOf('.'))==curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')))
						{
							alert("File with this filename already exists.Cannot upload");
							return false;
						}
				}
		}	
	
}
</script>
</html>